// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

// Referenced classes of package com.android.internal.app:
//            MediaRouteChooserDialog

public class MediaRouteChooserDialogFragment extends DialogFragment
{

    public MediaRouteChooserDialogFragment()
    {
        int i;
        if(MediaRouteChooserDialog.isLightTheme(getContext()))
            i = 0x1030132;
        else
            i = 0x103012e;
        setCancelable(true);
        setStyle(0, i);
    }

    public int getRouteTypes()
    {
        Bundle bundle = getArguments();
        int i;
        if(bundle != null)
            i = bundle.getInt("routeTypes");
        else
            i = 0;
        return i;
    }

    public MediaRouteChooserDialog onCreateChooserDialog(Context context, Bundle bundle)
    {
        return new MediaRouteChooserDialog(context, getTheme());
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        bundle = onCreateChooserDialog(getActivity(), bundle);
        bundle.setRouteTypes(getRouteTypes());
        bundle.setExtendedSettingsClickListener(mExtendedSettingsClickListener);
        return bundle;
    }

    public void setExtendedSettingsClickListener(android.view.View.OnClickListener onclicklistener)
    {
        if(onclicklistener != mExtendedSettingsClickListener)
        {
            mExtendedSettingsClickListener = onclicklistener;
            MediaRouteChooserDialog mediaroutechooserdialog = (MediaRouteChooserDialog)getDialog();
            if(mediaroutechooserdialog != null)
                mediaroutechooserdialog.setExtendedSettingsClickListener(onclicklistener);
        }
    }

    public void setRouteTypes(int i)
    {
        if(i != getRouteTypes())
        {
            Bundle bundle = getArguments();
            Object obj = bundle;
            if(bundle == null)
                obj = new Bundle();
            ((Bundle) (obj)).putInt("routeTypes", i);
            setArguments(((Bundle) (obj)));
            obj = (MediaRouteChooserDialog)getDialog();
            if(obj != null)
                ((MediaRouteChooserDialog) (obj)).setRouteTypes(i);
        }
    }

    private final String ARGUMENT_ROUTE_TYPES = "routeTypes";
    private android.view.View.OnClickListener mExtendedSettingsClickListener;
}
