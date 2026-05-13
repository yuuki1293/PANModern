package yuuki1293.panmodern.blockentity;

import com.lowdragmc.lowdraglib2.gui.ui.elements.ItemSlot;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import com.lowdragmc.lowdraglib2.gui.factory.BlockUIMenuType;
import com.lowdragmc.lowdraglib2.gui.ui.ModularUI;
import com.lowdragmc.lowdraglib2.gui.ui.UI;
import com.lowdragmc.lowdraglib2.gui.ui.UIElement;
import com.lowdragmc.lowdraglib2.gui.ui.elements.Label;
import com.lowdragmc.lowdraglib2.gui.ui.elements.ScrollerView;
import com.lowdragmc.lowdraglib2.gui.ui.elements.inventory.InventorySlots;
import com.lowdragmc.lowdraglib2.gui.ui.style.StylesheetManager;

import yuuki1293.panmodern.PANModern;
import yuuki1293.panmodern.registry.BlockEntities;
import yuuki1293.panmodern.registry.Blocks;

public class PANCoreBlockEntity extends BlockEntity {
    public PANCoreBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntities.PAN_CORE_BLOCK_ENTITY.get(), pos, blockState);
    }

    public ModularUI createUI(BlockUIMenuType.BlockUIHolder holder) {
        var itemListElement = new ScrollerView();
        itemListElement.addClass("pan-core-item-list");

        for (int i = 0; i < 100; i++){
            itemListElement.addScrollViewChild(new ItemSlot().setItem(Items.ACACIA_BOAT.getDefaultInstance()));
        }

        var root = new UIElement().addChildren(
            new Label().setText(
                Blocks.PAN_CORE_BLOCK.get()
                    .getName()),
            itemListElement,
            new InventorySlots().addClass("pan-core-player-inventory"))
            .addClass("pan-core-root");

        var stylesheet = StylesheetManager.INSTANCE
            .getStylesheetSafe(ResourceLocation.fromNamespaceAndPath(PANModern.MODID, "lss/pan_core.lss"));
        var ui = UI.of(root, stylesheet);
        return ModularUI.of(ui, holder.player);
    }
}
