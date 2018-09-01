// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.content.res.AssetManager;
import android.graphics.fonts.FontVariationAxis;
import android.util.Log;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FontFamily
{

    public FontFamily()
    {
        mBuilderPtr = nInitBuilder(null, 0);
    }

    public FontFamily(String s, int i)
    {
        mBuilderPtr = nInitBuilder(s, i);
    }

    private static native void nAbort(long l);

    private static native void nAddAxisValue(long l, int i, float f);

    private static boolean nAddFont(long l, ByteBuffer bytebuffer, int i)
    {
        return nAddFont(l, bytebuffer, i, -1, -1);
    }

    private static native boolean nAddFont(long l, ByteBuffer bytebuffer, int i, int j, int k);

    private static native boolean nAddFontFromAssetManager(long l, AssetManager assetmanager, String s, int i, boolean flag, int j, int k, 
            int i1);

    private static native boolean nAddFontWeightStyle(long l, ByteBuffer bytebuffer, int i, int j, int k);

    private static native void nAllowUnsupportedFont(long l);

    private static native long nCreateFamily(long l);

    private static native long nInitBuilder(String s, int i);

    private static native void nUnrefFamily(long l);

    public void abortCreation()
    {
        if(mBuilderPtr == 0L)
        {
            throw new IllegalStateException("This FontFamily is already frozen or abandoned");
        } else
        {
            nAbort(mBuilderPtr);
            mBuilderPtr = 0L;
            return;
        }
    }

    public boolean addFont(String s, int i, FontVariationAxis afontvariationaxis[], int j, int k)
    {
        FontVariationAxis afontvariationaxis1[];
        Object obj1;
        FontVariationAxis fontvariationaxis;
        if(mBuilderPtr == 0L)
            throw new IllegalStateException("Unable to call addFont after freezing.");
        afontvariationaxis1 = null;
        obj1 = null;
        fontvariationaxis = null;
        Object obj2;
        obj2 = JVM INSTR new #63  <Class FileInputStream>;
        ((FileInputStream) (obj2)).FileInputStream(s);
        obj1 = ((FileInputStream) (obj2)).getChannel();
        long l = ((FileChannel) (obj1)).size();
        obj1 = ((FileChannel) (obj1)).map(java.nio.channels.FileChannel.MapMode.READ_ONLY, 0L, l);
        if(afontvariationaxis == null) goto _L2; else goto _L1
_L1:
        int i1 = 0;
        int j1 = afontvariationaxis.length;
_L3:
        if(i1 >= j1)
            break; /* Loop/switch isn't completed */
        fontvariationaxis = afontvariationaxis[i1];
        nAddAxisValue(mBuilderPtr, fontvariationaxis.getOpenTypeTagValue(), fontvariationaxis.getStyleValue());
        i1++;
        if(true) goto _L3; else goto _L2
_L2:
        boolean flag = nAddFont(mBuilderPtr, ((ByteBuffer) (obj1)), i, j, k);
        afontvariationaxis = afontvariationaxis1;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_145;
        ((FileInputStream) (obj2)).close();
        afontvariationaxis = afontvariationaxis1;
_L6:
        if(afontvariationaxis == null) goto _L5; else goto _L4
_L4:
        try
        {
            throw afontvariationaxis;
        }
        // Misplaced declaration of an exception variable
        catch(FontVariationAxis afontvariationaxis[]) { }
_L7:
        Log.e(TAG, (new StringBuilder()).append("Error mapping font file ").append(s).toString());
        return false;
        afontvariationaxis;
          goto _L6
_L5:
        return flag;
        obj2;
        afontvariationaxis = fontvariationaxis;
_L11:
        throw obj2;
        Exception exception;
        exception;
        obj1 = afontvariationaxis;
        afontvariationaxis = ((FontVariationAxis []) (obj2));
        obj2 = exception;
_L10:
        exception = afontvariationaxis;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_223;
        ((FileInputStream) (obj1)).close();
        exception = afontvariationaxis;
_L8:
        if(exception == null)
            break MISSING_BLOCK_LABEL_269;
        try
        {
            throw exception;
        }
        // Misplaced declaration of an exception variable
        catch(FontVariationAxis afontvariationaxis[]) { }
          goto _L7
        obj1;
label0:
        {
            if(afontvariationaxis != null)
                break label0;
            exception = ((Exception) (obj1));
        }
          goto _L8
        exception = afontvariationaxis;
        if(afontvariationaxis == obj1) goto _L8; else goto _L9
_L9:
        afontvariationaxis.addSuppressed(((Throwable) (obj1)));
        exception = afontvariationaxis;
          goto _L8
        throw obj2;
        obj2;
        afontvariationaxis = null;
          goto _L10
        afontvariationaxis;
        obj1 = obj2;
        Object obj = null;
        obj2 = afontvariationaxis;
        afontvariationaxis = obj;
          goto _L10
        Throwable throwable;
        throwable;
        afontvariationaxis = ((FontVariationAxis []) (obj2));
        obj2 = throwable;
          goto _L11
    }

    public boolean addFontFromAssetManager(AssetManager assetmanager, String s, int i, boolean flag, int j, int k, int l, 
            FontVariationAxis afontvariationaxis[])
    {
        if(mBuilderPtr == 0L)
            throw new IllegalStateException("Unable to call addFontFromAsset after freezing.");
        if(afontvariationaxis != null)
        {
            int i1 = 0;
            for(int j1 = afontvariationaxis.length; i1 < j1; i1++)
            {
                FontVariationAxis fontvariationaxis = afontvariationaxis[i1];
                nAddAxisValue(mBuilderPtr, fontvariationaxis.getOpenTypeTagValue(), fontvariationaxis.getStyleValue());
            }

        }
        return nAddFontFromAssetManager(mBuilderPtr, assetmanager, s, i, flag, j, k, l);
    }

    public boolean addFontFromBuffer(ByteBuffer bytebuffer, int i, FontVariationAxis afontvariationaxis[], int j, int k)
    {
        if(mBuilderPtr == 0L)
            throw new IllegalStateException("Unable to call addFontWeightStyle after freezing.");
        if(afontvariationaxis != null)
        {
            int l = 0;
            for(int i1 = afontvariationaxis.length; l < i1; l++)
            {
                FontVariationAxis fontvariationaxis = afontvariationaxis[l];
                nAddAxisValue(mBuilderPtr, fontvariationaxis.getOpenTypeTagValue(), fontvariationaxis.getStyleValue());
            }

        }
        return nAddFontWeightStyle(mBuilderPtr, bytebuffer, i, j, k);
    }

    public void allowUnsupportedFont()
    {
        if(mBuilderPtr == 0L)
        {
            throw new IllegalStateException("Unable to allow unsupported font.");
        } else
        {
            nAllowUnsupportedFont(mBuilderPtr);
            return;
        }
    }

    protected void finalize()
        throws Throwable
    {
        if(mNativePtr != 0L)
            nUnrefFamily(mNativePtr);
        if(mBuilderPtr != 0L)
            nAbort(mBuilderPtr);
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public boolean freeze()
    {
        if(mBuilderPtr == 0L)
            throw new IllegalStateException("This FontFamily is already frozen");
        mNativePtr = nCreateFamily(mBuilderPtr);
        mBuilderPtr = 0L;
        boolean flag;
        if(mNativePtr != 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static String TAG = "FontFamily";
    private long mBuilderPtr;
    public long mNativePtr;

}
