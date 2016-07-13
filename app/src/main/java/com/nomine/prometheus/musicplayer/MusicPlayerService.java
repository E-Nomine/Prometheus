package com.nomine.prometheus.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;

import com.nomine.prometheus.R;
import com.nomine.prometheus.rxbus.MusicEvent;
import com.nomine.prometheus.rxbus.RxBus;

import java.io.IOException;

import rx.Subscription;
import rx.functions.Action1;

public class MusicPlayerService extends Service {
    private MediaPlayer mMediaPlayer;
    private String MUSIC_URL;
    private Subscription rxSubscription;
    FloatingActionButton floatingActionButton;
    public MusicPlayerService() {}

    @Override
    public void onCreate() {
        super.onCreate();
        floatingActionButton = null;
        mMediaPlayer = new MediaPlayer();
        rxSubscription = RxBus.getDefault()
                .toObserverable(MusicEvent.class)
                .subscribe(
                        new Action1<MusicEvent>() {
                               @Override
                               public void call(MusicEvent userEvent) {
                                   String name = userEvent.getEvent();
                                   if(name.equals("PLAY")){
                                       if(floatingActionButton!=null){
                                           floatingActionButton.setSelected(false);
                                       }
                                       floatingActionButton = userEvent.getFloatingActionButton();
                                       String url = userEvent.getUrl();
                                       playMusic(url);
                                   }
                                   if(name.equals("PAUSE")){
                                       pauseMusic();
                                   }
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                // TODO: 处理异常
                            }
                        }
                );

    }

    public void playMusic(String url){

        System.out.println(url);
        mMediaPlayer.reset();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mMediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.prepareAsync();
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
    }

    public void pauseMusic(){
        mMediaPlayer.reset();
    }
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
