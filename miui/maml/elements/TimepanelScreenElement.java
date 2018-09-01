// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import miui.maml.*;
import miui.maml.data.Expression;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ImageScreenElement

public class TimepanelScreenElement extends ImageScreenElement
{

    public TimepanelScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mCalendar = Calendar.getInstance();
        mLocalizedZero = DecimalFormatSymbols.getInstance().getZeroDigit();
        mFormatRaw = getAttr(element, "format");
        mFormatExp = Expression.build(getVariables(), getAttr(element, "formatExp"));
        mSpace = (int)scale(getAttrAsInt(element, "space", 0));
    }

    private void createBitmap()
    {
        int i = 0;
        int j = 0;
        for(int k = 0; k < "0123456789:".length();)
        {
            Bitmap bitmap = getDigitBmp("0123456789:".charAt(k));
            if(bitmap == null)
            {
                mLoadResourceFailed = true;
                Log.e("TimepanelScreenElement", (new StringBuilder()).append("Failed to load digit bitmap: ").append("0123456789:".charAt(k)).toString());
                return;
            }
            int l = i;
            if(i < bitmap.getWidth())
                l = bitmap.getWidth();
            if(mBmpHeight < bitmap.getHeight())
                mBmpHeight = bitmap.getHeight();
            i = j;
            if(j == 0)
                i = bitmap.getDensity();
            k++;
            j = i;
            i = l;
        }

        Bitmap bitmap1 = Bitmap.createBitmap(i * 5 + mSpace * 4, mBmpHeight, android.graphics.Bitmap.Config.ARGB_8888);
        bitmap1.setDensity(j);
        mBitmap.setBitmap(bitmap1);
        setActualHeight(descale(mBmpHeight));
    }

    private Bitmap getDigitBmp(char c)
    {
        String s = getSrc();
        String s1 = s;
        if(TextUtils.isEmpty(s))
            s1 = "time.png";
        if(c == ':')
        {
            s = "dot";
        } else
        {
            char c1 = c;
            if(c >= mLocalizedZero)
            {
                c1 = c;
                if(c <= mLocalizedZero + 9)
                {
                    c = (char)((c - mLocalizedZero) + 48);
                    c1 = c;
                }
            }
            s = String.valueOf(c1);
        }
        return getContext().mResourceManager.getBitmap(Utils.addFileNameSuffix(s1, s));
    }

    private String getFormat()
    {
        if(mFormatExp != null)
            return mFormatExp.evaluateStr();
        else
            return mFormat;
    }

    private void setDateFormat()
    {
        if(TextUtils.isEmpty(mFormatRaw) && mFormatExp == null)
        {
            String s;
            if(DateFormat.is24HourFormat(getContext().mContext))
                s = "kk:mm";
            else
                s = "hh:mm";
            mFormat = s;
        } else
        {
            mFormat = mFormatRaw;
        }
    }

    private void updateTime(boolean flag)
    {
        if(mLoadResourceFailed)
            return;
        Object obj = mBitmap.getBitmap();
        if(obj == null)
            return;
        long l = System.currentTimeMillis();
        mCalendar.setTimeInMillis(l);
        CharSequence charsequence = DateFormat.format(getFormat(), mCalendar);
        if(!flag && charsequence.equals(mPreTime))
            return;
        mPreTime = charsequence;
        obj = new Canvas(((Bitmap) (obj)));
        ((Canvas) (obj)).drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
        int i = 0;
        for(int j = 0; j < charsequence.length();)
        {
            Bitmap bitmap = getDigitBmp(charsequence.charAt(j));
            int k = i;
            if(bitmap != null)
            {
                ((Canvas) (obj)).drawBitmap(bitmap, i, 0.0F, null);
                k = i + bitmap.getWidth() + mSpace;
            }
            j++;
            i = k;
        }

        mBitmap.updateVersion();
        mBmpWidth = i - mSpace;
        setActualWidth(descale(mBmpWidth));
        requestUpdate();
    }

    protected void doTick(long l)
    {
        super.doTick(l);
        l = System.currentTimeMillis() / 60000L;
        String s = getSrc();
        String s1 = getFormat();
        if(l != mPreMinute || TextUtils.equals(s, mOldSrc) ^ true || TextUtils.equals(s1, mOldFormat) ^ true)
        {
            updateTime(true);
            mPreMinute = l;
            mOldSrc = s;
            mOldFormat = s1;
        }
    }

    public void finish()
    {
        mPreTime = null;
        mLoadResourceFailed = false;
    }

    protected int getBitmapWidth()
    {
        return mBmpWidth;
    }

    public void init()
    {
        super.init();
        setDateFormat();
        mPreTime = null;
        createBitmap();
        updateTime(true);
    }

    public void pause()
    {
    }

    public void resume()
    {
        mCalendar = Calendar.getInstance();
        mLocalizedZero = DecimalFormatSymbols.getInstance().getZeroDigit();
        setDateFormat();
        updateTime(true);
    }

    private static final String LOG_TAG = "TimepanelScreenElement";
    private static final String M12 = "hh:mm";
    private static final String M24 = "kk:mm";
    public static final String TAG_NAME = "Time";
    private int mBmpHeight;
    private int mBmpWidth;
    protected Calendar mCalendar;
    private String mFormat;
    private Expression mFormatExp;
    private String mFormatRaw;
    private boolean mLoadResourceFailed;
    private char mLocalizedZero;
    private String mOldFormat;
    private String mOldSrc;
    private long mPreMinute;
    private CharSequence mPreTime;
    private int mSpace;
}
