package bet.astral.inventorytweaks.mixins;

import bet.astral.inventorytweaks.api.CraftingContainer;
import bet.astral.inventorytweaks.keybind.Controls;
import bet.astral.inventorytweaks.utils.ItemSlotUtils;
import finalforeach.cosmicreach.ClientSingletons;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.items.containers.SlotContainer;
import finalforeach.cosmicreach.networking.GamePacket;
import finalforeach.cosmicreach.networking.client.ClientNetworkManager;
import finalforeach.cosmicreach.networking.packets.ContainerSyncPacket;
import finalforeach.cosmicreach.settings.ControlSettings;
import finalforeach.cosmicreach.settings.Keybind;
import finalforeach.cosmicreach.ui.widgets.ItemSlotWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

@Mixin(ItemSlotWidget.class)
public abstract class ItemSlotWidgetMixin {
    @Shadow
    public abstract ItemSlot getItemSlot();

    @Shadow
    protected abstract boolean isHoveredOver();

    @Inject(method = "act", at = @At(value = "INVOKE", target = "Lcom/badlogic/gdx/scenes/scene2d/ui/Stack;act(F)V"))
    private void act(float delta, CallbackInfo ci) {
        ItemSlot itemSlot = this.getItemSlot();
        // Ignore if action was dealt already
        boolean isDropped = ControlSettings.keyDropItem.isJustPressed();
        if (isDropped) {
            return;
        }

        boolean isHovered = this.isHoveredOver();
        for (int i = 0; i < 10; i++) {
            Keybind keybind = Controls.hotbarButtons[i];
            // Check if hotbar button was pressed
            if (!(isHovered && keybind.isJustPressed())) {
                continue;
            }

            ItemSlot otherSlot = ClientSingletons.get().getLocalPlayer().inventory.getSlot(i);

            // Make sure network is there
            if (!(ClientNetworkManager.isConnected())) {
                handleSwap(itemSlot, otherSlot, (slot, other) -> {
                    otherSlot.onItemSlotUpdate();
                    itemSlot.onItemSlotUpdate();
                });
            } else {
                // I hate this it's so messy
                handleSwap(itemSlot, otherSlot, (slot, other) -> {
                    otherSlot.onItemSlotUpdate();
                    itemSlot.onItemSlotUpdate();

                    // Get slots
                    List<GamePacket> packets = new LinkedList<>();
                    packets.add(new ContainerSyncPacket(0, ClientSingletons.get().getLocalPlayer().inventory));

                    // Check and add correct packets coresponding to the containers
                    SlotContainer container = slot.getContainer();
                    SlotContainer container2 = other.getContainer();

                    if (container instanceof CraftingContainer) {
                        packets.add(new ContainerSyncPacket(1, container));
                    } else if (container2 instanceof CraftingContainer) {
                        packets.add(new ContainerSyncPacket(1, container2));
                    }

                    // Send packets
                    for (GamePacket packet : packets) {
                        ClientNetworkManager.sendAsClient(packet);
                    }
                });
            }
            return;
        }
    }

    private void handleSwap(ItemSlot itemSlot, ItemSlot otherSlot, BiConsumer<ItemSlot, ItemSlot> updateAction) {
        // Both items are null -> Ignore
        if (otherSlot.itemStack == null && itemSlot.itemStack == null) {
            // Return, because to skip checking other the other keybindings
            return;
        }
        // No point trying to swap the items if the other slot is return
        if (otherSlot.isOutputOnly()) {
            return;
            // If slot is output slot -> merge
        } else if (itemSlot.isOutputOnly()) {
            itemSlot.mergeInto(otherSlot);
            // Else swap the items
        } else {
            ItemSlotUtils.swapItems(itemSlot, otherSlot);
        }
        // Update client and server
        updateAction.accept(itemSlot, otherSlot);

        /*

        // Swap items if possible, null item -> easy swap
        if ((otherSlot.itemStack == null && !itemSlot.isOutputOnly())
                || itemSlot.itemStack == null) {
            // Swap items
            ItemStack saved = itemSlot.itemStack;
            itemSlot.itemStack = otherSlot.itemStack;
            otherSlot.itemStack = saved;

            // Update slots for sync
            updateAction.accept(itemSlot, otherSlot);
        } else if (otherSlot.itemStack == null) {
        } else if (otherSlot.itemStack != null) {
            // Make sure items can be properly merged
            final ItemStack itemHovering = itemSlot.itemStack;
            final ItemStack itemOther = otherSlot.itemStack;

            // Properly function item slots
            if (itemSlot.isOutputOnly()) {
                // If over, or equals the stack limit ignore
                if (itemOther.amount >= itemOther.stackLimit) {
                    return;
                }

                // Return if can't merge with curent item
                if (!itemHovering.getItem().canMergeWith(itemOther.getItem())) {
                    return;
                }

                // Make sure maximum amounts are not
                final int otherAmount = itemOther.amount;
                final int hoverAmount = itemHovering.amount;
                int total = otherAmount + hoverAmount;
                // Stack limit exceeds, change stack amounts
                if (total > itemOther.stackLimit) {
                    int subtract = total - itemOther.stackLimit;
                    itemOther.amount = itemOther.stackLimit;
                    itemHovering.amount = subtract;
                } else {
                    // Make the other slot have higher item count, make output null
                    itemOther.amount = total;
                    itemSlot.itemStack = null;
                }
            } else {
                // Just swap normally
                ItemStack save = itemSlot.itemStack;
                itemSlot.itemStack = otherSlot.itemStack;
                otherSlot.itemStack = save;
            }

            // Update slots
            itemSlot.onItemSlotUpdate();
            otherSlot.onItemSlotUpdate();
        }
         */

    }
}
