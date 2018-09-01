// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.*;
import android.content.*;
import android.net.Uri;
import android.os.Bundle;

// Referenced classes of package com.android.internal.app:
//            ChooserActivity

public class ResolverTargetActionsDialogFragment extends DialogFragment
    implements android.content.DialogInterface.OnClickListener
{

    public ResolverTargetActionsDialogFragment()
    {
    }

    public ResolverTargetActionsDialogFragment(CharSequence charsequence, ComponentName componentname, boolean flag)
    {
        Bundle bundle = new Bundle();
        bundle.putCharSequence("title", charsequence);
        bundle.putParcelable("componentName", componentname);
        bundle.putBoolean("pinned", flag);
        setArguments(bundle);
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        ComponentName componentname = (ComponentName)getArguments().getParcelable("componentName");
        i;
        JVM INSTR tableswitch 0 1: default 36
    //                   0 41
    //                   1 119;
           goto _L1 _L2 _L3
_L1:
        dismiss();
        return;
_L2:
        dialoginterface = ChooserActivity.getPinnedSharedPrefs(getContext());
        String s = componentname.flattenToString();
        if(dialoginterface.getBoolean(componentname.flattenToString(), false))
            dialoginterface.edit().remove(s).apply();
        else
            dialoginterface.edit().putBoolean(s, true).apply();
        getActivity().recreate();
        continue; /* Loop/switch isn't completed */
_L3:
        startActivity((new Intent()).setAction("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", componentname.getPackageName(), null)).addFlags(0x80000));
        if(true) goto _L1; else goto _L4
_L4:
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        bundle = getArguments();
        int i;
        if(bundle.getBoolean("pinned", false))
            i = 0x1070062;
        else
            i = 0x1070061;
        return (new android.app.AlertDialog.Builder(getContext())).setCancelable(true).setItems(i, this).setTitle(bundle.getCharSequence("title")).create();
    }

    private static final int APP_INFO_INDEX = 1;
    private static final String NAME_KEY = "componentName";
    private static final String PINNED_KEY = "pinned";
    private static final String TITLE_KEY = "title";
    private static final int TOGGLE_PIN_INDEX = 0;
}
