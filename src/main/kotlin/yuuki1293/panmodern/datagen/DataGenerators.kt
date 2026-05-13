package yuuki1293.panmodern.datagen

import net.minecraft.data.DataGenerator
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.data.event.GatherDataEvent

object DataGenerators {
    fun gatherData(event: GatherDataEvent) {
        val generator: DataGenerator = event.generator
        val packOutput: PackOutput = generator.packOutput

        generator.addProvider(event.includeClient(), PANModernLanguageProvider(packOutput))
        generator.addProvider(
            event.includeClient(),
            PANModernBlockStateProvider(packOutput, event.existingFileHelper),
        )
    }
}
