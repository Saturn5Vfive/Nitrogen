package net.nitrogen.utils;

import net.nitrogen.Nitrogen;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ChatUtils {
    public static void message(String msg){
        Nitrogen.i.player.sendMessage(Text.of(Formatting.AQUA + "[" + Formatting.DARK_AQUA + "Nitrogen" + Formatting.AQUA + "] " + Formatting.RESET + msg), false);
    }
}   
