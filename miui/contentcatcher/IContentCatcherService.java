// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher;

import android.os.*;
import miui.contentcatcher.sdk.ClientToken;
import miui.contentcatcher.sdk.Content;
import miui.contentcatcher.sdk.DecorateContentParam;
import miui.contentcatcher.sdk.Token;
import miui.contentcatcher.sdk.data.PageConfig;
import miui.contentcatcher.sdk.injector.IContentDecorateCallback;
import miui.contentcatcher.sdk.listener.IContentListenerCallback;

public interface IContentCatcherService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IContentCatcherService
    {

        public static IContentCatcherService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.contentcatcher.IContentCatcherService");
            if(iinterface != null && (iinterface instanceof IContentCatcherService))
                return (IContentCatcherService)iinterface;
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
            String s1;
            String s3;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("miui.contentcatcher.IContentCatcherService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.contentcatcher.IContentCatcherService");
                if(parcel.readInt() != 0)
                    parcel = (Token)Token.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getPageConfig(parcel);
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
                parcel.enforceInterface("miui.contentcatcher.IContentCatcherService");
                Token token;
                if(parcel.readInt() != 0)
                    token = (Token)Token.CREATOR.createFromParcel(parcel);
                else
                    token = null;
                registerContentInjector(token, miui.contentcatcher.sdk.injector.IContentDecorateCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("miui.contentcatcher.IContentCatcherService");
                if(parcel.readInt() != 0)
                    parcel = (Token)Token.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                unregisterContentInjector(parcel);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("miui.contentcatcher.IContentCatcherService");
                if(parcel.readInt() != 0)
                    parcel = (Content)Content.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onContentCatched(parcel);
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("miui.contentcatcher.IContentCatcherService");
                ClientToken clienttoken;
                if(parcel.readInt() != 0)
                    clienttoken = (ClientToken)ClientToken.CREATOR.createFromParcel(parcel);
                else
                    clienttoken = null;
                registerContentListener(clienttoken, miui.contentcatcher.sdk.listener.IContentListenerCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("miui.contentcatcher.IContentCatcherService");
                if(parcel.readInt() != 0)
                    parcel = (ClientToken)ClientToken.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                unregisterContentListener(parcel);
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("miui.contentcatcher.IContentCatcherService");
                ClientToken clienttoken1;
                Token token1;
                if(parcel.readInt() != 0)
                    clienttoken1 = (ClientToken)ClientToken.CREATOR.createFromParcel(parcel);
                else
                    clienttoken1 = null;
                if(parcel.readInt() != 0)
                    token1 = (Token)Token.CREATOR.createFromParcel(parcel);
                else
                    token1 = null;
                if(parcel.readInt() != 0)
                    parcel = (DecorateContentParam)DecorateContentParam.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                decorateContent(clienttoken1, token1, parcel);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("miui.contentcatcher.IContentCatcherService");
                updateConfig(parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("miui.contentcatcher.IContentCatcherService");
                String s2 = parcel.readString();
                String s = parcel.readString();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                updateClientConfig(s2, s, flag);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("miui.contentcatcher.IContentCatcherService");
                s3 = parcel.readString();
                s1 = parcel.readString();
                break;
            }
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            updateJobValidity(s3, s1, flag1);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "miui.contentcatcher.IContentCatcherService";
        static final int TRANSACTION_decorateContent = 7;
        static final int TRANSACTION_getPageConfig = 1;
        static final int TRANSACTION_onContentCatched = 4;
        static final int TRANSACTION_registerContentInjector = 2;
        static final int TRANSACTION_registerContentListener = 5;
        static final int TRANSACTION_unregisterContentInjector = 3;
        static final int TRANSACTION_unregisterContentListener = 6;
        static final int TRANSACTION_updateClientConfig = 9;
        static final int TRANSACTION_updateConfig = 8;
        static final int TRANSACTION_updateJobValidity = 10;

        public Stub()
        {
            attachInterface(this, "miui.contentcatcher.IContentCatcherService");
        }
    }

    private static class Stub.Proxy
        implements IContentCatcherService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void decorateContent(ClientToken clienttoken, Token token, DecorateContentParam decoratecontentparam)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.contentcatcher.IContentCatcherService");
            if(clienttoken == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            clienttoken.writeToParcel(parcel, 0);
_L5:
            if(token == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            token.writeToParcel(parcel, 0);
_L6:
            if(decoratecontentparam == null)
                break MISSING_BLOCK_LABEL_132;
            parcel.writeInt(1);
            decoratecontentparam.writeToParcel(parcel, 0);
_L7:
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            clienttoken;
            parcel1.recycle();
            parcel.recycle();
            throw clienttoken;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public String getInterfaceDescriptor()
        {
            return "miui.contentcatcher.IContentCatcherService";
        }

        public PageConfig getPageConfig(Token token)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.contentcatcher.IContentCatcherService");
            if(token == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            token.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_96;
            token = (PageConfig)PageConfig.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return token;
_L2:
            parcel.writeInt(0);
              goto _L3
            token;
            parcel1.recycle();
            parcel.recycle();
            throw token;
            token = null;
              goto _L4
        }

        public void onContentCatched(Content content)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.contentcatcher.IContentCatcherService");
            if(content == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            content.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            content;
            parcel1.recycle();
            parcel.recycle();
            throw content;
        }

        public void registerContentInjector(Token token, IContentDecorateCallback icontentdecoratecallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.contentcatcher.IContentCatcherService");
            if(token == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            token.writeToParcel(parcel, 0);
_L4:
            token = obj;
            if(icontentdecoratecallback == null)
                break MISSING_BLOCK_LABEL_49;
            token = icontentdecoratecallback.asBinder();
            parcel.writeStrongBinder(token);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            token;
            parcel1.recycle();
            parcel.recycle();
            throw token;
        }

        public void registerContentListener(ClientToken clienttoken, IContentListenerCallback icontentlistenercallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.contentcatcher.IContentCatcherService");
            if(clienttoken == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            clienttoken.writeToParcel(parcel, 0);
_L4:
            clienttoken = obj;
            if(icontentlistenercallback == null)
                break MISSING_BLOCK_LABEL_49;
            clienttoken = icontentlistenercallback.asBinder();
            parcel.writeStrongBinder(clienttoken);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            clienttoken;
            parcel1.recycle();
            parcel.recycle();
            throw clienttoken;
        }

        public void unregisterContentInjector(Token token)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.contentcatcher.IContentCatcherService");
            if(token == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            token.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            token;
            parcel1.recycle();
            parcel.recycle();
            throw token;
        }

        public void unregisterContentListener(ClientToken clienttoken)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.contentcatcher.IContentCatcherService");
            if(clienttoken == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            clienttoken.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            clienttoken;
            parcel1.recycle();
            parcel.recycle();
            throw clienttoken;
        }

        public void updateClientConfig(String s, String s1, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.contentcatcher.IContentCatcherService");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void updateConfig(String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.contentcatcher.IContentCatcherService");
            parcel.writeStringArray(as);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void updateJobValidity(String s, String s1, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.contentcatcher.IContentCatcherService");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
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


    public abstract void decorateContent(ClientToken clienttoken, Token token, DecorateContentParam decoratecontentparam)
        throws RemoteException;

    public abstract PageConfig getPageConfig(Token token)
        throws RemoteException;

    public abstract void onContentCatched(Content content)
        throws RemoteException;

    public abstract void registerContentInjector(Token token, IContentDecorateCallback icontentdecoratecallback)
        throws RemoteException;

    public abstract void registerContentListener(ClientToken clienttoken, IContentListenerCallback icontentlistenercallback)
        throws RemoteException;

    public abstract void unregisterContentInjector(Token token)
        throws RemoteException;

    public abstract void unregisterContentListener(ClientToken clienttoken)
        throws RemoteException;

    public abstract void updateClientConfig(String s, String s1, boolean flag)
        throws RemoteException;

    public abstract void updateConfig(String as[])
        throws RemoteException;

    public abstract void updateJobValidity(String s, String s1, boolean flag)
        throws RemoteException;
}
