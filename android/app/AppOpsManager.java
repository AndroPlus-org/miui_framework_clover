// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.os.*;
import android.util.ArrayMap;
import com.android.internal.app.*;
import java.util.*;

// Referenced classes of package android.app:
//            AppOpsManagerInjector

public class AppOpsManager
{
    public static class OnOpChangedInternalListener
        implements OnOpChangedListener
    {

        public void onOpChanged(int i, String s)
        {
        }

        public void onOpChanged(String s, String s1)
        {
        }

        public OnOpChangedInternalListener()
        {
        }
    }

    public static interface OnOpChangedListener
    {

        public abstract void onOpChanged(String s, String s1);
    }

    public static class OpEntry
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public int getDuration()
        {
            int i;
            if(mDuration == -1)
                i = (int)(System.currentTimeMillis() - mTime);
            else
                i = mDuration;
            return i;
        }

        public int getMode()
        {
            return mMode;
        }

        public int getOp()
        {
            return mOp;
        }

        public String getProxyPackageName()
        {
            return mProxyPackageName;
        }

        public int getProxyUid()
        {
            return mProxyUid;
        }

        public long getRejectTime()
        {
            return mRejectTime;
        }

        public long getTime()
        {
            return mTime;
        }

        public boolean isRunning()
        {
            boolean flag;
            if(mDuration == -1)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mOp);
            parcel.writeInt(mMode);
            parcel.writeLong(mTime);
            parcel.writeLong(mRejectTime);
            parcel.writeInt(mDuration);
            parcel.writeInt(mProxyUid);
            parcel.writeString(mProxyPackageName);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public OpEntry createFromParcel(Parcel parcel)
            {
                return new OpEntry(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public OpEntry[] newArray(int i)
            {
                return new OpEntry[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final int mDuration;
        private final int mMode;
        private final int mOp;
        private final String mProxyPackageName;
        private final int mProxyUid;
        private final long mRejectTime;
        private final long mTime;


        public OpEntry(int i, int j, long l, long l1, int k, 
                int i1, String s)
        {
            mOp = i;
            mMode = j;
            mTime = l;
            mRejectTime = l1;
            mDuration = k;
            mProxyUid = i1;
            mProxyPackageName = s;
        }

        OpEntry(Parcel parcel)
        {
            mOp = parcel.readInt();
            mMode = parcel.readInt();
            mTime = parcel.readLong();
            mRejectTime = parcel.readLong();
            mDuration = parcel.readInt();
            mProxyUid = parcel.readInt();
            mProxyPackageName = parcel.readString();
        }
    }

    public static class PackageOps
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public List getOps()
        {
            return mEntries;
        }

        public String getPackageName()
        {
            return mPackageName;
        }

        public int getUid()
        {
            return mUid;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(mPackageName);
            parcel.writeInt(mUid);
            parcel.writeInt(mEntries.size());
            for(int j = 0; j < mEntries.size(); j++)
                ((OpEntry)mEntries.get(j)).writeToParcel(parcel, i);

        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public PackageOps createFromParcel(Parcel parcel)
            {
                return new PackageOps(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public PackageOps[] newArray(int i)
            {
                return new PackageOps[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final List mEntries;
        private final String mPackageName;
        private final int mUid;


        PackageOps(Parcel parcel)
        {
            mPackageName = parcel.readString();
            mUid = parcel.readInt();
            mEntries = new ArrayList();
            int i = parcel.readInt();
            for(int j = 0; j < i; j++)
                mEntries.add((OpEntry)OpEntry.CREATOR.createFromParcel(parcel));

        }

        public PackageOps(String s, int i, List list)
        {
            mPackageName = s;
            mUid = i;
            mEntries = list;
        }
    }


    static String[] _2D_get0()
    {
        return sOpToString;
    }

    AppOpsManager(Context context, IAppOpsService iappopsservice)
    {
        mContext = context;
        mService = iappopsservice;
    }

    private String buildSecurityExceptionMsg(int i, int j, String s)
    {
        return (new StringBuilder()).append(s).append(" from uid ").append(j).append(" not allowed to perform ").append(sOpNames[i]).toString();
    }

    public static IBinder getToken(IAppOpsService iappopsservice)
    {
        android/app/AppOpsManager;
        JVM INSTR monitorenter ;
        if(sToken == null)
            break MISSING_BLOCK_LABEL_18;
        iappopsservice = sToken;
        android/app/AppOpsManager;
        JVM INSTR monitorexit ;
        return iappopsservice;
        Binder binder = JVM INSTR new #717 <Class Binder>;
        binder.Binder();
        sToken = iappopsservice.getToken(binder);
        iappopsservice = sToken;
        android/app/AppOpsManager;
        JVM INSTR monitorexit ;
        return iappopsservice;
        iappopsservice;
        throw iappopsservice.rethrowFromSystemServer();
        iappopsservice;
        android/app/AppOpsManager;
        JVM INSTR monitorexit ;
        throw iappopsservice;
    }

    public static boolean opAllowSystemBypassRestriction(int i)
    {
        return sOpAllowSystemRestrictionBypass[i];
    }

    public static boolean opAllowsReset(int i)
    {
        if(i > 10000)
            return true;
        else
            return sOpDisableReset[i] ^ true;
    }

    public static int opToDefaultMode(int i)
    {
        if(i > 10000)
            return AppOpsManagerInjector.opToDefaultMode(i);
        else
            return sOpDefaultMode[i];
    }

    public static String opToName(int i)
    {
        if(i == -1)
            return "NONE";
        String s;
        if(i < sOpNames.length)
            s = sOpNames[i];
        else
            s = (new StringBuilder()).append("Unknown(").append(i).append(")").toString();
        return s;
    }

    public static String opToPermission(int i)
    {
        if(i > 10000)
            return null;
        else
            return sOpPerms[i];
    }

    public static String opToRestriction(int i)
    {
        if(i > 10000)
            return null;
        else
            return sOpRestrictions[i];
    }

    public static int opToSwitch(int i)
    {
        if(i > 10000)
            return i;
        else
            return sOpToSwitch[i];
    }

    public static String permissionToOp(String s)
    {
        s = (Integer)sPermToOp.get(s);
        if(s == null)
            return null;
        else
            return sOpToString[s.intValue()];
    }

    public static int permissionToOpCode(String s)
    {
        s = (Integer)sPermToOp.get(s);
        int i;
        if(s != null)
            i = s.intValue();
        else
            i = -1;
        return i;
    }

    public static int strDebugOpToOp(String s)
    {
        for(int i = 0; i < sOpNames.length; i++)
            if(sOpNames[i].equals(s))
                return i;

        throw new IllegalArgumentException((new StringBuilder()).append("Unknown operation string: ").append(s).toString());
    }

    public static int strOpToOp(String s)
    {
        Integer integer = (Integer)sOpStrToOp.get(s);
        if(integer == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown operation string: ").append(s).toString());
        else
            return integer.intValue();
    }

    public int checkAudioOp(int i, int j, int k, String s)
    {
        SecurityException securityexception;
        try
        {
            j = mService.checkAudioOperation(i, j, k, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(j != 2)
            break MISSING_BLOCK_LABEL_49;
        securityexception = JVM INSTR new #777 <Class SecurityException>;
        securityexception.SecurityException(buildSecurityExceptionMsg(i, k, s));
        throw securityexception;
        return j;
    }

    public int checkAudioOpNoThrow(int i, int j, int k, String s)
    {
        try
        {
            i = mService.checkAudioOperation(i, j, k, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public int checkOp(int i, int j, String s)
    {
        int k;
        SecurityException securityexception;
        try
        {
            k = mService.checkOperation(i, j, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(k != 2)
            break MISSING_BLOCK_LABEL_46;
        securityexception = JVM INSTR new #777 <Class SecurityException>;
        securityexception.SecurityException(buildSecurityExceptionMsg(i, j, s));
        throw securityexception;
        return k;
    }

    public int checkOp(String s, int i, String s1)
    {
        return checkOp(strOpToOp(s), i, s1);
    }

    public int checkOpNoThrow(int i, int j, String s)
    {
        try
        {
            i = mService.checkOperation(i, j, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public int checkOpNoThrow(String s, int i, String s1)
    {
        return checkOpNoThrow(strOpToOp(s), i, s1);
    }

    public void checkPackage(int i, String s)
    {
        try
        {
            if(mService.checkPackage(i, s) != 0)
            {
                SecurityException securityexception = JVM INSTR new #777 <Class SecurityException>;
                StringBuilder stringbuilder = JVM INSTR new #649 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                securityexception.SecurityException(stringbuilder.append("Package ").append(s).append(" does not belong to ").append(i).toString());
                throw securityexception;
            }
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void finishOp(int i)
    {
        finishOp(i, Process.myUid(), mContext.getOpPackageName());
    }

    public void finishOp(int i, int j, String s)
    {
        try
        {
            mService.finishOperation(getToken(mService), i, j, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void finishOp(String s, int i, String s1)
    {
        finishOp(strOpToOp(s), i, s1);
    }

    public List getOpsForPackage(int i, String s, int ai[])
    {
        try
        {
            s = mService.getOpsForPackage(i, s, ai);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public List getPackagesForOps(int ai[])
    {
        try
        {
            ai = mService.getPackagesForOps(ai);
        }
        // Misplaced declaration of an exception variable
        catch(int ai[])
        {
            throw ai.rethrowFromSystemServer();
        }
        return ai;
    }

    public boolean isOperationActive(int i, int j, String s)
    {
        boolean flag;
        try
        {
            flag = mService.isOperationActive(i, j, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public int noteOp(int i)
    {
        return noteOp(i, Process.myUid(), mContext.getOpPackageName());
    }

    public int noteOp(int i, int j, String s)
    {
        int k;
        SecurityException securityexception;
        try
        {
            k = mService.noteOperation(i, j, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(k != 2)
            break MISSING_BLOCK_LABEL_46;
        securityexception = JVM INSTR new #777 <Class SecurityException>;
        securityexception.SecurityException(buildSecurityExceptionMsg(i, j, s));
        throw securityexception;
        return k;
    }

    public int noteOp(String s, int i, String s1)
    {
        return noteOp(strOpToOp(s), i, s1);
    }

    public int noteOpNoThrow(int i, int j, String s)
    {
        try
        {
            i = mService.noteOperation(i, j, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public int noteOpNoThrow(String s, int i, String s1)
    {
        return noteOpNoThrow(strOpToOp(s), i, s1);
    }

    public int noteProxyOp(int i, String s)
    {
        int j = noteProxyOpNoThrow(i, s);
        if(j == 2)
            throw new SecurityException((new StringBuilder()).append("Proxy package ").append(mContext.getOpPackageName()).append(" from uid ").append(Process.myUid()).append(" or calling package ").append(s).append(" from uid ").append(Binder.getCallingUid()).append(" not allowed to perform ").append(sOpNames[i]).toString());
        else
            return j;
    }

    public int noteProxyOp(String s, String s1)
    {
        return noteProxyOp(strOpToOp(s), s1);
    }

    public int noteProxyOpNoThrow(int i, String s)
    {
        try
        {
            i = mService.noteProxyOperation(i, mContext.getOpPackageName(), Binder.getCallingUid(), s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public int noteProxyOpNoThrow(String s, String s1)
    {
        return noteProxyOpNoThrow(strOpToOp(s), s1);
    }

    public int registerCallback(IOpsCallback iopscallback)
    {
        byte byte0 = -1;
        int i = byte0;
        if(iopscallback != null)
            try
            {
                i = mService.registerCallback(iopscallback);
            }
            // Misplaced declaration of an exception variable
            catch(IOpsCallback iopscallback)
            {
                i = byte0;
            }
        return i;
    }

    public void resetAllModes()
    {
        try
        {
            mService.resetAllModes(UserHandle.myUserId(), null);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setMode(int i, int j, String s, int k)
    {
        try
        {
            mService.setMode(i, j, s, k);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setRestriction(int i, int j, int k, String as[])
    {
        try
        {
            int l = Binder.getCallingUid();
            mService.setAudioRestriction(i, j, l, k, as);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String as[])
        {
            throw as.rethrowFromSystemServer();
        }
    }

    public void setUidMode(int i, int j, int k)
    {
        try
        {
            mService.setUidMode(i, j, k);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setUidMode(String s, int i, int j)
    {
        try
        {
            mService.setUidMode(strOpToOp(s), i, j);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setUserRestriction(int i, boolean flag, IBinder ibinder)
    {
        setUserRestriction(i, flag, ibinder, null);
    }

    public void setUserRestriction(int i, boolean flag, IBinder ibinder, String as[])
    {
        setUserRestrictionForUser(i, flag, ibinder, as, mContext.getUserId());
    }

    public void setUserRestrictionForUser(int i, boolean flag, IBinder ibinder, String as[], int j)
    {
        try
        {
            mService.setUserRestriction(i, flag, ibinder, j, as);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw ibinder.rethrowFromSystemServer();
        }
    }

    public int startOp(int i)
    {
        return startOp(i, Process.myUid(), mContext.getOpPackageName());
    }

    public int startOp(int i, int j, String s)
    {
        int k;
        SecurityException securityexception;
        try
        {
            k = mService.startOperation(getToken(mService), i, j, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        if(k != 2)
            break MISSING_BLOCK_LABEL_53;
        securityexception = JVM INSTR new #777 <Class SecurityException>;
        securityexception.SecurityException(buildSecurityExceptionMsg(i, j, s));
        throw securityexception;
        return k;
    }

    public int startOp(String s, int i, String s1)
    {
        return startOp(strOpToOp(s), i, s1);
    }

    public int startOpNoThrow(int i, int j, String s)
    {
        try
        {
            i = mService.startOperation(getToken(mService), i, j, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public int startOpNoThrow(String s, int i, String s1)
    {
        return startOpNoThrow(strOpToOp(s), i, s1);
    }

    public void startWatchingMode(int i, String s, OnOpChangedListener onopchangedlistener)
    {
        ArrayMap arraymap = mModeWatchers;
        arraymap;
        JVM INSTR monitorenter ;
        IAppOpsCallback iappopscallback = (IAppOpsCallback)mModeWatchers.get(onopchangedlistener);
        Object obj;
        obj = iappopscallback;
        if(iappopscallback != null)
            break MISSING_BLOCK_LABEL_54;
        obj = JVM INSTR new #6   <Class AppOpsManager$1>;
        ((_cls1) (obj)).this. _cls1();
        mModeWatchers.put(onopchangedlistener, obj);
        mService.startWatchingMode(i, s, ((IAppOpsCallback) (obj)));
        arraymap;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s.rethrowFromSystemServer();
        s;
        arraymap;
        JVM INSTR monitorexit ;
        throw s;
    }

    public void startWatchingMode(String s, String s1, OnOpChangedListener onopchangedlistener)
    {
        startWatchingMode(strOpToOp(s), s1, onopchangedlistener);
    }

    public void stopWatchingMode(OnOpChangedListener onopchangedlistener)
    {
        ArrayMap arraymap = mModeWatchers;
        arraymap;
        JVM INSTR monitorenter ;
        onopchangedlistener = (IAppOpsCallback)mModeWatchers.get(onopchangedlistener);
        if(onopchangedlistener == null)
            break MISSING_BLOCK_LABEL_33;
        mService.stopWatchingMode(onopchangedlistener);
        arraymap;
        JVM INSTR monitorexit ;
        return;
        onopchangedlistener;
        throw onopchangedlistener.rethrowFromSystemServer();
        onopchangedlistener;
        arraymap;
        JVM INSTR monitorexit ;
        throw onopchangedlistener;
    }

    public static final int MIUI_OP_END = 10024;
    public static final int MIUI_OP_START = 10000;
    public static final int MODE_ALLOWED = 0;
    public static final int MODE_ASK = 4;
    public static final int MODE_DEFAULT = 3;
    public static final int MODE_ERRORED = 2;
    public static final int MODE_IGNORED = 1;
    public static final int MODE_UNKNOWN = -1;
    public static final String OPSTR_ACTIVATE_VPN = "android:activate_vpn";
    public static final String OPSTR_ADD_VOICEMAIL = "android:add_voicemail";
    public static final String OPSTR_ANSWER_PHONE_CALLS = "android:answer_phone_calls";
    public static final String OPSTR_BLUETOOTH_ADMIN = "android:bluetooth_admin";
    public static final String OPSTR_BODY_SENSORS = "android:body_sensors";
    public static final String OPSTR_CALL_PHONE = "android:call_phone";
    public static final String OPSTR_CAMERA = "android:camera";
    public static final String OPSTR_CHANGE_WIFI_STATE = "android:change_wifi_state";
    public static final String OPSTR_COARSE_LOCATION = "android:coarse_location";
    public static final String OPSTR_FINE_LOCATION = "android:fine_location";
    public static final String OPSTR_GET_ACCOUNTS = "android:get_accounts";
    public static final String OPSTR_GET_USAGE_STATS = "android:get_usage_stats";
    public static final String OPSTR_INSTANT_APP_START_FOREGROUND = "android:instant_app_start_foreground";
    public static final String OPSTR_MOCK_LOCATION = "android:mock_location";
    public static final String OPSTR_MONITOR_HIGH_POWER_LOCATION = "android:monitor_location_high_power";
    public static final String OPSTR_MONITOR_LOCATION = "android:monitor_location";
    public static final String OPSTR_PICTURE_IN_PICTURE = "android:picture_in_picture";
    public static final String OPSTR_PROCESS_OUTGOING_CALLS = "android:process_outgoing_calls";
    public static final String OPSTR_READ_CALENDAR = "android:read_calendar";
    public static final String OPSTR_READ_CALL_LOG = "android:read_call_log";
    public static final String OPSTR_READ_CELL_BROADCASTS = "android:read_cell_broadcasts";
    public static final String OPSTR_READ_CONTACTS = "android:read_contacts";
    public static final String OPSTR_READ_EXTERNAL_STORAGE = "android:read_external_storage";
    public static final String OPSTR_READ_PHONE_NUMBERS = "android:read_phone_numbers";
    public static final String OPSTR_READ_PHONE_STATE = "android:read_phone_state";
    public static final String OPSTR_READ_SMS = "android:read_sms";
    public static final String OPSTR_RECEIVE_MMS = "android:receive_mms";
    public static final String OPSTR_RECEIVE_SMS = "android:receive_sms";
    public static final String OPSTR_RECEIVE_WAP_PUSH = "android:receive_wap_push";
    public static final String OPSTR_RECORD_AUDIO = "android:record_audio";
    public static final String OPSTR_SEND_SMS = "android:send_sms";
    public static final String OPSTR_SYSTEM_ALERT_WINDOW = "android:system_alert_window";
    public static final String OPSTR_USE_FINGERPRINT = "android:use_fingerprint";
    public static final String OPSTR_USE_SIP = "android:use_sip";
    public static final String OPSTR_WRITE_CALENDAR = "android:write_calendar";
    public static final String OPSTR_WRITE_CALL_LOG = "android:write_call_log";
    public static final String OPSTR_WRITE_CONTACTS = "android:write_contacts";
    public static final String OPSTR_WRITE_EXTERNAL_STORAGE = "android:write_external_storage";
    public static final String OPSTR_WRITE_SETTINGS = "android:write_settings";
    public static final int OP_ACCESS_NOTIFICATIONS = 25;
    public static final int OP_ACCESS_XIAOMI_ACCOUNT = 10015;
    public static final int OP_ACTIVATE_VPN = 47;
    public static final int OP_ADD_VOICEMAIL = 52;
    public static final int OP_ANSWER_PHONE_CALLS = 69;
    public static final int OP_ASSIST_SCREENSHOT = 50;
    public static final int OP_ASSIST_STRUCTURE = 49;
    public static final int OP_AUDIO_ACCESSIBILITY_VOLUME = 64;
    public static final int OP_AUDIO_ALARM_VOLUME = 37;
    public static final int OP_AUDIO_BLUETOOTH_VOLUME = 39;
    public static final int OP_AUDIO_MASTER_VOLUME = 33;
    public static final int OP_AUDIO_MEDIA_VOLUME = 36;
    public static final int OP_AUDIO_NOTIFICATION_VOLUME = 38;
    public static final int OP_AUDIO_RING_VOLUME = 35;
    public static final int OP_AUDIO_VOICE_VOLUME = 34;
    public static final int OP_AUTO_START = 10008;
    public static final int OP_BACKGROUND_START_ACTIVITY = 10021;
    public static final int OP_BLUETOOTH_ADMIN = 71;
    public static final int OP_BLUETOOTH_CHANGE = 10002;
    public static final int OP_BODY_SENSORS = 56;
    public static final int OP_BOOT_COMPLETED = 10007;
    public static final int OP_CALL_PHONE = 13;
    public static final int OP_CAMERA = 26;
    public static final int OP_CHANGE_WIFI_STATE = 70;
    public static final int OP_COARSE_LOCATION = 0;
    public static final int OP_DATA_CONNECT_CHANGE = 10003;
    public static final int OP_DELETE_CALL_LOG = 10013;
    public static final int OP_DELETE_CONTACTS = 10012;
    public static final int OP_DELETE_MMS = 10011;
    public static final int OP_DELETE_SMS = 10010;
    public static final int OP_EXACT_ALARM = 10014;
    public static final int OP_FINE_LOCATION = 1;
    public static final int OP_GET_ACCOUNTS = 62;
    public static final int OP_GET_INSTALLED_APPS = 10022;
    public static final int OP_GET_TASKS = 10019;
    public static final int OP_GET_USAGE_STATS = 43;
    public static final int OP_GPS = 2;
    public static final int OP_INSTALL_SHORTCUT = 10017;
    public static final int OP_INSTANT_APP_START_FOREGROUND = 68;
    public static final int OP_MOCK_LOCATION = 58;
    public static final int OP_MONITOR_HIGH_POWER_LOCATION = 42;
    public static final int OP_MONITOR_LOCATION = 41;
    public static final int OP_MUTE_MICROPHONE = 44;
    public static final int OP_NEIGHBORING_CELLS = 12;
    public static final int OP_NFC = 10016;
    public static final int OP_NFC_CHANGE = 10009;
    public static final int OP_NONE = -1;
    public static final int OP_PICTURE_IN_PICTURE = 67;
    public static final int OP_PLAY_AUDIO = 28;
    public static final int OP_POST_NOTIFICATION = 11;
    public static final int OP_PROCESS_OUTGOING_CALLS = 54;
    public static final int OP_PROJECT_MEDIA = 46;
    public static final int OP_READ_CALENDAR = 8;
    public static final int OP_READ_CALL_LOG = 6;
    public static final int OP_READ_CELL_BROADCASTS = 57;
    public static final int OP_READ_CLIPBOARD = 29;
    public static final int OP_READ_CONTACTS = 4;
    public static final int OP_READ_EXTERNAL_STORAGE = 59;
    public static final int OP_READ_ICC_SMS = 21;
    public static final int OP_READ_MMS = 10005;
    public static final int OP_READ_NOTIFICATION_SMS = 10018;
    public static final int OP_READ_PHONE_NUMBERS = 65;
    public static final int OP_READ_PHONE_STATE = 51;
    public static final int OP_READ_SMS = 14;
    public static final int OP_RECEIVE_EMERGECY_SMS = 17;
    public static final int OP_RECEIVE_MMS = 18;
    public static final int OP_RECEIVE_SMS = 16;
    public static final int OP_RECEIVE_WAP_PUSH = 19;
    public static final int OP_RECORD_AUDIO = 27;
    public static final int OP_REQUEST_INSTALL_PACKAGES = 66;
    public static final int OP_RUN_IN_BACKGROUND = 63;
    public static final int OP_SEND_MMS = 10004;
    public static final int OP_SEND_SMS = 20;
    public static final int OP_SERVICE_FOREGROUND = 10023;
    public static final int OP_SHOW_WHEN_LOCKED = 10020;
    public static final int OP_SYSTEM_ALERT_WINDOW = 24;
    public static final int OP_TAKE_AUDIO_FOCUS = 32;
    public static final int OP_TAKE_MEDIA_BUTTONS = 31;
    public static final int OP_TOAST_WINDOW = 45;
    public static final int OP_TURN_SCREEN_ON = 61;
    public static final int OP_USE_FINGERPRINT = 55;
    public static final int OP_USE_SIP = 53;
    public static final int OP_VIBRATE = 3;
    public static final int OP_WAKE_LOCK = 40;
    public static final int OP_WIFI_CHANGE = 10001;
    public static final int OP_WIFI_SCAN = 10;
    public static final int OP_WRITE_CALENDAR = 9;
    public static final int OP_WRITE_CALL_LOG = 7;
    public static final int OP_WRITE_CLIPBOARD = 30;
    public static final int OP_WRITE_CONTACTS = 5;
    public static final int OP_WRITE_EXTERNAL_STORAGE = 60;
    public static final int OP_WRITE_ICC_SMS = 22;
    public static final int OP_WRITE_MMS = 10006;
    public static final int OP_WRITE_SETTINGS = 23;
    public static final int OP_WRITE_SMS = 15;
    public static final int OP_WRITE_WALLPAPER = 48;
    private static final int RUNTIME_AND_APPOP_PERMISSIONS_OPS[] = {
        4, 5, 62, 8, 9, 20, 16, 14, 19, 18, 
        57, 59, 60, 0, 1, 51, 65, 13, 6, 7, 
        52, 53, 54, 69, 27, 26, 56, 25, 24, 23, 
        66
    };
    public static final int _NUM_OP = 72;
    private static boolean sOpAllowSystemRestrictionBypass[] = {
        1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 
        1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0
    };
    private static int sOpDefaultMode[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 
        0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 3, 0, 0, 1, 1, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 
        0, 0, 0, 0, 0, 0, 2, 0, 3, 0, 
        0, 0
    };
    private static boolean sOpDisableReset[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0
    };
    private static String sOpNames[] = {
        "COARSE_LOCATION", "FINE_LOCATION", "GPS", "VIBRATE", "READ_CONTACTS", "WRITE_CONTACTS", "READ_CALL_LOG", "WRITE_CALL_LOG", "READ_CALENDAR", "WRITE_CALENDAR", 
        "WIFI_SCAN", "POST_NOTIFICATION", "NEIGHBORING_CELLS", "CALL_PHONE", "READ_SMS", "WRITE_SMS", "RECEIVE_SMS", "RECEIVE_EMERGECY_SMS", "RECEIVE_MMS", "RECEIVE_WAP_PUSH", 
        "SEND_SMS", "READ_ICC_SMS", "WRITE_ICC_SMS", "WRITE_SETTINGS", "SYSTEM_ALERT_WINDOW", "ACCESS_NOTIFICATIONS", "CAMERA", "RECORD_AUDIO", "PLAY_AUDIO", "READ_CLIPBOARD", 
        "WRITE_CLIPBOARD", "TAKE_MEDIA_BUTTONS", "TAKE_AUDIO_FOCUS", "AUDIO_MASTER_VOLUME", "AUDIO_VOICE_VOLUME", "AUDIO_RING_VOLUME", "AUDIO_MEDIA_VOLUME", "AUDIO_ALARM_VOLUME", "AUDIO_NOTIFICATION_VOLUME", "AUDIO_BLUETOOTH_VOLUME", 
        "WAKE_LOCK", "MONITOR_LOCATION", "MONITOR_HIGH_POWER_LOCATION", "GET_USAGE_STATS", "MUTE_MICROPHONE", "TOAST_WINDOW", "PROJECT_MEDIA", "ACTIVATE_VPN", "WRITE_WALLPAPER", "ASSIST_STRUCTURE", 
        "ASSIST_SCREENSHOT", "OP_READ_PHONE_STATE", "ADD_VOICEMAIL", "USE_SIP", "PROCESS_OUTGOING_CALLS", "USE_FINGERPRINT", "BODY_SENSORS", "READ_CELL_BROADCASTS", "MOCK_LOCATION", "READ_EXTERNAL_STORAGE", 
        "WRITE_EXTERNAL_STORAGE", "TURN_ON_SCREEN", "GET_ACCOUNTS", "RUN_IN_BACKGROUND", "AUDIO_ACCESSIBILITY_VOLUME", "READ_PHONE_NUMBERS", "REQUEST_INSTALL_PACKAGES", "PICTURE_IN_PICTURE", "INSTANT_APP_START_FOREGROUND", "ANSWER_PHONE_CALLS", 
        "CHANGE_WIFI_STATE", "BLUETOOTH_ADMIN"
    };
    private static String sOpPerms[] = {
        "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", null, "android.permission.VIBRATE", "android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS", "android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG", "android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR", 
        "android.permission.ACCESS_WIFI_STATE", null, null, "android.permission.CALL_PHONE", "android.permission.READ_SMS", null, "android.permission.RECEIVE_SMS", "android.permission.RECEIVE_EMERGENCY_BROADCAST", "android.permission.RECEIVE_MMS", "android.permission.RECEIVE_WAP_PUSH", 
        "android.permission.SEND_SMS", "android.permission.READ_SMS", null, "android.permission.WRITE_SETTINGS", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.ACCESS_NOTIFICATIONS", "android.permission.CAMERA", "android.permission.RECORD_AUDIO", null, null, 
        null, null, null, null, null, null, null, null, null, null, 
        "android.permission.WAKE_LOCK", null, null, "android.permission.PACKAGE_USAGE_STATS", null, null, null, null, null, null, 
        null, "android.permission.READ_PHONE_STATE", "com.android.voicemail.permission.ADD_VOICEMAIL", "android.permission.USE_SIP", "android.permission.PROCESS_OUTGOING_CALLS", "android.permission.USE_FINGERPRINT", "android.permission.BODY_SENSORS", "android.permission.READ_CELL_BROADCASTS", null, "android.permission.READ_EXTERNAL_STORAGE", 
        "android.permission.WRITE_EXTERNAL_STORAGE", null, "android.permission.GET_ACCOUNTS", null, null, "android.permission.READ_PHONE_NUMBERS", "android.permission.REQUEST_INSTALL_PACKAGES", null, "android.permission.INSTANT_APP_FOREGROUND_SERVICE", "android.permission.ANSWER_PHONE_CALLS", 
        "android.permission.CHANGE_WIFI_STATE", "android.permission.BLUETOOTH_ADMIN"
    };
    private static String sOpRestrictions[] = {
        "no_share_location", "no_share_location", "no_share_location", null, null, null, "no_outgoing_calls", "no_outgoing_calls", null, null, 
        "no_share_location", null, null, null, "no_sms", "no_sms", "no_sms", null, "no_sms", null, 
        "no_sms", "no_sms", "no_sms", null, "no_create_windows", null, "no_camera", "no_record_audio", null, null, 
        null, null, null, "no_adjust_volume", "no_adjust_volume", "no_adjust_volume", "no_adjust_volume", "no_adjust_volume", "no_adjust_volume", "no_adjust_volume", 
        null, "no_share_location", "no_share_location", null, "no_unmute_microphone", "no_create_windows", null, null, "no_wallpaper", null, 
        null, null, null, null, null, null, null, null, null, null, 
        null, null, null, null, "no_adjust_volume", null, null, null, null, null, 
        null, null
    };
    private static HashMap sOpStrToOp;
    private static String sOpToString[] = {
        "android:coarse_location", "android:fine_location", null, null, "android:read_contacts", "android:write_contacts", "android:read_call_log", "android:write_call_log", "android:read_calendar", "android:write_calendar", 
        null, null, null, "android:call_phone", "android:read_sms", null, "android:receive_sms", null, "android:receive_mms", "android:receive_wap_push", 
        "android:send_sms", null, null, "android:write_settings", "android:system_alert_window", null, "android:camera", "android:record_audio", null, null, 
        null, null, null, null, null, null, null, null, null, null, 
        null, "android:monitor_location", "android:monitor_location_high_power", "android:get_usage_stats", null, null, null, "android:activate_vpn", null, null, 
        null, "android:read_phone_state", "android:add_voicemail", "android:use_sip", "android:process_outgoing_calls", "android:use_fingerprint", "android:body_sensors", "android:read_cell_broadcasts", "android:mock_location", "android:read_external_storage", 
        "android:write_external_storage", null, "android:get_accounts", null, null, "android:read_phone_numbers", null, "android:picture_in_picture", "android:instant_app_start_foreground", "android:answer_phone_calls", 
        "android:change_wifi_state", "android:bluetooth_admin"
    };
    private static int sOpToSwitch[] = {
        0, 0, 0, 3, 4, 5, 6, 7, 8, 9, 
        0, 11, 0, 13, 14, 15, 16, 16, 18, 19, 
        20, 14, 15, 23, 24, 25, 26, 27, 28, 29, 
        30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 
        40, 0, 0, 43, 44, 45, 46, 47, 48, 49, 
        50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 
        60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 
        70, 71
    };
    private static HashMap sPermToOp;
    static IBinder sToken;
    final Context mContext;
    final ArrayMap mModeWatchers = new ArrayMap();
    final IAppOpsService mService;

    static 
    {
        boolean flag = false;
        sOpStrToOp = new HashMap();
        sPermToOp = new HashMap();
        if(sOpToSwitch.length != 72)
            throw new IllegalStateException((new StringBuilder()).append("sOpToSwitch length ").append(sOpToSwitch.length).append(" should be ").append(72).toString());
        if(sOpToString.length != 72)
            throw new IllegalStateException((new StringBuilder()).append("sOpToString length ").append(sOpToString.length).append(" should be ").append(72).toString());
        if(sOpNames.length != 72)
            throw new IllegalStateException((new StringBuilder()).append("sOpNames length ").append(sOpNames.length).append(" should be ").append(72).toString());
        if(sOpPerms.length != 72)
            throw new IllegalStateException((new StringBuilder()).append("sOpPerms length ").append(sOpPerms.length).append(" should be ").append(72).toString());
        if(sOpDefaultMode.length != 72)
            throw new IllegalStateException((new StringBuilder()).append("sOpDefaultMode length ").append(sOpDefaultMode.length).append(" should be ").append(72).toString());
        if(sOpDisableReset.length != 72)
            throw new IllegalStateException((new StringBuilder()).append("sOpDisableReset length ").append(sOpDisableReset.length).append(" should be ").append(72).toString());
        if(sOpRestrictions.length != 72)
            throw new IllegalStateException((new StringBuilder()).append("sOpRestrictions length ").append(sOpRestrictions.length).append(" should be ").append(72).toString());
        if(sOpAllowSystemRestrictionBypass.length != 72)
            throw new IllegalStateException((new StringBuilder()).append("sOpAllowSYstemRestrictionsBypass length ").append(sOpRestrictions.length).append(" should be ").append(72).toString());
        for(int j = 0; j < 72; j++)
            if(sOpToString[j] != null)
                sOpStrToOp.put(sOpToString[j], Integer.valueOf(j));

        int ai[] = RUNTIME_AND_APPOP_PERMISSIONS_OPS;
        int l = ai.length;
        for(int k = ((flag) ? 1 : 0); k < l; k++)
        {
            int i = ai[k];
            if(sOpPerms[i] != null)
                sPermToOp.put(sOpPerms[i], Integer.valueOf(i));
        }

    }

    // Unreferenced inner class android/app/AppOpsManager$1

/* anonymous class */
    class _cls1 extends com.android.internal.app.IAppOpsCallback.Stub
    {

        public void opChanged(int i, int j, String s)
        {
            if(callback instanceof OnOpChangedInternalListener)
                ((OnOpChangedInternalListener)callback).onOpChanged(i, s);
            if(AppOpsManager._2D_get0()[i] != null)
                callback.onOpChanged(AppOpsManager._2D_get0()[i], s);
        }

        final AppOpsManager this$0;
        final OnOpChangedListener val$callback;

            
            {
                this$0 = AppOpsManager.this;
                callback = onopchangedlistener;
                super();
            }
    }

}
