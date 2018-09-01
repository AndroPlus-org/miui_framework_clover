// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import java.util.Arrays;
import java.util.Iterator;

// Referenced classes of package android.filterfw.core:
//            KeyValueMap, MutableFrameFormat

public class FrameFormat
{

    protected FrameFormat()
    {
        mBaseType = 0;
        mBytesPerSample = 1;
        mSize = -1;
        mTarget = 0;
    }

    public FrameFormat(int i, int j)
    {
        mBaseType = 0;
        mBytesPerSample = 1;
        mSize = -1;
        mTarget = 0;
        mBaseType = i;
        mTarget = j;
        initDefaults();
    }

    public static String baseTypeToString(int i)
    {
        switch(i)
        {
        default:
            return "unknown";

        case 0: // '\0'
            return "unspecified";

        case 1: // '\001'
            return "bit";

        case 2: // '\002'
            return "byte";

        case 3: // '\003'
            return "int";

        case 4: // '\004'
            return "int";

        case 5: // '\005'
            return "float";

        case 6: // '\006'
            return "double";

        case 7: // '\007'
            return "pointer";

        case 8: // '\b'
            return "object";
        }
    }

    public static int bytesPerSampleOf(int i)
    {
        switch(i)
        {
        default:
            return 1;

        case 1: // '\001'
        case 2: // '\002'
            return 1;

        case 3: // '\003'
            return 2;

        case 4: // '\004'
        case 5: // '\005'
        case 7: // '\007'
            return 4;

        case 6: // '\006'
            return 8;
        }
    }

