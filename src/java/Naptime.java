package com.example.tim.myapplication;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.CountDownTimer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;
import android.annotation.SuppressLint;

import java.util.List;
import java.util.ArrayList;
import android.content.pm.PackageManager;
import android.widget.ArrayAdapter;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Vibrator;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.github.johnpersano.supertoasts.SuperActivityToast;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.instabug.library.Instabug;
import com.instabug.wrapper.support.activity.InstabugActionBarActivity;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;
import com.squareup.picasso.Picasso;
import com.survivingwithandroid.weather.lib.WeatherClient;
import com.survivingwithandroid.weather.lib.WeatherConfig;
import com.survivingwithandroid.weather.lib.exception.WeatherLibException;
import com.survivingwithandroid.weather.lib.model.CurrentWeather;
import com.survivingwithandroid.weather.lib.model.Weather;
import com.survivingwithandroid.weather.lib.provider.yahooweather.YahooProviderType;
import com.survivingwithandroid.weather.lib.request.WeatherRequest;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dmax.dialog.SpotsDialog;
import me.drakeet.materialdialog.MaterialDialog;
import mehdi.sakout.fancybuttons.FancyButton;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class Naptime extends InstabugActionBarActivity implements OnInitListener, OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    RelativeLayout background;
    private int MY_DATA = 0;
    private TextToSpeech myTTS;
    private static final int REQUEST_CODE = 1234;
    private ListView wordsList;
    public String say = "Did you say ";
    public String firstMatch;
    public static String special = "";
    public String myText = "Hello, How long will you like to nap";
    public String Defaultwkupmsg = "Time's up, wake up";
    public static String sorry = "I'm sorry can you please include hours minutes and seconds";
    public static String sorry2 = "Can you give me a time please and click again";
    public static String tocalc = "";
    public static int time = 0;
    public static CounterClass timer;
    public static Vibrator vibrate;
    TextView textViewTime;
    //TextView from remoteview
    TextView notification;
    //remoteview
    RemoteViews rv;
    public MediaPlayer mp;
    int check = 0;
    //notificationManger, notificaton and ids
    NotificationManager nm;
    static final int NCTID = 224334;
    static final int FINALID = 3856;
    Notification NCT;
    Notification FINAL;
    //Drawaer menu here
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle drawerToggle;

    //info part here
    public String UserName;
    SharedPreferences sp;

    //nightmode here
    //Variable to store brightness value
    private int brightness;
    //Content resolver used as a handle to the system's settings
    private ContentResolver cResolver;
    //Window object, that will store a reference to the current window
    private Window window;

    private Spinner spinner2;
    private MaterialViewPager mViewPager;

    private AlertDialog dialog;
    private MaterialDialog alert;

    public ByteArrayBuffer byteArrayBuffer;
    ShowcaseView v1;
    ShowcaseView v2;
    Boolean isTicking;

    MaterialDialog inputD;
    View layout;
    MaterialDialog ratedialog;
    MaterialDialog updateDialog;

    private double longtitude;
    private double altitude;
    private double latitude;
    String countryName;
    String cityName;
    String stateName;

    GoogleApiClient mGoogleApiClient;
    Woeid id;
    WeatherClient weatherClient;
    String woeid;
    TextView temper;
    TextView curr;
    ImageView wicon;
    TextView currcondition;
    ImageButton refresh;
    CheckBox cb;
    SharedPreferences checked;
    SharedPreferences.Editor checkedEd;
    SharedPreferences version;
    String condition;
    TextView textViewplace;
    FancyButton stop;
    ImageView img;
    View weatherlayout;
    MaterialDialog weatherD;
    private TextView HumtextView;
    private TextView feelstextView;
    private ImageView arrow;
    private TextView showcasetext;
    private SharedPreferences isfirst;
    private SharedPreferences.Editor editfirst;
    public String nature = "http://www.hdiphone6wallpaper.com/wp-content/uploads/Nature/FTP2/Beautiful%20sunrise%20Night%20iPhone%206%20Wallpaper.jpg";
    public String londonred ="http://www.hdiphone6wallpaper.com/wp-content/uploads/City/London%20HD%20landscape%204%20iPhone%206%20Wallpaper.jpg";

    public String pageOne = ""; //will be split into array of urls
    public String page1 = "";
    public String page2 = "";
    public String page3 = "";
    public String page4 = "";
    public String page5 = "";
    public String picture2 = "http://www.ilikewallpaper.net/iphone-5-wallpapers/download/27153/Buildings-Blue-Flare-Night-City-Sky-iphone-5-wallpaper-ilikewallpaper_com.jpg";

    public String picture3 = "http://www.ilikewallpaper.net/iphone-5-wallpapers/download/5030/Rainy-Paris-iphone-5-wallpaper-ilikewallpaper_com.jpg";
   public String localurl = "file:///android_asset/wp1.jpg";
    public String localurl2 = "file:///android_asset/wp2.jpg";
    public String localurl3 = "file:///android_asset/wp3.jpg";
    public String localurl4= "file:///android_asset/wp4.jpg";
    public String localurl5 ="file:///android_asset/wp5.jpg";

    public WindowManager wm;
    public CheckVersion checkVersion;
    public int curVersion;

    /****************************************************************************************************************
     * All Menu items
     ****************************************************************************************************************/
    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, dlDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position
