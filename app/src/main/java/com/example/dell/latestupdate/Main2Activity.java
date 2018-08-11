package com.example.dell.latestupdate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Newfragmant1 newfragmant1 = new Newfragmant1();
    TVshow tVshowfragmant = new TVshow();
    Newfragmant3 newfragmant3 = new Newfragmant3();
    private String userId;
    CallbackManager callbackManager;
    private static final String EMAIL = "email";
    SharedPreferences sharedPreferences;
    LoginButton loginButton;
    private ProfileTracker profileTracker;
    private String firstName,lastName, email,birthday,gender;
    private URL profilePicture;
    MaterialSearchView search_view;
    CircleImageView imageViewn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences(SHPconstants.prefsName, MODE_PRIVATE);
        callbackManager = CallbackManager.Factory.create();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager1.beginTransaction();
        transaction.replace(R.id.container, newfragmant1).commit();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        search_view = (MaterialSearchView) findViewById(R.id.search_view);

        View hader = navigationView.getHeaderView(0);
        imageViewn =hader.findViewById(R.id.imagenavigation);

        search_view.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                Intent intent = new Intent(Main2Activity.this,SearchActivity.class);
                intent.putExtra(Contract.QUERY, query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });


        search_view.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });

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
        getMenuInflater().inflate(R.menu.main2, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        search_view.setMenuItem(item);
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Movies) {
            // Handle the camera action
            setmoviefragment();
        } else if (id == R.id.TV_show) {
            settvfragmant();

        } else if (id == R.id.Favourite) {

            setFavourite();

        } else if (id == R.id.about) {

            Intent intent = new Intent(this, AboutActivityy.class);
            startActivity(intent);
            return false;

        }
        else if(id == R.id.login)
        {
                imageViewn.setImageDrawable(getResources().getDrawable(R.drawable.mypic));
        }
        else if(id == R.id.logout)
        {
               imageViewn.setImageDrawable(getResources().getDrawable(R.drawable.image));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void login()
    {
        loginButton.setReadPermissions(Arrays.asList(new String[]{EMAIL, "user_status", "public_profile", "user_posts", "read_custom_friendlists"}));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Toast.makeText(Main2Activity.this, "your fb id", Toast.LENGTH_LONG).show();

                sharedPreferences.edit().putBoolean(SHPconstants.loginStatus, true)
                        .putString(SHPconstants.prefsAccessToken , loginResult.getAccessToken() +"")
                        .apply();
                Toast.makeText(Main2Activity.this, "your fb id", Toast.LENGTH_LONG).show();

                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        try {
                            userId = object.getString("id");
                            profilePicture = new URL("https://graph.facebppk.com/" + userId +"/picture?width=500&height=500");
                            if(object.has("first_name"))
                            {
                                firstName = object.getString("first_name");
                                Log.d("namel", firstName);
                                Toast.makeText(Main2Activity.this,firstName, Toast.LENGTH_LONG).show();

                            }
                            if(object.has("last_name"))
                            {
                                lastName = object.getString("last_name");
                            }
                            if(object.has("email"))
                            {
                                email =object.getString("email");
                            }
                            if(object.has("birthday"))
                            {
                                birthday = object.getString("birthday");
                            }
                            if(object.has("gender"))
                            {
                                gender = object.getString("gender");
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields" ,"id , first_name , last_name, email ,birthday, gender");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();

            }

            @Override
            public void onCancel() {
                // App code

                Toast.makeText(Main2Activity.this, "OOPS! not open", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code]

                Toast.makeText(Main2Activity.this, "Error comes out", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }



    private void setFavourite() {

        FragmentManager fragmentManager1 = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager1.beginTransaction();
        transaction.replace(R.id.container, newfragmant3).commit();
    }

    private void setmoviefragment() {
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager1.beginTransaction();
        transaction.replace(R.id.container, newfragmant1).commit();

    }
    private void settvfragmant() {
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager1.beginTransaction();
        transaction.replace(R.id.container, tVshowfragmant).commit();

    }


}
