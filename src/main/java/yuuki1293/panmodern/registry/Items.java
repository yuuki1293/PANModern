package yuuki1293.panmodern.registry;

import java.util.function.Supplier;

import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import yuuki1293.panmodern.PANModern;

public class Items {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PANModern.MODID);

    public static final Supplier<BlockItem> PAN_CORE_ITEM = ITEMS.registerSimpleBlockItem(Blocks.PAN_CORE_BLOCK);
}
