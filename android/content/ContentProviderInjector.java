// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;


// Referenced classes of package android.content:
//            Context

public class ContentProviderInjector
{

    public ContentProviderInjector()
    {
    }

    public static boolean isCrossUserIncomingUri(Context context, int i)
    {
        return context.getUserId() == 0 && i == 999;
    }

    public static boolean isMmsProviderClass(String s)
    {
        return "com.android.providers.telephony.MmsProvider".equals(s);
    }
}
