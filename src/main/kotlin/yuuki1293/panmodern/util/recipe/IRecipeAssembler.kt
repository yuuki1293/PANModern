package yuuki1293.panmodern.util.recipe

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeInput
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.Level

interface IRecipeAssembler<in I : RecipeInput> {
    fun assemble(level: Level, recipeType: RecipeType<out Recipe<out RecipeInput?>?>, input: I): List<ItemStack>
}
