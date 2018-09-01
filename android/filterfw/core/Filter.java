// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.filterfw.format.ObjectFormat;
import android.filterfw.io.GraphIOException;
import android.filterfw.io.TextGraphReader;
import android.util.Log;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

// Referenced classes of package android.filterfw.core:
//            GenerateFinalPort, KeyValueMap, GenerateFieldPort, GenerateProgramPort, 
//            GenerateProgramPorts, InputPort, OutputPort, FilterPort, 
//            Frame, FilterContext, FrameManager, SimpleFrame, 
//            MutableFrameFormat, SerializedFrame, FrameFormat, FinalPort, 
//            FieldPort, StreamPort, ProgramPort, ProtocolException, 
//            Program

public abstract class Filter
{

    public Filter(String s)
    {
        mInputCount = -1;
        mOutputCount = -1;
        mStatus = 0;
        mIsOpen = false;
        mName = s;
        mFramesToRelease = new HashSet();
        mFramesToSet = new HashMap();
        mStatus = 0;
        mLogVerbose = Log.isLoggable("Filter", 2);
    }

    private final void addAndSetFinalPorts(KeyValueMap keyvaluemap)
    {
        Field afield[];
        int i;
        int j;
        afield = getClass().getDeclaredFields();
        i = 0;
        j = afield.length;
_L2:
        Object obj;
        GenerateFinalPort generatefinalport;
        if(i >= j)
            break MISSING_BLOCK_LABEL_180;
        Field field = afield[i];
        obj = field.getAnnotation(android/filterfw/core/GenerateFinalPort);
        if(obj != null)
        {
            generatefinalport = (GenerateFinalPort)obj;
            if(generatefinalport.name().isEmpty())
                obj = field.getName();
            else
                obj = generatefinalport.name();
            addFieldPort(((String) (obj)), field, generatefinalport.hasDefault(), true);
            if(!keyvaluemap.containsKey(obj))
                break; /* Loop/switch isn't completed */
            setImmediateInputValue(((String) (obj)), keyvaluemap.get(obj));
            keyvaluemap.remove(obj);
        }
_L4:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        if(generatefinalport.hasDefault()) goto _L4; else goto _L3
_L3:
        throw new RuntimeException((new StringBuilder()).append("No value specified for final input port '").append(((String) (obj))).append("' of filter ").append(this).append("!").toString());
    }

    private final void addAnnotatedPorts()
    {
        Field afield[] = getClass().getDeclaredFields();
        int i = afield.length;
        int j = 0;
        while(j < i) 
        {
            Field field = afield[j];
            java.lang.annotation.Annotation annotation = field.getAnnotation(android/filterfw/core/GenerateFieldPort);
            if(annotation != null)
            {
                addFieldGenerator((GenerateFieldPort)annotation, field);
            } else
            {
                java.lang.annotation.Annotation annotation1 = field.getAnnotation(android/filterfw/core/GenerateProgramPort);
                if(annotation1 != null)
                {
                    addProgramGenerator((GenerateProgramPort)annotation1, field);
                } else
                {
                    java.lang.annotation.Annotation annotation2 = field.getAnnotation(android/filterfw/core/GenerateProgramPorts);
                    if(annotation2 != null)
                    {
                        GenerateProgramPort agenerateprogramport[] = ((GenerateProgramPorts)annotation2).value();
                        int k = agenerateprogramport.length;
                        int l = 0;
                        while(l < k) 
                        {
                            addProgramGenerator(agenerateprogramport[l], field);
                            l++;
                        }
                    }
                }
            }
            j++;
        }
    }

    private final void addFieldGenerator(GenerateFieldPort generatefieldport, Field field)
    {
        String s;
        if(generatefieldport.name().isEmpty())
            s = field.getName();
        else
            s = generatefieldport.name();
        addFieldPort(s, field, generatefieldport.hasDefault(), false);
    }

