package yuuki1293.panmodern.block

import com.lowdragmc.lowdraglib2.gui.factory.BlockUIMenuType
import com.lowdragmc.lowdraglib2.gui.ui.ModularUI
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.EntityBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import yuuki1293.panmodern.blockentity.PANAdapterBlockEntity

class PANAdapterBlock(properties: Properties) :
    Block(properties),
    EntityBlock,
    BlockUIMenuType.BlockUI {

    override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity =
        PANAdapterBlockEntity(blockPos, blockState)

    override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hit: BlockHitResult,
    ): InteractionResult {
        if (!level.isClientSide) {
            BlockUIMenuType.openUI(player as ServerPlayer, pos)
        }
        return InteractionResult.SUCCESS
    }

    override fun neighborChanged(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        neighborBlock: Block,
        neighborPos: BlockPos,
        movedByPiston: Boolean,
    ) {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston)
        (level.getBlockEntity(pos) as? PANAdapterBlockEntity)?.updateRecipeType()
    }

    override fun createUI(holder: BlockUIMenuType.BlockUIHolder): ModularUI? {
        val blockEntity = holder.player
            .level()
            .getBlockEntity(holder.pos)

        return if (blockEntity is PANAdapterBlockEntity) {
            blockEntity.createUI(holder)
        } else {
            null
        }
    }
}
