// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.graphics.Rect;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.*;
import android.view.Surface;

public interface ITvInputSession
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITvInputSession
    {

        public static ITvInputSession asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.tv.ITvInputSession");
            if(iinterface != null && (iinterface instanceof ITvInputSession))
                return (ITvInputSession)iinterface;
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
                parcel1.writeString("android.media.tv.ITvInputSession");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                release();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setMain(flag);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                if(parcel.readInt() != 0)
                    parcel = (Surface)Surface.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setSurface(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                dispatchSurfaceChanged(parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                setVolume(parcel.readFloat());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                if(parcel.readInt() != 0)
                    parcel1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                tune(parcel1, parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setCaptionEnabled(flag1);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                selectTrack(parcel.readInt(), parcel.readString());
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                parcel1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                appPrivateCommand(parcel1, parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                parcel1 = parcel.readStrongBinder();
                if(parcel.readInt() != 0)
                    parcel = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                createOverlayView(parcel1, parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                if(parcel.readInt() != 0)
                    parcel = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                relayoutOverlayView(parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                removeOverlayView();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                unblockContent(parcel.readString());
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                if(parcel.readInt() != 0)
                    parcel = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                timeShiftPlay(parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                timeShiftPause();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                timeShiftResume();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                timeShiftSeekTo(parcel.readLong());
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                if(parcel.readInt() != 0)
                    parcel = (PlaybackParams)PlaybackParams.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                timeShiftSetPlaybackParams(parcel);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                timeShiftEnablePositionTracking(flag2);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                if(parcel.readInt() != 0)
                    parcel = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startRecording(parcel);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.media.tv.ITvInputSession");
                stopRecording();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.tv.ITvInputSession";
        static final int TRANSACTION_appPrivateCommand = 9;
        static final int TRANSACTION_createOverlayView = 10;
        static final int TRANSACTION_dispatchSurfaceChanged = 4;
        static final int TRANSACTION_relayoutOverlayView = 11;
        static final int TRANSACTION_release = 1;
        static final int TRANSACTION_removeOverlayView = 12;
        static final int TRANSACTION_selectTrack = 8;
        static final int TRANSACTION_setCaptionEnabled = 7;
        static final int TRANSACTION_setMain = 2;
        static final int TRANSACTION_setSurface = 3;
        static final int TRANSACTION_setVolume = 5;
        static final int TRANSACTION_startRecording = 20;
        static final int TRANSACTION_stopRecording = 21;
        static final int TRANSACTION_timeShiftEnablePositionTracking = 19;
        static final int TRANSACTION_timeShiftPause = 15;
        static final int TRANSACTION_timeShiftPlay = 14;
        static final int TRANSACTION_timeShiftResume = 16;
        static final int TRANSACTION_timeShiftSeekTo = 17;
        static final int TRANSACTION_timeShiftSetPlaybackParams = 18;
        static final int TRANSACTION_tune = 6;
        static final int TRANSACTION_unblockContent = 13;

        public Stub()
        {
            attachInterface(this, "android.media.tv.ITvInputSession");
        }
    }

    private static class Stub.Proxy
        implements ITvInputSession
    {

        public void appPrivateCommand(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void createOverlayView(IBinder ibinder, Rect rect)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            parcel.writeStrongBinder(ibinder);
            if(rect == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void dispatchSurfaceChanged(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.tv.ITvInputSession";
        }

        public void relayoutOverlayView(Rect rect)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            if(rect == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            rect;
            parcel.recycle();
            throw rect;
        }

        public void release()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void removeOverlayView()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void selectTrack(int i, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void setCaptionEnabled(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setMain(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setSurface(Surface surface)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            if(surface == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            surface.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            surface;
            parcel.recycle();
            throw surface;
        }

        public void setVolume(float f)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            parcel.writeFloat(f);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void startRecording(Uri uri)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            if(uri == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(20, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel.recycle();
            throw uri;
        }

        public void stopRecording()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            mRemote.transact(21, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void timeShiftEnablePositionTracking(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(19, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void timeShiftPause()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void timeShiftPlay(Uri uri)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
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

        public void timeShiftResume()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            mRemote.transact(16, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void timeShiftSeekTo(long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            parcel.writeLong(l);
            mRemote.transact(17, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void timeShiftSetPlaybackParams(PlaybackParams playbackparams)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            if(playbackparams == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            playbackparams.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(18, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            playbackparams;
            parcel.recycle();
            throw playbackparams;
        }

        public void tune(Uri uri, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
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

        public void unblockContent(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputSession");
            parcel.writeString(s);
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void appPrivateCommand(String s, Bundle bundle)
        throws RemoteException;

    public abstract void createOverlayView(IBinder ibinder, Rect rect)
        throws RemoteException;

    public abstract void dispatchSurfaceChanged(int i, int j, int k)
        throws RemoteException;

    public abstract void relayoutOverlayView(Rect rect)
        throws RemoteException;

    public abstract void release()
        throws RemoteException;

    public abstract void removeOverlayView()
        throws RemoteException;

    public abstract void selectTrack(int i, String s)
        throws RemoteException;

    public abstract void setCaptionEnabled(boolean flag)
        throws RemoteException;

    public abstract void setMain(boolean flag)
        throws RemoteException;

    public abstract void setSurface(Surface surface)
        throws RemoteException;

    public abstract void setVolume(float f)
        throws RemoteException;

    public abstract void startRecording(Uri uri)
        throws RemoteException;

    public abstract void stopRecording()
        throws RemoteException;

    public abstract void timeShiftEnablePositionTracking(boolean flag)
        throws RemoteException;

    public abstract void timeShiftPause()
        throws RemoteException;

    public abstract void timeShiftPlay(Uri uri)
        throws RemoteException;

    public abstract void timeShiftResume()
        throws RemoteException;

    public abstract void timeShiftSeekTo(long l)
        throws RemoteException;

    public abstract void timeShiftSetPlaybackParams(PlaybackParams playbackparams)
        throws RemoteException;

    public abstract void tune(Uri uri, Bundle bundle)
        throws RemoteException;

    public abstract void unblockContent(String s)
        throws RemoteException;
}
