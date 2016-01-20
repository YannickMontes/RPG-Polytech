/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Items;

import com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign;
import java.util.ArrayList;
import java.util.List;

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

    public int getRequiredLevel() {
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

    public static StuffItem getRandomItemInList(Rarity rarity, int level, List<StuffItem> list) {
        List<StuffItem> possibilities = new ArrayList<>();
        for (StuffItem a : list) {
            if ((a.getRequiredLevel() <= level && a.getRequiredLevel() > level - 5) && (a.getRarity() == rarity)) {
                possibilities.add(a);
            }
        }
        if (possibilities.isEmpty()) {
            int randomValue = (int) (Math.random() * list.size());
            return list.get(randomValue);
        }
        int randomValue = (int) (Math.random() * possibilities.size());

        return possibilities.get(randomValue);
    }
}
