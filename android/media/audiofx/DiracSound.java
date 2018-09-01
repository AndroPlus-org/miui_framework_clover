// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiofx;

import java.util.UUID;

// Referenced classes of package android.media.audiofx:
//            AudioEffect

public class DiracSound extends AudioEffect
{

    public DiracSound(int i, int j)
    {
        super(EFFECT_TYPE_NULL, EFFECT_TYPE_DIRACSOUND, i, j);
    }

    public int getHeadsetType()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        int ai[] = new int[1];
        checkStatus(getParameter(1, ai));
        return ai[0];
    }

    public float getLevel(int i)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        byte abyte0[] = new byte[10];
        checkStatus(getParameter(new int[] {
            2, i
        }, abyte0));
        return (new Float(new String(abyte0))).floatValue();
    }

    public int getMode()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        int ai[] = new int[1];
        checkStatus(getParameter(3, ai));
        return ai[0];
    }

    public int getMovie()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        int ai[] = new int[1];
        checkStatus(getParameter(5, ai));
        return ai[0];
    }

    public int getMovieMode()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        int ai[] = new int[1];
        checkStatus(getParameter(7, ai));
        return ai[0];
    }

    public int getMusic()
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        int ai[] = new int[1];
        checkStatus(getParameter(4, ai));
        return ai[0];
    }

    public void setHeadsetType(int i)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(1, i));
    }

    public void setHifiMode(int i)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(8, i));
    }

    public void setLevel(int i, float f)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        byte abyte0[] = String.valueOf(f).getBytes();
        checkStatus(setParameter(new int[] {
            2, i
        }, abyte0));
    }

    public void setMode(int i)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(3, i));
    }

    public void setMovie(int i)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(5, i));
    }

    public void setMovieMode(int i)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(7, i));
    }

    public void setMusic(int i)
        throws IllegalStateException, IllegalArgumentException, UnsupportedOperationException
    {
        checkStatus(setParameter(4, i));
    }

    public static final int DIRACSOUND_HEADSET_EM001 = 15;
    public static final int DIRACSOUND_HEADSET_EM006 = 18;
    public static final int DIRACSOUND_HEADSET_EM007 = 16;
    public static final int DIRACSOUND_HEADSET_EM013 = 19;
    public static final int DIRACSOUND_HEADSET_EM303 = 13;
    public static final int DIRACSOUND_HEADSET_EM304 = 14;
    public static final int DIRACSOUND_HEADSET_GENERAL = 5;
    public static final int DIRACSOUND_HEADSET_GENERAL_INEAR = 6;
    public static final int DIRACSOUND_HEADSET_HM004 = 17;
    public static final int DIRACSOUND_HEADSET_MEP100 = 1;
    public static final int DIRACSOUND_HEADSET_MEP200 = 2;
    public static final int DIRACSOUND_HEADSET_MK101 = 7;
    public static final int DIRACSOUND_HEADSET_MK301 = 8;
    public static final int DIRACSOUND_HEADSET_MK303 = 9;
    public static final int DIRACSOUND_HEADSET_MO701 = 10;
    public static final int DIRACSOUND_HEADSET_MR102 = 11;
    public static final int DIRACSOUND_HEADSET_NONE = 0;
    public static final int DIRACSOUND_HEADSET_PHD = 12;
    public static final int DIRACSOUND_HEADSET_PISTON100 = 3;
    public static final int DIRACSOUND_HEADSET_PISTON200 = 4;
    public static final int DIRACSOUND_MODE_MOVIE = 1;
    public static final int DIRACSOUND_MODE_MOVIE_CINEMA = 0;
    public static final int DIRACSOUND_MODE_MOVIE_CINIMA = 0;
    public static final int DIRACSOUND_MODE_MOVIE_STUDIO = 1;
    public static final int DIRACSOUND_MODE_MUSIC = 0;
    private static final int DIRACSOUND_PARAM_EQ_LEVEL = 2;
    private static final int DIRACSOUND_PARAM_HEADSET_TYPE = 1;
    private static final int DIRACSOUND_PARAM_HIFI_MODE = 8;
    private static final int DIRACSOUND_PARAM_MODE = 3;
    private static final int DIRACSOUND_PARAM_MOVIE = 5;
    private static final int DIRACSOUND_PARAM_MOVIE_MODE = 7;
    private static final int DIRACSOUND_PARAM_MUSIC = 4;
    private static final UUID EFFECT_TYPE_DIRACSOUND = UUID.fromString("e069d9e0-8329-11df-9168-0002a5d5c51b");
    private static final String TAG = "DiracSound";

}
