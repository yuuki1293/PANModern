package yuuki1293.panmodern.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import yuuki1293.panmodern.PANModern;
import yuuki1293.panmodern.registry.Blocks;
import yuuki1293.panmodern.registry.CreativeTabs;

public class PANModernLanguageProvider extends LanguageProvider {
    public PANModernLanguageProvider(PackOutput output) {
        super(output, PANModern.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(Blocks.PAN_CORE_BLOCK.get(), "PAN Core");
        add(CreativeTabs.CREATIVE_MODE_TAB_KEY, "PAN Modern");
    }
}
