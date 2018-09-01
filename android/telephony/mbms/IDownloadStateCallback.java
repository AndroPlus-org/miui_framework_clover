// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.os.*;

// Referenced classes of package android.telephony.mbms:
//            DownloadRequest, FileInfo

public interface IDownloadStateCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IDownloadStateCallback
    {

        public static IDownloadStateCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.telephony.mbms.IDownloadStateCallback");
            if(iinterface != null && (iinterface instanceof IDownloadStateCallback))
                return (IDownloadStateCallback)iinterface;
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
                parcel1.writeString("android.telephony.mbms.IDownloadStateCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.telephony.mbms.IDownloadStateCallback");
                DownloadRequest downloadrequest;
                FileInfo fileinfo;
                if(parcel.readInt() != 0)
                    downloadrequest = (DownloadRequest)DownloadRequest.CREATOR.createFromParcel(parcel);
                else
                    downloadrequest = null;
                if(parcel.readInt() != 0)
                    fileinfo = (FileInfo)FileInfo.CREATOR.createFromParcel(parcel);
                else
                    fileinfo = null;
                onProgressUpdated(downloadrequest, fileinfo, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.telephony.mbms.IDownloadStateCallback");
                break;
            }
            DownloadRequest downloadrequest1;
            FileInfo fileinfo1;
            if(parcel.readInt() != 0)
                downloadrequest1 = (DownloadRequest)DownloadRequest.CREATOR.createFromParcel(parcel);
            else
                downloadrequest1 = null;
            if(parcel.readInt() != 0)
                fileinfo1 = (FileInfo)FileInfo.CREATOR.createFromParcel(parcel);
            else
                fileinfo1 = null;
            onStateUpdated(downloadrequest1, fileinfo1, parcel.readInt());
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.telephony.mbms.IDownloadStateCallback";
        static final int TRANSACTION_onProgressUpdated = 1;
        static final int TRANSACTION_onStateUpdated = 2;

        public Stub()
        {
            attachInterface(this, "android.telephony.mbms.IDownloadStateCallback");
        }
    }

    private static class Stub.Proxy
        implements IDownloadStateCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.telephony.mbms.IDownloadStateCallback";
        }

        public void onProgressUpdated(DownloadRequest downloadrequest, FileInfo fileinfo, int i, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.IDownloadStateCallback");
            if(downloadrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            downloadrequest.writeToParcel(parcel, 0);
_L3:
            if(fileinfo == null)
                break MISSING_BLOCK_LABEL_132;
            parcel.writeInt(1);
            fileinfo.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            downloadrequest;
            parcel1.recycle();
            parcel.recycle();
            throw downloadrequest;
            parcel.writeInt(0);
              goto _L4
        }

        public void onStateUpdated(DownloadRequest downloadrequest, FileInfo fileinfo, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.IDownloadStateCallback");
            if(downloadrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            downloadrequest.writeToParcel(parcel, 0);
_L3:
            if(fileinfo == null)
                break MISSING_BLOCK_LABEL_111;
            parcel.writeInt(1);
            fileinfo.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            downloadrequest;
            parcel1.recycle();
            parcel.recycle();
            throw downloadrequest;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onProgressUpdated(DownloadRequest downloadrequest, FileInfo fileinfo, int i, int j, int k, int l)
        throws RemoteException;

    public abstract void onStateUpdated(DownloadRequest downloadrequest, FileInfo fileinfo, int i)
        throws RemoteException;
}
