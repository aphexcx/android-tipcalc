package com.codepath.example.tipcalc.tipcalc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spTip);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tip_amounts, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                EditText etTotalAmount = (EditText) findViewById(R.id.etTotalAmount);
                TextView tvTipAmount = (TextView) findViewById(R.id.tvTipAmount);
                double percentage = 1;
                if (item != null) {
                    switch (item) {
                        case "10%":
                            percentage = 0.1;
                            break;
                        case "15%":
                            percentage = 0.15;
                            break;
                        case "18%":
                            percentage = 0.18;
                            break;
                        case "20%":
                            percentage = 0.2;
                            break;
                        case "Custom...":
                            percentage = 0.1;
                            break;
                    }
                    String amountText = etTotalAmount.getText().toString();
                    double tip = 0;
                    if (!amountText.isEmpty()) {
                        double total = Double.parseDouble(amountText);
                        tip = total * percentage;
                    }
                    tvTipAmount.setText(String.valueOf(String.format("%.2f", tip)));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
