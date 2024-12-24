package bet.astral.inventorytweaks.mixins;

import bet.astral.inventorytweaks.annotations.IgnoreNoUsagesMixin;
import bet.astral.inventorytweaks.api.CraftingScreen;
import finalforeach.cosmicreach.items.screens.FurnaceScreen;
import org.spongepowered.asm.mixin.Mixin;

@IgnoreNoUsagesMixin
@Mixin(FurnaceScreen.class)
public class FurnaceScreenMixin implements CraftingScreen {
}
