/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

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
        return handlingAbility;
    }

    @Override
    public String toString()
    {
        String text = super.toString();
        
        text +="Maniabilité: "+this.handlingAbility+"\n";
        text +="Niveau requis: "+this.requiredLevel+"\n";
        
        return text;
    }

    
}
