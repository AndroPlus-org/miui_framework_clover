// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.util.ArraySet;
import android.util.ExceptionUtils;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

// Referenced classes of package com.android.internal.util:
//            ArrayUtils

public class CollectionUtils
{

    private CollectionUtils()
    {
    }

    public static List add(List list, Object obj)
    {
        Object obj1;
label0:
        {
            if(list != null)
            {
                obj1 = list;
                if(list != Collections.emptyList())
                    break label0;
            }
            obj1 = new ArrayList();
        }
        ((List) (obj1)).add(obj);
        return ((List) (obj1));
    }

    public static Set add(Set set, Object obj)
    {
        Object obj1;
label0:
        {
            if(set != null)
            {
                obj1 = set;
                if(set != Collections.emptySet())
                    break label0;
            }
            obj1 = new ArraySet();
        }
        ((Set) (obj1)).add(obj);
        return ((Set) (obj1));
    }

    public static boolean any(List list, Predicate predicate)
    {
        boolean flag;
        if(find(list, predicate) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static List copyOf(List list)
    {
        if(ArrayUtils.isEmpty(list))
            list = Collections.emptyList();
        else
            list = new ArrayList(list);
        return list;
    }

    public static Set copyOf(Set set)
    {
        if(ArrayUtils.isEmpty(set))
            set = Collections.emptySet();
        else
            set = new ArraySet(set);
        return set;
    }

    public static List emptyIfNull(List list)
    {
        List list1 = list;
        if(list == null)
            list1 = Collections.emptyList();
        return list1;
    }

    public static Set emptyIfNull(Set set)
    {
        Set set1 = set;
        if(set == null)
            set1 = Collections.emptySet();
        return set1;
    }

    public static List filter(List list, Class class1)
    {
        if(ArrayUtils.isEmpty(list))
            return Collections.emptyList();
        ArrayList arraylist = null;
        for(int i = 0; i < list.size();)
        {
            Object obj = list.get(i);
            ArrayList arraylist1 = arraylist;
            if(class1.isInstance(obj))
                arraylist1 = ArrayUtils.add(arraylist, obj);
            i++;
            arraylist = arraylist1;
        }

        return emptyIfNull(arraylist);
    }

    public static List filter(List list, Predicate predicate)
    {
        ArrayList arraylist = null;
        for(int i = 0; i < size(list);)
        {
            Object obj = list.get(i);
            ArrayList arraylist1 = arraylist;
            if(predicate.test(obj))
                arraylist1 = ArrayUtils.add(arraylist, obj);
            i++;
            arraylist = arraylist1;
        }

        return emptyIfNull(arraylist);
    }

    public static Set filter(Set set, Predicate predicate)
    {
        if(set == null || set.size() == 0)
            return Collections.emptySet();
        ArraySet arrayset = null;
        Object obj = null;
        if(set instanceof ArraySet)
        {
            arrayset = (ArraySet)set;
            int i = arrayset.size();
            int j = 0;
            set = ((Set) (obj));
            do
            {
                obj = set;
                if(j >= i)
                    break;
                Object obj1 = arrayset.valueAt(j);
                obj = set;
                if(predicate.test(obj1))
                    obj = ArrayUtils.add(set, obj1);
                j++;
                set = ((Set) (obj));
            } while(true);
        } else
        {
            Iterator iterator = set.iterator();
            set = arrayset;
            do
            {
                obj = set;
                if(!iterator.hasNext())
                    break;
                obj = iterator.next();
                if(predicate.test(obj))
                    set = ArrayUtils.add(set, obj);
            } while(true);
        }
        return emptyIfNull(((Set) (obj)));
    }

    public static Object find(List list, Predicate predicate)
    {
        if(ArrayUtils.isEmpty(list))
            return null;
        for(int i = 0; i < list.size(); i++)
        {
            Object obj = list.get(i);
            if(predicate.test(obj))
                return obj;
        }

        return null;
    }

    public static void forEach(Set set, FunctionalUtils.ThrowingConsumer throwingconsumer)
    {
        int i;
        if(set == null || throwingconsumer == null)
            return;
        i = set.size();
        if(i == 0)
            return;
        if(!(set instanceof ArraySet))
            break MISSING_BLOCK_LABEL_57;
        set = (ArraySet)set;
        int j = 0;
        while(j < i) 
        {
            try
            {
                throwingconsumer.accept(set.valueAt(j));
            }
            // Misplaced declaration of an exception variable
            catch(Set set)
            {
                throw ExceptionUtils.propagate(set);
            }
            j++;
        }
        break MISSING_BLOCK_LABEL_94;
        for(set = set.iterator(); set.hasNext(); throwingconsumer.accept(set.next()));
    }

    public static List map(List list, Function function)
    {
        if(ArrayUtils.isEmpty(list))
            return Collections.emptyList();
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < list.size(); i++)
            arraylist.add(function.apply(list.get(i)));

        return arraylist;
    }

    public static Set map(Set set, Function function)
    {
        if(ArrayUtils.isEmpty(set))
            return Collections.emptySet();
        ArraySet arrayset = new ArraySet();
        if(set instanceof ArraySet)
        {
            set = (ArraySet)set;
            int i = set.size();
            for(int j = 0; j < i; j++)
                arrayset.add(function.apply(set.valueAt(j)));

        } else
        {
            for(set = set.iterator(); set.hasNext(); arrayset.add(function.apply(set.next())));
        }
        return arrayset;
    }

    public static List mapNotNull(List list, Function function)
    {
        if(ArrayUtils.isEmpty(list))
            return Collections.emptyList();
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < list.size(); i++)
        {
            Object obj = function.apply(list.get(i));
            if(obj != null)
                arraylist.add(obj);
        }

        return arraylist;
    }

    public static List remove(List list, Object obj)
    {
        if(ArrayUtils.isEmpty(list))
        {
            return emptyIfNull(list);
        } else
        {
            list.remove(obj);
            return list;
        }
    }

    public static Set remove(Set set, Object obj)
    {
        if(ArrayUtils.isEmpty(set))
        {
            return emptyIfNull(set);
        } else
        {
            set.remove(obj);
            return set;
        }
    }

    public static int size(Collection collection)
    {
        int i;
        if(collection != null)
            i = collection.size();
        else
            i = 0;
        return i;
    }
}
