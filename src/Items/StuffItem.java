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

    public StuffItem(String name, int weight, int handlingAbility) {
        super(name, weight);
        this.handlingAbility = handlingAbility;
    }

    public int getHandlingAbility() {
        return handlingAbility;
    }

}
