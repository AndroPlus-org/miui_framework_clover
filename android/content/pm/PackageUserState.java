// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.util.ArraySet;
import com.android.internal.util.ArrayUtils;
import java.util.Arrays;

// Referenced classes of package android.content.pm:
//            ComponentInfo, ApplicationInfo

public class PackageUserState
{

    public PackageUserState()
    {
        categoryHint = -1;
        installed = true;
        hidden = false;
        suspended = false;
        enabled = 0;
        domainVerificationStatus = 0;
        installReason = 0;
    }

    public PackageUserState(PackageUserState packageuserstate)
    {
        Object obj = null;
        super();
        categoryHint = -1;
        ceDataInode = packageuserstate.ceDataInode;
        installed = packageuserstate.installed;
        stopped = packageuserstate.stopped;
        notLaunched = packageuserstate.notLaunched;
        hidden = packageuserstate.hidden;
        suspended = packageuserstate.suspended;
        instantApp = packageuserstate.instantApp;
        virtualPreload = packageuserstate.virtualPreload;
        enabled = packageuserstate.enabled;
        lastDisableAppCaller = packageuserstate.lastDisableAppCaller;
        domainVerificationStatus = packageuserstate.domainVerificationStatus;
        appLinkGeneration = packageuserstate.appLinkGeneration;
        categoryHint = packageuserstate.categoryHint;
        installReason = packageuserstate.installReason;
        disabledComponents = ArrayUtils.cloneOrNull(packageuserstate.disabledComponents);
        enabledComponents = ArrayUtils.cloneOrNull(packageuserstate.enabledComponents);
        if(packageuserstate.overlayPaths == null)
            packageuserstate = obj;
        else
            packageuserstate = (String[])Arrays.copyOf(packageuserstate.overlayPaths, packageuserstate.overlayPaths.length);
        overlayPaths = packageuserstate;
    }

    public final boolean equals(Object obj)
    {
        if(!(obj instanceof PackageUserState))
            return false;
        obj = (PackageUserState)obj;
        if(ceDataInode != ((PackageUserState) (obj)).ceDataInode)
            return false;
        if(installed != ((PackageUserState) (obj)).installed)
            return false;
        if(stopped != ((PackageUserState) (obj)).stopped)
            return false;
        if(notLaunched != ((PackageUserState) (obj)).notLaunched)
            return false;
        if(hidden != ((PackageUserState) (obj)).hidden)
            return false;
        if(suspended != ((PackageUserState) (obj)).suspended)
            return false;
        if(instantApp != ((PackageUserState) (obj)).instantApp)
            return false;
        if(virtualPreload != ((PackageUserState) (obj)).virtualPreload)
            return false;
        if(enabled != ((PackageUserState) (obj)).enabled)
            return false;
        while(lastDisableAppCaller == null && ((PackageUserState) (obj)).lastDisableAppCaller != null || lastDisableAppCaller != null && lastDisableAppCaller.equals(((PackageUserState) (obj)).lastDisableAppCaller) ^ true) 
            return false;
        if(domainVerificationStatus != ((PackageUserState) (obj)).domainVerificationStatus)
            return false;
        if(appLinkGeneration != ((PackageUserState) (obj)).appLinkGeneration)
            return false;
        if(categoryHint != ((PackageUserState) (obj)).categoryHint)
            return false;
        if(installReason != ((PackageUserState) (obj)).installReason)
            return false;
        while(disabledComponents == null && ((PackageUserState) (obj)).disabledComponents != null || disabledComponents != null && ((PackageUserState) (obj)).disabledComponents == null) 
            return false;
        if(disabledComponents != null)
        {
            if(disabledComponents.size() != ((PackageUserState) (obj)).disabledComponents.size())
                return false;
            for(int i = disabledComponents.size() - 1; i >= 0; i--)
                if(!((PackageUserState) (obj)).disabledComponents.contains(disabledComponents.valueAt(i)))
                    return false;

        }
        while(enabledComponents == null && ((PackageUserState) (obj)).enabledComponents != null || enabledComponents != null && ((PackageUserState) (obj)).enabledComponents == null) 
            return false;
        if(enabledComponents != null)
        {
            if(enabledComponents.size() != ((PackageUserState) (obj)).enabledComponents.size())
                return false;
            for(int j = enabledComponents.size() - 1; j >= 0; j--)
                if(!((PackageUserState) (obj)).enabledComponents.contains(enabledComponents.valueAt(j)))
                    return false;

        }
        return true;
    }

    public boolean isAvailable(int i)
    {
        boolean flag;
        boolean flag1;
        if((0x400000 & i) != 0)
            flag = true;
        else
            flag = false;
        if((i & 0x2000) != 0)
            flag1 = true;
        else
            flag1 = false;
        if(!flag)
        {
            if(installed)
            {
                if(!hidden)
                    flag1 = true;
            } else
            {
                flag1 = false;
            }
        } else
        {
            flag1 = true;
        }
        return flag1;
    }

    public boolean isEnabled(ComponentInfo componentinfo, int i)
    {
        if((i & 0x200) != 0)
            return true;
        enabled;
        JVM INSTR tableswitch 0 4: default 48
    //                   0 75
    //                   1 48
    //                   2 64
    //                   3 64
    //                   4 66;
           goto _L1 _L2 _L1 _L3 _L3 _L4
_L1:
        if(ArrayUtils.contains(enabledComponents, componentinfo.name))
            return true;
        break; /* Loop/switch isn't completed */
_L3:
        return false;
_L4:
        if((0x8000 & i) == 0)
            return false;
_L2:
        if(componentinfo.applicationInfo.enabled) goto _L1; else goto _L5
_L5:
        return false;
        if(ArrayUtils.contains(disabledComponents, componentinfo.name))
            return false;
        else
            return componentinfo.enabled;
    }

    public boolean isMatch(ComponentInfo componentinfo, int i)
    {
        boolean flag = componentinfo.applicationInfo.isSystemApp();
        boolean flag1;
        if((0x402000 & i) != 0)
            flag1 = true;
        else
            flag1 = false;
        if(!isAvailable(i))
        {
            if(!flag)
                flag1 = false;
            if(flag1 ^ true)
                return false;
        }
        if(!isEnabled(componentinfo, i))
            return false;
        if((0x100000 & i) != 0 && !flag)
            return false;
        if((0x40000 & i) != 0)
            flag1 = componentinfo.directBootAware ^ true;
        else
            flag1 = false;
        if((0x80000 & i) != 0)
            flag = componentinfo.directBootAware;
        else
            flag = false;
        if(flag1)
            flag = true;
        return flag;
    }

    public int appLinkGeneration;
    public int categoryHint;
    public long ceDataInode;
    public ArraySet disabledComponents;
    public int domainVerificationStatus;
    public int enabled;
    public ArraySet enabledComponents;
    public boolean hidden;
    public int installReason;
    public boolean installed;
    public boolean instantApp;
    public String lastDisableAppCaller;
    public boolean notLaunched;
    public String overlayPaths[];
    public boolean stopped;
    public boolean suspended;
    public boolean virtualPreload;
}