    public static String dimensionsToString(int ai[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(ai != null)
        {
            int i = ai.length;
            int j = 0;
            while(j < i) 
            {
                if(ai[j] == 0)
                    stringbuffer.append("[]");
                else
                    stringbuffer.append((new StringBuilder()).append("[").append(String.valueOf(ai[j])).append("]").toString());
                j++;
            }
        }
        return stringbuffer.toString();
    }

    private void initDefaults()
    {
        mBytesPerSample = bytesPerSampleOf(mBaseType);
    }

    public static String metaDataToString(KeyValueMap keyvaluemap)
    {
        if(keyvaluemap == null)
            return "";
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("{ ");
        java.util.Map.Entry entry;
        for(keyvaluemap = keyvaluemap.entrySet().iterator(); keyvaluemap.hasNext(); stringbuffer.append((new StringBuilder()).append((String)entry.getKey()).append(": ").append(entry.getValue()).append(" ").toString()))
            entry = (java.util.Map.Entry)keyvaluemap.next();

        stringbuffer.append("}");
        return stringbuffer.toString();
    }

    public static int readTargetString(String s)
    {
        if(s.equalsIgnoreCase("CPU") || s.equalsIgnoreCase("NATIVE"))
            return 2;
        if(s.equalsIgnoreCase("GPU"))
            return 3;
        if(s.equalsIgnoreCase("SIMPLE"))
            return 1;
        if(s.equalsIgnoreCase("VERTEXBUFFER"))
            return 4;
        if(s.equalsIgnoreCase("UNSPECIFIED"))
            return 0;
        else
            throw new RuntimeException((new StringBuilder()).append("Unknown target type '").append(s).append("'!").toString());
    }

    public static String targetToString(int i)
    {
        switch(i)
        {
        default:
            return "unknown";

        case 0: // '\0'
            return "unspecified";

        case 1: // '\001'
            return "simple";

        case 2: // '\002'
            return "native";

        case 3: // '\003'
            return "gpu";

        case 4: // '\004'
            return "vbo";

        case 5: // '\005'
            return "renderscript";
        }
    }

    public static FrameFormat unspecified()
    {
        return new FrameFormat(0, 0);
    }

    int calcSize(int ai[])
    {
        int i = 0;
        if(ai != null && ai.length > 0)
        {
            int j = getBytesPerSample();
            for(int k = ai.length; i < k; i++)
                j *= ai[i];

            return j;
        } else
        {
            return 0;
        }
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(this == obj)
            return true;
        if(!(obj instanceof FrameFormat))
            return false;
        obj = (FrameFormat)obj;
        boolean flag1 = flag;
        if(((FrameFormat) (obj)).mBaseType == mBaseType)
        {
            flag1 = flag;
            if(((FrameFormat) (obj)).mTarget == mTarget)
            {
                flag1 = flag;
                if(((FrameFormat) (obj)).mBytesPerSample == mBytesPerSample)
                {
                    flag1 = flag;
                    if(Arrays.equals(((FrameFormat) (obj)).mDimensions, mDimensions))
                        flag1 = ((FrameFormat) (obj)).mMetaData.equals(mMetaData);
                }
            }
        }
        return flag1;
    }

    public int getBaseType()
    {
        return mBaseType;
    }

    public int getBytesPerSample()
    {
        return mBytesPerSample;
    }

    public int getDepth()
    {
        int i;
        if(mDimensions != null && mDimensions.length >= 3)
            i = mDimensions[2];
        else
            i = -1;
        return i;
    }

    public int getDimension(int i)
    {
        return mDimensions[i];
    }

    public int getDimensionCount()
    {
        int i;
        if(mDimensions == null)
            i = 0;
        else
            i = mDimensions.length;
        return i;
    }

    public int[] getDimensions()
    {
        return mDimensions;
    }

    public int getHeight()
    {
        int i;
        if(mDimensions != null && mDimensions.length >= 2)
            i = mDimensions[1];
        else
            i = -1;
        return i;
    }

    public int getLength()
    {
        int i;
        if(mDimensions != null && mDimensions.length >= 1)
            i = mDimensions[0];
        else
            i = -1;
        return i;
    }

    public Object getMetaValue(String s)
    {
        Object obj = null;
        if(mMetaData != null)
            obj = mMetaData.get(s);
        return obj;
    }

    public int getNumberOfDimensions()
    {
        int i;
        if(mDimensions != null)
            i = mDimensions.length;
        else
            i = 0;
        return i;
    }

    public Class getObjectClass()
    {
        return mObjectClass;
    }

    public int getSize()
    {
        if(mSize == -1)
            mSize = calcSize(mDimensions);
        return mSize;
    }

    public int getTarget()
    {
        return mTarget;
    }

    public int getValuesPerSample()
    {
        return mBytesPerSample / bytesPerSampleOf(mBaseType);
    }

    public int getWidth()
    {
        return getLength();
    }

    public boolean hasMetaKey(String s)
    {
        boolean flag;
        if(mMetaData != null)
            flag = mMetaData.containsKey(s);
        else
            flag = false;
        return flag;
    }

    public boolean hasMetaKey(String s, Class class1)
    {
        if(mMetaData != null && mMetaData.containsKey(s))
        {
            if(!class1.isAssignableFrom(mMetaData.get(s).getClass()))
                throw new RuntimeException((new StringBuilder()).append("FrameFormat meta-key '").append(s).append("' is of type ").append(mMetaData.get(s).getClass()).append(" but expected to be of type ").append(class1).append("!").toString());
            else
                return true;
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return mBaseType ^ 0x1073 ^ mBytesPerSample ^ getSize();
    }

    public boolean isBinaryDataType()
    {
        boolean flag = true;
        if(mBaseType < 1 || mBaseType > 6)
            flag = false;
        return flag;
    }

    public boolean isCompatibleWith(FrameFormat frameformat)
    {
label0:
        {
            if(frameformat.getBaseType() != 0 && getBaseType() != frameformat.getBaseType())
                return false;
            if(frameformat.getTarget() != 0 && getTarget() != frameformat.getTarget())
                return false;
            if(frameformat.getBytesPerSample() != 1 && getBytesPerSample() != frameformat.getBytesPerSample())
                return false;
            if(frameformat.getDimensionCount() > 0 && getDimensionCount() != frameformat.getDimensionCount())
                return false;
            for(int i = 0; i < frameformat.getDimensionCount(); i++)
            {
                int j = frameformat.getDimension(i);
                if(j != 0 && getDimension(i) != j)
                    return false;
            }

            if(frameformat.getObjectClass() != null && (getObjectClass() == null || frameformat.getObjectClass().isAssignableFrom(getObjectClass()) ^ true))
                return false;
            if(frameformat.mMetaData == null)
                break label0;
            Iterator iterator = frameformat.mMetaData.keySet().iterator();
            String s;
            do
            {
                if(!iterator.hasNext())
                    break label0;
                s = (String)iterator.next();
            } while(mMetaData != null && !(mMetaData.containsKey(s) ^ true) && !(mMetaData.get(s).equals(frameformat.mMetaData.get(s)) ^ true));
            return false;
        }
        return true;
    }

    boolean isReplaceableBy(FrameFormat frameformat)
    {
        boolean flag;
        if(mTarget == frameformat.mTarget && getSize() == frameformat.getSize())
            flag = Arrays.equals(frameformat.mDimensions, mDimensions);
        else
            flag = false;
        return flag;
    }

    public boolean mayBeCompatibleWith(FrameFormat frameformat)
    {
label0:
        {
            if(frameformat.getBaseType() != 0 && getBaseType() != 0 && getBaseType() != frameformat.getBaseType())
                return false;
            if(frameformat.getTarget() != 0 && getTarget() != 0 && getTarget() != frameformat.getTarget())
                return false;
            if(frameformat.getBytesPerSample() != 1 && getBytesPerSample() != 1 && getBytesPerSample() != frameformat.getBytesPerSample())
                return false;
            if(frameformat.getDimensionCount() > 0 && getDimensionCount() > 0 && getDimensionCount() != frameformat.getDimensionCount())
                return false;
            for(int i = 0; i < frameformat.getDimensionCount(); i++)
            {
                int j = frameformat.getDimension(i);
                if(j != 0 && getDimension(i) != 0 && getDimension(i) != j)
                    return false;
            }

            if(frameformat.getObjectClass() != null && getObjectClass() != null && !frameformat.getObjectClass().isAssignableFrom(getObjectClass()))
                return false;
            if(frameformat.mMetaData == null || mMetaData == null)
                break label0;
            Iterator iterator = frameformat.mMetaData.keySet().iterator();
            String s;
            do
            {
                if(!iterator.hasNext())
                    break label0;
                s = (String)iterator.next();
            } while(!mMetaData.containsKey(s) || !(mMetaData.get(s).equals(frameformat.mMetaData.get(s)) ^ true));
            return false;
        }
        return true;
    }

    public MutableFrameFormat mutableCopy()
    {
        KeyValueMap keyvaluemap = null;
        MutableFrameFormat mutableframeformat = new MutableFrameFormat();
        mutableframeformat.setBaseType(getBaseType());
        mutableframeformat.setTarget(getTarget());
        mutableframeformat.setBytesPerSample(getBytesPerSample());
        mutableframeformat.setDimensions(getDimensions());
        mutableframeformat.setObjectClass(getObjectClass());
        if(mMetaData != null)
            keyvaluemap = (KeyValueMap)mMetaData.clone();
        mutableframeformat.mMetaData = keyvaluemap;
        return mutableframeformat;
    }

    public String toString()
    {
        int i = getValuesPerSample();
        String s;
        String s1;
        String s2;
        if(i == 1)
            s = "";
        else
            s = String.valueOf(i);
        if(mTarget == 0)
            s1 = "";
        else
            s1 = (new StringBuilder()).append(targetToString(mTarget)).append(" ").toString();
        if(mObjectClass == null)
            s2 = "";
        else
            s2 = (new StringBuilder()).append(" class(").append(mObjectClass.getSimpleName()).append(") ").toString();
        return (new StringBuilder()).append(s1).append(baseTypeToString(mBaseType)).append(s).append(dimensionsToString(mDimensions)).append(s2).append(metaDataToString(mMetaData)).toString();
    }

    public static final int BYTES_PER_SAMPLE_UNSPECIFIED = 1;
    protected static final int SIZE_UNKNOWN = -1;
    public static final int SIZE_UNSPECIFIED = 0;
    public static final int TARGET_GPU = 3;
    public static final int TARGET_NATIVE = 2;
    public static final int TARGET_RS = 5;
    public static final int TARGET_SIMPLE = 1;
    public static final int TARGET_UNSPECIFIED = 0;
    public static final int TARGET_VERTEXBUFFER = 4;
    public static final int TYPE_BIT = 1;
    public static final int TYPE_BYTE = 2;
    public static final int TYPE_DOUBLE = 6;
    public static final int TYPE_FLOAT = 5;
    public static final int TYPE_INT16 = 3;
    public static final int TYPE_INT32 = 4;
    public static final int TYPE_OBJECT = 8;
    public static final int TYPE_POINTER = 7;
    public static final int TYPE_UNSPECIFIED = 0;
    protected int mBaseType;
    protected int mBytesPerSample;
    protected int mDimensions[];
    protected KeyValueMap mMetaData;
    protected Class mObjectClass;
    protected int mSize;
    protected int mTarget;
}
