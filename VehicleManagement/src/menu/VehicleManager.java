/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.io.IOException;
import myTools.MyTools;
import vehicle.*;

/**
 *
 * @author Bond
 */
public class VehicleManager {

    public static void main(String[] args) {
        String[] mainMenu = {"Adding new vehicle", "Checking exits Vehicle", "Updating vehicle", "Delete vehicle", "Search vehicle", "Display vehicle", "save to file", "Quit"};
        String[] searchMenu = {"Search by name", "search by id", "Back to main menu"};
        String[] displayMenu = {"Display All", "Display by type", "Display all Sorted", "Back to main menu"};
        int choice;
        Menu menu = new Menu();
        VehicleManagement vh = new VehicleManagement();
        String fileProduct = "src\\Data\\vehicle.dat";
        vh.readFromFile(fileProduct);

        do {
            System.out.print("\n\n----------------------------");
            System.out.print("\n      Vehicle Management      ");
            System.out.println("\n----------------------------");
            choice = menu.getChoice(mainMenu);
            switch (choice) {
                case 1:
                    vh.addVehicle();
                    break;
                case 2:
                    String codeNum = MyTools.readStr("Enter vehicle code number (6 digits)", "\\d{6}");
                    String id = "v_" + codeNum;
                    vh.existVehicle(id);
                    break;
                case 3:
                    vh.updateVehicle();
                    break;
                case 4:
                    vh.deleteVehicle();
                    break;
                case 5:
                    int sChoice;
                    do {
                        System.out.println("\n\n-----Vehicle Management-----");
                        sChoice = menu.getChoice(searchMenu);
                        switch (sChoice) {
                            case 1:
                                String name = MyTools.readStr("Enter vehicle name", ".*");
                                vh.existVehicle(name);
                                break;
                            case 2:
                                String codeNum2 = MyTools.readStr("Enter vehicle code number (6 digits)", "\\d{6}");
                                String id2 = "v_" + codeNum2;
                                vh.existVehicle(codeNum2);
                                break;
                            default:
                                break;
                        }
                    } while (sChoice > 0 && sChoice < 3);
                    break;
                case 6:
                    int dChoice;
                    do {
                        System.out.println("\n\n-----Vehicle Management-----");
                        dChoice = menu.getChoice(displayMenu);
                        switch (dChoice) {
                            case 1:
                                vh.showAllProduct();
                                break;
                            case 2:
                                vh.showByType();
                                break;
                            case 3:
                                vh.showAllSorted(vh);
                                break;
                            default:
                                break;
                        }
                    } while (dChoice > 0 && dChoice < 4);
                    break;
                case 7:
                    try {
                        vh.svaeToFile(fileProduct);
                        System.out.println("Saved!");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                default:
                    System.out.println("Thank you!");

            }
        } while (choice > 0 && choice < 8);
    }
}
