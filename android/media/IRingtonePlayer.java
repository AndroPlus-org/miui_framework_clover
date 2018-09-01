// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.net.Uri;
import android.os.*;

// Referenced classes of package android.media:
//            AudioAttributes

public interface IRingtonePlayer
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRingtonePlayer
    {

        public static IRingtonePlayer asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IRingtonePlayer");
            if(iinterface != null && (iinterface instanceof IRingtonePlayer))
                return (IRingtonePlayer)iinterface;
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
                parcel1.writeString("android.media.IRingtonePlayer");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IRingtonePlayer");
                IBinder ibinder = parcel.readStrongBinder();
                AudioAttributes audioattributes;
                float f;
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    audioattributes = (AudioAttributes)AudioAttributes.CREATOR.createFromParcel(parcel);
                else
                    audioattributes = null;
                f = parcel.readFloat();
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                play(ibinder, parcel1, audioattributes, f, flag);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.IRingtonePlayer");
                stop(parcel.readStrongBinder());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.IRingtonePlayer");
                boolean flag1 = isPlaying(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.IRingtonePlayer");
                parcel1 = parcel.readStrongBinder();
                float f1 = parcel.readFloat();
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                setPlaybackProperties(parcel1, f1, flag2);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.IRingtonePlayer");
                UserHandle userhandle;
                boolean flag3;
                if(parcel.readInt() != 0)
                    parcel1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    userhandle = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    userhandle = null;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                if(parcel.readInt() != 0)
                    parcel = (AudioAttributes)AudioAttributes.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                playAsync(parcel1, userhandle, flag3, parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.IRingtonePlayer");
                stopAsync();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.IRingtonePlayer");
                if(parcel.readInt() != 0)
                    parcel = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getTitle(parcel);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.media.IRingtonePlayer");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Uri)Uri.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            parcel = openRingtone(parcel);
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
        }

        private static final String DESCRIPTOR = "android.media.IRingtonePlayer";
        static final int TRANSACTION_getTitle = 7;
        static final int TRANSACTION_isPlaying = 3;
        static final int TRANSACTION_openRingtone = 8;
        static final int TRANSACTION_play = 1;
        static final int TRANSACTION_playAsync = 5;
        static final int TRANSACTION_setPlaybackProperties = 4;
        static final int TRANSACTION_stop = 2;
        static final int TRANSACTION_stopAsync = 6;

        public Stub()
        {
            attachInterface(this, "android.media.IRingtonePlayer");
        }
    }

    private static class Stub.Proxy
        implements IRingtonePlayer
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IRingtonePlayer";
        }

        public String getTitle(Uri uri)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRingtonePlayer");
            if(uri == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            uri = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return uri;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel1.recycle();
            parcel.recycle();
            throw uri;
        }

        public boolean isPlaying(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.IRingtonePlayer");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, parcel1, 0);
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
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public ParcelFileDescriptor openRingtone(Uri uri)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRingtonePlayer");
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_97;
            uri = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return uri;
_L2:
            parcel.writeInt(0);
              goto _L3
            uri;
            parcel1.recycle();
            parcel.recycle();
            throw uri;
            uri = null;
              goto _L4
        }

        public void play(IBinder ibinder, Uri uri, AudioAttributes audioattributes, float f, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRingtonePlayer");
            parcel.writeStrongBinder(ibinder);
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L3:
            if(audioattributes == null)
                break MISSING_BLOCK_LABEL_112;
            parcel.writeInt(1);
            audioattributes.writeToParcel(parcel, 0);
_L4:
            parcel.writeFloat(f);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            ibinder;
            parcel.recycle();
            throw ibinder;
            parcel.writeInt(0);
              goto _L4
        }

        public void playAsync(Uri uri, UserHandle userhandle, boolean flag, AudioAttributes audioattributes)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRingtonePlayer");
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L5:
            if(userhandle == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L6:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(audioattributes == null)
                break MISSING_BLOCK_LABEL_132;
            parcel.writeInt(1);
            audioattributes.writeToParcel(parcel, 0);
_L7:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            uri;
            parcel.recycle();
            throw uri;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void setPlaybackProperties(IBinder ibinder, float f, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRingtonePlayer");
            parcel.writeStrongBinder(ibinder);
            parcel.writeFloat(f);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void stop(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRingtonePlayer");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void stopAsync()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRingtonePlayer");
            mRemote.transact(6, parcel, null, 1);
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


    public abstract String getTitle(Uri uri)
        throws RemoteException;

    public abstract boolean isPlaying(IBinder ibinder)
        throws RemoteException;

    public abstract ParcelFileDescriptor openRingtone(Uri uri)
        throws RemoteException;

    public abstract void play(IBinder ibinder, Uri uri, AudioAttributes audioattributes, float f, boolean flag)
        throws RemoteException;

    public abstract void playAsync(Uri uri, UserHandle userhandle, boolean flag, AudioAttributes audioattributes)
        throws RemoteException;

    public abstract void setPlaybackProperties(IBinder ibinder, float f, boolean flag)
        throws RemoteException;

    public abstract void stop(IBinder ibinder)
        throws RemoteException;

    public abstract void stopAsync()
        throws RemoteException;
}
