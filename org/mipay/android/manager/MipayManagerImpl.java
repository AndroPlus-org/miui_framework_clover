// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.mipay.android.manager;

import android.content.Context;
import android.os.*;
import android.util.Slog;
import java.util.ArrayList;

// Referenced classes of package org.mipay.android.manager:
//            IMipayManager

public class MipayManagerImpl
    implements IMipayManager
{

    public MipayManagerImpl()
    {
    }

    public static IMipayManager getInstance()
    {
        if(INSTANCE != null) goto _L2; else goto _L1
_L1:
        org/mipay/android/manager/MipayManagerImpl;
        JVM INSTR monitorenter ;
        if(INSTANCE == null)
        {
            MipayManagerImpl mipaymanagerimpl = JVM INSTR new #2   <Class MipayManagerImpl>;
            mipaymanagerimpl.MipayManagerImpl();
            INSTANCE = mipaymanagerimpl;
        }
        org/mipay/android/manager/MipayManagerImpl;
        JVM INSTR monitorexit ;
_L2:
        return INSTANCE;
        Exception exception;
        exception;
        throw exception;
    }

    private void initService()
        throws RemoteException
    {
        if(mService == null)
            mService = HwBinder.getService(SERVICE_NAME, "default");
    }

    private int signUpdate(String s)
    {
        byte byte0;
        HwParcel hwparcel;
        byte0 = -1;
        hwparcel = new HwParcel();
        initService();
        int i = byte0;
        if(mService != null)
        {
            HwParcel hwparcel1 = JVM INSTR new #88  <Class HwParcel>;
            hwparcel1.HwParcel();
            hwparcel1.writeInterfaceToken(INTERFACE_DESCRIPTOR);
            hwparcel1.writeString(s);
            mService.transact(CODE_SIGN_UPDATE, hwparcel1, hwparcel, 0);
            hwparcel.verifySuccess();
            hwparcel1.releaseTemporaryStorage();
            i = hwparcel.readInt32();
        }
        hwparcel.release();
_L2:
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("signUpdate, res:").append(i).toString());
        return i;
        s;
        String s1 = TAG;
        StringBuilder stringbuilder = JVM INSTR new #119 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.e(s1, stringbuilder.append("transact fail. ").append(s).toString());
        hwparcel.release();
        i = byte0;
        if(true) goto _L2; else goto _L1
_L1:
        s;
        hwparcel.release();
        throw s;
    }

    public boolean contains(String s)
    {
        boolean flag;
        boolean flag1;
        HwParcel hwparcel;
        flag = false;
        flag1 = false;
        hwparcel = new HwParcel();
        initService();
        if(mService != null)
        {
            HwParcel hwparcel1 = JVM INSTR new #88  <Class HwParcel>;
            hwparcel1.HwParcel();
            hwparcel1.writeInterfaceToken(INTERFACE_DESCRIPTOR);
            hwparcel1.writeString(s);
            mService.transact(CODE_CONTAINS, hwparcel1, hwparcel, 0);
            hwparcel.verifySuccess();
            hwparcel1.releaseTemporaryStorage();
            flag1 = hwparcel.readBool();
        }
        hwparcel.release();
_L2:
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("contains, ").append(s).append(" res:").append(flag1).toString());
        return flag1;
        RemoteException remoteexception;
        remoteexception;
        String s1 = TAG;
        StringBuilder stringbuilder = JVM INSTR new #119 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.e(s1, stringbuilder.append("transact fail. ").append(remoteexception).toString());
        hwparcel.release();
        flag1 = flag;
        if(true) goto _L2; else goto _L1
_L1:
        s;
        hwparcel.release();
        throw s;
    }

    public int generateKeyPair(String s, String s1)
    {
        byte byte0;
        HwParcel hwparcel;
        byte0 = -1;
        hwparcel = new HwParcel();
        initService();
        int i = byte0;
        if(mService != null)
        {
            HwParcel hwparcel1 = JVM INSTR new #88  <Class HwParcel>;
            hwparcel1.HwParcel();
            hwparcel1.writeInterfaceToken(INTERFACE_DESCRIPTOR);
            hwparcel1.writeString(s);
            hwparcel1.writeString(s1);
            mService.transact(CODE_GEN_KEY_PAIR, hwparcel1, hwparcel, 0);
            hwparcel.verifySuccess();
            hwparcel1.releaseTemporaryStorage();
            i = hwparcel.readInt32();
        }
        hwparcel.release();
_L2:
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("generateKeyPair, ").append(s).append(" ").append(s1).append(" res:").append(i).toString());
        return i;
        RemoteException remoteexception;
        remoteexception;
        String s2 = TAG;
        StringBuilder stringbuilder = JVM INSTR new #119 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.e(s2, stringbuilder.append("transact fail. ").append(remoteexception).toString());
        hwparcel.release();
        i = byte0;
        if(true) goto _L2; else goto _L1
