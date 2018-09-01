// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.*;
import com.android.internal.util.IndentingPrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

// Referenced classes of package android.os:
//            Parcel, BadParcelableException, Bundle, PersistableBundle

public class BaseBundle
{
    static final class NoImagePreloadHolder
    {

        public static final Parcel EMPTY_PARCEL = Parcel.obtain();


        NoImagePreloadHolder()
        {
        }
    }


    BaseBundle()
    {
        this((ClassLoader)null, 0);
    }

    BaseBundle(int i)
    {
        this((ClassLoader)null, i);
    }

    BaseBundle(BaseBundle basebundle)
    {
        mMap = null;
        mParcelledData = null;
        copyInternal(basebundle, false);
    }

    BaseBundle(Parcel parcel)
    {
        mMap = null;
        mParcelledData = null;
        readFromParcelInner(parcel);
    }

    BaseBundle(Parcel parcel, int i)
    {
        mMap = null;
        mParcelledData = null;
        readFromParcelInner(parcel, i);
    }

    BaseBundle(ClassLoader classloader)
    {
        this(classloader, 0);
    }

    BaseBundle(ClassLoader classloader, int i)
    {
        mMap = null;
        mParcelledData = null;
        Object obj;
        if(i > 0)
            obj = new ArrayMap(i);
        else
            obj = new ArrayMap();
        mMap = ((ArrayMap) (obj));
        obj = classloader;
        if(classloader == null)
            obj = getClass().getClassLoader();
        mClassLoader = ((ClassLoader) (obj));
    }

    BaseBundle(boolean flag)
    {
        mMap = null;
        mParcelledData = null;
    }

    public static void dumpStats(IndentingPrintWriter indentingprintwriter, BaseBundle basebundle)
    {
        indentingprintwriter.increaseIndent();
        if(basebundle == null)
        {
            indentingprintwriter.println("[null]");
            return;
        }
        basebundle = basebundle.getMap();
        for(int i = 0; i < basebundle.size(); i++)
            dumpStats(indentingprintwriter, (String)basebundle.keyAt(i), basebundle.valueAt(i));

        indentingprintwriter.decreaseIndent();
    }

    public static void dumpStats(IndentingPrintWriter indentingprintwriter, SparseArray sparsearray)
    {
        indentingprintwriter.increaseIndent();
        if(sparsearray == null)
        {
            indentingprintwriter.println("[null]");
            return;
        }
        for(int i = 0; i < sparsearray.size(); i++)
            dumpStats(indentingprintwriter, (new StringBuilder()).append("0x").append(Integer.toHexString(sparsearray.keyAt(i))).toString(), sparsearray.valueAt(i));

        indentingprintwriter.decreaseIndent();
    }

