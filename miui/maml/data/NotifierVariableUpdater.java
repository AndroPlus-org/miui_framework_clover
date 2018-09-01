// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.content.Context;
import android.content.Intent;
import miui.maml.NotifierManager;
import miui.maml.ScreenContext;

// Referenced classes of package miui.maml.data:
//            VariableUpdater, VariableUpdaterManager

public abstract class NotifierVariableUpdater extends VariableUpdater
    implements miui.maml.NotifierManager.OnNotifyListener
{

    public NotifierVariableUpdater(VariableUpdaterManager variableupdatermanager, String s)
    {
        super(variableupdatermanager);
        mType = s;
        mNotifierManager = NotifierManager.getInstance(getContext().mContext);
    }

    public void finish()
    {
        mNotifierManager.releaseNotifier(mType, this);
    }

    public void init()
    {
        mNotifierManager.acquireNotifier(mType, this);
    }

    public abstract void onNotify(Context context, Intent intent, Object obj);

    public void pause()
    {
        mNotifierManager.pause(mType, this);
    }

    public void resume()
    {
        mNotifierManager.resume(mType, this);
    }

    protected NotifierManager mNotifierManager;
    private String mType;
}
