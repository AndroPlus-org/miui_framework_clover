// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.graphics.Rect;
import android.os.Parcel;
import android.util.Log;
import java.util.*;

public final class TimedText
{
    public static final class CharPos
    {

        public final int endChar;
        public final int startChar;

        public CharPos(int i, int j)
        {
            startChar = i;
            endChar = j;
        }
    }

    public static final class Font
    {

        public final int ID;
        public final String name;

        public Font(int i, String s)
        {
            ID = i;
            name = s;
        }
    }

    public static final class HyperText
    {

        public final String URL;
        public final String altString;
        public final int endChar;
        public final int startChar;

        public HyperText(int i, int j, String s, String s1)
        {
            startChar = i;
            endChar = j;
            URL = s;
            altString = s1;
        }
    }

    public static final class Justification
    {

        public final int horizontalJustification;
        public final int verticalJustification;

        public Justification(int i, int j)
        {
            horizontalJustification = i;
            verticalJustification = j;
        }
    }

    public static final class Karaoke
    {

        public final int endChar;
        public final int endTimeMs;
        public final int startChar;
        public final int startTimeMs;

        public Karaoke(int i, int j, int k, int l)
        {
            startTimeMs = i;
            endTimeMs = j;
            startChar = k;
            endChar = l;
        }
    }

    public static final class Style
    {

        public final int colorRGBA;
        public final int endChar;
        public final int fontID;
        public final int fontSize;
        public final boolean isBold;
        public final boolean isItalic;
        public final boolean isUnderlined;
        public final int startChar;

        public Style(int i, int j, int k, boolean flag, boolean flag1, boolean flag2, int l, 
                int i1)
        {
            startChar = i;
            endChar = j;
            fontID = k;
            isBold = flag;
            isItalic = flag1;
            isUnderlined = flag2;
            fontSize = l;
            colorRGBA = i1;
        }
    }


    public TimedText(Parcel parcel)
    {
        mDisplayFlags = -1;
        mBackgroundColorRGBA = -1;
        mHighlightColorRGBA = -1;
        mScrollDelay = -1;
        mWrapText = -1;
        mBlinkingPosList = null;
        mHighlightPosList = null;
        mKaraokeList = null;
        mFontList = null;
        mStyleList = null;
        mHyperTextList = null;
        mTextBounds = null;
        mTextChars = null;
        if(!parseParcel(parcel))
        {
            mKeyObjectMap.clear();
            throw new IllegalArgumentException("parseParcel() fails");
        } else
        {
            return;
        }
    }

    private boolean containsKey(int i)
    {
        return isValidKey(i) && mKeyObjectMap.containsKey(Integer.valueOf(i));
    }

    private Object getObject(int i)
    {
        if(containsKey(i))
            return mKeyObjectMap.get(Integer.valueOf(i));
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid key: ").append(i).toString());
    }

    private boolean isValidKey(int i)
    {
        return i >= 1 && i <= 16 || i >= 101 && i <= 107;
    }

    private Set keySet()
    {
        return mKeyObjectMap.keySet();
    }

