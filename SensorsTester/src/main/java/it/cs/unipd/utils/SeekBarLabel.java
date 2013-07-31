package it.cs.unipd.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
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
public class SeekBarLabel extends DialogPreference implements SeekBar.OnSeekBarChangeListener {

    private static final String SEEKBAR_NS = "http://schemas.android.com/apk/res/it.cs.unipd.utils";
    private static final String ANDROID_NS = "http://schemas.android.com/apk/res/android";

    private static final String MIN_VALUE_ATTRIBUTE = "minValue";
    private static final String MAX_VALUE_ATTRIBUTE = "maxValue";
    private static final String LIST_VALUES_ATTRIBUTE = "listValues";

    private String[] listValues = null;
    private int minValue = 0;
    private int maxValue = -1;
    private int step = 1;
    private int currentValue = 0;

    public SeekBarLabel(Context context, AttributeSet attrs) {
        super(context, attrs);

        /**
         * Retrieving attributes values to build the SeekBar
         */
        //minValue = attrs.getAttributeIntValue(SEEKBAR_NS, MIN_VALUE_ATTRIBUTE, minValue);
        //maxValue = attrs.getAttributeIntValue(SEEKBAR_NS, MAX_VALUE_ATTRIBUTE, maxValue);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SeekBarLabelAttributes, 0, 0);

        step = a.getInt(R.styleable.SeekBarLabelAttributes_step, step);
        minValue = a.getInt(R.styleable.SeekBarLabelAttributes_minValue, minValue);
        maxValue = a.getInt(R.styleable.SeekBarLabelAttributes_maxValue, maxValue);

    }

    @Override
    protected View onCreateDialogView() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String key = this.getKey();
        currentValue = sharedPreferences.getInt(this.getKey(), currentValue / step) / step;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View seekBarView = inflater.inflate(R.layout.slider_with_value, null);

        SeekBar seekBar = (SeekBar) seekBarView.findViewById(R.id.slider_bar);
        seekBar.setMax(maxValue / step);
        seekBar.setProgress(currentValue);
        seekBar.setOnSeekBarChangeListener(this);

        return seekBarView;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View seekBarView = inflater.inflate(R.layout.slider_with_value, null);

        TextView value = (TextView) seekBarView.findViewById(R.id.slider_text_value);
        value.setText(Integer.toString(progress * step));
        currentValue = progress * step;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (!positiveResult) {
            return;
        }
        if (shouldPersist()) {
            persistInt(currentValue);
        }

        notifyChanged();
    }

    @Override
    public CharSequence getSummary() {
        String summary = "Current value: %d seconds";
        return String.format(summary, currentValue);
    }
}
