// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;

import android.util.Log;
import android.util.Pair;
import dalvik.system.CloseGuard;
import java.util.*;

// Referenced classes of package android.renderscript:
//            BaseObj, RenderScript, RSIllegalArgumentException, Allocation, 
//            RSInvalidStateException, RSRuntimeException, Type, Script, 
//            FieldPacker

public final class ScriptGroup extends BaseObj
{
    public static final class Binding
    {

        Script.FieldID getField()
        {
            return mField;
        }

        Object getValue()
        {
            return mValue;
        }

        private final Script.FieldID mField;
        private final Object mValue;

        public Binding(Script.FieldID fieldid, Object obj)
        {
            mField = fieldid;
            mValue = obj;
        }
    }

    public static final class Builder
    {

        private Node findNode(Script.KernelID kernelid)
        {
            for(int i = 0; i < mNodes.size(); i++)
            {
                Node node = (Node)mNodes.get(i);
                for(int j = 0; j < node.mKernels.size(); j++)
                    if(kernelid == node.mKernels.get(j))
                        return node;

            }

            return null;
        }

        private Node findNode(Script script)
        {
            for(int i = 0; i < mNodes.size(); i++)
                if(script == ((Node)mNodes.get(i)).mScript)
                    return (Node)mNodes.get(i);

            return null;
        }

        private void mergeDAGs(int i, int j)
        {
            for(int k = 0; k < mNodes.size(); k++)
                if(((Node)mNodes.get(k)).dagNumber == j)
                    ((Node)mNodes.get(k)).dagNumber = i;

        }

        private void validateCycle(Node node, Node node1)
        {
            for(int i = 0; i < node.mOutputs.size(); i++)
            {
                Object obj = (ConnectLine)node.mOutputs.get(i);
                if(((ConnectLine) (obj)).mToK != null)
                {
                    Node node2 = findNode(((ConnectLine) (obj)).mToK.mScript);
                    if(node2.equals(node1))
                        throw new RSInvalidStateException("Loops in group not allowed.");
                    validateCycle(node2, node1);
                }
                if(((ConnectLine) (obj)).mToF == null)
                    continue;
                obj = findNode(((ConnectLine) (obj)).mToF.mScript);
                if(((Node) (obj)).equals(node1))
                    throw new RSInvalidStateException("Loops in group not allowed.");
                validateCycle(((Node) (obj)), node1);
            }

        }

        private void validateDAG()
        {
            for(int i = 0; i < mNodes.size(); i++)
            {
                Node node = (Node)mNodes.get(i);
                if(node.mInputs.size() != 0)
                    continue;
                if(node.mOutputs.size() == 0 && mNodes.size() > 1)
                    throw new RSInvalidStateException("Groups cannot contain unconnected scripts");
                validateDAGRecurse(node, i + 1);
            }

            int k = ((Node)mNodes.get(0)).dagNumber;
            for(int j = 0; j < mNodes.size(); j++)
                if(((Node)mNodes.get(j)).dagNumber != k)
                    throw new RSInvalidStateException("Multiple DAGs in group not allowed.");

        }

        private void validateDAGRecurse(Node node, int i)
        {
            if(node.dagNumber != 0 && node.dagNumber != i)
            {
                mergeDAGs(node.dagNumber, i);
                return;
            }
            node.dagNumber = i;
            for(int j = 0; j < node.mOutputs.size(); j++)
            {
                ConnectLine connectline = (ConnectLine)node.mOutputs.get(j);
                if(connectline.mToK != null)
                    validateDAGRecurse(findNode(connectline.mToK.mScript), i);
                if(connectline.mToF != null)
                    validateDAGRecurse(findNode(connectline.mToF.mScript), i);
            }

        }

        public Builder addConnection(Type type, Script.KernelID kernelid, Script.FieldID fieldid)
        {
            Node node = findNode(kernelid);
            if(node == null)
                throw new RSInvalidStateException("From script not found.");
            Node node1 = findNode(fieldid.mScript);
            if(node1 == null)
            {
                throw new RSInvalidStateException("To script not found.");
            } else
            {
                ConnectLine connectline = new ConnectLine(type, kernelid, fieldid);
                mLines.add(new ConnectLine(type, kernelid, fieldid));
                node.mOutputs.add(connectline);
                node1.mInputs.add(connectline);
                validateCycle(node, node);
                return this;
            }
        }

