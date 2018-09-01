// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.opengl;


// Referenced classes of package android.opengl:
//            GLU

public class GLException extends RuntimeException
{

    public GLException(int i)
    {
        super(getErrorString(i));
        mError = i;
    }

    public GLException(int i, String s)
    {
        super(s);
        mError = i;
    }

    private static String getErrorString(int i)
    {
        String s = GLU.gluErrorString(i);
        String s1 = s;
        if(s == null)
            s1 = (new StringBuilder()).append("Unknown error 0x").append(Integer.toHexString(i)).toString();
        return s1;
    }

    int getError()
    {
        return mError;
    }

    private final int mError;
}
