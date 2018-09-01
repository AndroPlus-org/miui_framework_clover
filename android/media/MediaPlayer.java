// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.app.ActivityThread;
import android.content.*;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.os.*;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.util.Log;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.android.internal.util.Preconditions;
import java.io.*;
import java.lang.ref.WeakReference;
import java.net.*;
import java.nio.ByteOrder;
import java.util.*;
import libcore.io.*;

// Referenced classes of package android.media:
//            PlayerBase, VolumeAutomation, MediaDrm, AudioSystem, 
//            SubtitleController, UnsupportedSchemeException, NotProvisionedException, ResourceBusyException, 
//            SubtitleTrack, MediaHTTPService, MediaPlayerInjector, MediaFormat, 
//            SRTRenderer, VolumeShaper, PlaybackParams, Metadata, 
//            MediaTimestamp, DeniedByServerException, AudioAttributes, ExtraRingtoneManager, 
//            RingtoneManager, MediaDataSource, BufferingParams, MediaTimeProvider, 
//            SyncParams, TimedText, SubtitleData, TimedMetaData, 
//            MediaDrmException

public class MediaPlayer extends PlayerBase
    implements SubtitleController.Listener, VolumeAutomation
{
    public static final class DrmInfo
    {

        static DrmInfo _2D_wrap0(DrmInfo drminfo)
        {
            return drminfo.makeCopy();
        }

        private String arrToHex(byte abyte0[])
        {
            String s = "0x";
            for(int i = 0; i < abyte0.length; i++)
                s = (new StringBuilder()).append(s).append(String.format("%02x", new Object[] {
                    Byte.valueOf(abyte0[i])
                })).toString();

            return s;
        }

        private UUID bytesToUUID(byte abyte0[])
        {
            long l = 0L;
            long l1 = 0L;
            for(int i = 0; i < 8; i++)
            {
                l |= ((long)abyte0[i] & 255L) << (7 - i) * 8;
                l1 |= ((long)abyte0[i + 8] & 255L) << (7 - i) * 8;
            }

            return new UUID(l, l1);
        }

        private DrmInfo makeCopy()
        {
            return new DrmInfo(mapPssh, supportedSchemes);
        }

        private Map parsePSSH(byte abyte0[], int i)
        {
            HashMap hashmap = new HashMap();
            int j = i;
            int k = 0;
            int l = 0;
            while(j > 0) 
            {
                if(j < 16)
                {
                    Log.w("MediaPlayer", String.format("parsePSSH: len is too short to parse UUID: (%d < 16) pssh: %d", new Object[] {
                        Integer.valueOf(j), Integer.valueOf(i)
                    }));
                    return null;
                }
                UUID uuid = bytesToUUID(Arrays.copyOfRange(abyte0, l, l + 16));
                int i1 = l + 16;
                l = j - 16;
                if(l < 4)
                {
                    Log.w("MediaPlayer", String.format("parsePSSH: len is too short to parse datalen: (%d < 4) pssh: %d", new Object[] {
                        Integer.valueOf(l), Integer.valueOf(i)
                    }));
                    return null;
                }
                byte abyte1[] = Arrays.copyOfRange(abyte0, i1, i1 + 4);
                int j1;
                if(ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN)
                    j = (abyte1[3] & 0xff) << 24 | (abyte1[2] & 0xff) << 16 | (abyte1[1] & 0xff) << 8 | abyte1[0] & 0xff;
                else
                    j = (abyte1[0] & 0xff) << 24 | (abyte1[1] & 0xff) << 16 | (abyte1[2] & 0xff) << 8 | abyte1[3] & 0xff;
                i1 += 4;
                j1 = l - 4;
                if(j1 < j)
                {
                    Log.w("MediaPlayer", String.format("parsePSSH: len is too short to parse data: (%d < %d) pssh: %d", new Object[] {
                        Integer.valueOf(j1), Integer.valueOf(j), Integer.valueOf(i)
                    }));
                    return null;
                }
                abyte1 = Arrays.copyOfRange(abyte0, i1, i1 + j);
                l = i1 + j;
                j = j1 - j;
                Log.v("MediaPlayer", String.format("parsePSSH[%d]: <%s, %s> pssh: %d", new Object[] {
                    Integer.valueOf(k), uuid, arrToHex(abyte1), Integer.valueOf(i)
                }));
                k++;
                hashmap.put(uuid, abyte1);
            }
            return hashmap;
        }

        public Map getPssh()
        {
            return mapPssh;
        }

        public UUID[] getSupportedSchemes()
        {
            return supportedSchemes;
        }

        private Map mapPssh;
        private UUID supportedSchemes[];

        private DrmInfo(Parcel parcel)
        {
            Log.v("MediaPlayer", (new StringBuilder()).append("DrmInfo(").append(parcel).append(") size ").append(parcel.dataSize()).toString());
            int i = parcel.readInt();
            byte abyte0[] = new byte[i];
            parcel.readByteArray(abyte0);
            Log.v("MediaPlayer", (new StringBuilder()).append("DrmInfo() PSSH: ").append(arrToHex(abyte0)).toString());
            mapPssh = parsePSSH(abyte0, i);
            Log.v("MediaPlayer", (new StringBuilder()).append("DrmInfo() PSSH: ").append(mapPssh).toString());
            int j = parcel.readInt();
            supportedSchemes = new UUID[j];
            for(int k = 0; k < j; k++)
            {
                byte abyte1[] = new byte[16];
                parcel.readByteArray(abyte1);
                supportedSchemes[k] = bytesToUUID(abyte1);
                Log.v("MediaPlayer", (new StringBuilder()).append("DrmInfo() supportedScheme[").append(k).append("]: ").append(supportedSchemes[k]).toString());
            }

            Log.v("MediaPlayer", (new StringBuilder()).append("DrmInfo() Parcel psshsize: ").append(i).append(" supportedDRMsCount: ").append(j).toString());
        }

        DrmInfo(Parcel parcel, DrmInfo drminfo)
        {
            this(parcel);
        }

        private DrmInfo(Map map, UUID auuid[])
        {
            mapPssh = map;
            supportedSchemes = auuid;
        }
    }

    private class EventHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            if(MediaPlayer._2D_get5(mMediaPlayer) == 0L)
            {
                Log.w("MediaPlayer", "mediaplayer went away with unhandled events");
                return;
            }
            message.what;
            JVM INSTR lookupswitch 16: default 164
        //                       0: 468
        //                       1: 193
        //                       2: 405
        //                       3: 508
        //                       4: 535
        //                       5: 578
        //                       6: 469
        //                       7: 469
        //                       8: 452
        //                       9: 557
        //                       99: 973
        //                       100: 609
        //                       200: 743
        //                       201: 1050
        //                       202: 1106
        //                       210: 243;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16
_L6:
            break MISSING_BLOCK_LABEL_535;
_L10:
            break MISSING_BLOCK_LABEL_557;
_L7:
            break MISSING_BLOCK_LABEL_578;
_L12:
            break MISSING_BLOCK_LABEL_609;
_L13:
            break MISSING_BLOCK_LABEL_743;
_L14:
            break MISSING_BLOCK_LABEL_1050;
_L15:
            break MISSING_BLOCK_LABEL_1106;
_L2:
            break; /* Loop/switch isn't completed */
_L1:
            Log.e("MediaPlayer", (new StringBuilder()).append("Unknown message type ").append(message.what).toString());
            return;
_L3:
            try
            {
                MediaPlayer._2D_wrap2(MediaPlayer.this);
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                sendMessage(obtainMessage(100, 1, -1010, null));
            }
            message = MediaPlayer._2D_get13(MediaPlayer.this);
            if(message != null)
                message.onPrepared(mMediaPlayer);
            return;
_L16:
            Log.v("MediaPlayer", (new StringBuilder()).append("MEDIA_DRM_INFO ").append(MediaPlayer._2D_get9(MediaPlayer.this)).toString());
            if(message.obj != null) goto _L18; else goto _L17
_L17:
            Log.w("MediaPlayer", "MEDIA_DRM_INFO msg.obj=NULL");
_L24:
            return;
_L18:
            if(!(message.obj instanceof Parcel)) goto _L20; else goto _L19
_L19:
            OnDrmInfoHandlerDelegate ondrminfohandlerdelegate = null;
            Object obj = MediaPlayer._2D_get1(MediaPlayer.this);
            obj;
            JVM INSTR monitorenter ;
            message = ondrminfohandlerdelegate;
            if(MediaPlayer._2D_get9(MediaPlayer.this) == null) goto _L22; else goto _L21
_L21:
            message = ondrminfohandlerdelegate;
            if(MediaPlayer._2D_get0(MediaPlayer.this) != null)
                message = DrmInfo._2D_wrap0(MediaPlayer._2D_get0(MediaPlayer.this));
_L22:
            ondrminfohandlerdelegate = MediaPlayer._2D_get9(MediaPlayer.this);
            obj;
            JVM INSTR monitorexit ;
            if(ondrminfohandlerdelegate != null)
                ondrminfohandlerdelegate.notifyClient(message);
            continue; /* Loop/switch isn't completed */
            message;
            throw message;
_L20:
            Log.w("MediaPlayer", (new StringBuilder()).append("MEDIA_DRM_INFO msg.obj of unexpected type ").append(message.obj).toString());
            if(true) goto _L24; else goto _L23
_L23:
            break; /* Loop/switch isn't completed */
_L4:
            MediaPlayer._2D_get7(MediaPlayer.this).onCompletion(mMediaPlayer);
            message = MediaPlayer._2D_get8(MediaPlayer.this);
            if(message != null)
                message.onCompletion(mMediaPlayer);
            MediaPlayer._2D_wrap4(MediaPlayer.this, false);
            return;
_L9:
            message = MediaPlayer._2D_get21(MediaPlayer.this);
            if(message != null)
                message.onStopped();
_L25:
            return;
_L8:
            TimeProvider timeprovider = MediaPlayer._2D_get21(MediaPlayer.this);
            if(timeprovider != null)
            {
                boolean flag;
                if(message.what == 7)
                    flag = true;
                else
                    flag = false;
                timeprovider.onPaused(flag);
            }
            if(true) goto _L25; else goto _L5
_L5:
            OnBufferingUpdateListener onbufferingupdatelistener = MediaPlayer._2D_get6(MediaPlayer.this);
            if(onbufferingupdatelistener != null)
                onbufferingupdatelistener.onBufferingUpdate(mMediaPlayer, message.arg1);
            return;
            message = MediaPlayer._2D_get14(MediaPlayer.this);
            if(message != null)
                message.onSeekComplete(mMediaPlayer);
            message = MediaPlayer._2D_get21(MediaPlayer.this);
            if(message != null)
                message.onSeekComplete(mMediaPlayer);
            return;
            OnVideoSizeChangedListener onvideosizechangedlistener = MediaPlayer._2D_get18(MediaPlayer.this);
            if(onvideosizechangedlistener != null)
                onvideosizechangedlistener.onVideoSizeChanged(mMediaPlayer, message.arg1, message.arg2);
            return;
            Log.e("MediaPlayer", (new StringBuilder()).append("Error (").append(message.arg1).append(",").append(message.arg2).append(")").toString());
            boolean flag1 = false;
            OnErrorListener onerrorlistener = MediaPlayer._2D_get11(MediaPlayer.this);
            if(onerrorlistener != null)
                flag1 = onerrorlistener.onError(mMediaPlayer, message.arg1, message.arg2);
            MediaPlayer._2D_get7(MediaPlayer.this).onCompletion(mMediaPlayer);
            message = MediaPlayer._2D_get8(MediaPlayer.this);
            if(message != null && flag1 ^ true)
                message.onCompletion(mMediaPlayer);
            MediaPlayer._2D_wrap4(MediaPlayer.this, false);
            return;
            message.arg1;
            JVM INSTR lookupswitch 5: default 796
        //                       700: 828
        //                       701: 933
        //                       702: 933
        //                       802: 876
        //                       803: 883;
               goto _L26 _L27 _L28 _L28 _L29 _L30
_L26:
            OnInfoListener oninfolistener = MediaPlayer._2D_get12(MediaPlayer.this);
            if(oninfolistener != null)
                oninfolistener.onInfo(mMediaPlayer, message.arg1, message.arg2);
            return;
_L27:
            Log.i("MediaPlayer", (new StringBuilder()).append("Info (").append(message.arg1).append(",").append(message.arg2).append(")").toString());
            continue; /* Loop/switch isn't completed */
_L29:
            try
            {
                MediaPlayer._2D_wrap2(MediaPlayer.this);
            }
            catch(RuntimeException runtimeexception)
            {
                sendMessage(obtainMessage(100, 1, -1010, null));
            }
_L30:
            message.arg1 = 802;
            if(MediaPlayer._2D_get20(MediaPlayer.this) != null)
                MediaPlayer._2D_get20(MediaPlayer.this).selectDefaultTrack();
            continue; /* Loop/switch isn't completed */
_L28:
            TimeProvider timeprovider1 = MediaPlayer._2D_get21(MediaPlayer.this);
            if(timeprovider1 != null)
            {
                boolean flag2;
                if(message.arg1 == 701)
                    flag2 = true;
                else
                    flag2 = false;
                timeprovider1.onBuffering(flag2);
            }
            if(true) goto _L26; else goto _L11
_L11:
            OnTimedTextListener ontimedtextlistener = MediaPlayer._2D_get17(MediaPlayer.this);
            if(ontimedtextlistener == null)
                return;
            if(message.obj == null)
                ontimedtextlistener.onTimedText(mMediaPlayer, null);
            else
            if(message.obj instanceof Parcel)
            {
                Parcel parcel = (Parcel)message.obj;
                message = new TimedText(parcel);
                parcel.recycle();
                ontimedtextlistener.onTimedText(mMediaPlayer, message);
            }
            return;
            OnSubtitleDataListener onsubtitledatalistener = MediaPlayer._2D_get15(MediaPlayer.this);
            if(onsubtitledatalistener == null)
                return;
            if(message.obj instanceof Parcel)
            {
                Parcel parcel1 = (Parcel)message.obj;
                message = new SubtitleData(parcel1);
                parcel1.recycle();
                onsubtitledatalistener.onSubtitleData(mMediaPlayer, message);
            }
            return;
            OnTimedMetaDataAvailableListener ontimedmetadataavailablelistener = MediaPlayer._2D_get16(MediaPlayer.this);
            if(ontimedmetadataavailablelistener == null)
                return;
            if(message.obj instanceof Parcel)
            {
                message = (Parcel)message.obj;
                TimedMetaData timedmetadata = TimedMetaData.createTimedMetaDataFromParcel(message);
                message.recycle();
                ontimedmetadataavailablelistener.onTimedMetaDataAvailable(mMediaPlayer, timedmetadata);
            }
            return;
        }

        private MediaPlayer mMediaPlayer;
        final MediaPlayer this$0;

        public EventHandler(MediaPlayer mediaplayer1, Looper looper)
        {
            this$0 = MediaPlayer.this;
            super(looper);
            mMediaPlayer = mediaplayer1;
        }
    }

    public static final class MetricsConstants
    {

        public static final String CODEC_AUDIO = "android.media.mediaplayer.audio.codec";
        public static final String CODEC_VIDEO = "android.media.mediaplayer.video.codec";
        public static final String DURATION = "android.media.mediaplayer.durationMs";
        public static final String ERRORS = "android.media.mediaplayer.err";
        public static final String ERROR_CODE = "android.media.mediaplayer.errcode";
        public static final String FRAMES = "android.media.mediaplayer.frames";
        public static final String FRAMES_DROPPED = "android.media.mediaplayer.dropped";
        public static final String HEIGHT = "android.media.mediaplayer.height";
        public static final String MIME_TYPE_AUDIO = "android.media.mediaplayer.audio.mime";
        public static final String MIME_TYPE_VIDEO = "android.media.mediaplayer.video.mime";
        public static final String PLAYING = "android.media.mediaplayer.playingMs";
        public static final String WIDTH = "android.media.mediaplayer.width";

        private MetricsConstants()
        {
        }
    }

    public static final class NoDrmSchemeException extends MediaDrmException
    {

        public NoDrmSchemeException(String s)
        {
            super(s);
        }
    }

    public static interface OnBufferingUpdateListener
    {

        public abstract void onBufferingUpdate(MediaPlayer mediaplayer, int i);
    }

    public static interface OnCompletionListener
    {

        public abstract void onCompletion(MediaPlayer mediaplayer);
    }

    public static interface OnDrmConfigHelper
    {

        public abstract void onDrmConfig(MediaPlayer mediaplayer);
    }

    private class OnDrmInfoHandlerDelegate
    {

        static MediaPlayer _2D_get0(OnDrmInfoHandlerDelegate ondrminfohandlerdelegate)
        {
            return ondrminfohandlerdelegate.mMediaPlayer;
        }

        static OnDrmInfoListener _2D_get1(OnDrmInfoHandlerDelegate ondrminfohandlerdelegate)
        {
            return ondrminfohandlerdelegate.mOnDrmInfoListener;
        }

        void notifyClient(DrmInfo drminfo)
        {
            if(mHandler != null)
                mHandler.post(drminfo. new Runnable() {

                    public void run()
                    {
                        OnDrmInfoHandlerDelegate._2D_get1(OnDrmInfoHandlerDelegate.this).onDrmInfo(OnDrmInfoHandlerDelegate._2D_get0(OnDrmInfoHandlerDelegate.this), drmInfo);
                    }

                    final OnDrmInfoHandlerDelegate this$1;
                    final DrmInfo val$drmInfo;

            
            {
                this$1 = final_ondrminfohandlerdelegate;
                drmInfo = DrmInfo.this;
                super();
            }
                }
);
            else
                mOnDrmInfoListener.onDrmInfo(mMediaPlayer, drminfo);
        }

        private Handler mHandler;
        private MediaPlayer mMediaPlayer;
        private OnDrmInfoListener mOnDrmInfoListener;
        final MediaPlayer this$0;

        OnDrmInfoHandlerDelegate(MediaPlayer mediaplayer1, OnDrmInfoListener ondrminfolistener, Handler handler)
        {
            this$0 = MediaPlayer.this;
            super();
            mMediaPlayer = mediaplayer1;
            mOnDrmInfoListener = ondrminfolistener;
            if(handler != null)
                mHandler = handler;
        }
    }

    public static interface OnDrmInfoListener
    {

        public abstract void onDrmInfo(MediaPlayer mediaplayer, DrmInfo drminfo);
    }

    private class OnDrmPreparedHandlerDelegate
    {

        static MediaPlayer _2D_get0(OnDrmPreparedHandlerDelegate ondrmpreparedhandlerdelegate)
        {
            return ondrmpreparedhandlerdelegate.mMediaPlayer;
        }

        static OnDrmPreparedListener _2D_get1(OnDrmPreparedHandlerDelegate ondrmpreparedhandlerdelegate)
        {
            return ondrmpreparedhandlerdelegate.mOnDrmPreparedListener;
        }

        void notifyClient(int i)
        {
            if(mHandler != null)
                mHandler.post(i. new Runnable() {

                    public void run()
                    {
                        OnDrmPreparedHandlerDelegate._2D_get1(OnDrmPreparedHandlerDelegate.this).onDrmPrepared(OnDrmPreparedHandlerDelegate._2D_get0(OnDrmPreparedHandlerDelegate.this), status);
                    }

                    final OnDrmPreparedHandlerDelegate this$1;
                    final int val$status;

            
            {
                this$1 = final_ondrmpreparedhandlerdelegate;
                status = I.this;
                super();
            }
                }
);
            else
                Log.e("MediaPlayer", "OnDrmPreparedHandlerDelegate:notifyClient: Unexpected null mHandler");
        }

        private Handler mHandler;
        private MediaPlayer mMediaPlayer;
        private OnDrmPreparedListener mOnDrmPreparedListener;
        final MediaPlayer this$0;

        OnDrmPreparedHandlerDelegate(MediaPlayer mediaplayer1, OnDrmPreparedListener ondrmpreparedlistener, Handler handler)
        {
            this$0 = MediaPlayer.this;
            super();
            mMediaPlayer = mediaplayer1;
            mOnDrmPreparedListener = ondrmpreparedlistener;
            if(handler != null)
                mHandler = handler;
            else
            if(MediaPlayer._2D_get3(MediaPlayer.this) != null)
                mHandler = MediaPlayer._2D_get3(MediaPlayer.this);
            else
                Log.e("MediaPlayer", "OnDrmPreparedHandlerDelegate: Unexpected null mEventHandler");
        }
    }

    public static interface OnDrmPreparedListener
    {

        public abstract void onDrmPrepared(MediaPlayer mediaplayer, int i);
    }

    public static interface OnErrorListener
    {

        public abstract boolean onError(MediaPlayer mediaplayer, int i, int j);
    }

    public static interface OnInfoListener
    {

        public abstract boolean onInfo(MediaPlayer mediaplayer, int i, int j);
    }

    public static interface OnPreparedListener
    {

        public abstract void onPrepared(MediaPlayer mediaplayer);
    }

    public static interface OnSeekCompleteListener
    {

        public abstract void onSeekComplete(MediaPlayer mediaplayer);
    }

    public static interface OnSubtitleDataListener
    {

        public abstract void onSubtitleData(MediaPlayer mediaplayer, SubtitleData subtitledata);
    }

    public static interface OnTimedMetaDataAvailableListener
    {

        public abstract void onTimedMetaDataAvailable(MediaPlayer mediaplayer, TimedMetaData timedmetadata);
    }

    public static interface OnTimedTextListener
    {

        public abstract void onTimedText(MediaPlayer mediaplayer, TimedText timedtext);
    }

    public static interface OnVideoSizeChangedListener
    {

        public abstract void onVideoSizeChanged(MediaPlayer mediaplayer, int i, int j);
    }

    public static final class ProvisioningNetworkErrorException extends MediaDrmException
    {

        public ProvisioningNetworkErrorException(String s)
        {
            super(s);
        }
    }

    public static final class ProvisioningServerErrorException extends MediaDrmException
    {

        public ProvisioningServerErrorException(String s)
        {
            super(s);
        }
    }

    private class ProvisioningThread extends Thread
    {

        public ProvisioningThread initialize(MediaDrm.ProvisionRequest provisionrequest, UUID uuid1, MediaPlayer mediaplayer)
        {
            drmLock = MediaPlayer._2D_get1(mediaplayer);
            onDrmPreparedHandlerDelegate = MediaPlayer._2D_get10(mediaplayer);
            mediaPlayer = mediaplayer;
            urlStr = (new StringBuilder()).append(provisionrequest.getDefaultUrl()).append("&signedRequest=").append(new String(provisionrequest.getData())).toString();
            uuid = uuid1;
            status = 3;
            Log.v("MediaPlayer", (new StringBuilder()).append("HandleProvisioninig: Thread is initialised url: ").append(urlStr).toString());
            return this;
        }

        public void run()
        {
            boolean flag;
            Object obj;
            Object obj1;
            byte abyte0[];
            boolean flag1;
            Object obj2;
            flag = false;
            obj = null;
            obj1 = null;
            abyte0 = null;
            flag1 = false;
            obj2 = obj;
            URL url = JVM INSTR new #99  <Class URL>;
            obj2 = obj;
            url.URL(urlStr);
            obj2 = obj;
            HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection();
            byte abyte1[];
            obj = abyte0;
            abyte1 = obj1;
            httpurlconnection.setRequestMethod("POST");
            obj = abyte0;
            abyte1 = obj1;
            httpurlconnection.setDoOutput(false);
            obj = abyte0;
            abyte1 = obj1;
            httpurlconnection.setDoInput(true);
            obj = abyte0;
            abyte1 = obj1;
            httpurlconnection.setConnectTimeout(60000);
            obj = abyte0;
            abyte1 = obj1;
            httpurlconnection.setReadTimeout(60000);
            obj = abyte0;
            abyte1 = obj1;
            httpurlconnection.connect();
            obj = abyte0;
            abyte1 = obj1;
            abyte0 = Streams.readFully(httpurlconnection.getInputStream());
            obj = abyte0;
            abyte1 = abyte0;
            obj2 = JVM INSTR new #53  <Class StringBuilder>;
            obj = abyte0;
            abyte1 = abyte0;
            ((StringBuilder) (obj2)).StringBuilder();
            obj = abyte0;
            abyte1 = abyte0;
            Log.v("MediaPlayer", ((StringBuilder) (obj2)).append("HandleProvisioninig: Thread run: response ").append(abyte0.length).append(" ").append(abyte0).toString());
            obj2 = abyte0;
            httpurlconnection.disconnect();
            obj2 = abyte0;
_L1:
            int i;
            i = ((flag1) ? 1 : 0);
            if(obj2 == null)
                break MISSING_BLOCK_LABEL_243;
            MediaPlayer._2D_get2(MediaPlayer.this).provideProvisionResponse(((byte []) (obj2)));
            Log.v("MediaPlayer", "HandleProvisioninig: Thread run: provideProvisionResponse SUCCEEDED!");
            i = 1;
_L2:
            boolean flag2;
            boolean flag3;
            flag2 = false;
            flag3 = false;
            if(onDrmPreparedHandlerDelegate == null)
                break MISSING_BLOCK_LABEL_521;
            obj = drmLock;
            obj;
            JVM INSTR monitorenter ;
            if(i == 0)
                break MISSING_BLOCK_LABEL_295;
            flag3 = MediaPlayer._2D_wrap0(mediaPlayer, uuid);
            StringBuilder stringbuilder;
            Object obj3;
            if(flag3)
                i = ((flag) ? 1 : 0);
            else
                i = 3;
            status = i;
            MediaPlayer._2D_set0(mediaPlayer, false);
            MediaPlayer._2D_set1(mediaPlayer, false);
            if(flag3)
                break MISSING_BLOCK_LABEL_325;
            MediaPlayer._2D_wrap1(MediaPlayer.this);
            obj;
            JVM INSTR monitorexit ;
            onDrmPreparedHandlerDelegate.notifyClient(status);
_L3:
            finished = true;
            return;
            obj3;
            abyte1 = ((byte []) (obj));
            status = 1;
            abyte1 = ((byte []) (obj));
            stringbuilder = JVM INSTR new #53  <Class StringBuilder>;
            abyte1 = ((byte []) (obj));
            stringbuilder.StringBuilder();
            abyte1 = ((byte []) (obj));
            Log.w("MediaPlayer", stringbuilder.append("HandleProvisioninig: Thread run: connect ").append(obj3).append(" url: ").append(url).toString());
            obj3 = obj;
            httpurlconnection.disconnect();
            obj3 = obj;
              goto _L1
            obj;
            obj3 = abyte1;
            httpurlconnection.disconnect();
            obj3 = abyte1;
            try
            {
                throw obj;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                status = 1;
                Log.w("MediaPlayer", (new StringBuilder()).append("HandleProvisioninig: Thread run: openConnection ").append(obj).toString());
            }
              goto _L1
            obj3;
            status = 2;
            Log.w("MediaPlayer", (new StringBuilder()).append("HandleProvisioninig: Thread run: provideProvisionResponse ").append(obj3).toString());
            i = ((flag1) ? 1 : 0);
              goto _L2
            obj3;
            throw obj3;
            boolean flag4 = flag2;
            if(i != 0)
            {
                flag4 = MediaPlayer._2D_wrap0(mediaPlayer, uuid);
                int j;
                if(flag4)
                    j = 0;
                else
                    j = 3;
                status = j;
            }
            MediaPlayer._2D_set0(mediaPlayer, false);
            MediaPlayer._2D_set1(mediaPlayer, false);
            if(!flag4)
                MediaPlayer._2D_wrap1(MediaPlayer.this);
              goto _L3
        }

        public int status()
        {
            return status;
        }

        public static final int TIMEOUT_MS = 60000;
        private Object drmLock;
        private boolean finished;
        private MediaPlayer mediaPlayer;
        private OnDrmPreparedHandlerDelegate onDrmPreparedHandlerDelegate;
        private int status;
        final MediaPlayer this$0;
        private String urlStr;
        private UUID uuid;

        private ProvisioningThread()
        {
            this$0 = MediaPlayer.this;
            super();
        }

        ProvisioningThread(ProvisioningThread provisioningthread)
        {
            this();
        }
    }

    static class TimeProvider
        implements OnSeekCompleteListener, MediaTimeProvider
    {

        static Handler _2D_get0(TimeProvider timeprovider)
        {
            return timeprovider.mEventHandler;
        }

        static void _2D_wrap0(TimeProvider timeprovider)
        {
            timeprovider.notifySeek();
        }

        static void _2D_wrap1(TimeProvider timeprovider)
        {
            timeprovider.notifyStop();
        }

        static void _2D_wrap2(TimeProvider timeprovider, boolean flag)
        {
            timeprovider.notifyTimedEvent(flag);
        }

        static void _2D_wrap3(TimeProvider timeprovider, Pair pair)
        {
            timeprovider.notifyTrackData(pair);
        }

        private long getEstimatedTime(long l, boolean flag)
        {
            if(!mPaused) goto _L2; else goto _L1
_L1:
            mLastReportedTime = mLastTimeUs + mTimeAdjustment;
_L4:
            return mLastReportedTime;
_L2:
            l = (l - mLastNanoTime) / 1000L;
            mLastReportedTime = mLastTimeUs + l;
            if(mTimeAdjustment > 0L)
            {
                l = mTimeAdjustment - l / 2L;
                if(l <= 0L)
                    mTimeAdjustment = 0L;
                else
                    mLastReportedTime = mLastReportedTime + l;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        private void notifySeek()
        {
            int i = 0;
            this;
            JVM INSTR monitorenter ;
            mSeeking = false;
            long l;
            MediaTimeProvider.OnMediaTimeListener aonmediatimelistener[];
            int j;
            l = getCurrentTimeUs(true, false);
            if(DEBUG)
            {
                StringBuilder stringbuilder = JVM INSTR new #158 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("MTP", stringbuilder.append("onSeekComplete at ").append(l).toString());
            }
            aonmediatimelistener = mListeners;
            j = aonmediatimelistener.length;
_L5:
            if(i >= j) goto _L2; else goto _L1
_L1:
            MediaTimeProvider.OnMediaTimeListener onmediatimelistener = aonmediatimelistener[i];
            if(onmediatimelistener != null) goto _L3; else goto _L2
_L2:
            this;
            JVM INSTR monitorexit ;
            return;
_L3:
            onmediatimelistener.onSeek(l);
            i++;
            continue; /* Loop/switch isn't completed */
            Object obj;
            obj;
            if(DEBUG)
                Log.d("MTP", "onSeekComplete but no player");
            mPausing = true;
            notifyTimedEvent(false);
              goto _L2
            obj;
            throw obj;
            if(true) goto _L5; else goto _L4
_L4:
        }

        private void notifyStop()
        {
            this;
            JVM INSTR monitorenter ;
            MediaTimeProvider.OnMediaTimeListener aonmediatimelistener[] = mListeners;
            int i = 0;
            int j = aonmediatimelistener.length;
_L2:
            MediaTimeProvider.OnMediaTimeListener onmediatimelistener;
            if(i < j)
            {
                onmediatimelistener = aonmediatimelistener[i];
                if(onmediatimelistener != null)
                    break MISSING_BLOCK_LABEL_30;
            }
            this;
            JVM INSTR monitorexit ;
            return;
            onmediatimelistener.onStop();
            i++;
            if(true) goto _L2; else goto _L1
_L1:
            Exception exception;
            exception;
            throw exception;
        }

        private void notifyTimedEvent(boolean flag)
        {
            this;
            JVM INSTR monitorenter ;
            long l = getCurrentTimeUs(flag, true);
_L1:
            long l1 = l;
            flag = mSeeking;
            if(!flag)
                break MISSING_BLOCK_LABEL_46;
            this;
            JVM INSTR monitorexit ;
            return;
            Object obj;
            obj;
            mRefresh = true;
            mPausing = true;
            l = getCurrentTimeUs(flag, true);
              goto _L1
            StringBuilder stringbuilder;
            if(!DEBUG)
                break MISSING_BLOCK_LABEL_184;
            stringbuilder = JVM INSTR new #158 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            stringbuilder.append("notifyTimedEvent(").append(mLastTimeUs).append(" -> ").append(l).append(") from {");
            boolean flag1 = true;
            long al[] = mTimes;
            int i = 0;
            int j = al.length;
_L3:
            long l2;
            if(i >= j)
                break MISSING_BLOCK_LABEL_165;
            l2 = al[i];
            if(l2 != -1L)
                break; /* Loop/switch isn't completed */
_L4:
            i++;
            if(true) goto _L3; else goto _L2
_L2:
            if(flag1)
                break MISSING_BLOCK_LABEL_151;
            stringbuilder.append(", ");
            stringbuilder.append(l2);
            flag1 = false;
              goto _L4
            stringbuilder.append("}");
            Log.d("MTP", stringbuilder.toString());
            Vector vector;
            vector = JVM INSTR new #201 <Class Vector>;
            vector.Vector();
            i = 0;
_L18:
            if(i < mTimes.length && mListeners[i] != null) goto _L6; else goto _L5
_L5:
            if(l1 <= l) goto _L8; else goto _L7
_L7:
            if(!(mPaused ^ true)) goto _L8; else goto _L9
_L9:
            if(DEBUG)
            {
                StringBuilder stringbuilder1 = JVM INSTR new #158 <Class StringBuilder>;
                stringbuilder1.StringBuilder();
                Log.d("MTP", stringbuilder1.append("scheduling for ").append(l1).append(" and ").append(l).toString());
            }
            scheduleNotification(0, l1 - l);
_L16:
            for(Iterator iterator = vector.iterator(); iterator.hasNext(); ((MediaTimeProvider.OnMediaTimeListener)iterator.next()).onTimedEvent(l));
            break MISSING_BLOCK_LABEL_470;
            iterator;
            throw iterator;
_L6:
            if(mTimes[i] > -1L) goto _L11; else goto _L10
_L10:
            l2 = l1;
_L12:
            i++;
            l1 = l2;
            continue; /* Loop/switch isn't completed */
_L11:
            if(mTimes[i] > 1000L + l)
                break MISSING_BLOCK_LABEL_423;
            vector.add(mListeners[i]);
            if(DEBUG)
                Log.d("MTP", "removed");
            mTimes[i] = -1L;
            l2 = l1;
              goto _L12
            if(l1 == l) goto _L14; else goto _L13
_L13:
            l2 = l1;
            if(mTimes[i] >= l1) goto _L12; else goto _L14
_L14:
            l2 = mTimes[i];
              goto _L12
_L8:
            mEventHandler.removeMessages(1);
            if(true) goto _L16; else goto _L15
_L15:
            this;
            JVM INSTR monitorexit ;
            return;
            if(true) goto _L18; else goto _L17
_L17:
        }

        private void notifyTrackData(Pair pair)
        {
            this;
            JVM INSTR monitorenter ;
            ((SubtitleTrack)pair.first).onData((byte[])pair.second, true, -1L);
            this;
            JVM INSTR monitorexit ;
            return;
            pair;
            throw pair;
        }

        private int registerListener(MediaTimeProvider.OnMediaTimeListener onmediatimelistener)
        {
            int i = 0;
            do
            {
                if(i >= mListeners.length || mListeners[i] == onmediatimelistener || mListeners[i] == null)
                {
                    if(i >= mListeners.length)
                    {
                        MediaTimeProvider.OnMediaTimeListener aonmediatimelistener[] = new MediaTimeProvider.OnMediaTimeListener[i + 1];
                        long al[] = new long[i + 1];
                        System.arraycopy(mListeners, 0, aonmediatimelistener, 0, mListeners.length);
                        System.arraycopy(mTimes, 0, al, 0, mTimes.length);
                        mListeners = aonmediatimelistener;
                        mTimes = al;
                    }
                    if(mListeners[i] == null)
                    {
                        mListeners[i] = onmediatimelistener;
                        mTimes[i] = -1L;
                    }
                    return i;
                }
                i++;
            } while(true);
        }

        private void scheduleNotification(int i, long l)
        {
            if(mSeeking && (i == 0 || i == 1))
                return;
            if(DEBUG)
                Log.v("MTP", (new StringBuilder()).append("scheduleNotification ").append(i).append(" in ").append(l).toString());
            mEventHandler.removeMessages(1);
            Message message = mEventHandler.obtainMessage(1, i, 0);
            mEventHandler.sendMessageDelayed(message, (int)(l / 1000L));
        }

        public void cancelNotifications(MediaTimeProvider.OnMediaTimeListener onmediatimelistener)
        {
            this;
            JVM INSTR monitorenter ;
            int i = 0;
_L7:
            if(i >= mListeners.length) goto _L2; else goto _L1
_L1:
            if(mListeners[i] != onmediatimelistener) goto _L4; else goto _L3
_L3:
            System.arraycopy(mListeners, i + 1, mListeners, i, mListeners.length - i - 1);
            System.arraycopy(mTimes, i + 1, mTimes, i, mTimes.length - i - 1);
            mListeners[mListeners.length - 1] = null;
            mTimes[mTimes.length - 1] = -1L;
_L2:
            scheduleNotification(0, 0L);
            this;
            JVM INSTR monitorexit ;
            return;
_L4:
            MediaTimeProvider.OnMediaTimeListener onmediatimelistener1 = mListeners[i];
            if(onmediatimelistener1 == null) goto _L2; else goto _L5
_L5:
            i++;
            if(true) goto _L7; else goto _L6
_L6:
            onmediatimelistener;
            throw onmediatimelistener;
        }

        public void close()
        {
            mEventHandler.removeMessages(1);
            if(mHandlerThread != null)
            {
                mHandlerThread.quitSafely();
                mHandlerThread = null;
            }
        }

        protected void finalize()
        {
            if(mHandlerThread != null)
                mHandlerThread.quitSafely();
        }

        public long getCurrentTimeUs(boolean flag, boolean flag1)
            throws IllegalStateException
        {
            boolean flag2 = true;
            this;
            JVM INSTR monitorenter ;
            if(!mPaused || !(flag ^ true))
                break MISSING_BLOCK_LABEL_28;
            long l = mLastReportedTime;
            this;
            JVM INSTR monitorexit ;
            return l;
            long l1 = System.nanoTime();
            if(flag)
                break MISSING_BLOCK_LABEL_55;
            l = mLastNanoTime;
            if(l1 < l + 0x12a05f200L)
                break MISSING_BLOCK_LABEL_213;
            mLastTimeUs = (long)mPlayer.getCurrentPosition() * 1000L;
            flag = flag2;
            StringBuilder stringbuilder;
            if(mPlayer.isPlaying())
                flag = mBuffering;
            mPaused = flag;
            if(!DEBUG)
                break MISSING_BLOCK_LABEL_151;
            stringbuilder = JVM INSTR new #158 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            String s;
            if(mPaused)
                s = "paused";
            else
                s = "playing";
            Log.v("MTP", stringbuilder.append(s).append(" at ").append(mLastTimeUs).toString());
            mLastNanoTime = l1;
            if(!flag1)
                break MISSING_BLOCK_LABEL_324;
            if(mLastTimeUs >= mLastReportedTime)
                break MISSING_BLOCK_LABEL_324;
            mTimeAdjustment = mLastReportedTime - mLastTimeUs;
            if(mTimeAdjustment > 0xf4240L)
            {
                mStopped = false;
                mSeeking = true;
                scheduleNotification(3, 0L);
            }
_L1:
            l = getEstimatedTime(l1, flag1);
            this;
            JVM INSTR monitorexit ;
            return l;
            Object obj;
            obj;
            if(!mPausing)
                break MISSING_BLOCK_LABEL_314;
            mPausing = false;
            getEstimatedTime(l1, flag1);
            mPaused = true;
            if(DEBUG)
            {
                obj = JVM INSTR new #158 <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                Log.d("MTP", ((StringBuilder) (obj)).append("illegal state, but pausing: estimating at ").append(mLastReportedTime).toString());
            }
            l = mLastReportedTime;
            this;
            JVM INSTR monitorexit ;
            return l;
            throw obj;
            obj;
            this;
            JVM INSTR monitorexit ;
            throw obj;
            mTimeAdjustment = 0L;
              goto _L1
        }

        public void notifyAt(long l, MediaTimeProvider.OnMediaTimeListener onmediatimelistener)
        {
            this;
            JVM INSTR monitorenter ;
            if(DEBUG)
            {
                StringBuilder stringbuilder = JVM INSTR new #158 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("MTP", stringbuilder.append("notifyAt ").append(l).toString());
            }
            mTimes[registerListener(onmediatimelistener)] = l;
            scheduleNotification(0, 0L);
            this;
            JVM INSTR monitorexit ;
            return;
            onmediatimelistener;
            throw onmediatimelistener;
        }

        public void onBuffering(boolean flag)
        {
            this;
            JVM INSTR monitorenter ;
            if(DEBUG)
            {
                StringBuilder stringbuilder = JVM INSTR new #158 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("MTP", stringbuilder.append("onBuffering: ").append(flag).toString());
            }
            mBuffering = flag;
            scheduleNotification(1, 0L);
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void onNewPlayer()
        {
            if(!mRefresh) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorenter ;
            mStopped = false;
            mSeeking = true;
            mBuffering = false;
            scheduleNotification(3, 0L);
            this;
            JVM INSTR monitorexit ;
_L2:
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void onPaused(boolean flag)
        {
            this;
            JVM INSTR monitorenter ;
            if(DEBUG)
            {
                StringBuilder stringbuilder = JVM INSTR new #158 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("MTP", stringbuilder.append("onPaused: ").append(flag).toString());
            }
            if(!mStopped)
                break MISSING_BLOCK_LABEL_63;
            mStopped = false;
            mSeeking = true;
            scheduleNotification(3, 0L);
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            mPausing = flag;
            mSeeking = false;
            scheduleNotification(1, 0L);
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        public void onSeekComplete(MediaPlayer mediaplayer)
        {
            this;
            JVM INSTR monitorenter ;
            mStopped = false;
            mSeeking = true;
            scheduleNotification(3, 0L);
            this;
            JVM INSTR monitorexit ;
            return;
            mediaplayer;
            throw mediaplayer;
        }

        public void onStopped()
        {
            this;
            JVM INSTR monitorenter ;
            if(DEBUG)
                Log.d("MTP", "onStopped");
            mPaused = true;
            mStopped = true;
            mSeeking = false;
            mBuffering = false;
            scheduleNotification(2, 0L);
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void scheduleUpdate(MediaTimeProvider.OnMediaTimeListener onmediatimelistener)
        {
            this;
            JVM INSTR monitorenter ;
            if(DEBUG)
                Log.d("MTP", "scheduleUpdate");
            int i = registerListener(onmediatimelistener);
            if(!mStopped)
            {
                mTimes[i] = 0L;
                scheduleNotification(0, 0L);
            }
            this;
            JVM INSTR monitorexit ;
            return;
            onmediatimelistener;
            throw onmediatimelistener;
        }

        private static final long MAX_EARLY_CALLBACK_US = 1000L;
        private static final long MAX_NS_WITHOUT_POSITION_CHECK = 0x12a05f200L;
        private static final int NOTIFY = 1;
        private static final int NOTIFY_SEEK = 3;
        private static final int NOTIFY_STOP = 2;
        private static final int NOTIFY_TIME = 0;
        private static final int NOTIFY_TRACK_DATA = 4;
        private static final int REFRESH_AND_NOTIFY_TIME = 1;
        private static final String TAG = "MTP";
        private static final long TIME_ADJUSTMENT_RATE = 2L;
        public boolean DEBUG;
        private boolean mBuffering;
        private Handler mEventHandler;
        private HandlerThread mHandlerThread;
        private long mLastNanoTime;
        private long mLastReportedTime;
        private long mLastTimeUs;
        private MediaTimeProvider.OnMediaTimeListener mListeners[];
        private boolean mPaused;
        private boolean mPausing;
        private MediaPlayer mPlayer;
        private boolean mRefresh;
        private boolean mSeeking;
        private boolean mStopped;
        private long mTimeAdjustment;
        private long mTimes[];

        public TimeProvider(MediaPlayer mediaplayer)
        {
            mLastTimeUs = 0L;
            mPaused = true;
            mStopped = true;
            mRefresh = false;
            mPausing = false;
            mSeeking = false;
            DEBUG = false;
            mPlayer = mediaplayer;
            Looper looper;
            try
            {
                getCurrentTimeUs(true, false);
            }
            // Misplaced declaration of an exception variable
            catch(MediaPlayer mediaplayer)
            {
                mRefresh = true;
            }
            looper = Looper.myLooper();
            mediaplayer = looper;
            if(looper == null)
            {
                Looper looper1 = Looper.getMainLooper();
                mediaplayer = looper1;
                if(looper1 == null)
                {
                    mHandlerThread = new HandlerThread("MediaPlayerMTPEventThread", -2);
                    mHandlerThread.start();
                    mediaplayer = mHandlerThread.getLooper();
                }
            }
            mEventHandler = new EventHandler(mediaplayer);
            mListeners = new MediaTimeProvider.OnMediaTimeListener[0];
            mTimes = new long[0];
            mLastTimeUs = 0L;
            mTimeAdjustment = 0L;
        }
    }

    private class TimeProvider.EventHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            if(message.what != 1) goto _L2; else goto _L1
_L1:
            message.arg1;
            JVM INSTR tableswitch 0 4: default 48
        //                       0 49
        //                       1 60
        //                       2 71
        //                       3 81
        //                       4 91;
               goto _L2 _L3 _L4 _L5 _L6 _L7
_L2:
            return;
_L3:
            TimeProvider._2D_wrap2(TimeProvider.this, false);
            continue; /* Loop/switch isn't completed */
_L4:
            TimeProvider._2D_wrap2(TimeProvider.this, true);
            continue; /* Loop/switch isn't completed */
_L5:
            TimeProvider._2D_wrap1(TimeProvider.this);
            continue; /* Loop/switch isn't completed */
_L6:
            TimeProvider._2D_wrap0(TimeProvider.this);
            continue; /* Loop/switch isn't completed */
_L7:
            TimeProvider._2D_wrap3(TimeProvider.this, (Pair)message.obj);
            if(true) goto _L2; else goto _L8
_L8:
        }

        final TimeProvider this$1;

        public TimeProvider.EventHandler(Looper looper)
        {
            this$1 = TimeProvider.this;
            super(looper);
        }
    }

    public static class TrackInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public MediaFormat getFormat()
        {
            if(mTrackType == 3 || mTrackType == 4)
                return mFormat;
            else
                return null;
        }

        public String getLanguage()
        {
            String s = mFormat.getString("language");
            String s1 = s;
            if(s == null)
                s1 = "und";
            return s1;
        }

        public int getTrackType()
        {
            return mTrackType;
        }

        public String toString()
        {
            StringBuilder stringbuilder;
            stringbuilder = new StringBuilder(128);
            stringbuilder.append(getClass().getName());
            stringbuilder.append('{');
            mTrackType;
            JVM INSTR tableswitch 1 4: default 64
        //                       1 100
        //                       2 110
        //                       3 120
        //                       4 130;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            stringbuilder.append("UNKNOWN");
_L7:
            stringbuilder.append(", ").append(mFormat.toString());
            stringbuilder.append("}");
            return stringbuilder.toString();
_L2:
            stringbuilder.append("VIDEO");
            continue; /* Loop/switch isn't completed */
_L3:
            stringbuilder.append("AUDIO");
            continue; /* Loop/switch isn't completed */
_L4:
            stringbuilder.append("TIMEDTEXT");
            continue; /* Loop/switch isn't completed */
_L5:
            stringbuilder.append("SUBTITLE");
            if(true) goto _L7; else goto _L6
_L6:
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mTrackType);
            parcel.writeString(getLanguage());
            if(mTrackType == 4)
            {
                parcel.writeString(mFormat.getString("mime"));
                parcel.writeInt(mFormat.getInteger("is-autoselect"));
                parcel.writeInt(mFormat.getInteger("is-default"));
                parcel.writeInt(mFormat.getInteger("is-forced-subtitle"));
            }
        }

        static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public TrackInfo createFromParcel(Parcel parcel)
            {
                return new TrackInfo(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public TrackInfo[] newArray(int i)
            {
                return new TrackInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int MEDIA_TRACK_TYPE_AUDIO = 2;
        public static final int MEDIA_TRACK_TYPE_METADATA = 5;
        public static final int MEDIA_TRACK_TYPE_SUBTITLE = 4;
        public static final int MEDIA_TRACK_TYPE_TIMEDTEXT = 3;
        public static final int MEDIA_TRACK_TYPE_UNKNOWN = 0;
        public static final int MEDIA_TRACK_TYPE_VIDEO = 1;
        final MediaFormat mFormat;
        final int mTrackType;


        TrackInfo(int i, MediaFormat mediaformat)
        {
            mTrackType = i;
            mFormat = mediaformat;
        }

        TrackInfo(Parcel parcel)
        {
            mTrackType = parcel.readInt();
            mFormat = MediaFormat.createSubtitleFormat(parcel.readString(), parcel.readString());
            if(mTrackType == 4)
            {
                mFormat.setInteger("is-autoselect", parcel.readInt());
                mFormat.setInteger("is-default", parcel.readInt());
                mFormat.setInteger("is-forced-subtitle", parcel.readInt());
            }
        }
    }


    static DrmInfo _2D_get0(MediaPlayer mediaplayer)
    {
        return mediaplayer.mDrmInfo;
    }

    static Object _2D_get1(MediaPlayer mediaplayer)
    {
        return mediaplayer.mDrmLock;
    }

    static OnDrmPreparedHandlerDelegate _2D_get10(MediaPlayer mediaplayer)
    {
        return mediaplayer.mOnDrmPreparedHandlerDelegate;
    }

    static OnErrorListener _2D_get11(MediaPlayer mediaplayer)
    {
        return mediaplayer.mOnErrorListener;
    }

    static OnInfoListener _2D_get12(MediaPlayer mediaplayer)
    {
        return mediaplayer.mOnInfoListener;
    }

    static OnPreparedListener _2D_get13(MediaPlayer mediaplayer)
    {
        return mediaplayer.mOnPreparedListener;
    }

    static OnSeekCompleteListener _2D_get14(MediaPlayer mediaplayer)
    {
        return mediaplayer.mOnSeekCompleteListener;
    }

    static OnSubtitleDataListener _2D_get15(MediaPlayer mediaplayer)
    {
        return mediaplayer.mOnSubtitleDataListener;
    }

    static OnTimedMetaDataAvailableListener _2D_get16(MediaPlayer mediaplayer)
    {
        return mediaplayer.mOnTimedMetaDataAvailableListener;
    }

    static OnTimedTextListener _2D_get17(MediaPlayer mediaplayer)
    {
        return mediaplayer.mOnTimedTextListener;
    }

    static OnVideoSizeChangedListener _2D_get18(MediaPlayer mediaplayer)
    {
        return mediaplayer.mOnVideoSizeChangedListener;
    }

    static Vector _2D_get19(MediaPlayer mediaplayer)
    {
        return mediaplayer.mOpenSubtitleSources;
    }

    static MediaDrm _2D_get2(MediaPlayer mediaplayer)
    {
        return mediaplayer.mDrmObj;
    }

    static SubtitleController _2D_get20(MediaPlayer mediaplayer)
    {
        return mediaplayer.mSubtitleController;
    }

    static TimeProvider _2D_get21(MediaPlayer mediaplayer)
    {
        return mediaplayer.mTimeProvider;
    }

    static EventHandler _2D_get3(MediaPlayer mediaplayer)
    {
        return mediaplayer.mEventHandler;
    }

    static Vector _2D_get4(MediaPlayer mediaplayer)
    {
        return mediaplayer.mIndexTrackPairs;
    }

    static long _2D_get5(MediaPlayer mediaplayer)
    {
        return mediaplayer.mNativeContext;
    }

    static OnBufferingUpdateListener _2D_get6(MediaPlayer mediaplayer)
    {
        return mediaplayer.mOnBufferingUpdateListener;
    }

    static OnCompletionListener _2D_get7(MediaPlayer mediaplayer)
    {
        return mediaplayer.mOnCompletionInternalListener;
    }

    static OnCompletionListener _2D_get8(MediaPlayer mediaplayer)
    {
        return mediaplayer.mOnCompletionListener;
    }

    static OnDrmInfoHandlerDelegate _2D_get9(MediaPlayer mediaplayer)
    {
        return mediaplayer.mOnDrmInfoHandlerDelegate;
    }

    static boolean _2D_set0(MediaPlayer mediaplayer, boolean flag)
    {
        mediaplayer.mDrmProvisioningInProgress = flag;
        return flag;
    }

    static boolean _2D_set1(MediaPlayer mediaplayer, boolean flag)
    {
        mediaplayer.mPrepareDrmInProgress = flag;
        return flag;
    }

    static SubtitleController _2D_set2(MediaPlayer mediaplayer, SubtitleController subtitlecontroller)
    {
        mediaplayer.mSubtitleController = subtitlecontroller;
        return subtitlecontroller;
    }

    static boolean _2D_wrap0(MediaPlayer mediaplayer, UUID uuid)
    {
        return mediaplayer.resumePrepareDrm(uuid);
    }

    static void _2D_wrap1(MediaPlayer mediaplayer)
    {
        mediaplayer.cleanDrmObj();
    }

    static void _2D_wrap2(MediaPlayer mediaplayer)
    {
        mediaplayer.scanInternalSubtitleTracks();
    }

    static void _2D_wrap3(MediaPlayer mediaplayer)
    {
        mediaplayer.startImpl();
    }

    static void _2D_wrap4(MediaPlayer mediaplayer, boolean flag)
    {
        mediaplayer.stayAwake(flag);
    }

    public MediaPlayer()
    {
        super((new AudioAttributes.Builder()).build(), 2);
        mWakeLock = null;
        mStreamType = 0x80000000;
        mUsage = -1;
        mIndexTrackPairs = new Vector();
        mInbandTrackIndices = new BitSet();
        mSelectedSubtitleTrackIndex = -1;
        mSubtitleDataListener = new OnSubtitleDataListener() {

            public void onSubtitleData(MediaPlayer mediaplayer, SubtitleData subtitledata)
            {
                int i = subtitledata.getTrackIndex();
                mediaplayer = MediaPlayer._2D_get4(MediaPlayer.this);
                mediaplayer;
                JVM INSTR monitorenter ;
                Iterator iterator = MediaPlayer._2D_get4(MediaPlayer.this).iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    Pair pair = (Pair)iterator.next();
                    if(pair.first != null && ((Integer)pair.first).intValue() == i && pair.second != null)
                        ((SubtitleTrack)pair.second).onData(subtitledata);
                } while(true);
                break MISSING_BLOCK_LABEL_102;
                subtitledata;
                throw subtitledata;
                mediaplayer;
                JVM INSTR monitorexit ;
            }

            final MediaPlayer this$0;

            
            {
                this$0 = MediaPlayer.this;
                super();
            }
        }
;
        Looper looper = Looper.myLooper();
        if(looper != null)
        {
            mEventHandler = new EventHandler(this, looper);
        } else
        {
            Looper looper1 = Looper.getMainLooper();
            if(looper1 != null)
                mEventHandler = new EventHandler(this, looper1);
            else
                mEventHandler = null;
        }
        mTimeProvider = new TimeProvider(this);
        mOpenSubtitleSources = new Vector();
        native_setup(new WeakReference(this));
        baseRegisterPlayer();
    }

    private int HandleProvisioninig(UUID uuid)
    {
        if(mDrmProvisioningInProgress)
        {
            Log.e("MediaPlayer", "HandleProvisioninig: Unexpected mDrmProvisioningInProgress");
            return 3;
        }
        MediaDrm.ProvisionRequest provisionrequest = mDrmObj.getProvisionRequest();
        if(provisionrequest == null)
        {
            Log.e("MediaPlayer", "HandleProvisioninig: getProvisionRequest returned null.");
            return 3;
        }
        Log.v("MediaPlayer", (new StringBuilder()).append("HandleProvisioninig provReq  data: ").append(provisionrequest.getData()).append(" url: ").append(provisionrequest.getDefaultUrl()).toString());
        mDrmProvisioningInProgress = true;
        mDrmProvisioningThread = (new ProvisioningThread(null)).initialize(provisionrequest, uuid, this);
        mDrmProvisioningThread.start();
        int i;
        if(mOnDrmPreparedHandlerDelegate != null)
        {
            i = 0;
        } else
        {
            try
            {
                mDrmProvisioningThread.join();
            }
            // Misplaced declaration of an exception variable
            catch(UUID uuid)
            {
                Log.w("MediaPlayer", (new StringBuilder()).append("HandleProvisioninig: Thread.join Exception ").append(uuid).toString());
            }
            i = mDrmProvisioningThread.status();
            mDrmProvisioningThread = null;
        }
        return i;
    }

    private native int _getAudioStreamType()
        throws IllegalStateException;

    private native void _pause()
        throws IllegalStateException;

    private native void _prepare()
        throws IOException, IllegalStateException;

    private native void _prepareDrm(byte abyte0[], byte abyte1[]);

    private native void _release();

    private native void _releaseDrm();

    private native void _reset();

    private final native void _seekTo(long l, int i);

    private native void _setAudioStreamType(int i);

    private native void _setAuxEffectSendLevel(float f);

    private native void _setDataSource(MediaDataSource mediadatasource)
        throws IllegalArgumentException, IllegalStateException;

    private native void _setDataSource(FileDescriptor filedescriptor, long l, long l1)
        throws IOException, IllegalArgumentException, IllegalStateException;

    private native void _setVideoSurface(Surface surface);

    private native void _setVolume(float f, float f1);

    private native void _start()
        throws IllegalStateException;

    private native void _stop()
        throws IllegalStateException;

    private boolean attemptDataSource(ContentResolver contentresolver, Uri uri)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        contentresolver = contentresolver.openAssetFileDescriptor(uri, "r");
        obj3 = contentresolver;
        obj2 = contentresolver;
        setDataSource(contentresolver);
        obj3 = obj1;
        if(contentresolver == null)
            break MISSING_BLOCK_LABEL_47;
        contentresolver.close();
        obj3 = obj1;
_L2:
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_99;
        try
        {
            throw obj3;
        }
        // Misplaced declaration of an exception variable
        catch(ContentResolver contentresolver)
        {
            Log.w("MediaPlayer", (new StringBuilder()).append("Couldn't open ").append(uri).append(": ").append(contentresolver).toString());
        }
        return false;
        obj3;
        if(true) goto _L2; else goto _L1
_L1:
        return true;
        contentresolver;
        throw contentresolver;
        obj2;
_L6:
        obj = contentresolver;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_120;
        ((AssetFileDescriptor) (obj3)).close();
        obj = contentresolver;
_L4:
        if(obj == null)
            break; /* Loop/switch isn't completed */
        throw obj;
        obj3;
        if(contentresolver == null)
        {
            obj = obj3;
            continue; /* Loop/switch isn't completed */
        }
        obj = contentresolver;
        if(contentresolver == obj3)
            continue; /* Loop/switch isn't completed */
        contentresolver.addSuppressed(((Throwable) (obj3)));
        obj = contentresolver;
        if(true) goto _L4; else goto _L3
_L3:
        throw obj2;
        contentresolver;
        obj3 = obj2;
        obj2 = contentresolver;
        contentresolver = ((ContentResolver) (obj));
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static boolean availableMimeTypeForExternalSource(String s)
    {
        return "application/x-subrip".equals(s);
    }

    private void cleanDrmObj()
    {
        Log.v("MediaPlayer", (new StringBuilder()).append("cleanDrmObj: mDrmObj=").append(mDrmObj).append(" mDrmSessionId=").append(mDrmSessionId).toString());
        if(mDrmSessionId != null)
        {
            mDrmObj.closeSession(mDrmSessionId);
            mDrmSessionId = null;
        }
        if(mDrmObj != null)
        {
            mDrmObj.release();
            mDrmObj = null;
        }
    }

    public static MediaPlayer create(Context context, int i)
    {
        int j = AudioSystem.newAudioSessionId();
        if(j <= 0)
            j = 0;
        return create(context, i, null, j);
    }

    public static MediaPlayer create(Context context, int i, AudioAttributes audioattributes, int j)
    {
        AssetFileDescriptor assetfiledescriptor = context.getResources().openRawResourceFd(i);
        if(assetfiledescriptor == null)
            return null;
        MediaPlayer mediaplayer;
        mediaplayer = JVM INSTR new #2   <Class MediaPlayer>;
        mediaplayer.MediaPlayer();
        if(audioattributes == null)
            break MISSING_BLOCK_LABEL_78;
        context = audioattributes;
_L1:
        mediaplayer.setAudioAttributes(context);
        mediaplayer.setAudioSessionId(j);
        mediaplayer.setDataSource(assetfiledescriptor.getFileDescriptor(), assetfiledescriptor.getStartOffset(), assetfiledescriptor.getLength());
        assetfiledescriptor.close();
        mediaplayer.prepare();
        return mediaplayer;
        context = JVM INSTR new #434 <Class AudioAttributes$Builder>;
        context.AudioAttributes.Builder();
        context = context.build();
          goto _L1
        context;
        Log.d("MediaPlayer", "create failed:", context);
_L3:
        return null;
        context;
        Log.d("MediaPlayer", "create failed:", context);
        continue; /* Loop/switch isn't completed */
        context;
        Log.d("MediaPlayer", "create failed:", context);
        if(true) goto _L3; else goto _L2
_L2:
    }

    public static MediaPlayer create(Context context, Uri uri)
    {
        return create(context, uri, null);
    }

    public static MediaPlayer create(Context context, Uri uri, SurfaceHolder surfaceholder)
    {
        int i = AudioSystem.newAudioSessionId();
        if(i <= 0)
            i = 0;
        return create(context, uri, surfaceholder, null, i);
    }

    public static MediaPlayer create(Context context, Uri uri, SurfaceHolder surfaceholder, AudioAttributes audioattributes, int i)
    {
        MediaPlayer mediaplayer;
        mediaplayer = JVM INSTR new #2   <Class MediaPlayer>;
        mediaplayer.MediaPlayer();
        if(audioattributes == null)
            break MISSING_BLOCK_LABEL_52;
_L1:
        mediaplayer.setAudioAttributes(audioattributes);
        mediaplayer.setAudioSessionId(i);
        mediaplayer.setDataSource(context, uri);
        if(surfaceholder == null)
            break MISSING_BLOCK_LABEL_44;
        mediaplayer.setDisplay(surfaceholder);
        mediaplayer.prepare();
        return mediaplayer;
        audioattributes = JVM INSTR new #434 <Class AudioAttributes$Builder>;
        audioattributes.AudioAttributes.Builder();
        audioattributes = audioattributes.build();
          goto _L1
        context;
        Log.d("MediaPlayer", "create failed:", context);
_L3:
        return null;
        context;
        Log.d("MediaPlayer", "create failed:", context);
        continue; /* Loop/switch isn't completed */
        context;
        Log.d("MediaPlayer", "create failed:", context);
        if(true) goto _L3; else goto _L2
_L2:
    }

    private int getAudioStreamType()
    {
        if(mStreamType == 0x80000000)
            mStreamType = _getAudioStreamType();
        return mStreamType;
    }

    private static final byte[] getByteArrayFromUUID(UUID uuid)
    {
        long l = uuid.getMostSignificantBits();
        long l1 = uuid.getLeastSignificantBits();
        uuid = new byte[16];
        for(int i = 0; i < 8; i++)
        {
            uuid[i] = (byte)(int)(l >>> (7 - i) * 8);
            uuid[i + 8] = (byte)(int)(l1 >>> (7 - i) * 8);
        }

        return uuid;
    }

    private TrackInfo[] getInbandTrackInfo()
        throws IllegalStateException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        TrackInfo atrackinfo[];
        parcel.writeInterfaceToken("android.media.IMediaPlayer");
        parcel.writeInt(1);
        invoke(parcel, parcel1);
        atrackinfo = (TrackInfo[])parcel1.createTypedArray(TrackInfo.CREATOR);
        parcel.recycle();
        parcel1.recycle();
        return atrackinfo;
        Exception exception;
        exception;
        parcel.recycle();
        parcel1.recycle();
        throw exception;
    }

    private boolean isVideoScalingModeSupported(int i)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(i != 1)
            if(i == 2)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private native void nativeSetDataSource(IBinder ibinder, String s, String as[], String as1[])
        throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;

    private native int native_applyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation);

    private final native void native_finalize();

    private final native boolean native_getMetadata(boolean flag, boolean flag1, Parcel parcel);

    private native PersistableBundle native_getMetrics();

    private native VolumeShaper.State native_getVolumeShaperState(int i);

    private static final native void native_init();

    private final native int native_invoke(Parcel parcel, Parcel parcel1);

    public static native int native_pullBatteryData(Parcel parcel);

    private final native int native_setMetadataFilter(Parcel parcel);

    private final native int native_setRetransmitEndpoint(String s, int i);

    private final native void native_setup(Object obj);

    private void populateInbandTracks()
    {
        TrackInfo atrackinfo[] = getInbandTrackInfo();
        Vector vector = mIndexTrackPairs;
        vector;
        JVM INSTR monitorenter ;
        int i = 0;
_L2:
        if(i >= atrackinfo.length)
            break MISSING_BLOCK_LABEL_114;
        if(!mInbandTrackIndices.get(i))
            break; /* Loop/switch isn't completed */
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        mInbandTrackIndices.set(i);
        if(atrackinfo[i].getTrackType() != 4)
            break MISSING_BLOCK_LABEL_95;
        SubtitleTrack subtitletrack = mSubtitleController.addTrack(atrackinfo[i].getFormat());
        mIndexTrackPairs.add(Pair.create(Integer.valueOf(i), subtitletrack));
          goto _L3
        Exception exception;
        exception;
        throw exception;
        mIndexTrackPairs.add(Pair.create(Integer.valueOf(i), null));
          goto _L3
        vector;
        JVM INSTR monitorexit ;
    }

    private static void postEventFromNative(Object obj, int i, int j, int k, Object obj1)
    {
        MediaPlayer mediaplayer;
        mediaplayer = (MediaPlayer)((WeakReference)obj).get();
        if(mediaplayer == null)
            return;
        i;
        JVM INSTR lookupswitch 3: default 52
    //                   1: 204
    //                   200: 85
    //                   210: 115;
           goto _L1 _L2 _L3 _L4
_L1:
        if(mediaplayer.mEventHandler != null)
        {
            obj = mediaplayer.mEventHandler.obtainMessage(i, j, k, obj1);
            mediaplayer.mEventHandler.sendMessage(((Message) (obj)));
        }
        return;
_L3:
        if(j == 2)
        {
            (new Thread(new Runnable(mediaplayer) {

                public void run()
                {
                    mp.start();
                }

                final MediaPlayer val$mp;

            
            {
                mp = mediaplayer;
                super();
            }
            }
)).start();
            Thread.yield();
        }
        continue; /* Loop/switch isn't completed */
_L4:
        Log.v("MediaPlayer", "postEventFromNative MEDIA_DRM_INFO");
        if(!(obj1 instanceof Parcel)) goto _L6; else goto _L5
_L5:
        DrmInfo drminfo = new DrmInfo((Parcel)obj1, null);
        obj = mediaplayer.mDrmLock;
        obj;
        JVM INSTR monitorenter ;
        mediaplayer.mDrmInfo = drminfo;
_L7:
        obj;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        obj1;
        throw obj1;
_L6:
        Log.w("MediaPlayer", (new StringBuilder()).append("MEDIA_DRM_INFO msg.obj of unexpected type ").append(obj1).toString());
        continue; /* Loop/switch isn't completed */
_L2:
        obj = mediaplayer.mDrmLock;
        obj;
        JVM INSTR monitorenter ;
        mediaplayer.mDrmInfoResolved = true;
          goto _L7
        obj1;
        throw obj1;
        if(true) goto _L1; else goto _L8
_L8:
    }

    private void prepareDrm_createDrmStep(UUID uuid)
        throws UnsupportedSchemeException
    {
        Log.v("MediaPlayer", (new StringBuilder()).append("prepareDrm_createDrmStep: UUID: ").append(uuid).toString());
        try
        {
            MediaDrm mediadrm = JVM INSTR new #505 <Class MediaDrm>;
            mediadrm.MediaDrm(uuid);
            mDrmObj = mediadrm;
            uuid = JVM INSTR new #513 <Class StringBuilder>;
            uuid.StringBuilder();
            Log.v("MediaPlayer", uuid.append("prepareDrm_createDrmStep: Created mDrmObj=").append(mDrmObj).toString());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            Log.e("MediaPlayer", (new StringBuilder()).append("prepareDrm_createDrmStep: MediaDrm failed with ").append(uuid).toString());
        }
        throw uuid;
    }

    private void prepareDrm_openSessionStep(UUID uuid)
        throws NotProvisionedException, ResourceBusyException
    {
        Log.v("MediaPlayer", (new StringBuilder()).append("prepareDrm_openSessionStep: uuid: ").append(uuid).toString());
        try
        {
            mDrmSessionId = mDrmObj.openSession();
            StringBuilder stringbuilder = JVM INSTR new #513 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.v("MediaPlayer", stringbuilder.append("prepareDrm_openSessionStep: mDrmSessionId=").append(mDrmSessionId).toString());
            _prepareDrm(getByteArrayFromUUID(uuid), mDrmSessionId);
            Log.v("MediaPlayer", "prepareDrm_openSessionStep: _prepareDrm/Crypto succeeded");
            return;
        }
        // Misplaced declaration of an exception variable
        catch(UUID uuid)
        {
            Log.e("MediaPlayer", (new StringBuilder()).append("prepareDrm_openSessionStep: open/crypto failed with ").append(uuid).toString());
        }
        throw uuid;
    }

    private void resetDrmState()
    {
        Object obj = mDrmLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        obj1 = JVM INSTR new #513 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.v("MediaPlayer", ((StringBuilder) (obj1)).append("resetDrmState:  mDrmInfo=").append(mDrmInfo).append(" mDrmProvisioningThread=").append(mDrmProvisioningThread).append(" mPrepareDrmInProgress=").append(mPrepareDrmInProgress).append(" mActiveDrmScheme=").append(mActiveDrmScheme).toString());
        mDrmInfoResolved = false;
        mDrmInfo = null;
        obj1 = mDrmProvisioningThread;
        if(obj1 == null) goto _L2; else goto _L1
_L1:
        mDrmProvisioningThread.join();
_L3:
        mDrmProvisioningThread = null;
_L2:
        mPrepareDrmInProgress = false;
        mActiveDrmScheme = false;
        cleanDrmObj();
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj2;
        obj2;
        StringBuilder stringbuilder = JVM INSTR new #513 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.w("MediaPlayer", stringbuilder.append("resetDrmState: ProvThread.join Exception ").append(obj2).toString());
          goto _L3
        obj2;
        throw obj2;
    }

    private boolean resumePrepareDrm(UUID uuid)
    {
        boolean flag;
        Log.v("MediaPlayer", (new StringBuilder()).append("resumePrepareDrm: uuid: ").append(uuid).toString());
        flag = false;
        prepareDrm_openSessionStep(uuid);
        mDrmUUID = uuid;
        mActiveDrmScheme = true;
        flag = true;
_L2:
        return flag;
        uuid;
        Log.w("MediaPlayer", (new StringBuilder()).append("HandleProvisioninig: Thread run _prepareDrm resume failed with ").append(uuid).toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void scanInternalSubtitleTracks()
    {
        setSubtitleAnchor();
        populateInbandTracks();
        if(mSubtitleController != null)
            mSubtitleController.selectDefaultTrack();
    }

    private void selectOrDeselectInbandTrack(int i, boolean flag)
        throws IllegalStateException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("android.media.IMediaPlayer");
        byte byte0;
        if(flag)
            byte0 = 4;
        else
            byte0 = 5;
        parcel.writeInt(byte0);
        parcel.writeInt(i);
        invoke(parcel, parcel1);
        parcel.recycle();
        parcel1.recycle();
        return;
        Exception exception;
        exception;
        parcel.recycle();
        parcel1.recycle();
        throw exception;
    }

    private void selectOrDeselectTrack(int i, boolean flag)
        throws IllegalStateException
    {
        SubtitleTrack subtitletrack;
        populateInbandTracks();
        Pair pair;
        try
        {
            pair = (Pair)mIndexTrackPairs.get(i);
        }
        catch(ArrayIndexOutOfBoundsException arrayindexoutofboundsexception)
        {
            return;
        }
        subtitletrack = (SubtitleTrack)pair.second;
        if(subtitletrack == null)
        {
            selectOrDeselectInbandTrack(((Integer)pair.first).intValue(), flag);
            return;
        }
        if(mSubtitleController == null)
            return;
        if(!flag)
        {
            if(mSubtitleController.getSelectedTrack() == subtitletrack)
                mSubtitleController.selectTrack(null);
            else
                Log.w("MediaPlayer", "trying to deselect track that was not selected");
            return;
        }
        if(subtitletrack.getTrackType() != 3) goto _L2; else goto _L1
_L1:
        i = getSelectedTrack(3);
        Vector vector = mIndexTrackPairs;
        vector;
        JVM INSTR monitorenter ;
        if(i < 0)
            break MISSING_BLOCK_LABEL_176;
        if(i < mIndexTrackPairs.size())
        {
            Pair pair1 = (Pair)mIndexTrackPairs.get(i);
            if(pair1.first != null && pair1.second == null)
                selectOrDeselectInbandTrack(((Integer)pair1.first).intValue(), false);
        }
        vector;
        JVM INSTR monitorexit ;
_L2:
        mSubtitleController.selectTrack(subtitletrack);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void setDataSource(String s, Map map, List list)
        throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
    {
        String as[] = null;
        String as1[] = null;
        if(map != null)
        {
            String as2[] = new String[map.size()];
            String as3[] = new String[map.size()];
            int i = 0;
            map = map.entrySet().iterator();
            do
            {
                as = as2;
                as1 = as3;
                if(!map.hasNext())
                    break;
                as = (java.util.Map.Entry)map.next();
                as2[i] = (String)as.getKey();
                as3[i] = (String)as.getValue();
                i++;
            } while(true);
        }
        setDataSource(s, as, as1, list);
    }

    private void setDataSource(String s, String as[], String as1[], List list)
        throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
    {
        Object obj = Uri.parse(s);
        String s1 = ((Uri) (obj)).getScheme();
        if("file".equals(s1))
        {
            obj = ((Uri) (obj)).getPath();
        } else
        {
            obj = s;
            if(s1 != null)
            {
                nativeSetDataSource(MediaHTTPService.createHttpServiceBinderIfNecessary(s, list), s, as, as1);
                return;
            }
        }
        s = new File(((String) (obj)));
        if(s.exists())
        {
            s = new FileInputStream(s);
            setDataSource(s.getFD());
            s.close();
            return;
        } else
        {
            throw new IOException("setDataSource failed.");
        }
    }

    private native boolean setParameter(int i, Parcel parcel);

    private void setSubtitleAnchor()
    {
        this;
        JVM INSTR monitorenter ;
        HandlerThread handlerthread;
        if(mSubtitleController != null || ActivityThread.currentApplication() == null)
            break MISSING_BLOCK_LABEL_62;
        handlerthread = JVM INSTR new #1042 <Class HandlerThread>;
        handlerthread.HandlerThread("SetSubtitleAnchorThread");
        handlerthread.start();
        Handler handler = JVM INSTR new #1048 <Class Handler>;
        handler.Handler(handlerthread.getLooper());
        Runnable runnable = JVM INSTR new #16  <Class MediaPlayer$4>;
        runnable.this. _cls4();
        handler.post(runnable);
        handlerthread.join();
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        Thread.currentThread().interrupt();
        Log.w("MediaPlayer", "failed to join SetSubtitleAnchorThread");
          goto _L1
        obj;
        throw obj;
    }

    private void startImpl()
    {
        baseStart();
        stayAwake(true);
        _start();
    }

    private void stayAwake(boolean flag)
    {
        if(mWakeLock == null) goto _L2; else goto _L1
_L1:
        if(!flag || !(mWakeLock.isHeld() ^ true)) goto _L4; else goto _L3
_L3:
        mWakeLock.acquire();
_L2:
        MediaPlayerInjector.updateActiveProcessStatus(mStayAwake, flag, Process.myPid(), Process.myUid());
        mStayAwake = flag;
        updateSurfaceScreenOn();
        return;
_L4:
        if(!flag && mWakeLock.isHeld())
            mWakeLock.release();
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void updateSurfaceScreenOn()
    {
        if(mSurfaceHolder != null)
        {
            SurfaceHolder surfaceholder = mSurfaceHolder;
            boolean flag;
            if(mScreenOnWhilePlaying)
                flag = mStayAwake;
            else
                flag = false;
            surfaceholder.setKeepScreenOn(flag);
        }
    }

    public void addSubtitleSource(final InputStream fIs, final MediaFormat fFormat)
        throws IllegalStateException
    {
        if(fIs == null) goto _L2; else goto _L1
_L1:
        final Object thread = mOpenSubtitleSources;
        thread;
        JVM INSTR monitorenter ;
        mOpenSubtitleSources.add(fIs);
        thread;
        JVM INSTR monitorexit ;
_L4:
        getMediaTimeProvider();
        thread = new HandlerThread("SubtitleReadThread", 9);
        ((HandlerThread) (thread)).start();
        (new Handler(((HandlerThread) (thread)).getLooper())).post(new Runnable() {

            private int addTrack()
            {
                SubtitleTrack subtitletrack;
                Scanner scanner;
                String s;
                if(fIs == null || MediaPlayer._2D_get20(MediaPlayer.this) == null)
                    return 901;
                subtitletrack = MediaPlayer._2D_get20(MediaPlayer.this).addTrack(fFormat);
                if(subtitletrack == null)
                    return 901;
                scanner = new Scanner(fIs, "UTF-8");
                s = scanner.useDelimiter("\\A").next();
                Object obj = MediaPlayer._2D_get19(MediaPlayer.this);
                obj;
                JVM INSTR monitorenter ;
                MediaPlayer._2D_get19(MediaPlayer.this).remove(fIs);
                obj;
                JVM INSTR monitorexit ;
                scanner.close();
                obj = MediaPlayer._2D_get4(MediaPlayer.this);
                obj;
                JVM INSTR monitorenter ;
                MediaPlayer._2D_get4(MediaPlayer.this).add(Pair.create(null, subtitletrack));
                obj;
                JVM INSTR monitorexit ;
                obj = TimeProvider._2D_get0(MediaPlayer._2D_get21(MediaPlayer.this));
                ((Handler) (obj)).sendMessage(((Handler) (obj)).obtainMessage(1, 4, 0, Pair.create(subtitletrack, s.getBytes())));
                return 803;
                Exception exception;
                exception;
                throw exception;
                exception;
                throw exception;
            }

            public void run()
            {
                int i = addTrack();
                if(MediaPlayer._2D_get3(MediaPlayer.this) != null)
                {
                    Message message = MediaPlayer._2D_get3(MediaPlayer.this).obtainMessage(200, i, 0, null);
                    MediaPlayer._2D_get3(MediaPlayer.this).sendMessage(message);
                }
                thread.getLooper().quitSafely();
            }

            final MediaPlayer this$0;
            final MediaFormat val$fFormat;
            final InputStream val$fIs;
            final HandlerThread val$thread;

            
            {
                this$0 = MediaPlayer.this;
                fIs = inputstream;
                fFormat = mediaformat;
                thread = handlerthread;
                super();
            }
        }
);
        return;
        fIs;
        throw fIs;
_L2:
        Log.w("MediaPlayer", "addSubtitleSource called with null InputStream");
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void addTimedTextSource(Context context, Uri uri, String s)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        Object obj;
        Context context1;
        Context context2;
        obj = uri.getScheme();
        if(obj == null || ((String) (obj)).equals("file"))
        {
            addTimedTextSource(uri.getPath(), s);
            return;
        }
        context1 = null;
        context2 = null;
        obj = null;
        context = context.getContentResolver().openAssetFileDescriptor(uri, "r");
        if(context == null)
        {
            if(context != null)
                context.close();
            return;
        }
        obj = context;
        context1 = context;
        context2 = context;
        addTimedTextSource(context.getFileDescriptor(), s);
        if(context != null)
            context.close();
        return;
        context;
        if(obj != null)
            ((AssetFileDescriptor) (obj)).close();
_L2:
        return;
        context;
        if(context1 != null)
            context1.close();
        if(true) goto _L2; else goto _L1
_L1:
        context;
        if(context2 != null)
            context2.close();
        throw context;
    }

    public void addTimedTextSource(final FileDescriptor dupedFd, final long offset2, final long length2, final String track)
        throws IllegalArgumentException, IllegalStateException
    {
        final Object thread;
        if(!availableMimeTypeForExternalSource(track))
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal mimeType for timed text source: ").append(track).toString());
        try
        {
            dupedFd = Libcore.os.dup(dupedFd);
        }
        // Misplaced declaration of an exception variable
        catch(final FileDescriptor dupedFd)
        {
            Log.e("MediaPlayer", dupedFd.getMessage(), dupedFd);
            throw new RuntimeException(dupedFd);
        }
        thread = new MediaFormat();
        ((MediaFormat) (thread)).setString("mime", track);
        ((MediaFormat) (thread)).setInteger("is-timed-text", 1);
        if(mSubtitleController == null)
            setSubtitleAnchor();
        if(!mSubtitleController.hasRendererFor(((MediaFormat) (thread))))
        {
            track = ActivityThread.currentApplication();
            mSubtitleController.registerRenderer(new SRTRenderer(track, mEventHandler));
        }
        track = mSubtitleController.addTrack(((MediaFormat) (thread)));
        thread = mIndexTrackPairs;
        thread;
        JVM INSTR monitorenter ;
        mIndexTrackPairs.add(Pair.create(null, track));
        thread;
        JVM INSTR monitorexit ;
        getMediaTimeProvider();
        thread = new HandlerThread("TimedTextReadThread", 9);
        ((HandlerThread) (thread)).start();
        (new Handler(((HandlerThread) (thread)).getLooper())).post(new Runnable() {

            private int addTrack()
            {
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                byte abyte0[];
                Libcore.os.lseek(dupedFd, offset2, OsConstants.SEEK_SET);
                abyte0 = new byte[4096];
                long l = 0L;
_L1:
                int i;
                if(l >= length2)
                    break MISSING_BLOCK_LABEL_78;
                i = (int)Math.min(abyte0.length, length2 - l);
                i = IoBridge.read(dupedFd, abyte0, 0, i);
                if(i >= 0)
                    break MISSING_BLOCK_LABEL_128;
                abyte0 = TimeProvider._2D_get0(MediaPlayer._2D_get21(MediaPlayer.this));
                abyte0.sendMessage(abyte0.obtainMessage(1, 4, 0, Pair.create(track, bytearrayoutputstream.toByteArray())));
                try
                {
                    Libcore.os.close(dupedFd);
                }
                catch(ErrnoException errnoexception)
                {
                    Log.e("MediaPlayer", errnoexception.getMessage(), errnoexception);
                }
                return 803;
                bytearrayoutputstream.write(abyte0, 0, i);
                l += i;
                  goto _L1
                Object obj;
                obj;
                Log.e("MediaPlayer", ((Exception) (obj)).getMessage(), ((Throwable) (obj)));
                try
                {
                    Libcore.os.close(dupedFd);
                }
                // Misplaced declaration of an exception variable
                catch(Object obj)
                {
                    Log.e("MediaPlayer", ((ErrnoException) (obj)).getMessage(), ((Throwable) (obj)));
                }
                return 900;
                obj;
                try
                {
                    Libcore.os.close(dupedFd);
                }
                catch(ErrnoException errnoexception1)
                {
                    Log.e("MediaPlayer", errnoexception1.getMessage(), errnoexception1);
                }
                throw obj;
            }

            public void run()
            {
                int i = addTrack();
                if(MediaPlayer._2D_get3(MediaPlayer.this) != null)
                {
                    Message message = MediaPlayer._2D_get3(MediaPlayer.this).obtainMessage(200, i, 0, null);
                    MediaPlayer._2D_get3(MediaPlayer.this).sendMessage(message);
                }
                thread.getLooper().quitSafely();
            }

            final MediaPlayer this$0;
            final FileDescriptor val$dupedFd;
            final long val$length2;
            final long val$offset2;
            final HandlerThread val$thread;
            final SubtitleTrack val$track;

            
            {
                this$0 = MediaPlayer.this;
                dupedFd = filedescriptor;
                offset2 = l;
                length2 = l1;
                track = subtitletrack;
                thread = handlerthread;
                super();
            }
        }
);
        return;
        dupedFd;
        throw dupedFd;
    }

    public void addTimedTextSource(FileDescriptor filedescriptor, String s)
        throws IllegalArgumentException, IllegalStateException
    {
        addTimedTextSource(filedescriptor, 0L, 0x7ffffffffffffffL, s);
    }

    public void addTimedTextSource(String s, String s1)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        if(!availableMimeTypeForExternalSource(s1))
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal mimeType for timed text source: ").append(s1).toString());
        File file = new File(s);
        if(file.exists())
        {
            s = new FileInputStream(file);
            addTimedTextSource(s.getFD(), s1);
            s.close();
            return;
        } else
        {
            throw new IOException(s);
        }
    }

    public native void attachAuxEffect(int i);

    public VolumeShaper createVolumeShaper(VolumeShaper.Configuration configuration)
    {
        return new VolumeShaper(configuration, this);
    }

    public void deselectTrack(int i)
        throws IllegalStateException
    {
        selectOrDeselectTrack(i, false);
    }

    public PlaybackParams easyPlaybackParams(float f, int i)
    {
        PlaybackParams playbackparams;
        playbackparams = new PlaybackParams();
        playbackparams.allowDefaults();
        i;
        JVM INSTR tableswitch 0 2: default 40
    //                   0 74
    //                   1 86
    //                   2 103;
           goto _L1 _L2 _L3 _L4
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Audio playback mode ").append(i).append(" is not supported").toString());
_L2:
        playbackparams.setSpeed(f).setPitch(1.0F);
_L6:
        return playbackparams;
_L3:
        playbackparams.setSpeed(f).setPitch(1.0F).setAudioFallbackMode(2);
        continue; /* Loop/switch isn't completed */
_L4:
        playbackparams.setSpeed(f).setPitch(f);
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected void finalize()
    {
        baseRelease();
        native_finalize();
    }

    public native int getAudioSessionId();

    public native BufferingParams getBufferingParams();

    public native int getCurrentPosition();

    public native BufferingParams getDefaultBufferingParams();

    public DrmInfo getDrmInfo()
    {
label0:
        {
            obj = null;
            synchronized(mDrmLock)
            {
                if(!mDrmInfoResolved && mDrmInfo == null)
                {
                    Log.v("MediaPlayer", "The Player has not been prepared yet");
                    obj = JVM INSTR new #568 <Class IllegalStateException>;
                    ((IllegalStateException) (obj)).IllegalStateException("The Player has not been prepared yet");
                    throw obj;
                }
                break label0;
            }
        }
        if(mDrmInfo != null)
            obj = DrmInfo._2D_wrap0(mDrmInfo);
        obj1;
        JVM INSTR monitorexit ;
        return ((DrmInfo) (obj));
    }

    public String getDrmPropertyString(String s)
        throws NoDrmSchemeException
    {
label0:
        {
            Log.v("MediaPlayer", (new StringBuilder()).append("getDrmPropertyString: propertyName: ").append(s).toString());
            synchronized(mDrmLock)
            {
                if(!mActiveDrmScheme && mDrmConfigAllowed ^ true)
                {
                    Log.w("MediaPlayer", "getDrmPropertyString NoDrmSchemeException");
                    s = JVM INSTR new #35  <Class MediaPlayer$NoDrmSchemeException>;
                    s.NoDrmSchemeException("getDrmPropertyString: Has to prepareDrm() first.");
                    throw s;
                }
                break label0;
            }
        }
        String s1 = mDrmObj.getPropertyString(s);
        obj;
        JVM INSTR monitorexit ;
        Log.v("MediaPlayer", (new StringBuilder()).append("getDrmPropertyString: propertyName: ").append(s).append(" --> value: ").append(s1).toString());
        return s1;
        s;
        StringBuilder stringbuilder = JVM INSTR new #513 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.w("MediaPlayer", stringbuilder.append("getDrmPropertyString Exception ").append(s).toString());
        throw s;
    }

    public native int getDuration();

    public MediaDrm.KeyRequest getKeyRequest(byte abyte0[], byte abyte1[], String s, int i, Map map)
        throws NoDrmSchemeException
    {
label0:
        {
            Log.v("MediaPlayer", (new StringBuilder()).append("getKeyRequest:  keySetId: ").append(abyte0).append(" initData:").append(abyte1).append(" mimeType: ").append(s).append(" keyType: ").append(i).append(" optionalParameters: ").append(map).toString());
            synchronized(mDrmLock)
            {
                if(!mActiveDrmScheme)
                {
                    Log.e("MediaPlayer", "getKeyRequest NoDrmSchemeException");
                    abyte0 = JVM INSTR new #35  <Class MediaPlayer$NoDrmSchemeException>;
                    abyte0.NoDrmSchemeException("getKeyRequest: Has to set a DRM scheme first.");
                    throw abyte0;
                }
                break label0;
            }
        }
        if(i == 3)
            break MISSING_BLOCK_LABEL_123;
        abyte0 = mDrmSessionId;
        if(map == null) goto _L2; else goto _L1
_L1:
        HashMap hashmap;
        hashmap = JVM INSTR new #1299 <Class HashMap>;
        hashmap.HashMap(map);
        map = hashmap;
_L4:
        abyte1 = mDrmObj.getKeyRequest(abyte0, abyte1, s, i, map);
        abyte0 = JVM INSTR new #513 <Class StringBuilder>;
        abyte0.StringBuilder();
        Log.v("MediaPlayer", abyte0.append("getKeyRequest:   --> request: ").append(abyte1).toString());
        obj;
        JVM INSTR monitorexit ;
        return abyte1;
_L2:
        map = null;
        if(true) goto _L4; else goto _L3
_L3:
        abyte1;
        abyte0 = JVM INSTR new #513 <Class StringBuilder>;
        abyte0.StringBuilder();
        Log.w("MediaPlayer", abyte0.append("getKeyRequest Exception ").append(abyte1).toString());
        throw abyte1;
        abyte0;
        Log.w("MediaPlayer", "getKeyRequest NotProvisionedException: Unexpected. Shouldn't have reached here.");
        abyte0 = JVM INSTR new #568 <Class IllegalStateException>;
        abyte0.IllegalStateException("getKeyRequest: Unexpected provisioning error.");
        throw abyte0;
    }

    public MediaTimeProvider getMediaTimeProvider()
    {
        if(mTimeProvider == null)
            mTimeProvider = new TimeProvider(this);
        return mTimeProvider;
    }

    public Metadata getMetadata(boolean flag, boolean flag1)
    {
        Parcel parcel = Parcel.obtain();
        Metadata metadata = new Metadata();
        if(!native_getMetadata(flag, flag1, parcel))
        {
            parcel.recycle();
            return null;
        }
        if(!metadata.parse(parcel))
        {
            parcel.recycle();
            return null;
        } else
        {
            return metadata;
        }
    }

    public PersistableBundle getMetrics()
    {
        return native_getMetrics();
    }

    public native PlaybackParams getPlaybackParams();

    public int getSelectedTrack(int i)
        throws IllegalStateException
    {
        if(mSubtitleController == null || i != 4 && i != 3) goto _L2; else goto _L1
_L1:
        Object obj = mSubtitleController.getSelectedTrack();
        if(obj == null) goto _L2; else goto _L3
_L3:
        Object obj1 = mIndexTrackPairs;
        obj1;
        JVM INSTR monitorenter ;
        int j = 0;
_L4:
        int k;
        if(j >= mIndexTrackPairs.size())
            break; /* Loop/switch isn't completed */
        if(((Pair)mIndexTrackPairs.get(j)).second != obj)
            break MISSING_BLOCK_LABEL_87;
        k = ((SubtitleTrack) (obj)).getTrackType();
        if(k != i)
            break MISSING_BLOCK_LABEL_87;
        obj1;
        JVM INSTR monitorexit ;
        return j;
        j++;
        if(true) goto _L4; else goto _L2
_L2:
        obj1 = Parcel.obtain();
        obj = Parcel.obtain();
        ((Parcel) (obj1)).writeInterfaceToken("android.media.IMediaPlayer");
        ((Parcel) (obj1)).writeInt(7);
        ((Parcel) (obj1)).writeInt(i);
        invoke(((Parcel) (obj1)), ((Parcel) (obj)));
        j = ((Parcel) (obj)).readInt();
        Vector vector = mIndexTrackPairs;
        vector;
        JVM INSTR monitorenter ;
        i = 0;
_L6:
        if(i >= mIndexTrackPairs.size())
            break; /* Loop/switch isn't completed */
        Pair pair = (Pair)mIndexTrackPairs.get(i);
        if(pair.first == null)
            break MISSING_BLOCK_LABEL_213;
        k = ((Integer)pair.first).intValue();
        if(k != j)
            break MISSING_BLOCK_LABEL_213;
        vector;
        JVM INSTR monitorexit ;
        ((Parcel) (obj1)).recycle();
        ((Parcel) (obj)).recycle();
        return i;
        obj;
        throw obj;
        i++;
        if(true) goto _L6; else goto _L5
_L5:
        vector;
        JVM INSTR monitorexit ;
        ((Parcel) (obj1)).recycle();
        ((Parcel) (obj)).recycle();
        return -1;
        Exception exception1;
        exception1;
        vector;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        ((Parcel) (obj1)).recycle();
        ((Parcel) (obj)).recycle();
        throw exception;
    }

    public native SyncParams getSyncParams();

    public MediaTimestamp getTimestamp()
    {
        long l;
        long l1;
        float f;
        l = getCurrentPosition();
        l1 = System.nanoTime();
        if(!isPlaying())
            break MISSING_BLOCK_LABEL_46;
        f = getPlaybackParams().getSpeed();
_L1:
        MediaTimestamp mediatimestamp = new MediaTimestamp(l * 1000L, l1, f);
        return mediatimestamp;
        f = 0.0F;
          goto _L1
        IllegalStateException illegalstateexception;
        illegalstateexception;
        return null;
    }

    public TrackInfo[] getTrackInfo()
        throws IllegalStateException
    {
        TrackInfo atrackinfo[] = getInbandTrackInfo();
        Vector vector = mIndexTrackPairs;
        vector;
        JVM INSTR monitorenter ;
        TrackInfo atrackinfo1[] = new TrackInfo[mIndexTrackPairs.size()];
        int i = 0;
_L2:
        Object obj;
        if(i >= atrackinfo1.length)
            break MISSING_BLOCK_LABEL_117;
        obj = (Pair)mIndexTrackPairs.get(i);
        if(((Pair) (obj)).first == null)
            break; /* Loop/switch isn't completed */
        atrackinfo1[i] = atrackinfo[((Integer)((Pair) (obj)).first).intValue()];
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        obj = (SubtitleTrack)((Pair) (obj)).second;
        atrackinfo1[i] = new TrackInfo(((SubtitleTrack) (obj)).getTrackType(), ((SubtitleTrack) (obj)).getFormat());
          goto _L3
        Exception exception;
        exception;
        throw exception;
        vector;
        JVM INSTR monitorexit ;
        return atrackinfo1;
    }

    public native int getVideoHeight();

    public native int getVideoWidth();

    public void invoke(Parcel parcel, Parcel parcel1)
    {
        int i = native_invoke(parcel, parcel1);
        parcel1.setDataPosition(0);
        if(i != 0)
            throw new RuntimeException((new StringBuilder()).append("failure code: ").append(i).toString());
        else
            return;
    }

    public native boolean isLooping();

    public native boolean isPlaying();

    public Parcel newRequest()
    {
        Parcel parcel = Parcel.obtain();
        parcel.writeInterfaceToken("android.media.IMediaPlayer");
        return parcel;
    }

    public void onSubtitleTrackSelected(SubtitleTrack subtitletrack)
    {
        if(mSelectedSubtitleTrackIndex >= 0)
        {
            Vector vector;
            Iterator iterator;
            Pair pair;
            try
            {
                selectOrDeselectInbandTrack(mSelectedSubtitleTrackIndex, false);
            }
            catch(IllegalStateException illegalstateexception) { }
            mSelectedSubtitleTrackIndex = -1;
        }
        setOnSubtitleDataListener(null);
        if(subtitletrack == null)
            return;
        vector = mIndexTrackPairs;
        vector;
        JVM INSTR monitorenter ;
        iterator = mIndexTrackPairs.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            pair = (Pair)iterator.next();
            if(pair.first == null || pair.second != subtitletrack)
                continue;
            mSelectedSubtitleTrackIndex = ((Integer)pair.first).intValue();
            break;
        } while(true);
        vector;
        JVM INSTR monitorexit ;
        if(mSelectedSubtitleTrackIndex >= 0)
        {
            try
            {
                selectOrDeselectInbandTrack(mSelectedSubtitleTrackIndex, true);
            }
            // Misplaced declaration of an exception variable
            catch(SubtitleTrack subtitletrack) { }
            setOnSubtitleDataListener(mSubtitleDataListener);
        }
        return;
        subtitletrack;
        throw subtitletrack;
    }

    public void pause()
        throws IllegalStateException
    {
        stayAwake(false);
        _pause();
        basePause();
    }

    int playerApplyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation)
    {
        return native_applyVolumeShaper(configuration, operation);
    }

    VolumeShaper.State playerGetVolumeShaperState(int i)
    {
        return native_getVolumeShaperState(i);
    }

    void playerPause()
    {
        pause();
    }

    int playerSetAuxEffectSendLevel(boolean flag, float f)
    {
        if(flag)
            f = 0.0F;
        _setAuxEffectSendLevel(f);
        return 0;
    }

    void playerSetVolume(boolean flag, float f, float f1)
    {
        if(flag)
            f = 0.0F;
        if(flag)
            f1 = 0.0F;
        _setVolume(f, f1);
    }

    void playerStart()
    {
        start();
    }

    void playerStop()
    {
        stop();
    }

    public void prepare()
        throws IOException, IllegalStateException
    {
        _prepare();
        scanInternalSubtitleTracks();
        Object obj = mDrmLock;
        obj;
        JVM INSTR monitorenter ;
        mDrmInfoResolved = true;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public native void prepareAsync()
        throws IllegalStateException;

    public void prepareDrm(UUID uuid)
        throws UnsupportedSchemeException, ResourceBusyException, ProvisioningNetworkErrorException, ProvisioningServerErrorException
    {
        boolean flag;
label0:
        {
            Log.v("MediaPlayer", (new StringBuilder()).append("prepareDrm: uuid: ").append(uuid).append(" mOnDrmConfigHelper: ").append(mOnDrmConfigHelper).toString());
            flag = false;
            synchronized(mDrmLock)
            {
                if(mDrmInfo == null)
                {
                    Log.e("MediaPlayer", "prepareDrm(): Wrong usage: The player must be prepared and DRM info be retrieved before this call.");
                    uuid = JVM INSTR new #568 <Class IllegalStateException>;
                    uuid.IllegalStateException("prepareDrm(): Wrong usage: The player must be prepared and DRM info be retrieved before this call.");
                    throw uuid;
                }
                break label0;
            }
        }
        Object obj2;
        if(mActiveDrmScheme)
        {
            uuid = JVM INSTR new #513 <Class StringBuilder>;
            uuid.StringBuilder();
            uuid = uuid.append("prepareDrm(): Wrong usage: There is already an active DRM scheme with ").append(mDrmUUID).toString();
            Log.e("MediaPlayer", uuid);
            IllegalStateException illegalstateexception = JVM INSTR new #568 <Class IllegalStateException>;
            illegalstateexception.IllegalStateException(uuid);
            throw illegalstateexception;
        }
        if(mPrepareDrmInProgress)
        {
            Log.e("MediaPlayer", "prepareDrm(): Wrong usage: There is already a pending prepareDrm call.");
            uuid = JVM INSTR new #568 <Class IllegalStateException>;
            uuid.IllegalStateException("prepareDrm(): Wrong usage: There is already a pending prepareDrm call.");
            throw uuid;
        }
        if(mDrmProvisioningInProgress)
        {
            Log.e("MediaPlayer", "prepareDrm(): Unexpectd: Provisioning is already in progress.");
            uuid = JVM INSTR new #568 <Class IllegalStateException>;
            uuid.IllegalStateException("prepareDrm(): Unexpectd: Provisioning is already in progress.");
            throw uuid;
        }
        cleanDrmObj();
        mPrepareDrmInProgress = true;
        obj2 = mOnDrmPreparedHandlerDelegate;
        prepareDrm_createDrmStep(uuid);
        mDrmConfigAllowed = true;
        obj;
        JVM INSTR monitorexit ;
        if(mOnDrmConfigHelper != null)
            mOnDrmConfigHelper.onDrmConfig(this);
        Object obj1 = mDrmLock;
        obj1;
        JVM INSTR monitorenter ;
        mDrmConfigAllowed = false;
        int i;
        int j;
        i = 0;
        j = i;
        prepareDrm_openSessionStep(uuid);
        j = i;
        mDrmUUID = uuid;
        j = i;
        mActiveDrmScheme = true;
        flag = true;
        if(!mDrmProvisioningInProgress)
            mPrepareDrmInProgress = false;
        j = ((flag) ? 1 : 0);
        if(true)
            break MISSING_BLOCK_LABEL_308;
        cleanDrmObj();
        j = ((flag) ? 1 : 0);
_L7:
        obj1;
        JVM INSTR monitorexit ;
        if(j != 0 && obj2 != null)
            ((OnDrmPreparedHandlerDelegate) (obj2)).notifyClient(0);
        return;
        uuid;
        Log.w("MediaPlayer", "prepareDrm(): Exception ", uuid);
        mPrepareDrmInProgress = false;
        throw uuid;
        uuid;
        j = i;
        obj2 = JVM INSTR new #513 <Class StringBuilder>;
        j = i;
        ((StringBuilder) (obj2)).StringBuilder();
        j = i;
        Log.e("MediaPlayer", ((StringBuilder) (obj2)).append("prepareDrm: Exception ").append(uuid).toString());
        j = 1;
        throw uuid;
        uuid;
        if(!mDrmProvisioningInProgress)
            mPrepareDrmInProgress = false;
        if(j == 0)
            break MISSING_BLOCK_LABEL_416;
        cleanDrmObj();
        throw uuid;
        uuid;
        obj1;
        JVM INSTR monitorexit ;
        throw uuid;
        NotProvisionedException notprovisionedexception;
        notprovisionedexception;
        j = i;
        Log.w("MediaPlayer", "prepareDrm: NotProvisionedException");
        j = i;
        i = HandleProvisioninig(uuid);
        if(i == 0) goto _L2; else goto _L1
_L1:
        flag = true;
        i;
        JVM INSTR tableswitch 1 2: default 480
    //                   1 514
    //                   2 548;
           goto _L3 _L4 _L5
_L3:
        j = ((flag) ? 1 : 0);
        Log.e("MediaPlayer", "prepareDrm: Post-provisioning preparation failed.");
        j = ((flag) ? 1 : 0);
        uuid = JVM INSTR new #568 <Class IllegalStateException>;
        j = ((flag) ? 1 : 0);
        uuid.IllegalStateException("prepareDrm: Post-provisioning preparation failed.");
        j = ((flag) ? 1 : 0);
        throw uuid;
_L4:
        j = ((flag) ? 1 : 0);
        Log.e("MediaPlayer", "prepareDrm: Provisioning was required but failed due to a network error.");
        j = ((flag) ? 1 : 0);
        uuid = JVM INSTR new #87  <Class MediaPlayer$ProvisioningNetworkErrorException>;
        j = ((flag) ? 1 : 0);
        uuid.ProvisioningNetworkErrorException("prepareDrm: Provisioning was required but failed due to a network error.");
        j = ((flag) ? 1 : 0);
        throw uuid;
_L5:
        j = ((flag) ? 1 : 0);
        Log.e("MediaPlayer", "prepareDrm: Provisioning was required but the request was denied by the server.");
        j = ((flag) ? 1 : 0);
        uuid = JVM INSTR new #90  <Class MediaPlayer$ProvisioningServerErrorException>;
        j = ((flag) ? 1 : 0);
        uuid.ProvisioningServerErrorException("prepareDrm: Provisioning was required but the request was denied by the server.");
        j = ((flag) ? 1 : 0);
        throw uuid;
_L2:
        if(!mDrmProvisioningInProgress)
            mPrepareDrmInProgress = false;
        j = ((flag) ? 1 : 0);
        if(true)
            continue; /* Loop/switch isn't completed */
        cleanDrmObj();
        j = ((flag) ? 1 : 0);
        if(true) goto _L7; else goto _L6
_L6:
        uuid;
        j = i;
        Log.e("MediaPlayer", "prepareDrm(): Wrong usage: The player must be in the prepared state to call prepareDrm().");
        flag = true;
        j = ((flag) ? 1 : 0);
        uuid = JVM INSTR new #568 <Class IllegalStateException>;
        j = ((flag) ? 1 : 0);
        uuid.IllegalStateException("prepareDrm(): Wrong usage: The player must be in the prepared state to call prepareDrm().");
        j = ((flag) ? 1 : 0);
        throw uuid;
    }

    public byte[] provideKeyResponse(byte abyte0[], byte abyte1[])
        throws NoDrmSchemeException, DeniedByServerException
    {
label0:
        {
            Log.v("MediaPlayer", (new StringBuilder()).append("provideKeyResponse: keySetId: ").append(abyte0).append(" response: ").append(abyte1).toString());
            synchronized(mDrmLock)
            {
                if(!mActiveDrmScheme)
                {
                    Log.e("MediaPlayer", "getKeyRequest NoDrmSchemeException");
                    abyte0 = JVM INSTR new #35  <Class MediaPlayer$NoDrmSchemeException>;
                    abyte0.NoDrmSchemeException("getKeyRequest: Has to set a DRM scheme first.");
                    throw abyte0;
                }
                break label0;
            }
        }
        if(abyte0 != null)
            break MISSING_BLOCK_LABEL_156;
        Object obj1 = mDrmSessionId;
_L1:
        byte abyte2[];
        abyte2 = mDrmObj.provideKeyResponse(((byte []) (obj1)), abyte1);
        obj1 = JVM INSTR new #513 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.v("MediaPlayer", ((StringBuilder) (obj1)).append("provideKeyResponse: keySetId: ").append(abyte0).append(" response: ").append(abyte1).append(" --> ").append(abyte2).toString());
        obj;
        JVM INSTR monitorexit ;
        return abyte2;
        obj1 = abyte0;
          goto _L1
        abyte1;
        abyte0 = JVM INSTR new #513 <Class StringBuilder>;
        abyte0.StringBuilder();
        Log.w("MediaPlayer", abyte0.append("provideKeyResponse Exception ").append(abyte1).toString());
        throw abyte1;
        abyte0;
        Log.w("MediaPlayer", "provideKeyResponse NotProvisionedException: Unexpected. Shouldn't have reached here.");
        abyte0 = JVM INSTR new #568 <Class IllegalStateException>;
        abyte0.IllegalStateException("provideKeyResponse: Unexpected provisioning error.");
        throw abyte0;
    }

    public void release()
    {
        baseRelease();
        stayAwake(false);
        updateSurfaceScreenOn();
        mOnPreparedListener = null;
        mOnBufferingUpdateListener = null;
        mOnCompletionListener = null;
        mOnSeekCompleteListener = null;
        mOnErrorListener = null;
        mOnInfoListener = null;
        mOnVideoSizeChangedListener = null;
        mOnTimedTextListener = null;
        if(mTimeProvider != null)
        {
            mTimeProvider.close();
            mTimeProvider = null;
        }
        mOnSubtitleDataListener = null;
        mOnDrmConfigHelper = null;
        mOnDrmInfoHandlerDelegate = null;
        mOnDrmPreparedHandlerDelegate = null;
        resetDrmState();
        _release();
    }

    public void releaseDrm()
        throws NoDrmSchemeException
    {
label0:
        {
            Log.v("MediaPlayer", "releaseDrm:");
            synchronized(mDrmLock)
            {
                if(!mActiveDrmScheme)
                {
                    Log.e("MediaPlayer", "releaseDrm(): No active DRM scheme to release.");
                    NoDrmSchemeException nodrmschemeexception = JVM INSTR new #35  <Class MediaPlayer$NoDrmSchemeException>;
                    nodrmschemeexception.NoDrmSchemeException("releaseDrm: No active DRM scheme to release.");
                    throw nodrmschemeexception;
                }
                break label0;
            }
        }
        _releaseDrm();
        cleanDrmObj();
        mActiveDrmScheme = false;
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        Log.e("MediaPlayer", "releaseDrm: Exception ", ((Throwable) (obj1)));
          goto _L1
        obj1;
        Log.w("MediaPlayer", "releaseDrm: Exception ", ((Throwable) (obj1)));
        IllegalStateException illegalstateexception = JVM INSTR new #568 <Class IllegalStateException>;
        illegalstateexception.IllegalStateException("releaseDrm: The player is not in a valid state.");
        throw illegalstateexception;
    }

    public void reset()
    {
        mSelectedSubtitleTrackIndex = -1;
        Vector vector = mOpenSubtitleSources;
        vector;
        JVM INSTR monitorenter ;
        Iterator iterator = mOpenSubtitleSources.iterator();
_L1:
        InputStream inputstream;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_52;
        inputstream = (InputStream)iterator.next();
        try
        {
            inputstream.close();
        }
        catch(IOException ioexception) { }
          goto _L1
        mOpenSubtitleSources.clear();
        vector;
        JVM INSTR monitorexit ;
        if(mSubtitleController != null)
            mSubtitleController.reset();
        if(mTimeProvider != null)
        {
            mTimeProvider.close();
            mTimeProvider = null;
        }
        stayAwake(false);
        _reset();
        if(mEventHandler != null)
            mEventHandler.removeCallbacksAndMessages(null);
        vector = mIndexTrackPairs;
        vector;
        JVM INSTR monitorenter ;
        mIndexTrackPairs.clear();
        mInbandTrackIndices.clear();
        vector;
        JVM INSTR monitorexit ;
        resetDrmState();
        return;
        Exception exception;
        exception;
        throw exception;
        exception;
        throw exception;
    }

    public void restoreKeys(byte abyte0[])
        throws NoDrmSchemeException
    {
label0:
        {
            Log.v("MediaPlayer", (new StringBuilder()).append("restoreKeys: keySetId: ").append(abyte0).toString());
            synchronized(mDrmLock)
            {
                if(!mActiveDrmScheme)
                {
                    Log.w("MediaPlayer", "restoreKeys NoDrmSchemeException");
                    abyte0 = JVM INSTR new #35  <Class MediaPlayer$NoDrmSchemeException>;
                    abyte0.NoDrmSchemeException("restoreKeys: Has to set a DRM scheme first.");
                    throw abyte0;
                }
                break label0;
            }
        }
        mDrmObj.restoreKeys(mDrmSessionId, abyte0);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        abyte0 = JVM INSTR new #513 <Class StringBuilder>;
        abyte0.StringBuilder();
        Log.w("MediaPlayer", abyte0.append("restoreKeys Exception ").append(exception).toString());
        throw exception;
    }

    public void seekTo(int i)
        throws IllegalStateException
    {
        seekTo(i, 0);
    }

    public void seekTo(long l, int i)
    {
        if(i < 0 || i > 3)
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal seek mode: ").append(i).toString());
        if(l <= 0x7fffffffL) goto _L2; else goto _L1
_L1:
        long l1;
        Log.w("MediaPlayer", (new StringBuilder()).append("seekTo offset ").append(l).append(" is too large, cap to ").append(0x7fffffff).toString());
        l1 = 0x7fffffffL;
_L4:
        _seekTo(l1, i);
        return;
_L2:
        l1 = l;
        if(l < 0xffffffff80000000L)
        {
            Log.w("MediaPlayer", (new StringBuilder()).append("seekTo offset ").append(l).append(" is too small, cap to ").append(0x80000000).toString());
            l1 = 0xffffffff80000000L;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void selectTrack(int i)
        throws IllegalStateException
    {
        selectOrDeselectTrack(i, true);
    }

    public void setAudioAttributes(AudioAttributes audioattributes)
        throws IllegalArgumentException
    {
        boolean flag = false;
        if(audioattributes == null)
            throw new IllegalArgumentException("Cannot set AudioAttributes to null");
        baseUpdateAudioAttributes(audioattributes);
        mUsage = audioattributes.getUsage();
        if((audioattributes.getAllFlags() & 0x40) != 0)
            flag = true;
        mBypassInterruptionPolicy = flag;
        Parcel parcel = Parcel.obtain();
        audioattributes.writeToParcel(parcel, 1);
        setParameter(1400, parcel);
        parcel.recycle();
    }

    public native void setAudioSessionId(int i)
        throws IllegalArgumentException, IllegalStateException;

    public void setAudioStreamType(int i)
    {
        deprecateStreamTypeForPlayback(i, "MediaPlayer", "setAudioStreamType()");
        baseUpdateAudioAttributes((new AudioAttributes.Builder()).setInternalLegacyStreamType(i).build());
        _setAudioStreamType(i);
        mStreamType = i;
    }

    public void setAuxEffectSendLevel(float f)
    {
        baseSetAuxEffectSendLevel(f);
    }

    public native void setBufferingParams(BufferingParams bufferingparams);

    public void setDataSource(Context context, Uri uri)
        throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
    {
        setDataSource(context, uri, ((Map) (null)), null);
    }

    public void setDataSource(Context context, Uri uri, Map map)
        throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
    {
        setDataSource(context, uri, map, null);
    }

    public void setDataSource(Context context, Uri uri, Map map, List list)
        throws IOException
    {
        if(context == null)
            throw new NullPointerException("context param can not be null.");
        if(uri == null)
            throw new NullPointerException("uri param can not be null.");
        if(list != null)
        {
            CookieHandler cookiehandler = CookieHandler.getDefault();
            if(cookiehandler != null && (cookiehandler instanceof CookieManager) ^ true)
                throw new IllegalArgumentException("The cookie handler has to be of CookieManager type when cookies are provided.");
        }
        ContentResolver contentresolver = context.getContentResolver();
        String s = uri.getScheme();
        String s1 = ContentProvider.getAuthorityWithoutUserId(uri.getAuthority());
        if("file".equals(s))
        {
            setDataSource(uri.getPath());
            return;
        }
        if("content".equals(s) && "settings".equals(s1))
        {
            int i = ExtraRingtoneManager.getDefaultSoundType(uri);
            Uri uri1 = RingtoneManager.getCacheForType(i, context.getUserId());
            context = ExtraRingtoneManager.getActualDefaultRingtoneUri(context, i);
            if(attemptDataSource(contentresolver, uri1))
                return;
            if(attemptDataSource(contentresolver, context))
                return;
            setDataSource(uri.toString(), map, list);
        } else
        {
            if(attemptDataSource(contentresolver, uri))
                return;
            setDataSource(uri.toString(), map, list);
        }
    }

    public void setDataSource(AssetFileDescriptor assetfiledescriptor)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        Preconditions.checkNotNull(assetfiledescriptor);
        if(assetfiledescriptor.getDeclaredLength() < 0L)
            setDataSource(assetfiledescriptor.getFileDescriptor());
        else
            setDataSource(assetfiledescriptor.getFileDescriptor(), assetfiledescriptor.getStartOffset(), assetfiledescriptor.getDeclaredLength());
    }

    public void setDataSource(MediaDataSource mediadatasource)
        throws IllegalArgumentException, IllegalStateException
    {
        _setDataSource(mediadatasource);
    }

    public void setDataSource(FileDescriptor filedescriptor)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        setDataSource(filedescriptor, 0L, 0x7ffffffffffffffL);
    }

    public void setDataSource(FileDescriptor filedescriptor, long l, long l1)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        _setDataSource(filedescriptor, l, l1);
    }

    public void setDataSource(String s)
        throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
    {
        setDataSource(s, ((Map) (null)), ((List) (null)));
    }

    public void setDataSource(String s, Map map)
        throws IOException, IllegalArgumentException, SecurityException, IllegalStateException
    {
        setDataSource(s, map, ((List) (null)));
    }

    public void setDisplay(SurfaceHolder surfaceholder)
    {
        mSurfaceHolder = surfaceholder;
        if(surfaceholder != null)
            surfaceholder = surfaceholder.getSurface();
        else
            surfaceholder = null;
        _setVideoSurface(surfaceholder);
        updateSurfaceScreenOn();
    }

    public void setDrmPropertyString(String s, String s1)
        throws NoDrmSchemeException
    {
label0:
        {
            Log.v("MediaPlayer", (new StringBuilder()).append("setDrmPropertyString: propertyName: ").append(s).append(" value: ").append(s1).toString());
            synchronized(mDrmLock)
            {
                if(!mActiveDrmScheme && mDrmConfigAllowed ^ true)
                {
                    Log.w("MediaPlayer", "setDrmPropertyString NoDrmSchemeException");
                    s = JVM INSTR new #35  <Class MediaPlayer$NoDrmSchemeException>;
                    s.NoDrmSchemeException("setDrmPropertyString: Has to prepareDrm() first.");
                    throw s;
                }
                break label0;
            }
        }
        mDrmObj.setPropertyString(s, s1);
        obj;
        JVM INSTR monitorexit ;
        return;
        s;
        s1 = JVM INSTR new #513 <Class StringBuilder>;
        s1.StringBuilder();
        Log.w("MediaPlayer", s1.append("setDrmPropertyString Exception ").append(s).toString());
        throw s;
    }

    public native void setLooping(boolean flag);

    public int setMetadataFilter(Set set, Set set1)
    {
        Parcel parcel = newRequest();
        int i = parcel.dataSize() + (set.size() + 1 + 1 + set1.size()) * 4;
        if(parcel.dataCapacity() < i)
            parcel.setDataCapacity(i);
        parcel.writeInt(set.size());
        for(set = set.iterator(); set.hasNext(); parcel.writeInt(((Integer)set.next()).intValue()));
        parcel.writeInt(set1.size());
        for(set = set1.iterator(); set.hasNext(); parcel.writeInt(((Integer)set.next()).intValue()));
        return native_setMetadataFilter(parcel);
    }

    public native void setNextMediaPlayer(MediaPlayer mediaplayer);

    public void setOnBufferingUpdateListener(OnBufferingUpdateListener onbufferingupdatelistener)
    {
        mOnBufferingUpdateListener = onbufferingupdatelistener;
    }

    public void setOnCompletionListener(OnCompletionListener oncompletionlistener)
    {
        mOnCompletionListener = oncompletionlistener;
    }

    public void setOnDrmConfigHelper(OnDrmConfigHelper ondrmconfighelper)
    {
        Object obj = mDrmLock;
        obj;
        JVM INSTR monitorenter ;
        mOnDrmConfigHelper = ondrmconfighelper;
        obj;
        JVM INSTR monitorexit ;
        return;
        ondrmconfighelper;
        throw ondrmconfighelper;
    }

    public void setOnDrmInfoListener(OnDrmInfoListener ondrminfolistener)
    {
        setOnDrmInfoListener(ondrminfolistener, null);
    }

    public void setOnDrmInfoListener(OnDrmInfoListener ondrminfolistener, Handler handler)
    {
        Object obj = mDrmLock;
        obj;
        JVM INSTR monitorenter ;
        if(ondrminfolistener == null)
            break MISSING_BLOCK_LABEL_34;
        OnDrmInfoHandlerDelegate ondrminfohandlerdelegate = JVM INSTR new #47  <Class MediaPlayer$OnDrmInfoHandlerDelegate>;
        ondrminfohandlerdelegate.this. OnDrmInfoHandlerDelegate(this, ondrminfolistener, handler);
        mOnDrmInfoHandlerDelegate = ondrminfohandlerdelegate;
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        mOnDrmInfoHandlerDelegate = null;
          goto _L1
        ondrminfolistener;
        throw ondrminfolistener;
    }

    public void setOnDrmPreparedListener(OnDrmPreparedListener ondrmpreparedlistener)
    {
        setOnDrmPreparedListener(ondrmpreparedlistener, null);
    }

    public void setOnDrmPreparedListener(OnDrmPreparedListener ondrmpreparedlistener, Handler handler)
    {
        Object obj = mDrmLock;
        obj;
        JVM INSTR monitorenter ;
        if(ondrmpreparedlistener == null)
            break MISSING_BLOCK_LABEL_34;
        OnDrmPreparedHandlerDelegate ondrmpreparedhandlerdelegate = JVM INSTR new #55  <Class MediaPlayer$OnDrmPreparedHandlerDelegate>;
        ondrmpreparedhandlerdelegate.this. OnDrmPreparedHandlerDelegate(this, ondrmpreparedlistener, handler);
        mOnDrmPreparedHandlerDelegate = ondrmpreparedhandlerdelegate;
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        mOnDrmPreparedHandlerDelegate = null;
          goto _L1
        ondrmpreparedlistener;
        throw ondrmpreparedlistener;
    }

    public void setOnErrorListener(OnErrorListener onerrorlistener)
    {
        mOnErrorListener = onerrorlistener;
    }

    public void setOnInfoListener(OnInfoListener oninfolistener)
    {
        mOnInfoListener = oninfolistener;
    }

    public void setOnPreparedListener(OnPreparedListener onpreparedlistener)
    {
        mOnPreparedListener = onpreparedlistener;
    }

    public void setOnSeekCompleteListener(OnSeekCompleteListener onseekcompletelistener)
    {
        mOnSeekCompleteListener = onseekcompletelistener;
    }

    public void setOnSubtitleDataListener(OnSubtitleDataListener onsubtitledatalistener)
    {
        mOnSubtitleDataListener = onsubtitledatalistener;
    }

    public void setOnTimedMetaDataAvailableListener(OnTimedMetaDataAvailableListener ontimedmetadataavailablelistener)
    {
        mOnTimedMetaDataAvailableListener = ontimedmetadataavailablelistener;
    }

    public void setOnTimedTextListener(OnTimedTextListener ontimedtextlistener)
    {
        mOnTimedTextListener = ontimedtextlistener;
    }

    public void setOnVideoSizeChangedListener(OnVideoSizeChangedListener onvideosizechangedlistener)
    {
        mOnVideoSizeChangedListener = onvideosizechangedlistener;
    }

    public native void setPlaybackParams(PlaybackParams playbackparams);

    public void setRetransmitEndpoint(InetSocketAddress inetsocketaddress)
        throws IllegalStateException, IllegalArgumentException
    {
        String s = null;
        int i = 0;
        if(inetsocketaddress != null)
        {
            s = inetsocketaddress.getAddress().getHostAddress();
            i = inetsocketaddress.getPort();
        }
        i = native_setRetransmitEndpoint(s, i);
        if(i != 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal re-transmit endpoint; native ret ").append(i).toString());
        else
            return;
    }

    public void setScreenOnWhilePlaying(boolean flag)
    {
        if(mScreenOnWhilePlaying != flag)
        {
            if(flag && mSurfaceHolder == null)
                Log.w("MediaPlayer", "setScreenOnWhilePlaying(true) is ineffective without a SurfaceHolder");
            mScreenOnWhilePlaying = flag;
            updateSurfaceScreenOn();
        }
    }

    public void setSubtitleAnchor(SubtitleController subtitlecontroller, SubtitleController.Anchor anchor)
    {
        mSubtitleController = subtitlecontroller;
        mSubtitleController.setAnchor(anchor);
    }

    public void setSurface(Surface surface)
    {
        if(mScreenOnWhilePlaying && surface != null)
            Log.w("MediaPlayer", "setScreenOnWhilePlaying(true) is ineffective for Surface");
        mSurfaceHolder = null;
        _setVideoSurface(surface);
        updateSurfaceScreenOn();
    }

    public native void setSyncParams(SyncParams syncparams);

    public void setVideoScalingMode(int i)
    {
        Parcel parcel;
        Parcel parcel1;
        if(!isVideoScalingModeSupported(i))
            throw new IllegalArgumentException((new StringBuilder()).append("Scaling mode ").append(i).append(" is not supported").toString());
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("android.media.IMediaPlayer");
        parcel.writeInt(6);
        parcel.writeInt(i);
        invoke(parcel, parcel1);
        parcel.recycle();
        parcel1.recycle();
        return;
        Exception exception;
        exception;
        parcel.recycle();
        parcel1.recycle();
        throw exception;
    }

    public void setVolume(float f)
    {
        setVolume(f, f);
    }

    public void setVolume(float f, float f1)
    {
        baseSetVolume(f, f1);
    }

    public void setWakeMode(Context context, int i)
    {
        boolean flag = false;
        boolean flag1 = false;
        if(SystemProperties.getBoolean("audio.offload.ignore_setawake", false))
        {
            Log.w("MediaPlayer", (new StringBuilder()).append("IGNORING setWakeMode ").append(i).toString());
            return;
        }
        if(mWakeLock != null)
        {
            flag = flag1;
            if(mWakeLock.isHeld())
            {
                flag = true;
                mWakeLock.release();
            }
            mWakeLock = null;
        }
        mWakeLock = ((PowerManager)context.getSystemService("power")).newWakeLock(0x20000000 | i, android/media/MediaPlayer.getName());
        mWakeLock.setReferenceCounted(false);
        if(flag)
            mWakeLock.acquire();
    }

    public void start()
        throws IllegalStateException
    {
        final int delay = getStartDelayMs();
        if(delay == 0)
            startImpl();
        else
            (new Thread() {

                public void run()
                {
                    try
                    {
                        Thread.sleep(delay);
                    }
                    catch(InterruptedException interruptedexception)
                    {
                        interruptedexception.printStackTrace();
                    }
                    baseSetStartDelayMs(0);
                    MediaPlayer._2D_wrap3(MediaPlayer.this);
_L2:
                    return;
                    IllegalStateException illegalstateexception;
                    illegalstateexception;
                    if(true) goto _L2; else goto _L1
_L1:
                }

                final MediaPlayer this$0;
                final int val$delay;

            
            {
                this$0 = MediaPlayer.this;
                delay = i;
                super();
            }
            }
).start();
    }

    public void stop()
        throws IllegalStateException
    {
        stayAwake(false);
        _stop();
        baseStop();
    }

    public static final boolean APPLY_METADATA_FILTER = true;
    public static final boolean BYPASS_METADATA_FILTER = false;
    private static final String IMEDIA_PLAYER = "android.media.IMediaPlayer";
    private static final int INVOKE_ID_ADD_EXTERNAL_SOURCE = 2;
    private static final int INVOKE_ID_ADD_EXTERNAL_SOURCE_FD = 3;
    private static final int INVOKE_ID_DESELECT_TRACK = 5;
    private static final int INVOKE_ID_GET_SELECTED_TRACK = 7;
    private static final int INVOKE_ID_GET_TRACK_INFO = 1;
    private static final int INVOKE_ID_SELECT_TRACK = 4;
    private static final int INVOKE_ID_SET_VIDEO_SCALE_MODE = 6;
    private static final int KEY_PARAMETER_AUDIO_ATTRIBUTES = 1400;
    private static final int MEDIA_BUFFERING_UPDATE = 3;
    private static final int MEDIA_DRM_INFO = 210;
    private static final int MEDIA_ERROR = 100;
    public static final int MEDIA_ERROR_IO = -1004;
    public static final int MEDIA_ERROR_MALFORMED = -1007;
    public static final int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 200;
    public static final int MEDIA_ERROR_SERVER_DIED = 100;
    public static final int MEDIA_ERROR_SYSTEM = 0x80000000;
    public static final int MEDIA_ERROR_TIMED_OUT = -110;
    public static final int MEDIA_ERROR_UNKNOWN = 1;
    public static final int MEDIA_ERROR_UNSUPPORTED = -1010;
    private static final int MEDIA_INFO = 200;
    public static final int MEDIA_INFO_AUDIO_NOT_PLAYING = 804;
    public static final int MEDIA_INFO_BAD_INTERLEAVING = 800;
    public static final int MEDIA_INFO_BUFFERING_END = 702;
    public static final int MEDIA_INFO_BUFFERING_START = 701;
    public static final int MEDIA_INFO_EXTERNAL_METADATA_UPDATE = 803;
    public static final int MEDIA_INFO_METADATA_UPDATE = 802;
    public static final int MEDIA_INFO_NETWORK_BANDWIDTH = 703;
    public static final int MEDIA_INFO_NOT_SEEKABLE = 801;
    public static final int MEDIA_INFO_STARTED_AS_NEXT = 2;
    public static final int MEDIA_INFO_SUBTITLE_TIMED_OUT = 902;
    public static final int MEDIA_INFO_TIMED_TEXT_ERROR = 900;
    public static final int MEDIA_INFO_UNKNOWN = 1;
    public static final int MEDIA_INFO_UNSUPPORTED_SUBTITLE = 901;
    public static final int MEDIA_INFO_VIDEO_NOT_PLAYING = 805;
    public static final int MEDIA_INFO_VIDEO_RENDERING_START = 3;
    public static final int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700;
    private static final int MEDIA_META_DATA = 202;
    public static final String MEDIA_MIMETYPE_TEXT_CEA_608 = "text/cea-608";
    public static final String MEDIA_MIMETYPE_TEXT_CEA_708 = "text/cea-708";
    public static final String MEDIA_MIMETYPE_TEXT_SUBRIP = "application/x-subrip";
    public static final String MEDIA_MIMETYPE_TEXT_VTT = "text/vtt";
    private static final int MEDIA_NOP = 0;
    private static final int MEDIA_PAUSED = 7;
    private static final int MEDIA_PLAYBACK_COMPLETE = 2;
    private static final int MEDIA_PREPARED = 1;
    private static final int MEDIA_SEEK_COMPLETE = 4;
    private static final int MEDIA_SET_VIDEO_SIZE = 5;
    private static final int MEDIA_SKIPPED = 9;
    private static final int MEDIA_STARTED = 6;
    private static final int MEDIA_STOPPED = 8;
    private static final int MEDIA_SUBTITLE_DATA = 201;
    private static final int MEDIA_TIMED_TEXT = 99;
    public static final boolean METADATA_ALL = false;
    public static final boolean METADATA_UPDATE_ONLY = true;
    public static final int PLAYBACK_RATE_AUDIO_MODE_DEFAULT = 0;
    public static final int PLAYBACK_RATE_AUDIO_MODE_RESAMPLE = 2;
    public static final int PLAYBACK_RATE_AUDIO_MODE_STRETCH = 1;
    public static final int PREPARE_DRM_STATUS_PREPARATION_ERROR = 3;
    public static final int PREPARE_DRM_STATUS_PROVISIONING_NETWORK_ERROR = 1;
    public static final int PREPARE_DRM_STATUS_PROVISIONING_SERVER_ERROR = 2;
    public static final int PREPARE_DRM_STATUS_SUCCESS = 0;
    public static final int SEEK_CLOSEST = 3;
    public static final int SEEK_CLOSEST_SYNC = 2;
    public static final int SEEK_NEXT_SYNC = 1;
    public static final int SEEK_PREVIOUS_SYNC = 0;
    private static final String TAG = "MediaPlayer";
    public static final int VIDEO_SCALING_MODE_SCALE_TO_FIT = 1;
    public static final int VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING = 2;
    private boolean mActiveDrmScheme;
    private boolean mBypassInterruptionPolicy;
    private boolean mDrmConfigAllowed;
    private DrmInfo mDrmInfo;
    private boolean mDrmInfoResolved;
    private final Object mDrmLock = new Object();
    private MediaDrm mDrmObj;
    private boolean mDrmProvisioningInProgress;
    private ProvisioningThread mDrmProvisioningThread;
    private byte mDrmSessionId[];
    private UUID mDrmUUID;
    private EventHandler mEventHandler;
    private BitSet mInbandTrackIndices;
    private Vector mIndexTrackPairs;
    private int mListenerContext;
    private long mNativeContext;
    private long mNativeSurfaceTexture;
    private OnBufferingUpdateListener mOnBufferingUpdateListener;
    private final OnCompletionListener mOnCompletionInternalListener = new OnCompletionListener() {

        public void onCompletion(MediaPlayer mediaplayer)
        {
            baseStop();
        }

        final MediaPlayer this$0;

            
            {
                this$0 = MediaPlayer.this;
                super();
            }
    }
;
    private OnCompletionListener mOnCompletionListener;
    private OnDrmConfigHelper mOnDrmConfigHelper;
    private OnDrmInfoHandlerDelegate mOnDrmInfoHandlerDelegate;
    private OnDrmPreparedHandlerDelegate mOnDrmPreparedHandlerDelegate;
    private OnErrorListener mOnErrorListener;
    private OnInfoListener mOnInfoListener;
    private OnPreparedListener mOnPreparedListener;
    private OnSeekCompleteListener mOnSeekCompleteListener;
    private OnSubtitleDataListener mOnSubtitleDataListener;
    private OnTimedMetaDataAvailableListener mOnTimedMetaDataAvailableListener;
    private OnTimedTextListener mOnTimedTextListener;
    private OnVideoSizeChangedListener mOnVideoSizeChangedListener;
    private Vector mOpenSubtitleSources;
    private boolean mPrepareDrmInProgress;
    private boolean mScreenOnWhilePlaying;
    private int mSelectedSubtitleTrackIndex;
    private boolean mStayAwake;
    private int mStreamType;
    private SubtitleController mSubtitleController;
    private OnSubtitleDataListener mSubtitleDataListener;
    private SurfaceHolder mSurfaceHolder;
    private TimeProvider mTimeProvider;
    private int mUsage;
    private android.os.PowerManager.WakeLock mWakeLock;

    static 
    {
        System.loadLibrary("media_jni");
        native_init();
    }

    // Unreferenced inner class android/media/MediaPlayer$4

/* anonymous class */
    class _cls4
        implements Runnable
    {

        public void run()
        {
            android.app.Application application = ActivityThread.currentApplication();
            MediaPlayer._2D_set2(MediaPlayer.this, new SubtitleController(application, MediaPlayer._2D_get21(MediaPlayer.this), MediaPlayer.this));
            MediaPlayer._2D_get20(MediaPlayer.this).setAnchor(new SubtitleController.Anchor() {

                public Looper getSubtitleLooper()
                {
                    return Looper.getMainLooper();
                }

                public void setSubtitleWidget(SubtitleTrack.RenderingWidget renderingwidget)
                {
                }

                final _cls4 this$1;

            
            {
                this$1 = _cls4.this;
                super();
            }
            }
);
            thread.getLooper().quitSafely();
        }

        final MediaPlayer this$0;
        final HandlerThread val$thread;

            
            {
                this$0 = MediaPlayer.this;
                thread = handlerthread;
                super();
            }
    }

}
