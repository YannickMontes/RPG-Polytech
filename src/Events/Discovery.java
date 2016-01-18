/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

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
public class Discovery extends Event
{
    public Team team;
    public String text;
    
    public Discovery(Team t)
    {
        this.name="Découverte!";
        this.team = t;
        
        generateRandomDiscovery();
    }
    
    private void generateRandomDiscovery()
    {
        int randomNumber = (int)(Math.random()*100);
        StuffItem stuffItem = null;
        Item item = null;
                
        if(randomNumber==0)//1%
        {
            //Nouveau personnage
            Character newCh = null;
            randomNumber = (int)(Math.random()*(Character.NBCLASSES-1));
            switch(randomNumber)
            {
                case 0:
                    newCh = new Warrior("Sylvain Duriff", (int)(Math.random()*(3))+team.getLevelMaxInTeam()-1);
                    break;
                case 1:
                    newCh = new Thief("EddineLanger", (int)(Math.random()*(3))+team.getLevelMaxInTeam()-1);
                    break;
                case 2:
                    newCh = new Athlete("Pierre Kiroule", (int)(Math.random()*(3))+team.getLevelMaxInTeam()-1);
                    break;
            }
            team.addCharacterTeam(newCh);
        }
        else if(randomNumber<=20)//20%
        {
            if(randomNumber<=10)//10% Arme mainstream
            {  
                stuffItem = StuffItem.getRandomItemInList(Rarity.MAINSTREAM, this.team.getLevelMaxInTeam()+5, Weapon.listWeaponItem);
            }
            else if(randomNumber<=16)//6% Arme commune
            {
                stuffItem = StuffItem.getRandomItemInList(Rarity.COMMON, this.team.getLevelMaxInTeam()+5, Weapon.listWeaponItem);
            }
            else if(randomNumber<=19)//3% Arme rare
            {
                stuffItem = StuffItem.getRandomItemInList(Rarity.RARE, this.team.getLevelMaxInTeam()+5, Weapon.listWeaponItem);
            }
            else//1% Arme primordial
            {
                stuffItem = StuffItem.getRandomItemInList(Rarity.PRIMORDIAL, this.team.getLevelMaxInTeam()+5, Weapon.listWeaponItem);
            }
        }
        else if(randomNumber<=40)//20%
        {
            if(randomNumber<=30)//10% Armure mainstream
            {  
                stuffItem = StuffItem.getRandomItemInList(Rarity.MAINSTREAM, this.team.getLevelMaxInTeam()+5, Armor.listeArmorItem);
            }
            else if(randomNumber<=36)//6% Armure commune
            {
                stuffItem = StuffItem.getRandomItemInList(Rarity.COMMON, this.team.getLevelMaxInTeam()+5, Armor.listeArmorItem);
            }
            else if(randomNumber<=39)//3% Armure rare
            {
                stuffItem = StuffItem.getRandomItemInList(Rarity.RARE, this.team.getLevelMaxInTeam()+5, Armor.listeArmorItem);
            }
            else//1% Armure primordial
            {
                stuffItem = StuffItem.getRandomItemInList(Rarity.PRIMORDIAL, this.team.getLevelMaxInTeam()+5, Armor.listeArmorItem);
            }
        }
        else if(randomNumber<=60)//20%
        {
            if(randomNumber<=50)//10% Jambieres mainstream
            {  
                stuffItem = StuffItem.getRandomItemInList(Rarity.MAINSTREAM, this.team.getLevelMaxInTeam()+5, Greave.listGreaveItem);
            }
            else if(randomNumber<=56)//6% Jambieres commune
            {
                stuffItem = StuffItem.getRandomItemInList(Rarity.COMMON, this.team.getLevelMaxInTeam()+5, Greave.listGreaveItem);
            }
            else if(randomNumber<=59)//3% Jambieres rare
            {
                stuffItem = StuffItem.getRandomItemInList(Rarity.RARE, this.team.getLevelMaxInTeam()+5, Greave.listGreaveItem);
            }
            else//1% Jambieres primordial
            {
                stuffItem = StuffItem.getRandomItemInList(Rarity.PRIMORDIAL, this.team.getLevelMaxInTeam()+5, Greave.listGreaveItem);
            }
        }
        else //Objet
        {
            item = UseableItem.getRandomItem(Potion.listPotionItem);
        }
        
        int i=0;
        for(i=0; i<team.getNbCharacters(); i++)
        {
            if(team.getCharacters().get(i).inventoryNotFull())
            {
                if(stuffItem==null)
                {
                    team.getCharacters().get(i).addInventory(item);
                }
                else
                {
                    team.getCharacters().get(i).addInventory(stuffItem);
                }
                break;
            }
        }
        text = Controller.ConsoleDesign.text("Vous avez découvert l'item suivant:\n", Controller.ConsoleDesign.magentaText);
        text += (stuffItem==null) ? item.toString() : stuffItem.toString();
        text += Controller.ConsoleDesign.text("Il a été placé dans l'inventaire du personnage "+team.getCharacters().get(i).getName()+".", Controller.ConsoleDesign.magentaText);
    }

    @Override
    public String toString()
    {
        return Controller.ConsoleDesign.text(text, Controller.ConsoleDesign.magentaText);
    }
    
    
}
