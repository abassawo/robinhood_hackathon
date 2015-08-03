package abassawo.c4q.nyc.fe_nyc;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class WalletFragment extends Fragment {
    String strCash;
    boolean addCash;
    double cashInWallet = 300, transactionCash, savings, monthlyWallet = 300;
    Button add, minus, calculate;
    ImageView tree;
    TextView cashDisplay, warning;
    EditText enterAmount;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View myInflatedView = inflater.inflate(R.layout.fragment_wallet, container, false);

        add = (Button) myInflatedView.findViewById(R.id.add_button);
        minus = (Button) myInflatedView.findViewById(R.id.minus_button);
        calculate = (Button) myInflatedView.findViewById(R.id.calculate);
        tree = (ImageView) myInflatedView.findViewById(R.id.tree);
        cashDisplay = (TextView) myInflatedView.findViewById(R.id.wallet_money);
        warning = (TextView) myInflatedView.findViewById(R.id.warning);
        enterAmount = (EditText) myInflatedView.findViewById(R.id.enter_amount);

        //TODO get cash amount from shared preferences

        cashDisplay.setText(String.valueOf(cashInWallet));

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterAmount.setVisibility(View.VISIBLE);
                calculate.setVisibility(View.VISIBLE);
                addCash = false;
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterAmount.setVisibility(View.VISIBLE);
                calculate.setVisibility(View.VISIBLE);
                addCash = true;
            }
        });

        warning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ResourceActivity.class);
                startActivity(intent);
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transactionCash = Double.parseDouble(enterAmount.getText().toString());

                if (addCash) {
                    cashInWallet = cashInWallet + transactionCash;
                } else {
                    cashInWallet = cashInWallet - transactionCash;
                    if (cashInWallet < 0) {
                        cashInWallet = 0.0;
                    }
                }
                cashDisplay.setText(String.valueOf(cashInWallet));
                enterAmount.setText("");
                enterAmount.setVisibility(View.GONE);
                calculate.setVisibility(View.GONE);

                double cashPercentage = (cashInWallet / monthlyWallet) * 100;

                if (cashPercentage > 80) {
                    tree.setBackgroundResource(R.drawable.tree_full);
                    warning.setVisibility(View.GONE);
                } else if (cashPercentage > 60) {
                    tree.setBackgroundResource(R.drawable.tree_60);
                    warning.setVisibility(View.GONE);
                } else if (cashPercentage > 40) {
                    tree.setBackgroundResource(R.drawable.tree_40);
                    warning.setVisibility(View.GONE);
                } else if (cashPercentage > 20) {
                    tree.setBackgroundResource(R.drawable.tree_20);
                    warning.setVisibility(View.GONE);
                } else {
                    tree.setBackgroundResource(R.drawable.tree_empty);
                    warning.setVisibility(View.VISIBLE);

                }
            }
        });

        return myInflatedView;
    }


}



