package bet.astral.inventorytweaks.keybind;

import com.badlogic.gdx.Input;
import finalforeach.cosmicreach.settings.Keybind;

public class Controls {
    public static final Keybind HOTBAR_1 = Keybind.fromDefaultKeyNeverMouse("hotbar-slot-1", Input.Keys.NUM_1);
    public static final Keybind HOTBAR_2 = Keybind.fromDefaultKeyNeverMouse("hotbar-slot-2", Input.Keys.NUM_2);
    public static final Keybind HOTBAR_3 = Keybind.fromDefaultKeyNeverMouse("hotbar-slot-3", Input.Keys.NUM_3);
    public static final Keybind HOTBAR_4 = Keybind.fromDefaultKeyNeverMouse("hotbar-slot-4", Input.Keys.NUM_4);
    public static final Keybind HOTBAR_5 = Keybind.fromDefaultKeyNeverMouse("hotbar-slot-5", Input.Keys.NUM_5);
    public static final Keybind HOTBAR_6 = Keybind.fromDefaultKeyNeverMouse("hotbar-slot-6", Input.Keys.NUM_6);
    public static final Keybind HOTBAR_7 = Keybind.fromDefaultKeyNeverMouse("hotbar-slot-7", Input.Keys.NUM_7);
    public static final Keybind HOTBAR_8 = Keybind.fromDefaultKeyNeverMouse("hotbar-slot-8", Input.Keys.NUM_8);
    public static final Keybind HOTBAR_9 = Keybind.fromDefaultKeyNeverMouse("hotbar-slot-9", Input.Keys.NUM_9);
    public static final Keybind HOTBAR_10 = Keybind.fromDefaultKeyNeverMouse("hotbar-slot-10", Input.Keys.NUM_0);
    public static final Keybind[] hotbarButtons = new Keybind[]{
            HOTBAR_1, HOTBAR_2, HOTBAR_3,
            HOTBAR_4, HOTBAR_5, HOTBAR_6,
            HOTBAR_7, HOTBAR_8, HOTBAR_9,
            HOTBAR_10
    };
}
