// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.alsa;

import android.util.Slog;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.android.internal.alsa:
//            LineTokenizer

public class AlsaDevicesParser
{
    public class AlsaDeviceRecord
    {

        public boolean parse(String s)
        {
            int i;
            int j;
            i = 0;
            j = 0;
_L8:
            int l;
            String s1;
            int k = AlsaDevicesParser._2D_get0().nextToken(s, i);
            if(k == -1)
                return true;
            l = AlsaDevicesParser._2D_get0().nextDelimiter(s, k);
            i = l;
            if(l == -1)
                i = s.length();
            s1 = s.substring(k, i);
            l = j;
            j;
            JVM INSTR tableswitch 0 5: default 100
        //                       0 103
        //                       1 111
        //                       2 141
        //                       3 204
        //                       4 252
        //                       5 306;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L2:
            break MISSING_BLOCK_LABEL_103;
_L1:
            l = j;
_L10:
            j = l + 1;
              goto _L8
_L3:
            try
            {
                mCardNum = Integer.parseInt(s1);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Slog.e("AlsaDevicesParser", (new StringBuilder()).append("Failed to parse token ").append(j).append(" of ").append("/proc/asound/devices").append(" token: ").append(s1).toString());
                return false;
            }
            l = j;
            if(s.charAt(i) == '-') goto _L10; else goto _L9
_L9:
            l = j + 1;
              goto _L10
_L4:
            mDeviceNum = Integer.parseInt(s1);
            l = j;
              goto _L10
_L5:
            l = j;
            if(s1.equals("digital")) goto _L10; else goto _L11
_L11:
            if(!s1.equals("control")) goto _L13; else goto _L12
_L12:
            mDeviceType = 1;
            l = j;
              goto _L10
_L13:
            s1.equals("raw");
            l = j;
              goto _L10
_L6:
            if(!s1.equals("audio")) goto _L15; else goto _L14
_L14:
            mDeviceType = 0;
            l = j;
              goto _L10
_L15:
            l = j;
            if(!s1.equals("midi")) goto _L10; else goto _L16
_L16:
            mDeviceType = 2;
            AlsaDevicesParser._2D_set1(AlsaDevicesParser.this, true);
            l = j;
              goto _L10
_L7:
            if(!s1.equals("capture")) goto _L18; else goto _L17
_L17:
            mDeviceDir = 0;
            AlsaDevicesParser._2D_set0(AlsaDevicesParser.this, true);
            l = j;
              goto _L10
_L18:
            l = j;
            if(!s1.equals("playback")) goto _L10; else goto _L19
_L19:
            mDeviceDir = 1;
            AlsaDevicesParser._2D_set2(AlsaDevicesParser.this, true);
            l = j;
              goto _L10
        }

        public String textFormat()
        {
            StringBuilder stringbuilder;
            stringbuilder = new StringBuilder();
            stringbuilder.append("[").append(mCardNum).append(":").append(mDeviceNum).append("]");
            mDeviceType;
            JVM INSTR tableswitch 0 2: default 68
        //                       0 112
        //                       1 122
        //                       2 132;
               goto _L1 _L2 _L3 _L4
_L1:
            stringbuilder.append(" N/A");
_L8:
            mDeviceDir;
            JVM INSTR tableswitch 0 1: default 100
        //                       0 142
        //                       1 152;
               goto _L5 _L6 _L7
_L5:
            stringbuilder.append(" N/A");
_L9:
            return stringbuilder.toString();
_L2:
            stringbuilder.append(" Audio");
              goto _L8
_L3:
            stringbuilder.append(" Control");
              goto _L8
_L4:
            stringbuilder.append(" MIDI");
              goto _L8
_L6:
            stringbuilder.append(" Capture");
              goto _L9
_L7:
            stringbuilder.append(" Playback");
              goto _L9
        }

        public static final int kDeviceDir_Capture = 0;
        public static final int kDeviceDir_Playback = 1;
        public static final int kDeviceDir_Unknown = -1;
        public static final int kDeviceType_Audio = 0;
        public static final int kDeviceType_Control = 1;
        public static final int kDeviceType_MIDI = 2;
        public static final int kDeviceType_Unknown = -1;
        int mCardNum;
        int mDeviceDir;
        int mDeviceNum;
        int mDeviceType;
        final AlsaDevicesParser this$0;

        public AlsaDeviceRecord()
        {
            this$0 = AlsaDevicesParser.this;
            super();
            mCardNum = -1;
            mDeviceNum = -1;
            mDeviceType = -1;
            mDeviceDir = -1;
        }
    }


