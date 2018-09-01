// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.app.AppGlobals;
import android.content.Context;
import android.util.Log;
import com.android.internal.util.FastPrintWriter;
import com.android.internal.util.TypedProperties;
import dalvik.bytecode.OpcodeInfo;
import dalvik.system.VMDebug;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.apache.harmony.dalvik.ddmc.*;

// Referenced classes of package android.os:
//            RemoteException, ServiceManager, IBinder, Environment, 
//            Parcelable, Parcel

public final class Debug
{
    public static interface DebugProperty
        extends Annotation
    {
    }

    public static class InstructionCount
    {

        public boolean collect()
        {
            try
            {
                VMDebug.stopInstructionCounting();
                VMDebug.getInstructionCount(mCounts);
            }
            catch(UnsupportedOperationException unsupportedoperationexception)
            {
                return false;
            }
            return true;
        }

        public int globalMethodInvocations()
        {
            int i = 0;
            for(int j = 0; j < NUM_INSTR;)
            {
                int k = i;
                if(OpcodeInfo.isInvoke(j))
                    k = i + mCounts[j];
                j++;
                i = k;
            }

            return i;
        }

        public int globalTotal()
        {
            int i = 0;
            for(int j = 0; j < NUM_INSTR; j++)
                i += mCounts[j];

            return i;
        }

        public boolean resetAndStart()
        {
            try
            {
                VMDebug.startInstructionCounting();
                VMDebug.resetInstructionCount();
            }
            catch(UnsupportedOperationException unsupportedoperationexception)
            {
                return false;
            }
            return true;
        }

        private static final int NUM_INSTR;
        private int mCounts[];

        static 
        {
            NUM_INSTR = OpcodeInfo.MAXIMUM_PACKED_VALUE + 1;
        }

        public InstructionCount()
        {
            mCounts = new int[NUM_INSTR];
        }
    }

