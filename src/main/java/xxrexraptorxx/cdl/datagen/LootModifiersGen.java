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
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.minecraft.world.item.equipment.trim.TrimPatterns;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.SetNameFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
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
                .add(item(Items.DIAMOND).apply(LootHelper.setItemName("blood_diamond", ChatFormatting.RED))
                        .apply(LootHelper.setEnchantment(Enchantments.VANISHING_CURSE, lookupEnchantments)))
                .add(item(Items.EMERALD).apply(LootHelper.setItemName("blood_emerald", ChatFormatting.RED))
                        .apply(LootHelper.setEnchantment(Enchantments.VANISHING_CURSE, lookupEnchantments)))
                .add(item(Items.DIAMOND_PICKAXE).apply(LootHelper.setItemName("miners_pickaxe")).apply(LootHelper.setEnchantment(Enchantments.MENDING, 0, 1, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.FORTUNE, 2, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.EFFICIENCY, 2, 5, lookupEnchantments)).apply(LootHelper.setDamage()))
                .add(item(Items.PAPER).apply(LootHelper.setItemName("note")).apply(LootHelper.setLore("note_lost")))
                .add(item(Items.PAPER).apply(LootHelper.setItemName("note")).apply(LootHelper.setLore("note_dig_down")))
                .add(item(Items.AMETHYST_SHARD).apply(LootHelper.setItemName("cursed_crystal", ChatFormatting.RED))
                        .apply(LootHelper.setAttribute(Attributes.MAX_HEALTH, AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(1, 15), EquipmentSlotGroup.ANY))
                        .apply(LootHelper.setGlint(true)))
                .add(item(Items.IRON_PICKAXE).apply(LootHelper.setItemName("climbing_axe")).apply(LootHelper.setDamage())
                        .apply(LootHelper.setAttribute(Attributes.STEP_HEIGHT, AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(1, 3), EquipmentSlotGroup.HAND)));


        builder(BuiltInLootTables.DESERT_PYRAMID, 0.05).getLootPool().add(item(Items.PLAYER_HEAD).apply(LootHelper.setItemName("steves_head")))
                .add(item(Items.GOLDEN_HORSE_ARMOR).apply(LootHelper.setItemName("royal_horse_armor"))
                        .apply(LootHelper.setEnchantment(Enchantments.PROTECTION, 1, 2, lookupEnchantments))
                        .apply(LootHelper.setAttribute(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(0.5F), EquipmentSlotGroup.ARMOR))
                        .apply(LootHelper.setDamage()))
                .add(item(Items.DIAMOND).apply(LootHelper.setItemName("blood_diamond", ChatFormatting.RED))
                        .apply(LootHelper.setEnchantment(Enchantments.VANISHING_CURSE, lookupEnchantments)))
                .add(item(Items.EMERALD).apply(LootHelper.setItemName("blood_emerald", ChatFormatting.RED))
                        .apply(LootHelper.setEnchantment(Enchantments.VANISHING_CURSE, lookupEnchantments)))
                .add(item(Items.AMETHYST_SHARD).apply(LootHelper.setItemName("moon_crystal"))
                        .apply(LootHelper.setAttribute(Attributes.LUCK, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(3), EquipmentSlotGroup.HAND)))
                .add(item(Items.AMETHYST_SHARD).apply(LootHelper.setItemName("cursed_crystal", ChatFormatting.RED))
                        .apply(LootHelper.setAttribute(Attributes.MAX_HEALTH, AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(1, 15), EquipmentSlotGroup.ANY))
                        .apply(LootHelper.setGlint(true)));


        builder(BuiltInLootTables.SIMPLE_DUNGEON, 0.05).getLootPool()
                .add(item(Items.NAME_TAG).apply(SetNameFunction.setName(Component.literal("Grumm"), SetNameFunction.Target.CUSTOM_NAME)))
                .add(item(Items.IRON_CHESTPLATE).apply(LootHelper.setItemName("reinforced_chestplate"))
                        .apply(LootHelper.setEnchantment(Enchantments.PROTECTION, 3, 4, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.UNBREAKING, 1, 3, lookupEnchantments)).apply(LootHelper.setDamage()))
                .add(item(Items.IRON_HELMET).apply(LootHelper.setItemName("hardened_iron_helmet"))
                        .apply(LootHelper.setAttribute(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(0.5F), EquipmentSlotGroup.HEAD))
                        .apply(LootHelper.setDamage()))
                .add(item(Items.IRON_CHESTPLATE).apply(LootHelper.setItemName("hardened_iron_chestplate"))
                        .apply(LootHelper.setAttribute(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(0.5F), EquipmentSlotGroup.CHEST))
                        .apply(LootHelper.setDamage()))
                .add(item(Items.IRON_LEGGINGS).apply(LootHelper.setItemName("hardened_iron_leggings"))
                        .apply(LootHelper.setAttribute(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(0.5F), EquipmentSlotGroup.LEGS))
                        .apply(LootHelper.setDamage()))
                .add(item(Items.IRON_BOOTS).apply(LootHelper.setItemName("hardened_iron_boots"))
                        .apply(LootHelper.setAttribute(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(0.5F), EquipmentSlotGroup.FEET))
                        .apply(LootHelper.setDamage()))
                .add(item(Items.IRON_HORSE_ARMOR).apply(LootHelper.setItemName("hardened_iron_horse_armor"))
                        .apply(LootHelper.setAttribute(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(0.5F), EquipmentSlotGroup.ARMOR))
                        .apply(LootHelper.setDamage()));


        builder(BuiltInLootTables.SHIPWRECK_TREASURE, 0.05).getLootPool()
                .add(item(Items.IRON_BOOTS).apply(LootHelper.setItemName("diving_boots")).apply(LootHelper.setEnchantment(Enchantments.DEPTH_STRIDER, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.AQUA_AFFINITY, 1, lookupEnchantments)).apply(LootHelper.setDamage()))
                .add(item(Items.IRON_HELMET).apply(LootHelper.setItemName("diving_helmet")).apply(LootHelper.setEnchantment(Enchantments.RESPIRATION, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.AQUA_AFFINITY, 1, lookupEnchantments)).apply(LootHelper.setDamage()))
                .add(item(Items.FISHING_ROD).apply(LootHelper.setItemName("pro_fishing_rod")).apply(LootHelper.setEnchantment(Enchantments.UNBREAKING, 1, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.LUCK_OF_THE_SEA, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.LURE, 1, 3, lookupEnchantments)).apply(LootHelper.setDamage()))
                .add(item(Items.TRIDENT).apply(LootHelper.setItemName("poseidon_trident")).apply(LootHelper.setEnchantment(Enchantments.RIPTIDE, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.IMPALING, 3, 5, lookupEnchantments)).apply(LootHelper.setDamage()))
                .add(item(Items.DIAMOND_PICKAXE).apply(LootHelper.setItemName("underwater_pickaxe")).apply(LootHelper.setAttribute(Attributes.WATER_MOVEMENT_EFFICIENCY,
                        AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(0.2f, 1.0f), EquipmentSlotGroup.MAINHAND)).apply(LootHelper.setDamage()));


        builder(BuiltInLootTables.SHIPWRECK_SUPPLY, 0.05).getLootPool().add(item(Items.PUFFERFISH).apply(LootHelper.setItemName("magical_fish")).apply(
                LootHelper.setAttribute(Attributes.WATER_MOVEMENT_EFFICIENCY, AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(0.1f, 0.6f), EquipmentSlotGroup.ANY))
                .apply(LootHelper.setGlint(true)));


        builder(BuiltInLootTables.PILLAGER_OUTPOST, 0.05).getLootPool()
                .add(item(Items.BOW).apply(LootHelper.setItemName("sniper_bow")).apply(LootHelper.setEnchantment(Enchantments.PUNCH, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.POWER, 3, 5, lookupEnchantments)).apply(LootHelper.setDamage()))
                .add(item(Items.CROSSBOW).apply(LootHelper.setItemName("improved_crossbow")).apply(LootHelper.setEnchantment(Enchantments.PIERCING, 1, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.UNBREAKING, 1, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.QUICK_CHARGE, 1, lookupEnchantments)).apply(LootHelper.setDamage()));


        builder(BuiltInLootTables.BASTION_OTHER, 0.05).getLootPool()
                .add(item(Items.FLINT_AND_STEEL).apply(LootHelper.setItemName("infinite_fire", ChatFormatting.RED))
                        .apply(LootHelper.setEnchantment(Enchantments.UNBREAKING, 3, 5, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.FIRE_ASPECT, 2, 5, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.MENDING, 0, 1, lookupEnchantments)));


        builder(BuiltInLootTables.BASTION_HOGLIN_STABLE, 0.05).getLootPool()
                .add(item(Items.BONE).apply(LootHelper.setItemName("dennis_bone")).apply(LootHelper.setLore("dennis_bone")))
                .add(item(Items.GOLDEN_HORSE_ARMOR).apply(LootHelper.setItemName("royal_horse_armor"))
                        .apply(LootHelper.setEnchantment(Enchantments.PROTECTION, 1, 2, lookupEnchantments))
                        .apply(LootHelper.setAttribute(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(0.5F), EquipmentSlotGroup.ARMOR))
                        .apply(LootHelper.setDamage()));


        builder(BuiltInLootTables.BASTION_TREASURE, 0.05).getLootPool()
                .add(item(Items.GOLDEN_SWORD).apply(LootHelper.setItemName("thiefs_sword")).apply(LootHelper.setEnchantment(Enchantments.LOOTING, 3, lookupEnchantments))
                        .apply(LootHelper.setDamage()))
                .add(item(Items.IRON_SWORD).apply(LootHelper.setItemName("chungus_sword")).apply(LootHelper.setEnchantment(Enchantments.KNOCKBACK, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.LOOTING, 1, 5, lookupEnchantments)).apply(LootHelper.setDamage()))
                .add(item(Items.IRON_SWORD).apply(LootHelper.setItemName("malgosha_sword")).apply(LootHelper.setEnchantment(Enchantments.FIRE_ASPECT, 5, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.LOOTING, 1, 5, lookupEnchantments)).apply(LootHelper.setDamage()))
                .add(item(Items.AMETHYST_SHARD).apply(LootHelper.setItemName("moon_crystal", ChatFormatting.RED))
                        .apply(LootHelper.setAttribute(Attributes.LUCK, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(3), EquipmentSlotGroup.HAND))
                        .apply(LootHelper.setGlint(true)));


        builder(BuiltInLootTables.WOODLAND_MANSION, 0.05).getLootPool()
                .add(item(Items.CARVED_PUMPKIN).apply(LootHelper.setItemName("evil_pumpkin", ChatFormatting.DARK_RED))
                        .apply(LootHelper.setLore("evil_pumpkin", ChatFormatting.GRAY)).apply(LootHelper.setEnchantment(Enchantments.BINDING_CURSE, lookupEnchantments)))
                .add(item(Items.PHANTOM_MEMBRANE).apply(LootHelper.setItemName("radiating_phantom_membrane"))
                        .apply(LootHelper.setAttribute(Attributes.GRAVITY, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(-0.03f), EquipmentSlotGroup.HAND))
                        .apply(LootHelper.setGlint(true)))
                .add(item(Items.APPLE).apply(LootHelper.setItemName("delicious_apple"))
                        .apply(LootHelper.setFoodEffect(() -> new MobEffectInstance(MobEffects.POISON, 200, 3), 0.5f)).apply(LootHelper.setGlint(true))
                        .apply(LootHelper.setCount(1, 3)))
                .add(item(Items.PAPER).apply(LootHelper.setItemName("note")).apply(LootHelper.setLore("note_find_the_orb")));


        builder(BuiltInLootTables.IGLOO_CHEST, 0.05).getLootPool()
                .add(item(Items.DIAMOND_BOOTS).apply(LootHelper.setItemName("frost_walker")).apply(LootHelper.setEnchantment(Enchantments.FROST_WALKER, 3, lookupEnchantments))
                        .apply(LootHelper.setDamage()))
                .add(item(Items.EXPERIENCE_BOTTLE).apply(LootHelper.setItemName("life_essence", ChatFormatting.RED)).apply(LootHelper.setCount(1, 3)))
                .add(item(Items.IRON_PICKAXE).apply(LootHelper.setItemName("climbing_axe")).apply(LootHelper.setDamage())
                        .apply(LootHelper.setAttribute(Attributes.STEP_HEIGHT, AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(1, 3), EquipmentSlotGroup.HAND)));


        builder(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_RARE, 0.05).getLootPool()
                .add(item(Items.BREEZE_ROD).apply(LootHelper.setItemName("charged_breeze_rod")).apply(LootHelper.setEnchantment(Enchantments.KNOCKBACK, 10, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.WIND_BURST, 1, 3, lookupEnchantments)))
                .add(item(Items.IRON_AXE).apply(LootHelper.setItemName("hammer")).apply(LootHelper.setEnchantment(Enchantments.KNOCKBACK, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.BREACH, 1, 3, lookupEnchantments)).apply(LootHelper.setDamage()))
                .add(item(Items.CHAINMAIL_BOOTS).apply(LootHelper.setItemName("climbing_boots")).apply(LootHelper.setDamage())
                        .apply(LootHelper.setAttribute(Attributes.STEP_HEIGHT, AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(1, 2), EquipmentSlotGroup.FEET)));


        builder(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS, 0.05).getLootPool()
                .add(item(Items.BREEZE_ROD).apply(LootHelper.setItemName("charged_breeze_rod")).apply(LootHelper.setEnchantment(Enchantments.KNOCKBACK, 10, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.WIND_BURST, 1, 3, lookupEnchantments)))
                .add(item(Items.CHAINMAIL_BOOTS).apply(LootHelper.setItemName("climbing_boots")).apply(LootHelper.setDamage())
                        .apply(LootHelper.setAttribute(Attributes.STEP_HEIGHT, AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(1, 2), EquipmentSlotGroup.FEET)));


        builder(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_UNIQUE, 0.05).getLootPool()
                .add(item(Items.ENCHANTED_GOLDEN_APPLE).apply(LootHelper.setItemName("notch_apple"))
                        .apply(LootHelper.setAttribute(Attributes.MAX_HEALTH, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(5), EquipmentSlotGroup.HAND)))
                .add(item(Items.LEATHER_BOOTS).apply(LootHelper.setItemName("flash_boots")).apply(LootHelper.setColor(16773632))
                        .apply(LootHelper.setArmorTrim(TrimMaterials.REDSTONE, TrimPatterns.TIDE, lookupTrimMaterials, lookupTrimPatterns))
                        .apply(LootHelper.setAttribute(Attributes.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(1), EquipmentSlotGroup.FEET)))
                .add(item(Items.MACE).apply(LootHelper.setItemName("mjölnir")).apply(LootHelper.setEnchantment(Enchantments.KNOCKBACK, 1, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.DENSITY, 5, lookupEnchantments)).apply(LootHelper.setDamage()))
                .add(item(Items.HEAVY_CORE).apply(LootHelper.setItemName("super_heavy_core"))
                        .apply(LootHelper.setAttribute(Attributes.KNOCKBACK_RESISTANCE, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(1), EquipmentSlotGroup.ANY))
                        .apply(LootHelper.setAttribute(Attributes.FALL_DAMAGE_MULTIPLIER, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(1.5f),
                                EquipmentSlotGroup.ANY)));


        builder(BuiltInLootTables.NETHER_BRIDGE, 0.05).getLootPool()
                .add(item(Items.BLAZE_ROD).apply(LootHelper.setItemName("charged_blaze_rod")).apply(LootHelper.setEnchantment(Enchantments.FIRE_ASPECT, 10, lookupEnchantments)))
                .add(item(Items.PAPER).apply(LootHelper.setItemName("note")).apply(LootHelper.setLore("note_lost")))
                .add(item(Items.IRON_PICKAXE).apply(LootHelper.setItemName("climbing_axe")).apply(LootHelper.setDamage())
                        .apply(LootHelper.setAttribute(Attributes.STEP_HEIGHT, AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(1, 3), EquipmentSlotGroup.HAND)));


        builder(BuiltInLootTables.VILLAGE_BUTCHER, 0.05).getLootPool().add(item(Items.BEEF).apply(LootHelper.setItemName("bio_steak"))
                .apply(LootHelper.setFoodEffect(() -> new MobEffectInstance(MobEffects.SATURATION, 200), 0.9f)).apply(LootHelper.setCount(1, 8)));


        builder(BuiltInLootTables.VILLAGE_PLAINS_HOUSE, 0.05).getLootPool()
                .add(item(Items.CAKE).apply(LootHelper.setItemName("birthday_cake")).apply(LootHelper.setLore("birthday_cake_1", ChatFormatting.GRAY)))
                .add(item(Items.CAKE).apply(LootHelper.setItemName("birthday_cake")).apply(LootHelper.setLore("birthday_cake_2", ChatFormatting.GRAY)))
                .add(item(Items.CAKE).apply(LootHelper.setItemName("birthday_cake")).apply(LootHelper.setLore("birthday_cake_3", ChatFormatting.GRAY)))
                .add(item(Items.CAKE).apply(LootHelper.setItemName("birthday_cake")).apply(LootHelper.setLore("birthday_cake_4", ChatFormatting.GRAY)))
                .add(item(Items.COOKIE).apply(LootHelper.setItemName("grandma_cookie")).apply(LootHelper.setFoodEffect(() -> new MobEffectInstance(MobEffects.REGENERATION, 90), 1))
                        .apply(LootHelper.setCount(5, 15)))
                .add(item(Items.PAPER).apply(LootHelper.setItemName("note")).apply(LootHelper.setLore("note_idiot")))
                .add(item(Items.PAPER).apply(LootHelper.setItemName("note")).apply(LootHelper.setLore("note_dig_down")));


        builder(BuiltInLootTables.VILLAGE_TAIGA_HOUSE, 0.05).getLootPool()
                .add(item(Items.CAKE).apply(LootHelper.setItemName("birthday_cake")).apply(LootHelper.setLore("birthday_cake_1", ChatFormatting.GRAY)))
                .add(item(Items.CAKE).apply(LootHelper.setItemName("birthday_cake")).apply(LootHelper.setLore("birthday_cake_2", ChatFormatting.GRAY)))
                .add(item(Items.CAKE).apply(LootHelper.setItemName("birthday_cake")).apply(LootHelper.setLore("birthday_cake_3", ChatFormatting.GRAY)))
                .add(item(Items.CAKE).apply(LootHelper.setItemName("birthday_cake")).apply(LootHelper.setLore("birthday_cake_4", ChatFormatting.GRAY)))
                .add(item(Items.COOKIE).apply(LootHelper.setItemName("grandma_cookie")).apply(LootHelper.setFoodEffect(() -> new MobEffectInstance(MobEffects.REGENERATION, 90), 1))
                        .apply(LootHelper.setCount(5, 15)))
                .add(item(Items.PAPER).apply(LootHelper.setItemName("note")).apply(LootHelper.setLore("note_idiot")))
                .add(item(Items.PAPER).apply(LootHelper.setItemName("note")).apply(LootHelper.setLore("note_dig_down")));


        builder(BuiltInLootTables.VILLAGE_ARMORER, 0.05).getLootPool()
                .add(item(Items.IRON_HELMET).apply(LootHelper.setItemName("hardened_iron_helmet"))
                        .apply(LootHelper.setAttribute(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(0.5F), EquipmentSlotGroup.HEAD))
                        .apply(LootHelper.setDamage()))
                .add(item(Items.IRON_CHESTPLATE).apply(LootHelper.setItemName("hardened_iron_chestplate"))
                        .apply(LootHelper.setAttribute(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(0.5F), EquipmentSlotGroup.CHEST))
                        .apply(LootHelper.setDamage()))
                .add(item(Items.IRON_LEGGINGS).apply(LootHelper.setItemName("hardened_iron_leggings"))
                        .apply(LootHelper.setAttribute(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(0.5F), EquipmentSlotGroup.LEGS))
                        .apply(LootHelper.setDamage()))
                .add(item(Items.IRON_BOOTS).apply(LootHelper.setItemName("hardened_iron_boots"))
                        .apply(LootHelper.setAttribute(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(0.5F), EquipmentSlotGroup.FEET))
                        .apply(LootHelper.setDamage()))
                .add(item(Items.IRON_HORSE_ARMOR).apply(LootHelper.setItemName("hardened_iron_horse_armor"))
                        .apply(LootHelper.setAttribute(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(0.5F), EquipmentSlotGroup.ARMOR))
                        .apply(LootHelper.setDamage()));


        builder(BuiltInLootTables.STRONGHOLD_CROSSING, 0.03).getLootPool().add(item(Items.DIAMOND_SWORD).apply(LootHelper.setItemName("herobrine_sword"))
                .apply(LootHelper.setLore("herobrine_sword", ChatFormatting.GRAY)).apply(LootHelper.setEnchantment(Enchantments.FIRE_ASPECT, lookupEnchantments))
                .apply(LootHelper.setEnchantment(Enchantments.SHARPNESS, 3, lookupEnchantments)).apply(LootHelper.setEnchantment(Enchantments.SWEEPING_EDGE, 3, lookupEnchantments))
                .apply(LootHelper.setEnchantment(Enchantments.KNOCKBACK, 3, lookupEnchantments)).apply(LootHelper.setEnchantment(Enchantments.UNBREAKING, 3, lookupEnchantments))
                .apply(LootHelper.setEnchantment(Enchantments.MENDING, 3, lookupEnchantments)).apply(LootHelper.setEnchantment(Enchantments.VANISHING_CURSE, lookupEnchantments))
                .apply(LootHelper.setEnchantment(Enchantments.BINDING_CURSE, lookupEnchantments)).apply(LootHelper.setDamage()))
                .add(item(Items.DIAMOND_CHESTPLATE).apply(LootHelper.setItemName("herobrine_chestplate")).apply(LootHelper.setLore("herobrine_chestplate", ChatFormatting.GRAY))
                        .apply(LootHelper.setEnchantment(Enchantments.THORNS, lookupEnchantments)).apply(LootHelper.setEnchantment(Enchantments.PROTECTION, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.FIRE_PROTECTION, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.UNBREAKING, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.MENDING, 3, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.VANISHING_CURSE, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.BINDING_CURSE, lookupEnchantments))
                        .apply(LootHelper.setArmorTrim(TrimMaterials.NETHERITE, TrimPatterns.SILENCE, lookupTrimMaterials, lookupTrimPatterns)).apply(LootHelper.setDamage()))
                .add(item(Items.NETHER_STAR).apply(LootHelper.setItemName("herobrine_horcrux")).apply(LootHelper.setLore("herobrine_horcrux", ChatFormatting.GRAY))
                        .apply(LootHelper.setEnchantment(Enchantments.VANISHING_CURSE, lookupEnchantments))
                        .apply(LootHelper.setEnchantment(Enchantments.BINDING_CURSE, lookupEnchantments))
                        .apply(LootHelper.setAttribute(Attributes.ENTITY_INTERACTION_RANGE, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(1),
                                EquipmentSlotGroup.HAND)))
                .add(item(Items.EXPERIENCE_BOTTLE).apply(LootHelper.setItemName("life_essence", ChatFormatting.RED)).apply(LootHelper.setCount(1, 3)))
                .add(item(Items.BEDROCK).apply(LootHelper.setLore("bedrock"))).add(item(Items.ENDER_EYE).apply(LootHelper.setItemName("all_seeing_ender_eye"))
                        .apply(LootHelper.setAttribute(Attributes.CAMERA_DISTANCE, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(4), EquipmentSlotGroup.HAND)));


        builder(BuiltInLootTables.STRONGHOLD_LIBRARY, 0.03).getLootPool()
                .add(item(Items.WRITTEN_BOOK).apply(LootHelper.setItemName("notch_diary"))
                        .apply(LootHelper.setAttribute(Attributes.LUCK, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(5), EquipmentSlotGroup.HAND)))
                .add(item(Items.KNOWLEDGE_BOOK).apply(LootHelper.setItemName("attack_range_spellbook"))
                        .apply(LootHelper.setAttribute(Attributes.ENTITY_INTERACTION_RANGE, AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(1, 3),
                                EquipmentSlotGroup.HAND))
                        .apply(LootHelper.setGlint(true)))
                .add(item(Items.KNOWLEDGE_BOOK).apply(LootHelper.setItemName("build_range_spellbook"))
                        .apply(LootHelper.setAttribute(Attributes.BLOCK_INTERACTION_RANGE, AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(1, 3),
                                EquipmentSlotGroup.HAND))
                        .apply(LootHelper.setGlint(true)))
                .add(item(Items.KNOWLEDGE_BOOK).apply(LootHelper.setItemName("step_spellbook"))
                        .apply(LootHelper.setAttribute(Attributes.STEP_HEIGHT, AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(1, 2), EquipmentSlotGroup.HAND))
                        .apply(LootHelper.setGlint(true)))
                .add(item(Items.KNOWLEDGE_BOOK).apply(LootHelper.setItemName("sneak_spellbook"))
                        .apply(LootHelper.setAttribute(Attributes.SNEAKING_SPEED, AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(0.2f, 0.5f),
                                EquipmentSlotGroup.HAND))
                        .apply(LootHelper.setGlint(true)))
                .add(item(Items.KNOWLEDGE_BOOK).apply(LootHelper.setItemName("walk_spellbook"))
                        .apply(LootHelper.setAttribute(Attributes.MOVEMENT_SPEED, AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(0.2f, 0.8f),
                                EquipmentSlotGroup.HAND))
                        .apply(LootHelper.setGlint(true)))
                .add(item(Items.KNOWLEDGE_BOOK).apply(LootHelper.setItemName("fly_spellbook"))
                        .apply(LootHelper.setAttribute(Attributes.FLYING_SPEED, AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(0.2f, 0.5f),
                                EquipmentSlotGroup.HAND))
                        .apply(LootHelper.setGlint(true)))
                .add(item(Items.KNOWLEDGE_BOOK).apply(LootHelper.setItemName("jump_spellbook"))
                        .apply(LootHelper.setAttribute(Attributes.JUMP_STRENGTH, AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(0.2f, 0.4f),
                                EquipmentSlotGroup.HAND))
                        .apply(LootHelper.setGlint(true)))
                .add(item(Items.PAPER).apply(LootHelper.setItemName("note")).apply(LootHelper.setLore("note_find_the_orb")))
                .add(item(Items.PAPER).apply(LootHelper.setItemName("note")).apply(LootHelper.setLore("note_dig_down")));


        builder(BuiltInLootTables.BURIED_TREASURE, 0.03).getLootPool().add(item(Items.HEART_OF_THE_SEA).apply(LootHelper.setItemName("ultimate_heart_of_the_sea"))
                .apply(LootHelper.setAttribute(Attributes.OXYGEN_BONUS, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(1), EquipmentSlotGroup.HAND)));


        builder(BuiltInLootTables.END_CITY_TREASURE, 0.05).getLootPool()
                .add(item(Items.SHULKER_SPAWN_EGG).apply(LootHelper.setItemName("shulker_egg")).apply(LootHelper.setGlint(true))
                        .apply(LootHelper.setAttribute(Attributes.GRAVITY, AttributeModifier.Operation.ADD_VALUE, ConstantValue.exactly(-0.07f), EquipmentSlotGroup.HAND)));


        builder(BuiltInLootTables.ANCIENT_CITY, 0.05).getLootPool().add(item(Items.PAPER).apply(LootHelper.setItemName("note")).apply(LootHelper.setLore("note_portal")))
                .add(item(Items.PAPER).apply(LootHelper.setItemName("note")).apply(LootHelper.setLore("note_vibrations")))
                .add(item(Items.ECHO_SHARD).apply(LootHelper.setItemName("transmutation_shard"))
                        .apply(LootHelper.setAttribute(Attributes.SCALE, AttributeModifier.Operation.ADD_VALUE, UniformGenerator.between(0.3F, 1.5F), EquipmentSlotGroup.ANY))
                        .apply(LootHelper.setGlint(true)));


        builder(BuiltInLootTables.SPAWN_BONUS_CHEST, 0.05).getLootPool().add(item(Items.PAPER).apply(LootHelper.setItemName("note")).apply(LootHelper.setLore("note_dig_down")));
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

        private Builder(String lootTableName) {
            this.lootTableName = "inject/" + lootTableName;
            this.conditions = new ArrayList<>();
        }


        private ChestLootModifier build() {
            return new ChestLootModifier(conditions.toArray(new LootItemCondition[]{}),
                    ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(References.MODID, getName())));
        }


        protected LootTable.Builder createLootTable() {
            return new LootTable.Builder().withPool(lootPool);
        }


        public LootPool.Builder getLootPool() {
            return lootPool;
        }


        protected String getName() {
            return lootTableName;
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
