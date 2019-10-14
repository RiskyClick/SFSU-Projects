/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Airstrike;

/**
 *
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 */
public class KeyControl extends KeyAdapter {
    private GameEvents gameEvents;
    
    public KeyControl(){
        
    }
    public KeyControl(GameEvents ge){
        this.gameEvents = ge;
    }
    
    public void keyPressed(KeyEvent e) {
        gameEvents.setValue(e);
    }
    /*public void keyReleased(KeyEvent e){
        gameEvents.setValue(e);
    }*/
    
}