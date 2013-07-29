package it.cs.unipd.it.cs.unipd.utils;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.AttributeSet;

import it.cs.unipd.sensorstester.R;

/**
 * Created by Matteo Ciman on 29/07/13.
 */
public class SeekBarLabel extends Preference {

    public SeekBarLabel(Context context, AttributeSet attrs) {

        super(context, attrs);
        System.out.println(attrs);
        System.out.println(attrs.getAttribute)

        setLayoutResource(R.layout.slider_with_value);
    }

}
