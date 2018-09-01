// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.*;
import com.android.internal.util.Preconditions;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

// Referenced classes of package android.media:
//            MediaCas, MediaFormat, MediaHTTPService, DrmInitData, 
//            MediaDataSource

public final class MediaExtractor
{
    public static final class CasInfo
    {

        public MediaCas.Session getSession()
        {
            return mSession;
        }

        public int getSystemId()
        {
            return mSystemId;
        }

        private final MediaCas.Session mSession;
        private final int mSystemId;

        CasInfo(int i, MediaCas.Session session)
        {
            mSystemId = i;
            mSession = session;
        }
    }

    public static final class MetricsConstants
    {

        public static final String FORMAT = "android.media.mediaextractor.fmt";
        public static final String MIME_TYPE = "android.media.mediaextractor.mime";
        public static final String TRACKS = "android.media.mediaextractor.ntrk";

        private MetricsConstants()
        {
        }
    }


    public MediaExtractor()
    {
        native_setup();
    }

    private native Map getFileFormatNative();

    private native Map getTrackFormatNative(int i);

    private final native void nativeSetDataSource(IBinder ibinder, String s, String as[], String as1[])
        throws IOException;

    private final native void nativeSetMediaCas(IHwBinder ihwbinder);

    private final native void native_finalize();

    private native PersistableBundle native_getMetrics();

    private static final native void native_init();

    private final native void native_setup();

    private ArrayList toByteArray(byte abyte0[])
    {
        ArrayList arraylist = new ArrayList(abyte0.length);
        for(int i = 0; i < abyte0.length; i++)
            arraylist.add(i, Byte.valueOf(abyte0[i]));

        return arraylist;
    }

    public native boolean advance();

    protected void finalize()
    {
        native_finalize();
    }

    public native long getCachedDuration();

    public CasInfo getCasInfo(int i)
    {
        Map map = getTrackFormatNative(i);
        if(map.containsKey("ca-system-id"))
        {
            i = ((Integer)map.get("ca-system-id")).intValue();
            Object obj = null;
            Object obj1 = obj;
            if(mMediaCas != null)
            {
                obj1 = obj;
                if(map.containsKey("ca-session-id"))
                {
                    ByteBuffer bytebuffer = (ByteBuffer)map.get("ca-session-id");
                    bytebuffer.rewind();
                    obj1 = new byte[bytebuffer.remaining()];
                    bytebuffer.get(((byte []) (obj1)));
                    obj1 = mMediaCas.createFromSessionId(toByteArray(((byte []) (obj1))));
                }
            }
            return new CasInfo(i, ((MediaCas.Session) (obj1)));
        } else
        {
            return null;
        }
    }

    public DrmInitData getDrmInitData()
    {
        Map map = getFileFormatNative();
        if(map == null)
            return null;
        if(map.containsKey("pssh"))
        {
            Object obj1 = getPsshInfo();
            final HashMap initDataMap = new HashMap();
            java.util.Map.Entry entry;
            for(obj1 = ((Map) (obj1)).entrySet().iterator(); ((Iterator) (obj1)).hasNext(); initDataMap.put((UUID)entry.getKey(), new DrmInitData.SchemeInitData("cenc", (byte[])entry.getValue())))
                entry = (java.util.Map.Entry)((Iterator) (obj1)).next();

            return new DrmInitData() {

                public DrmInitData.SchemeInitData get(UUID uuid)
                {
                    return (DrmInitData.SchemeInitData)initDataMap.get(uuid);
                }

                final MediaExtractor this$0;
                final Map val$initDataMap;

            
            {
                this$0 = MediaExtractor.this;
                initDataMap = map;
                super();
            }
            }
;
        }
        int i = getTrackCount();
        for(int j = 0; j < i;)
        {
            Object obj = getTrackFormatNative(j);
            if(!((Map) (obj)).containsKey("crypto-key"))
            {
                j++;
            } else
            {
                obj = (ByteBuffer)((Map) (obj)).get("crypto-key");
                ((ByteBuffer) (obj)).rewind();
                final byte data[] = new byte[((ByteBuffer) (obj)).remaining()];
                ((ByteBuffer) (obj)).get(data);
                return new DrmInitData() {

                    public DrmInitData.SchemeInitData get(UUID uuid)
                    {
                        return new DrmInitData.SchemeInitData("webm", data);
                    }

                    final MediaExtractor this$0;
                    final byte val$data[];

            
            {
                this$0 = MediaExtractor.this;
                data = abyte0;
                super();
            }
                }
;
            }
        }

        return null;
    }

    public PersistableBundle getMetrics()
    {
        return native_getMetrics();
    }

