// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.companion;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.os.*;
import java.util.List;

// Referenced classes of package android.companion:
//            AssociationRequest, IFindDeviceCallback

public interface ICompanionDeviceManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICompanionDeviceManager
    {

        public static ICompanionDeviceManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.companion.ICompanionDeviceManager");
            if(iinterface != null && (iinterface instanceof ICompanionDeviceManager))
                return (ICompanionDeviceManager)iinterface;
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
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.companion.ICompanionDeviceManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.companion.ICompanionDeviceManager");
                AssociationRequest associationrequest;
                if(parcel.readInt() != 0)
                    associationrequest = (AssociationRequest)AssociationRequest.CREATOR.createFromParcel(parcel);
                else
                    associationrequest = null;
                associate(associationrequest, IFindDeviceCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.companion.ICompanionDeviceManager");
                AssociationRequest associationrequest1;
                if(parcel.readInt() != 0)
                    associationrequest1 = (AssociationRequest)AssociationRequest.CREATOR.createFromParcel(parcel);
                else
                    associationrequest1 = null;
                stopScan(associationrequest1, IFindDeviceCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.companion.ICompanionDeviceManager");
                parcel = getAssociations(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.companion.ICompanionDeviceManager");
                disassociate(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.companion.ICompanionDeviceManager");
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag = hasNotificationAccess(parcel);
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.companion.ICompanionDeviceManager");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            parcel = requestNotificationAccess(parcel);
            parcel1.writeNoException();
            if(parcel != null)
            {
                parcel1.writeInt(1);
                parcel.writeToParcel(parcel1, 1);
            } else
            {
                parcel1.writeInt(0);
            }
            return true;
        }

        private static final String DESCRIPTOR = "android.companion.ICompanionDeviceManager";
        static final int TRANSACTION_associate = 1;
        static final int TRANSACTION_disassociate = 4;
        static final int TRANSACTION_getAssociations = 3;
        static final int TRANSACTION_hasNotificationAccess = 5;
        static final int TRANSACTION_requestNotificationAccess = 6;
        static final int TRANSACTION_stopScan = 2;

        public Stub()
        {
            attachInterface(this, "android.companion.ICompanionDeviceManager");
        }
    }

    private static class Stub.Proxy
        implements ICompanionDeviceManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void associate(AssociationRequest associationrequest, IFindDeviceCallback ifinddevicecallback, String s)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.companion.ICompanionDeviceManager");
            if(associationrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            associationrequest.writeToParcel(parcel, 0);
_L4:
            associationrequest = obj;
            if(ifinddevicecallback == null)
                break MISSING_BLOCK_LABEL_51;
            associationrequest = ifinddevicecallback.asBinder();
            parcel.writeStrongBinder(associationrequest);
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            associationrequest;
            parcel1.recycle();
            parcel.recycle();
            throw associationrequest;
        }

        public void disassociate(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.companion.ICompanionDeviceManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public List getAssociations(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.companion.ICompanionDeviceManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.companion.ICompanionDeviceManager";
        }

        public boolean hasNotificationAccess(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.companion.ICompanionDeviceManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(5, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public PendingIntent requestNotificationAccess(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.companion.ICompanionDeviceManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_97;
            componentname = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public void stopScan(AssociationRequest associationrequest, IFindDeviceCallback ifinddevicecallback, String s)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.companion.ICompanionDeviceManager");
            if(associationrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            associationrequest.writeToParcel(parcel, 0);
_L4:
            associationrequest = obj;
            if(ifinddevicecallback == null)
                break MISSING_BLOCK_LABEL_51;
            associationrequest = ifinddevicecallback.asBinder();
            parcel.writeStrongBinder(associationrequest);
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            associationrequest;
            parcel1.recycle();
            parcel.recycle();
            throw associationrequest;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void associate(AssociationRequest associationrequest, IFindDeviceCallback ifinddevicecallback, String s)
        throws RemoteException;

    public abstract void disassociate(String s, String s1)
        throws RemoteException;

    public abstract List getAssociations(String s, int i)
        throws RemoteException;

    public abstract boolean hasNotificationAccess(ComponentName componentname)
        throws RemoteException;

    public abstract PendingIntent requestNotificationAccess(ComponentName componentname)
        throws RemoteException;

    public abstract void stopScan(AssociationRequest associationrequest, IFindDeviceCallback ifinddevicecallback, String s)
        throws RemoteException;
}
