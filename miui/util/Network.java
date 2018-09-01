// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.*;
import java.net.URL;
import miui.os.Environment;
import org.json.JSONObject;

public class Network
{

    public Network()
    {
    }

    public static JSONObject doHttpPostWithResponseStatus(Context context, String s, String s1)
    {
        if(context == null)
            throw new IllegalArgumentException("context");
        if(s == null || s.trim().length() == 0)
            throw new IllegalArgumentException("url");
        else
            return new JSONObject();
    }

    public static String getCMWapUrl(URL url)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(url.getProtocol()).append("://").append("10.0.0.172").append(url.getPath());
        if(!TextUtils.isEmpty(url.getQuery()))
            stringbuilder.append("?").append(url.getQuery());
        return stringbuilder.toString();
    }

    public static String getOperatorType()
    {
        String s;
        Object obj;
        String s1;
        s = "";
        obj = null;
        s1 = null;
        Object obj2;
        File file = JVM INSTR new #101 <Class File>;
        file.File(Environment.getMiuiDataDirectory(), "ot");
        obj2 = JVM INSTR new #112 <Class BufferedReader>;
        FileReader filereader = JVM INSTR new #114 <Class FileReader>;
        filereader.FileReader(file);
        ((BufferedReader) (obj2)).BufferedReader(filereader);
        s1 = ((BufferedReader) (obj2)).readLine();
        if(obj2 != null)
            try
            {
                ((BufferedReader) (obj2)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj2) { }
_L2:
        return s1;
        obj2;
        obj2 = s1;
_L5:
        s1 = s;
        if(obj2 == null) goto _L2; else goto _L1
_L1:
        ((BufferedReader) (obj2)).close();
        s1 = s;
          goto _L2
        obj2;
        s1 = s;
          goto _L2
        Object obj1;
        obj1;
        obj2 = obj;
_L4:
        if(obj2 != null)
            try
            {
                ((BufferedReader) (obj2)).close();
            }
            catch(IOException ioexception) { }
        throw obj1;
        obj1;
        if(true) goto _L4; else goto _L3
_L3:
        obj1;
          goto _L5
    }

    public static boolean isCmwap(Context context)
    {
        if(!"CN".equalsIgnoreCase(((TelephonyManager)context.getSystemService("phone")).getSimCountryIso()))
            return false;
        context = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        if(context == null)
            return false;
        for(context = context.getExtraInfo(); TextUtils.isEmpty(context) || context.length() < 3 || context.contains("ctwap");)
            return false;

        return context.regionMatches(true, context.length() - 3, "wap", 0, 3);
    }

    public static boolean isNetworkConnected(Context context)
    {
        if(context != null)
        {
            context = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
            if(context != null)
                return context.isAvailable();
        }
        return false;
    }

    public static final String CMWAP_GATEWAY = "10.0.0.172";
    public static final String CMWAP_HEADER_HOST_KEY = "X-Online-Host";
    public static final int CONNECTION_TIMEOUT = 10000;
    private static final String LogTag = miui/util/Network.getSimpleName();
    public static final String OPERATOR_TYPE_FILE_NAME = "ot";
    public static final int READ_TIMEOUT = 15000;
    public static final String RESPONSE_BODY = "RESPONSE_BODY";
    public static final String RESPONSE_CODE = "RESPONSE_CODE";

}
