// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.util.TypedValue;
import com.android.internal.util.XmlUtils;
import java.io.*;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.content.res:
//            StringBlock, AssetManager, XmlResourceParser

final class XmlBlock
{
    final class Parser
        implements XmlResourceParser
    {

        public void close()
        {
            XmlBlock xmlblock = mBlock;
            xmlblock;
            JVM INSTR monitorenter ;
            if(mParseState != 0L)
            {
                XmlBlock._2D_wrap15(mParseState);
                mParseState = 0L;
                XmlBlock._2D_wrap14(mBlock);
            }
            xmlblock;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void defineEntityReplacementText(String s, String s1)
            throws XmlPullParserException
        {
            throw new XmlPullParserException("defineEntityReplacementText() not supported");
        }

        protected void finalize()
            throws Throwable
        {
            close();
        }

        public boolean getAttributeBooleanValue(int i, boolean flag)
        {
            boolean flag1 = false;
            int j = XmlBlock._2D_wrap1(mParseState, i);
            if(j >= 16 && j <= 31)
            {
                flag = flag1;
                if(XmlBlock._2D_wrap2(mParseState, i) != 0)
                    flag = true;
                return flag;
            } else
            {
                return flag;
            }
        }

        public boolean getAttributeBooleanValue(String s, String s1, boolean flag)
        {
            int i = XmlBlock._2D_wrap3(mParseState, s, s1);
            if(i >= 0)
                return getAttributeBooleanValue(i, flag);
            else
                return flag;
        }

        public int getAttributeCount()
        {
            int i;
            if(mEventType == 2)
                i = XmlBlock._2D_wrap0(mParseState);
            else
                i = -1;
            return i;
        }

        public float getAttributeFloatValue(int i, float f)
        {
            if(XmlBlock._2D_wrap1(mParseState, i) == 4)
                return Float.intBitsToFloat(XmlBlock._2D_wrap2(mParseState, i));
            else
                throw new RuntimeException("not a float!");
        }

        public float getAttributeFloatValue(String s, String s1, float f)
        {
            int i = XmlBlock._2D_wrap3(mParseState, s, s1);
            if(i >= 0)
                return getAttributeFloatValue(i, f);
            else
                return f;
        }

        public int getAttributeIntValue(int i, int j)
        {
            int k = XmlBlock._2D_wrap1(mParseState, i);
            if(k >= 16 && k <= 31)
                return XmlBlock._2D_wrap2(mParseState, i);
            else
                return j;
        }

        public int getAttributeIntValue(String s, String s1, int i)
        {
            int j = XmlBlock._2D_wrap3(mParseState, s, s1);
            if(j >= 0)
                return getAttributeIntValue(j, i);
            else
                return i;
        }

        public int getAttributeListValue(int i, String as[], int j)
        {
            int k = XmlBlock._2D_wrap1(mParseState, i);
            i = XmlBlock._2D_wrap2(mParseState, i);
            if(k == 3)
                return XmlUtils.convertValueToList(mStrings.get(i), as, j);
            else
                return i;
        }

        public int getAttributeListValue(String s, String s1, String as[], int i)
        {
            int j = XmlBlock._2D_wrap3(mParseState, s, s1);
            if(j >= 0)
                return getAttributeListValue(j, as, i);
            else
                return i;
        }

        public String getAttributeName(int i)
        {
            int j = XmlBlock._2D_wrap4(mParseState, i);
            if(j >= 0)
                return mStrings.get(j).toString();
            else
                throw new IndexOutOfBoundsException(String.valueOf(i));
        }

        public int getAttributeNameResource(int i)
        {
            return XmlBlock._2D_wrap6(mParseState, i);
        }

        public String getAttributeNamespace(int i)
        {
            int j = XmlBlock._2D_wrap5(mParseState, i);
            if(j >= 0)
                return mStrings.get(j).toString();
            if(j == -1)
                return "";
            else
                throw new IndexOutOfBoundsException(String.valueOf(i));
        }

        public String getAttributePrefix(int i)
        {
            throw new RuntimeException("getAttributePrefix not supported");
        }

        public int getAttributeResourceValue(int i, int j)
        {
            if(XmlBlock._2D_wrap1(mParseState, i) == 1)
                return XmlBlock._2D_wrap2(mParseState, i);
            else
                return j;
        }

        public int getAttributeResourceValue(String s, String s1, int i)
        {
            int j = XmlBlock._2D_wrap3(mParseState, s, s1);
            if(j >= 0)
                return getAttributeResourceValue(j, i);
            else
                return i;
        }

        public String getAttributeType(int i)
        {
            return "CDATA";
        }

        public int getAttributeUnsignedIntValue(int i, int j)
        {
            int k = XmlBlock._2D_wrap1(mParseState, i);
            if(k >= 16 && k <= 31)
                return XmlBlock._2D_wrap2(mParseState, i);
            else
                return j;
        }

        public int getAttributeUnsignedIntValue(String s, String s1, int i)
        {
            int j = XmlBlock._2D_wrap3(mParseState, s, s1);
            if(j >= 0)
                return getAttributeUnsignedIntValue(j, i);
            else
                return i;
        }

        public String getAttributeValue(int i)
        {
            int j = XmlBlock._2D_wrap7(mParseState, i);
            if(j >= 0)
                return mStrings.get(j).toString();
            j = XmlBlock._2D_wrap1(mParseState, i);
            if(j == 0)
                throw new IndexOutOfBoundsException(String.valueOf(i));
            else
                return TypedValue.coerceToString(j, XmlBlock._2D_wrap2(mParseState, i));
        }

        public String getAttributeValue(String s, String s1)
        {
            int i = XmlBlock._2D_wrap3(mParseState, s, s1);
            if(i >= 0)
                return getAttributeValue(i);
            else
                return null;
        }

        public String getClassAttribute()
        {
            int i = XmlBlock._2D_wrap8(mParseState);
            String s;
            if(i >= 0)
                s = mStrings.get(i).toString();
            else
                s = null;
            return s;
        }

        public int getColumnNumber()
        {
            return -1;
        }

        public int getDepth()
        {
            return mDepth;
        }

        public int getEventType()
            throws XmlPullParserException
        {
            return mEventType;
        }

        public boolean getFeature(String s)
        {
            if("http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(s))
                return true;
            return "http://xmlpull.org/v1/doc/features.html#report-namespace-prefixes".equals(s);
        }

        public String getIdAttribute()
        {
            int i = XmlBlock._2D_wrap9(mParseState);
            String s;
            if(i >= 0)
                s = mStrings.get(i).toString();
            else
                s = null;
            return s;
        }

        public int getIdAttributeResourceValue(int i)
        {
            return getAttributeResourceValue(null, "id", i);
        }

        public String getInputEncoding()
        {
            return null;
        }

        public int getLineNumber()
        {
            return XmlBlock._2D_wrap10(mParseState);
        }

        public String getName()
        {
            int i = XmlBlock.nativeGetName(mParseState);
            String s;
            if(i >= 0)
                s = mStrings.get(i).toString();
            else
                s = null;
            return s;
        }

        public String getNamespace()
        {
            int i = XmlBlock._2D_wrap11(mParseState);
            String s;
            if(i >= 0)
                s = mStrings.get(i).toString();
            else
                s = "";
            return s;
        }

        public String getNamespace(String s)
        {
            throw new RuntimeException("getNamespace() not supported");
        }

        public int getNamespaceCount(int i)
            throws XmlPullParserException
        {
            throw new XmlPullParserException("getNamespaceCount() not supported");
        }

        public String getNamespacePrefix(int i)
            throws XmlPullParserException
        {
            throw new XmlPullParserException("getNamespacePrefix() not supported");
        }

        public String getNamespaceUri(int i)
            throws XmlPullParserException
        {
            throw new XmlPullParserException("getNamespaceUri() not supported");
        }

        final CharSequence getPooledString(int i)
        {
            return mStrings.get(i);
        }

        public String getPositionDescription()
        {
            return (new StringBuilder()).append("Binary XML file line #").append(getLineNumber()).toString();
        }

        public String getPrefix()
        {
            throw new RuntimeException("getPrefix not supported");
        }

        public Object getProperty(String s)
        {
            return null;
        }

        public int getStyleAttribute()
        {
            return XmlBlock._2D_wrap12(mParseState);
        }

        public String getText()
        {
            int i = XmlBlock._2D_wrap13(mParseState);
            String s;
            if(i >= 0)
                s = mStrings.get(i).toString();
            else
                s = null;
            return s;
        }

        public char[] getTextCharacters(int ai[])
        {
            String s = getText();
            char ac[] = null;
            if(s != null)
            {
                ai[0] = 0;
                ai[1] = s.length();
                ac = new char[s.length()];
                s.getChars(0, s.length(), ac, 0);
            }
            return ac;
        }

        public boolean isAttributeDefault(int i)
        {
            return false;
        }

        public boolean isEmptyElementTag()
            throws XmlPullParserException
        {
            return false;
        }

        public boolean isWhitespace()
            throws XmlPullParserException
        {
            return false;
        }

        public int next()
            throws XmlPullParserException, IOException
        {
            int i;
            if(!mStarted)
            {
                mStarted = true;
                return 0;
            }
            if(mParseState == 0L)
                return 1;
            i = XmlBlock.nativeNext(mParseState);
            if(mDecNextDepth)
            {
                mDepth = mDepth - 1;
                mDecNextDepth = false;
            }
            i;
            JVM INSTR tableswitch 2 3: default 80
        //                       2 96
        //                       3 109;
               goto _L1 _L2 _L3
_L1:
            mEventType = i;
            if(i == 1)
                close();
            return i;
_L2:
            mDepth = mDepth + 1;
            continue; /* Loop/switch isn't completed */
_L3:
            mDecNextDepth = true;
            if(true) goto _L1; else goto _L4
_L4:
        }

        public int nextTag()
            throws XmlPullParserException, IOException
        {
            int i = next();
            int j = i;
            if(i == 4)
            {
                j = i;
                if(isWhitespace())
                    j = next();
            }
            if(j != 2 && j != 3)
                throw new XmlPullParserException((new StringBuilder()).append(getPositionDescription()).append(": expected start or end tag").toString(), this, null);
            else
                return j;
        }

        public String nextText()
            throws XmlPullParserException, IOException
        {
            if(getEventType() != 2)
                throw new XmlPullParserException((new StringBuilder()).append(getPositionDescription()).append(": parser must be on START_TAG to read next text").toString(), this, null);
            int i = next();
            if(i == 4)
            {
                String s = getText();
                if(next() != 3)
                    throw new XmlPullParserException((new StringBuilder()).append(getPositionDescription()).append(": event TEXT it must be immediately followed by END_TAG").toString(), this, null);
                else
                    return s;
            }
            if(i == 3)
                return "";
            else
                throw new XmlPullParserException((new StringBuilder()).append(getPositionDescription()).append(": parser must be on START_TAG or TEXT to read text").toString(), this, null);
        }

        public int nextToken()
            throws XmlPullParserException, IOException
        {
            return next();
        }

        public void require(int i, String s, String s1)
            throws XmlPullParserException, IOException
        {
            if(i != getEventType() || s != null && s.equals(getNamespace()) ^ true || s1 != null && s1.equals(getName()) ^ true)
                throw new XmlPullParserException((new StringBuilder()).append("expected ").append(TYPES[i]).append(getPositionDescription()).toString());
            else
                return;
        }

        public void setFeature(String s, boolean flag)
            throws XmlPullParserException
        {
            if("http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(s) && flag)
                return;
            if("http://xmlpull.org/v1/doc/features.html#report-namespace-prefixes".equals(s) && flag)
                return;
            else
                throw new XmlPullParserException((new StringBuilder()).append("Unsupported feature: ").append(s).toString());
        }

        public void setInput(InputStream inputstream, String s)
            throws XmlPullParserException
        {
            throw new XmlPullParserException("setInput() not supported");
        }

        public void setInput(Reader reader)
            throws XmlPullParserException
        {
            throw new XmlPullParserException("setInput() not supported");
        }

        public void setProperty(String s, Object obj)
            throws XmlPullParserException
        {
            throw new XmlPullParserException("setProperty() not supported");
        }

        private final XmlBlock mBlock;
        private boolean mDecNextDepth;
        private int mDepth;
        private int mEventType;
        long mParseState;
        private boolean mStarted;
        final XmlBlock this$0;

        Parser(long l, XmlBlock xmlblock1)
        {
            this$0 = XmlBlock.this;
            super();
            mStarted = false;
            mDecNextDepth = false;
            mDepth = 0;
            mEventType = 0;
            mParseState = l;
            mBlock = xmlblock1;
            XmlBlock._2D_set0(xmlblock1, XmlBlock._2D_get0(xmlblock1) + 1);
        }
    }


    static int _2D_get0(XmlBlock xmlblock)
    {
        return xmlblock.mOpenCount;
    }

    static int _2D_set0(XmlBlock xmlblock, int i)
    {
        xmlblock.mOpenCount = i;
        return i;
    }

    static int _2D_wrap0(long l)
    {
        return nativeGetAttributeCount(l);
    }

    static int _2D_wrap1(long l, int i)
    {
        return nativeGetAttributeDataType(l, i);
    }

    static int _2D_wrap10(long l)
    {
        return nativeGetLineNumber(l);
    }

    static int _2D_wrap11(long l)
    {
        return nativeGetNamespace(l);
    }

    static int _2D_wrap12(long l)
    {
        return nativeGetStyleAttribute(l);
    }

    static int _2D_wrap13(long l)
    {
        return nativeGetText(l);
    }

    static void _2D_wrap14(XmlBlock xmlblock)
    {
        xmlblock.decOpenCountLocked();
    }

    static void _2D_wrap15(long l)
    {
        nativeDestroyParseState(l);
    }

    static int _2D_wrap2(long l, int i)
    {
        return nativeGetAttributeData(l, i);
    }

    static int _2D_wrap3(long l, String s, String s1)
    {
        return nativeGetAttributeIndex(l, s, s1);
    }

    static int _2D_wrap4(long l, int i)
    {
        return nativeGetAttributeName(l, i);
    }

    static int _2D_wrap5(long l, int i)
    {
        return nativeGetAttributeNamespace(l, i);
    }

    static int _2D_wrap6(long l, int i)
    {
        return nativeGetAttributeResource(l, i);
    }

    static int _2D_wrap7(long l, int i)
    {
        return nativeGetAttributeStringValue(l, i);
    }

    static int _2D_wrap8(long l)
    {
        return nativeGetClassAttribute(l);
    }

    static int _2D_wrap9(long l)
    {
        return nativeGetIdAttribute(l);
    }

    XmlBlock(AssetManager assetmanager, long l)
    {
        mOpen = true;
        mOpenCount = 1;
        mAssets = assetmanager;
        mNative = l;
        mStrings = new StringBlock(nativeGetStringBlock(l), false);
    }

    public XmlBlock(byte abyte0[])
    {
        mOpen = true;
        mOpenCount = 1;
        mAssets = null;
        mNative = nativeCreate(abyte0, 0, abyte0.length);
        mStrings = new StringBlock(nativeGetStringBlock(mNative), false);
    }

    public XmlBlock(byte abyte0[], int i, int j)
    {
        mOpen = true;
        mOpenCount = 1;
        mAssets = null;
        mNative = nativeCreate(abyte0, i, j);
        mStrings = new StringBlock(nativeGetStringBlock(mNative), false);
    }

    private void decOpenCountLocked()
    {
        mOpenCount = mOpenCount - 1;
        if(mOpenCount == 0)
        {
            nativeDestroy(mNative);
            if(mAssets != null)
                mAssets.xmlBlockGone(hashCode());
        }
    }

    private static final native long nativeCreate(byte abyte0[], int i, int j);

    private static final native long nativeCreateParseState(long l);

    private static final native void nativeDestroy(long l);

    private static final native void nativeDestroyParseState(long l);

    private static final native int nativeGetAttributeCount(long l);

    private static final native int nativeGetAttributeData(long l, int i);

    private static final native int nativeGetAttributeDataType(long l, int i);

    private static final native int nativeGetAttributeIndex(long l, String s, String s1);

    private static final native int nativeGetAttributeName(long l, int i);

    private static final native int nativeGetAttributeNamespace(long l, int i);

    private static final native int nativeGetAttributeResource(long l, int i);

    private static final native int nativeGetAttributeStringValue(long l, int i);

    private static final native int nativeGetClassAttribute(long l);

    private static final native int nativeGetIdAttribute(long l);

    private static final native int nativeGetLineNumber(long l);

    static final native int nativeGetName(long l);

    private static final native int nativeGetNamespace(long l);

    private static final native long nativeGetStringBlock(long l);

    private static final native int nativeGetStyleAttribute(long l);

    private static final native int nativeGetText(long l);

    static final native int nativeNext(long l);

    public void close()
    {
        this;
        JVM INSTR monitorenter ;
        if(mOpen)
        {
            mOpen = false;
            decOpenCountLocked();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void finalize()
        throws Throwable
    {
        close();
    }

    public XmlResourceParser newParser()
    {
        this;
        JVM INSTR monitorenter ;
        Parser parser;
        if(mNative == 0L)
            break MISSING_BLOCK_LABEL_32;
        parser = new Parser(nativeCreateParseState(mNative), this);
        return parser;
        this;
        JVM INSTR monitorexit ;
        return null;
        Exception exception;
        exception;
        throw exception;
    }

    private static final boolean DEBUG = false;
    private final AssetManager mAssets;
    private final long mNative;
    private boolean mOpen;
    private int mOpenCount;
    final StringBlock mStrings;
}
