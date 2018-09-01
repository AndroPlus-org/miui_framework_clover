// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.carrier;

import android.net.Uri;
import android.os.*;
import java.util.List;

// Referenced classes of package android.service.carrier:
//            ICarrierMessagingCallback, MessagePdu

public interface ICarrierMessagingService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICarrierMessagingService
    {

        public static ICarrierMessagingService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.carrier.ICarrierMessagingService");
            if(iinterface != null && (iinterface instanceof ICarrierMessagingService))
                return (ICarrierMessagingService)iinterface;
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
                parcel1.writeString("android.service.carrier.ICarrierMessagingService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.carrier.ICarrierMessagingService");
                if(parcel.readInt() != 0)
                    parcel1 = (MessagePdu)MessagePdu.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                filterSms(parcel1, parcel.readString(), parcel.readInt(), parcel.readInt(), ICarrierMessagingCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.carrier.ICarrierMessagingService");
                sendTextSms(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt(), ICarrierMessagingCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.carrier.ICarrierMessagingService");
                sendDataSms(parcel.createByteArray(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), ICarrierMessagingCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.carrier.ICarrierMessagingService");
                sendMultipartTextSms(parcel.createStringArrayList(), parcel.readInt(), parcel.readString(), parcel.readInt(), ICarrierMessagingCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.carrier.ICarrierMessagingService");
                Uri uri;
                if(parcel.readInt() != 0)
                    parcel1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri = null;
                sendMms(parcel1, i, uri, ICarrierMessagingCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.service.carrier.ICarrierMessagingService");
                break;
            }
            Uri uri1;
            if(parcel.readInt() != 0)
                parcel1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            i = parcel.readInt();
            if(parcel.readInt() != 0)
                uri1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
            else
                uri1 = null;
            downloadMms(parcel1, i, uri1, ICarrierMessagingCallback.Stub.asInterface(parcel.readStrongBinder()));
            return true;
        }

        private static final String DESCRIPTOR = "android.service.carrier.ICarrierMessagingService";
        static final int TRANSACTION_downloadMms = 6;
        static final int TRANSACTION_filterSms = 1;
        static final int TRANSACTION_sendDataSms = 3;
        static final int TRANSACTION_sendMms = 5;
        static final int TRANSACTION_sendMultipartTextSms = 4;
        static final int TRANSACTION_sendTextSms = 2;

        public Stub()
        {
            attachInterface(this, "android.service.carrier.ICarrierMessagingService");
        }
    }

    private static class Stub.Proxy
        implements ICarrierMessagingService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void downloadMms(Uri uri, int i, Uri uri1, ICarrierMessagingCallback icarriermessagingcallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.carrier.ICarrierMessagingService");
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            if(uri1 == null)
                break MISSING_BLOCK_LABEL_116;
            parcel.writeInt(1);
            uri1.writeToParcel(parcel, 0);
_L4:
            uri = obj;
            if(icarriermessagingcallback == null)
                break MISSING_BLOCK_LABEL_71;
            uri = icarriermessagingcallback.asBinder();
            parcel.writeStrongBinder(uri);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            uri;
            parcel.recycle();
            throw uri;
            parcel.writeInt(0);
              goto _L4
        }

        public void filterSms(MessagePdu messagepdu, String s, int i, int j, ICarrierMessagingCallback icarriermessagingcallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.carrier.ICarrierMessagingService");
            if(messagepdu == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            messagepdu.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            messagepdu = obj;
            if(icarriermessagingcallback == null)
                break MISSING_BLOCK_LABEL_67;
            messagepdu = icarriermessagingcallback.asBinder();
            parcel.writeStrongBinder(messagepdu);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            messagepdu;
            parcel.recycle();
            throw messagepdu;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.carrier.ICarrierMessagingService";
        }

        public void sendDataSms(byte abyte0[], int i, String s, int j, int k, ICarrierMessagingCallback icarriermessagingcallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.carrier.ICarrierMessagingService");
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeInt(k);
            abyte0 = obj;
            if(icarriermessagingcallback == null)
                break MISSING_BLOCK_LABEL_63;
            abyte0 = icarriermessagingcallback.asBinder();
            parcel.writeStrongBinder(abyte0);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            abyte0;
            parcel.recycle();
            throw abyte0;
        }

        public void sendMms(Uri uri, int i, Uri uri1, ICarrierMessagingCallback icarriermessagingcallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.carrier.ICarrierMessagingService");
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            if(uri1 == null)
                break MISSING_BLOCK_LABEL_115;
            parcel.writeInt(1);
            uri1.writeToParcel(parcel, 0);
_L4:
            uri = obj;
            if(icarriermessagingcallback == null)
                break MISSING_BLOCK_LABEL_71;
            uri = icarriermessagingcallback.asBinder();
            parcel.writeStrongBinder(uri);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            uri;
            parcel.recycle();
            throw uri;
            parcel.writeInt(0);
              goto _L4
        }

        public void sendMultipartTextSms(List list, int i, String s, int j, ICarrierMessagingCallback icarriermessagingcallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.carrier.ICarrierMessagingService");
            parcel.writeStringList(list);
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            list = obj;
            if(icarriermessagingcallback == null)
                break MISSING_BLOCK_LABEL_56;
            list = icarriermessagingcallback.asBinder();
            parcel.writeStrongBinder(list);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void sendTextSms(String s, int i, String s1, int j, ICarrierMessagingCallback icarriermessagingcallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.carrier.ICarrierMessagingService");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            parcel.writeInt(j);
            s = obj;
            if(icarriermessagingcallback == null)
                break MISSING_BLOCK_LABEL_56;
            s = icarriermessagingcallback.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
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


    public abstract void downloadMms(Uri uri, int i, Uri uri1, ICarrierMessagingCallback icarriermessagingcallback)
        throws RemoteException;

    public abstract void filterSms(MessagePdu messagepdu, String s, int i, int j, ICarrierMessagingCallback icarriermessagingcallback)
        throws RemoteException;

    public abstract void sendDataSms(byte abyte0[], int i, String s, int j, int k, ICarrierMessagingCallback icarriermessagingcallback)
        throws RemoteException;

    public abstract void sendMms(Uri uri, int i, Uri uri1, ICarrierMessagingCallback icarriermessagingcallback)
        throws RemoteException;

    public abstract void sendMultipartTextSms(List list, int i, String s, int j, ICarrierMessagingCallback icarriermessagingcallback)
        throws RemoteException;

    public abstract void sendTextSms(String s, int i, String s1, int j, ICarrierMessagingCallback icarriermessagingcallback)
        throws RemoteException;
}
