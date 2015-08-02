package abassawo.c4q.nyc.fe_nyc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by s3a on 8/1/15.
 */
public class IncomeGoalForm  extends Activity{

    EditText rentValue;
    EditText utilValue;
    EditText foodValue;
    EditText miscValue;
    TextView total_expenses;
    TextView total_earnings;
    Button addExpenses;
    EditText incomeTextInt;
    TextView remaingBal;
    Button nextButton;
    double utilexpensesInt;
    double rentInt;
    double foodInt;
    double miscInt;
    double incomeInt;
    double totalexpensedouble;
    double remainingBalDouble;
    double totalEarningsDouble;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newuser_income_exp);

        rentValue = (EditText)findViewById(R.id.rent_value);
        utilValue =(EditText)findViewById(R.id.util_value);
        foodValue = (EditText)findViewById(R.id.food_value);
        miscValue = (EditText)findViewById(R.id.misc_value);
        incomeTextInt= (EditText)findViewById(R.id.income_value);
        total_expenses = (TextView)findViewById(R.id.expense_val);
        total_earnings = (TextView)findViewById(R.id.earnings_value);
        addExpenses = (Button) findViewById(R.id.calcbutton);
        remaingBal = (TextView)findViewById(R.id.remaining_bal_value);
          //add rent util food misc



        addExpenses.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                incomeInt =Double.parseDouble(incomeTextInt.getText().toString());
                rentInt = Double.parseDouble(rentValue.getText().toString());
                miscInt =Double.parseDouble(miscValue.getText().toString());
                utilexpensesInt =Double.parseDouble(utilValue.getText().toString());
                foodInt =Double.parseDouble(foodValue.getText().toString());

                totalexpensedouble= rentInt + miscInt + utilexpensesInt+ foodInt;
                total_expenses.setText(Double.toString(totalexpensedouble));

                totalEarningsDouble= incomeInt;
                total_earnings.setText(Double.toString(totalEarningsDouble));

                //Substract


                remainingBalDouble = incomeInt -totalexpensedouble;
                remaingBal.setText(Double.toString(remainingBalDouble));
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
               // Intent intent = new Intent(IncomeGoalForm.this, BudgetViewFragment.class);
                //startActivity(intent);
            }

        });


    }
}