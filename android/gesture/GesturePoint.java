// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.gesture;

import java.io.DataInputStream;
import java.io.IOException;

public class GesturePoint
{

    public GesturePoint(float f, float f1, long l)
    {
        x = f;
        y = f1;
        timestamp = l;
    }

    static GesturePoint deserialize(DataInputStream datainputstream)
        throws IOException
    {
        return new GesturePoint(datainputstream.readFloat(), datainputstream.readFloat(), datainputstream.readLong());
    }

    public Object clone()
    {
        return new GesturePoint(x, y, timestamp);
    }

    public final long timestamp;
    public final float x;
    public final float y;
}
