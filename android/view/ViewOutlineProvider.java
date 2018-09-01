// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.Outline;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.view:
//            View

public abstract class ViewOutlineProvider
{

    public ViewOutlineProvider()
    {
    }

    public abstract void getOutline(View view, Outline outline);

    public static final ViewOutlineProvider BACKGROUND = new ViewOutlineProvider() {

        public void getOutline(View view, Outline outline)
        {
            Drawable drawable = view.getBackground();
            if(drawable != null)
            {
                drawable.getOutline(outline);
            } else
            {
                outline.setRect(0, 0, view.getWidth(), view.getHeight());
                outline.setAlpha(0.0F);
            }
        }

    }
;
    public static final ViewOutlineProvider BOUNDS = new ViewOutlineProvider() {

        public void getOutline(View view, Outline outline)
        {
            outline.setRect(0, 0, view.getWidth(), view.getHeight());
        }

    }
;
    public static final ViewOutlineProvider PADDED_BOUNDS = new ViewOutlineProvider() {

        public void getOutline(View view, Outline outline)
        {
            outline.setRect(view.getPaddingLeft(), view.getPaddingTop(), view.getWidth() - view.getPaddingRight(), view.getHeight() - view.getPaddingBottom());
        }

    }
;

}
