package com.example.mobile_dev_assignment1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mobile_dev_assignment1.databinding.FragmentFirstBinding;

import java.text.DecimalFormat;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    //initialize all the edittext, textview , and button parameters
    EditText str_priAmount;
    EditText str_intRate;
    EditText str_loanTenure;
    TextView str_totalAmount;
    Button btn_calculate;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialize the edit text values
        str_priAmount = view.findViewById(R.id.str_Principal);
        str_intRate = view.findViewById(R.id.str_Interest);
        str_loanTenure = view.findViewById(R.id.str_Tenure);

        //Initialize the textview value
        str_totalAmount = view.findViewById(R.id.str_totalAmount);

        //initialize the button value
        btn_calculate = view.findViewById(R.id.calculate_button);

        //set an onclick listener for the button to calculate the function
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                String str_total = updateTotalAmount();
                b.putString("amount", str_total);
                Navigation.findNavController(view).
                        navigate(R.id.action_FirstFragment_to_SecondFragment, b);
            }
        });

    }
    //function to calculate the monthly EMI using user input values
    public String updateTotalAmount(){
        //get the input from the user, cast it to double to do calculation
        double principal = Double.parseDouble(str_priAmount.getText().toString());
        double interest = Double.parseDouble(str_intRate.getText().toString());
        double tenure = Double.parseDouble(str_loanTenure.getText().toString());

        //calculate the rate and the power first
        double rate = (interest/12)/100;
        double pow1 = Math.pow(1+rate,tenure);

        //get the result
        double dbl_result = principal*rate*pow1 / (pow1-1);
        DecimalFormat df = new DecimalFormat("####0.00");

        //return the value as a string
        return df.format(dbl_result);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}