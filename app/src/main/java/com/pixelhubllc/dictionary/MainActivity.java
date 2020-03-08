package com.pixelhubllc.dictionary;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.database.SQLException;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.pixelhubllc.database.DatabaseAccess;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    DatabaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseAccess = DatabaseAccess.getInstance(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SearchFragment())
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
                            selectedFragment = new SearchFragment();
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
