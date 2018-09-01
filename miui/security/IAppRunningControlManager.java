// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.security;

import android.content.Intent;
import android.os.*;
import java.util.List;

public interface IAppRunningControlManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAppRunningControlManager
    {

        public static IAppRunningControlManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.security.IAppRunningControlManager");
            if(iinterface != null && (iinterface instanceof IAppRunningControlManager))
                return (IAppRunningControlManager)iinterface;
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
            boolean flag2;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("miui.security.IAppRunningControlManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.security.IAppRunningControlManager");
                java.util.ArrayList arraylist = parcel.createStringArrayList();
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setDisallowRunningList(arraylist, parcel);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("miui.security.IAppRunningControlManager");
                String s = parcel.readString();
                Intent intent;
                boolean flag;
                if(parcel.readInt() != 0)
                    intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                parcel = getBlockActivityIntent(s, intent, flag, parcel.readInt());
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
                parcel.enforceInterface("miui.security.IAppRunningControlManager");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setBlackListEnable(flag1);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("miui.security.IAppRunningControlManager");
                flag2 = matchRule(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                break;
            }
            if(flag2)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "miui.security.IAppRunningControlManager";
        static final int TRANSACTION_getBlockActivityIntent = 2;
        static final int TRANSACTION_matchRule = 4;
        static final int TRANSACTION_setBlackListEnable = 3;
        static final int TRANSACTION_setDisallowRunningList = 1;

        public Stub()
        {
            attachInterface(this, "miui.security.IAppRunningControlManager");
        }
    }

    private static class Stub.Proxy
        implements IAppRunningControlManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public Intent getBlockActivityIntent(String s, Intent intent, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.IAppRunningControlManager");
            parcel.writeString(s);
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_144;
            s = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            s = null;
              goto _L4
        }

        public String getInterfaceDescriptor()
        {
            return "miui.security.IAppRunningControlManager";
        }

        public boolean matchRule(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.IAppRunningControlManager");
            parcel.writeString(s);
            parcel.writeInt(i);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setBlackListEnable(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.IAppRunningControlManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
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

        public void setDisallowRunningList(List list, Intent intent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.IAppRunningControlManager");
            parcel.writeStringList(list);
            if(intent == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract Intent getBlockActivityIntent(String s, Intent intent, boolean flag, int i)
        throws RemoteException;

    public abstract boolean matchRule(String s, int i)
        throws RemoteException;

    public abstract void setBlackListEnable(boolean flag)
        throws RemoteException;

    public abstract void setDisallowRunningList(List list, Intent intent)
        throws RemoteException;
}
