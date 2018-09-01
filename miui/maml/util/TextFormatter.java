// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import android.text.TextUtils;
import android.util.Log;
import java.util.*;
import miui.maml.data.*;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.util:
//            StyleHelper

public class TextFormatter
{
    private static class ExpressioPara extends FormatPara
    {

        public Object evaluate()
        {
            return Long.valueOf((long)mExp.evaluate());
        }

        private Expression mExp;

        public ExpressioPara(Expression expression)
        {
            super(null);
            mExp = expression;
        }
    }

    private static abstract class FormatPara
    {

        public static FormatPara build(Variables variables, String s)
        {
            String s1 = s.trim();
            if(s1.startsWith("@"))
                return new StringVarPara(variables, s1.substring(1));
            variables = Expression.build(variables, s1);
            if(variables == null)
            {
                Log.e("TextFormatter", (new StringBuilder()).append("invalid parameter expression:").append(s).toString());
                return null;
            } else
            {
                return new ExpressioPara(variables);
            }
        }

        public static FormatPara[] buildArray(Variables variables, String s)
        {
            int i = 0;
            int j = 0;
            ArrayList arraylist = new ArrayList();
            int k = 0;
            while(k < s.length()) 
            {
                char c = s.charAt(k);
                int l = j;
                if(i == 0)
                {
                    l = j;
                    if(c == ',')
                    {
                        FormatPara formatpara = build(variables, s.substring(j, k));
                        if(formatpara == null)
                            return null;
                        arraylist.add(formatpara);
                        l = k + 1;
                    }
                }
                if(c == '(')
                {
                    j = i + 1;
                } else
                {
                    j = i;
                    if(c == ')')
                        j = i - 1;
                }
                k++;
                i = j;
                j = l;
            }
            variables = build(variables, s.substring(j));
            if(variables == null)
            {
                return null;
            } else
            {
                arraylist.add(variables);
                return (FormatPara[])arraylist.toArray(new FormatPara[arraylist.size()]);
            }
        }

        public abstract Object evaluate();

        private FormatPara()
        {
        }

        FormatPara(FormatPara formatpara)
        {
            this();
        }
    }

    private static class StringVarPara extends FormatPara
    {

        public Object evaluate()
        {
            String s = mVar.getString();
            String s1 = s;
            if(s == null)
                s1 = "";
            return s1;
        }

        private IndexedVariable mVar;
        private String mVariable;

        public StringVarPara(Variables variables, String s)
        {
            super(null);
            mVariable = s;
            mVar = new IndexedVariable(mVariable, variables, false);
        }
    }


    public TextFormatter(Variables variables, String s)
    {
        this(variables, s, "", "");
    }

    public TextFormatter(Variables variables, String s, String s1)
    {
        this(variables, "", s, s1);
    }

    public TextFormatter(Variables variables, String s, String s1, String s2)
    {
        mText = s;
        if(mText.startsWith("@"))
        {
            mText = mText.substring(1);
            if(!mText.startsWith("@"))
                mIndexedTextVar = new IndexedVariable(mText, variables, false);
        }
        mFormat = s1;
        if(mFormat.startsWith("@"))
        {
            mFormat = mFormat.substring(1);
            if(!mFormat.startsWith("@"))
                mIndexedFormatVar = new IndexedVariable(mFormat, variables, false);
        }
        if(!TextUtils.isEmpty(s2))
        {
            mParas = FormatPara.buildArray(variables, s2);
            if(mParas != null)
                mParasValue = new Object[mParas.length];
        }
    }

    public TextFormatter(Variables variables, String s, String s1, String s2, Expression expression, Expression expression1)
    {
        this(variables, s, s1, s2);
        mTextExpression = expression;
        mFormatExpression = expression1;
    }

    public TextFormatter(Variables variables, String s, Expression expression)
    {
        this(variables, s, "", "", expression, null);
    }

    public static TextFormatter fromElement(Variables variables, Element element, String s, String s1, String s2, String s3, String s4)
    {
        return new TextFormatter(variables, element.getAttribute(s), element.getAttribute(s1), element.getAttribute(s2), Expression.build(variables, element.getAttribute(s3)), Expression.build(variables, element.getAttribute(s4)));
    }

    public static TextFormatter fromElement(Variables variables, Element element, miui.maml.StylesManager.Style style)
    {
        String s = StyleHelper.getAttr(element, "paras", style);
        String s1 = s;
        if(TextUtils.isEmpty(s))
            s1 = StyleHelper.getAttr(element, "params", style);
        return new TextFormatter(variables, StyleHelper.getAttr(element, "text", style), StyleHelper.getAttr(element, "format", style), s1, Expression.build(variables, StyleHelper.getAttr(element, "textExp", style)), Expression.build(variables, StyleHelper.getAttr(element, "formatExp", style)));
    }

    public String getFormat()
    {
        if(mFormatExpression != null)
            return mFormatExpression.evaluateStr();
        if(mIndexedFormatVar != null)
            return mIndexedFormatVar.getString();
        else
            return mFormat;
    }

    public String getText()
    {
        if(mTextExpression != null)
            return mTextExpression.evaluateStr();
        String s = getFormat();
        if(!TextUtils.isEmpty(s))
        {
            if(mParas != null)
            {
                for(int i = 0; i < mParas.length; i++)
                    mParasValue[i] = mParas[i].evaluate();

            }
            if(mParasValue != null)
            {
                String s1;
                try
                {
                    s1 = String.format(s, mParasValue);
                }
                catch(IllegalFormatException illegalformatexception)
                {
                    return (new StringBuilder()).append("Format error: ").append(s).toString();
                }
                return s1;
            }
        }
        if(mIndexedTextVar != null)
            return mIndexedTextVar.getString();
        else
            return mText;
    }

    public boolean hasFormat()
    {
        return TextUtils.isEmpty(mFormat) ^ true;
    }

    public transient void setParams(Object aobj[])
    {
        if(aobj != null)
        {
            mParas = null;
            int i = aobj.length;
            if(mParasValue == null)
                mParasValue = new Object[i];
            int j = i;
            if(mParasValue.length < i)
                j = mParasValue.length;
            mParasValue = Arrays.copyOf(aobj, j);
        }
    }

    public void setText(String s)
    {
        mText = s;
        mFormat = "";
    }

    private static final String LOG_TAG = "TextFormatter";
    private String mFormat;
    private Expression mFormatExpression;
    private IndexedVariable mIndexedFormatVar;
    private IndexedVariable mIndexedTextVar;
    private FormatPara mParas[];
    private Object mParasValue[];
    private String mText;
    private Expression mTextExpression;
}