    private final void addProgramGenerator(GenerateProgramPort generateprogramport, Field field)
    {
        String s = generateprogramport.name();
        String s1;
        if(generateprogramport.variableName().isEmpty())
            s1 = s;
        else
            s1 = generateprogramport.variableName();
        addProgramPort(s, s1, field, generateprogramport.type(), generateprogramport.hasDefault());
    }

    private final void closePorts()
    {
        if(mLogVerbose)
            Log.v("Filter", (new StringBuilder()).append("Closing all ports on ").append(this).append("!").toString());
        for(Iterator iterator = mInputPorts.values().iterator(); iterator.hasNext(); ((InputPort)iterator.next()).close());
        for(Iterator iterator1 = mOutputPorts.values().iterator(); iterator1.hasNext(); ((OutputPort)iterator1.next()).close());
    }

    private final boolean filterMustClose()
    {
        for(Iterator iterator = mInputPorts.values().iterator(); iterator.hasNext();)
        {
            InputPort inputport = (InputPort)iterator.next();
            if(inputport.filterMustClose())
            {
                if(mLogVerbose)
                    Log.v("Filter", (new StringBuilder()).append("Filter ").append(this).append(" must close due to port ").append(inputport).toString());
                return true;
            }
        }

        for(Iterator iterator1 = mOutputPorts.values().iterator(); iterator1.hasNext();)
        {
            OutputPort outputport = (OutputPort)iterator1.next();
            if(outputport.filterMustClose())
            {
                if(mLogVerbose)
                    Log.v("Filter", (new StringBuilder()).append("Filter ").append(this).append(" must close due to port ").append(outputport).toString());
                return true;
            }
        }

        return false;
    }

    private final void initFinalPorts(KeyValueMap keyvaluemap)
    {
        mInputPorts = new HashMap();
        mOutputPorts = new HashMap();
        addAndSetFinalPorts(keyvaluemap);
    }

    private final void initRemainingPorts(KeyValueMap keyvaluemap)
    {
        addAnnotatedPorts();
        setupPorts();
        setInitialInputValues(keyvaluemap);
    }

    private final boolean inputConditionsMet()
    {
        for(Iterator iterator = mInputPorts.values().iterator(); iterator.hasNext();)
        {
            InputPort inputport = (InputPort)iterator.next();
            if(!inputport.isReady())
            {
                if(mLogVerbose)
                    Log.v("Filter", (new StringBuilder()).append("Input condition not met: ").append(inputport).append("!").toString());
                return false;
            }
        }

        return true;
    }

