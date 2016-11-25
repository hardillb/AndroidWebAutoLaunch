package uk.me.hardill.weblauncher;

import android.os.Bundle;
import android.preference.PreferenceActivity;


/**
 * Created by hardillb on 23/11/16.
 */

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
