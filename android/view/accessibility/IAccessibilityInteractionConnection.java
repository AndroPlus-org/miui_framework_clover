// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.accessibility;

import android.graphics.Region;
import android.os.*;
import android.view.MagnificationSpec;

// Referenced classes of package android.view.accessibility:
//            IAccessibilityInteractionConnectionCallback

public interface IAccessibilityInteractionConnection
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAccessibilityInteractionConnection
    {

        public static IAccessibilityInteractionConnection asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.accessibility.IAccessibilityInteractionConnection");
            if(iinterface != null && (iinterface instanceof IAccessibilityInteractionConnection))
                return (IAccessibilityInteractionConnection)iinterface;
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
            long l10;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.view.accessibility.IAccessibilityInteractionConnection");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityInteractionConnection");
                long l = parcel.readLong();
                IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback;
                int k;
                long l5;
                MagnificationSpec magnificationspec;
                if(parcel.readInt() != 0)
                    parcel1 = (Region)Region.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                i = parcel.readInt();
                iaccessibilityinteractionconnectioncallback = IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder());
                k = parcel.readInt();
                j = parcel.readInt();
                l5 = parcel.readLong();
                if(parcel.readInt() != 0)
                    magnificationspec = (MagnificationSpec)MagnificationSpec.CREATOR.createFromParcel(parcel);
                else
                    magnificationspec = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                findAccessibilityNodeInfoByAccessibilityId(l, parcel1, i, iaccessibilityinteractionconnectioncallback, k, j, l5, magnificationspec, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityInteractionConnection");
                long l1 = parcel.readLong();
                String s = parcel.readString();
                IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback1;
                int i1;
                long l6;
                if(parcel.readInt() != 0)
                    parcel1 = (Region)Region.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                i = parcel.readInt();
                iaccessibilityinteractionconnectioncallback1 = IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder());
                i1 = parcel.readInt();
                j = parcel.readInt();
                l6 = parcel.readLong();
                if(parcel.readInt() != 0)
                    parcel = (MagnificationSpec)MagnificationSpec.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                findAccessibilityNodeInfosByViewId(l1, s, parcel1, i, iaccessibilityinteractionconnectioncallback1, i1, j, l6, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityInteractionConnection");
                long l2 = parcel.readLong();
                String s1 = parcel.readString();
                IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback2;
                int j1;
                long l7;
                if(parcel.readInt() != 0)
                    parcel1 = (Region)Region.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                j = parcel.readInt();
                iaccessibilityinteractionconnectioncallback2 = IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                j1 = parcel.readInt();
                l7 = parcel.readLong();
                if(parcel.readInt() != 0)
                    parcel = (MagnificationSpec)MagnificationSpec.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                findAccessibilityNodeInfosByText(l2, s1, parcel1, j, iaccessibilityinteractionconnectioncallback2, i, j1, l7, parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityInteractionConnection");
                long l3 = parcel.readLong();
                int k1 = parcel.readInt();
                long l8;
                IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback3;
                int j2;
                if(parcel.readInt() != 0)
                    parcel1 = (Region)Region.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                j2 = parcel.readInt();
                iaccessibilityinteractionconnectioncallback3 = IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                j = parcel.readInt();
                l8 = parcel.readLong();
                if(parcel.readInt() != 0)
                    parcel = (MagnificationSpec)MagnificationSpec.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                findFocus(l3, k1, parcel1, j2, iaccessibilityinteractionconnectioncallback3, i, j, l8, parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityInteractionConnection");
                long l9 = parcel.readLong();
                j = parcel.readInt();
                long l4;
                int i2;
                IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback4;
                int k2;
                if(parcel.readInt() != 0)
                    parcel1 = (Region)Region.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                i = parcel.readInt();
                iaccessibilityinteractionconnectioncallback4 = IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder());
                i2 = parcel.readInt();
                k2 = parcel.readInt();
                l4 = parcel.readLong();
                if(parcel.readInt() != 0)
                    parcel = (MagnificationSpec)MagnificationSpec.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                focusSearch(l9, j, parcel1, i, iaccessibilityinteractionconnectioncallback4, i2, k2, l4, parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.view.accessibility.IAccessibilityInteractionConnection");
                l10 = parcel.readLong();
                i = parcel.readInt();
                break;
            }
            if(parcel.readInt() != 0)
                parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            performAccessibilityAction(l10, i, parcel1, parcel.readInt(), IAccessibilityInteractionConnectionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readLong());
            return true;
        }

        private static final String DESCRIPTOR = "android.view.accessibility.IAccessibilityInteractionConnection";
        static final int TRANSACTION_findAccessibilityNodeInfoByAccessibilityId = 1;
        static final int TRANSACTION_findAccessibilityNodeInfosByText = 3;
        static final int TRANSACTION_findAccessibilityNodeInfosByViewId = 2;
        static final int TRANSACTION_findFocus = 4;
        static final int TRANSACTION_focusSearch = 5;
        static final int TRANSACTION_performAccessibilityAction = 6;

        public Stub()
        {
            attachInterface(this, "android.view.accessibility.IAccessibilityInteractionConnection");
        }
    }

    private static class Stub.Proxy
        implements IAccessibilityInteractionConnection
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void findAccessibilityNodeInfoByAccessibilityId(long l, Region region, int i, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int j, int k, 
                long l1, MagnificationSpec magnificationspec, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityInteractionConnection");
            parcel.writeLong(l);
            if(region == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            region.writeToParcel(parcel, 0);
_L7:
            parcel.writeInt(i);
            if(iaccessibilityinteractionconnectioncallback == null) goto _L4; else goto _L3
_L3:
            region = iaccessibilityinteractionconnectioncallback.asBinder();
_L8:
            parcel.writeStrongBinder(region);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeLong(l1);
            if(magnificationspec == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            magnificationspec.writeToParcel(parcel, 0);
_L9:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_172;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L10:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L7
            region;
            parcel.recycle();
            throw region;
_L4:
            region = null;
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            parcel.writeInt(0);
              goto _L10
        }

        public void findAccessibilityNodeInfosByText(long l, String s, Region region, int i, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int j, 
                int k, long l1, MagnificationSpec magnificationspec)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityInteractionConnection");
            parcel.writeLong(l);
            parcel.writeString(s);
            if(region == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            region.writeToParcel(parcel, 0);
_L5:
            parcel.writeInt(i);
            if(iaccessibilityinteractionconnectioncallback == null) goto _L4; else goto _L3
_L3:
            s = iaccessibilityinteractionconnectioncallback.asBinder();
_L6:
            parcel.writeStrongBinder(s);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeLong(l1);
            if(magnificationspec == null)
                break MISSING_BLOCK_LABEL_152;
            parcel.writeInt(1);
            magnificationspec.writeToParcel(parcel, 0);
_L7:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            s;
            parcel.recycle();
            throw s;
_L4:
            s = null;
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void findAccessibilityNodeInfosByViewId(long l, String s, Region region, int i, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int j, 
                int k, long l1, MagnificationSpec magnificationspec)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityInteractionConnection");
            parcel.writeLong(l);
            parcel.writeString(s);
            if(region == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            region.writeToParcel(parcel, 0);
_L5:
            parcel.writeInt(i);
            if(iaccessibilityinteractionconnectioncallback == null) goto _L4; else goto _L3
_L3:
            s = iaccessibilityinteractionconnectioncallback.asBinder();
_L6:
            parcel.writeStrongBinder(s);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeLong(l1);
            if(magnificationspec == null)
                break MISSING_BLOCK_LABEL_152;
            parcel.writeInt(1);
            magnificationspec.writeToParcel(parcel, 0);
_L7:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            s;
            parcel.recycle();
            throw s;
_L4:
            s = null;
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void findFocus(long l, int i, Region region, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int k, 
                int i1, long l1, MagnificationSpec magnificationspec)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityInteractionConnection");
            parcel.writeLong(l);
            parcel.writeInt(i);
            if(region == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            region.writeToParcel(parcel, 0);
_L5:
            parcel.writeInt(j);
            if(iaccessibilityinteractionconnectioncallback == null) goto _L4; else goto _L3
_L3:
            region = iaccessibilityinteractionconnectioncallback.asBinder();
_L6:
            parcel.writeStrongBinder(region);
            parcel.writeInt(k);
            parcel.writeInt(i1);
            parcel.writeLong(l1);
            if(magnificationspec == null)
                break MISSING_BLOCK_LABEL_157;
            parcel.writeInt(1);
            magnificationspec.writeToParcel(parcel, 0);
_L7:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            region;
            parcel.recycle();
            throw region;
_L4:
            region = null;
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void focusSearch(long l, int i, Region region, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int k, 
                int i1, long l1, MagnificationSpec magnificationspec)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityInteractionConnection");
            parcel.writeLong(l);
            parcel.writeInt(i);
            if(region == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            region.writeToParcel(parcel, 0);
_L5:
            parcel.writeInt(j);
            if(iaccessibilityinteractionconnectioncallback == null) goto _L4; else goto _L3
_L3:
            region = iaccessibilityinteractionconnectioncallback.asBinder();
_L6:
            parcel.writeStrongBinder(region);
            parcel.writeInt(k);
            parcel.writeInt(i1);
            parcel.writeLong(l1);
            if(magnificationspec == null)
                break MISSING_BLOCK_LABEL_157;
            parcel.writeInt(1);
            magnificationspec.writeToParcel(parcel, 0);
_L7:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            region;
            parcel.recycle();
            throw region;
_L4:
            region = null;
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.accessibility.IAccessibilityInteractionConnection";
        }

        public void performAccessibilityAction(long l, int i, Bundle bundle, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int k, 
                int i1, long l1)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.accessibility.IAccessibilityInteractionConnection");
            parcel.writeLong(l);
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_121;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            bundle = obj;
            if(iaccessibilityinteractionconnectioncallback == null)
                break MISSING_BLOCK_LABEL_71;
            bundle = iaccessibilityinteractionconnectioncallback.asBinder();
            parcel.writeStrongBinder(bundle);
            parcel.writeInt(k);
            parcel.writeInt(i1);
            parcel.writeLong(l1);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel.recycle();
            throw bundle;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void findAccessibilityNodeInfoByAccessibilityId(long l, Region region, int i, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int j, int k, 
            long l1, MagnificationSpec magnificationspec, Bundle bundle)
        throws RemoteException;

    public abstract void findAccessibilityNodeInfosByText(long l, String s, Region region, int i, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int j, 
            int k, long l1, MagnificationSpec magnificationspec)
        throws RemoteException;

    public abstract void findAccessibilityNodeInfosByViewId(long l, String s, Region region, int i, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int j, 
            int k, long l1, MagnificationSpec magnificationspec)
        throws RemoteException;

    public abstract void findFocus(long l, int i, Region region, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int k, 
            int i1, long l1, MagnificationSpec magnificationspec)
        throws RemoteException;

    public abstract void focusSearch(long l, int i, Region region, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int k, 
            int i1, long l1, MagnificationSpec magnificationspec)
        throws RemoteException;

    public abstract void performAccessibilityAction(long l, int i, Bundle bundle, int j, IAccessibilityInteractionConnectionCallback iaccessibilityinteractionconnectioncallback, int k, 
            int i1, long l1)
        throws RemoteException;
}
