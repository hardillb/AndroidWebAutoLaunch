package uk.me.hardill.weblauncher;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import static uk.me.hardill.weblauncher.R.xml.preferences;


/**
 * Created by hardillb on 23/11/16.
 */

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(preferences);
    }
}
