package kmitl.lab09.thasorn58070047.moneyflow.model;

public class Balance {

    private int sum_income;
    private int sum_expense;

    //Amount of income
    public int getSum_income() {
        return sum_income;
    }

    public void setSum_income(int sum_income) {
        this.sum_income = sum_income;
    }

    //amount of expense
    public int getSum_expense() {
        return sum_expense;
    }

    public void setSum_expense(int sum_expense) {
        this.sum_expense = sum_expense;
    }

    //amount of balance
    public int getBalance() {
        return sum_income - sum_expense;
    }
}