        public Builder addConnection(Type type, Script.KernelID kernelid, Script.KernelID kernelid1)
        {
            Node node = findNode(kernelid);
            if(node == null)
                throw new RSInvalidStateException("From script not found.");
            Node node1 = findNode(kernelid1);
            if(node1 == null)
            {
                throw new RSInvalidStateException("To script not found.");
            } else
            {
                ConnectLine connectline = new ConnectLine(type, kernelid, kernelid1);
                mLines.add(new ConnectLine(type, kernelid, kernelid1));
                node.mOutputs.add(connectline);
                node1.mInputs.add(connectline);
                validateCycle(node, node);
                return this;
            }
        }

        public Builder addKernel(Script.KernelID kernelid)
        {
            if(mLines.size() != 0)
                throw new RSInvalidStateException("Kernels may not be added once connections exist.");
            if(findNode(kernelid) != null)
                return this;
            mKernelCount = mKernelCount + 1;
            Node node = findNode(kernelid.mScript);
            Node node1 = node;
            if(node == null)
            {
                node1 = new Node(kernelid.mScript);
                mNodes.add(node1);
            }
            node1.mKernels.add(kernelid);
            return this;
        }

        public ScriptGroup create()
        {
            if(mNodes.size() == 0)
                throw new RSInvalidStateException("Empty script groups are not allowed");
            for(int i = 0; i < mNodes.size(); i++)
                ((Node)mNodes.get(i)).dagNumber = 0;

            validateDAG();
            ArrayList arraylist = new ArrayList();
            ArrayList arraylist1 = new ArrayList();
            long al[] = new long[mKernelCount];
            int j1 = 0;
            for(int j = 0; j < mNodes.size(); j++)
            {
                Node node = (Node)mNodes.get(j);
                for(int k1 = 0; k1 < node.mKernels.size();)
                {
                    Script.KernelID kernelid = (Script.KernelID)node.mKernels.get(k1);
                    al[j1] = kernelid.getID(mRS);
                    boolean flag = false;
                    boolean flag1 = false;
                    for(int l1 = 0; l1 < node.mInputs.size(); l1++)
                        if(((ConnectLine)node.mInputs.get(l1)).mToK == kernelid)
                            flag = true;

                    for(int i2 = 0; i2 < node.mOutputs.size(); i2++)
                        if(((ConnectLine)node.mOutputs.get(i2)).mFrom == kernelid)
                            flag1 = true;

                    if(!flag)
                        arraylist.add(new IO(kernelid));
                    if(!flag1)
                        arraylist1.add(new IO(kernelid));
                    k1++;
                    j1++;
                }

            }

            if(j1 != mKernelCount)
                throw new RSRuntimeException("Count mismatch, should not happen.");
            long al2[] = new long[mLines.size()];
            long al1[] = new long[mLines.size()];
            long al3[] = new long[mLines.size()];
            long al4[] = new long[mLines.size()];
            for(int k = 0; k < mLines.size(); k++)
            {
                ConnectLine connectline = (ConnectLine)mLines.get(k);
                al2[k] = connectline.mFrom.getID(mRS);
                if(connectline.mToK != null)
                    al1[k] = connectline.mToK.getID(mRS);
                if(connectline.mToF != null)
                    al3[k] = connectline.mToF.getID(mRS);
                al4[k] = connectline.mAllocationType.getID(mRS);
            }

            long l2 = mRS.nScriptGroupCreate(al, al2, al1, al3, al4);
            if(l2 == 0L)
                throw new RSRuntimeException("Object creation error, should not happen.");
            ScriptGroup scriptgroup = new ScriptGroup(l2, mRS);
            scriptgroup.mOutputs = new IO[arraylist1.size()];
            for(int l = 0; l < arraylist1.size(); l++)
                scriptgroup.mOutputs[l] = (IO)arraylist1.get(l);

            scriptgroup.mInputs = new IO[arraylist.size()];
            for(int i1 = 0; i1 < arraylist.size(); i1++)
                scriptgroup.mInputs[i1] = (IO)arraylist.get(i1);

            return scriptgroup;
        }

