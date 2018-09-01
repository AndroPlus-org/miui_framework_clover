// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

// Referenced classes of package com.android.internal.view.menu:
//            MenuItemImpl, IconMenuView

public final class IconMenuItemView extends TextView
    implements MenuView.ItemView
{

    public IconMenuItemView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public IconMenuItemView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public IconMenuItemView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mPositionIconAvailable = new Rect();
        mPositionIconOutput = new Rect();
        if(sPrependShortcutLabel == null)
            sPrependShortcutLabel = getResources().getString(0x1040546);
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.MenuView, i, j);
        mDisabledAlpha = attributeset.getFloat(6, 0.8F);
        mTextAppearance = attributeset.getResourceId(1, -1);
        mTextAppearanceContext = context;
        attributeset.recycle();
    }

    private void positionIcon()
    {
        if(mIcon == null)
        {
            return;
        } else
        {
            Rect rect = mPositionIconOutput;
            getLineBounds(0, rect);
            mPositionIconAvailable.set(0, 0, getWidth(), rect.top);
            int i = getLayoutDirection();
            Gravity.apply(0x800013, mIcon.getIntrinsicWidth(), mIcon.getIntrinsicHeight(), mPositionIconAvailable, mPositionIconOutput, i);
            mIcon.setBounds(mPositionIconOutput);
            return;
        }
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        if(mItemData != null && mIcon != null)
        {
            int i;
            Drawable drawable;
            if(!mItemData.isEnabled())
            {
                if(!isPressed())
                    i = isFocused() ^ true;
                else
                    i = 1;
            } else
            {
                i = 0;
            }
            drawable = mIcon;
            if(i != 0)
                i = (int)(mDisabledAlpha * 255F);
            else
                i = 255;
            drawable.setAlpha(i);
        }
    }

    public MenuItemImpl getItemData()
    {
        return mItemData;
    }

    IconMenuView.LayoutParams getTextAppropriateLayoutParams()
    {
        IconMenuView.LayoutParams layoutparams = (IconMenuView.LayoutParams)getLayoutParams();
        IconMenuView.LayoutParams layoutparams1 = layoutparams;
        if(layoutparams == null)
            layoutparams1 = new IconMenuView.LayoutParams(-1, -1);
        layoutparams1.desiredWidth = (int)Layout.getDesiredWidth(getText(), 0, getText().length(), getPaint(), getTextDirectionHeuristic());
        return layoutparams1;
    }

    public void initialize(MenuItemImpl menuitemimpl, int i)
    {
        mItemData = menuitemimpl;
        initialize(menuitemimpl.getTitleForItemView(this), menuitemimpl.getIcon());
        if(menuitemimpl.isVisible())
            i = 0;
        else
            i = 8;
        setVisibility(i);
        setEnabled(menuitemimpl.isEnabled());
    }

    void initialize(CharSequence charsequence, Drawable drawable)
    {
        setClickable(true);
        setFocusable(true);
        if(mTextAppearance != -1)
            setTextAppearance(mTextAppearanceContext, mTextAppearance);
        setTitle(charsequence);
        setIcon(drawable);
        if(mItemData != null)
        {
            drawable = mItemData.getContentDescription();
            if(TextUtils.isEmpty(drawable))
                setContentDescription(charsequence);
            else
                setContentDescription(drawable);
            setTooltipText(mItemData.getTooltipText());
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        positionIcon();
    }

    protected void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        super.onTextChanged(charsequence, i, j, k);
        setLayoutParams(getTextAppropriateLayoutParams());
    }

    public boolean performClick()
    {
        if(super.performClick())
            return true;
        if(mItemInvoker != null && mItemInvoker.invokeItem(mItemData))
        {
            playSoundEffect(0);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean prefersCondensedTitle()
    {
        return true;
    }

    void setCaptionMode(boolean flag)
    {
        if(mItemData == null)
            return;
        Object obj;
        if(flag)
            flag = mItemData.shouldShowShortcut();
        else
            flag = false;
        mShortcutCaptionMode = flag;
        obj = mItemData.getTitleForItemView(this);
        if(mShortcutCaptionMode)
        {
            if(mShortcutCaption == null)
                mShortcutCaption = mItemData.getShortcutLabel();
            obj = mShortcutCaption;
        }
        setText(((CharSequence) (obj)));
    }

    public void setCheckable(boolean flag)
    {
    }

    public void setChecked(boolean flag)
    {
    }

    public void setIcon(Drawable drawable)
    {
        mIcon = drawable;
        if(drawable != null)
        {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            setCompoundDrawables(null, drawable, null, null);
            setGravity(81);
            requestLayout();
        } else
        {
            setCompoundDrawables(null, null, null, null);
            setGravity(17);
        }
    }

    void setIconMenuView(IconMenuView iconmenuview)
    {
        mIconMenuView = iconmenuview;
    }

    public void setItemData(MenuItemImpl menuitemimpl)
    {
        mItemData = menuitemimpl;
    }

    public void setItemInvoker(MenuBuilder.ItemInvoker iteminvoker)
    {
        mItemInvoker = iteminvoker;
    }

    public void setShortcut(boolean flag, char c)
    {
        if(mShortcutCaptionMode)
        {
            mShortcutCaption = null;
            setCaptionMode(true);
        }
    }

    public void setTitle(CharSequence charsequence)
    {
        if(!mShortcutCaptionMode) goto _L2; else goto _L1
_L1:
        setCaptionMode(true);
_L4:
        return;
_L2:
        if(charsequence != null)
            setText(charsequence);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setVisibility(int i)
    {
        super.setVisibility(i);
        if(mIconMenuView != null)
            mIconMenuView.markStaleChildren();
    }

    public boolean showsIcon()
    {
        return true;
    }

    private static final int NO_ALPHA = 255;
    private static String sPrependShortcutLabel;
    private float mDisabledAlpha;
    private Drawable mIcon;
    private IconMenuView mIconMenuView;
    private MenuItemImpl mItemData;
    private MenuBuilder.ItemInvoker mItemInvoker;
    private Rect mPositionIconAvailable;
    private Rect mPositionIconOutput;
    private String mShortcutCaption;
    private boolean mShortcutCaptionMode;
    private int mTextAppearance;
    private Context mTextAppearanceContext;
}
