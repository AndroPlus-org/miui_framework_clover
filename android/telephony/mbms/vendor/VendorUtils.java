// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms.vendor;

import android.content.*;
import android.content.pm.PackageManager;
import android.telephony.mbms.MbmsDownloadReceiver;
import java.util.List;

public class VendorUtils
{

    public VendorUtils()
    {
    }

    public static ComponentName getAppReceiverFromPackageName(Context context, String s)
    {
        s = new ComponentName(s, android/telephony/mbms/MbmsDownloadReceiver.getCanonicalName());
        Intent intent = new Intent();
        intent.setComponent(s);
        context = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        if(context != null && context.size() > 0)
            return s;
        else
            return null;
    }

    public static final String ACTION_CLEANUP = "android.telephony.mbms.action.CLEANUP";
    public static final String ACTION_DOWNLOAD_RESULT_INTERNAL = "android.telephony.mbms.action.DOWNLOAD_RESULT_INTERNAL";
    public static final String ACTION_FILE_DESCRIPTOR_REQUEST = "android.telephony.mbms.action.FILE_DESCRIPTOR_REQUEST";
    public static final String EXTRA_FD_COUNT = "android.telephony.mbms.extra.FD_COUNT";
    public static final String EXTRA_FINAL_URI = "android.telephony.mbms.extra.FINAL_URI";
    public static final String EXTRA_FREE_URI_LIST = "android.telephony.mbms.extra.FREE_URI_LIST";
    public static final String EXTRA_PAUSED_LIST = "android.telephony.mbms.extra.PAUSED_LIST";
    public static final String EXTRA_PAUSED_URI_LIST = "android.telephony.mbms.extra.PAUSED_URI_LIST";
    public static final String EXTRA_SERVICE_ID = "android.telephony.mbms.extra.SERVICE_ID";
    public static final String EXTRA_TEMP_FILES_IN_USE = "android.telephony.mbms.extra.TEMP_FILES_IN_USE";
    public static final String EXTRA_TEMP_FILE_ROOT = "android.telephony.mbms.extra.TEMP_FILE_ROOT";
    public static final String EXTRA_TEMP_LIST = "android.telephony.mbms.extra.TEMP_LIST";
}
