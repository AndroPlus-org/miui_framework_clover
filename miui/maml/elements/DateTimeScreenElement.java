// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.text.TextUtils;
import android.util.Log;
import miui.date.Calendar;
import miui.maml.ScreenElementRoot;
import miui.maml.data.Expression;
import miui.maml.util.TextFormatter;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            TextScreenElement

public class DateTimeScreenElement extends TextScreenElement
{
    class DateFormatter
    {

        public String getText()
        {
            if(mTextFormatter == null)
                return null;
            String s = mTextFormatter.getFormat();
            if(s == null)
                return null;
            long l;
            if(mValueExp != null)
                l = (long)evaluate(mValueExp);
            else
                l = System.currentTimeMillis();
            if(TextUtils.equals(mOldFormat, s) && Math.abs(l - mPreValue) < 200L)
                return mText;
            mOldFormat = s;
            mCalendar.setTimeInMillis(l);
            String s1 = s;
            if(s.contains("NNNN"))
            {
                if(mCalendar.get(9) != mCurDay)
                {
                    mLunarDate = mCalendar.format("N\u6708e");
                    s1 = mCalendar.format("t");
                    if(s1 != null)
                        mLunarDate = (new StringBuilder()).append(mLunarDate).append(" ").append(s1).toString();
                    mCurDay = mCalendar.get(9);
                    Log.i("DateTimeScreenElement", (new StringBuilder()).append("get lunar date:").append(mLunarDate).toString());
                }
                s1 = s.replace("NNNN", mLunarDate);
            }
            mText = mCalendar.format(s1);
            mPreValue = l;
            return mText;
        }

        public void resetCalendar()
        {
            mCalendar = new Calendar();
        }

        private Calendar mCalendar;
        private int mCurDay;
        private String mLunarDate;
        private String mOldFormat;
        private long mPreValue;
        private String mText;
        private TextFormatter mTextFormatter;
        private Expression mValueExp;
        final DateTimeScreenElement this$0;

        public DateFormatter(TextFormatter textformatter, Expression expression)
        {
            this$0 = DateTimeScreenElement.this;
            super();
            mCalendar = new Calendar();
            mCurDay = -1;
            mTextFormatter = textformatter;
            mValueExp = expression;
        }
    }


    public DateTimeScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        screenelementroot = Expression.build(getVariables(), element.getAttribute("value"));
        mDateFormatter = new DateFormatter(mFormatter, screenelementroot);
        if(!TextUtils.isEmpty(element.getAttribute("contentDescriptionFormat")))
        {
            mHasContentDescription = true;
            mDescriptionDateFormatter = new DateFormatter(TextFormatter.fromElement(getVariables(), element, null, "contentDescriptionFormat", null, null, null), screenelementroot);
        }
    }

    public String getContentDescription()
    {
        String s;
        if(mDescriptionDateFormatter != null)
            s = mDescriptionDateFormatter.getText();
        else
            s = super.getContentDescription();
        return s;
    }

    protected String getText()
    {
        return mDateFormatter.getText();
    }

    public void resume()
    {
        super.resume();
        mDateFormatter.resetCalendar();
        if(mDescriptionDateFormatter != null)
            mDescriptionDateFormatter.resetCalendar();
    }

    public static final String TAG_NAME = "DateTime";
    private DateFormatter mDateFormatter;
    private DateFormatter mDescriptionDateFormatter;
}
