// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import android.util.EventLog;

public class EventLogTags
{

    private EventLogTags()
    {
    }

    public static void writeTtsSpeakFailure(String s, int i, int j, int k, String s1, int l, int i1)
    {
        EventLog.writeEvent(0x128e2, new Object[] {
            s, Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), s1, Integer.valueOf(l), Integer.valueOf(i1)
        });
    }

    public static void writeTtsSpeakSuccess(String s, int i, int j, int k, String s1, int l, int i1, long l1, long l2, long l3)
    {
        EventLog.writeEvent(0x128e1, new Object[] {
            s, Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), s1, Integer.valueOf(l), Integer.valueOf(i1), Long.valueOf(l1), Long.valueOf(l2), Long.valueOf(l3)
        });
    }

    public static void writeTtsV2SpeakFailure(String s, int i, int j, int k, String s1, int l)
    {
        EventLog.writeEvent(0x128e4, new Object[] {
            s, Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), s1, Integer.valueOf(l)
        });
    }

    public static void writeTtsV2SpeakSuccess(String s, int i, int j, int k, String s1, long l, long l1, long l2)
    {
        EventLog.writeEvent(0x128e3, new Object[] {
            s, Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), s1, Long.valueOf(l), Long.valueOf(l1), Long.valueOf(l2)
        });
    }

    public static final int TTS_SPEAK_FAILURE = 0x128e2;
    public static final int TTS_SPEAK_SUCCESS = 0x128e1;
    public static final int TTS_V2_SPEAK_FAILURE = 0x128e4;
    public static final int TTS_V2_SPEAK_SUCCESS = 0x128e3;
}
