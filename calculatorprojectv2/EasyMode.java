package com.example.calculatorprojectv2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.*;

import java.util.ArrayList;

public class EasyMode extends AppCompatActivity {
    TextView txtViewDisplay, txtViewGoal, txtViewClickCounter, txtViewTotalClicks, txtViewLevel, txtViewPoints; //add a TextView for the number that the use has to reach
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnAdd, btnSubtract, btnMultiply, btnDivide, btnCalculate, btn0, btnDecimal, btnNegative, btnClear, btnShop;

    private int clickCounter = 0;
    private int totalClicks = 0;
    private String displayLabel = "";

    private int points = 0;


    //FROM 0-4, TO MATCH WITH ALLSTAGES ARRAYLIST (CHANGE LATER MAYBE IDK, BUT EASIER TO MATCH WITH ARRAYLIST FOR NOW)
    private int currentStage = -1;

    ArrayList<Stage> allStages = new ArrayList<>();

    Context context;
    ;
    CharSequence keystrokeOver;
    CharSequence sillyGoose;
    int duration;

    Toast fgd;
    Context contextTwo;
    CharSequence textOver;
    CharSequence textUnder;
    int durationTwo;
    Toast underShot;
    Toast overShot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);


        context = getApplicationContext();

        sillyGoose = "I said Addition you silly goose :)";
        duration = Toast.LENGTH_SHORT;


        fgd = Toast.makeText(context, sillyGoose, duration);


        textOver = "Goal Overshot!";
        textUnder = "Goal Undershot!";


        underShot = Toast.makeText(context, textUnder, duration);
        overShot = Toast.makeText(context, textOver, duration);

        btn1 = (Button) findViewById(R.id.buttonOne);
        btn2 = (Button) findViewById(R.id.buttonTwo);
        btn3 = (Button) findViewById(R.id.buttonThree);
        btn4 = (Button) findViewById(R.id.buttonFour);
        btn5 = (Button) findViewById(R.id.buttonFive);
        btn6 = (Button) findViewById(R.id.buttonSix);
        btn7 = (Button) findViewById(R.id.buttonSeven);
        btn8 = (Button) findViewById(R.id.buttonEight);
        btn9 = (Button) findViewById(R.id.buttonNine);
        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnSubtract = (Button) findViewById(R.id.buttonSubtract);
        btnMultiply = (Button) findViewById(R.id.buttonMultiply);
        btnDivide = (Button) findViewById(R.id.buttonDivide);
        btnCalculate = (Button) findViewById(R.id.buttonCalculate);
        btn0 = (Button) findViewById(R.id.buttonZero);
        btnDecimal = (Button) findViewById(R.id.buttonDecimal);
        btnNegative = (Button) findViewById(R.id.buttonNegative);
        btnClear = (Button) findViewById(R.id.buttonClear);
        btnShop = (Button) findViewById(R.id.buttonShop);

        //^^ The numerical calculator buttons


        //^^ For the Click Listener for the Button

        txtViewDisplay = (TextView) findViewById(R.id.display);
        txtViewGoal = (TextView) findViewById((R.id.goalDisplay));
        txtViewClickCounter = (TextView) findViewById(R.id.buttonClickCounter);
        txtViewTotalClicks = (TextView) findViewById(R.id.constraintDisplay);
        txtViewLevel = (TextView) findViewById(R.id.levelLabel);
        txtViewPoints = (TextView) findViewById(R.id.pointsDisplay);
        //^^ Sets the Displays

        txtViewPoints.setText("Points: " + points);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("1");

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("2");

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("3");

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("4");

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("5");

            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("6");

            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("7");

            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("8");

            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("9");

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("+");

            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("-");

            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("×");

            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("÷");

            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateResult();

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayLabel = "";
                txtViewDisplay.setText(displayLabel);
                totalClicks = allStages.get(currentStage).getClicks();
                clickCounter = 0;

                txtViewClickCounter.setText("Clicks Left: " + totalClicks);
            }
        });
        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shop = new Intent(EasyMode.this, Shop.class);
                shop.putExtra("Points", points);
                shop.putExtra("Current Stage", currentStage);
                startActivity(shop);
            }
        });


        allStages.add(new Stage(64, 3));
        allStages.add(new Stage(55, 4));
        allStages.add(new Stage(169, 5));
        allStages.add(new Stage(267, 6));
        allStages.add(new Stage(12, 3));

        currentStage++;

        setStuff();
        Intent getShopValues = getIntent();
        reSetUIValues(getShopValues.getIntExtra("Points", 0), getShopValues.getIntExtra("Current Stage", 0));

    }
    private void reSetUIValues(int Points, int curStage){
        points = Points;
        currentStage = curStage;
        txtViewPoints.setText("Points: " + points);
        setStuff();

    }
    private void setStuff() {
        clickCounter = 0;
        txtViewLevel.setText("Stage: " + (currentStage + 1));
        totalClicks = allStages.get(currentStage).getClicks();
        txtViewGoal.setText("Goal: " + allStages.get(currentStage).getGoal());
        displayLabel = "";
        txtViewTotalClicks.setText("Total Clicks: " + totalClicks);
        txtViewClickCounter.setText("Clicks Left: " + totalClicks);
    }

    private void finishScreen() {
        displayLabel = "";
        txtViewDisplay.setText("You Beat the Game!");
        txtViewTotalClicks.setVisibility(View.GONE);
        txtViewGoal.setVisibility(View.GONE);
        txtViewClickCounter.setVisibility(View.GONE);
        txtViewLevel.setVisibility(View.GONE);

        btn1.setVisibility(View.GONE);
        btn2.setVisibility(View.GONE);
        btn3.setVisibility(View.GONE);
        btn4.setVisibility(View.GONE);
        btn5.setVisibility(View.GONE);
        btn6.setVisibility(View.GONE);
        btn7.setVisibility(View.GONE);
        btn8.setVisibility(View.GONE);
        btn9.setVisibility(View.GONE);
        btnAdd.setVisibility(View.GONE);
        btnSubtract.setVisibility(View.GONE);
        btnMultiply.setVisibility(View.GONE);
        btnDivide.setVisibility(View.GONE);
        btnCalculate.setVisibility(View.GONE);
        btn0.setVisibility(View.GONE);
        btnDecimal.setVisibility(View.GONE);
        btnNegative.setVisibility(View.GONE);
    }

    public void buttonClick(String symbol) {


        if (clickCounter >= totalClicks) {
            Toast.makeText(context, "Out of Clicks!", Toast.LENGTH_SHORT).show();

//            displayLabel = "";
//            txtViewDisplay.setText(displayLabel);

        } else {

            clickCounter++;
            txtViewClickCounter.setText("Clicks Left: " + (totalClicks - clickCounter));
            displayLabel = displayLabel.concat(symbol);
            txtViewDisplay.setText(displayLabel);

        }
    }


    public void calculateResult() {

        String expEval = txtViewDisplay.getText().toString();

        expEval = expEval.replaceAll("×", "*");
        expEval = expEval.replaceAll("÷", "/");

        String onlyDigits = expEval;
        onlyDigits = onlyDigits.replaceAll("'\\+'", "");
        onlyDigits = onlyDigits.replaceAll("-", "");
        onlyDigits = onlyDigits.replaceAll("\\*", "");
        onlyDigits = onlyDigits.replaceAll("'/'", "");


        System.out.println(onlyDigits);

        Expression exp = new Expression(expEval);

        String resultS = String.valueOf(exp.calculate());

        double result = Double.parseDouble(resultS);

        if (result == allStages.get(currentStage).getGoal() && result != Double.parseDouble(onlyDigits)) {
            allStages.get(currentStage).setAchievedGoal(true);
            if (currentStage < 4) {
                currentStage++;
                setStuff();
                Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show();
                points += currentStage + 1;
                txtViewPoints.setText("Points: " + points);
            } else {
                finishScreen();
            }

//            } else if (result == Double.parseDouble(expEval)){
//                Toast.makeText(context, "That's the same number!", Toast.LENGTH_SHORT).show();
//
//            }
        } else {

            if (result > allStages.get(currentStage).getGoal()) {
                overShot.show();
            } else if (result < allStages.get(currentStage).getGoal()) {
                underShot.show();
            } else {
                Toast.makeText(context, "That's the same number!", Toast.LENGTH_SHORT).show();
            }

            displayLabel = "";
            clickCounter = 0;
            points--;
            txtViewPoints.setText("Points: " + points);

        }

        if (!allStages.get(4).getAchievedGoal()) {
            txtViewDisplay.setText(displayLabel);
            txtViewClickCounter.setText("Clicks Left: " + (totalClicks - clickCounter));

        }
    }
}