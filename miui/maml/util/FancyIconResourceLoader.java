// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import java.io.InputStream;
import miui.content.res.ThemeResources;
import miui.content.res.ThemeResourcesSystem;
import miui.maml.ResourceLoader;

public class FancyIconResourceLoader extends ResourceLoader
{

    public FancyIconResourceLoader(String s)
    {
        mRelatviePathBaseIcons = s;
    }

    public InputStream getInputStream(String s, long al[])
    {
        return ThemeResources.getSystem().getIconStream((new StringBuilder()).append(mRelatviePathBaseIcons).append(s).toString(), al);
    }

    public boolean resourceExists(String s)
    {
        return ThemeResources.getSystem().hasIcon((new StringBuilder()).append(mRelatviePathBaseIcons).append(s).toString());
    }

    private String mRelatviePathBaseIcons;
}