//        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                Intent i = new Intent(this, Info.class);
                startActivity(i);
                break;
            case R.id.feedback:
                SuperToast.create(
                        getApplicationContext(),
                        "Shake device can also send feedback",
                        SuperToast.Duration.SHORT,
                        Style.getStyle(Style.ORANGE, SuperToast.Animations.POPUP)).show();
                Instabug.getInstance().invoke();
                break;
            case R.id.about:
                Intent i2 = new Intent(this, About.class);
                i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i2);
                break;
            case R.id.drop_down2:
                alert.show();
                break;
            case R.id.share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out Naptime \n  https://play.google.com/store/apps/details?id=com.naptime.tim.myapplication");
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
                break;
            case R.id.help:
                Intent helpintent = new Intent(this, MyIntro.class);
                helpintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(helpintent);
                break;

        }
        mDrawer.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    /***************************************************************************************************
     * All Menu Items complete
     *
     * @param
     ***************************************************************************************************/


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /************************************************************************************************
     * Constructor
     *
     * @param savedInstanceState
     ************************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //report bug
        Instabug.initialize(this.getApplication(), "bbcfc3edb3cb7c09fd09d428037d9db9");
        Instabug.getInstance().setShowIntroDialog(false);
        Instabug.getInstance().setPostFeedbackMessage("Thank you for your feedback!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naptime);
        /***********************************************************************
         * We dont want to restart a thread that is already running
         * Other Threads start here.
         * Namely checkVersion class and checkUpdate in the bottom of Naptime
         * Since this is all a try catch consider initializing the heasder  if this fails
         ************************************************************************/
        try {
            curVersion = getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionCode;

        } catch (Exception e) {

        }
        startService(new Intent(Naptime.this, CheckVersion.class));

        if (!checkUpdate.isAlive()) {
           checkUpdate.start();
//            SuperToast.create(getApplicationContext(), "Checking Update",
//                    SuperToast.Duration.SHORT, Style.getStyle(Style.ORANGE, SuperToast.Animations.SCALE)).show();
        }

        isTicking = false;


        /****************************************************************************************
         * Weather Details Dialog
         ****************************************************************************************/
        LayoutInflater inflater123 = (LayoutInflater) Naptime.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        weatherlayout = inflater123.inflate(R.layout.weather, null);
        img = (ImageView) weatherlayout.findViewById(R.id.weahterimg);
        HumtextView = (TextView) weatherlayout.findViewById(R.id.hums);
        feelstextView = (TextView) weatherlayout.findViewById(R.id.feels);
        weatherD = new MaterialDialog(Naptime.this);
        weatherD.setCanceledOnTouchOutside(true);
        weatherD.setView(weatherlayout);
        weatherD.setBackgroundResource(R.drawable.bgcolor);


        /*****************************************************************************************
         ***************Checkbox to not show dialog again
         ****************************************************************************************/
        checked = getSharedPreferences("checked", Context.MODE_PRIVATE);
        checkedEd = checked.edit();

        /*****************************************************************************************
         * Enable GPS if location is off dialog box
         * I will simply use last known location
         ****************************************************************************************/
        //get location part
        buildGoogleApiClient();
        mGoogleApiClient.connect();
        //check if location server is on
        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        boolean gps_enable = false;
        boolean network_enable = false;
        try {
            gps_enable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            network_enable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {

        }
        if (!gps_enable || !network_enable) {
            LayoutInflater inflater1 = (LayoutInflater) Naptime.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater1.inflate(R.layout.alert, null);
            cb = (CheckBox) layout.findViewById(R.id.checkBox);
            final MaterialDialog materialDialog = new MaterialDialog(this);
            materialDialog.setView(layout);
            materialDialog.setNegativeButton("Ignore", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cb.isChecked()) {
                        checkedEd.putBoolean("checked", true);
                        checkedEd.apply();
                    }
                    materialDialog.dismiss();
                }
            });
            materialDialog.setPositiveButton("Turn on Location Services", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });
            if (checked.contains("checked")) {
                if (!checked.getBoolean("checked", true))
                    materialDialog.show();
            } else {
                materialDialog.show();
            }
        }
        /***********************************************************************************
         * End of location
         **********************************************************************************/

        // Initialize text to speech
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
       // startActivityForResult(checkTTSIntent, MY_DATA);


        //Originally for showcase
        isfirst = getSharedPreferences("isfirst", Context.MODE_PRIVATE);
        editfirst = isfirst.edit();


        /**
         *************Initialize MP, Vibration and Spots Dialog to delay perception of Speech Engine Initialization
         */
        mp = MediaPlayer.create(this, R.raw.let_her_go);
        vibrate = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        background = (RelativeLayout) findViewById(R.id.background);

        dialog = new SpotsDialog(this, "Initializing");
        dialog.setCancelable(false);
        dialog.show();


        //info part
        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();
        /**
         * End of Above initialization
         */

        //change fonts family here
        Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
        Typeface tf3 = Typeface.createFromAsset(getAssets(), "Raleway-Regular.ttf");
        TextView logo = (TextView) findViewById(R.id.logo_white);
        logo.setTypeface(tf3);
        final TextView countdown = (TextView) findViewById(R.id.textViewTime);
        countdown.setTypeface(tf);


        /**********************************
         Drawer menu initializations starts here
         **********************************/
        /**
         * if the try-catch failed initialize header to prevent NPE
         */
        if (mViewPager == null) {
            initializeHeader();
            SuperToast.create(getApplicationContext(), "Initializing in npe checker",
                    SuperToast.Duration.SHORT, Style.getStyle(Style.ORANGE, SuperToast.Animations.SCALE)).show();
        }
        // Set a Toolbar to replace the ActionBar.
        toolbar = mViewPager.getToolbar();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.nvView);

        // Setup drawer view
        setupDrawerContent(nvDrawer);

        // Lookup navigation view
        NavigationView navigationView = (NavigationView) findViewById(R.id.nvView);
        NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        if (navigationMenuView != null) {
            navigationMenuView.setVerticalScrollBarEnabled(false);
        }
        // Inflate the header view at runtime
        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header);

        //get things in header

        temper = (TextView) headerLayout.findViewById(R.id.temper);
        curr = (TextView) headerLayout.findViewById(R.id.currtemper);
        wicon = (ImageView) headerLayout.findViewById(R.id.wicon);
        //Picasso.with(getApplicationContext()).load("http://timodie.github.io/weatherimages/snow.png").into(wicon);

        currcondition = (TextView) headerLayout.findViewById(R.id.condition);
        textViewplace = (TextView) headerLayout.findViewById(R.id.place);
        refresh = (ImageButton) headerLayout.findViewById(R.id.refresh);
        refresh.setClickable(false);
        refresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getcurrweather();
                SuperToast.create(getApplicationContext(), "Refreshing",
                        SuperToast.Duration.SHORT, Style.getStyle(Style.ORANGE, SuperToast.Animations.SCALE)).show();
            }
        });

        temper.setTypeface(tf3);
        curr.setTypeface(tf3);

        currcondition.setTypeface(tf3);
        textViewplace.setTypeface(tf3);


        //check if internet available
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            final MaterialDialog mMaterialDialog = new MaterialDialog(this);
            mMaterialDialog.setTitle("Alert!");
            mMaterialDialog.setMessage("No internet access, Some features may not work!");
            mMaterialDialog.setPositiveButton("Ignore", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMaterialDialog.dismiss();
                }
            });
            mMaterialDialog.setNegativeButton("go to setting", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Settings.ACTION_SETTINGS));
                }
            });
            mMaterialDialog.show();
        } else {
            //get currweather if place is not empty and there is internet access
            sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            if (sp.contains("place")) {
                if (!sp.getString("place", "").equals("")) {
                    cityName = sp.getString("place", "");
                    getcurrweather();
                }
            } else { //change here to last known location
                cityName = "NewYork NY";
                getcurrweather();
            }
        }
        version = getSharedPreferences("versionNo", Context.MODE_PRIVATE);


        // We can now look up items within the header if needed
