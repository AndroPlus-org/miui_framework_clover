// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.*;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.widget:
//            AdapterView, Filterable, PopupWindow, EdgeEffect, 
//            EditText, AbsListViewInjector, ListAdapter, FastScroller, 
//            Checkable, RemoteViewsAdapter, Filter, OverScroller, 
//            Adapter

public abstract class AbsListView extends AdapterView
    implements TextWatcher, android.view.ViewTreeObserver.OnGlobalLayoutListener, Filter.FilterListener, android.view.ViewTreeObserver.OnTouchModeChangeListener, RemoteViewsAdapter.RemoteAdapterConnectionCallback
{
    static abstract class AbsPositionScroller
    {

        public abstract void start(int i);

        public abstract void start(int i, int j);

        public abstract void startWithOffset(int i, int j);

        public abstract void startWithOffset(int i, int j, int k);

        public abstract void stop();

        AbsPositionScroller()
        {
        }
    }

    class AdapterDataSetObserver extends AdapterView.AdapterDataSetObserver
    {

        public void onChanged()
        {
            super.onChanged();
            if(AbsListView._2D_get5(AbsListView.this) != null)
                AbsListView._2D_get5(AbsListView.this).onSectionsChanged();
        }

        public void onInvalidated()
        {
            super.onInvalidated();
            if(AbsListView._2D_get5(AbsListView.this) != null)
                AbsListView._2D_get5(AbsListView.this).onSectionsChanged();
        }

        final AbsListView this$0;

        AdapterDataSetObserver()
        {
            this$0 = AbsListView.this;
            super(AbsListView.this);
        }
    }

    private class CheckForKeyLongPress extends WindowRunnnable
        implements Runnable
    {

        public void run()
        {
            if(!isPressed() || mSelectedPosition < 0) goto _L2; else goto _L1
_L1:
            View view;
            int i = mSelectedPosition;
            int j = mFirstPosition;
            view = getChildAt(i - j);
            if(mDataChanged) goto _L4; else goto _L3
_L3:
            boolean flag = false;
            if(sameWindow())
                flag = performLongPress(view, mSelectedPosition, mSelectedRowId);
            if(flag)
            {
                setPressed(false);
                view.setPressed(false);
            }
_L2:
            return;
_L4:
            setPressed(false);
            if(view != null)
                view.setPressed(false);
            if(true) goto _L2; else goto _L5
_L5:
        }

        final AbsListView this$0;

        private CheckForKeyLongPress()
        {
            this$0 = AbsListView.this;
            super(null);
        }

        CheckForKeyLongPress(CheckForKeyLongPress checkforkeylongpress)
        {
            this();
        }
    }

    private class CheckForLongPress extends WindowRunnnable
        implements Runnable
    {

        static void _2D_wrap0(CheckForLongPress checkforlongpress, float f, float f1)
        {
            checkforlongpress.setCoords(f, f1);
        }

        private void setCoords(float f, float f1)
        {
            mX = f;
            mY = f1;
        }

        public void run()
        {
            int i = mMotionPosition;
            View view = getChildAt(i - mFirstPosition);
            if(view != null)
            {
                int j = mMotionPosition;
                long l = mAdapter.getItemId(mMotionPosition);
                boolean flag = false;
                boolean flag1 = flag;
                if(sameWindow())
                {
                    flag1 = flag;
                    if(mDataChanged ^ true)
                        if(mX != -1F && mY != -1F)
                            flag1 = performLongPress(view, j, l, mX, mY);
                        else
                            flag1 = performLongPress(view, j, l);
                }
                if(flag1)
                {
                    AbsListView._2D_set1(AbsListView.this, true);
                    mTouchMode = -1;
                    setPressed(false);
                    view.setPressed(false);
                } else
                {
                    mTouchMode = 2;
                }
            }
        }

        private static final int INVALID_COORD = -1;
        private float mX;
        private float mY;
        final AbsListView this$0;

        private CheckForLongPress()
        {
            this$0 = AbsListView.this;
            super(null);
            mX = -1F;
            mY = -1F;
        }

        CheckForLongPress(CheckForLongPress checkforlongpress)
        {
            this();
        }
    }

    private final class CheckForTap
        implements Runnable
    {

        public void run()
        {
            if(mTouchMode == 0)
            {
                mTouchMode = 1;
                View view = getChildAt(mMotionPosition - mFirstPosition);
                if(view != null && view.hasExplicitFocusable() ^ true)
                {
                    mLayoutMode = 0;
                    if(!mDataChanged)
                    {
                        float af[] = AbsListView._2D_get14(AbsListView.this);
                        af[0] = x;
                        af[1] = y;
                        transformPointToViewLocal(af, view);
                        view.drawableHotspotChanged(af[0], af[1]);
                        view.setPressed(true);
                        setPressed(true);
                        layoutChildren();
                        positionSelector(mMotionPosition, view);
                        refreshDrawableState();
                        int i = ViewConfiguration.getLongPressTimeout();
                        boolean flag = isLongClickable();
                        if(mSelector != null)
                        {
                            Drawable drawable = mSelector.getCurrent();
                            if(drawable != null && (drawable instanceof TransitionDrawable))
                                if(flag)
                                    ((TransitionDrawable)drawable).startTransition(i);
                                else
                                    ((TransitionDrawable)drawable).resetTransition();
                            mSelector.setHotspot(x, y);
                        }
                        if(flag)
                        {
                            if(AbsListView._2D_get11(AbsListView.this) == null)
                                AbsListView._2D_set2(AbsListView.this, new CheckForLongPress(null));
                            CheckForLongPress._2D_wrap0(AbsListView._2D_get11(AbsListView.this), x, y);
                            AbsListView._2D_get11(AbsListView.this).rememberWindowAttachCount();
                            postDelayed(AbsListView._2D_get11(AbsListView.this), i);
                        } else
                        {
                            mTouchMode = 2;
                        }
                    } else
                    {
                        mTouchMode = 2;
                    }
                }
            }
        }

        final AbsListView this$0;
        float x;
        float y;

        private CheckForTap()
        {
            this$0 = AbsListView.this;
            super();
        }

        CheckForTap(CheckForTap checkfortap)
        {
            this();
        }
    }

    private class FlingRunnable
        implements Runnable
    {

        static OverScroller _2D_get0(FlingRunnable flingrunnable)
        {
            return flingrunnable.mScroller;
        }

        static boolean _2D_set0(FlingRunnable flingrunnable, boolean flag)
        {
            flingrunnable.mSuppressIdleStateChangeCall = flag;
            return flag;
        }

        void edgeReached(int i)
        {
            int j;
            mScroller.notifyVerticalEdgeReached(AbsListView._2D_get13(AbsListView.this), 0, mOverflingDistance);
            j = getOverScrollMode();
            if(j != 0 && (j != 1 || !(AbsListView._2D_wrap1(AbsListView.this) ^ true))) goto _L2; else goto _L1
_L1:
            mTouchMode = 6;
            int k = (int)mScroller.getCurrVelocity();
            if(i > 0)
                AbsListView._2D_get4(AbsListView.this).onAbsorb(k);
            else
                AbsListView._2D_get3(AbsListView.this).onAbsorb(k);
_L4:
            invalidate();
            postOnAnimation(this);
            return;
_L2:
            mTouchMode = -1;
            if(mPositionScroller != null)
                mPositionScroller.stop();
            if(true) goto _L4; else goto _L3
_L3:
        }

        void endFling()
        {
            mTouchMode = -1;
            removeCallbacks(this);
            removeCallbacks(mCheckFlywheel);
            if(!mSuppressIdleStateChangeCall)
                reportScrollStateChange(0);
            AbsListView._2D_wrap5(AbsListView.this);
            mScroller.abortAnimation();
            if(AbsListView._2D_get6(AbsListView.this) != null)
            {
                AbsListView._2D_get6(AbsListView.this).finish();
                AbsListView._2D_set0(AbsListView.this, null);
            }
        }

        void flywheelTouch()
        {
            postDelayed(mCheckFlywheel, 40L);
        }

        public void run()
        {
            mTouchMode;
            JVM INSTR tableswitch 3 6: default 36
        //                       3 41
        //                       4 52
        //                       5 36
        //                       6 450;
               goto _L1 _L2 _L3 _L1 _L4
_L1:
            endFling();
            return;
_L2:
            if(mScroller.isFinished())
                return;
_L3:
            if(mDataChanged)
                layoutChildren();
            if(mItemCount == 0 || getChildCount() == 0)
            {
                endFling();
                return;
            }
            Object obj = mScroller;
            boolean flag = ((OverScroller) (obj)).computeScrollOffset();
            int i = ((OverScroller) (obj)).getCurrY();
            int j = mLastFlingY - i;
            int l;
            boolean flag2;
            boolean flag3;
            if(j > 0)
            {
                mMotionPosition = mFirstPosition;
                obj = getChildAt(0);
                mMotionViewOriginalTop = ((View) (obj)).getTop();
                l = Math.min(getHeight() - AbsListView._2D_get9(AbsListView.this) - AbsListView._2D_get10(AbsListView.this) - 1, j);
            } else
            {
                l = getChildCount() - 1;
                mMotionPosition = mFirstPosition + l;
                View view = getChildAt(l);
                mMotionViewOriginalTop = view.getTop();
                l = Math.max(-(getHeight() - AbsListView._2D_get9(AbsListView.this) - AbsListView._2D_get10(AbsListView.this) - 1), j);
            }
            obj = getChildAt(mMotionPosition - mFirstPosition);
            j = 0;
            if(obj != null)
                j = ((View) (obj)).getTop();
            flag2 = trackMotionScroll(l, l);
            if(flag2 && l != 0)
                flag3 = true;
            else
                flag3 = false;
            if(flag3)
            {
                if(obj != null)
                {
                    j = -(l - (((View) (obj)).getTop() - j));
                    AbsListView._2D_wrap3(AbsListView.this, 0, j, 0, AbsListView._2D_get13(AbsListView.this), 0, 0, 0, mOverflingDistance, false);
                }
                if(flag)
                    edgeReached(l);
            } else
            if(flag && flag3 ^ true)
            {
                if(flag2)
                    invalidate();
                mLastFlingY = i;
                postOnAnimation(this);
            } else
            {
                endFling();
            }
_L6:
            return;
_L4:
            OverScroller overscroller = mScroller;
            if(overscroller.computeScrollOffset())
            {
                int j1 = AbsListView._2D_get13(AbsListView.this);
                int k = overscroller.getCurrY();
                if(AbsListView._2D_wrap3(AbsListView.this, 0, k - j1, 0, j1, 0, 0, 0, mOverflingDistance, false))
                {
                    boolean flag1;
                    int i1;
                    if(j1 <= 0 && k > 0)
                        i1 = 1;
                    else
                        i1 = 0;
                    if(j1 >= 0 && k < 0)
                        flag1 = true;
                    else
                        flag1 = false;
                    if(i1 != 0 || flag1)
                    {
                        int k1 = (int)overscroller.getCurrVelocity();
                        i1 = k1;
                        if(flag1)
                            i1 = -k1;
                        overscroller.abortAnimation();
                        start(i1);
                    } else
                    {
                        startSpringback();
                    }
                } else
                {
                    invalidate();
                    postOnAnimation(this);
                }
            } else
            {
                endFling();
            }
            if(true) goto _L6; else goto _L5
_L5:
        }

        void start(int i)
        {
            int j;
            if(i < 0)
                j = 0x7fffffff;
            else
                j = 0;
            mLastFlingY = j;
            mScroller.setInterpolator(null);
            mScroller.fling(0, j, 0, i, 0, 0x7fffffff, 0, 0x7fffffff);
            mTouchMode = 4;
            mSuppressIdleStateChangeCall = false;
            postOnAnimation(this);
            if(AbsListView._2D_get6(AbsListView.this) == null)
                AbsListView._2D_set0(AbsListView.this, StrictMode.enterCriticalSpan("AbsListView-fling"));
        }

        void startOverfling(int i)
        {
            mScroller.setInterpolator(null);
            mScroller.fling(0, AbsListView._2D_get13(AbsListView.this), 0, i, 0, 0, 0x80000000, 0x7fffffff, 0, getHeight());
            mTouchMode = 6;
            mSuppressIdleStateChangeCall = false;
            invalidate();
            postOnAnimation(this);
        }

        void startScroll(int i, int j, boolean flag, boolean flag1)
        {
            int k;
            OverScroller overscroller;
            Interpolator interpolator;
            if(i < 0)
                k = 0x7fffffff;
            else
                k = 0;
            mLastFlingY = k;
            overscroller = mScroller;
            if(flag)
                interpolator = AbsListView.sLinearInterpolator;
            else
                interpolator = null;
            overscroller.setInterpolator(interpolator);
            mScroller.startScroll(0, k, 0, i, j);
            mTouchMode = 4;
            mSuppressIdleStateChangeCall = flag1;
            postOnAnimation(this);
        }

        void startSpringback()
        {
            mSuppressIdleStateChangeCall = false;
            if(mScroller.springBack(0, AbsListView._2D_get13(AbsListView.this), 0, 0, 0, 0))
            {
                mTouchMode = 6;
                invalidate();
                postOnAnimation(this);
            } else
            {
                mTouchMode = -1;
                reportScrollStateChange(0);
            }
        }

        private static final int FLYWHEEL_TIMEOUT = 40;
        private final Runnable mCheckFlywheel = new _cls1();
        private int mLastFlingY;
        private final OverScroller mScroller;
        private boolean mSuppressIdleStateChangeCall;
        final AbsListView this$0;

        FlingRunnable()
        {
            this$0 = AbsListView.this;
            super();
            mScroller = new OverScroller(getContext());
        }
    }

    private class InputConnectionWrapper
        implements InputConnection
    {

        private InputConnection getTarget()
        {
            if(mTarget == null)
                mTarget = AbsListView._2D_wrap0(AbsListView.this).onCreateInputConnection(mOutAttrs);
            return mTarget;
        }

        public boolean beginBatchEdit()
        {
            return getTarget().beginBatchEdit();
        }

        public boolean clearMetaKeyStates(int i)
        {
            return getTarget().clearMetaKeyStates(i);
        }

        public void closeConnection()
        {
            getTarget().closeConnection();
        }

        public boolean commitCompletion(CompletionInfo completioninfo)
        {
            return getTarget().commitCompletion(completioninfo);
        }

        public boolean commitContent(InputContentInfo inputcontentinfo, int i, Bundle bundle)
        {
            return getTarget().commitContent(inputcontentinfo, i, bundle);
        }

        public boolean commitCorrection(CorrectionInfo correctioninfo)
        {
            return getTarget().commitCorrection(correctioninfo);
        }

        public boolean commitText(CharSequence charsequence, int i)
        {
            return getTarget().commitText(charsequence, i);
        }

        public boolean deleteSurroundingText(int i, int j)
        {
            return getTarget().deleteSurroundingText(i, j);
        }

        public boolean deleteSurroundingTextInCodePoints(int i, int j)
        {
            return getTarget().deleteSurroundingTextInCodePoints(i, j);
        }

        public boolean endBatchEdit()
        {
            return getTarget().endBatchEdit();
        }

        public boolean finishComposingText()
        {
            boolean flag;
            if(mTarget != null)
                flag = mTarget.finishComposingText();
            else
                flag = true;
            return flag;
        }

        public int getCursorCapsMode(int i)
        {
            if(mTarget == null)
                return 16384;
            else
                return mTarget.getCursorCapsMode(i);
        }

        public ExtractedText getExtractedText(ExtractedTextRequest extractedtextrequest, int i)
        {
            return getTarget().getExtractedText(extractedtextrequest, i);
        }

        public Handler getHandler()
        {
            return getTarget().getHandler();
        }

        public CharSequence getSelectedText(int i)
        {
            if(mTarget == null)
                return "";
            else
                return mTarget.getSelectedText(i);
        }

        public CharSequence getTextAfterCursor(int i, int j)
        {
            if(mTarget == null)
                return "";
            else
                return mTarget.getTextAfterCursor(i, j);
        }

        public CharSequence getTextBeforeCursor(int i, int j)
        {
            if(mTarget == null)
                return "";
            else
                return mTarget.getTextBeforeCursor(i, j);
        }

        public boolean performContextMenuAction(int i)
        {
            return getTarget().performContextMenuAction(i);
        }

        public boolean performEditorAction(int i)
        {
            if(i == 6)
            {
                InputMethodManager inputmethodmanager = (InputMethodManager)getContext().getSystemService(android/view/inputmethod/InputMethodManager);
                if(inputmethodmanager != null)
                    inputmethodmanager.hideSoftInputFromWindow(getWindowToken(), 0);
                return true;
            } else
            {
                return false;
            }
        }

        public boolean performPrivateCommand(String s, Bundle bundle)
        {
            return getTarget().performPrivateCommand(s, bundle);
        }

        public boolean reportFullscreenMode(boolean flag)
        {
            return AbsListView._2D_get2(AbsListView.this).reportFullscreenMode(flag);
        }

        public boolean requestCursorUpdates(int i)
        {
            return getTarget().requestCursorUpdates(i);
        }

        public boolean sendKeyEvent(KeyEvent keyevent)
        {
            return AbsListView._2D_get2(AbsListView.this).sendKeyEvent(keyevent);
        }

        public boolean setComposingRegion(int i, int j)
        {
            return getTarget().setComposingRegion(i, j);
        }

        public boolean setComposingText(CharSequence charsequence, int i)
        {
            return getTarget().setComposingText(charsequence, i);
        }

        public boolean setSelection(int i, int j)
        {
            return getTarget().setSelection(i, j);
        }

        private final EditorInfo mOutAttrs;
        private InputConnection mTarget;
        final AbsListView this$0;

        public InputConnectionWrapper(EditorInfo editorinfo)
        {
            this$0 = AbsListView.this;
            super();
            mOutAttrs = editorinfo;
        }
    }

    public static class LayoutParams extends android.view.ViewGroup.LayoutParams
    {

        protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
        {
            super.encodeProperties(viewhierarchyencoder);
            viewhierarchyencoder.addProperty("list:viewType", viewType);
            viewhierarchyencoder.addProperty("list:recycledHeaderFooter", recycledHeaderFooter);
            viewhierarchyencoder.addProperty("list:forceAdd", forceAdd);
            viewhierarchyencoder.addProperty("list:isEnabled", isEnabled);
        }

        boolean forceAdd;
        boolean isEnabled;
        long itemId;
        boolean recycledHeaderFooter;
        int scrappedFromPosition;
        int viewType;

        public LayoutParams(int i, int j)
        {
            super(i, j);
            itemId = -1L;
        }

        public LayoutParams(int i, int j, int k)
        {
            super(i, j);
            itemId = -1L;
            viewType = k;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            itemId = -1L;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            itemId = -1L;
        }
    }

    class ListItemAccessibilityDelegate extends android.view.View.AccessibilityDelegate
    {

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilitynodeinfo)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfo);
            int i = getPositionForView(view);
            onInitializeAccessibilityNodeInfoForItem(view, i, accessibilitynodeinfo);
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle)
        {
            if(super.performAccessibilityAction(view, i, bundle))
                return true;
            int j = getPositionForView(view);
            if(j == -1 || mAdapter == null)
                return false;
            if(j >= mAdapter.getCount())
                return false;
            bundle = view.getLayoutParams();
            boolean flag;
            if(bundle instanceof LayoutParams)
                flag = ((LayoutParams)bundle).isEnabled;
            else
                flag = false;
            if(!isEnabled() || flag ^ true)
                return false;
            switch(i)
            {
            default:
                return false;

            case 8: // '\b'
                if(getSelectedItemPosition() == j)
                {
                    setSelection(-1);
                    return true;
                } else
                {
                    return false;
                }

            case 4: // '\004'
                if(getSelectedItemPosition() != j)
                {
                    setSelection(j);
                    return true;
                } else
                {
                    return false;
                }

            case 16: // '\020'
                if(AbsListView._2D_wrap2(AbsListView.this, view))
                {
                    long l = getItemIdAtPosition(j);
                    return performItemClick(view, j, l);
                } else
                {
                    return false;
                }

            case 32: // ' '
                break;
            }
            if(isLongClickable())
            {
                long l1 = getItemIdAtPosition(j);
                return performLongPress(view, j, l1);
            } else
            {
                return false;
            }
        }

        final AbsListView this$0;

        ListItemAccessibilityDelegate()
        {
            this$0 = AbsListView.this;
            super();
        }
    }

    public static interface MultiChoiceModeListener
        extends android.view.ActionMode.Callback
    {

        public abstract void onItemCheckedStateChanged(ActionMode actionmode, int i, long l, boolean flag);
    }

    class MultiChoiceModeWrapper
        implements MultiChoiceModeListener
    {

        public boolean hasWrappedCallback()
        {
            boolean flag;
            if(mWrapped != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean onActionItemClicked(ActionMode actionmode, MenuItem menuitem)
        {
            return mWrapped.onActionItemClicked(actionmode, menuitem);
        }

        public boolean onCreateActionMode(ActionMode actionmode, Menu menu)
        {
            if(mWrapped.onCreateActionMode(actionmode, menu))
            {
                setLongClickable(false);
                return true;
            } else
            {
                return false;
            }
        }

        public void onDestroyActionMode(ActionMode actionmode)
        {
            mWrapped.onDestroyActionMode(actionmode);
            mChoiceActionMode = null;
            clearChoices();
            mDataChanged = true;
            rememberSyncState();
            requestLayout();
            setLongClickable(true);
        }

        public void onItemCheckedStateChanged(ActionMode actionmode, int i, long l, boolean flag)
        {
            mWrapped.onItemCheckedStateChanged(actionmode, i, l, flag);
            if(AbsListViewInjector.needFinishActionMode(AbsListView.this))
                actionmode.finish();
        }

        public boolean onPrepareActionMode(ActionMode actionmode, Menu menu)
        {
            return mWrapped.onPrepareActionMode(actionmode, menu);
        }

        public void setWrapped(MultiChoiceModeListener multichoicemodelistener)
        {
            mWrapped = multichoicemodelistener;
        }

        private MultiChoiceModeListener mWrapped;
        final AbsListView this$0;

        MultiChoiceModeWrapper()
        {
            this$0 = AbsListView.this;
            super();
        }
    }

    public static interface OnScrollListener
    {

        public abstract void onScroll(AbsListView abslistview, int i, int j, int k);

        public abstract void onScrollStateChanged(AbsListView abslistview, int i);

        public static final int SCROLL_STATE_FLING = 2;
        public static final int SCROLL_STATE_IDLE = 0;
        public static final int SCROLL_STATE_TOUCH_SCROLL = 1;
    }

    private class PerformClick extends WindowRunnnable
        implements Runnable
    {

        public void run()
        {
            if(mDataChanged)
                return;
            ListAdapter listadapter = mAdapter;
            int i = mClickMotionPosition;
            if(listadapter != null && mItemCount > 0 && i != -1 && i < listadapter.getCount() && sameWindow() && listadapter.isEnabled(i))
            {
                View view = getChildAt(i - mFirstPosition);
                if(view != null)
                    performItemClick(view, i, listadapter.getItemId(i));
            }
        }

        int mClickMotionPosition;
        final AbsListView this$0;

        private PerformClick()
        {
            this$0 = AbsListView.this;
            super(null);
        }

        PerformClick(PerformClick performclick)
        {
            this();
        }
    }

    class PositionScroller extends AbsPositionScroller
        implements Runnable
    {

        private void scrollToVisible(int i, int j, int k)
        {
            int l;
            int k1;
            int l1;
            int i2;
label0:
            {
                l = mFirstPosition;
                int i1 = (l + getChildCount()) - 1;
                k1 = mListPadding.top;
                l1 = getHeight() - mListPadding.bottom;
                if(i < l || i > i1)
                    Log.w("AbsListView", (new StringBuilder()).append("scrollToVisible called with targetPos ").append(i).append(" not visible [").append(l).append(", ").append(i1).append("]").toString());
                if(j >= l)
                {
                    i2 = j;
                    if(j <= i1)
                        break label0;
                }
                i2 = -1;
            }
            View view = getChildAt(i - l);
            j = view.getTop();
            int j1 = view.getBottom();
            i = 0;
            if(j1 > l1)
                i = j1 - l1;
            if(j < k1)
                i = j - k1;
            if(i == 0)
                return;
            j = i;
            if(i2 < 0) goto _L2; else goto _L1
_L1:
            View view1 = getChildAt(i2 - l);
            i2 = view1.getTop();
            j = view1.getBottom();
            l = Math.abs(i);
            if(i >= 0 || j + l <= l1) goto _L4; else goto _L3
_L3:
            j = Math.max(0, j - l1);
_L2:
            smoothScrollBy(j, k);
            return;
_L4:
            j = i;
            if(i > 0)
            {
                j = i;
                if(i2 - l < k1)
                    j = Math.min(0, i2 - k1);
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        public void run()
        {
            int i;
            int l;
            i = getHeight();
            l = mFirstPosition;
            mMode;
            JVM INSTR tableswitch 1 5: default 56
        //                       1 57
        //                       2 434
        //                       3 246
        //                       4 575
        //                       5 744;
               goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
            return;
_L2:
            int i1 = getChildCount() - 1;
            l += i1;
            if(i1 < 0)
                return;
            if(l == mLastSeenPos)
            {
                postOnAnimation(this);
                return;
            }
            Object obj = getChildAt(i1);
            int i3 = ((View) (obj)).getHeight();
            int l3 = ((View) (obj)).getTop();
            int i5;
            int j5;
            boolean flag;
            if(l < mItemCount - 1)
                i1 = Math.max(mListPadding.bottom, mExtraScroll);
            else
                i1 = mListPadding.bottom;
            i5 = getVerticalSpacing();
            obj = AbsListView.this;
            j5 = mScrollDuration;
            if(l < mTargetPos)
                flag = true;
            else
                flag = false;
            ((AbsListView) (obj)).smoothScrollBy((i3 - (i - l3)) + i1 + i5, j5, true, flag);
            mLastSeenPos = l;
            if(l < mTargetPos)
                postOnAnimation(this);
              goto _L7
_L4:
            for(int j1 = getChildCount(); l == mBoundPos || j1 <= 1 || l + j1 >= mItemCount;)
            {
                reportScrollStateChange(0);
                return;
            }

            if(++l == mLastSeenPos)
            {
                postOnAnimation(this);
                return;
            }
            View view = getChildAt(1);
            int k1 = view.getHeight();
            int i4 = view.getTop();
            i = Math.max(mListPadding.bottom, mExtraScroll);
            if(l < mBoundPos)
            {
                smoothScrollBy(Math.max(0, (k1 + i4) - i), mScrollDuration, true, true);
                mLastSeenPos = l;
                postOnAnimation(this);
            } else
            if(i4 > i)
                smoothScrollBy(i4 - i, mScrollDuration, true, false);
            else
                reportScrollStateChange(0);
              goto _L7
_L3:
            if(l == mLastSeenPos)
            {
                postOnAnimation(this);
                return;
            }
            Object obj1 = getChildAt(0);
            if(obj1 == null)
                return;
            i = ((View) (obj1)).getTop();
            int l1;
            int j4;
            boolean flag1;
            if(l > 0)
                l1 = Math.max(mExtraScroll, mListPadding.top);
            else
                l1 = mListPadding.top;
            obj1 = AbsListView.this;
            j4 = mScrollDuration;
            if(l > mTargetPos)
                flag1 = true;
            else
                flag1 = false;
            ((AbsListView) (obj1)).smoothScrollBy(i - l1, j4, true, flag1);
            mLastSeenPos = l;
            if(l > mTargetPos)
                postOnAnimation(this);
              goto _L7
_L5:
            int i2 = getChildCount() - 2;
            if(i2 < 0)
                return;
            l += i2;
            if(l == mLastSeenPos)
            {
                postOnAnimation(this);
                return;
            }
            View view1 = getChildAt(i2);
            i2 = view1.getHeight();
            int k4 = view1.getTop();
            int j3 = Math.max(mListPadding.top, mExtraScroll);
            mLastSeenPos = l;
            if(l > mBoundPos)
            {
                smoothScrollBy(-(i - k4 - j3), mScrollDuration, true, true);
                postOnAnimation(this);
            } else
            {
                i -= j3;
                i2 = k4 + i2;
                if(i > i2)
                    smoothScrollBy(-(i - i2), mScrollDuration, true, false);
                else
                    reportScrollStateChange(0);
            }
              goto _L7
_L6:
            int k5;
            float f;
            float f1;
            float f2;
            if(mLastSeenPos == l)
            {
                postOnAnimation(this);
                return;
            }
            mLastSeenPos = l;
            int k3 = getChildCount();
            k5 = mTargetPos;
            i = (l + k3) - 1;
            View view2 = getChildAt(0);
            int j2 = view2.getHeight();
            View view3 = getChildAt(k3 - 1);
            int l4 = view3.getHeight();
            if((float)j2 == 0.0F)
                f = 1.0F;
            else
                f = (float)(view2.getTop() + j2) / (float)j2;
            if((float)l4 == 0.0F)
                f1 = 1.0F;
            else
                f1 = (float)((getHeight() + l4) - view3.getBottom()) / (float)l4;
            f2 = 0.0F;
            if(k5 >= l) goto _L9; else goto _L8
_L8:
            f = (float)(l - k5) + (1.0F - f) + 1.0F;
_L10:
            f = Math.min(Math.abs(f / (float)k3), 1.0F);
            if(k5 < l)
            {
                i = (int)((float)(-getHeight()) * f);
                j2 = (int)((float)mScrollDuration * f);
                smoothScrollBy(i, j2, true, true);
                postOnAnimation(this);
            } else
            if(k5 > i)
            {
                int j = (int)((float)getHeight() * f);
                int k2 = (int)((float)mScrollDuration * f);
                smoothScrollBy(j, k2, true, true);
                postOnAnimation(this);
            } else
            {
                int l2 = getChildAt(k5 - l).getTop() - mOffsetFromTop;
                int k = (int)((float)mScrollDuration * ((float)Math.abs(l2) / (float)getHeight()));
                smoothScrollBy(l2, k, true, false);
            }
_L7:
            if(true) goto _L1; else goto _L9
_L9:
            f = f2;
            if(k5 > i)
                f = (float)(k5 - i) + (1.0F - f1);
              goto _L10
        }

        public void start(int i)
        {
            stop();
            if(mDataChanged)
            {
                mPositionScrollAfterLayout = i. new Runnable() {

                    public void run()
                    {
                        start(position);
                    }

                    final PositionScroller this$1;
                    final int val$position;

            
            {
                this$1 = final_positionscroller;
                position = I.this;
                super();
            }
                }
;
                return;
            }
            int j = getChildCount();
            if(j == 0)
                return;
            int k = mFirstPosition;
            int l = (k + j) - 1;
            j = Math.max(0, Math.min(getCount() - 1, i));
            if(j < k)
            {
                i = (k - j) + 1;
                mMode = 2;
            } else
            if(j > l)
            {
                i = (j - l) + 1;
                mMode = 1;
            } else
            {
                scrollToVisible(j, -1, 200);
                return;
            }
            if(i > 0)
                mScrollDuration = 200 / i;
            else
                mScrollDuration = 200;
            mTargetPos = j;
            mBoundPos = -1;
            mLastSeenPos = -1;
            postOnAnimation(this);
        }

        public void start(final int position, int i)
        {
            stop();
            if(i == -1)
            {
                start(position);
                return;
            }
            if(mDataChanged)
            {
                mPositionScrollAfterLayout = i. new Runnable() {

                    public void run()
                    {
                        start(position, boundPosition);
                    }

                    final PositionScroller this$1;
                    final int val$boundPosition;
                    final int val$position;

            
            {
                this$1 = final_positionscroller;
                position = i;
                boundPosition = I.this;
                super();
            }
                }
;
                return;
            }
            int j = getChildCount();
            if(j == 0)
                return;
            int k = mFirstPosition;
            int l = (k + j) - 1;
            j = Math.max(0, Math.min(getCount() - 1, position));
            if(j < k)
            {
                l -= i;
                if(l < 1)
                    return;
                position = (k - j) + 1;
                k = l - 1;
                if(k < position)
                {
                    position = k;
                    mMode = 4;
                } else
                {
                    mMode = 2;
                }
            } else
            if(j > l)
            {
                k = i - k;
                if(k < 1)
                    return;
                position = (j - l) + 1;
                if(--k < position)
                {
                    position = k;
                    mMode = 3;
                } else
                {
                    mMode = 1;
                }
            } else
            {
                scrollToVisible(j, i, 200);
                return;
            }
            if(position > 0)
                mScrollDuration = 200 / position;
            else
                mScrollDuration = 200;
            mTargetPos = j;
            mBoundPos = i;
            mLastSeenPos = -1;
            postOnAnimation(this);
        }

        public void startWithOffset(int i, int j)
        {
            startWithOffset(i, j, 200);
        }

        public void startWithOffset(final int position, final int postOffset, int i)
        {
            stop();
            if(mDataChanged)
            {
                mPositionScrollAfterLayout = i. new Runnable() {

                    public void run()
                    {
                        startWithOffset(position, postOffset, duration);
                    }

                    final PositionScroller this$1;
                    final int val$duration;
                    final int val$position;
                    final int val$postOffset;

            
            {
                this$1 = final_positionscroller;
                position = i;
                postOffset = j;
                duration = I.this;
                super();
            }
                }
;
                return;
            }
            int j = getChildCount();
            if(j == 0)
                return;
            postOffset += getPaddingTop();
            mTargetPos = Math.max(0, Math.min(getCount() - 1, position));
            mOffsetFromTop = postOffset;
            mBoundPos = -1;
            mLastSeenPos = -1;
            mMode = 5;
            int k = mFirstPosition;
            position = (k + j) - 1;
            float f;
            if(mTargetPos < k)
                position = k - mTargetPos;
            else
            if(mTargetPos > position)
            {
                position = mTargetPos - position;
            } else
            {
                position = getChildAt(mTargetPos - k).getTop();
                smoothScrollBy(position - postOffset, i, true, false);
                return;
            }
            f = (float)position / (float)j;
            if(f >= 1.0F)
                i = (int)((float)i / f);
            mScrollDuration = i;
            mLastSeenPos = -1;
            postOnAnimation(this);
        }

        public void stop()
        {
            removeCallbacks(this);
        }

        private static final int MOVE_DOWN_BOUND = 3;
        private static final int MOVE_DOWN_POS = 1;
        private static final int MOVE_OFFSET = 5;
        private static final int MOVE_UP_BOUND = 4;
        private static final int MOVE_UP_POS = 2;
        private static final int SCROLL_DURATION = 200;
        private int mBoundPos;
        private final int mExtraScroll;
        private int mLastSeenPos;
        private int mMode;
        private int mOffsetFromTop;
        private int mScrollDuration;
        private int mTargetPos;
        final AbsListView this$0;

        PositionScroller()
        {
            this$0 = AbsListView.this;
            super();
            mExtraScroll = ViewConfiguration.get(AbsListView._2D_get1(AbsListView.this)).getScaledFadingEdgeLength();
        }
    }

    class RecycleBin
    {

        static RecyclerListener _2D_get0(RecycleBin recyclebin)
        {
            return recyclebin.mRecyclerListener;
        }

        static RecyclerListener _2D_set0(RecycleBin recyclebin, RecyclerListener recyclerlistener)
        {
            recyclebin.mRecyclerListener = recyclerlistener;
            return recyclerlistener;
        }

        private void clearScrap(ArrayList arraylist)
        {
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
                removeDetachedView((View)arraylist.remove(i - 1 - j), false);

        }

        private void clearScrapForRebind(View view)
        {
            view.clearAccessibilityFocus();
            view.setAccessibilityDelegate(null);
        }

        private ArrayList getSkippedScrap()
        {
            if(mSkippedScrap == null)
                mSkippedScrap = new ArrayList();
            return mSkippedScrap;
        }

        private void pruneScrapViews()
        {
            int i = mActiveViews.length;
            int j = mViewTypeCount;
            ArrayList aarraylist[] = mScrapViews;
            for(int k = 0; k < j; k++)
            {
                ArrayList arraylist = aarraylist[k];
                for(int j1 = arraylist.size(); j1 > i;)
                {
                    j1--;
                    arraylist.remove(j1);
                }

            }

            Object obj = mTransientStateViews;
            if(obj != null)
            {
                int k1;
                for(int l = 0; l < ((SparseArray) (obj)).size(); l = k1 + 1)
                {
                    View view = (View)((SparseArray) (obj)).valueAt(l);
                    k1 = l;
                    if(!view.hasTransientState())
                    {
                        removeDetachedView(view, false);
                        ((SparseArray) (obj)).removeAt(l);
                        k1 = l - 1;
                    }
                }

            }
            obj = mTransientStateViewsById;
            if(obj != null)
            {
                int l1;
                for(int i1 = 0; i1 < ((LongSparseArray) (obj)).size(); i1 = l1 + 1)
                {
                    View view1 = (View)((LongSparseArray) (obj)).valueAt(i1);
                    l1 = i1;
                    if(!view1.hasTransientState())
                    {
                        removeDetachedView(view1, false);
                        ((LongSparseArray) (obj)).removeAt(i1);
                        l1 = i1 - 1;
                    }
                }

            }
        }

        private void removeDetachedView(View view, boolean flag)
        {
            view.setAccessibilityDelegate(null);
            AbsListView._2D_wrap6(AbsListView.this, view, flag);
        }

        private View retrieveFromScrap(ArrayList arraylist, int i)
        {
            int j = arraylist.size();
            if(j > 0)
            {
                for(int k = j - 1; k >= 0; k--)
                {
                    LayoutParams layoutparams = (LayoutParams)((View)arraylist.get(k)).getLayoutParams();
                    if(mAdapterHasStableIds)
                    {
                        if(mAdapter.getItemId(i) == layoutparams.itemId)
                            return (View)arraylist.remove(k);
                        continue;
                    }
                    if(layoutparams.scrappedFromPosition == i)
                    {
                        arraylist = (View)arraylist.remove(k);
                        clearScrapForRebind(arraylist);
                        return arraylist;
                    }
                }

                arraylist = (View)arraylist.remove(j - 1);
                clearScrapForRebind(arraylist);
                return arraylist;
            } else
            {
                return null;
            }
        }

        void addScrapView(View view, int i)
        {
            LayoutParams layoutparams;
            int j;
            layoutparams = (LayoutParams)view.getLayoutParams();
            if(layoutparams == null)
                return;
            layoutparams.scrappedFromPosition = i;
            j = layoutparams.viewType;
            if(!shouldRecycleViewType(j))
            {
                if(j != -2)
                    getSkippedScrap().add(view);
                return;
            }
            view.dispatchStartTemporaryDetach();
            notifyViewAccessibilityStateChangedIfNeeded(1);
            if(!view.hasTransientState()) goto _L2; else goto _L1
_L1:
            if(mAdapter != null && mAdapterHasStableIds)
            {
                if(mTransientStateViewsById == null)
                    mTransientStateViewsById = new LongSparseArray();
                mTransientStateViewsById.put(layoutparams.itemId, view);
            } else
            if(!mDataChanged)
            {
                if(mTransientStateViews == null)
                    mTransientStateViews = new SparseArray();
                mTransientStateViews.put(i, view);
            } else
            {
                clearScrapForRebind(view);
                getSkippedScrap().add(view);
            }
_L4:
            return;
_L2:
            clearScrapForRebind(view);
            if(mViewTypeCount == 1)
                mCurrentScrap.add(view);
            else
                mScrapViews[j].add(view);
            if(mRecyclerListener != null)
                mRecyclerListener.onMovedToScrapHeap(view);
            if(true) goto _L4; else goto _L3
_L3:
        }

        void clear()
        {
            if(mViewTypeCount == 1)
            {
                clearScrap(mCurrentScrap);
            } else
            {
                int i = mViewTypeCount;
                int j = 0;
                while(j < i) 
                {
                    clearScrap(mScrapViews[j]);
                    j++;
                }
            }
            clearTransientStateViews();
        }

        void clearTransientStateViews()
        {
            Object obj = mTransientStateViews;
            if(obj != null)
            {
                int i = ((SparseArray) (obj)).size();
                for(int k = 0; k < i; k++)
                    removeDetachedView((View)((SparseArray) (obj)).valueAt(k), false);

                ((SparseArray) (obj)).clear();
            }
            obj = mTransientStateViewsById;
            if(obj != null)
            {
                int j = ((LongSparseArray) (obj)).size();
                for(int l = 0; l < j; l++)
                    removeDetachedView((View)((LongSparseArray) (obj)).valueAt(l), false);

                ((LongSparseArray) (obj)).clear();
            }
        }

        void fillActiveViews(int i, int j)
        {
            if(mActiveViews.length < i)
                mActiveViews = new View[i];
            mFirstActivePosition = j;
            View aview[] = mActiveViews;
            for(int k = 0; k < i; k++)
            {
                View view = getChildAt(k);
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                if(layoutparams != null && layoutparams.viewType != -2)
                {
                    aview[k] = view;
                    layoutparams.scrappedFromPosition = j + k;
                }
            }

        }

        void fullyDetachScrapViews()
        {
            int i = mViewTypeCount;
            ArrayList aarraylist[] = mScrapViews;
            for(int j = 0; j < i; j++)
            {
                ArrayList arraylist = aarraylist[j];
                for(int k = arraylist.size() - 1; k >= 0; k--)
                {
                    View view = (View)arraylist.get(k);
                    if(view.isTemporarilyDetached())
                        removeDetachedView(view, false);
                }

            }

        }

        View getActiveView(int i)
        {
            i -= mFirstActivePosition;
            View aview[] = mActiveViews;
            if(i >= 0 && i < aview.length)
            {
                View view = aview[i];
                aview[i] = null;
                return view;
            } else
            {
                return null;
            }
        }

        View getScrapView(int i)
        {
            int j = mAdapter.getItemViewType(i);
            if(j < 0)
                return null;
            if(mViewTypeCount == 1)
                return retrieveFromScrap(mCurrentScrap, i);
            if(j < mScrapViews.length)
                return retrieveFromScrap(mScrapViews[j], i);
            else
                return null;
        }

        View getTransientStateView(int i)
        {
            if(mAdapter != null && mAdapterHasStableIds && mTransientStateViewsById != null)
            {
                long l = mAdapter.getItemId(i);
                View view = (View)mTransientStateViewsById.get(l);
                mTransientStateViewsById.remove(l);
                return view;
            }
            if(mTransientStateViews != null)
            {
                i = mTransientStateViews.indexOfKey(i);
                if(i >= 0)
                {
                    View view1 = (View)mTransientStateViews.valueAt(i);
                    mTransientStateViews.removeAt(i);
                    return view1;
                }
            }
            return null;
        }

        public void markChildrenDirty()
        {
            if(mViewTypeCount == 1)
            {
                ArrayList arraylist = mCurrentScrap;
                int i = arraylist.size();
                for(int i1 = 0; i1 < i; i1++)
                    ((View)arraylist.get(i1)).forceLayout();

            } else
            {
                int i2 = mViewTypeCount;
                for(int j1 = 0; j1 < i2; j1++)
                {
                    ArrayList arraylist1 = mScrapViews[j1];
                    int j2 = arraylist1.size();
                    for(int j = 0; j < j2; j++)
                        ((View)arraylist1.get(j)).forceLayout();

                }

            }
            if(mTransientStateViews != null)
            {
                int k = mTransientStateViews.size();
                for(int k1 = 0; k1 < k; k1++)
                    ((View)mTransientStateViews.valueAt(k1)).forceLayout();

            }
            if(mTransientStateViewsById != null)
            {
                int l = mTransientStateViewsById.size();
                for(int l1 = 0; l1 < l; l1++)
                    ((View)mTransientStateViewsById.valueAt(l1)).forceLayout();

            }
        }

        void reclaimScrapViews(List list)
        {
            if(mViewTypeCount == 1)
            {
                list.addAll(mCurrentScrap);
            } else
            {
                int i = mViewTypeCount;
                ArrayList aarraylist[] = mScrapViews;
                int j = 0;
                while(j < i) 
                {
                    list.addAll(aarraylist[j]);
                    j++;
                }
            }
        }

        void removeSkippedScrap()
        {
            if(mSkippedScrap == null)
                return;
            int i = mSkippedScrap.size();
            for(int j = 0; j < i; j++)
                removeDetachedView((View)mSkippedScrap.get(j), false);

            mSkippedScrap.clear();
        }

        void scrapActiveViews()
        {
            View aview[] = mActiveViews;
            boolean flag;
            boolean flag1;
            Object obj;
            int i;
            if(mRecyclerListener != null)
                flag = true;
            else
                flag = false;
            if(mViewTypeCount > 1)
                flag1 = true;
            else
                flag1 = false;
            obj = mCurrentScrap;
            i = aview.length - 1;
            while(i >= 0) 
            {
                View view = aview[i];
                Object obj1 = obj;
                if(view != null)
                {
                    obj1 = (LayoutParams)view.getLayoutParams();
                    int j = ((LayoutParams) (obj1)).viewType;
                    aview[i] = null;
                    if(view.hasTransientState())
                    {
                        view.dispatchStartTemporaryDetach();
                        if(mAdapter != null && mAdapterHasStableIds)
                        {
                            if(mTransientStateViewsById == null)
                                mTransientStateViewsById = new LongSparseArray();
                            long l = mAdapter.getItemId(mFirstActivePosition + i);
                            mTransientStateViewsById.put(l, view);
                            obj1 = obj;
                        } else
                        if(!mDataChanged)
                        {
                            if(mTransientStateViews == null)
                                mTransientStateViews = new SparseArray();
                            mTransientStateViews.put(mFirstActivePosition + i, view);
                            obj1 = obj;
                        } else
                        {
                            obj1 = obj;
                            if(j != -2)
                            {
                                removeDetachedView(view, false);
                                obj1 = obj;
                            }
                        }
                    } else
                    if(!shouldRecycleViewType(j))
                    {
                        obj1 = obj;
                        if(j != -2)
                        {
                            removeDetachedView(view, false);
                            obj1 = obj;
                        }
                    } else
                    {
                        if(flag1)
                            obj = mScrapViews[j];
                        obj1.scrappedFromPosition = mFirstActivePosition + i;
                        removeDetachedView(view, false);
                        ((ArrayList) (obj)).add(view);
                        obj1 = obj;
                        if(flag)
                        {
                            mRecyclerListener.onMovedToScrapHeap(view);
                            obj1 = obj;
                        }
                    }
                }
                i--;
                obj = obj1;
            }
            pruneScrapViews();
        }

        void setCacheColorHint(int i)
        {
            if(mViewTypeCount == 1)
            {
                ArrayList arraylist = mCurrentScrap;
                int j = arraylist.size();
                for(int i1 = 0; i1 < j; i1++)
                    ((View)arraylist.get(i1)).setDrawingCacheBackgroundColor(i);

            } else
            {
                int l1 = mViewTypeCount;
                for(int j1 = 0; j1 < l1; j1++)
                {
                    ArrayList arraylist1 = mScrapViews[j1];
                    int i2 = arraylist1.size();
                    for(int k = 0; k < i2; k++)
                        ((View)arraylist1.get(k)).setDrawingCacheBackgroundColor(i);

                }

            }
            View aview[] = mActiveViews;
            int l = aview.length;
            for(int k1 = 0; k1 < l; k1++)
            {
                View view = aview[k1];
                if(view != null)
                    view.setDrawingCacheBackgroundColor(i);
            }

        }

        public void setViewTypeCount(int i)
        {
            if(i < 1)
                throw new IllegalArgumentException("Can't have a viewTypeCount < 1");
            ArrayList aarraylist[] = new ArrayList[i];
            for(int j = 0; j < i; j++)
                aarraylist[j] = new ArrayList();

            mViewTypeCount = i;
            mCurrentScrap = aarraylist[0];
            mScrapViews = aarraylist;
        }

        public boolean shouldRecycleViewType(int i)
        {
            boolean flag = false;
            if(i >= 0)
                flag = true;
            return flag;
        }

        private View mActiveViews[];
        private ArrayList mCurrentScrap;
        private int mFirstActivePosition;
        private RecyclerListener mRecyclerListener;
        private ArrayList mScrapViews[];
        private ArrayList mSkippedScrap;
        private SparseArray mTransientStateViews;
        private LongSparseArray mTransientStateViewsById;
        private int mViewTypeCount;
        final AbsListView this$0;

        RecycleBin()
        {
            this$0 = AbsListView.this;
            super();
            mActiveViews = new View[0];
        }
    }

    public static interface RecyclerListener
    {

        public abstract void onMovedToScrapHeap(View view);
    }

    static class SavedState extends android.view.View.BaseSavedState
    {

        public String toString()
        {
            return (new StringBuilder()).append("AbsListView.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" selectedId=").append(selectedId).append(" firstId=").append(firstId).append(" viewTop=").append(viewTop).append(" position=").append(position).append(" height=").append(height).append(" filter=").append(filter).append(" checkState=").append(checkState).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeLong(selectedId);
            parcel.writeLong(firstId);
            parcel.writeInt(viewTop);
            parcel.writeInt(position);
            parcel.writeInt(height);
            parcel.writeString(filter);
            if(inActionMode)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
            parcel.writeInt(checkedItemCount);
            parcel.writeSparseBooleanArray(checkState);
            if(checkIdState != null)
                i = checkIdState.size();
            else
                i = 0;
            parcel.writeInt(i);
            for(int j = 0; j < i; j++)
            {
                parcel.writeLong(checkIdState.keyAt(j));
                parcel.writeInt(((Integer)checkIdState.valueAt(j)).intValue());
            }

        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        LongSparseArray checkIdState;
        SparseBooleanArray checkState;
        int checkedItemCount;
        String filter;
        long firstId;
        int height;
        boolean inActionMode;
        int position;
        long selectedId;
        int viewTop;


        private SavedState(Parcel parcel)
        {
            boolean flag = false;
            super(parcel);
            selectedId = parcel.readLong();
            firstId = parcel.readLong();
            viewTop = parcel.readInt();
            position = parcel.readInt();
            height = parcel.readInt();
            filter = parcel.readString();
            if(parcel.readByte() != 0)
                flag = true;
            inActionMode = flag;
            checkedItemCount = parcel.readInt();
            checkState = parcel.readSparseBooleanArray();
            int i = parcel.readInt();
            if(i > 0)
            {
                checkIdState = new LongSparseArray();
                for(int j = 0; j < i; j++)
                {
                    long l = parcel.readLong();
                    int k = parcel.readInt();
                    checkIdState.put(l, Integer.valueOf(k));
                }

            }
        }

        SavedState(Parcel parcel, SavedState savedstate)
        {
            this(parcel);
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }

    public static interface SelectionBoundsAdjuster
    {

        public abstract void adjustListItemSelectionBounds(Rect rect);
    }

    private class WindowRunnnable
    {

        public void rememberWindowAttachCount()
        {
            mOriginalAttachCount = AbsListView._2D_wrap4(AbsListView.this);
        }

        public boolean sameWindow()
        {
            boolean flag;
            if(AbsListView._2D_wrap4(AbsListView.this) == mOriginalAttachCount)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private int mOriginalAttachCount;
        final AbsListView this$0;

        private WindowRunnnable()
        {
            this$0 = AbsListView.this;
            super();
        }

        WindowRunnnable(WindowRunnnable windowrunnnable)
        {
            this();
        }
    }


    static int _2D_get0(AbsListView abslistview)
    {
        return abslistview.mActivePointerId;
    }

    static Context _2D_get1(AbsListView abslistview)
    {
        return abslistview.mContext;
    }

    static int _2D_get10(AbsListView abslistview)
    {
        return abslistview.mPaddingTop;
    }

    static CheckForLongPress _2D_get11(AbsListView abslistview)
    {
        return abslistview.mPendingCheckForLongPress;
    }

    static int _2D_get12(AbsListView abslistview)
    {
        return abslistview.mPersistentDrawingCache;
    }

    static int _2D_get13(AbsListView abslistview)
    {
        return abslistview.mScrollY;
    }

    static float[] _2D_get14(AbsListView abslistview)
    {
        return abslistview.mTmpPoint;
    }

    static VelocityTracker _2D_get15(AbsListView abslistview)
    {
        return abslistview.mVelocityTracker;
    }

    static InputConnection _2D_get2(AbsListView abslistview)
    {
        return abslistview.mDefInputConnection;
    }

    static EdgeEffect _2D_get3(AbsListView abslistview)
    {
        return abslistview.mEdgeGlowBottom;
    }

    static EdgeEffect _2D_get4(AbsListView abslistview)
    {
        return abslistview.mEdgeGlowTop;
    }

    static FastScroller _2D_get5(AbsListView abslistview)
    {
        return abslistview.mFastScroll;
    }

    static android.os.StrictMode.Span _2D_get6(AbsListView abslistview)
    {
        return abslistview.mFlingStrictSpan;
    }

    static int _2D_get7(AbsListView abslistview)
    {
        return abslistview.mMaximumVelocity;
    }

    static int _2D_get8(AbsListView abslistview)
    {
        return abslistview.mMinimumVelocity;
    }

    static int _2D_get9(AbsListView abslistview)
    {
        return abslistview.mPaddingBottom;
    }

    static android.os.StrictMode.Span _2D_set0(AbsListView abslistview, android.os.StrictMode.Span span)
    {
        abslistview.mFlingStrictSpan = span;
        return span;
    }

    static boolean _2D_set1(AbsListView abslistview, boolean flag)
    {
        abslistview.mHasPerformedLongPress = flag;
        return flag;
    }

    static CheckForLongPress _2D_set2(AbsListView abslistview, CheckForLongPress checkforlongpress)
    {
        abslistview.mPendingCheckForLongPress = checkforlongpress;
        return checkforlongpress;
    }

    static Runnable _2D_set3(AbsListView abslistview, Runnable runnable)
    {
        abslistview.mTouchModeReset = runnable;
        return runnable;
    }

    static EditText _2D_wrap0(AbsListView abslistview)
    {
        return abslistview.getTextFilterInput();
    }

    static boolean _2D_wrap1(AbsListView abslistview)
    {
        return abslistview.contentFits();
    }

    static void _2D_wrap10(AbsListView abslistview, boolean flag)
    {
        abslistview.setFastScrollerEnabledUiThread(flag);
    }

    static boolean _2D_wrap2(AbsListView abslistview, View view)
    {
        return abslistview.isItemClickable(view);
    }

    static boolean _2D_wrap3(AbsListView abslistview, int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, boolean flag)
    {
        return abslistview.overScrollBy(i, j, k, l, i1, j1, k1, l1, flag);
    }

    static int _2D_wrap4(AbsListView abslistview)
    {
        return abslistview.getWindowAttachCount();
    }

    static void _2D_wrap5(AbsListView abslistview)
    {
        abslistview.clearScrollingCache();
    }

    static void _2D_wrap6(AbsListView abslistview, View view, boolean flag)
    {
        abslistview.removeDetachedView(view, flag);
    }

    static void _2D_wrap7(AbsListView abslistview, boolean flag)
    {
        abslistview.setChildrenDrawingCacheEnabled(flag);
    }

    static void _2D_wrap8(AbsListView abslistview, boolean flag)
    {
        abslistview.setChildrenDrawnWithCacheEnabled(flag);
    }

    static void _2D_wrap9(AbsListView abslistview, boolean flag)
    {
        abslistview.setFastScrollerAlwaysVisibleUiThread(flag);
    }

    public AbsListView(Context context)
    {
        super(context);
        mChoiceMode = 0;
        mLayoutMode = 0;
        mDeferNotifyDataSetChanged = false;
        mDrawSelectorOnTop = false;
        mSelectorPosition = -1;
        mSelectorRect = new Rect();
        mRecycler = new RecycleBin();
        mSelectionLeftPadding = 0;
        mSelectionTopPadding = 0;
        mSelectionRightPadding = 0;
        mSelectionBottomPadding = 0;
        mListPadding = new Rect();
        mWidthMeasureSpec = 0;
        mTouchMode = -1;
        mSelectedTop = 0;
        mSmoothScrollbarEnabled = true;
        mResurrectToPosition = -1;
        mContextMenuInfo = null;
        mLastTouchMode = -1;
        mScrollProfilingStarted = false;
        mFlingProfilingStarted = false;
        mScrollStrictSpan = null;
        mFlingStrictSpan = null;
        mLastScrollState = 0;
        mVelocityScale = 1.0F;
        mIsScrap = new boolean[1];
        mScrollOffset = new int[2];
        mScrollConsumed = new int[2];
        mTmpPoint = new float[2];
        mNestedYOffset = 0;
        mActivePointerId = -1;
        mDirection = 0;
        mIsFirstTouchMoveEvent = false;
        mNumTouchMoveEvent = 0;
        mDownMotionY = 0;
        mInertia = 0;
        mIsTouching = false;
        mScaleY = 1.0F;
        initAbsListView();
        mOwnerThread = Thread.currentThread();
        setVerticalScrollBarEnabled(true);
        context = context.obtainStyledAttributes(com.android.internal.R.styleable.View);
        initializeScrollbarsInternal(context);
        context.recycle();
    }

    public AbsListView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101006a);
    }

    public AbsListView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public AbsListView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mChoiceMode = 0;
        mLayoutMode = 0;
        mDeferNotifyDataSetChanged = false;
        mDrawSelectorOnTop = false;
        mSelectorPosition = -1;
        mSelectorRect = new Rect();
        mRecycler = new RecycleBin();
        mSelectionLeftPadding = 0;
        mSelectionTopPadding = 0;
        mSelectionRightPadding = 0;
        mSelectionBottomPadding = 0;
        mListPadding = new Rect();
        mWidthMeasureSpec = 0;
        mTouchMode = -1;
        mSelectedTop = 0;
        mSmoothScrollbarEnabled = true;
        mResurrectToPosition = -1;
        mContextMenuInfo = null;
        mLastTouchMode = -1;
        mScrollProfilingStarted = false;
        mFlingProfilingStarted = false;
        mScrollStrictSpan = null;
        mFlingStrictSpan = null;
        mLastScrollState = 0;
        mVelocityScale = 1.0F;
        mIsScrap = new boolean[1];
        mScrollOffset = new int[2];
        mScrollConsumed = new int[2];
        mTmpPoint = new float[2];
        mNestedYOffset = 0;
        mActivePointerId = -1;
        mDirection = 0;
        mIsFirstTouchMoveEvent = false;
        mNumTouchMoveEvent = 0;
        mDownMotionY = 0;
        mInertia = 0;
        mIsTouching = false;
        mScaleY = 1.0F;
        initAbsListView();
        mOwnerThread = Thread.currentThread();
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.AbsListView, i, j);
        attributeset = typedarray.getDrawable(0);
        if(attributeset != null)
            setSelector(attributeset);
        mDrawSelectorOnTop = typedarray.getBoolean(1, false);
        setStackFromBottom(typedarray.getBoolean(2, false));
        setScrollingCacheEnabled(typedarray.getBoolean(3, true));
        setTextFilterEnabled(typedarray.getBoolean(4, false));
        setTranscriptMode(typedarray.getInt(5, 0));
        setCacheColorHint(typedarray.getColor(6, 0));
        setSmoothScrollbarEnabled(typedarray.getBoolean(9, true));
        setChoiceMode(typedarray.getInt(7, 0));
        setFastScrollEnabled(typedarray.getBoolean(8, false));
        setFastScrollStyle(typedarray.getResourceId(11, 0));
        setFastScrollAlwaysVisible(typedarray.getBoolean(10, false));
        typedarray.recycle();
        if(context.getResources().getConfiguration().uiMode == 6)
            setRevealOnFocusHint(false);
    }

    private boolean acceptFilter()
    {
        boolean flag;
        if(mTextFilterEnabled && (getAdapter() instanceof Filterable))
        {
            if(((Filterable)getAdapter()).getFilter() != null)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private boolean canScrollDown()
    {
        int i = getChildCount();
        boolean flag;
        boolean flag1;
        if(mFirstPosition + i < mItemCount)
            flag = true;
        else
            flag = false;
        flag1 = flag;
        if(!flag)
        {
            flag1 = flag;
            if(i > 0)
                if(getChildAt(i - 1).getBottom() > mBottom - mListPadding.bottom)
                    flag1 = true;
                else
                    flag1 = false;
        }
        return flag1;
    }

    private boolean canScrollUp()
    {
        boolean flag;
        boolean flag1;
        if(mFirstPosition > 0)
            flag = true;
        else
            flag = false;
        flag1 = flag;
        if(!flag)
        {
            flag1 = flag;
            if(getChildCount() > 0)
                if(getChildAt(0).getTop() < mListPadding.top)
                    flag1 = true;
                else
                    flag1 = false;
        }
        return flag1;
    }

    private void clearScrollingCache()
    {
        if(!isHardwareAccelerated())
        {
            if(mClearScrollingCache == null)
                mClearScrollingCache = new Runnable() {

                    public void run()
                    {
                        if(mCachingStarted)
                        {
                            AbsListView abslistview = AbsListView.this;
                            mCachingActive = false;
                            abslistview.mCachingStarted = false;
                            AbsListView._2D_wrap8(AbsListView.this, false);
                            if((AbsListView._2D_get12(AbsListView.this) & 2) == 0)
                                AbsListView._2D_wrap7(AbsListView.this, false);
                            if(!isAlwaysDrawnWithCacheEnabled())
                                invalidate();
                        }
                    }

                    final AbsListView this$0;

            
            {
                this$0 = AbsListView.this;
                super();
            }
                }
;
            post(mClearScrollingCache);
        }
    }

    private boolean contentFits()
    {
        boolean flag = true;
        int i = getChildCount();
        if(i == 0)
            return true;
        if(i != mItemCount)
            return false;
        if(getChildAt(0).getTop() >= mListPadding.top)
        {
            if(getChildAt(i - 1).getBottom() > getHeight() - mListPadding.bottom)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private void createScrollingCache()
    {
        if(mScrollingCacheEnabled && mCachingStarted ^ true && isHardwareAccelerated() ^ true)
        {
            setChildrenDrawnWithCacheEnabled(true);
            setChildrenDrawingCacheEnabled(true);
            mCachingActive = true;
            mCachingStarted = true;
        }
    }

    private void createTextFilter(boolean flag)
    {
        if(mPopup == null)
        {
            PopupWindow popupwindow = new PopupWindow(getContext());
            popupwindow.setFocusable(false);
            popupwindow.setTouchable(false);
            popupwindow.setInputMethodMode(2);
            popupwindow.setContentView(getTextFilterInput());
            popupwindow.setWidth(-2);
            popupwindow.setHeight(-2);
            popupwindow.setBackgroundDrawable(null);
            mPopup = popupwindow;
            getViewTreeObserver().addOnGlobalLayoutListener(this);
            mGlobalLayoutListenerAddedFilter = true;
        }
        if(flag)
            mPopup.setAnimationStyle(0x1030301);
        else
            mPopup.setAnimationStyle(0x1030302);
    }

    private void dismissPopup()
    {
        if(mPopup != null)
            mPopup.dismiss();
    }

    private void drawSelector(Canvas canvas)
    {
        if(!mSelectorRect.isEmpty())
        {
            Drawable drawable = mSelector;
            drawable.setBounds(mSelectorRect);
            drawable.draw(canvas);
        }
    }

    private void finishGlows()
    {
        if(mEdgeGlowTop != null)
        {
            mEdgeGlowTop.finish();
            mEdgeGlowBottom.finish();
        }
    }

    static int getDistance(Rect rect, Rect rect1, int i)
    {
        i;
        JVM INSTR lookupswitch 6: default 60
    //                   1: 245
    //                   2: 245
    //                   17: 167
    //                   33: 206
    //                   66: 71
    //                   130: 128;
           goto _L1 _L2 _L2 _L3 _L4 _L5 _L6
_L1:
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT, FOCUS_FORWARD, FOCUS_BACKWARD}.");
_L5:
        int j;
        int k;
        int l;
        j = rect.right;
        k = rect.top + rect.height() / 2;
        i = rect1.left;
        l = rect1.top + rect1.height() / 2;
_L8:
        i -= j;
        l -= k;
        return l * l + i * i;
_L6:
        j = rect.left + rect.width() / 2;
        k = rect.bottom;
        i = rect1.left + rect1.width() / 2;
        l = rect1.top;
        continue; /* Loop/switch isn't completed */
_L3:
        j = rect.left;
        k = rect.top + rect.height() / 2;
        i = rect1.right;
        l = rect1.top + rect1.height() / 2;
        continue; /* Loop/switch isn't completed */
_L4:
        j = rect.left + rect.width() / 2;
        k = rect.top;
        i = rect1.left + rect1.width() / 2;
        l = rect1.bottom;
        continue; /* Loop/switch isn't completed */
_L2:
        j = rect.right + rect.width() / 2;
        k = rect.top + rect.height() / 2;
        i = rect1.left + rect1.width() / 2;
        l = rect1.top + rect1.height() / 2;
        if(true) goto _L8; else goto _L7
_L7:
    }

    private int[] getDrawableStateForSelector()
    {
        if(mIsChildViewEnabled)
            return super.getDrawableState();
        int i = ENABLED_STATE_SET[0];
        int ai[] = onCreateDrawableState(1);
        byte byte0 = -1;
        int j = ai.length - 1;
        do
        {
label0:
            {
                int k = byte0;
                if(j >= 0)
                {
                    if(ai[j] != i)
                        break label0;
                    k = j;
                }
                if(k >= 0)
                    System.arraycopy(ai, k + 1, ai, k, ai.length - k - 1);
                return ai;
            }
            j--;
        } while(true);
    }

    private EditText getTextFilterInput()
    {
        if(mTextFilter == null)
        {
            mTextFilter = (EditText)LayoutInflater.from(getContext()).inflate(0x1090118, null);
            mTextFilter.setRawInputType(177);
            mTextFilter.setImeOptions(0x10000000);
            mTextFilter.addTextChangedListener(this);
        }
        return mTextFilter;
    }

    private void initAbsListView()
    {
        setClickable(true);
        setFocusableInTouchMode(true);
        setWillNotDraw(false);
        setAlwaysDrawnWithCacheEnabled(false);
        setScrollingCacheEnabled(true);
        ViewConfiguration viewconfiguration = ViewConfiguration.get(mContext);
        mTouchSlop = viewconfiguration.getScaledTouchSlop();
        mVerticalScrollFactor = viewconfiguration.getScaledVerticalScrollFactor();
        if(OPTS_INPUT)
        {
            double d = Double.parseDouble(MOVE_TOUCH_SLOP);
            if(d > 0.0D)
            {
                if(d < 0.59999999999999998D)
                    mMoveAcceleration = (int)((double)mTouchSlop * 0.59999999999999998D);
                else
                if(d >= 0.59999999999999998D && d < 1.0D)
                    mMoveAcceleration = (int)((double)mTouchSlop * d);
                else
                    mMoveAcceleration = mTouchSlop;
            } else
            {
                mMoveAcceleration = mTouchSlop;
            }
        }
        mMinimumVelocity = viewconfiguration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = viewconfiguration.getScaledMaximumFlingVelocity();
        mOverscrollDistance = viewconfiguration.getScaledOverscrollDistance();
        mOverflingDistance = viewconfiguration.getScaledOverflingDistance();
        mDensityScale = getContext().getResources().getDisplayMetrics().density;
    }

    private void initOrResetVelocityTracker()
    {
        if(mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
        else
            mVelocityTracker.clear();
    }

    private void initVelocityTrackerIfNotExists()
    {
        if(mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
    }

    private void invalidateBottomGlow()
    {
        if(mEdgeGlowBottom == null)
            return;
        boolean flag = getClipToPadding();
        int i;
        int j;
        int k;
        if(flag)
            i = getHeight() - mPaddingBottom;
        else
            i = getHeight();
        if(flag)
            j = mPaddingLeft;
        else
            j = 0;
        if(flag)
            k = getWidth() - mPaddingRight;
        else
            k = getWidth();
        invalidate(j, i - mEdgeGlowBottom.getMaxHeight(), k, i);
    }

    private void invalidateTopGlow()
    {
        if(mEdgeGlowTop == null)
            return;
        boolean flag = getClipToPadding();
        int i;
        int j;
        int k;
        if(flag)
            i = mPaddingTop;
        else
            i = 0;
        if(flag)
            j = mPaddingLeft;
        else
            j = 0;
        if(flag)
            k = getWidth() - mPaddingRight;
        else
            k = getWidth();
        invalidate(j, i, k, mEdgeGlowTop.getMaxHeight() + i);
    }

    private boolean isItemClickable(View view)
    {
        return view.hasExplicitFocusable() ^ true;
    }

    private boolean isOwnerThread()
    {
        boolean flag;
        if(mOwnerThread == Thread.currentThread())
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void onSecondaryPointerUp(MotionEvent motionevent)
    {
        int i = (motionevent.getAction() & 0xff00) >> 8;
        if(motionevent.getPointerId(i) == mActivePointerId)
        {
            if(i == 0)
                i = 1;
            else
                i = 0;
            mMotionX = (int)motionevent.getX(i);
            mMotionY = (int)motionevent.getY(i);
            mMotionCorrection = 0;
            mActivePointerId = motionevent.getPointerId(i);
        }
    }

    private void onTouchCancel()
    {
        if(mIsTouching)
        {
            mIsTouching = false;
            AbsListViewInjector.resetScale(this);
        }
        mTouchMode;
        JVM INSTR tableswitch 5 6: default 44
    //                   5 121
    //                   6 94;
           goto _L1 _L2 _L3
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        mTouchMode = -1;
        setPressed(false);
        View view = getChildAt(mMotionPosition - mFirstPosition);
        if(view != null)
            view.setPressed(false);
        clearScrollingCache();
        removeCallbacks(mPendingCheckForLongPress);
        recycleVelocityTracker();
_L5:
        if(mEdgeGlowTop != null)
        {
            mEdgeGlowTop.onRelease();
            mEdgeGlowBottom.onRelease();
        }
        mActivePointerId = -1;
        return;
_L2:
        if(mFlingRunnable == null)
            mFlingRunnable = new FlingRunnable();
        mFlingRunnable.startSpringback();
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void onTouchDown(MotionEvent motionevent)
    {
        mHasPerformedLongPress = false;
        AbsListViewInjector.initOnTouchDown(this, motionevent);
        mActivePointerId = motionevent.getPointerId(0);
        if(mTouchMode != 6) goto _L2; else goto _L1
_L1:
        mFlingRunnable.endFling();
        if(mPositionScroller != null)
            mPositionScroller.stop();
        mTouchMode = 5;
        mMotionX = (int)motionevent.getX();
        mMotionY = (int)motionevent.getY();
        mLastY = mMotionY;
        mMotionCorrection = 0;
        mDirection = 0;
_L4:
        if(mTouchMode == 0 && mMotionPosition != -1 && performButtonActionOnTouchDown(motionevent))
            removeCallbacks(mPendingCheckForTap);
        return;
_L2:
        int i;
        int j;
        int k;
        int l;
        i = (int)motionevent.getX();
        j = (int)motionevent.getY();
        k = pointToPosition(i, j);
        l = k;
        if(!mDataChanged)
        {
            if(mTouchMode != 4)
                break; /* Loop/switch isn't completed */
            createScrollingCache();
            mTouchMode = 3;
            mMotionCorrection = 0;
            l = findMotionRow(j);
            mFlingRunnable.flywheelTouch();
        }
_L5:
        if(l >= 0)
            mMotionViewOriginalTop = getChildAt(l - mFirstPosition).getTop();
        mMotionX = i;
        mMotionY = j;
        mMotionPosition = l;
        mLastY = 0x80000000;
        if(true) goto _L4; else goto _L3
_L3:
        l = k;
        if(k >= 0)
        {
            l = k;
            if(((ListAdapter)getAdapter()).isEnabled(k))
            {
                mTouchMode = 0;
                if(mPendingCheckForTap == null)
                    mPendingCheckForTap = new CheckForTap(null);
                mPendingCheckForTap.x = motionevent.getX();
                mPendingCheckForTap.y = motionevent.getY();
                postDelayed(mPendingCheckForTap, ViewConfiguration.getTapTimeout());
                l = k;
            }
        }
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    private void onTouchMove(MotionEvent motionevent, MotionEvent motionevent1)
    {
        int i;
        int j;
        int k;
        if(mHasPerformedLongPress)
            return;
        i = motionevent.findPointerIndex(mActivePointerId);
        j = i;
        if(i == -1)
        {
            j = 0;
            mActivePointerId = motionevent.getPointerId(0);
        }
        if(mDataChanged)
            layoutChildren();
        i = (int)motionevent.getY(j);
        if(!mIsTouching)
            AbsListViewInjector.initOnTouchDown(this, motionevent);
        mInertia = AbsListViewInjector.getPanSpeed(this, i);
        k = mDownMotionY;
        mTouchMode;
        JVM INSTR tableswitch -1 5: default 128
    //                   -1 306
    //                   0 129
    //                   1 129
    //                   2 129
    //                   3 273
    //                   4 128
    //                   5 273;
           goto _L1 _L2 _L3 _L3 _L3 _L4 _L1 _L4
_L1:
        return;
_L3:
        if(!startScrollIfNeeded((int)motionevent.getX(j), i, motionevent1))
        {
            motionevent1 = getChildAt(mMotionPosition - mFirstPosition);
            float f = motionevent.getX(j);
            if(!pointInView(f, i, mTouchSlop))
            {
                setPressed(false);
                if(motionevent1 != null)
                    motionevent1.setPressed(false);
                if(mTouchMode == 0)
                    motionevent = mPendingCheckForTap;
                else
                    motionevent = mPendingCheckForLongPress;
                removeCallbacks(motionevent);
                mTouchMode = 2;
                updateSelectorState();
            } else
            if(motionevent1 != null)
            {
                motionevent = mTmpPoint;
                motionevent[0] = f;
                motionevent[1] = i;
                transformPointToViewLocal(motionevent, motionevent1);
                motionevent1.drawableHotspotChanged(motionevent[0], motionevent[1]);
            }
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(AbsListViewInjector.setListScaleIfNeeded(this, i - k))
        {
            mLastY = i;
            return;
        }
        scrollIfNeeded((int)motionevent.getX(j), i, motionevent1);
        continue; /* Loop/switch isn't completed */
_L2:
        mInertia = 0;
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void onTouchUp(MotionEvent motionevent)
    {
        if(mIsTouching)
            mIsTouching = false;
        mTouchMode;
        JVM INSTR tableswitch 0 5: default 56
    //                   0 124
    //                   1 124
    //                   2 124
    //                   3 544
    //                   4 56
    //                   5 912;
           goto _L1 _L2 _L2 _L2 _L3 _L1 _L4
_L1:
        setPressed(false);
        if(mEdgeGlowTop != null)
        {
            mEdgeGlowTop.onRelease();
            mEdgeGlowBottom.onRelease();
        }
        invalidate();
        removeCallbacks(mPendingCheckForLongPress);
        recycleVelocityTracker();
        mActivePointerId = -1;
        if(mScrollStrictSpan != null)
        {
            mScrollStrictSpan.finish();
            mScrollStrictSpan = null;
        }
        return;
_L2:
        int i = mMotionPosition;
        final View child = getChildAt(i - mFirstPosition);
        if(child != null)
        {
            if(mTouchMode != 0)
                child.setPressed(false);
            float f = motionevent.getX();
            boolean flag;
            if(f > (float)mListPadding.left && f < (float)(getWidth() - mListPadding.right))
                flag = true;
            else
                flag = false;
            if(flag && child.hasExplicitFocusable() ^ true)
            {
                if(mPerformClick == null)
                    mPerformClick = new PerformClick(null);
                PerformClick performclick = mPerformClick;
                performclick.mClickMotionPosition = i;
                performclick.rememberWindowAttachCount();
                mResurrectToPosition = i;
                if(mTouchMode == 0 || mTouchMode == 1)
                {
                    Object obj;
                    if(mTouchMode == 0)
                        obj = mPendingCheckForTap;
                    else
                        obj = mPendingCheckForLongPress;
                    removeCallbacks(((Runnable) (obj)));
                    mLayoutMode = 0;
                    if(!mDataChanged && mAdapter.isEnabled(i))
                    {
                        mTouchMode = 1;
                        setSelectedPositionInt(mMotionPosition);
                        layoutChildren();
                        child.setPressed(true);
                        positionSelector(mMotionPosition, child);
                        setPressed(true);
                        if(mSelector != null)
                        {
                            obj = mSelector.getCurrent();
                            if(obj != null && (obj instanceof TransitionDrawable))
                                ((TransitionDrawable)obj).resetTransition();
                            mSelector.setHotspot(f, motionevent.getY());
                        }
                        if(!mDataChanged && mIsDetaching ^ true && isAttachedToWindow() && !post(performclick))
                            performclick.run();
                        if(mTouchModeReset != null)
                            removeCallbacks(mTouchModeReset);
                        mTouchModeReset = new Runnable() {

                            public void run()
                            {
                                AbsListView._2D_set3(AbsListView.this, null);
                                mTouchMode = -1;
                                child.setPressed(false);
                                setPressed(false);
                            }

                            final AbsListView this$0;
                            final View val$child;

            
            {
                this$0 = AbsListView.this;
                child = view;
                super();
            }
                        }
;
                        postDelayed(mTouchModeReset, ViewConfiguration.getPressedStateDuration());
                    } else
                    {
                        mTouchMode = -1;
                        updateSelectorState();
                    }
                    return;
                }
                if(!mDataChanged && mAdapter.isEnabled(i))
                    performclick.run();
            }
        }
        mTouchMode = -1;
        updateSelectorState();
        continue; /* Loop/switch isn't completed */
_L3:
        int l = getChildCount();
        if(l > 0)
        {
            int j = getChildAt(0).getTop();
            int i1 = getChildAt(l - 1).getBottom();
            int j1 = mListPadding.top;
            int k1 = getHeight() - mListPadding.bottom;
            if(mFirstPosition == 0 && j >= j1 && mFirstPosition + l < mItemCount && i1 <= getHeight() - k1)
            {
                mTouchMode = -1;
                reportScrollStateChange(0);
            } else
            {
                motionevent = mVelocityTracker;
                motionevent.computeCurrentVelocity(1000, mMaximumVelocity);
                int l1 = (int)(motionevent.getYVelocity(mActivePointerId) * mVelocityScale);
                boolean flag1;
                if(Math.abs(l1) > mMinimumVelocity)
                    flag1 = true;
                else
                    flag1 = false;
                if(flag1 && (mFirstPosition != 0 || j != j1 - mOverscrollDistance) && (mFirstPosition + l != mItemCount || i1 != mOverscrollDistance + k1))
                {
                    if(!dispatchNestedPreFling(0.0F, -l1))
                    {
                        if(mFlingRunnable == null)
                            mFlingRunnable = new FlingRunnable();
                        reportScrollStateChange(2);
                        mFlingRunnable.start(-l1);
                        dispatchNestedFling(0.0F, -l1, true);
                    } else
                    {
                        mTouchMode = -1;
                        reportScrollStateChange(0);
                    }
                } else
                {
                    mTouchMode = -1;
                    reportScrollStateChange(0);
                    if(mFlingRunnable != null)
                        mFlingRunnable.endFling();
                    if(mPositionScroller != null)
                        mPositionScroller.stop();
                    if(flag1 && dispatchNestedPreFling(0.0F, -l1) ^ true)
                        dispatchNestedFling(0.0F, -l1, false);
                }
            }
        } else
        {
            mTouchMode = -1;
            reportScrollStateChange(0);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(mFlingRunnable == null)
            mFlingRunnable = new FlingRunnable();
        motionevent = mVelocityTracker;
        motionevent.computeCurrentVelocity(1000, mMaximumVelocity);
        int k = (int)motionevent.getYVelocity(mActivePointerId);
        reportScrollStateChange(2);
        if(Math.abs(k) > mMinimumVelocity)
            mFlingRunnable.startOverfling(-k);
        else
            mFlingRunnable.startSpringback();
        if(true) goto _L1; else goto _L5
_L5:
    }

    private boolean performStylusButtonPressAction(MotionEvent motionevent)
    {
        if(mChoiceMode == 3 && mChoiceActionMode == null)
        {
            motionevent = getChildAt(mMotionPosition - mFirstPosition);
            if(motionevent != null && performLongPress(motionevent, mMotionPosition, mAdapter.getItemId(mMotionPosition)))
            {
                mTouchMode = -1;
                setPressed(false);
                motionevent.setPressed(false);
                return true;
            }
        }
        return false;
    }

    private void positionPopup()
    {
        int i = getResources().getDisplayMetrics().heightPixels;
        int ai[] = new int[2];
        getLocationOnScreen(ai);
        i = (i - ai[1] - getHeight()) + (int)(mDensityScale * 20F);
        if(!mPopup.isShowing())
            mPopup.showAtLocation(this, 81, ai[0], i);
        else
            mPopup.update(ai[0], i, -1, -1);
    }

    private void positionSelector(int i, View view, boolean flag, float f, float f1)
    {
        boolean flag1;
        Rect rect;
        boolean flag2;
        if(i != mSelectorPosition)
            flag1 = true;
        else
            flag1 = false;
        if(i != -1)
            mSelectorPosition = i;
        rect = mSelectorRect;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        if(view instanceof SelectionBoundsAdjuster)
            ((SelectionBoundsAdjuster)view).adjustListItemSelectionBounds(rect);
        rect.left = rect.left - mSelectionLeftPadding;
        rect.top = rect.top - mSelectionTopPadding;
        rect.right = rect.right + mSelectionRightPadding;
        rect.bottom = rect.bottom + mSelectionBottomPadding;
        flag2 = view.isEnabled();
        if(mIsChildViewEnabled != flag2)
            mIsChildViewEnabled = flag2;
        view = mSelector;
        if(view != null)
        {
            if(flag1)
            {
                view.setVisible(false, false);
                view.setState(StateSet.NOTHING);
            }
            view.setBounds(rect);
            if(flag1)
            {
                if(getVisibility() == 0)
                    view.setVisible(true, false);
                updateSelectorState();
            }
            if(flag)
                view.setHotspot(f, f1);
        }
    }

    private void recycleVelocityTracker()
    {
        if(mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    private void scrollIfNeeded(int i, int j, MotionEvent motionevent)
    {
        int k;
        int l;
        int i1;
        int k1;
        int l1;
        boolean flag;
        k = j - mMotionY;
        l = 0;
        i1 = 0;
        k1 = k;
        if(mLastY == 0x80000000)
            k1 = k - mMotionCorrection;
        boolean flag2;
        Object obj;
        boolean flag3;
        if(mLastY != 0x80000000)
            l1 = mLastY - j;
        else
            l1 = -k1;
        k = k1;
        if(dispatchNestedPreScroll(0, l1, mScrollConsumed, mScrollOffset))
        {
            k1 += mScrollConsumed[1];
            l1 = -mScrollOffset[1];
            int j2 = mScrollConsumed[1];
            k = k1;
            i1 = j2;
            l = l1;
            if(motionevent != null)
            {
                motionevent.offsetLocation(0.0F, mScrollOffset[1]);
                mNestedYOffset = mNestedYOffset + mScrollOffset[1];
                l = l1;
                i1 = j2;
                k = k1;
            }
        }
        if(mLastY != 0x80000000)
            k1 = (j - mLastY) + i1;
        else
            k1 = k;
        flag2 = false;
        flag = false;
        if(mTouchMode != 3) goto _L2; else goto _L1
_L1:
        if(mScrollStrictSpan == null)
            mScrollStrictSpan = StrictMode.enterCriticalSpan("AbsListView-scroll");
        if(j == mLastY) goto _L4; else goto _L3
_L3:
        if((mGroupFlags & 0x80000) == 0 && Math.abs(k) > mTouchSlop)
        {
            obj = getParent();
            if(obj != null)
                ((ViewParent) (obj)).requestDisallowInterceptTouchEvent(true);
        }
        if(mMotionPosition >= 0)
            l1 = mMotionPosition - mFirstPosition;
        else
            l1 = getChildCount() / 2;
        i1 = 0;
        obj = getChildAt(l1);
        if(obj != null)
            i1 = ((View) (obj)).getTop();
        flag3 = false;
        if(k1 != 0)
            flag3 = trackMotionScroll(k, k1);
        obj = getChildAt(l1);
        k = ((flag2) ? 1 : 0);
        if(obj == null) goto _L6; else goto _L5
_L5:
        l1 = ((View) (obj)).getTop();
        k = ((flag) ? 1 : 0);
        if(!flag3) goto _L8; else goto _L7
_L7:
        i1 = -k1 - (l1 - i1);
        if(!dispatchNestedScroll(0, i1 - k1, 0, i1, mScrollOffset)) goto _L10; else goto _L9
_L9:
        i = 0 - mScrollOffset[1];
        k = i;
        if(motionevent != null)
        {
            motionevent.offsetLocation(0.0F, mScrollOffset[1]);
            mNestedYOffset = mNestedYOffset + mScrollOffset[1];
            k = i;
        }
_L8:
        mMotionY = j + k + l;
_L6:
        mLastY = j + k + l;
_L4:
        return;
_L10:
        boolean flag4;
        flag4 = overScrollBy(0, i1, 0, mScrollY, 0, 0, 0, mOverscrollDistance, true);
        if(flag4 && mVelocityTracker != null)
            mVelocityTracker.clear();
        l1 = getOverScrollMode();
        if(l1 == 0) goto _L12; else goto _L11
_L11:
        k = ((flag) ? 1 : 0);
        if(l1 != 1) goto _L8; else goto _L13
_L13:
        k = ((flag) ? 1 : 0);
        if(!(contentFits() ^ true)) goto _L8; else goto _L12
_L12:
        if(!flag4)
        {
            mDirection = 0;
            mTouchMode = 5;
        }
        if(k1 > 0)
        {
            mEdgeGlowTop.onPull((float)(-i1) / (float)getHeight(), (float)i / (float)getWidth());
            if(!mEdgeGlowBottom.isFinished())
                mEdgeGlowBottom.onRelease();
            invalidateTopGlow();
            k = ((flag) ? 1 : 0);
        } else
        {
            k = ((flag) ? 1 : 0);
            if(k1 < 0)
            {
                mEdgeGlowBottom.onPull((float)i1 / (float)getHeight(), 1.0F - (float)i / (float)getWidth());
                if(!mEdgeGlowTop.isFinished())
                    mEdgeGlowTop.onRelease();
                invalidateBottomGlow();
                k = ((flag) ? 1 : 0);
            }
        }
          goto _L8
_L2:
        if(mTouchMode != 5 || j == mLastY) goto _L4; else goto _L14
        if(k1 != 0)
        {
            overScrollBy(0, k1, 0, mScrollY, 0, 0, 0, mOverscrollDistance, true);
            k2 = getOverScrollMode();
            boolean flag1;
            if(k2 == 0 || k2 == 1 && contentFits() ^ true)
                if(k > 0)
                {
                    mEdgeGlowTop.onPull((float)k1 / (float)getHeight(), (float)i / (float)getWidth());
                    if(!mEdgeGlowBottom.isFinished())
                        mEdgeGlowBottom.onRelease();
                    invalidateTopGlow();
                } else
                if(k < 0)
                {
                    mEdgeGlowBottom.onPull((float)k1 / (float)getHeight(), 1.0F - (float)i / (float)getWidth());
                    if(!mEdgeGlowTop.isFinished())
                        mEdgeGlowTop.onRelease();
                    invalidateBottomGlow();
                }
        }
        if(i2 != 0)
        {
            if(mScrollY != 0)
            {
                mScrollY = 0;
                invalidateParentIfNeeded();
            }
            trackMotionScroll(i2, i2);
            mTouchMode = 3;
            k = findClosestMotionRow(j);
            mMotionCorrection = 0;
            motionevent = getChildAt(k - mFirstPosition);
            if(motionevent != null)
                i = motionevent.getTop();
            else
                i = 0;
            mMotionViewOriginalTop = i;
            mMotionY = j + l;
            mMotionPosition = k;
        }
        mLastY = j + 0 + l;
        mDirection = j1;
          goto _L4
_L14:
        int k2 = mScrollY;
        int l2 = k2 - k1;
        int j1;
        int i2;
        if(j > mLastY)
            j1 = 1;
        else
            j1 = -1;
        if(mDirection == 0)
            mDirection = j1;
        i2 = -k1;
        if(l2 < 0 && k2 >= 0 || l2 > 0 && k2 <= 0)
        {
            k2 = -k2;
            i2 = k1 + k2;
            k1 = k2;
        } else
        {
            flag1 = false;
            k1 = i2;
            i2 = ((flag1) ? 1 : 0);
        }
        break MISSING_BLOCK_LABEL_775;
    }

    private void setFastScrollerAlwaysVisibleUiThread(boolean flag)
    {
        if(mFastScroll != null)
            mFastScroll.setAlwaysShow(flag);
    }

    private void setFastScrollerEnabledUiThread(boolean flag)
    {
        if(mFastScroll == null) goto _L2; else goto _L1
_L1:
        mFastScroll.setEnabled(flag);
_L4:
        resolvePadding();
        if(mFastScroll != null)
            mFastScroll.updateLayout();
        return;
_L2:
        if(flag)
        {
            mFastScroll = new FastScroller(this, mFastScrollStyle);
            mFastScroll.setEnabled(true);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void setItemViewLayoutParams(View view, int i)
    {
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        LayoutParams layoutparams1;
        if(layoutparams == null)
            layoutparams1 = (LayoutParams)generateDefaultLayoutParams();
        else
        if(!checkLayoutParams(layoutparams))
            layoutparams1 = (LayoutParams)generateLayoutParams(layoutparams);
        else
            layoutparams1 = (LayoutParams)layoutparams;
        if(mAdapterHasStableIds)
            layoutparams1.itemId = mAdapter.getItemId(i);
        layoutparams1.viewType = mAdapter.getItemViewType(i);
        layoutparams1.isEnabled = mAdapter.isEnabled(i);
        if(layoutparams1 != layoutparams)
            view.setLayoutParams(layoutparams1);
    }

    private boolean showContextMenuForChildInternal(View view, float f, float f1, boolean flag)
    {
        int i = getPositionForView(view);
        if(i < 0)
            return false;
        long l = mAdapter.getItemId(i);
        boolean flag1 = false;
        if(mOnItemLongClickListener != null)
            flag1 = mOnItemLongClickListener.onItemLongClick(this, view, i, l);
        boolean flag2 = flag1;
        if(!flag1)
        {
            mContextMenuInfo = createContextMenuInfo(getChildAt(i - mFirstPosition), i, l);
            if(flag)
                flag2 = super.showContextMenuForChild(view, f, f1);
            else
                flag2 = super.showContextMenuForChild(view);
        }
        return flag2;
    }

    private boolean showContextMenuInternal(float f, float f1, boolean flag)
    {
        int i = pointToPosition((int)f, (int)f1);
        if(i != -1)
        {
            long l = mAdapter.getItemId(i);
            View view = getChildAt(i - mFirstPosition);
            if(view != null)
            {
                mContextMenuInfo = createContextMenuInfo(view, i, l);
                if(flag)
                    return super.showContextMenuForChild(this, f, f1);
                else
                    return super.showContextMenuForChild(this);
            }
        }
        if(flag)
            return super.showContextMenu(f, f1);
        else
            return super.showContextMenu();
    }

    private void showPopup()
    {
        if(getWindowVisibility() == 0)
        {
            createTextFilter(true);
            positionPopup();
            checkFocus();
        }
    }

    private boolean startScrollIfNeeded(int i, int j, MotionEvent motionevent)
    {
        int k = j - mMotionY;
        int l = Math.abs(k);
        boolean flag;
        if(mScrollY != 0)
            flag = true;
        else
            flag = false;
        if(OPTS_INPUT)
        {
            if(mIsFirstTouchMoveEvent)
            {
                if(l > mMoveAcceleration)
                    l = 1;
                else
                    l = 0;
            } else
            if(l > mTouchSlop)
                l = 1;
            else
                l = 0;
        } else
        if(l > mTouchSlop)
            l = 1;
        else
            l = 0;
        if((flag || l != 0) && (getNestedScrollAxes() & 2) == 0)
        {
            createScrollingCache();
            Object obj;
            if(flag)
            {
                mTouchMode = 5;
                mMotionCorrection = 0;
            } else
            {
                mTouchMode = 3;
                if(mIsFirstTouchMoveEvent)
                {
                    int i1;
                    if(k > 0)
                        i1 = mMoveAcceleration;
                    else
                        i1 = -mMoveAcceleration;
                    mMotionCorrection = i1;
                } else
                {
                    int j1;
                    if(k > 0)
                        j1 = mTouchSlop;
                    else
                        j1 = -mTouchSlop;
                    mMotionCorrection = j1;
                }
            }
            removeCallbacks(mPendingCheckForLongPress);
            setPressed(false);
            obj = getChildAt(mMotionPosition - mFirstPosition);
            if(obj != null)
                ((View) (obj)).setPressed(false);
            reportScrollStateChange(1);
            obj = getParent();
            if(obj != null)
                ((ViewParent) (obj)).requestDisallowInterceptTouchEvent(true);
            scrollIfNeeded(i, j, motionevent);
            return true;
        } else
        {
            return false;
        }
    }

    private void updateOnScreenCheckedViews()
    {
        int i = mFirstPosition;
        int j = getChildCount();
        boolean flag;
        int k;
        if(getContext().getApplicationInfo().targetSdkVersion >= 11)
            flag = true;
        else
            flag = false;
        k = 0;
        while(k < j) 
        {
            View view = getChildAt(k);
            int l = i + k;
            if(view instanceof Checkable)
                ((Checkable)view).setChecked(mCheckStates.get(l));
            else
            if(flag)
                view.setActivated(mCheckStates.get(l));
            k++;
        }
    }

    private void useDefaultSelector()
    {
        setSelector(getContext().getDrawable(0x1080062));
    }

    public void addTouchables(ArrayList arraylist)
    {
        int i = getChildCount();
        int j = mFirstPosition;
        ListAdapter listadapter = mAdapter;
        if(listadapter == null)
            return;
        for(int k = 0; k < i; k++)
        {
            View view = getChildAt(k);
            if(listadapter.isEnabled(j + k))
                arraylist.add(view);
            view.addTouchables(arraylist);
        }

    }

    public void afterTextChanged(Editable editable)
    {
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public boolean canScrollList(int i)
    {
        boolean flag = true;
        boolean flag1 = true;
        int j = getChildCount();
        if(j == 0)
            return false;
        int k = mFirstPosition;
        Rect rect = mListPadding;
        if(i > 0)
        {
            i = getChildAt(j - 1).getBottom();
            boolean flag2 = flag1;
            if(k + j >= mItemCount)
                if(i > getHeight() - rect.bottom)
                    flag2 = flag1;
                else
                    flag2 = false;
            return flag2;
        }
        i = getChildAt(0).getTop();
        boolean flag3 = flag;
        if(k <= 0)
            if(i < rect.top)
                flag3 = flag;
            else
                flag3 = false;
        return flag3;
    }

    public boolean checkInputConnectionProxy(View view)
    {
        boolean flag;
        if(view == mTextFilter)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    public void clearChoices()
    {
        if(mCheckStates != null)
            mCheckStates.clear();
        if(mCheckedIdStates != null)
            mCheckedIdStates.clear();
        mCheckedItemCount = 0;
    }

    public void clearTextFilter()
    {
        if(mFiltered)
        {
            getTextFilterInput().setText("");
            mFiltered = false;
            if(mPopup != null && mPopup.isShowing())
                dismissPopup();
        }
    }

    protected int computeVerticalScrollExtent()
    {
        int i = getChildCount();
        if(i > 0)
        {
            if(mSmoothScrollbarEnabled)
            {
                int j = i * 100;
                View view = getChildAt(0);
                int k = view.getTop();
                int l = view.getHeight();
                int i1 = j;
                if(l > 0)
                    i1 = j + (k * 100) / l;
                view = getChildAt(i - 1);
                k = view.getBottom();
                i = view.getHeight();
                j = i1;
                if(i > 0)
                    j = i1 - ((k - getHeight()) * 100) / i;
                return j;
            } else
            {
                return 1;
            }
        } else
        {
            return 0;
        }
    }

    protected int computeVerticalScrollOffset()
    {
        int i = mFirstPosition;
        int j = getChildCount();
        if(i >= 0 && j > 0)
            if(mSmoothScrollbarEnabled)
            {
                View view = getChildAt(0);
                int k = view.getTop();
                int i1 = view.getHeight();
                if(i1 > 0)
                    return Math.max((i * 100 - (k * 100) / i1) + (int)(((float)mScrollY / (float)getHeight()) * (float)mItemCount * 100F), 0);
            } else
            {
                int j1 = mItemCount;
                int l;
                if(i == 0)
                    l = 0;
                else
                if(i + j == j1)
                    l = j1;
                else
                    l = i + j / 2;
                return (int)((float)i + (float)j * ((float)l / (float)j1));
            }
        return 0;
    }

    protected int computeVerticalScrollRange()
    {
        int j;
        if(mSmoothScrollbarEnabled)
        {
            int i = Math.max(mItemCount * 100, 0);
            j = i;
            if(mScrollY != 0)
                j = i + Math.abs((int)(((float)mScrollY / (float)getHeight()) * (float)mItemCount * 100F));
        } else
        {
            j = mItemCount;
        }
        return j;
    }

    void confirmCheckedPositionsById()
    {
        int i;
        int j;
        mCheckStates.clear();
        i = 0;
        j = 0;
_L8:
        long l;
        int k;
        int i1;
        int j1;
        boolean flag;
        if(j >= mCheckedIdStates.size())
            break MISSING_BLOCK_LABEL_254;
        l = mCheckedIdStates.keyAt(j);
        k = ((Integer)mCheckedIdStates.valueAt(j)).intValue();
        if(l == mAdapter.getItemId(k))
            break MISSING_BLOCK_LABEL_238;
        i1 = Math.max(0, k - 20);
        j1 = Math.min(k + 20, mItemCount);
        flag = false;
_L5:
        boolean flag1 = flag;
        if(i1 >= j1) goto _L2; else goto _L1
_L1:
        if(l != mAdapter.getItemId(i1)) goto _L4; else goto _L3
_L3:
        flag1 = true;
        mCheckStates.put(i1, true);
        mCheckedIdStates.setValueAt(j, Integer.valueOf(i1));
_L2:
        i1 = j;
        if(!flag1)
        {
            mCheckedIdStates.delete(l);
            int k1 = j - 1;
            mCheckedItemCount = mCheckedItemCount - 1;
            j = 1;
            i = j;
            i1 = k1;
            if(mChoiceActionMode != null)
            {
                i = j;
                i1 = k1;
                if(mMultiChoiceModeCallback != null)
                {
                    mMultiChoiceModeCallback.onItemCheckedStateChanged(mChoiceActionMode, k, l, false);
                    i1 = k1;
                    i = j;
                }
            }
        }
_L6:
        j = i1 + 1;
        continue; /* Loop/switch isn't completed */
_L4:
        i1++;
          goto _L5
        mCheckStates.put(k, true);
        i1 = j;
          goto _L6
        if(i != 0 && mChoiceActionMode != null)
            mChoiceActionMode.invalidate();
        return;
        if(true) goto _L8; else goto _L7
_L7:
    }

    android.view.ContextMenu.ContextMenuInfo createContextMenuInfo(View view, int i, long l)
    {
        return new AdapterView.AdapterContextMenuInfo(view, i, l);
    }

    AbsPositionScroller createPositionScroller()
    {
        return new PositionScroller();
    }

    public void deferNotifyDataSetChanged()
    {
        mDeferNotifyDataSetChanged = true;
    }

    protected void dispatchDraw(Canvas canvas)
    {
        int i = 0;
        boolean flag = false;
        if(canvas != null)
        {
            i = canvas.save();
            flag = true;
            AbsListViewInjector.onRenderTick(this, canvas);
        }
        int j = 0;
        boolean flag1;
        boolean flag2;
        if((mGroupFlags & 0x22) == 34)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
        {
            j = canvas.save();
            int k = mScrollX;
            int l = mScrollY;
            canvas.clipRect(mPaddingLeft + k, mPaddingTop + l, (mRight + k) - mLeft - mPaddingRight, (mBottom + l) - mTop - mPaddingBottom);
            mGroupFlags = mGroupFlags & 0xffffffdd;
        }
        flag2 = mDrawSelectorOnTop;
        if(!flag2)
            drawSelector(canvas);
        super.dispatchDraw(canvas);
        if(flag2)
            drawSelector(canvas);
        if(flag1)
        {
            canvas.restoreToCount(j);
            mGroupFlags = mGroupFlags | 0x22;
        }
        if(canvas != null && flag)
            canvas.restoreToCount(i);
    }

    public void dispatchDrawableHotspotChanged(float f, float f1)
    {
    }

    protected void dispatchSetPressed(boolean flag)
    {
    }

    public void draw(Canvas canvas)
    {
        boolean flag = false;
        super.draw(canvas);
        if(mEdgeGlowTop != null)
        {
            int i = mScrollY;
            boolean flag1 = getClipToPadding();
            int j;
            int k;
            int l;
            int i1;
            if(flag1)
            {
                j = getWidth() - mPaddingLeft - mPaddingRight;
                k = getHeight() - mPaddingTop - mPaddingBottom;
                l = mPaddingLeft;
                i1 = mPaddingTop;
            } else
            {
                j = getWidth();
                k = getHeight();
                l = 0;
                i1 = 0;
            }
            if(!mEdgeGlowTop.isFinished())
            {
                int j1 = canvas.save();
                canvas.clipRect(l, i1, l + j, mEdgeGlowTop.getMaxHeight() + i1);
                int l1 = Math.min(0, mFirstPositionDistanceGuess + i);
                canvas.translate(l, l1 + i1);
                mEdgeGlowTop.setSize(j, k);
                if(mEdgeGlowTop.draw(canvas))
                    invalidateTopGlow();
                canvas.restoreToCount(j1);
            }
            if(!mEdgeGlowBottom.isFinished())
            {
                int k1 = canvas.save();
                canvas.clipRect(l, (i1 + k) - mEdgeGlowBottom.getMaxHeight(), l + j, i1 + k);
                int i2 = -j;
                i = Math.max(getHeight(), mLastPositionDistanceGuess + i);
                i1 = ((flag) ? 1 : 0);
                if(flag1)
                    i1 = mPaddingBottom;
                canvas.translate(i2 + l, i - i1);
                canvas.rotate(180F, j, 0.0F);
                mEdgeGlowBottom.setSize(j, k);
                if(mEdgeGlowBottom.draw(canvas))
                    invalidateBottomGlow();
                canvas.restoreToCount(k1);
            }
        }
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        updateSelectorState();
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        super.encodeProperties(viewhierarchyencoder);
        viewhierarchyencoder.addProperty("drawing:cacheColorHint", getCacheColorHint());
        viewhierarchyencoder.addProperty("list:fastScrollEnabled", isFastScrollEnabled());
        viewhierarchyencoder.addProperty("list:scrollingCacheEnabled", isScrollingCacheEnabled());
        viewhierarchyencoder.addProperty("list:smoothScrollbarEnabled", isSmoothScrollbarEnabled());
        viewhierarchyencoder.addProperty("list:stackFromBottom", isStackFromBottom());
        viewhierarchyencoder.addProperty("list:textFilterEnabled", isTextFilterEnabled());
        View view = getSelectedView();
        if(view != null)
        {
            viewhierarchyencoder.addPropertyKey("selectedView");
            view.encode(viewhierarchyencoder);
        }
    }

    abstract void fillGap(boolean flag);

    int findClosestMotionRow(int i)
    {
        int j = getChildCount();
        if(j == 0)
            return -1;
        i = findMotionRow(i);
        if(i == -1)
            i = (mFirstPosition + j) - 1;
        return i;
    }

    abstract int findMotionRow(int i);

    public View findViewByAccessibilityIdTraversal(int i)
    {
        if(i == getAccessibilityViewId())
            return this;
        else
            return super.findViewByAccessibilityIdTraversal(i);
    }

    public void fling(int i)
    {
        if(mFlingRunnable == null)
            mFlingRunnable = new FlingRunnable();
        reportScrollStateChange(2);
        mFlingRunnable.start(i);
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-1, -2, 0);
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return new LayoutParams(layoutparams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/AbsListView.getName();
    }

    View getAccessibilityFocusedChild(View view)
    {
        ViewParent viewparent = view.getParent();
        View view1 = view;
        for(view = viewparent; (view instanceof View) && view != this; view = view.getParent())
            view1 = (View)view;

        if(!(view instanceof View))
            return null;
        else
            return view1;
    }

    protected float getBottomFadingEdgeStrength()
    {
        int i = getChildCount();
        float f = super.getBottomFadingEdgeStrength();
        if(i == 0)
            return f;
        if((mFirstPosition + i) - 1 < mItemCount - 1)
            return 1.0F;
        int j = getChildAt(i - 1).getBottom();
        i = getHeight();
        float f1 = getVerticalFadingEdgeLength();
        if(j > i - mPaddingBottom)
            f = (float)((j - i) + mPaddingBottom) / f1;
        return f;
    }

    protected int getBottomPaddingOffset()
    {
        int i;
        if((mGroupFlags & 0x22) == 34)
            i = 0;
        else
            i = mPaddingBottom;
        return i;
    }

    public int getCacheColorHint()
    {
        return mCacheColorHint;
    }

    public int getCheckedItemCount()
    {
        return mCheckedItemCount;
    }

    public long[] getCheckedItemIds()
    {
        while(mChoiceMode == 0 || mCheckedIdStates == null || mAdapter == null) 
            return new long[0];
        LongSparseArray longsparsearray = mCheckedIdStates;
        int i = longsparsearray.size();
        long al[] = new long[i];
        for(int j = 0; j < i; j++)
            al[j] = longsparsearray.keyAt(j);

        return al;
    }

    public int getCheckedItemPosition()
    {
        if(mChoiceMode == 1 && mCheckStates != null && mCheckStates.size() == 1)
            return mCheckStates.keyAt(0);
        else
            return -1;
    }

    public SparseBooleanArray getCheckedItemPositions()
    {
        if(mChoiceMode != 0)
            return mCheckStates;
        else
            return null;
    }

    public int getChoiceMode()
    {
        return mChoiceMode;
    }

    protected android.view.ContextMenu.ContextMenuInfo getContextMenuInfo()
    {
        return mContextMenuInfo;
    }

    public void getFocusedRect(Rect rect)
    {
        View view = getSelectedView();
        if(view != null && view.getParent() == this)
        {
            view.getFocusedRect(rect);
            offsetDescendantRectToMyCoords(view, rect);
        } else
        {
            super.getFocusedRect(rect);
        }
    }

    int getFooterViewsCount()
    {
        return 0;
    }

    int getHeaderViewsCount()
    {
        return 0;
    }

    int getHeightForPosition(int i)
    {
        int j = getFirstVisiblePosition();
        int k = getChildCount();
        j = i - j;
        if(j >= 0 && j < k)
        {
            return getChildAt(j).getHeight();
        } else
        {
            View view = obtainView(i, mIsScrap);
            view.measure(mWidthMeasureSpec, 0);
            int l = view.getMeasuredHeight();
            mRecycler.addScrapView(view, i);
            return l;
        }
    }

    protected int getLeftPaddingOffset()
    {
        int i;
        if((mGroupFlags & 0x22) == 34)
            i = 0;
        else
            i = -mPaddingLeft;
        return i;
    }

    public int getListPaddingBottom()
    {
        return mListPadding.bottom;
    }

    public int getListPaddingLeft()
    {
        return mListPadding.left;
    }

    public int getListPaddingRight()
    {
        return mListPadding.right;
    }

    public int getListPaddingTop()
    {
        return mListPadding.top;
    }

    protected int getRightPaddingOffset()
    {
        int i;
        if((mGroupFlags & 0x22) == 34)
            i = 0;
        else
            i = mPaddingRight;
        return i;
    }

    public View getSelectedView()
    {
        if(mItemCount > 0 && mSelectedPosition >= 0)
            return getChildAt(mSelectedPosition - mFirstPosition);
        else
            return null;
    }

    int getSelectionModeForAccessibility()
    {
        switch(getChoiceMode())
        {
        default:
            return 0;

        case 0: // '\0'
            return 0;

        case 1: // '\001'
            return 1;

        case 2: // '\002'
        case 3: // '\003'
            return 2;
        }
    }

    public Drawable getSelector()
    {
        return mSelector;
    }

    public int getSolidColor()
    {
        return mCacheColorHint;
    }

    public CharSequence getTextFilter()
    {
        if(mTextFilterEnabled && mTextFilter != null)
            return mTextFilter.getText();
        else
            return null;
    }

    protected float getTopFadingEdgeStrength()
    {
        int i = getChildCount();
        float f = super.getTopFadingEdgeStrength();
        if(i == 0)
            return f;
        if(mFirstPosition > 0)
            return 1.0F;
        i = getChildAt(0).getTop();
        float f1 = getVerticalFadingEdgeLength();
        if(i < mPaddingTop)
            f = (float)(-(i - mPaddingTop)) / f1;
        return f;
    }

    protected int getTopPaddingOffset()
    {
        int i;
        if((mGroupFlags & 0x22) == 34)
            i = 0;
        else
            i = -mPaddingTop;
        return i;
    }

    public int getTranscriptMode()
    {
        return mTranscriptMode;
    }

    public int getVerticalScrollbarWidth()
    {
        if(mFastScroll != null && mFastScroll.isEnabled())
            return Math.max(super.getVerticalScrollbarWidth(), mFastScroll.getWidth());
        else
            return super.getVerticalScrollbarWidth();
    }

    int getVerticalSpacing()
    {
        return 0;
    }

    void handleBoundsChange()
    {
        int i;
        if(mInLayout)
            return;
        i = getChildCount();
        if(i <= 0) goto _L2; else goto _L1
_L1:
        int j;
        mDataChanged = true;
        rememberSyncState();
        j = 0;
_L4:
        View view;
        android.view.ViewGroup.LayoutParams layoutparams;
        if(j < i)
        {
            view = getChildAt(j);
            layoutparams = view.getLayoutParams();
            break MISSING_BLOCK_LABEL_45;
        }
          goto _L2
        if(layoutparams == null || layoutparams.width < 1 || layoutparams.height < 1)
            view.forceLayout();
        j++;
        continue; /* Loop/switch isn't completed */
_L2:
        return;
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void handleDataChanged()
    {
        byte byte0;
        int i;
        int j;
        byte0 = 3;
        i = mItemCount;
        j = mLastHandledItemCount;
        mLastHandledItemCount = mItemCount;
        if(mChoiceMode != 0 && mAdapter != null && mAdapter.hasStableIds())
            confirmCheckedPositionsById();
        mRecycler.clearTransientStateViews();
        if(i <= 0)
            break MISSING_BLOCK_LABEL_427;
        if(!mNeedSync) goto _L2; else goto _L1
_L1:
        mNeedSync = false;
        mPendingSync = null;
        if(mTranscriptMode == 2)
        {
            mLayoutMode = 3;
            return;
        }
        if(mTranscriptMode == 1)
        {
            if(mForceTranscriptScroll)
            {
                mForceTranscriptScroll = false;
                mLayoutMode = 3;
                return;
            }
            int k = getChildCount();
            int l = getHeight() - getPaddingBottom();
            View view = getChildAt(k - 1);
            int j1;
            if(view != null)
                j1 = view.getBottom();
            else
                j1 = l;
            if(mFirstPosition + k >= j && j1 <= l)
            {
                mLayoutMode = 3;
                return;
            }
            awakenScrollBars();
        }
        mSyncMode;
        JVM INSTR tableswitch 0 1: default 220
    //                   0 281
    //                   1 374;
           goto _L2 _L3 _L4
_L2:
        int i1;
        if(isInTouchMode())
            break MISSING_BLOCK_LABEL_419;
        i1 = getSelectedItemPosition();
        int k1 = i1;
        if(i1 >= i)
            k1 = i - 1;
        i1 = k1;
        if(k1 < 0)
            i1 = 0;
        k1 = lookForSelectablePosition(i1, true);
        if(k1 >= 0)
        {
            setNextSelectedPositionInt(k1);
            return;
        }
        break; /* Loop/switch isn't completed */
_L3:
        if(isInTouchMode())
        {
            mLayoutMode = 5;
            mSyncPosition = Math.min(Math.max(0, mSyncPosition), i - 1);
            return;
        }
        int l1 = findSyncPosition();
        if(l1 >= 0 && lookForSelectablePosition(l1, true) == l1)
        {
            mSyncPosition = l1;
            if(mSyncHeight == (long)getHeight())
                mLayoutMode = 5;
            else
                mLayoutMode = 2;
            setNextSelectedPositionInt(l1);
            return;
        }
        if(true) goto _L2; else goto _L5
_L4:
        mLayoutMode = 5;
        mSyncPosition = Math.min(Math.max(0, mSyncPosition), i - 1);
        return;
_L5:
        int i2 = lookForSelectablePosition(i1, false);
        if(i2 >= 0)
        {
            setNextSelectedPositionInt(i2);
            return;
        }
        break MISSING_BLOCK_LABEL_427;
        if(mResurrectToPosition >= 0)
            return;
        int j2;
        if(mStackFromBottom)
            j2 = byte0;
        else
            j2 = 1;
        mLayoutMode = j2;
        mSelectedPosition = -1;
        mSelectedRowId = 0x8000000000000000L;
        mNextSelectedPosition = -1;
        mNextSelectedRowId = 0x8000000000000000L;
        mNeedSync = false;
        mPendingSync = null;
        mSelectorPosition = -1;
        checkSelectionChanged();
        return;
    }

    protected boolean handleScrollBarDragging(MotionEvent motionevent)
    {
        return false;
    }

    public boolean hasTextFilter()
    {
        return mFiltered;
    }

    void hideSelector()
    {
        if(mSelectedPosition != -1)
        {
            if(mLayoutMode != 4)
                mResurrectToPosition = mSelectedPosition;
            if(mNextSelectedPosition >= 0 && mNextSelectedPosition != mSelectedPosition)
                mResurrectToPosition = mNextSelectedPosition;
            setSelectedPositionInt(-1);
            setNextSelectedPositionInt(-1);
            mSelectedTop = 0;
        }
    }

    protected void internalSetPadding(int i, int j, int k, int l)
    {
        super.internalSetPadding(i, j, k, l);
        if(isLayoutRequested())
            handleBoundsChange();
    }

    public void invalidateViews()
    {
        mDataChanged = true;
        rememberSyncState();
        requestLayout();
        invalidate();
    }

    void invokeOnItemScrollListener()
    {
        if(mFastScroll != null)
            mFastScroll.onScroll(mFirstPosition, getChildCount(), mItemCount);
        if(mOnScrollListener != null)
            mOnScrollListener.onScroll(this, mFirstPosition, getChildCount(), mItemCount);
        onScrollChanged(0, 0, 0, 0);
    }

    public boolean isFastScrollAlwaysVisible()
    {
        boolean flag = false;
        boolean flag1 = false;
        if(mFastScroll == null)
        {
            if(mFastScrollEnabled)
                flag1 = mFastScrollAlwaysVisible;
            return flag1;
        }
        flag1 = flag;
        if(mFastScroll.isEnabled())
            flag1 = mFastScroll.isAlwaysShowEnabled();
        return flag1;
    }

    public boolean isFastScrollEnabled()
    {
        if(mFastScroll == null)
            return mFastScrollEnabled;
        else
            return mFastScroll.isEnabled();
    }

    protected boolean isInFilterMode()
    {
        return mFiltered;
    }

    public boolean isItemChecked(int i)
    {
        if(mChoiceMode != 0 && mCheckStates != null)
            return mCheckStates.get(i);
        else
            return false;
    }

    protected boolean isPaddingOffsetRequired()
    {
        boolean flag;
        if((mGroupFlags & 0x22) != 34)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isScrollingCacheEnabled()
    {
        return mScrollingCacheEnabled;
    }

    public boolean isSmoothScrollbarEnabled()
    {
        return mSmoothScrollbarEnabled;
    }

    public boolean isStackFromBottom()
    {
        return mStackFromBottom;
    }

    public boolean isTextFilterEnabled()
    {
        return mTextFilterEnabled;
    }

    protected boolean isVerticalScrollBarHidden()
    {
        return isFastScrollEnabled();
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if(mSelector != null)
            mSelector.jumpToCurrentState();
    }

    void keyPressed()
    {
        if(!isEnabled() || isClickable() ^ true)
            return;
        Drawable drawable = mSelector;
        Rect rect = mSelectorRect;
        if(drawable != null && (isFocused() || touchModeDrawsInPressedState()) && rect.isEmpty() ^ true)
        {
            View view = getChildAt(mSelectedPosition - mFirstPosition);
            if(view != null)
            {
                if(view.hasExplicitFocusable())
                    return;
                view.setPressed(true);
            }
            setPressed(true);
            boolean flag = isLongClickable();
            drawable = drawable.getCurrent();
            if(drawable != null && (drawable instanceof TransitionDrawable))
                if(flag)
                    ((TransitionDrawable)drawable).startTransition(ViewConfiguration.getLongPressTimeout());
                else
                    ((TransitionDrawable)drawable).resetTransition();
            if(flag && mDataChanged ^ true)
            {
                if(mPendingCheckForKeyLongPress == null)
                    mPendingCheckForKeyLongPress = new CheckForKeyLongPress(null);
                mPendingCheckForKeyLongPress.rememberWindowAttachCount();
                postDelayed(mPendingCheckForKeyLongPress, ViewConfiguration.getLongPressTimeout());
            }
        }
    }

    protected void layoutChildren()
    {
    }

    View obtainView(int i, boolean aflag[])
    {
        View view;
        View view2;
        Trace.traceBegin(8L, "obtainView");
        aflag[0] = false;
        view = mRecycler.getTransientStateView(i);
        if(view != null)
        {
            if(((LayoutParams)view.getLayoutParams()).viewType == mAdapter.getItemViewType(i))
            {
                View view1 = mAdapter.getView(i, view, this);
                if(view1 != view)
                {
                    setItemViewLayoutParams(view1, i);
                    mRecycler.addScrapView(view1, i);
                }
            }
            aflag[0] = true;
            view.dispatchFinishTemporaryDetach();
            return view;
        }
        view2 = mRecycler.getScrapView(i);
        view = mAdapter.getView(i, view2, this);
        if(view2 == null) goto _L2; else goto _L1
_L1:
        if(view == view2) goto _L4; else goto _L3
_L3:
        mRecycler.addScrapView(view2, i);
_L2:
        if(mCacheColorHint != 0)
            view.setDrawingCacheBackgroundColor(mCacheColorHint);
        if(view.getImportantForAccessibility() == 0)
            view.setImportantForAccessibility(1);
        setItemViewLayoutParams(view, i);
        if(AccessibilityManager.getInstance(mContext).isEnabled())
        {
            if(mAccessibilityDelegate == null)
                mAccessibilityDelegate = new ListItemAccessibilityDelegate();
            if(view.getAccessibilityDelegate() == null)
                view.setAccessibilityDelegate(mAccessibilityDelegate);
        }
        Trace.traceEnd(8L);
        return view;
_L4:
        if(view.isTemporarilyDetached())
        {
            aflag[0] = true;
            view.dispatchFinishTemporaryDetach();
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        ViewTreeObserver viewtreeobserver = getViewTreeObserver();
        viewtreeobserver.addOnTouchModeChangeListener(this);
        if(mTextFilterEnabled && mPopup != null && mGlobalLayoutListenerAddedFilter ^ true)
            viewtreeobserver.addOnGlobalLayoutListener(this);
        if(mAdapter != null && mDataSetObserver == null)
        {
            mDataSetObserver = new AdapterDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
            mDataChanged = true;
            mOldItemCount = mItemCount;
            mItemCount = mAdapter.getCount();
        }
    }

    public void onCancelPendingInputEvents()
    {
        super.onCancelPendingInputEvents();
        if(mPerformClick != null)
            removeCallbacks(mPerformClick);
        if(mPendingCheckForTap != null)
            removeCallbacks(mPendingCheckForTap);
        if(mPendingCheckForLongPress != null)
            removeCallbacks(mPendingCheckForLongPress);
        if(mPendingCheckForKeyLongPress != null)
            removeCallbacks(mPendingCheckForKeyLongPress);
    }

    public InputConnection onCreateInputConnection(EditorInfo editorinfo)
    {
        if(isTextFilterEnabled())
        {
            if(mPublicInputConnection == null)
            {
                mDefInputConnection = new BaseInputConnection(this, false);
                mPublicInputConnection = new InputConnectionWrapper(editorinfo);
            }
            editorinfo.inputType = 177;
            editorinfo.imeOptions = 6;
            return mPublicInputConnection;
        } else
        {
            return null;
        }
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        mIsDetaching = true;
        dismissPopup();
        mRecycler.clear();
        ViewTreeObserver viewtreeobserver = getViewTreeObserver();
        viewtreeobserver.removeOnTouchModeChangeListener(this);
        if(mTextFilterEnabled && mPopup != null)
        {
            viewtreeobserver.removeOnGlobalLayoutListener(this);
            mGlobalLayoutListenerAddedFilter = false;
        }
        if(mAdapter != null && mDataSetObserver != null)
        {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
            mDataSetObserver = null;
        }
        if(mScrollStrictSpan != null)
        {
            mScrollStrictSpan.finish();
            mScrollStrictSpan = null;
        }
        if(mFlingStrictSpan != null)
        {
            mFlingStrictSpan.finish();
            mFlingStrictSpan = null;
        }
        if(mFlingRunnable != null)
            removeCallbacks(mFlingRunnable);
        if(mPositionScroller != null)
            mPositionScroller.stop();
        if(mClearScrollingCache != null)
            removeCallbacks(mClearScrollingCache);
        if(mPerformClick != null)
            removeCallbacks(mPerformClick);
        if(mTouchModeReset != null)
        {
            removeCallbacks(mTouchModeReset);
            mTouchModeReset.run();
        }
        mIsDetaching = false;
    }

    protected void onDisplayHint(int i)
    {
        super.onDisplayHint(i);
        i;
        JVM INSTR lookupswitch 2: default 32
    //                   0: 69
    //                   4: 45;
           goto _L1 _L2 _L3
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break MISSING_BLOCK_LABEL_69;
_L4:
        boolean flag;
        if(i == 4)
            flag = true;
        else
            flag = false;
        mPopupHidden = flag;
        return;
_L3:
        if(mPopup != null && mPopup.isShowing())
            dismissPopup();
          goto _L4
        if(mFiltered && mPopup != null && mPopup.isShowing() ^ true)
            showPopup();
          goto _L4
    }

    public void onFilterComplete(int i)
    {
        if(mSelectedPosition < 0 && i > 0)
        {
            mResurrectToPosition = -1;
            resurrectSelection();
        }
    }

    protected void onFocusChanged(boolean flag, int i, Rect rect)
    {
        super.onFocusChanged(flag, i, rect);
        if(flag && mSelectedPosition < 0 && isInTouchMode() ^ true)
        {
            if(!isAttachedToWindow() && mAdapter != null)
            {
                mDataChanged = true;
                mOldItemCount = mItemCount;
                mItemCount = mAdapter.getCount();
            }
            resurrectSelection();
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        motionevent.getAction();
        JVM INSTR tableswitch 8 11: default 36
    //                   8 42
    //                   9 36
    //                   10 36
    //                   11 107;
           goto _L1 _L2 _L1 _L1 _L3
_L1:
        return super.onGenericMotionEvent(motionevent);
_L2:
        float f;
        int i;
        if(motionevent.isFromSource(2))
            f = motionevent.getAxisValue(9);
        else
        if(motionevent.isFromSource(0x400000))
            f = motionevent.getAxisValue(26);
        else
            f = 0.0F;
        i = Math.round(mVerticalScrollFactor * f);
        if(i != 0 && !trackMotionScroll(i, i))
            return true;
        continue; /* Loop/switch isn't completed */
_L3:
        if(motionevent.isFromSource(2))
        {
            int j = motionevent.getActionButton();
            if((j == 32 || j == 2) && (mTouchMode == 0 || mTouchMode == 1) && performStylusButtonPressAction(motionevent))
            {
                removeCallbacks(mPendingCheckForLongPress);
                removeCallbacks(mPendingCheckForTap);
            }
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void onGlobalLayout()
    {
        if(!isShown()) goto _L2; else goto _L1
_L1:
        if(mFiltered && mPopup != null && mPopup.isShowing() ^ true && mPopupHidden ^ true)
            showPopup();
_L4:
        return;
_L2:
        if(mPopup != null && mPopup.isShowing())
            dismissPopup();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onInitializeAccessibilityNodeInfoForItem(View view, int i, AccessibilityNodeInfo accessibilitynodeinfo)
    {
        if(i == -1)
            return;
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        boolean flag;
        if(layoutparams instanceof LayoutParams)
            flag = ((LayoutParams)layoutparams).isEnabled;
        else
            flag = false;
        if(!isEnabled() || flag ^ true)
        {
            accessibilitynodeinfo.setEnabled(false);
            return;
        }
        if(i == getSelectedItemPosition())
        {
            accessibilitynodeinfo.setSelected(true);
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_CLEAR_SELECTION);
        } else
        {
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SELECT);
        }
        if(isItemClickable(view))
        {
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
            accessibilitynodeinfo.setClickable(true);
        }
        if(isLongClickable())
        {
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_LONG_CLICK);
            accessibilitynodeinfo.setLongClickable(true);
        }
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        if(isEnabled())
        {
            if(canScrollUp())
            {
                accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
                accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP);
                accessibilitynodeinfo.setScrollable(true);
            }
            if(canScrollDown())
            {
                accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
                accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN);
                accessibilitynodeinfo.setScrollable(true);
            }
        }
        accessibilitynodeinfo.removeAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
        accessibilitynodeinfo.setClickable(false);
    }

    public boolean onInterceptHoverEvent(MotionEvent motionevent)
    {
        if(mFastScroll != null && mFastScroll.onInterceptHoverEvent(motionevent))
            return true;
        else
            return super.onInterceptHoverEvent(motionevent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i;
        i = motionevent.getActionMasked();
        if(mPositionScroller != null)
            mPositionScroller.stop();
        if(mIsDetaching || isAttachedToWindow() ^ true)
            return false;
        if(mFastScroll != null && mFastScroll.onInterceptTouchEvent(motionevent))
            return true;
        i;
        JVM INSTR tableswitch 0 6: default 100
    //                   0 102
    //                   1 382
    //                   2 257
    //                   3 382
    //                   4 100
    //                   5 100
    //                   6 419;
           goto _L1 _L2 _L3 _L4 _L3 _L1 _L1 _L5
_L1:
        return false;
_L2:
        if(OPTS_INPUT)
            mNumTouchMoveEvent = 0;
        int j = mTouchMode;
        if(j == 6 || j == 5)
        {
            mMotionCorrection = 0;
            return true;
        }
        int l = (int)motionevent.getX();
        int j1 = (int)motionevent.getY();
        mActivePointerId = motionevent.getPointerId(0);
        int k1 = findMotionRow(j1);
        if(j != 4 && k1 >= 0)
        {
            mMotionViewOriginalTop = getChildAt(k1 - mFirstPosition).getTop();
            mMotionX = l;
            mMotionY = j1;
            mMotionPosition = k1;
            mTouchMode = 0;
            clearScrollingCache();
        }
        mLastY = 0x80000000;
        initOrResetVelocityTracker();
        mVelocityTracker.addMovement(motionevent);
        mNestedYOffset = 0;
        startNestedScroll(2);
        if(j == 4)
            return true;
        continue; /* Loop/switch isn't completed */
_L4:
        if(OPTS_INPUT)
        {
            mNumTouchMoveEvent = mNumTouchMoveEvent + 1;
            int k;
            int i1;
            if(mNumTouchMoveEvent == 1)
                mIsFirstTouchMoveEvent = true;
            else
                mIsFirstTouchMoveEvent = false;
        }
        switch(mTouchMode)
        {
        case 0: // '\0'
            i1 = motionevent.findPointerIndex(mActivePointerId);
            k = i1;
            if(i1 == -1)
            {
                k = 0;
                mActivePointerId = motionevent.getPointerId(0);
            }
            i1 = (int)motionevent.getY(k);
            initVelocityTrackerIfNotExists();
            mVelocityTracker.addMovement(motionevent);
            if(startScrollIfNeeded((int)motionevent.getX(k), i1, null))
                return true;
            break;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(OPTS_INPUT)
            mNumTouchMoveEvent = 0;
        mTouchMode = -1;
        mActivePointerId = -1;
        recycleVelocityTracker();
        reportScrollStateChange(0);
        stopNestedScroll();
        continue; /* Loop/switch isn't completed */
_L5:
        if(OPTS_INPUT)
            mNumTouchMoveEvent = 0;
        onSecondaryPointerUp(motionevent);
        if(true) goto _L1; else goto _L6
_L6:
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(KeyEvent.isConfirmKey(i))
        {
            if(!isEnabled())
                return true;
            if(isClickable() && isPressed() && mSelectedPosition >= 0 && mAdapter != null && mSelectedPosition < mAdapter.getCount())
            {
                keyevent = getChildAt(mSelectedPosition - mFirstPosition);
                if(keyevent != null)
                {
                    performItemClick(keyevent, mSelectedPosition, mSelectedRowId);
                    keyevent.setPressed(false);
                }
                setPressed(false);
                return true;
            }
        }
        return super.onKeyUp(i, keyevent);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        mInLayout = true;
        k = getChildCount();
        if(flag)
        {
            for(i = 0; i < k; i++)
                getChildAt(i).forceLayout();

            mRecycler.markChildrenDirty();
        }
        layoutChildren();
        mOverscrollMax = (l - j) / 3;
        if(mFastScroll != null)
            mFastScroll.onItemCountChanged(getChildCount(), mItemCount);
        mInLayout = false;
    }

    protected void onMeasure(int i, int j)
    {
        boolean flag = true;
        if(mSelector == null)
            useDefaultSelector();
        Rect rect = mListPadding;
        rect.left = mSelectionLeftPadding + mPaddingLeft;
        rect.top = mSelectionTopPadding + mPaddingTop;
        rect.right = mSelectionRightPadding + mPaddingRight;
        rect.bottom = mSelectionBottomPadding + mPaddingBottom;
        if(mTranscriptMode == 1)
        {
            int k = getChildCount();
            j = getHeight() - getPaddingBottom();
            View view = getChildAt(k - 1);
            if(view != null)
                i = view.getBottom();
            else
                i = j;
            if(mFirstPosition + k >= mLastHandledItemCount)
            {
                if(i > j)
                    flag = false;
            } else
            {
                flag = false;
            }
            mForceTranscriptScroll = flag;
        }
    }

    public boolean onNestedFling(View view, float f, float f1, boolean flag)
    {
        int i = getChildCount();
        if(!flag && i > 0 && canScrollList((int)f1) && Math.abs(f1) > (float)mMinimumVelocity)
        {
            reportScrollStateChange(2);
            if(mFlingRunnable == null)
                mFlingRunnable = new FlingRunnable();
            if(!dispatchNestedPreFling(0.0F, f1))
                mFlingRunnable.start((int)f1);
            return true;
        } else
        {
            return dispatchNestedFling(f, f1, flag);
        }
    }

    public void onNestedScroll(View view, int i, int j, int k, int l)
    {
        view = getChildAt(getChildCount() / 2);
        if(view != null)
            i = view.getTop();
        else
            i = 0;
        if(view == null || trackMotionScroll(-l, -l))
        {
            k = l;
            j = 0;
            if(view != null)
            {
                j = view.getTop() - i;
                k = l - j;
            }
            dispatchNestedScroll(0, j, 0, k, null);
        }
    }

    public void onNestedScrollAccepted(View view, View view1, int i)
    {
        super.onNestedScrollAccepted(view, view1, i);
        startNestedScroll(2);
    }

    protected void onOverScrolled(int i, int j, boolean flag, boolean flag1)
    {
        if(mScrollY != j)
        {
            onScrollChanged(mScrollX, j, mScrollX, mScrollY);
            mScrollY = j;
            invalidateParentIfNeeded();
            awakenScrollBars();
        }
    }

    public boolean onRemoteAdapterConnected()
    {
        if(mRemoteAdapter != mAdapter)
        {
            setAdapter(mRemoteAdapter);
            if(mDeferNotifyDataSetChanged)
            {
                mRemoteAdapter.notifyDataSetChanged();
                mDeferNotifyDataSetChanged = false;
            }
            return false;
        }
        if(mRemoteAdapter != null)
        {
            mRemoteAdapter.superNotifyDataSetChanged();
            return true;
        } else
        {
            return false;
        }
    }

    public void onRemoteAdapterDisconnected()
    {
    }

    public PointerIcon onResolvePointerIcon(MotionEvent motionevent, int i)
    {
        if(mFastScroll != null)
        {
            PointerIcon pointericon = mFastScroll.onResolvePointerIcon(motionevent, i);
            if(pointericon != null)
                return pointericon;
        }
        return super.onResolvePointerIcon(motionevent, i);
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        mDataChanged = true;
        mSyncHeight = ((SavedState) (parcelable)).height;
        if(((SavedState) (parcelable)).selectedId < 0L) goto _L2; else goto _L1
_L1:
        mNeedSync = true;
        mPendingSync = parcelable;
        mSyncRowId = ((SavedState) (parcelable)).selectedId;
        mSyncPosition = ((SavedState) (parcelable)).position;
        mSpecificTop = ((SavedState) (parcelable)).viewTop;
        mSyncMode = 0;
_L4:
        setFilterText(((SavedState) (parcelable)).filter);
        if(((SavedState) (parcelable)).checkState != null)
            mCheckStates = ((SavedState) (parcelable)).checkState;
        if(((SavedState) (parcelable)).checkIdState != null)
            mCheckedIdStates = ((SavedState) (parcelable)).checkIdState;
        mCheckedItemCount = ((SavedState) (parcelable)).checkedItemCount;
        if(((SavedState) (parcelable)).inActionMode && mChoiceMode == 3 && mMultiChoiceModeCallback != null)
            mChoiceActionMode = startActionMode(mMultiChoiceModeCallback);
        requestLayout();
        return;
_L2:
        if(((SavedState) (parcelable)).firstId >= 0L)
        {
            setSelectedPositionInt(-1);
            setNextSelectedPositionInt(-1);
            mSelectorPosition = -1;
            mNeedSync = true;
            mPendingSync = parcelable;
            mSyncRowId = ((SavedState) (parcelable)).firstId;
            mSyncPosition = ((SavedState) (parcelable)).position;
            mSpecificTop = ((SavedState) (parcelable)).viewTop;
            mSyncMode = 1;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onRtlPropertiesChanged(int i)
    {
        super.onRtlPropertiesChanged(i);
        if(mFastScroll != null)
            mFastScroll.setScrollbarPosition(getVerticalScrollbarPosition());
    }

    public Parcelable onSaveInstanceState()
    {
        dismissPopup();
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        if(mPendingSync != null)
        {
            savedstate.selectedId = mPendingSync.selectedId;
            savedstate.firstId = mPendingSync.firstId;
            savedstate.viewTop = mPendingSync.viewTop;
            savedstate.position = mPendingSync.position;
            savedstate.height = mPendingSync.height;
            savedstate.filter = mPendingSync.filter;
            savedstate.inActionMode = mPendingSync.inActionMode;
            savedstate.checkedItemCount = mPendingSync.checkedItemCount;
            savedstate.checkState = mPendingSync.checkState;
            savedstate.checkIdState = mPendingSync.checkIdState;
            return savedstate;
        }
        int i;
        long l;
        boolean flag;
        if(getChildCount() > 0 && mItemCount > 0)
            i = 1;
        else
            i = 0;
        l = getSelectedItemId();
        savedstate.selectedId = l;
        savedstate.height = getHeight();
        if(l >= 0L)
        {
            savedstate.viewTop = mSelectedTop;
            savedstate.position = getSelectedItemPosition();
            savedstate.firstId = -1L;
        } else
        if(i != 0 && mFirstPosition > 0)
        {
            savedstate.viewTop = getChildAt(0).getTop();
            int i1 = mFirstPosition;
            int j = i1;
            if(i1 >= mItemCount)
                j = mItemCount - 1;
            savedstate.position = j;
            savedstate.firstId = mAdapter.getItemId(j);
        } else
        {
            savedstate.viewTop = 0;
            savedstate.firstId = -1L;
            savedstate.position = 0;
        }
        savedstate.filter = null;
        if(mFiltered)
        {
            Object obj = mTextFilter;
            if(obj != null)
            {
                obj = ((EditText) (obj)).getText();
                if(obj != null)
                    savedstate.filter = ((Editable) (obj)).toString();
            }
        }
        if(mChoiceMode == 3 && mChoiceActionMode != null)
            flag = true;
        else
            flag = false;
        savedstate.inActionMode = flag;
        if(mCheckStates != null)
            savedstate.checkState = mCheckStates.clone();
        if(mCheckedIdStates != null)
        {
            LongSparseArray longsparsearray = new LongSparseArray();
            int k = mCheckedIdStates.size();
            for(i = 0; i < k; i++)
                longsparsearray.put(mCheckedIdStates.keyAt(i), (Integer)mCheckedIdStates.valueAt(i));

            savedstate.checkIdState = longsparsearray;
        }
        savedstate.checkedItemCount = mCheckedItemCount;
        if(mRemoteAdapter != null)
            mRemoteAdapter.saveRemoteViewsCache();
        return savedstate;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        handleBoundsChange();
        if(mFastScroll != null)
            mFastScroll.onSizeChanged(i, j, k, l);
    }

    public boolean onStartNestedScroll(View view, View view1, int i)
    {
        boolean flag = false;
        if((i & 2) != 0)
            flag = true;
        return flag;
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        if(!isTextFilterEnabled()) goto _L2; else goto _L1
_L1:
        boolean flag;
        createTextFilter(true);
        i = charsequence.length();
        flag = mPopup.isShowing();
        if(flag || i <= 0) goto _L4; else goto _L3
_L3:
        showPopup();
        mFiltered = true;
_L6:
        if(mAdapter instanceof Filterable)
        {
            Filter filter = ((Filterable)mAdapter).getFilter();
            if(filter == null)
                break; /* Loop/switch isn't completed */
            filter.filter(charsequence, this);
        }
_L2:
        return;
_L4:
        if(flag && i == 0)
        {
            dismissPopup();
            mFiltered = false;
        }
        if(true) goto _L6; else goto _L5
_L5:
        throw new IllegalStateException("You cannot call onTextChanged with a non filterable adapter");
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        MotionEvent motionevent1;
        int i;
        boolean flag = true;
        if(!isEnabled())
        {
            if(!isClickable())
                flag = isLongClickable();
            return flag;
        }
        if(mPositionScroller != null)
            mPositionScroller.stop();
        if(mIsDetaching || isAttachedToWindow() ^ true)
            return false;
        startNestedScroll(2);
        if(mFastScroll != null && mFastScroll.onTouchEvent(motionevent))
            return true;
        initVelocityTrackerIfNotExists();
        motionevent1 = MotionEvent.obtain(motionevent);
        i = motionevent.getActionMasked();
        if(i == 0)
            mNestedYOffset = 0;
        motionevent1.offsetLocation(0.0F, mNestedYOffset);
        i;
        JVM INSTR tableswitch 0 6: default 160
    //                   0 181
    //                   1 246
    //                   2 200
    //                   3 265
    //                   4 160
    //                   5 375
    //                   6 283;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6 _L7
_L1:
        if(mVelocityTracker != null)
            mVelocityTracker.addMovement(motionevent1);
        motionevent1.recycle();
        return true;
_L2:
        onTouchDown(motionevent);
        if(OPTS_INPUT)
            mNumTouchMoveEvent = 0;
        continue; /* Loop/switch isn't completed */
_L4:
        if(OPTS_INPUT)
        {
            mNumTouchMoveEvent = mNumTouchMoveEvent + 1;
            if(mNumTouchMoveEvent == 1)
                mIsFirstTouchMoveEvent = true;
            else
                mIsFirstTouchMoveEvent = false;
        }
        onTouchMove(motionevent, motionevent1);
        continue; /* Loop/switch isn't completed */
_L3:
        onTouchUp(motionevent);
        if(OPTS_INPUT)
            mNumTouchMoveEvent = 0;
        continue; /* Loop/switch isn't completed */
_L5:
        onTouchCancel();
        if(OPTS_INPUT)
            mNumTouchMoveEvent = 0;
        continue; /* Loop/switch isn't completed */
_L7:
        onSecondaryPointerUp(motionevent);
        int l = mMotionX;
        int j = mMotionY;
        l = pointToPosition(l, j);
        if(l >= 0)
        {
            mMotionViewOriginalTop = getChildAt(l - mFirstPosition).getTop();
            mMotionPosition = l;
        }
        mDownMotionY = j - (mLastY - mDownMotionY);
        mLastY = j;
        if(OPTS_INPUT)
            mNumTouchMoveEvent = 0;
        continue; /* Loop/switch isn't completed */
_L6:
        int k = motionevent.getActionIndex();
        int j1 = motionevent.getPointerId(k);
        int i1 = (int)motionevent.getX(k);
        k = (int)motionevent.getY(k);
        mMotionCorrection = 0;
        mActivePointerId = j1;
        mMotionX = i1;
        mMotionY = k;
        i1 = pointToPosition(i1, k);
        if(i1 >= 0)
        {
            mMotionViewOriginalTop = getChildAt(i1 - mFirstPosition).getTop();
            mMotionPosition = i1;
        }
        mLastY = k;
        if(OPTS_INPUT)
            mNumTouchMoveEvent = 0;
        if(true) goto _L1; else goto _L8
_L8:
    }

    public void onTouchModeChanged(boolean flag)
    {
        if(!flag) goto _L2; else goto _L1
_L1:
        hideSelector();
        if(getHeight() > 0 && getChildCount() > 0)
            layoutChildren();
        updateSelectorState();
_L4:
        return;
_L2:
        int i = mTouchMode;
        if(i == 5 || i == 6)
        {
            if(mFlingRunnable != null)
                mFlingRunnable.endFling();
            if(mPositionScroller != null)
                mPositionScroller.stop();
            if(mScrollY != 0)
            {
                mScrollY = 0;
                invalidateParentCaches();
                finishGlows();
                invalidate();
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onWindowFocusChanged(boolean flag)
    {
        int i;
        super.onWindowFocusChanged(flag);
        if(isInTouchMode())
            i = 0;
        else
            i = 1;
        if(flag) goto _L2; else goto _L1
_L1:
        setChildrenDrawingCacheEnabled(false);
        if(mFlingRunnable != null)
        {
            removeCallbacks(mFlingRunnable);
            FlingRunnable._2D_set0(mFlingRunnable, false);
            mFlingRunnable.endFling();
            if(mPositionScroller != null)
                mPositionScroller.stop();
            if(mScrollY != 0)
            {
                mScrollY = 0;
                invalidateParentCaches();
                finishGlows();
                invalidate();
            }
        }
        dismissPopup();
        if(i == 1)
            mResurrectToPosition = mSelectedPosition;
_L4:
        mLastTouchMode = i;
        return;
_L2:
        if(mFiltered && mPopupHidden ^ true)
            showPopup();
        if(i != mLastTouchMode && mLastTouchMode != -1)
            if(i == 1)
            {
                resurrectSelection();
            } else
            {
                hideSelector();
                mLayoutMode = 0;
                layoutChildren();
            }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean performAccessibilityActionInternal(int i, Bundle bundle)
    {
        if(super.performAccessibilityActionInternal(i, bundle))
            return true;
        switch(i)
        {
        default:
            return false;

        case 4096: 
        case 16908346: 
            if(isEnabled() && canScrollDown())
            {
                smoothScrollBy(getHeight() - mListPadding.top - mListPadding.bottom, 200);
                return true;
            } else
            {
                return false;
            }

        case 8192: 
        case 16908344: 
            break;
        }
        if(isEnabled() && canScrollUp())
        {
            smoothScrollBy(-(getHeight() - mListPadding.top - mListPadding.bottom), 200);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean performItemClick(View view, int i, long l)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        flag = false;
        flag1 = true;
        flag2 = true;
        flag3 = true;
        if(mChoiceMode == 0) goto _L2; else goto _L1
_L1:
        boolean flag4;
        boolean flag5;
        flag4 = true;
        flag5 = false;
        if(mChoiceMode != 2 && (mChoiceMode != 3 || mChoiceActionMode == null)) goto _L4; else goto _L3
_L3:
        flag = mCheckStates.get(i, false) ^ true;
        mCheckStates.put(i, flag);
        if(mCheckedIdStates != null && mAdapter.hasStableIds())
            if(flag)
                mCheckedIdStates.put(mAdapter.getItemId(i), Integer.valueOf(i));
            else
                mCheckedIdStates.delete(mAdapter.getItemId(i));
        if(flag)
            mCheckedItemCount = mCheckedItemCount + 1;
        else
            mCheckedItemCount = mCheckedItemCount - 1;
        if(mChoiceActionMode != null)
        {
            mMultiChoiceModeCallback.onItemCheckedStateChanged(mChoiceActionMode, i, l, flag);
            flag3 = false;
        }
        flag5 = true;
_L6:
        flag2 = flag3;
        flag = flag4;
        if(flag5)
        {
            updateOnScreenCheckedViews();
            flag = flag4;
            flag2 = flag3;
        }
_L2:
        flag4 = flag;
        if(flag2)
            flag4 = flag | super.performItemClick(view, i, l);
        return flag4;
_L4:
        flag3 = flag1;
        if(mChoiceMode != 1)
            continue; /* Loop/switch isn't completed */
        if(!(mCheckStates.get(i, false) ^ true))
            break; /* Loop/switch isn't completed */
        mCheckStates.clear();
        mCheckStates.put(i, true);
        if(mCheckedIdStates != null && mAdapter.hasStableIds())
        {
            mCheckedIdStates.clear();
            mCheckedIdStates.put(mAdapter.getItemId(i), Integer.valueOf(i));
        }
        mCheckedItemCount = 1;
_L8:
        flag5 = true;
        flag3 = flag1;
        if(true) goto _L6; else goto _L5
_L5:
        if(mCheckStates.size() != 0 && !(mCheckStates.valueAt(0) ^ true)) goto _L8; else goto _L7
_L7:
        mCheckedItemCount = 0;
          goto _L8
    }

    boolean performLongPress(View view, int i, long l)
    {
        return performLongPress(view, i, l, -1F, -1F);
    }

    boolean performLongPress(View view, int i, long l, float f, float f1)
    {
        if(mChoiceMode == 3)
        {
            if(mChoiceActionMode == null)
            {
                view = startActionMode(mMultiChoiceModeCallback);
                mChoiceActionMode = view;
                if(view != null)
                {
                    setItemChecked(i, true);
                    performHapticFeedback(0);
                }
            }
            return true;
        }
        boolean flag = false;
        if(mOnItemLongClickListener != null)
            flag = mOnItemLongClickListener.onItemLongClick(this, view, i, l);
        boolean flag1 = flag;
        if(!flag)
        {
            mContextMenuInfo = createContextMenuInfo(view, i, l);
            if(f != -1F && f1 != -1F)
                flag1 = super.showContextMenuForChild(this, f, f1);
            else
                flag1 = super.showContextMenuForChild(this);
        }
        if(flag1)
            performHapticFeedback(0);
        return flag1;
    }

    public int pointToPosition(int i, int j)
    {
        Rect rect = mTouchFrame;
        Rect rect1 = rect;
        if(rect == null)
        {
            mTouchFrame = new Rect();
            rect1 = mTouchFrame;
        }
        for(int k = getChildCount() - 1; k >= 0; k--)
        {
            View view = getChildAt(k);
            if(view.getVisibility() != 0)
                continue;
            view.getHitRect(rect1);
            if(rect1.contains(i, j))
                return mFirstPosition + k;
        }

        return -1;
    }

    public long pointToRowId(int i, int j)
    {
        i = pointToPosition(i, j);
        if(i >= 0)
            return mAdapter.getItemId(i);
        else
            return 0x8000000000000000L;
    }

    void positionSelector(int i, View view)
    {
        positionSelector(i, view, false, -1F, -1F);
    }

    void positionSelectorLikeFocus(int i, View view)
    {
        if(mSelector != null && mSelectorPosition != i && i != -1)
        {
            Rect rect = mSelectorRect;
            positionSelector(i, view, true, rect.exactCenterX(), rect.exactCenterY());
        } else
        {
            positionSelector(i, view);
        }
    }

    void positionSelectorLikeTouch(int i, View view, float f, float f1)
    {
        positionSelector(i, view, true, f, f1);
    }

    public void reclaimViews(List list)
    {
        int i = getChildCount();
        RecyclerListener recyclerlistener = RecycleBin._2D_get0(mRecycler);
        for(int j = 0; j < i; j++)
        {
            View view = getChildAt(j);
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            if(layoutparams == null || !mRecycler.shouldRecycleViewType(layoutparams.viewType))
                continue;
            list.add(view);
            view.setAccessibilityDelegate(null);
            if(recyclerlistener != null)
                recyclerlistener.onMovedToScrapHeap(view);
        }

        mRecycler.reclaimScrapViews(list);
        removeAllViewsInLayout();
    }

    int reconcileSelectedPosition()
    {
        int i = mSelectedPosition;
        int j = i;
        if(i < 0)
            j = mResurrectToPosition;
        return Math.min(Math.max(0, j), mItemCount - 1);
    }

    void reportScrollStateChange(int i)
    {
        if(i != mLastScrollState && mOnScrollListener != null)
        {
            mLastScrollState = i;
            mOnScrollListener.onScrollStateChanged(this, i);
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean flag)
    {
        if(flag)
            recycleVelocityTracker();
        super.requestDisallowInterceptTouchEvent(flag);
    }

    public void requestLayout()
    {
        if(!mBlockLayoutRequests && mInLayout ^ true)
            super.requestLayout();
    }

    void requestLayoutIfNecessary()
    {
        if(getChildCount() > 0)
        {
            resetList();
            requestLayout();
            invalidate();
        }
    }

    void resetList()
    {
        removeAllViewsInLayout();
        mFirstPosition = 0;
        mDataChanged = false;
        mPositionScrollAfterLayout = null;
        mNeedSync = false;
        mPendingSync = null;
        mOldSelectedPosition = -1;
        mOldSelectedRowId = 0x8000000000000000L;
        setSelectedPositionInt(-1);
        setNextSelectedPositionInt(-1);
        mSelectedTop = 0;
        mSelectorPosition = -1;
        mSelectorRect.setEmpty();
        invalidate();
    }

    boolean resurrectSelection()
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        boolean flag;
        i = getChildCount();
        if(i <= 0)
            return false;
        j = 0;
        k = 0;
        l = mListPadding.top;
        i1 = mBottom - mTop - mListPadding.bottom;
        j1 = mFirstPosition;
        k1 = mResurrectToPosition;
        flag = true;
        if(k1 < j1 || k1 >= j1 + i) goto _L2; else goto _L1
_L1:
        boolean flag1;
        j = k1;
        View view = getChildAt(k1 - mFirstPosition);
        k = view.getTop();
        int l1 = view.getBottom();
        if(k < l)
        {
            k = l + getVerticalFadingEdgeLength();
            k1 = j;
            flag1 = flag;
        } else
        {
            flag1 = flag;
            k1 = j;
            if(l1 > i1)
            {
                k = i1 - view.getMeasuredHeight() - getVerticalFadingEdgeLength();
                flag1 = flag;
                k1 = j;
            }
        }
_L3:
        mResurrectToPosition = -1;
        removeCallbacks(mFlingRunnable);
        if(mPositionScroller != null)
            mPositionScroller.stop();
        mTouchMode = -1;
        clearScrollingCache();
        mSpecificTop = k;
        k = lookForSelectablePosition(k1, flag1);
        View view1;
        int i2;
        int j2;
        int k2;
        int l2;
        if(k >= j1 && k <= getLastVisiblePosition())
        {
            mLayoutMode = 4;
            updateSelectorState();
            setSelectionInt(k);
            invokeOnItemScrollListener();
        } else
        {
            k = -1;
        }
        reportScrollStateChange(0);
        if(k >= 0)
            flag1 = true;
        else
            flag1 = false;
        return flag1;
_L2:
        if(k1 >= j1)
            break MISSING_BLOCK_LABEL_374;
        i2 = j1;
        i1 = 0;
        j = k;
_L4:
        flag1 = flag;
        k1 = i2;
        k = j;
        if(i1 < i)
        {
label0:
            {
                k = getChildAt(i1).getTop();
                k1 = l;
                if(i1 != 0)
                    break label0;
                j2 = k;
                if(j1 <= 0)
                {
                    k1 = l;
                    j = j2;
                    if(k >= l)
                        break label0;
                }
                k1 = l + getVerticalFadingEdgeLength();
                j = j2;
            }
label1:
            {
                if(k < k1)
                    break label1;
                k1 = j1 + i1;
                flag1 = flag;
            }
        }
          goto _L3
        i1++;
        l = k1;
          goto _L4
        k2 = mItemCount;
        flag = false;
        j2 = (j1 + i) - 1;
        i2 = i - 1;
_L5:
        flag1 = flag;
        k1 = j2;
        k = j;
        if(i2 >= 0)
        {
label2:
            {
                view1 = getChildAt(i2);
                k = view1.getTop();
                l2 = view1.getBottom();
                k1 = i1;
                if(i2 != i - 1)
                    break label2;
                l = k;
                if(j1 + i >= k2)
                {
                    k1 = i1;
                    j = l;
                    if(l2 <= i1)
                        break label2;
                }
                k1 = i1 - getVerticalFadingEdgeLength();
                j = l;
            }
label3:
            {
                if(l2 > k1)
                    break label3;
                k1 = j1 + i2;
                flag1 = flag;
            }
        }
          goto _L3
        i2--;
        i1 = k1;
          goto _L5
    }

    boolean resurrectSelectionIfNeeded()
    {
        if(mSelectedPosition < 0 && resurrectSelection())
        {
            updateSelectorState();
            return true;
        } else
        {
            return false;
        }
    }

    public void scrollListBy(int i)
    {
        trackMotionScroll(-i, -i);
    }

    public void sendAccessibilityEventInternal(int i)
    {
        if(i == 4096)
        {
            int j = getFirstVisiblePosition();
            int k = getLastVisiblePosition();
            if(mLastAccessibilityScrollEventFromIndex == j && mLastAccessibilityScrollEventToIndex == k)
                return;
            mLastAccessibilityScrollEventFromIndex = j;
            mLastAccessibilityScrollEventToIndex = k;
        }
        super.sendAccessibilityEventInternal(i);
    }

    boolean sendToTextFilter(int i, int j, KeyEvent keyevent)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        if(!acceptFilter())
            return false;
        flag = false;
        flag1 = false;
        flag2 = true;
        i;
        JVM INSTR lookupswitch 8: default 92
    //                   4: 169
    //                   19: 163
    //                   20: 163
    //                   21: 163
    //                   22: 163
    //                   23: 163
    //                   62: 306
    //                   66: 163;
           goto _L1 _L2 _L3 _L3 _L3 _L3 _L3 _L4 _L3
_L1:
        flag = flag1;
        if(!flag2) goto _L6; else goto _L5
_L5:
        Object obj;
        createTextFilter(true);
        obj = keyevent;
        if(keyevent.getRepeatCount() > 0)
            obj = KeyEvent.changeTimeRepeat(keyevent, keyevent.getEventTime(), 0);
        keyevent.getAction();
        JVM INSTR tableswitch 0 2: default 156
    //                   0 315
    //                   1 330
    //                   2 345;
           goto _L7 _L8 _L9 _L10
_L10:
        break MISSING_BLOCK_LABEL_345;
_L7:
        flag = flag1;
_L6:
        return flag;
_L3:
        flag2 = false;
          goto _L1
_L2:
        flag2 = flag;
        if(mFiltered)
        {
            flag2 = flag;
            if(mPopup != null)
            {
                flag2 = flag;
                if(mPopup.isShowing())
                    if(keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0)
                    {
                        obj = getKeyDispatcherState();
                        if(obj != null)
                            ((android.view.KeyEvent.DispatcherState) (obj)).startTracking(keyevent, this);
                        flag2 = true;
                    } else
                    {
                        flag2 = flag;
                        if(keyevent.getAction() == 1)
                        {
                            flag2 = flag;
                            if(keyevent.isTracking())
                            {
                                flag2 = flag;
                                if(keyevent.isCanceled() ^ true)
                                {
                                    flag2 = true;
                                    mTextFilter.setText("");
                                }
                            }
                        }
                    }
            }
        }
        flag = false;
        flag1 = flag2;
        flag2 = flag;
          goto _L1
_L4:
        flag2 = mFiltered;
          goto _L1
_L8:
        flag = mTextFilter.onKeyDown(i, ((KeyEvent) (obj)));
          goto _L6
_L9:
        flag = mTextFilter.onKeyUp(i, ((KeyEvent) (obj)));
          goto _L6
        flag = mTextFilter.onKeyMultiple(i, j, keyevent);
          goto _L6
    }

    public volatile void setAdapter(Adapter adapter)
    {
        setAdapter((ListAdapter)adapter);
    }

    public void setAdapter(ListAdapter listadapter)
    {
        if(listadapter != null)
        {
            mAdapterHasStableIds = mAdapter.hasStableIds();
            if(mChoiceMode != 0 && mAdapterHasStableIds && mCheckedIdStates == null)
                mCheckedIdStates = new LongSparseArray();
        }
        clearChoices();
    }

    public void setCacheColorHint(int i)
    {
        if(i != mCacheColorHint)
        {
            mCacheColorHint = i;
            int j = getChildCount();
            for(int k = 0; k < j; k++)
                getChildAt(k).setDrawingCacheBackgroundColor(i);

            mRecycler.setCacheColorHint(i);
        }
    }

    public void setChoiceMode(int i)
    {
        mChoiceMode = i;
        if(mChoiceActionMode != null)
        {
            mChoiceActionMode.finish();
            mChoiceActionMode = null;
        }
        if(mChoiceMode != 0)
        {
            if(mCheckStates == null)
                mCheckStates = new SparseBooleanArray(0);
            if(mCheckedIdStates == null && mAdapter != null && mAdapter.hasStableIds())
                mCheckedIdStates = new LongSparseArray(0);
            if(mChoiceMode == 3)
            {
                clearChoices();
                setLongClickable(true);
            }
        }
    }

    public void setDrawSelectorOnTop(boolean flag)
    {
        mDrawSelectorOnTop = flag;
    }

    public void setFastScrollAlwaysVisible(final boolean alwaysShow)
    {
        if(mFastScrollAlwaysVisible != alwaysShow)
        {
            if(alwaysShow && mFastScrollEnabled ^ true)
                setFastScrollEnabled(true);
            mFastScrollAlwaysVisible = alwaysShow;
            if(isOwnerThread())
                setFastScrollerAlwaysVisibleUiThread(alwaysShow);
            else
                post(new Runnable() {

                    public void run()
                    {
                        AbsListView._2D_wrap9(AbsListView.this, alwaysShow);
                    }

                    final AbsListView this$0;
                    final boolean val$alwaysShow;

            
            {
                this$0 = AbsListView.this;
                alwaysShow = flag;
                super();
            }
                }
);
        }
    }

    public void setFastScrollEnabled(final boolean enabled)
    {
        if(mFastScrollEnabled != enabled)
        {
            mFastScrollEnabled = enabled;
            if(isOwnerThread())
                setFastScrollerEnabledUiThread(enabled);
            else
                post(new Runnable() {

                    public void run()
                    {
                        AbsListView._2D_wrap10(AbsListView.this, enabled);
                    }

                    final AbsListView this$0;
                    final boolean val$enabled;

            
            {
                this$0 = AbsListView.this;
                enabled = flag;
                super();
            }
                }
);
        }
    }

    public void setFastScrollStyle(int i)
    {
        if(mFastScroll == null)
            mFastScrollStyle = i;
        else
            mFastScroll.setStyle(i);
    }

    public void setFilterText(String s)
    {
        if(mTextFilterEnabled && TextUtils.isEmpty(s) ^ true)
        {
            createTextFilter(false);
            mTextFilter.setText(s);
            mTextFilter.setSelection(s.length());
            if(mAdapter instanceof Filterable)
            {
                if(mPopup == null)
                    ((Filterable)mAdapter).getFilter().filter(s);
                mFiltered = true;
                mDataSetObserver.clearSavedState();
            }
        }
    }

    protected boolean setFrame(int i, int j, int k, int l)
    {
        boolean flag = super.setFrame(i, j, k, l);
        if(flag)
        {
            if(getWindowVisibility() == 0)
                i = 1;
            else
                i = 0;
            if(mFiltered && i != 0 && mPopup != null && mPopup.isShowing())
                positionPopup();
        }
        return flag;
    }

    public void setFriction(float f)
    {
        if(mFlingRunnable == null)
            mFlingRunnable = new FlingRunnable();
        FlingRunnable._2D_get0(mFlingRunnable).setFriction(f);
    }

    public void setItemChecked(int i, boolean flag)
    {
        if(mChoiceMode == 0)
            return;
        if(flag && mChoiceMode == 3 && mChoiceActionMode == null)
        {
            if(mMultiChoiceModeCallback == null || mMultiChoiceModeCallback.hasWrappedCallback() ^ true)
                throw new IllegalStateException("AbsListView: attempted to start selection mode for CHOICE_MODE_MULTIPLE_MODAL but no choice mode callback was supplied. Call setMultiChoiceModeListener to set a callback.");
            mChoiceActionMode = startActionMode(mMultiChoiceModeCallback);
        }
        if(mChoiceMode != 2 && mChoiceMode != 3) goto _L2; else goto _L1
_L1:
        boolean flag5;
        boolean flag1 = mCheckStates.get(i);
        mCheckStates.put(i, flag);
        boolean flag3;
        if(mCheckedIdStates != null && mAdapter.hasStableIds())
            if(flag)
                mCheckedIdStates.put(mAdapter.getItemId(i), Integer.valueOf(i));
            else
                mCheckedIdStates.delete(mAdapter.getItemId(i));
        if(flag1 != flag)
            flag3 = true;
        else
            flag3 = false;
        if(flag3)
            if(flag)
                mCheckedItemCount = mCheckedItemCount + 1;
            else
                mCheckedItemCount = mCheckedItemCount - 1;
        flag5 = flag3;
        if(mChoiceActionMode != null)
        {
            long l = mAdapter.getItemId(i);
            mMultiChoiceModeCallback.onItemCheckedStateChanged(mChoiceActionMode, i, l, flag);
            flag5 = flag3;
        }
_L4:
        if(!mInLayout && mBlockLayoutRequests ^ true && flag5)
        {
            mDataChanged = true;
            rememberSyncState();
            requestLayout();
        }
        return;
_L2:
        boolean flag2;
        boolean flag4;
        if(mCheckedIdStates != null)
            flag2 = mAdapter.hasStableIds();
        else
            flag2 = false;
        if(isItemChecked(i) != flag)
            flag4 = true;
        else
            flag4 = false;
        if(flag || isItemChecked(i))
        {
            mCheckStates.clear();
            if(flag2)
                mCheckedIdStates.clear();
        }
        if(flag)
        {
            mCheckStates.put(i, true);
            if(flag2)
                mCheckedIdStates.put(mAdapter.getItemId(i), Integer.valueOf(i));
            mCheckedItemCount = 1;
            flag5 = flag4;
            continue; /* Loop/switch isn't completed */
        }
        if(mCheckStates.size() != 0)
        {
            flag5 = flag4;
            if(!(mCheckStates.valueAt(0) ^ true))
                continue; /* Loop/switch isn't completed */
        }
        mCheckedItemCount = 0;
        flag5 = flag4;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setMultiChoiceModeListener(MultiChoiceModeListener multichoicemodelistener)
    {
        if(mMultiChoiceModeCallback == null)
            mMultiChoiceModeCallback = new MultiChoiceModeWrapper();
        mMultiChoiceModeCallback.setWrapped(multichoicemodelistener);
    }

    public void setOnScrollListener(OnScrollListener onscrolllistener)
    {
        mOnScrollListener = onscrolllistener;
        invokeOnItemScrollListener();
    }

    public void setOverScrollMode(int i)
    {
        if(i != 2)
        {
            if(mEdgeGlowTop == null)
            {
                Context context = getContext();
                mEdgeGlowTop = new EdgeEffect(context);
                mEdgeGlowBottom = new EdgeEffect(context);
            }
        } else
        {
            mEdgeGlowTop = null;
            mEdgeGlowBottom = null;
        }
        super.setOverScrollMode(i);
    }

    public void setRecyclerListener(RecyclerListener recyclerlistener)
    {
        RecycleBin._2D_set0(mRecycler, recyclerlistener);
    }

    public void setRemoteViewsAdapter(Intent intent)
    {
        setRemoteViewsAdapter(intent, false);
    }

    public void setRemoteViewsAdapter(Intent intent, boolean flag)
    {
        if(mRemoteAdapter != null && (new android.content.Intent.FilterComparison(intent)).equals(new android.content.Intent.FilterComparison(mRemoteAdapter.getRemoteViewsServiceIntent())))
            return;
        mDeferNotifyDataSetChanged = false;
        mRemoteAdapter = new RemoteViewsAdapter(getContext(), intent, this, flag);
        if(mRemoteAdapter.isDataReady())
            setAdapter(mRemoteAdapter);
    }

    public Runnable setRemoteViewsAdapterAsync(Intent intent)
    {
        return new RemoteViewsAdapter.AsyncRemoteAdapterAction(this, intent);
    }

    public void setRemoteViewsOnClickHandler(RemoteViews.OnClickHandler onclickhandler)
    {
        if(mRemoteAdapter != null)
            mRemoteAdapter.setRemoteViewsOnClickHandler(onclickhandler);
    }

    public void setScrollBarStyle(int i)
    {
        super.setScrollBarStyle(i);
        if(mFastScroll != null)
            mFastScroll.setScrollBarStyle(i);
    }

    public void setScrollIndicators(View view, View view1)
    {
        mScrollUp = view;
        mScrollDown = view1;
    }

    public void setScrollingCacheEnabled(boolean flag)
    {
        if(mScrollingCacheEnabled && flag ^ true)
            clearScrollingCache();
        mScrollingCacheEnabled = flag;
    }

    public void setSelectionFromTop(int i, int j)
    {
        if(mAdapter == null)
            return;
        if(!isInTouchMode())
        {
            int k = lookForSelectablePosition(i, true);
            i = k;
            if(k >= 0)
            {
                setNextSelectedPositionInt(k);
                i = k;
            }
        } else
        {
            mResurrectToPosition = i;
        }
        if(i >= 0)
        {
            mLayoutMode = 4;
            mSpecificTop = mListPadding.top + j;
            if(mNeedSync)
            {
                mSyncPosition = i;
                mSyncRowId = mAdapter.getItemId(i);
            }
            if(mPositionScroller != null)
                mPositionScroller.stop();
            requestLayout();
        }
    }

    abstract void setSelectionInt(int i);

    public void setSelector(int i)
    {
        setSelector(getContext().getDrawable(i));
    }

    public void setSelector(Drawable drawable)
    {
        if(mSelector != null)
        {
            mSelector.setCallback(null);
            unscheduleDrawable(mSelector);
        }
        mSelector = drawable;
        Rect rect = new Rect();
        drawable.getPadding(rect);
        mSelectionLeftPadding = rect.left;
        mSelectionTopPadding = rect.top;
        mSelectionRightPadding = rect.right;
        mSelectionBottomPadding = rect.bottom;
        drawable.setCallback(this);
        updateSelectorState();
    }

    public void setSmoothScrollbarEnabled(boolean flag)
    {
        mSmoothScrollbarEnabled = flag;
    }

    public void setStackFromBottom(boolean flag)
    {
        if(mStackFromBottom != flag)
        {
            mStackFromBottom = flag;
            requestLayoutIfNecessary();
        }
    }

    public void setTextFilterEnabled(boolean flag)
    {
        mTextFilterEnabled = flag;
    }

    public void setTranscriptMode(int i)
    {
        mTranscriptMode = i;
    }

    public void setVelocityScale(float f)
    {
        mVelocityScale = f;
    }

    public void setVerticalScrollbarPosition(int i)
    {
        super.setVerticalScrollbarPosition(i);
        if(mFastScroll != null)
            mFastScroll.setScrollbarPosition(i);
    }

    void setVisibleRangeHint(int i, int j)
    {
        if(mRemoteAdapter != null)
            mRemoteAdapter.setVisibleRangeHint(i, j);
    }

    boolean shouldShowSelector()
    {
        boolean flag;
        if(!isFocused() || !(isInTouchMode() ^ true))
        {
            if(touchModeDrawsInPressedState())
                flag = isPressed();
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public boolean showContextMenu()
    {
        return showContextMenuInternal(0.0F, 0.0F, false);
    }

    public boolean showContextMenu(float f, float f1)
    {
        return showContextMenuInternal(f, f1, true);
    }

    public boolean showContextMenuForChild(View view)
    {
        if(isShowingContextMenuWithCoords())
            return false;
        else
            return showContextMenuForChildInternal(view, 0.0F, 0.0F, false);
    }

    public boolean showContextMenuForChild(View view, float f, float f1)
    {
        return showContextMenuForChildInternal(view, f, f1, true);
    }

    public void smoothScrollBy(int i, int j)
    {
        smoothScrollBy(i, j, false, false);
    }

    void smoothScrollBy(int i, int j, boolean flag, boolean flag1)
    {
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        if(mFlingRunnable == null)
            mFlingRunnable = new FlingRunnable();
        k = mFirstPosition;
        l = getChildCount();
        i1 = getPaddingTop();
        j1 = getHeight();
        k1 = getPaddingBottom();
        break MISSING_BLOCK_LABEL_49;
        if(i == 0 || mItemCount == 0 || l == 0 || k == 0 && getChildAt(0).getTop() == i1 && i < 0 || k + l == mItemCount && getChildAt(l - 1).getBottom() == j1 - k1 && i > 0)
        {
            mFlingRunnable.endFling();
            if(mPositionScroller != null)
                mPositionScroller.stop();
        } else
        {
            reportScrollStateChange(2);
            mFlingRunnable.startScroll(i, j, flag, flag1);
        }
        return;
    }

    void smoothScrollByOffset(int i)
    {
        int j = -1;
        if(i < 0)
            j = getFirstVisiblePosition();
        else
        if(i > 0)
            j = getLastVisiblePosition();
        if(j > -1)
        {
            View view = getChildAt(j - getFirstVisiblePosition());
            if(view != null)
            {
                Rect rect = new Rect();
                int k = j;
                if(view.getGlobalVisibleRect(rect))
                {
                    int l = view.getWidth();
                    k = view.getHeight();
                    float f = (float)(rect.width() * rect.height()) / (float)(l * k);
                    if(i < 0 && f < 0.75F)
                    {
                        k = j + 1;
                    } else
                    {
                        k = j;
                        if(i > 0)
                        {
                            k = j;
                            if(f < 0.75F)
                                k = j - 1;
                        }
                    }
                }
                smoothScrollToPosition(Math.max(0, Math.min(getCount(), k + i)));
            }
        }
    }

    public void smoothScrollToPosition(int i)
    {
        if(mPositionScroller == null)
            mPositionScroller = createPositionScroller();
        mPositionScroller.start(i);
    }

    public void smoothScrollToPosition(int i, int j)
    {
        if(mPositionScroller == null)
            mPositionScroller = createPositionScroller();
        mPositionScroller.start(i, j);
    }

    public void smoothScrollToPositionFromTop(int i, int j)
    {
        if(mPositionScroller == null)
            mPositionScroller = createPositionScroller();
        mPositionScroller.startWithOffset(i, j);
    }

    public void smoothScrollToPositionFromTop(int i, int j, int k)
    {
        if(mPositionScroller == null)
            mPositionScroller = createPositionScroller();
        mPositionScroller.startWithOffset(i, j, k);
    }

    boolean touchModeDrawsInPressedState()
    {
        switch(mTouchMode)
        {
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
            return true;
        }
    }

    boolean trackMotionScroll(int i, int j)
    {
        int k;
        Object obj;
        int i2;
        int k2;
        int l2;
        int i3;
        int j3;
        int l3;
        int i4;
        k = getChildCount();
        if(k == 0)
            return true;
        int l = getChildAt(0).getTop();
        int i1 = getChildAt(k - 1).getBottom();
        obj = mListPadding;
        int j1 = 0;
        int k1 = 0;
        if((mGroupFlags & 0x22) == 34)
        {
            j1 = ((Rect) (obj)).top;
            k1 = ((Rect) (obj)).bottom;
        }
        int l1 = getHeight();
        i2 = getHeight() - mPaddingBottom - mPaddingTop;
        int j2;
        if(i < 0)
            j2 = Math.max(-(i2 - 1), i);
        else
            j2 = Math.min(i2 - 1, i);
        if(j < 0)
            k2 = Math.max(-(i2 - 1), j);
        else
            k2 = Math.min(i2 - 1, j);
        l2 = mFirstPosition;
        if(l2 == 0)
            mFirstPositionDistanceGuess = l - ((Rect) (obj)).top;
        else
            mFirstPositionDistanceGuess = mFirstPositionDistanceGuess + k2;
        if(l2 + k == mItemCount)
            mLastPositionDistanceGuess = ((Rect) (obj)).bottom + i1;
        else
            mLastPositionDistanceGuess = mLastPositionDistanceGuess + k2;
        if(l2 == 0 && l >= ((Rect) (obj)).top)
        {
            if(k2 >= 0)
                i = 1;
            else
                i = 0;
        } else
        {
            i = 0;
        }
        if(l2 + k == mItemCount && i1 <= getHeight() - ((Rect) (obj)).bottom)
        {
            if(k2 <= 0)
                j = 1;
            else
                j = 0;
        } else
        {
            j = 0;
        }
        if(i != 0 || j != 0)
        {
            boolean flag;
            if(k2 != 0)
                flag = true;
            else
                flag = false;
            return flag;
        }
        boolean flag1;
        boolean flag2;
        boolean flag3;
        if(k2 < 0)
            flag1 = true;
        else
            flag1 = false;
        flag2 = isInTouchMode();
        if(flag2)
            hideSelector();
        i3 = getHeaderViewsCount();
        j3 = mItemCount - getFooterViewsCount();
        j = 0;
        flag3 = false;
        i2 = 0;
        i = 0;
        if(!flag1) goto _L2; else goto _L1
_L1:
        i2 = -k2;
        j = i2;
        if((mGroupFlags & 0x22) == 34)
            j = i2 + ((Rect) (obj)).top;
        i2 = 0;
_L7:
        l3 = i;
        i4 = ((flag3) ? 1 : 0);
        if(i2 >= k) goto _L4; else goto _L3
_L3:
        obj = getChildAt(i2);
        if(((View) (obj)).getBottom() < j) goto _L6; else goto _L5
_L5:
        i4 = ((flag3) ? 1 : 0);
        l3 = i;
_L4:
        mMotionViewNewTop = mMotionViewOriginalTop + j2;
        mBlockLayoutRequests = true;
        if(l3 > 0)
        {
            detachViewsFromParent(i4, l3);
            mRecycler.removeSkippedScrap();
        }
        if(!awakenScrollBars())
            invalidate();
        offsetChildrenTopAndBottom(k2);
        if(flag1)
            mFirstPosition = mFirstPosition + l3;
        i = Math.abs(k2);
        if(j1 - l < i || i1 - (l1 - k1) < i)
            fillGap(flag1);
        mRecycler.fullyDetachScrapViews();
        int k3;
        if(!flag2 && mSelectedPosition != -1)
        {
            i = mSelectedPosition - mFirstPosition;
            if(i >= 0 && i < getChildCount())
                positionSelector(mSelectedPosition, getChildAt(i));
        } else
        if(mSelectorPosition != -1)
        {
            i = mSelectorPosition - mFirstPosition;
            if(i >= 0 && i < getChildCount())
                positionSelector(-1, getChildAt(i));
        } else
        {
            mSelectorRect.setEmpty();
        }
        mBlockLayoutRequests = false;
        invokeOnItemScrollListener();
        return false;
_L6:
        i++;
        l3 = l2 + i2;
        if(l3 >= i3 && l3 < j3)
        {
            ((View) (obj)).clearAccessibilityFocus();
            mRecycler.addScrapView(((View) (obj)), l3);
        }
        i2++;
          goto _L7
_L2:
        i = getHeight() - k2;
        k3 = i;
        if((mGroupFlags & 0x22) == 34)
            k3 = i - ((Rect) (obj)).bottom;
        i = k - 1;
_L10:
        l3 = i2;
        i4 = j;
        if(i < 0) goto _L4; else goto _L8
_L8:
        obj = getChildAt(i);
        l3 = i2;
        i4 = j;
        if(((View) (obj)).getTop() <= k3) goto _L4; else goto _L9
_L9:
        j = i;
        i2++;
        l3 = l2 + i;
        if(l3 >= i3 && l3 < j3)
        {
            ((View) (obj)).clearAccessibilityFocus();
            mRecycler.addScrapView(((View) (obj)), l3);
        }
        i--;
          goto _L10
    }

    void updateScrollIndicators()
    {
        boolean flag = false;
        int i;
        if(mScrollUp != null)
        {
            View view = mScrollUp;
            if(canScrollUp())
                i = 0;
            else
                i = 4;
            view.setVisibility(i);
        }
        if(mScrollDown != null)
        {
            view = mScrollDown;
            if(canScrollDown())
                i = ((flag) ? 1 : 0);
            else
                i = 4;
            view.setVisibility(i);
        }
    }

    void updateSelectorState()
    {
        Drawable drawable = mSelector;
        if(drawable != null && drawable.isStateful())
            if(shouldShowSelector())
            {
                if(drawable.setState(getDrawableStateForSelector()))
                    invalidateDrawable(drawable);
            } else
            {
                drawable.setState(StateSet.NOTHING);
            }
    }

    public boolean verifyDrawable(Drawable drawable)
    {
        boolean flag;
        if(mSelector != drawable)
            flag = super.verifyDrawable(drawable);
        else
            flag = true;
        return flag;
    }

    static final int BOUNCE_BACK_TIME = 400;
    static final float BOUNCE_DECAY = 0.98F;
    static final int BOUNCE_FORWARD_TIME = 200;
    static final int BOUNCE_MULTIPLY = 3;
    private static final int CHECK_POSITION_SEARCH_DISTANCE = 20;
    public static final int CHOICE_MODE_MULTIPLE = 2;
    public static final int CHOICE_MODE_MULTIPLE_MODAL = 3;
    public static final int CHOICE_MODE_NONE = 0;
    public static final int CHOICE_MODE_SINGLE = 1;
    private static final int INVALID_POINTER = -1;
    static final int LAYOUT_FORCE_BOTTOM = 3;
    static final int LAYOUT_FORCE_TOP = 1;
    static final int LAYOUT_MOVE_SELECTION = 6;
    static final int LAYOUT_NORMAL = 0;
    static final int LAYOUT_SET_SELECTION = 2;
    static final int LAYOUT_SPECIFIC = 4;
    static final int LAYOUT_SYNC = 5;
    private static final String MOVE_TOUCH_SLOP = SystemProperties.get("persist.vendor.qti.inputopts.movetouchslop", "0.6");
    private static final boolean OPTS_INPUT = SystemProperties.getBoolean("persist.vendor.qti.inputopts.enable", false);
    static final int OVERSCROLL_LIMIT_DIVISOR = 3;
    private static final boolean PROFILE_FLINGING = false;
    private static final boolean PROFILE_SCROLLING = false;
    private static final String TAG = "AbsListView";
    static final int TOUCH_MODE_DONE_WAITING = 2;
    static final int TOUCH_MODE_DOWN = 0;
    static final int TOUCH_MODE_FLING = 4;
    private static final int TOUCH_MODE_OFF = 1;
    private static final int TOUCH_MODE_ON = 0;
    static final int TOUCH_MODE_OVERFLING = 6;
    static final int TOUCH_MODE_OVERSCROLL = 5;
    static final int TOUCH_MODE_REST = -1;
    static final int TOUCH_MODE_SCROLL = 3;
    static final int TOUCH_MODE_TAP = 1;
    private static final int TOUCH_MODE_UNKNOWN = -1;
    private static final double TOUCH_SLOP_MAX = 1D;
    private static final double TOUCH_SLOP_MIN = 0.59999999999999998D;
    public static final int TRANSCRIPT_MODE_ALWAYS_SCROLL = 2;
    public static final int TRANSCRIPT_MODE_DISABLED = 0;
    public static final int TRANSCRIPT_MODE_NORMAL = 1;
    static final int TRANSFORM_DELAY = 1;
    static final Interpolator sLinearInterpolator = new LinearInterpolator();
    private ListItemAccessibilityDelegate mAccessibilityDelegate;
    private int mActivePointerId;
    ListAdapter mAdapter;
    boolean mAdapterHasStableIds;
    AnimatorSet mAnimatorSet;
    private int mCacheColorHint;
    boolean mCachingActive;
    boolean mCachingStarted;
    SparseBooleanArray mCheckStates;
    LongSparseArray mCheckedIdStates;
    int mCheckedItemCount;
    ActionMode mChoiceActionMode;
    int mChoiceMode;
    private Runnable mClearScrollingCache;
    private android.view.ContextMenu.ContextMenuInfo mContextMenuInfo;
    AdapterDataSetObserver mDataSetObserver;
    private InputConnection mDefInputConnection;
    private boolean mDeferNotifyDataSetChanged;
    private float mDensityScale;
    private int mDirection;
    int mDownMotionY;
    boolean mDrawSelectorOnTop;
    private EdgeEffect mEdgeGlowBottom;
    private EdgeEffect mEdgeGlowTop;
    private FastScroller mFastScroll;
    boolean mFastScrollAlwaysVisible;
    boolean mFastScrollEnabled;
    private int mFastScrollStyle;
    private boolean mFiltered;
    private int mFirstPositionDistanceGuess;
    private boolean mFlingProfilingStarted;
    private FlingRunnable mFlingRunnable;
    private android.os.StrictMode.Span mFlingStrictSpan;
    private boolean mForceTranscriptScroll;
    private boolean mGlobalLayoutListenerAddedFilter;
    private boolean mHasPerformedLongPress;
    int mInertia;
    private boolean mIsChildViewEnabled;
    private boolean mIsDetaching;
    private boolean mIsFirstTouchMoveEvent;
    final boolean mIsScrap[];
    boolean mIsShortList;
    boolean mIsTouching;
    private int mLastAccessibilityScrollEventFromIndex;
    private int mLastAccessibilityScrollEventToIndex;
    private int mLastHandledItemCount;
    float mLastPivotX;
    float mLastPivotY;
    private int mLastPositionDistanceGuess;
    private int mLastScrollState;
    private int mLastTouchMode;
    int mLastY;
    int mLayoutMode;
    Rect mListPadding;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    int mMotionCorrection;
    int mMotionPosition;
    int mMotionViewNewTop;
    int mMotionViewOriginalTop;
    int mMotionX;
    int mMotionY;
    private int mMoveAcceleration;
    MultiChoiceModeWrapper mMultiChoiceModeCallback;
    private int mNestedYOffset;
    private int mNumTouchMoveEvent;
    int mOffsetRevise;
    private OnScrollListener mOnScrollListener;
    int mOverflingDistance;
    int mOverscrollDistance;
    int mOverscrollMax;
    private final Thread mOwnerThread;
    private CheckForKeyLongPress mPendingCheckForKeyLongPress;
    private CheckForLongPress mPendingCheckForLongPress;
    private CheckForTap mPendingCheckForTap;
    private SavedState mPendingSync;
    private PerformClick mPerformClick;
    PopupWindow mPopup;
    private boolean mPopupHidden;
    Runnable mPositionScrollAfterLayout;
    AbsPositionScroller mPositionScroller;
    private InputConnectionWrapper mPublicInputConnection;
    final RecycleBin mRecycler;
    private RemoteViewsAdapter mRemoteAdapter;
    int mResurrectToPosition;
    float mScaleY;
    boolean mScaleYDirty;
    private final int mScrollConsumed[];
    View mScrollDown;
    private final int mScrollOffset[];
    private boolean mScrollProfilingStarted;
    private android.os.StrictMode.Span mScrollStrictSpan;
    View mScrollUp;
    boolean mScrollingCacheEnabled;
    int mSelectedTop;
    int mSelectionBottomPadding;
    int mSelectionLeftPadding;
    int mSelectionRightPadding;
    int mSelectionTopPadding;
    Drawable mSelector;
    int mSelectorPosition;
    Rect mSelectorRect;
    private int mSelectorState[];
    private boolean mSmoothScrollbarEnabled;
    boolean mStackFromBottom;
    EditText mTextFilter;
    private boolean mTextFilterEnabled;
    private final float mTmpPoint[];
    private Rect mTouchFrame;
    int mTouchMode;
    private Runnable mTouchModeReset;
    private int mTouchSlop;
    private int mTranscriptMode;
    private float mVelocityScale;
    private VelocityTracker mVelocityTracker;
    private float mVerticalScrollFactor;
    int mWidthMeasureSpec;


    // Unreferenced inner class android/widget/AbsListView$FlingRunnable$1

/* anonymous class */
    class FlingRunnable._cls1
        implements Runnable
    {

        public void run()
        {
            int i = AbsListView._2D_get0(_fld0);
            VelocityTracker velocitytracker = AbsListView._2D_get15(_fld0);
            OverScroller overscroller = FlingRunnable._2D_get0(FlingRunnable.this);
            if(velocitytracker == null || i == -1)
                return;
            velocitytracker.computeCurrentVelocity(1000, AbsListView._2D_get7(_fld0));
            float f = -velocitytracker.getYVelocity(i);
            if(Math.abs(f) >= (float)AbsListView._2D_get8(_fld0) && overscroller.isScrollingInDirection(0.0F, f))
            {
                postDelayed(this, 40L);
            } else
            {
                endFling();
                mTouchMode = 3;
                reportScrollStateChange(1);
            }
        }

        final FlingRunnable this$1;

            
            {
                this$1 = FlingRunnable.this;
                super();
            }
    }

}
