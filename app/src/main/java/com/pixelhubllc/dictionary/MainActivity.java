package com.pixelhubllc.dictionary;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.pixelhubllc.database.DatabaseAccess;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    DatabaseAccess databaseAccess;
//    Button nightmode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        nightmode = findViewById(R.id.gonight);
//        nightmode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getApplicationContext().setTheme(MODE_NIGHT_YES);
//            }
//        });

        databaseAccess = DatabaseAccess.getInstance(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SearchFragment(this))
                .commit();

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation_bar);
        navigationView.setOnNavigationItemSelectedListener(navlistener);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.search:
                            selectedFragment = new SearchFragment(MainActivity.this);
                            break;

                        case R.id.favourite:
                            selectedFragment = new FavouriteFragment();
                            break;

                        case R.id.words:
                            selectedFragment = new WordsFragment();
                            break;

                        case R.id.setting:
                            selectedFragment = new SettingFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment)
                            .commit();

                    return true;

                }

            };


}