    public Map getPsshInfo()
    {
        Object obj = null;
        Map map = getFileFormatNative();
        Map map1 = obj;
        if(map != null)
        {
            map1 = obj;
            if(map.containsKey("pssh"))
            {
                ByteBuffer bytebuffer = (ByteBuffer)map.get("pssh");
                bytebuffer.order(ByteOrder.nativeOrder());
                bytebuffer.rewind();
                map.remove("pssh");
                HashMap hashmap = new HashMap();
                do
                {
                    map1 = hashmap;
                    if(bytebuffer.remaining() <= 0)
                        break;
                    bytebuffer.order(ByteOrder.BIG_ENDIAN);
                    UUID uuid = new UUID(bytebuffer.getLong(), bytebuffer.getLong());
                    bytebuffer.order(ByteOrder.nativeOrder());
                    map1 = new byte[bytebuffer.getInt()];
                    bytebuffer.get(map1);
                    hashmap.put(uuid, map1);
                } while(true);
            }
        }
        return map1;
    }

    public native boolean getSampleCryptoInfo(MediaCodec.CryptoInfo cryptoinfo);

    public native int getSampleFlags();

    public native long getSampleTime();

    public native int getSampleTrackIndex();

    public final native int getTrackCount();

    public MediaFormat getTrackFormat(int i)
    {
        return new MediaFormat(getTrackFormatNative(i));
    }

    public native boolean hasCacheReachedEndOfStream();

    public native int readSampleData(ByteBuffer bytebuffer, int i);

    public final native void release();

    public native void seekTo(long l, int i);

    public native void selectTrack(int i);

    public final void setDataSource(Context context, Uri uri, Map map)
        throws IOException
    {
        Object obj;
        Context context1;
        Context context2;
        obj = uri.getScheme();
        if(obj == null || ((String) (obj)).equals("file"))
        {
            setDataSource(uri.getPath());
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
        if(context.getDeclaredLength() >= 0L) goto _L2; else goto _L1
_L1:
        obj = context;
        context1 = context;
        context2 = context;
        setDataSource(context.getFileDescriptor());
_L4:
        if(context != null)
            context.close();
        return;
_L2:
        obj = context;
        context1 = context;
        context2 = context;
        setDataSource(context.getFileDescriptor(), context.getStartOffset(), context.getDeclaredLength());
        if(true) goto _L4; else goto _L3
_L3:
        context;
        if(obj != null)
            ((AssetFileDescriptor) (obj)).close();
_L6:
        setDataSource(uri.toString(), map);
        return;
        context;
        if(context1 != null)
            context1.close();
        if(true) goto _L6; else goto _L5
_L5:
        context;
        if(context2 != null)
            context2.close();
        throw context;
    }

    public final void setDataSource(AssetFileDescriptor assetfiledescriptor)
        throws IOException, IllegalArgumentException, IllegalStateException
    {
        Preconditions.checkNotNull(assetfiledescriptor);
        if(assetfiledescriptor.getDeclaredLength() < 0L)
            setDataSource(assetfiledescriptor.getFileDescriptor());
        else
            setDataSource(assetfiledescriptor.getFileDescriptor(), assetfiledescriptor.getStartOffset(), assetfiledescriptor.getDeclaredLength());
    }

    public final native void setDataSource(MediaDataSource mediadatasource)
        throws IOException;

    public final void setDataSource(FileDescriptor filedescriptor)
        throws IOException
    {
        setDataSource(filedescriptor, 0L, 0x7ffffffffffffffL);
    }

    public final native void setDataSource(FileDescriptor filedescriptor, long l, long l1)
        throws IOException;

    public final void setDataSource(String s)
        throws IOException
    {
        nativeSetDataSource(MediaHTTPService.createHttpServiceBinderIfNecessary(s), s, null, null);
    }

    public final void setDataSource(String s, Map map)
        throws IOException
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
        nativeSetDataSource(MediaHTTPService.createHttpServiceBinderIfNecessary(s), s, as, as1);
    }

    public final void setMediaCas(MediaCas mediacas)
    {
        mMediaCas = mediacas;
        nativeSetMediaCas(mediacas.getBinder());
    }

    public native void unselectTrack(int i);

    public static final int SAMPLE_FLAG_ENCRYPTED = 2;
    public static final int SAMPLE_FLAG_PARTIAL_FRAME = 4;
    public static final int SAMPLE_FLAG_SYNC = 1;
    public static final int SEEK_TO_CLOSEST_SYNC = 2;
    public static final int SEEK_TO_NEXT_SYNC = 1;
    public static final int SEEK_TO_PREVIOUS_SYNC = 0;
    private MediaCas mMediaCas;
    private long mNativeContext;

    static 
    {
        System.loadLibrary("media_jni");
        native_init();
    }
}