    static LineTokenizer _2D_get0()
    {
        return mTokenizer;
    }

    static boolean _2D_set0(AlsaDevicesParser alsadevicesparser, boolean flag)
    {
        alsadevicesparser.mHasCaptureDevices = flag;
        return flag;
    }

    static boolean _2D_set1(AlsaDevicesParser alsadevicesparser, boolean flag)
    {
        alsadevicesparser.mHasMIDIDevices = flag;
        return flag;
    }

    static boolean _2D_set2(AlsaDevicesParser alsadevicesparser, boolean flag)
    {
        alsadevicesparser.mHasPlaybackDevices = flag;
        return flag;
    }

    public AlsaDevicesParser()
    {
        mHasCaptureDevices = false;
        mHasPlaybackDevices = false;
        mHasMIDIDevices = false;
    }

    private void Log(String s)
    {
    }

    private boolean isLineDeviceRecord(String s)
    {
        boolean flag;
        if(s.charAt(5) == '[')
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int getDefaultDeviceNum(int i)
    {
        return 0;
    }

    public boolean hasCaptureDevices(int i)
    {
        for(Iterator iterator = mDeviceRecords.iterator(); iterator.hasNext();)
        {
            AlsaDeviceRecord alsadevicerecord = (AlsaDeviceRecord)iterator.next();
            if(alsadevicerecord.mCardNum == i && alsadevicerecord.mDeviceType == 0 && alsadevicerecord.mDeviceDir == 0)
                return true;
        }

        return false;
    }

    public boolean hasMIDIDevices(int i)
    {
        for(Iterator iterator = mDeviceRecords.iterator(); iterator.hasNext();)
        {
            AlsaDeviceRecord alsadevicerecord = (AlsaDeviceRecord)iterator.next();
            if(alsadevicerecord.mCardNum == i && alsadevicerecord.mDeviceType == 2)
                return true;
        }

        return false;
    }

    public boolean hasPlaybackDevices(int i)
    {
        for(Iterator iterator = mDeviceRecords.iterator(); iterator.hasNext();)
        {
            AlsaDeviceRecord alsadevicerecord = (AlsaDeviceRecord)iterator.next();
            if(alsadevicerecord.mCardNum == i && alsadevicerecord.mDeviceType == 0 && alsadevicerecord.mDeviceDir == 1)
                return true;
        }

        return false;
    }

    public void scan()
    {
        Object obj;
        mDeviceRecords.clear();
        obj = new File("/proc/asound/devices");
        Object obj1;
        BufferedReader bufferedreader;
        obj1 = JVM INSTR new #123 <Class FileReader>;
        ((FileReader) (obj1)).FileReader(((File) (obj)));
        bufferedreader = JVM INSTR new #128 <Class BufferedReader>;
        bufferedreader.BufferedReader(((java.io.Reader) (obj1)));
_L4:
        obj = bufferedreader.readLine();
        if(obj == null) goto _L2; else goto _L1
_L1:
        if(!isLineDeviceRecord(((String) (obj)))) goto _L4; else goto _L3
_L3:
        AlsaDeviceRecord alsadevicerecord = JVM INSTR new #6   <Class AlsaDevicesParser$AlsaDeviceRecord>;
        alsadevicerecord.this. AlsaDeviceRecord();
        alsadevicerecord.parse(((String) (obj)));
        mDeviceRecords.add(alsadevicerecord);
          goto _L4
_L6:
        return;
_L2:
        try
        {
            ((FileReader) (obj1)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj1)
        {
            ((FileNotFoundException) (obj1)).printStackTrace();
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected static final boolean DEBUG = false;
    private static final String TAG = "AlsaDevicesParser";
    private static final String kDevicesFilePath = "/proc/asound/devices";
    private static final int kEndIndex_CardNum = 8;
    private static final int kEndIndex_DeviceNum = 11;
    private static final int kIndex_CardDeviceField = 5;
    private static final int kStartIndex_CardNum = 6;
    private static final int kStartIndex_DeviceNum = 9;
    private static final int kStartIndex_Type = 14;
    private static LineTokenizer mTokenizer = new LineTokenizer(" :[]-");
    private final ArrayList mDeviceRecords = new ArrayList();
    private boolean mHasCaptureDevices;
    private boolean mHasMIDIDevices;
    private boolean mHasPlaybackDevices;

}
