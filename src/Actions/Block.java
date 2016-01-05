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
public interface Block extends Capacity {

    public boolean block();

    public boolean dodge();

}
