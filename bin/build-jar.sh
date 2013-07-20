#!/bin/sh
cd ..
jar -cvfm MinecraftMOTD.jar manifest.txt org/minecraft/server/motd/*.class
