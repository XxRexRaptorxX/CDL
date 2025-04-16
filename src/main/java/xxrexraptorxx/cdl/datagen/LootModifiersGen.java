package xxrexraptorxx.cdl.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.SetStewEffectFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import xxrexraptorxx.cdl.main.References;
import xxrexraptorxx.cdl.utils.ChestLootModifier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

//Credits: ochotonida from "SomeAssemblyRequired" (https://github.com/ochotonida/some-assembly-required/blob/1.21.1/src/main/java/someassemblyrequired/data/providers/LootModifiers.java#L42)
public class LootModifiersGen extends GlobalLootModifierProvider {

    protected final List<Builder> lootBuilders = new ArrayList<>();

    public LootModifiersGen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries, References.MODID);
    }

    private void addLoot() {
        builder(BuiltInLootTables.SIMPLE_DUNGEON, 0.5).getLootPool().add(
                item(Items.SUSPICIOUS_STEW).apply(SetStewEffectFunction.stewEffect()
                        .withEffect(MobEffects.NIGHT_VISION, UniformGenerator.between(7, 10))
                        .withEffect(MobEffects.JUMP, UniformGenerator.between(7, 10))
                        .withEffect(MobEffects.WEAKNESS, UniformGenerator.between(6, 8))
                        .withEffect(MobEffects.BLINDNESS, UniformGenerator.between(5, 7))
                        .withEffect(MobEffects.POISON, UniformGenerator.between(10, 20))
                        .withEffect(MobEffects.SATURATION, UniformGenerator.between(7, 10))
                )
        );
    }


    protected static LootPoolSingletonContainer.Builder<?> item(Item item) {
        return item(item, 1);
    }


    protected static LootPoolSingletonContainer.Builder<?> item(Item item, int weight) {
        return LootItem.lootTableItem(item).setWeight(weight);
    }


    protected Builder builder(ResourceKey<LootTable> lootTable, double chance) {
        Builder builder = new Builder(lootTable.location().getPath());
        builder.lootModifierCondition(LootTableIdCondition.builder(lootTable.location()).build());
        if (chance != 1) {
            builder.lootPoolCondition(LootItemRandomChanceCondition.randomChance((float) chance));
        }

        lootBuilders.add(builder);
        return builder;
    }


    @Override
    protected void start() {
        addLoot();

        for (Builder lootBuilder : lootBuilders) {
            add(lootBuilder.getName(), lootBuilder.build());
        }
    }

    @SuppressWarnings({"UnusedReturnValue", "unused"})
    protected static class Builder {

        private final String lootTableName;
        private final LootPool.Builder lootPool = LootPool.lootPool();
        private final List<LootItemCondition> conditions;

        private LootContextParamSet paramSet = LootContextParamSets.CHEST;

        private Builder(String lootTableName) {
            this.lootTableName = "inject/" + lootTableName;
            this.conditions = new ArrayList<>();
        }

        private ChestLootModifier build() {
            return new ChestLootModifier(conditions.toArray(new LootItemCondition[]{}), ResourceKey.create(
                    Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(References.MODID, getName())));
        }

        protected LootTable.Builder createLootTable() {
            return new LootTable.Builder().withPool(lootPool);
        }

        public LootContextParamSet getParameterSet() {
            return paramSet;
        }

        public LootPool.Builder getLootPool() {
            return lootPool;
        }

        protected String getName() {
            return lootTableName;
        }

        private Builder parameterSet(LootContextParamSet paramSet) {
            this.paramSet = paramSet;
            return this;
        }

        private Builder lootPoolCondition(LootItemCondition.Builder condition) {
            lootPool.when(condition);
            return this;
        }

        private Builder lootModifierCondition(LootItemCondition condition) {
            conditions.add(condition);
            return this;
        }
    }
}