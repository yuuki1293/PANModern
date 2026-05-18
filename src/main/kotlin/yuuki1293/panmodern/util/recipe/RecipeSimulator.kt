package yuuki1293.panmodern.util.recipe

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeInput
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.Level

class RecipeSimulator<I : RecipeInput>(
    private val inputAdapter: IRecipeInputAdapter<I>,
    private val assembler: IRecipeAssembler<I>,
) {
    fun simulate(
        level: Level,
        recipeType: RecipeType<out Recipe<out RecipeInput?>?>,
        ingredients: List<ItemStack>,
    ): List<ItemStack> {
        val input = inputAdapter.createInput(ingredients) ?: return emptyList()
        return assembler.assemble(level, recipeType, input)
    }
}
