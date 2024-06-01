package com.vetador.minescapersmusic.handlers;

/** For testing purpose */
//@Mod.EventBusSubscriber(modid = MinescapeRSMusic.MODID, value = CLIENT)
public class SetMusicHandler {

    /*
    @SubscribeEvent
    public static void onClientChat(ClientChatEvent event) {
        String message = event.getMessage().toString();
        message = message.replace("'", "").replace(".", "");
        LOGGER.info("message: " + message);
        LOGGER.info("soundMap: " + soundMap.get(message));
        if (soundMap.containsKey(message)) {
            // Create a ResourceLocation for the sound
            ResourceLocation soundID = new ResourceLocation(MinescapeRSMusic.MODID, soundMap.get(message));
            // Create a SoundEvent from the ResourceLocation
            SoundEvent soundEvent = SoundEvent.createFixedRangeEvent(soundID, 16);
            currentMusic = soundEvent;
            if (shouldPlayMusic)
            {
                MusicVolumeHandler.shouldFadeOut = true;
            }
        }
    }

     */
}
