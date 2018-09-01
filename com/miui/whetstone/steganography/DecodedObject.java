// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.steganography;

import java.io.File;
import java.io.IOException;

public class DecodedObject
{

    public DecodedObject(byte abyte0[])
    {
        bytes = abyte0;
    }

    public byte[] intoByteArray()
    {
        return bytes;
    }

    public File intoFile(File file)
    {
        throw new RuntimeException("Not implemented yet");
    }

    public File intoFile(String s)
        throws IOException
    {
        return intoFile(new File(s));
    }

    public String intoString()
    {
        return new String(bytes);
    }

    private final byte bytes[];
}
