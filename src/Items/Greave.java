package Items;

/**
 * Cette classe concerne les jambières d'un personnage. 
 * Elles sont comptées comme une armure.
 * @author yannick
 */
public class Greave extends StuffItem
{
    private int defenseValue; // Caractérise la défense apportée par l'équipement.
    
    /**
     * Constructeur de base d'un item de type jambière.
     * @param name Nom de l'item
     * @param weight Poids de l'item
     * @param handlingAbility Maniabilité de l'item
     * @param defenseValue Valeur de défense octroyée par l'item
     * @param r Rareté de l'item
     */
    public Greave(String name, int weight, int handlingAbility, int defenseValue, int r)
    {
        super(name, weight, handlingAbility, r);
        this.defenseValue = defenseValue;
    }
    
    /**
     * Permet d'obtenir la valeur de défense de l'armure.
     * @return La défense qu'octroie l'armure, en entier positif.
     */
    public int getDefenseValue()
    {
        return this.defenseValue;
    }
}
