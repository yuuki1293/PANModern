package yuuki1293.panmodern.blockentity

import com.lowdragmc.lowdraglib2.gui.factory.BlockUIMenuType
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
import com.lowdragmc.lowdraglib2.gui.ui.layout.px
import com.lowdragmc.lowdraglib2.gui.ui.style.StylesheetManager
import com.lowdragmc.lowdraglib2.gui.ui.styletemplate.MCSprites
import dev.vfyjxf.taffy.style.FlexDirection
import dev.vfyjxf.taffy.style.FlexWrap
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import yuuki1293.panmodern.PANModern
import yuuki1293.panmodern.registry.BlockEntities
import yuuki1293.panmodern.registry.Blocks
import yuuki1293.panmodern.registry.Items

const val ITEM_SLOT_SIZE: Int = 18
const val ITEM_LIST_ROW: Int = 10
const val ITEM_LIST_COLUMN: Int = 9

class PANCoreBlockEntity(
    pos: BlockPos,
    blockState: BlockState,
) : BlockEntity(BlockEntities.PAN_CORE_BLOCK_ENTITY.get(), pos, blockState) {

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
                layout = {
                    height(ITEM_LIST_ROW * ITEM_SLOT_SIZE)
                    width(ITEM_LIST_COLUMN * ITEM_SLOT_SIZE)
                }

                scrollerViewStyle = {
                    mode(ScrollerMode.VERTICAL)
                    verticalScrollDisplay(ScrollDisplay.ALWAYS)
                }
            }) {
                withViewContainer {
                    layout.flexDirection(FlexDirection.ROW)
                    layout.wrap(FlexWrap.WRAP)
                }

                repeat(100) {
                    itemSlot({
                        item = Items.PAN_CORE_ITEM.get().defaultInstance
                    })
                }
            }

            inventorySlots({
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
