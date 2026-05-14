package yuuki1293.panmodern.blockentity

import com.lowdragmc.lowdraglib2.gui.factory.BlockUIMenuType
import com.lowdragmc.lowdraglib2.gui.slot.ItemHandlerSlot
import com.lowdragmc.lowdraglib2.gui.ui.ModularUI
import com.lowdragmc.lowdraglib2.gui.ui.UI
import com.lowdragmc.lowdraglib2.gui.ui.data.ScrollDisplay
import com.lowdragmc.lowdraglib2.gui.ui.data.ScrollerMode
import com.lowdragmc.lowdraglib2.gui.ui.element
import com.lowdragmc.lowdraglib2.gui.ui.elements.itemSlot
import com.lowdragmc.lowdraglib2.gui.ui.elements.label
import com.lowdragmc.lowdraglib2.gui.ui.elements.scrollerView
import com.lowdragmc.lowdraglib2.gui.ui.elements.withViewContainer
import com.lowdragmc.lowdraglib2.gui.ui.inventorySlots
import com.lowdragmc.lowdraglib2.gui.ui.layout.auto
import com.lowdragmc.lowdraglib2.gui.ui.layout.px
import com.lowdragmc.lowdraglib2.gui.ui.style.StylesheetManager
import com.lowdragmc.lowdraglib2.gui.ui.styletemplate.MCSprites
import dev.vfyjxf.taffy.style.AlignItems
import dev.vfyjxf.taffy.style.FlexDirection
import dev.vfyjxf.taffy.style.FlexWrap
import dev.vfyjxf.taffy.style.TaffyDisplay
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.neoforge.items.ItemStackHandler
import yuuki1293.panmodern.PANModern
import yuuki1293.panmodern.registry.BlockEntities
import yuuki1293.panmodern.registry.Blocks

class PANCoreBlockEntity(
    pos: BlockPos,
    blockState: BlockState,
) : BlockEntity(BlockEntities.PAN_CORE_BLOCK_ENTITY.get(), pos, blockState) {
    companion object {
        const val ITEM_SLOT_SIZE: Float = 18f
        const val ITEM_LIST_ROW: Int = 10
        const val ITEM_LIST_COLUMN: Int = 9
    }

    val dummyItemHandler = ItemStackHandler(100)

    fun createUI(holder: BlockUIMenuType.BlockUIHolder): ModularUI {
        val root = element({
            layout = {
                margin { all(5.px) }
                padding { all(5.px) }
            }

            style = {
                background(MCSprites.BORDER)
            }
        }) {
            label({ text = Blocks.PAN_CORE_BLOCK.get().name })

            scrollerView({
                scrollerViewStyle = {
                    mode(ScrollerMode.VERTICAL)
                    verticalScrollDisplay(ScrollDisplay.ALWAYS)
                }
            }) {
                withViewContainer {
                    layout.flexDirection(FlexDirection.ROW)
                    layout.wrap(FlexWrap.WRAP)
                    layoutStyle.height(ITEM_LIST_ROW * ITEM_SLOT_SIZE)
                    layoutStyle.width(ITEM_LIST_COLUMN * ITEM_SLOT_SIZE)
                }

                repeat(100) {
                    itemSlot({
                        bind(ItemHandlerSlot(dummyItemHandler, it).setCanPlace { false }.setCanTake { false })
                        dummyItemHandler.setStackInSlot(it, BuiltInRegistries.ITEM.byId(it + 1).defaultInstance)
                    })
                }
            }

            inventorySlots({
                layout = {
                    alignSelf(AlignItems.CENTER)
                }

                style = {
                    tooltips()
                }
            })
        }

        val stylesheet = StylesheetManager.INSTANCE
            .getStylesheetSafe(ResourceLocation.fromNamespaceAndPath(PANModern.MODID, "lss/pan_core.lss"))
        val ui = UI.of(root, stylesheet)
        return ModularUI.of(ui, holder.player)
    }
}
