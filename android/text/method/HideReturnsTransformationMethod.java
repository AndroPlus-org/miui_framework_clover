// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;


// Referenced classes of package android.text.method:
//            ReplacementTransformationMethod

public class HideReturnsTransformationMethod extends ReplacementTransformationMethod
{

    public HideReturnsTransformationMethod()
    {
    }

    public static HideReturnsTransformationMethod getInstance()
    {
        if(sInstance != null)
        {
            return sInstance;
        } else
        {
            sInstance = new HideReturnsTransformationMethod();
            return sInstance;
        }
    }

    protected char[] getOriginal()
    {
        return ORIGINAL;
    }

    protected char[] getReplacement()
    {
        return REPLACEMENT;
    }

    private static char ORIGINAL[] = {
        '\r'
    };
    private static char REPLACEMENT[] = {
        '\uFEFF'
    };
    private static HideReturnsTransformationMethod sInstance;

}
