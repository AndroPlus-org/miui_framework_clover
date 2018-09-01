// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.Rect;

// Referenced classes of package android.view:
//            DisplayListCanvas

public interface WindowCallbacks
{

    public abstract boolean onContentDrawn(int i, int j, int k, int l);

    public abstract void onPostDraw(DisplayListCanvas displaylistcanvas);

    public abstract void onRequestDraw(boolean flag);

    public abstract void onWindowDragResizeEnd();

    public abstract void onWindowDragResizeStart(Rect rect, boolean flag, Rect rect1, Rect rect2, int i);

    public abstract void onWindowSizeIsChanging(Rect rect, boolean flag, Rect rect1, Rect rect2);

    public static final int RESIZE_MODE_DOCKED_DIVIDER = 1;
    public static final int RESIZE_MODE_FREEFORM = 0;
    public static final int RESIZE_MODE_INVALID = -1;
}
