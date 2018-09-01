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
import android.widget.TimePicker;

// Referenced classes of package android.app:
//            AlertDialog

public class TimePickerDialog extends AlertDialog
    implements android.content.DialogInterface.OnClickListener, android.widget.TimePicker.OnTimeChangedListener
{
    public static interface OnTimeSetListener
    {

        public abstract void onTimeSet(TimePicker timepicker, int i, int j);
    }


    static TimePicker _2D_get0(TimePickerDialog timepickerdialog)
    {
        return timepickerdialog.mTimePicker;
    }

    public TimePickerDialog(Context context, int i, OnTimeSetListener ontimesetlistener, int j, int k, boolean flag)
    {
        super(context, resolveDialogTheme(context, i));
        mTimeSetListener = ontimesetlistener;
        mInitialHourOfDay = j;
        mInitialMinute = k;
        mIs24HourView = flag;
        context = getContext();
        ontimesetlistener = LayoutInflater.from(context).inflate(0x109010f, null);
        setView(ontimesetlistener);
        setButton(-1, context.getString(0x104000a), this);
        setButton(-2, context.getString(0x1040000), this);
        setButtonPanelLayoutHint(1);
        mTimePicker = (TimePicker)ontimesetlistener.findViewById(0x102044e);
        mTimePicker.setIs24HourView(Boolean.valueOf(mIs24HourView));
        mTimePicker.setCurrentHour(Integer.valueOf(mInitialHourOfDay));
        mTimePicker.setCurrentMinute(Integer.valueOf(mInitialMinute));
        mTimePicker.setOnTimeChangedListener(this);
    }

    public TimePickerDialog(Context context, OnTimeSetListener ontimesetlistener, int i, int j, boolean flag)
    {
        this(context, 0, ontimesetlistener, i, j, flag);
    }

    static int resolveDialogTheme(Context context, int i)
    {
        if(i == 0)
        {
            TypedValue typedvalue = new TypedValue();
            context.getTheme().resolveAttribute(0x101049e, typedvalue, true);
            return typedvalue.resourceId;
        } else
        {
            return i;
        }
    }

    public TimePicker getTimePicker()
    {
        return mTimePicker;
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        i;
        JVM INSTR tableswitch -2 -1: default 24
    //                   -2 68
    //                   -1 25;
           goto _L1 _L2 _L3
_L1:
        return;
_L3:
        if(mTimeSetListener != null)
            mTimeSetListener.onTimeSet(mTimePicker, mTimePicker.getCurrentHour().intValue(), mTimePicker.getCurrentMinute().intValue());
        continue; /* Loop/switch isn't completed */
_L2:
        cancel();
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        int i = bundle.getInt("hour");
        int j = bundle.getInt("minute");
        mTimePicker.setIs24HourView(Boolean.valueOf(bundle.getBoolean("is24hour")));
        mTimePicker.setCurrentHour(Integer.valueOf(i));
        mTimePicker.setCurrentMinute(Integer.valueOf(j));
    }

    public Bundle onSaveInstanceState()
    {
        Bundle bundle = super.onSaveInstanceState();
        bundle.putInt("hour", mTimePicker.getCurrentHour().intValue());
        bundle.putInt("minute", mTimePicker.getCurrentMinute().intValue());
        bundle.putBoolean("is24hour", mTimePicker.is24HourView());
        return bundle;
    }

    public void onTimeChanged(TimePicker timepicker, int i, int j)
    {
    }

    public void show()
    {
        super.show();
        getButton(-1).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(TimePickerDialog._2D_get0(TimePickerDialog.this).validateInput())
                {
                    TimePickerDialog.this.onClick(TimePickerDialog.this, -1);
                    dismiss();
                }
            }

            final TimePickerDialog this$0;

            
            {
                this$0 = TimePickerDialog.this;
                super();
            }
        }
);
    }

    public void updateTime(int i, int j)
    {
        mTimePicker.setCurrentHour(Integer.valueOf(i));
        mTimePicker.setCurrentMinute(Integer.valueOf(j));
    }

    private static final String HOUR = "hour";
    private static final String IS_24_HOUR = "is24hour";
    private static final String MINUTE = "minute";
    private final int mInitialHourOfDay;
    private final int mInitialMinute;
    private final boolean mIs24HourView;
    private final TimePicker mTimePicker;
    private final OnTimeSetListener mTimeSetListener;
}
