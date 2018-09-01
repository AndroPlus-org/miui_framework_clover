// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import com.android.internal.util.Preconditions;
import java.util.*;

// Referenced classes of package android.media:
//            MediaFileInjector, DecoderCapabilities

public class MediaFile
{
    public static class MediaFileType
    {

        public final int fileType;
        public final String mimeType;

        MediaFileType(int i, String s)
        {
            fileType = i;
            mimeType = s;
        }
    }


    public MediaFile()
    {
    }

    static void addFileType(String s, int i, String s1)
    {
        sFileTypeMap.put(s, new MediaFileType(i, s1));
        sMimeTypeMap.put(s1, Integer.valueOf(i));
    }

    private static void addFileType(String s, int i, String s1, int j, boolean flag)
    {
        addFileType(s, i, s1);
        sFileTypeToFormatMap.put(s, Integer.valueOf(j));
        sMimeTypeToFormatMap.put(s1, Integer.valueOf(j));
        if(flag)
        {
            Preconditions.checkArgument(sFormatToMimeTypeMap.containsKey(Integer.valueOf(j)) ^ true);
            sFormatToMimeTypeMap.put(Integer.valueOf(j), s1);
        }
    }

    public static String getFileTitle(String s)
    {
        int i = s.lastIndexOf('/');
        String s1 = s;
        if(i >= 0)
        {
            i++;
            s1 = s;
            if(i < s.length())
                s1 = s.substring(i);
        }
        i = s1.lastIndexOf('.');
        s = s1;
        if(i > 0)
            s = s1.substring(0, i);
        return s;
    }

    public static MediaFileType getFileType(String s)
    {
        int i = s.lastIndexOf('.');
        if(i < 0)
            return null;
        else
            return (MediaFileType)sFileTypeMap.get(s.substring(i + 1).toUpperCase(Locale.ROOT));
    }

    public static int getFileTypeForMimeType(String s)
    {
        s = (Integer)sMimeTypeMap.get(s);
        int i;
        if(s == null)
            i = 0;
        else
            i = s.intValue();
        return i;
    }

    public static int getFormatCode(String s, String s1)
    {
        if(s1 != null)
        {
            s1 = (Integer)sMimeTypeToFormatMap.get(s1);
            if(s1 != null)
                return s1.intValue();
        }
        int i = s.lastIndexOf('.');
        if(i > 0)
        {
            s = s.substring(i + 1).toUpperCase(Locale.ROOT);
            s = (Integer)sFileTypeToFormatMap.get(s);
            if(s != null)
                return s.intValue();
        }
        return 12288;
    }

    public static String getMimeTypeForFile(String s)
    {
        Object obj = null;
        s = getFileType(s);
        if(s == null)
            s = obj;
        else
            s = ((MediaFileType) (s)).mimeType;
        return s;
    }

    public static String getMimeTypeForFormatCode(int i)
    {
        return (String)sFormatToMimeTypeMap.get(Integer.valueOf(i));
    }

