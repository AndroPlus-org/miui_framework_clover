// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.content.pm:
//            BaseParceledListSlice

public class ParceledListSlice extends BaseParceledListSlice
{

    private ParceledListSlice(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
    }

    ParceledListSlice(Parcel parcel, ClassLoader classloader, ParceledListSlice parceledlistslice)
    {
        this(parcel, classloader);
    }

    public ParceledListSlice(List list)
    {
        super(list);
    }

    public static ParceledListSlice emptyList()
    {
        return new ParceledListSlice(Collections.emptyList());
    }

    public int describeContents()
    {
        int i = 0;
        List list = getList();
        for(int j = 0; j < list.size(); j++)
            i |= ((Parcelable)list.get(j)).describeContents();

        return i;
    }

    public volatile List getList()
    {
        return super.getList();
    }

    protected android.os.Parcelable.Creator readParcelableCreator(Parcel parcel, ClassLoader classloader)
    {
        return parcel.readParcelableCreator(classloader);
    }

    public volatile void setInlineCountLimit(int i)
    {
        super.setInlineCountLimit(i);
    }

    protected void writeElement(Parcelable parcelable, Parcel parcel, int i)
    {
        parcelable.writeToParcel(parcel, i);
    }

    protected volatile void writeElement(Object obj, Parcel parcel, int i)
    {
        writeElement((Parcelable)obj, parcel, i);
    }

    protected void writeParcelableCreator(Parcelable parcelable, Parcel parcel)
    {
        parcel.writeParcelableCreator(parcelable);
    }

    protected volatile void writeParcelableCreator(Object obj, Parcel parcel)
    {
        writeParcelableCreator((Parcelable)obj, parcel);
    }

    public volatile void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
    }

    public static final android.os.Parcelable.ClassLoaderCreator CREATOR = new android.os.Parcelable.ClassLoaderCreator() {

        public ParceledListSlice createFromParcel(Parcel parcel)
        {
            return new ParceledListSlice(parcel, null, null);
        }

        public ParceledListSlice createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new ParceledListSlice(parcel, classloader, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return createFromParcel(parcel, classloader);
        }

        public ParceledListSlice[] newArray(int i)
        {
            return new ParceledListSlice[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;

}
