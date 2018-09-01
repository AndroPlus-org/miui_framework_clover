// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;
import java.io.FileDescriptor;

// Referenced classes of package android.location:
//            ILocationManager

public class MiuiLocationManagerProxy
{
    public static interface IInvokeMonitor
    {

        public abstract boolean onInvoke(IBinder ibinder, int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException;
    }

    private static class ProxyBinder
        implements IBinder
    {

        public void dump(FileDescriptor filedescriptor, String as[])
            throws RemoteException
        {
            mBinder.dump(filedescriptor, as);
        }

        public void dumpAsync(FileDescriptor filedescriptor, String as[])
            throws RemoteException
        {
            mBinder.dumpAsync(filedescriptor, as);
        }

        public String getInterfaceDescriptor()
            throws RemoteException
        {
            return mBinder.getInterfaceDescriptor();
        }

        public boolean isBinderAlive()
        {
            return mBinder.isBinderAlive();
        }

        public void linkToDeath(android.os.IBinder.DeathRecipient deathrecipient, int i)
            throws RemoteException
        {
            mBinder.linkToDeath(deathrecipient, i);
        }

        public boolean pingBinder()
        {
            return mBinder.pingBinder();
        }

        public IInterface queryLocalInterface(String s)
        {
            return mBinder.queryLocalInterface(s);
        }

        public void shellCommand(FileDescriptor filedescriptor, FileDescriptor filedescriptor1, FileDescriptor filedescriptor2, String as[], ShellCallback shellcallback, ResultReceiver resultreceiver)
            throws RemoteException
        {
            mBinder.shellCommand(filedescriptor, filedescriptor1, filedescriptor2, as, shellcallback, resultreceiver);
        }

        public boolean transact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            android/location/MiuiLocationManagerProxy;
            JVM INSTR monitorenter ;
            boolean flag;
            if(MiuiLocationManagerProxy._2D_get0() == null)
                break MISSING_BLOCK_LABEL_34;
            flag = MiuiLocationManagerProxy._2D_get0().onInvoke(mBinder, i, parcel, parcel1, j);
            android/location/MiuiLocationManagerProxy;
            JVM INSTR monitorexit ;
            return flag;
            android/location/MiuiLocationManagerProxy;
            JVM INSTR monitorexit ;
            return mBinder.transact(i, parcel, parcel1, j);
            parcel;
            throw parcel;
        }

        public boolean unlinkToDeath(android.os.IBinder.DeathRecipient deathrecipient, int i)
        {
            return mBinder.unlinkToDeath(deathrecipient, i);
        }

        private IBinder mBinder;

        public ProxyBinder(IBinder ibinder)
        {
            mBinder = ibinder;
        }
    }


    static IInvokeMonitor _2D_get0()
    {
        return sInvokeMonitor;
    }

    public MiuiLocationManagerProxy()
    {
    }

    public static ILocationManager getProxy(ILocationManager ilocationmanager)
    {
        return ILocationManager.Stub.asInterface(new ProxyBinder(ilocationmanager.asBinder()));
    }

    public static void setInvokeMonitor(IInvokeMonitor iinvokemonitor)
    {
        android/location/MiuiLocationManagerProxy;
        JVM INSTR monitorenter ;
        sInvokeMonitor = iinvokemonitor;
        android/location/MiuiLocationManagerProxy;
        JVM INSTR monitorexit ;
        return;
        iinvokemonitor;
        throw iinvokemonitor;
    }

    private static IInvokeMonitor sInvokeMonitor;
}
