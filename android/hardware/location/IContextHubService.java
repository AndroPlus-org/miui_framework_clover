// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.os.*;

// Referenced classes of package android.hardware.location:
//            NanoAppFilter, ContextHubInfo, NanoAppInstanceInfo, NanoApp, 
//            IContextHubCallback, ContextHubMessage

public interface IContextHubService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IContextHubService
    {

        public static IContextHubService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.location.IContextHubService");
            if(iinterface != null && (iinterface instanceof IContextHubService))
                return (IContextHubService)iinterface;
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
                parcel1.writeString("android.hardware.location.IContextHubService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.location.IContextHubService");
                i = registerCallback(IContextHubCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.location.IContextHubService");
                parcel = getContextHubHandles();
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.location.IContextHubService");
                parcel = getContextHubInfo(parcel.readInt());
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

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.location.IContextHubService");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (NanoApp)NanoApp.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = loadNanoApp(i, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.location.IContextHubService");
                i = unloadNanoApp(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.hardware.location.IContextHubService");
                parcel = getNanoAppInstanceInfo(parcel.readInt());
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

            case 7: // '\007'
                parcel.enforceInterface("android.hardware.location.IContextHubService");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (NanoAppFilter)NanoAppFilter.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = findNanoAppOnHub(i, parcel);
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.hardware.location.IContextHubService");
                i = parcel.readInt();
                j = parcel.readInt();
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ContextHubMessage)ContextHubMessage.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            i = sendMessage(i, j, parcel);
            parcel1.writeNoException();
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.location.IContextHubService";
        static final int TRANSACTION_findNanoAppOnHub = 7;
        static final int TRANSACTION_getContextHubHandles = 2;
        static final int TRANSACTION_getContextHubInfo = 3;
        static final int TRANSACTION_getNanoAppInstanceInfo = 6;
        static final int TRANSACTION_loadNanoApp = 4;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_sendMessage = 8;
        static final int TRANSACTION_unloadNanoApp = 5;

        public Stub()
        {
            attachInterface(this, "android.hardware.location.IContextHubService");
        }
    }

    private static class Stub.Proxy
        implements IContextHubService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public int[] findNanoAppOnHub(int i, NanoAppFilter nanoappfilter)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IContextHubService");
            parcel.writeInt(i);
            if(nanoappfilter == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            nanoappfilter.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            nanoappfilter = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return nanoappfilter;
            parcel.writeInt(0);
              goto _L1
            nanoappfilter;
            parcel1.recycle();
            parcel.recycle();
            throw nanoappfilter;
        }

        public int[] getContextHubHandles()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int ai[];
            parcel.writeInterfaceToken("android.hardware.location.IContextHubService");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return ai;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ContextHubInfo getContextHubInfo(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IContextHubService");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ContextHubInfo contexthubinfo = (ContextHubInfo)ContextHubInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return contexthubinfo;
_L2:
            contexthubinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.location.IContextHubService";
        }

        public NanoAppInstanceInfo getNanoAppInstanceInfo(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IContextHubService");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            NanoAppInstanceInfo nanoappinstanceinfo = (NanoAppInstanceInfo)NanoAppInstanceInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return nanoappinstanceinfo;
_L2:
            nanoappinstanceinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int loadNanoApp(int i, NanoApp nanoapp)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IContextHubService");
            parcel.writeInt(i);
            if(nanoapp == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            nanoapp.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            nanoapp;
            parcel1.recycle();
            parcel.recycle();
            throw nanoapp;
        }

        public int registerCallback(IContextHubCallback icontexthubcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IContextHubService");
            if(icontexthubcallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = icontexthubcallback.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            icontexthubcallback;
            parcel1.recycle();
            parcel.recycle();
            throw icontexthubcallback;
        }

        public int sendMessage(int i, int j, ContextHubMessage contexthubmessage)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IContextHubService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(contexthubmessage == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            contexthubmessage.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            contexthubmessage;
            parcel1.recycle();
            parcel.recycle();
            throw contexthubmessage;
        }

        public int unloadNanoApp(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IContextHubService");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int[] findNanoAppOnHub(int i, NanoAppFilter nanoappfilter)
        throws RemoteException;

    public abstract int[] getContextHubHandles()
        throws RemoteException;

    public abstract ContextHubInfo getContextHubInfo(int i)
        throws RemoteException;

    public abstract NanoAppInstanceInfo getNanoAppInstanceInfo(int i)
        throws RemoteException;

    public abstract int loadNanoApp(int i, NanoApp nanoapp)
        throws RemoteException;

    public abstract int registerCallback(IContextHubCallback icontexthubcallback)
        throws RemoteException;

    public abstract int sendMessage(int i, int j, ContextHubMessage contexthubmessage)
        throws RemoteException;

    public abstract int unloadNanoApp(int i)
        throws RemoteException;
}
