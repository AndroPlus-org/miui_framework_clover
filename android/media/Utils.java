// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.FileUtils;
import android.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Utils
{

    Utils()
    {
    }

    static Range alignRange(Range range, int i)
    {
        return range.intersect(Integer.valueOf(divUp(((Integer)range.getLower()).intValue(), i) * i), Integer.valueOf((((Integer)range.getUpper()).intValue() / i) * i));
    }

    public static int binarySearchDistinctRanges(Range arange[], Comparable comparable)
    {
        return Arrays.binarySearch(arange, Range.create(comparable, comparable), new Comparator() {

            public int compare(Range range, Range range1)
            {
                if(range.getUpper().compareTo(range1.getLower()) < 0)
                    return -1;
                return range.getLower().compareTo(range1.getUpper()) <= 0 ? 0 : 1;
            }

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((Range)obj, (Range)obj1);
            }

        }
);
    }

    static int divUp(int i, int j)
    {
        return ((i + j) - 1) / j;
    }

    static long divUp(long l, long l1)
    {
        return ((l + l1) - 1L) / l1;
    }

    static Range factorRange(Range range, int i)
    {
        if(i == 1)
            return range;
        else
            return Range.create(Integer.valueOf(divUp(((Integer)range.getLower()).intValue(), i)), Integer.valueOf(((Integer)range.getUpper()).intValue() / i));
    }

    static Range factorRange(Range range, long l)
    {
        if(l == 1L)
            return range;
        else
            return Range.create(Long.valueOf(divUp(((Long)range.getLower()).longValue(), l)), Long.valueOf(((Long)range.getUpper()).longValue() / l));
    }

    static int gcd(int i, int j)
    {
        if(i == 0 && j == 0)
            return 1;
        int k = j;
        if(j < 0)
            k = -j;
        j = i;
        int l = k;
        if(i < 0)
        {
            j = -i;
            l = k;
        }
        do
        {
            i = l;
            if(j != 0)
            {
                l = j;
                j = i % j;
            } else
            {
                return i;
            }
        } while(true);
    }

    static String getFileDisplayNameFromUri(Context context, Uri uri)
    {
        String s;
        Object obj;
        Object obj1;
        s = null;
        obj = null;
        obj1 = uri.getScheme();
        if("file".equals(obj1))
            return uri.getLastPathSegment();
        if(!"content".equals(obj1)) goto _L2; else goto _L1
_L1:
        Object obj2;
        obj2 = null;
        obj1 = null;
        context = context.getContentResolver().query(uri, new String[] {
            "_display_name"
        }, null, null, null);
        if(context == null)
            break MISSING_BLOCK_LABEL_141;
        obj1 = context;
        obj2 = context;
        if(context.getCount() == 0)
            break MISSING_BLOCK_LABEL_141;
        obj1 = context;
        obj2 = context;
        context.moveToFirst();
        obj1 = context;
        obj2 = context;
        s = context.getString(context.getColumnIndex("_display_name"));
        uri = obj;
        if(context == null)
            break MISSING_BLOCK_LABEL_129;
        context.close();
        uri = obj;
_L3:
        if(uri != null)
            throw uri;
        else
            return s;
        uri;
          goto _L3
        obj1 = s;
        if(context == null)
            break MISSING_BLOCK_LABEL_157;
        context.close();
        obj1 = s;
_L4:
        if(obj1 != null)
            throw obj1;
        break; /* Loop/switch isn't completed */
        obj1;
        if(true) goto _L4; else goto _L2
        context;
        throw context;
        uri;
_L7:
        obj2 = context;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_192;
        ((Cursor) (obj1)).close();
        obj2 = context;
_L5:
        if(obj2 != null)
            throw obj2;
        else
            throw uri;
        obj1;
        if(context == null)
        {
            obj2 = obj1;
        } else
        {
            obj2 = context;
            if(context != obj1)
            {
                context.addSuppressed(((Throwable) (obj1)));
                obj2 = context;
            }
        }
          goto _L5
_L2:
        return uri.toString();
        uri;
        context = null;
        obj1 = obj2;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static File getUniqueExternalFile(Context context, String s, String s1, String s2)
    {
        context = Environment.getExternalStoragePublicDirectory(s);
        context.mkdirs();
        try
        {
            context = FileUtils.buildUniqueFile(context, s2, s1);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("Utils", (new StringBuilder()).append("Unable to get a unique file name: ").append(context).toString());
            return null;
        }
        return context;
    }

    static Range intRangeFor(double d)
    {
        return Range.create(Integer.valueOf((int)d), Integer.valueOf((int)Math.ceil(d)));
    }

    public static Range[] intersectSortedDistinctRanges(Range arange[], Range arange1[])
    {
        int i = 0;
        int j = 0;
        Vector vector = new Vector();
        int k = arange1.length;
        do
        {
            Range range;
label0:
            {
                if(i < k)
                {
                    range = arange1[i];
                    int l = j;
                    do
                    {
                        j = l;
                        if(l >= arange.length)
                            break;
                        j = l;
                        if(arange[l].getUpper().compareTo(range.getLower()) >= 0)
                            break;
                        l++;
                    } while(true);
                    for(; j < arange.length && arange[j].getUpper().compareTo(range.getUpper()) < 0; j++)
                        vector.add(range.intersect(arange[j]));

                    if(j != arange.length)
                        break label0;
                }
                return (Range[])vector.toArray(new Range[vector.size()]);
            }
            if(arange[j].getLower().compareTo(range.getUpper()) <= 0)
                vector.add(range.intersect(arange[j]));
            i++;
        } while(true);
    }

    private static long lcm(int i, int j)
    {
        if(i == 0 || j == 0)
            throw new IllegalArgumentException("lce is not defined for zero arguments");
        else
            return ((long)i * (long)j) / (long)gcd(i, j);
    }

    static Range longRangeFor(double d)
    {
        return Range.create(Long.valueOf((long)d), Long.valueOf((long)Math.ceil(d)));
    }

    static Range parseIntRange(Object obj, Range range)
    {
        Object obj1;
        int i;
        obj1 = (String)obj;
        i = ((String) (obj1)).indexOf('-');
        if(i < 0)
            break MISSING_BLOCK_LABEL_49;
        return Range.create(Integer.valueOf(Integer.parseInt(((String) (obj1)).substring(0, i), 10)), Integer.valueOf(Integer.parseInt(((String) (obj1)).substring(i + 1), 10)));
        int j = Integer.parseInt(((String) (obj1)));
        obj1 = Range.create(Integer.valueOf(j), Integer.valueOf(j));
        return ((Range) (obj1));
        obj;
        return range;
        Object obj2;
        obj2;
_L2:
        Log.w("Utils", (new StringBuilder()).append("could not parse integer range '").append(obj).append("'").toString());
        return range;
        obj2;
        continue; /* Loop/switch isn't completed */
        obj2;
        if(true) goto _L2; else goto _L1
_L1:
    }

    static int parseIntSafely(Object obj, int i)
    {
        if(obj == null)
            return i;
        int j = Integer.parseInt((String)obj);
        return j;
        obj;
        return i;
        Object obj1;
        obj1;
_L2:
        Log.w("Utils", (new StringBuilder()).append("could not parse integer '").append(obj).append("'").toString());
        return i;
        obj1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    static Range parseLongRange(Object obj, Range range)
    {
        Object obj1;
        int i;
        obj1 = (String)obj;
        i = ((String) (obj1)).indexOf('-');
        if(i < 0)
            break MISSING_BLOCK_LABEL_49;
        return Range.create(Long.valueOf(Long.parseLong(((String) (obj1)).substring(0, i), 10)), Long.valueOf(Long.parseLong(((String) (obj1)).substring(i + 1), 10)));
        long l = Long.parseLong(((String) (obj1)));
        obj1 = Range.create(Long.valueOf(l), Long.valueOf(l));
        return ((Range) (obj1));
        obj;
        return range;
        Object obj2;
        obj2;
_L2:
        Log.w("Utils", (new StringBuilder()).append("could not parse long range '").append(obj).append("'").toString());
        return range;
        obj2;
        continue; /* Loop/switch isn't completed */
        obj2;
        if(true) goto _L2; else goto _L1
_L1:
    }

    static Range parseRationalRange(Object obj, Range range)
    {
        Object obj1;
        int i;
        obj1 = (String)obj;
        i = ((String) (obj1)).indexOf('-');
        if(i < 0)
            break MISSING_BLOCK_LABEL_39;
        return Range.create(Rational.parseRational(((String) (obj1)).substring(0, i)), Rational.parseRational(((String) (obj1)).substring(i + 1)));
        obj1 = Rational.parseRational(((String) (obj1)));
        obj1 = Range.create(((Comparable) (obj1)), ((Comparable) (obj1)));
        return ((Range) (obj1));
        obj;
        return range;
        Object obj2;
        obj2;
_L2:
        Log.w("Utils", (new StringBuilder()).append("could not parse rational range '").append(obj).append("'").toString());
        return range;
        obj2;
        continue; /* Loop/switch isn't completed */
        obj2;
        if(true) goto _L2; else goto _L1
_L1:
    }

    static Size parseSize(Object obj, Size size)
    {
        Size size1 = Size.parseSize((String)obj);
        return size1;
        obj;
        return size;
        Object obj1;
        obj1;
_L2:
        Log.w("Utils", (new StringBuilder()).append("could not parse size '").append(obj).append("'").toString());
        return size;
        obj1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    static Pair parseSizeRange(Object obj)
    {
        Object obj1;
        int i;
        obj1 = (String)obj;
        i = ((String) (obj1)).indexOf('-');
        if(i < 0)
            break MISSING_BLOCK_LABEL_39;
        return Pair.create(Size.parseSize(((String) (obj1)).substring(0, i)), Size.parseSize(((String) (obj1)).substring(i + 1)));
        obj1 = Size.parseSize(((String) (obj1)));
        obj1 = Pair.create(obj1, obj1);
        return ((Pair) (obj1));
        obj;
        return null;
        Object obj2;
        obj2;
_L2:
        Log.w("Utils", (new StringBuilder()).append("could not parse size range '").append(obj).append("'").toString());
        return null;
        obj2;
        continue; /* Loop/switch isn't completed */
        obj2;
        if(true) goto _L2; else goto _L1
_L1:
    }

    static Range scaleRange(Range range, int i, int j)
    {
        if(i == j)
            return range;
        else
            return Range.create(scaleRatio((Rational)range.getLower(), i, j), scaleRatio((Rational)range.getUpper(), i, j));
    }

    private static Rational scaleRatio(Rational rational, int i, int j)
    {
        int k = gcd(i, j);
        i /= k;
        j /= k;
        return new Rational((int)((double)rational.getNumerator() * (double)i), (int)((double)rational.getDenominator() * (double)j));
    }

    public static void sortDistinctRanges(Range arange[])
    {
        Arrays.sort(arange, new Comparator() {

            public int compare(Range range, Range range1)
            {
                if(range.getUpper().compareTo(range1.getLower()) < 0)
                    return -1;
                if(range.getLower().compareTo(range1.getUpper()) > 0)
                    return 1;
                else
                    throw new IllegalArgumentException((new StringBuilder()).append("sample rate ranges must be distinct (").append(range).append(" and ").append(range1).append(")").toString());
            }

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((Range)obj, (Range)obj1);
            }

        }
);
    }

    private static final String TAG = "Utils";
}
