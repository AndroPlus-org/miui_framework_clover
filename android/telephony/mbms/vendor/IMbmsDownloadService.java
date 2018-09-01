// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms.vendor;

import android.os.*;
import android.telephony.mbms.*;
import java.util.List;

public interface IMbmsDownloadService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMbmsDownloadService
    {

        public static IMbmsDownloadService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.telephony.mbms.vendor.IMbmsDownloadService");
            if(iinterface != null && (iinterface instanceof IMbmsDownloadService))
                return (IMbmsDownloadService)iinterface;
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
                parcel1.writeString("android.telephony.mbms.vendor.IMbmsDownloadService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsDownloadService");
                i = initialize(parcel.readInt(), android.telephony.mbms.IMbmsDownloadSessionCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsDownloadService");
                i = requestUpdateFileServices(parcel.readInt(), parcel.createStringArrayList());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsDownloadService");
                i = setTempFileRootDirectory(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsDownloadService");
                if(parcel.readInt() != 0)
                    parcel = (DownloadRequest)DownloadRequest.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = download(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsDownloadService");
                DownloadRequest downloadrequest;
                if(parcel.readInt() != 0)
                    downloadrequest = (DownloadRequest)DownloadRequest.CREATOR.createFromParcel(parcel);
                else
                    downloadrequest = null;
                i = registerStateCallback(downloadrequest, android.telephony.mbms.IDownloadStateCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsDownloadService");
                DownloadRequest downloadrequest1;
                if(parcel.readInt() != 0)
                    downloadrequest1 = (DownloadRequest)DownloadRequest.CREATOR.createFromParcel(parcel);
                else
                    downloadrequest1 = null;
                i = unregisterStateCallback(downloadrequest1, android.telephony.mbms.IDownloadStateCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsDownloadService");
                parcel = listPendingDownloads(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsDownloadService");
                if(parcel.readInt() != 0)
                    parcel = (DownloadRequest)DownloadRequest.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = cancelDownload(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsDownloadService");
                DownloadRequest downloadrequest2;
                if(parcel.readInt() != 0)
                    downloadrequest2 = (DownloadRequest)DownloadRequest.CREATOR.createFromParcel(parcel);
                else
                    downloadrequest2 = null;
                if(parcel.readInt() != 0)
                    parcel = (FileInfo)FileInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getDownloadStatus(downloadrequest2, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsDownloadService");
                if(parcel.readInt() != 0)
                    parcel = (DownloadRequest)DownloadRequest.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = resetDownloadKnowledge(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.telephony.mbms.vendor.IMbmsDownloadService");
                dispose(parcel.readInt());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.telephony.mbms.vendor.IMbmsDownloadService";
        static final int TRANSACTION_cancelDownload = 8;
        static final int TRANSACTION_dispose = 11;
        static final int TRANSACTION_download = 4;
        static final int TRANSACTION_getDownloadStatus = 9;
        static final int TRANSACTION_initialize = 1;
        static final int TRANSACTION_listPendingDownloads = 7;
        static final int TRANSACTION_registerStateCallback = 5;
        static final int TRANSACTION_requestUpdateFileServices = 2;
        static final int TRANSACTION_resetDownloadKnowledge = 10;
        static final int TRANSACTION_setTempFileRootDirectory = 3;
        static final int TRANSACTION_unregisterStateCallback = 6;

        public Stub()
        {
            attachInterface(this, "android.telephony.mbms.vendor.IMbmsDownloadService");
        }
    }

    private static class Stub.Proxy
        implements IMbmsDownloadService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public int cancelDownload(DownloadRequest downloadrequest)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsDownloadService");
            if(downloadrequest == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            downloadrequest.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            downloadrequest;
            parcel1.recycle();
            parcel.recycle();
            throw downloadrequest;
        }

        public void dispose(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsDownloadService");
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
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

        public int download(DownloadRequest downloadrequest)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsDownloadService");
            if(downloadrequest == null)
                break MISSING_BLOCK_LABEL_64;
            parcel.writeInt(1);
            downloadrequest.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            downloadrequest;
            parcel1.recycle();
            parcel.recycle();
            throw downloadrequest;
        }

        public int getDownloadStatus(DownloadRequest downloadrequest, FileInfo fileinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsDownloadService");
            if(downloadrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            downloadrequest.writeToParcel(parcel, 0);
_L3:
            if(fileinfo == null)
                break MISSING_BLOCK_LABEL_105;
            parcel.writeInt(1);
            fileinfo.writeToParcel(parcel, 0);
_L4:
            int i;
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
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

        public String getInterfaceDescriptor()
        {
            return "android.telephony.mbms.vendor.IMbmsDownloadService";
        }

        public int initialize(int i, IMbmsDownloadSessionCallback imbmsdownloadsessioncallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsDownloadService");
            parcel.writeInt(i);
            if(imbmsdownloadsessioncallback == null)
                break MISSING_BLOCK_LABEL_36;
            ibinder = imbmsdownloadsessioncallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            imbmsdownloadsessioncallback;
            parcel1.recycle();
            parcel.recycle();
            throw imbmsdownloadsessioncallback;
        }

        public List listPendingDownloads(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsDownloadService");
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(DownloadRequest.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int registerStateCallback(DownloadRequest downloadrequest, IDownloadStateCallback idownloadstatecallback, int i)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsDownloadService");
            if(downloadrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            downloadrequest.writeToParcel(parcel, 0);
_L4:
            downloadrequest = obj;
            if(idownloadstatecallback == null)
                break MISSING_BLOCK_LABEL_51;
            downloadrequest = idownloadstatecallback.asBinder();
            parcel.writeStrongBinder(downloadrequest);
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            downloadrequest;
            parcel1.recycle();
            parcel.recycle();
            throw downloadrequest;
        }

        public int requestUpdateFileServices(int i, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsDownloadService");
            parcel.writeInt(i);
            parcel.writeStringList(list);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public int resetDownloadKnowledge(DownloadRequest downloadrequest)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsDownloadService");
            if(downloadrequest == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            downloadrequest.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            downloadrequest;
            parcel1.recycle();
            parcel.recycle();
            throw downloadrequest;
        }

        public int setTempFileRootDirectory(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsDownloadService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
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

        public int unregisterStateCallback(DownloadRequest downloadrequest, IDownloadStateCallback idownloadstatecallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.telephony.mbms.vendor.IMbmsDownloadService");
            if(downloadrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            downloadrequest.writeToParcel(parcel, 0);
_L4:
            downloadrequest = obj;
            if(idownloadstatecallback == null)
                break MISSING_BLOCK_LABEL_49;
            downloadrequest = idownloadstatecallback.asBinder();
            int i;
            parcel.writeStrongBinder(downloadrequest);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            downloadrequest;
            parcel1.recycle();
            parcel.recycle();
            throw downloadrequest;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int cancelDownload(DownloadRequest downloadrequest)
        throws RemoteException;

    public abstract void dispose(int i)
        throws RemoteException;

    public abstract int download(DownloadRequest downloadrequest)
        throws RemoteException;

    public abstract int getDownloadStatus(DownloadRequest downloadrequest, FileInfo fileinfo)
        throws RemoteException;

    public abstract int initialize(int i, IMbmsDownloadSessionCallback imbmsdownloadsessioncallback)
        throws RemoteException;

    public abstract List listPendingDownloads(int i)
        throws RemoteException;

    public abstract int registerStateCallback(DownloadRequest downloadrequest, IDownloadStateCallback idownloadstatecallback, int i)
        throws RemoteException;

    public abstract int requestUpdateFileServices(int i, List list)
        throws RemoteException;

    public abstract int resetDownloadKnowledge(DownloadRequest downloadrequest)
        throws RemoteException;

    public abstract int setTempFileRootDirectory(int i, String s)
        throws RemoteException;

    public abstract int unregisterStateCallback(DownloadRequest downloadrequest, IDownloadStateCallback idownloadstatecallback)
        throws RemoteException;
}
