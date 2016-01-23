/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Items;

import com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.MAGENTA;

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

    public Rarity getRarity() {
        return this.rarity;
    }

    @Override
    public String toString() {
        return ConsoleDesign.text("Nom: " + this.name, MAGENTA)
                + "\n"
                + ConsoleDesign.text("Poids: " + this.weight, MAGENTA)
                + "\n"
                + ConsoleDesign.text("Rareté: " + this.rarity, MAGENTA)
                + "\n";
    }
}
