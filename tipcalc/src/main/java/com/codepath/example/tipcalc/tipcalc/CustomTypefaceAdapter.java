package com.codepath.example.tipcalc.tipcalc;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Aphex on 3/19/14.
 */
public class CustomTypefaceAdapter extends ArrayAdapter<CharSequence> {

    Context context;
    int layoutResourceId;
    CharSequence data[] = null;
    Typeface tf;

    public CustomTypefaceAdapter(Context context, int layoutResourceId, CharSequence[] data, String FONT) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        tf = Typeface.createFromAsset(context.getAssets(), FONT);

    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView textview = (TextView) view;
        textview.setTypeface(tf);
        textview.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        return textview;
    }
}