// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.content.res.Resources;
import android.os.Trace;
import android.util.*;
import java.io.*;

// Referenced classes of package android.graphics:
//            Bitmap, NinePatch, Rect, ColorSpace

public class BitmapFactory
{
    public static class Options
    {

        static void validate(Options options)
        {
            if(options == null)
                return;
            if(options.inBitmap != null && options.inBitmap.getConfig() == Bitmap.Config.HARDWARE)
                throw new IllegalArgumentException("Bitmaps with Config.HARWARE are always immutable");
            if(options.inMutable && options.inPreferredConfig == Bitmap.Config.HARDWARE)
                throw new IllegalArgumentException("Bitmaps with Config.HARDWARE cannot be decoded into - they are immutable");
            if(options.inPreferredColorSpace != null)
            {
                if(!(options.inPreferredColorSpace instanceof ColorSpace.Rgb))
                    throw new IllegalArgumentException("The destination color space must use the RGB color model");
                if(((ColorSpace.Rgb)options.inPreferredColorSpace).getTransferParameters() == null)
                    throw new IllegalArgumentException("The destination color space must use an ICC parametric transfer function");
            }
        }

        public void requestCancelDecode()
        {
            mCancel = true;
        }

        public Bitmap inBitmap;
        public int inDensity;
        public boolean inDither;
        public boolean inInputShareable;
        public boolean inJustDecodeBounds;
        public boolean inMutable;
        public boolean inPreferQualityOverSpeed;
        public ColorSpace inPreferredColorSpace;
        public Bitmap.Config inPreferredConfig;
        public boolean inPremultiplied;
        public boolean inPurgeable;
        public int inSampleSize;
        public boolean inScaled;
        public int inScreenDensity;
        public int inTargetDensity;
        public byte inTempStorage[];
        public boolean mCancel;
        public ColorSpace outColorSpace;
        public Bitmap.Config outConfig;
        public int outHeight;
        public String outMimeType;
        public int outWidth;

        public Options()
        {
            inPreferredConfig = Bitmap.Config.ARGB_8888;
            inPreferredColorSpace = null;
            inScaled = true;
            inPremultiplied = true;
        }
    }


    public BitmapFactory()
    {
    }

    public static Bitmap decodeByteArray(byte abyte0[], int i, int j)
    {
        return decodeByteArray(abyte0, i, j, null);
    }

    public static Bitmap decodeByteArray(byte abyte0[], int i, int j, Options options)
    {
        if((i | j) < 0 || abyte0.length < i + j)
            throw new ArrayIndexOutOfBoundsException();
        Options.validate(options);
        Trace.traceBegin(2L, "decodeBitmap");
        abyte0 = nativeDecodeByteArray(abyte0, i, j, options);
        if(abyte0 != null || options == null)
            break MISSING_BLOCK_LABEL_78;
        if(options.inBitmap != null)
        {
            abyte0 = JVM INSTR new #46  <Class IllegalArgumentException>;
            abyte0.IllegalArgumentException("Problem decoding into existing bitmap");
            throw abyte0;
        }
        break MISSING_BLOCK_LABEL_78;
        abyte0;
        Trace.traceEnd(2L);
        throw abyte0;
        setDensityFromOptions(abyte0, options);
        Trace.traceEnd(2L);
        return abyte0;
    }

    public static Bitmap decodeFile(String s)
    {
        return decodeFile(s, null);
    }

