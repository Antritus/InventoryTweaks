package bet.astral.inventorytweaks.mixins;

import finalforeach.cosmicreach.items.screens.BaseItemScreen;
import finalforeach.cosmicreach.ui.UI;
import bet.astral.inventorytweaks.api.ExtendedHotbar;
import finalforeach.cosmicreach.ui.widgets.ItemSlotWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BaseItemScreen.class)
public abstract class BaseItemScreenMixin{
    @Shadow protected ItemSlotWidget[] slotWidgets;

    @Inject(method = "onShow", at = @At("HEAD"))
    public void onShow1(CallbackInfo ci){
        // Change hotbar values to deny swapping slots
        if (UI.hotbar != null){
            ExtendedHotbar extendedHotbar = (ExtendedHotbar) UI.hotbar;
            extendedHotbar.canChangeSlot(false);
        }
    }

    @Inject(method = "onHide", at = @At("HEAD"))
    public void onHide1(CallbackInfo ci) {
        // Change hotbar values to allow swapping slots
        if (UI.hotbar != null){
            ExtendedHotbar extendedHotbar = (ExtendedHotbar) UI.hotbar;
            extendedHotbar.canChangeSlot(true);
        }

        if (slotWidgets != null){
            for (ItemSlotWidget slotWidget : slotWidgets) {
                slotWidget.hoveredOver = false;
            }
        }
    }
}