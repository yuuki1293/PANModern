package yuuki1293.panmodern.util

import mekanism.api.recipes.MekanismRecipeTypes
import mekanism.common.recipe.lookup.IRecipeLookupHandler
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeInput
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import yuuki1293.panmodern.util.recipe.CraftingInputAdapter
import yuuki1293.panmodern.util.recipe.RecipeSimulator
import yuuki1293.panmodern.util.recipe.SingleItemInputAdapter
import yuuki1293.panmodern.util.recipe.SingleOutputRecipeAssembler
import yuuki1293.panmodern.util.recipe.TwoItemInputAdapter

object RecipeUtils {
    private val craftingSimulator = RecipeSimulator(CraftingInputAdapter, SingleOutputRecipeAssembler)
    private val singleItemSimulator = RecipeSimulator(SingleItemInputAdapter, SingleOutputRecipeAssembler)
    private val twoItemSimulator = RecipeSimulator(TwoItemInputAdapter, SingleOutputRecipeAssembler)

    fun getRecipeType(block: Block): RecipeType<out Recipe<out RecipeInput?>?>? = when (block) {
        Blocks.CRAFTING_TABLE -> RecipeType.CRAFTING
        Blocks.FURNACE -> RecipeType.SMELTING
        Blocks.SMOKER -> RecipeType.SMOKING
        Blocks.BLAST_FURNACE -> RecipeType.BLASTING
        is IRecipeLookupHandler<*> -> block.recipeType.recipeType // For Mekanism Machine.
        else -> null
    }

    fun getOutputs(
        level: Level,
        recipeType: RecipeType<out Recipe<out RecipeInput?>?>?,
        ingredients: List<ItemStack>,
    ): List<ItemStack> {
        val simulator = when (recipeType) {
            null -> return emptyList()
            RecipeType.CRAFTING -> craftingSimulator
            RecipeType.SMELTING,
            RecipeType.SMOKING,
            RecipeType.BLASTING,
            -> singleItemSimulator

            MekanismRecipeTypes.TYPE_CRUSHING.value(),
            MekanismRecipeTypes.TYPE_ENRICHING.value(),
            MekanismRecipeTypes.TYPE_SMELTING.value(),
            MekanismRecipeTypes.TYPE_SAWING.value(),
            -> singleItemSimulator

            MekanismRecipeTypes.TYPE_COMBINING.value() -> twoItemSimulator
            else -> return emptyList()
        }

        return simulator.simulate(level, recipeType, ingredients)
    }
}
