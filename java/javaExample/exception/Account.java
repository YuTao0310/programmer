package exception;

public class Account {
    protected double balance;

    public Account(){

        
    }
    public Account(double balance) {
        this.balance =balance;
    }   

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double money) {
        this.balance += money;
    }

    public void withDraw(double money) throws OverdraftException{
        if (money > balance) {
            throw new OverdraftException("count is overdrawn and the overdraft is" + (money - balance), money - balance);
        }
        this.balance -= money;
    }

    class OverdraftException extends Exception {
        private double deficit;
        public OverdraftException(String message, double deficit) {
            super(message);
            this.deficit = deficit;
        }
        public double getDeficit() {
            return this.deficit;
        }
    }
}
