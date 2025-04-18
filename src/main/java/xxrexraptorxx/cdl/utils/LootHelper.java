package xxrexraptorxx.cdl.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import xxrexraptorxx.cdl.main.References;

public class LootHelper {

    public static LootItemFunction.Builder setItemName(String name) {
        return SetNameFunction.setName(Component.translatable("item." + References.MODID + name), SetNameFunction.Target.ITEM_NAME);
    }


    public static LootItemFunction.Builder setItemName(String name, ChatFormatting formatting) {
        return SetNameFunction.setName(Component.translatable("item." + References.MODID + name).withStyle(formatting), SetNameFunction.Target.ITEM_NAME);
    }


    public static LootItemFunction.Builder setLore(String name) {
        return new SetLoreFunction.Builder().addLine(Component.translatable("lore." + References.MODID + name));
    }


    public static LootItemFunction.Builder setLore(String name, ChatFormatting formatting) {
        return new SetLoreFunction.Builder().addLine(Component.translatable("lore." + References.MODID + name).withStyle(formatting));
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

}