    public static class MemoryInfo
        implements Parcelable
    {

        public static String getOtherLabel(int i)
        {
            switch(i)
            {
            default:
                return "????";

            case 0: // '\0'
                return "Dalvik Other";

            case 1: // '\001'
                return "Stack";

            case 2: // '\002'
                return "Cursor";

            case 3: // '\003'
                return "Ashmem";

            case 4: // '\004'
                return "Gfx dev";

            case 5: // '\005'
                return "Other dev";

            case 6: // '\006'
                return ".so mmap";

            case 7: // '\007'
                return ".jar mmap";

            case 8: // '\b'
                return ".apk mmap";

            case 9: // '\t'
                return ".ttf mmap";

            case 10: // '\n'
                return ".dex mmap";

            case 11: // '\013'
                return ".oat mmap";

            case 12: // '\f'
                return ".art mmap";

            case 13: // '\r'
                return "Other mmap";

            case 14: // '\016'
                return "EGL mtrack";

            case 15: // '\017'
                return "GL mtrack";

            case 16: // '\020'
                return "Other mtrack";

            case 17: // '\021'
                return ".Heap";

            case 18: // '\022'
                return ".LOS";

            case 19: // '\023'
                return ".Zygote";

            case 20: // '\024'
                return ".NonMoving";

            case 21: // '\025'
                return ".LinearAlloc";

            case 22: // '\026'
                return ".GC";

            case 23: // '\027'
                return ".JITCache";

            case 24: // '\030'
                return ".CompilerMetadata";

            case 25: // '\031'
                return ".IndirectRef";

            case 26: // '\032'
                return ".Boot vdex";

            case 27: // '\033'
                return ".App dex";

            case 28: // '\034'
                return ".App vdex";

            case 29: // '\035'
                return ".App art";

            case 30: // '\036'
                return ".Boot art";
            }
        }

        public int describeContents()
        {
            return 0;
        }

        public String getMemoryStat(String s)
        {
            if(s.equals("summary.java-heap"))
                return Integer.toString(getSummaryJavaHeap());
            if(s.equals("summary.native-heap"))
                return Integer.toString(getSummaryNativeHeap());
            if(s.equals("summary.code"))
                return Integer.toString(getSummaryCode());
            if(s.equals("summary.stack"))
                return Integer.toString(getSummaryStack());
            if(s.equals("summary.graphics"))
                return Integer.toString(getSummaryGraphics());
            if(s.equals("summary.private-other"))
                return Integer.toString(getSummaryPrivateOther());
            if(s.equals("summary.system"))
                return Integer.toString(getSummarySystem());
            if(s.equals("summary.total-pss"))
                return Integer.toString(getSummaryTotalPss());
            if(s.equals("summary.total-swap"))
                return Integer.toString(getSummaryTotalSwap());
            else
                return null;
        }

        public Map getMemoryStats()
        {
            HashMap hashmap = new HashMap();
            hashmap.put("summary.java-heap", Integer.toString(getSummaryJavaHeap()));
            hashmap.put("summary.native-heap", Integer.toString(getSummaryNativeHeap()));
            hashmap.put("summary.code", Integer.toString(getSummaryCode()));
            hashmap.put("summary.stack", Integer.toString(getSummaryStack()));
            hashmap.put("summary.graphics", Integer.toString(getSummaryGraphics()));
            hashmap.put("summary.private-other", Integer.toString(getSummaryPrivateOther()));
            hashmap.put("summary.system", Integer.toString(getSummarySystem()));
            hashmap.put("summary.total-pss", Integer.toString(getSummaryTotalPss()));
            hashmap.put("summary.total-swap", Integer.toString(getSummaryTotalSwap()));
            return hashmap;
        }

        public int getOtherPrivate(int i)
        {
            return getOtherPrivateClean(i) + getOtherPrivateDirty(i);
        }

        public int getOtherPrivateClean(int i)
        {
            return otherStats[i * 8 + 4];
        }

        public int getOtherPrivateDirty(int i)
        {
            return otherStats[i * 8 + 2];
        }

        public int getOtherPss(int i)
        {
            return otherStats[i * 8 + 0];
        }

        public int getOtherSharedClean(int i)
        {
            return otherStats[i * 8 + 5];
        }

        public int getOtherSharedDirty(int i)
        {
            return otherStats[i * 8 + 3];
        }

        public int getOtherSwappablePss(int i)
        {
            return otherStats[i * 8 + 1];
        }

        public int getOtherSwappedOut(int i)
        {
            return otherStats[i * 8 + 6];
        }

        public int getOtherSwappedOutPss(int i)
        {
            return otherStats[i * 8 + 7];
        }

        public int getSummaryCode()
        {
            return getOtherPrivate(6) + getOtherPrivate(7) + getOtherPrivate(8) + getOtherPrivate(9) + getOtherPrivate(10) + getOtherPrivate(11);
        }

        public int getSummaryGraphics()
        {
            return getOtherPrivate(4) + getOtherPrivate(14) + getOtherPrivate(15);
        }

        public int getSummaryJavaHeap()
        {
            return dalvikPrivateDirty + getOtherPrivate(12);
        }

        public int getSummaryNativeHeap()
        {
            return nativePrivateDirty;
        }

        public int getSummaryPrivateOther()
        {
            return (getTotalPrivateClean() + getTotalPrivateDirty()) - getSummaryJavaHeap() - getSummaryNativeHeap() - getSummaryCode() - getSummaryStack() - getSummaryGraphics();
        }

        public int getSummaryStack()
        {
            return getOtherPrivateDirty(1);
        }

        public int getSummarySystem()
        {
            return getTotalPss() - getTotalPrivateClean() - getTotalPrivateDirty();
        }

        public int getSummaryTotalPss()
        {
            return getTotalPss();
        }

        public int getSummaryTotalSwap()
        {
            return getTotalSwappedOut();
        }

        public int getSummaryTotalSwapPss()
        {
            return getTotalSwappedOutPss();
        }

        public int getTotalPrivateClean()
        {
            return dalvikPrivateClean + nativePrivateClean + otherPrivateClean;
        }

        public int getTotalPrivateDirty()
        {
            return dalvikPrivateDirty + nativePrivateDirty + otherPrivateDirty;
        }

        public int getTotalPss()
        {
            return dalvikPss + nativePss + otherPss + getTotalSwappedOutPss();
        }

        public int getTotalSharedClean()
        {
            return dalvikSharedClean + nativeSharedClean + otherSharedClean;
        }

        public int getTotalSharedDirty()
        {
            return dalvikSharedDirty + nativeSharedDirty + otherSharedDirty;
        }

        public int getTotalSwappablePss()
        {
            return dalvikSwappablePss + nativeSwappablePss + otherSwappablePss;
        }

        public int getTotalSwappedOut()
        {
            return dalvikSwappedOut + nativeSwappedOut + otherSwappedOut;
        }

        public int getTotalSwappedOutPss()
        {
            return dalvikSwappedOutPss + nativeSwappedOutPss + otherSwappedOutPss;
        }

        public int getTotalUss()
        {
            return dalvikPrivateClean + dalvikPrivateDirty + nativePrivateClean + nativePrivateDirty + otherPrivateClean + otherPrivateDirty;
        }

        public boolean hasSwappedOutPss()
        {
            return hasSwappedOutPss;
        }

        public void readFromParcel(Parcel parcel)
        {
            boolean flag = false;
            dalvikPss = parcel.readInt();
            dalvikSwappablePss = parcel.readInt();
            dalvikPrivateDirty = parcel.readInt();
            dalvikSharedDirty = parcel.readInt();
            dalvikPrivateClean = parcel.readInt();
            dalvikSharedClean = parcel.readInt();
            dalvikSwappedOut = parcel.readInt();
            dalvikSwappedOutPss = parcel.readInt();
            nativePss = parcel.readInt();
            nativeSwappablePss = parcel.readInt();
            nativePrivateDirty = parcel.readInt();
            nativeSharedDirty = parcel.readInt();
            nativePrivateClean = parcel.readInt();
            nativeSharedClean = parcel.readInt();
            nativeSwappedOut = parcel.readInt();
            nativeSwappedOutPss = parcel.readInt();
            otherPss = parcel.readInt();
            otherSwappablePss = parcel.readInt();
            otherPrivateDirty = parcel.readInt();
            otherSharedDirty = parcel.readInt();
            otherPrivateClean = parcel.readInt();
            otherSharedClean = parcel.readInt();
            otherSwappedOut = parcel.readInt();
            if(parcel.readInt() != 0)
                flag = true;
            hasSwappedOutPss = flag;
            otherSwappedOutPss = parcel.readInt();
            otherStats = parcel.createIntArray();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(dalvikPss);
            parcel.writeInt(dalvikSwappablePss);
            parcel.writeInt(dalvikPrivateDirty);
            parcel.writeInt(dalvikSharedDirty);
            parcel.writeInt(dalvikPrivateClean);
            parcel.writeInt(dalvikSharedClean);
            parcel.writeInt(dalvikSwappedOut);
            parcel.writeInt(dalvikSwappedOutPss);
            parcel.writeInt(nativePss);
            parcel.writeInt(nativeSwappablePss);
            parcel.writeInt(nativePrivateDirty);
            parcel.writeInt(nativeSharedDirty);
            parcel.writeInt(nativePrivateClean);
            parcel.writeInt(nativeSharedClean);
            parcel.writeInt(nativeSwappedOut);
            parcel.writeInt(nativeSwappedOutPss);
            parcel.writeInt(otherPss);
            parcel.writeInt(otherSwappablePss);
            parcel.writeInt(otherPrivateDirty);
            parcel.writeInt(otherSharedDirty);
            parcel.writeInt(otherPrivateClean);
            parcel.writeInt(otherSharedClean);
            parcel.writeInt(otherSwappedOut);
            if(hasSwappedOutPss)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(otherSwappedOutPss);
            parcel.writeIntArray(otherStats);
        }

        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

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
        public static final int HEAP_DALVIK = 1;
        public static final int HEAP_NATIVE = 2;
        public static final int HEAP_UNKNOWN = 0;
        public static final int NUM_CATEGORIES = 8;
        public static final int NUM_DVK_STATS = 14;
        public static final int NUM_OTHER_STATS = 17;
        public static final int OTHER_APK = 8;
        public static final int OTHER_ART = 12;
        public static final int OTHER_ART_APP = 29;
        public static final int OTHER_ART_BOOT = 30;
        public static final int OTHER_ASHMEM = 3;
        public static final int OTHER_CURSOR = 2;
        public static final int OTHER_DALVIK_LARGE = 18;
        public static final int OTHER_DALVIK_NON_MOVING = 20;
        public static final int OTHER_DALVIK_NORMAL = 17;
        public static final int OTHER_DALVIK_OTHER = 0;
        public static final int OTHER_DALVIK_OTHER_ACCOUNTING = 22;
        public static final int OTHER_DALVIK_OTHER_CODE_CACHE = 23;
        public static final int OTHER_DALVIK_OTHER_COMPILER_METADATA = 24;
        public static final int OTHER_DALVIK_OTHER_INDIRECT_REFERENCE_TABLE = 25;
        public static final int OTHER_DALVIK_OTHER_LINEARALLOC = 21;
        public static final int OTHER_DALVIK_ZYGOTE = 19;
        public static final int OTHER_DEX = 10;
        public static final int OTHER_DEX_APP_DEX = 27;
        public static final int OTHER_DEX_APP_VDEX = 28;
        public static final int OTHER_DEX_BOOT_VDEX = 26;
        public static final int OTHER_DVK_STAT_ART_END = 13;
        public static final int OTHER_DVK_STAT_ART_START = 12;
        public static final int OTHER_DVK_STAT_DALVIK_END = 3;
        public static final int OTHER_DVK_STAT_DALVIK_OTHER_END = 8;
        public static final int OTHER_DVK_STAT_DALVIK_OTHER_START = 4;
        public static final int OTHER_DVK_STAT_DALVIK_START = 0;
        public static final int OTHER_DVK_STAT_DEX_END = 11;
        public static final int OTHER_DVK_STAT_DEX_START = 9;
        public static final int OTHER_GL = 15;
        public static final int OTHER_GL_DEV = 4;
        public static final int OTHER_GRAPHICS = 14;
        public static final int OTHER_JAR = 7;
        public static final int OTHER_OAT = 11;
        public static final int OTHER_OTHER_MEMTRACK = 16;
        public static final int OTHER_SO = 6;
        public static final int OTHER_STACK = 1;
        public static final int OTHER_TTF = 9;
        public static final int OTHER_UNKNOWN_DEV = 5;
        public static final int OTHER_UNKNOWN_MAP = 13;
        public static final int offsetPrivateClean = 4;
        public static final int offsetPrivateDirty = 2;
        public static final int offsetPss = 0;
        public static final int offsetSharedClean = 5;
        public static final int offsetSharedDirty = 3;
        public static final int offsetSwappablePss = 1;
        public static final int offsetSwappedOut = 6;
        public static final int offsetSwappedOutPss = 7;
        public int dalvikPrivateClean;
        public int dalvikPrivateDirty;
        public int dalvikPss;
        public int dalvikSharedClean;
        public int dalvikSharedDirty;
        public int dalvikSwappablePss;
        public int dalvikSwappedOut;
        public int dalvikSwappedOutPss;
        public boolean hasSwappedOutPss;
        public int nativePrivateClean;
        public int nativePrivateDirty;
        public int nativePss;
        public int nativeSharedClean;
        public int nativeSharedDirty;
        public int nativeSwappablePss;
        public int nativeSwappedOut;
        public int nativeSwappedOutPss;
        public int otherPrivateClean;
        public int otherPrivateDirty;
        public int otherPss;
        public int otherSharedClean;
        public int otherSharedDirty;
        private int otherStats[];
        public int otherSwappablePss;
        public int otherSwappedOut;
        public int otherSwappedOutPss;


        public MemoryInfo()
        {
            otherStats = new int[248];
        }

        private MemoryInfo(Parcel parcel)
        {
            otherStats = new int[248];
            readFromParcel(parcel);
        }

        MemoryInfo(Parcel parcel, MemoryInfo memoryinfo)
        {
            this(parcel);
        }
    }


