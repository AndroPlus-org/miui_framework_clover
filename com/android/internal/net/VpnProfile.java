// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class VpnProfile
    implements Cloneable, Parcelable
{

    public VpnProfile(Parcel parcel)
    {
        boolean flag = true;
        super();
        name = "";
        type = 0;
        server = "";
        username = "";
        password = "";
        dnsServers = "";
        searchDomains = "";
        routes = "";
        mppe = true;
        l2tpSecret = "";
        ipsecIdentifier = "";
        ipsecSecret = "";
        ipsecUserCert = "";
        ipsecCaCert = "";
        ipsecServerCert = "";
        saveLogin = false;
        key = parcel.readString();
        name = parcel.readString();
        type = parcel.readInt();
        server = parcel.readString();
        username = parcel.readString();
        password = parcel.readString();
        dnsServers = parcel.readString();
        searchDomains = parcel.readString();
        routes = parcel.readString();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mppe = flag1;
        l2tpSecret = parcel.readString();
        ipsecIdentifier = parcel.readString();
        ipsecSecret = parcel.readString();
        ipsecUserCert = parcel.readString();
        ipsecCaCert = parcel.readString();
        ipsecServerCert = parcel.readString();
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        saveLogin = flag1;
    }

    public VpnProfile(String s)
    {
        name = "";
        type = 0;
        server = "";
        username = "";
        password = "";
        dnsServers = "";
        searchDomains = "";
        routes = "";
        mppe = true;
        l2tpSecret = "";
        ipsecIdentifier = "";
        ipsecSecret = "";
        ipsecUserCert = "";
        ipsecCaCert = "";
        ipsecServerCert = "";
        saveLogin = false;
        key = s;
    }

    public static VpnProfile decode(String s, byte abyte0[])
    {
        boolean flag;
        flag = true;
        if(s == null)
            return null;
        String as[];
        String s1 = JVM INSTR new #112 <Class String>;
        s1.String(abyte0, StandardCharsets.UTF_8);
        as = s1.split("\0", -1);
        if(as.length < 14 || as.length > 15)
            return null;
        abyte0 = JVM INSTR new #2   <Class VpnProfile>;
        abyte0.VpnProfile(s);
        abyte0.name = as[0];
        abyte0.type = Integer.parseInt(as[1]);
        if(((VpnProfile) (abyte0)).type < 0 || ((VpnProfile) (abyte0)).type > 5)
            return null;
        abyte0.server = as[2];
        abyte0.username = as[3];
        abyte0.password = as[4];
        abyte0.dnsServers = as[5];
        abyte0.searchDomains = as[6];
        abyte0.routes = as[7];
        abyte0.mppe = Boolean.parseBoolean(as[8]);
        abyte0.l2tpSecret = as[9];
        abyte0.ipsecIdentifier = as[10];
        abyte0.ipsecSecret = as[11];
        abyte0.ipsecUserCert = as[12];
        abyte0.ipsecCaCert = as[13];
        if(as.length > 14)
            s = as[14];
        else
            s = "";
        try
        {
            abyte0.ipsecServerCert = s;
            if(((VpnProfile) (abyte0)).username.isEmpty())
                flag = ((VpnProfile) (abyte0)).password.isEmpty() ^ true;
            abyte0.saveLogin = flag;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        return abyte0;
    }

    public boolean areDnsAddressesNumeric()
    {
        String as[];
        int i;
        int j;
        try
        {
            as = dnsServers.split(" +");
            i = as.length;
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            return false;
        }
        j = 0;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        InetAddress.parseNumericAddress(as[j]);
        j++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_15;
_L1:
        return true;
    }

    public int describeContents()
    {
        return 0;
    }

    public byte[] encode()
    {
        StringBuilder stringbuilder = new StringBuilder(name);
        stringbuilder.append('\0').append(type);
        stringbuilder.append('\0').append(server);
        StringBuilder stringbuilder1 = stringbuilder.append('\0');
        String s;
        if(saveLogin)
            s = username;
        else
            s = "";
        stringbuilder1.append(s);
        stringbuilder1 = stringbuilder.append('\0');
        if(saveLogin)
            s = password;
        else
            s = "";
        stringbuilder1.append(s);
        stringbuilder.append('\0').append(dnsServers);
        stringbuilder.append('\0').append(searchDomains);
        stringbuilder.append('\0').append(routes);
        stringbuilder.append('\0').append(mppe);
        stringbuilder.append('\0').append(l2tpSecret);
        stringbuilder.append('\0').append(ipsecIdentifier);
        stringbuilder.append('\0').append(ipsecSecret);
        stringbuilder.append('\0').append(ipsecUserCert);
        stringbuilder.append('\0').append(ipsecCaCert);
        stringbuilder.append('\0').append(ipsecServerCert);
        return stringbuilder.toString().getBytes(StandardCharsets.UTF_8);
    }

    public boolean hasDns()
    {
        return TextUtils.isEmpty(dnsServers) ^ true;
    }

    public boolean isServerAddressNumeric()
    {
        try
        {
            InetAddress.parseNumericAddress(server);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            return false;
        }
        return true;
    }

    public boolean isTypeValidForLockdown()
    {
        boolean flag = false;
        if(type != 0)
            flag = true;
        return flag;
    }

    public boolean isValidLockdownProfile()
    {
        boolean flag;
        if(isTypeValidForLockdown() && isServerAddressNumeric() && hasDns())
            flag = areDnsAddressesNumeric();
        else
            flag = false;
        return flag;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeString(key);
        parcel.writeString(name);
        parcel.writeInt(type);
        parcel.writeString(server);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(dnsServers);
        parcel.writeString(searchDomains);
        parcel.writeString(routes);
        if(mppe)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeString(l2tpSecret);
        parcel.writeString(ipsecIdentifier);
        parcel.writeString(ipsecSecret);
        parcel.writeString(ipsecUserCert);
        parcel.writeString(ipsecCaCert);
        parcel.writeString(ipsecServerCert);
        if(saveLogin)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public VpnProfile createFromParcel(Parcel parcel)
        {
            return new VpnProfile(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public VpnProfile[] newArray(int i)
        {
            return new VpnProfile[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "VpnProfile";
    public static final int TYPE_IPSEC_HYBRID_RSA = 5;
    public static final int TYPE_IPSEC_XAUTH_PSK = 3;
    public static final int TYPE_IPSEC_XAUTH_RSA = 4;
    public static final int TYPE_L2TP_IPSEC_PSK = 1;
    public static final int TYPE_L2TP_IPSEC_RSA = 2;
    public static final int TYPE_MAX = 5;
    public static final int TYPE_PPTP = 0;
    public String dnsServers;
    public String ipsecCaCert;
    public String ipsecIdentifier;
    public String ipsecSecret;
    public String ipsecServerCert;
    public String ipsecUserCert;
    public final String key;
    public String l2tpSecret;
    public boolean mppe;
    public String name;
    public String password;
    public String routes;
    public boolean saveLogin;
    public String searchDomains;
    public String server;
    public int type;
    public String username;

}
