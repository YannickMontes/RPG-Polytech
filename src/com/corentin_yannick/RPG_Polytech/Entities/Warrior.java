/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Entities;

import com.corentin_yannick.RPG_Polytech.Actions.Anger;
import com.corentin_yannick.RPG_Polytech.Actions.Attack;
import com.corentin_yannick.RPG_Polytech.Actions.Block;
import com.corentin_yannick.RPG_Polytech.Items.UseableItem;
import com.corentin_yannick.RPG_Polytech.Actions.UseItem;

/**
 *
 * @author yannick
 */
public class Warrior extends Character implements Attack, Block, UseItem, Anger {

    public Warrior(String name) {
        super(name, "Guerrier");
    }

    public Warrior(String n, int l) {
        super(n, l, "Guerrier");
    }

    @Override
    public void initStats() {
        this.basicAttributes.put(Attribute.STRENGTH, 35);
        this.basicAttributes.put(Attribute.DEXTERITY, 22);
        this.basicAttributes.put(Attribute.DEFENSE, 23);
        this.basicAttributes.put(Attribute.SPEED, 15);
        this.attributes.put(Attribute.SPEED, 15);
        this.attributes.put(Attribute.STRENGTH, 35);
        this.attributes.put(Attribute.DEFENSE, 23);
        this.attributes.put(Attribute.DEXTERITY, 22);
        this.attributes.put(Attribute.HEALTH, 150 + 4 * level.getLevel() + 3);
        this.attributes.put(Attribute.MANA, 20 + 3 * level.getLevel() + 3);

        capacities.add("Attaquer");
        capacities.add("Bloquer");
        capacities.add("Utiliser un item");
    }

    @Override
    public void putRandomPoint(int lvl) {
        int cpt = NBPOINTLEVELUP;
        if (lvl % 10 == 0)//Tout les 10 lvl, 3 en speed.
        {
            if (this.increaseAttribute(Attribute.SPEED, 3).equals("")) {
                cpt -= 3;
            }
        } else if (lvl % 2 == 0)//Tout les lvl pairs
        {
            if (this.increaseAttribute(Attribute.STRENGTH, 2).equals("")) {
                cpt -= 2;
            }
            if (this.increaseAttribute(Attribute.DEXTERITY, 1).equals("")) {
                cpt -= 1;
            }
        } else//Tout les lvl impairs
        {
            if (this.increaseAttribute(Attribute.DEFENSE, 2).equals("")) {
                cpt -= 2;
            }
            if (this.increaseAttribute(Attribute.DEXTERITY, 1).equals("")) {
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
    public boolean strikeABlow(Character opponent, int damages) {
        if (capacities.contains("Attaquer")) {
            opponent.takeABlow(damages);
            return true;
        }
        return false;
    }

     @Override
    public boolean block(int upValue) {
        if (capacities.contains("Bloquer")) {
            this.attributes.replace(Attribute.DEFENSE, (int) (this.attributes.get(Attribute.DEFENSE) + upValue), false);
            return true;
        }
        return false;
    }

    @Override
    public boolean dodge(int upValue) {
        if (capacities.contains("Bloquer")) {
            return true;
        }
        return false;
    }

   @Override
    public boolean useItem(UseableItem useableItem, int upValue) {
        if (capacities.contains("Utiliser un item")) {
            this.attributes.replace(useableItem.getAttributeBonus(), this.attributes.get(useableItem.getAttributeBonus()) + upValue, false);
            this.inventory.remove(useableItem); //delete
            return true;
        }
        return false;
    }

    @Override
    public String anger(Character opponent)
    {
        if(capacities.contains("Anger"))
        {
            if(this.attributes.get(Attribute.MANA)>=10)
            {
                int nbAlea = (int)(Math.random()*100);
                if(nbAlea<=this.getAttributeValue(Attribute.DEXTERITY))
                {
                    return "";
                }
                return "Attaque échouée";
            }
            return "Vous n'avez pas assez de mana";
        }
        return "Vous n'avez pas la compétence.";
    }

}
