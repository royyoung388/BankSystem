package controller;

import bean.account.CheckingAccount;

public class CheckingAccountController extends AbstractAccountController {
    public CheckingAccountController(CheckingAccount checkingAccount) {
        super(checkingAccount);
    }

    public boolean withdraw(double amount) {
        return false;
    }

    public boolean deposit(double amount) {
        return false;
    }

    public boolean transfer(int toID, double amount) {
        return transfer(toID, amount, amount * CHECKING_RATE);
    }

    public boolean transfer(double amount) {
        return false;
    }
}
