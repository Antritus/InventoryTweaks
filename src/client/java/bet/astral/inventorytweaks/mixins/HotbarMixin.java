package bet.astral.inventorytweaks.mixins;

import finalforeach.cosmicreach.gamestates.GameState;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.items.Hotbar;
import bet.astral.inventorytweaks.api.ExtendedHotbar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Hotbar.class)
public abstract class HotbarMixin implements ExtendedHotbar {
    public boolean canChangeSlot = true;

    @Override
    public boolean canChangeSlot() {
        return canChangeSlot;
    }

    @Override
    public void canChangeSlot(boolean value) {
        this.canChangeSlot = value;
    }

    public boolean canChangeSlotFixed(){
        return canChangeSlot && (GameState.currentGameState instanceof InGame);
    }

    @Inject(method = "keyDown", at = @At("HEAD"), cancellable = true)
    public void keyDown(int key, CallbackInfoReturnable<Boolean> cir){
        if (!canChangeSlotFixed()){
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "scrolled", at = @At("HEAD"), cancellable = true)
    public void scrolled1(float amountX, float amountY, CallbackInfoReturnable<Boolean> cir){
        if (!canChangeSlotFixed()){
            cir.setReturnValue(false);
        }
    }
}