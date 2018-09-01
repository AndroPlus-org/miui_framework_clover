// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.job;

import android.os.*;

// Referenced classes of package android.app.job:
//            JobParameters

public interface IJobService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IJobService
    {

        public static IJobService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.job.IJobService");
            if(iinterface != null && (iinterface instanceof IJobService))
                return (IJobService)iinterface;
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
                parcel1.writeString("android.app.job.IJobService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.job.IJobService");
                if(parcel.readInt() != 0)
                    parcel = (JobParameters)JobParameters.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startJob(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.job.IJobService");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (JobParameters)JobParameters.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            stopJob(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.app.job.IJobService";
        static final int TRANSACTION_startJob = 1;
        static final int TRANSACTION_stopJob = 2;

        public Stub()
        {
            attachInterface(this, "android.app.job.IJobService");
        }
    }

    private static class Stub.Proxy
        implements IJobService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.job.IJobService";
        }

        public void startJob(JobParameters jobparameters)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.job.IJobService");
            if(jobparameters == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            jobparameters.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            jobparameters;
            parcel.recycle();
            throw jobparameters;
        }

        public void stopJob(JobParameters jobparameters)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.job.IJobService");
            if(jobparameters == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            jobparameters.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            jobparameters;
            parcel.recycle();
            throw jobparameters;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void startJob(JobParameters jobparameters)
        throws RemoteException;

    public abstract void stopJob(JobParameters jobparameters)
        throws RemoteException;
}
