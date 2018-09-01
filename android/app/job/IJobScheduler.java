// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.job;

import android.os.*;
import java.util.List;

// Referenced classes of package android.app.job:
//            JobInfo, JobWorkItem

public interface IJobScheduler
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IJobScheduler
    {

        public static IJobScheduler asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.job.IJobScheduler");
            if(iinterface != null && (iinterface instanceof IJobScheduler))
                return (IJobScheduler)iinterface;
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
                parcel1.writeString("android.app.job.IJobScheduler");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.job.IJobScheduler");
                if(parcel.readInt() != 0)
                    parcel = (JobInfo)JobInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = schedule(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.job.IJobScheduler");
                JobInfo jobinfo;
                if(parcel.readInt() != 0)
                    jobinfo = (JobInfo)JobInfo.CREATOR.createFromParcel(parcel);
                else
                    jobinfo = null;
                if(parcel.readInt() != 0)
                    parcel = (JobWorkItem)JobWorkItem.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = enqueue(jobinfo, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.job.IJobScheduler");
                JobInfo jobinfo1;
                if(parcel.readInt() != 0)
                    jobinfo1 = (JobInfo)JobInfo.CREATOR.createFromParcel(parcel);
                else
                    jobinfo1 = null;
                i = scheduleAsPackage(jobinfo1, parcel.readString(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.job.IJobScheduler");
                cancel(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.job.IJobScheduler");
                cancelAll();
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.app.job.IJobScheduler");
                parcel = getAllPendingJobs();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.app.job.IJobScheduler");
                parcel = getPendingJob(parcel.readInt());
                parcel1.writeNoException();
                break;
            }
            if(parcel != null)
            {
                parcel1.writeInt(1);
                parcel.writeToParcel(parcel1, 1);
            } else
            {
                parcel1.writeInt(0);
            }
            return true;
        }

        private static final String DESCRIPTOR = "android.app.job.IJobScheduler";
        static final int TRANSACTION_cancel = 4;
        static final int TRANSACTION_cancelAll = 5;
        static final int TRANSACTION_enqueue = 2;
        static final int TRANSACTION_getAllPendingJobs = 6;
        static final int TRANSACTION_getPendingJob = 7;
        static final int TRANSACTION_schedule = 1;
        static final int TRANSACTION_scheduleAsPackage = 3;

        public Stub()
        {
            attachInterface(this, "android.app.job.IJobScheduler");
        }
    }

    private static class Stub.Proxy
        implements IJobScheduler
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancel(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.job.IJobScheduler");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
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

        public void cancelAll()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.job.IJobScheduler");
            mRemote.transact(5, parcel, parcel1, 0);
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

        public int enqueue(JobInfo jobinfo, JobWorkItem jobworkitem)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.job.IJobScheduler");
            if(jobinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            jobinfo.writeToParcel(parcel, 0);
_L3:
            if(jobworkitem == null)
                break MISSING_BLOCK_LABEL_104;
            parcel.writeInt(1);
            jobworkitem.writeToParcel(parcel, 0);
_L4:
            int i;
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L3
            jobinfo;
            parcel1.recycle();
            parcel.recycle();
            throw jobinfo;
            parcel.writeInt(0);
              goto _L4
        }

        public List getAllPendingJobs()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.app.job.IJobScheduler");
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(JobInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.job.IJobScheduler";
        }

        public JobInfo getPendingJob(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.job.IJobScheduler");
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            JobInfo jobinfo = (JobInfo)JobInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return jobinfo;
_L2:
            jobinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int schedule(JobInfo jobinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.job.IJobScheduler");
            if(jobinfo == null)
                break MISSING_BLOCK_LABEL_64;
            parcel.writeInt(1);
            jobinfo.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            jobinfo;
            parcel1.recycle();
            parcel.recycle();
            throw jobinfo;
        }

        public int scheduleAsPackage(JobInfo jobinfo, String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.job.IJobScheduler");
            if(jobinfo == null)
                break MISSING_BLOCK_LABEL_92;
            parcel.writeInt(1);
            jobinfo.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            jobinfo;
            parcel1.recycle();
            parcel.recycle();
            throw jobinfo;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void cancel(int i)
        throws RemoteException;

    public abstract void cancelAll()
        throws RemoteException;

    public abstract int enqueue(JobInfo jobinfo, JobWorkItem jobworkitem)
        throws RemoteException;

    public abstract List getAllPendingJobs()
        throws RemoteException;

    public abstract JobInfo getPendingJob(int i)
        throws RemoteException;

    public abstract int schedule(JobInfo jobinfo)
        throws RemoteException;

    public abstract int scheduleAsPackage(JobInfo jobinfo, String s, int i, String s1)
        throws RemoteException;
}
