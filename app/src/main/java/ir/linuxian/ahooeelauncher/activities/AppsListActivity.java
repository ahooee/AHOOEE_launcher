package ir.linuxian.ahooeelauncher.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.linuxian.ahooeelauncher.R;

public class AppsListActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_list);


        init();



    }

    private void init() {

        //getting list of installed apps using PackageManager.getInstalledApplications() method
        // and store the list in a List of ApplicationInfo objects
        PackageManager packageManager = getPackageManager();

        List<ApplicationInfo> applicationInfos = packageManager.getInstalledApplications(0);


        //storing the name of all installed apps names in a list
        List<String> appsPackageNames = new ArrayList<>();

        for(ApplicationInfo applicationInfo:applicationInfos) {
            Intent intent = packageManager.getLaunchIntentForPackage(applicationInfo.packageName);

            //checks if the app has a launcher
            if(intent!=null)
                //get the app name by its package name , using these two methods
            appsPackageNames.add(applicationInfo.loadLabel(packageManager)+"");
            //appsPackageNames.add(packageManager.getApplicationLabel(applicationInfo)+"");

        }

        //set an String ArrayAdapter to the listview that includes the list of installed apps  names list
        ListView listView  = findViewById(R.id.listview);

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,appsPackageNames));
    }
}