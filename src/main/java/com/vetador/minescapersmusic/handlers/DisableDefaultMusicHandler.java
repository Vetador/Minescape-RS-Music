package com.vetador.minescapersmusic.handlers;


import com.vetador.minescapersmusic.MinescapeRSMusic;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.vetador.minescapersmusic.handlers.EnableModHandler.featuresEnabled;
import static net.minecraftforge.api.distmarker.Dist.CLIENT;

@Mod.EventBusSubscriber(modid = MinescapeRSMusic.MODID, value = CLIENT)
public class DisableDefaultMusicHandler {


    @SubscribeEvent
    public static void onPlaySound(PlaySoundEvent event) {
        if (event.getName().contains("music") && !event.getName().contains("menu") && featuresEnabled) {
            event.setSound(null);
        }
    }
}
