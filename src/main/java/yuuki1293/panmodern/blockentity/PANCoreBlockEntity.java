package yuuki1293.panmodern.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import com.lowdragmc.lowdraglib2.gui.factory.BlockUIMenuType;
import com.lowdragmc.lowdraglib2.gui.texture.SpriteTexture;
import com.lowdragmc.lowdraglib2.gui.ui.ModularUI;
import com.lowdragmc.lowdraglib2.gui.ui.UI;
import com.lowdragmc.lowdraglib2.gui.ui.UIElement;
import com.lowdragmc.lowdraglib2.gui.ui.elements.Button;
import com.lowdragmc.lowdraglib2.gui.ui.elements.Label;
import com.lowdragmc.lowdraglib2.gui.ui.elements.inventory.InventorySlots;
import com.lowdragmc.lowdraglib2.gui.ui.event.UIEvent;
import com.lowdragmc.lowdraglib2.gui.ui.styletemplate.Sprites;

import yuuki1293.panmodern.registry.BlockEntities;

public class PANCoreBlockEntity extends BlockEntity {

    public PANCoreBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntities.PAN_CORE_BLOCK_ENTITY.get(), pos, blockState);
    }

    public ModularUI createUI(BlockUIMenuType.BlockUIHolder holder) {
        return ModularUI.of(
            UI.of(
                new UIElement().addChildren(
                    new Label().setText("My First UI"),
                    new Button().setText("Click Me!")
                        .setOnServerClick(x -> hoge(x, holder))
                        .setOnClick(x -> hoge(x, holder)),
                    new UIElement().layout(
                        layout -> layout.width(80)
                            .height(80))
                        .style(style -> style.background(SpriteTexture.of("ldlib2:textures/gui/icon.png"))),
                    new InventorySlots().apply(slot -> slot.slotStyle(style -> style.showItemTooltips(true))))
                    .style(style -> style.background(Sprites.BORDER))),
            holder.player);
    }

    private void hoge(UIEvent event, BlockUIMenuType.BlockUIHolder holder) {
        holder.player.addItem(Items.STONE.getDefaultInstance());
    }
}
