// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.*;

// Referenced classes of package android.content:
//            IOnPrimaryClipChangedListener, ClipData, ClipDescription

public interface IClipboard
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IClipboard
    {

        public static IClipboard asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.IClipboard");
            if(iinterface != null && (iinterface instanceof IClipboard))
                return (IClipboard)iinterface;
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
            boolean flag3;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.content.IClipboard");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.IClipboard");
                ClipData clipdata;
                if(parcel.readInt() != 0)
                    clipdata = (ClipData)ClipData.CREATOR.createFromParcel(parcel);
                else
                    clipdata = null;
                setPrimaryClip(clipdata, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.IClipboard");
                parcel = getPrimaryClip(parcel.readString());
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
                parcel.enforceInterface("android.content.IClipboard");
                parcel = getPrimaryClipDescription(parcel.readString());
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
                parcel.enforceInterface("android.content.IClipboard");
                boolean flag2 = hasPrimaryClip(parcel.readString());
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag2)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.content.IClipboard");
                addPrimaryClipChangedListener(IOnPrimaryClipChangedListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.content.IClipboard");
                removePrimaryClipChangedListener(IOnPrimaryClipChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.content.IClipboard");
                flag3 = hasClipboardText(parcel.readString());
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                break;
            }
            if(flag3)
                i = 1;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.content.IClipboard";
        static final int TRANSACTION_addPrimaryClipChangedListener = 5;
        static final int TRANSACTION_getPrimaryClip = 2;
        static final int TRANSACTION_getPrimaryClipDescription = 3;
        static final int TRANSACTION_hasClipboardText = 7;
        static final int TRANSACTION_hasPrimaryClip = 4;
        static final int TRANSACTION_removePrimaryClipChangedListener = 6;
        static final int TRANSACTION_setPrimaryClip = 1;

        public Stub()
        {
            attachInterface(this, "android.content.IClipboard");
        }
    }

    private static class Stub.Proxy
        implements IClipboard
    {

        public void addPrimaryClipChangedListener(IOnPrimaryClipChangedListener ionprimaryclipchangedlistener, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IClipboard");
            if(ionprimaryclipchangedlistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = ionprimaryclipchangedlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ionprimaryclipchangedlistener;
            parcel1.recycle();
            parcel.recycle();
            throw ionprimaryclipchangedlistener;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.IClipboard";
        }

        public ClipData getPrimaryClip(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IClipboard");
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ClipData)ClipData.CREATOR.createFromParcel(parcel1);
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

        public ClipDescription getPrimaryClipDescription(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IClipboard");
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ClipDescription)ClipDescription.CREATOR.createFromParcel(parcel1);
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

        public boolean hasClipboardText(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.IClipboard");
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean hasPrimaryClip(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.IClipboard");
            parcel.writeString(s);
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

        public void removePrimaryClipChangedListener(IOnPrimaryClipChangedListener ionprimaryclipchangedlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IClipboard");
            if(ionprimaryclipchangedlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ionprimaryclipchangedlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ionprimaryclipchangedlistener;
            parcel1.recycle();
            parcel.recycle();
            throw ionprimaryclipchangedlistener;
        }

        public void setPrimaryClip(ClipData clipdata, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.IClipboard");
            if(clipdata == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            clipdata.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            clipdata;
            parcel1.recycle();
            parcel.recycle();
            throw clipdata;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addPrimaryClipChangedListener(IOnPrimaryClipChangedListener ionprimaryclipchangedlistener, String s)
        throws RemoteException;

    public abstract ClipData getPrimaryClip(String s)
        throws RemoteException;

    public abstract ClipDescription getPrimaryClipDescription(String s)
        throws RemoteException;

    public abstract boolean hasClipboardText(String s)
        throws RemoteException;

    public abstract boolean hasPrimaryClip(String s)
        throws RemoteException;

    public abstract void removePrimaryClipChangedListener(IOnPrimaryClipChangedListener ionprimaryclipchangedlistener)
        throws RemoteException;

    public abstract void setPrimaryClip(ClipData clipdata, String s)
        throws RemoteException;
}
