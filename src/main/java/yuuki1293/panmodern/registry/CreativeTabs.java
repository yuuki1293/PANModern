package yuuki1293.panmodern.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredRegister;
import yuuki1293.panmodern.PANModern;

import java.util.function.Supplier;

public class CreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, PANModern.MODID);

    public static final Supplier<CreativeModeTab> PAN_MODERN_TAB = CREATIVE_MODE_TABS.register("panmodern_tab", () -> CreativeModeTab.builder()
        .title(Component.translatable("itemGroup." + PANModern.MODID + ".example"))
        .icon(Blocks.PAN_CORE_BLOCK::toStack)
        .displayItems((params, output) -> {
            output.accept(Blocks.PAN_CORE_BLOCK.get().asItem());
        })
        .build()
    );
}
