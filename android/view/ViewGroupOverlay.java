// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.Context;

// Referenced classes of package android.view:
//            ViewOverlay, View

public class ViewGroupOverlay extends ViewOverlay
{

    ViewGroupOverlay(Context context, View view)
    {
        super(context, view);
    }

    public void add(View view)
    {
        mOverlayViewGroup.add(view);
    }

    public void remove(View view)
    {
        mOverlayViewGroup.remove(view);
    }
}
