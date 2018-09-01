// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.graphics.Canvas;
import android.os.Handler;
import android.util.*;
import java.util.*;

// Referenced classes of package android.media:
//            MediaTimeProvider, SubtitleData, MediaFormat

public abstract class SubtitleTrack
    implements MediaTimeProvider.OnMediaTimeListener
{
    public static class Cue
    {

        public void onTime(long l)
        {
        }

        public long mEndTimeMs;
        public long mInnerTimesMs[];
        public Cue mNextInRun;
        public long mRunID;
        public long mStartTimeMs;

        public Cue()
        {
        }
    }

    static class CueList
    {

        static SortedMap _2D_get0(CueList cuelist)
        {
            return cuelist.mCues;
        }

        static void _2D_wrap0(CueList cuelist, Cue cue, long l)
        {
            cuelist.removeEvent(cue, l);
        }

        private boolean addEvent(Cue cue, long l)
        {
            Vector vector = (Vector)mCues.get(Long.valueOf(l));
            Vector vector1;
            if(vector == null)
            {
                vector1 = new Vector(2);
                mCues.put(Long.valueOf(l), vector1);
            } else
            {
                vector1 = vector;
                if(vector.contains(cue))
                    return false;
            }
            vector1.add(cue);
            return true;
        }

        private void removeEvent(Cue cue, long l)
        {
            Vector vector = (Vector)mCues.get(Long.valueOf(l));
            if(vector != null)
            {
                vector.remove(cue);
                if(vector.size() == 0)
                    mCues.remove(Long.valueOf(l));
            }
        }

        public void add(Cue cue)
        {
            if(cue.mStartTimeMs >= cue.mEndTimeMs)
                return;
            if(!addEvent(cue, cue.mStartTimeMs))
                return;
            long l = cue.mStartTimeMs;
            if(cue.mInnerTimesMs != null)
            {
                long al[] = cue.mInnerTimesMs;
                int i = 0;
                for(int j = al.length; i < j;)
                {
                    long l1 = al[i];
                    long l2 = l;
                    if(l1 > l)
                    {
                        l2 = l;
                        if(l1 < cue.mEndTimeMs)
                        {
                            addEvent(cue, l1);
                            l2 = l1;
                        }
                    }
                    i++;
                    l = l2;
                }

            }
            addEvent(cue, cue.mEndTimeMs);
        }

        public Iterable entriesBetween(final long lastTimeMs, long l)
        {
            return l. new Iterable() {

                public Iterator iterator()
                {
                    if(DEBUG)
                        Log.d("CueList", (new StringBuilder()).append("slice (").append(lastTimeMs).append(", ").append(timeMs).append("]=").toString());
                    CueList.EntryIterator entryiterator;
                    try
                    {
                        entryiterator = new CueList.EntryIterator(CueList._2D_get0(CueList.this).subMap(Long.valueOf(lastTimeMs + 1L), Long.valueOf(timeMs + 1L)));
                    }
                    catch(IllegalArgumentException illegalargumentexception)
                    {
                        return new CueList.EntryIterator(null);
                    }
                    return entryiterator;
                }

                final CueList this$1;
                final long val$lastTimeMs;
                final long val$timeMs;

            
            {
                this$1 = final_cuelist;
                lastTimeMs = l;
                timeMs = J.this;
                super();
            }
            }
;
        }

        public long nextTimeAfter(long l)
        {
            SortedMap sortedmap;
            try
            {
                sortedmap = mCues.tailMap(Long.valueOf(1L + l));
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                return -1L;
            }
            catch(NoSuchElementException nosuchelementexception)
            {
                return -1L;
            }
            if(sortedmap == null)
                break MISSING_BLOCK_LABEL_35;
            l = ((Long)sortedmap.firstKey()).longValue();
            return l;
            return -1L;
        }

        public void remove(Cue cue)
        {
            removeEvent(cue, cue.mStartTimeMs);
            if(cue.mInnerTimesMs != null)
            {
                long al[] = cue.mInnerTimesMs;
                int i = 0;
                for(int j = al.length; i < j; i++)
                    removeEvent(cue, al[i]);

            }
            removeEvent(cue, cue.mEndTimeMs);
        }

        private static final String TAG = "CueList";
        public boolean DEBUG;
        private SortedMap mCues;

        CueList()
        {
            DEBUG = false;
            mCues = new TreeMap();
        }
    }

    class CueList.EntryIterator
        implements Iterator
    {

        private void nextKey()
        {
_L1:
            try
            {
                if(mRemainingCues == null)
                {
                    NoSuchElementException nosuchelementexception = JVM INSTR new #74  <Class NoSuchElementException>;
                    nosuchelementexception.NoSuchElementException("");
                    throw nosuchelementexception;
                }
            }
            catch(NoSuchElementException nosuchelementexception1)
            {
                mDone = true;
                mRemainingCues = null;
                mListIterator = null;
                return;
            }
            mCurrentTimeMs = ((Long)mRemainingCues.firstKey()).longValue();
            mListIterator = ((Vector)mRemainingCues.get(Long.valueOf(mCurrentTimeMs))).iterator();
            mRemainingCues = mRemainingCues.tailMap(Long.valueOf(mCurrentTimeMs + 1L));
_L2:
            mDone = false;
            if(mListIterator.hasNext())
                return;
              goto _L1
            IllegalArgumentException illegalargumentexception;
            illegalargumentexception;
            mRemainingCues = null;
              goto _L2
        }

        public boolean hasNext()
        {
            return mDone ^ true;
        }

        public Pair next()
        {
            if(mDone)
                throw new NoSuchElementException("");
            mLastEntry = new Pair(Long.valueOf(mCurrentTimeMs), (Cue)mListIterator.next());
            mLastListIterator = mListIterator;
            if(!mListIterator.hasNext())
                nextKey();
            return mLastEntry;
        }

        public volatile Object next()
        {
            return next();
        }

        public void remove()
        {
            if(mLastListIterator == null || ((Cue)mLastEntry.second).mEndTimeMs != ((Long)mLastEntry.first).longValue())
                throw new IllegalStateException("");
            mLastListIterator.remove();
            mLastListIterator = null;
            if(((Vector)CueList._2D_get0(CueList.this).get(mLastEntry.first)).size() == 0)
                CueList._2D_get0(CueList.this).remove(mLastEntry.first);
            Cue cue = (Cue)mLastEntry.second;
            CueList._2D_wrap0(CueList.this, cue, cue.mStartTimeMs);
            if(cue.mInnerTimesMs != null)
            {
                long al[] = cue.mInnerTimesMs;
                int i = al.length;
                for(int j = 0; j < i; j++)
                {
                    long l = al[j];
                    CueList._2D_wrap0(CueList.this, cue, l);
                }

            }
        }

        private long mCurrentTimeMs;
        private boolean mDone;
        private Pair mLastEntry;
        private Iterator mLastListIterator;
        private Iterator mListIterator;
        private SortedMap mRemainingCues;
        final CueList this$1;

        public CueList.EntryIterator(SortedMap sortedmap)
        {
            this$1 = CueList.this;
            super();
            if(DEBUG)
                Log.v("CueList", (new StringBuilder()).append(sortedmap).append("").toString());
            mRemainingCues = sortedmap;
            mLastListIterator = null;
            nextKey();
        }
    }

    public static interface RenderingWidget
    {

        public abstract void draw(Canvas canvas);

        public abstract void onAttachedToWindow();

        public abstract void onDetachedFromWindow();

        public abstract void setOnChangedListener(OnChangedListener onchangedlistener);

        public abstract void setSize(int i, int j);

        public abstract void setVisible(boolean flag);
    }

    public static interface RenderingWidget.OnChangedListener
    {

        public abstract void onChanged(RenderingWidget renderingwidget);
    }

    private static class Run
    {

        public void removeAtEndTimeMs()
        {
            Run run = mPrevRunAtEndTimeMs;
            if(mPrevRunAtEndTimeMs != null)
            {
                mPrevRunAtEndTimeMs.mNextRunAtEndTimeMs = mNextRunAtEndTimeMs;
                mPrevRunAtEndTimeMs = null;
            }
            if(mNextRunAtEndTimeMs != null)
            {
                mNextRunAtEndTimeMs.mPrevRunAtEndTimeMs = run;
                mNextRunAtEndTimeMs = null;
            }
        }

        public void storeByEndTimeMs(LongSparseArray longsparsearray)
        {
            int i = longsparsearray.indexOfKey(mStoredEndTimeMs);
            if(i >= 0)
            {
                if(mPrevRunAtEndTimeMs == null)
                {
                    if(!_2D_assertionsDisabled && this != longsparsearray.valueAt(i))
                        throw new AssertionError();
                    if(mNextRunAtEndTimeMs == null)
                        longsparsearray.removeAt(i);
                    else
                        longsparsearray.setValueAt(i, mNextRunAtEndTimeMs);
                }
                removeAtEndTimeMs();
            }
            if(mEndTimeMs >= 0L)
            {
                mPrevRunAtEndTimeMs = null;
                mNextRunAtEndTimeMs = (Run)longsparsearray.get(mEndTimeMs);
                if(mNextRunAtEndTimeMs != null)
                    mNextRunAtEndTimeMs.mPrevRunAtEndTimeMs = this;
                longsparsearray.put(mEndTimeMs, this);
                mStoredEndTimeMs = mEndTimeMs;
            }
        }

        static final boolean _2D_assertionsDisabled = android/media/SubtitleTrack$Run.desiredAssertionStatus() ^ true;
        public long mEndTimeMs;
        public Cue mFirstCue;
        public Run mNextRunAtEndTimeMs;
        public Run mPrevRunAtEndTimeMs;
        public long mRunID;
        private long mStoredEndTimeMs;


        private Run()
        {
            mEndTimeMs = -1L;
            mRunID = 0L;
            mStoredEndTimeMs = -1L;
        }

        Run(Run run)
        {
            this();
        }
    }


    static Runnable _2D_set0(SubtitleTrack subtitletrack, Runnable runnable)
    {
        subtitletrack.mRunnable = runnable;
        return runnable;
    }

    public SubtitleTrack(MediaFormat mediaformat)
    {
        DEBUG = false;
        mHandler = new Handler();
        mNextScheduledTimeMs = -1L;
        mFormat = mediaformat;
        mCues = new CueList();
        clearActiveCues();
        mLastTimeMs = -1L;
    }

    private void removeRunsByEndTimeIndex(int i)
    {
        Run run1;
        for(Run run = (Run)mRunsByEndTime.valueAt(i); run != null; run = run1)
        {
            Cue cue1;
            for(Cue cue = run.mFirstCue; cue != null; cue = cue1)
            {
                mCues.remove(cue);
                cue1 = cue.mNextInRun;
                cue.mNextInRun = null;
            }

            mRunsByID.remove(run.mRunID);
            run1 = run.mNextRunAtEndTimeMs;
            run.mPrevRunAtEndTimeMs = null;
            run.mNextRunAtEndTimeMs = null;
        }

        mRunsByEndTime.removeAt(i);
    }

    private void takeTime(long l)
    {
        this;
        JVM INSTR monitorenter ;
        mLastTimeMs = l;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected boolean addCue(Cue cue)
    {
        this;
        JVM INSTR monitorenter ;
        mCues.add(cue);
        if(cue.mRunID == 0L) goto _L2; else goto _L1
_L1:
        Run run = (Run)mRunsByID.get(cue.mRunID);
        if(run != null) goto _L4; else goto _L3
_L3:
        Object obj;
        obj = JVM INSTR new #27  <Class SubtitleTrack$Run>;
        ((Run) (obj)).Run(null);
        mRunsByID.put(cue.mRunID, obj);
        obj.mEndTimeMs = cue.mEndTimeMs;
_L12:
        cue.mNextInRun = ((Run) (obj)).mFirstCue;
        obj.mFirstCue = cue;
_L2:
        long l = -1L;
        obj = mTimeProvider;
        long l1;
        l1 = l;
        if(obj == null)
            break MISSING_BLOCK_LABEL_115;
        try
        {
            l1 = mTimeProvider.getCurrentTimeUs(false, true) / 1000L;
        }
        catch(IllegalStateException illegalstateexception)
        {
            l1 = l;
        }
        if(DEBUG)
        {
            obj = JVM INSTR new #168 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Log.v("SubtitleTrack", ((StringBuilder) (obj)).append("mVisible=").append(mVisible).append(", ").append(cue.mStartTimeMs).append(" <= ").append(l1).append(", ").append(cue.mEndTimeMs).append(" >= ").append(mLastTimeMs).toString());
        }
        if(!mVisible || cue.mStartTimeMs > l1 || cue.mEndTimeMs < mLastTimeMs) goto _L6; else goto _L5
_L5:
        if(mRunnable != null)
            mHandler.removeCallbacks(mRunnable);
        cue = JVM INSTR new #8   <Class SubtitleTrack$1>;
        cue.this. _cls1();
        mRunnable = cue;
        if(!mHandler.postDelayed(mRunnable, 10L)) goto _L8; else goto _L7
_L7:
        if(DEBUG)
            Log.v("SubtitleTrack", "scheduling update");
_L10:
        this;
        JVM INSTR monitorexit ;
        return true;
_L4:
        obj = run;
        if(run.mEndTimeMs >= cue.mEndTimeMs)
            continue; /* Loop/switch isn't completed */
        run.mEndTimeMs = cue.mEndTimeMs;
        obj = run;
        continue; /* Loop/switch isn't completed */
        cue;
        throw cue;
_L8:
        if(!DEBUG) goto _L10; else goto _L9
_L9:
        Log.w("SubtitleTrack", "failed to schedule subtitle view update");
          goto _L10
_L6:
        if(mVisible && cue.mEndTimeMs >= mLastTimeMs && (cue.mStartTimeMs < mNextScheduledTimeMs || mNextScheduledTimeMs < 0L))
            scheduleTimedEvents();
        this;
        JVM INSTR monitorexit ;
        return false;
        if(true) goto _L12; else goto _L11
_L11:
    }

    protected void clearActiveCues()
    {
        this;
        JVM INSTR monitorenter ;
        if(DEBUG)
        {
            StringBuilder stringbuilder = JVM INSTR new #168 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.v("SubtitleTrack", stringbuilder.append("Clearing ").append(mActiveCues.size()).append(" active cues").toString());
        }
        mActiveCues.clear();
        mLastUpdateTimeMs = -1L;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void finalize()
        throws Throwable
    {
        for(int i = mRunsByEndTime.size() - 1; i >= 0; i--)
            removeRunsByEndTimeIndex(i);

        super.finalize();
    }

    protected void finishedRun(long l)
    {
        if(l != 0L && l != -1L)
        {
            Run run = (Run)mRunsByID.get(l);
            if(run != null)
                run.storeByEndTimeMs(mRunsByEndTime);
        }
    }

    public final MediaFormat getFormat()
    {
        return mFormat;
    }

    public abstract RenderingWidget getRenderingWidget();

    public int getTrackType()
    {
        byte byte0;
        if(getRenderingWidget() == null)
            byte0 = 3;
        else
            byte0 = 4;
        return byte0;
    }

    public void hide()
    {
        if(!mVisible)
            return;
        if(mTimeProvider != null)
            mTimeProvider.cancelNotifications(this);
        RenderingWidget renderingwidget = getRenderingWidget();
        if(renderingwidget != null)
            renderingwidget.setVisible(false);
        mVisible = false;
    }

    protected void onData(SubtitleData subtitledata)
    {
        long l = subtitledata.getStartTimeUs() + 1L;
        onData(subtitledata.getData(), true, l);
        setRunDiscardTimeMs(l, (subtitledata.getStartTimeUs() + subtitledata.getDurationUs()) / 1000L);
    }

    public abstract void onData(byte abyte0[], boolean flag, long l);

    public void onSeek(long l)
    {
        if(DEBUG)
            Log.d("SubtitleTrack", (new StringBuilder()).append("onSeek ").append(l).toString());
        this;
        JVM INSTR monitorenter ;
        l /= 1000L;
        updateActiveCues(true, l);
        takeTime(l);
        this;
        JVM INSTR monitorexit ;
        updateView(mActiveCues);
        scheduleTimedEvents();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void onStop()
    {
        this;
        JVM INSTR monitorenter ;
        if(DEBUG)
            Log.d("SubtitleTrack", "onStop");
        clearActiveCues();
        mLastTimeMs = -1L;
        this;
        JVM INSTR monitorexit ;
        updateView(mActiveCues);
        mNextScheduledTimeMs = -1L;
        mTimeProvider.notifyAt(-1L, this);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void onTimedEvent(long l)
    {
        if(DEBUG)
            Log.d("SubtitleTrack", (new StringBuilder()).append("onTimedEvent ").append(l).toString());
        this;
        JVM INSTR monitorenter ;
        l /= 1000L;
        updateActiveCues(false, l);
        takeTime(l);
        this;
        JVM INSTR monitorexit ;
        updateView(mActiveCues);
        scheduleTimedEvents();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void scheduleTimedEvents()
    {
        if(mTimeProvider != null)
        {
            mNextScheduledTimeMs = mCues.nextTimeAfter(mLastTimeMs);
            if(DEBUG)
                Log.d("SubtitleTrack", (new StringBuilder()).append("sched @").append(mNextScheduledTimeMs).append(" after ").append(mLastTimeMs).toString());
            MediaTimeProvider mediatimeprovider = mTimeProvider;
            long l;
            if(mNextScheduledTimeMs >= 0L)
                l = mNextScheduledTimeMs * 1000L;
            else
                l = -1L;
            mediatimeprovider.notifyAt(l, this);
        }
    }

    public void setRunDiscardTimeMs(long l, long l1)
    {
        if(l != 0L && l != -1L)
        {
            Run run = (Run)mRunsByID.get(l);
            if(run != null)
            {
                run.mEndTimeMs = l1;
                run.storeByEndTimeMs(mRunsByEndTime);
            }
        }
    }

    public void setTimeProvider(MediaTimeProvider mediatimeprovider)
    {
        this;
        JVM INSTR monitorenter ;
        MediaTimeProvider mediatimeprovider1 = mTimeProvider;
        if(mediatimeprovider1 != mediatimeprovider)
            break MISSING_BLOCK_LABEL_15;
        this;
        JVM INSTR monitorexit ;
        return;
        if(mTimeProvider != null)
            mTimeProvider.cancelNotifications(this);
        mTimeProvider = mediatimeprovider;
        if(mTimeProvider != null)
            mTimeProvider.scheduleUpdate(this);
        this;
        JVM INSTR monitorexit ;
        return;
        mediatimeprovider;
        throw mediatimeprovider;
    }

    public void show()
    {
        if(mVisible)
            return;
        mVisible = true;
        RenderingWidget renderingwidget = getRenderingWidget();
        if(renderingwidget != null)
            renderingwidget.setVisible(true);
        if(mTimeProvider != null)
            mTimeProvider.scheduleUpdate(this);
    }

    protected void updateActiveCues(boolean flag, long l)
    {
        this;
        JVM INSTR monitorenter ;
        if(flag)
            break MISSING_BLOCK_LABEL_15;
        if(mLastUpdateTimeMs <= l)
            break MISSING_BLOCK_LABEL_19;
        clearActiveCues();
        Iterator iterator = mCues.entriesBetween(mLastUpdateTimeMs, l).iterator();
_L3:
        Object obj;
        Object obj1;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_269;
        obj = (Pair)iterator.next();
        obj1 = (Cue)((Pair) (obj)).second;
        if(((Cue) (obj1)).mEndTimeMs != ((Long)((Pair) (obj)).first).longValue()) goto _L2; else goto _L1
_L1:
        if(DEBUG)
        {
            obj = JVM INSTR new #168 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Log.v("SubtitleTrack", ((StringBuilder) (obj)).append("Removing ").append(obj1).toString());
        }
        mActiveCues.remove(obj1);
        if(((Cue) (obj1)).mRunID == 0L)
            iterator.remove();
          goto _L3
        obj1;
        throw obj1;
_L2:
        if(((Cue) (obj1)).mStartTimeMs != ((Long)((Pair) (obj)).first).longValue()) goto _L5; else goto _L4
_L4:
        if(DEBUG)
        {
            StringBuilder stringbuilder = JVM INSTR new #168 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.v("SubtitleTrack", stringbuilder.append("Adding ").append(obj1).toString());
        }
        if(((Cue) (obj1)).mInnerTimesMs != null)
            ((Cue) (obj1)).onTime(l);
        mActiveCues.add(obj1);
          goto _L3
_L5:
        if(((Cue) (obj1)).mInnerTimesMs == null) goto _L3; else goto _L6
_L6:
        ((Cue) (obj1)).onTime(l);
          goto _L3
        for(; mRunsByEndTime.size() > 0 && mRunsByEndTime.keyAt(0) <= l; removeRunsByEndTimeIndex(0));
        mLastUpdateTimeMs = l;
        this;
        JVM INSTR monitorexit ;
    }

    public abstract void updateView(Vector vector);

    private static final String TAG = "SubtitleTrack";
    public boolean DEBUG;
    protected final Vector mActiveCues = new Vector();
    protected CueList mCues;
    private MediaFormat mFormat;
    protected Handler mHandler;
    private long mLastTimeMs;
    private long mLastUpdateTimeMs;
    private long mNextScheduledTimeMs;
    private Runnable mRunnable;
    protected final LongSparseArray mRunsByEndTime = new LongSparseArray();
    protected final LongSparseArray mRunsByID = new LongSparseArray();
    protected MediaTimeProvider mTimeProvider;
    protected boolean mVisible;

    // Unreferenced inner class android/media/SubtitleTrack$1

/* anonymous class */
    class _cls1
        implements Runnable
    {

        public void run()
        {
            SubtitleTrack subtitletrack = track;
            subtitletrack;
            JVM INSTR monitorenter ;
            SubtitleTrack._2D_set0(SubtitleTrack.this, null);
            updateActiveCues(true, thenMs);
            updateView(mActiveCues);
            subtitletrack;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final SubtitleTrack this$0;
        final long val$thenMs;
        final SubtitleTrack val$track;

            
            {
                this$0 = SubtitleTrack.this;
                track = subtitletrack1;
                thenMs = l;
                super();
            }
    }

}
