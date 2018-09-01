// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.ifaa.android.manager;

import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.util.Slog;
import java.util.ArrayList;
import java.util.Arrays;
import miui.util.FeatureParser;

// Referenced classes of package org.ifaa.android.manager:
//            IFAAManagerV2

public class IFAAManagerImpl extends IFAAManagerV2
{

    public IFAAManagerImpl()
    {
        mDevModel = null;
    }

    public static IFAAManagerV2 getInstance()
    {
        if(INSTANCE != null) goto _L2; else goto _L1
_L1:
        org/ifaa/android/manager/IFAAManagerImpl;
        JVM INSTR monitorenter ;
        if(INSTANCE == null)
        {
            IFAAManagerImpl ifaamanagerimpl = JVM INSTR new #2   <Class IFAAManagerImpl>;
            ifaamanagerimpl.IFAAManagerImpl();
            INSTANCE = ifaamanagerimpl;
        }
        org/ifaa/android/manager/IFAAManagerImpl;
        JVM INSTR monitorexit ;
_L2:
        return INSTANCE;
        Exception exception;
        exception;
        throw exception;
    }

    public String getDeviceModel()
    {
        if(mDevModel == null)
        {
            String s = FeatureParser.getString("finger_alipay_ifaa_model");
            if(s == null || "".equalsIgnoreCase(s))
                mDevModel = (new StringBuilder()).append(Build.MANUFACTURER).append("-").append(Build.DEVICE).toString();
            else
                mDevModel = s;
        }
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("getDeviceModel devcieModel:").append(mDevModel).toString());
        return mDevModel;
    }

    public int getSupportBIOTypes(Context context)
    {
        int i = SystemProperties.getInt("persist.sys.ifaa", 0);
        int j = i & (IFAA_TYPE_FINGER | IFAA_TYPE_IRIS);
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("getSupportBIOTypes return:").append(i).append(" res:").append(j).toString());
        return j;
    }

    public int getVersion()
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("getVersion sdk:").append(android.os.Build.VERSION.SDK_INT).append(" IfaaVer:").append(mIfaaVer).toString());
        return mIfaaVer;
    }

    public byte[] processCmdV2(Context context, byte abyte0[])
    {
        context = new HwParcel();
        byte abyte1[];
        int i;
        if(mService == null)
            mService = HwBinder.getService(SERVICE_NAME, "default");
        if(mService == null)
            break MISSING_BLOCK_LABEL_158;
        HwParcel hwparcel = JVM INSTR new #143 <Class HwParcel>;
        hwparcel.HwParcel();
        hwparcel.writeInterfaceToken(INTERFACE_DESCRIPTOR);
        ArrayList arraylist = JVM INSTR new #160 <Class ArrayList>;
        arraylist.ArrayList(Arrays.asList(HwBlob.wrapArray(abyte0)));
        hwparcel.writeInt8Vector(arraylist);
        hwparcel.writeInt32(arraylist.size());
        mService.transact(CODE_PROCESS_CMD, hwparcel, context, 0);
        context.verifySuccess();
        hwparcel.releaseTemporaryStorage();
        abyte0 = context.readInt8Vector();
        i = abyte0.size();
        abyte1 = new byte[i];
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        abyte1[j] = ((Byte)abyte0.get(j)).byteValue();
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        context.release();
        return abyte1;
        context.release();
_L4:
        Slog.e(TAG, "processCmdV2, return null");
        return null;
        RemoteException remoteexception;
        remoteexception;
        String s = TAG;
        abyte0 = JVM INSTR new #80  <Class StringBuilder>;
        abyte0.StringBuilder();
        Slog.e(s, abyte0.append("transact failed. ").append(remoteexception).toString());
        context.release();
        if(true) goto _L4; else goto _L3
_L3:
        abyte0;
        context.release();
        throw abyte0;
    }

    public int startBIOManager(Context context, int i)
    {
        byte byte0 = -1;
        if(IFAA_TYPE_FINGER == i)
        {
            Intent intent = new Intent();
            intent.setClassName(mFingerPackName, mFingerActName);
            intent.setFlags(0x10000000);
            context.startActivity(intent);
            byte0 = 0;
        }
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("startBIOManager authType:").append(i).append(" res:").append(byte0).toString());
        return byte0;
    }

    private static int CODE_PROCESS_CMD = 1;
    private static boolean DEBUG = true;
    private static int IFAA_TYPE_FINGER = 1;
    private static int IFAA_TYPE_IRIS = 2;
    private static volatile IFAAManagerImpl INSTANCE = null;
    private static String INTERFACE_DESCRIPTOR = "vendor.xiaomi.hardware.mlipay@1.0::IMlipayService";
    private static String SERVICE_NAME = "vendor.xiaomi.hardware.mlipay@1.0::IMlipayService";
    private static String TAG = "IfaaManagerImpl";
    private static String mFingerActName = "com.android.settings.NewFingerprintActivity";
    private static String mFingerPackName = "com.android.settings";
    private String mDevModel;
    private IHwBinder mService;

}
