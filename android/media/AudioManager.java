// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.app.PendingIntent;
import android.bluetooth.BluetoothDevice;
import android.content.*;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.media.audiopolicy.AudioPolicy;
import android.media.session.MediaSessionLegacyHelper;
import android.os.*;
import android.util.ArrayMap;
import android.util.Log;
import android.view.KeyEvent;
import com.android.internal.app.IAppOpsService;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package android.media:
//            AudioPortEventHandler, AudioDevicePort, AudioDeviceInfo, AudioSystem, 
//            AudioAttributes, IAudioService, AudioPort, AudioPortConfig, 
//            AudioPatch, AudioHandle, AudioGainConfig, AudioGain, 
//            AudioFocusRequest, RemoteControlClient, RemoteController, PlayerBase, 
//            IAudioFocusDispatcher, IPlaybackConfigDispatcher, IRecordingConfigDispatcher, AudioFocusInfo, 
//            IRingtonePlayer, IVolumeController, AudioDeviceCallback, VolumePolicy

public class AudioManager
{
    public static abstract class AudioPlaybackCallback
    {

        public void onPlaybackConfigChanged(List list)
        {
        }

        public AudioPlaybackCallback()
        {
        }
    }

    private static class AudioPlaybackCallbackInfo
    {

        final AudioPlaybackCallback mCb;
        final Handler mHandler;

        AudioPlaybackCallbackInfo(AudioPlaybackCallback audioplaybackcallback, Handler handler)
        {
            mCb = audioplaybackcallback;
            mHandler = handler;
        }
    }

    public static abstract class AudioRecordingCallback
    {

        public void onRecordingConfigChanged(List list)
        {
        }

        public AudioRecordingCallback()
        {
        }
    }

    private static class AudioRecordingCallbackInfo
    {

        final AudioRecordingCallback mCb;
        final Handler mHandler;

        AudioRecordingCallbackInfo(AudioRecordingCallback audiorecordingcallback, Handler handler)
        {
            mCb = audiorecordingcallback;
            mHandler = handler;
        }
    }

    private static class FocusRequestInfo
    {

        final Handler mHandler;
        final AudioFocusRequest mRequest;

        FocusRequestInfo(AudioFocusRequest audiofocusrequest, Handler handler)
        {
            mRequest = audiofocusrequest;
            mHandler = handler;
        }
    }

    private class NativeEventHandlerDelegate
    {

        Handler getHandler()
        {
            return mHandler;
        }

        private final Handler mHandler;
        final AudioManager this$0;

        NativeEventHandlerDelegate(AudioDeviceCallback audiodevicecallback, Handler handler)
        {
            this$0 = AudioManager.this;
            super();
            if(handler != null)
                audiomanager = handler.getLooper();
            else
                audiomanager = Looper.getMainLooper();
            if(AudioManager.this != null)
                mHandler = new _cls1(audiodevicecallback);
            else
                mHandler = null;
        }
    }

    private class OnAmPortUpdateListener
        implements OnAudioPortUpdateListener
    {

        public void onAudioPatchListUpdate(AudioPatch aaudiopatch[])
        {
        }

        public void onAudioPortListUpdate(AudioPort aaudioport[])
        {
            AudioManager._2D_wrap1(AudioManager.this, null);
        }

        public void onServiceDied()
        {
            AudioManager._2D_wrap1(AudioManager.this, null);
        }

        static final String TAG = "OnAmPortUpdateListener";
        final AudioManager this$0;

        private OnAmPortUpdateListener()
        {
            this$0 = AudioManager.this;
            super();
        }

        OnAmPortUpdateListener(OnAmPortUpdateListener onamportupdatelistener)
        {
            this();
        }
    }

    public static interface OnAudioFocusChangeListener
    {

        public abstract void onAudioFocusChange(int i);
    }

    public static interface OnAudioPortUpdateListener
    {

        public abstract void onAudioPatchListUpdate(AudioPatch aaudiopatch[]);

        public abstract void onAudioPortListUpdate(AudioPort aaudioport[]);

        public abstract void onServiceDied();
    }

    private static final class PlaybackConfigChangeCallbackData
    {

        final AudioPlaybackCallback mCb;
        final List mConfigs;

        PlaybackConfigChangeCallbackData(AudioPlaybackCallback audioplaybackcallback, List list)
        {
            mCb = audioplaybackcallback;
            mConfigs = list;
        }
    }

    private static final class RecordConfigChangeCallbackData
    {

        final AudioRecordingCallback mCb;
        final List mConfigs;

        RecordConfigChangeCallbackData(AudioRecordingCallback audiorecordingcallback, List list)
        {
            mCb = audiorecordingcallback;
            mConfigs = list;
        }
    }

    private class ServiceEventHandlerDelegate
    {

        Handler getHandler()
        {
            return mHandler;
        }

        private final Handler mHandler;
        final AudioManager this$0;

        ServiceEventHandlerDelegate(Handler handler)
        {
            this$0 = AudioManager.this;
            super();
            if(handler == null)
            {
                handler = Looper.myLooper();
                audiomanager = handler;
                if(handler == null)
                    audiomanager = Looper.getMainLooper();
            } else
            {
                audiomanager = handler.getLooper();
            }
            if(AudioManager.this != null)
                mHandler = new _cls1(AudioManager.this);
            else
                mHandler = null;
        }
    }


    static List _2D_get0(AudioManager audiomanager)
    {
        return audiomanager.mPlaybackCallbackList;
    }

    static Object _2D_get1(AudioManager audiomanager)
    {
        return audiomanager.mPlaybackCallbackLock;
    }

    static List _2D_get2(AudioManager audiomanager)
    {
        return audiomanager.mRecordCallbackList;
    }

    static Object _2D_get3(AudioManager audiomanager)
    {
        return audiomanager.mRecordCallbackLock;
    }

    static ServiceEventHandlerDelegate _2D_get4(AudioManager audiomanager)
    {
        return audiomanager.mServiceEventHandlerDelegate;
    }

    static FocusRequestInfo _2D_wrap0(AudioManager audiomanager, String s)
    {
        return audiomanager.findFocusRequestInfo(s);
    }

    static void _2D_wrap1(AudioManager audiomanager, Handler handler)
    {
        audiomanager.broadcastDeviceListChange(handler);
    }

    public AudioManager()
    {
        mAudioFocusIdListenerMap = new ConcurrentHashMap();
        mServiceEventHandlerDelegate = new ServiceEventHandlerDelegate(null);
        mAudioFocusDispatcher = new IAudioFocusDispatcher.Stub() {

            public void dispatchAudioFocusChange(int i, String s)
            {
                Object obj = AudioManager._2D_wrap0(AudioManager.this, s);
                if(obj != null && ((FocusRequestInfo) (obj)).mRequest.getOnAudioFocusChangeListener() != null)
                {
                    if(((FocusRequestInfo) (obj)).mHandler == null)
                        obj = AudioManager._2D_get4(AudioManager.this).getHandler();
                    else
                        obj = ((FocusRequestInfo) (obj)).mHandler;
                    ((Handler) (obj)).sendMessage(((Handler) (obj)).obtainMessage(0, i, 0, s));
                }
            }

            final AudioManager this$0;

            
            {
                this$0 = AudioManager.this;
                super();
            }
        }
;
        mPlaybackCallbackLock = new Object();
        mPlayCb = new IPlaybackConfigDispatcher.Stub() {

            public void dispatchPlaybackConfigChange(List list, boolean flag)
            {
                if(flag)
                    Binder.flushPendingCommands();
                Object obj = AudioManager._2D_get1(AudioManager.this);
                obj;
                JVM INSTR monitorenter ;
                if(AudioManager._2D_get0(AudioManager.this) == null) goto _L2; else goto _L1
_L1:
                int i = 0;
_L3:
                if(i >= AudioManager._2D_get0(AudioManager.this).size())
                    break; /* Loop/switch isn't completed */
                AudioPlaybackCallbackInfo audioplaybackcallbackinfo = (AudioPlaybackCallbackInfo)AudioManager._2D_get0(AudioManager.this).get(i);
                if(audioplaybackcallbackinfo.mHandler != null)
                {
                    Object obj1 = audioplaybackcallbackinfo.mHandler;
                    PlaybackConfigChangeCallbackData playbackconfigchangecallbackdata = JVM INSTR new #49  <Class AudioManager$PlaybackConfigChangeCallbackData>;
                    playbackconfigchangecallbackdata.PlaybackConfigChangeCallbackData(audioplaybackcallbackinfo.mCb, list);
                    obj1 = ((Handler) (obj1)).obtainMessage(2, playbackconfigchangecallbackdata);
                    audioplaybackcallbackinfo.mHandler.sendMessage(((Message) (obj1)));
                }
                i++;
                if(true) goto _L3; else goto _L2
_L2:
                obj;
                JVM INSTR monitorexit ;
                return;
                list;
                throw list;
            }

            final AudioManager this$0;

            
            {
                this$0 = AudioManager.this;
                super();
            }
        }
;
        mRecordCallbackLock = new Object();
        mRecCb = new IRecordingConfigDispatcher.Stub() {

            public void dispatchRecordingConfigChange(List list)
            {
                Object obj = AudioManager._2D_get3(AudioManager.this);
                obj;
                JVM INSTR monitorenter ;
                if(AudioManager._2D_get2(AudioManager.this) == null) goto _L2; else goto _L1
_L1:
                int i = 0;
_L3:
                if(i >= AudioManager._2D_get2(AudioManager.this).size())
                    break; /* Loop/switch isn't completed */
                AudioRecordingCallbackInfo audiorecordingcallbackinfo = (AudioRecordingCallbackInfo)AudioManager._2D_get2(AudioManager.this).get(i);
                if(audiorecordingcallbackinfo.mHandler != null)
                {
                    Handler handler = audiorecordingcallbackinfo.mHandler;
                    Object obj1 = JVM INSTR new #44  <Class AudioManager$RecordConfigChangeCallbackData>;
                    ((RecordConfigChangeCallbackData) (obj1)).RecordConfigChangeCallbackData(audiorecordingcallbackinfo.mCb, list);
                    obj1 = handler.obtainMessage(1, obj1);
                    audiorecordingcallbackinfo.mHandler.sendMessage(((Message) (obj1)));
                }
                i++;
                if(true) goto _L3; else goto _L2
_L2:
                obj;
                JVM INSTR monitorexit ;
                return;
                list;
                throw list;
            }

            final AudioManager this$0;

            
            {
                this$0 = AudioManager.this;
                super();
            }
        }
;
        mICallBack = new Binder();
        mPortListener = null;
        mDeviceCallbacks = new ArrayMap();
        mPreviousPorts = new ArrayList();
        mUseVolumeKeySounds = true;
        mUseFixedVolume = false;
    }

