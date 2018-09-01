// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.gesture;

import java.util.ArrayList;

// Referenced classes of package android.gesture:
//            Gesture, GestureUtils, GestureStroke

class Instance
{

    private Instance(long l, float af[], String s)
    {
        id = l;
        vector = af;
        label = s;
    }

    static Instance createInstance(int i, int j, Gesture gesture, String s)
    {
        if(i == 2)
        {
            float af[] = temporalSampler(j, gesture);
            gesture = new Instance(gesture.getID(), af, s);
            gesture.normalize();
        } else
        {
            float af1[] = spatialSampler(gesture);
            gesture = new Instance(gesture.getID(), af1, s);
        }
        return gesture;
    }

    private void normalize()
    {
        float af[] = vector;
        float f = 0.0F;
        int i = af.length;
        for(int j = 0; j < i; j++)
            f += af[j] * af[j];

        f = (float)Math.sqrt(f);
        for(int k = 0; k < i; k++)
            af[k] = af[k] / f;

    }

    private static float[] spatialSampler(Gesture gesture)
    {
        return GestureUtils.spatialSampling(gesture, 16, false);
    }

    private static float[] temporalSampler(int i, Gesture gesture)
    {
        gesture = GestureUtils.temporalSampling((GestureStroke)gesture.getStrokes().get(0), 16);
        float af[] = GestureUtils.computeCentroid(gesture);
        float f = (float)Math.atan2(gesture[1] - af[1], gesture[0] - af[0]);
        float f1 = -f;
        float f2 = f1;
        if(i != 1)
        {
            int j = ORIENTATIONS.length;
            i = 0;
            do
            {
                f2 = f1;
                if(i >= j)
                    break;
                float f3 = ORIENTATIONS[i] - f;
                f2 = f1;
                if(Math.abs(f3) < Math.abs(f1))
                    f2 = f3;
                i++;
                f1 = f2;
            } while(true);
        }
        GestureUtils.translate(gesture, -af[0], -af[1]);
        GestureUtils.rotate(gesture, f2);
        return gesture;
    }

    private static final float ORIENTATIONS[] = {
        0.0F, 0.7853982F, 1.570796F, 2.356194F, 3.141593F, 0.0F, -0.7853982F, -1.570796F, -2.356194F, -3.141593F
    };
    private static final int PATCH_SAMPLE_SIZE = 16;
    private static final int SEQUENCE_SAMPLE_SIZE = 16;
    final long id;
    final String label;
    final float vector[];

}
