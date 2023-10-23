#!/bin/sh

JAR_FILE_PATH="/var/www/static.tarnishps.com/Tarnish.jar"
BOOTSTRAP_FILE_PATH="/var/www/static.tarnishps.com/bootstrap.json"

JAR_HASH=`sha256sum $JAR_FILE_PATH | awk '{print $1}'`
JAR_SIZE=`wc -c $JAR_FILE_PATH | awk '{print $1}'`

cat >$BOOTSTRAP_FILE_PATH <<EOF
{
  "artifacts": [
    {
      "hash": "$JAR_HASH",
      "name": "client.jar",
      "path": "https://static.tarnishps.com/Tarnish.jar",
      "size": $JAR_SIZE
    }
  ],
  "client": {
    "artifactId": "client",
    "classifier": "shaded",
    "extension": "jar",
    "groupId": "net.runelite",
    "properties": {},
    "version": "1.5.22.1"
  },
  "clientJvm17Arguments": [
    "-XX:+DisableAttachMechanism",
    "-Xmx512m",
    "-Xss2m",
    "-XX:CompileThreshold=1500",
    "-Djna.nosys=true",
    "--add-opens=java.desktop/sun.awt=ALL-UNNAMED"
  ],
  "clientJvm17MacArguments": [
    "-XX:+DisableAttachMechanism",
    "-Xmx512m",
    "-Xss2m",
    "-XX:CompileThreshold=1500",
    "-Djna.nosys=true",
    "--add-opens=java.desktop/sun.awt=ALL-UNNAMED",
    "--add-opens=java.desktop/com.apple.eawt=ALL-UNNAMED"
  ],
  "clientJvm9Arguments": [
    "-XX:+DisableAttachMechanism",
    "-Xmx512m",
    "-Xss2m",
    "-XX:CompileThreshold=1500",
    "-Djna.nosys=true"
  ],
  "clientJvmArguments": [
    "-XX:+DisableAttachMechanism",
    "-Xmx512m",
    "-Xss2m",
    "-XX:CompileThreshold=1500",
    "-Xincgc",
    "-XX:+UseConcMarkSweepGC",
    "-XX:+UseParNewGC",
    "-Djna.nosys=true"
  ],
  "launcherArguments": [
    "-XX:+DisableAttachMechanism",
    "-Drunelite.launcher.nojvm=true",
    "-Xmx512m",
    "-Xss2m",
    "-XX:CompileThreshold=1500",
    "-Xincgc",
    "-XX:+UseConcMarkSweepGC",
    "-XX:+UseParNewGC",
    "-Djna.nosys=true"
  ],
  "launcherJvm11Arguments": [
    "-XX:+DisableAttachMechanism",
    "-Drunelite.launcher.nojvm=true",
    "-Xmx512m",
    "-Xss2m",
    "-XX:CompileThreshold=1500",
    "-Djna.nosys=true"
  ],
  "launcherJvm17Arguments": [
    "-XX:+DisableAttachMechanism",
    "-Drunelite.launcher.nojvm=true",
    "-Xmx512m",
    "-Xss2m",
    "-XX:CompileThreshold=1500",
    "-Djna.nosys=true",
    "--add-opens=java.desktop/sun.awt=ALL-UNNAMED"
  ],
  "launcherJvm17MacArguments": [
    "-XX:+DisableAttachMechanism",
    "-Drunelite.launcher.nojvm=true",
    "-Xmx512m",
    "-Xss2m",
    "-XX:CompileThreshold=1500",
    "-Djna.nosys=true",
    "--add-opens=java.desktop/sun.awt=ALL-UNNAMED",
    "--add-opens=java.desktop/com.apple.eawt=ALL-UNNAMED"
  ]
}
EOF