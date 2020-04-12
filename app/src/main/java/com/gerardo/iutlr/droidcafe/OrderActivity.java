package com.gerardo.iutlr.droidcafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerFragment.OnFragmentInteractionListener {
    private TextView orderText;
    private RadioGroup group;
    private Spinner spinnerPhones;
    private String orderSelected = "";
    private String spinnerLabel = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderText = findViewById(R.id.textorder);
        group = findViewById(R.id.radioGroup);
        spinnerPhones = findViewById(R.id.spinner_phone);

        orderSelected = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);
        orderText.setText(orderSelected);
        group.check(R.id.sameday);
        if (spinnerPhones != null) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_array, R.layout.support_simple_spinner_dropdown_item);
            spinnerPhones.setAdapter(adapter);
            spinnerPhones.setOnItemSelectedListener(this);
        }
    }

    public void onRadioButtonClicked(View view) {
        if (((RadioButton) view).isChecked()) {
            displayToast(((RadioButton) view).getText().toString());
        }
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerLabel = parent.getItemAtPosition(position).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void showDatePicker(View view) {
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), getString(R.string.date_picker));
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string +
                "/" + day_string + "/" + year_string);
        displayToast("Date: " + dateMessage);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
