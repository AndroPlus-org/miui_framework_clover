// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.graphics.Color;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

class Cea708CCParser
{
    public static class CaptionColor
    {

        public int getArgbValue()
        {
            return Color.argb(OPACITY_MAP[opacity], COLOR_MAP[red], COLOR_MAP[green], COLOR_MAP[blue]);
        }

        private static final int COLOR_MAP[] = {
            0, 15, 240, 255
        };
        public static final int OPACITY_FLASH = 1;
        private static final int OPACITY_MAP[] = {
            255, 254, 128, 0
        };
        public static final int OPACITY_SOLID = 0;
        public static final int OPACITY_TRANSLUCENT = 2;
        public static final int OPACITY_TRANSPARENT = 3;
        public final int blue;
        public final int green;
        public final int opacity;
        public final int red;


        public CaptionColor(int i, int j, int k, int l)
        {
            opacity = i;
            red = j;
            green = k;
            blue = l;
        }
    }

    public static class CaptionEvent
    {

        public final Object obj;
        public final int type;

        public CaptionEvent(int i, Object obj1)
        {
            type = i;
            obj = obj1;
        }
    }

    public static class CaptionPenAttr
    {

        public static final int OFFSET_NORMAL = 1;
        public static final int OFFSET_SUBSCRIPT = 0;
        public static final int OFFSET_SUPERSCRIPT = 2;
        public static final int PEN_SIZE_LARGE = 2;
        public static final int PEN_SIZE_SMALL = 0;
        public static final int PEN_SIZE_STANDARD = 1;
        public final int edgeType;
        public final int fontTag;
        public final boolean italic;
        public final int penOffset;
        public final int penSize;
        public final int textTag;
        public final boolean underline;

        public CaptionPenAttr(int i, int j, int k, int l, int i1, boolean flag, boolean flag1)
        {
            penSize = i;
            penOffset = j;
            textTag = k;
            fontTag = l;
            edgeType = i1;
            underline = flag;
            italic = flag1;
        }
    }

    public static class CaptionPenColor
    {

        public final CaptionColor backgroundColor;
        public final CaptionColor edgeColor;
        public final CaptionColor foregroundColor;

        public CaptionPenColor(CaptionColor captioncolor, CaptionColor captioncolor1, CaptionColor captioncolor2)
        {
            foregroundColor = captioncolor;
            backgroundColor = captioncolor1;
            edgeColor = captioncolor2;
        }
    }

    public static class CaptionPenLocation
    {

        public final int column;
        public final int row;

        public CaptionPenLocation(int i, int j)
        {
            row = i;
            column = j;
        }
    }

    public static class CaptionWindow
    {

        public final int anchorHorizontal;
        public final int anchorId;
        public final int anchorVertical;
        public final int columnCount;
        public final boolean columnLock;
        public final int id;
        public final int penStyle;
        public final int priority;
        public final boolean relativePositioning;
        public final int rowCount;
        public final boolean rowLock;
        public final boolean visible;
        public final int windowStyle;

        public CaptionWindow(int i, boolean flag, boolean flag1, boolean flag2, int j, boolean flag3, int k, 
                int l, int i1, int j1, int k1, int l1, int i2)
        {
            id = i;
            visible = flag;
            rowLock = flag1;
            columnLock = flag2;
            priority = j;
            relativePositioning = flag3;
            anchorVertical = k;
            anchorHorizontal = l;
            anchorId = i1;
            rowCount = j1;
            columnCount = k1;
            penStyle = l1;
            windowStyle = i2;
        }
    }

    public static class CaptionWindowAttr
    {

        public final CaptionColor borderColor;
        public final int borderType;
        public final int displayEffect;
        public final int effectDirection;
        public final int effectSpeed;
        public final CaptionColor fillColor;
        public final int justify;
        public final int printDirection;
        public final int scrollDirection;
        public final boolean wordWrap;

