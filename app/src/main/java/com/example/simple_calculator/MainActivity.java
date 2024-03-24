package com.example.simple_calculator;

import static com.example.simple_calculator.R.id.*;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String number;
    private double firstNumber = 0.0;
    private double lastNumber = 0.0;
    private String status;
    private boolean operator = false;
    private DecimalFormat myFormatter = new DecimalFormat("######.######");
    //private String history;
    private String currentResult;
    private boolean dot = true;
    private boolean btnAcControl = true;
    private boolean btnEqualsControl = false;


    private TextView textViewHistory;
    private TextView textViewResult;


    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btnAC;
    Button btnDel;
    Button btnPlus;
    Button btnMinus;
    Button btnMultiply;
    Button btnDivide;
    Button btnEquals;
    Button btnDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // initialize
        textViewHistory = findViewById(R.id.textViewHistory);
        textViewResult = findViewById(R.id.textViewResult);

        status = ""; // Initialize status here

        //initial all button reference
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnAC = findViewById(R.id.btnAC);
        btnDel = findViewById(R.id.btnDel);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiply = findViewById(R.id.btnMultiple);
        btnDivide = findViewById(R.id.btnDivide);
        btnEquals = findViewById(R.id.btnEquals);
        btnDot = findViewById(R.id.btnDot);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnAC.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnEquals.setOnClickListener(this);
        btnDot.setOnClickListener(this);
    }

    //
    @Override
    public void onClick(View v) {
        if (v != null) {
            int viewId = v.getId(); // Get the ID of the clicked view
            if (viewId == R.id.btn0) {
                numberClick("0");
            } else if (viewId == R.id.btn1) {
                numberClick("1");
            } else if (viewId == R.id.btn2) {
                numberClick("2");
            } else if (viewId == R.id.btn3) {
                numberClick("3");
            } else if (viewId == R.id.btn4) {
                numberClick("4");
            } else if (viewId == R.id.btn5) {
                numberClick("5");
            } else if (viewId == R.id.btn6) {
                numberClick("6");
            } else if (viewId == R.id.btn7) {
                numberClick("7");
            } else if (viewId == R.id.btn8) {
                numberClick("8");
            } else if (viewId == R.id.btn9) {
                numberClick("9");
            } else if (viewId == R.id.btnAC) {
                btnACClick();
            } else if (viewId == R.id.btnDel) {
                btnDelClick();
            } else if (viewId == R.id.btnPlus) {
                btnPlusClick();
            } else if (viewId == R.id.btnMinus) {
                btnMinusClick();
            } else if (viewId == R.id.btnMultiple) {
                btnMultiplyClick();
            } else if (viewId == R.id.btnDivide) {
                btnDivideClick();
            } else if (viewId == R.id.btnEquals) {
                btnEqualsClick();
            } else if (viewId == R.id.btnDot) {
                btnDotClick();
            }
        }
    }


    private void numberClick(String view) {
        if (number == null) {
            number = view;
            if (textViewHistory.getText().toString().endsWith("+")
                    || textViewHistory.getText().toString().endsWith("-")
                    || textViewHistory.getText().toString().endsWith("*")
                    || textViewHistory.getText().toString().endsWith("/"))
            {
                // add history textview if it finish with operator like + - / *
                textViewHistory.append(number + "");
            }
        } else if (btnEqualsControl) {
            firstNumber = 0.0;
            lastNumber = 0.0;
            number = view;

        } else {
            number += view;

        }
        textViewResult.setText(number);
        operator = true;
        btnAcControl = false;
        btnEqualsControl = false;
    }


    private void btnACClick() {
        number = null;
        status = "";
        textViewResult.setText("0");
        textViewHistory.setText("");
        firstNumber = 0.0;
        lastNumber = 0.0;
        dot = true;
        btnAcControl = true;
    }

    private void btnDelClick() {
        if (btnAcControl) {
            textViewResult.setText("0");
        } else {
            if (number != null) {
                number = number.substring(0, number.length() - 1);
                if (number.isEmpty()) {
                    btnDel.setClickable(false);
                } else {
                    dot = !number.contains(".");
                }
                textViewResult.setText(number);
            }
        }
    }

