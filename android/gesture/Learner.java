// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.gesture;

import java.util.ArrayList;

// Referenced classes of package android.gesture:
//            Instance

abstract class Learner
{

    Learner()
    {
    }

    void addInstance(Instance instance)
    {
        mInstances.add(instance);
    }

    abstract ArrayList classify(int i, int j, float af[]);

    ArrayList getInstances()
    {
        return mInstances;
    }

    void removeInstance(long l)
    {
        ArrayList arraylist = mInstances;
        int i = arraylist.size();
        for(int j = 0; j < i; j++)
        {
            Instance instance = (Instance)arraylist.get(j);
            if(l == instance.id)
            {
                arraylist.remove(instance);
                return;
            }
        }

    }

    void removeInstances(String s)
    {
        ArrayList arraylist;
        ArrayList arraylist1;
        int i;
        int j;
        arraylist = new ArrayList();
        arraylist1 = mInstances;
        i = arraylist1.size();
        j = 0;
_L2:
        Instance instance;
        if(j >= i)
            break MISSING_BLOCK_LABEL_88;
        instance = (Instance)arraylist1.get(j);
        break MISSING_BLOCK_LABEL_40;
        if(instance.label == null && s == null || instance.label != null && instance.label.equals(s))
            arraylist.add(instance);
        j++;
        continue; /* Loop/switch isn't completed */
        arraylist1.removeAll(arraylist);
        return;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private final ArrayList mInstances = new ArrayList();
}
