// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.appwidget;

import android.appwidget.AppWidgetProviderInfo;
import android.os.*;
import android.widget.RemoteViews;

public interface IAppWidgetHost
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAppWidgetHost
    {

        public static IAppWidgetHost asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.appwidget.IAppWidgetHost");
            if(iinterface != null && (iinterface instanceof IAppWidgetHost))
                return (IAppWidgetHost)iinterface;
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
                parcel1.writeString("com.android.internal.appwidget.IAppWidgetHost");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetHost");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (RemoteViews)RemoteViews.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateAppWidget(i, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetHost");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (AppWidgetProviderInfo)AppWidgetProviderInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                providerChanged(i, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetHost");
                providersChanged();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.appwidget.IAppWidgetHost");
                viewDataChanged(parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.appwidget.IAppWidgetHost";
        static final int TRANSACTION_providerChanged = 2;
        static final int TRANSACTION_providersChanged = 3;
        static final int TRANSACTION_updateAppWidget = 1;
        static final int TRANSACTION_viewDataChanged = 4;

        public Stub()
        {
            attachInterface(this, "com.android.internal.appwidget.IAppWidgetHost");
        }
    }

    private static class Stub.Proxy
        implements IAppWidgetHost
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.appwidget.IAppWidgetHost";
        }

        public void providerChanged(int i, AppWidgetProviderInfo appwidgetproviderinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetHost");
            parcel.writeInt(i);
            if(appwidgetproviderinfo == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            appwidgetproviderinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            appwidgetproviderinfo;
            parcel.recycle();
            throw appwidgetproviderinfo;
        }

        public void providersChanged()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetHost");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void updateAppWidget(int i, RemoteViews remoteviews)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetHost");
            parcel.writeInt(i);
            if(remoteviews == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            remoteviews.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            remoteviews;
            parcel.recycle();
            throw remoteviews;
        }

        public void viewDataChanged(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.appwidget.IAppWidgetHost");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void providerChanged(int i, AppWidgetProviderInfo appwidgetproviderinfo)
        throws RemoteException;

    public abstract void providersChanged()
        throws RemoteException;

    public abstract void updateAppWidget(int i, RemoteViews remoteviews)
        throws RemoteException;

    public abstract void viewDataChanged(int i, int j)
        throws RemoteException;
}
