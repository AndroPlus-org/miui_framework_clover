// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.os;

import android.os.Environment;
import java.io.File;

public class MiuiEnvironment
{

    public MiuiEnvironment()
    {
    }

    public static File getLegacyExternalStorageDirectory()
    {
        return Environment.getLegacyExternalStorageDirectory();
    }
}
