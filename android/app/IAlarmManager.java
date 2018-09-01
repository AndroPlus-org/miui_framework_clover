// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.*;

// Referenced classes of package android.app:
//            PendingIntent, IAlarmListener

public interface IAlarmManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAlarmManager
    {

        public static IAlarmManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IAlarmManager");
            if(iinterface != null && (iinterface instanceof IAlarmManager))
                return (IAlarmManager)iinterface;
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
                parcel1.writeString("android.app.IAlarmManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IAlarmManager");
                String s = parcel.readString();
                j = parcel.readInt();
                long l = parcel.readLong();
                long l1 = parcel.readLong();
                long l3 = parcel.readLong();
                i = parcel.readInt();
                PendingIntent pendingintent;
                IAlarmListener ialarmlistener;
                String s1;
                WorkSource worksource;
                if(parcel.readInt() != 0)
                    pendingintent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent = null;
                ialarmlistener = IAlarmListener.Stub.asInterface(parcel.readStrongBinder());
                s1 = parcel.readString();
                if(parcel.readInt() != 0)
                    worksource = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    worksource = null;
                if(parcel.readInt() != 0)
                    parcel = (AlarmManager.AlarmClockInfo)AlarmManager.AlarmClockInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                set(s, j, l, l1, l3, i, pendingintent, ialarmlistener, s1, worksource, parcel);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.IAlarmManager");
                boolean flag = setTime(parcel.readLong());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.IAlarmManager");
                setTimeZone(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.IAlarmManager");
                PendingIntent pendingintent1;
                if(parcel.readInt() != 0)
                    pendingintent1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent1 = null;
                remove(pendingintent1, IAlarmListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.IAlarmManager");
                long l2 = getNextWakeFromIdleTime();
                parcel1.writeNoException();
                parcel1.writeLong(l2);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.app.IAlarmManager");
                parcel = getNextAlarmClock(parcel.readInt());
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

            case 7: // '\007'
                parcel.enforceInterface("android.app.IAlarmManager");
                i = parcel.readInt();
                break;
            }
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            updateBlockedUids(i, flag1);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.app.IAlarmManager";
        static final int TRANSACTION_getNextAlarmClock = 6;
        static final int TRANSACTION_getNextWakeFromIdleTime = 5;
        static final int TRANSACTION_remove = 4;
        static final int TRANSACTION_set = 1;
        static final int TRANSACTION_setTime = 2;
        static final int TRANSACTION_setTimeZone = 3;
        static final int TRANSACTION_updateBlockedUids = 7;

        public Stub()
        {
            attachInterface(this, "android.app.IAlarmManager");
        }
    }

    private static class Stub.Proxy
        implements IAlarmManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IAlarmManager";
        }

        public AlarmManager.AlarmClockInfo getNextAlarmClock(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IAlarmManager");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            AlarmManager.AlarmClockInfo alarmclockinfo = (AlarmManager.AlarmClockInfo)AlarmManager.AlarmClockInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return alarmclockinfo;
_L2:
            alarmclockinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public long getNextWakeFromIdleTime()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.app.IAlarmManager");
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void remove(PendingIntent pendingintent, IAlarmListener ialarmlistener)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IAlarmManager");
            if(pendingintent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L4:
            pendingintent = obj;
            if(ialarmlistener == null)
                break MISSING_BLOCK_LABEL_49;
            pendingintent = ialarmlistener.asBinder();
            parcel.writeStrongBinder(pendingintent);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            pendingintent;
            parcel1.recycle();
            parcel.recycle();
            throw pendingintent;
        }

        public void set(String s, int i, long l, long l1, long l2, int j, PendingIntent pendingintent, IAlarmListener ialarmlistener, String s1, WorkSource worksource, AlarmManager.AlarmClockInfo alarmclockinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IAlarmManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeLong(l1);
            parcel.writeLong(l2);
            parcel.writeInt(j);
            if(pendingintent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L7:
            if(ialarmlistener == null) goto _L4; else goto _L3
_L3:
            s = ialarmlistener.asBinder();
_L8:
            parcel.writeStrongBinder(s);
            parcel.writeString(s1);
            if(worksource == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L9:
            if(alarmclockinfo == null)
                break MISSING_BLOCK_LABEL_207;
            parcel.writeInt(1);
            alarmclockinfo.writeToParcel(parcel, 0);
_L10:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L7
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
_L4:
            s = null;
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            parcel.writeInt(0);
              goto _L10
        }

        public boolean setTime(long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.IAlarmManager");
            parcel.writeLong(l);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setTimeZone(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IAlarmManager");
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void updateBlockedUids(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IAlarmManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract AlarmManager.AlarmClockInfo getNextAlarmClock(int i)
        throws RemoteException;

    public abstract long getNextWakeFromIdleTime()
        throws RemoteException;

    public abstract void remove(PendingIntent pendingintent, IAlarmListener ialarmlistener)
        throws RemoteException;

    public abstract void set(String s, int i, long l, long l1, long l2, int j, PendingIntent pendingintent, IAlarmListener ialarmlistener, String s1, WorkSource worksource, AlarmManager.AlarmClockInfo alarmclockinfo)
        throws RemoteException;

    public abstract boolean setTime(long l)
        throws RemoteException;

    public abstract void setTimeZone(String s)
        throws RemoteException;

    public abstract void updateBlockedUids(int i, boolean flag)
        throws RemoteException;
}
