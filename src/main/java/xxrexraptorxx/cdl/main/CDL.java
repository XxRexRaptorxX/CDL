package xxrexraptorxx.cdl.main;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xxrexraptorxx.cdl.registry.ModLootModifiers;
import xxrexraptorxx.magmacore.main.ModRegistry;

/**
 * @author XxRexRaptorxX (RexRaptor)
 * @projectPage <a href="https://www.curseforge.com/minecraft/mc-mods/customized-dungeon-loot">...</a>
 **/
@Mod(References.MODID)
public class CDL {

    public static final Logger LOGGER = LogManager.getLogger();

    public CDL(IEventBus eventBus, ModContainer container) {
        ModRegistry.register(References.MODID, References.NAME, References.URL);
        ModLootModifiers.init(eventBus);
    }

}
