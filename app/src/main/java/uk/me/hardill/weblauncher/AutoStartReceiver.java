package uk.me.hardill.weblauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AutoStartReceiver extends BroadcastReceiver {
    public AutoStartReceiver() {
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        PreferenceManager.setDefaultValues(context, R.xml.preferences, false);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        boolean onStart = sharedPref.getBoolean("launch_on_start", false);
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()) && onStart) {
            Intent startActivity = new Intent(context, FullscreenActivity.class);
            startActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(startActivity);
        }
    }
}
