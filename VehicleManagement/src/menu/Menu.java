/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;
import myTools.*;
/**
 *
 * @author Bond
 */
public class Menu {
    public int getChoice(String[] op){
        int choice;
        for (int i = 0; i < op.length; i++) {
            System.out.println((i + 1) + " - " + op[i]);
        }
        choice = Integer.parseInt(MyTools.readStr("Enter your option ", "[0-9]"));
        return choice;
    }
}
