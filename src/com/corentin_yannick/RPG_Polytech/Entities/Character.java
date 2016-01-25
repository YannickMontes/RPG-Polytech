package com.corentin_yannick.RPG_Polytech.Entities;

import com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign;
import com.corentin_yannick.RPG_Polytech.Items.Armor;
import com.corentin_yannick.RPG_Polytech.Items.Item;
import com.corentin_yannick.RPG_Polytech.Items.StuffItem;
import com.corentin_yannick.RPG_Polytech.Items.UseableItem;
import com.corentin_yannick.RPG_Polytech.Items.Weapon;
import java.util.ArrayList;
import java.util.List;
import com.corentin_yannick.RPG_Polytech.Controllers.Controller;
import com.corentin_yannick.RPG_Polytech.Items.Greave;
import com.corentin_yannick.RPG_Polytech.Items.Potion;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.RED;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.GREEN;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.CYAN;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.YELLOW;
import com.corentin_yannick.RPG_Polytech.Items.Rarity;

/**
 * Cette classe abstraite contient toutes les informations nécéssaires pour un
 * personnage du jeu.
 *
 * @author Yannick Montes & Corentin Maréchal
 */
public abstract class Character {

    //Variables de limite fixées au préalable.
    public final static int MAXARMOREQUIPMENT = 1;
    public final static int MAXGREAVEEQUIPMENT = 1;
    public final static int MAXWEAPONEQUIPMENT = 1;
    public final static int MAXEQUIPMENT = 3;
    public final static int NBCLASSES = 3;
    public final static int NBPOINTLEVELUP = 3;

    //Variables d'instances
    /**
     * Correspond au nom du personnage
     */
    protected String name;
    /**
     * Variable contenant le nom de la classe du personnage
     */
    protected String className;
    /**
     * Map<Clé,Valeur> contenant les attributs du personnage
     * (tout compris)
     */
    protected Attributes attributes;
    /**
     * Map<Clé,Valeur> contenant les attributs de base du personnage.
     * L'équipement ainsi que les buffs et autre n'influent pas sur ces attributs.
     * Ces attributs sont régis par des contraintes.
     */
    protected Attributes basicAttributes;
    /**
     * Variable contenant le level du personnage.
     * Permet également de connaitre son expérience actuelle, et l'exp nécéssaire
     * pour passer au niveau supérieur.
     */
    protected Level level;
    /**
     * Attribut permettant de savoir la capacité maximum que peut transporter le 
     * personnage.
     */
    protected int maxWeight;
    /**
     * Liste contenant tous les objets du personnage. Cette liste contient 
     * également les équipements actuels. 
     * La somme totale des poids de chaque item ne peut pas excéder l'attribut
     * maxWeight du personnage.
     */
    protected List<Item> inventory;
    /**
     * Liste contenant les items actuellement équipés du personnage. Le poids des
     * Chaque item de cette liste est également présent dans l'inventaire.
     */
    protected List<StuffItem> equipment;
    /**
     * Liste de string contenant les capacités de chaque personnages.
     */
    protected List<String> capacities;

    /*
        CONSTRUCTEURS
     */
    
    /**
     * Constructeur basique de la classe personnage. Initialise un personnage au
     * niveau 1, avec le stuff de départ.
     *
     * @param name Nom du personnage
     * @param className Nom de la classe du personnage
     */
    public Character(String name, String className) {
        this.name = name;
        this.level = new Level();
        this.className = className;
        this.initVars();
        this.initStats();
        this.calculateMaxWeight();
        this.initStuff();
    }

    /**
     * Ce constructeur permet de créer un personnage équilibré selon sa classe,
     * à partir d'un nom et d'un niveau.
     *
     * @param n Nom du pesonnage
     * @param l Niveau du personnage
     * @param className Nom de la classe du personnage
     */
    public Character(String n, int l, String className) {
        this.className = className;
        this.level = new Level(l);
        this.name = n;

        this.initVars();
        this.initStats();
        this.calculateMaxWeight();

        int nbPoints = (level.getLevel() - 1) * NBPOINTLEVELUP;
        int lvl = level.getLevel();
        while (nbPoints > 0) {
            putRandomPoint(lvl);
            nbPoints -= NBPOINTLEVELUP;
            lvl--;
        }
        this.initStuff();
    }

