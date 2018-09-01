// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.metrics;

import android.os.Parcel;
import android.os.Parcelable;

public final class RaEvent
    implements Parcelable
{
    public static class Builder
    {

        private long updateLifetime(long l, long l1)
        {
            if(l == -1L)
                return l1;
            else
                return Math.min(l, l1);
        }

        public RaEvent build()
        {
            return new RaEvent(routerLifetime, prefixValidLifetime, prefixPreferredLifetime, routeInfoLifetime, rdnssLifetime, dnsslLifetime);
        }

        public Builder updateDnsslLifetime(long l)
        {
            dnsslLifetime = updateLifetime(dnsslLifetime, l);
            return this;
        }

        public Builder updatePrefixPreferredLifetime(long l)
        {
            prefixPreferredLifetime = updateLifetime(prefixPreferredLifetime, l);
            return this;
        }

        public Builder updatePrefixValidLifetime(long l)
        {
            prefixValidLifetime = updateLifetime(prefixValidLifetime, l);
            return this;
        }

        public Builder updateRdnssLifetime(long l)
        {
            rdnssLifetime = updateLifetime(rdnssLifetime, l);
            return this;
        }

        public Builder updateRouteInfoLifetime(long l)
        {
            routeInfoLifetime = updateLifetime(routeInfoLifetime, l);
            return this;
        }

        public Builder updateRouterLifetime(long l)
        {
            routerLifetime = updateLifetime(routerLifetime, l);
            return this;
        }

        long dnsslLifetime;
        long prefixPreferredLifetime;
        long prefixValidLifetime;
        long rdnssLifetime;
        long routeInfoLifetime;
        long routerLifetime;

        public Builder()
        {
            routerLifetime = -1L;
            prefixValidLifetime = -1L;
            prefixPreferredLifetime = -1L;
            routeInfoLifetime = -1L;
            rdnssLifetime = -1L;
            dnsslLifetime = -1L;
        }
    }


    public RaEvent(long l, long l1, long l2, long l3, long l4, long l5)
    {
        routerLifetime = l;
        prefixValidLifetime = l1;
        prefixPreferredLifetime = l2;
        routeInfoLifetime = l3;
        rdnssLifetime = l4;
        dnsslLifetime = l5;
    }

    private RaEvent(Parcel parcel)
    {
        routerLifetime = parcel.readLong();
        prefixValidLifetime = parcel.readLong();
        prefixPreferredLifetime = parcel.readLong();
        routeInfoLifetime = parcel.readLong();
        rdnssLifetime = parcel.readLong();
        dnsslLifetime = parcel.readLong();
    }

    RaEvent(Parcel parcel, RaEvent raevent)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return (new StringBuilder("RaEvent(lifetimes: ")).append(String.format("router=%ds, ", new Object[] {
            Long.valueOf(routerLifetime)
        })).append(String.format("prefix_valid=%ds, ", new Object[] {
            Long.valueOf(prefixValidLifetime)
        })).append(String.format("prefix_preferred=%ds, ", new Object[] {
            Long.valueOf(prefixPreferredLifetime)
        })).append(String.format("route_info=%ds, ", new Object[] {
            Long.valueOf(routeInfoLifetime)
        })).append(String.format("rdnss=%ds, ", new Object[] {
            Long.valueOf(rdnssLifetime)
        })).append(String.format("dnssl=%ds)", new Object[] {
            Long.valueOf(dnsslLifetime)
        })).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(routerLifetime);
        parcel.writeLong(prefixValidLifetime);
        parcel.writeLong(prefixPreferredLifetime);
        parcel.writeLong(routeInfoLifetime);
        parcel.writeLong(rdnssLifetime);
        parcel.writeLong(dnsslLifetime);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RaEvent createFromParcel(Parcel parcel)
        {
            return new RaEvent(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RaEvent[] newArray(int i)
        {
            return new RaEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final long NO_LIFETIME = -1L;
    public final long dnsslLifetime;
    public final long prefixPreferredLifetime;
    public final long prefixValidLifetime;
    public final long rdnssLifetime;
    public final long routeInfoLifetime;
    public final long routerLifetime;

}
