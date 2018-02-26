package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void pay(Integer TypeofPay, String TypeofGas, Double Amount) throws IOException {
        if (TypeofPay.equals(1)) {
            Gas_Pump paid = new Gas_Pump(TypeofGas);
            System.out.println(paid.prepay(TypeofGas, Amount));
        } else if (TypeofPay.equals(2)) {
            Gas_Pump paid = new Gas_Pump(TypeofGas);
            System.out.println(paid.payafter(TypeofGas, Amount));
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        System.out.println("\nWELCOME TO JUAN WAY GAS STATION!\n");
        System.out.println("Would you like to [1] Pre-Pay, [2] Pay-after, [3] Admin Only.\n");
        String TypeofGas = reader.nextLine();
        Integer TypeofPay = reader.nextInt();
        System.out.println("Which type of gas would you like? Regular, Mid-Grade, or Premium?\n");
        if (TypeofPay.equals(1)) {
            System.out.println("How much money would you like to pump worth of gas?\n");
            Double money = reader.nextDouble();
            pay(TypeofPay, TypeofGas, money);
        } else if (TypeofPay.equals(2)) {
            System.out.println("How many gallons would you like to pump?\n");
            Double gallons = reader.nextDouble();
            pay(TypeofPay, TypeofGas, gallons);
        } else if (TypeofPay.equals(3)) {
            System.out.println(Gas_Pump.totalSales());
            System.exit(0);

        }
    }

}




