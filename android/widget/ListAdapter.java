// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;


// Referenced classes of package android.widget:
//            Adapter

public interface ListAdapter
    extends Adapter
{

    public abstract boolean areAllItemsEnabled();

    public abstract boolean isEnabled(int i);
}