        private int mKernelCount;
        private ArrayList mLines;
        private ArrayList mNodes;
        private RenderScript mRS;

        public Builder(RenderScript renderscript)
        {
            mNodes = new ArrayList();
            mLines = new ArrayList();
            mRS = renderscript;
        }
    }

    public static final class Builder2
    {

        private Closure addInvokeInternal(Script.InvokeID invokeid, Object aobj[], Map map)
        {
            invokeid = new Closure(mRS, invokeid, aobj, map);
            mClosures.add(invokeid);
            return invokeid;
        }

        private Closure addKernelInternal(Script.KernelID kernelid, Type type, Object aobj[], Map map)
        {
            kernelid = new Closure(mRS, kernelid, type, aobj, map);
            mClosures.add(kernelid);
            return kernelid;
        }

        private boolean seperateArgsAndBindings(Object aobj[], ArrayList arraylist, Map map)
        {
            int i = 0;
_L7:
            int j = i;
            if(i >= aobj.length) goto _L2; else goto _L1
_L1:
            if(!(aobj[i] instanceof Binding)) goto _L4; else goto _L3
_L3:
            j = i;
_L2:
            if(j >= aobj.length)
                break; /* Loop/switch isn't completed */
            if(!(aobj[j] instanceof Binding))
                return false;
            arraylist = (Binding)aobj[j];
            map.put(arraylist.getField(), arraylist.getValue());
            j++;
            continue; /* Loop/switch isn't completed */
_L4:
            arraylist.add(aobj[i]);
            i++;
            continue; /* Loop/switch isn't completed */
            if(true) goto _L2; else goto _L5
_L5:
            return true;
            if(true) goto _L7; else goto _L6
_L6:
        }

        public Input addInput()
        {
            Input input = new Input();
            mInputs.add(input);
            return input;
        }

        public transient Closure addInvoke(Script.InvokeID invokeid, Object aobj[])
        {
            ArrayList arraylist = new ArrayList();
            HashMap hashmap = new HashMap();
            if(!seperateArgsAndBindings(aobj, arraylist, hashmap))
                return null;
            else
                return addInvokeInternal(invokeid, arraylist.toArray(), hashmap);
        }

        public transient Closure addKernel(Script.KernelID kernelid, Type type, Object aobj[])
        {
            ArrayList arraylist = new ArrayList();
            HashMap hashmap = new HashMap();
            if(!seperateArgsAndBindings(aobj, arraylist, hashmap))
                return null;
            else
                return addKernelInternal(kernelid, type, arraylist.toArray(), hashmap);
        }

        public transient ScriptGroup create(String s, Future afuture[])
        {
            while(s == null || s.isEmpty() || s.length() > 100 || s.equals(s.replaceAll("[^a-zA-Z0-9-]", "_")) ^ true) 
                throw new RSIllegalArgumentException("invalid script group name");
            s = new ScriptGroup(mRS, s, mClosures, mInputs, afuture);
            mClosures = new ArrayList();
            mInputs = new ArrayList();
            return s;
        }

        private static final String TAG = "ScriptGroup.Builder2";
        List mClosures;
        List mInputs;
        RenderScript mRS;

        public Builder2(RenderScript renderscript)
        {
            mRS = renderscript;
            mClosures = new ArrayList();
            mInputs = new ArrayList();
        }
    }

    public static final class Closure extends BaseObj
    {

        private void retrieveValueAndDependenceInfo(RenderScript renderscript, int i, Script.FieldID fieldid, Object obj, long al[], int ai[], long al1[], 
                long al2[])
        {
            if(obj instanceof Future)
            {
                Future future = (Future)obj;
                obj = future.getValue();
                al1[i] = future.getClosure().getID(renderscript);
                al1 = future.getFieldID();
                long l;
                if(al1 != null)
                    l = al1.getID(renderscript);
                else
                    l = 0L;
                al2[i] = l;
            } else
            {
                al1[i] = 0L;
                al2[i] = 0L;
            }
            if(obj instanceof Input)
            {
                renderscript = (Input)obj;
                if(i < mArgs.length)
                    renderscript.addReference(this, i);
                else
                    renderscript.addReference(this, fieldid);
                al[i] = 0L;
                ai[i] = 0;
            } else
            {
                renderscript = new ValueAndSize(renderscript, obj);
                al[i] = ((ValueAndSize) (renderscript)).value;
                ai[i] = ((ValueAndSize) (renderscript)).size;
            }
        }

