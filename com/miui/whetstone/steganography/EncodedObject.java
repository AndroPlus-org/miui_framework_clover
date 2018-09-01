// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.steganography;

import android.graphics.Bitmap;
import java.io.*;

public class EncodedObject
{

    public EncodedObject(Bitmap bitmap1)
    {
        bitmap = bitmap1;
    }

    public Bitmap intoBitmap()
    {
        return bitmap;
    }

    public File intoFile(File file)
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, bytearrayoutputstream);
        file.createNewFile();
        FileOutputStream fileoutputstream = new FileOutputStream(file);
        fileoutputstream.write(bytearrayoutputstream.toByteArray());
        fileoutputstream.close();
        return file;
    }

    public File intoFile(String s)
        throws IOException
    {
        return intoFile(new File(s));
    }

    private final Bitmap bitmap;
}
