package hollywood.kayushi07.com.hollywoodhollywoodgame.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import hollywood.kayushi07.com.hollywoodhollywoodgame.MovieDataPump.Level01;
import hollywood.kayushi07.com.hollywoodhollywoodgame.MovieDataPump.Level02;
import hollywood.kayushi07.com.hollywoodhollywoodgame.MovieDataPump.Level03;
import hollywood.kayushi07.com.hollywoodhollywoodgame.MovieDataPump.Level04;
import hollywood.kayushi07.com.hollywoodhollywoodgame.MovieDataPump.Level05;
import hollywood.kayushi07.com.hollywoodhollywoodgame.MovieDataPump.Level06;
import hollywood.kayushi07.com.hollywoodhollywoodgame.MovieDataPump.Level07;
import hollywood.kayushi07.com.hollywoodhollywoodgame.MovieDataPump.Level08;
import hollywood.kayushi07.com.hollywoodhollywoodgame.MovieDataPump.Level09;
import hollywood.kayushi07.com.hollywoodhollywoodgame.MovieDataPump.Level10;
import hollywood.kayushi07.com.hollywoodhollywoodgame.R;
import hollywood.kayushi07.com.hollywoodhollywoodgame.Receiver.NetworkStateReceiver;

/**
 * Created by Ayushi on 12-01-2018.
 */

public class GameActivity extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener, RewardedVideoAdListener {

    private NetworkStateReceiver networkStateReceiver;

    Button b,o1,l1,l2,y,w,o2,o3,d;
    TextView game_txt;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    int count, randomMovie, bollywood=0, score=10;
    String f_movie, lowerMovie;
    Animation animBounce, animFade;
    private AdView mAdView;
    ImageButton b_hint;
    RewardedVideoAd mAd;
    ProgressDialog progressDoalog;
    int level;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_page);

//        Intent intent = getIntent();
        level = Dummy.getCurrent_level();//intent.getIntExtra("Level",1);
//        level++;

        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

        mAdView = (AdView) findViewById(R.id.adView);

        game_txt = (TextView) findViewById(R.id.movie_name);
        b = (Button) findViewById(R.id.B_btn);
        o1 = (Button) findViewById(R.id.O_btn);
        l1 = (Button) findViewById(R.id.L_btn);
        l2 = (Button) findViewById(R.id.L2_btn);
        y = (Button) findViewById(R.id.Y_btn);
        w = (Button) findViewById(R.id.W_btn);
        o2 = (Button) findViewById(R.id.O2_btn);
        o3 = (Button) findViewById(R.id.O3_btn);
        d = (Button) findViewById(R.id.D_btn);
        b_hint = (ImageButton) findViewById(R.id.b_hint);

        switch(level){
            case 1:
                expandableListDetail = Level01.getData();
                count = 432;
                break;
            case 2:
                expandableListDetail = Level02.getData();
                count = 268;
                break;
            case 3:
                expandableListDetail = Level03.getData();
                count = 870;
                break;
            case 4:
                expandableListDetail = Level04.getData();
                count = 612;
                break;
            case 5:
                expandableListDetail = Level05.getData();
                count = 886;
                break;
            case 6:
                expandableListDetail = Level06.getData();
                count = 652;
                break;
            case 7:
                expandableListDetail = Level07.getData();
                count = 660;
                break;
            case 8:
                expandableListDetail = Level08.getData();
                count = 812;
                break;
            case 9:
                expandableListDetail = Level09.getData();
                count = 948;
                break;
            case 10:
                expandableListDetail = Level10.getData();
                count = 626;
                break;



        }

//        expandableListDetail = ExListData2011_2015.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
//        count = 711;

        Random r = new Random();
        randomMovie = r.nextInt(count);

        String expandedListText = (String) expandableListDetail.get(this.expandableListTitle.get(0))
                .get(randomMovie);


        char[] charArrayM;
        //Converting string variable to byte array.
        lowerMovie = expandedListText.toUpperCase();
        charArrayM = lowerMovie.toCharArray();

        for (int i = 0; i < charArrayM.length; i++) {

            if (!(charArrayM[i] == 'A' || charArrayM[i] == 'E' || charArrayM[i] == 'I' || charArrayM[i] == 'O' || charArrayM[i] == 'U' || charArrayM[i] == ' ' || charArrayM[i] == '-'  || charArrayM[i] == ',' || charArrayM[i] == ':'|| charArrayM[i] == '&' || charArrayM[i] == '+'|| charArrayM[i] == '.'|| charArrayM[i] == '\'')) {

                charArrayM[i] = '_';
            }
        }

        f_movie = new String(charArrayM);

//        f_movie = f_movie.replaceAll(".(?!$)", "$0  ");
        game_txt.setText(f_movie);
        System.out.println("ACTUAL MOVIE : " + expandedListText);

        animBounce = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);
        game_txt.startAnimation(animBounce);

        b_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                progressDoalog = new ProgressDialog(GameActivity.this);
                progressDoalog.setIcon(R.drawable.hint);
                progressDoalog.setMessage("Its loading....");
                progressDoalog.setTitle("Watch a short video to get a hint...");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDoalog.show();


                mAd = MobileAds.getRewardedVideoAdInstance(GameActivity.this);
                mAd.setRewardedVideoAdListener(GameActivity.this);
                mAd.loadAd(getString(R.string.ad_rewarded),
                        new AdRequest.Builder()
                                .build());
            }
        });
