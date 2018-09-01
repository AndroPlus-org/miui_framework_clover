// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.chooser;

import android.os.*;
import java.util.List;

// Referenced classes of package android.service.chooser:
//            ChooserTarget

public interface IChooserTargetResult
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IChooserTargetResult
    {

        public static IChooserTargetResult asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.chooser.IChooserTargetResult");
            if(iinterface != null && (iinterface instanceof IChooserTargetResult))
                return (IChooserTargetResult)iinterface;
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
                parcel1.writeString("android.service.chooser.IChooserTargetResult");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.chooser.IChooserTargetResult");
                sendResult(parcel.createTypedArrayList(ChooserTarget.CREATOR));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.chooser.IChooserTargetResult";
        static final int TRANSACTION_sendResult = 1;

        public Stub()
        {
            attachInterface(this, "android.service.chooser.IChooserTargetResult");
        }
    }

    private static class Stub.Proxy
        implements IChooserTargetResult
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.chooser.IChooserTargetResult";
        }

        public void sendResult(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.chooser.IChooserTargetResult");
            parcel.writeTypedList(list);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void sendResult(List list)
        throws RemoteException;
}
