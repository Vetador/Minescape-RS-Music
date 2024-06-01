package com.vetador.minescapersmusic.asm.mixin;

import net.minecraft.client.sounds.MusicManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static com.vetador.minescapersmusic.handlers.MusicVolumeHandler.*;
import static com.vetador.minescapersmusic.handlers.EnableModHandler.featuresEnabled;

@Mixin(MusicManager.class)
public class preventReplay {

    /**
     * Prevent the music from playing again if the current music is the same.
     */

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        if (featuresEnabled) {
            if (previousMusic != null && currentMusic != null) {
                if (currentMusic.equals(previousMusic)) {
                    shouldPlayMusic = false;
                } else {
                    shouldPlayMusic = true;
                }
            } else {
                shouldPlayMusic = true;
            }
        }
    }
}
