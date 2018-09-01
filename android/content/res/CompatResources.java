// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

// Referenced classes of package android.content.res:
//            Resources, ColorStateList

public class CompatResources extends Resources
{

    public CompatResources(ClassLoader classloader)
    {
        super(classloader);
        mContext = new WeakReference(null);
    }

    private Resources.Theme getTheme()
    {
        Resources.Theme theme = null;
        Context context = (Context)mContext.get();
        if(context != null)
            theme = context.getTheme();
        return theme;
    }

    public int getColor(int i)
        throws Resources.NotFoundException
    {
        return getColor(i, getTheme());
    }

    public ColorStateList getColorStateList(int i)
        throws Resources.NotFoundException
    {
        return getColorStateList(i, getTheme());
    }

    public Drawable getDrawable(int i)
        throws Resources.NotFoundException
    {
        return getDrawable(i, getTheme());
    }

    public Drawable getDrawableForDensity(int i, int j)
        throws Resources.NotFoundException
    {
        return getDrawableForDensity(i, j, getTheme());
    }

    public void setContext(Context context)
    {
        mContext = new WeakReference(context);
    }

    private WeakReference mContext;
}
