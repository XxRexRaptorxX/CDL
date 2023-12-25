package xxrexraptorxx.cdl.main;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import xxrexraptorxx.cdl.utils.Config;
import xxrexraptorxx.cdl.world.LootTableInjection;

/**
 * @author XxRexRaptorxX (RexRaptor)
 * @projectPage https://www.curseforge.com/minecraft/mc-mods/customized-dungeon-loot
 **/
@Mod(References.MODID)
public class CDL {

    public static final Logger LOGGER = LogManager.getLogger();

    public CDL() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        modBus.addListener(this::setup);

        Config.init();
    }


    private void setup(final @NotNull FMLCommonSetupEvent event) {
        NeoForge.EVENT_BUS.addListener(LootTableInjection::onChestLootLoad);
    }

}