    private Debug()
    {
    }

    public static final boolean cacheRegisterMap(String s)
    {
        return VMDebug.cacheRegisterMap(s);
    }

    public static void changeDebugPort(int i)
    {
    }

    public static long countInstancesOfClass(Class class1)
    {
        return VMDebug.countInstancesOfClass(class1, true);
    }

    public static void dumpHprofData(String s)
        throws IOException
    {
        VMDebug.dumpHprofData(s);
    }

    public static void dumpHprofData(String s, FileDescriptor filedescriptor)
        throws IOException
    {
        VMDebug.dumpHprofData(s, filedescriptor);
    }

    public static void dumpHprofDataDdms()
    {
        VMDebug.dumpHprofDataDdms();
    }

    public static native boolean dumpJavaBacktraceToFileTimeout(int i, String s, int j);

    public static native boolean dumpNativeBacktraceToFileTimeout(int i, String s, int j);

    public static native void dumpNativeHeap(FileDescriptor filedescriptor);

    public static native void dumpNativeMallocInfo(FileDescriptor filedescriptor);

    public static final void dumpReferenceTables()
    {
        VMDebug.dumpReferenceTables();
    }

    public static boolean dumpService(String s, FileDescriptor filedescriptor, String as[])
    {
        IBinder ibinder = ServiceManager.getService(s);
        if(ibinder == null)
        {
            Log.e("Debug", (new StringBuilder()).append("Can't find service to dump: ").append(s).toString());
            return false;
        }
        try
        {
            ibinder.dump(filedescriptor, as);
        }
        // Misplaced declaration of an exception variable
        catch(FileDescriptor filedescriptor)
        {
            Log.e("Debug", (new StringBuilder()).append("Can't dump service: ").append(s).toString(), filedescriptor);
            return false;
        }
        return true;
    }

