package xxrexraptorxx.cdl.main;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xxrexraptorxx.cdl.utils.Config;

/**
 * @author XxRexRaptorxX (RexRaptor)
 * @projectPage https://www.curseforge.com/minecraft/mc-mods/customized-dungeon-loot
 **/
@Mod(References.MODID)
public class CDL {

    public static final Logger LOGGER = LogManager.getLogger();


    public CDL() {
        Config.init();
        MinecraftForge.EVENT_BUS.register(this);
    }

}
