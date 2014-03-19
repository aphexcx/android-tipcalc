package com.codepath.example.tipcalc.tipcalc;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.TextView;


public class TipCalculatorActivity extends ActionBarActivity implements View.OnTouchListener,
        AdapterView.OnItemClickListener {
    private EditText etSubTotal;
    private EditText etTipPercent;
    private TextView tvTotal;
    private ListPopupWindow lpw;
    private String[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        etTipPercent = (EditText) findViewById(R.id.etTipPercent);
        etTipPercent.setOnTouchListener(this);
        etTipPercent.setText("20");
        etTipPercent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    String tipString = etTipPercent.getText().toString();
                    updateTipCalculation(tipString);
                }

            }
        });

        list = new String[]{"10", "15", "18", "20"};
        lpw = new ListPopupWindow(this);
        lpw.setAdapter(new CustomTypefaceAdapter(this,
                android.R.layout.simple_list_item_1, list, "fonts/fakereceipt.ttf"));
        lpw.setAnchorView(etTipPercent);
        lpw.setModal(true);
        lpw.setOnItemClickListener(this);


        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/fakereceipt.ttf");
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        tvTotal.setTypeface(type);
        TextView tvThankYou = (TextView) findViewById(R.id.tvThankYou);
        tvThankYou.setTypeface(type);
        TextView tvPctMarker = (TextView) findViewById(R.id.tvPctMarker);
        tvPctMarker.setTypeface(type);
        TextView tvSubtotalLabel = (TextView) findViewById(R.id.tvSubtotalLabel);
        tvSubtotalLabel.setTypeface(type);
        TextView tvTipAmount = (TextView) findViewById(R.id.tvTipAmount);
        tvTipAmount.setTypeface(type);
        TextView tvTipAmountLabel = (TextView) findViewById(R.id.tvTipAmountLabel);
        tvTipAmountLabel.setTypeface(type);
        TextView tvTipPercentLabel = (TextView) findViewById(R.id.tvTipPercentLabel);
        tvTipPercentLabel.setTypeface(type);
        TextView tvTotalLabel = (TextView) findViewById(R.id.tvTotalLabel);
        tvTotalLabel.setTypeface(type);

        int actionBarTitle = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
        TextView actionBarTitleView = (TextView) getWindow().findViewById(actionBarTitle);
        if (actionBarTitleView != null) {
            actionBarTitleView.setTypeface(type);
        }

        etTipPercent = (EditText) findViewById(R.id.etTipPercent);
        etTipPercent.setTypeface(type);

        etSubTotal = (EditText) findViewById(R.id.etSubTotal);
        etSubTotal.setTypeface(type);
        etSubTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    String tipString = etTipPercent.getText().toString();
                    updateTipCalculation(tipString);
                }

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        String item = parent.getItemAtPosition(position).toString();
        etTipPercent.setText(item);
        lpw.dismiss();
    }

    private void updateTipCalculation(String tipString) {
        TextView tvTipAmount = (TextView) findViewById(R.id.tvTipAmount);
        double percentage;
        if (tipString != null) {
            switch (tipString) {
                case "10":
                    percentage = 0.1;
                    break;
                case "15":
                    percentage = 0.15;
                    break;
                case "18":
                    percentage = 0.18;
                    break;
                case "20":
                    percentage = 0.2;
                    break;
                default:
                    double tippct;
                    try {
                        tippct = Double.parseDouble(tipString);
                    } catch (NumberFormatException e) {
                        tippct = 0;
                    }
                    percentage = tippct / 100;
                    break;
            }
            String amountText = etSubTotal.getText().toString();
            double tip = 0;
            if (!amountText.isEmpty()) {
                double subtotal = Double.parseDouble(amountText);
                tip = subtotal * percentage;
                double total = subtotal + tip;
                tvTotal.setText(String.valueOf(String.format("%.2f", total)));
            }
            tvTipAmount.setText(String.valueOf(String.format("%.2f", tip)));
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_RIGHT = 2;

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getX() >= (v.getWidth() - ((EditText) v)
                    .getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                lpw.show();
                return true;
            }
        }
        return false;
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
