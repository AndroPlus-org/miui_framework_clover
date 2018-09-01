// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, Binder, IBinder, 
//            Parcel

public interface IProcessInfoService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IProcessInfoService
    {

        public static IProcessInfoService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IProcessInfoService");
            if(iinterface != null && (iinterface instanceof IProcessInfoService))
                return (IProcessInfoService)iinterface;
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
            int ai2[];
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.os.IProcessInfoService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IProcessInfoService");
                int ai[] = parcel.createIntArray();
                i = parcel.readInt();
                if(i < 0)
                    parcel = null;
                else
                    parcel = new int[i];
                getProcessStatesFromPids(ai, parcel);
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.IProcessInfoService");
                ai2 = parcel.createIntArray();
                i = parcel.readInt();
                break;
            }
            int ai1[];
            if(i < 0)
                ai1 = null;
            else
                ai1 = new int[i];
            i = parcel.readInt();
            if(i < 0)
                parcel = null;
            else
                parcel = new int[i];
            getProcessStatesAndOomScoresFromPids(ai2, ai1, parcel);
            parcel1.writeNoException();
            parcel1.writeIntArray(ai1);
            parcel1.writeIntArray(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.os.IProcessInfoService";
        static final int TRANSACTION_getProcessStatesAndOomScoresFromPids = 2;
        static final int TRANSACTION_getProcessStatesFromPids = 1;

        public Stub()
        {
            attachInterface(this, "android.os.IProcessInfoService");
        }
    }

    private static class Stub.Proxy
        implements IProcessInfoService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IProcessInfoService";
        }

        public void getProcessStatesAndOomScoresFromPids(int ai[], int ai1[], int ai2[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IProcessInfoService");
            parcel.writeIntArray(ai);
            if(ai1 != null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(-1);
_L3:
            if(ai2 != null)
                break MISSING_BLOCK_LABEL_110;
            parcel.writeInt(-1);
_L4:
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.readIntArray(ai1);
            parcel1.readIntArray(ai2);
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(ai1.length);
              goto _L3
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
            parcel.writeInt(ai2.length);
              goto _L4
        }

        public void getProcessStatesFromPids(int ai[], int ai1[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IProcessInfoService");
            parcel.writeIntArray(ai);
            if(ai1 != null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(-1);
_L1:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.readIntArray(ai1);
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(ai1.length);
              goto _L1
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void getProcessStatesAndOomScoresFromPids(int ai[], int ai1[], int ai2[])
        throws RemoteException;

    public abstract void getProcessStatesFromPids(int ai[], int ai1[])
        throws RemoteException;
}
