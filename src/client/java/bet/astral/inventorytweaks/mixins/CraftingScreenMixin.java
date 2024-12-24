package bet.astral.inventorytweaks.mixins;

import bet.astral.inventorytweaks.annotations.IgnoreNoUsagesMixin;
import bet.astral.inventorytweaks.api.CraftingScreen;
import org.spongepowered.asm.mixin.Mixin;

@IgnoreNoUsagesMixin
@Mixin(finalforeach.cosmicreach.items.screens.CraftingScreen.class)
public class CraftingScreenMixin implements CraftingScreen {
}
