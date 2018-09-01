// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import java.io.File;
import java.io.IOException;

public class JournaledFile
{

    public JournaledFile(File file, File file1)
    {
        mReal = file;
        mTemp = file1;
    }

    public File chooseForRead()
    {
        File file1;
        if(mReal.exists())
        {
            File file = mReal;
            file1 = file;
            if(mTemp.exists())
            {
                mTemp.delete();
                file1 = file;
            }
        } else
        if(mTemp.exists())
        {
            file1 = mTemp;
            mTemp.renameTo(mReal);
        } else
        {
            return mReal;
        }
        return file1;
    }

    public File chooseForWrite()
    {
        if(mWriting)
            throw new IllegalStateException("uncommitted write already in progress");
        if(!mReal.exists())
            try
            {
                mReal.createNewFile();
            }
            catch(IOException ioexception) { }
        if(mTemp.exists())
            mTemp.delete();
        mWriting = true;
        return mTemp;
    }

    public void commit()
    {
        if(!mWriting)
        {
            throw new IllegalStateException("no file to commit");
        } else
        {
            mWriting = false;
            mTemp.renameTo(mReal);
            return;
        }
    }

    public void rollback()
    {
        if(!mWriting)
        {
            throw new IllegalStateException("no file to roll back");
        } else
        {
            mWriting = false;
            mTemp.delete();
            return;
        }
    }

    File mReal;
    File mTemp;
    boolean mWriting;
}
