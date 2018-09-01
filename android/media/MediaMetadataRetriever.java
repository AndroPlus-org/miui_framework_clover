// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import java.io.*;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package android.media:
//            MediaHTTPService, MediaDataSource

public class MediaMetadataRetriever
{

    public MediaMetadataRetriever()
    {
        native_setup();
    }

    private native Bitmap _getFrameAtTime(long l, int i, int j, int k);

    private native void _setDataSource(MediaDataSource mediadatasource)
        throws IllegalArgumentException;

    private native void _setDataSource(IBinder ibinder, String s, String as[], String as1[])
        throws IllegalArgumentException;

    private native byte[] getEmbeddedPicture(int i);

    private final native void native_finalize();

    private static native void native_init();

    private native void native_setup();

    public native String extractMetadata(int i);

    protected void finalize()
        throws Throwable
    {
        native_finalize();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public byte[] getEmbeddedPicture()
    {
        return getEmbeddedPicture(65535);
    }

    public Bitmap getFrameAtTime()
    {
        return _getFrameAtTime(-1L, 2, -1, -1);
    }

    public Bitmap getFrameAtTime(long l)
    {
        return getFrameAtTime(l, 2);
    }

    public Bitmap getFrameAtTime(long l, int i)
    {
        if(i < 0 || i > 3)
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported option: ").append(i).toString());
        else
            return _getFrameAtTime(l, i, -1, -1);
    }

    public Bitmap getScaledFrameAtTime(long l, int i, int j, int k)
    {
        if(i < 0 || i > 3)
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported option: ").append(i).toString());
        if(j <= 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid width: ").append(j).toString());
        if(k <= 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid height: ").append(k).toString());
        else
            return _getFrameAtTime(l, i, j, k);
    }

    public native void release();

    public void setDataSource(Context context, Uri uri)
        throws IllegalArgumentException, SecurityException
    {
        Object obj;
        Object obj1;
        Object obj2;
        Context context1;
        if(uri == null)
            throw new IllegalArgumentException();
        obj = uri.getScheme();
        if(obj == null || ((String) (obj)).equals("file"))
        {
            setDataSource(uri.getPath());
            return;
        }
        obj1 = null;
        obj2 = null;
        obj = obj2;
        context1 = obj1;
        context = context.getContentResolver();
        obj = obj2;
        context1 = obj1;
        context = context.openAssetFileDescriptor(uri, "r");
        if(context != null)
            break MISSING_BLOCK_LABEL_167;
        obj = context;
        context1 = context;
        obj2 = JVM INSTR new #93  <Class IllegalArgumentException>;
        obj = context;
        context1 = context;
        ((IllegalArgumentException) (obj2)).IllegalArgumentException();
        obj = context;
        context1 = context;
        try
        {
            throw obj2;
        }
        // Misplaced declaration of an exception variable
        catch(Context context) { }
        if(obj != null)
            try
            {
                ((AssetFileDescriptor) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Context context) { }
        setDataSource(uri.toString());
        return;
        context;
        obj = obj2;
        context1 = obj1;
        context = JVM INSTR new #93  <Class IllegalArgumentException>;
        obj = obj2;
        context1 = obj1;
        context.IllegalArgumentException();
        obj = obj2;
        context1 = obj1;
        throw context;
        context;
        if(context1 != null)
            try
            {
                context1.close();
            }
            // Misplaced declaration of an exception variable
            catch(Uri uri) { }
        throw context;
        obj = context;
        context1 = context;
        obj2 = context.getFileDescriptor();
        obj = context;
        context1 = context;
        if(((FileDescriptor) (obj2)).valid())
            break MISSING_BLOCK_LABEL_219;
        obj = context;
        context1 = context;
        obj2 = JVM INSTR new #93  <Class IllegalArgumentException>;
        obj = context;
        context1 = context;
        ((IllegalArgumentException) (obj2)).IllegalArgumentException();
        obj = context;
        context1 = context;
        throw obj2;
        obj = context;
        context1 = context;
        if(context.getDeclaredLength() >= 0L) goto _L2; else goto _L1
_L1:
        obj = context;
        context1 = context;
        setDataSource(((FileDescriptor) (obj2)));
_L3:
        if(context == null)
            break MISSING_BLOCK_LABEL_252;
        context.close();
_L4:
        return;
_L2:
        obj = context;
        context1 = context;
        setDataSource(((FileDescriptor) (obj2)), context.getStartOffset(), context.getDeclaredLength());
          goto _L3
        context;
          goto _L4
    }

    public void setDataSource(MediaDataSource mediadatasource)
        throws IllegalArgumentException
    {
        _setDataSource(mediadatasource);
    }

    public void setDataSource(FileDescriptor filedescriptor)
        throws IllegalArgumentException
    {
        setDataSource(filedescriptor, 0L, 0x7ffffffffffffffL);
    }

    public native void setDataSource(FileDescriptor filedescriptor, long l, long l1)
        throws IllegalArgumentException;

    public void setDataSource(String s)
        throws IllegalArgumentException
    {
        Object obj;
        Object obj1;
        Object obj2;
        obj = null;
        if(s == null)
            throw new IllegalArgumentException();
        obj1 = null;
        obj2 = null;
        Object obj4;
        obj4 = JVM INSTR new #220 <Class FileInputStream>;
        ((FileInputStream) (obj4)).FileInputStream(s);
        setDataSource(((FileInputStream) (obj4)).getFD(), 0L, 0x7ffffffffffffffL);
        s = obj;
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_57;
        ((FileInputStream) (obj4)).close();
        s = obj;
_L3:
        if(s == null) goto _L2; else goto _L1
_L1:
        throw s;
        s;
_L6:
        throw new IllegalArgumentException();
        s;
          goto _L3
        obj4;
        s = ((String) (obj2));
_L12:
        throw obj4;
        Exception exception;
        exception;
        obj1 = s;
        s = ((String) (obj4));
        obj4 = exception;
_L11:
        exception = s;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_109;
        ((FileInputStream) (obj1)).close();
        exception = s;
_L7:
        if(exception == null) goto _L5; else goto _L4
_L4:
        throw exception;
        s;
          goto _L6
        obj1;
label0:
        {
            if(s != null)
                break label0;
            exception = ((Exception) (obj1));
        }
          goto _L7
        exception = s;
        if(s == obj1) goto _L7; else goto _L8
_L8:
        s.addSuppressed(((Throwable) (obj1)));
        exception = s;
          goto _L7
        s;
_L10:
        throw new IllegalArgumentException();
_L5:
        throw obj4;
_L2:
        return;
        s;
        if(true) goto _L10; else goto _L9
_L9:
        obj4;
        s = null;
          goto _L11
        s;
        obj1 = obj4;
        Object obj3 = null;
        obj4 = s;
        s = obj3;
          goto _L11
        Throwable throwable;
        throwable;
        s = ((String) (obj4));
        obj4 = throwable;
          goto _L12
    }

    public void setDataSource(String s, Map map)
        throws IllegalArgumentException
    {
        int i = 0;
        String as[] = new String[map.size()];
        String as1[] = new String[map.size()];
        for(map = map.entrySet().iterator(); map.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)map.next();
            as[i] = (String)entry.getKey();
            as1[i] = (String)entry.getValue();
            i++;
        }

        _setDataSource(MediaHTTPService.createHttpServiceBinderIfNecessary(s), s, as, as1);
    }

    private static final int EMBEDDED_PICTURE_TYPE_ANY = 65535;
    public static final int METADATA_KEY_ALBUM = 1;
    public static final int METADATA_KEY_ALBUMARTIST = 13;
    public static final int METADATA_KEY_ARTIST = 2;
    public static final int METADATA_KEY_AUTHOR = 3;
    public static final int METADATA_KEY_BITRATE = 20;
    public static final int METADATA_KEY_CAPTURE_FRAMERATE = 25;
    public static final int METADATA_KEY_CD_TRACK_NUMBER = 0;
    public static final int METADATA_KEY_COMPILATION = 15;
    public static final int METADATA_KEY_COMPOSER = 4;
    public static final int METADATA_KEY_DATE = 5;
    public static final int METADATA_KEY_DISC_NUMBER = 14;
    public static final int METADATA_KEY_DURATION = 9;
    public static final int METADATA_KEY_GENRE = 6;
    public static final int METADATA_KEY_HAS_AUDIO = 16;
    public static final int METADATA_KEY_HAS_VIDEO = 17;
    public static final int METADATA_KEY_IS_DRM = 22;
    public static final int METADATA_KEY_LOCATION = 23;
    public static final int METADATA_KEY_LYRIC = 1000;
    public static final int METADATA_KEY_MIMETYPE = 12;
    public static final int METADATA_KEY_NUM_TRACKS = 10;
    public static final int METADATA_KEY_TIMED_TEXT_LANGUAGES = 21;
    public static final int METADATA_KEY_TITLE = 7;
    public static final int METADATA_KEY_VIDEO_HEIGHT = 19;
    public static final int METADATA_KEY_VIDEO_ROTATION = 24;
    public static final int METADATA_KEY_VIDEO_WIDTH = 18;
    public static final int METADATA_KEY_WRITER = 11;
    public static final int METADATA_KEY_YEAR = 8;
    public static final int OPTION_CLOSEST = 3;
    public static final int OPTION_CLOSEST_SYNC = 2;
    public static final int OPTION_NEXT_SYNC = 1;
    public static final int OPTION_PREVIOUS_SYNC = 0;
    private long mNativeContext;

    static 
    {
        System.loadLibrary("media_jni");
        native_init();
    }
}
