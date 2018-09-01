// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc;

import android.content.ComponentName;
import android.nfc.cardemulation.NfcFServiceInfo;
import android.os.*;
import java.util.List;

public interface INfcFCardEmulation
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INfcFCardEmulation
    {

        public static INfcFCardEmulation asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.nfc.INfcFCardEmulation");
            if(iinterface != null && (iinterface instanceof INfcFCardEmulation))
                return (INfcFCardEmulation)iinterface;
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
            boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            boolean flag4 = false;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.nfc.INfcFCardEmulation");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.nfc.INfcFCardEmulation");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getSystemCodeForService(i, parcel);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.nfc.INfcFCardEmulation");
                i = parcel.readInt();
                ComponentName componentname;
                boolean flag5;
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                flag5 = registerSystemCodeForService(i, componentname, parcel.readString());
                parcel1.writeNoException();
                i = ((flag4) ? 1 : 0);
                if(flag5)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.nfc.INfcFCardEmulation");
                i = parcel.readInt();
                boolean flag6;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag6 = removeSystemCodeForService(i, parcel);
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag6)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.nfc.INfcFCardEmulation");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getNfcid2ForService(i, parcel);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.nfc.INfcFCardEmulation");
                i = parcel.readInt();
                ComponentName componentname1;
                boolean flag7;
                if(parcel.readInt() != 0)
                    componentname1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname1 = null;
                flag7 = setNfcid2ForService(i, componentname1, parcel.readString());
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag7)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.nfc.INfcFCardEmulation");
                boolean flag8;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag8 = enableNfcFForegroundService(parcel);
                parcel1.writeNoException();
                i = ((flag2) ? 1 : 0);
                if(flag8)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.nfc.INfcFCardEmulation");
                boolean flag9 = disableNfcFForegroundService();
                parcel1.writeNoException();
                i = ((flag3) ? 1 : 0);
                if(flag9)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.nfc.INfcFCardEmulation");
                parcel = getNfcFServices(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.nfc.INfcFCardEmulation");
                i = getMaxNumOfRegisterableSystemCodes();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.nfc.INfcFCardEmulation";
        static final int TRANSACTION_disableNfcFForegroundService = 7;
        static final int TRANSACTION_enableNfcFForegroundService = 6;
        static final int TRANSACTION_getMaxNumOfRegisterableSystemCodes = 9;
        static final int TRANSACTION_getNfcFServices = 8;
        static final int TRANSACTION_getNfcid2ForService = 4;
        static final int TRANSACTION_getSystemCodeForService = 1;
        static final int TRANSACTION_registerSystemCodeForService = 2;
        static final int TRANSACTION_removeSystemCodeForService = 3;
        static final int TRANSACTION_setNfcid2ForService = 5;

        public Stub()
        {
            attachInterface(this, "android.nfc.INfcFCardEmulation");
        }
    }

    private static class Stub.Proxy
        implements INfcFCardEmulation
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean disableNfcFForegroundService()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.nfc.INfcFCardEmulation");
            mRemote.transact(7, parcel, parcel1, 0);
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

        public boolean enableNfcFForegroundService(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcFCardEmulation");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(6, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public String getInterfaceDescriptor()
        {
            return "android.nfc.INfcFCardEmulation";
        }

        public int getMaxNumOfRegisterableSystemCodes()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.nfc.INfcFCardEmulation");
            mRemote.transact(9, parcel, parcel1, 0);
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

        public List getNfcFServices(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.nfc.INfcFCardEmulation");
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(NfcFServiceInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getNfcid2ForService(int i, ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcFCardEmulation");
            parcel.writeInt(i);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public String getSystemCodeForService(int i, ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcFCardEmulation");
            parcel.writeInt(i);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean registerSystemCodeForService(int i, ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcFCardEmulation");
            parcel.writeInt(i);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
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
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean removeSystemCodeForService(int i, ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcFCardEmulation");
            parcel.writeInt(i);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean setNfcid2ForService(int i, ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcFCardEmulation");
            parcel.writeInt(i);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean disableNfcFForegroundService()
        throws RemoteException;

    public abstract boolean enableNfcFForegroundService(ComponentName componentname)
        throws RemoteException;

    public abstract int getMaxNumOfRegisterableSystemCodes()
        throws RemoteException;

    public abstract List getNfcFServices(int i)
        throws RemoteException;

    public abstract String getNfcid2ForService(int i, ComponentName componentname)
        throws RemoteException;

    public abstract String getSystemCodeForService(int i, ComponentName componentname)
        throws RemoteException;

    public abstract boolean registerSystemCodeForService(int i, ComponentName componentname, String s)
        throws RemoteException;

    public abstract boolean removeSystemCodeForService(int i, ComponentName componentname)
        throws RemoteException;

    public abstract boolean setNfcid2ForService(int i, ComponentName componentname, String s)
        throws RemoteException;
}
