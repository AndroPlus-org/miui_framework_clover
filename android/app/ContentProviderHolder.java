// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.ContentProviderNative;
import android.content.IContentProvider;
import android.content.pm.ProviderInfo;
import android.os.*;

public class ContentProviderHolder
    implements Parcelable
{

    public ContentProviderHolder(ProviderInfo providerinfo)
    {
        info = providerinfo;
    }

    private ContentProviderHolder(Parcel parcel)
    {
        info = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(parcel);
        provider = ContentProviderNative.asInterface(parcel.readStrongBinder());
        connection = parcel.readStrongBinder();
        boolean flag;
        if(parcel.readInt() != 0)
            flag = true;
        else
            flag = false;
        noReleaseNeeded = flag;
    }

    ContentProviderHolder(Parcel parcel, ContentProviderHolder contentproviderholder)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        i = 0;
        info.writeToParcel(parcel, 0);
        if(provider != null)
            parcel.writeStrongBinder(provider.asBinder());
        else
            parcel.writeStrongBinder(null);
        parcel.writeStrongBinder(connection);
        if(noReleaseNeeded)
            i = 1;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ContentProviderHolder createFromParcel(Parcel parcel)
        {
            return new ContentProviderHolder(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ContentProviderHolder[] newArray(int i)
        {
            return new ContentProviderHolder[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public IBinder connection;
    public final ProviderInfo info;
    public boolean noReleaseNeeded;
    public IContentProvider provider;

}
