// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewHierarchyEncoder;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package android.widget:
//            TextView, Checkable

public class CheckedTextView extends TextView
    implements Checkable
{
    static class SavedState extends android.view.View.BaseSavedState
    {

        public String toString()
        {
            return (new StringBuilder()).append("CheckedTextView.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" checked=").append(checked).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(checked));
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        boolean checked;


        private SavedState(Parcel parcel)
        {
            super(parcel);
            checked = ((Boolean)parcel.readValue(null)).booleanValue();
        }

        SavedState(Parcel parcel, SavedState savedstate)
        {
            this(parcel);
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    public CheckedTextView(Context context)
    {
        this(context, null);
    }

    public CheckedTextView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x10103c8);
    }

    public CheckedTextView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public CheckedTextView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mCheckMarkTintList = null;
        mCheckMarkTintMode = null;
        mHasCheckMarkTint = false;
        mHasCheckMarkTintMode = false;
        mCheckMarkGravity = 0x800005;
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.CheckedTextView, i, j);
        context = attributeset.getDrawable(1);
        if(context != null)
            setCheckMarkDrawable(context);
        if(attributeset.hasValue(3))
        {
            mCheckMarkTintMode = Drawable.parseTintMode(attributeset.getInt(3, -1), mCheckMarkTintMode);
            mHasCheckMarkTintMode = true;
        }
        if(attributeset.hasValue(2))
        {
            mCheckMarkTintList = attributeset.getColorStateList(2);
            mHasCheckMarkTint = true;
        }
        mCheckMarkGravity = attributeset.getInt(4, 0x800005);
        setChecked(attributeset.getBoolean(0, false));
        attributeset.recycle();
        applyCheckMarkTint();
    }

    private void applyCheckMarkTint()
    {
        if(mCheckMarkDrawable != null && (mHasCheckMarkTint || mHasCheckMarkTintMode))
        {
            mCheckMarkDrawable = mCheckMarkDrawable.mutate();
            if(mHasCheckMarkTint)
                mCheckMarkDrawable.setTintList(mCheckMarkTintList);
            if(mHasCheckMarkTintMode)
                mCheckMarkDrawable.setTintMode(mCheckMarkTintMode);
            if(mCheckMarkDrawable.isStateful())
                mCheckMarkDrawable.setState(getDrawableState());
        }
    }

    private boolean isCheckMarkAtStart()
    {
        boolean flag;
        if((Gravity.getAbsoluteGravity(mCheckMarkGravity, getLayoutDirection()) & 7) == 3)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void setBasePadding(boolean flag)
    {
        if(flag)
            mBasePadding = mPaddingLeft;
        else
            mBasePadding = mPaddingRight;
    }

    private void setCheckMarkDrawableInternal(Drawable drawable, int i)
    {
        boolean flag = true;
        if(mCheckMarkDrawable != null)
        {
            mCheckMarkDrawable.setCallback(null);
            unscheduleDrawable(mCheckMarkDrawable);
        }
        boolean flag1;
        if(drawable != mCheckMarkDrawable)
            flag1 = true;
        else
            flag1 = false;
        mNeedRequestlayout = flag1;
        if(drawable != null)
        {
            drawable.setCallback(this);
            if(getVisibility() == 0)
                flag1 = flag;
            else
                flag1 = false;
            drawable.setVisible(flag1, false);
            drawable.setState(CHECKED_STATE_SET);
            setMinHeight(drawable.getIntrinsicHeight());
            mCheckMarkWidth = drawable.getIntrinsicWidth();
            drawable.setState(getDrawableState());
        } else
        {
            mCheckMarkWidth = 0;
        }
        mCheckMarkDrawable = drawable;
        mCheckMarkResource = i;
        applyCheckMarkTint();
        resolvePadding();
    }

    private void updatePadding()
    {
        boolean flag = true;
        boolean flag1 = true;
        resetPaddingToInitialValues();
        int i;
        if(mCheckMarkDrawable != null)
            i = mCheckMarkWidth + mBasePadding;
        else
            i = mBasePadding;
        if(isCheckMarkAtStart())
        {
            boolean flag3 = mNeedRequestlayout;
            if(mPaddingLeft == i)
                flag1 = false;
            mNeedRequestlayout = flag1 | flag3;
            mPaddingLeft = i;
        } else
        {
            boolean flag4 = mNeedRequestlayout;
            boolean flag2;
            if(mPaddingRight != i)
                flag2 = flag;
            else
                flag2 = false;
            mNeedRequestlayout = flag2 | flag4;
            mPaddingRight = i;
        }
        if(mNeedRequestlayout)
        {
            requestLayout();
            mNeedRequestlayout = false;
        }
    }

    public void drawableHotspotChanged(float f, float f1)
    {
        super.drawableHotspotChanged(f, f1);
        if(mCheckMarkDrawable != null)
            mCheckMarkDrawable.setHotspot(f, f1);
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        Drawable drawable = mCheckMarkDrawable;
        if(drawable != null && drawable.isStateful() && drawable.setState(getDrawableState()))
            invalidateDrawable(drawable);
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        super.encodeProperties(viewhierarchyencoder);
        viewhierarchyencoder.addProperty("text:checked", isChecked());
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/CheckedTextView.getName();
    }

    public Drawable getCheckMarkDrawable()
    {
        return mCheckMarkDrawable;
    }

    public ColorStateList getCheckMarkTintList()
    {
        return mCheckMarkTintList;
    }

    public android.graphics.PorterDuff.Mode getCheckMarkTintMode()
    {
        return mCheckMarkTintMode;
    }

    protected void internalSetPadding(int i, int j, int k, int l)
    {
        super.internalSetPadding(i, j, k, l);
        setBasePadding(isCheckMarkAtStart());
    }

    public boolean isChecked()
    {
        return mChecked;
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if(mCheckMarkDrawable != null)
            mCheckMarkDrawable.jumpToCurrentState();
    }

    protected int[] onCreateDrawableState(int i)
    {
        int ai[] = super.onCreateDrawableState(i + 1);
        if(isChecked())
            mergeDrawableStates(ai, CHECKED_STATE_SET);
        return ai;
    }

    protected void onDraw(Canvas canvas)
    {
        Drawable drawable;
        super.onDraw(canvas);
        drawable = mCheckMarkDrawable;
        if(drawable == null) goto _L2; else goto _L1
_L1:
        int i;
        int k;
        int l;
        i = getGravity();
        k = drawable.getIntrinsicHeight();
        l = 0;
        i & 0x70;
        JVM INSTR lookupswitch 2: default 60
    //                   16: 166
    //                   80: 154;
           goto _L3 _L4 _L5
_L3:
        break; /* Loop/switch isn't completed */
_L4:
        break MISSING_BLOCK_LABEL_166;
_L6:
        boolean flag = isCheckMarkAtStart();
        int j = getWidth();
        int i1 = l + k;
        if(flag)
        {
            k = mBasePadding;
            j = k + mCheckMarkWidth;
        } else
        {
            j -= mBasePadding;
            k = j - mCheckMarkWidth;
        }
        drawable.setBounds(mScrollX + k, l, mScrollX + j, i1);
        drawable.draw(canvas);
        canvas = getBackground();
        if(canvas != null)
            canvas.setHotspotBounds(mScrollX + k, l, mScrollX + j, i1);
_L2:
        return;
_L5:
        l = getHeight() - k;
          goto _L6
        l = (getHeight() - k) / 2;
          goto _L6
    }

    public void onInitializeAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEventInternal(accessibilityevent);
        accessibilityevent.setChecked(mChecked);
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        accessibilitynodeinfo.setCheckable(true);
        accessibilitynodeinfo.setChecked(mChecked);
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        setChecked(((SavedState) (parcelable)).checked);
        requestLayout();
    }

    public void onRtlPropertiesChanged(int i)
    {
        super.onRtlPropertiesChanged(i);
        updatePadding();
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.checked = isChecked();
        return savedstate;
    }

    public void setCheckMarkDrawable(int i)
    {
        if(i != 0 && i == mCheckMarkResource)
            return;
        Drawable drawable;
        if(i != 0)
            drawable = getContext().getDrawable(i);
        else
            drawable = null;
        setCheckMarkDrawableInternal(drawable, i);
    }

    public void setCheckMarkDrawable(Drawable drawable)
    {
        setCheckMarkDrawableInternal(drawable, 0);
    }

    public void setCheckMarkTintList(ColorStateList colorstatelist)
    {
        mCheckMarkTintList = colorstatelist;
        mHasCheckMarkTint = true;
        applyCheckMarkTint();
    }

    public void setCheckMarkTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mCheckMarkTintMode = mode;
        mHasCheckMarkTintMode = true;
        applyCheckMarkTint();
    }

    public void setChecked(boolean flag)
    {
        if(mChecked != flag)
        {
            mChecked = flag;
            refreshDrawableState();
            notifyViewAccessibilityStateChangedIfNeeded(0);
        }
    }

    public void setVisibility(int i)
    {
        super.setVisibility(i);
        if(mCheckMarkDrawable != null)
        {
            Drawable drawable = mCheckMarkDrawable;
            boolean flag;
            if(i == 0)
                flag = true;
            else
                flag = false;
            drawable.setVisible(flag, false);
        }
    }

    public void toggle()
    {
        setChecked(mChecked ^ true);
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        boolean flag;
        if(drawable != mCheckMarkDrawable)
            flag = super.verifyDrawable(drawable);
        else
            flag = true;
        return flag;
    }

    private static final int CHECKED_STATE_SET[] = {
        0x10100a0
    };
    private int mBasePadding;
    private Drawable mCheckMarkDrawable;
    private int mCheckMarkGravity;
    private int mCheckMarkResource;
    private ColorStateList mCheckMarkTintList;
    private android.graphics.PorterDuff.Mode mCheckMarkTintMode;
    private int mCheckMarkWidth;
    private boolean mChecked;
    private boolean mHasCheckMarkTint;
    private boolean mHasCheckMarkTintMode;
    private boolean mNeedRequestlayout;

}
