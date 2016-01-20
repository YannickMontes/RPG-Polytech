/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import Controller.ConsoleDesign;
import Entities.Attribute;
import java.util.List;

/**
 *
 * @author yannick
 */
public class Potion extends UseableItem {

    public static List<UseableItem> listPotionItem;

    public Potion(String name, int weight, int bonusValue, int r, Attribute att) {
        super(name, weight, bonusValue, r);
        this.attributeBonus = att;
    }

    @Override
    public String toString() {
        return ConsoleDesign.text("Type: Potion", ConsoleDesign.magentaText) + "\n" +
                ConsoleDesign.text("Attribut augmenté:: " + this.attributeBonus , ConsoleDesign.magentaText) + "\n" +
                ConsoleDesign.text("Bonus: " + this.getValue(), ConsoleDesign.magentaText) + "\n";
    }
}
