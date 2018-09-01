// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.util.Log;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import miui.maml.util.Utils;

public class Variables
{
    private static abstract class BaseVarBucket
    {

        public boolean exists(String s)
        {
            return mIndices.containsKey(s);
        }

        protected abstract void onAddItem(int i);

        public int registerVariable(String s)
        {
            this;
            JVM INSTR monitorenter ;
            Integer integer = (Integer)mIndices.get(s);
            Integer integer1;
            integer1 = integer;
            if(integer != null)
                break MISSING_BLOCK_LABEL_46;
            integer1 = Integer.valueOf(mNextIndex);
            mIndices.put(s, integer1);
            onAddItem(mNextIndex);
            int i;
            if(integer1.intValue() == mNextIndex)
                mNextIndex = mNextIndex + 1;
            if(Variables._2D_get0())
            {
                StringBuilder stringbuilder = JVM INSTR new #62  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("Variables", stringbuilder.append("registerVariable: ").append(s).append("  index:").append(integer1).toString());
            }
            i = integer1.intValue();
            this;
            JVM INSTR monitorexit ;
            return i;
            s;
            throw s;
        }

        private HashMap mIndices;
        private int mNextIndex;

        private BaseVarBucket()
        {
            mIndices = new HashMap();
            mNextIndex = 0;
        }

        BaseVarBucket(BaseVarBucket basevarbucket)
        {
            this();
        }
    }

    private static class DoubleBucket extends BaseVarBucket
    {

