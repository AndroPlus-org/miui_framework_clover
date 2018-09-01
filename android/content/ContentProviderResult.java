// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.content:
//            ContentProvider

public class ContentProviderResult
    implements Parcelable
{

    public ContentProviderResult(int i)
    {
        count = Integer.valueOf(i);
        uri = null;
    }

    public ContentProviderResult(ContentProviderResult contentproviderresult, int i)
    {
        uri = ContentProvider.maybeAddUserId(contentproviderresult.uri, i);
        count = contentproviderresult.count;
    }

    public ContentProviderResult(Uri uri1)
    {
        if(uri1 == null)
        {
            throw new IllegalArgumentException("uri must not be null");
        } else
        {
            uri = uri1;
            count = null;
            return;
        }
    }

    public ContentProviderResult(Parcel parcel)
    {
        if(parcel.readInt() == 1)
        {
            count = Integer.valueOf(parcel.readInt());
            uri = null;
        } else
        {
            count = null;
            uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        if(uri != null)
            return (new StringBuilder()).append("ContentProviderResult(uri=").append(uri.toString()).append(")").toString();
        else
            return (new StringBuilder()).append("ContentProviderResult(count=").append(count).append(")").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(uri == null)
        {
            parcel.writeInt(1);
            parcel.writeInt(count.intValue());
        } else
        {
            parcel.writeInt(2);
            uri.writeToParcel(parcel, 0);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ContentProviderResult createFromParcel(Parcel parcel)
        {
            return new ContentProviderResult(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ContentProviderResult[] newArray(int i)
        {
            return new ContentProviderResult[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final Integer count;
    public final Uri uri;

}
