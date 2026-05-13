package yuuki1293.panmodern.registry

import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.entity.BlockEntityType
import net.neoforged.neoforge.registries.DeferredRegister
import yuuki1293.panmodern.PANModern
import yuuki1293.panmodern.blockentity.PANCoreBlockEntity
import java.util.function.Supplier

object BlockEntities {
    val BLOCK_ENTITY_TYPES: DeferredRegister<BlockEntityType<*>> =
        DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, PANModern.MODID)

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    val PAN_CORE_BLOCK_ENTITY: Supplier<BlockEntityType<PANCoreBlockEntity>> = BLOCK_ENTITY_TYPES.register(
        "pan_core",
        Supplier {
            BlockEntityType.Builder.of(
                { pos, state -> PANCoreBlockEntity(pos, state) },
                Blocks.PAN_CORE_BLOCK.get(),
            ).build(null as com.mojang.datafixers.types.Type<*>?)
        },
    )
}
