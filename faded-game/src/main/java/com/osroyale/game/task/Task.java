package com.osroyale.game.task;

import java.util.Optional;
import java.util.UUID;

/**
 * A game representing a cyclic unit of work.
 *
 * @author lare96 <http://github.org/lare96>
 */
public abstract class Task {

    /** If execution happens instantly upon being scheduled. */
    private final boolean instant;

    /** The cyclic delay. */
    private int delay;

    /** If registration has taken place. */
    private boolean running;

    /** A counter that determines when execution should take place. */
    private int counter;

    /** An optional attachment. */
    private Optional<Object> key = Optional.empty();

    /** The creation stack trace because jire likes to guess */
    private StackTraceElement[] creationStackTrace;

    /** The time the task started task.run(), if null the task has not ran yet **/
    private Optional<Long> runStartTime = Optional.empty();

    /** Unique task UUID which helps us identify if any tasks will lock up **/
    private final UUID uuid;

    /** The time the task was created **/
    private final long taskCreationTime;

    /** Creates a new {@link Task}. */
    public Task(boolean instant, int delay) {
        if (delay <= 0) {
            instant = true;
            delay = 1;
        }
        this.instant = instant;
        this.delay = delay;
        this.creationStackTrace = Thread.currentThread().getStackTrace();
        this.uuid = UUID.randomUUID();
        this.taskCreationTime = System.currentTimeMillis();
    }

    public String getTaskId() {
        return uuid.toString();
    }

    /** Creates a new {@link Task} that doesn't execute instantly. */
    public Task(int delay) {
        this(false, delay);
    }

    public void setExecutionTime() {
        runStartTime = Optional.of(System.currentTimeMillis());
    }

    public long getTaskCreationTime() {
        return taskCreationTime;
    }

    public Optional<Long> getRunStartTime() {
        return runStartTime;
    }

    public Long getElapsedTimeFromRunStartTime() {
        if (runStartTime.isPresent()) {
            return System.currentTimeMillis() - runStartTime.get();
        }
        return null;
    }

    public String getCreationStackTraceStr() {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement ste : creationStackTrace) {
            sb.append(ste.toString()).append("\n");
        }
        return sb.toString();
    }

    final synchronized void process() {
        if (++counter >= delay && running) {
            baseExecute();
            counter = 0;
        }
    }

    /***
     * Executes the abstract execute method ensuring any methods are called before and after
     */
    protected void baseExecute() {
        setExecutionTime();
        execute();
    }

    /** A function representing the unit of work that will be carried out. */
    protected abstract void execute();

    /** Determines if the task can be ran. */
    public boolean canRun() {
        return true;
    }

    /** Cancels all subsequent executions. Does nothing if already cancelled. */
    public synchronized final void cancel() {
        if (running) {
            onCancel(false);
            running = false;
        }
    }

    /** Cancels all subsequent executions. Does nothing if already cancelled. */
    public synchronized final void cancel(boolean logout) {
        if (running) {
            onCancel(logout);
            running = false;
        }
    }

    /** A function executed when iterated over. */
    void onLoop() { }

    /** A function executed on registration. */
    protected void onSchedule() {  }

    /** A function executed on registration. */
    protected boolean canSchedule() {
        return true;
    }

    /** A function executed on cancellation. */
    protected void onCancel(boolean logout) { }

    /** A function executed on thrown exceptions. */
    void onException(Exception e) {

    }

    /** Attaches a new key. */
    public Task attach(Object newKey) {
        key = Optional.ofNullable(newKey);
        return this;
    }

    /** @return {@code true} if execution happens instantly upon being scheduled. */
    public boolean isInstant() {
        return instant;
    }

    /** @return The cyclic delay. */
    public int getDelay() {
        return delay;
    }

    /** Sets the cyclic delay. */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /** @return {@code true} if registration has taken place. */
    public boolean isRunning() {
        return running;
    }

    /** @return An optional attachment. */
    public Optional<Object> getAttachment() {
        return key;
    }

    synchronized void setRunning(boolean running) {
        this.running = running;
    }
}