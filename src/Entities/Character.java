/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Actions.Attack;
import Actions.Block;
import Actions.Capacity;
import Items.Armor;
import Items.Item;
import Items.StuffItem;
import Items.UseableItem;
import Items.Weapon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Actions.Care;

/**
 *
 * @author yannick
 */
public abstract class Character {

    //Fixed variables
    public final int MAXHEALTH = 100;
    public final int MAXDEXTERITY = 100;
    public final int MAXDEFENSE = 100;
    public final int MAXSTRENGTH = 100;
    public final int MAXSPEED = 100;

    public final int MAXARMOREQUIPMENT = 2;
    public final int MAXWEAPONEQUIPMENT = 1;
    public final int MAXEQUIPMENT = 3;

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
            // traitement
            // utilisation = influence sur caratéristiques
            // utilisation potion -> augmentation santé perso
            this.inventory.remove(useableItem);
        }
    }

    /*  **
     *
     * To use Armor in the equipment against an opponent
     *
     * @param armor
     * @param opponent
     *
    public void useArmor(Armor armor, Character opponent) {
        if (this.equipment.contains(armor)) {
            // traitement
            // utilisation = influence sur caratéristiques

        }
    }

    **
     *
     * To use a Weapon in the equipment against an opponent
     *
     * @param weapon
     * @param opponent
     *
    public void useWeapon(Weapon weapon, Character opponent) {
        if (this.equipment.contains(weapon)) {
            // traitement
            // utilisation = influence sur caratéristiques
            // utilisation arme -> diminution santé adversaire
        }
    }
     */
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

    public ArrayList<StuffItem> getEquipment(Class itemType) {
        ArrayList<StuffItem> items = new ArrayList<>();
        for (StuffItem stuffItem : equipment) {
            if (stuffItem.getClass() == itemType) {
                items.add(stuffItem);
            }
        }
        return items;
    }

    /**
     *
     * Equip character with Armor or Weapon
     *
     * @param stuffItem
     * @return
     */
    public boolean equipMe(StuffItem stuffItem) {
        if (equipment.size() < MAXEQUIPMENT && inventory.contains(stuffItem)) { //if character owns stuff item and his equipment contains less than MAXEQUIPMENT stuff items
            if ((stuffItem.getClass() == Weapon.class && numberWeaponEquipment() < MAXWEAPONEQUIPMENT) || (stuffItem.getClass() == Armor.class && numberArmorEquipment() < MAXARMOREQUIPMENT)) {
                //if number of weapon in equiment is less than MAXWEAPONEQUIPMENT and the stuff item is a weapon 
                //OR if number of armor in equiment is less than MAXARMOREQUIPMENT and the stuff item is an armor 
                equipment.add(stuffItem);
                return true;
            }
        }
        return false;
    }

    /**
     *
     * Equip character with Armor or Weapon and replace an other Stuff
     *
     * @param newStuffItem
     * @param removeStuffItem
     * @return
     */
    public boolean equipMe(StuffItem newStuffItem, StuffItem removeStuffItem) {
        if (equipment.size() <= MAXEQUIPMENT && inventory.contains(newStuffItem) && equipment.contains(removeStuffItem)) { //if character owns the new stuff item and his equipment contains the replacement stuff item and his equipment contains less than MAXEQUIPMENT stuff items
            equipment.remove(removeStuffItem);
            if (equipMe(newStuffItem) == true) {
                return true;
            } else {
                equipment.add(removeStuffItem);
            }
        }
        return false;
    }
    
    /**
     *
     * Take a blow after an attack
     * 
     * @param value
     */
    public void takeABlow(int value)
    {
        this.attributes.replace(Attribute.HEALTH, this.attributes.get(Attribute.HEALTH)-value);
    }

    //Function to use capacity
    public int verifySuccess(Capacity capacity) {
        //selon la capacitié utilisée: selon ses caractéristiques et son arme (s'il en a)
        //calcule de proba
        int probability=0;
        if (capacity.getClass() == Attack.class) {
            probability=this.attributes.get(Attribute.DEXTERITY);
            for (StuffItem weapon : this.getEquipment(Weapon.class)) {
                probability += ((Weapon) weapon).getHandlingAbility();
            }
        } else if (capacity.getClass() == Block.class) {
            probability=this.attributes.get(Attribute.DEFENSE);
            for (StuffItem armor : this.getEquipment(Armor.class)) {
                probability += ((Armor) armor).getHandlingAbility();
            }
        } else if (capacity.getClass() == Care.class) {
            probability=1;
        }
        return probability;
    }

    public int measureImpact(Capacity capacity, Character opponent) {
        //selon la capacité utilisisée
        //// ATTAQUE
        //// - calculer les dégâts occasionnés (somme force attaquant et valeur degat arme s'il en a)
        //// - calculer la défense de l'adversaire (somme de sa valeur de défense et des armures s'il en a
        //// - calculer le dommage: degat - defense
        //// - soustraire impacte attaque sur la santé de l'adversaire
        //// PARADE
        //// - augmente défense s'il est attaqué
        //// SOIN
        //// - augmente santé d'une valeur de la santé en fonction de la capacité de soin de ses équipements.
        if (capacity.getClass() == Attack.class) {
            int damage = this.attributes.get(Attribute.STRENGTH);
            for (StuffItem weapon : this.getEquipment(Weapon.class)) {
                damage += ((Weapon) weapon).getDamage();
            }

            int defense = opponent.attributes.get(Attribute.DEFENSE);
            for (StuffItem armor : opponent.getEquipment(Armor.class)) {
                defense += ((Armor) armor).getDefenseValue();
            }
            
            int damages = damage-defense;
            
            return damages;
            
        } else if (capacity.getClass() == Block.class) {
            
        } else if (capacity.getClass() == Care.class) {
            int care=0; // passer l'objet de soin en parametre
            return care;
        }
        return 0;
    }

}
