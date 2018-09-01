// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.component;

import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.util.Slog;
import com.miui.whetstone.WhetstoneSleepModeController;
import com.miui.whetstone.WhetstoneWakeUpManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package com.miui.whetstone.component:
//            ComponentPolicyInfo, PackageComponentPolicy, ComponentHelper

public class WtComponentManager
{

    private WtComponentManager(Context context)
    {
        mDefaultComponentPoliy = new CopyOnWriteArrayList();
        mPackagePolices = new CopyOnWriteArrayList();
        mInputPackages = new CopyOnWriteArrayList();
        mIdIndex = 0;
        mContext = context;
        mSleepModeController = WhetstoneSleepModeController.getInstance(context);
    }

    private boolean checkDefaultComponentPolicy(int i, int j, int k, Intent intent, String s, String s1, String s2)
    {
        boolean flag = true;
        Iterator iterator = mDefaultComponentPoliy.iterator();
        boolean flag1;
label0:
        do
        {
            ComponentPolicyInfo componentpolicyinfo;
            do
            {
                flag1 = flag;
                if(!iterator.hasNext())
                    break label0;
                componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
            } while(componentpolicyinfo == null || componentpolicyinfo.isCommonPolicy() ^ true || componentpolicyinfo.isCommonPolicyIgnore(s1, s2));
            flag1 = componentpolicyinfo.checkPolicyResultWithStartType(s1, i, j, k, intent, s);
            flag = flag1;
        } while(flag1);
        return flag1;
    }

    private boolean checkPackagePolicy(int i, int j, Intent intent, String s, String s1, int k, String s2, 
            int l)
    {
        boolean flag = true;
        l = 0;
        Iterator iterator = mPackagePolices.iterator();
        int i1;
        boolean flag1;
label0:
        do
        {
            do
            {
                i1 = l;
                flag1 = flag;
                if(!iterator.hasNext())
                    break label0;
                s2 = (PackageComponentPolicy)iterator.next();
            } while(!s2.isEquals(s1));
            i1 = 1;
            l = 1;
            flag1 = s2.checkPackagePolicyWithStartType(k, i, s, j, intent);
            flag = flag1;
        } while(flag1);
        if(i1 == 0)
        {
            s1 = new PackageComponentPolicy(s1);
            flag1 = s1.checkPackagePolicyWithStartType(k, i, s, j, intent);
            mPackagePolices.add(s1);
        }
        return flag1;
    }

    public static WtComponentManager getInstance()
    {
        return sWtComponentManager;
    }

    public static boolean isDebug()
    {
        return DEBUG;
    }

    private boolean isInputMethod(String s)
    {
        for(Iterator iterator = mInputPackages.iterator(); iterator.hasNext();)
            if(((String)iterator.next()).equals(s))
                return true;

        return false;
    }

    public static WtComponentManager makeInstance(Context context)
    {
        if(sWtComponentManager != null) goto _L2; else goto _L1
_L1:
        com/miui/whetstone/component/WtComponentManager;
        JVM INSTR monitorenter ;
        if(sWtComponentManager == null)
        {
            WtComponentManager wtcomponentmanager = JVM INSTR new #2   <Class WtComponentManager>;
            wtcomponentmanager.WtComponentManager(context);
            sWtComponentManager = wtcomponentmanager;
        }
        com/miui/whetstone/component/WtComponentManager;
        JVM INSTR monitorexit ;
_L2:
        return sWtComponentManager;
        context;
        throw context;
    }

