/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author coren
 */
public abstract class ConsoleDesign {

    public static String boldText = "\u001B[1m";
    public static String redBack = "\033[1;41m";
    public static String redText = "\033[31m";

    public static String textBox(String text,String color) {
        String textBox = "";
        if(color!=null)
            textBox+=color;
        for (int i = 0; i < text.length()+4; i++) {
            textBox += "-";
        }
        textBox += "\n";
                    textBox+=color;
        textBox += "| "+text+" |";
        textBox += "\n";
                    textBox+=color;
        for (int i = 0; i < text.length()+4; i++) {
            textBox += "-";
        }
        return textBox;
    }
    
     public static String textDashArrow(String text,String color) {
        String textBox = "";
        textBox+=color;
        textBox += "--> "+text+" <--";
        return textBox;
    }
     
     public static String textDash(String text,String color) {
        String textBox = "";
        textBox+=color;
        textBox += "--- "+text+" ---";
        return textBox;
    }
     
     public static String text(String text,String color) {
        String textBox = "";
        textBox+=color;
        textBox += text;
        return textBox;
    }
}