    public AudioManager(Context context)
    {
        mAudioFocusIdListenerMap = new ConcurrentHashMap();
        mServiceEventHandlerDelegate = new ServiceEventHandlerDelegate(null);
        mAudioFocusDispatcher = new _cls1();
        mPlaybackCallbackLock = new Object();
        mPlayCb = new _cls2();
        mRecordCallbackLock = new Object();
        mRecCb = new _cls3();
        mICallBack = new Binder();
        mPortListener = null;
        mDeviceCallbacks = new ArrayMap();
        mPreviousPorts = new ArrayList();
        setContext(context);
        mUseVolumeKeySounds = getContext().getResources().getBoolean(0x11200c6);
        mUseFixedVolume = getContext().getResources().getBoolean(0x11200c2);
    }

    public static final String adjustToString(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder("unknown adjust mode ")).append(i).toString();

        case 1: // '\001'
            return "ADJUST_RAISE";

        case -1: 
            return "ADJUST_LOWER";

        case 0: // '\0'
            return "ADJUST_SAME";

        case -100: 
            return "ADJUST_MUTE";

        case 100: // 'd'
            return "ADJUST_UNMUTE";

        case 101: // 'e'
            return "ADJUST_TOGGLE_MUTE";
        }
    }

    private void broadcastDeviceListChange(Handler handler)
    {
        ArrayList arraylist;
        arraylist = new ArrayList();
        if(listAudioDevicePorts(arraylist) != 0)
            return;
        if(handler == null) goto _L2; else goto _L1
_L1:
        handler.sendMessage(Message.obtain(handler, 0, infoListFromPortList(arraylist, 3)));
_L6:
        mPreviousPorts = arraylist;
        return;
_L2:
        AudioDeviceInfo aaudiodeviceinfo[];
        AudioDeviceInfo aaudiodeviceinfo1[];
        aaudiodeviceinfo = calcListDeltas(mPreviousPorts, arraylist, 3);
        aaudiodeviceinfo1 = calcListDeltas(arraylist, mPreviousPorts, 3);
        if(aaudiodeviceinfo.length == 0 && aaudiodeviceinfo1.length == 0)
            continue; /* Loop/switch isn't completed */
        handler = mDeviceCallbacks;
        handler;
        JVM INSTR monitorenter ;
        int i = 0;
_L4:
        Handler handler1;
        if(i >= mDeviceCallbacks.size())
            break; /* Loop/switch isn't completed */
        handler1 = ((NativeEventHandlerDelegate)mDeviceCallbacks.valueAt(i)).getHandler();
        if(handler1 == null)
            break MISSING_BLOCK_LABEL_155;
        if(aaudiodeviceinfo.length != 0)
            handler1.sendMessage(Message.obtain(handler1, 1, aaudiodeviceinfo));
        if(aaudiodeviceinfo1.length != 0)
            handler1.sendMessage(Message.obtain(handler1, 2, aaudiodeviceinfo1));
        i++;
        if(true) goto _L4; else goto _L3
_L3:
        if(true) goto _L6; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    private static AudioDeviceInfo[] calcListDeltas(ArrayList arraylist, ArrayList arraylist1, int i)
    {
        ArrayList arraylist2 = new ArrayList();
        for(int j = 0; j < arraylist1.size(); j++)
        {
            boolean flag = false;
            AudioDevicePort audiodeviceport = (AudioDevicePort)arraylist1.get(j);
            int k = 0;
            while(k < arraylist.size() && flag ^ true) 
            {
                if(audiodeviceport.id() == ((AudioDevicePort)arraylist.get(k)).id())
                    flag = true;
                else
                    flag = false;
                k++;
            }
            if(!flag)
                arraylist2.add(audiodeviceport);
        }

        return infoListFromPortList(arraylist2, i);
    }

    private static boolean checkFlags(AudioDevicePort audiodeviceport, int i)
    {
        boolean flag;
        flag = true;
        break MISSING_BLOCK_LABEL_2;
        if((audiodeviceport.role() != 2 || (i & 2) == 0) && (audiodeviceport.role() != 1 || (i & 1) == 0))
            flag = false;
        return flag;
    }

    private static boolean checkTypes(AudioDevicePort audiodeviceport)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(AudioDeviceInfo.convertInternalDeviceToDeviceType(audiodeviceport.type()) != 0)
        {
            flag1 = flag;
            if(audiodeviceport.type() != 0x80000080)
                flag1 = true;
        }
        return flag1;
    }

    public static int createAudioPatch(AudioPatch aaudiopatch[], AudioPortConfig aaudioportconfig[], AudioPortConfig aaudioportconfig1[])
    {
        return AudioSystem.createAudioPatch(aaudiopatch, aaudioportconfig, aaudioportconfig1);
    }

    private static void filterDevicePorts(ArrayList arraylist, ArrayList arraylist1)
    {
        arraylist1.clear();
        for(int i = 0; i < arraylist.size(); i++)
            if(arraylist.get(i) instanceof AudioDevicePort)
                arraylist1.add((AudioDevicePort)arraylist.get(i));

    }

    private FocusRequestInfo findFocusRequestInfo(String s)
    {
        return (FocusRequestInfo)mAudioFocusIdListenerMap.get(s);
    }

    public static String flagsToString(int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        boolean flag = false;
        int k = i;
        for(i = ((flag) ? 1 : 0); i < FLAG_NAMES.length;)
        {
            int l = 1 << i;
            int j = k;
            if((k & l) != 0)
            {
                if(stringbuilder.length() > 0)
                    stringbuilder.append(',');
                stringbuilder.append(FLAG_NAMES[i]);
                j = k & l;
            }
            i++;
            k = j;
        }

        if(k != 0)
        {
            if(stringbuilder.length() > 0)
                stringbuilder.append(',');
            stringbuilder.append(k);
        }
        return stringbuilder.toString();
    }

    private static IAppOpsService getAppOpsService()
    {
        if(sAppOpsService != null)
        {
            return sAppOpsService;
        } else
        {
            sAppOpsService = com.android.internal.app.IAppOpsService.Stub.asInterface(ServiceManager.getService("appops"));
            return sAppOpsService;
        }
    }

    private Context getContext()
    {
        if(mApplicationContext == null)
            setContext(mOriginalContext);
        if(mApplicationContext != null)
            return mApplicationContext;
        else
            return mOriginalContext;
    }

    public static AudioDeviceInfo[] getDevicesStatic(int i)
    {
        ArrayList arraylist = new ArrayList();
        if(listAudioDevicePorts(arraylist) != 0)
            return new AudioDeviceInfo[0];
        else
            return infoListFromPortList(arraylist, i);
    }

    private String getIdForAudioFocusListener(OnAudioFocusChangeListener onaudiofocuschangelistener)
    {
        if(onaudiofocuschangelistener == null)
            return new String(toString());
        else
            return new String((new StringBuilder()).append(toString()).append(onaudiofocuschangelistener.toString()).toString());
    }

    private static IAudioService getService()
    {
        if(sService != null)
        {
            return sService;
        } else
        {
            sService = IAudioService.Stub.asInterface(ServiceManager.getService("audio"));
            return sService;
        }
    }

    private boolean hasPlaybackCallback_sync(AudioPlaybackCallback audioplaybackcallback)
    {
        if(mPlaybackCallbackList != null)
        {
            for(int i = 0; i < mPlaybackCallbackList.size(); i++)
                if(audioplaybackcallback.equals(((AudioPlaybackCallbackInfo)mPlaybackCallbackList.get(i)).mCb))
                    return true;

        }
        return false;
    }

    private boolean hasRecordCallback_sync(AudioRecordingCallback audiorecordingcallback)
    {
        if(mRecordCallbackList != null)
        {
            for(int i = 0; i < mRecordCallbackList.size(); i++)
                if(audiorecordingcallback.equals(((AudioRecordingCallbackInfo)mRecordCallbackList.get(i)).mCb))
                    return true;

        }
        return false;
    }

    private static AudioDeviceInfo[] infoListFromPortList(ArrayList arraylist, int i)
    {
        int j = 0;
        Iterator iterator = arraylist.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            AudioDevicePort audiodeviceport = (AudioDevicePort)iterator.next();
            if(checkTypes(audiodeviceport) && checkFlags(audiodeviceport, i))
                j++;
        } while(true);
        AudioDeviceInfo aaudiodeviceinfo[] = new AudioDeviceInfo[j];
        j = 0;
        Iterator iterator1 = arraylist.iterator();
        do
        {
            if(!iterator1.hasNext())
                break;
            arraylist = (AudioDevicePort)iterator1.next();
            if(checkTypes(arraylist) && checkFlags(arraylist, i))
            {
                aaudiodeviceinfo[j] = new AudioDeviceInfo(arraylist);
                j++;
            }
        } while(true);
        return aaudiodeviceinfo;
    }

    public static boolean isInputDevice(int i)
    {
        boolean flag;
        if((i & 0x80000000) == 0x80000000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isOutputDevice(int i)
    {
        boolean flag = false;
        if((0x80000000 & i) == 0)
            flag = true;
        return flag;
    }

    private boolean isRestricted(int i)
    {
        boolean flag = false;
        try
        {
            IAppOpsService iappopsservice = getAppOpsService();
            Object obj = JVM INSTR new #749 <Class AudioAttributes$Builder>;
            ((AudioAttributes.Builder) (obj)).AudioAttributes.Builder();
            i = iappopsservice.checkAudioOperation(28, ((AudioAttributes.Builder) (obj)).setInternalLegacyStreamType(i).build().getUsage(), Process.myUid(), getContext().getPackageName());
            obj = JVM INSTR new #544 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Log.d("AudioManager", ((StringBuilder) (obj)).append("getStreamVolume isRestricted mode = ").append(i).toString());
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
            return false;
        }
        catch(SecurityException securityexception)
        {
            securityexception.printStackTrace();
            return false;
        }
        if(i != 0)
            flag = true;
        return flag;
    }

    public static boolean isValidRingerMode(int i)
    {
        if(i < 0 || i > 2)
            return false;
        IAudioService iaudioservice = getService();
        boolean flag;
        try
        {
            flag = iaudioservice.isValidRingerMode(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public static int listAudioDevicePorts(ArrayList arraylist)
    {
        if(arraylist == null)
            return -2;
        ArrayList arraylist1 = new ArrayList();
        int i = updateAudioPortCache(arraylist1, null, null);
        if(i == 0)
            filterDevicePorts(arraylist1, arraylist);
        return i;
    }

    public static int listAudioPatches(ArrayList arraylist)
    {
        return updateAudioPortCache(null, arraylist, null);
    }

    public static int listAudioPorts(ArrayList arraylist)
    {
        return updateAudioPortCache(arraylist, null, null);
    }

    public static int listPreviousAudioDevicePorts(ArrayList arraylist)
    {
        if(arraylist == null)
            return -2;
        ArrayList arraylist1 = new ArrayList();
        int i = updateAudioPortCache(null, null, arraylist1);
        if(i == 0)
            filterDevicePorts(arraylist1, arraylist);
        return i;
    }

    public static int listPreviousAudioPorts(ArrayList arraylist)
    {
        return updateAudioPortCache(null, null, arraylist);
    }

    private boolean querySoundEffectsEnabled(int i)
    {
        boolean flag = false;
        if(android.provider.Settings.System.getIntForUser(getContext().getContentResolver(), "sound_effects_enabled", 0, i) != 0)
            flag = true;
        return flag;
    }

    public static int releaseAudioPatch(AudioPatch audiopatch)
    {
        return AudioSystem.releaseAudioPatch(audiopatch);
    }

    private boolean removePlaybackCallback_sync(AudioPlaybackCallback audioplaybackcallback)
    {
        if(mPlaybackCallbackList != null)
        {
            for(int i = 0; i < mPlaybackCallbackList.size(); i++)
                if(audioplaybackcallback.equals(((AudioPlaybackCallbackInfo)mPlaybackCallbackList.get(i)).mCb))
                {
                    mPlaybackCallbackList.remove(i);
                    return true;
                }

        }
        return false;
    }

    private boolean removeRecordCallback_sync(AudioRecordingCallback audiorecordingcallback)
    {
        if(mRecordCallbackList != null)
        {
            for(int i = 0; i < mRecordCallbackList.size(); i++)
                if(audiorecordingcallback.equals(((AudioRecordingCallbackInfo)mRecordCallbackList.get(i)).mCb))
                {
                    mRecordCallbackList.remove(i);
                    return true;
                }

        }
        return false;
    }

    static int resetAudioPortGeneration()
    {
        Integer integer = sAudioPortGeneration;
        integer;
        JVM INSTR monitorenter ;
        int i;
        i = sAudioPortGeneration.intValue();
        sAudioPortGeneration = Integer.valueOf(0);
        integer;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public static int setAudioPortGain(AudioPort audioport, AudioGainConfig audiogainconfig)
    {
        if(audioport == null || audiogainconfig == null)
        {
            return -2;
        } else
        {
            AudioPortConfig audioportconfig = audioport.activeConfig();
            audioport = new AudioPortConfig(audioport, audioportconfig.samplingRate(), audioportconfig.channelMask(), audioportconfig.format(), audiogainconfig);
            audioport.mConfigMask = 8;
            return AudioSystem.setAudioPortConfig(audioport);
        }
    }

    private void setContext(Context context)
    {
        mApplicationContext = context.getApplicationContext();
        if(mApplicationContext != null)
            mOriginalContext = null;
        else
            mOriginalContext = context;
    }

    static int updateAudioPortCache(ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2)
    {
        sAudioPortEventHandler.init();
        Integer integer = sAudioPortGeneration;
        integer;
        JVM INSTR monitorenter ;
        if(sAudioPortGeneration.intValue() != 0) goto _L2; else goto _L1
_L1:
        int ai[];
        int ai1[];
        ArrayList arraylist3;
        ArrayList arraylist4;
        ai = new int[1];
        ai1 = new int[1];
        arraylist3 = JVM INSTR new #474 <Class ArrayList>;
        arraylist3.ArrayList();
        arraylist4 = JVM INSTR new #474 <Class ArrayList>;
        arraylist4.ArrayList();
_L4:
        int i;
        arraylist3.clear();
        i = AudioSystem.listAudioPorts(arraylist3, ai1);
        if(i == 0)
            break MISSING_BLOCK_LABEL_85;
        Log.w("AudioManager", "updateAudioPortCache: listAudioPorts failed");
        integer;
        JVM INSTR monitorexit ;
        return i;
        arraylist4.clear();
        i = AudioSystem.listAudioPatches(arraylist4, ai);
        if(i == 0)
            continue; /* Loop/switch isn't completed */
        Log.w("AudioManager", "updateAudioPortCache: listAudioPatches failed");
        integer;
        JVM INSTR monitorexit ;
        return i;
        if(ai[0] != ai1[0]) goto _L4; else goto _L3
_L3:
        i = 0;
_L10:
        if(i >= arraylist4.size())
            break; /* Loop/switch isn't completed */
        int j = 0;
_L6:
        if(j >= ((AudioPatch)arraylist4.get(i)).sources().length)
            break; /* Loop/switch isn't completed */
        AudioPortConfig audioportconfig = updatePortConfig(((AudioPatch)arraylist4.get(i)).sources()[j], arraylist3);
        ((AudioPatch)arraylist4.get(i)).sources()[j] = audioportconfig;
        j++;
        if(true) goto _L6; else goto _L5
_L5:
        j = 0;
_L8:
        if(j >= ((AudioPatch)arraylist4.get(i)).sinks().length)
            break; /* Loop/switch isn't completed */
        AudioPortConfig audioportconfig1 = updatePortConfig(((AudioPatch)arraylist4.get(i)).sinks()[j], arraylist3);
        ((AudioPatch)arraylist4.get(i)).sinks()[j] = audioportconfig1;
        j++;
        if(true) goto _L8; else goto _L7
_L7:
        i++;
        if(true) goto _L10; else goto _L9
_L9:
        Iterator iterator = arraylist4.iterator();
_L20:
        if(!iterator.hasNext()) goto _L12; else goto _L11
_L11:
        AudioPatch audiopatch = (AudioPatch)iterator.next();
        int k = 0;
        AudioPortConfig aaudioportconfig1[] = audiopatch.sources();
        j = 0;
        int l = aaudioportconfig1.length;
_L17:
        i = k;
        if(j >= l) goto _L14; else goto _L13
_L13:
        if(aaudioportconfig1[j] != null) goto _L16; else goto _L15
_L15:
        i = 1;
_L14:
        AudioPortConfig aaudioportconfig[] = audiopatch.sinks();
        k = 0;
        l = aaudioportconfig.length;
_L18:
        j = i;
        if(k < l)
        {
            if(aaudioportconfig[k] != null)
                break MISSING_BLOCK_LABEL_419;
            j = 1;
        }
        if(j == 0)
            continue; /* Loop/switch isn't completed */
        iterator.remove();
        continue; /* Loop/switch isn't completed */
        arraylist;
        throw arraylist;
_L16:
        j++;
          goto _L17
        k++;
          goto _L18
_L12:
        sPreviousAudioPortsCached = sAudioPortsCached;
        sAudioPortsCached = arraylist3;
        sAudioPatchesCached = arraylist4;
        sAudioPortGeneration = Integer.valueOf(ai1[0]);
_L2:
        if(arraylist == null)
            break MISSING_BLOCK_LABEL_467;
        arraylist.clear();
        arraylist.addAll(sAudioPortsCached);
        if(arraylist1 == null)
            break MISSING_BLOCK_LABEL_483;
        arraylist1.clear();
        arraylist1.addAll(sAudioPatchesCached);
        if(arraylist2 == null)
            break MISSING_BLOCK_LABEL_499;
        arraylist2.clear();
        arraylist2.addAll(sPreviousAudioPortsCached);
        integer;
        JVM INSTR monitorexit ;
        return 0;
        if(true) goto _L20; else goto _L19
_L19:
    }

    static AudioPortConfig updatePortConfig(AudioPortConfig audioportconfig, ArrayList arraylist)
    {
        Object obj = audioportconfig.port();
        int i = 0;
        AudioPort audioport;
label0:
        do
        {
label1:
            {
                audioport = ((AudioPort) (obj));
                if(i < arraylist.size())
                {
                    if(!((AudioPort)arraylist.get(i)).handle().equals(((AudioPort) (obj)).handle()))
                        break label1;
                    audioport = (AudioPort)arraylist.get(i);
                }
                if(i == arraylist.size())
                {
                    Log.e("AudioManager", (new StringBuilder()).append("updatePortConfig port not found for handle: ").append(audioport.handle().id()).toString());
                    return null;
                }
                break label0;
            }
            i++;
        } while(true);
        obj = audioportconfig.gain();
        arraylist = ((ArrayList) (obj));
        if(obj != null)
            arraylist = audioport.gain(((AudioGainConfig) (obj)).index()).buildConfig(((AudioGainConfig) (obj)).mode(), ((AudioGainConfig) (obj)).channelMask(), ((AudioGainConfig) (obj)).values(), ((AudioGainConfig) (obj)).rampDurationMs());
        return audioport.buildConfig(audioportconfig.samplingRate(), audioportconfig.channelMask(), audioportconfig.format(), arraylist);
    }

    public int abandonAudioFocus(OnAudioFocusChangeListener onaudiofocuschangelistener)
    {
        return abandonAudioFocus(onaudiofocuschangelistener, null);
    }

    public int abandonAudioFocus(OnAudioFocusChangeListener onaudiofocuschangelistener, AudioAttributes audioattributes)
    {
        unregisterAudioFocusRequest(onaudiofocuschangelistener);
        IAudioService iaudioservice = getService();
        int i;
        try
        {
            i = iaudioservice.abandonAudioFocus(mAudioFocusDispatcher, getIdForAudioFocusListener(onaudiofocuschangelistener), audioattributes, getContext().getOpPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(OnAudioFocusChangeListener onaudiofocuschangelistener)
        {
            throw onaudiofocuschangelistener.rethrowFromSystemServer();
        }
        return i;
    }

    public void abandonAudioFocusForCall()
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.abandonAudioFocus(null, "AudioFocus_For_Phone_Ring_And_Calls", null, getContext().getOpPackageName());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public int abandonAudioFocusRequest(AudioFocusRequest audiofocusrequest)
    {
        if(audiofocusrequest == null)
            throw new IllegalArgumentException("Illegal null AudioFocusRequest");
        else
            return abandonAudioFocus(audiofocusrequest.getOnAudioFocusChangeListener(), audiofocusrequest.getAudioAttributes());
    }

    public void adjustStreamVolume(int i, int j, int k)
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.adjustStreamVolume(i, j, k, getContext().getOpPackageName());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void adjustSuggestedStreamVolume(int i, int j, int k)
    {
        MediaSessionLegacyHelper.getHelper(getContext()).sendAdjustVolumeBy(j, i, k);
    }

    public void adjustVolume(int i, int j)
    {
        MediaSessionLegacyHelper.getHelper(getContext()).sendAdjustVolumeBy(0x80000000, i, j);
    }

    public void avrcpSupportsAbsoluteVolume(String s, boolean flag)
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.avrcpSupportsAbsoluteVolume(s, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void disableSafeMediaVolume()
    {
        try
        {
            getService().disableSafeMediaVolume(mApplicationContext.getOpPackageName());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public int dispatchAudioFocusChange(AudioFocusInfo audiofocusinfo, int i, AudioPolicy audiopolicy)
    {
        if(audiofocusinfo == null)
            throw new NullPointerException("Illegal null AudioFocusInfo");
        if(audiopolicy == null)
            throw new NullPointerException("Illegal null AudioPolicy");
        IAudioService iaudioservice = getService();
        try
        {
            i = iaudioservice.dispatchFocusChange(audiofocusinfo, i, audiopolicy.cb());
        }
        // Misplaced declaration of an exception variable
        catch(AudioFocusInfo audiofocusinfo)
        {
            throw audiofocusinfo.rethrowFromSystemServer();
        }
        return i;
    }

    public void dispatchMediaKeyEvent(KeyEvent keyevent)
    {
        MediaSessionLegacyHelper.getHelper(getContext()).sendMediaButtonEvent(keyevent, false);
    }

    public void forceVolumeControlStream(int i)
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.forceVolumeControlStream(i, mICallBack);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public int generateAudioSessionId()
    {
        int i = AudioSystem.newAudioSessionId();
        if(i > 0)
        {
            return i;
        } else
        {
            Log.e("AudioManager", "Failure to generate a new audio session ID");
            return -1;
        }
    }

    public List getActivePlaybackConfigurations()
    {
        Object obj = getService();
        try
        {
            obj = ((IAudioService) (obj)).getActivePlaybackConfigurations();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return ((List) (obj));
    }

    public List getActiveRecordingConfigurations()
    {
        Object obj = getService();
        try
        {
            obj = ((IAudioService) (obj)).getActiveRecordingConfigurations();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return ((List) (obj));
    }

    public AudioDeviceInfo[] getDevices(int i)
    {
        return getDevicesStatic(i);
    }

    public int getDevicesForStream(int i)
    {
        switch(i)
        {
        case 6: // '\006'
        case 7: // '\007'
        case 9: // '\t'
        default:
            return 0;

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 8: // '\b'
        case 10: // '\n'
            return AudioSystem.getDevicesForStream(i);
        }
    }

    public int getFocusRampTimeMs(int i, AudioAttributes audioattributes)
    {
        IAudioService iaudioservice = getService();
        try
        {
            i = iaudioservice.getFocusRampTimeMs(i, audioattributes);
        }
        // Misplaced declaration of an exception variable
        catch(AudioAttributes audioattributes)
        {
            throw audioattributes.rethrowFromSystemServer();
        }
        return i;
    }

    public int getLastAudibleStreamVolume(int i)
    {
        IAudioService iaudioservice = getService();
        try
        {
            i = iaudioservice.getLastAudibleStreamVolume(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int getMode()
    {
        IAudioService iaudioservice = getService();
        int i;
        try
        {
            i = iaudioservice.getMode();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int getOutputLatency(int i)
    {
        return AudioSystem.getOutputLatency(i);
    }

    public String getParameters(String s)
    {
        return AudioSystem.getParameters(s);
    }

    public String getProperty(String s)
    {
        Object obj = null;
        Object obj1 = null;
        if("android.media.property.OUTPUT_SAMPLE_RATE".equals(s))
        {
            int i = AudioSystem.getPrimaryOutputSamplingRate();
            s = obj1;
            if(i > 0)
                s = Integer.toString(i);
            return s;
        }
        if("android.media.property.OUTPUT_FRAMES_PER_BUFFER".equals(s))
        {
            int j = AudioSystem.getPrimaryOutputFrameCount();
            s = obj;
            if(j > 0)
                s = Integer.toString(j);
            return s;
        }
        if("android.media.property.SUPPORT_MIC_NEAR_ULTRASOUND".equals(s))
            return String.valueOf(getContext().getResources().getBoolean(0x11200ac));
        if("android.media.property.SUPPORT_SPEAKER_NEAR_ULTRASOUND".equals(s))
            return String.valueOf(getContext().getResources().getBoolean(0x11200ae));
        if("android.media.property.SUPPORT_AUDIO_SOURCE_UNPROCESSED".equals(s))
            return String.valueOf(getContext().getResources().getBoolean(0x11200a8));
        else
            return null;
    }

    public int getRingerMode()
    {
        IAudioService iaudioservice = getService();
        int i;
        try
        {
            i = iaudioservice.getRingerModeExternal();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int getRingerModeInternal()
    {
        int i;
        try
        {
            i = getService().getRingerModeInternal();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public IRingtonePlayer getRingtonePlayer()
    {
        IRingtonePlayer iringtoneplayer;
        try
        {
            iringtoneplayer = getService().getRingtonePlayer();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return iringtoneplayer;
    }

    public int getRouting(int i)
    {
        return -1;
    }

    public int getStreamMaxVolume(int i)
    {
        IAudioService iaudioservice = getService();
        try
        {
            i = iaudioservice.getStreamMaxVolume(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int getStreamMinVolume(int i)
    {
        IAudioService iaudioservice = getService();
        try
        {
            i = iaudioservice.getStreamMinVolume(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int getStreamVolume(int i)
    {
        if(isRestricted(i))
            return 0;
        IAudioService iaudioservice = getService();
        try
        {
            i = iaudioservice.getStreamVolume(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int getUiSoundsStreamType()
    {
        IAudioService iaudioservice = getService();
        int i;
        try
        {
            i = iaudioservice.getUiSoundsStreamType();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int getVibrateSetting(int i)
    {
        IAudioService iaudioservice = getService();
        try
        {
            i = iaudioservice.getVibrateSetting(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public void handleBluetoothA2dpDeviceConfigChange(BluetoothDevice bluetoothdevice)
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.handleBluetoothA2dpDeviceConfigChange(bluetoothdevice);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothDevice bluetoothdevice)
        {
            throw bluetoothdevice.rethrowFromSystemServer();
        }
    }

    public boolean isAudioFocusExclusive()
    {
        IAudioService iaudioservice = getService();
        int i;
        boolean flag;
        try
        {
            i = iaudioservice.getCurrentAudioFocus();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(i == 4)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isBluetoothA2dpOn()
    {
        if(AudioSystem.getDeviceConnectionState(128, "") == 1)
            return true;
        if(AudioSystem.getDeviceConnectionState(256, "") == 1)
            return true;
        return AudioSystem.getDeviceConnectionState(512, "") == 1;
    }

    public boolean isBluetoothScoAvailableOffCall()
    {
        return getContext().getResources().getBoolean(0x112002b);
    }

    public boolean isBluetoothScoOn()
    {
        IAudioService iaudioservice = getService();
        boolean flag;
        try
        {
            flag = iaudioservice.isBluetoothScoOn();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isHdmiSystemAudioSupported()
    {
        boolean flag;
        try
        {
            flag = getService().isHdmiSystemAudioSupported();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isMasterMute()
    {
        IAudioService iaudioservice = getService();
        boolean flag;
        try
        {
            flag = iaudioservice.isMasterMute();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isMicrophoneMute()
    {
        return AudioSystem.isMicrophoneMuted();
    }

    public boolean isMusicActive()
    {
        return AudioSystem.isStreamActive(3, 0);
    }

    public boolean isMusicActiveRemotely()
    {
        return AudioSystem.isStreamActiveRemotely(3, 0);
    }

    public boolean isSilentMode()
    {
        int i = getRingerMode();
        boolean flag;
        if(i != 0)
        {
            if(i == 1)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public boolean isSpeakerphoneOn()
    {
        IAudioService iaudioservice = getService();
        boolean flag;
        try
        {
            flag = iaudioservice.isSpeakerphoneOn();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isStreamAffectedByMute(int i)
    {
        boolean flag;
        try
        {
            flag = getService().isStreamAffectedByMute(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isStreamAffectedByRingerMode(int i)
    {
        boolean flag;
        try
        {
            flag = getService().isStreamAffectedByRingerMode(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isStreamMute(int i)
    {
        IAudioService iaudioservice = getService();
        boolean flag;
        try
        {
            flag = iaudioservice.isStreamMute(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isVolumeFixed()
    {
        return mUseFixedVolume;
    }

    public boolean isWiredHeadsetOn()
    {
        return AudioSystem.getDeviceConnectionState(4, "") != 0 || AudioSystem.getDeviceConnectionState(8, "") != 0 || AudioSystem.getDeviceConnectionState(0x4000000, "") != 0;
    }

    public void loadSoundEffects()
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.loadSoundEffects();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void notifyVolumeControllerVisible(IVolumeController ivolumecontroller, boolean flag)
    {
        try
        {
            getService().notifyVolumeControllerVisible(ivolumecontroller, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IVolumeController ivolumecontroller)
        {
            throw ivolumecontroller.rethrowFromSystemServer();
        }
    }

    public void playSoundEffect(int i)
    {
        if(i < 0 || i >= 10)
            return;
        if(!querySoundEffectsEnabled(Process.myUserHandle().getIdentifier()))
            return;
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.playSoundEffect(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void playSoundEffect(int i, float f)
    {
        if(i < 0 || i >= 10)
            return;
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.playSoundEffectVolume(i, f);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void playSoundEffect(int i, int j)
    {
        if(i < 0 || i >= 10)
            return;
        if(!querySoundEffectsEnabled(j))
            return;
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.playSoundEffect(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void preDispatchKeyEvent(KeyEvent keyevent, int i)
    {
        int j = keyevent.getKeyCode();
        if(j != 25 && j != 24 && j != 164 && mVolumeKeyUpTime + 300L > SystemClock.uptimeMillis())
            adjustSuggestedStreamVolume(0, i, 8);
    }

    public void registerAudioDeviceCallback(AudioDeviceCallback audiodevicecallback, Handler handler)
    {
        ArrayMap arraymap = mDeviceCallbacks;
        arraymap;
        JVM INSTR monitorenter ;
        if(audiodevicecallback == null)
            break MISSING_BLOCK_LABEL_100;
        if(mDeviceCallbacks.containsKey(audiodevicecallback) ^ true)
        {
            if(mDeviceCallbacks.size() == 0)
            {
                if(mPortListener == null)
                {
                    OnAmPortUpdateListener onamportupdatelistener = JVM INSTR new #32  <Class AudioManager$OnAmPortUpdateListener>;
                    onamportupdatelistener.this. OnAmPortUpdateListener(null);
                    mPortListener = onamportupdatelistener;
                }
                registerAudioPortUpdateListener(mPortListener);
            }
            NativeEventHandlerDelegate nativeeventhandlerdelegate = JVM INSTR new #27  <Class AudioManager$NativeEventHandlerDelegate>;
            nativeeventhandlerdelegate.this. NativeEventHandlerDelegate(audiodevicecallback, handler);
            mDeviceCallbacks.put(audiodevicecallback, nativeeventhandlerdelegate);
            broadcastDeviceListChange(nativeeventhandlerdelegate.getHandler());
        }
        arraymap;
        JVM INSTR monitorexit ;
        return;
        audiodevicecallback;
        throw audiodevicecallback;
    }

    public void registerAudioFocusRequest(AudioFocusRequest audiofocusrequest)
    {
        Object obj = null;
        Handler handler = audiofocusrequest.getOnAudioFocusChangeListenerHandler();
        if(handler != null)
            obj = (new ServiceEventHandlerDelegate(handler)).getHandler();
        obj = new FocusRequestInfo(audiofocusrequest, ((Handler) (obj)));
        audiofocusrequest = getIdForAudioFocusListener(audiofocusrequest.getOnAudioFocusChangeListener());
        mAudioFocusIdListenerMap.put(audiofocusrequest, obj);
    }

    public void registerAudioPlaybackCallback(AudioPlaybackCallback audioplaybackcallback, Handler handler)
    {
        if(audioplaybackcallback == null)
            throw new IllegalArgumentException("Illegal null AudioPlaybackCallback argument");
        Object obj = mPlaybackCallbackLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        int j;
        if(mPlaybackCallbackList == null)
        {
            ArrayList arraylist = JVM INSTR new #474 <Class ArrayList>;
            arraylist.ArrayList();
            mPlaybackCallbackList = arraylist;
        }
        i = mPlaybackCallbackList.size();
        if(hasPlaybackCallback_sync(audioplaybackcallback))
            break MISSING_BLOCK_LABEL_155;
        List list = mPlaybackCallbackList;
        AudioPlaybackCallbackInfo audioplaybackcallbackinfo = JVM INSTR new #15  <Class AudioManager$AudioPlaybackCallbackInfo>;
        ServiceEventHandlerDelegate serviceeventhandlerdelegate = JVM INSTR new #47  <Class AudioManager$ServiceEventHandlerDelegate>;
        serviceeventhandlerdelegate.this. ServiceEventHandlerDelegate(handler);
        audioplaybackcallbackinfo.AudioPlaybackCallbackInfo(audioplaybackcallback, serviceeventhandlerdelegate.getHandler());
        list.add(audioplaybackcallbackinfo);
        j = mPlaybackCallbackList.size();
        if(i != 0 || j <= 0)
            break MISSING_BLOCK_LABEL_141;
        getService().registerPlaybackCallback(mPlayCb);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        audioplaybackcallback;
        throw audioplaybackcallback.rethrowFromSystemServer();
        audioplaybackcallback;
        obj;
        JVM INSTR monitorexit ;
        throw audioplaybackcallback;
        Log.w("AudioManager", "attempt to call registerAudioPlaybackCallback() on a previouslyregistered callback");
          goto _L1
    }

    public int registerAudioPolicy(AudioPolicy audiopolicy)
    {
        if(audiopolicy == null)
            throw new IllegalArgumentException("Illegal null AudioPolicy argument");
        Object obj = getService();
        try
        {
            obj = ((IAudioService) (obj)).registerAudioPolicy(audiopolicy.getConfig(), audiopolicy.cb(), audiopolicy.hasFocusListener(), audiopolicy.isFocusPolicy());
        }
        // Misplaced declaration of an exception variable
        catch(AudioPolicy audiopolicy)
        {
            throw audiopolicy.rethrowFromSystemServer();
        }
        if(obj == null)
            return -1;
        audiopolicy.setRegistration(((String) (obj)));
        return 0;
    }

    public void registerAudioPortUpdateListener(OnAudioPortUpdateListener onaudioportupdatelistener)
    {
        sAudioPortEventHandler.init();
        sAudioPortEventHandler.registerListener(onaudioportupdatelistener);
    }

    public void registerAudioRecordingCallback(AudioRecordingCallback audiorecordingcallback, Handler handler)
    {
        if(audiorecordingcallback == null)
            throw new IllegalArgumentException("Illegal null AudioRecordingCallback argument");
        Object obj = mRecordCallbackLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        int j;
        if(mRecordCallbackList == null)
        {
            ArrayList arraylist = JVM INSTR new #474 <Class ArrayList>;
            arraylist.ArrayList();
            mRecordCallbackList = arraylist;
        }
        i = mRecordCallbackList.size();
        if(hasRecordCallback_sync(audiorecordingcallback))
            break MISSING_BLOCK_LABEL_157;
        List list = mRecordCallbackList;
        AudioRecordingCallbackInfo audiorecordingcallbackinfo = JVM INSTR new #21  <Class AudioManager$AudioRecordingCallbackInfo>;
        ServiceEventHandlerDelegate serviceeventhandlerdelegate = JVM INSTR new #47  <Class AudioManager$ServiceEventHandlerDelegate>;
        serviceeventhandlerdelegate.this. ServiceEventHandlerDelegate(handler);
        audiorecordingcallbackinfo.AudioRecordingCallbackInfo(audiorecordingcallback, serviceeventhandlerdelegate.getHandler());
        list.add(audiorecordingcallbackinfo);
        j = mRecordCallbackList.size();
        if(i != 0 || j <= 0)
            break MISSING_BLOCK_LABEL_143;
        audiorecordingcallback = getService();
        audiorecordingcallback.registerRecordingCallback(mRecCb);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        audiorecordingcallback;
        throw audiorecordingcallback.rethrowFromSystemServer();
        audiorecordingcallback;
        obj;
        JVM INSTR monitorexit ;
        throw audiorecordingcallback;
        Log.w("AudioManager", "attempt to call registerAudioRecordingCallback() on a previouslyregistered callback");
          goto _L1
    }

    public void registerMediaButtonEventReceiver(PendingIntent pendingintent)
    {
        if(pendingintent == null)
        {
            return;
        } else
        {
            registerMediaButtonIntent(pendingintent, null);
            return;
        }
    }

    public void registerMediaButtonEventReceiver(ComponentName componentname)
    {
        if(componentname == null)
            return;
        if(!componentname.getPackageName().equals(getContext().getPackageName()))
        {
            Log.e("AudioManager", "registerMediaButtonEventReceiver() error: receiver and context package names don't match");
            return;
        } else
        {
            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.setComponent(componentname);
            registerMediaButtonIntent(PendingIntent.getBroadcast(getContext(), 0, intent, 0), componentname);
            return;
        }
    }

    public void registerMediaButtonIntent(PendingIntent pendingintent, ComponentName componentname)
    {
        if(pendingintent == null)
        {
            Log.e("AudioManager", "Cannot call registerMediaButtonIntent() with a null parameter");
            return;
        } else
        {
            MediaSessionLegacyHelper.getHelper(getContext()).addMediaButtonListener(pendingintent, componentname, getContext());
            return;
        }
    }

    public void registerRemoteControlClient(RemoteControlClient remotecontrolclient)
    {
        if(remotecontrolclient == null || remotecontrolclient.getRcMediaIntent() == null)
        {
            return;
        } else
        {
            remotecontrolclient.registerWithSession(MediaSessionLegacyHelper.getHelper(getContext()));
            return;
        }
    }

    public boolean registerRemoteController(RemoteController remotecontroller)
    {
        if(remotecontroller == null)
        {
            return false;
        } else
        {
            remotecontroller.startListeningToSessions();
            return true;
        }
    }

    public void reloadAudioSettings()
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.reloadAudioSettings();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public int requestAudioFocus(AudioFocusRequest audiofocusrequest)
    {
        return requestAudioFocus(audiofocusrequest, null);
    }

    public int requestAudioFocus(AudioFocusRequest audiofocusrequest, AudioPolicy audiopolicy)
    {
        Object obj = null;
        if(audiofocusrequest == null)
            throw new NullPointerException("Illegal null AudioFocusRequest");
        if(audiofocusrequest.locksFocus() && audiopolicy == null)
            throw new IllegalArgumentException("Illegal null audio policy when locking audio focus");
        registerAudioFocusRequest(audiofocusrequest);
        IAudioService iaudioservice = getService();
        int i;
        AudioAttributes audioattributes;
        int j;
        IBinder ibinder;
        IAudioFocusDispatcher iaudiofocusdispatcher;
        String s;
        String s1;
        int k;
        try
        {
            i = getContext().getApplicationInfo().targetSdkVersion;
        }
        catch(NullPointerException nullpointerexception)
        {
            i = android.os.Build.VERSION.SDK_INT;
        }
        try
        {
            audioattributes = audiofocusrequest.getAudioAttributes();
            j = audiofocusrequest.getFocusGain();
            ibinder = mICallBack;
            iaudiofocusdispatcher = mAudioFocusDispatcher;
            s = getIdForAudioFocusListener(audiofocusrequest.getOnAudioFocusChangeListener());
            s1 = getContext().getOpPackageName();
            k = audiofocusrequest.getFlags();
        }
        // Misplaced declaration of an exception variable
        catch(AudioFocusRequest audiofocusrequest)
        {
            throw audiofocusrequest.rethrowFromSystemServer();
        }
        audiofocusrequest = obj;
        if(audiopolicy == null)
            break MISSING_BLOCK_LABEL_121;
        audiofocusrequest = audiopolicy.cb();
        i = iaudioservice.requestAudioFocus(audioattributes, j, ibinder, iaudiofocusdispatcher, s, s1, k, audiofocusrequest, i);
        return i;
    }

    public int requestAudioFocus(OnAudioFocusChangeListener onaudiofocuschangelistener, int i, int j)
    {
        PlayerBase.deprecateStreamTypeForPlayback(i, "AudioManager", "requestAudioFocus()");
        boolean flag = false;
        try
        {
            AudioAttributes.Builder builder = JVM INSTR new #749 <Class AudioAttributes$Builder>;
            builder.AudioAttributes.Builder();
            i = requestAudioFocus(onaudiofocuschangelistener, builder.setInternalLegacyStreamType(i).build(), j, 0);
        }
        // Misplaced declaration of an exception variable
        catch(OnAudioFocusChangeListener onaudiofocuschangelistener)
        {
            Log.e("AudioManager", "Audio focus request denied due to ", onaudiofocuschangelistener);
            i = ((flag) ? 1 : 0);
        }
        return i;
    }

    public int requestAudioFocus(OnAudioFocusChangeListener onaudiofocuschangelistener, AudioAttributes audioattributes, int i, int j)
        throws IllegalArgumentException
    {
        if(j != (j & 3))
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid flags 0x").append(Integer.toHexString(j).toUpperCase()).toString());
        else
            return requestAudioFocus(onaudiofocuschangelistener, audioattributes, i, j & 3, null);
    }

    public int requestAudioFocus(OnAudioFocusChangeListener onaudiofocuschangelistener, AudioAttributes audioattributes, int i, int j, AudioPolicy audiopolicy)
        throws IllegalArgumentException
    {
        boolean flag = true;
        if(audioattributes == null)
            throw new IllegalArgumentException("Illegal null AudioAttributes argument");
        if(!AudioFocusRequest.isValidFocusGain(i))
            throw new IllegalArgumentException("Invalid duration hint");
        if(j != (j & 7))
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal flags 0x").append(Integer.toHexString(j).toUpperCase()).toString());
        if((j & 1) == 1 && onaudiofocuschangelistener == null)
            throw new IllegalArgumentException("Illegal null focus listener when flagged as accepting delayed focus grant");
        if((j & 2) == 2 && onaudiofocuschangelistener == null)
            throw new IllegalArgumentException("Illegal null focus listener when flagged as pausing instead of ducking");
        if((j & 4) == 4 && audiopolicy == null)
            throw new IllegalArgumentException("Illegal null audio policy when locking audio focus");
        onaudiofocuschangelistener = (new AudioFocusRequest.Builder(i)).setOnAudioFocusChangeListenerInt(onaudiofocuschangelistener, null).setAudioAttributes(audioattributes);
        boolean flag1;
        if((j & 1) == 1)
            flag1 = true;
        else
            flag1 = false;
        onaudiofocuschangelistener = onaudiofocuschangelistener.setAcceptsDelayedFocusGain(flag1);
        if((j & 2) == 2)
            flag1 = true;
        else
            flag1 = false;
        onaudiofocuschangelistener = onaudiofocuschangelistener.setWillPauseWhenDucked(flag1);
        if((j & 4) == 4)
            flag1 = flag;
        else
            flag1 = false;
        return requestAudioFocus(onaudiofocuschangelistener.setLocksFocus(flag1).build(), audiopolicy);
    }

    public void requestAudioFocusForCall(int i, int j)
    {
        IAudioService iaudioservice = getService();
        try
        {
            AudioAttributes.Builder builder = JVM INSTR new #749 <Class AudioAttributes$Builder>;
            builder.AudioAttributes.Builder();
            iaudioservice.requestAudioFocus(builder.setInternalLegacyStreamType(i).build(), j, mICallBack, null, "AudioFocus_For_Phone_Ring_And_Calls", getContext().getOpPackageName(), 4, null, 0);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public int setBluetoothA2dpDeviceConnectionState(BluetoothDevice bluetoothdevice, int i, int j)
    {
        IAudioService iaudioservice = getService();
        try
        {
            i = iaudioservice.setBluetoothA2dpDeviceConnectionState(bluetoothdevice, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(BluetoothDevice bluetoothdevice)
        {
            throw bluetoothdevice.rethrowFromSystemServer();
        }
        return i;
    }

    public void setBluetoothA2dpOn(boolean flag)
    {
    }

    public void setBluetoothScoOn(boolean flag)
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.setBluetoothScoOn(flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public int setHdmiSystemAudioSupported(boolean flag)
    {
        int i;
        try
        {
            i = getService().setHdmiSystemAudioSupported(flag);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public void setMasterMute(boolean flag, int i)
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.setMasterMute(flag, i, getContext().getOpPackageName(), UserHandle.getCallingUserId());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setMicrophoneMute(boolean flag)
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.setMicrophoneMute(flag, getContext().getOpPackageName(), UserHandle.getCallingUserId());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setMode(int i)
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.setMode(i, mICallBack, mApplicationContext.getOpPackageName());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setParameter(String s, String s1)
    {
        setParameters((new StringBuilder()).append(s).append("=").append(s1).toString());
    }

    public void setParameters(String s)
    {
        AudioSystem.setParameters(s);
    }

    public void setRingerMode(int i)
    {
        if(!isValidRingerMode(i))
            return;
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.setRingerModeExternal(i, getContext().getOpPackageName());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setRingerModeInternal(int i)
    {
        try
        {
            getService().setRingerModeInternal(i, getContext().getOpPackageName());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setRouting(int i, int j, int k)
    {
    }

    public void setSpeakerphoneOn(boolean flag)
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.setSpeakerphoneOn(flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setStreamMute(int i, boolean flag)
    {
        Log.w("AudioManager", "setStreamMute is deprecated. adjustStreamVolume should be used instead.");
        byte byte0;
        if(flag)
            byte0 = -100;
        else
            byte0 = 100;
        if(i == 0x80000000)
            adjustSuggestedStreamVolume(byte0, i, 0);
        else
            adjustStreamVolume(i, byte0, 0);
    }

    public void setStreamSolo(int i, boolean flag)
    {
        Log.w("AudioManager", "setStreamSolo has been deprecated. Do not use.");
    }

    public void setStreamVolume(int i, int j, int k)
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.setStreamVolume(i, j, k, getContext().getOpPackageName());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setVibrateSetting(int i, int j)
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.setVibrateSetting(i, j);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setVolumeController(IVolumeController ivolumecontroller)
    {
        try
        {
            getService().setVolumeController(ivolumecontroller);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IVolumeController ivolumecontroller)
        {
            throw ivolumecontroller.rethrowFromSystemServer();
        }
    }

    public void setVolumePolicy(VolumePolicy volumepolicy)
    {
        try
        {
            getService().setVolumePolicy(volumepolicy);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(VolumePolicy volumepolicy)
        {
            throw volumepolicy.rethrowFromSystemServer();
        }
    }

    public void setWiredDeviceConnectionState(int i, int j, String s, String s1)
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.setWiredDeviceConnectionState(i, j, s, s1, mApplicationContext.getOpPackageName());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setWiredHeadsetOn(boolean flag)
    {
    }

    public boolean shouldVibrate(int i)
    {
        IAudioService iaudioservice = getService();
        boolean flag;
        try
        {
            flag = iaudioservice.shouldVibrate(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void startBluetoothSco()
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.startBluetoothSco(mICallBack, getContext().getApplicationInfo().targetSdkVersion);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void startBluetoothScoVirtualCall()
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.startBluetoothScoVirtualCall(mICallBack);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void stopBluetoothSco()
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.stopBluetoothSco(mICallBack);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void unloadSoundEffects()
    {
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.unloadSoundEffects();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void unregisterAudioDeviceCallback(AudioDeviceCallback audiodevicecallback)
    {
        ArrayMap arraymap = mDeviceCallbacks;
        arraymap;
        JVM INSTR monitorenter ;
        if(mDeviceCallbacks.containsKey(audiodevicecallback))
        {
            mDeviceCallbacks.remove(audiodevicecallback);
            if(mDeviceCallbacks.size() == 0)
                unregisterAudioPortUpdateListener(mPortListener);
        }
        arraymap;
        JVM INSTR monitorexit ;
        return;
        audiodevicecallback;
        throw audiodevicecallback;
    }

    public void unregisterAudioFocusRequest(OnAudioFocusChangeListener onaudiofocuschangelistener)
    {
        mAudioFocusIdListenerMap.remove(getIdForAudioFocusListener(onaudiofocuschangelistener));
    }

    public void unregisterAudioPlaybackCallback(AudioPlaybackCallback audioplaybackcallback)
    {
        if(audioplaybackcallback == null)
            throw new IllegalArgumentException("Illegal null AudioPlaybackCallback argument");
        Object obj = mPlaybackCallbackLock;
        obj;
        JVM INSTR monitorenter ;
        if(mPlaybackCallbackList != null)
            break MISSING_BLOCK_LABEL_42;
        Log.w("AudioManager", "attempt to call unregisterAudioPlaybackCallback() on a callback that was never registered");
        obj;
        JVM INSTR monitorexit ;
        return;
        int i;
        int j;
        i = mPlaybackCallbackList.size();
        if(!removePlaybackCallback_sync(audioplaybackcallback))
            break MISSING_BLOCK_LABEL_106;
        j = mPlaybackCallbackList.size();
        if(i <= 0 || j != 0)
            break MISSING_BLOCK_LABEL_92;
        getService().unregisterPlaybackCallback(mPlayCb);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        audioplaybackcallback;
        throw audioplaybackcallback.rethrowFromSystemServer();
        audioplaybackcallback;
        obj;
        JVM INSTR monitorexit ;
        throw audioplaybackcallback;
        Log.w("AudioManager", "attempt to call unregisterAudioPlaybackCallback() on a callback already unregistered or never registered");
          goto _L1
    }

    public void unregisterAudioPolicyAsync(AudioPolicy audiopolicy)
    {
        if(audiopolicy == null)
            throw new IllegalArgumentException("Illegal null AudioPolicy argument");
        IAudioService iaudioservice = getService();
        try
        {
            iaudioservice.unregisterAudioPolicyAsync(audiopolicy.cb());
            audiopolicy.setRegistration(null);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(AudioPolicy audiopolicy)
        {
            throw audiopolicy.rethrowFromSystemServer();
        }
    }

    public void unregisterAudioPortUpdateListener(OnAudioPortUpdateListener onaudioportupdatelistener)
    {
        sAudioPortEventHandler.unregisterListener(onaudioportupdatelistener);
    }

    public void unregisterAudioRecordingCallback(AudioRecordingCallback audiorecordingcallback)
    {
        if(audiorecordingcallback == null)
            throw new IllegalArgumentException("Illegal null AudioRecordingCallback argument");
        Object obj = mRecordCallbackLock;
        obj;
        JVM INSTR monitorenter ;
        List list = mRecordCallbackList;
        if(list != null)
            break MISSING_BLOCK_LABEL_34;
        obj;
        JVM INSTR monitorexit ;
        return;
        int i;
        int j;
        i = mRecordCallbackList.size();
        if(!removeRecordCallback_sync(audiorecordingcallback))
            break MISSING_BLOCK_LABEL_102;
        j = mRecordCallbackList.size();
        if(i <= 0 || j != 0)
            break MISSING_BLOCK_LABEL_88;
        audiorecordingcallback = getService();
        audiorecordingcallback.unregisterRecordingCallback(mRecCb);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        audiorecordingcallback;
        throw audiorecordingcallback.rethrowFromSystemServer();
        audiorecordingcallback;
        obj;
        JVM INSTR monitorexit ;
        throw audiorecordingcallback;
        Log.w("AudioManager", "attempt to call unregisterAudioRecordingCallback() on a callback already unregistered or never registered");
          goto _L1
    }

    public void unregisterMediaButtonEventReceiver(PendingIntent pendingintent)
    {
        if(pendingintent == null)
        {
            return;
        } else
        {
            unregisterMediaButtonIntent(pendingintent);
            return;
        }
    }

    public void unregisterMediaButtonEventReceiver(ComponentName componentname)
    {
        if(componentname == null)
        {
            return;
        } else
        {
            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.setComponent(componentname);
            unregisterMediaButtonIntent(PendingIntent.getBroadcast(getContext(), 0, intent, 0));
            return;
        }
    }

    public void unregisterMediaButtonIntent(PendingIntent pendingintent)
    {
        MediaSessionLegacyHelper.getHelper(getContext()).removeMediaButtonListener(pendingintent);
    }

    public void unregisterRemoteControlClient(RemoteControlClient remotecontrolclient)
    {
        if(remotecontrolclient == null || remotecontrolclient.getRcMediaIntent() == null)
        {
            return;
        } else
        {
            remotecontrolclient.unregisterWithSession(MediaSessionLegacyHelper.getHelper(getContext()));
            return;
        }
    }

    public void unregisterRemoteController(RemoteController remotecontroller)
    {
        if(remotecontroller == null)
        {
            return;
        } else
        {
            remotecontroller.stopListeningToSessions();
            return;
        }
    }

    public static final String ACTION_AUDIO_BECOMING_NOISY = "android.media.AUDIO_BECOMING_NOISY";
    public static final String ACTION_HDMI_AUDIO_PLUG = "android.media.action.HDMI_AUDIO_PLUG";
    public static final String ACTION_HEADSET_PLUG = "android.intent.action.HEADSET_PLUG";
    public static final String ACTION_SCO_AUDIO_STATE_CHANGED = "android.media.SCO_AUDIO_STATE_CHANGED";
    public static final String ACTION_SCO_AUDIO_STATE_UPDATED = "android.media.ACTION_SCO_AUDIO_STATE_UPDATED";
    public static final int ADJUST_LOWER = -1;
    public static final int ADJUST_MUTE = -100;
    public static final int ADJUST_RAISE = 1;
    public static final int ADJUST_SAME = 0;
    public static final int ADJUST_TOGGLE_MUTE = 101;
    public static final int ADJUST_UNMUTE = 100;
    public static final int AUDIOFOCUS_FLAGS_APPS = 3;
    public static final int AUDIOFOCUS_FLAGS_SYSTEM = 7;
    public static final int AUDIOFOCUS_FLAG_DELAY_OK = 1;
    public static final int AUDIOFOCUS_FLAG_LOCK = 4;
    public static final int AUDIOFOCUS_FLAG_PAUSES_ON_DUCKABLE_LOSS = 2;
    public static final int AUDIOFOCUS_GAIN = 1;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE = 4;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;
    public static final int AUDIOFOCUS_LOSS = -1;
    public static final int AUDIOFOCUS_LOSS_TRANSIENT = -2;
    public static final int AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK = -3;
    public static final int AUDIOFOCUS_NONE = 0;
    public static final int AUDIOFOCUS_REQUEST_DELAYED = 2;
    public static final int AUDIOFOCUS_REQUEST_FAILED = 0;
    public static final int AUDIOFOCUS_REQUEST_GRANTED = 1;
    static final int AUDIOPORT_GENERATION_INIT = 0;
    public static final int AUDIO_SESSION_ID_GENERATE = 0;
    private static final boolean DEBUG = false;
    public static final int DEVICE_IN_ANLG_DOCK_HEADSET = 0x80000200;
    public static final int DEVICE_IN_BACK_MIC = 0x80000080;
    public static final int DEVICE_IN_BLUETOOTH_SCO_HEADSET = 0x80000008;
    public static final int DEVICE_IN_BUILTIN_MIC = 0x80000004;
    public static final int DEVICE_IN_DGTL_DOCK_HEADSET = 0x80000400;
    public static final int DEVICE_IN_FM_TUNER = 0x80002000;
    public static final int DEVICE_IN_HDMI = 0x80000020;
    public static final int DEVICE_IN_LINE = 0x80008000;
    public static final int DEVICE_IN_LOOPBACK = 0x80040000;
    public static final int DEVICE_IN_SPDIF = 0x80010000;
    public static final int DEVICE_IN_TELEPHONY_RX = 0x80000040;
    public static final int DEVICE_IN_TV_TUNER = 0x80004000;
    public static final int DEVICE_IN_USB_ACCESSORY = 0x80000800;
    public static final int DEVICE_IN_USB_DEVICE = 0x80001000;
    public static final int DEVICE_IN_WIRED_HEADSET = 0x80000010;
    public static final int DEVICE_NONE = 0;
    public static final int DEVICE_OUT_ANLG_DOCK_HEADSET = 2048;
    public static final int DEVICE_OUT_AUX_DIGITAL = 1024;
    public static final int DEVICE_OUT_BLUETOOTH_A2DP = 128;
    public static final int DEVICE_OUT_BLUETOOTH_A2DP_HEADPHONES = 256;
    public static final int DEVICE_OUT_BLUETOOTH_A2DP_SPEAKER = 512;
    public static final int DEVICE_OUT_BLUETOOTH_SCO = 16;
    public static final int DEVICE_OUT_BLUETOOTH_SCO_CARKIT = 64;
    public static final int DEVICE_OUT_BLUETOOTH_SCO_HEADSET = 32;
    public static final int DEVICE_OUT_DEFAULT = 0x40000000;
    public static final int DEVICE_OUT_DGTL_DOCK_HEADSET = 4096;
    public static final int DEVICE_OUT_EARPIECE = 1;
    public static final int DEVICE_OUT_FM = 0x100000;
    public static final int DEVICE_OUT_HDMI = 1024;
    public static final int DEVICE_OUT_HDMI_ARC = 0x40000;
    public static final int DEVICE_OUT_LINE = 0x20000;
    public static final int DEVICE_OUT_REMOTE_SUBMIX = 32768;
    public static final int DEVICE_OUT_SPDIF = 0x80000;
    public static final int DEVICE_OUT_SPEAKER = 2;
    public static final int DEVICE_OUT_TELEPHONY_TX = 0x10000;
    public static final int DEVICE_OUT_USB_ACCESSORY = 8192;
    public static final int DEVICE_OUT_USB_DEVICE = 16384;
    public static final int DEVICE_OUT_USB_HEADSET = 0x4000000;
    public static final int DEVICE_OUT_WIRED_HEADPHONE = 8;
    public static final int DEVICE_OUT_WIRED_HEADSET = 4;
    public static final int ERROR = -1;
    public static final int ERROR_BAD_VALUE = -2;
    public static final int ERROR_DEAD_OBJECT = -6;
    public static final int ERROR_INVALID_OPERATION = -3;
    public static final int ERROR_NO_INIT = -5;
    public static final int ERROR_PERMISSION_DENIED = -4;
    public static final String EXTRA_AUDIO_PLUG_STATE = "android.media.extra.AUDIO_PLUG_STATE";
    public static final String EXTRA_ENCODINGS = "android.media.extra.ENCODINGS";
    public static final String EXTRA_MASTER_VOLUME_MUTED = "android.media.EXTRA_MASTER_VOLUME_MUTED";
    public static final String EXTRA_MAX_CHANNEL_COUNT = "android.media.extra.MAX_CHANNEL_COUNT";
    public static final String EXTRA_PREV_VOLUME_STREAM_DEVICES = "android.media.EXTRA_PREV_VOLUME_STREAM_DEVICES";
    public static final String EXTRA_PREV_VOLUME_STREAM_VALUE = "android.media.EXTRA_PREV_VOLUME_STREAM_VALUE";
    public static final String EXTRA_RINGER_MODE = "android.media.EXTRA_RINGER_MODE";
    public static final String EXTRA_SCO_AUDIO_PREVIOUS_STATE = "android.media.extra.SCO_AUDIO_PREVIOUS_STATE";
    public static final String EXTRA_SCO_AUDIO_STATE = "android.media.extra.SCO_AUDIO_STATE";
    public static final String EXTRA_STREAM_VOLUME_MUTED = "android.media.EXTRA_STREAM_VOLUME_MUTED";
    public static final String EXTRA_VIBRATE_SETTING = "android.media.EXTRA_VIBRATE_SETTING";
    public static final String EXTRA_VIBRATE_TYPE = "android.media.EXTRA_VIBRATE_TYPE";
    public static final String EXTRA_VOLUME_STREAM_DEVICES = "android.media.EXTRA_VOLUME_STREAM_DEVICES";
    public static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
    public static final String EXTRA_VOLUME_STREAM_TYPE_ALIAS = "android.media.EXTRA_VOLUME_STREAM_TYPE_ALIAS";
    public static final String EXTRA_VOLUME_STREAM_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE";
    public static final int FLAG_ACTIVE_MEDIA_ONLY = 512;
    public static final int FLAG_ALLOW_RINGER_MODES = 2;
    public static final int FLAG_BLUETOOTH_ABS_VOLUME = 64;
    public static final int FLAG_FIXED_VOLUME = 32;
    public static final int FLAG_FROM_KEY = 4096;
    public static final int FLAG_HDMI_SYSTEM_AUDIO_VOLUME = 256;
    private static final String FLAG_NAMES[] = {
        "FLAG_SHOW_UI", "FLAG_ALLOW_RINGER_MODES", "FLAG_PLAY_SOUND", "FLAG_REMOVE_SOUND_AND_VIBRATE", "FLAG_VIBRATE", "FLAG_FIXED_VOLUME", "FLAG_BLUETOOTH_ABS_VOLUME", "FLAG_SHOW_SILENT_HINT", "FLAG_HDMI_SYSTEM_AUDIO_VOLUME", "FLAG_ACTIVE_MEDIA_ONLY", 
        "FLAG_SHOW_UI_WARNINGS", "FLAG_SHOW_VIBRATE_HINT", "FLAG_FROM_KEY"
    };
    public static final int FLAG_PLAY_SOUND = 4;
    public static final int FLAG_REMOVE_SOUND_AND_VIBRATE = 8;
    public static final int FLAG_SHOW_SILENT_HINT = 128;
    public static final int FLAG_SHOW_UI = 1;
    public static final int FLAG_SHOW_UI_WARNINGS = 1024;
    public static final int FLAG_SHOW_VIBRATE_HINT = 2048;
    public static final int FLAG_VIBRATE = 16;
    public static final int FX_FOCUS_NAVIGATION_DOWN = 2;
    public static final int FX_FOCUS_NAVIGATION_LEFT = 3;
    public static final int FX_FOCUS_NAVIGATION_RIGHT = 4;
    public static final int FX_FOCUS_NAVIGATION_UP = 1;
    public static final int FX_KEYPRESS_DELETE = 7;
    public static final int FX_KEYPRESS_INVALID = 9;
    public static final int FX_KEYPRESS_RETURN = 8;
    public static final int FX_KEYPRESS_SPACEBAR = 6;
    public static final int FX_KEYPRESS_STANDARD = 5;
    public static final int FX_KEY_CLICK = 0;
    public static final int GET_DEVICES_ALL = 3;
    public static final int GET_DEVICES_INPUTS = 1;
    public static final int GET_DEVICES_OUTPUTS = 2;
    public static final String INTERNAL_RINGER_MODE_CHANGED_ACTION = "android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION";
    public static final String MASTER_MUTE_CHANGED_ACTION = "android.media.MASTER_MUTE_CHANGED_ACTION";
    public static final int MODE_CURRENT = -1;
    public static final int MODE_INVALID = -2;
    public static final int MODE_IN_CALL = 2;
    public static final int MODE_IN_COMMUNICATION = 3;
    public static final int MODE_NORMAL = 0;
    public static final int MODE_RINGTONE = 1;
    private static final int MSG_DEVICES_CALLBACK_REGISTERED = 0;
    private static final int MSG_DEVICES_DEVICES_ADDED = 1;
    private static final int MSG_DEVICES_DEVICES_REMOVED = 2;
    private static final int MSSG_FOCUS_CHANGE = 0;
    private static final int MSSG_PLAYBACK_CONFIG_CHANGE = 2;
    private static final int MSSG_RECORDING_CONFIG_CHANGE = 1;
    public static final int NUM_SOUND_EFFECTS = 10;
    public static final int NUM_STREAMS = 5;
    public static final String PROPERTY_OUTPUT_FRAMES_PER_BUFFER = "android.media.property.OUTPUT_FRAMES_PER_BUFFER";
    public static final String PROPERTY_OUTPUT_SAMPLE_RATE = "android.media.property.OUTPUT_SAMPLE_RATE";
    public static final String PROPERTY_SUPPORT_AUDIO_SOURCE_UNPROCESSED = "android.media.property.SUPPORT_AUDIO_SOURCE_UNPROCESSED";
    public static final String PROPERTY_SUPPORT_MIC_NEAR_ULTRASOUND = "android.media.property.SUPPORT_MIC_NEAR_ULTRASOUND";
    public static final String PROPERTY_SUPPORT_SPEAKER_NEAR_ULTRASOUND = "android.media.property.SUPPORT_SPEAKER_NEAR_ULTRASOUND";
    public static final int RECORD_CONFIG_EVENT_START = 1;
    public static final int RECORD_CONFIG_EVENT_STOP = 0;
    public static final String RINGER_MODE_CHANGED_ACTION = "android.media.RINGER_MODE_CHANGED";
    public static final int RINGER_MODE_MAX = 2;
    public static final int RINGER_MODE_NORMAL = 2;
    public static final int RINGER_MODE_SILENT = 0;
    public static final int RINGER_MODE_VIBRATE = 1;
    public static final int ROUTE_ALL = -1;
    public static final int ROUTE_BLUETOOTH = 4;
    public static final int ROUTE_BLUETOOTH_A2DP = 16;
    public static final int ROUTE_BLUETOOTH_SCO = 4;
    public static final int ROUTE_EARPIECE = 1;
    public static final int ROUTE_HEADSET = 8;
    public static final int ROUTE_SPEAKER = 2;
    public static final int SCO_AUDIO_STATE_CONNECTED = 1;
    public static final int SCO_AUDIO_STATE_CONNECTING = 2;
    public static final int SCO_AUDIO_STATE_DISCONNECTED = 0;
    public static final int SCO_AUDIO_STATE_ERROR = -1;
    public static final int STREAM_ACCESSIBILITY = 10;
    public static final int STREAM_ALARM = 4;
    public static final int STREAM_BLUETOOTH_SCO = 6;
    public static final String STREAM_DEVICES_CHANGED_ACTION = "android.media.STREAM_DEVICES_CHANGED_ACTION";
    public static final int STREAM_DTMF = 8;
    public static final int STREAM_MUSIC = 3;
    public static final String STREAM_MUTE_CHANGED_ACTION = "android.media.STREAM_MUTE_CHANGED_ACTION";
    public static final int STREAM_NOTIFICATION = 5;
    public static final int STREAM_RING = 2;
    public static final int STREAM_SYSTEM = 1;
    public static final int STREAM_SYSTEM_ENFORCED = 7;
    public static final int STREAM_TTS = 9;
    public static final int STREAM_VOICE_CALL = 0;
    public static final int SUCCESS = 0;
    private static final String TAG = "AudioManager";
    public static final int USE_DEFAULT_STREAM_TYPE = 0x80000000;
    public static final String VIBRATE_SETTING_CHANGED_ACTION = "android.media.VIBRATE_SETTING_CHANGED";
    public static final int VIBRATE_SETTING_OFF = 0;
    public static final int VIBRATE_SETTING_ON = 1;
    public static final int VIBRATE_SETTING_ONLY_SILENT = 2;
    public static final int VIBRATE_TYPE_NOTIFICATION = 1;
    public static final int VIBRATE_TYPE_RINGER = 0;
    public static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    private static IAppOpsService sAppOpsService;
    static ArrayList sAudioPatchesCached = new ArrayList();
    private static final AudioPortEventHandler sAudioPortEventHandler = new AudioPortEventHandler();
    static Integer sAudioPortGeneration = new Integer(0);
    static ArrayList sAudioPortsCached = new ArrayList();
    static ArrayList sPreviousAudioPortsCached = new ArrayList();
    private static IAudioService sService;
    private Context mApplicationContext;
    private final IAudioFocusDispatcher mAudioFocusDispatcher;
    private final ConcurrentHashMap mAudioFocusIdListenerMap;
    private ArrayMap mDeviceCallbacks;
    private final IBinder mICallBack;
    private Context mOriginalContext;
    private final IPlaybackConfigDispatcher mPlayCb;
    private List mPlaybackCallbackList;
    private final Object mPlaybackCallbackLock;
    private OnAmPortUpdateListener mPortListener;
    private ArrayList mPreviousPorts;
    private final IRecordingConfigDispatcher mRecCb;
    private List mRecordCallbackList;
    private final Object mRecordCallbackLock;
    private final ServiceEventHandlerDelegate mServiceEventHandlerDelegate;
    private final boolean mUseFixedVolume;
    private final boolean mUseVolumeKeySounds;
    private long mVolumeKeyUpTime;


    // Unreferenced inner class android/media/AudioManager$NativeEventHandlerDelegate$1

/* anonymous class */
    class NativeEventHandlerDelegate._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 2: default 32
        //                       0 61
        //                       1 61
        //                       2 85;
               goto _L1 _L2 _L2 _L3
_L1:
            Log.e("AudioManager", (new StringBuilder()).append("Unknown native event type: ").append(message.what).toString());
_L5:
            return;
_L2:
            if(callback != null)
                callback.onAudioDevicesAdded((AudioDeviceInfo[])message.obj);
            continue; /* Loop/switch isn't completed */
_L3:
            if(callback != null)
                callback.onAudioDevicesRemoved((AudioDeviceInfo[])message.obj);
            if(true) goto _L5; else goto _L4
_L4:
        }

        final NativeEventHandlerDelegate this$1;
        final AudioDeviceCallback val$callback;

            
            {
                this$1 = final_nativeeventhandlerdelegate;
                callback = audiodevicecallback;
                super(Looper.this);
            }
    }


    // Unreferenced inner class android/media/AudioManager$ServiceEventHandlerDelegate$1

/* anonymous class */
    class ServiceEventHandlerDelegate._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 2: default 32
        //                       0 61
        //                       1 148
        //                       2 177;
               goto _L1 _L2 _L3 _L4
_L1:
            Log.e("AudioManager", (new StringBuilder()).append("Unknown event ").append(message.what).toString());
_L6:
            return;
_L2:
            Object obj = AudioManager._2D_wrap0(_fld0, (String)message.obj);
            if(obj != null)
            {
                obj = ((FocusRequestInfo) (obj)).mRequest.getOnAudioFocusChangeListener();
                if(obj != null)
                {
                    Log.d("AudioManager", (new StringBuilder()).append("dispatching onAudioFocusChange(").append(message.arg1).append(") to ").append(message.obj).toString());
                    ((OnAudioFocusChangeListener) (obj)).onAudioFocusChange(message.arg1);
                }
            }
            continue; /* Loop/switch isn't completed */
_L3:
            message = (RecordConfigChangeCallbackData)message.obj;
            if(((RecordConfigChangeCallbackData) (message)).mCb != null)
                ((RecordConfigChangeCallbackData) (message)).mCb.onRecordingConfigChanged(((RecordConfigChangeCallbackData) (message)).mConfigs);
            continue; /* Loop/switch isn't completed */
_L4:
            message = (PlaybackConfigChangeCallbackData)message.obj;
            if(((PlaybackConfigChangeCallbackData) (message)).mCb != null)
                ((PlaybackConfigChangeCallbackData) (message)).mCb.onPlaybackConfigChanged(((PlaybackConfigChangeCallbackData) (message)).mConfigs);
            if(true) goto _L6; else goto _L5
_L5:
        }

        final ServiceEventHandlerDelegate this$1;

            
            {
                this$1 = ServiceEventHandlerDelegate.this;
                super(looper);
            }
    }

}