    private void updatePolicyStatus(String s, int i, boolean flag, int j)
    {
        Iterator iterator = mDefaultComponentPoliy.iterator();
        Iterator iterator1 = mPackagePolices.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ComponentPolicyInfo componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
            if(componentpolicyinfo != null)
            {
                componentpolicyinfo.updatePolicyEnableStatus(s, i, j);
                if(componentpolicyinfo.isRemovedWhenPolicyDisbale() && componentpolicyinfo.getEnableStatus() ^ true)
                {
                    if(DEBUG)
                        Slog.d("WtComponentManager", (new StringBuilder()).append("remove policy pkg:").append(componentpolicyinfo.getPkg()).append(" userId:").append(componentpolicyinfo.getUserId()).append(" Id:").append(componentpolicyinfo.getId()).toString());
                    mDefaultComponentPoliy.remove(componentpolicyinfo);
                }
            }
        } while(true);
        do
        {
            if(!iterator1.hasNext())
                break;
            PackageComponentPolicy packagecomponentpolicy = (PackageComponentPolicy)iterator1.next();
            if(packagecomponentpolicy != null)
            {
                if(DEBUG)
                    Slog.i("WtComponentManager", (new StringBuilder()).append("check policy status: ").append(packagecomponentpolicy.getPkgName()).toString());
                if(flag && packagecomponentpolicy.isEquals(s))
                    packagecomponentpolicy.setPackageStartTimeWithType(j, SystemClock.elapsedRealtime());
                packagecomponentpolicy.updatePolicyEnableState(s, i, j);
            }
        } while(true);
    }

    public boolean checkPackagePolicyState(String s, String s1, int i, int j, String s2, String s3, int k, 
            int l, Bundle bundle)
    {
        int i1;
        boolean flag;
        int j1;
        i1 = 0;
        flag = false;
        j1 = UserHandle.getUserId(k);
        bundle = (Intent)bundle.getParcelable("intent");
        if(DEBUG)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("Process Start: pkg: ").append(s).append(" userId: ").append(j).append(" Process:").append(s3).append(" Caller: ").append(s1).append(" CallerUserId:").append(j1).append(" StartType: ").append(i).append(" Flag: ").append(0).append(" intent: ");
            if(bundle == null)
                s2 = "null";
            else
                s2 = bundle.getAction();
            Slog.d("WtComponentManager", stringbuilder.append(s2).toString());
        }
        if(!mSleepModeController.isInSleep()) goto _L2; else goto _L1
_L1:
        if((i & 1) == 0) goto _L4; else goto _L3
_L3:
        mSleepModeController.exitSleepModeByActivity();
          goto _L2
_L4:
        if(mSleepModeController.isInCharging() || !mSleepModeController.isProcessControlEnable() || !mSleepModeController.isForbidProcessStartBySleepMode(s, s3, i) || WhetstoneWakeUpManager.getInstance(mContext).checkIfAppBeAllowedStartForWakeUpControl(-1, s, null) == 2) goto _L2; else goto _L5
_L5:
        return false;
_L2:
        if(s == null || s.length() == 0 || s1 == null || s1.length() == 0 || j < 0 || j1 < 0 || l < 0)
        {
            Slog.e("WtComponentManager", "error start info, pass");
            return true;
        }
        s2 = mPackagePolices.iterator();
