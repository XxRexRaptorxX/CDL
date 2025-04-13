package xxrexraptorxx.cdl.utils;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

//setblock ~ ~ ~ minecraft:chest{LootTable:"minecraft:chests/stronghold_library"}
public class ChestLootModifier extends LootModifier {

    public static final MapCodec<ChestLootModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            LootModifier.codecStart(instance).and(instance.group(
                    Codec.STRING.fieldOf("name").forGetter(e -> e.name),
                    Codec.INT.fieldOf("amount").forGetter(e -> e.amount),
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(e -> e.item)
            )).apply(instance, ChestLootModifier::new));

    private final String name;
    private final int amount;
    private final Item item;


    public ChestLootModifier(LootItemCondition[] conditionsIn, String name, int amount, Item item) {
        super(conditionsIn);
        this.name = name;
        this.amount = amount;
        this.item = item;
    }


    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }


    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for(LootItemCondition condition : this.conditions) {
            if(!condition.test(context)) {
                return generatedLoot;
            }
        }
        generatedLoot.add(new ItemStack(this.item));
        return generatedLoot;
    }
}