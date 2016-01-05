/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;
import Entities.Character;

/**
 *
 * @author yannick
 */
public interface Attack extends Capacity
{
    public String strikeABlow(Character opponent);
    
}
