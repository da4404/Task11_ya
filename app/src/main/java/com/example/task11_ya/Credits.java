package com.example.task11_ya;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This activity displays developer information and application credits.
 *
 * @author Darya Hamalca
 * @version 1.0
 * @since 11/01/2026
 */
public class Credits extends AppCompatActivity
{

    /**
     * Called when the activity is first created.
     * <p>
     * This method is responsible for initializing the activity, enabling edge-to-edge
     * display, and setting the content layout.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it
     *                           most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_credits);
    }
}
