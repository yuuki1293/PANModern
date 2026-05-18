package yuuki1293.panmodern.blockentity

import com.lowdragmc.lowdraglib2.gui.factory.BlockUIMenuType
import com.lowdragmc.lowdraglib2.gui.sync.bindings.impl.DataBindingBuilder
import com.lowdragmc.lowdraglib2.gui.ui.ModularUI
import com.lowdragmc.lowdraglib2.gui.ui.UI
import com.lowdragmc.lowdraglib2.gui.ui.data.ScrollDisplay
import com.lowdragmc.lowdraglib2.gui.ui.data.ScrollerMode
import com.lowdragmc.lowdraglib2.gui.ui.element
import com.lowdragmc.lowdraglib2.gui.ui.elements.asXeiPhantom
import com.lowdragmc.lowdraglib2.gui.ui.elements.asXeiRecipeIngredient
import com.lowdragmc.lowdraglib2.gui.ui.elements.itemSlot
import com.lowdragmc.lowdraglib2.gui.ui.elements.label
import com.lowdragmc.lowdraglib2.gui.ui.elements.scrollerView
import com.lowdragmc.lowdraglib2.gui.ui.elements.withViewContainer
import com.lowdragmc.lowdraglib2.gui.ui.event.UIEvents
import com.lowdragmc.lowdraglib2.gui.ui.inventorySlots
import com.lowdragmc.lowdraglib2.gui.ui.layout.px
import com.lowdragmc.lowdraglib2.gui.ui.style.StylesheetManager
import com.lowdragmc.lowdraglib2.gui.ui.styletemplate.MCSprites
import com.lowdragmc.lowdraglib2.integration.xei.IngredientIO
import dev.vfyjxf.taffy.style.AlignContent
import dev.vfyjxf.taffy.style.AlignItems
import dev.vfyjxf.taffy.style.FlexDirection
import dev.vfyjxf.taffy.style.FlexWrap
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeInput
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.neoforge.items.ItemStackHandler
import yuuki1293.panmodern.PANModern
import yuuki1293.panmodern.registry.BlockEntities
import yuuki1293.panmodern.registry.Blocks
import yuuki1293.panmodern.util.RecipeUtils

class PANAdapterBlockEntity(
    pos: BlockPos,
    blockState: BlockState,
) : BlockEntity(BlockEntities.PAN_ADAPTER_BLOCK_ENTITY.get(), pos, blockState) {
    companion object {
        const val ITEM_SLOT_SIZE: Float = 18f
        const val ITEM_LIST_ROW: Int = 3
        const val ITEM_LIST_COLUMN: Int = 3
    }

    val ingredientsItemHandler = object : ItemStackHandler(81) {
        override fun onContentsChanged(slot: Int) {
            super.onContentsChanged(slot)
            refreshOutput()
        }
    }
    val resultItemHandler = ItemStackHandler(81)
    var recipeType: RecipeType<out Recipe<out RecipeInput?>?>? = null

    override fun onLoad() {
        super.onLoad()
        refreshRecipeType()
    }

    fun updateRecipeType() {
        if (refreshRecipeType()) {
            super.setChanged()
        }
    }

    private fun refreshRecipeType(): Boolean {
        val level = level
        if (level == null) {
            refreshOutput()
            return false
        }

        val updatedRecipeType = Direction.entries.firstNotNullOfOrNull { direction ->
            val adjacentBlock = level
                .getBlockState(blockPos.relative(direction))
                .block
            RecipeUtils.getRecipeType(adjacentBlock)
        }

        if (recipeType == updatedRecipeType) {
            refreshOutput()
            return false
        }

        recipeType = updatedRecipeType
        refreshOutput()
        return true
    }

    private fun refreshOutput() {
        clearResultItems()

        val level = level ?: return
        val recipeType = recipeType ?: return
        RecipeUtils.getOutputs(level, recipeType, ingredientsItemHandler.toItemStacks())
            .take(resultItemHandler.slots)
            .forEachIndexed { slot, output ->
                resultItemHandler.setStackInSlot(slot, output)
            }
    }

    private fun ItemStackHandler.toItemStacks(): List<ItemStack> {
        return List(slots) { slot ->
            getStackInSlot(slot)
        }
    }

    private fun clearResultItems() {
        repeat(resultItemHandler.slots) { slot ->
            resultItemHandler.setStackInSlot(slot, ItemStack.EMPTY)
        }
    }

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
            label({ text = Blocks.PAN_ADAPTER_BLOCK.get().name })

            element({
                layout = {
                    flexDirection(FlexDirection.ROW)
                    alignContent(AlignContent.CENTER)
                    justifyContent(AlignContent.CENTER)
                }
            }) {
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

                    repeat(81) {
                        itemSlot {
                            asXeiPhantom()
                            asXeiRecipeIngredient(IngredientIO.INPUT)

                            bind(
                                DataBindingBuilder.itemStack(
                                    { ingredientsItemHandler.getStackInSlot(it) },
                                    { itemStack -> ingredientsItemHandler.setStackInSlot(it, itemStack) },
                                ).build(),
                            )

                            events { slot ->
                                UIEvents.CLICK += {
                                    val carried = slot.modularUI?.menu?.carried ?: ItemStack.EMPTY
                                    slot.setItem(carried.copy())
                                }
                            }
                        }
                    }
                }

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

                    repeat(81) {
                        itemSlot {
                            bind(
                                DataBindingBuilder.itemStack(
                                    { resultItemHandler.getStackInSlot(it) },
                                    {},
                                ).build(),
                            )
                        }
                    }
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