    public static void enableEmulatorTraceOutput()
    {
        VMDebug.startEmulatorTracing();
    }

    private static boolean fieldTypeMatches(Field field, Class class1)
    {
        field = field.getType();
        if(field == class1)
            return true;
        boolean flag;
        try
        {
            class1 = class1.getField("TYPE");
        }
        // Misplaced declaration of an exception variable
        catch(Field field)
        {
            return false;
        }
        try
        {
            class1 = (Class)class1.get(null);
        }
        // Misplaced declaration of an exception variable
        catch(Field field)
        {
            return false;
        }
        if(field == class1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static String fixTracePath(String s)
    {
label0:
        {
            Object obj;
            if(s != null)
            {
                obj = s;
                if(s.charAt(0) == '/')
                    break label0;
            }
            obj = AppGlobals.getInitialApplication();
            if(obj != null)
                obj = ((Context) (obj)).getExternalFilesDir(null);
            else
                obj = Environment.getExternalStorageDirectory();
            if(s == null)
                obj = (new File(((File) (obj)), "dmtrace")).getAbsolutePath();
            else
                obj = (new File(((File) (obj)), s)).getAbsolutePath();
        }
        s = ((String) (obj));
        if(!((String) (obj)).endsWith(".trace"))
            s = (new StringBuilder()).append(((String) (obj))).append(".trace").toString();
        return s;
    }

    public static final native int getBinderDeathObjectCount();

    public static final native int getBinderLocalObjectCount();

    public static final native int getBinderProxyObjectCount();

    public static native int getBinderReceivedTransactions();

    public static native int getBinderSentTransactions();

    public static String getCaller()
    {
        return getCaller(Thread.currentThread().getStackTrace(), 0);
    }

    private static String getCaller(StackTraceElement astacktraceelement[], int i)
    {
        if(i + 4 >= astacktraceelement.length)
        {
            return "<bottom of call stack>";
        } else
        {
            astacktraceelement = astacktraceelement[i + 4];
            return (new StringBuilder()).append(astacktraceelement.getClassName()).append(".").append(astacktraceelement.getMethodName()).append(":").append(astacktraceelement.getLineNumber()).toString();
        }
    }

    public static String getCallers(int i)
    {
        StackTraceElement astacktraceelement[] = Thread.currentThread().getStackTrace();
        StringBuffer stringbuffer = new StringBuffer();
        for(int j = 0; j < i; j++)
            stringbuffer.append(getCaller(astacktraceelement, j)).append(" ");

        return stringbuffer.toString();
    }

    public static String getCallers(int i, int j)
    {
        StackTraceElement astacktraceelement[] = Thread.currentThread().getStackTrace();
        StringBuffer stringbuffer = new StringBuffer();
        for(int k = i; k < j + i; k++)
            stringbuffer.append(getCaller(astacktraceelement, k)).append(" ");

        return stringbuffer.toString();
    }

    public static String getCallers(int i, String s)
    {
        StackTraceElement astacktraceelement[] = Thread.currentThread().getStackTrace();
        StringBuffer stringbuffer = new StringBuffer();
        for(int j = 0; j < i; j++)
            stringbuffer.append(s).append(getCaller(astacktraceelement, j)).append("\n");

        return stringbuffer.toString();
    }

    public static int getGlobalAllocCount()
    {
        return VMDebug.getAllocCount(1);
    }

    public static int getGlobalAllocSize()
    {
        return VMDebug.getAllocCount(2);
    }

    public static int getGlobalClassInitCount()
    {
        return VMDebug.getAllocCount(32);
    }

    public static int getGlobalClassInitTime()
    {
        return VMDebug.getAllocCount(64);
    }

    public static int getGlobalExternalAllocCount()
    {
        return 0;
    }

    public static int getGlobalExternalAllocSize()
    {
        return 0;
    }

    public static int getGlobalExternalFreedCount()
    {
        return 0;
    }

    public static int getGlobalExternalFreedSize()
    {
        return 0;
    }

    public static int getGlobalFreedCount()
    {
        return VMDebug.getAllocCount(4);
    }

    public static int getGlobalFreedSize()
    {
        return VMDebug.getAllocCount(8);
    }

    public static int getGlobalGcInvocationCount()
    {
        return VMDebug.getAllocCount(16);
    }

    public static int getLoadedClassCount()
    {
        return VMDebug.getLoadedClassCount();
    }

    public static native void getMemInfo(long al[]);

    public static native void getMemoryInfo(int i, MemoryInfo memoryinfo);

    public static native void getMemoryInfo(MemoryInfo memoryinfo);

    public static int getMethodTracingMode()
    {
        return VMDebug.getMethodTracingMode();
    }

    public static native long getNativeHeapAllocatedSize();

    public static native long getNativeHeapFreeSize();

    public static native long getNativeHeapSize();

    public static native long getPss();

    public static native long getPss(int i, long al[], long al1[]);

    public static String getRuntimeStat(String s)
    {
        return VMDebug.getRuntimeStat(s);
    }

    public static Map getRuntimeStats()
    {
        return VMDebug.getRuntimeStats();
    }

    public static int getThreadAllocCount()
    {
        return VMDebug.getAllocCount(0x10000);
    }

    public static int getThreadAllocSize()
    {
        return VMDebug.getAllocCount(0x20000);
    }

    public static int getThreadExternalAllocCount()
    {
        return 0;
    }

    public static int getThreadExternalAllocSize()
    {
        return 0;
    }

    public static int getThreadGcInvocationCount()
    {
        return VMDebug.getAllocCount(0x100000);
    }

    public static native String getUnreachableMemory(int i, boolean flag);

    public static String[] getVmFeatureList()
    {
        return VMDebug.getVmFeatureList();
    }

    public static boolean isDebuggerConnected()
    {
        return VMDebug.isDebuggerConnected();
    }

    private static void modifyFieldIfSet(Field field, TypedProperties typedproperties, String s)
    {
        if(field.getType() == java/lang/String)
        {
            int i = typedproperties.getStringInfo(s);
            switch(i)
            {
            default:
                throw new IllegalStateException((new StringBuilder()).append("Unexpected getStringInfo(").append(s).append(") return value ").append(i).toString());

            case 0: // '\0'
                try
                {
                    field.set(null, null);
                    return;
                }
                // Misplaced declaration of an exception variable
                catch(Field field)
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Cannot set field for ").append(s).toString(), field);
                }

            case -1: 
                return;

            case -2: 
                throw new IllegalArgumentException((new StringBuilder()).append("Type of ").append(s).append(" ").append(" does not match field type (").append(field.getType()).append(")").toString());

            case 1: // '\001'
                break;
            }
        }
        typedproperties = ((TypedProperties) (typedproperties.get(s)));
        if(typedproperties == null)
            break MISSING_BLOCK_LABEL_270;
        if(!fieldTypeMatches(field, typedproperties.getClass()))
            throw new IllegalArgumentException((new StringBuilder()).append("Type of ").append(s).append(" (").append(typedproperties.getClass()).append(") ").append(" does not match field type (").append(field.getType()).append(")").toString());
        field.set(null, typedproperties);
        return;
        field;
        throw new IllegalArgumentException((new StringBuilder()).append("Cannot set field for ").append(s).toString(), field);
    }

    public static void printLoadedClasses(int i)
    {
        VMDebug.printLoadedClasses(i);
    }

    public static void resetAllCounts()
    {
        VMDebug.resetAllocCount(-1);
    }

    public static void resetGlobalAllocCount()
    {
        VMDebug.resetAllocCount(1);
    }

    public static void resetGlobalAllocSize()
    {
        VMDebug.resetAllocCount(2);
    }

    public static void resetGlobalClassInitCount()
    {
        VMDebug.resetAllocCount(32);
    }

    public static void resetGlobalClassInitTime()
    {
        VMDebug.resetAllocCount(64);
    }

    public static void resetGlobalExternalAllocCount()
    {
    }

    public static void resetGlobalExternalAllocSize()
    {
    }

    public static void resetGlobalExternalFreedCount()
    {
    }

    public static void resetGlobalExternalFreedSize()
    {
    }

    public static void resetGlobalFreedCount()
    {
        VMDebug.resetAllocCount(4);
    }

    public static void resetGlobalFreedSize()
    {
        VMDebug.resetAllocCount(8);
    }

    public static void resetGlobalGcInvocationCount()
    {
        VMDebug.resetAllocCount(16);
    }

    public static void resetThreadAllocCount()
    {
        VMDebug.resetAllocCount(0x10000);
    }

    public static void resetThreadAllocSize()
    {
        VMDebug.resetAllocCount(0x20000);
    }

    public static void resetThreadExternalAllocCount()
    {
    }

    public static void resetThreadExternalAllocSize()
    {
    }

    public static void resetThreadGcInvocationCount()
    {
        VMDebug.resetAllocCount(0x100000);
    }

    public static int setAllocationLimit(int i)
    {
        return -1;
    }

    public static void setFieldsOn(Class class1)
    {
        setFieldsOn(class1, false);
    }

    public static void setFieldsOn(Class class1, boolean flag)
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("setFieldsOn(");
        if(class1 == null)
            class1 = "null";
        else
            class1 = class1.getName();
        Log.wtf("Debug", stringbuilder.append(class1).append(") called in non-DEBUG build").toString());
    }

