package com.vetador.minescapersmusic.asm.mixin;

import com.vetador.minescapersmusic.MinescapeRSMusic;
import com.vetador.minescapersmusic.handlers.MusicVolumeHandler;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.vetador.minescapersmusic.handlers.MusicVolumeHandler.currentMusic;
import static com.vetador.minescapersmusic.handlers.MusicVolumeHandler.shouldPlayMusic;
import static com.vetador.minescapersmusic.SoundsMap.*;
import static com.vetador.minescapersmusic.handlers.EnableModHandler.featuresEnabled;

@Mixin(Gui.class)
public class onSetOverlayMessage {

    @Inject(at = @At(value = "TAIL"), method = "setOverlayMessage(Lnet/minecraft/network/chat/Component;Z)V")
    public void onOverlayMessage(Component pComponent, boolean p_93065_, CallbackInfo ci) {
        if (featuresEnabled) {
            String message = pComponent.getString();
            if (!message.startsWith("궾")) return;
            // Extract the first 14 characters
            String firstPart = message.substring(0, Math.min(16, message.length()));
            // Remove numbers from the first 14 characters
            firstPart = firstPart.replaceAll("[0-9]", "");
            // Get the remaining part of the string
            String remainingPart = message.length() > 16 ? message.substring(16) : "";
            // Combine the modified first part with the remaining part
            message = firstPart + remainingPart;
            message = message.substring(12).replace("'", "").replace(".", "").replaceAll(" - ", "");
            if (soundMap.containsKey(message)) {
                // Create a ResourceLocation for the sound
                ResourceLocation soundID = new ResourceLocation(MinescapeRSMusic.MODID, soundMap.get(message));
                // Create a SoundEvent from the ResourceLocation
                SoundEvent soundEvent = SoundEvent.createFixedRangeEvent(soundID, 16);
                currentMusic = soundEvent;
                if (shouldPlayMusic) {
                    MusicVolumeHandler.shouldFadeOut = true;
                }
            }
        }
    }
}
