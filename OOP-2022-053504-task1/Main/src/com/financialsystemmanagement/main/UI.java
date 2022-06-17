package com.financialsystemmanagement.main;

import com.financialsystemmanagement.database.Database;
import com.financialsystemmanagement.enterprices.Banks;
import com.financialsystemmanagement.interfaces.Bank;
import com.financialsystemmanagement.interfaces.Client;
import com.financialsystemmanagement.users.BankClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

    public static boolean isInteger(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
            return false;
        }
    }

    public static void mainMenu(){
        System.out.println("---------------\n" +
                "Choose an action:\n" +
                "1. Show banks\n" +
                "2. Show all companies\n" +
                "3. Choose a bank");
    }

    public static void showAllBanks(List<String> lines, Database db){
        Banks b;
        for (String s: lines) {
            b = db.deserializeBank(s);
            b.showInfo();
        }
    }

    public static void showAllClients(List<String> lines, Database db){
        BankClient b;
        for (String s: lines) {
            b = db.deserializeClient(s);
            b.clientInfo();
        }
    }

    public static void bankStartLogInMenu(){
        System.out.println("What would you like to do?\n" +
                "1. Log in\n" +
                "2. Log in as a personal\n" +
                "3. Add new client\n" +
                "4. Show all clients\n" +
                "5. Exit");
    }

    public static void bankClientMenu(){
        System.out.println("What would you like to do?\n" +
                "1. Transfer\n" +
                "2. Withdrawal\n" +
                "3. Take an installment\n" +
                "4. Take a credit\n" +
                "5. Your info\n" +
                "6. Open new account\n" +
                "7. Exit");
    }

    public static BankClient bankLoginMenu(List<String> clients, Client bc, Banks b, Database db) throws IOException{
        System.out.println("---------------\n" +
                "Welcome to " + b.getEnterpriseName());
        Scanner in = new Scanner(System.in);
        String id;
        List<BankClient> buf = new ArrayList<>();
        System.out.println("Enter your name: ");
        String name = in.nextLine();
        if(bc.findClientByName(name) != null){
            if(bc.isInBank(bc.findClientByName(name), b)) {
                System.out.println("Enter password: " + bc.findClientByName(name).get(0).getPassword());
                if (bc.findClientByName(name).get(0).getPassword().equals(in.nextLine())) {
                    for (String s: clients) {
                        if(db.deserializeClient(s).getPersonalName().equals(name) &&
                                db.deserializeClient(s).getBankId() == b.getBankId()){
                            buf.add(db.deserializeClient(s));
                        }
                    }
                    if(buf.size() == 1) {
                        return bc.findClientByName(name).get(0);
                    } else {
                        System.out.println("What account would wou like to choose?");
                        for (BankClient c: buf) {
                            System.out.println(c.getUserId() + ". " + c.getPersonalName() + " " + c.getMoneyCount());
                        }
                        System.out.println("Enter id:");
                        while (true){
                            id = in.nextLine();
                            if(isInteger(id)){
                                for (BankClient s: buf) {
                                    if(s.getUserId() == Integer.parseInt(id)){
                                        return s;
                                    }
                                }
                                System.out.println("Try again!");
                            } else {
                                System.out.println("Try again!");
                            }
                        }
                    }
                } else {
                    System.out.println("\nWrong Password!");
                }
            } else {
                System.out.println("123");
            }
        } else {
            System.out.println("\nThere aren't client with this name!");
        }
        return null;
    }

    public static String bankLoginPersonalMenu(Banks b){
        Scanner in = new Scanner(System.in);
        System.out.println("---------------\n" +
                "Welcome to " + b.getEnterpriseName());
        System.out.println("Choose a person: \n" +
                "1. Admin\n" +
                "2. Operator.\n" +
                "3. Manager ");
        String str;
        while(true){
            str = in.nextLine();
            if(str.equals("1") || str.equals("2") || str.equals("3")){
                break;
            }
        }
        return str;
    }

    public static int choosingBank(List<String> b, Bank br, Database db) throws IOException {
        Scanner in = new Scanner(System.in);
        String id;
        for (String s : b) {
            db.deserializeBank(s).showInfo();
        }
        System.out.println("Enter bank ID: ");
        while (true) {
            id = in.nextLine();
            if (!isInteger(id)) {
            } else if(br.findBankById(Integer.parseInt(id)) != null) {
                br.findBankById(Integer.parseInt(id)).showInfo();
                System.out.println("---------------");
                break;
            } else {
                System.out.println("There aren't bank with this id!");
            }
        }
        return Integer.parseInt(id);
    }

}