    /*
        FONCTIONS D'INITIALISATION
     */
    /**
     * Fonction permettant d'initialiser les variables de la classe.
     */
    private void initVars() {
        this.inventory = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.capacities = new ArrayList<>();
        this.attributes = new Attributes();
        this.basicAttributes = new Attributes();
    }

    /**
     * Fonction permettant d'initialiser un stuff aléatoire en fonction des
     * items existants. Si le niveau du joueur est de 1, le stuff généré est le
     * stuff de départ.
     */
    private void initStuff() {
        if (this.level.getLevel() == 1) {
            this.addEquipement(Greave.listGreaveItem.get(0));
            this.addEquipement(Armor.listeArmorItem.get(0));
            this.addEquipement(Weapon.listWeaponItem.get(0));
        } else {
            List<StuffItem> listPossibleArmor = new ArrayList<>();
            List<StuffItem> listPossibleWeapon = new ArrayList<>();
            List<StuffItem> listPossibleGreave = new ArrayList<>();
            for (StuffItem armor : Armor.listeArmorItem) {
                if (armor.getRequiredLevel() <= this.level.getLevel() && armor.getRequiredLevel() > this.level.getLevel() - 5) {
                    listPossibleArmor.add(armor);
                }
            }
            for (StuffItem weapon : Weapon.listWeaponItem) {
                if (weapon.getRequiredLevel() <= this.level.getLevel() && weapon.getRequiredLevel() > this.level.getLevel() - 5) {
                    listPossibleWeapon.add(weapon);
                }
            }
            for (StuffItem greave : Greave.listGreaveItem) {
                if (greave.getRequiredLevel() <= this.level.getLevel() && greave.getRequiredLevel() > this.level.getLevel() - 5) {
                    listPossibleGreave.add(greave);
                }
            }
            int nbAlea;
            if(listPossibleArmor.isEmpty())
            {
                this.addEquipement(Armor.getRandomItemInList(Rarity.COMMON, this.getLevel(), Armor.listeArmorItem));
            }
            else
            {
                nbAlea = (int) (Math.random() * listPossibleArmor.size());
                this.addEquipement(listPossibleArmor.get(nbAlea));
            }
            if(listPossibleGreave.isEmpty())
            {
                 this.addEquipement(Greave.getRandomItemInList(Rarity.COMMON, this.getLevel(), Greave.listGreaveItem));
            }
            else
            {
                nbAlea = (int) (Math.random() * listPossibleGreave.size());
                this.addEquipement(listPossibleGreave.get(nbAlea));
            }
            if(listPossibleWeapon.isEmpty())
            {
                 this.addEquipement(Weapon.getRandomItemInList(Rarity.COMMON, this.getLevel(), Weapon.listWeaponItem));
            }
            else
            {
                nbAlea = (int) (Math.random() * listPossibleWeapon.size());
                this.addEquipement(listPossibleWeapon.get(nbAlea));
            }
            this.addRandomItems();
        }
        this.inventory.add(Potion.listPotionItem.get(0));
        this.inventory.add(Potion.listPotionItem.get(0));
        this.reinitStats();
    }

    /**
     * Fonction permettant d'initialiser les stats du personnage.
     * A implementer dans les sous-classes.
     */
    public abstract void initStats();

    /*
        GETTERS & SETTERS
     */
    public String getName() {
        return name;
    }

    public int getAttributeValue(Attribute at)
    {
        return this.attributes.get(at);
    }
    
    public int getBasicAttributeValue(Attribute at)
    {
        return this.basicAttributes.get(at);
    }

    public Attributes getBasicAttributesCHEAT() {
        return basicAttributes;
    }