_L1:
        s;
        hwparcel.release();
        throw s;
    }

    public String getFpIds()
    {
        String s;
        HwParcel hwparcel;
        s = "";
        hwparcel = new HwParcel();
        initService();
        Object obj = s;
        if(mService != null)
        {
            obj = JVM INSTR new #88  <Class HwParcel>;
            ((HwParcel) (obj)).HwParcel();
            ((HwParcel) (obj)).writeInterfaceToken(INTERFACE_DESCRIPTOR);
            mService.transact(CODE_GET_FP_IDS, ((HwParcel) (obj)), hwparcel, 0);
            hwparcel.verifySuccess();
            ((HwParcel) (obj)).releaseTemporaryStorage();
            obj = hwparcel.readString();
        }
        hwparcel.release();
_L2:
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("getFpIds, fpIds:").append(((String) (obj))).toString());
        return ((String) (obj));
        RemoteException remoteexception;
        remoteexception;
        String s1 = TAG;
        obj = JVM INSTR new #119 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        Slog.e(s1, ((StringBuilder) (obj)).append("transact fail. ").append(remoteexception).toString());
        hwparcel.release();
        obj = s;
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        hwparcel.release();
        throw exception;
    }

    public int getSupportBIOTypes(Context context)
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("getSupportBIOTypes :").append(MIPAY_TYPE_FINGER).toString());
        return MIPAY_TYPE_FINGER;
    }

    public int getVersion()
    {
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("getVersion :").append(MIPAY_VERISON_1).toString());
        return MIPAY_VERISON_1;
    }

    public int removeAllKey()
    {
        byte byte0;
        HwParcel hwparcel;
        byte0 = -1;
        hwparcel = new HwParcel();
        initService();
        int i = byte0;
        if(mService != null)
        {
            HwParcel hwparcel1 = JVM INSTR new #88  <Class HwParcel>;
            hwparcel1.HwParcel();
            hwparcel1.writeInterfaceToken(INTERFACE_DESCRIPTOR);
            mService.transact(CODE_RM_ALL_KEY, hwparcel1, hwparcel, 0);
            hwparcel.verifySuccess();
            hwparcel1.releaseTemporaryStorage();
            i = hwparcel.readInt32();
        }
        hwparcel.release();
_L2:
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("removeAllKey, res:").append(i).toString());
        return i;
        Object obj;
        obj;
        String s = TAG;
        StringBuilder stringbuilder = JVM INSTR new #119 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.e(s, stringbuilder.append("transact fail. ").append(obj).toString());
        hwparcel.release();
        i = byte0;
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        hwparcel.release();
        throw obj;
    }

    public byte[] sign()
    {
        HwParcel hwparcel = new HwParcel();
        Object obj;
        int i;
        byte abyte0[];
        initService();
        if(mService == null)
            break MISSING_BLOCK_LABEL_111;
        obj = JVM INSTR new #88  <Class HwParcel>;
        ((HwParcel) (obj)).HwParcel();
        ((HwParcel) (obj)).writeInterfaceToken(INTERFACE_DESCRIPTOR);
        mService.transact(CODE_SIGN, ((HwParcel) (obj)), hwparcel, 0);
        hwparcel.verifySuccess();
        ((HwParcel) (obj)).releaseTemporaryStorage();
        obj = hwparcel.readInt8Vector();
        i = ((ArrayList) (obj)).size();
        abyte0 = new byte[i];
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        abyte0[j] = ((Byte)((ArrayList) (obj)).get(j)).byteValue();
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        hwparcel.release();
        return abyte0;
        hwparcel.release();
_L4:
        Slog.e(TAG, "sign fail, return null");
        return null;
        Object obj1;
        obj1;
        String s = TAG;
        StringBuilder stringbuilder = JVM INSTR new #119 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.e(s, stringbuilder.append("transact fail. ").append(obj1).toString());
        hwparcel.release();
        if(true) goto _L4; else goto _L3
_L3:
        obj1;
        hwparcel.release();
        throw obj1;
    }

    public int signInit(String s, String s1)
    {
        byte byte0;
        HwParcel hwparcel;
        byte0 = -1;
        hwparcel = new HwParcel();
        initService();
        int i = byte0;
        if(mService != null)
        {
            HwParcel hwparcel1 = JVM INSTR new #88  <Class HwParcel>;
            hwparcel1.HwParcel();
            hwparcel1.writeInterfaceToken(INTERFACE_DESCRIPTOR);
            hwparcel1.writeString(s);
            hwparcel1.writeString(s1);
            mService.transact(CODE_SIGN_INIT, hwparcel1, hwparcel, 0);
            hwparcel.verifySuccess();
            hwparcel1.releaseTemporaryStorage();
            i = hwparcel.readInt32();
        }
        hwparcel.release();
_L2:
        if(DEBUG)
            Slog.i(TAG, (new StringBuilder()).append("signInit, ").append(s).append(" ").append(s1).append(" res:").append(i).toString());
        return i;
        RemoteException remoteexception;
        remoteexception;
        String s2 = TAG;
        StringBuilder stringbuilder = JVM INSTR new #119 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.e(s2, stringbuilder.append("transact fail. ").append(remoteexception).toString());
        hwparcel.release();
        i = byte0;
        if(true) goto _L2; else goto _L1
_L1:
        s;
        hwparcel.release();
        throw s;
    }

    public int signUpdate(byte abyte0[], int i, int j)
    {
        return signUpdate(new String(abyte0, i, j));
    }

    private static int CODE_CONTAINS = 1;
    private static int CODE_GEN_KEY_PAIR = 2;
    private static int CODE_GET_FP_IDS = 7;
    private static int CODE_RM_ALL_KEY = 6;
    private static int CODE_SIGN = 5;
    private static int CODE_SIGN_INIT = 3;
    private static int CODE_SIGN_UPDATE = 4;
    private static boolean DEBUG = true;
    private static volatile MipayManagerImpl INSTANCE = null;
    private static String INTERFACE_DESCRIPTOR = "vendor.xiaomi.hardware.tidaservice@1.0::ITidaService";
    private static int MIPAY_TYPE_FINGER = 1;
    private static int MIPAY_TYPE_IRIS = 2;
    private static int MIPAY_VERISON_1 = 1;
    private static String SERVICE_NAME = "vendor.xiaomi.hardware.tidaservice@1.0::ITidaService";
    private static String TAG = "MipayManagerImpl";
    private IHwBinder mService;

}
