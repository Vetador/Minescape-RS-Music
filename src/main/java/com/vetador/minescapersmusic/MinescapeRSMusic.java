package com.vetador.minescapersmusic;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MinescapeRSMusic.MODID)
public class MinescapeRSMusic
{
    public static final String MODID = "minescapersmusic";

    public MinescapeRSMusic()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
    }

    private void setup(FMLClientSetupEvent event) {
        SoundsMap.initializeSounds();
    }
}