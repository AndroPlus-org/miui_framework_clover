// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.view.accessibility.AccessibilityEvent;
import java.util.List;

// Referenced classes of package android.view:
//            MotionEvent, KeyEvent, ActionMode, Menu, 
//            View, MenuItem, SearchEvent

public class WindowCallbackWrapper
    implements Window.Callback
{

    public WindowCallbackWrapper(Window.Callback callback)
    {
        if(callback == null)
        {
            throw new IllegalArgumentException("Window callback may not be null");
        } else
        {
            mWrapped = callback;
            return;
        }
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionevent)
    {
        return mWrapped.dispatchGenericMotionEvent(motionevent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        return mWrapped.dispatchKeyEvent(keyevent);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyevent)
    {
        return mWrapped.dispatchKeyShortcutEvent(keyevent);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        return mWrapped.dispatchPopulateAccessibilityEvent(accessibilityevent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        return mWrapped.dispatchTouchEvent(motionevent);
    }

    public boolean dispatchTrackballEvent(MotionEvent motionevent)
    {
        return mWrapped.dispatchTrackballEvent(motionevent);
    }

    public void onActionModeFinished(ActionMode actionmode)
    {
        mWrapped.onActionModeFinished(actionmode);
    }

    public void onActionModeStarted(ActionMode actionmode)
    {
        mWrapped.onActionModeStarted(actionmode);
    }

    public void onAttachedToWindow()
    {
        mWrapped.onAttachedToWindow();
    }

    public void onContentChanged()
    {
        mWrapped.onContentChanged();
    }

    public boolean onCreatePanelMenu(int i, Menu menu)
    {
        return mWrapped.onCreatePanelMenu(i, menu);
    }

    public View onCreatePanelView(int i)
    {
        return mWrapped.onCreatePanelView(i);
    }

    public void onDetachedFromWindow()
    {
        mWrapped.onDetachedFromWindow();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuitem)
    {
        return mWrapped.onMenuItemSelected(i, menuitem);
    }

    public boolean onMenuOpened(int i, Menu menu)
    {
        return mWrapped.onMenuOpened(i, menu);
    }

    public void onPanelClosed(int i, Menu menu)
    {
        mWrapped.onPanelClosed(i, menu);
    }

    public void onPointerCaptureChanged(boolean flag)
    {
        mWrapped.onPointerCaptureChanged(flag);
    }

    public boolean onPreparePanel(int i, View view, Menu menu)
    {
        return mWrapped.onPreparePanel(i, view, menu);
    }

    public void onProvideKeyboardShortcuts(List list, Menu menu, int i)
    {
        mWrapped.onProvideKeyboardShortcuts(list, menu, i);
    }

    public boolean onSearchRequested()
    {
        return mWrapped.onSearchRequested();
    }

    public boolean onSearchRequested(SearchEvent searchevent)
    {
        return mWrapped.onSearchRequested(searchevent);
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutparams)
    {
        mWrapped.onWindowAttributesChanged(layoutparams);
    }

    public void onWindowFocusChanged(boolean flag)
    {
        mWrapped.onWindowFocusChanged(flag);
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback)
    {
        return mWrapped.onWindowStartingActionMode(callback);
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i)
    {
        return mWrapped.onWindowStartingActionMode(callback, i);
    }

    private Window.Callback mWrapped;
}
