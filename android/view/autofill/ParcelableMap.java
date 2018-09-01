// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.autofill;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Iterator;

// Referenced classes of package android.view.autofill:
//            AutofillId, AutofillValue

class ParcelableMap extends HashMap
    implements Parcelable
{

    ParcelableMap(int i)
    {
        super(i);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(size());
        java.util.Map.Entry entry;
        for(Iterator iterator = entrySet().iterator(); iterator.hasNext(); parcel.writeParcelable((Parcelable)entry.getValue(), 0))
        {
            entry = (java.util.Map.Entry)iterator.next();
            parcel.writeParcelable((Parcelable)entry.getKey(), 0);
        }

    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ParcelableMap createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            ParcelableMap parcelablemap = new ParcelableMap(i);
            for(int j = 0; j < i; j++)
                parcelablemap.put((AutofillId)parcel.readParcelable(null), (AutofillValue)parcel.readParcelable(null));

            return parcelablemap;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ParcelableMap[] newArray(int i)
        {
            return new ParcelableMap[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;

}
