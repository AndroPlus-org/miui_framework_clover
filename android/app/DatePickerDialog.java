// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import java.util.Calendar;

// Referenced classes of package android.app:
//            AlertDialog

public class DatePickerDialog extends AlertDialog
    implements android.content.DialogInterface.OnClickListener, android.widget.DatePicker.OnDateChangedListener
{
    public static interface OnDateSetListener
    {

        public abstract void onDateSet(DatePicker datepicker, int i, int j, int k);
    }


    public DatePickerDialog(Context context)
    {
        this(context, 0, null, Calendar.getInstance(), -1, -1, -1);
    }

    public DatePickerDialog(Context context, int i)
    {
        this(context, i, null, Calendar.getInstance(), -1, -1, -1);
    }

    public DatePickerDialog(Context context, int i, OnDateSetListener ondatesetlistener, int j, int k, int l)
    {
        this(context, i, ondatesetlistener, null, j, k, l);
    }

    private DatePickerDialog(Context context, int i, OnDateSetListener ondatesetlistener, Calendar calendar, int j, int k, int l)
    {
        super(context, resolveDialogTheme(context, i));
        mValidationCallback = new android.widget.DatePicker.ValidationCallback() {

            public void onValidationChanged(boolean flag)
            {
                Button button = getButton(-1);
                if(button != null)
                    button.setEnabled(flag);
            }

            final DatePickerDialog this$0;

            
            {
                this$0 = DatePickerDialog.this;
                super();
            }
        }
;
        context = getContext();
        View view = LayoutInflater.from(context).inflate(0x109004a, null);
        setView(view);
        setButton(-1, context.getString(0x104000a), this);
        setButton(-2, context.getString(0x1040000), this);
        setButtonPanelLayoutHint(1);
        if(calendar != null)
        {
            j = calendar.get(1);
            k = calendar.get(2);
            l = calendar.get(5);
        }
        mDatePicker = (DatePicker)view.findViewById(0x1020212);
        mDatePicker.init(j, k, l, this);
        mDatePicker.setValidationCallback(mValidationCallback);
        mDateSetListener = ondatesetlistener;
    }

    public DatePickerDialog(Context context, OnDateSetListener ondatesetlistener, int i, int j, int k)
    {
        this(context, 0, ondatesetlistener, null, i, j, k);
    }

    static int resolveDialogTheme(Context context, int i)
    {
        if(i == 0)
        {
            TypedValue typedvalue = new TypedValue();
            context.getTheme().resolveAttribute(0x10104ac, typedvalue, true);
            return typedvalue.resourceId;
        } else
        {
            return i;
        }
    }

    public DatePicker getDatePicker()
    {
        return mDatePicker;
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        i;
        JVM INSTR tableswitch -2 -1: default 24
    //                   -2 76
    //                   -1 25;
           goto _L1 _L2 _L3
_L1:
        return;
_L3:
        if(mDateSetListener != null)
        {
            mDatePicker.clearFocus();
            mDateSetListener.onDateSet(mDatePicker, mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
        }
        continue; /* Loop/switch isn't completed */
_L2:
        cancel();
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void onDateChanged(DatePicker datepicker, int i, int j, int k)
    {
        mDatePicker.init(i, j, k, this);
    }

    public void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        int i = bundle.getInt("year");
        int j = bundle.getInt("month");
        int k = bundle.getInt("day");
        mDatePicker.init(i, j, k, this);
    }

    public Bundle onSaveInstanceState()
    {
        Bundle bundle = super.onSaveInstanceState();
        bundle.putInt("year", mDatePicker.getYear());
        bundle.putInt("month", mDatePicker.getMonth());
        bundle.putInt("day", mDatePicker.getDayOfMonth());
        return bundle;
    }

    public void setOnDateSetListener(OnDateSetListener ondatesetlistener)
    {
        mDateSetListener = ondatesetlistener;
    }

    public void updateDate(int i, int j, int k)
    {
        mDatePicker.updateDate(i, j, k);
    }

    private static final String DAY = "day";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
    private final DatePicker mDatePicker;
    private OnDateSetListener mDateSetListener;
    private final android.widget.DatePicker.ValidationCallback mValidationCallback;
}
