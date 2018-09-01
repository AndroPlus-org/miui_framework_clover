// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.util.Log;
import java.util.ArrayList;

// Referenced classes of package android.media:
//            AudioServiceInjector, AudioPatch, AudioPortConfig

public class AudioSystem
{
    public static interface AudioRecordingCallback
    {

        public abstract void onRecordingConfigurationChanged(int i, int j, int k, int l, int ai[], String s);
    }

    public static interface DynamicPolicyCallback
    {

        public abstract void onDynamicPolicyMixStateUpdate(String s, int i);
    }

    public static interface ErrorCallback
    {

        public abstract void onError(int i);
    }


    public AudioSystem()
    {
    }

    public static native int checkAudioFlinger();

    public static native int createAudioPatch(AudioPatch aaudiopatch[], AudioPortConfig aaudioportconfig[], AudioPortConfig aaudioportconfig1[]);

    public static String deviceStateToString(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("unknown state (").append(i).append(")").toString();

        case 0: // '\0'
            return "DEVICE_STATE_UNAVAILABLE";

        case 1: // '\001'
            return "DEVICE_STATE_AVAILABLE";
        }
    }

    private static void dynamicPolicyCallbackFromNative(int i, String s, int j)
    {
        DynamicPolicyCallback dynamicpolicycallback = null;
        android/media/AudioSystem;
        JVM INSTR monitorenter ;
        if(sDynPolicyCallback != null)
            dynamicpolicycallback = sDynPolicyCallback;
        android/media/AudioSystem;
        JVM INSTR monitorexit ;
        if(dynamicpolicycallback == null) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 0 0: default 40
    //                   0 74;
           goto _L3 _L4
_L3:
        Log.e("AudioSystem", (new StringBuilder()).append("dynamicPolicyCallbackFromNative: unknown event ").append(i).toString());
_L2:
        return;
        s;
        throw s;
_L4:
        dynamicpolicycallback.onDynamicPolicyMixStateUpdate(s, j);
        if(true) goto _L2; else goto _L5
_L5:
    }

    private static void errorCallbackFromNative(int i)
    {
        ErrorCallback errorcallback = null;
        android/media/AudioSystem;
        JVM INSTR monitorenter ;
        if(mErrorCallback != null)
            errorcallback = mErrorCallback;
        android/media/AudioSystem;
        JVM INSTR monitorexit ;
        if(errorcallback != null)
            errorcallback.onError(i);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static String forceUseConfigToString(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("unknown config (").append(i).append(")").toString();

        case 0: // '\0'
            return "FORCE_NONE";

        case 1: // '\001'
            return "FORCE_SPEAKER";

        case 2: // '\002'
            return "FORCE_HEADPHONES";

        case 3: // '\003'
            return "FORCE_BT_SCO";

        case 4: // '\004'
            return "FORCE_BT_A2DP";

        case 5: // '\005'
            return "FORCE_WIRED_ACCESSORY";

        case 6: // '\006'
            return "FORCE_BT_CAR_DOCK";

        case 7: // '\007'
            return "FORCE_BT_DESK_DOCK";

        case 8: // '\b'
            return "FORCE_ANALOG_DOCK";

        case 9: // '\t'
            return "FORCE_DIGITAL_DOCK";

        case 10: // '\n'
            return "FORCE_NO_BT_A2DP";

        case 11: // '\013'
            return "FORCE_SYSTEM_ENFORCED";

        case 12: // '\f'
            return "FORCE_HDMI_SYSTEM_AUDIO_ENFORCED";

        case 13: // '\r'
            return "FORCE_ENCODED_SURROUND_NEVER";

        case 14: // '\016'
            return "FORCE_ENCODED_SURROUND_ALWAYS";
        }
    }

    public static String forceUseUsageToString(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("unknown usage (").append(i).append(")").toString();

        case 0: // '\0'
            return "FOR_COMMUNICATION";

        case 1: // '\001'
            return "FOR_MEDIA";

        case 2: // '\002'
            return "FOR_RECORD";

        case 3: // '\003'
            return "FOR_DOCK";

        case 4: // '\004'
            return "FOR_SYSTEM";

        case 5: // '\005'
            return "FOR_HDMI_SYSTEM_AUDIO";

        case 6: // '\006'
            return "FOR_ENCODED_SURROUND";
        }
    }

    public static native int getAudioHwSyncForSession(int i);

    public static int getDefaultStreamVolume(int i)
    {
        return DEFAULT_STREAM_VOLUME[i];
    }

    public static native int getDeviceConnectionState(int i, String s);

    public static native int getDevicesForStream(int i);

    public static native int getForceUse(int i);

    public static String getInputDeviceName(int i)
    {
        switch(i)
        {
        default:
            return Integer.toString(i);

        case -2147483647: 
            return "communication";

        case -2147483646: 
            return "ambient";

        case -2147483644: 
            return "mic";

        case -2147483640: 
            return "bt_sco_hs";

        case -2147483632: 
            return "headset";

        case -2147483616: 
            return "aux_digital";

        case -2147483584: 
            return "telephony_rx";

        case -2147483520: 
            return "back_mic";

        case -2147483392: 
            return "remote_submix";

        case -2147483136: 
            return "analog_dock";

        case -2147482624: 
            return "digital_dock";

        case -2147481600: 
            return "usb_accessory";

        case -2147479552: 
            return "usb_device";

        case -2147475456: 
            return "fm_tuner";

        case -2147467264: 
            return "tv_tuner";

        case -2147450880: 
            return "line";

        case -2147418112: 
            return "spdif";

        case -2147352576: 
            return "bt_a2dp";

        case -2147221504: 
            return "loopback";

        case -2146959360: 
            return "ip";

        case -2146435072: 
            return "bus";

        case -2130706432: 
            return "proxy";

        case -2113929216: 
            return "usb_headset";
        }
    }

    public static native boolean getMasterMono();

    public static native boolean getMasterMute();

    public static native float getMasterVolume();

    public static final int getNumStreamTypes()
    {
        return 11;
    }

    public static String getOutputDeviceName(int i)
    {
        switch(i)
        {
        default:
            return Integer.toString(i);

        case 1: // '\001'
            return "earpiece";

        case 2: // '\002'
            return "speaker";

        case 4: // '\004'
            return "headset";

        case 8: // '\b'
            return "headphone";

        case 16: // '\020'
            return "bt_sco";

        case 32: // ' '
            return "bt_sco_hs";

        case 64: // '@'
            return "bt_sco_carkit";

        case 128: 
            return "bt_a2dp";

        case 256: 
            return "bt_a2dp_hp";

        case 512: 
            return "bt_a2dp_spk";

        case 1024: 
            return "hdmi";

        case 2048: 
            return "analog_dock";

        case 4096: 
            return "digital_dock";

        case 8192: 
            return "usb_accessory";

        case 16384: 
            return "usb_device";

        case 32768: 
            return "remote_submix";

        case 65536: 
            return "telephony_tx";

        case 131072: 
            return "line";

        case 262144: 
            return "hmdi_arc";

        case 524288: 
            return "spdif";

        case 1048576: 
            return "fm_transmitter";

        case 2097152: 
            return "aux_line";

        case 4194304: 
            return "speaker_safe";

        case 8388608: 
            return "ip";

        case 16777216: 
            return "bus";

        case 33554432: 
            return "proxy";

        case 67108864: 
            return "usb_headset";
        }
    }

    public static native int getOutputLatency(int i);

    public static native String getParameters(String s);

    public static int getPlatformType(Context context)
    {
        if(context.getResources().getBoolean(0x11200cb))
            return 1;
        return !context.getPackageManager().hasSystemFeature("android.software.leanback") ? 0 : 2;
    }

    public static native int getPrimaryOutputFrameCount();

    public static native int getPrimaryOutputSamplingRate();

    public static native float getStreamVolumeDB(int i, int j, int k);

    public static native int getStreamVolumeIndex(int i, int j);

    public static int getValueForVibrateSetting(int i, int j, int k)
    {
        return i & 3 << j * 2 | (k & 3) << j * 2;
    }

    public static native int handleDeviceConfigChange(int i, String s, String s1);

    public static native int initStreamVolume(int i, int j, int k);

    public static native boolean isMicrophoneMuted();

    public static boolean isSingleVolume(Context context)
    {
        boolean flag = context.getResources().getBoolean(0x112009c);
        if(getPlatformType(context) == 2)
            flag = true;
        return flag;
    }

    public static native boolean isSourceActive(int i);

    public static native boolean isStreamActive(int i, int j);

    public static native boolean isStreamActiveRemotely(int i, int j);

    public static native int listAudioPatches(ArrayList arraylist, int ai[]);

    public static native int listAudioPorts(ArrayList arraylist, int ai[]);

    public static String modeToString(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("unknown mode (").append(i).append(")").toString();

        case -1: 
            return "MODE_CURRENT";

        case 2: // '\002'
            return "MODE_IN_CALL";

        case 3: // '\003'
            return "MODE_IN_COMMUNICATION";

        case -2: 
            return "MODE_INVALID";

        case 0: // '\0'
            return "MODE_NORMAL";

        case 1: // '\001'
            return "MODE_RINGTONE";
        }
    }

    public static native int muteMicrophone(boolean flag);

    private static final native void native_register_dynamic_policy_callback();

    private static final native void native_register_recording_callback();

    public static native int newAudioPlayerId();

    public static native int newAudioSessionId();

    private static void recordingCallbackFromNative(int i, int j, int k, int l, int ai[])
    {
        android/media/AudioSystem;
        JVM INSTR monitorenter ;
        AudioRecordingCallback audiorecordingcallback = sRecordingCallback;
        android/media/AudioSystem;
        JVM INSTR monitorexit ;
        if(audiorecordingcallback != null)
            audiorecordingcallback.onRecordingConfigurationChanged(i, j, k, l, ai, "");
        return;
        ai;
        throw ai;
    }

    public static native int registerPolicyMixes(ArrayList arraylist, boolean flag);

    public static native int releaseAudioPatch(AudioPatch audiopatch);

    public static native int setAudioPortConfig(AudioPortConfig audioportconfig);

    public static native int setDeviceConnectionState(int i, int j, String s, String s1);

    public static void setDynamicPolicyCallback(DynamicPolicyCallback dynamicpolicycallback)
    {
        android/media/AudioSystem;
        JVM INSTR monitorenter ;
        sDynPolicyCallback = dynamicpolicycallback;
        native_register_dynamic_policy_callback();
        android/media/AudioSystem;
        JVM INSTR monitorexit ;
        return;
        dynamicpolicycallback;
        throw dynamicpolicycallback;
    }

    public static void setErrorCallback(ErrorCallback errorcallback)
    {
        android/media/AudioSystem;
        JVM INSTR monitorenter ;
        mErrorCallback = errorcallback;
        if(errorcallback == null)
            break MISSING_BLOCK_LABEL_20;
        errorcallback.onError(checkAudioFlinger());
        android/media/AudioSystem;
        JVM INSTR monitorexit ;
        return;
        errorcallback;
        throw errorcallback;
    }

    public static native int setForceUse(int i, int j);

    public static native int setLowRamDevice(boolean flag);

    public static native int setMasterMono(boolean flag);

    public static native int setMasterMute(boolean flag);

    public static native int setMasterVolume(float f);

    public static native int setParameters(String s);

    public static native int setPhoneState(int i);

    public static void setRecordingCallback(AudioRecordingCallback audiorecordingcallback)
    {
        android/media/AudioSystem;
        JVM INSTR monitorenter ;
        sRecordingCallback = audiorecordingcallback;
        native_register_recording_callback();
        android/media/AudioSystem;
        JVM INSTR monitorexit ;
        return;
        audiorecordingcallback;
        throw audiorecordingcallback;
    }

    public static native int setStreamVolumeIndex(int i, int j, int k);

    public static String streamToString(int i)
    {
        if(i >= 0 && i < STREAM_NAMES.length)
            return STREAM_NAMES[i];
        if(i == 0x80000000)
            return "USE_DEFAULT_STREAM_TYPE";
        else
            return (new StringBuilder()).append("UNKNOWN_STREAM_").append(i).toString();
    }

    public static native int systemReady();

    public static final int AUDIO_HW_SYNC_INVALID = 0;
    public static final int AUDIO_SESSION_ALLOCATE = 0;
    public static final int AUDIO_STATUS_ERROR = 1;
    public static final int AUDIO_STATUS_OK = 0;
    public static final int AUDIO_STATUS_SERVER_DIED = 100;
    public static final int BAD_VALUE = -2;
    public static final int DEAD_OBJECT = -6;
    public static final int DEFAULT_MUTE_STREAMS_AFFECTED = 46;
    public static int DEFAULT_STREAM_VOLUME[] = {
        4, 7, 5, 5, 6, 5, 7, 7, 5, 5, 
        5
    };
    public static final int DEVICE_ALL_HDMI_SYSTEM_AUDIO_AND_SPEAKER = 0x2c0002;
    public static final int DEVICE_BIT_DEFAULT = 0x40000000;
    public static final int DEVICE_BIT_IN = 0x80000000;
    public static final int DEVICE_IN_ALL = 0xc31fffff;
    public static final int DEVICE_IN_ALL_SCO = 0x80000008;
    public static final int DEVICE_IN_ALL_USB = 0x82001800;
    public static final int DEVICE_IN_AMBIENT = 0x80000002;
    public static final String DEVICE_IN_AMBIENT_NAME = "ambient";
    public static final int DEVICE_IN_ANLG_DOCK_HEADSET = 0x80000200;
    public static final String DEVICE_IN_ANLG_DOCK_HEADSET_NAME = "analog_dock";
    public static final int DEVICE_IN_AUX_DIGITAL = 0x80000020;
    public static final String DEVICE_IN_AUX_DIGITAL_NAME = "aux_digital";
    public static final int DEVICE_IN_BACK_MIC = 0x80000080;
    public static final String DEVICE_IN_BACK_MIC_NAME = "back_mic";
    public static final int DEVICE_IN_BLUETOOTH_A2DP = 0x80020000;
    public static final String DEVICE_IN_BLUETOOTH_A2DP_NAME = "bt_a2dp";
    public static final int DEVICE_IN_BLUETOOTH_SCO_HEADSET = 0x80000008;
    public static final String DEVICE_IN_BLUETOOTH_SCO_HEADSET_NAME = "bt_sco_hs";
    public static final int DEVICE_IN_BUILTIN_MIC = 0x80000004;
    public static final String DEVICE_IN_BUILTIN_MIC_NAME = "mic";
    public static final int DEVICE_IN_BUS = 0x80100000;
    public static final String DEVICE_IN_BUS_NAME = "bus";
    public static final int DEVICE_IN_COMMUNICATION = 0x80000001;
    public static final String DEVICE_IN_COMMUNICATION_NAME = "communication";
    public static final int DEVICE_IN_DEFAULT = 0xc0000000;
    public static final int DEVICE_IN_DGTL_DOCK_HEADSET = 0x80000400;
    public static final String DEVICE_IN_DGTL_DOCK_HEADSET_NAME = "digital_dock";
    public static final int DEVICE_IN_FM_TUNER = 0x80002000;
    public static final String DEVICE_IN_FM_TUNER_NAME = "fm_tuner";
    public static final int DEVICE_IN_HDMI = 0x80000020;
    public static final int DEVICE_IN_IP = 0x80080000;
    public static final String DEVICE_IN_IP_NAME = "ip";
    public static final int DEVICE_IN_LINE = 0x80008000;
    public static final String DEVICE_IN_LINE_NAME = "line";
    public static final int DEVICE_IN_LOOPBACK = 0x80040000;
    public static final String DEVICE_IN_LOOPBACK_NAME = "loopback";
    public static final int DEVICE_IN_PROXY = 0x81000000;
    public static final String DEVICE_IN_PROXY_NAME = "proxy";
    public static final int DEVICE_IN_REMOTE_SUBMIX = 0x80000100;
    public static final String DEVICE_IN_REMOTE_SUBMIX_NAME = "remote_submix";
    public static final int DEVICE_IN_SPDIF = 0x80010000;
    public static final String DEVICE_IN_SPDIF_NAME = "spdif";
    public static final int DEVICE_IN_TELEPHONY_RX = 0x80000040;
    public static final String DEVICE_IN_TELEPHONY_RX_NAME = "telephony_rx";
    public static final int DEVICE_IN_TV_TUNER = 0x80004000;
    public static final String DEVICE_IN_TV_TUNER_NAME = "tv_tuner";
    public static final int DEVICE_IN_USB_ACCESSORY = 0x80000800;
    public static final String DEVICE_IN_USB_ACCESSORY_NAME = "usb_accessory";
    public static final int DEVICE_IN_USB_DEVICE = 0x80001000;
    public static final String DEVICE_IN_USB_DEVICE_NAME = "usb_device";
    public static final int DEVICE_IN_USB_HEADSET = 0x82000000;
    public static final String DEVICE_IN_USB_HEADSET_NAME = "usb_headset";
    public static final int DEVICE_IN_VOICE_CALL = 0x80000040;
    public static final int DEVICE_IN_WIRED_HEADSET = 0x80000010;
    public static final String DEVICE_IN_WIRED_HEADSET_NAME = "headset";
    public static final int DEVICE_NONE = 0;
    public static final int DEVICE_OUT_ALL = 0x47ffffff;
    public static final int DEVICE_OUT_ALL_A2DP = 896;
    public static final int DEVICE_OUT_ALL_HDMI_SYSTEM_AUDIO = 0x2c0000;
    public static final int DEVICE_OUT_ALL_SCO = 112;
    public static final int DEVICE_OUT_ALL_USB = 0x4006000;
    public static final int DEVICE_OUT_ANLG_DOCK_HEADSET = 2048;
    public static final String DEVICE_OUT_ANLG_DOCK_HEADSET_NAME = "analog_dock";
    public static final int DEVICE_OUT_AUX_DIGITAL = 1024;
    public static final String DEVICE_OUT_AUX_DIGITAL_NAME = "aux_digital";
    public static final int DEVICE_OUT_AUX_LINE = 0x200000;
    public static final String DEVICE_OUT_AUX_LINE_NAME = "aux_line";
    public static final int DEVICE_OUT_BLUETOOTH_A2DP = 128;
    public static final int DEVICE_OUT_BLUETOOTH_A2DP_HEADPHONES = 256;
    public static final String DEVICE_OUT_BLUETOOTH_A2DP_HEADPHONES_NAME = "bt_a2dp_hp";
    public static final String DEVICE_OUT_BLUETOOTH_A2DP_NAME = "bt_a2dp";
    public static final int DEVICE_OUT_BLUETOOTH_A2DP_SPEAKER = 512;
    public static final String DEVICE_OUT_BLUETOOTH_A2DP_SPEAKER_NAME = "bt_a2dp_spk";
    public static final int DEVICE_OUT_BLUETOOTH_SCO = 16;
    public static final int DEVICE_OUT_BLUETOOTH_SCO_CARKIT = 64;
    public static final String DEVICE_OUT_BLUETOOTH_SCO_CARKIT_NAME = "bt_sco_carkit";
    public static final int DEVICE_OUT_BLUETOOTH_SCO_HEADSET = 32;
    public static final String DEVICE_OUT_BLUETOOTH_SCO_HEADSET_NAME = "bt_sco_hs";
    public static final String DEVICE_OUT_BLUETOOTH_SCO_NAME = "bt_sco";
    public static final int DEVICE_OUT_BUS = 0x1000000;
    public static final String DEVICE_OUT_BUS_NAME = "bus";
    public static final int DEVICE_OUT_DEFAULT = 0x40000000;
    public static final int DEVICE_OUT_DGTL_DOCK_HEADSET = 4096;
    public static final String DEVICE_OUT_DGTL_DOCK_HEADSET_NAME = "digital_dock";
    public static final int DEVICE_OUT_EARPIECE = 1;
    public static final String DEVICE_OUT_EARPIECE_NAME = "earpiece";
    public static final int DEVICE_OUT_FM = 0x100000;
    public static final String DEVICE_OUT_FM_NAME = "fm_transmitter";
    public static final int DEVICE_OUT_HDMI = 1024;
    public static final int DEVICE_OUT_HDMI_ARC = 0x40000;
    public static final String DEVICE_OUT_HDMI_ARC_NAME = "hmdi_arc";
    public static final String DEVICE_OUT_HDMI_NAME = "hdmi";
    public static final int DEVICE_OUT_IP = 0x800000;
    public static final String DEVICE_OUT_IP_NAME = "ip";
    public static final int DEVICE_OUT_LINE = 0x20000;
    public static final String DEVICE_OUT_LINE_NAME = "line";
    public static final int DEVICE_OUT_PROXY = 0x2000000;
    public static final String DEVICE_OUT_PROXY_NAME = "proxy";
    public static final int DEVICE_OUT_REMOTE_SUBMIX = 32768;
    public static final String DEVICE_OUT_REMOTE_SUBMIX_NAME = "remote_submix";
    public static final int DEVICE_OUT_SPDIF = 0x80000;
    public static final String DEVICE_OUT_SPDIF_NAME = "spdif";
    public static final int DEVICE_OUT_SPEAKER = 2;
    public static final String DEVICE_OUT_SPEAKER_NAME = "speaker";
    public static final int DEVICE_OUT_SPEAKER_SAFE = 0x400000;
    public static final String DEVICE_OUT_SPEAKER_SAFE_NAME = "speaker_safe";
    public static final int DEVICE_OUT_TELEPHONY_TX = 0x10000;
    public static final String DEVICE_OUT_TELEPHONY_TX_NAME = "telephony_tx";
    public static final int DEVICE_OUT_USB_ACCESSORY = 8192;
    public static final String DEVICE_OUT_USB_ACCESSORY_NAME = "usb_accessory";
    public static final int DEVICE_OUT_USB_DEVICE = 16384;
    public static final String DEVICE_OUT_USB_DEVICE_NAME = "usb_device";
    public static final int DEVICE_OUT_USB_HEADSET = 0x4000000;
    public static final String DEVICE_OUT_USB_HEADSET_NAME = "usb_headset";
    public static final int DEVICE_OUT_WIRED_HEADPHONE = 8;
    public static final String DEVICE_OUT_WIRED_HEADPHONE_NAME = "headphone";
    public static final int DEVICE_OUT_WIRED_HEADSET = 4;
    public static final String DEVICE_OUT_WIRED_HEADSET_NAME = "headset";
    public static final int DEVICE_STATE_AVAILABLE = 1;
    public static final int DEVICE_STATE_UNAVAILABLE = 0;
    private static final int DYNAMIC_POLICY_EVENT_MIX_STATE_UPDATE = 0;
    public static final int ERROR = -1;
    public static final int FORCE_ANALOG_DOCK = 8;
    public static final int FORCE_BT_A2DP = 4;
    public static final int FORCE_BT_CAR_DOCK = 6;
    public static final int FORCE_BT_DESK_DOCK = 7;
    public static final int FORCE_BT_SCO = 3;
    public static final int FORCE_DEFAULT = 0;
    public static final int FORCE_DIGITAL_DOCK = 9;
    public static final int FORCE_ENCODED_SURROUND_ALWAYS = 14;
    public static final int FORCE_ENCODED_SURROUND_NEVER = 13;
    public static final int FORCE_HDMI_SYSTEM_AUDIO_ENFORCED = 12;
    public static final int FORCE_HEADPHONES = 2;
    public static final int FORCE_NONE = 0;
    public static final int FORCE_NO_BT_A2DP = 10;
    public static final int FORCE_SPEAKER = 1;
    public static final int FORCE_SYSTEM_ENFORCED = 11;
    public static final int FORCE_WIRED_ACCESSORY = 5;
    public static final int FOR_COMMUNICATION = 0;
    public static final int FOR_DOCK = 3;
    public static final int FOR_ENCODED_SURROUND = 6;
    public static final int FOR_HDMI_SYSTEM_AUDIO = 5;
    public static final int FOR_LB_TEST = 7;
    public static final int FOR_MEDIA = 1;
    public static final int FOR_RECORD = 2;
    public static final int FOR_RING = 8;
    public static final int FOR_SYSTEM = 4;
    public static final int INVALID_OPERATION = -3;
    public static final String IN_VOICE_COMM_FOCUS_ID = "AudioFocus_For_Phone_Ring_And_Calls";
    public static final int MODE_CURRENT = -1;
    public static final int MODE_INVALID = -2;
    public static final int MODE_IN_CALL = 2;
    public static final int MODE_IN_COMMUNICATION = 3;
    public static final int MODE_NORMAL = 0;
    public static final int MODE_RINGTONE = 1;
    static final int NATIVE_EVENT_ROUTING_CHANGE = 1000;
    public static final int NO_INIT = -5;
    private static final int NUM_DEVICE_STATES = 1;
    public static final int NUM_FORCE_CONFIG = 15;
    private static final int NUM_FORCE_USE = 9;
    public static final int NUM_MODES = 4;
    public static final int NUM_STREAMS = 5;
    private static final int NUM_STREAM_TYPES = 11;
    public static final int PERMISSION_DENIED = -4;
    public static final int PHONE_STATE_INCALL = 2;
    public static final int PHONE_STATE_OFFCALL = 0;
    public static final int PHONE_STATE_RINGING = 1;
    public static final int PLATFORM_DEFAULT = 0;
    public static final int PLATFORM_TELEVISION = 2;
    public static final int PLATFORM_VOICE = 1;
    public static final int PLAY_SOUND_DELAY = 300;
    public static final int ROUTE_ALL = -1;
    public static final int ROUTE_BLUETOOTH = 4;
    public static final int ROUTE_BLUETOOTH_A2DP = 16;
    public static final int ROUTE_BLUETOOTH_SCO = 4;
    public static final int ROUTE_EARPIECE = 1;
    public static final int ROUTE_HEADSET = 8;
    public static final int ROUTE_SPEAKER = 2;
    public static final int STREAM_ACCESSIBILITY = 10;
    public static final int STREAM_ALARM = 4;
    public static final int STREAM_BLUETOOTH_SCO = 6;
    public static final int STREAM_DEFAULT = -1;
    public static final int STREAM_DTMF = 8;
    public static final int STREAM_MUSIC = 3;
    public static final String STREAM_NAMES[] = {
        "STREAM_VOICE_CALL", "STREAM_SYSTEM", "STREAM_RING", "STREAM_MUSIC", "STREAM_ALARM", "STREAM_NOTIFICATION", "STREAM_BLUETOOTH_SCO", "STREAM_SYSTEM_ENFORCED", "STREAM_DTMF", "STREAM_TTS", 
        "STREAM_ACCESSIBILITY"
    };
    public static final int STREAM_NOTIFICATION = 5;
    public static final int STREAM_RING = 2;
    public static final int STREAM_SYSTEM = 1;
    public static final int STREAM_SYSTEM_ENFORCED = 7;
    public static final int STREAM_TTS = 9;
    public static final int STREAM_VOICE_CALL = 0;
    public static final int SUCCESS = 0;
    public static final int SYNC_EVENT_NONE = 0;
    public static final int SYNC_EVENT_PRESENTATION_COMPLETE = 1;
    private static final String TAG = "AudioSystem";
    public static final int WOULD_BLOCK = -7;
    private static ErrorCallback mErrorCallback;
    private static DynamicPolicyCallback sDynPolicyCallback;
    private static AudioRecordingCallback sRecordingCallback;

    static 
    {
        AudioServiceInjector.adjustDefaultStreamVolume(DEFAULT_STREAM_VOLUME);
    }
}
