package xxrexraptorxx.cdl.datagen;

import com.google.common.base.Preconditions;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import xxrexraptorxx.cdl.main.References;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("SameParameterValue")
public class LootTablesGen extends LootTableProvider {

    private final List<SubProviderEntry> lootTables = new ArrayList<>();

    private final LootModifiersGen lootModifiers;
    protected final CompletableFuture<HolderLookup.Provider> lookupProvider;


    public LootTablesGen(PackOutput packOutput, Set<ResourceKey<LootTable>> requiredTables, List<SubProviderEntry> subProviders, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, Set.of(), List.of(), lookupProvider);
        this.lootModifiers = subProviders;
        this.lookupProvider = lookupProvider;
    }

    @Override
    public List<SubProviderEntry> getTables() {
        for (LootModifiersGen.Builder lootBuilder : lootModifiers.lootBuilders) {
            addLootTable(lootBuilder.getName(), lootBuilder.createLootTable(), lootBuilder.getParameterSet());
        }

        return lootTables;
    }


    private void addLootTable(String location, LootTable.Builder lootTable, LootContextParamSet lootParameterSet) {
        if (location.startsWith("inject/")) {
            String actualLocation = location.replace("inject/", "");
            Preconditions.checkArgument(existingFileHelper.exists(ResourceLocation.parse("loot_table/" + actualLocation + ".json"), PackType.SERVER_DATA), "Loot table %s does not exist in any known data pack", actualLocation);
        }
        lootTables.add(new SubProviderEntry(registries -> lootBuilder -> lootBuilder.accept(ResourceKey.create(
                Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(References.MODID, location)), lootTable), lootParameterSet));
    }

}