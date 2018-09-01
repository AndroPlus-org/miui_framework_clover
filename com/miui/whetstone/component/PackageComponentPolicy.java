// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.component;

import android.content.Intent;
import android.util.Slog;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package com.miui.whetstone.component:
//            WtComponentManager, ComponentPolicyInfo

public class PackageComponentPolicy
{
    class ProcessPermission
    {

        public boolean getCheckResult(int i)
        {
            switch(i)
            {
            case 3: // '\003'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            default:
                return false;

            case 1: // '\001'
                return checkStartType[0];

            case 2: // '\002'
                return checkStartType[1];

            case 4: // '\004'
                return checkStartType[2];

            case 8: // '\b'
                return checkStartType[3];
            }
        }

        public boolean hasAllow()
        {
            boolean aflag[] = checkStartType;
            int i = aflag.length;
            for(int j = 0; j < i; j++)
                if(aflag[j])
                    return true;

            return false;
        }

        public String toString()
        {
            return (new StringBuilder()).append("{ Process: ").append(processName).append(" Activity: ").append(checkStartType[0]).append(" Service: ").append(checkStartType[1]).append(" Receiver: ").append(checkStartType[2]).append(" Provider: ").append(checkStartType[3]).append(" }").toString();
        }

        public void updateCheckList(int i, boolean flag)
        {
            if((i & 1) != 0)
                checkStartType[0] = flag;
            if((i & 2) != 0)
                checkStartType[1] = flag;
            if((i & 4) != 0)
                checkStartType[2] = flag;
            if((i & 8) != 0)
                checkStartType[3] = flag;
            if(PackageComponentPolicy._2D_get0())
                Slog.i("PackageComponentPolicy", (new StringBuilder()).append(processName).append(" checkList:").append(Arrays.toString(checkStartType)).toString());
        }

        public void updateCheckListAll(boolean flag)
        {
            for(int i = 0; i < checkStartType.length; i++)
                checkStartType[i] = flag;

        }

        boolean checkStartType[] = {
            0, 0, 0, 0
        };
        String processName;
        final PackageComponentPolicy this$0;

        ProcessPermission(String s)
        {
            this$0 = PackageComponentPolicy.this;
            super();
            processName = s;
        }

        ProcessPermission(String s, boolean aflag[])
        {
            this$0 = PackageComponentPolicy.this;
            super();
            processName = s;
            checkStartType = (boolean[])aflag.clone();
        }
    }


    static boolean _2D_get0()
    {
        return DEBUG;
    }

    public PackageComponentPolicy(String s)
    {
        mProcessWhiteList = new ArrayList();
        mProcessBlackList = new ArrayList();
        mWhiteIntents = new ArrayList();
        mPolicyInfos = new CopyOnWriteArrayList();
        mPkgName = s;
    }

