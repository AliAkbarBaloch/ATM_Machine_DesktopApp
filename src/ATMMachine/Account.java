package ATMMachine;

import com.sun.jna.platform.win32.WinBase;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Account {
    Scanner menuInput=new Scanner(System.in);
    DecimalFormat moneyFormat=new DecimalFormat("'$'####,##0.00");
    private int CustomerNumber,PinNumber;
    double checkBalance=0,saveBalance=0;
    public void setCustomerNumber(int CustomerNumber){
        this.CustomerNumber=CustomerNumber;

    }
    public int getCustomerNumber(){
        return CustomerNumber;
    }
    public void  setPinNumber(int PinNumber){
        this.PinNumber=PinNumber;
    }
    public int getPinNumber(){
        return PinNumber;
    }
    public double getCheckingBalance(){
        return checkBalance;
    }
    public double getSavingBalance(){
        return saveBalance;
    }
    public double calCheckingWithdrawal(double amount){
        checkBalance=(checkBalance-amount);
        return checkBalance;
    }

    public double calcSavingWithdrawal(double amount) {
        saveBalance=(saveBalance-amount);
        return saveBalance;
    }
    public double calcCheckingDeposit(double amount){
        checkBalance=(checkBalance+amount);
        return checkBalance;
    }
    public double calcSavingDeposit(double amount){
        saveBalance=(saveBalance+amount);
        return saveBalance;
    }
    public void getWithdrawal(){
        System.out.println("Checking Account Balance "+moneyFormat.format(checkBalance));
        double amount=menuInput.nextDouble();
        if((checkBalance-amount)>=0){
            calCheckingWithdrawal(amount);
            System.out.println("New Checking Account Balance "+moneyFormat.format(checkBalance));

        }else{
            System.out.println("Negative Can not Withdrawal");
        }
    }
    public void getWithdrawalSaving(){
        System.out.println("Saving Account Balance "+moneyFormat.format(saveBalance));
        double amount=menuInput.nextDouble();
        if((saveBalance-amount)>=0){
            calcSavingWithdrawal(amount);
            System.out.println("New Saving Account Balance "+moneyFormat.format(saveBalance));
        }
        else{
            System.out.println("Balance Can Not be Negative");
        }
    }
    public void getDeposit(){
        System.out.println("Checking Account Balance "+moneyFormat.format(checkBalance));
        double amount=menuInput.nextDouble();
        if((checkBalance+amount)>=0){
            calcCheckingDeposit(amount);
            System.out.println("New Checking Account Balance "+moneyFormat.format(checkBalance));

        }else{
            System.out.println("Negative Can not Withdrawal");
        }
    }
    public void getDepositSaving(){
        System.out.println("Saving Account Balance "+moneyFormat.format(saveBalance));
        double amount=menuInput.nextDouble();
        if((saveBalance+amount)>=0){
            calcSavingDeposit(amount);
            System.out.println("New Saving Account Balance "+moneyFormat.format(saveBalance));
        }
        else{
            System.out.println("Balance Can Not be Negative");
        }
    }

}
