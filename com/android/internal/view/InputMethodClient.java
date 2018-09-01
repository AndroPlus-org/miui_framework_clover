// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;


public final class InputMethodClient
{

    public InputMethodClient()
    {
    }

    public static String getStartInputReason(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("Unknown=").append(i).toString();

        case 0: // '\0'
            return "UNSPECIFIED";

        case 1: // '\001'
            return "WINDOW_FOCUS_GAIN";

        case 2: // '\002'
            return "WINDOW_FOCUS_GAIN_REPORT_ONLY";

        case 3: // '\003'
            return "APP_CALLED_RESTART_INPUT_API";

        case 4: // '\004'
            return "CHECK_FOCUS";

        case 5: // '\005'
            return "BOUND_TO_IMMS";

        case 6: // '\006'
            return "UNBOUND_FROM_IMMS";

        case 7: // '\007'
            return "ACTIVATED_BY_IMMS";

        case 8: // '\b'
            return "DEACTIVATED_BY_IMMS";

        case 9: // '\t'
            return "SESSION_CREATED_BY_IME";
        }
    }

    public static String getUnbindReason(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("Unknown=").append(i).toString();

        case 0: // '\0'
            return "UNSPECIFIED";

        case 1: // '\001'
            return "SWITCH_CLIENT";

        case 2: // '\002'
            return "SWITCH_IME";

        case 3: // '\003'
            return "DISCONNECT_IME";

        case 4: // '\004'
            return "NO_IME";

        case 5: // '\005'
            return "SWITCH_IME_FAILED";

        case 6: // '\006'
            return "SWITCH_USER";
        }
    }

    public static String softInputModeToString(int i)
    {
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder();
        int j = i & 0xf;
        int k = i & 0xf0;
        if((i & 0x100) != 0)
            i = 1;
        else
            i = 0;
        j;
        JVM INSTR tableswitch 0 5: default 68
    //                   0 173
    //                   1 183
    //                   2 193
    //                   3 203
    //                   4 213
    //                   5 223;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        stringbuilder.append("STATE_UNKNOWN(");
        stringbuilder.append(j);
        stringbuilder.append(")");
_L13:
        k;
        JVM INSTR lookupswitch 4: default 132
    //                   0: 233
    //                   16: 243
    //                   32: 253
    //                   48: 263;
           goto _L8 _L9 _L10 _L11 _L12
_L8:
        stringbuilder.append("|ADJUST_UNKNOWN(");
        stringbuilder.append(k);
        stringbuilder.append(")");
_L14:
        if(i != 0)
            stringbuilder.append("|IS_FORWARD_NAVIGATION");
        return stringbuilder.toString();
_L2:
        stringbuilder.append("STATE_UNSPECIFIED");
          goto _L13
_L3:
        stringbuilder.append("STATE_UNCHANGED");
          goto _L13
_L4:
        stringbuilder.append("STATE_HIDDEN");
          goto _L13
_L5:
        stringbuilder.append("STATE_ALWAYS_HIDDEN");
          goto _L13
_L6:
        stringbuilder.append("STATE_VISIBLE");
          goto _L13
_L7:
        stringbuilder.append("STATE_ALWAYS_VISIBLE");
          goto _L13
_L9:
        stringbuilder.append("|ADJUST_UNSPECIFIED");
          goto _L14
_L10:
        stringbuilder.append("|ADJUST_RESIZE");
          goto _L14
_L11:
        stringbuilder.append("|ADJUST_PAN");
          goto _L14
_L12:
        stringbuilder.append("|ADJUST_NOTHING");
          goto _L14
    }

    public static final int START_INPUT_REASON_ACTIVATED_BY_IMMS = 7;
    public static final int START_INPUT_REASON_APP_CALLED_RESTART_INPUT_API = 3;
    public static final int START_INPUT_REASON_BOUND_TO_IMMS = 5;
    public static final int START_INPUT_REASON_CHECK_FOCUS = 4;
    public static final int START_INPUT_REASON_DEACTIVATED_BY_IMMS = 8;
    public static final int START_INPUT_REASON_SESSION_CREATED_BY_IME = 9;
    public static final int START_INPUT_REASON_UNBOUND_FROM_IMMS = 6;
    public static final int START_INPUT_REASON_UNSPECIFIED = 0;
    public static final int START_INPUT_REASON_WINDOW_FOCUS_GAIN = 1;
    public static final int START_INPUT_REASON_WINDOW_FOCUS_GAIN_REPORT_ONLY = 2;
    public static final int UNBIND_REASON_DISCONNECT_IME = 3;
    public static final int UNBIND_REASON_NO_IME = 4;
    public static final int UNBIND_REASON_SWITCH_CLIENT = 1;
    public static final int UNBIND_REASON_SWITCH_IME = 2;
    public static final int UNBIND_REASON_SWITCH_IME_FAILED = 5;
    public static final int UNBIND_REASON_SWITCH_USER = 6;
    public static final int UNBIND_REASON_UNSPECIFIED = 0;
}