    public static final boolean isAvailable(String s)
    {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try
        {
            s = classloader.loadClass(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        try
        {
            s.asSubclass(android/filterfw/core/Filter);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        return true;
    }

    private final boolean outputConditionsMet()
    {
        for(Iterator iterator = mOutputPorts.values().iterator(); iterator.hasNext();)
        {
            OutputPort outputport = (OutputPort)iterator.next();
            if(!outputport.isReady())
            {
                if(mLogVerbose)
                    Log.v("Filter", (new StringBuilder()).append("Output condition not met: ").append(outputport).append("!").toString());
                return false;
            }
        }

        return true;
    }

    private final void releasePulledFrames(FilterContext filtercontext)
    {
        Frame frame;
        for(Iterator iterator = mFramesToRelease.iterator(); iterator.hasNext(); filtercontext.getFrameManager().releaseFrame(frame))
            frame = (Frame)iterator.next();

        mFramesToRelease.clear();
    }

    private final void setImmediateInputValue(String s, Object obj)
    {
        if(mLogVerbose)
            Log.v("Filter", (new StringBuilder()).append("Setting immediate value ").append(obj).append(" for port ").append(s).append("!").toString());
        s = getInputPort(s);
        s.open();
        s.setFrame(SimpleFrame.wrapObject(obj, null));
    }

    private final void setInitialInputValues(KeyValueMap keyvaluemap)
    {
        java.util.Map.Entry entry;
        for(keyvaluemap = keyvaluemap.entrySet().iterator(); keyvaluemap.hasNext(); setInputValue((String)entry.getKey(), entry.getValue()))
            entry = (java.util.Map.Entry)keyvaluemap.next();

    }

    private final void transferInputFrames(FilterContext filtercontext)
    {
        for(Iterator iterator = mInputPorts.values().iterator(); iterator.hasNext(); ((InputPort)iterator.next()).transfer(filtercontext));
    }

    private final Frame wrapInputValue(String s, Object obj)
    {
        MutableFrameFormat mutableframeformat = ObjectFormat.fromObject(obj, 1);
        boolean flag;
        if(obj == null)
        {
            s = getInputPort(s).getPortFormat();
            if(s == null)
                s = null;
            else
                s = s.getObjectClass();
            mutableframeformat.setObjectClass(s);
        }
        if(!(obj instanceof Number) && (obj instanceof Boolean) ^ true && (obj instanceof String) ^ true)
            flag = obj instanceof Serializable;
        else
            flag = false;
        if(flag)
            s = new SerializedFrame(mutableframeformat, null);
        else
            s = new SimpleFrame(mutableframeformat, null);
        s.setObjectValue(obj);
        return s;
    }

    protected void addFieldPort(String s, Field field, boolean flag, boolean flag1)
    {
        field.setAccessible(true);
        Object obj;
        if(flag1)
            obj = new FinalPort(this, s, field, flag);
        else
            obj = new FieldPort(this, s, field, flag);
        if(mLogVerbose)
            Log.v("Filter", (new StringBuilder()).append("Filter ").append(this).append(" adding ").append(obj).toString());
        ((InputPort) (obj)).setPortFormat(ObjectFormat.fromClass(field.getType(), 1));
        mInputPorts.put(s, obj);
    }

    protected void addInputPort(String s)
    {
        addMaskedInputPort(s, null);
    }

    protected void addMaskedInputPort(String s, FrameFormat frameformat)
    {
        StreamPort streamport = new StreamPort(this, s);
        if(mLogVerbose)
            Log.v("Filter", (new StringBuilder()).append("Filter ").append(this).append(" adding ").append(streamport).toString());
        mInputPorts.put(s, streamport);
        streamport.setPortFormat(frameformat);
    }

    protected void addOutputBasedOnInput(String s, String s1)
    {
        OutputPort outputport = new OutputPort(this, s);
        if(mLogVerbose)
            Log.v("Filter", (new StringBuilder()).append("Filter ").append(this).append(" adding ").append(outputport).toString());
        outputport.setBasePort(getInputPort(s1));
        mOutputPorts.put(s, outputport);
    }

    protected void addOutputPort(String s, FrameFormat frameformat)
    {
        OutputPort outputport = new OutputPort(this, s);
        if(mLogVerbose)
            Log.v("Filter", (new StringBuilder()).append("Filter ").append(this).append(" adding ").append(outputport).toString());
        outputport.setPortFormat(frameformat);
        mOutputPorts.put(s, outputport);
    }

    protected void addProgramPort(String s, String s1, Field field, Class class1, boolean flag)
    {
        field.setAccessible(true);
        s1 = new ProgramPort(this, s, s1, field, flag);
        if(mLogVerbose)
            Log.v("Filter", (new StringBuilder()).append("Filter ").append(this).append(" adding ").append(s1).toString());
        s1.setPortFormat(ObjectFormat.fromClass(class1, 1));
        mInputPorts.put(s, s1);
    }

    final boolean canProcess()
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        if(mLogVerbose)
        {
            StringBuilder stringbuilder = JVM INSTR new #140 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.v("Filter", stringbuilder.append("Checking if can process: ").append(this).append(" (").append(mStatus).append(").").toString());
        }
        if(mStatus > 3)
            break MISSING_BLOCK_LABEL_82;
        if(inputConditionsMet())
            flag = outputConditionsMet();
        return flag;
        this;
        JVM INSTR monitorexit ;
        return false;
        Exception exception;
        exception;
        throw exception;
    }

    final void clearInputs()
    {
        for(Iterator iterator = mInputPorts.values().iterator(); iterator.hasNext(); ((InputPort)iterator.next()).clear());
    }

    final void clearOutputs()
    {
        for(Iterator iterator = mOutputPorts.values().iterator(); iterator.hasNext(); ((OutputPort)iterator.next()).clear());
    }

    public void close(FilterContext filtercontext)
    {
    }

    protected void closeOutputPort(String s)
    {
        getOutputPort(s).close();
    }

    protected void delayNextProcess(int i)
    {
        mSleepDelay = i;
        mStatus = 4;
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
    }

    public String getFilterClassName()
    {
        return getClass().getSimpleName();
    }

    public final FrameFormat getInputFormat(String s)
    {
        return getInputPort(s).getSourceFormat();
    }

    public final InputPort getInputPort(String s)
    {
        if(mInputPorts == null)
            throw new NullPointerException((new StringBuilder()).append("Attempting to access input port '").append(s).append("' of ").append(this).append(" before Filter has been initialized!").toString());
        InputPort inputport = (InputPort)mInputPorts.get(s);
        if(inputport == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown input port '").append(s).append("' on filter ").append(this).append("!").toString());
        else
            return inputport;
    }

    final Collection getInputPorts()
    {
        return mInputPorts.values();
    }

    public final String getName()
    {
        return mName;
    }

    public final int getNumberOfConnectedInputs()
    {
        int i = 0;
        Iterator iterator = mInputPorts.values().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            if(((InputPort)iterator.next()).isConnected())
                i++;
        } while(true);
        return i;
    }

    public final int getNumberOfConnectedOutputs()
    {
        int i = 0;
        Iterator iterator = mOutputPorts.values().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            if(((OutputPort)iterator.next()).isConnected())
                i++;
        } while(true);
        return i;
    }

    public final int getNumberOfInputs()
    {
        int i;
        if(mOutputPorts == null)
            i = 0;
        else
            i = mInputPorts.size();
        return i;
    }

    public final int getNumberOfOutputs()
    {
        int i;
        if(mInputPorts == null)
            i = 0;
        else
            i = mOutputPorts.size();
        return i;
    }

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        return null;
    }