    public int getLevel() {
        return level.getLevel();
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
    
    /**
     * Permet d'obtenir le nombre d'item équipable total.
     * @return Le nombre d'item équipable total
     */
    public int getNbEquipableItem()
    {
        int nb = 0;
        for(Item i : this.inventory)
        {
            if(i instanceof StuffItem)
            {
                if(((StuffItem)(i)).getRequiredLevel()<=this.getLevel())
                {
                    nb++;
                }
            }
        }
        return nb;
    }
    
    /**
     * Permet d'obtenir le nombre d'item équipables d'un certain type. 
     * Les items contenu dans l'équipement ne sont pas comptabilisés. 
     * @param clas  Le type d'item voulu
     * @return Le nombre d'items équipables du type voulu
     */
    public int getNbEquipableItem(Class clas)
    {
        int nb = 0;
        for(Item i : this.inventory)
        {
            if(i.getClass() == clas)
            {
                if(((StuffItem)(i)).getRequiredLevel()<=this.getLevel())
                {
                    nb++;
                }
            }
        }
        return nb;
    }
    
    /**
     * Permet de savoir si l'item passé en paramètres est dans l'inventaire.
     * @param i Item dont on veut connaitre l'existence dans l'inventaire
     * @return Vrai si l'inventaire le contient, faux sinon
     */
    public boolean inventoryContains(Item i)
    {
        return (this.inventory.contains(i));
    }
    
    /**
     * Permet d'obtenir le poids total de l'équipement actuel du personnage.
     * @return le poids total.
     */
    public int getTotalWeightEquipment()
    {
        int sum = 0;
        for(Item i : this.equipment)
        {
            sum += i.getWeight();
        }
        return sum;
    }
    
    /**
     * Fonction permettant d'obtenir le poids total que prennent les objets
     * dans l'inventaire.
     * @return La poids total, sous forme d'entier
     */
    protected int inventoryWeight() {
        int weight = 0;
        for (Item item : inventory) {
            weight += item.getWeight();
        }
        return weight;
    }

    /**
     * Fonction permettant de vérifier si le personnage est vivant.
     *
     * @return Vrai si les pvs sont >0, Faux sinon.
     */
    public boolean isAlive() {
        if (this.attributes.get(Attribute.HEALTH) <= 0) {
            return false;
        }
        return true;
    }

    /**
     * Fonction calculant le nombre d'items de la classe passé en paramètre,
     * dans l'équipement du personnage
     *
     * @param c Classe dont on veut connaitre le nombre d'équipements
     * @return Le nombre d'équipement de classe c
     */
    private int numberOfClassEquipment(Class c) {
        int i = 0;
        for (StuffItem stuffItem : equipment) {
            if (stuffItem.getClass() == c) {
                i++;
            }
        }
        return i;
    }

    /**
     * Fonction permettant d'obtenir le nombre d'items utilisable présents dans
     * l'inventaire.
     *
     * @return Le nombre d'objets utilisable, sous forme d'entier.
     */
    public int getNumberUseableItem() {
        int i = 0;
        for (Item item : inventory) {
            if (item instanceof UseableItem) {
                i++;
            }
        }
        return i;
    }

    /**
     * Permet d'obtenir tous les objets équipés d'un type passé en paramètre
     * sous forme de liste.
     *
     * @param itemType Type des items voulus
     * @return La liste contenant les items
     */
    public StuffItem getEquipment(Class itemType)
    {
        ArrayList<StuffItem> items = new ArrayList<>();
        for (StuffItem stuffItem : equipment)
        {
            if (stuffItem.getClass() == itemType)
            {
                return stuffItem;
            }
        }
        return null;
    }

    /**
     * Vérifie si l'inventaire est plein.
     *
     * @return Vrai s'il est plein, faux sinon.
     */
    public boolean inventoryNotFull() {
        int sum = 0;
        for (Item item : this.inventory) {
            sum += item.getWeight();
        }
        if (sum > this.maxWeight) {
            return false;
        }
        return true;
    }
    
    public String getUseableItemsToString()
    {
        String ret = "";
        int nb = 1;
        for(Item i : this.inventory)
        {
            if(i instanceof UseableItem)
            {
                ret+="Item n°"+nb+"\n"+i.toString();
                nb++;
            }
        }
        return ret;
    }
    
    /**
     * Permet d'obtenir l'item de l'inventaire en fonction du niveau passé en
     * paramètre, ainsi que sa classe. Les items équipés ne sont pas comptés dedans.
     * @param classe La classe de l'item qu'on veut
     * @param number le numéro dans la liste
     * @return L'item concerné
     */
    public Item getInInventoryItemOfType(Class classe, int number)
    {
        int nb = 0;
        for(Item i : this.inventory)
        {
            if(i.getClass()==classe && !this.equipment.contains(i))
            {
                if(nb==number)
                {
                    return i;
                }
                nb++;
            }
        }
        return null;
    }

    /*
        FONCTION DE GESTION DE L'INVENTAIRE ET EQUIPEMENT
     */
    
    
    /**
     * Permet d'ajouter un iteam a l'inventaire.
     *
     * @param item L'item à ajouter.
     * @return Vrai si l'ajout a pu se faire, faux sinon.
     */
    public boolean addInventory(Item item) {
        if (item.getWeight() + inventoryWeight() <= maxWeight) {
            this.inventory.add(item);
            return true;
        }
        return false;
    }

    /**
     * Fonction permettant d'utiliser un item
     *
     * @param useableItem L'item a utiliser.
     */
    public void useUseableItem(UseableItem useableItem) {
        if (this.inventory.contains(useableItem)) {
            // traitement
            // utilisation = influence sur caratéristiques
            // utilisation potion -> augmentation santé perso
            this.inventory.remove(useableItem);
        }
    }

    /**
     * Fonction permettant d'équiper un personnage.
     *
     * @param stuffItem l'item à équiper
     * @return Vrai si l'item a pu etre équipé, faux sinon.
     */
    public String addEquipement(StuffItem stuffItem) 
    {
        if (equipment.size() < MAXEQUIPMENT) {
            if ((stuffItem.getClass() == Weapon.class && numberOfClassEquipment(Weapon.class) < MAXWEAPONEQUIPMENT)
                    || (stuffItem.getClass() == Armor.class && numberOfClassEquipment(Armor.class) < MAXARMOREQUIPMENT)
                    || (stuffItem.getClass() == Greave.class && numberOfClassEquipment(Greave.class) < MAXGREAVEEQUIPMENT)) 
            {
                if(stuffItem.getRequiredLevel()>this.getLevel())
                {
                    return "Vous n'avez pas le niveau requis pour équiper cet item.";
                }
                this.equipment.add(stuffItem);
                return "";
            }
            return "Vous avez déjà un équipement du même type équipé.";
        }
        return "Vous avez trop d'éléments équipés.";
    }

    /**
     * Fonction permettant de remplacer un équipement actuel avec un nouveau.
     *
     * @param newStuffItem Item remplaçant
     * @param removeStuffItem Item à remplacer
     * @return
     */
    public String replaceEquipment(StuffItem newStuffItem, StuffItem removeStuffItem) {
        this.equipment.remove(removeStuffItem);
        this.inventory.add(removeStuffItem);
        String message = this.addEquipement(newStuffItem);
        if (message.equals("")) {
            this.reinitStats();
            return "";
        }
        return message;
    }

    /**
     * Fonction permettant de mettre a jour les attributs, en fonction du stuff.
     */
    private void updateAttributeStuff() {
        for (StuffItem item : this.equipment) {
            if (item.getClass() == Weapon.class) {
                int value = ((Weapon) item).getDamage();
                this.attributes.addValueToAttribute(Attribute.STRENGTH, value);
            } else if (item.getClass() == Armor.class) {
                int value = ((Armor) item).getDefenseValue();
                this.attributes.addValueToAttribute(Attribute.DEFENSE, value);
            } else if (item.getClass() == Greave.class) {
                int value = ((Greave) item).getDefenseValue();
                this.attributes.addValueToAttribute(Attribute.DEFENSE, value);
            }
        }
    }

    /*
        FONCTIONS DE COMBAT
     */
    /**
     * Fonction pour prendre un coup.
     *
     * @param value Dégats subis
     */
    public void takeABlow(int value) {
        this.attributes.replace(Attribute.HEALTH, this.attributes.get(Attribute.HEALTH) - value);
    }

    /*
        FONCTIONS D'ATTRIBUTS
     */
    /**
     * Fonction permettant de remettre un personnage avec sa vie pleine.
     */
    public void restoreLife() {
        this.attributes.replace(Attribute.HEALTH, 150 + 2 * level.getLevel() + 3);
        this.attributes.replace(Attribute.MANA, 20 + 2 * level.getLevel() + 3);
    }

    /**
     * Cette fonction gère le traitement de l'exception en fonction des
     * contraintes sur les caractéristiques.
     *
     * @param attribute L'attribute concerné
     * @param value La valeur dont on veut l'augmenter
     * @return Une chaine vide ("") si tout s'est bien passé, l'erreur de
     * l'exception sinon.
     */
    public String increaseAttribute(Attribute attribute, int value) {
        try {
            this.basicAttributes.replace(attribute, this.basicAttributes.get(attribute) + value);
            this.restoreAttribute(attribute);
            return "";
        } catch (IllegalArgumentException ex) {
            return ex.getMessage() + "\n";
        }
    }

    /**
     * Fonction permettant de remettre un attribut a sa valeur de base.
     *
     * @param attribute Attribut à réinitialiser
     */
    private void restoreAttribute(Attribute attribute) {
        attributes.replace(attribute, basicAttributes.get(attribute), false);
    }

    /**
     * Fonction pour remettre tous les attributs à leur valeur de base.
     */
    private void restoreAttributes() {
        restoreAttribute(Attribute.SPEED);
        restoreAttribute(Attribute.DEFENSE);
        restoreAttribute(Attribute.DEXTERITY);
        restoreAttribute(Attribute.STRENGTH);
    }

    /**
     * Réinitialisation des caractéristiques.
     */
    public void reinitStats() {
        restoreAttributes();
        updateAttributeStuff();
        calculateMaxWeight();
    }

    /**
     * Permet de calculer le poids total que peut transporter le personnage
     * en fonction de sa force.
     * Valeur de base sans force: 25.
     */
    private void calculateMaxWeight() {
        this.maxWeight = 25 + this.attributes.get(Attribute.STRENGTH);
    }

    /**
     * Fonction à surchager dans les classes filles. Permet de répartir les
     * points de compétence aléatoire en fonction du niveau.
     *
     * @param lvl Niveau du personnage
     */
    public abstract void putRandomPoint(int lvl);

    /*
        FONCTIONS DE NIVEAU
     */
    /**
     * Fonction qui permet d'augmenter l'expérience du personnage.
     *
     * @param value Valeur dont on veut augmenter l'expérience.
     */
    public void increaseExperience(int value) {
        if (this.level.increaseExp(value)) {
            this.upLevel();
        }
    }

    /**
     * Fonction qui fait gagner un niveau au personnage, et demande a
     * l'utilisateur de répartir les points.
     */
    private void upLevel() {
        restoreAttributes();

        String text = ConsoleDesign.textBox("Passage au niveau supérieur!", RED)
                + "\n";

        if (this.basicAttributes.canUpSomething()) {
            text += ConsoleDesign.textBox("Vos caractéristiques actuelles:\n" + this.toString(), RED)
                    + "\n"
                    + ConsoleDesign.textDashArrow("Vous pouvez répartir " + NBPOINTLEVELUP + " points de compétence supplémentaires!", RED)
                    + "\n"
                    + ConsoleDesign.text("1 -> Force\n", RED)
                    + ConsoleDesign.text("2 -> Défense\n", RED)
                    + ConsoleDesign.text("3 -> Dextérité\n", RED)
                    + ConsoleDesign.text("4 -> Vitesse\n", RED);
        } else {
            text = ConsoleDesign.text("Vous ne pouvez plus rien améliorer (caractéristiques de bases déjà au maximum).", RED);
            System.out.println(text);
            return;
        }

        int cpt = NBPOINTLEVELUP;
        String res;
        while (cpt > 0) {
            switch (Controller.askNumberBetween(text, 1, 4)) {
                case 1:
                    res = this.increaseAttribute(Attribute.STRENGTH, 1);
                    if (res.equals(""))//Tout s'est bien passé
                    {
                        cpt--;
                    } else {
                        text = res;
                    }
                    break;
                case 2:
                    res = this.increaseAttribute(Attribute.DEFENSE, 1);
                    if (res.equals(""))//Tout s'est bien passé
                    {
                        cpt--;
                    } else {
                        text = res;
                    }
                    break;
                case 3:
                    res = this.increaseAttribute(Attribute.DEXTERITY, 1);
                    if (res.equals(""))//Tout s'est bien passé
                    {
                        cpt--;
                    } else {
                        text = res;
                    }
                    break;
                case 4:
                    res = this.increaseAttribute(Attribute.SPEED, 1);
                    if (res.equals(""))//Tout s'est bien passé
                    {
                        cpt--;
                    } else {
                        text = res;
                    }
                    break;
            }
            text = ConsoleDesign.textDashArrow("Réitérez l'opération encore " + cpt + " fois", RED)
                    + "\n"
                    + ConsoleDesign.text("1 -> Force\n", RED)
                    + ConsoleDesign.text("2 -> Défense\n", RED)
                    + ConsoleDesign.text("3 -> Dextérité\n", RED)
                    + ConsoleDesign.text("4 -> Vitesse\n", RED);
        }

        System.out.println("Vos nouvelles statistiques:\n" + this.toString());
    }

    /*****
     * 
        FONCTIONS AVEC RETOUR TEXTE
     * 
     *****/

    /**
     * Fonction toString. Renvoie les données du personnage sous forme de texte.
     * @return Un string contenant les données du personnage.
     */
    @Override
    public String toString() {
        String text = ConsoleDesign.text("Nom: " + this.name, CYAN)
                + "\n"
                + ConsoleDesign.text("Classe: " + this.className, CYAN)
                + "\n"
                + ConsoleDesign.text("Niveau: " + this.level.getLevel(), CYAN)
                + "\n";
        if (this.isAlive()) {
            text += ConsoleDesign.text("Santé: " + this.attributes.get(Attribute.HEALTH), CYAN)
                    + "\n"
                    + ConsoleDesign.text("Mana: " + this.attributes.get(Attribute.MANA), CYAN)
                    + "\n"
                    + ConsoleDesign.text("Force: " + this.attributes.get(Attribute.STRENGTH), CYAN)
                    + "\n"
                    + ConsoleDesign.text("Défense: " + this.attributes.get(Attribute.DEFENSE), CYAN)
                    + "\n"
                    + ConsoleDesign.text("Dextérité: " + this.attributes.get(Attribute.DEXTERITY), CYAN)
                    + "\n"
                    + ConsoleDesign.text("Vitesse: " + this.attributes.get(Attribute.SPEED), CYAN)
                    + "\n";
        } else {
            text += ConsoleDesign.text("Personnage mort.\n", RED);
        }

        return text;
    }

    /**
     * Permet d'obtenir les infos sur le niveau actuel.
     * @return Un string contenant les informations.
     */
    public String getActuelLevelState() 
    {
        return this.level.toString();
    }

    /**
     * Permet d'obtenir l'inventaire du personnage sous forme de texte.
     * Chaque item contenu dans l'équipement sera signalé avec le mot "Equipé".
     * @return Un string contenant la totalité de l'inventaire.
     */
    public String getInvetoryToString()
    {
        String text ="";
        int cpt=1;
        text += ConsoleDesign.text("\nInventaire de "+this.name+ ":\n",CYAN);
        for(Item i : this.inventory)
        {
            if(this.equipment.contains(i))
            {
                text +=ConsoleDesign.text("\n("+cpt+")", GREEN)+ConsoleDesign.text("Equipé\n", YELLOW)+i.toString();
            }
            else
            {
                text +=ConsoleDesign.text("\n("+cpt+")", GREEN)+i.toString();
            }
            cpt++;
        }
        return text;
    }

    /**
     * Permet d'obtenir sous forme de texte l'équipement actuel du personnage.
     * @return Un texte contenant l'équipement du personnage.
     */
    public String getEquipmentToString()
    {
        String text = "";
        int cpt = 1;
        text += ConsoleDesign.text("Equipement de: "+this.name+"\n",CYAN);
        for(StuffItem i : this.equipment)
        {
            if(this.equipment.contains(i))
            {
                text +=ConsoleDesign.text("\n("+cpt+")", GREEN)+ConsoleDesign.text("Equipé\n", YELLOW)+i.toString();
            }
            cpt++;
        }
        return text;
    }
    
    /**
     * Permet d'obtenir sous forme de string, chaque élément équipable du personnage
     * selon un type passé en paramètre. 
     * Les items présents dans l'équipement ne sont pas comptabilisés.
     * @param clas Le type d'item voulu
     * @return Un texte contenant la liste des éléments équipables du perso.
     */
    public String getEquipeableItemToString(Class clas)
    {
        String text ="";
        int cpt=1;
        text += ConsoleDesign.text("Elements équipables de "+this.name+":",CYAN);
        for(Item i : this.inventory)
        {
            if(!this.equipment.contains(i) && i.getClass()==clas)
            {
                text +=ConsoleDesign.text("\n("+cpt+")\n", GREEN)+i.toString();
                cpt++;
            }
        }  
        return text;
    }

    public String addCapacity()
    {
        if(this instanceof Warrior)
        {
            ((Warrior)this).capacities.add("Anger");
            return "Le personnage "+this.name+" a appris la nouvelle capacité 'Anger'! Il peut désormais l'utiliser"
                    + "en échange de 25 points de mana. Les dégâts de base sont de 40, plus 0.8 fois l'attaque du personnage. "
                    + "Le taux de réussite est fonction de la dextérité du personnage (en pourcentage).";
        }
        else if(this instanceof Athlete)
        {
            ((Athlete)this).capacities.add("Uppercut");
            return "Le personnage "+this.name+" a appris la nouvelle capacité 'Uppercut'! Il peut désormais l'utiliser"
                    + "en échange de 15 points de mana. Les dégâts de base sont de 25, plus 0.5 fois l'attaque du personnage. Cette compétence ignore la défense advairse. "
                    + "Cette attaque ne peut échouer.";
        }
        else if(this instanceof Thief)
        {
            ((Thief)this).capacities.add("Pickpocket");
            return "Le personnage "+this.name+" a appris la nouvelle capacité 'Pickpocket'! Il peut désormais l'utiliser"
                    + "en échange de 10 points de mana. Les dégâts de base sont de 15, plus 2 fois la vitesse du personnage, et 0.2 fois son attaque."
                    + "Cette compétence permet également de voler un objet a l'adeversaire."
                    + "Le taux de réussite est fonction de la dextérité du personnage (à partir de 50, 100%).";
        }
        return "";
    }

    public void decreaseAttribute(Attribute attribute, int i)
    {
        this.attributes.replace(attribute, this.getAttributeValue(attribute)-i);
    }

    public int getNbItemsInInventory()
    {
        int sum = 0;
        for(Item i : this.inventory)
        {
            sum++;
        }
        return sum;
    }
    
    public Item getItem(int nb)
    {
        return this.inventory.get(nb);
    }

    public void removeItemInInventory(Item item)
    {
        this.inventory.remove(item);
    }

    private void addRandomItems()
    {
        int nbAlea = (int)(Math.random()*5)+5;
        for(int i=0; i<nbAlea; i++)
        {
            Potion.getRandomItem(Potion.listPotionItem);
        }
    }
}
