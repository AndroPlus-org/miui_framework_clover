// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.app.PendingIntent;
import android.os.Parcel;
import android.text.ParcelableSpan;

public class EasyEditSpan
    implements ParcelableSpan
{

    public EasyEditSpan()
    {
        mPendingIntent = null;
        mDeleteEnabled = true;
    }

    public EasyEditSpan(PendingIntent pendingintent)
    {
        mPendingIntent = pendingintent;
        mDeleteEnabled = true;
    }

    public EasyEditSpan(Parcel parcel)
    {
        mPendingIntent = (PendingIntent)parcel.readParcelable(null);
        boolean flag;
        if(parcel.readByte() == 1)
            flag = true;
        else
            flag = false;
        mDeleteEnabled = flag;
    }

    public int describeContents()
    {
        return 0;
    }

    public PendingIntent getPendingIntent()
    {
        return mPendingIntent;
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 22;
    }

    public boolean isDeleteEnabled()
    {
        return mDeleteEnabled;
    }

    public void setDeleteEnabled(boolean flag)
    {
        mDeleteEnabled = flag;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        i = 0;
        parcel.writeParcelable(mPendingIntent, 0);
        if(mDeleteEnabled)
            i = 1;
        parcel.writeByte((byte)i);
    }

    public static final String EXTRA_TEXT_CHANGED_TYPE = "android.text.style.EXTRA_TEXT_CHANGED_TYPE";
    public static final int TEXT_DELETED = 1;
    public static final int TEXT_MODIFIED = 2;
    private boolean mDeleteEnabled;
    private final PendingIntent mPendingIntent;
}
