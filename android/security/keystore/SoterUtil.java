// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keystore;

import android.util.Base64;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package android.security.keystore:
//            SoterRSAKeyGenParameterSpec

public class SoterUtil
{

    public SoterUtil()
    {
    }

    private static boolean contains(String s, String as[])
    {
        while(as == null || as.length == 0 || isNullOrNil(s)) 
        {
            Log.e("Soter.Util", "hy: param error");
            throw new IllegalArgumentException("param error");
        }
        int i = as.length;
        for(int j = 0; j < i; j++)
            if(s.equals(as[j]))
                return true;

        return false;
    }

    private static String containsPrefix(String s, String as[])
    {
        int i;
        for(i = 0; as == null || as.length == 0 || isNullOrNil(s);)
        {
            Log.e("Soter.Util", "hy: param error");
            throw new IllegalArgumentException("param error");
        }

        for(int j = as.length; i < j; i++)
        {
            String s1 = as[i];
            if(!isNullOrNil(s1) && s1.startsWith(s))
                return s1;
        }

        return null;
    }

    public static SoterRSAKeyGenParameterSpec convertKeyNameToParameterSpec(String s)
    {
        String as[];
        boolean flag;
        boolean flag1;
        String s1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        if(isNullOrNil(s))
        {
            Log.e("Soter.Util", "hy: null or nil when convert key name to parameter");
            return null;
        }
        as = s.split("\\.");
        if(as == null || as.length <= 1)
        {
            Log.w("Soter.Util", "hy: pure alias, no parameter");
            return null;
        }
        flag = false;
        flag1 = false;
        s1 = "";
        flag2 = false;
        flag3 = false;
        flag4 = false;
        if(!contains("auto_signed_when_get_pubkey_attk", as)) goto _L2; else goto _L1
_L1:
        boolean flag5;
        boolean flag6;
        flag5 = true;
        s = s1;
        flag6 = flag1;
_L4:
        if(contains("secmsg_and_counter_signed_when_sign", as))
            flag2 = true;
        flag1 = flag4;
        if(contains("addcounter", as))
        {
            flag = true;
            flag3 = flag;
            flag1 = flag4;
            if(contains("next_attk", as))
            {
                flag1 = true;
                flag3 = flag;
            }
        }
        s = new SoterRSAKeyGenParameterSpec(true, flag5, flag6, s, flag2, flag3, flag1);
        Log.i("Soter.Util", (new StringBuilder()).append("hy: spec: ").append(s.toString()).toString());
        return s;
_L2:
        String s2 = containsPrefix("auto_signed_when_get_pubkey", as);
        flag5 = flag;
        flag6 = flag1;
        s = s1;
        if(!isNullOrNil(s2))
        {
            s2 = retrieveKeyNameFromExpr(s2);
            flag5 = flag;
            flag6 = flag1;
            s = s1;
            if(!isNullOrNil(s2))
            {
                flag6 = true;
                s = s2;
                flag5 = flag;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static byte[] getDataFromRaw(byte abyte0[], String s)
        throws JSONException
    {
        if(isNullOrNil(s))
        {
            Log.e("Soter", "hy: json keyname error");
            return null;
        }
        if(abyte0 == null)
        {
            Log.e("Soter", "hy: json origin null");
            return null;
        }
        abyte0 = retriveJsonFromExportedData(abyte0);
        if(abyte0 != null && abyte0.has(s))
        {
            abyte0 = abyte0.getString(s);
            Log.d("Soter", (new StringBuilder()).append("base64 encoded public key: ").append(abyte0).toString());
            abyte0 = abyte0.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "").replace("\\n", "");
            Log.d("Soter", (new StringBuilder()).append("pure base64 encoded public key: ").append(abyte0).toString());
            return Base64.decode(abyte0, 0);
        } else
        {
            return null;
        }
    }

    public static String getPureKeyAliasFromKeyName(String s)
    {
        Log.i("Soter.Util", (new StringBuilder()).append("hy: retrieving pure name from: ").append(s).toString());
        if(isNullOrNil(s))
        {
            Log.e("Soter.Util", "hy: null or nil when get pure key alias");
            return null;
        }
        String as[] = s.split("\\.");
        if(as == null || as.length <= 1)
        {
            Log.d("Soter.Util", "hy: pure alias");
            return s;
        } else
        {
            return as[0];
        }
    }

    public static boolean isNullOrNil(String s)
    {
        boolean flag;
        if(s != null)
            flag = s.equals("");
        else
            flag = true;
        return flag;
    }

    private static String retrieveKeyNameFromExpr(String s)
    {
        if(!isNullOrNil(s))
        {
            int i = s.indexOf("(");
            int j = s.indexOf(")");
            if(i >= 0 && j > i)
            {
                return s.substring(i + 1, j);
            } else
            {
                Log.e("Soter.Util", "hy: no key name");
                return null;
            }
        } else
        {
            Log.e("Soter.Util", "hy: expr is null");
            return null;
        }
    }

    private static JSONObject retriveJsonFromExportedData(byte abyte0[])
    {
        if(abyte0 == null)
        {
            Log.e("Soter", "raw data is null");
            return null;
        }
        if(abyte0.length < 4)
            Log.e("Soter", "raw data length smaller than 4");
        byte abyte1[] = new byte[4];
        System.arraycopy(abyte0, 0, abyte1, 0, 4);
        int i = toInt(abyte1);
        Log.d("Soter", (new StringBuilder()).append("parsed raw length: ").append(i).toString());
        if(i > 0x100000)
        {
            Log.e("Soter", "exceed max json length. return null");
            return null;
        }
        abyte1 = new byte[i];
        if(abyte0.length <= i + 4)
        {
            Log.e("Soter", "length not correct 2");
            return null;
        }
        System.arraycopy(abyte0, 4, abyte1, 0, i);
        abyte0 = new String(abyte1);
        Log.d("Soter", (new StringBuilder()).append("to convert json: ").append(abyte0).toString());
        try
        {
            abyte0 = new JSONObject(abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Log.e("Soter", "hy: can not convert to json");
            return null;
        }
        return abyte0;
    }

    public static int toInt(byte abyte0[])
    {
        int i = 0;
        for(int j = 0; j < abyte0.length; j++)
            i += (abyte0[j] & 0xff) << j * 8;

        return i;
    }

    public static final String JSON_KEY_PUBLIC = "pub_key";
    private static final int MAX_JSON_LENG = 0x100000;
    private static final String PARAM_NEED_AUTO_ADD_COUNTER_WHEN_GET_PUBLIC_KEY = "addcounter";
    private static final String PARAM_NEED_AUTO_ADD_SECMSG_FID_COUNTER_WHEN_SIGN = "secmsg_and_counter_signed_when_sign";
    private static final String PARAM_NEED_AUTO_SIGNED_WITH_ATTK_WHEN_GET_PUBLIC_KEY = "auto_signed_when_get_pubkey_attk";
    private static final String PARAM_NEED_AUTO_SIGNED_WITH_COMMON_KEY_WHEN_GET_PUBLIC_KEY = "auto_signed_when_get_pubkey";
    private static final String PARAM_NEED_NEXT_ATTK = "next_attk";
    private static final int RAW_LENGTH_PREFIX = 4;
    public static final String TAG = "Soter.Util";
}
