// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.theme;

import android.miui.Shell;
import android.os.SELinux;
import android.util.Log;
import java.io.File;
import miui.content.res.ThemeResources;

public class ThemePermissionUtils
{

    public ThemePermissionUtils()
    {
    }

    public static boolean updateFilePermissionWithThemeContext(String s)
    {
        return updateFilePermissionWithThemeContext(s, true);
    }

    public static boolean updateFilePermissionWithThemeContext(String s, boolean flag)
    {
        Object obj;
        char c;
        if(s == null)
            return false;
        obj = null;
        c = '\uFFFF';
        if(!s.startsWith("/data/system/theme/")) goto _L2; else goto _L1
_L1:
        obj = new File("/data/system/theme/");
        c = '\u01ED';
_L4:
        File file = new File(s);
        if(obj == null || file.exists() ^ true || file.getAbsolutePath().equals(((File) (obj)).getAbsolutePath()))
            return false;
        break; /* Loop/switch isn't completed */
_L2:
        if(s.startsWith(ThemeResources.THEME_MAGIC_PATH))
        {
            obj = new File(ThemeResources.THEME_MAGIC_PATH);
            c = '\u01FD';
        }
        if(true) goto _L4; else goto _L3
_L3:
        boolean flag1 = false;
        if(flag) goto _L6; else goto _L5
_L5:
        flag = flag1;
        if(!SELinux.setFileContext(s, "u:object_r:theme_data_file:s0"));
        boolean flag2;
        flag2 = false;
        flag1 = false;
        flag = flag2;
        obj = JVM INSTR new #57  <Class StringBuilder>;
        flag = flag2;
        ((StringBuilder) (obj)).StringBuilder();
        flag = flag2;
        Log.i("Theme", ((StringBuilder) (obj)).append("system user update theme file: ").append(s).append("  ").append(false).toString());
        flag = flag1;
_L8:
        return flag;
_L6:
        flag = flag1;
        flag1 = Shell.chown(s, 9801, 9801);
        flag = flag1;
        boolean flag3;
        if(!Shell.chmod(s, c))
            flag1 = false;
        flag = flag1;
        flag3 = Shell.setfilecon(s, "u:object_r:theme_data_file:s0");
        flag = flag1;
        if(!flag3)
            flag = false;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
        Log.i("Theme", (new StringBuilder()).append("occur exception when updating theme file: ").append(s).toString());
        if(true) goto _L8; else goto _L7
_L7:
    }

    private static final String THEME_FILE_CONTEXT = "u:object_r:theme_data_file:s0";
}
