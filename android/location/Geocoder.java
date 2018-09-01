// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import java.io.IOException;
import java.util.*;

// Referenced classes of package android.location:
//            GeocoderParams, ILocationManager

public final class Geocoder
{

    public Geocoder(Context context)
    {
        this(context, Locale.getDefault());
    }

    public Geocoder(Context context, Locale locale)
    {
        if(locale == null)
        {
            throw new NullPointerException("locale == null");
        } else
        {
            mParams = new GeocoderParams(context, locale);
            mService = ILocationManager.Stub.asInterface(ServiceManager.getService("location"));
            return;
        }
    }

    public static boolean isPresent()
    {
        ILocationManager ilocationmanager = ILocationManager.Stub.asInterface(ServiceManager.getService("location"));
        boolean flag;
        try
        {
            flag = ilocationmanager.geocoderIsPresent();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("Geocoder", "isPresent: got RemoteException", remoteexception);
            return false;
        }
        return flag;
    }

    public List getFromLocation(double d, double d1, int i)
        throws IOException
    {
        if(d < -90D || d > 90D)
            throw new IllegalArgumentException((new StringBuilder()).append("latitude == ").append(d).toString());
        if(d1 < -180D || d1 > 180D)
            throw new IllegalArgumentException((new StringBuilder()).append("longitude == ").append(d1).toString());
        Object obj;
        String s;
        obj = JVM INSTR new #107 <Class ArrayList>;
        ((ArrayList) (obj)).ArrayList();
        s = mService.getFromLocation(d, d1, i, mParams, ((List) (obj)));
        if(s != null)
        {
            try
            {
                obj = JVM INSTR new #76  <Class IOException>;
                ((IOException) (obj)).IOException(s);
                throw obj;
            }
            catch(RemoteException remoteexception)
            {
                Log.e("Geocoder", "getFromLocation: got RemoteException", remoteexception);
            }
            return null;
        } else
        {
            return ((List) (obj));
        }
    }

    public List getFromLocationName(String s, int i)
        throws IOException
    {
        if(s == null)
            throw new IllegalArgumentException("locationName == null");
        Object obj;
        obj = JVM INSTR new #107 <Class ArrayList>;
        ((ArrayList) (obj)).ArrayList();
        s = mService.getFromLocationName(s, 0.0D, 0.0D, 0.0D, 0.0D, i, mParams, ((List) (obj)));
        if(s != null)
        {
            try
            {
                obj = JVM INSTR new #76  <Class IOException>;
                ((IOException) (obj)).IOException(s);
                throw obj;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("Geocoder", "getFromLocationName: got RemoteException", s);
            }
            return null;
        } else
        {
            return ((List) (obj));
        }
    }

    public List getFromLocationName(String s, int i, double d, double d1, double d2, double d3)
        throws IOException
    {
        if(s == null)
            throw new IllegalArgumentException("locationName == null");
        if(d < -90D || d > 90D)
            throw new IllegalArgumentException((new StringBuilder()).append("lowerLeftLatitude == ").append(d).toString());
        if(d1 < -180D || d1 > 180D)
            throw new IllegalArgumentException((new StringBuilder()).append("lowerLeftLongitude == ").append(d1).toString());
        if(d2 < -90D || d2 > 90D)
            throw new IllegalArgumentException((new StringBuilder()).append("upperRightLatitude == ").append(d2).toString());
        if(d3 < -180D || d3 > 180D)
            throw new IllegalArgumentException((new StringBuilder()).append("upperRightLongitude == ").append(d3).toString());
        Object obj;
        obj = JVM INSTR new #107 <Class ArrayList>;
        ((ArrayList) (obj)).ArrayList();
        s = mService.getFromLocationName(s, d, d1, d2, d3, i, mParams, ((List) (obj)));
        if(s != null)
        {
            try
            {
                obj = JVM INSTR new #76  <Class IOException>;
                ((IOException) (obj)).IOException(s);
                throw obj;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("Geocoder", "getFromLocationName: got RemoteException", s);
            }
            return null;
        } else
        {
            return ((List) (obj));
        }
    }

    private static final String TAG = "Geocoder";
    private GeocoderParams mParams;
    private ILocationManager mService;
}
