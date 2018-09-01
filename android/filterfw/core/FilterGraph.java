// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.filterpacks.base.FrameBranch;
import android.filterpacks.base.NullFilter;
import android.util.Log;
import java.util.*;

// Referenced classes of package android.filterfw.core:
//            OutputPort, InputPort, KeyValueMap, Filter, 
//            FrameFormat, FilterContext

public class FilterGraph
{

    public FilterGraph()
    {
        mFilters = new HashSet();
        mNameMap = new HashMap();
        mPreconnections = new HashMap();
        mIsReady = false;
        mAutoBranchMode = 0;
        mTypeCheckMode = 2;
        mDiscardUnconnectedOutputs = false;
        TAG = "FilterGraph";
        mLogVerbose = Log.isLoggable(TAG, 2);
    }

    private void checkConnections()
    {
    }

    private void connectPorts()
    {
        int i = 1;
        Iterator iterator = mPreconnections.entrySet().iterator();
        do
        {
            if(iterator.hasNext())
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                Object obj1 = (OutputPort)entry.getKey();
                LinkedList linkedlist = (LinkedList)entry.getValue();
                if(linkedlist.size() == 1)
                {
                    ((OutputPort) (obj1)).connectTo((InputPort)linkedlist.get(0));
                    continue;
                }
                if(mAutoBranchMode == 0)
                    throw new RuntimeException((new StringBuilder()).append("Attempting to connect ").append(obj1).append(" to multiple ").append("filter ports! Enable auto-branching to allow this.").toString());
                if(mLogVerbose)
                    Log.v(TAG, (new StringBuilder()).append("Creating branch for ").append(obj1).append("!").toString());
                if(mAutoBranchMode == 1)
                {
                    Object obj = new FrameBranch((new StringBuilder()).append("branch").append(i).toString());
                    new KeyValueMap();
                    ((FrameBranch) (obj)).initWithAssignmentList(new Object[] {
                        "outputs", Integer.valueOf(linkedlist.size())
                    });
                    addFilter(((Filter) (obj)));
                    ((OutputPort) (obj1)).connectTo(((FrameBranch) (obj)).getInputPort("in"));
                    obj1 = linkedlist.iterator();
                    for(obj = ((Filter) (obj)).getOutputPorts().iterator(); ((Iterator) (obj)).hasNext(); ((OutputPort)((Iterator) (obj)).next()).connectTo((InputPort)((Iterator) (obj1)).next()));
                } else
                {
                    throw new RuntimeException("TODO: Unsynced branches not implemented yet!");
                }
            } else
            {
                mPreconnections.clear();
                return;
            }
            i++;
        } while(true);
    }

    private void discardUnconnectedOutputs()
    {
        LinkedList linkedlist = new LinkedList();
        for(Iterator iterator = mFilters.iterator(); iterator.hasNext();)
        {
            Filter filter = (Filter)iterator.next();
            int i = 0;
            Iterator iterator1 = filter.getOutputPorts().iterator();
            while(iterator1.hasNext()) 
            {
                OutputPort outputport = (OutputPort)iterator1.next();
                if(!outputport.isConnected())
                {
                    if(mLogVerbose)
                        Log.v(TAG, (new StringBuilder()).append("Autoconnecting unconnected ").append(outputport).append(" to Null filter.").toString());
                    NullFilter nullfilter = new NullFilter((new StringBuilder()).append(filter.getName()).append("ToNull").append(i).toString());
                    nullfilter.init();
                    linkedlist.add(nullfilter);
                    outputport.connectTo(nullfilter.getInputPort("frame"));
                    i++;
                }
            }
        }

        for(Iterator iterator2 = linkedlist.iterator(); iterator2.hasNext(); addFilter((Filter)iterator2.next()));
    }

    private HashSet getSourceFilters()
    {
        HashSet hashset = new HashSet();
        Iterator iterator = getFilters().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Filter filter = (Filter)iterator.next();
            if(filter.getNumberOfConnectedInputs() == 0)
            {
                if(mLogVerbose)
                    Log.v(TAG, (new StringBuilder()).append("Found source filter: ").append(filter).toString());
                hashset.add(filter);
            }
        } while(true);
        return hashset;
    }

    private void preconnect(OutputPort outputport, InputPort inputport)
    {
        LinkedList linkedlist = (LinkedList)mPreconnections.get(outputport);
        LinkedList linkedlist1 = linkedlist;
        if(linkedlist == null)
        {
            linkedlist1 = new LinkedList();
            mPreconnections.put(outputport, linkedlist1);
        }
        linkedlist1.add(inputport);
    }

    private boolean readyForProcessing(Filter filter, Set set)
    {
        if(set.contains(filter))
            return false;
        for(filter = filter.getInputPorts().iterator(); filter.hasNext();)
        {
            Filter filter1 = ((InputPort)filter.next()).getSourceFilter();
            if(filter1 != null && set.contains(filter1) ^ true)
                return false;
        }

        return true;
    }

    private void removeFilter(Filter filter)
    {
        mFilters.remove(filter);
        mNameMap.remove(filter.getName());
    }

    private void runTypeCheck()
    {
        Stack stack = new Stack();
        HashSet hashset = new HashSet();
        stack.addAll(getSourceFilters());
        while(!stack.empty()) 
        {
            Filter filter = (Filter)stack.pop();
            hashset.add(filter);
            updateOutputs(filter);
            if(mLogVerbose)
                Log.v(TAG, (new StringBuilder()).append("Running type check on ").append(filter).append("...").toString());
            runTypeCheckOn(filter);
            Iterator iterator = filter.getOutputPorts().iterator();
            while(iterator.hasNext()) 
            {
                Filter filter1 = ((OutputPort)iterator.next()).getTargetFilter();
                if(filter1 != null && readyForProcessing(filter1, hashset))
                    stack.push(filter1);
            }
        }
        if(hashset.size() != getFilters().size())
            throw new RuntimeException("Could not schedule all filters! Is your graph malformed?");
        else
            return;
    }

    private void runTypeCheckOn(Filter filter)
    {
        Iterator iterator = filter.getInputPorts().iterator();
_L6:
        InputPort inputport;
        FrameFormat frameformat;
        FrameFormat frameformat1;
        boolean flag;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_280;
            inputport = (InputPort)iterator.next();
            if(mLogVerbose)
                Log.v(TAG, (new StringBuilder()).append("Type checking port ").append(inputport).toString());
            frameformat = inputport.getSourceFormat();
            frameformat1 = inputport.getPortFormat();
        } while(frameformat == null || frameformat1 == null);
        if(mLogVerbose)
            Log.v(TAG, (new StringBuilder()).append("Checking ").append(frameformat).append(" against ").append(frameformat1).append(".").toString());
        flag = true;
        mTypeCheckMode;
        JVM INSTR tableswitch 0 2: default 172
    //                   0 238
    //                   1 246
    //                   2 263;
           goto _L1 _L2 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_263;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L7:
        if(!flag)
            throw new RuntimeException((new StringBuilder()).append("Type mismatch: Filter ").append(filter).append(" expects a ").append("format of type ").append(frameformat1).append(" but got a format of type ").append(frameformat).append("!").toString());
        if(true) goto _L6; else goto _L5
