// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.admin;

import android.content.Intent;
import java.util.List;

public abstract class DevicePolicyManagerInternal
{
    public static interface OnCrossProfileWidgetProvidersChangeListener
    {

        public abstract void onCrossProfileWidgetProvidersChanged(int i, List list);
    }


    public DevicePolicyManagerInternal()
    {
    }

    public abstract void addOnCrossProfileWidgetProvidersChangeListener(OnCrossProfileWidgetProvidersChangeListener oncrossprofilewidgetproviderschangelistener);

    public abstract Intent createShowAdminSupportIntent(int i, boolean flag);

    public abstract Intent createUserRestrictionSupportIntent(int i, String s);

    public abstract List getCrossProfileWidgetProviders(int i);

    public abstract boolean isActiveAdminWithPolicy(int i, int j);
}