    public final OutputPort getOutputPort(String s)
    {
        if(mInputPorts == null)
            throw new NullPointerException((new StringBuilder()).append("Attempting to access output port '").append(s).append("' of ").append(this).append(" before Filter has been initialized!").toString());
        OutputPort outputport = (OutputPort)mOutputPorts.get(s);
        if(outputport == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown output port '").append(s).append("' on filter ").append(this).append("!").toString());
        else
            return outputport;
    }

    final Collection getOutputPorts()
    {
        return mOutputPorts.values();
    }

    public final int getSleepDelay()
    {
        return 250;
    }

    final int getStatus()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mStatus;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public final void init()
        throws ProtocolException
    {
        initWithValueMap(new KeyValueMap());
    }

    protected void initProgramInputs(Program program, FilterContext filtercontext)
    {
        if(program != null)
        {
            Iterator iterator = mInputPorts.values().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                InputPort inputport = (InputPort)iterator.next();
                if(inputport.getTarget() == program)
                    inputport.transfer(filtercontext);
            } while(true);
        }
    }

    public final transient void initWithAssignmentList(Object aobj[])
    {
        KeyValueMap keyvaluemap = new KeyValueMap();
        keyvaluemap.setKeyValues(aobj);
        initWithValueMap(keyvaluemap);
    }

