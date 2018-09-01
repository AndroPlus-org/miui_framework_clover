// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;

// Referenced classes of package android.widget:
//            ViewSwitcher, ImageView

public class ImageSwitcher extends ViewSwitcher
{

    public ImageSwitcher(Context context)
    {
        super(context);
    }

    public ImageSwitcher(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/ImageSwitcher.getName();
    }

    public void setImageDrawable(Drawable drawable)
    {
        ((ImageView)getNextView()).setImageDrawable(drawable);
        showNext();
    }

    public void setImageResource(int i)
    {
        ((ImageView)getNextView()).setImageResource(i);
        showNext();
    }

    public void setImageURI(Uri uri)
    {
        ((ImageView)getNextView()).setImageURI(uri);
        showNext();
    }
}
