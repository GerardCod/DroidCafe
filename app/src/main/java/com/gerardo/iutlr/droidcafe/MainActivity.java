package com.gerardo.iutlr.droidcafe;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.gerardo.iutlr.droidcafe.extra.MESSAGE";
    private String mOrderMessage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mOrderMessage.equals("")) {
                    Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
                    startActivity(intent);
                } else {
                    displayToast("Select an image, please.");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_contact) {
            displayToast(getString(R.string.action_contact_message));
            return true;
        } else if (id == R.id.action_order) {
            if (!mOrderMessage.equals("")) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
                startActivity(intent);
            } else {
                displayToast("Select an image, please.");
            }
            return true;
        } else if (id == R.id.action_status) {
            displayToast(getString(R.string.action_status_message));
            return true;
        } else if (id == R.id.action_favorites) {
            displayToast(getString(R.string.action_favorites_message));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickImage(View view) {
        mOrderMessage = view.getContentDescription().toString();
        displayToast(mOrderMessage);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}