    public static Bitmap decodeFile(String s, Options options)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Options.validate(options);
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = obj1;
        Object obj4 = JVM INSTR new #70  <Class FileInputStream>;
        obj3 = obj1;
        ((FileInputStream) (obj4)).FileInputStream(s);
        s = decodeStream(((InputStream) (obj4)), null, options);
        if(obj4 != null)
            try
            {
                ((InputStream) (obj4)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Options options) { }
        options = s;
_L2:
        return options;
        options;
        s = obj2;
_L5:
        obj3 = s;
        obj4 = JVM INSTR new #82  <Class StringBuilder>;
        obj3 = s;
        ((StringBuilder) (obj4)).StringBuilder();
        obj3 = s;
        Log.e("BitmapFactory", ((StringBuilder) (obj4)).append("Unable to decode stream: ").append(options).toString());
        options = obj;
        if(s == null) goto _L2; else goto _L1
_L1:
        s.close();
        options = obj;
          goto _L2
        s;
        options = obj;
          goto _L2
        s;
_L4:
        if(obj3 != null)
            try
            {
                ((InputStream) (obj3)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Options options) { }
        throw s;
        s;
        obj3 = obj4;
        if(true) goto _L4; else goto _L3
_L3:
        options;
        s = ((String) (obj4));
          goto _L5
    }

    public static Bitmap decodeFileDescriptor(FileDescriptor filedescriptor)
    {
        return decodeFileDescriptor(filedescriptor, null, null);
    }

    public static Bitmap decodeFileDescriptor(FileDescriptor filedescriptor, Rect rect, Options options)
    {
        Options.validate(options);
        Trace.traceBegin(2L, "decodeFileDescriptor");
        if(!nativeIsSeekable(filedescriptor)) goto _L2; else goto _L1
_L1:
        filedescriptor = nativeDecodeFileDescriptor(filedescriptor, rect, options);
_L4:
        if(filedescriptor != null || options == null)
            break; /* Loop/switch isn't completed */
        if(options.inBitmap != null)
        {
            filedescriptor = JVM INSTR new #46  <Class IllegalArgumentException>;
            filedescriptor.IllegalArgumentException("Problem decoding into existing bitmap");
            throw filedescriptor;
        }
        break; /* Loop/switch isn't completed */
        filedescriptor;
        Trace.traceEnd(2L);
        throw filedescriptor;
_L2:
        FileInputStream fileinputstream;
        fileinputstream = JVM INSTR new #70  <Class FileInputStream>;
        fileinputstream.FileInputStream(filedescriptor);
        filedescriptor = decodeStreamInternal(fileinputstream, rect, options);
        try
        {
            fileinputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch(Rect rect) { }
        if(true) goto _L4; else goto _L3
        filedescriptor;
        try
        {
            fileinputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch(Rect rect) { }
        throw filedescriptor;
_L3:
        setDensityFromOptions(filedescriptor, options);
        Trace.traceEnd(2L);
        return filedescriptor;
    }

    public static Bitmap decodeResource(Resources resources, int i)
    {
        return decodeResource(resources, i, null);
    }

    public static Bitmap decodeResource(Resources resources, int i, Options options)
    {
        Object obj;
        Object obj1;
        InputStream inputstream;
        Object obj2;
        InputStream inputstream1;
        Options.validate(options);
        obj = null;
        obj1 = null;
        inputstream = null;
        obj2 = inputstream;
        inputstream1 = obj1;
        TypedValue typedvalue = JVM INSTR new #133 <Class TypedValue>;
        obj2 = inputstream;
        inputstream1 = obj1;
        typedvalue.TypedValue();
        obj2 = inputstream;
        inputstream1 = obj1;
        inputstream = resources.openRawResource(i, typedvalue);
        obj2 = inputstream;
        inputstream1 = inputstream;
        resources = decodeResourceStream(resources, typedvalue, inputstream, null, options);
        obj2 = resources;
        resources = ((Resources) (obj2));
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_93;
        inputstream.close();
        resources = ((Resources) (obj2));
_L1:
        if(resources == null && options != null && options.inBitmap != null)
            throw new IllegalArgumentException("Problem decoding into existing bitmap");
        else
            return resources;
        resources;
        resources = ((Resources) (obj2));
          goto _L1
        resources;
        resources = obj;
        if(obj2 == null) goto _L1; else goto _L2
_L2:
        ((InputStream) (obj2)).close();
        resources = obj;
          goto _L1
        resources;
        resources = obj;
          goto _L1
        resources;
        if(inputstream1 != null)
            try
            {
                inputstream1.close();
            }
            // Misplaced declaration of an exception variable
            catch(Options options) { }
        throw resources;
    }

    public static Bitmap decodeResourceStream(Resources resources, TypedValue typedvalue, InputStream inputstream, Rect rect, Options options)
    {
        Options options1;
        Options.validate(options);
        options1 = options;
        if(options == null)
            options1 = new Options();
        if(options1.inDensity != 0 || typedvalue == null) goto _L2; else goto _L1
_L1:
        int i = typedvalue.density;
        if(i != 0) goto _L4; else goto _L3
_L3:
        options1.inDensity = 160;
_L2:
        if(options1.inTargetDensity == 0 && resources != null)
            options1.inTargetDensity = resources.getDisplayMetrics().densityDpi;
        return decodeStream(inputstream, rect, options1);
_L4:
        if(i != 65535)
            options1.inDensity = i;
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static Bitmap decodeStream(InputStream inputstream)
    {
        return decodeStream(inputstream, null, null);
    }

    public static Bitmap decodeStream(InputStream inputstream, Rect rect, Options options)
    {
        if(inputstream == null)
            return null;
        Options.validate(options);
        Trace.traceBegin(2L, "decodeBitmap");
        if(!(inputstream instanceof android.content.res.AssetManager.AssetInputStream))
            break MISSING_BLOCK_LABEL_74;
        inputstream = nativeDecodeAsset(((android.content.res.AssetManager.AssetInputStream)inputstream).getNativeAsset(), rect, options);
_L1:
        if(inputstream != null || options == null)
            break MISSING_BLOCK_LABEL_84;
        if(options.inBitmap != null)
        {
            inputstream = JVM INSTR new #46  <Class IllegalArgumentException>;
            inputstream.IllegalArgumentException("Problem decoding into existing bitmap");
            throw inputstream;
        }
        break MISSING_BLOCK_LABEL_84;
        inputstream;
        Trace.traceEnd(2L);
        throw inputstream;
        inputstream = decodeStreamInternal(inputstream, rect, options);
          goto _L1
        setDensityFromOptions(inputstream, options);
        Trace.traceEnd(2L);
        return inputstream;
    }

    private static Bitmap decodeStreamInternal(InputStream inputstream, Rect rect, Options options)
    {
        byte abyte0[] = null;
        if(options != null)
            abyte0 = options.inTempStorage;
        byte abyte1[] = abyte0;
        if(abyte0 == null)
            abyte1 = new byte[16384];
        return nativeDecodeStream(inputstream, abyte1, rect, options);
    }

    private static native Bitmap nativeDecodeAsset(long l, Rect rect, Options options);

    private static native Bitmap nativeDecodeByteArray(byte abyte0[], int i, int j, Options options);

    private static native Bitmap nativeDecodeFileDescriptor(FileDescriptor filedescriptor, Rect rect, Options options);

    private static native Bitmap nativeDecodeStream(InputStream inputstream, byte abyte0[], Rect rect, Options options);

    private static native boolean nativeIsSeekable(FileDescriptor filedescriptor);

    private static void setDensityFromOptions(Bitmap bitmap, Options options)
    {
        int i;
        if(bitmap == null || options == null)
            return;
        i = options.inDensity;
        if(i == 0) goto _L2; else goto _L1
_L1:
        bitmap.setDensity(i);
        int j;
        for(j = options.inTargetDensity; j == 0 || i == j || i == options.inScreenDensity;)
            return;

        byte abyte0[] = bitmap.getNinePatchChunk();
        boolean flag;
        if(abyte0 != null)
            flag = NinePatch.isNinePatchChunk(abyte0);
        else
            flag = false;
        if(options.inScaled || flag)
            bitmap.setDensity(j);
_L4:
        return;
_L2:
        if(options.inBitmap != null)
            bitmap.setDensity(Bitmap.getDefaultDensity());
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final int DECODE_BUFFER_SIZE = 16384;
}