        public void destroy()
        {
            super.destroy();
            if(mReturnValue != null)
                mReturnValue.destroy();
        }

        protected void finalize()
            throws Throwable
        {
            mReturnValue = null;
            super.finalize();
        }

        public Future getGlobal(Script.FieldID fieldid)
        {
            Future future = (Future)mGlobalFuture.get(fieldid);
            Object obj1 = future;
            if(future == null)
            {
                Object obj = mBindings.get(fieldid);
                obj1 = obj;
                if(obj instanceof Future)
                    obj1 = ((Future)obj).getValue();
                obj1 = new Future(this, fieldid, obj1);
                mGlobalFuture.put(fieldid, obj1);
            }
            return ((Future) (obj1));
        }

        public Future getReturn()
        {
            if(mReturnFuture == null)
                mReturnFuture = new Future(this, null, mReturnValue);
            return mReturnFuture;
        }

        void setArg(int i, Object obj)
        {
            Object obj1 = obj;
            if(obj instanceof Future)
                obj1 = ((Future)obj).getValue();
            mArgs[i] = obj1;
            obj = new ValueAndSize(mRS, obj1);
            mRS.nClosureSetArg(getID(mRS), i, ((ValueAndSize) (obj)).value, ((ValueAndSize) (obj)).size);
        }

        void setGlobal(Script.FieldID fieldid, Object obj)
        {
            Object obj1 = obj;
            if(obj instanceof Future)
                obj1 = ((Future)obj).getValue();
            mBindings.put(fieldid, obj1);
            obj = new ValueAndSize(mRS, obj1);
            mRS.nClosureSetGlobal(getID(mRS), fieldid.getID(mRS), ((ValueAndSize) (obj)).value, ((ValueAndSize) (obj)).size);
        }

        private static final String TAG = "Closure";
        private Object mArgs[];
        private Map mBindings;
        private FieldPacker mFP;
        private Map mGlobalFuture;
        private Future mReturnFuture;
        private Allocation mReturnValue;

        Closure(long l, RenderScript renderscript)
        {
            super(l, renderscript);
        }

        Closure(RenderScript renderscript, Script.InvokeID invokeid, Object aobj[], Map map)
        {
            super(0L, renderscript);
            mFP = FieldPacker.createFromArray(aobj);
            mArgs = aobj;
            mBindings = map;
            mGlobalFuture = new HashMap();
            int i = map.size();
            aobj = new long[i];
            long al[] = new long[i];
            int ai[] = new int[i];
            long al1[] = new long[i];
            long al2[] = new long[i];
            i = 0;
            for(map = map.entrySet().iterator(); map.hasNext();)
            {
                Object obj = (java.util.Map.Entry)map.next();
                Object obj1 = ((java.util.Map.Entry) (obj)).getValue();
                obj = (Script.FieldID)((java.util.Map.Entry) (obj)).getKey();
                aobj[i] = ((Script.FieldID) (obj)).getID(renderscript);
                retrieveValueAndDependenceInfo(renderscript, i, ((Script.FieldID) (obj)), obj1, al, ai, al1, al2);
                i++;
            }

            setID(renderscript.nInvokeClosureCreate(invokeid.getID(renderscript), mFP.getData(), ((long []) (aobj)), al, ai));
            guard.open("destroy");
        }

