package yuuki1293.panmodern.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class DataGenerators {

    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(event.includeClient(), new PANModernLanguageProvider(packOutput));
        generator.addProvider(
            event.includeClient(),
            new PANModernBlockStateProvider(packOutput, event.getExistingFileHelper()));
    }
}
