package com.example.kunakornm.musicmeme.main.presenter;

import android.media.MediaPlayer;

public interface MainPresenterInterface {
    void start();
    void effectStarter(MediaPlayer mediaPlayer);
    void effectStopper(MediaPlayer mediaPlayer);
}
