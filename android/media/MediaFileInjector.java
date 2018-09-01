// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


// Referenced classes of package android.media:
//            MediaFile

class MediaFileInjector
{

    MediaFileInjector()
    {
    }

    static void addMiuiFileType()
    {
        MediaFile.addFileType("FLV", 2000, "video/x-flv");
        MediaFile.addFileType("RM", 2001, "video/x-pn-realvideo");
        MediaFile.addFileType("RMVB", 2002, "video/x-pn-realvideo");
        MediaFile.addFileType("MOV", 2003, "video/quicktime");
        MediaFile.addFileType("VOB", 2004, "video/x-ms-vob");
        MediaFile.addFileType("F4V", 2005, "video/mp4");
        MediaFile.addFileType("3G2B", 2006, "video/3gpp");
    }

    static boolean isMIUIVideoFileType(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 2000)
        {
            flag1 = flag;
            if(i <= 2006)
                flag1 = true;
        }
        return flag1;
    }

    public static final int FILE_TYPE_3G2B = 2006;
    public static final int FILE_TYPE_F4V = 2005;
    public static final int FILE_TYPE_FLV = 2000;
    public static final int FILE_TYPE_MOV = 2003;
    public static final int FILE_TYPE_RM = 2001;
    public static final int FILE_TYPE_RMVB = 2002;
    public static final int FILE_TYPE_VOB = 2004;
    private static final int FIRST_MIUI_VIDEO_FILE_TYPE = 2000;
    private static final int LAST_MIUI_VIDEO_FILE_TYPE = 2006;
    public static final int MIUI_FILE_TYPE_BASE = 2000;
}
