// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims;

import android.os.Handler;
import android.os.Message;

public interface ImsUtInterface
{

    public abstract void queryCLIP(Message message);

    public abstract void queryCLIR(Message message);

    public abstract void queryCOLP(Message message);

    public abstract void queryCOLR(Message message);

    public abstract void queryCallBarring(int i, int j, Message message);

    public abstract void queryCallBarring(int i, Message message);

    public abstract void queryCallForward(int i, String s, int j, Message message);

    public abstract void queryCallForward(int i, String s, Message message);

    public abstract void queryCallWaiting(Message message);

    public abstract void setSuppServiceIndication(Handler handler, int i, Object obj);

    public abstract void unSetSuppServiceIndication(Handler handler);

    public abstract void updateCLIP(boolean flag, Message message);

    public abstract void updateCLIR(int i, Message message);

    public abstract void updateCOLP(boolean flag, Message message);

    public abstract void updateCOLR(int i, Message message);

    public abstract void updateCallBarring(int i, int j, int k, Message message, String as[]);

    public abstract void updateCallBarring(int i, int j, Message message, String as[]);

    public abstract void updateCallForward(int i, int j, String s, int k, int l, Message message);

    public abstract void updateCallWaiting(boolean flag, int i, Message message);

    public static final int ACTION_ACTIVATION = 1;
    public static final int ACTION_DEACTIVATION = 0;
    public static final int ACTION_ERASURE = 4;
    public static final int ACTION_INTERROGATION = 5;
    public static final int ACTION_REGISTRATION = 3;
    public static final int CB_BAIC = 1;
    public static final int CB_BAOC = 2;
    public static final int CB_BA_ALL = 7;
    public static final int CB_BA_MO = 8;
    public static final int CB_BA_MT = 9;
    public static final int CB_BIC_ACR = 6;
    public static final int CB_BIC_WR = 5;
    public static final int CB_BOIC = 3;
    public static final int CB_BOIC_EXHC = 4;
    public static final int CB_BS_MT = 10;
    public static final int CDIV_CF_ALL = 4;
    public static final int CDIV_CF_ALL_CONDITIONAL = 5;
    public static final int CDIV_CF_BUSY = 1;
    public static final int CDIV_CF_NOT_LOGGED_IN = 6;
    public static final int CDIV_CF_NOT_REACHABLE = 3;
    public static final int CDIV_CF_NO_REPLY = 2;
    public static final int CDIV_CF_UNCONDITIONAL = 0;
    public static final int INVALID = -1;
    public static final int OIR_DEFAULT = 0;
    public static final int OIR_PRESENTATION_NOT_RESTRICTED = 2;
    public static final int OIR_PRESENTATION_RESTRICTED = 1;
}
