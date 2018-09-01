// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.accessibility;


// Referenced classes of package android.view.accessibility:
//            AccessibilityEvent

public interface AccessibilityEventSource
{

    public abstract void sendAccessibilityEvent(int i);

    public abstract void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityevent);
}