    public boolean checkPackagePolicyWithStartType(int i, int j, String s, int k, Intent intent)
    {
        Iterator iterator = mPolicyInfos.iterator();
        if((j & 4) != 0)
            if(!isIntentWhiteList(intent));
        boolean flag;
        do
        {
            flag = true;
            if(!iterator.hasNext())
                break;
            ComponentPolicyInfo componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
            if(componentpolicyinfo == null || (componentpolicyinfo.getStartType() & j) == 0 || componentpolicyinfo.isCommonPolicy() || componentpolicyinfo.getEnableStatus() ^ true || componentpolicyinfo.checkPolicyResult(mPkgName, i, k, intent, s))
                continue;
            flag = false;
            break;
        } while(true);
        return flag;
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        if(isEmpty())
            return;
        printwriter.println("");
        printwriter.println(" ### Package Policy ### ");
        printwriter.println((new StringBuilder()).append("Package: ").append(mPkgName).append(" Total: ").append(mPolicyInfos.size()).toString());
        printwriter.println("");
        printwriter.println(" ### Package Called White Lists ### ");
        printwriter.println((new StringBuilder()).append(" Activity: ").append(mWhiteListWithStartTypeAsCalled[0]).append(" Service: ").append(mWhiteListWithStartTypeAsCalled[1]).append(" Receiver: ").append(mWhiteListWithStartTypeAsCalled[2]).append(" Provider: ").append(mWhiteListWithStartTypeAsCalled[3]).toString());
        for(Iterator iterator = mProcessWhiteList.iterator(); iterator.hasNext(); printwriter.println(((ProcessPermission)iterator.next()).toString()));
        printwriter.println("");
        printwriter.println(" ### Package Called Black Lists ### ");
        printwriter.println((new StringBuilder()).append(" Activity: ").append(mBlackListWithStartTypeAsCalled[0]).append(" Service: ").append(mBlackListWithStartTypeAsCalled[1]).append(" Receiver: ").append(mBlackListWithStartTypeAsCalled[2]).append(" Provider: ").append(mBlackListWithStartTypeAsCalled[3]).toString());
        for(Iterator iterator1 = mProcessBlackList.iterator(); iterator1.hasNext(); printwriter.println(((ProcessPermission)iterator1.next()).toString()));
        printwriter.println("");
        printwriter.println(" ### Package Caller White Lists ### ");
        printwriter.println((new StringBuilder()).append(" Activity: ").append(mWhiteListWithStartTypeAsCaller[0]).append(" Service: ").append(mWhiteListWithStartTypeAsCaller[1]).append(" Receiver: ").append(mWhiteListWithStartTypeAsCaller[2]).append(" Provider: ").append(mWhiteListWithStartTypeAsCaller[3]).toString());
        printwriter.println("");
        printwriter.println(" ### Package Caller Black Lists ### ");
        printwriter.println((new StringBuilder()).append(" Activity: ").append(mBlackListWithStartTypeAsCaller[0]).append(" Service: ").append(mBlackListWithStartTypeAsCaller[1]).append(" Receiver: ").append(mBlackListWithStartTypeAsCaller[2]).append(" Provider: ").append(mBlackListWithStartTypeAsCaller[3]).toString());
        printwriter.println("");
        printwriter.println(" ### Package White Intents ### ");
        for(Iterator iterator2 = mWhiteIntents.iterator(); iterator2.hasNext(); printwriter.println((String)iterator2.next()));
        printwriter.println("");
        for(Iterator iterator3 = mPolicyInfos.iterator(); iterator3.hasNext(); ((ComponentPolicyInfo)iterator3.next()).dump(filedescriptor, printwriter, as));
    }

    public void dumpPackagtCommonInfo(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
    }

    public PackageComponentPolicy getPackagePolicy(String s)
    {
        if(mPkgName.equals(s))
            return this;
        else
            return null;
    }

    public long getPackageStartTimeWithType(int i)
    {
        return mStartTimeByType[i];
    }

    public String getPkgName()
    {
        return mPkgName;
    }

    public ComponentPolicyInfo getPolicy(int i)
    {
        for(Iterator iterator = mPolicyInfos.iterator(); iterator.hasNext();)
        {
            ComponentPolicyInfo componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
            if(componentpolicyinfo != null && componentpolicyinfo.getId() == i)
                return componentpolicyinfo;
        }

        return null;
    }

