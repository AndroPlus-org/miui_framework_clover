// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import java.util.GregorianCalendar;

public class GeomagneticField
{
    private static class LegendreTable
    {

        static final boolean _2D_assertionsDisabled = android/hardware/GeomagneticField$LegendreTable.desiredAssertionStatus() ^ true;
        public final float mP[][];
        public final float mPDeriv[][];


        public LegendreTable(int i, float f)
        {
            float f1 = (float)Math.cos(f);
            float f2 = (float)Math.sin(f);
            mP = new float[i + 1][];
            mPDeriv = new float[i + 1][];
            mP[0] = (new float[] {
                1.0F
            });
            mPDeriv[0] = (new float[] {
                0.0F
            });
            for(int j = 1; j <= i; j++)
            {
                mP[j] = new float[j + 1];
                mPDeriv[j] = new float[j + 1];
                int k = 0;
                while(k <= j) 
                {
                    if(j == k)
                    {
                        mP[j][k] = mP[j - 1][k - 1] * f2;
                        mPDeriv[j][k] = mP[j - 1][k - 1] * f1 + mPDeriv[j - 1][k - 1] * f2;
                    } else
                    if(j == 1 || k == j - 1)
                    {
                        mP[j][k] = mP[j - 1][k] * f1;
                        mPDeriv[j][k] = -f2 * mP[j - 1][k] + mPDeriv[j - 1][k] * f1;
                    } else
                    {
                        if(!_2D_assertionsDisabled && (j <= 1 || k >= j - 1))
                            throw new AssertionError();
                        f = (float)((j - 1) * (j - 1) - k * k) / (float)((j * 2 - 1) * (j * 2 - 3));
                        mP[j][k] = mP[j - 1][k] * f1 - mP[j - 2][k] * f;
                        mPDeriv[j][k] = (-f2 * mP[j - 1][k] + mPDeriv[j - 1][k] * f1) - mPDeriv[j - 2][k] * f;
                    }
                    k++;
                }
            }

        }
    }


    public GeomagneticField(float f, float f1, float f2, long l)
    {
        int i = G_COEFF.length;
        float f3 = Math.min(89.99999F, Math.max(-89.99999F, f));
        computeGeocentricCoordinates(f3, f1, f2);
        if(!_2D_assertionsDisabled && G_COEFF.length != H_COEFF.length)
            throw new AssertionError();
        LegendreTable legendretable = new LegendreTable(i - 1, (float)(1.5707963267948966D - (double)mGcLatitudeRad));
        float af[] = new float[i + 2];
        af[0] = 1.0F;
        af[1] = 6371.2F / mGcRadiusKm;
        for(int j = 2; j < af.length; j++)
            af[j] = af[j - 1] * af[1];

        float af1[] = new float[i];
        float af2[] = new float[i];
        af1[0] = 0.0F;
        af2[0] = 1.0F;
        af1[1] = (float)Math.sin(mGcLongitudeRad);
        af2[1] = (float)Math.cos(mGcLongitudeRad);
        for(int k = 2; k < i; k++)
        {
            int j1 = k >> 1;
            af1[k] = af1[k - j1] * af2[j1] + af2[k - j1] * af1[j1];
            af2[k] = af2[k - j1] * af2[j1] - af1[k - j1] * af1[j1];
        }

        float f4 = 1.0F / (float)Math.cos(mGcLatitudeRad);
        float f5 = (float)(l - BASE_TIME) / 3.1536E+010F;
        f2 = 0.0F;
        f1 = 0.0F;
        f = 0.0F;
        for(int i1 = 1; i1 < i; i1++)
        {
            for(int k1 = 0; k1 <= i1; k1++)
            {
                float f6 = G_COEFF[i1][k1] + DELTA_G[i1][k1] * f5;
                float f7 = H_COEFF[i1][k1] + DELTA_H[i1][k1] * f5;
                f2 += af[i1 + 2] * (af2[k1] * f6 + af1[k1] * f7) * legendretable.mPDeriv[i1][k1] * SCHMIDT_QUASI_NORM_FACTORS[i1][k1];
                f1 += af[i1 + 2] * (float)k1 * (af1[k1] * f6 - af2[k1] * f7) * legendretable.mP[i1][k1] * SCHMIDT_QUASI_NORM_FACTORS[i1][k1] * f4;
                f -= (float)(i1 + 1) * af[i1 + 2] * (af2[k1] * f6 + af1[k1] * f7) * legendretable.mP[i1][k1] * SCHMIDT_QUASI_NORM_FACTORS[i1][k1];
            }

        }

        double d = Math.toRadians(f3) - (double)mGcLatitudeRad;
        mX = (float)((double)f2 * Math.cos(d) + (double)f * Math.sin(d));
        mY = f1;
        mZ = (float)((double)(-f2) * Math.sin(d) + (double)f * Math.cos(d));
    }

