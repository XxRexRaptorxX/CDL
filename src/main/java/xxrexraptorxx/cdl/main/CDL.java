package xxrexraptorxx.cdl.main;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author XxRexRaptorxX (RexRaptor)
 * @projectPage https://www.curseforge.com/minecraft/mc-mods/customized-dungeon-loot
 **/

@Mod(References.MODID)
public class CDL {

    private static final Logger LOGGER = LogManager.getLogger();

    public CDL() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);

    }


    private void setup(final FMLCommonSetupEvent event) {
    }

}
