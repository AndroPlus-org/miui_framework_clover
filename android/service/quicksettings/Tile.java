// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.quicksettings;

import android.graphics.drawable.Icon;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;

// Referenced classes of package android.service.quicksettings:
//            IQSService

public final class Tile
    implements Parcelable
{

    public Tile()
    {
        mState = 2;
    }

    public Tile(Parcel parcel)
    {
        mState = 2;
        readFromParcel(parcel);
    }

    private void readFromParcel(Parcel parcel)
    {
        if(parcel.readByte() != 0)
            mIcon = (Icon)Icon.CREATOR.createFromParcel(parcel);
        else
            mIcon = null;
        mState = parcel.readInt();
        mLabel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mContentDescription = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public CharSequence getContentDescription()
    {
        return mContentDescription;
    }

    public Icon getIcon()
    {
        return mIcon;
    }

    public CharSequence getLabel()
    {
        return mLabel;
    }

    public int getState()
    {
        return mState;
    }

    public void setContentDescription(CharSequence charsequence)
    {
        mContentDescription = charsequence;
    }

    public void setIcon(Icon icon)
    {
        mIcon = icon;
    }

    public void setLabel(CharSequence charsequence)
    {
        mLabel = charsequence;
    }

    public void setService(IQSService iqsservice, IBinder ibinder)
    {
        mService = iqsservice;
        mToken = ibinder;
    }

    public void setState(int i)
    {
        mState = i;
    }

    public void updateTile()
    {
        mService.updateQsTile(this, mToken);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("Tile", "Couldn't update tile");
          goto _L1
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mIcon != null)
        {
            parcel.writeByte((byte)1);
            mIcon.writeToParcel(parcel, i);
        } else
        {
            parcel.writeByte((byte)0);
        }
        parcel.writeInt(mState);
        TextUtils.writeToParcel(mLabel, parcel, i);
        TextUtils.writeToParcel(mContentDescription, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Tile createFromParcel(Parcel parcel)
        {
            return new Tile(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Tile[] newArray(int i)
        {
            return new Tile[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int STATE_ACTIVE = 2;
    public static final int STATE_INACTIVE = 1;
    public static final int STATE_UNAVAILABLE = 0;
    private static final String TAG = "Tile";
    private CharSequence mContentDescription;
    private Icon mIcon;
    private CharSequence mLabel;
    private IQSService mService;
    private int mState;
    private IBinder mToken;

}
