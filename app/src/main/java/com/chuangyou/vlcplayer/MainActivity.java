package com.chuangyou.vlcplayer;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.util.ArrayList;

public class MainActivity extends Activity implements SurfaceHolder.Callback {

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        //设置main
        setContentView(R.layout.activity_main);
        //设置video
        SurfaceView surfaceView = findViewById(R.id.video);
        //获取holder
        SurfaceHolder holder = surfaceView.getHolder();
        //设置
        holder.setKeepScreenOn(true);
        //添加回调
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        ArrayList<String> options = new ArrayList<>();
        //设置
        options.add("--aout=opensles");
        //audio
        options.add("--audio-time-stretch");
        //verbosity
        options.add("-vvv");
        LibVLC libVLC = new LibVLC(getBaseContext(), options);
        //创建media
        final Media media = new Media(libVLC, Uri.parse("rtmp://58.200.131.2:1935/livetv/hunantv"));
        //创建player
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        //设置suface
        mediaPlayer.getVLCVout().setVideoSurface(holder.getSurface(), holder);
        //添加
        mediaPlayer.getVLCVout().attachViews();
        //设置大小
        mediaPlayer.getVLCVout().setWindowSize(holder.getSurfaceFrame().width(), holder.getSurfaceFrame().height());
        //final Media media = new Media(libVLC, Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"));
        mediaPlayer.setMedia(media);
        //设置
        mediaPlayer.play();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
