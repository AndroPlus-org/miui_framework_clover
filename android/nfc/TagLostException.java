// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc;

import java.io.IOException;

public class TagLostException extends IOException
{

    public TagLostException()
    {
    }

    public TagLostException(String s)
    {
        super(s);
    }
}
