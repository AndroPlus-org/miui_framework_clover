// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.content.Context;
import android.location.*;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;
import miui.maml.ScreenContext;
import miui.maml.ScreenElementRoot;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.data:
//            VariableBinder, Expression

public class LocationBinder extends VariableBinder
{

    static void _2D_wrap0(LocationBinder locationbinder, Location location)
    {
        locationbinder.updateLocation(location);
    }

    public LocationBinder(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mLocationProvider = null;
        mProviderType = Utils.getAttrAsInt(element, "type", 0);
        mMinTime = Utils.getAttrAsLong(element, "time", 30000L);
        mMinDistance = Utils.getAttrAsFloat(element, "distance", 10F);
        mEnableExp = Expression.build(getVariables(), element.getAttribute("enable"));
        loadVariables(element);
        if(sLocationManager == null)
            sLocationManager = (LocationManager)getContext().mContext.getSystemService("location");
        element = sLocationManager.getProviders(true);
        if(element == null)
        {
            updateLocation(null);
            return;
        }
        if(mProviderType == 2)
        {
            if(element.contains("gps"))
                mLocationProvider = "gps";
        } else
        if(mProviderType == 1)
        {
            if(element.contains("network"))
                mLocationProvider = "network";
        } else
        if(element.contains("network"))
            mLocationProvider = "network";
        else
        if(element.contains("gps"))
            mLocationProvider = "gps";
        if(mLocationProvider == null)
        {
            updateLocation(null);
            return;
        } else
        {
            mLocationListener = new LocationListener() {

                public void onLocationChanged(Location location)
                {
                    LocationBinder._2D_wrap0(LocationBinder.this, location);
                }

                public void onProviderDisabled(String s)
                {
                }

                public void onProviderEnabled(String s)
                {
                }

                public void onStatusChanged(String s, int i, Bundle bundle)
                {
                }

                final LocationBinder this$0;

            
            {
                this$0 = LocationBinder.this;
                super();
            }
            }
;
            updateLocation(sLocationManager.getLastKnownLocation(mLocationProvider));
            return;
        }
    }

    private void registerListener()
    {
        if(mRegistered || mEnable ^ true || mLocationProvider == null)
        {
            return;
        } else
        {
            sLocationManager.requestLocationUpdates(mLocationProvider, mMinTime, mMinDistance, mLocationListener);
            mRegistered = true;
            return;
        }
    }

    private void unregisterListener()
    {
        if(!mRegistered)
        {
            return;
        } else
        {
            sLocationManager.removeUpdates(mLocationListener);
            mRegistered = false;
            return;
        }
    }

    private void updateLocation(Location location)
    {
        String as[] = null;
        if(location != null)
        {
            as = new String[7];
            as[0] = String.valueOf(location.getAccuracy());
            as[1] = String.valueOf(location.getAltitude());
            as[2] = String.valueOf(location.getBearing());
            as[3] = String.valueOf(location.getLatitude());
            as[4] = String.valueOf(location.getLongitude());
            as[5] = String.valueOf(location.getSpeed());
            as[6] = String.valueOf(location.getTime());
        }
        for(location = mVariables.iterator(); location.hasNext(); ((VariableBinder.Variable)location.next()).set(as));
        onUpdateComplete();
    }

    public void finish()
    {
        unregisterListener();
        super.finish();
    }

    public void init()
    {
        boolean flag = true;
        super.init();
        boolean flag1 = flag;
        if(mEnableExp != null)
            if(mEnableExp.evaluate() > 0.0D)
                flag1 = flag;
            else
                flag1 = false;
        mEnable = flag1;
        registerListener();
    }

    protected VariableBinder.Variable onLoadVariable(Element element)
    {
        return new VariableBinder.Variable(element, getContext().mVariables);
    }

    public void pause()
    {
        super.pause();
        unregisterListener();
    }

    public void resume()
    {
        super.resume();
        registerListener();
    }

    private static final float DEFAULT_MIN_DISTANCE = 10F;
    private static final long DEFAULT_MIN_TIME = 30000L;
    private static final int DEFAULT_PROVIDER_TYPE = 0;
    private static final int GPS = 2;
    private static final int NETWORK = 1;
    public static final String TAG_NAME = "LocationBinder";
    private static LocationManager sLocationManager;
    private boolean mEnable;
    private Expression mEnableExp;
    private LocationListener mLocationListener;
    private String mLocationProvider;
    private float mMinDistance;
    private long mMinTime;
    private int mProviderType;
    private boolean mRegistered;
}
