// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.appwidget.AppWidgetProviderInfo;
import android.content.*;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import java.util.List;

public abstract class ShortcutServiceInternal
{
    public static interface ShortcutChangeListener
    {

        public abstract void onShortcutChanged(String s, int i);
    }


    public ShortcutServiceInternal()
    {
    }

    public abstract void addListener(ShortcutChangeListener shortcutchangelistener);

    public abstract Intent[] createShortcutIntents(int i, String s, String s1, String s2, int j);

    public abstract ParcelFileDescriptor getShortcutIconFd(int i, String s, String s1, String s2, int j);

    public abstract int getShortcutIconResId(int i, String s, String s1, String s2, int j);

    public abstract List getShortcuts(int i, String s, long l, String s1, List list, ComponentName componentname, 
            int j, int k);

    public abstract boolean hasShortcutHostPermission(int i, String s);

    public abstract boolean isPinnedByCaller(int i, String s, String s1, String s2, int j);

    public abstract boolean isRequestPinItemSupported(int i, int j);

    public abstract void pinShortcuts(int i, String s, String s1, List list, int j);

    public abstract boolean requestPinAppWidget(String s, AppWidgetProviderInfo appwidgetproviderinfo, Bundle bundle, IntentSender intentsender, int i);
}
