// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.Parcel;
import android.text.TextUtils;

// Referenced classes of package android.app:
//            PendingIntent

public class MiuiNotification
{

    public MiuiNotification()
    {
        enableFloat = true;
        enableKeyguard = true;
        floatTime = 5000;
        messageCount = 1;
    }

    public static int[] getLedPwmOffOn(int i)
    {
        int ai[] = new int[2];
        ai[0] = (i / 4) * 3;
        ai[1] = i - ai[0];
        return ai;
    }

    public PendingIntent getExitFloatingIntent()
    {
        return exitFloatingIntent;
    }

    public int getFloatTime()
    {
        return floatTime;
    }

    public CharSequence getMessageClassName()
    {
        return messageClassName;
    }

    public int getMessageCount()
    {
        return messageCount;
    }

    public CharSequence getTargetPkg()
    {
        return targetPkg;
    }

    public boolean isEnableFloat()
    {
        return enableFloat;
    }

    public boolean isEnableKeyguard()
    {
        return enableKeyguard;
    }

    public void readFromParcel(Parcel parcel)
    {
        boolean flag = true;
        boolean flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        customizedIcon = flag1;
        traceType = parcel.readInt();
        if(parcel.readInt() != 0)
            traceContent = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        enableFloat = flag1;
        floatTime = parcel.readInt();
        if(parcel.readInt() == 1)
            flag1 = flag;
        else
            flag1 = false;
        enableKeyguard = flag1;
        if(parcel.readInt() != 0)
            targetPkg = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        if(parcel.readInt() != 0)
            exitFloatingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
        messageCount = parcel.readInt();
        if(parcel.readInt() != 0)
            messageClassName = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }

    public MiuiNotification setCustomizedIcon(boolean flag)
    {
        customizedIcon = flag;
        return this;
    }

    public MiuiNotification setEnableFloat(boolean flag)
    {
        enableFloat = flag;
        return this;
    }

    public MiuiNotification setEnableKeyguard(boolean flag)
    {
        enableKeyguard = flag;
        return this;
    }

    public MiuiNotification setExitFloatingIntent(PendingIntent pendingintent)
    {
        exitFloatingIntent = pendingintent;
        return this;
    }

    public MiuiNotification setFloatTime(int i)
    {
        if(i > 0)
            floatTime = i;
        return this;
    }

    public void setMessageClassName(CharSequence charsequence)
    {
        messageClassName = charsequence;
    }

    public void setMessageCount(int i)
    {
        messageCount = i;
    }

    public void setTargetPkg(CharSequence charsequence)
    {
        targetPkg = charsequence;
    }

    public void setTo(MiuiNotification miuinotification)
    {
        traceType = miuinotification.traceType;
        traceContent = miuinotification.traceContent;
        customizedIcon = miuinotification.customizedIcon;
        enableFloat = miuinotification.enableFloat;
        floatTime = miuinotification.floatTime;
        enableKeyguard = miuinotification.enableKeyguard;
        targetPkg = miuinotification.targetPkg;
        exitFloatingIntent = miuinotification.exitFloatingIntent;
        messageCount = miuinotification.messageCount;
        messageClassName = miuinotification.messageClassName;
    }

    public MiuiNotification setTrace(int i, CharSequence charsequence)
    {
        traceType = i;
        traceContent = charsequence;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j;
        if(customizedIcon)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeInt(traceType);
        if(traceContent != null)
        {
            parcel.writeInt(1);
            TextUtils.writeToParcel(traceContent, parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        if(enableFloat)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeInt(floatTime);
        if(enableKeyguard)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(targetPkg != null)
        {
            parcel.writeInt(1);
            TextUtils.writeToParcel(targetPkg, parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        if(exitFloatingIntent != null)
        {
            parcel.writeInt(1);
            exitFloatingIntent.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(messageCount);
        if(messageClassName != null)
        {
            parcel.writeInt(1);
            TextUtils.writeToParcel(messageClassName, parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
    }

    public static final String EXTRA_SHOW_ACTION = "miui.showAction";
    public static final int TYPE_ADVERTISEMENT = 1;
    public boolean customizedIcon;
    private boolean enableFloat;
    private boolean enableKeyguard;
    private PendingIntent exitFloatingIntent;
    private int floatTime;
    private CharSequence messageClassName;
    private int messageCount;
    private CharSequence targetPkg;
    public CharSequence traceContent;
    public int traceType;
}
