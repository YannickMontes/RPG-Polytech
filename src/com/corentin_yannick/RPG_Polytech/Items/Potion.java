/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Items;

import com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.MAGENTA;
import com.corentin_yannick.RPG_Polytech.Entities.Attribute;
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
        return ConsoleDesign.text("Type: Potion", MAGENTA) + "\n" +
                ConsoleDesign.text("Attribut augmenté: " + this.attributeBonus , MAGENTA) + "\n" +
                ConsoleDesign.text("Bonus: " + this.getValue(), MAGENTA) + "\n";
    }
}
