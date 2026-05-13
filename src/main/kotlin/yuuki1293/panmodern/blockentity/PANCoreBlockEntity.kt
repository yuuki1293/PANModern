package yuuki1293.panmodern.blockentity

import com.lowdragmc.lowdraglib2.gui.factory.BlockUIMenuType
import com.lowdragmc.lowdraglib2.gui.ui.ModularUI
import com.lowdragmc.lowdraglib2.gui.ui.UI
import com.lowdragmc.lowdraglib2.gui.ui.UIElement
import com.lowdragmc.lowdraglib2.gui.ui.elements.ItemSlot
import com.lowdragmc.lowdraglib2.gui.ui.elements.Label
import com.lowdragmc.lowdraglib2.gui.ui.elements.ScrollerView
import com.lowdragmc.lowdraglib2.gui.ui.elements.inventory.InventorySlots
import com.lowdragmc.lowdraglib2.gui.ui.style.StylesheetManager
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import yuuki1293.panmodern.PANModern
import yuuki1293.panmodern.registry.BlockEntities
import yuuki1293.panmodern.registry.Blocks
import net.minecraft.world.item.Items as MinecraftItems

class PANCoreBlockEntity(
    pos: BlockPos,
    blockState: BlockState,
) : BlockEntity(BlockEntities.PAN_CORE_BLOCK_ENTITY.get(), pos, blockState) {
    fun createUI(holder: BlockUIMenuType.BlockUIHolder): ModularUI {
        val itemListElement = ScrollerView()
        itemListElement.addClass("pan-core-item-list")

        repeat(100) {
            itemListElement.addScrollViewChild(ItemSlot().setItem(MinecraftItems.ACACIA_BOAT.defaultInstance))
        }

        val root = UIElement()
            .addChildren(
                Label().setText(Blocks.PAN_CORE_BLOCK.get().getName()),
                itemListElement,
                InventorySlots().addClass("pan-core-player-inventory"),
            )
            .addClass("pan-core-root")

        val stylesheet = StylesheetManager.INSTANCE
            .getStylesheetSafe(ResourceLocation.fromNamespaceAndPath(PANModern.MODID, "lss/pan_core.lss"))
        val ui = UI.of(root, stylesheet)
        return ModularUI.of(ui, holder.player)
    }
}
