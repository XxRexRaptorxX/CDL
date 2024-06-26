package xxrexraptorxx.cdl.world;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.function.Supplier;

//www.misode.github.io/loot-table/
public class ChestLootEnhancerModifier extends LootModifier {

    public static final Supplier<MapCodec<ChestLootEnhancerModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.mapCodec(inst -> codecStart(inst)
            .and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(m -> m.item))
            .and(Codec.INT.fieldOf("count").forGetter(m -> m.count))
            .and(Codec.INT.optionalFieldOf("min_count", -1).forGetter(m -> m.minCount))
            .and(Codec.INT.optionalFieldOf("max_count", -1).forGetter(m -> m.maxCount))
            .apply(inst, ChestLootEnhancerModifier::new)));

    private final Item item;
    private final int count;
    private final int minCount;
    private final int maxCount;


    public ChestLootEnhancerModifier(LootItemCondition[] conditionsIn, Item item, int count, int minCount, int maxCount) {
        super(conditionsIn);
        this.item = item;
        this.count = count;
        this.minCount = minCount;
        this.maxCount = maxCount;
    }


    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        RandomSource random = context.getRandom();
        int itemCount;

        if (minCount >= 0 && maxCount >= 0) {
            itemCount = random.nextIntBetweenInclusive(minCount, maxCount);
        } else {
            itemCount = count;
        }

        ItemStack itemToAdd = new ItemStack(item, itemCount);
        generatedLoot.add(itemToAdd);
        return generatedLoot;
    }


    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}