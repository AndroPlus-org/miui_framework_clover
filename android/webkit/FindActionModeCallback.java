// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.*;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

// Referenced classes of package android.webkit:
//            WebView

public class FindActionModeCallback
    implements android.view.ActionMode.Callback, TextWatcher, android.view.View.OnClickListener, WebView.FindListener
{
    public static class NoAction
        implements android.view.ActionMode.Callback
    {

        public boolean onActionItemClicked(ActionMode actionmode, MenuItem menuitem)
        {
            return false;
        }

        public boolean onCreateActionMode(ActionMode actionmode, Menu menu)
        {
            return false;
        }

        public void onDestroyActionMode(ActionMode actionmode)
        {
        }

        public boolean onPrepareActionMode(ActionMode actionmode, Menu menu)
        {
            return false;
        }

        public NoAction()
        {
        }
    }


    public FindActionModeCallback(Context context)
    {
        mGlobalVisibleRect = new Rect();
        mGlobalVisibleOffset = new Point();
        mCustomView = LayoutInflater.from(context).inflate(0x1090120, null);
        mEditText = (EditText)mCustomView.findViewById(0x1020003);
        mEditText.setCustomSelectionActionModeCallback(new NoAction());
        mEditText.setOnClickListener(this);
        setText("");
        mMatches = (TextView)mCustomView.findViewById(0x10202f1);
        mInput = (InputMethodManager)context.getSystemService(android/view/inputmethod/InputMethodManager);
        mResources = context.getResources();
    }

    private void findNext(boolean flag)
    {
        if(mWebView == null)
            throw new AssertionError("No WebView for FindActionModeCallback::findNext");
        if(!mMatchesFound)
        {
            findAll();
            return;
        }
        if(mNumberOfMatches == 0)
        {
            return;
        } else
        {
            mWebView.findNext(flag);
            updateMatchesString();
            return;
        }
    }

    private void updateMatchesString()
    {
        if(mNumberOfMatches == 0)
            mMatches.setText(0x10403ce);
        else
            mMatches.setText(mResources.getQuantityString(0x1150016, mNumberOfMatches, new Object[] {
                Integer.valueOf(mActiveMatchIndex + 1), Integer.valueOf(mNumberOfMatches)
            }));
        mMatches.setVisibility(0);
    }

    public void afterTextChanged(Editable editable)
    {
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public void findAll()
    {
        if(mWebView == null)
            throw new AssertionError("No WebView for FindActionModeCallback::findAll");
        Editable editable = mEditText.getText();
        if(editable.length() == 0)
        {
            mWebView.clearMatches();
            mMatches.setVisibility(8);
            mMatchesFound = false;
            mWebView.findAll(null);
        } else
        {
            mMatchesFound = true;
            mMatches.setVisibility(4);
            mNumberOfMatches = 0;
            mWebView.findAllAsync(editable.toString());
        }
    }

    public void finish()
    {
        mActionMode.finish();
    }

    public int getActionModeGlobalBottom()
    {
        if(mActionMode == null)
            return 0;
        View view = (View)mCustomView.getParent();
        View view1 = view;
        if(view == null)
            view1 = mCustomView;
        view1.getGlobalVisibleRect(mGlobalVisibleRect, mGlobalVisibleOffset);
        return mGlobalVisibleRect.bottom;
    }

    public boolean onActionItemClicked(ActionMode actionmode, MenuItem menuitem)
    {
        if(mWebView == null)
            throw new AssertionError("No WebView for FindActionModeCallback::onActionItemClicked");
        mInput.hideSoftInputFromWindow(mWebView.getWindowToken(), 0);
        menuitem.getItemId();
        JVM INSTR tableswitch 16908876 16908877: default 60
    //                   16908876 69
    //                   16908877 62;
           goto _L1 _L2 _L3
_L1:
        return false;
_L3:
        findNext(false);
_L5:
        return true;
_L2:
        findNext(true);
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void onClick(View view)
    {
        findNext(true);
    }

    public boolean onCreateActionMode(ActionMode actionmode, Menu menu)
    {
        if(!actionmode.isUiFocusable())
        {
            return false;
        } else
        {
            actionmode.setCustomView(mCustomView);
            actionmode.getMenuInflater().inflate(0x1140002, menu);
            mActionMode = actionmode;
            actionmode = mEditText.getText();
            Selection.setSelection(actionmode, actionmode.length());
            mMatches.setVisibility(8);
            mMatchesFound = false;
            mMatches.setText("0");
            mEditText.requestFocus();
            return true;
        }
    }

    public void onDestroyActionMode(ActionMode actionmode)
    {
        mActionMode = null;
        mWebView.notifyFindDialogDismissed();
        mWebView.setFindDialogFindListener(null);
        mInput.hideSoftInputFromWindow(mWebView.getWindowToken(), 0);
    }

    public void onFindResultReceived(int i, int j, boolean flag)
    {
        boolean flag1 = false;
        if(flag)
        {
            flag = flag1;
            if(j == 0)
                flag = true;
            updateMatchCount(i, j, flag);
        }
    }

    public boolean onPrepareActionMode(ActionMode actionmode, Menu menu)
    {
        return false;
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        findAll();
    }

    public void setText(String s)
    {
        mEditText.setText(s);
        s = mEditText.getText();
        int i = s.length();
        Selection.setSelection(s, i, i);
        s.setSpan(this, 0, i, 18);
        mMatchesFound = false;
    }

    public void setWebView(WebView webview)
    {
        if(webview == null)
        {
            throw new AssertionError("WebView supplied to FindActionModeCallback cannot be null");
        } else
        {
            mWebView = webview;
            mWebView.setFindDialogFindListener(this);
            return;
        }
    }

    public void showSoftInput()
    {
        if(mEditText.requestFocus())
            mInput.showSoftInput(mEditText, 0);
    }

    public void updateMatchCount(int i, int j, boolean flag)
    {
        if(!flag)
        {
            mNumberOfMatches = j;
            mActiveMatchIndex = i;
            updateMatchesString();
        } else
        {
            mMatches.setVisibility(8);
            mNumberOfMatches = 0;
        }
    }

    private ActionMode mActionMode;
    private int mActiveMatchIndex;
    private View mCustomView;
    private EditText mEditText;
    private Point mGlobalVisibleOffset;
    private Rect mGlobalVisibleRect;
    private InputMethodManager mInput;
    private TextView mMatches;
    private boolean mMatchesFound;
    private int mNumberOfMatches;
    private Resources mResources;
    private WebView mWebView;
}
