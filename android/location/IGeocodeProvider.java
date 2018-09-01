// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.location:
//            GeocoderParams, Address

public interface IGeocodeProvider
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGeocodeProvider
    {

        public static IGeocodeProvider asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.location.IGeocodeProvider");
            if(iinterface != null && (iinterface instanceof IGeocodeProvider))
                return (IGeocodeProvider)iinterface;
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
            double d1;
            double d3;
            String s;
            double d4;
            double d5;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.location.IGeocodeProvider");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.location.IGeocodeProvider");
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

            case 2: // '\002'
                parcel.enforceInterface("android.location.IGeocodeProvider");
                s = parcel.readString();
                d3 = parcel.readDouble();
                d4 = parcel.readDouble();
                d5 = parcel.readDouble();
                d1 = parcel.readDouble();
                i = parcel.readInt();
                break;
            }
            ArrayList arraylist1;
            if(parcel.readInt() != 0)
                parcel = (GeocoderParams)GeocoderParams.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            arraylist1 = new ArrayList();
            parcel = getFromLocationName(s, d3, d4, d5, d1, i, parcel, arraylist1);
            parcel1.writeNoException();
            parcel1.writeString(parcel);
            parcel1.writeTypedList(arraylist1);
            return true;
        }

        private static final String DESCRIPTOR = "android.location.IGeocodeProvider";
        static final int TRANSACTION_getFromLocation = 1;
        static final int TRANSACTION_getFromLocationName = 2;

        public Stub()
        {
            attachInterface(this, "android.location.IGeocodeProvider");
        }
    }

    private static class Stub.Proxy
        implements IGeocodeProvider
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getFromLocation(double d, double d1, int i, GeocoderParams geocoderparams, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.IGeocodeProvider");
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            parcel.writeInt(i);
            if(geocoderparams == null)
                break MISSING_BLOCK_LABEL_106;
            parcel.writeInt(1);
            geocoderparams.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, parcel1, 0);
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
            parcel.writeInterfaceToken("android.location.IGeocodeProvider");
            parcel.writeString(s);
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            parcel.writeDouble(d2);
            parcel.writeDouble(d3);
            parcel.writeInt(i);
            if(geocoderparams == null)
                break MISSING_BLOCK_LABEL_125;
            parcel.writeInt(1);
            geocoderparams.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "android.location.IGeocodeProvider";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract String getFromLocation(double d, double d1, int i, GeocoderParams geocoderparams, List list)
        throws RemoteException;

    public abstract String getFromLocationName(String s, double d, double d1, double d2, 
            double d3, int i, GeocoderParams geocoderparams, List list)
        throws RemoteException;
}
