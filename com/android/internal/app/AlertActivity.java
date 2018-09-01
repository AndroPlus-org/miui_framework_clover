// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;

// Referenced classes of package com.android.internal.app:
//            AlertController

public abstract class AlertActivity extends Activity
    implements DialogInterface
{

    public AlertActivity()
    {
    }

    public static boolean dispatchPopulateAccessibilityEvent(Activity activity, AccessibilityEvent accessibilityevent)
    {
        accessibilityevent.setClassName(android/app/Dialog.getName());
        accessibilityevent.setPackageName(activity.getPackageName());
        activity = activity.getWindow().getAttributes();
        boolean flag;
        if(((android.view.ViewGroup.LayoutParams) (activity)).width == -1)
        {
            if(((android.view.ViewGroup.LayoutParams) (activity)).height == -1)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        accessibilityevent.setFullScreen(flag);
        return false;
    }

    public void cancel()
    {
        finish();
    }

    public void dismiss()
    {
        if(!isFinishing())
            finish();
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        return dispatchPopulateAccessibilityEvent(((Activity) (this)), accessibilityevent);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mAlert = AlertController.create(this, this, getWindow());
        mAlertParams = new AlertController.AlertParams(this);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(mAlert.onKeyDown(i, keyevent))
            return true;
        else
            return super.onKeyDown(i, keyevent);
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(mAlert.onKeyUp(i, keyevent))
            return true;
        else
            return super.onKeyUp(i, keyevent);
    }

    protected void setupAlert()
    {
        mAlert.installContent(mAlertParams);
    }

    protected AlertController mAlert;
    protected AlertController.AlertParams mAlertParams;
}
