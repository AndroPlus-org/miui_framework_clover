// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.LocaleList;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

// Referenced classes of package android.text.method:
//            TransformationMethod2

public class AllCapsTransformationMethod
    implements TransformationMethod2
{

    public AllCapsTransformationMethod(Context context)
    {
        mLocale = context.getResources().getConfiguration().getLocales().get(0);
    }

    public CharSequence getTransformation(CharSequence charsequence, View view)
    {
        if(!mEnabled)
        {
            Log.w("AllCapsTransformationMethod", "Caller did not enable length changes; not transforming text");
            return charsequence;
        }
        if(charsequence == null)
            return null;
        Locale locale = null;
        if(view instanceof TextView)
            locale = ((TextView)view).getTextLocale();
        view = locale;
        if(locale == null)
            view = mLocale;
        return TextUtils.toUpperCase(view, charsequence, charsequence instanceof Spanned);
    }

    public void onFocusChanged(View view, CharSequence charsequence, boolean flag, int i, Rect rect)
    {
    }

    public void setLengthChangesAllowed(boolean flag)
    {
        mEnabled = flag;
    }

    private static final String TAG = "AllCapsTransformationMethod";
    private boolean mEnabled;
    private Locale mLocale;
}
