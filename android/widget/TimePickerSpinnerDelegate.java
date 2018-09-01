// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.*;
import android.os.Parcelable;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.inputmethod.InputMethodManager;
import java.util.Calendar;
import java.util.List;
import libcore.icu.LocaleData;

// Referenced classes of package android.widget:
//            TimePicker, NumberPicker, EditText, TextView, 
//            Button

class TimePickerSpinnerDelegate extends TimePicker.AbstractTimePickerDelegate
{

    static NumberPicker _2D_get0(TimePickerSpinnerDelegate timepickerspinnerdelegate)
    {
        return timepickerspinnerdelegate.mHourSpinner;
    }

    static boolean _2D_get1(TimePickerSpinnerDelegate timepickerspinnerdelegate)
    {
        return timepickerspinnerdelegate.mIsAm;
    }

    static NumberPicker _2D_get2(TimePickerSpinnerDelegate timepickerspinnerdelegate)
    {
        return timepickerspinnerdelegate.mMinuteSpinner;
    }

    static boolean _2D_set0(TimePickerSpinnerDelegate timepickerspinnerdelegate, boolean flag)
    {
        timepickerspinnerdelegate.mIsAm = flag;
        return flag;
    }

    static void _2D_wrap0(TimePickerSpinnerDelegate timepickerspinnerdelegate)
    {
        timepickerspinnerdelegate.onTimeChanged();
    }

    static void _2D_wrap1(TimePickerSpinnerDelegate timepickerspinnerdelegate)
    {
        timepickerspinnerdelegate.updateAmPmControl();
    }

    static void _2D_wrap2(TimePickerSpinnerDelegate timepickerspinnerdelegate)
    {
        timepickerspinnerdelegate.updateInputState();
    }

