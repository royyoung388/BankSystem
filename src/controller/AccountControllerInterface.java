package controller;

public interface AccountControllerInterface {
     boolean deposit(double amount);
    boolean withdraw(double amount);
    boolean transfer(double amount);
}
