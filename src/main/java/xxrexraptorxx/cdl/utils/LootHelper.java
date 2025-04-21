package xxrexraptorxx.cdl.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import xxrexraptorxx.cdl.main.References;

public class LootHelper {

    public static LootItemFunction.Builder setItemName(String name) {
        return SetNameFunction.setName(Component.translatable("item." + References.MODID + "." + name), SetNameFunction.Target.ITEM_NAME);
    }


    public static LootItemFunction.Builder setItemName(String name, ChatFormatting formatting) {
        return SetNameFunction.setName(Component.translatable("item." + References.MODID + "." + name).withStyle(formatting), SetNameFunction.Target.ITEM_NAME);
    }


    public static LootItemFunction.Builder setLore(String name) {
        return new SetLoreFunction.Builder().addLine(Component.translatable("lore." + References.MODID + "." + name));
    }


    public static LootItemFunction.Builder setLore(String name, ChatFormatting formatting) {
        return new SetLoreFunction.Builder().addLine(Component.translatable("lore." + References.MODID + "." + name).withStyle(formatting));
    }


    public static LootItemFunction.Builder setDamage() {
        return setDamage(0.2f, 0.7f);
    }


    public static LootItemFunction.Builder setDamage(float min, float max) {
        return SetItemDamageFunction.setDamage(UniformGenerator.between(min, max));
    }


    public static LootItemFunction.Builder setEnchantment(ResourceKey<Enchantment> enchantment, HolderLookup.RegistryLookup<Enchantment> lookup) {
        return setEnchantment(enchantment, 1, lookup);
    }


    public static LootItemFunction.Builder setEnchantment(ResourceKey<Enchantment> enchantment, Integer level, HolderLookup.RegistryLookup<Enchantment> lookup) {
        return new SetEnchantmentsFunction.Builder(false).withEnchantment(lookup.getOrThrow(enchantment), ConstantValue.exactly(level));
    }


    public static LootItemFunction.Builder setEnchantment(ResourceKey<Enchantment> enchantment, Integer minLevel, Integer maxLevel, HolderLookup.RegistryLookup<Enchantment> lookup) {
        return new SetEnchantmentsFunction.Builder(false).withEnchantment(lookup.getOrThrow(enchantment), UniformGenerator.between(minLevel, maxLevel));
    }


    public static LootItemFunction.Builder setAttribute(Holder<Attribute> attribute, AttributeModifier.Operation operation, NumberProvider value, EquipmentSlotGroup slots) {
        return SetAttributesFunction.setAttributes().withModifier(new SetAttributesFunction.ModifierBuilder(ResourceLocation.parse(attribute.getRegisteredName()), attribute, operation, value).forSlot(slots));
    }


    public static LootItemFunction.Builder setGlint(boolean value) {
        return SetComponentsFunction.setComponent(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, value);
    }


    public static LootItemFunction.Builder setArmorTrim(ResourceKey<TrimMaterial> material, ResourceKey<TrimPattern> pattern, HolderLookup.RegistryLookup<TrimMaterial> materialLookup, HolderLookup.RegistryLookup<TrimPattern> patternLookup) {
        return SetComponentsFunction.setComponent(DataComponents.TRIM, new ArmorTrim(materialLookup.getOrThrow(material), patternLookup.getOrThrow(pattern), false));
    }


    public static LootItemFunction.Builder setColor(int rgb) {
        return SetComponentsFunction.setComponent(DataComponents.DYED_COLOR, new DyedItemColor(rgb, false));
    }


    public static LootItemFunction.Builder setColor(int red, int blue, int green) {
        return setColor(red<<16 + green<<8 + blue);
    }
}
