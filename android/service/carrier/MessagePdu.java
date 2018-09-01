// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.carrier;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;

public final class MessagePdu
    implements Parcelable
{

    public MessagePdu(List list)
    {
        if(list == null || list.contains(null))
        {
            throw new IllegalArgumentException("pduList must not be null or contain nulls");
        } else
        {
            mPduList = list;
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public List getPdus()
    {
        return mPduList;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mPduList == null)
        {
            parcel.writeInt(-1);
        } else
        {
            parcel.writeInt(mPduList.size());
            Iterator iterator = mPduList.iterator();
            while(iterator.hasNext()) 
                parcel.writeByteArray((byte[])iterator.next());
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MessagePdu createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i != -1) goto _L2; else goto _L1
_L1:
            Object obj = null;
_L4:
            return new MessagePdu(((List) (obj)));
_L2:
            ArrayList arraylist = new ArrayList(i);
            int j = 0;
            do
            {
                obj = arraylist;
                if(j >= i)
                    continue;
                arraylist.add(parcel.createByteArray());
                j++;
            } while(true);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MessagePdu[] newArray(int i)
        {
            return new MessagePdu[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int NULL_LENGTH = -1;
    private final List mPduList;

}