    public static int setGlobalAllocationLimit(int i)
    {
        return -1;
    }

    public static void startAllocCounting()
    {
        VMDebug.startAllocCounting();
    }

    public static void startMethodTracing()
    {
        VMDebug.startMethodTracing(fixTracePath(null), 0, 0, false, 0);
    }

    public static void startMethodTracing(String s)
    {
        startMethodTracing(s, 0, 0);
    }

    public static void startMethodTracing(String s, int i)
    {
        startMethodTracing(s, i, 0);
    }

    public static void startMethodTracing(String s, int i, int j)
    {
        VMDebug.startMethodTracing(fixTracePath(s), i, j, false, 0);
    }

    public static void startMethodTracing(String s, FileDescriptor filedescriptor, int i, int j, boolean flag)
    {
        VMDebug.startMethodTracing(s, filedescriptor, i, j, false, 0, flag);
    }

    public static void startMethodTracingDdms(int i, int j, boolean flag, int k)
    {
        VMDebug.startMethodTracingDdms(i, j, flag, k);
    }

    public static void startMethodTracingSampling(String s, int i, int j)
    {
        VMDebug.startMethodTracing(fixTracePath(s), i, 0, true, j);
    }

    public static void startNativeTracing()
    {
        Object obj;
        Object obj1;
        obj = null;
        obj1 = null;
        Object obj3;
        FileOutputStream fileoutputstream = JVM INSTR new #458 <Class FileOutputStream>;
        fileoutputstream.FileOutputStream("/sys/qemu_trace/state");
        obj3 = JVM INSTR new #461 <Class FastPrintWriter>;
        ((FastPrintWriter) (obj3)).FastPrintWriter(fileoutputstream);
        ((PrintWriter) (obj3)).println("1");
        if(obj3 != null)
            ((PrintWriter) (obj3)).close();
_L2:
        VMDebug.startEmulatorTracing();
        return;
        obj3;
        obj3 = obj1;
_L5:
        if(obj3 != null)
            ((PrintWriter) (obj3)).close();
        if(true) goto _L2; else goto _L1
_L1:
        Object obj2;
        obj2;
        obj3 = obj;
_L4:
        if(obj3 != null)
            ((PrintWriter) (obj3)).close();
        throw obj2;
        obj2;
        if(true) goto _L4; else goto _L3
_L3:
        obj2;
          goto _L5
    }

