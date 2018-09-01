// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

// Referenced classes of package android.hardware.display:
//            WifiDisplay, WifiDisplaySessionInfo

public final class WifiDisplayStatus
    implements Parcelable
{

    public WifiDisplayStatus()
    {
        this(0, 0, 0, null, WifiDisplay.EMPTY_ARRAY, null);
    }

    public WifiDisplayStatus(int i, int j, int k, WifiDisplay wifidisplay, WifiDisplay awifidisplay[], WifiDisplaySessionInfo wifidisplaysessioninfo)
    {
        if(awifidisplay == null)
            throw new IllegalArgumentException("displays must not be null");
        mFeatureState = i;
        mScanState = j;
        mActiveDisplayState = k;
        mActiveDisplay = wifidisplay;
        mDisplays = awifidisplay;
        if(wifidisplaysessioninfo == null)
            wifidisplaysessioninfo = new WifiDisplaySessionInfo();
        mSessionInfo = wifidisplaysessioninfo;
    }

    public int describeContents()
    {
        return 0;
    }

    public WifiDisplay getActiveDisplay()
    {
        return mActiveDisplay;
    }

    public int getActiveDisplayState()
    {
        return mActiveDisplayState;
    }

    public WifiDisplay[] getDisplays()
    {
        return mDisplays;
    }

    public int getFeatureState()
    {
        return mFeatureState;
    }

    public int getScanState()
    {
        return mScanState;
    }

    public WifiDisplaySessionInfo getSessionInfo()
    {
        return mSessionInfo;
    }

    public String toString()
    {
        return (new StringBuilder()).append("WifiDisplayStatus{featureState=").append(mFeatureState).append(", scanState=").append(mScanState).append(", activeDisplayState=").append(mActiveDisplayState).append(", activeDisplay=").append(mActiveDisplay).append(", displays=").append(Arrays.toString(mDisplays)).append(", sessionInfo=").append(mSessionInfo).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j = 0;
        parcel.writeInt(mFeatureState);
        parcel.writeInt(mScanState);
        parcel.writeInt(mActiveDisplayState);
        WifiDisplay awifidisplay[];
        if(mActiveDisplay != null)
        {
            parcel.writeInt(1);
            mActiveDisplay.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(mDisplays.length);
        awifidisplay = mDisplays;
        for(int k = awifidisplay.length; j < k; j++)
            awifidisplay[j].writeToParcel(parcel, i);

        mSessionInfo.writeToParcel(parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiDisplayStatus createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            int j = parcel.readInt();
            int k = parcel.readInt();
            WifiDisplay wifidisplay = null;
            if(parcel.readInt() != 0)
                wifidisplay = (WifiDisplay)WifiDisplay.CREATOR.createFromParcel(parcel);
            WifiDisplay awifidisplay[] = (WifiDisplay[])WifiDisplay.CREATOR.newArray(parcel.readInt());
            for(int l = 0; l < awifidisplay.length; l++)
                awifidisplay[l] = (WifiDisplay)WifiDisplay.CREATOR.createFromParcel(parcel);

            return new WifiDisplayStatus(i, j, k, wifidisplay, awifidisplay, (WifiDisplaySessionInfo)WifiDisplaySessionInfo.CREATOR.createFromParcel(parcel));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiDisplayStatus[] newArray(int i)
        {
            return new WifiDisplayStatus[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DISPLAY_STATE_CONNECTED = 2;
    public static final int DISPLAY_STATE_CONNECTING = 1;
    public static final int DISPLAY_STATE_NOT_CONNECTED = 0;
    public static final int FEATURE_STATE_DISABLED = 1;
    public static final int FEATURE_STATE_OFF = 2;
    public static final int FEATURE_STATE_ON = 3;
    public static final int FEATURE_STATE_UNAVAILABLE = 0;
    public static final int SCAN_STATE_NOT_SCANNING = 0;
    public static final int SCAN_STATE_SCANNING = 1;
    private final WifiDisplay mActiveDisplay;
    private final int mActiveDisplayState;
    private final WifiDisplay mDisplays[];
    private final int mFeatureState;
    private final int mScanState;
    private final WifiDisplaySessionInfo mSessionInfo;

}
