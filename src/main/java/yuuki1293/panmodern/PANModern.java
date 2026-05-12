package yuuki1293.panmodern;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(PANModern.MODID)
public class PANModern {
    public static final String MODID = "panmodern";
    private static final Logger LOGGER = LogUtils.getLogger();

    public PANModern(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.debug("{} initialized", MODID);
    }
}
