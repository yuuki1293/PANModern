package yuuki1293.panmodern.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import yuuki1293.panmodern.registry.BlockEntities;

public class PANCoreBlockEntity extends BlockEntity {
    public PANCoreBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntities.PAN_CORE_BLOCK_ENTITY.get(), pos, blockState);
    }


}
