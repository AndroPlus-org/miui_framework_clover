// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.autofill;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.os.RemoteException;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

// Referenced classes of package android.view.autofill:
//            Helper, IAutofillWindowPresenter

public class AutofillPopupWindow extends PopupWindow
{
    private class WindowPresenter
    {

        void hide(Rect rect)
        {
            mPresenter.hide(rect);
_L1:
            return;
            rect;
            Log.w("AutofillPopupWindow", "Error hiding fill window", rect);
            rect.rethrowFromSystemServer();
              goto _L1
        }

        void show(android.view.WindowManager.LayoutParams layoutparams, Rect rect, boolean flag, int i)
        {
            mPresenter.show(layoutparams, rect, flag, i);
_L1:
            return;
            layoutparams;
            Log.w("AutofillPopupWindow", "Error showing fill window", layoutparams);
            layoutparams.rethrowFromSystemServer();
              goto _L1
        }

        final IAutofillWindowPresenter mPresenter;
        final AutofillPopupWindow this$0;

        WindowPresenter(IAutofillWindowPresenter iautofillwindowpresenter)
        {
            this$0 = AutofillPopupWindow.this;
            super();
            mPresenter = iautofillwindowpresenter;
        }
    }


    public AutofillPopupWindow(IAutofillWindowPresenter iautofillwindowpresenter)
    {
        mWindowPresenter = new WindowPresenter(iautofillwindowpresenter);
        setOutsideTouchable(true);
        setInputMethodMode(1);
    }

    protected void attachToAnchor(View view, int i, int j, int k)
    {
        super.attachToAnchor(view, i, j, k);
        view.addOnAttachStateChangeListener(mOnAttachStateChangeListener);
    }

    protected void detachFromAnchor()
    {
        View view = getAnchor();
        if(view != null)
            view.removeOnAttachStateChangeListener(mOnAttachStateChangeListener);
        super.detachFromAnchor();
    }

    public void dismiss()
    {
        if(!isShowing() || isTransitioningToDismiss())
            return;
        setShowing(false);
        setTransitioningToDismiss(true);
        mWindowPresenter.hide(getTransitionEpicenter());
        detachFromAnchor();
        if(getOnDismissListener() != null)
            getOnDismissListener().onDismiss();
    }

    public int getAnimationStyle()
    {
        throw new IllegalStateException("You can't call this!");
    }

    public Drawable getBackground()
    {
        throw new IllegalStateException("You can't call this!");
    }

    public View getContentView()
    {
        throw new IllegalStateException("You can't call this!");
    }

    protected android.view.WindowManager.LayoutParams getDecorViewLayoutParams()
    {
        return mWindowLayoutParams;
    }

    public float getElevation()
    {
        throw new IllegalStateException("You can't call this!");
    }

    public Transition getEnterTransition()
    {
        throw new IllegalStateException("You can't call this!");
    }

    public Transition getExitTransition()
    {
        throw new IllegalStateException("You can't call this!");
    }

    protected boolean hasContentView()
    {
        return true;
    }

    protected boolean hasDecorView()
    {
        return true;
    }

    public void setAnimationStyle(int i)
    {
        throw new IllegalStateException("You can't call this!");
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        throw new IllegalStateException("You can't call this!");
    }

    public void setContentView(View view)
    {
        if(view != null)
            throw new IllegalStateException("You can't call this!");
        else
            return;
    }

    public void setElevation(float f)
    {
        throw new IllegalStateException("You can't call this!");
    }

    public void setEnterTransition(Transition transition)
    {
        throw new IllegalStateException("You can't call this!");
    }

    public void setExitTransition(Transition transition)
    {
        throw new IllegalStateException("You can't call this!");
    }

    public void setTouchInterceptor(android.view.View.OnTouchListener ontouchlistener)
    {
        throw new IllegalStateException("You can't call this!");
    }

