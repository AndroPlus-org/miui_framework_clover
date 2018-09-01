// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.app.ActivityManager;
import android.bluetooth.BleScanWrapper;
import android.bluetooth.IBluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.ILocationPolicyListener;
import android.location.LocationPolicyManager;
import android.os.*;
import android.util.*;
import com.google.android.collect.Maps;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.*;

// Referenced classes of package com.miui.whetstone:
//            AlarmPolicy, BroadcastPolicy, IPowerKeeperClient

public class PowerKeeperPolicy extends IPowerKeeperPolicy.Stub
{
    class Client
    {

        static int _2D_get0(Client client)
        {
            return client.mUid;
        }

        public void clearResource()
        {
            mBleScanWrapper = null;
            mIBinder = null;
            mDeathRecipient = null;
        }

        public boolean getAllowed()
        {
            return mAllowed;
        }

        public boolean getScanning()
        {
            return mScanning;
        }

        public void linkToDeath(android.os.IBinder.DeathRecipient deathrecipient)
        {
            mIBinder.linkToDeath(deathrecipient, 0);
            mDeathRecipient = deathrecipient;
_L1:
            return;
            deathrecipient;
            Log.e("PowerKeeperPolicy", (new StringBuilder()).append("Unable to link deathRecipient for client: ").append(mBleScanWrapper).toString());
              goto _L1
        }

        public void setAllowed(boolean flag)
        {
            if(PowerKeeperPolicy._2D_get0())
                Log.d("PowerKeeperPolicy", (new StringBuilder()).append("uid = ").append(mUid).append(" setAllowed, allowed = ").append(flag).toString());
            mAllowed = flag;
        }

        public void setScanning(boolean flag)
        {
            if(PowerKeeperPolicy._2D_get0())
                Log.d("PowerKeeperPolicy", (new StringBuilder()).append("uid = ").append(mUid).append(" setScanning, scanning = ").append(flag).toString());
            mScanning = flag;
        }

        public void startLeScan()
        {
            if(mScanning)
            {
                if(PowerKeeperPolicy._2D_get0())
                    Log.d("PowerKeeperPolicy", (new StringBuilder()).append("startLeScan, uid = ").append(mUid).append(" is scanning").toString());
                return;
            }
            Object obj = ServiceManager.getService("bluetooth_manager");
            if(obj == null) goto _L2; else goto _L1
_L1:
            obj = android.bluetooth.IBluetoothManager.Stub.asInterface(((IBinder) (obj))).getBluetoothGatt();
            mBleScanWrapper.startScan(((android.bluetooth.IBluetoothGatt) (obj)));
_L3:
            if(PowerKeeperPolicy._2D_get0())
            {
                StringBuilder stringbuilder = JVM INSTR new #59  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("PowerKeeperPolicy", stringbuilder.append("ForceStartLeScan, client: ").append(mBleScanWrapper).toString());
            }
_L2:
            mScanning = true;
            return;
            Object obj1;
            obj1;
            Log.e("PowerKeeperPolicy", "startLeScan", ((Throwable) (obj1)));
              goto _L3
            obj1;
            Log.e("PowerKeeperPolicy", "startLeScan", ((Throwable) (obj1)));
              goto _L2
        }

        public void stopLeScan()
        {
            if(!mScanning)
            {
                if(PowerKeeperPolicy._2D_get0())
                    Log.d("PowerKeeperPolicy", (new StringBuilder()).append("stopLeScan, uid = ").append(mUid).append(" is not scanning").toString());
                return;
            }
            Object obj = ServiceManager.getService("bluetooth_manager");
            if(obj == null) goto _L2; else goto _L1
_L1:
            obj = android.bluetooth.IBluetoothManager.Stub.asInterface(((IBinder) (obj))).getBluetoothGatt();
            mBleScanWrapper.stopScan(((android.bluetooth.IBluetoothGatt) (obj)));
_L3:
            if(PowerKeeperPolicy._2D_get0())
            {
                StringBuilder stringbuilder = JVM INSTR new #59  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("PowerKeeperPolicy", stringbuilder.append("ForceStopLeScan, client: ").append(mBleScanWrapper).toString());
            }
_L2:
            mScanning = false;
            return;
            Object obj1;
            obj1;
            Log.e("PowerKeeperPolicy", "stopLeScan", ((Throwable) (obj1)));
              goto _L3
            obj1;
            Log.e("PowerKeeperPolicy", "stopLeScan", ((Throwable) (obj1)));
              goto _L2
        }

        public void unlinkToDeath()
        {
            if(mDeathRecipient == null)
                break MISSING_BLOCK_LABEL_27;
            mIBinder.unlinkToDeath(mDeathRecipient, 0);
            mDeathRecipient = null;
_L1:
            return;
            NoSuchElementException nosuchelementexception;
            nosuchelementexception;
            Log.e("PowerKeeperPolicy", (new StringBuilder()).append("Unable to unlink deathRecipient for client: ").append(mBleScanWrapper).toString());
              goto _L1
        }

        private boolean mAllowed;
        private BleScanWrapper mBleScanWrapper;
        private android.os.IBinder.DeathRecipient mDeathRecipient;
        private IBinder mIBinder;
        private boolean mScanning;
        private int mUid;
        final PowerKeeperPolicy this$0;

        public Client(BleScanWrapper blescanwrapper, IBinder ibinder, int i)
        {
            this$0 = PowerKeeperPolicy.this;
            super();
            mBleScanWrapper = blescanwrapper;
            mIBinder = ibinder;
            mUid = i;
            mDeathRecipient = null;
            mScanning = false;
            mAllowed = false;
        }
    }

