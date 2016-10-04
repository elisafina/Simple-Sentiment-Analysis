/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocessing;

/**
 *
 * @author user
 */
public class Twitter {
    
    String text;
    int target;

    public Twitter() {
    }

    public Twitter(String text, int target) {
        this.text = text;
        this.target = target;
    }
    

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
    
    
}
