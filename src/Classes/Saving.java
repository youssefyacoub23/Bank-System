package Classes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Saving extends Client{
    protected int clientBalanceLimit;
    private static Scanner cin = new Scanner(System.in);
    private static LocalDateTime dateNow = LocalDateTime.now();
    private static DateTimeFormatter dateNowFormated = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Saving(String clientName, String clientPassword, String clientAccNum , int clientAge , int clientBalanceLimit) {
        this.clientName = clientName;
        this.clientAccNum = clientAccNum;
        this.clientAge = clientAge;
        this.clientPassword = clientPassword;
        this.accType = "Saving Account";
        this.clientBalanceLimit = clientBalanceLimit;
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

        switch( optionDeposit ){
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

        int checkLimit = clientBalance - amount;

        while(amount > clientBalance || amount < 0 || checkLimit < clientBalanceLimit)
        {
            if (amount > clientBalance) {
                System.out.println("Low Balance!");
                withdraw();
            } else if (amount < 0) {
                System.out.println("Please enter a valid amount!");
                withdraw();
            } else if (checkLimit < clientBalanceLimit) {
                System.out.println("You are going off limit!");
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