// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Bundle;
import android.util.Log;
import java.io.FileDescriptor;

public class MiuiAudioRecord extends IMiuiAudioRecord.Stub
{
    public final class AudioInfo
    {

        public long size;
        final MiuiAudioRecord this$0;
        public long timeUs;

        public AudioInfo()
        {
            this$0 = MiuiAudioRecord.this;
            super();
        }
    }

    public final class MetaData
    {

        public int channelCount;
        public int sampleRate;
        final MiuiAudioRecord this$0;

        public MetaData()
        {
            this$0 = MiuiAudioRecord.this;
            super();
        }
    }


    public MiuiAudioRecord(FileDescriptor filedescriptor, long l)
    {
        if(!native_setup(filedescriptor, l))
            Log.e(TAG, "init MiuiAudioRecord fail");
    }

    private final native boolean native_fillBuffer(AudioInfo audioinfo, int i, int j);

    private final native boolean native_getMetadata(MetaData metadata);

    private final native boolean native_setup(FileDescriptor filedescriptor, long l);

    private final native boolean native_start(long l);

    private final native boolean native_stop();

    public Bundle fillBuffer(int i, int j)
    {
        AudioInfo audioinfo = new AudioInfo();
        if(!native_fillBuffer(audioinfo, i, j))
        {
            Log.d(TAG, "fillBuffer fail!");
            return null;
        } else
        {
            Bundle bundle = new Bundle();
            bundle.putLong("presentationTimeUs", audioinfo.timeUs);
            bundle.putLong("size", audioinfo.size);
            return bundle;
        }
    }

    public Bundle getMetaData()
    {
        MetaData metadata = new MetaData();
        if(!native_getMetadata(metadata))
        {
            Log.d(TAG, "getMetaData fail!");
            return null;
        } else
        {
            Bundle bundle = new Bundle();
            bundle.putInt("sample-rate", metadata.sampleRate);
            bundle.putInt("channel-count", metadata.channelCount);
            return bundle;
        }
    }

    public boolean start(long l)
    {
        return native_start(l);
    }

    public boolean stop()
    {
        return native_stop();
    }

    private static String TAG = "MiuiAudioRecord";
    private long mNativeAudioRecordInJavaObj;

    static 
    {
        System.loadLibrary("exmedia");
    }
}