_L5:
        inputport.setChecksType(false);
          goto _L7
_L3:
        flag = frameformat.mayBeCompatibleWith(frameformat1);
        inputport.setChecksType(true);
          goto _L7
        flag = frameformat.isCompatibleWith(frameformat1);
        inputport.setChecksType(false);
          goto _L7
    }

    private void updateOutputs(Filter filter)
    {
        Iterator iterator = filter.getOutputPorts().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            OutputPort outputport = (OutputPort)iterator.next();
            Object obj = outputport.getBasePort();
            if(obj != null)
            {
                obj = ((InputPort) (obj)).getSourceFormat();
                obj = filter.getOutputFormat(outputport.getName(), ((FrameFormat) (obj)));
                if(obj == null)
                    throw new RuntimeException((new StringBuilder()).append("Filter did not return an output format for ").append(outputport).append("!").toString());
                outputport.setPortFormat(((FrameFormat) (obj)));
            }
        } while(true);
    }

    public boolean addFilter(Filter filter)
    {
        if(!containsFilter(filter))
        {
            mFilters.add(filter);
            mNameMap.put(filter.getName(), filter);
            return true;
        } else
        {
            return false;
        }
    }

    public void beginProcessing()
    {
        if(mLogVerbose)
            Log.v(TAG, "Opening all filter connections...");
        for(Iterator iterator = mFilters.iterator(); iterator.hasNext(); ((Filter)iterator.next()).openOutputs());
        mIsReady = true;
    }

    public void closeFilters(FilterContext filtercontext)
    {
        if(mLogVerbose)
            Log.v(TAG, "Closing all filters...");
        for(Iterator iterator = mFilters.iterator(); iterator.hasNext(); ((Filter)iterator.next()).performClose(filtercontext));
        mIsReady = false;
    }

    public void connect(Filter filter, String s, Filter filter1, String s1)
    {
        if(filter == null || filter1 == null)
            throw new IllegalArgumentException("Passing null Filter in connect()!");
        if(!containsFilter(filter) || containsFilter(filter1) ^ true)
            throw new RuntimeException("Attempting to connect filter not in graph!");
        OutputPort outputport = filter.getOutputPort(s);
        InputPort inputport = filter1.getInputPort(s1);
        if(outputport == null)
            throw new RuntimeException((new StringBuilder()).append("Unknown output port '").append(s).append("' on Filter ").append(filter).append("!").toString());
        if(inputport == null)
        {
            throw new RuntimeException((new StringBuilder()).append("Unknown input port '").append(s1).append("' on Filter ").append(filter1).append("!").toString());
        } else
        {
            preconnect(outputport, inputport);
            return;
        }
    }

    public void connect(String s, String s1, String s2, String s3)
    {
        Filter filter = getFilter(s);
        Filter filter1 = getFilter(s2);
        if(filter == null)
            throw new RuntimeException((new StringBuilder()).append("Attempting to connect unknown source filter '").append(s).append("'!").toString());
        if(filter1 == null)
        {
            throw new RuntimeException((new StringBuilder()).append("Attempting to connect unknown target filter '").append(s2).append("'!").toString());
        } else
        {
            connect(filter, s1, filter1, s3);
            return;
        }
    }

    public boolean containsFilter(Filter filter)
    {
        return mFilters.contains(filter);
    }

    public void flushFrames()
    {
        for(Iterator iterator = mFilters.iterator(); iterator.hasNext(); ((Filter)iterator.next()).clearOutputs());
    }

    public Filter getFilter(String s)
    {
        return (Filter)mNameMap.get(s);
    }

    public Set getFilters()
    {
        return mFilters;
    }

    public boolean isReady()
    {
        return mIsReady;
    }

    public void setAutoBranchMode(int i)
    {
        mAutoBranchMode = i;
    }

    public void setDiscardUnconnectedOutputs(boolean flag)
    {
        mDiscardUnconnectedOutputs = flag;
    }

    public void setTypeCheckMode(int i)
    {
        mTypeCheckMode = i;
    }

    void setupFilters()
    {
        if(mDiscardUnconnectedOutputs)
            discardUnconnectedOutputs();
        connectPorts();
        checkConnections();
        runTypeCheck();
    }

    public void tearDown(FilterContext filtercontext)
    {
        if(!mFilters.isEmpty())
        {
            flushFrames();
            for(Iterator iterator = mFilters.iterator(); iterator.hasNext(); ((Filter)iterator.next()).performTearDown(filtercontext));
            mFilters.clear();
            mNameMap.clear();
            mIsReady = false;
        }
    }

    public static final int AUTOBRANCH_OFF = 0;
    public static final int AUTOBRANCH_SYNCED = 1;
    public static final int AUTOBRANCH_UNSYNCED = 2;
    public static final int TYPECHECK_DYNAMIC = 1;
    public static final int TYPECHECK_OFF = 0;
    public static final int TYPECHECK_STRICT = 2;
    private String TAG;
    private int mAutoBranchMode;
    private boolean mDiscardUnconnectedOutputs;
    private HashSet mFilters;
    private boolean mIsReady;
    private boolean mLogVerbose;
    private HashMap mNameMap;
    private HashMap mPreconnections;
    private int mTypeCheckMode;
}
