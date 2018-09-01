// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.IBinder;

final class JniParcel
{

    public JniParcel(int i, int j)
    {
        numbers = new long[i];
        numbersCount = 0;
        readPosOfNumbers = 0;
        objects = new Object[j];
        objectsCount = 0;
        readPosOfObjects = 0;
    }

    private void writeLong1(long l)
    {
        int i = numbersCount;
        if(i + 1 <= numbers.length)
        {
            numbers[i] = l;
            numbersCount = numbersCount + 1;
        }
    }

    private void writeLong2(long l, long l1)
    {
        int i = numbersCount;
        if(i + 2 <= numbers.length)
        {
            numbers[i] = l;
            numbers[i + 1] = l1;
            numbersCount = numbersCount + 2;
        }
    }

    private void writeLong3(long l, long l1, long l2)
    {
        int i = numbersCount;
        if(i + 3 <= numbers.length)
        {
            numbers[i] = l;
            numbers[i + 1] = l1;
            numbers[i + 2] = l2;
            numbersCount = numbersCount + 3;
        }
    }

    private void writeLong4(long l, long l1, long l2, long l3)
    {
        int i = numbersCount;
        if(i + 4 <= numbers.length)
        {
            numbers[i] = l;
            numbers[i + 1] = l1;
            numbers[i + 2] = l2;
            numbers[i + 3] = l3;
            numbersCount = numbersCount + 4;
        }
    }

    private void writeLong5(long l, long l1, long l2, long l3, long l4)
    {
        int i = numbersCount;
        if(i + 5 <= numbers.length)
        {
            numbers[i] = l;
            numbers[i + 1] = l1;
            numbers[i + 2] = l2;
            numbers[i + 3] = l3;
            numbers[i + 4] = l4;
            numbersCount = numbersCount + 5;
        }
    }

    private void writeLong6(long l, long l1, long l2, long l3, long l4, long l5)
    {
        int i = numbersCount;
        if(i + 6 <= numbers.length)
        {
            numbers[i] = l;
            numbers[i + 1] = l1;
            numbers[i + 2] = l2;
            numbers[i + 3] = l3;
            numbers[i + 4] = l4;
            numbers[i + 5] = l5;
            numbersCount = numbersCount + 6;
        }
    }

    private void writeLong7(long l, long l1, long l2, long l3, long l4, long l5, long l6)
    {
        int i = numbersCount;
        if(i + 7 <= numbers.length)
        {
            numbers[i] = l;
            numbers[i + 1] = l1;
            numbers[i + 2] = l2;
            numbers[i + 3] = l3;
            numbers[i + 4] = l4;
            numbers[i + 5] = l5;
            numbers[i + 6] = l6;
            numbersCount = numbersCount + 7;
        }
    }

    private void writeLong8(long l, long l1, long l2, long l3, long l4, long l5, long l6, 
            long l7)
    {
        int i = numbersCount;
        if(i + 8 <= numbers.length)
        {
            numbers[i] = l;
            numbers[i + 1] = l1;
            numbers[i + 2] = l2;
            numbers[i + 3] = l3;
            numbers[i + 4] = l4;
            numbers[i + 5] = l5;
            numbers[i + 6] = l6;
            numbers[i + 7] = l7;
            numbersCount = numbersCount + 8;
        }
    }

    private void writeLong9(long l, long l1, long l2, long l3, long l4, long l5, long l6, 
            long l7, long l8)
    {
        int i = numbersCount;
        if(i + 9 <= numbers.length)
        {
            numbers[i] = l;
            numbers[i + 1] = l1;
            numbers[i + 2] = l2;
            numbers[i + 3] = l3;
            numbers[i + 4] = l4;
            numbers[i + 5] = l5;
            numbers[i + 6] = l6;
            numbers[i + 7] = l7;
            numbers[i + 8] = l8;
            numbersCount = numbersCount + 9;
        }
    }

    private void writeObject1(Object obj)
    {
        int i = objectsCount;
        if(i + 1 <= objects.length)
        {
            objects[i] = obj;
            objectsCount = objectsCount + 1;
        }
    }

    private void writeObject2(Object obj, Object obj1)
    {
        int i = objectsCount;
        if(i + 2 <= objects.length)
        {
            objects[i] = obj;
            objects[i + 1] = obj1;
            objectsCount = objectsCount + 2;
        }
    }