    private boolean parseParcel(Parcel parcel)
    {
        int i;
        parcel.setDataPosition(0);
        if(parcel.dataAvail() == 0)
            return false;
        i = parcel.readInt();
        if(i != 102) goto _L2; else goto _L1
_L1:
        int i1;
        int k = parcel.readInt();
        if(k != 7)
            return false;
        i = parcel.readInt();
        mKeyObjectMap.put(Integer.valueOf(k), Integer.valueOf(i));
        if(parcel.readInt() != 16)
            return false;
        parcel.readInt();
        byte abyte0[] = parcel.createByteArray();
        if(abyte0 == null || abyte0.length == 0)
            mTextChars = null;
        else
            mTextChars = new String(abyte0);
_L21:
        if(parcel.dataAvail() > 0)
            break MISSING_BLOCK_LABEL_104;
          goto _L3
_L2:
        if(i != 101)
        {
            Log.w("TimedText", (new StringBuilder()).append("Invalid timed text key found: ").append(i).toString());
            return false;
        }
        continue; /* Loop/switch isn't completed */
        Object obj;
        Object obj1;
        i1 = parcel.readInt();
        if(!isValidKey(i1))
        {
            Log.w("TimedText", (new StringBuilder()).append("Invalid timed text key found: ").append(i1).toString());
            return false;
        }
        obj1 = null;
        obj = obj1;
        i1;
        JVM INSTR tableswitch 1 15: default 280
    //                   1 459
    //                   2 284
    //                   3 507
    //                   4 439
    //                   5 569
    //                   6 419
    //                   7 284
    //                   8 405
    //                   9 349
    //                   10 363
    //                   11 391
    //                   12 377
    //                   13 335
    //                   14 527
    //                   15 479;
           goto _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L6 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18
_L6:
        break; /* Loop/switch isn't completed */
_L4:
        obj = obj1;
_L19:
        if(obj != null)
        {
            if(mKeyObjectMap.containsKey(Integer.valueOf(i1)))
                mKeyObjectMap.remove(Integer.valueOf(i1));
            mKeyObjectMap.put(Integer.valueOf(i1), obj);
        }
        continue; /* Loop/switch isn't completed */
_L16:
        readStyle(parcel);
        obj = mStyleList;
        continue; /* Loop/switch isn't completed */
_L12:
        readFont(parcel);
        obj = mFontList;
        continue; /* Loop/switch isn't completed */
_L13:
        readHighlight(parcel);
        obj = mHighlightPosList;
        continue; /* Loop/switch isn't completed */
_L15:
        readKaraoke(parcel);
        obj = mKaraokeList;
        continue; /* Loop/switch isn't completed */
_L14:
        readHyperText(parcel);
        obj = mHyperTextList;
        continue; /* Loop/switch isn't completed */
_L11:
        readBlinkingText(parcel);
        obj = mBlinkingPosList;
        continue; /* Loop/switch isn't completed */
_L10:
        mWrapText = parcel.readInt();
        obj = Integer.valueOf(mWrapText);
        continue; /* Loop/switch isn't completed */
_L8:
        mHighlightColorRGBA = parcel.readInt();
        obj = Integer.valueOf(mHighlightColorRGBA);
        continue; /* Loop/switch isn't completed */
_L5:
        mDisplayFlags = parcel.readInt();
        obj = Integer.valueOf(mDisplayFlags);
        continue; /* Loop/switch isn't completed */
_L18:
        mJustification = new Justification(parcel.readInt(), parcel.readInt());
        obj = mJustification;
        continue; /* Loop/switch isn't completed */
_L7:
        mBackgroundColorRGBA = parcel.readInt();
        obj = Integer.valueOf(mBackgroundColorRGBA);
        continue; /* Loop/switch isn't completed */
_L17:
        int l = parcel.readInt();
        int j1 = parcel.readInt();
        int j = parcel.readInt();
        mTextBounds = new Rect(j1, l, parcel.readInt(), j);
        obj = obj1;
        continue; /* Loop/switch isn't completed */
_L9:
        mScrollDelay = parcel.readInt();
        obj = Integer.valueOf(mScrollDelay);
        if(true) goto _L19; else goto _L3
_L3:
        return true;
        if(true) goto _L21; else goto _L20
_L20:
    }

    private void readBlinkingText(Parcel parcel)
    {
        parcel = new CharPos(parcel.readInt(), parcel.readInt());
        if(mBlinkingPosList == null)
            mBlinkingPosList = new ArrayList();
        mBlinkingPosList.add(parcel);
    }

    private void readFont(Parcel parcel)
    {
        int i = parcel.readInt();
        for(int j = 0; j < i; j++)
        {
            int k = parcel.readInt();
            int l = parcel.readInt();
            Font font = new Font(k, new String(parcel.createByteArray(), 0, l));
            if(mFontList == null)
                mFontList = new ArrayList();
            mFontList.add(font);
        }

    }

    private void readHighlight(Parcel parcel)
    {
        parcel = new CharPos(parcel.readInt(), parcel.readInt());
        if(mHighlightPosList == null)
            mHighlightPosList = new ArrayList();
        mHighlightPosList.add(parcel);
    }

    private void readHyperText(Parcel parcel)
    {
        int i = parcel.readInt();
        int j = parcel.readInt();
        int k = parcel.readInt();
        String s = new String(parcel.createByteArray(), 0, k);
        k = parcel.readInt();
        parcel = new HyperText(i, j, s, new String(parcel.createByteArray(), 0, k));
        if(mHyperTextList == null)
            mHyperTextList = new ArrayList();
        mHyperTextList.add(parcel);
    }

