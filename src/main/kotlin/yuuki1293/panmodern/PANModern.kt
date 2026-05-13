package yuuki1293.panmodern

import com.mojang.logging.LogUtils
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import org.slf4j.Logger
import yuuki1293.panmodern.datagen.DataGenerators
import yuuki1293.panmodern.registry.BlockEntities
import yuuki1293.panmodern.registry.Blocks
import yuuki1293.panmodern.registry.CreativeTabs
import yuuki1293.panmodern.registry.Items

@Mod(PANModern.MODID)
class PANModern(
    modEventBus: IEventBus,
    @Suppress("UNUSED_PARAMETER") modContainer: ModContainer,
) {
    init {
        Blocks.BLOCKS.register(modEventBus)
        BlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus)
        Items.ITEMS.register(modEventBus)
        CreativeTabs.CREATIVE_MODE_TABS.register(modEventBus)
        modEventBus.addListener(DataGenerators::gatherData)

        LOGGER.debug("{} initialized", MODID)
    }

    companion object {
        const val MODID: String = "panmodern"
        private val LOGGER: Logger = LogUtils.getLogger()
    }
}
