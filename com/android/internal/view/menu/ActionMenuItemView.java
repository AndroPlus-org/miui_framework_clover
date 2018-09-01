// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.menu;

import android.content.Context;
import android.content.res.*;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ForwardingListener;
import android.widget.TextView;
import java.util.List;

// Referenced classes of package com.android.internal.view.menu:
//            MenuItemImpl, ShowableListMenu

public class ActionMenuItemView extends TextView
    implements MenuView.ItemView, android.view.View.OnClickListener, android.widget.ActionMenuView.ActionMenuChildView
{
    private class ActionMenuItemForwardingListener extends ForwardingListener
    {

        public ShowableListMenu getPopup()
        {
            if(ActionMenuItemView._2D_get2(ActionMenuItemView.this) != null)
                return ActionMenuItemView._2D_get2(ActionMenuItemView.this).getPopup();
            else
                return null;
        }

        protected boolean onForwardingStarted()
        {
            boolean flag = false;
            if(ActionMenuItemView._2D_get1(ActionMenuItemView.this) != null && ActionMenuItemView._2D_get1(ActionMenuItemView.this).invokeItem(ActionMenuItemView._2D_get0(ActionMenuItemView.this)))
            {
                ShowableListMenu showablelistmenu = getPopup();
                if(showablelistmenu != null)
                    flag = showablelistmenu.isShowing();
                return flag;
            } else
            {
                return false;
            }
        }

        final ActionMenuItemView this$0;

        public ActionMenuItemForwardingListener()
        {
            this$0 = ActionMenuItemView.this;
            super(ActionMenuItemView.this);
        }
    }

    public static abstract class PopupCallback
    {

        public abstract ShowableListMenu getPopup();

        public PopupCallback()
        {
        }
    }


    static MenuItemImpl _2D_get0(ActionMenuItemView actionmenuitemview)
    {
        return actionmenuitemview.mItemData;
    }

    static MenuBuilder.ItemInvoker _2D_get1(ActionMenuItemView actionmenuitemview)
    {
        return actionmenuitemview.mItemInvoker;
    }

    static PopupCallback _2D_get2(ActionMenuItemView actionmenuitemview)
    {
        return actionmenuitemview.mPopupCallback;
    }

    public ActionMenuItemView(Context context)
    {
        this(context, null);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        Resources resources = context.getResources();
        mAllowTextWithIcon = shouldAllowTextWithIcon();
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ActionMenuItemView, i, j);
        mMinWidth = context.getDimensionPixelSize(0, 0);
        context.recycle();
        mMaxIconSize = (int)(32F * resources.getDisplayMetrics().density + 0.5F);
        setOnClickListener(this);
        mSavedPaddingLeft = -1;
        setSaveEnabled(false);
    }

    private boolean shouldAllowTextWithIcon()
    {
        boolean flag;
        Configuration configuration;
        int i;
        int j;
        boolean flag1;
        flag = true;
        configuration = getContext().getResources().getConfiguration();
        i = configuration.screenWidthDp;
        j = configuration.screenHeightDp;
        flag1 = flag;
        if(i >= 480) goto _L2; else goto _L1
_L1:
        if(i < 640 || j < 480) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(configuration.orientation != 2)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void updateTextButtonVisibility()
    {
        boolean flag = true;
        Object obj = null;
        boolean flag1 = TextUtils.isEmpty(mTitle);
        boolean flag2 = flag;
        boolean flag3;
        CharSequence charsequence;
        if(mIcon != null)
            if(mItemData.showsTextAsAction())
            {
                flag2 = flag;
                if(!mAllowTextWithIcon)
                    flag2 = mExpandedFormat;
            } else
            {
                flag2 = false;
            }
        flag3 = (flag1 ^ true) & flag2;
        if(flag3)
            charsequence = mTitle;
        else
            charsequence = null;
        setText(charsequence);
        charsequence = mItemData.getContentDescription();
        if(TextUtils.isEmpty(charsequence))
        {
            if(flag3)
                charsequence = null;
            else
                charsequence = mItemData.getTitle();
            setContentDescription(charsequence);
        } else
        {
            setContentDescription(charsequence);
        }
        charsequence = mItemData.getTooltipText();
        if(TextUtils.isEmpty(charsequence))
        {
            if(flag3)
                charsequence = obj;
            else
                charsequence = mItemData.getTitle();
            setTooltipText(charsequence);
        } else
        {
            setTooltipText(charsequence);
        }
    }

    public boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        return onHoverEvent(motionevent);
    }

    public boolean dispatchPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        onPopulateAccessibilityEvent(accessibilityevent);
        return true;
    }

    public MenuItemImpl getItemData()
    {
        return mItemData;
    }

    public boolean hasText()
    {
        return TextUtils.isEmpty(getText()) ^ true;
    }

    public void initialize(MenuItemImpl menuitemimpl, int i)
    {
        mItemData = menuitemimpl;
        setIcon(menuitemimpl.getIcon());
        setTitle(menuitemimpl.getTitleForItemView(this));
        setId(menuitemimpl.getItemId());
        if(menuitemimpl.isVisible())
            i = 0;
        else
            i = 8;
        setVisibility(i);
        setEnabled(menuitemimpl.isEnabled());
        if(menuitemimpl.hasSubMenu() && mForwardingListener == null)
            mForwardingListener = new ActionMenuItemForwardingListener();
    }

    public boolean needsDividerAfter()
    {
        return hasText();
    }

    public boolean needsDividerBefore()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(hasText())
        {
            flag1 = flag;
            if(mItemData.getIcon() == null)
                flag1 = true;
        }
        return flag1;
    }

    public void onClick(View view)
    {
        if(mItemInvoker != null)
            mItemInvoker.invokeItem(mItemData);
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        mAllowTextWithIcon = shouldAllowTextWithIcon();
        updateTextButtonVisibility();
    }

    protected void onMeasure(int i, int j)
    {
        boolean flag = hasText();
        if(flag && mSavedPaddingLeft >= 0)
            super.setPadding(mSavedPaddingLeft, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        super.onMeasure(i, j);
        int k = android.view.View.MeasureSpec.getMode(i);
        i = android.view.View.MeasureSpec.getSize(i);
        int l = getMeasuredWidth();
        if(k == 0x80000000)
            i = Math.min(i, mMinWidth);
        else
            i = mMinWidth;
        if(k != 0x40000000 && mMinWidth > 0 && l < i)
            super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(i, 0x40000000), j);
        if(!flag && mIcon != null)
            super.setPadding((getMeasuredWidth() - mIcon.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    public void onPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        super.onPopulateAccessibilityEventInternal(accessibilityevent);
        CharSequence charsequence = getContentDescription();
        if(!TextUtils.isEmpty(charsequence))
            accessibilityevent.getText().add(charsequence);
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        super.onRestoreInstanceState(null);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(mItemData.hasSubMenu() && mForwardingListener != null && mForwardingListener.onTouch(this, motionevent))
            return true;
        else
            return super.onTouchEvent(motionevent);
    }

    public boolean prefersCondensedTitle()
    {
        return true;
    }

    public void setCheckable(boolean flag)
    {
    }

    public void setChecked(boolean flag)
    {
    }

    public void setExpandedFormat(boolean flag)
    {
        if(mExpandedFormat != flag)
        {
            mExpandedFormat = flag;
            if(mItemData != null)
                mItemData.actionFormatChanged();
        }
    }

    public void setIcon(Drawable drawable)
    {
        mIcon = drawable;
        if(drawable != null)
        {
            int i = drawable.getIntrinsicWidth();
            int j = drawable.getIntrinsicHeight();
            int k = j;
            int l = i;
            if(i > mMaxIconSize)
            {
                float f = (float)mMaxIconSize / (float)i;
                l = mMaxIconSize;
                k = (int)((float)j * f);
            }
            i = k;
            j = l;
            if(k > mMaxIconSize)
            {
                float f1 = (float)mMaxIconSize / (float)k;
                i = mMaxIconSize;
                j = (int)((float)l * f1);
            }
            drawable.setBounds(0, 0, j, i);
        }
        setCompoundDrawables(drawable, null, null, null);
        updateTextButtonVisibility();
    }

    public void setItemInvoker(MenuBuilder.ItemInvoker iteminvoker)
    {
        mItemInvoker = iteminvoker;
    }

    public void setPadding(int i, int j, int k, int l)
    {
        mSavedPaddingLeft = i;
        super.setPadding(i, j, k, l);
    }

    public void setPopupCallback(PopupCallback popupcallback)
    {
        mPopupCallback = popupcallback;
    }

    public void setShortcut(boolean flag, char c)
    {
    }

    public void setTitle(CharSequence charsequence)
    {
        mTitle = charsequence;
        updateTextButtonVisibility();
    }

    public boolean showsIcon()
    {
        return true;
    }

    private static final int MAX_ICON_SIZE = 32;
    private static final String TAG = "ActionMenuItemView";
    private boolean mAllowTextWithIcon;
    private boolean mExpandedFormat;
    private ForwardingListener mForwardingListener;
    private Drawable mIcon;
    private MenuItemImpl mItemData;
    private MenuBuilder.ItemInvoker mItemInvoker;
    private int mMaxIconSize;
    private int mMinWidth;
    private PopupCallback mPopupCallback;
    private int mSavedPaddingLeft;
    private CharSequence mTitle;
}
