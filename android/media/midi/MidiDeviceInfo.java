// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.midi;

import android.os.*;
import android.util.Log;

public final class MidiDeviceInfo
    implements Parcelable
{
    public static final class PortInfo
    {

        public String getName()
        {
            return mName;
        }

        public int getPortNumber()
        {
            return mPortNumber;
        }

        public int getType()
        {
            return mPortType;
        }

        public static final int TYPE_INPUT = 1;
        public static final int TYPE_OUTPUT = 2;
        private final String mName;
        private final int mPortNumber;
        private final int mPortType;

        PortInfo(int i, int j, String s)
        {
            mPortType = i;
            mPortNumber = j;
            String s1 = s;
            if(s == null)
                s1 = "";
            mName = s1;
        }
    }


    public MidiDeviceInfo(int i, int j, int k, int l, String as[], String as1[], Bundle bundle, 
            boolean flag)
    {
        mType = i;
        mId = j;
        mInputPortCount = k;
        mOutputPortCount = l;
        if(as == null)
            mInputPortNames = new String[k];
        else
            mInputPortNames = as;
        if(as1 == null)
            mOutputPortNames = new String[l];
        else
            mOutputPortNames = as1;
        mProperties = bundle;
        mIsPrivate = flag;
    }

    private Bundle getBasicProperties(String as[])
    {
        Bundle bundle = new Bundle();
        int i = 0;
        int j = as.length;
        while(i < j) 
        {
            String s = as[i];
            Object obj = mProperties.get(s);
            if(obj != null)
                if(obj instanceof String)
                    bundle.putString(s, (String)obj);
                else
                if(obj instanceof Integer)
                    bundle.putInt(s, ((Integer)obj).intValue());
                else
                    Log.w("MidiDeviceInfo", (new StringBuilder()).append("Unsupported property type: ").append(obj.getClass().getName()).toString());
            i++;
        }
        return bundle;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof MidiDeviceInfo)
        {
            if(((MidiDeviceInfo)obj).mId == mId)
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    public int getId()
    {
        return mId;
    }

    public int getInputPortCount()
    {
        return mInputPortCount;
    }

    public int getOutputPortCount()
    {
        return mOutputPortCount;
    }

    public PortInfo[] getPorts()
    {
        PortInfo aportinfo[] = new PortInfo[mInputPortCount + mOutputPortCount];
        int i = 0;
        for(int j = 0; j < mInputPortCount;)
        {
            aportinfo[i] = new PortInfo(1, j, mInputPortNames[j]);
            j++;
            i++;
        }

        for(int k = 0; k < mOutputPortCount;)
        {
            aportinfo[i] = new PortInfo(2, k, mOutputPortNames[k]);
            k++;
            i++;
        }

        return aportinfo;
    }

    public Bundle getProperties()
    {
        return mProperties;
    }

    public int getType()
    {
        return mType;
    }

    public int hashCode()
    {
        return mId;
    }

    public boolean isPrivate()
    {
        return mIsPrivate;
    }

    public String toString()
    {
        mProperties.getString("name");
        return (new StringBuilder()).append("MidiDeviceInfo[mType=").append(mType).append(",mInputPortCount=").append(mInputPortCount).append(",mOutputPortCount=").append(mOutputPortCount).append(",mProperties=").append(mProperties).append(",mIsPrivate=").append(mIsPrivate).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mType);
        parcel.writeInt(mId);
        parcel.writeInt(mInputPortCount);
        parcel.writeInt(mOutputPortCount);
        parcel.writeStringArray(mInputPortNames);
        parcel.writeStringArray(mOutputPortNames);
        if(mIsPrivate)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeBundle(getBasicProperties(new String[] {
            "name", "manufacturer", "product", "version", "serial_number", "alsa_card", "alsa_device"
        }));
        parcel.writeBundle(mProperties);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MidiDeviceInfo createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            int j = parcel.readInt();
            int k = parcel.readInt();
            int l = parcel.readInt();
            String as[] = parcel.createStringArray();
            String as1[] = parcel.createStringArray();
            boolean flag;
            if(parcel.readInt() == 1)
                flag = true;
            else
                flag = false;
            parcel.readBundle();
            return new MidiDeviceInfo(i, j, k, l, as, as1, parcel.readBundle(), flag);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MidiDeviceInfo[] newArray(int i)
        {
            return new MidiDeviceInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String PROPERTY_ALSA_CARD = "alsa_card";
    public static final String PROPERTY_ALSA_DEVICE = "alsa_device";
    public static final String PROPERTY_BLUETOOTH_DEVICE = "bluetooth_device";
    public static final String PROPERTY_MANUFACTURER = "manufacturer";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_SERIAL_NUMBER = "serial_number";
    public static final String PROPERTY_SERVICE_INFO = "service_info";
    public static final String PROPERTY_USB_DEVICE = "usb_device";
    public static final String PROPERTY_VERSION = "version";
    private static final String TAG = "MidiDeviceInfo";
    public static final int TYPE_BLUETOOTH = 3;
    public static final int TYPE_USB = 1;
    public static final int TYPE_VIRTUAL = 2;
    private final int mId;
    private final int mInputPortCount;
    private final String mInputPortNames[];
    private final boolean mIsPrivate;
    private final int mOutputPortCount;
    private final String mOutputPortNames[];
    private final Bundle mProperties;
    private final int mType;

}
