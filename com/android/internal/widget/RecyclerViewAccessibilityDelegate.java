// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package com.android.internal.widget:
//            RecyclerView

public class RecyclerViewAccessibilityDelegate extends android.view.View.AccessibilityDelegate
{

    public RecyclerViewAccessibilityDelegate(RecyclerView recyclerview)
    {
        mRecyclerView = recyclerview;
    }

    public android.view.View.AccessibilityDelegate getItemDelegate()
    {
        return mItemDelegate;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(view, accessibilityevent);
        accessibilityevent.setClassName(com/android/internal/widget/RecyclerView.getName());
        if((view instanceof RecyclerView) && shouldIgnore() ^ true)
        {
            view = (RecyclerView)view;
            if(view.getLayoutManager() != null)
                view.getLayoutManager().onInitializeAccessibilityEvent(accessibilityevent);
        }
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfo);
        accessibilitynodeinfo.setClassName(com/android/internal/widget/RecyclerView.getName());
        if(!shouldIgnore() && mRecyclerView.getLayoutManager() != null)
            mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle)
    {
        if(super.performAccessibilityAction(view, i, bundle))
            return true;
        if(!shouldIgnore() && mRecyclerView.getLayoutManager() != null)
            return mRecyclerView.getLayoutManager().performAccessibilityAction(i, bundle);
        else
            return false;
    }

    boolean shouldIgnore()
    {
        return mRecyclerView.hasPendingAdapterUpdates();
    }

    final android.view.View.AccessibilityDelegate mItemDelegate = new android.view.View.AccessibilityDelegate() {

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilitynodeinfo)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfo);
            if(!shouldIgnore() && mRecyclerView.getLayoutManager() != null)
                mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfoForItem(view, accessibilitynodeinfo);
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle)
        {
            if(super.performAccessibilityAction(view, i, bundle))
                return true;
            if(!shouldIgnore() && mRecyclerView.getLayoutManager() != null)
                return mRecyclerView.getLayoutManager().performAccessibilityActionForItem(view, i, bundle);
            else
                return false;
        }

        final RecyclerViewAccessibilityDelegate this$0;

            
            {
                this$0 = RecyclerViewAccessibilityDelegate.this;
                super();
            }
    }
;
    final RecyclerView mRecyclerView;
}
