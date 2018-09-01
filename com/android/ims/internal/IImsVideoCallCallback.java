// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.os.*;
import android.telecom.VideoProfile;

public interface IImsVideoCallCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IImsVideoCallCallback
    {

        public static IImsVideoCallCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.IImsVideoCallCallback");
            if(iinterface != null && (iinterface instanceof IImsVideoCallCallback))
                return (IImsVideoCallCallback)iinterface;
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
                parcel1.writeString("com.android.ims.internal.IImsVideoCallCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallCallback");
                if(parcel.readInt() != 0)
                    parcel = (VideoProfile)VideoProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                receiveSessionModifyRequest(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallCallback");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel1 = (VideoProfile)VideoProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (VideoProfile)VideoProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                receiveSessionModifyResponse(i, parcel1, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallCallback");
                handleCallSessionEvent(parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallCallback");
                changePeerDimensions(parcel.readInt(), parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallCallback");
                changeCallDataUsage(parcel.readLong());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallCallback");
                if(parcel.readInt() != 0)
                    parcel = (android.telecom.VideoProfile.CameraCapabilities)android.telecom.VideoProfile.CameraCapabilities.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                changeCameraCapabilities(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.ims.internal.IImsVideoCallCallback");
                changeVideoQuality(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.IImsVideoCallCallback";
        static final int TRANSACTION_changeCallDataUsage = 5;
        static final int TRANSACTION_changeCameraCapabilities = 6;
        static final int TRANSACTION_changePeerDimensions = 4;
        static final int TRANSACTION_changeVideoQuality = 7;
        static final int TRANSACTION_handleCallSessionEvent = 3;
        static final int TRANSACTION_receiveSessionModifyRequest = 1;
        static final int TRANSACTION_receiveSessionModifyResponse = 2;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.IImsVideoCallCallback");
        }
    }

    private static class Stub.Proxy
        implements IImsVideoCallCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void changeCallDataUsage(long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallCallback");
            parcel.writeLong(l);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void changeCameraCapabilities(android.telecom.VideoProfile.CameraCapabilities cameracapabilities)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallCallback");
            if(cameracapabilities == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            cameracapabilities.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            cameracapabilities;
            parcel.recycle();
            throw cameracapabilities;
        }

        public void changePeerDimensions(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void changeVideoQuality(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallCallback");
            parcel.writeInt(i);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.IImsVideoCallCallback";
        }

        public void handleCallSessionEvent(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallCallback");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void receiveSessionModifyRequest(VideoProfile videoprofile)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallCallback");
            if(videoprofile == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            videoprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            videoprofile;
            parcel.recycle();
            throw videoprofile;
        }

        public void receiveSessionModifyResponse(int i, VideoProfile videoprofile, VideoProfile videoprofile1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsVideoCallCallback");
            parcel.writeInt(i);
            if(videoprofile == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            videoprofile.writeToParcel(parcel, 0);
_L3:
            if(videoprofile1 == null)
                break MISSING_BLOCK_LABEL_90;
            parcel.writeInt(1);
            videoprofile1.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(2, parcel, null, 1);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void changeCallDataUsage(long l)
        throws RemoteException;

    public abstract void changeCameraCapabilities(android.telecom.VideoProfile.CameraCapabilities cameracapabilities)
        throws RemoteException;

    public abstract void changePeerDimensions(int i, int j)
        throws RemoteException;

    public abstract void changeVideoQuality(int i)
        throws RemoteException;

    public abstract void handleCallSessionEvent(int i)
        throws RemoteException;

    public abstract void receiveSessionModifyRequest(VideoProfile videoprofile)
        throws RemoteException;

    public abstract void receiveSessionModifyResponse(int i, VideoProfile videoprofile, VideoProfile videoprofile1)
        throws RemoteException;
}
