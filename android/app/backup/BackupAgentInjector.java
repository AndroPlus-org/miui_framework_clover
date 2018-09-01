// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.system.OsConstants;

class BackupAgentInjector
{

    BackupAgentInjector()
    {
    }

    static boolean shouldSkip(int i)
    {
        boolean flag;
        if(!OsConstants.S_ISREG(i))
            flag = OsConstants.S_ISDIR(i) ^ true;
        else
            flag = false;
        return flag;
    }
}
