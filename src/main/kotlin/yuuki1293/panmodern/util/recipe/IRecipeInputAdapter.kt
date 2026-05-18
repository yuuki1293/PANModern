package yuuki1293.panmodern.util.recipe

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeInput

interface IRecipeInputAdapter<I : RecipeInput> {
    fun createInput(ingredients: List<ItemStack>): I?
}
