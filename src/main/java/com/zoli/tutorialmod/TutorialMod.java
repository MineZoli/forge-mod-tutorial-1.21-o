package com.zoli.tutorialmod;

import com.mojang.logging.LogUtils;
import com.zoli.tutorialmod.block.Modblocks;
import com.zoli.tutorialmod.item.ModCreativeModeTabs;
import com.zoli.tutorialmod.item.Moditems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(TutorialMod.MOD_ID)
public class TutorialMod {

    public static final String MOD_ID = "tutorialmod";

    public static final Logger LOGGER = LogUtils.getLogger();

    public TutorialMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);


        Moditems.register(modEventBus);
        Modblocks.register((modEventBus));


        modEventBus.addListener(this::addCreative);
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
            if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
                event.accept(Moditems.ALEXANDRITE);
                event.accept(Moditems.RAW_ALEXANDRITE);

            }

            if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
                event.accept(Modblocks.RAW_ALEXANDRITE_BLOCK);
                event.accept(Modblocks.ALEXANDRITE_BLOCK);
            }





    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
