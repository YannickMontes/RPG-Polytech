/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Actions.Skill;
import Items.Armor;
import Items.Item;
import Items.StuffItem;
import Items.UseableItem;
import Items.Weapon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yannick
 */
public abstract class Character {

    //Fixed variables
    public final int MAXHEALTH = 100;
    public final int MAXDEXTERITY = 100;
    public final int MAXARMOREQUIPMENT = 2;
    public final int MAXWEAPONEQUIPMENT = 1;
    public  final int MAXEQUIPMENT = 3;

    //Attributes
    protected String name;
    protected Map<Attribute, Integer> attributes;
    protected int level;
    protected int maxWeight;
    protected List<Item> inventory;
    protected List<StuffItem> equipment;

    //Constructors
    public Character(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.attributes = new HashMap<>();
    }

    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Attribute, Integer> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<Attribute, Integer> characs) {
        this.attributes = attributes;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public List<StuffItem> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<StuffItem> equipment) {
        this.equipment = equipment;
    }
   
    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    protected int inventoryWeight() {
        int weight = 0;
        for (Item item : inventory) {
            weight += item.getWeight();
        }
        return weight;
    }

    public boolean addInventory(Item item) {
        if (item.getWeight() + inventoryWeight() <= maxWeight) {
            this.inventory.add(item);
            return true;
        }
        return false;
    }

    public void useUseableItem(UseableItem useableItem) {
        if (this.inventory.contains(useableItem)) {
            this.inventory.remove(useableItem);
            // traitement
        }
    }

    private int numberArmorEquipment() {
        int i = 0;
        for (StuffItem stuffItem : equipment) {
            if (stuffItem.getClass() == Armor.class) {
                i++;
            }
        }
        return i;
    }

    private int numberWeaponEquipment() {
        for (StuffItem stuffItem : equipment) {
            if (stuffItem.getClass() == Weapon.class) {
                return 1;
            }
        }
        return 0;
    }

    public void equipMe(StuffItem stuffItem) {
        if(equipment.size()<=MAXEQUIPMENT){
            if((stuffItem.getClass() == Weapon.class && numberArmorEquipment()<=MAXWEAPONEQUIPMENT)||(stuffItem.getClass() == Armor.class && numberArmorEquipment()<=MAXARMOREQUIPMENT)){
                equipment.add(stuffItem);
            }
        }
    }

    public void equipMe(StuffItem newStuffItem, StuffItem removeStuffItem) {

    }
}