//        ImageView ivHeaderPhoto = headerLayout.findViewById(R.id.imageView);

        // Find our drawer view
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        // Tie DrawerLayout events to the ActionBarToggle
        dlDrawer.setDrawerListener(drawerToggle);


        /**********************************
         Drawer menu ends here
         **********************************/

        //customize the font family of menu item title
        class CustomTypefaceSpan extends TypefaceSpan {

            private final Typeface newType;

            public CustomTypefaceSpan(String family, Typeface type) {
                super(family);
                newType = type;
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                applyCustomTypeFace(ds, newType);
            }

            @Override
            public void updateMeasureState(TextPaint paint) {
                applyCustomTypeFace(paint, newType);
            }

            private void applyCustomTypeFace(Paint paint, Typeface tf) {
                int oldStyle;
                Typeface old = paint.getTypeface();
                if (old == null) {
                    oldStyle = 0;
                } else {
                    oldStyle = old.getStyle();
                }

                int fake = oldStyle & ~tf.getStyle();
                if ((fake & Typeface.BOLD) != 0) {
                    paint.setFakeBoldText(true);
                }

                if ((fake & Typeface.ITALIC) != 0) {
                    paint.setTextSkewX(-0.25f);
                }

                paint.setTypeface(tf);
            }
        }

        /*************************
         night mode here
         **************************/
        //Get the content resolver
        cResolver = getContentResolver();

        //Get the current window
        window = getWindow();

        try {
            // To handle the auto
            Settings.System.putInt(cResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            //Get the current system brightness
            brightness = Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            //Throw an error case it couldn't be retrieved
            Log.e("Error", "Cannot access system brightness");
            e.printStackTrace();
        }

        Menu menu = navigationView.getMenu();

        /***************************************************
         change font for subitem titles(Support menu items)
         */
        MenuItem feedback = menu.findItem(R.id.feedback);
        SpannableString feedbacktitle = new SpannableString(feedback.getTitle());
        feedbacktitle.setSpan(new CustomTypefaceSpan("", tf3), 0, feedbacktitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        feedback.setTitle(feedbacktitle);

        MenuItem about = menu.findItem(R.id.about);
        SpannableString aboutitle = new SpannableString(about.getTitle());
        aboutitle.setSpan(new CustomTypefaceSpan("", tf3), 0, aboutitle.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        about.setTitle(aboutitle);

        MenuItem subitems = menu.findItem(R.id.subitems);
        SpannableString subtitle = new SpannableString(subitems.getTitle());
        subtitle.setSpan(new CustomTypefaceSpan("", tf3), 0, subtitle.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        subitems.setTitle(subtitle);

        MenuItem share = menu.findItem(R.id.share);
        SpannableString shareitem = new SpannableString(share.getTitle());
        shareitem.setSpan(new CustomTypefaceSpan("", tf3), 0, shareitem.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        share.setTitle(shareitem);

        MenuItem help = menu.findItem(R.id.help);
        SpannableString helpTitle = new SpannableString(help.getTitle());
        helpTitle.setSpan(new CustomTypefaceSpan("", tf3), 0, helpTitle.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        help.setTitle(helpTitle);

        /***************************************************
         change font for subitem titles(feedback and about) END
         */


        /***************************************************
         RingTone
         ***************************************************/

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        adapter.add("Song1");
        adapter.add("Song2");
        adapter.add("My Songs");


        ListView listView = new ListView(this);
        listView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                switch (item) {
                    case "Options":
                        break;
                    case "Song1": {
                        final MediaPlayer Def = MediaPlayer.create(Naptime.this, R.raw.let_her_go);
                        editor.putString("ring", "Song1");
                        editor.apply();
                        //view the ringtone
                        CountDownTimer cntr_aCounter = new CountDownTimer(3000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                Def.start();
                            }

                            public void onFinish() {
                                //code fire after finish
                                Def.pause();
                                Def.seekTo(0);
                            }
                        };
                        cntr_aCounter.start();

                    }
                    break;
                    case "Song2": {
                        final MediaPlayer Def = MediaPlayer.create(Naptime.this, R.raw.w);
                        editor.putString("ring", "Song2");
                        editor.apply();
                        //view the ringtone
                        CountDownTimer cntr_aCounter = new CountDownTimer(3000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                Def.start();
                            }

                            public void onFinish() {
                                //code fire after finish
                                Def.pause();
                                Def.seekTo(0);
                            }
                        };
                        cntr_aCounter.start();

                    }
                    break;
                    case "My Songs": {
                        editor.putString("ring", "My Songs");
                        editor.apply();
                        Intent i = new Intent(Naptime.this, RingTone.class);
                        startActivity(i);
                        alert.dismiss();
                    }
                    break;
                }

            }
        });
        listView.setAdapter(adapter);

        alert = new MaterialDialog(this).setTitle(
                "WakeUp Song").setContentView(listView);

        alert.setPositiveButton("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        MenuItem dropdown2 = menu.findItem(R.id.drop_down2);
//        View drop_view2 = MenuItemCompat.getActionView(dropdown2);
//        spinner2 = (Spinner)drop_view2.findViewById(R.id.spinner);
        //set font family
        SpannableString mNewTitle5 = new SpannableString(dropdown2.getTitle());
        mNewTitle5.setSpan(new CustomTypefaceSpan("", tf3), 0, mNewTitle5.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        dropdown2.setTitle(mNewTitle5);


        /***************************************************
         RingTone END
         ***************************************************/

        //Menu item for General
        MenuItem info = menu.findItem(R.id.nav_first_fragment);
        //set font family here
        SpannableString mNewTitle4 = new SpannableString(info.getTitle());
        mNewTitle4.setSpan(new CustomTypefaceSpan("", tf3), 0, mNewTitle4.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        info.setTitle(mNewTitle4);

        /**************
         * switch_vibrate here
         **************/
        MenuItem switch_vibrate = menu.findItem(R.id.v_switch);
        //set font family of title
        SpannableString mNewTitle = new SpannableString(switch_vibrate.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", tf3), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        switch_vibrate.setTitle(mNewTitle);
        View s_v = MenuItemCompat.getActionView(switch_vibrate);
        SwitchCompat s_vw = (SwitchCompat) s_v.findViewById(R.id.vswitch);
        if (sp.getString("vb", "").equals("1")) {
            s_vw.setChecked(true);
        }
        s_vw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putString("vb", "1");
                    editor.apply();
                    SuperActivityToast.create(Naptime.this, "Vibration On", SuperToast.Duration.SHORT, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();
                } else {
                    editor.putString("vb", "0");
                    editor.apply();
                    SuperActivityToast.create(Naptime.this, "Vibration Off", SuperToast.Duration.SHORT, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();
                }

            }
        });
        /**************
         * switch_vibrate ends here
         **************/

        MenuItem menuItem = menu.findItem(R.id.nav_switch);
        //set font family
        SpannableString mNewTitle3 = new SpannableString(menuItem.getTitle());
        mNewTitle3.setSpan(new CustomTypefaceSpan("", tf3), 0, mNewTitle3.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        menuItem.setTitle(mNewTitle3);

        View actionView = MenuItemCompat.getActionView(menuItem);
        SwitchCompat sw = (SwitchCompat) actionView.findViewById(R.id.navswitch);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    //Set the system brightness using the brightness variable value
                    Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
                    //Get the current window attributes
                    WindowManager.LayoutParams layoutpars = window.getAttributes();
                    //Set the brightness of this window
                    layoutpars.screenBrightness = 20 / (float) 255;
                    //Apply attribute changes to this window
                    window.setAttributes(layoutpars);

                    SuperActivityToast.create(Naptime.this, "Night Mode On", SuperToast.Duration.SHORT, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();
                } else {
                    // The toggle is disabled
                    //Set the system brightness using the brightness variable value
                    Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
                    //Get the current window attributes
                    WindowManager.LayoutParams layoutpars = window.getAttributes();
                    //Set the brightness of this window
                    layoutpars.screenBrightness = brightness / (float) 255;
                    //Apply attribute changes to this window
                    window.setAttributes(layoutpars);

                    SuperActivityToast.create(Naptime.this, "Night Mode Off", SuperToast.Duration.SHORT, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();
                }
            }
        });
        /*************************
         night mode ends here
         **************************/


        /***************************************
         * **********Notification Starts Here
         *******************************************/
        Intent intent1 = new Intent(this, Naptime.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent1, 0);
        //create remoteview for notification countdown timer
        rv = new RemoteViews(this.getPackageName(), R.layout.remoteview);

        NCT = new Notification.Builder(this)
                .setContent(rv)
                .setPriority(Notification.PRIORITY_MAX)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setDefaults(0)
                .setContentIntent(pi)
                .setSmallIcon(R.drawable.small)
                .build();

        FINAL = new Notification.Builder(this)
                .setContentTitle("NapTime")
                .setContentText("Times up !!")
                .setPriority(Notification.PRIORITY_MAX)
                .setWhen(System.currentTimeMillis())
                .setFullScreenIntent(pi, true)
                .setAutoCancel(true)
                .setDefaults(0)
                .setContentIntent(pi)
                .setSmallIcon(R.drawable.small)
                .build();
        nm = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        nm.cancel(FINALID);
        //ends here


        //--------get notification from remoteview.xml-----------
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vi = inflater.inflate(R.layout.remoteview, null);
        //display countdown in notification, initialize in onTick
        notification = (TextView) vi.findViewById(R.id.notification);
        //--------------------------------------------------------
        //TextView to display timer
        textViewTime = (TextView) findViewById(R.id.textViewTime);

        /****************
         Button Part
         *********************/
        final FancyButton nap = (FancyButton) findViewById(R.id.nap);
        final FancyButton input = (FancyButton) findViewById(R.id.input);
        stop = (FancyButton) findViewById(R.id.stop);
        stop.setEnabled(false);
        stop.setBackgroundColor(Color.GRAY);


        nap.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //ask how long will you like to nap here
//                Animation an = AnimationUtils.loadAnimation(Naptime.this, R.anim.fade_in);
//                stop.startAnimation(an);
                if (mp.isPlaying()) {
                    mp.stop();
                }
                if (isTicking == true) {
                    SuperActivityToast.create(Naptime.this, "Timer is already running", SuperToast.Duration.SHORT,
                            Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();
                } else {
                    UserName = sp.getString("username", "");
                    if (UserName != null) {
                        myText = "Hello, " + UserName + ", " + "How long will you like to nap";
                    }
                    speakWords(myText);
                    while (true) {
                        if (!myTTS.isSpeaking())
                            break;
                    }
                    naptButtonClicked();
                    stop.setEnabled(true);
                    stop.setBackgroundColor(Color.parseColor("#ff5f69"));
                }
            }
        });


        stop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                stop.setEnabled(false);
                stop.setBackgroundColor(Color.GRAY);

                Intent startIntent = new Intent(Naptime.this, ForegroundService.class);
                startIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
                startService(startIntent);
                //Stop checkversion service
                stopService(new Intent(Naptime.this, CheckVersion.class));

                isTicking = false;

                if (check == 1) {
                    timer.cancel();
                    textViewTime.setText("");
                }


                mp.stop();

                //stops notification countdown timer
                nm.cancel(NCTID);
                nm.cancel(FINALID);
                vibrate.cancel();