    public boolean inCheckList(String s, int i, boolean flag, boolean flag1)
    {
        boolean flag3;
        Object obj;
        Object obj1;
        boolean flag2;
        boolean flag4;
        Iterator iterator;
        if(s != null)
            flag2 = true;
        else
            flag2 = false;
        flag3 = false;
        flag4 = false;
        if(!flag1) goto _L2; else goto _L1
_L1:
        if(flag)
            obj = mWhiteListWithStartTypeAsCalled;
        else
            obj = mBlackListWithStartTypeAsCalled;
        obj1 = sMutex;
        obj1;
        JVM INSTR monitorenter ;
        flag1 = flag4;
        i;
        JVM INSTR tableswitch 1 8: default 88
    //                   1 214
    //                   2 223
    //                   3 92
    //                   4 232
    //                   5 92
    //                   6 92
    //                   7 92
    //                   8 241;
           goto _L3 _L4 _L5 _L6 _L7 _L6 _L6 _L6 _L8
_L3:
        flag1 = flag4;
_L6:
        obj1;
        JVM INSTR monitorexit ;
        flag4 = flag1;
        if(!flag2) goto _L10; else goto _L9
_L9:
        if(flag)
            obj = mProcessWhiteList;
        else
            obj = mProcessBlackList;
        obj1 = sMutex;
        obj1;
        JVM INSTR monitorenter ;
        iterator = ((Iterable) (obj)).iterator();
_L12:
        flag = flag1;
        obj = obj1;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_190;
        obj = (ProcessPermission)iterator.next();
        if(!((ProcessPermission) (obj)).processName.equals(s)) goto _L12; else goto _L11
_L11:
        flag = ((ProcessPermission) (obj)).getCheckResult(i);
        flag1 = flag;
        if(!flag) goto _L12; else goto _L13
_L13:
        obj = obj1;
_L15:
        obj;
        JVM INSTR monitorexit ;
        flag4 = flag;
_L10:
        return flag4;
_L4:
        flag1 = obj[0];
        continue; /* Loop/switch isn't completed */
_L5:
        flag1 = obj[1];
        continue; /* Loop/switch isn't completed */
_L7:
        flag1 = obj[2];
        continue; /* Loop/switch isn't completed */
_L8:
        flag1 = obj[3];
        continue; /* Loop/switch isn't completed */
        s;
        throw s;
_L2:
        if(flag)
            s = mWhiteListWithStartTypeAsCaller;
        else
            s = mBlackListWithStartTypeAsCaller;
        obj = sMutex;
        obj;
        JVM INSTR monitorenter ;
        switch(i)
        {
        case 3: // '\003'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        default:
            flag = flag3;
            break;

        case 1: // '\001'
            flag = s[0];
            break;

        case 2: // '\002'
            flag = s[1];
            break;

        case 4: // '\004'
            flag = s[2];
            break;

        case 8: // '\b'
            flag = s[3];
            break;
        }
        if(true) goto _L15; else goto _L14
_L14:
        if(true) goto _L6; else goto _L16
_L16:
    }

    public boolean isEmpty()
    {
        if(!mPolicyInfos.isEmpty())
            return false;
        if(!mProcessWhiteList.isEmpty() || mProcessBlackList.isEmpty() ^ true)
            return false;
        if(!mWhiteIntents.isEmpty())
            return false;
        boolean aflag[] = mWhiteListWithStartTypeAsCalled;
        int i = aflag.length;
        for(int j = 0; j < i; j++)
            if(Boolean.valueOf(aflag[j]).booleanValue())
                return false;

        aflag = mBlackListWithStartTypeAsCalled;
        i = aflag.length;
        for(int k = 0; k < i; k++)
            if(Boolean.valueOf(aflag[k]).booleanValue())
                return false;

        aflag = mWhiteListWithStartTypeAsCaller;
        i = aflag.length;
        for(int l = 0; l < i; l++)
            if(Boolean.valueOf(aflag[l]).booleanValue())
                return false;

        aflag = mBlackListWithStartTypeAsCaller;
        i = aflag.length;
        for(int i1 = 0; i1 < i; i1++)
            if(Boolean.valueOf(aflag[i1]).booleanValue())
                return false;

        return true;
    }

    public boolean isEquals(String s)
    {
        return mPkgName.equals(s);
    }

    public boolean isIntentWhiteList(Intent intent)
    {
        if(intent == null || intent.getAction() == null)
            return false;
        List list = mWhiteIntents;
        list;
        JVM INSTR monitorenter ;
        boolean flag = mWhiteIntents.contains(intent.getAction());
        list;
        JVM INSTR monitorexit ;
        return flag;
        intent;
        throw intent;
    }

