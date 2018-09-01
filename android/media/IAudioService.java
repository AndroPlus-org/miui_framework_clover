// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.bluetooth.BluetoothDevice;
import android.media.audiopolicy.AudioPolicyConfig;
import android.media.audiopolicy.IAudioPolicyCallback;
import android.os.*;
import java.util.List;

// Referenced classes of package android.media:
//            IAudioFocusDispatcher, AudioAttributes, AudioFocusInfo, IRingtonePlayer, 
//            IVolumeController, IPlaybackConfigDispatcher, IRecordingConfigDispatcher, VolumePolicy, 
//            IAudioRoutesObserver, AudioRoutesInfo, AudioPlaybackConfiguration, AudioRecordingConfiguration

public interface IAudioService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAudioService
    {

        public static IAudioService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IAudioService");
            if(iinterface != null && (iinterface instanceof IAudioService))
                return (IAudioService)iinterface;
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
                parcel1.writeString("android.media.IAudioService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IAudioService");
                adjustSuggestedStreamVolume(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.IAudioService");
                adjustStreamVolume(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.IAudioService");
                setStreamVolume(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag = isStreamMute(parcel.readInt());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                forceRemoteSubmixFullVolume(flag1, parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag2 = isMasterMute();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                setMasterMute(flag3, parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.media.IAudioService");
                i = getStreamVolume(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.media.IAudioService");
                i = getStreamMinVolume(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.media.IAudioService");
                i = getStreamMaxVolume(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.media.IAudioService");
                i = getLastAudibleStreamVolume(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                setMicrophoneMute(flag4, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.media.IAudioService");
                setRingerModeExternal(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.media.IAudioService");
                setRingerModeInternal(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.media.IAudioService");
                i = getRingerModeExternal();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.media.IAudioService");
                i = getRingerModeInternal();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag5 = isValidRingerMode(parcel.readInt());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.media.IAudioService");
                setVibrateSetting(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.media.IAudioService");
                i = getVibrateSetting(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag6 = shouldVibrate(parcel.readInt());
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.media.IAudioService");
                setMode(parcel.readInt(), parcel.readStrongBinder(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.media.IAudioService");
                i = getMode();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.media.IAudioService");
                playSoundEffect(parcel.readInt());
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.media.IAudioService");
                playSoundEffectVolume(parcel.readInt(), parcel.readFloat());
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag7 = loadSoundEffects();
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.media.IAudioService");
                unloadSoundEffects();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.media.IAudioService");
                reloadAudioSettings();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.media.IAudioService");
                parcel1 = parcel.readString();
                boolean flag8;
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                avrcpSupportsAbsoluteVolume(parcel1, flag8);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag9;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                setSpeakerphoneOn(flag9);
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag10 = isSpeakerphoneOn();
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag11;
                if(parcel.readInt() != 0)
                    flag11 = true;
                else
                    flag11 = false;
                setBluetoothScoOn(flag11);
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag12 = isBluetoothScoOn();
                parcel1.writeNoException();
                if(flag12)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag13;
                if(parcel.readInt() != 0)
                    flag13 = true;
                else
                    flag13 = false;
                setBluetoothA2dpOn(flag13);
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag14 = isBluetoothA2dpOn();
                parcel1.writeNoException();
                if(flag14)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.media.IAudioService");
                AudioAttributes audioattributes;
                if(parcel.readInt() != 0)
                    audioattributes = (AudioAttributes)AudioAttributes.CREATOR.createFromParcel(parcel);
                else
                    audioattributes = null;
                i = requestAudioFocus(audioattributes, parcel.readInt(), parcel.readStrongBinder(), IAudioFocusDispatcher.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readInt(), android.media.audiopolicy.IAudioPolicyCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.media.IAudioService");
                IAudioFocusDispatcher iaudiofocusdispatcher = IAudioFocusDispatcher.Stub.asInterface(parcel.readStrongBinder());
                String s = parcel.readString();
                AudioAttributes audioattributes1;
                if(parcel.readInt() != 0)
                    audioattributes1 = (AudioAttributes)AudioAttributes.CREATOR.createFromParcel(parcel);
                else
                    audioattributes1 = null;
                i = abandonAudioFocus(iaudiofocusdispatcher, s, audioattributes1, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.media.IAudioService");
                unregisterAudioFocusClient(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.media.IAudioService");
                i = getCurrentAudioFocus();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.media.IAudioService");
                startBluetoothSco(parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 40: // '('
                parcel.enforceInterface("android.media.IAudioService");
                startBluetoothScoVirtualCall(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 41: // ')'
                parcel.enforceInterface("android.media.IAudioService");
                stopBluetoothSco(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 42: // '*'
                parcel.enforceInterface("android.media.IAudioService");
                forceVolumeControlStream(parcel.readInt(), parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 43: // '+'
                parcel.enforceInterface("android.media.IAudioService");
                setRingtonePlayer(IRingtonePlayer.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 44: // ','
                parcel.enforceInterface("android.media.IAudioService");
                parcel = getRingtonePlayer();
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 45: // '-'
                parcel.enforceInterface("android.media.IAudioService");
                i = getUiSoundsStreamType();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 46: // '.'
                parcel.enforceInterface("android.media.IAudioService");
                setWiredDeviceConnectionState(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 47: // '/'
                parcel.enforceInterface("android.media.IAudioService");
                BluetoothDevice bluetoothdevice;
                if(parcel.readInt() != 0)
                    bluetoothdevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice = null;
                i = setBluetoothA2dpDeviceConnectionState(bluetoothdevice, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 48: // '0'
                parcel.enforceInterface("android.media.IAudioService");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                handleBluetoothA2dpDeviceConfigChange(parcel);
                parcel1.writeNoException();
                return true;

            case 49: // '1'
                parcel.enforceInterface("android.media.IAudioService");
                parcel = startWatchingRoutes(IAudioRoutesObserver.Stub.asInterface(parcel.readStrongBinder()));
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

            case 50: // '2'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag15 = isCameraSoundForced();
                parcel1.writeNoException();
                if(flag15)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 51: // '3'
                parcel.enforceInterface("android.media.IAudioService");
                setVolumeController(IVolumeController.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 52: // '4'
                parcel.enforceInterface("android.media.IAudioService");
                IVolumeController ivolumecontroller = IVolumeController.Stub.asInterface(parcel.readStrongBinder());
                boolean flag16;
                if(parcel.readInt() != 0)
                    flag16 = true;
                else
                    flag16 = false;
                notifyVolumeControllerVisible(ivolumecontroller, flag16);
                parcel1.writeNoException();
                return true;

            case 53: // '5'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag17 = isStreamAffectedByRingerMode(parcel.readInt());
                parcel1.writeNoException();
                if(flag17)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 54: // '6'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag18 = isStreamAffectedByMute(parcel.readInt());
                parcel1.writeNoException();
                if(flag18)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 55: // '7'
                parcel.enforceInterface("android.media.IAudioService");
                disableSafeMediaVolume(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 56: // '8'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag19;
                if(parcel.readInt() != 0)
                    flag19 = true;
                else
                    flag19 = false;
                i = setHdmiSystemAudioSupported(flag19);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 57: // '9'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag20 = isHdmiSystemAudioSupported();
                parcel1.writeNoException();
                if(flag20)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 58: // ':'
                parcel.enforceInterface("android.media.IAudioService");
                boolean flag21;
                AudioPolicyConfig audiopolicyconfig;
                IAudioPolicyCallback iaudiopolicycallback;
                boolean flag23;
                if(parcel.readInt() != 0)
                    audiopolicyconfig = (AudioPolicyConfig)AudioPolicyConfig.CREATOR.createFromParcel(parcel);
                else
                    audiopolicyconfig = null;
                iaudiopolicycallback = android.media.audiopolicy.IAudioPolicyCallback.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    flag21 = true;
                else
                    flag21 = false;
                if(parcel.readInt() != 0)
                    flag23 = true;
                else
                    flag23 = false;
                parcel = registerAudioPolicy(audiopolicyconfig, iaudiopolicycallback, flag21, flag23);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 59: // ';'
                parcel.enforceInterface("android.media.IAudioService");
                unregisterAudioPolicyAsync(android.media.audiopolicy.IAudioPolicyCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 60: // '<'
                parcel.enforceInterface("android.media.IAudioService");
                i = setFocusPropertiesForPolicy(parcel.readInt(), android.media.audiopolicy.IAudioPolicyCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 61: // '='
                parcel.enforceInterface("android.media.IAudioService");
                if(parcel.readInt() != 0)
                    parcel = (VolumePolicy)VolumePolicy.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setVolumePolicy(parcel);
                parcel1.writeNoException();
                return true;

            case 62: // '>'
                parcel.enforceInterface("android.media.IAudioService");
                registerRecordingCallback(IRecordingConfigDispatcher.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 63: // '?'
                parcel.enforceInterface("android.media.IAudioService");
                unregisterRecordingCallback(IRecordingConfigDispatcher.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 64: // '@'
                parcel.enforceInterface("android.media.IAudioService");
                parcel = getActiveRecordingConfigurations();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 65: // 'A'
                parcel.enforceInterface("android.media.IAudioService");
                registerPlaybackCallback(IPlaybackConfigDispatcher.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 66: // 'B'
                parcel.enforceInterface("android.media.IAudioService");
                unregisterPlaybackCallback(IPlaybackConfigDispatcher.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 67: // 'C'
                parcel.enforceInterface("android.media.IAudioService");
                parcel = getActivePlaybackConfigurations();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 68: // 'D'
                parcel.enforceInterface("android.media.IAudioService");
                if(parcel.readInt() != 0)
                    parcel = (PlayerBase.PlayerIdCard)PlayerBase.PlayerIdCard.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = trackPlayer(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 69: // 'E'
                parcel.enforceInterface("android.media.IAudioService");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (AudioAttributes)AudioAttributes.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                playerAttributes(i, parcel);
                return true;

            case 70: // 'F'
                parcel.enforceInterface("android.media.IAudioService");
                playerEvent(parcel.readInt(), parcel.readInt());
                return true;

            case 71: // 'G'
                parcel.enforceInterface("android.media.IAudioService");
                releasePlayer(parcel.readInt());
                return true;

            case 72: // 'H'
                parcel.enforceInterface("android.media.IAudioService");
                disableRingtoneSync(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 73: // 'I'
                parcel.enforceInterface("android.media.IAudioService");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (AudioAttributes)AudioAttributes.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getFocusRampTimeMs(i, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 74: // 'J'
                parcel.enforceInterface("android.media.IAudioService");
                AudioFocusInfo audiofocusinfo;
                if(parcel.readInt() != 0)
                    audiofocusinfo = (AudioFocusInfo)AudioFocusInfo.CREATOR.createFromParcel(parcel);
                else
                    audiofocusinfo = null;
                i = dispatchFocusChange(audiofocusinfo, parcel.readInt(), android.media.audiopolicy.IAudioPolicyCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 75: // 'K'
                parcel.enforceInterface("android.media.IAudioService");
                i = parcel.readInt();
                boolean flag22;
                if(parcel.readInt() != 0)
                    flag22 = true;
                else
                    flag22 = false;
                playerHasOpPlayAudio(i, flag22);
                return true;

            case 76: // 'L'
                parcel.enforceInterface("android.media.IAudioService");
                break;
            }
            ParcelFileDescriptor parcelfiledescriptor;
            if(parcel.readInt() != 0)
                parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
            else
                parcelfiledescriptor = null;
            parcel = createAudioRecordForLoopback(parcelfiledescriptor, parcel.readLong());
            parcel1.writeNoException();
            parcel1.writeStrongBinder(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.IAudioService";
        static final int TRANSACTION_abandonAudioFocus = 36;
        static final int TRANSACTION_adjustStreamVolume = 2;
        static final int TRANSACTION_adjustSuggestedStreamVolume = 1;
        static final int TRANSACTION_avrcpSupportsAbsoluteVolume = 28;
        static final int TRANSACTION_createAudioRecordForLoopback = 76;
        static final int TRANSACTION_disableRingtoneSync = 72;
        static final int TRANSACTION_disableSafeMediaVolume = 55;
        static final int TRANSACTION_dispatchFocusChange = 74;
        static final int TRANSACTION_forceRemoteSubmixFullVolume = 5;
        static final int TRANSACTION_forceVolumeControlStream = 42;
        static final int TRANSACTION_getActivePlaybackConfigurations = 67;
        static final int TRANSACTION_getActiveRecordingConfigurations = 64;
        static final int TRANSACTION_getCurrentAudioFocus = 38;
        static final int TRANSACTION_getFocusRampTimeMs = 73;
        static final int TRANSACTION_getLastAudibleStreamVolume = 11;
        static final int TRANSACTION_getMode = 22;
        static final int TRANSACTION_getRingerModeExternal = 15;
        static final int TRANSACTION_getRingerModeInternal = 16;
        static final int TRANSACTION_getRingtonePlayer = 44;
        static final int TRANSACTION_getStreamMaxVolume = 10;
        static final int TRANSACTION_getStreamMinVolume = 9;
        static final int TRANSACTION_getStreamVolume = 8;
        static final int TRANSACTION_getUiSoundsStreamType = 45;
        static final int TRANSACTION_getVibrateSetting = 19;
        static final int TRANSACTION_handleBluetoothA2dpDeviceConfigChange = 48;
        static final int TRANSACTION_isBluetoothA2dpOn = 34;
        static final int TRANSACTION_isBluetoothScoOn = 32;
        static final int TRANSACTION_isCameraSoundForced = 50;
        static final int TRANSACTION_isHdmiSystemAudioSupported = 57;
        static final int TRANSACTION_isMasterMute = 6;
        static final int TRANSACTION_isSpeakerphoneOn = 30;
        static final int TRANSACTION_isStreamAffectedByMute = 54;
        static final int TRANSACTION_isStreamAffectedByRingerMode = 53;
        static final int TRANSACTION_isStreamMute = 4;
        static final int TRANSACTION_isValidRingerMode = 17;
        static final int TRANSACTION_loadSoundEffects = 25;
        static final int TRANSACTION_notifyVolumeControllerVisible = 52;
        static final int TRANSACTION_playSoundEffect = 23;
        static final int TRANSACTION_playSoundEffectVolume = 24;
        static final int TRANSACTION_playerAttributes = 69;
        static final int TRANSACTION_playerEvent = 70;
        static final int TRANSACTION_playerHasOpPlayAudio = 75;
        static final int TRANSACTION_registerAudioPolicy = 58;
        static final int TRANSACTION_registerPlaybackCallback = 65;
        static final int TRANSACTION_registerRecordingCallback = 62;
        static final int TRANSACTION_releasePlayer = 71;
        static final int TRANSACTION_reloadAudioSettings = 27;
        static final int TRANSACTION_requestAudioFocus = 35;
        static final int TRANSACTION_setBluetoothA2dpDeviceConnectionState = 47;
        static final int TRANSACTION_setBluetoothA2dpOn = 33;
        static final int TRANSACTION_setBluetoothScoOn = 31;
        static final int TRANSACTION_setFocusPropertiesForPolicy = 60;
        static final int TRANSACTION_setHdmiSystemAudioSupported = 56;
        static final int TRANSACTION_setMasterMute = 7;
        static final int TRANSACTION_setMicrophoneMute = 12;
        static final int TRANSACTION_setMode = 21;
        static final int TRANSACTION_setRingerModeExternal = 13;
        static final int TRANSACTION_setRingerModeInternal = 14;
        static final int TRANSACTION_setRingtonePlayer = 43;
        static final int TRANSACTION_setSpeakerphoneOn = 29;
        static final int TRANSACTION_setStreamVolume = 3;
        static final int TRANSACTION_setVibrateSetting = 18;
        static final int TRANSACTION_setVolumeController = 51;
        static final int TRANSACTION_setVolumePolicy = 61;
        static final int TRANSACTION_setWiredDeviceConnectionState = 46;
        static final int TRANSACTION_shouldVibrate = 20;
        static final int TRANSACTION_startBluetoothSco = 39;
        static final int TRANSACTION_startBluetoothScoVirtualCall = 40;
        static final int TRANSACTION_startWatchingRoutes = 49;
        static final int TRANSACTION_stopBluetoothSco = 41;
        static final int TRANSACTION_trackPlayer = 68;
        static final int TRANSACTION_unloadSoundEffects = 26;
        static final int TRANSACTION_unregisterAudioFocusClient = 37;
        static final int TRANSACTION_unregisterAudioPolicyAsync = 59;
        static final int TRANSACTION_unregisterPlaybackCallback = 66;
        static final int TRANSACTION_unregisterRecordingCallback = 63;

        public Stub()
        {
            attachInterface(this, "android.media.IAudioService");
        }
    }

    private static class Stub.Proxy
        implements IAudioService
    {

        public int abandonAudioFocus(IAudioFocusDispatcher iaudiofocusdispatcher, String s, AudioAttributes audioattributes, String s1)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(iaudiofocusdispatcher == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = iaudiofocusdispatcher.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            if(audioattributes == null)
                break MISSING_BLOCK_LABEL_111;
            parcel.writeInt(1);
            audioattributes.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s1);
            mRemote.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            iaudiofocusdispatcher;
            parcel1.recycle();
            parcel.recycle();
            throw iaudiofocusdispatcher;
        }

        public void adjustStreamVolume(int i, int j, int k, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void adjustSuggestedStreamVolume(int i, int j, int k, String s, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void avrcpSupportsAbsoluteVolume(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(28, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public IBinder createAudioRecordForLoopback(ParcelFileDescriptor parcelfiledescriptor, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L1:
            parcel.writeLong(l);
            mRemote.transact(76, parcel, parcel1, 0);
            parcel1.readException();
            parcelfiledescriptor = parcel1.readStrongBinder();
            parcel1.recycle();
            parcel.recycle();
            return parcelfiledescriptor;
            parcel.writeInt(0);
              goto _L1
            parcelfiledescriptor;
            parcel1.recycle();
            parcel.recycle();
            throw parcelfiledescriptor;
        }

        public void disableRingtoneSync(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            mRemote.transact(72, parcel, parcel1, 0);
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

        public void disableSafeMediaVolume(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeString(s);
            mRemote.transact(55, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int dispatchFocusChange(AudioFocusInfo audiofocusinfo, int i, IAudioPolicyCallback iaudiopolicycallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(audiofocusinfo == null)
                break MISSING_BLOCK_LABEL_103;
            parcel.writeInt(1);
            audiofocusinfo.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            audiofocusinfo = obj;
            if(iaudiopolicycallback == null)
                break MISSING_BLOCK_LABEL_57;
            audiofocusinfo = iaudiopolicycallback.asBinder();
            parcel.writeStrongBinder(audiofocusinfo);
            mRemote.transact(74, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            audiofocusinfo;
            parcel1.recycle();
            parcel.recycle();
            throw audiofocusinfo;
        }

        public void forceRemoteSubmixFullVolume(boolean flag, IBinder ibinder)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void forceVolumeControlStream(int i, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(42, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public List getActivePlaybackConfigurations()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(67, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(AudioPlaybackConfiguration.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getActiveRecordingConfigurations()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(64, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(AudioRecordingConfiguration.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getCurrentAudioFocus()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(38, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getFocusRampTimeMs(int i, AudioAttributes audioattributes)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            if(audioattributes == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            audioattributes.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(73, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            audioattributes;
            parcel1.recycle();
            parcel.recycle();
            throw audioattributes;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IAudioService";
        }

        public int getLastAudibleStreamVolume(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getRingerModeExternal()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getRingerModeInternal()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IRingtonePlayer getRingtonePlayer()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IRingtonePlayer iringtoneplayer;
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(44, parcel, parcel1, 0);
            parcel1.readException();
            iringtoneplayer = IRingtonePlayer.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return iringtoneplayer;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getStreamMaxVolume(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getStreamMinVolume(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getStreamVolume(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getUiSoundsStreamType()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getVibrateSetting(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void handleBluetoothA2dpDeviceConfigChange(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(48, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean isBluetoothA2dpOn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(34, parcel, parcel1, 0);
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

        public boolean isBluetoothScoOn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(32, parcel, parcel1, 0);
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

        public boolean isCameraSoundForced()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(50, parcel, parcel1, 0);
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

        public boolean isHdmiSystemAudioSupported()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(57, parcel, parcel1, 0);
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

        public boolean isMasterMute()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(6, parcel, parcel1, 0);
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

        public boolean isSpeakerphoneOn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(30, parcel, parcel1, 0);
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

        public boolean isStreamAffectedByMute(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            mRemote.transact(54, parcel, parcel1, 0);
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

        public boolean isStreamAffectedByRingerMode(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            mRemote.transact(53, parcel, parcel1, 0);
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

        public boolean isStreamMute(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isValidRingerMode(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            mRemote.transact(17, parcel, parcel1, 0);
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

        public boolean loadSoundEffects()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(25, parcel, parcel1, 0);
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

        public void notifyVolumeControllerVisible(IVolumeController ivolumecontroller, boolean flag)
            throws RemoteException
        {
            IBinder ibinder;
            int i;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(ivolumecontroller == null)
                break MISSING_BLOCK_LABEL_33;
            ibinder = ivolumecontroller.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(52, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ivolumecontroller;
            parcel1.recycle();
            parcel.recycle();
            throw ivolumecontroller;
        }

        public void playSoundEffect(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            mRemote.transact(23, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void playSoundEffectVolume(int i, float f)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            parcel.writeFloat(f);
            mRemote.transact(24, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void playerAttributes(int i, AudioAttributes audioattributes)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            if(audioattributes == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            audioattributes.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(69, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            audioattributes;
            parcel.recycle();
            throw audioattributes;
        }

        public void playerEvent(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(70, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void playerHasOpPlayAudio(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(75, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String registerAudioPolicy(AudioPolicyConfig audiopolicyconfig, IAudioPolicyCallback iaudiopolicycallback, boolean flag, boolean flag1)
            throws RemoteException
        {
            Object obj;
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(audiopolicyconfig == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            audiopolicyconfig.writeToParcel(parcel, 0);
_L4:
            audiopolicyconfig = obj;
            if(iaudiopolicycallback == null)
                break MISSING_BLOCK_LABEL_54;
            audiopolicyconfig = iaudiopolicycallback.asBinder();
            parcel.writeStrongBinder(audiopolicyconfig);
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(58, parcel, parcel1, 0);
            parcel1.readException();
            audiopolicyconfig = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return audiopolicyconfig;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            audiopolicyconfig;
            parcel1.recycle();
            parcel.recycle();
            throw audiopolicyconfig;
        }

        public void registerPlaybackCallback(IPlaybackConfigDispatcher iplaybackconfigdispatcher)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(iplaybackconfigdispatcher == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iplaybackconfigdispatcher.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(65, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iplaybackconfigdispatcher;
            parcel1.recycle();
            parcel.recycle();
            throw iplaybackconfigdispatcher;
        }

        public void registerRecordingCallback(IRecordingConfigDispatcher irecordingconfigdispatcher)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(irecordingconfigdispatcher == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = irecordingconfigdispatcher.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(62, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            irecordingconfigdispatcher;
            parcel1.recycle();
            parcel.recycle();
            throw irecordingconfigdispatcher;
        }

        public void releasePlayer(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            mRemote.transact(71, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void reloadAudioSettings()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(27, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public int requestAudioFocus(AudioAttributes audioattributes, int i, IBinder ibinder, IAudioFocusDispatcher iaudiofocusdispatcher, String s, String s1, int j, 
                IAudioPolicyCallback iaudiopolicycallback, int k)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(audioattributes == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            audioattributes.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            if(iaudiofocusdispatcher == null)
                break MISSING_BLOCK_LABEL_180;
            audioattributes = iaudiofocusdispatcher.asBinder();
_L4:
            parcel.writeStrongBinder(audioattributes);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(j);
            audioattributes = obj;
            if(iaudiopolicycallback == null)
                break MISSING_BLOCK_LABEL_105;
            audioattributes = iaudiopolicycallback.asBinder();
            parcel.writeStrongBinder(audioattributes);
            parcel.writeInt(k);
            mRemote.transact(35, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L3
            audioattributes;
            parcel1.recycle();
            parcel.recycle();
            throw audioattributes;
            audioattributes = null;
              goto _L4
        }

        public int setBluetoothA2dpDeviceConnectionState(BluetoothDevice bluetoothdevice, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(47, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public void setBluetoothA2dpOn(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(33, parcel, parcel1, 0);
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

        public void setBluetoothScoOn(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(31, parcel, parcel1, 0);
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

        public int setFocusPropertiesForPolicy(int i, IAudioPolicyCallback iaudiopolicycallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            if(iaudiopolicycallback == null)
                break MISSING_BLOCK_LABEL_36;
            ibinder = iaudiopolicycallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(60, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            iaudiopolicycallback;
            parcel1.recycle();
            parcel.recycle();
            throw iaudiopolicycallback;
        }

        public int setHdmiSystemAudioSupported(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(56, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setMasterMute(boolean flag, int i, String s, int j)
            throws RemoteException
        {
            int k;
            Parcel parcel;
            Parcel parcel1;
            k = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(flag)
                k = 1;
            parcel.writeInt(k);
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setMicrophoneMute(boolean flag, String s, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(flag)
                j = 1;
            parcel.writeInt(j);
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

        public void setMode(int i, IBinder ibinder, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
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

        public void setRingerModeExternal(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            parcel.writeString(s);
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

        public void setRingerModeInternal(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setRingtonePlayer(IRingtonePlayer iringtoneplayer)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(iringtoneplayer == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iringtoneplayer.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(43, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iringtoneplayer;
            parcel1.recycle();
            parcel.recycle();
            throw iringtoneplayer;
        }

        public void setSpeakerphoneOn(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(29, parcel, parcel1, 0);
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

        public void setStreamVolume(int i, int j, int k, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setVibrateSetting(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(18, parcel, parcel1, 0);
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

        public void setVolumeController(IVolumeController ivolumecontroller)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(ivolumecontroller == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ivolumecontroller.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(51, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ivolumecontroller;
            parcel1.recycle();
            parcel.recycle();
            throw ivolumecontroller;
        }

        public void setVolumePolicy(VolumePolicy volumepolicy)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(volumepolicy == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            volumepolicy.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(61, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            volumepolicy;
            parcel1.recycle();
            parcel.recycle();
            throw volumepolicy;
        }

        public void setWiredDeviceConnectionState(int i, int j, String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(46, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean shouldVibrate(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeInt(i);
            mRemote.transact(20, parcel, parcel1, 0);
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

        public void startBluetoothSco(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(39, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void startBluetoothScoVirtualCall(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(40, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public AudioRoutesInfo startWatchingRoutes(IAudioRoutesObserver iaudioroutesobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(iaudioroutesobserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iaudioroutesobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(49, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            iaudioroutesobserver = (AudioRoutesInfo)AudioRoutesInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return iaudioroutesobserver;
_L2:
            iaudioroutesobserver = null;
            if(true) goto _L4; else goto _L3
_L3:
            iaudioroutesobserver;
            parcel1.recycle();
            parcel.recycle();
            throw iaudioroutesobserver;
        }

        public void stopBluetoothSco(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(41, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int trackPlayer(PlayerBase.PlayerIdCard playeridcard)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(playeridcard == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            playeridcard.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(68, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            playeridcard;
            parcel1.recycle();
            parcel.recycle();
            throw playeridcard;
        }

        public void unloadSoundEffects()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            mRemote.transact(26, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void unregisterAudioFocusClient(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            parcel.writeString(s);
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void unregisterAudioPolicyAsync(IAudioPolicyCallback iaudiopolicycallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(iaudiopolicycallback == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = iaudiopolicycallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(59, parcel, null, 1);
            parcel.recycle();
            return;
            iaudiopolicycallback;
            parcel.recycle();
            throw iaudiopolicycallback;
        }

        public void unregisterPlaybackCallback(IPlaybackConfigDispatcher iplaybackconfigdispatcher)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(iplaybackconfigdispatcher == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = iplaybackconfigdispatcher.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(66, parcel, null, 1);
            parcel.recycle();
            return;
            iplaybackconfigdispatcher;
            parcel.recycle();
            throw iplaybackconfigdispatcher;
        }

        public void unregisterRecordingCallback(IRecordingConfigDispatcher irecordingconfigdispatcher)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioService");
            if(irecordingconfigdispatcher == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = irecordingconfigdispatcher.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(63, parcel, null, 1);
            parcel.recycle();
            return;
            irecordingconfigdispatcher;
            parcel.recycle();
            throw irecordingconfigdispatcher;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int abandonAudioFocus(IAudioFocusDispatcher iaudiofocusdispatcher, String s, AudioAttributes audioattributes, String s1)
        throws RemoteException;

    public abstract void adjustStreamVolume(int i, int j, int k, String s)
        throws RemoteException;

    public abstract void adjustSuggestedStreamVolume(int i, int j, int k, String s, String s1)
        throws RemoteException;

    public abstract void avrcpSupportsAbsoluteVolume(String s, boolean flag)
        throws RemoteException;

    public abstract IBinder createAudioRecordForLoopback(ParcelFileDescriptor parcelfiledescriptor, long l)
        throws RemoteException;

    public abstract void disableRingtoneSync(int i)
        throws RemoteException;

    public abstract void disableSafeMediaVolume(String s)
        throws RemoteException;

    public abstract int dispatchFocusChange(AudioFocusInfo audiofocusinfo, int i, IAudioPolicyCallback iaudiopolicycallback)
        throws RemoteException;

    public abstract void forceRemoteSubmixFullVolume(boolean flag, IBinder ibinder)
        throws RemoteException;

    public abstract void forceVolumeControlStream(int i, IBinder ibinder)
        throws RemoteException;

    public abstract List getActivePlaybackConfigurations()
        throws RemoteException;

    public abstract List getActiveRecordingConfigurations()
        throws RemoteException;

    public abstract int getCurrentAudioFocus()
        throws RemoteException;

    public abstract int getFocusRampTimeMs(int i, AudioAttributes audioattributes)
        throws RemoteException;

    public abstract int getLastAudibleStreamVolume(int i)
        throws RemoteException;

    public abstract int getMode()
        throws RemoteException;

    public abstract int getRingerModeExternal()
        throws RemoteException;

    public abstract int getRingerModeInternal()
        throws RemoteException;

    public abstract IRingtonePlayer getRingtonePlayer()
        throws RemoteException;

    public abstract int getStreamMaxVolume(int i)
        throws RemoteException;

    public abstract int getStreamMinVolume(int i)
        throws RemoteException;

    public abstract int getStreamVolume(int i)
        throws RemoteException;

    public abstract int getUiSoundsStreamType()
        throws RemoteException;

    public abstract int getVibrateSetting(int i)
        throws RemoteException;

    public abstract void handleBluetoothA2dpDeviceConfigChange(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean isBluetoothA2dpOn()
        throws RemoteException;

    public abstract boolean isBluetoothScoOn()
        throws RemoteException;

    public abstract boolean isCameraSoundForced()
        throws RemoteException;

    public abstract boolean isHdmiSystemAudioSupported()
        throws RemoteException;

    public abstract boolean isMasterMute()
        throws RemoteException;

    public abstract boolean isSpeakerphoneOn()
        throws RemoteException;

    public abstract boolean isStreamAffectedByMute(int i)
        throws RemoteException;

    public abstract boolean isStreamAffectedByRingerMode(int i)
        throws RemoteException;

    public abstract boolean isStreamMute(int i)
        throws RemoteException;

    public abstract boolean isValidRingerMode(int i)
        throws RemoteException;

    public abstract boolean loadSoundEffects()
        throws RemoteException;

    public abstract void notifyVolumeControllerVisible(IVolumeController ivolumecontroller, boolean flag)
        throws RemoteException;

    public abstract void playSoundEffect(int i)
        throws RemoteException;

    public abstract void playSoundEffectVolume(int i, float f)
        throws RemoteException;

    public abstract void playerAttributes(int i, AudioAttributes audioattributes)
        throws RemoteException;

    public abstract void playerEvent(int i, int j)
        throws RemoteException;

    public abstract void playerHasOpPlayAudio(int i, boolean flag)
        throws RemoteException;

    public abstract String registerAudioPolicy(AudioPolicyConfig audiopolicyconfig, IAudioPolicyCallback iaudiopolicycallback, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void registerPlaybackCallback(IPlaybackConfigDispatcher iplaybackconfigdispatcher)
        throws RemoteException;

    public abstract void registerRecordingCallback(IRecordingConfigDispatcher irecordingconfigdispatcher)
        throws RemoteException;

    public abstract void releasePlayer(int i)
        throws RemoteException;

    public abstract void reloadAudioSettings()
        throws RemoteException;

    public abstract int requestAudioFocus(AudioAttributes audioattributes, int i, IBinder ibinder, IAudioFocusDispatcher iaudiofocusdispatcher, String s, String s1, int j, 
            IAudioPolicyCallback iaudiopolicycallback, int k)
        throws RemoteException;

    public abstract int setBluetoothA2dpDeviceConnectionState(BluetoothDevice bluetoothdevice, int i, int j)
        throws RemoteException;

    public abstract void setBluetoothA2dpOn(boolean flag)
        throws RemoteException;

    public abstract void setBluetoothScoOn(boolean flag)
        throws RemoteException;

    public abstract int setFocusPropertiesForPolicy(int i, IAudioPolicyCallback iaudiopolicycallback)
        throws RemoteException;

    public abstract int setHdmiSystemAudioSupported(boolean flag)
        throws RemoteException;

    public abstract void setMasterMute(boolean flag, int i, String s, int j)
        throws RemoteException;

    public abstract void setMicrophoneMute(boolean flag, String s, int i)
        throws RemoteException;

    public abstract void setMode(int i, IBinder ibinder, String s)
        throws RemoteException;

    public abstract void setRingerModeExternal(int i, String s)
        throws RemoteException;

    public abstract void setRingerModeInternal(int i, String s)
        throws RemoteException;

    public abstract void setRingtonePlayer(IRingtonePlayer iringtoneplayer)
        throws RemoteException;

    public abstract void setSpeakerphoneOn(boolean flag)
        throws RemoteException;

    public abstract void setStreamVolume(int i, int j, int k, String s)
        throws RemoteException;

    public abstract void setVibrateSetting(int i, int j)
        throws RemoteException;

    public abstract void setVolumeController(IVolumeController ivolumecontroller)
        throws RemoteException;

    public abstract void setVolumePolicy(VolumePolicy volumepolicy)
        throws RemoteException;

    public abstract void setWiredDeviceConnectionState(int i, int j, String s, String s1, String s2)
        throws RemoteException;

    public abstract boolean shouldVibrate(int i)
        throws RemoteException;

    public abstract void startBluetoothSco(IBinder ibinder, int i)
        throws RemoteException;

    public abstract void startBluetoothScoVirtualCall(IBinder ibinder)
        throws RemoteException;

    public abstract AudioRoutesInfo startWatchingRoutes(IAudioRoutesObserver iaudioroutesobserver)
        throws RemoteException;

    public abstract void stopBluetoothSco(IBinder ibinder)
        throws RemoteException;

    public abstract int trackPlayer(PlayerBase.PlayerIdCard playeridcard)
        throws RemoteException;

    public abstract void unloadSoundEffects()
        throws RemoteException;

    public abstract void unregisterAudioFocusClient(String s)
        throws RemoteException;

    public abstract void unregisterAudioPolicyAsync(IAudioPolicyCallback iaudiopolicycallback)
        throws RemoteException;

    public abstract void unregisterPlaybackCallback(IPlaybackConfigDispatcher iplaybackconfigdispatcher)
        throws RemoteException;

    public abstract void unregisterRecordingCallback(IRecordingConfigDispatcher irecordingconfigdispatcher)
        throws RemoteException;
}
