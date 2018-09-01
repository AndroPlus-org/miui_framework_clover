// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.net.Uri;
import android.os.*;
import android.view.InputChannel;
import java.util.List;

// Referenced classes of package android.media.tv:
//            TvTrackInfo

public interface ITvInputClient
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITvInputClient
    {

        public static ITvInputClient asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.tv.ITvInputClient");
            if(iinterface != null && (iinterface instanceof ITvInputClient))
                return (ITvInputClient)iinterface;
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
                parcel1.writeString("android.media.tv.ITvInputClient");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                String s = parcel.readString();
                IBinder ibinder = parcel.readStrongBinder();
                if(parcel.readInt() != 0)
                    parcel1 = (InputChannel)InputChannel.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onSessionCreated(s, ibinder, parcel1, parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                onSessionReleased(parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                String s1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onSessionEvent(s1, parcel1, parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                if(parcel.readInt() != 0)
                    parcel1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onChannelRetuned(parcel1, parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                onTracksChanged(parcel.createTypedArrayList(TvTrackInfo.CREATOR), parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                onTrackSelected(parcel.readInt(), parcel.readString(), parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                onVideoAvailable(parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                onVideoUnavailable(parcel.readInt(), parcel.readInt());
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                onContentAllowed(parcel.readInt());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                onContentBlocked(parcel.readString(), parcel.readInt());
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                onLayoutSurface(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                onTimeShiftStatusChanged(parcel.readInt(), parcel.readInt());
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                onTimeShiftStartPositionChanged(parcel.readLong(), parcel.readInt());
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                onTimeShiftCurrentPositionChanged(parcel.readLong(), parcel.readInt());
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onTuned(i, parcel);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                if(parcel.readInt() != 0)
                    parcel1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onRecordingStopped(parcel1, parcel.readInt());
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.media.tv.ITvInputClient");
                onError(parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.tv.ITvInputClient";
        static final int TRANSACTION_onChannelRetuned = 4;
        static final int TRANSACTION_onContentAllowed = 9;
        static final int TRANSACTION_onContentBlocked = 10;
        static final int TRANSACTION_onError = 17;
        static final int TRANSACTION_onLayoutSurface = 11;
        static final int TRANSACTION_onRecordingStopped = 16;
        static final int TRANSACTION_onSessionCreated = 1;
        static final int TRANSACTION_onSessionEvent = 3;
        static final int TRANSACTION_onSessionReleased = 2;
        static final int TRANSACTION_onTimeShiftCurrentPositionChanged = 14;
        static final int TRANSACTION_onTimeShiftStartPositionChanged = 13;
        static final int TRANSACTION_onTimeShiftStatusChanged = 12;
        static final int TRANSACTION_onTrackSelected = 6;
        static final int TRANSACTION_onTracksChanged = 5;
        static final int TRANSACTION_onTuned = 15;
        static final int TRANSACTION_onVideoAvailable = 7;
        static final int TRANSACTION_onVideoUnavailable = 8;

        public Stub()
        {
            attachInterface(this, "android.media.tv.ITvInputClient");
        }
    }

    private static class Stub.Proxy
        implements ITvInputClient
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.tv.ITvInputClient";
        }

        public void onChannelRetuned(Uri uri, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            if(uri == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel.recycle();
            throw uri;
        }

        public void onContentAllowed(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            parcel.writeInt(i);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onContentBlocked(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onError(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(17, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onLayoutSurface(int i, int j, int k, int l, int i1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeInt(i1);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onRecordingStopped(Uri uri, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            if(uri == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(16, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel.recycle();
            throw uri;
        }

        public void onSessionCreated(String s, IBinder ibinder, InputChannel inputchannel, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            parcel.writeString(s);
            parcel.writeStrongBinder(ibinder);
            if(inputchannel == null)
                break MISSING_BLOCK_LABEL_69;
            parcel.writeInt(1);
            inputchannel.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void onSessionEvent(String s, Bundle bundle, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_62;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void onSessionReleased(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTimeShiftCurrentPositionChanged(long l, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            parcel.writeLong(l);
            parcel.writeInt(i);
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTimeShiftStartPositionChanged(long l, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            parcel.writeLong(l);
            parcel.writeInt(i);
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTimeShiftStatusChanged(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTrackSelected(int i, String s, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onTracksChanged(List list, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            parcel.writeTypedList(list);
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void onTuned(int i, Uri uri)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            parcel.writeInt(i);
            if(uri == null)
                break MISSING_BLOCK_LABEL_50;
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

        public void onVideoAvailable(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            parcel.writeInt(i);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onVideoUnavailable(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputClient");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(8, parcel, null, 1);
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


    public abstract void onChannelRetuned(Uri uri, int i)
        throws RemoteException;

    public abstract void onContentAllowed(int i)
        throws RemoteException;

    public abstract void onContentBlocked(String s, int i)
        throws RemoteException;

    public abstract void onError(int i, int j)
        throws RemoteException;

    public abstract void onLayoutSurface(int i, int j, int k, int l, int i1)
        throws RemoteException;

    public abstract void onRecordingStopped(Uri uri, int i)
        throws RemoteException;

    public abstract void onSessionCreated(String s, IBinder ibinder, InputChannel inputchannel, int i)
        throws RemoteException;

    public abstract void onSessionEvent(String s, Bundle bundle, int i)
        throws RemoteException;

    public abstract void onSessionReleased(int i)
        throws RemoteException;

    public abstract void onTimeShiftCurrentPositionChanged(long l, int i)
        throws RemoteException;

    public abstract void onTimeShiftStartPositionChanged(long l, int i)
        throws RemoteException;

    public abstract void onTimeShiftStatusChanged(int i, int j)
        throws RemoteException;

    public abstract void onTrackSelected(int i, String s, int j)
        throws RemoteException;

    public abstract void onTracksChanged(List list, int i)
        throws RemoteException;

    public abstract void onTuned(int i, Uri uri)
        throws RemoteException;

    public abstract void onVideoAvailable(int i)
        throws RemoteException;

    public abstract void onVideoUnavailable(int i, int j)
        throws RemoteException;
}
