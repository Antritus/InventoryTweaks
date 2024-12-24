package bet.astral.inventorytweaks.mixins;

import bet.astral.inventorytweaks.annotations.IgnoreNoUsagesMixin;
import bet.astral.inventorytweaks.api.CraftingContainer;
import finalforeach.cosmicreach.items.containers.CraftingSlotContainer;
import org.spongepowered.asm.mixin.Mixin;

@IgnoreNoUsagesMixin
@Mixin(CraftingSlotContainer.class)
public class CraftingContainerMixin implements CraftingContainer {
}