    public void policyEnableWithModule(String s, boolean flag)
    {
        Iterator iterator = mPolicyInfos.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ComponentPolicyInfo componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
            if(componentpolicyinfo != null && componentpolicyinfo.getPolicyModuleName().equals(s))
                componentpolicyinfo.setEnableState(flag);
        } while(true);
    }

    public void policyEnableWithSetter(String s, boolean flag)
    {
        Iterator iterator = mPolicyInfos.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ComponentPolicyInfo componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
            if(componentpolicyinfo != null && componentpolicyinfo.getSetter().equals(s))
                componentpolicyinfo.setEnableState(flag);
        } while(true);
    }

    public void removeAllPoliciesByModule(String s)
    {
        if(s == null)
            return;
        Iterator iterator = mPolicyInfos.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ComponentPolicyInfo componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
            if(componentpolicyinfo != null && componentpolicyinfo.getPolicyModuleName().equals(s))
                mPolicyInfos.remove(componentpolicyinfo);
        } while(true);
    }

    public void removeAllPoliciesBySetter(String s)
    {
        if(s == null)
            return;
        Iterator iterator = mPolicyInfos.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ComponentPolicyInfo componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
            if(componentpolicyinfo != null && componentpolicyinfo.getSetter().equals(s))
                mPolicyInfos.remove(componentpolicyinfo);
        } while(true);
    }

    public void removePolicyById(int i)
    {
        if(i < 0)
            return;
        Iterator iterator = mPolicyInfos.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ComponentPolicyInfo componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
            if(componentpolicyinfo != null && componentpolicyinfo.getId() == i)
                mPolicyInfos.remove(componentpolicyinfo);
        } while(true);
    }

    public void removePolicyByUser(int i)
    {
        if(i < 0)
            mPolicyInfos.clear();
        Iterator iterator = mPolicyInfos.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ComponentPolicyInfo componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
            if(componentpolicyinfo != null && componentpolicyinfo.getUserId() == i)
                mPolicyInfos.remove(componentpolicyinfo);
        } while(true);
    }

    public void setPackageStartTimeWithType(int i, long l)
    {
        byte byte0;
        byte byte1;
        byte0 = -1;
        byte1 = byte0;
        i;
        JVM INSTR tableswitch 1 8: default 56
    //                   1 74
    //                   2 80
    //                   3 60
    //                   4 86
    //                   5 60
    //                   6 60
    //                   7 60
    //                   8 92;
           goto _L1 _L2 _L3 _L4 _L5 _L4 _L4 _L4 _L6
_L4:
        break; /* Loop/switch isn't completed */
_L1:
        byte1 = byte0;
_L8:
        if(byte1 >= 0)
            mStartTimeByType[byte1] = l;
        return;
_L2:
        byte1 = 0;
        continue; /* Loop/switch isn't completed */
_L3:
        byte1 = 1;
        continue; /* Loop/switch isn't completed */
_L5:
        byte1 = 2;
        continue; /* Loop/switch isn't completed */
_L6:
        byte1 = 3;
        if(true) goto _L8; else goto _L7
_L7:
    }

    public void updateCheckIntens(List list, boolean flag)
    {
        List list1 = mWhiteIntents;
        list1;
        JVM INSTR monitorenter ;
        if(!flag)
            break MISSING_BLOCK_LABEL_63;
        mWhiteIntents.addAll(list);
_L1:
        list1;
        JVM INSTR monitorexit ;
        if(DEBUG)
            Slog.i("PackageComponentPolicy", (new StringBuilder()).append("Component Check Intents: ").append(mWhiteIntents.toString()).toString());
        return;
        mWhiteIntents.removeAll(list);
          goto _L1
        list;
        throw list;
    }

    public void updateCheckList(String s, int i, boolean flag, boolean flag1, boolean flag2)
    {
        boolean flag3;
        List list;
        Object obj;
        Iterator iterator;
        ProcessPermission processpermission1;
        boolean flag4;
        if(s != null)
            flag3 = true;
        else
            flag3 = false;
        if(DEBUG)
            Slog.i("PackageComponentPolicy", (new StringBuilder()).append("Update CheckList: Pkg:").append(mPkgName).append(" Process:").append(s).append(" StartType:").append(i).append(" isAdd: ").append(flag).append(" isWhiteList:").append(flag1).append(" isCalled:").append(flag2).toString());
        if(!flag2) goto _L2; else goto _L1
_L1:
        if(!flag3) goto _L4; else goto _L3
_L3:
        if(flag1)
            list = mProcessWhiteList;
        else
            list = mProcessBlackList;
        obj = sMutex;
        obj;
        JVM INSTR monitorenter ;
        iterator = list.iterator();
        flag3 = true;
_L6:
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_241;
            processpermission1 = (ProcessPermission)iterator.next();
        } while(processpermission1 == null);
        if(!processpermission1.processName.equals(s)) goto _L6; else goto _L5