        Closure(RenderScript renderscript, Script.KernelID kernelid, Type type, Object aobj[], Map map)
        {
            super(0L, renderscript);
            mArgs = aobj;
            mReturnValue = Allocation.createTyped(renderscript, type);
            mBindings = map;
            mGlobalFuture = new HashMap();
            int i = aobj.length + map.size();
            type = new long[i];
            long al[] = new long[i];
            int ai[] = new int[i];
            long al1[] = new long[i];
            long al2[] = new long[i];
            for(i = 0; i < aobj.length; i++)
            {
                type[i] = 0L;
                retrieveValueAndDependenceInfo(renderscript, i, null, aobj[i], al, ai, al1, al2);
            }

            for(aobj = map.entrySet().iterator(); ((Iterator) (aobj)).hasNext();)
            {
                Object obj = (java.util.Map.Entry)((Iterator) (aobj)).next();
                map = ((Map) (((java.util.Map.Entry) (obj)).getValue()));
                obj = (Script.FieldID)((java.util.Map.Entry) (obj)).getKey();
                type[i] = ((Script.FieldID) (obj)).getID(renderscript);
                retrieveValueAndDependenceInfo(renderscript, i, ((Script.FieldID) (obj)), map, al, ai, al1, al2);
                i++;
            }

            setID(renderscript.nClosureCreate(kernelid.getID(renderscript), mReturnValue.getID(renderscript), type, al, ai, al1, al2));
            guard.open("destroy");
        }
    }

    private static final class Closure.ValueAndSize
    {

        public int size;
        public long value;

        public Closure.ValueAndSize(RenderScript renderscript, Object obj)
        {
            if(!(obj instanceof Allocation)) goto _L2; else goto _L1
_L1:
            value = ((Allocation)obj).getID(renderscript);
            size = -1;
_L4:
            return;
_L2:
            if(obj instanceof Boolean)
            {
                int i;
                if(((Boolean)obj).booleanValue())
                    i = 1;
                else
                    i = 0;
                value = i;
                size = 4;
            } else
            if(obj instanceof Integer)
            {
                value = ((Integer)obj).longValue();
                size = 4;
            } else
            if(obj instanceof Long)
            {
                value = ((Long)obj).longValue();
                size = 8;
            } else
            if(obj instanceof Float)
            {
                value = Float.floatToRawIntBits(((Float)obj).floatValue());
                size = 4;
            } else
            if(obj instanceof Double)
            {
                value = Double.doubleToRawLongBits(((Double)obj).doubleValue());
                size = 8;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }
    }

    static class ConnectLine
    {

        Type mAllocationType;
        Script.KernelID mFrom;
        Script.FieldID mToF;
        Script.KernelID mToK;

        ConnectLine(Type type, Script.KernelID kernelid, Script.FieldID fieldid)
        {
            mFrom = kernelid;
            mToF = fieldid;
            mAllocationType = type;
        }

        ConnectLine(Type type, Script.KernelID kernelid, Script.KernelID kernelid1)
        {
            mFrom = kernelid;
            mToK = kernelid1;
            mAllocationType = type;
        }
    }

    public static final class Future
    {

        Closure getClosure()
        {
            return mClosure;
        }

        Script.FieldID getFieldID()
        {
            return mFieldID;
        }

        Object getValue()
        {
            return mValue;
        }

        Closure mClosure;
        Script.FieldID mFieldID;
        Object mValue;

        Future(Closure closure, Script.FieldID fieldid, Object obj)
        {
            mClosure = closure;
            mFieldID = fieldid;
            mValue = obj;
        }
    }

    static class IO
    {

        Allocation mAllocation;
        Script.KernelID mKID;

        IO(Script.KernelID kernelid)
        {
            mKID = kernelid;
        }
    }

    public static final class Input
    {

        void addReference(Closure closure, int i)
        {
            mArgIndex.add(Pair.create(closure, Integer.valueOf(i)));
        }

        void addReference(Closure closure, Script.FieldID fieldid)
        {
            mFieldID.add(Pair.create(closure, fieldid));
        }

        Object get()
        {
            return mValue;
        }

        void set(Object obj)
        {
            mValue = obj;
            Pair pair;
            for(Iterator iterator = mArgIndex.iterator(); iterator.hasNext(); ((Closure)pair.first).setArg(((Integer)pair.second).intValue(), obj))
                pair = (Pair)iterator.next();

            Pair pair1;
            for(Iterator iterator1 = mFieldID.iterator(); iterator1.hasNext(); ((Closure)pair1.first).setGlobal((Script.FieldID)pair1.second, obj))
                pair1 = (Pair)iterator1.next();

        }

