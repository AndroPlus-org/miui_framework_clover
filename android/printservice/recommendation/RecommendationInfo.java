// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.printservice.recommendation;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public final class RecommendationInfo
    implements Parcelable
{

    private RecommendationInfo(Parcel parcel)
    {
        boolean flag = false;
        CharSequence charsequence = parcel.readCharSequence();
        CharSequence charsequence1 = parcel.readCharSequence();
        ArrayList arraylist = readDiscoveredPrinters(parcel);
        if(parcel.readByte() != 0)
            flag = true;
        this(charsequence, charsequence1, ((List) (arraylist)), flag);
    }

    RecommendationInfo(Parcel parcel, RecommendationInfo recommendationinfo)
    {
        this(parcel);
    }

    public RecommendationInfo(CharSequence charsequence, CharSequence charsequence1, int i, boolean flag)
    {
        throw new IllegalArgumentException("This constructor has been deprecated");
    }

    public RecommendationInfo(CharSequence charsequence, CharSequence charsequence1, List list, boolean flag)
    {
        mPackageName = Preconditions.checkStringNotEmpty(charsequence);
        mName = Preconditions.checkStringNotEmpty(charsequence1);
        mDiscoveredPrinters = (List)Preconditions.checkCollectionElementsNotNull(list, "discoveredPrinters");
        mRecommendsMultiVendorService = flag;
    }

    private static ArrayList readDiscoveredPrinters(Parcel parcel)
    {
        int i = parcel.readInt();
        ArrayList arraylist = new ArrayList(i);
        int j = 0;
        while(j < i) 
        {
            try
            {
                arraylist.add(InetAddress.getByAddress(parcel.readBlob()));
            }
            // Misplaced declaration of an exception variable
            catch(Parcel parcel)
            {
                throw new IllegalArgumentException(parcel);
            }
            j++;
        }
        return arraylist;
    }

    public int describeContents()
    {
        return 0;
    }

    public List getDiscoveredPrinters()
    {
        return mDiscoveredPrinters;
    }

    public CharSequence getName()
    {
        return mName;
    }

    public int getNumDiscoveredPrinters()
    {
        return mDiscoveredPrinters.size();
    }

    public CharSequence getPackageName()
    {
        return mPackageName;
    }

    public boolean recommendsMultiVendorService()
    {
        return mRecommendsMultiVendorService;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeCharSequence(mPackageName);
        parcel.writeCharSequence(mName);
        parcel.writeInt(mDiscoveredPrinters.size());
        for(Iterator iterator = mDiscoveredPrinters.iterator(); iterator.hasNext(); parcel.writeBlob(((InetAddress)iterator.next()).getAddress()));
        if(mRecommendsMultiVendorService)
            i = 1;
        else
            i = 0;
        parcel.writeByte((byte)i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RecommendationInfo createFromParcel(Parcel parcel)
        {
            return new RecommendationInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RecommendationInfo[] newArray(int i)
        {
            return new RecommendationInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final List mDiscoveredPrinters;
    private final CharSequence mName;
    private final CharSequence mPackageName;
    private final boolean mRecommendsMultiVendorService;

}
