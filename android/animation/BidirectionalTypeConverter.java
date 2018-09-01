// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;


// Referenced classes of package android.animation:
//            TypeConverter

public abstract class BidirectionalTypeConverter extends TypeConverter
{
    private static class InvertedConverter extends BidirectionalTypeConverter
    {

        public Object convert(Object obj)
        {
            return mConverter.convertBack(obj);
        }

        public Object convertBack(Object obj)
        {
            return mConverter.convert(obj);
        }

        private BidirectionalTypeConverter mConverter;

        public InvertedConverter(BidirectionalTypeConverter bidirectionaltypeconverter)
        {
            super(bidirectionaltypeconverter.getTargetType(), bidirectionaltypeconverter.getSourceType());
            mConverter = bidirectionaltypeconverter;
        }
    }


    public BidirectionalTypeConverter(Class class1, Class class2)
    {
        super(class1, class2);
    }

    public abstract Object convertBack(Object obj);

    public BidirectionalTypeConverter invert()
    {
        if(mInvertedConverter == null)
            mInvertedConverter = new InvertedConverter(this);
        return mInvertedConverter;
    }

    private BidirectionalTypeConverter mInvertedConverter;
}
