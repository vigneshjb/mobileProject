package cse535.group38.resquebot;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cse535.group38.resquebot.background.WifiService;
import cse535.group38.resquebot.delegate.UiDelegate;
import cse535.group38.resquebot.model.Task;
import cse535.group38.resquebot.delegate.DbDelegate;


public class MainActivity extends AppCompatActivity {

    public static String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        callService(); //TODO: change this to "startWifiListener".
    }

    @Override
    protected void onPostResume(){
        super.onPostResume();
        if(message.length()!=0)
            showSnackBar(message);
    }
    
    public void callService()//TODO: change this to "startWifiListener".
    {
        Intent intService = new Intent(this, WifiService.class);
        startService(intService);
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new TabFragment(), "Task List");
        adapter.addFragment(new TabFragment(), "Create Task");
        adapter.addFragment(new TabFragment(), "Upload Logs");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    // UI based Event Listeners
    public void insertIntoTask(View view){
        if (DbDelegate.writeTaskToDb(getNewTaskFromUI(), getApplicationContext()))
            showSnackBar("Task Created");
        else
            showSnackBar("Creating Task Failed");
    }

    // UI based Object Builder and flush UI
    public Task getNewTaskFromUI(){
        return UiDelegate.getNewTask(this);
    }

    public void showSnackBar(String message){
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
                .show();
    }
}
