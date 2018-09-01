// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.content.Context;
import android.content.Intent;
import miui.maml.ScreenContext;
import miui.maml.ScreenElementRoot;

// Referenced classes of package miui.maml.data:
//            NotifierVariableUpdater, IndexedVariable, VariableUpdaterManager

public class BatteryVariableUpdater extends NotifierVariableUpdater
{

    public BatteryVariableUpdater(VariableUpdaterManager variableupdatermanager)
    {
        super(variableupdatermanager, "android.intent.action.BATTERY_CHANGED");
        mBatteryLevel = new IndexedVariable("battery_level", getRoot().getContext().mVariables, true);
    }

    public void onNotify(Context context, Intent intent, Object obj)
    {
        int i = 100;
        if(intent.getAction().equals("android.intent.action.BATTERY_CHANGED"))
        {
            int j = intent.getIntExtra("level", -1);
            if(j != -1 && mLevel != j)
            {
                context = mBatteryLevel;
                if(j < 100)
                    i = j;
                context.set(i);
                mLevel = j;
                getRoot().requestUpdate();
            }
        }
    }

    public static final String USE_TAG = "Battery";
    private IndexedVariable mBatteryLevel;
    private int mLevel;
}
