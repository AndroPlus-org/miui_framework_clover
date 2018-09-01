// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.content.pm.PackageInfoLite;
import android.content.res.ObbInfo;
import android.os.*;
import com.android.internal.os.IParcelFileDescriptorFactory;

public interface IMediaContainerService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMediaContainerService
    {

        public static IMediaContainerService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.IMediaContainerService");
            if(iinterface != null && (iinterface instanceof IMediaContainerService))
                return (IMediaContainerService)iinterface;
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
            String s2;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.android.internal.app.IMediaContainerService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.IMediaContainerService");
                String s = parcel.readString();
                String s1 = parcel.readString();
                String s3 = parcel.readString();
                boolean flag;
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                parcel = copyPackageToContainer(s, s1, s3, flag, flag2, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.app.IMediaContainerService");
                i = copyPackage(parcel.readString(), com.android.internal.os.IParcelFileDescriptorFactory.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.app.IMediaContainerService");
                parcel = getMinimalPackageInfo(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.app.IMediaContainerService");
                parcel = getObbInfo(parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.app.IMediaContainerService");
                clearDirectory(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.app.IMediaContainerService");
                s2 = parcel.readString();
                break;
            }
            boolean flag1;
            long l;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            l = calculateInstalledSize(s2, flag1, parcel.readString());
            parcel1.writeNoException();
            parcel1.writeLong(l);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.app.IMediaContainerService";
        static final int TRANSACTION_calculateInstalledSize = 6;
        static final int TRANSACTION_clearDirectory = 5;
        static final int TRANSACTION_copyPackage = 2;
        static final int TRANSACTION_copyPackageToContainer = 1;
        static final int TRANSACTION_getMinimalPackageInfo = 3;
        static final int TRANSACTION_getObbInfo = 4;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.IMediaContainerService");
        }
    }

    private static class Stub.Proxy
        implements IMediaContainerService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public long calculateInstalledSize(String s, boolean flag, String s1)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IMediaContainerService");
            parcel.writeString(s);
            if(flag)
                i = 1;
            long l;
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clearDirectory(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IMediaContainerService");
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int copyPackage(String s, IParcelFileDescriptorFactory iparcelfiledescriptorfactory)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IMediaContainerService");
            parcel.writeString(s);
            s = obj;
            if(iparcelfiledescriptorfactory == null)
                break MISSING_BLOCK_LABEL_38;
            s = iparcelfiledescriptorfactory.asBinder();
            int i;
            parcel.writeStrongBinder(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String copyPackageToContainer(String s, String s1, String s2, boolean flag, boolean flag1, String s3)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IMediaContainerService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            int i;
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
            parcel.writeString(s3);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.IMediaContainerService";
        }

        public PackageInfoLite getMinimalPackageInfo(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IMediaContainerService");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (PackageInfoLite)PackageInfoLite.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ObbInfo getObbInfo(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IMediaContainerService");
            parcel.writeString(s);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ObbInfo)ObbInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract long calculateInstalledSize(String s, boolean flag, String s1)
        throws RemoteException;

    public abstract void clearDirectory(String s)
        throws RemoteException;

    public abstract int copyPackage(String s, IParcelFileDescriptorFactory iparcelfiledescriptorfactory)
        throws RemoteException;

    public abstract String copyPackageToContainer(String s, String s1, String s2, boolean flag, boolean flag1, String s3)
        throws RemoteException;

    public abstract PackageInfoLite getMinimalPackageInfo(String s, int i, String s1)
        throws RemoteException;

    public abstract ObbInfo getObbInfo(String s)
        throws RemoteException;
}
