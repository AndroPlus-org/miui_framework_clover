// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.*;

// Referenced classes of package android.content:
//            Intent, IIntentReceiver

public interface IIntentSender
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IIntentSender
    {

        public static IIntentSender asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.IIntentSender");
            if(iinterface != null && (iinterface instanceof IIntentSender))
                return (IIntentSender)iinterface;
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
                parcel1.writeString("android.content.IIntentSender");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.IIntentSender");
                i = parcel.readInt();
                break;
            }
            String s;
            IBinder ibinder;
            IIntentReceiver iintentreceiver;
            String s1;
            if(parcel.readInt() != 0)
                parcel1 = (Intent)Intent.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            s = parcel.readString();
            ibinder = parcel.readStrongBinder();
            iintentreceiver = IIntentReceiver.Stub.asInterface(parcel.readStrongBinder());
            s1 = parcel.readString();
            if(parcel.readInt() != 0)
                parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            send(i, parcel1, s, ibinder, iintentreceiver, s1, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.content.IIntentSender";
        static final int TRANSACTION_send = 1;

        public Stub()
        {
            attachInterface(this, "android.content.IIntentSender");
        }
    }

    private static class Stub.Proxy
        implements IIntentSender
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.IIntentSender";
        }

        public void send(int i, Intent intent, String s, IBinder ibinder, IIntentReceiver iintentreceiver, String s1, Bundle bundle)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IIntentSender");
            parcel.writeInt(i);
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            parcel.writeStrongBinder(ibinder);
            intent = obj;
            if(iintentreceiver == null)
                break MISSING_BLOCK_LABEL_67;
            intent = iintentreceiver.asBinder();
            parcel.writeStrongBinder(intent);
            parcel.writeString(s1);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_137;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            intent;
            parcel.recycle();
            throw intent;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void send(int i, Intent intent, String s, IBinder ibinder, IIntentReceiver iintentreceiver, String s1, Bundle bundle)
        throws RemoteException;
}
