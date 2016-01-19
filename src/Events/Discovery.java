/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Controller.ConsoleDesign;
import Controller.Controller;
import Entities.Athlete;
import Manager.Team;
import Entities.Character;
import Entities.Thief;
import Entities.Warrior;
import Items.Armor;
import Items.Greave;
import Items.Item;
import Items.Potion;
import Items.Rarity;
import Items.StuffItem;
import Items.UseableItem;
import Items.Weapon;

/**
 *
 * @author yannick
 */
public class Discovery extends Event {

    private Team team;

    public Discovery(Team t) {
        this.name = "Découverte!";
        this.team = t;

        generateRandomDiscovery();
    }

    private void askUserAddItem(Item item) {
        String text = "\n" + ConsoleDesign.textBox("Vous avez découvert l'item suivant:", ConsoleDesign.redText);
        text += ConsoleDesign.text(item.toString(), ConsoleDesign.magentaText);
        System.out.println(text);
        boolean yes = Controller.askYesNo(ConsoleDesign.textDashArrow("Voulez-vous ajouter cet item découvert ? [O/N]", ConsoleDesign.redText));
        text = "";
        if (yes) {
            int num = 0;
            String characText = "";
            System.out.println(ConsoleDesign.textDash("A qui voulez-vous ajouter l'item découvert ?", ConsoleDesign.redText));
            for (Character c : team.getCharacters()) {
                characText += ConsoleDesign.text(Integer.toString(num) + " -> " + c.getName(), ConsoleDesign.redText) + "\n";
                num++;
            }
            int persoNumber;
            do {
                persoNumber = Controller.askNumberBetween(characText, 0, num - 1);
            } while (!team.getCharacters().get(persoNumber).isAlive());

            Character chosePerso = team.getCharacters().get(persoNumber);
            boolean successAdd = chosePerso.addInventory(item);

            if (successAdd) {
                text += ConsoleDesign.text("Il a été placé dans l'inventaire du personnage " + team.getCharacters().get(persoNumber).getName() + ".", ConsoleDesign.magentaText);
            } else {
                text += ConsoleDesign.text("Vous n'avez plus de place dans tout vos inventaires.", ConsoleDesign.redText);
            }
            System.out.println(text);
        }
    }

    private void generateRandomDiscovery() {
        int randomNumber = (int) (Math.random() * 100);
        Item item = null;

        if (randomNumber == 0)//1%
        {
            //Nouveau personnage
            System.out.println("\n" + ConsoleDesign.textBox("Vous avez découvert un nouveau personnage!", ConsoleDesign.redText) + "\n");
            if (Team.NBMAXCHARACTERS >= this.team.getNbCharacters()) {
                System.out.println(ConsoleDesign.text("Cependant, vous ne pouvez plus ajouter de personnages à votre équipe.", ConsoleDesign.redText));
            } else {
                Character newCh = null;
                randomNumber = (int) (Math.random() * (Character.NBCLASSES - 1));
                switch (randomNumber) {
                    case 0:
                        newCh = new Warrior("Sylvain Duriff", (int) (Math.random() * (3)) + team.getLevelMaxInTeam() - 1);
                        break;
                    case 1:
                        newCh = new Thief("EddineLanger", (int) (Math.random() * (3)) + team.getLevelMaxInTeam() - 1);
                        break;
                    case 2:
                        newCh = new Athlete("Pierre Kiroule", (int) (Math.random() * (3)) + team.getLevelMaxInTeam() - 1);
                        break;
                }
                team.addCharacterTeam(newCh);
                System.out.println(ConsoleDesign.text(newCh.toString() + "Ce personnage à été ajouté à votre équipe.\n", ConsoleDesign.magentaText));
            }
        } else if (randomNumber <= 20)//20%
        {
            if (randomNumber <= 10)//10% Arme mainstream
            {
                item = StuffItem.getRandomItemInList(Rarity.MAINSTREAM, this.team.getLevelMaxInTeam() + 5, Weapon.listWeaponItem);
            } else if (randomNumber <= 16)//6% Arme commune
            {
                item = StuffItem.getRandomItemInList(Rarity.COMMON, this.team.getLevelMaxInTeam() + 5, Weapon.listWeaponItem);
            } else if (randomNumber <= 19)//3% Arme rare
            {
                item = StuffItem.getRandomItemInList(Rarity.RARE, this.team.getLevelMaxInTeam() + 5, Weapon.listWeaponItem);
            } else//1% Arme primordial
            {
                item = StuffItem.getRandomItemInList(Rarity.PRIMORDIAL, this.team.getLevelMaxInTeam() + 5, Weapon.listWeaponItem);
            }
        } else if (randomNumber <= 40)//20%
        {
            if (randomNumber <= 30)//10% Armure mainstream
            {
                item = StuffItem.getRandomItemInList(Rarity.MAINSTREAM, this.team.getLevelMaxInTeam() + 5, Armor.listeArmorItem);
            } else if (randomNumber <= 36)//6% Armure commune
            {
                item = StuffItem.getRandomItemInList(Rarity.COMMON, this.team.getLevelMaxInTeam() + 5, Armor.listeArmorItem);
            } else if (randomNumber <= 39)//3% Armure rare
            {
                item = StuffItem.getRandomItemInList(Rarity.RARE, this.team.getLevelMaxInTeam() + 5, Armor.listeArmorItem);
            } else//1% Armure primordial
            {
                item = StuffItem.getRandomItemInList(Rarity.PRIMORDIAL, this.team.getLevelMaxInTeam() + 5, Armor.listeArmorItem);
            }
        } else if (randomNumber <= 60)//20%
        {
            if (randomNumber <= 50)//10% Jambieres mainstream
            {
                item = StuffItem.getRandomItemInList(Rarity.MAINSTREAM, this.team.getLevelMaxInTeam() + 5, Greave.listGreaveItem);
            } else if (randomNumber <= 56)//6% Jambieres commune
            {
                item = StuffItem.getRandomItemInList(Rarity.COMMON, this.team.getLevelMaxInTeam() + 5, Greave.listGreaveItem);
            } else if (randomNumber <= 59)//3% Jambieres rare
            {
                item = StuffItem.getRandomItemInList(Rarity.RARE, this.team.getLevelMaxInTeam() + 5, Greave.listGreaveItem);
            } else//1% Jambieres primordial
            {
                item = StuffItem.getRandomItemInList(Rarity.PRIMORDIAL, this.team.getLevelMaxInTeam() + 5, Greave.listGreaveItem);
            }
        } else //Objet
        {
            item = UseableItem.getRandomItem(Potion.listPotionItem);
        }

        if (item != null) {
            askUserAddItem(item);
        }

        /*
        boolean successAdd = false
                
        for(i=0; i<team.getNbCharacters(); i++)
        {
            if(team.getCharacters().get(i).inventoryNotFull())
            {
                if(stuffItem==null && item!=null)
                {
                    successAdd = team.getCharacters().get(i).addInventory(item);
                }
                else if(stuffItem!= null && item==null)
                {
                    successAdd = team.getCharacters().get(i).addInventory(stuffItem);
                }
                break;
            }
        }*/
    }

}
