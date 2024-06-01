package com.vetador.minescapersmusic.handlers;

import com.vetador.minescapersmusic.MinescapeRSMusic;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.mojang.text2speech.Narrator.LOGGER;

@Mod.EventBusSubscriber(modid = MinescapeRSMusic.MODID, value = Dist.CLIENT)
public class EnableModHandler {

    public static boolean featuresEnabled = false;

    @SubscribeEvent
    public static void onClientConnect(ClientChatReceivedEvent event)
    {
        String systemChat = event.getMessage().getString();
        if (event.isSystem() && systemChat.contains("Welcome to the GamesLabs Network"))
        {
            featuresEnabled = true;
            LOGGER.info("Runescape music features enabled.");
        }
    }

    @SubscribeEvent
    public static void onClientDisconnect(PlayerEvent.PlayerLoggedOutEvent event)
    {
        featuresEnabled = false;
        LOGGER.info("Runescape music features disabled.");
    }
}
