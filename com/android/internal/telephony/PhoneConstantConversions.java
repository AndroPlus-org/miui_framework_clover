// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;


public class PhoneConstantConversions
{

    private static int[] _2D_getcom_2D_android_2D_internal_2D_telephony_2D_PhoneConstants$DataStateSwitchesValues()
    {
        if(_2D_com_2D_android_2D_internal_2D_telephony_2D_PhoneConstants$DataStateSwitchesValues != null)
            return _2D_com_2D_android_2D_internal_2D_telephony_2D_PhoneConstants$DataStateSwitchesValues;
        int ai[] = new int[PhoneConstants.DataState.values().length];
        try
        {
            ai[PhoneConstants.DataState.CONNECTED.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[PhoneConstants.DataState.CONNECTING.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[PhoneConstants.DataState.DISCONNECTED.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[PhoneConstants.DataState.SUSPENDED.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_com_2D_android_2D_internal_2D_telephony_2D_PhoneConstants$DataStateSwitchesValues = ai;
        return ai;
    }

    private static int[] _2D_getcom_2D_android_2D_internal_2D_telephony_2D_PhoneConstants$StateSwitchesValues()
    {
        if(_2D_com_2D_android_2D_internal_2D_telephony_2D_PhoneConstants$StateSwitchesValues != null)
            return _2D_com_2D_android_2D_internal_2D_telephony_2D_PhoneConstants$StateSwitchesValues;
        int ai[] = new int[PhoneConstants.State.values().length];
        try
        {
            ai[PhoneConstants.State.IDLE.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[PhoneConstants.State.OFFHOOK.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[PhoneConstants.State.RINGING.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_com_2D_android_2D_internal_2D_telephony_2D_PhoneConstants$StateSwitchesValues = ai;
        return ai;
    }

    public PhoneConstantConversions()
    {
    }

    public static int convertCallState(PhoneConstants.State state)
    {
        switch(_2D_getcom_2D_android_2D_internal_2D_telephony_2D_PhoneConstants$StateSwitchesValues()[state.ordinal()])
        {
        default:
            return 0;

        case 2: // '\002'
            return 1;

        case 1: // '\001'
            return 2;
        }
    }

    public static PhoneConstants.State convertCallState(int i)
    {
        switch(i)
        {
        default:
            return PhoneConstants.State.IDLE;

        case 1: // '\001'
            return PhoneConstants.State.RINGING;

        case 2: // '\002'
            return PhoneConstants.State.OFFHOOK;
        }
    }

    public static int convertDataState(PhoneConstants.DataState datastate)
    {
        switch(_2D_getcom_2D_android_2D_internal_2D_telephony_2D_PhoneConstants$DataStateSwitchesValues()[datastate.ordinal()])
        {
        default:
            return 0;

        case 2: // '\002'
            return 1;

        case 1: // '\001'
            return 2;

        case 3: // '\003'
            return 3;
        }
    }

    public static PhoneConstants.DataState convertDataState(int i)
    {
        switch(i)
        {
        default:
            return PhoneConstants.DataState.DISCONNECTED;

        case 1: // '\001'
            return PhoneConstants.DataState.CONNECTING;

        case 2: // '\002'
            return PhoneConstants.DataState.CONNECTED;

        case 3: // '\003'
            return PhoneConstants.DataState.SUSPENDED;
        }
    }

    private static final int _2D_com_2D_android_2D_internal_2D_telephony_2D_PhoneConstants$DataStateSwitchesValues[];
    private static final int _2D_com_2D_android_2D_internal_2D_telephony_2D_PhoneConstants$StateSwitchesValues[];
}
