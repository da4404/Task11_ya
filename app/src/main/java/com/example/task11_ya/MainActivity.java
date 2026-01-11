package com.example.task11_ya;

import android.content.DialogInterface; // ייבוא חסר שנוסף
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog; // שינוי לייבוא המתאים יותר
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity
{

    TextView summaryTextView;
    ConstraintLayout mainLayout;
    Button btnStyle;
    Button btnFood;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStyle = findViewById(R.id.btn1);
        summaryTextView = findViewById(R.id.sicom);
        mainLayout = findViewById(R.id.main);
        btnFood = findViewById(R.id.btn2);

        btnFood.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openFoodDialog();
            }
        });
        btnStyle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openStyleDialog();
            }
        });
    }
    private void openFoodDialog()
    {
        final String[] items = {"Popcorn", "Soda", "Chips", "Chocolate", "Water"};
        final boolean[] checkedItems = {false, false, false, false, false};
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Select Food and Equipment");
        adb.setCancelable(false);
        adb.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked)
            {
                checkedItems[which] = isChecked;
            }
        });
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                StringBuilder selectedList = new StringBuilder("Selected: ");
                boolean first = true;
                for (int i = 0; i < items.length; i++) {
                    if (checkedItems[i]) {
                        if (!first) selectedList.append(", ");
                        selectedList.append(items[i]);
                        first = false;
                    }
                }
                summaryTextView.setText(selectedList.toString());
            }
        });

        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        adb.create().show();
    }
    private void openStyleDialog()
    {
        final String[] options = {"Movie", "Restaurant", "Show", "Night Walk"};

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Choose Your Night Out Style");
        adb.setCancelable(false);

        adb.setItems(options, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String selected = options[which];

                summaryTextView.setText("Plan: " + selected);
                if (which == 0)
                {
                    mainLayout.setBackgroundColor(Color.BLUE);
                }
                else if (which == 1)
                {
                    mainLayout.setBackgroundColor(Color.YELLOW);
                }
                else if (which == 2)
                {
                    mainLayout.setBackgroundColor(Color.MAGENTA);
                }
                else
                {
                    mainLayout.setBackgroundColor(Color.LTGRAY);
                }
            }
        });
        AlertDialog ad = adb.create();
        ad.show();
    }
}