// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.graphics.drawable.Drawable;

// Referenced classes of package android.content.res:
//            ThemedResourceCache, Configuration, Resources

class DrawableCache extends ThemedResourceCache
{

    DrawableCache()
    {
    }

    public Drawable getInstance(long l, Resources resources, Resources.Theme theme)
    {
        android.graphics.drawable.Drawable.ConstantState constantstate = (android.graphics.drawable.Drawable.ConstantState)get(l, theme);
        if(constantstate != null)
            return constantstate.newDrawable(resources, theme);
        else
            return null;
    }

    public boolean shouldInvalidateEntry(android.graphics.drawable.Drawable.ConstantState constantstate, int i)
    {
        return Configuration.needNewResources(i, constantstate.getChangingConfigurations());
    }

    public volatile boolean shouldInvalidateEntry(Object obj, int i)
    {
        return shouldInvalidateEntry((android.graphics.drawable.Drawable.ConstantState)obj, i);
    }
}
