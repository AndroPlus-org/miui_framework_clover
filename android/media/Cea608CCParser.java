// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.*;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;

class Cea608CCParser
{
    private static class CCData
    {

        static boolean _2D_wrap0(CCData ccdata)
        {
            return ccdata.isExtendedChar();
        }

        private String ctrlCodeToString(int i)
        {
            return mCtrlCodeMap[i - 32];
        }

        static CCData[] fromByteArray(byte abyte0[])
        {
            CCData accdata[] = new CCData[abyte0.length / 3];
            for(int i = 0; i < accdata.length; i++)
                accdata[i] = new CCData(abyte0[i * 3], abyte0[i * 3 + 1], abyte0[i * 3 + 2]);

            return accdata;
        }

        private char getBasicChar(byte byte0)
        {
            byte0;
            JVM INSTR lookupswitch 10: default 92
        //                       42: 99
        //                       92: 108
        //                       94: 117
        //                       95: 126
        //                       96: 135
        //                       123: 144
        //                       124: 153
        //                       125: 162
        //                       126: 171
        //                       127: 180;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L1:
            char c;
            byte0 = (char)byte0;
            c = byte0;
_L13:
            return c;
_L2:
            byte0 = 225;
            c = byte0;
            continue; /* Loop/switch isn't completed */
_L3:
            byte0 = 233;
            c = byte0;
            continue; /* Loop/switch isn't completed */
_L4:
            byte0 = 237;
            c = byte0;
            continue; /* Loop/switch isn't completed */
_L5:
            byte0 = 243;
            c = byte0;
            continue; /* Loop/switch isn't completed */
_L6:
            byte0 = 250;
            c = byte0;
            continue; /* Loop/switch isn't completed */
_L7:
            byte0 = 231;
            c = byte0;
            continue; /* Loop/switch isn't completed */
_L8:
            byte0 = 247;
            c = byte0;
            continue; /* Loop/switch isn't completed */
_L9:
            byte0 = 209;
            c = byte0;
            continue; /* Loop/switch isn't completed */
_L10:
            byte0 = 241;
            c = byte0;
            continue; /* Loop/switch isn't completed */
_L11:
            byte0 = 9608;
            c = byte0;
            if(true) goto _L13; else goto _L12
_L12:
        }

        private String getBasicChars()
        {
            if(mData1 >= 32 && mData1 <= 127)
            {
                StringBuilder stringbuilder = new StringBuilder(2);
                stringbuilder.append(getBasicChar(mData1));
                if(mData2 >= 32 && mData2 <= 127)
                    stringbuilder.append(getBasicChar(mData2));
                return stringbuilder.toString();
            } else
            {
                return null;
            }
        }

        private String getExtendedChar()
        {
            if((mData1 == 18 || mData1 == 26) && mData2 >= 32 && mData2 <= 63)
                return mSpanishCharMap[mData2 - 32];
            if((mData1 == 19 || mData1 == 27) && mData2 >= 32 && mData2 <= 63)
                return mProtugueseCharMap[mData2 - 32];
            else
                return null;
        }

        private String getSpecialChar()
        {
            if((mData1 == 17 || mData1 == 25) && mData2 >= 48 && mData2 <= 63)
                return mSpecialCharMap[mData2 - 48];
            else
                return null;
        }

