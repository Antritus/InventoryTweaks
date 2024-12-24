package bet.astral.inventorytweaks.mixins;

import finalforeach.cosmicreach.gamestates.GameState;
import finalforeach.cosmicreach.gamestates.MainMenu;
import finalforeach.cosmicreach.gamestates.PrealphaPreamble;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PrealphaPreamble.class)
public class SkipAlphaScreenMixin {
    @Overwrite
    public void render() {
        GameState.switchToGameState(new MainMenu());
    }
}
