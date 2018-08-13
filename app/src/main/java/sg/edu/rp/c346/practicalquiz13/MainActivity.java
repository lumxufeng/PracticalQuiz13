package sg.edu.rp.c346.practicalquiz13;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    BroadcastReceiver br = new MessageReceiver();
    RadioButton rb1;
    RadioButton rb2;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rg = findViewById(R.id.radioGroup);
        onPause();


        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction("android.intent.action.WALLPAPER_CHANGED");
        this.registerReceiver(br,filter);



    }

    @Override
    protected void onPause() {
        super.onPause();
        String text = "";
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        int check = rg.getCheckedRadioButtonId();
        if(check == rb1.getId()){
            text = rb1.getText().toString();
        }
        else{
            text = rb2.getText().toString();
        }

        prefEdit.putString("msg",text);
        prefEdit.commit();

    }
}
