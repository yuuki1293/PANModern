package yuuki1293.panmodern.util.recipe

import net.minecraft.core.NonNullList
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.CraftingInput
import net.minecraft.world.item.crafting.RecipeInput
import net.minecraft.world.item.crafting.SingleRecipeInput

object CraftingInputAdapter : IRecipeInputAdapter<CraftingInput> {
    override fun createInput(ingredients: List<ItemStack>): CraftingInput =
        CraftingInput.of(9, 9, ingredients.toNonNullList(81))
}

object SingleItemInputAdapter : IRecipeInputAdapter<SingleRecipeInput> {
    override fun createInput(ingredients: List<ItemStack>): SingleRecipeInput? =
        ingredients.firstNotNullOfOrNull { itemStack ->
            itemStack.takeUnless { it.isEmpty }?.let(::SingleRecipeInput)
        }
}

object TwoItemInputAdapter : IRecipeInputAdapter<RecipeInput> {
    override fun createInput(ingredients: List<ItemStack>): RecipeInput? {
        val inputs = ingredients.filterNot { it.isEmpty }.take(2)
        if (inputs.size < 2) {
            return null
        }
        return ItemStackListRecipeInput(inputs)
    }
}

private fun List<ItemStack>.toNonNullList(size: Int): NonNullList<ItemStack> {
    val items = NonNullList.withSize(size, ItemStack.EMPTY)
    take(size).forEachIndexed { index, itemStack ->
        items[index] = itemStack
    }
    return items
}

private class ItemStackListRecipeInput(private val itemStacks: List<ItemStack>) : RecipeInput {
    override fun getItem(index: Int): ItemStack = itemStacks[index]

    override fun size(): Int = itemStacks.size
}
