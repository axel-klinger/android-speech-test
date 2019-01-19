package com.example.myspeechtest;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private TextView txtSpeechInput;
    private TextView txtSpeechOutput;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this, this);
        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        txtSpeechOutput = (TextView) findViewById(R.id.txtSpeechOutput);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        // hide the action bar
        //getActionBar().hide();

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String input = result.get(0);
                    txtSpeechInput.setText(input);
                    tts.setLanguage(Locale.GERMAN);
                    // speak out
                    String output = answer(input);
                    String coloredOutput = color(output);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        txtSpeechOutput.setText(Html.fromHtml(coloredOutput,  Html.FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
                    } else {
                        txtSpeechOutput.setText(Html.fromHtml(coloredOutput), TextView.BufferType.SPANNABLE);
                    }
//                    txtSpeechOutput.setText(output);
                    tts.speak(output, TextToSpeech.QUEUE_FLUSH, null);
                }
                break;
            }

        }
    }

// === Text Logic ===
    private String answer(String input) {
        String answer = input;
        if (answer.startsWith("ich")) {
            answer = answer.replaceAll("ich", "Du");
        } else {
            answer = "Ich " + answer;
        }
        answer = answer.replaceAll("mir", "DIR");
        answer = answer.replaceAll("mich", "DICH");
        answer = answer.replaceAll("mein", "DEIN");  // inkl. meine, meines, meiner, meinem, meinen
        answer = answer.replaceAll("dir", "MIR");
        answer = answer.replaceAll("dich", "MICH");
        answer = answer.replaceAll("dein", "MEIN");  // inkl. deine, deines, deiner, deinem, deinen

        answer = answer.replaceAll("MIR", "mir");
        answer = answer.replaceAll("MICH", "mich");
        answer = answer.replaceAll("MEIN", "mein");
        answer = answer.replaceAll("DIR", "dir");
        answer = answer.replaceAll("DICH", "dich");
        answer = answer.replaceAll("DEIN", "dein");
        return answer;
    }

    private String color(String input) {
        String answer = input;
        answer = answer.replaceAll("Ich", "<font color='red'>Ich</font>");
        answer = answer.replaceAll("mich", "<font color='red'>mich</font>");
        answer = answer.replaceAll("mir", "<font color='red'>mir</font>");
        answer = answer.replaceAll("mein", "<font color='red'>mein</font>");
        answer = answer.replaceAll("Du", "<font color='red'>Du</font>");
        answer = answer.replaceAll("dir", "<font color='red'>dir</font>");
        answer = answer.replaceAll("dich", "<font color='red'>dich</font>");
        answer = answer.replaceAll("dein", "<font color='red'>dein</font>");
        return answer;
    }

// === Text Logic ===

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }
}