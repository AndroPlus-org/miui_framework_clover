// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.widget:
//            Adapter

public interface SpinnerAdapter
    extends Adapter
{

    public abstract View getDropDownView(int i, View view, ViewGroup viewgroup);
}
