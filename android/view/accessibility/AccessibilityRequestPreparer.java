// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.accessibility;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import java.lang.ref.WeakReference;

// Referenced classes of package android.view.accessibility:
//            AccessibilityManager

public abstract class AccessibilityRequestPreparer
{
    private class ViewAttachStateListener
        implements android.view.View.OnAttachStateChangeListener
    {

        public void onViewAttachedToWindow(View view)
        {
        }

        public void onViewDetachedFromWindow(View view)
        {
            Context context = view.getContext();
            if(context != null)
                ((AccessibilityManager)context.getSystemService(android/view/accessibility/AccessibilityManager)).removeAccessibilityRequestPreparer(AccessibilityRequestPreparer.this);
            view.removeOnAttachStateChangeListener(this);
        }

        final AccessibilityRequestPreparer this$0;

        private ViewAttachStateListener()
        {
            this$0 = AccessibilityRequestPreparer.this;
            super();
        }

        ViewAttachStateListener(ViewAttachStateListener viewattachstatelistener)
        {
            this();
        }
    }


    public AccessibilityRequestPreparer(View view, int i)
    {
        if(!view.isAttachedToWindow())
        {
            throw new IllegalStateException("View must be attached to a window");
        } else
        {
            mViewRef = new WeakReference(view);
            mRequestTypes = i;
            view.addOnAttachStateChangeListener(new ViewAttachStateListener(null));
            return;
        }
    }

    public View getView()
    {
        return (View)mViewRef.get();
    }

    public abstract void onPrepareExtraData(int i, String s, Bundle bundle, Message message);

    public static final int REQUEST_TYPE_EXTRA_DATA = 1;
    private final int mRequestTypes;
    private final WeakReference mViewRef;
}
