/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Actions;

import com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign;
import com.corentin_yannick.RPG_Polytech.Controllers.Controller;
import com.corentin_yannick.RPG_Polytech.Entities.Athlete;
import com.corentin_yannick.RPG_Polytech.Entities.Attribute;
import com.corentin_yannick.RPG_Polytech.Items.Armor;
import com.corentin_yannick.RPG_Polytech.Items.StuffItem;
import com.corentin_yannick.RPG_Polytech.Items.UseableItem;
import com.corentin_yannick.RPG_Polytech.Items.Weapon;
import com.corentin_yannick.RPG_Polytech.Manager.Team;
import com.corentin_yannick.RPG_Polytech.Entities.Character;
import com.corentin_yannick.RPG_Polytech.Entities.Thief;
import com.corentin_yannick.RPG_Polytech.Entities.Warrior;
import com.corentin_yannick.RPG_Polytech.Manager.Turn;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.RED;
import com.corentin_yannick.RPG_Polytech.Items.Item;
/**
 *
 * @author coren
 */
public class Action {

    public Character character;
    public Team opponentsTeam;
    public Turn currentTurn;
    public String message;

    public Action(Character character, Team opponentsTeam, Turn currentTurn) {
        this.character = character;
        this.opponentsTeam = opponentsTeam;
        this.currentTurn = currentTurn;
        this.message = "";
    }

    public String makeAutoAttack() {
        Character opponent = null;
        do
        {
            int opponentNumber = 0;
            int probaOpponent = (int) (Math.random() * (opponentsTeam.getCharacters().size() * 100));
            opponentNumber = probaOpponent / 100;
            opponent = opponentsTeam.getCharacters().get(opponentNumber);
        }while(!opponent.isAlive());
        boolean success = verifySuccess("attack");
        int damages = 0;
        if (success == true) {
            damages = measureImpact("attack", opponent, null);
            if (character instanceof Warrior) {
                ((Warrior) character).strikeABlow(opponent, damages);
            } else if (character instanceof Athlete) {
                ((Athlete) character).strikeABlow(opponent, damages);
            } else if (character instanceof Thief) {
                ((Thief) character).strikeABlow(opponent, damages);
            }
        }
        return attackResult(success, opponent, damages);
    }

   /* public String makeAutoUseObject() {
        UseableItem useableItem = character.getInInventoryItemOfType(UseableItem.class, );
        boolean success = verifySuccess("useItem");
        int upValue = 0;
        if (success == true) {
            if (character instanceof Warrior) {
                if (((Warrior) character).getInventory().contains(useableItem)) {
                    upValue = measureImpact("useItem", null, useableItem);
                    if (((Warrior) character).useItem(useableItem, upValue) == false) {
                        System.out.println(ConsoleDesign.text("Vous ne possédez pas cette capacité !", RED));
                        return false;
                    }
                } else {
                    System.out.println(ConsoleDesign.text("Vous ne possédez pas cette objet !", RED));
                    return false;
                }
            } else if (character instanceof Athlete) {
                if (((Warrior) character).getInventory().contains(useableItem)) {
                    upValue = measureImpact("useItem", null, useableItem);
                    if (((Warrior) character).useItem(useableItem, upValue) == false) {
                        System.out.println(ConsoleDesign.text("Vous ne possédez pas cette capacité !", RED));
                        return false;
                    }
                } else {
                    System.out.println(ConsoleDesign.text("Vous ne possédez pas cette objet !", RED));
                    return false;
                }
            } else if (character instanceof Thief) {
                if (((Warrior) character).getInventory().contains(useableItem)) {
                    upValue = measureImpact("useItem", null, useableItem);
                    if (((Warrior) character).useItem(useableItem, upValue) == false) {
                        System.out.println(ConsoleDesign.text("Vous ne possédez pas cette capacité !", RED));
                        return false;
                    }
                } else {
                    System.out.println(ConsoleDesign.text("Vous ne possédez pas cette objet !", RED));
                    return false;
                }
            }
        }
        System.out.println(ConsoleDesign.text(careResult(success, useableItem), RED));

        currentTurn.turnOf(character, true);
    }
    */

