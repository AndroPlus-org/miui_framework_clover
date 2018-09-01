// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import miui.maml.ScreenContext;
import miui.maml.ScreenElementRoot;

// Referenced classes of package miui.maml.data:
//            VariableUpdaterManager

public class VariableUpdater
{

    public VariableUpdater(VariableUpdaterManager variableupdatermanager)
    {
        mVariableUpdaterManager = variableupdatermanager;
    }

    public void finish()
    {
    }

    protected final ScreenContext getContext()
    {
        return getRoot().getContext();
    }

    protected final ScreenElementRoot getRoot()
    {
        return mVariableUpdaterManager.getRoot();
    }

    public void init()
    {
    }

    public void pause()
    {
    }

    public void resume()
    {
    }

    public void tick(long l)
    {
    }

    private VariableUpdaterManager mVariableUpdaterManager;
}
