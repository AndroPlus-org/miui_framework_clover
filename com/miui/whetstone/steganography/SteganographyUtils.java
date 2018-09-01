// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.steganography;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;

// Referenced classes of package com.miui.whetstone.steganography:
//            Steg, DecodedObject, EncodedObject

public class SteganographyUtils
{

    public SteganographyUtils()
    {
    }

    public static String decodeWatermark(Bitmap bitmap)
    {
        Object obj = null;
        if(bitmap == null)
            return null;
        try
        {
            bitmap = Steg.withInput(bitmap).decode().intoString();
        }
        // Misplaced declaration of an exception variable
        catch(Bitmap bitmap)
        {
            Log.w(TAG, (new StringBuilder()).append("decodeWatermark Exception e:").append(bitmap.getMessage()).toString());
            bitmap = obj;
        }
        return bitmap;
    }

    public static String decodeWatermark(File file)
    {
        Object obj = null;
        if(file == null)
            return null;
        try
        {
            file = Steg.withInput(file).decode().intoString();
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            Log.w(TAG, (new StringBuilder()).append("decodeWatermark Exception e:").append(file.getMessage()).toString());
            file = obj;
        }
        return file;
    }

    public static String decodeWatermark(String s)
    {
        Object obj = null;
        if(TextUtils.isEmpty(s))
            return null;
        try
        {
            s = Steg.withInput(s).decode().intoString();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w(TAG, (new StringBuilder()).append("decodeWatermark Exception e:").append(s.getMessage()).toString());
            s = obj;
        }
        return s;
    }

    public static Bitmap encodeWatermark(Bitmap bitmap, String s)
    {
        Object obj = null;
        if(bitmap == null || TextUtils.isEmpty(s))
            return null;
        try
        {
            bitmap = Steg.withInput(bitmap).encode(s).intoBitmap();
        }
        // Misplaced declaration of an exception variable
        catch(Bitmap bitmap)
        {
            Log.w(TAG, (new StringBuilder()).append("encodeWatermark Exception e:").append(bitmap.getMessage()).toString());
            bitmap = obj;
        }
        return bitmap;
    }

    public static Bitmap encodeWatermark(File file, String s)
    {
        Object obj = null;
        if(file == null || TextUtils.isEmpty(s))
            return null;
        try
        {
            file = Steg.withInput(file).encode(s).intoBitmap();
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            Log.w(TAG, (new StringBuilder()).append("encodeWatermark Exception e:").append(file.getMessage()).toString());
            file = obj;
        }
        return file;
    }

    public static Bitmap encodeWatermark(String s, String s1)
    {
        Object obj = null;
        if(TextUtils.isEmpty(s) || TextUtils.isEmpty(s1))
            return null;
        try
        {
            s = Steg.withInput(s).encode(s1).intoBitmap();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w(TAG, (new StringBuilder()).append("encodeWatermark Exception e:").append(s.getMessage()).toString());
            s = obj;
        }
        return s;
    }

    public static File encodeWatermark(Bitmap bitmap, File file, String s)
    {
        Object obj;
        for(obj = null; bitmap == null || file == null || TextUtils.isEmpty(s);)
            return null;

        try
        {
            bitmap = Steg.withInput(bitmap).encode(s).intoFile(file);
        }
        // Misplaced declaration of an exception variable
        catch(Bitmap bitmap)
        {
            Log.w(TAG, (new StringBuilder()).append("encodeWatermark Exception e:").append(bitmap.getMessage()).toString());
            bitmap = obj;
        }
        return bitmap;
    }

    public static File encodeWatermark(Bitmap bitmap, String s, String s1)
    {
        Object obj;
        for(obj = null; bitmap == null || s == null || TextUtils.isEmpty(s1);)
            return null;

        try
        {
            bitmap = Steg.withInput(bitmap).encode(s1).intoFile(s);
        }
        // Misplaced declaration of an exception variable
        catch(Bitmap bitmap)
        {
            Log.w(TAG, (new StringBuilder()).append("encodeWatermark Exception e:").append(bitmap.getMessage()).toString());
            bitmap = obj;
        }
        return bitmap;
    }

    public static File encodeWatermark(File file, File file1, String s)
    {
        Object obj;
        for(obj = null; file == null || file1 == null || TextUtils.isEmpty(s);)
            return null;

        try
        {
            file = Steg.withInput(file).encode(s).intoFile(file1);
        }
        // Misplaced declaration of an exception variable
        catch(File file)
        {
            Log.w(TAG, (new StringBuilder()).append("encodeWatermark Exception e:").append(file.getMessage()).toString());
            file = obj;
        }
        return file;
    }

    public static File encodeWatermark(String s, String s1, String s2)
    {
        Object obj;
        for(obj = null; s == null || s1 == null || TextUtils.isEmpty(s2);)
            return null;

        try
        {
            s = Steg.withInput(s).encode(s2).intoFile(s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w(TAG, (new StringBuilder()).append("encodeWatermark Exception e:").append(s.getMessage()).toString());
            s = obj;
        }
        return s;
    }

    private static String TAG = "Whet_SteganographyUtils";

}