    public String makeAutoDefense() {
        int blockNumber = (int) (1 + Math.random());
        if (character instanceof Warrior) {
            switch (blockNumber) {
                case 1:
                    boolean success = verifySuccess("block");
                    int upBlock = 0;
                    if (success == true) {
                        upBlock = measureImpact("block", null, null);
                        ((Warrior) character).block(upBlock);
                    }
                    return blockResult(success, upBlock);
                case 2:
                    boolean successD = verifySuccess("dodge");
                    int upDodge = 0;
                    if (successD == true) {
                        upDodge = measureImpact("dodge", null, null);
                        ((Warrior) character).dodge(upDodge);
                    }
                    return dodgeResult(successD, upDodge);
            }
        } else if (character instanceof Athlete) {
            switch (blockNumber) {
                case 1:
                    boolean success = verifySuccess("block");
                    int upBlock = 0;
                    if (success == true) {
                        upBlock = measureImpact("block", null, null);
                        ((Athlete) character).block(upBlock);
                    }
                    return blockResult(success, upBlock);
                case 2:
                    boolean successD = verifySuccess("dodge");
                    int upDodge = 0;
                    if (successD == true) {
                        upDodge = measureImpact("dodge", null, null);
                        ((Athlete) character).dodge(upDodge);
                    }
                    return dodgeResult(successD, upDodge);
            }
        } else if (character instanceof Thief) {
            switch (blockNumber) {
                case 1:
                    boolean success = verifySuccess("block");
                    int upBlock = 0;
                    if (success == true) {
                        upBlock = measureImpact("block", null, null);
                        ((Thief) character).block(upBlock);
                    }
                    return blockResult(success, upBlock);
                case 2:
                    boolean successD = verifySuccess("dodge");
                    int upDodge = 0;
                    if (successD == true) {
                        upDodge = measureImpact("dodge", null, null);
                        ((Thief) character).dodge(upDodge);
                    }
                    return dodgeResult(successD, upDodge);
            }
        }
        return "";
    }

    public boolean makeAttack() {
        Character opponent = null;
        String actionText = ConsoleDesign.textDashArrow("Choississez un adversaire", RED) + "\n";
        int num = 0;
        for (Character op : opponentsTeam.getCharacters()) {
            if (op.isAlive()) {
                actionText += ConsoleDesign.text(Integer.toString(num) + " -> " + op.getName(), RED) +" (Santé:"+op.getAttributeValue(Attribute.HEALTH) + ")\n";
            }
            num++;
        }

        int opponentNumber;
        do {
            opponentNumber = Controller.askNumberBetween(actionText, 0, num - 1);
        } while (!opponentsTeam.getCharacters().get(opponentNumber).isAlive());

        opponent = opponentsTeam.getCharacters().get(opponentNumber);

        boolean success = verifySuccess("attack");
        int damages = 0;
        if (success == true) {
            damages = measureImpact("attack", opponent, null);
            if (character instanceof Warrior) {
                if (((Warrior) character).strikeABlow(opponent, damages) == false) {
                    System.out.println("Vous ne possédez pas cette capacité !");
                    return false;
                }
            } else if (character instanceof Athlete) {
                if (((Athlete) character).strikeABlow(opponent, damages) == false) {
                    System.out.println("Vous ne possédez pas cette capacité !");
                    return false;
                }
            } else if (character instanceof Thief) {
                if (((Thief) character).strikeABlow(opponent, damages) == false) {
                    System.out.println("Vous ne possédez pas cette capacité !");
                    return false;
                }
            }
        }
        System.out.println(attackResult(success, opponent, damages));
        return true;
    }

    
    
