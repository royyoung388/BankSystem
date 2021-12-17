package controller;

import bean.account.Account;
import bean.account.SavingAccount;

public class SavingAccountController extends AbstractAccountController {
    public SavingAccountController(SavingAccount account) {
        super(account);
    }

    public boolean withdraw(double amount) {
        return super.withdraw(amount, amount * WITHDRAW_RATE);
    }

    public boolean deposit(double amount) {
        return super.deposit(amount, 0);
    }

    public boolean transfer(int toID, double amount) {
        Account toAccount = accountModel.queryAccount(toID);
        if (toAccount.getType() == Account.AccountType.SECURITY) {
            if (amount < 1000 || account.getBalance() - amount < 2500) {
                return false;
            }
        }
        System.out.println("saving transfer");
        return transfer(toID, amount, 0);
    }

    public static void main(String[] args) {
        SavingAccountController savingAccountController=new SavingAccountController(new SavingAccount(11,6," ",80,"USD"));
//        System.out.println(savingAccountController.transfer(12,10));
//        System.out.println(savingAccountController.withdraw(10));
//        System.out.println(savingAccountController.deposit(10));




    }
}