//private void btnDelClick() {
//    if (btnAcControl) {
//        textViewResult.setText("0");
//    } else {
//        if (number != null) {
//            number = number.substring(0, number.length() - 1);
//            dot = !number.contains(".");
//            textViewResult.setText(number);
//        }
//    }
//}

    private void btnPlusClick() {
        //history = textViewHistory.getText().toString(); // Line 195
        currentResult = textViewResult.getText().toString();
        //textViewHistory.setText(history + currentResult + "+");
        textViewHistory.setText(currentResult + "+");

        if (operator) {
            switch (status) {
                case "multiplication":
                    multiply();
                    break;
                case "division":
                    divide();
                    break;
                case "subtraction":
                    minus();
                    break;
                default:
                    plus();
                    break;
            }
        }
        status = "sum";
        operator = false;
        number = null;
    }


    private void btnMinusClick() {
        //history = textViewHistory.getText().toString();
        currentResult = textViewResult.getText().toString();
        //textViewHistory.setText(history + currentResult + "-");
        textViewHistory.setText(currentResult + "-");
        if (operator) {
            switch (status) {
                case "multiplication":
                    multiply();
                    break;
                case "division":
                    divide();
                    break;
                case "sum":
                    plus();
                    break;
                default:
                    minus();
                    break;
            }
        }
        status = "subtraction";
        operator = false;
        number = null;
    }

    private void btnMultiplyClick() {
        //history = textViewHistory.getText().toString();
        currentResult = textViewResult.getText().toString();
        //textViewHistory.setText(history + currentResult + "*");
        textViewHistory.setText(currentResult + "*");

        if (operator) {
            switch (status) {
                case "sum":
                    plus();
                    break;
                case "division":
                    divide();
                    break;
                case "subtraction":
                    minus();
                    break;
                default:
                    multiply();
                    break;
            }
        }
        status = "multiplication";
        operator = false;
        number = null;
    }

    private void btnDivideClick() {
        //history = textViewHistory.getText().toString();
        currentResult = textViewResult.getText().toString();
        //textViewHistory.setText(history + currentResult + "/");
        textViewHistory.setText(currentResult + "/");

        if (operator) {
            switch (status) {
                case "multiplication":
                    multiply();
                    break;
                case "sum":
                    plus();
                    break;
                case "subtraction":
                    minus();
                    break;
                default:
                    divide();
                    break;
            }
        }
        status = "division";
        operator = false;
        number = null;
    }

    private void btnEqualsClick() {
        if (operator) {
            switch (status) {
                case "sum":
                    plus();
                    break;
                case "subtraction":
                    minus();
                    break;
                case "multiplication":
                    multiply();
                    break;
                case "division":
                    divide();
                    break;
                default:
                    firstNumber = Double.parseDouble(textViewResult.getText().toString());
                    break;
            }
        }

        operator = false;
        btnEqualsControl = true;
    }


    private void btnDotClick() {
        if (dot) {
            if (number == null) {
                number = "0.";
            } else {
                number += ".";
            }
        }

        textViewResult.setText(number);
        dot = false;
    }


    // method plus number
    private void plus() {
        lastNumber = Double.parseDouble(textViewResult.getText().toString());
        firstNumber += lastNumber; // mean firstNumber = firstNumber + lastNumber
        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    // method minus number
    private void minus() {
        if (firstNumber == 0.0) {
            firstNumber = Double.parseDouble(textViewResult.getText().toString());
        } else {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber -= lastNumber;
        }
        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    // method multiply * number
    private void multiply() {
        if (firstNumber == 0.0) {
            firstNumber = 1.0;
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber *= lastNumber;
        } else {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber *= lastNumber;
        }
        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    // method divide / number
    private void divide() {
        if (firstNumber == 0.0) {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = lastNumber / 1;
        } else {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber /= lastNumber;
        }
        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }

}