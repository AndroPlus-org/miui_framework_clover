// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.*;
import android.os.*;
import java.util.List;

// Referenced classes of package android.content.pm:
//            ParceledListSlice, IShortcutService, ShortcutInfo

public class ShortcutManager
{

    public ShortcutManager(Context context)
    {
        this(context, IShortcutService.Stub.asInterface(ServiceManager.getService("shortcut")));
    }

    public ShortcutManager(Context context, IShortcutService ishortcutservice)
    {
        mContext = context;
        mService = ishortcutservice;
    }

    public boolean addDynamicShortcuts(List list)
    {
        boolean flag;
        try
        {
            IShortcutService ishortcutservice = mService;
            String s = mContext.getPackageName();
            ParceledListSlice parceledlistslice = JVM INSTR new #51  <Class ParceledListSlice>;
            parceledlistslice.ParceledListSlice(list);
            flag = ishortcutservice.addDynamicShortcuts(s, parceledlistslice, injectMyUserId());
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            throw list.rethrowFromSystemServer();
        }
        return flag;
    }

    public Intent createShortcutResultIntent(ShortcutInfo shortcutinfo)
    {
        try
        {
            shortcutinfo = mService.createShortcutResultIntent(mContext.getPackageName(), shortcutinfo, injectMyUserId());
        }
        // Misplaced declaration of an exception variable
        catch(ShortcutInfo shortcutinfo)
        {
            throw shortcutinfo.rethrowFromSystemServer();
        }
        return shortcutinfo;
    }

    public void disableShortcuts(List list)
    {
        try
        {
            mService.disableShortcuts(mContext.getPackageName(), list, null, 0, injectMyUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            throw list.rethrowFromSystemServer();
        }
    }

    public void disableShortcuts(List list, int i)
    {
        try
        {
            mService.disableShortcuts(mContext.getPackageName(), list, null, i, injectMyUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            throw list.rethrowFromSystemServer();
        }
    }

    public void disableShortcuts(List list, CharSequence charsequence)
    {
        try
        {
            mService.disableShortcuts(mContext.getPackageName(), list, charsequence, 0, injectMyUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            throw list.rethrowFromSystemServer();
        }
    }

    public void disableShortcuts(List list, String s)
    {
        disableShortcuts(list, ((CharSequence) (s)));
    }

    public void enableShortcuts(List list)
    {
        try
        {
            mService.enableShortcuts(mContext.getPackageName(), list, injectMyUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            throw list.rethrowFromSystemServer();
        }
    }

    public List getDynamicShortcuts()
    {
        List list;
        try
        {
            list = mService.getDynamicShortcuts(mContext.getPackageName(), injectMyUserId()).getList();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public int getIconMaxHeight()
    {
        int i;
        try
        {
            i = mService.getIconMaxDimensions(mContext.getPackageName(), injectMyUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int getIconMaxWidth()
    {
        int i;
        try
        {
            i = mService.getIconMaxDimensions(mContext.getPackageName(), injectMyUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public List getManifestShortcuts()
    {
        List list;
        try
        {
            list = mService.getManifestShortcuts(mContext.getPackageName(), injectMyUserId()).getList();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public int getMaxShortcutCountForActivity()
    {
        return getMaxShortcutCountPerActivity();
    }

    public int getMaxShortcutCountPerActivity()
    {
        int i;
        try
        {
            i = mService.getMaxShortcutCountPerActivity(mContext.getPackageName(), injectMyUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public List getPinnedShortcuts()
    {
        List list;
        try
        {
            list = mService.getPinnedShortcuts(mContext.getPackageName(), injectMyUserId()).getList();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public long getRateLimitResetTime()
    {
        long l;
        try
        {
            l = mService.getRateLimitResetTime(mContext.getPackageName(), injectMyUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return l;
    }

    public int getRemainingCallCount()
    {
        int i;
        try
        {
            i = mService.getRemainingCallCount(mContext.getPackageName(), injectMyUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    protected int injectMyUserId()
    {
        return UserHandle.myUserId();
    }

    public boolean isRateLimitingActive()
    {
        boolean flag = false;
        int i;
        try
        {
            i = mService.getRemainingCallCount(mContext.getPackageName(), injectMyUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(i == 0)
            flag = true;
        return flag;
    }

    public boolean isRequestPinShortcutSupported()
    {
        boolean flag;
        try
        {
            flag = mService.isRequestPinItemSupported(injectMyUserId(), 1);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void onApplicationActive(String s, int i)
    {
        try
        {
            mService.onApplicationActive(s, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void removeAllDynamicShortcuts()
    {
        try
        {
            mService.removeAllDynamicShortcuts(mContext.getPackageName(), injectMyUserId());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void removeDynamicShortcuts(List list)
    {
        try
        {
            mService.removeDynamicShortcuts(mContext.getPackageName(), list, injectMyUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            throw list.rethrowFromSystemServer();
        }
    }

    public void reportShortcutUsed(String s)
    {
        try
        {
            mService.reportShortcutUsed(mContext.getPackageName(), s, injectMyUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public boolean requestPinShortcut(ShortcutInfo shortcutinfo, IntentSender intentsender)
    {
        boolean flag;
        try
        {
            flag = mService.requestPinShortcut(mContext.getPackageName(), shortcutinfo, intentsender, injectMyUserId());
        }
        // Misplaced declaration of an exception variable
        catch(ShortcutInfo shortcutinfo)
        {
            throw shortcutinfo.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean setDynamicShortcuts(List list)
    {
        boolean flag;
        try
        {
            IShortcutService ishortcutservice = mService;
            String s = mContext.getPackageName();
            ParceledListSlice parceledlistslice = JVM INSTR new #51  <Class ParceledListSlice>;
            parceledlistslice.ParceledListSlice(list);
            flag = ishortcutservice.setDynamicShortcuts(s, parceledlistslice, injectMyUserId());
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            throw list.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean updateShortcuts(List list)
    {
        boolean flag;
        try
        {
            IShortcutService ishortcutservice = mService;
            String s = mContext.getPackageName();
            ParceledListSlice parceledlistslice = JVM INSTR new #51  <Class ParceledListSlice>;
            parceledlistslice.ParceledListSlice(list);
            flag = ishortcutservice.updateShortcuts(s, parceledlistslice, injectMyUserId());
        }
        // Misplaced declaration of an exception variable
        catch(List list)
        {
            throw list.rethrowFromSystemServer();
        }
        return flag;
    }

    private static final String TAG = "ShortcutManager";
    private final Context mContext;
    private final IShortcutService mService;
}
