// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Build;
import android.util.SparseIntArray;
import java.util.Iterator;
import java.util.TreeSet;

// Referenced classes of package android.media:
//            AudioDevicePort, AudioFormat, AudioHandle

public final class AudioDeviceInfo
{

    AudioDeviceInfo(AudioDevicePort audiodeviceport)
    {
        mPort = audiodeviceport;
    }

    public static int convertDeviceTypeToInternalDevice(int i)
    {
        return EXT_TO_INT_DEVICE_MAPPING.get(i, 0);
    }

    public static int convertInternalDeviceToDeviceType(int i)
    {
        return INT_TO_EXT_DEVICE_MAPPING.get(i, 0);
    }

    public String getAddress()
    {
        return mPort.address();
    }

    public int[] getChannelCounts()
    {
        Object obj = new TreeSet();
        int ai[] = getChannelMasks();
        int i = ai.length;
        int j = 0;
        while(j < i) 
        {
            int k = ai[j];
            if(isSink())
                k = AudioFormat.channelCountFromOutChannelMask(k);
            else
                k = AudioFormat.channelCountFromInChannelMask(k);
            ((TreeSet) (obj)).add(Integer.valueOf(k));
            j++;
        }
        ai = getChannelIndexMasks();
        int l = ai.length;
        for(j = 0; j < l; j++)
            ((TreeSet) (obj)).add(Integer.valueOf(Integer.bitCount(ai[j])));

        ai = new int[((TreeSet) (obj)).size()];
        j = 0;
        for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
        {
            ai[j] = ((Integer)((Iterator) (obj)).next()).intValue();
            j++;
        }

        return ai;
    }

    public int[] getChannelIndexMasks()
    {
        return mPort.channelIndexMasks();
    }

    public int[] getChannelMasks()
    {
        return mPort.channelMasks();
    }

    public int[] getEncodings()
    {
        return AudioFormat.filterPublicFormats(mPort.formats());
    }

    public int getId()
    {
        return mPort.handle().id();
    }

    public CharSequence getProductName()
    {
        String s = mPort.name();
        if(s.length() == 0)
            s = Build.MODEL;
        return s;
    }

    public int[] getSampleRates()
    {
        return mPort.samplingRates();
    }

    public int getType()
    {
        return INT_TO_EXT_DEVICE_MAPPING.get(mPort.type(), 0);
    }

