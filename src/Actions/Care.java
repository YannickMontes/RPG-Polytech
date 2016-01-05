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
public interface Care extends Capacity {

    public String heal(UseableItem useableItem);

}
