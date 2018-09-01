// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import dalvik.system.CloseGuard;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package android.media:
//            MediaFormat

public final class MediaMuxer
{
    public static final class OutputFormat
    {

        public static final int MUXER_OUTPUT_3GPP = 2;
        public static final int MUXER_OUTPUT_MPEG_4 = 0;
        public static final int MUXER_OUTPUT_WEBM = 1;

        private OutputFormat()
        {
        }
    }


    public MediaMuxer(FileDescriptor filedescriptor, int i)
        throws IOException
    {
        mState = -1;
        mCloseGuard = CloseGuard.get();
        mLastTrackIndex = -1;
        setUpMediaMuxer(filedescriptor, i);
    }

    public MediaMuxer(String s, int i)
        throws IOException
    {
        RandomAccessFile randomaccessfile;
        mState = -1;
        mCloseGuard = CloseGuard.get();
        mLastTrackIndex = -1;
        if(s == null)
            throw new IllegalArgumentException("path must not be null");
        randomaccessfile = null;
        RandomAccessFile randomaccessfile1;
        randomaccessfile1 = JVM INSTR new #64  <Class RandomAccessFile>;
        randomaccessfile1.RandomAccessFile(s, "rws");
        setUpMediaMuxer(randomaccessfile1.getFD(), i);
        if(randomaccessfile1 != null)
            randomaccessfile1.close();
        return;
        s;
_L2:
        if(randomaccessfile != null)
            randomaccessfile.close();
        throw s;
        s;
        randomaccessfile = randomaccessfile1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static native int nativeAddTrack(long l, String as[], Object aobj[]);

    private static native void nativeRelease(long l);

    private static native void nativeSetLocation(long l, int i, int j);

    private static native void nativeSetOrientationHint(long l, int i);

    private static native long nativeSetup(FileDescriptor filedescriptor, int i)
        throws IllegalArgumentException, IOException;

    private static native void nativeStart(long l);

    private static native void nativeStop(long l);

    private static native void nativeWriteSampleData(long l, int i, ByteBuffer bytebuffer, int j, int k, long l1, 
            int i1);

    private void setUpMediaMuxer(FileDescriptor filedescriptor, int i)
        throws IOException
    {
        if(i != 0 && i != 1 && i != 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("format: ").append(i).append(" is invalid").toString());
        } else
        {
            mNativeObject = nativeSetup(filedescriptor, i);
            mState = 0;
            mCloseGuard.open("release");
            return;
        }
    }

    public int addTrack(MediaFormat mediaformat)
    {
        if(mediaformat == null)
            throw new IllegalArgumentException("format must not be null.");
        if(mState != 0)
            throw new IllegalStateException("Muxer is not initialized.");
        if(mNativeObject == 0L)
            throw new IllegalStateException("Muxer has been released!");
        Map map = mediaformat.getMap();
        int i = map.size();
        if(i > 0)
        {
            String as[] = new String[i];
            mediaformat = ((MediaFormat) (new Object[i]));
            i = 0;
            for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                as[i] = (String)entry.getKey();
                mediaformat[i] = entry.getValue();
                i++;
            }

            i = nativeAddTrack(mNativeObject, as, mediaformat);
            if(mLastTrackIndex >= i)
            {
                throw new IllegalArgumentException("Invalid format.");
            } else
            {
                mLastTrackIndex = i;
                return i;
            }
        } else
        {
            throw new IllegalArgumentException("format must not be empty.");
        }
    }

    protected void finalize()
        throws Throwable
    {
        if(mCloseGuard != null)
            mCloseGuard.warnIfOpen();
        if(mNativeObject != 0L)
        {
            nativeRelease(mNativeObject);
            mNativeObject = 0L;
        }
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public void release()
    {
        if(mState == 1)
            stop();
        if(mNativeObject != 0L)
        {
            nativeRelease(mNativeObject);
            mNativeObject = 0L;
            mCloseGuard.close();
        }
        mState = -1;
    }

    public void setLocation(float f, float f1)
    {
        int i = (int)((double)(f * 10000F) + 0.5D);
        int j = (int)((double)(f1 * 10000F) + 0.5D);
        if(i > 0xdbba0 || i < 0xfff24460)
            throw new IllegalArgumentException((new StringBuilder()).append("Latitude: ").append(f).append(" out of range.").toString());
        if(j > 0x1b7740 || j < 0xffe488c0)
            throw new IllegalArgumentException((new StringBuilder()).append("Longitude: ").append(f1).append(" out of range").toString());
        if(mState == 0 && mNativeObject != 0L)
        {
            nativeSetLocation(mNativeObject, i, j);
            return;
        } else
        {
            throw new IllegalStateException("Can't set location due to wrong state.");
        }
    }

    public void setOrientationHint(int i)
    {
        if(i != 0 && i != 90 && i != 180 && i != 270)
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported angle: ").append(i).toString());
        if(mState == 0)
        {
            nativeSetOrientationHint(mNativeObject, i);
            return;
        } else
        {
            throw new IllegalStateException("Can't set rotation degrees due to wrong state.");
        }
    }

    public void start()
    {
        if(mNativeObject == 0L)
            throw new IllegalStateException("Muxer has been released!");
        if(mState == 0)
        {
            nativeStart(mNativeObject);
            mState = 1;
            return;
        } else
        {
            throw new IllegalStateException("Can't start due to wrong state.");
        }
    }

    public void stop()
    {
        if(mState == 1)
        {
            nativeStop(mNativeObject);
            mState = 2;
            return;
        } else
        {
            throw new IllegalStateException("Can't stop due to wrong state.");
        }
    }

    public void writeSampleData(int i, ByteBuffer bytebuffer, MediaCodec.BufferInfo bufferinfo)
    {
        if(i < 0 || i > mLastTrackIndex)
            throw new IllegalArgumentException("trackIndex is invalid");
        if(bytebuffer == null)
            throw new IllegalArgumentException("byteBuffer must not be null");
        if(bufferinfo == null)
            throw new IllegalArgumentException("bufferInfo must not be null");
        while(bufferinfo.size < 0 || bufferinfo.offset < 0 || bufferinfo.offset + bufferinfo.size > bytebuffer.capacity() || bufferinfo.presentationTimeUs < 0L) 
            throw new IllegalArgumentException("bufferInfo must specify a valid buffer offset, size and presentation time");
        if(mNativeObject == 0L)
            throw new IllegalStateException("Muxer has been released!");
        if(mState != 1)
        {
            throw new IllegalStateException("Can't write, muxer is not started");
        } else
        {
            nativeWriteSampleData(mNativeObject, i, bytebuffer, bufferinfo.offset, bufferinfo.size, bufferinfo.presentationTimeUs, bufferinfo.flags);
            return;
        }
    }

    private static final int MUXER_STATE_INITIALIZED = 0;
    private static final int MUXER_STATE_STARTED = 1;
    private static final int MUXER_STATE_STOPPED = 2;
    private static final int MUXER_STATE_UNINITIALIZED = -1;
    private final CloseGuard mCloseGuard;
    private int mLastTrackIndex;
    private long mNativeObject;
    private int mState;

    static 
    {
        System.loadLibrary("media_jni");
    }
}
