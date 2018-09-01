// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

// Referenced classes of package android.preference:
//            DialogPreference

public class SeekBarDialogPreference extends DialogPreference
{

    public SeekBarDialogPreference(Context context)
    {
        this(context, null);
    }

    public SeekBarDialogPreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x11100b1);
    }

    public SeekBarDialogPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public SeekBarDialogPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        createActionButtons();
        mMyIcon = getDialogIcon();
        setDialogIcon(null);
    }

    protected static SeekBar getSeekBar(View view)
    {
        return (SeekBar)view.findViewById(0x10203d3);
    }

    public void createActionButtons()
    {
        setPositiveButtonText(0x104000a);
        setNegativeButtonText(0x1040000);
    }

    protected void onBindDialogView(View view)
    {
        super.onBindDialogView(view);
        view = (ImageView)view.findViewById(0x1020006);
        if(mMyIcon != null)
            view.setImageDrawable(mMyIcon);
        else
            view.setVisibility(8);
    }

    private final Drawable mMyIcon;
}
