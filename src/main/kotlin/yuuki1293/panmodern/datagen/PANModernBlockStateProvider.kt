package yuuki1293.panmodern.datagen

import net.minecraft.data.PackOutput
import net.neoforged.neoforge.client.model.generators.BlockStateProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import yuuki1293.panmodern.PANModern
import yuuki1293.panmodern.registry.Blocks

class PANModernBlockStateProvider(
    output: PackOutput,
    existingFileHelper: ExistingFileHelper,
) : BlockStateProvider(output, PANModern.MODID, existingFileHelper) {
    override fun registerStatesAndModels() {
        simpleBlockWithItem(Blocks.PAN_CORE_BLOCK.get(), cubeAll(Blocks.PAN_CORE_BLOCK.get()))
        simpleBlockWithItem(Blocks.PAN_ADAPTER_BLOCK.get(), cubeAll(Blocks.PAN_ADAPTER_BLOCK.get()))
    }
}
