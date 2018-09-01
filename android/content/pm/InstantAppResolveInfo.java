// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public final class InstantAppResolveInfo
    implements Parcelable
{
    public static final class InstantAppDigest
        implements Parcelable
    {

        private static byte[][] generateDigest(String s, int i)
        {
            ArrayList arraylist;
            MessageDigest messagedigest;
            int j;
            arraylist = new ArrayList();
            try
            {
                messagedigest = MessageDigest.getInstance("SHA-256");
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw new IllegalStateException("could not find digest algorithm");
            }
            if(i > 0) goto _L2; else goto _L1
_L1:
            arraylist.add(messagedigest.digest(s.getBytes()));
_L6:
            return (byte[][])arraylist.toArray(new byte[arraylist.size()][]);
_L2:
            j = s.lastIndexOf('.', s.lastIndexOf('.') - 1);
            if(j >= 0)
                break MISSING_BLOCK_LABEL_94;
            arraylist.add(messagedigest.digest(s.getBytes()));
            continue; /* Loop/switch isn't completed */
            arraylist.add(messagedigest.digest(s.substring(j + 1, s.length()).getBytes()));
            int k = 1;
_L4:
            if(j < 0 || k >= i)
                continue; /* Loop/switch isn't completed */
            j = s.lastIndexOf('.', j - 1);
            arraylist.add(messagedigest.digest(s.substring(j + 1, s.length()).getBytes()));
            k++;
            if(true) goto _L4; else goto _L3
_L3:
            if(true) goto _L6; else goto _L5
_L5:
        }

        public int describeContents()
        {
            return 0;
        }

        public byte[][] getDigestBytes()
        {
            return mDigestBytes;
        }

        public int[] getDigestPrefix()
        {
            return mDigestPrefix;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            if(mDigestBytes == null)
            {
                parcel.writeInt(-1);
            } else
            {
                parcel.writeInt(mDigestBytes.length);
                i = 0;
                while(i < mDigestBytes.length) 
                {
                    parcel.writeByteArray(mDigestBytes[i]);
                    i++;
                }
            }
            parcel.writeIntArray(mDigestPrefix);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public InstantAppDigest createFromParcel(Parcel parcel)
            {
                return new InstantAppDigest(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public InstantAppDigest[] newArray(int i)
            {
                return new InstantAppDigest[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private static final int DIGEST_MASK = -4096;
        private static final int DIGEST_PREFIX_COUNT = 5;
        private final byte mDigestBytes[][];
        private final int mDigestPrefix[];


        InstantAppDigest(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i == -1)
            {
                mDigestBytes = null;
            } else
            {
                mDigestBytes = new byte[i][];
                int j = 0;
                while(j < i) 
                {
                    mDigestBytes[j] = parcel.createByteArray();
                    j++;
                }
            }
            mDigestPrefix = parcel.createIntArray();
        }

        public InstantAppDigest(String s)
        {
            this(s, -1);
        }

        public InstantAppDigest(String s, int i)
        {
            if(s == null)
                throw new IllegalArgumentException();
            mDigestBytes = generateDigest(s.toLowerCase(Locale.ENGLISH), i);
            mDigestPrefix = new int[mDigestBytes.length];
            for(i = 0; i < mDigestBytes.length; i++)
                mDigestPrefix[i] = ((mDigestBytes[i][0] & 0xff) << 24 | (mDigestBytes[i][1] & 0xff) << 16 | (mDigestBytes[i][2] & 0xff) << 8 | (mDigestBytes[i][3] & 0xff) << 0) & 0xfffff000;

        }
    }


    public InstantAppResolveInfo(InstantAppDigest instantappdigest, String s, List list, int i)
    {
        while(s == null && list != null && list.size() != 0 || s != null && (list == null || list.size() == 0)) 
            throw new IllegalArgumentException();
        mDigest = instantappdigest;
        if(list != null)
        {
            mFilters = new ArrayList(list.size());
            mFilters.addAll(list);
        } else
        {
            mFilters = null;
        }
        mPackageName = s;
        mVersionCode = i;
    }

    InstantAppResolveInfo(Parcel parcel)
    {
        mDigest = (InstantAppDigest)parcel.readParcelable(null);
        mPackageName = parcel.readString();
        mFilters = new ArrayList();
        parcel.readList(mFilters, null);
        mVersionCode = parcel.readInt();
    }

    public InstantAppResolveInfo(String s, String s1, List list)
    {
        this(new InstantAppDigest(s), s1, list, -1);
    }

    public int describeContents()
    {
        return 0;
    }

    public byte[] getDigestBytes()
    {
        return mDigest.getDigestBytes()[0];
    }

    public int getDigestPrefix()
    {
        return mDigest.getDigestPrefix()[0];
    }

    public List getIntentFilters()
    {
        return mFilters;
    }

    public String getPackageName()
    {
        return mPackageName;
    }

    public int getVersionCode()
    {
        return mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mDigest, i);
        parcel.writeString(mPackageName);
        parcel.writeList(mFilters);
        parcel.writeInt(mVersionCode);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public InstantAppResolveInfo createFromParcel(Parcel parcel)
        {
            return new InstantAppResolveInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public InstantAppResolveInfo[] newArray(int i)
        {
            return new InstantAppResolveInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String SHA_ALGORITHM = "SHA-256";
    private final InstantAppDigest mDigest;
    private final List mFilters;
    private final String mPackageName;
    private final int mVersionCode;

}
