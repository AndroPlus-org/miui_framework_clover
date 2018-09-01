// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.inputmethodservice;

import android.content.Context;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.*;
import java.io.IOException;
import java.util.*;
import org.xmlpull.v1.XmlPullParserException;

public class Keyboard
{
    public static class Key
    {

        public int[] getCurrentDrawableState()
        {
            int ai[] = KEY_STATE_NORMAL;
            if(!on) goto _L2; else goto _L1
_L1:
            if(pressed)
                ai = KEY_STATE_PRESSED_ON;
            else
                ai = KEY_STATE_NORMAL_ON;
_L4:
            return ai;
_L2:
            if(sticky)
            {
                if(pressed)
                    ai = KEY_STATE_PRESSED_OFF;
                else
                    ai = KEY_STATE_NORMAL_OFF;
            } else
            if(pressed)
                ai = KEY_STATE_PRESSED;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public boolean isInside(int i, int j)
        {
            boolean flag;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            if((edgeFlags & 1) > 0)
                flag = true;
            else
                flag = false;
            if((edgeFlags & 2) > 0)
                flag1 = true;
            else
                flag1 = false;
            if((edgeFlags & 4) > 0)
                flag2 = true;
            else
                flag2 = false;
            if((edgeFlags & 8) > 0)
                flag3 = true;
            else
                flag3 = false;
            return (i >= x || flag && i <= x + width) && (i < x + width || flag1 && i >= x) && (j >= y || flag2 && j <= y + height) && (j < y + height || flag3 && j >= y);
        }

        public void onPressed()
        {
            pressed = pressed ^ true;
        }

        public void onReleased(boolean flag)
        {
            pressed = pressed ^ true;
            if(sticky && flag)
                on = on ^ true;
        }

        int[] parseCSV(String s)
        {
            int i = 0;
            int j = 0;
            if(s.length() > 0)
            {
                boolean flag = true;
                i = j;
                j = ((flag) ? 1 : 0);
                do
                {
                    int k = s.indexOf(",", i + 1);
                    i = j;
                    if(k <= 0)
                        break;
                    j++;
                    i = k;
                } while(true);
            }
            int ai[] = new int[i];
            j = 0;
            StringTokenizer stringtokenizer = new StringTokenizer(s, ",");
            while(stringtokenizer.hasMoreTokens()) 
            {
                try
                {
                    ai[j] = Integer.parseInt(stringtokenizer.nextToken());
                }
                catch(NumberFormatException numberformatexception)
                {
                    Log.e("Keyboard", (new StringBuilder()).append("Error parsing keycodes ").append(s).toString());
                }
                j++;
            }
            return ai;
        }

        public int squaredDistanceFrom(int i, int j)
        {
            i = (x + width / 2) - i;
            j = (y + height / 2) - j;
            return i * i + j * j;
        }

        private static final int KEY_STATE_NORMAL[] = new int[0];
        private static final int KEY_STATE_NORMAL_OFF[] = {
            0x101009f
        };
        private static final int KEY_STATE_NORMAL_ON[] = {
            0x101009f, 0x10100a0
        };
        private static final int KEY_STATE_PRESSED[] = {
            0x10100a7
        };
        private static final int KEY_STATE_PRESSED_OFF[] = {
            0x10100a7, 0x101009f
        };
        private static final int KEY_STATE_PRESSED_ON[] = {
            0x10100a7, 0x101009f, 0x10100a0
        };
        public int codes[];
        public int edgeFlags;
        public int gap;
        public int height;
        public Drawable icon;
        public Drawable iconPreview;
        private Keyboard keyboard;
        public CharSequence label;
        public boolean modifier;
        public boolean on;
        public CharSequence popupCharacters;
        public int popupResId;
        public boolean pressed;
        public boolean repeatable;
        public boolean sticky;
        public CharSequence text;
        public int width;
        public int x;
        public int y;


        public Key(Resources resources, Row row, int i, int j, XmlResourceParser xmlresourceparser)
        {
            this(row);
            x = i;
            y = j;
            TypedArray typedarray = resources.obtainAttributes(Xml.asAttributeSet(xmlresourceparser), com.android.internal.R.styleable.Keyboard);
            width = Keyboard.getDimensionOrFraction(typedarray, 0, Keyboard._2D_get5(keyboard), row.defaultWidth);
            height = Keyboard.getDimensionOrFraction(typedarray, 1, Keyboard._2D_get4(keyboard), row.defaultHeight);
            gap = Keyboard.getDimensionOrFraction(typedarray, 2, Keyboard._2D_get5(keyboard), row.defaultHorizontalGap);
            typedarray.recycle();
            resources = resources.obtainAttributes(Xml.asAttributeSet(xmlresourceparser), com.android.internal.R.styleable.Keyboard_Key);
            x = x + gap;
            xmlresourceparser = new TypedValue();
            resources.getValue(0, xmlresourceparser);
            if(((TypedValue) (xmlresourceparser)).type != 16 && ((TypedValue) (xmlresourceparser)).type != 17) goto _L2; else goto _L1
_L1:
            codes = (new int[] {
                ((TypedValue) (xmlresourceparser)).data
            });
_L4:
            iconPreview = resources.getDrawable(7);
            if(iconPreview != null)
                iconPreview.setBounds(0, 0, iconPreview.getIntrinsicWidth(), iconPreview.getIntrinsicHeight());
            popupCharacters = resources.getText(2);
            popupResId = resources.getResourceId(1, 0);
            repeatable = resources.getBoolean(6, false);
            modifier = resources.getBoolean(4, false);
            sticky = resources.getBoolean(5, false);
            edgeFlags = resources.getInt(3, 0);
            edgeFlags = edgeFlags | row.rowEdgeFlags;
            icon = resources.getDrawable(10);
            if(icon != null)
                icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
            label = resources.getText(9);
            text = resources.getText(8);
            if(codes == null && TextUtils.isEmpty(label) ^ true)
                codes = (new int[] {
                    label.charAt(0)
                });
            resources.recycle();
            return;
_L2:
            if(((TypedValue) (xmlresourceparser)).type == 3)
                codes = parseCSV(((TypedValue) (xmlresourceparser)).string.toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public Key(Row row)
        {
            keyboard = Row._2D_get0(row);
            height = row.defaultHeight;
            width = row.defaultWidth;
            gap = row.defaultHorizontalGap;
            edgeFlags = row.rowEdgeFlags;
        }
    }

    public static class Row
    {

        static Keyboard _2D_get0(Row row)
        {
            return row.parent;
        }

        public int defaultHeight;
        public int defaultHorizontalGap;
        public int defaultWidth;
        ArrayList mKeys;
        public int mode;
        private Keyboard parent;
        public int rowEdgeFlags;
        public int verticalGap;

        public Row(Resources resources, Keyboard keyboard, XmlResourceParser xmlresourceparser)
        {
            mKeys = new ArrayList();
            parent = keyboard;
            TypedArray typedarray = resources.obtainAttributes(Xml.asAttributeSet(xmlresourceparser), com.android.internal.R.styleable.Keyboard);
            defaultWidth = Keyboard.getDimensionOrFraction(typedarray, 0, Keyboard._2D_get5(keyboard), Keyboard._2D_get3(keyboard));
            defaultHeight = Keyboard.getDimensionOrFraction(typedarray, 1, Keyboard._2D_get4(keyboard), Keyboard._2D_get0(keyboard));
            defaultHorizontalGap = Keyboard.getDimensionOrFraction(typedarray, 2, Keyboard._2D_get5(keyboard), Keyboard._2D_get1(keyboard));
            verticalGap = Keyboard.getDimensionOrFraction(typedarray, 3, Keyboard._2D_get4(keyboard), Keyboard._2D_get2(keyboard));
            typedarray.recycle();
            resources = resources.obtainAttributes(Xml.asAttributeSet(xmlresourceparser), com.android.internal.R.styleable.Keyboard_Row);
            rowEdgeFlags = resources.getInt(0, 0);
            mode = resources.getResourceId(1, 0);
        }

        public Row(Keyboard keyboard)
        {
            mKeys = new ArrayList();
            parent = keyboard;
        }
    }


    static int _2D_get0(Keyboard keyboard)
    {
        return keyboard.mDefaultHeight;
    }

    static int _2D_get1(Keyboard keyboard)
    {
        return keyboard.mDefaultHorizontalGap;
    }

    static int _2D_get2(Keyboard keyboard)
    {
        return keyboard.mDefaultVerticalGap;
    }

    static int _2D_get3(Keyboard keyboard)
    {
        return keyboard.mDefaultWidth;
    }

    static int _2D_get4(Keyboard keyboard)
    {
        return keyboard.mDisplayHeight;
    }

    static int _2D_get5(Keyboard keyboard)
    {
        return keyboard.mDisplayWidth;
    }

    public Keyboard(Context context, int i)
    {
        this(context, i, 0);
    }

    public Keyboard(Context context, int i, int j)
    {
        rows = new ArrayList();
        DisplayMetrics displaymetrics = context.getResources().getDisplayMetrics();
        mDisplayWidth = displaymetrics.widthPixels;
        mDisplayHeight = displaymetrics.heightPixels;
        mDefaultHorizontalGap = 0;
        mDefaultWidth = mDisplayWidth / 10;
        mDefaultVerticalGap = 0;
        mDefaultHeight = mDefaultWidth;
        mKeys = new ArrayList();
        mModifierKeys = new ArrayList();
        mKeyboardMode = j;
        loadKeyboard(context, context.getResources().getXml(i));
    }

    public Keyboard(Context context, int i, int j, int k, int l)
    {
        rows = new ArrayList();
        mDisplayWidth = k;
        mDisplayHeight = l;
        mDefaultHorizontalGap = 0;
        mDefaultWidth = mDisplayWidth / 10;
        mDefaultVerticalGap = 0;
        mDefaultHeight = mDefaultWidth;
        mKeys = new ArrayList();
        mModifierKeys = new ArrayList();
        mKeyboardMode = j;
        loadKeyboard(context, context.getResources().getXml(i));
    }

    public Keyboard(Context context, int i, CharSequence charsequence, int j, int k)
    {
        this(context, i);
        i = 0;
        int l = 0;
        boolean flag = false;
        mTotalWidth = 0;
        Row row = new Row(this);
        row.defaultHeight = mDefaultHeight;
        row.defaultWidth = mDefaultWidth;
        row.defaultHorizontalGap = mDefaultHorizontalGap;
        row.verticalGap = mDefaultVerticalGap;
        row.rowEdgeFlags = 12;
        int j1;
        int k1;
        if(j == -1)
            j1 = 0x7fffffff;
        else
            j1 = j;
        k1 = 0;
        j = ((flag) ? 1 : 0);
        while(k1 < charsequence.length()) 
        {
            int i1;
            char c;
            int l1;
label0:
            {
                c = charsequence.charAt(k1);
                if(j < j1)
                {
                    l1 = j;
                    j = i;
                    i1 = l;
                    if(mDefaultWidth + i + k <= mDisplayWidth)
                        break label0;
                }
                j = 0;
                i1 = l + (mDefaultVerticalGap + mDefaultHeight);
                l1 = 0;
            }
            context = new Key(row);
            context.x = j;
            context.y = i1;
            context.label = String.valueOf(c);
            context.codes = (new int[] {
                c
            });
            i = l1 + 1;
            l = j + (((Key) (context)).width + ((Key) (context)).gap);
            mKeys.add(context);
            row.mKeys.add(context);
            if(l > mTotalWidth)
                mTotalWidth = l;
            k1++;
            j = i;
            i = l;
            l = i1;
        }
        mTotalHeight = mDefaultHeight + l;
        rows.add(row);
    }

    private void computeNearestNeighbors()
    {
        int ai[];
        int i;
        int j;
        int k;
        mCellWidth = ((getMinWidth() + 10) - 1) / 10;
        mCellHeight = ((getHeight() + 5) - 1) / 5;
        mGridNeighbors = new int[50][];
        ai = new int[mKeys.size()];
        i = mCellWidth;
        j = mCellHeight;
        k = 0;
_L12:
        if(k >= i * 10) goto _L2; else goto _L1
_L1:
        int l = 0;
_L11:
        int i1;
        int j1;
        if(l >= j * 5)
            continue; /* Loop/switch isn't completed */
        i1 = 0;
        j1 = 0;
_L6:
        if(j1 >= mKeys.size()) goto _L4; else goto _L3
_L3:
        Key key = (Key)mKeys.get(j1);
          goto _L5
_L8:
        int k1;
        ai[i1] = j1;
        k1 = i1 + 1;
_L9:
        j1++;
        i1 = k1;
          goto _L6
_L5:
        if(key.squaredDistanceFrom(k, l) < mProximityThreshold || key.squaredDistanceFrom((mCellWidth + k) - 1, l) < mProximityThreshold || key.squaredDistanceFrom((mCellWidth + k) - 1, (mCellHeight + l) - 1) < mProximityThreshold) goto _L8; else goto _L7
_L7:
        k1 = i1;
        if(key.squaredDistanceFrom(k, (mCellHeight + l) - 1) >= mProximityThreshold) goto _L9; else goto _L8
_L4:
        int ai1[] = new int[i1];
        System.arraycopy(ai, 0, ai1, 0, i1);
        mGridNeighbors[(l / mCellHeight) * 10 + k / mCellWidth] = ai1;
        l += mCellHeight;
        if(true) goto _L11; else goto _L10
_L10:
        k += mCellWidth;
          goto _L12
_L2:
    }

    static int getDimensionOrFraction(TypedArray typedarray, int i, int j, int k)
    {
        TypedValue typedvalue = typedarray.peekValue(i);
        if(typedvalue == null)
            return k;
        if(typedvalue.type == 5)
            return typedarray.getDimensionPixelOffset(i, k);
        if(typedvalue.type == 6)
            return Math.round(typedarray.getFraction(i, j, j, k));
        else
            return k;
    }

    private void loadKeyboard(Context context, XmlResourceParser xmlresourceparser)
    {
        int i;
        boolean flag;
        int j;
        int k;
        int l;
        Object obj2;
        Resources resources;
        i = 0;
        flag = false;
        j = 0;
        k = 0;
        l = 0;
        Object obj = null;
        obj2 = null;
        resources = context.getResources();
        context = obj;
_L21:
        int i1 = l;
        int j1 = xmlresourceparser.next();
        i1 = l;
        if(j1 == 1) goto _L2; else goto _L1
_L1:
        if(j1 != 2)
            break MISSING_BLOCK_LABEL_458;
        i1 = l;
        Object obj1 = xmlresourceparser.getName();
        i1 = l;
        if(!"Row".equals(obj1)) goto _L4; else goto _L3
_L3:
        flag = true;
        j1 = 0;
        i1 = l;
        obj1 = createRowFromXml(resources, xmlresourceparser);
        i1 = l;
        rows.add(obj1);
        i1 = l;
        if(((Row) (obj1)).mode == 0) goto _L6; else goto _L5
_L5:
        i1 = l;
        if(((Row) (obj1)).mode == mKeyboardMode) goto _L6; else goto _L7
_L7:
        i1 = 1;
_L8:
        obj2 = obj1;
        k = j1;
        if(i1 == 0)
            continue; /* Loop/switch isn't completed */
        i1 = l;
        skipToEndOfRow(xmlresourceparser);
        flag = false;
        obj2 = obj1;
        k = j1;
        continue; /* Loop/switch isn't completed */
_L6:
        i1 = 0;
        if(true) goto _L8; else goto _L4
_L4:
        i1 = l;
        if(!"Key".equals(obj1)) goto _L10; else goto _L9
_L9:
        j1 = 1;
        i1 = l;
        context = createKeyFromXml(resources, ((Row) (obj2)), k, l, xmlresourceparser);
        i1 = l;
        mKeys.add(context);
        i1 = l;
        if(((Key) (context)).codes[0] != -1) goto _L12; else goto _L11
_L11:
        i = 0;
_L17:
        i1 = l;
        if(i >= mShiftKeys.length) goto _L14; else goto _L13
_L13:
        i1 = l;
        if(mShiftKeys[i] != null) goto _L16; else goto _L15
_L15:
        i1 = l;
        mShiftKeys[i] = context;
        i1 = l;
        mShiftKeyIndices[i] = mKeys.size() - 1;
_L14:
        i1 = l;
        mModifierKeys.add(context);
_L19:
        i1 = l;
        ((Row) (obj2)).mKeys.add(context);
        i = j1;
        continue; /* Loop/switch isn't completed */
_L2:
        mTotalHeight = i1 - mDefaultVerticalGap;
        return;
_L16:
        i++;
          goto _L17
_L12:
        i1 = l;
        if(((Key) (context)).codes[0] != -6) goto _L19; else goto _L18
_L18:
        i1 = l;
        mModifierKeys.add(context);
          goto _L19
_L10:
        i1 = l;
        if(!"Keyboard".equals(obj1))
            continue; /* Loop/switch isn't completed */
        i1 = l;
        try
        {
            parseKeyboardAttributes(resources, xmlresourceparser);
            continue; /* Loop/switch isn't completed */
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("Keyboard", (new StringBuilder()).append("Parse error:").append(context).toString());
            context.printStackTrace();
        }
          goto _L2
        boolean flag1;
        if(j1 != 3)
            continue; /* Loop/switch isn't completed */
        if(i == 0)
            break MISSING_BLOCK_LABEL_529;
        flag1 = false;
        i1 = l;
        j1 = k + (((Key) (context)).gap + ((Key) (context)).width);
        k = j1;
        i = ((flag1) ? 1 : 0);
        i1 = l;
        if(j1 <= mTotalWidth)
            continue; /* Loop/switch isn't completed */
        i1 = l;
        mTotalWidth = j1;
        k = j1;
        i = ((flag1) ? 1 : 0);
        continue; /* Loop/switch isn't completed */
        if(!flag)
            continue; /* Loop/switch isn't completed */
        flag = false;
        i1 = l;
        l += ((Row) (obj2)).verticalGap;
        i1 = l;
        j1 = ((Row) (obj2)).defaultHeight;
        l += j1;
        j++;
        if(true) goto _L21; else goto _L20
_L20:
    }

    private void parseKeyboardAttributes(Resources resources, XmlResourceParser xmlresourceparser)
    {
        resources = resources.obtainAttributes(Xml.asAttributeSet(xmlresourceparser), com.android.internal.R.styleable.Keyboard);
        mDefaultWidth = getDimensionOrFraction(resources, 0, mDisplayWidth, mDisplayWidth / 10);
        mDefaultHeight = getDimensionOrFraction(resources, 1, mDisplayHeight, 50);
        mDefaultHorizontalGap = getDimensionOrFraction(resources, 2, mDisplayWidth, 0);
        mDefaultVerticalGap = getDimensionOrFraction(resources, 3, mDisplayHeight, 0);
        mProximityThreshold = (int)((float)mDefaultWidth * SEARCH_DISTANCE);
        mProximityThreshold = mProximityThreshold * mProximityThreshold;
        resources.recycle();
    }

    private void skipToEndOfRow(XmlResourceParser xmlresourceparser)
        throws XmlPullParserException, IOException
    {
        int i;
        do
            i = xmlresourceparser.next();
        while(i != 1 && (i != 3 || !xmlresourceparser.getName().equals("Row")));
    }

    protected Key createKeyFromXml(Resources resources, Row row, int i, int j, XmlResourceParser xmlresourceparser)
    {
        return new Key(resources, row, i, j, xmlresourceparser);
    }

    protected Row createRowFromXml(Resources resources, XmlResourceParser xmlresourceparser)
    {
        return new Row(resources, this, xmlresourceparser);
    }

    public int getHeight()
    {
        return mTotalHeight;
    }

    protected int getHorizontalGap()
    {
        return mDefaultHorizontalGap;
    }

    protected int getKeyHeight()
    {
        return mDefaultHeight;
    }

    protected int getKeyWidth()
    {
        return mDefaultWidth;
    }

    public List getKeys()
    {
        return mKeys;
    }

    public int getMinWidth()
    {
        return mTotalWidth;
    }

    public List getModifierKeys()
    {
        return mModifierKeys;
    }

    public int[] getNearestKeys(int i, int j)
    {
        if(mGridNeighbors == null)
            computeNearestNeighbors();
        if(i >= 0 && i < getMinWidth() && j >= 0 && j < getHeight())
        {
            i = (j / mCellHeight) * 10 + i / mCellWidth;
            if(i < 50)
                return mGridNeighbors[i];
        }
        return new int[0];
    }

    public int getShiftKeyIndex()
    {
        return mShiftKeyIndices[0];
    }

    public int[] getShiftKeyIndices()
    {
        return mShiftKeyIndices;
    }

    protected int getVerticalGap()
    {
        return mDefaultVerticalGap;
    }

    public boolean isShifted()
    {
        return mShifted;
    }

    final void resize(int i, int j)
    {
        int k = rows.size();
        for(j = 0; j < k; j++)
        {
            Row row = (Row)rows.get(j);
            int l = row.mKeys.size();
            int i1 = 0;
            int j1 = 0;
            for(int k1 = 0; k1 < l;)
            {
                Key key = (Key)row.mKeys.get(k1);
                int l1 = i1;
                if(k1 > 0)
                    l1 = i1 + key.gap;
                j1 += key.width;
                k1++;
                i1 = l1;
            }

            if(i1 + j1 <= i)
                continue;
            boolean flag = false;
            float f = (float)(i - i1) / (float)j1;
            j1 = 0;
            i1 = ((flag) ? 1 : 0);
            for(; j1 < l; j1++)
            {
                Key key1 = (Key)row.mKeys.get(j1);
                key1.width = (int)((float)key1.width * f);
                key1.x = i1;
                i1 += key1.width + key1.gap;
            }

        }

        mTotalWidth = i;
    }

    protected void setHorizontalGap(int i)
    {
        mDefaultHorizontalGap = i;
    }

    protected void setKeyHeight(int i)
    {
        mDefaultHeight = i;
    }

    protected void setKeyWidth(int i)
    {
        mDefaultWidth = i;
    }

    public boolean setShifted(boolean flag)
    {
        Key akey[] = mShiftKeys;
        int i = akey.length;
        for(int j = 0; j < i; j++)
        {
            Key key = akey[j];
            if(key != null)
                key.on = flag;
        }

        if(mShifted != flag)
        {
            mShifted = flag;
            return true;
        } else
        {
            return false;
        }
    }

    protected void setVerticalGap(int i)
    {
        mDefaultVerticalGap = i;
    }

    public static final int EDGE_BOTTOM = 8;
    public static final int EDGE_LEFT = 1;
    public static final int EDGE_RIGHT = 2;
    public static final int EDGE_TOP = 4;
    private static final int GRID_HEIGHT = 5;
    private static final int GRID_SIZE = 50;
    private static final int GRID_WIDTH = 10;
    public static final int KEYCODE_ALT = -6;
    public static final int KEYCODE_CANCEL = -3;
    public static final int KEYCODE_DELETE = -5;
    public static final int KEYCODE_DONE = -4;
    public static final int KEYCODE_MODE_CHANGE = -2;
    public static final int KEYCODE_SHIFT = -1;
    private static float SEARCH_DISTANCE = 0F;
    static final String TAG = "Keyboard";
    private static final String TAG_KEY = "Key";
    private static final String TAG_KEYBOARD = "Keyboard";
    private static final String TAG_ROW = "Row";
    private int mCellHeight;
    private int mCellWidth;
    private int mDefaultHeight;
    private int mDefaultHorizontalGap;
    private int mDefaultVerticalGap;
    private int mDefaultWidth;
    private int mDisplayHeight;
    private int mDisplayWidth;
    private int mGridNeighbors[][];
    private int mKeyHeight;
    private int mKeyWidth;
    private int mKeyboardMode;
    private List mKeys;
    private CharSequence mLabel;
    private List mModifierKeys;
    private int mProximityThreshold;
    private int mShiftKeyIndices[] = {
        -1, -1
    };
    private Key mShiftKeys[] = {
        null, null
    };
    private boolean mShifted;
    private int mTotalHeight;
    private int mTotalWidth;
    private ArrayList rows;

    static 
    {
        SEARCH_DISTANCE = 1.8F;
    }
}
