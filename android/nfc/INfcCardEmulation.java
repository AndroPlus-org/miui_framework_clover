// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc;

import android.content.ComponentName;
import android.nfc.cardemulation.AidGroup;
import android.nfc.cardemulation.ApduServiceInfo;
import android.os.*;
import java.util.List;

public interface INfcCardEmulation
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INfcCardEmulation
    {

        public static INfcCardEmulation asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.nfc.INfcCardEmulation");
            if(iinterface != null && (iinterface instanceof INfcCardEmulation))
                return (INfcCardEmulation)iinterface;
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
            boolean flag8;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.nfc.INfcCardEmulation");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.nfc.INfcCardEmulation");
                i = parcel.readInt();
                ComponentName componentname;
                boolean flag;
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                flag = isDefaultServiceForCategory(i, componentname, parcel.readString());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.nfc.INfcCardEmulation");
                i = parcel.readInt();
                ComponentName componentname1;
                boolean flag1;
                if(parcel.readInt() != 0)
                    componentname1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname1 = null;
                flag1 = isDefaultServiceForAid(i, componentname1, parcel.readString());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.nfc.INfcCardEmulation");
                i = parcel.readInt();
                ComponentName componentname2;
                boolean flag2;
                if(parcel.readInt() != 0)
                    componentname2 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname2 = null;
                flag2 = setDefaultServiceForCategory(i, componentname2, parcel.readString());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.nfc.INfcCardEmulation");
                i = parcel.readInt();
                boolean flag3;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag3 = setDefaultForNextTap(i, parcel);
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.nfc.INfcCardEmulation");
                i = parcel.readInt();
                ComponentName componentname3;
                boolean flag4;
                if(parcel.readInt() != 0)
                    componentname3 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname3 = null;
                if(parcel.readInt() != 0)
                    parcel = (AidGroup)AidGroup.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag4 = registerAidGroupForService(i, componentname3, parcel);
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.nfc.INfcCardEmulation");
                i = parcel.readInt();
                ComponentName componentname4;
                if(parcel.readInt() != 0)
                    componentname4 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname4 = null;
                parcel = getAidGroupForService(i, componentname4, parcel.readString());
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
                parcel.enforceInterface("android.nfc.INfcCardEmulation");
                i = parcel.readInt();
                ComponentName componentname5;
                boolean flag5;
                if(parcel.readInt() != 0)
                    componentname5 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname5 = null;
                flag5 = removeAidGroupForService(i, componentname5, parcel.readString());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.nfc.INfcCardEmulation");
                parcel = getServices(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.nfc.INfcCardEmulation");
                boolean flag6;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag6 = setPreferredService(parcel);
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.nfc.INfcCardEmulation");
                boolean flag7 = unsetPreferredService();
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.nfc.INfcCardEmulation");
                flag8 = supportsAidPrefixRegistration();
                parcel1.writeNoException();
                break;
            }
            if(flag8)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.nfc.INfcCardEmulation";
        static final int TRANSACTION_getAidGroupForService = 6;
        static final int TRANSACTION_getServices = 8;
        static final int TRANSACTION_isDefaultServiceForAid = 2;
        static final int TRANSACTION_isDefaultServiceForCategory = 1;
        static final int TRANSACTION_registerAidGroupForService = 5;
        static final int TRANSACTION_removeAidGroupForService = 7;
        static final int TRANSACTION_setDefaultForNextTap = 4;
        static final int TRANSACTION_setDefaultServiceForCategory = 3;
        static final int TRANSACTION_setPreferredService = 9;
        static final int TRANSACTION_supportsAidPrefixRegistration = 11;
        static final int TRANSACTION_unsetPreferredService = 10;

        public Stub()
        {
            attachInterface(this, "android.nfc.INfcCardEmulation");
        }
    }

    private static class Stub.Proxy
        implements INfcCardEmulation
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public AidGroup getAidGroupForService(int i, ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcCardEmulation");
            parcel.writeInt(i);
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_124;
            componentname = (AidGroup)AidGroup.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public String getInterfaceDescriptor()
        {
            return "android.nfc.INfcCardEmulation";
        }

        public List getServices(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcCardEmulation");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createTypedArrayList(ApduServiceInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isDefaultServiceForAid(int i, ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcCardEmulation");
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

        public boolean isDefaultServiceForCategory(int i, ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcCardEmulation");
            parcel.writeInt(i);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
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

        public boolean registerAidGroupForService(int i, ComponentName componentname, AidGroup aidgroup)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcCardEmulation");
            parcel.writeInt(i);
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(aidgroup == null)
                break MISSING_BLOCK_LABEL_126;
            parcel.writeInt(1);
            aidgroup.writeToParcel(parcel, 0);
_L4:
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
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public boolean removeAidGroupForService(int i, ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcCardEmulation");
            parcel.writeInt(i);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
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
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean setDefaultForNextTap(int i, ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcCardEmulation");
            parcel.writeInt(i);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, parcel1, 0);
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

        public boolean setDefaultServiceForCategory(int i, ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcCardEmulation");
            parcel.writeInt(i);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
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

        public boolean setPreferredService(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.nfc.INfcCardEmulation");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
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
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean supportsAidPrefixRegistration()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.nfc.INfcCardEmulation");
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

        public boolean unsetPreferredService()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.nfc.INfcCardEmulation");
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract AidGroup getAidGroupForService(int i, ComponentName componentname, String s)
        throws RemoteException;

    public abstract List getServices(int i, String s)
        throws RemoteException;

    public abstract boolean isDefaultServiceForAid(int i, ComponentName componentname, String s)
        throws RemoteException;

    public abstract boolean isDefaultServiceForCategory(int i, ComponentName componentname, String s)
        throws RemoteException;

    public abstract boolean registerAidGroupForService(int i, ComponentName componentname, AidGroup aidgroup)
        throws RemoteException;

    public abstract boolean removeAidGroupForService(int i, ComponentName componentname, String s)
        throws RemoteException;

    public abstract boolean setDefaultForNextTap(int i, ComponentName componentname)
        throws RemoteException;

    public abstract boolean setDefaultServiceForCategory(int i, ComponentName componentname, String s)
        throws RemoteException;

    public abstract boolean setPreferredService(ComponentName componentname)
        throws RemoteException;

    public abstract boolean supportsAidPrefixRegistration()
        throws RemoteException;

    public abstract boolean unsetPreferredService()
        throws RemoteException;
}
