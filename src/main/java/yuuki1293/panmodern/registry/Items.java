package yuuki1293.panmodern.registry;

import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import yuuki1293.panmodern.PANModern;

import java.util.function.Supplier;

public class Items {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PANModern.MODID);

    public static final Supplier<BlockItem> PAN_CORE_ITEM = ITEMS.registerSimpleBlockItem(
        Blocks.PAN_CORE_BLOCK
    );
}
