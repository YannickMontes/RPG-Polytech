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
public class Thief extends Character implements Attack, Care, Block {

    public Thief(String name) {
        super(name, "Voleur");

    }

    public Thief(String n, int l) {
        super(n, l, "Voleur");
    }

    @Override
    public void initStats() {
        this.basicAttributes.put(Attribute.DEXTERITY, 30);
        this.basicAttributes.put(Attribute.DEFENSE, 15);
        this.basicAttributes.put(Attribute.SPEED, 30);
        this.basicAttributes.put(Attribute.STRENGTH, 25);
        this.attributes.put(Attribute.SPEED, 30);
        this.attributes.put(Attribute.STRENGTH, 25);
        this.attributes.put(Attribute.DEFENSE, 15);
        this.attributes.put(Attribute.DEXTERITY, 30);
        this.attributes.put(Attribute.HEALTH, 150 + 2 * level.getLevel() + 3);
        this.attributes.put(Attribute.MANA, 20 + 2 * level.getLevel() + 3);

        capacities.add("Attaquer");
        capacities.add("Bloquer");
        capacities.add("Soigner");
    }

    @Override
    public void putRandomPoint(int lvl) {
        int cpt = NBPOINTLEVELUP;
        if (lvl % 10 == 0)//Tout les 10 lvl
        {
            if (this.increaseAttribute(Attribute.DEFENSE, 2).equals("")) {
                cpt -= 2;
            }
            if (this.increaseAttribute(Attribute.STRENGTH, 1).equals("")) {
                cpt -= 1;
            }
        } else if (lvl % 2 == 0)//Tout les lvl pairs 
        {
            if (this.increaseAttribute(Attribute.SPEED, 2).equals("")) {
                cpt -= 2;
            }
            if (this.increaseAttribute(Attribute.DEXTERITY, 1).equals("")) {
                cpt -= 1;
            }
        } else//Tout les lvl impairs
        {
            if (this.increaseAttribute(Attribute.STRENGTH, 1).equals("")) {
                cpt -= 1;
            }
            if (this.increaseAttribute(Attribute.DEXTERITY, 1).equals("")) {
                cpt -= 1;
            }
            if (this.increaseAttribute(Attribute.DEFENSE, 1).equals("")) {
                cpt -= 1;
            }
        }

        if (cpt > 0) {
            if (this.increaseAttribute(Attribute.STRENGTH, cpt).equals("")) {
                cpt = 0;
            }
            if (this.increaseAttribute(Attribute.DEFENSE, cpt).equals("")) {
                cpt = 0;
            }
            if (this.increaseAttribute(Attribute.DEXTERITY, cpt).equals("")) {
                cpt = 0;
            }
            if (this.increaseAttribute(Attribute.SPEED, cpt).equals("")) {
                cpt = 0;
            }
        }
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
                upBlock = measureImpact("block", null, null);
            }
            String text = blockResult(success, upBlock);
            return text;
        }
        return "Vous ne possédez pas la capacité de bloque actuellement";
    }

    @Override
    public String dodge() {
        if (capacities.contains("Bloquer")) {
            boolean success = verifySuccess("dodge");
            int upDodge = 0;
            if (success == true) {
                upDodge = measureImpact("dodge", null, null);
            }
            String text = dodgeResult(success, upDodge);
            return text;
        }
        return "Vous ne possédez pas la capacité d'esquive actuellement";
    }
}
