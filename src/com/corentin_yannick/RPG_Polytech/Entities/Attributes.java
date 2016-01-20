/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Entities;

import java.util.HashMap;

/**
 *
 * @author yannick
 */
public class Attributes extends HashMap<Attribute, Integer> {

    public final static int MAXHEALTH = 999;
    public final static int MAXDEXTERITY = 99;
    public final static int MAXDEFENSE = 99;
    public final static int MAXSTRENGTH = 99;
    public final static int MAXSPEED = 99;
    public final static int MAXMANA = 999;

    public Integer addValueToAttribute(Attribute attribute, int value) {
        return super.replace(attribute, this.get(attribute) + value);
    }

    public void cheat() {
        replace(Attribute.SPEED, 999, false);
        replace(Attribute.DEXTERITY, 999, false);
        replace(Attribute.DEFENSE, 999, false);
        replace(Attribute.STRENGTH, 999, false);
    }

    @Override
    public Integer put(Attribute key, Integer value) {
        String message = verifyExcess(key, value);
        if (!message.equals("")) {
            throw new IllegalArgumentException(message);
        }
        return super.put(key, value);
    }

    @Override
    public Integer replace(Attribute key, Integer value) {
        String message = verifyExcess(key, value);
        if (!message.equals("")) {
            throw new IllegalArgumentException(message);
        }
        return super.replace(key, value);
    }

    public Integer replace(Attribute key, Integer value, boolean check) {
        if (check) {
            String message = verifyExcess(key, value);
            if (!message.equals("")) {
                throw new IllegalArgumentException(message);
            }
        }
        return super.replace(key, value);
    }

    public boolean canUpSomething() {
        if (this.get(Attribute.DEFENSE) < MAXDEFENSE || this.get(Attribute.STRENGTH) < MAXSTRENGTH
                || this.get(Attribute.DEXTERITY) < MAXDEXTERITY || this.get(Attribute.SPEED) < MAXSPEED) {
            return true;
        }
        return false;
    }

    private String verifyExcess(Attribute key, Integer value) {
        String message = "";
        switch (key) {
            case DEFENSE:
                if (value > MAXDEFENSE) {
                    message = "L'attribut suivant à dépassé sa valeur maximale: " + key;
                } else if (this.containsKey(Attribute.DEFENSE)) {
                    if (value >= (int) (this.get(Attribute.STRENGTH) * 2) || value >= (int) (this.get(Attribute.DEXTERITY) * 2)) {
                        message = "Il n'est pas possible d'augmenter votre défense d'une valeur supérieure à 2 fois votre force ou 2 fois votre dextérité.";
                    }
                }
                break;

            case DEXTERITY:
                if (value > MAXDEXTERITY) {
                    message = "L'attribut suivant à dépassé sa valeur maximale: " + key;
                } else if (this.containsKey(Attribute.DEXTERITY)) {
                    if (value >= (int) (this.get(Attribute.STRENGTH) * 2.5)) {
                        message = "Il n'est pas possible d'augmenter votre vitesse d'une valeur supérieure à 2,5 fois votre force.";
                    }
                }
                break;

            case SPEED:
                if (value > MAXSPEED) {
                    message = "L'attribut suivant à dépassé sa valeur maximale: " + key;
                } else if (this.containsKey(Attribute.SPEED)) {
                    if (value >= (int) (this.get(Attribute.STRENGTH) * 2.5) || value >= (int) (this.get(Attribute.DEXTERITY) * 2.5)) {
                        message = "Il n'est pas possible d'augmenter votre vitesse d'une valeur supérieure à 2,5 fois votre force et dextérité.";
                    }
                }
                break;

            case STRENGTH:
                if (value > MAXSTRENGTH) {
                    message = "L'attribut suivant à dépassé sa valeur maximale: " + key;
                } else if (this.containsKey(Attribute.STRENGTH)) {
                    if (value >= (int) (this.get(Attribute.DEFENSE) * 2.5) || value >= (int) (this.get(Attribute.DEXTERITY) * 2.5)) {
                        message = "Il n'est pas possible d'augmenter votre force d'une valeur supérieure à 2,5 fois votre dextérité, ou 2,5 fois votre défense.";
                    }
                }
                break;

            case HEALTH:
                if (value > MAXHEALTH) {
                    message = "L'attribut suivant à dépassé sa valeur maximale: " + key;
                }
                break;

            case MANA:
                if (value > MAXMANA) {
                    message = "L'attribut suivant à dépassé sa valeur maximale: " + key;
                }
                break;

            default:
                break;
        }
        return message;
    }

}
