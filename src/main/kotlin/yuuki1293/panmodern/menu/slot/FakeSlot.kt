package yuuki1293.panmodern.menu.slot

import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.items.ItemStackHandler

class FakeSlot(val itemHandler: ItemStackHandler, val invSlot: Int) : Slot(EMPTY_INVENTORY, invSlot, 0, 0) {
    companion object {
        val EMPTY_INVENTORY = SimpleContainer(0)
    }

    override fun onTake(player: Player, item: ItemStack) {
    }

    override fun remove(amount: Int): ItemStack = ItemStack.EMPTY

    override fun mayPlace(stack: ItemStack): Boolean = false

    override fun getItem(): ItemStack = itemHandler.getStackInSlot(invSlot)

    override fun set(stack: ItemStack) {
        itemHandler.setStackInSlot(invSlot, stack.copy())
    }

    override fun mayPickup(player: Player): Boolean = false

    override fun isFake(): Boolean = true
}
