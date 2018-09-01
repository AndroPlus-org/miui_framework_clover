// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.content.Context;
import android.os.*;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.util.AsyncChannel;
import com.android.internal.util.Preconditions;
import java.util.*;

// Referenced classes of package android.net.wifi:
//            IWifiScanner, ScanResult

public class WifiScanner
{
    public static interface ActionListener
    {

        public abstract void onFailure(int i, String s);

        public abstract void onSuccess();
    }

    public static class BssidInfo
    {

        public String bssid;
        public int frequencyHint;
        public int high;
        public int low;

        public BssidInfo()
        {
        }
    }

    public static interface BssidListener
        extends ActionListener
    {

        public abstract void onFound(ScanResult ascanresult[]);

        public abstract void onLost(ScanResult ascanresult[]);
    }

    public static class ChannelSpec
    {

        public int dwellTimeMS;
        public int frequency;
        public boolean passive;

        public ChannelSpec(int i)
        {
            frequency = i;
            passive = false;
            dwellTimeMS = 0;
        }
    }

    public static class HotlistSettings
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public HotlistSettings createFromParcel(Parcel parcel)
            {
                return new HotlistSettings();
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public HotlistSettings[] newArray(int i)
            {
                return new HotlistSettings[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public int apLostThreshold;
        public BssidInfo bssidInfos[];


        public HotlistSettings()
        {
        }
    }

    public static class OperationResult
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(reason);
            parcel.writeString(description);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public OperationResult createFromParcel(Parcel parcel)
            {
                return new OperationResult(parcel.readInt(), parcel.readString());
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public OperationResult[] newArray(int i)
            {
                return new OperationResult[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public String description;
        public int reason;


        public OperationResult(int i, String s)
        {
            reason = i;
            description = s;
        }
    }

    public static class ParcelableScanData
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public ScanData[] getResults()
        {
            return mResults;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            if(mResults != null)
            {
                parcel.writeInt(mResults.length);
                for(int j = 0; j < mResults.length; j++)
                    mResults[j].writeToParcel(parcel, i);

            } else
            {
                parcel.writeInt(0);
            }
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ParcelableScanData createFromParcel(Parcel parcel)
            {
                int i = parcel.readInt();
                ScanData ascandata[] = new ScanData[i];
                for(int j = 0; j < i; j++)
                    ascandata[j] = (ScanData)ScanData.CREATOR.createFromParcel(parcel);

                return new ParcelableScanData(ascandata);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ParcelableScanData[] newArray(int i)
            {
                return new ParcelableScanData[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public ScanData mResults[];


        public ParcelableScanData(ScanData ascandata[])
        {
            mResults = ascandata;
        }
    }

    public static class ParcelableScanResults
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public ScanResult[] getResults()
        {
            return mResults;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            if(mResults != null)
            {
                parcel.writeInt(mResults.length);
                for(int j = 0; j < mResults.length; j++)
                    mResults[j].writeToParcel(parcel, i);

            } else
            {
                parcel.writeInt(0);
            }
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ParcelableScanResults createFromParcel(Parcel parcel)
            {
                int i = parcel.readInt();
                ScanResult ascanresult[] = new ScanResult[i];
                for(int j = 0; j < i; j++)
                    ascanresult[j] = (ScanResult)ScanResult.CREATOR.createFromParcel(parcel);

                return new ParcelableScanResults(ascanresult);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ParcelableScanResults[] newArray(int i)
            {
                return new ParcelableScanResults[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public ScanResult mResults[];


        public ParcelableScanResults(ScanResult ascanresult[])
        {
            mResults = ascanresult;
        }
    }

    public static interface PnoScanListener
        extends ScanListener
    {

        public abstract void onPnoNetworkFound(ScanResult ascanresult[]);
    }

    public static class PnoSettings
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            if(isConnected)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(min5GHzRssi);
            parcel.writeInt(min24GHzRssi);
            parcel.writeInt(initialScoreMax);
            parcel.writeInt(currentConnectionBonus);
            parcel.writeInt(sameNetworkBonus);
            parcel.writeInt(secureBonus);
            parcel.writeInt(band5GHzBonus);
            if(networkList != null)
            {
                parcel.writeInt(networkList.length);
                for(i = 0; i < networkList.length; i++)
                {
                    parcel.writeString(networkList[i].ssid);
                    parcel.writeByte(networkList[i].flags);
                    parcel.writeByte(networkList[i].authBitField);
                }

            } else
            {
                parcel.writeInt(0);
            }
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public PnoSettings createFromParcel(Parcel parcel)
            {
                boolean flag = true;
                PnoSettings pnosettings = new PnoSettings();
                int i;
                if(parcel.readInt() != 1)
                    flag = false;
                pnosettings.isConnected = flag;
                pnosettings.min5GHzRssi = parcel.readInt();
                pnosettings.min24GHzRssi = parcel.readInt();
                pnosettings.initialScoreMax = parcel.readInt();
                pnosettings.currentConnectionBonus = parcel.readInt();
                pnosettings.sameNetworkBonus = parcel.readInt();
                pnosettings.secureBonus = parcel.readInt();
                pnosettings.band5GHzBonus = parcel.readInt();
                i = parcel.readInt();
                pnosettings.networkList = new PnoSettings.PnoNetwork[i];
                for(int j = 0; j < i; j++)
                {
                    PnoSettings.PnoNetwork pnonetwork = new PnoSettings.PnoNetwork(parcel.readString());
                    pnonetwork.flags = parcel.readByte();
                    pnonetwork.authBitField = parcel.readByte();
                    pnosettings.networkList[j] = pnonetwork;
                }

                return pnosettings;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public PnoSettings[] newArray(int i)
            {
                return new PnoSettings[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public int band5GHzBonus;
        public int currentConnectionBonus;
        public int initialScoreMax;
        public boolean isConnected;
        public int min24GHzRssi;
        public int min5GHzRssi;
        public PnoNetwork networkList[];
        public int sameNetworkBonus;
        public int secureBonus;


        public PnoSettings()
        {
        }
    }

    public static class PnoSettings.PnoNetwork
    {

        public static final byte AUTH_CODE_EAPOL = 4;
        public static final byte AUTH_CODE_OPEN = 1;
        public static final byte AUTH_CODE_PSK = 2;
        public static final byte FLAG_A_BAND = 2;
        public static final byte FLAG_DIRECTED_SCAN = 1;
        public static final byte FLAG_G_BAND = 4;
        public static final byte FLAG_SAME_NETWORK = 16;
        public static final byte FLAG_STRICT_MATCH = 8;
        public byte authBitField;
        public byte flags;
        public String ssid;

        public PnoSettings.PnoNetwork(String s)
        {
            ssid = s;
            flags = (byte)0;
            authBitField = (byte)0;
        }
    }

    public static class ScanData
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public int getBucketsScanned()
        {
            return mBucketsScanned;
        }

        public int getFlags()
        {
            return mFlags;
        }

        public int getId()
        {
            return mId;
        }

        public ScanResult[] getResults()
        {
            return mResults;
        }

        public boolean isAllChannelsScanned()
        {
            return mAllChannelsScanned;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            int j = 0;
            if(mResults != null)
            {
                parcel.writeInt(mId);
                parcel.writeInt(mFlags);
                parcel.writeInt(mBucketsScanned);
                if(mAllChannelsScanned)
                    j = 1;
                parcel.writeInt(j);
                parcel.writeInt(mResults.length);
                for(int k = 0; k < mResults.length; k++)
                    mResults[k].writeToParcel(parcel, i);

            } else
            {
                parcel.writeInt(0);
            }
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ScanData createFromParcel(Parcel parcel)
            {
                int i = parcel.readInt();
                int j = parcel.readInt();
                int k = parcel.readInt();
                boolean flag;
                int l;
                ScanResult ascanresult[];
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                l = parcel.readInt();
                ascanresult = new ScanResult[l];
                for(int i1 = 0; i1 < l; i1++)
                    ascanresult[i1] = (ScanResult)ScanResult.CREATOR.createFromParcel(parcel);

                return new ScanData(i, j, k, flag, ascanresult);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ScanData[] newArray(int i)
            {
                return new ScanData[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private boolean mAllChannelsScanned;
        private int mBucketsScanned;
        private int mFlags;
        private int mId;
        private ScanResult mResults[];


        ScanData()
        {
        }

        public ScanData(int i, int j, int k, boolean flag, ScanResult ascanresult[])
        {
            mId = i;
            mFlags = j;
            mBucketsScanned = k;
            mAllChannelsScanned = flag;
            mResults = ascanresult;
        }

        public ScanData(int i, int j, ScanResult ascanresult[])
        {
            mId = i;
            mFlags = j;
            mResults = ascanresult;
        }

        public ScanData(ScanData scandata)
        {
            mId = scandata.mId;
            mFlags = scandata.mFlags;
            mBucketsScanned = scandata.mBucketsScanned;
            mAllChannelsScanned = scandata.mAllChannelsScanned;
            mResults = new ScanResult[scandata.mResults.length];
            for(int i = 0; i < scandata.mResults.length; i++)
            {
                ScanResult scanresult = new ScanResult(scandata.mResults[i]);
                mResults[i] = scanresult;
            }

        }
    }

    public static interface ScanListener
        extends ActionListener
    {

        public abstract void onFullResult(ScanResult scanresult);

        public abstract void onPeriodChanged(int i);

        public abstract void onResults(ScanData ascandata[]);
    }

    public static class ScanSettings
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(band);
            parcel.writeInt(periodInMs);
            parcel.writeInt(reportEvents);
            parcel.writeInt(numBssidsPerScan);
            parcel.writeInt(maxScansToCache);
            parcel.writeInt(maxPeriodInMs);
            parcel.writeInt(stepCount);
            if(isPnoScan)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(channels != null)
            {
                parcel.writeInt(channels.length);
                i = 0;
                while(i < channels.length) 
                {
                    parcel.writeInt(channels[i].frequency);
                    parcel.writeInt(channels[i].dwellTimeMS);
                    int j;
                    if(channels[i].passive)
                        j = 1;
                    else
                        j = 0;
                    parcel.writeInt(j);
                    i++;
                }
            } else
            {
                parcel.writeInt(0);
            }
            if(hiddenNetworks != null)
            {
                parcel.writeInt(hiddenNetworks.length);
                for(i = 0; i < hiddenNetworks.length; i++)
                    parcel.writeString(hiddenNetworks[i].ssid);

            } else
            {
                parcel.writeInt(0);
            }
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ScanSettings createFromParcel(Parcel parcel)
            {
                ScanSettings scansettings = new ScanSettings();
                scansettings.band = parcel.readInt();
                scansettings.periodInMs = parcel.readInt();
                scansettings.reportEvents = parcel.readInt();
                scansettings.numBssidsPerScan = parcel.readInt();
                scansettings.maxScansToCache = parcel.readInt();
                scansettings.maxPeriodInMs = parcel.readInt();
                scansettings.stepCount = parcel.readInt();
                boolean flag;
                int i;
                int j;
                if(parcel.readInt() == 1)
                    flag = true;
                else
                    flag = false;
                scansettings.isPnoScan = flag;
                i = parcel.readInt();
                scansettings.channels = new ChannelSpec[i];
                j = 0;
                while(j < i) 
                {
                    ChannelSpec channelspec = new ChannelSpec(parcel.readInt());
                    channelspec.dwellTimeMS = parcel.readInt();
                    if(parcel.readInt() == 1)
                        flag = true;
                    else
                        flag = false;
                    channelspec.passive = flag;
                    scansettings.channels[j] = channelspec;
                    j++;
                }
                i = parcel.readInt();
                scansettings.hiddenNetworks = new ScanSettings.HiddenNetwork[i];
                for(int k = 0; k < i; k++)
                {
                    String s = parcel.readString();
                    scansettings.hiddenNetworks[k] = new ScanSettings.HiddenNetwork(s);
                }

                return scansettings;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ScanSettings[] newArray(int i)
            {
                return new ScanSettings[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public int band;
        public ChannelSpec channels[];
        public HiddenNetwork hiddenNetworks[];
        public boolean isPnoScan;
        public int maxPeriodInMs;
        public int maxScansToCache;
        public int numBssidsPerScan;
        public int periodInMs;
        public int reportEvents;
        public int stepCount;


        public ScanSettings()
        {
        }
    }

    public static class ScanSettings.HiddenNetwork
    {

        public String ssid;

        public ScanSettings.HiddenNetwork(String s)
        {
            ssid = s;
        }
    }

    private class ServiceHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            Object obj;
            switch(message.what)
            {
            case 69635: 
            default:
                obj = WifiScanner._2D_wrap0(WifiScanner.this, message.arg2);
                if(obj == null)
                    return;
                break;

            case 69634: 
                return;

            case 69636: 
                Log.e("WifiScanner", "Channel connection lost");
                WifiScanner._2D_set0(WifiScanner.this, null);
                getLooper().quit();
                return;
            }
            message.what;
            JVM INSTR tableswitch 159749 159770: default 180
        //                       159749 231
        //                       159750 180
        //                       159751 180
        //                       159752 180
        //                       159753 284
        //                       159754 304
        //                       159755 180
        //                       159756 180
        //                       159757 180
        //                       159758 180
        //                       159759 324
        //                       159760 344
        //                       159761 181
        //                       159762 191
        //                       159763 270
        //                       159764 251
        //                       159765 180
        //                       159766 180
        //                       159767 364
        //                       159768 180
        //                       159769 180
        //                       159770 379;
               goto _L1 _L2 _L1 _L1 _L1 _L3 _L4 _L1 _L1 _L1 _L1 _L5 _L6 _L7 _L8 _L9 _L10 _L1 _L1 _L11 _L1 _L1 _L12
_L1:
            return;
_L7:
            ((ActionListener)obj).onSuccess();
_L13:
            return;
_L8:
            OperationResult operationresult = (OperationResult)message.obj;
            ((ActionListener)obj).onFailure(operationresult.reason, operationresult.description);
            WifiScanner._2D_wrap1(WifiScanner.this, message.arg2);
            continue; /* Loop/switch isn't completed */
_L2:
            ((ScanListener)obj).onResults(((ParcelableScanData)message.obj).getResults());
            return;
_L10:
            message = (ScanResult)message.obj;
            ((ScanListener)obj).onFullResult(message);
            return;
_L9:
            ((ScanListener)obj).onPeriodChanged(message.arg1);
            return;
_L3:
            ((BssidListener)obj).onFound(((ParcelableScanResults)message.obj).getResults());
            return;
_L4:
            ((BssidListener)obj).onLost(((ParcelableScanResults)message.obj).getResults());
            return;
_L5:
            ((WifiChangeListener)obj).onChanging(((ParcelableScanResults)message.obj).getResults());
            return;
_L6:
            ((WifiChangeListener)obj).onQuiescence(((ParcelableScanResults)message.obj).getResults());
            return;
_L11:
            WifiScanner._2D_wrap1(WifiScanner.this, message.arg2);
            if(true) goto _L13; else goto _L12
_L12:
            ((PnoScanListener)obj).onPnoNetworkFound(((ParcelableScanResults)message.obj).getResults());
            return;
        }

        final WifiScanner this$0;

        ServiceHandler(Looper looper)
        {
            this$0 = WifiScanner.this;
            super(looper);
        }
    }

    public static interface WifiChangeListener
        extends ActionListener
    {

        public abstract void onChanging(ScanResult ascanresult[]);

        public abstract void onQuiescence(ScanResult ascanresult[]);
    }

    public static class WifiChangeSettings
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public WifiChangeSettings createFromParcel(Parcel parcel)
            {
                return new WifiChangeSettings();
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public WifiChangeSettings[] newArray(int i)
            {
                return new WifiChangeSettings[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public BssidInfo bssidInfos[];
        public int lostApSampleSize;
        public int minApsBreachingThreshold;
        public int periodInMs;
        public int rssiSampleSize;
        public int unchangedSampleSize;


        public WifiChangeSettings()
        {
        }
    }


    static AsyncChannel _2D_set0(WifiScanner wifiscanner, AsyncChannel asyncchannel)
    {
        wifiscanner.mAsyncChannel = asyncchannel;
        return asyncchannel;
    }

    static Object _2D_wrap0(WifiScanner wifiscanner, int i)
    {
        return wifiscanner.getListener(i);
    }

    static Object _2D_wrap1(WifiScanner wifiscanner, int i)
    {
        return wifiscanner.removeListener(i);
    }

    public WifiScanner(Context context, IWifiScanner iwifiscanner, Looper looper)
    {
        mListenerKey = 1;
        mContext = context;
        mService = iwifiscanner;
        try
        {
            context = mService.getMessenger();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context.rethrowFromSystemServer();
        }
        if(context == null)
        {
            throw new IllegalStateException("getMessenger() returned null!  This is invalid.");
        } else
        {
            mAsyncChannel = new AsyncChannel();
            mInternalHandler = new ServiceHandler(looper);
            mAsyncChannel.connectSync(mContext, mInternalHandler, context);
            mAsyncChannel.sendMessage(0x11001);
            return;
        }
    }

    private int addListener(ActionListener actionlistener)
    {
        Object obj = mListenerMapLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        int i;
        if(getListenerKey(actionlistener) != 0)
            flag = true;
        else
            flag = false;
        i = putListener(actionlistener);
        if(!flag)
            break MISSING_BLOCK_LABEL_66;
        actionlistener = JVM INSTR new #23  <Class WifiScanner$OperationResult>;
        actionlistener.OperationResult(-5, "Outstanding request with same key not stopped yet");
        Message.obtain(mInternalHandler, 0x27012, 0, i, actionlistener).sendToTarget();
        obj;
        JVM INSTR monitorexit ;
        return 0;
        obj;
        JVM INSTR monitorexit ;
        return i;
        actionlistener;
        throw actionlistener;
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

    private int removeListener(Object obj)
    {
        int i;
        i = getListenerKey(obj);
        if(i == 0)
        {
            Log.e("WifiScanner", "listener cannot be found");
            return i;
        }
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

    private void startPnoScan(ScanSettings scansettings, PnoSettings pnosettings, int i)
    {
        Bundle bundle = new Bundle();
        scansettings.isPnoScan = true;
        bundle.putParcelable("ScanSettings", scansettings);
        bundle.putParcelable("PnoSettings", pnosettings);
        mAsyncChannel.sendMessage(0x27018, 0, i, bundle);
    }

    private void validateChannel()
    {
        if(mAsyncChannel == null)
            throw new IllegalStateException("No permission to access and change wifi or a bad initialization");
        else
            return;
    }

    public void configureWifiChange(int i, int j, int k, int l, int i1, BssidInfo abssidinfo[])
    {
        throw new UnsupportedOperationException();
    }

    public void configureWifiChange(WifiChangeSettings wifichangesettings)
    {
        throw new UnsupportedOperationException();
    }

    public void deregisterScanListener(ScanListener scanlistener)
    {
        Preconditions.checkNotNull(scanlistener, "listener cannot be null");
        int i = removeListener(scanlistener);
        if(i == 0)
        {
            return;
        } else
        {
            validateChannel();
            mAsyncChannel.sendMessage(0x2701c, 0, i);
            return;
        }
    }

    public List getAvailableChannels(int i)
    {
        ArrayList arraylist;
        try
        {
            arraylist = mService.getAvailableChannels(i).getIntegerArrayList("Channels");
        }
        catch(RemoteException remoteexception)
        {
            return null;
        }
        return arraylist;
    }

    public boolean getScanResults()
    {
        boolean flag = false;
        validateChannel();
        if(mAsyncChannel.sendMessageSynchronously(0x27004, 0).what == 0x27011)
            flag = true;
        return flag;
    }

    public List getSingleScanResults()
    {
        validateChannel();
        Object obj = mAsyncChannel.sendMessageSynchronously(0x2701d, 0);
        if(((Message) (obj)).what == 0x27011)
        {
            return Arrays.asList(((ParcelableScanResults)((Message) (obj)).obj).getResults());
        } else
        {
            obj = (OperationResult)((Message) (obj)).obj;
            Log.e("WifiScanner", (new StringBuilder()).append("Error retrieving SingleScan results reason: ").append(((OperationResult) (obj)).reason).append(" description: ").append(((OperationResult) (obj)).description).toString());
            return new ArrayList();
        }
    }

    public void registerScanListener(ScanListener scanlistener)
    {
        Preconditions.checkNotNull(scanlistener, "listener cannot be null");
        int i = addListener(scanlistener);
        if(i == 0)
        {
            return;
        } else
        {
            validateChannel();
            mAsyncChannel.sendMessage(0x2701b, 0, i);
            return;
        }
    }

    public void startBackgroundScan(ScanSettings scansettings, ScanListener scanlistener)
    {
        startBackgroundScan(scansettings, scanlistener, null);
    }

    public void startBackgroundScan(ScanSettings scansettings, ScanListener scanlistener, WorkSource worksource)
    {
        Preconditions.checkNotNull(scanlistener, "listener cannot be null");
        int i = addListener(scanlistener);
        if(i == 0)
        {
            return;
        } else
        {
            validateChannel();
            scanlistener = new Bundle();
            scanlistener.putParcelable("ScanSettings", scansettings);
            scanlistener.putParcelable("WorkSource", worksource);
            mAsyncChannel.sendMessage(0x27002, 0, i, scanlistener);
            return;
        }
    }

    public void startConnectedPnoScan(ScanSettings scansettings, PnoSettings pnosettings, PnoScanListener pnoscanlistener)
    {
        Preconditions.checkNotNull(pnoscanlistener, "listener cannot be null");
        Preconditions.checkNotNull(pnosettings, "pnoSettings cannot be null");
        int i = addListener(pnoscanlistener);
        if(i == 0)
        {
            return;
        } else
        {
            validateChannel();
            pnosettings.isConnected = true;
            startPnoScan(scansettings, pnosettings, i);
            return;
        }
    }

    public void startDisconnectedPnoScan(ScanSettings scansettings, PnoSettings pnosettings, PnoScanListener pnoscanlistener)
    {
        Preconditions.checkNotNull(pnoscanlistener, "listener cannot be null");
        Preconditions.checkNotNull(pnosettings, "pnoSettings cannot be null");
        int i = addListener(pnoscanlistener);
        if(i == 0)
        {
            return;
        } else
        {
            validateChannel();
            pnosettings.isConnected = false;
            startPnoScan(scansettings, pnosettings, i);
            return;
        }
    }

    public void startScan(ScanSettings scansettings, ScanListener scanlistener)
    {
        startScan(scansettings, scanlistener, null);
    }

    public void startScan(ScanSettings scansettings, ScanListener scanlistener, WorkSource worksource)
    {
        Preconditions.checkNotNull(scanlistener, "listener cannot be null");
        int i = addListener(scanlistener);
        if(i == 0)
        {
            return;
        } else
        {
            validateChannel();
            scanlistener = new Bundle();
            scanlistener.putParcelable("ScanSettings", scansettings);
            scanlistener.putParcelable("WorkSource", worksource);
            mAsyncChannel.sendMessage(0x27015, 0, i, scanlistener);
            return;
        }
    }

    public void startTrackingBssids(BssidInfo abssidinfo[], int i, BssidListener bssidlistener)
    {
        throw new UnsupportedOperationException();
    }

    public void startTrackingWifiChange(WifiChangeListener wifichangelistener)
    {
        throw new UnsupportedOperationException();
    }

    public void stopBackgroundScan(ScanListener scanlistener)
    {
        Preconditions.checkNotNull(scanlistener, "listener cannot be null");
        int i = removeListener(scanlistener);
        if(i == 0)
        {
            return;
        } else
        {
            validateChannel();
            mAsyncChannel.sendMessage(0x27003, 0, i);
            return;
        }
    }

    public void stopPnoScan(ScanListener scanlistener)
    {
        Preconditions.checkNotNull(scanlistener, "listener cannot be null");
        int i = removeListener(scanlistener);
        if(i == 0)
        {
            return;
        } else
        {
            validateChannel();
            mAsyncChannel.sendMessage(0x27019, 0, i);
            return;
        }
    }

    public void stopScan(ScanListener scanlistener)
    {
        Preconditions.checkNotNull(scanlistener, "listener cannot be null");
        int i = removeListener(scanlistener);
        if(i == 0)
        {
            return;
        } else
        {
            validateChannel();
            mAsyncChannel.sendMessage(0x27016, 0, i);
            return;
        }
    }

    public void stopTrackingBssids(BssidListener bssidlistener)
    {
        throw new UnsupportedOperationException();
    }

    public void stopTrackingWifiChange(WifiChangeListener wifichangelistener)
    {
        throw new UnsupportedOperationException();
    }

    private static final int BASE = 0x27000;
    public static final int CMD_AP_FOUND = 0x27009;
    public static final int CMD_AP_LOST = 0x2700a;
    public static final int CMD_DEREGISTER_SCAN_LISTENER = 0x2701c;
    public static final int CMD_FULL_SCAN_RESULT = 0x27014;
    public static final int CMD_GET_SCAN_RESULTS = 0x27004;
    public static final int CMD_GET_SINGLE_SCAN_RESULTS = 0x2701d;
    public static final int CMD_OP_FAILED = 0x27012;
    public static final int CMD_OP_SUCCEEDED = 0x27011;
    public static final int CMD_PERIOD_CHANGED = 0x27013;
    public static final int CMD_PNO_NETWORK_FOUND = 0x2701a;
    public static final int CMD_REGISTER_SCAN_LISTENER = 0x2701b;
    public static final int CMD_SCAN = 0x27000;
    public static final int CMD_SCAN_RESULT = 0x27005;
    public static final int CMD_SINGLE_SCAN_COMPLETED = 0x27017;
    public static final int CMD_START_BACKGROUND_SCAN = 0x27002;
    public static final int CMD_START_PNO_SCAN = 0x27018;
    public static final int CMD_START_SINGLE_SCAN = 0x27015;
    public static final int CMD_STOP_BACKGROUND_SCAN = 0x27003;
    public static final int CMD_STOP_PNO_SCAN = 0x27019;
    public static final int CMD_STOP_SINGLE_SCAN = 0x27016;
    public static final int CMD_WIFI_CHANGES_STABILIZED = 0x27010;
    public static final int CMD_WIFI_CHANGE_DETECTED = 0x2700f;
    private static final boolean DBG = false;
    public static final String GET_AVAILABLE_CHANNELS_EXTRA = "Channels";
    private static final int INVALID_KEY = 0;
    public static final int MAX_SCAN_PERIOD_MS = 0xfa000;
    public static final int MIN_SCAN_PERIOD_MS = 1000;
    public static final String PNO_PARAMS_PNO_SETTINGS_KEY = "PnoSettings";
    public static final String PNO_PARAMS_SCAN_SETTINGS_KEY = "ScanSettings";
    public static final int REASON_DUPLICATE_REQEUST = -5;
    public static final int REASON_INVALID_LISTENER = -2;
    public static final int REASON_INVALID_REQUEST = -3;
    public static final int REASON_NOT_AUTHORIZED = -4;
    public static final int REASON_SUCCEEDED = 0;
    public static final int REASON_UNSPECIFIED = -1;
    public static final int REPORT_EVENT_AFTER_BUFFER_FULL = 0;
    public static final int REPORT_EVENT_AFTER_EACH_SCAN = 1;
    public static final int REPORT_EVENT_FULL_SCAN_RESULT = 2;
    public static final int REPORT_EVENT_NO_BATCH = 4;
    public static final String SCAN_PARAMS_SCAN_SETTINGS_KEY = "ScanSettings";
    public static final String SCAN_PARAMS_WORK_SOURCE_KEY = "WorkSource";
    private static final String TAG = "WifiScanner";
    public static final int WIFI_BAND_24_GHZ = 1;
    public static final int WIFI_BAND_5_GHZ = 2;
    public static final int WIFI_BAND_5_GHZ_DFS_ONLY = 4;
    public static final int WIFI_BAND_5_GHZ_WITH_DFS = 6;
    public static final int WIFI_BAND_BOTH = 3;
    public static final int WIFI_BAND_BOTH_WITH_DFS = 7;
    public static final int WIFI_BAND_UNSPECIFIED = 0;
    private AsyncChannel mAsyncChannel;
    private Context mContext;
    private final Handler mInternalHandler;
    private int mListenerKey;
    private final SparseArray mListenerMap = new SparseArray();
    private final Object mListenerMapLock = new Object();
    private IWifiScanner mService;
}