    private void readKaraoke(Parcel parcel)
    {
        int i = parcel.readInt();
        for(int j = 0; j < i; j++)
        {
            Karaoke karaoke = new Karaoke(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
            if(mKaraokeList == null)
                mKaraokeList = new ArrayList();
            mKaraokeList.add(karaoke);
        }

    }

    private void readStyle(Parcel parcel)
    {
        boolean flag = false;
        int i = -1;
        int j = -1;
        int k = -1;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        int l = -1;
        int i1 = -1;
        do
        {
            if(!flag && parcel.dataAvail() > 0)
            {
                switch(parcel.readInt())
                {
                default:
                    parcel.setDataPosition(parcel.dataPosition() - 4);
                    flag = true;
                    break;

                case 103: // 'g'
                    i = parcel.readInt();
                    break;

                case 104: // 'h'
                    j = parcel.readInt();
                    break;

                case 105: // 'i'
                    k = parcel.readInt();
                    break;

                case 2: // '\002'
                    int j1 = parcel.readInt();
                    if(j1 % 2 == 1)
                        flag3 = true;
                    else
                        flag3 = false;
                    if(j1 % 4 >= 2)
                        flag2 = true;
                    else
                        flag2 = false;
                    if(j1 / 4 == 1)
                    {
                        boolean flag4 = true;
                        flag1 = flag3;
                        flag3 = flag4;
                    } else
                    {
                        boolean flag5 = false;
                        flag1 = flag3;
                        flag3 = flag5;
                    }
                    break;

                case 106: // 'j'
                    l = parcel.readInt();
                    break;

                case 107: // 'k'
                    i1 = parcel.readInt();
                    break;
                }
                continue;
            }
            parcel = new Style(i, j, k, flag1, flag2, flag3, l, i1);
            if(mStyleList == null)
                mStyleList = new ArrayList();
            mStyleList.add(parcel);
            break;
        } while(true);
    }

    public Rect getBounds()
    {
        return mTextBounds;
    }

    public String getText()
    {
        return mTextChars;
    }

    private static final int FIRST_PRIVATE_KEY = 101;
    private static final int FIRST_PUBLIC_KEY = 1;
    private static final int KEY_BACKGROUND_COLOR_RGBA = 3;
    private static final int KEY_DISPLAY_FLAGS = 1;
    private static final int KEY_END_CHAR = 104;
    private static final int KEY_FONT_ID = 105;
    private static final int KEY_FONT_SIZE = 106;
    private static final int KEY_GLOBAL_SETTING = 101;
    private static final int KEY_HIGHLIGHT_COLOR_RGBA = 4;
    private static final int KEY_LOCAL_SETTING = 102;
    private static final int KEY_SCROLL_DELAY = 5;
    private static final int KEY_START_CHAR = 103;
    private static final int KEY_START_TIME = 7;
    private static final int KEY_STRUCT_BLINKING_TEXT_LIST = 8;
    private static final int KEY_STRUCT_FONT_LIST = 9;
    private static final int KEY_STRUCT_HIGHLIGHT_LIST = 10;
    private static final int KEY_STRUCT_HYPER_TEXT_LIST = 11;
    private static final int KEY_STRUCT_JUSTIFICATION = 15;
    private static final int KEY_STRUCT_KARAOKE_LIST = 12;
    private static final int KEY_STRUCT_STYLE_LIST = 13;
    private static final int KEY_STRUCT_TEXT = 16;
    private static final int KEY_STRUCT_TEXT_POS = 14;
    private static final int KEY_STYLE_FLAGS = 2;
    private static final int KEY_TEXT_COLOR_RGBA = 107;
    private static final int KEY_WRAP_TEXT = 6;
    private static final int LAST_PRIVATE_KEY = 107;
    private static final int LAST_PUBLIC_KEY = 16;
    private static final String TAG = "TimedText";
    private int mBackgroundColorRGBA;
    private List mBlinkingPosList;
    private int mDisplayFlags;
    private List mFontList;
    private int mHighlightColorRGBA;
    private List mHighlightPosList;
    private List mHyperTextList;
    private Justification mJustification;
    private List mKaraokeList;
    private final HashMap mKeyObjectMap = new HashMap();
    private int mScrollDelay;
    private List mStyleList;
    private Rect mTextBounds;
    private String mTextChars;
    private int mWrapText;
}