//if want to restart natpimer
                Naptime.this.recreate();
            }
        });


        input.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isTicking == true) {
                    SuperToast.create(
                            getApplicationContext(),
                            "Timer is already running",
                            SuperToast.Duration.SHORT,
                            Style.getStyle(Style.GRAY, SuperToast.Animations.FLYIN)).show();
//
                } else {

                    typeToNap();
                    inputD.show();
                }
            }
        });

        // start.setOnClickListener(this);
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(new
                Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0) {
            nap.setBackgroundColor(Color.GRAY);
            final MaterialDialog mMaterialDialog2 = new MaterialDialog(this);
            mMaterialDialog2.setTitle("Oops!");
            mMaterialDialog2.setMessage("Recognizer not present but still trying");
            mMaterialDialog2.setPositiveButton("Ok", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMaterialDialog2.dismiss();
                }
            });
            mMaterialDialog2.show();
        }
        checkVersion();
    }


    /******************************************************************************************************************
     * END OF CONSTRUCTOR-----END OF CONSTRUCTOR----END OF CONSTRUCTOR
     * Longest constrctor ever lol
     * Upcoming Methods : checkVersion(), typeToNap()
     *******************************************************************************************************************/
    public void checkVersion() {
        //SuperActivityToast.create(Naptime.this, "Checking version method ", SuperToast.Duration.SHORT, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();

        if (version.contains("newVersion")) {
            SuperActivityToast.create(Naptime.this, "Checkversion is " + version.getString("newVersion", ""), SuperToast.Duration.SHORT, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();
            String latest = version.getString("newVersion", "");
            try {
                int newVer = extractInt(latest);

                if (curVersion < newVer) {
                    SuperActivityToast.create(Naptime.this, "Update Available", SuperToast.Duration.SHORT, Style.getStyle(Style.RED, SuperToast.Animations.FADE)).show();
                    updateAlert();
                    updateDialog.show();
                }
            } catch (NumberFormatException e) {
                SuperActivityToast.create(Naptime.this, "NumberFormatExceptionThrown", SuperToast.Duration.SHORT, Style.getStyle(Style.RED, SuperToast.Animations.FADE)).show();

            }
        } else {
            //SuperActivityToast.create(Naptime.this, "Version unavailable", SuperToast.Duration.SHORT, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();

        }
    }


    public void typeToNap() {
        LayoutInflater inflater1 = (LayoutInflater) Naptime.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = inflater1.inflate(R.layout.edittext, null);
        inputD = new MaterialDialog(Naptime.this);
        inputD.setView(layout);
        inputD.setCanceledOnTouchOutside(true);
        inputD.setPositiveButton("START", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    mp.stop();
                }
                int h1;
                int m1;
                int s1;
                MaterialEditText e1 = (MaterialEditText) layout.findViewById(R.id.editText);
                MaterialEditText e2 = (MaterialEditText) layout.findViewById(R.id.editText2);
                MaterialEditText e3 = (MaterialEditText) layout.findViewById(R.id.editText3);
                e1.addValidator(new RegexpValidator("Too long", "\\d{0,2}"));
                e2.addValidator(new RegexpValidator("Too long", "\\d{0,2}"));
                e3.addValidator(new RegexpValidator("Too long", "\\d{0,2}"));

                String hour = e1.getText().toString();
                String minute = e2.getText().toString();
                String second = e3.getText().toString();

                String h = "hours";
                String m = "minutes";
                String s = "seconds";

                if (hour.equals("") && minute.equals("") && second.equals("")) {
                    SuperToast.create(
                            getApplicationContext(),
                            "Please enter numbers",
                            SuperToast.Duration.SHORT,
                            Style.getStyle(Style.GREEN, SuperToast.Animations.FLYIN)).show();
                } else {
                    if (!hour.equals("")) {
                        h1 = extractInt(hour);
                        if (h1 == 1)
                            h = "hour";
                    } else {
                        h = "";
                        h1 = 0;
                        hour = "";
                    }

                    if (!minute.equals("")) {
                        m1 = extractInt(minute);
                        if (m1 == 1)
                            m = "minute";
                    } else {
                        m = "";
                        m1 = 0;
                        minute = "";
                    }

                    if (!second.equals("")) {
                        s1 = extractInt(second);
                        if (s1 == 1)
                            s = "second";
                    } else {
                        s = "";
                        s1 = 0;
                        second = "";
                    }
                    int t = h1 * 60 * 60000 + m1 * 60000 + s1 * 1000;

                    int actualhour = (t / (60 * 60000));
                    if (!(actualhour == 0)) {
                        hour = Integer.toString(actualhour);
                        if (actualhour == 1) {
                            h = "hour";
                        }
                        int actualmin = (t % (actualhour * 60 * 60000)) / 60000;
                        if (actualmin == 1) {
                            h = "minute";
                        }
                        if (!(actualmin == 0)) {
                            minute = Integer.toString(actualmin);
                            int actualsec = ((t % (actualhour * 60 * 60000)) % (actualmin * 60000)) / 1000;
                            if (actualsec == 1) {
                                h = "second";
                            }
                            if (!(actualsec == 0))
                                second = Integer.toString(actualsec);
                        }
                    }


                    if (e1.validate() && e2.validate() && e3.validate()) {
                        e1.setText("");
                        e2.setText("");
                        e3.setText("");
                        speakWords("Ok, I will wake you up in , " + hour + h + ", " + minute + m + ", " + second + s);
                        SystemClock.sleep(2000);

                        timer = new CounterClass(t, 1000);
                        timer.start();
                        check = 1;
                        stop.setEnabled(true);
                        stop.setBackgroundColor(Color.parseColor("#ff5f69"));
                        inputD.dismiss();
                    }
                }
            }
        });

        inputD.setNegativeButton("CANCEL", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputD.dismiss();
            }
        });

    }


    /*****************************************************************************************************************
     * Voice Recogntion and Text to Speech Methods
     * The heart of the code exists in onActivityResult
     ******************************************************************************************************************/
    public void naptButtonClicked() {

        startVoiceRecognitionActivity();
    }

    private void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please start speaking...");
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void onClick(View v) {
    }

    private void speakWords(String speech) {
        // myTTS.speak(myText,TextToSpeech.QUEUE_ADD,null);
        myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_DATA) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                myTTS = new TextToSpeech(this, this);
            } else {
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            //Fill the words list with the string values the recognition engine thought it heard
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            ListView list = (ListView)findViewById(R.id.list);

            //debug
//            ArrayAdapter<String> adp = new ArrayAdapter<String>((getApplicationContext()), android.R.layout.simple_list_item_1, matches);
//            list.setAdapter(adp);

//            wordsList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, matches));
            firstMatch = matches.get(0).toLowerCase();
            if (firstMatch.equals("forever")) {
                speakWords("Seriously");
            } else if (isNumeric(firstMatch)) {
                speakWords(sorry);
                while (true) {
                    if (!myTTS.isSpeaking())
                        break;
                }
                naptButtonClicked();
            } else {
                //calculate time
                if (firstMatch.split(" ")[0].equals("yes") || firstMatch.split(" ")[0].equals("yeah")) {
                    speakWords("ok,I will wake you up in, " + tocalc);
                    if (tocalc.equals("half an hour")) time = 30 * 60000;
                    else if (tocalc.equals("one minute")) time = 60000;
                    else if (tocalc.equals("an hour")) time = 60 * 60000;
                    else {
                        int result = 0;
                        String tmp[] = tocalc.split(" ");
                        for (int i = 0; i < tmp.length; i++) {
                            if (isNumeric(tmp[i])) {
                                switch (tmp[i + 1]) {
                                    case "hours":
                                        result += (extractInt(tmp[i]) * 60 * 60000);
                                        break;
                                    case "hour":
                                        result += (extractInt(tmp[i]) * 60 * 60000);
                                        break;
                                    case "minutes":
                                        result += (extractInt(tmp[i]) * 60000);
                                        break;
                                    case "seconds":
                                        result += (extractInt(tmp[i]) * 1000);
                                        break;
                                }
                            }
                        }
                        time = result;
                    }
                    timer = new CounterClass(time, 1000);
                    SystemClock.sleep(2000);
                    timer.start();
                    check = 1;
                } else if (firstMatch.equals("no")) {
                    speakWords("Can you tell me again");
                    while (true) {
                        if (!myTTS.isSpeaking())
                            break;
                    }
                    naptButtonClicked();
                } else {
                    firstMatch = VoiceSynthesis(firstMatch);
                    if (special == "") {
                        if ((firstMatch.split(" ")).length < 2) {
                            speakWords(sorry2);
                        } else {
                            speakWords(say + firstMatch + ", " + UserName);
                            tocalc = firstMatch;
                            while (true) {
                                if (!myTTS.isSpeaking())
                                    break;
                            }
                            naptButtonClicked();
                        }
                    } else {
                        speakWords(special);
                    }
                    special = "";
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    public static int extractInt(String s) {
        Matcher match = Pattern.compile("\\d+").matcher(s);
        if (!match.find())
            throw new NumberFormatException();
        else
            return Integer.parseInt(match.group());

    }

    public static boolean isNumeric(String inputData) {
        return inputData.matches("[-+]?\\d+(\\.\\d+)?");
    }

    private static String VoiceSynthesis(String first) {
        String speech = "";
        String words[] = first.split(" ");
        String output[] = new String[100];
        String keywords[] = {"half", "hour", "hours", "minutes", "minute", "seconds", "and", "one"};
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (isNumeric(words[i])) {
                output[count] = words[i];
                count++;
            } else {
                for (int a = 0; a < keywords.length; a++) {
                    if (words[i].equals(keywords[a])) {
                        output[count] = words[i];
                        count++;
                    }
                }
            }
        }
        if (output[0] == null) special = sorry2;
        else if (output[0].equals("and")) {
            special = sorry;
        } else if (output[0].equals("hour")) {
            speech = "an hour";
        } else if (!isNumeric(output[0])) {
            if (output[0].toLowerCase().equals("one")) {
                speech = "one minute";
            } else {
                speech = "half an hour";
            }
        } else {
            int i = 0;
            String s = "";
            while (output[i] != null) {
                s += (output[i] + " ");
                i++;
            }
            speech = s;
        }
        return speech;
    }

    /*****************************************************************************************
     * End of Speech recogntion and text to speech methods
     *
     *****************************************************************************************/

    /****************************************************
     * Generic Andoroid Methods
     * Very likely to not require edits in the future
     ****************************************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_naptime, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Make sure this is the method with just `Bundle` as the signature
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDestroy() {
        //Close the Text to Speech Library
        if (myTTS != null) {
            myTTS.stop();
            myTTS.shutdown();
            Log.d("...........", "TTS Destroyed");
        }
        //timer.cancel();
        Intent startIntent = new Intent(Naptime.this, ForegroundService.class);
        startIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
        startService(startIntent);
        //Stop checkVersion
        stopService(new Intent(Naptime.this, CheckVersion.class));

//        bubblesManager.recycle();
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            if (myTTS.isLanguageAvailable(Locale.US) == TextToSpeech.LANG_AVAILABLE)
                myTTS.setLanguage(Locale.US);
            dialog.dismiss();
        }
        if (status == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! TTS failed...", Toast.LENGTH_LONG).show();
        }
    }

    /*********************************************************************************************
     * *  CounterClass- Everything about timer, on tick and OnFinish
     *********************************************************************************************/


    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        // countdown timer finishes
        public void onFinish() {

            //disable keyguard here
            KeyguardManager km = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
            final KeyguardManager.KeyguardLock kl = km.newKeyguardLock("my_app");
            kl.disableKeyguard();

            //keyguardlock is deprecated in API > 17 try flags instead:
            wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

            //Unlock
            final Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
            // window.addFlags(WindowManager.LayoutParams.FLAG_);
            window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);


            //acquire wakelock
            PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
            final PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.FULL_WAKE_LOCK, "MyWakeLock");
            wakeLock.acquire();

            //start naptime
            Intent naptime = getIntent();
            naptime.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(naptime);

            //show snooze dialog
            final MaterialDialog snooze = new MaterialDialog(Naptime.this);
            snooze.setTitle("Snooze");
            snooze.setMessage("Wanna snooze?");
            snooze.setPositiveButton("snooze", new OnClickListener() {
                @Override
                public void onClick(View v) {
                    stop.performClick();
                    snooze.dismiss();
                    wakeLock.release();
                    kl.reenableKeyguard();
                    timer = new CounterClass(5 * 60000, 1000);
                    timer.start();
                    check = 1;
                    stop.setEnabled(true);
                    stop.setBackgroundColor(Color.parseColor("#ff5f69"));
                }

            });
            snooze.setNegativeButton("Dismiss", new OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    kl.reenableKeyguard();
                    //api >17
                    wakeLock.release();
                    stop.performClick();
                    snooze.dismiss();
                }
            });
            snooze.show();

            /**************
             * get music part
             *************/
            if (sp.getString("ring", "").equals("Song1") || !sp.contains("ring")) {
                mp = MediaPlayer.create(Naptime.this, R.raw.let_her_go);
            } else if (sp.getString("ring", "").equals("Song2")) {
                mp = MediaPlayer.create(Naptime.this, R.raw.w);
            } else {
                File song = new File(getFilesDir() + "/" + "song");
                Uri u;
                u = Uri.parse(song.toString());
                MediaPlayer mp2 = MediaPlayer.create(Naptime.this, u);
                if (mp2 != null)
                    mp = mp2;
            }
            mp.setLooping(true);
            /**************
             * get music part END
             *************/
            isTicking = false;
            textViewTime.setText("Time's up.");
            if (!sp.getString("wkupmsg", "").equals(""))
                Defaultwkupmsg = sp.getString("wkupmsg", "");
            speakWords(Defaultwkupmsg);
            //cancel the notification countdown timer
            nm.cancel(NCTID);
            //notify time's up
            nm.notify(FINALID, FINAL);

            Intent startIntent = new Intent(Naptime.this, ForegroundService.class);
            startIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
            startService(startIntent);

            //rate dialog
            ratedialog = new MaterialDialog(Naptime.this);
            ratedialog.setTitle("Like our app?");
            ratedialog.setMessage("Please rate our app and leave feedback!");
            ratedialog.setPositiveButton("Rate now", new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    // To count with Play market backstack, After pressing back button,
                    // to taken back to our application, we need to add following flags to intent.
                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                            Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    try {
                        startActivity(goToMarket);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
                    }
                    ratedialog.dismiss();
                    mp.stop();
                }
            });
            ratedialog.setNegativeButton("Cancel", new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ratedialog.dismiss();
                }
            });
            SharedPreferences napcount = getSharedPreferences("napcount", Context.MODE_PRIVATE);
            SharedPreferences.Editor napcounted = napcount.edit();
            if (napcount.contains("napcount")) {
                int tmp = napcount.getInt("napcount", 0);
                if (tmp == 4) {
                    ratedialog.show();
                    tmp = -1;
                }
                tmp++;
                napcounted.putInt("napcount", tmp);
                napcounted.apply();
            } else {
                ratedialog.show();
                napcounted.putInt("napcount", 0);
                napcounted.apply();
            }

            //ratedialog END


            SystemClock.sleep(1000);
            mp.start();
            //virbate pattern
            int dot = 200;      // Length of a Morse Code "dot" in milliseconds
            int dash = 500;     // Length of a Morse Code "dash" in milliseconds
            int short_gap = 200;    // Length of Gap Between dots/dashes
            int medium_gap = 500;   // Length of Gap Between Letters
            int long_gap = 1000;    // Length of Gap Between Words
            long[] pattern = {
                    0,  // Start immediately
                    dot, dash, short_gap, medium_gap, long_gap
            };
            if (sp.getString("vb", "").equals("1")) {
                vibrate.vibrate(pattern, 1);
            }
        }

        @SuppressLint("NewApi")
        //@TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            textViewTime.setText(hms);

            //foreground service
            Intent startIntent = new Intent(Naptime.this, ForegroundService.class);
            startIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
            startService(startIntent);

            isTicking = true;
            //start notification countdown timer
            rv.setTextViewText(R.id.notification, hms);
            nm.notify(NCTID, NCT);
        }
    }

    public void snoozeDialog(){
        timer = new CounterClass(5 * 60000, 1000);
        timer.start();

    }
