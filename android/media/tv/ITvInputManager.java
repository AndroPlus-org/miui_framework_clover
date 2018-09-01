// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.content.Intent;
import android.graphics.Rect;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.*;
import android.view.Surface;
import java.util.List;

// Referenced classes of package android.media.tv:
//            ITvInputHardwareCallback, TvInputInfo, ITvInputHardware, TvStreamConfig, 
//            ITvInputClient, DvbDeviceInfo, ITvInputManagerCallback, TvInputHardwareInfo, 
//            TvContentRatingSystemInfo

public interface ITvInputManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITvInputManager
    {

        public static ITvInputManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.tv.ITvInputManager");
            if(iinterface != null && (iinterface instanceof ITvInputManager))
                return (ITvInputManager)iinterface;
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
                parcel1.writeString("android.media.tv.ITvInputManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                parcel = getTvInputList(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                parcel = getTvInputInfo(parcel.readString(), parcel.readInt());
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
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                TvInputInfo tvinputinfo;
                if(parcel.readInt() != 0)
                    tvinputinfo = (TvInputInfo)TvInputInfo.CREATOR.createFromParcel(parcel);
                else
                    tvinputinfo = null;
                updateTvInputInfo(tvinputinfo, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                i = getTvInputState(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                parcel = getTvContentRatingSystemList(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                registerCallback(ITvInputManagerCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                unregisterCallback(ITvInputManagerCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                boolean flag = isParentalControlsEnabled(parcel.readInt());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setParentalControlsEnabled(flag1, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                boolean flag2 = isRatingBlocked(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                parcel = getBlockedRatings(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                addBlockedRating(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                removeBlockedRating(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                ITvInputClient itvinputclient = ITvInputClient.Stub.asInterface(parcel.readStrongBinder());
                String s = parcel.readString();
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                createSession(itvinputclient, s, flag3, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                releaseSession(parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                setMainSession(parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                IBinder ibinder2 = parcel.readStrongBinder();
                Surface surface;
                if(parcel.readInt() != 0)
                    surface = (Surface)Surface.CREATOR.createFromParcel(parcel);
                else
                    surface = null;
                setSurface(ibinder2, surface, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                dispatchSurfaceChanged(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                setVolume(parcel.readStrongBinder(), parcel.readFloat(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                IBinder ibinder9 = parcel.readStrongBinder();
                Uri uri;
                Bundle bundle1;
                if(parcel.readInt() != 0)
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri = null;
                if(parcel.readInt() != 0)
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle1 = null;
                tune(ibinder9, uri, bundle1, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                IBinder ibinder = parcel.readStrongBinder();
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                setCaptionEnabled(ibinder, flag4, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                selectTrack(parcel.readStrongBinder(), parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                IBinder ibinder3 = parcel.readStrongBinder();
                String s1 = parcel.readString();
                Bundle bundle;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                sendAppPrivateCommand(ibinder3, s1, bundle, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                IBinder ibinder4 = parcel.readStrongBinder();
                IBinder ibinder10 = parcel.readStrongBinder();
                Rect rect;
                if(parcel.readInt() != 0)
                    rect = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect = null;
                createOverlayView(ibinder4, ibinder10, rect, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                IBinder ibinder5 = parcel.readStrongBinder();
                Rect rect1;
                if(parcel.readInt() != 0)
                    rect1 = (Rect)Rect.CREATOR.createFromParcel(parcel);
                else
                    rect1 = null;
                relayoutOverlayView(ibinder5, rect1, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                removeOverlayView(parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                unblockContent(parcel.readStrongBinder(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                IBinder ibinder6 = parcel.readStrongBinder();
                Uri uri1;
                if(parcel.readInt() != 0)
                    uri1 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri1 = null;
                timeShiftPlay(ibinder6, uri1, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                timeShiftPause(parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                timeShiftResume(parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                timeShiftSeekTo(parcel.readStrongBinder(), parcel.readLong(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                IBinder ibinder7 = parcel.readStrongBinder();
                PlaybackParams playbackparams;
                if(parcel.readInt() != 0)
                    playbackparams = (PlaybackParams)PlaybackParams.CREATOR.createFromParcel(parcel);
                else
                    playbackparams = null;
                timeShiftSetPlaybackParams(ibinder7, playbackparams, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                IBinder ibinder1 = parcel.readStrongBinder();
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                timeShiftEnablePositionTracking(ibinder1, flag5, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                IBinder ibinder8 = parcel.readStrongBinder();
                Uri uri2;
                if(parcel.readInt() != 0)
                    uri2 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri2 = null;
                startRecording(ibinder8, uri2, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                stopRecording(parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                parcel = getHardwareList();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                i = parcel.readInt();
                ITvInputHardwareCallback itvinputhardwarecallback = ITvInputHardwareCallback.Stub.asInterface(parcel.readStrongBinder());
                TvInputInfo tvinputinfo1;
                if(parcel.readInt() != 0)
                    tvinputinfo1 = (TvInputInfo)TvInputInfo.CREATOR.createFromParcel(parcel);
                else
                    tvinputinfo1 = null;
                parcel = acquireTvInputHardware(i, itvinputhardwarecallback, tvinputinfo1, parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                releaseTvInputHardware(parcel.readInt(), ITvInputHardware.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                parcel = getAvailableTvStreamConfigList(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 40: // '('
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                String s2 = parcel.readString();
                Surface surface1;
                boolean flag6;
                TvStreamConfig tvstreamconfig;
                if(parcel.readInt() != 0)
                    surface1 = (Surface)Surface.CREATOR.createFromParcel(parcel);
                else
                    surface1 = null;
                if(parcel.readInt() != 0)
                    tvstreamconfig = (TvStreamConfig)TvStreamConfig.CREATOR.createFromParcel(parcel);
                else
                    tvstreamconfig = null;
                flag6 = captureFrame(s2, surface1, tvstreamconfig, parcel.readInt());
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 41: // ')'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                boolean flag7 = isSingleSessionActive(parcel.readInt());
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 42: // '*'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                parcel = getDvbDeviceList();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 43: // '+'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                DvbDeviceInfo dvbdeviceinfo;
                if(parcel.readInt() != 0)
                    dvbdeviceinfo = (DvbDeviceInfo)DvbDeviceInfo.CREATOR.createFromParcel(parcel);
                else
                    dvbdeviceinfo = null;
                parcel = openDvbDevice(dvbdeviceinfo, parcel.readInt());
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

            case 44: // ','
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                Intent intent;
                if(parcel.readInt() != 0)
                    intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent = null;
                sendTvInputNotifyIntent(intent, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 45: // '-'
                parcel.enforceInterface("android.media.tv.ITvInputManager");
                break;
            }
            Uri uri3;
            if(parcel.readInt() != 0)
                uri3 = (Uri)Uri.CREATOR.createFromParcel(parcel);
            else
                uri3 = null;
            requestChannelBrowsable(uri3, parcel.readInt());
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.media.tv.ITvInputManager";
        static final int TRANSACTION_acquireTvInputHardware = 37;
        static final int TRANSACTION_addBlockedRating = 12;
        static final int TRANSACTION_captureFrame = 40;
        static final int TRANSACTION_createOverlayView = 24;
        static final int TRANSACTION_createSession = 14;
        static final int TRANSACTION_dispatchSurfaceChanged = 18;
        static final int TRANSACTION_getAvailableTvStreamConfigList = 39;
        static final int TRANSACTION_getBlockedRatings = 11;
        static final int TRANSACTION_getDvbDeviceList = 42;
        static final int TRANSACTION_getHardwareList = 36;
        static final int TRANSACTION_getTvContentRatingSystemList = 5;
        static final int TRANSACTION_getTvInputInfo = 2;
        static final int TRANSACTION_getTvInputList = 1;
        static final int TRANSACTION_getTvInputState = 4;
        static final int TRANSACTION_isParentalControlsEnabled = 8;
        static final int TRANSACTION_isRatingBlocked = 10;
        static final int TRANSACTION_isSingleSessionActive = 41;
        static final int TRANSACTION_openDvbDevice = 43;
        static final int TRANSACTION_registerCallback = 6;
        static final int TRANSACTION_relayoutOverlayView = 25;
        static final int TRANSACTION_releaseSession = 15;
        static final int TRANSACTION_releaseTvInputHardware = 38;
        static final int TRANSACTION_removeBlockedRating = 13;
        static final int TRANSACTION_removeOverlayView = 26;
        static final int TRANSACTION_requestChannelBrowsable = 45;
        static final int TRANSACTION_selectTrack = 22;
        static final int TRANSACTION_sendAppPrivateCommand = 23;
        static final int TRANSACTION_sendTvInputNotifyIntent = 44;
        static final int TRANSACTION_setCaptionEnabled = 21;
        static final int TRANSACTION_setMainSession = 16;
        static final int TRANSACTION_setParentalControlsEnabled = 9;
        static final int TRANSACTION_setSurface = 17;
        static final int TRANSACTION_setVolume = 19;
        static final int TRANSACTION_startRecording = 34;
        static final int TRANSACTION_stopRecording = 35;
        static final int TRANSACTION_timeShiftEnablePositionTracking = 33;
        static final int TRANSACTION_timeShiftPause = 29;
        static final int TRANSACTION_timeShiftPlay = 28;
        static final int TRANSACTION_timeShiftResume = 30;
        static final int TRANSACTION_timeShiftSeekTo = 31;
        static final int TRANSACTION_timeShiftSetPlaybackParams = 32;
        static final int TRANSACTION_tune = 20;
        static final int TRANSACTION_unblockContent = 27;
        static final int TRANSACTION_unregisterCallback = 7;
        static final int TRANSACTION_updateTvInputInfo = 3;

        public Stub()
        {
            attachInterface(this, "android.media.tv.ITvInputManager");
        }
    }

    private static class Stub.Proxy
        implements ITvInputManager
    {

        public ITvInputHardware acquireTvInputHardware(int i, ITvInputHardwareCallback itvinputhardwarecallback, TvInputInfo tvinputinfo, int j)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeInt(i);
            if(itvinputhardwarecallback == null)
                break MISSING_BLOCK_LABEL_38;
            ibinder = itvinputhardwarecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(tvinputinfo == null)
                break MISSING_BLOCK_LABEL_112;
            parcel.writeInt(1);
            tvinputinfo.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            itvinputhardwarecallback = ITvInputHardware.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return itvinputhardwarecallback;
            parcel.writeInt(0);
              goto _L1
            itvinputhardwarecallback;
            parcel1.recycle();
            parcel.recycle();
            throw itvinputhardwarecallback;
        }

        public void addBlockedRating(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean captureFrame(String s, Surface surface, TvStreamConfig tvstreamconfig, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeString(s);
            if(surface == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            surface.writeToParcel(parcel, 0);
_L3:
            if(tvstreamconfig == null)
                break MISSING_BLOCK_LABEL_136;
            parcel.writeInt(1);
            tvstreamconfig.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(40, parcel, parcel1, 0);
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
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void createOverlayView(IBinder ibinder, IBinder ibinder1, Rect rect, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeStrongBinder(ibinder1);
            if(rect == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void createSession(ITvInputClient itvinputclient, String s, boolean flag, int i, int j)
            throws RemoteException
        {
            IBinder ibinder;
            int k;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            k = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            if(itvinputclient == null)
                break MISSING_BLOCK_LABEL_35;
            ibinder = itvinputclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            if(flag)
                k = 1;
            parcel.writeInt(k);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            itvinputclient;
            parcel1.recycle();
            parcel.recycle();
            throw itvinputclient;
        }

        public void dispatchSurfaceChanged(IBinder ibinder, int i, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public List getAvailableTvStreamConfigList(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(39, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createTypedArrayList(TvStreamConfig.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public List getBlockedRatings(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getDvbDeviceList()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            mRemote.transact(42, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(DvbDeviceInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getHardwareList()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            mRemote.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(TvInputHardwareInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.tv.ITvInputManager";
        }

        public List getTvContentRatingSystemList(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(TvContentRatingSystemInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public TvInputInfo getTvInputInfo(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (TvInputInfo)TvInputInfo.CREATOR.createFromParcel(parcel1);
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

        public List getTvInputList(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(TvInputInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getTvInputState(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isParentalControlsEnabled(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isRatingBlocked(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
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

        public boolean isSingleSessionActive(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeInt(i);
            mRemote.transact(41, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParcelFileDescriptor openDvbDevice(DvbDeviceInfo dvbdeviceinfo, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            if(dvbdeviceinfo == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            dvbdeviceinfo.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            mRemote.transact(43, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_109;
            dvbdeviceinfo = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return dvbdeviceinfo;
_L2:
            parcel.writeInt(0);
              goto _L3
            dvbdeviceinfo;
            parcel1.recycle();
            parcel.recycle();
            throw dvbdeviceinfo;
            dvbdeviceinfo = null;
              goto _L4
        }

        public void registerCallback(ITvInputManagerCallback itvinputmanagercallback, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            if(itvinputmanagercallback == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = itvinputmanagercallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            itvinputmanagercallback;
            parcel1.recycle();
            parcel.recycle();
            throw itvinputmanagercallback;
        }

        public void relayoutOverlayView(IBinder ibinder, Rect rect, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            if(rect == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            rect.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void releaseSession(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void releaseTvInputHardware(int i, ITvInputHardware itvinputhardware, int j)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeInt(i);
            if(itvinputhardware == null)
                break MISSING_BLOCK_LABEL_38;
            ibinder = itvinputhardware.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(j);
            mRemote.transact(38, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            itvinputhardware;
            parcel1.recycle();
            parcel.recycle();
            throw itvinputhardware;
        }

        public void removeBlockedRating(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void removeOverlayView(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void requestChannelBrowsable(Uri uri, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            if(uri == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel1.recycle();
            parcel.recycle();
            throw uri;
        }

        public void selectTrack(IBinder ibinder, int i, String s, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void sendAppPrivateCommand(IBinder ibinder, String s, Bundle bundle, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void sendTvInputNotifyIntent(Intent intent, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            if(intent == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(44, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
        }

        public void setCaptionEnabled(IBinder ibinder, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setMainSession(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setParentalControlsEnabled(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
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

        public void setSurface(IBinder ibinder, Surface surface, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            if(surface == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            surface.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setVolume(IBinder ibinder, float f, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeFloat(f);
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void startRecording(IBinder ibinder, Uri uri, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            if(uri == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void stopRecording(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(35, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void timeShiftEnablePositionTracking(IBinder ibinder, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void timeShiftPause(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void timeShiftPlay(IBinder ibinder, Uri uri, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            if(uri == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void timeShiftResume(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void timeShiftSeekTo(IBinder ibinder, long l, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l);
            parcel.writeInt(i);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void timeShiftSetPlaybackParams(IBinder ibinder, PlaybackParams playbackparams, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            if(playbackparams == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            playbackparams.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(32, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void tune(IBinder ibinder, Uri uri, Bundle bundle, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_119;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
            parcel.writeInt(0);
              goto _L4
        }

        public void unblockContent(IBinder ibinder, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void unregisterCallback(ITvInputManagerCallback itvinputmanagercallback, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            if(itvinputmanagercallback == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = itvinputmanagercallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            itvinputmanagercallback;
            parcel1.recycle();
            parcel.recycle();
            throw itvinputmanagercallback;
        }

        public void updateTvInputInfo(TvInputInfo tvinputinfo, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputManager");
            if(tvinputinfo == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            tvinputinfo.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            tvinputinfo;
            parcel1.recycle();
            parcel.recycle();
            throw tvinputinfo;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract ITvInputHardware acquireTvInputHardware(int i, ITvInputHardwareCallback itvinputhardwarecallback, TvInputInfo tvinputinfo, int j)
        throws RemoteException;

    public abstract void addBlockedRating(String s, int i)
        throws RemoteException;

    public abstract boolean captureFrame(String s, Surface surface, TvStreamConfig tvstreamconfig, int i)
        throws RemoteException;

    public abstract void createOverlayView(IBinder ibinder, IBinder ibinder1, Rect rect, int i)
        throws RemoteException;

    public abstract void createSession(ITvInputClient itvinputclient, String s, boolean flag, int i, int j)
        throws RemoteException;

    public abstract void dispatchSurfaceChanged(IBinder ibinder, int i, int j, int k, int l)
        throws RemoteException;

    public abstract List getAvailableTvStreamConfigList(String s, int i)
        throws RemoteException;

    public abstract List getBlockedRatings(int i)
        throws RemoteException;

    public abstract List getDvbDeviceList()
        throws RemoteException;

    public abstract List getHardwareList()
        throws RemoteException;

    public abstract List getTvContentRatingSystemList(int i)
        throws RemoteException;

    public abstract TvInputInfo getTvInputInfo(String s, int i)
        throws RemoteException;

    public abstract List getTvInputList(int i)
        throws RemoteException;

    public abstract int getTvInputState(String s, int i)
        throws RemoteException;

    public abstract boolean isParentalControlsEnabled(int i)
        throws RemoteException;

    public abstract boolean isRatingBlocked(String s, int i)
        throws RemoteException;

    public abstract boolean isSingleSessionActive(int i)
        throws RemoteException;

    public abstract ParcelFileDescriptor openDvbDevice(DvbDeviceInfo dvbdeviceinfo, int i)
        throws RemoteException;

    public abstract void registerCallback(ITvInputManagerCallback itvinputmanagercallback, int i)
        throws RemoteException;

    public abstract void relayoutOverlayView(IBinder ibinder, Rect rect, int i)
        throws RemoteException;

    public abstract void releaseSession(IBinder ibinder, int i)
        throws RemoteException;

    public abstract void releaseTvInputHardware(int i, ITvInputHardware itvinputhardware, int j)
        throws RemoteException;

    public abstract void removeBlockedRating(String s, int i)
        throws RemoteException;

    public abstract void removeOverlayView(IBinder ibinder, int i)
        throws RemoteException;

    public abstract void requestChannelBrowsable(Uri uri, int i)
        throws RemoteException;

    public abstract void selectTrack(IBinder ibinder, int i, String s, int j)
        throws RemoteException;

    public abstract void sendAppPrivateCommand(IBinder ibinder, String s, Bundle bundle, int i)
        throws RemoteException;

    public abstract void sendTvInputNotifyIntent(Intent intent, int i)
        throws RemoteException;

    public abstract void setCaptionEnabled(IBinder ibinder, boolean flag, int i)
        throws RemoteException;

    public abstract void setMainSession(IBinder ibinder, int i)
        throws RemoteException;

    public abstract void setParentalControlsEnabled(boolean flag, int i)
        throws RemoteException;

    public abstract void setSurface(IBinder ibinder, Surface surface, int i)
        throws RemoteException;

    public abstract void setVolume(IBinder ibinder, float f, int i)
        throws RemoteException;

    public abstract void startRecording(IBinder ibinder, Uri uri, int i)
        throws RemoteException;

    public abstract void stopRecording(IBinder ibinder, int i)
        throws RemoteException;

    public abstract void timeShiftEnablePositionTracking(IBinder ibinder, boolean flag, int i)
        throws RemoteException;

    public abstract void timeShiftPause(IBinder ibinder, int i)
        throws RemoteException;

    public abstract void timeShiftPlay(IBinder ibinder, Uri uri, int i)
        throws RemoteException;

    public abstract void timeShiftResume(IBinder ibinder, int i)
        throws RemoteException;

    public abstract void timeShiftSeekTo(IBinder ibinder, long l, int i)
        throws RemoteException;

    public abstract void timeShiftSetPlaybackParams(IBinder ibinder, PlaybackParams playbackparams, int i)
        throws RemoteException;

    public abstract void tune(IBinder ibinder, Uri uri, Bundle bundle, int i)
        throws RemoteException;

    public abstract void unblockContent(IBinder ibinder, String s, int i)
        throws RemoteException;

    public abstract void unregisterCallback(ITvInputManagerCallback itvinputmanagercallback, int i)
        throws RemoteException;

    public abstract void updateTvInputInfo(TvInputInfo tvinputinfo, int i)
        throws RemoteException;
}