        List mArgIndex;
        List mFieldID;
        Object mValue;

        Input()
        {
            mFieldID = new ArrayList();
            mArgIndex = new ArrayList();
        }
    }

    static class Node
    {

        int dagNumber;
        ArrayList mInputs;
        ArrayList mKernels;
        Node mNext;
        ArrayList mOutputs;
        Script mScript;

        Node(Script script)
        {
            mKernels = new ArrayList();
            mInputs = new ArrayList();
            mOutputs = new ArrayList();
            mScript = script;
        }
    }


    ScriptGroup(long l, RenderScript renderscript)
    {
        super(l, renderscript);
        guard.open("destroy");
    }

    ScriptGroup(RenderScript renderscript, String s, List list, List list1, Future afuture[])
    {
        super(0L, renderscript);
        mName = s;
        mClosures = list;
        mInputs2 = list1;
        mOutputs2 = afuture;
        list1 = new long[list.size()];
        for(int i = 0; i < list1.length; i++)
            list1[i] = ((Closure)list.get(i)).getID(renderscript);

        setID(renderscript.nScriptGroup2Create(s, RenderScript.getCachePath(), list1));
        guard.open("destroy");
    }

    public void destroy()
    {
        super.destroy();
        if(mClosures != null)
        {
            for(Iterator iterator = mClosures.iterator(); iterator.hasNext(); ((Closure)iterator.next()).destroy());
        }
    }

    public void execute()
    {
        mRS.nScriptGroupExecute(getID(mRS));
    }

    public transient Object[] execute(Object aobj[])
    {
        if(aobj.length < mInputs2.size())
        {
            Log.e("ScriptGroup", (new StringBuilder()).append(toString()).append(" receives ").append(aobj.length).append(" inputs, ").append("less than expected ").append(mInputs2.size()).toString());
            return null;
        }
        if(aobj.length > mInputs2.size())
            Log.i("ScriptGroup", (new StringBuilder()).append(toString()).append(" receives ").append(aobj.length).append(" inputs, ").append("more than expected ").append(mInputs2.size()).toString());
        for(int i = 0; i < mInputs2.size(); i++)
        {
            Object obj = aobj[i];
            if((obj instanceof Future) || (obj instanceof Input))
            {
                Log.e("ScriptGroup", (new StringBuilder()).append(toString()).append(": input ").append(i).append(" is a future or unbound value").toString());
                return null;
            }
            ((Input)mInputs2.get(i)).set(obj);
        }

        mRS.nScriptGroup2Execute(getID(mRS));
        Object aobj1[] = new Object[mOutputs2.length];
        Future afuture[] = mOutputs2;
        int j = 0;
        int k = afuture.length;
        for(int l = 0; j < k; l++)
        {
            Object obj1 = afuture[j].getValue();
            aobj = ((Object []) (obj1));
            if(obj1 instanceof Input)
                aobj = ((Object []) (((Input)obj1).get()));
            aobj1[l] = ((Object) (aobj));
            j++;
        }

        return aobj1;
    }

    public void setInput(Script.KernelID kernelid, Allocation allocation)
    {
        for(int i = 0; i < mInputs.length; i++)
            if(mInputs[i].mKID == kernelid)
            {
                mInputs[i].mAllocation = allocation;
                mRS.nScriptGroupSetInput(getID(mRS), kernelid.getID(mRS), mRS.safeID(allocation));
                return;
            }

        throw new RSIllegalArgumentException("Script not found");
    }

    public void setOutput(Script.KernelID kernelid, Allocation allocation)
    {
        for(int i = 0; i < mOutputs.length; i++)
            if(mOutputs[i].mKID == kernelid)
            {
                mOutputs[i].mAllocation = allocation;
                mRS.nScriptGroupSetOutput(getID(mRS), kernelid.getID(mRS), mRS.safeID(allocation));
                return;
            }

        throw new RSIllegalArgumentException("Script not found");
    }

    private static final String TAG = "ScriptGroup";
    private List mClosures;
    IO mInputs[];
    private List mInputs2;
    private String mName;
    IO mOutputs[];
    private Future mOutputs2[];
}
