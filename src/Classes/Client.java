package Classes;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Client {
    private static Scanner cin = new Scanner(System.in);
    protected String clientName , clientPassword , clientAccNum , accType;
    protected int clientBalance , clientAge;
    protected static ArrayList<String> dateArrayList = new ArrayList<String>();
    protected static ArrayList<String> amountArrayList = new ArrayList<String>();
    protected static ArrayList<String> transTypeArrayList = new ArrayList<String>();
    public abstract void deposit() throws InterruptedException;
    public abstract void withdraw() throws InterruptedException;
    public void checkBalance() throws InterruptedException {
        System.out.println("Your current balance is: " + clientBalance + "$");
        Thread.sleep(4000);
        Interface.mainMenu();
    }
    public void showHistory() throws InterruptedException {

        for (int i = 0 ; i < dateArrayList.size() ; i++){
            System.out.println(dateArrayList.get(i)+" "+ amountArrayList.get(i)+" "+ transTypeArrayList.get(i));
        }
        Thread.sleep(4000);
        Interface.mainMenu();
    }
    public void showDetails() throws InterruptedException {

        System.out.println("------Name------");
        System.out.println(clientName);
        System.out.println("------Age------");
        System.out.println(clientAge);
        System.out.println("------Account Number------");
        System.out.println(clientAccNum);
        System.out.println("------Account Type------");
        System.out.println(accType);
        Thread.sleep(4000);
        Interface.mainMenu();

    }
    public void updatePassword() throws InterruptedException {

        System.out.println("Enter your new password..");
        Interface.inputPassword();
        clientPassword = Interface.password;
        System.out.println("Changing your password..");
        Thread.sleep(2000);
        System.out.println("Password changed successfully!");
        Interface.mainMenu();

    }
}