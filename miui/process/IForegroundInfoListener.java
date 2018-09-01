// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.process;

import android.os.*;

// Referenced classes of package miui.process:
//            ForegroundInfo

public interface IForegroundInfoListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IForegroundInfoListener
    {

        public static IForegroundInfoListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.process.IForegroundInfoListener");
            if(iinterface != null && (iinterface instanceof IForegroundInfoListener))
                return (IForegroundInfoListener)iinterface;
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
                parcel1.writeString("miui.process.IForegroundInfoListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.process.IForegroundInfoListener");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ForegroundInfo)ForegroundInfo.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onForegroundInfoChanged(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "miui.process.IForegroundInfoListener";
        static final int TRANSACTION_onForegroundInfoChanged = 1;

        public Stub()
        {
            attachInterface(this, "miui.process.IForegroundInfoListener");
        }
    }

    private static class Stub.Proxy
        implements IForegroundInfoListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.process.IForegroundInfoListener";
        }

        public void onForegroundInfoChanged(ForegroundInfo foregroundinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.process.IForegroundInfoListener");
            if(foregroundinfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            foregroundinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            foregroundinfo;
            parcel.recycle();
            throw foregroundinfo;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onForegroundInfoChanged(ForegroundInfo foregroundinfo)
        throws RemoteException;
}
