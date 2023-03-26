package ATMMachine;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OptionMenu extends Account{
    Scanner menuInput=new Scanner(System.in);
    DecimalFormat moneyFormat=new DecimalFormat("'$'####,##0.00");
    HashMap<Integer,Integer> data=new HashMap<Integer, Integer>();
    public void getLogin() throws IOException{
        int x=1;
        do{
            try{
                data.put(12345,12);
                data.put(12345,12);
                System.out.println("Welcome to ATM");
                System.out.println("Enter you Customer Number");
                setCustomerNumber(menuInput.nextInt());

                System.out.println("Enter you Pin");
                setPinNumber(menuInput.nextInt());


            }catch (Exception e){
                System.out.println("\nInvalid Characters Only Number will be applicable");
                x=2;
            }
            for(Map.Entry<Integer,Integer> entry : data.entrySet()){
                if(entry.getKey()==getCustomerNumber() && entry.getValue()==getPinNumber()){
                    getAccountType();
                }
                else{
                    System.out.println("Wrong Customer Number Or Pin");
                }
            }

        }while (x==1);

    }
    public void getAccountType(){
        System.out.println("Select Account Type");
        System.out.println("1. Current");
        System.out.println("2. Saving");
        System.out.println("3. Exit");
        int selection=menuInput.nextInt();

        switch (selection){
            case 1:
                getChecking();
                break;
            case 2:
                getSaving();
                break;
            case 3:
                System.out.println("Thank you for Using ATM, Bye");
                break;
            default:
                System.out.println("Invalid Choice ");
                getAccountType();
        }
    }
    public void getChecking(){
        System.out.println("Current Accout\n");
        System.out.println("1. View Balance");
        System.out.println("2. Withdrawal Funds");
        System.out.println("3. Deposit Funds");
        System.out.println("4 Exit");

        int selection=menuInput.nextInt();

        switch (selection){
            case 1:
                System.out.println("Checking Account Balance"+moneyFormat.format(getCheckingBalance()));
                getAccountType();
                break;
            case 2:
                getWithdrawal();
                getAccountType();
                break;
            case 3:
                getDeposit();
                getAccountType();
                break;
            case 4:
                System.out.println("Thank for using ATM, Bye");
                break;
            default:
                System.out.println("Wrong Input");
                getChecking();
        }
    }
    public void getSaving(){
        System.out.println("Current Accout\n");
        System.out.println("1. View Balance");
        System.out.println("2. Withdrawal Funds");
        System.out.println("3. Deposit Funds");
        System.out.println("4 Exit");

       int selection=menuInput.nextInt();

        switch (selection){
            case 1:
                System.out.println("Checking Account Balance"+moneyFormat.format(getSavingBalance()));
                getAccountType();
                break;
            case 2:
                getWithdrawalSaving();
                getAccountType();
                break;
            case 3:
                getDepositSaving();
                getAccountType();
                break;
            case 4:
                System.out.println("Thank for using ATM, Bye");
                break;
            default:
                System.out.println("Wrong Input");
                getSaving();
        }
    }

}
