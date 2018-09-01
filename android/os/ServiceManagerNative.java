// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            Binder, IServiceManager, IBinder, ServiceManagerProxy, 
//            RemoteException, Parcel

public abstract class ServiceManagerNative extends Binder
    implements IServiceManager
{

    public ServiceManagerNative()
    {
        attachInterface(this, "android.os.IServiceManager");
    }

    public static IServiceManager asInterface(IBinder ibinder)
    {
        if(ibinder == null)
            return null;
        IServiceManager iservicemanager = (IServiceManager)ibinder.queryLocalInterface("android.os.IServiceManager");
        if(iservicemanager != null)
            return iservicemanager;
        else
            return new ServiceManagerProxy(ibinder);
    }

    public IBinder asBinder()
    {
        return this;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
    {
        i;
        JVM INSTR tableswitch 1 6: default 40
    //                   1 42
    //                   2 62
    //                   3 82
    //                   4 126
    //                   5 40
    //                   6 142;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6
_L1:
        return false;
_L2:
        parcel.enforceInterface("android.os.IServiceManager");
        parcel1.writeStrongBinder(getService(parcel.readString()));
        return true;
_L3:
        parcel.enforceInterface("android.os.IServiceManager");
        parcel1.writeStrongBinder(checkService(parcel.readString()));
        return true;
_L4:
        String s;
        parcel.enforceInterface("android.os.IServiceManager");
        s = parcel.readString();
        parcel1 = parcel.readStrongBinder();
        boolean flag;
        if(parcel.readInt() != 0)
            flag = true;
        else
            flag = false;
        addService(s, parcel1, flag);
        return true;
_L5:
        parcel.enforceInterface("android.os.IServiceManager");
        parcel1.writeStringArray(listServices());
        return true;
_L6:
        parcel.enforceInterface("android.os.IServiceManager");
        setPermissionController(IPermissionController.Stub.asInterface(parcel.readStrongBinder()));
        return true;
        parcel;
        if(true) goto _L1; else goto _L7
_L7:
    }
}
