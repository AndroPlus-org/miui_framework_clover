// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.health;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

public class HealthKeys
{
    public static interface Constant
        extends Annotation
    {

        public abstract int type();
    }

    public static class Constants
    {

        public String getDataType()
        {
            return mDataType;
        }

        public int getIndex(int i, int j)
        {
            int k = Arrays.binarySearch(mKeys[i], j);
            if(k >= 0)
                return k;
            else
                throw new RuntimeException((new StringBuilder()).append("Unknown Constant ").append(j).append(" (of type ").append(i).append(" )").toString());
        }

        public int[] getKeys(int i)
        {
            return mKeys[i];
        }

        public int getSize(int i)
        {
            return mKeys[i].length;
        }

        private final String mDataType;
        private final int mKeys[][] = new int[5][];

        public Constants(Class class1)
        {
            mDataType = class1.getSimpleName();
            Field afield[] = class1.getDeclaredFields();
            int i = afield.length;
            SortedIntArray asortedintarray[] = new SortedIntArray[mKeys.length];
            for(int j = 0; j < asortedintarray.length; j++)
                asortedintarray[j] = new SortedIntArray(i);

            int k = 0;
            while(k < i) 
            {
                class1 = afield[k];
                Constant constant = (Constant)class1.getAnnotation(android/os/health/HealthKeys$Constant);
                if(constant != null)
                {
                    int i1 = constant.type();
                    if(i1 >= asortedintarray.length)
                        throw new RuntimeException((new StringBuilder()).append("Unknown Constant type ").append(i1).append(" on ").append(class1).toString());
                    try
                    {
                        asortedintarray[i1].addValue(class1.getInt(null));
                    }
                    catch(IllegalAccessException illegalaccessexception)
                    {
                        throw new RuntimeException((new StringBuilder()).append("Can't read constant value type=").append(i1).append(" field=").append(class1).toString(), illegalaccessexception);
                    }
                }
                k++;
            }
            for(int l = 0; l < asortedintarray.length; l++)
                mKeys[l] = asortedintarray[l].getArray();

        }
    }

    private static class SortedIntArray
    {

        void addValue(int i)
        {
            int ai[] = mArray;
            int j = mCount;
            mCount = j + 1;
            ai[j] = i;
        }

        int[] getArray()
        {
            if(mCount == mArray.length)
            {
                Arrays.sort(mArray);
                return mArray;
            } else
            {
                int ai[] = new int[mCount];
                System.arraycopy(mArray, 0, ai, 0, mCount);
                Arrays.sort(ai);
                return ai;
            }
        }

        int mArray[];
        int mCount;

        SortedIntArray(int i)
        {
            mArray = new int[i];
        }
    }


    public HealthKeys()
    {
    }

    public static final int BASE_PACKAGE = 40000;
    public static final int BASE_PID = 20000;
    public static final int BASE_PROCESS = 30000;
    public static final int BASE_SERVICE = 50000;
    public static final int BASE_UID = 10000;
    public static final int TYPE_COUNT = 5;
    public static final int TYPE_MEASUREMENT = 1;
    public static final int TYPE_MEASUREMENTS = 4;
    public static final int TYPE_STATS = 2;
    public static final int TYPE_TIMER = 0;
    public static final int TYPE_TIMERS = 3;
    public static final int UNKNOWN_KEY = 0;
}