_L5:
        flag4 = false;
        processpermission1.updateCheckList(i, flag);
        flag3 = flag4;
        if(flag) goto _L6; else goto _L7
_L7:
        flag3 = flag4;
        if(!(processpermission1.hasAllow() ^ true)) goto _L6; else goto _L8
_L8:
        iterator.remove();
        flag3 = flag4;
          goto _L6
        s;
        throw s;
        if(!flag || !flag3)
            break MISSING_BLOCK_LABEL_279;
        ProcessPermission processpermission = JVM INSTR new #6   <Class PackageComponentPolicy$ProcessPermission>;
        processpermission.this. ProcessPermission(s);
        processpermission.updateCheckList(i, true);
        list.add(processpermission);
        obj;
        JVM INSTR monitorexit ;
_L10:
        return;
_L4:
        updatePackageCheckList(flag1, i, flag, true);
        if(DEBUG)
            Slog.i("PackageComponentPolicy", (new StringBuilder()).append(mPkgName).append(" white caller: ").append(Arrays.toString(mWhiteListWithStartTypeAsCaller)).append(" white called: ").append(Arrays.toString(mWhiteListWithStartTypeAsCalled)).append(" black caller: ").append(Arrays.toString(mBlackListWithStartTypeAsCaller)).append(" black called: ").append(Arrays.toString(mBlackListWithStartTypeAsCalled)).toString());
        continue; /* Loop/switch isn't completed */
_L2:
        updatePackageCheckList(flag1, i, flag, false);
        if(DEBUG)
            Slog.i("PackageComponentPolicy", (new StringBuilder()).append(mPkgName).append(" white caller: ").append(Arrays.toString(mWhiteListWithStartTypeAsCaller)).append(" white called: ").append(Arrays.toString(mWhiteListWithStartTypeAsCalled)).append(" black caller: ").append(Arrays.toString(mBlackListWithStartTypeAsCaller)).append(" black called: ").append(Arrays.toString(mBlackListWithStartTypeAsCalled)).toString());
        if(true) goto _L10; else goto _L9
