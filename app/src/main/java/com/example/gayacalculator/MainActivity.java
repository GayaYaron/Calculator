package com.example.gayacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //simple version- accepts a digit, an action, another digit and then equals and calculates the result

//    Integer a,b;
//    String action;
//    List<Button> buttons = new ArrayList<>(15);
//    boolean fillA = true;
//    TextView resultTv;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        getViews();
//        setListener();
//    }
//
//    public void getViews() {
//        buttons.add(findViewById(R.id.zeroBtn));
//        buttons.add(findViewById(R.id.oneBtn));
//        buttons.add(findViewById(R.id.twoBtn));
//        buttons.add(findViewById(R.id.threeBtn));
//        buttons.add(findViewById(R.id.fourBtn));
//        buttons.add(findViewById(R.id.fiveBtn));
//        buttons.add(findViewById(R.id.sixBtn));
//        buttons.add(findViewById(R.id.sevenBtn));
//        buttons.add(findViewById(R.id.eightBtn));
//        buttons.add(findViewById(R.id.nineBtn));
//        buttons.add(findViewById(R.id.plusBtn));
//        buttons.add(findViewById(R.id.minusBtn));
//        buttons.add(findViewById(R.id.multiplyBtn));
//        buttons.add(findViewById(R.id.divideBtn));
//        buttons.add(findViewById(R.id.equalsBtn));
//
//        resultTv = findViewById(R.id.resultTV);
//    }
//
//    public void setListener() {
//        for (Button btn: buttons) {
//            btn.setOnClickListener(this);
//        }
//        findViewById(R.id.clearBtn).setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View view) {
//        Button button = (Button) view;
//        String btnText = button.getText().toString();
//        if(button.getId() == R.id.clearBtn) {
//            clearClicked();
//        }else {
//            mathClicked(button, btnText);
//        }
//    }
//
//    public void mathClicked(Button button, String btnText) {
//        boolean keepChecking = true;
//        for(int i=0; i<buttons.size() && keepChecking; i++) {
//            boolean isCurrent = button.equals(buttons.get(i));
//            if(isCurrent) {
//                if(i<10) {
//                    numberClicked(Integer.parseInt(btnText));
//                }else if(i<14) {
//                    actionClicked(btnText);
//                }else {
//                    equalsClicked();
//                }
//            }
//            keepChecking = !isCurrent;
//        }
//    }
//
//    public void numberClicked(int num) {
//        resultTv.setText(num + "");
//        if(fillA) {
//            a = num;
//        } else {
//            b = num;
//        }
//    }
//
//    public void actionClicked(String actionClick) {
//        fillA = false;
//        action = actionClick;
//        resultTv.setText(resultTv.getText().toString() + actionClick);
//    }
//
//    public void equalsClicked() {
//        int result = 0;
//        boolean error = false;
//        switch (action) {
//            case "+":
//                result = a+b;
//                break;
//            case "-":
//                result = a-b;
//                break;
//            case "*":
//                result = a*b;
//                break;
//            case "/":
//                if(b==0){
//                    Toast.makeText(this, "You cannot divide by 0", Toast.LENGTH_SHORT).show();
//                    error = true;
//                }else {
//                    result = a/b;
//                }
//                break;
//            default:
//                if (a != null) {
//                    result = a;
//                }
//        }
//        if(!error) {
//            resultTv.setText(result+"");
//        }
//    }
//
//    public void clearClicked() {
//        resultTv.setText("");
//        a = null;
//        b = null;
//        action = null;
//        fillA = true;
//    }

    //the extended version- does not let you divide by 0, allows multi-digit numbers, handles multiple actions and more

    int currentResult = 0;
    String action;
    List<Button> buttons = new ArrayList<>(15);
    TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();
        setListener();
    }

    public void getViews() {
        buttons.add(findViewById(R.id.zeroBtn));
        buttons.add(findViewById(R.id.oneBtn));
        buttons.add(findViewById(R.id.twoBtn));
        buttons.add(findViewById(R.id.threeBtn));
        buttons.add(findViewById(R.id.fourBtn));
        buttons.add(findViewById(R.id.fiveBtn));
        buttons.add(findViewById(R.id.sixBtn));
        buttons.add(findViewById(R.id.sevenBtn));
        buttons.add(findViewById(R.id.eightBtn));
        buttons.add(findViewById(R.id.nineBtn));
        buttons.add(findViewById(R.id.plusBtn));
        buttons.add(findViewById(R.id.minusBtn));
        buttons.add(findViewById(R.id.multiplyBtn));
        buttons.add(findViewById(R.id.divideBtn));
        buttons.add(findViewById(R.id.equalsBtn));

        resultTv = findViewById(R.id.resultTV);
    }

    public void setListener() {
        for (Button btn : buttons) {
            btn.setOnClickListener(this);
        }
        findViewById(R.id.clearBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String btnText = button.getText().toString();
        if (button.getId() == R.id.clearBtn) {
            clearClicked();
        } else {
            mathClicked(button, btnText);
        }
    }

    public void mathClicked(Button button, String btnText) {
        boolean keepChecking = true;
        for (int i = 0; i < buttons.size() && keepChecking; i++) {
            boolean isCurrent = button.equals(buttons.get(i));
            if (isCurrent) {
                if (i < 10) {
                    numberClicked(Integer.parseInt(btnText));
                } else if (i < 14) {
                    actionClicked(btnText);
                } else {
                    equalsClicked();
                }
            }
            keepChecking = !isCurrent;
        }
    }

    public void numberClicked(int num) {
        String currValue = resultTv.getText().toString();
        if(currValue.length()>0 && lastCharIsNotNum(currValue)) {
            resultTv.setText(num+"");
        }else {
            resultTv.setText(resultTv.getText().toString() + num);
        }
    }

    /*
    checks if the last character is a math action,
    if so- replaces the action with new one (including in text)
    if not- sets the new current result and shows the text as current result+ action
     */
    public void actionClicked(String actionClick) {
        String currValue = resultTv.getText().toString();
        if(currValue.length() == 0) {
            resultTv.setText(actionClick);
        }else if (lastCharIsNotNum(currValue)) {
            resultTv.setText(currValue.substring(0, (currValue.length() - 1)) + actionClick);
        } else {
            int currNum = Integer.parseInt(currValue);
            currentResult = (action == null) ? (currNum) : (doTheMath(currentResult, currNum));
            resultTv.setText(currentResult + actionClick);
        }
        action = actionClick;
    }

    public boolean lastCharIsNotNum(String text) {
        return (text.length()==0)?true:(!Character.isDigit(text.charAt(text.length() - 1)));
    }

    public int doTheMath(int a, int b) {
        int result = 0;
        switch (action) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b == 0) {
                    Toast.makeText(this, "You cannot divide by 0", Toast.LENGTH_SHORT).show();
                    result = a;
                } else {
                    result = a / b;
                }
                break;
        }
        return result;
    }

    /*
    if equals was clicked before any action- shows the number, else:

    checks if last character is action
    if so- acts on current result as both numbers
    if not- does the math
    shows result and resets action
     */
    public void equalsClicked() {
        String currValue = resultTv.getText().toString();
        if (action != null) {
            currentResult = (lastCharIsNotNum(currValue)) ?
                    (addMeToMe(currValue.substring(0, currValue.length() - 1))) :
                    (doTheMath(currentResult, Integer.parseInt(currValue)));
        }else {
            currentResult = (lastCharIsNotNum(currValue)) ?
                    (0) :
                    (Integer.parseInt(currValue));
        }
        resultTv.setText(currentResult + "");
    }

    public int addMeToMe(String num) {
        int number = Integer.parseInt(num);
        return doTheMath(number, number);
    }

    public void clearClicked() {
        currentResult = 0;
        action = null;
        resultTv.setText("");
    }
}