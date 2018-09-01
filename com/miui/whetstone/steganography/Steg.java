// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.steganography;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;

// Referenced classes of package com.miui.whetstone.steganography:
//            DecodedObject, BitmapEncoder, EncodedObject

public class Steg
{

    public Steg()
    {
        key = null;
        passmode = 0;
        inBitmap = null;
    }

    private int bytesAvaliableInBitmap()
    {
        if(inBitmap == null)
            return 0;
        else
            return (inBitmap.getWidth() * inBitmap.getHeight() * 3) / 8 - 12;
    }

    private void setInputBitmap(Bitmap bitmap)
    {
        inBitmap = bitmap;
    }

    public static Steg withInput(Bitmap bitmap)
    {
        Steg steg = new Steg();
        steg.setInputBitmap(bitmap);
        return steg;
    }

    public static Steg withInput(File file)
    {
        Steg steg = new Steg();
        steg.setInputBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
        return steg;
    }

    public static Steg withInput(String s)
    {
        Steg steg = new Steg();
        steg.setInputBitmap(BitmapFactory.decodeFile(s));
        return steg;
    }

    public DecodedObject decode()
        throws Exception
    {
        return new DecodedObject(BitmapEncoder.decode(inBitmap));
    }

    public EncodedObject encode(File file)
        throws Exception
    {
        throw new RuntimeException("Not implemented yet");
    }

    public EncodedObject encode(String s)
        throws Exception
    {
        return encode(s.getBytes());
    }

    public EncodedObject encode(byte abyte0[])
        throws Exception
    {
        if(abyte0.length > bytesAvaliableInBitmap())
            throw new IllegalArgumentException((new StringBuilder()).append("Not enough space in bitmap to hold data (max:").append(bytesAvaliableInBitmap()).append(")").toString());
        else
            return new EncodedObject(BitmapEncoder.encode(inBitmap, abyte0));
    }

    public Steg withPassword(String s)
    {
        withPassword(s, 1);
        return this;
    }

    public Steg withPassword(String s, int i)
    {
        key = s;
        passmode = i;
        throw new RuntimeException("Not implemented yet");
    }

    private final int PASS_NONE = 0;
    private final int PASS_SIMPLE_XOR = 1;
    private Bitmap inBitmap;
    private String key;
    private int passmode;
}
