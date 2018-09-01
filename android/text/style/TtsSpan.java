// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.os.Parcel;
import android.os.PersistableBundle;
import android.text.ParcelableSpan;
import java.text.NumberFormat;
import java.util.Locale;

public class TtsSpan
    implements ParcelableSpan
{
    public static class Builder
    {

        public TtsSpan build()
        {
            return new TtsSpan(mType, mArgs);
        }

        public Builder setIntArgument(String s, int i)
        {
            mArgs.putInt(s, i);
            return this;
        }

        public Builder setLongArgument(String s, long l)
        {
            mArgs.putLong(s, l);
            return this;
        }

        public Builder setStringArgument(String s, String s1)
        {
            mArgs.putString(s, s1);
            return this;
        }

        private PersistableBundle mArgs;
        private final String mType;

        public Builder(String s)
        {
            mArgs = new PersistableBundle();
            mType = s;
        }
    }

    public static class CardinalBuilder extends SemioticClassBuilder
    {

        public CardinalBuilder setNumber(long l)
        {
            return setNumber(String.valueOf(l));
        }

        public CardinalBuilder setNumber(String s)
        {
            return (CardinalBuilder)setStringArgument("android.arg.number", s);
        }

        public CardinalBuilder()
        {
            super("android.type.cardinal");
        }

        public CardinalBuilder(long l)
        {
            this();
            setNumber(l);
        }

        public CardinalBuilder(String s)
        {
            this();
            setNumber(s);
        }
    }

    public static class DateBuilder extends SemioticClassBuilder
    {

        public DateBuilder setDay(int i)
        {
            return (DateBuilder)setIntArgument("android.arg.day", i);
        }

        public DateBuilder setMonth(int i)
        {
            return (DateBuilder)setIntArgument("android.arg.month", i);
        }

        public DateBuilder setWeekday(int i)
        {
            return (DateBuilder)setIntArgument("android.arg.weekday", i);
        }

        public DateBuilder setYear(int i)
        {
            return (DateBuilder)setIntArgument("android.arg.year", i);
        }

        public DateBuilder()
        {
            super("android.type.date");
        }

        public DateBuilder(Integer integer, Integer integer1, Integer integer2, Integer integer3)
        {
            this();
            if(integer != null)
                setWeekday(integer.intValue());
            if(integer1 != null)
                setDay(integer1.intValue());
            if(integer2 != null)
                setMonth(integer2.intValue());
            if(integer3 != null)
                setYear(integer3.intValue());
        }
    }

    public static class DecimalBuilder extends SemioticClassBuilder
    {

        public DecimalBuilder setArgumentsFromDouble(double d, int i, int j)
        {
            Object obj = NumberFormat.getInstance(Locale.US);
            ((NumberFormat) (obj)).setMinimumFractionDigits(j);
            ((NumberFormat) (obj)).setMaximumFractionDigits(j);
            ((NumberFormat) (obj)).setGroupingUsed(false);
            obj = ((NumberFormat) (obj)).format(d);
            i = ((String) (obj)).indexOf('.');
            if(i >= 0)
            {
                setIntegerPart(((String) (obj)).substring(0, i));
                setFractionalPart(((String) (obj)).substring(i + 1));
            } else
            {
                setIntegerPart(((String) (obj)));
            }
            return this;
        }

        public DecimalBuilder setFractionalPart(String s)
        {
            return (DecimalBuilder)setStringArgument("android.arg.fractional_part", s);
        }

        public DecimalBuilder setIntegerPart(long l)
        {
            return setIntegerPart(String.valueOf(l));
        }

        public DecimalBuilder setIntegerPart(String s)
        {
            return (DecimalBuilder)setStringArgument("android.arg.integer_part", s);
        }

        public DecimalBuilder()
        {
            super("android.type.decimal");
        }

        public DecimalBuilder(double d, int i, int j)
        {
            this();
            setArgumentsFromDouble(d, i, j);
        }

        public DecimalBuilder(String s, String s1)
        {
            this();
            setIntegerPart(s);
            setFractionalPart(s1);
        }
    }

    public static class DigitsBuilder extends SemioticClassBuilder
    {

        public DigitsBuilder setDigits(String s)
        {
            return (DigitsBuilder)setStringArgument("android.arg.digits", s);
        }

        public DigitsBuilder()
        {
            super("android.type.digits");
        }

        public DigitsBuilder(String s)
        {
            this();
            setDigits(s);
        }
    }

    public static class ElectronicBuilder extends SemioticClassBuilder
    {

        public ElectronicBuilder setDomain(String s)
        {
            return (ElectronicBuilder)setStringArgument("android.arg.domain", s);
        }

        public ElectronicBuilder setEmailArguments(String s, String s1)
        {
            return setDomain(s1).setUsername(s);
        }

        public ElectronicBuilder setFragmentId(String s)
        {
            return (ElectronicBuilder)setStringArgument("android.arg.fragment_id", s);
        }

        public ElectronicBuilder setPassword(String s)
        {
            return (ElectronicBuilder)setStringArgument("android.arg.password", s);
        }

        public ElectronicBuilder setPath(String s)
        {
            return (ElectronicBuilder)setStringArgument("android.arg.path", s);
        }

        public ElectronicBuilder setPort(int i)
        {
            return (ElectronicBuilder)setIntArgument("android.arg.port", i);
        }

        public ElectronicBuilder setProtocol(String s)
        {
            return (ElectronicBuilder)setStringArgument("android.arg.protocol", s);
        }

        public ElectronicBuilder setQueryString(String s)
        {
            return (ElectronicBuilder)setStringArgument("android.arg.query_string", s);
        }

        public ElectronicBuilder setUsername(String s)
        {
            return (ElectronicBuilder)setStringArgument("android.arg.username", s);
        }

        public ElectronicBuilder()
        {
            super("android.type.electronic");
        }
    }

    public static class FractionBuilder extends SemioticClassBuilder
    {

        public FractionBuilder setDenominator(long l)
        {
            return setDenominator(String.valueOf(l));
        }

        public FractionBuilder setDenominator(String s)
        {
            return (FractionBuilder)setStringArgument("android.arg.denominator", s);
        }

        public FractionBuilder setIntegerPart(long l)
        {
            return setIntegerPart(String.valueOf(l));
        }

        public FractionBuilder setIntegerPart(String s)
        {
            return (FractionBuilder)setStringArgument("android.arg.integer_part", s);
        }

        public FractionBuilder setNumerator(long l)
        {
            return setNumerator(String.valueOf(l));
        }

        public FractionBuilder setNumerator(String s)
        {
            return (FractionBuilder)setStringArgument("android.arg.numerator", s);
        }

        public FractionBuilder()
        {
            super("android.type.fraction");
        }

        public FractionBuilder(long l, long l1, long l2)
        {
            this();
            setIntegerPart(l);
            setNumerator(l1);
            setDenominator(l2);
        }
    }

    public static class MeasureBuilder extends SemioticClassBuilder
    {

        public MeasureBuilder setDenominator(long l)
        {
            return setDenominator(String.valueOf(l));
        }

        public MeasureBuilder setDenominator(String s)
        {
            return (MeasureBuilder)setStringArgument("android.arg.denominator", s);
        }

        public MeasureBuilder setFractionalPart(String s)
        {
            return (MeasureBuilder)setStringArgument("android.arg.fractional_part", s);
        }

        public MeasureBuilder setIntegerPart(long l)
        {
            return setIntegerPart(String.valueOf(l));
        }

        public MeasureBuilder setIntegerPart(String s)
        {
            return (MeasureBuilder)setStringArgument("android.arg.integer_part", s);
        }

        public MeasureBuilder setNumber(long l)
        {
            return setNumber(String.valueOf(l));
        }

        public MeasureBuilder setNumber(String s)
        {
            return (MeasureBuilder)setStringArgument("android.arg.number", s);
        }

        public MeasureBuilder setNumerator(long l)
        {
            return setNumerator(String.valueOf(l));
        }

        public MeasureBuilder setNumerator(String s)
        {
            return (MeasureBuilder)setStringArgument("android.arg.numerator", s);
        }

        public MeasureBuilder setUnit(String s)
        {
            return (MeasureBuilder)setStringArgument("android.arg.unit", s);
        }

        public MeasureBuilder()
        {
            super("android.type.measure");
        }
    }

    public static class MoneyBuilder extends SemioticClassBuilder
    {

        public MoneyBuilder setCurrency(String s)
        {
            return (MoneyBuilder)setStringArgument("android.arg.money", s);
        }

        public MoneyBuilder setFractionalPart(String s)
        {
            return (MoneyBuilder)setStringArgument("android.arg.fractional_part", s);
        }

        public MoneyBuilder setIntegerPart(long l)
        {
            return setIntegerPart(String.valueOf(l));
        }

        public MoneyBuilder setIntegerPart(String s)
        {
            return (MoneyBuilder)setStringArgument("android.arg.integer_part", s);
        }

        public MoneyBuilder setQuantity(String s)
        {
            return (MoneyBuilder)setStringArgument("android.arg.quantity", s);
        }

        public MoneyBuilder()
        {
            super("android.type.money");
        }
    }

    public static class OrdinalBuilder extends SemioticClassBuilder
    {

        public OrdinalBuilder setNumber(long l)
        {
            return setNumber(String.valueOf(l));
        }

        public OrdinalBuilder setNumber(String s)
        {
            return (OrdinalBuilder)setStringArgument("android.arg.number", s);
        }

        public OrdinalBuilder()
        {
            super("android.type.ordinal");
        }

        public OrdinalBuilder(long l)
        {
            this();
            setNumber(l);
        }

        public OrdinalBuilder(String s)
        {
            this();
            setNumber(s);
        }
    }

    public static class SemioticClassBuilder extends Builder
    {

        public SemioticClassBuilder setAnimacy(String s)
        {
            return (SemioticClassBuilder)setStringArgument("android.arg.animacy", s);
        }

        public SemioticClassBuilder setCase(String s)
        {
            return (SemioticClassBuilder)setStringArgument("android.arg.case", s);
        }

        public SemioticClassBuilder setGender(String s)
        {
            return (SemioticClassBuilder)setStringArgument("android.arg.gender", s);
        }

        public SemioticClassBuilder setMultiplicity(String s)
        {
            return (SemioticClassBuilder)setStringArgument("android.arg.multiplicity", s);
        }

        public SemioticClassBuilder(String s)
        {
            super(s);
        }
    }

    public static class TelephoneBuilder extends SemioticClassBuilder
    {

        public TelephoneBuilder setCountryCode(String s)
        {
            return (TelephoneBuilder)setStringArgument("android.arg.country_code", s);
        }

        public TelephoneBuilder setExtension(String s)
        {
            return (TelephoneBuilder)setStringArgument("android.arg.extension", s);
        }

        public TelephoneBuilder setNumberParts(String s)
        {
            return (TelephoneBuilder)setStringArgument("android.arg.number_parts", s);
        }

        public TelephoneBuilder()
        {
            super("android.type.telephone");
        }

        public TelephoneBuilder(String s)
        {
            this();
            setNumberParts(s);
        }
    }

    public static class TextBuilder extends SemioticClassBuilder
    {

        public TextBuilder setText(String s)
        {
            return (TextBuilder)setStringArgument("android.arg.text", s);
        }

        public TextBuilder()
        {
            super("android.type.text");
        }

        public TextBuilder(String s)
        {
            this();
            setText(s);
        }
    }

    public static class TimeBuilder extends SemioticClassBuilder
    {

        public TimeBuilder setHours(int i)
        {
            return (TimeBuilder)setIntArgument("android.arg.hours", i);
        }

        public TimeBuilder setMinutes(int i)
        {
            return (TimeBuilder)setIntArgument("android.arg.minutes", i);
        }

        public TimeBuilder()
        {
            super("android.type.time");
        }

        public TimeBuilder(int i, int j)
        {
            this();
            setHours(i);
            setMinutes(j);
        }
    }

    public static class VerbatimBuilder extends SemioticClassBuilder
    {

        public VerbatimBuilder setVerbatim(String s)
        {
            return (VerbatimBuilder)setStringArgument("android.arg.verbatim", s);
        }

        public VerbatimBuilder()
        {
            super("android.type.verbatim");
        }

        public VerbatimBuilder(String s)
        {
            this();
            setVerbatim(s);
        }
    }


    public TtsSpan(Parcel parcel)
    {
        mType = parcel.readString();
        mArgs = parcel.readPersistableBundle();
    }

    public TtsSpan(String s, PersistableBundle persistablebundle)
    {
        mType = s;
        mArgs = persistablebundle;
    }

    public int describeContents()
    {
        return 0;
    }

    public PersistableBundle getArgs()
    {
        return mArgs;
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 24;
    }

    public String getType()
    {
        return mType;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        parcel.writeString(mType);
        parcel.writePersistableBundle(mArgs);
    }

    public static final String ANIMACY_ANIMATE = "android.animate";
    public static final String ANIMACY_INANIMATE = "android.inanimate";
    public static final String ARG_ANIMACY = "android.arg.animacy";
    public static final String ARG_CASE = "android.arg.case";
    public static final String ARG_COUNTRY_CODE = "android.arg.country_code";
    public static final String ARG_CURRENCY = "android.arg.money";
    public static final String ARG_DAY = "android.arg.day";
    public static final String ARG_DENOMINATOR = "android.arg.denominator";
    public static final String ARG_DIGITS = "android.arg.digits";
    public static final String ARG_DOMAIN = "android.arg.domain";
    public static final String ARG_EXTENSION = "android.arg.extension";
    public static final String ARG_FRACTIONAL_PART = "android.arg.fractional_part";
    public static final String ARG_FRAGMENT_ID = "android.arg.fragment_id";
    public static final String ARG_GENDER = "android.arg.gender";
    public static final String ARG_HOURS = "android.arg.hours";
    public static final String ARG_INTEGER_PART = "android.arg.integer_part";
    public static final String ARG_MINUTES = "android.arg.minutes";
    public static final String ARG_MONTH = "android.arg.month";
    public static final String ARG_MULTIPLICITY = "android.arg.multiplicity";
    public static final String ARG_NUMBER = "android.arg.number";
    public static final String ARG_NUMBER_PARTS = "android.arg.number_parts";
    public static final String ARG_NUMERATOR = "android.arg.numerator";
    public static final String ARG_PASSWORD = "android.arg.password";
    public static final String ARG_PATH = "android.arg.path";
    public static final String ARG_PORT = "android.arg.port";
    public static final String ARG_PROTOCOL = "android.arg.protocol";
    public static final String ARG_QUANTITY = "android.arg.quantity";
    public static final String ARG_QUERY_STRING = "android.arg.query_string";
    public static final String ARG_TEXT = "android.arg.text";
    public static final String ARG_UNIT = "android.arg.unit";
    public static final String ARG_USERNAME = "android.arg.username";
    public static final String ARG_VERBATIM = "android.arg.verbatim";
    public static final String ARG_WEEKDAY = "android.arg.weekday";
    public static final String ARG_YEAR = "android.arg.year";
    public static final String CASE_ABLATIVE = "android.ablative";
    public static final String CASE_ACCUSATIVE = "android.accusative";
    public static final String CASE_DATIVE = "android.dative";
    public static final String CASE_GENITIVE = "android.genitive";
    public static final String CASE_INSTRUMENTAL = "android.instrumental";
    public static final String CASE_LOCATIVE = "android.locative";
    public static final String CASE_NOMINATIVE = "android.nominative";
    public static final String CASE_VOCATIVE = "android.vocative";
    public static final String GENDER_FEMALE = "android.female";
    public static final String GENDER_MALE = "android.male";
    public static final String GENDER_NEUTRAL = "android.neutral";
    public static final int MONTH_APRIL = 3;
    public static final int MONTH_AUGUST = 7;
    public static final int MONTH_DECEMBER = 11;
    public static final int MONTH_FEBRUARY = 1;
    public static final int MONTH_JANUARY = 0;
    public static final int MONTH_JULY = 6;
    public static final int MONTH_JUNE = 5;
    public static final int MONTH_MARCH = 2;
    public static final int MONTH_MAY = 4;
    public static final int MONTH_NOVEMBER = 10;
    public static final int MONTH_OCTOBER = 9;
    public static final int MONTH_SEPTEMBER = 8;
    public static final String MULTIPLICITY_DUAL = "android.dual";
    public static final String MULTIPLICITY_PLURAL = "android.plural";
    public static final String MULTIPLICITY_SINGLE = "android.single";
    public static final String TYPE_CARDINAL = "android.type.cardinal";
    public static final String TYPE_DATE = "android.type.date";
    public static final String TYPE_DECIMAL = "android.type.decimal";
    public static final String TYPE_DIGITS = "android.type.digits";
    public static final String TYPE_ELECTRONIC = "android.type.electronic";
    public static final String TYPE_FRACTION = "android.type.fraction";
    public static final String TYPE_MEASURE = "android.type.measure";
    public static final String TYPE_MONEY = "android.type.money";
    public static final String TYPE_ORDINAL = "android.type.ordinal";
    public static final String TYPE_TELEPHONE = "android.type.telephone";
    public static final String TYPE_TEXT = "android.type.text";
    public static final String TYPE_TIME = "android.type.time";
    public static final String TYPE_VERBATIM = "android.type.verbatim";
    public static final int WEEKDAY_FRIDAY = 6;
    public static final int WEEKDAY_MONDAY = 2;
    public static final int WEEKDAY_SATURDAY = 7;
    public static final int WEEKDAY_SUNDAY = 1;
    public static final int WEEKDAY_THURSDAY = 5;
    public static final int WEEKDAY_TUESDAY = 3;
    public static final int WEEKDAY_WEDNESDAY = 4;
    private final PersistableBundle mArgs;
    private final String mType;
}
