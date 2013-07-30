package it.cs.unipd.utils;


import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;

import it.cs.unipd.sensorstester.R;


/**
 * Created by Matteo Ciman on 29/07/13.
 */
public class SeekBarLabel extends Preference implements SeekBar.OnSeekBarChangeListener {

    private static final String SEEKBAR_NS = "http://schemas.android.com/apk/res/it.cs.unipd.utils";
    private static final String ANDROID_NS = "http://schemas.android.com/apk/res/android";

    private static final String MIN_VALUE_ATTRIBUTE = "minValue";
    private static final String MAX_VALUE_ATTRIBUTE = "maxValue";
    private static final String LIST_VALUES_ATTRIBUTE = "listValues";

    private String[] listValues = null;
    private int minValue = 0;
    private int maxValue = -1;

    public SeekBarLabel(Context context, AttributeSet attrs) {
        super(context, attrs);

        /**
         * Retrieving attributes values to build the SeekBar
         */
        //minValue = attrs.getAttributeIntValue(SEEKBAR_NS, MIN_VALUE_ATTRIBUTE, minValue);
        //maxValue = attrs.getAttributeIntValue(SEEKBAR_NS, MAX_VALUE_ATTRIBUTE, maxValue);

    }

    @Override
    protected View onCreateView(ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View seekBarView = inflater.inflate(R.layout.slider_with_value, null);

        TextView title = (TextView) seekBarView.findViewById(R.id.title_seekbar);
        title.setText(getTitle());

        SeekBar seekBar = (SeekBar) seekBarView.findViewById(R.id.slider_bar);
        seekBar.setMax(maxValue);
        seekBar.setOnSeekBarChangeListener(this);

        return seekBarView;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        /*LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View seekBarView = inflater.inflate(R.layout.slider_with_value, null);
        TextView value = (TextView) seekBar.findViewById(R.id.slider_text_value);

        if (listValues != null) {
            value.setText(listValues[progress]);
        }
        else {
            value.setText(Integer.toString(progress));
        }*/

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
