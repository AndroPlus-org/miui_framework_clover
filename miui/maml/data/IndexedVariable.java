// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;


// Referenced classes of package miui.maml.data:
//            Variables

public class IndexedVariable
{

    public IndexedVariable(String s, Variables variables, boolean flag)
    {
        mIsNumber = flag;
        int i;
        if(mIsNumber)
            i = variables.registerDoubleVariable(s);
        else
            i = variables.registerVariable(s);
        mIndex = i;
        mVars = variables;
    }

    public final Object get()
    {
        return mVars.get(mIndex);
    }

    public final Object getArr(int i)
    {
        return mVars.getArr(mIndex, i);
    }

    public final double getArrDouble(int i)
    {
        return mVars.getArrDouble(mIndex, i);
    }

    public final String getArrString(int i)
    {
        return mVars.getArrString(mIndex, i);
    }

    public final double getDouble()
    {
        return mVars.getDouble(mIndex);
    }

    public final int getIndex()
    {
        return mIndex;
    }

    public final String getString()
    {
        return mVars.getString(mIndex);
    }

    public final Variables getVariables()
    {
        return mVars;
    }

    public final int getVersion()
    {
        return mVars.getVer(mIndex, mIsNumber);
    }

    public final boolean isNull()
    {
        boolean flag;
        if(mIsNumber)
            flag = mVars.existsDouble(mIndex) ^ true;
        else
        if(mVars.get(mIndex) == null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public final boolean isNull(int i)
    {
        boolean flag;
        if(mIsNumber)
            flag = mVars.existsArrItem(mIndex, i) ^ true;
        else
        if(mVars.getArr(mIndex, i) == null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public final void set(double d)
    {
        mVars.put(mIndex, d);
    }

    public final boolean set(Object obj)
    {
        if(mIsNumber)
        {
            return mVars.putDouble(mIndex, obj);
        } else
        {
            mVars.put(mIndex, obj);
            return true;
        }
    }

    public final boolean setArr(int i, double d)
    {
        return mVars.putArr(mIndex, i, d);
    }

    public final boolean setArr(int i, Object obj)
    {
        boolean flag;
        if(mIsNumber)
            flag = mVars.putArrDouble(mIndex, i, obj);
        else
            flag = mVars.putArr(mIndex, i, obj);
        return flag;
    }

    protected int mIndex;
    private boolean mIsNumber;
    protected Variables mVars;
}
