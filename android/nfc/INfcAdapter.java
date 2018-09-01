// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc;

import android.app.PendingIntent;
import android.content.IntentFilter;
import android.os.*;

// Referenced classes of package android.nfc:
//            INfcUnlockHandler, Tag, INfcAdapterExtras, INfcCardEmulation, 
//            INfcDta, INfcFCardEmulation, INfcTag, ITagRemovedCallback, 
//            BeamShareData, IAppCallback, TechListParcel

public interface INfcAdapter
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INfcAdapter
    {

        public static INfcAdapter asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.nfc.INfcAdapter");
            if(iinterface != null && (iinterface instanceof INfcAdapter))
                return (INfcAdapter)iinterface;
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
                parcel1.writeString("android.nfc.INfcAdapter");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                parcel = getNfcTagInterface();
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                parcel = getNfcCardEmulationInterface();
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                parcel = getNfcFCardEmulationInterface();
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                parcel = getNfcAdapterExtrasInterface(parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                parcel = getNfcAdapterVendorInterface(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeStrongBinder(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                parcel = getNfcDtaInterface(parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                i = getState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                flag = disable(flag);
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                boolean flag1 = enable();
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                boolean flag2 = enableNdefPush();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                boolean flag3 = disableNdefPush();
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                boolean flag4 = isNdefPushEnabled();
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                pausePolling(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                resumePolling();
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                PendingIntent pendingintent;
                IntentFilter aintentfilter[];
                if(parcel.readInt() != 0)
                    pendingintent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent = null;
                aintentfilter = (IntentFilter[])parcel.createTypedArray(IntentFilter.CREATOR);
                if(parcel.readInt() != 0)
                    parcel = (TechListParcel)TechListParcel.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setForegroundDispatch(pendingintent, aintentfilter, parcel);
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                setAppCallback(IAppCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                invokeBeam();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                if(parcel.readInt() != 0)
                    parcel = (BeamShareData)BeamShareData.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                invokeBeamInternal(parcel);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                boolean flag5 = ignore(parcel.readInt(), parcel.readInt(), ITagRemovedCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                if(parcel.readInt() != 0)
                    parcel = (Tag)Tag.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                dispatch(parcel);
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                IBinder ibinder = parcel.readStrongBinder();
                IAppCallback iappcallback = IAppCallback.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setReaderMode(ibinder, iappcallback, i, parcel);
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                setP2pModes(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                addNfcUnlockHandler(INfcUnlockHandler.Stub.asInterface(parcel.readStrongBinder()), parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                removeNfcUnlockHandler(INfcUnlockHandler.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.nfc.INfcAdapter");
                verifyNfcPermission();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.nfc.INfcAdapter";
        static final int TRANSACTION_addNfcUnlockHandler = 23;
        static final int TRANSACTION_disable = 8;
        static final int TRANSACTION_disableNdefPush = 11;
        static final int TRANSACTION_dispatch = 20;
        static final int TRANSACTION_enable = 9;
        static final int TRANSACTION_enableNdefPush = 10;
        static final int TRANSACTION_getNfcAdapterExtrasInterface = 4;
        static final int TRANSACTION_getNfcAdapterVendorInterface = 5;
        static final int TRANSACTION_getNfcCardEmulationInterface = 2;
        static final int TRANSACTION_getNfcDtaInterface = 6;
        static final int TRANSACTION_getNfcFCardEmulationInterface = 3;
        static final int TRANSACTION_getNfcTagInterface = 1;
        static final int TRANSACTION_getState = 7;
        static final int TRANSACTION_ignore = 19;
        static final int TRANSACTION_invokeBeam = 17;
        static final int TRANSACTION_invokeBeamInternal = 18;
        static final int TRANSACTION_isNdefPushEnabled = 12;
        static final int TRANSACTION_pausePolling = 13;
        static final int TRANSACTION_removeNfcUnlockHandler = 24;
        static final int TRANSACTION_resumePolling = 14;
        static final int TRANSACTION_setAppCallback = 16;
        static final int TRANSACTION_setForegroundDispatch = 15;
        static final int TRANSACTION_setP2pModes = 22;
        static final int TRANSACTION_setReaderMode = 21;
        static final int TRANSACTION_verifyNfcPermission = 25;

        public Stub()
        {
            attachInterface(this, "android.nfc.INfcAdapter");
        }
    }

    private static class Stub.Proxy
        implements INfcAdapter
    {

        public void addNfcUnlockHandler(INfcUnlockHandler infcunlockhandler, int ai[])
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            if(infcunlockhandler == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = infcunlockhandler.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeIntArray(ai);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            infcunlockhandler;
            parcel1.recycle();
            parcel.recycle();
            throw infcunlockhandler;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean disable(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
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

        public boolean disableNdefPush()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            mRemote.transact(11, parcel, parcel1, 0);
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

        public void dispatch(Tag tag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            if(tag == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            tag.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            tag;
            parcel1.recycle();
            parcel.recycle();
            throw tag;
        }

        public boolean enable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            mRemote.transact(9, parcel, parcel1, 0);
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

        public boolean enableNdefPush()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            mRemote.transact(10, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "android.nfc.INfcAdapter";
        }

        public INfcAdapterExtras getNfcAdapterExtrasInterface(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            parcel.writeString(s);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            s = INfcAdapterExtras.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IBinder getNfcAdapterVendorInterface(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readStrongBinder();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public INfcCardEmulation getNfcCardEmulationInterface()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            INfcCardEmulation infccardemulation;
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            infccardemulation = INfcCardEmulation.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return infccardemulation;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public INfcDta getNfcDtaInterface(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            s = INfcDta.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public INfcFCardEmulation getNfcFCardEmulationInterface()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            INfcFCardEmulation infcfcardemulation;
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            infcfcardemulation = INfcFCardEmulation.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return infcfcardemulation;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public INfcTag getNfcTagInterface()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            INfcTag infctag;
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            infctag = INfcTag.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return infctag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean ignore(int i, int j, ITagRemovedCallback itagremovedcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(itagremovedcallback == null)
                break MISSING_BLOCK_LABEL_44;
            ibinder = itagremovedcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(19, parcel, parcel1, 0);
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
            itagremovedcallback;
            parcel1.recycle();
            parcel.recycle();
            throw itagremovedcallback;
        }

        public void invokeBeam()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            mRemote.transact(17, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void invokeBeamInternal(BeamShareData beamsharedata)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            if(beamsharedata == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            beamsharedata.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(18, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            beamsharedata;
            parcel.recycle();
            throw beamsharedata;
        }

        public boolean isNdefPushEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            mRemote.transact(12, parcel, parcel1, 0);
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

        public void pausePolling(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
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

        public void removeNfcUnlockHandler(INfcUnlockHandler infcunlockhandler)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            if(infcunlockhandler == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = infcunlockhandler.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            infcunlockhandler;
            parcel1.recycle();
            parcel.recycle();
            throw infcunlockhandler;
        }

        public void resumePolling()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            mRemote.transact(14, parcel, parcel1, 0);
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

        public void setAppCallback(IAppCallback iappcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            if(iappcallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iappcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iappcallback;
            parcel1.recycle();
            parcel.recycle();
            throw iappcallback;
        }

        public void setForegroundDispatch(PendingIntent pendingintent, IntentFilter aintentfilter[], TechListParcel techlistparcel)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            if(pendingintent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L3:
            parcel.writeTypedArray(aintentfilter, 0);
            if(techlistparcel == null)
                break MISSING_BLOCK_LABEL_113;
            parcel.writeInt(1);
            techlistparcel.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            pendingintent;
            parcel1.recycle();
            parcel.recycle();
            throw pendingintent;
            parcel.writeInt(0);
              goto _L4
        }

        public void setP2pModes(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(22, parcel, parcel1, 0);
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

        public void setReaderMode(IBinder ibinder, IAppCallback iappcallback, int i, Bundle bundle)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            parcel.writeStrongBinder(ibinder);
            ibinder = obj;
            if(iappcallback == null)
                break MISSING_BLOCK_LABEL_40;
            ibinder = iappcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_104;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void verifyNfcPermission()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcAdapter");
            mRemote.transact(25, parcel, parcel1, 0);
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


    public abstract void addNfcUnlockHandler(INfcUnlockHandler infcunlockhandler, int ai[])
        throws RemoteException;

    public abstract boolean disable(boolean flag)
        throws RemoteException;

    public abstract boolean disableNdefPush()
        throws RemoteException;

    public abstract void dispatch(Tag tag)
        throws RemoteException;

    public abstract boolean enable()
        throws RemoteException;

    public abstract boolean enableNdefPush()
        throws RemoteException;

    public abstract INfcAdapterExtras getNfcAdapterExtrasInterface(String s)
        throws RemoteException;

    public abstract IBinder getNfcAdapterVendorInterface(String s)
        throws RemoteException;

    public abstract INfcCardEmulation getNfcCardEmulationInterface()
        throws RemoteException;

    public abstract INfcDta getNfcDtaInterface(String s)
        throws RemoteException;

    public abstract INfcFCardEmulation getNfcFCardEmulationInterface()
        throws RemoteException;

    public abstract INfcTag getNfcTagInterface()
        throws RemoteException;

    public abstract int getState()
        throws RemoteException;

    public abstract boolean ignore(int i, int j, ITagRemovedCallback itagremovedcallback)
        throws RemoteException;

    public abstract void invokeBeam()
        throws RemoteException;

    public abstract void invokeBeamInternal(BeamShareData beamsharedata)
        throws RemoteException;

    public abstract boolean isNdefPushEnabled()
        throws RemoteException;

    public abstract void pausePolling(int i)
        throws RemoteException;

    public abstract void removeNfcUnlockHandler(INfcUnlockHandler infcunlockhandler)
        throws RemoteException;

    public abstract void resumePolling()
        throws RemoteException;

    public abstract void setAppCallback(IAppCallback iappcallback)
        throws RemoteException;

    public abstract void setForegroundDispatch(PendingIntent pendingintent, IntentFilter aintentfilter[], TechListParcel techlistparcel)
        throws RemoteException;

    public abstract void setP2pModes(int i, int j)
        throws RemoteException;

    public abstract void setReaderMode(IBinder ibinder, IAppCallback iappcallback, int i, Bundle bundle)
        throws RemoteException;

    public abstract void verifyNfcPermission()
        throws RemoteException;
}
