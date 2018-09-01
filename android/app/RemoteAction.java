// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.graphics.drawable.Icon;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.io.PrintWriter;

// Referenced classes of package android.app:
//            PendingIntent

public final class RemoteAction
    implements Parcelable
{

    public RemoteAction(Icon icon, CharSequence charsequence, CharSequence charsequence1, PendingIntent pendingintent)
    {
        while(icon == null || charsequence == null || charsequence1 == null || pendingintent == null) 
            throw new IllegalArgumentException("Expected icon, title, content description and action callback");
        mIcon = icon;
        mTitle = charsequence;
        mContentDescription = charsequence1;
        mActionIntent = pendingintent;
        mEnabled = true;
    }

    RemoteAction(Parcel parcel)
    {
        mIcon = (Icon)Icon.CREATOR.createFromParcel(parcel);
        mTitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mContentDescription = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mActionIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
        mEnabled = parcel.readBoolean();
    }

    public RemoteAction clone()
    {
        RemoteAction remoteaction = new RemoteAction(mIcon, mTitle, mContentDescription, mActionIntent);
        remoteaction.setEnabled(mEnabled);
        return remoteaction;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(String s, PrintWriter printwriter)
    {
        printwriter.print(s);
        printwriter.print((new StringBuilder()).append("title=").append(mTitle).toString());
        printwriter.print((new StringBuilder()).append(" enabled=").append(mEnabled).toString());
        printwriter.print((new StringBuilder()).append(" contentDescription=").append(mContentDescription).toString());
        printwriter.print((new StringBuilder()).append(" icon=").append(mIcon).toString());
        printwriter.print((new StringBuilder()).append(" action=").append(mActionIntent.getIntent()).toString());
        printwriter.println();
    }

    public PendingIntent getActionIntent()
    {
        return mActionIntent;
    }

    public CharSequence getContentDescription()
    {
        return mContentDescription;
    }

    public Icon getIcon()
    {
        return mIcon;
    }

    public CharSequence getTitle()
    {
        return mTitle;
    }

    public boolean isEnabled()
    {
        return mEnabled;
    }

    public void setEnabled(boolean flag)
    {
        mEnabled = flag;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        mIcon.writeToParcel(parcel, 0);
        TextUtils.writeToParcel(mTitle, parcel, i);
        TextUtils.writeToParcel(mContentDescription, parcel, i);
        mActionIntent.writeToParcel(parcel, i);
        parcel.writeBoolean(mEnabled);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RemoteAction createFromParcel(Parcel parcel)
        {
            return new RemoteAction(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RemoteAction[] newArray(int i)
        {
            return new RemoteAction[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "RemoteAction";
    private final PendingIntent mActionIntent;
    private final CharSequence mContentDescription;
    private boolean mEnabled;
    private final Icon mIcon;
    private final CharSequence mTitle;

}