    class ClientDeathRecipient
        implements android.os.IBinder.DeathRecipient
    {

        public void binderDied()
        {
            if(PowerKeeperPolicy._2D_get0())
                Log.d("PowerKeeperPolicy", (new StringBuilder()).append("Binder is dead - unregistering client (").append(mBleScanWrapper).append(")!").toString());
            Object obj = PowerKeeperPolicy._2D_get1(PowerKeeperPolicy.this);
            obj;
            JVM INSTR monitorenter ;
            boolean flag;
            Client client;
            flag = PowerKeeperPolicy._2D_wrap0(PowerKeeperPolicy.this);
            client = (Client)PowerKeeperPolicy._2D_get2(PowerKeeperPolicy.this).get(mBleScanWrapper);
            if(client == null)
                break MISSING_BLOCK_LABEL_106;
            client.unlinkToDeath();
            client.clearResource();
            PowerKeeperPolicy._2D_get2(PowerKeeperPolicy.this).remove(mBleScanWrapper);
            boolean flag1 = PowerKeeperPolicy._2D_wrap0(PowerKeeperPolicy.this);
            if(!flag || !(flag1 ^ true))
                break MISSING_BLOCK_LABEL_148;
            if(PowerKeeperPolicy._2D_get5(PowerKeeperPolicy.this).isInParoleState() ^ true)
                PowerKeeperPolicy._2D_wrap3(PowerKeeperPolicy.this);
            if(flag1)
                break MISSING_BLOCK_LABEL_178;
            if(PowerKeeperPolicy._2D_get5(PowerKeeperPolicy.this).isWorking() ^ true)
                PowerKeeperPolicy._2D_get5(PowerKeeperPolicy.this).startParoleCheck();
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        BleScanWrapper mBleScanWrapper;
        final PowerKeeperPolicy this$0;

        public ClientDeathRecipient(BleScanWrapper blescanwrapper)
        {
            this$0 = PowerKeeperPolicy.this;
            super();
            mBleScanWrapper = blescanwrapper;
        }
    }

    class ParoleCheck
    {

        static Object _2D_get0(ParoleCheck parolecheck)
        {
            return parolecheck.mParoleLock;
        }

        static boolean _2D_wrap0(ParoleCheck parolecheck, Message message)
        {
            return parolecheck.onParoleMessageHandlerLocked(message);
        }

        private void enterParoleAndNotifyLocked()
        {
            mInParole = true;
            mNotifyHandler.obtainMessage(mMsgWhat, 1, 0).sendToTarget();
        }

        private void enterParoleLocked()
        {
            mInParole = true;
        }

        private void exitParoleAndNotifyLocked()
        {
            mInParole = false;
            mNotifyHandler.obtainMessage(mMsgWhat, 0, 0).sendToTarget();
        }

        private void exitParoleLocked()
        {
            mInParole = false;
        }

        private boolean onParoleMessageHandlerLocked(Message message)
        {
            boolean flag = true;
            message.what;
            JVM INSTR tableswitch 1001 1002: default 28
        //                       1001 32
        //                       1002 87;
               goto _L1 _L2 _L3
_L1:
            boolean flag1 = false;
_L5:
            return flag1;
_L2:
            flag1 = flag;
            if(state == 1)
            {
                state = 4;
                mParoleIndex = 0;
                flag1 = flag;
                if(mParoleArray != null)
                {
                    long l = mParoleArray[0];
                    mParoleCheckHandler.sendEmptyMessageDelayed(1002, l);
                    flag1 = flag;
                }
            }
            continue; /* Loop/switch isn't completed */
_L3:
            flag1 = flag;
            if(state == 4)
                if(mParoleArray == null)
                {
                    Log.e("PowerKeeperPolicy", "Parole check array is null");
                    flag1 = flag;
                } else
                {
                    long l1;
                    int i;
                    if(mParoleIndex % 2 == 0)
                        enterParoleAndNotifyLocked();
                    else
                        exitParoleAndNotifyLocked();
                    i = mParoleArray.length;
                    if(mParoleIndex + 1 >= i)
                        mParoleIndex = mParoleIndex - 1;
                    else
                        mParoleIndex = mParoleIndex + 1;
                    l1 = mParoleArray[mParoleIndex];
                    mParoleCheckHandler.sendEmptyMessageDelayed(1002, l1);
                    flag1 = flag;
                }
            if(true) goto _L5; else goto _L4
_L4:
        }

        public boolean isInParoleState()
        {
            Object obj = mParoleLock;
            obj;
            JVM INSTR monitorenter ;
            boolean flag = mInParole;
            obj;
            JVM INSTR monitorexit ;
            return flag;
            Exception exception;
            exception;
            throw exception;
        }

        public boolean isWorking()
        {
            Object obj = mParoleLock;
            obj;
            JVM INSTR monitorenter ;
            int i = state;
            if(i == 0)
                return false;
            obj;
            JVM INSTR monitorexit ;
            return true;
            Exception exception;
            exception;
            throw exception;
        }

        public void setParoleCheckParam(long al[])
        {
            Object obj = mParoleLock;
            obj;
            JVM INSTR monitorenter ;
            int i;
            i = state;
            if(state == 4)
            {
                if(isInParoleState())
                    exitParoleAndNotifyLocked();
                PowerKeeperPolicy._2D_wrap3(PowerKeeperPolicy.this);
            }
            mParoleIndex = 0;
            if(al == null) goto _L2; else goto _L1
_L1:
            if(al.length != 0) goto _L3; else goto _L2
_L2:
            mParoleArray = null;
_L5:
            if(i != 4)
                break MISSING_BLOCK_LABEL_66;
            startParoleCheck();
            obj;
            JVM INSTR monitorexit ;
            return;
_L3:
            if(al.length % 2 == 0)
                mParoleArray = Arrays.copyOf(al, al.length);
            if(true) goto _L5; else goto _L4
_L4:
            al;
            throw al;
        }

        public void startParoleCheck()
        {
            Object obj = mParoleLock;
            obj;
            JVM INSTR monitorenter ;
            if(state == 0)
            {
                state = 1;
                exitParoleLocked();
                mParoleCheckHandler.sendEmptyMessage(1001);
            }
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void stopParoleCheck()
        {
            Object obj = mParoleLock;
            obj;
            JVM INSTR monitorenter ;
            if(state != 0)
            {
                state = 0;
                exitParoleLocked();
                mParoleCheckHandler.removeCallbacksAndMessages(null);
            }
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private static final int MSG_PAROLE_CHECK_RUNNING = 1002;
        private static final int MSG_START_PAROLE_CHECK = 1001;
        private static final int STATE_RUNNING = 4;
        private static final int STATE_STARTED = 3;
        private static final int STATE_STARTING = 1;
        private static final int STATE_STOPPED = 0;
        private static final int STATE_STOPPING = 2;
        private boolean mInParole;
        private int mMsgWhat;
        private Handler mNotifyHandler;
        private long mParoleArray[] = {
            0x6ddd000L, 30000L
        };
        private Handler mParoleCheckHandler;
        private int mParoleIndex;
        private Object mParoleLock;
        private int state;
        final PowerKeeperPolicy this$0;

        public ParoleCheck(Handler handler, int i)
        {
            this$0 = PowerKeeperPolicy.this;
            super();
            mParoleIndex = 0;
            mInParole = false;
            state = 0;
            mParoleLock = new Object();
            mNotifyHandler = handler;
            mMsgWhat = i;
            powerkeeperpolicy = new _cls1();
            mParoleCheckHandler = new Handler(mNotifyHandler.getLooper(), PowerKeeperPolicy.this);
        }
    }


    static boolean _2D_get0()
    {
        return DEBUG;
    }

    static Object _2D_get1(PowerKeeperPolicy powerkeeperpolicy)
    {
        return powerkeeperpolicy.mBleLock;
    }

    static Map _2D_get2(PowerKeeperPolicy powerkeeperpolicy)
    {
        return powerkeeperpolicy.mClientMap;
    }

    static boolean _2D_get3(PowerKeeperPolicy powerkeeperpolicy)
    {
        return powerkeeperpolicy.mLeScanFeatureEnable;
    }

    static Handler _2D_get4(PowerKeeperPolicy powerkeeperpolicy)
    {
        return powerkeeperpolicy.mLeScanHandler;
    }

    static ParoleCheck _2D_get5(PowerKeeperPolicy powerkeeperpolicy)
    {
        return powerkeeperpolicy.mParoleCheck;
    }

    static SparseBooleanArray _2D_get6(PowerKeeperPolicy powerkeeperpolicy)
    {
        return powerkeeperpolicy.mUidAllow;
    }

    static IPowerKeeperClient _2D_set0(PowerKeeperPolicy powerkeeperpolicy, IPowerKeeperClient ipowerkeeperclient)
    {
        powerkeeperpolicy.sPowerKeeperService = ipowerkeeperclient;
        return ipowerkeeperclient;
    }

    static boolean _2D_wrap0(PowerKeeperPolicy powerkeeperpolicy)
    {
        return powerkeeperpolicy.checkLeScanAllowedLocked();
    }

    static boolean _2D_wrap1(PowerKeeperPolicy powerkeeperpolicy, Message message)
    {
        return powerkeeperpolicy.onLeScanMessageHandler(message);
    }

    static void _2D_wrap2(PowerKeeperPolicy powerkeeperpolicy)
    {
        powerkeeperpolicy.restoreFakeGpsStatus();
    }

    static void _2D_wrap3(PowerKeeperPolicy powerkeeperpolicy)
    {
        powerkeeperpolicy.stopLeScanAllLocked();
    }

    private PowerKeeperPolicy(ClassLoader classloader)
    {
        mBlockedUidWakelocks = new SparseArray();
        pushAlarmLeaderIntent = null;
        mAppBGIdleFeatureStatus = false;
        mLeScanFeatureEnable = false;
        mClientMap = Maps.newHashMap();
        mUidAllow = new SparseBooleanArray();
        mUidScanning = new SparseBooleanArray();
        mLocationPolicyListener = new android.location.ILocationPolicyListener.Stub() {

            public void onRestrictBackgroundChanged(boolean flag)
            {
            }

            public void onUidRulesChanged(int i, int j)
            {
                boolean flag = true;
                Object obj = PowerKeeperPolicy._2D_get1(PowerKeeperPolicy.this);
                obj;
                JVM INSTR monitorenter ;
                boolean flag1;
                boolean flag2;
                if(j == 0 || j == 1)
                    flag1 = true;
                else
                    flag1 = false;
                flag2 = PowerKeeperPolicy._2D_get6(PowerKeeperPolicy.this).get(i, true);
                if(flag2 != flag1)
                    break MISSING_BLOCK_LABEL_57;
                obj;
                JVM INSTR monitorexit ;
                return;
                PowerKeeperPolicy._2D_get6(PowerKeeperPolicy.this).put(i, flag1);
                flag2 = PowerKeeperPolicy._2D_get3(PowerKeeperPolicy.this);
                if(flag2)
                    break MISSING_BLOCK_LABEL_88;
                obj;
                JVM INSTR monitorexit ;
                return;
                Handler handler = PowerKeeperPolicy._2D_get4(PowerKeeperPolicy.this);
                if(flag1)
                    j = ((flag) ? 1 : 0);
                else
                    j = 0;
                handler.obtainMessage(1000, i, j).sendToTarget();
                obj;
                JVM INSTR monitorexit ;
                return;
                Exception exception;
                exception;
                throw exception;
            }

            final PowerKeeperPolicy this$0;

            
            {
                this$0 = PowerKeeperPolicy.this;
                super();
            }
        }
;
        mAlarmRestricts = new SparseArray();
        mBroadcastRestricts = new SparseArray();
        mAlarmTypeData = new SparseIntArray();
        mAlarmUidData = new SparseIntArray();
        mAlarmDataTotal = 0;
        mBroadcastTypeData = new HashMap();
        mBroadcastUidData = new SparseIntArray();
        mBroadcastDataTotal = 0;
        pushAlarmLeaderIntent = null;
        pushAlarmLeaderUid = -1;
        try
        {
            mPowerManagerServiceInjector = Class.forName("com.android.server.power.PowerManagerServiceInjector", false, classloader);
            setUidWakeLockDisabledState = mPowerManagerServiceInjector.getDeclaredMethod("setUidPartialWakeLockDisabledState", new Class[] {
                Integer.TYPE, java/lang/String, Boolean.TYPE
            });
        }
        // Misplaced declaration of an exception variable
        catch(ClassLoader classloader)
        {
            Log.e("PowerKeeperPolicy", "PowerKeeperPolicy", classloader);
        }
        sPowerKeeperService = null;
        mContext = null;
    }

    private boolean checkLeScanAllowedLocked()
    {
        boolean flag = false;
        Object obj = mBleLock;
        obj;
        JVM INSTR monitorenter ;
        Iterator iterator = mClientMap.entrySet().iterator();
_L2:
        boolean flag1 = flag;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        flag1 = ((Client)((java.util.Map.Entry)iterator.next()).getValue()).getAllowed();
        if(!flag1)
            continue; /* Loop/switch isn't completed */
        flag1 = true;
        break; /* Loop/switch isn't completed */
        if(true) goto _L2; else goto _L1
_L1:
        if(DEBUG)
            Log.d("PowerKeeperPolicy", (new StringBuilder()).append("checkLeScanAllowedLocked: ret = ").append(flag1).toString());
        return flag1;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean checkLeScanParoleLocked()
    {
        boolean flag = mParoleCheck.isInParoleState();
        if(DEBUG)
            Log.d("PowerKeeperPolicy", (new StringBuilder()).append("checkLeScanParoleLocked: ret = ").append(flag).toString());
        return flag;
    }

    private boolean checkLeScanRunningLocked()
    {
        boolean flag = false;
        Object obj = mBleLock;
        obj;
        JVM INSTR monitorenter ;
        Iterator iterator = mClientMap.entrySet().iterator();
_L2:
        boolean flag1 = flag;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        flag1 = ((Client)((java.util.Map.Entry)iterator.next()).getValue()).getScanning();
        if(!flag1)
            continue; /* Loop/switch isn't completed */
        flag1 = true;
        break; /* Loop/switch isn't completed */
        if(true) goto _L2; else goto _L1
_L1:
        if(DEBUG)
            Log.d("PowerKeeperPolicy", (new StringBuilder()).append("checkLeScanRunningLocked: ret = ").append(flag1).toString());
        return flag1;
        Exception exception;
        exception;
        throw exception;
    }

    private void dumpBrdCastManageInfo(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        JSONArray jsonarray;
        printwriter.println("---------------------------------------------");
        jsonarray = new JSONArray();
        SparseArray sparsearray = mBrdcastUidTypeInfo;
        sparsearray;
        JVM INSTR monitorenter ;
        int i = 0;
_L2:
        JSONObject jsonobject;
        if(i >= mBrdcastUidTypeInfo.size())
            break MISSING_BLOCK_LABEL_207;
        jsonobject = JVM INSTR new #355 <Class JSONObject>;
        jsonobject.JSONObject();
        as = getPkgNameByUid(mBrdcastUidTypeInfo.keyAt(i));
        filedescriptor = as;
        if(as != null)
            break MISSING_BLOCK_LABEL_83;
        filedescriptor = Integer.toString(mBrdcastUidTypeInfo.keyAt(i));
        JSONArray jsonarray1;
        jsonobject.put("pkgName", filedescriptor);
        jsonarray1 = JVM INSTR new #348 <Class JSONArray>;
        jsonarray1.JSONArray();
        JSONObject jsonobject1;
        for(as = ((Map)mBrdcastUidTypeInfo.valueAt(i)).entrySet().iterator(); as.hasNext(); jsonarray1.put(jsonobject1))
        {
            filedescriptor = (java.util.Map.Entry)as.next();
            jsonobject1 = JVM INSTR new #355 <Class JSONObject>;
            jsonobject1.JSONObject();
            jsonobject1.put("Name", filedescriptor.getKey());
            jsonobject1.put("Cnts", filedescriptor.getValue());
        }

        break MISSING_BLOCK_LABEL_246;
        filedescriptor;
        Log.e("PowerKeeperPolicy", "dumpBrdCastManageInfo", filedescriptor);
        sparsearray;
        JVM INSTR monitorexit ;
        printwriter.println((new StringBuilder()).append("broadCastInfo:").append(jsonarray.toString()).toString());
        printwriter.println("---------------------------------------------");
        return;
        jsonobject.put("broadcast", jsonarray1);
        jsonarray.put(jsonobject);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        filedescriptor;
        throw filedescriptor;
    }

    private Intent getAppPushAlarmLeaderIntent()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Intent intent = pushAlarmLeaderIntent;
        obj;
        JVM INSTR monitorexit ;
        return intent;
        Exception exception;
        exception;
        throw exception;
    }

    public static PowerKeeperPolicy getInstance()
    {
        if(sInstance == null)
            sInstance = new PowerKeeperPolicy(Thread.currentThread().getContextClassLoader());
        return sInstance;
    }

    private String getPkgNameByUid(int i)
    {
        String as[] = mContext.getPackageManager().getPackagesForUid(i);
        if(as == null || as.length == 0)
            return null;
        else
            return as[0];
    }

    private boolean hasAlarmRestrict(int i, int j)
    {
        boolean flag = false;
        Object obj = mPolicyLock;
        obj;
        JVM INSTR monitorenter ;
        int ai[] = (int[])mAlarmRestricts.get(i);
        obj;
        JVM INSTR monitorexit ;
        boolean flag1 = flag;
        if(ai == null) goto _L2; else goto _L1
_L1:
        i = 0;
_L7:
        flag1 = flag;
        if(i >= ai.length) goto _L2; else goto _L3
_L3:
        if(ai[i] != j) goto _L5; else goto _L4
_L4:
        flag1 = true;
_L2:
        return flag1;
        Exception exception;
        exception;
        throw exception;
_L5:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private boolean hasBroadcastRestrict(int i, String s)
    {
        boolean flag = false;
        Object obj = mPolicyLock;
        obj;
        JVM INSTR monitorenter ;
        String as[] = (String[])mBroadcastRestricts.get(i);
        obj;
        JVM INSTR monitorexit ;
        boolean flag1;
        flag1 = flag;
        if(as != null)
        {
            flag1 = flag;
            if(s != null)
            {
                if(as.length == 1 && "a.jack.bc.1".equals(as[0]))
                    return true;
                break MISSING_BLOCK_LABEL_70;
            }
        }
          goto _L1
        s;
        throw s;
        i = 0;
_L6:
        flag1 = flag;
        if(i >= as.length) goto _L1; else goto _L2
_L2:
        if(!as[i].equals(s)) goto _L4; else goto _L3
_L3:
        flag1 = true;
_L1:
        return flag1;
_L4:
        i++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private boolean onLeScanMessageHandler(Message message)
    {
        boolean flag = true;
        message.what;
        JVM INSTR tableswitch 1000 1001: default 28
    //                   1000 32
    //                   1001 264;
           goto _L1 _L2 _L3
_L1:
        boolean flag1 = false;
_L5:
        return flag1;
_L2:
        flag1 = flag;
        if(mLeScanFeatureEnable)
        {
            int i = message.arg1;
            if(message.arg2 > 0)
                flag1 = true;
            else
                flag1 = false;
            if(DEBUG)
                Log.d("PowerKeeperPolicy", (new StringBuilder()).append("leScan uid rule change, uid = ").append(i).append(", allow = ").append(flag1).toString());
            if(flag1)
            {
                boolean flag2 = checkLeScanAllowedLocked();
                setLeScanAllowedLocked(i, flag1);
                boolean flag4 = checkLeScanAllowedLocked();
                if(!flag2 && flag4 && mParoleCheck.isInParoleState() ^ true)
                    startLeScanAllLocked();
                flag1 = flag;
                if(flag4)
                {
                    flag1 = flag;
                    if(mParoleCheck.isWorking())
                    {
                        mParoleCheck.stopParoleCheck();
                        flag1 = flag;
                    }
                }
            } else
            {
                boolean flag5 = checkLeScanAllowedLocked();
                setLeScanAllowedLocked(i, flag1);
                boolean flag3 = checkLeScanAllowedLocked();
                if(flag5 && flag3 ^ true && mParoleCheck.isInParoleState() ^ true)
                    stopLeScanAllLocked();
                flag1 = flag;
                if(!flag3)
                {
                    flag1 = flag;
                    if(mParoleCheck.isWorking() ^ true)
                    {
                        mParoleCheck.startParoleCheck();
                        flag1 = flag;
                    }
                }
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        flag1 = flag;
        if(mLeScanFeatureEnable)
            if(checkLeScanAllowedLocked())
            {
                Log.e("PowerKeeperPolicy", "le scan is allowed but parole check is still running");
                flag1 = flag;
            } else
            {
                if(message.arg1 > 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(DEBUG)
                    Log.d("PowerKeeperPolicy", (new StringBuilder()).append("leScan parole change, inParole = ").append(flag1).toString());
                if(flag1)
                {
                    startLeScanAllLocked();
                    flag1 = flag;
                } else
                {
                    stopLeScanAllLocked();
                    flag1 = flag;
                }
            }
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void restoreFakeGpsStatus()
    {
        Log.d("PowerKeeperPolicy", "restore miui gps status");
        LocationPolicyManager.from(mContext).setPhoneStationary(false, null);
        LocationPolicyManager.from(mContext).setFakeGpsFeatureOnState(false);
    }

    private void setLeScanAllowedLocked(int i, boolean flag)
    {
        Iterator iterator = mClientMap.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            if(Client._2D_get0((Client)entry.getValue()) == i)
            {
                ((Client)entry.getValue()).setAllowed(flag);
                if(DEBUG)
                    Log.d("PowerKeeperPolicy", (new StringBuilder()).append("setLeScanAllowedLocked: uid = ").append(i).append(", allow = ").append(flag).toString());
            }
        } while(true);
    }

    private void startLeScanAllLocked()
    {
        if(DEBUG)
            Log.d("PowerKeeperPolicy", "startLeScanAllLocked");
        for(Iterator iterator = mClientMap.entrySet().iterator(); iterator.hasNext(); ((Client)((java.util.Map.Entry)iterator.next()).getValue()).startLeScan());
    }

    private void stopLeScanAllLocked()
    {
        if(DEBUG)
            Log.d("PowerKeeperPolicy", "stopLeScanAllLocked");
        for(Iterator iterator = mClientMap.entrySet().iterator(); iterator.hasNext(); ((Client)((java.util.Map.Entry)iterator.next()).getValue()).stopLeScan());
    }

    public void bleScanInit()
    {
        if(mContext != null)
        {
            HandlerThread handlerthread = new HandlerThread("PowerKeeperPolicy");
            handlerthread.start();
            android.os.Handler.Callback callback = new android.os.Handler.Callback() {

                public boolean handleMessage(Message message)
                {
                    Object obj = PowerKeeperPolicy._2D_get1(PowerKeeperPolicy.this);
                    obj;
                    JVM INSTR monitorenter ;
                    boolean flag = PowerKeeperPolicy._2D_wrap1(PowerKeeperPolicy.this, message);
                    obj;
                    JVM INSTR monitorexit ;
                    return flag;
                    message;
                    throw message;
                }

                final PowerKeeperPolicy this$0;

            
            {
                this$0 = PowerKeeperPolicy.this;
                super();
            }
            }
;
            mLeScanHandler = new Handler(handlerthread.getLooper(), callback);
            mParoleCheck = new ParoleCheck(mLeScanHandler, 1001);
            LocationPolicyManager.from(mContext).registerListener(mLocationPolicyListener);
        }
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.println("\nDump of PowerKeeperPolicy:\n");
        int i = 0;
        for(int k = as.length; i < k; i++)
            if(as[i].equalsIgnoreCase("broadCastInfo"))
            {
                dumpBrdCastManageInfo(filedescriptor, printwriter, as);
                return;
            }

        printwriter.println("====mAlarmRestricts:");
        for(i = 0; i < mAlarmRestricts.size(); i++)
        {
            printwriter.print((new StringBuilder()).append("[").append(mAlarmRestricts.keyAt(i)).append("] = ").toString());
            filedescriptor = (int[])mAlarmRestricts.get(mAlarmRestricts.keyAt(i));
            int l = 0;
            for(int i2 = filedescriptor.length; l < i2; l++)
            {
                int j3 = filedescriptor[l];
                printwriter.print((new StringBuilder()).append("").append(getAlarmName(j3)).append(", ").toString());
            }

            printwriter.print("\n");
        }

        printwriter.println("\n====mBroadcastRestricts:");
        for(i = 0; i < mBroadcastRestricts.size(); i++)
        {
            printwriter.println((new StringBuilder()).append("------[").append(mBroadcastRestricts.keyAt(i)).append("] = ").toString());
            filedescriptor = (String[])mBroadcastRestricts.get(mBroadcastRestricts.keyAt(i));
            int i1 = 0;
            for(int j2 = filedescriptor.length; i1 < j2; i1++)
            {
                as = filedescriptor[i1];
                printwriter.println((new StringBuilder()).append("----------").append(as).toString());
            }

        }

        printwriter.println((new StringBuilder()).append("\n====Total block alarm ").append(mAlarmDataTotal).append(" times").toString());
        for(i = 0; i < mAlarmTypeData.size(); i++)
        {
            int j1 = mAlarmTypeData.keyAt(i);
            int k2 = mAlarmTypeData.get(j1);
            printwriter.println((new StringBuilder()).append("mAlarmTypeData[").append(getAlarmName(j1)).append("] = ").append(k2).toString());
        }

        for(i = 0; i < mAlarmUidData.size(); i++)
        {
            int l2 = mAlarmUidData.keyAt(i);
            int k1 = mAlarmUidData.get(l2);
            printwriter.println((new StringBuilder()).append("mAlarmUidData[").append(l2).append("] = ").append(k1).toString());
        }

        printwriter.println((new StringBuilder()).append("\n====Total block broadcast ").append(mBroadcastDataTotal).append(" times").toString());
        for(filedescriptor = mBroadcastTypeData.entrySet().iterator(); filedescriptor.hasNext(); printwriter.println((new StringBuilder()).append("mBroadcastTypeData[").append((String)as.getKey()).append("] = ").append(as.getValue()).toString()))
            as = (java.util.Map.Entry)filedescriptor.next();

        for(i = 0; i < mBroadcastUidData.size(); i++)
        {
            int l1 = mBroadcastUidData.keyAt(i);
            int i3 = mBroadcastUidData.get(l1);
            printwriter.println((new StringBuilder()).append("mBroadcastUidData[").append(l1).append("] = ").append(i3).toString());
        }

        printwriter.println("\n====mBlockedWakelocks====\n\t");
        i = 0;
        while(i < mBlockedUidWakelocks.size()) 
        {
            printwriter.print((new StringBuilder()).append(" uid:").append(mBlockedUidWakelocks.keyAt(i)).append("\t").toString());
            if(mBlockedUidWakelocks.valueAt(i) != null)
                printwriter.println((new StringBuilder()).append(" tags:").append(((ArrayList)mBlockedUidWakelocks.valueAt(i)).toString()).toString());
            else
                printwriter.println();
            i++;
        }
        printwriter.println("\n====mUidBroadcastStat====\n\t");
        filedescriptor = mUidBroadcastStat;
        filedescriptor;
        JVM INSTR monitorenter ;
        int j = 0;
_L2:
        if(j >= mUidBroadcastStat.size())
            break; /* Loop/switch isn't completed */
        as = JVM INSTR new #304 <Class StringBuilder>;
        as.StringBuilder();
        printwriter.println(as.append(" uid:").append(mUidBroadcastStat.keyAt(j)).append(" policy = ").append(mUidBroadcastStat.valueAt(j)).toString());
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        printwriter.println((new StringBuilder()).append("\n====AppBGIdleFeatureIs====").append(getAppBGIdleFeatureEnable()).toString());
        return;
        printwriter;
        throw printwriter;
    }

    public String getAlarmName(int i)
    {
        switch(i)
        {
        default:
            return null;

        case 0: // '\0'
            return "RTC_WAKEUP";

        case 1: // '\001'
            return "RTC";

        case 2: // '\002'
            return "ELAPSED_REALTIME_WAKEUP";

        case 3: // '\003'
            return "ELAPSED_REALTIME";
        }
    }

    public AlarmPolicy[] getAlarmPolicies()
    {
        AlarmPolicy aalarmpolicy[] = new AlarmPolicy[mAlarmRestricts.size()];
        for(int i = 0; i < mAlarmRestricts.size(); i++)
        {
            aalarmpolicy[i].mUid = mAlarmRestricts.keyAt(i);
            aalarmpolicy[i].mRestrictTypes = (int[])mAlarmRestricts.get(aalarmpolicy[i].mUid);
        }

        return aalarmpolicy;
    }

    public boolean getAppBGIdleFeatureEnable()
    {
        SparseIntArray sparseintarray = mAppBGIdleLevel;
        sparseintarray;
        JVM INSTR monitorenter ;
        boolean flag = mAppBGIdleFeatureStatus;
        sparseintarray;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public int getAppBGIdleLevel(int i)
    {
        if(!UserHandle.isApp(i))
            return 0;
        SparseIntArray sparseintarray = mAppBGIdleLevel;
        sparseintarray;
        JVM INSTR monitorenter ;
        i = mAppBGIdleLevel.get(i, 0);
        sparseintarray;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean getAppBroadcastControlStat(int i)
    {
        if(!UserHandle.isApp(i))
            return false;
        SparseBooleanArray sparsebooleanarray = mUidBroadcastStat;
        sparsebooleanarray;
        JVM INSTR monitorenter ;
        boolean flag = mUidBroadcastStat.get(i);
        sparsebooleanarray;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean getAppPushAlarmFunc(int i)
    {
        SparseBooleanArray sparsebooleanarray = mUidPushAlarmStat;
        sparsebooleanarray;
        JVM INSTR monitorenter ;
        boolean flag = mUidPushAlarmStat.get(i, false);
        sparsebooleanarray;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public int getAppPushAlarmLeaderUid()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = pushAlarmLeaderUid;
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public Intent getAppPushAlarmProperty(int i)
    {
        SparseArray sparsearray = mUidPushAlarmProperty;
        sparsearray;
        JVM INSTR monitorenter ;
        Intent intent = (Intent)mUidPushAlarmProperty.get(i);
        sparsearray;
        JVM INSTR monitorexit ;
        return intent;
        Exception exception;
        exception;
        throw exception;
    }

    public BroadcastPolicy[] getBroadcastPolicies()
    {
        BroadcastPolicy abroadcastpolicy[] = new BroadcastPolicy[mBroadcastRestricts.size()];
        int i = 0;
        while(i < mBroadcastRestricts.size()) 
        {
            if(abroadcastpolicy[i] != null)
            {
                abroadcastpolicy[i].mUid = mBroadcastRestricts.keyAt(i);
                abroadcastpolicy[i].mRestrictTypes = (String[])mBroadcastRestricts.get(abroadcastpolicy[i].mUid);
            }
            i++;
        }
        return abroadcastpolicy;
    }

    public int getOomAdjByPid(Context context, int i)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        Context context1;
        Object obj10;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        obj5 = null;
        obj6 = null;
        obj7 = null;
        obj8 = null;
        obj9 = obj6;
        context1 = ((Context) (obj1));
        obj10 = obj3;
        context = JVM INSTR new #669 <Class FileInputStream>;
        obj9 = obj6;
        context1 = ((Context) (obj1));
        obj10 = obj3;
        File file = JVM INSTR new #671 <Class File>;
        obj9 = obj6;
        context1 = ((Context) (obj1));
        obj10 = obj3;
        StringBuilder stringbuilder = JVM INSTR new #304 <Class StringBuilder>;
        obj9 = obj6;
        context1 = ((Context) (obj1));
        obj10 = obj3;
        stringbuilder.StringBuilder();
        obj9 = obj6;
        context1 = ((Context) (obj1));
        obj10 = obj3;
        file.File(stringbuilder.append("/proc/").append(i).append("/oom_adj").toString());
        obj9 = obj6;
        context1 = ((Context) (obj1));
        obj10 = obj3;
        context.FileInputStream(file);
        obj1 = JVM INSTR new #681 <Class InputStreamReader>;
        ((InputStreamReader) (obj1)).InputStreamReader(context);
        obj5 = JVM INSTR new #686 <Class BufferedReader>;
        ((BufferedReader) (obj5)).BufferedReader(((java.io.Reader) (obj1)));
        obj9 = ((BufferedReader) (obj5)).readLine();
        if(obj5 != null)
            try
            {
                ((BufferedReader) (obj5)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj5) { }
        if(obj1 != null)
            try
            {
                ((InputStreamReader) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj1) { }
        if(context != null)
            try
            {
                context.close();
            }
            // Misplaced declaration of an exception variable
            catch(Context context) { }
_L2:
        if(obj9 != null)
            return Integer.valueOf(((String) (obj9))).intValue();
        else
            return -100;
        obj1;
        context = obj2;
_L5:
        obj9 = obj8;
        context1 = context;
        obj10 = obj5;
        Log.e("PowerKeeperPolicy", "getOomAdjByPid", ((Throwable) (obj1)));
        if(obj8 != null)
            try
            {
                ((BufferedReader) (obj8)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj1) { }
        if(obj5 != null)
            try
            {
                ((InputStreamReader) (obj5)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj1) { }
        obj9 = obj;
        if(context == null) goto _L2; else goto _L1
_L1:
        context.close();
        obj9 = obj;
          goto _L2
        context;
        obj9 = obj;
          goto _L2
        obj1;
        obj5 = obj10;
        context = context1;
_L4:
        if(obj9 != null)
            try
            {
                ((BufferedReader) (obj9)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj9) { }
        if(obj5 != null)
            try
            {
                ((InputStreamReader) (obj5)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj5) { }
        if(context != null)
            try
            {
                context.close();
            }
            // Misplaced declaration of an exception variable
            catch(Context context) { }
        throw obj1;
        obj1;
        obj9 = obj7;
        obj5 = obj4;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        obj9 = obj7;
        obj5 = obj1;
        obj1 = exception1;
        continue; /* Loop/switch isn't completed */
        exception1;
        obj9 = obj5;
        obj5 = obj1;
        obj1 = exception1;
        if(true) goto _L4; else goto _L3
_L3:
        obj1;
          goto _L5
        Exception exception;
        exception;
        obj5 = obj1;
        obj1 = exception;
          goto _L5
        exception;
        obj8 = obj5;
        obj5 = obj1;
        obj1 = exception;
          goto _L5
    }

    public String getPackageNameByPid(Context context, int i)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        Object obj10;
        Object obj11;
        Object obj12;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        obj5 = null;
        obj6 = null;
        obj7 = null;
        obj8 = null;
        obj9 = null;
        obj10 = obj7;
        obj11 = obj2;
        obj12 = obj4;
        Object obj13 = JVM INSTR new #669 <Class FileInputStream>;
        obj10 = obj7;
        obj11 = obj2;
        obj12 = obj4;
        File file = JVM INSTR new #671 <Class File>;
        obj10 = obj7;
        obj11 = obj2;
        obj12 = obj4;
        StringBuilder stringbuilder = JVM INSTR new #304 <Class StringBuilder>;
        obj10 = obj7;
        obj11 = obj2;
        obj12 = obj4;
        stringbuilder.StringBuilder();
        obj10 = obj7;
        obj11 = obj2;
        obj12 = obj4;
        file.File(stringbuilder.append("/proc/").append(i).append("/cmdline").toString());
        obj10 = obj7;
        obj11 = obj2;
        obj12 = obj4;
        ((FileInputStream) (obj13)).FileInputStream(file);
        obj2 = JVM INSTR new #681 <Class InputStreamReader>;
        ((InputStreamReader) (obj2)).InputStreamReader(((java.io.InputStream) (obj13)));
        obj10 = JVM INSTR new #686 <Class BufferedReader>;
        ((BufferedReader) (obj10)).BufferedReader(((java.io.Reader) (obj2)));
        obj1 = obj;
        obj11 = ((BufferedReader) (obj10)).readLine();
        obj6 = obj11;
        if(obj11 == null)
            break MISSING_BLOCK_LABEL_214;
        obj1 = obj11;
        obj6 = ((String) (obj11)).replace('\0', ' ').trim();
        if(obj10 != null)
            try
            {
                ((BufferedReader) (obj10)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj10) { }
        if(obj2 != null)
            try
            {
                ((InputStreamReader) (obj2)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj2) { }
        if(obj13 != null)
            try
            {
                ((FileInputStream) (obj13)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj13) { }
_L2:
label0:
        {
            obj13 = obj6;
            if(obj6 != null)
                break label0;
            context = ((ActivityManager)context.getSystemService("activity")).getRunningAppProcesses().iterator();
            do
            {
                obj13 = obj6;
                if(!context.hasNext())
                    break label0;
                obj13 = (android.app.ActivityManager.RunningAppProcessInfo)context.next();
            } while(((android.app.ActivityManager.RunningAppProcessInfo) (obj13)).pid != i);
            obj13 = ((android.app.ActivityManager.RunningAppProcessInfo) (obj13)).processName;
        }
        context = ((Context) (obj13));
        if(obj13 != null)
        {
            i = ((String) (obj13)).indexOf(":");
            context = ((Context) (obj13));
            if(i > 0)
                context = ((String) (obj13)).substring(0, i);
        }
        return context;
        obj2;
        obj13 = obj3;
_L5:
        obj10 = obj9;
        obj11 = obj13;
        obj12 = obj6;
        Log.e("PowerKeeperPolicy", "getPackageNameByPid", ((Throwable) (obj2)));
        if(obj9 != null)
            try
            {
                ((BufferedReader) (obj9)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj2) { }
        if(obj6 != null)
            try
            {
                ((InputStreamReader) (obj6)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj2) { }
        obj6 = obj1;
        if(obj13 == null) goto _L2; else goto _L1
_L1:
        ((FileInputStream) (obj13)).close();
        obj6 = obj1;
          goto _L2
        obj13;
        obj6 = obj1;
          goto _L2
        obj2;
        obj13 = obj12;
        context = ((Context) (obj11));
_L4:
        if(obj10 != null)
            try
            {
                ((BufferedReader) (obj10)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj6) { }
        if(obj13 != null)
            try
            {
                ((InputStreamReader) (obj13)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj13) { }
        if(context != null)
            try
            {
                context.close();
            }
            // Misplaced declaration of an exception variable
            catch(Context context) { }
        throw obj2;
        obj2;
        context = ((Context) (obj13));
        obj10 = obj8;
        obj13 = obj5;
        continue; /* Loop/switch isn't completed */
        obj6;
        context = ((Context) (obj13));
        obj10 = obj8;
        obj13 = obj2;
        obj2 = obj6;
        continue; /* Loop/switch isn't completed */
        obj6;
        context = ((Context) (obj13));
        obj13 = obj2;
        obj2 = obj6;
        if(true) goto _L4; else goto _L3
_L3:
        obj2;
          goto _L5
        obj10;
        obj6 = obj2;
        obj2 = obj10;
          goto _L5
        Exception exception;
        exception;
        obj6 = obj2;
        obj9 = obj10;
        obj2 = exception;
          goto _L5
    }

    public boolean isAlarmAllowedLocked(int i, int j, int k)
    {
        boolean flag = true;
        if(k < 0 || k > 3)
            return true;
        if(hasAlarmRestrict(-10, k) || hasAlarmRestrict(j, k))
        {
            flag = false;
            Log.d("PowerKeeperPolicy", (new StringBuilder()).append("isAlarmAllowedLocked, uid = ").append(j).append(",  type = ").append(k).append(", return :").append(false).toString());
        }
        if(!flag)
        {
            mAlarmDataTotal = mAlarmDataTotal + 1;
            i = mAlarmTypeData.get(k, 0);
            mAlarmTypeData.put(k, i + 1);
            i = mAlarmUidData.get(j, 0);
            mAlarmUidData.put(j, i + 1);
        }
        return flag;
    }

    public boolean isBroadcastAllowedLocked(int i, int j, String s)
    {
        boolean flag = true;
        if(s == null)
            return true;
        if(!getAppBroadcastControlStat(j))
            return true;
        if(hasBroadcastRestrict(j, s))
        {
            flag = false;
            Log.d("PowerKeeperPolicy", (new StringBuilder()).append("isBroadcastAllowedLocked, uid = ").append(j).append(", type = ").append(s).append(", return :").append(false).toString());
        }
        if(!flag)
        {
            mBroadcastDataTotal = mBroadcastDataTotal + 1;
            Object obj = (Integer)mBroadcastTypeData.get(s);
            Object obj1 = obj;
            if(obj == null)
                obj1 = Integer.valueOf(0);
            obj = mBroadcastTypeData;
            i = ((Integer) (obj1)).intValue() + 1;
            ((Map) (obj)).put(s, Integer.valueOf(i));
            i = mBroadcastUidData.get(j, 0);
            obj1 = mBroadcastUidData;
            i = Integer.valueOf(i).intValue() + 1;
            ((SparseIntArray) (obj1)).put(j, i);
            obj = (Map)mBrdcastUidTypeInfo.get(j);
            obj1 = obj;
            if(obj == null)
            {
                obj1 = new HashMap();
                mBrdcastUidTypeInfo.put(j, obj1);
            }
            Integer integer = (Integer)((Map) (obj1)).get(s);
            obj = integer;
            if(integer == null)
                obj = Integer.valueOf(0);
            i = ((Integer) (obj)).intValue() + 1;
            ((Map) (obj1)).put(s, Integer.valueOf(i));
        }
        return flag;
    }

    public boolean isLeScanAllowed(int i)
    {
        if(mContext == null)
            return true;
        Object obj = mBleLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        flag = LocationPolicyManager.isAllowedByLocationPolicy(mContext, i, 3);
        mUidAllow.put(i, flag);
_L1:
        boolean flag1 = mLeScanFeatureEnable;
        if(flag1)
            break MISSING_BLOCK_LABEL_75;
        obj;
        JVM INSTR monitorexit ;
        return true;
        Object obj1;
        obj1;
        flag = true;
        Log.e("PowerKeeperPolicy", "isLeScanAllowed", ((Throwable) (obj1)));
          goto _L1
        obj1;
        throw obj1;
        flag1 = flag;
        if(flag)
            break MISSING_BLOCK_LABEL_95;
        flag1 = flag;
        if(checkLeScanAllowedLocked())
            flag1 = true;
        flag = flag1;
        if(flag1)
            break MISSING_BLOCK_LABEL_115;
        flag = flag1;
        if(checkLeScanParoleLocked())
            flag = true;
        if(DEBUG)
        {
            StringBuilder stringbuilder = JVM INSTR new #304 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("PowerKeeperPolicy", stringbuilder.append("isLeScanAllowed: uid = ").append(i).append(", allow = ").append(flag).toString());
        }
        mUidScanning.put(i, flag);
        obj;
        JVM INSTR monitorexit ;
        return flag;
    }

    public boolean isWakelockDisabledByPolicy(String s, int i)
    {
        boolean flag = false;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mBlockedUidWakelocks.indexOfKey(i) < 0)
            break MISSING_BLOCK_LABEL_101;
        flag = true;
        Object obj1 = (ArrayList)mBlockedUidWakelocks.get(i);
        boolean flag1;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_101;
        flag1 = false;
        flag = flag1;
        if(s == null)
            break MISSING_BLOCK_LABEL_101;
        obj1 = ((Iterable) (obj1)).iterator();
_L2:
        flag = flag1;
        if(!((Iterator) (obj1)).hasNext())
            break MISSING_BLOCK_LABEL_101;
        flag = Pattern.compile((String)((Iterator) (obj1)).next()).matcher(s).find();
        if(!flag) goto _L2; else goto _L1
_L1:
        flag = true;
        return flag;
        s;
        throw s;
    }

    public void notifyFrozenAppWakeUpByHighPriorityAlarm(int i)
    {
        if(sPowerKeeperService == null)
            break MISSING_BLOCK_LABEL_17;
        sPowerKeeperService.notifyFrozenAppWakeUpByHighPriorityAlarm(i);
_L1:
        return;
        Exception exception;
        exception;
        Log.e("PowerKeeperPolicy", "notifyFrozenAppWakeUpByBroacastReceive failed", exception);
          goto _L1
    }

    public void offerPowerKeeperIBinder(IBinder ibinder)
    {
        sPowerKeeperService = IPowerKeeperClient.Stub.asInterface(ibinder);
        android.os.IBinder.DeathRecipient deathrecipient = JVM INSTR new #8   <Class PowerKeeperPolicy$2>;
        deathrecipient.this. _cls2();
        ibinder.linkToDeath(deathrecipient, 0);
_L1:
        return;
        ibinder;
        Log.e("PowerKeeperPolicy", "offerPowerKeeperIBinder", ibinder);
          goto _L1
    }

    public void setAlarmPolicy(AlarmPolicy aalarmpolicy[], boolean flag)
    {
        if(Binder.getCallingUid() != 1000)
            return;
        if(aalarmpolicy == null)
            return;
        if(flag)
            mAlarmRestricts.clear();
        int i = 0;
        while(i < aalarmpolicy.length) 
        {
            if(aalarmpolicy[i].mRestrictTypes == null || aalarmpolicy[i].mRestrictTypes.length == 0)
            {
                if(!flag)
                    mAlarmRestricts.delete(aalarmpolicy[i].mUid);
            } else
            {
                mAlarmRestricts.put(aalarmpolicy[i].mUid, aalarmpolicy[i].mRestrictTypes);
            }
            i++;
        }
    }

    public void setAppBGIdleFeatureEnable(boolean flag)
    {
        SparseIntArray sparseintarray = mAppBGIdleLevel;
        sparseintarray;
        JVM INSTR monitorenter ;
        mAppBGIdleFeatureStatus = flag;
        sparseintarray;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setAppBGIdleLevel(int i, int j)
    {
        while(!UserHandle.isApp(i) || j > 2 || j < 0) 
            return;
        SparseIntArray sparseintarray = mAppBGIdleLevel;
        sparseintarray;
        JVM INSTR monitorenter ;
        mAppBGIdleLevel.put(i, j);
        sparseintarray;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setAppBroadcastControlStat(int i, boolean flag)
    {
        SparseBooleanArray sparsebooleanarray = mUidBroadcastStat;
        sparsebooleanarray;
        JVM INSTR monitorenter ;
        boolean flag1 = UserHandle.isApp(i);
        if(flag1)
            break MISSING_BLOCK_LABEL_21;
        sparsebooleanarray;
        JVM INSTR monitorexit ;
        return;
        mUidBroadcastStat.put(i, flag);
        sparsebooleanarray;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setAppPushAlarmLeader(int i, Intent intent)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        pushAlarmLeaderUid = i;
        pushAlarmLeaderIntent = intent;
        mUidPushAlarmProperty.put(i, intent);
        mUidPushAlarmStat.put(i, true);
        obj;
        JVM INSTR monitorexit ;
        return;
        intent;
        throw intent;
    }

    public void setAppPushAlarmProperty(int i, Intent intent, boolean flag)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mUidPushAlarmProperty.put(i, intent);
        mUidPushAlarmStat.put(i, flag);
        obj;
        JVM INSTR monitorexit ;
        return;
        intent;
        throw intent;
    }

    public void setBroadcastPolicy(BroadcastPolicy abroadcastpolicy[], boolean flag)
    {
        if(Binder.getCallingUid() != 1000)
            return;
        if(abroadcastpolicy == null)
            return;
        if(flag)
            mBroadcastRestricts.clear();
        int i = 0;
        while(i < abroadcastpolicy.length) 
        {
            if(abroadcastpolicy[i] != null)
                if(abroadcastpolicy[i].mRestrictTypes == null || abroadcastpolicy[i].mRestrictTypes.length == 0)
                {
                    if(!flag)
                        mBroadcastRestricts.delete(abroadcastpolicy[i].mUid);
                } else
                {
                    mBroadcastRestricts.put(abroadcastpolicy[i].mUid, abroadcastpolicy[i].mRestrictTypes);
                }
            i++;
        }
    }

    public void setContext(Context context)
    {
        if(mContext == null)
        {
            mContext = context;
            bleScanInit();
        }
    }

    public void setLeScanFeature(boolean flag)
    {
        if(mContext == null)
            return;
        Object obj = mBleLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag1 = mLeScanFeatureEnable;
        if(flag1 != flag)
            break MISSING_BLOCK_LABEL_28;
        obj;
        JVM INSTR monitorexit ;
        return;
        if(DEBUG)
        {
            StringBuilder stringbuilder = JVM INSTR new #304 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("PowerKeeperPolicy", stringbuilder.append("setLeScanFeature: ").append(flag).toString());
        }
        mLeScanFeatureEnable = flag;
        if(!flag) goto _L2; else goto _L1
_L1:
        mParoleCheck.startParoleCheck();
_L3:
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        mParoleCheck.stopParoleCheck();
        mLeScanHandler.removeCallbacksAndMessages(null);
        Client client;
        for(Iterator iterator = mClientMap.entrySet().iterator(); iterator.hasNext(); client.clearResource())
        {
            client = (Client)((java.util.Map.Entry)iterator.next()).getValue();
            client.startLeScan();
            client.unlinkToDeath();
        }

        break MISSING_BLOCK_LABEL_170;
        Exception exception;
        exception;
        throw exception;
        mClientMap.clear();
        mUidScanning.clear();
          goto _L3
    }

    public void setLeScanParam(Bundle bundle)
    {
        if(mContext == null)
            return;
        Object obj = mBleLock;
        obj;
        JVM INSTR monitorenter ;
        if(bundle.containsKey("parolePeriodArray"))
        {
            bundle = bundle.getLongArray("parolePeriodArray");
            mParoleCheck.setParoleCheckParam(bundle);
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        bundle;
        throw bundle;
    }

    public void startLeScan(Bundle bundle)
    {
        BleScanWrapper blescanwrapper;
        Object obj;
        int i;
        if(mContext == null)
            return;
        mContext.enforceCallingPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        blescanwrapper = (BleScanWrapper)bundle.getParcelable("BleScanWrapper");
        obj = bundle.getBinder("IBinder");
        i = bundle.getInt("uid");
        if(DEBUG)
            Log.d("PowerKeeperPolicy", (new StringBuilder()).append("startLeScan: ").append(blescanwrapper).toString());
        bundle = ((Bundle) (mBleLock));
        bundle;
        JVM INSTR monitorenter ;
        boolean flag = mLeScanFeatureEnable;
        if(flag)
            break MISSING_BLOCK_LABEL_102;
        bundle;
        JVM INSTR monitorexit ;
        return;
        boolean flag1;
        boolean flag2;
        Client client;
        flag1 = mUidAllow.get(i, true);
        flag2 = mUidScanning.get(i, true);
        client = (Client)mClientMap.get(blescanwrapper);
        if(client == null)
            break MISSING_BLOCK_LABEL_167;
        client.unlinkToDeath();
        client.clearResource();
        mClientMap.remove(blescanwrapper);
        flag = checkLeScanAllowedLocked();
        Client client1 = JVM INSTR new #12  <Class PowerKeeperPolicy$Client>;
        client1.this. Client(blescanwrapper, ((IBinder) (obj)), i);
        client1.setAllowed(flag1);
        client1.setScanning(flag2);
        obj = JVM INSTR new #15  <Class PowerKeeperPolicy$ClientDeathRecipient>;
        ((ClientDeathRecipient) (obj)).this. ClientDeathRecipient(blescanwrapper);
        client1.linkToDeath(((android.os.IBinder.DeathRecipient) (obj)));
        mClientMap.put(blescanwrapper, client1);
        flag1 = checkLeScanAllowedLocked();
        if(flag || !flag1)
            break MISSING_BLOCK_LABEL_263;
        if(mParoleCheck.isInParoleState() ^ true)
            startLeScanAllLocked();
        if(!flag1)
            break MISSING_BLOCK_LABEL_285;
        if(mParoleCheck.isWorking())
            mParoleCheck.stopParoleCheck();
        bundle;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void stopLeScan(Bundle bundle)
    {
        BleScanWrapper blescanwrapper;
        if(mContext == null)
            return;
        mContext.enforceCallingPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        blescanwrapper = (BleScanWrapper)bundle.getParcelable("BleScanWrapper");
        bundle.getBinder("IBinder");
        if(DEBUG)
            Log.d("PowerKeeperPolicy", (new StringBuilder()).append("stopLeScan: ").append(blescanwrapper).toString());
        bundle = ((Bundle) (mBleLock));
        bundle;
        JVM INSTR monitorenter ;
        boolean flag = mLeScanFeatureEnable;
        if(flag)
            break MISSING_BLOCK_LABEL_91;
        bundle;
        JVM INSTR monitorexit ;
        return;
        boolean flag1;
        Client client;
        flag1 = checkLeScanAllowedLocked();
        client = (Client)mClientMap.get(blescanwrapper);
        if(client == null)
            break MISSING_BLOCK_LABEL_138;
        client.unlinkToDeath();
        client.clearResource();
        mClientMap.remove(blescanwrapper);
        flag = checkLeScanAllowedLocked();
        if(!flag1 || !(flag ^ true))
            break MISSING_BLOCK_LABEL_170;
        if(mParoleCheck.isInParoleState() ^ true)
            stopLeScanAllLocked();
        if(flag)
            break MISSING_BLOCK_LABEL_193;
        if(mParoleCheck.isWorking() ^ true)
            mParoleCheck.startParoleCheck();
        bundle;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void updateWakelockBlockedUid(int i, String s, boolean flag)
    {
        boolean flag2;
        int j = Binder.getCallingUid();
        Slog.d("PowerKeeperPolicy", (new StringBuilder()).append("Caller[").append(j).append("] updateWakelockBlockedUid:").append("uid=").append(i).append(", tag=").append(s).append(", block=").append(flag).toString());
        if(j != 1000)
            return;
        flag2 = false;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mBlockedUidWakelocks.indexOfKey(i) < 0) goto _L2; else goto _L1
_L1:
        Object obj1 = (ArrayList)mBlockedUidWakelocks.get(i);
        if(!flag) goto _L4; else goto _L3
_L3:
        if(s != null || obj1 == null) goto _L6; else goto _L5
_L5:
        mBlockedUidWakelocks.put(i, null);
        boolean flag1 = true;
_L8:
        obj;
        JVM INSTR monitorexit ;
        if(!flag1)
            break MISSING_BLOCK_LABEL_180;
        setUidWakeLockDisabledState.invoke(mPowerManagerServiceInjector, new Object[] {
            Integer.valueOf(i), s, Boolean.valueOf(flag)
        });
_L13:
        return;
_L6:
        flag1 = flag2;
        if(s == null) goto _L8; else goto _L7
_L7:
        flag1 = flag2;
        if(obj1 == null) goto _L8; else goto _L9
_L9:
        flag1 = flag2;
        if(((ArrayList) (obj1)).contains(s)) goto _L8; else goto _L10
_L10:
        ((ArrayList) (obj1)).add(s);
        flag1 = true;
          goto _L8
_L4:
        if(s != null)
            break MISSING_BLOCK_LABEL_242;
        mBlockedUidWakelocks.remove(i);
        flag1 = true;
          goto _L8
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_289;
        flag1 = flag2;
        if(!((ArrayList) (obj1)).contains(s)) goto _L8; else goto _L11
_L11:
        ((ArrayList) (obj1)).remove(s);
        if(((ArrayList) (obj1)).size() == 0)
            mBlockedUidWakelocks.remove(i);
        flag1 = true;
          goto _L8
        obj1 = JVM INSTR new #304 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Slog.w("PowerKeeperPolicy", ((StringBuilder) (obj1)).append("cannot unblock the wakelock[").append(s).append("] for uid ").append(i).append(", please unblock all the wakelock with a null tag").toString());
        flag1 = flag2;
          goto _L8
        s;
        throw s;
_L2:
        flag1 = flag2;
        if(!flag) goto _L8; else goto _L12
_L12:
        flag1 = true;
        obj1 = null;
        if(s == null)
            break MISSING_BLOCK_LABEL_384;
        obj1 = JVM INSTR new #600 <Class ArrayList>;
        ((ArrayList) (obj1)).ArrayList();
        ((ArrayList) (obj1)).add(s);
        mBlockedUidWakelocks.put(i, obj1);
          goto _L8
        s;
        Log.e("PowerKeeperPolicy", "updateWakelockBlockedUid", s);
          goto _L13
        s;
        Log.e("PowerKeeperPolicy", "updateWakelockBlockedUid", s);
          goto _L13
    }

    public static final int APP_BG_IDLE_LEVEL_DELAY = 1;
    public static final int APP_BG_IDLE_LEVEL_DISABLE = 2;
    public static final int APP_BG_IDLE_LEVEL_NORMAL = 0;
    private static final boolean DEBUG;
    public static final int DEFAULT_UID = -10;
    private static final int MSG_LE_SCAN_PAROLE_CHANGE = 1001;
    private static final int MSG_LE_SCAN_UID_RULE_CHANGE = 1000;
    private static final String TAG = "PowerKeeperPolicy";
    private static final boolean debug = false;
    private static PowerKeeperPolicy sInstance;
    private final int SYSTEM_PID_STAT_FORMAT[] = {
        288, 4128, 4128
    };
    private int mAlarmDataTotal;
    private SparseArray mAlarmRestricts;
    private SparseIntArray mAlarmTypeData;
    private SparseIntArray mAlarmUidData;
    private boolean mAppBGIdleFeatureStatus;
    private final SparseIntArray mAppBGIdleLevel = new SparseIntArray();
    private final Object mBleLock = new Object();
    private SparseArray mBlockedUidWakelocks;
    private final SparseArray mBrdcastUidTypeInfo = new SparseArray();
    private int mBroadcastDataTotal;
    private SparseArray mBroadcastRestricts;
    private Map mBroadcastTypeData;
    private SparseIntArray mBroadcastUidData;
    private Map mClientMap;
    private Context mContext;
    private Class mExtraActivityManagerService;
    private boolean mLeScanFeatureEnable;
    private Handler mLeScanHandler;
    private ILocationPolicyListener mLocationPolicyListener;
    private final Object mLock = new Object();
    private ParoleCheck mParoleCheck;
    private final Object mPolicyLock = new Object();
    private Class mPowerManagerServiceInjector;
    private ClassLoader mSystemServiceClassLoader;
    private final String mSytemPidStatData[] = new String[2];
    private SparseBooleanArray mUidAllow;
    private final SparseBooleanArray mUidBroadcastStat = new SparseBooleanArray();
    private final SparseArray mUidPushAlarmProperty = new SparseArray();
    private final SparseBooleanArray mUidPushAlarmStat = new SparseBooleanArray();
    private SparseBooleanArray mUidScanning;
    private Intent pushAlarmLeaderIntent;
    private int pushAlarmLeaderUid;
    private IPowerKeeperClient sPowerKeeperService;
    private Method setAppToIdle;
    private Method setUidWakeLockDisabledState;

    static 
    {
        DEBUG = Build.IS_DEBUGGABLE;
    }

    // Unreferenced inner class com/miui/whetstone/PowerKeeperPolicy$2

/* anonymous class */
    class _cls2
        implements android.os.IBinder.DeathRecipient
    {

        public void binderDied()
        {
            b.unlinkToDeath(this, 0);
            PowerKeeperPolicy._2D_set0(PowerKeeperPolicy.this, null);
            Slog.d("PowerKeeperPolicy", "powerkeeper died, reset handle to null");
            PowerKeeperPolicy._2D_wrap2(PowerKeeperPolicy.this);
        }

        final PowerKeeperPolicy this$0;
        final IBinder val$b;

            
            {
                this$0 = PowerKeeperPolicy.this;
                b = ibinder;
                super();
            }
    }


    // Unreferenced inner class com/miui/whetstone/PowerKeeperPolicy$ParoleCheck$1

/* anonymous class */
    class ParoleCheck._cls1
        implements android.os.Handler.Callback
    {

        public boolean handleMessage(Message message)
        {
            Object obj = ParoleCheck._2D_get0(ParoleCheck.this);
            obj;
            JVM INSTR monitorenter ;
            boolean flag = ParoleCheck._2D_wrap0(ParoleCheck.this, message);
            obj;
            JVM INSTR monitorexit ;
            return flag;
            message;
            throw message;
        }

        final ParoleCheck this$1;

            
            {
                this$1 = ParoleCheck.this;
                super();
            }
    }

}
