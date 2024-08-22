package Classes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
public class Interface{
    //Testing Account
    private static Checking exampleAccChecking = new Checking("test account","testtest1","00000000",18);
    //Users Array Lists
    private static ArrayList<Checking> checkingArrayList = new ArrayList<Checking>(Arrays.asList(exampleAccChecking));
    private static ArrayList<Saving> savingArrayList = new ArrayList<Saving>();
    private static ArrayList<Current> currentArrayList = new ArrayList<Current>();
    private static Scanner cin = new Scanner(System.in);
    protected static String name , password , accNum , passcode , currentList;
    private static int age , currentAccUser ,limit;

    public static void startUpMenu() throws InterruptedException {
        printAnimation("\nHow can we assist you?");
        System.out.println("[1] Join Us! ");
        System.out.println("[2] Log in ");
        System.out.println("[3] Learn about our accounts types");
        System.out.println("[4] Exit");

        int optionStartUpMenu = cin.nextInt();

        switch(optionStartUpMenu){
            case 1 :
                createNewUser();
                break;
            case 2 :
                loginScreen();
                break;
            case 3 :
                System.out.println("-----Saving account-----");
                System.out.println("A savings account is a type of deposit account that allows individuals to save money while earning interest on their balance.");
                System.out.println("These accounts are typically used for long-term savings goals or emergency funds.");
                System.out.println("Interest rates on savings accounts are generally lower than other investment options but provide a secure and liquid way to store funds.");
                System.out.println("They are suitable for individuals who want to build up savings gradually over time.\n");
                System.out.println("-----Checking account-----");
                System.out.println("A checking account is a standard bank account that offers easy access to funds for everyday transactions.");
                System.out.println("It allows account holders to write checks, use debit cards, and make electronic transfers.");
                System.out.println("Unlike savings accounts, checking accounts are more geared towards daily spending and do not usually accrue significant interest.");
                System.out.println("Checking accounts often come with features like overdraft protection and can be linked to other financial products.");
                System.out.println("They are suitable for individuals who need regular access to their money and frequently engage in transactions.\n");
                System.out.println("-----Current account-----");
                System.out.println("A current account is a type of bank account designed for frequent transactions and business use.");
                System.out.println("It provides a range of services such as check-writing, electronic fund transfers, and debit card usage.");
                System.out.println("Unlike savings accounts, current accounts generally offer little to no interest on the balance.");
                System.out.println("These accounts are ideal for individuals or businesses that need quick and easy access to their funds for daily financial activities.");
                System.out.println("Current accounts often have lower restrictions on the number of transactions allowed compared to savings accounts.\n");
                System.out.println("[1] Back to main menu");

                int optionAccTypes = cin.nextInt();

                if (optionAccTypes == 1) startUpMenu();
                else{

                    do{
                        printAnimation("Please stick with the options provided!\n");
                        optionAccTypes = cin.nextInt();
                    }while(optionAccTypes != 1);

                    startUpMenu();
                }
                break;
            case 4 :
                System.exit(0);
                break;
            default:
                printAnimation("Please stick with the options provided!\n");
                startUpMenu();
        }

    }
    //Creating a new user.
    public static void createNewUser() throws InterruptedException {
        printAnimation("Great choice! You're one step closer to your premium banking experience!");
        printAnimation("May we have your name sir?");
        cin.nextLine();
        name = cin.nextLine();
        //Checking if the name contains numbers or other special characters.
        if( checkString(name) == false ){

            do{
                printAnimation("Please enter a valid name!");
                name = cin.next();
            }while(checkString(name) == false);

        }

        printAnimation("And how old are you?");
        age = cin.nextInt();
        //Checking if the client is older than 18
        if (age < 18){

            printAnimation("We're sorry but our regulations don't allow minors to open an account with us.\nBut we wish you a good day!");
            Thread.sleep(1500);
            System.exit(0);

        }

        printAnimation("What type of account do you wish to open?");
        System.out.println("[1] Checking");
        System.out.println("[2] Saving");
        System.out.println("[3] Current");
        
        int optionAccType = cin.nextInt();

        if (optionAccType != 1 && optionAccType != 2 && optionAccType != 3) {

            do {
                printAnimation("Please stick with the options provided!");
                optionAccType = cin.nextInt();
            } while (optionAccType != 1 || optionAccType != 2 || optionAccType != 3);
            
            
        }
        //Taking his password and checking if another user has the same password
        printAnimation("Now for your password?");
        inputPassword();

        printAnimation("Last but not least set a 4-digits passcode");
        passcode = cin.next();
        //Checking if passcode is 4 digits and only contains numbers
        if( checkStringNum(passcode) == false ){

            do{
                printAnimation("Please enter a valid passcode!");
                passcode = cin.next();
            }while(checkStringNum(passcode) == false);

        }
        if (optionAccType == 2) {
            printAnimation("Please set a limit for your balance:");
            limit = cin.nextInt();
        }
        //Generating a random account number
        accNum = Integer.toString((int)(Math.random()*100000000));
        printAnimation("Congratulations on becoming a member of our community!\nThis is your account number: "+accNum);
        System.out.println("\nPlease wait until we redirect you to the login screen!");

        switch (optionAccType) {
            case 1:
                addCheckingUser();
                break;
            case 2:
                addSavingUser();
                break;
            case 3:
                addCurrentUser();
                break;
        }

        Thread.sleep(3000);
        loginScreen();

    }
    //Taking his password and checking if another user has the same password
    protected static void inputPassword() throws InterruptedException {

        password = cin.next();

        for ( int checkPassCheckLoop = 0 ; checkPassCheckLoop < checkingArrayList.size() ; checkPassCheckLoop++ ){

            if (Objects.equals(password,checkingArrayList.get(checkPassCheckLoop).clientPassword)){

                    printAnimation("The password you chose was already taken by another client..");
                    System.out.println("Please choose another password!");
                    inputPassword();

            }

        }

        for ( int checkPassSavLoop = 0 ; checkPassSavLoop < savingArrayList.size() ; checkPassSavLoop++ ){

            if (Objects.equals(password,savingArrayList.get(checkPassSavLoop).clientPassword)){

                    printAnimation("The password you chose was already taken by another client..");
                    System.out.println("Please choose another password!");
                    inputPassword();

            }

        }

        for ( int checkPassCurLoop = 0 ; checkPassCurLoop < currentArrayList.size() ; checkPassCurLoop++ ){

            if (Objects.equals(password,currentArrayList.get(checkPassCurLoop).clientPassword)){

                    printAnimation("The password you chose was already taken by another client..");
                    System.out.println("Please choose another password!");
                    inputPassword();

            }

        }
    }
    //Adding a new user to his desired account type with the attributes he chose.
    public static void addCheckingUser(){
        Checking checkingUser = new Checking(name, password , accNum , age);
        checkingArrayList.add(checkingUser);
    }

