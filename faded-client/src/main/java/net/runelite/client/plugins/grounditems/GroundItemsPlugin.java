/*
 * Copyright (c) 2017, Aria <aria@ar1as.space>
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.grounditems;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.awt.Color;
import java.awt.Rectangle;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.osroyale.Item;
import com.osroyale.ItemDefinition;

import javax.inject.Inject;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.EvictingQueue;
import com.google.common.collect.ImmutableList;
import com.google.inject.Provides;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.FocusChanged;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.ItemDespawned;
import net.runelite.api.events.ItemQuantityChanged;
import net.runelite.api.events.ItemSpawned;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.events.NpcLootReceived;
import net.runelite.client.events.PlayerLootReceived;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.ItemStack;
import net.runelite.client.input.KeyManager;
import net.runelite.client.input.MouseManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.grounditems.config.HighlightTier;
import net.runelite.client.plugins.grounditems.config.MenuHighlightMode;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.ColorUtil;
import net.runelite.client.util.QuantityFormatter;
import net.runelite.client.util.Text;

@PluginDescriptor(
	name = "Ground Items",
	description = "Highlight ground items and/or show price information",
	tags = {"grand", "exchange", "high", "alchemy", "prices", "highlight", "overlay"}
)
public class GroundItemsPlugin extends Plugin
{
	@Value
	static class PriceHighlight
	{
		private final int price;
		private final Color color;
	}

	// ItemID for coins
	private static final int COINS = ItemID.COINS_995;
	// Ground item menu options
	private static final int FIRST_OPTION = MenuAction.GROUND_ITEM_FIRST_OPTION.getId();
	private static final int SECOND_OPTION = MenuAction.GROUND_ITEM_SECOND_OPTION.getId();
	private static final int THIRD_OPTION = MenuAction.GROUND_ITEM_THIRD_OPTION.getId(); // this is Take
	private static final int FOURTH_OPTION = MenuAction.GROUND_ITEM_FOURTH_OPTION.getId();
	private static final int FIFTH_OPTION = MenuAction.GROUND_ITEM_FIFTH_OPTION.getId();
	private static final int EXAMINE_ITEM = MenuAction.EXAMINE_ITEM_GROUND.getId();
	private static final int CAST_ON_ITEM = MenuAction.SPELL_CAST_ON_GROUND_ITEM.getId();

	private static final String TELEGRAB_TEXT = ColorUtil.wrapWithColorTag("Telekinetic Grab", Color.GREEN) + ColorUtil.prependColorTag(" -> ", Color.WHITE);

	@Getter(AccessLevel.PACKAGE)
	@Setter(AccessLevel.PACKAGE)
	private Map.Entry<Rectangle, GroundItem> textBoxBounds;

	@Getter(AccessLevel.PACKAGE)
	@Setter(AccessLevel.PACKAGE)
	private Map.Entry<Rectangle, GroundItem> hiddenBoxBounds;

	@Getter(AccessLevel.PACKAGE)
	@Setter(AccessLevel.PACKAGE)
	private Map.Entry<Rectangle, GroundItem> highlightBoxBounds;

	@Getter(AccessLevel.PACKAGE)
	@Setter(AccessLevel.PACKAGE)
	private boolean hotKeyPressed;

	@Getter(AccessLevel.PACKAGE)
	@Setter(AccessLevel.PACKAGE)
	private boolean hideAll;

	private List<String> hiddenItemList = new CopyOnWriteArrayList<>();
	private List<String> highlightedItemsList = new CopyOnWriteArrayList<>();

	@Inject
	private GroundItemInputListener inputListener;

	@Inject
	private MouseManager mouseManager;

	@Inject
	private KeyManager keyManager;

	@Inject
	private ItemManager itemManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private GroundItemsConfig config;

	@Inject
	private GroundItemsOverlay overlay;

	@Inject
	private Notifier notifier;
	
	@Inject
	private Client client;

	@Inject
	private ScheduledExecutorService executor;

	@Getter
	private final Map<GroundItem.GroundItemKey, GroundItem> collectedGroundItems = new LinkedHashMap<>();
	private List<PriceHighlight> priceChecks = ImmutableList.of();
	private LoadingCache<NamedQuantity, Boolean> highlightedItems;
	private LoadingCache<NamedQuantity, Boolean> hiddenItems;
	private final Queue<Integer> droppedItemQueue = EvictingQueue.create(16); // recently dropped items

	@Provides
	GroundItemsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(GroundItemsConfig.class);
	}

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
		mouseManager.registerMouseListener(inputListener);
		keyManager.registerKeyListener(inputListener);
		executor.execute(this::reset);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		mouseManager.unregisterMouseListener(inputListener);
		keyManager.unregisterKeyListener(inputListener);
		highlightedItems.invalidateAll();
		highlightedItems = null;
		hiddenItems.invalidateAll();
		hiddenItems = null;
		hiddenItemList = null;
		highlightedItemsList = null;
		collectedGroundItems.clear();
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (event.getGroup().equals("grounditems"))
		{
			executor.execute(this::reset);
		}
	}

	@Subscribe
	public void onGameStateChanged(final GameStateChanged event)
	{
		if (event.getGameState() == GameState.LOADING)
		{
			collectedGroundItems.clear();
		}
	}

	@Subscribe
	public void onItemSpawned(ItemSpawned itemSpawned)
	{
		Item item = itemSpawned.getItem();
		Tile tile = itemSpawned.getTile();

		GroundItem groundItem = buildGroundItem(tile, item);
		
		GroundItem.GroundItemKey groundItemKey = new GroundItem.GroundItemKey(item.itemId, tile.getWorldLocation());
		GroundItem existing = collectedGroundItems.putIfAbsent(groundItemKey, groundItem);
		if (existing != null)
		{
			existing.setQuantity(existing.getQuantity() + groundItem.getQuantity());
			// The spawn time remains set at the oldest spawn
		}
	}

	@Subscribe
	public void onItemDespawned(ItemDespawned itemDespawned)
	{
		Item item = itemDespawned.getItem();
		Tile tile = itemDespawned.getTile();

		GroundItem.GroundItemKey groundItemKey = new GroundItem.GroundItemKey(item.itemId, tile.getWorldLocation());
		GroundItem groundItem = collectedGroundItems.get(groundItemKey);
		if (groundItem == null)
		{
			return;
		}

		if (groundItem.getQuantity() <= item.itemAmount)
		{
			collectedGroundItems.remove(groundItemKey);
		}
		else
		{
			groundItem.setQuantity(groundItem.getQuantity() - item.itemAmount);
			// When picking up an item when multiple stacks appear on the ground,
			// it is not known which item is picked up, so we invalidate the spawn
			// time
			groundItem.setSpawnTime(null);
		}
	}

	@Subscribe
	public void onItemQuantityChanged(ItemQuantityChanged itemQuantityChanged)
	{
		Item item = itemQuantityChanged.getItem();
		Tile tile = itemQuantityChanged.getTile();
		int oldQuantity = itemQuantityChanged.getOldQuantity();
		int newQuantity = itemQuantityChanged.getNewQuantity();

		int diff = newQuantity - oldQuantity;
		GroundItem.GroundItemKey groundItemKey = new GroundItem.GroundItemKey(item.itemId, tile.getWorldLocation());
		GroundItem groundItem = collectedGroundItems.get(groundItemKey);
		if (groundItem != null)
		{
			groundItem.setQuantity(groundItem.getQuantity() + diff);
		}
	}

	@Subscribe
	public void onNpcLootReceived(NpcLootReceived npcLootReceived)
	{
		Collection<ItemStack> items = npcLootReceived.getItems();
		lootReceived(items, LootType.PVM);
	}

	@Subscribe
	public void onPlayerLootReceived(PlayerLootReceived playerLootReceived)
	{
		Collection<ItemStack> items = playerLootReceived.getItems();
		lootReceived(items, LootType.PVP);
	}

	@Subscribe
	public void onClientTick(ClientTick event)
	{
	}

	private void lootReceived(Collection<ItemStack> items, LootType lootType)
	{
		for (ItemStack itemStack : items)
		{
			WorldPoint location = WorldPoint.fromLocal(client, itemStack.getLocation());
			GroundItem.GroundItemKey groundItemKey = new GroundItem.GroundItemKey(itemStack.getId(), location);
			GroundItem groundItem = collectedGroundItems.get(groundItemKey);
			if (groundItem != null)
			{
				groundItem.setLootType(lootType);
			}
		}
	}

	private GroundItem buildGroundItem(final Tile tile, final Item item)
	{
		// Collect the data for the item
		final int itemId = item.itemId;
		final ItemDefinition def = ItemDefinition.lookup(itemId);
		
		int height = tile.getPhysicalLevel();
		
		final GroundItem groundItem = GroundItem.builder()
			.id(itemId)
			.location(tile.getWorldLocation())
			/*.itemId(realItemId)*/
			.itemId(itemId)
			.quantity(item.itemAmount)
			/*.name(itemComposition.getName())*/
			.name(def.name)
			//.haPrice(alchPrice)
			.haPrice(0)
			//.height(tile.getGroundItem().getHeight())
			.height(height)
			//.tradeable(itemComposition.isTradeable())
			.tradeable(true)
			.lootType(LootType.DROPPED)
			//.lootType(dropped ? LootType.DROPPED : LootType.UNKNOWN)
			.spawnTime(Instant.now())
			.stackable(def.stackable)
			//.stackable(itemComposition.isStackable())
			.build();

		return groundItem;
	}

	private void reset()
	{
		// gets the hidden items from the text box in the config
		hiddenItemList = Text.fromCSV(config.getHiddenItems());

		// gets the highlighted items from the text box in the config
		highlightedItemsList = Text.fromCSV(config.getHighlightItems());

		highlightedItems = CacheBuilder.newBuilder()
			.maximumSize(512L)
			.expireAfterAccess(10, TimeUnit.MINUTES)
			.build(new WildcardMatchLoader(highlightedItemsList));

		hiddenItems = CacheBuilder.newBuilder()
			.maximumSize(512L)
			.expireAfterAccess(10, TimeUnit.MINUTES)
			.build(new WildcardMatchLoader(hiddenItemList));

		// Cache colors
		ImmutableList.Builder<PriceHighlight> priceCheckBuilder = ImmutableList.builder();

		if (config.insaneValuePrice() > 0)
		{
			priceCheckBuilder.add(new PriceHighlight(config.insaneValuePrice(), config.insaneValueColor()));
		}

		if (config.highValuePrice() > 0)
		{
			priceCheckBuilder.add(new PriceHighlight(config.highValuePrice(), config.highValueColor()));
		}

		if (config.mediumValuePrice() > 0)
		{
			priceCheckBuilder.add(new PriceHighlight(config.mediumValuePrice(), config.mediumValueColor()));
		}

		if (config.lowValuePrice() > 0)
		{
			priceCheckBuilder.add(new PriceHighlight(config.lowValuePrice(), config.lowValueColor()));
		}

		priceChecks = priceCheckBuilder.build();
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded event)
	{

	}

	void updateList(String item, boolean hiddenList)
	{
		final List<String> hiddenItemSet = new ArrayList<>(hiddenItemList);
		final List<String> highlightedItemSet = new ArrayList<>(highlightedItemsList);

		if (hiddenList)
		{
			highlightedItemSet.removeIf(item::equalsIgnoreCase);
		}
		else
		{
			hiddenItemSet.removeIf(item::equalsIgnoreCase);
		}

		final List<String> items = hiddenList ? hiddenItemSet : highlightedItemSet;

		if (!items.removeIf(item::equalsIgnoreCase))
		{
			items.add(item);
		}

		config.setHiddenItems(Text.toCSV(hiddenItemSet));
		config.setHighlightedItem(Text.toCSV(highlightedItemSet));
	}

	Color getHighlighted(NamedQuantity item, int gePrice, int haPrice)
	{
		if (TRUE.equals(highlightedItems.getUnchecked(item)))
		{
			return config.highlightedColor();
		}

		// Explicit hide takes priority over implicit highlight
		if (TRUE.equals(hiddenItems.getUnchecked(item)))
		{
			return null;
		}

		final int price = getValueByMode(gePrice, haPrice);
		for (PriceHighlight highlight : priceChecks)
		{
			if (price > highlight.getPrice())
			{
				return highlight.getColor();
			}
		}

		return null;
	}

	Color getHidden(NamedQuantity item, int gePrice, int haPrice, boolean isTradeable)
	{
		final boolean isExplicitHidden = TRUE.equals(hiddenItems.getUnchecked(item));
		final boolean isExplicitHighlight = TRUE.equals(highlightedItems.getUnchecked(item));
		final boolean canBeHidden = gePrice > 0 || isTradeable || !config.dontHideUntradeables();
		final boolean underGe = gePrice < config.getHideUnderValue();
		final boolean underHa = haPrice < config.getHideUnderValue();

		// Explicit highlight takes priority over implicit hide
		return isExplicitHidden || (!isExplicitHighlight && canBeHidden && underGe && underHa)
			? config.hiddenColor()
			: null;
	}

	Color getItemColor(Color highlighted, Color hidden)
	{
		if (highlighted != null)
		{
			return highlighted;
		}

		if (hidden != null)
		{
			return hidden;
		}

		return config.defaultColor();
	}

	@Subscribe
	public void onFocusChanged(FocusChanged focusChanged)
	{
		if (!focusChanged.isFocused())
		{
			setHotKeyPressed(false);
		}
	}

	private void notifyHighlightedItem(GroundItem item)
	{
		final boolean shouldNotifyHighlighted = config.notifyHighlightedDrops() &&
			config.highlightedColor().equals(getHighlighted(
				new NamedQuantity(item),
				item.getGePrice(),
				item.getHaPrice()));

		final boolean shouldNotifyTier = config.notifyTier() != HighlightTier.OFF &&
			getValueByMode(item.getGePrice(), item.getHaPrice()) > config.notifyTier().getValueFromTier(config) &&
			FALSE.equals(hiddenItems.getUnchecked(new NamedQuantity(item)));

		final String dropType;
		if (shouldNotifyHighlighted)
		{
			dropType = "highlighted";
		}
		else if (shouldNotifyTier)
		{
			dropType = "valuable";
		}
		else
		{
			return;
		}

		final Player local = client.getLocalPlayer();
		final StringBuilder notificationStringBuilder = new StringBuilder()
			.append("[")
			.append(local.getName())
			.append("] received a ")
			.append(dropType)
			.append(" drop: ")
			.append(item.getName());

		if (item.getQuantity() > 1)
		{
			if (item.getQuantity() > (int) Character.MAX_VALUE)
			{
				notificationStringBuilder.append(" (Lots!)");
			}
			else
			{
				notificationStringBuilder.append(" (")
					.append(QuantityFormatter.quantityToStackSize(item.getQuantity()))
					.append(")");
			}
		}
		
		notifier.notify(notificationStringBuilder.toString());
	}

	private int getValueByMode(int gePrice, int haPrice)
	{
		switch (config.valueCalculationMode())
		{
			case GE:
				return gePrice;
			case HA:
				return haPrice;
			default: // Highest
				return Math.max(gePrice, haPrice);
		}
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked menuOptionClicked)
	{
		if (menuOptionClicked.getMenuAction() == MenuAction.ITEM_DROP)
		{
			int itemId = (int) menuOptionClicked.getId();
			// Keep a queue of recently dropped items to better detect
			// item spawns that are drops
			droppedItemQueue.add(itemId);
		}
	}
}
