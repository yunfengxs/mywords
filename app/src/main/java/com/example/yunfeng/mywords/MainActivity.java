package com.example.yunfeng.mywords;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.yunfeng.mywords.Fragment.myUser;
import com.example.yunfeng.mywords.Fragment.reader;
import com.example.yunfeng.mywords.Fragment.showWords;

public class MainActivity extends AppCompatActivity {

    private  reader readers=new reader();
    private myUser user=new myUser();
    private showWords showwords=new showWords();
    private  Fragment mFragment;

    //当前显示的Fragment


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(readers);
                    return true;
                case R.id.navigation_dashboard:
                    replaceFragment(showwords);
                    return true;
                case R.id.navigation_notifications:
                    replaceFragment(user);
                    return true;
            }
            return false;
        }

    };

  /*  public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolber,menu);
        return true;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(readers);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void replaceFragment(Fragment fragment){
        if(mFragment != fragment) {
            mFragment = fragment;
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager. beginTransaction();
            transaction.replace(R.id.content, mFragment);
            transaction.commit();
        }

    }
}
