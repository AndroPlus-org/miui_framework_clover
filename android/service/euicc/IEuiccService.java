// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.euicc;

import android.os.*;
import android.telephony.euicc.DownloadableSubscription;

// Referenced classes of package android.service.euicc:
//            IDeleteSubscriptionCallback, IDownloadSubscriptionCallback, IEraseSubscriptionsCallback, IGetDefaultDownloadableSubscriptionListCallback, 
//            IGetDownloadableSubscriptionMetadataCallback, IGetEidCallback, IGetEuiccInfoCallback, IGetEuiccProfileInfoListCallback, 
//            IRetainSubscriptionsForFactoryResetCallback, ISwitchToSubscriptionCallback, IUpdateSubscriptionNicknameCallback

public interface IEuiccService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IEuiccService
    {

        public static IEuiccService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.euicc.IEuiccService");
            if(iinterface != null && (iinterface instanceof IEuiccService))
                return (IEuiccService)iinterface;
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
                parcel1.writeString("android.service.euicc.IEuiccService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.euicc.IEuiccService");
                i = parcel.readInt();
                boolean flag;
                boolean flag4;
                if(parcel.readInt() != 0)
                    parcel1 = (DownloadableSubscription)DownloadableSubscription.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                downloadSubscription(i, parcel1, flag, flag4, IDownloadSubscriptionCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.euicc.IEuiccService");
                i = parcel.readInt();
                boolean flag1;
                if(parcel.readInt() != 0)
                    parcel1 = (DownloadableSubscription)DownloadableSubscription.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                getDownloadableSubscriptionMetadata(i, parcel1, flag1, IGetDownloadableSubscriptionMetadataCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.euicc.IEuiccService");
                getEid(parcel.readInt(), IGetEidCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.euicc.IEuiccService");
                getEuiccProfileInfoList(parcel.readInt(), IGetEuiccProfileInfoListCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.euicc.IEuiccService");
                i = parcel.readInt();
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                getDefaultDownloadableSubscriptionList(i, flag2, IGetDefaultDownloadableSubscriptionListCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.service.euicc.IEuiccService");
                getEuiccInfo(parcel.readInt(), IGetEuiccInfoCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.service.euicc.IEuiccService");
                deleteSubscription(parcel.readInt(), parcel.readString(), IDeleteSubscriptionCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.service.euicc.IEuiccService");
                i = parcel.readInt();
                parcel1 = parcel.readString();
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                switchToSubscription(i, parcel1, flag3, ISwitchToSubscriptionCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.service.euicc.IEuiccService");
                updateSubscriptionNickname(parcel.readInt(), parcel.readString(), parcel.readString(), IUpdateSubscriptionNicknameCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.service.euicc.IEuiccService");
                eraseSubscriptions(parcel.readInt(), IEraseSubscriptionsCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.service.euicc.IEuiccService");
                retainSubscriptionsForFactoryReset(parcel.readInt(), IRetainSubscriptionsForFactoryResetCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.euicc.IEuiccService";
        static final int TRANSACTION_deleteSubscription = 7;
        static final int TRANSACTION_downloadSubscription = 1;
        static final int TRANSACTION_eraseSubscriptions = 10;
        static final int TRANSACTION_getDefaultDownloadableSubscriptionList = 5;
        static final int TRANSACTION_getDownloadableSubscriptionMetadata = 2;
        static final int TRANSACTION_getEid = 3;
        static final int TRANSACTION_getEuiccInfo = 6;
        static final int TRANSACTION_getEuiccProfileInfoList = 4;
        static final int TRANSACTION_retainSubscriptionsForFactoryReset = 11;
        static final int TRANSACTION_switchToSubscription = 8;
        static final int TRANSACTION_updateSubscriptionNickname = 9;

        public Stub()
        {
            attachInterface(this, "android.service.euicc.IEuiccService");
        }
    }

    private static class Stub.Proxy
        implements IEuiccService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void deleteSubscription(int i, String s, IDeleteSubscriptionCallback ideletesubscriptioncallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IEuiccService");
            parcel.writeInt(i);
            parcel.writeString(s);
            s = obj;
            if(ideletesubscriptioncallback == null)
                break MISSING_BLOCK_LABEL_41;
            s = ideletesubscriptioncallback.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void downloadSubscription(int i, DownloadableSubscription downloadablesubscription, boolean flag, boolean flag1, IDownloadSubscriptionCallback idownloadsubscriptioncallback)
            throws RemoteException
        {
            Object obj;
            boolean flag2;
            Parcel parcel;
            obj = null;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IEuiccService");
            parcel.writeInt(i);
            if(downloadablesubscription == null)
                break MISSING_BLOCK_LABEL_110;
            parcel.writeInt(1);
            downloadablesubscription.writeToParcel(parcel, 0);
_L1:
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
            downloadablesubscription = obj;
            if(idownloadsubscriptioncallback == null)
                break MISSING_BLOCK_LABEL_83;
            downloadablesubscription = idownloadsubscriptioncallback.asBinder();
            parcel.writeStrongBinder(downloadablesubscription);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            downloadablesubscription;
            parcel.recycle();
            throw downloadablesubscription;
        }

        public void eraseSubscriptions(int i, IEraseSubscriptionsCallback ierasesubscriptionscallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IEuiccService");
            parcel.writeInt(i);
            if(ierasesubscriptionscallback == null)
                break MISSING_BLOCK_LABEL_31;
            ibinder = ierasesubscriptionscallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            ierasesubscriptionscallback;
            parcel.recycle();
            throw ierasesubscriptionscallback;
        }

        public void getDefaultDownloadableSubscriptionList(int i, boolean flag, IGetDefaultDownloadableSubscriptionListCallback igetdefaultdownloadablesubscriptionlistcallback)
            throws RemoteException
        {
            boolean flag1;
            IBinder ibinder;
            Parcel parcel;
            flag1 = true;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IEuiccService");
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            if(igetdefaultdownloadablesubscriptionlistcallback == null)
                break MISSING_BLOCK_LABEL_49;
            ibinder = igetdefaultdownloadablesubscriptionlistcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            igetdefaultdownloadablesubscriptionlistcallback;
            parcel.recycle();
            throw igetdefaultdownloadablesubscriptionlistcallback;
        }

        public void getDownloadableSubscriptionMetadata(int i, DownloadableSubscription downloadablesubscription, boolean flag, IGetDownloadableSubscriptionMetadataCallback igetdownloadablesubscriptionmetadatacallback)
            throws RemoteException
        {
            boolean flag1;
            Object obj;
            Parcel parcel;
            flag1 = true;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IEuiccService");
            parcel.writeInt(i);
            if(downloadablesubscription == null)
                break MISSING_BLOCK_LABEL_97;
            parcel.writeInt(1);
            downloadablesubscription.writeToParcel(parcel, 0);
_L1:
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            downloadablesubscription = obj;
            if(igetdownloadablesubscriptionmetadatacallback == null)
                break MISSING_BLOCK_LABEL_70;
            downloadablesubscription = igetdownloadablesubscriptionmetadatacallback.asBinder();
            parcel.writeStrongBinder(downloadablesubscription);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            downloadablesubscription;
            parcel.recycle();
            throw downloadablesubscription;
        }

        public void getEid(int i, IGetEidCallback igeteidcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IEuiccService");
            parcel.writeInt(i);
            if(igeteidcallback == null)
                break MISSING_BLOCK_LABEL_31;
            ibinder = igeteidcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            igeteidcallback;
            parcel.recycle();
            throw igeteidcallback;
        }

        public void getEuiccInfo(int i, IGetEuiccInfoCallback igeteuiccinfocallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IEuiccService");
            parcel.writeInt(i);
            if(igeteuiccinfocallback == null)
                break MISSING_BLOCK_LABEL_31;
            ibinder = igeteuiccinfocallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            igeteuiccinfocallback;
            parcel.recycle();
            throw igeteuiccinfocallback;
        }

        public void getEuiccProfileInfoList(int i, IGetEuiccProfileInfoListCallback igeteuiccprofileinfolistcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IEuiccService");
            parcel.writeInt(i);
            if(igeteuiccprofileinfolistcallback == null)
                break MISSING_BLOCK_LABEL_31;
            ibinder = igeteuiccprofileinfolistcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            igeteuiccprofileinfolistcallback;
            parcel.recycle();
            throw igeteuiccprofileinfolistcallback;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.euicc.IEuiccService";
        }

        public void retainSubscriptionsForFactoryReset(int i, IRetainSubscriptionsForFactoryResetCallback iretainsubscriptionsforfactoryresetcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IEuiccService");
            parcel.writeInt(i);
            if(iretainsubscriptionsforfactoryresetcallback == null)
                break MISSING_BLOCK_LABEL_31;
            ibinder = iretainsubscriptionsforfactoryresetcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            iretainsubscriptionsforfactoryresetcallback;
            parcel.recycle();
            throw iretainsubscriptionsforfactoryresetcallback;
        }

        public void switchToSubscription(int i, String s, boolean flag, ISwitchToSubscriptionCallback iswitchtosubscriptioncallback)
            throws RemoteException
        {
            boolean flag1;
            Object obj;
            Parcel parcel;
            flag1 = true;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IEuiccService");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            s = obj;
            if(iswitchtosubscriptioncallback == null)
                break MISSING_BLOCK_LABEL_59;
            s = iswitchtosubscriptioncallback.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void updateSubscriptionNickname(int i, String s, String s1, IUpdateSubscriptionNicknameCallback iupdatesubscriptionnicknamecallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IEuiccService");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            s = obj;
            if(iupdatesubscriptionnicknamecallback == null)
                break MISSING_BLOCK_LABEL_49;
            s = iupdatesubscriptionnicknamecallback.asBinder();
            parcel.writeStrongBinder(s);
            mRemote.transact(9, parcel, null, 1);
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


    public abstract void deleteSubscription(int i, String s, IDeleteSubscriptionCallback ideletesubscriptioncallback)
        throws RemoteException;

    public abstract void downloadSubscription(int i, DownloadableSubscription downloadablesubscription, boolean flag, boolean flag1, IDownloadSubscriptionCallback idownloadsubscriptioncallback)
        throws RemoteException;

    public abstract void eraseSubscriptions(int i, IEraseSubscriptionsCallback ierasesubscriptionscallback)
        throws RemoteException;

    public abstract void getDefaultDownloadableSubscriptionList(int i, boolean flag, IGetDefaultDownloadableSubscriptionListCallback igetdefaultdownloadablesubscriptionlistcallback)
        throws RemoteException;

    public abstract void getDownloadableSubscriptionMetadata(int i, DownloadableSubscription downloadablesubscription, boolean flag, IGetDownloadableSubscriptionMetadataCallback igetdownloadablesubscriptionmetadatacallback)
        throws RemoteException;

    public abstract void getEid(int i, IGetEidCallback igeteidcallback)
        throws RemoteException;

    public abstract void getEuiccInfo(int i, IGetEuiccInfoCallback igeteuiccinfocallback)
        throws RemoteException;

    public abstract void getEuiccProfileInfoList(int i, IGetEuiccProfileInfoListCallback igeteuiccprofileinfolistcallback)
        throws RemoteException;

    public abstract void retainSubscriptionsForFactoryReset(int i, IRetainSubscriptionsForFactoryResetCallback iretainsubscriptionsforfactoryresetcallback)
        throws RemoteException;

    public abstract void switchToSubscription(int i, String s, boolean flag, ISwitchToSubscriptionCallback iswitchtosubscriptioncallback)
        throws RemoteException;

    public abstract void updateSubscriptionNickname(int i, String s, String s1, IUpdateSubscriptionNicknameCallback iupdatesubscriptionnicknamecallback)
        throws RemoteException;
}
