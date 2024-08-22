package Classes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Checking extends Client{
    private static Scanner cin = new Scanner(System.in);
    private static LocalDateTime dateNow = LocalDateTime.now();
    private static DateTimeFormatter dateNowFormated = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    //Parameterized Constructor
    public Checking(String clientName, String clientPassword, String clientAccNum , int clientAge) {
        this.clientName = clientName;
        this.clientAccNum = clientAccNum;
        this.clientAge = clientAge;
        this.clientPassword = clientPassword;
        this.accType = "Checking Account";
    }
    //Depositing an amount
    @Override
    public void deposit() throws InterruptedException {
        System.out.println("Please enter the amount you want to deposit:");
        int amount = cin.nextInt();

        while( amount < 0 )
        {
            System.out.println("Please try again ");
            deposit();
        }
        clientBalance = clientBalance + amount ;

        dateNow = LocalDateTime.now();
        dateArrayList.add(dateNow.format(dateNowFormated));
        amountArrayList.add(Integer.toString(amount) + "$");
        transTypeArrayList.add("Deposit");

        System.out.println("The amount was added successfully to your account!");
        System.out.println("Your current balance : " + clientBalance + "$\n");
        System.out.println("[1] Back to main menu");
        System.out.println("[2] Deposit another amount");

        int optionDeposit = cin.nextInt();

        switch(optionDeposit){
            case 1:
                Interface.mainMenu();
                break;
            case 2:
                deposit();
                break;
            default:
                System.out.println("Please stick with the options provided!\n");
        }
    }
    //Withdraw
    @Override
    public void withdraw() throws InterruptedException {
        System.out.println("Please enter the amount you want to withdraw:");
        int amount = cin.nextInt();

        while(amount > clientBalance || amount < 0 )
        {
            if (amount > clientBalance) {
                System.out.println("Low Balance!");
                withdraw();
            }
            if (amount < 0) {
                System.out.println("Please enter a valid amount!");
                withdraw();
            }
        }
        clientBalance = clientBalance - amount ;

        dateNow = LocalDateTime.now();
        dateArrayList.add(dateNow.format(dateNowFormated));
        amountArrayList.add("-" + Integer.toString(amount) + "$");
        transTypeArrayList.add("Withdrawal");

        System.out.println("The amount was withdrawn successfully out of your account!");
        System.out.println("Your current balance : " + clientBalance + "$\n");
        System.out.println("[1] Back to main menu");
        System.out.println("[2] Withdraw another amount");

        int optionWithdraw = cin.nextInt();

        switch( optionWithdraw ){
            case 1:
                Interface.mainMenu();
                break;
            case 2:
                withdraw();
                break;
            default:
                System.out.println("Please stick with the options provided!\n");
        }
    }
}