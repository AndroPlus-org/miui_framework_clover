// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.util.Log;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.media:
//            SubtitleTrack, TtmlNodeListener, TtmlParser, TtmlNode, 
//            TtmlCue, TtmlUtils, MediaTimeProvider, TtmlRenderingWidget, 
//            MediaFormat

class TtmlTrack extends SubtitleTrack
    implements TtmlNodeListener
{

    TtmlTrack(TtmlRenderingWidget ttmlrenderingwidget, MediaFormat mediaformat)
    {
        super(mediaformat);
        mRenderingWidget = ttmlrenderingwidget;
        mParsingData = "";
    }

    private void addTimeEvents(TtmlNode ttmlnode)
    {
        mTimeEvents.add(Long.valueOf(ttmlnode.mStartTimeMs));
        mTimeEvents.add(Long.valueOf(ttmlnode.mEndTimeMs));
        for(int i = 0; i < ttmlnode.mChildren.size(); i++)
            addTimeEvents((TtmlNode)ttmlnode.mChildren.get(i));

    }

    private List getActiveNodes(long l, long l1)
    {
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < mTtmlNodes.size(); i++)
        {
            TtmlNode ttmlnode = (TtmlNode)mTtmlNodes.get(i);
            if(ttmlnode.isActive(l, l1))
                arraylist.add(ttmlnode);
        }

        return arraylist;
    }

    public TtmlCue getNextResult()
    {
        while(mTimeEvents.size() >= 2) 
        {
            long l = ((Long)mTimeEvents.pollFirst()).longValue();
            long l1 = ((Long)mTimeEvents.first()).longValue();
            if(!getActiveNodes(l, l1).isEmpty())
                return new TtmlCue(l, l1, TtmlUtils.applySpacePolicy(TtmlUtils.extractText(mRootNode, l, l1), false), TtmlUtils.extractTtmlFragment(mRootNode, l, l1));
        }
        return null;
    }

    public volatile SubtitleTrack.RenderingWidget getRenderingWidget()
    {
        return getRenderingWidget();
    }

    public TtmlRenderingWidget getRenderingWidget()
    {
        return mRenderingWidget;
    }

    public void onData(byte abyte0[], boolean flag, long l)
    {
        Object obj;
        obj = JVM INSTR new #162 <Class String>;
        ((String) (obj)).String(abyte0, "UTF-8");
        abyte0 = mParser;
        abyte0;
        JVM INSTR monitorenter ;
        if(mCurrentRunID != null && l != mCurrentRunID.longValue())
        {
            obj = JVM INSTR new #171 <Class IllegalStateException>;
            StringBuilder stringbuilder = JVM INSTR new #173 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            ((IllegalStateException) (obj)).IllegalStateException(stringbuilder.append("Run #").append(mCurrentRunID).append(" in progress.  Cannot process run #").append(l).toString());
            throw obj;
        }
          goto _L1
        obj;
        abyte0;
        JVM INSTR monitorexit ;
        throw obj;
        abyte0;
        Log.w("TtmlTrack", (new StringBuilder()).append("subtitle data is not UTF-8 encoded: ").append(abyte0).toString());
_L4:
        return;
_L1:
        mCurrentRunID = Long.valueOf(l);
        StringBuilder stringbuilder1 = JVM INSTR new #173 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        mParsingData = stringbuilder1.append(mParsingData).append(((String) (obj))).toString();
        if(!flag) goto _L3; else goto _L2
_L2:
        mParser.parse(mParsingData, mCurrentRunID.longValue());
_L5:
        finishedRun(l);
        mParsingData = "";
        mCurrentRunID = null;
_L3:
        abyte0;
        JVM INSTR monitorexit ;
          goto _L4
        Object obj1;
        obj1;
        ((IOException) (obj1)).printStackTrace();
          goto _L5
        obj1;
        ((XmlPullParserException) (obj1)).printStackTrace();
          goto _L5
    }

    public void onRootNodeParsed(TtmlNode ttmlnode)
    {
        mRootNode = ttmlnode;
        do
        {
            ttmlnode = getNextResult();
            if(ttmlnode != null)
            {
                addCue(ttmlnode);
            } else
            {
                mRootNode = null;
                mTtmlNodes.clear();
                mTimeEvents.clear();
                return;
            }
        } while(true);
    }

    public void onTtmlNodeParsed(TtmlNode ttmlnode)
    {
        mTtmlNodes.addLast(ttmlnode);
        addTimeEvents(ttmlnode);
    }

    public void updateView(Vector vector)
    {
        if(!mVisible)
            return;
        if(DEBUG && mTimeProvider != null)
            try
            {
                StringBuilder stringbuilder = JVM INSTR new #173 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("TtmlTrack", stringbuilder.append("at ").append(mTimeProvider.getCurrentTimeUs(false, true) / 1000L).append(" ms the active cues are:").toString());
            }
            catch(IllegalStateException illegalstateexception)
            {
                Log.d("TtmlTrack", "at (illegal state) the active cues are:");
            }
        mRenderingWidget.setActiveCues(vector);
    }

    private static final String TAG = "TtmlTrack";
    private Long mCurrentRunID;
    private final TtmlParser mParser = new TtmlParser(this);
    private String mParsingData;
    private final TtmlRenderingWidget mRenderingWidget;
    private TtmlNode mRootNode;
    private final TreeSet mTimeEvents = new TreeSet();
    private final LinkedList mTtmlNodes = new LinkedList();
}
