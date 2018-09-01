// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

// Referenced classes of package com.android.internal.app:
//            MediaRouteControllerDialog

public class MediaRouteControllerDialogFragment extends DialogFragment
{

    public MediaRouteControllerDialogFragment()
    {
        setCancelable(true);
    }

    public MediaRouteControllerDialog onCreateControllerDialog(Context context, Bundle bundle)
    {
        return new MediaRouteControllerDialog(context, getTheme());
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        return onCreateControllerDialog(getContext(), bundle);
    }
}
