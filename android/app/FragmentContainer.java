// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

// Referenced classes of package android.app:
//            Fragment

public abstract class FragmentContainer
{

    public FragmentContainer()
    {
    }

    public Fragment instantiate(Context context, String s, Bundle bundle)
    {
        return Fragment.instantiate(context, s, bundle);
    }

    public abstract View onFindViewById(int i);

    public abstract boolean onHasView();
}