    public boolean isSink()
    {
        boolean flag;
        if(mPort.role() == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isSource()
    {
        boolean flag = true;
        if(mPort.role() != 1)
            flag = false;
        return flag;
    }

    private static final SparseIntArray EXT_TO_INT_DEVICE_MAPPING;
    private static final SparseIntArray INT_TO_EXT_DEVICE_MAPPING;
    public static final int TYPE_AUX_LINE = 19;
    public static final int TYPE_BLUETOOTH_A2DP = 8;
    public static final int TYPE_BLUETOOTH_SCO = 7;
    public static final int TYPE_BUILTIN_EARPIECE = 1;
    public static final int TYPE_BUILTIN_MIC = 15;
    public static final int TYPE_BUILTIN_SPEAKER = 2;
    public static final int TYPE_BUS = 21;
    public static final int TYPE_DOCK = 13;
    public static final int TYPE_FM = 14;
    public static final int TYPE_FM_TUNER = 16;
    public static final int TYPE_HDMI = 9;
    public static final int TYPE_HDMI_ARC = 10;
    public static final int TYPE_IP = 20;
    public static final int TYPE_LINE_ANALOG = 5;
    public static final int TYPE_LINE_DIGITAL = 6;
    public static final int TYPE_TELEPHONY = 18;
    public static final int TYPE_TV_TUNER = 17;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_USB_ACCESSORY = 12;
    public static final int TYPE_USB_DEVICE = 11;
    public static final int TYPE_USB_HEADSET = 22;
    public static final int TYPE_WIRED_HEADPHONES = 4;
    public static final int TYPE_WIRED_HEADSET = 3;
    private final AudioDevicePort mPort;

    static 
    {
        INT_TO_EXT_DEVICE_MAPPING = new SparseIntArray();
        INT_TO_EXT_DEVICE_MAPPING.put(1, 1);
        INT_TO_EXT_DEVICE_MAPPING.put(2, 2);
        INT_TO_EXT_DEVICE_MAPPING.put(4, 3);
        INT_TO_EXT_DEVICE_MAPPING.put(8, 4);
        INT_TO_EXT_DEVICE_MAPPING.put(16, 7);
        INT_TO_EXT_DEVICE_MAPPING.put(32, 7);
        INT_TO_EXT_DEVICE_MAPPING.put(64, 7);
        INT_TO_EXT_DEVICE_MAPPING.put(128, 8);
        INT_TO_EXT_DEVICE_MAPPING.put(256, 8);
        INT_TO_EXT_DEVICE_MAPPING.put(512, 8);
        INT_TO_EXT_DEVICE_MAPPING.put(1024, 9);
        INT_TO_EXT_DEVICE_MAPPING.put(2048, 13);
        INT_TO_EXT_DEVICE_MAPPING.put(4096, 13);
        INT_TO_EXT_DEVICE_MAPPING.put(8192, 12);
        INT_TO_EXT_DEVICE_MAPPING.put(16384, 11);
        INT_TO_EXT_DEVICE_MAPPING.put(0x4000000, 22);
        INT_TO_EXT_DEVICE_MAPPING.put(0x10000, 18);
        INT_TO_EXT_DEVICE_MAPPING.put(0x20000, 5);
        INT_TO_EXT_DEVICE_MAPPING.put(0x40000, 10);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80000, 6);
        INT_TO_EXT_DEVICE_MAPPING.put(0x100000, 14);
        INT_TO_EXT_DEVICE_MAPPING.put(0x200000, 19);
        INT_TO_EXT_DEVICE_MAPPING.put(0x800000, 20);
        INT_TO_EXT_DEVICE_MAPPING.put(0x1000000, 21);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80000004, 15);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80000008, 7);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80000010, 3);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80000020, 9);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80000040, 18);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80000080, 15);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80000200, 13);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80000400, 13);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80000800, 12);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80001000, 11);
        INT_TO_EXT_DEVICE_MAPPING.put(0x82000000, 22);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80002000, 16);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80004000, 17);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80008000, 5);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80010000, 6);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80020000, 8);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80080000, 20);
        INT_TO_EXT_DEVICE_MAPPING.put(0x80100000, 21);
        EXT_TO_INT_DEVICE_MAPPING = new SparseIntArray();
        EXT_TO_INT_DEVICE_MAPPING.put(1, 1);
        EXT_TO_INT_DEVICE_MAPPING.put(2, 2);
        EXT_TO_INT_DEVICE_MAPPING.put(3, 4);
        EXT_TO_INT_DEVICE_MAPPING.put(4, 8);
        EXT_TO_INT_DEVICE_MAPPING.put(5, 0x20000);
        EXT_TO_INT_DEVICE_MAPPING.put(6, 0x80000);
        EXT_TO_INT_DEVICE_MAPPING.put(7, 16);
        EXT_TO_INT_DEVICE_MAPPING.put(8, 128);
        EXT_TO_INT_DEVICE_MAPPING.put(9, 1024);
        EXT_TO_INT_DEVICE_MAPPING.put(10, 0x40000);
        EXT_TO_INT_DEVICE_MAPPING.put(11, 16384);
        EXT_TO_INT_DEVICE_MAPPING.put(22, 0x4000000);
        EXT_TO_INT_DEVICE_MAPPING.put(12, 8192);
        EXT_TO_INT_DEVICE_MAPPING.put(13, 2048);
        EXT_TO_INT_DEVICE_MAPPING.put(14, 0x100000);
        EXT_TO_INT_DEVICE_MAPPING.put(15, 0x80000004);
        EXT_TO_INT_DEVICE_MAPPING.put(16, 0x80002000);
        EXT_TO_INT_DEVICE_MAPPING.put(17, 0x80004000);
        EXT_TO_INT_DEVICE_MAPPING.put(18, 0x10000);
        EXT_TO_INT_DEVICE_MAPPING.put(19, 0x200000);
        EXT_TO_INT_DEVICE_MAPPING.put(20, 0x800000);
        EXT_TO_INT_DEVICE_MAPPING.put(21, 0x1000000);
    }
}
