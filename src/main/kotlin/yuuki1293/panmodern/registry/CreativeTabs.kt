package yuuki1293.panmodern.registry

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.neoforged.neoforge.registries.DeferredRegister
import yuuki1293.panmodern.PANModern
import java.util.function.Supplier

object CreativeTabs {
    const val CREATIVE_MODE_TAB_KEY: String = "itemGroup.${PANModern.MODID}"

    val CREATIVE_MODE_TABS: DeferredRegister<CreativeModeTab> =
        DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, PANModern.MODID)

    val PAN_MODERN_TAB: Supplier<CreativeModeTab> = CREATIVE_MODE_TABS.register(
        "panmodern_tab",
        Supplier {
            CreativeModeTab.builder()
                .title(Component.translatable(CREATIVE_MODE_TAB_KEY))
                .icon { Blocks.PAN_CORE_BLOCK.toStack() }
                .displayItems { _, output ->
                    output.accept(Blocks.PAN_CORE_BLOCK.get().asItem())
                    output.accept(Blocks.PAN_ADAPTER_BLOCK.get().asItem())
                }
                .build()
        },
    )
}
