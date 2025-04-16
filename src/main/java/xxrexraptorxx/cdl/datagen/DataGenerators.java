package xxrexraptorxx.cdl.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import xxrexraptorxx.cdl.main.References;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = References.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        LootModifiersGen lootModifiers = new LootModifiersGen(packOutput, lookupProvider);
        generator.addProvider(event.includeServer(), lootModifiers);
        generator.addProvider(event.includeServer(), new LootTablesGen(packOutput, helper, lootModifiers, lookupProvider));
    }
}
