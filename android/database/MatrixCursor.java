// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;

import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.database:
//            AbstractCursor, CursorIndexOutOfBoundsException, DatabaseUtils

public class MatrixCursor extends AbstractCursor
{
    public class RowBuilder
    {

        public RowBuilder add(Object obj)
        {
            if(index == endIndex)
            {
                throw new CursorIndexOutOfBoundsException("No more columns left.");
            } else
            {
                Object aobj[] = MatrixCursor._2D_get2(MatrixCursor.this);
                int i = index;
                index = i + 1;
                aobj[i] = obj;
                return this;
            }
        }

        public RowBuilder add(String s, Object obj)
        {
            for(int i = 0; i < MatrixCursor._2D_get1(MatrixCursor.this).length; i++)
                if(s.equals(MatrixCursor._2D_get1(MatrixCursor.this)[i]))
                    MatrixCursor._2D_get2(MatrixCursor.this)[row * MatrixCursor._2D_get0(MatrixCursor.this) + i] = obj;

            return this;
        }

        private final int endIndex;
        private int index;
        private final int row;
        final MatrixCursor this$0;

        RowBuilder(int i)
        {
            this$0 = MatrixCursor.this;
            super();
            row = i;
            index = MatrixCursor._2D_get0(MatrixCursor.this) * i;
            endIndex = index + MatrixCursor._2D_get0(MatrixCursor.this);
        }
    }


    static int _2D_get0(MatrixCursor matrixcursor)
    {
        return matrixcursor.columnCount;
    }

    static String[] _2D_get1(MatrixCursor matrixcursor)
    {
        return matrixcursor.columnNames;
    }

    static Object[] _2D_get2(MatrixCursor matrixcursor)
    {
        return matrixcursor.data;
    }

    public MatrixCursor(String as[])
    {
        this(as, 16);
    }

    public MatrixCursor(String as[], int i)
    {
        rowCount = 0;
        columnNames = as;
        columnCount = as.length;
        int j = i;
        if(i < 1)
            j = 1;
        data = new Object[columnCount * j];
    }

    private void addRow(ArrayList arraylist, int i)
    {
        int j = arraylist.size();
        if(j != columnCount)
            throw new IllegalArgumentException((new StringBuilder()).append("columnNames.length = ").append(columnCount).append(", columnValues.size() = ").append(j).toString());
        rowCount = rowCount + 1;
        Object aobj[] = data;
        for(int k = 0; k < j; k++)
            aobj[i + k] = arraylist.get(k);

    }

    private void ensureCapacity(int i)
    {
        if(i > data.length)
        {
            Object aobj[] = data;
            int j = data.length * 2;
            int k = j;
            if(j < i)
                k = i;
            data = new Object[k];
            System.arraycopy(((Object) (aobj)), 0, ((Object) (data)), 0, aobj.length);
        }
    }

    private Object get(int i)
    {
        if(i < 0 || i >= columnCount)
            throw new CursorIndexOutOfBoundsException((new StringBuilder()).append("Requested column: ").append(i).append(", # of columns: ").append(columnCount).toString());
        if(mPos < 0)
            throw new CursorIndexOutOfBoundsException("Before first row.");
        if(mPos >= rowCount)
            throw new CursorIndexOutOfBoundsException("After last row.");
        else
            return data[mPos * columnCount + i];
    }

    public void addRow(Iterable iterable)
    {
        int i = rowCount * columnCount;
        int j = i + columnCount;
        ensureCapacity(j);
        if(iterable instanceof ArrayList)
        {
            addRow((ArrayList)iterable, i);
            return;
        }
        Object aobj[] = data;
        for(Iterator iterator = iterable.iterator(); iterator.hasNext();)
        {
            iterable = ((Iterable) (iterator.next()));
            if(i == j)
                throw new IllegalArgumentException("columnValues.size() > columnNames.length");
            aobj[i] = iterable;
            i++;
        }

        if(i != j)
        {
            throw new IllegalArgumentException("columnValues.size() < columnNames.length");
        } else
        {
            rowCount = rowCount + 1;
            return;
        }
    }

    public void addRow(Object aobj[])
    {
        if(aobj.length != columnCount)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("columnNames.length = ").append(columnCount).append(", columnValues.length = ").append(aobj.length).toString());
        } else
        {
            int i = rowCount;
            rowCount = i + 1;
            i *= columnCount;
            ensureCapacity(columnCount + i);
            System.arraycopy(((Object) (aobj)), 0, ((Object) (data)), i, columnCount);
            return;
        }
    }

    public byte[] getBlob(int i)
    {
        return (byte[])get(i);
    }

    public String[] getColumnNames()
    {
        return columnNames;
    }

    public int getCount()
    {
        return rowCount;
    }

    public double getDouble(int i)
    {
        Object obj = get(i);
        if(obj == null)
            return 0.0D;
        if(obj instanceof Number)
            return ((Number)obj).doubleValue();
        else
            return Double.parseDouble(obj.toString());
    }

    public float getFloat(int i)
    {
        Object obj = get(i);
        if(obj == null)
            return 0.0F;
        if(obj instanceof Number)
            return ((Number)obj).floatValue();
        else
            return Float.parseFloat(obj.toString());
    }

    public int getInt(int i)
    {
        Object obj = get(i);
        if(obj == null)
            return 0;
        if(obj instanceof Number)
            return ((Number)obj).intValue();
        else
            return Integer.parseInt(obj.toString());
    }

    public long getLong(int i)
    {
        Object obj = get(i);
        if(obj == null)
            return 0L;
        if(obj instanceof Number)
            return ((Number)obj).longValue();
        else
            return Long.parseLong(obj.toString());
    }

    public short getShort(int i)
    {
        Object obj = get(i);
        if(obj == null)
            return 0;
        if(obj instanceof Number)
            return ((Number)obj).shortValue();
        else
            return Short.parseShort(obj.toString());
    }

    public String getString(int i)
    {
        Object obj = get(i);
        if(obj == null)
            return null;
        else
            return obj.toString();
    }

    public int getType(int i)
    {
        return DatabaseUtils.getTypeOfObject(get(i));
    }

    public boolean isNull(int i)
    {
        boolean flag;
        if(get(i) == null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public RowBuilder newRow()
    {
        int i = rowCount;
        rowCount = i + 1;
        ensureCapacity(rowCount * columnCount);
        return new RowBuilder(i);
    }

    private final int columnCount;
    private final String columnNames[];
    private Object data[];
    private int rowCount;
}
