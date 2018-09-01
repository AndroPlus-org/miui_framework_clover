// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.graphics.Bitmap;
import android.os.*;

public interface IAssistScreenshotReceiver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAssistScreenshotReceiver
    {

        public static IAssistScreenshotReceiver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.IAssistScreenshotReceiver");
            if(iinterface != null && (iinterface instanceof IAssistScreenshotReceiver))
                return (IAssistScreenshotReceiver)iinterface;
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
                parcel1.writeString("com.android.internal.app.IAssistScreenshotReceiver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.IAssistScreenshotReceiver");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            send(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.app.IAssistScreenshotReceiver";
        static final int TRANSACTION_send = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.IAssistScreenshotReceiver");
        }
    }

    private static class Stub.Proxy
        implements IAssistScreenshotReceiver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.IAssistScreenshotReceiver";
        }

        public void send(Bitmap bitmap)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAssistScreenshotReceiver");
            if(bitmap == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            bitmap.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bitmap;
            parcel.recycle();
            throw bitmap;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void send(Bitmap bitmap)
        throws RemoteException;
}
