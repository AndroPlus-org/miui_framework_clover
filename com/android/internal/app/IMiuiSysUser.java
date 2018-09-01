// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.content.ComponentName;
import android.os.*;

public interface IMiuiSysUser
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMiuiSysUser
    {

        public static IMiuiSysUser asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.IMiuiSysUser");
            if(iinterface != null && (iinterface instanceof IMiuiSysUser))
                return (IMiuiSysUser)iinterface;
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
                parcel1.writeString("com.android.internal.app.IMiuiSysUser");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.IMiuiSysUser");
                long l = parcel.readLong();
                j = parcel.readInt();
                i = parcel.readInt();
                String s = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                notifyAMProcStart(l, j, i, s, parcel1, parcel.readString());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.app.IMiuiSysUser");
                notifyAMProcDied(parcel.readInt(), parcel.readString());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.app.IMiuiSysUser");
                notifyAMDestroyActivity(parcel.readInt(), parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.app.IMiuiSysUser");
                notifyAMPauseActivity(parcel.readInt(), parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.app.IMiuiSysUser");
                if(parcel.readInt() != 0)
                    parcel1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                notifyAMResumeActivity(parcel1, parcel.readInt(), parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.app.IMiuiSysUser");
                if(parcel.readInt() != 0)
                    parcel1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                notifyAMRestartActivity(parcel1, parcel.readInt(), parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.app.IMiuiSysUser");
                if(parcel.readInt() != 0)
                    parcel1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                notifyAMCreateActivity(parcel1, parcel.readInt(), parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.app.IMiuiSysUser");
                parcel1 = parcel.readString();
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            notifyEvent(parcel1, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.app.IMiuiSysUser";
        static final int TRANSACTION_notifyAMCreateActivity = 7;
        static final int TRANSACTION_notifyAMDestroyActivity = 3;
        static final int TRANSACTION_notifyAMPauseActivity = 4;
        static final int TRANSACTION_notifyAMProcDied = 2;
        static final int TRANSACTION_notifyAMProcStart = 1;
        static final int TRANSACTION_notifyAMRestartActivity = 6;
        static final int TRANSACTION_notifyAMResumeActivity = 5;
        static final int TRANSACTION_notifyEvent = 8;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.IMiuiSysUser");
        }
    }

    private static class Stub.Proxy
        implements IMiuiSysUser
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.IMiuiSysUser";
        }

        public void notifyAMCreateActivity(ComponentName componentname, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IMiuiSysUser");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel.recycle();
            throw componentname;
        }

        public void notifyAMDestroyActivity(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IMiuiSysUser");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void notifyAMPauseActivity(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IMiuiSysUser");
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

        public void notifyAMProcDied(int i, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IMiuiSysUser");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void notifyAMProcStart(long l, int i, int j, String s, ComponentName componentname, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IMiuiSysUser");
            parcel.writeLong(l);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_85;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s1);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void notifyAMRestartActivity(ComponentName componentname, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IMiuiSysUser");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel.recycle();
            throw componentname;
        }

        public void notifyAMResumeActivity(ComponentName componentname, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IMiuiSysUser");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_62;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel.recycle();
            throw componentname;
        }

        public void notifyEvent(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IMiuiSysUser");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void notifyAMCreateActivity(ComponentName componentname, int i, int j)
        throws RemoteException;

    public abstract void notifyAMDestroyActivity(int i, int j)
        throws RemoteException;

    public abstract void notifyAMPauseActivity(int i, int j)
        throws RemoteException;

    public abstract void notifyAMProcDied(int i, String s)
        throws RemoteException;

    public abstract void notifyAMProcStart(long l, int i, int j, String s, ComponentName componentname, String s1)
        throws RemoteException;

    public abstract void notifyAMRestartActivity(ComponentName componentname, int i, int j)
        throws RemoteException;

    public abstract void notifyAMResumeActivity(ComponentName componentname, int i, int j)
        throws RemoteException;

    public abstract void notifyEvent(String s, Bundle bundle)
        throws RemoteException;
}
