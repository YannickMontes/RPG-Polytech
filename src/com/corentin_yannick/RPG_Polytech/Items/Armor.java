/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Items;

import com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign;
import java.util.List;

/**
 *
 * @author yannick
 */
public class Armor extends StuffItem {

    private int defenseValue;
    public static List<StuffItem> listeArmorItem;

    public Armor(String name, int weight, int handlingAbility, int defenseValue, int rarity, int requiredLevel) {
        super(name, weight, handlingAbility, rarity, requiredLevel);
        this.defenseValue = defenseValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    @Override
    public String toString() {
        return ConsoleDesign.text("Type: Armure\n", ConsoleDesign.magentaText)
                + super.toString()
                + ConsoleDesign.text("Defense: " + this.defenseValue, ConsoleDesign.magentaText)
                + "\n";
    }
}