        public CaptionWindowAttr(CaptionColor captioncolor, CaptionColor captioncolor1, int i, boolean flag, int j, int k, int l, 
                int i1, int j1, int k1)
        {
            fillColor = captioncolor;
            borderColor = captioncolor1;
            borderType = i;
            wordWrap = flag;
            printDirection = j;
            scrollDirection = k;
            justify = l;
            effectDirection = i1;
            effectSpeed = j1;
            displayEffect = k1;
        }
    }

    private static class Const
    {

        public static final int CODE_C0_BS = 8;
        public static final int CODE_C0_CR = 13;
        public static final int CODE_C0_ETX = 3;
        public static final int CODE_C0_EXT1 = 16;
        public static final int CODE_C0_FF = 12;
        public static final int CODE_C0_HCR = 14;
        public static final int CODE_C0_NUL = 0;
        public static final int CODE_C0_P16 = 24;
        public static final int CODE_C0_RANGE_END = 31;
        public static final int CODE_C0_RANGE_START = 0;
        public static final int CODE_C0_SKIP1_RANGE_END = 23;
        public static final int CODE_C0_SKIP1_RANGE_START = 16;
        public static final int CODE_C0_SKIP2_RANGE_END = 31;
        public static final int CODE_C0_SKIP2_RANGE_START = 24;
        public static final int CODE_C1_CLW = 136;
        public static final int CODE_C1_CW0 = 128;
        public static final int CODE_C1_CW1 = 129;
        public static final int CODE_C1_CW2 = 130;
        public static final int CODE_C1_CW3 = 131;
        public static final int CODE_C1_CW4 = 132;
        public static final int CODE_C1_CW5 = 133;
        public static final int CODE_C1_CW6 = 134;
        public static final int CODE_C1_CW7 = 135;
        public static final int CODE_C1_DF0 = 152;
        public static final int CODE_C1_DF1 = 153;
        public static final int CODE_C1_DF2 = 154;
        public static final int CODE_C1_DF3 = 155;
        public static final int CODE_C1_DF4 = 156;
        public static final int CODE_C1_DF5 = 157;
        public static final int CODE_C1_DF6 = 158;
        public static final int CODE_C1_DF7 = 159;
        public static final int CODE_C1_DLC = 142;
        public static final int CODE_C1_DLW = 140;
        public static final int CODE_C1_DLY = 141;
        public static final int CODE_C1_DSW = 137;
        public static final int CODE_C1_HDW = 138;
        public static final int CODE_C1_RANGE_END = 159;
        public static final int CODE_C1_RANGE_START = 128;
        public static final int CODE_C1_RST = 143;
        public static final int CODE_C1_SPA = 144;
        public static final int CODE_C1_SPC = 145;
        public static final int CODE_C1_SPL = 146;
        public static final int CODE_C1_SWA = 151;
        public static final int CODE_C1_TGW = 139;
        public static final int CODE_C2_RANGE_END = 31;
        public static final int CODE_C2_RANGE_START = 0;
        public static final int CODE_C2_SKIP0_RANGE_END = 7;
        public static final int CODE_C2_SKIP0_RANGE_START = 0;
        public static final int CODE_C2_SKIP1_RANGE_END = 15;
        public static final int CODE_C2_SKIP1_RANGE_START = 8;
        public static final int CODE_C2_SKIP2_RANGE_END = 23;
        public static final int CODE_C2_SKIP2_RANGE_START = 16;
        public static final int CODE_C2_SKIP3_RANGE_END = 31;
        public static final int CODE_C2_SKIP3_RANGE_START = 24;
        public static final int CODE_C3_RANGE_END = 159;
        public static final int CODE_C3_RANGE_START = 128;
        public static final int CODE_C3_SKIP4_RANGE_END = 135;
        public static final int CODE_C3_SKIP4_RANGE_START = 128;
        public static final int CODE_C3_SKIP5_RANGE_END = 143;
        public static final int CODE_C3_SKIP5_RANGE_START = 136;
        public static final int CODE_G0_MUSICNOTE = 127;
        public static final int CODE_G0_RANGE_END = 127;
        public static final int CODE_G0_RANGE_START = 32;
        public static final int CODE_G1_RANGE_END = 255;
        public static final int CODE_G1_RANGE_START = 160;
        public static final int CODE_G2_BLK = 48;
        public static final int CODE_G2_NBTSP = 33;
        public static final int CODE_G2_RANGE_END = 127;
        public static final int CODE_G2_RANGE_START = 32;
        public static final int CODE_G2_TSP = 32;
        public static final int CODE_G3_CC = 160;
        public static final int CODE_G3_RANGE_END = 255;
        public static final int CODE_G3_RANGE_START = 160;

