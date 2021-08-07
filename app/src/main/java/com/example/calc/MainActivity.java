package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.*;
import org.mariuszgromada.math.mxparser.Expression;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
private EditText display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    display = findViewById(R.id.display);
    display.setShowSoftInputOnFocus(false);
    display.setOnClickListener( v -> {
        if(getString(R.string.Display).equals(display.getText().toString()))
            display.setText("");
    });
    }

    public void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(getString(R.string.Display).equals(display.getText().toString())){
            display.setText(strToAdd);
        }else {
            display.setText(String.format("%s%s%s", leftStr,strToAdd,rightStr));
        }
        display.setSelection(cursorPos+1);
    }


   public void backspace(View view){
       int cursorPos = display.getSelectionStart();
       int texLen = display.getText().length();
       if(cursorPos!=0  && texLen!=0){
           SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
           selection.replace(cursorPos-1,cursorPos,"");
           display.setText(selection);
           display.setSelection(cursorPos-1);
       }
    }

    public void bracketsBtn(View view){
    int cursorPos = display.getSelectionStart();
    int openPar =0;
    int closedPar =0;
    int texLen= display.getText().length();

        for (int i = 0; i <cursorPos; i++) {
            if (display.getText().toString().substring(i,i+1).equals("(")){
                openPar+=1;
            }
            if (display.getText().toString().substring(i,i+1).equals(")")){
                closedPar+=1;
            }
        }
        if (openPar==closedPar || display.getText().toString().substring(texLen-1, texLen).equals("(")){
            updateText("(");
        }else if (closedPar<openPar || !display.getText().toString().substring(texLen-1, texLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos+1);
    }
    public void equalsBtn(View view){
        String userExp = display.getText().toString();
        Expression expression = new Expression(userExp);
        String result = String.valueOf(expression.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void zeroBtn(View view){
        updateText("0");
    }

    public void oneBtn(View view){
        updateText("1");
    }
    public void twoBtn(View view){
        updateText("2");
    }
    public void threeBtn(View view){
        updateText("3");
    }
    public void fourBtn(View view){
        updateText("4");
    }
    public void fiveBtn(View view){
        updateText("5");
    }
    public void sixBtn(View view){
        updateText("6");
    }
    public void sevenBtn(View view){
        updateText("7");
    }
    public void eightBtn(View view){
        updateText("8");
    }
    public void nineBtn(View view){
        updateText("9");
    }

    public void plusBtn(View view){
        updateText("+");
    }
    public void minusBtn(View view){
        updateText("-");
    }
    public void multiplyBtn(View view){
        updateText("X");
    }
    public void divisionBtn(View view){
        updateText("/");
    }

    public void clearBtn(View view){
    display.setText("");
    }

    public void powerBtn(View view){
    updateText("^");
    }

    public void dotBtn(View view){
        updateText(".");
    }

    public void plusminusBtn(View view){
        updateText("-");
    }

}