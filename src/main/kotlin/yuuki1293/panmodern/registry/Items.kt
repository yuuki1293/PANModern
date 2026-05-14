package yuuki1293.panmodern.registry

import net.minecraft.world.item.BlockItem
import net.neoforged.neoforge.registries.DeferredRegister
import yuuki1293.panmodern.PANModern
import java.util.function.Supplier

object Items {
    val ITEMS: DeferredRegister.Items = DeferredRegister.createItems(PANModern.MODID)

    val PAN_CORE_ITEM: Supplier<BlockItem> = ITEMS.registerSimpleBlockItem(Blocks.PAN_CORE_BLOCK)
    val PAN_ADAPTER_ITEM: Supplier<BlockItem> = ITEMS.registerSimpleBlockItem(Blocks.PAN_ADAPTER_BLOCK)
}
