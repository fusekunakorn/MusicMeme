package com.example.kunakornm.musicmeme.main.presenter;

import android.media.MediaPlayer;

import com.example.kunakornm.musicmeme.R;
import com.example.kunakornm.musicmeme.main.view.MainActivityInterface;

public class MainPresenterImpl implements MainPresenterInterface {
    MainActivityInterface viewInterface;

    public  MainPresenterImpl(MainActivityInterface mainActivityInterface) {
        this.viewInterface = mainActivityInterface;
    }


    @Override
    public void start() {

    }

    @Override
    public void effectStarter(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }

    @Override
    public void effectStopper(MediaPlayer mediaPlayer) {
        try{
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }catch (Exception e){

        }

    }

}
