// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;

// Referenced classes of package android.net.wifi:
//            WifiChannel

public class ScanSettings
    implements Parcelable
{

    public ScanSettings()
    {
    }

    public ScanSettings(ScanSettings scansettings)
    {
        if(scansettings.channelSet != null)
            channelSet = new ArrayList(scansettings.channelSet);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean isValid()
    {
        for(Iterator iterator = channelSet.iterator(); iterator.hasNext();)
            if(!((WifiChannel)iterator.next()).isValid())
                return false;

        return true;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j;
        if(channelSet == null)
            j = 0;
        else
            j = channelSet.size();
        parcel.writeInt(j);
        if(channelSet != null)
        {
            for(Iterator iterator = channelSet.iterator(); iterator.hasNext(); ((WifiChannel)iterator.next()).writeToParcel(parcel, i));
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ScanSettings createFromParcel(Parcel parcel)
        {
            ScanSettings scansettings = new ScanSettings();
            int i = parcel.readInt();
            if(i > 0)
            {
                scansettings.channelSet = new ArrayList(i);
                for(; i > 0; i--)
                    scansettings.channelSet.add((WifiChannel)WifiChannel.CREATOR.createFromParcel(parcel));

            }
            return scansettings;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ScanSettings[] newArray(int i)
        {
            return new ScanSettings[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public Collection channelSet;

}
