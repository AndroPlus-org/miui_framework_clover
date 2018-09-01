// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import java.util.List;

public class FragmentManagerNonConfig
{

    FragmentManagerNonConfig(List list, List list1)
    {
        mFragments = list;
        mChildNonConfigs = list1;
    }

    List getChildNonConfigs()
    {
        return mChildNonConfigs;
    }

    List getFragments()
    {
        return mFragments;
    }

    private final List mChildNonConfigs;
    private final List mFragments;
}
