// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.*;

// Referenced classes of package android.content:
//            Intent

public interface IIntentReceiver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IIntentReceiver
    {

        public static IIntentReceiver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.IIntentReceiver");
            if(iinterface != null && (iinterface instanceof IIntentReceiver))
                return (IIntentReceiver)iinterface;
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
                parcel1.writeString("android.content.IIntentReceiver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.IIntentReceiver");
                break;
            }
            String s;
            Bundle bundle;
            boolean flag;
            boolean flag1;
            if(parcel.readInt() != 0)
                parcel1 = (Intent)Intent.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            i = parcel.readInt();
            s = parcel.readString();
            if(parcel.readInt() != 0)
                bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                bundle = null;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            performReceive(parcel1, i, s, bundle, flag, flag1, parcel.readInt());
            return true;
        }

        private static final String DESCRIPTOR = "android.content.IIntentReceiver";
        static final int TRANSACTION_performReceive = 1;

        public Stub()
        {
            attachInterface(this, "android.content.IIntentReceiver");
        }
    }

    private static class Stub.Proxy
        implements IIntentReceiver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.IIntentReceiver";
        }

        public void performReceive(Intent intent, int i, String s, Bundle bundle, boolean flag, boolean flag1, int j)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IIntentReceiver");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_135;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(j);
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


    public abstract void performReceive(Intent intent, int i, String s, Bundle bundle, boolean flag, boolean flag1, int j)
        throws RemoteException;
}
