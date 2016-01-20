/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Items.UseableItem;

/**
 *
 * @author yannick
 */
public interface UseItem extends Capacity {

    public String useItem(UseableItem useableItem);
}
