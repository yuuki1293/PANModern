package yuuki1293.panmodern.blockentity;

import com.lowdragmc.lowdraglib2.gui.factory.BlockUIMenuType;
import com.lowdragmc.lowdraglib2.gui.texture.SpriteTexture;
import com.lowdragmc.lowdraglib2.gui.ui.ModularUI;
import com.lowdragmc.lowdraglib2.gui.ui.UI;
import com.lowdragmc.lowdraglib2.gui.ui.UIElement;
import com.lowdragmc.lowdraglib2.gui.ui.elements.Button;
import com.lowdragmc.lowdraglib2.gui.ui.elements.Label;
import com.lowdragmc.lowdraglib2.gui.ui.event.UIEvent;
import com.lowdragmc.lowdraglib2.gui.ui.styletemplate.Sprites;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import yuuki1293.panmodern.registry.BlockEntities;

public class PANCoreBlockEntity extends BlockEntity {
    public PANCoreBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntities.PAN_CORE_BLOCK_ENTITY.get(), pos, blockState);
    }

    public ModularUI createUI(BlockUIMenuType.BlockUIHolder holder) {
        return ModularUI.of(UI.of(
            new UIElement().addChildren(
                // add a label to display text
                new Label().setText("My First UI"),
                // add a button with text
                new Button().setText("Click Me!").setOnClick(x -> hoge(x, holder)),
                // add an element to display an image based on a resource location
                new UIElement().layout(layout -> layout.width(80).height(80))
                    .style(style -> style.background(
                        SpriteTexture.of("ldlib2:textures/gui/icon.png"))
                    )
            ).style(style -> style.background(Sprites.BORDER)) // set a background for the root element
            // create a UI
        ), holder.player);
    }

    private void hoge(UIEvent event, BlockUIMenuType.BlockUIHolder holder) {
        holder.player.addItem(Items.STONE.getDefaultInstance());
    }
}
