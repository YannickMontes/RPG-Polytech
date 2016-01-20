/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Items;

import com.corentin_yannick.RPG_Polytech.Entities.Attribute;
import java.util.List;

/**
 *
 * @author yannick
 */
public class UseableItem extends Item {

    public static List<UseableItem> listUseableItem;
    protected final int value;
    protected Attribute attributeBonus;

    public UseableItem(String name, int weight, int bonusValue, int r) {
        super(name, weight, r);
        this.value = bonusValue;
    }

    public int getValue() {
        return value;
    }

    public Attribute getAttributeBonus() {
        return attributeBonus;
    }

    public static UseableItem getRandomItem(List<UseableItem> liste) {
        return liste.get((int) (Math.random() * liste.size()));
    }
}