    public TimePickerSpinnerDelegate(TimePicker timepicker, Context context, AttributeSet attributeset, int i, int j)
    {
        super(timepicker, context);
        mIsEnabled = true;
        attributeset = mContext.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TimePicker, i, j);
        i = attributeset.getResourceId(13, 0x1090111);
        attributeset.recycle();
        LayoutInflater.from(mContext).inflate(i, mDelegator, true).setSaveFromParentEnabled(false);
        mHourSpinner = (NumberPicker)timepicker.findViewById(0x102028e);
        mHourSpinner.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker numberpicker, int k, int l)
            {
                TimePickerSpinnerDelegate._2D_wrap2(TimePickerSpinnerDelegate.this);
                break MISSING_BLOCK_LABEL_7;
                if(!is24Hour() && (k == 11 && l == 12 || k == 12 && l == 11))
                {
                    TimePickerSpinnerDelegate._2D_set0(TimePickerSpinnerDelegate.this, TimePickerSpinnerDelegate._2D_get1(TimePickerSpinnerDelegate.this) ^ true);
                    TimePickerSpinnerDelegate._2D_wrap1(TimePickerSpinnerDelegate.this);
                }
                TimePickerSpinnerDelegate._2D_wrap0(TimePickerSpinnerDelegate.this);
                return;
            }

            final TimePickerSpinnerDelegate this$0;

            
            {
                this$0 = TimePickerSpinnerDelegate.this;
                super();
            }
        }
);
        mHourSpinnerInput = (EditText)mHourSpinner.findViewById(0x1020344);
        mHourSpinnerInput.setImeOptions(5);
        mDivider = (TextView)mDelegator.findViewById(0x102022b);
        if(mDivider != null)
            setDividerText();
        mMinuteSpinner = (NumberPicker)mDelegator.findViewById(0x1020303);
        mMinuteSpinner.setMinValue(0);
        mMinuteSpinner.setMaxValue(59);
        mMinuteSpinner.setOnLongPressUpdateInterval(100L);
        mMinuteSpinner.setFormatter(NumberPicker.getTwoDigitFormatter());
        mMinuteSpinner.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker numberpicker, int k, int l)
            {
                int i1;
                int j1;
                TimePickerSpinnerDelegate._2D_wrap2(TimePickerSpinnerDelegate.this);
                i1 = TimePickerSpinnerDelegate._2D_get2(TimePickerSpinnerDelegate.this).getMinValue();
                j1 = TimePickerSpinnerDelegate._2D_get2(TimePickerSpinnerDelegate.this).getMaxValue();
                if(k != j1 || l != i1) goto _L2; else goto _L1
_L1:
                k = TimePickerSpinnerDelegate._2D_get0(TimePickerSpinnerDelegate.this).getValue() + 1;
                if(!is24Hour() && k == 12)
                {
                    TimePickerSpinnerDelegate._2D_set0(TimePickerSpinnerDelegate.this, TimePickerSpinnerDelegate._2D_get1(TimePickerSpinnerDelegate.this) ^ true);
                    TimePickerSpinnerDelegate._2D_wrap1(TimePickerSpinnerDelegate.this);
                }
                TimePickerSpinnerDelegate._2D_get0(TimePickerSpinnerDelegate.this).setValue(k);
_L4:
                TimePickerSpinnerDelegate._2D_wrap0(TimePickerSpinnerDelegate.this);
                return;
_L2:
                if(k == i1 && l == j1)
                {
                    k = TimePickerSpinnerDelegate._2D_get0(TimePickerSpinnerDelegate.this).getValue() - 1;
                    if(!is24Hour() && k == 11)
                    {
                        TimePickerSpinnerDelegate._2D_set0(TimePickerSpinnerDelegate.this, TimePickerSpinnerDelegate._2D_get1(TimePickerSpinnerDelegate.this) ^ true);
                        TimePickerSpinnerDelegate._2D_wrap1(TimePickerSpinnerDelegate.this);
                    }
                    TimePickerSpinnerDelegate._2D_get0(TimePickerSpinnerDelegate.this).setValue(k);
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final TimePickerSpinnerDelegate this$0;

            
            {
                this$0 = TimePickerSpinnerDelegate.this;
                super();
            }
        }
);
        mMinuteSpinnerInput = (EditText)mMinuteSpinner.findViewById(0x1020344);
        mMinuteSpinnerInput.setImeOptions(5);
        mAmPmStrings = getAmPmStrings(context);
        context = mDelegator.findViewById(0x10201a4);
        if(context instanceof Button)
        {
            mAmPmSpinner = null;
            mAmPmSpinnerInput = null;
            mAmPmButton = (Button)context;
            mAmPmButton.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    view.requestFocus();
                    TimePickerSpinnerDelegate._2D_set0(TimePickerSpinnerDelegate.this, TimePickerSpinnerDelegate._2D_get1(TimePickerSpinnerDelegate.this) ^ true);
                    TimePickerSpinnerDelegate._2D_wrap1(TimePickerSpinnerDelegate.this);
                    TimePickerSpinnerDelegate._2D_wrap0(TimePickerSpinnerDelegate.this);
                }

                final TimePickerSpinnerDelegate this$0;

            
            {
                this$0 = TimePickerSpinnerDelegate.this;
                super();
            }
            }
);
        } else
        {
            mAmPmButton = null;
            mAmPmSpinner = (NumberPicker)context;
            mAmPmSpinner.setMinValue(0);
            mAmPmSpinner.setMaxValue(1);
            mAmPmSpinner.setDisplayedValues(mAmPmStrings);
            mAmPmSpinner.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

                public void onValueChange(NumberPicker numberpicker, int k, int l)
                {
                    TimePickerSpinnerDelegate._2D_wrap2(TimePickerSpinnerDelegate.this);
                    numberpicker.requestFocus();
                    TimePickerSpinnerDelegate._2D_set0(TimePickerSpinnerDelegate.this, TimePickerSpinnerDelegate._2D_get1(TimePickerSpinnerDelegate.this) ^ true);
                    TimePickerSpinnerDelegate._2D_wrap1(TimePickerSpinnerDelegate.this);
                    TimePickerSpinnerDelegate._2D_wrap0(TimePickerSpinnerDelegate.this);
                }

                final TimePickerSpinnerDelegate this$0;

            
            {
                this$0 = TimePickerSpinnerDelegate.this;
                super();
            }
            }
);
            mAmPmSpinnerInput = (EditText)mAmPmSpinner.findViewById(0x1020344);
            mAmPmSpinnerInput.setImeOptions(6);
        }
        if(isAmPmAtStart())
        {
            timepicker = (ViewGroup)timepicker.findViewById(0x102044f);
            timepicker.removeView(context);
            timepicker.addView(context, 0);
            timepicker = (android.view.ViewGroup.MarginLayoutParams)context.getLayoutParams();
            i = timepicker.getMarginStart();
            j = timepicker.getMarginEnd();
            if(i != j)
            {
                timepicker.setMarginStart(j);
                timepicker.setMarginEnd(i);
            }
        }
        getHourFormatData();
        updateHourControl();
        updateMinuteControl();
        updateAmPmControl();
        mTempCalendar = Calendar.getInstance(mLocale);
        setHour(mTempCalendar.get(11));
        setMinute(mTempCalendar.get(12));
        if(!isEnabled())
            setEnabled(false);
        setContentDescriptions();
        if(mDelegator.getImportantForAccessibility() == 0)
            mDelegator.setImportantForAccessibility(1);
    }

    public static String[] getAmPmStrings(Context context)
    {
        Object obj = LocaleData.get(context.getResources().getConfiguration().locale);
        if(((LocaleData) (obj)).amPm[0].length() > 4)
            context = ((LocaleData) (obj)).narrowAm;
        else
            context = ((LocaleData) (obj)).amPm[0];
        if(((LocaleData) (obj)).amPm[1].length() > 4)
            obj = ((LocaleData) (obj)).narrowPm;
        else
            obj = ((LocaleData) (obj)).amPm[1];
        return (new String[] {
            context, obj
        });
    }

    private void getHourFormatData()
    {
        int j;
        int k;
        java.util.Locale locale = mLocale;
        String s;
        int i;
        if(mIs24HourView)
            s = "Hm";
        else
            s = "hm";
        s = DateFormat.getBestDateTimePattern(locale, s);
        i = s.length();
        mHourWithTwoDigit = false;
        j = 0;
_L7:
        if(j >= i) goto _L2; else goto _L1
_L1:
        k = s.charAt(j);
          goto _L3
_L5:
        mHourFormat = (char)k;
        if(j + 1 < i && k == s.charAt(j + 1))
            mHourWithTwoDigit = true;
_L2:
        return;
_L3:
        if(k == 72 || k == 104 || k == 75 || k == 107) goto _L5; else goto _L4
_L4:
        j++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private boolean isAmPmAtStart()
    {
        return DateFormat.getBestDateTimePattern(mLocale, "hm").startsWith("a");
    }

    private void onTimeChanged()
    {
        mDelegator.sendAccessibilityEvent(4);
        if(mOnTimeChangedListener != null)
            mOnTimeChangedListener.onTimeChanged(mDelegator, getHour(), getMinute());
        if(mAutoFillChangeListener != null)
            mAutoFillChangeListener.onTimeChanged(mDelegator, getHour(), getMinute());
    }

    private void setContentDescriptions()
    {
        trySetContentDescription(mMinuteSpinner, 0x10202a7, 0x1040640);
        trySetContentDescription(mMinuteSpinner, 0x102021e, 0x104063a);
        trySetContentDescription(mHourSpinner, 0x10202a7, 0x104063f);
        trySetContentDescription(mHourSpinner, 0x102021e, 0x1040639);
        if(mAmPmSpinner != null)
        {
            trySetContentDescription(mAmPmSpinner, 0x10202a7, 0x1040641);
            trySetContentDescription(mAmPmSpinner, 0x102021e, 0x104063b);
        }
    }

    private void setCurrentHour(int i, boolean flag)
    {
        int j;
        if(i == getHour())
            return;
        resetAutofilledValue();
        j = i;
        if(is24Hour()) goto _L2; else goto _L1
_L1:
        if(i < 12) goto _L4; else goto _L3
_L3:
        mIsAm = false;
        j = i;
        if(i > 12)
            j = i - 12;
_L6:
        updateAmPmControl();
_L2:
        mHourSpinner.setValue(j);
        if(flag)
            onTimeChanged();
        return;
_L4:
        mIsAm = true;
        j = i;
        if(i == 0)
            j = 12;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void setCurrentMinute(int i, boolean flag)
    {
        if(i == getMinute())
            return;
        resetAutofilledValue();
        mMinuteSpinner.setValue(i);
        if(flag)
            onTimeChanged();
    }

    private void setDividerText()
    {
        String s;
        int i;
        int k;
        if(mIs24HourView)
            s = "Hm";
        else
            s = "hm";
        s = DateFormat.getBestDateTimePattern(mLocale, s);
        i = s.lastIndexOf('H');
        k = i;
        if(i == -1)
            k = s.lastIndexOf('h');
        if(k == -1)
        {
            s = ":";
        } else
        {
            int j = s.indexOf('m', k + 1);
            if(j == -1)
                s = Character.toString(s.charAt(k + 1));
            else
                s = s.substring(k + 1, j);
        }
        mDivider.setText(s);
    }

    private void trySetContentDescription(View view, int i, int j)
    {
        view = view.findViewById(i);
        if(view != null)
            view.setContentDescription(mContext.getString(j));
    }

    private void updateAmPmControl()
    {
        if(is24Hour())
        {
            if(mAmPmSpinner != null)
                mAmPmSpinner.setVisibility(8);
            else
                mAmPmButton.setVisibility(8);
        } else
        {
            int i;
            if(mIsAm)
                i = 0;
            else
                i = 1;
            if(mAmPmSpinner != null)
            {
                mAmPmSpinner.setValue(i);
                mAmPmSpinner.setVisibility(0);
            } else
            {
                mAmPmButton.setText(mAmPmStrings[i]);
                mAmPmButton.setVisibility(0);
            }
        }
        mDelegator.sendAccessibilityEvent(4);
    }

    private void updateHourControl()
    {
        NumberPicker numberpicker;
        NumberPicker.Formatter formatter;
        if(is24Hour())
        {
            if(mHourFormat == 'k')
            {
                mHourSpinner.setMinValue(1);
                mHourSpinner.setMaxValue(24);
            } else
            {
                mHourSpinner.setMinValue(0);
                mHourSpinner.setMaxValue(23);
            }
        } else
        if(mHourFormat == 'K')
        {
            mHourSpinner.setMinValue(0);
            mHourSpinner.setMaxValue(11);
        } else
        {
            mHourSpinner.setMinValue(1);
            mHourSpinner.setMaxValue(12);
        }
        numberpicker = mHourSpinner;
        if(mHourWithTwoDigit)
            formatter = NumberPicker.getTwoDigitFormatter();
        else
            formatter = null;
        numberpicker.setFormatter(formatter);
    }

    private void updateInputState()
    {
        InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
        if(inputmethodmanager == null) goto _L2; else goto _L1
_L1:
        if(!inputmethodmanager.isActive(mHourSpinnerInput)) goto _L4; else goto _L3
_L3:
        mHourSpinnerInput.clearFocus();
        inputmethodmanager.hideSoftInputFromWindow(mDelegator.getWindowToken(), 0);
_L2:
        return;
_L4:
        if(inputmethodmanager.isActive(mMinuteSpinnerInput))
        {
            mMinuteSpinnerInput.clearFocus();
            inputmethodmanager.hideSoftInputFromWindow(mDelegator.getWindowToken(), 0);
        } else
        if(inputmethodmanager.isActive(mAmPmSpinnerInput))
        {
            mAmPmSpinnerInput.clearFocus();
            inputmethodmanager.hideSoftInputFromWindow(mDelegator.getWindowToken(), 0);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void updateMinuteControl()
    {
        if(is24Hour())
            mMinuteSpinnerInput.setImeOptions(6);
        else
            mMinuteSpinnerInput.setImeOptions(5);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        onPopulateAccessibilityEvent(accessibilityevent);
        return true;
    }

    public View getAmView()
    {
        return mAmPmSpinnerInput;
    }

    public int getBaseline()
    {
        return mHourSpinner.getBaseline();
    }

    public int getHour()
    {
        int i = mHourSpinner.getValue();
        if(is24Hour())
            return i;
        if(mIsAm)
            return i % 12;
        else
            return i % 12 + 12;
    }

    public View getHourView()
    {
        return mHourSpinnerInput;
    }

    public int getMinute()
    {
        return mMinuteSpinner.getValue();
    }

    public View getMinuteView()
    {
        return mMinuteSpinnerInput;
    }

    public View getPmView()
    {
        return mAmPmSpinnerInput;
    }

    public boolean is24Hour()
    {
        return mIs24HourView;
    }

    public boolean isEnabled()
    {
        return mIsEnabled;
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        char c;
        String s;
        if(mIs24HourView)
            c = '\201';
        else
            c = 'A';
        mTempCalendar.set(11, getHour());
        mTempCalendar.set(12, getMinute());
        s = DateUtils.formatDateTime(mContext, mTempCalendar.getTimeInMillis(), c);
        accessibilityevent.getText().add(s);
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        if(parcelable instanceof TimePicker.AbstractTimePickerDelegate.SavedState)
        {
            parcelable = (TimePicker.AbstractTimePickerDelegate.SavedState)parcelable;
            setHour(parcelable.getHour());
            setMinute(parcelable.getMinute());
        }
    }

    public Parcelable onSaveInstanceState(Parcelable parcelable)
    {
        return new TimePicker.AbstractTimePickerDelegate.SavedState(parcelable, getHour(), getMinute(), is24Hour());
    }

    public void setDate(int i, int j)
    {
        setCurrentHour(i, false);
        setCurrentMinute(j, false);
        onTimeChanged();
    }

    public void setEnabled(boolean flag)
    {
        mMinuteSpinner.setEnabled(flag);
        if(mDivider != null)
            mDivider.setEnabled(flag);
        mHourSpinner.setEnabled(flag);
        if(mAmPmSpinner != null)
            mAmPmSpinner.setEnabled(flag);
        else
            mAmPmButton.setEnabled(flag);
        mIsEnabled = flag;
    }

    public void setHour(int i)
    {
        setCurrentHour(i, true);
    }

    public void setIs24Hour(boolean flag)
    {
        if(mIs24HourView == flag)
        {
            return;
        } else
        {
            int i = getHour();
            mIs24HourView = flag;
            getHourFormatData();
            updateHourControl();
            setCurrentHour(i, false);
            updateMinuteControl();
            updateAmPmControl();
            return;
        }
    }

    public void setMinute(int i)
    {
        setCurrentMinute(i, true);
    }

    public boolean validateInput()
    {
        return true;
    }

    private static final boolean DEFAULT_ENABLED_STATE = true;
    private static final int HOURS_IN_HALF_DAY = 12;
    private final Button mAmPmButton;
    private final NumberPicker mAmPmSpinner;
    private final EditText mAmPmSpinnerInput;
    private final String mAmPmStrings[];
    private final TextView mDivider;
    private char mHourFormat;
    private final NumberPicker mHourSpinner;
    private final EditText mHourSpinnerInput;
    private boolean mHourWithTwoDigit;
    private boolean mIs24HourView;
    private boolean mIsAm;
    private boolean mIsEnabled;
    private final NumberPicker mMinuteSpinner;
    private final EditText mMinuteSpinnerInput;
    private final Calendar mTempCalendar;
}
