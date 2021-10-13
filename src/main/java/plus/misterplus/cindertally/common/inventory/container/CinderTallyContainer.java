package plus.misterplus.cindertally.common.inventory.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import plus.misterplus.cindertally.common.inventory.CinderTallyInventory;
import plus.misterplus.cindertally.helper.EffectHelper;
import plus.misterplus.cindertally.helper.LifespanHelper;
import plus.misterplus.cindertally.helper.NBTHelper;
import plus.misterplus.cindertally.registry.CinderTallyRegistry;

public class CinderTallyContainer extends Container {

    private IInventory container;
    private int containerRows;

    public CinderTallyContainer(int windowId, PlayerInventory playerInventory, IInventory container) {
        super(CinderTallyRegistry.CONTAINER_CINDER_TALLY, windowId);
        this.container = container;
        this.containerRows = 1;
        int i = (this.containerRows - 4) * 18;

        for(int j = 0; j < this.containerRows; ++j) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(container, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        // Player Inventory
        for(int l = 0; l < 3; ++l) {
            for(int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(playerInventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 161 + i));
        }
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return EffectHelper.isInStasis(player);
    }


}
