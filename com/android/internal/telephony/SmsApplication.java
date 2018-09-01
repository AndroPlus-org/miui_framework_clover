// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.app.AppOpsManager;
import android.content.*;
import android.content.pm.*;
import android.content.res.Resources;
import android.net.Uri;
import android.os.*;
import android.telephony.*;
import com.android.internal.content.PackageMonitor;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.os.BackgroundThread;
import java.util.*;

// Referenced classes of package com.android.internal.telephony:
//            SmsApplicationInjector

public final class SmsApplication
{
    public static class SmsApplicationData
    {

        static String _2D_get0(SmsApplicationData smsapplicationdata)
        {
            return smsapplicationdata.mMmsReceiverClass;
        }

        static String _2D_get1(SmsApplicationData smsapplicationdata)
        {
            return smsapplicationdata.mProviderChangedReceiverClass;
        }

        static String _2D_get2(SmsApplicationData smsapplicationdata)
        {
            return smsapplicationdata.mRespondViaMessageClass;
        }

        static String _2D_get3(SmsApplicationData smsapplicationdata)
        {
            return smsapplicationdata.mSendToClass;
        }

        static String _2D_get4(SmsApplicationData smsapplicationdata)
        {
            return smsapplicationdata.mSimFullReceiverClass;
        }

        static String _2D_get5(SmsApplicationData smsapplicationdata)
        {
            return smsapplicationdata.mSmsAppChangedReceiverClass;
        }

        static String _2D_get6(SmsApplicationData smsapplicationdata)
        {
            return smsapplicationdata.mSmsReceiverClass;
        }

        static int _2D_get7(SmsApplicationData smsapplicationdata)
        {
            return smsapplicationdata.mUid;
        }

        static String _2D_set0(SmsApplicationData smsapplicationdata, String s)
        {
            smsapplicationdata.mMmsReceiverClass = s;
            return s;
        }

        static String _2D_set1(SmsApplicationData smsapplicationdata, String s)
        {
            smsapplicationdata.mProviderChangedReceiverClass = s;
            return s;
        }

        static String _2D_set2(SmsApplicationData smsapplicationdata, String s)
        {
            smsapplicationdata.mRespondViaMessageClass = s;
            return s;
        }

        static String _2D_set3(SmsApplicationData smsapplicationdata, String s)
        {
            smsapplicationdata.mSendToClass = s;
            return s;
        }

        static String _2D_set4(SmsApplicationData smsapplicationdata, String s)
        {
            smsapplicationdata.mSimFullReceiverClass = s;
            return s;
        }

        static String _2D_set5(SmsApplicationData smsapplicationdata, String s)
        {
            smsapplicationdata.mSmsAppChangedReceiverClass = s;
            return s;
        }

        static String _2D_set6(SmsApplicationData smsapplicationdata, String s)
        {
            smsapplicationdata.mSmsReceiverClass = s;
            return s;
        }

        public String getApplicationName(Context context)
        {
            Object obj = null;
            if(mApplicationName == null)
            {
                PackageManager packagemanager = context.getPackageManager();
                try
                {
                    context = packagemanager.getApplicationInfoAsUser(mPackageName, 0, UserHandle.getUserId(mUid));
                }
                // Misplaced declaration of an exception variable
                catch(Context context)
                {
                    return null;
                }
                if(context != null)
                {
                    context = packagemanager.getApplicationLabel(context);
                    if(context == null)
                        context = obj;
                    else
                        context = context.toString();
                    mApplicationName = context;
                }
            }
            return mApplicationName;
        }

