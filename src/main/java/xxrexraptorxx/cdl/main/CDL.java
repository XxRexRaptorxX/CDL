package xxrexraptorxx.cdl.main;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
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
        MinecraftForge.EVENT_BUS.register(this);
    }

}
