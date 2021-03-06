package com.syafrizal.my_geer.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.syafrizal.my_geer.Fragments.HomeFragment;
import com.syafrizal.my_geer.Fragments.ListFragment;
import com.syafrizal.my_geer.Fragments.NotificationsFragment;
import com.syafrizal.my_geer.Fragments.PinFragment;
import com.syafrizal.my_geer.Fragments.ProfileFragment;
import com.syafrizal.my_geer.Model.Notification;
import com.syafrizal.my_geer.R;
import com.syafrizal.my_geer.config.Constants;

import java.util.ArrayList;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    Fragment fragment;
    private TextView mTextMessage;

//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Membaca file menu dan menambahkan isinya ke action bar jika ada.
//        getMenuInflater().inflate(R.menu.setting, menu);
//        return true;
//    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_pin:
                    addFragment("pin");
                    return true;
                case R.id.navigation_list:
                    addFragment("list");
                    return true;
                case R.id.navigation_home:
                    addFragment("home");
                    return true;
                case R.id.navigation_notifications:
                    addFragment("notification");
                    return true;
                case R.id.navigation_profile:
                    addFragment("profile");
                    return true;
            }
            return false;
        }
    };

    public void initPaperDb(){
        //init paperdb
        Paper.init(this);
        if(Paper.book().read(Constants.PaperDB.NOTIFICATIONS) == null)
            Paper.book().write(Constants.PaperDB.NOTIFICATIONS,new ArrayList<Notification>());

//        if(Paper.book().read("notification"))
//        Paper.book().write("notification",)
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPaperDb();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TES", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
//                        String msg = getString(R.string.msg_token_fmt, token);
//                        Log.d("TES", msg);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void addFragment(String tujuan) {

        switch (tujuan){
            case "home":
                fragment = new HomeFragment();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                break;
            case "pin":
                fragment = new PinFragment();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                break;
            case "list":
                fragment = new ListFragment();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                break;
            case "profile":
                fragment = new ProfileFragment();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();

                break;
            case "notification":
                fragment = new NotificationsFragment();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                break;
        }
    }

}
