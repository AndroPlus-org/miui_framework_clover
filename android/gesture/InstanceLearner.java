// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.gesture;

import java.util.*;

// Referenced classes of package android.gesture:
//            Learner, Instance, GestureUtils, Prediction

class InstanceLearner extends Learner
{

    InstanceLearner()
    {
    }

    ArrayList classify(int i, int j, float af[])
    {
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = getInstances();
        int k = arraylist1.size();
        TreeMap treemap = new TreeMap();
        int l = 0;
        while(l < k) 
        {
            Instance instance = (Instance)arraylist1.get(l);
            if(instance.vector.length == af.length)
            {
                double d;
                Double double1;
                if(i == 2)
                    d = GestureUtils.minimumCosineDistance(instance.vector, af, j);
                else
                    d = GestureUtils.squaredEuclideanDistance(instance.vector, af);
                if(d == 0.0D)
                    d = 1.7976931348623157E+308D;
                else
                    d = 1.0D / d;
                double1 = (Double)treemap.get(instance.label);
                if(double1 == null || d > double1.doubleValue())
                    treemap.put(instance.label, Double.valueOf(d));
            }
            l++;
        }
        String s;
        for(af = treemap.keySet().iterator(); af.hasNext(); arraylist.add(new Prediction(s, ((Double)treemap.get(s)).doubleValue())))
            s = (String)af.next();

        Collections.sort(arraylist, sComparator);
        return arraylist;
    }

    private static final Comparator sComparator = new Comparator() {

        public int compare(Prediction prediction, Prediction prediction1)
        {
            double d = prediction.score;
            double d1 = prediction1.score;
            if(d > d1)
                return -1;
            return d >= d1 ? 0 : 1;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((Prediction)obj, (Prediction)obj1);
        }

    }
;

}
