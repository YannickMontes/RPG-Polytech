/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools • Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Controllers;

/**
 *
 * @author coren
 */
public abstract class ConsoleDesign {

    public static String BOLD = "\u001B[1m";
    public static String REDBACK = "\033[41m";
    public static String RED = "\033[31m";
    public static String GREENBACK = "\033[42m";
    public static String GREEN = "\033[32m";
    public static String YELLOWBACK = "\033[43m";
    public static String YELLOW = "\033[33m";
    public static String BLUEBACK = "\033[44m";
    public static String BLUE = "\033[34m";
    public static String MAGENTABACK = "\033[45m";
    public static String MAGENTA= "\033[35m";
    public static String CYANBACK = "\033[46m";
    public static String CYAN = "\033[36m";
    public static String WHITEBACK = "\033[47m";
    public static String WHITE = "\033[1;37m";
    public static String RESET = "\033[0m";

    public static String textBox(String text, String color) {
        String textBox = "";
        if (color != null) {
            textBox += color;
        }
        for (int i = 0; i < text.length() + 4; i++) {
            textBox += "-";
        }
        textBox += "\n";
        textBox += color;
        textBox += "| " + text + " |";
        textBox += "\n";
        textBox += color;
        for (int i = 0; i < text.length() + 4; i++) {
            textBox += "-";
        }
        textBox += RESET;
        return textBox;
    }

    public static String textBox(String text, String color, String color2) {
        String textBox = "";
        if (color != null) {
            textBox += color + color2;
        }
        for (int i = 0; i < text.length() + 4; i++) {
            textBox += "-";
        }
        textBox += "\n";
        textBox += color + color2;
        textBox += "| " + text + " |";
        textBox += "\n";
        textBox += color + color2;
        for (int i = 0; i < text.length() + 4; i++) {
            textBox += "-";
        }
        textBox += RESET;
        return textBox;
    }

    public static String textDashArrow(String text, String color) {
        String textBox = "";
        textBox += color;
        textBox += "--> " + text + " <--";
        textBox += RESET;
        return textBox;
    }

    public static String textDash(String text, String color) {
        String textBox = "";
        textBox += color;
        textBox += "--- " + text + " ---";
        textBox += RESET;
        return textBox;
    }

    public static String text(String text, String color) {
        String textBox = "";
        textBox += color;
        textBox += text;
        textBox += RESET;
        return textBox;
    }

    public static String RPG(String color, String color2) {
        String textBox = "";
        textBox += color + color2;
        textBox += " ••••••••••••••••  ••••••••••••••••  •••••••••••••••• \n";
        textBox += color + color2;
        textBox += " •              •  •              •  •              • \n";
        textBox += color + color2;
        textBox += " •              •  •              •  •                \n";
        textBox += color + color2;
        textBox += " •              •  •              •  •                \n";
        textBox += color + color2;
        textBox += " ••••••••••••••••  ••••••••••••••••  •                \n";
        textBox += color + color2;
        textBox += " • •               •                 •        ••••••• \n";
        textBox += color + color2;
        textBox += " •    •            •                 •              • \n";
        textBox += color + color2;
        textBox += " •       •         •                 •              • \n";
        textBox += color + color2;
        textBox += " •          •      •                 •              • \n";
        textBox += color + color2;
        textBox += " •             •   •                 •••••••••••••••• \n";
        textBox += color + color2;
        textBox += "                                                      \n";
        textBox += color + color2;
        textBox += "         By Yannick Montes & Corentin Maréchal        \n";
        textBox += color + color2;
        textBox += "                                                      \n";

        textBox += RESET;
        return textBox;
    }
}
