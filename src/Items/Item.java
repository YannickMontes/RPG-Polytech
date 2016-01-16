/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import Controller.ConsoleDesign;
import Entities.Attribute;

/**
 *
 * @author yannick
 */
public abstract class Item {

    private String name;
    private int weight;
    private Rarity rarity;

    public Item(String name, int weight, int r) {
        this.name = name;
        this.weight = weight;
        this.rarity = Rarity.values()[r];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return ConsoleDesign.text("Nom: " + this.name, ConsoleDesign.magentaText)
                + "\n"
                + ConsoleDesign.text("Poids: " + this.weight, ConsoleDesign.magentaText)
                + "\n"
                + ConsoleDesign.text("Rareté: " + this.rarity, ConsoleDesign.magentaText)
                + "\n";
    }
}
