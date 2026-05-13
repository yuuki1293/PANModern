package yuuki1293.panmodern.registry;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import yuuki1293.panmodern.PANModern;
import yuuki1293.panmodern.blockentity.PANCoreBlockEntity;

public class BlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister
        .create(Registries.BLOCK_ENTITY_TYPE, PANModern.MODID);

    public static final Supplier<BlockEntityType<PANCoreBlockEntity>> PAN_CORE_BLOCK_ENTITY = BLOCK_ENTITY_TYPES
        .register(
            "pan_core",
            () -> BlockEntityType.Builder.of(PANCoreBlockEntity::new, Blocks.PAN_CORE_BLOCK.get())
                .build(null));
}