    private void computeGeocentricCoordinates(float f, float f1, float f2)
    {
        f2 /= 1000F;
        double d = Math.toRadians(f);
        f = (float)Math.cos(d);
        float f3 = (float)Math.sin(d);
        float f4 = f3 / f;
        float f5 = (float)Math.sqrt(4.068064E+007F * f * f + 4.04083E+007F * f3 * f3);
        mGcLatitudeRad = (float)Math.atan(((f5 * f2 + 4.04083E+007F) * f4) / (f5 * f2 + 4.068064E+007F));
        mGcLongitudeRad = (float)Math.toRadians(f1);
        mGcRadiusKm = (float)Math.sqrt(f2 * f2 + 2.0F * f2 * (float)Math.sqrt(4.068064E+007F * f * f + 4.04083E+007F * f3 * f3) + (1.654914E+015F * f * f + 1.632831E+015F * f3 * f3) / (4.068064E+007F * f * f + 4.04083E+007F * f3 * f3));
    }

    private static float[][] computeSchmidtQuasiNormFactors(int i)
    {
        float af[][] = new float[i + 1][];
        af[0] = (new float[] {
            1.0F
        });
        for(int j = 1; j <= i; j++)
        {
            af[j] = new float[j + 1];
            af[j][0] = (af[j - 1][0] * (float)(j * 2 - 1)) / (float)j;
            int k = 1;
            while(k <= j) 
            {
                float af1[] = af[j];
                float f = af[j][k - 1];
                byte byte0;
                if(k == 1)
                    byte0 = 2;
                else
                    byte0 = 1;
                af1[k] = (float)Math.sqrt((float)(byte0 * ((j - k) + 1)) / (float)(j + k)) * f;
                k++;
            }
        }

        return af;
    }

    public float getDeclination()
    {
        return (float)Math.toDegrees(Math.atan2(mY, mX));
    }

    public float getFieldStrength()
    {
        return (float)Math.sqrt(mX * mX + mY * mY + mZ * mZ);
    }

    public float getHorizontalStrength()
    {
        return (float)Math.hypot(mX, mY);
    }

    public float getInclination()
    {
        return (float)Math.toDegrees(Math.atan2(mZ, getHorizontalStrength()));
    }

    public float getX()
    {
        return mX;
    }

    public float getY()
    {
        return mY;
    }

    public float getZ()
    {
        return mZ;
    }

    static final boolean _2D_assertionsDisabled = android/hardware/GeomagneticField.desiredAssertionStatus() ^ true;
    private static final long BASE_TIME = (new GregorianCalendar(2010, 1, 1)).getTimeInMillis();
    private static final float DELTA_G[][] = {
        {
            0.0F
        }, {
            11.6F, 16.5F
        }, {
            -12.1F, -4.4F, 1.9F
        }, {
            0.4F, -4.1F, -2.9F, -7.7F
        }, {
            -1.8F, 2.3F, -8.7F, 4.6F, -2.1F
        }, {
            -1F, 0.6F, -1.8F, -1F, 0.9F, 1.0F
        }, {
            -0.2F, -0.2F, -0.1F, 2.0F, -1.7F, -0.3F, 1.7F
        }, {
            0.1F, -0.1F, -0.6F, 1.3F, 0.4F, 0.3F, -0.7F, 0.6F
        }, {
            -0.1F, 0.1F, -0.6F, 0.2F, -0.2F, 0.3F, 0.3F, -0.6F, 0.2F
        }, {
            0.0F, -0.1F, 0.0F, 0.3F, -0.4F, -0.3F, 0.1F, -0.1F, -0.4F, -0.2F
        }, {
            0.0F, 0.0F, -0.1F, 0.2F, 0.0F, -0.1F, -0.2F, 0.0F, -0.1F, -0.2F, 
            -0.2F
        }, {
            0.0F, 0.0F, 0.0F, 0.1F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 
            -0.1F, 0.0F
        }, {
            0.0F, 0.0F, 0.1F, 0.1F, -0.1F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 
            0.0F, -0.1F, 0.1F
        }
    };
    private static final float DELTA_H[][];
    private static final float EARTH_REFERENCE_RADIUS_KM = 6371.2F;
    private static final float EARTH_SEMI_MAJOR_AXIS_KM = 6378.137F;
    private static final float EARTH_SEMI_MINOR_AXIS_KM = 6356.752F;
    private static final float G_COEFF[][] = {
        {
            0.0F
        }, {
            -29496.6F, -1586.3F
        }, {
            -2396.6F, 3026.1F, 1668.6F
        }, {
            1340.1F, -2326.2F, 1231.9F, 634F
        }, {
            912.6F, 808.9F, 166.7F, -357.1F, 89.4F
        }, {
            -230.9F, 357.2F, 200.3F, -141.1F, -163F, -7.8F
        }, {
            72.8F, 68.6F, 76F, -141.4F, -22.8F, 13.2F, -77.9F
        }, {
            80.5F, -75.1F, -4.7F, 45.3F, 13.9F, 10.4F, 1.7F, 4.9F
        }, {
            24.4F, 8.1F, -14.5F, -5.6F, -19.3F, 11.5F, 10.9F, -14.1F, -3.7F
        }, {
            5.4F, 9.4F, 3.4F, -5.2F, 3.1F, -12.4F, -0.7F, 8.4F, -8.5F, -10.1F
        }, {
            -2F, -6.3F, 0.9F, -1.1F, -0.2F, 2.5F, -0.3F, 2.2F, 3.1F, -1F, 
            -2.8F
        }, {
            3F, -1.5F, -2.1F, 1.7F, -0.5F, 0.5F, -0.8F, 0.4F, 1.8F, 0.1F, 
            0.7F, 3.8F
        }, {
            -2.2F, -0.2F, 0.3F, 1.0F, -0.6F, 0.9F, -0.1F, 0.5F, -0.4F, -0.4F, 
            0.2F, -0.8F, 0.0F
        }
    };
    private static final float H_COEFF[][];
    private static final float SCHMIDT_QUASI_NORM_FACTORS[][] = computeSchmidtQuasiNormFactors(G_COEFF.length);
    private float mGcLatitudeRad;
    private float mGcLongitudeRad;
    private float mGcRadiusKm;
    private float mX;
    private float mY;
    private float mZ;

