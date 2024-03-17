package com.example.calculadorapa;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView processTextView;
    private TextView resultTextView;
    private StringBuilder process = new StringBuilder();
    private double result = 0.0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        processTextView = findViewById(R.id.processTextView);
        resultTextView = findViewById(R.id.resultTextView);

        findViewById(R.id.button0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("0");
            }
        });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("1");
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("2");
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("3");
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("4");
            }
        });

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("5");
            }
        });

        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("6");
            }
        });

        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("7");
            }
        });

        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("8");
            }
        });

        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("9");
            }
        });

        findViewById(R.id.buttonClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        findViewById(R.id.buttonDivide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperator("/");
            }
        });

        findViewById(R.id.buttonMultiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperator("*");
            }
        });

        findViewById(R.id.buttonresta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperator("-");
            }
        });

        findViewById(R.id.buttonsuma).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperator("+");
            }
        });

        findViewById(R.id.buttonigual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        findViewById(R.id.buttonDecimal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!process.toString().endsWith(".")) {
                    appendNumber(".");
                }
            }
        });
    }

    private void appendNumber(String number) {
        process.append(number);
        processTextView.setText(process.toString());
    }

    private void clear() {
        process.setLength(0);
        result = 0.0;
        operator = "";
        processTextView.setText("");
        resultTextView.setText("");
    }

    private void setOperator(String op) {
        if (!operator.isEmpty()) {
            calculate();
        }

        operator = op;

        if (process.length() > 0) {
            process.append(" ").append(operator).append(" ");
            processTextView.setText(process.toString());
        }
    }

    private void calculate() {
        if (process.length() > 0 && operator.length() > 0) {
            String[] tokens = process.toString().split("\\s+");
            if (tokens.length != 3) {
                resultTextView.setText("Error");
                return;
            }

            double operand1 = Double.parseDouble(tokens[0]);
            String operator = tokens[1];
            double operand2 = Double.parseDouble(tokens[2]);

            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0.0) {
                        result = operand1 / operand2;
                    } else {
                        resultTextView.setText("Error");
                        return;
                    }
                    break;
                default:
                    resultTextView.setText("Error");
                    return;
            }

            DecimalFormat df = new DecimalFormat("#");
            String formattedResult = df.format(result);
            if (result == (double)((long) result)) {
                resultTextView.setText(formattedResult);
            } else {
                resultTextView.setText(Double.toString(result));
            }

            process.setLength(0);
            process.append(result);
            operator = "";
        }
    }
}
