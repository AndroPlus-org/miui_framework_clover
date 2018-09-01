// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.opengl;


// Referenced classes of package android.opengl:
//            EGLObjectHandle

public class EGLContext extends EGLObjectHandle
{

    private EGLContext(long l)
    {
        super(l);
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(!(obj instanceof EGLContext))
            return false;
        obj = (EGLContext)obj;
        if(getNativeHandle() != ((EGLContext) (obj)).getNativeHandle())
            flag = false;
        return flag;
    }
}