    public static void stopAllocCounting()
    {
        VMDebug.stopAllocCounting();
    }

    public static void stopMethodTracing()
    {
        VMDebug.stopMethodTracing();
    }

    public static void stopNativeTracing()
    {
        Object obj;
        Object obj1;
        VMDebug.stopEmulatorTracing();
        obj = null;
        obj1 = null;
        Object obj3;
        FileOutputStream fileoutputstream = JVM INSTR new #458 <Class FileOutputStream>;
        fileoutputstream.FileOutputStream("/sys/qemu_trace/state");
        obj3 = JVM INSTR new #461 <Class FastPrintWriter>;
        ((FastPrintWriter) (obj3)).FastPrintWriter(fileoutputstream);
        ((PrintWriter) (obj3)).println("0");
        if(obj3 != null)
            ((PrintWriter) (obj3)).close();
_L2:
        return;
        obj3;
        obj3 = obj1;
_L5:
        if(obj3 != null)
            ((PrintWriter) (obj3)).close();
        if(true) goto _L2; else goto _L1
_L1:
        Object obj2;
        obj2;
        obj3 = obj;
_L4:
        if(obj3 != null)
            ((PrintWriter) (obj3)).close();
        throw obj2;
        obj2;
        if(true) goto _L4; else goto _L3
_L3:
        obj2;
          goto _L5
    }