    static 
    {
        float af[] = {
            0.0F, 4944.4F
        };
        float af1[] = {
            0.0F, -160.2F, 251.9F, -536.6F
        };
        float af2[] = {
            0.0F, 286.4F, -211.2F, 164.3F, -309.1F
        };
        float af3[] = {
            0.0F, 44.6F, 188.9F, -118.2F, 0.0F, 100.9F
        };
        float af4[] = {
            0.0F, -20.8F, 44.1F, 61.5F, -66.3F, 3.1F, 55F
        };
        float af5[] = {
            0.0F, -57.9F, -21.1F, 6.5F, 24.9F, 7F, -27.7F, -3.3F
        };
        float af6[] = {
            0.0F, 11F, -20F, 11.9F, -17.4F, 16.7F, 7F, -10.8F, 1.7F
        };
        float af7[] = {
            0.0F, -20.5F, 11.5F, 12.8F, -7.2F, -7.4F, 8F, 2.1F, -6.1F, 7F
        };
        float af8[] = {
            0.0F, 2.8F, -0.1F, 4.7F, 4.4F, -7.2F, -1F, -3.9F, -2F, -2F, 
            -8.3F
        };
        float af9[] = {
            0.0F, 0.2F, 1.7F, -0.6F, -1.8F, 0.9F, -0.4F, -2.5F, -1.3F, -2.1F, 
            -1.9F, -1.8F
        };
        H_COEFF = (new float[][] {
            new float[] {
                0.0F
            }, af, new float[] {
                0.0F, -2707.7F, -576.1F
            }, af1, af2, af3, af4, af5, af6, af7, af8, af9, new float[] {
                0.0F, -0.9F, 0.3F, 2.1F, -2.5F, 0.5F, 0.6F, 0.0F, 0.1F, 0.3F, 
                -0.9F, -0.2F, 0.9F
            }
        });
        af = (new float[] {
            0.0F
        });
        af1 = (new float[] {
            0.0F, -22.5F, -11.8F
        });
        af2 = (new float[] {
            0.0F, 7.3F, -3.9F, -2.6F
        });
        af3 = (new float[] {
            0.0F, 1.1F, 2.7F, 3.9F, -0.8F
        });
        af4 = (new float[] {
            0.0F, 0.7F, 0.3F, -0.1F, -0.1F, -0.8F, -0.3F, 0.3F
        });
        af5 = (new float[] {
            0.0F, 0.1F, -0.1F, 0.0F, -0.1F, -0.1F, 0.0F, -0.1F, -0.2F, 0.0F, 
            -0.1F
        });
        DELTA_H = (new float[][] {
            af, new float[] {
                0.0F, -25.9F
            }, af1, af2, af3, new float[] {
                0.0F, 0.4F, 1.8F, 1.2F, 4F, -0.6F
            }, new float[] {
                0.0F, -0.2F, -2.1F, -0.4F, -0.6F, 0.5F, 0.9F
            }, af4, new float[] {
                0.0F, -0.1F, 0.2F, 0.4F, 0.4F, 0.1F, -0.1F, 0.4F, 0.3F
            }, new float[] {
                0.0F, 0.0F, -0.2F, 0.0F, -0.1F, 0.1F, 0.0F, -0.2F, 0.3F, 0.2F
            }, 
            af5, new float[] {
                0.0F, 0.0F, 0.1F, 0.0F, 0.1F, 0.0F, 0.1F, 0.0F, -0.1F, -0.1F, 
                0.0F, -0.1F
            }, new float[] {
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F, 0.0F, 0.0F, 0.0F, 
                0.0F, 0.0F, 0.0F
            }
        });
    }
}
