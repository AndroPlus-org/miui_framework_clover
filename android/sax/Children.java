// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.sax;


// Referenced classes of package android.sax:
//            Element

class Children
{
    static class Child extends Element
    {

        final int hash;
        Child next;

        Child(Element element, String s, String s1, int i, int j)
        {
            super(element, s, s1, i);
            hash = j;
        }
    }


    Children()
    {
        children = new Child[16];
    }

    Element get(String s, String s1)
    {
        int i;
        Child child1;
        i = s.hashCode() * 31 + s1.hashCode();
        Child child = children[i & 0xf];
        child1 = child;
        if(child == null)
            return null;
_L4:
        if(child1.hash == i && child1.uri.compareTo(s) == 0 && child1.localName.compareTo(s1) == 0)
            return child1;
        if(true) goto _L2; else goto _L1
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        child1 = child1.next;
        if(child1 == null)
            break; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L3
_L3:
        return null;
    }

    Element getOrCreate(Element element, String s, String s1)
    {
        int i;
        Child child2;
label0:
        {
            i = s.hashCode() * 31 + s1.hashCode();
            int j = i & 0xf;
            Child child = children[j];
            child2 = child;
            if(child == null)
            {
                element = new Child(element, s, s1, element.depth + 1, i);
                children[j] = element;
                return element;
            }
            Child child1;
            for(; child2.hash != i || child2.uri.compareTo(s) != 0 || child2.localName.compareTo(s1) != 0; child2 = child1)
            {
                child1 = child2.next;
                if(child1 == null)
                    break label0;
            }

            return child2;
        }
        element = new Child(element, s, s1, element.depth + 1, i);
        child2.next = element;
        return element;
    }

    Child children[];
}
