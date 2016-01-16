/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entities.Athlete;
import Entities.Thief;
import Entities.Warrior;
import Items.StuffItem;
import Items.Weapon;

/**
 *
 * @author yannick
 */
public class RPG
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        /* Warrior warrior = new Warrior("Jean");
        warrior.getInventory().add(new Weapon("escalibure", 10, 15, 50));
        warrior.getInventory().add(new Weapon("r", 10, 15, 50));

        boolean a = warrior.equipMe((StuffItem) warrior.getInventory().get(0));
        boolean b = warrior.equipMe((StuffItem) warrior.getInventory().get(1));
         */
        Warrior w = new Warrior("Connard", 5);
        Athlete a = new Athlete("Enculé", 4);
        Thief t = new Thief("Enculé", 7);

        Team c = new Team("Player");
        c.addCharacterTeam(w);
        c.addCharacterTeam(t);
        c.addCharacterTeam(a);
        
        System.out.println(c.toString());
        
        Team tg = new Team("FDP");
        tg.generateRandomTeam(c);

        System.out.println(tg.toString());
        
        Game g = new Game();
        g.startGame();
    }

}
