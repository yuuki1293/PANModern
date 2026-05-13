package yuuki1293.panmodern.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import yuuki1293.panmodern.PANModern;
import yuuki1293.panmodern.registry.Blocks;

public class PANModernBlockStateProvider extends BlockStateProvider {
    public PANModernBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PANModern.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(Blocks.PAN_CORE_BLOCK.get(), cubeAll(Blocks.PAN_CORE_BLOCK.get()));
    }
}
