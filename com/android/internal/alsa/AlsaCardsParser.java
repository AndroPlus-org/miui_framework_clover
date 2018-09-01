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

public class AlsaCardsParser
{
    public class AlsaCardRecord
    {

        static boolean _2D_wrap0(AlsaCardRecord alsacardrecord, String s, int i)
        {
            return alsacardrecord.parse(s, i);
        }

        private boolean parse(String s, int i)
        {
            boolean flag = false;
            if(i != 0) goto _L2; else goto _L1
_L1:
            int j = AlsaCardsParser._2D_get0().nextToken(s, 0);
            int l = AlsaCardsParser._2D_get0().nextDelimiter(s, j);
            try
            {
                mCardNum = Integer.parseInt(s.substring(j, l));
            }
            catch(NumberFormatException numberformatexception)
            {
                Slog.e("AlsaCardRecord", (new StringBuilder()).append("Failed to parse line ").append(i).append(" of ").append("/proc/asound/cards").append(": ").append(s.substring(j, l)).toString());
                return false;
            }
            j = AlsaCardsParser._2D_get0().nextToken(s, l);
            i = AlsaCardsParser._2D_get0().nextDelimiter(s, j);
            mField1 = s.substring(j, i);
            mCardName = s.substring(AlsaCardsParser._2D_get0().nextToken(s, i));
_L4:
            return true;
_L2:
            if(i == 1)
            {
                int k = AlsaCardsParser._2D_get0().nextToken(s, 0);
                if(k != -1)
                {
                    i = s.indexOf("at usb-");
                    if(i != -1)
                        flag = true;
                    mIsUsb = flag;
                    if(mIsUsb)
                        mCardDescription = s.substring(k, i - 1);
                }
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void log(int i)
        {
            Slog.d("AlsaCardRecord", (new StringBuilder()).append("").append(i).append(" [").append(mCardNum).append(" ").append(mCardName).append(" : ").append(mCardDescription).append(" usb:").append(mIsUsb).toString());
        }

        public String textFormat()
        {
            return (new StringBuilder()).append(mCardName).append(" : ").append(mCardDescription).toString();
        }

        private static final String TAG = "AlsaCardRecord";
        private static final String kUsbCardKeyStr = "at usb-";
        public String mCardDescription;
        public String mCardName;
        public int mCardNum;
        public String mField1;
        public boolean mIsUsb;
        final AlsaCardsParser this$0;

        public AlsaCardRecord()
        {
            this$0 = AlsaCardsParser.this;
            super();
            mCardNum = -1;
            mField1 = "";
            mCardName = "";
            mCardDescription = "";
            mIsUsb = false;
        }
    }


    static LineTokenizer _2D_get0()
    {
        return mTokenizer;
    }

    public AlsaCardsParser()
    {
        mCardRecords = new ArrayList();
    }

    private void Log(String s)
    {
    }

    private static void LogDevices(String s, ArrayList arraylist)
    {
        Slog.d("AlsaCardsParser", (new StringBuilder()).append(s).append(" ----------------").toString());
        int i = 0;
        for(s = arraylist.iterator(); s.hasNext();)
        {
            ((AlsaCardRecord)s.next()).log(i);
            i++;
        }

        Slog.d("AlsaCardsParser", "----------------");
    }

    public static boolean hasCardNumber(ArrayList arraylist, int i)
    {
        for(arraylist = arraylist.iterator(); arraylist.hasNext();)
            if(((AlsaCardRecord)arraylist.next()).mCardNum == i)
                return true;

        return false;
    }

    public AlsaCardRecord getCardRecordAt(int i)
    {
        return (AlsaCardRecord)mCardRecords.get(i);
    }

    public AlsaCardRecord getCardRecordFor(int i)
    {
        for(Iterator iterator = mCardRecords.iterator(); iterator.hasNext();)
        {
            AlsaCardRecord alsacardrecord = (AlsaCardRecord)iterator.next();
            if(alsacardrecord.mCardNum == i)
                return alsacardrecord;
        }

        return null;
    }

    public int getDefaultCard()
    {
        int i = getDefaultUsbCard();
        int j = i;
        if(i < 0)
        {
            j = i;
            if(getNumCardRecords() > 0)
                j = getCardRecordAt(getNumCardRecords() - 1).mCardNum;
        }
        return j;
    }

    public int getDefaultUsbCard()
    {
        Object obj = mCardRecords;
        scan();
        for(Iterator iterator = getNewCardRecords(((ArrayList) (obj))).iterator(); iterator.hasNext();)
        {
            AlsaCardRecord alsacardrecord = (AlsaCardRecord)iterator.next();
            if(alsacardrecord.mIsUsb)
                return alsacardrecord.mCardNum;
        }

        for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
        {
            AlsaCardRecord alsacardrecord1 = (AlsaCardRecord)((Iterator) (obj)).next();
            if(alsacardrecord1.mIsUsb)
                return alsacardrecord1.mCardNum;
        }

        return -1;
    }

    public ArrayList getNewCardRecords(ArrayList arraylist)
    {
        ArrayList arraylist1 = new ArrayList();
        Iterator iterator = mCardRecords.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            AlsaCardRecord alsacardrecord = (AlsaCardRecord)iterator.next();
            if(!hasCardNumber(arraylist, alsacardrecord.mCardNum))
                arraylist1.add(alsacardrecord);
        } while(true);
        return arraylist1;
    }

    public int getNumCardRecords()
    {
        return mCardRecords.size();
    }

    public ArrayList getScanRecords()
    {
        return mCardRecords;
    }

    public boolean isCardUsb(int i)
    {
        for(Iterator iterator = mCardRecords.iterator(); iterator.hasNext();)
        {
            AlsaCardRecord alsacardrecord = (AlsaCardRecord)iterator.next();
            if(alsacardrecord.mCardNum == i)
                return alsacardrecord.mIsUsb;
        }

        return false;
    }

    public void scan()
    {
        Object obj;
        mCardRecords = new ArrayList();
        obj = new File("/proc/asound/cards");
        FileReader filereader;
        BufferedReader bufferedreader;
        filereader = JVM INSTR new #148 <Class FileReader>;
        filereader.FileReader(((File) (obj)));
        bufferedreader = JVM INSTR new #153 <Class BufferedReader>;
        bufferedreader.BufferedReader(filereader);
_L4:
        String s = bufferedreader.readLine();
        if(s == null) goto _L2; else goto _L1
_L1:
        obj = JVM INSTR new #6   <Class AlsaCardsParser$AlsaCardRecord>;
        ((AlsaCardRecord) (obj)).this. AlsaCardRecord();
        AlsaCardRecord._2D_wrap0(((AlsaCardRecord) (obj)), s, 0);
        s = bufferedreader.readLine();
        if(s != null) goto _L3; else goto _L2
_L2:
        filereader.close();
_L5:
        return;
_L3:
        AlsaCardRecord._2D_wrap0(((AlsaCardRecord) (obj)), s, 1);
        mCardRecords.add(obj);
          goto _L4
        Object obj1;
        obj1;
        ((FileNotFoundException) (obj1)).printStackTrace();
          goto _L5
        obj1;
        ((IOException) (obj1)).printStackTrace();
          goto _L5
    }

    protected static final boolean DEBUG = false;
    private static final String TAG = "AlsaCardsParser";
    private static final String kCardsFilePath = "/proc/asound/cards";
    private static LineTokenizer mTokenizer = new LineTokenizer(" :[]");
    private ArrayList mCardRecords;

}
