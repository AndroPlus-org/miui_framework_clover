// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc;

import android.os.*;

// Referenced classes of package android.nfc:
//            Tag

public interface INfcUnlockHandler
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INfcUnlockHandler
    {

        public static INfcUnlockHandler asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.nfc.INfcUnlockHandler");
            if(iinterface != null && (iinterface instanceof INfcUnlockHandler))
                return (INfcUnlockHandler)iinterface;
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
            boolean flag = false;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.nfc.INfcUnlockHandler");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.nfc.INfcUnlockHandler");
                break;
            }
            boolean flag1;
            if(parcel.readInt() != 0)
                parcel = (Tag)Tag.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            flag1 = onUnlockAttempted(parcel);
            parcel1.writeNoException();
            i = ((flag) ? 1 : 0);
            if(flag1)
                i = 1;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.nfc.INfcUnlockHandler";
        static final int TRANSACTION_onUnlockAttempted = 1;

        public Stub()
        {
            attachInterface(this, "android.nfc.INfcUnlockHandler");
        }
    }

    private static class Stub.Proxy
        implements INfcUnlockHandler
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.nfc.INfcUnlockHandler";
        }

        public boolean onUnlockAttempted(Tag tag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcUnlockHandler");
            if(tag == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            tag.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(1, parcel, parcel1, 0);
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
            tag;
            parcel1.recycle();
            parcel.recycle();
            throw tag;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean onUnlockAttempted(Tag tag)
        throws RemoteException;
}
