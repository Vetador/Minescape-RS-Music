package com.vetador.minescapersmusic.handlers;

import com.vetador.minescapersmusic.MinescapeRSMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.mojang.text2speech.Narrator.LOGGER;
import static com.vetador.minescapersmusic.handlers.EnableModHandler.featuresEnabled;
import static net.minecraftforge.api.distmarker.Dist.CLIENT;

@Mod.EventBusSubscriber(modid = MinescapeRSMusic.MODID, value = CLIENT)
public class MusicVolumeHandler {

    public static ResourceLocation musicLoc;
    public static boolean startedFade = false;
    public static int tickCounter = 0;
    public static boolean shouldFadeOut = false;
    public static double volume = 1;
    public static double defaultVolume = 1;
    public static SoundEvent currentMusic = null;
    public static SoundEvent previousMusic = null;
    public static boolean shouldPlayMusic = true;

    @SubscribeEvent
    public static void onTickFade(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getInstance();
        Options options = mc.options;
        if (shouldFadeOut && event.phase == TickEvent.Phase.END && featuresEnabled) {
            if (!startedFade) {

                defaultVolume = options.getSoundSourceVolume(SoundSource.MUSIC);
                startedFade = true;
            }
            tickCounter++;
            volume -= defaultVolume * 0.01666f;
            setMusicVolume(volume);
            if (tickCounter >= 60) {
                startedFade = false;
                shouldFadeOut = false;
                stopAllMusic();
                mc.level.playLocalSound(0,0,0, currentMusic, SoundSource.MUSIC, 1f,1f, false);
                previousMusic = currentMusic;
                tickCounter = 0;
                volume = defaultVolume;
                setMusicVolume(defaultVolume);
                musicLoc = currentMusic.getLocation();
                LOGGER.info("stopping current music. Now playing: " + musicLoc);
            }
        }
    }

    private static void setMusicVolume(double volume) {
        // Clamp the volume between 0.0 and 1.0
        volume = Math.max(0.0f, Math.min(volume, defaultVolume));
        // Access the current music ticker and adjust its volume
        Minecraft mc = Minecraft.getInstance();
        mc.options.getSoundSourceOptionInstance(SoundSource.MUSIC).set(volume);
    }

    private static void stopAllMusic() {
        Minecraft minecraft = Minecraft.getInstance();
        SoundManager soundManager = minecraft.getSoundManager();
        soundManager.stop(); // Stop all sounds from the MUSIC source
    }
}