    public final void initWithAssignmentString(String s)
    {
        try
        {
            TextGraphReader textgraphreader = JVM INSTR new #541 <Class TextGraphReader>;
            textgraphreader.TextGraphReader();
            initWithValueMap(textgraphreader.readKeyValueAssignments(s));
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new IllegalArgumentException(s.getMessage());
        }
    }

    public final void initWithValueMap(KeyValueMap keyvaluemap)
    {
        initFinalPorts(keyvaluemap);
        initRemainingPorts(keyvaluemap);
        mStatus = 1;
    }

    public boolean isOpen()
    {
        return mIsOpen;
    }

    final void notifyFieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        if(mStatus == 3 || mStatus == 2)
            fieldPortValueUpdated(s, filtercontext);
    }

    public void open(FilterContext filtercontext)
    {
    }

    final void openOutputs()
    {
        if(mLogVerbose)
            Log.v("Filter", (new StringBuilder()).append("Opening all output ports on ").append(this).append("!").toString());
        Iterator iterator = mOutputPorts.values().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            OutputPort outputport = (OutputPort)iterator.next();
            if(!outputport.isOpen())
                outputport.open();
        } while(true);
    }

    protected void parametersUpdated(Set set)
    {
    }

    final void performClose(FilterContext filtercontext)
    {
        this;
        JVM INSTR monitorenter ;
        if(mIsOpen)
        {
            if(mLogVerbose)
            {
                StringBuilder stringbuilder = JVM INSTR new #140 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.v("Filter", stringbuilder.append("Closing ").append(this).toString());
            }
            mIsOpen = false;
            mStatus = 2;
            close(filtercontext);
            closePorts();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        filtercontext;
        throw filtercontext;
    }

    final void performOpen(FilterContext filtercontext)
    {
        this;
        JVM INSTR monitorenter ;
        if(mIsOpen)
            break MISSING_BLOCK_LABEL_189;
        if(mStatus == 1)
        {
            if(mLogVerbose)
            {
                StringBuilder stringbuilder = JVM INSTR new #140 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.v("Filter", stringbuilder.append("Preparing ").append(this).toString());
            }
            prepare(filtercontext);
            mStatus = 2;
        }
        if(mStatus == 2)
        {
            if(mLogVerbose)
            {
                StringBuilder stringbuilder1 = JVM INSTR new #140 <Class StringBuilder>;
                stringbuilder1.StringBuilder();
                Log.v("Filter", stringbuilder1.append("Opening ").append(this).toString());
            }
            open(filtercontext);
            mStatus = 3;
        }
        if(mStatus != 3)
        {
            filtercontext = JVM INSTR new #138 <Class RuntimeException>;
            StringBuilder stringbuilder2 = JVM INSTR new #140 <Class StringBuilder>;
            stringbuilder2.StringBuilder();
            filtercontext.RuntimeException(stringbuilder2.append("Filter ").append(this).append(" was brought into invalid state during ").append("opening (state: ").append(mStatus).append(")!").toString());
            throw filtercontext;
        }
        break MISSING_BLOCK_LABEL_184;
        filtercontext;
        this;
        JVM INSTR monitorexit ;
        throw filtercontext;
        mIsOpen = true;
        this;
        JVM INSTR monitorexit ;
    }

    final void performProcess(FilterContext filtercontext)
    {
        this;
        JVM INSTR monitorenter ;
        if(mStatus == 7)
        {
            RuntimeException runtimeexception = JVM INSTR new #138 <Class RuntimeException>;
            filtercontext = JVM INSTR new #140 <Class StringBuilder>;
            filtercontext.StringBuilder();
            runtimeexception.RuntimeException(filtercontext.append("Filter ").append(this).append(" is already torn down!").toString());
            throw runtimeexception;
        }
        break MISSING_BLOCK_LABEL_53;
        filtercontext;
        this;
        JVM INSTR monitorexit ;
        throw filtercontext;
        transferInputFrames(filtercontext);
        if(mStatus < 3)
            performOpen(filtercontext);
        if(mLogVerbose)
        {
            StringBuilder stringbuilder = JVM INSTR new #140 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.v("Filter", stringbuilder.append("Processing ").append(this).toString());
        }
        mCurrentTimestamp = -1L;
        process(filtercontext);
        releasePulledFrames(filtercontext);
        if(filterMustClose())
            performClose(filtercontext);
        this;
        JVM INSTR monitorexit ;
    }

    final void performTearDown(FilterContext filtercontext)
    {
        this;
        JVM INSTR monitorenter ;
        performClose(filtercontext);
        if(mStatus != 7)
        {
            tearDown(filtercontext);
            mStatus = 7;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        filtercontext;
        throw filtercontext;
    }

    protected void prepare(FilterContext filtercontext)
    {
    }

    public abstract void process(FilterContext filtercontext);

    protected final Frame pullInput(String s)
    {
        Frame frame = getInputPort(s).pullFrame();
        if(mCurrentTimestamp == -1L)
        {
            mCurrentTimestamp = frame.getTimestamp();
            if(mLogVerbose)
                Log.v("Filter", (new StringBuilder()).append("Default-setting current timestamp from input port ").append(s).append(" to ").append(mCurrentTimestamp).toString());
        }
        mFramesToRelease.add(frame);
        return frame;
    }

    final void pushInputFrame(String s, Frame frame)
    {
        this;
        JVM INSTR monitorenter ;
        s = getInputPort(s);
        if(!s.isOpen())
            s.open();
        s.pushFrame(frame);
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    final void pushInputValue(String s, Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        pushInputFrame(s, wrapInputValue(s, obj));
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    protected final void pushOutput(String s, Frame frame)
    {
        if(frame.getTimestamp() == -2L)
        {
            if(mLogVerbose)
                Log.v("Filter", (new StringBuilder()).append("Default-setting output Frame timestamp on port ").append(s).append(" to ").append(mCurrentTimestamp).toString());
            frame.setTimestamp(mCurrentTimestamp);
        }
        getOutputPort(s).pushFrame(frame);
    }

    public void setInputFrame(String s, Frame frame)
    {
        s = getInputPort(s);
        if(!s.isOpen())
            s.open();
        s.setFrame(frame);
    }

    public final void setInputValue(String s, Object obj)
    {
        setInputFrame(s, wrapInputValue(s, obj));
    }

    protected void setWaitsOnInputPort(String s, boolean flag)
    {
        getInputPort(s).setBlocking(flag);
    }

    protected void setWaitsOnOutputPort(String s, boolean flag)
    {
        getOutputPort(s).setBlocking(flag);
    }

    public abstract void setupPorts();

    public void tearDown(FilterContext filtercontext)
    {
    }

    public String toString()
    {
        return (new StringBuilder()).append("'").append(getName()).append("' (").append(getFilterClassName()).append(")").toString();
    }

    protected void transferInputPortFrame(String s, FilterContext filtercontext)
    {
        getInputPort(s).transfer(filtercontext);
    }

    final void unsetStatus(int i)
    {
        this;
        JVM INSTR monitorenter ;
        mStatus = mStatus & i;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    static final int STATUS_ERROR = 6;
    static final int STATUS_FINISHED = 5;
    static final int STATUS_PREINIT = 0;
    static final int STATUS_PREPARED = 2;
    static final int STATUS_PROCESSING = 3;
    static final int STATUS_RELEASED = 7;
    static final int STATUS_SLEEPING = 4;
    static final int STATUS_UNPREPARED = 1;
    private static final String TAG = "Filter";
    private long mCurrentTimestamp;
    private HashSet mFramesToRelease;
    private HashMap mFramesToSet;
    private int mInputCount;
    private HashMap mInputPorts;
    private boolean mIsOpen;
    private boolean mLogVerbose;
    private String mName;
    private int mOutputCount;
    private HashMap mOutputPorts;
    private int mSleepDelay;
    private int mStatus;
}
