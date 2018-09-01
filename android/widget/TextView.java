// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.*;
import android.content.pm.ApplicationInfo;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormatSymbols;
import android.os.*;
import android.text.*;
import android.text.method.*;
import android.text.style.*;
import android.text.util.Linkify;
import android.util.*;
import android.view.*;
import android.view.accessibility.*;
import android.view.animation.AnimationUtils;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.*;
import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.view.textservice.SpellCheckerSubtype;
import android.view.textservice.TextServicesManager;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.util.FastMath;
import com.android.internal.widget.EditableInputConnection;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.*;
import libcore.util.EmptyArray;
import miui.util.TypefaceUtils;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.widget:
//            Editor, Scroller, Toast, SpellChecker

public class TextView extends View
    implements android.view.ViewTreeObserver.OnPreDrawListener
{
    public static final class BufferType extends Enum
    {

        public static BufferType valueOf(String s)
        {
            return (BufferType)Enum.valueOf(android/widget/TextView$BufferType, s);
        }

        public static BufferType[] values()
        {
            return $VALUES;
        }

        private static final BufferType $VALUES[];
        public static final BufferType EDITABLE;
        public static final BufferType NORMAL;
        public static final BufferType SPANNABLE;

        static 
        {
            NORMAL = new BufferType("NORMAL", 0);
            SPANNABLE = new BufferType("SPANNABLE", 1);
            EDITABLE = new BufferType("EDITABLE", 2);
            $VALUES = (new BufferType[] {
                NORMAL, SPANNABLE, EDITABLE
            });
        }

        private BufferType(String s, int i)
        {
            super(s, i);
        }
    }

    private class ChangeWatcher
        implements TextWatcher, SpanWatcher
    {

        public void afterTextChanged(Editable editable)
        {
            sendAfterTextChanged(editable);
            if(MetaKeyKeyListener.getMetaState(editable, 2048) != 0)
                MetaKeyKeyListener.stopSelecting(TextView.this, editable);
        }

        public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
            if(AccessibilityManager.getInstance(TextView._2D_get0(TextView.this)).isEnabled() && TextView.isPasswordInputType(getInputType()) ^ true && hasPasswordTransformationMethod() ^ true)
                mBeforeText = charsequence.toString();
            TextView._2D_wrap0(TextView.this, charsequence, i, j, k);
        }

        public void onSpanAdded(Spannable spannable, Object obj, int i, int j)
        {
            spanChange(spannable, obj, -1, i, -1, j);
        }

        public void onSpanChanged(Spannable spannable, Object obj, int i, int j, int k, int l)
        {
            spanChange(spannable, obj, i, k, j, l);
        }

        public void onSpanRemoved(Spannable spannable, Object obj, int i, int j)
        {
            spanChange(spannable, obj, i, -1, j, -1);
        }

        public void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
            handleTextChanged(charsequence, i, j, k);
            if(AccessibilityManager.getInstance(TextView._2D_get0(TextView.this)).isEnabled() && (isFocused() || isSelected() && isShown()))
            {
                sendAccessibilityEventTypeViewTextChanged(mBeforeText, i, j, k);
                mBeforeText = null;
            }
        }

        private CharSequence mBeforeText;
        final TextView this$0;

        private ChangeWatcher()
        {
            this$0 = TextView.this;
            super();
        }

        ChangeWatcher(ChangeWatcher changewatcher)
        {
            this();
        }
    }

    private static class CharWrapper
        implements CharSequence, GetChars, GraphicsOperations
    {

        static char[] _2D_set0(CharWrapper charwrapper, char ac[])
        {
            charwrapper.mChars = ac;
            return ac;
        }

        public char charAt(int i)
        {
            return mChars[mStart + i];
        }

        public void drawText(BaseCanvas basecanvas, int i, int j, float f, float f1, Paint paint)
        {
            basecanvas.drawText(mChars, i + mStart, j - i, f, f1, paint);
        }

        public void drawTextRun(BaseCanvas basecanvas, int i, int j, int k, int l, float f, float f1, 
                boolean flag, Paint paint)
        {
            basecanvas.drawTextRun(mChars, i + mStart, j - i, k + mStart, l - k, f, f1, flag, paint);
        }

        public void getChars(int i, int j, char ac[], int k)
        {
            while(i < 0 || j < 0 || i > mLength || j > mLength) 
                throw new IndexOutOfBoundsException((new StringBuilder()).append(i).append(", ").append(j).toString());
            System.arraycopy(mChars, mStart + i, ac, k, j - i);
        }

        public float getTextRunAdvances(int i, int j, int k, int l, boolean flag, float af[], int i1, 
                Paint paint)
        {
            return paint.getTextRunAdvances(mChars, i + mStart, j - i, k + mStart, l - k, flag, af, i1);
        }

        public int getTextRunCursor(int i, int j, int k, int l, int i1, Paint paint)
        {
            return paint.getTextRunCursor(mChars, i + mStart, j - i, k, l + mStart, i1);
        }

        public int getTextWidths(int i, int j, float af[], Paint paint)
        {
            return paint.getTextWidths(mChars, mStart + i, j - i, af);
        }

        public int length()
        {
            return mLength;
        }

        public float measureText(int i, int j, Paint paint)
        {
            return paint.measureText(mChars, mStart + i, j - i);
        }

        void set(char ac[], int i, int j)
        {
            mChars = ac;
            mStart = i;
            mLength = j;
        }

        public CharSequence subSequence(int i, int j)
        {
            while(i < 0 || j < 0 || i > mLength || j > mLength) 
                throw new IndexOutOfBoundsException((new StringBuilder()).append(i).append(", ").append(j).toString());
            return new String(mChars, mStart + i, j - i);
        }

        public String toString()
        {
            return new String(mChars, mStart, mLength);
        }

        private char mChars[];
        private int mLength;
        private int mStart;

        public CharWrapper(char ac[], int i, int j)
        {
            mChars = ac;
            mStart = i;
            mLength = j;
        }
    }

    static class Drawables
    {

        private void applyErrorDrawableIfNeeded(int i)
        {
            mDrawableSaved;
            JVM INSTR tableswitch 0 1: default 28
        //                       0 143
        //                       1 114;
               goto _L1 _L2 _L3
_L1:
            if(mDrawableError == null) goto _L5; else goto _L4
_L4:
            i;
            JVM INSTR tableswitch 1 1: default 56
        //                       1 172;
               goto _L6 _L7
_L6:
            mDrawableSaved = 0;
            mDrawableTemp = mShowing[2];
            mDrawableSizeTemp = mDrawableSizeRight;
            mDrawableHeightTemp = mDrawableHeightRight;
            mShowing[2] = mDrawableError;
            mDrawableSizeRight = mDrawableSizeError;
            mDrawableHeightRight = mDrawableHeightError;
_L5:
            return;
_L3:
            mShowing[0] = mDrawableTemp;
            mDrawableSizeLeft = mDrawableSizeTemp;
            mDrawableHeightLeft = mDrawableHeightTemp;
              goto _L1
_L2:
            mShowing[2] = mDrawableTemp;
            mDrawableSizeRight = mDrawableSizeTemp;
            mDrawableHeightRight = mDrawableHeightTemp;
              goto _L1
_L7:
            mDrawableSaved = 1;
            mDrawableTemp = mShowing[0];
            mDrawableSizeTemp = mDrawableSizeLeft;
            mDrawableHeightTemp = mDrawableHeightLeft;
            mShowing[0] = mDrawableError;
            mDrawableSizeLeft = mDrawableSizeError;
            mDrawableHeightLeft = mDrawableHeightError;
              goto _L5
        }

        public boolean hasMetadata()
        {
            boolean flag;
            if(mDrawablePadding == 0 && !mHasTintMode)
                flag = mHasTint;
            else
                flag = true;
            return flag;
        }

        public boolean resolveWithLayoutDirection(int i)
        {
            boolean flag;
            Drawable drawable;
            Drawable drawable1;
            flag = true;
            drawable = mShowing[0];
            drawable1 = mShowing[2];
            mShowing[0] = mDrawableLeftInitial;
            mShowing[2] = mDrawableRightInitial;
            if(!mIsRtlCompatibilityMode) goto _L2; else goto _L1
_L1:
            if(mDrawableStart != null && mShowing[0] == null)
            {
                mShowing[0] = mDrawableStart;
                mDrawableSizeLeft = mDrawableSizeStart;
                mDrawableHeightLeft = mDrawableHeightStart;
            }
            if(mDrawableEnd != null && mShowing[2] == null)
            {
                mShowing[2] = mDrawableEnd;
                mDrawableSizeRight = mDrawableSizeEnd;
                mDrawableHeightRight = mDrawableHeightEnd;
            }
_L4:
            applyErrorDrawableIfNeeded(i);
            boolean flag1 = flag;
            if(mShowing[0] == drawable)
                if(mShowing[2] != drawable1)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
_L2:
            switch(i)
            {
            default:
                if(mOverride)
                {
                    mShowing[0] = mDrawableStart;
                    mDrawableSizeLeft = mDrawableSizeStart;
                    mDrawableHeightLeft = mDrawableHeightStart;
                    mShowing[2] = mDrawableEnd;
                    mDrawableSizeRight = mDrawableSizeEnd;
                    mDrawableHeightRight = mDrawableHeightEnd;
                }
                break;

            case 1: // '\001'
                if(mOverride)
                {
                    mShowing[2] = mDrawableStart;
                    mDrawableSizeRight = mDrawableSizeStart;
                    mDrawableHeightRight = mDrawableHeightStart;
                    mShowing[0] = mDrawableEnd;
                    mDrawableSizeLeft = mDrawableSizeEnd;
                    mDrawableHeightLeft = mDrawableHeightEnd;
                }
                break;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void setErrorDrawable(Drawable drawable, TextView textview)
        {
            if(mDrawableError != drawable && mDrawableError != null)
                mDrawableError.setCallback(null);
            mDrawableError = drawable;
            if(mDrawableError != null)
            {
                drawable = mCompoundRect;
                int ai[] = textview.getDrawableState();
                mDrawableError.setState(ai);
                mDrawableError.copyBounds(drawable);
                mDrawableError.setCallback(textview);
                mDrawableSizeError = drawable.width();
                mDrawableHeightError = drawable.height();
            } else
            {
                mDrawableHeightError = 0;
                mDrawableSizeError = 0;
            }
        }

        static final int BOTTOM = 3;
        static final int DRAWABLE_LEFT = 1;
        static final int DRAWABLE_NONE = -1;
        static final int DRAWABLE_RIGHT = 0;
        static final int LEFT = 0;
        static final int RIGHT = 2;
        static final int TOP = 1;
        final Rect mCompoundRect = new Rect();
        Drawable mDrawableEnd;
        Drawable mDrawableError;
        int mDrawableHeightEnd;
        int mDrawableHeightError;
        int mDrawableHeightLeft;
        int mDrawableHeightRight;
        int mDrawableHeightStart;
        int mDrawableHeightTemp;
        Drawable mDrawableLeftInitial;
        int mDrawablePadding;
        Drawable mDrawableRightInitial;
        int mDrawableSaved;
        int mDrawableSizeBottom;
        int mDrawableSizeEnd;
        int mDrawableSizeError;
        int mDrawableSizeLeft;
        int mDrawableSizeRight;
        int mDrawableSizeStart;
        int mDrawableSizeTemp;
        int mDrawableSizeTop;
        Drawable mDrawableStart;
        Drawable mDrawableTemp;
        int mDrawableWidthBottom;
        int mDrawableWidthTop;
        boolean mHasTint;
        boolean mHasTintMode;
        boolean mIsRtlCompatibilityMode;
        boolean mOverride;
        final Drawable mShowing[] = new Drawable[4];
        ColorStateList mTintList;
        android.graphics.PorterDuff.Mode mTintMode;

        public Drawables(Context context)
        {
            mDrawableSaved = -1;
            boolean flag;
            if(context.getApplicationInfo().targetSdkVersion >= 17)
                flag = context.getApplicationInfo().hasRtlSupport() ^ true;
            else
                flag = true;
            mIsRtlCompatibilityMode = flag;
            mOverride = false;
        }
    }

    private static final class Marquee
    {

        static Choreographer _2D_get0(Marquee marquee)
        {
            return marquee.mChoreographer;
        }

        static int _2D_get1(Marquee marquee)
        {
            return marquee.mRepeatLimit;
        }

        static byte _2D_get2(Marquee marquee)
        {
            return marquee.mStatus;
        }

        static long _2D_set0(Marquee marquee, long l)
        {
            marquee.mLastAnimationMs = l;
            return l;
        }

        static int _2D_set1(Marquee marquee, int i)
        {
            marquee.mRepeatLimit = i;
            return i;
        }

        static byte _2D_set2(Marquee marquee, byte byte0)
        {
            marquee.mStatus = byte0;
            return byte0;
        }

        private void resetScroll()
        {
            mScroll = 0.0F;
            TextView textview = (TextView)mView.get();
            if(textview != null)
                textview.invalidate();
        }

        float getGhostOffset()
        {
            return mGhostOffset;
        }

        float getMaxFadeScroll()
        {
            return mMaxFadeScroll;
        }

        float getScroll()
        {
            return mScroll;
        }

        boolean isRunning()
        {
            boolean flag;
            if(mStatus == 2)
                flag = true;
            else
                flag = false;
            return flag;
        }

        boolean isStopped()
        {
            boolean flag = false;
            if(mStatus == 0)
                flag = true;
            return flag;
        }

        boolean shouldDrawGhost()
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mStatus == 2)
            {
                flag1 = flag;
                if(mScroll > mGhostStart)
                    flag1 = true;
            }
            return flag1;
        }

        boolean shouldDrawLeftFade()
        {
            boolean flag;
            if(mScroll <= mFadeStop)
                flag = true;
            else
                flag = false;
            return flag;
        }

        void start(int i)
        {
            if(i == 0)
            {
                stop();
                return;
            }
            mRepeatLimit = i;
            TextView textview = (TextView)mView.get();
            if(textview != null && TextView._2D_get2(textview) != null)
            {
                mStatus = (byte)1;
                mScroll = 0.0F;
                i = textview.getWidth() - textview.getCompoundPaddingLeft() - textview.getCompoundPaddingRight();
                float f = TextView._2D_get2(textview).getLineWidth(0);
                float f1 = (float)i / 3F;
                mGhostStart = (f - (float)i) + f1;
                mMaxScroll = mGhostStart + (float)i;
                mGhostOffset = f + f1;
                mFadeStop = (float)i / 6F + f;
                mMaxFadeScroll = mGhostStart + f + f;
                textview.invalidate();
                mChoreographer.postFrameCallback(mStartCallback);
            }
        }

        void stop()
        {
            mStatus = (byte)0;
            mChoreographer.removeFrameCallback(mStartCallback);
            mChoreographer.removeFrameCallback(mRestartCallback);
            mChoreographer.removeFrameCallback(mTickCallback);
            resetScroll();
        }

        void tick()
        {
            if(mStatus != 2)
                return;
            mChoreographer.removeFrameCallback(mTickCallback);
            TextView textview = (TextView)mView.get();
            if(textview != null && (textview.isFocused() || textview.isSelected()))
            {
                long l = mChoreographer.getFrameTime();
                long l1 = mLastAnimationMs;
                mLastAnimationMs = l;
                float f = (float)(l - l1) / 1000F;
                float f1 = mPixelsPerSecond;
                mScroll = mScroll + f * f1;
                if(mScroll > mMaxScroll)
                {
                    mScroll = mMaxScroll;
                    mChoreographer.postFrameCallbackDelayed(mRestartCallback, 1200L);
                } else
                {
                    mChoreographer.postFrameCallback(mTickCallback);
                }
                textview.invalidate();
            }
        }

        private static final int MARQUEE_DELAY = 1200;
        private static final float MARQUEE_DELTA_MAX = 0.07F;
        private static final int MARQUEE_DP_PER_SECOND = 30;
        private static final byte MARQUEE_RUNNING = 2;
        private static final byte MARQUEE_STARTING = 1;
        private static final byte MARQUEE_STOPPED = 0;
        private final Choreographer mChoreographer = Choreographer.getInstance();
        private float mFadeStop;
        private float mGhostOffset;
        private float mGhostStart;
        private long mLastAnimationMs;
        private float mMaxFadeScroll;
        private float mMaxScroll;
        private final float mPixelsPerSecond;
        private int mRepeatLimit;
        private android.view.Choreographer.FrameCallback mRestartCallback;
        private float mScroll;
        private android.view.Choreographer.FrameCallback mStartCallback;
        private byte mStatus;
        private android.view.Choreographer.FrameCallback mTickCallback;
        private final WeakReference mView;

        Marquee(TextView textview)
        {
            mStatus = (byte)0;
            mTickCallback = new _cls1();
            mStartCallback = new _cls2();
            mRestartCallback = new _cls3();
            mPixelsPerSecond = 30F * textview.getContext().getResources().getDisplayMetrics().density;
            mView = new WeakReference(textview);
        }
    }

    public static interface OnEditorActionListener
    {

        public abstract boolean onEditorAction(TextView textview, int i, KeyEvent keyevent);
    }

    public static class SavedState extends android.view.View.BaseSavedState
    {

        public String toString()
        {
            String s = (new StringBuilder()).append("TextView.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" start=").append(selStart).append(" end=").append(selEnd).toString();
            String s1 = s;
            if(text != null)
                s1 = (new StringBuilder()).append(s).append(" text=").append(text).toString();
            return (new StringBuilder()).append(s1).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(selStart);
            parcel.writeInt(selEnd);
            int j;
            if(frozenWithFocus)
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
            TextUtils.writeToParcel(text, parcel, i);
            if(error == null)
            {
                parcel.writeInt(0);
            } else
            {
                parcel.writeInt(1);
                TextUtils.writeToParcel(error, parcel, i);
            }
            if(editorState == null)
            {
                parcel.writeInt(0);
            } else
            {
                parcel.writeInt(1);
                editorState.writeToParcel(parcel, i);
            }
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
        ParcelableParcel editorState;
        CharSequence error;
        boolean frozenWithFocus;
        int selEnd;
        int selStart;
        CharSequence text;


        private SavedState(Parcel parcel)
        {
            boolean flag = false;
            super(parcel);
            selStart = -1;
            selEnd = -1;
            selStart = parcel.readInt();
            selEnd = parcel.readInt();
            if(parcel.readInt() != 0)
                flag = true;
            frozenWithFocus = flag;
            text = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            if(parcel.readInt() != 0)
                error = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            if(parcel.readInt() != 0)
                editorState = (ParcelableParcel)ParcelableParcel.CREATOR.createFromParcel(parcel);
        }

        SavedState(Parcel parcel, SavedState savedstate)
        {
            this(parcel);
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
            selStart = -1;
            selEnd = -1;
        }
    }


    static Context _2D_get0(TextView textview)
    {
        return textview.mContext;
    }

    static Editor _2D_get1(TextView textview)
    {
        return textview.mEditor;
    }

    static Layout _2D_get2(TextView textview)
    {
        return textview.mLayout;
    }

    private static int[] _2D_getandroid_2D_text_2D_Layout$AlignmentSwitchesValues()
    {
        if(_2D_android_2D_text_2D_Layout$AlignmentSwitchesValues != null)
            return _2D_android_2D_text_2D_Layout$AlignmentSwitchesValues;
        int ai[] = new int[android.text.Layout.Alignment.values().length];
        try
        {
            ai[android.text.Layout.Alignment.ALIGN_CENTER.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[android.text.Layout.Alignment.ALIGN_LEFT.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[android.text.Layout.Alignment.ALIGN_NORMAL.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[android.text.Layout.Alignment.ALIGN_OPPOSITE.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[android.text.Layout.Alignment.ALIGN_RIGHT.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_android_2D_text_2D_Layout$AlignmentSwitchesValues = ai;
        return ai;
    }

    static void _2D_wrap0(TextView textview, CharSequence charsequence, int i, int j, int k)
    {
        textview.sendBeforeTextChanged(charsequence, i, j, k);
    }

    static void _2D_wrap1(TextView textview)
    {
        textview.updateTextServicesLocaleLocked();
    }

    public TextView(Context context)
    {
        this(context, null);
    }

    public TextView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010084);
    }

    public TextView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public TextView(Context context, AttributeSet attributeset, int i, int j)
    {
        Object obj;
        Object obj1;
        int k;
        ColorStateList colorstatelist;
        String s;
        ColorStateList colorstatelist2;
        int i1;
        Object obj6;
        Object obj7;
        byte byte0;
        boolean flag1;
        byte byte3;
        float f;
        float f1;
        float f3;
        float f6;
        boolean flag6;
        float f9;
        Object obj12;
        Object obj13;
        int i2;
        int j2;
        int k2;
        int l2;
        super(context, attributeset, i, j);
        mEditableFactory = android.text.Editable.Factory.getInstance();
        mSpannableFactory = android.text.Spannable.Factory.getInstance();
        mMarqueeRepeatLimit = 3;
        mLastLayoutDirection = -1;
        mMarqueeFadeMode = 0;
        mBufferType = BufferType.NORMAL;
        mLocalesChanged = false;
        mListenerChanged = false;
        mGravity = 0x800033;
        mLinksClickable = true;
        mSpacingMult = 1.0F;
        mSpacingAdd = 0.0F;
        mMaximum = 0x7fffffff;
        mMaxMode = 1;
        mMinimum = 0;
        mMinMode = 1;
        mOldMaximum = mMaximum;
        mOldMaxMode = mMaxMode;
        mMaxWidth = 0x7fffffff;
        mMaxWidthMode = 2;
        mMinWidth = 0;
        mMinWidthMode = 2;
        mDesiredHeightAtMeasure = -1;
        mIncludePad = true;
        mDeferScroll = -1;
        mFilters = NO_FILTERS;
        mHighlightColor = 0x6633b5e5;
        mHighlightPathBogus = true;
        mDeviceProvisionedState = 0;
        mAutoSizeTextType = 0;
        mNeedsAutoSizeText = false;
        mAutoSizeStepGranularityInPx = -1F;
        mAutoSizeMinTextSizeInPx = -1F;
        mAutoSizeMaxTextSizeInPx = -1F;
        mAutoSizeTextSizesInPx = EmptyArray.INT;
        mHasPresetAutoSizeValues = false;
        mTextFromResource = false;
        if(getImportantForAutofill() == 0)
            setImportantForAutofill(1);
        mText = "";
        obj = getResources();
        obj1 = ((Resources) (obj)).getCompatibilityInfo();
        mTextPaint = new TextPaint(1);
        mTextPaint.density = ((Resources) (obj)).getDisplayMetrics().density;
        mTextPaint.setCompatibilityScaling(((CompatibilityInfo) (obj1)).applicationScale);
        mHighlightPaint = new Paint(1);
        mHighlightPaint.setCompatibilityScaling(((CompatibilityInfo) (obj1)).applicationScale);
        mMovement = getDefaultMovementMethod();
        mTransformation = null;
        k = 0;
        boolean flag = false;
        colorstatelist = null;
        Object obj2 = null;
        s = null;
        Object obj4 = null;
        colorstatelist2 = null;
        Object obj5 = null;
        i1 = 15;
        obj1 = null;
        obj6 = null;
        obj7 = null;
        Object obj8 = null;
        byte0 = 0;
        byte byte1 = -1;
        byte byte2 = -1;
        flag1 = false;
        boolean flag2 = false;
        byte3 = 0;
        boolean flag4 = false;
        f = 0.0F;
        f1 = 0.0F;
        f3 = 0.0F;
        float f4 = 0.0F;
        f6 = 0.0F;
        float f7 = 0.0F;
        flag6 = false;
        boolean flag7 = false;
        f9 = 0.0F;
        float f10 = 0.0F;
        obj = null;
        Object obj10 = null;
        mBreakStrategy = 0;
        mHyphenationFrequency = 0;
        mJustificationMode = 0;
        obj12 = context.getTheme();
        TypedArray typedarray = ((android.content.res.Resources.Theme) (obj12)).obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TextViewAppearance, i, j);
        obj13 = null;
        i2 = typedarray.getResourceId(0, -1);
        typedarray.recycle();
        if(i2 != -1)
            obj13 = ((android.content.res.Resources.Theme) (obj12)).obtainStyledAttributes(i2, com.android.internal.R.styleable.TextAppearance);
        i2 = byte2;
        j2 = i1;
        k2 = byte1;
        if(obj13 == null)
            break MISSING_BLOCK_LABEL_2053;
        l2 = ((TypedArray) (obj13)).getIndexCount();
        boolean flag9 = false;
        k2 = byte1;
        j2 = i1;
        colorstatelist2 = obj5;
        s = obj4;
        k = ((flag) ? 1 : 0);
        colorstatelist = obj2;
        i2 = byte2;
        byte3 = flag4;
        f = f7;
        f9 = f10;
        i1 = ((flag9) ? 1 : 0);
        obj = obj8;
        obj1 = obj10;
        flag6 = flag7;
        f3 = f4;
        flag1 = flag2;
_L19:
        int j3;
        if(i1 >= l2)
            break MISSING_BLOCK_LABEL_2028;
        j3 = ((TypedArray) (obj13)).getIndex(i1);
        j3;
        JVM INSTR tableswitch 0 15: default 684
    //                   0 1084
    //                   1 1154
    //                   2 1408
    //                   3 880
    //                   4 810
    //                   5 948
    //                   6 1016
    //                   7 1546
    //                   8 1615
    //                   9 1684
    //                   10 1753
    //                   11 1477
    //                   12 1223
    //                   13 1822
    //                   14 1891
    //                   15 1960;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17
_L17:
        break MISSING_BLOCK_LABEL_1960;
_L6:
        break; /* Loop/switch isn't completed */
_L1:
        int l;
        Object obj3;
        ColorStateList colorstatelist1;
        Object obj9;
        int j1;
        int k1;
        boolean flag3;
        int l1;
        float f5;
        float f8;
        boolean flag8;
        float f11;
        Object obj11;
        int i3;
        i3 = k2;
        l = j2;
        colorstatelist1 = colorstatelist2;
        obj3 = s;
        l1 = k;
        obj9 = colorstatelist;
        j1 = i2;
        k1 = byte3;
        f8 = f;
        f11 = f9;
        obj11 = obj1;
        flag8 = flag6;
        f5 = f3;
        f6 = f1;
        flag3 = flag1;
_L20:
        i1++;
        flag1 = flag3;
        f1 = f6;
        f3 = f5;
        flag6 = flag8;
        obj1 = obj11;
        f9 = f11;
        f = f8;
        byte3 = k1;
        i2 = j1;
        colorstatelist = ((ColorStateList) (obj9));
        k = l1;
        s = ((String) (obj3));
        colorstatelist2 = colorstatelist1;
        j2 = l;
        k2 = i3;
        if(true) goto _L19; else goto _L18
_L18:
        l1 = ((TypedArray) (obj13)).getColor(j3, k);
        flag3 = flag1;
        f6 = f1;
        f5 = f3;
        flag8 = flag6;
        obj11 = obj1;
        f11 = f9;
        f8 = f;
        k1 = byte3;
        j1 = i2;
        obj9 = colorstatelist;
        obj3 = s;
        colorstatelist1 = colorstatelist2;
        l = j2;
        i3 = k2;
          goto _L20
_L5:
        obj9 = ((TypedArray) (obj13)).getColorStateList(j3);
        flag3 = flag1;
        f6 = f1;
        f5 = f3;
        flag8 = flag6;
        obj11 = obj1;
        f11 = f9;
        f8 = f;
        k1 = byte3;
        j1 = i2;
        l1 = k;
        obj3 = s;
        colorstatelist1 = colorstatelist2;
        l = j2;
        i3 = k2;
          goto _L20
_L7:
        obj3 = ((TypedArray) (obj13)).getColorStateList(j3);
        flag3 = flag1;
        f6 = f1;
        f5 = f3;
        flag8 = flag6;
        obj11 = obj1;
        f11 = f9;
        f8 = f;
        k1 = byte3;
        j1 = i2;
        obj9 = colorstatelist;
        l1 = k;
        colorstatelist1 = colorstatelist2;
        l = j2;
        i3 = k2;
          goto _L20
_L8:
        colorstatelist1 = ((TypedArray) (obj13)).getColorStateList(j3);
        flag3 = flag1;
        f6 = f1;
        f5 = f3;
        flag8 = flag6;
        obj11 = obj1;
        f11 = f9;
        f8 = f;
        k1 = byte3;
        j1 = i2;
        obj9 = colorstatelist;
        l1 = k;
        obj3 = s;
        l = j2;
        i3 = k2;
          goto _L20
_L2:
        l = ((TypedArray) (obj13)).getDimensionPixelSize(j3, j2);
        flag3 = flag1;
        f6 = f1;
        f5 = f3;
        flag8 = flag6;
        obj11 = obj1;
        f11 = f9;
        f8 = f;
        k1 = byte3;
        j1 = i2;
        obj9 = colorstatelist;
        l1 = k;
        obj3 = s;
        colorstatelist1 = colorstatelist2;
        i3 = k2;
          goto _L20
_L3:
        i3 = ((TypedArray) (obj13)).getInt(j3, -1);
        flag3 = flag1;
        f6 = f1;
        f5 = f3;
        flag8 = flag6;
        obj11 = obj1;
        f11 = f9;
        f8 = f;
        k1 = byte3;
        j1 = i2;
        obj9 = colorstatelist;
        l1 = k;
        obj3 = s;
        colorstatelist1 = colorstatelist2;
        l = j2;
          goto _L20
_L14:
        obj7 = obj;
        if(context.isRestricted())
            break MISSING_BLOCK_LABEL_1254;
        obj7 = obj;
        if(!context.canLoadUnsafeResources())
            break MISSING_BLOCK_LABEL_1254;
        try
        {
            obj7 = ((TypedArray) (obj13)).getFont(j3);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj7 = obj;
        }
        flag3 = flag1;
        f6 = f1;
        f5 = f3;
        flag8 = flag6;
        obj11 = obj1;
        obj = obj7;
        f11 = f9;
        f8 = f;
        k1 = byte3;
        j1 = i2;
        obj9 = colorstatelist;
        l1 = k;
        obj3 = s;
        colorstatelist1 = colorstatelist2;
        l = j2;
        i3 = k2;
        if(obj7 == null)
        {
            obj6 = ((TypedArray) (obj13)).getString(j3);
            flag3 = flag1;
            f6 = f1;
            f5 = f3;
            flag8 = flag6;
            obj11 = obj1;
            obj = obj7;
            f11 = f9;
            f8 = f;
            k1 = byte3;
            j1 = i2;
            obj9 = colorstatelist;
            l1 = k;
            obj3 = s;
            colorstatelist1 = colorstatelist2;
            l = j2;
            i3 = k2;
        }
          goto _L20
_L4:
        j1 = ((TypedArray) (obj13)).getInt(j3, -1);
        flag3 = flag1;
        f6 = f1;
        f5 = f3;
        flag8 = flag6;
        obj11 = obj1;
        f11 = f9;
        f8 = f;
        k1 = byte3;
        obj9 = colorstatelist;
        l1 = k;
        obj3 = s;
        colorstatelist1 = colorstatelist2;
        l = j2;
        i3 = k2;
          goto _L20
_L13:
        flag3 = ((TypedArray) (obj13)).getBoolean(j3, false);
        f6 = f1;
        f5 = f3;
        flag8 = flag6;
        obj11 = obj1;
        f11 = f9;
        f8 = f;
        k1 = byte3;
        j1 = i2;
        obj9 = colorstatelist;
        l1 = k;
        obj3 = s;
        colorstatelist1 = colorstatelist2;
        l = j2;
        i3 = k2;
          goto _L20
_L9:
        k1 = ((TypedArray) (obj13)).getInt(j3, 0);
        flag3 = flag1;
        f6 = f1;
        f5 = f3;
        flag8 = flag6;
        obj11 = obj1;
        f11 = f9;
        f8 = f;
        j1 = i2;
        obj9 = colorstatelist;
        l1 = k;
        obj3 = s;
        colorstatelist1 = colorstatelist2;
        l = j2;
        i3 = k2;
          goto _L20
_L10:
        f6 = ((TypedArray) (obj13)).getFloat(j3, 0.0F);
        flag3 = flag1;
        f5 = f3;
        flag8 = flag6;
        obj11 = obj1;
        f11 = f9;
        f8 = f;
        k1 = byte3;
        j1 = i2;
        obj9 = colorstatelist;
        l1 = k;
        obj3 = s;
        colorstatelist1 = colorstatelist2;
        l = j2;
        i3 = k2;
          goto _L20
_L11:
        f5 = ((TypedArray) (obj13)).getFloat(j3, 0.0F);
        flag3 = flag1;
        f6 = f1;
        flag8 = flag6;
        obj11 = obj1;
        f11 = f9;
        f8 = f;
        k1 = byte3;
        j1 = i2;
        obj9 = colorstatelist;
        l1 = k;
        obj3 = s;
        colorstatelist1 = colorstatelist2;
        l = j2;
        i3 = k2;
          goto _L20
_L12:
        f8 = ((TypedArray) (obj13)).getFloat(j3, 0.0F);
        flag3 = flag1;
        f6 = f1;
        f5 = f3;
        flag8 = flag6;
        obj11 = obj1;
        f11 = f9;
        k1 = byte3;
        j1 = i2;
        obj9 = colorstatelist;
        l1 = k;
        obj3 = s;
        colorstatelist1 = colorstatelist2;
        l = j2;
        i3 = k2;
          goto _L20
_L15:
        flag8 = ((TypedArray) (obj13)).getBoolean(j3, false);
        flag3 = flag1;
        f6 = f1;
        f5 = f3;
        obj11 = obj1;
        f11 = f9;
        f8 = f;
        k1 = byte3;
        j1 = i2;
        obj9 = colorstatelist;
        l1 = k;
        obj3 = s;
        colorstatelist1 = colorstatelist2;
        l = j2;
        i3 = k2;
          goto _L20
_L16:
        f11 = ((TypedArray) (obj13)).getFloat(j3, 0.0F);
        flag3 = flag1;
        f6 = f1;
        f5 = f3;
        flag8 = flag6;
        obj11 = obj1;
        f8 = f;
        k1 = byte3;
        j1 = i2;
        obj9 = colorstatelist;
        l1 = k;
        obj3 = s;
        colorstatelist1 = colorstatelist2;
        l = j2;
        i3 = k2;
          goto _L20
        obj11 = ((TypedArray) (obj13)).getString(j3);
        flag3 = flag1;
        f6 = f1;
        f5 = f3;
        flag8 = flag6;
        f11 = f9;
        f8 = f;
        k1 = byte3;
        j1 = i2;
        obj9 = colorstatelist;
        l1 = k;
        obj3 = s;
        colorstatelist1 = colorstatelist2;
        l = j2;
        i3 = k2;
          goto _L20
        ((TypedArray) (obj13)).recycle();
        f6 = f;
        obj7 = obj;
        obj = obj1;
        obj1 = obj6;
        f = f1;
        Drawable drawable;
        boolean flag5;
        Drawable drawable1;
        boolean flag10;
        Object obj14;
        CharSequence charsequence;
        boolean flag11;
        boolean flag12;
        int i4;
        Drawable drawable2;
        Drawable drawable3;
        Drawable drawable4;
        boolean flag13;
        TypedArray typedarray1;
        int k4;
        flag10 = getDefaultEditable();
        obj14 = null;
        l2 = 0;
        charsequence = null;
        flag11 = false;
        flag12 = false;
        byte byte4 = -1;
        i4 = 0;
        flag8 = false;
        drawable2 = null;
        obj9 = null;
        drawable1 = null;
        drawable3 = null;
        drawable = null;
        drawable4 = null;
        colorstatelist1 = null;
        obj3 = null;
        i3 = 0;
        byte byte5 = -1;
        flag3 = false;
        j1 = -1;
        obj11 = "";
        obj13 = null;
        flag13 = false;
        float f12 = -1F;
        f8 = -1F;
        float f14 = -1F;
        l = 0;
        typedarray1 = ((android.content.res.Resources.Theme) (obj12)).obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TextView, i, j);
        k4 = typedarray1.getIndexCount();
        flag5 = false;
        j3 = 0;
        obj6 = s;
        i1 = k;
        s = ((String) (obj11));
        k = i2;
        k1 = byte3;
        f1 = f9;
        i2 = l;
        obj11 = obj;
        l = byte0;
        byte3 = byte5;
        f5 = f3;
        f11 = f;
        byte0 = byte4;
        f = f14;
        f9 = f12;
        f3 = f8;
_L111:
        int l3;
        int j4;
        float f13;
        float f15;
        int l4;
        boolean flag14;
        boolean flag16;
        Drawable drawable5;
        Drawable drawable6;
        Drawable drawable7;
        int i5;
        Drawable drawable8;
        Drawable drawable9;
        ColorStateList colorstatelist3;
        android.graphics.PorterDuff.Mode mode;
        Drawable drawable10;
        float f16;
        float f17;
        boolean flag18;
        boolean flag20;
        int j5;
        String s1;
        boolean flag22;
        Object obj15;
        boolean flag23;
        CharSequence charsequence1;
        CharSequence charsequence2;
        int k5;
        float f18;
        int l5;
        int i6;
        boolean flag24;
        boolean flag25;
        float f19;
        boolean flag26;
        int j6;
        boolean flag27;
        int k6;
        Object obj16;
        ColorStateList colorstatelist4;
        int l6;
        ColorStateList colorstatelist5;
        ColorStateList colorstatelist6;
        int i7;
        int j7;
        if(j3 >= k4)
            break MISSING_BLOCK_LABEL_21939;
        l4 = typedarray1.getIndex(j3);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
        l4;
        JVM INSTR tableswitch 0 91: default 2824
    //                   0 13370
    //                   1 3012
    //                   2 14362
    //                   3 14560
    //                   4 14758
    //                   5 13774
    //                   6 13576
    //                   7 13970
    //                   8 14166
    //                   9 10785
    //                   10 9598
    //                   11 4984
    //                   12 5187
    //                   13 8583
    //                   14 7365
    //                   15 9395
    //                   16 8177
    //                   17 4588
    //                   18 9997
    //                   19 9801
    //                   20 12176
    //                   21 11584
    //                   22 7162
    //                   23 7568
    //                   24 7771
    //                   25 7974
    //                   26 8380
    //                   27 8786
    //                   28 8989
    //                   29 9192
    //                   30 10192
    //                   31 15196
    //                   32 10587
    //                   33 4786
    //                   34 11189
    //                   35 11979
    //                   36 12582
    //                   37 12779
    //                   38 12976
    //                   39 13173
    //                   40 3600
    //                   41 3798
    //                   42 3994
    //                   43 3404
    //                   44 4390
    //                   45 4192
    //                   46 3206
    //                   47 12379
    //                   48 5586
    //                   49 5978
    //                   50 5390
    //                   51 5782
    //                   52 6964
    //                   53 15394
    //                   54 15602
    //                   55 10983
    //                   56 15808
    //                   57 16892
    //                   58 17094
    //                   59 16215
    //                   60 16444
    //                   61 16663
    //                   62 17908
    //                   63 18111
    //                   64 18314
    //                   65 3012
    //                   66 3012
    //                   67 19126
    //                   68 3012
    //                   69 3012
    //                   70 17705
    //                   71 18517
    //                   72 19329
    //                   73 6174
    //                   74 6370
    //                   75 14956
    //                   76 19526
    //                   77 19723
    //                   78 19920
    //                   79 6566
    //                   80 6762
    //                   81 20116
    //                   82 20319
    //                   83 16005
    //                   84 20522
    //                   85 20725
    //                   86 21319
    //                   87 20923
    //                   88 21121
    //                   89 21736
    //                   90 18720
    //                   91 18923;
           goto _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50 _L51 _L52 _L53 _L54 _L55 _L56 _L57 _L58 _L59 _L60 _L61 _L62 _L63 _L64 _L65 _L66 _L67 _L68 _L69 _L70 _L71 _L72 _L73 _L74 _L75 _L76 _L77 _L78 _L79 _L80 _L81 _L82 _L83 _L84 _L85 _L86 _L23 _L23 _L87 _L23 _L23 _L88 _L89 _L90 _L91 _L92 _L93 _L94 _L95 _L96 _L97 _L98 _L99 _L100 _L101 _L102 _L103 _L104 _L105 _L106 _L107 _L108 _L109
_L107:
        break MISSING_BLOCK_LABEL_21736;
_L68:
        break; /* Loop/switch isn't completed */
_L23:
        break; /* Loop/switch isn't completed */
_L21:
        j7 = k2;
        i7 = j2;
        colorstatelist6 = colorstatelist2;
        colorstatelist5 = ((ColorStateList) (obj6));
        l6 = i1;
        colorstatelist4 = colorstatelist;
        obj16 = s;
        k6 = k;
        flag27 = flag3;
        j6 = k1;
        flag26 = flag8;
        f19 = f6;
        flag25 = flag11;
        flag24 = flag13;
        i6 = l2;
        l5 = j1;
        f18 = f1;
        k5 = i2;
        charsequence2 = ((CharSequence) (obj14));
        charsequence1 = ((CharSequence) (obj13));
        flag23 = flag5;
        obj = obj7;
        obj15 = obj11;
        flag22 = l;
        s1 = ((String) (obj1));
        j5 = byte3;
        flag20 = flag6;
        flag18 = flag10;
        f17 = f5;
        f16 = f11;
        drawable10 = ((Drawable) (obj9));
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        colorstatelist3 = colorstatelist1;
        drawable9 = drawable;
        drawable8 = drawable1;
        i5 = i3;
        drawable7 = drawable2;
        drawable6 = drawable4;
        drawable5 = drawable3;
        obj12 = charsequence;
        j4 = i4;
        flag16 = flag12;
        l3 = byte0;
        f15 = f;
        f13 = f9;
        f8 = f3;
        flag14 = flag1;
_L112:
        j3++;
        flag1 = flag14;
        f3 = f8;
        f9 = f13;
        f = f15;
        byte0 = l3;
        flag12 = flag16;
        i4 = j4;
        charsequence = ((CharSequence) (obj12));
        drawable3 = drawable5;
        drawable4 = drawable6;
        drawable2 = drawable7;
        i3 = i5;
        drawable1 = drawable8;
        drawable = drawable9;
        colorstatelist1 = colorstatelist3;
        obj3 = mode;
        obj9 = drawable10;
        f11 = f16;
        f5 = f17;
        flag10 = flag18;
        flag6 = flag20;
        byte3 = j5;
        obj1 = s1;
        l = ((flag22) ? 1 : 0);
        obj11 = obj15;
        obj7 = obj;
        flag5 = flag23;
        obj13 = charsequence1;
        obj14 = charsequence2;
        i2 = k5;
        f1 = f18;
        j1 = l5;
        l2 = i6;
        flag13 = flag24;
        flag11 = flag25;
        f6 = f19;
        flag8 = flag26;
        k1 = j6;
        flag3 = flag27;
        k = k6;
        s = ((String) (obj16));
        colorstatelist = colorstatelist4;
        i1 = l6;
        obj6 = colorstatelist5;
        colorstatelist2 = colorstatelist6;
        j2 = i7;
        k2 = j7;
        if(true) goto _L111; else goto _L110
_L110:
        flag18 = typedarray1.getBoolean(l4, flag10);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L65:
        charsequence2 = typedarray1.getText(l4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L62:
        i6 = typedarray1.getInt(l4, l2);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L63:
        obj12 = typedarray1.getText(l4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L64:
        flag25 = typedarray1.getBoolean(l4, flag11);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L67:
        flag16 = typedarray1.getBoolean(l4, flag12);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L66:
        l3 = typedarray1.getInt(l4, byte0);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L39:
        j4 = typedarray1.getInt(l4, i4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L55:
        flag26 = typedarray1.getBoolean(l4, flag8);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L33:
        mAutoLinkMask = typedarray1.getInt(l4, 0);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L34:
        mLinksClickable = typedarray1.getBoolean(l4, true);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L72:
        drawable7 = typedarray1.getDrawable(l4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L70:
        drawable10 = typedarray1.getDrawable(l4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L73:
        drawable8 = typedarray1.getDrawable(l4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L71:
        drawable5 = typedarray1.getDrawable(l4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L91:
        drawable9 = typedarray1.getDrawable(l4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L92:
        drawable6 = typedarray1.getDrawable(l4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L97:
        colorstatelist3 = typedarray1.getColorStateList(l4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L98:
        mode = Drawable.parseTintMode(typedarray1.getInt(l4, -1), ((android.graphics.PorterDuff.Mode) (obj3)));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L74:
        i5 = typedarray1.getDimensionPixelSize(l4, i3);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L44:
        setMaxLines(typedarray1.getInt(l4, -1));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L36:
        setMaxHeight(typedarray1.getDimensionPixelSize(l4, -1));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L45:
        setLines(typedarray1.getInt(l4, -1));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L46:
        setHeight(typedarray1.getDimensionPixelSize(l4, -1));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L47:
        setMinLines(typedarray1.getInt(l4, -1));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L38:
        setMinHeight(typedarray1.getDimensionPixelSize(l4, -1));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L48:
        setMaxEms(typedarray1.getInt(l4, -1));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L35:
        setMaxWidth(typedarray1.getDimensionPixelSize(l4, -1));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L49:
        setEms(typedarray1.getInt(l4, -1));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L50:
        setWidth(typedarray1.getDimensionPixelSize(l4, -1));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L51:
        setMinEms(typedarray1.getInt(l4, -1));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L37:
        setMinWidth(typedarray1.getDimensionPixelSize(l4, -1));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L32:
        setGravity(typedarray1.getInt(l4, -1));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L41:
        charsequence1 = typedarray1.getText(l4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L40:
        flag23 = true;
        obj16 = typedarray1.getText(l4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L52:
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
        if(typedarray1.getBoolean(l4, false))
        {
            setHorizontallyScrolling(true);
            flag14 = flag1;
            f8 = f3;
            f13 = f9;
            f15 = f;
            l3 = byte0;
            flag16 = flag12;
            j4 = i4;
            obj12 = charsequence;
            drawable5 = drawable3;
            drawable6 = drawable4;
            drawable7 = drawable2;
            i5 = i3;
            drawable8 = drawable1;
            drawable9 = drawable;
            colorstatelist3 = colorstatelist1;
            mode = ((android.graphics.PorterDuff.Mode) (obj3));
            drawable10 = ((Drawable) (obj9));
            f16 = f11;
            f17 = f5;
            flag18 = flag10;
            flag20 = flag6;
            j5 = byte3;
            s1 = ((String) (obj1));
            flag22 = l;
            obj15 = obj11;
            obj = obj7;
            flag23 = flag5;
            charsequence1 = ((CharSequence) (obj13));
            charsequence2 = ((CharSequence) (obj14));
            k5 = i2;
            f18 = f1;
            l5 = j1;
            i6 = l2;
            flag24 = flag13;
            flag25 = flag11;
            f19 = f6;
            flag26 = flag8;
            j6 = k1;
            flag27 = flag3;
            k6 = k;
            obj16 = s;
            colorstatelist4 = colorstatelist;
            l6 = i1;
            colorstatelist5 = ((ColorStateList) (obj6));
            colorstatelist6 = colorstatelist2;
            i7 = j2;
            j7 = k2;
        }
          goto _L112
_L54:
        flag27 = typedarray1.getBoolean(l4, flag3);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L31:
        j5 = typedarray1.getInt(l4, byte3);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L77:
        setMarqueeRepeatLimit(typedarray1.getInt(l4, mMarqueeRepeatLimit));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L56:
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
        if(!typedarray1.getBoolean(l4, true))
        {
            setIncludeFontPadding(false);
            flag14 = flag1;
            f8 = f3;
            f13 = f9;
            f15 = f;
            l3 = byte0;
            flag16 = flag12;
            j4 = i4;
            obj12 = charsequence;
            drawable5 = drawable3;
            drawable6 = drawable4;
            drawable7 = drawable2;
            i5 = i3;
            drawable8 = drawable1;
            drawable9 = drawable;
            colorstatelist3 = colorstatelist1;
            mode = ((android.graphics.PorterDuff.Mode) (obj3));
            drawable10 = ((Drawable) (obj9));
            f16 = f11;
            f17 = f5;
            flag18 = flag10;
            flag20 = flag6;
            j5 = byte3;
            s1 = ((String) (obj1));
            flag22 = l;
            obj15 = obj11;
            obj = obj7;
            flag23 = flag5;
            charsequence1 = ((CharSequence) (obj13));
            charsequence2 = ((CharSequence) (obj14));
            k5 = i2;
            f18 = f1;
            l5 = j1;
            i6 = l2;
            flag24 = flag13;
            flag25 = flag11;
            f19 = f6;
            flag26 = flag8;
            j6 = k1;
            flag27 = flag3;
            k6 = k;
            obj16 = s;
            colorstatelist4 = colorstatelist;
            l6 = i1;
            colorstatelist5 = ((ColorStateList) (obj6));
            colorstatelist6 = colorstatelist2;
            i7 = j2;
            j7 = k2;
        }
          goto _L112
_L43:
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
        if(!typedarray1.getBoolean(l4, true))
        {
            setCursorVisible(false);
            flag14 = flag1;
            f8 = f3;
            f13 = f9;
            f15 = f;
            l3 = byte0;
            flag16 = flag12;
            j4 = i4;
            obj12 = charsequence;
            drawable5 = drawable3;
            drawable6 = drawable4;
            drawable7 = drawable2;
            i5 = i3;
            drawable8 = drawable1;
            drawable9 = drawable;
            colorstatelist3 = colorstatelist1;
            mode = ((android.graphics.PorterDuff.Mode) (obj3));
            drawable10 = ((Drawable) (obj9));
            f16 = f11;
            f17 = f5;
            flag18 = flag10;
            flag20 = flag6;
            j5 = byte3;
            s1 = ((String) (obj1));
            flag22 = l;
            obj15 = obj11;
            obj = obj7;
            flag23 = flag5;
            charsequence1 = ((CharSequence) (obj13));
            charsequence2 = ((CharSequence) (obj14));
            k5 = i2;
            f18 = f1;
            l5 = j1;
            i6 = l2;
            flag24 = flag13;
            flag25 = flag11;
            f19 = f6;
            flag26 = flag8;
            j6 = k1;
            flag27 = flag3;
            k6 = k;
            obj16 = s;
            colorstatelist4 = colorstatelist;
            l6 = i1;
            colorstatelist5 = ((ColorStateList) (obj6));
            colorstatelist6 = colorstatelist2;
            i7 = j2;
            j7 = k2;
        }
          goto _L112
_L57:
        l5 = typedarray1.getInt(l4, -1);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L42:
        setTextScaleX(typedarray1.getFloat(l4, 1.0F));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L69:
        mFreezesText = typedarray1.getBoolean(l4, false);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L58:
        j6 = typedarray1.getInt(l4, 0);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L59:
        f16 = typedarray1.getFloat(l4, 0.0F);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L60:
        f17 = typedarray1.getFloat(l4, 0.0F);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L61:
        f19 = typedarray1.getFloat(l4, 0.0F);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L22:
        setEnabled(typedarray1.getBoolean(l4, isEnabled()));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L28:
        l6 = typedarray1.getColor(l4, i1);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L27:
        colorstatelist4 = typedarray1.getColorStateList(l4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L29:
        colorstatelist5 = typedarray1.getColorStateList(l4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L30:
        colorstatelist6 = typedarray1.getColorStateList(l4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        i7 = j2;
        j7 = k2;
          goto _L112
_L24:
        i7 = typedarray1.getDimensionPixelSize(l4, j2);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        j7 = k2;
          goto _L112
_L25:
        j7 = typedarray1.getInt(l4, k2);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
          goto _L112
_L26:
        k6 = typedarray1.getInt(l4, k);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L93:
        obj = obj7;
        if(context.isRestricted())
            break MISSING_BLOCK_LABEL_14987;
        obj = obj7;
        if(!context.canLoadUnsafeResources())
            break MISSING_BLOCK_LABEL_14987;
        try
        {
            obj = typedarray1.getFont(l4);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            obj = obj7;
        }
        if(obj == null)
            obj1 = typedarray1.getString(l4);
        flag22 = true;
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        obj15 = obj11;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L53:
        flag24 = typedarray1.getBoolean(l4, flag13);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L75:
        mSpacingAdd = typedarray1.getDimensionPixelSize(l4, (int)mSpacingAdd);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L76:
        mSpacingMult = typedarray1.getFloat(l4, mSpacingMult);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L78:
        k5 = typedarray1.getInt(l4, 0);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L101:
        createEditorIfNeeded();
        mEditor.mAllowUndo = typedarray1.getBoolean(l4, true);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L81:
        createEditorIfNeeded();
        mEditor.createInputContentTypeIfNeeded();
        mEditor.mInputContentType.imeOptions = typedarray1.getInt(l4, mEditor.mInputContentType.imeOptions);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L82:
        createEditorIfNeeded();
        mEditor.createInputContentTypeIfNeeded();
        mEditor.mInputContentType.imeActionLabel = typedarray1.getText(l4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L83:
        createEditorIfNeeded();
        mEditor.createInputContentTypeIfNeeded();
        mEditor.mInputContentType.imeActionId = typedarray1.getInt(l4, mEditor.mInputContentType.imeActionId);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L79:
        setPrivateImeOptions(typedarray1.getString(l4));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L80:
        setInputExtras(typedarray1.getResourceId(l4, 0));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
        obj;
        Log.w("TextView", "Failure reading input extras", ((Throwable) (obj)));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
        obj;
        Log.w("TextView", "Failure reading input extras", ((Throwable) (obj)));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L88:
        mCursorDrawableRes = typedarray1.getResourceId(l4, 0);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L84:
        mTextSelectHandleLeftRes = typedarray1.getResourceId(l4, 0);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L85:
        mTextSelectHandleRightRes = typedarray1.getResourceId(l4, 0);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L86:
        mTextSelectHandleRes = typedarray1.getResourceId(l4, 0);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L89:
        mTextEditSuggestionItemLayout = typedarray1.getResourceId(l4, 0);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L108:
        mTextEditSuggestionContainerLayout = typedarray1.getResourceId(l4, 0);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L109:
        mTextEditSuggestionHighlightStyle = typedarray1.getResourceId(l4, 0);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L87:
        setTextIsSelectable(typedarray1.getBoolean(l4, false));
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L90:
        flag14 = typedarray1.getBoolean(l4, false);
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L94:
        flag20 = typedarray1.getBoolean(l4, false);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L95:
        f18 = typedarray1.getFloat(l4, 0.0F);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L96:
        obj15 = typedarray1.getString(l4);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L99:
        mBreakStrategy = typedarray1.getInt(l4, 0);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L100:
        mHyphenationFrequency = typedarray1.getInt(l4, 0);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L102:
        mAutoSizeTextType = typedarray1.getInt(l4, 0);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L103:
        f15 = typedarray1.getDimension(l4, -1F);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L105:
        f13 = typedarray1.getDimension(l4, -1F);
        flag14 = flag1;
        f8 = f3;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L106:
        f8 = typedarray1.getDimension(l4, -1F);
        flag14 = flag1;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
_L104:
        l4 = typedarray1.getResourceId(l4, 0);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
        if(l4 > 0)
        {
            obj = typedarray1.getResources().obtainTypedArray(l4);
            setupAutoSizeUniformPresetSizes(((TypedArray) (obj)));
            ((TypedArray) (obj)).recycle();
            flag14 = flag1;
            f8 = f3;
            f13 = f9;
            f15 = f;
            l3 = byte0;
            flag16 = flag12;
            j4 = i4;
            obj12 = charsequence;
            drawable5 = drawable3;
            drawable6 = drawable4;
            drawable7 = drawable2;
            i5 = i3;
            drawable8 = drawable1;
            drawable9 = drawable;
            colorstatelist3 = colorstatelist1;
            mode = ((android.graphics.PorterDuff.Mode) (obj3));
            drawable10 = ((Drawable) (obj9));
            f16 = f11;
            f17 = f5;
            flag18 = flag10;
            flag20 = flag6;
            j5 = byte3;
            s1 = ((String) (obj1));
            flag22 = l;
            obj15 = obj11;
            obj = obj7;
            flag23 = flag5;
            charsequence1 = ((CharSequence) (obj13));
            charsequence2 = ((CharSequence) (obj14));
            k5 = i2;
            f18 = f1;
            l5 = j1;
            i6 = l2;
            flag24 = flag13;
            flag25 = flag11;
            f19 = f6;
            flag26 = flag8;
            j6 = k1;
            flag27 = flag3;
            k6 = k;
            obj16 = s;
            colorstatelist4 = colorstatelist;
            l6 = i1;
            colorstatelist5 = ((ColorStateList) (obj6));
            colorstatelist6 = colorstatelist2;
            i7 = j2;
            j7 = k2;
        }
          goto _L112
        mJustificationMode = typedarray1.getInt(l4, 0);
        flag14 = flag1;
        f8 = f3;
        f13 = f9;
        f15 = f;
        l3 = byte0;
        flag16 = flag12;
        j4 = i4;
        obj12 = charsequence;
        drawable5 = drawable3;
        drawable6 = drawable4;
        drawable7 = drawable2;
        i5 = i3;
        drawable8 = drawable1;
        drawable9 = drawable;
        colorstatelist3 = colorstatelist1;
        mode = ((android.graphics.PorterDuff.Mode) (obj3));
        drawable10 = ((Drawable) (obj9));
        f16 = f11;
        f17 = f5;
        flag18 = flag10;
        flag20 = flag6;
        j5 = byte3;
        s1 = ((String) (obj1));
        flag22 = l;
        obj15 = obj11;
        obj = obj7;
        flag23 = flag5;
        charsequence1 = ((CharSequence) (obj13));
        charsequence2 = ((CharSequence) (obj14));
        k5 = i2;
        f18 = f1;
        l5 = j1;
        i6 = l2;
        flag24 = flag13;
        flag25 = flag11;
        f19 = f6;
        flag26 = flag8;
        j6 = k1;
        flag27 = flag3;
        k6 = k;
        obj16 = s;
        colorstatelist4 = colorstatelist;
        l6 = i1;
        colorstatelist5 = ((ColorStateList) (obj6));
        colorstatelist6 = colorstatelist2;
        i7 = j2;
        j7 = k2;
          goto _L112
        typedarray1.recycle();
        obj12 = BufferType.EDITABLE;
        int k3 = i2 & 0xfff;
        boolean flag15;
        boolean flag17;
        boolean flag19;
        boolean flag21;
        if(k3 == 129)
            flag15 = true;
        else
            flag15 = false;
        if(k3 == 225)
            flag17 = true;
        else
            flag17 = false;
        if(k3 == 18)
            flag19 = true;
        else
            flag19 = false;
        if(context.getApplicationInfo().targetSdkVersion >= 26)
            flag21 = true;
        else
            flag21 = false;
        mUseInternationalizedInput = flag21;
        if(obj14 == null) goto _L114; else goto _L113
_L113:
        try
        {
            obj = Class.forName(((CharSequence) (obj14)).toString());
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new RuntimeException(context);
        }
        try
        {
            createEditorIfNeeded();
            mEditor.mKeyListener = (KeyListener)((Class) (obj)).newInstance();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new RuntimeException(context);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new RuntimeException(context);
        }
        obj = mEditor;
        if(i2 == 0) goto _L116; else goto _L115
_L115:
        obj.mInputType = i2;
        obj = obj12;
_L129:
        if(mEditor != null)
            mEditor.adjustInputType(flag13, flag15, flag17, flag19);
        obj14 = obj;
        if(flag8)
        {
            createEditorIfNeeded();
            mEditor.mSelectAllOnFocus = true;
            obj14 = obj;
            if(obj == BufferType.NORMAL)
                obj14 = BufferType.SPANNABLE;
        }
        if(colorstatelist1 != null || obj3 != null)
        {
            if(mDrawables == null)
                mDrawables = new Drawables(context);
            if(colorstatelist1 != null)
            {
                mDrawables.mTintList = colorstatelist1;
                mDrawables.mHasTint = true;
            }
            if(obj3 != null)
            {
                mDrawables.mTintMode = ((android.graphics.PorterDuff.Mode) (obj3));
                mDrawables.mHasTintMode = true;
            }
        }
        setCompoundDrawablesWithIntrinsicBounds(drawable2, ((Drawable) (obj9)), drawable1, drawable3);
        setRelativeDrawablesIfNeeded(drawable, drawable4);
        setCompoundDrawablePadding(i3);
        setInputTypeSingleLine(flag3);
        applySingleLine(flag3, flag3, flag3);
        i2 = byte3;
        if(flag3)
        {
            i2 = byte3;
            if(getKeyListener() == null)
            {
                i2 = byte3;
                if(byte3 < 0)
                    i2 = 3;
            }
        }
        i2;
        JVM INSTR tableswitch 1 4: default 22308
    //                   1 23231
    //                   2 23241
    //                   3 23251
    //                   4 23261;
           goto _L117 _L118 _L119 _L120 _L121
_L117:
        if(colorstatelist == null)
            colorstatelist = ColorStateList.valueOf(0xff000000);
        setTextColor(colorstatelist);
        setHintTextColor(((ColorStateList) (obj6)));
        setLinkTextColor(colorstatelist2);
        if(i1 != 0)
            setHighlightColor(i1);
        setRawTextSize(j2, true);
        setElegantTextHeight(flag6);
        setLetterSpacing(f1);
        setFontFeatureSettings(((String) (obj11)));
        if(flag1)
            setTransformationMethod(new AllCapsTransformationMethod(getContext()));
        if(flag13 || flag15 || flag17 || flag19)
        {
            setTransformationMethod(PasswordTransformationMethod.getInstance());
            i2 = 3;
        } else
        {
            i2 = k2;
            if(mEditor != null)
            {
                i2 = k2;
                if((mEditor.mInputType & 0xfff) == 129)
                    i2 = 3;
            }
        }
        obj = obj1;
        if(i2 != -1)
        {
            obj = obj1;
            if(l ^ true)
                obj = null;
        }
        setTypefaceFromAttrs(((Typeface) (obj7)), ((String) (obj)), i2, k);
        if(k1 != 0)
            setShadowLayer(f6, f11, f5, k1);
        if(j1 >= 0)
            setFilters(new InputFilter[] {
                new android.text.InputFilter.LengthFilter(j1)
            });
        else
            setFilters(NO_FILTERS);
        setText(s, ((BufferType) (obj14)));
        if(flag5)
            mTextFromResource = true;
        if(obj13 != null)
            setHint(((CharSequence) (obj13)));
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.View, i, j);
        if(mMovement != null || getKeyListener() != null)
            i = 1;
        else
            i = 0;
        if(i == 0)
            flag3 = isClickable();
        else
            flag3 = true;
        if(i == 0)
            flag1 = isLongClickable();
        else
            flag1 = true;
        i = getFocusable();
        i2 = context.getIndexCount();
        j = 0;
_L128:
        if(j >= i2) goto _L123; else goto _L122
_L122:
        k2 = context.getIndex(j);
        k2;
        JVM INSTR lookupswitch 3: default 22640
    //                   19: 23380
    //                   30: 23464
    //                   31: 23481;
           goto _L124 _L125 _L126 _L127
_L124:
        flag8 = flag1;
        flag6 = flag3;
_L135:
        j++;
        flag3 = flag6;
        flag1 = flag8;
          goto _L128
_L116:
        try
        {
            i2 = mEditor.mKeyListener.getInputType();
            continue; /* Loop/switch isn't completed */
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            mEditor.mInputType = 1;
            obj = obj12;
        }
          goto _L129
_L114:
        if(charsequence != null)
        {
            createEditorIfNeeded();
            mEditor.mKeyListener = DigitsKeyListener.getInstance(charsequence.toString());
            obj = mEditor;
            if(i2 == 0)
                i2 = 1;
            obj.mInputType = i2;
            obj = obj12;
        } else
        if(i2 != 0)
        {
            setInputType(i2, true);
            flag3 = isMultilineInputType(i2) ^ true;
            obj = obj12;
        } else
        if(flag11)
        {
            createEditorIfNeeded();
            mEditor.mKeyListener = DialerKeyListener.getInstance();
            mEditor.mInputType = 3;
            obj = obj12;
        } else
        {
label0:
            {
                if(l2 == 0)
                    break label0;
                createEditorIfNeeded();
                obj = mEditor;
                if((l2 & 2) != 0)
                    flag11 = true;
                else
                    flag11 = false;
                if((l2 & 4) != 0)
                    flag10 = true;
                else
                    flag10 = false;
                obj.mKeyListener = DigitsKeyListener.getInstance(null, flag11, flag10);
                i2 = mEditor.mKeyListener.getInputType();
                mEditor.mInputType = i2;
                obj = obj12;
            }
        }
          goto _L129
        if(!flag12 && byte0 == -1)
            break MISSING_BLOCK_LABEL_23078;
        i2 = 1;
        byte0;
        JVM INSTR tableswitch 1 3: default 23000
    //                   1 23039
    //                   2 23052
    //                   3 23065;
           goto _L130 _L131 _L132 _L133
_L133:
        break MISSING_BLOCK_LABEL_23065;
_L130:
        obj = android.text.method.TextKeyListener.Capitalize.NONE;
_L134:
        createEditorIfNeeded();
        mEditor.mKeyListener = TextKeyListener.getInstance(flag12, ((android.text.method.TextKeyListener.Capitalize) (obj)));
        mEditor.mInputType = i2;
        obj = obj12;
          goto _L129
_L131:
        obj = android.text.method.TextKeyListener.Capitalize.SENTENCES;
        i2 = 16385;
          goto _L134
_L132:
        obj = android.text.method.TextKeyListener.Capitalize.WORDS;
        i2 = 8193;
          goto _L134
        obj = android.text.method.TextKeyListener.Capitalize.CHARACTERS;
        i2 = 4097;
          goto _L134
        if(flag10)
        {
            createEditorIfNeeded();
            mEditor.mKeyListener = TextKeyListener.getInstance();
            mEditor.mInputType = 1;
            obj = obj12;
        } else
        if(isTextSelectable())
        {
            if(mEditor != null)
            {
                mEditor.mKeyListener = null;
                mEditor.mInputType = 0;
            }
            obj = BufferType.SPANNABLE;
            setMovementMethod(ArrowKeyMovementMethod.getInstance());
        } else
        {
            if(mEditor != null)
                mEditor.mKeyListener = null;
            switch(i4)
            {
            default:
                obj = obj12;
                break;

            case 0: // '\0'
                obj = BufferType.NORMAL;
                break;

            case 1: // '\001'
                obj = BufferType.SPANNABLE;
                break;

            case 2: // '\002'
                obj = BufferType.EDITABLE;
                break;
            }
            continue; /* Loop/switch isn't completed */
        }
          goto _L129
_L118:
        setEllipsize(android.text.TextUtils.TruncateAt.START);
          goto _L117
_L119:
        setEllipsize(android.text.TextUtils.TruncateAt.MIDDLE);
          goto _L117
_L120:
        setEllipsize(android.text.TextUtils.TruncateAt.END);
          goto _L117
_L121:
        if(ViewConfiguration.get(context).isFadingMarqueeEnabled())
        {
            setHorizontalFadingEdgeEnabled(true);
            mMarqueeFadeMode = 0;
        } else
        {
            setHorizontalFadingEdgeEnabled(false);
            mMarqueeFadeMode = 1;
        }
        setEllipsize(android.text.TextUtils.TruncateAt.MARQUEE);
          goto _L117
_L125:
        attributeset = new TypedValue();
        flag6 = flag3;
        flag8 = flag1;
        if(context.getValue(k2, attributeset))
            if(((TypedValue) (attributeset)).type == 18)
            {
                if(((TypedValue) (attributeset)).data == 0)
                {
                    i = 0;
                    flag6 = flag3;
                    flag8 = flag1;
                } else
                {
                    i = 1;
                    flag6 = flag3;
                    flag8 = flag1;
                }
            } else
            {
                i = ((TypedValue) (attributeset)).data;
                flag6 = flag3;
                flag8 = flag1;
            }
          goto _L135
_L126:
        flag6 = context.getBoolean(k2, flag3);
        flag8 = flag1;
          goto _L135
_L127:
        flag8 = context.getBoolean(k2, flag1);
        flag6 = flag3;
          goto _L135
_L123:
        context.recycle();
        if(i != getFocusable())
            setFocusable(i);
        setClickable(flag3);
        setLongClickable(flag1);
        if(mEditor != null)
            mEditor.prepareCursorControllers();
        if(getImportantForAccessibility() == 0)
            setImportantForAccessibility(1);
        if(supportsAutoSizeText())
        {
            if(mAutoSizeTextType == 1)
            {
                if(!mHasPresetAutoSizeValues)
                {
                    context = getResources().getDisplayMetrics();
                    float f2 = f9;
                    if(f9 == -1F)
                        f2 = TypedValue.applyDimension(2, 12F, context);
                    f9 = f3;
                    if(f3 == -1F)
                        f9 = TypedValue.applyDimension(2, 112F, context);
                    f3 = f;
                    if(f == -1F)
                        f3 = 1.0F;
                    validateAndSetAutoSizeTextTypeUniformConfiguration(f2, f9, f3);
                }
                setupAutoSizeText();
            }
        } else
        {
            mAutoSizeTextType = 0;
        }
        return;
        if(true) goto _L129; else goto _L136
_L136:
        if(true) goto _L115; else goto _L137
_L137:
    }

    private void applyCompoundDrawableTint()
    {
        if(mDrawables == null)
            return;
        if(mDrawables.mHasTint || mDrawables.mHasTintMode)
        {
            ColorStateList colorstatelist = mDrawables.mTintList;
            android.graphics.PorterDuff.Mode mode = mDrawables.mTintMode;
            boolean flag = mDrawables.mHasTint;
            boolean flag1 = mDrawables.mHasTintMode;
            int ai[] = getDrawableState();
            Drawable adrawable[] = mDrawables.mShowing;
            int i = 0;
            int j = adrawable.length;
            do
            {
                if(i >= j)
                    break;
                Drawable drawable = adrawable[i];
                if(drawable != null && drawable != mDrawables.mDrawableError)
                {
                    drawable.mutate();
                    if(flag)
                        drawable.setTintList(colorstatelist);
                    if(flag1)
                        drawable.setTintMode(mode);
                    if(drawable.isStateful())
                        drawable.setState(ai);
                }
                i++;
            } while(true);
        }
    }

    private void applySingleLine(boolean flag, boolean flag1, boolean flag2)
    {
        mSingleLine = flag;
        if(!flag) goto _L2; else goto _L1
_L1:
        setLines(1);
        setHorizontallyScrolling(true);
        if(flag1)
            setTransformationMethod(SingleLineTransformationMethod.getInstance());
_L4:
        return;
_L2:
        if(flag2)
            setMaxLines(0x7fffffff);
        setHorizontallyScrolling(false);
        if(flag1)
            setTransformationMethod(null);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void assumeLayout()
    {
        int i = mRight - mLeft - getCompoundPaddingLeft() - getCompoundPaddingRight();
        int j = i;
        if(i < 1)
            j = 0;
        i = j;
        if(mHorizontallyScrolling)
            i = 0x100000;
        makeNewLayout(i, j, UNKNOWN_BORING, UNKNOWN_BORING, j, false);
    }

    private void autoSizeText()
    {
        if(!isAutoSizeEnabled())
            return;
        if(!mNeedsAutoSizeText) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        if(getMeasuredWidth() <= 0 || getMeasuredHeight() <= 0)
            return;
        if(mHorizontallyScrolling)
            i = 0x100000;
        else
            i = getMeasuredWidth() - getTotalPaddingLeft() - getTotalPaddingRight();
        j = getMeasuredHeight() - getExtendedPaddingBottom() - getExtendedPaddingTop();
        if(i <= 0 || j <= 0)
            return;
        RectF rectf = TEMP_RECTF;
        rectf;
        JVM INSTR monitorenter ;
        TEMP_RECTF.setEmpty();
        TEMP_RECTF.right = i;
        TEMP_RECTF.bottom = j;
        float f = findLargestTextSizeWhichFits(TEMP_RECTF);
        if(f != getTextSize())
        {
            setTextSizeInternal(0, f, false);
            makeNewLayout(i, 0, UNKNOWN_BORING, UNKNOWN_BORING, mRight - mLeft - getCompoundPaddingLeft() - getCompoundPaddingRight(), false);
        }
        rectf;
        JVM INSTR monitorexit ;
_L2:
        mNeedsAutoSizeText = true;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean bringTextIntoView()
    {
        Layout layout;
        int i;
        android.text.Layout.Alignment alignment;
        int j;
        int k;
        int l;
        int i1;
        android.text.Layout.Alignment alignment1;
        int j1;
        if(isShowingHint())
            layout = mHintLayout;
        else
            layout = mLayout;
        i = 0;
        if((mGravity & 0x70) == 80)
            i = layout.getLineCount() - 1;
        alignment = layout.getParagraphAlignment(i);
        j = layout.getParagraphDirection(i);
        k = mRight - mLeft - getCompoundPaddingLeft() - getCompoundPaddingRight();
        l = mBottom - mTop - getExtendedPaddingTop() - getExtendedPaddingBottom();
        i1 = layout.getHeight();
        if(alignment == android.text.Layout.Alignment.ALIGN_NORMAL)
        {
            if(j == 1)
                alignment1 = android.text.Layout.Alignment.ALIGN_LEFT;
            else
                alignment1 = android.text.Layout.Alignment.ALIGN_RIGHT;
        } else
        {
            alignment1 = alignment;
            if(alignment == android.text.Layout.Alignment.ALIGN_OPPOSITE)
                if(j == 1)
                    alignment1 = android.text.Layout.Alignment.ALIGN_RIGHT;
                else
                    alignment1 = android.text.Layout.Alignment.ALIGN_LEFT;
        }
        if(alignment1 == android.text.Layout.Alignment.ALIGN_CENTER)
        {
            j1 = (int)Math.floor(layout.getLineLeft(i));
            i = (int)Math.ceil(layout.getLineRight(i));
            if(i - j1 < k)
                i = (i + j1) / 2 - k / 2;
            else
            if(j < 0)
                i -= k;
            else
                i = j1;
        } else
        if(alignment1 == android.text.Layout.Alignment.ALIGN_RIGHT)
            i = (int)Math.ceil(layout.getLineRight(i)) - k;
        else
            i = (int)Math.floor(layout.getLineLeft(i));
        if(i1 < l)
            j1 = 0;
        else
        if((mGravity & 0x70) == 80)
            j1 = i1 - l;
        else
            j1 = 0;
        if(i != mScrollX || j1 != mScrollY)
        {
            scrollTo(i, j1);
            return true;
        } else
        {
            return false;
        }
    }

    private boolean canMarquee()
    {
        boolean flag = true;
        int i = mRight - mLeft - getCompoundPaddingLeft() - getCompoundPaddingRight();
        boolean flag1;
        if(i > 0)
        {
            flag1 = flag;
            if(mLayout.getLineWidth(0) <= (float)i)
                if(mMarqueeFadeMode != 0 && mSavedMarqueeModeLayout != null)
                {
                    if(mSavedMarqueeModeLayout.getLineWidth(0) > (float)i)
                        flag1 = flag;
                    else
                        flag1 = false;
                } else
                {
                    flag1 = false;
                }
        } else
        {
            flag1 = false;
        }
        return flag1;
    }

    private void changeListenerLocaleTo(Locale locale)
    {
        if(mListenerChanged)
            return;
        if(mEditor == null) goto _L2; else goto _L1
_L1:
        int i;
        KeyListener keylistener = mEditor.mKeyListener;
        boolean flag;
        if(keylistener instanceof DigitsKeyListener)
            locale = DigitsKeyListener.getInstance(locale, (DigitsKeyListener)keylistener);
        else
        if(keylistener instanceof DateKeyListener)
            locale = DateKeyListener.getInstance(locale);
        else
        if(keylistener instanceof TimeKeyListener)
            locale = TimeKeyListener.getInstance(locale);
        else
        if(keylistener instanceof DateTimeKeyListener)
            locale = DateTimeKeyListener.getInstance(locale);
        else
            return;
        flag = isPasswordInputType(mEditor.mInputType);
        setKeyListenerOnly(locale);
        setInputTypeFromEditor();
        if(!flag) goto _L2; else goto _L3
_L3:
        i = mEditor.mInputType & 0xf;
        if(i != 1) goto _L5; else goto _L4
_L4:
        locale = mEditor;
        locale.mInputType = ((Editor) (locale)).mInputType | 0x80;
_L2:
        return;
_L5:
        if(i == 2)
        {
            locale = mEditor;
            locale.mInputType = ((Editor) (locale)).mInputType | 0x10;
        }
        if(true) goto _L2; else goto _L6
_L6:
    }

    private void checkForRelayout()
    {
        if((mLayoutParams.width != -2 || mMaxWidthMode == mMinWidthMode && mMaxWidth == mMinWidth) && (mHint == null || mHintLayout != null) && mRight - mLeft - getCompoundPaddingLeft() - getCompoundPaddingRight() > 0)
        {
            int i = mLayout.getHeight();
            int j = mLayout.getWidth();
            int k;
            if(mHintLayout == null)
                k = 0;
            else
                k = mHintLayout.getWidth();
            makeNewLayout(j, k, UNKNOWN_BORING, UNKNOWN_BORING, mRight - mLeft - getCompoundPaddingLeft() - getCompoundPaddingRight(), false);
            if(mEllipsize != android.text.TextUtils.TruncateAt.MARQUEE)
            {
                if(mLayoutParams.height != -2 && mLayoutParams.height != -1)
                {
                    autoSizeText();
                    invalidate();
                    return;
                }
                if(mLayout.getHeight() == i && (mHintLayout == null || mHintLayout.getHeight() == i))
                {
                    autoSizeText();
                    invalidate();
                    return;
                }
            }
            requestLayout();
            invalidate();
        } else
        {
            nullLayouts();
            requestLayout();
            invalidate();
        }
    }

    private void checkForResize()
    {
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = false;
        if(mLayout == null) goto _L2; else goto _L1
_L1:
        if(mLayoutParams.width == -2)
        {
            flag1 = true;
            invalidate();
        }
        if(mLayoutParams.height != -2) goto _L4; else goto _L3
_L3:
        flag = flag1;
        if(getDesiredHeight() != getHeight())
            flag = true;
_L2:
        if(flag)
            requestLayout();
        return;
_L4:
        flag = flag1;
        if(mLayoutParams.height == -1)
        {
            flag = flag1;
            if(mDesiredHeightAtMeasure >= 0)
            {
                flag = flag1;
                if(getDesiredHeight() != mDesiredHeightAtMeasure)
                    flag = true;
            }
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private int[] cleanupAutoSizePresetSizes(int ai[])
    {
        int i = ai.length;
        if(i == 0)
            return ai;
        Arrays.sort(ai);
        IntArray intarray = new IntArray();
        for(int j = 0; j < i; j++)
        {
            int k = ai[j];
            if(k > 0 && intarray.binarySearch(k) < 0)
                intarray.add(k);
        }

        if(i != intarray.size())
            ai = intarray.toArray();
        return ai;
    }

    private void clearAutoSizeConfiguration()
    {
        mAutoSizeTextType = 0;
        mAutoSizeMinTextSizeInPx = -1F;
        mAutoSizeMaxTextSizeInPx = -1F;
        mAutoSizeStepGranularityInPx = -1F;
        mAutoSizeTextSizesInPx = EmptyArray.INT;
        mNeedsAutoSizeText = false;
    }

    private boolean compressText(float f)
    {
        if(isHardwareAccelerated())
            return false;
        if(f > 0.0F && mLayout != null && getLineCount() == 1 && mUserSetTextScaleX ^ true && mTextPaint.getTextScaleX() == 1.0F)
        {
            f = ((mLayout.getLineWidth(0) + 1.0F) - f) / f;
            if(f > 0.0F && f <= 0.07F)
            {
                mTextPaint.setTextScaleX(1.0F - f - 0.005F);
                post(new Runnable() {

                    public void run()
                    {
                        requestLayout();
                    }

                    final TextView this$0;

            
            {
                this$0 = TextView.this;
                super();
            }
                }
);
                return true;
            }
        }
        return false;
    }

    private void convertFromViewportToContentCoordinates(Rect rect)
    {
        int i = viewportToContentHorizontalOffset();
        rect.left = rect.left + i;
        rect.right = rect.right + i;
        i = viewportToContentVerticalOffset();
        rect.top = rect.top + i;
        rect.bottom = rect.bottom + i;
    }

    private void createEditorIfNeeded()
    {
        if(mEditor == null)
            mEditor = new Editor(this);
    }

    private static int desired(Layout layout)
    {
        int i = layout.getLineCount();
        CharSequence charsequence = layout.getText();
        float f = 0.0F;
        for(int j = 0; j < i - 1; j++)
            if(charsequence.charAt(layout.getLineEnd(j) - 1) != '\n')
                return -1;

        for(int k = 0; k < i; k++)
            f = Math.max(f, layout.getLineWidth(k));

        return (int)Math.ceil(f);
    }

    private int doKeyDown(int i, KeyEvent keyevent, KeyEvent keyevent1)
    {
        if(!isEnabled())
            return 0;
        if(keyevent.getRepeatCount() == 0 && KeyEvent.isModifierKey(i) ^ true)
            mPreventDefaultMovement = false;
        i;
        JVM INSTR lookupswitch 7: default 96
    //                   4: 308
    //                   23: 268
    //                   61: 284
    //                   66: 169
    //                   277: 331
    //                   278: 356
    //                   279: 381;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        if(mEditor == null || mEditor.mKeyListener == null) goto _L10; else goto _L9
_L9:
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(keyevent1 == null) goto _L12; else goto _L11
_L11:
        boolean flag2;
        beginBatchEdit();
        flag2 = mEditor.mKeyListener.onKeyOther(this, (Editable)mText, keyevent1);
        hideErrorIfUnchanged();
          goto _L13
_L5:
        if(keyevent.hasNoModifiers())
        {
            if(mEditor != null && mEditor.mInputContentType != null && mEditor.mInputContentType.onEditorActionListener != null && mEditor.mInputContentType.onEditorActionListener.onEditorAction(this, 0, keyevent))
            {
                mEditor.mInputContentType.enterDown = true;
                return -1;
            }
            if((keyevent.getFlags() & 0x10) != 0 || shouldAdvanceFocusOnEnter())
                return !hasOnClickListeners() ? -1 : 0;
        }
          goto _L1
_L3:
        if(keyevent.hasNoModifiers() && shouldAdvanceFocusOnEnter())
            return 0;
          goto _L1
_L4:
        if((keyevent.hasNoModifiers() || keyevent.hasModifiers(1)) && shouldAdvanceFocusOnTab())
            return 0;
          goto _L1
_L2:
        if(mEditor != null && mEditor.getTextActionMode() != null)
        {
            stopTextActionMode();
            return -1;
        }
          goto _L1
_L6:
        if(keyevent.hasNoModifiers() && canCut() && onTextContextMenuItem(0x1020020))
            return -1;
          goto _L1
_L7:
        if(keyevent.hasNoModifiers() && canCopy() && onTextContextMenuItem(0x1020021))
            return -1;
          goto _L1
_L8:
        if(keyevent.hasNoModifiers() && canPaste() && onTextContextMenuItem(0x1020022))
            return -1;
          goto _L1
_L13:
        flag1 = false;
        if(flag2)
        {
            endBatchEdit();
            return -1;
        }
        endBatchEdit();
_L12:
        if(flag1)
        {
            beginBatchEdit();
            flag2 = mEditor.mKeyListener.onKeyDown(this, (Editable)mText, i, keyevent);
            endBatchEdit();
            hideErrorIfUnchanged();
            if(flag2)
                return 1;
        }
        break; /* Loop/switch isn't completed */
        AbstractMethodError abstractmethoderror;
        abstractmethoderror;
        endBatchEdit();
        flag1 = flag;
        if(true) goto _L12; else goto _L10
        keyevent;
        endBatchEdit();
        throw keyevent;
_L10:
        if(mMovement == null || mLayout == null) goto _L15; else goto _L14
_L14:
        flag = true;
        flag1 = flag;
        if(keyevent1 == null)
            break MISSING_BLOCK_LABEL_538;
        flag2 = mMovement.onKeyOther(this, (Spannable)mText, keyevent1);
        flag1 = false;
        if(flag2)
            return -1;
        break MISSING_BLOCK_LABEL_538;
        keyevent1;
        flag1 = flag;
        if(flag1 && mMovement.onKeyDown(this, (Spannable)mText, i, keyevent))
        {
            if(keyevent.getRepeatCount() == 0 && KeyEvent.isModifierKey(i) ^ true)
                mPreventDefaultMovement = true;
            return 2;
        }
        if(keyevent.getSource() == 257 && isDirectionalNavigationKey(i))
            return -1;
_L15:
        if(mPreventDefaultMovement && KeyEvent.isModifierKey(i) ^ true)
            i = -1;
        else
            i = 0;
        return i;
    }

    private void ensureIterableTextForAccessibilitySelectable()
    {
        if(!(mText instanceof Spannable))
            setText(mText, BufferType.SPANNABLE);
    }

    private int findLargestTextSizeWhichFits(RectF rectf)
    {
        int i = mAutoSizeTextSizesInPx.length;
        if(i == 0)
            throw new IllegalStateException("No available text sizes to choose from.");
        int j = 0;
        int k = 1;
        for(i--; k <= i;)
        {
            j = (k + i) / 2;
            if(suggestedSizeFitsInSpace(mAutoSizeTextSizesInPx[j], rectf))
            {
                int l = j + 1;
                j = k;
                k = l;
            } else
            {
                i = j - 1;
                j = i;
            }
        }

        return mAutoSizeTextSizesInPx[j];
    }

    private void fixFocusableAndClickableSettings()
    {
        if(mMovement != null || mEditor != null && mEditor.mKeyListener != null)
        {
            setFocusable(1);
            setClickable(true);
            setLongClickable(true);
        } else
        {
            setFocusable(16);
            setClickable(false);
            setLongClickable(false);
        }
    }

    private int getBottomVerticalOffset(boolean flag)
    {
        boolean flag1 = false;
        int i = mGravity & 0x70;
        Layout layout = mLayout;
        Layout layout1 = layout;
        if(!flag)
        {
            layout1 = layout;
            if(mText.length() == 0)
            {
                layout1 = layout;
                if(mHintLayout != null)
                    layout1 = mHintLayout;
            }
        }
        int j = ((flag1) ? 1 : 0);
        if(i != 80)
        {
            int k = getBoxHeight(layout1);
            int l = layout1.getHeight();
            j = ((flag1) ? 1 : 0);
            if(l < k)
                if(i == 48)
                    j = k - l;
                else
                    j = k - l >> 1;
        }
        return j;
    }

    private int getBoxHeight(Layout layout)
    {
        Insets insets;
        int i;
        if(isLayoutModeOptical(mParent))
            insets = getOpticalInsets();
        else
            insets = Insets.NONE;
        if(layout == mHintLayout)
            i = getCompoundPaddingTop() + getCompoundPaddingBottom();
        else
            i = getExtendedPaddingTop() + getExtendedPaddingBottom();
        return (getMeasuredHeight() - i) + insets.top + insets.bottom;
    }

    private Locale getCustomLocaleForKeyListenerOrNull()
    {
        if(!mUseInternationalizedInput)
            return null;
        LocaleList localelist = getImeHintLocales();
        if(localelist == null)
            return null;
        else
            return localelist.get(0);
    }

    private int getDesiredHeight()
    {
        boolean flag = true;
        int i = getDesiredHeight(mLayout, true);
        Layout layout = mHintLayout;
        if(mEllipsize == null)
            flag = false;
        return Math.max(i, getDesiredHeight(layout, flag));
    }

    private int getDesiredHeight(Layout layout, boolean flag)
    {
        int i;
        Drawables drawables;
        int l;
        int i1;
        if(layout == null)
            return 0;
        i = layout.getHeight(flag);
        drawables = mDrawables;
        int j = i;
        if(drawables != null)
            j = Math.max(Math.max(i, drawables.mDrawableHeightLeft), drawables.mDrawableHeightRight);
        i = layout.getLineCount();
        l = getCompoundPaddingTop() + getCompoundPaddingBottom();
        i1 = j + l;
        if(mMaxMode == 1) goto _L2; else goto _L1
_L1:
        int k;
        int j1;
        k = Math.min(i1, mMaximum);
        j1 = i;
_L4:
        if(mMinMode == 1)
        {
            i = k;
            if(j1 < mMinimum)
                i = k + getLineHeight() * (mMinimum - j1);
        } else
        {
            i = Math.max(k, mMinimum);
        }
        return Math.max(i, getSuggestedMinimumHeight());
_L2:
        k = i1;
        j1 = i;
        if(!flag) goto _L4; else goto _L3
_L3:
        k = i1;
        j1 = i;
        if(i <= mMaximum) goto _L4; else goto _L5
_L5:
        if(layout instanceof DynamicLayout) goto _L7; else goto _L6
_L6:
        k = i1;
        j1 = i;
        if(!(layout instanceof BoringLayout)) goto _L4; else goto _L7
_L7:
        i = layout.getLineTop(mMaximum);
        k = i;
        if(drawables != null)
            k = Math.max(Math.max(i, drawables.mDrawableHeightLeft), drawables.mDrawableHeightRight);
        k += l;
        j1 = mMaximum;
          goto _L4
    }

    private float getHorizontalFadingEdgeStrength(float f, float f1)
    {
        int i = getHorizontalFadingEdgeLength();
        if(i == 0)
            return 0.0F;
        f = Math.abs(f - f1);
        if(f > (float)i)
            return 1.0F;
        else
            return f / (float)i;
    }

    private void getInterestingRect(Rect rect, int i)
    {
        convertFromViewportToContentCoordinates(rect);
        if(i == 0)
            rect.top = rect.top - getExtendedPaddingTop();
        if(i == mLayout.getLineCount() - 1)
            rect.bottom = rect.bottom + getExtendedPaddingBottom();
    }

    private android.text.Layout.Alignment getLayoutAlignment()
    {
        getTextAlignment();
        JVM INSTR tableswitch 1 6: default 44
    //                   1 50
    //                   2 150
    //                   3 157
    //                   4 164
    //                   5 171
    //                   6 193;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        android.text.Layout.Alignment alignment = android.text.Layout.Alignment.ALIGN_NORMAL;
_L9:
        return alignment;
_L2:
        switch(mGravity & 0x800007)
        {
        default:
            alignment = android.text.Layout.Alignment.ALIGN_NORMAL;
            break;

        case 8388611: 
            alignment = android.text.Layout.Alignment.ALIGN_NORMAL;
            break;

        case 8388613: 
            alignment = android.text.Layout.Alignment.ALIGN_OPPOSITE;
            break;

        case 3: // '\003'
            alignment = android.text.Layout.Alignment.ALIGN_LEFT;
            break;

        case 5: // '\005'
            alignment = android.text.Layout.Alignment.ALIGN_RIGHT;
            break;

        case 1: // '\001'
            alignment = android.text.Layout.Alignment.ALIGN_CENTER;
            break;
        }
        if(false)
            ;
        continue; /* Loop/switch isn't completed */
_L3:
        alignment = android.text.Layout.Alignment.ALIGN_NORMAL;
        continue; /* Loop/switch isn't completed */
_L4:
        alignment = android.text.Layout.Alignment.ALIGN_OPPOSITE;
        continue; /* Loop/switch isn't completed */
_L5:
        alignment = android.text.Layout.Alignment.ALIGN_CENTER;
        continue; /* Loop/switch isn't completed */
_L6:
        if(getLayoutDirection() == 1)
            alignment = android.text.Layout.Alignment.ALIGN_RIGHT;
        else
            alignment = android.text.Layout.Alignment.ALIGN_LEFT;
        continue; /* Loop/switch isn't completed */
_L7:
        if(getLayoutDirection() == 1)
            alignment = android.text.Layout.Alignment.ALIGN_LEFT;
        else
            alignment = android.text.Layout.Alignment.ALIGN_RIGHT;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public static int getTextColor(Context context, TypedArray typedarray, int i)
    {
        context = getTextColors(context, typedarray);
        if(context == null)
            return i;
        else
            return context.getDefaultColor();
    }

    public static ColorStateList getTextColors(Context context, TypedArray typedarray)
    {
        if(typedarray == null)
            throw new NullPointerException();
        TypedArray typedarray1 = context.obtainStyledAttributes(android.R.styleable.TextView);
        ColorStateList colorstatelist = typedarray1.getColorStateList(5);
        typedarray = colorstatelist;
        if(colorstatelist == null)
        {
            int i = typedarray1.getResourceId(1, 0);
            typedarray = colorstatelist;
            if(i != 0)
            {
                context = context.obtainStyledAttributes(i, android.R.styleable.TextAppearance);
                typedarray = context.getColorStateList(3);
                context.recycle();
            }
        }
        typedarray1.recycle();
        return typedarray;
    }

    private CharSequence getTextForAccessibility()
    {
        if(TextUtils.isEmpty(mText))
            return mHint;
        else
            return mTransformed;
    }

    private Locale getTextServicesLocale(boolean flag)
    {
        updateTextServicesLocaleAsync();
        Locale locale;
        if(mCurrentSpellCheckerLocaleCache == null && flag ^ true)
            locale = Locale.getDefault();
        else
            locale = mCurrentSpellCheckerLocaleCache;
        return locale;
    }

    private Path getUpdatedHighlightPath()
    {
label0:
        {
            Object obj = null;
            Paint paint = mHighlightPaint;
            int i = getSelectionStart();
            int j = getSelectionEnd();
            Path path = obj;
            if(mMovement == null)
                break label0;
            if(!isFocused())
            {
                path = obj;
                if(!isPressed())
                    break label0;
            }
            path = obj;
            if(i >= 0)
                if(i == j)
                {
                    path = obj;
                    if(mEditor != null)
                    {
                        path = obj;
                        if(mEditor.isCursorVisible())
                        {
                            path = obj;
                            if((SystemClock.uptimeMillis() - mEditor.mShowCursor) % 1000L < 500L)
                            {
                                if(mHighlightPathBogus)
                                {
                                    if(mHighlightPath == null)
                                        mHighlightPath = new Path();
                                    mHighlightPath.reset();
                                    mLayout.getCursorPath(i, mHighlightPath, mText);
                                    mEditor.updateCursorsPositions();
                                    mHighlightPathBogus = false;
                                }
                                paint.setColor(mCurTextColor);
                                paint.setStyle(android.graphics.Paint.Style.STROKE);
                                path = mHighlightPath;
                            }
                        }
                    }
                } else
                {
                    if(mHighlightPathBogus)
                    {
                        if(mHighlightPath == null)
                            mHighlightPath = new Path();
                        mHighlightPath.reset();
                        mLayout.getSelectionPath(i, j, mHighlightPath);
                        mHighlightPathBogus = false;
                    }
                    paint.setColor(mHighlightColor);
                    paint.setStyle(android.graphics.Paint.Style.FILL);
                    path = mHighlightPath;
                }
        }
        return path;
    }

    private boolean hasSpannableText()
    {
        boolean flag;
        if(mText != null)
            flag = mText instanceof Spannable;
        else
            flag = false;
        return flag;
    }

    private void invalidateCursor(int i, int j, int k)
    {
        if(i >= 0 || j >= 0 || k >= 0)
            invalidateRegion(Math.min(Math.min(i, j), k), Math.max(Math.max(i, j), k), true);
    }

    private boolean isAutoSizeEnabled()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(supportsAutoSizeText())
        {
            flag1 = flag;
            if(mAutoSizeTextType != 0)
                flag1 = true;
        }
        return flag1;
    }

    private boolean isAutofillable()
    {
        boolean flag = false;
        if(getAutofillType() != 0)
            flag = true;
        return flag;
    }

    private boolean isDirectionalNavigationKey(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 19: // '\023'
        case 20: // '\024'
        case 21: // '\025'
        case 22: // '\026'
            return true;
        }
    }

    private boolean isMarqueeFadeEnabled()
    {
        boolean flag = true;
        if(mEllipsize == android.text.TextUtils.TruncateAt.MARQUEE)
        {
            if(mMarqueeFadeMode == 1)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private static boolean isMultilineInputType(int i)
    {
        boolean flag;
        if((0x2000f & i) == 0x20001)
            flag = true;
        else
            flag = false;
        return flag;
    }

    static boolean isPasswordInputType(int i)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        i &= 0xfff;
        flag1 = flag;
        if(i == 129) goto _L2; else goto _L1
_L1:
        if(i != 225) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 18)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    private boolean isShowingHint()
    {
        boolean flag;
        if(TextUtils.isEmpty(mText))
            flag = TextUtils.isEmpty(mHint) ^ true;
        else
            flag = false;
        return flag;
    }

    private static boolean isVisiblePasswordInputType(int i)
    {
        boolean flag;
        if((i & 0xfff) == 145)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void notifyAutoFillManagerAfterTextChangedIfNeeded()
    {
        if(!isAutofillable())
            return;
        AutofillManager autofillmanager = (AutofillManager)mContext.getSystemService(android/view/autofill/AutofillManager);
        if(autofillmanager != null)
            autofillmanager.notifyValueChanged(this);
    }

    private void nullLayouts()
    {
        if((mLayout instanceof BoringLayout) && mSavedLayout == null)
            mSavedLayout = (BoringLayout)mLayout;
        if((mHintLayout instanceof BoringLayout) && mSavedHintLayout == null)
            mSavedHintLayout = (BoringLayout)mHintLayout;
        mHintLayout = null;
        mLayout = null;
        mSavedMarqueeModeLayout = null;
        mHintBoring = null;
        mBoring = null;
        if(mEditor != null)
            mEditor.prepareCursorControllers();
    }

    private void onProvideAutoStructureForAssistOrAutofill(ViewStructure viewstructure, boolean flag)
    {
        Layout layout;
        int i;
        boolean flag1;
        int j;
        int i1;
        int k1;
        if(!hasPasswordTransformationMethod())
            flag1 = isPasswordInputType(getInputType());
        else
            flag1 = true;
        if(flag)
            viewstructure.setDataIsSensitive(mTextFromResource ^ true);
        if(flag1 && !flag) goto _L2; else goto _L1
_L1:
        if(mLayout == null)
            assumeLayout();
        layout = mLayout;
        i = layout.getLineCount();
        if(i > 1) goto _L4; else goto _L3
_L3:
        CharSequence charsequence = getText();
        if(flag)
            viewstructure.setText(charsequence);
        else
            viewstructure.setText(charsequence, getSelectionStart(), getSelectionEnd());
_L6:
        if(!flag)
        {
            j = 0;
            i1 = getTypefaceStyle();
            if((i1 & 1) != 0)
                j = 1;
            k1 = j;
            if((i1 & 2) != 0)
                k1 = j | 2;
            i1 = mTextPaint.getFlags();
            j = k1;
            if((i1 & 0x20) != 0)
                j = k1 | 1;
            k1 = j;
            if((i1 & 8) != 0)
                k1 = j | 4;
            j = k1;
            if((i1 & 0x10) != 0)
                j = k1 | 8;
            viewstructure.setTextStyle(getTextSize(), getCurrentTextColor(), 1, j);
        }
_L2:
        viewstructure.setHint(getHint());
        viewstructure.setInputType(getInputType());
        return;
_L4:
        Object obj;
        int j1;
        int l1;
        int k2;
        int i3;
        int j3;
label0:
        {
            int ai[] = new int[2];
            getLocationInWindow(ai);
            j1 = ai[1];
            obj = this;
            for(android.view.ViewParent viewparent = getParent(); viewparent instanceof View; viewparent = ((View) (obj)).getParent())
                obj = (View)viewparent;

            int k = ((View) (obj)).getHeight();
            CharSequence charsequence1;
            int i2;
            int l2;
            if(j1 >= 0)
            {
                l1 = getLineAtCoordinateUnclamped(0.0F);
                j1 = getLineAtCoordinateUnclamped(k - 1);
            } else
            {
                l1 = getLineAtCoordinateUnclamped(-j1);
                j1 = getLineAtCoordinateUnclamped(k - 1 - j1);
            }
            i2 = l1 - (j1 - l1) / 2;
            k = i2;
            if(i2 < 0)
                k = 0;
            i2 = j1 + (j1 - l1) / 2;
            k2 = i2;
            if(i2 >= i)
                k2 = i - 1;
            i2 = layout.getLineStart(k);
            l2 = layout.getLineEnd(k2);
            i3 = getSelectionStart();
            j3 = getSelectionEnd();
            i = l2;
            k2 = i2;
            if(i3 < j3)
            {
                k = i2;
                if(i3 < i2)
                    k = i3;
                i = l2;
                k2 = k;
                if(j3 > l2)
                {
                    i = j3;
                    k2 = k;
                }
            }
            charsequence1 = getText();
            if(k2 <= 0)
            {
                obj = charsequence1;
                if(i >= charsequence1.length())
                    break label0;
            }
            obj = charsequence1.subSequence(k2, i);
        }
        if(flag)
        {
            viewstructure.setText(((CharSequence) (obj)));
        } else
        {
            viewstructure.setText(((CharSequence) (obj)), i3 - k2, j3 - k2);
            int ai2[] = new int[(j1 - l1) + 1];
            int ai1[] = new int[(j1 - l1) + 1];
            int j2 = getBaselineOffset();
            for(int l = l1; l <= j1; l++)
            {
                ai2[l - l1] = layout.getLineStart(l);
                ai1[l - l1] = layout.getLineBaseline(l) + j2;
            }

            viewstructure.setTextLines(ai2, ai1);
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    private int[] parseDimensionArray(TypedArray typedarray)
    {
        if(typedarray == null)
            return null;
        int ai[] = new int[typedarray.length()];
        for(int i = 0; i < ai.length; i++)
            ai[i] = typedarray.getDimensionPixelSize(i, 0);

        return ai;
    }

    private void paste(int i, int j, boolean flag)
    {
        ClipData clipdata = ((ClipboardManager)getContext().getSystemService("clipboard")).getPrimaryClip();
        if(clipdata != null)
        {
            boolean flag1 = false;
            int k = 0;
            while(k < clipdata.getItemCount()) 
            {
                Object obj;
                boolean flag2;
                if(flag)
                {
                    obj = clipdata.getItemAt(k).coerceToStyledText(getContext());
                } else
                {
                    obj = clipdata.getItemAt(k).coerceToText(getContext());
                    if(obj instanceof Spanned)
                        obj = ((CharSequence) (obj)).toString();
                }
                flag2 = flag1;
                if(obj != null)
                    if(!flag1)
                    {
                        Selection.setSelection((Spannable)mText, j);
                        ((Editable)mText).replace(i, j, ((CharSequence) (obj)));
                        flag2 = true;
                    } else
                    {
                        ((Editable)mText).insert(getSelectionEnd(), "\n");
                        ((Editable)mText).insert(getSelectionEnd(), ((CharSequence) (obj)));
                        flag2 = flag1;
                    }
                k++;
                flag1 = flag2;
            }
            sLastCutCopyOrTextChangedTime = 0L;
        }
    }

    private boolean performAccessibilityActionClick(Bundle bundle)
    {
        boolean flag1;
label0:
        {
            boolean flag = false;
            if(!isEnabled())
                return false;
            if(isClickable() || isLongClickable())
            {
                if(isFocusable() && isFocused() ^ true)
                    requestFocus();
                performClick();
                flag = true;
            }
            if(mMovement == null)
            {
                flag1 = flag;
                if(!onCheckIsTextEditor())
                    break label0;
            }
            flag1 = flag;
            if(!hasSpannableText())
                break label0;
            flag1 = flag;
            if(mLayout == null)
                break label0;
            if(!isTextEditable())
            {
                flag1 = flag;
                if(!isTextSelectable())
                    break label0;
            }
            flag1 = flag;
            if(isFocused())
            {
                bundle = InputMethodManager.peekInstance();
                viewClicked(bundle);
                flag1 = flag;
                if(!isTextSelectable())
                {
                    flag1 = flag;
                    if(mEditor.mShowSoftInputOnFocus)
                    {
                        flag1 = flag;
                        if(bundle != null)
                            flag1 = flag | bundle.showSoftInput(this, 0);
                    }
                }
            }
        }
        return flag1;
    }

    public static void preloadFontCache()
    {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.DEFAULT);
        paint.measureText("H");
    }

    private void prepareDrawableForDisplay(Drawable drawable)
    {
        if(drawable == null)
            return;
        drawable.setLayoutDirection(getLayoutDirection());
        if(drawable.isStateful())
        {
            drawable.setState(getDrawableState());
            drawable.jumpToCurrentState();
        }
    }

    private void registerForPreDraw()
    {
        if(!mPreDrawRegistered)
        {
            getViewTreeObserver().addOnPreDrawListener(this);
            mPreDrawRegistered = true;
        }
    }

    private void removeIntersectingNonAdjacentSpans(int i, int j, Class class1)
    {
        if(!(mText instanceof Editable))
            return;
        Editable editable = (Editable)mText;
        class1 = ((Class) (editable.getSpans(i, j, class1)));
        int k = class1.length;
        int l = 0;
        do
        {
label0:
            {
                if(l < k)
                {
                    int i1 = editable.getSpanStart(class1[l]);
                    if(editable.getSpanEnd(class1[l]) != i && i1 != j)
                        break label0;
                }
                return;
            }
            editable.removeSpan(class1[l]);
            l++;
        } while(true);
    }

    static void removeParcelableSpans(Spannable spannable, int i, int j)
    {
        Object aobj[] = spannable.getSpans(i, j, android/text/ParcelableSpan);
        for(i = aobj.length; i > 0;)
        {
            i--;
            spannable.removeSpan(aobj[i]);
        }

    }

    private void requestAutofill()
    {
        AutofillManager autofillmanager = (AutofillManager)mContext.getSystemService(android/view/autofill/AutofillManager);
        if(autofillmanager != null)
            autofillmanager.requestAutofill(this);
    }

    private void restartMarqueeIfNeeded()
    {
        if(mRestartMarquee && mEllipsize == android.text.TextUtils.TruncateAt.MARQUEE)
        {
            mRestartMarquee = false;
            startMarquee();
        }
    }

    private void sendBeforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        if(mListeners != null)
        {
            ArrayList arraylist = mListeners;
            int l = arraylist.size();
            for(int i1 = 0; i1 < l; i1++)
                ((TextWatcher)arraylist.get(i1)).beforeTextChanged(charsequence, i, j, k);

        }
        removeIntersectingNonAdjacentSpans(i, i + j, android/text/style/SpellCheckSpan);
        removeIntersectingNonAdjacentSpans(i, i + j, android/text/style/SuggestionSpan);
    }

    private void setFilters(Editable editable, InputFilter ainputfilter[])
    {
        if(mEditor != null)
        {
            boolean flag;
            boolean flag1;
            int i;
            int k;
            if(mEditor.mUndoInputFilter != null)
                flag = true;
            else
                flag = false;
            flag1 = mEditor.mKeyListener instanceof InputFilter;
            i = 0;
            if(flag)
                i = 1;
            k = i;
            if(flag1)
                k = i + 1;
            if(k > 0)
            {
                InputFilter ainputfilter1[] = new InputFilter[ainputfilter.length + k];
                System.arraycopy(ainputfilter, 0, ainputfilter1, 0, ainputfilter.length);
                int j = 0;
                if(flag)
                {
                    ainputfilter1[ainputfilter.length] = mEditor.mUndoInputFilter;
                    j = 1;
                }
                if(flag1)
                    ainputfilter1[ainputfilter.length + j] = (InputFilter)mEditor.mKeyListener;
                editable.setFilters(ainputfilter1);
                return;
            }
        }
        editable.setFilters(ainputfilter);
    }

    private void setInputType(int i, boolean flag)
    {
        int j;
        Object obj;
        boolean flag1 = true;
        j = i & 0xf;
        if(j == 1)
        {
            boolean flag2;
            if((0x8000 & i) != 0)
                flag2 = true;
            else
                flag2 = false;
            if((i & 0x1000) != 0)
                obj = android.text.method.TextKeyListener.Capitalize.CHARACTERS;
            else
            if((i & 0x2000) != 0)
                obj = android.text.method.TextKeyListener.Capitalize.WORDS;
            else
            if((i & 0x4000) != 0)
                obj = android.text.method.TextKeyListener.Capitalize.SENTENCES;
            else
                obj = android.text.method.TextKeyListener.Capitalize.NONE;
            obj = TextKeyListener.getInstance(flag2, ((android.text.method.TextKeyListener.Capitalize) (obj)));
            j = i;
        } else
        {
label0:
            {
                if(j != 2)
                    break label0;
                Locale locale = getCustomLocaleForKeyListenerOrNull();
                boolean flag3;
                DigitsKeyListener digitskeylistener;
                if((i & 0x1000) != 0)
                    flag3 = true;
                else
                    flag3 = false;
                if((i & 0x2000) == 0)
                    flag1 = false;
                digitskeylistener = DigitsKeyListener.getInstance(locale, flag3, flag1);
                obj = digitskeylistener;
                j = i;
                if(locale != null)
                {
                    int k = digitskeylistener.getInputType();
                    obj = digitskeylistener;
                    j = i;
                    if((k & 0xf) != 2)
                    {
                        j = k;
                        if((i & 0x10) != 0)
                            j = k | 0x80;
                        obj = digitskeylistener;
                    }
                }
            }
        }
_L4:
        setRawInputType(j);
        mListenerChanged = false;
        Object obj1;
        if(flag)
        {
            createEditorIfNeeded();
            mEditor.mKeyListener = ((KeyListener) (obj));
        } else
        {
            setKeyListenerOnly(((KeyListener) (obj)));
        }
        return;
        if(j != 4)
            break MISSING_BLOCK_LABEL_341;
        obj = getCustomLocaleForKeyListenerOrNull();
        i & 0xff0;
        JVM INSTR lookupswitch 2: default 284
    //                   16: 321
    //                   32: 331;
           goto _L1 _L2 _L3
_L3:
        break MISSING_BLOCK_LABEL_331;
_L1:
        obj1 = DateTimeKeyListener.getInstance(((Locale) (obj)));
_L5:
        obj = obj1;
        j = i;
        if(mUseInternationalizedInput)
        {
            j = ((KeyListener) (obj1)).getInputType();
            obj = obj1;
        }
          goto _L4
_L2:
        obj1 = DateKeyListener.getInstance(((Locale) (obj)));
          goto _L5
        obj1 = TimeKeyListener.getInstance(((Locale) (obj)));
          goto _L5
        if(j == 3)
        {
            obj = DialerKeyListener.getInstance();
            j = i;
        } else
        {
            obj = TextKeyListener.getInstance();
            j = i;
        }
          goto _L4
    }

    private void setInputTypeFromEditor()
    {
        try
        {
            mEditor.mInputType = mEditor.mKeyListener.getInputType();
        }
        catch(IncompatibleClassChangeError incompatibleclasschangeerror)
        {
            mEditor.mInputType = 1;
        }
        setInputTypeSingleLine(mSingleLine);
    }

    private void setInputTypeSingleLine(boolean flag)
    {
        if(mEditor != null && (mEditor.mInputType & 0xf) == 1)
            if(flag)
            {
                Editor editor = mEditor;
                editor.mInputType = editor.mInputType & 0xfffdffff;
            } else
            {
                Editor editor1 = mEditor;
                editor1.mInputType = editor1.mInputType | 0x20000;
            }
    }

    private void setKeyListenerOnly(KeyListener keylistener)
    {
        if(mEditor == null && keylistener == null)
            return;
        createEditorIfNeeded();
        if(mEditor.mKeyListener != keylistener)
        {
            mEditor.mKeyListener = keylistener;
            if(keylistener != null && (mText instanceof Editable) ^ true)
                setText(mText);
            setFilters((Editable)mText, mFilters);
        }
    }

    private void setPrimaryClip(ClipData clipdata)
    {
        ((ClipboardManager)getContext().getSystemService("clipboard")).setPrimaryClip(clipdata);
        sLastCutCopyOrTextChangedTime = SystemClock.uptimeMillis();
    }

    private void setRawTextSize(float f, boolean flag)
    {
        if(f != mTextPaint.getTextSize())
        {
            mTextPaint.setTextSize(f);
            if(flag && mLayout != null)
            {
                mNeedsAutoSizeText = false;
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    private void setRelativeDrawablesIfNeeded(Drawable drawable, Drawable drawable1)
    {
        boolean flag;
        if(drawable != null || drawable1 != null)
            flag = true;
        else
            flag = false;
        if(flag)
        {
            Object obj = mDrawables;
            Drawables drawables = ((Drawables) (obj));
            if(obj == null)
            {
                drawables = new Drawables(getContext());
                mDrawables = drawables;
            }
            mDrawables.mOverride = true;
            obj = drawables.mCompoundRect;
            int ai[] = getDrawableState();
            if(drawable != null)
            {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                drawable.setState(ai);
                drawable.copyBounds(((Rect) (obj)));
                drawable.setCallback(this);
                drawables.mDrawableStart = drawable;
                drawables.mDrawableSizeStart = ((Rect) (obj)).width();
                drawables.mDrawableHeightStart = ((Rect) (obj)).height();
            } else
            {
                drawables.mDrawableHeightStart = 0;
                drawables.mDrawableSizeStart = 0;
            }
            if(drawable1 != null)
            {
                drawable1.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight());
                drawable1.setState(ai);
                drawable1.copyBounds(((Rect) (obj)));
                drawable1.setCallback(this);
                drawables.mDrawableEnd = drawable1;
                drawables.mDrawableSizeEnd = ((Rect) (obj)).width();
                drawables.mDrawableHeightEnd = ((Rect) (obj)).height();
            } else
            {
                drawables.mDrawableHeightEnd = 0;
                drawables.mDrawableSizeEnd = 0;
            }
            resetResolvedDrawables();
            resolveDrawables();
            applyCompoundDrawableTint();
        }
    }

    private void setText(CharSequence charsequence, BufferType buffertype, boolean flag, int i)
    {
        Object obj;
        int i1;
        Object obj1;
        int j1;
        mTextFromResource = false;
        obj = charsequence;
        if(charsequence == null)
            obj = "";
        charsequence = ((CharSequence) (obj));
        if(!isSuggestionsEnabled())
            charsequence = removeSuggestionSpans(((CharSequence) (obj)));
        if(!mUserSetTextScaleX)
            mTextPaint.setTextScaleX(1.0F);
        if((charsequence instanceof Spanned) && ((Spanned)charsequence).getSpanStart(android.text.TextUtils.TruncateAt.MARQUEE) >= 0)
        {
            int j;
            int l;
            if(ViewConfiguration.get(mContext).isFadingMarqueeEnabled())
            {
                setHorizontalFadingEdgeEnabled(true);
                mMarqueeFadeMode = 0;
            } else
            {
                setHorizontalFadingEdgeEnabled(false);
                mMarqueeFadeMode = 1;
            }
            setEllipsize(android.text.TextUtils.TruncateAt.MARQUEE);
        }
        j = mFilters.length;
        l = 0;
        obj = charsequence;
        for(; l < j; l++)
        {
            charsequence = mFilters[l].filter(((CharSequence) (obj)), 0, ((CharSequence) (obj)).length(), EMPTY_SPANNED, 0, 0);
            if(charsequence != null)
                obj = charsequence;
        }

        i1 = i;
        int k;
        Object obj2;
        int k1;
        if(flag)
            if(mText != null)
            {
                i1 = mText.length();
                sendBeforeTextChanged(mText, 0, i1, ((CharSequence) (obj)).length());
            } else
            {
                sendBeforeTextChanged("", 0, 0, ((CharSequence) (obj)).length());
                i1 = i;
            }
        k = 0;
        i = k;
        if(mListeners != null)
        {
            i = k;
            if(mListeners.size() != 0)
                i = 1;
        }
        break MISSING_BLOCK_LABEL_250;
        if(buffertype == BufferType.EDITABLE || getKeyListener() != null || i != 0)
        {
            createEditorIfNeeded();
            mEditor.forgetUndoRedo();
            charsequence = mEditableFactory.newEditable(((CharSequence) (obj)));
            obj = charsequence;
            setFilters(charsequence, mFilters);
            obj1 = InputMethodManager.peekInstance();
            charsequence = ((CharSequence) (obj));
            if(obj1 != null)
            {
                ((InputMethodManager) (obj1)).restartInput(this);
                charsequence = ((CharSequence) (obj));
            }
        } else
        if(buffertype == BufferType.SPANNABLE || mMovement != null)
        {
            charsequence = mSpannableFactory.newSpannable(((CharSequence) (obj)));
        } else
        {
            charsequence = ((CharSequence) (obj));
            if(!(obj instanceof CharWrapper))
                charsequence = TextUtils.stringOrSpannedString(((CharSequence) (obj)));
        }
        obj1 = charsequence;
        obj2 = buffertype;
        if(mAutoLinkMask != 0)
        {
            if(buffertype == BufferType.EDITABLE || (charsequence instanceof Spannable))
                obj = (Spannable)charsequence;
            else
                obj = mSpannableFactory.newSpannable(charsequence);
            obj1 = charsequence;
            obj2 = buffertype;
            if(Linkify.addLinks(((Spannable) (obj)), mAutoLinkMask))
            {
                if(buffertype == BufferType.EDITABLE)
                    charsequence = BufferType.EDITABLE;
                else
                    charsequence = BufferType.SPANNABLE;
                mText = ((CharSequence) (obj));
                obj1 = obj;
                obj2 = charsequence;
                if(mLinksClickable)
                {
                    obj1 = obj;
                    obj2 = charsequence;
                    if(textCanBeSelected() ^ true)
                    {
                        setMovementMethod(LinkMovementMethod.getInstance());
                        obj2 = charsequence;
                        obj1 = obj;
                    }
                }
            }
        }
        mBufferType = ((BufferType) (obj2));
        mText = ((CharSequence) (obj1));
        if(mTransformation == null)
            mTransformed = ((CharSequence) (obj1));
        else
            mTransformed = mTransformation.getTransformation(((CharSequence) (obj1)), this);
        j1 = ((CharSequence) (obj1)).length();
        if(!(obj1 instanceof Spannable) || !(mAllowTransformationLengthChange ^ true))
            break MISSING_BLOCK_LABEL_761;
        buffertype = (Spannable)obj1;
        charsequence = (ChangeWatcher[])buffertype.getSpans(0, buffertype.length(), android/widget/TextView$ChangeWatcher);
        k1 = charsequence.length;
        for(k = 0; k < k1; k++)
            buffertype.removeSpan(charsequence[k]);

        break MISSING_BLOCK_LABEL_651;
        if(mChangeWatcher == null)
            mChangeWatcher = new ChangeWatcher(null);
        buffertype.setSpan(mChangeWatcher, 0, j1, 0x640012);
        if(mEditor != null)
            mEditor.addSpanWatchers(buffertype);
        if(mTransformation != null)
            buffertype.setSpan(mTransformation, 0, j1, 18);
        if(mMovement != null)
        {
            mMovement.initialize(this, (Spannable)obj1);
            if(mEditor != null)
                mEditor.mSelectionMoved = false;
        }
        if(mLayout != null)
            checkForRelayout();
        sendOnTextChanged(((CharSequence) (obj1)), 0, i1, j1);
        onTextChanged(((CharSequence) (obj1)), 0, i1, j1);
        notifyViewAccessibilityStateChangedIfNeeded(2);
        if(i != 0)
            sendAfterTextChanged((Editable)obj1);
        else
            notifyAutoFillManagerAfterTextChangedIfNeeded();
        if(mEditor != null)
            mEditor.prepareCursorControllers();
        return;
    }

    private void setTextSizeInternal(int i, float f, boolean flag)
    {
        Object obj = getContext();
        if(obj == null)
            obj = Resources.getSystem();
        else
            obj = ((Context) (obj)).getResources();
        setRawTextSize(TypedValue.applyDimension(i, f, ((Resources) (obj)).getDisplayMetrics()), flag);
    }

    private void setTypefaceFromAttrs(Typeface typeface, String s, int i, int j)
    {
        Typeface typeface1 = typeface;
        if(typeface != null || s == null) goto _L2; else goto _L1
_L1:
        s = Typeface.create(s, j);
_L4:
        if(s != null)
        {
            setTypeface(s);
            return;
        }
        break; /* Loop/switch isn't completed */
_L2:
        s = typeface1;
        if(typeface != null)
        {
            s = typeface1;
            if(typeface.getStyle() != j)
                s = Typeface.create(typeface, j);
        }
        if(true) goto _L4; else goto _L3
_L3:
        i;
        JVM INSTR tableswitch 1 3: default 84
    //                   1 92
    //                   2 99
    //                   3 106;
           goto _L5 _L6 _L7 _L8
_L5:
        setTypeface(s, j);
        return;
_L6:
        s = Typeface.SANS_SERIF;
        continue; /* Loop/switch isn't completed */
_L7:
        s = Typeface.SERIF;
        continue; /* Loop/switch isn't completed */
_L8:
        s = Typeface.MONOSPACE;
        if(true) goto _L5; else goto _L9
_L9:
    }

    private boolean setupAutoSizeText()
    {
        if(supportsAutoSizeText() && mAutoSizeTextType == 1)
        {
            if(!mHasPresetAutoSizeValues || mAutoSizeTextSizesInPx.length == 0)
            {
                int i = 1;
                for(float f = Math.round(mAutoSizeMinTextSizeInPx); Math.round(mAutoSizeStepGranularityInPx + f) <= Math.round(mAutoSizeMaxTextSizeInPx); f += mAutoSizeStepGranularityInPx)
                    i++;

                int ai[] = new int[i];
                float f1 = mAutoSizeMinTextSizeInPx;
                for(int j = 0; j < i; j++)
                {
                    ai[j] = Math.round(f1);
                    f1 += mAutoSizeStepGranularityInPx;
                }

                mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(ai);
            }
            mNeedsAutoSizeText = true;
        } else
        {
            mNeedsAutoSizeText = false;
        }
        return mNeedsAutoSizeText;
    }

    private void setupAutoSizeUniformPresetSizes(TypedArray typedarray)
    {
        int i = typedarray.length();
        int ai[] = new int[i];
        if(i > 0)
        {
            for(int j = 0; j < i; j++)
                ai[j] = typedarray.getDimensionPixelSize(j, -1);

            mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(ai);
            setupAutoSizeUniformPresetSizesConfiguration();
        }
    }

    private boolean setupAutoSizeUniformPresetSizesConfiguration()
    {
        int i = mAutoSizeTextSizesInPx.length;
        boolean flag;
        if(i > 0)
            flag = true;
        else
            flag = false;
        mHasPresetAutoSizeValues = flag;
        if(mHasPresetAutoSizeValues)
        {
            mAutoSizeTextType = 1;
            mAutoSizeMinTextSizeInPx = mAutoSizeTextSizesInPx[0];
            mAutoSizeMaxTextSizeInPx = mAutoSizeTextSizesInPx[i - 1];
            mAutoSizeStepGranularityInPx = -1F;
        }
        return mHasPresetAutoSizeValues;
    }

    private void shareSelectedText()
    {
        String s = getSelectedText();
        if(s != null && s.isEmpty() ^ true)
        {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.removeExtra("android.intent.extra.TEXT");
            intent.putExtra("android.intent.extra.TEXT", s);
            getContext().startActivity(Intent.createChooser(intent, null));
            Selection.setSelection((Spannable)mText, getSelectionEnd());
        }
    }

    private boolean shouldAdvanceFocusOnEnter()
    {
        if(getKeyListener() == null)
            return false;
        if(mSingleLine)
            return true;
        if(mEditor != null && (mEditor.mInputType & 0xf) == 1)
        {
            int i = mEditor.mInputType & 0xff0;
            if(i == 32 || i == 48)
                return true;
        }
        return false;
    }

    private boolean shouldAdvanceFocusOnTab()
    {
        if(getKeyListener() != null && mSingleLine ^ true && mEditor != null && (mEditor.mInputType & 0xf) == 1)
        {
            int i = mEditor.mInputType & 0xff0;
            if(i == 0x40000 || i == 0x20000)
                return false;
        }
        return true;
    }

    private void startMarquee()
    {
        if(getKeyListener() != null)
            return;
        if(compressText(getWidth() - getCompoundPaddingLeft() - getCompoundPaddingRight()))
            return;
        if((mMarquee == null || mMarquee.isStopped()) && (isFocused() || isSelected()) && getLineCount() == 1 && canMarquee())
        {
            if(mMarqueeFadeMode == 1)
            {
                mMarqueeFadeMode = 2;
                Layout layout = mLayout;
                mLayout = mSavedMarqueeModeLayout;
                mSavedMarqueeModeLayout = layout;
                setHorizontalFadingEdgeEnabled(true);
                requestLayout();
                invalidate();
            }
            if(mMarquee == null)
                mMarquee = new Marquee(this);
            mMarquee.start(mMarqueeRepeatLimit);
        }
    }

    private void startStopMarquee(boolean flag)
    {
        if(mEllipsize == android.text.TextUtils.TruncateAt.MARQUEE)
            if(flag)
                startMarquee();
            else
                stopMarquee();
    }

    private void stopMarquee()
    {
        if(mMarquee != null && mMarquee.isStopped() ^ true)
            mMarquee.stop();
        if(mMarqueeFadeMode == 2)
        {
            mMarqueeFadeMode = 1;
            Layout layout = mSavedMarqueeModeLayout;
            mSavedMarqueeModeLayout = mLayout;
            mLayout = layout;
            setHorizontalFadingEdgeEnabled(false);
            requestLayout();
            invalidate();
        }
    }

    private boolean suggestedSizeFitsInSpace(int i, RectF rectf)
    {
        Object obj;
        int j;
        android.text.StaticLayout.Builder builder;
        if(mTransformed != null)
            obj = mTransformed;
        else
            obj = getText();
        j = getMaxLines();
        if(mTempTextPaint == null)
            mTempTextPaint = new TextPaint();
        else
            mTempTextPaint.reset();
        mTempTextPaint.set(getPaint());
        mTempTextPaint.setTextSize(i);
        builder = android.text.StaticLayout.Builder.obtain(((CharSequence) (obj)), 0, ((CharSequence) (obj)).length(), mTempTextPaint, Math.round(rectf.right));
        obj = builder.setAlignment(getLayoutAlignment()).setLineSpacing(getLineSpacingExtra(), getLineSpacingMultiplier()).setIncludePad(getIncludeFontPadding()).setBreakStrategy(getBreakStrategy()).setHyphenationFrequency(getHyphenationFrequency()).setJustificationMode(getJustificationMode());
        if(mMaxMode == 1)
            i = mMaximum;
        else
            i = 0x7fffffff;
        ((android.text.StaticLayout.Builder) (obj)).setMaxLines(i).setTextDirection(getTextDirectionHeuristic());
        obj = builder.build();
        if(j != -1 && ((StaticLayout) (obj)).getLineCount() > j)
            return false;
        return (float)((StaticLayout) (obj)).getHeight() <= rectf.bottom;
    }

    private void unregisterForPreDraw()
    {
        getViewTreeObserver().removeOnPreDrawListener(this);
        mPreDrawRegistered = false;
        mPreDrawListenerDetached = false;
    }

    private void updateTextColors()
    {
        boolean flag = false;
        int i = mTextColor.getColorForState(getDrawableState(), 0);
        if(i != mCurTextColor)
        {
            mCurTextColor = i;
            flag = true;
        }
        i = ((flag) ? 1 : 0);
        if(mLinkTextColor != null)
        {
            int j = mLinkTextColor.getColorForState(getDrawableState(), 0);
            i = ((flag) ? 1 : 0);
            if(j != mTextPaint.linkColor)
            {
                mTextPaint.linkColor = j;
                i = 1;
            }
        }
        flag = i;
        if(mHintTextColor != null)
        {
            int k = mHintTextColor.getColorForState(getDrawableState(), 0);
            flag = i;
            if(k != mCurHintTextColor)
            {
                mCurHintTextColor = k;
                flag = i;
                if(mText.length() == 0)
                    flag = true;
            }
        }
        if(flag)
        {
            if(mEditor != null)
                mEditor.invalidateTextDisplayList();
            invalidate();
        }
    }

    private void updateTextServicesLocaleAsync()
    {
        AsyncTask.execute(new Runnable() {

            public void run()
            {
                TextView._2D_wrap1(TextView.this);
            }

            final TextView this$0;

            
            {
                this$0 = TextView.this;
                super();
            }
        }
);
    }

    private void updateTextServicesLocaleLocked()
    {
        Object obj = ((TextServicesManager)mContext.getSystemService("textservices")).getCurrentSpellCheckerSubtype(true);
        if(obj != null)
            obj = ((SpellCheckerSubtype) (obj)).getLocaleObject();
        else
            obj = null;
        mCurrentSpellCheckerLocaleCache = ((Locale) (obj));
    }

    private void validateAndSetAutoSizeTextTypeUniformConfiguration(float f, float f1, float f2)
    {
        if(f <= 0.0F)
            throw new IllegalArgumentException((new StringBuilder()).append("Minimum auto-size text size (").append(f).append("px) is less or equal to (0px)").toString());
        if(f1 <= f)
            throw new IllegalArgumentException((new StringBuilder()).append("Maximum auto-size text size (").append(f1).append("px) is less or equal to minimum auto-size ").append("text size (").append(f).append("px)").toString());
        if(f2 <= 0.0F)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("The auto-size step granularity (").append(f2).append("px) is less or equal to (0px)").toString());
        } else
        {
            mAutoSizeTextType = 1;
            mAutoSizeMinTextSizeInPx = f;
            mAutoSizeMaxTextSizeInPx = f1;
            mAutoSizeStepGranularityInPx = f2;
            mHasPresetAutoSizeValues = false;
            return;
        }
    }

    public void addExtraDataToAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo, String s, Bundle bundle)
    {
        if(bundle == null)
            return;
        if(s.equals("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY"))
        {
            int i = bundle.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX", -1);
            int j;
            for(j = bundle.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH", -1); j <= 0 || i < 0 || i >= mText.length();)
            {
                Log.e("TextView", "Invalid arguments for accessibility character locations");
                return;
            }

            bundle = new RectF[j];
            android.view.inputmethod.CursorAnchorInfo.Builder builder = new android.view.inputmethod.CursorAnchorInfo.Builder();
            populateCharacterBounds(builder, i, i + j, viewportToContentHorizontalOffset(), viewportToContentVerticalOffset());
            CursorAnchorInfo cursoranchorinfo = builder.setMatrix(null).build();
            for(int k = 0; k < j; k++)
            {
                if((cursoranchorinfo.getCharacterBoundsFlags(i + k) & 1) != 1)
                    continue;
                RectF rectf = cursoranchorinfo.getCharacterBounds(i + k);
                if(rectf != null)
                {
                    mapRectFromViewToScreenCoords(rectf, true);
                    bundle[k] = rectf;
                }
            }

            accessibilitynodeinfo.getExtras().putParcelableArray(s, bundle);
        }
    }

    public void addTextChangedListener(TextWatcher textwatcher)
    {
        if(mListeners == null)
            mListeners = new ArrayList();
        mListeners.add(textwatcher);
    }

    public final void append(CharSequence charsequence)
    {
        append(charsequence, 0, charsequence.length());
    }

    public void append(CharSequence charsequence, int i, int j)
    {
        if(!(mText instanceof Editable))
            setText(mText, BufferType.EDITABLE);
        ((Editable)mText).append(charsequence, i, j);
        if(mAutoLinkMask != 0 && Linkify.addLinks((Spannable)mText, mAutoLinkMask) && mLinksClickable && textCanBeSelected() ^ true)
            setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void autofill(AutofillValue autofillvalue)
    {
        if(!autofillvalue.isText() || isTextEditable() ^ true)
        {
            Log.w("TextView", (new StringBuilder()).append(autofillvalue).append(" could not be autofilled into ").append(this).toString());
            return;
        }
        setText(autofillvalue.getTextValue(), mBufferType, true, 0);
        autofillvalue = getText();
        if(autofillvalue instanceof Spannable)
            Selection.setSelection((Spannable)autofillvalue, autofillvalue.length());
    }

    public void beginBatchEdit()
    {
        if(mEditor != null)
            mEditor.beginBatchEdit();
    }

    public boolean bringPointIntoView(int i)
    {
        boolean flag;
        Layout layout;
        int j;
        if(isLayoutRequested())
        {
            mDeferScroll = i;
            return false;
        }
        flag = false;
        if(isShowingHint())
            layout = mHintLayout;
        else
            layout = mLayout;
        if(layout == null)
            return false;
        j = layout.getLineForOffset(i);
        _2D_getandroid_2D_text_2D_Layout$AlignmentSwitchesValues()[layout.getParagraphAlignment(j).ordinal()];
        JVM INSTR tableswitch 2 5: default 92
    //                   2 595
    //                   3 607
    //                   4 618
    //                   5 601;
           goto _L1 _L2 _L3 _L4 _L5
_L4:
        break MISSING_BLOCK_LABEL_618;
_L1:
        int k = 0;
_L6:
label0:
        {
            boolean flag1;
            int k1;
            int l1;
            int i2;
            int j2;
            int k2;
            int l2;
            int i3;
            int j3;
            int k3;
            int l3;
            int i4;
            int j4;
            if(k > 0)
                flag1 = true;
            else
                flag1 = false;
            k1 = (int)layout.getPrimaryHorizontal(i, flag1);
            l1 = layout.getLineTop(j);
            i2 = layout.getLineTop(j + 1);
            j2 = (int)Math.floor(layout.getLineLeft(j));
            i = (int)Math.ceil(layout.getLineRight(j));
            k2 = layout.getHeight();
            l2 = mRight - mLeft - getCompoundPaddingLeft() - getCompoundPaddingRight();
            i3 = mBottom - mTop - getExtendedPaddingTop() - getExtendedPaddingBottom();
            j3 = i;
            if(!mHorizontallyScrolling)
            {
                j3 = i;
                if(i - j2 > l2)
                {
                    j3 = i;
                    if(i > k1)
                        j3 = Math.max(k1, j2 + l2);
                }
            }
            i = (i2 - l1) / 2;
            k3 = i;
            if(i > i3 / 4)
                k3 = i3 / 4;
            l3 = i;
            if(i > l2 / 4)
                l3 = l2 / 4;
            i4 = mScrollX;
            j4 = mScrollY;
            i = j4;
            if(l1 - j4 < k3)
                i = l1 - k3;
            j4 = i;
            if(i2 - i > i3 - k3)
                j4 = i2 - (i3 - k3);
            i = j4;
            if(k2 - j4 < i3)
                i = k2 - i3;
            k3 = i;
            if(0 - i > 0)
                k3 = 0;
            i = i4;
            if(k != 0)
            {
                int k4 = i4;
                if(k1 - i4 < l3)
                    k4 = k1 - l3;
                i = k4;
                if(k1 - k4 > l2 - l3)
                    i = k1 - (l2 - l3);
            }
            if(k < 0)
            {
                k = i;
                if(j2 - i > 0)
                    k = j2;
                i = k;
                if(j3 - k < l2)
                    i = j3 - l2;
            } else
            if(k > 0)
            {
                int l = i;
                if(j3 - i < l2)
                    l = j3 - l2;
                i = l;
                if(j2 - l > 0)
                    i = j2;
            } else
            if(j3 - j2 <= l2)
                i = j2 - (l2 - (j3 - j2)) / 2;
            else
            if(k1 > j3 - l3)
                i = j3 - l2;
            else
            if(k1 < j2 + l3)
                i = j2;
            else
            if(j2 > i)
                i = j2;
            else
            if(j3 < i + l2)
            {
                i = j3 - l2;
            } else
            {
                int i1 = i;
                if(k1 - i < l3)
                    i1 = k1 - l3;
                i = i1;
                if(k1 - i1 > l2 - l3)
                    i = k1 - (l2 - l3);
            }
            if(i == mScrollX)
            {
                flag1 = flag;
                if(k3 == mScrollY)
                    break label0;
            }
            if(mScroller == null)
            {
                scrollTo(i, k3);
            } else
            {
                long l4 = AnimationUtils.currentAnimationTimeMillis();
                long l5 = mLastScroll;
                i -= mScrollX;
                int j1 = k3 - mScrollY;
                if(l4 - l5 > 250L)
                {
                    mScroller.startScroll(mScrollX, mScrollY, i, j1);
                    awakenScrollBars(mScroller.getDuration());
                    invalidate();
                } else
                {
                    if(!mScroller.isFinished())
                        mScroller.abortAnimation();
                    scrollBy(i, j1);
                }
                mLastScroll = AnimationUtils.currentAnimationTimeMillis();
            }
            flag1 = true;
        }
        flag = flag1;
        if(isFocused())
        {
            if(mTempRect == null)
                mTempRect = new Rect();
            mTempRect.set(k1 - 2, l1, k1 + 2, i2);
            getInterestingRect(mTempRect, j);
            mTempRect.offset(mScrollX, mScrollY);
            flag = flag1;
            if(requestRectangleOnScreen(mTempRect))
                flag = true;
        }
        return flag;
_L2:
        k = 1;
          goto _L6
_L5:
        k = -1;
          goto _L6
_L3:
        k = layout.getParagraphDirection(j);
          goto _L6
        k = -layout.getParagraphDirection(j);
          goto _L6
    }

    boolean canCopy()
    {
        if(hasPasswordTransformationMethod())
            return false;
        return mText.length() > 0 && hasSelection() && mEditor != null;
    }

    boolean canCut()
    {
        if(hasPasswordTransformationMethod())
            return false;
        return mText.length() > 0 && hasSelection() && (mText instanceof Editable) && mEditor != null && mEditor.mKeyListener != null;
    }

    boolean canPaste()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mText instanceof Editable)
        {
            flag1 = flag;
            if(mEditor != null)
            {
                flag1 = flag;
                if(mEditor.mKeyListener != null)
                {
                    flag1 = flag;
                    if(getSelectionStart() >= 0)
                    {
                        flag1 = flag;
                        if(getSelectionEnd() >= 0)
                            flag1 = ((ClipboardManager)getContext().getSystemService("clipboard")).hasPrimaryClip();
                    }
                }
            }
        }
        return flag1;
    }

    boolean canPasteAsPlainText()
    {
        if(!canPaste())
            return false;
        Object obj = ((ClipboardManager)getContext().getSystemService("clipboard")).getPrimaryClip();
        ClipDescription clipdescription = ((ClipData) (obj)).getDescription();
        boolean flag = clipdescription.hasMimeType("text/plain");
        obj = ((ClipData) (obj)).getItemAt(0).getText();
        if(flag && (obj instanceof Spanned) && TextUtils.hasStyleSpan((Spanned)obj))
            return true;
        else
            return clipdescription.hasMimeType("text/html");
    }

    boolean canProcessText()
    {
        if(getId() == -1)
            return false;
        else
            return canShare();
    }

    boolean canRedo()
    {
        boolean flag;
        if(mEditor != null)
            flag = mEditor.canRedo();
        else
            flag = false;
        return flag;
    }

    boolean canRequestAutofill()
    {
        if(!isAutofillable())
            return false;
        AutofillManager autofillmanager = (AutofillManager)mContext.getSystemService(android/view/autofill/AutofillManager);
        if(autofillmanager != null)
            return autofillmanager.isEnabled();
        else
            return false;
    }

    boolean canSelectAllText()
    {
        boolean flag = true;
        boolean flag1;
        if(canSelectText() && hasPasswordTransformationMethod() ^ true)
        {
            flag1 = flag;
            if(getSelectionStart() == 0)
                if(getSelectionEnd() != mText.length())
                    flag1 = flag;
                else
                    flag1 = false;
        } else
        {
            flag1 = false;
        }
        return flag1;
    }

    boolean canSelectText()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mText.length() != 0)
        {
            flag1 = flag;
            if(mEditor != null)
                flag1 = mEditor.hasSelectionController();
        }
        return flag1;
    }

    boolean canShare()
    {
        if(!getContext().canStartActivityForResult() || isDeviceProvisioned() ^ true)
            return false;
        else
            return canCopy();
    }

    boolean canUndo()
    {
        boolean flag;
        if(mEditor != null)
            flag = mEditor.canUndo();
        else
            flag = false;
        return flag;
    }

    public void cancelLongPress()
    {
        super.cancelLongPress();
        if(mEditor != null)
            mEditor.mIgnoreActionUpEvent = true;
    }

    public void clearComposingText()
    {
        if(mText instanceof Spannable)
            BaseInputConnection.removeComposingSpans((Spannable)mText);
    }

    protected int computeHorizontalScrollRange()
    {
        if(mLayout != null)
        {
            int i;
            if(mSingleLine && (mGravity & 7) == 3)
                i = (int)mLayout.getLineWidth(0);
            else
                i = mLayout.getWidth();
            return i;
        } else
        {
            return super.computeHorizontalScrollRange();
        }
    }

    public void computeScroll()
    {
        if(mScroller != null && mScroller.computeScrollOffset())
        {
            mScrollX = mScroller.getCurrX();
            mScrollY = mScroller.getCurrY();
            invalidateParentCaches();
            postInvalidate();
        }
    }

    protected int computeVerticalScrollExtent()
    {
        return getHeight() - getCompoundPaddingTop() - getCompoundPaddingBottom();
    }

    protected int computeVerticalScrollRange()
    {
        if(mLayout != null)
            return mLayout.getHeight();
        else
            return super.computeVerticalScrollRange();
    }

    float convertToLocalHorizontalCoordinate(float f)
    {
        f = Math.max(0.0F, f - (float)getTotalPaddingLeft());
        return Math.min(getWidth() - getTotalPaddingRight() - 1, f) + (float)getScrollX();
    }

    public void debug(int i)
    {
        super.debug(i);
        String s = debugIndent(i);
        s = (new StringBuilder()).append(s).append("frame={").append(mLeft).append(", ").append(mTop).append(", ").append(mRight).append(", ").append(mBottom).append("} scroll={").append(mScrollX).append(", ").append(mScrollY).append("} ").toString();
        if(mText != null)
        {
            String s1 = (new StringBuilder()).append(s).append("mText=\"").append(mText).append("\" ").toString();
            s = s1;
            if(mLayout != null)
                s = (new StringBuilder()).append(s1).append("mLayout width=").append(mLayout.getWidth()).append(" height=").append(mLayout.getHeight()).toString();
        } else
        {
            s = (new StringBuilder()).append(s).append("mText=NULL").toString();
        }
        Log.d("View", s);
    }

    protected void deleteText_internal(int i, int j)
    {
        ((Editable)mText).delete(i, j);
    }

    public boolean didTouchFocusSelect()
    {
        boolean flag;
        if(mEditor != null)
            flag = mEditor.mTouchFocusSelected;
        else
            flag = false;
        return flag;
    }

    public void drawableHotspotChanged(float f, float f1)
    {
        super.drawableHotspotChanged(f, f1);
        if(mDrawables != null)
        {
            Drawable adrawable[] = mDrawables.mShowing;
            int i = 0;
            for(int j = adrawable.length; i < j; i++)
            {
                Drawable drawable = adrawable[i];
                if(drawable != null)
                    drawable.setHotspot(f, f1);
            }

        }
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        if(mTextColor != null && mTextColor.isStateful() || mHintTextColor != null && mHintTextColor.isStateful() || mLinkTextColor != null && mLinkTextColor.isStateful())
            updateTextColors();
        if(mDrawables != null)
        {
            int ai[] = getDrawableState();
            Drawable adrawable[] = mDrawables.mShowing;
            int i = 0;
            for(int j = adrawable.length; i < j; i++)
            {
                Drawable drawable = adrawable[i];
                if(drawable != null && drawable.isStateful() && drawable.setState(ai))
                    invalidateDrawable(drawable);
            }

        }
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        Object obj = null;
        super.encodeProperties(viewhierarchyencoder);
        Object obj1 = getEllipsize();
        if(obj1 == null)
            obj1 = null;
        else
            obj1 = ((android.text.TextUtils.TruncateAt) (obj1)).name();
        viewhierarchyencoder.addProperty("text:ellipsize", ((String) (obj1)));
        viewhierarchyencoder.addProperty("text:textSize", getTextSize());
        viewhierarchyencoder.addProperty("text:scaledTextSize", getScaledTextSize());
        viewhierarchyencoder.addProperty("text:typefaceStyle", getTypefaceStyle());
        viewhierarchyencoder.addProperty("text:selectionStart", getSelectionStart());
        viewhierarchyencoder.addProperty("text:selectionEnd", getSelectionEnd());
        viewhierarchyencoder.addProperty("text:curTextColor", mCurTextColor);
        if(mText == null)
            obj1 = obj;
        else
            obj1 = mText.toString();
        viewhierarchyencoder.addProperty("text:text", ((String) (obj1)));
        viewhierarchyencoder.addProperty("text:gravity", mGravity);
    }

    public void endBatchEdit()
    {
        if(mEditor != null)
            mEditor.endBatchEdit();
    }

    public boolean extractText(ExtractedTextRequest extractedtextrequest, ExtractedText extractedtext)
    {
        createEditorIfNeeded();
        return mEditor.extractText(extractedtextrequest, extractedtext);
    }

    public void findViewsWithText(ArrayList arraylist, CharSequence charsequence, int i)
    {
        super.findViewsWithText(arraylist, charsequence, i);
        if(!arraylist.contains(this) && (i & 1) != 0 && TextUtils.isEmpty(charsequence) ^ true && TextUtils.isEmpty(mText) ^ true)
        {
            charsequence = charsequence.toString().toLowerCase();
            if(mText.toString().toLowerCase().contains(charsequence))
                arraylist.add(this);
        }
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/TextView.getName();
    }

    public int getAccessibilitySelectionEnd()
    {
        return getSelectionEnd();
    }

    public int getAccessibilitySelectionStart()
    {
        return getSelectionStart();
    }

    public final int getAutoLinkMask()
    {
        return mAutoLinkMask;
    }

    public int getAutoSizeMaxTextSize()
    {
        return Math.round(mAutoSizeMaxTextSizeInPx);
    }

    public int getAutoSizeMinTextSize()
    {
        return Math.round(mAutoSizeMinTextSizeInPx);
    }

    public int getAutoSizeStepGranularity()
    {
        return Math.round(mAutoSizeStepGranularityInPx);
    }

    public int[] getAutoSizeTextAvailableSizes()
    {
        return mAutoSizeTextSizesInPx;
    }

    public int getAutoSizeTextType()
    {
        return mAutoSizeTextType;
    }

    public int getAutofillType()
    {
        int i;
        if(isTextEditable())
            i = 1;
        else
            i = 0;
        return i;
    }

    public AutofillValue getAutofillValue()
    {
        AutofillValue autofillvalue;
        if(isTextEditable())
            autofillvalue = AutofillValue.forText(getText());
        else
            autofillvalue = null;
        return autofillvalue;
    }

    public int getBaseline()
    {
        if(mLayout == null)
            return super.getBaseline();
        else
            return getBaselineOffset() + mLayout.getLineBaseline(0);
    }

    int getBaselineOffset()
    {
        int i = 0;
        if((mGravity & 0x70) != 48)
            i = getVerticalOffset(true);
        int j = i;
        if(isLayoutModeOptical(mParent))
            j = i - getOpticalInsets().top;
        return getExtendedPaddingTop() + j;
    }

    protected int getBottomPaddingOffset()
    {
        return (int)Math.max(0.0F, mShadowDy + mShadowRadius);
    }

    public int getBreakStrategy()
    {
        return mBreakStrategy;
    }

    public int getCompoundDrawablePadding()
    {
        Drawables drawables = mDrawables;
        int i;
        if(drawables != null)
            i = drawables.mDrawablePadding;
        else
            i = 0;
        return i;
    }

    public ColorStateList getCompoundDrawableTintList()
    {
        ColorStateList colorstatelist = null;
        if(mDrawables != null)
            colorstatelist = mDrawables.mTintList;
        return colorstatelist;
    }

    public android.graphics.PorterDuff.Mode getCompoundDrawableTintMode()
    {
        android.graphics.PorterDuff.Mode mode = null;
        if(mDrawables != null)
            mode = mDrawables.mTintMode;
        return mode;
    }

    public Drawable[] getCompoundDrawables()
    {
        Drawables drawables = mDrawables;
        if(drawables != null)
            return (Drawable[])drawables.mShowing.clone();
        else
            return (new Drawable[] {
                null, null, null, null
            });
    }

    public Drawable[] getCompoundDrawablesRelative()
    {
        Drawables drawables = mDrawables;
        if(drawables != null)
            return (new Drawable[] {
                drawables.mDrawableStart, drawables.mShowing[1], drawables.mDrawableEnd, drawables.mShowing[3]
            });
        else
            return (new Drawable[] {
                null, null, null, null
            });
    }

    public int getCompoundPaddingBottom()
    {
        Drawables drawables = mDrawables;
        if(drawables == null || drawables.mShowing[3] == null)
            return mPaddingBottom;
        else
            return mPaddingBottom + drawables.mDrawablePadding + drawables.mDrawableSizeBottom;
    }

    public int getCompoundPaddingEnd()
    {
        resolveDrawables();
        switch(getLayoutDirection())
        {
        case 0: // '\0'
        default:
            return getCompoundPaddingRight();

        case 1: // '\001'
            return getCompoundPaddingLeft();
        }
    }

    public int getCompoundPaddingLeft()
    {
        Drawables drawables = mDrawables;
        if(drawables == null || drawables.mShowing[0] == null)
            return mPaddingLeft;
        else
            return mPaddingLeft + drawables.mDrawablePadding + drawables.mDrawableSizeLeft;
    }

    public int getCompoundPaddingRight()
    {
        Drawables drawables = mDrawables;
        if(drawables == null || drawables.mShowing[2] == null)
            return mPaddingRight;
        else
            return mPaddingRight + drawables.mDrawablePadding + drawables.mDrawableSizeRight;
    }

    public int getCompoundPaddingStart()
    {
        resolveDrawables();
        switch(getLayoutDirection())
        {
        case 0: // '\0'
        default:
            return getCompoundPaddingLeft();

        case 1: // '\001'
            return getCompoundPaddingRight();
        }
    }

    public int getCompoundPaddingTop()
    {
        Drawables drawables = mDrawables;
        if(drawables == null || drawables.mShowing[1] == null)
            return mPaddingTop;
        else
            return mPaddingTop + drawables.mDrawablePadding + drawables.mDrawableSizeTop;
    }

    public final int getCurrentHintTextColor()
    {
        int i;
        if(mHintTextColor != null)
            i = mCurHintTextColor;
        else
            i = mCurTextColor;
        return i;
    }

    public final int getCurrentTextColor()
    {
        return mCurTextColor;
    }

    public android.view.ActionMode.Callback getCustomInsertionActionModeCallback()
    {
        android.view.ActionMode.Callback callback = null;
        if(mEditor != null)
            callback = mEditor.mCustomInsertionActionModeCallback;
        return callback;
    }

    public android.view.ActionMode.Callback getCustomSelectionActionModeCallback()
    {
        android.view.ActionMode.Callback callback = null;
        if(mEditor != null)
            callback = mEditor.mCustomSelectionActionModeCallback;
        return callback;
    }

    protected boolean getDefaultEditable()
    {
        return false;
    }

    protected MovementMethod getDefaultMovementMethod()
    {
        return null;
    }

    public Editable getEditableText()
    {
        Editable editable;
        if(mText instanceof Editable)
            editable = (Editable)mText;
        else
            editable = null;
        return editable;
    }

    public final Editor getEditorForTesting()
    {
        return mEditor;
    }

    public android.text.TextUtils.TruncateAt getEllipsize()
    {
        return mEllipsize;
    }

    public CharSequence getError()
    {
        CharSequence charsequence = null;
        if(mEditor != null)
            charsequence = mEditor.mError;
        return charsequence;
    }

    public int getExtendedPaddingBottom()
    {
        if(mMaxMode != 1)
            return getCompoundPaddingBottom();
        if(mLayout == null)
            assumeLayout();
        if(mLayout.getLineCount() <= mMaximum)
            return getCompoundPaddingBottom();
        int i = getCompoundPaddingTop();
        int j = getCompoundPaddingBottom();
        i = getHeight() - i - j;
        int k = mLayout.getLineTop(mMaximum);
        if(k >= i)
            return j;
        int l = mGravity & 0x70;
        if(l == 48)
            return (j + i) - k;
        if(l == 80)
            return j;
        else
            return (i - k) / 2 + j;
    }

    public int getExtendedPaddingTop()
    {
        if(mMaxMode != 1)
            return getCompoundPaddingTop();
        if(mLayout == null)
            assumeLayout();
        if(mLayout.getLineCount() <= mMaximum)
            return getCompoundPaddingTop();
        int i = getCompoundPaddingTop();
        int j = getCompoundPaddingBottom();
        int k = getHeight() - i - j;
        j = mLayout.getLineTop(mMaximum);
        if(j >= k)
            return i;
        int l = mGravity & 0x70;
        if(l == 48)
            return i;
        if(l == 80)
            return (i + k) - j;
        else
            return (k - j) / 2 + i;
    }

    protected int getFadeHeight(boolean flag)
    {
        int i;
        if(mLayout != null)
            i = mLayout.getHeight();
        else
            i = 0;
        return i;
    }

    protected int getFadeTop(boolean flag)
    {
        if(mLayout == null)
            return 0;
        int i = 0;
        if((mGravity & 0x70) != 48)
            i = getVerticalOffset(true);
        int j = i;
        if(flag)
            j = i + getTopPaddingOffset();
        return getExtendedPaddingTop() + j;
    }

    public InputFilter[] getFilters()
    {
        return mFilters;
    }

    public void getFocusedRect(Rect rect)
    {
        int i;
        int j;
        if(mLayout == null)
        {
            super.getFocusedRect(rect);
            return;
        }
        i = getSelectionEnd();
        if(i < 0)
        {
            super.getFocusedRect(rect);
            return;
        }
        j = getSelectionStart();
        if(j >= 0 && j < i) goto _L2; else goto _L1
_L1:
        int k = mLayout.getLineForOffset(i);
        rect.top = mLayout.getLineTop(k);
        rect.bottom = mLayout.getLineBottom(k);
        rect.left = (int)mLayout.getPrimaryHorizontal(i) - 2;
        rect.right = rect.left + 4;
_L4:
        j = getCompoundPaddingLeft();
        int l = getExtendedPaddingTop();
        i = l;
        if((mGravity & 0x70) != 48)
            i = l + getVerticalOffset(false);
        rect.offset(j, i);
        i = getExtendedPaddingBottom();
        rect.bottom = rect.bottom + i;
        return;
_L2:
        int i1 = mLayout.getLineForOffset(j);
        int j1 = mLayout.getLineForOffset(i);
        rect.top = mLayout.getLineTop(i1);
        rect.bottom = mLayout.getLineBottom(j1);
        if(i1 == j1)
        {
            rect.left = (int)mLayout.getPrimaryHorizontal(j);
            rect.right = (int)mLayout.getPrimaryHorizontal(i);
            continue; /* Loop/switch isn't completed */
        }
        if(mHighlightPathBogus)
        {
            if(mHighlightPath == null)
                mHighlightPath = new Path();
            mHighlightPath.reset();
            mLayout.getSelectionPath(j, i, mHighlightPath);
            mHighlightPathBogus = false;
        }
        RectF rectf = TEMP_RECTF;
        rectf;
        JVM INSTR monitorenter ;
        mHighlightPath.computeBounds(TEMP_RECTF, true);
        rect.left = (int)TEMP_RECTF.left - 1;
        rect.right = (int)TEMP_RECTF.right + 1;
        rectf;
        JVM INSTR monitorexit ;
        if(true) goto _L4; else goto _L3
_L3:
        rect;
        throw rect;
    }

    public String getFontFeatureSettings()
    {
        return mTextPaint.getFontFeatureSettings();
    }

    public String getFontVariationSettings()
    {
        return mTextPaint.getFontVariationSettings();
    }

    public boolean getFreezesText()
    {
        return mFreezesText;
    }

    public int getGravity()
    {
        return mGravity;
    }

    public int getHighlightColor()
    {
        return mHighlightColor;
    }

    public CharSequence getHint()
    {
        return mHint;
    }

    final Layout getHintLayout()
    {
        return mHintLayout;
    }

    public final ColorStateList getHintTextColors()
    {
        return mHintTextColor;
    }

    public int getHorizontalOffsetForDrawables()
    {
        return 0;
    }

    public boolean getHorizontallyScrolling()
    {
        return mHorizontallyScrolling;
    }

    public int getHyphenationFrequency()
    {
        return mHyphenationFrequency;
    }

    public int getImeActionId()
    {
        int i;
        if(mEditor != null && mEditor.mInputContentType != null)
            i = mEditor.mInputContentType.imeActionId;
        else
            i = 0;
        return i;
    }

    public CharSequence getImeActionLabel()
    {
        Object obj = null;
        CharSequence charsequence = obj;
        if(mEditor != null)
        {
            charsequence = obj;
            if(mEditor.mInputContentType != null)
                charsequence = mEditor.mInputContentType.imeActionLabel;
        }
        return charsequence;
    }

    public LocaleList getImeHintLocales()
    {
        if(mEditor == null)
            return null;
        if(mEditor.mInputContentType == null)
            return null;
        else
            return mEditor.mInputContentType.imeHintLocales;
    }

    public int getImeOptions()
    {
        int i;
        if(mEditor != null && mEditor.mInputContentType != null)
            i = mEditor.mInputContentType.imeOptions;
        else
            i = 0;
        return i;
    }

    public boolean getIncludeFontPadding()
    {
        return mIncludePad;
    }

    public Bundle getInputExtras(boolean flag)
    {
        if(mEditor == null && flag ^ true)
            return null;
        createEditorIfNeeded();
        if(mEditor.mInputContentType == null)
        {
            if(!flag)
                return null;
            mEditor.createInputContentTypeIfNeeded();
        }
        if(mEditor.mInputContentType.extras == null)
        {
            if(!flag)
                return null;
            mEditor.mInputContentType.extras = new Bundle();
        }
        return mEditor.mInputContentType.extras;
    }

    public int getInputType()
    {
        int i;
        if(mEditor == null)
            i = 0;
        else
            i = mEditor.mInputType;
        return i;
    }

    public CharSequence getIterableTextForAccessibility()
    {
        return mText;
    }

    public android.view.AccessibilityIterators.TextSegmentIterator getIteratorForGranularity(int i)
    {
        i;
        JVM INSTR lookupswitch 2: default 28
    //                   4: 34
    //                   16: 71;
           goto _L1 _L2 _L3
_L1:
        Spannable spannable;
        return super.getIteratorForGranularity(i);
_L2:
        if(!TextUtils.isEmpty(spannable = (Spannable)getIterableTextForAccessibility()) && getLayout() != null)
        {
            AccessibilityIterators.LineTextSegmentIterator linetextsegmentiterator = AccessibilityIterators.LineTextSegmentIterator.getInstance();
            linetextsegmentiterator.initialize(spannable, getLayout());
            return linetextsegmentiterator;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(!TextUtils.isEmpty((Spannable)getIterableTextForAccessibility()) && getLayout() != null)
        {
            AccessibilityIterators.PageTextSegmentIterator pagetextsegmentiterator = AccessibilityIterators.PageTextSegmentIterator.getInstance();
            pagetextsegmentiterator.initialize(this);
            return pagetextsegmentiterator;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    public int getJustificationMode()
    {
        return mJustificationMode;
    }

    public final KeyListener getKeyListener()
    {
        KeyListener keylistener = null;
        if(mEditor != null)
            keylistener = mEditor.mKeyListener;
        return keylistener;
    }

    public final Layout getLayout()
    {
        return mLayout;
    }

    protected float getLeftFadingEdgeStrength()
    {
        if(isMarqueeFadeEnabled() && mMarquee != null && mMarquee.isStopped() ^ true)
        {
            Marquee marquee = mMarquee;
            if(marquee.shouldDrawLeftFade())
                return getHorizontalFadingEdgeStrength(marquee.getScroll(), 0.0F);
            else
                return 0.0F;
        }
        if(getLineCount() == 1)
        {
            float f = getLayout().getLineLeft(0);
            if(f > (float)mScrollX)
                return 0.0F;
            else
                return getHorizontalFadingEdgeStrength(mScrollX, f);
        } else
        {
            return super.getLeftFadingEdgeStrength();
        }
    }

    protected int getLeftPaddingOffset()
    {
        return (getCompoundPaddingLeft() - mPaddingLeft) + (int)Math.min(0.0F, mShadowDx - mShadowRadius);
    }

    public float getLetterSpacing()
    {
        return mTextPaint.getLetterSpacing();
    }

    int getLineAtCoordinate(float f)
    {
        f = Math.max(0.0F, f - (float)getTotalPaddingTop());
        f = Math.min(getHeight() - getTotalPaddingBottom() - 1, f);
        float f1 = getScrollY();
        return getLayout().getLineForVertical((int)(f + f1));
    }

    int getLineAtCoordinateUnclamped(float f)
    {
        float f1 = getTotalPaddingTop();
        float f2 = getScrollY();
        return getLayout().getLineForVertical((int)((f - f1) + f2));
    }

    public int getLineBounds(int i, Rect rect)
    {
        if(mLayout == null)
        {
            if(rect != null)
                rect.set(0, 0, 0, 0);
            return 0;
        }
        int j = mLayout.getLineBounds(i, rect);
        int k = getExtendedPaddingTop();
        i = k;
        if((mGravity & 0x70) != 48)
            i = k + getVerticalOffset(true);
        if(rect != null)
            rect.offset(getCompoundPaddingLeft(), i);
        return j + i;
    }

    public int getLineCount()
    {
        int i;
        if(mLayout != null)
            i = mLayout.getLineCount();
        else
            i = 0;
        return i;
    }

    public int getLineHeight()
    {
        return FastMath.round((float)mTextPaint.getFontMetricsInt(null) * mSpacingMult + mSpacingAdd);
    }

    public float getLineSpacingExtra()
    {
        return mSpacingAdd;
    }

    public float getLineSpacingMultiplier()
    {
        return mSpacingMult;
    }

    public final ColorStateList getLinkTextColors()
    {
        return mLinkTextColor;
    }

    public final boolean getLinksClickable()
    {
        return mLinksClickable;
    }

    public int getMarqueeRepeatLimit()
    {
        return mMarqueeRepeatLimit;
    }

    public int getMaxEms()
    {
        int i;
        if(mMaxWidthMode == 1)
            i = mMaxWidth;
        else
            i = -1;
        return i;
    }

    public int getMaxHeight()
    {
        int i;
        if(mMaxMode == 2)
            i = mMaximum;
        else
            i = -1;
        return i;
    }

    public int getMaxLines()
    {
        int i;
        if(mMaxMode == 1)
            i = mMaximum;
        else
            i = -1;
        return i;
    }

    public int getMaxWidth()
    {
        int i;
        if(mMaxWidthMode == 2)
            i = mMaxWidth;
        else
            i = -1;
        return i;
    }

    public int getMinEms()
    {
        int i;
        if(mMinWidthMode == 1)
            i = mMinWidth;
        else
            i = -1;
        return i;
    }

    public int getMinHeight()
    {
        int i;
        if(mMinMode == 2)
            i = mMinimum;
        else
            i = -1;
        return i;
    }

    public int getMinLines()
    {
        int i;
        if(mMinMode == 1)
            i = mMinimum;
        else
            i = -1;
        return i;
    }

    public int getMinWidth()
    {
        int i;
        if(mMinWidthMode == 2)
            i = mMinWidth;
        else
            i = -1;
        return i;
    }

    public final MovementMethod getMovementMethod()
    {
        return mMovement;
    }

    int getOffsetAtCoordinate(int i, float f)
    {
        f = convertToLocalHorizontalCoordinate(f);
        return getLayout().getOffsetForHorizontal(i, f);
    }

    public int getOffsetForPosition(float f, float f1)
    {
        if(getLayout() == null)
            return -1;
        else
            return getOffsetAtCoordinate(getLineAtCoordinate(f1), f);
    }

    public TextPaint getPaint()
    {
        return mTextPaint;
    }

    public int getPaintFlags()
    {
        return mTextPaint.getFlags();
    }

    public String getPrivateImeOptions()
    {
        Object obj = null;
        String s = obj;
        if(mEditor != null)
        {
            s = obj;
            if(mEditor.mInputContentType != null)
                s = mEditor.mInputContentType.privateImeOptions;
        }
        return s;
    }

    protected float getRightFadingEdgeStrength()
    {
        if(isMarqueeFadeEnabled() && mMarquee != null && mMarquee.isStopped() ^ true)
        {
            Marquee marquee = mMarquee;
            return getHorizontalFadingEdgeStrength(marquee.getMaxFadeScroll(), marquee.getScroll());
        }
        if(getLineCount() == 1)
        {
            float f = mScrollX + (getWidth() - getCompoundPaddingLeft() - getCompoundPaddingRight());
            float f1 = getLayout().getLineRight(0);
            if(f1 < f)
                return 0.0F;
            else
                return getHorizontalFadingEdgeStrength(f, f1);
        } else
        {
            return super.getRightFadingEdgeStrength();
        }
    }

    protected int getRightPaddingOffset()
    {
        return -(getCompoundPaddingRight() - mPaddingRight) + (int)Math.max(0.0F, mShadowDx + mShadowRadius);
    }

    public float getScaledTextSize()
    {
        return mTextPaint.getTextSize() / mTextPaint.density;
    }

    String getSelectedText()
    {
        if(!hasSelection())
            return null;
        int i = getSelectionStart();
        int j = getSelectionEnd();
        CharSequence charsequence;
        if(i > j)
            charsequence = mText.subSequence(j, i);
        else
            charsequence = mText.subSequence(i, j);
        return String.valueOf(charsequence);
    }

    public int getSelectionEnd()
    {
        return Selection.getSelectionEnd(getText());
    }

    public int getSelectionStart()
    {
        return Selection.getSelectionStart(getText());
    }

    public int getShadowColor()
    {
        return mShadowColor;
    }

    public float getShadowDx()
    {
        return mShadowDx;
    }

    public float getShadowDy()
    {
        return mShadowDy;
    }

    public float getShadowRadius()
    {
        return mShadowRadius;
    }

    public final boolean getShowSoftInputOnFocus()
    {
        boolean flag;
        if(mEditor != null)
            flag = mEditor.mShowSoftInputOnFocus;
        else
            flag = true;
        return flag;
    }

    public Locale getSpellCheckerLocale()
    {
        return getTextServicesLocale(true);
    }

    public CharSequence getText()
    {
        return mText;
    }

    public TextClassifier getTextClassifier()
    {
        if(mTextClassifier == null)
        {
            TextClassificationManager textclassificationmanager = (TextClassificationManager)mContext.getSystemService(android/view/textclassifier/TextClassificationManager);
            if(textclassificationmanager != null)
                mTextClassifier = textclassificationmanager.getTextClassifier();
            else
                mTextClassifier = TextClassifier.NO_OP;
        }
        return mTextClassifier;
    }

    public final ColorStateList getTextColors()
    {
        return mTextColor;
    }

    protected TextDirectionHeuristic getTextDirectionHeuristic()
    {
        if(hasPasswordTransformationMethod())
            return TextDirectionHeuristics.LTR;
        if(mEditor != null && (mEditor.mInputType & 0xf) == 3)
        {
            byte byte0 = Character.getDirectionality(DecimalFormatSymbols.getInstance(getTextLocale()).getDigitStrings()[0].codePointAt(0));
            if(byte0 == 1 || byte0 == 2)
                return TextDirectionHeuristics.RTL;
            else
                return TextDirectionHeuristics.LTR;
        }
        boolean flag;
        if(getLayoutDirection() == 1)
            flag = true;
        else
            flag = false;
        switch(getTextDirection())
        {
        case 1: // '\001'
        default:
            TextDirectionHeuristic textdirectionheuristic;
            if(flag)
                textdirectionheuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
            else
                textdirectionheuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
            return textdirectionheuristic;

        case 2: // '\002'
            return TextDirectionHeuristics.ANYRTL_LTR;

        case 3: // '\003'
            return TextDirectionHeuristics.LTR;

        case 4: // '\004'
            return TextDirectionHeuristics.RTL;

        case 5: // '\005'
            return TextDirectionHeuristics.LOCALE;

        case 6: // '\006'
            return TextDirectionHeuristics.FIRSTSTRONG_LTR;

        case 7: // '\007'
            return TextDirectionHeuristics.FIRSTSTRONG_RTL;
        }
    }

    public Locale getTextLocale()
    {
        return mTextPaint.getTextLocale();
    }

    public LocaleList getTextLocales()
    {
        return mTextPaint.getTextLocales();
    }

    public float getTextScaleX()
    {
        return mTextPaint.getTextScaleX();
    }

    public Locale getTextServicesLocale()
    {
        return getTextServicesLocale(false);
    }

    public float getTextSize()
    {
        return mTextPaint.getTextSize();
    }

    protected int getTopPaddingOffset()
    {
        return (int)Math.min(0.0F, mShadowDy - mShadowRadius);
    }

    public int getTotalPaddingBottom()
    {
        return getExtendedPaddingBottom() + getBottomVerticalOffset(true);
    }

    public int getTotalPaddingEnd()
    {
        return getCompoundPaddingEnd();
    }

    public int getTotalPaddingLeft()
    {
        return getCompoundPaddingLeft();
    }

    public int getTotalPaddingRight()
    {
        return getCompoundPaddingRight();
    }

    public int getTotalPaddingStart()
    {
        return getCompoundPaddingStart();
    }

    public int getTotalPaddingTop()
    {
        return getExtendedPaddingTop() + getVerticalOffset(true);
    }

    public final TransformationMethod getTransformationMethod()
    {
        return mTransformation;
    }

    CharSequence getTransformedText(int i, int j)
    {
        return removeSuggestionSpans(mTransformed.subSequence(i, j));
    }

    public Typeface getTypeface()
    {
        return mTextPaint.getTypeface();
    }

    public int getTypefaceStyle()
    {
        Typeface typeface = mTextPaint.getTypeface();
        int i;
        if(typeface != null)
            i = typeface.getStyle();
        else
            i = 0;
        return i;
    }

    public final UndoManager getUndoManager()
    {
        throw new UnsupportedOperationException("not implemented");
    }

    public URLSpan[] getUrls()
    {
        if(mText instanceof Spanned)
            return (URLSpan[])((Spanned)mText).getSpans(0, mText.length(), android/text/style/URLSpan);
        else
            return new URLSpan[0];
    }

    int getVerticalOffset(boolean flag)
    {
        boolean flag1 = false;
        int i = mGravity & 0x70;
        Layout layout = mLayout;
        Layout layout1 = layout;
        if(!flag)
        {
            layout1 = layout;
            if(mText.length() == 0)
            {
                layout1 = layout;
                if(mHintLayout != null)
                    layout1 = mHintLayout;
            }
        }
        int j = ((flag1) ? 1 : 0);
        if(i != 48)
        {
            int k = getBoxHeight(layout1);
            int l = layout1.getHeight();
            j = ((flag1) ? 1 : 0);
            if(l < k)
                if(i == 80)
                    j = k - l;
                else
                    j = k - l >> 1;
        }
        return j;
    }

    public WordIterator getWordIterator()
    {
        if(mEditor != null)
            return mEditor.getWordIterator();
        else
            return null;
    }

    public boolean handleBackInTextActionModeIfNeeded(KeyEvent keyevent)
    {
        if(mEditor == null || mEditor.getTextActionMode() == null)
            return false;
        if(keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0)
        {
            android.view.KeyEvent.DispatcherState dispatcherstate = getKeyDispatcherState();
            if(dispatcherstate != null)
                dispatcherstate.startTracking(keyevent, this);
            return true;
        }
        if(keyevent.getAction() == 1)
        {
            android.view.KeyEvent.DispatcherState dispatcherstate1 = getKeyDispatcherState();
            if(dispatcherstate1 != null)
                dispatcherstate1.handleUpEvent(keyevent);
            if(keyevent.isTracking() && keyevent.isCanceled() ^ true)
            {
                stopTextActionMode();
                return true;
            }
        }
        return false;
    }

    void handleTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        sLastCutCopyOrTextChangedTime = 0L;
        Editor.InputMethodState inputmethodstate;
        if(mEditor == null)
            inputmethodstate = null;
        else
            inputmethodstate = mEditor.mInputMethodState;
        if(inputmethodstate == null || inputmethodstate.mBatchEditNesting == 0)
            updateAfterEdit();
        if(inputmethodstate != null)
        {
            inputmethodstate.mContentChanged = true;
            if(inputmethodstate.mChangedStart < 0)
            {
                inputmethodstate.mChangedStart = i;
                inputmethodstate.mChangedEnd = i + j;
            } else
            {
                inputmethodstate.mChangedStart = Math.min(inputmethodstate.mChangedStart, i);
                inputmethodstate.mChangedEnd = Math.max(inputmethodstate.mChangedEnd, (i + j) - inputmethodstate.mChangedDelta);
            }
            inputmethodstate.mChangedDelta = inputmethodstate.mChangedDelta + (k - j);
        }
        resetErrorChangedFlag();
        sendOnTextChanged(charsequence, i, j, k);
        onTextChanged(charsequence, i, j, k);
    }

    public boolean hasOverlappingRendering()
    {
        boolean flag;
        if(getBackground() != null && getBackground().getCurrent() != null || (mText instanceof Spannable) || hasSelection())
            flag = true;
        else
            flag = isHorizontalFadingEdgeEnabled();
        return flag;
    }

    boolean hasPasswordTransformationMethod()
    {
        return mTransformation instanceof PasswordTransformationMethod;
    }

    public boolean hasSelection()
    {
        boolean flag = false;
        int i = getSelectionStart();
        int j = getSelectionEnd();
        boolean flag1 = flag;
        if(i >= 0)
        {
            flag1 = flag;
            if(i != j)
                flag1 = true;
        }
        return flag1;
    }

    public void hideErrorIfUnchanged()
    {
        if(mEditor != null && mEditor.mError != null && mEditor.mErrorWasChanged ^ true)
            setError(null, null);
    }

    void invalidateCursor()
    {
        int i = getSelectionEnd();
        invalidateCursor(i, i, i);
    }

    void invalidateCursorPath()
    {
        if(!mHighlightPathBogus) goto _L2; else goto _L1
_L1:
        invalidateCursor();
_L4:
        return;
_L2:
        int i;
        int j;
        i = getCompoundPaddingLeft();
        j = getExtendedPaddingTop() + getVerticalOffset(true);
        if(mEditor.mCursorCount != 0)
            break; /* Loop/switch isn't completed */
        RectF rectf = TEMP_RECTF;
        rectf;
        JVM INSTR monitorenter ;
        float f = (float)Math.ceil(mTextPaint.getStrokeWidth());
        float f1;
        f1 = f;
        if(f < 1.0F)
            f1 = 1.0F;
        f1 /= 2.0F;
        mHighlightPath.computeBounds(TEMP_RECTF, false);
        invalidate((int)Math.floor(((float)i + TEMP_RECTF.left) - f1), (int)Math.floor(((float)j + TEMP_RECTF.top) - f1), (int)Math.ceil((float)i + TEMP_RECTF.right + f1), (int)Math.ceil((float)j + TEMP_RECTF.bottom + f1));
        rectf;
        JVM INSTR monitorexit ;
        if(true) goto _L4; else goto _L3
        Exception exception;
        exception;
        throw exception;
_L3:
        int k = 0;
        while(k < mEditor.mCursorCount) 
        {
            Rect rect = mEditor.mCursorDrawable[k].getBounds();
            invalidate(rect.left + i, rect.top + j, rect.right + i, rect.bottom + j);
            k++;
        }
        if(true) goto _L4; else goto _L5
_L5:
    }

    public void invalidateDrawable(Drawable drawable)
    {
        int i;
        int j;
        i = 0;
        j = 0;
        if(!verifyDrawable(drawable)) goto _L2; else goto _L1
_L1:
        Rect rect;
        int k;
        Drawables drawables;
        int l;
        int i1;
        int j1;
        rect = drawable.getBounds();
        k = mScrollX;
        i = mScrollY;
        drawables = mDrawables;
        l = j;
        i1 = k;
        j1 = i;
        if(drawables == null) goto _L4; else goto _L3
_L3:
        if(drawable != drawables.mShowing[0]) goto _L6; else goto _L5
_L5:
        int k1 = getCompoundPaddingTop();
        j = getCompoundPaddingBottom();
        j1 = mBottom;
        l = mTop;
        i1 = k + mPaddingLeft;
        j1 = i + ((j1 - l - j - k1 - drawables.mDrawableHeightLeft) / 2 + k1);
        l = 1;
_L4:
        i = l;
        if(l != 0)
        {
            invalidate(rect.left + i1, rect.top + j1, rect.right + i1, rect.bottom + j1);
            i = l;
        }
_L2:
        if(i == 0)
            super.invalidateDrawable(drawable);
        return;
_L6:
        if(drawable == drawables.mShowing[2])
        {
            l = getCompoundPaddingTop();
            j = getCompoundPaddingBottom();
            j1 = mBottom;
            int l1 = mTop;
            i1 = k + (mRight - mLeft - mPaddingRight - drawables.mDrawableSizeRight);
            j1 = i + ((j1 - l1 - j - l - drawables.mDrawableHeightRight) / 2 + l);
            l = 1;
        } else
        if(drawable == drawables.mShowing[1])
        {
            l = getCompoundPaddingLeft();
            i1 = getCompoundPaddingRight();
            i1 = k + ((mRight - mLeft - i1 - l - drawables.mDrawableWidthTop) / 2 + l);
            j1 = i + mPaddingTop;
            l = 1;
        } else
        {
            l = j;
            i1 = k;
            j1 = i;
            if(drawable == drawables.mShowing[3])
            {
                i1 = getCompoundPaddingLeft();
                l = getCompoundPaddingRight();
                i1 = k + ((mRight - mLeft - l - i1 - drawables.mDrawableWidthBottom) / 2 + i1);
                j1 = i + (mBottom - mTop - mPaddingBottom - drawables.mDrawableSizeBottom);
                l = 1;
            }
        }
        if(true) goto _L4; else goto _L7
_L7:
    }

    void invalidateRegion(int i, int j, boolean flag)
    {
        if(mLayout == null)
        {
            invalidate();
        } else
        {
            int k = mLayout.getLineForOffset(i);
            int l = mLayout.getLineTop(k);
            int i1 = l;
            if(k > 0)
                i1 = l - mLayout.getLineDescent(k - 1);
            int j1;
            int l1;
            int i2;
            int j2;
            if(i == j)
                j1 = k;
            else
                j1 = mLayout.getLineForOffset(j);
            l1 = mLayout.getLineBottom(j1);
            i2 = l1;
            j2 = i1;
            if(flag)
            {
                i2 = l1;
                j2 = i1;
                if(mEditor != null)
                {
                    int k2 = 0;
                    l = i1;
                    i1 = l1;
                    do
                    {
                        i2 = i1;
                        j2 = l;
                        if(k2 >= mEditor.mCursorCount)
                            break;
                        Rect rect = mEditor.mCursorDrawable[k2].getBounds();
                        l = Math.min(l, rect.top);
                        i1 = Math.max(i1, rect.bottom);
                        k2++;
                    } while(true);
                }
            }
            i1 = getCompoundPaddingLeft();
            l = getExtendedPaddingTop() + getVerticalOffset(true);
            if(k == j1 && flag ^ true)
            {
                int k1 = (int)mLayout.getPrimaryHorizontal(i);
                i = (int)((double)mLayout.getPrimaryHorizontal(j) + 1.0D);
                j = k1 + i1;
                i += i1;
            } else
            {
                j = i1;
                i = getWidth() - getCompoundPaddingRight();
            }
            invalidate(mScrollX + j, l + j2, mScrollX + i, l + i2);
        }
    }

    public boolean isAccessibilitySelectionExtendable()
    {
        return true;
    }

    public boolean isCursorVisible()
    {
        boolean flag;
        if(mEditor == null)
            flag = true;
        else
            flag = mEditor.mCursorVisible;
        return flag;
    }

    boolean isDeviceProvisioned()
    {
        boolean flag = true;
        if(mDeviceProvisionedState == 0)
        {
            int i;
            if(android.provider.Settings.Global.getInt(mContext.getContentResolver(), "device_provisioned", 0) != 0)
                i = 2;
            else
                i = 1;
            mDeviceProvisionedState = i;
        }
        if(mDeviceProvisionedState != 2)
            flag = false;
        return flag;
    }

    boolean isInBatchEditMode()
    {
        boolean flag = false;
        if(mEditor == null)
            return false;
        Editor.InputMethodState inputmethodstate = mEditor.mInputMethodState;
        if(inputmethodstate != null)
        {
            if(inputmethodstate.mBatchEditNesting > 0)
                flag = true;
            return flag;
        } else
        {
            return mEditor.mInBatchEditControllers;
        }
    }

    public boolean isInExtractedMode()
    {
        return false;
    }

    public boolean isInputMethodTarget()
    {
        InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
        boolean flag;
        if(inputmethodmanager != null)
            flag = inputmethodmanager.isActive(this);
        else
            flag = false;
        return flag;
    }

    protected boolean isPaddingOffsetRequired()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(mShadowRadius == 0.0F)
            if(mDrawables != null)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public boolean isPositionVisible(float f, float f1)
    {
        float af[] = TEMP_POSITION;
        af;
        JVM INSTR monitorenter ;
        float af1[] = TEMP_POSITION;
        Object obj;
        af1[0] = f;
        af1[1] = f1;
        obj = this;
_L6:
        if(obj == null)
            break; /* Loop/switch isn't completed */
        if(obj == this)
            break MISSING_BLOCK_LABEL_65;
        af1[0] = af1[0] - (float)((View) (obj)).getScrollX();
        af1[1] = af1[1] - (float)((View) (obj)).getScrollY();
        if(af1[0] < 0.0F) goto _L2; else goto _L1
_L1:
        f = af1[1];
        if(f >= 0.0F) goto _L3; else goto _L2
_L2:
        af;
        JVM INSTR monitorexit ;
        return false;
_L3:
        if(af1[0] > (float)((View) (obj)).getWidth() || af1[1] > (float)((View) (obj)).getHeight()) goto _L2; else goto _L4
_L4:
        if(!((View) (obj)).getMatrix().isIdentity())
            ((View) (obj)).getMatrix().mapPoints(af1);
        af1[0] = af1[0] + (float)((View) (obj)).getLeft();
        af1[1] = af1[1] + (float)((View) (obj)).getTop();
        obj = ((View) (obj)).getParent();
        if(obj instanceof View)
        {
            obj = (View)obj;
            continue; /* Loop/switch isn't completed */
        }
        obj = null;
        if(true) goto _L6; else goto _L5
_L5:
        return true;
        Exception exception;
        exception;
        throw exception;
    }

    boolean isSingleLine()
    {
        return mSingleLine;
    }

    public boolean isSuggestionsEnabled()
    {
        boolean flag;
        int i;
        boolean flag1;
        flag = true;
        if(mEditor == null)
            return false;
        if((mEditor.mInputType & 0xf) != 1)
            return false;
        if((mEditor.mInputType & 0x80000) > 0)
            return false;
        i = mEditor.mInputType & 0xff0;
        flag1 = flag;
        if(i == 0) goto _L2; else goto _L1
_L1:
        if(i != 48) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 80)
        {
            flag1 = flag;
            if(i != 64)
            {
                flag1 = flag;
                if(i != 160)
                    flag1 = false;
            }
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    boolean isTextEditable()
    {
        boolean flag;
        if((mText instanceof Editable) && onCheckIsTextEditor())
            flag = isEnabled();
        else
            flag = false;
        return flag;
    }

    public boolean isTextSelectable()
    {
        boolean flag;
        if(mEditor == null)
            flag = false;
        else
            flag = mEditor.mTextIsSelectable;
        return flag;
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if(mDrawables != null)
        {
            Drawable adrawable[] = mDrawables.mShowing;
            int i = 0;
            for(int j = adrawable.length; i < j; i++)
            {
                Drawable drawable = adrawable[i];
                if(drawable != null)
                    drawable.jumpToCurrentState();
            }

        }
    }

    public int length()
    {
        return mText.length();
    }

    protected void makeNewLayout(int i, int j, android.text.BoringLayout.Metrics metrics, android.text.BoringLayout.Metrics metrics1, int k, boolean flag)
    {
        stopMarquee();
        mOldMaximum = mMaximum;
        mOldMaxMode = mMaxMode;
        mHighlightPathBogus = true;
        int l = i;
        if(i < 0)
            l = 0;
        int j1 = j;
        if(j < 0)
            j1 = 0;
        android.text.Layout.Alignment alignment = getLayoutAlignment();
        int k1;
        boolean flag1;
        android.text.TextUtils.TruncateAt truncateat;
        android.text.TextUtils.TruncateAt truncateat2;
        boolean flag2;
        if(mSingleLine && mLayout != null)
        {
            if(alignment != android.text.Layout.Alignment.ALIGN_NORMAL)
            {
                if(alignment == android.text.Layout.Alignment.ALIGN_OPPOSITE)
                    i = 1;
                else
                    i = 0;
            } else
            {
                i = 1;
            }
        } else
        {
            i = 0;
        }
        k1 = 0;
        if(i != 0)
            k1 = mLayout.getParagraphDirection(0);
        if(mEllipsize != null && getKeyListener() == null)
            flag1 = true;
        else
            flag1 = false;
        if(mEllipsize == android.text.TextUtils.TruncateAt.MARQUEE)
        {
            if(mMarqueeFadeMode != 0)
                j = 1;
            else
                j = 0;
        } else
        {
            j = 0;
        }
        truncateat = mEllipsize;
        truncateat2 = truncateat;
        if(mEllipsize == android.text.TextUtils.TruncateAt.MARQUEE)
        {
            truncateat2 = truncateat;
            if(mMarqueeFadeMode == 1)
                truncateat2 = android.text.TextUtils.TruncateAt.END_SMALL;
        }
        if(mTextDir == null)
            mTextDir = getTextDirectionHeuristic();
        if(truncateat2 == mEllipsize)
            flag2 = true;
        else
            flag2 = false;
        mLayout = makeSingleLayout(l, metrics, k, alignment, flag1, truncateat2, flag2);
        if(j != 0)
        {
            android.text.TextUtils.TruncateAt truncateat1;
            if(truncateat2 == android.text.TextUtils.TruncateAt.MARQUEE)
                truncateat1 = android.text.TextUtils.TruncateAt.END;
            else
                truncateat1 = android.text.TextUtils.TruncateAt.MARQUEE;
            if(truncateat2 != mEllipsize)
                flag2 = true;
            else
                flag2 = false;
            mSavedMarqueeModeLayout = makeSingleLayout(l, metrics, k, alignment, flag1, truncateat1, flag2);
        }
        if(mEllipsize != null)
            j = 1;
        else
            j = 0;
        mHintLayout = null;
        if(mHint != null)
        {
            if(j != 0)
                j1 = l;
            metrics = metrics1;
            if(metrics1 == UNKNOWN_BORING)
            {
                metrics1 = BoringLayout.isBoring(mHint, mTextPaint, mTextDir, mHintBoring);
                metrics = metrics1;
                if(metrics1 != null)
                {
                    mHintBoring = metrics1;
                    metrics = metrics1;
                }
            }
            if(metrics != null)
                if(metrics.width <= j1 && (j == 0 || metrics.width <= k))
                {
                    if(mSavedHintLayout != null)
                        mHintLayout = mSavedHintLayout.replaceOrMake(mHint, mTextPaint, j1, alignment, mSpacingMult, mSpacingAdd, metrics, mIncludePad);
                    else
                        mHintLayout = BoringLayout.make(mHint, mTextPaint, j1, alignment, mSpacingMult, mSpacingAdd, metrics, mIncludePad);
                    mSavedHintLayout = (BoringLayout)mHintLayout;
                } else
                if(j != 0 && metrics.width <= j1)
                    if(mSavedHintLayout != null)
                        mHintLayout = mSavedHintLayout.replaceOrMake(mHint, mTextPaint, j1, alignment, mSpacingMult, mSpacingAdd, metrics, mIncludePad, mEllipsize, k);
                    else
                        mHintLayout = BoringLayout.make(mHint, mTextPaint, j1, alignment, mSpacingMult, mSpacingAdd, metrics, mIncludePad, mEllipsize, k);
            if(mHintLayout == null)
            {
                metrics = android.text.StaticLayout.Builder.obtain(mHint, 0, mHint.length(), mTextPaint, j1).setAlignment(alignment).setTextDirection(mTextDir).setLineSpacing(mSpacingAdd, mSpacingMult).setIncludePad(mIncludePad).setBreakStrategy(mBreakStrategy).setHyphenationFrequency(mHyphenationFrequency).setJustificationMode(mJustificationMode);
                int i1;
                if(mMaxMode == 1)
                    i1 = mMaximum;
                else
                    i1 = 0x7fffffff;
                metrics = metrics.setMaxLines(i1);
                if(j != 0)
                    metrics.setEllipsize(mEllipsize).setEllipsizedWidth(k);
                mHintLayout = metrics.build();
            }
        }
        if(flag || i != 0 && k1 != mLayout.getParagraphDirection(0))
            registerForPreDraw();
        if(mEllipsize == android.text.TextUtils.TruncateAt.MARQUEE && !compressText(k))
        {
            i = mLayoutParams.height;
            if(i != -2 && i != -1)
                startMarquee();
            else
                mRestartMarquee = true;
        }
        if(mEditor != null)
            mEditor.prepareCursorControllers();
    }

    protected Layout makeSingleLayout(int i, android.text.BoringLayout.Metrics metrics, int j, android.text.Layout.Alignment alignment, boolean flag, android.text.TextUtils.TruncateAt truncateat, boolean flag1)
    {
        TextPaint textpaint = null;
        if(mText instanceof Spannable)
        {
            CharSequence charsequence = mText;
            CharSequence charsequence1 = mTransformed;
            textpaint = mTextPaint;
            Object obj = mTextDir;
            float f = mSpacingMult;
            float f1 = mSpacingAdd;
            flag1 = mIncludePad;
            int k = mBreakStrategy;
            int l = mHyphenationFrequency;
            int i1 = mJustificationMode;
            if(getKeyListener() == null)
                metrics = truncateat;
            else
                metrics = null;
            metrics = new DynamicLayout(charsequence, charsequence1, textpaint, i, alignment, ((TextDirectionHeuristic) (obj)), f, f1, flag1, k, l, i1, metrics, j);
        } else
        {
            Object obj1 = metrics;
            if(metrics == UNKNOWN_BORING)
            {
                metrics = BoringLayout.isBoring(mTransformed, mTextPaint, mTextDir, mBoring);
                obj1 = metrics;
                if(metrics != null)
                {
                    mBoring = metrics;
                    obj1 = metrics;
                }
            }
            metrics = textpaint;
            if(obj1 != null)
                if(((android.text.BoringLayout.Metrics) (obj1)).width <= i && (truncateat == null || ((android.text.BoringLayout.Metrics) (obj1)).width <= j))
                {
                    if(flag1 && mSavedLayout != null)
                        obj1 = mSavedLayout.replaceOrMake(mTransformed, mTextPaint, i, alignment, mSpacingMult, mSpacingAdd, ((android.text.BoringLayout.Metrics) (obj1)), mIncludePad);
                    else
                        obj1 = BoringLayout.make(mTransformed, mTextPaint, i, alignment, mSpacingMult, mSpacingAdd, ((android.text.BoringLayout.Metrics) (obj1)), mIncludePad);
                    metrics = ((android.text.BoringLayout.Metrics) (obj1));
                    if(flag1)
                    {
                        mSavedLayout = (BoringLayout)obj1;
                        metrics = ((android.text.BoringLayout.Metrics) (obj1));
                    }
                } else
                {
                    metrics = textpaint;
                    if(flag)
                    {
                        metrics = textpaint;
                        if(((android.text.BoringLayout.Metrics) (obj1)).width <= i)
                            if(flag1 && mSavedLayout != null)
                                metrics = mSavedLayout.replaceOrMake(mTransformed, mTextPaint, i, alignment, mSpacingMult, mSpacingAdd, ((android.text.BoringLayout.Metrics) (obj1)), mIncludePad, truncateat, j);
                            else
                                metrics = BoringLayout.make(mTransformed, mTextPaint, i, alignment, mSpacingMult, mSpacingAdd, ((android.text.BoringLayout.Metrics) (obj1)), mIncludePad, truncateat, j);
                    }
                }
        }
        obj = metrics;
        if(metrics == null)
        {
            metrics = android.text.StaticLayout.Builder.obtain(mTransformed, 0, mTransformed.length(), mTextPaint, i).setAlignment(alignment).setTextDirection(mTextDir).setLineSpacing(mSpacingAdd, mSpacingMult).setIncludePad(mIncludePad).setBreakStrategy(mBreakStrategy).setHyphenationFrequency(mHyphenationFrequency).setJustificationMode(mJustificationMode);
            if(mMaxMode == 1)
                i = mMaximum;
            else
                i = 0x7fffffff;
            metrics = metrics.setMaxLines(i);
            if(flag)
                metrics.setEllipsize(truncateat).setEllipsizedWidth(j);
            obj = metrics.build();
        }
        return ((Layout) (obj));
    }

    public boolean moveCursorToVisibleOffset()
    {
        if(!(mText instanceof Spannable))
            return false;
        int i = getSelectionStart();
        if(i != getSelectionEnd())
            return false;
        int j = mLayout.getLineForOffset(i);
        int k = mLayout.getLineTop(j);
        int l = mLayout.getLineTop(j + 1);
        int i1 = mBottom - mTop - getExtendedPaddingTop() - getExtendedPaddingBottom();
        int j1 = (l - k) / 2;
        int k1 = j1;
        if(j1 > i1 / 4)
            k1 = i1 / 4;
        j1 = mScrollY;
        int l1;
        if(k < j1 + k1)
            j = mLayout.getLineForVertical(j1 + k1 + (l - k));
        else
        if(l > (i1 + j1) - k1)
            j = mLayout.getLineForVertical((i1 + j1) - k1 - (l - k));
        l1 = mRight;
        i1 = mLeft;
        j1 = getCompoundPaddingLeft();
        l = getCompoundPaddingRight();
        k = mScrollX;
        k1 = mLayout.getOffsetForHorizontal(j, k);
        j1 = mLayout.getOffsetForHorizontal(j, (l1 - i1 - j1 - l) + k);
        if(k1 < j1)
            j = k1;
        else
            j = j1;
        if(k1 <= j1)
            k1 = j1;
        j1 = i;
        if(i >= j)
        {
            j = j1;
            if(i > k1)
                j = k1;
        }
        if(j != i)
        {
            Selection.setSelection((Spannable)mText, j);
            return true;
        } else
        {
            return false;
        }
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        if(i != 100) goto _L2; else goto _L1
_L1:
        if(j != -1 || intent == null) goto _L4; else goto _L3
_L3:
        intent = intent.getCharSequenceExtra("android.intent.extra.PROCESS_TEXT");
        if(intent == null) goto _L2; else goto _L5
_L5:
        if(!isTextEditable()) goto _L7; else goto _L6
_L6:
        replaceSelectionWithText(intent);
        if(mEditor != null)
            mEditor.refreshTextActionMode();
_L2:
        return;
_L7:
        if(intent.length() > 0)
            Toast.makeText(getContext(), String.valueOf(intent), 1).show();
        continue; /* Loop/switch isn't completed */
_L4:
        if(mText instanceof Spannable)
            Selection.setSelection((Spannable)mText, getSelectionEnd());
        if(true) goto _L2; else goto _L8
_L8:
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(mEditor != null)
            mEditor.onAttachedToWindow();
        if(mPreDrawListenerDetached)
        {
            getViewTreeObserver().addOnPreDrawListener(this);
            mPreDrawListenerDetached = false;
        }
    }

    public void onBeginBatchEdit()
    {
    }

    public boolean onCheckIsTextEditor()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mEditor != null)
        {
            flag1 = flag;
            if(mEditor.mInputType != 0)
                flag1 = true;
        }
        return flag1;
    }

    public void onCommitCompletion(CompletionInfo completioninfo)
    {
    }

    public void onCommitCorrection(CorrectionInfo correctioninfo)
    {
        if(mEditor != null)
            mEditor.onCommitCorrection(correctioninfo);
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        if(!mLocalesChanged)
        {
            mTextPaint.setTextLocales(LocaleList.getDefault());
            if(mLayout != null)
            {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    protected void onCreateContextMenu(ContextMenu contextmenu)
    {
        if(mEditor != null)
            mEditor.onCreateContextMenu(contextmenu);
    }

    protected int[] onCreateDrawableState(int i)
    {
        int ai[];
        if(mSingleLine)
        {
            ai = super.onCreateDrawableState(i);
        } else
        {
            ai = super.onCreateDrawableState(i + 1);
            mergeDrawableStates(ai, MULTILINE_STATE_SET);
        }
        if(isTextSelectable())
        {
            int j = ai.length;
            for(i = 0; i < j; i++)
                if(ai[i] == 0x10100a7)
                {
                    int ai1[] = new int[j - 1];
                    System.arraycopy(ai, 0, ai1, 0, i);
                    System.arraycopy(ai, i + 1, ai1, i, j - i - 1);
                    return ai1;
                }

        }
        return ai;
    }

    public InputConnection onCreateInputConnection(EditorInfo editorinfo)
    {
        if(onCheckIsTextEditor() && isEnabled())
        {
            mEditor.createInputMethodStateIfNeeded();
            editorinfo.inputType = getInputType();
            if(mEditor.mInputContentType != null)
            {
                editorinfo.imeOptions = mEditor.mInputContentType.imeOptions;
                editorinfo.privateImeOptions = mEditor.mInputContentType.privateImeOptions;
                editorinfo.actionLabel = mEditor.mInputContentType.imeActionLabel;
                editorinfo.actionId = mEditor.mInputContentType.imeActionId;
                editorinfo.extras = mEditor.mInputContentType.extras;
                editorinfo.hintLocales = mEditor.mInputContentType.imeHintLocales;
            } else
            {
                editorinfo.imeOptions = 0;
                editorinfo.hintLocales = null;
            }
            if(focusSearch(130) != null)
                editorinfo.imeOptions = editorinfo.imeOptions | 0x8000000;
            if(focusSearch(33) != null)
                editorinfo.imeOptions = editorinfo.imeOptions | 0x4000000;
            if((editorinfo.imeOptions & 0xff) == 0)
            {
                EditableInputConnection editableinputconnection;
                if((editorinfo.imeOptions & 0x8000000) != 0)
                    editorinfo.imeOptions = editorinfo.imeOptions | 5;
                else
                    editorinfo.imeOptions = editorinfo.imeOptions | 6;
                if(!shouldAdvanceFocusOnEnter())
                    editorinfo.imeOptions = editorinfo.imeOptions | 0x40000000;
            }
            if(isMultilineInputType(editorinfo.inputType))
                editorinfo.imeOptions = editorinfo.imeOptions | 0x40000000;
            editorinfo.hintText = mHint;
            if(mText instanceof Editable)
            {
                editableinputconnection = new EditableInputConnection(this);
                editorinfo.initialSelStart = getSelectionStart();
                editorinfo.initialSelEnd = getSelectionEnd();
                editorinfo.initialCapsMode = editableinputconnection.getCursorCapsMode(getInputType());
                return editableinputconnection;
            }
        }
        return null;
    }

    protected void onDetachedFromWindowInternal()
    {
        if(mPreDrawRegistered)
        {
            getViewTreeObserver().removeOnPreDrawListener(this);
            mPreDrawListenerDetached = true;
        }
        resetResolvedDrawables();
        if(mEditor != null)
            mEditor.onDetachedFromWindow();
        super.onDetachedFromWindowInternal();
    }

    public boolean onDragEvent(DragEvent dragevent)
    {
        switch(dragevent.getAction())
        {
        case 4: // '\004'
        default:
            return true;

        case 1: // '\001'
            boolean flag;
            if(mEditor != null)
                flag = mEditor.hasInsertionController();
            else
                flag = false;
            return flag;

        case 5: // '\005'
            requestFocus();
            return true;

        case 2: // '\002'
            if(mText instanceof Spannable)
            {
                int i = getOffsetForPosition(dragevent.getX(), dragevent.getY());
                Selection.setSelection((Spannable)mText, i);
            }
            return true;

        case 3: // '\003'
            break;
        }
        if(mEditor != null)
            mEditor.onDrop(dragevent);
        return true;
    }

    protected void onDraw(Canvas canvas)
    {
        restartMarqueeIfNeeded();
        super.onDraw(canvas);
        int i = getCompoundPaddingLeft();
        int k = getCompoundPaddingTop();
        int l = getCompoundPaddingRight();
        int i1 = getCompoundPaddingBottom();
        int j1 = mScrollX;
        int k1 = mScrollY;
        int l1 = mRight;
        int i2 = mLeft;
        int j2 = mBottom;
        int l2 = mTop;
        boolean flag = isLayoutRtl();
        int j3 = getHorizontalOffsetForDrawables();
        int k3;
        Object obj;
        int i4;
        Object obj1;
        int j4;
        int k4;
        float f;
        float f1;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f8;
        if(flag)
            k3 = 0;
        else
            k3 = j3;
        if(!flag)
            j3 = 0;
        obj = mDrawables;
        if(obj != null)
        {
            int l3 = j2 - l2 - i1 - k;
            l = l1 - i2 - l - i;
            if(((Drawables) (obj)).mShowing[0] != null)
            {
                canvas.save();
                canvas.translate(mPaddingLeft + j1 + k3, k1 + k + (l3 - ((Drawables) (obj)).mDrawableHeightLeft) / 2);
                ((Drawables) (obj)).mShowing[0].draw(canvas);
                canvas.restore();
            }
            if(((Drawables) (obj)).mShowing[2] != null)
            {
                canvas.save();
                canvas.translate((j1 + l1) - i2 - mPaddingRight - ((Drawables) (obj)).mDrawableSizeRight - j3, k1 + k + (l3 - ((Drawables) (obj)).mDrawableHeightRight) / 2);
                ((Drawables) (obj)).mShowing[2].draw(canvas);
                canvas.restore();
            }
            if(((Drawables) (obj)).mShowing[1] != null)
            {
                canvas.save();
                canvas.translate(j1 + i + (l - ((Drawables) (obj)).mDrawableWidthTop) / 2, mPaddingTop + k1);
                ((Drawables) (obj)).mShowing[1].draw(canvas);
                canvas.restore();
            }
            if(((Drawables) (obj)).mShowing[3] != null)
            {
                canvas.save();
                canvas.translate(j1 + i + (l - ((Drawables) (obj)).mDrawableWidthBottom) / 2, (k1 + j2) - l2 - mPaddingBottom - ((Drawables) (obj)).mDrawableSizeBottom);
                ((Drawables) (obj)).mShowing[3].draw(canvas);
                canvas.restore();
            }
        }
        k3 = mCurTextColor;
        if(mLayout == null)
            assumeLayout();
        obj1 = mLayout;
        obj = obj1;
        j3 = k3;
        if(mHint != null)
        {
            obj = obj1;
            j3 = k3;
            if(mText.length() == 0)
            {
                if(mHintTextColor != null)
                    k3 = mCurHintTextColor;
                obj = mHintLayout;
                j3 = k3;
            }
        }
        mTextPaint.setColor(j3);
        mTextPaint.drawableState = getDrawableState();
        canvas.save();
        l = getExtendedPaddingTop();
        j3 = getExtendedPaddingBottom();
        j4 = mBottom;
        i4 = mTop;
        k4 = mLayout.getHeight();
        f = i + j1;
        if(k1 == 0)
            k3 = 0;
        else
            k3 = l + k1;
        f1 = k3;
        f2 = (l1 - i2 - getCompoundPaddingRight()) + j1;
        k3 = j3;
        if(k1 == k4 - (j4 - i4 - i1 - k))
            k3 = 0;
        f3 = ((j2 - l2) + k1) - k3;
        f4 = f3;
        f5 = f;
        f6 = f2;
        f8 = f1;
        if(mShadowRadius != 0.0F)
        {
            f5 = f + Math.min(0.0F, mShadowDx - mShadowRadius);
            f6 = f2 + Math.max(0.0F, mShadowDx + mShadowRadius);
            f8 = f1 + Math.min(0.0F, mShadowDy - mShadowRadius);
            f4 = f3 + Math.max(0.0F, mShadowDy + mShadowRadius);
        }
        canvas.clipRect(f5, f8, f6, f4);
        k3 = 0;
        j3 = 0;
        if((mGravity & 0x70) != 48)
        {
            k3 = getVerticalOffset(false);
            j3 = getVerticalOffset(true);
        }
        canvas.translate(i, l + k3);
        i = getLayoutDirection();
        i = Gravity.getAbsoluteGravity(mGravity, i);
        if(isMarqueeFadeEnabled())
        {
            if(!mSingleLine && getLineCount() == 1 && canMarquee() && (i & 7) != 3)
            {
                k1 = mRight;
                int j = mLeft;
                int k2 = getCompoundPaddingLeft();
                int i3 = getCompoundPaddingRight();
                float f7 = mLayout.getLineRight(0);
                float f9 = k1 - j - (k2 + i3);
                canvas.translate((float)((Layout) (obj)).getParagraphDirection(0) * (f7 - f9), 0.0F);
            }
            if(mMarquee != null && mMarquee.isRunning())
            {
                float f10 = -mMarquee.getScroll();
                canvas.translate((float)((Layout) (obj)).getParagraphDirection(0) * f10, 0.0F);
            }
        }
        k3 = j3 - k3;
        obj1 = getUpdatedHighlightPath();
        if(mEditor != null)
            mEditor.onDraw(canvas, ((Layout) (obj)), ((Path) (obj1)), mHighlightPaint, k3);
        else
            ((Layout) (obj)).draw(canvas, ((Path) (obj1)), mHighlightPaint, k3);
        if(mMarquee != null && mMarquee.shouldDrawGhost())
        {
            float f11 = mMarquee.getGhostOffset();
            canvas.translate((float)((Layout) (obj)).getParagraphDirection(0) * f11, 0.0F);
            ((Layout) (obj)).draw(canvas, ((Path) (obj1)), mHighlightPaint, k3);
        }
        canvas.restore();
    }

    public void onEditorAction(int i)
    {
        Object obj;
        if(mEditor == null)
            obj = null;
        else
            obj = mEditor.mInputContentType;
        if(obj != null)
        {
            if(((Editor.InputContentType) (obj)).onEditorActionListener != null && ((Editor.InputContentType) (obj)).onEditorActionListener.onEditorAction(this, i, null))
                return;
            if(i == 5)
            {
                obj = focusSearch(2);
                if(obj != null && !((View) (obj)).requestFocus(2))
                    throw new IllegalStateException("focus search returned a view that wasn't able to take focus!");
                else
                    return;
            }
            if(i == 7)
            {
                obj = focusSearch(1);
                if(obj != null && !((View) (obj)).requestFocus(1))
                    throw new IllegalStateException("focus search returned a view that wasn't able to take focus!");
                else
                    return;
            }
            if(i == 6)
            {
                obj = InputMethodManager.peekInstance();
                if(obj != null && ((InputMethodManager) (obj)).isActive(this))
                    ((InputMethodManager) (obj)).hideSoftInputFromWindow(getWindowToken(), 0);
                return;
            }
        }
        obj = getViewRootImpl();
        if(obj != null)
        {
            long l = SystemClock.uptimeMillis();
            ((ViewRootImpl) (obj)).dispatchKeyFromIme(new KeyEvent(l, l, 0, 66, 0, 0, -1, 0, 22));
            ((ViewRootImpl) (obj)).dispatchKeyFromIme(new KeyEvent(SystemClock.uptimeMillis(), l, 1, 66, 0, 0, -1, 0, 22));
        }
    }

    public void onEndBatchEdit()
    {
    }

    protected void onFocusChanged(boolean flag, int i, Rect rect)
    {
        if(isTemporarilyDetached())
        {
            super.onFocusChanged(flag, i, rect);
            return;
        }
        if(mEditor != null)
            mEditor.onFocusChanged(flag, i);
        if(flag && (mText instanceof Spannable))
            MetaKeyKeyListener.resetMetaState((Spannable)mText);
        startStopMarquee(flag);
        if(mTransformation != null)
            mTransformation.onFocusChanged(this, mText, flag, i, rect);
        super.onFocusChanged(flag, i, rect);
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        if(mMovement == null || !(mText instanceof Spannable) || mLayout == null)
            break MISSING_BLOCK_LABEL_50;
        boolean flag = mMovement.onGenericMotionEvent(this, (Spannable)mText, motionevent);
        if(flag)
            return true;
        break MISSING_BLOCK_LABEL_50;
        AbstractMethodError abstractmethoderror;
        abstractmethoderror;
        return super.onGenericMotionEvent(motionevent);
    }

    public void onInitializeAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEventInternal(accessibilityevent);
        accessibilityevent.setPassword(hasPasswordTransformationMethod());
        if(accessibilityevent.getEventType() == 8192)
        {
            accessibilityevent.setFromIndex(Selection.getSelectionStart(mText));
            accessibilityevent.setToIndex(Selection.getSelectionEnd(mText));
            accessibilityevent.setItemCount(mText.length());
        }
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        accessibilitynodeinfo.setPassword(hasPasswordTransformationMethod());
        accessibilitynodeinfo.setText(getTextForAccessibility());
        accessibilitynodeinfo.setHintText(mHint);
        accessibilitynodeinfo.setShowingHintText(isShowingHint());
        if(mBufferType == BufferType.EDITABLE)
        {
            accessibilitynodeinfo.setEditable(true);
            if(isEnabled())
                accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_TEXT);
        }
        if(mEditor != null)
        {
            accessibilitynodeinfo.setInputType(mEditor.mInputType);
            if(mEditor.mError != null)
            {
                accessibilitynodeinfo.setContentInvalid(true);
                accessibilitynodeinfo.setError(mEditor.mError);
            }
        }
        if(!TextUtils.isEmpty(mText))
        {
            accessibilitynodeinfo.addAction(256);
            accessibilitynodeinfo.addAction(512);
            accessibilitynodeinfo.setMovementGranularities(31);
            accessibilitynodeinfo.addAction(0x20000);
            accessibilitynodeinfo.setAvailableExtraData(Arrays.asList(new String[] {
                "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY"
            }));
        }
        if(isFocused())
        {
            if(canCopy())
                accessibilitynodeinfo.addAction(16384);
            if(canPaste())
                accessibilitynodeinfo.addAction(32768);
            if(canCut())
                accessibilitynodeinfo.addAction(0x10000);
            if(canShare())
                accessibilitynodeinfo.addAction(new android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction(0x10000000, getResources().getString(0x10405c7)));
            if(canProcessText())
                mEditor.mProcessTextIntentActionsHandler.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
        }
        int i = mFilters.length;
        for(int j = 0; j < i; j++)
        {
            InputFilter inputfilter = mFilters[j];
            if(inputfilter instanceof android.text.InputFilter.LengthFilter)
                accessibilitynodeinfo.setMaxTextLength(((android.text.InputFilter.LengthFilter)inputfilter).getMax());
        }

        if(!isSingleLine())
            accessibilitynodeinfo.setMultiLine(true);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(doKeyDown(i, keyevent, null) == 0)
            return super.onKeyDown(i, keyevent);
        else
            return true;
    }

    public boolean onKeyMultiple(int i, int j, KeyEvent keyevent)
    {
        KeyEvent keyevent1;
        int k;
        keyevent1 = KeyEvent.changeAction(keyevent, 0);
        k = doKeyDown(i, keyevent1, keyevent);
        if(k == 0)
            return super.onKeyMultiple(i, j, keyevent);
        if(k == -1)
            return true;
        j--;
        keyevent = KeyEvent.changeAction(keyevent, 1);
        if(k != 1) goto _L2; else goto _L1
_L1:
        mEditor.mKeyListener.onKeyUp(this, (Editable)mText, i, keyevent);
        while(--j > 0) 
        {
            mEditor.mKeyListener.onKeyDown(this, (Editable)mText, i, keyevent1);
            mEditor.mKeyListener.onKeyUp(this, (Editable)mText, i, keyevent);
        }
        hideErrorIfUnchanged();
_L4:
        return true;
_L2:
        if(k == 2)
        {
            mMovement.onKeyUp(this, (Spannable)mText, i, keyevent);
            while(--j > 0) 
            {
                mMovement.onKeyDown(this, (Spannable)mText, i, keyevent1);
                mMovement.onKeyUp(this, (Spannable)mText, i, keyevent);
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean onKeyPreIme(int i, KeyEvent keyevent)
    {
        if(i == 4 && handleBackInTextActionModeIfNeeded(keyevent))
            return true;
        else
            return super.onKeyPreIme(i, keyevent);
    }

    public boolean onKeyShortcut(int i, KeyEvent keyevent)
    {
        if(!keyevent.hasModifiers(4096)) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR lookupswitch 5: default 60
    //                   29: 67
    //                   31: 109
    //                   50: 123
    //                   52: 95
    //                   54: 81;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        return super.onKeyShortcut(i, keyevent);
_L4:
        if(!canSelectText()) goto _L3; else goto _L9
_L9:
        return onTextContextMenuItem(0x102001f);
_L8:
        if(!canUndo()) goto _L3; else goto _L10
_L10:
        return onTextContextMenuItem(0x1020032);
_L7:
        if(!canCut()) goto _L3; else goto _L11
_L11:
        return onTextContextMenuItem(0x1020020);
_L5:
        if(!canCopy()) goto _L3; else goto _L12
_L12:
        return onTextContextMenuItem(0x1020021);
_L6:
        if(!canPaste()) goto _L3; else goto _L13
_L13:
        return onTextContextMenuItem(0x1020022);
_L2:
        if(!keyevent.hasModifiers(4097)) goto _L3; else goto _L14
_L14:
        switch(i)
        {
        case 54: // '6'
            continue; /* Loop/switch isn't completed */

        case 50: // '2'
            if(canPaste())
                return onTextContextMenuItem(0x1020031);
            break;
        }
        continue; /* Loop/switch isn't completed */
        if(!canRedo()) goto _L3; else goto _L15
_L15:
        return onTextContextMenuItem(0x1020033);
        if(true) goto _L3; else goto _L16
_L16:
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(!isEnabled())
            return super.onKeyUp(i, keyevent);
        if(!KeyEvent.isModifierKey(i))
            mPreventDefaultMovement = false;
        i;
        JVM INSTR lookupswitch 2: default 52
    //                   23: 96
    //                   66: 175;
           goto _L1 _L2 _L3
_L1:
        if(mEditor != null && mEditor.mKeyListener != null && mEditor.mKeyListener.onKeyUp(this, (Editable)mText, i, keyevent))
            return true;
        break; /* Loop/switch isn't completed */
_L2:
        if(keyevent.hasNoModifiers() && !hasOnClickListeners() && mMovement != null && (mText instanceof Editable) && mLayout != null && onCheckIsTextEditor())
        {
            InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
            viewClicked(inputmethodmanager);
            if(inputmethodmanager != null && getShowSoftInputOnFocus())
                inputmethodmanager.showSoftInput(this, 0);
        }
        return super.onKeyUp(i, keyevent);
_L3:
        if(!keyevent.hasNoModifiers()) goto _L1; else goto _L4
_L4:
        if(mEditor != null && mEditor.mInputContentType != null && mEditor.mInputContentType.onEditorActionListener != null && mEditor.mInputContentType.enterDown)
        {
            mEditor.mInputContentType.enterDown = false;
            if(mEditor.mInputContentType.onEditorActionListener.onEditorAction(this, 0, keyevent))
                return true;
        }
        if(((keyevent.getFlags() & 0x10) != 0 || shouldAdvanceFocusOnEnter()) && !hasOnClickListeners())
        {
            View view = focusSearch(130);
            if(view != null)
                if(!view.requestFocus(130))
                {
                    throw new IllegalStateException("focus search returned a view that wasn't able to take focus!");
                } else
                {
                    super.onKeyUp(i, keyevent);
                    return true;
                }
            if((keyevent.getFlags() & 0x10) != 0)
            {
                InputMethodManager inputmethodmanager1 = InputMethodManager.peekInstance();
                if(inputmethodmanager1 != null && inputmethodmanager1.isActive(this))
                    inputmethodmanager1.hideSoftInputFromWindow(getWindowToken(), 0);
            }
        }
        return super.onKeyUp(i, keyevent);
        if(mMovement != null && mLayout != null && mMovement.onKeyUp(this, (Spannable)mText, i, keyevent))
            return true;
        else
            return super.onKeyUp(i, keyevent);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if(mDeferScroll >= 0)
        {
            i = mDeferScroll;
            mDeferScroll = -1;
            bringPointIntoView(Math.min(i, mText.length()));
        }
        autoSizeText();
    }

    void onLocaleChanged()
    {
        mEditor.onLocaleChanged();
    }

    protected void onMeasure(int i, int j)
    {
        int j2;
        int k = android.view.View.MeasureSpec.getMode(i);
        int l = android.view.View.MeasureSpec.getMode(j);
        int i1 = android.view.View.MeasureSpec.getSize(i);
        int j1 = android.view.View.MeasureSpec.getSize(j);
        android.text.BoringLayout.Metrics metrics = UNKNOWN_BORING;
        android.text.BoringLayout.Metrics metrics1 = UNKNOWN_BORING;
        if(mTextDir == null)
            mTextDir = getTextDirectionHeuristic();
        int k1 = -1;
        j = 0;
        int l1 = 0;
        android.text.BoringLayout.Metrics metrics2;
        int i2;
        if(k == 0x40000000)
        {
            j = i1;
            metrics2 = metrics;
        } else
        {
            i = k1;
            if(mLayout != null)
            {
                i = k1;
                if(mEllipsize == null)
                    i = desired(mLayout);
            }
            Object obj;
            if(i < 0)
            {
                android.text.BoringLayout.Metrics metrics3 = BoringLayout.isBoring(mTransformed, mTextPaint, mTextDir, mBoring);
                metrics = metrics3;
                j2 = j;
                if(metrics3 != null)
                {
                    mBoring = metrics3;
                    j2 = j;
                    metrics = metrics3;
                }
            } else
            {
                j2 = 1;
            }
            if(metrics == null || metrics == UNKNOWN_BORING)
            {
                j = i;
                if(i < 0)
                    j = (int)Math.ceil(Layout.getDesiredWidth(mTransformed, 0, mTransformed.length(), mTextPaint, mTextDir));
                l1 = j;
                i = j;
            } else
            {
                l1 = metrics.width;
            }
            obj = mDrawables;
            j = l1;
            if(obj != null)
                j = Math.max(Math.max(l1, ((Drawables) (obj)).mDrawableWidthTop), ((Drawables) (obj)).mDrawableWidthBottom);
            obj = metrics1;
            i2 = j;
            if(mHint != null)
            {
                i2 = -1;
                l1 = i2;
                if(mHintLayout != null)
                {
                    l1 = i2;
                    if(mEllipsize == null)
                        l1 = desired(mHintLayout);
                }
                if(l1 < 0)
                {
                    obj = BoringLayout.isBoring(mHint, mTextPaint, mTextDir, mHintBoring);
                    metrics1 = ((android.text.BoringLayout.Metrics) (obj));
                    if(obj != null)
                    {
                        mHintBoring = ((android.text.BoringLayout.Metrics) (obj));
                        metrics1 = ((android.text.BoringLayout.Metrics) (obj));
                    }
                }
                if(metrics1 == null || metrics1 == UNKNOWN_BORING)
                {
                    i2 = l1;
                    if(l1 < 0)
                        i2 = (int)Math.ceil(Layout.getDesiredWidth(mHint, 0, mHint.length(), mTextPaint, mTextDir));
                    l1 = i2;
                } else
                {
                    l1 = metrics1.width;
                }
                obj = metrics1;
                i2 = j;
                if(l1 > j)
                {
                    i2 = l1;
                    obj = metrics1;
                }
            }
            j = i2 + (getCompoundPaddingLeft() + getCompoundPaddingRight());
            if(mMaxWidthMode == 1)
                j = Math.min(j, mMaxWidth * getLineHeight());
            else
                j = Math.min(j, mMaxWidth);
            if(mMinWidthMode == 1)
                j = Math.max(j, mMinWidth * getLineHeight());
            else
                j = Math.max(j, mMinWidth);
            i2 = Math.max(j, getSuggestedMinimumWidth());
            metrics2 = metrics;
            metrics1 = ((android.text.BoringLayout.Metrics) (obj));
            k1 = i;
            l1 = j2;
            j = i2;
            if(k == 0x80000000)
            {
                j = Math.min(i1, i2);
                metrics2 = metrics;
                metrics1 = ((android.text.BoringLayout.Metrics) (obj));
                k1 = i;
                l1 = j2;
            }
        }
        i1 = j - getCompoundPaddingLeft() - getCompoundPaddingRight();
        i = i1;
        if(mHorizontallyScrolling)
            i = 0x100000;
        i2 = i;
        if(mHintLayout == null)
            i = i2;
        else
            i = mHintLayout.getWidth();
        if(mLayout == null)
        {
            makeNewLayout(i2, i2, metrics2, metrics1, j - getCompoundPaddingLeft() - getCompoundPaddingRight(), false);
        } else
        {
            boolean flag;
            if(mLayout.getWidth() != i2 || i != i2)
                j2 = 1;
            else
            if(mLayout.getEllipsizedWidth() != j - getCompoundPaddingLeft() - getCompoundPaddingRight())
                j2 = 1;
            else
                j2 = 0;
            if(mHint == null && mEllipsize == null && i2 > mLayout.getWidth())
            {
                if(!(mLayout instanceof BoringLayout))
                {
                    if(l1 != 0 && k1 >= 0 && k1 <= i2)
                        i = 1;
                    else
                        i = 0;
                } else
                {
                    i = 1;
                }
            } else
            {
                i = 0;
            }
            if(mMaxMode != mOldMaxMode || mMaximum != mOldMaximum)
                flag = true;
            else
                flag = false;
            if(j2 != 0 || flag)
                if(!flag && i != 0)
                    mLayout.increaseWidthTo(i2);
                else
                    makeNewLayout(i2, i2, metrics2, metrics1, j - getCompoundPaddingLeft() - getCompoundPaddingRight(), false);
        }
        if(l == 0x40000000)
        {
            i = j1;
            mDesiredHeightAtMeasure = -1;
        } else
        {
            j2 = getDesiredHeight();
            i = j2;
            mDesiredHeightAtMeasure = j2;
            if(l == 0x80000000)
                i = Math.min(j2, j1);
        }
        l1 = i - getCompoundPaddingTop() - getCompoundPaddingBottom();
        j2 = l1;
        if(mMaxMode == 1)
        {
            j2 = l1;
            if(mLayout.getLineCount() > mMaximum)
                j2 = Math.min(l1, mLayout.getLineTop(mMaximum));
        }
        break MISSING_BLOCK_LABEL_217;
        if(mMovement != null || mLayout.getWidth() > i1 || mLayout.getHeight() > j2)
            registerForPreDraw();
        else
            scrollTo(0, 0);
        setMeasuredDimension(j, i);
        return;
    }

    public void onPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        super.onPopulateAccessibilityEventInternal(accessibilityevent);
        CharSequence charsequence = getTextForAccessibility();
        if(!TextUtils.isEmpty(charsequence))
            accessibilityevent.getText().add(charsequence);
    }

    public boolean onPreDraw()
    {
        if(mLayout == null)
            assumeLayout();
        if(mMovement != null)
        {
            int i = getSelectionEnd();
            int j = i;
            if(mEditor != null)
            {
                j = i;
                if(mEditor.mSelectionModifierCursorController != null)
                {
                    j = i;
                    if(mEditor.mSelectionModifierCursorController.isSelectionStartDragged())
                        j = getSelectionStart();
                }
            }
            i = j;
            if(j < 0)
            {
                i = j;
                if((mGravity & 0x70) == 80)
                    i = mText.length();
            }
            if(i >= 0)
                bringPointIntoView(i);
        } else
        {
            bringTextIntoView();
        }
        if(mEditor != null && mEditor.mCreatedWithASelection)
        {
            mEditor.refreshTextActionMode();
            mEditor.mCreatedWithASelection = false;
        }
        unregisterForPreDraw();
        return true;
    }

    public boolean onPrivateIMECommand(String s, Bundle bundle)
    {
        return false;
    }

    public void onProvideAutofillStructure(ViewStructure viewstructure, int i)
    {
        super.onProvideAutofillStructure(viewstructure, i);
        onProvideAutoStructureForAssistOrAutofill(viewstructure, true);
    }

    public void onProvideStructure(ViewStructure viewstructure)
    {
        super.onProvideStructure(viewstructure);
        onProvideAutoStructureForAssistOrAutofill(viewstructure, false);
    }

    public void onResolveDrawables(int i)
    {
        if(mLastLayoutDirection == i)
            return;
        mLastLayoutDirection = i;
        if(mDrawables != null && mDrawables.resolveWithLayoutDirection(i))
        {
            prepareDrawableForDisplay(mDrawables.mShowing[0]);
            prepareDrawableForDisplay(mDrawables.mShowing[2]);
            applyCompoundDrawableTint();
        }
    }

    public PointerIcon onResolvePointerIcon(MotionEvent motionevent, int i)
    {
        if((mText instanceof Spannable) && mLinksClickable)
        {
            int j = getOffsetForPosition(motionevent.getX(i), motionevent.getY(i));
            if(((ClickableSpan[])((Spannable)mText).getSpans(j, j, android/text/style/ClickableSpan)).length > 0)
                return PointerIcon.getSystemIcon(mContext, 1002);
        }
        if(isTextSelectable() || isTextEditable())
            return PointerIcon.getSystemIcon(mContext, 1008);
        else
            return super.onResolvePointerIcon(motionevent, i);
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        SavedState savedstate;
        if(!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        savedstate = (SavedState)parcelable;
        super.onRestoreInstanceState(savedstate.getSuperState());
        if(savedstate.text != null)
            setText(savedstate.text);
        if(savedstate.selStart < 0 || savedstate.selEnd < 0 || !(mText instanceof Spannable)) goto _L2; else goto _L1
_L1:
        int i = mText.length();
        if(savedstate.selStart <= i && savedstate.selEnd <= i) goto _L4; else goto _L3
_L3:
        parcelable = "";
        if(savedstate.text != null)
            parcelable = "(restored) ";
        Log.e("TextView", (new StringBuilder()).append("Saved cursor position ").append(savedstate.selStart).append("/").append(savedstate.selEnd).append(" out of range for ").append(parcelable).append("text ").append(mText).toString());
_L2:
        if(savedstate.error != null)
            post(new Runnable() {

                public void run()
                {
                    if(TextView._2D_get1(TextView.this) == null || TextView._2D_get1(TextView.this).mErrorWasChanged ^ true)
                        setError(error);
                }

                final TextView this$0;
                final CharSequence val$error;

            
            {
                this$0 = TextView.this;
                error = charsequence;
                super();
            }
            }
);
        if(savedstate.editorState != null)
        {
            createEditorIfNeeded();
            mEditor.restoreInstanceState(savedstate.editorState);
        }
        return;
_L4:
        Selection.setSelection((Spannable)mText, savedstate.selStart, savedstate.selEnd);
        if(savedstate.frozenWithFocus)
        {
            createEditorIfNeeded();
            mEditor.mFrozenWithFocus = true;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void onRtlPropertiesChanged(int i)
    {
        super.onRtlPropertiesChanged(i);
        TextDirectionHeuristic textdirectionheuristic = getTextDirectionHeuristic();
        if(mTextDir != textdirectionheuristic)
        {
            mTextDir = textdirectionheuristic;
            if(mLayout != null)
                checkForRelayout();
        }
    }

    public Parcelable onSaveInstanceState()
    {
        Object obj;
        boolean flag;
        int i;
        int j;
        boolean flag2;
label0:
        {
            obj = super.onSaveInstanceState();
            flag = getFreezesText();
            boolean flag1 = false;
            i = -1;
            j = -1;
            flag2 = flag1;
            if(mText == null)
                break label0;
            int k = getSelectionStart();
            int l = getSelectionEnd();
            if(k < 0)
            {
                j = l;
                flag2 = flag1;
                i = k;
                if(l < 0)
                    break label0;
            }
            flag2 = true;
            i = k;
            j = l;
        }
        if(flag || flag2)
        {
            SavedState savedstate = new SavedState(((Parcelable) (obj)));
            if(flag)
                if(mText instanceof Spanned)
                {
                    obj = new SpannableStringBuilder(mText);
                    if(mEditor != null)
                    {
                        removeMisspelledSpans(((Spannable) (obj)));
                        ((Spannable) (obj)).removeSpan(mEditor.mSuggestionRangeSpan);
                    }
                    savedstate.text = ((CharSequence) (obj));
                } else
                {
                    savedstate.text = mText.toString();
                }
            if(flag2)
            {
                savedstate.selStart = i;
                savedstate.selEnd = j;
            }
            if(isFocused() && i >= 0 && j >= 0)
                savedstate.frozenWithFocus = true;
            savedstate.error = getError();
            if(mEditor != null)
                savedstate.editorState = mEditor.saveInstanceState();
            return savedstate;
        } else
        {
            return ((Parcelable) (obj));
        }
    }

    public void onScreenStateChanged(int i)
    {
        super.onScreenStateChanged(i);
        if(mEditor != null)
            mEditor.onScreenStateChanged(i);
    }

    protected void onScrollChanged(int i, int j, int k, int l)
    {
        super.onScrollChanged(i, j, k, l);
        if(mEditor != null)
            mEditor.onScrollChanged();
    }

    protected void onSelectionChanged(int i, int j)
    {
        sendAccessibilityEvent(8192);
    }

    protected void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public boolean onTextContextMenuItem(int i)
    {
        int j = 0;
        int k = mText.length();
        if(isFocused())
        {
            int l = getSelectionStart();
            k = getSelectionEnd();
            j = Math.max(0, Math.min(l, k));
            k = Math.max(0, Math.max(l, k));
        }
        switch(i)
        {
        default:
            return false;

        case 16908319: 
            boolean flag = hasSelection();
            selectAllText();
            if(mEditor != null && flag)
                mEditor.invalidateActionModeAsync();
            return true;

        case 16908338: 
            if(mEditor != null)
                mEditor.undo();
            return true;

        case 16908339: 
            if(mEditor != null)
                mEditor.redo();
            return true;

        case 16908322: 
            paste(j, k, true);
            return true;

        case 16908337: 
            paste(j, k, false);
            return true;

        case 16908320: 
            setPrimaryClip(ClipData.newPlainText(null, getTransformedText(j, k)));
            deleteText_internal(j, k);
            return true;

        case 16908321: 
            setPrimaryClip(ClipData.newPlainText(null, getTransformedText(j, k)));
            stopTextActionMode();
            return true;

        case 16908340: 
            if(mEditor != null)
                mEditor.replace();
            return true;

        case 16908341: 
            shareSelectedText();
            return true;

        case 16908355: 
            requestAutofill();
            stopTextActionMode();
            return true;
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
label0:
        {
label1:
            {
                int i = motionevent.getActionMasked();
                if(mEditor != null)
                {
                    mEditor.onTouchEvent(motionevent);
                    if(mEditor.mSelectionModifierCursorController != null && mEditor.mSelectionModifierCursorController.isDragAcceleratorActive())
                        return true;
                }
                flag = super.onTouchEvent(motionevent);
                if(mEditor != null && mEditor.mDiscardNextActionUp && i == 1)
                {
                    mEditor.mDiscardNextActionUp = false;
                    if(mEditor.mIsInsertionActionModeStartPending)
                    {
                        mEditor.startInsertionActionMode();
                        mEditor.mIsInsertionActionModeStartPending = false;
                    }
                    return flag;
                }
                boolean flag1;
                boolean flag2;
                boolean flag3;
                boolean flag4;
                InputMethodManager inputmethodmanager;
                if(i == 1 && (mEditor == null || mEditor.mIgnoreActionUpEvent ^ true))
                    flag1 = isFocused();
                else
                    flag1 = false;
                if(mMovement == null && !onCheckIsTextEditor() || !isEnabled() || !(mText instanceof Spannable) || mLayout == null)
                    break label0;
                flag2 = false;
                if(mMovement != null)
                    flag2 = mMovement.onTouchEvent(this, (Spannable)mText, motionevent);
                flag3 = isTextSelectable();
                flag4 = flag2;
                if(flag1)
                {
                    flag4 = flag2;
                    if(mLinksClickable)
                    {
                        flag4 = flag2;
                        if(mAutoLinkMask != 0)
                        {
                            flag4 = flag2;
                            if(flag3)
                            {
                                ClickableSpan aclickablespan[] = (ClickableSpan[])((Spannable)mText).getSpans(getSelectionStart(), getSelectionEnd(), android/text/style/ClickableSpan);
                                flag4 = flag2;
                                if(aclickablespan.length > 0)
                                {
                                    aclickablespan[0].onClick(this);
                                    flag4 = true;
                                }
                            }
                        }
                    }
                }
                flag2 = flag4;
                if(!flag1)
                    break label1;
                if(!isTextEditable())
                {
                    flag2 = flag4;
                    if(!flag3)
                        break label1;
                }
                inputmethodmanager = InputMethodManager.peekInstance();
                viewClicked(inputmethodmanager);
                if(isTextEditable() && mEditor.mShowSoftInputOnFocus && inputmethodmanager != null)
                    inputmethodmanager.showSoftInput(this, 0);
                mEditor.onTouchUpEvent(motionevent);
                flag2 = true;
            }
            if(flag2)
                return true;
        }
        return flag;
    }

    public boolean onTrackballEvent(MotionEvent motionevent)
    {
        if(mMovement != null && (mText instanceof Spannable) && mLayout != null && mMovement.onTrackballEvent(this, (Spannable)mText, motionevent))
            return true;
        else
            return super.onTrackballEvent(motionevent);
    }

    protected void onVisibilityChanged(View view, int i)
    {
        super.onVisibilityChanged(view, i);
        if(mEditor != null && i != 0)
        {
            mEditor.hideCursorAndSpanControllers();
            stopTextActionMode();
        }
    }

    public void onWindowFocusChanged(boolean flag)
    {
        super.onWindowFocusChanged(flag);
        if(mEditor != null)
            mEditor.onWindowFocusChanged(flag);
        startStopMarquee(flag);
    }

    public boolean performAccessibilityActionInternal(int i, Bundle bundle)
    {
        if(mEditor != null && mEditor.mProcessTextIntentActionsHandler.performAccessibilityAction(i))
            return true;
        switch(i)
        {
        default:
            return super.performAccessibilityActionInternal(i, bundle);

        case 16: // '\020'
            return performAccessibilityActionClick(bundle);

        case 16384: 
            return isFocused() && canCopy() && onTextContextMenuItem(0x1020021);

        case 32768: 
            return isFocused() && canPaste() && onTextContextMenuItem(0x1020022);

        case 65536: 
            return isFocused() && canCut() && onTextContextMenuItem(0x1020020);

        case 131072: 
            ensureIterableTextForAccessibilitySelectable();
            CharSequence charsequence = getIterableTextForAccessibility();
            if(charsequence == null)
                return false;
            int j;
            if(bundle != null)
                i = bundle.getInt("ACTION_ARGUMENT_SELECTION_START_INT", -1);
            else
                i = -1;
            if(bundle != null)
                j = bundle.getInt("ACTION_ARGUMENT_SELECTION_END_INT", -1);
            else
                j = -1;
            if(getSelectionStart() != i || getSelectionEnd() != j)
            {
                if(i == j && j == -1)
                {
                    Selection.removeSelection((Spannable)charsequence);
                    return true;
                }
                if(i >= 0 && i <= j && j <= charsequence.length())
                {
                    Selection.setSelection((Spannable)charsequence, i, j);
                    if(mEditor != null)
                        mEditor.startSelectionActionModeAsync(false);
                    return true;
                }
            }
            return false;

        case 256: 
        case 512: 
            ensureIterableTextForAccessibilitySelectable();
            return super.performAccessibilityActionInternal(i, bundle);

        case 268435456: 
            return isFocused() && canShare() && onTextContextMenuItem(0x1020035);

        case 2097152: 
            break;
        }
        if(!isEnabled() || mBufferType != BufferType.EDITABLE)
            return false;
        if(bundle != null)
            bundle = bundle.getCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE");
        else
            bundle = null;
        setText(bundle);
        if(mText != null)
        {
            i = mText.length();
            if(i > 0)
                Selection.setSelection((Spannable)mText, i);
        }
        return true;
    }

    public boolean performLongClick()
    {
        boolean flag = false;
        boolean flag1 = false;
        if(mEditor != null)
            mEditor.mIsBeingLongClicked = true;
        if(super.performLongClick())
        {
            flag = true;
            flag1 = true;
        }
        boolean flag2 = flag;
        if(mEditor != null)
        {
            flag2 = flag | mEditor.performLongClick(flag);
            mEditor.mIsBeingLongClicked = false;
        }
        if(flag2)
        {
            if(!flag1)
                performHapticFeedback(0);
            if(mEditor != null)
                mEditor.mDiscardNextActionUp = true;
        } else
        {
            MetricsLogger.action(mContext, 629, 0);
        }
        return flag2;
    }

    public void populateCharacterBounds(android.view.inputmethod.CursorAnchorInfo.Builder builder, int i, int j, float f, float f1)
    {
        int k = mLayout.getLineForOffset(i);
        int l = mLayout.getLineForOffset(j - 1);
        do
        {
            if(k > l)
                break;
            int i1 = mLayout.getLineStart(k);
            int k1 = mLayout.getLineEnd(k);
            int i2 = Math.max(i1, i);
            int j2 = Math.min(k1, j);
            boolean flag;
            float af[];
            float f2;
            float f3;
            int k2;
            if(mLayout.getParagraphDirection(k) == 1)
                flag = true;
            else
                flag = false;
            af = new float[j2 - i2];
            mLayout.getPaint().getTextWidths(mText, i2, j2, af);
            f2 = mLayout.getLineTop(k);
            f3 = mLayout.getLineBottom(k);
            k2 = i2;
            while(k2 < j2) 
            {
label0:
                {
                    float f4 = af[k2 - i2];
                    boolean flag1 = mLayout.isRtlCharAt(k2);
                    float f5 = mLayout.getPrimaryHorizontal(k2);
                    float f6 = mLayout.getSecondaryHorizontal(k2);
                    int j1;
                    int l1;
                    float f7;
                    boolean flag2;
                    boolean flag3;
                    if(flag)
                    {
                        if(flag1)
                        {
                            f5 = f6 - f4;
                        } else
                        {
                            f6 = f5;
                            f4 = f5 + f4;
                            f5 = f6;
                            f6 = f4;
                        }
                    } else
                    if(!flag1)
                    {
                        f5 = f6;
                        f6 += f4;
                    } else
                    {
                        f4 = f5 - f4;
                        f6 = f5;
                        f5 = f4;
                    }
                    f5 += f;
                    f4 = f6 + f;
                    f6 = f2 + f1;
                    f7 = f3 + f1;
                    flag2 = isPositionVisible(f5, f6);
                    flag3 = isPositionVisible(f4, f7);
                    j1 = 0;
                    if(flag2 || flag3)
                        j1 = 1;
                    if(flag2)
                    {
                        l1 = j1;
                        if(!(flag3 ^ true))
                            break label0;
                    }
                    l1 = j1 | 2;
                }
                j1 = l1;
                if(flag1)
                    j1 = l1 | 4;
                builder.addCharacterBounds(k2, f5, f6, f4, f7, j1);
                k2++;
            }
            k++;
        } while(true);
    }

    void removeAdjacentSuggestionSpans(int i)
    {
        if(!(mText instanceof Editable))
            return;
        Editable editable = (Editable)mText;
        SuggestionSpan asuggestionspan[] = (SuggestionSpan[])editable.getSpans(i, i, android/text/style/SuggestionSpan);
        int j = asuggestionspan.length;
        for(int k = 0; k < j; k++)
        {
            int l = editable.getSpanStart(asuggestionspan[k]);
            int i1 = editable.getSpanEnd(asuggestionspan[k]);
            if((i1 == i || l == i) && SpellChecker.haveWordBoundariesChanged(editable, i, i, l, i1))
                editable.removeSpan(asuggestionspan[k]);
        }

    }

    void removeMisspelledSpans(Spannable spannable)
    {
        SuggestionSpan asuggestionspan[] = (SuggestionSpan[])spannable.getSpans(0, spannable.length(), android/text/style/SuggestionSpan);
        for(int i = 0; i < asuggestionspan.length; i++)
        {
            int j = asuggestionspan[i].getFlags();
            if((j & 1) != 0 && (j & 2) != 0)
                spannable.removeSpan(asuggestionspan[i]);
        }

    }

    CharSequence removeSuggestionSpans(CharSequence charsequence)
    {
        CharSequence charsequence1 = charsequence;
        if(charsequence instanceof Spanned)
        {
            Spannable spannable;
            SuggestionSpan asuggestionspan[];
            int i;
            if(charsequence instanceof Spannable)
            {
                spannable = (Spannable)charsequence;
            } else
            {
                spannable = mSpannableFactory.newSpannable(charsequence);
                charsequence = spannable;
            }
            asuggestionspan = (SuggestionSpan[])spannable.getSpans(0, charsequence.length(), android/text/style/SuggestionSpan);
            i = 0;
            do
            {
                charsequence1 = charsequence;
                if(i >= asuggestionspan.length)
                    break;
                spannable.removeSpan(asuggestionspan[i]);
                i++;
            } while(true);
        }
        return charsequence1;
    }

    public void removeTextChangedListener(TextWatcher textwatcher)
    {
        if(mListeners != null)
        {
            int i = mListeners.indexOf(textwatcher);
            if(i >= 0)
                mListeners.remove(i);
        }
    }

    void replaceSelectionWithText(CharSequence charsequence)
    {
        ((Editable)mText).replace(getSelectionStart(), getSelectionEnd(), charsequence);
    }

    protected void replaceText_internal(int i, int j, CharSequence charsequence)
    {
        ((Editable)mText).replace(i, j, charsequence);
    }

    public void resetErrorChangedFlag()
    {
        if(mEditor != null)
            mEditor.mErrorWasChanged = false;
    }

    protected void resetResolvedDrawables()
    {
        super.resetResolvedDrawables();
        mLastLayoutDirection = -1;
    }

    boolean selectAllText()
    {
        if(mEditor != null)
            mEditor.hideFloatingToolbar(500);
        int i = mText.length();
        Selection.setSelection((Spannable)mText, 0, i);
        boolean flag;
        if(i > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void sendAccessibilityEventInternal(int i)
    {
        if(i == 32768 && mEditor != null)
            mEditor.mProcessTextIntentActionsHandler.initializeAccessibilityActions();
        if(i == 4096)
        {
            return;
        } else
        {
            super.sendAccessibilityEventInternal(i);
            return;
        }
    }

    void sendAccessibilityEventTypeViewTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(16);
        accessibilityevent.setFromIndex(i);
        accessibilityevent.setRemovedCount(j);
        accessibilityevent.setAddedCount(k);
        accessibilityevent.setBeforeText(charsequence);
        sendAccessibilityEventUnchecked(accessibilityevent);
    }

    void sendAfterTextChanged(Editable editable)
    {
        if(mListeners != null)
        {
            ArrayList arraylist = mListeners;
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
                ((TextWatcher)arraylist.get(j)).afterTextChanged(editable);

        }
        notifyAutoFillManagerAfterTextChangedIfNeeded();
        hideErrorIfUnchanged();
    }

    void sendOnTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        if(mListeners != null)
        {
            ArrayList arraylist = mListeners;
            int l = arraylist.size();
            for(int i1 = 0; i1 < l; i1++)
                ((TextWatcher)arraylist.get(i1)).onTextChanged(charsequence, i, j, k);

        }
        if(mEditor != null)
            mEditor.sendOnTextChanged(i, j, k);
    }

    public void setAccessibilitySelection(int i, int j)
    {
        if(getAccessibilitySelectionStart() == i && getAccessibilitySelectionEnd() == j)
            return;
        CharSequence charsequence = getIterableTextForAccessibility();
        if(Math.min(i, j) >= 0 && Math.max(i, j) <= charsequence.length())
            Selection.setSelection((Spannable)charsequence, i, j);
        else
            Selection.removeSelection((Spannable)charsequence);
        if(mEditor != null)
        {
            mEditor.hideCursorAndSpanControllers();
            mEditor.stopTextActionMode();
        }
    }

    public void setAllCaps(boolean flag)
    {
        if(flag)
            setTransformationMethod(new AllCapsTransformationMethod(getContext()));
        else
            setTransformationMethod(null);
    }

    public final void setAutoLinkMask(int i)
    {
        mAutoLinkMask = i;
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int j, int k, int l)
    {
        if(supportsAutoSizeText())
        {
            DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
            validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(l, i, displaymetrics), TypedValue.applyDimension(l, j, displaymetrics), TypedValue.applyDimension(l, k, displaymetrics));
            if(setupAutoSizeText())
            {
                autoSizeText();
                invalidate();
            }
        }
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int ai[], int i)
    {
        int j;
        if(!supportsAutoSizeText())
            break MISSING_BLOCK_LABEL_145;
        j = ai.length;
        if(j <= 0) goto _L2; else goto _L1
_L1:
        int ai1[] = new int[j];
        if(i != 0) goto _L4; else goto _L3
_L3:
        int ai2[] = Arrays.copyOf(ai, j);
_L6:
        mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(ai2);
        if(!setupAutoSizeUniformPresetSizesConfiguration())
            throw new IllegalArgumentException((new StringBuilder()).append("None of the preset sizes is valid: ").append(Arrays.toString(ai)).toString());
        break; /* Loop/switch isn't completed */
_L4:
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        int k = 0;
        do
        {
            ai2 = ai1;
            if(k >= j)
                break;
            ai1[k] = Math.round(TypedValue.applyDimension(i, ai[k], displaymetrics));
            k++;
        } while(true);
        if(true) goto _L6; else goto _L5
_L2:
        mHasPresetAutoSizeValues = false;
_L5:
        if(setupAutoSizeText())
        {
            autoSizeText();
            invalidate();
        }
    }

    public void setAutoSizeTextTypeWithDefaults(int i)
    {
        if(!supportsAutoSizeText()) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 0 1: default 32
    //                   0 60
    //                   1 65;
           goto _L3 _L4 _L5
_L3:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown auto-size text type: ").append(i).toString());
_L4:
        clearAutoSizeConfiguration();
_L2:
        return;
_L5:
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(2, 12F, displaymetrics), TypedValue.applyDimension(2, 112F, displaymetrics), 1.0F);
        if(setupAutoSizeText())
        {
            autoSizeText();
            invalidate();
        }
        if(true) goto _L2; else goto _L6
_L6:
    }

    public void setBreakStrategy(int i)
    {
        mBreakStrategy = i;
        if(mLayout != null)
        {
            nullLayouts();
            requestLayout();
            invalidate();
        }
    }

    public void setCompoundDrawablePadding(int i)
    {
        Drawables drawables = mDrawables;
        if(i == 0)
        {
            if(drawables != null)
                drawables.mDrawablePadding = i;
        } else
        {
            Drawables drawables1 = drawables;
            if(drawables == null)
            {
                drawables1 = new Drawables(getContext());
                mDrawables = drawables1;
            }
            drawables1.mDrawablePadding = i;
        }
        invalidate();
        requestLayout();
    }

    public void setCompoundDrawableTintList(ColorStateList colorstatelist)
    {
        if(mDrawables == null)
            mDrawables = new Drawables(getContext());
        mDrawables.mTintList = colorstatelist;
        mDrawables.mHasTint = true;
        applyCompoundDrawableTint();
    }

    public void setCompoundDrawableTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mDrawables == null)
            mDrawables = new Drawables(getContext());
        mDrawables.mTintMode = mode;
        mDrawables.mHasTintMode = true;
        applyCompoundDrawableTint();
    }

    public void setCompoundDrawables(Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
    {
        Object obj;
        obj = mDrawables;
        if(obj != null)
        {
            if(((Drawables) (obj)).mDrawableStart != null)
                ((Drawables) (obj)).mDrawableStart.setCallback(null);
            obj.mDrawableStart = null;
            if(((Drawables) (obj)).mDrawableEnd != null)
                ((Drawables) (obj)).mDrawableEnd.setCallback(null);
            obj.mDrawableEnd = null;
            obj.mDrawableHeightStart = 0;
            obj.mDrawableSizeStart = 0;
            obj.mDrawableHeightEnd = 0;
            obj.mDrawableSizeEnd = 0;
        }
        break MISSING_BLOCK_LABEL_81;
        boolean flag;
        Drawables drawables;
        if(drawable != null || drawable1 != null || drawable2 != null || drawable3 != null)
            flag = true;
        else
            flag = false;
        if(!flag)
        {
            drawables = ((Drawables) (obj));
            if(obj != null)
                if(!((Drawables) (obj)).hasMetadata())
                {
                    mDrawables = null;
                    drawables = ((Drawables) (obj));
                } else
                {
                    for(int i = ((Drawables) (obj)).mShowing.length - 1; i >= 0; i--)
                    {
                        if(((Drawables) (obj)).mShowing[i] != null)
                            ((Drawables) (obj)).mShowing[i].setCallback(null);
                        ((Drawables) (obj)).mShowing[i] = null;
                    }

                    obj.mDrawableHeightLeft = 0;
                    obj.mDrawableSizeLeft = 0;
                    obj.mDrawableHeightRight = 0;
                    obj.mDrawableSizeRight = 0;
                    obj.mDrawableWidthTop = 0;
                    obj.mDrawableSizeTop = 0;
                    obj.mDrawableWidthBottom = 0;
                    obj.mDrawableSizeBottom = 0;
                    drawables = ((Drawables) (obj));
                }
        } else
        {
            drawables = ((Drawables) (obj));
            if(obj == null)
            {
                drawables = new Drawables(getContext());
                mDrawables = drawables;
            }
            mDrawables.mOverride = false;
            if(drawables.mShowing[0] != drawable && drawables.mShowing[0] != null)
                drawables.mShowing[0].setCallback(null);
            drawables.mShowing[0] = drawable;
            if(drawables.mShowing[1] != drawable1 && drawables.mShowing[1] != null)
                drawables.mShowing[1].setCallback(null);
            drawables.mShowing[1] = drawable1;
            if(drawables.mShowing[2] != drawable2 && drawables.mShowing[2] != null)
                drawables.mShowing[2].setCallback(null);
            drawables.mShowing[2] = drawable2;
            if(drawables.mShowing[3] != drawable3 && drawables.mShowing[3] != null)
                drawables.mShowing[3].setCallback(null);
            drawables.mShowing[3] = drawable3;
            obj = drawables.mCompoundRect;
            int ai[] = getDrawableState();
            if(drawable != null)
            {
                drawable.setState(ai);
                drawable.copyBounds(((Rect) (obj)));
                drawable.setCallback(this);
                drawables.mDrawableSizeLeft = ((Rect) (obj)).width();
                drawables.mDrawableHeightLeft = ((Rect) (obj)).height();
            } else
            {
                drawables.mDrawableHeightLeft = 0;
                drawables.mDrawableSizeLeft = 0;
            }
            if(drawable2 != null)
            {
                drawable2.setState(ai);
                drawable2.copyBounds(((Rect) (obj)));
                drawable2.setCallback(this);
                drawables.mDrawableSizeRight = ((Rect) (obj)).width();
                drawables.mDrawableHeightRight = ((Rect) (obj)).height();
            } else
            {
                drawables.mDrawableHeightRight = 0;
                drawables.mDrawableSizeRight = 0;
            }
            if(drawable1 != null)
            {
                drawable1.setState(ai);
                drawable1.copyBounds(((Rect) (obj)));
                drawable1.setCallback(this);
                drawables.mDrawableSizeTop = ((Rect) (obj)).height();
                drawables.mDrawableWidthTop = ((Rect) (obj)).width();
            } else
            {
                drawables.mDrawableWidthTop = 0;
                drawables.mDrawableSizeTop = 0;
            }
            if(drawable3 != null)
            {
                drawable3.setState(ai);
                drawable3.copyBounds(((Rect) (obj)));
                drawable3.setCallback(this);
                drawables.mDrawableSizeBottom = ((Rect) (obj)).height();
                drawables.mDrawableWidthBottom = ((Rect) (obj)).width();
            } else
            {
                drawables.mDrawableWidthBottom = 0;
                drawables.mDrawableSizeBottom = 0;
            }
        }
        if(drawables != null)
        {
            drawables.mDrawableLeftInitial = drawable;
            drawables.mDrawableRightInitial = drawable2;
        }
        resetResolvedDrawables();
        resolveDrawables();
        applyCompoundDrawableTint();
        invalidate();
        requestLayout();
        return;
    }

    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
    {
        Drawables drawables;
        drawables = mDrawables;
        if(drawables != null)
        {
            if(drawables.mShowing[0] != null)
                drawables.mShowing[0].setCallback(null);
            Drawable adrawable[] = drawables.mShowing;
            drawables.mDrawableLeftInitial = null;
            adrawable[0] = null;
            if(drawables.mShowing[2] != null)
                drawables.mShowing[2].setCallback(null);
            adrawable = drawables.mShowing;
            drawables.mDrawableRightInitial = null;
            adrawable[2] = null;
            drawables.mDrawableHeightLeft = 0;
            drawables.mDrawableSizeLeft = 0;
            drawables.mDrawableHeightRight = 0;
            drawables.mDrawableSizeRight = 0;
        }
        break MISSING_BLOCK_LABEL_113;
        boolean flag;
        if(drawable != null || drawable1 != null || drawable2 != null)
            flag = true;
        else
        if(drawable3 != null)
            flag = true;
        else
            flag = false;
        if(!flag)
        {
            if(drawables != null)
                if(!drawables.hasMetadata())
                {
                    mDrawables = null;
                } else
                {
                    if(drawables.mDrawableStart != null)
                        drawables.mDrawableStart.setCallback(null);
                    drawables.mDrawableStart = null;
                    if(drawables.mShowing[1] != null)
                        drawables.mShowing[1].setCallback(null);
                    drawables.mShowing[1] = null;
                    if(drawables.mDrawableEnd != null)
                        drawables.mDrawableEnd.setCallback(null);
                    drawables.mDrawableEnd = null;
                    if(drawables.mShowing[3] != null)
                        drawables.mShowing[3].setCallback(null);
                    drawables.mShowing[3] = null;
                    drawables.mDrawableHeightStart = 0;
                    drawables.mDrawableSizeStart = 0;
                    drawables.mDrawableHeightEnd = 0;
                    drawables.mDrawableSizeEnd = 0;
                    drawables.mDrawableWidthTop = 0;
                    drawables.mDrawableSizeTop = 0;
                    drawables.mDrawableWidthBottom = 0;
                    drawables.mDrawableSizeBottom = 0;
                }
        } else
        {
            Drawables drawables1 = drawables;
            if(drawables == null)
            {
                drawables1 = new Drawables(getContext());
                mDrawables = drawables1;
            }
            mDrawables.mOverride = true;
            if(drawables1.mDrawableStart != drawable && drawables1.mDrawableStart != null)
                drawables1.mDrawableStart.setCallback(null);
            drawables1.mDrawableStart = drawable;
            if(drawables1.mShowing[1] != drawable1 && drawables1.mShowing[1] != null)
                drawables1.mShowing[1].setCallback(null);
            drawables1.mShowing[1] = drawable1;
            if(drawables1.mDrawableEnd != drawable2 && drawables1.mDrawableEnd != null)
                drawables1.mDrawableEnd.setCallback(null);
            drawables1.mDrawableEnd = drawable2;
            if(drawables1.mShowing[3] != drawable3 && drawables1.mShowing[3] != null)
                drawables1.mShowing[3].setCallback(null);
            drawables1.mShowing[3] = drawable3;
            Rect rect = drawables1.mCompoundRect;
            int ai[] = getDrawableState();
            if(drawable != null)
            {
                drawable.setState(ai);
                drawable.copyBounds(rect);
                drawable.setCallback(this);
                drawables1.mDrawableSizeStart = rect.width();
                drawables1.mDrawableHeightStart = rect.height();
            } else
            {
                drawables1.mDrawableHeightStart = 0;
                drawables1.mDrawableSizeStart = 0;
            }
            if(drawable2 != null)
            {
                drawable2.setState(ai);
                drawable2.copyBounds(rect);
                drawable2.setCallback(this);
                drawables1.mDrawableSizeEnd = rect.width();
                drawables1.mDrawableHeightEnd = rect.height();
            } else
            {
                drawables1.mDrawableHeightEnd = 0;
                drawables1.mDrawableSizeEnd = 0;
            }
            if(drawable1 != null)
            {
                drawable1.setState(ai);
                drawable1.copyBounds(rect);
                drawable1.setCallback(this);
                drawables1.mDrawableSizeTop = rect.height();
                drawables1.mDrawableWidthTop = rect.width();
            } else
            {
                drawables1.mDrawableWidthTop = 0;
                drawables1.mDrawableSizeTop = 0;
            }
            if(drawable3 != null)
            {
                drawable3.setState(ai);
                drawable3.copyBounds(rect);
                drawable3.setCallback(this);
                drawables1.mDrawableSizeBottom = rect.height();
                drawables1.mDrawableWidthBottom = rect.width();
            } else
            {
                drawables1.mDrawableWidthBottom = 0;
                drawables1.mDrawableSizeBottom = 0;
            }
        }
        resetResolvedDrawables();
        resolveDrawables();
        invalidate();
        requestLayout();
        return;
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int j, int k, int l)
    {
        Drawable drawable = null;
        Context context = getContext();
        Drawable drawable1;
        Drawable drawable2;
        Drawable drawable3;
        if(i != 0)
            drawable1 = context.getDrawable(i);
        else
            drawable1 = null;
        if(j != 0)
            drawable2 = context.getDrawable(j);
        else
            drawable2 = null;
        if(k != 0)
            drawable3 = context.getDrawable(k);
        else
            drawable3 = null;
        if(l != 0)
            drawable = context.getDrawable(l);
        setCompoundDrawablesRelativeWithIntrinsicBounds(drawable1, drawable2, drawable3, drawable);
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
    {
        if(drawable != null)
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        if(drawable2 != null)
            drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
        if(drawable1 != null)
            drawable1.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight());
        if(drawable3 != null)
            drawable3.setBounds(0, 0, drawable3.getIntrinsicWidth(), drawable3.getIntrinsicHeight());
        setCompoundDrawablesRelative(drawable, drawable1, drawable2, drawable3);
    }

    public void setCompoundDrawablesWithIntrinsicBounds(int i, int j, int k, int l)
    {
        Drawable drawable = null;
        Context context = getContext();
        Drawable drawable1;
        Drawable drawable2;
        Drawable drawable3;
        if(i != 0)
            drawable1 = context.getDrawable(i);
        else
            drawable1 = null;
        if(j != 0)
            drawable2 = context.getDrawable(j);
        else
            drawable2 = null;
        if(k != 0)
            drawable3 = context.getDrawable(k);
        else
            drawable3 = null;
        if(l != 0)
            drawable = context.getDrawable(l);
        setCompoundDrawablesWithIntrinsicBounds(drawable1, drawable2, drawable3, drawable);
    }

    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
    {
        if(drawable != null)
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        if(drawable2 != null)
            drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
        if(drawable1 != null)
            drawable1.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight());
        if(drawable3 != null)
            drawable3.setBounds(0, 0, drawable3.getIntrinsicWidth(), drawable3.getIntrinsicHeight());
        setCompoundDrawables(drawable, drawable1, drawable2, drawable3);
    }

    public void setCursorDrawableRes(int i)
    {
        mCursorDrawableRes = i;
        for(i = 0; i < mEditor.mCursorCount; i++)
            mEditor.mCursorDrawable[i] = null;

        mEditor.mCursorCount = 0;
    }

    protected void setCursorPosition_internal(int i, int j)
    {
        Selection.setSelection((Editable)mText, i, j);
    }

    public void setCursorVisible(boolean flag)
    {
        if(flag && mEditor == null)
            return;
        createEditorIfNeeded();
        if(mEditor.mCursorVisible != flag)
        {
            mEditor.mCursorVisible = flag;
            invalidate();
            mEditor.makeBlink();
            mEditor.prepareCursorControllers();
        }
    }

    public void setCustomInsertionActionModeCallback(android.view.ActionMode.Callback callback)
    {
        createEditorIfNeeded();
        mEditor.mCustomInsertionActionModeCallback = callback;
    }

    public void setCustomSelectionActionModeCallback(android.view.ActionMode.Callback callback)
    {
        createEditorIfNeeded();
        mEditor.mCustomSelectionActionModeCallback = callback;
    }

    public final void setEditableFactory(android.text.Editable.Factory factory)
    {
        mEditableFactory = factory;
        setText(mText);
    }

    public void setElegantTextHeight(boolean flag)
    {
        if(flag != mTextPaint.isElegantTextHeight())
        {
            mTextPaint.setElegantTextHeight(flag);
            if(mLayout != null)
            {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public void setEllipsize(android.text.TextUtils.TruncateAt truncateat)
    {
        if(mEllipsize != truncateat)
        {
            mEllipsize = truncateat;
            if(mLayout != null)
            {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public void setEms(int i)
    {
        mMinWidth = i;
        mMaxWidth = i;
        mMinWidthMode = 1;
        mMaxWidthMode = 1;
        requestLayout();
        invalidate();
    }

    public void setEnabled(boolean flag)
    {
        if(flag == isEnabled())
            return;
        if(!flag)
        {
            InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
            if(inputmethodmanager != null && inputmethodmanager.isActive(this))
                inputmethodmanager.hideSoftInputFromWindow(getWindowToken(), 0);
        }
        super.setEnabled(flag);
        if(flag)
        {
            InputMethodManager inputmethodmanager1 = InputMethodManager.peekInstance();
            if(inputmethodmanager1 != null)
                inputmethodmanager1.restartInput(this);
        }
        if(mEditor != null)
        {
            mEditor.invalidateTextDisplayList();
            mEditor.prepareCursorControllers();
            mEditor.makeBlink();
        }
    }

    public void setError(CharSequence charsequence)
    {
        if(charsequence == null)
        {
            setError(null, null);
        } else
        {
            Drawable drawable = getContext().getDrawable(0x1080503);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            setError(charsequence, drawable);
        }
    }

    public void setError(CharSequence charsequence, Drawable drawable)
    {
        createEditorIfNeeded();
        mEditor.setError(charsequence, drawable);
        notifyViewAccessibilityStateChangedIfNeeded(0);
    }

    public void setExtractedText(ExtractedText extractedtext)
    {
        Object obj = getEditableText();
        int i;
        int j;
        int k;
        int l;
        if(extractedtext.text != null)
            if(obj == null)
            {
                setText(extractedtext.text, BufferType.EDITABLE);
            } else
            {
                l = 0;
                j = ((Editable) (obj)).length();
                if(extractedtext.partialStartOffset >= 0)
                {
                    i = ((Editable) (obj)).length();
                    j = extractedtext.partialStartOffset;
                    k = j;
                    if(j > i)
                        k = i;
                    int i1 = extractedtext.partialEndOffset;
                    l = k;
                    j = i1;
                    if(i1 > i)
                    {
                        j = i;
                        l = k;
                    }
                }
                removeParcelableSpans(((Spannable) (obj)), l, j);
                if(TextUtils.equals(((Editable) (obj)).subSequence(l, j), extractedtext.text))
                {
                    if(extractedtext.text instanceof Spanned)
                        TextUtils.copySpansFrom((Spanned)extractedtext.text, 0, j - l, java/lang/Object, ((Spannable) (obj)), l);
                } else
                {
                    ((Editable) (obj)).replace(l, j, extractedtext.text);
                }
            }
        obj = (Spannable)getText();
        i = ((Spannable) (obj)).length();
        j = extractedtext.selectionStart;
        if(j < 0)
        {
            k = 0;
        } else
        {
            k = j;
            if(j > i)
                k = i;
        }
        l = extractedtext.selectionEnd;
        if(l < 0)
        {
            j = 0;
        } else
        {
            j = l;
            if(l > i)
                j = i;
        }
        Selection.setSelection(((Spannable) (obj)), k, j);
        if((extractedtext.flags & 2) != 0)
            MetaKeyKeyListener.startSelecting(this, ((Spannable) (obj)));
        else
            MetaKeyKeyListener.stopSelecting(this, ((Spannable) (obj)));
    }

    public void setExtracting(ExtractedTextRequest extractedtextrequest)
    {
        if(mEditor.mInputMethodState != null)
            mEditor.mInputMethodState.mExtractedTextRequest = extractedtextrequest;
        mEditor.hideCursorAndSpanControllers();
        stopTextActionMode();
        if(mEditor.mSelectionModifierCursorController != null)
            mEditor.mSelectionModifierCursorController.resetTouchOffsets();
    }

    public void setFilters(InputFilter ainputfilter[])
    {
        if(ainputfilter == null)
            throw new IllegalArgumentException();
        mFilters = ainputfilter;
        if(mText instanceof Editable)
            setFilters((Editable)mText, ainputfilter);
    }

    public void setFontFeatureSettings(String s)
    {
        if(s != mTextPaint.getFontFeatureSettings())
        {
            mTextPaint.setFontFeatureSettings(s);
            if(mLayout != null)
            {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public boolean setFontVariationSettings(String s)
    {
        String s1 = mTextPaint.getFontVariationSettings();
        if(s == s1 || s != null && s.equals(s1))
            return true;
        boolean flag = mTextPaint.setFontVariationSettings(s);
        if(flag && mLayout != null)
        {
            nullLayouts();
            requestLayout();
            invalidate();
        }
        return flag;
    }

    protected boolean setFrame(int i, int j, int k, int l)
    {
        boolean flag = super.setFrame(i, j, k, l);
        if(mEditor != null)
            mEditor.setFrame();
        restartMarqueeIfNeeded();
        return flag;
    }

    public void setFreezesText(boolean flag)
    {
        mFreezesText = flag;
    }

    public void setGravity(int i)
    {
        int j = i;
        if((i & 0x800007) == 0)
            j = i | 0x800003;
        i = j;
        if((j & 0x70) == 0)
            i = j | 0x30;
        j = 0;
        if((i & 0x800007) != (mGravity & 0x800007))
            j = 1;
        if(i != mGravity)
            invalidate();
        mGravity = i;
        if(mLayout != null && j)
        {
            int k = mLayout.getWidth();
            if(mHintLayout == null)
                i = 0;
            else
                i = mHintLayout.getWidth();
            makeNewLayout(k, i, UNKNOWN_BORING, UNKNOWN_BORING, mRight - mLeft - getCompoundPaddingLeft() - getCompoundPaddingRight(), true);
        }
    }

    public void setHeight(int i)
    {
        mMinimum = i;
        mMaximum = i;
        mMinMode = 2;
        mMaxMode = 2;
        requestLayout();
        invalidate();
    }

    public void setHighlightColor(int i)
    {
        if(mHighlightColor != i)
        {
            mHighlightColor = i;
            invalidate();
        }
    }

    public final void setHint(int i)
    {
        setHint(getContext().getResources().getText(i));
    }

    public final void setHint(CharSequence charsequence)
    {
        mHint = TextUtils.stringOrSpannedString(charsequence);
        if(mLayout != null)
            checkForRelayout();
        if(mText.length() == 0)
            invalidate();
        if(mEditor != null && mText.length() == 0 && mHint != null)
            mEditor.invalidateTextDisplayList();
    }

    public final void setHintTextColor(int i)
    {
        mHintTextColor = ColorStateList.valueOf(i);
        updateTextColors();
    }

    public final void setHintTextColor(ColorStateList colorstatelist)
    {
        mHintTextColor = colorstatelist;
        updateTextColors();
    }

    public void setHorizontallyScrolling(boolean flag)
    {
        if(mHorizontallyScrolling != flag)
        {
            mHorizontallyScrolling = flag;
            if(mLayout != null)
            {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public void setHyphenationFrequency(int i)
    {
        mHyphenationFrequency = i;
        if(mLayout != null)
        {
            nullLayouts();
            requestLayout();
            invalidate();
        }
    }

    public void setImeActionLabel(CharSequence charsequence, int i)
    {
        createEditorIfNeeded();
        mEditor.createInputContentTypeIfNeeded();
        mEditor.mInputContentType.imeActionLabel = charsequence;
        mEditor.mInputContentType.imeActionId = i;
    }

    public void setImeHintLocales(LocaleList localelist)
    {
        Object obj = null;
        createEditorIfNeeded();
        mEditor.createInputContentTypeIfNeeded();
        mEditor.mInputContentType.imeHintLocales = localelist;
        if(mUseInternationalizedInput)
        {
            if(localelist == null)
                localelist = obj;
            else
                localelist = localelist.get(0);
            changeListenerLocaleTo(localelist);
        }
    }

    public void setImeOptions(int i)
    {
        createEditorIfNeeded();
        mEditor.createInputContentTypeIfNeeded();
        mEditor.mInputContentType.imeOptions = i;
    }

    public void setIncludeFontPadding(boolean flag)
    {
        if(mIncludePad != flag)
        {
            mIncludePad = flag;
            if(mLayout != null)
            {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public void setInputExtras(int i)
        throws XmlPullParserException, IOException
    {
        createEditorIfNeeded();
        android.content.res.XmlResourceParser xmlresourceparser = getResources().getXml(i);
        mEditor.createInputContentTypeIfNeeded();
        mEditor.mInputContentType.extras = new Bundle();
        getResources().parseBundleExtras(xmlresourceparser, mEditor.mInputContentType.extras);
    }

    public void setInputType(int i)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        flag = isPasswordInputType(getInputType());
        flag1 = isVisiblePasswordInputType(getInputType());
        setInputType(i, false);
        flag2 = isPasswordInputType(i);
        flag3 = isVisiblePasswordInputType(i);
        flag4 = false;
        flag5 = false;
        if(!flag2) goto _L2; else goto _L1
_L1:
        setTransformationMethod(PasswordTransformationMethod.getInstance());
        setTypefaceFromAttrs(null, null, 3, 0);
        flag4 = flag5;
_L4:
        flag3 = isMultilineInputType(i) ^ true;
        if(mSingleLine != flag3 || flag4)
            applySingleLine(flag3, flag2 ^ true, true);
        if(!isSuggestionsEnabled())
            mText = removeSuggestionSpans(mText);
        InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
        if(inputmethodmanager != null)
            inputmethodmanager.restartInput(this);
        return;
_L2:
        if(flag3)
        {
            if(mTransformation == PasswordTransformationMethod.getInstance())
                flag4 = true;
            setTypefaceFromAttrs(null, null, 3, 0);
            continue; /* Loop/switch isn't completed */
        }
        if(!flag)
        {
            flag4 = flag5;
            if(!flag1)
                continue; /* Loop/switch isn't completed */
        }
        setTypefaceFromAttrs(null, null, -1, -1);
        flag4 = flag5;
        if(mTransformation == PasswordTransformationMethod.getInstance())
            flag4 = true;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setJustificationMode(int i)
    {
        mJustificationMode = i;
        if(mLayout != null)
        {
            nullLayouts();
            requestLayout();
            invalidate();
        }
    }

    public void setKeyListener(KeyListener keylistener)
    {
        mListenerChanged = true;
        setKeyListenerOnly(keylistener);
        fixFocusableAndClickableSettings();
        if(keylistener == null) goto _L2; else goto _L1
_L1:
        createEditorIfNeeded();
        setInputTypeFromEditor();
_L4:
        keylistener = InputMethodManager.peekInstance();
        if(keylistener != null)
            keylistener.restartInput(this);
        return;
_L2:
        if(mEditor != null)
            mEditor.mInputType = 0;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setLetterSpacing(float f)
    {
        if(f != mTextPaint.getLetterSpacing())
        {
            mTextPaint.setLetterSpacing(f);
            if(mLayout != null)
            {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public void setLineSpacing(float f, float f1)
    {
        if(mSpacingAdd != f || mSpacingMult != f1)
        {
            mSpacingAdd = f;
            mSpacingMult = f1;
            if(mLayout != null)
            {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public void setLines(int i)
    {
        mMinimum = i;
        mMaximum = i;
        mMinMode = 1;
        mMaxMode = 1;
        requestLayout();
        invalidate();
    }

    public final void setLinkTextColor(int i)
    {
        mLinkTextColor = ColorStateList.valueOf(i);
        updateTextColors();
    }

    public final void setLinkTextColor(ColorStateList colorstatelist)
    {
        mLinkTextColor = colorstatelist;
        updateTextColors();
    }

    public final void setLinksClickable(boolean flag)
    {
        mLinksClickable = flag;
    }

    public void setMarqueeRepeatLimit(int i)
    {
        mMarqueeRepeatLimit = i;
    }

    public void setMaxEms(int i)
    {
        mMaxWidth = i;
        mMaxWidthMode = 1;
        requestLayout();
        invalidate();
    }

    public void setMaxHeight(int i)
    {
        mMaximum = i;
        mMaxMode = 2;
        requestLayout();
        invalidate();
    }

    public void setMaxLines(int i)
    {
        mMaximum = i;
        mMaxMode = 1;
        requestLayout();
        invalidate();
    }

    public void setMaxWidth(int i)
    {
        mMaxWidth = i;
        mMaxWidthMode = 2;
        requestLayout();
        invalidate();
    }

    public void setMinEms(int i)
    {
        mMinWidth = i;
        mMinWidthMode = 1;
        requestLayout();
        invalidate();
    }

    public void setMinHeight(int i)
    {
        mMinimum = i;
        mMinMode = 2;
        requestLayout();
        invalidate();
    }

    public void setMinLines(int i)
    {
        mMinimum = i;
        mMinMode = 1;
        requestLayout();
        invalidate();
    }

    public void setMinWidth(int i)
    {
        mMinWidth = i;
        mMinWidthMode = 2;
        requestLayout();
        invalidate();
    }

    public final void setMovementMethod(MovementMethod movementmethod)
    {
        if(mMovement != movementmethod)
        {
            mMovement = movementmethod;
            if(movementmethod != null && (mText instanceof Spannable) ^ true)
                setText(mText);
            fixFocusableAndClickableSettings();
            if(mEditor != null)
                mEditor.prepareCursorControllers();
        }
    }

    public void setOnEditorActionListener(OnEditorActionListener oneditoractionlistener)
    {
        createEditorIfNeeded();
        mEditor.createInputContentTypeIfNeeded();
        mEditor.mInputContentType.onEditorActionListener = oneditoractionlistener;
    }

    public void setPadding(int i, int j, int k, int l)
    {
        if(i != mPaddingLeft || k != mPaddingRight || j != mPaddingTop || l != mPaddingBottom)
            nullLayouts();
        super.setPadding(i, j, k, l);
        invalidate();
    }

    public void setPaddingRelative(int i, int j, int k, int l)
    {
        if(i != getPaddingStart() || k != getPaddingEnd() || j != mPaddingTop || l != mPaddingBottom)
            nullLayouts();
        super.setPaddingRelative(i, j, k, l);
        invalidate();
    }

    public void setPaintFlags(int i)
    {
        if(mTextPaint.getFlags() != i)
        {
            mTextPaint.setFlags(i);
            if(mLayout != null)
            {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public void setPrivateImeOptions(String s)
    {
        createEditorIfNeeded();
        mEditor.createInputContentTypeIfNeeded();
        mEditor.mInputContentType.privateImeOptions = s;
    }

    public void setRawInputType(int i)
    {
        if(i == 0 && mEditor == null)
        {
            return;
        } else
        {
            createEditorIfNeeded();
            mEditor.mInputType = i;
            return;
        }
    }

    public void setScroller(Scroller scroller)
    {
        mScroller = scroller;
    }

    public void setSelectAllOnFocus(boolean flag)
    {
        createEditorIfNeeded();
        mEditor.mSelectAllOnFocus = flag;
        if(flag && (mText instanceof Spannable) ^ true)
            setText(mText, BufferType.SPANNABLE);
    }

    public void setSelected(boolean flag)
    {
        boolean flag1 = isSelected();
        super.setSelected(flag);
        if(flag != flag1 && mEllipsize == android.text.TextUtils.TruncateAt.MARQUEE)
            if(flag)
                startMarquee();
            else
                stopMarquee();
    }

    public void setShadowLayer(float f, float f1, float f2, int i)
    {
        mTextPaint.setShadowLayer(f, f1, f2, i);
        mShadowRadius = f;
        mShadowDx = f1;
        mShadowDy = f2;
        mShadowColor = i;
        if(mEditor != null)
        {
            mEditor.invalidateTextDisplayList();
            mEditor.invalidateHandlesAndActionMode();
        }
        invalidate();
    }

    public final void setShowSoftInputOnFocus(boolean flag)
    {
        createEditorIfNeeded();
        mEditor.mShowSoftInputOnFocus = flag;
    }

    public void setSingleLine()
    {
        setSingleLine(true);
    }

    public void setSingleLine(boolean flag)
    {
        setInputTypeSingleLine(flag);
        applySingleLine(flag, true, true);
    }

    protected void setSpan_internal(Object obj, int i, int j, int k)
    {
        ((Editable)mText).setSpan(obj, i, j, k);
    }

    public final void setSpannableFactory(android.text.Spannable.Factory factory)
    {
        mSpannableFactory = factory;
        setText(mText);
    }

    public final void setText(int i)
    {
        setText(getContext().getResources().getText(i));
        mTextFromResource = true;
    }

    public final void setText(int i, BufferType buffertype)
    {
        setText(getContext().getResources().getText(i), buffertype);
        mTextFromResource = true;
    }

    public final void setText(CharSequence charsequence)
    {
        setText(charsequence, mBufferType);
    }

    public void setText(CharSequence charsequence, BufferType buffertype)
    {
        setText(charsequence, buffertype, true, 0);
        if(mCharWrapper != null)
            CharWrapper._2D_set0(mCharWrapper, null);
    }

    public final void setText(char ac[], int i, int j)
    {
        int k;
        for(k = 0; i < 0 || j < 0 || i + j > ac.length;)
            throw new IndexOutOfBoundsException((new StringBuilder()).append(i).append(", ").append(j).toString());

        if(mText != null)
        {
            k = mText.length();
            sendBeforeTextChanged(mText, 0, k, j);
        } else
        {
            sendBeforeTextChanged("", 0, 0, j);
        }
        if(mCharWrapper == null)
            mCharWrapper = new CharWrapper(ac, i, j);
        else
            mCharWrapper.set(ac, i, j);
        setText(((CharSequence) (mCharWrapper)), mBufferType, false, k);
    }

    public void setTextAppearance(int i)
    {
        setTextAppearance(mContext, i);
    }

    public void setTextAppearance(Context context, int i)
    {
        TypedArray typedarray;
        Object obj;
        Object obj1;
        Object obj2;
        typedarray = context.obtainStyledAttributes(i, android.R.styleable.TextAppearance);
        i = typedarray.getColor(4, 0);
        if(i != 0)
            setHighlightColor(i);
        obj = typedarray.getColorStateList(3);
        if(obj != null)
            setTextColor(((ColorStateList) (obj)));
        i = typedarray.getDimensionPixelSize(0, 0);
        if(i != 0)
            setRawTextSize(i, true);
        obj = typedarray.getColorStateList(5);
        if(obj != null)
            setHintTextColor(((ColorStateList) (obj)));
        obj = typedarray.getColorStateList(6);
        if(obj != null)
            setLinkTextColor(((ColorStateList) (obj)));
        obj1 = null;
        obj2 = null;
        obj = obj1;
        if(context.isRestricted())
            break MISSING_BLOCK_LABEL_134;
        obj = obj1;
        if(!context.canLoadUnsafeResources())
            break MISSING_BLOCK_LABEL_134;
        try
        {
            obj = typedarray.getFont(12);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            obj = obj1;
        }
        context = obj2;
        if(obj == null)
            context = typedarray.getString(12);
        setTypefaceFromAttrs(((Typeface) (obj)), context, typedarray.getInt(1, -1), typedarray.getInt(2, -1));
        i = typedarray.getInt(7, 0);
        if(i != 0)
        {
            float f = typedarray.getFloat(8, 0.0F);
            float f1 = typedarray.getFloat(9, 0.0F);
            setShadowLayer(typedarray.getFloat(10, 0.0F), f, f1, i);
        }
        if(typedarray.getBoolean(11, false))
            setTransformationMethod(new AllCapsTransformationMethod(getContext()));
        if(typedarray.hasValue(13))
            setElegantTextHeight(typedarray.getBoolean(13, false));
        if(typedarray.hasValue(14))
            setLetterSpacing(typedarray.getFloat(14, 0.0F));
        if(typedarray.hasValue(15))
            setFontFeatureSettings(typedarray.getString(15));
        typedarray.recycle();
        return;
    }

    public void setTextClassifier(TextClassifier textclassifier)
    {
        mTextClassifier = textclassifier;
    }

    public void setTextColor(int i)
    {
        mTextColor = ColorStateList.valueOf(i);
        updateTextColors();
    }

    public void setTextColor(ColorStateList colorstatelist)
    {
        if(colorstatelist == null)
        {
            throw new NullPointerException();
        } else
        {
            mTextColor = colorstatelist;
            updateTextColors();
            return;
        }
    }

    public void setTextIsSelectable(boolean flag)
    {
        Object obj = null;
        if(!flag && mEditor == null)
            return;
        createEditorIfNeeded();
        if(mEditor.mTextIsSelectable == flag)
            return;
        mEditor.mTextIsSelectable = flag;
        setFocusableInTouchMode(flag);
        setFocusable(16);
        setClickable(flag);
        setLongClickable(flag);
        if(flag)
            obj = ArrowKeyMovementMethod.getInstance();
        setMovementMethod(((MovementMethod) (obj)));
        CharSequence charsequence = mText;
        if(flag)
            obj = BufferType.SPANNABLE;
        else
            obj = BufferType.NORMAL;
        setText(charsequence, ((BufferType) (obj)));
        mEditor.prepareCursorControllers();
    }

    public final void setTextKeepState(CharSequence charsequence)
    {
        setTextKeepState(charsequence, mBufferType);
    }

    public final void setTextKeepState(CharSequence charsequence, BufferType buffertype)
    {
        int i = getSelectionStart();
        int j = getSelectionEnd();
        int k = charsequence.length();
        setText(charsequence, buffertype);
        if((i >= 0 || j >= 0) && (mText instanceof Spannable))
            Selection.setSelection((Spannable)mText, Math.max(0, Math.min(i, k)), Math.max(0, Math.min(j, k)));
    }

    public void setTextLocale(Locale locale)
    {
        mLocalesChanged = true;
        mTextPaint.setTextLocale(locale);
        if(mLayout != null)
        {
            nullLayouts();
            requestLayout();
            invalidate();
        }
    }

    public void setTextLocales(LocaleList localelist)
    {
        mLocalesChanged = true;
        mTextPaint.setTextLocales(localelist);
        if(mLayout != null)
        {
            nullLayouts();
            requestLayout();
            invalidate();
        }
    }

    public void setTextScaleX(float f)
    {
        if(f != mTextPaint.getTextScaleX())
        {
            mUserSetTextScaleX = true;
            mTextPaint.setTextScaleX(f);
            if(mLayout != null)
            {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public void setTextSize(float f)
    {
        setTextSize(2, f);
    }

    public void setTextSize(int i, float f)
    {
        if(!isAutoSizeEnabled())
            setTextSizeInternal(i, f, true);
    }

    public final void setTransformationMethod(TransformationMethod transformationmethod)
    {
        if(transformationmethod == mTransformation)
            return;
        if(mTransformation != null && (mText instanceof Spannable))
            ((Spannable)mText).removeSpan(mTransformation);
        mTransformation = transformationmethod;
        if(transformationmethod instanceof TransformationMethod2)
        {
            transformationmethod = (TransformationMethod2)transformationmethod;
            boolean flag;
            if(!isTextSelectable())
                flag = (mText instanceof Editable) ^ true;
            else
                flag = false;
            mAllowTransformationLengthChange = flag;
            transformationmethod.setLengthChangesAllowed(mAllowTransformationLengthChange);
        } else
        {
            mAllowTransformationLengthChange = false;
        }
        setText(mText);
        if(hasPasswordTransformationMethod())
            notifyViewAccessibilityStateChangedIfNeeded(0);
        mTextDir = getTextDirectionHeuristic();
    }

    public void setTypeface(Typeface typeface)
    {
        typeface = TypefaceUtils.replaceTypeface(getContext(), typeface);
        if(mTextPaint.getTypeface() != typeface)
        {
            mTextPaint.setTypeface(typeface);
            if(mLayout != null)
            {
                nullLayouts();
                requestLayout();
                invalidate();
            }
        }
    }

    public void setTypeface(Typeface typeface, int i)
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
            setTypeface(typeface);
            typeface = getTypeface();
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
            setTypeface(typeface);
        }
    }

    public final void setUndoManager(UndoManager undomanager, String s)
    {
        throw new UnsupportedOperationException("not implemented");
    }

    public void setWidth(int i)
    {
        mMinWidth = i;
        mMaxWidth = i;
        mMinWidthMode = 2;
        mMaxWidthMode = 2;
        requestLayout();
        invalidate();
    }

    public boolean showContextMenu()
    {
        if(mEditor != null)
            mEditor.setContextMenuAnchor((0.0F / 0.0F), (0.0F / 0.0F));
        return super.showContextMenu();
    }

    public boolean showContextMenu(float f, float f1)
    {
        if(mEditor != null)
            mEditor.setContextMenuAnchor(f, f1);
        return super.showContextMenu(f, f1);
    }

    void spanChange(Spanned spanned, Object obj, int i, int j, int k, int l)
    {
        Editor.InputMethodState inputmethodstate;
label0:
        {
            int i1 = 0;
            byte byte0 = -1;
            int j1 = -1;
            boolean flag;
            boolean flag1;
            int k1;
            if(mEditor == null)
                inputmethodstate = null;
            else
                inputmethodstate = mEditor.mInputMethodState;
            if(obj != Selection.SELECTION_END)
                break label0;
            flag1 = true;
            k1 = j;
            if(i < 0)
            {
                j1 = k1;
                i1 = ((flag1) ? 1 : 0);
                if(j < 0)
                    break label0;
            }
            invalidateCursor(Selection.getSelectionStart(spanned), i, j);
            checkForResize();
            registerForPreDraw();
            j1 = k1;
            i1 = ((flag1) ? 1 : 0);
            if(mEditor != null)
            {
                mEditor.makeBlink();
                i1 = ((flag1) ? 1 : 0);
                j1 = k1;
            }
        }
label1:
        {
            k1 = byte0;
            flag = i1;
            if(obj != Selection.SELECTION_START)
                break label1;
            flag1 = true;
            i1 = j;
            if(i < 0)
            {
                k1 = i1;
                flag = flag1;
                if(j < 0)
                    break label1;
            }
            invalidateCursor(Selection.getSelectionEnd(spanned), i, j);
            flag = flag1;
            k1 = i1;
        }
        if(flag)
        {
            mHighlightPathBogus = true;
            if(mEditor != null && isFocused() ^ true)
                mEditor.mSelectionMoved = true;
            if((spanned.getSpanFlags(obj) & 0x200) == 0)
            {
                i1 = k1;
                if(k1 < 0)
                    i1 = Selection.getSelectionStart(spanned);
                k1 = j1;
                if(j1 < 0)
                    k1 = Selection.getSelectionEnd(spanned);
                if(mEditor != null)
                {
                    mEditor.refreshTextActionMode();
                    if(!hasSelection() && mEditor.getTextActionMode() == null && hasTransientState())
                        setHasTransientState(false);
                }
                onSelectionChanged(i1, k1);
            }
        }
        if((obj instanceof UpdateAppearance) || (obj instanceof ParagraphStyle) || (obj instanceof CharacterStyle))
        {
            if(inputmethodstate == null || inputmethodstate.mBatchEditNesting == 0)
            {
                invalidate();
                mHighlightPathBogus = true;
                checkForResize();
            } else
            {
                inputmethodstate.mContentChanged = true;
            }
            if(mEditor != null)
            {
                if(i >= 0)
                    mEditor.invalidateTextDisplayList(mLayout, i, k);
                if(j >= 0)
                    mEditor.invalidateTextDisplayList(mLayout, j, l);
                mEditor.invalidateHandlesAndActionMode();
            }
        }
        if(MetaKeyKeyListener.isMetaTracker(spanned, obj))
        {
            mHighlightPathBogus = true;
            if(inputmethodstate != null && MetaKeyKeyListener.isSelectingMetaTracker(spanned, obj))
                inputmethodstate.mSelectionModeChanged = true;
            if(Selection.getSelectionStart(spanned) >= 0)
                if(inputmethodstate == null || inputmethodstate.mBatchEditNesting == 0)
                    invalidateCursor();
                else
                    inputmethodstate.mCursorChanged = true;
        }
        if((obj instanceof ParcelableSpan) && inputmethodstate != null && inputmethodstate.mExtractedTextRequest != null)
            if(inputmethodstate.mBatchEditNesting != 0)
            {
                if(i >= 0)
                {
                    if(inputmethodstate.mChangedStart > i)
                        inputmethodstate.mChangedStart = i;
                    if(inputmethodstate.mChangedStart > k)
                        inputmethodstate.mChangedStart = k;
                }
                if(j >= 0)
                {
                    if(inputmethodstate.mChangedStart > j)
                        inputmethodstate.mChangedStart = j;
                    if(inputmethodstate.mChangedStart > l)
                        inputmethodstate.mChangedStart = l;
                }
            } else
            {
                inputmethodstate.mContentChanged = true;
            }
        if(mEditor != null && mEditor.mSpellChecker != null && j < 0 && (obj instanceof SpellCheckSpan))
            mEditor.mSpellChecker.onSpellCheckSpanRemoved((SpellCheckSpan)obj);
    }

    protected void stopTextActionMode()
    {
        if(mEditor != null)
            mEditor.stopTextActionMode();
    }

    protected boolean supportsAutoSizeText()
    {
        return true;
    }

    boolean textCanBeSelected()
    {
        boolean flag = false;
        if(mMovement == null || mMovement.canSelectArbitrarily() ^ true)
            return false;
        boolean flag1;
        if(!isTextEditable())
        {
            flag1 = flag;
            if(isTextSelectable())
            {
                flag1 = flag;
                if(mText instanceof Spannable)
                    flag1 = isEnabled();
            }
        } else
        {
            flag1 = true;
        }
        return flag1;
    }

    void updateAfterEdit()
    {
        invalidate();
        int i = getSelectionStart();
        if(i >= 0 || (mGravity & 0x70) == 80)
            registerForPreDraw();
        checkForResize();
        if(i >= 0)
        {
            mHighlightPathBogus = true;
            if(mEditor != null)
                mEditor.makeBlink();
            bringPointIntoView(i);
        }
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        boolean flag = super.verifyDrawable(drawable);
        if(!flag && mDrawables != null)
        {
            Drawable adrawable[] = mDrawables.mShowing;
            int i = 0;
            for(int j = adrawable.length; i < j; i++)
                if(drawable == adrawable[i])
                    return true;

        }
        return flag;
    }

    protected void viewClicked(InputMethodManager inputmethodmanager)
    {
        if(inputmethodmanager != null)
            inputmethodmanager.viewClicked(this);
    }

    int viewportToContentHorizontalOffset()
    {
        return getCompoundPaddingLeft() - mScrollX;
    }

    int viewportToContentVerticalOffset()
    {
        int i = getExtendedPaddingTop() - mScrollY;
        int j = i;
        if((mGravity & 0x70) != 48)
            j = i + getVerticalOffset(false);
        return j;
    }

    private static final int _2D_android_2D_text_2D_Layout$AlignmentSwitchesValues[];
    static final int ACCESSIBILITY_ACTION_PROCESS_TEXT_START_ID = 0x10000100;
    private static final int ACCESSIBILITY_ACTION_SHARE = 0x10000000;
    private static final int ANIMATED_SCROLL_GAP = 250;
    public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;
    public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;
    private static final int CHANGE_WATCHER_PRIORITY = 100;
    static final boolean DEBUG_AUTOFILL = false;
    static final boolean DEBUG_EXTRACT = false;
    private static final int DECIMAL = 4;
    private static final int DEFAULT_AUTO_SIZE_GRANULARITY_IN_PX = 1;
    private static final int DEFAULT_AUTO_SIZE_MAX_TEXT_SIZE_IN_SP = 112;
    private static final int DEFAULT_AUTO_SIZE_MIN_TEXT_SIZE_IN_SP = 12;
    private static final int DEVICE_PROVISIONED_NO = 1;
    private static final int DEVICE_PROVISIONED_UNKNOWN = 0;
    private static final int DEVICE_PROVISIONED_YES = 2;
    private static final Spanned EMPTY_SPANNED = new SpannedString("");
    private static final int EMS = 1;
    private static final int FLOATING_TOOLBAR_SELECT_ALL_REFRESH_DELAY = 500;
    static final int ID_ASSIST = 0x1020041;
    static final int ID_AUTOFILL = 0x1020043;
    static final int ID_COPY = 0x1020021;
    static final int ID_CUT = 0x1020020;
    static final int ID_PASTE = 0x1020022;
    static final int ID_PASTE_AS_PLAIN_TEXT = 0x1020031;
    static final int ID_REDO = 0x1020033;
    static final int ID_REPLACE = 0x1020034;
    static final int ID_SELECT_ALL = 0x102001f;
    static final int ID_SHARE = 0x1020035;
    static final int ID_UNDO = 0x1020032;
    private static final int KEY_DOWN_HANDLED_BY_KEY_LISTENER = 1;
    private static final int KEY_DOWN_HANDLED_BY_MOVEMENT_METHOD = 2;
    private static final int KEY_EVENT_HANDLED = -1;
    private static final int KEY_EVENT_NOT_HANDLED = 0;
    private static final int LINES = 1;
    static final String LOG_TAG = "TextView";
    private static final int MARQUEE_FADE_NORMAL = 0;
    private static final int MARQUEE_FADE_SWITCH_SHOW_ELLIPSIS = 1;
    private static final int MARQUEE_FADE_SWITCH_SHOW_FADE = 2;
    private static final int MONOSPACE = 3;
    private static final int MULTILINE_STATE_SET[] = {
        0x101034d
    };
    private static final InputFilter NO_FILTERS[] = new InputFilter[0];
    private static final int PIXELS = 2;
    static final int PROCESS_TEXT_REQUEST_CODE = 100;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int SIGNED = 2;
    private static final float TEMP_POSITION[] = new float[2];
    private static final RectF TEMP_RECTF = new RectF();
    private static final android.text.BoringLayout.Metrics UNKNOWN_BORING = new android.text.BoringLayout.Metrics();
    private static final float UNSET_AUTO_SIZE_UNIFORM_CONFIGURATION_VALUE = -1F;
    static final int VERY_WIDE = 0x100000;
    static long sLastCutCopyOrTextChangedTime;
    private boolean mAllowTransformationLengthChange;
    private int mAutoLinkMask;
    private float mAutoSizeMaxTextSizeInPx;
    private float mAutoSizeMinTextSizeInPx;
    private float mAutoSizeStepGranularityInPx;
    private int mAutoSizeTextSizesInPx[];
    private int mAutoSizeTextType;
    private android.text.BoringLayout.Metrics mBoring;
    private int mBreakStrategy;
    private BufferType mBufferType;
    private ChangeWatcher mChangeWatcher;
    private CharWrapper mCharWrapper;
    private int mCurHintTextColor;
    private int mCurTextColor;
    private volatile Locale mCurrentSpellCheckerLocaleCache;
    int mCursorDrawableRes;
    private int mDeferScroll;
    private int mDesiredHeightAtMeasure;
    private int mDeviceProvisionedState;
    Drawables mDrawables;
    private android.text.Editable.Factory mEditableFactory;
    private Editor mEditor;
    private android.text.TextUtils.TruncateAt mEllipsize;
    private InputFilter mFilters[];
    private boolean mFreezesText;
    private int mGravity;
    private boolean mHasPresetAutoSizeValues;
    int mHighlightColor;
    private final Paint mHighlightPaint;
    private Path mHighlightPath;
    private boolean mHighlightPathBogus;
    private CharSequence mHint;
    private android.text.BoringLayout.Metrics mHintBoring;
    private Layout mHintLayout;
    private ColorStateList mHintTextColor;
    private boolean mHorizontallyScrolling;
    private int mHyphenationFrequency;
    private boolean mIncludePad;
    private int mJustificationMode;
    private int mLastLayoutDirection;
    private long mLastScroll;
    private Layout mLayout;
    private ColorStateList mLinkTextColor;
    private boolean mLinksClickable;
    private boolean mListenerChanged;
    private ArrayList mListeners;
    private boolean mLocalesChanged;
    private Marquee mMarquee;
    private int mMarqueeFadeMode;
    private int mMarqueeRepeatLimit;
    private int mMaxMode;
    private int mMaxWidth;
    private int mMaxWidthMode;
    private int mMaximum;
    private int mMinMode;
    private int mMinWidth;
    private int mMinWidthMode;
    private int mMinimum;
    private MovementMethod mMovement;
    private boolean mNeedsAutoSizeText;
    private int mOldMaxMode;
    private int mOldMaximum;
    private boolean mPreDrawListenerDetached;
    private boolean mPreDrawRegistered;
    private boolean mPreventDefaultMovement;
    private boolean mRestartMarquee;
    private BoringLayout mSavedHintLayout;
    private BoringLayout mSavedLayout;
    private Layout mSavedMarqueeModeLayout;
    private Scroller mScroller;
    private int mShadowColor;
    private float mShadowDx;
    private float mShadowDy;
    private float mShadowRadius;
    private boolean mSingleLine;
    private float mSpacingAdd;
    private float mSpacingMult;
    private android.text.Spannable.Factory mSpannableFactory;
    private Rect mTempRect;
    private TextPaint mTempTextPaint;
    private CharSequence mText;
    private TextClassifier mTextClassifier;
    private ColorStateList mTextColor;
    private TextDirectionHeuristic mTextDir;
    int mTextEditSuggestionContainerLayout;
    int mTextEditSuggestionHighlightStyle;
    int mTextEditSuggestionItemLayout;
    private boolean mTextFromResource;
    private final TextPaint mTextPaint;
    int mTextSelectHandleLeftRes;
    int mTextSelectHandleRes;
    int mTextSelectHandleRightRes;
    private TransformationMethod mTransformation;
    private CharSequence mTransformed;
    private final boolean mUseInternationalizedInput;
    private boolean mUserSetTextScaleX;


    // Unreferenced inner class android/widget/TextView$Marquee$1

/* anonymous class */
    class Marquee._cls1
        implements android.view.Choreographer.FrameCallback
    {

        public void doFrame(long l)
        {
            tick();
        }

        final Marquee this$1;

            
            {
                this$1 = Marquee.this;
                super();
            }
    }


    // Unreferenced inner class android/widget/TextView$Marquee$2

/* anonymous class */
    class Marquee._cls2
        implements android.view.Choreographer.FrameCallback
    {

        public void doFrame(long l)
        {
            Marquee._2D_set2(Marquee.this, (byte)2);
            Marquee._2D_set0(Marquee.this, Marquee._2D_get0(Marquee.this).getFrameTime());
            tick();
        }

        final Marquee this$1;

            
            {
                this$1 = Marquee.this;
                super();
            }
    }


    // Unreferenced inner class android/widget/TextView$Marquee$3

/* anonymous class */
    class Marquee._cls3
        implements android.view.Choreographer.FrameCallback
    {

        public void doFrame(long l)
        {
            if(Marquee._2D_get2(Marquee.this) == 2)
            {
                if(Marquee._2D_get1(Marquee.this) >= 0)
                {
                    Marquee marquee = Marquee.this;
                    Marquee._2D_set1(marquee, Marquee._2D_get1(marquee) - 1);
                }
                start(Marquee._2D_get1(Marquee.this));
            }
        }

        final Marquee this$1;

            
            {
                this$1 = Marquee.this;
                super();
            }
    }

}
