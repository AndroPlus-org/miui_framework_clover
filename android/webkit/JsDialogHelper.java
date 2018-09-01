// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.net.MalformedURLException;
import java.net.URL;

// Referenced classes of package android.webkit:
//            URLUtil, WebChromeClient, JsPromptResult, WebView

public class JsDialogHelper
{
    private class CancelListener
        implements android.content.DialogInterface.OnCancelListener, android.content.DialogInterface.OnClickListener
    {

        public void onCancel(DialogInterface dialoginterface)
        {
            JsDialogHelper._2D_get0(JsDialogHelper.this).cancel();
        }

        public void onClick(DialogInterface dialoginterface, int i)
        {
            JsDialogHelper._2D_get0(JsDialogHelper.this).cancel();
        }

        final JsDialogHelper this$0;

        private CancelListener()
        {
            this$0 = JsDialogHelper.this;
            super();
        }

        CancelListener(CancelListener cancellistener)
        {
            this();
        }
    }

    private class PositiveListener
        implements android.content.DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            if(mEdit == null)
                JsDialogHelper._2D_get0(JsDialogHelper.this).confirm();
            else
                JsDialogHelper._2D_get0(JsDialogHelper.this).confirm(mEdit.getText().toString());
        }

        private final EditText mEdit;
        final JsDialogHelper this$0;

        public PositiveListener(EditText edittext)
        {
            this$0 = JsDialogHelper.this;
            super();
            mEdit = edittext;
        }
    }


    static JsPromptResult _2D_get0(JsDialogHelper jsdialoghelper)
    {
        return jsdialoghelper.mResult;
    }

    public JsDialogHelper(JsPromptResult jspromptresult, int i, String s, String s1, String s2)
    {
        mResult = jspromptresult;
        mDefaultValue = s;
        mMessage = s1;
        mType = i;
        mUrl = s2;
    }

    public JsDialogHelper(JsPromptResult jspromptresult, Message message)
    {
        mResult = jspromptresult;
        mDefaultValue = message.getData().getString("default");
        mMessage = message.getData().getString("message");
        mType = message.getData().getInt("type");
        mUrl = message.getData().getString("url");
    }

    private static boolean canShowAlertDialog(Context context)
    {
        return context instanceof Activity;
    }

    private String getJsDialogTitle(Context context)
    {
        String s = mUrl;
        if(URLUtil.isDataUrl(mUrl))
            context = context.getString(0x10402a2);
        else
            try
            {
                URL url = JVM INSTR new #94  <Class URL>;
                url.URL(mUrl);
                StringBuilder stringbuilder = JVM INSTR new #99  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                context = context.getString(0x10402a1, new Object[] {
                    stringbuilder.append(url.getProtocol()).append("://").append(url.getHost()).toString()
                });
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                context = s;
            }
        return context;
    }

    public boolean invokeCallback(WebChromeClient webchromeclient, WebView webview)
    {
        switch(mType)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unexpected type: ").append(mType).toString());

        case 1: // '\001'
            return webchromeclient.onJsAlert(webview, mUrl, mMessage, mResult);

        case 2: // '\002'
            return webchromeclient.onJsConfirm(webview, mUrl, mMessage, mResult);

        case 4: // '\004'
            return webchromeclient.onJsBeforeUnload(webview, mUrl, mMessage, mResult);

        case 3: // '\003'
            return webchromeclient.onJsPrompt(webview, mUrl, mMessage, mDefaultValue, mResult);
        }
    }

    public void showDialog(Context context)
    {
        if(!canShowAlertDialog(context))
        {
            Log.w("JsDialogHelper", "Cannot create a dialog, the WebView context is not an Activity");
            mResult.cancel();
            return;
        }
        String s;
        String s1;
        int i;
        int j;
        android.app.AlertDialog.Builder builder;
        if(mType == 4)
        {
            s = context.getString(0x10402a0);
            s1 = context.getString(0x104029d, new Object[] {
                mMessage
            });
            i = 0x104029f;
            j = 0x104029e;
        } else
        {
            s = getJsDialogTitle(context);
            s1 = mMessage;
            i = 0x104000a;
            j = 0x1040000;
        }
        builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle(s);
        builder.setOnCancelListener(new CancelListener(null));
        if(mType != 3)
        {
            builder.setMessage(s1);
            builder.setPositiveButton(i, new PositiveListener(null));
        } else
        {
            View view = LayoutInflater.from(context).inflate(0x1090071, null);
            context = (EditText)view.findViewById(0x102049d);
            context.setText(mDefaultValue);
            builder.setPositiveButton(i, new PositiveListener(context));
            ((TextView)view.findViewById(0x102000b)).setText(mMessage);
            builder.setView(view);
        }
        if(mType != 1)
            builder.setNegativeButton(j, new CancelListener(null));
        builder.show();
    }

    public static final int ALERT = 1;
    public static final int CONFIRM = 2;
    public static final int PROMPT = 3;
    private static final String TAG = "JsDialogHelper";
    public static final int UNLOAD = 4;
    private final String mDefaultValue;
    private final String mMessage;
    private final JsPromptResult mResult;
    private final int mType;
    private final String mUrl;
}