        private boolean isBasicChar()
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mData1 >= 32)
            {
                flag1 = flag;
                if(mData1 <= 127)
                    flag1 = true;
            }
            return flag1;
        }

        private boolean isExtendedChar()
        {
            boolean flag = false;
              goto _L1
_L3:
            boolean flag1;
            flag1 = flag;
            if(mData2 >= 32)
            {
                flag1 = flag;
                if(mData2 <= 63)
                    flag1 = true;
            }
_L4:
            return flag1;
_L1:
            if(mData1 == 18 || mData1 == 26 || mData1 == 19) goto _L3; else goto _L2
_L2:
            flag1 = flag;
            if(mData1 != 27) goto _L4; else goto _L3
        }

        private boolean isSpecialChar()
        {
            boolean flag1;
label0:
            {
                boolean flag = false;
                if(mData1 != 17)
                {
                    flag1 = flag;
                    if(mData1 != 25)
                        break label0;
                }
                flag1 = flag;
                if(mData2 >= 48)
                {
                    flag1 = flag;
                    if(mData2 <= 63)
                        flag1 = true;
                }
            }
            return flag1;
        }

        int getCtrlCode()
        {
            if((mData1 == 20 || mData1 == 28) && mData2 >= 32 && mData2 <= 47)
                return mData2;
            else
                return -1;
        }

        String getDisplayText()
        {
            String s = getBasicChars();
            String s2 = s;
            if(s == null)
            {
                String s1 = getSpecialChar();
                s2 = s1;
                if(s1 == null)
                    s2 = getExtendedChar();
            }
            return s2;
        }

        StyleCode getMidRow()
        {
            if((mData1 == 17 || mData1 == 25) && mData2 >= 32 && mData2 <= 47)
                return StyleCode.fromByte(mData2);
            else
                return null;
        }

        PAC getPAC()
        {
            if((mData1 & 0x70) == 16 && (mData2 & 0x40) == 64 && ((mData1 & 7) != 0 || (mData2 & 0x20) == 0))
                return PAC.fromBytes(mData1, mData2);
            else
                return null;
        }

        int getTabOffset()
        {
            if((mData1 == 23 || mData1 == 31) && mData2 >= 33 && mData2 <= 35)
                return mData2 & 3;
            else
                return 0;
        }

        boolean isDisplayableChar()
        {
            boolean flag;
            if(!isBasicChar() && !isSpecialChar())
                flag = isExtendedChar();
            else
                flag = true;
            return flag;
        }

        public String toString()
        {
            if(mData1 < 16 && mData2 < 16)
                return String.format("[%d]Null: %02x %02x", new Object[] {
                    Byte.valueOf(mType), Byte.valueOf(mData1), Byte.valueOf(mData2)
                });
            int i = getCtrlCode();
            if(i != -1)
                return String.format("[%d]%s", new Object[] {
                    Byte.valueOf(mType), ctrlCodeToString(i)
                });
            i = getTabOffset();
            if(i > 0)
                return String.format("[%d]Tab%d", new Object[] {
                    Byte.valueOf(mType), Integer.valueOf(i)
                });
            Object obj = getPAC();
            if(obj != null)
                return String.format("[%d]PAC: %s", new Object[] {
                    Byte.valueOf(mType), ((PAC) (obj)).toString()
                });
            obj = getMidRow();
            if(obj != null)
                return String.format("[%d]Mid-row: %s", new Object[] {
                    Byte.valueOf(mType), ((StyleCode) (obj)).toString()
                });
            if(isDisplayableChar())
                return String.format("[%d]Displayable: %s (%02x %02x)", new Object[] {
                    Byte.valueOf(mType), getDisplayText(), Byte.valueOf(mData1), Byte.valueOf(mData2)
                });
            else
                return String.format("[%d]Invalid: %02x %02x", new Object[] {
                    Byte.valueOf(mType), Byte.valueOf(mData1), Byte.valueOf(mData2)
                });
        }

        private static final String mCtrlCodeMap[] = {
            "RCL", "BS", "AOF", "AON", "DER", "RU2", "RU3", "RU4", "FON", "RDC", 
            "TR", "RTD", "EDM", "CR", "ENM", "EOC"
        };
        private static final String mProtugueseCharMap[] = {
            "\303", "\343", "\315", "\314", "\354", "\322", "\362", "\325", "\365", "{", 
            "}", "\\", "^", "_", "|", "~", "\304", "\344", "\326", "\366", 
            "\337", "\245", "\244", "\u2502", "\305", "\345", "\330", "\370", "\u250C", "\u2510", 
            "\u2514", "\u2518"
        };
        private static final String mSpanishCharMap[] = {
            "\301", "\311", "\323", "\332", "\334", "\374", "\u2018", "\241", "*", "'", 
            "\u2014", "\251", "\u2120", "\u2022", "\u201C", "\u201D", "\300", "\302", "\307", "\310", 
            "\312", "\313", "\353", "\316", "\317", "\357", "\324", "\331", "\371", "\333", 
            "\253", "\273"
        };
        private static final String mSpecialCharMap[] = {
            "\256", "\260", "\275", "\277", "\u2122", "\242", "\243", "\u266A", "\340", "\240", 
            "\350", "\342", "\352", "\356", "\364", "\373"
        };
        private final byte mData1;
        private final byte mData2;
        private final byte mType;


        CCData(byte byte0, byte byte1, byte byte2)
        {
            mType = byte0;
            mData1 = byte1;
            mData2 = byte2;
        }
    }

    private static class CCLineBuilder
    {

        void applyStyleSpan(SpannableStringBuilder spannablestringbuilder, StyleCode stylecode, int i, int j)
        {
            if(stylecode.isItalics())
                spannablestringbuilder.setSpan(new StyleSpan(2), i, j, 33);
            if(stylecode.isUnderline())
                spannablestringbuilder.setSpan(new UnderlineSpan(), i, j, 33);
        }

        char charAt(int i)
        {
            return mDisplayChars.charAt(i);
        }

        SpannableStringBuilder getStyledText(android.view.accessibility.CaptioningManager.CaptionStyle captionstyle)
        {
            SpannableStringBuilder spannablestringbuilder;
            int i;
            int j;
            int k;
            StyleCode stylecode;
            spannablestringbuilder = new SpannableStringBuilder(mDisplayChars);
            i = -1;
            j = 0;
            k = -1;
            stylecode = null;
_L8:
            Object obj;
            if(j >= mDisplayChars.length())
                break MISSING_BLOCK_LABEL_269;
            obj = null;
            if(mMidRowStyles[j] == null) goto _L2; else goto _L1
_L1:
            StyleCode stylecode1 = mMidRowStyles[j];
_L4:
            int l = k;
            if(stylecode1 != null)
            {
                stylecode = stylecode1;
                if(k >= 0 && i >= 0)
                    applyStyleSpan(spannablestringbuilder, stylecode1, k, j);
                l = j;
            }
            if(mDisplayChars.charAt(j) != '\240')
            {
                k = i;
                if(i < 0)
                    k = j;
            } else
            {
                k = i;
                if(i >= 0)
                {
                    if(mDisplayChars.charAt(i) != ' ')
                        i--;
                    if(mDisplayChars.charAt(j - 1) == ' ')
                        k = j;
                    else
                        k = j + 1;
                    spannablestringbuilder.setSpan(new MutableBackgroundColorSpan(captionstyle.backgroundColor), i, k, 33);
                    if(l >= 0)
                        applyStyleSpan(spannablestringbuilder, stylecode, l, k);
                    k = -1;
                }
            }
            j++;
            i = k;
            k = l;
            continue; /* Loop/switch isn't completed */
_L2:
            stylecode1 = obj;
            if(mPACStyles[j] == null) goto _L4; else goto _L3
_L3:
            if(k < 0) goto _L6; else goto _L5
_L5:
            stylecode1 = obj;
            if(i >= 0) goto _L4; else goto _L6
_L6:
            stylecode1 = mPACStyles[j];
              goto _L4
            return spannablestringbuilder;
            if(true) goto _L8; else goto _L7
_L7:
        }

        int length()
        {
            return mDisplayChars.length();
        }

        void setCharAt(int i, char c)
        {
            mDisplayChars.setCharAt(i, c);
            mMidRowStyles[i] = null;
        }

        void setMidRowAt(int i, StyleCode stylecode)
        {
            mDisplayChars.setCharAt(i, ' ');
            mMidRowStyles[i] = stylecode;
        }

        void setPACAt(int i, PAC pac)
        {
            mPACStyles[i] = pac;
        }

        private final StringBuilder mDisplayChars;
        private final StyleCode mMidRowStyles[];
        private final StyleCode mPACStyles[];

        CCLineBuilder(String s)
        {
            mDisplayChars = new StringBuilder(s);
            mMidRowStyles = new StyleCode[mDisplayChars.length()];
            mPACStyles = new StyleCode[mDisplayChars.length()];
        }
    }

    private static class CCMemory
    {

        static void _2D_wrap0(CCMemory ccmemory, int i, int j)
        {
            ccmemory.moveBaselineTo(i, j);
        }

        private static int clamp(int i, int j, int k)
        {
            if(i >= j)
                if(i > k)
                    j = k;
                else
                    j = i;
            return j;
        }

        private CCLineBuilder getLineBuffer(int i)
        {
            if(mLines[i] == null)
                mLines[i] = new CCLineBuilder(mBlankLine);
            return mLines[i];
        }

        private void moveBaselineTo(int i, int j)
        {
            if(mRow == i)
                return;
            int k = j;
            if(i < j)
                k = i;
            int k1 = k;
            if(mRow < k)
                k1 = mRow;
            if(i < mRow)
            {
                for(int l = k1 - 1; l >= 0; l--)
                    mLines[i - l] = mLines[mRow - l];

            } else
            {
                for(int i1 = 0; i1 < k1; i1++)
                    mLines[i - i1] = mLines[mRow - i1];

            }
            for(int j1 = 0; j1 <= i - j; j1++)
                mLines[j1] = null;

            for(i++; i < mLines.length; i++)
                mLines[i] = null;

        }

        private void moveCursorByCol(int i)
        {
            mCol = clamp(mCol + i, 1, 32);
        }

        private void moveCursorTo(int i, int j)
        {
            mRow = clamp(i, 1, 15);
            mCol = clamp(j, 1, 32);
        }

        private void moveCursorToRow(int i)
        {
            mRow = clamp(i, 1, 15);
        }

        void bs()
        {
            moveCursorByCol(-1);
            if(mLines[mRow] != null)
            {
                mLines[mRow].setCharAt(mCol, '\240');
                if(mCol == 31)
                    mLines[mRow].setCharAt(32, '\240');
            }
        }

        void cr()
        {
            moveCursorTo(mRow + 1, 1);
        }

        void der()
        {
            if(mLines[mRow] != null)
            {
                for(int i = 0; i < mCol; i++)
                    if(mLines[mRow].charAt(i) != '\240')
                    {
                        for(i = mCol; i < mLines[mRow].length(); i++)
                            mLines[i].setCharAt(i, '\240');

                        return;
                    }

                mLines[mRow] = null;
            }
        }

        void erase()
        {
            for(int i = 0; i < mLines.length; i++)
                mLines[i] = null;

            mRow = 15;
            mCol = 1;
        }

        SpannableStringBuilder[] getStyledText(android.view.accessibility.CaptioningManager.CaptionStyle captionstyle)
        {
            ArrayList arraylist = new ArrayList(15);
            int i = 1;
            while(i <= 15) 
            {
                SpannableStringBuilder spannablestringbuilder;
                if(mLines[i] != null)
                    spannablestringbuilder = mLines[i].getStyledText(captionstyle);
                else
                    spannablestringbuilder = null;
                arraylist.add(spannablestringbuilder);
                i++;
            }
            return (SpannableStringBuilder[])arraylist.toArray(new SpannableStringBuilder[15]);
        }

        void rollUp(int i)
        {
            for(int j = 0; j <= mRow - i; j++)
                mLines[j] = null;

            int k = (mRow - i) + 1;
            i = k;
            if(k < 1)
                i = 1;
            for(; i < mRow; i++)
                mLines[i] = mLines[i + 1];

            for(i = mRow; i < mLines.length; i++)
                mLines[i] = null;

            mCol = 1;
        }

        void tab(int i)
        {
            moveCursorByCol(i);
        }

        void writeMidRowCode(StyleCode stylecode)
        {
            getLineBuffer(mRow).setMidRowAt(mCol, stylecode);
            moveCursorByCol(1);
        }

        void writePAC(PAC pac)
        {
            if(pac.isIndentPAC())
                moveCursorTo(pac.getRow(), pac.getCol());
            else
                moveCursorTo(pac.getRow(), 1);
            getLineBuffer(mRow).setPACAt(mCol, pac);
        }

        void writeText(String s)
        {
            for(int i = 0; i < s.length(); i++)
            {
                getLineBuffer(mRow).setCharAt(mCol, s.charAt(i));
                moveCursorByCol(1);
            }

        }

        private final String mBlankLine;
        private int mCol;
        private final CCLineBuilder mLines[] = new CCLineBuilder[17];
        private int mRow;

        CCMemory()
        {
            char ac[] = new char[34];
            Arrays.fill(ac, '\240');
            mBlankLine = new String(ac);
        }
    }

    static interface DisplayListener
    {

        public abstract android.view.accessibility.CaptioningManager.CaptionStyle getCaptionStyle();

        public abstract void onDisplayChanged(SpannableStringBuilder aspannablestringbuilder[]);
    }

    public static class MutableBackgroundColorSpan extends CharacterStyle
        implements UpdateAppearance
    {

        public int getBackgroundColor()
        {
            return mColor;
        }

        public void setBackgroundColor(int i)
        {
            mColor = i;
        }

        public void updateDrawState(TextPaint textpaint)
        {
            textpaint.bgColor = mColor;
        }

        private int mColor;

        public MutableBackgroundColorSpan(int i)
        {
            mColor = i;
        }
    }

    private static class PAC extends StyleCode
    {

        static PAC fromBytes(byte byte0, byte byte1)
        {
            int i = (new int[] {
                11, 1, 3, 12, 14, 5, 7, 9
            })[byte0 & 7] + ((byte1 & 0x20) >> 5);
            byte0 = 0;
            if((byte1 & 1) != 0)
                byte0 = 2;
            if((byte1 & 0x10) != 0)
                return new PAC(i, (byte1 >> 1 & 7) * 4, byte0, 0);
            int j = byte1 >> 1 & 7;
            int k = j;
            byte1 = byte0;
            if(j == 7)
            {
                k = 0;
                byte1 = byte0 | 1;
            }
            return new PAC(i, -1, byte1, k);
        }

        int getCol()
        {
            return mCol;
        }

        int getRow()
        {
            return mRow;
        }

        boolean isIndentPAC()
        {
            boolean flag = false;
            if(mCol >= 0)
                flag = true;
            return flag;
        }

        public String toString()
        {
            return String.format("{%d, %d}, %s", new Object[] {
                Integer.valueOf(mRow), Integer.valueOf(mCol), super.toString()
            });
        }

        final int mCol;
        final int mRow;

        PAC(int i, int j, int k, int l)
        {
            super(k, l);
            mRow = i;
            mCol = j;
        }
    }

    private static class StyleCode
    {

        static StyleCode fromByte(byte byte0)
        {
            byte byte1 = 0;
            int i = byte0 >> 1 & 7;
            if((byte0 & 1) != 0)
                byte1 = 2;
            int j = i;
            byte0 = byte1;
            if(i == 7)
            {
                j = 0;
                byte0 = byte1 | 1;
            }
            return new StyleCode(byte0, j);
        }

        int getColor()
        {
            return mColor;
        }

        boolean isItalics()
        {
            boolean flag = false;
            if((mStyle & 1) != 0)
                flag = true;
            return flag;
        }

        boolean isUnderline()
        {
            boolean flag = false;
            if((mStyle & 2) != 0)
                flag = true;
            return flag;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("{");
            stringbuilder.append(mColorMap[mColor]);
            if((mStyle & 1) != 0)
                stringbuilder.append(", ITALICS");
            if((mStyle & 2) != 0)
                stringbuilder.append(", UNDERLINE");
            stringbuilder.append("}");
            return stringbuilder.toString();
        }

        static final int COLOR_BLUE = 2;
        static final int COLOR_CYAN = 3;
        static final int COLOR_GREEN = 1;
        static final int COLOR_INVALID = 7;
        static final int COLOR_MAGENTA = 6;
        static final int COLOR_RED = 4;
        static final int COLOR_WHITE = 0;
        static final int COLOR_YELLOW = 5;
        static final int STYLE_ITALICS = 1;
        static final int STYLE_UNDERLINE = 2;
        static final String mColorMap[] = {
            "WHITE", "GREEN", "BLUE", "CYAN", "RED", "YELLOW", "MAGENTA", "INVALID"
        };
        final int mColor;
        final int mStyle;


        StyleCode(int i, int j)
        {
            mStyle = i;
            mColor = j;
        }
    }


    Cea608CCParser(DisplayListener displaylistener)
    {
        mMode = 1;
        mRollUpSize = 4;
        mPrevCtrlCode = -1;
        mDisplay = new CCMemory();
        mNonDisplay = new CCMemory();
        mTextMem = new CCMemory();
        mListener = displaylistener;
    }

    private CCMemory getMemory()
    {
        switch(mMode)
        {
        default:
            Log.w("Cea608CCParser", (new StringBuilder()).append("unrecoginized mode: ").append(mMode).toString());
            return mDisplay;

        case 3: // '\003'
            return mNonDisplay;

        case 4: // '\004'
            return mTextMem;

        case 1: // '\001'
        case 2: // '\002'
            return mDisplay;
        }
    }

    private boolean handleCtrlCode(CCData ccdata)
    {
        int i;
        i = ccdata.getCtrlCode();
        if(mPrevCtrlCode != -1 && mPrevCtrlCode == i)
        {
            mPrevCtrlCode = -1;
            return true;
        }
        i;
        JVM INSTR tableswitch 32 47: default 108
    //                   32 115
    //                   33 127
    //                   34 108
    //                   35 108
    //                   36 137
    //                   37 147
    //                   38 147
    //                   39 147
    //                   40 185
    //                   41 196
    //                   42 204
    //                   43 219
    //                   44 227
    //                   45 241
    //                   46 285
    //                   47 295;
           goto _L1 _L2 _L3 _L1 _L1 _L4 _L5 _L5 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L1:
        mPrevCtrlCode = -1;
        return false;
_L2:
        mMode = 3;
_L15:
        mPrevCtrlCode = i;
        return true;
_L3:
        getMemory().bs();
        continue; /* Loop/switch isn't completed */
_L4:
        getMemory().der();
        continue; /* Loop/switch isn't completed */
_L5:
        mRollUpSize = i - 35;
        if(mMode != 2)
        {
            mDisplay.erase();
            mNonDisplay.erase();
        }
        mMode = 2;
        continue; /* Loop/switch isn't completed */
_L6:
        Log.i("Cea608CCParser", "Flash On");
        continue; /* Loop/switch isn't completed */
_L7:
        mMode = 1;
        continue; /* Loop/switch isn't completed */
_L8:
        mMode = 4;
        mTextMem.erase();
        continue; /* Loop/switch isn't completed */
_L9:
        mMode = 4;
        continue; /* Loop/switch isn't completed */
_L10:
        mDisplay.erase();
        updateDisplay();
        continue; /* Loop/switch isn't completed */
_L11:
        if(mMode == 2)
            getMemory().rollUp(mRollUpSize);
        else
            getMemory().cr();
        if(mMode == 2)
            updateDisplay();
        continue; /* Loop/switch isn't completed */
_L12:
        mNonDisplay.erase();
        continue; /* Loop/switch isn't completed */
_L13:
        swapMemory();
        mMode = 3;
        updateDisplay();
        if(true) goto _L15; else goto _L14
_L14:
    }

    private boolean handleDisplayableChars(CCData ccdata)
    {
        if(!ccdata.isDisplayableChar())
            return false;
        if(CCData._2D_wrap0(ccdata))
            getMemory().bs();
        getMemory().writeText(ccdata.getDisplayText());
        if(mMode == 1 || mMode == 2)
            updateDisplay();
        return true;
    }

    private boolean handleMidRowCode(CCData ccdata)
    {
        ccdata = ccdata.getMidRow();
        if(ccdata != null)
        {
            getMemory().writeMidRowCode(ccdata);
            return true;
        } else
        {
            return false;
        }
    }

    private boolean handlePACCode(CCData ccdata)
    {
        ccdata = ccdata.getPAC();
        if(ccdata != null)
        {
            if(mMode == 2)
                CCMemory._2D_wrap0(getMemory(), ccdata.getRow(), mRollUpSize);
            getMemory().writePAC(ccdata);
            return true;
        } else
        {
            return false;
        }
    }

    private boolean handleTabOffsets(CCData ccdata)
    {
        int i = ccdata.getTabOffset();
        if(i > 0)
        {
            getMemory().tab(i);
            return true;
        } else
        {
            return false;
        }
    }

    private void swapMemory()
    {
        CCMemory ccmemory = mDisplay;
        mDisplay = mNonDisplay;
        mNonDisplay = ccmemory;
    }

    private void updateDisplay()
    {
        if(mListener != null)
        {
            android.view.accessibility.CaptioningManager.CaptionStyle captionstyle = mListener.getCaptionStyle();
            mListener.onDisplayChanged(mDisplay.getStyledText(captionstyle));
        }
    }

    public void parse(byte abyte0[])
    {
        abyte0 = CCData.fromByteArray(abyte0);
        int i = 0;
        while(i < abyte0.length) 
        {
            if(DEBUG)
                Log.d("Cea608CCParser", abyte0[i].toString());
            if(!handleCtrlCode(abyte0[i]) && !handleTabOffsets(abyte0[i]) && !handlePACCode(abyte0[i]) && !handleMidRowCode(abyte0[i]))
                handleDisplayableChars(abyte0[i]);
            i++;
        }
    }

    private static final int AOF = 34;
    private static final int AON = 35;
    private static final int BS = 33;
    private static final int CR = 45;
    private static final boolean DEBUG = Log.isLoggable("Cea608CCParser", 3);
    private static final int DER = 36;
    private static final int EDM = 44;
    private static final int ENM = 46;
    private static final int EOC = 47;
    private static final int FON = 40;
    private static final int INVALID = -1;
    public static final int MAX_COLS = 32;
    public static final int MAX_ROWS = 15;
    private static final int MODE_PAINT_ON = 1;
    private static final int MODE_POP_ON = 3;
    private static final int MODE_ROLL_UP = 2;
    private static final int MODE_TEXT = 4;
    private static final int MODE_UNKNOWN = 0;
    private static final int RCL = 32;
    private static final int RDC = 41;
    private static final int RTD = 43;
    private static final int RU2 = 37;
    private static final int RU3 = 38;
    private static final int RU4 = 39;
    private static final String TAG = "Cea608CCParser";
    private static final int TR = 42;
    private static final char TS = 160;
    private CCMemory mDisplay;
    private final DisplayListener mListener;
    private int mMode;
    private CCMemory mNonDisplay;
    private int mPrevCtrlCode;
    private int mRollUpSize;
    private CCMemory mTextMem;

}
