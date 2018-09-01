// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.gesture;

import android.graphics.RectF;
import android.util.Log;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

// Referenced classes of package android.gesture:
//            GesturePoint, OrientedBoundingBox, Gesture, GestureStroke

public final class GestureUtils
{

    private GestureUtils()
    {
    }

    static void closeStream(Closeable closeable)
    {
        if(closeable == null)
            break MISSING_BLOCK_LABEL_10;
        closeable.close();
_L1:
        return;
        closeable;
        Log.e("Gestures", "Could not close stream", closeable);
          goto _L1
    }

    static float[] computeCentroid(float af[])
    {
        float f = 0.0F;
        float f1 = 0.0F;
        int i = af.length;
        for(int j = 0; j < i; j++)
        {
            f += af[j];
            j++;
            f1 += af[j];
        }

        return (new float[] {
            (2.0F * f) / (float)i, (2.0F * f1) / (float)i
        });
    }

    private static float[][] computeCoVariance(float af[])
    {
        float af1[][] = new float[2][2];
        af1[0][0] = 0.0F;
        af1[0][1] = 0.0F;
        af1[1][0] = 0.0F;
        af1[1][1] = 0.0F;
        int i = af.length;
        for(int j = 0; j < i; j++)
        {
            float f = af[j];
            j++;
            float f1 = af[j];
            float af2[] = af1[0];
            af2[0] = af2[0] + f * f;
            af2 = af1[0];
            af2[1] = af2[1] + f * f1;
            af1[1][0] = af1[0][1];
            af2 = af1[1];
            af2[1] = af2[1] + f1 * f1;
        }

        af = af1[0];
        af[0] = af[0] / (float)(i / 2);
        af = af1[0];
        af[1] = af[1] / (float)(i / 2);
        af = af1[1];
        af[0] = af[0] / (float)(i / 2);
        af = af1[1];
        af[1] = af[1] / (float)(i / 2);
        return af1;
    }

    private static float[] computeOrientation(float af[][])
    {
        float af1[] = new float[2];
        if(af[0][1] == 0.0F || af[1][0] == 0.0F)
        {
            af1[0] = 1.0F;
            af1[1] = 0.0F;
        }
        float f = -af[0][0];
        float f1 = af[1][1];
        float f2 = af[0][0];
        float f3 = af[1][1];
        float f4 = af[0][1];
        float f5 = af[1][0];
        f1 = (f - f1) / 2.0F;
        f2 = (float)Math.sqrt(Math.pow(f1, 2D) - (double)(f2 * f3 - f4 * f5));
        f3 = -f1 + f2;
        f1 = -f1 - f2;
        if(f3 == f1)
        {
            af1[0] = 0.0F;
            af1[1] = 0.0F;
        } else
        {
            if(f3 <= f1)
                f3 = f1;
            af1[0] = 1.0F;
            af1[1] = (f3 - af[0][0]) / af[0][1];
        }
        return af1;
    }

    public static OrientedBoundingBox computeOrientedBoundingBox(ArrayList arraylist)
    {
        int i = arraylist.size();
        float af[] = new float[i * 2];
        for(int j = 0; j < i; j++)
        {
            GesturePoint gesturepoint = (GesturePoint)arraylist.get(j);
            int k = j * 2;
            af[k] = gesturepoint.x;
            af[k + 1] = gesturepoint.y;
        }

        return computeOrientedBoundingBox(af, computeCentroid(af));
    }

    public static OrientedBoundingBox computeOrientedBoundingBox(float af[])
    {
        int i = af.length;
        float af1[] = new float[i];
        for(int j = 0; j < i; j++)
            af1[j] = af[j];

        return computeOrientedBoundingBox(af1, computeCentroid(af1));
    }

