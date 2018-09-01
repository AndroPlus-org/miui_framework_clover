// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.os.*;
import com.android.ims.*;

// Referenced classes of package com.android.ims.internal:
//            IImsUt

public interface IImsUtListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IImsUtListener
    {

        public static IImsUtListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.IImsUtListener");
            if(iinterface != null && (iinterface instanceof IImsUtListener))
                return (IImsUtListener)iinterface;
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
                parcel1.writeString("com.android.ims.internal.IImsUtListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.IImsUtListener");
                utConfigurationUpdated(IImsUt.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.ims.internal.IImsUtListener");
                parcel1 = IImsUt.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ImsReasonInfo)ImsReasonInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                utConfigurationUpdateFailed(parcel1, i, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.ims.internal.IImsUtListener");
                parcel1 = IImsUt.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                utConfigurationQueried(parcel1, i, parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.ims.internal.IImsUtListener");
                parcel1 = IImsUt.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ImsReasonInfo)ImsReasonInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                utConfigurationQueryFailed(parcel1, i, parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.ims.internal.IImsUtListener");
                utConfigurationCallBarringQueried(IImsUt.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (ImsSsInfo[])parcel.createTypedArray(ImsSsInfo.CREATOR));
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.ims.internal.IImsUtListener");
                utConfigurationCallForwardQueried(IImsUt.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (ImsCallForwardInfo[])parcel.createTypedArray(ImsCallForwardInfo.CREATOR));
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.ims.internal.IImsUtListener");
                utConfigurationCallWaitingQueried(IImsUt.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (ImsSsInfo[])parcel.createTypedArray(ImsSsInfo.CREATOR));
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.ims.internal.IImsUtListener");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ImsSsData)ImsSsData.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onSupplementaryServiceIndication(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.IImsUtListener";
        static final int TRANSACTION_onSupplementaryServiceIndication = 8;
        static final int TRANSACTION_utConfigurationCallBarringQueried = 5;
        static final int TRANSACTION_utConfigurationCallForwardQueried = 6;
        static final int TRANSACTION_utConfigurationCallWaitingQueried = 7;
        static final int TRANSACTION_utConfigurationQueried = 3;
        static final int TRANSACTION_utConfigurationQueryFailed = 4;
        static final int TRANSACTION_utConfigurationUpdateFailed = 2;
        static final int TRANSACTION_utConfigurationUpdated = 1;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.IImsUtListener");
        }
    }

    private static class Stub.Proxy
        implements IImsUtListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.IImsUtListener";
        }

        public void onSupplementaryServiceIndication(ImsSsData imsssdata)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsUtListener");
            if(imsssdata == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            imsssdata.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            imsssdata;
            parcel.recycle();
            throw imsssdata;
        }

        public void utConfigurationCallBarringQueried(IImsUt iimsut, int i, ImsSsInfo aimsssinfo[])
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsUtListener");
            if(iimsut == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iimsut.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeTypedArray(aimsssinfo, 0);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            iimsut;
            parcel.recycle();
            throw iimsut;
        }

        public void utConfigurationCallForwardQueried(IImsUt iimsut, int i, ImsCallForwardInfo aimscallforwardinfo[])
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsUtListener");
            if(iimsut == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iimsut.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeTypedArray(aimscallforwardinfo, 0);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            iimsut;
            parcel.recycle();
            throw iimsut;
        }

        public void utConfigurationCallWaitingQueried(IImsUt iimsut, int i, ImsSsInfo aimsssinfo[])
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsUtListener");
            if(iimsut == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iimsut.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeTypedArray(aimsssinfo, 0);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            iimsut;
            parcel.recycle();
            throw iimsut;
        }

        public void utConfigurationQueried(IImsUt iimsut, int i, Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsUtListener");
            if(iimsut == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iimsut.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_78;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimsut;
            parcel.recycle();
            throw iimsut;
        }

        public void utConfigurationQueryFailed(IImsUt iimsut, int i, ImsReasonInfo imsreasoninfo)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsUtListener");
            if(iimsut == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iimsut.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(imsreasoninfo == null)
                break MISSING_BLOCK_LABEL_78;
            parcel.writeInt(1);
            imsreasoninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimsut;
            parcel.recycle();
            throw iimsut;
        }

        public void utConfigurationUpdateFailed(IImsUt iimsut, int i, ImsReasonInfo imsreasoninfo)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsUtListener");
            if(iimsut == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iimsut.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(imsreasoninfo == null)
                break MISSING_BLOCK_LABEL_78;
            parcel.writeInt(1);
            imsreasoninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iimsut;
            parcel.recycle();
            throw iimsut;
        }

        public void utConfigurationUpdated(IImsUt iimsut, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsUtListener");
            if(iimsut == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iimsut.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            iimsut;
            parcel.recycle();
            throw iimsut;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onSupplementaryServiceIndication(ImsSsData imsssdata)
        throws RemoteException;

    public abstract void utConfigurationCallBarringQueried(IImsUt iimsut, int i, ImsSsInfo aimsssinfo[])
        throws RemoteException;

    public abstract void utConfigurationCallForwardQueried(IImsUt iimsut, int i, ImsCallForwardInfo aimscallforwardinfo[])
        throws RemoteException;

    public abstract void utConfigurationCallWaitingQueried(IImsUt iimsut, int i, ImsSsInfo aimsssinfo[])
        throws RemoteException;

    public abstract void utConfigurationQueried(IImsUt iimsut, int i, Bundle bundle)
        throws RemoteException;

    public abstract void utConfigurationQueryFailed(IImsUt iimsut, int i, ImsReasonInfo imsreasoninfo)
        throws RemoteException;

    public abstract void utConfigurationUpdateFailed(IImsUt iimsut, int i, ImsReasonInfo imsreasoninfo)
        throws RemoteException;

    public abstract void utConfigurationUpdated(IImsUt iimsut, int i)
        throws RemoteException;
}
