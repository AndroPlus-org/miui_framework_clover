// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;


// Referenced classes of package miui.maml.data:
//            BaseFunctions, StringFunctions, FormatFunctions

public class FunctionsLoader
{

    public FunctionsLoader()
    {
    }

    public static void load()
    {
        BaseFunctions.load();
        StringFunctions.load();
        FormatFunctions.load();
    }
}
