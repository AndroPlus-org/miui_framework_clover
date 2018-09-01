// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Locale;

// Referenced classes of package android.net:
//            Uri, Proxy

public class ProxyInfo
    implements Parcelable
{

    public ProxyInfo(ProxyInfo proxyinfo)
    {
        if(proxyinfo != null)
        {
            mHost = proxyinfo.getHost();
            mPort = proxyinfo.getPort();
            mPacFileUrl = proxyinfo.mPacFileUrl;
            mExclusionList = proxyinfo.getExclusionListAsString();
            mParsedExclusionList = proxyinfo.mParsedExclusionList;
        } else
        {
            mPacFileUrl = Uri.EMPTY;
        }
    }

    public ProxyInfo(Uri uri)
    {
        mHost = "localhost";
        mPort = -1;
        setExclusionList("");
        if(uri == null)
        {
            throw new NullPointerException();
        } else
        {
            mPacFileUrl = uri;
            return;
        }
    }

    public ProxyInfo(Uri uri, int i)
    {
        mHost = "localhost";
        mPort = i;
        setExclusionList("");
        if(uri == null)
        {
            throw new NullPointerException();
        } else
        {
            mPacFileUrl = uri;
            return;
        }
    }

    public ProxyInfo(String s)
    {
        mHost = "localhost";
        mPort = -1;
        setExclusionList("");
        mPacFileUrl = Uri.parse(s);
    }

    public ProxyInfo(String s, int i, String s1)
    {
        mHost = s;
        mPort = i;
        setExclusionList(s1);
        mPacFileUrl = Uri.EMPTY;
    }

    private ProxyInfo(String s, int i, String s1, String as[])
    {
        mHost = s;
        mPort = i;
        mExclusionList = s1;
        mParsedExclusionList = as;
        mPacFileUrl = Uri.EMPTY;
    }

    ProxyInfo(String s, int i, String s1, String as[], ProxyInfo proxyinfo)
    {
        this(s, i, s1, as);
    }

    public static ProxyInfo buildDirectProxy(String s, int i)
    {
        return new ProxyInfo(s, i, null);
    }

    public static ProxyInfo buildDirectProxy(String s, int i, List list)
    {
        list = (String[])list.toArray(new String[list.size()]);
        return new ProxyInfo(s, i, TextUtils.join(",", list), list);
    }

    public static ProxyInfo buildPacProxy(Uri uri)
    {
        return new ProxyInfo(uri);
    }

    private void setExclusionList(String s)
    {
        mExclusionList = s;
        if(mExclusionList == null)
            mParsedExclusionList = new String[0];
        else
            mParsedExclusionList = s.toLowerCase(Locale.ROOT).split(",");
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(!(obj instanceof ProxyInfo))
            return false;
        obj = (ProxyInfo)obj;
        if(!Uri.EMPTY.equals(mPacFileUrl))
        {
            if(!mPacFileUrl.equals(((ProxyInfo) (obj)).getPacFileUrl()) || mPort != ((ProxyInfo) (obj)).mPort)
                flag = false;
            return flag;
        }
        if(!Uri.EMPTY.equals(((ProxyInfo) (obj)).mPacFileUrl))
            return false;
        if(mExclusionList != null && mExclusionList.equals(((ProxyInfo) (obj)).getExclusionListAsString()) ^ true)
            return false;
        if(mHost != null && ((ProxyInfo) (obj)).getHost() != null && !mHost.equals(((ProxyInfo) (obj)).getHost()))
            return false;
        if(mHost != null && ((ProxyInfo) (obj)).mHost == null)
            return false;
        if(mHost == null && ((ProxyInfo) (obj)).mHost != null)
            return false;
        return mPort == ((ProxyInfo) (obj)).mPort;
    }

    public String[] getExclusionList()
    {
        return mParsedExclusionList;
    }

    public String getExclusionListAsString()
    {
        return mExclusionList;
    }

    public String getHost()
    {
        return mHost;
    }

    public Uri getPacFileUrl()
    {
        return mPacFileUrl;
    }

    public int getPort()
    {
        return mPort;
    }

    public InetSocketAddress getSocketAddress()
    {
        InetSocketAddress inetsocketaddress = null;
        InetSocketAddress inetsocketaddress1;
        inetsocketaddress1 = JVM INSTR new #145 <Class InetSocketAddress>;
        inetsocketaddress1.InetSocketAddress(mHost, mPort);
        inetsocketaddress = inetsocketaddress1;
_L2:
        return inetsocketaddress;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        if(mHost == null)
            j = 0;
        else
            j = mHost.hashCode();
        if(mExclusionList != null)
            i = mExclusionList.hashCode();
        return j + i + mPort;
    }

    public boolean isValid()
    {
        if(!Uri.EMPTY.equals(mPacFileUrl))
            return true;
        String s;
        String s1;
        String s2;
        boolean flag;
        if(mHost == null)
            s = "";
        else
            s = mHost;
        if(mPort == 0)
            s1 = "";
        else
            s1 = Integer.toString(mPort);
        if(mExclusionList == null)
            s2 = "";
        else
            s2 = mExclusionList;
        if(Proxy.validate(s, s1, s2) == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public Proxy makeProxy()
    {
        Proxy proxy = Proxy.NO_PROXY;
        Proxy proxy1 = proxy;
        if(mHost != null)
            try
            {
                InetSocketAddress inetsocketaddress = JVM INSTR new #145 <Class InetSocketAddress>;
                inetsocketaddress.InetSocketAddress(mHost, mPort);
                proxy1 = JVM INSTR new #169 <Class Proxy>;
                proxy1.Proxy(java.net.Proxy.Type.HTTP, inetsocketaddress);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                illegalargumentexception = proxy;
            }
        return proxy1;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if(!Uri.EMPTY.equals(mPacFileUrl))
        {
            stringbuilder.append("PAC Script: ");
            stringbuilder.append(mPacFileUrl);
        }
        if(mHost != null)
        {
            stringbuilder.append("[");
            stringbuilder.append(mHost);
            stringbuilder.append("] ");
            stringbuilder.append(Integer.toString(mPort));
            if(mExclusionList != null)
                stringbuilder.append(" xl=").append(mExclusionList);
        } else
        {
            stringbuilder.append("[ProxyProperties.mHost == null]");
        }
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(!Uri.EMPTY.equals(mPacFileUrl))
        {
            parcel.writeByte((byte)1);
            mPacFileUrl.writeToParcel(parcel, 0);
            parcel.writeInt(mPort);
            return;
        }
        parcel.writeByte((byte)0);
        if(mHost != null)
        {
            parcel.writeByte((byte)1);
            parcel.writeString(mHost);
            parcel.writeInt(mPort);
        } else
        {
            parcel.writeByte((byte)0);
        }
        parcel.writeString(mExclusionList);
        parcel.writeStringArray(mParsedExclusionList);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ProxyInfo createFromParcel(Parcel parcel)
        {
            String s = null;
            int i = 0;
            if(parcel.readByte() != 0)
                return new ProxyInfo((Uri)Uri.CREATOR.createFromParcel(parcel), parcel.readInt());
            if(parcel.readByte() != 0)
            {
                s = parcel.readString();
                i = parcel.readInt();
            }
            return new ProxyInfo(s, i, parcel.readString(), parcel.readStringArray(), null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ProxyInfo[] newArray(int i)
        {
            return new ProxyInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String LOCAL_EXCL_LIST = "";
    public static final String LOCAL_HOST = "localhost";
    public static final int LOCAL_PORT = -1;
    private String mExclusionList;
    private String mHost;
    private Uri mPacFileUrl;
    private String mParsedExclusionList[];
    private int mPort;

}
