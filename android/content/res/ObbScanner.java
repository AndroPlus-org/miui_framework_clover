// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import java.io.File;
import java.io.IOException;

// Referenced classes of package android.content.res:
//            ObbInfo

public class ObbScanner
{

    private ObbScanner()
    {
    }

    public static ObbInfo getObbInfo(String s)
        throws IOException
    {
        if(s == null)
            throw new IllegalArgumentException("file path cannot be null");
        Object obj = new File(s);
        if(!((File) (obj)).exists())
        {
            throw new IllegalArgumentException((new StringBuilder()).append("OBB file does not exist: ").append(s).toString());
        } else
        {
            s = ((File) (obj)).getCanonicalPath();
            obj = new ObbInfo();
            obj.filename = s;
            getObbInfo_native(s, ((ObbInfo) (obj)));
            return ((ObbInfo) (obj));
        }
    }

    private static native void getObbInfo_native(String s, ObbInfo obbinfo)
        throws IOException;
}
