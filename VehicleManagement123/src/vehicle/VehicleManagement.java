/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
import myTools.*;

/**
 *
 * @author Bond
 */
public class VehicleManagement extends ArrayList<Vehicle> {

    public Vehicle checkVehicle(String id) {
        for (Vehicle ve : this) {
            if (ve.getId().equalsIgnoreCase(id)) {
                return ve;
            } else if (ve.getName().equalsIgnoreCase(id)) {
                return ve;
            }
        }
        return null;
    }

    public void addVehicle() {
        int i = 1;
        String id = null;
        while (true) {
            System.out.println("---ID of product is auto generated---");
            do {
                id = MyTools.generateCode("V_", 6, i++);
            } while (this.checkVehicle(id) != null);
            System.out.println("Vehicle's ID: " + id);
            String name = MyTools.readStr("Enter product name", ".*");
            String color = MyTools.readStr("Enter product color", ".*");
            double price = Double.parseDouble(MyTools.readStr("Enter product price", "\\d+(\\.\\d+)?"));
            String brand = MyTools.readStr("Enter product brand", ".*");
            String type = MyTools.readStr("Enter product type", ".*");
            Date today = new Date();
            String productYear = MyTools.toString(MyTools.readDateBefore("enter product year ( before or equal today)", "dd-MM-yyyy", today), "dd-MM-yyyy");
            int quantity = Integer.parseInt(MyTools.readStr("Enter product quantity", "\\d+"));
            Vehicle v1 = new Vehicle(id, name, color, price, brand, type, productYear, quantity);
            this.add(v1);
            System.out.println("Add product successfully!");
            System.out.println("Do you want to add new product?");
            String con = MyTools.readStr("Enter choice (1:yes/2:no)", "[1-2]");
            if (con.equalsIgnoreCase("2")) {
                break;
            }
        }
    }

    public void existVehicle(String id) {
        if (checkVehicle(id) != null) {
            System.out.println("Exist Vehicle: " + checkVehicle(id).getId());
            System.out.println(checkVehicle(id).toString());
        } else {
            System.out.println("Not Exist Vehicle");
        }
    }
    public void searchByName(String name){
        ArrayList<Vehicle> listVe = new ArrayList<>();
        for (Vehicle ve : this) {
            if (ve.getName().contains(name)) {
                listVe.add(ve);
            }
        }
        if(listVe != null){
            System.out.println("Found vehicle: ");
            for (Vehicle v : listVe) {
                System.out.println(v.getId() + ", " + v.getName() + ", " + v.getColor() + ", " + v.getPrice() + ", " + v.getBrand() + ", " + v.getType() + ", " + v.getProductYear() + ", " + v.getQuantity());
            }
        }else{
            System.out.println("Not found vehicle!");
        }
        
    }

    public void updateVehicle() {
        Vehicle v = new Vehicle();
        Scanner sc = new Scanner(System.in);
        String codeNum = MyTools.readStr("Enter vehocle code number (6 digits)", "\\d{6}");
        String id = "V_" + codeNum;
        if (checkVehicle(id) == null) {
            System.out.println("Vehicle is not exist");
        } else {
            System.out.println("Vehicle information:");
            v = checkVehicle(id);
            System.out.println(v.toString());
            while (true) {
                String k = MyTools.readStr("Do you want to update (1:Y/2:N) ", "[1-2]");
                if (k.equals("2")) {
                    System.out.println("Stop update, back to Main Menu!");
                    break;
                }

                String name = MyTools.inputUpdateName("Enter new name");
                if (name != null) {
                    v.setName(name);
                } else {

                }
                String color = MyTools.inputUpdateName("Enter new color");
                if (color != null) {
                    v.setColor(color);
                } else {

                }
                String price = MyTools.inputUpdatePrice();
                if (price != null) {
                    v.setPrice(Double.parseDouble(price));
                } else {

                }
                String brand = MyTools.inputUpdateName("Enter new brand");
                if (brand != null) {
                    v.setBrand(brand);
                } else {

                }
                String type = MyTools.inputUpdateName("Enter new type");
                if (type != null) {
                    v.setType(type);
                } else {

                }
                while (true) {
                    System.out.print("Enter new manufactoring day : ");
                    String newMaDay = sc.nextLine().trim();
                    System.out.println("");
                    if (newMaDay == null || newMaDay.length() == 0 || newMaDay == "") {
                        break;
                    } else {
                        Date newDate = MyTools.parseDate(newMaDay, "dd-MM-yyyy");
                        if (newDate == null) {
                            System.out.println("Date have to be (dd-MM-yyyy)!!!");
                        } else {
                            Date today = new Date();
                            if (MyTools.checkDateBefore(newDate, MyTools.toString(today, "dd-MM-yyyy"), "dd-MM-yyyy") == false) {
                                System.out.println("Date is not before today");
                            } else {
                                checkVehicle(id).setProductYear(MyTools.toString(newDate, "dd-MM-yyyy"));
                                break;
                            }

                        }
                    }
                }
                String quantity = MyTools.inputUpdateQuantity();
                if (quantity != null) {
                    v.setQuantity(Integer.parseInt(quantity));
                } else {

                }
                System.out.println("Vehicle after update: " + v.toString());
                System.out.println("Update successfully!!");
                break;
            }
        }
    }

