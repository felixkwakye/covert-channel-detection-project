package com.felixk.ccsender;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button start, stop;
    TextView update;
    EditText editText;
    AudioManager am;
    char zero = '0';
    char one = '1';
    int level1 = 0;
    int level2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.btnStart);
        stop = (Button) findViewById(R.id.btnStop);
        update = (TextView) findViewById(R.id.textView2);
        editText = (EditText) findViewById(R.id.editText1);

        //Reads and encodes user input from the text space (editText)
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = String.valueOf(editText.getText());
                encode(text);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    //converts String text to binary
    public void encode(String text) {
        String s = text;
        byte[] bytes = s.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append("");
        }

        transmit(String.valueOf(binary));
    }

    //transmit method sends binary over the volume channel
    public void transmit(final String text) {
        final Timer mytimer = new Timer();

        TimerTask ts = new TimerTask() {
            int i = 0;

            @Override
            public void run() {

                if (text.charAt(i) == zero) {
                    decreaseVolume();
                    System.out.println("position " + i + "-" + text.charAt(i));
                }
                if (text.charAt(i) == one) {
                    increaseVolume();
                   System.out.println("position " + i + "+" + text.charAt(i));
                }
                if (i < text.length() - 1) {
                    i++;
                } else {
                    mytimer.cancel();
                }
            }
        };
        mytimer.scheduleAtFixedRate(ts, 1000, 550);
    }

    //decreaseVolume method decreases device volume
    @SuppressLint("NewApi")
    public void decreaseVolume() {
        int n = 1;
        while (level1 == n && n < 4) {
            n = ThreadLocalRandom.current().nextInt(1, 4);

        }
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(AudioManager.STREAM_RING, n, 0);
        level1 = n;
    }

    //increaseVolume method increased device volume
    @SuppressLint("NewApi")
    public void increaseVolume() {
        int n = 5;
        while (level2 == n && n > 4) {
            n = ThreadLocalRandom.current().nextInt(5, 7 + 1);
        }
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(AudioManager.STREAM_RING, n, 0);
        level2 = n;
    }
}
