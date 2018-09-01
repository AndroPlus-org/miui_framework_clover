// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.text.*;
import android.text.method.AllCapsTransformationMethod;
import android.text.method.TransformationMethod2;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;

// Referenced classes of package android.widget:
//            CompoundButton

public class Switch extends CompoundButton
{

    static float _2D_get0(Switch switch1)
    {
        return switch1.mThumbPosition;
    }

    static void _2D_wrap0(Switch switch1, float f)
    {
        switch1.setThumbPosition(f);
    }

    public Switch(Context context)
    {
        this(context, null);
    }

    public Switch(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101043f);
    }

    public Switch(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public Switch(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mThumbTintList = null;
        mThumbTintMode = null;
        mHasThumbTint = false;
        mHasThumbTintMode = false;
        mTrackTintList = null;
        mTrackTintMode = null;
        mHasTrackTint = false;
        mHasTrackTintMode = false;
        mVelocityTracker = VelocityTracker.obtain();
        mTempRect = new Rect();
        mTextPaint = new TextPaint(1);
        Object obj = getResources();
        mTextPaint.density = ((Resources) (obj)).getDisplayMetrics().density;
        mTextPaint.setCompatibilityScaling(((Resources) (obj)).getCompatibilityInfo().applicationScale);
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Switch, i, j);
        mThumbDrawable = attributeset.getDrawable(2);
        if(mThumbDrawable != null)
            mThumbDrawable.setCallback(this);
        mTrackDrawable = attributeset.getDrawable(4);
        if(mTrackDrawable != null)
            mTrackDrawable.setCallback(this);
        mTextOn = attributeset.getText(0);
        mTextOff = attributeset.getText(1);
        mShowText = attributeset.getBoolean(11, true);
        mThumbTextPadding = attributeset.getDimensionPixelSize(7, 0);
        mSwitchMinWidth = attributeset.getDimensionPixelSize(5, 0);
        mSwitchPadding = attributeset.getDimensionPixelSize(6, 0);
        mSplitTrack = attributeset.getBoolean(8, false);
        obj = attributeset.getColorStateList(9);
        if(obj != null)
        {
            mThumbTintList = ((ColorStateList) (obj));
            mHasThumbTint = true;
        }
        obj = Drawable.parseTintMode(attributeset.getInt(10, -1), null);
        if(mThumbTintMode != obj)
        {
            mThumbTintMode = ((android.graphics.PorterDuff.Mode) (obj));
            mHasThumbTintMode = true;
        }
        if(mHasThumbTint || mHasThumbTintMode)
            applyThumbTint();
        obj = attributeset.getColorStateList(12);
        if(obj != null)
        {
            mTrackTintList = ((ColorStateList) (obj));
            mHasTrackTint = true;
        }
        obj = Drawable.parseTintMode(attributeset.getInt(13, -1), null);
        if(mTrackTintMode != obj)
        {
            mTrackTintMode = ((android.graphics.PorterDuff.Mode) (obj));
            mHasTrackTintMode = true;
        }
        if(mHasTrackTint || mHasTrackTintMode)
            applyTrackTint();
        i = attributeset.getResourceId(3, 0);
        if(i != 0)
            setSwitchTextAppearance(context, i);
        attributeset.recycle();
        context = ViewConfiguration.get(context);
        mTouchSlop = context.getScaledTouchSlop();
        mMinFlingVelocity = context.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }

    private void animateThumbToCheckedState(boolean flag)
    {
        int i;
        float f;
        if(flag)
            i = 1;
        else
            i = 0;
        f = i;
        mPositionAnimator = ObjectAnimator.ofFloat(this, THUMB_POS, new float[] {
            f
        });
        mPositionAnimator.setDuration(250L);
        mPositionAnimator.setAutoCancel(true);
        mPositionAnimator.start();
    }

    private void applyThumbTint()
    {
        if(mThumbDrawable != null && (mHasThumbTint || mHasThumbTintMode))
        {
            mThumbDrawable = mThumbDrawable.mutate();
            if(mHasThumbTint)
                mThumbDrawable.setTintList(mThumbTintList);
            if(mHasThumbTintMode)
                mThumbDrawable.setTintMode(mThumbTintMode);
            if(mThumbDrawable.isStateful())
                mThumbDrawable.setState(getDrawableState());
        }
    }

    private void applyTrackTint()
    {
        if(mTrackDrawable != null && (mHasTrackTint || mHasTrackTintMode))
        {
            mTrackDrawable = mTrackDrawable.mutate();
            if(mHasTrackTint)
                mTrackDrawable.setTintList(mTrackTintList);
            if(mHasTrackTintMode)
                mTrackDrawable.setTintMode(mTrackTintMode);
            if(mTrackDrawable.isStateful())
                mTrackDrawable.setState(getDrawableState());
        }
    }

    private void cancelPositionAnimator()
    {
        if(mPositionAnimator != null)
            mPositionAnimator.cancel();
    }

    private void cancelSuperTouch(MotionEvent motionevent)
    {
        motionevent = MotionEvent.obtain(motionevent);
        motionevent.setAction(3);
        super.onTouchEvent(motionevent);
        motionevent.recycle();
    }

    private boolean getTargetCheckedState()
    {
        boolean flag;
        if(mThumbPosition > 0.5F)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private int getThumbOffset()
    {
        float f;
        if(isLayoutRtl())
            f = 1.0F - mThumbPosition;
        else
            f = mThumbPosition;
        return (int)((float)getThumbScrollRange() * f + 0.5F);
    }

    private int getThumbScrollRange()
    {
        if(mTrackDrawable != null)
        {
            Rect rect = mTempRect;
            mTrackDrawable.getPadding(rect);
            Insets insets;
            if(mThumbDrawable != null)
                insets = mThumbDrawable.getOpticalInsets();
            else
                insets = Insets.NONE;
            return mSwitchWidth - mThumbWidth - rect.left - rect.right - insets.left - insets.right;
        } else
        {
            return 0;
        }
    }

    private boolean hitThumb(float f, float f1)
    {
        boolean flag = false;
        if(mThumbDrawable == null)
            return false;
        int i = getThumbOffset();
        mThumbDrawable.getPadding(mTempRect);
        int j = mSwitchTop;
        int k = mTouchSlop;
        int l = (mSwitchLeft + i) - mTouchSlop;
        int i1 = mThumbWidth;
        int j1 = mTempRect.left;
        i = mTempRect.right;
        int k1 = mTouchSlop;
        int l1 = mSwitchBottom;
        int i2 = mTouchSlop;
        boolean flag1 = flag;
        if(f > (float)l)
        {
            flag1 = flag;
            if(f < (float)(i1 + l + j1 + i + k1))
            {
                flag1 = flag;
                if(f1 > (float)(j - k))
                {
                    flag1 = flag;
                    if(f1 < (float)(l1 + i2))
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    private Layout makeLayout(CharSequence charsequence)
    {
        if(mSwitchTransformationMethod != null)
            charsequence = mSwitchTransformationMethod.getTransformation(charsequence, this);
        int i = (int)Math.ceil(Layout.getDesiredWidth(charsequence, 0, charsequence.length(), mTextPaint, getTextDirectionHeuristic()));
        return new StaticLayout(charsequence, mTextPaint, i, android.text.Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
    }

    private void onProvideAutoFillStructureForAssistOrAutofill(ViewStructure viewstructure)
    {
        CharSequence charsequence;
        if(isChecked())
            charsequence = mTextOn;
        else
            charsequence = mTextOff;
        if(!TextUtils.isEmpty(charsequence))
        {
            CharSequence charsequence1 = viewstructure.getText();
            if(TextUtils.isEmpty(charsequence1))
            {
                viewstructure.setText(charsequence);
            } else
            {
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append(charsequence1).append(' ').append(charsequence);
                viewstructure.setText(stringbuilder);
            }
        }
    }

    private void setSwitchTypefaceByIndex(int i, int j)
    {
        Typeface typeface = null;
        i;
        JVM INSTR tableswitch 1 3: default 28
    //                   1 35
    //                   2 42
    //                   3 49;
           goto _L1 _L2 _L3 _L4
_L1:
        setSwitchTypeface(typeface, j);
        return;
_L2:
        typeface = Typeface.SANS_SERIF;
        continue; /* Loop/switch isn't completed */
_L3:
        typeface = Typeface.SERIF;
        continue; /* Loop/switch isn't completed */
_L4:
        typeface = Typeface.MONOSPACE;
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void setThumbPosition(float f)
    {
        mThumbPosition = f;
        invalidate();
    }

    private void stopDrag(MotionEvent motionevent)
    {
        mTouchMode = 0;
        boolean flag;
        boolean flag1;
        if(motionevent.getAction() == 1)
            flag = isEnabled();
        else
            flag = false;
        flag1 = isChecked();
        if(flag)
        {
            mVelocityTracker.computeCurrentVelocity(1000);
            float f = mVelocityTracker.getXVelocity();
            if(Math.abs(f) > (float)mMinFlingVelocity)
            {
                if(isLayoutRtl() ? f < 0.0F : f > 0.0F)
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = getTargetCheckedState();
            }
        } else
        {
            flag = flag1;
        }
        if(flag != flag1)
            playSoundEffect(0);
        setChecked(flag);
        cancelSuperTouch(motionevent);
    }

    public void draw(Canvas canvas)
    {
        Rect rect = mTempRect;
        int i = mSwitchLeft;
        int j = mSwitchTop;
        int k = mSwitchRight;
        int l = mSwitchBottom;
        int i1 = i + getThumbOffset();
        Object obj;
        int k1;
        if(mThumbDrawable != null)
            obj = mThumbDrawable.getOpticalInsets();
        else
            obj = Insets.NONE;
        k1 = i1;
        if(mTrackDrawable != null)
        {
            mTrackDrawable.getPadding(rect);
            int l1 = i1 + rect.left;
            k1 = i;
            int i2 = j;
            i1 = k;
            int j2 = l;
            int k2 = j2;
            int l2 = k1;
            int i3 = i1;
            int j3 = i2;
            if(obj != Insets.NONE)
            {
                if(((Insets) (obj)).left > rect.left)
                    k1 = i + (((Insets) (obj)).left - rect.left);
                if(((Insets) (obj)).top > rect.top)
                    i2 = j + (((Insets) (obj)).top - rect.top);
                if(((Insets) (obj)).right > rect.right)
                    i1 = k - (((Insets) (obj)).right - rect.right);
                k2 = j2;
                l2 = k1;
                i3 = i1;
                j3 = i2;
                if(((Insets) (obj)).bottom > rect.bottom)
                {
                    k2 = l - (((Insets) (obj)).bottom - rect.bottom);
                    j3 = i2;
                    i3 = i1;
                    l2 = k1;
                }
            }
            mTrackDrawable.setBounds(l2, j3, i3, k2);
            k1 = l1;
        }
        if(mThumbDrawable != null)
        {
            mThumbDrawable.getPadding(rect);
            int j1 = k1 - rect.left;
            k1 = mThumbWidth + k1 + rect.right;
            mThumbDrawable.setBounds(j1, j, k1, l);
            obj = getBackground();
            if(obj != null)
                ((Drawable) (obj)).setHotspotBounds(j1, j, k1, l);
        }
        super.draw(canvas);
    }

    public void drawableHotspotChanged(float f, float f1)
    {
        super.drawableHotspotChanged(f, f1);
        if(mThumbDrawable != null)
            mThumbDrawable.setHotspot(f, f1);
        if(mTrackDrawable != null)
            mTrackDrawable.setHotspot(f, f1);
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        int ai[] = getDrawableState();
        boolean flag = false;
        Drawable drawable = mThumbDrawable;
        boolean flag1 = flag;
        if(drawable != null)
        {
            flag1 = flag;
            if(drawable.isStateful())
                flag1 = drawable.setState(ai);
        }
        drawable = mTrackDrawable;
        flag = flag1;
        if(drawable != null)
        {
            flag = flag1;
            if(drawable.isStateful())
                flag = flag1 | drawable.setState(ai);
        }
        if(flag)
            invalidate();
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/Switch.getName();
    }

    public int getCompoundPaddingLeft()
    {
        if(!isLayoutRtl())
            return super.getCompoundPaddingLeft();
        int i = super.getCompoundPaddingLeft() + mSwitchWidth;
        int j = i;
        if(!TextUtils.isEmpty(getText()))
            j = i + mSwitchPadding;
        return j;
    }

    public int getCompoundPaddingRight()
    {
        if(isLayoutRtl())
            return super.getCompoundPaddingRight();
        int i = super.getCompoundPaddingRight() + mSwitchWidth;
        int j = i;
        if(!TextUtils.isEmpty(getText()))
            j = i + mSwitchPadding;
        return j;
    }

    public boolean getShowText()
    {
        return mShowText;
    }

    public boolean getSplitTrack()
    {
        return mSplitTrack;
    }

    public int getSwitchMinWidth()
    {
        return mSwitchMinWidth;
    }

    public int getSwitchPadding()
    {
        return mSwitchPadding;
    }

    public CharSequence getTextOff()
    {
        return mTextOff;
    }

    public CharSequence getTextOn()
    {
        return mTextOn;
    }

    public Drawable getThumbDrawable()
    {
        return mThumbDrawable;
    }

    public int getThumbTextPadding()
    {
        return mThumbTextPadding;
    }

    public ColorStateList getThumbTintList()
    {
        return mThumbTintList;
    }

    public android.graphics.PorterDuff.Mode getThumbTintMode()
    {
        return mThumbTintMode;
    }

    public Drawable getTrackDrawable()
    {
        return mTrackDrawable;
    }

    public ColorStateList getTrackTintList()
    {
        return mTrackTintList;
    }

    public android.graphics.PorterDuff.Mode getTrackTintMode()
    {
        return mTrackTintMode;
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if(mThumbDrawable != null)
            mThumbDrawable.jumpToCurrentState();
        if(mTrackDrawable != null)
            mTrackDrawable.jumpToCurrentState();
        if(mPositionAnimator != null && mPositionAnimator.isStarted())
        {
            mPositionAnimator.end();
            mPositionAnimator = null;
        }
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
        super.onDraw(canvas);
        Object obj = mTempRect;
        Drawable drawable = mTrackDrawable;
        int i;
        int j;
        int k;
        int l;
        Object obj1;
        int k1;
        if(drawable != null)
            drawable.getPadding(((Rect) (obj)));
        else
            ((Rect) (obj)).setEmpty();
        i = mSwitchTop;
        j = mSwitchBottom;
        k = ((Rect) (obj)).top;
        l = ((Rect) (obj)).bottom;
        obj1 = mThumbDrawable;
        if(drawable != null)
            if(mSplitTrack && obj1 != null)
            {
                Insets insets = ((Drawable) (obj1)).getOpticalInsets();
                ((Drawable) (obj1)).copyBounds(((Rect) (obj)));
                obj.left = ((Rect) (obj)).left + insets.left;
                obj.right = ((Rect) (obj)).right - insets.right;
                int i1 = canvas.save();
                canvas.clipRect(((Rect) (obj)), android.graphics.Region.Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(i1);
            } else
            {
                drawable.draw(canvas);
            }
        k1 = canvas.save();
        if(obj1 != null)
            ((Drawable) (obj1)).draw(canvas);
        if(getTargetCheckedState())
            obj = mOnLayout;
        else
            obj = mOffLayout;
        if(obj != null)
        {
            int ai[] = getDrawableState();
            if(mTextColors != null)
                mTextPaint.setColor(mTextColors.getColorForState(ai, 0));
            mTextPaint.drawableState = ai;
            int j1;
            int l1;
            if(obj1 != null)
            {
                obj1 = ((Drawable) (obj1)).getBounds();
                j1 = ((Rect) (obj1)).left + ((Rect) (obj1)).right;
            } else
            {
                j1 = getWidth();
            }
            l1 = j1 / 2;
            j1 = ((Layout) (obj)).getWidth() / 2;
            i = (i + k + (j - l)) / 2;
            j = ((Layout) (obj)).getHeight() / 2;
            canvas.translate(l1 - j1, i - j);
            ((Layout) (obj)).draw(canvas);
        }
        canvas.restoreToCount(k1);
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        CharSequence charsequence;
        if(isChecked())
            charsequence = mTextOn;
        else
            charsequence = mTextOff;
        if(!TextUtils.isEmpty(charsequence))
        {
            CharSequence charsequence1 = accessibilitynodeinfo.getText();
            if(TextUtils.isEmpty(charsequence1))
            {
                accessibilitynodeinfo.setText(charsequence);
            } else
            {
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append(charsequence1).append(' ').append(charsequence);
                accessibilitynodeinfo.setText(stringbuilder);
            }
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        j = 0;
        i = 0;
        if(mThumbDrawable != null)
        {
            Rect rect = mTempRect;
            Insets insets;
            if(mTrackDrawable != null)
                mTrackDrawable.getPadding(rect);
            else
                rect.setEmpty();
            insets = mThumbDrawable.getOpticalInsets();
            j = Math.max(0, insets.left - rect.left);
            i = Math.max(0, insets.right - rect.right);
        }
        if(isLayoutRtl())
        {
            k = getPaddingLeft() + j;
            l = (mSwitchWidth + k) - j - i;
        } else
        {
            l = getWidth() - getPaddingRight() - i;
            k = (l - mSwitchWidth) + j + i;
        }
        getGravity() & 0x70;
        JVM INSTR lookupswitch 3: default 156
    //                   16: 228
    //                   48: 156
    //                   80: 262;
           goto _L1 _L2 _L1 _L3
_L1:
        j = getPaddingTop();
        i = j + mSwitchHeight;
_L5:
        mSwitchLeft = k;
        mSwitchTop = j;
        mSwitchBottom = i;
        mSwitchRight = l;
        return;
_L2:
        j = ((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2 - mSwitchHeight / 2;
        i = j + mSwitchHeight;
        continue; /* Loop/switch isn't completed */
_L3:
        i = getHeight() - getPaddingBottom();
        j = i - mSwitchHeight;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void onMeasure(int i, int j)
    {
        if(mShowText)
        {
            if(mOnLayout == null)
                mOnLayout = makeLayout(mTextOn);
            if(mOffLayout == null)
                mOffLayout = makeLayout(mTextOff);
        }
        Object obj = mTempRect;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        if(mThumbDrawable != null)
        {
            mThumbDrawable.getPadding(((Rect) (obj)));
            k = mThumbDrawable.getIntrinsicWidth() - ((Rect) (obj)).left - ((Rect) (obj)).right;
            l = mThumbDrawable.getIntrinsicHeight();
        } else
        {
            k = 0;
            l = 0;
        }
        if(mShowText)
            i1 = Math.max(mOnLayout.getWidth(), mOffLayout.getWidth()) + mThumbTextPadding * 2;
        else
            i1 = 0;
        mThumbWidth = Math.max(i1, k);
        if(mTrackDrawable != null)
        {
            mTrackDrawable.getPadding(((Rect) (obj)));
            k = mTrackDrawable.getIntrinsicHeight();
        } else
        {
            ((Rect) (obj)).setEmpty();
            k = 0;
        }
        j1 = ((Rect) (obj)).left;
        k1 = ((Rect) (obj)).right;
        l1 = j1;
        i1 = k1;
        if(mThumbDrawable != null)
        {
            obj = mThumbDrawable.getOpticalInsets();
            l1 = Math.max(j1, ((Insets) (obj)).left);
            i1 = Math.max(k1, ((Insets) (obj)).right);
        }
        i1 = Math.max(mSwitchMinWidth, mThumbWidth * 2 + l1 + i1);
        l = Math.max(k, l);
        mSwitchWidth = i1;
        mSwitchHeight = l;
        super.onMeasure(i, j);
        if(getMeasuredHeight() < l)
            setMeasuredDimension(getMeasuredWidthAndState(), l);
    }

    public void onPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        super.onPopulateAccessibilityEventInternal(accessibilityevent);
        CharSequence charsequence;
        if(isChecked())
            charsequence = mTextOn;
        else
            charsequence = mTextOff;
        if(charsequence != null)
            accessibilityevent.getText().add(charsequence);
    }

    public void onProvideAutofillStructure(ViewStructure viewstructure, int i)
    {
        super.onProvideAutofillStructure(viewstructure, i);
        onProvideAutoFillStructureForAssistOrAutofill(viewstructure);
    }

    public void onProvideStructure(ViewStructure viewstructure)
    {
        super.onProvideStructure(viewstructure);
        onProvideAutoFillStructureForAssistOrAutofill(viewstructure);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        mVelocityTracker.addMovement(motionevent);
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 44
    //                   0 50
    //                   1 299
    //                   2 94
    //                   3 299;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        return super.onTouchEvent(motionevent);
_L2:
        float f = motionevent.getX();
        float f3 = motionevent.getY();
        if(isEnabled() && hitThumb(f, f3))
        {
            mTouchMode = 1;
            mTouchX = f;
            mTouchY = f3;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        switch(mTouchMode)
        {
        case 1: // '\001'
            float f1 = motionevent.getX();
            float f4 = motionevent.getY();
            if(Math.abs(f1 - mTouchX) > (float)mTouchSlop || Math.abs(f4 - mTouchY) > (float)mTouchSlop)
            {
                mTouchMode = 2;
                getParent().requestDisallowInterceptTouchEvent(true);
                mTouchX = f1;
                mTouchY = f4;
                return true;
            }
            break;

        case 2: // '\002'
            float f6 = motionevent.getX();
            int i = getThumbScrollRange();
            float f2 = f6 - mTouchX;
            float f5;
            if(i != 0)
            {
                f2 /= i;
            } else
            {
                int j;
                if(f2 > 0.0F)
                    j = 1;
                else
                    j = -1;
                f2 = j;
            }
            f5 = f2;
            if(isLayoutRtl())
                f5 = -f2;
            f2 = MathUtils.constrain(mThumbPosition + f5, 0.0F, 1.0F);
            if(f2 != mThumbPosition)
            {
                mTouchX = f6;
                setThumbPosition(f2);
            }
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(mTouchMode == 2)
        {
            stopDrag(motionevent);
            super.onTouchEvent(motionevent);
            return true;
        }
        mTouchMode = 0;
        mVelocityTracker.clear();
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void setChecked(boolean flag)
    {
        super.setChecked(flag);
        flag = isChecked();
        if(isAttachedToWindow() && isLaidOut())
        {
            animateThumbToCheckedState(flag);
        } else
        {
            cancelPositionAnimator();
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            setThumbPosition(i);
        }
    }

    public void setShowText(boolean flag)
    {
        if(mShowText != flag)
        {
            mShowText = flag;
            requestLayout();
        }
    }

    public void setSplitTrack(boolean flag)
    {
        mSplitTrack = flag;
        invalidate();
    }

    public void setSwitchMinWidth(int i)
    {
        mSwitchMinWidth = i;
        requestLayout();
    }

    public void setSwitchPadding(int i)
    {
        mSwitchPadding = i;
        requestLayout();
    }

    public void setSwitchTextAppearance(Context context, int i)
    {
        TypedArray typedarray = context.obtainStyledAttributes(i, com.android.internal.R.styleable.TextAppearance);
        context = typedarray.getColorStateList(3);
        if(context != null)
            mTextColors = context;
        else
            mTextColors = getTextColors();
        i = typedarray.getDimensionPixelSize(0, 0);
        if(i != 0 && (float)i != mTextPaint.getTextSize())
        {
            mTextPaint.setTextSize(i);
            requestLayout();
        }
        setSwitchTypefaceByIndex(typedarray.getInt(1, -1), typedarray.getInt(2, -1));
        if(typedarray.getBoolean(11, false))
        {
            mSwitchTransformationMethod = new AllCapsTransformationMethod(getContext());
            mSwitchTransformationMethod.setLengthChangesAllowed(true);
        } else
        {
            mSwitchTransformationMethod = null;
        }
        typedarray.recycle();
    }

    public void setSwitchTypeface(Typeface typeface)
    {
        if(mTextPaint.getTypeface() != typeface)
        {
            mTextPaint.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }

    public void setSwitchTypeface(Typeface typeface, int i)
    {
        boolean flag = false;
        if(i > 0)
        {
            int j;
            float f;
            if(typeface == null)
                typeface = Typeface.defaultFromStyle(i);
            else
                typeface = Typeface.create(typeface, i);
            setSwitchTypeface(typeface);
            if(typeface != null)
                j = typeface.getStyle();
            else
                j = 0;
            i &= j;
            typeface = mTextPaint;
            if((i & 1) != 0)
                flag = true;
            typeface.setFakeBoldText(flag);
            typeface = mTextPaint;
            if((i & 2) != 0)
                f = -0.25F;
            else
                f = 0.0F;
            typeface.setTextSkewX(f);
        } else
        {
            mTextPaint.setFakeBoldText(false);
            mTextPaint.setTextSkewX(0.0F);
            setSwitchTypeface(typeface);
        }
    }

    public void setTextOff(CharSequence charsequence)
    {
        mTextOff = charsequence;
        requestLayout();
    }

    public void setTextOn(CharSequence charsequence)
    {
        mTextOn = charsequence;
        requestLayout();
    }

    public void setThumbDrawable(Drawable drawable)
    {
        if(mThumbDrawable != null)
            mThumbDrawable.setCallback(null);
        mThumbDrawable = drawable;
        if(drawable != null)
            drawable.setCallback(this);
        requestLayout();
    }

    public void setThumbResource(int i)
    {
        setThumbDrawable(getContext().getDrawable(i));
    }

    public void setThumbTextPadding(int i)
    {
        mThumbTextPadding = i;
        requestLayout();
    }

    public void setThumbTintList(ColorStateList colorstatelist)
    {
        mThumbTintList = colorstatelist;
        mHasThumbTint = true;
        applyThumbTint();
    }

    public void setThumbTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mThumbTintMode = mode;
        mHasThumbTintMode = true;
        applyThumbTint();
    }

    public void setTrackDrawable(Drawable drawable)
    {
        if(mTrackDrawable != null)
            mTrackDrawable.setCallback(null);
        mTrackDrawable = drawable;
        if(drawable != null)
            drawable.setCallback(this);
        requestLayout();
    }

    public void setTrackResource(int i)
    {
        setTrackDrawable(getContext().getDrawable(i));
    }

    public void setTrackTintList(ColorStateList colorstatelist)
    {
        mTrackTintList = colorstatelist;
        mHasTrackTint = true;
        applyTrackTint();
    }

    public void setTrackTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mTrackTintMode = mode;
        mHasTrackTintMode = true;
        applyTrackTint();
    }

    public void toggle()
    {
        setChecked(isChecked() ^ true);
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(super.verifyDrawable(drawable)) goto _L2; else goto _L1
_L1:
        if(drawable != mThumbDrawable) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(drawable != mTrackDrawable)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    private static final int CHECKED_STATE_SET[] = {
        0x10100a0
    };
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int THUMB_ANIMATION_DURATION = 250;
    private static final FloatProperty THUMB_POS = new FloatProperty("thumbPos") {

        public Float get(Switch switch1)
        {
            return Float.valueOf(Switch._2D_get0(switch1));
        }

        public volatile Object get(Object obj)
        {
            return get((Switch)obj);
        }

        public void setValue(Switch switch1, float f)
        {
            Switch._2D_wrap0(switch1, f);
        }

        public volatile void setValue(Object obj, float f)
        {
            setValue((Switch)obj, f);
        }

    }
;
    private static final int TOUCH_MODE_DOWN = 1;
    private static final int TOUCH_MODE_DRAGGING = 2;
    private static final int TOUCH_MODE_IDLE = 0;
    private boolean mHasThumbTint;
    private boolean mHasThumbTintMode;
    private boolean mHasTrackTint;
    private boolean mHasTrackTintMode;
    private int mMinFlingVelocity;
    private Layout mOffLayout;
    private Layout mOnLayout;
    private ObjectAnimator mPositionAnimator;
    private boolean mShowText;
    private boolean mSplitTrack;
    private int mSwitchBottom;
    private int mSwitchHeight;
    private int mSwitchLeft;
    private int mSwitchMinWidth;
    private int mSwitchPadding;
    private int mSwitchRight;
    private int mSwitchTop;
    private TransformationMethod2 mSwitchTransformationMethod;
    private int mSwitchWidth;
    private final Rect mTempRect;
    private ColorStateList mTextColors;
    private CharSequence mTextOff;
    private CharSequence mTextOn;
    private TextPaint mTextPaint;
    private Drawable mThumbDrawable;
    private float mThumbPosition;
    private int mThumbTextPadding;
    private ColorStateList mThumbTintList;
    private android.graphics.PorterDuff.Mode mThumbTintMode;
    private int mThumbWidth;
    private int mTouchMode;
    private int mTouchSlop;
    private float mTouchX;
    private float mTouchY;
    private Drawable mTrackDrawable;
    private ColorStateList mTrackTintList;
    private android.graphics.PorterDuff.Mode mTrackTintMode;
    private VelocityTracker mVelocityTracker;

}
