/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Items;

import com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.MAGENTA;
import java.util.List;

/**
 *
 * @author yannick
 */
public class Spell extends UseableItem {

    public static List<UseableItem> listSortilegeItem;
    private int manaCost;

    public Spell(String name, int weight, int bonusValue, int rarity, int manaCost) {
        super(name, weight, bonusValue, rarity);
        this.manaCost = manaCost;
    }

    public int getManaCost() {
        return this.manaCost;
    }

    @Override
    public String toString() {
        return ConsoleDesign.text("Type: Sortilège", MAGENTA) + "\n" +
                ConsoleDesign.text("Dégats: " + this.getValue() , MAGENTA) + "\n" +
                ConsoleDesign.text("Cout en mana: " + this.manaCost, MAGENTA) + "\n";
    }
}
