// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.session;

import android.content.pm.ParceledListSlice;
import android.media.MediaMetadata;
import android.os.*;
import android.text.TextUtils;

// Referenced classes of package android.media.session:
//            PlaybackState, ParcelableVolumeInfo

public interface ISessionControllerCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISessionControllerCallback
    {

        public static ISessionControllerCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.session.ISessionControllerCallback");
            if(iinterface != null && (iinterface instanceof ISessionControllerCallback))
                return (ISessionControllerCallback)iinterface;
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
                parcel1.writeString("android.media.session.ISessionControllerCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.session.ISessionControllerCallback");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onEvent(parcel1, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.session.ISessionControllerCallback");
                onSessionDestroyed();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.session.ISessionControllerCallback");
                if(parcel.readInt() != 0)
                    parcel = (PlaybackState)PlaybackState.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPlaybackStateChanged(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.session.ISessionControllerCallback");
                if(parcel.readInt() != 0)
                    parcel = (MediaMetadata)MediaMetadata.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onMetadataChanged(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.session.ISessionControllerCallback");
                if(parcel.readInt() != 0)
                    parcel = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onQueueChanged(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.session.ISessionControllerCallback");
                if(parcel.readInt() != 0)
                    parcel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onQueueTitleChanged(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.session.ISessionControllerCallback");
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onExtrasChanged(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.media.session.ISessionControllerCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ParcelableVolumeInfo)ParcelableVolumeInfo.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onVolumeInfoChanged(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.session.ISessionControllerCallback";
        static final int TRANSACTION_onEvent = 1;
        static final int TRANSACTION_onExtrasChanged = 7;
        static final int TRANSACTION_onMetadataChanged = 4;
        static final int TRANSACTION_onPlaybackStateChanged = 3;
        static final int TRANSACTION_onQueueChanged = 5;
        static final int TRANSACTION_onQueueTitleChanged = 6;
        static final int TRANSACTION_onSessionDestroyed = 2;
        static final int TRANSACTION_onVolumeInfoChanged = 8;

        public Stub()
        {
            attachInterface(this, "android.media.session.ISessionControllerCallback");
        }
    }

    private static class Stub.Proxy
        implements ISessionControllerCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.session.ISessionControllerCallback";
        }

        public void onEvent(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionControllerCallback");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel.recycle();
            throw s;
        }

        public void onExtrasChanged(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionControllerCallback");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel.recycle();
            throw bundle;
        }

        public void onMetadataChanged(MediaMetadata mediametadata)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionControllerCallback");
            if(mediametadata == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            mediametadata.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            mediametadata;
            parcel.recycle();
            throw mediametadata;
        }

        public void onPlaybackStateChanged(PlaybackState playbackstate)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionControllerCallback");
            if(playbackstate == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            playbackstate.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            playbackstate;
            parcel.recycle();
            throw playbackstate;
        }

        public void onQueueChanged(ParceledListSlice parceledlistslice)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionControllerCallback");
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parceledlistslice;
            parcel.recycle();
            throw parceledlistslice;
        }

        public void onQueueTitleChanged(CharSequence charsequence)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionControllerCallback");
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L1:
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            charsequence;
            parcel.recycle();
            throw charsequence;
        }

        public void onSessionDestroyed()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionControllerCallback");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onVolumeInfoChanged(ParcelableVolumeInfo parcelablevolumeinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ISessionControllerCallback");
            if(parcelablevolumeinfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            parcelablevolumeinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parcelablevolumeinfo;
            parcel.recycle();
            throw parcelablevolumeinfo;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onEvent(String s, Bundle bundle)
        throws RemoteException;

    public abstract void onExtrasChanged(Bundle bundle)
        throws RemoteException;

    public abstract void onMetadataChanged(MediaMetadata mediametadata)
        throws RemoteException;

    public abstract void onPlaybackStateChanged(PlaybackState playbackstate)
        throws RemoteException;

    public abstract void onQueueChanged(ParceledListSlice parceledlistslice)
        throws RemoteException;

    public abstract void onQueueTitleChanged(CharSequence charsequence)
        throws RemoteException;

    public abstract void onSessionDestroyed()
        throws RemoteException;

    public abstract void onVolumeInfoChanged(ParcelableVolumeInfo parcelablevolumeinfo)
        throws RemoteException;
}