//        LinearLayout ll = (LinearLayout) findViewById(R.id.word);
//        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        int numWords = 11;
//        String wordId;
//
//        for(int i=1;i<=numWords;i++)
//        {
//
//            // add edittext
//            EditText et = new EditText(this);
//            et.setLayoutParams(p);
//            et.setText("Text " + i);
//            et.setId(i);
//            ll.addView(et);
//        }
    }

    public void onClickBtn(View v) {

        animFade = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade);
        v.startAnimation(animFade);

        String current_movie = game_txt.getText().toString();
        v.setEnabled(false);
        CharSequence alphabet = v.getContentDescription();
//        String s = String.valueOf(v.getContentDescription());
        char acc = alphabet.charAt(0);
        char[] charArrayWord = current_movie.toCharArray();

        if (lowerMovie.contains(alphabet)) {

            for (int in = -1; (in = lowerMovie.indexOf(acc, in)) != -1; in++) {
                charArrayWord[in] = acc;

            }
            f_movie = new String(charArrayWord);
//              f_movie = f_movie.replaceAll(".(?!$)", "$0  ");
//            game_txt.setText(null);
            game_txt.setText(f_movie);
            game_txt.startAnimation(animBounce);

        }
        else
        {
            switch (bollywood) {
                case 0:
//                    b.setBackgroundColor(Color.parseColor("#FF0000"));
                    b.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_round_red_left));
                    bollywood=1;
                    score=9;
                    break;
                case 1:
                    o1.setBackgroundColor(Color.parseColor("#FF0000"));
                    bollywood=2;
                    score=8;
                    break;
                case 2:
                    l1.setBackgroundColor(Color.parseColor("#FF0000"));
                    bollywood=3;
                    score=7;
                    break;
                case 3:
                    l2.setBackgroundColor(Color.parseColor("#FF0000"));
                    bollywood=4;
                    score=6;
                    break;
                case 4:
                    y.setBackgroundColor(Color.parseColor("#FF0000"));
                    bollywood=5;
                    score=5;
                    break;
                case 5:
                    w.setBackgroundColor(Color.parseColor("#FF0000"));
                    bollywood=6;
                    score=4;
                    break;
                case 6:
                    o2.setBackgroundColor(Color.parseColor("#FF0000"));
                    bollywood=7;
                    score=3;
                    break;
                case 7:
                    o3.setBackgroundColor(Color.parseColor("#FF0000"));
                    bollywood=8;
                    score=2;
                    break;
                case 8:
//                    d.setBackgroundColor(Color.parseColor("#FF0000"));
                    d.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_round_right_red));
                    bollywood=0;
                    score=1;
                    Intent i = new Intent(this, PopupActivity.class);
                    i.putExtra("Level", level);
                    i.putExtra("Score",score);
                    startActivity(i);
                    finish();
                    break;

            }
        }
        if(!f_movie.contains("_"))
        {
            Intent i = new Intent(this, PopupActivity.class);
            i.putExtra("Score",score);
            i.putExtra("Level", level);

            startActivity(i);
            finish();

        }
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {

        networkStateReceiver.removeListener(this);
        this.unregisterReceiver(networkStateReceiver);

        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void networkAvailable() {
        mAdView.setVisibility(View.VISIBLE);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void networkUnavailable() {
        mAdView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        System.out.println("Rewarded Video Ad Loaded");
        progressDoalog.dismiss();
        try {
            if (mAd.isLoaded()) {
                mAd.show();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRewardedVideoAdOpened() {
        System.out.println("Rewarded Video Ad Opened");
    }

    @Override
    public void onRewardedVideoStarted() {
        System.out.println("Rewarded Video Started");
    }

    @Override
    public void onRewardedVideoAdClosed() {
        System.out.println("Rewarded Video Ad Closed");
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        System.out.println("on Rewarded");

        String current_movie = game_txt.getText().toString();
        char[] charArrayWord = current_movie.toCharArray();

        int indd = current_movie.indexOf('_');
        char findChar = lowerMovie.charAt(indd);
        System.out.println("on Rewarded char ::  " + findChar);


        for (int in = -1; (in = lowerMovie.indexOf(findChar, in)) != -1; in++) {
            charArrayWord[in] = findChar;

            System.out.println("on Rewarded ::  " + findChar);
        }
        f_movie = new String(charArrayWord);
        game_txt.setText(f_movie);
        game_txt.startAnimation(animBounce);


        if(!f_movie.contains("_"))
        {
            Intent i = new Intent(this, PopupActivity.class);
            i.putExtra("Level", level);

            i.putExtra("Score",score);
            startActivity(i);
            finish();

        }

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        System.out.println("Rewarded Video Ad Left Application");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        System.out.println("Rewarded Video Ad Failed To Load");


        progressDoalog.dismiss();

        try {
            progressDoalog = new ProgressDialog(GameActivity.this);
            progressDoalog.setMessage("Please connect to the Internet to watch video to get free hint. Try Again.");
            progressDoalog.setTitle("No Connection!");
            progressDoalog.setIcon(R.drawable.hint);
            progressDoalog.setCancelable(false);
            progressDoalog.setButton(DialogInterface.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            progressDoalog.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