    private static OrientedBoundingBox computeOrientedBoundingBox(float af[], float af1[])
    {
        translate(af, -af1[0], -af1[1]);
        float af2[] = computeOrientation(computeCoVariance(af));
        float f;
        float f1;
        float f2;
        float f3;
        float f4;
        int i;
        if(af2[0] == 0.0F && af2[1] == 0.0F)
        {
            f = -1.570796F;
        } else
        {
            f = (float)Math.atan2(af2[1], af2[0]);
            rotate(af, -f);
        }
        f1 = 3.402823E+038F;
        f2 = 3.402823E+038F;
        f3 = 1.401298E-045F;
        f4 = 1.401298E-045F;
        i = af.length;
        for(int j = 0; j < i;)
        {
            float f5 = f1;
            if(af[j] < f1)
                f5 = af[j];
            f1 = f3;
            if(af[j] > f3)
                f1 = af[j];
            j++;
            float f6 = f2;
            if(af[j] < f2)
                f6 = af[j];
            f2 = f4;
            if(af[j] > f4)
                f2 = af[j];
            j++;
            f3 = f1;
            f4 = f2;
            f1 = f5;
            f2 = f6;
        }

        return new OrientedBoundingBox((float)((double)(180F * f) / 3.1415926535897931D), af1[0], af1[1], f3 - f1, f4 - f2);
    }

    static float computeStraightness(float af[])
    {
        float f = computeTotalLength(af);
        float f1 = af[2];
        float f2 = af[0];
        float f3 = af[3];
        float f4 = af[1];
        return (float)Math.hypot(f1 - f2, f3 - f4) / f;
    }

    static float computeStraightness(float af[], float f)
    {
        float f1 = af[2];
        float f2 = af[0];
        float f3 = af[3];
        float f4 = af[1];
        return (float)Math.hypot(f1 - f2, f3 - f4) / f;
    }

    static float computeTotalLength(float af[])
    {
        float f = 0.0F;
        int i = af.length;
        for(int j = 0; j < i - 4; j += 2)
        {
            float f1 = af[j + 2];
            float f2 = af[j];
            float f3 = af[j + 3];
            float f4 = af[j + 1];
            f = (float)((double)f + Math.hypot(f1 - f2, f3 - f4));
        }

        return f;
    }

    static float cosineDistance(float af[], float af1[])
    {
        float f = 0.0F;
        int i = af.length;
        for(int j = 0; j < i; j++)
            f += af[j] * af1[j];

        return (float)Math.acos(f);
    }

    static float minimumCosineDistance(float af[], float af1[], int i)
    {
        int j = af.length;
        float f = 0.0F;
        float f1 = 0.0F;
        for(int k = 0; k < j; k += 2)
        {
            f += af[k] * af1[k] + af[k + 1] * af1[k + 1];
            f1 += af[k] * af1[k + 1] - af[k + 1] * af1[k];
        }

        if(f != 0.0F)
        {
            float f2 = f1 / f;
            double d = Math.atan(f2);
            if(i > 2 && Math.abs(d) >= 3.1415926535897931D / (double)i)
            {
                return (float)Math.acos(f);
            } else
            {
                d = Math.cos(d);
                double d1 = f2;
                return (float)Math.acos((double)f * d + (double)f1 * (d * d1));
            }
        } else
        {
            return 1.570796F;
        }
    }

    private static void plot(float f, float f1, float af[], int i)
    {
        float f2;
        int j;
        int k;
        int l;
        int i1;
        f2 = f;
        if(f < 0.0F)
            f2 = 0.0F;
        f = f1;
        if(f1 < 0.0F)
            f = 0.0F;
        j = (int)Math.floor(f2);
        k = (int)Math.ceil(f2);
        l = (int)Math.floor(f);
        i1 = (int)Math.ceil(f);
        if(f2 != (float)j || f != (float)l) goto _L2; else goto _L1
_L1:
        i = i1 * i + k;
        if(af[i] < 1.0F)
            af[i] = 1.0F;
_L4:
        return;
_L2:
        double d = Math.pow((float)j - f2, 2D);
        double d1 = Math.pow((float)l - f, 2D);
        double d2 = Math.pow((float)k - f2, 2D);
        double d3 = Math.pow((float)i1 - f, 2D);
        float f3 = (float)Math.sqrt(d + d1);
        float f4 = (float)Math.sqrt(d2 + d1);
        f2 = (float)Math.sqrt(d + d3);
        f1 = (float)Math.sqrt(d2 + d3);
        f = f3 + f4 + f2 + f1;
        f3 /= f;
        int j1 = l * i + j;
        if(f3 > af[j1])
            af[j1] = f3;
        f4 /= f;
        j1 = l * i + k;
        if(f4 > af[j1])
            af[j1] = f4;
        f2 /= f;
        j = i1 * i + j;
        if(f2 > af[j])
            af[j] = f2;
        f = f1 / f;
        i = i1 * i + k;
        if(f > af[i])
            af[i] = f;
        if(true) goto _L4; else goto _L3
_L3:
    }