label0:
        do
        {
            PackageComponentPolicy packagecomponentpolicy;
            do
            {
                if(!s2.hasNext())
                    break label0;
                packagecomponentpolicy = (PackageComponentPolicy)s2.next();
                if(!packagecomponentpolicy.isEquals(s))
                    continue;
                if(packagecomponentpolicy.inCheckList(s3, i, true, true))
                {
                    if(DEBUG)
                        Slog.d("WtComponentManager", (new StringBuilder()).append("Process ").append(s3).append(" white list start permission: allow").toString());
                    updatePolicyStatus(s, j, true, i);
                    return true;
                }
                if(packagecomponentpolicy.inCheckList(s3, i, false, true))
                {
                    Slog.d("WtComponentManager", (new StringBuilder()).append("Process ").append(s3).append(" black list start permission: deny ").toString());
                    updatePolicyStatus(s, j, false, i);
                    return false;
                }
                continue label0;
            } while(!packagecomponentpolicy.isEquals(s1));
            if(packagecomponentpolicy.inCheckList(null, i, true, false))
            {
                if(DEBUG)
                    Slog.d("WtComponentManager", (new StringBuilder()).append("Process ").append(s3).append(" white list start permission: allow").toString());
                updatePolicyStatus(s, j, true, i);
                return true;
            }
            if(packagecomponentpolicy.inCheckList(null, i, false, false))
            {
                Slog.d("WtComponentManager", (new StringBuilder()).append("Process ").append(s3).append(" black list start permission: deny ").toString());
                updatePolicyStatus(s, j, false, i);
                return false;
            }
        } while(true);
        if(isInputMethod(s))
            i1 = 0x20000;
        int k1 = i1;
        if("Restart: AMS".equals(s1))
            k1 = i1 | 0x2000;
        i1 = k1;
        boolean flag1 = flag;
        if(ComponentHelper.isCallerHasForegroundActivities(l))
        {
            l = k1;
            if((k1 & 0x2000) == 0)
                l = k1 | 4;
            i1 = l;
            flag1 = flag;
            if(s.equals(s1))
            {
                i1 = l;
                flag1 = flag;
                if(j == j1)
                {
                    i1 = l | 0x14000;
                    flag1 = true;
                }
            }
        }
        l = i1;
        if(!flag1)
        {
            l = i1;
            if(s.equals(s1))
            {
                l = i1;
                if(j == j1)
                    l = i1 | 0x4000;
            }
        }
        i1 = l;
        if(ComponentHelper.isSystemPackage(s, j))
            i1 = l | 0x1000;
        l = i1;
        if(ComponentHelper.isCallerSystemUid(k))
            l = i1 | 2;
        k = l;
        if(ComponentHelper.isSystemPackage(s1, j1))
            k = l | 1;
        l = k;
        if(i == 4)
            l = k | 0x8000;
        boolean flag2;
        if(checkDefaultComponentPolicy(j, i, l, bundle, s3, s, s1))
            flag2 = checkPackagePolicy(i, l, bundle, s3, s, j, s1, j1);
        else
            flag2 = false;
        if(DEBUG)
        {
            s2 = (new StringBuilder()).append("Process ").append(s3).append(" start permission: ");
            if(flag2)
                s1 = "allow";
            else
                s1 = "deny";
            Slog.d("WtComponentManager", s2.append(s1).toString());
        }
        updatePolicyStatus(s, j, flag2, i);
        return flag2;
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.println(" ===== Common Policy ===== ");
        printwriter.println((new StringBuilder()).append("Total: ").append(mDefaultComponentPoliy.size()).toString());
        ComponentPolicyInfo componentpolicyinfo;
        for(Iterator iterator = mDefaultComponentPoliy.iterator(); iterator.hasNext(); componentpolicyinfo.dump(filedescriptor, printwriter, as))
        {
            componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
            printwriter.println("");
        }

        printwriter.println("");
        printwriter.println(" ===== Package Policy ===== ");
        printwriter.println((new StringBuilder()).append("Total: ").append(mPackagePolices.size()).toString());
        for(Iterator iterator1 = mPackagePolices.iterator(); iterator1.hasNext(); ((PackageComponentPolicy)iterator1.next()).dump(filedescriptor, printwriter, as));
    }

    public PackageComponentPolicy getPackageComponentPolicy(String s)
    {
        for(Iterator iterator = mPackagePolices.iterator(); iterator.hasNext();)
        {
            PackageComponentPolicy packagecomponentpolicy = (PackageComponentPolicy)iterator.next();
            if(packagecomponentpolicy.isEquals(s))
                return packagecomponentpolicy;
        }

        return null;
    }

    public ComponentPolicyInfo getPolicy(int i)
    {
        for(Iterator iterator = mDefaultComponentPoliy.iterator(); iterator.hasNext();)
        {
            ComponentPolicyInfo componentpolicyinfo1 = (ComponentPolicyInfo)iterator.next();
            if(componentpolicyinfo1 != null && componentpolicyinfo1.getId() == i)
                return componentpolicyinfo1;
        }

        ComponentPolicyInfo componentpolicyinfo = null;
        Iterator iterator1 = mPackagePolices.iterator();
        do
        {
            if(!iterator1.hasNext())
                break;
            ComponentPolicyInfo componentpolicyinfo2 = ((PackageComponentPolicy)iterator1.next()).getPolicy(i);
            componentpolicyinfo = componentpolicyinfo2;
            if(componentpolicyinfo2 == null)
                continue;
            componentpolicyinfo = componentpolicyinfo2;
            break;
        } while(true);
        return componentpolicyinfo;
    }

    public void policyEnableWithModule(String s, boolean flag)
    {
        Iterator iterator = mDefaultComponentPoliy.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ComponentPolicyInfo componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
            if(componentpolicyinfo != null && componentpolicyinfo.getPolicyModuleName().equals(s))
                componentpolicyinfo.setEnableState(flag);
        } while(true);
        iterator = mPackagePolices.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            PackageComponentPolicy packagecomponentpolicy = (PackageComponentPolicy)iterator.next();
            if(packagecomponentpolicy != null)
                packagecomponentpolicy.policyEnableWithSetter(s, flag);
        } while(true);
    }

    public void policyEnableWithSetter(String s, boolean flag)
    {
        Iterator iterator = mDefaultComponentPoliy.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ComponentPolicyInfo componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
            if(componentpolicyinfo != null && componentpolicyinfo.getSetter().equals(s))
                componentpolicyinfo.setEnableState(flag);
        } while(true);
        Iterator iterator1 = mPackagePolices.iterator();
        do
        {
            if(!iterator1.hasNext())
                break;
            PackageComponentPolicy packagecomponentpolicy = (PackageComponentPolicy)iterator1.next();
            if(packagecomponentpolicy != null)
                packagecomponentpolicy.policyEnableWithSetter(s, flag);
        } while(true);
    }

    public void removePackagePoliciesByPackageInfos(List list)
    {
        for(list = list.iterator(); list.hasNext();)
        {
            String as[] = ((String)list.next()).split("#");
            int i = Integer.parseInt(as[0]);
            String s = as[1];
            Iterator iterator = mPackagePolices.iterator();
            while(iterator.hasNext()) 
            {
                PackageComponentPolicy packagecomponentpolicy = (PackageComponentPolicy)iterator.next();
                if(packagecomponentpolicy != null && packagecomponentpolicy.isEquals(s))
                    packagecomponentpolicy.removePolicyByUser(i);
            }
        }

    }

    public void removePackagesPolicies(int ai[])
    {
        int i = 0;
        for(int j = ai.length; i < j; i++)
        {
            int k = ai[i];
            Iterator iterator = mDefaultComponentPoliy.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                ComponentPolicyInfo componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
                if(componentpolicyinfo != null && componentpolicyinfo.getId() == k)
                    mDefaultComponentPoliy.remove(componentpolicyinfo);
            } while(true);
            for(Iterator iterator1 = mPackagePolices.iterator(); iterator1.hasNext(); ((PackageComponentPolicy)iterator1.next()).removePolicyById(k));
        }

    }

    public void removePackagesPoliciesByModule(String s)
    {
        Iterator iterator = mDefaultComponentPoliy.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ComponentPolicyInfo componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
            if(componentpolicyinfo != null && componentpolicyinfo.getPolicyModuleName().equals(s))
                mDefaultComponentPoliy.remove(componentpolicyinfo);
        } while(true);
        for(Iterator iterator1 = mPackagePolices.iterator(); iterator1.hasNext(); ((PackageComponentPolicy)iterator1.next()).removeAllPoliciesBySetter(s));
    }

    public void removePackagesPoliciesBySetter(String s)
    {
        Iterator iterator = mDefaultComponentPoliy.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ComponentPolicyInfo componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
            if(componentpolicyinfo != null && componentpolicyinfo.getSetter().equals(s))
                mDefaultComponentPoliy.remove(componentpolicyinfo);
        } while(true);
        for(Iterator iterator1 = mPackagePolices.iterator(); iterator1.hasNext(); ((PackageComponentPolicy)iterator1.next()).removeAllPoliciesBySetter(s));
    }

    public void updateCheckIntents(String s, List list, boolean flag)
    {
        boolean flag1 = false;
        Iterator iterator = mPackagePolices.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            PackageComponentPolicy packagecomponentpolicy = (PackageComponentPolicy)iterator.next();
            if(packagecomponentpolicy.getPkgName().equals(s))
            {
                flag1 = true;
                packagecomponentpolicy.updateCheckIntens(list, flag);
            }
        } while(true);
        if(!flag1)
        {
            s = new PackageComponentPolicy(s);
            s.updateCheckIntens(list, flag);
            mPackagePolices.add(s);
        }
    }

    public void updateCheckList(List list)
    {
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Object obj = ((String)iterator.next()).split("#");
            Object obj1 = obj[0];
            int i;
            boolean flag;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            boolean flag4;
            if(obj[1].equals("null"))
                list = null;
            else
                list = obj[1];
            i = Integer.parseInt(obj[2]);
            flag = Boolean.parseBoolean(obj[3]);
            flag1 = Boolean.parseBoolean(obj[4]);
            flag2 = Boolean.parseBoolean(obj[5]);
            flag3 = false;
            if(DEBUG)
                Slog.i("WtComponentManager", (new StringBuilder()).append("Update check list:").append(((String) (obj1))).append(" ").append(list).append(" ").append(i).append(" ").append(flag).append(" ").append(flag1).append(" ").append(flag2).toString());
            obj = mPackagePolices.iterator();
            do
            {
                flag4 = flag3;
                if(!((Iterator) (obj)).hasNext())
                    break;
                PackageComponentPolicy packagecomponentpolicy = (PackageComponentPolicy)((Iterator) (obj)).next();
                if(!packagecomponentpolicy.getPkgName().equals(obj1))
                    continue;
                flag4 = true;
                packagecomponentpolicy.updateCheckList(list, i, flag, flag1, flag2);
                break;
            } while(true);
            if(!flag4)
            {
                obj1 = new PackageComponentPolicy(((String) (obj1)));
                ((PackageComponentPolicy) (obj1)).updateCheckList(list, i, flag, flag1, flag2);
                mPackagePolices.add(obj1);
            }
        } while(true);
    }

    public void updateInputMethod(String s, boolean flag)
    {
        if(flag)
        {
            if(!mInputPackages.contains(s))
                mInputPackages.add(s);
        } else
        {
            mInputPackages.remove(s);
        }
    }

    public int[] updatePackagesPolicies(List list)
    {
        int ai[];
        int i;
        ai = new int[list.size()];
        i = 0;
        list = list.iterator();
_L2:
        ComponentPolicyInfo componentpolicyinfo;
        Object obj1;
        int j;
        if(!list.hasNext())
            break MISSING_BLOCK_LABEL_599;
        componentpolicyinfo = (ComponentPolicyInfo)list.next();
        if(DEBUG)
            Slog.d("WtComponentManager", (new StringBuilder()).append("Receive Update Policy: ").append(componentpolicyinfo.toString()).toString());
        Iterator iterator = mDefaultComponentPoliy.iterator();
        obj1 = mPackagePolices.iterator();
        j = 0;
        boolean flag = false;
        if(!componentpolicyinfo.isCommonPolicy())
            break; /* Loop/switch isn't completed */
        do
        {
            j = ((flag) ? 1 : 0);
            if(!iterator.hasNext())
                break;
            obj1 = (ComponentPolicyInfo)iterator.next();
            if(obj1 == null || componentpolicyinfo.getId() < 0 || ((ComponentPolicyInfo) (obj1)).getId() != componentpolicyinfo.getId())
                continue;
            mDefaultComponentPoliy.set(mDefaultComponentPoliy.indexOf(obj1), componentpolicyinfo);
            ai[i] = componentpolicyinfo.getId();
            flag = true;
            j = ((flag) ? 1 : 0);
            if(DEBUG)
            {
                Slog.d("WtComponentManager", (new StringBuilder()).append("Updated Common Policy: ").append(componentpolicyinfo.toString()).toString());
                j = ((flag) ? 1 : 0);
            }
            break;
        } while(true);
        if(j != 0)
            break MISSING_BLOCK_LABEL_321;
        Object obj = sMutex;
        obj;
        JVM INSTR monitorenter ;
        j = mIdIndex;
        mIdIndex = j + 1;
        ai[i] = j;
        obj;
        JVM INSTR monitorexit ;
        componentpolicyinfo.setId(ai[i]);
        mDefaultComponentPoliy.add(componentpolicyinfo);
        if(DEBUG)
            Slog.d("WtComponentManager", (new StringBuilder()).append("Added Common Policy: ").append(componentpolicyinfo.toString()).toString());
_L9:
        i++;
        if(true) goto _L2; else goto _L1
        list;
        throw list;
_L1:
        if(!((Iterator) (obj1)).hasNext()) goto _L4; else goto _L3
_L3:
        PackageComponentPolicy packagecomponentpolicy;
        packagecomponentpolicy = (PackageComponentPolicy)((Iterator) (obj1)).next();
        if(packagecomponentpolicy == null || !packagecomponentpolicy.isEquals(componentpolicyinfo.getPkg()))
            continue; /* Loop/switch isn't completed */
        if(componentpolicyinfo.getId() >= 0) goto _L6; else goto _L5
_L5:
        obj = sMutex;
        obj;
        JVM INSTR monitorenter ;
        j = mIdIndex;
        mIdIndex = j + 1;
        componentpolicyinfo.setId(j);
        obj;
        JVM INSTR monitorexit ;
_L6:
        packagecomponentpolicy.updatePolicy(componentpolicyinfo);
        if(DEBUG)
            Slog.d("WtComponentManager", (new StringBuilder()).append("Updated Package Policy: ").append(componentpolicyinfo.toString()).toString());
        ai[i] = componentpolicyinfo.getId();
        j = 1;
        if(true) goto _L1; else goto _L7
_L7:
        list;
        throw list;
_L4:
        if(j != 0) goto _L9; else goto _L8
_L8:
        obj1 = new PackageComponentPolicy(componentpolicyinfo.getPkg());
        obj = sMutex;
        obj;
        JVM INSTR monitorenter ;
        int k = mIdIndex;
        mIdIndex = k + 1;
        componentpolicyinfo.setId(k);
        obj;
        JVM INSTR monitorexit ;
        ai[i] = ((PackageComponentPolicy) (obj1)).updatePolicy(componentpolicyinfo);
        if(ai[i] >= 0)
        {
            mPackagePolices.add(obj1);
            if(DEBUG)
                Slog.d("WtComponentManager", (new StringBuilder()).append("Added Package Policy: ").append(componentpolicyinfo.toString()).toString());
        }
          goto _L9
        list;
        throw list;
        return ai;
    }

    private static final boolean DEBUG;
    private static final String SERVICE_RESTART = "Restart: AMS";
    private static final String TAG = "WtComponentManager";
    private static final Object sMutex = new Object();
    private static volatile WtComponentManager sWtComponentManager = null;
    private Context mContext;
    private CopyOnWriteArrayList mDefaultComponentPoliy;
    private int mIdIndex;
    private CopyOnWriteArrayList mInputPackages;
    private CopyOnWriteArrayList mPackagePolices;
    private WhetstoneSleepModeController mSleepModeController;

    static 
    {
        boolean flag;
        if(Build.TYPE.equalsIgnoreCase("user"))
            flag = SystemProperties.getBoolean("persist.sys.whetstone.debug", false);
        else
            flag = true;
        DEBUG = flag;
    }
}
