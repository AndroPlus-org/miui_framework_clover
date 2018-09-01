// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.SharedPreferences;
import android.os.*;
import android.system.*;
import android.util.Log;
import com.android.internal.util.ExponentiallyBucketedHistogram;
import com.android.internal.util.XmlUtils;
import com.google.android.collect.Maps;
import dalvik.system.BlockGuard;
import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import libcore.io.IoUtils;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.app:
//            QueuedWork, ContextImpl, ActivityThread

final class SharedPreferencesImpl
    implements SharedPreferences
{
    public final class EditorImpl
        implements android.content.SharedPreferences.Editor
    {

        static void _2D_wrap0(EditorImpl editorimpl, MemoryCommitResult memorycommitresult)
        {
            editorimpl.notifyListeners(memorycommitresult);
        }

        private MemoryCommitResult commitToMemory()
        {
            Object obj;
            HashSet hashset;
            obj = null;
            hashset = null;
            Object obj1 = SharedPreferencesImpl._2D_get3(SharedPreferencesImpl.this);
            obj1;
            JVM INSTR monitorenter ;
            Map map;
            if(SharedPreferencesImpl._2D_get1(SharedPreferencesImpl.this) > 0)
            {
                SharedPreferencesImpl sharedpreferencesimpl = SharedPreferencesImpl.this;
                HashMap hashmap = JVM INSTR new #64  <Class HashMap>;
                hashmap.HashMap(SharedPreferencesImpl._2D_get4(SharedPreferencesImpl.this));
                SharedPreferencesImpl._2D_set2(sharedpreferencesimpl, hashmap);
            }
            map = SharedPreferencesImpl._2D_get4(SharedPreferencesImpl.this);
            SharedPreferencesImpl sharedpreferencesimpl1 = SharedPreferencesImpl.this;
            SharedPreferencesImpl._2D_set1(sharedpreferencesimpl1, SharedPreferencesImpl._2D_get1(sharedpreferencesimpl1) + 1);
            Object obj2;
            boolean flag1;
            String s;
            Object obj4;
            boolean flag;
            boolean flag2;
            Iterator iterator;
            java.util.Map.Entry entry;
            if(SharedPreferencesImpl._2D_get2(SharedPreferencesImpl.this).size() > 0)
                flag = true;
            else
                flag = false;
            if(!flag)
                break MISSING_BLOCK_LABEL_130;
            obj = JVM INSTR new #91  <Class ArrayList>;
            ((ArrayList) (obj)).ArrayList();
            hashset = JVM INSTR new #94  <Class HashSet>;
            hashset.HashSet(SharedPreferencesImpl._2D_get2(SharedPreferencesImpl.this).keySet());
            obj2 = mLock;
            obj2;
            JVM INSTR monitorenter ;
            flag1 = false;
            flag2 = false;
            if(!mClear)
                break MISSING_BLOCK_LABEL_191;
            flag1 = flag2;
            if(SharedPreferencesImpl._2D_get4(SharedPreferencesImpl.this).isEmpty())
                break MISSING_BLOCK_LABEL_186;
            flag1 = true;
            SharedPreferencesImpl._2D_get4(SharedPreferencesImpl.this).clear();
            mClear = false;
            iterator = mModified.entrySet().iterator();
_L6:
            if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
            entry = (java.util.Map.Entry)iterator.next();
            s = (String)entry.getKey();
            obj4 = entry.getValue();
            if(obj4 != this && obj4 != null) goto _L4; else goto _L3
_L3:
            if(!SharedPreferencesImpl._2D_get4(SharedPreferencesImpl.this).containsKey(s)) goto _L6; else goto _L5
_L5:
            SharedPreferencesImpl._2D_get4(SharedPreferencesImpl.this).remove(s);
_L9:
            flag2 = true;
            flag1 = flag2;
            if(!flag) goto _L6; else goto _L7
_L7:
            ((List) (obj)).add(s);
            flag1 = flag2;
              goto _L6
            obj;
            obj2;
            JVM INSTR monitorexit ;
            throw obj;
            obj;
_L10:
            obj1;
            JVM INSTR monitorexit ;
            throw obj;
_L4:
            Object obj3;
            if(!SharedPreferencesImpl._2D_get4(SharedPreferencesImpl.this).containsKey(s))
                break; /* Loop/switch isn't completed */
            obj3 = SharedPreferencesImpl._2D_get4(SharedPreferencesImpl.this).get(s);
            if(obj3 == null)
                break; /* Loop/switch isn't completed */
            if(obj3.equals(obj4)) goto _L6; else goto _L8
_L8:
            SharedPreferencesImpl._2D_get4(SharedPreferencesImpl.this).put(s, obj4);
              goto _L9
_L2:
            mModified.clear();
            if(!flag1)
                break MISSING_BLOCK_LABEL_439;
            SharedPreferencesImpl sharedpreferencesimpl2 = SharedPreferencesImpl.this;
            SharedPreferencesImpl._2D_set0(sharedpreferencesimpl2, SharedPreferencesImpl._2D_get0(sharedpreferencesimpl2) + 1L);
            long l = SharedPreferencesImpl._2D_get0(SharedPreferencesImpl.this);
            obj2;
            JVM INSTR monitorexit ;
            obj1;
            JVM INSTR monitorexit ;
            return new MemoryCommitResult(l, ((List) (obj)), hashset, map, null);
            obj;
              goto _L10
        }

        private void notifyListeners(MemoryCommitResult memorycommitresult)
        {
            while(memorycommitresult.listeners == null || memorycommitresult.keysModified == null || memorycommitresult.keysModified.size() == 0) 
                return;
            if(Looper.myLooper() == Looper.getMainLooper())
            {
                for(int i = memorycommitresult.keysModified.size() - 1; i >= 0; i--)
                {
                    String s = (String)memorycommitresult.keysModified.get(i);
                    Iterator iterator = memorycommitresult.listeners.iterator();
                    do
                    {
                        if(!iterator.hasNext())
                            break;
                        android.content.SharedPreferences.OnSharedPreferenceChangeListener onsharedpreferencechangelistener = (android.content.SharedPreferences.OnSharedPreferenceChangeListener)iterator.next();
                        if(onsharedpreferencechangelistener != null)
                            onsharedpreferencechangelistener.onSharedPreferenceChanged(SharedPreferencesImpl.this, s);
                    } while(true);
                }

            } else
            {
                ActivityThread.sMainThreadHandler.post(memorycommitresult. new Runnable() {

                    public void run()
                    {
                        EditorImpl._2D_wrap0(EditorImpl.this, mcr);
                    }

                    final EditorImpl this$1;
                    final MemoryCommitResult val$mcr;

            
            {
                this$1 = final_editorimpl;
                mcr = MemoryCommitResult.this;
                super();
            }
                }
);
            }
        }

        public void apply()
        {
            long l = System.currentTimeMillis();
            final MemoryCommitResult mcr = commitToMemory();
            Runnable runnable = l. new Runnable() {

                public void run()
                {
                    mcr.writtenToDiskLatch.await();
_L2:
                    return;
                    InterruptedException interruptedexception;
                    interruptedexception;
                    if(true) goto _L2; else goto _L1
_L1:
                }

                final EditorImpl this$1;
                final MemoryCommitResult val$mcr;
                final long val$startTime;

            
            {
                this$1 = final_editorimpl;
                mcr = memorycommitresult;
                startTime = J.this;
                super();
            }
            }
;
            QueuedWork.addFinisher(runnable);
            runnable = runnable. new Runnable() {

                public void run()
                {
                    awaitCommit.run();
                    QueuedWork.removeFinisher(awaitCommit);
                }

                final EditorImpl this$1;
                final Runnable val$awaitCommit;

            
            {
                this$1 = final_editorimpl;
                awaitCommit = Runnable.this;
                super();
            }
            }
;
            SharedPreferencesImpl._2D_wrap0(SharedPreferencesImpl.this, mcr, runnable);
            notifyListeners(mcr);
        }

        public android.content.SharedPreferences.Editor clear()
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            mClear = true;
            obj;
            JVM INSTR monitorexit ;
            return this;
            Exception exception;
            exception;
            throw exception;
        }

        public boolean commit()
        {
            MemoryCommitResult memorycommitresult;
            memorycommitresult = commitToMemory();
            SharedPreferencesImpl._2D_wrap0(SharedPreferencesImpl.this, memorycommitresult, null);
            try
            {
                memorycommitresult.writtenToDiskLatch.await();
            }
            catch(InterruptedException interruptedexception)
            {
                return false;
            }
            notifyListeners(memorycommitresult);
            return memorycommitresult.writeToDiskResult;
            Exception exception;
            exception;
            throw exception;
        }

        public android.content.SharedPreferences.Editor putBoolean(String s, boolean flag)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            mModified.put(s, Boolean.valueOf(flag));
            obj;
            JVM INSTR monitorexit ;
            return this;
            s;
            throw s;
        }

        public android.content.SharedPreferences.Editor putFloat(String s, float f)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            mModified.put(s, Float.valueOf(f));
            obj;
            JVM INSTR monitorexit ;
            return this;
            s;
            throw s;
        }

        public android.content.SharedPreferences.Editor putInt(String s, int i)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            mModified.put(s, Integer.valueOf(i));
            obj;
            JVM INSTR monitorexit ;
            return this;
            s;
            throw s;
        }

        public android.content.SharedPreferences.Editor putLong(String s, long l)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            mModified.put(s, Long.valueOf(l));
            obj;
            JVM INSTR monitorexit ;
            return this;
            s;
            throw s;
        }

        public android.content.SharedPreferences.Editor putString(String s, String s1)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            mModified.put(s, s1);
            obj;
            JVM INSTR monitorexit ;
            return this;
            s;
            throw s;
        }

        public android.content.SharedPreferences.Editor putStringSet(String s, Set set)
        {
            Object obj = null;
            Object obj1 = mLock;
            obj1;
            JVM INSTR monitorenter ;
            Map map = mModified;
            if(set != null) goto _L2; else goto _L1
_L1:
            set = obj;
_L4:
            map.put(s, set);
            obj1;
            JVM INSTR monitorexit ;
            return this;
_L2:
            set = new HashSet(set);
            if(true) goto _L4; else goto _L3
_L3:
            s;
            throw s;
        }

        public android.content.SharedPreferences.Editor remove(String s)
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            mModified.put(s, this);
            obj;
            JVM INSTR monitorexit ;
            return this;
            s;
            throw s;
        }

        private boolean mClear;
        private final Object mLock = new Object();
        private final Map mModified = Maps.newHashMap();
        final SharedPreferencesImpl this$0;

        public EditorImpl()
        {
            this$0 = SharedPreferencesImpl.this;
            super();
            mClear = false;
        }
    }

    private static class MemoryCommitResult
    {

        void setDiskWriteResult(boolean flag, boolean flag1)
        {
            wasWritten = flag;
            writeToDiskResult = flag1;
            writtenToDiskLatch.countDown();
        }

        final List keysModified;
        final Set listeners;
        final Map mapToWriteToDisk;
        final long memoryStateGeneration;
        boolean wasWritten;
        volatile boolean writeToDiskResult;
        final CountDownLatch writtenToDiskLatch;

        private MemoryCommitResult(long l, List list, Set set, Map map)
        {
            writtenToDiskLatch = new CountDownLatch(1);
            writeToDiskResult = false;
            wasWritten = false;
            memoryStateGeneration = l;
            keysModified = list;
            listeners = set;
            mapToWriteToDisk = map;
        }

        MemoryCommitResult(long l, List list, Set set, Map map, MemoryCommitResult memorycommitresult)
        {
            this(l, list, set, map);
        }
    }


    static long _2D_get0(SharedPreferencesImpl sharedpreferencesimpl)
    {
        return sharedpreferencesimpl.mCurrentMemoryStateGeneration;
    }

    static int _2D_get1(SharedPreferencesImpl sharedpreferencesimpl)
    {
        return sharedpreferencesimpl.mDiskWritesInFlight;
    }

    static WeakHashMap _2D_get2(SharedPreferencesImpl sharedpreferencesimpl)
    {
        return sharedpreferencesimpl.mListeners;
    }

    static Object _2D_get3(SharedPreferencesImpl sharedpreferencesimpl)
    {
        return sharedpreferencesimpl.mLock;
    }

    static Map _2D_get4(SharedPreferencesImpl sharedpreferencesimpl)
    {
        return sharedpreferencesimpl.mMap;
    }

    static Object _2D_get5(SharedPreferencesImpl sharedpreferencesimpl)
    {
        return sharedpreferencesimpl.mWritingToDiskLock;
    }

    static long _2D_set0(SharedPreferencesImpl sharedpreferencesimpl, long l)
    {
        sharedpreferencesimpl.mCurrentMemoryStateGeneration = l;
        return l;
    }

    static int _2D_set1(SharedPreferencesImpl sharedpreferencesimpl, int i)
    {
        sharedpreferencesimpl.mDiskWritesInFlight = i;
        return i;
    }

    static Map _2D_set2(SharedPreferencesImpl sharedpreferencesimpl, Map map)
    {
        sharedpreferencesimpl.mMap = map;
        return map;
    }

    static void _2D_wrap0(SharedPreferencesImpl sharedpreferencesimpl, MemoryCommitResult memorycommitresult, Runnable runnable)
    {
        sharedpreferencesimpl.enqueueDiskWrite(memorycommitresult, runnable);
    }

    static void _2D_wrap1(SharedPreferencesImpl sharedpreferencesimpl)
    {
        sharedpreferencesimpl.loadFromDisk();
    }

    static void _2D_wrap2(SharedPreferencesImpl sharedpreferencesimpl, MemoryCommitResult memorycommitresult, boolean flag)
    {
        sharedpreferencesimpl.writeToFile(memorycommitresult, flag);
    }

    SharedPreferencesImpl(File file, int i)
    {
        mDiskWritesInFlight = 0;
        mLoaded = false;
        mNumSync = 0;
        mFile = file;
        mBackupFile = makeBackupFile(file);
        mMode = i;
        mLoaded = false;
        mMap = null;
        startLoadFromDisk();
    }

    private void awaitLoadedLocked()
    {
        if(!mLoaded)
            BlockGuard.getThreadPolicy().onReadFromDisk();
        while(!mLoaded) 
            try
            {
                mLock.wait();
            }
            catch(InterruptedException interruptedexception) { }
    }

    private static FileOutputStream createFileOutputStream(File file)
    {
        Object obj = null;
        Object obj1;
        obj1 = JVM INSTR new #166 <Class FileOutputStream>;
        ((FileOutputStream) (obj1)).FileOutputStream(file);
        file = ((File) (obj1));
_L2:
        return file;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        filenotfoundexception = file.getParentFile();
        if(!filenotfoundexception.mkdir())
        {
            Log.e("SharedPreferencesImpl", (new StringBuilder()).append("Couldn't create directory for SharedPreferences file ").append(file).toString());
            return null;
        }
        FileUtils.setPermissions(filenotfoundexception.getPath(), 505, -1, -1);
        filenotfoundexception = JVM INSTR new #166 <Class FileOutputStream>;
        filenotfoundexception.FileOutputStream(file);
        file = filenotfoundexception;
        continue; /* Loop/switch isn't completed */
        filenotfoundexception;
        Log.e("SharedPreferencesImpl", (new StringBuilder()).append("Couldn't create SharedPreferences file ").append(file).toString(), filenotfoundexception);
        file = obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void enqueueDiskWrite(final MemoryCommitResult mcr, final Runnable postWriteRunnable)
    {
        final boolean isFromSyncCommit;
        boolean flag;
        int i;
        if(postWriteRunnable == null)
            isFromSyncCommit = true;
        else
            isFromSyncCommit = false;
        postWriteRunnable = new Runnable() {

            public void run()
            {
                Object obj = SharedPreferencesImpl._2D_get5(SharedPreferencesImpl.this);
                obj;
                JVM INSTR monitorenter ;
                SharedPreferencesImpl._2D_wrap2(SharedPreferencesImpl.this, mcr, isFromSyncCommit);
                obj;
                JVM INSTR monitorexit ;
                Object obj1 = SharedPreferencesImpl._2D_get3(SharedPreferencesImpl.this);
                obj1;
                JVM INSTR monitorenter ;
                obj = SharedPreferencesImpl.this;
                SharedPreferencesImpl._2D_set1(((SharedPreferencesImpl) (obj)), SharedPreferencesImpl._2D_get1(((SharedPreferencesImpl) (obj))) - 1);
                obj1;
                JVM INSTR monitorexit ;
                if(postWriteRunnable != null)
                    postWriteRunnable.run();
                return;
                obj1;
                throw obj1;
                Exception exception;
                exception;
                throw exception;
            }

            final SharedPreferencesImpl this$0;
            final boolean val$isFromSyncCommit;
            final MemoryCommitResult val$mcr;
            final Runnable val$postWriteRunnable;

            
            {
                this$0 = SharedPreferencesImpl.this;
                mcr = memorycommitresult;
                isFromSyncCommit = flag;
                postWriteRunnable = runnable;
                super();
            }
        }
;
        if(!isFromSyncCommit)
            break MISSING_BLOCK_LABEL_74;
        mcr = ((MemoryCommitResult) (mLock));
        mcr;
        JVM INSTR monitorenter ;
        i = mDiskWritesInFlight;
        if(i == 1)
            flag = true;
        else
            flag = false;
        mcr;
        JVM INSTR monitorexit ;
        if(flag)
        {
            postWriteRunnable.run();
            return;
        }
        break MISSING_BLOCK_LABEL_74;
        postWriteRunnable;
        throw postWriteRunnable;
        QueuedWork.queue(postWriteRunnable, isFromSyncCommit ^ true);
        return;
    }

    private boolean hasFileChangedUnexpectedly()
    {
        boolean flag = true;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mDiskWritesInFlight;
        if(i <= 0)
            break MISSING_BLOCK_LABEL_22;
        obj;
        JVM INSTR monitorexit ;
        return false;
        obj;
        JVM INSTR monitorexit ;
        Object obj1;
        boolean flag1;
        long l;
        long l1;
        try
        {
            BlockGuard.getThreadPolicy().onReadFromDisk();
            obj = Os.stat(mFile.getPath());
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1)
        {
            return true;
        }
        obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        flag1 = flag;
        if(!((StructStat) (obj)).st_mtim.equals(mStatTimestamp))
            break MISSING_BLOCK_LABEL_92;
        l = mStatSize;
        l1 = ((StructStat) (obj)).st_size;
        if(l != l1)
            flag1 = flag;
        else
            flag1 = false;
        obj1;
        JVM INSTR monitorexit ;
        return flag1;
        obj1;
        throw obj1;
        Exception exception;
        exception;
        throw exception;
    }

    private void loadFromDisk()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mLoaded;
        if(!flag)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        if(mBackupFile.exists())
        {
            mFile.delete();
            mBackupFile.renameTo(mFile);
        }
        obj;
        JVM INSTR monitorexit ;
        Object obj1;
        Object obj2;
        Object obj3;
        if(mFile.exists() && mFile.canRead() ^ true)
            Log.w("SharedPreferencesImpl", (new StringBuilder()).append("Attempt to read preferences file ").append(mFile).append(" without permission").toString());
        obj1 = null;
        obj2 = null;
        obj = null;
        obj3 = obj1;
        StructStat structstat = Os.stat(mFile.getPath());
        obj3 = obj1;
        obj = structstat;
        flag = mFile.canRead();
        Object obj5;
        obj3 = obj2;
        obj = structstat;
        if(!flag)
            break MISSING_BLOCK_LABEL_228;
        obj3 = null;
        obj5 = null;
        obj = obj3;
        Object obj6 = JVM INSTR new #280 <Class BufferedInputStream>;
        obj = obj3;
        FileInputStream fileinputstream = JVM INSTR new #282 <Class FileInputStream>;
        obj = obj3;
        fileinputstream.FileInputStream(mFile);
        obj = obj3;
        ((BufferedInputStream) (obj6)).BufferedInputStream(fileinputstream, 16384);
        obj5 = XmlUtils.readMapXml(((java.io.InputStream) (obj6)));
        obj3 = obj5;
        obj = structstat;
        IoUtils.closeQuietly(((AutoCloseable) (obj6)));
        obj = structstat;
        obj3 = obj5;
_L3:
        obj5 = mLock;
        obj5;
        JVM INSTR monitorenter ;
        mLoaded = true;
        if(obj3 == null) goto _L2; else goto _L1
_L1:
        mMap = ((Map) (obj3));
        mStatTimestamp = ((StructStat) (obj)).st_mtim;
        mStatSize = ((StructStat) (obj)).st_size;
_L4:
        mLock.notifyAll();
        obj5;
        JVM INSTR monitorexit ;
        return;
        Object obj4;
        obj4;
        throw obj4;
        obj4;
_L6:
        obj = obj5;
        obj6 = JVM INSTR new #181 <Class StringBuilder>;
        obj = obj5;
        ((StringBuilder) (obj6)).StringBuilder();
        obj = obj5;
        Log.w("SharedPreferencesImpl", ((StringBuilder) (obj6)).append("Cannot read ").append(mFile.getAbsolutePath()).toString(), ((Throwable) (obj4)));
        obj4 = obj1;
        obj = structstat;
        IoUtils.closeQuietly(((AutoCloseable) (obj5)));
        obj4 = obj2;
        obj = structstat;
          goto _L3
        obj5;
        obj6 = obj;
_L5:
        obj4 = obj1;
        obj = structstat;
        IoUtils.closeQuietly(((AutoCloseable) (obj6)));
        obj4 = obj1;
        obj = structstat;
        try
        {
            throw obj5;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj5) { }
          goto _L3
_L2:
        HashMap hashmap = JVM INSTR new #310 <Class HashMap>;
        hashmap.HashMap();
        mMap = hashmap;
          goto _L4
        Exception exception;
        exception;
        throw exception;
        obj5;
          goto _L5
        obj4;
        obj5 = obj6;
          goto _L6
    }

    static File makeBackupFile(File file)
    {
        return new File((new StringBuilder()).append(file.getPath()).append(".bak").toString());
    }

    private void startLoadFromDisk()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mLoaded = false;
        obj;
        JVM INSTR monitorexit ;
        (new Thread("SharedPreferencesImpl-load") {

            public void run()
            {
                SharedPreferencesImpl._2D_wrap1(SharedPreferencesImpl.this);
            }

            final SharedPreferencesImpl this$0;

            
            {
                this$0 = SharedPreferencesImpl.this;
                super(s);
            }
        }
).start();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void writeToFile(MemoryCommitResult memorycommitresult, boolean flag)
    {
        boolean flag1;
        boolean flag2;
        if(!mFile.exists())
            break MISSING_BLOCK_LABEL_174;
        flag1 = false;
        flag2 = false;
        if(mDiskStateGeneration >= memorycommitresult.memoryStateGeneration) goto _L2; else goto _L1
_L1:
        if(!flag) goto _L4; else goto _L3
_L3:
        flag2 = true;
_L2:
        if(!flag2)
        {
            memorycommitresult.setDiskWriteResult(false, true);
            return;
        }
        break; /* Loop/switch isn't completed */
_L4:
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        long l;
        long l2;
        l = mCurrentMemoryStateGeneration;
        l2 = memorycommitresult.memoryStateGeneration;
        flag2 = flag1;
        if(l == l2)
            flag2 = true;
        obj;
        JVM INSTR monitorexit ;
        if(true) goto _L2; else goto _L5
        memorycommitresult;
        throw memorycommitresult;
_L5:
        if(!mBackupFile.exists())
        {
            if(!mFile.renameTo(mBackupFile))
            {
                Log.e("SharedPreferencesImpl", (new StringBuilder()).append("Couldn't rename file ").append(mFile).append(" to backup file ").append(mBackupFile).toString());
                memorycommitresult.setDiskWriteResult(false, false);
                return;
            }
        } else
        {
            mFile.delete();
        }
        obj = createFileOutputStream(mFile);
        if(obj == null)
        {
            ErrnoException errnoexception;
            long l1;
            long l3;
            Object obj1;
            Exception exception;
            try
            {
                memorycommitresult.setDiskWriteResult(false, false);
                return;
            }
            catch(XmlPullParserException xmlpullparserexception)
            {
                Log.w("SharedPreferencesImpl", "writeToFile: Got exception:", xmlpullparserexception);
            }
            catch(IOException ioexception)
            {
                Log.w("SharedPreferencesImpl", "writeToFile: Got exception:", ioexception);
            }
            break MISSING_BLOCK_LABEL_428;
        }
        XmlUtils.writeMapXml(memorycommitresult.mapToWriteToDisk, ((java.io.OutputStream) (obj)));
        l1 = System.currentTimeMillis();
        FileUtils.sync(((FileOutputStream) (obj)));
        l3 = System.currentTimeMillis();
        ((FileOutputStream) (obj)).close();
        ContextImpl.setFilePermissionsFromMode(mFile.getPath(), mMode, 0);
        obj1 = Os.stat(mFile.getPath());
        obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mStatTimestamp = ((StructStat) (obj1)).st_mtim;
        mStatSize = ((StructStat) (obj1)).st_size;
        obj;
        JVM INSTR monitorexit ;
_L7:
        mBackupFile.delete();
        mDiskStateGeneration = memorycommitresult.memoryStateGeneration;
        memorycommitresult.setDiskWriteResult(true, true);
        l1 = l3 - l1;
        mSyncTimes.add((int)l1);
        mNumSync = mNumSync + 1;
          goto _L6
_L8:
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        errnoexception;
          goto _L7
_L6:
        if(mNumSync % 1024 == 0 || l1 > 256L)
        {
            obj1 = mSyncTimes;
            obj = JVM INSTR new #181 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            ((ExponentiallyBucketedHistogram) (obj1)).log("SharedPreferencesImpl", ((StringBuilder) (obj)).append("Time required to fsync ").append(mFile).append(": ").toString());
        }
          goto _L8
        if(mFile.exists() && !mFile.delete())
            Log.e("SharedPreferencesImpl", (new StringBuilder()).append("Couldn't clean up partially-written file ").append(mFile).toString());
        memorycommitresult.setDiskWriteResult(false, false);
        return;
          goto _L7
    }

    public boolean contains(String s)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        awaitLoadedLocked();
        flag = mMap.containsKey(s);
        obj;
        JVM INSTR monitorexit ;
        return flag;
        s;
        throw s;
    }

    public android.content.SharedPreferences.Editor edit()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        awaitLoadedLocked();
        obj;
        JVM INSTR monitorexit ;
        return new EditorImpl();
        Exception exception;
        exception;
        throw exception;
    }

    public Map getAll()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        HashMap hashmap;
        awaitLoadedLocked();
        hashmap = new HashMap(mMap);
        obj;
        JVM INSTR monitorexit ;
        return hashmap;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean getBoolean(String s, boolean flag)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        awaitLoadedLocked();
        s = (Boolean)mMap.get(s);
        if(s == null)
            break MISSING_BLOCK_LABEL_34;
        flag = s.booleanValue();
        obj;
        JVM INSTR monitorexit ;
        return flag;
        s;
        throw s;
    }

    public float getFloat(String s, float f)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        awaitLoadedLocked();
        s = (Float)mMap.get(s);
        if(s == null)
            break MISSING_BLOCK_LABEL_34;
        f = s.floatValue();
        obj;
        JVM INSTR monitorexit ;
        return f;
        s;
        throw s;
    }

    public int getInt(String s, int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        awaitLoadedLocked();
        s = (Integer)mMap.get(s);
        if(s == null)
            break MISSING_BLOCK_LABEL_34;
        i = s.intValue();
        obj;
        JVM INSTR monitorexit ;
        return i;
        s;
        throw s;
    }

    public long getLong(String s, long l)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        awaitLoadedLocked();
        s = (Long)mMap.get(s);
        if(s == null)
            break MISSING_BLOCK_LABEL_36;
        l = s.longValue();
        obj;
        JVM INSTR monitorexit ;
        return l;
        s;
        throw s;
    }

    public String getString(String s, String s1)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        awaitLoadedLocked();
        s = (String)mMap.get(s);
        if(s == null)
            s = s1;
        obj;
        JVM INSTR monitorexit ;
        return s;
        s;
        throw s;
    }

    public Set getStringSet(String s, Set set)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        awaitLoadedLocked();
        s = (Set)mMap.get(s);
        if(s != null)
            set = s;
        obj;
        JVM INSTR monitorexit ;
        return set;
        s;
        throw s;
    }

    public void registerOnSharedPreferenceChangeListener(android.content.SharedPreferences.OnSharedPreferenceChangeListener onsharedpreferencechangelistener)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mListeners.put(onsharedpreferencechangelistener, CONTENT);
        obj;
        JVM INSTR monitorexit ;
        return;
        onsharedpreferencechangelistener;
        throw onsharedpreferencechangelistener;
    }

    void startReloadIfChangedUnexpectedly()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = hasFileChangedUnexpectedly();
        if(flag)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        startLoadFromDisk();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void unregisterOnSharedPreferenceChangeListener(android.content.SharedPreferences.OnSharedPreferenceChangeListener onsharedpreferencechangelistener)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mListeners.remove(onsharedpreferencechangelistener);
        obj;
        JVM INSTR monitorexit ;
        return;
        onsharedpreferencechangelistener;
        throw onsharedpreferencechangelistener;
    }

    private static final Object CONTENT = new Object();
    private static final boolean DEBUG = false;
    private static final long MAX_FSYNC_DURATION_MILLIS = 256L;
    private static final String TAG = "SharedPreferencesImpl";
    private final File mBackupFile;
    private long mCurrentMemoryStateGeneration;
    private long mDiskStateGeneration;
    private int mDiskWritesInFlight;
    private final File mFile;
    private final WeakHashMap mListeners = new WeakHashMap();
    private boolean mLoaded;
    private final Object mLock = new Object();
    private Map mMap;
    private final int mMode;
    private int mNumSync;
    private long mStatSize;
    private StructTimespec mStatTimestamp;
    private final ExponentiallyBucketedHistogram mSyncTimes = new ExponentiallyBucketedHistogram(16);
    private final Object mWritingToDiskLock = new Object();

}
