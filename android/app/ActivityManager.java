// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.content.pm.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.*;
import android.os.*;
import android.text.TextUtils;
import android.util.*;
import com.android.internal.os.RoSystemProperties;
import com.android.internal.os.TransferPipe;
import com.android.internal.util.FastPrintWriter;
import com.android.server.LocalServices;
import com.miui.internal.transition.IMiuiAppTransitionAnimationHelper;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlSerializer;

// Referenced classes of package android.app:
//            IActivityManager, AppGlobals, ActivityThread, ActivityManagerInternal, 
//            PendingIntent, Activity, IAppTask, Instrumentation

public class ActivityManager
{
    public static class AppTask
    {

        public void finishAndRemoveTask()
        {
            try
            {
                mAppTaskImpl.finishAndRemoveTask();
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        public RecentTaskInfo getTaskInfo()
        {
            RecentTaskInfo recenttaskinfo;
            try
            {
                recenttaskinfo = mAppTaskImpl.getTaskInfo();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return recenttaskinfo;
        }

        public void moveToFront()
        {
            try
            {
                mAppTaskImpl.moveToFront();
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        public void setExcludeFromRecents(boolean flag)
        {
            try
            {
                mAppTaskImpl.setExcludeFromRecents(flag);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        public void startActivity(Context context, Intent intent, Bundle bundle)
        {
            ActivityThread activitythread = ActivityThread.currentActivityThread();
            activitythread.getInstrumentation().execStartActivityFromAppTask(context, activitythread.getApplicationThread(), mAppTaskImpl, intent, bundle);
        }

        private IAppTask mAppTaskImpl;

        public AppTask(IAppTask iapptask)
        {
            mAppTaskImpl = iapptask;
        }
    }

    public static class MemoryInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void readFromParcel(Parcel parcel)
        {
            boolean flag = false;
            availMem = parcel.readLong();
            totalMem = parcel.readLong();
            threshold = parcel.readLong();
            if(parcel.readInt() != 0)
                flag = true;
            lowMemory = flag;
            hiddenAppThreshold = parcel.readLong();
            secondaryServerThreshold = parcel.readLong();
            visibleAppThreshold = parcel.readLong();
            foregroundAppThreshold = parcel.readLong();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeLong(availMem);
            parcel.writeLong(totalMem);
            parcel.writeLong(threshold);
            if(lowMemory)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeLong(hiddenAppThreshold);
            parcel.writeLong(secondaryServerThreshold);
            parcel.writeLong(visibleAppThreshold);
            parcel.writeLong(foregroundAppThreshold);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public MemoryInfo createFromParcel(Parcel parcel)
            {
                return new MemoryInfo(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public MemoryInfo[] newArray(int i)
            {
                return new MemoryInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public long availMem;
        public long foregroundAppThreshold;
        public long hiddenAppThreshold;
        public boolean lowMemory;
        public long secondaryServerThreshold;
        public long threshold;
        public long totalMem;
        public long visibleAppThreshold;


        public MemoryInfo()
        {
        }

        private MemoryInfo(Parcel parcel)
        {
            readFromParcel(parcel);
        }

        MemoryInfo(Parcel parcel, MemoryInfo memoryinfo)
        {
            this(parcel);
        }
    }

    public static interface OnUidImportanceListener
    {

        public abstract void onUidImportance(int i, int j);
    }

    public static class ProcessErrorStateInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void readFromParcel(Parcel parcel)
        {
            condition = parcel.readInt();
            processName = parcel.readString();
            pid = parcel.readInt();
            uid = parcel.readInt();
            tag = parcel.readString();
            shortMsg = parcel.readString();
            longMsg = parcel.readString();
            stackTrace = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(condition);
            parcel.writeString(processName);
            parcel.writeInt(pid);
            parcel.writeInt(uid);
            parcel.writeString(tag);
            parcel.writeString(shortMsg);
            parcel.writeString(longMsg);
            parcel.writeString(stackTrace);
        }

        public static final int CRASHED = 1;
        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ProcessErrorStateInfo createFromParcel(Parcel parcel)
            {
                return new ProcessErrorStateInfo(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ProcessErrorStateInfo[] newArray(int i)
            {
                return new ProcessErrorStateInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int NOT_RESPONDING = 2;
        public static final int NO_ERROR = 0;
        public int condition;
        public byte crashData[];
        public String longMsg;
        public int pid;
        public String processName;
        public String shortMsg;
        public String stackTrace;
        public String tag;
        public int uid;


        public ProcessErrorStateInfo()
        {
            crashData = null;
        }

        private ProcessErrorStateInfo(Parcel parcel)
        {
            crashData = null;
            readFromParcel(parcel);
        }

        ProcessErrorStateInfo(Parcel parcel, ProcessErrorStateInfo processerrorstateinfo)
        {
            this(parcel);
        }
    }

    public static class RecentTaskInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void readFromParcel(Parcel parcel)
        {
            id = parcel.readInt();
            persistentId = parcel.readInt();
            Object obj;
            boolean flag;
            if(parcel.readInt() > 0)
                obj = (Intent)Intent.CREATOR.createFromParcel(parcel);
            else
                obj = null;
            baseIntent = ((Intent) (obj));
            origActivity = ComponentName.readFromParcel(parcel);
            realActivity = ComponentName.readFromParcel(parcel);
            description = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            if(parcel.readInt() > 0)
                obj = (TaskDescription)TaskDescription.CREATOR.createFromParcel(parcel);
            else
                obj = null;
            taskDescription = ((TaskDescription) (obj));
            stackId = parcel.readInt();
            userId = parcel.readInt();
            firstActiveTime = parcel.readLong();
            lastActiveTime = parcel.readLong();
            affiliatedTaskId = parcel.readInt();
            affiliatedTaskColor = parcel.readInt();
            baseActivity = ComponentName.readFromParcel(parcel);
            topActivity = ComponentName.readFromParcel(parcel);
            numActivities = parcel.readInt();
            if(parcel.readInt() > 0)
                obj = (Rect)Rect.CREATOR.createFromParcel(parcel);
            else
                obj = null;
            bounds = ((Rect) (obj));
            if(parcel.readInt() == 1)
                flag = true;
            else
                flag = false;
            supportsSplitScreenMultiWindow = flag;
            resizeMode = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            i = 1;
            parcel.writeInt(id);
            parcel.writeInt(persistentId);
            if(baseIntent != null)
            {
                parcel.writeInt(1);
                baseIntent.writeToParcel(parcel, 0);
            } else
            {
                parcel.writeInt(0);
            }
            ComponentName.writeToParcel(origActivity, parcel);
            ComponentName.writeToParcel(realActivity, parcel);
            TextUtils.writeToParcel(description, parcel, 1);
            if(taskDescription != null)
            {
                parcel.writeInt(1);
                taskDescription.writeToParcel(parcel, 0);
            } else
            {
                parcel.writeInt(0);
            }
            parcel.writeInt(stackId);
            parcel.writeInt(userId);
            parcel.writeLong(firstActiveTime);
            parcel.writeLong(lastActiveTime);
            parcel.writeInt(affiliatedTaskId);
            parcel.writeInt(affiliatedTaskColor);
            ComponentName.writeToParcel(baseActivity, parcel);
            ComponentName.writeToParcel(topActivity, parcel);
            parcel.writeInt(numActivities);
            if(bounds != null)
            {
                parcel.writeInt(1);
                bounds.writeToParcel(parcel, 0);
            } else
            {
                parcel.writeInt(0);
            }
            if(!supportsSplitScreenMultiWindow)
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(resizeMode);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public RecentTaskInfo createFromParcel(Parcel parcel)
            {
                return new RecentTaskInfo(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public RecentTaskInfo[] newArray(int i)
            {
                return new RecentTaskInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public int affiliatedTaskColor;
        public int affiliatedTaskId;
        public ComponentName baseActivity;
        public Intent baseIntent;
        public Rect bounds;
        public CharSequence description;
        public long firstActiveTime;
        public int id;
        public long lastActiveTime;
        public int numActivities;
        public ComponentName origActivity;
        public int persistentId;
        public ComponentName realActivity;
        public int resizeMode;
        public int stackId;
        public boolean supportsSplitScreenMultiWindow;
        public TaskDescription taskDescription;
        public ComponentName topActivity;
        public int userId;


        public RecentTaskInfo()
        {
        }

        private RecentTaskInfo(Parcel parcel)
        {
            readFromParcel(parcel);
        }

        RecentTaskInfo(Parcel parcel, RecentTaskInfo recenttaskinfo)
        {
            this(parcel);
        }
    }

    public static class RunningAppProcessInfo
        implements Parcelable
    {

        public static int importanceToProcState(int i)
        {
            if(i == 1000)
                return 18;
            if(i >= 400)
                return 13;
            if(i >= 300)
                return 11;
            if(i > 270)
                return 10;
            if(i >= 230)
                return 8;
            if(i >= 200)
                return 6;
            if(i >= 150)
                return 5;
            return i < 125 ? 3 : 4;
        }

        public static int procStateToImportance(int i)
        {
            if(i == 18)
                return 1000;
            if(i >= 13)
                return 400;
            if(i >= 11)
                return 300;
            if(i > 10)
                return 270;
            if(i >= 8)
                return 230;
            if(i >= 6)
                return 200;
            if(i >= 5)
                return 150;
            return i < 4 ? 100 : 125;
        }

        public static int procStateToImportanceForClient(int i, Context context)
        {
            return procStateToImportanceForTargetSdk(i, context.getApplicationInfo().targetSdkVersion);
        }

        public static int procStateToImportanceForTargetSdk(int i, int j)
        {
            i = procStateToImportance(i);
            if(j >= 26) goto _L2; else goto _L1
_L1:
            i;
            JVM INSTR lookupswitch 2: default 40
        //                       230: 42
        //                       270: 46;
               goto _L2 _L3 _L4
_L2:
            return i;
_L3:
            return 130;
_L4:
            return 170;
        }

        public int describeContents()
        {
            return 0;
        }

        public void readFromParcel(Parcel parcel)
        {
            processName = parcel.readString();
            pid = parcel.readInt();
            uid = parcel.readInt();
            pkgList = parcel.readStringArray();
            flags = parcel.readInt();
            lastTrimLevel = parcel.readInt();
            importance = parcel.readInt();
            lru = parcel.readInt();
            importanceReasonCode = parcel.readInt();
            importanceReasonPid = parcel.readInt();
            importanceReasonComponent = ComponentName.readFromParcel(parcel);
            importanceReasonImportance = parcel.readInt();
            processState = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(processName);
            parcel.writeInt(pid);
            parcel.writeInt(uid);
            parcel.writeStringArray(pkgList);
            parcel.writeInt(flags);
            parcel.writeInt(lastTrimLevel);
            parcel.writeInt(importance);
            parcel.writeInt(lru);
            parcel.writeInt(importanceReasonCode);
            parcel.writeInt(importanceReasonPid);
            ComponentName.writeToParcel(importanceReasonComponent, parcel);
            parcel.writeInt(importanceReasonImportance);
            parcel.writeInt(processState);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public RunningAppProcessInfo createFromParcel(Parcel parcel)
            {
                return new RunningAppProcessInfo(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public RunningAppProcessInfo[] newArray(int i)
            {
                return new RunningAppProcessInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int FLAG_CANT_SAVE_STATE = 1;
        public static final int FLAG_HAS_ACTIVITIES = 4;
        public static final int FLAG_PERSISTENT = 2;
        public static final int IMPORTANCE_BACKGROUND = 400;
        public static final int IMPORTANCE_CACHED = 400;
        public static final int IMPORTANCE_CANT_SAVE_STATE = 270;
        public static final int IMPORTANCE_CANT_SAVE_STATE_PRE_26 = 170;
        public static final int IMPORTANCE_EMPTY = 500;
        public static final int IMPORTANCE_FOREGROUND = 100;
        public static final int IMPORTANCE_FOREGROUND_SERVICE = 125;
        public static final int IMPORTANCE_GONE = 1000;
        public static final int IMPORTANCE_PERCEPTIBLE = 230;
        public static final int IMPORTANCE_PERCEPTIBLE_PRE_26 = 130;
        public static final int IMPORTANCE_SERVICE = 300;
        public static final int IMPORTANCE_TOP_SLEEPING = 150;
        public static final int IMPORTANCE_VISIBLE = 200;
        public static final int REASON_PROVIDER_IN_USE = 1;
        public static final int REASON_SERVICE_IN_USE = 2;
        public static final int REASON_UNKNOWN = 0;
        public int flags;
        public int importance;
        public int importanceReasonCode;
        public ComponentName importanceReasonComponent;
        public int importanceReasonImportance;
        public int importanceReasonPid;
        public int lastTrimLevel;
        public int lru;
        public int pid;
        public String pkgList[];
        public String processName;
        public int processState;
        public int uid;


        public RunningAppProcessInfo()
        {
            importance = 100;
            importanceReasonCode = 0;
            processState = 6;
        }

        private RunningAppProcessInfo(Parcel parcel)
        {
            readFromParcel(parcel);
        }

        RunningAppProcessInfo(Parcel parcel, RunningAppProcessInfo runningappprocessinfo)
        {
            this(parcel);
        }

        public RunningAppProcessInfo(String s, int i, String as[])
        {
            processName = s;
            pid = i;
            pkgList = as;
        }
    }

    public static class RunningServiceInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void readFromParcel(Parcel parcel)
        {
            boolean flag = true;
            service = ComponentName.readFromParcel(parcel);
            pid = parcel.readInt();
            uid = parcel.readInt();
            process = parcel.readString();
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            foreground = flag1;
            activeSince = parcel.readLong();
            if(parcel.readInt() != 0)
                flag1 = flag;
            else
                flag1 = false;
            started = flag1;
            clientCount = parcel.readInt();
            crashCount = parcel.readInt();
            lastActivityTime = parcel.readLong();
            restarting = parcel.readLong();
            flags = parcel.readInt();
            clientPackage = parcel.readString();
            clientLabel = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            boolean flag = true;
            ComponentName.writeToParcel(service, parcel);
            parcel.writeInt(pid);
            parcel.writeInt(uid);
            parcel.writeString(process);
            if(foreground)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeLong(activeSince);
            if(started)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(clientCount);
            parcel.writeInt(crashCount);
            parcel.writeLong(lastActivityTime);
            parcel.writeLong(restarting);
            parcel.writeInt(flags);
            parcel.writeString(clientPackage);
            parcel.writeInt(clientLabel);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public RunningServiceInfo createFromParcel(Parcel parcel)
            {
                return new RunningServiceInfo(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public RunningServiceInfo[] newArray(int i)
            {
                return new RunningServiceInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int FLAG_FOREGROUND = 2;
        public static final int FLAG_PERSISTENT_PROCESS = 8;
        public static final int FLAG_STARTED = 1;
        public static final int FLAG_SYSTEM_PROCESS = 4;
        public long activeSince;
        public int clientCount;
        public int clientLabel;
        public String clientPackage;
        public int crashCount;
        public int flags;
        public boolean foreground;
        public long lastActivityTime;
        public int pid;
        public String process;
        public long restarting;
        public ComponentName service;
        public boolean started;
        public int uid;


        public RunningServiceInfo()
        {
        }

        private RunningServiceInfo(Parcel parcel)
        {
            readFromParcel(parcel);
        }

        RunningServiceInfo(Parcel parcel, RunningServiceInfo runningserviceinfo)
        {
            this(parcel);
        }
    }

    public static class RunningTaskInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void readFromParcel(Parcel parcel)
        {
            id = parcel.readInt();
            stackId = parcel.readInt();
            baseActivity = ComponentName.readFromParcel(parcel);
            topActivity = ComponentName.readFromParcel(parcel);
            boolean flag;
            if(parcel.readInt() != 0)
                thumbnail = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel);
            else
                thumbnail = null;
            description = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            numActivities = parcel.readInt();
            numRunning = parcel.readInt();
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            supportsSplitScreenMultiWindow = flag;
            resizeMode = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            i = 1;
            parcel.writeInt(id);
            parcel.writeInt(stackId);
            ComponentName.writeToParcel(baseActivity, parcel);
            ComponentName.writeToParcel(topActivity, parcel);
            if(thumbnail != null)
            {
                parcel.writeInt(1);
                thumbnail.writeToParcel(parcel, 0);
            } else
            {
                parcel.writeInt(0);
            }
            TextUtils.writeToParcel(description, parcel, 1);
            parcel.writeInt(numActivities);
            parcel.writeInt(numRunning);
            if(!supportsSplitScreenMultiWindow)
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(resizeMode);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public RunningTaskInfo createFromParcel(Parcel parcel)
            {
                return new RunningTaskInfo(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public RunningTaskInfo[] newArray(int i)
            {
                return new RunningTaskInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public ComponentName baseActivity;
        public CharSequence description;
        public int id;
        public long lastActiveTime;
        public int numActivities;
        public int numRunning;
        public int resizeMode;
        public int stackId;
        public boolean supportsSplitScreenMultiWindow;
        public Bitmap thumbnail;
        public ComponentName topActivity;


        public RunningTaskInfo()
        {
        }

        private RunningTaskInfo(Parcel parcel)
        {
            readFromParcel(parcel);
        }

        RunningTaskInfo(Parcel parcel, RunningTaskInfo runningtaskinfo)
        {
            this(parcel);
        }
    }

    public static class StackId
    {

        public static boolean allowTopTaskToReturnHome(int i)
        {
            boolean flag;
            if(i != 4)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public static boolean canReceiveKeys(int i)
        {
            boolean flag;
            if(i != 4)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public static boolean canSpecifyOrientation(int i)
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = flag;
            if(i == 0) goto _L2; else goto _L1
_L1:
            if(i != 5) goto _L4; else goto _L3
_L3:
            flag1 = flag;
_L2:
            return flag1;
_L4:
            flag1 = flag;
            if(i != 1)
            {
                flag1 = flag;
                if(i != 6)
                    flag1 = isDynamicStack(i);
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        public static boolean hasMovementAnimations(int i)
        {
            boolean flag;
            if(i != 4)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public static boolean hasWindowDecor(int i)
        {
            boolean flag;
            if(i == 2)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public static boolean hasWindowShadow(int i)
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(i != 2)
                if(i == 4)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public static boolean isAllowedOverLockscreen(int i)
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = flag;
            if(i == 0) goto _L2; else goto _L1
_L1:
            if(i != 1) goto _L4; else goto _L3
_L3:
            flag1 = flag;
_L2:
            return flag1;
_L4:
            flag1 = flag;
            if(i != 6)
                flag1 = false;
            if(true) goto _L2; else goto _L5
_L5:
        }

        public static boolean isAllowedToEnterPictureInPicture(int i)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(i != 0)
            {
                flag1 = flag;
                if(i != 6)
                {
                    flag1 = flag;
                    if(i != 5)
                        flag1 = true;
                }
            }
            return flag1;
        }

        public static boolean isAlwaysOnTop(int i)
        {
            boolean flag;
            if(i == 4)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public static boolean isBackdropToTranslucentActivity(int i)
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = flag;
            if(i == 1) goto _L2; else goto _L1
_L1:
            if(i != 6) goto _L4; else goto _L3
_L3:
            flag1 = flag;
_L2:
            return flag1;
_L4:
            flag1 = flag;
            if(i != 5)
                flag1 = false;
            if(true) goto _L2; else goto _L5
_L5:
        }

        public static boolean isDynamicStack(int i)
        {
            boolean flag;
            if(i >= 7)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public static boolean isDynamicStacksVisibleBehindAllowed(int i)
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(i != 4)
                if(i == 6)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public static boolean isHomeOrRecentsStack(int i)
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(i != 0)
                if(i == 5)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public static boolean isMultiWindowStack(int i)
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = flag;
            if(i == 4) goto _L2; else goto _L1
_L1:
            if(i != 2) goto _L4; else goto _L3
_L3:
            flag1 = flag;
_L2:
            return flag1;
_L4:
            flag1 = flag;
            if(i != 3)
                flag1 = false;
            if(true) goto _L2; else goto _L5
_L5:
        }

        public static boolean isResizeableByDockedStack(int i)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(isStaticStack(i))
            {
                flag1 = flag;
                if(i != 3)
                {
                    flag1 = flag;
                    if(i != 4)
                    {
                        flag1 = flag;
                        if(i != 6)
                            flag1 = true;
                    }
                }
            }
            return flag1;
        }

        public static boolean isStackAffectedByDragResizing(int i)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(isStaticStack(i))
            {
                flag1 = flag;
                if(i != 4)
                {
                    flag1 = flag;
                    if(i != 6)
                        flag1 = true;
                }
            }
            return flag1;
        }

        public static boolean isStaticStack(int i)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(i >= 0)
            {
                flag1 = flag;
                if(i <= 6)
                    flag1 = true;
            }
            return flag1;
        }

        public static boolean isTaskResizeAllowed(int i)
        {
            boolean flag;
            if(i == 2)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public static boolean isTaskResizeableByDockedStack(int i)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(isStaticStack(i))
            {
                flag1 = flag;
                if(i != 2)
                {
                    flag1 = flag;
                    if(i != 3)
                    {
                        flag1 = flag;
                        if(i != 4)
                        {
                            flag1 = flag;
                            if(i != 6)
                                flag1 = true;
                        }
                    }
                }
            }
            return flag1;
        }

        public static boolean keepFocusInStackIfPossible(int i)
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = flag;
            if(i == 2) goto _L2; else goto _L1
_L1:
            if(i != 3) goto _L4; else goto _L3
_L3:
            flag1 = flag;
_L2:
            return flag1;
_L4:
            flag1 = flag;
            if(i != 4)
                flag1 = false;
            if(true) goto _L2; else goto _L5
_L5:
        }

        public static boolean keepVisibleDeadAppWindowOnScreen(int i)
        {
            boolean flag;
            if(i != 4)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public static boolean normallyFullscreenWindows(int i)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(i != 4)
            {
                flag1 = flag;
                if(i != 2)
                {
                    flag1 = flag;
                    if(i != 3)
                        flag1 = true;
                }
            }
            return flag1;
        }

        public static boolean persistTaskBounds(int i)
        {
            boolean flag;
            if(i == 2)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public static boolean replaceWindowsOnTaskMove(int i, int j)
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(i != 2)
                if(j == 2)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public static boolean resizeStackWithLaunchBounds(int i)
        {
            boolean flag;
            if(i == 4)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public static boolean tasksAreFloating(int i)
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(i != 2)
                if(i == 4)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public static boolean useAnimationSpecForAppTransition(int i)
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = flag;
            if(i == 2) goto _L2; else goto _L1
_L1:
            if(i != 1) goto _L4; else goto _L3
_L3:
            flag1 = flag;
_L2:
            return flag1;
_L4:
            flag1 = flag;
            if(i != 6)
            {
                flag1 = flag;
                if(i != 3)
                {
                    flag1 = flag;
                    if(i != -1)
                        flag1 = false;
                }
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        public static boolean useWindowFrameForBackdrop(int i)
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(i != 2)
                if(i == 4)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public static boolean windowsAreScaleable(int i)
        {
            boolean flag;
            if(i == 4)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public static final int ASSISTANT_STACK_ID = 6;
        public static final int DOCKED_STACK_ID = 3;
        public static final int FIRST_DYNAMIC_STACK_ID = 7;
        public static final int FIRST_STATIC_STACK_ID = 0;
        public static final int FREEFORM_WORKSPACE_STACK_ID = 2;
        public static final int FULLSCREEN_WORKSPACE_STACK_ID = 1;
        public static final int HOME_STACK_ID = 0;
        public static final int INVALID_STACK_ID = -1;
        public static final int LAST_STATIC_STACK_ID = 6;
        public static final int PINNED_STACK_ID = 4;
        public static final int RECENTS_STACK_ID = 5;

        public StackId()
        {
        }
    }

    public static class StackInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void readFromParcel(Parcel parcel)
        {
            boolean flag = false;
            stackId = parcel.readInt();
            bounds = new Rect(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
            taskIds = parcel.createIntArray();
            taskNames = parcel.createStringArray();
            int i = parcel.readInt();
            if(i > 0)
            {
                taskBounds = new Rect[i];
                for(int j = 0; j < i; j++)
                {
                    taskBounds[j] = new Rect();
                    taskBounds[j].set(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                }

            } else
            {
                taskBounds = null;
            }
            taskUserIds = parcel.createIntArray();
            displayId = parcel.readInt();
            userId = parcel.readInt();
            if(parcel.readInt() > 0)
                flag = true;
            visible = flag;
            position = parcel.readInt();
            if(parcel.readInt() > 0)
                topActivity = ComponentName.readFromParcel(parcel);
        }

        public String toString()
        {
            return toString("");
        }

        public String toString(String s)
        {
            StringBuilder stringbuilder = new StringBuilder(256);
            stringbuilder.append(s);
            stringbuilder.append("Stack id=");
            stringbuilder.append(stackId);
            stringbuilder.append(" bounds=");
            stringbuilder.append(bounds.toShortString());
            stringbuilder.append(" displayId=");
            stringbuilder.append(displayId);
            stringbuilder.append(" userId=");
            stringbuilder.append(userId);
            stringbuilder.append("\n");
            s = (new StringBuilder()).append(s).append("  ").toString();
            for(int i = 0; i < taskIds.length; i++)
            {
                stringbuilder.append(s);
                stringbuilder.append("taskId=");
                stringbuilder.append(taskIds[i]);
                stringbuilder.append(": ");
                stringbuilder.append(taskNames[i]);
                if(taskBounds != null)
                {
                    stringbuilder.append(" bounds=");
                    stringbuilder.append(taskBounds[i].toShortString());
                }
                stringbuilder.append(" userId=").append(taskUserIds[i]);
                stringbuilder.append(" visible=").append(visible);
                if(topActivity != null)
                    stringbuilder.append(" topActivity=").append(topActivity);
                stringbuilder.append("\n");
            }

            return stringbuilder.toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(stackId);
            parcel.writeInt(bounds.left);
            parcel.writeInt(bounds.top);
            parcel.writeInt(bounds.right);
            parcel.writeInt(bounds.bottom);
            parcel.writeIntArray(taskIds);
            parcel.writeStringArray(taskNames);
            if(taskBounds == null)
                i = 0;
            else
                i = taskBounds.length;
            parcel.writeInt(i);
            for(int j = 0; j < i; j++)
            {
                parcel.writeInt(taskBounds[j].left);
                parcel.writeInt(taskBounds[j].top);
                parcel.writeInt(taskBounds[j].right);
                parcel.writeInt(taskBounds[j].bottom);
            }

            parcel.writeIntArray(taskUserIds);
            parcel.writeInt(displayId);
            parcel.writeInt(userId);
            if(visible)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(position);
            if(topActivity != null)
            {
                parcel.writeInt(1);
                topActivity.writeToParcel(parcel, 0);
            } else
            {
                parcel.writeInt(0);
            }
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public StackInfo createFromParcel(Parcel parcel)
            {
                return new StackInfo(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public StackInfo[] newArray(int i)
            {
                return new StackInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public Rect bounds;
        public int displayId;
        public int position;
        public int stackId;
        public Rect taskBounds[];
        public int taskIds[];
        public String taskNames[];
        public int taskUserIds[];
        public ComponentName topActivity;
        public int userId;
        public boolean visible;


        public StackInfo()
        {
            bounds = new Rect();
        }

        private StackInfo(Parcel parcel)
        {
            bounds = new Rect();
            readFromParcel(parcel);
        }

        StackInfo(Parcel parcel, StackInfo stackinfo)
        {
            this(parcel);
        }
    }

    public static class TaskDescription
        implements Parcelable
    {

        public static Bitmap loadTaskDescriptionIcon(String s, int i)
        {
            if(s != null)
            {
                try
                {
                    s = ActivityManager.getService().getTaskDescriptionIcon(s, i);
                }
                // Misplaced declaration of an exception variable
                catch(String s)
                {
                    throw s.rethrowFromSystemServer();
                }
                return s;
            } else
            {
                return null;
            }
        }

        public void copyFrom(TaskDescription taskdescription)
        {
            mLabel = taskdescription.mLabel;
            mIcon = taskdescription.mIcon;
            mIconFilename = taskdescription.mIconFilename;
            mColorPrimary = taskdescription.mColorPrimary;
            mColorBackground = taskdescription.mColorBackground;
            mStatusBarColor = taskdescription.mStatusBarColor;
            mNavigationBarColor = taskdescription.mNavigationBarColor;
        }

        public void copyFromPreserveHiddenFields(TaskDescription taskdescription)
        {
            mLabel = taskdescription.mLabel;
            mIcon = taskdescription.mIcon;
            mIconFilename = taskdescription.mIconFilename;
            mColorPrimary = taskdescription.mColorPrimary;
            if(taskdescription.mColorBackground != 0)
                mColorBackground = taskdescription.mColorBackground;
            if(taskdescription.mStatusBarColor != 0)
                mStatusBarColor = taskdescription.mStatusBarColor;
            if(taskdescription.mNavigationBarColor != 0)
                mNavigationBarColor = taskdescription.mNavigationBarColor;
        }

        public int describeContents()
        {
            return 0;
        }

        public int getBackgroundColor()
        {
            return mColorBackground;
        }

        public Bitmap getIcon()
        {
            if(mIcon != null)
                return mIcon;
            else
                return loadTaskDescriptionIcon(mIconFilename, UserHandle.myUserId());
        }

        public String getIconFilename()
        {
            return mIconFilename;
        }

        public Bitmap getInMemoryIcon()
        {
            return mIcon;
        }

        public String getLabel()
        {
            return mLabel;
        }

        public int getNavigationBarColor()
        {
            return mNavigationBarColor;
        }

        public int getPrimaryColor()
        {
            return mColorPrimary;
        }

        public int getStatusBarColor()
        {
            return mStatusBarColor;
        }

        public void readFromParcel(Parcel parcel)
        {
            Object obj = null;
            Object obj1;
            if(parcel.readInt() > 0)
                obj1 = parcel.readString();
            else
                obj1 = null;
            mLabel = ((String) (obj1));
            if(parcel.readInt() > 0)
                obj1 = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel);
            else
                obj1 = null;
            mIcon = ((Bitmap) (obj1));
            mColorPrimary = parcel.readInt();
            mColorBackground = parcel.readInt();
            mStatusBarColor = parcel.readInt();
            mNavigationBarColor = parcel.readInt();
            obj1 = obj;
            if(parcel.readInt() > 0)
                obj1 = parcel.readString();
            mIconFilename = ((String) (obj1));
        }

        public void restoreFromXml(String s, String s1)
        {
            if(!"task_description_label".equals(s)) goto _L2; else goto _L1
_L1:
            setLabel(s1);
_L4:
            return;
_L2:
            if("task_description_color".equals(s))
                setPrimaryColor((int)Long.parseLong(s1, 16));
            else
            if("task_description_colorBackground".equals(s))
                setBackgroundColor((int)Long.parseLong(s1, 16));
            else
            if("task_description_icon_filename".equals(s))
                setIconFilename(s1);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void saveToXml(XmlSerializer xmlserializer)
            throws IOException
        {
            if(mLabel != null)
                xmlserializer.attribute(null, "task_description_label", mLabel);
            if(mColorPrimary != 0)
                xmlserializer.attribute(null, "task_description_color", Integer.toHexString(mColorPrimary));
            if(mColorBackground != 0)
                xmlserializer.attribute(null, "task_description_colorBackground", Integer.toHexString(mColorBackground));
            if(mIconFilename != null)
                xmlserializer.attribute(null, "task_description_icon_filename", mIconFilename);
        }

        public void setBackgroundColor(int i)
        {
            if(i != 0 && Color.alpha(i) != 255)
            {
                throw new RuntimeException("A TaskDescription's background color should be opaque");
            } else
            {
                mColorBackground = i;
                return;
            }
        }

        public void setIcon(Bitmap bitmap)
        {
            mIcon = bitmap;
        }

        public void setIconFilename(String s)
        {
            mIconFilename = s;
            mIcon = null;
        }

        public void setLabel(String s)
        {
            mLabel = s;
        }

        public void setNavigationBarColor(int i)
        {
            mNavigationBarColor = i;
        }

        public void setPrimaryColor(int i)
        {
            if(i != 0 && Color.alpha(i) != 255)
            {
                throw new RuntimeException("A TaskDescription's primary color should be opaque");
            } else
            {
                mColorPrimary = i;
                return;
            }
        }

        public void setStatusBarColor(int i)
        {
            mStatusBarColor = i;
        }

        public String toString()
        {
            return (new StringBuilder()).append("TaskDescription Label: ").append(mLabel).append(" Icon: ").append(mIcon).append(" IconFilename: ").append(mIconFilename).append(" colorPrimary: ").append(mColorPrimary).append(" colorBackground: ").append(mColorBackground).append(" statusBarColor: ").append(mColorBackground).append(" navigationBarColor: ").append(mNavigationBarColor).toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            if(mLabel == null)
            {
                parcel.writeInt(0);
            } else
            {
                parcel.writeInt(1);
                parcel.writeString(mLabel);
            }
            if(mIcon == null)
            {
                parcel.writeInt(0);
            } else
            {
                parcel.writeInt(1);
                mIcon.writeToParcel(parcel, 0);
            }
            parcel.writeInt(mColorPrimary);
            parcel.writeInt(mColorBackground);
            parcel.writeInt(mStatusBarColor);
            parcel.writeInt(mNavigationBarColor);
            if(mIconFilename == null)
            {
                parcel.writeInt(0);
            } else
            {
                parcel.writeInt(1);
                parcel.writeString(mIconFilename);
            }
        }

        private static final String ATTR_TASKDESCRIPTIONCOLOR_BACKGROUND = "task_description_colorBackground";
        private static final String ATTR_TASKDESCRIPTIONCOLOR_PRIMARY = "task_description_color";
        private static final String ATTR_TASKDESCRIPTIONICONFILENAME = "task_description_icon_filename";
        private static final String ATTR_TASKDESCRIPTIONLABEL = "task_description_label";
        public static final String ATTR_TASKDESCRIPTION_PREFIX = "task_description_";
        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public TaskDescription createFromParcel(Parcel parcel)
            {
                return new TaskDescription(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public TaskDescription[] newArray(int i)
            {
                return new TaskDescription[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private int mColorBackground;
        private int mColorPrimary;
        private Bitmap mIcon;
        private String mIconFilename;
        private String mLabel;
        private int mNavigationBarColor;
        private int mStatusBarColor;


        public TaskDescription()
        {
            this(null, null, null, 0, 0, 0, 0);
        }

        public TaskDescription(TaskDescription taskdescription)
        {
            copyFrom(taskdescription);
        }

        private TaskDescription(Parcel parcel)
        {
            readFromParcel(parcel);
        }

        TaskDescription(Parcel parcel, TaskDescription taskdescription)
        {
            this(parcel);
        }

        public TaskDescription(String s)
        {
            this(s, null, null, 0, 0, 0, 0);
        }

        public TaskDescription(String s, Bitmap bitmap)
        {
            this(s, bitmap, null, 0, 0, 0, 0);
        }

        public TaskDescription(String s, Bitmap bitmap, int i)
        {
            this(s, bitmap, null, i, 0, 0, 0);
            if(i != 0 && Color.alpha(i) != 255)
                throw new RuntimeException("A TaskDescription's primary color should be opaque");
            else
                return;
        }

        public TaskDescription(String s, Bitmap bitmap, String s1, int i, int j, int k, int l)
        {
            mLabel = s;
            mIcon = bitmap;
            mIconFilename = s1;
            mColorPrimary = i;
            mColorBackground = j;
            mStatusBarColor = k;
            mNavigationBarColor = l;
        }
    }

    public static class TaskSnapshot
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public Rect getContentInsets()
        {
            return mContentInsets;
        }

        public int getOrientation()
        {
            return mOrientation;
        }

        public float getScale()
        {
            return mScale;
        }

        public GraphicBuffer getSnapshot()
        {
            return mSnapshot;
        }

        public boolean isReducedResolution()
        {
            return mReducedResolution;
        }

        public String toString()
        {
            return (new StringBuilder()).append("TaskSnapshot{mSnapshot=").append(mSnapshot).append(" mOrientation=").append(mOrientation).append(" mContentInsets=").append(mContentInsets.toShortString()).append(" mReducedResolution=").append(mReducedResolution).append(" mScale=").append(mScale).toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeParcelable(mSnapshot, 0);
            parcel.writeInt(mOrientation);
            parcel.writeParcelable(mContentInsets, 0);
            parcel.writeBoolean(mReducedResolution);
            parcel.writeFloat(mScale);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public TaskSnapshot createFromParcel(Parcel parcel)
            {
                return new TaskSnapshot(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public TaskSnapshot[] newArray(int i)
            {
                return new TaskSnapshot[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final Rect mContentInsets;
        private final int mOrientation;
        private final boolean mReducedResolution;
        private final float mScale;
        private final GraphicBuffer mSnapshot;


        public TaskSnapshot(GraphicBuffer graphicbuffer, int i, Rect rect, boolean flag, float f)
        {
            mSnapshot = graphicbuffer;
            mOrientation = i;
            mContentInsets = new Rect(rect);
            mReducedResolution = flag;
            mScale = f;
        }

        private TaskSnapshot(Parcel parcel)
        {
            mSnapshot = (GraphicBuffer)parcel.readParcelable(null);
            mOrientation = parcel.readInt();
            mContentInsets = (Rect)parcel.readParcelable(null);
            mReducedResolution = parcel.readBoolean();
            mScale = parcel.readFloat();
        }

        TaskSnapshot(Parcel parcel, TaskSnapshot tasksnapshot)
        {
            this(parcel);
        }
    }

    public static class TaskThumbnail
        implements Parcelable
    {

        public int describeContents()
        {
            if(thumbnailFileDescriptor != null)
                return thumbnailFileDescriptor.describeContents();
            else
                return 0;
        }

        public void readFromParcel(Parcel parcel)
        {
            if(parcel.readInt() != 0)
                mainThumbnail = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel);
            else
                mainThumbnail = null;
            if(parcel.readInt() != 0)
                thumbnailFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
            else
                thumbnailFileDescriptor = null;
            if(parcel.readInt() != 0)
                thumbnailInfo = (TaskThumbnailInfo)TaskThumbnailInfo.CREATOR.createFromParcel(parcel);
            else
                thumbnailInfo = null;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            if(mainThumbnail != null)
            {
                parcel.writeInt(1);
                mainThumbnail.writeToParcel(parcel, i);
            } else
            {
                parcel.writeInt(0);
            }
            if(thumbnailFileDescriptor != null)
            {
                parcel.writeInt(1);
                thumbnailFileDescriptor.writeToParcel(parcel, i);
            } else
            {
                parcel.writeInt(0);
            }
            if(thumbnailInfo != null)
            {
                parcel.writeInt(1);
                thumbnailInfo.writeToParcel(parcel, i);
            } else
            {
                parcel.writeInt(0);
            }
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public TaskThumbnail createFromParcel(Parcel parcel)
            {
                return new TaskThumbnail(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public TaskThumbnail[] newArray(int i)
            {
                return new TaskThumbnail[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public Bitmap mainThumbnail;
        public ParcelFileDescriptor thumbnailFileDescriptor;
        public TaskThumbnailInfo thumbnailInfo;


        public TaskThumbnail()
        {
        }

        private TaskThumbnail(Parcel parcel)
        {
            readFromParcel(parcel);
        }

        TaskThumbnail(Parcel parcel, TaskThumbnail taskthumbnail)
        {
            this(parcel);
        }
    }

    public static class TaskThumbnailInfo
        implements Parcelable
    {

        public void copyFrom(TaskThumbnailInfo taskthumbnailinfo)
        {
            taskWidth = taskthumbnailinfo.taskWidth;
            taskHeight = taskthumbnailinfo.taskHeight;
            screenOrientation = taskthumbnailinfo.screenOrientation;
        }

        public int describeContents()
        {
            return 0;
        }

        public void readFromParcel(Parcel parcel)
        {
            taskWidth = parcel.readInt();
            taskHeight = parcel.readInt();
            screenOrientation = parcel.readInt();
        }

        public void reset()
        {
            taskWidth = 0;
            taskHeight = 0;
            screenOrientation = 0;
        }

        public void restoreFromXml(String s, String s1)
        {
            if(!"task_thumbnailinfo_task_width".equals(s)) goto _L2; else goto _L1
_L1:
            taskWidth = Integer.parseInt(s1);
_L4:
            return;
_L2:
            if("task_thumbnailinfo_task_height".equals(s))
                taskHeight = Integer.parseInt(s1);
            else
            if("task_thumbnailinfo_screen_orientation".equals(s))
                screenOrientation = Integer.parseInt(s1);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void saveToXml(XmlSerializer xmlserializer)
            throws IOException
        {
            xmlserializer.attribute(null, "task_thumbnailinfo_task_width", Integer.toString(taskWidth));
            xmlserializer.attribute(null, "task_thumbnailinfo_task_height", Integer.toString(taskHeight));
            xmlserializer.attribute(null, "task_thumbnailinfo_screen_orientation", Integer.toString(screenOrientation));
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(taskWidth);
            parcel.writeInt(taskHeight);
            parcel.writeInt(screenOrientation);
        }

        private static final String ATTR_SCREEN_ORIENTATION = "task_thumbnailinfo_screen_orientation";
        private static final String ATTR_TASK_HEIGHT = "task_thumbnailinfo_task_height";
        public static final String ATTR_TASK_THUMBNAILINFO_PREFIX = "task_thumbnailinfo_";
        private static final String ATTR_TASK_WIDTH = "task_thumbnailinfo_task_width";
        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public TaskThumbnailInfo createFromParcel(Parcel parcel)
            {
                return new TaskThumbnailInfo(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public TaskThumbnailInfo[] newArray(int i)
            {
                return new TaskThumbnailInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public int screenOrientation;
        public int taskHeight;
        public int taskWidth;


        public TaskThumbnailInfo()
        {
            screenOrientation = 0;
        }

        private TaskThumbnailInfo(Parcel parcel)
        {
            screenOrientation = 0;
            readFromParcel(parcel);
        }

        TaskThumbnailInfo(Parcel parcel, TaskThumbnailInfo taskthumbnailinfo)
        {
            this(parcel);
        }
    }

    static final class UidObserver extends IUidObserver.Stub
    {

        public void onUidActive(int i)
        {
        }

        public void onUidCachedChanged(int i, boolean flag)
        {
        }

        public void onUidGone(int i, boolean flag)
        {
            mListener.onUidImportance(i, 1000);
        }

        public void onUidIdle(int i, boolean flag)
        {
        }

        public void onUidStateChanged(int i, int j, long l)
        {
            mListener.onUidImportance(i, RunningAppProcessInfo.procStateToImportanceForClient(j, mContext));
        }

        final Context mContext;
        final OnUidImportanceListener mListener;

        UidObserver(OnUidImportanceListener onuidimportancelistener, Context context)
        {
            mListener = onuidimportancelistener;
            mContext = context;
        }
    }


    ActivityManager(Context context, Handler handler)
    {
        mContext = context;
    }

    public static void broadcastStickyIntent(Intent intent, int i)
    {
        broadcastStickyIntent(intent, -1, i);
    }

    public static void broadcastStickyIntent(Intent intent, int i, int j)
    {
        getService().broadcastIntent(null, intent, null, null, -1, null, null, null, i, null, false, true, j);
_L2:
        return;
        intent;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static int checkComponentPermission(String s, int i, int j, boolean flag)
    {
        int k = UserHandle.getAppId(i);
        if(k == 0 || k == 1000)
            return 0;
        if(UserHandle.isIsolated(i))
            return -1;
        if(j >= 0 && UserHandle.isSameApp(i, j))
            return 0;
        if(!flag)
            return -1;
        if(s == null)
            return 0;
        try
        {
            i = AppGlobals.getPackageManager().checkUidPermission(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public static int checkUidPermission(String s, int i)
    {
        try
        {
            i = AppGlobals.getPackageManager().checkUidPermission(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public static void dumpPackageStateStatic(FileDescriptor filedescriptor, String s)
    {
        FastPrintWriter fastprintwriter = new FastPrintWriter(new FileOutputStream(filedescriptor));
        dumpService(fastprintwriter, filedescriptor, "package", new String[] {
            s
        });
        fastprintwriter.println();
        dumpService(fastprintwriter, filedescriptor, "activity", new String[] {
            "-a", "package", s
        });
        fastprintwriter.println();
        dumpService(fastprintwriter, filedescriptor, "meminfo", new String[] {
            "--local", "--package", s
        });
        fastprintwriter.println();
        dumpService(fastprintwriter, filedescriptor, "procstats", new String[] {
            s
        });
        fastprintwriter.println();
        dumpService(fastprintwriter, filedescriptor, "usagestats", new String[] {
            "--packages", s
        });
        fastprintwriter.println();
        dumpService(fastprintwriter, filedescriptor, "batterystats", new String[] {
            s
        });
        fastprintwriter.flush();
    }

    private static void dumpService(PrintWriter printwriter, FileDescriptor filedescriptor, String s, String as[])
    {
        Object obj;
        printwriter.print("DUMP OF SERVICE ");
        printwriter.print(s);
        printwriter.println(":");
        s = ServiceManager.checkService(s);
        if(s == null)
        {
            printwriter.println("  (Service not found)");
            return;
        }
        obj = null;
        TransferPipe transferpipe;
        printwriter.flush();
        transferpipe = JVM INSTR new #404 <Class TransferPipe>;
        transferpipe.TransferPipe();
        transferpipe.setBufferPrefix("  ");
        s.dumpAsync(transferpipe.getWriteFd().getFileDescriptor(), as);
        transferpipe.go(filedescriptor, 10000L);
_L1:
        return;
        s;
        filedescriptor = obj;
_L2:
        if(filedescriptor != null)
            filedescriptor.kill();
        printwriter.println("Failure dumping service:");
        s.printStackTrace(printwriter);
          goto _L1
        s;
        filedescriptor = transferpipe;
          goto _L2
    }

    private void ensureAppTaskThumbnailSizeLocked()
    {
        if(mAppTaskThumbnailSize != null)
            break MISSING_BLOCK_LABEL_19;
        mAppTaskThumbnailSize = getService().getAppTaskThumbnailSize();
        return;
        RemoteException remoteexception;
        remoteexception;
        throw remoteexception.rethrowFromSystemServer();
    }

    public static int getCurrentUser()
    {
        UserInfo userinfo;
        int i;
        try
        {
            userinfo = getService().getCurrentUser();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(userinfo == null) goto _L2; else goto _L1
_L1:
        i = userinfo.id;
_L4:
        return i;
_L2:
        i = 0;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static int getDefaultAppRecentsLimitStatic()
    {
        return getMaxRecentTasksStatic() / 6;
    }

    static int getLauncherLargeIconSizeInner(Context context)
    {
        context = context.getResources();
        int i = context.getDimensionPixelSize(0x1050000);
        if(context.getConfiguration().smallestScreenWidthDp < 600)
            return i;
        switch(context.getDisplayMetrics().densityDpi)
        {
        default:
            return (int)((float)i * 1.5F + 0.5F);

        case 120: // 'x'
            return (i * 160) / 120;

        case 160: 
            return (i * 240) / 160;

        case 213: 
            return (i * 320) / 240;

        case 240: 
            return (i * 320) / 240;

        case 320: 
            return (i * 480) / 320;

        case 480: 
            return (i * 320 * 2) / 480;
        }
    }

    public static int getMaxAppRecentsLimitStatic()
    {
        return getMaxRecentTasksStatic() / 2;
    }

    public static int getMaxNumPictureInPictureActions()
    {
        return 3;
    }

    public static int getMaxRecentTasksStatic()
    {
        if(gMaxRecentTasks < 0)
        {
            int i;
            if(isLowRamDeviceStatic())
                i = 36;
            else
                i = 48;
            gMaxRecentTasks = i;
            return i;
        } else
        {
            return gMaxRecentTasks;
        }
    }

    public static void getMyMemoryState(RunningAppProcessInfo runningappprocessinfo)
    {
        try
        {
            getService().getMyMemoryState(runningappprocessinfo);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(RunningAppProcessInfo runningappprocessinfo)
        {
            throw runningappprocessinfo.rethrowFromSystemServer();
        }
    }

    public static IActivityManager getService()
    {
        return (IActivityManager)IActivityManagerSingleton.get();
    }

    public static int handleIncomingUser(int i, int j, int k, boolean flag, boolean flag1, String s, String s1)
    {
        if(UserHandle.getUserId(j) == k)
            return k;
        try
        {
            i = getService().handleIncomingUser(i, j, k, flag, flag1, s, s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public static boolean isHighEndGfx()
    {
        boolean flag;
        if(!"1".equals(SystemProperties.get("persist.sys.force_sw_gles", "0")) && isLowRamDeviceStatic() ^ true)
            flag = Resources.getSystem().getBoolean(0x1120023) ^ true;
        else
            flag = false;
        return flag;
    }

    public static boolean isLowRamDeviceStatic()
    {
        boolean flag;
        if(!RoSystemProperties.CONFIG_LOW_RAM)
        {
            if(Build.IS_DEBUGGABLE)
                flag = DEVELOPMENT_FORCE_LOW_RAM;
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public static final boolean isProcStateBackground(int i)
    {
        boolean flag;
        if(i >= 8)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isRunningInTestHarness()
    {
        return SystemProperties.getBoolean("ro.test_harness", false);
    }

    public static boolean isSmallBatteryDevice()
    {
        return RoSystemProperties.CONFIG_SMALL_BATTERY;
    }

    public static final boolean isStartResultFatalError(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(-100 <= i)
        {
            flag1 = flag;
            if(i <= -1)
                flag1 = true;
        }
        return flag1;
    }

    public static final boolean isStartResultSuccessful(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 0)
        {
            flag1 = flag;
            if(i <= 99)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isSystemReady()
    {
        if(!sSystemReady)
            if(ActivityThread.isSystem())
                sSystemReady = ((ActivityManagerInternal)LocalServices.getService(android/app/ActivityManagerInternal)).isSystemReady();
            else
                sSystemReady = true;
        return sSystemReady;
    }

    public static boolean isUserAMonkey()
    {
        boolean flag;
        try
        {
            flag = getService().isUserAMonkey();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public static void logoutCurrentUser()
    {
        int i;
        i = getCurrentUser();
        if(i == 0)
            break MISSING_BLOCK_LABEL_30;
        getService().switchUser(0);
        getService().stopUser(i, false, null);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.rethrowFromSystemServer();
          goto _L1
    }

    public static void noteAlarmFinish(PendingIntent pendingintent, int i, String s)
    {
        android.content.IIntentSender iintentsender = null;
        IActivityManager iactivitymanager = getService();
        if(pendingintent == null)
            break MISSING_BLOCK_LABEL_16;
        iintentsender = pendingintent.getTarget();
        iactivitymanager.noteAlarmFinish(iintentsender, i, s);
_L2:
        return;
        pendingintent;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void noteAlarmStart(PendingIntent pendingintent, int i, String s)
    {
        android.content.IIntentSender iintentsender = null;
        IActivityManager iactivitymanager = getService();
        if(pendingintent == null)
            break MISSING_BLOCK_LABEL_16;
        iintentsender = pendingintent.getTarget();
        iactivitymanager.noteAlarmStart(iintentsender, i, s);
_L2:
        return;
        pendingintent;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void noteWakeupAlarm(PendingIntent pendingintent, int i, String s, String s1)
    {
        android.content.IIntentSender iintentsender = null;
        IActivityManager iactivitymanager = getService();
        if(pendingintent == null)
            break MISSING_BLOCK_LABEL_18;
        iintentsender = pendingintent.getTarget();
        iactivitymanager.noteWakeupAlarm(iintentsender, i, s, s1);
_L2:
        return;
        pendingintent;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void setPersistentVrThread(int i)
    {
        getService().setPersistentVrThread(i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void setVrThread(int i)
    {
        getService().setVrThread(i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static int staticGetLargeMemoryClass()
    {
        String s = SystemProperties.get("dalvik.vm.heapsize", "16m");
        return Integer.parseInt(s.substring(0, s.length() - 1));
    }

    public static int staticGetMemoryClass()
    {
        String s = SystemProperties.get("dalvik.vm.heapgrowthlimit", "");
        if(s != null && "".equals(s) ^ true)
            return Integer.parseInt(s.substring(0, s.length() - 1));
        else
            return staticGetLargeMemoryClass();
    }

    public static boolean supportsMultiWindow(Context context)
    {
        boolean flag = context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
        if(!isLowRamDeviceStatic() || flag)
            flag = Resources.getSystem().getBoolean(0x11200b1);
        else
            flag = false;
        return flag;
    }

    public static boolean supportsSplitScreenMultiWindow(Context context)
    {
        boolean flag;
        if(supportsMultiWindow(context))
            flag = Resources.getSystem().getBoolean(0x11200b2);
        else
            flag = false;
        return flag;
    }

    public int addAppTask(Activity activity, Intent intent, TaskDescription taskdescription, Bitmap bitmap)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        ensureAppTaskThumbnailSizeLocked();
        obj = mAppTaskThumbnailSize;
        this;
        JVM INSTR monitorexit ;
label0:
        {
            int i = bitmap.getWidth();
            int j = bitmap.getHeight();
            Bitmap bitmap1;
            if(i == ((Point) (obj)).x)
            {
                bitmap1 = bitmap;
                if(j == ((Point) (obj)).y)
                    break label0;
            }
            bitmap1 = Bitmap.createBitmap(((Point) (obj)).x, ((Point) (obj)).y, bitmap.getConfig());
            float f = 0.0F;
            float f1;
            Canvas canvas;
            if(((Point) (obj)).x * i > ((Point) (obj)).y * j)
            {
                f1 = (float)((Point) (obj)).x / (float)j;
                f = ((float)((Point) (obj)).y - (float)i * f1) * 0.5F;
            } else
            {
                f1 = (float)((Point) (obj)).y / (float)i;
                float f2 = ((Point) (obj)).x;
                f2 = j;
            }
            obj = new Matrix();
            ((Matrix) (obj)).setScale(f1, f1);
            ((Matrix) (obj)).postTranslate((int)(0.5F + f), 0.0F);
            canvas = new Canvas(bitmap1);
            canvas.drawBitmap(bitmap, ((Matrix) (obj)), null);
            canvas.setBitmap(null);
        }
        bitmap = taskdescription;
        if(taskdescription == null)
            bitmap = new TaskDescription();
        try
        {
            i = getService().addAppTask(activity.getActivityToken(), intent, bitmap, bitmap1);
        }
        // Misplaced declaration of an exception variable
        catch(Activity activity)
        {
            throw activity.rethrowFromSystemServer();
        }
        return i;
        activity;
        throw activity;
    }

    public void addOnUidImportanceListener(OnUidImportanceListener onuidimportancelistener, int i)
    {
        this;
        JVM INSTR monitorenter ;
        if(mImportanceListeners.containsKey(onuidimportancelistener))
        {
            IllegalArgumentException illegalargumentexception = JVM INSTR new #726 <Class IllegalArgumentException>;
            StringBuilder stringbuilder = JVM INSTR new #728 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            illegalargumentexception.IllegalArgumentException(stringbuilder.append("Listener already registered: ").append(onuidimportancelistener).toString());
            throw illegalargumentexception;
        }
        break MISSING_BLOCK_LABEL_53;
        onuidimportancelistener;
        this;
        JVM INSTR monitorexit ;
        throw onuidimportancelistener;
        UidObserver uidobserver;
        uidobserver = JVM INSTR new #72  <Class ActivityManager$UidObserver>;
        uidobserver.UidObserver(onuidimportancelistener, mContext);
        getService().registerUidObserver(uidobserver, 3, RunningAppProcessInfo.importanceToProcState(i), mContext.getOpPackageName());
        mImportanceListeners.put(onuidimportancelistener, uidobserver);
        this;
        JVM INSTR monitorexit ;
        return;
        onuidimportancelistener;
        throw onuidimportancelistener.rethrowFromSystemServer();
    }

    public boolean clearApplicationUserData()
    {
        return clearApplicationUserData(mContext.getPackageName(), null);
    }

    public boolean clearApplicationUserData(String s, IPackageDataObserver ipackagedataobserver)
    {
        boolean flag;
        try
        {
            flag = getService().clearApplicationUserData(s, ipackagedataobserver, UserHandle.myUserId());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public void clearGrantedUriPermissions(String s)
    {
        try
        {
            getService().clearGrantedUriPermissions(s, UserHandle.myUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void clearWatchHeapLimit()
    {
        try
        {
            getService().setDumpHeapDebugLimit(null, 0, 0L, null);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void dumpPackageState(FileDescriptor filedescriptor, String s)
    {
        dumpPackageStateStatic(filedescriptor, s);
    }

    public void forceStopPackage(String s)
    {
        forceStopPackageAsUser(s, UserHandle.myUserId());
    }

    public void forceStopPackageAsUser(String s, int i)
    {
        try
        {
            getService().forceStopPackage(s, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public Size getAppTaskThumbnailSize()
    {
        this;
        JVM INSTR monitorenter ;
        Size size;
        ensureAppTaskThumbnailSizeLocked();
        size = new Size(mAppTaskThumbnailSize.x, mAppTaskThumbnailSize.y);
        this;
        JVM INSTR monitorexit ;
        return size;
        Exception exception;
        exception;
        throw exception;
    }

    public List getAppTasks()
    {
        Object obj = new ArrayList();
        List list;
        int i;
        try
        {
            list = getService().getAppTasks(mContext.getPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw ((RemoteException) (obj)).rethrowFromSystemServer();
        }
        i = list.size();
        for(int j = 0; j < i; j++)
            ((ArrayList) (obj)).add(new AppTask(IAppTask.Stub.asInterface((IBinder)list.get(j))));

        return ((List) (obj));
    }

    public ConfigurationInfo getDeviceConfigurationInfo()
    {
        ConfigurationInfo configurationinfo;
        try
        {
            configurationinfo = getService().getDeviceConfigurationInfo();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return configurationinfo;
    }

    public int getFrontActivityScreenCompatMode()
    {
        int i;
        try
        {
            i = getService().getFrontActivityScreenCompatMode();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public ParceledListSlice getGrantedUriPermissions(String s)
    {
        try
        {
            s = getService().getGrantedUriPermissions(s, UserHandle.myUserId());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public int getLargeMemoryClass()
    {
        return staticGetLargeMemoryClass();
    }

    public int getLauncherLargeIconDensity()
    {
        Resources resources = mContext.getResources();
        int i = resources.getDisplayMetrics().densityDpi;
        if(resources.getConfiguration().smallestScreenWidthDp < 600)
            return i;
        switch(i)
        {
        default:
            return (int)((float)i * 1.5F + 0.5F);

        case 120: // 'x'
            return 160;

        case 160: 
            return 240;

        case 213: 
            return 320;

        case 240: 
            return 320;

        case 320: 
            return 480;

        case 480: 
            return 640;
        }
    }

    public int getLauncherLargeIconSize()
    {
        return getLauncherLargeIconSizeInner(mContext);
    }

    public int getLockTaskModeState()
    {
        int i;
        try
        {
            i = getService().getLockTaskModeState();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int getMemoryClass()
    {
        return staticGetMemoryClass();
    }

    public void getMemoryInfo(MemoryInfo memoryinfo)
    {
        try
        {
            getService().getMemoryInfo(memoryinfo);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(MemoryInfo memoryinfo)
        {
            throw memoryinfo.rethrowFromSystemServer();
        }
    }

    public boolean getPackageAskScreenCompat(String s)
    {
        boolean flag;
        try
        {
            flag = getService().getPackageAskScreenCompat(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public int getPackageImportance(String s)
    {
        int i;
        try
        {
            i = RunningAppProcessInfo.procStateToImportanceForClient(getService().getPackageProcessState(s, mContext.getOpPackageName()), mContext);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public int getPackageScreenCompatMode(String s)
    {
        int i;
        try
        {
            i = getService().getPackageScreenCompatMode(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return i;
    }

    public android.os.Debug.MemoryInfo[] getProcessMemoryInfo(int ai[])
    {
        try
        {
            ai = getService().getProcessMemoryInfo(ai);
        }
        // Misplaced declaration of an exception variable
        catch(int ai[])
        {
            throw ai.rethrowFromSystemServer();
        }
        return ai;
    }

    public List getProcessesInErrorState()
    {
        List list;
        try
        {
            list = getService().getProcessesInErrorState();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public List getRecentTasks(int i, int j)
        throws SecurityException
    {
        List list;
        try
        {
            list = getService().getRecentTasks(i, j, UserHandle.myUserId()).getList();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public List getRecentTasksForUser(int i, int j, int k)
        throws SecurityException
    {
        List list;
        try
        {
            list = getService().getRecentTasks(i, j, k).getList();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public List getRunningAppProcesses()
    {
        List list;
        try
        {
            list = getService().getRunningAppProcesses();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public List getRunningExternalApplications()
    {
        List list;
        try
        {
            list = getService().getRunningExternalApplications();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public PendingIntent getRunningServiceControlPanel(ComponentName componentname)
        throws SecurityException
    {
        try
        {
            componentname = getService().getRunningServiceControlPanel(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return componentname;
    }

    public List getRunningServices(int i)
        throws SecurityException
    {
        List list;
        try
        {
            list = getService().getServices(i, 0);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public List getRunningTasks(int i)
        throws SecurityException
    {
        List list;
        try
        {
            list = getService().getTasks(i, 0);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public TaskThumbnail getTaskThumbnail(int i)
        throws SecurityException
    {
        TaskThumbnail taskthumbnail;
        try
        {
            taskthumbnail = getService().getTaskThumbnail(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return taskthumbnail;
    }

    public int getUidImportance(int i)
    {
        try
        {
            i = RunningAppProcessInfo.procStateToImportanceForClient(getService().getUidProcessState(i, mContext.getOpPackageName()), mContext);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public boolean isInLockTaskMode()
    {
        boolean flag = false;
        if(getLockTaskModeState() != 0)
            flag = true;
        return flag;
    }

    public boolean isLowRamDevice()
    {
        return isLowRamDeviceStatic();
    }

    public boolean isUserRunning(int i)
    {
        boolean flag;
        try
        {
            flag = getService().isUserRunning(i, 0);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isVrModePackageEnabled(ComponentName componentname)
    {
        boolean flag;
        try
        {
            flag = getService().isVrModePackageEnabled(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return flag;
    }

    public void killBackgroundProcesses(String s)
    {
        try
        {
            getService().killBackgroundProcesses(s, UserHandle.myUserId());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void killUid(int i, String s)
    {
        try
        {
            getService().killUid(UserHandle.getAppId(i), UserHandle.getUserId(i), s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void moveTaskToFront(int i, int j)
    {
        moveTaskToFront(i, j, null);
    }

    public void moveTaskToFront(int i, int j, Bundle bundle)
    {
        try
        {
            getService().moveTaskToFront(i, j, bundle);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Bundle bundle)
        {
            throw bundle.rethrowFromSystemServer();
        }
    }

    public void registerMiuiAppTransitionAnimationHelper(IMiuiAppTransitionAnimationHelper imiuiapptransitionanimationhelper)
    {
        if(imiuiapptransitionanimationhelper == null)
            return;
        getService().registerMiuiAppTransitionAnimationHelper(imiuiapptransitionanimationhelper);
_L1:
        return;
        imiuiapptransitionanimationhelper;
        imiuiapptransitionanimationhelper.printStackTrace();
          goto _L1
    }

    public void removeOnUidImportanceListener(OnUidImportanceListener onuidimportancelistener)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = (UidObserver)mImportanceListeners.remove(onuidimportancelistener);
        if(obj != null)
            break MISSING_BLOCK_LABEL_55;
        obj = JVM INSTR new #726 <Class IllegalArgumentException>;
        StringBuilder stringbuilder = JVM INSTR new #728 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        ((IllegalArgumentException) (obj)).IllegalArgumentException(stringbuilder.append("Listener not registered: ").append(onuidimportancelistener).toString());
        throw obj;
        onuidimportancelistener;
        this;
        JVM INSTR monitorexit ;
        throw onuidimportancelistener;
        getService().unregisterUidObserver(((IUidObserver) (obj)));
        this;
        JVM INSTR monitorexit ;
        return;
        onuidimportancelistener;
        throw onuidimportancelistener.rethrowFromSystemServer();
    }

    public boolean removeTask(int i)
        throws SecurityException
    {
        boolean flag;
        try
        {
            flag = getService().removeTask(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void restartPackage(String s)
    {
        killBackgroundProcesses(s);
    }

    public void setFrontActivityScreenCompatMode(int i)
    {
        try
        {
            getService().setFrontActivityScreenCompatMode(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setPackageAskScreenCompat(String s, boolean flag)
    {
        try
        {
            getService().setPackageAskScreenCompat(s, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setPackageScreenCompatMode(String s, int i)
    {
        try
        {
            getService().setPackageScreenCompatMode(s, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public boolean setProcessMemoryTrimLevel(String s, int i, int j)
    {
        boolean flag;
        try
        {
            flag = getService().setProcessMemoryTrimLevel(s, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public void setResizeWhiteList(List list)
    {
        getService().setResizeWhiteList(list);
_L1:
        return;
        list;
        list.printStackTrace();
          goto _L1
    }

    public void setWatchHeapLimit(long l)
    {
        try
        {
            getService().setDumpHeapDebugLimit(null, 0, l, mContext.getPackageName());
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void startLockTaskMode(int i)
    {
        try
        {
            getService().startLockTaskModeById(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void stopLockTaskMode()
    {
        try
        {
            getService().stopLockTaskMode();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public boolean switchUser(int i)
    {
        boolean flag;
        try
        {
            flag = getService().switchUser(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void unregisterMiuiAppTransitionAnimationHelper()
    {
        getService().unregisterMiuiAppTransitionAnimationHelper();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
          goto _L1
    }

    public static final String ACTION_REPORT_HEAP_LIMIT = "android.app.action.REPORT_HEAP_LIMIT";
    public static final int APP_START_MODE_DELAYED = 1;
    public static final int APP_START_MODE_DELAYED_RIGID = 2;
    public static final int APP_START_MODE_DISABLED = 3;
    public static final int APP_START_MODE_NORMAL = 0;
    public static final int ASSIST_CONTEXT_AUTOFILL = 2;
    public static final int ASSIST_CONTEXT_BASIC = 0;
    public static final int ASSIST_CONTEXT_FULL = 1;
    public static final int BROADCAST_FAILED_USER_STOPPED = -2;
    public static final int BROADCAST_STICKY_CANT_HAVE_PERMISSION = -1;
    public static final int BROADCAST_SUCCESS = 0;
    public static final int BUGREPORT_OPTION_FULL = 0;
    public static final int BUGREPORT_OPTION_INTERACTIVE = 1;
    public static final int BUGREPORT_OPTION_REMOTE = 2;
    public static final int BUGREPORT_OPTION_TELEPHONY = 4;
    public static final int BUGREPORT_OPTION_WEAR = 3;
    public static final int COMPAT_MODE_ALWAYS = -1;
    public static final int COMPAT_MODE_DISABLED = 0;
    public static final int COMPAT_MODE_ENABLED = 1;
    public static final int COMPAT_MODE_NEVER = -2;
    public static final int COMPAT_MODE_TOGGLE = 2;
    public static final int COMPAT_MODE_UNKNOWN = -3;
    private static final boolean DEVELOPMENT_FORCE_LOW_RAM = SystemProperties.getBoolean("debug.force_low_ram", false);
    public static final int DOCKED_STACK_CREATE_MODE_BOTTOM_OR_RIGHT = 1;
    public static final int DOCKED_STACK_CREATE_MODE_TOP_OR_LEFT = 0;
    public static final boolean ENABLE_TASK_SNAPSHOTS = SystemProperties.getBoolean("persist.enable_task_snapshots", true);
    private static final int FIRST_START_FATAL_ERROR_CODE = -100;
    private static final int FIRST_START_NON_FATAL_ERROR_CODE = 100;
    private static final int FIRST_START_SUCCESS_CODE = 0;
    public static final int FLAG_AND_LOCKED = 2;
    public static final int FLAG_AND_UNLOCKED = 4;
    public static final int FLAG_AND_UNLOCKING_OR_UNLOCKED = 8;
    public static final int FLAG_OR_STOPPED = 1;
    private static final Singleton IActivityManagerSingleton = new Singleton() {

        protected IActivityManager create()
        {
            return IActivityManager.Stub.asInterface(ServiceManager.getService("activity"));
        }

        protected volatile Object create()
        {
            return create();
        }

    }
;
    public static final int INTENT_SENDER_ACTIVITY = 2;
    public static final int INTENT_SENDER_ACTIVITY_RESULT = 3;
    public static final int INTENT_SENDER_BROADCAST = 1;
    public static final int INTENT_SENDER_FOREGROUND_SERVICE = 5;
    public static final int INTENT_SENDER_SERVICE = 4;
    private static final int LAST_START_FATAL_ERROR_CODE = -1;
    private static final int LAST_START_NON_FATAL_ERROR_CODE = 199;
    private static final int LAST_START_SUCCESS_CODE = 99;
    public static final int LOCK_TASK_MODE_LOCKED = 1;
    public static final int LOCK_TASK_MODE_NONE = 0;
    public static final int LOCK_TASK_MODE_PINNED = 2;
    public static final int MAX_PROCESS_STATE = 18;
    public static final String META_HOME_ALTERNATE = "android.app.home.alternate";
    public static final int MIN_PROCESS_STATE = 0;
    public static final int MOVE_TASK_NO_USER_ACTION = 2;
    public static final int MOVE_TASK_WITH_HOME = 1;
    public static final int PROCESS_STATE_BACKUP = 9;
    public static final int PROCESS_STATE_BOUND_FOREGROUND_SERVICE = 3;
    public static final int PROCESS_STATE_CACHED_ACTIVITY = 15;
    public static final int PROCESS_STATE_CACHED_ACTIVITY_CLIENT = 16;
    public static final int PROCESS_STATE_CACHED_EMPTY = 17;
    public static final int PROCESS_STATE_FOREGROUND_SERVICE = 4;
    public static final int PROCESS_STATE_HEAVY_WEIGHT = 10;
    public static final int PROCESS_STATE_HOME = 13;
    public static final int PROCESS_STATE_IMPORTANT_BACKGROUND = 7;
    public static final int PROCESS_STATE_IMPORTANT_FOREGROUND = 6;
    public static final int PROCESS_STATE_LAST_ACTIVITY = 14;
    public static final int PROCESS_STATE_NONEXISTENT = 18;
    public static final int PROCESS_STATE_PERSISTENT = 0;
    public static final int PROCESS_STATE_PERSISTENT_UI = 1;
    public static final int PROCESS_STATE_RECEIVER = 12;
    public static final int PROCESS_STATE_SERVICE = 11;
    public static final int PROCESS_STATE_TOP = 2;
    public static final int PROCESS_STATE_TOP_SLEEPING = 5;
    public static final int PROCESS_STATE_TRANSIENT_BACKGROUND = 8;
    public static final int PROCESS_STATE_UNKNOWN = -1;
    public static final int RECENT_IGNORE_HOME_AND_RECENTS_STACK_TASKS = 8;
    public static final int RECENT_IGNORE_UNAVAILABLE = 2;
    public static final int RECENT_INCLUDE_PROFILES = 4;
    public static final int RECENT_INGORE_DOCKED_STACK_TOP_TASK = 16;
    public static final int RECENT_INGORE_PINNED_STACK_TASKS = 32;
    public static final int RECENT_WITH_EXCLUDED = 1;
    public static final int RESIZE_MODE_FORCED = 2;
    public static final int RESIZE_MODE_PRESERVE_WINDOW = 1;
    public static final int RESIZE_MODE_SYSTEM = 0;
    public static final int RESIZE_MODE_SYSTEM_SCREEN_ROTATION = 1;
    public static final int RESIZE_MODE_USER = 1;
    public static final int RESIZE_MODE_USER_FORCED = 3;
    public static final int START_ABORTED = 102;
    public static final int START_ASSISTANT_HIDDEN_SESSION = -90;
    public static final int START_ASSISTANT_NOT_ACTIVE_SESSION = -89;
    public static final int START_CANCELED = -96;
    public static final int START_CLASS_NOT_FOUND = -92;
    public static final int START_DELIVERED_TO_TOP = 3;
    public static final int START_FLAG_DEBUG = 2;
    public static final int START_FLAG_NATIVE_DEBUGGING = 8;
    public static final int START_FLAG_ONLY_IF_NEEDED = 1;
    public static final int START_FLAG_TRACK_ALLOCATION = 4;
    public static final int START_FORWARD_AND_REQUEST_CONFLICT = -93;
    public static final int START_INTENT_NOT_RESOLVED = -91;
    public static final int START_NOT_ACTIVITY = -95;
    public static final int START_NOT_CURRENT_USER_ACTIVITY = -98;
    public static final int START_NOT_VOICE_COMPATIBLE = -97;
    public static final int START_PERMISSION_DENIED = -94;
    public static final int START_RETURN_INTENT_TO_CALLER = 1;
    public static final int START_RETURN_LOCK_TASK_MODE_VIOLATION = 101;
    public static final int START_SUCCESS = 0;
    public static final int START_SWITCHES_CANCELED = 100;
    public static final int START_TASK_TO_FRONT = 2;
    public static final int START_VOICE_HIDDEN_SESSION = -100;
    public static final int START_VOICE_NOT_ACTIVE_SESSION = -99;
    private static String TAG = "ActivityManager";
    public static final int UID_OBSERVER_ACTIVE = 8;
    public static final int UID_OBSERVER_CACHED = 16;
    public static final int UID_OBSERVER_GONE = 2;
    public static final int UID_OBSERVER_IDLE = 4;
    public static final int UID_OBSERVER_PROCSTATE = 1;
    public static final int USER_OP_ERROR_IS_SYSTEM = -3;
    public static final int USER_OP_ERROR_RELATED_USERS_CANNOT_STOP = -4;
    public static final int USER_OP_IS_CURRENT = -2;
    public static final int USER_OP_SUCCESS = 0;
    public static final int USER_OP_UNKNOWN_USER = -1;
    private static int gMaxRecentTasks = -1;
    private static volatile boolean sSystemReady = false;
    Point mAppTaskThumbnailSize;
    private final Context mContext;
    final ArrayMap mImportanceListeners = new ArrayMap();

}
