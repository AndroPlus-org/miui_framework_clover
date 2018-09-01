// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import android.graphics.Color;
import android.util.Log;
import miui.maml.data.*;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.util:
//            StyleHelper, Utils

public class ColorParser
{
    private static final class ExpressionType extends Enum
    {

        public static ExpressionType valueOf(String s)
        {
            return (ExpressionType)Enum.valueOf(miui/maml/util/ColorParser$ExpressionType, s);
        }

        public static ExpressionType[] values()
        {
            return $VALUES;
        }

        private static final ExpressionType $VALUES[];
        public static final ExpressionType ARGB;
        public static final ExpressionType CONST;
        public static final ExpressionType INVALID;
        public static final ExpressionType VARIABLE;

        static 
        {
            CONST = new ExpressionType("CONST", 0);
            VARIABLE = new ExpressionType("VARIABLE", 1);
            ARGB = new ExpressionType("ARGB", 2);
            INVALID = new ExpressionType("INVALID", 3);
            $VALUES = (new ExpressionType[] {
                CONST, VARIABLE, ARGB, INVALID
            });
        }

        private ExpressionType(String s, int i)
        {
            super(s, i);
        }
    }


    private static int[] _2D_getmiui_2D_maml_2D_util_2D_ColorParser$ExpressionTypeSwitchesValues()
    {
        if(_2D_miui_2D_maml_2D_util_2D_ColorParser$ExpressionTypeSwitchesValues != null)
            return _2D_miui_2D_maml_2D_util_2D_ColorParser$ExpressionTypeSwitchesValues;
        int ai[] = new int[ExpressionType.values().length];
        try
        {
            ai[ExpressionType.ARGB.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[ExpressionType.CONST.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[ExpressionType.INVALID.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[ExpressionType.VARIABLE.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_miui_2D_maml_2D_util_2D_ColorParser$ExpressionTypeSwitchesValues = ai;
        return ai;
    }

    public ColorParser(Variables variables, String s)
    {
        mColor = 0xff000000;
        mColorExpression = s.trim();
        if(!mColorExpression.startsWith("#"))
            break MISSING_BLOCK_LABEL_59;
        mType = ExpressionType.CONST;
        mColor = Color.parseColor(mColorExpression);
_L1:
        return;
        variables;
        mColor = 0xff000000;
          goto _L1
        if(mColorExpression.startsWith("@"))
        {
            mType = ExpressionType.VARIABLE;
            mIndexedColorVar = new IndexedVariable(mColorExpression.substring(1), variables, false);
        } else
        if(mColorExpression.startsWith("argb(") && mColorExpression.endsWith(")"))
        {
            mType = ExpressionType.ARGB;
            mRGBExpression = Expression.buildMultiple(variables, mColorExpression.substring(5, mColorExpression.length() - 1));
            if(mRGBExpression != null && mRGBExpression.length != 4)
            {
                Log.e("ColorParser", "bad expression format");
                throw new IllegalArgumentException("bad expression format.");
            }
        } else
        {
            mType = ExpressionType.INVALID;
        }
          goto _L1
    }

    public static ColorParser fromElement(Variables variables, Element element)
    {
        return new ColorParser(variables, element.getAttribute("color"));
    }

    public static ColorParser fromElement(Variables variables, Element element, String s, miui.maml.StylesManager.Style style)
    {
        return new ColorParser(variables, StyleHelper.getAttr(element, s, style));
    }

    public static ColorParser fromElement(Variables variables, Element element, miui.maml.StylesManager.Style style)
    {
        return new ColorParser(variables, StyleHelper.getAttr(element, "color", style));
    }

    public int getColor()
    {
        int i = 0xff000000;
        _2D_getmiui_2D_maml_2D_util_2D_ColorParser$ExpressionTypeSwitchesValues()[mType.ordinal()];
        JVM INSTR tableswitch 1 3: default 40
    //                   1 92
    //                   2 46
    //                   3 51;
           goto _L1 _L2 _L3 _L4
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        mColor = 0xff000000;
_L6:
        return mColor;
_L4:
        String s = mIndexedColorVar.getString();
        if(!Utils.equals(s, mCurColorString))
        {
            if(s != null)
                i = Color.parseColor(s);
            mColor = i;
            mCurColorString = s;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        mColor = Color.argb((int)mRGBExpression[0].evaluate(), (int)mRGBExpression[1].evaluate(), (int)mRGBExpression[2].evaluate(), (int)mRGBExpression[3].evaluate());
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static final int _2D_miui_2D_maml_2D_util_2D_ColorParser$ExpressionTypeSwitchesValues[];
    private static final int DEFAULT_COLOR = 0xff000000;
    private static final String LOG_TAG = "ColorParser";
    private int mColor;
    private String mColorExpression;
    private String mCurColorString;
    private IndexedVariable mIndexedColorVar;
    private Expression mRGBExpression[];
    private ExpressionType mType;
}
