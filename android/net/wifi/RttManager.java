// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.content.Context;
import android.os.*;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.util.AsyncChannel;

// Referenced classes of package android.net.wifi:
//            IRttManager

public class RttManager
{
    public class Capabilities
    {

        public int supportedPeerType;
        public int supportedType;
        final RttManager this$0;

        public Capabilities()
        {
            this$0 = RttManager.this;
            super();
        }
    }

    public static class ParcelableRttParams
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mParams.length);
            RttParams arttparams[] = mParams;
            int j = arttparams.length;
            i = 0;
            while(i < j) 
            {
                RttParams rttparams = arttparams[i];
                parcel.writeInt(rttparams.deviceType);
                parcel.writeInt(rttparams.requestType);
                int k;
                byte byte0;
                if(rttparams.secure)
                {
                    boolean flag = true;
                    byte0 = flag;
                } else
                {
                    k = 0;
                    byte0 = k;
                }
                parcel.writeByte(byte0);
                parcel.writeString(rttparams.bssid);
                parcel.writeInt(rttparams.channelWidth);
                parcel.writeInt(rttparams.frequency);
                parcel.writeInt(rttparams.centerFreq0);
                parcel.writeInt(rttparams.centerFreq1);
                parcel.writeInt(rttparams.numberBurst);
                parcel.writeInt(rttparams.interval);
                parcel.writeInt(rttparams.numSamplesPerBurst);
                parcel.writeInt(rttparams.numRetriesPerMeasurementFrame);
                parcel.writeInt(rttparams.numRetriesPerFTMR);
                if(rttparams.LCIRequest)
                    k = 1;
                else
                    k = 0;
                parcel.writeInt(k);
                if(rttparams.LCRRequest)
                    k = 1;
                else
                    k = 0;
                parcel.writeInt(k);
                parcel.writeInt(rttparams.burstTimeout);
                parcel.writeInt(rttparams.preamble);
                parcel.writeInt(rttparams.bandwidth);
                i++;
            }
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ParcelableRttParams createFromParcel(Parcel parcel)
            {
                int i = parcel.readInt();
                RttParams arttparams[] = new RttParams[i];
                int j = 0;
                while(j < i) 
                {
                    arttparams[j] = new RttParams();
                    arttparams[j].deviceType = parcel.readInt();
                    arttparams[j].requestType = parcel.readInt();
                    RttParams rttparams = arttparams[j];
                    boolean flag;
                    if(parcel.readByte() != 0)
                        flag = true;
                    else
                        flag = false;
                    rttparams.secure = flag;
                    arttparams[j].bssid = parcel.readString();
                    arttparams[j].channelWidth = parcel.readInt();
                    arttparams[j].frequency = parcel.readInt();
                    arttparams[j].centerFreq0 = parcel.readInt();
                    arttparams[j].centerFreq1 = parcel.readInt();
                    arttparams[j].numberBurst = parcel.readInt();
                    arttparams[j].interval = parcel.readInt();
                    arttparams[j].numSamplesPerBurst = parcel.readInt();
                    arttparams[j].numRetriesPerMeasurementFrame = parcel.readInt();
                    arttparams[j].numRetriesPerFTMR = parcel.readInt();
                    rttparams = arttparams[j];
                    if(parcel.readInt() == 1)
                        flag = true;
                    else
                        flag = false;
                    rttparams.LCIRequest = flag;
                    rttparams = arttparams[j];
                    if(parcel.readInt() == 1)
                        flag = true;
                    else
                        flag = false;
                    rttparams.LCRRequest = flag;
                    arttparams[j].burstTimeout = parcel.readInt();
                    arttparams[j].preamble = parcel.readInt();
                    arttparams[j].bandwidth = parcel.readInt();
                    j++;
                }
                return new ParcelableRttParams(arttparams);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ParcelableRttParams[] newArray(int i)
            {
                return new ParcelableRttParams[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public RttParams mParams[];


        public ParcelableRttParams(RttParams arttparams[])
        {
            RttParams arttparams1[] = arttparams;
            if(arttparams == null)
                arttparams1 = new RttParams[0];
            mParams = arttparams1;
        }
    }

    public static class ParcelableRttResults
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            for(int i = 0; i < mResults.length; i++)
            {
                stringbuilder.append("[").append(i).append("]: ");
                stringbuilder.append("bssid=").append(mResults[i].bssid);
                stringbuilder.append(", burstNumber=").append(mResults[i].burstNumber);
                stringbuilder.append(", measurementFrameNumber=").append(mResults[i].measurementFrameNumber);
                stringbuilder.append(", successMeasurementFrameNumber=").append(mResults[i].successMeasurementFrameNumber);
                stringbuilder.append(", frameNumberPerBurstPeer=").append(mResults[i].frameNumberPerBurstPeer);
                stringbuilder.append(", status=").append(mResults[i].status);
                stringbuilder.append(", requestType=").append(mResults[i].requestType);
                stringbuilder.append(", measurementType=").append(mResults[i].measurementType);
                stringbuilder.append(", retryAfterDuration=").append(mResults[i].retryAfterDuration);
                stringbuilder.append(", ts=").append(mResults[i].ts);
                stringbuilder.append(", rssi=").append(mResults[i].rssi);
                stringbuilder.append(", rssi_spread=").append(mResults[i].rssi_spread);
                stringbuilder.append(", rssiSpread=").append(mResults[i].rssiSpread);
                stringbuilder.append(", tx_rate=").append(mResults[i].tx_rate);
                stringbuilder.append(", txRate=").append(mResults[i].txRate);
                stringbuilder.append(", rxRate=").append(mResults[i].rxRate);
                stringbuilder.append(", rtt_ns=").append(mResults[i].rtt_ns);
                stringbuilder.append(", rtt=").append(mResults[i].rtt);
                stringbuilder.append(", rtt_sd_ns=").append(mResults[i].rtt_sd_ns);
                stringbuilder.append(", rttStandardDeviation=").append(mResults[i].rttStandardDeviation);
                stringbuilder.append(", rtt_spread_ns=").append(mResults[i].rtt_spread_ns);
                stringbuilder.append(", rttSpread=").append(mResults[i].rttSpread);
                stringbuilder.append(", distance_cm=").append(mResults[i].distance_cm);
                stringbuilder.append(", distance=").append(mResults[i].distance);
                stringbuilder.append(", distance_sd_cm=").append(mResults[i].distance_sd_cm);
                stringbuilder.append(", distanceStandardDeviation=").append(mResults[i].distanceStandardDeviation);
                stringbuilder.append(", distance_spread_cm=").append(mResults[i].distance_spread_cm);
                stringbuilder.append(", distanceSpread=").append(mResults[i].distanceSpread);
                stringbuilder.append(", burstDuration=").append(mResults[i].burstDuration);
                stringbuilder.append(", negotiatedBurstNum=").append(mResults[i].negotiatedBurstNum);
                stringbuilder.append(", LCI=").append(mResults[i].LCI);
                stringbuilder.append(", LCR=").append(mResults[i].LCR);
                stringbuilder.append(", secure=").append(mResults[i].secure);
            }

            return stringbuilder.toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            if(mResults != null)
            {
                parcel.writeInt(mResults.length);
                RttResult arttresult[] = mResults;
                int j = arttresult.length;
                i = 0;
                while(i < j) 
                {
                    RttResult rttresult = arttresult[i];
                    parcel.writeString(rttresult.bssid);
                    parcel.writeInt(rttresult.burstNumber);
                    parcel.writeInt(rttresult.measurementFrameNumber);
                    parcel.writeInt(rttresult.successMeasurementFrameNumber);
                    parcel.writeInt(rttresult.frameNumberPerBurstPeer);
                    parcel.writeInt(rttresult.status);
                    parcel.writeInt(rttresult.measurementType);
                    parcel.writeInt(rttresult.retryAfterDuration);
                    parcel.writeLong(rttresult.ts);
                    parcel.writeInt(rttresult.rssi);
                    parcel.writeInt(rttresult.rssiSpread);
                    parcel.writeInt(rttresult.txRate);
                    parcel.writeLong(rttresult.rtt);
                    parcel.writeLong(rttresult.rttStandardDeviation);
                    parcel.writeLong(rttresult.rttSpread);
                    parcel.writeInt(rttresult.distance);
                    parcel.writeInt(rttresult.distanceStandardDeviation);
                    parcel.writeInt(rttresult.distanceSpread);
                    parcel.writeInt(rttresult.burstDuration);
                    parcel.writeInt(rttresult.negotiatedBurstNum);
                    parcel.writeByte(rttresult.LCI.id);
                    if(rttresult.LCI.id != -1)
                    {
                        parcel.writeByte((byte)rttresult.LCI.data.length);
                        parcel.writeByteArray(rttresult.LCI.data);
                    }
                    parcel.writeByte(rttresult.LCR.id);
                    if(rttresult.LCR.id != -1)
                    {
                        parcel.writeByte((byte)rttresult.LCR.data.length);
                        parcel.writeByteArray(rttresult.LCR.data);
                    }
                    byte byte0;
                    if(rttresult.secure)
                    {
                        boolean flag = true;
                        byte0 = flag;
                    } else
                    {
                        boolean flag1 = false;
                        byte0 = flag1;
                    }
                    parcel.writeByte(byte0);
                    i++;
                }
            } else
            {
                parcel.writeInt(0);
            }
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ParcelableRttResults createFromParcel(Parcel parcel)
            {
                int i = parcel.readInt();
                if(i == 0)
                    return new ParcelableRttResults(null);
                RttResult arttresult[] = new RttResult[i];
                int j = 0;
                while(j < i) 
                {
                    arttresult[j] = new RttResult();
                    arttresult[j].bssid = parcel.readString();
                    arttresult[j].burstNumber = parcel.readInt();
                    arttresult[j].measurementFrameNumber = parcel.readInt();
                    arttresult[j].successMeasurementFrameNumber = parcel.readInt();
                    arttresult[j].frameNumberPerBurstPeer = parcel.readInt();
                    arttresult[j].status = parcel.readInt();
                    arttresult[j].measurementType = parcel.readInt();
                    arttresult[j].retryAfterDuration = parcel.readInt();
                    arttresult[j].ts = parcel.readLong();
                    arttresult[j].rssi = parcel.readInt();
                    arttresult[j].rssiSpread = parcel.readInt();
                    arttresult[j].txRate = parcel.readInt();
                    arttresult[j].rtt = parcel.readLong();
                    arttresult[j].rttStandardDeviation = parcel.readLong();
                    arttresult[j].rttSpread = parcel.readLong();
                    arttresult[j].distance = parcel.readInt();
                    arttresult[j].distanceStandardDeviation = parcel.readInt();
                    arttresult[j].distanceSpread = parcel.readInt();
                    arttresult[j].burstDuration = parcel.readInt();
                    arttresult[j].negotiatedBurstNum = parcel.readInt();
                    arttresult[j].LCI = new WifiInformationElement();
                    arttresult[j].LCI.id = parcel.readByte();
                    if(arttresult[j].LCI.id != -1)
                    {
                        byte byte0 = parcel.readByte();
                        arttresult[j].LCI.data = new byte[byte0];
                        parcel.readByteArray(arttresult[j].LCI.data);
                    }
                    arttresult[j].LCR = new WifiInformationElement();
                    arttresult[j].LCR.id = parcel.readByte();
                    if(arttresult[j].LCR.id != -1)
                    {
                        byte byte1 = parcel.readByte();
                        arttresult[j].LCR.data = new byte[byte1];
                        parcel.readByteArray(arttresult[j].LCR.data);
                    }
                    RttResult rttresult = arttresult[j];
                    boolean flag;
                    if(parcel.readByte() != 0)
                        flag = true;
                    else
                        flag = false;
                    rttresult.secure = flag;
                    j++;
                }
                return new ParcelableRttResults(arttresult);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ParcelableRttResults[] newArray(int i)
            {
                return new ParcelableRttResults[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public RttResult mResults[];


        public ParcelableRttResults(RttResult arttresult[])
        {
            mResults = arttresult;
        }
    }

    public static abstract class ResponderCallback
    {

        public abstract void onResponderEnableFailure(int i);

        public abstract void onResponderEnabled(ResponderConfig responderconfig);

        public ResponderCallback()
        {
        }
    }

    public static class ResponderConfig
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("macAddress = ").append(macAddress).append(" frequency = ").append(frequency).append(" centerFreq0 = ").append(centerFreq0).append(" centerFreq1 = ").append(centerFreq1).append(" channelWidth = ").append(channelWidth).append(" preamble = ").append(preamble);
            return stringbuilder.toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(macAddress);
            parcel.writeInt(frequency);
            parcel.writeInt(centerFreq0);
            parcel.writeInt(centerFreq1);
            parcel.writeInt(channelWidth);
            parcel.writeInt(preamble);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ResponderConfig createFromParcel(Parcel parcel)
            {
                ResponderConfig responderconfig = new ResponderConfig();
                responderconfig.macAddress = parcel.readString();
                responderconfig.frequency = parcel.readInt();
                responderconfig.centerFreq0 = parcel.readInt();
                responderconfig.centerFreq1 = parcel.readInt();
                responderconfig.channelWidth = parcel.readInt();
                responderconfig.preamble = parcel.readInt();
                return responderconfig;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ResponderConfig[] newArray(int i)
            {
                return new ResponderConfig[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public int centerFreq0;
        public int centerFreq1;
        public int channelWidth;
        public int frequency;
        public String macAddress;
        public int preamble;


        public ResponderConfig()
        {
            macAddress = "";
        }
    }

    public static class RttCapabilities
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public String toString()
        {
            StringBuffer stringbuffer = new StringBuffer();
            StringBuffer stringbuffer1 = stringbuffer.append("oneSidedRtt ");
            String s;
            if(oneSidedRttSupported)
                s = "is Supported. ";
            else
                s = "is not supported. ";
            stringbuffer1 = stringbuffer1.append(s).append("twoSided11McRtt ");
            if(twoSided11McRttSupported)
                s = "is Supported. ";
            else
                s = "is not supported. ";
            stringbuffer1 = stringbuffer1.append(s).append("lci ");
            if(lciSupported)
                s = "is Supported. ";
            else
                s = "is not supported. ";
            stringbuffer1 = stringbuffer1.append(s).append("lcr ");
            if(lcrSupported)
                s = "is Supported. ";
            else
                s = "is not supported. ";
            stringbuffer1.append(s);
            if((preambleSupported & 1) != 0)
                stringbuffer.append("Legacy ");
            if((preambleSupported & 2) != 0)
                stringbuffer.append("HT ");
            if((preambleSupported & 4) != 0)
                stringbuffer.append("VHT ");
            stringbuffer.append("is supported. ");
            if((bwSupported & 1) != 0)
                stringbuffer.append("5 MHz ");
            if((bwSupported & 2) != 0)
                stringbuffer.append("10 MHz ");
            if((bwSupported & 4) != 0)
                stringbuffer.append("20 MHz ");
            if((bwSupported & 8) != 0)
                stringbuffer.append("40 MHz ");
            if((bwSupported & 0x10) != 0)
                stringbuffer.append("80 MHz ");
            if((bwSupported & 0x20) != 0)
                stringbuffer.append("160 MHz ");
            stringbuffer.append("is supported.");
            stringbuffer1 = stringbuffer.append(" STA responder role is ");
            if(responderSupported)
                s = "supported";
            else
                s = "not supported";
            stringbuffer1.append(s);
            stringbuffer1 = stringbuffer.append(" Secure RTT protocol is ");
            if(secureRttSupported)
                s = "supported";
            else
                s = "not supported";
            stringbuffer1.append(s);
            stringbuffer.append((new StringBuilder()).append(" 11mc version is ").append(mcVersion).toString());
            return stringbuffer.toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            boolean flag = true;
            if(oneSidedRttSupported)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(twoSided11McRttSupported)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(lciSupported)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(lcrSupported)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(preambleSupported);
            parcel.writeInt(bwSupported);
            if(responderSupported)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(secureRttSupported)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(mcVersion);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public RttCapabilities createFromParcel(Parcel parcel)
            {
                boolean flag = true;
                RttCapabilities rttcapabilities = new RttCapabilities();
                boolean flag1;
                if(parcel.readInt() == 1)
                    flag1 = true;
                else
                    flag1 = false;
                rttcapabilities.oneSidedRttSupported = flag1;
                if(parcel.readInt() == 1)
                    flag1 = true;
                else
                    flag1 = false;
                rttcapabilities.twoSided11McRttSupported = flag1;
                if(parcel.readInt() == 1)
                    flag1 = true;
                else
                    flag1 = false;
                rttcapabilities.lciSupported = flag1;
                if(parcel.readInt() == 1)
                    flag1 = true;
                else
                    flag1 = false;
                rttcapabilities.lcrSupported = flag1;
                rttcapabilities.preambleSupported = parcel.readInt();
                rttcapabilities.bwSupported = parcel.readInt();
                if(parcel.readInt() == 1)
                    flag1 = true;
                else
                    flag1 = false;
                rttcapabilities.responderSupported = flag1;
                if(parcel.readInt() == 1)
                    flag1 = flag;
                else
                    flag1 = false;
                rttcapabilities.secureRttSupported = flag1;
                rttcapabilities.mcVersion = parcel.readInt();
                return rttcapabilities;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public RttCapabilities[] newArray(int i)
            {
                return new RttCapabilities[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public int bwSupported;
        public boolean lciSupported;
        public boolean lcrSupported;
        public int mcVersion;
        public boolean oneSidedRttSupported;
        public int preambleSupported;
        public boolean responderSupported;
        public boolean secureRttSupported;
        public boolean supportedPeerType;
        public boolean supportedType;
        public boolean twoSided11McRttSupported;


        public RttCapabilities()
        {
        }
    }

    public static class RttClient
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public String getPackageName()
        {
            return mPackageName;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(mPackageName);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public RttClient createFromParcel(Parcel parcel)
            {
                return new RttClient(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public RttClient[] newArray(int i)
            {
                return new RttClient[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final String mPackageName;


        protected RttClient(Parcel parcel)
        {
            mPackageName = parcel.readString();
        }

        public RttClient(String s)
        {
            mPackageName = s;
        }
    }

    public static interface RttListener
    {

        public abstract void onAborted();

        public abstract void onFailure(int i, String s);

        public abstract void onSuccess(RttResult arttresult[]);
    }

    public static class RttParams
    {

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("deviceType=").append(deviceType);
            stringbuilder.append(", requestType=").append(requestType);
            stringbuilder.append(", secure=").append(secure);
            stringbuilder.append(", bssid=").append(bssid);
            stringbuilder.append(", frequency=").append(frequency);
            stringbuilder.append(", channelWidth=").append(channelWidth);
            stringbuilder.append(", centerFreq0=").append(centerFreq0);
            stringbuilder.append(", centerFreq1=").append(centerFreq1);
            stringbuilder.append(", num_samples=").append(num_samples);
            stringbuilder.append(", num_retries=").append(num_retries);
            stringbuilder.append(", numberBurst=").append(numberBurst);
            stringbuilder.append(", interval=").append(interval);
            stringbuilder.append(", numSamplesPerBurst=").append(numSamplesPerBurst);
            stringbuilder.append(", numRetriesPerMeasurementFrame=").append(numRetriesPerMeasurementFrame);
            stringbuilder.append(", numRetriesPerFTMR=").append(numRetriesPerFTMR);
            stringbuilder.append(", LCIRequest=").append(LCIRequest);
            stringbuilder.append(", LCRRequest=").append(LCRRequest);
            stringbuilder.append(", burstTimeout=").append(burstTimeout);
            stringbuilder.append(", preamble=").append(preamble);
            stringbuilder.append(", bandwidth=").append(bandwidth);
            return stringbuilder.toString();
        }

        public boolean LCIRequest;
        public boolean LCRRequest;
        public int bandwidth;
        public String bssid;
        public int burstTimeout;
        public int centerFreq0;
        public int centerFreq1;
        public int channelWidth;
        public int deviceType;
        public int frequency;
        public int interval;
        public int numRetriesPerFTMR;
        public int numRetriesPerMeasurementFrame;
        public int numSamplesPerBurst;
        public int num_retries;
        public int num_samples;
        public int numberBurst;
        public int preamble;
        public int requestType;
        public boolean secure;

        public RttParams()
        {
            deviceType = 1;
            requestType = 1;
            numberBurst = 0;
            numSamplesPerBurst = 8;
            numRetriesPerMeasurementFrame = 0;
            numRetriesPerFTMR = 0;
            burstTimeout = 15;
            preamble = 2;
            bandwidth = 4;
        }
    }

    public static class RttResult
    {

        public WifiInformationElement LCI;
        public WifiInformationElement LCR;
        public String bssid;
        public int burstDuration;
        public int burstNumber;
        public int distance;
        public int distanceSpread;
        public int distanceStandardDeviation;
        public int distance_cm;
        public int distance_sd_cm;
        public int distance_spread_cm;
        public int frameNumberPerBurstPeer;
        public int measurementFrameNumber;
        public int measurementType;
        public int negotiatedBurstNum;
        public int requestType;
        public int retryAfterDuration;
        public int rssi;
        public int rssiSpread;
        public int rssi_spread;
        public long rtt;
        public long rttSpread;
        public long rttStandardDeviation;
        public long rtt_ns;
        public long rtt_sd_ns;
        public long rtt_spread_ns;
        public int rxRate;
        public boolean secure;
        public int status;
        public int successMeasurementFrameNumber;
        public long ts;
        public int txRate;
        public int tx_rate;

        public RttResult()
        {
        }
    }

    private class ServiceHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            Object obj;
            Log.i("RttManager", (new StringBuilder()).append("RTT manager get message: ").append(message.what).toString());
            switch(message.what)
            {
            case 69635: 
            default:
                obj = RttManager._2D_wrap0(RttManager.this, message.arg2);
                if(obj == null)
                {
                    Log.e("RttManager", (new StringBuilder()).append("invalid listener key = ").append(message.arg2).toString());
                    return;
                }
                break;

            case 69634: 
                return;

            case 69636: 
                Log.e("RttManager", "Channel connection lost");
                RttManager._2D_set0(RttManager.this, null);
                getLooper().quit();
                return;
            }
            Log.i("RttManager", (new StringBuilder()).append("listener key = ").append(message.arg2).toString());
            message.what;
            JVM INSTR tableswitch 160258 160264: default 204
        //                       160258 224
        //                       160259 205
        //                       160260 245
        //                       160261 204
        //                       160262 204
        //                       160263 269
        //                       160264 288;
               goto _L1 _L2 _L3 _L4 _L1 _L1 _L5 _L6
_L1:
            return;
_L3:
            reportSuccess(obj, message);
            RttManager._2D_wrap1(RttManager.this, message.arg2);
_L8:
            return;
_L2:
            reportFailure(obj, message);
            RttManager._2D_wrap1(RttManager.this, message.arg2);
            continue; /* Loop/switch isn't completed */
_L4:
            ((RttListener)obj).onAborted();
            RttManager._2D_wrap1(RttManager.this, message.arg2);
            continue; /* Loop/switch isn't completed */
_L5:
            message = (ResponderConfig)message.obj;
            ((ResponderCallback)obj).onResponderEnabled(message);
            continue; /* Loop/switch isn't completed */
_L6:
            ((ResponderCallback)obj).onResponderEnableFailure(message.arg1);
            RttManager._2D_wrap1(RttManager.this, message.arg2);
            if(true) goto _L8; else goto _L7
_L7:
        }

        void reportFailure(Object obj, Message message)
        {
            Object obj1 = (RttListener)obj;
            obj1 = (Bundle)message.obj;
            ((RttListener)obj).onFailure(message.arg1, ((Bundle) (obj1)).getString("android.net.wifi.RttManager.Description"));
        }

        void reportSuccess(Object obj, Message message)
        {
            RttListener rttlistener = (RttListener)obj;
            message = (ParcelableRttResults)message.obj;
            ((RttListener)obj).onSuccess(((ParcelableRttResults) (message)).mResults);
        }

        final RttManager this$0;

        ServiceHandler(Looper looper)
        {
            this$0 = RttManager.this;
            super(looper);
        }
    }

    public static class WifiInformationElement
    {

        public byte data[];
        public byte id;

        public WifiInformationElement()
        {
        }
    }


    static AsyncChannel _2D_set0(RttManager rttmanager, AsyncChannel asyncchannel)
    {
        rttmanager.mAsyncChannel = asyncchannel;
        return asyncchannel;
    }

    static Object _2D_wrap0(RttManager rttmanager, int i)
    {
        return rttmanager.getListener(i);
    }

    static Object _2D_wrap1(RttManager rttmanager, int i)
    {
        return rttmanager.removeListener(i);
    }

    public RttManager(Context context, IRttManager irttmanager, Looper looper)
    {
        mListenerKey = 1;
        mContext = context;
        mService = irttmanager;
        irttmanager = new int[1];
        Object obj;
        try
        {
            obj = JVM INSTR new #210 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Log.d("RttManager", ((StringBuilder) (obj)).append("Get the messenger from ").append(mService).toString());
            IRttManager irttmanager1 = mService;
            obj = JVM INSTR new #232 <Class Binder>;
            ((Binder) (obj)).Binder();
            obj = irttmanager1.getMessenger(((android.os.IBinder) (obj)), irttmanager);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context.rethrowFromSystemServer();
        }
        if(obj == null)
        {
            throw new IllegalStateException("getMessenger() returned null!  This is invalid.");
        } else
        {
            mAsyncChannel = new AsyncChannel();
            looper = new ServiceHandler(looper);
            mAsyncChannel.connectSync(mContext, looper, ((android.os.Messenger) (obj)));
            mAsyncChannel.sendMessage(0x11001, new RttClient(context.getPackageName()));
            mAsyncChannel.sendMessage(0x27209, irttmanager[0]);
            return;
        }
    }

    private Object getListener(int i)
    {
        if(i == 0)
            return null;
        Object obj = mListenerMapLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = mListenerMap.get(i);
        obj;
        JVM INSTR monitorexit ;
        return obj1;
        Exception exception;
        exception;
        throw exception;
    }

    private int getListenerKey(Object obj)
    {
        if(obj == null)
            return 0;
        Object obj1 = mListenerMapLock;
        obj1;
        JVM INSTR monitorenter ;
        int i = mListenerMap.indexOfValue(obj);
        if(i != -1)
            break MISSING_BLOCK_LABEL_31;
        obj1;
        JVM INSTR monitorexit ;
        return 0;
        i = mListenerMap.keyAt(i);
        obj1;
        JVM INSTR monitorexit ;
        return i;
        obj;
        throw obj;
    }

    private int putListener(Object obj)
    {
        if(obj == null)
            return 0;
        Object obj1 = mListenerMapLock;
        obj1;
        JVM INSTR monitorenter ;
        int i;
        do
        {
            i = mListenerKey;
            mListenerKey = i + 1;
        } while(i == 0);
        mListenerMap.put(i, obj);
        obj1;
        JVM INSTR monitorexit ;
        return i;
        obj;
        throw obj;
    }

    private int putListenerIfAbsent(Object obj)
    {
        if(obj == null)
            return 0;
        Object obj1 = mListenerMapLock;
        obj1;
        JVM INSTR monitorenter ;
        int i = getListenerKey(obj);
        if(i == 0)
            break MISSING_BLOCK_LABEL_27;
        obj1;
        JVM INSTR monitorexit ;
        return i;
        do
        {
            i = mListenerKey;
            mListenerKey = i + 1;
        } while(i == 0);
        mListenerMap.put(i, obj);
        obj1;
        JVM INSTR monitorexit ;
        return i;
        obj;
        throw obj;
    }

    private int removeListener(Object obj)
    {
        int i;
        i = getListenerKey(obj);
        if(i == 0)
            return i;
        obj = mListenerMapLock;
        obj;
        JVM INSTR monitorenter ;
        mListenerMap.remove(i);
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    private Object removeListener(int i)
    {
        if(i == 0)
            return null;
        Object obj = mListenerMapLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        obj1 = mListenerMap.get(i);
        mListenerMap.remove(i);
        obj;
        JVM INSTR monitorexit ;
        return obj1;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean rttParamSanity(RttParams rttparams, int i)
    {
        if(mRttCapabilities == null && getRttCapabilities() == null)
        {
            Log.e("RttManager", "Can not get RTT capabilities");
            throw new IllegalStateException("RTT chip is not working");
        }
        if(rttparams.deviceType != 1)
            return false;
        if(rttparams.requestType != 1 && rttparams.requestType != 2)
        {
            Log.e("RttManager", (new StringBuilder()).append("Request ").append(i).append(": Illegal Request Type: ").append(rttparams.requestType).toString());
            return false;
        }
        if(rttparams.requestType == 1 && mRttCapabilities.oneSidedRttSupported ^ true)
        {
            Log.e("RttManager", (new StringBuilder()).append("Request ").append(i).append(": One side RTT is not supported").toString());
            return false;
        }
        if(rttparams.requestType == 2 && mRttCapabilities.twoSided11McRttSupported ^ true)
        {
            Log.e("RttManager", (new StringBuilder()).append("Request ").append(i).append(": two side RTT is not supported").toString());
            return false;
        }
        if(rttparams.bssid == null || rttparams.bssid.isEmpty())
        {
            Log.e("RttManager", "No BSSID in params");
            return false;
        }
        if(rttparams.numberBurst != 0)
        {
            Log.e("RttManager", (new StringBuilder()).append("Request ").append(i).append(": Illegal number of burst: ").append(rttparams.numberBurst).toString());
            return false;
        }
        if(rttparams.numSamplesPerBurst <= 0 || rttparams.numSamplesPerBurst > 31)
        {
            Log.e("RttManager", (new StringBuilder()).append("Request ").append(i).append(": Illegal sample number per burst: ").append(rttparams.numSamplesPerBurst).toString());
            return false;
        }
        if(rttparams.numRetriesPerMeasurementFrame < 0 || rttparams.numRetriesPerMeasurementFrame > 3)
        {
            Log.e("RttManager", (new StringBuilder()).append("Request ").append(i).append(": Illegal measurement frame retry number:").append(rttparams.numRetriesPerMeasurementFrame).toString());
            return false;
        }
        if(rttparams.numRetriesPerFTMR < 0 || rttparams.numRetriesPerFTMR > 3)
        {
            Log.e("RttManager", (new StringBuilder()).append("Request ").append(i).append(": Illegal FTMR frame retry number:").append(rttparams.numRetriesPerFTMR).toString());
            return false;
        }
        if(rttparams.LCIRequest && mRttCapabilities.lciSupported ^ true)
        {
            Log.e("RttManager", (new StringBuilder()).append("Request ").append(i).append(": LCI is not supported").toString());
            return false;
        }
        if(rttparams.LCRRequest && mRttCapabilities.lcrSupported ^ true)
        {
            Log.e("RttManager", (new StringBuilder()).append("Request ").append(i).append(": LCR is not supported").toString());
            return false;
        }
        if(rttparams.burstTimeout < 1 || rttparams.burstTimeout > 11 && rttparams.burstTimeout != 15)
        {
            Log.e("RttManager", (new StringBuilder()).append("Request ").append(i).append(": Illegal burst timeout: ").append(rttparams.burstTimeout).toString());
            return false;
        }
        if((rttparams.preamble & mRttCapabilities.preambleSupported) == 0)
        {
            Log.e("RttManager", (new StringBuilder()).append("Request ").append(i).append(": Do not support this preamble: ").append(rttparams.preamble).toString());
            return false;
        }
        if((rttparams.bandwidth & mRttCapabilities.bwSupported) == 0)
        {
            Log.e("RttManager", (new StringBuilder()).append("Request ").append(i).append(": Do not support this bandwidth: ").append(rttparams.bandwidth).toString());
            return false;
        } else
        {
            return true;
        }
    }

    private void validateChannel()
    {
        if(mAsyncChannel == null)
            throw new IllegalStateException("No permission to access and change wifi or a bad initialization");
        else
            return;
    }

    public void disableResponder(ResponderCallback respondercallback)
    {
        if(respondercallback == null)
            throw new IllegalArgumentException("callback cannot be null");
        validateChannel();
        int i = removeListener(respondercallback);
        if(i == 0)
        {
            Log.e("RttManager", "responder not enabled yet");
            return;
        } else
        {
            mAsyncChannel.sendMessage(0x27206, 0, i);
            return;
        }
    }

    public void enableResponder(ResponderCallback respondercallback)
    {
        if(respondercallback == null)
        {
            throw new IllegalArgumentException("callback cannot be null");
        } else
        {
            validateChannel();
            int i = putListenerIfAbsent(respondercallback);
            mAsyncChannel.sendMessage(0x27205, 0, i);
            return;
        }
    }

    public Capabilities getCapabilities()
    {
        return new Capabilities();
    }

    public RttCapabilities getRttCapabilities()
    {
        Object obj = mCapabilitiesLock;
        obj;
        JVM INSTR monitorenter ;
        RttCapabilities rttcapabilities = mRttCapabilities;
        if(rttcapabilities != null)
            break MISSING_BLOCK_LABEL_29;
        mRttCapabilities = mService.getRttCapabilities();
        rttcapabilities = mRttCapabilities;
        obj;
        JVM INSTR monitorexit ;
        return rttcapabilities;
        Object obj1;
        obj1;
        throw ((RemoteException) (obj1)).rethrowFromSystemServer();
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
    }

    public void startRanging(RttParams arttparams[], RttListener rttlistener)
    {
        int i = 0;
        int j = arttparams.length;
        for(int k = 0; k < j; k++)
        {
            if(!rttParamSanity(arttparams[k], i))
                throw new IllegalArgumentException("RTT Request Parameter Illegal");
            i++;
        }

        validateChannel();
        arttparams = new ParcelableRttParams(arttparams);
        Log.i("RttManager", "Send RTT request to RTT Service");
        mAsyncChannel.sendMessage(0x27200, 0, putListener(rttlistener), arttparams);
    }

    public void stopRanging(RttListener rttlistener)
    {
        validateChannel();
        mAsyncChannel.sendMessage(0x27201, 0, removeListener(rttlistener));
    }

    public static final int BASE = 0x27200;
    public static final int CMD_OP_ABORTED = 0x27204;
    public static final int CMD_OP_DISABLE_RESPONDER = 0x27206;
    public static final int CMD_OP_ENABLE_RESPONDER = 0x27205;
    public static final int CMD_OP_ENALBE_RESPONDER_FAILED = 0x27208;
    public static final int CMD_OP_ENALBE_RESPONDER_SUCCEEDED = 0x27207;
    public static final int CMD_OP_FAILED = 0x27202;
    public static final int CMD_OP_REG_BINDER = 0x27209;
    public static final int CMD_OP_START_RANGING = 0x27200;
    public static final int CMD_OP_STOP_RANGING = 0x27201;
    public static final int CMD_OP_SUCCEEDED = 0x27203;
    private static final boolean DBG = false;
    public static final String DESCRIPTION_KEY = "android.net.wifi.RttManager.Description";
    private static final int INVALID_KEY = 0;
    public static final int PREAMBLE_HT = 2;
    public static final int PREAMBLE_LEGACY = 1;
    public static final int PREAMBLE_VHT = 4;
    public static final int REASON_INITIATOR_NOT_ALLOWED_WHEN_RESPONDER_ON = -6;
    public static final int REASON_INVALID_LISTENER = -3;
    public static final int REASON_INVALID_REQUEST = -4;
    public static final int REASON_NOT_AVAILABLE = -2;
    public static final int REASON_PERMISSION_DENIED = -5;
    public static final int REASON_UNSPECIFIED = -1;
    public static final int RTT_BW_10_SUPPORT = 2;
    public static final int RTT_BW_160_SUPPORT = 32;
    public static final int RTT_BW_20_SUPPORT = 4;
    public static final int RTT_BW_40_SUPPORT = 8;
    public static final int RTT_BW_5_SUPPORT = 1;
    public static final int RTT_BW_80_SUPPORT = 16;
    public static final int RTT_CHANNEL_WIDTH_10 = 6;
    public static final int RTT_CHANNEL_WIDTH_160 = 3;
    public static final int RTT_CHANNEL_WIDTH_20 = 0;
    public static final int RTT_CHANNEL_WIDTH_40 = 1;
    public static final int RTT_CHANNEL_WIDTH_5 = 5;
    public static final int RTT_CHANNEL_WIDTH_80 = 2;
    public static final int RTT_CHANNEL_WIDTH_80P80 = 4;
    public static final int RTT_CHANNEL_WIDTH_UNSPECIFIED = -1;
    public static final int RTT_PEER_NAN = 5;
    public static final int RTT_PEER_P2P_CLIENT = 4;
    public static final int RTT_PEER_P2P_GO = 3;
    public static final int RTT_PEER_TYPE_AP = 1;
    public static final int RTT_PEER_TYPE_STA = 2;
    public static final int RTT_PEER_TYPE_UNSPECIFIED = 0;
    public static final int RTT_STATUS_ABORTED = 8;
    public static final int RTT_STATUS_FAILURE = 1;
    public static final int RTT_STATUS_FAIL_AP_ON_DIFF_CHANNEL = 6;
    public static final int RTT_STATUS_FAIL_BUSY_TRY_LATER = 12;
    public static final int RTT_STATUS_FAIL_FTM_PARAM_OVERRIDE = 15;
    public static final int RTT_STATUS_FAIL_INVALID_TS = 9;
    public static final int RTT_STATUS_FAIL_NOT_SCHEDULED_YET = 4;
    public static final int RTT_STATUS_FAIL_NO_CAPABILITY = 7;
    public static final int RTT_STATUS_FAIL_NO_RSP = 2;
    public static final int RTT_STATUS_FAIL_PROTOCOL = 10;
    public static final int RTT_STATUS_FAIL_REJECTED = 3;
    public static final int RTT_STATUS_FAIL_SCHEDULE = 11;
    public static final int RTT_STATUS_FAIL_TM_TIMEOUT = 5;
    public static final int RTT_STATUS_INVALID_REQ = 13;
    public static final int RTT_STATUS_NO_WIFI = 14;
    public static final int RTT_STATUS_SUCCESS = 0;
    public static final int RTT_TYPE_11_MC = 4;
    public static final int RTT_TYPE_11_V = 2;
    public static final int RTT_TYPE_ONE_SIDED = 1;
    public static final int RTT_TYPE_TWO_SIDED = 2;
    public static final int RTT_TYPE_UNSPECIFIED = 0;
    private static final String TAG = "RttManager";
    private AsyncChannel mAsyncChannel;
    private final Object mCapabilitiesLock = new Object();
    private final Context mContext;
    private int mListenerKey;
    private final SparseArray mListenerMap = new SparseArray();
    private final Object mListenerMapLock = new Object();
    private RttCapabilities mRttCapabilities;
    private final IRttManager mService;
}
