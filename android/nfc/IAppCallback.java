// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc;

import android.os.*;

// Referenced classes of package android.nfc:
//            BeamShareData, Tag

public interface IAppCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAppCallback
    {

        public static IAppCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.nfc.IAppCallback");
            if(iinterface != null && (iinterface instanceof IAppCallback))
                return (IAppCallback)iinterface;
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
                parcel1.writeString("android.nfc.IAppCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.nfc.IAppCallback");
                parcel = createBeamShareData(parcel.readByte());
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

            case 2: // '\002'
                parcel.enforceInterface("android.nfc.IAppCallback");
                onNdefPushComplete(parcel.readByte());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.nfc.IAppCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Tag)Tag.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onTagDiscovered(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.nfc.IAppCallback";
        static final int TRANSACTION_createBeamShareData = 1;
        static final int TRANSACTION_onNdefPushComplete = 2;
        static final int TRANSACTION_onTagDiscovered = 3;

        public Stub()
        {
            attachInterface(this, "android.nfc.IAppCallback");
        }
    }

    private static class Stub.Proxy
        implements IAppCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public BeamShareData createBeamShareData(byte byte0)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.IAppCallback");
            parcel.writeByte(byte0);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            BeamShareData beamsharedata = (BeamShareData)BeamShareData.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return beamsharedata;
_L2:
            beamsharedata = null;
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
            return "android.nfc.IAppCallback";
        }

        public void onNdefPushComplete(byte byte0)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.IAppCallback");
            parcel.writeByte(byte0);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTagDiscovered(Tag tag)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.IAppCallback");
            if(tag == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            tag.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            tag;
            parcel.recycle();
            throw tag;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract BeamShareData createBeamShareData(byte byte0)
        throws RemoteException;

    public abstract void onNdefPushComplete(byte byte0)
        throws RemoteException;

    public abstract void onTagDiscovered(Tag tag)
        throws RemoteException;
}
