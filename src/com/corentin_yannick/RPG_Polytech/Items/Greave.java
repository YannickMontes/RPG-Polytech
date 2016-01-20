package com.corentin_yannick.RPG_Polytech.Items;

import com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign;
import java.util.List;

/**
 * Cette classe concerne les jambières d'un personnage. Elles sont comptées
 * comme une armure.
 *
 * @author yannick
 */
public class Greave extends StuffItem {

    public static List<StuffItem> listGreaveItem;
    private int defenseValue; // Caractérise la défense apportée par l'équipement.

    /**
     * Constructeur de base d'un item de type jambière.
     *
     * @param name Nom de l'item
     * @param weight Poids de l'item
     * @param handlingAbility Maniabilité de l'item
     * @param defenseValue Valeur de défense octroyée par l'item
     * @param rarity Rareté de l'item
     * @param requiredLevel Niveau requis pour l'équiper
     */
    public Greave(String name, int weight, int handlingAbility, int defenseValue, int rarity, int requiredLevel) {
        super(name, weight, handlingAbility, rarity, requiredLevel);
        this.defenseValue = defenseValue;
    }

    /**
     * Permet d'obtenir la valeur de défense de l'armure.
     *
     * @return La défense qu'octroie l'armure, en entier positif.
     */
    public int getDefenseValue() {
        return this.defenseValue;
    }

    @Override
    public String toString() {
        return ConsoleDesign.text("Type: Jambières\n", ConsoleDesign.magentaText)
                + super.toString()
                + ConsoleDesign.text("Defense: " + this.defenseValue, ConsoleDesign.magentaText)
                + "\n";
    }
}
