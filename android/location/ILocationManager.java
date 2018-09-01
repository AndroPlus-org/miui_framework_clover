// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.app.PendingIntent;
import android.os.*;
import com.android.internal.location.ProviderProperties;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.location:
//            IBatchedLocationCallback, IGnssMeasurementsListener, IGnssNavigationMessageListener, Criteria, 
//            GeocoderParams, LocationRequest, Location, ILocationListener, 
//            IGnssStatusListener, Geofence, Address

public interface ILocationManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ILocationManager
    {

        public static ILocationManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.location.ILocationManager");
            if(iinterface != null && (iinterface instanceof ILocationManager))
                return (ILocationManager)iinterface;
            else
                return new Proxy(ibinder);
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.location.ILocationManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.location.ILocationManager");
                LocationRequest locationrequest;
                ILocationListener ilocationlistener;
                PendingIntent pendingintent2;
                if(parcel.readInt() != 0)
                    locationrequest = (LocationRequest)LocationRequest.CREATOR.createFromParcel(parcel);
                else
                    locationrequest = null;
                ilocationlistener = ILocationListener.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    pendingintent2 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent2 = null;
                requestLocationUpdates(locationrequest, ilocationlistener, pendingintent2, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.location.ILocationManager");
                ILocationListener ilocationlistener1 = ILocationListener.Stub.asInterface(parcel.readStrongBinder());
                PendingIntent pendingintent;
                if(parcel.readInt() != 0)
                    pendingintent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent = null;
                removeUpdates(ilocationlistener1, pendingintent, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.location.ILocationManager");
                LocationRequest locationrequest1;
                PendingIntent pendingintent1;
                Geofence geofence1;
                if(parcel.readInt() != 0)
                    locationrequest1 = (LocationRequest)LocationRequest.CREATOR.createFromParcel(parcel);
                else
                    locationrequest1 = null;
                if(parcel.readInt() != 0)
                    geofence1 = (Geofence)Geofence.CREATOR.createFromParcel(parcel);
                else
                    geofence1 = null;
                if(parcel.readInt() != 0)
                    pendingintent1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent1 = null;
                requestGeofence(locationrequest1, geofence1, pendingintent1, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.location.ILocationManager");
                Geofence geofence;
                PendingIntent pendingintent3;
                if(parcel.readInt() != 0)
                    geofence = (Geofence)Geofence.CREATOR.createFromParcel(parcel);
                else
                    geofence = null;
                if(parcel.readInt() != 0)
                    pendingintent3 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent3 = null;
                removeGeofence(geofence, pendingintent3, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.location.ILocationManager");
                LocationRequest locationrequest2;
                if(parcel.readInt() != 0)
                    locationrequest2 = (LocationRequest)LocationRequest.CREATOR.createFromParcel(parcel);
                else
                    locationrequest2 = null;
                parcel = getLastLocation(locationrequest2, parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.location.ILocationManager");
                boolean flag = registerGnssStatusCallback(IGnssStatusListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.location.ILocationManager");
                unregisterGnssStatusCallback(IGnssStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.location.ILocationManager");
                boolean flag1 = geocoderIsPresent();
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.location.ILocationManager");
                double d = parcel.readDouble();
                double d2 = parcel.readDouble();
                i = parcel.readInt();
                ArrayList arraylist;
                if(parcel.readInt() != 0)
                    parcel = (GeocoderParams)GeocoderParams.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                arraylist = new ArrayList();
                parcel = getFromLocation(d, d2, i, parcel, arraylist);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                parcel1.writeTypedList(arraylist);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.location.ILocationManager");
                String s = parcel.readString();
                double d4 = parcel.readDouble();
                double d3 = parcel.readDouble();
                double d1 = parcel.readDouble();
                double d5 = parcel.readDouble();
                i = parcel.readInt();
                ArrayList arraylist1;
                if(parcel.readInt() != 0)
                    parcel = (GeocoderParams)GeocoderParams.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                arraylist1 = new ArrayList();
                parcel = getFromLocationName(s, d4, d3, d1, d5, i, parcel, arraylist1);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                parcel1.writeTypedList(arraylist1);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.location.ILocationManager");
                boolean flag2 = sendNiResponse(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.location.ILocationManager");
                boolean flag3 = addGnssMeasurementsListener(IGnssMeasurementsListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.location.ILocationManager");
                removeGnssMeasurementsListener(IGnssMeasurementsListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.location.ILocationManager");
                boolean flag4 = addGnssNavigationMessageListener(IGnssNavigationMessageListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.location.ILocationManager");
                removeGnssNavigationMessageListener(IGnssNavigationMessageListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.location.ILocationManager");
                i = getGnssYearOfHardware();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.location.ILocationManager");
                i = getGnssBatchSize(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.location.ILocationManager");
                boolean flag5 = addGnssBatchingCallback(IBatchedLocationCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.location.ILocationManager");
                removeGnssBatchingCallback();
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.location.ILocationManager");
                long l = parcel.readLong();
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                flag6 = startGnssBatch(l, flag6, parcel.readString());
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.location.ILocationManager");
                flushGnssBatch(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.location.ILocationManager");
                boolean flag7 = stopGnssBatch();
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.location.ILocationManager");
                parcel = getAllProviders();
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.location.ILocationManager");
                Criteria criteria;
                boolean flag8;
                if(parcel.readInt() != 0)
                    criteria = (Criteria)Criteria.CREATOR.createFromParcel(parcel);
                else
                    criteria = null;
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                parcel = getProviders(criteria, flag8);
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.location.ILocationManager");
                Criteria criteria1;
                boolean flag9;
                if(parcel.readInt() != 0)
                    criteria1 = (Criteria)Criteria.CREATOR.createFromParcel(parcel);
                else
                    criteria1 = null;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                parcel = getBestProvider(criteria1, flag9);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.location.ILocationManager");
                String s1 = parcel.readString();
                boolean flag10;
                if(parcel.readInt() != 0)
                    parcel = (Criteria)Criteria.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag10 = providerMeetsCriteria(s1, parcel);
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.location.ILocationManager");
                parcel = getProviderProperties(parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.location.ILocationManager");
                parcel = getNetworkProviderPackage();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.location.ILocationManager");
                boolean flag11 = isProviderEnabled(parcel.readString());
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.location.ILocationManager");
                String s4 = parcel.readString();
                ProviderProperties providerproperties;
                if(parcel.readInt() != 0)
                    providerproperties = (ProviderProperties)ProviderProperties.CREATOR.createFromParcel(parcel);
                else
                    providerproperties = null;
                addTestProvider(s4, providerproperties, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.location.ILocationManager");
                removeTestProvider(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.location.ILocationManager");
                String s5 = parcel.readString();
                Location location;
                if(parcel.readInt() != 0)
                    location = (Location)Location.CREATOR.createFromParcel(parcel);
                else
                    location = null;
                setTestProviderLocation(s5, location, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.location.ILocationManager");
                clearTestProviderLocation(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.location.ILocationManager");
                String s2 = parcel.readString();
                boolean flag12;
                if(parcel.readInt() != 0)
                    flag12 = true;
                else
                    flag12 = false;
                setTestProviderEnabled(s2, flag12, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.location.ILocationManager");
                clearTestProviderEnabled(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.location.ILocationManager");
                String s6 = parcel.readString();
                i = parcel.readInt();
                Bundle bundle;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                setTestProviderStatus(s6, i, bundle, parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.location.ILocationManager");
                clearTestProviderStatus(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.location.ILocationManager");
                String s3 = parcel.readString();
                String s7 = parcel.readString();
                boolean flag13;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag13 = sendExtraCommand(s3, s7, parcel);
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.location.ILocationManager");
                Location location1;
                boolean flag14;
                if(parcel.readInt() != 0)
                    location1 = (Location)Location.CREATOR.createFromParcel(parcel);
                else
                    location1 = null;
                if(parcel.readInt() != 0)
                    flag14 = true;
                else
                    flag14 = false;
                reportLocation(location1, flag14);
                parcel1.writeNoException();
                return true;

            case 40: // '('
                parcel.enforceInterface("android.location.ILocationManager");
                reportLocationBatch(parcel.createTypedArrayList(Location.CREATOR));
                parcel1.writeNoException();
                return true;

            case 41: // ')'
                parcel.enforceInterface("android.location.ILocationManager");
                locationCallbackFinished(ILocationListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 42: // '*'
                parcel.enforceInterface("android.location.ILocationManager");
                parcel = getBackgroundThrottlingWhitelist();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.location.ILocationManager";
        static final int TRANSACTION_addGnssBatchingCallback = 18;
        static final int TRANSACTION_addGnssMeasurementsListener = 12;
        static final int TRANSACTION_addGnssNavigationMessageListener = 14;
        static final int TRANSACTION_addTestProvider = 30;
        static final int TRANSACTION_clearTestProviderEnabled = 35;
        static final int TRANSACTION_clearTestProviderLocation = 33;
        static final int TRANSACTION_clearTestProviderStatus = 37;
        static final int TRANSACTION_flushGnssBatch = 21;
        static final int TRANSACTION_geocoderIsPresent = 8;
        static final int TRANSACTION_getAllProviders = 23;
        static final int TRANSACTION_getBackgroundThrottlingWhitelist = 42;
        static final int TRANSACTION_getBestProvider = 25;
        static final int TRANSACTION_getFromLocation = 9;
        static final int TRANSACTION_getFromLocationName = 10;
        static final int TRANSACTION_getGnssBatchSize = 17;
        static final int TRANSACTION_getGnssYearOfHardware = 16;
        static final int TRANSACTION_getLastLocation = 5;
        static final int TRANSACTION_getNetworkProviderPackage = 28;
        static final int TRANSACTION_getProviderProperties = 27;
        static final int TRANSACTION_getProviders = 24;
        static final int TRANSACTION_isProviderEnabled = 29;
        static final int TRANSACTION_locationCallbackFinished = 41;
        static final int TRANSACTION_providerMeetsCriteria = 26;
        static final int TRANSACTION_registerGnssStatusCallback = 6;
        static final int TRANSACTION_removeGeofence = 4;
        static final int TRANSACTION_removeGnssBatchingCallback = 19;
        static final int TRANSACTION_removeGnssMeasurementsListener = 13;
        static final int TRANSACTION_removeGnssNavigationMessageListener = 15;
        static final int TRANSACTION_removeTestProvider = 31;
        static final int TRANSACTION_removeUpdates = 2;
        static final int TRANSACTION_reportLocation = 39;
        static final int TRANSACTION_reportLocationBatch = 40;
        static final int TRANSACTION_requestGeofence = 3;
        static final int TRANSACTION_requestLocationUpdates = 1;
        static final int TRANSACTION_sendExtraCommand = 38;
        static final int TRANSACTION_sendNiResponse = 11;
        static final int TRANSACTION_setTestProviderEnabled = 34;
        static final int TRANSACTION_setTestProviderLocation = 32;
        static final int TRANSACTION_setTestProviderStatus = 36;
        static final int TRANSACTION_startGnssBatch = 20;
        static final int TRANSACTION_stopGnssBatch = 22;
        static final int TRANSACTION_unregisterGnssStatusCallback = 7;

        public Stub()
        {
            attachInterface(this, "android.location.ILocationManager");
        }
    }

    private static class Stub.Proxy
        implements ILocationManager
    {

        public boolean addGnssBatchingCallback(IBatchedLocationCallback ibatchedlocationcallback, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            if(ibatchedlocationcallback == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = ibatchedlocationcallback.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ibatchedlocationcallback;
            parcel1.recycle();
            parcel.recycle();
            throw ibatchedlocationcallback;
        }

        public boolean addGnssMeasurementsListener(IGnssMeasurementsListener ignssmeasurementslistener, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            if(ignssmeasurementslistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = ignssmeasurementslistener.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ignssmeasurementslistener;
            parcel1.recycle();
            parcel.recycle();
            throw ignssmeasurementslistener;
        }

        public boolean addGnssNavigationMessageListener(IGnssNavigationMessageListener ignssnavigationmessagelistener, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            if(ignssnavigationmessagelistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = ignssnavigationmessagelistener.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ignssnavigationmessagelistener;
            parcel1.recycle();
            parcel.recycle();
            throw ignssnavigationmessagelistener;
        }

        public void addTestProvider(String s, ProviderProperties providerproperties, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeString(s);
            if(providerproperties == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            providerproperties.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s1);
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void clearTestProviderEnabled(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(35, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clearTestProviderLocation(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clearTestProviderStatus(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void flushGnssBatch(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeString(s);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean geocoderIsPresent()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.location.ILocationManager");
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getAllProviders()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            ArrayList arraylist;
            parcel.writeInterfaceToken("android.location.ILocationManager");
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String[] getBackgroundThrottlingWhitelist()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.location.ILocationManager");
            mRemote.transact(42, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getBestProvider(Criteria criteria, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            if(criteria == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            criteria.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            criteria = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return criteria;
            parcel.writeInt(0);
              goto _L1
            criteria;
            parcel1.recycle();
            parcel.recycle();
            throw criteria;
        }

        public String getFromLocation(double d, double d1, int i, GeocoderParams geocoderparams, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            parcel.writeInt(i);
            if(geocoderparams == null)
                break MISSING_BLOCK_LABEL_107;
            parcel.writeInt(1);
            geocoderparams.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            geocoderparams = parcel1.readString();
            parcel1.readTypedList(list, Address.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return geocoderparams;
            parcel.writeInt(0);
              goto _L1
            geocoderparams;
            parcel1.recycle();
            parcel.recycle();
            throw geocoderparams;
        }

        public String getFromLocationName(String s, double d, double d1, double d2, 
                double d3, int i, GeocoderParams geocoderparams, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeString(s);
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            parcel.writeDouble(d2);
            parcel.writeDouble(d3);
            parcel.writeInt(i);
            if(geocoderparams == null)
                break MISSING_BLOCK_LABEL_126;
            parcel.writeInt(1);
            geocoderparams.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.readTypedList(list, Address.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getGnssBatchSize(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeString(s);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getGnssYearOfHardware()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.location.ILocationManager");
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.location.ILocationManager";
        }

        public Location getLastLocation(LocationRequest locationrequest, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            if(locationrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            locationrequest.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_108;
            locationrequest = (Location)Location.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return locationrequest;
_L2:
            parcel.writeInt(0);
              goto _L3
            locationrequest;
            parcel1.recycle();
            parcel.recycle();
            throw locationrequest;
            locationrequest = null;
              goto _L4
        }

        public String getNetworkProviderPackage()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.location.ILocationManager");
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ProviderProperties getProviderProperties(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeString(s);
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ProviderProperties)ProviderProperties.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public List getProviders(Criteria criteria, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            if(criteria == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            criteria.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            criteria = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return criteria;
            parcel.writeInt(0);
              goto _L1
            criteria;
            parcel1.recycle();
            parcel.recycle();
            throw criteria;
        }

        public boolean isProviderEnabled(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeString(s);
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void locationCallbackFinished(ILocationListener ilocationlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            if(ilocationlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ilocationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(41, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ilocationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw ilocationlistener;
        }

        public boolean providerMeetsCriteria(String s, Criteria criteria)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeString(s);
            if(criteria == null)
                break MISSING_BLOCK_LABEL_83;
            parcel.writeInt(1);
            criteria.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean registerGnssStatusCallback(IGnssStatusListener ignssstatuslistener, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            if(ignssstatuslistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = ignssstatuslistener.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            ignssstatuslistener;
            parcel1.recycle();
            parcel.recycle();
            throw ignssstatuslistener;
        }

        public void removeGeofence(Geofence geofence, PendingIntent pendingintent, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            if(geofence == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            geofence.writeToParcel(parcel, 0);
_L3:
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_111;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L4:
            parcel.writeString(s);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            geofence;
            parcel1.recycle();
            parcel.recycle();
            throw geofence;
            parcel.writeInt(0);
              goto _L4
        }

        public void removeGnssBatchingCallback()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void removeGnssMeasurementsListener(IGnssMeasurementsListener ignssmeasurementslistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            if(ignssmeasurementslistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ignssmeasurementslistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ignssmeasurementslistener;
            parcel1.recycle();
            parcel.recycle();
            throw ignssmeasurementslistener;
        }

        public void removeGnssNavigationMessageListener(IGnssNavigationMessageListener ignssnavigationmessagelistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            if(ignssnavigationmessagelistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ignssnavigationmessagelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ignssnavigationmessagelistener;
            parcel1.recycle();
            parcel.recycle();
            throw ignssnavigationmessagelistener;
        }

        public void removeTestProvider(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void removeUpdates(ILocationListener ilocationlistener, PendingIntent pendingintent, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            if(ilocationlistener == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = ilocationlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ilocationlistener;
            parcel1.recycle();
            parcel.recycle();
            throw ilocationlistener;
        }

        public void reportLocation(Location location, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            if(location == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            location.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(39, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            location;
            parcel1.recycle();
            parcel.recycle();
            throw location;
        }

        public void reportLocationBatch(List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeTypedList(list);
            mRemote.transact(40, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public void requestGeofence(LocationRequest locationrequest, Geofence geofence, PendingIntent pendingintent, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            if(locationrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            locationrequest.writeToParcel(parcel, 0);
_L5:
            if(geofence == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            geofence.writeToParcel(parcel, 0);
_L6:
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_138;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L7:
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            locationrequest;
            parcel1.recycle();
            parcel.recycle();
            throw locationrequest;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void requestLocationUpdates(LocationRequest locationrequest, ILocationListener ilocationlistener, PendingIntent pendingintent, String s)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            if(locationrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            locationrequest.writeToParcel(parcel, 0);
_L3:
            locationrequest = obj;
            if(ilocationlistener == null)
                break MISSING_BLOCK_LABEL_51;
            locationrequest = ilocationlistener.asBinder();
            parcel.writeStrongBinder(locationrequest);
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_135;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L4:
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            locationrequest;
            parcel1.recycle();
            parcel.recycle();
            throw locationrequest;
            parcel.writeInt(0);
              goto _L4
        }

        public boolean sendExtraCommand(String s, String s1, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_106;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(38, parcel, parcel1, 0);
            parcel1.readException();
            boolean flag;
            if(parcel1.readInt() != 0)
                flag = true;
            else
                flag = false;
            if(parcel1.readInt() != 0)
                bundle.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean sendNiResponse(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setTestProviderEnabled(String s, boolean flag, String s1)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setTestProviderLocation(String s, Location location, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeString(s);
            if(location == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            location.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s1);
            mRemote.transact(32, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setTestProviderStatus(String s, int i, Bundle bundle, long l, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeLong(l);
            parcel.writeString(s1);
            mRemote.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean startGnssBatch(long l, boolean flag, String s)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            parcel.writeLong(l);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean stopGnssBatch()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.location.ILocationManager");
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void unregisterGnssStatusCallback(IGnssStatusListener ignssstatuslistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationManager");
            if(ignssstatuslistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ignssstatuslistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ignssstatuslistener;
            parcel1.recycle();
            parcel.recycle();
            throw ignssstatuslistener;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean addGnssBatchingCallback(IBatchedLocationCallback ibatchedlocationcallback, String s)
        throws RemoteException;

    public abstract boolean addGnssMeasurementsListener(IGnssMeasurementsListener ignssmeasurementslistener, String s)
        throws RemoteException;

    public abstract boolean addGnssNavigationMessageListener(IGnssNavigationMessageListener ignssnavigationmessagelistener, String s)
        throws RemoteException;

    public abstract void addTestProvider(String s, ProviderProperties providerproperties, String s1)
        throws RemoteException;

    public abstract void clearTestProviderEnabled(String s, String s1)
        throws RemoteException;

    public abstract void clearTestProviderLocation(String s, String s1)
        throws RemoteException;

    public abstract void clearTestProviderStatus(String s, String s1)
        throws RemoteException;

    public abstract void flushGnssBatch(String s)
        throws RemoteException;

    public abstract boolean geocoderIsPresent()
        throws RemoteException;

    public abstract List getAllProviders()
        throws RemoteException;

    public abstract String[] getBackgroundThrottlingWhitelist()
        throws RemoteException;

    public abstract String getBestProvider(Criteria criteria, boolean flag)
        throws RemoteException;

    public abstract String getFromLocation(double d, double d1, int i, GeocoderParams geocoderparams, List list)
        throws RemoteException;

    public abstract String getFromLocationName(String s, double d, double d1, double d2, 
            double d3, int i, GeocoderParams geocoderparams, List list)
        throws RemoteException;

    public abstract int getGnssBatchSize(String s)
        throws RemoteException;

    public abstract int getGnssYearOfHardware()
        throws RemoteException;

    public abstract Location getLastLocation(LocationRequest locationrequest, String s)
        throws RemoteException;

    public abstract String getNetworkProviderPackage()
        throws RemoteException;

    public abstract ProviderProperties getProviderProperties(String s)
        throws RemoteException;

    public abstract List getProviders(Criteria criteria, boolean flag)
        throws RemoteException;

    public abstract boolean isProviderEnabled(String s)
        throws RemoteException;

    public abstract void locationCallbackFinished(ILocationListener ilocationlistener)
        throws RemoteException;

    public abstract boolean providerMeetsCriteria(String s, Criteria criteria)
        throws RemoteException;

    public abstract boolean registerGnssStatusCallback(IGnssStatusListener ignssstatuslistener, String s)
        throws RemoteException;

    public abstract void removeGeofence(Geofence geofence, PendingIntent pendingintent, String s)
        throws RemoteException;

    public abstract void removeGnssBatchingCallback()
        throws RemoteException;

    public abstract void removeGnssMeasurementsListener(IGnssMeasurementsListener ignssmeasurementslistener)
        throws RemoteException;

    public abstract void removeGnssNavigationMessageListener(IGnssNavigationMessageListener ignssnavigationmessagelistener)
        throws RemoteException;

    public abstract void removeTestProvider(String s, String s1)
        throws RemoteException;

    public abstract void removeUpdates(ILocationListener ilocationlistener, PendingIntent pendingintent, String s)
        throws RemoteException;

    public abstract void reportLocation(Location location, boolean flag)
        throws RemoteException;

    public abstract void reportLocationBatch(List list)
        throws RemoteException;

    public abstract void requestGeofence(LocationRequest locationrequest, Geofence geofence, PendingIntent pendingintent, String s)
        throws RemoteException;

    public abstract void requestLocationUpdates(LocationRequest locationrequest, ILocationListener ilocationlistener, PendingIntent pendingintent, String s)
        throws RemoteException;

    public abstract boolean sendExtraCommand(String s, String s1, Bundle bundle)
        throws RemoteException;

    public abstract boolean sendNiResponse(int i, int j)
        throws RemoteException;

    public abstract void setTestProviderEnabled(String s, boolean flag, String s1)
        throws RemoteException;

    public abstract void setTestProviderLocation(String s, Location location, String s1)
        throws RemoteException;

    public abstract void setTestProviderStatus(String s, int i, Bundle bundle, long l, String s1)
        throws RemoteException;

    public abstract boolean startGnssBatch(long l, boolean flag, String s)
        throws RemoteException;

    public abstract boolean stopGnssBatch()
        throws RemoteException;

    public abstract void unregisterGnssStatusCallback(IGnssStatusListener ignssstatuslistener)
        throws RemoteException;
}
