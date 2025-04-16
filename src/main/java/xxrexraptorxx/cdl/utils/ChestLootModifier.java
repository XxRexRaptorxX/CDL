package xxrexraptorxx.cdl.utils;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.function.Supplier;

//setblock ~ ~ ~ minecraft:chest{LootTable:"minecraft:chests/stronghold_library"}
public class ChestLootModifier extends LootModifier {

    public static final Supplier<MapCodec<ChestLootModifier>> CODEC = Suppliers.memoize(
            () -> RecordCodecBuilder.mapCodec(instance -> codecStart(instance)
                    .and(ResourceKey.codec(Registries.LOOT_TABLE).fieldOf("loot_table").forGetter(m -> m.lootTable))
                    .apply(instance, ChestLootModifier::new)
            )
    );

    private final ResourceKey<LootTable> lootTable;


    public ChestLootModifier(LootItemCondition[] conditions, ResourceKey<LootTable> lootTable) {
        super(conditions);
        this.lootTable = lootTable;
    }


    @Override
    @SuppressWarnings("deprecation")
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        context.getResolver().get(Registries.LOOT_TABLE, lootTable).map(Holder::value).orElse(LootTable.EMPTY)
                .getRandomItemsRaw(context, generatedLoot::add);
        return generatedLoot;
    }


    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}