// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.ContentResolver;
import android.graphics.*;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.FileInputStream;
import java.io.IOException;

// Referenced classes of package android.media:
//            MediaFile, ExifInterface, MediaMetadataRetriever

public class ThumbnailUtils
{
    private static class SizedThumbnailBitmap
    {

        public Bitmap mBitmap;
        public byte mThumbnailData[];
        public int mThumbnailHeight;
        public int mThumbnailWidth;

        private SizedThumbnailBitmap()
        {
        }

        SizedThumbnailBitmap(SizedThumbnailBitmap sizedthumbnailbitmap)
        {
            this();
        }
    }


    public ThumbnailUtils()
    {
    }

    private static void closeSilently(ParcelFileDescriptor parcelfiledescriptor)
    {
        if(parcelfiledescriptor == null)
            return;
        parcelfiledescriptor.close();
_L2:
        return;
        parcelfiledescriptor;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static int computeInitialSampleSize(android.graphics.BitmapFactory.Options options, int i, int j)
    {
        double d = options.outWidth;
        double d1 = options.outHeight;
        int k;
        int l;
        if(j == -1)
            k = 1;
        else
            k = (int)Math.ceil(Math.sqrt((d * d1) / (double)j));
        if(i == -1)
            l = 128;
        else
            l = (int)Math.min(Math.floor(d / (double)i), Math.floor(d1 / (double)i));
        if(l < k)
            return k;
        if(j == -1 && i == -1)
            return 1;
        if(i == -1)
            return k;
        else
            return l;
    }

    private static int computeSampleSize(android.graphics.BitmapFactory.Options options, int i, int j)
    {
        int k = computeInitialSampleSize(options, i, j);
        if(k <= 8)
        {
            i = 1;
            do
            {
                j = i;
                if(i >= k)
                    break;
                i <<= 1;
            } while(true);
        } else
        {
            j = ((k + 7) / 8) * 8;
        }
        return j;
    }

    public static Bitmap createImageThumbnail(String s, int i)
    {
        int j;
        char c;
        Object obj;
        Object obj1;
        Object obj2;
        Bitmap bitmap;
        StringBuilder stringbuilder;
label0:
        {
            Object obj3;
            int k;
            if(i == 1)
                j = 1;
            else
                j = 0;
            if(j != 0)
                c = '\u0140';
            else
                c = '`';
            if(j != 0)
                j = 0x30000;
            else
                j = 19200;
            obj = new SizedThumbnailBitmap(null);
            obj1 = null;
            obj2 = MediaFile.getFileType(s);
            bitmap = ((Bitmap) (obj1));
            if(obj2 == null)
                break label0;
            if(((MediaFile.MediaFileType) (obj2)).fileType != 31)
            {
                bitmap = ((Bitmap) (obj1));
                if(!MediaFile.isRawImageFileType(((MediaFile.MediaFileType) (obj2)).fileType))
                    break label0;
            }
            createThumbnailFromEXIF(s, c, j, ((SizedThumbnailBitmap) (obj)));
            bitmap = ((SizedThumbnailBitmap) (obj)).mBitmap;
        }
        obj1 = bitmap;
        if(bitmap != null)
            break MISSING_BLOCK_LABEL_286;
        obj2 = null;
        obj3 = null;
        stringbuilder = null;
        obj1 = obj3;
        obj = JVM INSTR new #105 <Class FileInputStream>;
        obj1 = obj3;
        ((FileInputStream) (obj)).FileInputStream(s);
        obj1 = ((FileInputStream) (obj)).getFD();
        obj2 = JVM INSTR new #46  <Class android.graphics.BitmapFactory$Options>;
        ((android.graphics.BitmapFactory.Options) (obj2)).android.graphics.BitmapFactory.Options();
        obj2.inSampleSize = 1;
        obj2.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(((java.io.FileDescriptor) (obj1)), null, ((android.graphics.BitmapFactory.Options) (obj2)));
        if(((android.graphics.BitmapFactory.Options) (obj2)).mCancel) goto _L2; else goto _L1
_L1:
        k = ((android.graphics.BitmapFactory.Options) (obj2)).outWidth;
        if(k != -1) goto _L3; else goto _L2
_L2:
        if(obj != null)
            try
            {
                ((FileInputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("ThumbnailUtils", "", s);
            }
        return null;
_L3:
        if(((android.graphics.BitmapFactory.Options) (obj2)).outHeight == -1) goto _L2; else goto _L4
_L4:
        obj2.inSampleSize = computeSampleSize(((android.graphics.BitmapFactory.Options) (obj2)), c, j);
        obj2.inJustDecodeBounds = false;
        obj2.inDither = false;
        obj2.inPreferredConfig = android.graphics.Bitmap.Config.ARGB_8888;
        obj1 = BitmapFactory.decodeFileDescriptor(((java.io.FileDescriptor) (obj1)), null, ((android.graphics.BitmapFactory.Options) (obj2)));
        s = ((String) (obj1));
        obj1 = s;
        if(obj == null)
            break MISSING_BLOCK_LABEL_286;
        ((FileInputStream) (obj)).close();
        obj1 = s;
_L5:
        s = ((String) (obj1));
        if(i == 3)
            s = extractThumbnail(((Bitmap) (obj1)), 96, 96, 2);
        return s;
        obj1;
        Log.e("ThumbnailUtils", "", ((Throwable) (obj1)));
        obj1 = s;
          goto _L5
        obj2;
        obj = stringbuilder;
_L11:
        obj1 = obj;
        stringbuilder = JVM INSTR new #158 <Class StringBuilder>;
        obj1 = obj;
        stringbuilder.StringBuilder();
        obj1 = obj;
        Log.e("ThumbnailUtils", stringbuilder.append("Unable to decode file ").append(s).append(". OutOfMemoryError.").toString(), ((Throwable) (obj2)));
        obj1 = bitmap;
        if(obj == null) goto _L5; else goto _L6
_L6:
        ((FileInputStream) (obj)).close();
        obj1 = bitmap;
          goto _L5
        s;
        Log.e("ThumbnailUtils", "", s);
        obj1 = bitmap;
          goto _L5
        obj;
        s = ((String) (obj2));
_L10:
        obj1 = s;
        Log.e("ThumbnailUtils", "", ((Throwable) (obj)));
        obj1 = bitmap;
        if(s == null) goto _L5; else goto _L7
_L7:
        s.close();
        obj1 = bitmap;
          goto _L5
        s;
        Log.e("ThumbnailUtils", "", s);
        obj1 = bitmap;
          goto _L5
        s;
_L9:
        if(obj1 != null)
            try
            {
                ((FileInputStream) (obj1)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj1)
            {
                Log.e("ThumbnailUtils", "", ((Throwable) (obj1)));
            }
        throw s;
        s;
        obj1 = obj;
        if(true) goto _L9; else goto _L8
_L8:
        IOException ioexception;
        ioexception;
        s = ((String) (obj));
        obj = ioexception;
          goto _L10
        obj2;
          goto _L11
    }

    private static void createThumbnailFromEXIF(String s, int i, int j, SizedThumbnailBitmap sizedthumbnailbitmap)
    {
        android.graphics.BitmapFactory.Options options;
        if(s == null)
            return;
        options = null;
        Object obj;
        obj = JVM INSTR new #173 <Class ExifInterface>;
        ((ExifInterface) (obj)).ExifInterface(s);
        obj = ((ExifInterface) (obj)).getThumbnail();
_L1:
        android.graphics.BitmapFactory.Options options1 = new android.graphics.BitmapFactory.Options();
        options = new android.graphics.BitmapFactory.Options();
        int k = 0;
        if(obj != null)
        {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(((byte []) (obj)), 0, obj.length, options);
            options.inSampleSize = computeSampleSize(options, i, j);
            k = options.outWidth / options.inSampleSize;
        }
        options1.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(s, options1);
        options1.inSampleSize = computeSampleSize(options1, i, j);
        i = options1.outWidth / options1.inSampleSize;
        IOException ioexception;
        if(obj != null && k >= i)
        {
            j = options.outWidth;
            i = options.outHeight;
            options.inJustDecodeBounds = false;
            sizedthumbnailbitmap.mBitmap = BitmapFactory.decodeByteArray(((byte []) (obj)), 0, obj.length, options);
            if(sizedthumbnailbitmap.mBitmap != null)
            {
                sizedthumbnailbitmap.mThumbnailData = ((byte []) (obj));
                sizedthumbnailbitmap.mThumbnailWidth = j;
                sizedthumbnailbitmap.mThumbnailHeight = i;
            }
        } else
        {
            options1.inJustDecodeBounds = false;
            sizedthumbnailbitmap.mBitmap = BitmapFactory.decodeFile(s, options1);
        }
        return;
        ioexception;
_L2:
        Log.w("ThumbnailUtils", ioexception);
        ioexception = options;
          goto _L1
        ioexception;
          goto _L2
    }

    public static Bitmap createVideoThumbnail(String s, int i)
    {
        Object obj;
        MediaMetadataRetriever mediametadataretriever;
        obj = null;
        mediametadataretriever = new MediaMetadataRetriever();
        mediametadataretriever.setDataSource(s);
        s = mediametadataretriever.getFrameAtTime(-1L);
        obj = s;
        try
        {
            mediametadataretriever.release();
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
_L2:
        if(obj == null)
            return null;
        break; /* Loop/switch isn't completed */
        s;
        try
        {
            mediametadataretriever.release();
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        continue; /* Loop/switch isn't completed */
        s;
        try
        {
            mediametadataretriever.release();
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        if(true) goto _L2; else goto _L1
        s;
        try
        {
            mediametadataretriever.release();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
        throw s;
_L1:
        if(i != 1) goto _L4; else goto _L3
_L3:
        int j = ((Bitmap) (obj)).getWidth();
        i = ((Bitmap) (obj)).getHeight();
        int k = Math.max(j, i);
        s = ((String) (obj));
        if(k > 512)
        {
            float f = 512F / (float)k;
            s = Bitmap.createScaledBitmap(((Bitmap) (obj)), Math.round((float)j * f), Math.round((float)i * f), true);
        }
_L6:
        return s;
_L4:
        s = ((String) (obj));
        if(i == 3)
            s = extractThumbnail(((Bitmap) (obj)), 96, 96, 2);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static Bitmap extractThumbnail(Bitmap bitmap, int i, int j)
    {
        return extractThumbnail(bitmap, i, j, 0);
    }

    public static Bitmap extractThumbnail(Bitmap bitmap, int i, int j, int k)
    {
        if(bitmap == null)
            return null;
        float f;
        Matrix matrix;
        if(bitmap.getWidth() < bitmap.getHeight())
            f = (float)i / (float)bitmap.getWidth();
        else
            f = (float)j / (float)bitmap.getHeight();
        matrix = new Matrix();
        matrix.setScale(f, f);
        return transform(matrix, bitmap, i, j, k | 1);
    }

    private static Bitmap makeBitmap(int i, int j, Uri uri, ContentResolver contentresolver, ParcelFileDescriptor parcelfiledescriptor, android.graphics.BitmapFactory.Options options)
    {
        ParcelFileDescriptor parcelfiledescriptor1;
        ParcelFileDescriptor parcelfiledescriptor2;
        parcelfiledescriptor1 = parcelfiledescriptor;
        if(parcelfiledescriptor != null)
            break MISSING_BLOCK_LABEL_20;
        parcelfiledescriptor2 = parcelfiledescriptor;
        parcelfiledescriptor1 = makeInputStream(uri, contentresolver);
        if(parcelfiledescriptor1 == null)
        {
            closeSilently(parcelfiledescriptor1);
            return null;
        }
        uri = options;
        if(options != null)
            break MISSING_BLOCK_LABEL_64;
        parcelfiledescriptor2 = parcelfiledescriptor1;
        parcelfiledescriptor = parcelfiledescriptor1;
        uri = JVM INSTR new #46  <Class android.graphics.BitmapFactory$Options>;
        parcelfiledescriptor2 = parcelfiledescriptor1;
        parcelfiledescriptor = parcelfiledescriptor1;
        uri.android.graphics.BitmapFactory.Options();
        parcelfiledescriptor2 = parcelfiledescriptor1;
        parcelfiledescriptor = parcelfiledescriptor1;
        contentresolver = parcelfiledescriptor1.getFileDescriptor();
        parcelfiledescriptor2 = parcelfiledescriptor1;
        parcelfiledescriptor = parcelfiledescriptor1;
        uri.inSampleSize = 1;
        parcelfiledescriptor2 = parcelfiledescriptor1;
        parcelfiledescriptor = parcelfiledescriptor1;
        uri.inJustDecodeBounds = true;
        parcelfiledescriptor2 = parcelfiledescriptor1;
        parcelfiledescriptor = parcelfiledescriptor1;
        BitmapFactory.decodeFileDescriptor(contentresolver, null, uri);
        parcelfiledescriptor2 = parcelfiledescriptor1;
        parcelfiledescriptor = parcelfiledescriptor1;
        if(((android.graphics.BitmapFactory.Options) (uri)).mCancel) goto _L2; else goto _L1
_L1:
        parcelfiledescriptor2 = parcelfiledescriptor1;
        parcelfiledescriptor = parcelfiledescriptor1;
        int k = ((android.graphics.BitmapFactory.Options) (uri)).outWidth;
        if(k != -1) goto _L3; else goto _L2
_L2:
        closeSilently(parcelfiledescriptor1);
        return null;
_L3:
        parcelfiledescriptor2 = parcelfiledescriptor1;
        parcelfiledescriptor = parcelfiledescriptor1;
        if(((android.graphics.BitmapFactory.Options) (uri)).outHeight == -1) goto _L2; else goto _L4
_L4:
        parcelfiledescriptor2 = parcelfiledescriptor1;
        parcelfiledescriptor = parcelfiledescriptor1;
        uri.inSampleSize = computeSampleSize(uri, i, j);
        parcelfiledescriptor2 = parcelfiledescriptor1;
        parcelfiledescriptor = parcelfiledescriptor1;
        uri.inJustDecodeBounds = false;
        parcelfiledescriptor2 = parcelfiledescriptor1;
        parcelfiledescriptor = parcelfiledescriptor1;
        uri.inDither = false;
        parcelfiledescriptor2 = parcelfiledescriptor1;
        parcelfiledescriptor = parcelfiledescriptor1;
        uri.inPreferredConfig = android.graphics.Bitmap.Config.ARGB_8888;
        parcelfiledescriptor2 = parcelfiledescriptor1;
        parcelfiledescriptor = parcelfiledescriptor1;
        uri = BitmapFactory.decodeFileDescriptor(contentresolver, null, uri);
        closeSilently(parcelfiledescriptor1);
        return uri;
        uri;
        parcelfiledescriptor = parcelfiledescriptor2;
        Log.e("ThumbnailUtils", "Got oom exception ", uri);
        closeSilently(parcelfiledescriptor2);
        return null;
        uri;
        closeSilently(parcelfiledescriptor);
        throw uri;
    }

    private static ParcelFileDescriptor makeInputStream(Uri uri, ContentResolver contentresolver)
    {
        try
        {
            uri = contentresolver.openFileDescriptor(uri, "r");
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            return null;
        }
        return uri;
    }

    private static Bitmap transform(Matrix matrix, Bitmap bitmap, int i, int j, int k)
    {
        int l;
        int j1;
        int k1;
        if((k & 1) != 0)
            l = 1;
        else
            l = 0;
        if((k & 2) != 0)
            k = 1;
        else
            k = 0;
        j1 = bitmap.getWidth() - i;
        k1 = bitmap.getHeight() - j;
        if(l == 0 && (j1 < 0 || k1 < 0))
        {
            matrix = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(matrix);
            l = Math.max(0, j1 / 2);
            k1 = Math.max(0, k1 / 2);
            Rect rect = new Rect(l, k1, Math.min(i, bitmap.getWidth()) + l, Math.min(j, bitmap.getHeight()) + k1);
            l = (i - rect.width()) / 2;
            k1 = (j - rect.height()) / 2;
            canvas.drawBitmap(bitmap, rect, new Rect(l, k1, i - l, j - k1), null);
            if(k != 0)
                bitmap.recycle();
            canvas.setBitmap(null);
            return matrix;
        }
        float f = bitmap.getWidth();
        float f1 = bitmap.getHeight();
        if(f / f1 > (float)i / (float)j)
        {
            f = (float)j / f1;
            int i1;
            int l1;
            Bitmap bitmap1;
            if(f < 0.9F || f > 1.0F)
                matrix.setScale(f, f);
            else
                matrix = null;
        } else
        {
            f = (float)i / f;
            if(f < 0.9F || f > 1.0F)
                matrix.setScale(f, f);
            else
                matrix = null;
        }
        if(matrix != null)
            matrix = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        else
            matrix = bitmap;
        if(k != 0 && matrix != bitmap)
            bitmap.recycle();
        i1 = Math.max(0, matrix.getWidth() - i);
        l1 = Math.max(0, matrix.getHeight() - j);
        bitmap1 = Bitmap.createBitmap(matrix, i1 / 2, l1 / 2, i, j);
        if(bitmap1 != matrix && (k != 0 || matrix != bitmap))
            matrix.recycle();
        return bitmap1;
    }

    private static final int MAX_NUM_PIXELS_MICRO_THUMBNAIL = 19200;
    private static final int MAX_NUM_PIXELS_THUMBNAIL = 0x30000;
    private static final int OPTIONS_NONE = 0;
    public static final int OPTIONS_RECYCLE_INPUT = 2;
    private static final int OPTIONS_SCALE_UP = 1;
    private static final String TAG = "ThumbnailUtils";
    public static final int TARGET_SIZE_MICRO_THUMBNAIL = 96;
    public static final int TARGET_SIZE_MINI_THUMBNAIL = 320;
    private static final int UNCONSTRAINED = -1;
}
