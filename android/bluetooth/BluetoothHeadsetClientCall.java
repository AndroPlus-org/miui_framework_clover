// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;
import java.util.UUID;

// Referenced classes of package android.bluetooth:
//            BluetoothDevice

public final class BluetoothHeadsetClientCall
    implements Parcelable
{

    public BluetoothHeadsetClientCall(BluetoothDevice bluetoothdevice, int i, int j, String s, boolean flag, boolean flag1)
    {
        this(bluetoothdevice, i, UUID.randomUUID(), j, s, flag, flag1);
    }

    public BluetoothHeadsetClientCall(BluetoothDevice bluetoothdevice, int i, UUID uuid, int j, String s, boolean flag, boolean flag1)
    {
        mDevice = bluetoothdevice;
        mId = i;
        mUUID = uuid;
        mState = j;
        if(s == null)
            s = "";
        mNumber = s;
        mMultiParty = flag;
        mOutgoing = flag1;
        mCreationElapsedMilli = SystemClock.elapsedRealtime();
    }

    public int describeContents()
    {
        return 0;
    }

    public long getCreationElapsedMilli()
    {
        return mCreationElapsedMilli;
    }

    public BluetoothDevice getDevice()
    {
        return mDevice;
    }

    public int getId()
    {
        return mId;
    }

    public String getNumber()
    {
        return mNumber;
    }

    public int getState()
    {
        return mState;
    }

    public UUID getUUID()
    {
        return mUUID;
    }

    public boolean isMultiParty()
    {
        return mMultiParty;
    }

    public boolean isOutgoing()
    {
        return mOutgoing;
    }

    public void setMultiParty(boolean flag)
    {
        mMultiParty = flag;
    }

    public void setNumber(String s)
    {
        mNumber = s;
    }

    public void setState(int i)
    {
        mState = i;
    }

    public String toString()
    {
        return toString(false);
    }

    public String toString(boolean flag)
    {
        StringBuilder stringbuilder;
        Object obj;
        stringbuilder = new StringBuilder("BluetoothHeadsetClientCall{mDevice: ");
        if(flag)
            obj = mDevice;
        else
            obj = Integer.valueOf(mDevice.hashCode());
        stringbuilder.append(obj);
        stringbuilder.append(", mId: ");
        stringbuilder.append(mId);
        stringbuilder.append(", mUUID: ");
        stringbuilder.append(mUUID);
        stringbuilder.append(", mState: ");
        mState;
        JVM INSTR tableswitch 0 7: default 116
    //                   0 205
    //                   1 215
    //                   2 225
    //                   3 235
    //                   4 245
    //                   5 255
    //                   6 265
    //                   7 275;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_275;
_L1:
        stringbuilder.append(mState);
_L10:
        stringbuilder.append(", mNumber: ");
        if(flag)
            obj = mNumber;
        else
            obj = Integer.valueOf(mNumber.hashCode());
        stringbuilder.append(obj);
        stringbuilder.append(", mMultiParty: ");
        stringbuilder.append(mMultiParty);
        stringbuilder.append(", mOutgoing: ");
        stringbuilder.append(mOutgoing);
        stringbuilder.append("}");
        return stringbuilder.toString();
_L2:
        stringbuilder.append("ACTIVE");
          goto _L10
_L3:
        stringbuilder.append("HELD");
          goto _L10
_L4:
        stringbuilder.append("DIALING");
          goto _L10
_L5:
        stringbuilder.append("ALERTING");
          goto _L10
_L6:
        stringbuilder.append("INCOMING");
          goto _L10
_L7:
        stringbuilder.append("WAITING");
          goto _L10
_L8:
        stringbuilder.append("HELD_BY_RESPONSE_AND_HOLD");
          goto _L10
        stringbuilder.append("TERMINATED");
          goto _L10
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeParcelable(mDevice, 0);
        parcel.writeInt(mId);
        parcel.writeString(mUUID.toString());
        parcel.writeInt(mState);
        parcel.writeString(mNumber);
        if(mMultiParty)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mOutgoing)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final int CALL_STATE_ACTIVE = 0;
    public static final int CALL_STATE_ALERTING = 3;
    public static final int CALL_STATE_DIALING = 2;
    public static final int CALL_STATE_HELD = 1;
    public static final int CALL_STATE_HELD_BY_RESPONSE_AND_HOLD = 6;
    public static final int CALL_STATE_INCOMING = 4;
    public static final int CALL_STATE_TERMINATED = 7;
    public static final int CALL_STATE_WAITING = 5;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothHeadsetClientCall createFromParcel(Parcel parcel)
        {
            boolean flag = true;
            BluetoothDevice bluetoothdevice = (BluetoothDevice)parcel.readParcelable(null);
            int i = parcel.readInt();
            UUID uuid = UUID.fromString(parcel.readString());
            int j = parcel.readInt();
            String s = parcel.readString();
            boolean flag1;
            if(parcel.readInt() == 1)
                flag1 = true;
            else
                flag1 = false;
            if(parcel.readInt() != 1)
                flag = false;
            return new BluetoothHeadsetClientCall(bluetoothdevice, i, uuid, j, s, flag1, flag);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothHeadsetClientCall[] newArray(int i)
        {
            return new BluetoothHeadsetClientCall[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final long mCreationElapsedMilli;
    private final BluetoothDevice mDevice;
    private final int mId;
    private boolean mMultiParty;
    private String mNumber;
    private final boolean mOutgoing;
    private int mState;
    private final UUID mUUID;

}
