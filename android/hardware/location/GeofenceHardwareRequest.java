// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;


public final class GeofenceHardwareRequest
{

    public GeofenceHardwareRequest()
    {
        mLastTransition = 4;
        mUnknownTimer = 30000;
        mMonitorTransitions = 7;
        mNotificationResponsiveness = 5000;
        mSourceTechnologies = 1;
    }

    public static GeofenceHardwareRequest createCircularGeofence(double d, double d1, double d2)
    {
        GeofenceHardwareRequest geofencehardwarerequest = new GeofenceHardwareRequest();
        geofencehardwarerequest.setCircularGeofence(d, d1, d2);
        return geofencehardwarerequest;
    }

    private void setCircularGeofence(double d, double d1, double d2)
    {
        mLatitude = d;
        mLongitude = d1;
        mRadius = d2;
        mType = 0;
    }

    public int getLastTransition()
    {
        return mLastTransition;
    }

    public double getLatitude()
    {
        return mLatitude;
    }

    public double getLongitude()
    {
        return mLongitude;
    }

    public int getMonitorTransitions()
    {
        return mMonitorTransitions;
    }

    public int getNotificationResponsiveness()
    {
        return mNotificationResponsiveness;
    }

    public double getRadius()
    {
        return mRadius;
    }

    public int getSourceTechnologies()
    {
        return mSourceTechnologies;
    }

    int getType()
    {
        return mType;
    }

    public int getUnknownTimer()
    {
        return mUnknownTimer;
    }

    public void setLastTransition(int i)
    {
        mLastTransition = i;
    }

    public void setMonitorTransitions(int i)
    {
        mMonitorTransitions = i;
    }

    public void setNotificationResponsiveness(int i)
    {
        mNotificationResponsiveness = i;
    }

    public void setSourceTechnologies(int i)
    {
        i &= 0x1f;
        if(i == 0)
        {
            throw new IllegalArgumentException("At least one valid source technology must be set.");
        } else
        {
            mSourceTechnologies = i;
            return;
        }
    }

    public void setUnknownTimer(int i)
    {
        mUnknownTimer = i;
    }

    static final int GEOFENCE_TYPE_CIRCLE = 0;
    private int mLastTransition;
    private double mLatitude;
    private double mLongitude;
    private int mMonitorTransitions;
    private int mNotificationResponsiveness;
    private double mRadius;
    private int mSourceTechnologies;
    private int mType;
    private int mUnknownTimer;
}
