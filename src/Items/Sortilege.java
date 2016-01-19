/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import Controller.ConsoleDesign;
import java.util.List;

/**
 *
 * @author yannick
 */
public class Sortilege extends UseableItem {

    public static List<UseableItem> listSortilegeItem;
    private int manaCost;

    public Sortilege(String name, int weight, int bonusValue, int rarity, int manaCost) {
        super(name, weight, bonusValue, rarity);
        this.manaCost = manaCost;
    }

    public int getManaCost() {
        return this.manaCost;
    }

    @Override
    public String toString() {
        return ConsoleDesign.text("Type: Sortilège", ConsoleDesign.magentaText) + "\n" +
                ConsoleDesign.text("Dégats: " + this.getValue() , ConsoleDesign.magentaText) + "\n" +
                ConsoleDesign.text("Cout en mana: " + this.manaCost, ConsoleDesign.magentaText) + "\n";
    }
}