        public boolean isComplete()
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mSmsReceiverClass != null)
            {
                flag1 = flag;
                if(mMmsReceiverClass != null)
                {
                    flag1 = flag;
                    if(mRespondViaMessageClass != null)
                    {
                        flag1 = flag;
                        if(mSendToClass != null)
                            flag1 = true;
                    }
                }
            }
            return flag1;
        }

        public String toString()
        {
            return (new StringBuilder()).append(" mPackageName: ").append(mPackageName).append(" mSmsReceiverClass: ").append(mSmsReceiverClass).append(" mMmsReceiverClass: ").append(mMmsReceiverClass).append(" mRespondViaMessageClass: ").append(mRespondViaMessageClass).append(" mSendToClass: ").append(mSendToClass).append(" mSmsAppChangedClass: ").append(mSmsAppChangedReceiverClass).append(" mProviderChangedReceiverClass: ").append(mProviderChangedReceiverClass).append(" mSimFullReceiverClass: ").append(mSimFullReceiverClass).append(" mUid: ").append(mUid).toString();
        }

        private String mApplicationName;
        private String mMmsReceiverClass;
        public String mPackageName;
        private String mProviderChangedReceiverClass;
        private String mRespondViaMessageClass;
        private String mSendToClass;
        private String mSimFullReceiverClass;
        private String mSmsAppChangedReceiverClass;
        private String mSmsReceiverClass;
        private int mUid;

        public SmsApplicationData(String s, int i)
        {
            mPackageName = s;
            mUid = i;
        }
    }

    private static final class SmsPackageMonitor extends PackageMonitor
    {

        private void onPackageChanged()
        {
            BackgroundThread.getHandler().removeCallbacks(mMsgCallback);
            BackgroundThread.getHandler().post(mMsgCallback);
        }

        public void handlePackageChanged()
        {
            PackageManager packagemanager = mContext.getPackageManager();
            Context context = mContext;
            int i = getSendingUserId();
            Object obj = context;
            if(i != 0)
                try
                {
                    Context context1 = mContext;
                    obj = mContext.getPackageName();
                    UserHandle userhandle = JVM INSTR new #62  <Class UserHandle>;
                    userhandle.UserHandle(i);
                    obj = context1.createPackageContextAsUser(((String) (obj)), 0, userhandle);
                }
                catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
                {
                    namenotfoundexception = context;
                }
            obj = SmsApplication.getDefaultSendToApplication(((Context) (obj)), true);
            if(obj != null)
                SmsApplication._2D_wrap0(packagemanager, ((ComponentName) (obj)), i);
        }

        public void onPackageAppeared(String s, int i)
        {
            onPackageChanged();
        }

        public void onPackageDisappeared(String s, int i)
        {
            onPackageChanged();
        }

        public void onPackageModified(String s)
        {
            onPackageChanged();
        }

        final Context mContext;
        Runnable mMsgCallback;

        public SmsPackageMonitor(Context context)
        {
            mMsgCallback = new _cls1();
            mContext = context;
        }
    }


    static void _2D_wrap0(PackageManager packagemanager, ComponentName componentname, int i)
    {
        configurePreferredActivity(packagemanager, componentname, i);
    }

    public SmsApplication()
    {
    }

    private static void assignWriteSmsPermissionToSystemApp(Context context, PackageManager packagemanager, AppOpsManager appopsmanager, String s)
    {
        if(packagemanager.checkSignatures(context.getPackageName(), s) != 0)
        {
            Rlog.e("SmsApplication", (new StringBuilder()).append(s).append(" does not have system signature").toString());
            return;
        }
        packagemanager = packagemanager.getPackageInfo(s, 0);
        if(appopsmanager.checkOp(15, ((PackageInfo) (packagemanager)).applicationInfo.uid, s) != 0)
        {
            context = JVM INSTR new #76  <Class StringBuilder>;
            context.StringBuilder();
            Rlog.w("SmsApplication", context.append(s).append(" does not have OP_WRITE_SMS:  (fixing)").toString());
            appopsmanager.setMode(15, ((PackageInfo) (packagemanager)).applicationInfo.uid, s, 0);
        }
_L1:
        return;
        context;
        Rlog.e("SmsApplication", (new StringBuilder()).append("Package not found: ").append(s).toString());
          goto _L1
    }

    private static void assignWriteSmsPermissionToSystemUid(AppOpsManager appopsmanager, int i)
    {
        appopsmanager.setUidMode(15, i, 0);
    }

    private static void configurePreferredActivity(PackageManager packagemanager, ComponentName componentname, int i)
    {
        replacePreferredActivity(packagemanager, componentname, i, "sms");
        replacePreferredActivity(packagemanager, componentname, i, "smsto");
        replacePreferredActivity(packagemanager, componentname, i, "mms");
        replacePreferredActivity(packagemanager, componentname, i, "mmsto");
    }

    private static SmsApplicationData getApplication(Context context, boolean flag, int i)
    {
label0:
        {
label1:
            {
                if(!((TelephonyManager)context.getSystemService("phone")).isSmsCapable())
                    return null;
                Object obj = getApplicationCollectionInternal(context, i);
                Object obj1 = android.provider.Settings.Secure.getStringForUser(context.getContentResolver(), "sms_default_application", i);
                Object obj2 = null;
                if(obj1 != null)
                    obj2 = getApplicationForPackage(((Collection) (obj)), ((String) (obj1)));
                obj1 = obj2;
                if(flag)
                {
                    obj1 = obj2;
                    if(obj2 == null)
                    {
                        obj1 = getApplicationForPackage(((Collection) (obj)), context.getResources().getString(0x10401a1));
                        obj2 = obj1;
                        if(obj1 == null)
                        {
                            obj2 = obj1;
                            if(((Collection) (obj)).size() != 0)
                                obj2 = (SmsApplicationData)((Collection) (obj)).toArray()[0];
                        }
                        obj1 = obj2;
                        if(obj2 != null)
                        {
                            setDefaultApplicationInternal(((SmsApplicationData) (obj2)).mPackageName, context, i);
                            obj1 = obj2;
                        }
                    }
                }
                obj = obj1;
                if(obj1 == null)
                    break label0;
                AppOpsManager appopsmanager = (AppOpsManager)context.getSystemService("appops");
                if(!flag)
                {
                    obj2 = obj1;
                    if(SmsApplicationData._2D_get7(((SmsApplicationData) (obj1))) != Process.myUid())
                        break label1;
                }
                obj2 = obj1;
                if(appopsmanager.checkOp(15, SmsApplicationData._2D_get7(((SmsApplicationData) (obj1))), ((SmsApplicationData) (obj1)).mPackageName) != 0)
                {
                    obj = (new StringBuilder()).append(((SmsApplicationData) (obj1)).mPackageName).append(" lost OP_WRITE_SMS: ");
                    if(flag)
                        obj2 = " (fixing)";
                    else
                        obj2 = " (no permission to fix)";
                    Rlog.e("SmsApplication", ((StringBuilder) (obj)).append(((String) (obj2))).toString());
                    if(flag)
                    {
                        appopsmanager.setMode(15, SmsApplicationData._2D_get7(((SmsApplicationData) (obj1))), ((SmsApplicationData) (obj1)).mPackageName, 0);
                        obj2 = obj1;
                    } else
                    {
                        obj2 = null;
                    }
                }
            }
            obj = obj2;
            if(flag)
            {
                obj1 = context.getPackageManager();
                configurePreferredActivity(((PackageManager) (obj1)), new ComponentName(((SmsApplicationData) (obj2)).mPackageName, SmsApplicationData._2D_get3(((SmsApplicationData) (obj2)))), i);
                assignWriteSmsPermissionToSystemApp(context, ((PackageManager) (obj1)), appopsmanager, "com.android.phone");
                assignWriteSmsPermissionToSystemApp(context, ((PackageManager) (obj1)), appopsmanager, "com.android.bluetooth");
                assignWriteSmsPermissionToSystemApp(context, ((PackageManager) (obj1)), appopsmanager, "com.android.mms.service");
                assignWriteSmsPermissionToSystemApp(context, ((PackageManager) (obj1)), appopsmanager, "com.android.providers.telephony");
                assignWriteSmsPermissionToSystemUid(appopsmanager, 1001);
                obj = obj2;
            }
        }
        return ((SmsApplicationData) (obj));
    }

    public static Collection getApplicationCollection(Context context)
    {
        int i;
        long l;
        i = getIncomingUserId(context);
        l = Binder.clearCallingIdentity();
        context = getApplicationCollectionInternal(context, i);
        Binder.restoreCallingIdentity(l);
        return context;
        context;
        Binder.restoreCallingIdentity(l);
        throw context;
    }

    private static Collection getApplicationCollectionInternal(Context context, int i)
    {
        PackageManager packagemanager = context.getPackageManager();
        Object obj1 = packagemanager.queryBroadcastReceiversAsUser(new Intent("android.provider.Telephony.SMS_DELIVER"), 0, i);
        context = new HashMap();
        Iterator iterator = ((Iterable) (obj1)).iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ActivityInfo activityinfo2 = ((ResolveInfo)iterator.next()).activityInfo;
            if(activityinfo2 != null && "android.permission.BROADCAST_SMS".equals(activityinfo2.permission))
            {
                String s = activityinfo2.packageName;
                if(!context.containsKey(s))
                {
                    SmsApplicationData smsapplicationdata4 = new SmsApplicationData(s, activityinfo2.applicationInfo.uid);
                    SmsApplicationData._2D_set6(smsapplicationdata4, activityinfo2.name);
                    context.put(s, smsapplicationdata4);
                }
            }
        } while(true);
        Object obj2 = new Intent("android.provider.Telephony.WAP_PUSH_DELIVER");
        ((Intent) (obj2)).setDataAndType(null, "application/vnd.wap.mms-message");
        obj2 = packagemanager.queryBroadcastReceiversAsUser(((Intent) (obj2)), 0, i).iterator();
        do
        {
            if(!((Iterator) (obj2)).hasNext())
                break;
            ActivityInfo activityinfo3 = ((ResolveInfo)((Iterator) (obj2)).next()).activityInfo;
            if(activityinfo3 != null && "android.permission.BROADCAST_WAP_PUSH".equals(activityinfo3.permission))
            {
                SmsApplicationData smsapplicationdata = (SmsApplicationData)context.get(activityinfo3.packageName);
                if(smsapplicationdata != null)
                    SmsApplicationData._2D_set0(smsapplicationdata, activityinfo3.name);
            }
        } while(true);
        Iterator iterator1 = packagemanager.queryIntentServicesAsUser(new Intent("android.intent.action.RESPOND_VIA_MESSAGE", Uri.fromParts("smsto", "", null)), 0, i).iterator();
        do
        {
            if(!iterator1.hasNext())
                break;
            obj2 = ((ResolveInfo)iterator1.next()).serviceInfo;
            if(obj2 != null && "android.permission.SEND_RESPOND_VIA_MESSAGE".equals(((ServiceInfo) (obj2)).permission))
            {
                SmsApplicationData smsapplicationdata1 = (SmsApplicationData)context.get(((ServiceInfo) (obj2)).packageName);
                if(smsapplicationdata1 != null)
                    SmsApplicationData._2D_set2(smsapplicationdata1, ((ServiceInfo) (obj2)).name);
            }
        } while(true);
        iterator1 = packagemanager.queryIntentActivitiesAsUser(new Intent("android.intent.action.SENDTO", Uri.fromParts("smsto", "", null)), 0, i).iterator();
        do
        {
            if(!iterator1.hasNext())
                break;
            obj2 = ((ResolveInfo)iterator1.next()).activityInfo;
            if(obj2 != null)
            {
                SmsApplicationData smsapplicationdata2 = (SmsApplicationData)context.get(((ActivityInfo) (obj2)).packageName);
                if(smsapplicationdata2 != null)
                    SmsApplicationData._2D_set3(smsapplicationdata2, ((ActivityInfo) (obj2)).name);
            }
        } while(true);
        obj2 = packagemanager.queryBroadcastReceiversAsUser(new Intent("android.provider.action.DEFAULT_SMS_PACKAGE_CHANGED"), 0, i).iterator();
        do
        {
            if(!((Iterator) (obj2)).hasNext())
                break;
            ActivityInfo activityinfo1 = ((ResolveInfo)((Iterator) (obj2)).next()).activityInfo;
            if(activityinfo1 != null)
            {
                SmsApplicationData smsapplicationdata5 = (SmsApplicationData)context.get(activityinfo1.packageName);
                if(smsapplicationdata5 != null)
                    SmsApplicationData._2D_set5(smsapplicationdata5, activityinfo1.name);
            }
        } while(true);
        obj2 = packagemanager.queryBroadcastReceiversAsUser(new Intent("android.provider.action.EXTERNAL_PROVIDER_CHANGE"), 0, i).iterator();
        do
        {
            if(!((Iterator) (obj2)).hasNext())
                break;
            ActivityInfo activityinfo4 = ((ResolveInfo)((Iterator) (obj2)).next()).activityInfo;
            if(activityinfo4 != null)
            {
                SmsApplicationData smsapplicationdata3 = (SmsApplicationData)context.get(activityinfo4.packageName);
                if(smsapplicationdata3 != null)
                    SmsApplicationData._2D_set1(smsapplicationdata3, activityinfo4.name);
            }
        } while(true);
        obj2 = packagemanager.queryBroadcastReceiversAsUser(new Intent("android.provider.Telephony.SIM_FULL"), 0, i).iterator();
        do
        {
            if(!((Iterator) (obj2)).hasNext())
                break;
            ActivityInfo activityinfo = ((ResolveInfo)((Iterator) (obj2)).next()).activityInfo;
            if(activityinfo != null)
            {
                SmsApplicationData smsapplicationdata6 = (SmsApplicationData)context.get(activityinfo.packageName);
                if(smsapplicationdata6 != null)
                    SmsApplicationData._2D_set4(smsapplicationdata6, activityinfo.name);
            }
        } while(true);
        obj1 = ((Iterable) (obj1)).iterator();
        do
        {
            if(!((Iterator) (obj1)).hasNext())
                break;
            Object obj = ((ResolveInfo)((Iterator) (obj1)).next()).activityInfo;
            if(obj != null)
            {
                String s1 = ((ActivityInfo) (obj)).packageName;
                obj = (SmsApplicationData)context.get(s1);
                if(obj != null && !((SmsApplicationData) (obj)).isComplete())
                    context.remove(s1);
            }
        } while(true);
        return context.values();
    }

    private static SmsApplicationData getApplicationForPackage(Collection collection, String s)
    {
        if(s == null)
            return null;
        for(Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            collection = (SmsApplicationData)iterator.next();
            if(((SmsApplicationData) (collection)).mPackageName.contentEquals(s))
                return collection;
        }

        return null;
    }

    public static ComponentName getDefaultExternalTelephonyProviderChangedApplication(Context context, boolean flag)
    {
        int i;
        long l;
        Object obj;
        i = getIncomingUserId(context);
        l = Binder.clearCallingIdentity();
        obj = null;
        SmsApplicationData smsapplicationdata = getApplication(context, flag, i);
        context = obj;
        if(smsapplicationdata == null)
            break MISSING_BLOCK_LABEL_57;
        context = obj;
        if(SmsApplicationData._2D_get1(smsapplicationdata) != null)
            context = new ComponentName(smsapplicationdata.mPackageName, SmsApplicationData._2D_get1(smsapplicationdata));
        Binder.restoreCallingIdentity(l);
        return context;
        context;
        Binder.restoreCallingIdentity(l);
        throw context;
    }

    public static ComponentName getDefaultMmsApplication(Context context, boolean flag)
    {
        int i;
        long l;
        Object obj;
        i = getIncomingUserId(context);
        l = Binder.clearCallingIdentity();
        obj = null;
        SmsApplicationData smsapplicationdata = getApplication(context, flag, i);
        context = obj;
        if(smsapplicationdata == null)
            break MISSING_BLOCK_LABEL_46;
        context = new ComponentName(smsapplicationdata.mPackageName, SmsApplicationData._2D_get0(smsapplicationdata));
        Binder.restoreCallingIdentity(l);
        return context;
        context;
        Binder.restoreCallingIdentity(l);
        throw context;
    }

    public static ComponentName getDefaultRespondViaMessageApplication(Context context, boolean flag)
    {
        int i;
        long l;
        Object obj;
        i = getIncomingUserId(context);
        l = Binder.clearCallingIdentity();
        obj = null;
        SmsApplicationData smsapplicationdata = getApplication(context, flag, i);
        context = obj;
        if(smsapplicationdata == null)
            break MISSING_BLOCK_LABEL_46;
        context = new ComponentName(smsapplicationdata.mPackageName, SmsApplicationData._2D_get2(smsapplicationdata));
        Binder.restoreCallingIdentity(l);
        return context;
        context;
        Binder.restoreCallingIdentity(l);
        throw context;
    }

    public static ComponentName getDefaultSendToApplication(Context context, boolean flag)
    {
        int i;
        long l;
        Object obj;
        i = getIncomingUserId(context);
        l = Binder.clearCallingIdentity();
        obj = null;
        SmsApplicationData smsapplicationdata = getApplication(context, flag, i);
        context = obj;
        if(smsapplicationdata == null)
            break MISSING_BLOCK_LABEL_46;
        context = new ComponentName(smsapplicationdata.mPackageName, SmsApplicationData._2D_get3(smsapplicationdata));
        Binder.restoreCallingIdentity(l);
        return context;
        context;
        Binder.restoreCallingIdentity(l);
        throw context;
    }

    public static ComponentName getDefaultSimFullApplication(Context context, boolean flag)
    {
        int i;
        long l;
        Object obj;
        i = getIncomingUserId(context);
        l = Binder.clearCallingIdentity();
        obj = null;
        SmsApplicationData smsapplicationdata = getApplication(context, flag, i);
        context = obj;
        if(smsapplicationdata == null)
            break MISSING_BLOCK_LABEL_57;
        context = obj;
        if(SmsApplicationData._2D_get4(smsapplicationdata) != null)
            context = new ComponentName(smsapplicationdata.mPackageName, SmsApplicationData._2D_get4(smsapplicationdata));
        Binder.restoreCallingIdentity(l);
        return context;
        context;
        Binder.restoreCallingIdentity(l);
        throw context;
    }

    public static ComponentName getDefaultSmsApplication(Context context, boolean flag)
    {
        int i;
        long l;
        Object obj;
        i = getIncomingUserId(context);
        l = Binder.clearCallingIdentity();
        obj = null;
        SmsApplicationData smsapplicationdata = getApplication(context, flag, i);
        context = obj;
        if(smsapplicationdata == null)
            break MISSING_BLOCK_LABEL_46;
        context = new ComponentName(smsapplicationdata.mPackageName, SmsApplicationData._2D_get6(smsapplicationdata));
        Binder.restoreCallingIdentity(l);
        return context;
        context;
        Binder.restoreCallingIdentity(l);
        throw context;
    }

    private static String getDefaultSmsApplicationPackageName(Context context)
    {
        context = getDefaultSmsApplication(context, false);
        if(context != null)
            return context.getPackageName();
        else
            return null;
    }

    private static int getIncomingUserId(Context context)
    {
        int i = context.getUserId();
        int j = Binder.getCallingUid();
        if(UserHandle.getAppId(j) < 10000)
            return i;
        else
            return UserHandle.getUserId(j);
    }

    public static SmsApplicationData getSmsApplicationData(String s, Context context)
    {
        return getApplicationForPackage(getApplicationCollection(context), s);
    }

    public static void initSmsPackageMonitor(Context context)
    {
        sSmsPackageMonitor = new SmsPackageMonitor(context);
        sSmsPackageMonitor.register(context, context.getMainLooper(), UserHandle.ALL, false);
    }

    public static boolean isDefaultSmsApplication(Context context, String s)
    {
        if(s == null)
            return false;
        context = getDefaultSmsApplicationPackageName(context);
        return context != null && context.equals(s) || "com.android.bluetooth".equals(s);
    }

    private static void replacePreferredActivity(PackageManager packagemanager, ComponentName componentname, int i, String s)
    {
        List list = packagemanager.queryIntentActivitiesAsUser(new Intent("android.intent.action.SENDTO", Uri.fromParts(s, "", null)), 0x10040, i);
        int j = list.size();
        ComponentName acomponentname[] = new ComponentName[j];
        for(int k = 0; k < j; k++)
        {
            ResolveInfo resolveinfo = (ResolveInfo)list.get(k);
            acomponentname[k] = new ComponentName(resolveinfo.activityInfo.packageName, resolveinfo.activityInfo.name);
        }

        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("android.intent.action.SENDTO");
        intentfilter.addCategory("android.intent.category.DEFAULT");
        intentfilter.addDataScheme(s);
        packagemanager.replacePreferredActivityAsUser(intentfilter, 0x208000, acomponentname, componentname, i);
    }

    public static void setDefaultApplication(String s, Context context)
    {
        int i;
        long l;
        if(!((TelephonyManager)context.getSystemService("phone")).isSmsCapable())
            return;
        i = getIncomingUserId(context);
        l = Binder.clearCallingIdentity();
        setDefaultApplicationInternal(s, context, i);
        Binder.restoreCallingIdentity(l);
        return;
        s;
        Binder.restoreCallingIdentity(l);
        throw s;
    }

    private static void setDefaultApplicationInternal(String s, Context context, int i)
    {
        Object obj = android.provider.Settings.Secure.getStringForUser(context.getContentResolver(), "sms_default_application", i);
        if(s != null && obj != null && s.equals(obj))
            return;
        PackageManager packagemanager = context.getPackageManager();
        Collection collection = getApplicationCollection(context);
        Object obj1;
        if(obj != null)
            obj1 = getApplicationForPackage(collection, ((String) (obj)));
        else
            obj1 = null;
        s = getApplicationForPackage(collection, s);
        if(s != null)
        {
            AppOpsManager appopsmanager = (AppOpsManager)context.getSystemService("appops");
            if(obj != null)
                try
                {
                    appopsmanager.setMode(15, packagemanager.getPackageInfoAsUser(((String) (obj)), 0, i).applicationInfo.uid, ((String) (obj)), 1);
                }
                catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
                {
                    Rlog.w("SmsApplication", (new StringBuilder()).append("Old SMS package not found: ").append(((String) (obj))).toString());
                }
            android.provider.Settings.Secure.putStringForUser(context.getContentResolver(), "sms_default_application", ((SmsApplicationData) (s)).mPackageName, i);
            configurePreferredActivity(packagemanager, new ComponentName(((SmsApplicationData) (s)).mPackageName, SmsApplicationData._2D_get3(s)), i);
            appopsmanager.setMode(15, SmsApplicationData._2D_get7(s), ((SmsApplicationData) (s)).mPackageName, 0);
            assignWriteSmsPermissionToSystemApp(context, packagemanager, appopsmanager, "com.android.phone");
            assignWriteSmsPermissionToSystemApp(context, packagemanager, appopsmanager, "com.android.bluetooth");
            assignWriteSmsPermissionToSystemApp(context, packagemanager, appopsmanager, "com.android.mms.service");
            assignWriteSmsPermissionToSystemApp(context, packagemanager, appopsmanager, "com.android.providers.telephony");
            assignWriteSmsPermissionToSystemUid(appopsmanager, 1001);
            if(obj1 != null && SmsApplicationData._2D_get5(((SmsApplicationData) (obj1))) != null)
            {
                obj = new Intent("android.provider.action.DEFAULT_SMS_PACKAGE_CHANGED");
                ((Intent) (obj)).setComponent(new ComponentName(((SmsApplicationData) (obj1)).mPackageName, SmsApplicationData._2D_get5(((SmsApplicationData) (obj1)))));
                ((Intent) (obj)).putExtra("android.provider.extra.IS_DEFAULT_SMS_APP", false);
                context.sendBroadcast(((Intent) (obj)));
            }
            if(SmsApplicationData._2D_get5(s) != null)
            {
                obj1 = new Intent("android.provider.action.DEFAULT_SMS_PACKAGE_CHANGED");
                ((Intent) (obj1)).setComponent(new ComponentName(((SmsApplicationData) (s)).mPackageName, SmsApplicationData._2D_get5(s)));
                ((Intent) (obj1)).putExtra("android.provider.extra.IS_DEFAULT_SMS_APP", true);
                context.sendBroadcast(((Intent) (obj1)));
            }
            MetricsLogger.action(context, 266, ((SmsApplicationData) (s)).mPackageName);
        }
    }

    public static boolean shouldWriteMessageForPackage(String s, Context context)
    {
        if(SmsManager.getDefault().getAutoPersisting())
            return true;
        if(SmsApplicationInjector.isIgnoreSmsStorageApplication(s))
            return false;
        else
            return isDefaultSmsApplication(context, s) ^ true;
    }

    private static final String BLUETOOTH_PACKAGE_NAME = "com.android.bluetooth";
    private static final boolean DEBUG_MULTIUSER = false;
    static final String LOG_TAG = "SmsApplication";
    private static final String MMS_SERVICE_PACKAGE_NAME = "com.android.mms.service";
    private static final String PHONE_PACKAGE_NAME = "com.android.phone";
    private static final String SCHEME_MMS = "mms";
    private static final String SCHEME_MMSTO = "mmsto";
    private static final String SCHEME_SMS = "sms";
    private static final String SCHEME_SMSTO = "smsto";
    private static final String TELEPHONY_PROVIDER_PACKAGE_NAME = "com.android.providers.telephony";
    private static SmsPackageMonitor sSmsPackageMonitor = null;


    // Unreferenced inner class com/android/internal/telephony/SmsApplication$SmsPackageMonitor$1

/* anonymous class */
    class SmsPackageMonitor._cls1
        implements Runnable
    {

        public void run()
        {
            handlePackageChanged();
        }

        final SmsPackageMonitor this$1;

            
            {
                this$1 = SmsPackageMonitor.this;
                super();
            }
    }

}
