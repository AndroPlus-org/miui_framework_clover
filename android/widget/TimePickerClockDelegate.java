// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.*;
import android.icu.text.DecimalFormatSymbols;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.StateSet;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.InputMethodManager;
import com.android.internal.widget.NumericTextView;
import java.util.Calendar;
import java.util.List;

// Referenced classes of package android.widget:
//            TextView, TimePicker, RadioButton, RadialTimePickerView, 
//            TextInputTimePickerView, ImageButton

class TimePickerClockDelegate extends TimePicker.AbstractTimePickerDelegate
{
    private static class ClickActionDelegate extends android.view.View.AccessibilityDelegate
    {

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilitynodeinfo)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfo);
            accessibilitynodeinfo.addAction(mClickAction);
        }

        private final android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction mClickAction;

        public ClickActionDelegate(Context context, int i)
        {
            mClickAction = new android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction(16, context.getString(i));
        }
    }

    private static class NearestTouchDelegate
        implements android.view.View.OnTouchListener
    {

        private View findNearestChild(ViewGroup viewgroup, int i, int j)
        {
            View view = null;
            int k = 0x7fffffff;
            int l = 0;
            for(int i1 = viewgroup.getChildCount(); l < i1;)
            {
                View view1 = viewgroup.getChildAt(l);
                int j1 = i - (view1.getLeft() + view1.getWidth() / 2);
                int k1 = j - (view1.getTop() + view1.getHeight() / 2);
                j1 = j1 * j1 + k1 * k1;
                k1 = k;
                if(k > j1)
                {
                    view = view1;
                    k1 = j1;
                }
                l++;
                k = k1;
            }

            return view;
        }

        public boolean onTouch(View view, MotionEvent motionevent)
        {
            int i = motionevent.getActionMasked();
            View view1;
            if(i == 0)
                if(view instanceof ViewGroup)
                    mInitialTouchTarget = findNearestChild((ViewGroup)view, (int)motionevent.getX(), (int)motionevent.getY());
                else
                    mInitialTouchTarget = null;
            view1 = mInitialTouchTarget;
            if(view1 == null)
                return false;
            float f = view.getScrollX() - view1.getLeft();
            float f1 = view.getScrollY() - view1.getTop();
            motionevent.offsetLocation(f, f1);
            boolean flag = view1.dispatchTouchEvent(motionevent);
            motionevent.offsetLocation(-f, -f1);
            if(i == 1 || i == 3)
                mInitialTouchTarget = null;
            return flag;
        }

        private View mInitialTouchTarget;

        private NearestTouchDelegate()
        {
        }

        NearestTouchDelegate(NearestTouchDelegate nearesttouchdelegate)
        {
            this();
        }
    }


    static boolean _2D_get0(TimePickerClockDelegate timepickerclockdelegate)
    {
        return timepickerclockdelegate.mAllowAutoAdvance;
    }

    static Runnable _2D_get1(TimePickerClockDelegate timepickerclockdelegate)
    {
        return timepickerclockdelegate.mCommitHour;
    }

    static Runnable _2D_get2(TimePickerClockDelegate timepickerclockdelegate)
    {
        return timepickerclockdelegate.mCommitMinute;
    }

    static NumericTextView _2D_get3(TimePickerClockDelegate timepickerclockdelegate)
    {
        return timepickerclockdelegate.mHourView;
    }

    static NumericTextView _2D_get4(TimePickerClockDelegate timepickerclockdelegate)
    {
        return timepickerclockdelegate.mMinuteView;
    }

    static String _2D_get5(TimePickerClockDelegate timepickerclockdelegate)
    {
        return timepickerclockdelegate.mSelectMinutes;
    }

    static int _2D_wrap0(TimePickerClockDelegate timepickerclockdelegate, int i)
    {
        return timepickerclockdelegate.getLocalizedHour(i);
    }

    static void _2D_wrap1(TimePickerClockDelegate timepickerclockdelegate, int i)
    {
        timepickerclockdelegate.setAmOrPm(i);
    }

    static void _2D_wrap2(TimePickerClockDelegate timepickerclockdelegate, int i, boolean flag, boolean flag1)
    {
        timepickerclockdelegate.setCurrentItemShowing(i, flag, flag1);
    }

    static void _2D_wrap3(TimePickerClockDelegate timepickerclockdelegate, int i, int j, boolean flag, boolean flag1)
    {
        timepickerclockdelegate.setHourInternal(i, j, flag, flag1);
    }

    static void _2D_wrap4(TimePickerClockDelegate timepickerclockdelegate, int i, int j, boolean flag)
    {
        timepickerclockdelegate.setMinuteInternal(i, j, flag);
    }

    static void _2D_wrap5(TimePickerClockDelegate timepickerclockdelegate)
    {
        timepickerclockdelegate.toggleRadialPickerMode();
    }

    static void _2D_wrap6(TimePickerClockDelegate timepickerclockdelegate)
    {
        timepickerclockdelegate.tryVibrate();
    }

    public TimePickerClockDelegate(TimePicker timepicker, Context context, AttributeSet attributeset, int i, int j)
    {
        super(timepicker, context);
        mRadialPickerModeEnabled = true;
        mIsEnabled = true;
        mIsAmPmAtLeft = false;
        mIsAmPmAtTop = false;
        TypedArray typedarray = mContext.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TimePicker, i, j);
        Object obj = (LayoutInflater)mContext.getSystemService("layout_inflater");
        Object obj1 = mContext.getResources();
        mSelectHours = ((Resources) (obj1)).getString(0x10405ad);
        mSelectMinutes = ((Resources) (obj1)).getString(0x10405b1);
        obj = ((LayoutInflater) (obj)).inflate(typedarray.getResourceId(12, 0x1090113), timepicker);
        ((View) (obj)).setSaveFromParentEnabled(false);
        mRadialTimePickerHeader = ((View) (obj)).findViewById(0x1020452);
        mRadialTimePickerHeader.setOnTouchListener(new NearestTouchDelegate(null));
        mHourView = (NumericTextView)((View) (obj)).findViewById(0x102028f);
        mHourView.setOnClickListener(mClickListener);
        mHourView.setOnFocusChangeListener(mFocusListener);
        mHourView.setOnDigitEnteredListener(mDigitEnteredListener);
        mHourView.setAccessibilityDelegate(new ClickActionDelegate(context, 0x10405ad));
        mSeparatorView = (TextView)((View) (obj)).findViewById(0x10203dc);
        mMinuteView = (NumericTextView)((View) (obj)).findViewById(0x1020304);
        mMinuteView.setOnClickListener(mClickListener);
        mMinuteView.setOnFocusChangeListener(mFocusListener);
        mMinuteView.setOnDigitEnteredListener(mDigitEnteredListener);
        mMinuteView.setAccessibilityDelegate(new ClickActionDelegate(context, 0x10405b1));
        mMinuteView.setRange(0, 59);
        mAmPmLayout = ((View) (obj)).findViewById(0x10201a7);
        mAmPmLayout.setOnTouchListener(new NearestTouchDelegate(null));
        timepicker = TimePicker.getAmPmStrings(context);
        mAmLabel = (RadioButton)mAmPmLayout.findViewById(0x10201a5);
        mAmLabel.setText(obtainVerbatim(timepicker[0]));
        mAmLabel.setOnClickListener(mClickListener);
        ensureMinimumTextWidth(mAmLabel);
        mPmLabel = (RadioButton)mAmPmLayout.findViewById(0x1020380);
        mPmLabel.setText(obtainVerbatim(timepicker[1]));
        mPmLabel.setOnClickListener(mClickListener);
        ensureMinimumTextWidth(mPmLabel);
        timepicker = null;
        int k = typedarray.getResourceId(1, 0);
        if(k != 0)
        {
            obj1 = mContext.obtainStyledAttributes(null, ATTRS_TEXT_COLOR, 0, k);
            timepicker = applyLegacyColorFixes(((TypedArray) (obj1)).getColorStateList(0));
            ((TypedArray) (obj1)).recycle();
        }
        obj1 = timepicker;
        if(timepicker == null)
            obj1 = typedarray.getColorStateList(11);
        mTextInputPickerHeader = ((View) (obj)).findViewById(0x10202af);
        if(obj1 != null)
        {
            mHourView.setTextColor(((ColorStateList) (obj1)));
            mSeparatorView.setTextColor(((ColorStateList) (obj1)));
            mMinuteView.setTextColor(((ColorStateList) (obj1)));
            mAmLabel.setTextColor(((ColorStateList) (obj1)));
            mPmLabel.setTextColor(((ColorStateList) (obj1)));
        }
        if(typedarray.hasValueOrEmpty(0))
        {
            mRadialTimePickerHeader.setBackground(typedarray.getDrawable(0));
            mTextInputPickerHeader.setBackground(typedarray.getDrawable(0));
        }
        typedarray.recycle();
        mRadialTimePickerView = (RadialTimePickerView)((View) (obj)).findViewById(0x102039c);
        mRadialTimePickerView.applyAttributes(attributeset, i, j);
        mRadialTimePickerView.setOnValueSelectedListener(mOnValueSelectedListener);
        mTextInputPickerView = (TextInputTimePickerView)((View) (obj)).findViewById(0x10202b2);
        mTextInputPickerView.setListener(mOnValueTypedListener);
        mRadialTimePickerModeButton = (ImageButton)((View) (obj)).findViewById(0x1020462);
        mRadialTimePickerModeButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                TimePickerClockDelegate._2D_wrap5(TimePickerClockDelegate.this);
            }

            final TimePickerClockDelegate this$0;

            
            {
                this$0 = TimePickerClockDelegate.this;
                super();
            }
        }
);
        mRadialTimePickerModeEnabledDescription = context.getResources().getString(0x1040646);
        mTextInputPickerModeEnabledDescription = context.getResources().getString(0x1040647);
        mAllowAutoAdvance = true;
        updateHourFormat();
        mTempCalendar = Calendar.getInstance(mLocale);
        initialize(mTempCalendar.get(11), mTempCalendar.get(12), mIs24Hour, 0);
    }

    private ColorStateList applyLegacyColorFixes(ColorStateList colorstatelist)
    {
        if(colorstatelist == null || colorstatelist.hasState(0x10102fe))
            return colorstatelist;
        int i;
        int j;
        if(colorstatelist.hasState(0x10100a1))
        {
            i = colorstatelist.getColorForState(StateSet.get(10), 0);
            j = colorstatelist.getColorForState(StateSet.get(8), 0);
        } else
        {
            i = colorstatelist.getDefaultColor();
            j = multiplyAlphaComponent(i, mContext.obtainStyledAttributes(ATTRS_DISABLED_ALPHA).getFloat(0, 0.3F));
        }
        if(i == 0 || j == 0)
            return null;
        else
            return new ColorStateList(new int[][] {
                new int[] {
                    0x10102fe
                }, new int[0]
            }, new int[] {
                i, j
            });
    }

    private static void ensureMinimumTextWidth(TextView textview)
    {
        textview.measure(0, 0);
        int i = textview.getMeasuredWidth();
        textview.setMinWidth(i);
        textview.setMinimumWidth(i);
    }

    private int getCurrentItemShowing()
    {
        return mRadialTimePickerView.getCurrentItemShowing();
    }

    private int getLocalizedHour(int i)
    {
        int j = i;
        if(!mIs24Hour)
            j = i % 12;
        i = j;
        if(!mHourFormatStartsAtZero)
        {
            i = j;
            if(j == 0)
                if(mIs24Hour)
                    i = 24;
                else
                    i = 12;
        }
        return i;
    }

    private void initialize(int i, int j, boolean flag, int k)
    {
        mCurrentHour = i;
        mCurrentMinute = j;
        mIs24Hour = flag;
        updateUI(k);
    }

    private static int lastIndexOfAny(String s, char ac[])
    {
        int i = ac.length;
        if(i > 0)
        {
            for(int j = s.length() - 1; j >= 0; j--)
            {
                char c = s.charAt(j);
                for(int k = 0; k < i; k++)
                    if(c == ac[k])
                        return j;

            }

        }
        return -1;
    }

    private int multiplyAlphaComponent(int i, float f)
    {
        return (int)((float)(i >> 24 & 0xff) * f + 0.5F) << 24 | i & 0xffffff;
    }

    static final CharSequence obtainVerbatim(String s)
    {
        return (new SpannableStringBuilder()).append(s, (new android.text.style.TtsSpan.VerbatimBuilder(s)).build(), 0);
    }

    private void onTimeChanged()
    {
        mDelegator.sendAccessibilityEvent(4);
        if(mOnTimeChangedListener != null)
            mOnTimeChangedListener.onTimeChanged(mDelegator, getHour(), getMinute());
        if(mAutoFillChangeListener != null)
            mAutoFillChangeListener.onTimeChanged(mDelegator, getHour(), getMinute());
    }

    private void setAmOrPm(int i)
    {
        updateAmPmLabelStates(i);
        if(mRadialTimePickerView.setAmOrPm(i))
        {
            mCurrentHour = getHour();
            updateTextInputPicker();
            if(mOnTimeChangedListener != null)
                mOnTimeChangedListener.onTimeChanged(mDelegator, getHour(), getMinute());
        }
    }

    private void setAmPmStart(boolean flag)
    {
        RelativeLayout.LayoutParams layoutparams = (RelativeLayout.LayoutParams)mAmPmLayout.getLayoutParams();
        if(layoutparams.getRule(1) == 0 && layoutparams.getRule(0) == 0) goto _L2; else goto _L1
_L1:
        if(TextUtils.getLayoutDirectionFromLocale(mLocale) != 0)
            flag ^= true;
        if(mIsAmPmAtLeft == flag)
            return;
        if(flag)
        {
            layoutparams.removeRule(1);
            layoutparams.addRule(0, mHourView.getId());
        } else
        {
            layoutparams.removeRule(0);
            layoutparams.addRule(1, mMinuteView.getId());
        }
        mIsAmPmAtLeft = flag;
_L4:
        mAmPmLayout.setLayoutParams(layoutparams);
        return;
_L2:
        if(layoutparams.getRule(3) != 0 || layoutparams.getRule(2) != 0)
        {
            if(mIsAmPmAtTop == flag)
                return;
            int i;
            View view;
            int j;
            if(flag)
            {
                i = layoutparams.getRule(3);
                layoutparams.removeRule(3);
                layoutparams.addRule(2, i);
            } else
            {
                i = layoutparams.getRule(2);
                layoutparams.removeRule(2);
                layoutparams.addRule(3, i);
            }
            view = mRadialTimePickerHeader.findViewById(i);
            j = view.getPaddingTop();
            i = view.getPaddingBottom();
            view.setPadding(view.getPaddingLeft(), i, view.getPaddingRight(), j);
            mIsAmPmAtTop = flag;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void setCurrentItemShowing(int i, boolean flag, boolean flag1)
    {
        boolean flag2 = true;
        mRadialTimePickerView.setCurrentItemShowing(i, flag);
        NumericTextView numerictextview;
        if(i == 0)
        {
            if(flag1)
                mDelegator.announceForAccessibility(mSelectHours);
        } else
        if(flag1)
            mDelegator.announceForAccessibility(mSelectMinutes);
        numerictextview = mHourView;
        if(i == 0)
            flag = true;
        else
            flag = false;
        numerictextview.setActivated(flag);
        numerictextview = mMinuteView;
        if(i == 1)
            flag = flag2;
        else
            flag = false;
        numerictextview.setActivated(flag);
    }

    private void setHourInternal(int i, int j, boolean flag, boolean flag1)
    {
        int k = 1;
        if(mCurrentHour == i)
            return;
        resetAutofilledValue();
        mCurrentHour = i;
        updateHeaderHour(i, flag);
        updateHeaderAmPm();
        if(j != 1)
        {
            mRadialTimePickerView.setCurrentHour(i);
            RadialTimePickerView radialtimepickerview = mRadialTimePickerView;
            if(i < 12)
                k = 0;
            radialtimepickerview.setAmOrPm(k);
        }
        if(j != 2)
            updateTextInputPicker();
        mDelegator.invalidate();
        if(flag1)
            onTimeChanged();
    }

    private void setMinuteInternal(int i, int j, boolean flag)
    {
        if(mCurrentMinute == i)
            return;
        resetAutofilledValue();
        mCurrentMinute = i;
        updateHeaderMinute(i, true);
        if(j != 1)
            mRadialTimePickerView.setCurrentMinute(i);
        if(j != 2)
            updateTextInputPicker();
        mDelegator.invalidate();
        if(flag)
            onTimeChanged();
    }

    private void toggleRadialPickerMode()
    {
        if(mRadialPickerModeEnabled)
        {
            mRadialTimePickerView.setVisibility(8);
            mRadialTimePickerHeader.setVisibility(8);
            mTextInputPickerHeader.setVisibility(0);
            mTextInputPickerView.setVisibility(0);
            mRadialTimePickerModeButton.setImageResource(0x108011d);
            mRadialTimePickerModeButton.setContentDescription(mRadialTimePickerModeEnabledDescription);
            mRadialPickerModeEnabled = false;
        } else
        {
            mRadialTimePickerView.setVisibility(0);
            mRadialTimePickerHeader.setVisibility(0);
            mTextInputPickerHeader.setVisibility(8);
            mTextInputPickerView.setVisibility(8);
            mRadialTimePickerModeButton.setImageResource(0x108016a);
            mRadialTimePickerModeButton.setContentDescription(mTextInputPickerModeEnabledDescription);
            updateTextInputPicker();
            InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
            if(inputmethodmanager != null)
                inputmethodmanager.hideSoftInputFromWindow(mDelegator.getWindowToken(), 0);
            mRadialPickerModeEnabled = true;
        }
    }

    private void tryAnnounceForAccessibility(CharSequence charsequence, boolean flag)
    {
        if(mLastAnnouncedIsHour != flag || charsequence.equals(mLastAnnouncedText) ^ true)
        {
            mDelegator.announceForAccessibility(charsequence);
            mLastAnnouncedText = charsequence;
            mLastAnnouncedIsHour = flag;
        }
    }

    private void tryVibrate()
    {
        mDelegator.performHapticFeedback(4);
    }

    private void updateAmPmLabelStates(int i)
    {
        boolean flag;
        if(i == 0)
            flag = true;
        else
            flag = false;
        mAmLabel.setActivated(flag);
        mAmLabel.setChecked(flag);
        if(i == 1)
            flag = true;
        else
            flag = false;
        mPmLabel.setActivated(flag);
        mPmLabel.setChecked(flag);
    }

    private void updateHeaderAmPm()
    {
        if(mIs24Hour)
        {
            mAmPmLayout.setVisibility(8);
        } else
        {
            setAmPmStart(DateFormat.getBestDateTimePattern(mLocale, "hm").startsWith("a"));
            int i;
            if(mCurrentHour < 12)
                i = 0;
            else
                i = 1;
            updateAmPmLabelStates(i);
        }
    }

    private void updateHeaderHour(int i, boolean flag)
    {
        i = getLocalizedHour(i);
        mHourView.setValue(i);
        if(flag)
            tryAnnounceForAccessibility(mHourView.getText(), true);
    }

    private void updateHeaderMinute(int i, boolean flag)
    {
        mMinuteView.setValue(i);
        if(flag)
            tryAnnounceForAccessibility(mMinuteView.getText(), false);
    }

    private void updateHeaderSeparator()
    {
        java.util.Locale locale = mLocale;
        String s;
        int i;
        if(mIs24Hour)
            s = "Hm";
        else
            s = "hm";
        s = DateFormat.getBestDateTimePattern(locale, s);
        i = lastIndexOfAny(s, new char[] {
            'H', 'h', 'K', 'k'
        });
        if(i == -1)
            s = ":";
        else
            s = Character.toString(s.charAt(i + 1));
        mSeparatorView.setText(s);
        mTextInputPickerView.updateSeparator(s);
    }

    private void updateHourFormat()
    {
        int j;
        int k;
        char c1;
        java.util.Locale locale = mLocale;
        Object obj;
        int i;
        boolean flag;
        char c;
        boolean flag1;
        if(mIs24Hour)
            obj = "Hm";
        else
            obj = "hm";
        obj = DateFormat.getBestDateTimePattern(locale, ((String) (obj)));
        i = ((String) (obj)).length();
        flag = false;
        c = '\0';
        j = 0;
_L7:
        k = c;
        flag1 = flag;
        if(j >= i) goto _L2; else goto _L1
_L1:
        c1 = ((String) (obj)).charAt(j);
          goto _L3
_L5:
        c = c1;
        k = c;
        flag1 = flag;
        if(j + 1 < i)
        {
            k = c;
            flag1 = flag;
            if(c1 == ((String) (obj)).charAt(j + 1))
            {
                flag1 = true;
                k = c;
            }
        }
_L2:
        mHourFormatShowLeadingZero = flag1;
        boolean flag2;
        if(k == 'K' || k == 'H')
            flag2 = true;
        else
            flag2 = false;
        mHourFormatStartsAtZero = flag2;
        if(mHourFormatStartsAtZero)
            j = 0;
        else
            j = 1;
        if(mIs24Hour)
            k = 23;
        else
            k = 11;
        mHourView.setRange(j, k + j);
        mHourView.setShowLeadingZeroes(mHourFormatShowLeadingZero);
        obj = DecimalFormatSymbols.getInstance(mLocale).getDigitStrings();
        k = 0;
        for(j = 0; j < 10; j++)
            k = Math.max(k, obj[j].length());

        break; /* Loop/switch isn't completed */
_L3:
        if(c1 == 'H' || c1 == 'h' || c1 == 'K' || c1 == 'k') goto _L5; else goto _L4
_L4:
        j++;
        if(true) goto _L7; else goto _L6
_L6:
        mTextInputPickerView.setHourFormat(k * 2);
        return;
    }

    private void updateRadialPicker(int i)
    {
        mRadialTimePickerView.initialize(mCurrentHour, mCurrentMinute, mIs24Hour);
        setCurrentItemShowing(i, false, true);
    }

    private void updateTextInputPicker()
    {
        TextInputTimePickerView textinputtimepickerview = mTextInputPickerView;
        int i = getLocalizedHour(mCurrentHour);
        int j = mCurrentMinute;
        int k;
        if(mCurrentHour < 12)
            k = 0;
        else
            k = 1;
        textinputtimepickerview.updateTextInputValues(i, j, k, mIs24Hour, mHourFormatStartsAtZero);
    }

    private void updateUI(int i)
    {
        updateHeaderAmPm();
        updateHeaderHour(mCurrentHour, false);
        updateHeaderSeparator();
        updateHeaderMinute(mCurrentMinute, false);
        updateRadialPicker(i);
        updateTextInputPicker();
        mDelegator.invalidate();
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        onPopulateAccessibilityEvent(accessibilityevent);
        return true;
    }

    public View getAmView()
    {
        return mAmLabel;
    }

    public int getBaseline()
    {
        return -1;
    }

    public int getHour()
    {
        int i = mRadialTimePickerView.getCurrentHour();
        if(mIs24Hour)
            return i;
        if(mRadialTimePickerView.getAmOrPm() == 1)
            return i % 12 + 12;
        else
            return i % 12;
    }

    public View getHourView()
    {
        return mHourView;
    }

    public int getMinute()
    {
        return mRadialTimePickerView.getCurrentMinute();
    }

    public View getMinuteView()
    {
        return mMinuteView;
    }

    public View getPmView()
    {
        return mPmLabel;
    }

    public boolean is24Hour()
    {
        return mIs24Hour;
    }

    public boolean isEnabled()
    {
        return mIsEnabled;
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        char c;
        String s;
        String s1;
        if(mIs24Hour)
            c = '\201';
        else
            c = 'A';
        mTempCalendar.set(11, getHour());
        mTempCalendar.set(12, getMinute());
        s = DateUtils.formatDateTime(mContext, mTempCalendar.getTimeInMillis(), c);
        if(mRadialTimePickerView.getCurrentItemShowing() == 0)
            s1 = mSelectHours;
        else
            s1 = mSelectMinutes;
        accessibilityevent.getText().add((new StringBuilder()).append(s).append(" ").append(s1).toString());
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        if(parcelable instanceof TimePicker.AbstractTimePickerDelegate.SavedState)
        {
            parcelable = (TimePicker.AbstractTimePickerDelegate.SavedState)parcelable;
            initialize(parcelable.getHour(), parcelable.getMinute(), parcelable.is24HourMode(), parcelable.getCurrentItemShowing());
            mRadialTimePickerView.invalidate();
        }
    }

    public Parcelable onSaveInstanceState(Parcelable parcelable)
    {
        return new TimePicker.AbstractTimePickerDelegate.SavedState(parcelable, getHour(), getMinute(), is24Hour(), getCurrentItemShowing());
    }

    public void setDate(int i, int j)
    {
        setHourInternal(i, 0, true, false);
        setMinuteInternal(j, 0, false);
        onTimeChanged();
    }

    public void setEnabled(boolean flag)
    {
        mHourView.setEnabled(flag);
        mMinuteView.setEnabled(flag);
        mAmLabel.setEnabled(flag);
        mPmLabel.setEnabled(flag);
        mRadialTimePickerView.setEnabled(flag);
        mIsEnabled = flag;
    }

    public void setHour(int i)
    {
        setHourInternal(i, 0, true, true);
    }

    public void setIs24Hour(boolean flag)
    {
        if(mIs24Hour != flag)
        {
            mIs24Hour = flag;
            mCurrentHour = getHour();
            updateHourFormat();
            updateUI(mRadialTimePickerView.getCurrentItemShowing());
        }
    }

    public void setMinute(int i)
    {
        setMinuteInternal(i, 0, true);
    }

    public boolean validateInput()
    {
        return mTextInputPickerView.validateInput();
    }

    private static final int AM = 0;
    private static final int ATTRS_DISABLED_ALPHA[] = {
        0x1010033
    };
    private static final int ATTRS_TEXT_COLOR[] = {
        0x1010098
    };
    private static final long DELAY_COMMIT_MILLIS = 2000L;
    private static final int FROM_EXTERNAL_API = 0;
    private static final int FROM_INPUT_PICKER = 2;
    private static final int FROM_RADIAL_PICKER = 1;
    private static final int HOURS_IN_HALF_DAY = 12;
    private static final int HOUR_INDEX = 0;
    private static final int MINUTE_INDEX = 1;
    private static final int PM = 1;
    private boolean mAllowAutoAdvance;
    private final RadioButton mAmLabel;
    private final View mAmPmLayout;
    private final android.view.View.OnClickListener mClickListener = new android.view.View.OnClickListener() {

        public void onClick(View view)
        {
            view.getId();
            JVM INSTR lookupswitch 4: default 48
        //                       16908709: 49
        //                       16908943: 76
        //                       16909060: 89
        //                       16909184: 65;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            return;
_L2:
            TimePickerClockDelegate._2D_wrap1(TimePickerClockDelegate.this, 0);
_L7:
            TimePickerClockDelegate._2D_wrap6(TimePickerClockDelegate.this);
            return;
_L5:
            TimePickerClockDelegate._2D_wrap1(TimePickerClockDelegate.this, 1);
            continue; /* Loop/switch isn't completed */
_L3:
            TimePickerClockDelegate._2D_wrap2(TimePickerClockDelegate.this, 0, true, true);
            continue; /* Loop/switch isn't completed */
_L4:
            TimePickerClockDelegate._2D_wrap2(TimePickerClockDelegate.this, 1, true, true);
            if(true) goto _L7; else goto _L6
_L6:
        }

        final TimePickerClockDelegate this$0;

            
            {
                this$0 = TimePickerClockDelegate.this;
                super();
            }
    }
;
    private final Runnable mCommitHour = new Runnable() {

        public void run()
        {
            setHour(TimePickerClockDelegate._2D_get3(TimePickerClockDelegate.this).getValue());
        }

        final TimePickerClockDelegate this$0;

            
            {
                this$0 = TimePickerClockDelegate.this;
                super();
            }
    }
;
    private final Runnable mCommitMinute = new Runnable() {

        public void run()
        {
            setMinute(TimePickerClockDelegate._2D_get4(TimePickerClockDelegate.this).getValue());
        }

        final TimePickerClockDelegate this$0;

            
            {
                this$0 = TimePickerClockDelegate.this;
                super();
            }
    }
;
    private int mCurrentHour;
    private int mCurrentMinute;
    private final com.android.internal.widget.NumericTextView.OnValueChangedListener mDigitEnteredListener = new com.android.internal.widget.NumericTextView.OnValueChangedListener() {

        public void onValueChanged(NumericTextView numerictextview, int l, boolean flag, boolean flag1)
        {
            Runnable runnable;
            NumericTextView numerictextview1;
            if(numerictextview == TimePickerClockDelegate._2D_get3(TimePickerClockDelegate.this))
            {
                runnable = TimePickerClockDelegate._2D_get1(TimePickerClockDelegate.this);
                if(numerictextview.isFocused())
                    numerictextview1 = TimePickerClockDelegate._2D_get4(TimePickerClockDelegate.this);
                else
                    numerictextview1 = null;
            } else
            if(numerictextview == TimePickerClockDelegate._2D_get4(TimePickerClockDelegate.this))
            {
                runnable = TimePickerClockDelegate._2D_get2(TimePickerClockDelegate.this);
                numerictextview1 = null;
            } else
            {
                return;
            }
            numerictextview.removeCallbacks(runnable);
            if(flag)
                if(flag1)
                {
                    runnable.run();
                    if(numerictextview1 != null)
                        numerictextview1.requestFocus();
                } else
                {
                    numerictextview.postDelayed(runnable, 2000L);
                }
        }

        final TimePickerClockDelegate this$0;

            
            {
                this$0 = TimePickerClockDelegate.this;
                super();
            }
    }
;
    private final android.view.View.OnFocusChangeListener mFocusListener = new android.view.View.OnFocusChangeListener() {

        public void onFocusChange(View view, boolean flag)
        {
            if(!flag) goto _L2; else goto _L1
_L1:
            view.getId();
            JVM INSTR lookupswitch 4: default 52
        //                       16908709: 53
        //                       16908943: 80
        //                       16909060: 93
        //                       16909184: 69;
               goto _L3 _L4 _L5 _L6 _L7
_L3:
            return;
_L4:
            TimePickerClockDelegate._2D_wrap1(TimePickerClockDelegate.this, 0);
_L9:
            TimePickerClockDelegate._2D_wrap6(TimePickerClockDelegate.this);
_L2:
            return;
_L7:
            TimePickerClockDelegate._2D_wrap1(TimePickerClockDelegate.this, 1);
            continue; /* Loop/switch isn't completed */
_L5:
            TimePickerClockDelegate._2D_wrap2(TimePickerClockDelegate.this, 0, true, true);
            continue; /* Loop/switch isn't completed */
_L6:
            TimePickerClockDelegate._2D_wrap2(TimePickerClockDelegate.this, 1, true, true);
            if(true) goto _L9; else goto _L8
_L8:
        }

        final TimePickerClockDelegate this$0;

            
            {
                this$0 = TimePickerClockDelegate.this;
                super();
            }
    }
;
    private boolean mHourFormatShowLeadingZero;
    private boolean mHourFormatStartsAtZero;
    private final NumericTextView mHourView;
    private boolean mIs24Hour;
    private boolean mIsAmPmAtLeft;
    private boolean mIsAmPmAtTop;
    private boolean mIsEnabled;
    private boolean mLastAnnouncedIsHour;
    private CharSequence mLastAnnouncedText;
    private final NumericTextView mMinuteView;
    private final RadialTimePickerView.OnValueSelectedListener mOnValueSelectedListener = new RadialTimePickerView.OnValueSelectedListener() {

        public void onValueSelected(int l, int i1, boolean flag)
        {
            boolean flag1;
            boolean flag2;
            int j1;
            flag1 = false;
            flag2 = false;
            j1 = 0;
            l;
            JVM INSTR tableswitch 0 1: default 32
        //                       0 81
        //                       1 196;
               goto _L1 _L2 _L3
_L1:
            if(mOnTimeChangedListener != null && j1 != 0)
                mOnTimeChangedListener.onTimeChanged(mDelegator, getHour(), getMinute());
            return;
_L2:
            l = ((flag1) ? 1 : 0);
            if(getHour() != i1)
                l = 1;
            if(!TimePickerClockDelegate._2D_get0(TimePickerClockDelegate.this))
                flag = false;
            TimePickerClockDelegate._2D_wrap3(TimePickerClockDelegate.this, i1, 1, flag ^ true, true);
            j1 = l;
            if(flag)
            {
                TimePickerClockDelegate._2D_wrap2(TimePickerClockDelegate.this, 1, true, false);
                i1 = TimePickerClockDelegate._2D_wrap0(TimePickerClockDelegate.this, i1);
                mDelegator.announceForAccessibility((new StringBuilder()).append(i1).append(". ").append(TimePickerClockDelegate._2D_get5(TimePickerClockDelegate.this)).toString());
                j1 = l;
            }
            continue; /* Loop/switch isn't completed */
_L3:
            j1 = ((flag2) ? 1 : 0);
            if(getMinute() != i1)
                j1 = 1;
            TimePickerClockDelegate._2D_wrap4(TimePickerClockDelegate.this, i1, 1, true);
            if(true) goto _L1; else goto _L4
_L4:
        }

        final TimePickerClockDelegate this$0;

            
            {
                this$0 = TimePickerClockDelegate.this;
                super();
            }
    }
;
    private final TextInputTimePickerView.OnValueTypedListener mOnValueTypedListener = new TextInputTimePickerView.OnValueTypedListener() {

        public void onValueChanged(int l, int i1)
        {
            l;
            JVM INSTR tableswitch 0 2: default 28
        //                       0 29
        //                       1 43
        //                       2 56;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            TimePickerClockDelegate._2D_wrap3(TimePickerClockDelegate.this, i1, 2, false, true);
            continue; /* Loop/switch isn't completed */
_L3:
            TimePickerClockDelegate._2D_wrap4(TimePickerClockDelegate.this, i1, 2, true);
            continue; /* Loop/switch isn't completed */
_L4:
            TimePickerClockDelegate._2D_wrap1(TimePickerClockDelegate.this, i1);
            if(true) goto _L1; else goto _L5
_L5:
        }

        final TimePickerClockDelegate this$0;

            
            {
                this$0 = TimePickerClockDelegate.this;
                super();
            }
    }
;
    private final RadioButton mPmLabel;
    private boolean mRadialPickerModeEnabled;
    private final View mRadialTimePickerHeader;
    private final ImageButton mRadialTimePickerModeButton;
    private final String mRadialTimePickerModeEnabledDescription;
    private final RadialTimePickerView mRadialTimePickerView;
    private final String mSelectHours;
    private final String mSelectMinutes;
    private final TextView mSeparatorView;
    private final Calendar mTempCalendar;
    private final View mTextInputPickerHeader;
    private final String mTextInputPickerModeEnabledDescription;
    private final TextInputTimePickerView mTextInputPickerView;

}
