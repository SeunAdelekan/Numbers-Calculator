package com.mysway.numbers;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.mysway.numbers.KeyUtils.BasicKeyInit;
import com.mysway.numbers.KeyUtils.ScientificKeyInit;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {


    public static ArrayList<Fragment> fragArrayList = new ArrayList();
    private boolean exit = false;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent intent = new Intent(this,AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class BasicFragment extends Fragment{

        View view;
        TextView mainText;
        TextView previewText;
        BasicKeyInit init;

        public BasicFragment(){

        }

        public static BasicFragment newInstance(int sectionNumber) {
            BasicFragment fragment = new BasicFragment();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState){

            View rootView = inflater.inflate(R.layout.fragment_basic, container, false);
            this.view = rootView;
            mainText = (TextView) rootView.findViewById(R.id.text_current);
            previewText = (TextView) rootView.findViewById(R.id.text_previous);
            init = new BasicKeyInit(rootView,mainText,previewText);
            init.initKeys(fragArrayList);
            return rootView;
        }
        @Override
        public void onResume(){
            super.onResume();
            init.initKeys(fragArrayList);
        }

    }

    public static class ScientificFragment extends Fragment{

        public ScientificFragment(){

        }

        public static  ScientificFragment newInstance(int sectionNumber) {
            ScientificFragment fragment = new ScientificFragment();

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState){

            View scientificView = inflater.inflate(R.layout.fragment_scientific, container, false);
            TextView textViewC = (TextView) scientificView.findViewById(R.id.text_current);
            TextView textViewP = (TextView) scientificView.findViewById(R.id.text_previous);

            ScientificKeyInit init = new ScientificKeyInit(scientificView,textViewC,textViewP,fragArrayList);
            init.initKeys();

            return scientificView;
        }

        @Override
        public void onResume(){
            super.onResume();
        }
    }

    public static class ScriptFragment extends Fragment{
        public ScriptFragment(){

        }

        public static ScriptFragment newInstance(int sectionNumber) {
            ScriptFragment fragment = new ScriptFragment();

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState){
            View rootView = inflater.inflate(R.layout.fragment_calcscript, container, false);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:{
                    BasicFragment fragment = new BasicFragment();
                    fragArrayList.add(fragment);
                    return fragment;
                }
                case 1:{
                    ScientificFragment fragment = new ScientificFragment();
                    fragArrayList.add(fragment);
                    return fragment;
                }
                case 2:{
                    return ScriptFragment.newInstance(position);
                }
                default:
                    ScriptFragment fragment = new ScriptFragment();
                    fragArrayList.add(fragment);
                    return fragment;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Calculator";
                case 1:
                    return "Scientific";
                case 2:
                    return "CalcScript";
            }
            return null;
        }

    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
            System.exit(0);
        } else {
            Toast.makeText(this, "Press Back again to Exit",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }

}
