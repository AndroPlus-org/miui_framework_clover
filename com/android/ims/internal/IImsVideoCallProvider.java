// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.net.Uri;
import android.os.*;
import android.telecom.VideoProfile;
import android.view.Surface;

// Referenced classes of package com.android.ims.internal:
//            IImsVideoCallCallback

public interface IImsVideoCallProvider
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IImsVideoCallProvider
    {

        public static IImsVideoCallProvider asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.IImsVideoCallProvider");
            if(iinterface != null && (iinterface instanceof IImsVideoCallProvider))
                return (IImsVideoCallProvider)iinterface;
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
                parcel1.writeString("com.android.ims.internal.IImsVideoCallProvider");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallProvider");
                setCallback(IImsVideoCallCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallProvider");
                setCamera(parcel.readString(), parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallProvider");
                if(parcel.readInt() != 0)
                    parcel = (Surface)Surface.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setPreviewSurface(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallProvider");
                if(parcel.readInt() != 0)
                    parcel = (Surface)Surface.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setDisplaySurface(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallProvider");
                setDeviceOrientation(parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallProvider");
                setZoom(parcel.readFloat());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallProvider");
                if(parcel.readInt() != 0)
                    parcel1 = (VideoProfile)VideoProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (VideoProfile)VideoProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                sendSessionModifyRequest(parcel1, parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallProvider");
                if(parcel.readInt() != 0)
                    parcel = (VideoProfile)VideoProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                sendSessionModifyResponse(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallProvider");
                requestCameraCapabilities();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallProvider");
                requestCallDataUsage();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallProvider");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Uri)Uri.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            setPauseImage(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.IImsVideoCallProvider";
        static final int TRANSACTION_requestCallDataUsage = 10;
        static final int TRANSACTION_requestCameraCapabilities = 9;
        static final int TRANSACTION_sendSessionModifyRequest = 7;
        static final int TRANSACTION_sendSessionModifyResponse = 8;
        static final int TRANSACTION_setCallback = 1;
        static final int TRANSACTION_setCamera = 2;
        static final int TRANSACTION_setDeviceOrientation = 5;
        static final int TRANSACTION_setDisplaySurface = 4;
        static final int TRANSACTION_setPauseImage = 11;
        static final int TRANSACTION_setPreviewSurface = 3;
        static final int TRANSACTION_setZoom = 6;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.IImsVideoCallProvider");
        }
    }

    private static class Stub.Proxy
        implements IImsVideoCallProvider
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.IImsVideoCallProvider";
        }

        public void requestCallDataUsage()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallProvider");
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void requestCameraCapabilities()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallProvider");
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void sendSessionModifyRequest(VideoProfile videoprofile, VideoProfile videoprofile1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallProvider");
            if(videoprofile == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            videoprofile.writeToParcel(parcel, 0);
_L3:
            if(videoprofile1 == null)
                break MISSING_BLOCK_LABEL_75;
            parcel.writeInt(1);
            videoprofile1.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            videoprofile;
            parcel.recycle();
            throw videoprofile;
            parcel.writeInt(0);
              goto _L4
        }

        public void sendSessionModifyResponse(VideoProfile videoprofile)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallProvider");
            if(videoprofile == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            videoprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            videoprofile;
            parcel.recycle();
            throw videoprofile;
        }

        public void setCallback(IImsVideoCallCallback iimsvideocallcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallProvider");
            if(iimsvideocallcallback == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = iimsvideocallcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            iimsvideocallcallback;
            parcel.recycle();
            throw iimsvideocallcallback;
        }

        public void setCamera(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallProvider");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void setDeviceOrientation(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallProvider");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setDisplaySurface(Surface surface)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallProvider");
            if(surface == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            surface.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            surface;
            parcel.recycle();
            throw surface;
        }

        public void setPauseImage(Uri uri)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallProvider");
            if(uri == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel.recycle();
            throw uri;
        }

        public void setPreviewSurface(Surface surface)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallProvider");
            if(surface == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            surface.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            surface;
            parcel.recycle();
            throw surface;
        }

        public void setZoom(float f)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallProvider");
            parcel.writeFloat(f);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void requestCallDataUsage()
        throws RemoteException;

    public abstract void requestCameraCapabilities()
        throws RemoteException;

    public abstract void sendSessionModifyRequest(VideoProfile videoprofile, VideoProfile videoprofile1)
        throws RemoteException;

    public abstract void sendSessionModifyResponse(VideoProfile videoprofile)
        throws RemoteException;

    public abstract void setCallback(IImsVideoCallCallback iimsvideocallcallback)
        throws RemoteException;

    public abstract void setCamera(String s, int i)
        throws RemoteException;

    public abstract void setDeviceOrientation(int i)
        throws RemoteException;

    public abstract void setDisplaySurface(Surface surface)
        throws RemoteException;

    public abstract void setPauseImage(Uri uri)
        throws RemoteException;

    public abstract void setPreviewSurface(Surface surface)
        throws RemoteException;

    public abstract void setZoom(float f)
        throws RemoteException;
}
