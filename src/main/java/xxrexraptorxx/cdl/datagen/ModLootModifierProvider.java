package xxrexraptorxx.cdl.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.functions.SetNameFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import xxrexraptorxx.cdl.main.References;
import xxrexraptorxx.cdl.utils.ChestLootModifier;

import java.util.concurrent.CompletableFuture;

public class ModLootModifierProvider extends GlobalLootModifierProvider {

    public ModLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, References.MODID);
    }

    public static final ResourceLocation ABANDONED_MINESHAFT = BuiltInLootTables.ABANDONED_MINESHAFT.location();
    public static final ResourceLocation SIMPLE_DUNGEON = BuiltInLootTables.SIMPLE_DUNGEON.location();


    @Override
    protected void start() {
        add("test", new ChestLootModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ABANDONED_MINESHAFT).build(),
                LootItemRandomChanceCondition.randomChance(0.3f).build(),
        }, "test", 1, Items.NETHER_STAR).apply(SetNameFunction.setName(Component.translatable("item.twilightforest.boarkchop").withStyle(Style.EMPTY.withItalic(false)), SetNameFunction.Target.ITEM_NAME),
    }
}