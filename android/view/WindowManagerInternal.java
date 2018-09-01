// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.Rect;
import android.graphics.Region;
import android.os.IBinder;
import android.view.animation.Animation;
import java.util.List;

// Referenced classes of package android.view:
//            MagnificationSpec, IInputFilter

public abstract class WindowManagerInternal
{
    public static abstract class AppTransitionListener
    {

        public void onAppTransitionCancelledLocked(int i)
        {
        }

        public void onAppTransitionFinishedLocked(IBinder ibinder)
        {
        }

        public void onAppTransitionPendingLocked()
        {
        }

        public int onAppTransitionStartingLocked(int i, IBinder ibinder, IBinder ibinder1, Animation animation, Animation animation1)
        {
            return 0;
        }

        public AppTransitionListener()
        {
        }
    }

    public static interface MagnificationCallbacks
    {

        public abstract void onMagnificationRegionChanged(Region region);

        public abstract void onRectangleOnScreenRequested(int i, int j, int k, int l);

        public abstract void onRotationChanged(int i);

        public abstract void onUserContextChanged();
    }

    public static interface OnHardKeyboardStatusChangeListener
    {

        public abstract void onHardKeyboardStatusChange(boolean flag);
    }

    public static interface WindowsForAccessibilityCallback
    {

        public abstract void onWindowsForAccessibilityChanged(List list);
    }


    public WindowManagerInternal()
    {
    }

    public abstract void addWindowToken(IBinder ibinder, int i, int j);

    public abstract void clearLastInputMethodWindowForTransition();

    public abstract void computeWindowsForAccessibility();

    public abstract MagnificationSpec getCompatibleMagnificationSpecForWindow(IBinder ibinder);

    public abstract IBinder getFocusedWindowToken();

    public abstract WindowManagerPolicy.WindowState getInputMethodTargetWindow();

    public abstract int getInputMethodWindowVisibleHeight();

    public abstract void getMagnificationRegion(Region region);

    public abstract void getWindowFrame(IBinder ibinder, Rect rect);

    public abstract boolean isDockedDividerResizing();

    public abstract boolean isHardKeyboardAvailable();

    public abstract boolean isKeyguardLocked();

    public abstract boolean isKeyguardShowingAndNotOccluded();

    public abstract boolean isStackVisible(int i);

    public abstract void registerAppTransitionListener(AppTransitionListener apptransitionlistener);

    public abstract void removeWindowToken(IBinder ibinder, boolean flag, int i);

    public abstract void requestTraversalFromDisplayManager();

    public abstract void saveLastInputMethodWindowForTransition();

    public abstract void setForceShowMagnifiableBounds(boolean flag);

    public abstract void setInputFilter(IInputFilter iinputfilter);

    public abstract void setMagnificationCallbacks(MagnificationCallbacks magnificationcallbacks);

    public abstract void setMagnificationSpec(MagnificationSpec magnificationspec);

    public abstract void setOnHardKeyboardStatusChangeListener(OnHardKeyboardStatusChangeListener onhardkeyboardstatuschangelistener);

    public abstract void setVr2dDisplayId(int i);

    public abstract void setWindowsForAccessibilityCallback(WindowsForAccessibilityCallback windowsforaccessibilitycallback);

    public abstract void showGlobalActions();

    public abstract void updateInputMethodWindowStatus(IBinder ibinder, boolean flag, boolean flag1, IBinder ibinder1);

    public abstract void waitForAllWindowsDrawn(Runnable runnable, long l);
}
