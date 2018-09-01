// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.*;
import android.util.AttributeSet;
import android.util.MathUtils;
import android.view.View;

// Referenced classes of package android.widget:
//            RelativeLayout, EditText, TextView, Spinner, 
//            TimePicker, ArrayAdapter, TimePickerClockDelegate, AdapterView

public class TextInputTimePickerView extends RelativeLayout
{
    static interface OnValueTypedListener
    {

        public abstract void onValueChanged(int i, int j);
    }


    static OnValueTypedListener _2D_get0(TextInputTimePickerView textinputtimepickerview)
    {
        return textinputtimepickerview.mListener;
    }

    static boolean _2D_wrap0(TextInputTimePickerView textinputtimepickerview, String s)
    {
        return textinputtimepickerview.parseAndSetHourInternal(s);
    }

    static boolean _2D_wrap1(TextInputTimePickerView textinputtimepickerview, String s)
    {
        return textinputtimepickerview.parseAndSetMinuteInternal(s);
    }

    public TextInputTimePickerView(Context context)
    {
        this(context, null);
    }

    public TextInputTimePickerView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public TextInputTimePickerView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public TextInputTimePickerView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        inflate(context, 0x1090114, this);
        mHourEditText = (EditText)findViewById(0x10202b0);
        mMinuteEditText = (EditText)findViewById(0x10202b1);
        mInputSeparatorView = (TextView)findViewById(0x10202b3);
        mErrorLabel = (TextView)findViewById(0x10202cd);
        mHourLabel = (TextView)findViewById(0x10202ce);
        mMinuteLabel = (TextView)findViewById(0x10202cf);
        mHourEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
                TextInputTimePickerView._2D_wrap0(TextInputTimePickerView.this, editable.toString());
            }

            public void beforeTextChanged(CharSequence charsequence, int k, int l, int i1)
            {
            }

            public void onTextChanged(CharSequence charsequence, int k, int l, int i1)
            {
            }

            final TextInputTimePickerView this$0;

            
            {
                this$0 = TextInputTimePickerView.this;
                super();
            }
        }
);
        mMinuteEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
                TextInputTimePickerView._2D_wrap1(TextInputTimePickerView.this, editable.toString());
            }

            public void beforeTextChanged(CharSequence charsequence, int k, int l, int i1)
            {
            }

            public void onTextChanged(CharSequence charsequence, int k, int l, int i1)
            {
            }

            final TextInputTimePickerView this$0;

            
            {
                this$0 = TextInputTimePickerView.this;
                super();
            }
        }
);
        mAmPmSpinner = (Spinner)findViewById(0x10201a6);
        attributeset = TimePicker.getAmPmStrings(context);
        context = new ArrayAdapter(context, 0x1090009);
        context.add(TimePickerClockDelegate.obtainVerbatim(attributeset[0]));
        context.add(TimePickerClockDelegate.obtainVerbatim(attributeset[1]));
        mAmPmSpinner.setAdapter(context);
        mAmPmSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView adapterview, View view, int k, long l)
            {
                if(k == 0)
                    TextInputTimePickerView._2D_get0(TextInputTimePickerView.this).onValueChanged(2, 0);
                else
                    TextInputTimePickerView._2D_get0(TextInputTimePickerView.this).onValueChanged(2, 1);
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            final TextInputTimePickerView this$0;

            
            {
                this$0 = TextInputTimePickerView.this;
                super();
            }
        }
);
    }

    private int getHourOfDayFromLocalizedHour(int i)
    {
        int j = i;
        if(!mIs24Hour) goto _L2; else goto _L1
_L1:
        int k;
        k = j;
        if(!mHourFormatStartsAtZero)
        {
            k = j;
            if(i == 24)
                k = 0;
        }
_L4:
        return k;
_L2:
        int l = j;
        if(!mHourFormatStartsAtZero)
        {
            l = j;
            if(i == 12)
                l = 0;
        }
        k = l;
        if(mAmPmSpinner.getSelectedItemPosition() == 1)
            k = l + 12;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private boolean isValidLocalizedHour(int i)
    {
        boolean flag = false;
        int j;
        byte byte0;
        boolean flag1;
        if(mHourFormatStartsAtZero)
            j = 0;
        else
            j = 1;
        if(mIs24Hour)
            byte0 = 23;
        else
            byte0 = 11;
        flag1 = flag;
        if(i >= j)
        {
            flag1 = flag;
            if(i <= byte0 + j)
                flag1 = true;
        }
        return flag1;
    }

    private boolean parseAndSetHourInternal(String s)
    {
        int i;
        i = Integer.parseInt(s);
        if(isValidLocalizedHour(i))
            break MISSING_BLOCK_LABEL_70;
        int j;
        int k;
        if(mHourFormatStartsAtZero)
            j = 0;
        else
            j = 1;
        if(mIs24Hour)
            k = 23;
        else
            k = j + 11;
        try
        {
            mListener.onValueChanged(0, getHourOfDayFromLocalizedHour(MathUtils.constrain(i, j, k)));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        return false;
        mListener.onValueChanged(0, getHourOfDayFromLocalizedHour(i));
        return true;
    }

    private boolean parseAndSetMinuteInternal(String s)
    {
        int i;
        try
        {
            i = Integer.parseInt(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        if(i >= 0 && i <= 59)
            break MISSING_BLOCK_LABEL_34;
        mListener.onValueChanged(1, MathUtils.constrain(i, 0, 59));
        return false;
        mListener.onValueChanged(1, i);
        return true;
    }

    private void setError(boolean flag)
    {
        byte byte0 = 4;
        mErrorShowing = flag;
        TextView textview = mErrorLabel;
        int i;
        if(flag)
            i = 0;
        else
            i = 4;
        textview.setVisibility(i);
        textview = mHourLabel;
        if(flag)
            i = 4;
        else
            i = 0;
        textview.setVisibility(i);
        textview = mMinuteLabel;
        if(flag)
            i = byte0;
        else
            i = 0;
        textview.setVisibility(i);
    }

    void setHourFormat(int i)
    {
        mHourEditText.setFilters(new InputFilter[] {
            new android.text.InputFilter.LengthFilter(i)
        });
        mMinuteEditText.setFilters(new InputFilter[] {
            new android.text.InputFilter.LengthFilter(i)
        });
        android.os.LocaleList localelist = mContext.getResources().getConfiguration().getLocales();
        mHourEditText.setImeHintLocales(localelist);
        mMinuteEditText.setImeHintLocales(localelist);
    }

    void setListener(OnValueTypedListener onvaluetypedlistener)
    {
        mListener = onvaluetypedlistener;
    }

    void updateSeparator(String s)
    {
        mInputSeparatorView.setText(s);
    }

    void updateTextInputValues(int i, int j, int k, boolean flag, boolean flag1)
    {
        mIs24Hour = flag;
        mHourFormatStartsAtZero = flag1;
        Spinner spinner = mAmPmSpinner;
        byte byte0;
        if(flag)
            byte0 = 4;
        else
            byte0 = 0;
        spinner.setVisibility(byte0);
        if(k == 0)
            mAmPmSpinner.setSelection(0);
        else
            mAmPmSpinner.setSelection(1);
        mHourEditText.setText(String.format("%d", new Object[] {
            Integer.valueOf(i)
        }));
        mMinuteEditText.setText(String.format("%d", new Object[] {
            Integer.valueOf(j)
        }));
        if(mErrorShowing)
            validateInput();
    }

    boolean validateInput()
    {
        boolean flag;
        if(parseAndSetHourInternal(mHourEditText.getText().toString()))
            flag = parseAndSetMinuteInternal(mMinuteEditText.getText().toString());
        else
            flag = false;
        setError(flag ^ true);
        return flag;
    }

    private static final int AM = 0;
    public static final int AMPM = 2;
    public static final int HOURS = 0;
    public static final int MINUTES = 1;
    private static final int PM = 1;
    private final Spinner mAmPmSpinner;
    private final TextView mErrorLabel;
    private boolean mErrorShowing;
    private final EditText mHourEditText;
    private boolean mHourFormatStartsAtZero;
    private final TextView mHourLabel;
    private final TextView mInputSeparatorView;
    private boolean mIs24Hour;
    private OnValueTypedListener mListener;
    private final EditText mMinuteEditText;
    private final TextView mMinuteLabel;
}
