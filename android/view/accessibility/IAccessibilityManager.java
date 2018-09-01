// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.accessibilityservice.IAccessibilityServiceClient;
import android.content.ComponentName;
import android.os.*;
import android.view.IWindow;
import java.util.List;

// Referenced classes of package android.view.accessibility:
//            IAccessibilityInteractionConnection, IAccessibilityManagerClient, AccessibilityEvent

public interface IAccessibilityManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAccessibilityManager
    {

        public static IAccessibilityManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.accessibility.IAccessibilityManager");
            if(iinterface != null && (iinterface instanceof IAccessibilityManager))
                return (IAccessibilityManager)iinterface;
            else
                return new Proxy(ibinder);
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            boolean flag2;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.view.accessibility.IAccessibilityManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityManager");
                interrupt(parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityManager");
                if(parcel.readInt() != 0)
                    parcel1 = (AccessibilityEvent)AccessibilityEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                sendAccessibilityEvent(parcel1, parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityManager");
                long l = addClient(IAccessibilityManagerClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityManager");
                parcel = getInstalledAccessibilityServiceList(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityManager");
                parcel = getEnabledAccessibilityServiceList(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityManager");
                i = addAccessibilityInteractionConnection(android.view.IWindow.Stub.asInterface(parcel.readStrongBinder()), IAccessibilityInteractionConnection.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityManager");
                removeAccessibilityInteractionConnection(android.view.IWindow.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityManager");
                setPictureInPictureActionReplacingConnection(IAccessibilityInteractionConnection.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityManager");
                IBinder ibinder = parcel.readStrongBinder();
                IAccessibilityServiceClient iaccessibilityserviceclient = android.accessibilityservice.IAccessibilityServiceClient.Stub.asInterface(parcel.readStrongBinder());
                AccessibilityServiceInfo accessibilityserviceinfo;
                if(parcel.readInt() != 0)
                    accessibilityserviceinfo = (AccessibilityServiceInfo)AccessibilityServiceInfo.CREATOR.createFromParcel(parcel);
                else
                    accessibilityserviceinfo = null;
                registerUiTestAutomationService(ibinder, iaccessibilityserviceclient, accessibilityserviceinfo, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityManager");
                unregisterUiTestAutomationService(android.accessibilityservice.IAccessibilityServiceClient.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityManager");
                ComponentName componentname;
                boolean flag;
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                temporaryEnableAccessibilityStateUntilKeyguardRemoved(componentname, flag);
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityManager");
                parcel = getWindowToken(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeStrongBinder(parcel);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityManager");
                notifyAccessibilityButtonClicked();
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityManager");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                notifyAccessibilityButtonVisibilityChanged(flag1);
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityManager");
                performAccessibilityShortcut();
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityManager");
                flag2 = sendFingerprintGesture(parcel.readInt());
                parcel1.writeNoException();
                break;
            }
            if(flag2)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.view.accessibility.IAccessibilityManager";
        static final int TRANSACTION_addAccessibilityInteractionConnection = 6;
        static final int TRANSACTION_addClient = 3;
        static final int TRANSACTION_getEnabledAccessibilityServiceList = 5;
        static final int TRANSACTION_getInstalledAccessibilityServiceList = 4;
        static final int TRANSACTION_getWindowToken = 12;
        static final int TRANSACTION_interrupt = 1;
        static final int TRANSACTION_notifyAccessibilityButtonClicked = 13;
        static final int TRANSACTION_notifyAccessibilityButtonVisibilityChanged = 14;
        static final int TRANSACTION_performAccessibilityShortcut = 15;
        static final int TRANSACTION_registerUiTestAutomationService = 9;
        static final int TRANSACTION_removeAccessibilityInteractionConnection = 7;
        static final int TRANSACTION_sendAccessibilityEvent = 2;
        static final int TRANSACTION_sendFingerprintGesture = 16;
        static final int TRANSACTION_setPictureInPictureActionReplacingConnection = 8;
        static final int TRANSACTION_temporaryEnableAccessibilityStateUntilKeyguardRemoved = 11;
        static final int TRANSACTION_unregisterUiTestAutomationService = 10;

        public Stub()
        {
            attachInterface(this, "android.view.accessibility.IAccessibilityManager");
        }
    }

    private static class Stub.Proxy
        implements IAccessibilityManager
    {

        public int addAccessibilityInteractionConnection(IWindow iwindow, IAccessibilityInteractionConnection iaccessibilityinteractionconnection, int i)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityManager");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_103;
            iwindow = iwindow.asBinder();
_L1:
            parcel.writeStrongBinder(iwindow);
            iwindow = obj;
            if(iaccessibilityinteractionconnection == null)
                break MISSING_BLOCK_LABEL_51;
            iwindow = iaccessibilityinteractionconnection.asBinder();
            parcel.writeStrongBinder(iwindow);
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            iwindow = null;
              goto _L1
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public long addClient(IAccessibilityManagerClient iaccessibilitymanagerclient, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityManager");
            if(iaccessibilitymanagerclient == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iaccessibilitymanagerclient.asBinder();
            long l;
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            iaccessibilitymanagerclient;
            parcel1.recycle();
            parcel.recycle();
            throw iaccessibilitymanagerclient;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public List getEnabledAccessibilityServiceList(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(AccessibilityServiceInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getInstalledAccessibilityServiceList(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityManager");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(AccessibilityServiceInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.accessibility.IAccessibilityManager";
        }

        public IBinder getWindowToken(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IBinder ibinder;
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            ibinder = parcel1.readStrongBinder();
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void interrupt(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityManager");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void notifyAccessibilityButtonClicked()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityManager");
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void notifyAccessibilityButtonVisibilityChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void performAccessibilityShortcut()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityManager");
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void registerUiTestAutomationService(IBinder ibinder, IAccessibilityServiceClient iaccessibilityserviceclient, AccessibilityServiceInfo accessibilityserviceinfo, int i)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityManager");
            parcel.writeStrongBinder(ibinder);
            ibinder = obj;
            if(iaccessibilityserviceclient == null)
                break MISSING_BLOCK_LABEL_40;
            ibinder = iaccessibilityserviceclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(accessibilityserviceinfo == null)
                break MISSING_BLOCK_LABEL_103;
            parcel.writeInt(1);
            accessibilityserviceinfo.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void removeAccessibilityInteractionConnection(IWindow iwindow)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityManager");
            if(iwindow == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iwindow.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iwindow;
            parcel1.recycle();
            parcel.recycle();
            throw iwindow;
        }

        public void sendAccessibilityEvent(AccessibilityEvent accessibilityevent, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityManager");
            if(accessibilityevent == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            accessibilityevent.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            accessibilityevent;
            parcel.recycle();
            throw accessibilityevent;
        }

        public boolean sendFingerprintGesture(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityManager");
            parcel.writeInt(i);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setPictureInPictureActionReplacingConnection(IAccessibilityInteractionConnection iaccessibilityinteractionconnection)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityManager");
            if(iaccessibilityinteractionconnection == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iaccessibilityinteractionconnection.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iaccessibilityinteractionconnection;
            parcel1.recycle();
            parcel.recycle();
            throw iaccessibilityinteractionconnection;
        }

        public void temporaryEnableAccessibilityStateUntilKeyguardRemoved(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void unregisterUiTestAutomationService(IAccessibilityServiceClient iaccessibilityserviceclient)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityManager");
            if(iaccessibilityserviceclient == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iaccessibilityserviceclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iaccessibilityserviceclient;
            parcel1.recycle();
            parcel.recycle();
            throw iaccessibilityserviceclient;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int addAccessibilityInteractionConnection(IWindow iwindow, IAccessibilityInteractionConnection iaccessibilityinteractionconnection, int i)
        throws RemoteException;

    public abstract long addClient(IAccessibilityManagerClient iaccessibilitymanagerclient, int i)
        throws RemoteException;

    public abstract List getEnabledAccessibilityServiceList(int i, int j)
        throws RemoteException;

    public abstract List getInstalledAccessibilityServiceList(int i)
        throws RemoteException;

    public abstract IBinder getWindowToken(int i, int j)
        throws RemoteException;

    public abstract void interrupt(int i)
        throws RemoteException;

    public abstract void notifyAccessibilityButtonClicked()
        throws RemoteException;

    public abstract void notifyAccessibilityButtonVisibilityChanged(boolean flag)
        throws RemoteException;

    public abstract void performAccessibilityShortcut()
        throws RemoteException;

    public abstract void registerUiTestAutomationService(IBinder ibinder, IAccessibilityServiceClient iaccessibilityserviceclient, AccessibilityServiceInfo accessibilityserviceinfo, int i)
        throws RemoteException;

    public abstract void removeAccessibilityInteractionConnection(IWindow iwindow)
        throws RemoteException;

    public abstract void sendAccessibilityEvent(AccessibilityEvent accessibilityevent, int i)
        throws RemoteException;

    public abstract boolean sendFingerprintGesture(int i)
        throws RemoteException;

    public abstract void setPictureInPictureActionReplacingConnection(IAccessibilityInteractionConnection iaccessibilityinteractionconnection)
        throws RemoteException;

    public abstract void temporaryEnableAccessibilityStateUntilKeyguardRemoved(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract void unregisterUiTestAutomationService(IAccessibilityServiceClient iaccessibilityserviceclient)
        throws RemoteException;
}
