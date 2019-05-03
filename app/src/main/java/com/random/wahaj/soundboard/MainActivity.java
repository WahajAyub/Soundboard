package com.random.wahaj.soundboard;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MemeFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String[] f = new String[0];
        try {
            f = getAssets().list("starwars");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Meme[] StarWars = new Meme[f.length];

        for(int i=0; i<f.length;i++){
            StarWars[i] = new Meme(f[i].substring(0,f[i].length()-4),"starwars/"+f[i]);
        }


        f = new String[0];
        try {
            f = getAssets().list("bigshaq");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Meme[] BigShaq = new Meme[f.length];

        for(int i=0; i<f.length;i++){
            BigShaq[i] = new Meme(f[i].substring(0,f[i].length()-4),"bigshaq/"+f[i]);
        }


        f = new String[0];
        try {
            f = getAssets().list("pewdiepie");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Meme[] PewDiePie = new Meme[f.length];

        for(int i=0; i<f.length;i++){
            PewDiePie[i] = new Meme(f[i].substring(0,f[i].length()-4),"pewdiepie/"+f[i]);
        }

        f = new String[0];
        try {
            f = getAssets().list("misc");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Meme[] misc = new Meme[f.length];

        for(int i=0; i<f.length;i++){
            misc[i] = new Meme(f[i].substring(0,f[i].length()-4),"misc/"+f[i]);
        }

        Meme[] memes = concatAll(StarWars, PewDiePie, BigShaq, misc);

        ListAdapter myAdapter = new MemeListAdapter(this, memes);
        ListView memeView = (ListView) findViewById(R.id.mainButtonList);
        memeView.setAdapter(myAdapter);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.popBackStack();
        } else if (id == R.id.nav_nothot) {
            MemeFragment fragment = new MemeFragment();

            Bundle bundle = new Bundle();
            bundle.putString("asset", "bigshaq");
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_starwars) {
            MemeFragment fragment = new MemeFragment();

            Bundle bundle = new Bundle();
            bundle.putString("asset", "starwars");
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_misc) {
            MemeFragment fragment = new MemeFragment();

            Bundle bundle = new Bundle();
            bundle.putString("asset", "misc");
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_pewdiepie) {
            MemeFragment fragment = new MemeFragment();

            Bundle bundle = new Bundle();
            bundle.putString("asset", "pewdiepie");
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack(null)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public static <T> T[] concatAll(T[] first, T[]... rest) {
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
}
