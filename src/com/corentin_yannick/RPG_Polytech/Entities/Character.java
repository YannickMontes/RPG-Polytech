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
    protected String name;
    protected String className;
    protected Attributes attributes;
    protected Attributes basicAttributes;
    protected Level level;
    protected int maxWeight;
    protected List<Item> inventory;
    protected List<StuffItem> equipment;
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

            int nbAlea = (int) (Math.random() * listPossibleArmor.size());
            this.addEquipement(listPossibleArmor.get(nbAlea));
            nbAlea = (int) (Math.random() * listPossibleGreave.size());
            this.addEquipement(listPossibleGreave.get(nbAlea));
            nbAlea = (int) (Math.random() * listPossibleWeapon.size());
            this.addEquipement(listPossibleWeapon.get(nbAlea));
        }
        this.inventory.add(Potion.listPotionItem.get(0));
        this.inventory.add(Potion.listPotionItem.get(0));
        //this.inventory.add(Weapon.listWeaponItem.get(1));
        this.reinitStats();
    }

    /**
     * Fonction permettant d'initialiser les stats du personnage.
     */
    public abstract void initStats();

    /*
        GETTERS & SETTERS
     */
    public String getName() {
        return name;
    }

    public Attributes getAttributes() {
        return attributes;
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

    public List<StuffItem> getEquipment() {
        return equipment;
    }

    public List<Item> getInventory() {
        return inventory;
    }
    
    public int getNbEquipableItem()
    {
        int nb = 0;
        for(int i=0; i<this.inventory.size(); i++)
        {
            if(this.inventory.get(i) instanceof StuffItem)
            {
                nb++;
            }
        }
        return nb;
    }
    
    /**
     * Fonction permettant d'obtenir la taille totale que prennent les objets
     * dans l'inventaire.
     *
     * @return La taille totale, sous forme d'entier
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
    public int numberUseableItem() {
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
    
    public List<UseableItem> getUseableItems()
    {
        List<UseableItem> ret = new ArrayList<>();
        for(Item i : this.inventory)
        {
            if(i instanceof UseableItem)
            {
                ret.add((UseableItem)i);
            }
        }
        return ret;
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
    public String addEquipement(StuffItem stuffItem) {
        /*if (!this.inventory.contains(stuffItem)) {
            if (!this.addInventory(stuffItem)) {
                return "Vous ne pouvez pas transporter plus d'objets.";
            }
        }*/
        if (equipment.size() < MAXEQUIPMENT) {
            if ((stuffItem.getClass() == Weapon.class && numberOfClassEquipment(Weapon.class) < MAXWEAPONEQUIPMENT)
                    || (stuffItem.getClass() == Armor.class && numberOfClassEquipment(Armor.class) < MAXARMOREQUIPMENT)
                    || (stuffItem.getClass() == Greave.class && numberOfClassEquipment(Greave.class) < MAXGREAVEEQUIPMENT)) {
                if(stuffItem.getRequiredLevel()>this.getLevel())
                {
                    return "Vous n'avez pas le niveau requis pour équiper cet item.";
                }
                this.equipment.add(stuffItem);
                return "";
            }
        }
        return "Vous avez déjà trop d'équipements équipés.";
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

    /**
     * Fonction permettant d'utiliser une capacité.
     *
     * @param capacity La capacité a utiliser.
     * @return Vrai si la capacité à fonctionnée, faux sinon.
     */
    public boolean verifySuccess(String capacity) {
        //selon la capacitié utilisée: selon ses caractéristiques et son arme (s'il en a)
        //calcule de proba
        float probability = 0;
        if ("attack".equals(capacity))
        {
            float mania=1;
            StuffItem weapon = this.getEquipment(Weapon.class);
            mania = weapon.getHandlingAbility()/100;
            
            probability = mania;
            probability += 2 * (1 - mania) * (this.attributes.get(Attribute.DEXTERITY) * 0.004);
            System.out.println("Probabilité d'attaquer: " + probability);
        } else if ("block".equals(capacity)) {
            //probability = this.attributes.get(Attribute.DEFENSE);
            StuffItem armor = this.getEquipment(Armor.class);
            probability += (float) (((Armor) armor).getHandlingAbility() / 100) * 0.2; // entre 0 et 100
            probability += (float) (this.attributes.get(Attribute.DEFENSE).floatValue() / 100)/* * (1 - (0.2 * this.getEquipment(Armor.class).size()))*/;
        } else if ("dodge".equals(capacity)) {
            for (StuffItem stuffItem : this.getEquipment()) {
                probability -= (float) (stuffItem.getWeight()) / 3;
            }
            probability += (float) (this.attributes.get(Attribute.SPEED).floatValue() / 100)/* * (1 - (0.2 * this.getEquipment().size()))*/;
        } else if ("useItem".equals(capacity)) {
            probability = 1;
        }

        if (probability > 1) {
            probability = 1;
        } else if (probability < 0) {
            probability = 0;
        }

        int rand = (int) (Math.random() * (100));
        if (rand <= (probability * 100)) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param capacity
     * @param opponent
     * @param useableItem
     * @return
     */
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
            return this.calculateDamageBasicAttack(opponent);
        } else if ("block".equals(capacity)) {
            this.attributes.replace(Attribute.DEFENSE, (int) (this.attributes.get(Attribute.DEFENSE) + 0.5 * this.attributes.get(Attribute.STRENGTH)),false);
            return (int) (0.5 * this.attributes.get(Attribute.STRENGTH));
        } else if ("useItem".equals(capacity) && useableItem != null) {
            this.attributes.replace(useableItem.getAttributeBonus(), this.attributes.get(useableItem.getAttributeBonus())+useableItem.getValue(), false);
            return 1;
        }
        return 0;
    }

    private int calculateDamageBasicAttack(Character opponent) {
        int defenseOpponent = opponent.getAttributes().get(Attribute.DEFENSE);
        int damage = 0;
        float reduction;
        if (defenseOpponent <= 50) {
            reduction = (float) (defenseOpponent * 0.01f);
        } else if (defenseOpponent <= 100) {
            reduction = (float) (0.5f + (defenseOpponent - 50) * 0.005f);
        } else if (defenseOpponent <= 200) {
            reduction = (float) (0.75f + (defenseOpponent - 100) * 0.00149925f);
        } else {
            reduction = 0.9f;
        }
        damage = (int) (this.attributes.get(Attribute.STRENGTH) * (1 - reduction));

        return damage;
    }

    /**
     * Fonction permettant de vérifier si l'attaque à fonctionner.
     *
     * @param success Réussite de l'attaque
     * @param opponent Cible
     * @param damages Dommages à infliger
     * @return Le texte à afficher.
     */
    public String attackResult(boolean success, Character opponent, int damages) {
        String text;
        if (success == true) {
            text = "(" + this.getName() + ") Attaque " + opponent.getName() + " avec une attaque de base, provoquant " + damages
                    + " points de dégats. (Santé: " + opponent.getAttributes().get(Attribute.HEALTH) + ")";
            if(!opponent.isAlive())
            {
                text+="\nLe personnage "+opponent.getName()+" est mort!";
            }
            return text;
        }
        return "(" + this.getName() + ") L'attaque sur " + opponent.getName() + " a échoué.";
    }

    /**
     * Fonction permettant de vérifier la réussite du soin
     *
     * @param success Réussite du soin
     * @param item Item à utiliser
     * @return Le texte à afficher
     */
    public String careResult(boolean success, UseableItem item) {
        String text;
        if (success == true) {
            if(item.getAttributeBonus() == Attribute.HEALTH)
            {
                text = "(" + this.getName() + ") Se soigne de " +item.getValue()+ ".";
            }
            else
            {
                text = "(" + this.getName() + ") Augmente de " +item.getValue()+ " son attribut de "+item.getAttributeBonus()+" pour le tour actuel.";
            }
            return text;
        }
        return "(" + this.getName() + ") L'utilisation d'item a échoué.";
    }

    /**
     * Fonction permettant de vérifier la réussite du block
     *
     * @param success Réussite
     * @param blockValue valeur du block
     * @return Le texte à afficher
     */
    public String blockResult(boolean success, int blockValue) {
        if (success == true) {
            return "(" + this.getName() + ") Bloque les attaques pour le prochain coup. Défense augmentée de " + blockValue
                    + ". (Défense: " + this.attributes.get(Attribute.DEFENSE) + ")";
        }
        return "(" + this.getName() + ") Bloquage échoué.";
    }

    /**
     * Fonction permettant de vérifier la réussite de l'esquive.
     *
     * @param success Réussite de l'esquive
     * @param blockValue valeur de l'esquive (?)
     * @return Le texte ç afficher
     */
    public String dodgeResult(boolean success, int blockValue) {
        if (success == true) {
            return "(" + this.getName() + ") Esquive les attaques le prochain tour.";
        }
        return "L'esquive a échoué";
    }

    /*
        FONCTIONS D'ATTRIBUTS
     */
    /**
     * Fonction permettant de remettre un personnage avec sa vie pleine.
     */
    public void restoreLife() {
        this.attributes.replace(Attribute.HEALTH, 150 + 2 * level.getLevel() + 3);
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
     *
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

        String text = ConsoleDesign.textBox("Passage au niveau supérieur!", ConsoleDesign.redText)
                + "\n";

        if (this.basicAttributes.canUpSomething()) {
            text += ConsoleDesign.textBox("Vos caractéristiques actuelles:\n" + this.toString(), ConsoleDesign.redText)
                    + "\n"
                    + ConsoleDesign.textDashArrow("Vous pouvez répartir " + NBPOINTLEVELUP + " points de compétence supplémentaires!", ConsoleDesign.redText)
                    + "\n"
                    + ConsoleDesign.text("1 -> Force\n", ConsoleDesign.redText)
                    + ConsoleDesign.text("2 -> Défense\n", ConsoleDesign.redText)
                    + ConsoleDesign.text("3 -> Dextérité\n", ConsoleDesign.redText)
                    + ConsoleDesign.text("4 -> Vitesse\n", ConsoleDesign.redText);
        } else {
            text = ConsoleDesign.text("Vous ne pouvez plus rien améliorer (caractéristiques de bases déjà au maximum).", ConsoleDesign.redText);
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
            text += ConsoleDesign.textDashArrow("Réitérez l'opération encore " + cpt + " fois", ConsoleDesign.redText)
                    + "\n"
                    + ConsoleDesign.text("1 -> Force\n", ConsoleDesign.redText)
                    + ConsoleDesign.text("2 -> Défense\n", ConsoleDesign.redText)
                    + ConsoleDesign.text("3 -> Dextérité\n", ConsoleDesign.redText)
                    + ConsoleDesign.text("4 -> Vitesse\n", ConsoleDesign.redText);
        }

        System.out.println("Vos nouvelles statistiques:\n" + this.toString());
    }

    /*
        FONCTIONS D'AFFICHAGE
     */
    /**
     * Afficher les données du personnage.
     */
    public void showData() {
        System.out.println("--------------------");
        System.out.println("DONNEES DE " + this.name);
        System.out.println("Classe: " + this.className);
        System.out.println("Niveau: " + this.level.getLevel());
        System.out.println("Niveau de santé: " + this.attributes.get(Attribute.HEALTH));
        System.out.println("Niveau de mana: " + this.attributes.get(Attribute.MANA));
        System.out.println("Niveau de dextérité: " + this.attributes.get(Attribute.DEXTERITY));
        System.out.println("Niveau de défense: " + this.attributes.get(Attribute.DEFENSE));
        System.out.println("Niveau de vitesse: " + this.attributes.get(Attribute.SPEED));
        System.out.println("Niveau de force: " + this.attributes.get(Attribute.STRENGTH));
        System.out.println("Nombre d'objet dans l'inventaire: " + this.inventory.size());
        System.out.println("Equipement actuel: ");
        for (StuffItem stuffItem : equipment) {
            System.out.println("-" + stuffItem.getName() + " Poids: " + stuffItem.getWeight() + " Maniabilité:" + stuffItem.getHandlingAbility());
        }
    }

    /**
     * Affiche son inventaire.
     */
    public void showInventary() {
        for (Item item : inventory) {
            System.out.print("-" + item.getName() + " (" + item.getClass() + ") Poids: " + item.getWeight());
            if (item instanceof UseableItem) {
                System.out.println(" Valeur de bonus:" + ((UseableItem) item).getValue());
            } else if (item instanceof StuffItem) {
                System.out.println(" Maniabilité:" + ((StuffItem) item).getHandlingAbility());
            }
        }
    }

    @Override
    public String toString() {
        String text = ConsoleDesign.text("Nom: " + this.name, ConsoleDesign.cyanText)
                + "\n"
                + ConsoleDesign.text("Classe: " + this.className, ConsoleDesign.cyanText)
                + "\n"
                + ConsoleDesign.text("Niveau: " + this.level.getLevel(), ConsoleDesign.cyanText)
                + "\n";
        if (this.isAlive()) {
            text += ConsoleDesign.text("Santé: " + this.attributes.get(Attribute.HEALTH), ConsoleDesign.cyanText)
                    + "\n"
                    + ConsoleDesign.text("Mana: " + this.attributes.get(Attribute.MANA), ConsoleDesign.cyanText)
                    + "\n"
                    + ConsoleDesign.text("Force: " + this.attributes.get(Attribute.STRENGTH), ConsoleDesign.cyanText)
                    + "\n"
                    + ConsoleDesign.text("Défense: " + this.attributes.get(Attribute.DEFENSE), ConsoleDesign.cyanText)
                    + "\n"
                    + ConsoleDesign.text("Dextérité: " + this.attributes.get(Attribute.DEXTERITY), ConsoleDesign.cyanText)
                    + "\n"
                    + ConsoleDesign.text("Vitesse: " + this.attributes.get(Attribute.SPEED), ConsoleDesign.cyanText)
                    + "\n";
        } else {
            text += ConsoleDesign.text("Personnage mort.\n", ConsoleDesign.redText);
        }

        return text;
    }

    /**
     * Affiche les informations sur le niveau actuel.
     */
    public void printActualLevelState() {
        System.out.println(this.level);
    }

    public String getInvetoryToString()
    {
        String text ="";
        int cpt=1;
        text += ConsoleDesign.text("\nInventaire de: \n"+this.name,ConsoleDesign.cyanText);
        for(Item i : this.inventory)
        {
            if(this.equipment.contains(i))
            {
                text +=ConsoleDesign.text("\n("+cpt+")", ConsoleDesign.greenText)+ConsoleDesign.text("Equipé\n", ConsoleDesign.yellowText)+i.toString();
            }
            else
            {
                text +=ConsoleDesign.text("\n("+cpt+")", ConsoleDesign.greenText)+i.toString();
            }
            cpt++;
        }
        return text;
    }

    public String getEquipmentToString()
    {
        String text = "";
        int cpt = 1;
        text += ConsoleDesign.text("Equipement de: \n"+this.name,ConsoleDesign.cyanText);
        for(StuffItem i : this.equipment)
        {
            if(this.equipment.contains(i))
            {
                text +=ConsoleDesign.text("\n("+cpt+")", ConsoleDesign.greenText)+ConsoleDesign.text("Equipé\n", ConsoleDesign.yellowText)+i.toString();
            }
            cpt++;
        }
        return text;
    }

    public String getEquipeableItemToString()
    {
        String text ="";
        int cpt=1;
        text += ConsoleDesign.text("Elements équipables de "+this.name+":",ConsoleDesign.cyanText);
        for(Item i : this.inventory)
        {
            if(!this.equipment.contains(i) && i instanceof StuffItem)
            {
                text +=ConsoleDesign.text("\n("+cpt+")\n", ConsoleDesign.greenText)+i.toString();
                cpt++;
            }
        }  
        return text;
    }

    public String getEquipeableItemToString(Class clas)
    {
        String text ="";
        int cpt=1;
        text += ConsoleDesign.text("Elements équipables de "+this.name+":",ConsoleDesign.cyanText);
        for(Item i : this.inventory)
        {
            if(!this.equipment.contains(i) && i.getClass()==clas)
            {
                text +=ConsoleDesign.text("\n("+cpt+")\n", ConsoleDesign.greenText)+i.toString();
                cpt++;
            }
        }  
        return text;
    }

    public int getNbEquipableItem(Class clas)
    {
        int sum = 0;
        for(int i=0; i<this.inventory.size(); i++)
        {
            if(this.inventory.get(i).getClass()==clas && !this.equipment.contains(i))
            {
                sum++;
            }
        }
        return sum;
    }
    
    public StuffItem getInInventoryItemOfType(Class classe, int number)
    {
        int nb = 0;
        for(Item i : this.inventory)
        {
            if(i.getClass()==classe)
            {
                if(nb==number)
                {
                    return (StuffItem)i;
                }
            }
        }
        return null;
    }
}
