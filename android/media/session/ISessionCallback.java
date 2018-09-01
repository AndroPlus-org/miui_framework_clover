// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.session;

import android.content.Intent;
import android.media.Rating;
import android.net.Uri;
import android.os.*;

public interface ISessionCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISessionCallback
    {

        public static ISessionCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.session.ISessionCallback");
            if(iinterface != null && (iinterface instanceof ISessionCallback))
                return (ISessionCallback)iinterface;
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
                parcel1.writeString("android.media.session.ISessionCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                String s = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onCommand(s, parcel1, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                if(parcel.readInt() != 0)
                    parcel1 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onMediaButton(parcel1, i, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                onPrepare();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPrepareFromMediaId(parcel1, parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPrepareFromSearch(parcel1, parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                if(parcel.readInt() != 0)
                    parcel1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPrepareFromUri(parcel1, parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                onPlay();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPlayFromMediaId(parcel1, parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPlayFromSearch(parcel1, parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                if(parcel.readInt() != 0)
                    parcel1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPlayFromUri(parcel1, parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                onSkipToTrack(parcel.readLong());
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                onPause();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                onStop();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                onNext();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                onPrevious();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                onFastForward();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                onRewind();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                onSeekTo(parcel.readLong());
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                if(parcel.readInt() != 0)
                    parcel = (Rating)Rating.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onRate(parcel);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onCustomAction(parcel1, parcel);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                onAdjustVolume(parcel.readInt());
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.media.session.ISessionCallback");
                onSetVolumeTo(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.session.ISessionCallback";
        static final int TRANSACTION_onAdjustVolume = 21;
        static final int TRANSACTION_onCommand = 1;
        static final int TRANSACTION_onCustomAction = 20;
        static final int TRANSACTION_onFastForward = 16;
        static final int TRANSACTION_onMediaButton = 2;
        static final int TRANSACTION_onNext = 14;
        static final int TRANSACTION_onPause = 12;
        static final int TRANSACTION_onPlay = 7;
        static final int TRANSACTION_onPlayFromMediaId = 8;
        static final int TRANSACTION_onPlayFromSearch = 9;
        static final int TRANSACTION_onPlayFromUri = 10;
        static final int TRANSACTION_onPrepare = 3;
        static final int TRANSACTION_onPrepareFromMediaId = 4;
        static final int TRANSACTION_onPrepareFromSearch = 5;
        static final int TRANSACTION_onPrepareFromUri = 6;
        static final int TRANSACTION_onPrevious = 15;
        static final int TRANSACTION_onRate = 19;
        static final int TRANSACTION_onRewind = 17;
        static final int TRANSACTION_onSeekTo = 18;
        static final int TRANSACTION_onSetVolumeTo = 22;
        static final int TRANSACTION_onSkipToTrack = 11;
        static final int TRANSACTION_onStop = 13;

        public Stub()
        {
            attachInterface(this, "android.media.session.ISessionCallback");
        }
    }

    private static class Stub.Proxy
        implements ISessionCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.session.ISessionCallback";
        }

        public void onAdjustVolume(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            parcel.writeInt(i);
            mRemote.transact(21, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            parcel.writeString(s);
            if(bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L3:
            if(resultreceiver == null)
                break MISSING_BLOCK_LABEL_90;
            parcel.writeInt(1);
            resultreceiver.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void onCustomAction(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(20, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void onFastForward()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            mRemote.transact(16, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onMediaButton(Intent intent, int i, ResultReceiver resultreceiver)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            if(resultreceiver == null)
                break MISSING_BLOCK_LABEL_90;
            parcel.writeInt(1);
            resultreceiver.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            intent;
            parcel.recycle();
            throw intent;
            parcel.writeInt(0);
              goto _L4
        }

        public void onNext()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onPause()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onPlay()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onPlayFromMediaId(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void onPlayFromSearch(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void onPlayFromUri(Uri uri, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_75;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            uri;
            parcel.recycle();
            throw uri;
            parcel.writeInt(0);
              goto _L4
        }

        public void onPrepare()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onPrepareFromMediaId(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void onPrepareFromSearch(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_75;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            uri;
            parcel.recycle();
            throw uri;
            parcel.writeInt(0);
              goto _L4
        }

        public void onPrevious()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onRate(Rating rating)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            if(rating == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            rating.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(19, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            rating;
            parcel.recycle();
            throw rating;
        }

        public void onRewind()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            mRemote.transact(17, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSeekTo(long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            parcel.writeLong(l);
            mRemote.transact(18, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSetVolumeTo(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            parcel.writeInt(i);
            mRemote.transact(22, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSkipToTrack(long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            parcel.writeLong(l);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onStop()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionCallback");
            mRemote.transact(13, parcel, null, 1);
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


    public abstract void onAdjustVolume(int i)
        throws RemoteException;

    public abstract void onCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
        throws RemoteException;

    public abstract void onCustomAction(String s, Bundle bundle)
        throws RemoteException;

    public abstract void onFastForward()
        throws RemoteException;

    public abstract void onMediaButton(Intent intent, int i, ResultReceiver resultreceiver)
        throws RemoteException;

    public abstract void onNext()
        throws RemoteException;

    public abstract void onPause()
        throws RemoteException;

    public abstract void onPlay()
        throws RemoteException;

    public abstract void onPlayFromMediaId(String s, Bundle bundle)
        throws RemoteException;

    public abstract void onPlayFromSearch(String s, Bundle bundle)
        throws RemoteException;

    public abstract void onPlayFromUri(Uri uri, Bundle bundle)
        throws RemoteException;

    public abstract void onPrepare()
        throws RemoteException;

    public abstract void onPrepareFromMediaId(String s, Bundle bundle)
        throws RemoteException;

    public abstract void onPrepareFromSearch(String s, Bundle bundle)
        throws RemoteException;

    public abstract void onPrepareFromUri(Uri uri, Bundle bundle)
        throws RemoteException;

    public abstract void onPrevious()
        throws RemoteException;

    public abstract void onRate(Rating rating)
        throws RemoteException;

    public abstract void onRewind()
        throws RemoteException;

    public abstract void onSeekTo(long l)
        throws RemoteException;

    public abstract void onSetVolumeTo(int i)
        throws RemoteException;

    public abstract void onSkipToTrack(long l)
        throws RemoteException;

    public abstract void onStop()
        throws RemoteException;
}
