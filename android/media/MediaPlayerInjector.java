// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.util.EventLog;

class MediaPlayerInjector
{

    MediaPlayerInjector()
    {
    }

    static void updateActiveProcessStatus(boolean flag, boolean flag1, int i, int j)
    {
        if(flag != flag1 && j > 10000)
        {
            int k;
            if(flag1)
                k = 1;
            else
                k = 2;
            EventLog.writeEvent(30200, (new StringBuilder(16)).append(i).append("&").append(j).append("&").append(k).toString());
        }
    }

    public static final int AUDIO_STATUS_CHANGE = 30200;
}
