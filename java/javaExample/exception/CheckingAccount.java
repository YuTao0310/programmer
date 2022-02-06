package exception;


public class CheckingAccount extends Account {
    private double overdraftProtection;

    public CheckingAccount(double balance) {
        super(balance);
        this.overdraftProtection = Math.random()*10000 + 100;
    }
    public CheckingAccount(double balance, double protect) {
        super(balance);
        this.overdraftProtection = protect;
    }

    public void withDraw(double amt) throws OverdraftException {
        double err = amt - balance;
        if (err > overdraftProtection){
            OverdraftException e =  new OverdraftException("cheking account is overdrawn", err - overdraftProtection);
            System.out.println("the overdraft is " + e.getDeficit());
            throw e;
        }
        balance -= amt;
    }
    public static void main(String[] args) {
        CheckingAccount c = new CheckingAccount(1000, 1000);
        try {
            c.withDraw(1000);
            c.withDraw(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
