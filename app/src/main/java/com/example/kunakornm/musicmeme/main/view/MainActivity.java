package com.example.kunakornm.musicmeme.main.view;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.kunakornm.musicmeme.R;
import com.example.kunakornm.musicmeme.main.Constants;
import com.example.kunakornm.musicmeme.main.presenter.MainPresenterImpl;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends Activity implements MainActivityInterface {
    MainPresenterImpl mainPresenter;
    ImageView img_lol, img_tt, img_sweet, img_devil, img_spinner, img_devil_stop, img_sweet_stop;
    Context context;
    boolean flag = true;
    Boolean flag_sweet = true, flag_thunglife = true, flag_crow = true, flag_lol = true;
    private MediaPlayer mp;
    LinearLayout li_main;
    String TAG = "FUSE";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        init();
    }

    private void initPresenter() {
        mainPresenter = new MainPresenterImpl(this);
    }

    private void init() {
        mainPresenter.start();
        img_lol = (ImageView) findViewById(R.id.img_lol);
        img_tt = (ImageView) findViewById(R.id.img_tt);
        img_sweet = (ImageView) findViewById(R.id.img_sweet);
        img_devil = (ImageView) findViewById(R.id.img_devil);
        img_spinner = (ImageView) findViewById(R.id.img_spinner);
        img_devil_stop = (ImageView) findViewById(R.id.img_devil_stop);
        img_sweet_stop = (ImageView) findViewById(R.id.img_sweet_stop);
        li_main = (LinearLayout) findViewById(R.id.li_main);

        img_lol.setOnClickListener(OnclickPlayed);
        img_tt.setOnClickListener(OnclickPlayed);
        img_sweet.setOnClickListener(OnclickPlayed);
        img_devil.setOnClickListener(OnclickPlayed);

    }

    public View.OnClickListener OnclickPlayed = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.img_lol:
                    soundManagement(Constants.LOL);
                    break;
                case R.id.img_tt:
                    soundManagement(Constants.TT);
                    break;
                case R.id.img_sweet:
                    soundManagement(Constants.SWEET_SMILE);
                    break;
                case R.id.img_devil:
                    soundManagement(Constants.DEVIL_SMILE);
                    break;
            }
        }
    };

    public void soundManagement(String type) {
        switch (type) {
            case Constants.LOL:
                TestRandomString();
                if (flag_lol) {
                    mainPresenter.effectStopper(mp);
                    mp = MediaPlayer.create(this, R.raw.sweet_sound);
                    spinner(1300);
                    manageBlinkEffect(1300, Color.WHITE, Color.RED, Color.WHITE, Animation.INFINITE);
                    mainPresenter.effectStarter(mp);
                    flag_lol = false;
                } else {
                    img_sweet_stop.setVisibility(View.GONE);
                    mainPresenter.effectStopper(mp);
                    spinner(0);
                    manageBlinkEffect(0, Color.WHITE, Color.WHITE, Color.WHITE, Animation.ABSOLUTE);
                    flag_lol = true;
                }
                break;
            case Constants.TT:
                if (flag_crow) {
                    mainPresenter.effectStopper(mp);
                    mp = MediaPlayer.create(this, R.raw.crow);
                    spinner(2000);
                    mainPresenter.effectStarter(mp);
                    flag_crow = false;
                } else {
                    img_sweet_stop.setVisibility(View.GONE);
                    mainPresenter.effectStopper(mp);
                    spinner(0);
                    manageBlinkEffect(0, Color.WHITE, Color.WHITE, Color.WHITE, Animation.ABSOLUTE);
                    flag_crow = true;
                }
                break;
            case Constants.SWEET_SMILE:
                if (flag_sweet) {
                    img_sweet_stop.setVisibility(View.VISIBLE);
                    mainPresenter.effectStopper(mp);
                    mp = MediaPlayer.create(this, R.raw.sweet_sound);
                    spinner(1300);
                    manageBlinkEffect(1300, Color.WHITE, Color.RED, Color.WHITE, Animation.INFINITE);
                    mainPresenter.effectStarter(mp);
                    flag_sweet = false;
                } else {
                    img_sweet_stop.setVisibility(View.GONE);
                    mainPresenter.effectStopper(mp);
                    flag_sweet = true;
                    spinner(0);
                    manageBlinkEffect(0, Color.WHITE, Color.WHITE, Color.WHITE, Animation.ABSOLUTE);
                }
                break;
            case Constants.DEVIL_SMILE:
                if (flag_thunglife) {
                    img_devil_stop.setVisibility(View.VISIBLE);
                    mainPresenter.effectStopper(mp);
                    mp = MediaPlayer.create(this, R.raw.thunglife);
                    spinner(1000);
                    manageBlinkEffect(1000, Color.YELLOW, Color.RED, Color.GREEN, Animation.INFINITE);
                    mainPresenter.effectStarter(mp);
                    flag_thunglife = false;
                } else {
                    img_devil_stop.setVisibility(View.GONE);
                    mainPresenter.effectStopper(mp);
                    flag_thunglife = true;
                    spinner(0);
                    manageBlinkEffect(0, Color.WHITE, Color.BLACK, Color.WHITE, Animation.ABSOLUTE);
                }
                break;
        }
    }

    public void TestRandomString(){
        Random rng = new Random();
        Set<Integer> generated = new LinkedHashSet<Integer>();
        Log.d(TAG, "generated: "+generated);
        while (generated.size() < 10)
        {
            Integer next = rng.nextInt(10) + 1;
            Log.d(TAG, "TestRandomString: "+next);
            generated.add(next);
            Log.d(TAG, "generated222: "+generated);
        }

    }

    public void spinner(int speed) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(speed);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        findViewById(R.id.img_spinner).startAnimation(rotateAnimation);

    }

    private void manageBlinkEffect(int blink, int c1, int c2, int c3, int animation) {
        ObjectAnimator anim = ObjectAnimator.ofInt(li_main,
                "backgroundColor", c1, c2, c3);
        anim.setDuration(blink);
        anim.setEvaluator(new ArgbEvaluator());
        //  anim.setRepeatMode(Animation.REVERSE);
        //anim.setRepeatCount(Animation.INFINITE);
        anim.setRepeatCount(animation);
        anim.start();
    }
}
