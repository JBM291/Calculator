package com.mills.b.joshua.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;
    private int[] numberButtonIDList = {
            R.id.button0, R.id.button1, R.id.button2,
            R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8,
            R.id.button9, R.id.buttonDot,
    };
    private int[] operationButtonIDList = {
            R.id.buttonEquals, R.id.buttonDivide, R.id.buttonMultiply,
            R.id.buttonMinus, R.id.buttonPlus
    };

    private Double operand1 = null;
    private String pendingOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayOperation = findViewById(R.id.operation);
        result = findViewById(R.id.result);
        newNumber = findViewById(R.id.newNumber);
        setNumberButtonListener();
        setOperationButtonListener();
    }

    private void setOperationButtonListener() {
        for (int id : operationButtonIDList) {
            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String op = ((Button) v).getText().toString();
                    String value = newNumber.getText().toString();
                    try {
                        Double doubleValue = Double.valueOf(value);
                        performOperation(doubleValue, op);
                    } catch (NumberFormatException e) {
                        newNumber.setText("");
                    }
                    pendingOperation = op;
                    displayOperation.setText(pendingOperation);
                }
            });
        }
    }

    private void setNumberButtonListener() {
        for (int id : numberButtonIDList) {
            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newNumber.append(((Button) v).getText().toString());
                }
            });
        }

    }

    private void performOperation(Double value, String op) {
        if (operand1 == null) {
            operand1 = value;
        } else {
            if (pendingOperation.equals("=")) {
                pendingOperation = op;
            }
            switch (pendingOperation) {
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if (value == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= value;
                    }
                    break;
                case "*":
                    operand1 *= value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "+":
                    operand1 += value;
                    break;

            }

        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }
}