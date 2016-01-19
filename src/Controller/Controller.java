/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Scanner;

/**
 *
 * @author Corentin
 */
public abstract class Controller {

    public static final Scanner sc = new Scanner(System.in);

    public static int askNumberBetween(String text, int start, int limit) {
        System.out.println(text);
        do {
            try {
                String textIn = sc.nextLine();
                int number = Integer.parseInt(textIn);
                if (number >= start && number <= limit) {
                    return number;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(ConsoleDesign.text("Veuillez entrer un chiffre entre " + Integer.toString(start) + " et " + Integer.toString(limit), ConsoleDesign.redText));
            }
        } while (true);
    }

    public static String askText(String text) {
        System.out.println(text);
        return sc.nextLine();
    }

    public static boolean askYesNo(String text) {
        System.out.println(text);
        do {
            try {
                String textIn = sc.nextLine();
                if ("O".equals(textIn) || "o".equals(textIn)) {
                    return true;
                } else if ("N".equals(textIn) || "n".equals(textIn)) {
                    return false;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(ConsoleDesign.text("Veuillez entrez O ou o pour OUI / N ou n pour NON", ConsoleDesign.redText));
            }
        } while (true);
    }

}