    public void deleteVehicle() {
        String codeNum = MyTools.readStr("Enter vehicle code number (6 digits)", "\\d{6}");
        String id = "V_" + codeNum;
        if (checkVehicle(id) == null) {
            System.out.println("Vehicle is not exist");
        } else {
            System.out.println("Found vehicle: " + checkVehicle(id).toString());
            String k = MyTools.readStr("Do you want to delete (1:Y/2:N) ", "[1-2]");
            if (k.equals("1")) {
                this.remove(checkVehicle(id));
                System.out.println("Delete successfully!!!");
            } else {
                System.out.println("Stop delete, Return to main menu!");
            }
        }
    }

    public void svaeToFile(String fName1) {
        if (this.isEmpty()) {
            System.out.println("Empty list...");
            return;
        }
        try {
            Collections.sort(this, new Comparator<Vehicle>() {
                @Override
                public int compare(Vehicle o1, Vehicle o2) {
                    return o1.getId().compareToIgnoreCase(o2.getId());
                }
            });
            File f = new File(fName1);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Vehicle v : this) {
                pw.println(v.getId() + ", " + v.getName() + ", " + v.getColor() + ", " + v.getPrice() + ", " + v.getBrand() + ", " + v.getType() + ", " + v.getProductYear() + ", " + v.getQuantity());
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readFromFile(String fName1) {
        try {
            File f = new File(fName1);
            if (!f.exists()) {
                return;
            }
            FileReader fl = new FileReader(f);
            BufferedReader br = new BufferedReader(fl);
            String details;
            while ((details = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String id = stk.nextToken().toLowerCase().trim();
                String name = stk.nextToken().toLowerCase().trim();
                String color = stk.nextToken().toLowerCase().trim();
                double price = Double.parseDouble(stk.nextToken().trim());
                String brand = stk.nextToken().toLowerCase().trim();
                String type = stk.nextToken().toLowerCase().trim();
                String productYear = stk.nextToken().toLowerCase().trim();
                int quantity = Integer.parseInt(stk.nextToken().trim());

                Vehicle v = new Vehicle(id, name, color, price, brand, type, productYear, quantity);
                this.add(v);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showAllProduct() {
        for (Vehicle v : this) {
            System.out.println(v.getId() + ", " + v.getName() + ", " + v.getColor() + ", " + v.getPrice() + ", " + v.getBrand() + ", " + v.getType() + ", " + v.getProductYear() + ", " + v.getQuantity());

        }
    }

    public void showAllSorted(ArrayList<Vehicle> list) {
        Collections.sort(list, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                if (o1.getPrice() - o2.getPrice() > 0) {
                    return -1;
                } else if (o1.getPrice() - o2.getPrice() < 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for (Vehicle v : list) {
            System.out.println(v.getId() + ", " + v.getName() + ", " + v.getColor() + ", " + v.getPrice() + ", " + v.getBrand() + ", " + v.getType() + ", " + v.getProductYear() + ", " + v.getQuantity());
        }
    }

    public void showByType() {
        String type = MyTools.readStr("Enter type you want to search", "\\d");
        for (Vehicle thi : this) {
            if (thi.getType().equalsIgnoreCase(type)) {
                System.out.println(thi.toString());
            }
        }
    }

    public void showByTypeSorted(ArrayList<Vehicle> list) {
        Collections.sort(list, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                if (o1.getPrice() - o2.getPrice() > 0) {
                    return -1;
                } else if (o1.getPrice() - o2.getPrice() < 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        showByType();
    }

}
