// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;

// Referenced classes of package android.view:
//            View, ContextMenu, ActionMode

public interface ViewParent
{

    public abstract void bringChildToFront(View view);

    public abstract boolean canResolveLayoutDirection();

    public abstract boolean canResolveTextAlignment();

    public abstract boolean canResolveTextDirection();

    public abstract void childDrawableStateChanged(View view);

    public abstract void childHasTransientStateChanged(View view, boolean flag);

    public abstract void clearChildFocus(View view);

    public abstract void createContextMenu(ContextMenu contextmenu);

    public abstract View focusSearch(View view, int i);

    public abstract void focusableViewAvailable(View view);

    public abstract boolean getChildVisibleRect(View view, Rect rect, Point point);

    public abstract int getLayoutDirection();

    public abstract ViewParent getParent();

    public abstract ViewParent getParentForAccessibility();

    public abstract int getTextAlignment();

    public abstract int getTextDirection();

    public abstract void invalidateChild(View view, Rect rect);

    public abstract ViewParent invalidateChildInParent(int ai[], Rect rect);

    public abstract boolean isLayoutDirectionResolved();

    public abstract boolean isLayoutRequested();

    public abstract boolean isTextAlignmentResolved();

    public abstract boolean isTextDirectionResolved();

    public abstract View keyboardNavigationClusterSearch(View view, int i);

    public abstract void notifySubtreeAccessibilityStateChanged(View view, View view1, int i);

    public void onDescendantInvalidated(View view, View view1)
    {
        if(getParent() != null)
            getParent().onDescendantInvalidated(view, view1);
    }

    public abstract boolean onNestedFling(View view, float f, float f1, boolean flag);

    public abstract boolean onNestedPreFling(View view, float f, float f1);

    public abstract boolean onNestedPrePerformAccessibilityAction(View view, int i, Bundle bundle);

    public abstract void onNestedPreScroll(View view, int i, int j, int ai[]);

    public abstract void onNestedScroll(View view, int i, int j, int k, int l);

    public abstract void onNestedScrollAccepted(View view, View view1, int i);

    public abstract boolean onStartNestedScroll(View view, View view1, int i);

    public abstract void onStopNestedScroll(View view);

    public abstract void recomputeViewAttributes(View view);

    public abstract void requestChildFocus(View view, View view1);

    public abstract boolean requestChildRectangleOnScreen(View view, Rect rect, boolean flag);

    public abstract void requestDisallowInterceptTouchEvent(boolean flag);

    public abstract void requestFitSystemWindows();

    public abstract void requestLayout();

    public abstract boolean requestSendAccessibilityEvent(View view, AccessibilityEvent accessibilityevent);

    public abstract void requestTransparentRegion(View view);

    public abstract boolean showContextMenuForChild(View view);

    public abstract boolean showContextMenuForChild(View view, float f, float f1);

    public abstract ActionMode startActionModeForChild(View view, ActionMode.Callback callback);

    public abstract ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i);
}
