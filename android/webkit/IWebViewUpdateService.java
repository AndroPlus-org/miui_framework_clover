// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.content.pm.PackageInfo;
import android.os.*;

// Referenced classes of package android.webkit:
//            WebViewProviderInfo, WebViewProviderResponse

public interface IWebViewUpdateService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWebViewUpdateService
    {

        public static IWebViewUpdateService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.webkit.IWebViewUpdateService");
            if(iinterface != null && (iinterface instanceof IWebViewUpdateService))
                return (IWebViewUpdateService)iinterface;
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
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.webkit.IWebViewUpdateService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.webkit.IWebViewUpdateService");
                notifyRelroCreationCompleted();
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.webkit.IWebViewUpdateService");
                parcel = waitForAndGetProvider();
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
                parcel.enforceInterface("android.webkit.IWebViewUpdateService");
                parcel = changeProviderAndSetting(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.webkit.IWebViewUpdateService");
                parcel = getValidWebViewPackages();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.webkit.IWebViewUpdateService");
                parcel = getAllWebViewPackages();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.webkit.IWebViewUpdateService");
                parcel = getCurrentWebViewPackageName();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.webkit.IWebViewUpdateService");
                parcel = getCurrentWebViewPackage();
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
                parcel.enforceInterface("android.webkit.IWebViewUpdateService");
                boolean flag2 = isFallbackPackage(parcel.readString());
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag2)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.webkit.IWebViewUpdateService");
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                enableFallbackLogic(flag3);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.webkit.IWebViewUpdateService");
                boolean flag4 = isMultiProcessEnabled();
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag4)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.webkit.IWebViewUpdateService");
                break;
            }
            boolean flag5;
            if(parcel.readInt() != 0)
                flag5 = true;
            else
                flag5 = false;
            enableMultiProcess(flag5);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.webkit.IWebViewUpdateService";
        static final int TRANSACTION_changeProviderAndSetting = 3;
        static final int TRANSACTION_enableFallbackLogic = 9;
        static final int TRANSACTION_enableMultiProcess = 11;
        static final int TRANSACTION_getAllWebViewPackages = 5;
        static final int TRANSACTION_getCurrentWebViewPackage = 7;
        static final int TRANSACTION_getCurrentWebViewPackageName = 6;
        static final int TRANSACTION_getValidWebViewPackages = 4;
        static final int TRANSACTION_isFallbackPackage = 8;
        static final int TRANSACTION_isMultiProcessEnabled = 10;
        static final int TRANSACTION_notifyRelroCreationCompleted = 1;
        static final int TRANSACTION_waitForAndGetProvider = 2;

        public Stub()
        {
            attachInterface(this, "android.webkit.IWebViewUpdateService");
        }
    }

    private static class Stub.Proxy
        implements IWebViewUpdateService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String changeProviderAndSetting(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.webkit.IWebViewUpdateService");
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
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

        public void enableFallbackLogic(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.webkit.IWebViewUpdateService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
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

        public void enableMultiProcess(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.webkit.IWebViewUpdateService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
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

        public WebViewProviderInfo[] getAllWebViewPackages()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            WebViewProviderInfo awebviewproviderinfo[];
            parcel.writeInterfaceToken("android.webkit.IWebViewUpdateService");
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            awebviewproviderinfo = (WebViewProviderInfo[])parcel1.createTypedArray(WebViewProviderInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return awebviewproviderinfo;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public PackageInfo getCurrentWebViewPackage()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.webkit.IWebViewUpdateService");
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            PackageInfo packageinfo = (PackageInfo)PackageInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return packageinfo;
_L2:
            packageinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getCurrentWebViewPackageName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.webkit.IWebViewUpdateService");
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.webkit.IWebViewUpdateService";
        }

        public WebViewProviderInfo[] getValidWebViewPackages()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            WebViewProviderInfo awebviewproviderinfo[];
            parcel.writeInterfaceToken("android.webkit.IWebViewUpdateService");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            awebviewproviderinfo = (WebViewProviderInfo[])parcel1.createTypedArray(WebViewProviderInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return awebviewproviderinfo;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isFallbackPackage(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.webkit.IWebViewUpdateService");
            parcel.writeString(s);
            mRemote.transact(8, parcel, parcel1, 0);
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

        public boolean isMultiProcessEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.webkit.IWebViewUpdateService");
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

        public void notifyRelroCreationCompleted()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.webkit.IWebViewUpdateService");
            mRemote.transact(1, parcel, parcel1, 0);
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

        public WebViewProviderResponse waitForAndGetProvider()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.webkit.IWebViewUpdateService");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            WebViewProviderResponse webviewproviderresponse = (WebViewProviderResponse)WebViewProviderResponse.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return webviewproviderresponse;
_L2:
            webviewproviderresponse = null;
            if(true) goto _L4; else goto _L3
_L3:
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


    public abstract String changeProviderAndSetting(String s)
        throws RemoteException;

    public abstract void enableFallbackLogic(boolean flag)
        throws RemoteException;

    public abstract void enableMultiProcess(boolean flag)
        throws RemoteException;

    public abstract WebViewProviderInfo[] getAllWebViewPackages()
        throws RemoteException;

    public abstract PackageInfo getCurrentWebViewPackage()
        throws RemoteException;

    public abstract String getCurrentWebViewPackageName()
        throws RemoteException;

    public abstract WebViewProviderInfo[] getValidWebViewPackages()
        throws RemoteException;

    public abstract boolean isFallbackPackage(String s)
        throws RemoteException;

    public abstract boolean isMultiProcessEnabled()
        throws RemoteException;

    public abstract void notifyRelroCreationCompleted()
        throws RemoteException;

    public abstract WebViewProviderResponse waitForAndGetProvider()
        throws RemoteException;
}