    public static long threadCpuTimeNanos()
    {
        return VMDebug.threadCpuTimeNanos();
    }

    public static void waitForDebugger()
    {
        if(!VMDebug.isDebuggingEnabled())
            return;
        if(isDebuggerConnected())
            return;
        System.out.println("Sending WAIT chunk");
        DdmServer.sendChunk(new Chunk(ChunkHandler.type("WAIT"), new byte[] {
            0
        }, 0, 1));
        mWaiting = true;
        while(!isDebuggerConnected()) 
            try
            {
                Thread.sleep(200L);
            }
            catch(InterruptedException interruptedexception) { }
        mWaiting = false;
        System.out.println("Debugger has connected");
_L3:
        long l = VMDebug.lastDebuggerActivity();
        if(l >= 0L) goto _L2; else goto _L1
_L1:
        System.out.println("debugger detached?");
_L4:
        return;
_L2:
label0:
        {
            if(l >= 1300L)
                break label0;
            System.out.println("waiting for debugger to settle...");
            try
            {
                Thread.sleep(200L);
            }
            catch(InterruptedException interruptedexception1) { }
        }
          goto _L3
        System.out.println((new StringBuilder()).append("debugger has settled (").append(l).append(")").toString());
          goto _L4
    }

    public static boolean waitingForDebugger()
    {
        return mWaiting;
    }

