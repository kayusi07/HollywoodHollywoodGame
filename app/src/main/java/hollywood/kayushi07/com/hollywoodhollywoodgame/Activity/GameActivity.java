package hollywood.kayushi07.com.hollywoodhollywoodgame.Activity;

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
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import hollywood.kayushi07.com.hollywoodhollywoodgame.MovieData.ExListData2011_2015;
import hollywood.kayushi07.com.hollywoodhollywoodgame.R;
import hollywood.kayushi07.com.hollywoodhollywoodgame.Receiver.NetworkStateReceiver;

/**
 * Created by Ayushi on 12-01-2018.
 */

public class GameActivity extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener {

    private NetworkStateReceiver networkStateReceiver;

    Button b,o1,l1,l2,y,w,o2,o3,d;
    TextView game_txt;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    int count, randomMovie, bollywood=0, score=10;
    String f_movie, lowerMovie;
    Animation animBounce;
    private AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_page);

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


        expandableListDetail = ExListData2011_2015.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        count = 711;

        Random r = new Random();
        randomMovie = r.nextInt(count);

        String expandedListText = (String) expandableListDetail.get(this.expandableListTitle.get(0))
                .get(randomMovie);


        char[] charArrayM;
        //Converting string variable to byte array.
        lowerMovie = expandedListText.toUpperCase();
        charArrayM = lowerMovie.toCharArray();

        for (int i = 0; i < charArrayM.length; i++) {

            if (!(charArrayM[i] == 'A' || charArrayM[i] == 'E' || charArrayM[i] == 'I' || charArrayM[i] == 'O' || charArrayM[i] == 'U' || charArrayM[i] == ' ' || charArrayM[i] == '-' || charArrayM[i] == '.')) {

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
}
