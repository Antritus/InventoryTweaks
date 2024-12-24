package bet.astral.inventorytweaks.utils;

import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.ui.widgets.ItemSlotWidget;

public class ItemSlotUtils {
    public static void swapItems(ItemSlot itemSlot, ItemSlot otherSlot){
        ItemStack itemStack = itemSlot.itemStack;
        itemSlot.itemStack = otherSlot.itemStack;
        otherSlot.itemStack = itemStack;
    }
    public static void swapItems(ItemSlotWidget itemSlot, ItemSlotWidget otherSlot){
        ItemStack itemStack = itemSlot.getItemSlot().itemStack;
        itemSlot.getItemSlot().itemStack = otherSlot.getItemSlot().itemStack;
        otherSlot.getItemSlot().itemStack = itemStack;
    }
}
