// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.joyose;

import android.content.Context;

// Referenced classes of package com.miui.joyose:
//            JoyoseProxy

public abstract class JoyoseManager
{

    public JoyoseManager()
    {
    }

    public boolean checkSceneWorkState(long l)
    {
        return JoyoseProxy.checkSceneWorkState(l);
    }

    public long getJoyoseSupportSceneList(Context context)
    {
        return JoyoseProxy.getJoyoseSupportSceneList(context);
    }

    public static final String EXTRA_CUR_PLACE = "cur_place";
    public static final String EXTRA_CUR_STATE = "cur_state";
    public static final String EXTRA_PRE_STATE = "pre_state";
    public static final long SCENE_IN_CYCLE_MODE = 8L;
    public static final long SCENE_IN_HOME_MODE = 2L;
    public static final long SCENE_IN_UNIT_MODE = 4L;
    public static final long SCENE_SLEEP_MODE = 1L;
    public static final String SLEEP_MODE_CHANGE_ACTION = "action_sleep_state_changed";
    public static final int SLEEP_MODE_STATE_DEEP_SLEEP = 3;
    public static final int SLEEP_MODE_STATE_LIGHT_EXIT_SLEEP = 4;
    public static final int SLEEP_MODE_STATE_LIGHT_SLEEP = 2;
    public static final int SLEEP_MODE_STATE_NO_SLEEP = 1;
    public static final int SLEEP_MODE_STATE_UNKNOW = 0;
    public static final int USER_IN_HOME = 1;
    public static final int USER_IN_UNIT = 2;
    public static final String USER_PLACE_CHANGE_ACTION = "action_user_place_changed";
    public static final int USER_UNCERTAIN_IN_HOME = 4;
    public static final int USER_UNCERTAIN_IN_UNIT = 8;
    public static final int USER_UNKNOW_PLACE = 0;
}
