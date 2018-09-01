// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import android.os.Bundle;

public final class SynthesisRequest
{

    public SynthesisRequest(CharSequence charsequence, Bundle bundle)
    {
        mText = charsequence;
        mParams = new Bundle(bundle);
    }

    public SynthesisRequest(String s, Bundle bundle)
    {
        mText = s;
        mParams = new Bundle(bundle);
    }

    public int getCallerUid()
    {
        return mCallerUid;
    }

    public CharSequence getCharSequenceText()
    {
        return mText;
    }

    public String getCountry()
    {
        return mCountry;
    }

    public String getLanguage()
    {
        return mLanguage;
    }

    public Bundle getParams()
    {
        return mParams;
    }

    public int getPitch()
    {
        return mPitch;
    }

    public int getSpeechRate()
    {
        return mSpeechRate;
    }

    public String getText()
    {
        return mText.toString();
    }

    public String getVariant()
    {
        return mVariant;
    }

    public String getVoiceName()
    {
        return mVoiceName;
    }

    void setCallerUid(int i)
    {
        mCallerUid = i;
    }

    void setLanguage(String s, String s1, String s2)
    {
        mLanguage = s;
        mCountry = s1;
        mVariant = s2;
    }

    void setPitch(int i)
    {
        mPitch = i;
    }

    void setSpeechRate(int i)
    {
        mSpeechRate = i;
    }

    void setVoiceName(String s)
    {
        mVoiceName = s;
    }

    private int mCallerUid;
    private String mCountry;
    private String mLanguage;
    private final Bundle mParams;
    private int mPitch;
    private int mSpeechRate;
    private final CharSequence mText;
    private String mVariant;
    private String mVoiceName;
}
