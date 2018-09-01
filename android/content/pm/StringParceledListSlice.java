// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.content.pm:
//            BaseParceledListSlice

public class StringParceledListSlice extends BaseParceledListSlice
{

    private StringParceledListSlice(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
    }

    StringParceledListSlice(Parcel parcel, ClassLoader classloader, StringParceledListSlice stringparceledlistslice)
    {
        this(parcel, classloader);
    }

    public StringParceledListSlice(List list)
    {
        super(list);
    }

    public static StringParceledListSlice emptyList()
    {
        return new StringParceledListSlice(Collections.emptyList());
    }

    public int describeContents()
    {
        return 0;
    }

    public volatile List getList()
    {
        return super.getList();
    }

    protected android.os.Parcelable.Creator readParcelableCreator(Parcel parcel, ClassLoader classloader)
    {
        return Parcel.STRING_CREATOR;
    }

    public volatile void setInlineCountLimit(int i)
    {
        super.setInlineCountLimit(i);
    }

    protected volatile void writeElement(Object obj, Parcel parcel, int i)
    {
        writeElement((String)obj, parcel, i);
    }

    protected void writeElement(String s, Parcel parcel, int i)
    {
        parcel.writeString(s);
    }

    protected volatile void writeParcelableCreator(Object obj, Parcel parcel)
    {
        writeParcelableCreator((String)obj, parcel);
    }

    protected void writeParcelableCreator(String s, Parcel parcel)
    {
    }

    public volatile void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
    }

    public static final android.os.Parcelable.ClassLoaderCreator CREATOR = new android.os.Parcelable.ClassLoaderCreator() {

        public StringParceledListSlice createFromParcel(Parcel parcel)
        {
            return new StringParceledListSlice(parcel, null, null);
        }

        public StringParceledListSlice createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new StringParceledListSlice(parcel, classloader, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return createFromParcel(parcel, classloader);
        }

        public StringParceledListSlice[] newArray(int i)
        {
            return new StringParceledListSlice[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;

}
