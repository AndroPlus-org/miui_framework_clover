// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.HashSet;

public class FilenameExtFilter
    implements FilenameFilter
{

    public FilenameExtFilter(String as[])
    {
        mExts = new HashSet();
        if(as != null)
            mExts.addAll(Arrays.asList(as));
    }

    public boolean accept(File file, String s)
    {
        if((new File((new StringBuilder()).append(file).append(File.separator).append(s).toString())).isDirectory())
            return true;
        int i = s.lastIndexOf('.');
        if(i != -1)
            return contains(((String)s.subSequence(i + 1, s.length())).toLowerCase());
        else
            return false;
    }

    public boolean contains(String s)
    {
        return mExts.contains(s.toLowerCase());
    }

    private HashSet mExts;
}