    private void writeObject3(Object obj, Object obj1, Object obj2)
    {
        int i = objectsCount;
        if(i + 3 <= objects.length)
        {
            objects[i] = obj;
            objects[i + 1] = obj1;
            objects[i + 2] = obj2;
            objectsCount = objectsCount + 3;
        }
    }

    private void writeObject4(Object obj, Object obj1, Object obj2, Object obj3)
    {
        int i = objectsCount;
        if(i + 4 <= objects.length)
        {
            objects[i] = obj;
            objects[i + 1] = obj1;
            objects[i + 2] = obj2;
            objects[i + 3] = obj3;
            objectsCount = objectsCount + 4;
        }
    }

    private void writeObject5(Object obj, Object obj1, Object obj2, Object obj3, Object obj4)
    {
        int i = objectsCount;
        if(i + 5 <= objects.length)
        {
            objects[i] = obj;
            objects[i + 1] = obj1;
            objects[i + 2] = obj2;
            objects[i + 3] = obj3;
            objects[i + 4] = obj4;
            objectsCount = objectsCount + 5;
        }
    }

    private void writeObject6(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5)
    {
        int i = objectsCount;
        if(i + 6 <= objects.length)
        {
            objects[i] = obj;
            objects[i + 1] = obj1;
            objects[i + 2] = obj2;
            objects[i + 3] = obj3;
            objects[i + 4] = obj4;
            objects[i + 5] = obj5;
            objectsCount = objectsCount + 6;
        }
    }

    private void writeObject7(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6)
    {
        int i = objectsCount;
        if(i + 7 <= objects.length)
        {
            objects[i] = obj;
            objects[i + 1] = obj1;
            objects[i + 2] = obj2;
            objects[i + 3] = obj3;
            objects[i + 4] = obj4;
            objects[i + 5] = obj5;
            objects[i + 6] = obj6;
            objectsCount = objectsCount + 7;
        }
    }

    private void writeObject8(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, 
            Object obj7)
    {
        int i = objectsCount;
        if(i + 8 <= objects.length)
        {
            objects[i] = obj;
            objects[i + 1] = obj1;
            objects[i + 2] = obj2;
            objects[i + 3] = obj3;
            objects[i + 4] = obj4;
            objects[i + 5] = obj5;
            objects[i + 6] = obj6;
            objects[i + 7] = obj7;
            objectsCount = objectsCount + 8;
        }
    }

    private void writeObject9(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, 
            Object obj7, Object obj8)
    {
        int i = objectsCount;
        if(i + 9 <= objects.length)
        {
            objects[i] = obj;
            objects[i + 1] = obj1;
            objects[i + 2] = obj2;
            objects[i + 3] = obj3;
            objects[i + 4] = obj4;
            objects[i + 5] = obj5;
            objects[i + 6] = obj6;
            objects[i + 7] = obj7;
            objects[i + 8] = obj8;
            objectsCount = objectsCount + 9;
        }
    }

    public final IBinder readBinder()
    {
        int i = readPosOfObjects;
        if(i < objectsCount)
        {
            readPosOfObjects = readPosOfObjects + 1;
            return (IBinder)objects[i];
        } else
        {
            return null;
        }
    }

    public final long readLong()
    {
        int i = readPosOfNumbers;
        if(i < numbersCount)
        {
            readPosOfNumbers = readPosOfNumbers + 1;
            return numbers[i];
        } else
        {
            return 0L;
        }
    }

    public final Object readObject()
    {
        int i = readPosOfObjects;
        if(i < objectsCount)
        {
            readPosOfObjects = readPosOfObjects + 1;
            return objects[i];
        } else
        {
            return null;
        }
    }

    public final String readString()
    {
        int i = readPosOfObjects;
        if(i < objectsCount)
        {
            readPosOfObjects = readPosOfObjects + 1;
            String s = (String)objects[i];
            String s1 = s;
            if(s == null)
                s1 = "";
            return s1;
        } else
        {
            return "";
        }
    }

    public final void reset()
    {
        numbersCount = 0;
        readPosOfNumbers = 0;
        int i = objectsCount;
        objectsCount = 0;
        readPosOfObjects = 0;
        for(int j = 0; j < i; j++)
            objects[j] = null;

    }

    private final long numbers[];
    private int numbersCount;
    private final Object objects[];
    private int objectsCount;
    private int readPosOfNumbers;
    private int readPosOfObjects;
}