    private static final String DEFAULT_TRACE_BODY = "dmtrace";
    private static final String DEFAULT_TRACE_EXTENSION = ".trace";
    public static final int MEMINFO_BUFFERS = 2;
    public static final int MEMINFO_CACHED = 3;
    public static final int MEMINFO_COUNT = 15;
    public static final int MEMINFO_FREE = 1;
    public static final int MEMINFO_KERNEL_STACK = 14;
    public static final int MEMINFO_MAPPED = 11;
    public static final int MEMINFO_PAGE_TABLES = 13;
    public static final int MEMINFO_SHMEM = 4;
    public static final int MEMINFO_SLAB = 5;
    public static final int MEMINFO_SLAB_RECLAIMABLE = 6;
    public static final int MEMINFO_SLAB_UNRECLAIMABLE = 7;
    public static final int MEMINFO_SWAP_FREE = 9;
    public static final int MEMINFO_SWAP_TOTAL = 8;
    public static final int MEMINFO_TOTAL = 0;
    public static final int MEMINFO_VM_ALLOC_USED = 12;
    public static final int MEMINFO_ZRAM_TOTAL = 10;
    private static final int MIN_DEBUGGER_IDLE = 1300;
    public static final int SHOW_CLASSLOADER = 2;
    public static final int SHOW_FULL_DETAIL = 1;
    public static final int SHOW_INITIALIZED = 4;
    private static final int SPIN_DELAY = 200;
    private static final String SYSFS_QEMU_TRACE_STATE = "/sys/qemu_trace/state";
    private static final String TAG = "Debug";
    public static final int TRACE_COUNT_ALLOCS = 1;
    private static final TypedProperties debugProperties = null;
    private static volatile boolean mWaiting = false;

}
