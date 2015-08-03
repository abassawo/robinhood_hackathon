package abassawo.c4q.nyc.fe_nyc;

/**
 * Created by c4q-Abass on 8/1/15.
 */
public class Account {

    public static double getSpendable() {
        return spendable;
    }

    private static double savingGoal;
    private static  double spendable;
    private static  double currentSavings;
    private static  double income;
    private static  double expenses;
    private static double foodPct, rentPct, miscPct, transPct;
    private String name;

    public static void main(String[] args) {
        //fixme
        savingGoal = .20 * income;
        rentPct = .3 * income;
        foodPct = .09 * income;
        miscPct = .10 * income;
        transPct = .20 * income;
        expenses = rentPct + foodPct + miscPct + transPct;
        spendable = income - (expenses + savingGoal); //full. for daily divide this by 30.
    }

    public Account(double income){
        this.income = income;

    }
}