    public static boolean isAudioFileType(int i)
    {
        boolean flag = true;
        if(i < 1 || i > 10) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        if(i >= 11)
        {
            flag1 = flag;
            if(i <= 13)
                continue; /* Loop/switch isn't completed */
        }
        if(i >= 210)
        {
            flag1 = flag;
            if(i > 218)
                flag1 = false;
        } else
        {
            flag1 = false;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static boolean isDrmFileType(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 51)
        {
            flag1 = flag;
            if(i <= 51)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isImageFileType(int i)
    {
        boolean flag = true;
        if(i < 31 || i > 37) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(i >= 300)
        {
            if(i > 309)
                flag = false;
        } else
        {
            flag = false;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static boolean isMimeTypeMedia(String s)
    {
        int i = getFileTypeForMimeType(s);
        boolean flag;
        if(!isAudioFileType(i) && !isVideoFileType(i) && !isImageFileType(i))
            flag = isPlayListFileType(i);
        else
            flag = true;
        return flag;
    }

    public static boolean isPlayListFileType(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 41)
        {
            flag1 = flag;
            if(i <= 44)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isRawImageFileType(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 300)
        {
            flag1 = flag;
            if(i <= 309)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isVideoFileType(int i)
    {
        boolean flag;
        if(i >= 21 && i <= 30 || i >= 200 && i <= 203)
            flag = true;
        else
            flag = MediaFileInjector.isMIUIVideoFileType(i);
        return flag;
    }

    private static boolean isWMAEnabled()
    {
        List list = DecoderCapabilities.getAudioDecoders();
        int i = list.size();
        for(int j = 0; j < i; j++)
            if((DecoderCapabilities.AudioDecoder)list.get(j) == DecoderCapabilities.AudioDecoder.AUDIO_DECODER_WMA)
                return true;

        return false;
    }

    private static boolean isWMVEnabled()
    {
        List list = DecoderCapabilities.getVideoDecoders();
        int i = list.size();
        for(int j = 0; j < i; j++)
            if((DecoderCapabilities.VideoDecoder)list.get(j) == DecoderCapabilities.VideoDecoder.VIDEO_DECODER_WMV)
                return true;

        return false;
    }

    public static final int FILE_TYPE_3GPA = 211;
    public static final int FILE_TYPE_3GPP = 23;
    public static final int FILE_TYPE_3GPP2 = 24;
    public static final int FILE_TYPE_AAC = 8;
    public static final int FILE_TYPE_AC3 = 212;
    public static final int FILE_TYPE_AIFF = 216;
    public static final int FILE_TYPE_AMR = 4;
    public static final int FILE_TYPE_APE = 217;
    public static final int FILE_TYPE_ARW = 304;
    public static final int FILE_TYPE_ASF = 26;
    public static final int FILE_TYPE_AVI = 29;
    public static final int FILE_TYPE_AWB = 5;
    public static final int FILE_TYPE_BMP = 34;
    public static final int FILE_TYPE_CR2 = 301;
    public static final int FILE_TYPE_DIVX = 202;
    public static final int FILE_TYPE_DNG = 300;
    public static final int FILE_TYPE_DSD = 218;
    public static final int FILE_TYPE_DTS = 210;
    public static final int FILE_TYPE_EC3 = 215;
    public static final int FILE_TYPE_FL = 51;
    public static final int FILE_TYPE_FLAC = 10;
    public static final int FILE_TYPE_FLV = 203;
    public static final int FILE_TYPE_GIF = 32;
    public static final int FILE_TYPE_HEIF = 37;
    public static final int FILE_TYPE_HTML = 101;
    public static final int FILE_TYPE_HTTPLIVE = 44;
    public static final int FILE_TYPE_IMY = 13;
    public static final int FILE_TYPE_JPEG = 31;
    public static final int FILE_TYPE_M3U = 41;
    public static final int FILE_TYPE_M4A = 2;
    public static final int FILE_TYPE_M4V = 22;
    public static final int FILE_TYPE_MID = 11;
    public static final int FILE_TYPE_MKA = 9;
    public static final int FILE_TYPE_MKV = 27;
    public static final int FILE_TYPE_MP2PS = 200;
    public static final int FILE_TYPE_MP2TS = 28;
    public static final int FILE_TYPE_MP3 = 1;
    public static final int FILE_TYPE_MP4 = 21;
    public static final int FILE_TYPE_MS_EXCEL = 105;
    public static final int FILE_TYPE_MS_POWERPOINT = 106;
    public static final int FILE_TYPE_MS_WORD = 104;
    public static final int FILE_TYPE_NEF = 302;
    public static final int FILE_TYPE_NRW = 303;
    public static final int FILE_TYPE_OGG = 7;
    public static final int FILE_TYPE_ORF = 306;
    public static final int FILE_TYPE_PCM = 214;
    public static final int FILE_TYPE_PDF = 102;
    public static final int FILE_TYPE_PEF = 308;
    public static final int FILE_TYPE_PLS = 42;
    public static final int FILE_TYPE_PNG = 33;
    public static final int FILE_TYPE_QCP = 213;
    public static final int FILE_TYPE_QT = 201;
    public static final int FILE_TYPE_RAF = 307;
    public static final int FILE_TYPE_RW2 = 305;
    public static final int FILE_TYPE_SMF = 12;
    public static final int FILE_TYPE_SRW = 309;
    public static final int FILE_TYPE_TEXT = 100;
    public static final int FILE_TYPE_WAV = 3;
    public static final int FILE_TYPE_WBMP = 35;
    public static final int FILE_TYPE_WEBM = 30;
    public static final int FILE_TYPE_WEBP = 36;
    public static final int FILE_TYPE_WMA = 6;
    public static final int FILE_TYPE_WMV = 25;
    public static final int FILE_TYPE_WPL = 43;
    public static final int FILE_TYPE_XML = 103;
    public static final int FILE_TYPE_ZIP = 107;
    private static final int FIRST_AUDIO_FILE_TYPE = 1;
    private static final int FIRST_AUDIO_FILE_TYPE_EXT = 210;
    private static final int FIRST_DRM_FILE_TYPE = 51;
    private static final int FIRST_IMAGE_FILE_TYPE = 31;
    private static final int FIRST_MIDI_FILE_TYPE = 11;
    private static final int FIRST_PLAYLIST_FILE_TYPE = 41;
    private static final int FIRST_RAW_IMAGE_FILE_TYPE = 300;
    private static final int FIRST_VIDEO_FILE_TYPE = 21;
    private static final int FIRST_VIDEO_FILE_TYPE2 = 200;
    private static final int LAST_AUDIO_FILE_TYPE = 10;
    private static final int LAST_AUDIO_FILE_TYPE_EXT = 218;
    private static final int LAST_DRM_FILE_TYPE = 51;
    private static final int LAST_IMAGE_FILE_TYPE = 37;
    private static final int LAST_MIDI_FILE_TYPE = 13;
    private static final int LAST_PLAYLIST_FILE_TYPE = 44;
    private static final int LAST_RAW_IMAGE_FILE_TYPE = 309;
    private static final int LAST_VIDEO_FILE_TYPE = 30;
    private static final int LAST_VIDEO_FILE_TYPE2 = 203;
    private static final HashMap sFileTypeMap = new HashMap();
    private static final HashMap sFileTypeToFormatMap = new HashMap();
    private static final HashMap sFormatToMimeTypeMap = new HashMap();
    private static final HashMap sMimeTypeMap = new HashMap();
    private static final HashMap sMimeTypeToFormatMap = new HashMap();

    static 
    {
        addFileType("MP3", 1, "audio/mpeg", 12297, true);
        addFileType("MPGA", 1, "audio/mpeg", 12297, false);
        addFileType("M4A", 2, "audio/mp4", 12299, false);
        addFileType("WAV", 3, "audio/x-wav", 12296, true);
        addFileType("AMR", 4, "audio/amr");
        addFileType("AWB", 5, "audio/amr-wb");
        if(isWMAEnabled())
            addFileType("WMA", 6, "audio/x-ms-wma", 47361, true);
        addFileType("OGG", 7, "audio/ogg", 47362, false);
        addFileType("OGG", 7, "application/ogg", 47362, true);
        addFileType("OGA", 7, "application/ogg", 47362, false);
        addFileType("AAC", 8, "audio/aac", 47363, true);
        addFileType("AAC", 8, "audio/aac-adts", 47363, false);
        addFileType("MKA", 9, "audio/x-matroska");
        addFileType("MID", 11, "audio/midi");
        addFileType("MIDI", 11, "audio/midi");
        addFileType("XMF", 11, "audio/midi");
        addFileType("RTTTL", 11, "audio/midi");
        addFileType("SMF", 12, "audio/sp-midi");
        addFileType("IMY", 13, "audio/imelody");
        addFileType("RTX", 11, "audio/midi");
        addFileType("OTA", 11, "audio/midi");
        addFileType("MXMF", 11, "audio/midi");
        addFileType("MPEG", 21, "video/mpeg", 12299, true);
        addFileType("MPG", 21, "video/mpeg", 12299, false);
        addFileType("MP4", 21, "video/mp4", 12299, false);
        addFileType("M4V", 22, "video/mp4", 12299, false);
        addFileType("MOV", 201, "video/quicktime", 12299, false);
        addFileType("3GP", 23, "video/3gpp", 47492, true);
        addFileType("3GPP", 23, "video/3gpp", 47492, false);
        addFileType("3G2", 24, "video/3gpp2", 47492, false);
        addFileType("3GPP2", 24, "video/3gpp2", 47492, false);
        addFileType("MKV", 27, "video/x-matroska");
        addFileType("WEBM", 30, "video/webm");
        addFileType("TS", 28, "video/mp2ts");
        addFileType("AVI", 29, "video/avi");
        if(isWMVEnabled())
        {
            addFileType("WMV", 25, "video/x-ms-wmv", 47489, true);
            addFileType("ASF", 26, "video/x-ms-asf");
        }
        addFileType("JPG", 31, "image/jpeg", 14337, true);
        addFileType("JPEG", 31, "image/jpeg", 14337, false);
        addFileType("GIF", 32, "image/gif", 14343, true);
        addFileType("PNG", 33, "image/png", 14347, true);
        addFileType("BMP", 34, "image/x-ms-bmp", 14340, true);
        addFileType("WBMP", 35, "image/vnd.wap.wbmp", 14336, false);
        addFileType("WEBP", 36, "image/webp", 14336, false);
        addFileType("HEIC", 37, "image/heif", 14354, true);
        addFileType("HEIF", 37, "image/heif", 14354, false);
        addFileType("DNG", 300, "image/x-adobe-dng", 14353, true);
        addFileType("CR2", 301, "image/x-canon-cr2", 14349, false);
        addFileType("NEF", 302, "image/x-nikon-nef", 14338, false);
        addFileType("NRW", 303, "image/x-nikon-nrw", 14349, false);
        addFileType("ARW", 304, "image/x-sony-arw", 14349, false);
        addFileType("RW2", 305, "image/x-panasonic-rw2", 14349, false);
        addFileType("ORF", 306, "image/x-olympus-orf", 14349, false);
        addFileType("RAF", 307, "image/x-fuji-raf", 14336, false);
        addFileType("PEF", 308, "image/x-pentax-pef", 14349, false);
        addFileType("SRW", 309, "image/x-samsung-srw", 14349, false);
        addFileType("M3U", 41, "audio/x-mpegurl", 47633, true);
        addFileType("M3U", 41, "application/x-mpegurl", 47633, false);
        addFileType("PLS", 42, "audio/x-scpls", 47636, true);
        addFileType("WPL", 43, "application/vnd.ms-wpl", 47632, true);
        addFileType("M3U8", 44, "application/vnd.apple.mpegurl");
        addFileType("M3U8", 44, "audio/mpegurl");
        addFileType("M3U8", 44, "audio/x-mpegurl");
        addFileType("FL", 51, "application/x-android-drm-fl");
        addFileType("TXT", 100, "text/plain", 12292, true);
        addFileType("HTM", 101, "text/html", 12293, true);
        addFileType("HTML", 101, "text/html", 12293, false);
        addFileType("PDF", 102, "application/pdf");
        addFileType("DOC", 104, "application/msword", 47747, true);
        addFileType("XLS", 105, "application/vnd.ms-excel", 47749, true);
        addFileType("PPT", 106, "application/mspowerpoint", 47750, true);
        addFileType("FLAC", 10, "audio/flac", 47366, true);
        addFileType("ZIP", 107, "application/zip");
        addFileType("MPG", 200, "video/mp2p");
        addFileType("MPEG", 200, "video/mp2p");
        addFileType("DIVX", 202, "video/divx");
        addFileType("FLV", 203, "video/flv");
        addFileType("QCP", 213, "audio/qcelp");
        addFileType("AC3", 212, "audio/ac3");
        addFileType("EC3", 215, "audio/eac3");
        addFileType("AIF", 216, "audio/x-aiff");
        addFileType("AIFF", 216, "audio/x-aiff");
        addFileType("APE", 217, "audio/x-ape");
        addFileType("DSF", 218, "audio/x-dsf");
        addFileType("DFF", 218, "audio/x-dff");
        addFileType("DSD", 218, "audio/dsd");
        MediaFileInjector.addMiuiFileType();
    }
}
