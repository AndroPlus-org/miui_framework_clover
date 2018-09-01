// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;
import java.util.List;

// Referenced classes of package android.media:
//            AudioRecordingConfiguration

public interface IRecordingConfigDispatcher
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRecordingConfigDispatcher
    {

        public static IRecordingConfigDispatcher asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IRecordingConfigDispatcher");
            if(iinterface != null && (iinterface instanceof IRecordingConfigDispatcher))
                return (IRecordingConfigDispatcher)iinterface;
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
                parcel1.writeString("android.media.IRecordingConfigDispatcher");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IRecordingConfigDispatcher");
                dispatchRecordingConfigChange(parcel.createTypedArrayList(AudioRecordingConfiguration.CREATOR));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.IRecordingConfigDispatcher";
        static final int TRANSACTION_dispatchRecordingConfigChange = 1;

        public Stub()
        {
            attachInterface(this, "android.media.IRecordingConfigDispatcher");
        }
    }

    private static class Stub.Proxy
        implements IRecordingConfigDispatcher
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void dispatchRecordingConfigChange(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRecordingConfigDispatcher");
            parcel.writeTypedList(list);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IRecordingConfigDispatcher";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void dispatchRecordingConfigChange(List list)
        throws RemoteException;
}
