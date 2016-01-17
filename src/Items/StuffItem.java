/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import Controller.ConsoleDesign;

/**
 *
 * @author yannick
 */
public abstract class StuffItem extends Item {

    private int handlingAbility;
    private int requiredLevel;

    public StuffItem(String name, int weight, int handlingAbility, int rarity, int requiredLevel) {
        super(name, weight, rarity);
        this.handlingAbility = handlingAbility;
        this.requiredLevel = requiredLevel;
    }

    public int getHandlingAbility() {
        return this.handlingAbility;
    }
    
    public int getRequiredLevel()
    {
        return this.requiredLevel;
    }

    @Override
    public String toString() {

        return super.toString()
                + ConsoleDesign.text("Maniabilité: " + this.handlingAbility, ConsoleDesign.magentaText)
                + "\n"
                + ConsoleDesign.text("Niveau requis: " + this.requiredLevel, ConsoleDesign.magentaText)
                + "\n";

    }

}
