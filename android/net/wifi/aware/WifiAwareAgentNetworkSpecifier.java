// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.aware;

import android.net.NetworkSpecifier;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import libcore.util.HexEncoding;

// Referenced classes of package android.net.wifi.aware:
//            WifiAwareNetworkSpecifier

public class WifiAwareAgentNetworkSpecifier extends NetworkSpecifier
    implements Parcelable
{
    private static class ByteArrayWrapper
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof ByteArrayWrapper))
                return false;
            else
                return Arrays.equals(((ByteArrayWrapper)obj).mData, mData);
        }

        public int hashCode()
        {
            return Arrays.hashCode(mData);
        }

        public String toString()
        {
            return new String(HexEncoding.encode(mData));
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeBlob(mData);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ByteArrayWrapper createFromParcel(Parcel parcel)
            {
                return new ByteArrayWrapper(parcel.readBlob());
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ByteArrayWrapper[] newArray(int i)
            {
                return new ByteArrayWrapper[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private byte mData[];


        ByteArrayWrapper(byte abyte0[])
        {
            mData = abyte0;
        }
    }


    static Set _2D_get0(WifiAwareAgentNetworkSpecifier wifiawareagentnetworkspecifier)
    {
        return wifiawareagentnetworkspecifier.mNetworkSpecifiers;
    }

    public WifiAwareAgentNetworkSpecifier()
    {
        mNetworkSpecifiers = new HashSet();
    }

    public WifiAwareAgentNetworkSpecifier(WifiAwareNetworkSpecifier wifiawarenetworkspecifier)
    {
        mNetworkSpecifiers = new HashSet();
        initialize();
        mNetworkSpecifiers.add(convert(wifiawarenetworkspecifier));
    }

    public WifiAwareAgentNetworkSpecifier(WifiAwareNetworkSpecifier awifiawarenetworkspecifier[])
    {
        mNetworkSpecifiers = new HashSet();
        initialize();
        int i = 0;
        for(int j = awifiawarenetworkspecifier.length; i < j; i++)
        {
            WifiAwareNetworkSpecifier wifiawarenetworkspecifier = awifiawarenetworkspecifier[i];
            mNetworkSpecifiers.add(convert(wifiawarenetworkspecifier));
        }

    }

    private ByteArrayWrapper convert(WifiAwareNetworkSpecifier wifiawarenetworkspecifier)
    {
        if(mDigester == null)
        {
            return null;
        } else
        {
            Parcel parcel = Parcel.obtain();
            wifiawarenetworkspecifier.writeToParcel(parcel, 0);
            wifiawarenetworkspecifier = parcel.marshall();
            mDigester.reset();
            mDigester.update(wifiawarenetworkspecifier);
            return new ByteArrayWrapper(mDigester.digest());
        }
    }

    private void initialize()
    {
        try
        {
            mDigester = MessageDigest.getInstance("SHA-256");
            return;
        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception)
        {
            Log.e("WifiAwareAgentNs", "Can not instantiate a SHA-256 digester!? Will match nothing.");
        }
    }

    public void assertValidFromUid(int i)
    {
        throw new SecurityException("WifiAwareAgentNetworkSpecifier should not be used in network requests");
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;
        if(!(obj instanceof WifiAwareAgentNetworkSpecifier))
            return false;
        else
            return mNetworkSpecifiers.equals(((WifiAwareAgentNetworkSpecifier)obj).mNetworkSpecifiers);
    }

    public int hashCode()
    {
        return mNetworkSpecifiers.hashCode();
    }

    public boolean isEmpty()
    {
        return mNetworkSpecifiers.isEmpty();
    }

    public boolean satisfiedBy(NetworkSpecifier networkspecifier)
    {
        if(!(networkspecifier instanceof WifiAwareAgentNetworkSpecifier))
            return false;
        networkspecifier = (WifiAwareAgentNetworkSpecifier)networkspecifier;
        for(Iterator iterator = mNetworkSpecifiers.iterator(); iterator.hasNext();)
        {
            ByteArrayWrapper bytearraywrapper = (ByteArrayWrapper)iterator.next();
            if(!((WifiAwareAgentNetworkSpecifier) (networkspecifier)).mNetworkSpecifiers.contains(bytearraywrapper))
                return false;
        }

        return true;
    }

    public boolean satisfiesAwareNetworkSpecifier(WifiAwareNetworkSpecifier wifiawarenetworkspecifier)
    {
        wifiawarenetworkspecifier = convert(wifiawarenetworkspecifier);
        return mNetworkSpecifiers.contains(wifiawarenetworkspecifier);
    }

    public String toString()
    {
        StringJoiner stringjoiner = new StringJoiner(",");
        for(Iterator iterator = mNetworkSpecifiers.iterator(); iterator.hasNext(); stringjoiner.add(((ByteArrayWrapper)iterator.next()).toString()));
        return stringjoiner.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeArray(mNetworkSpecifiers.toArray());
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiAwareAgentNetworkSpecifier createFromParcel(Parcel parcel)
        {
            WifiAwareAgentNetworkSpecifier wifiawareagentnetworkspecifier = new WifiAwareAgentNetworkSpecifier();
            parcel = ((Parcel) (parcel.readArray(null)));
            int i = 0;
            for(int j = parcel.length; i < j; i++)
            {
                Object obj = parcel[i];
                WifiAwareAgentNetworkSpecifier._2D_get0(wifiawareagentnetworkspecifier).add((ByteArrayWrapper)obj);
            }

            return wifiawareagentnetworkspecifier;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiAwareAgentNetworkSpecifier[] newArray(int i)
        {
            return new WifiAwareAgentNetworkSpecifier[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "WifiAwareAgentNs";
    private static final boolean VDBG = false;
    private MessageDigest mDigester;
    private Set mNetworkSpecifiers;

}
