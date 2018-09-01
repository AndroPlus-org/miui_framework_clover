// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.*;

// Referenced classes of package com.android.internal.view.menu:
//            MenuItemImpl

public class ListMenuItemView extends LinearLayout
    implements MenuView.ItemView
{

    public ListMenuItemView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x10104f2);
    }

    public ListMenuItemView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ListMenuItemView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.MenuView, i, j);
        mBackground = attributeset.getDrawable(5);
        mTextAppearance = attributeset.getResourceId(1, -1);
        mPreserveIconSpacing = attributeset.getBoolean(8, false);
        mTextAppearanceContext = context;
        mSubMenuArrow = attributeset.getDrawable(7);
        attributeset.recycle();
    }

    private LayoutInflater getInflater()
    {
        if(mInflater == null)
            mInflater = LayoutInflater.from(mContext);
        return mInflater;
    }

    private void insertCheckBox()
    {
        mCheckBox = (CheckBox)getInflater().inflate(0x109007a, this, false);
        addView(mCheckBox);
    }

    private void insertIconView()
    {
        mIconView = (ImageView)getInflater().inflate(0x109007b, this, false);
        addView(mIconView, 0);
    }

    private void insertRadioButton()
    {
        mRadioButton = (RadioButton)getInflater().inflate(0x109007d, this, false);
        addView(mRadioButton);
    }

    private void setSubMenuArrowVisible(boolean flag)
    {
        if(mSubMenuArrowView != null)
        {
            ImageView imageview = mSubMenuArrowView;
            int i;
            if(flag)
                i = 0;
            else
                i = 8;
            imageview.setVisibility(i);
        }
    }

    public MenuItemImpl getItemData()
    {
        return mItemData;
    }

    public void initialize(MenuItemImpl menuitemimpl, int i)
    {
        mItemData = menuitemimpl;
        mMenuType = i;
        if(menuitemimpl.isVisible())
            i = 0;
        else
            i = 8;
        setVisibility(i);
        setTitle(menuitemimpl.getTitleForItemView(this));
        setCheckable(menuitemimpl.isCheckable());
        setShortcut(menuitemimpl.shouldShowShortcut(), menuitemimpl.getShortcut());
        setIcon(menuitemimpl.getIcon());
        setEnabled(menuitemimpl.isEnabled());
        setSubMenuArrowVisible(menuitemimpl.hasSubMenu());
        setContentDescription(menuitemimpl.getContentDescription());
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        setBackgroundDrawable(mBackground);
        mTitleView = (TextView)findViewById(0x1020016);
        if(mTextAppearance != -1)
            mTitleView.setTextAppearance(mTextAppearanceContext, mTextAppearance);
        mShortcutView = (TextView)findViewById(0x10203e9);
        mSubMenuArrowView = (ImageView)findViewById(0x1020425);
        if(mSubMenuArrowView != null)
            mSubMenuArrowView.setImageDrawable(mSubMenuArrow);
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        if(mItemData != null && mItemData.hasSubMenu())
            accessibilitynodeinfo.setCanOpenPopup(true);
    }

    protected void onMeasure(int i, int j)
    {
        if(mIconView != null && mPreserveIconSpacing)
        {
            android.view.ViewGroup.LayoutParams layoutparams = getLayoutParams();
            android.widget.LinearLayout.LayoutParams layoutparams1 = (android.widget.LinearLayout.LayoutParams)mIconView.getLayoutParams();
            if(layoutparams.height > 0 && layoutparams1.width <= 0)
                layoutparams1.width = layoutparams.height;
        }
        super.onMeasure(i, j);
    }

    public boolean prefersCondensedTitle()
    {
        return false;
    }

    public void setCheckable(boolean flag)
    {
        if(!flag && mRadioButton == null && mCheckBox == null)
            return;
        Object obj;
        Object obj1;
        if(mItemData.isExclusiveCheckable())
        {
            if(mRadioButton == null)
                insertRadioButton();
            obj = mRadioButton;
            obj1 = mCheckBox;
        } else
        {
            if(mCheckBox == null)
                insertCheckBox();
            obj = mCheckBox;
            obj1 = mRadioButton;
        }
        if(!flag) goto _L2; else goto _L1
_L1:
        ((CompoundButton) (obj)).setChecked(mItemData.isChecked());
        int i;
        if(flag)
            i = 0;
        else
            i = 8;
        if(((CompoundButton) (obj)).getVisibility() != i)
            ((CompoundButton) (obj)).setVisibility(i);
        if(obj1 != null && ((CompoundButton) (obj1)).getVisibility() != 8)
            ((CompoundButton) (obj1)).setVisibility(8);
_L4:
        return;
_L2:
        if(mCheckBox != null)
            mCheckBox.setVisibility(8);
        if(mRadioButton != null)
            mRadioButton.setVisibility(8);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setChecked(boolean flag)
    {
        Object obj;
        if(mItemData.isExclusiveCheckable())
        {
            if(mRadioButton == null)
                insertRadioButton();
            obj = mRadioButton;
        } else
        {
            if(mCheckBox == null)
                insertCheckBox();
            obj = mCheckBox;
        }
        ((CompoundButton) (obj)).setChecked(flag);
    }

    public void setForceShowIcon(boolean flag)
    {
        mForceShowIcon = flag;
        mPreserveIconSpacing = flag;
    }

    public void setIcon(Drawable drawable)
    {
        boolean flag;
        if(!mItemData.shouldShowIcon())
            flag = mForceShowIcon;
        else
            flag = true;
        if(!flag && mPreserveIconSpacing ^ true)
            return;
        if(mIconView == null && drawable == null && mPreserveIconSpacing ^ true)
            return;
        if(mIconView == null)
            insertIconView();
        if(drawable != null || mPreserveIconSpacing)
        {
            ImageView imageview = mIconView;
            if(!flag)
                drawable = null;
            imageview.setImageDrawable(drawable);
            if(mIconView.getVisibility() != 0)
                mIconView.setVisibility(0);
        } else
        {
            mIconView.setVisibility(8);
        }
    }

    public void setShortcut(boolean flag, char c)
    {
        if(flag && mItemData.shouldShowShortcut())
            c = '\0';
        else
            c = '\b';
        if(c == 0)
            mShortcutView.setText(mItemData.getShortcutLabel());
        if(mShortcutView.getVisibility() != c)
            mShortcutView.setVisibility(c);
    }

    public void setTitle(CharSequence charsequence)
    {
        if(charsequence == null) goto _L2; else goto _L1
_L1:
        mTitleView.setText(charsequence);
        if(mTitleView.getVisibility() != 0)
            mTitleView.setVisibility(0);
_L4:
        return;
_L2:
        if(mTitleView.getVisibility() != 8)
            mTitleView.setVisibility(8);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean showsIcon()
    {
        return mForceShowIcon;
    }

    private static final String TAG = "ListMenuItemView";
    private Drawable mBackground;
    private CheckBox mCheckBox;
    private boolean mForceShowIcon;
    private ImageView mIconView;
    private LayoutInflater mInflater;
    private MenuItemImpl mItemData;
    private int mMenuType;
    private boolean mPreserveIconSpacing;
    private RadioButton mRadioButton;
    private TextView mShortcutView;
    private Drawable mSubMenuArrow;
    private ImageView mSubMenuArrowView;
    private int mTextAppearance;
    private Context mTextAppearanceContext;
    private TextView mTitleView;
}
