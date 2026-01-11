package com.example.task11_ya;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * The MainActivity class manages the main user interface and interactions for the application,
 * allowing users to plan a night out through various dialog-based options.
 *
 * @author Darya Hamalca
 * @version 1.0
 * @since 11/01/2026
 */
public class MainActivity extends AppCompatActivity
{

    private TextView summaryTextView;
    private ConstraintLayout mainLayout;
    private Button btnStyle;
    private Button btnFood;
    private Button btnMessage;
    private Button btnReset;

    /**
     * Initializes the options menu for the activity.
     * <p>
     * This method inflates the menu resource into the existing menu.
     *
     * @param menu The options menu in which you place your items.
     * @return true for the menu to be displayed; if you return false it will not be shown.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * Handles action bar item clicks.
     * <p>
     * This method is called whenever an item in your options menu is selected.
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_credits)
        {
            Intent intent = new Intent(this, Credits.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when the activity is first created.
     * <p>
     * This method initializes the UI components and sets up click listeners for the buttons.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStyle = findViewById(R.id.btn1);
        summaryTextView = findViewById(R.id.sicom);
        mainLayout = findViewById(R.id.main);
        btnFood = findViewById(R.id.btn2);
        btnMessage = findViewById(R.id.btn3);
        btnReset = findViewById(R.id.btn4);

        btnReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openResetDialog();
            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openNameDialog();
            }
        });
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

    /**
     * Opens a confirmation dialog to reset all choices.
     * <p>
     * If confirmed, the background color is reset and the summary text is cleared.
     */
    private void openResetDialog()
    {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Reset Planning");
        adb.setMessage("Are you sure you want to reset all choices?");
        adb.setCancelable(false);
        adb.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                mainLayout.setBackgroundColor(Color.WHITE);
                summaryTextView.setText("");
                Toast.makeText(MainActivity.this, "Planning has been reset!", Toast.LENGTH_SHORT).show();
            }
        });

        adb.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        adb.create().show();
    }

    /**
     * Opens a dialog to enter the user's name.
     * <p>
     * Displays a customized toast message after the user enters their name and clicks OK.
     */
    private void openNameDialog()
    {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Personal Message");
        adb.setCancelable(false);
        final EditText eT = new EditText(this);
        eT.setHint("Enter your name here");
        adb.setView(eT);
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String userName = eT.getText().toString();
                String message = "Enjoy your outing, " + userName + "!";
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
        adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        adb.create().show();
    }

    /**
     * Opens a multi-choice dialog to select food items.
     * <p>
     * The selected items are displayed in the summary text view.
     */
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

    /**
     * Opens a single-choice dialog to select the night out style.
     * <p>
     * Changes the background color and updates the summary text based on the selection.
     */
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
