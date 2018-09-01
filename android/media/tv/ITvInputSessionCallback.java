// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.net.Uri;
import android.os.*;
import java.util.List;

// Referenced classes of package android.media.tv:
//            ITvInputSession, TvTrackInfo

public interface ITvInputSessionCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITvInputSessionCallback
    {

        public static ITvInputSessionCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.tv.ITvInputSessionCallback");
            if(iinterface != null && (iinterface instanceof ITvInputSessionCallback))
                return (ITvInputSessionCallback)iinterface;
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
                parcel1.writeString("android.media.tv.ITvInputSessionCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.tv.ITvInputSessionCallback");
                onSessionCreated(ITvInputSession.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.tv.ITvInputSessionCallback");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onSessionEvent(parcel1, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.tv.ITvInputSessionCallback");
                if(parcel.readInt() != 0)
                    parcel = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onChannelRetuned(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.tv.ITvInputSessionCallback");
                onTracksChanged(parcel.createTypedArrayList(TvTrackInfo.CREATOR));
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.tv.ITvInputSessionCallback");
                onTrackSelected(parcel.readInt(), parcel.readString());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.tv.ITvInputSessionCallback");
                onVideoAvailable();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.tv.ITvInputSessionCallback");
                onVideoUnavailable(parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.media.tv.ITvInputSessionCallback");
                onContentAllowed();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.media.tv.ITvInputSessionCallback");
                onContentBlocked(parcel.readString());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.media.tv.ITvInputSessionCallback");
                onLayoutSurface(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.media.tv.ITvInputSessionCallback");
                onTimeShiftStatusChanged(parcel.readInt());
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.media.tv.ITvInputSessionCallback");
                onTimeShiftStartPositionChanged(parcel.readLong());
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.media.tv.ITvInputSessionCallback");
                onTimeShiftCurrentPositionChanged(parcel.readLong());
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.media.tv.ITvInputSessionCallback");
                if(parcel.readInt() != 0)
                    parcel = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onTuned(parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.media.tv.ITvInputSessionCallback");
                if(parcel.readInt() != 0)
                    parcel = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onRecordingStopped(parcel);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.media.tv.ITvInputSessionCallback");
                onError(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.tv.ITvInputSessionCallback";
        static final int TRANSACTION_onChannelRetuned = 3;
        static final int TRANSACTION_onContentAllowed = 8;
        static final int TRANSACTION_onContentBlocked = 9;
        static final int TRANSACTION_onError = 16;
        static final int TRANSACTION_onLayoutSurface = 10;
        static final int TRANSACTION_onRecordingStopped = 15;
        static final int TRANSACTION_onSessionCreated = 1;
        static final int TRANSACTION_onSessionEvent = 2;
        static final int TRANSACTION_onTimeShiftCurrentPositionChanged = 13;
        static final int TRANSACTION_onTimeShiftStartPositionChanged = 12;
        static final int TRANSACTION_onTimeShiftStatusChanged = 11;
        static final int TRANSACTION_onTrackSelected = 5;
        static final int TRANSACTION_onTracksChanged = 4;
        static final int TRANSACTION_onTuned = 14;
        static final int TRANSACTION_onVideoAvailable = 6;
        static final int TRANSACTION_onVideoUnavailable = 7;

        public Stub()
        {
            attachInterface(this, "android.media.tv.ITvInputSessionCallback");
        }
    }

    private static class Stub.Proxy
        implements ITvInputSessionCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.tv.ITvInputSessionCallback";
        }

        public void onChannelRetuned(Uri uri)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSessionCallback");
            if(uri == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel.recycle();
            throw uri;
        }

        public void onContentAllowed()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSessionCallback");
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onContentBlocked(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSessionCallback");
            parcel.writeString(s);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onError(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSessionCallback");
            parcel.writeInt(i);
            mRemote.transact(16, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onLayoutSurface(int i, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSessionCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onRecordingStopped(Uri uri)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSessionCallback");
            if(uri == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel.recycle();
            throw uri;
        }

        public void onSessionCreated(ITvInputSession itvinputsession, IBinder ibinder)
            throws RemoteException
        {
            IBinder ibinder1;
            Parcel parcel;
            ibinder1 = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSessionCallback");
            if(itvinputsession == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder1 = itvinputsession.asBinder();
            parcel.writeStrongBinder(ibinder1);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            itvinputsession;
            parcel.recycle();
            throw itvinputsession;
        }

        public void onSessionEvent(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSessionCallback");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void onTimeShiftCurrentPositionChanged(long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSessionCallback");
            parcel.writeLong(l);
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTimeShiftStartPositionChanged(long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSessionCallback");
            parcel.writeLong(l);
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTimeShiftStatusChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSessionCallback");
            parcel.writeInt(i);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTrackSelected(int i, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSessionCallback");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onTracksChanged(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSessionCallback");
            parcel.writeTypedList(list);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void onTuned(Uri uri)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSessionCallback");
            if(uri == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel.recycle();
            throw uri;
        }

        public void onVideoAvailable()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSessionCallback");
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onVideoUnavailable(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSessionCallback");
            parcel.writeInt(i);
            mRemote.transact(7, parcel, null, 1);
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


    public abstract void onChannelRetuned(Uri uri)
        throws RemoteException;

    public abstract void onContentAllowed()
        throws RemoteException;

    public abstract void onContentBlocked(String s)
        throws RemoteException;

    public abstract void onError(int i)
        throws RemoteException;

    public abstract void onLayoutSurface(int i, int j, int k, int l)
        throws RemoteException;

    public abstract void onRecordingStopped(Uri uri)
        throws RemoteException;

    public abstract void onSessionCreated(ITvInputSession itvinputsession, IBinder ibinder)
        throws RemoteException;

    public abstract void onSessionEvent(String s, Bundle bundle)
        throws RemoteException;

    public abstract void onTimeShiftCurrentPositionChanged(long l)
        throws RemoteException;

    public abstract void onTimeShiftStartPositionChanged(long l)
        throws RemoteException;

    public abstract void onTimeShiftStatusChanged(int i)
        throws RemoteException;

    public abstract void onTrackSelected(int i, String s)
        throws RemoteException;

    public abstract void onTracksChanged(List list)
        throws RemoteException;

    public abstract void onTuned(Uri uri)
        throws RemoteException;

    public abstract void onVideoAvailable()
        throws RemoteException;

    public abstract void onVideoUnavailable(int i)
        throws RemoteException;
}
