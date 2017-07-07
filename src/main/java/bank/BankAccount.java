package bank;

public class BankAccount {

    int state;

    public BankAccount(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    void takeMoney(int amount) {
        state -= amount;
    }
}
