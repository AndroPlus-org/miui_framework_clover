// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.system.*;

public class StatFs
{

    public StatFs(String s)
    {
        mStat = doStat(s);
    }

    private static StructStatVfs doStat(String s)
    {
        StructStatVfs structstatvfs;
        try
        {
            structstatvfs = Os.statvfs(s);
        }
        catch(ErrnoException errnoexception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid path: ").append(s).toString(), errnoexception);
        }
        return structstatvfs;
    }

    public int getAvailableBlocks()
    {
        return (int)mStat.f_bavail;
    }

    public long getAvailableBlocksLong()
    {
        return mStat.f_bavail;
    }

    public long getAvailableBytes()
    {
        return mStat.f_bavail * mStat.f_frsize;
    }

    public int getBlockCount()
    {
        return (int)mStat.f_blocks;
    }

    public long getBlockCountLong()
    {
        return mStat.f_blocks;
    }

    public int getBlockSize()
    {
        return (int)mStat.f_frsize;
    }

    public long getBlockSizeLong()
    {
        return mStat.f_frsize;
    }

    public int getFreeBlocks()
    {
        return (int)mStat.f_bfree;
    }

    public long getFreeBlocksLong()
    {
        return mStat.f_bfree;
    }

    public long getFreeBytes()
    {
        return mStat.f_bfree * mStat.f_frsize;
    }

    public long getTotalBytes()
    {
        return mStat.f_blocks * mStat.f_frsize;
    }

    public void restat(String s)
    {
        mStat = doStat(s);
    }

    private StructStatVfs mStat;
}