    public boolean makeDefense() {
        String text = ConsoleDesign.textDashArrow("Quelle parade voulez-vous utiliser ?", RED) + " \n";
        text += ConsoleDesign.text("1 -> Blocage", RED) + "\n";
        text += ConsoleDesign.text("2 -> Esquive", RED) + "\n";
        int blockNumber = Controller.askNumberBetween(text, 1, 2);
        if (character instanceof Warrior) {
            switch (blockNumber) {
                case 1:
                    boolean success = verifySuccess("block");
                    int upBlock = 0;
                    if (success == true) {
                        upBlock = measureImpact("block", null, null);
                        if (((Warrior) character).block(upBlock) == false) {
                            System.out.println(ConsoleDesign.text("Vous ne possédez pas cette capacité !", RED));
                            return false;
                        }
                    }
                    System.out.println(ConsoleDesign.text(blockResult(success, upBlock), RED));
                    break;
                case 2:
                    boolean successD = verifySuccess("dodge");
                    int upDodge = 0;
                    if (successD == true) {
                        upDodge = measureImpact("dodge", null, null);
                        if (((Warrior) character).dodge(upDodge) == false) {
                            System.out.println(ConsoleDesign.text("Vous ne possédez pas cette capacité !", RED));
                            return false;
                        }
                    }
                    System.out.println(ConsoleDesign.text(dodgeResult(successD, upDodge), RED));
                    break;
            }
        } else if (character instanceof Athlete) {
            switch (blockNumber) {
                case 1:
                    boolean success = verifySuccess("block");
                    int upBlock = 0;
                    if (success == true) {
                        upBlock = measureImpact("block", null, null);
                        if (((Athlete) character).block(upBlock) == false) {
                            System.out.println(ConsoleDesign.text("Vous ne possédez pas cette capacité !", RED));
                            return false;
                        }
                    }
                    System.out.println(ConsoleDesign.text(blockResult(success, upBlock), RED));
                    break;
                case 2:
                    boolean successD = verifySuccess("dodge");
                    int upDodge = 0;
                    if (successD == true) {
                        upDodge = measureImpact("dodge", null, null);
                        if (((Athlete) character).dodge(upDodge) == false) {
                            System.out.println(ConsoleDesign.text("Vous ne possédez pas cette capacité !", RED));
                            return false;
                        }
                    }
                    System.out.println(ConsoleDesign.text(dodgeResult(successD, upDodge), RED));
                    break;
            }
        } else if (character instanceof Thief) {
            switch (blockNumber) {
                case 1:
                    boolean success = verifySuccess("block");
                    int upBlock = 0;
                    if (success == true) {
                        upBlock = measureImpact("block", null, null);
                        if (((Thief) character).block(upBlock) == false) {
                            System.out.println(ConsoleDesign.text("Vous ne possédez pas cette capacité !", RED));
                            return false;
                        }
                    }
                    System.out.println(ConsoleDesign.text(blockResult(success, upBlock), RED));
                    break;
                case 2:
                    boolean successD = verifySuccess("dodge");
                    int upDodge = 0;
                    if (successD == true) {
                        upDodge = measureImpact("dodge", null, null);
                        if (((Thief) character).dodge(upDodge) == false) {
                            System.out.println(ConsoleDesign.text("Vous ne possédez pas cette capacité !", RED));
                            return false;
                        }
                    }
                    System.out.println(ConsoleDesign.text(dodgeResult(successD, upDodge), RED));
                    break;
            }
        }
        return true;
    }

