// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.usage;

import android.content.pm.ParceledListSlice;
import android.os.*;

// Referenced classes of package android.app.usage:
//            UsageEvents

public interface IUsageStatsManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IUsageStatsManager
    {

        public static IUsageStatsManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.usage.IUsageStatsManager");
            if(iinterface != null && (iinterface instanceof IUsageStatsManager))
                return (IUsageStatsManager)iinterface;
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
                parcel1.writeString("android.app.usage.IUsageStatsManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.usage.IUsageStatsManager");
                parcel = queryUsageStats(parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readString());
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

            case 2: // '\002'
                parcel.enforceInterface("android.app.usage.IUsageStatsManager");
                parcel = queryConfigurationStats(parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readString());
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

            case 3: // '\003'
                parcel.enforceInterface("android.app.usage.IUsageStatsManager");
                parcel = queryEvents(parcel.readLong(), parcel.readLong(), parcel.readString());
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

            case 4: // '\004'
                parcel.enforceInterface("android.app.usage.IUsageStatsManager");
                String s = parcel.readString();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setAppInactive(s, flag, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.usage.IUsageStatsManager");
                boolean flag1 = isAppInactive(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.app.usage.IUsageStatsManager");
                whitelistAppTemporarily(parcel.readString(), parcel.readLong(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.app.usage.IUsageStatsManager");
                onCarrierPrivilegedAppsChanged();
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.app.usage.IUsageStatsManager");
                reportChooserSelection(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.createStringArray(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.app.usage.IUsageStatsManager");
                parcel = queryUsageStatsAsUser(parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readString(), parcel.readInt());
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

        private static final String DESCRIPTOR = "android.app.usage.IUsageStatsManager";
        static final int TRANSACTION_isAppInactive = 5;
        static final int TRANSACTION_onCarrierPrivilegedAppsChanged = 7;
        static final int TRANSACTION_queryConfigurationStats = 2;
        static final int TRANSACTION_queryEvents = 3;
        static final int TRANSACTION_queryUsageStats = 1;
        static final int TRANSACTION_queryUsageStatsAsUser = 9;
        static final int TRANSACTION_reportChooserSelection = 8;
        static final int TRANSACTION_setAppInactive = 4;
        static final int TRANSACTION_whitelistAppTemporarily = 6;

        public Stub()
        {
            attachInterface(this, "android.app.usage.IUsageStatsManager");
        }
    }

    private static class Stub.Proxy
        implements IUsageStatsManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.usage.IUsageStatsManager";
        }

        public boolean isAppInactive(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.usage.IUsageStatsManager");
            parcel.writeString(s);
            parcel.writeInt(i);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void onCarrierPrivilegedAppsChanged()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.usage.IUsageStatsManager");
            mRemote.transact(7, parcel, parcel1, 0);
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

        public ParceledListSlice queryConfigurationStats(int i, long l, long l1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.usage.IUsageStatsManager");
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeLong(l1);
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public UsageEvents queryEvents(long l, long l1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.usage.IUsageStatsManager");
            parcel.writeLong(l);
            parcel.writeLong(l1);
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (UsageEvents)UsageEvents.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ParceledListSlice queryUsageStats(int i, long l, long l1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.usage.IUsageStatsManager");
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeLong(l1);
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ParceledListSlice queryUsageStatsAsUser(int i, long l, long l1, String s, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.usage.IUsageStatsManager");
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeLong(l1);
            parcel.writeString(s);
            parcel.writeInt(j);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void reportChooserSelection(String s, int i, String s1, String as[], String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.usage.IUsageStatsManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            parcel.writeStringArray(as);
            parcel.writeString(s2);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setAppInactive(String s, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.usage.IUsageStatsManager");
            parcel.writeString(s);
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void whitelistAppTemporarily(String s, long l, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.usage.IUsageStatsManager");
            parcel.writeString(s);
            parcel.writeLong(l);
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean isAppInactive(String s, int i)
        throws RemoteException;

    public abstract void onCarrierPrivilegedAppsChanged()
        throws RemoteException;

    public abstract ParceledListSlice queryConfigurationStats(int i, long l, long l1, String s)
        throws RemoteException;

    public abstract UsageEvents queryEvents(long l, long l1, String s)
        throws RemoteException;

    public abstract ParceledListSlice queryUsageStats(int i, long l, long l1, String s)
        throws RemoteException;

    public abstract ParceledListSlice queryUsageStatsAsUser(int i, long l, long l1, String s, int j)
        throws RemoteException;

    public abstract void reportChooserSelection(String s, int i, String s1, String as[], String s2)
        throws RemoteException;

    public abstract void setAppInactive(String s, boolean flag, int i)
        throws RemoteException;

    public abstract void whitelistAppTemporarily(String s, long l, int i)
        throws RemoteException;
}