    static float[] rotate(float af[], float f)
    {
        float f1 = (float)Math.cos(f);
        float f2 = (float)Math.sin(f);
        int i = af.length;
        for(int j = 0; j < i; j += 2)
        {
            float f3 = af[j];
            float f4 = af[j + 1];
            f = af[j];
            float f5 = af[j + 1];
            af[j] = f3 * f1 - f4 * f2;
            af[j + 1] = f * f2 + f5 * f1;
        }

        return af;
    }

    static float[] scale(float af[], float f, float f1)
    {
        int i = af.length;
        for(int j = 0; j < i; j += 2)
        {
            af[j] = af[j] * f;
            int k = j + 1;
            af[k] = af[k] * f1;
        }

        return af;
    }

    public static float[] spatialSampling(Gesture gesture, int i)
    {
        return spatialSampling(gesture, i, false);
    }

    public static float[] spatialSampling(Gesture gesture, int i, boolean flag)
    {
        float f;
        float af[];
        Object obj;
        float f1;
        float f2;
        float f3;
        float f5;
        f = i - 1;
        af = new float[i * i];
        Arrays.fill(af, 0.0F);
        obj = gesture.getBoundingBox();
        f1 = ((RectF) (obj)).width();
        f2 = ((RectF) (obj)).height();
        f3 = f / f1;
        f5 = f / f2;
        if(!flag) goto _L2; else goto _L1
_L1:
        int k;
        float f7;
        float f8;
        float f9;
        float f10;
        int j;
        float af1[];
        int i1;
        if(f3 < f5)
            f1 = f3;
        else
            f1 = f5;
        f5 = f1;
        f2 = f1;
        f1 = f5;
_L6:
        f7 = -((RectF) (obj)).centerX();
        f8 = -((RectF) (obj)).centerY();
        f9 = f / 2.0F;
        f10 = f / 2.0F;
        obj = gesture.getStrokes();
        j = ((ArrayList) (obj)).size();
        k = 0;
_L4:
        if(k >= j)
            break; /* Loop/switch isn't completed */
        af1 = ((GestureStroke)((ArrayList) (obj)).get(k)).points;
        int l = af1.length;
        gesture = new float[l];
        float f11;
        for(i1 = 0; i1 < l; i1 += 2)
        {
            gesture[i1] = (af1[i1] + f7) * f1 + f9;
            gesture[i1 + 1] = (af1[i1 + 1] + f8) * f2 + f10;
        }

        float f16 = -1F;
        float f17 = -1F;
        for(int j1 = 0; j1 < l;)
        {
            float f4;
            float f6;
            float f12;
            if(gesture[j1] < 0.0F)
                f4 = 0.0F;
            else
                f4 = gesture[j1];
            if(gesture[j1 + 1] < 0.0F)
                f12 = 0.0F;
            else
                f12 = gesture[j1 + 1];
            f6 = f4;
            if(f4 > f)
                f6 = f;
            f4 = f12;
            if(f12 > f)
                f4 = f;
            plot(f6, f4, af, i);
            if(f16 != -1F)
            {
                if(f16 > f6)
                {
                    f12 = (float)Math.ceil(f6);
                    float f18 = (f17 - f4) / (f16 - f6);
                    for(; f12 < f16; f12++)
                        plot(f12, (f12 - f6) * f18 + f4, af, i);

                } else
                if(f16 < f6)
                {
                    float f13 = (float)Math.ceil(f16);
                    float f19 = (f17 - f4) / (f16 - f6);
                    for(; f13 < f6; f13++)
                        plot(f13, (f13 - f6) * f19 + f4, af, i);

                }
                if(f17 > f4)
                {
                    float f14 = (float)Math.ceil(f4);
                    f16 = (f16 - f6) / (f17 - f4);
                    for(; f14 < f17; f14++)
                        plot((f14 - f4) * f16 + f6, f14, af, i);

                } else
                if(f17 < f4)
                {
                    float f15 = (float)Math.ceil(f17);
                    f17 = (f16 - f6) / (f17 - f4);
                    for(; f15 < f4; f15++)
                        plot((f15 - f4) * f17 + f6, f15, af, i);

                }
            }
            j1 += 2;
            f16 = f6;
            f17 = f4;
        }

        k++;
        continue; /* Loop/switch isn't completed */
_L2:
        f2 = f1 / f2;
        f1 = f2;
        if(f2 > 1.0F)
            f1 = 1.0F / f2;
        if(f1 < 0.26F)
        {
            if(f3 < f5)
                f1 = f3;
            else
                f1 = f5;
            f2 = f1;
            f5 = f1;
            f1 = f2;
            f2 = f5;
        } else
        if(f3 > f5)
        {
            f11 = f5 * NONUNIFORM_SCALE;
            f1 = f3;
            f2 = f5;
            if(f11 < f3)
            {
                f1 = f11;
                f2 = f5;
            }
        } else
        {
            f11 = f3 * NONUNIFORM_SCALE;
            f1 = f3;
            f2 = f5;
            if(f11 < f5)
            {
                f2 = f11;
                f1 = f3;
            }
        }
        continue; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L3
_L3:
        return af;
        if(true) goto _L6; else goto _L5
_L5:
    }

