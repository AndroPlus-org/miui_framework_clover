// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keymaster;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;

public class KeymasterCertificateChain
    implements Parcelable
{

    public KeymasterCertificateChain()
    {
        mCertificates = null;
    }

    private KeymasterCertificateChain(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    KeymasterCertificateChain(Parcel parcel, KeymasterCertificateChain keymastercertificatechain)
    {
        this(parcel);
    }

    public KeymasterCertificateChain(List list)
    {
        mCertificates = list;
    }

    public int describeContents()
    {
        return 0;
    }

    public List getCertificates()
    {
        return mCertificates;
    }

    public void readFromParcel(Parcel parcel)
    {
        int i = parcel.readInt();
        mCertificates = new ArrayList(i);
        for(int j = 0; j < i; j++)
            mCertificates.add(parcel.createByteArray());

    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mCertificates == null)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(mCertificates.size());
            Iterator iterator = mCertificates.iterator();
            while(iterator.hasNext()) 
                parcel.writeByteArray((byte[])iterator.next());
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public KeymasterCertificateChain createFromParcel(Parcel parcel)
        {
            return new KeymasterCertificateChain(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public KeymasterCertificateChain[] newArray(int i)
        {
            return new KeymasterCertificateChain[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private List mCertificates;

}
