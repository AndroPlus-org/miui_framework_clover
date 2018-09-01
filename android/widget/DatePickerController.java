// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import java.util.Calendar;

// Referenced classes of package android.widget:
//            OnDateChangedListener

interface DatePickerController
{

    public abstract Calendar getSelectedDay();

    public abstract void onYearSelected(int i);

    public abstract void registerOnDateChangedListener(OnDateChangedListener ondatechangedlistener);

    public abstract void tryVibrate();
}
