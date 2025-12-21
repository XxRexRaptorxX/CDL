package xxrexraptorxx.cdl.registry;

import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import xxrexraptorxx.cdl.main.References;
import xxrexraptorxx.cdl.utils.ChestLootModifier;

public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister
            .create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, References.MODID);

    public static void init(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }


    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<ChestLootModifier>> LOOT_GEN = LOOT_MODIFIER_SERIALIZERS.register("special_loot_gen",
            ChestLootModifier.CODEC);


    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
