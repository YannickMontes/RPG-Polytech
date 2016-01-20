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
public class Weapon extends StuffItem {

    public static List<StuffItem> listWeaponItem;
    private int damage;

    public Weapon(String name, int weight, int handlingAbility, int damage, int rarity, int requiredLevel) {
        super(name, weight, handlingAbility, rarity, requiredLevel);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return ConsoleDesign.text("Type: Arme\n", ConsoleDesign.magentaText)
                + super.toString()
                + ConsoleDesign.text("Dommages: " + this.damage, ConsoleDesign.magentaText)
                + "\n";
    }

}