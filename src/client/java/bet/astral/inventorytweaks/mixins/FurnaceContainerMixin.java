package bet.astral.inventorytweaks.mixins;

import bet.astral.inventorytweaks.annotations.IgnoreNoUsagesMixin;
import bet.astral.inventorytweaks.api.CraftingContainer;
import finalforeach.cosmicreach.items.containers.FurnaceSlotContainer;
import org.spongepowered.asm.mixin.Mixin;

@IgnoreNoUsagesMixin
@Mixin(FurnaceSlotContainer.class)
public class FurnaceContainerMixin implements CraftingContainer {
}