        private Const()
        {
        }
    }

    static interface DisplayListener
    {

        public abstract void emitEvent(CaptionEvent captionevent);
    }


    Cea708CCParser(DisplayListener displaylistener)
    {
        mCommand = 0;
        mListener = new DisplayListener() {

            public void emitEvent(CaptionEvent captionevent)
            {
            }

            final Cea708CCParser this$0;

            
            {
                this$0 = Cea708CCParser.this;
                super();
            }
        }
;
        if(displaylistener != null)
            mListener = displaylistener;
    }

    private void emitCaptionBuffer()
    {
        if(mBuffer.length() > 0)
        {
            mListener.emitEvent(new CaptionEvent(1, mBuffer.toString()));
            mBuffer.setLength(0);
        }
    }

    private void emitCaptionEvent(CaptionEvent captionevent)
    {
        emitCaptionBuffer();
        mListener.emitEvent(captionevent);
    }

    private int parseC0(byte abyte0[], int i)
    {
        if(mCommand < 24 || mCommand > 31) goto _L2; else goto _L1
_L1:
        if(mCommand != 24) goto _L4; else goto _L3
_L3:
        if(abyte0[i] != 0) goto _L6; else goto _L5
_L5:
        int j;
        try
        {
            mBuffer.append((char)abyte0[i + 1]);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Log.e("Cea708CCParser", "P16 Code - Could not find supported encoding", abyte0);
        }
_L4:
        j = i + 2;
_L8:
        return j;
_L6:
        String s = JVM INSTR new #83  <Class String>;
        s.String(Arrays.copyOfRange(abyte0, i, i + 2), "EUC-KR");
        mBuffer.append(s);
        continue; /* Loop/switch isn't completed */
_L2:
        if(mCommand < 16 || mCommand > 23)
            break; /* Loop/switch isn't completed */
        j = i + 1;
        if(true) goto _L8; else goto _L7
_L7:
        j = i;
        switch(mCommand)
        {
        default:
            j = i;
            break;

        case 3: // '\003'
            emitCaptionEvent(new CaptionEvent(2, Character.valueOf((char)mCommand)));
            j = i;
            break;

        case 8: // '\b'
            emitCaptionEvent(new CaptionEvent(2, Character.valueOf((char)mCommand)));
            j = i;
            break;

        case 12: // '\f'
            emitCaptionEvent(new CaptionEvent(2, Character.valueOf((char)mCommand)));
            j = i;
            break;

        case 13: // '\r'
            mBuffer.append('\n');
            j = i;
            break;

        case 14: // '\016'
            emitCaptionEvent(new CaptionEvent(2, Character.valueOf((char)mCommand)));
            j = i;
            break;

        case 0: // '\0'
            break;
        }
        if(true) goto _L8; else goto _L9
_L9:
        if(true) goto _L4; else goto _L10
_L10:
    }

