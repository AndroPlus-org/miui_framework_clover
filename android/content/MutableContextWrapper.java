// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;


// Referenced classes of package android.content:
//            ContextWrapper, Context

public class MutableContextWrapper extends ContextWrapper
{

    public MutableContextWrapper(Context context)
    {
        super(context);
    }

    public void setBaseContext(Context context)
    {
        mBase = context;
    }
}
