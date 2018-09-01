// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.watermark;

import android.graphics.Bitmap;
import android.os.*;

public interface IWatermarkCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWatermarkCallback
    {

        public static IWatermarkCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.miui.whetstone.watermark.IWatermarkCallback");
            if(iinterface != null && (iinterface instanceof IWatermarkCallback))
                return (IWatermarkCallback)iinterface;
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
                parcel1.writeString("com.miui.whetstone.watermark.IWatermarkCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.miui.whetstone.watermark.IWatermarkCallback");
                if(parcel.readInt() != 0)
                    parcel = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onEncodeWatermark(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.miui.whetstone.watermark.IWatermarkCallback");
                onDecodeWatermark(parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.miui.whetstone.watermark.IWatermarkCallback";
        static final int TRANSACTION_onDecodeWatermark = 2;
        static final int TRANSACTION_onEncodeWatermark = 1;

        public Stub()
        {
            attachInterface(this, "com.miui.whetstone.watermark.IWatermarkCallback");
        }
    }

    private static class Stub.Proxy
        implements IWatermarkCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.miui.whetstone.watermark.IWatermarkCallback";
        }

        public void onDecodeWatermark(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.watermark.IWatermarkCallback");
            parcel.writeString(s);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onEncodeWatermark(Bitmap bitmap)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.watermark.IWatermarkCallback");
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


    public abstract void onDecodeWatermark(String s)
        throws RemoteException;

    public abstract void onEncodeWatermark(Bitmap bitmap)
        throws RemoteException;
}
