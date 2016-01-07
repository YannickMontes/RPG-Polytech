/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Items.Armor;
import Items.Item;
import Items.StuffItem;
import Items.UseableItem;
import Items.Weapon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yannick
 */
public abstract class Character {

    //Fixed variables
    public final static int MAXARMOREQUIPMENT = 2;
    public final static int MAXWEAPONEQUIPMENT = 1;
    public final static int MAXEQUIPMENT = 3;
    public final static int NBCLASSES = 3;
    public final static int NBPOINTLEVELUP = 3;

    //Attributesattributes
    protected String name;
    protected String className;
    protected Attributes attributes;
    protected Attributes basicAttributes;
    protected int level;
    protected int maxWeight;
    protected List<Item> inventory;
    protected List<StuffItem> equipment;
    protected List<String> capacities;

    //Constructors
    public Character(String name, String className) {
        this.name = name;
        this.level = 1;
        this.className = className;
        this.inventory = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.capacities = new ArrayList<>();
        this.attributes = new Attributes();
        this.basicAttributes = new Attributes();
    }

    /**
     * Ce constructeur permet de créer un personnage équilibré selon sa classe,
     * à partir d'un nom et d'un niveau.
     *
     * @param n Nom du pesonnage
     * @param l Niveau du personnage
     */
    public Character(String n, int l) {
        this.level = l;
        this.name = n;
        int nbPoints = (level - 1) * NBPOINTLEVELUP;
        while (nbPoints > 0) {
            int randomNumber = (int) (Math.random() * Attribute.values().length - 1);
            putRandomPoint();
            nbPoints -= NBPOINTLEVELUP;
        }
    }

    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attributes getAttributes() {
        return attributes;
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

    public List<String> getCapacities() {
        return capacities;
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

    public boolean isAlive() {
        if (this.attributes.get(Attribute.HEALTH) <= 0) {
            return false;
        }
        return true;
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

    public int numberUseableItem() {
        int i = 0;
        for (Item item : inventory) {
            if (item instanceof UseableItem) {
                return i++;
            }
        }
        return i;
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
    public void takeABlow(int value) {
        this.attributes.replace(Attribute.HEALTH, this.attributes.get(Attribute.HEALTH) - value);
    }

    //Function to use capacity
    public boolean verifySuccess(String capacity) {
        //selon la capacitié utilisée: selon ses caractéristiques et son arme (s'il en a)
        //calcule de proba
        float probability = 0;
        if ("attack".equals(capacity)) {
            for (StuffItem weapon : this.getEquipment(Weapon.class)) {
                probability += (float) (((Weapon) weapon).getHandlingAbility() / 100) * 0.2; // entre 0 et 100
            }
            probability += (float) (this.attributes.get(Attribute.DEXTERITY).floatValue() / 100) * (1 - (0.2 * this.getEquipment(Weapon.class).size()));
        } else if ("block".equals(capacity)) {
            //probability = this.attributes.get(Attribute.DEFENSE);
            for (StuffItem armor : this.getEquipment(Armor.class)) {
                probability += (float) (((Armor) armor).getHandlingAbility() / 100) * 0.2; // entre 0 et 100
            }
            probability += (float) (this.attributes.get(Attribute.DEFENSE).floatValue() / 100) * (1 - (0.2 * this.getEquipment(Armor.class).size()));
        }else if ("dodge".equals(capacity)) {
            //probability = this.attributes.get(Attribute.SPEED);
            for (StuffItem armor : this.getEquipment(Armor.class)) {
                probability += (float) (((Armor) armor).getHandlingAbility() / 100) * 0.2; // entre 0 et 100
            }
            probability += (float) (this.attributes.get(Attribute.SPEED).floatValue() / 100) * (1 - (0.2 * this.getEquipment(Armor.class).size()));
        } else if ("care".equals(capacity)) {
            probability = 1;
        }
        probability = 1;

        int rand = (int) (Math.random() * (100));
        if (rand <= (probability * 100)) {
            return true;
        }
        return false;
    }

    public int measureImpact(String capacity, Character opponent, UseableItem useableItem) {
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
        if ("attack".equals(capacity) && opponent != null) {
            int damage = this.attributes.get(Attribute.STRENGTH);
            for (StuffItem weapon : this.getEquipment(Weapon.class)) {
                damage += ((Weapon) weapon).getDamage();
            }

            int defense = opponent.attributes.get(Attribute.DEFENSE);
            for (StuffItem armor : opponent.getEquipment(Armor.class)) {
                defense += ((Armor) armor).getDefenseValue();
            }

            int damages = damage - defense;
            if (damages <= 0) {
                return 0;
            }
            return damages;
        } else if ("block".equals(capacity)) {
            this.attributes.replace(Attribute.DEFENSE, (int) (this.attributes.get(Attribute.DEFENSE) + 0.5 * this.attributes.get(Attribute.STRENGTH)));
            return (int) (0.5 * this.attributes.get(Attribute.STRENGTH));
        } else if ("care".equals(capacity) && useableItem != null) {
            return useableItem.getBonusValue();
        }
        return 0;
    }

    public void restoreLife() {
        this.attributes.replace(Attribute.HEALTH, 150 + 2 * level + 3);
    }

    public void restoreAttribute(Attribute attribute) {
        attributes.replace(attribute, basicAttributes.get(attribute));
    }

    public void restoreAttributes() {
        restoreAttribute(Attribute.SPEED);
        restoreAttribute(Attribute.DEFENSE);
        restoreAttribute(Attribute.DEXTERITY);
        restoreAttribute(Attribute.STRENGTH);
    }

    public void showData() {
        System.out.println("--------------------");
        System.out.println("DONNEES DE " + this.name);
        System.out.println("Classe: " + this.className);
        System.out.println("Niveau: " + this.level);
        System.out.println("Niveau de santé: " + this.attributes.get(Attribute.HEALTH));
        System.out.println("Niveau de dextérité: " + this.attributes.get(Attribute.DEXTERITY));
        System.out.println("Niveau de défense: " + this.attributes.get(Attribute.DEFENSE));
        System.out.println("Niveau de vitesse: " + this.attributes.get(Attribute.SPEED));
        System.out.println("Niveau de force: " + this.attributes.get(Attribute.STRENGTH));
        System.out.println("Nombre d'objet dans l'inventaire: " + this.inventory.size());
        System.out.println("Equipement actuel: ");
        for (int i = 0; i < equipment.size(); i++) {
            System.out.println("-" + equipment.get(i).getName());
        }
    }

    public String attackResult(boolean success, Character opponent, int damages) {
        if (success == true) {
            return "Vous avez infligé " + damages + " de dégâts à " + opponent.getName() + " (Santé: " + opponent.getAttributes().get(Attribute.HEALTH) + ")";
        }
        return "Votre attaque sur " + opponent.getName() + " a échoué";
    }

    public String careResult(boolean success, int care) {
        if (success == true) {
            return "Vous avez augmenté de " + care + " votre vie " + " (Santé: " + this.getAttributes().get(Attribute.HEALTH) + ")";
        }
        return "Votre soin a échoué";
    }

    public String blockResult(boolean success, int blockValue) {
        if (success == true) {
            return "Vous avez augmenté de " + blockValue + " votre défense " + " (Défense: " + this.getAttributes().get(Attribute.DEFENSE) + ")";
        }
        return "Votre blocage a échoué";
    }

    public void putRandomPoint() {
    }
;
}
