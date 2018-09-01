// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.appwidget;

import android.appwidget.AppWidgetProviderInfo;
import android.content.*;
import android.content.pm.ParceledListSlice;
import android.os.*;
import android.widget.RemoteViews;

// Referenced classes of package com.android.internal.appwidget:
//            IAppWidgetHost

public interface IAppWidgetService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAppWidgetService
    {

        public static IAppWidgetService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.appwidget.IAppWidgetService");
            if(iinterface != null && (iinterface instanceof IAppWidgetService))
                return (IAppWidgetService)iinterface;
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
            boolean flag5;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.android.internal.appwidget.IAppWidgetService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                parcel = startListening(IAppWidgetHost.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt(), parcel.createIntArray());
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
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                stopListening(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                i = allocateAppWidgetId(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                deleteAppWidgetId(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                deleteHost(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                deleteAllHosts();
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                parcel = getAppWidgetViews(parcel.readString(), parcel.readInt());
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

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                parcel = getAppWidgetIdsForHost(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                parcel = createAppWidgetConfigIntentSender(parcel.readString(), parcel.readInt(), parcel.readInt());
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

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                String s = parcel.readString();
                int ai1[] = parcel.createIntArray();
                if(parcel.readInt() != 0)
                    parcel = (RemoteViews)RemoteViews.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateAppWidgetIds(s, ai1, parcel);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                String s3 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateAppWidgetOptions(s3, i, parcel);
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                parcel = getAppWidgetOptions(parcel.readString(), parcel.readInt());
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

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                String s4 = parcel.readString();
                int ai[] = parcel.createIntArray();
                if(parcel.readInt() != 0)
                    parcel = (RemoteViews)RemoteViews.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                partiallyUpdateAppWidgetIds(s4, ai, parcel);
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                ComponentName componentname;
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                if(parcel.readInt() != 0)
                    parcel = (RemoteViews)RemoteViews.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateAppWidgetProvider(componentname, parcel);
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                notifyAppWidgetViewDataChanged(parcel.readString(), parcel.createIntArray(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                parcel = getInstalledProvidersForProfile(parcel.readInt(), parcel.readInt(), parcel.readString());
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

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                parcel = getAppWidgetInfo(parcel.readString(), parcel.readInt());
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

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                boolean flag = hasBindAppWidgetPermission(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                String s5 = parcel.readString();
                i = parcel.readInt();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setBindAppWidgetPermission(s5, i, flag1);
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                String s1 = parcel.readString();
                i = parcel.readInt();
                j = parcel.readInt();
                ComponentName componentname1;
                boolean flag2;
                if(parcel.readInt() != 0)
                    componentname1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname1 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag2 = bindAppWidgetId(s1, i, j, componentname1, parcel);
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                String s2 = parcel.readString();
                i = parcel.readInt();
                Intent intent;
                if(parcel.readInt() != 0)
                    intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent = null;
                bindRemoteViewsService(s2, i, intent, parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                String s6 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                unbindRemoteViewsService(s6, i, parcel);
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getAppWidgetIds(parcel);
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                boolean flag3 = isBoundWidgetPackage(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                String s7 = parcel.readString();
                Bundle bundle;
                ComponentName componentname2;
                boolean flag4;
                if(parcel.readInt() != 0)
                    componentname2 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname2 = null;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                if(parcel.readInt() != 0)
                    parcel = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag4 = requestPinAppWidget(s7, componentname2, bundle, parcel);
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetService");
                flag5 = isRequestPinAppWidgetSupported();
                parcel1.writeNoException();
                break;
            }
            if(flag5)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.appwidget.IAppWidgetService";
        static final int TRANSACTION_allocateAppWidgetId = 3;
        static final int TRANSACTION_bindAppWidgetId = 20;
        static final int TRANSACTION_bindRemoteViewsService = 21;
        static final int TRANSACTION_createAppWidgetConfigIntentSender = 9;
        static final int TRANSACTION_deleteAllHosts = 6;
        static final int TRANSACTION_deleteAppWidgetId = 4;
        static final int TRANSACTION_deleteHost = 5;
        static final int TRANSACTION_getAppWidgetIds = 23;
        static final int TRANSACTION_getAppWidgetIdsForHost = 8;
        static final int TRANSACTION_getAppWidgetInfo = 17;
        static final int TRANSACTION_getAppWidgetOptions = 12;
        static final int TRANSACTION_getAppWidgetViews = 7;
        static final int TRANSACTION_getInstalledProvidersForProfile = 16;
        static final int TRANSACTION_hasBindAppWidgetPermission = 18;
        static final int TRANSACTION_isBoundWidgetPackage = 24;
        static final int TRANSACTION_isRequestPinAppWidgetSupported = 26;
        static final int TRANSACTION_notifyAppWidgetViewDataChanged = 15;
        static final int TRANSACTION_partiallyUpdateAppWidgetIds = 13;
        static final int TRANSACTION_requestPinAppWidget = 25;
        static final int TRANSACTION_setBindAppWidgetPermission = 19;
        static final int TRANSACTION_startListening = 1;
        static final int TRANSACTION_stopListening = 2;
        static final int TRANSACTION_unbindRemoteViewsService = 22;
        static final int TRANSACTION_updateAppWidgetIds = 10;
        static final int TRANSACTION_updateAppWidgetOptions = 11;
        static final int TRANSACTION_updateAppWidgetProvider = 14;

        public Stub()
        {
            attachInterface(this, "com.android.internal.appwidget.IAppWidgetService");
        }
    }

    private static class Stub.Proxy
        implements IAppWidgetService
    {

        public int allocateAppWidgetId(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeInt(i);
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean bindAppWidgetId(String s, int i, int j, ComponentName componentname, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_143;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(20, parcel, parcel1, 0);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void bindRemoteViewsService(String s, int i, Intent intent, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(intent == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IntentSender createAppWidgetConfigIntentSender(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel1);
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

        public void deleteAllHosts()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            mRemote.transact(6, parcel, parcel1, 0);
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

        public void deleteAppWidgetId(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
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

        public void deleteHost(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int[] getAppWidgetIds(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.createIntArray();
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

        public int[] getAppWidgetIdsForHost(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public AppWidgetProviderInfo getAppWidgetInfo(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (AppWidgetProviderInfo)AppWidgetProviderInfo.CREATOR.createFromParcel(parcel1);
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

        public Bundle getAppWidgetOptions(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
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

        public RemoteViews getAppWidgetViews(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (RemoteViews)RemoteViews.CREATOR.createFromParcel(parcel1);
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

        public ParceledListSlice getInstalledProvidersForProfile(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(16, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.appwidget.IAppWidgetService";
        }

        public boolean hasBindAppWidgetPermission(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(18, parcel, parcel1, 0);
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

        public boolean isBoundWidgetPackage(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(24, parcel, parcel1, 0);
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

        public boolean isRequestPinAppWidgetSupported()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            mRemote.transact(26, parcel, parcel1, 0);
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

        public void notifyAppWidgetViewDataChanged(String s, int ai[], int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeIntArray(ai);
            parcel.writeInt(i);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void partiallyUpdateAppWidgetIds(String s, int ai[], RemoteViews remoteviews)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeIntArray(ai);
            if(remoteviews == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            remoteviews.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean requestPinAppWidget(String s, ComponentName componentname, Bundle bundle, IntentSender intentsender)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L5:
            if(bundle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L6:
            if(intentsender == null)
                break MISSING_BLOCK_LABEL_157;
            parcel.writeInt(1);
            intentsender.writeToParcel(parcel, 0);
_L7:
            int i;
            mRemote.transact(25, parcel, parcel1, 0);
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
              goto _L5
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void setBindAppWidgetPermission(String s, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ParceledListSlice startListening(IAppWidgetHost iappwidgethost, String s, int i, int ai[])
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            if(iappwidgethost == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iappwidgethost.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeIntArray(ai);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            iappwidgethost = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return iappwidgethost;
_L2:
            iappwidgethost = null;
            if(true) goto _L4; else goto _L3
_L3:
            iappwidgethost;
            parcel1.recycle();
            parcel.recycle();
            throw iappwidgethost;
        }

        public void stopListening(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void unbindRemoteViewsService(String s, int i, Intent intent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(intent == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void updateAppWidgetIds(String s, int ai[], RemoteViews remoteviews)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeIntArray(ai);
            if(remoteviews == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            remoteviews.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void updateAppWidgetOptions(String s, int i, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void updateAppWidgetProvider(ComponentName componentname, RemoteViews remoteviews)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetService");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(remoteviews == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            remoteviews.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int allocateAppWidgetId(String s, int i)
        throws RemoteException;

    public abstract boolean bindAppWidgetId(String s, int i, int j, ComponentName componentname, Bundle bundle)
        throws RemoteException;

    public abstract void bindRemoteViewsService(String s, int i, Intent intent, IBinder ibinder)
        throws RemoteException;

    public abstract IntentSender createAppWidgetConfigIntentSender(String s, int i, int j)
        throws RemoteException;

    public abstract void deleteAllHosts()
        throws RemoteException;

    public abstract void deleteAppWidgetId(String s, int i)
        throws RemoteException;

    public abstract void deleteHost(String s, int i)
        throws RemoteException;

    public abstract int[] getAppWidgetIds(ComponentName componentname)
        throws RemoteException;

    public abstract int[] getAppWidgetIdsForHost(String s, int i)
        throws RemoteException;

    public abstract AppWidgetProviderInfo getAppWidgetInfo(String s, int i)
        throws RemoteException;

    public abstract Bundle getAppWidgetOptions(String s, int i)
        throws RemoteException;

    public abstract RemoteViews getAppWidgetViews(String s, int i)
        throws RemoteException;

    public abstract ParceledListSlice getInstalledProvidersForProfile(int i, int j, String s)
        throws RemoteException;

    public abstract boolean hasBindAppWidgetPermission(String s, int i)
        throws RemoteException;

    public abstract boolean isBoundWidgetPackage(String s, int i)
        throws RemoteException;

    public abstract boolean isRequestPinAppWidgetSupported()
        throws RemoteException;

    public abstract void notifyAppWidgetViewDataChanged(String s, int ai[], int i)
        throws RemoteException;

    public abstract void partiallyUpdateAppWidgetIds(String s, int ai[], RemoteViews remoteviews)
        throws RemoteException;

    public abstract boolean requestPinAppWidget(String s, ComponentName componentname, Bundle bundle, IntentSender intentsender)
        throws RemoteException;

    public abstract void setBindAppWidgetPermission(String s, int i, boolean flag)
        throws RemoteException;

    public abstract ParceledListSlice startListening(IAppWidgetHost iappwidgethost, String s, int i, int ai[])
        throws RemoteException;

    public abstract void stopListening(String s, int i)
        throws RemoteException;

    public abstract void unbindRemoteViewsService(String s, int i, Intent intent)
        throws RemoteException;

    public abstract void updateAppWidgetIds(String s, int ai[], RemoteViews remoteviews)
        throws RemoteException;

    public abstract void updateAppWidgetOptions(String s, int i, Bundle bundle)
        throws RemoteException;

    public abstract void updateAppWidgetProvider(ComponentName componentname, RemoteViews remoteviews)
        throws RemoteException;
}