    public static void dumpStats(IndentingPrintWriter indentingprintwriter, String s, Object obj)
    {
        int i;
        Parcel parcel = Parcel.obtain();
        parcel.writeValue(obj);
        i = parcel.dataPosition();
        parcel.recycle();
        if(i <= 1024) goto _L2; else goto _L1
_L1:
        indentingprintwriter.println((new StringBuilder()).append(s).append(" [size=").append(i).append("]").toString());
        if(!(obj instanceof BaseBundle)) goto _L4; else goto _L3
_L3:
        dumpStats(indentingprintwriter, (BaseBundle)obj);
_L2:
        return;
_L4:
        if(obj instanceof SparseArray)
            dumpStats(indentingprintwriter, (SparseArray)obj);
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void initializeFromParcelLocked(Parcel parcel, boolean flag)
    {
        ArrayMap arraymap;
        if(isEmptyParcel(parcel))
        {
            if(mMap == null)
                mMap = new ArrayMap(1);
            else
                mMap.erase();
            mParcelledData = null;
            return;
        }
        int i = parcel.readInt();
        if(i < 0)
            return;
        arraymap = mMap;
        if(arraymap == null)
        {
            arraymap = new ArrayMap(i);
        } else
        {
            arraymap.erase();
            arraymap.ensureCapacity(i);
        }
        parcel.readArrayMapInternal(arraymap, i, mClassLoader);
        mMap = arraymap;
        if(flag)
            recycleParcel(parcel);
_L2:
        mParcelledData = null;
        return;
        Object obj;
        obj;
        if(!sShouldDefuse || !(((RuntimeException) (obj)).getCause() instanceof ClassNotFoundException))
            break MISSING_BLOCK_LABEL_169;
        Log.w("Bundle", "Failed to parse Bundle, but defusing quietly", ((Throwable) (obj)));
        arraymap.erase();
        mMap = arraymap;
        if(flag)
            recycleParcel(parcel);
        continue; /* Loop/switch isn't completed */
        throw obj;
        obj;
        mMap = arraymap;
        if(flag)
            recycleParcel(parcel);
        mParcelledData = null;
        throw obj;
        obj;
        if(!sShouldDefuse)
            break; /* Loop/switch isn't completed */
        Log.w("Bundle", "Failed to parse Bundle, but defusing quietly", ((Throwable) (obj)));
        arraymap.erase();
        mMap = arraymap;
        if(flag)
            recycleParcel(parcel);
        if(true) goto _L2; else goto _L1
_L1:
        throw obj;
    }

    private static boolean isEmptyParcel(Parcel parcel)
    {
        boolean flag;
        if(parcel == NoImagePreloadHolder.EMPTY_PARCEL)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void readFromParcelInner(Parcel parcel, int i)
    {
        if(i < 0)
            throw new RuntimeException((new StringBuilder()).append("Bad length in parcel: ").append(i).toString());
        if(i == 0)
        {
            mParcelledData = NoImagePreloadHolder.EMPTY_PARCEL;
            return;
        }
        int j = parcel.readInt();
        if(j != 0x4c444e42)
            throw new IllegalStateException((new StringBuilder()).append("Bad magic number for Bundle: 0x").append(Integer.toHexString(j)).toString());
        if(!parcel.hasReadWriteHelper())
            break MISSING_BLOCK_LABEL_107;
        this;
        JVM INSTR monitorenter ;
        initializeFromParcelLocked(parcel, false);
        this;
        JVM INSTR monitorexit ;
        return;
        parcel;
        throw parcel;
        int k = parcel.dataPosition();
        parcel.setDataPosition(MathUtils.addOrThrow(k, i));
        Parcel parcel1 = Parcel.obtain();
        parcel1.setDataPosition(0);
        parcel1.appendFrom(parcel, k, i);
        parcel1.adoptClassCookies(parcel);
        parcel1.setDataPosition(0);
        mParcelledData = parcel1;
        return;
    }

    private static void recycleParcel(Parcel parcel)
    {
        if(parcel != null && isEmptyParcel(parcel) ^ true)
            parcel.recycle();
    }

    public static void setShouldDefuse(boolean flag)
    {
        sShouldDefuse = flag;
    }

    public void clear()
    {
        unparcel();
        mMap.clear();
    }

    public boolean containsKey(String s)
    {
        unparcel();
        return mMap.containsKey(s);
    }

    void copyInternal(BaseBundle basebundle, boolean flag)
    {
        basebundle;
        JVM INSTR monitorenter ;
        if(basebundle.mParcelledData == null) goto _L2; else goto _L1
_L1:
        if(!basebundle.isEmptyParcel()) goto _L4; else goto _L3
_L3:
        mParcelledData = NoImagePreloadHolder.EMPTY_PARCEL;
_L7:
        if(basebundle.mMap == null)
            break MISSING_BLOCK_LABEL_184;
        if(flag) goto _L6; else goto _L5
_L5:
        ArrayMap arraymap = JVM INSTR new #62  <Class ArrayMap>;
        arraymap.ArrayMap(basebundle.mMap);
        mMap = arraymap;
_L9:
        mClassLoader = basebundle.mClassLoader;
        basebundle;
        JVM INSTR monitorexit ;
        return;
_L4:
        mParcelledData = Parcel.obtain();
        mParcelledData.appendFrom(basebundle.mParcelledData, 0, basebundle.mParcelledData.dataSize());
        mParcelledData.setDataPosition(0);
          goto _L7
        Exception exception;
        exception;
        throw exception;
_L2:
        mParcelledData = null;
          goto _L7
_L6:
        ArrayMap arraymap2;
        int i;
        arraymap2 = basebundle.mMap;
        i = arraymap2.size();
        ArrayMap arraymap1 = JVM INSTR new #62  <Class ArrayMap>;
        arraymap1.ArrayMap(i);
        mMap = arraymap1;
        int j = 0;
_L10:
        if(j >= i) goto _L9; else goto _L8
_L8:
        mMap.append((String)arraymap2.keyAt(j), deepCopyValue(arraymap2.valueAt(j)));
        j++;
          goto _L10
        mMap = null;
          goto _L9
    }

    Object deepCopyValue(Object obj)
    {
        if(obj == null)
            return null;
        if(obj instanceof Bundle)
            return ((Bundle)obj).deepCopy();
        if(obj instanceof PersistableBundle)
            return ((PersistableBundle)obj).deepCopy();
        if(obj instanceof ArrayList)
            return deepcopyArrayList((ArrayList)obj);
        if(obj.getClass().isArray())
        {
            if(obj instanceof int[])
                return ((int[])obj).clone();
            if(obj instanceof long[])
                return ((long[])obj).clone();
            if(obj instanceof float[])
                return ((float[])obj).clone();
            if(obj instanceof double[])
                return ((double[])obj).clone();
            if(obj instanceof Object[])
                return ((Object[])obj).clone();
            if(obj instanceof byte[])
                return ((byte[])obj).clone();
            if(obj instanceof short[])
                return ((short[])obj).clone();
            if(obj instanceof char[])
                return ((char[])obj).clone();
        }
        return obj;
    }

    ArrayList deepcopyArrayList(ArrayList arraylist)
    {
        int i = arraylist.size();
        ArrayList arraylist1 = new ArrayList(i);
        for(int j = 0; j < i; j++)
            arraylist1.add(deepCopyValue(arraylist.get(j)));

        return arraylist1;
    }

    public Object get(String s)
    {
        unparcel();
        return mMap.get(s);
    }

    public boolean getBoolean(String s)
    {
        unparcel();
        return getBoolean(s, false);
    }

    public boolean getBoolean(String s, boolean flag)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return flag;
        boolean flag1;
        try
        {
            flag1 = ((Boolean)obj).booleanValue();
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "Boolean", Boolean.valueOf(flag), classcastexception);
            return flag;
        }
        return flag1;
    }

