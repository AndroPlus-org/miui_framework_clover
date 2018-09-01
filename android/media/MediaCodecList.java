// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.util.Log;
import java.util.*;

// Referenced classes of package android.media:
//            MediaFormat, MediaCodecInfo

public final class MediaCodecList
{

    private MediaCodecList()
    {
        this(0);
    }

    public MediaCodecList(int i)
    {
        initCodecList();
        if(i == 0)
            mCodecInfos = sRegularCodecInfos;
        else
            mCodecInfos = sAllCodecInfos;
    }

    static final native int findCodecByName(String s);

    private String findCodecForFormat(boolean flag, MediaFormat mediaformat)
    {
        String s;
        MediaCodecInfo amediacodecinfo[];
        int i;
        int j;
        s = mediaformat.getString("mime");
        amediacodecinfo = mCodecInfos;
        i = 0;
        j = amediacodecinfo.length;
_L5:
        if(i >= j) goto _L2; else goto _L1
_L1:
        Object obj = amediacodecinfo[i];
        if(((MediaCodecInfo) (obj)).isEncoder() == flag) goto _L4; else goto _L3
_L3:
        i++;
          goto _L5
_L4:
        MediaCodecInfo.CodecCapabilities codeccapabilities = ((MediaCodecInfo) (obj)).getCapabilitiesForType(s);
        if(codeccapabilities == null) goto _L3; else goto _L6
_L6:
        if(!codeccapabilities.isFormatSupported(mediaformat)) goto _L3; else goto _L7
_L7:
        obj = ((MediaCodecInfo) (obj)).getName();
        return ((String) (obj));
_L2:
        return null;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
          goto _L3
    }

    static final native MediaCodecInfo.CodecCapabilities getCodecCapabilities(int i, String s);

    public static final int getCodecCount()
    {
        initCodecList();
        return sRegularCodecInfos.length;
    }

    public static final MediaCodecInfo getCodecInfoAt(int i)
    {
        initCodecList();
        if(i < 0 || i > sRegularCodecInfos.length)
            throw new IllegalArgumentException();
        else
            return sRegularCodecInfos[i];
    }

    static final native String getCodecName(int i);

    static final Map getGlobalSettings()
    {
        Object obj = sInitLock;
        obj;
        JVM INSTR monitorenter ;
        if(sGlobalSettings == null)
            sGlobalSettings = native_getGlobalSettings();
        obj;
        JVM INSTR monitorexit ;
        return sGlobalSettings;
        Exception exception;
        exception;
        throw exception;
    }

    public static MediaCodecInfo getInfoFor(String s)
    {
        initCodecList();
        return sAllCodecInfos[findCodecByName(s)];
    }

    private static MediaCodecInfo getNewCodecInfoAt(int i)
    {
        String as[] = getSupportedTypes(i);
        MediaCodecInfo.CodecCapabilities acodeccapabilities[] = new MediaCodecInfo.CodecCapabilities[as.length];
        int j = 0;
        int k = as.length;
        for(int l = 0; j < k; l++)
        {
            acodeccapabilities[l] = getCodecCapabilities(i, as[j]);
            j++;
        }

        return new MediaCodecInfo(getCodecName(i), isEncoder(i), acodeccapabilities);
    }

    static final native String[] getSupportedTypes(int i);

    private static final void initCodecList()
    {
        Object obj = sInitLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        Object obj1;
        ArrayList arraylist;
        if(sRegularCodecInfos != null)
            break MISSING_BLOCK_LABEL_134;
        i = native_getCodecCount();
        obj1 = JVM INSTR new #131 <Class ArrayList>;
        ((ArrayList) (obj1)).ArrayList();
        arraylist = JVM INSTR new #131 <Class ArrayList>;
        arraylist.ArrayList();
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        MediaCodecInfo mediacodecinfo;
        mediacodecinfo = getNewCodecInfoAt(j);
        arraylist.add(mediacodecinfo);
        mediacodecinfo = mediacodecinfo.makeRegular();
        if(mediacodecinfo == null)
            break MISSING_BLOCK_LABEL_74;
        ((ArrayList) (obj1)).add(mediacodecinfo);
_L3:
        j++;
        if(true) goto _L2; else goto _L1
        Exception exception;
        exception;
        Log.e("MediaCodecList", "Could not get codec capabilities", exception);
          goto _L3
        obj1;
        throw obj1;
_L1:
        sRegularCodecInfos = (MediaCodecInfo[])((ArrayList) (obj1)).toArray(new MediaCodecInfo[((ArrayList) (obj1)).size()]);
        sAllCodecInfos = (MediaCodecInfo[])arraylist.toArray(new MediaCodecInfo[arraylist.size()]);
        obj;
        JVM INSTR monitorexit ;
    }

    static final native boolean isEncoder(int i);

    private static final native int native_getCodecCount();

    static final native Map native_getGlobalSettings();

    private static final native void native_init();

    public final String findDecoderForFormat(MediaFormat mediaformat)
    {
        return findCodecForFormat(false, mediaformat);
    }

    public final String findEncoderForFormat(MediaFormat mediaformat)
    {
        return findCodecForFormat(true, mediaformat);
    }

    public final MediaCodecInfo[] getCodecInfos()
    {
        return (MediaCodecInfo[])Arrays.copyOf(mCodecInfos, mCodecInfos.length);
    }

    public static final int ALL_CODECS = 1;
    public static final int REGULAR_CODECS = 0;
    private static final String TAG = "MediaCodecList";
    private static MediaCodecInfo sAllCodecInfos[];
    private static Map sGlobalSettings;
    private static Object sInitLock = new Object();
    private static MediaCodecInfo sRegularCodecInfos[];
    private MediaCodecInfo mCodecInfos[];

    static 
    {
        System.loadLibrary("media_jni");
        native_init();
    }
}
