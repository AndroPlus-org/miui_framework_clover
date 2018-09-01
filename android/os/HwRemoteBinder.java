// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import libcore.util.NativeAllocationRegistry;

// Referenced classes of package android.os:
//            IHwBinder, RemoteException, IHwInterface, HwParcel

public class HwRemoteBinder
    implements IHwBinder
{

    public HwRemoteBinder()
    {
        native_setup_empty();
        sNativeRegistry.registerNativeAllocation(this, mNativeContext);
    }

    private static final native long native_init();

    private final native void native_setup_empty();

    private static final void sendDeathNotice(IHwBinder.DeathRecipient deathrecipient, long l)
    {
        deathrecipient.serviceDied(l);
    }

    public native boolean linkToDeath(IHwBinder.DeathRecipient deathrecipient, long l);

    public IHwInterface queryLocalInterface(String s)
    {
        return null;
    }

    public final native void transact(int i, HwParcel hwparcel, HwParcel hwparcel1, int j)
        throws RemoteException;

    public native boolean unlinkToDeath(IHwBinder.DeathRecipient deathrecipient);

    private static final String TAG = "HwRemoteBinder";
    private static final NativeAllocationRegistry sNativeRegistry;
    private long mNativeContext;

    static 
    {
        long l = native_init();
        sNativeRegistry = new NativeAllocationRegistry(android/os/HwRemoteBinder.getClassLoader(), l, 128L);
    }
}
