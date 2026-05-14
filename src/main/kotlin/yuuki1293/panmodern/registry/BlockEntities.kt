package yuuki1293.panmodern.registry

import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.entity.BlockEntityType
import net.neoforged.neoforge.registries.DeferredRegister
import yuuki1293.panmodern.PANModern
import yuuki1293.panmodern.blockentity.PANAdapterBlockEntity
import yuuki1293.panmodern.blockentity.PANCoreBlockEntity
import java.util.function.Supplier

@Suppress("TYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
object BlockEntities {
    val BLOCK_ENTITY_TYPES: DeferredRegister<BlockEntityType<*>> =
        DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, PANModern.MODID)

    val PAN_CORE_BLOCK_ENTITY: Supplier<BlockEntityType<PANCoreBlockEntity>> = BLOCK_ENTITY_TYPES.register(
        "pan_core",
        Supplier {
            BlockEntityType.Builder.of(
                { pos, state -> PANCoreBlockEntity(pos, state) },
                Blocks.PAN_CORE_BLOCK.get(),
            ).build(null)
        },
    )
    val PAN_ADAPTER_BLOCK_ENTITY: Supplier<BlockEntityType<PANAdapterBlockEntity>> = BLOCK_ENTITY_TYPES.register(
        "pan_adapter",
        Supplier {
            BlockEntityType.Builder.of(
                { pos, state -> PANAdapterBlockEntity(pos, state) },
                Blocks.PAN_ADAPTER_BLOCK.get(),
            ).build(null)
        },
    )
}
