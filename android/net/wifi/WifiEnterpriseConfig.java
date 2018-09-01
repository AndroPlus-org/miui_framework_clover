// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.*;

// Referenced classes of package android.net.wifi:
//            ParcelUtil

public class WifiEnterpriseConfig
    implements Parcelable
{
    public static final class Eap
    {

        public static final int AKA = 5;
        public static final int AKA_PRIME = 6;
        public static final int NONE = -1;
        public static final int PEAP = 0;
        public static final int PWD = 3;
        public static final int SIM = 4;
        public static final int TLS = 1;
        public static final int TTLS = 2;
        public static final int UNAUTH_TLS = 7;
        public static final String strings[] = {
            "PEAP", "TLS", "TTLS", "PWD", "SIM", "AKA", "AKA'", "WFA-UNAUTH-TLS"
        };


        private Eap()
        {
        }
    }

    public static final class Phase2
    {

        public static final int AKA = 6;
        public static final int AKA_PRIME = 7;
        private static final String AUTHEAP_PREFIX = "autheap=";
        private static final String AUTH_PREFIX = "auth=";
        public static final int GTC = 4;
        public static final int MSCHAP = 2;
        public static final int MSCHAPV2 = 3;
        public static final int NONE = 0;
        public static final int PAP = 1;
        public static final int SIM = 5;
        public static final String strings[] = {
            "NULL", "PAP", "MSCHAP", "MSCHAPV2", "GTC", "SIM", "AKA", "AKA'"
        };


        private Phase2()
        {
        }
    }

    public static interface SupplicantLoader
    {

        public abstract String loadValue(String s);
    }

    public static interface SupplicantSaver
    {

        public abstract boolean saveValue(String s, String s1);
    }


    static HashMap _2D_get0(WifiEnterpriseConfig wifienterpriseconfig)
    {
        return wifienterpriseconfig.mFields;
    }

    static X509Certificate[] _2D_set0(WifiEnterpriseConfig wifienterpriseconfig, X509Certificate ax509certificate[])
    {
        wifienterpriseconfig.mCaCerts = ax509certificate;
        return ax509certificate;
    }

    static X509Certificate[] _2D_set1(WifiEnterpriseConfig wifienterpriseconfig, X509Certificate ax509certificate[])
    {
        wifienterpriseconfig.mClientCertificateChain = ax509certificate;
        return ax509certificate;
    }

    static PrivateKey _2D_set2(WifiEnterpriseConfig wifienterpriseconfig, PrivateKey privatekey)
    {
        wifienterpriseconfig.mClientPrivateKey = privatekey;
        return privatekey;
    }

    static int _2D_set3(WifiEnterpriseConfig wifienterpriseconfig, int i)
    {
        wifienterpriseconfig.mEapMethod = i;
        return i;
    }

    static int _2D_set4(WifiEnterpriseConfig wifienterpriseconfig, int i)
    {
        wifienterpriseconfig.mPhase2Method = i;
        return i;
    }

    public WifiEnterpriseConfig()
    {
        mFields = new HashMap();
        mEapMethod = -1;
        mPhase2Method = 0;
    }

    public WifiEnterpriseConfig(WifiEnterpriseConfig wifienterpriseconfig)
    {
        mFields = new HashMap();
        mEapMethod = -1;
        mPhase2Method = 0;
        copyFrom(wifienterpriseconfig, false, "");
    }

    private String convertToQuotedString(String s)
    {
        return (new StringBuilder()).append("\"").append(s).append("\"").toString();
    }

    private void copyFrom(WifiEnterpriseConfig wifienterpriseconfig, boolean flag, String s)
    {
        Iterator iterator = wifienterpriseconfig.mFields.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            String s1 = (String)iterator.next();
            if(!flag || !s1.equals("password") || !TextUtils.equals((CharSequence)wifienterpriseconfig.mFields.get(s1), s))
                mFields.put(s1, (String)wifienterpriseconfig.mFields.get(s1));
        } while(true);
        if(wifienterpriseconfig.mCaCerts != null)
            mCaCerts = (X509Certificate[])Arrays.copyOf(wifienterpriseconfig.mCaCerts, wifienterpriseconfig.mCaCerts.length);
        else
            mCaCerts = null;
        mClientPrivateKey = wifienterpriseconfig.mClientPrivateKey;
        if(wifienterpriseconfig.mClientCertificateChain != null)
            mClientCertificateChain = (X509Certificate[])Arrays.copyOf(wifienterpriseconfig.mClientCertificateChain, wifienterpriseconfig.mClientCertificateChain.length);
        else
            mClientCertificateChain = null;
        mEapMethod = wifienterpriseconfig.mEapMethod;
        mPhase2Method = wifienterpriseconfig.mPhase2Method;
    }

    public static String decodeCaCertificateAlias(String s)
    {
        byte abyte0[] = new byte[s.length() >> 1];
        int i = 0;
        for(int j = 0; i < s.length(); j++)
        {
            abyte0[j] = (byte)Integer.parseInt(s.substring(i, i + 2), 16);
            i += 2;
        }

        String s1;
        try
        {
            s1 = new String(abyte0, StandardCharsets.UTF_8);
        }
        catch(NumberFormatException numberformatexception)
        {
            numberformatexception.printStackTrace();
            return s;
        }
        return s1;
    }

    public static String encodeCaCertificateAlias(String s)
    {
        byte abyte0[] = s.getBytes(StandardCharsets.UTF_8);
        s = new StringBuilder(abyte0.length * 2);
        int i = abyte0.length;
        for(int j = 0; j < i; j++)
            s.append(String.format("%02x", new Object[] {
                Integer.valueOf(abyte0[j] & 0xff)
            }));

        return s.toString();
    }

    private String getFieldValue(String s, String s1)
    {
        s = (String)mFields.get(s);
        if(TextUtils.isEmpty(s) || "NULL".equals(s))
            return "";
        s = removeDoubleQuotes(s);
        if(s.startsWith(s1))
            return s.substring(s1.length());
        else
            return s;
    }

    private int getStringIndex(String as[], String s, int i)
    {
        if(TextUtils.isEmpty(s))
            return i;
        for(int j = 0; j < as.length; j++)
            if(s.equals(as[j]))
                return j;

        return i;
    }

    private boolean isEapMethodValid()
    {
        if(mEapMethod == -1)
        {
            Log.e("WifiEnterpriseConfig", "WiFi enterprise configuration is invalid as it supplies no EAP method.");
            return false;
        }
        if(mEapMethod < 0 || mEapMethod >= Eap.strings.length)
        {
            Log.e("WifiEnterpriseConfig", (new StringBuilder()).append("mEapMethod is invald for WiFi enterprise configuration: ").append(mEapMethod).toString());
            return false;
        }
        if(mPhase2Method < 0 || mPhase2Method >= Phase2.strings.length)
        {
            Log.e("WifiEnterpriseConfig", (new StringBuilder()).append("mPhase2Method is invald for WiFi enterprise configuration: ").append(mPhase2Method).toString());
            return false;
        } else
        {
            return true;
        }
    }

    private String removeDoubleQuotes(String s)
    {
        if(TextUtils.isEmpty(s))
            return "";
        int i = s.length();
        if(i > 1 && s.charAt(0) == '"' && s.charAt(i - 1) == '"')
            return s.substring(1, i - 1);
        else
            return s;
    }

    private void setFieldValue(String s, String s1, String s2)
    {
        if(TextUtils.isEmpty(s1))
        {
            mFields.put(s, "NULL");
        } else
        {
            if(!UNQUOTED_KEYS.contains(s))
                s1 = convertToQuotedString((new StringBuilder()).append(s2).append(s1).toString());
            else
                s1 = (new StringBuilder()).append(s2).append(s1).toString();
            mFields.put(s, s1);
        }
    }

    public void copyFromExternal(WifiEnterpriseConfig wifienterpriseconfig, String s)
    {
        copyFrom(wifienterpriseconfig, true, convertToQuotedString(s));
    }

    public int describeContents()
    {
        return 0;
    }

    public String getAltSubjectMatch()
    {
        return getFieldValue("altsubject_match");
    }

    public String getAnonymousIdentity()
    {
        return getFieldValue("anonymous_identity");
    }

    public X509Certificate getCaCertificate()
    {
        if(mCaCerts != null && mCaCerts.length > 0)
            return mCaCerts[0];
        else
            return null;
    }

    public String getCaCertificateAlias()
    {
        return getFieldValue("ca_cert", "keystore://CACERT_");
    }

    public String[] getCaCertificateAliases()
    {
        String as[] = null;
        String s = getFieldValue("ca_cert");
        if(s.startsWith("keystore://CACERT_"))
            return (new String[] {
                getFieldValue("ca_cert", "keystore://CACERT_")
            });
        if(s.startsWith("keystores://"))
        {
            as = TextUtils.split(s.substring("keystores://".length()), " ");
            for(int i = 0; i < as.length; i++)
            {
                as[i] = decodeCaCertificateAlias(as[i]);
                if(as[i].startsWith("CACERT_"))
                    as[i] = as[i].substring("CACERT_".length());
            }

            if(as.length == 0)
                as = null;
            return as;
        }
        if(!TextUtils.isEmpty(s))
        {
            as = new String[1];
            as[0] = s;
        }
        return as;
    }

    public X509Certificate[] getCaCertificates()
    {
        if(mCaCerts != null && mCaCerts.length > 0)
            return mCaCerts;
        else
            return null;
    }

    public String getCaPath()
    {
        return getFieldValue("ca_path");
    }

    public X509Certificate getClientCertificate()
    {
        if(mClientCertificateChain != null && mClientCertificateChain.length > 0)
            return mClientCertificateChain[0];
        else
            return null;
    }

    public String getClientCertificateAlias()
    {
        return getFieldValue("client_cert", "keystore://USRCERT_");
    }

    public X509Certificate[] getClientCertificateChain()
    {
        if(mClientCertificateChain != null && mClientCertificateChain.length > 0)
            return mClientCertificateChain;
        else
            return null;
    }

    public PrivateKey getClientPrivateKey()
    {
        return mClientPrivateKey;
    }

    public String getDomainSuffixMatch()
    {
        return getFieldValue("domain_suffix_match");
    }

    public int getEapMethod()
    {
        return mEapMethod;
    }

    public String getFieldValue(String s)
    {
        return getFieldValue(s, "");
    }

    public String getIdentity()
    {
        return getFieldValue("identity");
    }

    public String getKeyId(WifiEnterpriseConfig wifienterpriseconfig)
    {
        if(mEapMethod == -1)
        {
            if(wifienterpriseconfig != null)
                wifienterpriseconfig = wifienterpriseconfig.getKeyId(null);
            else
                wifienterpriseconfig = "NULL";
            return wifienterpriseconfig;
        }
        if(!isEapMethodValid())
            return "NULL";
        else
            return (new StringBuilder()).append(Eap.strings[mEapMethod]).append("_").append(Phase2.strings[mPhase2Method]).toString();
    }

    public String getPassword()
    {
        return getFieldValue("password");
    }

    public int getPhase2Method()
    {
        return mPhase2Method;
    }

    public String getPlmn()
    {
        return getFieldValue("plmn");
    }

    public String getRealm()
    {
        return getFieldValue("realm");
    }

    public String getSimNum()
    {
        return getFieldValue("sim_num");
    }

    public String getSubjectMatch()
    {
        return getFieldValue("subject_match");
    }

    public void loadFromSupplicant(SupplicantLoader supplicantloader)
    {
        String s2;
        String as[] = SUPPLICANT_CONFIG_KEYS;
        int i = as.length;
        int j = 0;
        while(j < i) 
        {
            String s = as[j];
            String s1 = supplicantloader.loadValue(s);
            if(s1 == null)
                mFields.put(s, "NULL");
            else
                mFields.put(s, s1);
            j++;
        }
        s2 = supplicantloader.loadValue("eap");
        mEapMethod = getStringIndex(Eap.strings, s2, -1);
        s2 = removeDoubleQuotes(supplicantloader.loadValue("phase2"));
        if(!s2.startsWith("auth=")) goto _L2; else goto _L1
_L1:
        supplicantloader = s2.substring("auth=".length());
_L4:
        mPhase2Method = getStringIndex(Phase2.strings, supplicantloader, 0);
        return;
_L2:
        supplicantloader = s2;
        if(s2.startsWith("autheap="))
            supplicantloader = s2.substring("autheap=".length());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void resetCaCertificate()
    {
        mCaCerts = null;
    }

    public void resetClientKeyEntry()
    {
        mClientPrivateKey = null;
        mClientCertificateChain = null;
    }

    public boolean saveToSupplicant(SupplicantSaver supplicantsaver)
    {
        if(!isEapMethodValid())
            return false;
        boolean flag;
        Iterator iterator;
        if(mEapMethod == 4 || mEapMethod == 5)
            flag = true;
        else
        if(mEapMethod == 6)
            flag = true;
        else
            flag = false;
        for(iterator = mFields.keySet().iterator(); iterator.hasNext();)
        {
            String s = (String)iterator.next();
            if((!flag || !"anonymous_identity".equals(s)) && !supplicantsaver.saveValue(s, (String)mFields.get(s)))
                return false;
        }

        if(!supplicantsaver.saveValue("eap", Eap.strings[mEapMethod]))
            return false;
        if(mEapMethod != 1 && mPhase2Method != 0)
        {
            boolean flag1;
            String s1;
            if(mEapMethod == 2 && mPhase2Method == 4)
                flag1 = true;
            else
                flag1 = false;
            if(flag1)
                s1 = "autheap=";
            else
                s1 = "auth=";
            return supplicantsaver.saveValue("phase2", convertToQuotedString((new StringBuilder()).append(s1).append(Phase2.strings[mPhase2Method]).toString()));
        }
        if(mPhase2Method == 0)
        {
            return supplicantsaver.saveValue("phase2", null);
        } else
        {
            Log.e("WifiEnterpriseConfig", "WiFi enterprise configuration is invalid as it supplies a phase 2 method but the phase1 method does not support it.");
            return false;
        }
    }

    public void setAltSubjectMatch(String s)
    {
        setFieldValue("altsubject_match", s);
    }

    public void setAnonymousIdentity(String s)
    {
        setFieldValue("anonymous_identity", s);
    }

    public void setCaCertificate(X509Certificate x509certificate)
    {
        if(x509certificate == null) goto _L2; else goto _L1
_L1:
        if(x509certificate.getBasicConstraints() < 0) goto _L4; else goto _L3
_L3:
        mCaCerts = (new X509Certificate[] {
            x509certificate
        });
_L6:
        return;
_L4:
        throw new IllegalArgumentException("Not a CA certificate");
_L2:
        mCaCerts = null;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void setCaCertificateAlias(String s)
    {
        setFieldValue("ca_cert", s, "keystore://CACERT_");
    }

    public void setCaCertificateAliases(String as[])
    {
        if(as == null)
            setFieldValue("ca_cert", null, "keystore://CACERT_");
        else
        if(as.length == 1)
        {
            setCaCertificateAlias(as[0]);
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            for(int i = 0; i < as.length; i++)
            {
                if(i > 0)
                    stringbuilder.append(" ");
                stringbuilder.append(encodeCaCertificateAlias((new StringBuilder()).append("CACERT_").append(as[i]).toString()));
            }

            setFieldValue("ca_cert", stringbuilder.toString(), "keystores://");
        }
    }

    public void setCaCertificates(X509Certificate ax509certificate[])
    {
        if(ax509certificate != null)
        {
            X509Certificate ax509certificate1[] = new X509Certificate[ax509certificate.length];
            for(int i = 0; i < ax509certificate.length;)
                if(ax509certificate[i].getBasicConstraints() >= 0)
                {
                    ax509certificate1[i] = ax509certificate[i];
                    i++;
                } else
                {
                    throw new IllegalArgumentException("Not a CA certificate");
                }

            mCaCerts = ax509certificate1;
        } else
        {
            mCaCerts = null;
        }
    }

    public void setCaPath(String s)
    {
        setFieldValue("ca_path", s);
    }

    public void setClientCertificateAlias(String s)
    {
        setFieldValue("client_cert", s, "keystore://USRCERT_");
        setFieldValue("key_id", s, "USRPKEY_");
        if(TextUtils.isEmpty(s))
        {
            setFieldValue("engine", "0");
            setFieldValue("engine_id", "");
        } else
        {
            setFieldValue("engine", "1");
            setFieldValue("engine_id", "keystore");
        }
    }

    public void setClientKeyEntry(PrivateKey privatekey, X509Certificate x509certificate)
    {
        X509Certificate ax509certificate[] = null;
        if(x509certificate != null)
        {
            ax509certificate = new X509Certificate[1];
            ax509certificate[0] = x509certificate;
        }
        setClientKeyEntryWithCertificateChain(privatekey, ax509certificate);
    }

    public void setClientKeyEntryWithCertificateChain(PrivateKey privatekey, X509Certificate ax509certificate[])
    {
        Object obj = null;
        X509Certificate ax509certificate1[] = obj;
        if(ax509certificate != null)
        {
            ax509certificate1 = obj;
            if(ax509certificate.length > 0)
            {
                if(ax509certificate[0].getBasicConstraints() != -1)
                    throw new IllegalArgumentException("First certificate in the chain must be a client end certificate");
                for(int i = 1; i < ax509certificate.length; i++)
                    if(ax509certificate[i].getBasicConstraints() == -1)
                        throw new IllegalArgumentException("All certificates following the first must be CA certificates");

                ax509certificate1 = (X509Certificate[])Arrays.copyOf(ax509certificate, ax509certificate.length);
                if(privatekey == null)
                    throw new IllegalArgumentException("Client cert without a private key");
                if(privatekey.getEncoded() == null)
                    throw new IllegalArgumentException("Private key cannot be encoded");
            }
        }
        mClientPrivateKey = privatekey;
        mClientCertificateChain = ax509certificate1;
    }

    public void setDomainSuffixMatch(String s)
    {
        setFieldValue("domain_suffix_match", s);
    }

    public void setEapMethod(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException("Unknown EAP method");

        case 1: // '\001'
        case 7: // '\007'
            setPhase2Method(0);
            // fall through

        case 0: // '\0'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
            mEapMethod = i;
            break;
        }
        setFieldValue("proactive_key_caching", "1");
    }

    public void setFieldValue(String s, String s1)
    {
        setFieldValue(s, s1, "");
    }

    public void setIdentity(String s)
    {
        setFieldValue("identity", s, "");
    }

    public void setPassword(String s)
    {
        setFieldValue("password", s);
    }

    public void setPhase2Method(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException("Unknown Phase 2 method");

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
            mPhase2Method = i;
            break;
        }
    }

    public void setPlmn(String s)
    {
        setFieldValue("plmn", s);
    }

    public void setRealm(String s)
    {
        setFieldValue("realm", s);
    }

    public void setSimNum(int i)
    {
        setFieldValue("sim_num", Integer.toString(i));
    }

    public void setSubjectMatch(String s)
    {
        setFieldValue("subject_match", s);
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        Iterator iterator = mFields.keySet().iterator();
        while(iterator.hasNext()) 
        {
            String s = (String)iterator.next();
            String s1;
            if("password".equals(s))
                s1 = "<removed>";
            else
                s1 = (String)mFields.get(s);
            stringbuffer.append(s).append(" ").append(s1).append("\n");
        }
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mFields.size());
        java.util.Map.Entry entry;
        for(Iterator iterator = mFields.entrySet().iterator(); iterator.hasNext(); parcel.writeString((String)entry.getValue()))
        {
            entry = (java.util.Map.Entry)iterator.next();
            parcel.writeString((String)entry.getKey());
        }

        parcel.writeInt(mEapMethod);
        parcel.writeInt(mPhase2Method);
        ParcelUtil.writeCertificates(parcel, mCaCerts);
        ParcelUtil.writePrivateKey(parcel, mClientPrivateKey);
        ParcelUtil.writeCertificates(parcel, mClientCertificateChain);
    }

    public static final String ALTSUBJECT_MATCH_KEY = "altsubject_match";
    public static final String ANON_IDENTITY_KEY = "anonymous_identity";
    public static final String CA_CERT_ALIAS_DELIMITER = " ";
    public static final String CA_CERT_KEY = "ca_cert";
    public static final String CA_CERT_PREFIX = "keystore://CACERT_";
    public static final String CA_PATH_KEY = "ca_path";
    public static final String CLIENT_CERT_KEY = "client_cert";
    public static final String CLIENT_CERT_PREFIX = "keystore://USRCERT_";
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiEnterpriseConfig createFromParcel(Parcel parcel)
        {
            WifiEnterpriseConfig wifienterpriseconfig = new WifiEnterpriseConfig();
            int i = parcel.readInt();
            for(int j = 0; j < i; j++)
            {
                String s = parcel.readString();
                String s1 = parcel.readString();
                WifiEnterpriseConfig._2D_get0(wifienterpriseconfig).put(s, s1);
            }

            WifiEnterpriseConfig._2D_set3(wifienterpriseconfig, parcel.readInt());
            WifiEnterpriseConfig._2D_set4(wifienterpriseconfig, parcel.readInt());
            WifiEnterpriseConfig._2D_set0(wifienterpriseconfig, ParcelUtil.readCertificates(parcel));
            WifiEnterpriseConfig._2D_set2(wifienterpriseconfig, ParcelUtil.readPrivateKey(parcel));
            WifiEnterpriseConfig._2D_set1(wifienterpriseconfig, ParcelUtil.readCertificates(parcel));
            return wifienterpriseconfig;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiEnterpriseConfig[] newArray(int i)
        {
            return new WifiEnterpriseConfig[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String DOM_SUFFIX_MATCH_KEY = "domain_suffix_match";
    public static final String EAP_ERP = "eap_erp";
    public static final String EAP_KEY = "eap";
    public static final String EMPTY_VALUE = "NULL";
    public static final String ENGINE_DISABLE = "0";
    public static final String ENGINE_ENABLE = "1";
    public static final String ENGINE_ID_KEY = "engine_id";
    public static final String ENGINE_ID_KEYSTORE = "keystore";
    public static final String ENGINE_KEY = "engine";
    public static final String IDENTITY_KEY = "identity";
    public static final String KEYSTORES_URI = "keystores://";
    public static final String KEYSTORE_URI = "keystore://";
    public static final String KEY_SIMNUM = "sim_num";
    public static final String OPP_KEY_CACHING = "proactive_key_caching";
    public static final String PASSWORD_KEY = "password";
    public static final String PHASE2_KEY = "phase2";
    public static final String PLMN_KEY = "plmn";
    public static final String PRIVATE_KEY_ID_KEY = "key_id";
    public static final String REALM_KEY = "realm";
    public static final String SUBJECT_MATCH_KEY = "subject_match";
    private static final String SUPPLICANT_CONFIG_KEYS[] = {
        "identity", "anonymous_identity", "password", "client_cert", "ca_cert", "subject_match", "engine", "engine_id", "key_id", "altsubject_match", 
        "domain_suffix_match", "ca_path"
    };
    private static final String TAG = "WifiEnterpriseConfig";
    private static final List UNQUOTED_KEYS = Arrays.asList(new String[] {
        "engine", "proactive_key_caching", "eap_erp"
    });
    private X509Certificate mCaCerts[];
    private X509Certificate mClientCertificateChain[];
    private PrivateKey mClientPrivateKey;
    private int mEapMethod;
    private HashMap mFields;
    private int mPhase2Method;

}
