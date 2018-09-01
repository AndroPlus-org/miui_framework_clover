// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.text.Html;
import android.text.TextUtils;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MusicLyricParser
{
    static class EntityCompator
        implements Comparator
    {

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((LyricEntity)obj, (LyricEntity)obj1);
        }

        public int compare(LyricEntity lyricentity, LyricEntity lyricentity1)
        {
            return lyricentity.time - lyricentity1.time;
        }

        EntityCompator()
        {
        }
    }

    public static class Lyric
    {

        static ArrayList _2D_get0(Lyric lyric)
        {
            return lyric.mEntityList;
        }

        private long getTimeFromLyricShot(int i, double d)
        {
            int j = size() - 1;
            long l;
            if(i >= j)
                l = (long)(((LyricEntity)mEntityList.get(j)).time + (i - j) * 8000) + (long)(8000D * d);
            else
                l = (long)((double)((LyricEntity)mEntityList.get(i)).time + (double)(((LyricEntity)mEntityList.get(i + 1)).time - ((LyricEntity)mEntityList.get(i)).time) * d);
            return l;
        }

        public void addOffset(int i)
        {
            LyricHeader lyricheader = mHeader;
            lyricheader.offset = lyricheader.offset + i;
            mIsModified = true;
        }

        public void correctLyric(LyricShot lyricshot, int i, double d)
        {
            boolean flag;
            long l;
            long l1;
label0:
            {
                for(int j = size(); i < 0 || i > j || lyricshot.lineIndex < 0 || lyricshot.lineIndex > j;)
                    return;

                l = getTimeFromLyricShot(lyricshot.lineIndex, lyricshot.percent);
                l1 = getTimeFromLyricShot(i, d);
                boolean flag1 = true;
                if(i <= lyricshot.lineIndex)
                {
                    flag = flag1;
                    if(i != lyricshot.lineIndex)
                        break label0;
                    flag = flag1;
                    if(d <= lyricshot.percent)
                        break label0;
                }
                flag = false;
            }
            if(!flag && l > l1)
                return;
            if(flag && l < l1)
            {
                return;
            } else
            {
                addOffset((int)(l - l1));
                return;
            }
        }

        public void decorate()
        {
            if(mEntityList.isEmpty())
                return;
            ArrayList arraylist = mEntityList;
            int i = arraylist.size();
            if(i <= 0 || ((LyricEntity)arraylist.get(0)).isDecorated())
                return;
            for(int j = 0; j < i; j++)
                ((LyricEntity)arraylist.get(j)).decorate();

        }

        public String getAfterLines(long l)
        {
            return mLyricLocator.getAfterLines(l);
        }

        public String getBeforeLines(long l)
        {
            return mLyricLocator.getBeforeLines(l);
        }

        public String getLastLine(long l)
        {
            return mLyricLocator.getLastLine(l);
        }

        public String getLine(long l)
        {
            return mLyricLocator.getLine(l);
        }

        public LyricEntity getLyricContent(int i)
        {
            LyricEntity lyricentity;
            if(i < 0)
                lyricentity = EMPTY_BEFORE;
            else
            if(i >= mEntityList.size())
                lyricentity = EMPTY_AFTER;
            else
                lyricentity = (LyricEntity)mEntityList.get(i);
            return lyricentity;
        }

        public LyricShot getLyricShot(long l)
        {
            int i = mHeader.offset;
            if((long)(((LyricEntity)mEntityList.get(0)).time + i) > l)
                return new LyricShot(0, 0.0D);
            for(int j = 1; j < mEntityList.size(); j++)
            {
                int k = ((LyricEntity)mEntityList.get(j)).time + i;
                if((long)k > l)
                {
                    i = ((LyricEntity)mEntityList.get(j - 1)).time + i;
                    double d = 0.0D;
                    if(k > i)
                        d = (double)(l - (long)i) / (double)(k - i);
                    return new LyricShot(j - 1, d);
                }
            }

            long l1 = ((LyricEntity)mEntityList.get(size() - 1)).time + i;
            if(l - l1 < 8000L)
            {
                double d1 = (double)(l - l1) / 8000D;
                return new LyricShot(size() - 1, d1);
            } else
            {
                return new LyricShot(mEntityList.size(), 0.0D);
            }
        }

        public String getNextLine(long l)
        {
            return mLyricLocator.getNextLine(l);
        }

        public long getOpenTime()
        {
            return mOpenTime;
        }

        public ArrayList getStringArr()
        {
            if(mEntityList.isEmpty())
                return null;
            ArrayList arraylist = new ArrayList(mEntityList.size());
            for(Iterator iterator = mEntityList.iterator(); iterator.hasNext(); arraylist.add(((LyricEntity)iterator.next()).lyric));
            return arraylist;
        }

        public int[] getTimeArr()
        {
            if(mEntityList.isEmpty())
                return null;
            int ai[] = new int[mEntityList.size()];
            int i = 0;
            for(Iterator iterator = mEntityList.iterator(); iterator.hasNext();)
            {
                ai[i] = ((LyricEntity)iterator.next()).time + mHeader.offset;
                i++;
            }

            return ai;
        }

        public boolean isModified()
        {
            return mIsModified;
        }

        public void recycleContent()
        {
            mEntityList.clear();
        }

        public void resetHeaderOffset()
        {
            mHeader.offset = mOriginHeaderOffset;
        }

        public void set(int ai[], ArrayList arraylist)
        {
            mLyricLocator.set(ai, arraylist);
        }

        public int size()
        {
            return mEntityList.size();
        }

        private final LyricEntity EMPTY_AFTER;
        private final LyricEntity EMPTY_BEFORE = new LyricEntity(-1, "\n");
        private final ArrayList mEntityList;
        private final LyricHeader mHeader;
        private boolean mIsModified;
        private LyricLocator mLyricLocator;
        private final long mOpenTime = System.currentTimeMillis();
        private int mOriginHeaderOffset;

        public Lyric(LyricHeader lyricheader, ArrayList arraylist, boolean flag)
        {
            mLyricLocator = new LyricLocator();
            mHeader = lyricheader;
            mOriginHeaderOffset = mHeader.offset;
            mEntityList = arraylist;
            mIsModified = flag;
            EMPTY_AFTER = new LyricEntity(arraylist.size(), "\n");
        }
    }

    class Lyric.LyricLine
    {

        CharSequence lyric;
        int pos;
        final Lyric this$1;

        Lyric.LyricLine()
        {
            this$1 = Lyric.this;
            super();
        }
    }

    class Lyric.LyricLocator
    {

        private int getLineNumber(long l)
        {
            long l1;
            for(int i = 0; i < mTimeArr.length; i++)
            {
                if(l < (long)mTimeArr[i])
                    continue;
                if(i < mTimeArr.length - 1)
                    l1 = mTimeArr[i + 1];
                else
                    l1 = 0x7fffffffffffffffL;
                if(l < l1)
                    return i;
            }

            return -1;
        }

        private void inflateLyricLines(ArrayList arraylist)
        {
            while(mTimeArr == null || arraylist == null || mTimeArr.length != arraylist.size()) 
            {
                mTimeArr = null;
                mLyricLines = null;
                return;
            }
            mLyricLines = new ArrayList();
            int i = 0;
            while(i < mTimeArr.length) 
            {
                Object obj = (CharSequence)arraylist.get(i);
                Lyric.LyricLine lyricline = new Lyric.LyricLine();
                lyricline.lyric = ((CharSequence) (obj));
                int k;
                if(i > 0)
                    obj = (Lyric.LyricLine)mLyricLines.get(i - 1);
                else
                    obj = null;
                if(obj != null)
                    k = ((Lyric.LyricLine) (obj)).pos + ((Lyric.LyricLine) (obj)).lyric.length() + CRLF_LENGTH;
                else
                    k = 0;
                lyricline.pos = k;
                mLyricLines.add(lyricline);
                i++;
            }
            mFullLyric = "";
            for(int j = 0; j < mLyricLines.size(); j++)
                mFullLyric = (new StringBuilder()).append(mFullLyric).append(((Lyric.LyricLine)mLyricLines.get(j)).lyric).append("\r\n").toString();

        }

        String getAfterLines(long l)
        {
            if(mTimeArr == null)
                return null;
            int i = getLineNumber(l);
            if(i < 0)
                return mFullLyric;
            if(i < mTimeArr.length - 1)
            {
                Lyric.LyricLine lyricline = (Lyric.LyricLine)mLyricLines.get(i);
                return mFullLyric.substring(lyricline.pos + lyricline.lyric.length() + CRLF_LENGTH, mFullLyric.length());
            } else
            {
                return null;
            }
        }

        String getBeforeLines(long l)
        {
            if(mTimeArr == null)
                return null;
            int i = getLineNumber(l);
            if(i > 0)
            {
                Lyric.LyricLine lyricline = (Lyric.LyricLine)mLyricLines.get(i);
                return mFullLyric.substring(0, lyricline.pos - CRLF_LENGTH);
            } else
            {
                return null;
            }
        }

        String getLastLine(long l)
        {
            if(mTimeArr == null)
                return null;
            int i = getLineNumber(l);
            if(i > 0)
            {
                Lyric.LyricLine lyricline = (Lyric.LyricLine)mLyricLines.get(i - 1);
                return mFullLyric.substring(lyricline.pos, lyricline.pos + lyricline.lyric.length());
            } else
            {
                return null;
            }
        }

        String getLine(long l)
        {
            if(mTimeArr == null)
                return null;
            int i = getLineNumber(l);
            if(i != -1)
            {
                Lyric.LyricLine lyricline = (Lyric.LyricLine)mLyricLines.get(i);
                return mFullLyric.substring(lyricline.pos, lyricline.pos + lyricline.lyric.length());
            } else
            {
                return null;
            }
        }

        String getNextLine(long l)
        {
            if(mTimeArr == null)
                return null;
            int i = getLineNumber(l);
            if(i >= -1 && i < mTimeArr.length - 1)
            {
                Lyric.LyricLine lyricline = (Lyric.LyricLine)mLyricLines.get(i + 1);
                return mFullLyric.substring(lyricline.pos, lyricline.pos + lyricline.lyric.length());
            } else
            {
                return null;
            }
        }

        void set(int ai[], ArrayList arraylist)
        {
            mTimeArr = ai;
            inflateLyricLines(arraylist);
        }

        final int CRLF_LENGTH = "\r\n".length();
        String mFullLyric;
        ArrayList mLyricLines;
        int mTimeArr[];
        final Lyric this$1;

        Lyric.LyricLocator()
        {
            this$1 = Lyric.this;
            super();
        }
    }

    public static class LyricEntity
    {

        public void decorate()
        {
            lyric = Html.fromHtml(String.format("%s<br/>", new Object[] {
                lyric
            }));
        }

        public boolean isDecorated()
        {
            return (lyric instanceof String) ^ true;
        }

        private static final String HTML_BR_PATTERN = "%s<br/>";
        public CharSequence lyric;
        public int time;

        public LyricEntity(int i, String s)
        {
            time = i;
            lyric = s;
        }
    }

    public static class LyricHeader
    {

        public String album;
        public String artist;
        public String editor;
        public int offset;
        public String title;
        public String version;

        public LyricHeader()
        {
        }
    }

    public static class LyricShot
    {

        public int lineIndex;
        public double percent;

        public LyricShot(int i, double d)
        {
            lineIndex = i;
            percent = d;
        }
    }


    public MusicLyricParser()
    {
    }

    private static void correctTime(Lyric lyric)
    {
        if(lyric == null)
            return;
        lyric = Lyric._2D_get0(lyric);
        int i = lyric.size();
        if(i > 1 && ((LyricEntity)lyric.get(0)).time == ((LyricEntity)lyric.get(1)).time)
            ((LyricEntity)lyric.get(0)).time = ((LyricEntity)lyric.get(1)).time / 2;
        for(int j = 1; j < i - 1; j++)
            if(((LyricEntity)lyric.get(j)).time == ((LyricEntity)lyric.get(j + 1)).time)
            {
                LyricEntity lyricentity = (LyricEntity)lyric.get(j);
                int k = ((LyricEntity)lyric.get(j - 1)).time;
                lyricentity.time = (((LyricEntity)lyric.get(j + 1)).time + k) / 2;
            }

    }

    private static Lyric doParse(String s)
        throws IOException
    {
        int i;
        boolean flag;
        boolean flag1;
        Object obj;
        LyricHeader lyricheader;
        ArrayList arraylist;
        i = 0;
        flag = false;
        flag1 = false;
        obj = null;
        lyricheader = new LyricHeader();
        arraylist = new ArrayList();
        s = s.split("\r\n");
        if(s == null) goto _L2; else goto _L1
_L1:
        int j = s.length;
_L7:
        flag = flag1;
        if(i >= j) goto _L2; else goto _L3
_L3:
        int k = parseLine(s[i], lyricheader, arraylist);
        if(k != 0) goto _L5; else goto _L4
_L4:
        flag = flag1;
_L2:
        s = obj;
        if(!arraylist.isEmpty())
        {
            Collections.sort(arraylist, new EntityCompator());
            s = new Lyric(lyricheader, arraylist, flag);
        }
        return s;
_L5:
        if(k == 1)
            flag1 = true;
        i++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private static int parseEntity(String as[], ArrayList arraylist, String s)
    {
        byte byte0 = 2;
        int i = (int)(Double.parseDouble(as[as.length - 1]) * 1000D);
        int j;
        int k;
        j = 0;
        k = 60;
        int l = as.length - 2;
_L2:
        byte byte1;
        byte1 = k;
        if(l < 0)
            break; /* Loop/switch isn't completed */
        int i1 = Integer.parseInt(as[l]);
        k = byte1 * 60;
        j += i1 * byte1;
        l--;
        if(true) goto _L2; else goto _L1
_L1:
        j = i + j * 1000;
        k = byte0;
        if(j >= 0x112a880)
            break MISSING_BLOCK_LABEL_113;
        as = JVM INSTR new #18  <Class MusicLyricParser$LyricEntity>;
        as.LyricEntity(j, s);
        arraylist.add(as);
        k = byte0;
_L4:
        return k;
        as;
        k = 1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static int parseHeader(String s, LyricHeader lyricheader)
    {
        int i = s.indexOf(":");
        if(i < 0 || i >= s.length() - 1)
            return 1;
        byte byte0 = 2;
        String s1 = s.substring(0, i);
        s = s.substring(i + 1);
        if(s1.equals("al"))
            lyricheader.album = s;
        else
        if(s1.equals("ar"))
            lyricheader.artist = s;
        else
        if(s1.equals("ti"))
            lyricheader.title = s;
        else
        if(s1.equals("by"))
            lyricheader.editor = s;
        else
        if(s1.equals("ve"))
            lyricheader.version = s;
        else
        if(s1.equals("offset"))
            try
            {
                lyricheader.offset = Integer.parseInt(s);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                byte0 = 1;
            }
        else
            byte0 = 1;
        return byte0;
    }

    private static int parseLine(String s, LyricHeader lyricheader, ArrayList arraylist)
    {
        s = s.trim();
        if(TextUtils.isEmpty(s))
            return 1;
        String s1 = TAG_EXTRA_LRC.matcher(s).replaceAll("");
        int i = s1.lastIndexOf("]");
        if(i == -1)
            return 1;
        s = s1.substring(i + 1);
        int j = s1.indexOf("[");
        if(j == -1)
            return 1;
        s1 = s1.substring(j, i);
        i = 2;
        String as[] = s1.split("]");
        j = 0;
        int k = as.length;
        while(j < k) 
        {
            String s2 = as[j];
            if(s2.startsWith("["))
            {
                String s3 = s2.substring(1);
                String as1[] = s3.split(":");
                if(as1.length >= 2)
                    if(TextUtils.isDigitsOnly(as1[0]))
                        i = parseEntity(as1, arraylist, s);
                    else
                        i = parseHeader(s3, lyricheader);
            }
            j++;
        }
        return i;
    }

    public static Lyric parseLyric(String s)
    {
        String s1;
        Object obj = null;
        s1 = null;
        if(s == null)
            break MISSING_BLOCK_LABEL_23;
        s1 = obj;
        s = doParse(s);
        s1 = s;
        correctTime(s);
        s1 = s;
_L2:
        return s1;
        s;
        s.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static final String CRLF = "\r\n";
    private static final int INTERVAL_OF_LAST = 8000;
    private static final int LINE_PARSE_IGNORE = 1;
    private static final int LINE_PARSE_REGULAR = 2;
    private static final int LINE_PARSE_STOP = 0;
    public static final int MAX_VALID_TIME = 0x112a880;
    private static final String TAG_ALBUM = "al";
    private static final String TAG_ARTIST = "ar";
    private static final String TAG_EDITOR = "by";
    private static final Pattern TAG_EXTRA_LRC = Pattern.compile("<[0-9]{0,2}:[0-9]{0,2}:[0-9]{0,2}>");
    private static final String TAG_OFFSET = "offset";
    private static final String TAG_TITLE = "ti";
    private static final String TAG_VERSION = "ve";

}
