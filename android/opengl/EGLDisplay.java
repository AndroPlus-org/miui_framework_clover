// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.opengl;


// Referenced classes of package android.opengl:
//            EGLObjectHandle

public class EGLDisplay extends EGLObjectHandle
{

    private EGLDisplay(long l)
    {
        super(l);
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(!(obj instanceof EGLDisplay))
            return false;
        obj = (EGLDisplay)obj;
        if(getNativeHandle() != ((EGLDisplay) (obj)).getNativeHandle())
            flag = false;
        return flag;
    }
}
