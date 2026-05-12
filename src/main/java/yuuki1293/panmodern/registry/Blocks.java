package yuuki1293.panmodern.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import yuuki1293.panmodern.PANModern;
import yuuki1293.panmodern.block.PANCoreBlock;

public class Blocks {
    public static final DeferredRegister.Blocks BLOCKS =
        DeferredRegister.createBlocks(PANModern.MODID);

    public static final DeferredBlock<Block> PAN_CORE_BLOCK =
        BLOCKS.register("pan_core", () -> new PANCoreBlock(BlockBehaviour.Properties.of()));
}
