// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.database:
//            BulkCursorNative, CursorWindow, IBulkCursor

public final class BulkCursorDescriptor
    implements Parcelable
{

    public BulkCursorDescriptor()
    {
    }

    public int describeContents()
    {
        return 0;
    }

    public void readFromParcel(Parcel parcel)
    {
        boolean flag = false;
        cursor = BulkCursorNative.asInterface(parcel.readStrongBinder());
        columnNames = parcel.readStringArray();
        if(parcel.readInt() != 0)
            flag = true;
        wantsAllOnMoveCalls = flag;
        count = parcel.readInt();
        if(parcel.readInt() != 0)
            window = (CursorWindow)CursorWindow.CREATOR.createFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeStrongBinder(cursor.asBinder());
        parcel.writeStringArray(columnNames);
        int j;
        if(wantsAllOnMoveCalls)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeInt(count);
        if(window != null)
        {
            parcel.writeInt(1);
            window.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BulkCursorDescriptor createFromParcel(Parcel parcel)
        {
            BulkCursorDescriptor bulkcursordescriptor = new BulkCursorDescriptor();
            bulkcursordescriptor.readFromParcel(parcel);
            return bulkcursordescriptor;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BulkCursorDescriptor[] newArray(int i)
        {
            return new BulkCursorDescriptor[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public String columnNames[];
    public int count;
    public IBulkCursor cursor;
    public boolean wantsAllOnMoveCalls;
    public CursorWindow window;

}
