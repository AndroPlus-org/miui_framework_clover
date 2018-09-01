// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.content.Context;
import android.os.Environment;
import android.os.UserHandle;
import android.text.TextUtils;
import java.io.File;
import java.util.*;
import miui.security.SecurityManager;

// Referenced classes of package android.security:
//            FingerprintIdUtilsCompat

public class FingerprintIdUtils
{

    public FingerprintIdUtils()
    {
    }

    public static void deleteFingerprintById(Context context, String s)
    {
        int i = getSecondSpaceId(context);
        HashMap hashmap = getUserFingerprintIdsFromXml(context, i);
        if(hashmap != null)
            hashmap.remove(s);
        writeFingerprintIdsToXml(context, i, hashmap);
    }

    private static String getPathByUserId(int i)
    {
        return (new File(Environment.getUserSystemDirectory(i), "fingerid_user_map.xml")).getAbsolutePath();
    }

    public static int getSecondSpaceId(Context context)
    {
        return android.provider.Settings.Secure.getIntForUser(context.getContentResolver(), "second_user_id", -10000, 0);
    }

    private static SecurityManager getSecurityManager(Context context)
    {
        return (SecurityManager)context.getSystemService("security");
    }

    public static HashMap getUserFingerprintIds(Context context, int i)
    {
        return FingerprintIdUtilsCompat.getValidFingerPrintIds(getUserFingerprintIdsFromXml(context, i), context);
    }

    private static HashMap getUserFingerprintIdsFromXml(Context context, int i)
    {
        return str2Map(getSecurityManager(context).readSystemDataStringFile(getPathByUserId(i)));
    }

    private static String map2Str(HashMap hashmap)
    {
        StringBuilder stringbuilder = new StringBuilder();
        Iterator iterator = hashmap.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            hashmap = (java.util.Map.Entry)iterator.next();
            stringbuilder.append((String)hashmap.getKey());
            stringbuilder.append('=');
            stringbuilder.append((Integer)hashmap.getValue());
            if(iterator.hasNext())
                stringbuilder.append(",");
        } while(true);
        return stringbuilder.toString();
    }

    public static void putUserFingerprintIds(Context context, HashMap hashmap)
    {
        if(hashmap == null)
            return;
        if(UserHandle.myUserId() == getSecondSpaceId(context))
            getSecurityManager(context).putSystemDataStringFile(getPathByUserId(UserHandle.myUserId()), map2Str(hashmap));
    }

    private static HashMap str2Map(String s)
    {
        HashMap hashmap = new HashMap();
        if(!TextUtils.isEmpty(s))
        {
            String as[] = s.split(",");
            if(as != null)
            {
                for(int i = 0; i < as.length; i++)
                {
                    s = as[i];
                    int j = s.indexOf('=');
                    hashmap.put(s.substring(0, j), Integer.valueOf(s.substring(j + 1)));
                }

            }
        }
        return hashmap;
    }

    public static void writeFingerprintIdsToXml(Context context, int i, HashMap hashmap)
    {
        getSecurityManager(context).putSystemDataStringFile(getPathByUserId(i), map2Str(hashmap));
    }

    private static final String FINGERPRINTID_USERID_TABLE = "fingerid_user_map.xml";
}
