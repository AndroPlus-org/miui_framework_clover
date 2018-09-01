// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.hardware.display;

import android.os.*;
import android.text.TextUtils;
import android.util.Slog;
import miui.os.DeviceFeature;

class DisplayFeatureServiceProxy
{

    DisplayFeatureServiceProxy(Object obj)
    {
        if(!(obj instanceof IBinder)) goto _L2; else goto _L1
_L1:
        mService = (IBinder)obj;
        mDescriptor = mService.getInterfaceDescriptor();
        if(TextUtils.isEmpty(mDescriptor))
            mDescriptor = "miui.hardware.display.IDisplayFeatureService";
_L4:
        return;
_L2:
        try
        {
            if(obj instanceof IHwBinder)
            {
                mHwService = (IHwBinder)obj;
                mDescriptor = interfaceDescriptor();
                if(TextUtils.isEmpty(mDescriptor))
                    mDescriptor = "vendor.xiaomi.hardware.displayfeature@1.0::IDisplayFeature";
            }
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private transient int callBinderTransact(int i, int ai[])
    {
        int j;
        Parcel parcel;
        Parcel parcel1;
        byte byte0;
        j = 0;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        byte0 = -1;
        int k;
        parcel.writeInterfaceToken(mDescriptor);
        k = ai.length;
_L2:
        if(j >= k)
            break; /* Loop/switch isn't completed */
        parcel.writeInt(ai[j]);
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        j = byte0;
        if(mService.transact(i, parcel, parcel1, 0))
        {
            parcel1.readException();
            j = parcel1.readInt();
        }
        parcel1.recycle();
        parcel.recycle();
_L4:
        return j;
        RemoteException remoteexception;
        remoteexception;
        String s = TAG;
        ai = JVM INSTR new #95  <Class StringBuilder>;
        ai.StringBuilder();
        Slog.e(s, ai.append("callBinderTransact transact fail. ").append(remoteexception).toString());
        parcel1.recycle();
        parcel.recycle();
        j = byte0;
        if(true) goto _L4; else goto _L3
_L3:
        ai;
        parcel1.recycle();
        parcel.recycle();
        throw ai;
    }

    private transient void callHwBinderTransact(int i, int ai[])
    {
        int j;
        HwParcel hwparcel;
        j = 0;
        hwparcel = new HwParcel();
        HwParcel hwparcel1;
        int k;
        hwparcel1 = JVM INSTR new #118 <Class HwParcel>;
        hwparcel1.HwParcel();
        hwparcel1.writeInterfaceToken(mDescriptor);
        k = ai.length;
_L2:
        if(j >= k)
            break; /* Loop/switch isn't completed */
        hwparcel1.writeInt32(ai[j]);
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        mHwService.transact(i, hwparcel1, hwparcel, 0);
        hwparcel.verifySuccess();
        hwparcel1.releaseTemporaryStorage();
        hwparcel.release();
_L4:
        return;
        RemoteException remoteexception;
        remoteexception;
        ai = TAG;
        StringBuilder stringbuilder = JVM INSTR new #95  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.e(ai, stringbuilder.append("callHwBinderTransact transact fail. ").append(remoteexception).toString());
        hwparcel.release();
        if(true) goto _L4; else goto _L3
_L3:
        ai;
        hwparcel.release();
        throw ai;
    }

    public String interfaceDescriptor()
        throws RemoteException
    {
        Object obj;
        HwParcel hwparcel;
        obj = new HwParcel();
        ((HwParcel) (obj)).writeInterfaceToken("android.hidl.base@1.0::IBase");
        hwparcel = new HwParcel();
        mHwService.transact(0xf445343, ((HwParcel) (obj)), hwparcel, 0);
        hwparcel.verifySuccess();
        ((HwParcel) (obj)).releaseTemporaryStorage();
        obj = hwparcel.readString();
        hwparcel.release();
        return ((String) (obj));
        Exception exception;
        exception;
        hwparcel.release();
        throw exception;
    }

    void setFeature(int i, int j, int k, int l)
    {
        if(DeviceFeature.SUPPORT_DISPLAYFEATURE_HIDL)
            callHwBinderTransact(1, new int[] {
                i, j, k, l
            });
        else
            callBinderTransact(100, new int[] {
                i, j, k, l
            });
    }

    private static final int HIDL_TRANSACTION_interfaceDescriptor = 0xf445343;
    private static final int HIDL_TRANSACTION_setFeature = 1;
    private static final String HWBINDER_BASE_INTERFACE_DESCRIPTOR = "android.hidl.base@1.0::IBase";
    private static final String HWBINDER_INTERFACE_DESCRIPTOR = "vendor.xiaomi.hardware.displayfeature@1.0::IDisplayFeature";
    private static final String INTERFACE_DESCRIPTOR = "miui.hardware.display.IDisplayFeatureService";
    private static String TAG = "DisplayFeatureServiceProxy";
    private static final int TRANSACTION_setFeature = 100;
    private String mDescriptor;
    private IHwBinder mHwService;
    private IBinder mService;

}
