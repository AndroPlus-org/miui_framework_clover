// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import java.util.ArrayList;
import java.util.List;

public class DecoderCapabilities
{
    public static final class AudioDecoder extends Enum
    {

        public static AudioDecoder valueOf(String s)
        {
            return (AudioDecoder)Enum.valueOf(android/media/DecoderCapabilities$AudioDecoder, s);
        }

        public static AudioDecoder[] values()
        {
            return $VALUES;
        }

        private static final AudioDecoder $VALUES[];
        public static final AudioDecoder AUDIO_DECODER_WMA;

        static 
        {
            AUDIO_DECODER_WMA = new AudioDecoder("AUDIO_DECODER_WMA", 0);
            $VALUES = (new AudioDecoder[] {
                AUDIO_DECODER_WMA
            });
        }

        private AudioDecoder(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class VideoDecoder extends Enum
    {

        public static VideoDecoder valueOf(String s)
        {
            return (VideoDecoder)Enum.valueOf(android/media/DecoderCapabilities$VideoDecoder, s);
        }

        public static VideoDecoder[] values()
        {
            return $VALUES;
        }

        private static final VideoDecoder $VALUES[];
        public static final VideoDecoder VIDEO_DECODER_WMV;

        static 
        {
            VIDEO_DECODER_WMV = new VideoDecoder("VIDEO_DECODER_WMV", 0);
            $VALUES = (new VideoDecoder[] {
                VIDEO_DECODER_WMV
            });
        }

        private VideoDecoder(String s, int i)
        {
            super(s, i);
        }
    }


    private DecoderCapabilities()
    {
    }

    public static List getAudioDecoders()
    {
        ArrayList arraylist = new ArrayList();
        int i = native_get_num_audio_decoders();
        for(int j = 0; j < i; j++)
            arraylist.add(AudioDecoder.values()[native_get_audio_decoder_type(j)]);

        return arraylist;
    }

    public static List getVideoDecoders()
    {
        ArrayList arraylist = new ArrayList();
        int i = native_get_num_video_decoders();
        for(int j = 0; j < i; j++)
            arraylist.add(VideoDecoder.values()[native_get_video_decoder_type(j)]);

        return arraylist;
    }

    private static final native int native_get_audio_decoder_type(int i);

    private static final native int native_get_num_audio_decoders();

    private static final native int native_get_num_video_decoders();

    private static final native int native_get_video_decoder_type(int i);

    private static final native void native_init();

    static 
    {
        System.loadLibrary("media_jni");
        native_init();
    }
}