    public boolean useObject() {
        UseableItem useableItem = null;
        if (character.getNumberUseableItem()> 0) {
            String careText = ConsoleDesign.textDashArrow("Quel item voulez-vous utiliser ?[0->Retour]", RED);
            System.out.println(character.getUseableItemsToString());
            int useableNumber = Controller.askNumberBetween(careText, 0, character.getNumberUseableItem());
            useableItem = (UseableItem)character.getInInventoryItemOfType(UseableItem.class, useableNumber);
        }else{
            System.out.println(ConsoleDesign.text("Vous ne possédez d'objet utilisatbles !", RED));
            return false;
        }
        boolean success = verifySuccess("useItem");
        int upValue = 0;
        if (success == true) {
            if (character instanceof Warrior) {
                if (((Warrior) character).inventoryContains(useableItem)) {
                    upValue = measureImpact("useItem", null, useableItem);
                    if (((Warrior) character).useItem(useableItem, upValue) == false) {
                        System.out.println(ConsoleDesign.text("Vous ne possédez pas cette capacité !", RED));
                        return false;
                    }
                } else {
                    System.out.println(ConsoleDesign.text("Vous ne possédez pas cette objet !", RED));
                    return false;
                }
            } else if (character instanceof Athlete) {
                if (((Athlete) character).inventoryContains(useableItem)) {
                    upValue = measureImpact("useItem", null, useableItem);
                    if (((Athlete) character).useItem(useableItem, upValue) == false) {
                        System.out.println(ConsoleDesign.text("Vous ne possédez pas cette capacité !", RED));
                        return false;
                    }
                } else {
                    System.out.println(ConsoleDesign.text("Vous ne possédez pas cette objet !", RED));
                    return false;
                }
            } else if (character instanceof Thief) {
                if (((Thief) character).inventoryContains(useableItem)) {
                    upValue = measureImpact("useItem", null, useableItem);
                    if (((Thief) character).useItem(useableItem, upValue) == false) {
                        System.out.println(ConsoleDesign.text("Vous ne possédez pas cette capacité !", RED));
                        return false;
                    }
                } else {
                    System.out.println(ConsoleDesign.text("Vous ne possédez pas cette objet !", RED));
                    return false;
                }
            }
        }
        System.out.println(ConsoleDesign.text(careResult(success, useableItem), RED));

        currentTurn.turnOf(character, true);
        return true;
    }

    public boolean useCapacity()
    {
        Character opponent = null;
        String actionText = ConsoleDesign.textDashArrow("Choississez un adversaire", RED) + "\n";
        int num = 0;
        for (Character op : opponentsTeam.getCharacters()) {
            if (op.isAlive()) {
                actionText += ConsoleDesign.text(Integer.toString(num) + " -> " + op.getName(), RED) + "\n";
            }
            num++;
        }

        int opponentNumber;
        do {
            opponentNumber = Controller.askNumberBetween(actionText, 0, num - 1);
        } while (!opponentsTeam.getCharacters().get(opponentNumber).isAlive());
        
        opponent = opponentsTeam.getCharacters().get(opponentNumber);
       
        String result = this.verifySuccesCapacity(opponent);
        
        if(result.equals(""))
        {
            int damages = measureImpact("capacity", opponent, null);
            opponent.takeABlow(damages);
            this.message += this.capacityResult(opponent, damages);
        }
        else
        {
            this.message += result;
            if(result.equals("Vous n'avez pas assez de mana"))
            {
                return false;
            }
        }
        
        System.out.println(ConsoleDesign.text(this.message, RED));
        
        return true;
    }

