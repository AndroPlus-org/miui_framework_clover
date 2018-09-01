// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.util.NoSuchElementException;
import libcore.util.NativeAllocationRegistry;

// Referenced classes of package android.os:
//            IHwBinder, RemoteException, HwParcel

public abstract class HwBinder
    implements IHwBinder
{

    public HwBinder()
    {
        native_setup();
        sNativeRegistry.registerNativeAllocation(this, mNativeContext);
    }

    public static final native void configureRpcThreadpool(long l, boolean flag);

    public static final native IHwBinder getService(String s, String s1)
        throws RemoteException, NoSuchElementException;

    public static final native void joinRpcThreadpool();

    private static final native long native_init();

    private final native void native_setup();

    public abstract void onTransact(int i, HwParcel hwparcel, HwParcel hwparcel1, int j)
        throws RemoteException;

    public final native void registerService(String s)
        throws RemoteException;

    public final native void transact(int i, HwParcel hwparcel, HwParcel hwparcel1, int j)
        throws RemoteException;

    private static final String TAG = "HwBinder";
    private static final NativeAllocationRegistry sNativeRegistry;
    private long mNativeContext;

    static 
    {
        long l = native_init();
        sNativeRegistry = new NativeAllocationRegistry(android/os/HwBinder.getClassLoader(), l, 128L);
    }
}
