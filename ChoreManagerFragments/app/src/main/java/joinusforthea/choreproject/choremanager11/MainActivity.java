package joinusforthea.choreproject.choremanager11;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//TODO @ EMILIE: The app doesnt crash, but i need to work on setting a task creator
public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {


    //added automatically
    NavigationView navigationView;
    Toolbar toolbar = null;
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private ShoppingFragment shoppingFragment;
    private TasksFragment tasksFragment;
    private PeopleFragment peopleFragment;
    private ImageView avatarImage;



    @Override
    //on create was added automatically
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //EM ADDED THIS WOOHOO!!
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //sets the default tab to tasks
        mViewPager.setCurrentItem(1);

    }



    //EV: taken from Mitch Tabian
    public void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        shoppingFragment = new ShoppingFragment();
        tasksFragment = new TasksFragment();
        peopleFragment = new PeopleFragment();

        adapter.addFragment(shoppingFragment, "Shopping");
        adapter.addFragment(tasksFragment, "Tasks");
        adapter.addFragment(peopleFragment, "People");

        viewPager.setAdapter(adapter);

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

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    @Override
    //EV: this was created automatically
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
    //EV: this was created automatically, then modified
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        mViewPager = (ViewPager) findViewById(R.id.container);
        if (id == R.id.nav_open_task) {
            //--------------------set the initial fragment-------------------
            Toast.makeText(getApplicationContext(), "open task pressed from navigation", Toast.LENGTH_LONG).show();
            mViewPager.setCurrentItem(1);

        } else if (id == R.id.nav_shopping_list) {
            mViewPager.setCurrentItem(0);
        } else if (id == R.id.nav_schedule) {
            Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_task_backlog) {
            Intent intent = new Intent(MainActivity.this, TasksBacklog.class);
            startActivity(intent);
        } else if (id == R.id.nav_people) {
            mViewPager.setCurrentItem(2);
        } else if (id == R.id.nav_cupboard_and_fridge) {
            Intent intent = new Intent(MainActivity.this, CupboardFridgeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_broom_closet) {
            Intent intent = new Intent(MainActivity.this, BroomClosetActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //EV: these methods are called from xml files when something is clicked
    public void openProfile(View view) {

        String avatarTag = null;
        if(view.getTag().equals("hollow_add")){
            Intent intent = new Intent(MainActivity.this, ChooseUserActivity.class);

            View parent = (View) view.getParent();
            TextView choreNameTextView = (TextView)(parent.findViewById(R.id.choreNameTextView));
            String choreName = choreNameTextView.getText().toString();
            intent.putExtra("passedTaskName", choreName.toString());
            startActivity(intent);
        }
        else if (view.getTag().equals("infoTextView")){
            //adding on click listener for clicking the task info
            avatarImage = (ImageView) view.findViewById(R.id.avatarImage);
            avatarTag = avatarImage.getTag().toString();
            Intent intent = new Intent(MainActivity.this, ProfilePageActivity.class);
            intent.putExtra("passedUserInfo", avatarTag);
            startActivity(intent);
        }
        else{
            avatarTag = view.getTag().toString();
            Intent intent = new Intent(MainActivity.this, ProfilePageActivity.class);
            intent.putExtra("passedUserInfo", avatarTag);
            startActivity(intent);
        }


    }

    public void openTask(View view) {
        tasksFragment.openTaskInfo(view);
    }


    public void openMessages(View view) {
        Intent intent = new Intent(MainActivity.this, MessageActivity.class);
        startActivity(intent);
    }

}