_L9:
    }

    public void updateCheckListAll(boolean flag, boolean flag1)
    {
        boolean aflag[];
        Object obj;
        int i;
        if(flag1)
            aflag = mWhiteListWithStartTypeAsCalled;
        else
            aflag = mBlackListWithStartTypeAsCalled;
        obj = sMutex;
        obj;
        JVM INSTR monitorenter ;
        i = 0;
        while(i < aflag.length) 
        {
            aflag[i] = flag;
            i++;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void updatePackageCheckList(boolean flag, int i, boolean flag1, boolean flag2)
    {
        boolean aflag[];
        if(!flag2)
        {
            if(flag)
                aflag = mWhiteListWithStartTypeAsCaller;
            else
                aflag = mBlackListWithStartTypeAsCaller;
        } else
        if(flag)
            aflag = mWhiteListWithStartTypeAsCalled;
        else
            aflag = mBlackListWithStartTypeAsCalled;
        if((i & 1) != 0)
            aflag[0] = flag1;
        if((i & 2) != 0)
            aflag[1] = flag1;
        if((i & 4) != 0)
            aflag[2] = flag1;
        if((i & 8) != 0)
            aflag[3] = flag1;
    }

    public int updatePolicy(ComponentPolicyInfo componentpolicyinfo)
    {
        if(componentpolicyinfo == null)
            return -1;
        Iterator iterator = mPolicyInfos.iterator();
        int i = 0;
        boolean flag = false;
        do
        {
label0:
            {
                boolean flag1 = flag;
                if(iterator.hasNext())
                {
                    ComponentPolicyInfo componentpolicyinfo1 = (ComponentPolicyInfo)iterator.next();
                    if(componentpolicyinfo1 == null || componentpolicyinfo.getId() != componentpolicyinfo1.getId())
                        break label0;
                    mPolicyInfos.set(i, componentpolicyinfo);
                    if(DEBUG)
                        Slog.d("PackageComponentPolicy", (new StringBuilder()).append("Updated package Policy: ").append(componentpolicyinfo.toString()).toString());
                    flag1 = true;
                }
                if(!flag1)
                {
                    mPolicyInfos.add(componentpolicyinfo);
                    if(DEBUG)
                        Slog.d("PackageComponentPolicy", (new StringBuilder()).append("Added package Policy: ").append(componentpolicyinfo.toString()).toString());
                }
                return componentpolicyinfo.getId();
            }
            i++;
        } while(true);
    }

    public void updatePolicyEnableState(String s, int i, int j)
    {
        Iterator iterator = mPolicyInfos.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ComponentPolicyInfo componentpolicyinfo = (ComponentPolicyInfo)iterator.next();
            if(componentpolicyinfo != null)
            {
                componentpolicyinfo.updatePolicyEnableStatus(s, i, j);
                if(componentpolicyinfo.isRemovedWhenPolicyDisbale() && componentpolicyinfo.getEnableStatus() ^ true)
                    mPolicyInfos.remove(componentpolicyinfo);
            }
        } while(true);
    }

    private static final boolean DEBUG = WtComponentManager.isDebug();
    public static final int MATCH_METHOD_EQUAL = 1;
    public static final int MATCH_METHOD_INCLUDE = 2;
    public static final int MATCH_METHOD_NOT_EQUAL = 4;
    public static final int MATCH_METHOD_NOT_INCLUDE = 8;
    public static final int POLICY_CHECK_ALLOW = 2;
    public static final int POLICY_CHECK_DENY = 4;
    public static final int POLICY_CHECK_FLAG = 1;
    public static final int POLICY_FLAG_CALLED_AMS_RESTART = 8192;
    public static final int POLICY_FLAG_CALLED_CKECK_INTENT = 32768;
    public static final int POLICY_FLAG_CALLED_FOREGROUND = 0x10000;
    public static final int POLICY_FLAG_CALLED_INPUT_METHOD = 0x20000;
    public static final int POLICY_FLAG_CALLED_SELF = 16384;
    public static final int POLICY_FLAG_CALLED_SYSTEM = 4096;
    public static final int POLICY_FLAG_CALLER_FOREGROUND = 4;
    public static final int POLICY_FLAG_CALLER_SYSTEM = 1;
    public static final int POLICY_FLAG_CALLER_SYSTEM_UID = 2;
    public static final int POLICY_FLAG_UNKNOWN = 0;
    public static final int POLICY_TYPE_ALL = 15;
    public static final int POLICY_TYPE_BROADCAST = 2;
    public static final int POLICY_TYPE_COMPONENT = 1;
    public static final int POLICY_TYPE_PUSH = 4;
    public static final int POLICY_TYPE_SLEEP = 8;
    public static final int POLICY_TYPE_UNKNOWN = 0;
    public static final int START_BY_ACTIVITY = 1;
    public static final int START_BY_ALL = 15;
    public static final int START_BY_PROVIDER = 8;
    public static final int START_BY_RECEIVER = 4;
    public static final int START_BY_SERVICE = 2;
    public static final int START_BY_UNKNOWN = 0;
    public static final int START_TIME_WITH_TYPE_ACTIVITY = 0;
    public static final int START_TIME_WITH_TYPE_PROCIDER = 3;
    public static final int START_TIME_WITH_TYPE_RECEIVER = 2;
    public static final int START_TIME_WITH_TYPE_SERVICE = 1;
    private static final String TAG = "PackageComponentPolicy";
    private static final Object sMutex = new Object();
    private boolean mBlackListWithStartTypeAsCalled[] = {
        0, 0, 0, 0
    };
    private boolean mBlackListWithStartTypeAsCaller[] = {
        0, 0, 0, 0
    };
    private String mPkgName;
    private CopyOnWriteArrayList mPolicyInfos;
    private List mProcessBlackList;
    private List mProcessWhiteList;
    private long mStartTimeByType[] = {
        0L, 0L, 0L, 0L
    };
    private List mWhiteIntents;
    private boolean mWhiteListWithStartTypeAsCalled[] = {
        0, 0, 0, 0
    };
    private boolean mWhiteListWithStartTypeAsCaller[] = {
        0, 0, 0, 0
    };

}
