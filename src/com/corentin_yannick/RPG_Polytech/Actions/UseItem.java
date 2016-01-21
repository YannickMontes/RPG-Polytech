/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Actions;

import com.corentin_yannick.RPG_Polytech.Items.UseableItem;

/**
 *
 * @author yannick
 */
public interface UseItem extends Capacity {

    public boolean useItem(UseableItem useableItem, int upValue);
    
}
