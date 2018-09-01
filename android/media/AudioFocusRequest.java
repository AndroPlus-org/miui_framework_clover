// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Handler;

// Referenced classes of package android.media:
//            AudioAttributes

public final class AudioFocusRequest
{
    public static final class Builder
    {

        public AudioFocusRequest build()
        {
            byte byte0 = 0;
            if((mDelayedFocus || mPausesOnDuck) && mFocusListener == null)
                throw new IllegalStateException("Can't use delayed focus or pause on duck without a listener");
            boolean flag;
            byte byte1;
            if(mDelayedFocus)
                flag = true;
            else
                flag = false;
            if(mPausesOnDuck)
                byte1 = 2;
            else
                byte1 = 0;
            if(mFocusLocked)
                byte0 = 4;
            return new AudioFocusRequest(mFocusListener, mListenerHandler, mAttr, mFocusGain, byte1 | (flag | false) | byte0, null);
        }

        public Builder setAcceptsDelayedFocusGain(boolean flag)
        {
            mDelayedFocus = flag;
            return this;
        }

        public Builder setAudioAttributes(AudioAttributes audioattributes)
        {
            if(audioattributes == null)
            {
                throw new NullPointerException("Illegal null AudioAttributes");
            } else
            {
                mAttr = audioattributes;
                return this;
            }
        }

        public Builder setFocusGain(int i)
        {
            if(!AudioFocusRequest.isValidFocusGain(i))
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Illegal audio focus gain type ").append(i).toString());
            } else
            {
                mFocusGain = i;
                return this;
            }
        }

        public Builder setLocksFocus(boolean flag)
        {
            mFocusLocked = flag;
            return this;
        }

        public Builder setOnAudioFocusChangeListener(AudioManager.OnAudioFocusChangeListener onaudiofocuschangelistener)
        {
            if(onaudiofocuschangelistener == null)
            {
                throw new NullPointerException("Illegal null focus listener");
            } else
            {
                mFocusListener = onaudiofocuschangelistener;
                mListenerHandler = null;
                return this;
            }
        }

        public Builder setOnAudioFocusChangeListener(AudioManager.OnAudioFocusChangeListener onaudiofocuschangelistener, Handler handler)
        {
            if(onaudiofocuschangelistener == null || handler == null)
            {
                throw new NullPointerException("Illegal null focus listener or handler");
            } else
            {
                mFocusListener = onaudiofocuschangelistener;
                mListenerHandler = handler;
                return this;
            }
        }

        Builder setOnAudioFocusChangeListenerInt(AudioManager.OnAudioFocusChangeListener onaudiofocuschangelistener, Handler handler)
        {
            mFocusListener = onaudiofocuschangelistener;
            mListenerHandler = handler;
            return this;
        }

        public Builder setWillPauseWhenDucked(boolean flag)
        {
            mPausesOnDuck = flag;
            return this;
        }

        private AudioAttributes mAttr;
        private boolean mDelayedFocus;
        private int mFocusGain;
        private AudioManager.OnAudioFocusChangeListener mFocusListener;
        private boolean mFocusLocked;
        private Handler mListenerHandler;
        private boolean mPausesOnDuck;

        public Builder(int i)
        {
            mAttr = AudioFocusRequest._2D_get0();
            mPausesOnDuck = false;
            mDelayedFocus = false;
            mFocusLocked = false;
            setFocusGain(i);
        }

        public Builder(AudioFocusRequest audiofocusrequest)
        {
            mAttr = AudioFocusRequest._2D_get0();
            mPausesOnDuck = false;
            mDelayedFocus = false;
            mFocusLocked = false;
            if(audiofocusrequest == null)
            {
                throw new IllegalArgumentException("Illegal null AudioFocusRequest");
            } else
            {
                mAttr = AudioFocusRequest._2D_get1(audiofocusrequest);
                mFocusListener = AudioFocusRequest._2D_get3(audiofocusrequest);
                mListenerHandler = AudioFocusRequest._2D_get4(audiofocusrequest);
                mFocusGain = AudioFocusRequest._2D_get2(audiofocusrequest);
                mPausesOnDuck = audiofocusrequest.willPauseWhenDucked();
                mDelayedFocus = audiofocusrequest.acceptsDelayedFocusGain();
                return;
            }
        }
    }


    static AudioAttributes _2D_get0()
    {
        return FOCUS_DEFAULT_ATTR;
    }

    static AudioAttributes _2D_get1(AudioFocusRequest audiofocusrequest)
    {
        return audiofocusrequest.mAttr;
    }

    static int _2D_get2(AudioFocusRequest audiofocusrequest)
    {
        return audiofocusrequest.mFocusGain;
    }

    static AudioManager.OnAudioFocusChangeListener _2D_get3(AudioFocusRequest audiofocusrequest)
    {
        return audiofocusrequest.mFocusListener;
    }

    static Handler _2D_get4(AudioFocusRequest audiofocusrequest)
    {
        return audiofocusrequest.mListenerHandler;
    }

    private AudioFocusRequest(AudioManager.OnAudioFocusChangeListener onaudiofocuschangelistener, Handler handler, AudioAttributes audioattributes, int i, int j)
    {
        mFocusListener = onaudiofocuschangelistener;
        mListenerHandler = handler;
        mFocusGain = i;
        mAttr = audioattributes;
        mFlags = j;
    }

    AudioFocusRequest(AudioManager.OnAudioFocusChangeListener onaudiofocuschangelistener, Handler handler, AudioAttributes audioattributes, int i, int j, AudioFocusRequest audiofocusrequest)
    {
        this(onaudiofocuschangelistener, handler, audioattributes, i, j);
    }

    static final boolean isValidFocusGain(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
            return true;
        }
    }

    public boolean acceptsDelayedFocusGain()
    {
        boolean flag = true;
        if((mFlags & 1) != 1)
            flag = false;
        return flag;
    }

    public AudioAttributes getAudioAttributes()
    {
        return mAttr;
    }

    int getFlags()
    {
        return mFlags;
    }

    public int getFocusGain()
    {
        return mFocusGain;
    }

    public AudioManager.OnAudioFocusChangeListener getOnAudioFocusChangeListener()
    {
        return mFocusListener;
    }

    public Handler getOnAudioFocusChangeListenerHandler()
    {
        return mListenerHandler;
    }

    public boolean locksFocus()
    {
        boolean flag;
        if((mFlags & 4) == 4)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean willPauseWhenDucked()
    {
        boolean flag;
        if((mFlags & 2) == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static final AudioAttributes FOCUS_DEFAULT_ATTR = (new AudioAttributes.Builder()).setUsage(1).build();
    private final AudioAttributes mAttr;
    private final int mFlags;
    private final int mFocusGain;
    private final AudioManager.OnAudioFocusChangeListener mFocusListener;
    private final Handler mListenerHandler;

}
