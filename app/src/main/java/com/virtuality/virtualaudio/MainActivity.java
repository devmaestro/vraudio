package com.virtuality.virtualaudio;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;


public class MainActivity extends Activity {

    Button playVirtualBarberAudio, playInterrogationChamberAudio;
    SeekBar controlVirtualBarberAudio, controlInterrogationChamberAudio;
    boolean virtualBarberStarted = false, interrogationChamberStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playVirtualBarberAudio = (Button) findViewById(R.id.play_audio001);
        controlVirtualBarberAudio = (SeekBar) findViewById(R.id.seekbar_audio001);
        playInterrogationChamberAudio = (Button) findViewById(R.id.play_audio002);
        controlInterrogationChamberAudio = (SeekBar) findViewById(R.id.seekbar_audio002);
        final MediaPlayer virtualBarberAudioPlayer = MediaPlayer.create(MainActivity.this, R.raw.audio001);
        final MediaPlayer interrogationRoomAudioPlayer = MediaPlayer.create(MainActivity.this, R.raw.audio002);
        controlVirtualBarberAudio.setMax(virtualBarberAudioPlayer.getDuration());
        controlInterrogationChamberAudio.setMax(interrogationRoomAudioPlayer.getDuration());
        playVirtualBarberAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(virtualBarberStarted) {
                    virtualBarberAudioPlayer.stop();
                    try {
                        virtualBarberAudioPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    virtualBarberStarted = false;
                } else{
                    virtualBarberAudioPlayer.start();
                    virtualBarberAudioPlayer.setLooping(true);
                    virtualBarberStarted = true;
                    final Handler handlerVirtualBarberAudio = new Handler();
                    Runnable runnableVirtualBarberAudio = new Runnable() {
                        @Override
                        public void run() {
                            if(virtualBarberAudioPlayer != null) {
                                int currentAudioPosition = virtualBarberAudioPlayer.getCurrentPosition();
                                controlVirtualBarberAudio.setProgress(currentAudioPosition);
                            }
                            handlerVirtualBarberAudio.postDelayed(this, 1000);
                        }
                    };
                    runnableVirtualBarberAudio.run();
                }

            }
        });
        controlVirtualBarberAudio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(virtualBarberAudioPlayer!=null) {
                    virtualBarberAudioPlayer.seekTo(seekBar.getProgress());
                }
            }
        });
        playInterrogationChamberAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(interrogationChamberStarted) {
                    interrogationRoomAudioPlayer.stop();
                    try {
                        interrogationRoomAudioPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    interrogationChamberStarted = false;
                } else{
                    interrogationRoomAudioPlayer.start();
                    interrogationRoomAudioPlayer.setLooping(true);
                    interrogationChamberStarted = true;
                    final Handler handlerInterrogationRoomAudio = new Handler();
                    Runnable runnableInterrogationRoomAudio = new Runnable() {
                        @Override
                        public void run() {
                            if(interrogationRoomAudioPlayer != null) {
                                int currentAudioPosition = interrogationRoomAudioPlayer.getCurrentPosition();
                                controlInterrogationChamberAudio.setProgress(currentAudioPosition);
                            }
                            handlerInterrogationRoomAudio.postDelayed(this, 1000);
                        }
                    };
                    runnableInterrogationRoomAudio.run();
                }

            }
        });
        controlInterrogationChamberAudio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(interrogationRoomAudioPlayer!=null) {
                    interrogationRoomAudioPlayer.seekTo(seekBar.getProgress());
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
