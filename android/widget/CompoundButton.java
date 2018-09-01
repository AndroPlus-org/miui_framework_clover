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
import android.util.Log;
import android.view.ViewHierarchyEncoder;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;

// Referenced classes of package android.widget:
//            Button, Checkable

public abstract class CompoundButton extends Button
    implements Checkable
{
    public static interface OnCheckedChangeListener
    {

        public abstract void onCheckedChanged(CompoundButton compoundbutton, boolean flag);
    }

    static class SavedState extends android.view.View.BaseSavedState
    {

        public String toString()
        {
            return (new StringBuilder()).append("CompoundButton.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" checked=").append(checked).append("}").toString();
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


    public CompoundButton(Context context)
    {
        this(context, null);
    }

    public CompoundButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public CompoundButton(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public CompoundButton(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mButtonTintList = null;
        mButtonTintMode = null;
        mHasButtonTint = false;
        mHasButtonTintMode = false;
        mCheckedFromResource = false;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.CompoundButton, i, j);
        attributeset = context.getDrawable(1);
        if(attributeset != null)
            setButtonDrawable(attributeset);
        if(context.hasValue(3))
        {
            mButtonTintMode = Drawable.parseTintMode(context.getInt(3, -1), mButtonTintMode);
            mHasButtonTintMode = true;
        }
        if(context.hasValue(2))
        {
            mButtonTintList = context.getColorStateList(2);
            mHasButtonTint = true;
        }
        setChecked(context.getBoolean(0, false));
        mCheckedFromResource = true;
        context.recycle();
        applyButtonTint();
    }

    private void applyButtonTint()
    {
        if(mButtonDrawable != null && (mHasButtonTint || mHasButtonTintMode))
        {
            mButtonDrawable = mButtonDrawable.mutate();
            if(mHasButtonTint)
                mButtonDrawable.setTintList(mButtonTintList);
            if(mHasButtonTintMode)
                mButtonDrawable.setTintMode(mButtonTintMode);
            if(mButtonDrawable.isStateful())
                mButtonDrawable.setState(getDrawableState());
        }
    }

    public void autofill(AutofillValue autofillvalue)
    {
        if(!isEnabled())
            return;
        if(!autofillvalue.isToggle())
        {
            Log.w(LOG_TAG, (new StringBuilder()).append(autofillvalue).append(" could not be autofilled into ").append(this).toString());
            return;
        } else
        {
            setChecked(autofillvalue.getToggleValue());
            return;
        }
    }

    public void drawableHotspotChanged(float f, float f1)
    {
        super.drawableHotspotChanged(f, f1);
        if(mButtonDrawable != null)
            mButtonDrawable.setHotspot(f, f1);
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        Drawable drawable = mButtonDrawable;
        if(drawable != null && drawable.isStateful() && drawable.setState(getDrawableState()))
            invalidateDrawable(drawable);
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        super.encodeProperties(viewhierarchyencoder);
        viewhierarchyencoder.addProperty("checked", isChecked());
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/CompoundButton.getName();
    }

    public int getAutofillType()
    {
        byte byte0;
        if(isEnabled())
            byte0 = 2;
        else
            byte0 = 0;
        return byte0;
    }

    public AutofillValue getAutofillValue()
    {
        AutofillValue autofillvalue;
        if(isEnabled())
            autofillvalue = AutofillValue.forToggle(isChecked());
        else
            autofillvalue = null;
        return autofillvalue;
    }

    public Drawable getButtonDrawable()
    {
        return mButtonDrawable;
    }

    public ColorStateList getButtonTintList()
    {
        return mButtonTintList;
    }

    public android.graphics.PorterDuff.Mode getButtonTintMode()
    {
        return mButtonTintMode;
    }

    public int getCompoundPaddingLeft()
    {
        int i = super.getCompoundPaddingLeft();
        int j = i;
        if(!isLayoutRtl())
        {
            Drawable drawable = mButtonDrawable;
            j = i;
            if(drawable != null)
                j = i + drawable.getIntrinsicWidth();
        }
        return j;
    }

    public int getCompoundPaddingRight()
    {
        int i = super.getCompoundPaddingRight();
        int j = i;
        if(isLayoutRtl())
        {
            Drawable drawable = mButtonDrawable;
            j = i;
            if(drawable != null)
                j = i + drawable.getIntrinsicWidth();
        }
        return j;
    }

    public int getHorizontalOffsetForDrawables()
    {
        Drawable drawable = mButtonDrawable;
        int i;
        if(drawable != null)
            i = drawable.getIntrinsicWidth();
        else
            i = 0;
        return i;
    }

    public boolean isChecked()
    {
        return mChecked;
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if(mButtonDrawable != null)
            mButtonDrawable.jumpToCurrentState();
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
        Drawable drawable = mButtonDrawable;
        if(drawable == null) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        int k;
        i = getGravity();
        j = drawable.getIntrinsicHeight();
        k = drawable.getIntrinsicWidth();
        i & 0x70;
        JVM INSTR lookupswitch 2: default 56
    //                   16: 173
    //                   80: 162;
           goto _L3 _L4 _L5
_L4:
        break MISSING_BLOCK_LABEL_173;
_L3:
        i = 0;
_L6:
        int l = i + j;
        Drawable drawable1;
        if(isLayoutRtl())
            j = getWidth() - k;
        else
            j = 0;
        if(isLayoutRtl())
            k = getWidth();
        drawable.setBounds(j, i, k, l);
        drawable1 = getBackground();
        if(drawable1 != null)
            drawable1.setHotspotBounds(j, i, k, l);
_L2:
        super.onDraw(canvas);
        if(drawable != null)
        {
            i = mScrollX;
            j = mScrollY;
            if(i == 0 && j == 0)
            {
                drawable.draw(canvas);
            } else
            {
                canvas.translate(i, j);
                drawable.draw(canvas);
                canvas.translate(-i, -j);
            }
        }
        return;
_L5:
        i = getHeight() - j;
          goto _L6
        i = (getHeight() - j) / 2;
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

    public void onProvideAutofillStructure(ViewStructure viewstructure, int i)
    {
        super.onProvideAutofillStructure(viewstructure, i);
        viewstructure.setDataIsSensitive(mCheckedFromResource ^ true);
    }

    public void onResolveDrawables(int i)
    {
        super.onResolveDrawables(i);
        if(mButtonDrawable != null)
            mButtonDrawable.setLayoutDirection(i);
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        setChecked(((SavedState) (parcelable)).checked);
        requestLayout();
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.checked = isChecked();
        return savedstate;
    }

    public boolean performClick()
    {
        toggle();
        boolean flag = super.performClick();
        if(!flag)
            playSoundEffect(0);
        return flag;
    }

    public void setButtonDrawable(int i)
    {
        Drawable drawable;
        if(i != 0)
            drawable = getContext().getDrawable(i);
        else
            drawable = null;
        setButtonDrawable(drawable);
    }

    public void setButtonDrawable(Drawable drawable)
    {
        if(mButtonDrawable != drawable)
        {
            if(mButtonDrawable != null)
            {
                mButtonDrawable.setCallback(null);
                unscheduleDrawable(mButtonDrawable);
            }
            mButtonDrawable = drawable;
            if(drawable != null)
            {
                drawable.setCallback(this);
                drawable.setLayoutDirection(getLayoutDirection());
                if(drawable.isStateful())
                    drawable.setState(getDrawableState());
                boolean flag;
                if(getVisibility() == 0)
                    flag = true;
                else
                    flag = false;
                drawable.setVisible(flag, false);
                setMinHeight(drawable.getIntrinsicHeight());
                applyButtonTint();
            }
        }
    }

    public void setButtonTintList(ColorStateList colorstatelist)
    {
        mButtonTintList = colorstatelist;
        mHasButtonTint = true;
        applyButtonTint();
    }

    public void setButtonTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mButtonTintMode = mode;
        mHasButtonTintMode = true;
        applyButtonTint();
    }

    public void setChecked(boolean flag)
    {
        if(mChecked != flag)
        {
            mCheckedFromResource = false;
            mChecked = flag;
            refreshDrawableState();
            notifyViewAccessibilityStateChangedIfNeeded(0);
            if(mBroadcasting)
                return;
            mBroadcasting = true;
            if(mOnCheckedChangeListener != null)
                mOnCheckedChangeListener.onCheckedChanged(this, mChecked);
            if(mOnCheckedChangeWidgetListener != null)
                mOnCheckedChangeWidgetListener.onCheckedChanged(this, mChecked);
            AutofillManager autofillmanager = (AutofillManager)mContext.getSystemService(android/view/autofill/AutofillManager);
            if(autofillmanager != null)
                autofillmanager.notifyValueChanged(this);
            mBroadcasting = false;
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener oncheckedchangelistener)
    {
        mOnCheckedChangeListener = oncheckedchangelistener;
    }

    void setOnCheckedChangeWidgetListener(OnCheckedChangeListener oncheckedchangelistener)
    {
        mOnCheckedChangeWidgetListener = oncheckedchangelistener;
    }

    public void toggle()
    {
        setChecked(mChecked ^ true);
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(!super.verifyDrawable(drawable))
            if(drawable == mButtonDrawable)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private static final int CHECKED_STATE_SET[] = {
        0x10100a0
    };
    private static final String LOG_TAG = android/widget/CompoundButton.getSimpleName();
    private boolean mBroadcasting;
    private Drawable mButtonDrawable;
    private ColorStateList mButtonTintList;
    private android.graphics.PorterDuff.Mode mButtonTintMode;
    private boolean mChecked;
    private boolean mCheckedFromResource;
    private boolean mHasButtonTint;
    private boolean mHasButtonTintMode;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private OnCheckedChangeListener mOnCheckedChangeWidgetListener;

}
