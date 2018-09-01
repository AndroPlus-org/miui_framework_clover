// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.IntArray;
import android.view.*;
import android.view.accessibility.*;
import java.util.List;

public abstract class ExploreByTouchHelper extends android.view.View.AccessibilityDelegate
{
    private class ExploreByTouchNodeProvider extends AccessibilityNodeProvider
    {

        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i)
        {
            return ExploreByTouchHelper._2D_wrap0(ExploreByTouchHelper.this, i);
        }

        public boolean performAction(int i, int j, Bundle bundle)
        {
            return ExploreByTouchHelper._2D_wrap1(ExploreByTouchHelper.this, i, j, bundle);
        }

        final ExploreByTouchHelper this$0;

        private ExploreByTouchNodeProvider()
        {
            this$0 = ExploreByTouchHelper.this;
            super();
        }

        ExploreByTouchNodeProvider(ExploreByTouchNodeProvider explorebytouchnodeprovider)
        {
            this();
        }
    }


    static AccessibilityNodeInfo _2D_wrap0(ExploreByTouchHelper explorebytouchhelper, int i)
    {
        return explorebytouchhelper.createNode(i);
    }

    static boolean _2D_wrap1(ExploreByTouchHelper explorebytouchhelper, int i, int j, Bundle bundle)
    {
        return explorebytouchhelper.performAction(i, j, bundle);
    }

    public ExploreByTouchHelper(View view)
    {
        mFocusedVirtualViewId = 0x80000000;
        mHoveredVirtualViewId = 0x80000000;
        if(view == null)
        {
            throw new IllegalArgumentException("View may not be null");
        } else
        {
            mView = view;
            mContext = view.getContext();
            mManager = (AccessibilityManager)mContext.getSystemService("accessibility");
            return;
        }
    }

    private boolean clearAccessibilityFocus(int i)
    {
        if(isAccessibilityFocused(i))
        {
            mFocusedVirtualViewId = 0x80000000;
            mView.invalidate();
            sendEventForVirtualView(i, 0x10000);
            return true;
        } else
        {
            return false;
        }
    }

    private AccessibilityEvent createEvent(int i, int j)
    {
        switch(i)
        {
        default:
            return createEventForChild(i, j);

        case -1: 
            return createEventForHost(j);
        }
    }

    private AccessibilityEvent createEventForChild(int i, int j)
    {
        AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(j);
        accessibilityevent.setEnabled(true);
        accessibilityevent.setClassName(DEFAULT_CLASS_NAME);
        onPopulateEventForVirtualView(i, accessibilityevent);
        if(accessibilityevent.getText().isEmpty() && accessibilityevent.getContentDescription() == null)
        {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        } else
        {
            accessibilityevent.setPackageName(mView.getContext().getPackageName());
            accessibilityevent.setSource(mView, i);
            return accessibilityevent;
        }
    }

    private AccessibilityEvent createEventForHost(int i)
    {
        AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(i);
        mView.onInitializeAccessibilityEvent(accessibilityevent);
        onPopulateEventForHost(accessibilityevent);
        return accessibilityevent;
    }

    private AccessibilityNodeInfo createNode(int i)
    {
        switch(i)
        {
        default:
            return createNodeForChild(i);

        case -1: 
            return createNodeForHost();
        }
    }

    private AccessibilityNodeInfo createNodeForChild(int i)
    {
        ensureTempRects();
        Rect rect = mTempParentRect;
        int ai[] = mTempGlobalRect;
        Rect rect1 = mTempScreenRect;
        AccessibilityNodeInfo accessibilitynodeinfo = AccessibilityNodeInfo.obtain();
        accessibilitynodeinfo.setEnabled(true);
        accessibilitynodeinfo.setClassName(DEFAULT_CLASS_NAME);
        accessibilitynodeinfo.setBoundsInParent(INVALID_PARENT_BOUNDS);
        onPopulateNodeForVirtualView(i, accessibilitynodeinfo);
        if(accessibilitynodeinfo.getText() == null && accessibilitynodeinfo.getContentDescription() == null)
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        accessibilitynodeinfo.getBoundsInParent(rect);
        if(rect.equals(INVALID_PARENT_BOUNDS))
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        int j = accessibilitynodeinfo.getActions();
        if((j & 0x40) != 0)
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        if((j & 0x80) != 0)
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        accessibilitynodeinfo.setPackageName(mView.getContext().getPackageName());
        accessibilitynodeinfo.setSource(mView, i);
        accessibilitynodeinfo.setParent(mView);
        if(mFocusedVirtualViewId == i)
        {
            accessibilitynodeinfo.setAccessibilityFocused(true);
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        } else
        {
            accessibilitynodeinfo.setAccessibilityFocused(false);
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_ACCESSIBILITY_FOCUS);
        }
        if(intersectVisibleToUser(rect))
        {
            accessibilitynodeinfo.setVisibleToUser(true);
            accessibilitynodeinfo.setBoundsInParent(rect);
        }
        mView.getLocationOnScreen(ai);
        i = ai[0];
        j = ai[1];
        rect1.set(rect);
        rect1.offset(i, j);
        accessibilitynodeinfo.setBoundsInScreen(rect1);
        return accessibilitynodeinfo;
    }

    private AccessibilityNodeInfo createNodeForHost()
    {
        AccessibilityNodeInfo accessibilitynodeinfo = AccessibilityNodeInfo.obtain(mView);
        mView.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
        int i = accessibilitynodeinfo.getChildCount();
        onPopulateNodeForHost(accessibilitynodeinfo);
        IntArray intarray;
        if(mTempArray == null)
            mTempArray = new IntArray();
        else
            mTempArray.clear();
        intarray = mTempArray;
        getVisibleVirtualViews(intarray);
        if(i > 0 && intarray.size() > 0)
            throw new RuntimeException("Views cannot have both real and virtual children");
        int k = intarray.size();
        for(int j = 0; j < k; j++)
            accessibilitynodeinfo.addChild(mView, intarray.get(j));

        return accessibilitynodeinfo;
    }

    private void ensureTempRects()
    {
        mTempGlobalRect = new int[2];
        mTempParentRect = new Rect();
        mTempScreenRect = new Rect();
    }

    private boolean intersectVisibleToUser(Rect rect)
    {
        if(rect == null || rect.isEmpty())
            return false;
        if(mView.getWindowVisibility() != 0)
            return false;
        Object obj;
        for(obj = mView.getParent(); obj instanceof View; obj = ((View) (obj)).getParent())
        {
            obj = (View)obj;
            if(((View) (obj)).getAlpha() <= 0.0F || ((View) (obj)).getVisibility() != 0)
                return false;
        }

        if(obj == null)
            return false;
        if(mTempVisibleRect == null)
            mTempVisibleRect = new Rect();
        obj = mTempVisibleRect;
        if(!mView.getLocalVisibleRect(((Rect) (obj))))
            return false;
        else
            return rect.intersect(((Rect) (obj)));
    }

    private boolean isAccessibilityFocused(int i)
    {
        boolean flag;
        if(mFocusedVirtualViewId == i)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean manageFocusForChild(int i, int j)
    {
        switch(j)
        {
        default:
            return false;

        case 64: // '@'
            return requestAccessibilityFocus(i);

        case 128: 
            return clearAccessibilityFocus(i);
        }
    }

    private boolean performAction(int i, int j, Bundle bundle)
    {
        switch(i)
        {
        default:
            return performActionForChild(i, j, bundle);

        case -1: 
            return performActionForHost(j, bundle);
        }
    }

    private boolean performActionForChild(int i, int j, Bundle bundle)
    {
        switch(j)
        {
        default:
            return onPerformActionForVirtualView(i, j, bundle);

        case 64: // '@'
        case 128: 
            return manageFocusForChild(i, j);
        }
    }

    private boolean performActionForHost(int i, Bundle bundle)
    {
        return mView.performAccessibilityAction(i, bundle);
    }

    private boolean requestAccessibilityFocus(int i)
    {
        AccessibilityManager accessibilitymanager = (AccessibilityManager)mContext.getSystemService("accessibility");
        if(!mManager.isEnabled() || accessibilitymanager.isTouchExplorationEnabled() ^ true)
            return false;
        if(!isAccessibilityFocused(i))
        {
            if(mFocusedVirtualViewId != 0x80000000)
                sendEventForVirtualView(mFocusedVirtualViewId, 0x10000);
            mFocusedVirtualViewId = i;
            mView.invalidate();
            sendEventForVirtualView(i, 32768);
            return true;
        } else
        {
            return false;
        }
    }

    private void updateHoveredVirtualView(int i)
    {
        if(mHoveredVirtualViewId == i)
        {
            return;
        } else
        {
            int j = mHoveredVirtualViewId;
            mHoveredVirtualViewId = i;
            sendEventForVirtualView(i, 128);
            sendEventForVirtualView(j, 256);
            return;
        }
    }

    public boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        boolean flag = true;
        if(!mManager.isEnabled() || mManager.isTouchExplorationEnabled() ^ true)
            return false;
        switch(motionevent.getAction())
        {
        case 8: // '\b'
        default:
            return false;

        case 7: // '\007'
        case 9: // '\t'
            int i = getVirtualViewAt(motionevent.getX(), motionevent.getY());
            updateHoveredVirtualView(i);
            if(i == 0x80000000)
                flag = false;
            return flag;

        case 10: // '\n'
            break;
        }
        if(mFocusedVirtualViewId != 0x80000000)
        {
            updateHoveredVirtualView(0x80000000);
            return true;
        } else
        {
            return false;
        }
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider(View view)
    {
        if(mNodeProvider == null)
            mNodeProvider = new ExploreByTouchNodeProvider(null);
        return mNodeProvider;
    }

    public int getFocusedVirtualView()
    {
        return mFocusedVirtualViewId;
    }

    protected abstract int getVirtualViewAt(float f, float f1);

    protected abstract void getVisibleVirtualViews(IntArray intarray);

    public void invalidateRoot()
    {
        invalidateVirtualView(-1, 1);
    }

    public void invalidateVirtualView(int i)
    {
        invalidateVirtualView(i, 0);
    }

    public void invalidateVirtualView(int i, int j)
    {
        if(i != 0x80000000 && mManager.isEnabled())
        {
            ViewParent viewparent = mView.getParent();
            if(viewparent != null)
            {
                AccessibilityEvent accessibilityevent = createEvent(i, 2048);
                accessibilityevent.setContentChangeTypes(j);
                viewparent.requestSendAccessibilityEvent(mView, accessibilityevent);
            }
        }
    }

    protected abstract boolean onPerformActionForVirtualView(int i, int j, Bundle bundle);

    protected void onPopulateEventForHost(AccessibilityEvent accessibilityevent)
    {
    }

    protected abstract void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityevent);

    protected void onPopulateNodeForHost(AccessibilityNodeInfo accessibilitynodeinfo)
    {
    }

    protected abstract void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfo accessibilitynodeinfo);

    public boolean sendEventForVirtualView(int i, int j)
    {
        if(i == 0x80000000 || mManager.isEnabled() ^ true)
            return false;
        ViewParent viewparent = mView.getParent();
        if(viewparent == null)
        {
            return false;
        } else
        {
            AccessibilityEvent accessibilityevent = createEvent(i, j);
            return viewparent.requestSendAccessibilityEvent(mView, accessibilityevent);
        }
    }

    private static final String DEFAULT_CLASS_NAME = android/view/View.getName();
    public static final int HOST_ID = -1;
    public static final int INVALID_ID = 0x80000000;
    private static final Rect INVALID_PARENT_BOUNDS = new Rect(0x7fffffff, 0x7fffffff, 0x80000000, 0x80000000);
    private final Context mContext;
    private int mFocusedVirtualViewId;
    private int mHoveredVirtualViewId;
    private final AccessibilityManager mManager;
    private ExploreByTouchNodeProvider mNodeProvider;
    private IntArray mTempArray;
    private int mTempGlobalRect[];
    private Rect mTempParentRect;
    private Rect mTempScreenRect;
    private Rect mTempVisibleRect;
    private final View mView;

}
