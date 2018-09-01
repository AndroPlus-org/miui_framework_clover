// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.process;

import android.os.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package miui.process:
//            IProcessManager, IMiuiApplicationThread, ForegroundInfo, ProcessConfig, 
//            IActivityChangeListener, IForegroundInfoListener, ProcessCloudData

class ProcessManagerProxy
    implements IProcessManager
{

    public ProcessManagerProxy(IBinder ibinder)
    {
        mRemote = ibinder;
    }

    public void addMiuiApplicationThread(IMiuiApplicationThread imiuiapplicationthread, int i)
        throws RemoteException
    {
        IBinder ibinder = null;
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("miui.IProcessManager");
        if(imiuiapplicationthread != null)
            ibinder = imiuiapplicationthread.asBinder();
        parcel.writeStrongBinder(ibinder);
        parcel.writeInt(i);
        mRemote.transact(13, parcel, parcel1, 1);
        parcel1.readException();
        parcel.recycle();
        parcel1.recycle();
    }

    public IBinder asBinder()
    {
        return mRemote;
    }

    public IMiuiApplicationThread getForegroundApplicationThread()
        throws RemoteException
    {
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("miui.IProcessManager");
        mRemote.transact(14, parcel, parcel1, 0);
        parcel1.readException();
        Object obj = parcel1.readStrongBinder();
        if(obj != null)
            obj = IMiuiApplicationThread.Stub.asInterface(((IBinder) (obj)));
        else
            obj = null;
        parcel.recycle();
        parcel1.recycle();
        return ((IMiuiApplicationThread) (obj));
    }

    public ForegroundInfo getForegroundInfo()
        throws RemoteException
    {
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("miui.IProcessManager");
        mRemote.transact(12, parcel, parcel1, 0);
        parcel1.readException();
        ForegroundInfo foregroundinfo = (ForegroundInfo)ForegroundInfo.CREATOR.createFromParcel(parcel1);
        parcel.recycle();
        parcel1.recycle();
        return foregroundinfo;
    }

    public List getLockedApplication(int i)
        throws RemoteException
    {
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("miui.IProcessManager");
        parcel.writeInt(i);
        mRemote.transact(4, parcel, parcel1, 0);
        parcel1.readException();
        ArrayList arraylist = null;
        i = parcel1.readInt();
        if(i >= 0)
        {
            ArrayList arraylist1 = new ArrayList();
            do
            {
                arraylist = arraylist1;
                if(i <= 0)
                    break;
                arraylist1.add(parcel1.readString());
                i--;
            } while(true);
        }
        parcel.recycle();
        parcel1.recycle();
        return arraylist;
    }

    public boolean isLockedApplication(String s, int i)
        throws RemoteException
    {
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("miui.IProcessManager");
        parcel.writeString(s);
        parcel.writeInt(i);
        mRemote.transact(9, parcel, parcel1, 0);
        parcel1.readException();
        boolean flag;
        if(parcel1.readInt() != 0)
            flag = true;
        else
            flag = false;
        parcel.recycle();
        parcel1.recycle();
        return flag;
    }

    public boolean kill(ProcessConfig processconfig)
        throws RemoteException
    {
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("miui.IProcessManager");
        processconfig.writeToParcel(parcel, 0);
        mRemote.transact(2, parcel, parcel1, 0);
        parcel1.readException();
        boolean flag;
        if(parcel1.readInt() != 0)
            flag = true;
        else
            flag = false;
        parcel.recycle();
        parcel1.recycle();
        return flag;
    }

    public boolean protectCurrentProcess(boolean flag, int i)
        throws RemoteException
    {
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("miui.IProcessManager");
        int j;
        if(flag)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeInt(i);
        mRemote.transact(7, parcel, parcel1, 0);
        parcel1.readException();
        if(parcel1.readInt() != 0)
            flag = true;
        else
            flag = false;
        parcel.recycle();
        parcel1.recycle();
        return flag;
    }

    public void registerActivityChangeListener(List list, List list1, IActivityChangeListener iactivitychangelistener)
        throws RemoteException
    {
        Object obj = null;
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("miui.IProcessManager");
        parcel.writeStringList(list);
        parcel.writeStringList(list1);
        list = obj;
        if(iactivitychangelistener != null)
            list = iactivitychangelistener.asBinder();
        parcel.writeStrongBinder(list);
        mRemote.transact(15, parcel, parcel1, 0);
        parcel1.readException();
        parcel.recycle();
        parcel1.recycle();
    }

    public void registerForegroundInfoListener(IForegroundInfoListener iforegroundinfolistener)
        throws RemoteException
    {
        IBinder ibinder = null;
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("miui.IProcessManager");
        if(iforegroundinfolistener != null)
            ibinder = iforegroundinfolistener.asBinder();
        parcel.writeStrongBinder(ibinder);
        mRemote.transact(10, parcel, parcel1, 0);
        parcel1.readException();
        parcel.recycle();
        parcel1.recycle();
    }

    public int startProcesses(List list, int i, boolean flag, int j, int k)
        throws RemoteException
    {
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("miui.IProcessManager");
        parcel.writeList(list);
        parcel.writeInt(i);
        if(flag)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(j);
        parcel.writeInt(k);
        mRemote.transact(6, parcel, parcel1, 0);
        parcel1.readException();
        i = parcel1.readInt();
        parcel.recycle();
        parcel1.recycle();
        return i;
    }

    public void unregisterActivityChangeListener(IActivityChangeListener iactivitychangelistener)
        throws RemoteException
    {
        IBinder ibinder = null;
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("miui.IProcessManager");
        if(iactivitychangelistener != null)
            ibinder = iactivitychangelistener.asBinder();
        parcel.writeStrongBinder(ibinder);
        mRemote.transact(16, parcel, parcel1, 0);
        parcel1.readException();
        parcel.recycle();
        parcel1.recycle();
    }

    public void unregisterForegroundInfoListener(IForegroundInfoListener iforegroundinfolistener)
        throws RemoteException
    {
        IBinder ibinder = null;
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("miui.IProcessManager");
        if(iforegroundinfolistener != null)
            ibinder = iforegroundinfolistener.asBinder();
        parcel.writeStrongBinder(ibinder);
        mRemote.transact(11, parcel, parcel1, 0);
        parcel1.readException();
        parcel.recycle();
        parcel1.recycle();
    }

    public void updateApplicationLockedState(String s, int i, boolean flag)
        throws RemoteException
    {
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("miui.IProcessManager");
        parcel.writeString(s);
        parcel.writeInt(i);
        if(flag)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        mRemote.transact(3, parcel, parcel1, 0);
        parcel1.readException();
        parcel.recycle();
        parcel1.recycle();
    }

    public void updateCloudData(ProcessCloudData processclouddata)
        throws RemoteException
    {
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("miui.IProcessManager");
        processclouddata.writeToParcel(parcel, 0);
        mRemote.transact(8, parcel, parcel1, 1);
        parcel1.readException();
        parcel.recycle();
        parcel1.recycle();
    }

    public void updateConfig(ProcessConfig processconfig)
        throws RemoteException
    {
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("miui.IProcessManager");
        processconfig.writeToParcel(parcel, 0);
        mRemote.transact(5, parcel, parcel1, 0);
        parcel1.readException();
        parcel.recycle();
        parcel1.recycle();
    }

    private IBinder mRemote;
}
