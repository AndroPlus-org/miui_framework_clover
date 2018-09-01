// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mqsas;

import android.content.pm.ParceledListSlice;
import android.os.*;
import miui.mqsas.sdk.event.AnrEvent;
import miui.mqsas.sdk.event.BootEvent;
import miui.mqsas.sdk.event.JavaExceptionEvent;
import miui.mqsas.sdk.event.PackageEvent;
import miui.mqsas.sdk.event.ScreenOnEvent;
import miui.mqsas.sdk.event.WatchdogEvent;

public interface IMQSService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMQSService
    {

        public static IMQSService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.mqsas.IMQSService");
            if(iinterface != null && (iinterface instanceof IMQSService))
                return (IMQSService)iinterface;
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
                parcel1.writeString("miui.mqsas.IMQSService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                reportSimpleEvent(parcel.readInt(), parcel.readString());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                if(parcel.readInt() != 0)
                    parcel = (AnrEvent)AnrEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                reportAnrEvent(parcel);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                if(parcel.readInt() != 0)
                    parcel = (JavaExceptionEvent)JavaExceptionEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                reportJavaExceptionEvent(parcel);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                if(parcel.readInt() != 0)
                    parcel = (WatchdogEvent)WatchdogEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                reportWatchdogEvent(parcel);
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                if(parcel.readInt() != 0)
                    parcel = (ScreenOnEvent)ScreenOnEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                reportScreenOnEvent(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                if(parcel.readInt() != 0)
                    parcel = (PackageEvent)PackageEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                reportPackageEvent(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                onBootCompleted();
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                if(parcel.readInt() != 0)
                    parcel = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                reportBroadcastEvent(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                if(parcel.readInt() != 0)
                    parcel = (BootEvent)BootEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                reportBootEvent(parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                reportTelephonyEvent(parcel.readInt(), parcel.readString());
                return true;

            case 11: // '\013'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                reportConnectExceptionEvent(parcel.readInt(), parcel.readInt());
                return true;

            case 12: // '\f'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                if(parcel.readInt() != 0)
                    parcel = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                reportKillProcessEvents(parcel);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                reportBluetoothEvent(parcel.readInt(), parcel.readString());
                return true;

            case 14: // '\016'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                parcel1 = parcel.readString();
                String s = parcel.readString();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                reportEvent(parcel1, s, flag);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                String s1 = parcel.readString();
                boolean flag1;
                if(parcel.readInt() != 0)
                    parcel1 = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                reportEvents(s1, parcel1, flag1);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                parcel = getOnlineRuleMatched(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                if(parcel.readInt() != 0)
                    parcel = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                reportPackageForegroundEvents(parcel);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                dumpBugReport();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("miui.mqsas.IMQSService");
                i = parcel.readInt();
                j = parcel.readInt();
                parcel1 = parcel.readString();
                break;
            }
            boolean flag2;
            if(parcel.readInt() != 0)
                flag2 = true;
            else
                flag2 = false;
            dialogButtonChecked(i, j, parcel1, flag2);
            return true;
        }

        private static final String DESCRIPTOR = "miui.mqsas.IMQSService";
        static final int TRANSACTION_dialogButtonChecked = 19;
        static final int TRANSACTION_dumpBugReport = 18;
        static final int TRANSACTION_getOnlineRuleMatched = 16;
        static final int TRANSACTION_onBootCompleted = 7;
        static final int TRANSACTION_reportAnrEvent = 2;
        static final int TRANSACTION_reportBluetoothEvent = 13;
        static final int TRANSACTION_reportBootEvent = 9;
        static final int TRANSACTION_reportBroadcastEvent = 8;
        static final int TRANSACTION_reportConnectExceptionEvent = 11;
        static final int TRANSACTION_reportEvent = 14;
        static final int TRANSACTION_reportEvents = 15;
        static final int TRANSACTION_reportJavaExceptionEvent = 3;
        static final int TRANSACTION_reportKillProcessEvents = 12;
        static final int TRANSACTION_reportPackageEvent = 6;
        static final int TRANSACTION_reportPackageForegroundEvents = 17;
        static final int TRANSACTION_reportScreenOnEvent = 5;
        static final int TRANSACTION_reportSimpleEvent = 1;
        static final int TRANSACTION_reportTelephonyEvent = 10;
        static final int TRANSACTION_reportWatchdogEvent = 4;

        public Stub()
        {
            attachInterface(this, "miui.mqsas.IMQSService");
        }
    }

    private static class Stub.Proxy
        implements IMQSService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void dialogButtonChecked(int i, int j, String s, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(19, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void dumpBugReport()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            mRemote.transact(18, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.mqsas.IMQSService";
        }

        public String getOnlineRuleMatched(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void onBootCompleted()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
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

        public void reportAnrEvent(AnrEvent anrevent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            if(anrevent == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            anrevent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            anrevent;
            parcel1.recycle();
            parcel.recycle();
            throw anrevent;
        }

        public void reportBluetoothEvent(int i, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void reportBootEvent(BootEvent bootevent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            if(bootevent == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            bootevent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bootevent;
            parcel.recycle();
            throw bootevent;
        }

        public void reportBroadcastEvent(ParceledListSlice parceledlistslice)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parceledlistslice;
            parcel.recycle();
            throw parceledlistslice;
        }

        public void reportConnectExceptionEvent(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void reportEvent(String s, String s1, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void reportEvents(String s, ParceledListSlice parceledlistslice, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            parcel.writeString(s);
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_71;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void reportJavaExceptionEvent(JavaExceptionEvent javaexceptionevent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            if(javaexceptionevent == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            javaexceptionevent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            javaexceptionevent;
            parcel1.recycle();
            parcel.recycle();
            throw javaexceptionevent;
        }

        public void reportKillProcessEvents(ParceledListSlice parceledlistslice)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parceledlistslice;
            parcel.recycle();
            throw parceledlistslice;
        }

        public void reportPackageEvent(PackageEvent packageevent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            if(packageevent == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            packageevent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            packageevent;
            parcel.recycle();
            throw packageevent;
        }

        public void reportPackageForegroundEvents(ParceledListSlice parceledlistslice)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(17, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parceledlistslice;
            parcel.recycle();
            throw parceledlistslice;
        }

        public void reportScreenOnEvent(ScreenOnEvent screenonevent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            if(screenonevent == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            screenonevent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            screenonevent;
            parcel.recycle();
            throw screenonevent;
        }

        public void reportSimpleEvent(int i, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void reportTelephonyEvent(int i, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void reportWatchdogEvent(WatchdogEvent watchdogevent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSService");
            if(watchdogevent == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            watchdogevent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            watchdogevent;
            parcel1.recycle();
            parcel.recycle();
            throw watchdogevent;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void dialogButtonChecked(int i, int j, String s, boolean flag)
        throws RemoteException;

    public abstract void dumpBugReport()
        throws RemoteException;

    public abstract String getOnlineRuleMatched(String s, String s1)
        throws RemoteException;

    public abstract void onBootCompleted()
        throws RemoteException;

    public abstract void reportAnrEvent(AnrEvent anrevent)
        throws RemoteException;

    public abstract void reportBluetoothEvent(int i, String s)
        throws RemoteException;

    public abstract void reportBootEvent(BootEvent bootevent)
        throws RemoteException;

    public abstract void reportBroadcastEvent(ParceledListSlice parceledlistslice)
        throws RemoteException;

    public abstract void reportConnectExceptionEvent(int i, int j)
        throws RemoteException;

    public abstract void reportEvent(String s, String s1, boolean flag)
        throws RemoteException;

    public abstract void reportEvents(String s, ParceledListSlice parceledlistslice, boolean flag)
        throws RemoteException;

    public abstract void reportJavaExceptionEvent(JavaExceptionEvent javaexceptionevent)
        throws RemoteException;

    public abstract void reportKillProcessEvents(ParceledListSlice parceledlistslice)
        throws RemoteException;

    public abstract void reportPackageEvent(PackageEvent packageevent)
        throws RemoteException;

    public abstract void reportPackageForegroundEvents(ParceledListSlice parceledlistslice)
        throws RemoteException;

    public abstract void reportScreenOnEvent(ScreenOnEvent screenonevent)
        throws RemoteException;

    public abstract void reportSimpleEvent(int i, String s)
        throws RemoteException;

    public abstract void reportTelephonyEvent(int i, String s)
        throws RemoteException;

    public abstract void reportWatchdogEvent(WatchdogEvent watchdogevent)
        throws RemoteException;
}