    public void showAsDropDown(View view, int i, int j, int k)
    {
        if(Helper.sVerbose)
            Log.v("AutofillPopupWindow", (new StringBuilder()).append("showAsDropDown(): anchor=").append(view).append(", xoff=").append(i).append(", yoff=").append(j).append(", isShowing(): ").append(isShowing()).toString());
        if(isShowing())
        {
            return;
        } else
        {
            setShowing(true);
            setDropDown(true);
            attachToAnchor(view, i, j, k);
            android.view.WindowManager.LayoutParams layoutparams = createPopupLayoutParams(view.getWindowToken());
            mWindowLayoutParams = layoutparams;
            updateAboveAnchor(findDropDownPosition(view, layoutparams, i, j, layoutparams.width, layoutparams.height, k, getAllowScrollingAnchorParent()));
            layoutparams.accessibilityIdOfAnchor = view.getAccessibilityViewId();
            layoutparams.packageName = view.getContext().getPackageName();
            mWindowPresenter.show(layoutparams, getTransitionEpicenter(), isLayoutInsetDecor(), view.getLayoutDirection());
            return;
        }
    }

    public void update(View view, int i, int j, int k, int l, Rect rect)
    {
        if(rect != null)
        {
            View view1 = new View(view) {

                public void addOnAttachStateChangeListener(android.view.View.OnAttachStateChangeListener onattachstatechangelistener)
                {
                    anchor.addOnAttachStateChangeListener(onattachstatechangelistener);
                }

                public int getAccessibilityViewId()
                {
                    return anchor.getAccessibilityViewId();
                }

                public IBinder getApplicationWindowToken()
                {
                    return anchor.getApplicationWindowToken();
                }

                public int getLayoutDirection()
                {
                    return anchor.getLayoutDirection();
                }

                public void getLocationOnScreen(int ai[])
                {
                    ai[0] = virtualBounds.left;
                    ai[1] = virtualBounds.top;
                }

                public View getRootView()
                {
                    return anchor.getRootView();
                }

                public ViewTreeObserver getViewTreeObserver()
                {
                    return anchor.getViewTreeObserver();
                }

                public void getWindowDisplayFrame(Rect rect1)
                {
                    anchor.getWindowDisplayFrame(rect1);
                }

                public IBinder getWindowToken()
                {
                    return anchor.getWindowToken();
                }

                public boolean isAttachedToWindow()
                {
                    return anchor.isAttachedToWindow();
                }

                public void removeOnAttachStateChangeListener(android.view.View.OnAttachStateChangeListener onattachstatechangelistener)
                {
                    anchor.removeOnAttachStateChangeListener(onattachstatechangelistener);
                }

                public boolean requestRectangleOnScreen(Rect rect1, boolean flag)
                {
                    return anchor.requestRectangleOnScreen(rect1, flag);
                }

                final AutofillPopupWindow this$0;
                final View val$anchor;
                final Rect val$virtualBounds;

            
            {
                this$0 = AutofillPopupWindow.this;
                virtualBounds = rect;
                anchor = view;
                super(final_context);
            }
            }
;
            view1.setLeftTopRightBottom(rect.left, rect.top, rect.right, rect.bottom);
            view1.setScrollX(view.getScrollX());
            view1.setScrollY(view.getScrollY());
            view = view1;
        }
        if(!isShowing())
        {
            setWidth(k);
            setHeight(l);
            showAsDropDown(view, i, j);
        } else
        {
            update(view, i, j, k, l);
        }
    }

    protected void update(View view, android.view.WindowManager.LayoutParams layoutparams)
    {
        int i;
        if(view != null)
            i = view.getLayoutDirection();
        else
            i = 3;
        mWindowPresenter.show(layoutparams, getTransitionEpicenter(), isLayoutInsetDecor(), i);
    }

    private static final String TAG = "AutofillPopupWindow";
    private final android.view.View.OnAttachStateChangeListener mOnAttachStateChangeListener = new android.view.View.OnAttachStateChangeListener() {

        public void onViewAttachedToWindow(View view)
        {
        }

        public void onViewDetachedFromWindow(View view)
        {
            dismiss();
        }

        final AutofillPopupWindow this$0;

            
            {
                this$0 = AutofillPopupWindow.this;
                super();
            }
    }
;
    private android.view.WindowManager.LayoutParams mWindowLayoutParams;
    private final WindowPresenter mWindowPresenter;
}