    /**
     * Fonction permettant d'utiliser une capacité.
     *
     * @param capacity La capacité a utiliser.
     * @return Vrai si la capacité à fonctionnée, faux sinon.
     */
    public boolean verifySuccess(String capacity) {
        //selon la capacitié utilisée: selon ses caractéristiques et son arme (s'il en a)
        //calcule de proba
        float probability = 0;
        if ("attack".equals(capacity)) {
            float mania = 1;
            StuffItem weapon = character.getEquipment(Weapon.class);
            mania = weapon.getHandlingAbility() / 100;

            probability = mania;
            probability += 2 * (1 - mania) * (character.getAttributeValue(Attribute.DEXTERITY) * 0.004);
            //System.out.println("Probabilité d'attaquer: " + probability);
        } else if ("block".equals(capacity)) {
            //probability = this.attributes.get(Attribute.DEFENSE);
            StuffItem armor = character.getEquipment(Armor.class);
            probability += (float) (((Armor) armor).getHandlingAbility() / 100) * 0.2; // entre 0 et 100
            probability += (float) ((float)character.getAttributeValue(Attribute.DEFENSE) / 100)/* * (1 - (0.2 * this.getEquipment(Armor.class).size()))*/;
        } else if ("dodge".equals(capacity)) {
            probability -= character.getTotalWeightEquipment()/50;
            probability += (float) ((float)character.getAttributeValue(Attribute.SPEED) / 100)/* * (1 - (0.2 * this.getEquipment().size()))*/;
        } else if ("useItem".equals(capacity)) {
            probability = 1;
        }

        if (probability > 1) {
            probability = 1;
        } else if (probability < 0) {
            probability = 0;
        }

        int rand = (int) (Math.random() * (100));
        if (rand <= (probability * 100)) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param capacity
     * @param opponent
     * @param useableItem
     * @return
     */
    public int measureImpact(String capacity, Character opponent, UseableItem useableItem) {
        if ("attack".equals(capacity) && opponent != null) {
            return this.reduceDamages(opponent, (int) (character.getAttributeValue(Attribute.STRENGTH)*1.5f));
        } else if ("block".equals(capacity)) {
            return (int) (0.5 * character.getAttributeValue(Attribute.STRENGTH));
        } else if ("useItem".equals(capacity) && useableItem != null) {
            return useableItem.getValue();
        } else if("capacity".equals(capacity))
        {
            return this.calculateDamageCapacity(opponent);
        }
        return 0;
    }

    private int reduceDamages(Character opponent, int initialValue) {
        int defenseOpponent = opponent.getAttributeValue(Attribute.DEFENSE);
        int damage = 0;
        float reduction;
        if (defenseOpponent <= 50) {
            reduction = (float) (defenseOpponent * 0.01f);
        } else if (defenseOpponent <= 100) {
            reduction = (float) (0.5f + (defenseOpponent - 50) * 0.005f);
        } else if (defenseOpponent <= 200) {
            reduction = (float) (0.75f + (defenseOpponent - 100) * 0.00149925f);
        } else {
            reduction = 0.9f;
        }
        damage = (int) (initialValue * (1 - reduction));

        return damage;
    }

    /**
     * Fonction permettant de vérifier si l'attaque à fonctionner.
     *
     * @param success Réussite de l'attaque
     * @param opponent Cible
     * @param damages Dommages à infliger
     * @return Le texte à afficher.
     */
    public String attackResult(boolean success, Character opponent, int damages) {
        String text;
        if (success == true) {
            text = "(" + character.getName() + ") Attaque " + opponent.getName() + " avec une attaque de base, provoquant " + damages
                    + " points de dégats. (Santé: " + opponent.getAttributeValue(Attribute.HEALTH) + ")";
            if (!opponent.isAlive()) {
                text += "\nLe personnage " + opponent.getName() + " est mort!";
            }
            return text;
        }
        return "(" + character.getName() + ") L'attaque sur " + opponent.getName() + " a échoué.";
    }

    /**
     * Fonction permettant de vérifier la réussite du soin
     *
     * @param success Réussite du soin
     * @param item Item à utiliser
     * @return Le texte à afficher
     */
    public String careResult(boolean success, UseableItem item) {
        String text;
        if (success == true) {
            if (item.getAttributeBonus() == Attribute.HEALTH) {
                text = "(" + character.getName() + ") Se soigne de " + item.getValue() + ".";
            } else {
                text = "(" + character.getName() + ") Augmente de " + item.getValue() + " son attribut de " + item.getAttributeBonus() + " pour le tour actuel.";
            }
            return text;
        }
        return "(" + character.getName() + ") L'utilisation d'item a échoué.";
    }

    /**
     * Fonction permettant de vérifier la réussite du block
     *
     * @param success Réussite
     * @param blockValue valeur du block
     * @return Le texte à afficher
     */
    public String blockResult(boolean success, int blockValue) {
        if (success == true) {
            return "(" + character.getName() + ") Bloque les attaques pour le prochain coup. Défense augmentée de " + blockValue
                    + ". (Défense: " + character.getAttributeValue(Attribute.DEFENSE) + ")";
        }
        return "(" + character.getName() + ") Bloquage échoué.";
    }

    /**
     * Fonction permettant de vérifier la réussite de l'esquive.
     *
     * @param success Réussite de l'esquive
     * @param blockValue valeur de l'esquive (?)
     * @return Le texte ç afficher
     */
    public String dodgeResult(boolean success, int blockValue) {
        if (success == true) {
            return "(" + character.getName() + ") Esquive les attaques le prochain tour.";
        }
        return "L'esquive a échoué";
    }
    
    public String capacityResult(Character opponent, int damages)
    {
        String text = "";
        if(character instanceof Warrior)
        {
            text += "(" + character.getName() + ") Frappe "+opponent.getName()+" avec Anger, infligeant "+damages+" points de dégâts (Santé: "+opponent.getAttributeValue(Attribute.HEALTH)+")";
        }
        else if(character instanceof Athlete)
        {
            text += "(" + character.getName() + ") Frappe "+opponent.getName()+" avec Uppercut, ignorant ainsi la défense de l'adversaire et infligeant "+damages+" points de dégâts (Santé: "+opponent.getAttributeValue(Attribute.HEALTH)+")";
        }
        else if(character instanceof Thief)
        {
            text += "(" + character.getName() + ") Frappe "+opponent.getName()+" avec Pickpocket, infligeant "+damages+" points de dégâts (Santé: "+opponent.getAttributeValue(Attribute.HEALTH)+")";
        }
        if (!opponent.isAlive()) 
        {
            text += "\nLe personnage " + opponent.getName() + " est mort!";
        }
        return text;
    }

    private String verifySuccesCapacity(Character opponent)
    {
        if(character instanceof Warrior)
        {
            return ((Warrior)character).anger(opponent);
        }
        else if(character instanceof Athlete)
        {
            return ((Athlete)character).uppercut(opponent);
        }
        else if(character instanceof Thief)
        {
            return ((Thief)character).pickpocket(opponent);
        }
        return "Problème";
    }

    private int calculateDamageCapacity(Character opponent)
    {
        if(character instanceof Warrior)
        {
            this.character.decreaseAttribute(Attribute.MANA, 25);
            int basedmg = 40+(5*this.character.getLevel());
            float ratio = 0.8f;
            int finaldamage = (int) (basedmg + ratio*this.character.getAttributeValue(Attribute.STRENGTH));
            return this.reduceDamages(opponent, finaldamage);
        }
        else if(character instanceof Athlete)
        {
            this.character.decreaseAttribute(Attribute.MANA, 15);
            int basedmg = 25+(5*this.character.getLevel());
            float ratio = 0.5f;
            int finaldamage = (int) (basedmg + ratio*this.character.getAttributeValue(Attribute.STRENGTH));
            return finaldamage;
        }
        else if(character instanceof Thief)
        {
            this.character.decreaseAttribute(Attribute.MANA, 10);
            int basedmg = 15+(5*this.character.getLevel());
            float ratioVit = 2f;
            float ratioAtt = 0.2f;
            int finaldamage = (int) (basedmg + ratioAtt*this.character.getAttributeValue(Attribute.STRENGTH) + ratioVit*this.character.getAttributeValue(Attribute.SPEED));
            
            int nbItems = opponent.getNbItemsInInventory();
            if(nbItems!=0)
            {
                Item item = opponent.getItem((int) (Math.random()*nbItems));    
                if(this.character.addInventory(item))
                {
                    opponent.removeItemInInventory(item);
                }
                this.message += "(" + character.getName() + ") vole l'item suivant:\n"+item.toString();
            }
            else
            {
                this.message += "\nVotre ennemi n'as pas d'item à piller.\n";
            }
            return this.reduceDamages(opponent, finaldamage);
        }
        return 0;
    }
}
