// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.content.Context;
import android.hardware.fingerprint.Fingerprint;
import android.hardware.fingerprint.FingerprintManager;
import java.util.*;

// Referenced classes of package android.security:
//            FingerprintIdUtils

public class FingerprintIdUtilsCompat
{

    public FingerprintIdUtilsCompat()
    {
    }

    private static List getAllFingerprintIds(Context context)
    {
        context = getService(context);
        if(context != null)
        {
            Object obj = context.getEnrolledFingerprints();
            context = new ArrayList();
            if(obj != null && ((List) (obj)).size() > 0)
                for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); context.add(Integer.toString(((Fingerprint)((Iterator) (obj)).next()).getFingerId())));
            return context;
        } else
        {
            return Collections.emptyList();
        }
    }

    public static int getSecondSpaceId(Context context)
    {
        return android.provider.Settings.Secure.getIntForUser(context.getContentResolver(), "second_user_id", -10000, 0);
    }

    public static FingerprintManager getService(Context context)
    {
        if(mFingerprintMgr == null)
            mFingerprintMgr = (FingerprintManager)context.getSystemService("fingerprint");
        return mFingerprintMgr;
    }

    public static HashMap getValidFingerPrintIds(HashMap hashmap, Context context)
    {
        Object obj = new HashMap();
        if(hashmap == null || hashmap.isEmpty())
            return ((HashMap) (obj));
        List list = getAllFingerprintIds(context);
        if(list == null || list.isEmpty())
            return ((HashMap) (obj));
        obj = hashmap.entrySet().iterator();
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break;
            if(!list.contains((String)((java.util.Map.Entry)((Iterator) (obj)).next()).getKey()))
            {
                ((Iterator) (obj)).remove();
                FingerprintIdUtils.writeFingerprintIdsToXml(context, getSecondSpaceId(context), hashmap);
            }
        } while(true);
        return hashmap;
    }

    private static FingerprintManager mFingerprintMgr = null;

}
