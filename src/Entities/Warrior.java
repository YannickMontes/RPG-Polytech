/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Actions.Attack;
import Actions.Block;
import Actions.Care;
import Items.UseableItem;

/**
 *
 * @author yannick
 */
public class Warrior extends Character implements Attack, Block, Care {

    public Warrior(String name) {
        super(name, "Guerrier");
        this.basicAttributes.put(Attribute.STRENGTH, 35);
        this.basicAttributes.put(Attribute.DEXTERITY, 22);
        this.basicAttributes.put(Attribute.DEFENSE, 23);
        this.basicAttributes.put(Attribute.SPEED, 15);
        this.attributes.put(Attribute.SPEED, 15);
        this.attributes.put(Attribute.STRENGTH, 35);
        this.attributes.put(Attribute.DEFENSE, 23);
        this.attributes.put(Attribute.DEXTERITY, 22);
        this.attributes.put(Attribute.HEALTH, 150 + 2 * level + 3);
        this.attributes.put(Attribute.MANA, 20 + 2 * level + 3);

        capacities.add("Attaquer");
        capacities.add("Bloquer");
        capacities.add("Soigner");
    }
    
    public Warrior(String n, int l)
    {
        super(n, l);
    }

    @Override
    public void putRandomPoint()
    {
        if(this.level%2 == 0)//Si le niveau est pair
        {
            this.basicAttributes.replace(Attribute.STRENGTH, this.basicAttributes.get(Attribute.STRENGTH)+Character.NBPOINTLEVELUP-1);
            this.basicAttributes.replace(Attribute.DEFENSE, this.basicAttributes.get(Attribute.DEFENSE)+Character.NBPOINTLEVELUP-2);
        }
        else
        {
            this.basicAttributes.replace(Attribute.DEXTERITY, this.basicAttributes.get(Attribute.DEXTERITY)+1);
            this.basicAttributes.replace(Attribute.STRENGTH, this.basicAttributes.get(Attribute.STRENGTH)+1);
            this.basicAttributes.replace(Attribute.DEFENSE, this.basicAttributes.get(Attribute.DEFENSE)+1);
        }
    }

    @Override
    public String strikeABlow(Character opponent) {
        if (opponent != null) {
            if (capacities.contains("Attaquer")) {
                boolean success = verifySuccess("attack");
                int damages = 0;
                if (success == true) {
                    damages = measureImpact("attack", opponent, null);
                    opponent.takeABlow(damages);
                }
                String text = attackResult(success, opponent, damages);
                return text;
            }
            return "Vous ne possédez pas la capacité d'attaque actuellement";
        }
        return "Votre adversaire est inconnu";
    }

    @Override
    public String block() {
        if (capacities.contains("Bloquer")) {
            boolean success = verifySuccess("block");
            int upBlock = 0;
            if (success == true) {
                upBlock = measureImpact("attack", null, null);
            }
            String text = blockResult(success, upBlock);
            return text;
        }
        return "Vous ne possédez pas la capacité d'attaque actuellement";
    }

    @Override
    public String dodge() {
        return "";
    }

    @Override
    public String heal(UseableItem useableItem) {
        if (useableItem != null) {
            if (inventory.contains(useableItem)) {
                if (capacities.contains("Soigner")) {
                    boolean success = verifySuccess("care");
                    int care = 0;
                    if (success == true) {
                        care = measureImpact("care", null, useableItem);
                        this.attributes.replace(Attribute.HEALTH, this.attributes.get(Attribute.HEALTH) + care);
                    }
                    String text = careResult(success, care);
                    return text;
                }
                return "Vous ne possedez pas la capacité de soigner actuellement";
            }
            return "Vous ne possedez pas cet objet dans votre inventaire";
        }
        return "Vous ne possedez pas d'objets utilisables dans votre inventaire";
    }

}
