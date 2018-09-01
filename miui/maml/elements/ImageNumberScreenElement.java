// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import miui.maml.*;
import miui.maml.data.Expression;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ImageScreenElement

public class ImageNumberScreenElement extends ImageScreenElement
{
    private class CharName
    {

        public char ch;
        public String name;
        final ImageNumberScreenElement this$0;

        public CharName(char c, String s)
        {
            this$0 = ImageNumberScreenElement.this;
            super();
            ch = c;
            name = s;
        }
    }


    public ImageNumberScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        LOG_TAG = "ImageNumberScreenElement";
        mPreNumber = 4.9406564584124654E-324D;
        mNumExpression = Expression.build(getVariables(), getAttr(element, "number"));
        mStrExpression = Expression.build(getVariables(), getAttr(element, "string"));
        mSpaceExpression = Expression.build(getVariables(), getAttr(element, "space"));
        element = getAttr(element, "charNameMap");
        if(!TextUtils.isEmpty(element))
        {
            mNameMap = new ArrayList();
            screenelementroot = element.split(",");
            int i = screenelementroot.length;
            for(int j = 0; j < i; j++)
            {
                element = screenelementroot[j];
                mNameMap.add(new CharName(element.charAt(0), element.substring(1)));
            }

        }
    }

    private String charToStr(char c)
    {
label0:
        {
            if(mNameMap == null)
                break label0;
            Iterator iterator = mNameMap.iterator();
            CharName charname;
            do
            {
                if(!iterator.hasNext())
                    break label0;
                charname = (CharName)iterator.next();
            } while(charname.ch != c);
            return charname.name;
        }
        if(c == '.')
            return "dot";
        else
            return String.valueOf(c);
    }

    private Bitmap getNumberBitmap(String s, String s1)
    {
        s = Utils.addFileNameSuffix(s, s1);
        return getContext().mResourceManager.getBitmap(s);
    }

    protected void doTick(long l)
    {
        super.doTick(l);
        if(mNumExpression == null && mStrExpression == null && mStrValue == null)
        {
            if(mCachedBmp != null)
            {
                mCachedBmp = null;
                mPreStr = null;
                mCurrentBitmap.setBitmap(null);
                updateBitmapVars();
            }
            return;
        }
        String s = null;
        String s1 = getSrc();
        int i = TextUtils.equals(s1, mOldSrc) ^ true;
        int j;
        int k;
        if(mNumExpression != null)
        {
            double d = evaluate(mNumExpression);
            if(d == mPreNumber && i ^ true)
                return;
            mPreNumber = d;
            s = Utils.doubleToString(d);
        } else
        if(mStrExpression != null || mStrValue != null)
        {
            if(mStrValue != null)
                s = mStrValue;
            else
                s = evaluateStr(mStrExpression);
            if(TextUtils.equals(s, mPreStr) && i ^ true)
                return;
            mPreStr = s;
        }
        if(mCachedBmp != null)
            mCachedBmp.eraseColor(0);
        mOldSrc = s1;
        mBmpWidth = 0;
        if(s != null)
            j = s.length();
        else
            j = 0;
        k = 0;
        while(k < j) 
        {
            Bitmap bitmap = getNumberBitmap(s1, charToStr(s.charAt(k)));
            if(bitmap == null)
            {
                Log.e(LOG_TAG, (new StringBuilder()).append("Fail to get bitmap for number ").append(String.valueOf(s.charAt(k))).toString());
                return;
            }
            int i1 = mBmpWidth + bitmap.getWidth();
            int j1 = bitmap.getHeight();
            int k1;
            if(mCachedBmp == null)
                k1 = 0;
            else
                k1 = mCachedBmp.getWidth();
            if(mCachedBmp == null)
                i = 0;
            else
                i = mCachedBmp.getHeight();
            if(i1 > k1 || j1 > i)
            {
                Bitmap bitmap1 = mCachedBmp;
                if(i1 > k1)
                {
                    k1 = j - k;
                    k1 = mBmpWidth + bitmap.getWidth() * k1 + mSpace * (k1 - 1);
                }
                i1 = j1;
                if(j1 <= i)
                    i1 = i;
                mBmpHeight = i1;
                mCachedBmp = Bitmap.createBitmap(k1, i1, android.graphics.Bitmap.Config.ARGB_8888);
                mCachedBmp.setDensity(bitmap.getDensity());
                mCurrentBitmap.setBitmap(mCachedBmp);
                mCachedCanvas = new Canvas(mCachedBmp);
                if(bitmap1 != null)
                    mCachedCanvas.drawBitmap(bitmap1, 0.0F, 0.0F, null);
            }
            mCachedCanvas.drawBitmap(bitmap, mBmpWidth, 0.0F, null);
            mBmpWidth = mBmpWidth + bitmap.getWidth();
            if(k < j - 1)
                mBmpWidth = mBmpWidth + mSpace;
            k++;
        }
        mCurrentBitmap.updateVersion();
        updateBitmapVars();
    }

    public void finish()
    {
        super.finish();
        mPreNumber = 4.9406564584124654E-324D;
        mPreStr = null;
    }

    protected int getBitmapHeight()
    {
        return mBmpHeight;
    }

    protected int getBitmapWidth()
    {
        return mBmpWidth;
    }

    public void init()
    {
        super.init();
        int i;
        if(mSpaceExpression == null)
            i = 0;
        else
            i = (int)scale(mSpaceExpression.evaluate());
        mSpace = i;
        mCurrentBitmap.setBitmap(mCachedBmp);
    }

    public void setValue(double d)
    {
        setValue(Utils.doubleToString(d));
    }

    public void setValue(String s)
    {
        mStrValue = s;
        requestUpdate();
    }

    protected void updateBitmap(boolean flag)
    {
        mCurrentBitmap.setBitmap(mCachedBmp);
        updateBitmapVars();
    }

    public static final String TAG_NAME = "ImageNumber";
    public static final String TAG_NAME1 = "ImageChars";
    private String LOG_TAG;
    private int mBmpHeight;
    private int mBmpWidth;
    private Bitmap mCachedBmp;
    private Canvas mCachedCanvas;
    private ArrayList mNameMap;
    private Expression mNumExpression;
    private String mOldSrc;
    private double mPreNumber;
    private String mPreStr;
    private int mSpace;
    private Expression mSpaceExpression;
    private Expression mStrExpression;
    private String mStrValue;
}
