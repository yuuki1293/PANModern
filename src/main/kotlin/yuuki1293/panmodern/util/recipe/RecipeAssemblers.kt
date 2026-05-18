package yuuki1293.panmodern.util.recipe

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeInput
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.Level

object SingleOutputRecipeAssembler : IRecipeAssembler<RecipeInput> {
    override fun assemble(
        level: Level,
        recipeType: RecipeType<out Recipe<out RecipeInput?>?>,
        input: RecipeInput,
    ): List<ItemStack> {
        val output = assembleOutput(level, recipeType, input)
        return output
            .takeUnless { it.isEmpty }
            ?.let(::listOf)
            ?: emptyList()
    }

    @Suppress("UNCHECKED_CAST")
    private fun assembleOutput(
        level: Level,
        recipeType: RecipeType<out Recipe<out RecipeInput?>?>,
        input: RecipeInput,
    ): ItemStack {
        return level.recipeManager
            .getRecipeFor(recipeType as RecipeType<Recipe<RecipeInput>>, input, level)
            .map { recipeHolder ->
                recipeHolder.value().assemble(input, level.registryAccess())
            }
            .orElse(ItemStack.EMPTY)
    }
}
