package Entities;

/**
 * Classe utilisée pour le niveau d'un personnage. Elle contient les méthodes
 * nécéssaires au traitement de l'expérience. Le niveau max est défini en dur.
 * @author yannick
 */
public class Level
{
    public final static int MAX_LEVEL = 99; // Constante définissant le niveau maximum, accessible de partout.
    
    private int level; //Caractérise le niveau actuel.
    private int actualExp; //Caractérise l'expérience du niveau actuel.
    private int requiredExp; //Caractérise l'expérience requise pour passer au niveau supérieur.
    
    /**
     * Constructeur de base de la classe Level. Initialise le niveau à 1.
     */
    public Level()
    {
        this(1);
    }
    
    /**
     * Constructeur permettant d'initialiser le niveau à value.
     * @param value Valeur du niveau voulue.
     */
    public Level(int value)
    {
        this.level = value;
        this.calculateRequiredExp();
    }
    
    /**
     * Permet d'obtenir la valeur du niveau actuel.
     * @return Le niveau actuel sous forme d'entier.
     */
    public int getLevel()
    {
        return this.level;
    }
    
    /**
     * Permet d'obtenir l'expérience actuelle.
     * @return L'expérience actuelle sous forme d'entier. 
     */
    public int getActualExperience()
    {
        return this.actualExp;
    }
    
    /**
     * Permet d'obtenir l'expérience requise pour passer au palier supérieur.
     * @return L'expérience requise sous forme d'entier. 
     */
    public int getRequiredExperience()
    {
        return this.requiredExp;
    }        
    
    /**
     * Fonction permettant d'ajouter de l'expérience. 
     * Si le joueur est au niveau max, impossible d'ajouter de l'expérience.
     * @param value Valeur à ajouter
     * @return Vrai s'il y a passage au niveau supérieur, faux sinon.
     */
    public boolean increaseExp(int value)
    {
        if(this.level<MAX_LEVEL)
        {
            if(value > 0)
            {
                this.actualExp += value;
                return this.checkExp();   
            }   
        }
        return false;
    }
    
    /**
     * Fonction interne. Elle permet de calculer l'expérience requise pour passer au niveau supérieur.
     */
    private void calculateRequiredExp()
    {
        if(this.level < MAX_LEVEL - 1) // Si le niveau est inférieur au niveau max, et au dernier niveau.
        {
            this.requiredExp = this.level * 40 + 35;
        }
        else if(this.level == MAX_LEVEL - 1) // Si le niveau est égal au niveau max -1 (dernier palier a franchir)
        {
            this.requiredExp = 0;
            for(int i=0; i < MAX_LEVEL; i++)
            {
                this.requiredExp += i*40+35;
            }
        }
        else // Si le niveau est maximum.
        {
            this.requiredExp = 0;
        }
    }
    
    /**
     * Fonction interne. Elle vérifie si la valeur de l'expérience actuelle est supérieure ou égale
     * à la valeur de l'expérience requise. Si tel est le cas, elle augmente d'un le niveau,
     * met à jour en conséquence l'expérience actuelle, et recalcule l'expérience nécéssaire.
     * @return Vrai si le niveau augmente, Faux sinon.
     */
    private boolean checkExp()
    {
        if(this.actualExp >= requiredExp)
        {
            this.level++;
            this.actualExp = this.actualExp - this.requiredExp;
            this.calculateRequiredExp();
            return true;
        }
        return false;
    }
}
