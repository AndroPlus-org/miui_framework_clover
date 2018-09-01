// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.gesture;

import java.io.*;
import java.util.*;

// Referenced classes of package android.gesture:
//            InstanceLearner, Gesture, Instance, Learner, 
//            GestureUtils

public class GestureStore
{

    public GestureStore()
    {
        mSequenceType = 2;
        mOrientationStyle = 2;
        mChanged = false;
        mClassifier = new InstanceLearner();
    }

    private void readFormatV1(DataInputStream datainputstream)
        throws IOException
    {
        Learner learner = mClassifier;
        HashMap hashmap = mNamedGestures;
        hashmap.clear();
        int i = datainputstream.readInt();
        for(int j = 0; j < i; j++)
        {
            String s = datainputstream.readUTF();
            int k = datainputstream.readInt();
            ArrayList arraylist = new ArrayList(k);
            for(int l = 0; l < k; l++)
            {
                Gesture gesture = Gesture.deserialize(datainputstream);
                arraylist.add(gesture);
                learner.addInstance(Instance.createInstance(mSequenceType, mOrientationStyle, gesture, s));
            }

            hashmap.put(s, arraylist);
        }

    }

    public void addGesture(String s, Gesture gesture)
    {
        if(s == null || s.length() == 0)
            return;
        ArrayList arraylist = (ArrayList)mNamedGestures.get(s);
        ArrayList arraylist1 = arraylist;
        if(arraylist == null)
        {
            arraylist1 = new ArrayList();
            mNamedGestures.put(s, arraylist1);
        }
        arraylist1.add(gesture);
        mClassifier.addInstance(Instance.createInstance(mSequenceType, mOrientationStyle, gesture, s));
        mChanged = true;
    }

    public Set getGestureEntries()
    {
        return mNamedGestures.keySet();
    }

    public ArrayList getGestures(String s)
    {
        s = (ArrayList)mNamedGestures.get(s);
        if(s != null)
            return new ArrayList(s);
        else
            return null;
    }

    Learner getLearner()
    {
        return mClassifier;
    }

    public int getOrientationStyle()
    {
        return mOrientationStyle;
    }

    public int getSequenceType()
    {
        return mSequenceType;
    }

    public boolean hasChanged()
    {
        return mChanged;
    }

    public void load(InputStream inputstream)
        throws IOException
    {
        load(inputstream, false);
    }

    public void load(InputStream inputstream, boolean flag)
        throws IOException
    {
        Object obj = null;
        DataInputStream datainputstream;
        datainputstream = JVM INSTR new #58  <Class DataInputStream>;
        if(!(inputstream instanceof BufferedInputStream))
            break MISSING_BLOCK_LABEL_58;
_L1:
        datainputstream.DataInputStream(inputstream);
        short word0 = datainputstream.readShort();
        switch(word0)
        {
        default:
            if(flag)
                GestureUtils.closeStream(datainputstream);
            return;

        case 1: // '\001'
            break;
        }
        break MISSING_BLOCK_LABEL_72;
        inputstream = new BufferedInputStream(inputstream, 32768);
          goto _L1
        readFormatV1(datainputstream);
        inputstream;
        obj = datainputstream;
_L3:
        if(flag)
            GestureUtils.closeStream(((java.io.Closeable) (obj)));
        throw inputstream;
        inputstream;
        if(true) goto _L3; else goto _L2
_L2:
    }

    public ArrayList recognize(Gesture gesture)
    {
        gesture = Instance.createInstance(mSequenceType, mOrientationStyle, gesture, null);
        return mClassifier.classify(mSequenceType, mOrientationStyle, ((Instance) (gesture)).vector);
    }

    public void removeEntry(String s)
    {
        mNamedGestures.remove(s);
        mClassifier.removeInstances(s);
        mChanged = true;
    }

    public void removeGesture(String s, Gesture gesture)
    {
        ArrayList arraylist = (ArrayList)mNamedGestures.get(s);
        if(arraylist == null)
            return;
        arraylist.remove(gesture);
        if(arraylist.isEmpty())
            mNamedGestures.remove(s);
        mClassifier.removeInstance(gesture.getID());
        mChanged = true;
    }

    public void save(OutputStream outputstream)
        throws IOException
    {
        save(outputstream, false);
    }

    public void save(OutputStream outputstream, boolean flag)
        throws IOException
    {
        String s = null;
        Object obj;
        Object obj1;
        obj = mNamedGestures;
        obj1 = JVM INSTR new #194 <Class DataOutputStream>;
        if(!(outputstream instanceof BufferedOutputStream)) goto _L2; else goto _L1
_L1:
        ((DataOutputStream) (obj1)).DataOutputStream(outputstream);
        ((DataOutputStream) (obj1)).writeShort(1);
        ((DataOutputStream) (obj1)).writeInt(((HashMap) (obj)).size());
        outputstream = ((HashMap) (obj)).entrySet().iterator();
_L6:
        int i;
        if(!outputstream.hasNext())
            break; /* Loop/switch isn't completed */
        obj = (java.util.Map.Entry)outputstream.next();
        s = (String)((java.util.Map.Entry) (obj)).getKey();
        obj = (ArrayList)((java.util.Map.Entry) (obj)).getValue();
        i = ((ArrayList) (obj)).size();
        ((DataOutputStream) (obj1)).writeUTF(s);
        ((DataOutputStream) (obj1)).writeInt(i);
        int j = 0;
_L4:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        ((Gesture)((ArrayList) (obj)).get(j)).serialize(((DataOutputStream) (obj1)));
        j++;
        if(true) goto _L4; else goto _L3
_L3:
        if(true) goto _L6; else goto _L5
_L2:
        outputstream = new BufferedOutputStream(outputstream, 32768);
          goto _L1
_L5:
        ((DataOutputStream) (obj1)).flush();
        mChanged = false;
        if(flag)
            GestureUtils.closeStream(((java.io.Closeable) (obj1)));
        return;
        outputstream;
        obj1 = s;
_L8:
        if(flag)
            GestureUtils.closeStream(((java.io.Closeable) (obj1)));
        throw outputstream;
        outputstream;
        if(true) goto _L8; else goto _L7
_L7:
    }

    public void setOrientationStyle(int i)
    {
        mOrientationStyle = i;
    }

    public void setSequenceType(int i)
    {
        mSequenceType = i;
    }

    private static final short FILE_FORMAT_VERSION = 1;
    public static final int ORIENTATION_INVARIANT = 1;
    public static final int ORIENTATION_SENSITIVE = 2;
    static final int ORIENTATION_SENSITIVE_4 = 4;
    static final int ORIENTATION_SENSITIVE_8 = 8;
    private static final boolean PROFILE_LOADING_SAVING = false;
    public static final int SEQUENCE_INVARIANT = 1;
    public static final int SEQUENCE_SENSITIVE = 2;
    private boolean mChanged;
    private Learner mClassifier;
    private final HashMap mNamedGestures = new HashMap();
    private int mOrientationStyle;
    private int mSequenceType;
}
