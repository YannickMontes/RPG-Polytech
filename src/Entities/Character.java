/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Items.Item;
import Items.Stuff;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yannick
 */
public abstract class Character
{

    //Fixed variables
    public final int MAXHEALTH = 100;
    public final int MAXDEXTERITY = 100;

    //Attributes
    private String name;
    private Map<Attribute, Integer> stats;
    private int level;
    private int maxWeight;
    private Stuff stuff;
    private List<Item> inventory;

    //Constructors
    public Character(String name)
    {
        this.name = name;
    }
    
    //Getters & Setters
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Map<Attribute, Integer> getStats()
    {
        return stats;
    }

    public void setStats(Map<Attribute, Integer> stats)
    {
        this.stats = stats;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getMaxWeight()
    {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight)
    {
        this.maxWeight = maxWeight;
    }

    public Stuff getStuff()
    {
        return stuff;
    }

    public void setStuff(Stuff stuff)
    {
        this.stuff = stuff;
    }

    public List<Item> getInventory()
    {
        return inventory;
    }

    public void setInventory(List<Item> inventory)
    {
        this.inventory = inventory;
    }

    
}
