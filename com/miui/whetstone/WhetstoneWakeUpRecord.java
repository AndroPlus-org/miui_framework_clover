// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.app.PendingIntent;

public class WhetstoneWakeUpRecord
{

    public WhetstoneWakeUpRecord(int i, PendingIntent pendingintent, String s)
    {
        uid = i;
        intent = pendingintent;
        packageName = s;
        wakeup_count = 0;
        status = true;
    }

    public void clearWakeupCount()
    {
        wakeup_count = 0;
    }

    public void disableWakeUpStatus()
    {
        status = false;
    }

    public long getLastWakeUpTime()
    {
        return updateTime;
    }

    public PendingIntent getRecordPendingIntent()
    {
        return intent;
    }

    public boolean getRecordStatus()
    {
        return status;
    }

    public int getWakeUpCount()
    {
        return wakeup_count;
    }

    public void increaseWakeUpCount()
    {
        if(System.currentTimeMillis() - updateTime > 5000L)
        {
            wakeup_count = wakeup_count + 1;
            updateTime = System.currentTimeMillis();
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("uid = ").append(uid).append("; wakeup_count = ").append(wakeup_count).append("; intent = ").append(intent).append("; status = ").append(status).append("; updateTime = ").append(updateTime).toString();
    }

    private static final int DELAY_STOP_RTC_APP_TIME = 5000;
    public PendingIntent intent;
    public String packageName;
    public boolean status;
    public int uid;
    public long updateTime;
    public int wakeup_count;
}