    private int parseC1(byte abyte0[], int i)
    {
        int j = i;
        mCommand;
        JVM INSTR tableswitch 128 159: default 148
    //                   128 152
    //                   129 152
    //                   130 152
    //                   131 152
    //                   132 152
    //                   133 152
    //                   134 152
    //                   135 152
    //                   136 180
    //                   137 213
    //                   138 246
    //                   139 280
    //                   140 314
    //                   141 348
    //                   142 382
    //                   143 401
    //                   144 420
    //                   145 552
    //                   146 700
    //                   147 150
    //                   148 150
    //                   149 150
    //                   150 150
    //                   151 749
    //                   152 990
    //                   153 990
    //                   154 990
    //                   155 990
    //                   156 990
    //                   157 990
    //                   158 990
    //                   159 990;
           goto _L1 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L14 _L14 _L14 _L15 _L16 _L16 _L16 _L16 _L16 _L16 _L16 _L16
_L14:
        break; /* Loop/switch isn't completed */
_L1:
        j = i;
_L18:
        return j;
_L2:
        emitCaptionEvent(new CaptionEvent(3, Integer.valueOf(mCommand - 128)));
        j = i;
        continue; /* Loop/switch isn't completed */
_L3:
        byte byte0 = abyte0[i];
        j = i + 1;
        emitCaptionEvent(new CaptionEvent(4, Integer.valueOf(byte0 & 0xff)));
        continue; /* Loop/switch isn't completed */
_L4:
        byte byte1 = abyte0[i];
        j = i + 1;
        emitCaptionEvent(new CaptionEvent(5, Integer.valueOf(byte1 & 0xff)));
        continue; /* Loop/switch isn't completed */
_L5:
        byte byte2 = abyte0[i];
        j = i + 1;
        emitCaptionEvent(new CaptionEvent(6, Integer.valueOf(byte2 & 0xff)));
        continue; /* Loop/switch isn't completed */
_L6:
        byte byte3 = abyte0[i];
        j = i + 1;
        emitCaptionEvent(new CaptionEvent(7, Integer.valueOf(byte3 & 0xff)));
        continue; /* Loop/switch isn't completed */
_L7:
        byte byte4 = abyte0[i];
        j = i + 1;
        emitCaptionEvent(new CaptionEvent(8, Integer.valueOf(byte4 & 0xff)));
        continue; /* Loop/switch isn't completed */
_L8:
        byte byte5 = abyte0[i];
        j = i + 1;
        emitCaptionEvent(new CaptionEvent(9, Integer.valueOf(byte5 & 0xff)));
        continue; /* Loop/switch isn't completed */
_L9:
        emitCaptionEvent(new CaptionEvent(10, null));
        j = i;
        continue; /* Loop/switch isn't completed */
_L10:
        emitCaptionEvent(new CaptionEvent(11, null));
        j = i;
        continue; /* Loop/switch isn't completed */
_L11:
        byte byte10 = abyte0[i];
        byte byte6 = abyte0[i];
        byte byte13 = abyte0[i];
        boolean flag;
        boolean flag3;
        byte byte16;
        byte byte19;
        if((abyte0[i + 1] & 0x80) != 0)
            flag = true;
        else
            flag = false;
        if((abyte0[i + 1] & 0x40) != 0)
            flag3 = true;
        else
            flag3 = false;
        byte16 = abyte0[i + 1];
        byte19 = abyte0[i + 1];
        j = i + 2;
        emitCaptionEvent(new CaptionEvent(12, new CaptionPenAttr(byte6 & 3, (byte13 & 0xc) >> 2, (byte10 & 0xf0) >> 4, byte19 & 7, (byte16 & 0x38) >> 3, flag3, flag)));
        continue; /* Loop/switch isn't completed */
_L12:
        CaptionColor captioncolor = new CaptionColor((abyte0[i] & 0xc0) >> 6, (abyte0[i] & 0x30) >> 4, (abyte0[i] & 0xc) >> 2, abyte0[i] & 3);
        i++;
        CaptionColor captioncolor2 = new CaptionColor((abyte0[i] & 0xc0) >> 6, (abyte0[i] & 0x30) >> 4, (abyte0[i] & 0xc) >> 2, abyte0[i] & 3);
        i++;
        abyte0 = new CaptionColor(0, (abyte0[i] & 0x30) >> 4, (abyte0[i] & 0xc) >> 2, abyte0[i] & 3);
        j = i + 1;
        emitCaptionEvent(new CaptionEvent(13, new CaptionPenColor(captioncolor, captioncolor2, abyte0)));
        continue; /* Loop/switch isn't completed */
_L13:
        byte byte20 = abyte0[i];
        byte byte7 = abyte0[i + 1];
        j = i + 2;
        emitCaptionEvent(new CaptionEvent(14, new CaptionPenLocation(byte20 & 0xf, byte7 & 0x3f)));
        continue; /* Loop/switch isn't completed */
_L15:
        CaptionColor captioncolor3 = new CaptionColor((abyte0[i] & 0xc0) >> 6, (abyte0[i] & 0x30) >> 4, (abyte0[i] & 0xc) >> 2, abyte0[i] & 3);
        byte byte17 = abyte0[i + 1];
        byte byte23 = abyte0[i + 2];
        CaptionColor captioncolor1 = new CaptionColor(0, (abyte0[i + 1] & 0x30) >> 4, (abyte0[i + 1] & 0xc) >> 2, abyte0[i + 1] & 3);
        byte byte8;
        byte byte11;
        byte byte14;
        boolean flag1;
        byte byte21;
        byte byte25;
        byte byte27;
        if((abyte0[i + 2] & 0x40) != 0)
            flag1 = true;
        else
            flag1 = false;
        byte25 = abyte0[i + 2];
        byte27 = abyte0[i + 2];
        byte11 = abyte0[i + 2];
        byte21 = abyte0[i + 3];
        byte14 = abyte0[i + 3];
        byte8 = abyte0[i + 3];
        j = i + 4;
        emitCaptionEvent(new CaptionEvent(15, new CaptionWindowAttr(captioncolor3, captioncolor1, (byte17 & 0xc0) >> 6 | (byte23 & 0x80) >> 5, flag1, (byte25 & 0x30) >> 4, (byte27 & 0xc) >> 2, byte11 & 3, (byte14 & 0xc) >> 2, (byte21 & 0xf0) >> 4, byte8 & 3)));
        continue; /* Loop/switch isn't completed */
_L16:
        int k = mCommand;
        byte byte9;
        byte byte12;
        byte byte15;
        boolean flag2;
        boolean flag4;
        byte byte18;
        byte byte22;
        byte byte24;
        byte byte26;
        boolean flag5;
        boolean flag6;
        byte byte28;
        if((abyte0[i] & 0x20) != 0)
            flag2 = true;
        else
            flag2 = false;
        if((abyte0[i] & 0x10) != 0)
            flag4 = true;
        else
            flag4 = false;
        if((abyte0[i] & 8) != 0)
            flag5 = true;
        else
            flag5 = false;
        byte22 = abyte0[i];
        if((abyte0[i + 1] & 0x80) != 0)
            flag6 = true;
        else
            flag6 = false;
        byte26 = abyte0[i + 1];
        byte28 = abyte0[i + 2];
        byte18 = abyte0[i + 3];
        byte15 = abyte0[i + 3];
        byte12 = abyte0[i + 4];
        byte9 = abyte0[i + 5];
        byte24 = abyte0[i + 5];
        j = i + 6;
        emitCaptionEvent(new CaptionEvent(16, new CaptionWindow(k - 152, flag2, flag4, flag5, byte22 & 7, flag6, byte26 & 0x7f, byte28 & 0xff, (byte18 & 0xf0) >> 4, byte15 & 0xf, byte12 & 0x3f, byte24 & 7, (byte9 & 0x38) >> 3)));
        if(true) goto _L18; else goto _L17
_L17:
    }

