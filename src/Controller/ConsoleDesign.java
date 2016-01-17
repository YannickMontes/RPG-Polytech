/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools • Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author coren
 */
public abstract class ConsoleDesign {

    public static String boldText = "\u001B[1m";
    public static String redBack = "\033[41m";
    public static String redText = "\033[31m";
    public static String greenBack = "\033[42m";
    public static String greenText = "\033[32m";
    public static String yellowBack = "\033[43m";
    public static String yellowText = "\033[33m";
    public static String blueBack = "\033[44m";
    public static String blueText = "\033[34m";
    public static String magentaBack = "\033[45m";
    public static String magentaText = "\033[35m";
    public static String cyanBack = "\033[46m";
    public static String cyanText = "\033[36m";
    public static String whiteBack = "\033[47m";
    public static String whiteText = "\033[1;37m";
    public static String reset = "\033[0m";

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
        textBox += reset;
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
        textBox += reset;
        return textBox;
    }

    public static String textDashArrow(String text, String color) {
        String textBox = "";
        textBox += color;
        textBox += "--> " + text + " <--";
        textBox += reset;
        return textBox;
    }

    public static String textDash(String text, String color) {
        String textBox = "";
        textBox += color;
        textBox += "--- " + text + " ---";
        textBox += reset;
        return textBox;
    }

    public static String text(String text, String color) {
        String textBox = "";
        textBox += color;
        textBox += text;
        textBox += reset;
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

        textBox += reset;
        return textBox;
    }
}
