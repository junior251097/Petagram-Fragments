package org.coursera.ccopa.petagram.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.coursera.ccopa.petagram.R;
import org.coursera.ccopa.petagram.adapters.PageAdapter;
import org.coursera.ccopa.petagram.fragments.PetFragment;
import org.coursera.ccopa.petagram.fragments.ProfileFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbarMain = findViewById(R.id.toolbarMain);
        toolbarMain.setOnMenuItemClickListener(menuItem -> {
            Intent intent;
            switch (menuItem.getItemId()) {
                case R.id.favorite:
                    intent = new Intent(MainActivity.this, FavoritePetsActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.contact:
                    intent = new Intent(MainActivity.this, ContactActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.about:
                    intent = new Intent(MainActivity.this, AboutActivity.class);
                    startActivity(intent);
                    return true;
                default:
                    return false;
            }
        });

        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setAdapter(new PageAdapter(this, getFragments()));
        final TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position + 1) {
                case 1:
                    tab.setText(R.string.activity_main_tab_one);
                    tab.setIcon(R.drawable.ic_pet_list);
                    break;
                case 2:
                    tab.setText(R.string.activity_main_tab_two);
                    tab.setIcon(R.drawable.ic_pet_profile);
                    break;
            }
        });
        tabLayoutMediator.attach();

        FloatingActionButton floatingButtonMain = findViewById(R.id.floatingButtonMain);
        floatingButtonMain.setOnClickListener(view -> Toast.makeText(MainActivity.this, R.string.toast_camera_button, Toast.LENGTH_SHORT).show());
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new PetFragment());
        fragments.add(new ProfileFragment());
        return fragments;
    }
}