    public static void addSavingUser(){

        Saving savingUser = new Saving(name, password , accNum , age , limit);
        savingArrayList.add(savingUser);
    }
    public static void addCurrentUser(){
        Current currentUser = new Current(name, password , accNum , age);
        currentArrayList.add(currentUser);
    }
    public static void loginScreen() throws InterruptedException {
        String sampleName , samplePassword;

        System.out.println("==========================");
        System.out.println("Welcome to Money Hub bank!");
        System.out.println("==========================");

        cin.nextLine();
        printAnimation("\nPlease enter your name:");
        sampleName = cin.nextLine();
        printAnimation("\nPlease enter your password:");
        samplePassword = cin.nextLine();
        //Checking all three Array Lists to search the name and password he entered and log him in
        for (int checkingloop = 0 ; checkingloop < checkingArrayList.size()  ; checkingloop++ ){

            if((Objects.equals(sampleName , checkingArrayList.get(checkingloop).clientName))
                    && (Objects.equals(samplePassword , checkingArrayList.get(checkingloop).clientPassword))){

                currentAccUser = checkingloop;
                currentList = "Checking";

                System.out.println("Checking name and password..");
                Thread.sleep(2500);
                System.out.println("You're logged in successfully!\n");
                Thread.sleep(2000);
                mainMenu();
            }
        }

        for (int savingloop = 0 ; savingloop < savingArrayList.size()  ; savingloop++ ){

            if((Objects.equals(sampleName , savingArrayList.get(savingloop).clientName))
                    && (Objects.equals(samplePassword , savingArrayList.get(savingloop).clientPassword))){

                currentAccUser = savingloop;
                currentList = "Saving";

                System.out.println("Checking name and password..");
                Thread.sleep(2500);
                System.out.println("You're logged in successfully!\n");
                Thread.sleep(2000);
                mainMenu();

            }

        }

        for (int currentloop = 0 ; currentloop < currentArrayList.size()  ; currentloop++ ){

            if((Objects.equals(sampleName , currentArrayList.get(currentloop).clientName))
                    && (Objects.equals(samplePassword , currentArrayList.get(currentloop).clientPassword))){

                currentAccUser = currentloop;
                currentList = "Current";

                System.out.println("Checking name and password..");
                Thread.sleep(2500);
                System.out.println("You're logged in successfully!\n");
                Thread.sleep(2000);
                mainMenu();

            }

        }

        System.out.println("Checking name and password..");
        Thread.sleep(2500);
        System.out.println("Either your name or password are incorrect.\nPlease try again!\n");
        Thread.sleep(2000);
        loginScreen();
    }
    public static void mainMenu() throws InterruptedException {
        System.out.println("-----MoneyHub-----\n");
        System.out.println("[1] Check Balance");
        System.out.println("[2] Deposit");
        System.out.println("[3] Withdraw");
        System.out.println("[4] View transactions history");
        System.out.println("[5] Display account details");
        System.out.println("[6] Update your password");
        System.out.println("[7] Log out");
        System.out.println("[8] Exit");

        int optionMainMenu = cin.nextInt();

        switch (optionMainMenu){
            case 1:
                switch (currentList){

                    case "Checking" :
                        checkingArrayList.get(currentAccUser).checkBalance();
                        break;
                    case "Saving" :
                        savingArrayList.get(currentAccUser).checkBalance();
                        break;
                    case "Current" :
                        currentArrayList.get(currentAccUser).checkBalance();
                        break;

                }
                break;
            //Deposit
            case 2:
                printAnimation("\nPlease enter your 4-digits passcode:");
                checkPasscode(cin.next());

                switch (currentList){

                    case "Checking" :
                        checkingArrayList.get(currentAccUser).deposit();
                        break;
                    case "Saving" :
                        savingArrayList.get(currentAccUser).deposit();
                        break;
                    case "Current" :
                        currentArrayList.get(currentAccUser).deposit();
                        break;

                }
                break;
            //Withdraw
            case 3:
                printAnimation("\nPlease enter your 4-digits passcode:");
                checkPasscode(cin.next());

                switch (currentList){

                    case "Checking" :
                        checkingArrayList.get(currentAccUser).withdraw();
                        break;
                    case "Saving" :
                        savingArrayList.get(currentAccUser).withdraw();
                        break;
                    case "Current" :
                        currentArrayList.get(currentAccUser).withdraw();
                        break;

                }
                break;
            //Show Transactions History
            case 4:
                switch (currentList){
                    case "Checking" :
                        checkingArrayList.get(currentAccUser).showHistory();
                        break;
                    case "Saving" :
                        savingArrayList.get(currentAccUser).showHistory();
                        break;
                    case "Current" :
                        currentArrayList.get(currentAccUser).showHistory();
                        break;
                }
                break;
            //Show account details
            case 5:
                switch (currentList){
                    case "Checking" :
                        checkingArrayList.get(currentAccUser).showDetails();
                        break;
                    case "Saving" :
                        savingArrayList.get(currentAccUser).showDetails();
                        break;
                    case "Current" :
                        currentArrayList.get(currentAccUser).showDetails();
                        break;
                }
                break;
            //Update password
            case 6:
                printAnimation("Enter your old password please..");
                String sampleOldPassword = cin.next();

                switch (currentList){
                    case "Checking" :
                        if (!Objects.equals(sampleOldPassword,checkingArrayList.get(currentAccUser).clientPassword)){

                            do {

                                System.out.println("Your password seems to be wrong try again!");
                                sampleOldPassword = cin.next();

                            }while (!Objects.equals(sampleOldPassword,checkingArrayList.get(currentAccUser).clientPassword));

                        }
                        checkingArrayList.get(currentAccUser).updatePassword();
                        break;
                    case "Saving" :
                        if (!Objects.equals(sampleOldPassword,savingArrayList.get(currentAccUser).clientPassword)){

                            do {

                                System.out.println("Your password seems to be wrong try again!");
                                sampleOldPassword = cin.next();

                            }while (!Objects.equals(sampleOldPassword,savingArrayList.get(currentAccUser).clientPassword));

                        }
                        savingArrayList.get(currentAccUser).updatePassword();
                        break;
                    case "Current" :
                        if (!Objects.equals(sampleOldPassword,currentArrayList.get(currentAccUser).clientPassword)){

                            do {

                                System.out.println("Your password seems to be wrong try again!");
                                sampleOldPassword = cin.next();

                            }while (!Objects.equals(sampleOldPassword,currentArrayList.get(currentAccUser).clientPassword));

                        }
                        currentArrayList.get(currentAccUser).updatePassword();
                        break;
                }
                break;
            //Log Out
            case 7:
                System.out.println("Logging out..");
                Thread.sleep(3000);
                startUpMenu();
                break;
            //Exit
            case 8:
                System.exit(0);
                break;
            default:
                printAnimation("Please stick with the options provided!\n\n");
                mainMenu();
        }
    }
    //Checking string to see if it contains anything other than letters
    public static Boolean checkString(String textToBeChecked){
        char[] charArray = textToBeChecked.toCharArray();
        Boolean isValid = false;

        for ( char i : charArray){
            if( (i >=  65 && i <= 122) || i == 32 ) {
                isValid = true;
            }
        }
        return isValid;
    }
    //Checking if string is 4 digits with only numbers
    public static Boolean checkStringNum(String textToBeChecked){
        char[] charArray = textToBeChecked.toCharArray();
        Boolean isValid = true;

        if(charArray.length != 4) return false;

        for ( char i : charArray){
            if( i <  47 || i > 57 ) {
                isValid = false;
            }
        }

        return isValid;
    }
    //Validating the passcode with only 3 attempts to enter it correctly
    public static void checkPasscode(String samplePasscode) throws InterruptedException {
        if( !Objects.equals(samplePasscode, passcode)){

            int countAttempts = 3;

            while(!Objects.equals(samplePasscode, passcode) && countAttempts != 0 ){
                System.out.println("Checking passcode..");
                Thread.sleep(2000);
                System.out.printf("\nIncorrect Passcode!\nYou have %d more attempts..\n " , countAttempts);
                printAnimation("Please re-enter your passcode:");
                samplePasscode = cin.next();
                countAttempts--;
            }

            if (!Objects.equals(samplePasscode, passcode)){
                printAnimation("You entered an incorrect passcode 3 times!\nAnd unfortunately according to our security terms and regulations,\n");
                printAnimation("We are obligated to lock the account!\nFor more information visit our website.");
                printAnimation("We wish you a good day!");
                Thread.sleep(2000);
                System.exit(0);
            }else{
                System.out.println("Checking passcode..");
                Thread.sleep(2000);
                System.out.println("Passcode entered successfully!\n");
                Thread.sleep(2000);
            }
        }
    }
    //Printing a string letter by letter
    public static void printAnimation( String textSample ) throws InterruptedException {
        char[] charArray = textSample.toCharArray();
        for ( char i : charArray ) {
            System.out.print(i);
            Thread.sleep(90);
        }

        System.out.print("\n");
    }
}