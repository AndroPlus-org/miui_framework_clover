// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech;

import android.content.*;
import android.content.pm.*;
import android.os.Bundle;

public class RecognizerIntent
{

    private RecognizerIntent()
    {
    }

    public static final Intent getVoiceDetailsIntent(Context context)
    {
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        for(context = context.getPackageManager().resolveActivity(intent, 128); context == null || ((ResolveInfo) (context)).activityInfo == null || ((ResolveInfo) (context)).activityInfo.metaData == null;)
            return null;

        String s = ((ResolveInfo) (context)).activityInfo.metaData.getString("android.speech.DETAILS");
        if(s == null)
        {
            return null;
        } else
        {
            Intent intent1 = new Intent("android.speech.action.GET_LANGUAGE_DETAILS");
            intent1.setComponent(new ComponentName(((ResolveInfo) (context)).activityInfo.packageName, s));
            return intent1;
        }
    }

    public static final String ACTION_GET_LANGUAGE_DETAILS = "android.speech.action.GET_LANGUAGE_DETAILS";
    public static final String ACTION_RECOGNIZE_SPEECH = "android.speech.action.RECOGNIZE_SPEECH";
    public static final String ACTION_VOICE_SEARCH_HANDS_FREE = "android.speech.action.VOICE_SEARCH_HANDS_FREE";
    public static final String ACTION_WEB_SEARCH = "android.speech.action.WEB_SEARCH";
    public static final String DETAILS_META_DATA = "android.speech.DETAILS";
    public static final String EXTRA_CALLING_PACKAGE = "calling_package";
    public static final String EXTRA_CONFIDENCE_SCORES = "android.speech.extra.CONFIDENCE_SCORES";
    public static final String EXTRA_LANGUAGE = "android.speech.extra.LANGUAGE";
    public static final String EXTRA_LANGUAGE_MODEL = "android.speech.extra.LANGUAGE_MODEL";
    public static final String EXTRA_LANGUAGE_PREFERENCE = "android.speech.extra.LANGUAGE_PREFERENCE";
    public static final String EXTRA_MAX_RESULTS = "android.speech.extra.MAX_RESULTS";
    public static final String EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE = "android.speech.extra.ONLY_RETURN_LANGUAGE_PREFERENCE";
    public static final String EXTRA_ORIGIN = "android.speech.extra.ORIGIN";
    public static final String EXTRA_PARTIAL_RESULTS = "android.speech.extra.PARTIAL_RESULTS";
    public static final String EXTRA_PREFER_OFFLINE = "android.speech.extra.PREFER_OFFLINE";
    public static final String EXTRA_PROMPT = "android.speech.extra.PROMPT";
    public static final String EXTRA_RESULTS = "android.speech.extra.RESULTS";
    public static final String EXTRA_RESULTS_PENDINGINTENT = "android.speech.extra.RESULTS_PENDINGINTENT";
    public static final String EXTRA_RESULTS_PENDINGINTENT_BUNDLE = "android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE";
    public static final String EXTRA_SECURE = "android.speech.extras.EXTRA_SECURE";
    public static final String EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS = "android.speech.extras.SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS";
    public static final String EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS = "android.speech.extras.SPEECH_INPUT_MINIMUM_LENGTH_MILLIS";
    public static final String EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS = "android.speech.extras.SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS";
    public static final String EXTRA_SUPPORTED_LANGUAGES = "android.speech.extra.SUPPORTED_LANGUAGES";
    public static final String EXTRA_WEB_SEARCH_ONLY = "android.speech.extra.WEB_SEARCH_ONLY";
    public static final String LANGUAGE_MODEL_FREE_FORM = "free_form";
    public static final String LANGUAGE_MODEL_WEB_SEARCH = "web_search";
    public static final int RESULT_AUDIO_ERROR = 5;
    public static final int RESULT_CLIENT_ERROR = 2;
    public static final int RESULT_NETWORK_ERROR = 4;
    public static final int RESULT_NO_MATCH = 1;
    public static final int RESULT_SERVER_ERROR = 3;
}
