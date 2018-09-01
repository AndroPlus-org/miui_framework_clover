// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.process;

import android.os.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package miui.process:
//            IProcessManager, ProcessManagerProxy, ProcessConfig, ProcessCloudData, 
//            ForegroundInfo, IMiuiApplicationThread

public abstract class ProcessManagerNative extends Binder
    implements IProcessManager
{

    public ProcessManagerNative()
    {
        attachInterface(this, "miui.IProcessManager");
    }

    public static IProcessManager asInterface(IBinder ibinder)
    {
        if(ibinder == null)
            return null;
        IProcessManager iprocessmanager = (IProcessManager)ibinder.queryLocalInterface("miui.IProcessManager");
        if(iprocessmanager != null)
            return iprocessmanager;
        else
            return new ProcessManagerProxy(ibinder);
    }

    public static IProcessManager getDefault()
    {
        if(pm != null) goto _L2; else goto _L1
_L1:
        miui/process/ProcessManagerNative;
        JVM INSTR monitorenter ;
        if(pm == null)
            pm = asInterface(ServiceManager.getService("ProcessManager"));
        miui/process/ProcessManagerNative;
        JVM INSTR monitorexit ;
_L2:
        return pm;
        Exception exception;
        exception;
        throw exception;
    }

    public IBinder asBinder()
    {
        return this;
    }

    protected boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException
    {
        switch(i)
        {
        default:
            return super.onTransact(i, parcel, parcel1, j);

        case 2: // '\002'
            parcel.enforceInterface("miui.IProcessManager");
            boolean flag = kill((ProcessConfig)ProcessConfig.CREATOR.createFromParcel(parcel));
            parcel1.writeNoException();
            if(flag)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;

        case 3: // '\003'
            parcel.enforceInterface("miui.IProcessManager");
            String s = parcel.readString();
            i = parcel.readInt();
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            updateApplicationLockedState(s, i, flag1);
            parcel1.writeNoException();
            return true;

        case 4: // '\004'
            parcel.enforceInterface("miui.IProcessManager");
            parcel = getLockedApplication(parcel.readInt());
            parcel1.writeNoException();
            if(parcel != null)
                i = parcel.size();
            else
                i = -1;
            parcel1.writeInt(i);
            for(j = 0; j < i; j++)
                parcel1.writeString((String)parcel.get(j));

            return true;

        case 5: // '\005'
            parcel.enforceInterface("miui.IProcessManager");
            updateConfig((ProcessConfig)ProcessConfig.CREATOR.createFromParcel(parcel));
            parcel1.writeNoException();
            return true;

        case 6: // '\006'
            parcel.enforceInterface("miui.IProcessManager");
            ArrayList arraylist = parcel.readArrayList(java/util/List.getClassLoader());
            i = parcel.readInt();
            boolean flag2;
            if(parcel.readInt() == 1)
                flag2 = true;
            else
                flag2 = false;
            i = startProcesses(arraylist, i, flag2, parcel.readInt(), parcel.readInt());
            parcel1.writeNoException();
            parcel1.writeInt(i);
            return true;

        case 7: // '\007'
            parcel.enforceInterface("miui.IProcessManager");
            boolean flag3;
            if(parcel.readInt() != 0)
                flag3 = true;
            else
                flag3 = false;
            flag3 = protectCurrentProcess(flag3, parcel.readInt());
            parcel1.writeNoException();
            if(flag3)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;

        case 8: // '\b'
            parcel.enforceInterface("miui.IProcessManager");
            updateCloudData((ProcessCloudData)ProcessCloudData.CREATOR.createFromParcel(parcel));
            parcel1.writeNoException();
            return true;

        case 9: // '\t'
            parcel.enforceInterface("miui.IProcessManager");
            boolean flag4 = isLockedApplication(parcel.readString(), parcel.readInt());
            parcel1.writeNoException();
            if(flag4)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;

        case 10: // '\n'
            parcel.enforceInterface("miui.IProcessManager");
            registerForegroundInfoListener(IForegroundInfoListener.Stub.asInterface(parcel.readStrongBinder()));
            return true;

        case 11: // '\013'
            parcel.enforceInterface("miui.IProcessManager");
            unregisterForegroundInfoListener(IForegroundInfoListener.Stub.asInterface(parcel.readStrongBinder()));
            return true;

        case 12: // '\f'
            parcel.enforceInterface("miui.IProcessManager");
            parcel = getForegroundInfo();
            parcel1.writeNoException();
            parcel.writeToParcel(parcel1, 0);
            return true;

        case 13: // '\r'
            parcel.enforceInterface("miui.IProcessManager");
            IBinder ibinder = parcel.readStrongBinder();
            i = parcel.readInt();
            if(ibinder != null)
                parcel = IMiuiApplicationThread.Stub.asInterface(ibinder);
            else
                parcel = null;
            addMiuiApplicationThread(parcel, i);
            parcel1.writeNoException();
            return true;

        case 14: // '\016'
            parcel.enforceInterface("miui.IProcessManager");
            parcel = getForegroundApplicationThread();
            parcel1.writeNoException();
            if(parcel != null)
                parcel = parcel.asBinder();
            else
                parcel = null;
            parcel1.writeStrongBinder(parcel);
            return true;

        case 15: // '\017'
            parcel.enforceInterface("miui.IProcessManager");
            parcel1 = new ArrayList();
            parcel.readStringList(parcel1);
            ArrayList arraylist1 = new ArrayList();
            parcel.readStringList(arraylist1);
            registerActivityChangeListener(parcel1, arraylist1, IActivityChangeListener.Stub.asInterface(parcel.readStrongBinder()));
            return true;

        case 16: // '\020'
            parcel.enforceInterface("miui.IProcessManager");
            unregisterActivityChangeListener(IActivityChangeListener.Stub.asInterface(parcel.readStrongBinder()));
            return true;
        }
    }

    private static volatile IProcessManager pm = null;

}
