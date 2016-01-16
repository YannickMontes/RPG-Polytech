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
public class UseableItem extends Item
{
    private int bonusValue;
    
    public UseableItem(String name, int weight, int bonusValue, int r) {
        super(name, weight,r);
        this.bonusValue=bonusValue;
    }

    public int getBonusValue() {
        return bonusValue;
    }
    
    
}
