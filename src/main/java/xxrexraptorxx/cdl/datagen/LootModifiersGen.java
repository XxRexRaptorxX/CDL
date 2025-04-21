package xxrexraptorxx.cdl.datagen;

import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.minecraft.world.item.armortrim.TrimPatterns;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.SetNameFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import xxrexraptorxx.cdl.main.References;
import xxrexraptorxx.cdl.utils.ChestLootModifier;
import xxrexraptorxx.cdl.utils.LootHelper;

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
        HolderLookup.RegistryLookup<Enchantment> lookupEnchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        HolderLookup.RegistryLookup<TrimMaterial> lookupTrimMaterials = this.registries.lookupOrThrow(Registries.TRIM_MATERIAL);
        HolderLookup.RegistryLookup<TrimPattern> lookupTrimPatterns = this.registries.lookupOrThrow(Registries.TRIM_PATTERN);

        builder(BuiltInLootTables.ABANDONED_MINESHAFT, 0.05).getLootPool()
                .add(item(Items.DIAMOND)
                        .apply(LootHelper.setItemName("blood_diamond", ChatFormatting.RED))
                        .apply(LootHelper.setEnchantment(Enchantments.VANISHING_CURSE, lookupEnchantments))
                )
                .add(item(Items.EMERALD)
                        .apply(LootHelper.setItemName("blood_emerald", ChatFormatting.RED))
                        .apply(LootHelper.setEnchantment(Enchantments.VANISHING_CURSE, lookupEnchantments))
                )
                .add(item(Items.DIAMOND_PICKAXE)
                        .apply(LootHelper.setItemName("miners_pickaxe"))
                        .apply(LootHelper.setEnchantment(Enchantments.MENDING, 0, 1, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.FORTUNE, 2, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.EFFICIENCY, 2, 5, lookupEnchantments))
                        .apply(LootHelper.setDamage())
                );


        builder(BuiltInLootTables.DESERT_PYRAMID, 0.05).getLootPool()
                .add(item(Items.PLAYER_HEAD)
                        .apply(LootHelper.setItemName("steves_head"))
                )
                .add(item(Items.GOLDEN_HORSE_ARMOR)
                        .apply(LootHelper.setItemName("royal_horse_armor"))
                        .apply(LootHelper.setEnchantment(Enchantments.PROTECTION, 3, 4, lookupEnchantments))
                )
                .add(item(Items.DIAMOND)
                        .apply(LootHelper.setItemName("blood_diamond", ChatFormatting.RED))
                        .apply(LootHelper.setEnchantment(Enchantments.VANISHING_CURSE, lookupEnchantments))
                )
                .add(item(Items.EMERALD)
                        .apply(LootHelper.setItemName("blood_emerald", ChatFormatting.RED))
                        .apply(LootHelper.setEnchantment(Enchantments.VANISHING_CURSE, lookupEnchantments))
                )
                .add(item(Items.AMETHYST_SHARD)
                        .apply(LootHelper.setItemName("moon_crystal", ChatFormatting.RED))
                        .apply(LootHelper.setAttribute(Attributes.LUCK, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(3), EquipmentSlotGroup.HAND))
        );


        builder(BuiltInLootTables.SIMPLE_DUNGEON, 0.05).getLootPool()
                .add(item(Items.NAME_TAG)
                        .apply(SetNameFunction.setName(Component.literal("Grumm"), SetNameFunction.Target.CUSTOM_NAME))
                )
                .add(item(Items.IRON_CHESTPLATE)
                        .apply(LootHelper.setItemName("reinforced_chestplate"))
                        .apply(LootHelper.setEnchantment(Enchantments.PROTECTION, 3, 4, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.UNBREAKING, 1, 3, lookupEnchantments))
                        .apply(LootHelper.setDamage())
                );


        builder(BuiltInLootTables.SHIPWRECK_TREASURE, 0.05).getLootPool()
                .add(item(Items.IRON_BOOTS)
                        .apply(LootHelper.setItemName("diving_boots"))
                        .apply(LootHelper.setEnchantment(Enchantments.DEPTH_STRIDER, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.AQUA_AFFINITY, 1, lookupEnchantments))
                        .apply(LootHelper.setDamage())
                )
                .add(item(Items.IRON_HELMET)
                        .apply(LootHelper.setItemName("diving_helmet"))
                        .apply(LootHelper.setEnchantment(Enchantments.RESPIRATION, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.AQUA_AFFINITY, 1, lookupEnchantments))
                        .apply(LootHelper.setDamage())
                )
                .add(item(Items.FISHING_ROD)
                        .apply(LootHelper.setItemName("pro_fishing_rod"))
                        .apply(LootHelper.setEnchantment(Enchantments.UNBREAKING, 1, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.LUCK_OF_THE_SEA, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.LURE, 1, 3, lookupEnchantments))
                        .apply(LootHelper.setDamage())
                )
                .add(item(Items.TRIDENT)
                        .apply(LootHelper.setItemName("poseidon_trident"))
                        .apply(LootHelper.setEnchantment(Enchantments.RIPTIDE, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.IMPALING, 3, 5, lookupEnchantments))
                        .apply(LootHelper.setDamage())
                );


        builder(BuiltInLootTables.PILLAGER_OUTPOST, 0.05).getLootPool()
                .add(item(Items.BOW)
                        .apply(LootHelper.setItemName("sniper_bow"))
                        .apply(LootHelper.setEnchantment(Enchantments.PUNCH, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.POWER, 3, 5, lookupEnchantments))
                        .apply(LootHelper.setDamage())
                )
                .add(item(Items.CROSSBOW)
                        .apply(LootHelper.setItemName("improved_crossbow"))
                        .apply(LootHelper.setEnchantment(Enchantments.PIERCING, 1, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.UNBREAKING, 1, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.QUICK_CHARGE, 1, lookupEnchantments))
                        .apply(LootHelper.setDamage())
                );


        builder(BuiltInLootTables.BASTION_OTHER, 0.05).getLootPool()
                .add(item(Items.FLINT_AND_STEEL)
                        .apply(LootHelper.setItemName("infinite_fire", ChatFormatting.RED))
                        .apply(LootHelper.setEnchantment(Enchantments.UNBREAKING, 3, 5, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.FIRE_ASPECT, 2, 5, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.MENDING, 0, 1, lookupEnchantments))
                );


        builder(BuiltInLootTables.BASTION_TREASURE, 0.05).getLootPool()
                .add(item(Items.GOLDEN_SWORD)
                        .apply(LootHelper.setItemName("thiefs_sword"))
                        .apply(LootHelper.setEnchantment(Enchantments.LOOTING, 3, lookupEnchantments))
                        .apply(LootHelper.setDamage())
                )
                .add(item(Items.AMETHYST_SHARD)
                        .apply(LootHelper.setItemName("moon_crystal", ChatFormatting.RED))
                        .apply(LootHelper.setAttribute(Attributes.LUCK, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(3), EquipmentSlotGroup.HAND))
                );


        builder(BuiltInLootTables.WOODLAND_MANSION, 0.05).getLootPool()
                .add(item(Items.CARVED_PUMPKIN)
                        .apply(LootHelper.setItemName("evil_pumpkin", ChatFormatting.DARK_RED))
                        .apply(LootHelper.setLore("evil_pumpkin", ChatFormatting.GRAY))
                        .apply(LootHelper.setEnchantment(Enchantments.BINDING_CURSE, lookupEnchantments))
                );


        builder(BuiltInLootTables.IGLOO_CHEST, 0.05).getLootPool()
                .add(item(Items.DIAMOND_BOOTS)
                        .apply(LootHelper.setItemName("frost_walker", ChatFormatting.DARK_RED))
                        .apply(LootHelper.setEnchantment(Enchantments.FROST_WALKER, 3, lookupEnchantments))
                        .apply(LootHelper.setDamage())
                );


        builder(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_RARE, 0.05).getLootPool()
                .add(item(Items.BREEZE_ROD)
                        .apply(LootHelper.setItemName("charged_breeze_rod"))
                        .apply(LootHelper.setEnchantment(Enchantments.KNOCKBACK, 10, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.WIND_BURST, 1, 3, lookupEnchantments))
                )
                .add(item(Items.IRON_AXE)
                        .apply(LootHelper.setItemName("hammer"))
                        .apply(LootHelper.setEnchantment(Enchantments.KNOCKBACK, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.BREACH, 1, 3, lookupEnchantments))
                        .apply(LootHelper.setDamage())
                );


        builder(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS, 0.05).getLootPool()
                .add(item(Items.BREEZE_ROD)
                        .apply(LootHelper.setItemName("charged_breeze_rod"))
                        .apply(LootHelper.setEnchantment(Enchantments.KNOCKBACK, 10, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.WIND_BURST, 1, 3, lookupEnchantments))
                );


        builder(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_UNIQUE, 0.05).getLootPool()
                .add(item(Items.ENCHANTED_GOLDEN_APPLE)
                        .apply(LootHelper.setItemName("notch_apple"))
                        .apply(LootHelper.setAttribute(Attributes.MAX_HEALTH, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(5), EquipmentSlotGroup.HAND))
                )
                .add(item(Items.LEATHER_BOOTS)
                        .apply(LootHelper.setItemName("flash_boots"))
                        .apply(LootHelper.setColor(16773632))
                        .apply(LootHelper.setArmorTrim(TrimMaterials.REDSTONE, TrimPatterns.TIDE, lookupTrimMaterials, lookupTrimPatterns))
                        .apply(LootHelper.setAttribute(Attributes.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(1), EquipmentSlotGroup.FEET))
                )
                .add(item(Items.MACE)
                        .apply(LootHelper.setItemName("mjölnir"))
                        .apply(LootHelper.setEnchantment(Enchantments.KNOCKBACK, 1, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.DENSITY, 5, lookupEnchantments))
                        .apply(LootHelper.setDamage())
                )
                .add(item(Items.HEAVY_CORE)
                        .apply(LootHelper.setItemName("super_heavy_core"))
                        .apply(LootHelper.setAttribute(Attributes.KNOCKBACK_RESISTANCE, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(1), EquipmentSlotGroup.HAND))
                );


        builder(BuiltInLootTables.NETHER_BRIDGE, 0.05).getLootPool()
                .add(item(Items.BLAZE_ROD)
                        .apply(LootHelper.setItemName("charged_blaze_rod"))
                        .apply(LootHelper.setEnchantment(Enchantments.FIRE_ASPECT, 10, lookupEnchantments))
                );


        builder(BuiltInLootTables.VILLAGE_PLAINS_HOUSE, 0.05).getLootPool()
                .add(item(Items.CAKE)
                        .apply(LootHelper.setItemName("birthday_cake"))
                        .apply(LootHelper.setLore("birthday_cake_1", ChatFormatting.GRAY))
                )
                .add(item(Items.CAKE)
                        .apply(LootHelper.setItemName("birthday_cake"))
                        .apply(LootHelper.setLore("birthday_cake_2", ChatFormatting.GRAY))
                )
                .add(item(Items.CAKE)
                        .apply(LootHelper.setItemName("birthday_cake"))
                        .apply(LootHelper.setLore("birthday_cake_3", ChatFormatting.GRAY))
                )
                .add(item(Items.CAKE)
                        .apply(LootHelper.setItemName("birthday_cake"))
                        .apply(LootHelper.setLore("birthday_cake_4", ChatFormatting.GRAY))
                )
                .add(item(Items.COOKIE)
                        .apply(LootHelper.setItemName("grandma_cookie"))
                        .apply(LootHelper.setFoodEffect(() -> new MobEffectInstance(MobEffects.REGENERATION, 90), 1))
                        .apply(LootHelper.setCount(5, 15))
                );


        builder(BuiltInLootTables.VILLAGE_TAIGA_HOUSE, 0.05).getLootPool()
                .add(item(Items.CAKE)
                        .apply(LootHelper.setItemName("birthday_cake"))
                        .apply(LootHelper.setLore("birthday_cake_1", ChatFormatting.GRAY))
                )
                .add(item(Items.CAKE)
                        .apply(LootHelper.setItemName("birthday_cake"))
                        .apply(LootHelper.setLore("birthday_cake_2", ChatFormatting.GRAY))
                )
                .add(item(Items.CAKE)
                        .apply(LootHelper.setItemName("birthday_cake"))
                        .apply(LootHelper.setLore("birthday_cake_3", ChatFormatting.GRAY))
                )
                .add(item(Items.CAKE)
                        .apply(LootHelper.setItemName("birthday_cake"))
                        .apply(LootHelper.setLore("birthday_cake_4", ChatFormatting.GRAY))
                )
                .add(item(Items.COOKIE)
                        .apply(LootHelper.setItemName("grandma_cookie"))
                        .apply(LootHelper.setFoodEffect(() -> new MobEffectInstance(MobEffects.REGENERATION, 90), 1))
                        .apply(LootHelper.setCount(5, 15))
                );


        builder(BuiltInLootTables.STRONGHOLD_CROSSING, 0.03).getLootPool()
                .add(item(Items.DIAMOND_SWORD)
                        .apply(LootHelper.setItemName("herobrine_sword"))
                        .apply(LootHelper.setLore("herobrine_sword", ChatFormatting.GRAY))
                        .apply(LootHelper.setEnchantment(Enchantments.FIRE_ASPECT, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.SHARPNESS, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.SWEEPING_EDGE, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.KNOCKBACK, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.UNBREAKING, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.MENDING, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.VANISHING_CURSE, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.BINDING_CURSE, lookupEnchantments))
                        .apply(LootHelper.setDamage())
                )
                .add(item(Items.DIAMOND_CHESTPLATE)
                        .apply(LootHelper.setItemName("herobrine_chestplate"))
                        .apply(LootHelper.setLore("herobrine_chestplate", ChatFormatting.GRAY))
                        .apply(LootHelper.setEnchantment(Enchantments.THORNS, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.PROTECTION, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.FIRE_PROTECTION, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.UNBREAKING, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.MENDING, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.VANISHING_CURSE, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.BINDING_CURSE, lookupEnchantments))
                        .apply(LootHelper.setArmorTrim(TrimMaterials.NETHERITE, TrimPatterns.SILENCE, lookupTrimMaterials, lookupTrimPatterns))
                        .apply(LootHelper.setDamage())
                )
                        .add(item(Items.NETHER_STAR)
                        .apply(LootHelper.setItemName("herobrine_horcrux"))
                        .apply(LootHelper.setLore("herobrine_horcrux", ChatFormatting.GRAY))
                        .apply(LootHelper.setEnchantment(Enchantments.VANISHING_CURSE, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.BINDING_CURSE, lookupEnchantments))
        );


        builder(BuiltInLootTables.STRONGHOLD_LIBRARY, 0.03).getLootPool()
                .add(item(Items.WRITTEN_BOOK)
                        .apply(LootHelper.setItemName("notch_diary"))
                        .apply(LootHelper.setAttribute(Attributes.LUCK, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(5), EquipmentSlotGroup.HAND))
                );


        builder(BuiltInLootTables.BURIED_TREASURE, 0.03).getLootPool()
                .add(item(Items.HEART_OF_THE_SEA)
                        .apply(LootHelper.setItemName("ultimate_heart_of_the_sea"))
                        .apply(LootHelper.setAttribute(Attributes.OXYGEN_BONUS, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(1), EquipmentSlotGroup.HAND))
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