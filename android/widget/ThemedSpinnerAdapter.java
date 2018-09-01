// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;


// Referenced classes of package android.widget:
//            SpinnerAdapter

public interface ThemedSpinnerAdapter
    extends SpinnerAdapter
{

    public abstract android.content.res.Resources.Theme getDropDownViewTheme();

    public abstract void setDropDownViewTheme(android.content.res.Resources.Theme theme);
}