        public final boolean exists(int i)
        {
            boolean flag = false;
            this;
            JVM INSTR monitorenter ;
            if(i >= 0) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorexit ;
            return flag;
_L2:
            Object obj;
            try
            {
                obj = mArray.get(i);
            }
            catch(IndexOutOfBoundsException indexoutofboundsexception)
            {
                return false;
            }
            if(obj != null)
                flag = true;
            if(true) goto _L1; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        public double get(int i)
        {
            double d = 0.0D;
            this;
            JVM INSTR monitorenter ;
            DoubleInfo doubleinfo;
            try
            {
                doubleinfo = (DoubleInfo)mArray.get(i);
            }
            catch(IndexOutOfBoundsException indexoutofboundsexception)
            {
                return 0.0D;
            }
            if(doubleinfo != null) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorexit ;
            return d;
_L2:
            d = doubleinfo.mValue;
            if(true) goto _L1; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        public int getVer(int i)
        {
            byte byte0 = -1;
            this;
            JVM INSTR monitorenter ;
            DoubleInfo doubleinfo;
            try
            {
                doubleinfo = (DoubleInfo)mArray.get(i);
            }
            catch(IndexOutOfBoundsException indexoutofboundsexception)
            {
                return -1;
            }
            if(doubleinfo != null) goto _L2; else goto _L1
_L1:
            i = byte0;
_L4:
            this;
            JVM INSTR monitorexit ;
            return i;
_L2:
            i = doubleinfo.mVersion;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        protected void onAddItem(int i)
        {
            for(; mArray.size() <= i; mArray.add(null));
        }

        public final void put(int i, double d)
        {
            this;
            JVM INSTR monitorenter ;
            if(i < 0)
                return;
            DoubleInfo doubleinfo = (DoubleInfo)mArray.get(i);
            if(doubleinfo != null)
                break MISSING_BLOCK_LABEL_53;
            try
            {
                doubleinfo = JVM INSTR new #35  <Class Variables$DoubleInfo>;
                doubleinfo.DoubleInfo(d, 0);
                mArray.set(i, doubleinfo);
            }
            catch(IndexOutOfBoundsException indexoutofboundsexception) { }
            this;
            JVM INSTR monitorexit ;
            return;
            doubleinfo.setValue(d);
            break MISSING_BLOCK_LABEL_50;
            Exception exception;
            exception;
            throw exception;
        }

        public void reset()
        {
            int i = mArray.size();
            for(int j = 0; j < i; j++)
            {
                DoubleInfo doubleinfo = (DoubleInfo)mArray.get(j);
                if(doubleinfo != null)
                    doubleinfo.setValue(0.0D);
            }

        }

        private ArrayList mArray;

        private DoubleBucket()
        {
            super(null);
            mArray = new ArrayList();
        }

        DoubleBucket(DoubleBucket doublebucket)
        {
            this();
        }
    }

    private static class DoubleInfo
    {

        public void setValue(double d)
        {
            mValue = d;
            mVersion = mVersion + 1;
        }

        double mValue;
        int mVersion;

        public DoubleInfo(double d, int i)
        {
            mValue = d;
            mVersion = i;
        }
    }

    private static class ValueInfo
    {

        public void reset()
        {
            if(mValue instanceof double[])
            {
                double ad[] = (double[])mValue;
                for(int i = 0; i < ad.length; i++)
                    ad[i] = 0.0D;

            } else
            if(mValue instanceof float[])
            {
                float af[] = (float[])mValue;
                for(int j = 0; j < af.length; j++)
                    af[j] = 0.0F;

            } else
            if(mValue instanceof int[])
            {
                int ai[] = (int[])mValue;
                for(int k = 0; k < ai.length; k++)
                    ai[k] = 0;

            } else
            if(mValue instanceof Object[])
            {
                Object aobj[] = (Object[])mValue;
                for(int l = 0; l < aobj.length; l++)
                    aobj[l] = null;

            } else
            {
                setValue(null);
            }
        }

        public void setValue(Object obj)
        {
            mValue = obj;
            mVersion = mVersion + 1;
        }

        Object mValue;
        int mVersion;

        public ValueInfo(Object obj, int i)
        {
            mValue = obj;
            mVersion = i;
        }
    }

    private static class VarBucket extends BaseVarBucket
    {

        public Object get(int i)
        {
            Object obj = null;
            this;
            JVM INSTR monitorenter ;
            ValueInfo valueinfo;
            try
            {
                valueinfo = (ValueInfo)mArray.get(i);
            }
            catch(IndexOutOfBoundsException indexoutofboundsexception)
            {
                return null;
            }
            if(valueinfo != null) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorexit ;
            return obj;
_L2:
            obj = valueinfo.mValue;
            if(true) goto _L1; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        public int getVer(int i)
        {
            byte byte0 = -1;
            this;
            JVM INSTR monitorenter ;
            ValueInfo valueinfo;
            try
            {
                valueinfo = (ValueInfo)mArray.get(i);
            }
            catch(IndexOutOfBoundsException indexoutofboundsexception)
            {
                return -1;
            }
            if(valueinfo != null) goto _L2; else goto _L1
_L1:
            i = byte0;
_L4:
            this;
            JVM INSTR monitorexit ;
            return i;
_L2:
            i = valueinfo.mVersion;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        protected void onAddItem(int i)
        {
            for(; mArray.size() <= i; mArray.add(null));
        }

        public final void put(int i, Object obj)
        {
            this;
            JVM INSTR monitorenter ;
            if(i < 0)
                return;
            ValueInfo valueinfo = (ValueInfo)mArray.get(i);
            if(valueinfo != null)
                break MISSING_BLOCK_LABEL_48;
            try
            {
                valueinfo = JVM INSTR new #33  <Class Variables$ValueInfo>;
                valueinfo.ValueInfo(obj, 0);
                mArray.set(i, valueinfo);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj) { }
            this;
            JVM INSTR monitorexit ;
            return;
            valueinfo.setValue(obj);
            break MISSING_BLOCK_LABEL_45;
            obj;
            throw obj;
        }

        public void reset()
        {
            int i = mArray.size();
            for(int j = 0; j < i; j++)
            {
                ValueInfo valueinfo = (ValueInfo)mArray.get(j);
                if(valueinfo != null)
                    valueinfo.reset();
            }

        }

        private ArrayList mArray;

        private VarBucket()
        {
            super(null);
            mArray = new ArrayList();
        }

        VarBucket(VarBucket varbucket)
        {
            this();
        }
    }


    static boolean _2D_get0()
    {
        return DBG;
    }

    public Variables()
    {
        mDoubleBucket = new DoubleBucket(null);
        mObjectBucket = new VarBucket(null);
    }

    private static void dbglog(String s)
    {
        if(DBG)
            Log.d("Variables", s);
    }

    private Object getArrInner(int i, int j)
    {
        Object obj = ((Object) ((Object[])get(i)));
        if(obj == null)
        {
            try
            {
                obj = JVM INSTR new #73  <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                dbglog(((StringBuilder) (obj)).append("getArrInner: designated object is not an array. index:").append(i).toString());
            }
            catch(ClassCastException classcastexception)
            {
                dbglog((new StringBuilder()).append("getArrInner: designated object type is not correct. index:").append(i).toString());
            }
            catch(IndexOutOfBoundsException indexoutofboundsexception)
            {
                dbglog((new StringBuilder()).append("getArrInner: designated index is invalid. index:").append(i).append(" arrIndex:").append(j).toString());
            }
            return null;
        } else
        {
            obj = obj[j];
            return obj;
        }
    }

    public boolean createArray(String s, int i, Class class1)
    {
        if(class1 == null || i <= 0)
            return false;
        int j = registerVariable(s);
        if(get(j) == null)
        {
            try
            {
                s = ((String) (Array.newInstance(class1, i)));
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                return false;
            }
            put(j, s);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean existsArrItem(int i, int j)
    {
        boolean flag = false;
        Object obj = get(i);
        if(obj == null)
            return false;
        boolean flag1 = flag;
        if(j >= 0)
        {
            try
            {
                i = Array.getLength(obj);
            }
            catch(RuntimeException runtimeexception)
            {
                return false;
            }
            flag1 = flag;
            if(j < i)
                flag1 = true;
        }
        return flag1;
    }

    public boolean existsDouble(int i)
    {
        return mDoubleBucket.exists(i);
    }

    public boolean existsDouble(String s)
    {
        return mDoubleBucket.exists(s);
    }

    public boolean existsObj(String s)
    {
        return mObjectBucket.exists(s);
    }

    public Object get(int i)
    {
        return mObjectBucket.get(i);
    }

    public Object get(String s)
    {
        return get(registerVariable(s));
    }

    public Object getArr(int i, int j)
    {
        return getArrInner(i, j);
    }

    public double getArrDouble(int i, int j)
    {
        Object obj = get(i);
        if(obj == null)
        {
            double d;
            try
            {
                obj = JVM INSTR new #73  <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                dbglog(((StringBuilder) (obj)).append("getArrDouble: designated array does not exist. index:").append(i).toString());
            }
            catch(Exception exception)
            {
                dbglog((new StringBuilder()).append("getArrDouble: designated index is invalid. index:").append(i).append(" arrIndex:").append(j).toString());
            }
            return 0.0D;
        }
        if(obj instanceof boolean[])
        {
            if(((boolean[])obj)[i])
                i = 1;
            else
                i = 0;
            return (double)i;
        }
        d = Array.getDouble(obj, j);
        return d;
    }

    public String getArrString(int i, int j)
    {
        return (String)getArrInner(i, j);
    }

    public double getDouble(int i)
    {
        return mDoubleBucket.get(i);
    }

    public double getDouble(String s)
    {
        return getDouble(registerDoubleVariable(s));
    }

    public String getString(int i)
    {
        String s;
        try
        {
            s = (String)get(i);
        }
        catch(ClassCastException classcastexception)
        {
            return null;
        }
        return s;
    }

    public String getString(String s)
    {
        return getString(registerVariable(s));
    }

    public int getVer(int i, boolean flag)
    {
        if(flag)
            i = mDoubleBucket.getVer(i);
        else
            i = mObjectBucket.getVer(i);
        return i;
    }

    public final void put(int i, double d)
    {
        mDoubleBucket.put(i, d);
    }

    public final void put(int i, Object obj)
    {
        mObjectBucket.put(i, obj);
    }

    public final void put(String s, double d)
    {
        put(registerDoubleVariable(s), d);
    }

    public void put(String s, Object obj)
    {
        put(registerVariable(s), obj);
    }

    public boolean putArr(int i, int j, double d)
    {
        Object obj;
        StringBuilder stringbuilder;
        try
        {
            obj = get(i);
        }
        catch(Exception exception)
        {
            dbglog((new StringBuilder()).append("putArr: failed. index:").append(i).append(" arrIndex:").append(j).append("\n").append(exception.toString()).toString());
            return false;
        }
        if(obj != null)
            break MISSING_BLOCK_LABEL_41;
        stringbuilder = JVM INSTR new #73  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        dbglog(stringbuilder.append("putArr: designated array does not exist. index:").append(i).toString());
        return false;
        if(!(obj instanceof double[])) goto _L2; else goto _L1
_L1:
        ((double[])obj)[j] = d;
_L3:
        put(i, obj);
        return true;
_L2:
label0:
        {
            if(!(obj instanceof byte[]))
                break label0;
            ((byte[])obj)[j] = (byte)(int)(long)d;
        }
          goto _L3
label1:
        {
            if(!(obj instanceof char[]))
                break label1;
            ((char[])obj)[j] = (char)(int)(long)d;
        }
          goto _L3
label2:
        {
            if(!(obj instanceof float[]))
                break label2;
            ((float[])obj)[j] = (float)d;
        }
          goto _L3
label3:
        {
            if(!(obj instanceof int[]))
                break label3;
            ((int[])obj)[j] = (int)(long)d;
        }
          goto _L3
label4:
        {
            if(!(obj instanceof long[]))
                break label4;
            ((long[])obj)[j] = (long)d;
        }
          goto _L3
        if(!(obj instanceof short[])) goto _L5; else goto _L4
_L4:
        ((short[])obj)[j] = (short)(int)(long)d;
          goto _L3
_L5:
        if(!(obj instanceof boolean[])) goto _L3; else goto _L6
_L6:
        boolean aflag[] = (boolean[])obj;
        boolean flag;
        if(d > 0.0D)
            flag = true;
        else
            flag = false;
        aflag[j] = flag;
          goto _L3
    }

    public boolean putArr(int i, int j, Object obj)
    {
        Object aobj[] = (Object[])get(i);
        if(aobj == null)
        {
            try
            {
                obj = JVM INSTR new #73  <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                dbglog(((StringBuilder) (obj)).append("putArr: designated array does not exist. index:").append(i).toString());
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                dbglog((new StringBuilder()).append("putArr: designated object is not an object array. index:").append(i).toString());
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                dbglog((new StringBuilder()).append("putArr: designated array index is invalid. index:").append(i).append(" arrIndex:").append(j).toString());
            }
            return false;
        }
        aobj[j] = obj;
        put(i, ((Object) (aobj)));
        return true;
    }

    public boolean putArrDouble(int i, int j, Object obj)
    {
        if(obj instanceof Number)
            return putArr(i, j, ((Number)obj).doubleValue());
        if(!(obj instanceof String))
            break MISSING_BLOCK_LABEL_47;
        boolean flag = putArr(i, j, Utils.parseDouble((String)obj));
        return flag;
        obj;
        return false;
    }

    public final boolean putDouble(int i, Object obj)
    {
        int j = 0;
        if(obj instanceof Number)
        {
            put(i, ((Number)obj).doubleValue());
            return true;
        }
        if(obj instanceof Boolean)
        {
            if(((Boolean)obj).booleanValue())
                j = 1;
            put(i, j);
            return true;
        }
        if(!(obj instanceof String))
            break MISSING_BLOCK_LABEL_73;
        put(i, Double.parseDouble((String)obj));
        return true;
        obj;
        return false;
    }

    public final void putNum(String s, double d)
    {
        put(s, d);
    }

    public int registerDoubleVariable(String s)
    {
        return mDoubleBucket.registerVariable(s);
    }

    public int registerVariable(String s)
    {
        return mObjectBucket.registerVariable(s);
    }

    public void reset()
    {
        mDoubleBucket.reset();
        mObjectBucket.reset();
    }

    private static boolean DBG = false;
    private static final String LOG_TAG = "Variables";
    private DoubleBucket mDoubleBucket;
    private VarBucket mObjectBucket;

    static 
    {
        DBG = false;
    }
}
