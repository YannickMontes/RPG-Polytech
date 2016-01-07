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
public class Controller {
        
    Scanner sc;

    public Controller()
    {
        sc = new Scanner(System.in);
    }
    
    public int askNumberBetween(String text, int start, int limit) {
        System.out.println(text);
        do {
            try {
                String textIn = sc.nextLine();
                int number = Integer.parseInt(textIn);
                if (number >= start && number <= limit) {
                    return number;
                }else{
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Veuillez entrer un chiffre entre " + Integer.toString(start) + " et " + Integer.toString(limit));
            }
        } while (true);
    }
    
    public String askText(String text) {
        System.out.println(text);
        return sc.nextLine();
    }
    

}
