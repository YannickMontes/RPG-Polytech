/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

/**
 *
 * @author yannick
 */
public abstract class Item 
{
    private String name;
    private int weight;
    
    public Item(String name, int weight)
    {
        this.name = name;
        this.weight = weight;
    }
}
