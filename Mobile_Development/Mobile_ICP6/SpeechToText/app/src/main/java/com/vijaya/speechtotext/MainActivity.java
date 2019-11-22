package com.vijaya.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    private TextToSpeech tts;
    private ArrayList<String> questions;
    private boolean ready = false;
    private boolean allowed = false;
    private SharedPreferences preferences;
    private  SharedPreferences.Editor editor;
    private static final String PREFS = "prefs";
    private static final String NAME = "name";
    private static final String AGE = "age";
    private static final String AS_NAME = "as_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences(PREFS, 0);
        editor = preferences.edit();
        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() { // When user click on mic button

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
        AIQuestionsandAnswer();

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i == TextToSpeech.SUCCESS){
                    int result = tts.setLanguage(Locale.US);
                    ready = true;
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS", "This language is not supported");
                    }
                    speak("Hello");
                }
                else{
                    ready=false;
                    Log.e("TTs", "Initialization Failed");
                }
            }
        });
    }
    private void AIQuestionsandAnswer(){
        questions = new ArrayList<>();
        questions.clear();
        questions.add("Hello, What is your name");
        questions.add("How are you today, master ");
        questions.add("I can understand, Please tell your symptoms in short");

    }

    private void speak(String text){
        if(ready){
            HashMap<String, String> hashMap = new HashMap<String, String>();

            hashMap.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_NOTIFICATION));
            tts.speak(text, TextToSpeech.QUEUE_ADD, hashMap);
        }

    }

    private void recognition(String text){
        Log.e("Speech", ""+ text);

        //Creating an array which contains the words of the answer
        String[] speech = text.split(" ");

//        //Last word of user's name
//        String name = speech[speech.length-1];
//
//        // put name in local storage and save changes
//        editor.putString(NAME, name).apply();
        if(text.contains("hello")){
            speak(questions.get(0));
        }
        if(text.contains("my name is")){ //speech.length ==1
            String name = speech[speech.length-1];
            Log.e("Your name", "" + name);
            editor.putString(NAME, name).apply();
            //make the app tell your name
            speak("Your name is "+ preferences.getString(NAME, null));
        }
        if(text.contains("my name is " + preferences.getString(NAME, null))){ //speech.length == 1
            speak(questions.get(1) + preferences.getString(NAME,null));
        }
        if(text.contains("what is my name")){
            speak("Your name is "+ preferences.getString(NAME, null));
        }

        if(text.contains("feel good") || text.contains("feel well") || text.contains("feeling good")|| text.contains("what should I do")){

            speak(questions.get(2));
        }
        if(text.contains("Hot Head") || text.contains("headache") || text.contains("chest pain")
                ||text.contains("I'm cold") || text.contains("appetite")){
            speak("I think you have a fever");
        }
        if(text.contains("I'm good") || text.contains("I'm fine")){
            speak("Awesome, how can I help you today?");
        }
        if(text.contains("what medicine")|| text.contains("die")){
            speak("I think you have a fever Don't worry you can take this medicine");
        }
        if(text.contains("I don't understand") || text.contains("what do you mean")){
            speak("Well, Tough Sucker, ha ha ha");
        }
        if(text.contains("what time is it")){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm"); // dd/mm/yyyy
            Date now = new Date();
            String[] strDate = simpleDateFormat.format(now).split(":");
            if(strDate[1].contains("00")){
                strDate[1] = "o'clock";
            }
            speak("The time is " + simpleDateFormat.format(now));
        }
        if(text.contains("thank you, my medical assistant") || text.contains("thank you")){
            speak("Thank you too "+ preferences.getString(NAME, null) + "Take Care and have a nice day sir");
        }
        if(text.contains("what's your name")||text.contains("what is your name")){
            String ai_name = preferences.getString(AS_NAME, "");
            if(ai_name.equals("")){
                speak("How do you want to call me? Sir");
            }
            else{
                speak("My name is "+ ai_name);
            }
        }
        if(text.contains("call you") || text.contains("name you")){
            String name = speech[speech.length-1];
            editor.putString(AS_NAME, name).apply();
            speak("I like it, thank you Sir "+ preferences.getString(NAME,null));
        }

//        else{
//            speak("Sorry your Question or Answer is not in our database i apologize sir");
//        }

    }


    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT); // Call appropriate Intent
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Sorry your device not supported", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String inSpeech = result.get(0);
                    mVoiceInputTv.setText(inSpeech); // mVoiceInputTv.setText(result.get(0))
                    recognition(inSpeech);
                }
                break;
            }

        }
    }



    public void onDestroy(){
        if (tts != null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}