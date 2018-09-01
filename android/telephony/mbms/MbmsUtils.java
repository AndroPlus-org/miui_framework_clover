// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.content.*;
import android.content.pm.*;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.List;

// Referenced classes of package android.telephony.mbms:
//            MbmsTempFileProvider

public class MbmsUtils
{

    public MbmsUtils()
    {
    }

    public static File getEmbmsTempFileDirForService(Context context, String s)
    {
        return new File(MbmsTempFileProvider.getEmbmsTempFileDir(context), s);
    }

    public static ServiceInfo getMiddlewareServiceInfo(Context context, String s)
    {
        context = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction(s);
        context = context.queryIntentServices(intent, 0x100000);
        if(context == null || context.size() == 0)
        {
            Log.w("MbmsUtils", "No download services found, cannot get service info");
            return null;
        }
        if(context.size() > 1)
        {
            Log.w("MbmsUtils", "More than one download service found, cannot get unique service");
            return null;
        } else
        {
            return ((ResolveInfo)context.get(0)).serviceInfo;
        }
    }

    public static boolean isContainedIn(File file, File file1)
    {
        boolean flag;
        try
        {
            file = file.getCanonicalPath();
            flag = file1.getCanonicalPath().startsWith(file);
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            throw new RuntimeException((new StringBuilder()).append("Failed to resolve canonical paths: ").append(file).toString());
        }
        return flag;
    }

    public static int startBinding(Context context, String s, ServiceConnection serviceconnection)
    {
        Intent intent = new Intent();
        s = getMiddlewareServiceInfo(context, s);
        if(s == null)
        {
            return 1;
        } else
        {
            intent.setComponent(toComponentName(s));
            context.bindService(intent, serviceconnection, 1);
            return 0;
        }
    }

    public static ComponentName toComponentName(ComponentInfo componentinfo)
    {
        return new ComponentName(componentinfo.packageName, componentinfo.name);
    }

    private static final String LOG_TAG = "MbmsUtils";
}
