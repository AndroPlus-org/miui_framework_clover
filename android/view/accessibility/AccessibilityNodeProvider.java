// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.accessibility;

import android.os.Bundle;
import java.util.List;

// Referenced classes of package android.view.accessibility:
//            AccessibilityNodeInfo

public abstract class AccessibilityNodeProvider
{

    public AccessibilityNodeProvider()
    {
    }

    public void addExtraDataToAccessibilityNodeInfo(int i, AccessibilityNodeInfo accessibilitynodeinfo, String s, Bundle bundle)
    {
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo(int i)
    {
        return null;
    }

    public List findAccessibilityNodeInfosByText(String s, int i)
    {
        return null;
    }

    public AccessibilityNodeInfo findFocus(int i)
    {
        return null;
    }

    public boolean performAction(int i, int j, Bundle bundle)
    {
        return false;
    }

    public static final int HOST_VIEW_ID = -1;
}