    static float squaredEuclideanDistance(float af[], float af1[])
    {
        float f = 0.0F;
        int i = af.length;
        for(int j = 0; j < i; j++)
        {
            float f1 = af[j] - af1[j];
            f += f1 * f1;
        }

        return f / (float)i;
    }

    public static float[] temporalSampling(GestureStroke gesturestroke, int i)
    {
        float f = gesturestroke.length / (float)(i - 1);
        int j = i * 2;
        float af[] = new float[j];
        float f1 = 0.0F;
        gesturestroke = gesturestroke.points;
        float f2 = gesturestroke[0];
        float f3 = gesturestroke[1];
        float f4 = 1.401298E-045F;
        float f5 = 1.401298E-045F;
        af[0] = f2;
        af[1] = f3;
        i = 1 + 1;
        int k = 0;
        int l = gesturestroke.length / 2;
label0:
        do
        {
            float f6;
            int i1;
label1:
            {
label2:
                {
                    if(k < l)
                    {
                        f6 = f4;
                        i1 = k;
                        if(f4 != 1.401298E-045F)
                            break label1;
                        i1 = k + 1;
                        if(i1 < l)
                            break label2;
                    }
                    for(; i < j; i += 2)
                    {
                        af[i] = f2;
                        af[i + 1] = f3;
                    }

                    break label0;
                }
                f6 = gesturestroke[i1 * 2];
                f5 = gesturestroke[i1 * 2 + 1];
            }
            float f7 = f6 - f2;
            f4 = f5 - f3;
            float f8 = (float)Math.hypot(f7, f4);
            if(f1 + f8 >= f)
            {
                f1 = (f - f1) / f8;
                f2 += f1 * f7;
                f3 += f1 * f4;
                af[i] = f2;
                i++;
                af[i] = f3;
                i++;
                f1 = 0.0F;
                f4 = f6;
                k = i1;
            } else
            {
                f2 = f6;
                f3 = f5;
                f4 = 1.401298E-045F;
                f5 = 1.401298E-045F;
                f1 += f8;
                k = i1;
            }
        } while(true);
        return af;
    }

    static float[] translate(float af[], float f, float f1)
    {
        int i = af.length;
        for(int j = 0; j < i; j += 2)
        {
            af[j] = af[j] + f;
            int k = j + 1;
            af[k] = af[k] + f1;
        }

        return af;
    }

    private static final float NONUNIFORM_SCALE = (float)Math.sqrt(2D);
    private static final float SCALING_THRESHOLD = 0.26F;

}
