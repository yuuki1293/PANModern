package yuuki1293.panmodern.registry

import net.minecraft.world.level.block.state.BlockBehaviour
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredRegister
import yuuki1293.panmodern.PANModern
import yuuki1293.panmodern.block.PANAdapterBlock
import yuuki1293.panmodern.block.PANCoreBlock
import java.util.function.Supplier
import net.minecraft.world.level.block.Block as MinecraftBlock

object Blocks {
    val BLOCKS: DeferredRegister.Blocks = DeferredRegister.createBlocks(PANModern.MODID)

    val PAN_CORE_BLOCK: DeferredBlock<MinecraftBlock> =
        BLOCKS.register("pan_core", Supplier { PANCoreBlock(BlockBehaviour.Properties.of()) })
    val PAN_ADAPTER_BLOCK: DeferredBlock<MinecraftBlock> =
        BLOCKS.register("pan_adapter", Supplier { PANAdapterBlock(BlockBehaviour.Properties.of()) })
}