/***************************************************************************************************
 * End of Counter Class & Snooze
 **************************************************************************************************/


    /*****************************************************************************************************
     * Weather Items
     *************************************************************************************************/
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void getcurrweather() {
        if (isfirst.contains("check")) {
            //arrow.setImageBitmap(null);

        }

        try {
            WeatherClient.ClientBuilder builder = new WeatherClient.ClientBuilder();
            WeatherConfig config = new WeatherConfig();
            config.unitSystem = WeatherConfig.UNIT_SYSTEM.M;
            config.lang = "en";
            weatherClient = builder.attach(Naptime.this)
                    .provider(new YahooProviderType())
                    .httpClient(com.survivingwithandroid.weather.lib.client.okhttp.WeatherDefaultClient.class)
                    .config(config)
                    .build();
            weatherClient.updateWeatherConfig(config);


            id = new Woeid(cityName + " " + stateName + " " + countryName);
            CountDownTimer t = new CountDownTimer(3000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    woeid = id.getWoeid();
                    refresh.setClickable(false);
                    editfirst.commit();
                }

                @Override
                public void onFinish() {
                    weatherClient.getCurrentCondition(new WeatherRequest(woeid), new WeatherClient.WeatherEventListener() {
                        @Override
                        public void onWeatherRetrieved(CurrentWeather weather) {
                            int minTemp = (int) weather.weather.temperature.getMinTemp();
                            int maxTemp = (int) weather.weather.temperature.getMaxTemp();
                            int temp = (int) weather.weather.temperature.getTemp();

                            int visibility = (int) weather.weather.currentCondition.getVisibility();
                            int hum = (int) weather.weather.currentCondition.getHumidity();
                            int feeling = (int) weather.weather.currentCondition.getFeelsLike();

                            String feels = "Visibility is " + String.valueOf(feeling) + " km";
                            String hums = "Humidity is " + String.valueOf(hum) + "%";

                            HumtextView.setText(hums);
                            feelstextView.setText(feels);

                            if (weather.weather.currentCondition.getWeatherCode() != null) {
                                condition = weather.weather.currentCondition.getWeatherCode().name();
                            }
                            temper.setText(String.valueOf(minTemp) + "°C" + " / " + String.valueOf(maxTemp) + "°C");
                            curr.setText(String.valueOf(temp) + "°C");
                            textViewplace.setText(cityName);
                            temper.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                   // arrow.setImageBitmap(null);
                                    weatherD.show();
                                }
                            });
                            curr.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                   // arrow.setImageBitmap(null);
                                    weatherD.show();
                                }
                            });
                            textViewplace.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                  //  arrow.setImageBitmap(null);
                                    weatherD.show();
                                }
                            });
                            Bitmap refreshicon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_cached_white_18dp);
                            refresh.setImageBitmap(refreshicon);
                            refresh.setClickable(true);

                            //all weather code and corresponding icon
                            final LinkedHashMap<String, String> weathercode = new LinkedHashMap();
                            weathercode.put("CLOUDY", "timodie.github.io/weatherimages/cloudy.png");
                            weathercode.put("CLEAR_NIGHT", "timodie.github.io/weatherimages/clear_night.png");
                            weathercode.put("FAIR_NIGHT", "timodie.github.io/weatherimages/fair_night.png");
                            weathercode.put("FAIR_DAY", "timodie.github.io/weatherimages/fair_day.png");
                            weathercode.put("DUST","timodie.github.io/weatherimages/dust.png");
                            weathercode.put("FOGGY", "timodie.github.io/weatherimages/foggy.png");
                            weathercode.put("HEAVY_SHOWERS", "timodie.github.io/weatherimages/showers.png");
                            weathercode.put("HEAVY_SNOW", "timodie.github.io/weatherimages/heavy_snow.png");
                            weathercode.put("PARTLY_CLOUDY_NIGHT", "timodie.github.io/weatherimages/partly_cloudy_night.png");
                            weathercode.put("PARTLY_CLOUDY_DAY", "timodie.github.io/weatherimages/partly_cloudy_day.png");
                            weathercode.put("PARTLY_CLOUDY", "timodie.github.io/weatherimages/partly_cloudy_day.png");
                            weathercode.put("SHOWERS", "timodie.github.io/weatherimages/showers.png");
                            weathercode.put("SNOW", "timodie.github.io/weatherimages/snow.png");
                            weathercode.put("SUNNY", "timodie.github.io/weatherimages/sunny.png");
                            weathercode.put("WINDY", "timodie.github.io/weatherimages/windy.png");
                            weathercode.put("BLOWING_SNOW", "timodie.github.io/weatherimages/blowing_snow.png");
                            weathercode.put("BLUSTERY", "timodie.github.io/weatherimages/blustery.png");
                            weathercode.put("COLD", "timodie.github.io/weatherimages/cold.png");
                            weathercode.put("DRIZZLE", "timodie.github.io/weatherimages/drizzle.png");
                            weathercode.put("FREEZING_DRIZZLE", "timodie.github.io/weatherimages/freezing_drizzle.png");
                            weathercode.put("FREEZING_RAIN", "timodie.github.io/weatherimages/freezing_rain.png");
                            weathercode.put("HAIL", "timodie.github.io/weatherimages/hail.png");
                            weathercode.put("HAZE", "timodie.github.io/weatherimages/haze.png");
                            weathercode.put("HOT", "timodie.github.io/weatherimages/hot.png");
                            weathercode.put("HURRICANE", "timodie.github.io/weatherimages/hurricane.png");
                            weathercode.put("ISOLATED_THUDERSHOWERS", "timodie.github.io/weatherimages/isolated_thundershowers.png");
                            weathercode.put("ISOLATED_THUNDERSTORMS", "timodie.github.io/weatherimages/isolated_thunderstorms.png");
                            weathercode.put("LIGHT_SNOW_SHOWERS", "timodie.github.io/weatherimages/light_snow_showers.png");
                            weathercode.put("MIXED_RAIN_AND_HAIL", "timodie.github.io/weatherimages/mixed_rainandhail.png");
                            weathercode.put("MIXED_RAIN_SLEET", "timodie.github.io/weatherimages/mixed_rainandsleet.png");
                            weathercode.put("MIXED_RAIN_SNOW", "timodie.github.io/weatherimages/mixed_rainandsnow.png");
                            weathercode.put("MIXED_SNOW_SLEET", "timodie.github.io/weatherimages/mixed_snowandsleet.png");
                            weathercode.put("MOSTLY_CLOUDY_DAY", "timodie.github.io/weatherimages/mostly_cloudy_day.png");
                            weathercode.put("MOSTLY_CLOUDY_NIGHT", "timodie.github.io/weatherimages/mostly_cloudy_night.png");
                            weathercode.put("SCATTERED_SHOWERS","timodie.github.io/weatherimages/scattered_showers.png");
                            weathercode.put("SCATTERED_SNOW_SHOWERS", "timodie.github.io/weatherimages/scattered_snow_showers.png");
                            weathercode.put("SCATTERED_THUNDERSTORMS", "timodie.github.io/weatherimages/scattered_thunderstorms.png");
                            weathercode.put("SCATTERED_THUNDERSTORMS_1", "timodie.github.io/weatherimages/scattered_thunderstorms.png");
                            weathercode.put("SEVERE_THUNDERSTORMS", "timodie.github.io/weatherimages/severe_thunderstorms.png");
                            weathercode.put("SLEET", "timodie.github.io/weatherimages/sleet.png");
                            weathercode.put("SMOKY", "timodie.github.io/weatherimages/smoky.png");
                            weathercode.put("SMOKY", "timodie.github.io/weatherimages/smoky.png");
                            weathercode.put("SNOW_FLURRIES", "timodie.github.io/weatherimages/snow_flurries.png");
                            weathercode.put("SNOW_SHOWERS", "timodie.github.io/weatherimages/snow_showers.png");
                            weathercode.put("THUNDERSHOWERS", "timodie.github.io/weatherimages/thundershowers.png");
                            weathercode.put("THUNDERSTORMS", "timodie.github.io/weatherimages/thunderstorms.png");
                            weathercode.put("TORNADO", "timodie.github.io/weatherimages/tornado.png");
                            weathercode.put("TORNADO", "timodie.github.io/weatherimages/tornado.png");
                            weathercode.put("TROPICAL_STORM", "timodie.github.io/weatherimages/tropical_storm.png");
                            weathercode.put("NOT_AVAILABLE","0");
                            if (weathercode.containsKey(condition)) {
                                SuperToast.create(getApplicationContext(), "contains key ",
                                        SuperToast.Duration.SHORT, Style.getStyle(Style.GREEN, SuperToast.Animations.FADE)).show();
                                if (weathercode.containsKey(condition)) {
                                    // Bitmap b = BitmapFactory.decodeResource(getResources(), weathercode.get(condition));
                                    //img.setImageBitmap(b);
                                    // wicon.setImageBitmap(b);
                                    String icon = weathercode.get(condition);
                                    if(!icon.equals("0")) {
                                        Picasso.with(getApplicationContext()).load("http://" + icon).into(wicon);
                                    }else{
                                        Picasso.with(getApplicationContext()).load("http://timodie.github.io/weatherimages/cloudy.png").into(wicon);
                                    }
                                    currcondition.setText(condition.replace("_", " ").toLowerCase());
                                    currcondition.setOnClickListener(new OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //arrow.setImageBitmap(null);
                                            weatherD.show();
                                        }
                                    });
                                    img.setOnClickListener(new OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                           // arrow.setImageBitmap(null);
                                            weatherD.show();
                                        }
                                    });
                                } else {
                                    SuperToast.create(getApplicationContext(), "Oops, weather is not available",
                                            SuperToast.Duration.SHORT, Style.getStyle(Style.GREEN, SuperToast.Animations.FADE)).show();
                                }
                            }
                        }

                        @Override
                        public void onWeatherError(WeatherLibException wle) {
                            SuperToast.create(getApplicationContext(), "Oops, weather is not available",
                                    SuperToast.Duration.SHORT, Style.getStyle(Style.GREEN, SuperToast.Animations.FADE)).show();
                            Log.d("WL", "Weather Error - parsing data");
                        }

                        @Override
                        public void onConnectionError(Throwable t) {
                            SuperToast.create(getApplicationContext(), "Oops, weather is not available because of connection error",
                                    SuperToast.Duration.SHORT, Style.getStyle(Style.GREEN, SuperToast.Animations.FADE)).show();
                            Log.d("WL", "Connection error");
                        }
                    });
                    dialog.setCancelable(true);
                    dialog.dismiss();
                }
            }.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        SuperToast.create(getApplicationContext(), "Get Location Failed", SuperToast.Duration.LONG, Style.getStyle(Style.ORANGE, SuperToast.Animations.FLYIN)).show();
        dialog.dismiss();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            latitude = mLastLocation.getLatitude();
            longtitude = mLastLocation.getLongitude();
            try {
                Geocoder geocoder = new Geocoder(Naptime.this, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(latitude, longtitude, 1);
                cityName = addresses.get(0).getLocality();
                stateName = addresses.get(0).getAdminArea();
                countryName = addresses.get(0).getCountryName();
            } catch (Exception e) {
            }

            getcurrweather();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        dialog.dismiss();
        SuperToast.create(getApplicationContext(), "Get Location Failed", SuperToast.Duration.LONG, Style.getStyle(Style.ORANGE, SuperToast.Animations.FLYIN)).show();
    }

    /**************************************************************************************************************************
     * Weather Items complete
     * @param
     **************************************************************************************************************************/

    /***************************************************************
     * *******Get Background from timodie.github.io/frag1background.txt
     * I use a new thread because it is not good to open
     * sockets on the main thread
     * I should be able to change all backgrounds by having multiple lines of url at github
     * Then I will split the string that reads them into an array of strings
     * Then I'll set the pages to the appropriate index
     * Question: How will I know what size to make the buffer? I may not have to worry about it
     * since java's garbage collector will free up unused memory
     **************************************************************/
    public Thread checkUpdate = new Thread() {
        public void run() {
            try {
                URL updateURL = new URL("http://timodie.github.io/frag1background.txt");
                URLConnection conn = updateURL.openConnection();

                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                //Since bytearraybuffer is deprecated in api >22
                //Im using a local class instead
                byteArrayBuffer = new ByteArrayBuffer(50);
                //byte [] barray = new byte[(int) 50];

                int current = 0;
                while ((current = bis.read()) != -1) {
                    byteArrayBuffer.append((byte) current);

                }

                /* Convert the Bytes read to a String. */

                final String s = new String(byteArrayBuffer.toByteArray());
                pageOne = s;
                String[] pages = pageOne.split("%");
                page1 = pages[0];
                page2 = pages[1];
                page3 = pages[2];
                page4 = pages[3];
                page5 = pages[4];

                /* Get current Version Number */
                String curVersion = getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionName;
                SuperToast.create(getApplicationContext(), "curVersion is :" + curVersion,
                        SuperToast.Duration.SHORT, Style.getStyle(Style.GREEN, SuperToast.Animations.FADE)).show();

                //Setting background
                //These conditions should only fail if there's no internet connection
                //Or for some reason we cannot connect to timodie.github.io/frag1background.txt
                if (!(page1.equals("") && page2.equals("") && page3.equals("") &&page4.equals("")&&page5.equals("")) ) {
                    localurl =page1;
                    localurl2=page2;
                    localurl3=page3;
                    localurl4=page4;
                    localurl5=page5;
                }
                initializeHeader();



            } catch (Exception e) {
            }
        }
    };

    /*********************************************************************************************
     * CheckUpdate is a try-catch which may sometimes fail
     * Should this happen,mViewPager will be Null and throw NPE
     * Hence, if mViewPager is null,this method will be called to initialize the backgrounds
     ********************************************************************************************/
    public void initializeHeader() {
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return new Fragment();
            }

            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 5) {
                    case 0:
                        return "loooongtext";
                    case 1:
                        return "loooongtext";
                    case 2:
                        return "loooongtext";
                    case 3:
                        return "loooongtext";
                    case 4:
                        return "loooongtext";
                }
                return "";
            }
        });


        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.instabug_annotation_color_blue,
                                localurl);

                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.instabug_annotation_color_blue,
                                localurl2);
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                localurl3);
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.instabug_annotation_color_gray,
                                localurl4);
                    case 4:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                localurl5);
                }
                //execute others actions if needed (ex : modify your header logo)
                return null;
            }
        });


        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

/******************************************************************************************
 * New Header part END
 *****************************************************************************************/
    }

    public void updateAlert() {
        updateDialog = new MaterialDialog(Naptime.this);
        updateDialog.setTitle("Naptime Has A New Release")
                .setMessage("Go Check it out!")
                .setPositiveButton("Go now", new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        // To count with Play market backstack, After pressing back button,
                        // to taken back to our application, we need to add following flags to intent.
                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

                        try {
                            startActivity(goToMarket);
                        } catch (
                                ActivityNotFoundException e
                                ) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));

                        }
                        updateDialog.dismiss();
                    }
                });
        updateDialog.setNegativeButton("Go Later", new OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialog.dismiss();
            }
        });
    }
}