    private int parseC2(byte abyte0[], int i)
    {
        if(mCommand < 0 || mCommand > 7) goto _L2; else goto _L1
_L1:
        int j = i;
_L4:
        return j;
_L2:
        if(mCommand >= 8 && mCommand <= 15)
            j = i + 1;
        else
        if(mCommand >= 16 && mCommand <= 23)
        {
            j = i + 2;
        } else
        {
            j = i;
            if(mCommand >= 24)
            {
                j = i;
                if(mCommand <= 31)
                    j = i + 3;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int parseC3(byte abyte0[], int i)
    {
        if(mCommand < 128 || mCommand > 135) goto _L2; else goto _L1
_L1:
        int j = i + 4;
_L4:
        return j;
_L2:
        j = i;
        if(mCommand >= 136)
        {
            j = i;
            if(mCommand <= 143)
                j = i + 5;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int parseExt1(byte abyte0[], int i)
    {
        int j;
        mCommand = abyte0[i] & 0xff;
        j = i + 1;
        if(mCommand < 0 || mCommand > 31) goto _L2; else goto _L1
_L1:
        i = parseC2(abyte0, j);
_L4:
        return i;
_L2:
        if(mCommand >= 128 && mCommand <= 159)
            i = parseC3(abyte0, j);
        else
        if(mCommand >= 32 && mCommand <= 127)
        {
            i = parseG2(abyte0, j);
        } else
        {
            i = j;
            if(mCommand >= 160)
            {
                i = j;
                if(mCommand <= 255)
                    i = parseG3(abyte0, j);
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int parseG0(byte abyte0[], int i)
    {
        if(mCommand == 127)
            mBuffer.append(MUSIC_NOTE_CHAR);
        else
            mBuffer.append((char)mCommand);
        return i;
    }

    private int parseG1(byte abyte0[], int i)
    {
        mBuffer.append((char)mCommand);
        return i;
    }

    private int parseG2(byte abyte0[], int i)
    {
        int j = mCommand;
        return i;
    }

    private int parseG3(byte abyte0[], int i)
    {
        int j = mCommand;
        return i;
    }

    private int parseServiceBlockData(byte abyte0[], int i)
    {
        int j;
        mCommand = abyte0[i] & 0xff;
        j = i + 1;
        if(mCommand != 16) goto _L2; else goto _L1
_L1:
        i = parseExt1(abyte0, j);
_L4:
        return i;
_L2:
        if(mCommand >= 0 && mCommand <= 31)
            i = parseC0(abyte0, j);
        else
        if(mCommand >= 128 && mCommand <= 159)
            i = parseC1(abyte0, j);
        else
        if(mCommand >= 32 && mCommand <= 127)
        {
            i = parseG0(abyte0, j);
        } else
        {
            i = j;
            if(mCommand >= 160)
            {
                i = j;
                if(mCommand <= 255)
                    i = parseG1(abyte0, j);
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void parse(byte abyte0[])
    {
        for(int i = 0; i < abyte0.length; i = parseServiceBlockData(abyte0, i));
        emitCaptionBuffer();
    }

    public static final int CAPTION_EMIT_TYPE_BUFFER = 1;
    public static final int CAPTION_EMIT_TYPE_COMMAND_CLW = 4;
    public static final int CAPTION_EMIT_TYPE_COMMAND_CWX = 3;
    public static final int CAPTION_EMIT_TYPE_COMMAND_DFX = 16;
    public static final int CAPTION_EMIT_TYPE_COMMAND_DLC = 10;
    public static final int CAPTION_EMIT_TYPE_COMMAND_DLW = 8;
    public static final int CAPTION_EMIT_TYPE_COMMAND_DLY = 9;
    public static final int CAPTION_EMIT_TYPE_COMMAND_DSW = 5;
    public static final int CAPTION_EMIT_TYPE_COMMAND_HDW = 6;
    public static final int CAPTION_EMIT_TYPE_COMMAND_RST = 11;
    public static final int CAPTION_EMIT_TYPE_COMMAND_SPA = 12;
    public static final int CAPTION_EMIT_TYPE_COMMAND_SPC = 13;
    public static final int CAPTION_EMIT_TYPE_COMMAND_SPL = 14;
    public static final int CAPTION_EMIT_TYPE_COMMAND_SWA = 15;
    public static final int CAPTION_EMIT_TYPE_COMMAND_TGW = 7;
    public static final int CAPTION_EMIT_TYPE_CONTROL = 2;
    private static final boolean DEBUG = false;
    private static final String MUSIC_NOTE_CHAR;
    private static final String TAG = "Cea708CCParser";
    private final StringBuffer mBuffer = new StringBuffer();
    private int mCommand;
    private DisplayListener mListener;

    static 
    {
        MUSIC_NOTE_CHAR = new String("\u266B".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    }
}
