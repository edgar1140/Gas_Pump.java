package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Gas_Pump {
    public String typeOfGas;
    String filepath = "/home/basecamp/IdeaProjects/Gas_Pump.java/src/com/company/Gas_Pump.csv";

    public Gas_Pump(String typeOfGas) {
        this.typeOfGas = typeOfGas;
    }

    public double prepay(String typeOfGas, Double money) throws IOException {
        double gallons = 0.0;
        if (typeOfGas.equals("Regular")) {
            gallons = (money / 1.40);
            String price = Double.toString(gallons);
            saveRecord("Regular", price, filepath);
            loadInventory("Regular", gallons);
        } else if (typeOfGas.equals("Mid-Grade")) {
            gallons = (money / 1.43);
            String price = Double.toString(gallons);
            saveRecord("Mid-Grade", price, filepath);
            loadInventory("Mid-Grade", gallons);
        } else if (typeOfGas.equals("Premium")) {
            gallons = (money / 2.00);
            String price = Double.toString(gallons);
            saveRecord("Premium", price, filepath);
            loadInventory("Premium", gallons);
        }
        return Math.round(gallons);
    }

    public double payafter(String typeOfGas, Double gallons) throws IOException {
        double money = 0.0;
        if (typeOfGas.equals("Regular")) {
            money = (gallons * 1.40);
            String price = Double.toString(money);
            saveRecord("Regular", price, filepath);
            loadInventory("Regular", money);
        } else if (typeOfGas.equals("Mid-Grade")) {
            money = (gallons * 1.43);
            String price = Double.toString(money);
            saveRecord("Mid-Grade", price, filepath);
            loadInventory("Mid-Grade", money);
        } else if (typeOfGas.equals("Premium")) {
            money = (gallons * 2.00);
            String price = Double.toString(money);
            saveRecord("Premium", price, filepath);
            loadInventory("Premium", money);
        }
        System.out.println("");
        return Math.round(money);
    }


    public static void saveRecord(String name, String price, String filepath) throws IOException {
        FileWriter writer = new FileWriter(filepath, true);
        writer.write("\n" + name + ", " + price);
        writer.close();

    }

    public static void loadInventory(String gastype, Double gallons) throws IOException {
        Scanner input;
        File file = new File("/home/basecamp/IdeaProjects/Gas_Pump.java/src/com/company/Inventory.csv");

        input = new Scanner(file);


        while (input.hasNextLine()) {
            String regular = input.nextLine();
            String mid_grade = input.nextLine();
            String premium = input.nextLine();
            ArrayList regularlist = new ArrayList<String>(Arrays.asList(regular.split(", ")));
            ArrayList midgradelist = new ArrayList<String>(Arrays.asList(mid_grade.split(", ")));
            ArrayList premiumlist = new ArrayList<String>(Arrays.asList(premium.split(", ")));
            if (gastype.equals("Regular")) {
                double total = Double.parseDouble(regularlist.get(1).toString());
                double new_total = total -= gallons;
                String new_ammount = Double.toString(new_total);
                regular = regular.replace(regularlist.get(1).toString(), new_ammount);
            } else if (gastype.equals("Mid-Grade")) {
                double total = Double.parseDouble(midgradelist.get(1).toString());
                double new_total = total -= gallons;
                String new_ammount = Double.toString(new_total);
                mid_grade = mid_grade.replace(midgradelist.get(1).toString(), new_ammount);
            } else if (gastype.equals("Premium")) {
                double total = Double.parseDouble(premiumlist.get(1).toString());
                double new_total = total -= gallons;
                String new_ammount = Double.toString(new_total);
                premium = premium.replace(premiumlist.get(1).toString(), new_ammount);
            }
            change_inventory(regular, mid_grade, premium);

        }
        input.close();
    }

    public static void change_inventory(String regular, String mid_grade, String Premium) throws IOException {
        FileWriter writer = new FileWriter("/home/basecamp/IdeaProjects/Gas_Pump.java/src/com/company/Inventory.csv");
        writer.write(regular);
        writer.write("\n" + mid_grade);
        writer.write("\n" + Premium);
        writer.close();
    }
    public static String totalSales() throws IOException {
        Scanner input;
        File file = new File("/home/basecamp/IdeaProjects/Gas_Pump.java/src/com/company/Inventory.csv");

        input = new Scanner(file);
        input.nextLine();

        Double total = 0.0;

        while (input.hasNextLine()) {
            String sale = input.nextLine();

            ArrayList saleList = new ArrayList<String>(Arrays.asList(sale.split(", ")));
            double salePrice = Double.parseDouble(saleList.get(1).toString());
            total += salePrice;

        }
        input.close();

        total = Math.round(total * 100.0) / 100.0;
        String message = "Total sales: $" + total;
        return message;

    }
}