    public boolean[] getBooleanArray(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        boolean aflag[];
        try
        {
            aflag = (boolean[])obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "byte[]", classcastexception);
            return null;
        }
        return aflag;
    }

    byte getByte(String s)
    {
        unparcel();
        return getByte(s, (byte)0).byteValue();
    }

    Byte getByte(String s, byte byte0)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return Byte.valueOf(byte0);
        Byte byte1;
        try
        {
            byte1 = (Byte)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "Byte", Byte.valueOf(byte0), classcastexception);
            return Byte.valueOf(byte0);
        }
        return byte1;
    }

    byte[] getByteArray(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        byte abyte0[];
        try
        {
            abyte0 = (byte[])obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "byte[]", classcastexception);
            return null;
        }
        return abyte0;
    }

    char getChar(String s)
    {
        unparcel();
        return getChar(s, '\0');
    }

    char getChar(String s, char c)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return c;
        char c1;
        try
        {
            c1 = ((Character)obj).charValue();
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "Character", Character.valueOf(c), classcastexception);
            return c;
        }
        return c1;
    }

    char[] getCharArray(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        char ac[];
        try
        {
            ac = (char[])obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "char[]", classcastexception);
            return null;
        }
        return ac;
    }

    CharSequence getCharSequence(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        CharSequence charsequence;
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "CharSequence", classcastexception);
            return null;
        }
        return charsequence;
    }

    CharSequence getCharSequence(String s, CharSequence charsequence)
    {
        s = getCharSequence(s);
        if(s != null)
            charsequence = s;
        return charsequence;
    }

    CharSequence[] getCharSequenceArray(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        CharSequence acharsequence[];
        try
        {
            acharsequence = (CharSequence[])obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "CharSequence[]", classcastexception);
            return null;
        }
        return acharsequence;
    }

    ArrayList getCharSequenceArrayList(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        ArrayList arraylist;
        try
        {
            arraylist = (ArrayList)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "ArrayList<CharSequence>", classcastexception);
            return null;
        }
        return arraylist;
    }

    ClassLoader getClassLoader()
    {
        return mClassLoader;
    }

    public double getDouble(String s)
    {
        unparcel();
        return getDouble(s, 0.0D);
    }

    public double getDouble(String s, double d)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return d;
        double d1;
        try
        {
            d1 = ((Double)obj).doubleValue();
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "Double", Double.valueOf(d), classcastexception);
            return d;
        }
        return d1;
    }

    public double[] getDoubleArray(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        double ad[];
        try
        {
            ad = (double[])obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "double[]", classcastexception);
            return null;
        }
        return ad;
    }

    float getFloat(String s)
    {
        unparcel();
        return getFloat(s, 0.0F);
    }

    float getFloat(String s, float f)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return f;
        float f1;
        try
        {
            f1 = ((Float)obj).floatValue();
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "Float", Float.valueOf(f), classcastexception);
            return f;
        }
        return f1;
    }

    float[] getFloatArray(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        float af[];
        try
        {
            af = (float[])obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "float[]", classcastexception);
            return null;
        }
        return af;
    }

    public int getInt(String s)
    {
        unparcel();
        return getInt(s, 0);
    }

    public int getInt(String s, int i)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return i;
        int j;
        try
        {
            j = ((Integer)obj).intValue();
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "Integer", Integer.valueOf(i), classcastexception);
            return i;
        }
        return j;
    }

    public int[] getIntArray(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        int ai[];
        try
        {
            ai = (int[])obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "int[]", classcastexception);
            return null;
        }
        return ai;
    }

    ArrayList getIntegerArrayList(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        ArrayList arraylist;
        try
        {
            arraylist = (ArrayList)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "ArrayList<Integer>", classcastexception);
            return null;
        }
        return arraylist;
    }

    public long getLong(String s)
    {
        unparcel();
        return getLong(s, 0L);
    }

    public long getLong(String s, long l)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return l;
        long l1;
        try
        {
            l1 = ((Long)obj).longValue();
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "Long", Long.valueOf(l), classcastexception);
            return l;
        }
        return l1;
    }

    public long[] getLongArray(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        long al[];
        try
        {
            al = (long[])obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "long[]", classcastexception);
            return null;
        }
        return al;
    }

    ArrayMap getMap()
    {
        unparcel();
        return mMap;
    }

    public String getPairValue()
    {
        unparcel();
        int i = mMap.size();
        if(i > 1)
            Log.w("Bundle", "getPairValue() used on Bundle with multiple pairs.");
        if(i == 0)
            return null;
        Object obj = mMap.valueAt(0);
        String s;
        try
        {
            s = (String)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning("getPairValue()", obj, "String", classcastexception);
            return null;
        }
        return s;
    }

    Serializable getSerializable(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        Serializable serializable;
        try
        {
            serializable = (Serializable)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "Serializable", classcastexception);
            return null;
        }
        return serializable;
    }

    short getShort(String s)
    {
        unparcel();
        return getShort(s, (short)0);
    }

    short getShort(String s, short word0)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return word0;
        short word1;
        try
        {
            word1 = ((Short)obj).shortValue();
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "Short", Short.valueOf(word0), classcastexception);
            return word0;
        }
        return word1;
    }

    short[] getShortArray(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        short aword0[];
        try
        {
            aword0 = (short[])obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "short[]", classcastexception);
            return null;
        }
        return aword0;
    }

    public String getString(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        String s1;
        try
        {
            s1 = (String)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "String", classcastexception);
            return null;
        }
        return s1;
    }

    public String getString(String s, String s1)
    {
        s = getString(s);
        if(s != null)
            s1 = s;
        return s1;
    }

    public String[] getStringArray(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        String as[];
        try
        {
            as = (String[])obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "String[]", classcastexception);
            return null;
        }
        return as;
    }

    ArrayList getStringArrayList(String s)
    {
        unparcel();
        Object obj = mMap.get(s);
        if(obj == null)
            return null;
        ArrayList arraylist;
        try
        {
            arraylist = (ArrayList)obj;
        }
        catch(ClassCastException classcastexception)
        {
            typeWarning(s, obj, "ArrayList<String>", classcastexception);
            return null;
        }
        return arraylist;
    }

    public boolean isEmpty()
    {
        unparcel();
        return mMap.isEmpty();
    }

    public boolean isEmptyParcel()
    {
        return isEmptyParcel(mParcelledData);
    }

    public boolean isParcelled()
    {
        boolean flag;
        if(mParcelledData != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public Set keySet()
    {
        unparcel();
        return mMap.keySet();
    }

    public boolean kindofEquals(BaseBundle basebundle)
    {
        boolean flag = false;
        if(basebundle == null)
            return false;
        if(isParcelled() != basebundle.isParcelled())
            return false;
        if(isParcelled())
        {
            if(mParcelledData.compareData(basebundle.mParcelledData) == 0)
                flag = true;
            return flag;
        } else
        {
            return mMap.equals(basebundle.mMap);
        }
    }

    public boolean maybeIsEmpty()
    {
        if(isParcelled())
            return isEmptyParcel();
        else
            return isEmpty();
    }

    public void putAll(PersistableBundle persistablebundle)
    {
        unparcel();
        persistablebundle.unparcel();
        mMap.putAll(persistablebundle.mMap);
    }

    void putAll(ArrayMap arraymap)
    {
        unparcel();
        mMap.putAll(arraymap);
    }

    public void putBoolean(String s, boolean flag)
    {
        unparcel();
        mMap.put(s, Boolean.valueOf(flag));
    }

    public void putBooleanArray(String s, boolean aflag[])
    {
        unparcel();
        mMap.put(s, aflag);
    }

    void putByte(String s, byte byte0)
    {
        unparcel();
        mMap.put(s, Byte.valueOf(byte0));
    }

    void putByteArray(String s, byte abyte0[])
    {
        unparcel();
        mMap.put(s, abyte0);
    }

    void putChar(String s, char c)
    {
        unparcel();
        mMap.put(s, Character.valueOf(c));
    }

    void putCharArray(String s, char ac[])
    {
        unparcel();
        mMap.put(s, ac);
    }

    void putCharSequence(String s, CharSequence charsequence)
    {
        unparcel();
        mMap.put(s, charsequence);
    }

    void putCharSequenceArray(String s, CharSequence acharsequence[])
    {
        unparcel();
        mMap.put(s, acharsequence);
    }

    void putCharSequenceArrayList(String s, ArrayList arraylist)
    {
        unparcel();
        mMap.put(s, arraylist);
    }

    public void putDouble(String s, double d)
    {
        unparcel();
        mMap.put(s, Double.valueOf(d));
    }

    public void putDoubleArray(String s, double ad[])
    {
        unparcel();
        mMap.put(s, ad);
    }

    void putFloat(String s, float f)
    {
        unparcel();
        mMap.put(s, Float.valueOf(f));
    }

    void putFloatArray(String s, float af[])
    {
        unparcel();
        mMap.put(s, af);
    }

    public void putInt(String s, int i)
    {
        unparcel();
        mMap.put(s, Integer.valueOf(i));
    }

    public void putIntArray(String s, int ai[])
    {
        unparcel();
        mMap.put(s, ai);
    }

    void putIntegerArrayList(String s, ArrayList arraylist)
    {
        unparcel();
        mMap.put(s, arraylist);
    }

    public void putLong(String s, long l)
    {
        unparcel();
        mMap.put(s, Long.valueOf(l));
    }

    public void putLongArray(String s, long al[])
    {
        unparcel();
        mMap.put(s, al);
    }

    void putSerializable(String s, Serializable serializable)
    {
        unparcel();
        mMap.put(s, serializable);
    }

    void putShort(String s, short word0)
    {
        unparcel();
        mMap.put(s, Short.valueOf(word0));
    }

    void putShortArray(String s, short aword0[])
    {
        unparcel();
        mMap.put(s, aword0);
    }

    public void putString(String s, String s1)
    {
        unparcel();
        mMap.put(s, s1);
    }

    public void putStringArray(String s, String as[])
    {
        unparcel();
        mMap.put(s, as);
    }

    void putStringArrayList(String s, ArrayList arraylist)
    {
        unparcel();
        mMap.put(s, arraylist);
    }

    void readFromParcelInner(Parcel parcel)
    {
        readFromParcelInner(parcel, parcel.readInt());
    }

    public void remove(String s)
    {
        unparcel();
        mMap.remove(s);
    }

    void setClassLoader(ClassLoader classloader)
    {
        mClassLoader = classloader;
    }

    public int size()
    {
        unparcel();
        return mMap.size();
    }

    void typeWarning(String s, Object obj, String s1, ClassCastException classcastexception)
    {
        typeWarning(s, obj, s1, "<null>", classcastexception);
    }

    void typeWarning(String s, Object obj, String s1, Object obj1, ClassCastException classcastexception)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Key ");
        stringbuilder.append(s);
        stringbuilder.append(" expected ");
        stringbuilder.append(s1);
        stringbuilder.append(" but value was a ");
        stringbuilder.append(obj.getClass().getName());
        stringbuilder.append(".  The default value ");
        stringbuilder.append(obj1);
        stringbuilder.append(" was returned.");
        Log.w("Bundle", stringbuilder.toString());
        Log.w("Bundle", "Attempt to cast generated internal exception:", classcastexception);
    }

    void unparcel()
    {
        this;
        JVM INSTR monitorenter ;
        Parcel parcel = mParcelledData;
        if(parcel == null)
            break MISSING_BLOCK_LABEL_17;
        initializeFromParcelLocked(parcel, true);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void writeToParcelInner(Parcel parcel, int i)
    {
        if(parcel.hasReadWriteHelper())
            unparcel();
        this;
        JVM INSTR monitorenter ;
        if(mParcelledData == null)
            break MISSING_BLOCK_LABEL_75;
        if(mParcelledData != NoImagePreloadHolder.EMPTY_PARCEL)
            break MISSING_BLOCK_LABEL_38;
        parcel.writeInt(0);
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        i = mParcelledData.dataSize();
        parcel.writeInt(i);
        parcel.writeInt(0x4c444e42);
        parcel.appendFrom(mParcelledData, 0, i);
          goto _L1
        parcel;
        throw parcel;
        ArrayMap arraymap = mMap;
        this;
        JVM INSTR monitorexit ;
        if(arraymap == null || arraymap.size() <= 0)
        {
            parcel.writeInt(0);
            return;
        } else
        {
            int j = parcel.dataPosition();
            parcel.writeInt(-1);
            parcel.writeInt(0x4c444e42);
            i = parcel.dataPosition();
            parcel.writeArrayMapInternal(arraymap);
            int k = parcel.dataPosition();
            parcel.setDataPosition(j);
            parcel.writeInt(k - i);
            parcel.setDataPosition(k);
            return;
        }
    }

    static final int BUNDLE_MAGIC = 0x4c444e42;
    static final boolean DEBUG = false;
    static final int FLAG_DEFUSABLE = 1;
    private static final boolean LOG_DEFUSABLE = false;
    private static final String TAG = "Bundle";
    private static volatile boolean sShouldDefuse = false;
    private ClassLoader mClassLoader;
    public int mFlags;
    ArrayMap mMap;
    Parcel mParcelledData;

}
