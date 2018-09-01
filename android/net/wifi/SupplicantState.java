// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;

public final class SupplicantState extends Enum
    implements Parcelable
{

    private static int[] _2D_getandroid_2D_net_2D_wifi_2D_SupplicantStateSwitchesValues()
    {
        if(_2D_android_2D_net_2D_wifi_2D_SupplicantStateSwitchesValues != null)
            return _2D_android_2D_net_2D_wifi_2D_SupplicantStateSwitchesValues;
        int ai[] = new int[values().length];
        try
        {
            ai[ASSOCIATED.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror12) { }
        try
        {
            ai[ASSOCIATING.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror11) { }
        try
        {
            ai[AUTHENTICATING.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror10) { }
        try
        {
            ai[COMPLETED.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror9) { }
        try
        {
            ai[DISCONNECTED.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror8) { }
        try
        {
            ai[DORMANT.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror7) { }
        try
        {
            ai[FOUR_WAY_HANDSHAKE.ordinal()] = 7;
        }
        catch(NoSuchFieldError nosuchfielderror6) { }
        try
        {
            ai[GROUP_HANDSHAKE.ordinal()] = 8;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai[INACTIVE.ordinal()] = 9;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[INTERFACE_DISABLED.ordinal()] = 10;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[INVALID.ordinal()] = 11;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[SCANNING.ordinal()] = 12;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[UNINITIALIZED.ordinal()] = 13;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_android_2D_net_2D_wifi_2D_SupplicantStateSwitchesValues = ai;
        return ai;
    }

    private SupplicantState(String s, int i)
    {
        super(s, i);
    }

    public static boolean isConnecting(SupplicantState supplicantstate)
    {
        switch(_2D_getandroid_2D_net_2D_wifi_2D_SupplicantStateSwitchesValues()[supplicantstate.ordinal()])
        {
        default:
            throw new IllegalArgumentException("Unknown supplicant state");

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 7: // '\007'
        case 8: // '\b'
            return true;

        case 5: // '\005'
        case 6: // '\006'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
            return false;
        }
    }

    public static boolean isDriverActive(SupplicantState supplicantstate)
    {
        switch(_2D_getandroid_2D_net_2D_wifi_2D_SupplicantStateSwitchesValues()[supplicantstate.ordinal()])
        {
        default:
            throw new IllegalArgumentException("Unknown supplicant state");

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 12: // '\f'
            return true;

        case 10: // '\n'
        case 11: // '\013'
        case 13: // '\r'
            return false;
        }
    }

    public static boolean isHandshakeState(SupplicantState supplicantstate)
    {
        switch(_2D_getandroid_2D_net_2D_wifi_2D_SupplicantStateSwitchesValues()[supplicantstate.ordinal()])
        {
        default:
            throw new IllegalArgumentException("Unknown supplicant state");

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 7: // '\007'
        case 8: // '\b'
            return true;

        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
            return false;
        }
    }

    public static boolean isValidState(SupplicantState supplicantstate)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(supplicantstate != UNINITIALIZED)
        {
            flag1 = flag;
            if(supplicantstate != INVALID)
                flag1 = true;
        }
        return flag1;
    }

    public static SupplicantState valueOf(String s)
    {
        return (SupplicantState)Enum.valueOf(android/net/wifi/SupplicantState, s);
    }

    public static SupplicantState[] values()
    {
        return $VALUES;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(name());
    }

    private static final SupplicantState $VALUES[];
    private static final int _2D_android_2D_net_2D_wifi_2D_SupplicantStateSwitchesValues[];
    public static final SupplicantState ASSOCIATED;
    public static final SupplicantState ASSOCIATING;
    public static final SupplicantState AUTHENTICATING;
    public static final SupplicantState COMPLETED;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SupplicantState createFromParcel(Parcel parcel)
        {
            return SupplicantState.valueOf(parcel.readString());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SupplicantState[] newArray(int i)
        {
            return new SupplicantState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final SupplicantState DISCONNECTED;
    public static final SupplicantState DORMANT;
    public static final SupplicantState FOUR_WAY_HANDSHAKE;
    public static final SupplicantState GROUP_HANDSHAKE;
    public static final SupplicantState INACTIVE;
    public static final SupplicantState INTERFACE_DISABLED;
    public static final SupplicantState INVALID;
    public static final SupplicantState SCANNING;
    public static final SupplicantState UNINITIALIZED;

    static 
    {
        DISCONNECTED = new SupplicantState("DISCONNECTED", 0);
        INTERFACE_DISABLED = new SupplicantState("INTERFACE_DISABLED", 1);
        INACTIVE = new SupplicantState("INACTIVE", 2);
        SCANNING = new SupplicantState("SCANNING", 3);
        AUTHENTICATING = new SupplicantState("AUTHENTICATING", 4);
        ASSOCIATING = new SupplicantState("ASSOCIATING", 5);
        ASSOCIATED = new SupplicantState("ASSOCIATED", 6);
        FOUR_WAY_HANDSHAKE = new SupplicantState("FOUR_WAY_HANDSHAKE", 7);
        GROUP_HANDSHAKE = new SupplicantState("GROUP_HANDSHAKE", 8);
        COMPLETED = new SupplicantState("COMPLETED", 9);
        DORMANT = new SupplicantState("DORMANT", 10);
        UNINITIALIZED = new SupplicantState("UNINITIALIZED", 11);
        INVALID = new SupplicantState("INVALID", 12);
        $VALUES = (new SupplicantState[] {
            DISCONNECTED, INTERFACE_DISABLED, INACTIVE, SCANNING, AUTHENTICATING, ASSOCIATING, ASSOCIATED, FOUR_WAY_HANDSHAKE, GROUP_HANDSHAKE, COMPLETED, 
            DORMANT, UNINITIALIZED, INVALID
        });
    }
}
