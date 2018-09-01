// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.textclassifier;

import com.android.internal.util.Preconditions;
import java.util.*;

final class EntityConfidence
{

    EntityConfidence()
    {
        mEntityConfidence = new HashMap();
        mEntityComparator = new _.Lambda.YdZbAd6a5x_pMw8WtGLtYRkzJSM((byte)0, this);
    }

    EntityConfidence(EntityConfidence entityconfidence)
    {
        mEntityConfidence = new HashMap();
        mEntityComparator = new _.Lambda.YdZbAd6a5x_pMw8WtGLtYRkzJSM((byte)1, this);
        Preconditions.checkNotNull(entityconfidence);
        mEntityConfidence.putAll(entityconfidence.mEntityConfidence);
    }

    public float getConfidenceScore(Object obj)
    {
        if(mEntityConfidence.containsKey(obj))
            return ((Float)mEntityConfidence.get(obj)).floatValue();
        else
            return 0.0F;
    }

    public List getEntities()
    {
        ArrayList arraylist = new ArrayList(mEntityConfidence.size());
        arraylist.addAll(mEntityConfidence.keySet());
        arraylist.sort(mEntityComparator);
        return Collections.unmodifiableList(arraylist);
    }

    int lambda$_2D_android_view_textclassifier_EntityConfidence_1225(Object obj, Object obj1)
    {
        float f = ((Float)mEntityConfidence.get(obj)).floatValue();
        float f1 = ((Float)mEntityConfidence.get(obj1)).floatValue();
        if(f > f1)
            return -1;
        return f >= f1 ? 0 : 1;
    }

    public void setEntityType(Object obj, float f)
    {
        Preconditions.checkNotNull(obj);
        if(f > 0.0F)
            mEntityConfidence.put(obj, Float.valueOf(Math.min(1.0F, f)));
        else
            mEntityConfidence.remove(obj);
    }

    public String toString()
    {
        return mEntityConfidence.toString();
    }

    private final Comparator mEntityComparator;
    private final Map mEntityConfidence;
}
