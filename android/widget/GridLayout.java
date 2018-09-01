// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.*;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Array;
import java.util.*;

// Referenced classes of package android.widget:
//            Space

public class GridLayout extends ViewGroup
{
    public static abstract class Alignment
    {

        abstract int getAlignmentValue(View view, int i, int j);

        Bounds getBounds()
        {
            return new Bounds(null);
        }

        abstract int getGravityOffset(View view, int i);

        int getSizeInCell(View view, int i, int j)
        {
            return i;
        }

        Alignment()
        {
        }
    }

    static final class Arc
    {

        public String toString()
        {
            StringBuilder stringbuilder = (new StringBuilder()).append(span).append(" ");
            String s;
            if(!valid)
                s = "+>";
            else
                s = "->";
            return stringbuilder.append(s).append(" ").append(value).toString();
        }

        public final Interval span;
        public boolean valid;
        public final MutableInt value;

        public Arc(Interval interval, MutableInt mutableint)
        {
            valid = true;
            span = interval;
            value = mutableint;
        }
    }

    static final class Assoc extends ArrayList
    {

        public static Assoc of(Class class1, Class class2)
        {
            return new Assoc(class1, class2);
        }

        public PackedMap pack()
        {
            int i = size();
            Object aobj[] = (Object[])Array.newInstance(keyType, i);
            Object aobj1[] = (Object[])Array.newInstance(valueType, i);
            for(int j = 0; j < i; j++)
            {
                aobj[j] = ((Pair)get(j)).first;
                aobj1[j] = ((Pair)get(j)).second;
            }

            return new PackedMap(aobj, aobj1, null);
        }

        public void put(Object obj, Object obj1)
        {
            add(Pair.create(obj, obj1));
        }

        private final Class keyType;
        private final Class valueType;

        private Assoc(Class class1, Class class2)
        {
            keyType = class1;
            valueType = class2;
        }
    }

    final class Axis
    {

        private void addComponentSizes(List list, PackedMap packedmap)
        {
            for(int i = 0; i < ((Interval[])packedmap.keys).length; i++)
                include(list, ((Interval[])packedmap.keys)[i], ((MutableInt[])packedmap.values)[i], false);

        }

        private String arcsToString(List list)
        {
            String s;
            StringBuilder stringbuilder;
            boolean flag;
            Iterator iterator;
            if(horizontal)
                s = "x";
            else
                s = "y";
            stringbuilder = new StringBuilder();
            flag = true;
            iterator = list.iterator();
            list = stringbuilder;
            while(iterator.hasNext()) 
            {
                Object obj = (Arc)iterator.next();
                int i;
                int j;
                int k;
                if(flag)
                    flag = false;
                else
                    list = list.append(", ");
                i = ((Arc) (obj)).span.min;
                j = ((Arc) (obj)).span.max;
                k = ((Arc) (obj)).value.value;
                if(i < j)
                    obj = (new StringBuilder()).append(s).append(j).append("-").append(s).append(i).append(">=").append(k).toString();
                else
                    obj = (new StringBuilder()).append(s).append(i).append("-").append(s).append(j).append("<=").append(-k).toString();
                list.append(((String) (obj)));
            }
            return list.toString();
        }

        private int calculateMaxIndex()
        {
            int i = -1;
            int j = 0;
            int k = getChildCount();
            while(j < k) 
            {
                Object obj = getChildAt(j);
                obj = getLayoutParams(((View) (obj)));
                if(horizontal)
                    obj = ((LayoutParams) (obj)).columnSpec;
                else
                    obj = ((LayoutParams) (obj)).rowSpec;
                obj = ((Spec) (obj)).span;
                i = Math.max(Math.max(Math.max(i, ((Interval) (obj)).min), ((Interval) (obj)).max), ((Interval) (obj)).size());
                j++;
            }
            j = i;
            if(i == -1)
                j = 0x80000000;
            return j;
        }

        private float calculateTotalWeight()
        {
            float f = 0.0F;
            int i = 0;
            int j = getChildCount();
            while(i < j) 
            {
                Object obj = getChildAt(i);
                if(((View) (obj)).getVisibility() != 8)
                {
                    obj = getLayoutParams(((View) (obj)));
                    if(horizontal)
                        obj = ((LayoutParams) (obj)).columnSpec;
                    else
                        obj = ((LayoutParams) (obj)).rowSpec;
                    f += ((Spec) (obj)).weight;
                }
                i++;
            }
            return f;
        }

        private void computeArcs()
        {
            getForwardLinks();
            getBackwardLinks();
        }

        private void computeGroupBounds()
        {
            Bounds abounds[] = (Bounds[])groupBounds.values;
            for(int i = 0; i < abounds.length; i++)
                abounds[i].reset();

            int j = 0;
            int k = getChildCount();
            while(j < k) 
            {
                View view = getChildAt(j);
                Object obj = getLayoutParams(view);
                int l;
                int i1;
                if(horizontal)
                    obj = ((LayoutParams) (obj)).columnSpec;
                else
                    obj = ((LayoutParams) (obj)).rowSpec;
                l = getMeasurementIncludingMargin(view, horizontal);
                if(((Spec) (obj)).weight == 0.0F)
                    i1 = 0;
                else
                    i1 = getDeltas()[j];
                ((Bounds)groupBounds.getValue(j)).include(GridLayout.this, view, ((Spec) (obj)), this, l + i1);
                j++;
            }
        }

        private boolean computeHasWeights()
        {
            int i;
            int j;
            i = 0;
            j = getChildCount();
_L2:
            Object obj;
            if(i >= j)
                break MISSING_BLOCK_LABEL_79;
            obj = getChildAt(i);
            if(((View) (obj)).getVisibility() != 8)
                break; /* Loop/switch isn't completed */
_L4:
            i++;
            if(true) goto _L2; else goto _L1
_L1:
            obj = getLayoutParams(((View) (obj)));
            if(horizontal)
                obj = ((LayoutParams) (obj)).columnSpec;
            else
                obj = ((LayoutParams) (obj)).rowSpec;
            if(((Spec) (obj)).weight == 0.0F) goto _L4; else goto _L3
_L3:
            return true;
            return false;
        }

        private void computeLinks(PackedMap packedmap, boolean flag)
        {
            MutableInt amutableint[] = (MutableInt[])packedmap.values;
            for(int i = 0; i < amutableint.length; i++)
                amutableint[i].reset();

            Bounds abounds[] = (Bounds[])getGroupBounds().values;
            int j = 0;
            while(j < abounds.length) 
            {
                int k = abounds[j].size(flag);
                MutableInt mutableint = (MutableInt)packedmap.getValue(j);
                int l = mutableint.value;
                if(!flag)
                    k = -k;
                mutableint.value = Math.max(l, k);
                j++;
            }
        }

        private void computeLocations(int ai[])
        {
            if(!hasWeights())
                solve(ai);
            else
                solveAndDistributeSpace(ai);
            if(!orderPreserved)
            {
                int i = ai[0];
                int j = 0;
                for(int k = ai.length; j < k; j++)
                    ai[j] = ai[j] - i;

            }
        }

        private void computeMargins(boolean flag)
        {
            int ai[];
            int i;
            int j;
            if(flag)
                ai = leadingMargins;
            else
                ai = trailingMargins;
            i = 0;
            j = getChildCount();
            while(i < j) 
            {
                View view = getChildAt(i);
                if(view.getVisibility() != 8)
                {
                    Object obj = getLayoutParams(view);
                    int k;
                    if(horizontal)
                        obj = ((LayoutParams) (obj)).columnSpec;
                    else
                        obj = ((LayoutParams) (obj)).rowSpec;
                    obj = ((Spec) (obj)).span;
                    if(flag)
                        k = ((Interval) (obj)).min;
                    else
                        k = ((Interval) (obj)).max;
                    ai[k] = Math.max(ai[k], getMargin1(view, horizontal, flag));
                }
                i++;
            }
        }

        private Arc[] createArcs()
        {
            ArrayList arraylist = new ArrayList();
            ArrayList arraylist1 = new ArrayList();
            addComponentSizes(arraylist, getForwardLinks());
            addComponentSizes(arraylist1, getBackwardLinks());
            if(orderPreserved)
            {
                for(int i = 0; i < getCount(); i++)
                    include(arraylist, new Interval(i, i + 1), new MutableInt(0));

            }
            int j = getCount();
            include(arraylist, new Interval(0, j), parentMin, false);
            include(arraylist1, new Interval(j, 0), parentMax, false);
            return (Arc[])GridLayout.append(topologicalSort(arraylist), topologicalSort(arraylist1));
        }

        private PackedMap createGroupBounds()
        {
            Assoc assoc = Assoc.of(android/widget/GridLayout$Spec, android/widget/GridLayout$Bounds);
            int i = 0;
            int j = getChildCount();
            while(i < j) 
            {
                Object obj = getChildAt(i);
                obj = getLayoutParams(((View) (obj)));
                if(horizontal)
                    obj = ((LayoutParams) (obj)).columnSpec;
                else
                    obj = ((LayoutParams) (obj)).rowSpec;
                assoc.put(obj, Spec._2D_wrap0(((Spec) (obj)), horizontal).getBounds());
                i++;
            }
            return assoc.pack();
        }

        private PackedMap createLinks(boolean flag)
        {
            Assoc assoc = Assoc.of(android/widget/GridLayout$Interval, android/widget/GridLayout$MutableInt);
            Spec aspec[] = (Spec[])getGroupBounds().keys;
            int i = 0;
            int j = aspec.length;
            while(i < j) 
            {
                Interval interval;
                if(flag)
                    interval = aspec[i].span;
                else
                    interval = aspec[i].span.inverse();
                assoc.put(interval, new MutableInt());
                i++;
            }
            return assoc.pack();
        }

        private PackedMap getBackwardLinks()
        {
            if(backwardLinks == null)
                backwardLinks = createLinks(false);
            if(!backwardLinksValid)
            {
                computeLinks(backwardLinks, false);
                backwardLinksValid = true;
            }
            return backwardLinks;
        }

        private PackedMap getForwardLinks()
        {
            if(forwardLinks == null)
                forwardLinks = createLinks(true);
            if(!forwardLinksValid)
            {
                computeLinks(forwardLinks, true);
                forwardLinksValid = true;
            }
            return forwardLinks;
        }

        private int getMaxIndex()
        {
            if(maxIndex == 0x80000000)
                maxIndex = Math.max(0, calculateMaxIndex());
            return maxIndex;
        }

        private int getMeasure(int i, int j)
        {
            setParentConstraints(i, j);
            return size(getLocations());
        }

        private boolean hasWeights()
        {
            if(!hasWeightsValid)
            {
                hasWeights = computeHasWeights();
                hasWeightsValid = true;
            }
            return hasWeights;
        }

        private void include(List list, Interval interval, MutableInt mutableint)
        {
            include(list, interval, mutableint, true);
        }

        private void include(List list, Interval interval, MutableInt mutableint, boolean flag)
        {
label0:
            {
                if(interval.size() == 0)
                    return;
                if(!flag)
                    break label0;
                Iterator iterator = list.iterator();
                do
                    if(!iterator.hasNext())
                        break label0;
                while(!((Arc)iterator.next()).span.equals(interval));
                return;
            }
            list.add(new Arc(interval, mutableint));
        }

        private void init(int ai[])
        {
            Arrays.fill(ai, 0);
        }

        private void logError(String s, Arc aarc[], boolean aflag[])
        {
            ArrayList arraylist = new ArrayList();
            ArrayList arraylist1 = new ArrayList();
            for(int i = 0; i < aarc.length; i++)
            {
                Arc arc = aarc[i];
                if(aflag[i])
                    arraylist.add(arc);
                if(!arc.valid)
                    arraylist1.add(arc);
            }

            mPrinter.println((new StringBuilder()).append(s).append(" constraints: ").append(arcsToString(arraylist)).append(" are inconsistent; permanently removing: ").append(arcsToString(arraylist1)).append(". ").toString());
        }

        private boolean relax(int ai[], Arc arc)
        {
            if(!arc.valid)
                return false;
            Interval interval = arc.span;
            int i = interval.min;
            int j = interval.max;
            int k = arc.value.value;
            i = ai[i] + k;
            if(i > ai[j])
            {
                ai[j] = i;
                return true;
            } else
            {
                return false;
            }
        }

        private void setParentConstraints(int i, int j)
        {
            parentMin.value = i;
            parentMax.value = -j;
            locationsValid = false;
        }

        private void shareOutDelta(int i, float f)
        {
            Arrays.fill(deltas, 0);
            boolean flag = false;
            int k = getChildCount();
            float f1 = f;
            int l = i;
            i = ((flag) ? 1 : 0);
            while(i < k) 
            {
                Object obj = getChildAt(i);
                int j;
                if(((View) (obj)).getVisibility() == 8)
                {
                    f = f1;
                    j = l;
                } else
                {
                    obj = getLayoutParams(((View) (obj)));
                    float f2;
                    if(horizontal)
                        obj = ((LayoutParams) (obj)).columnSpec;
                    else
                        obj = ((LayoutParams) (obj)).rowSpec;
                    f2 = ((Spec) (obj)).weight;
                    j = l;
                    f = f1;
                    if(f2 != 0.0F)
                    {
                        j = Math.round(((float)l * f2) / f1);
                        deltas[i] = j;
                        j = l - j;
                        f = f1 - f2;
                    }
                }
                i++;
                l = j;
                f1 = f;
            }
        }

        private int size(int ai[])
        {
            return ai[getCount()];
        }

        private boolean solve(int ai[])
        {
            return solve(getArcs(), ai);
        }

        private boolean solve(Arc aarc[], int ai[])
        {
            return solve(aarc, ai, true);
        }

        private boolean solve(Arc aarc[], int ai[], boolean flag)
        {
            String s;
            int i;
            boolean aflag[];
            if(horizontal)
                s = "horizontal";
            else
                s = "vertical";
            i = getCount() + 1;
            aflag = null;
label0:
            for(int j = 0; j < aarc.length; j++)
            {
                init(ai);
                for(int k = 0; k < i; k++)
                {
                    boolean flag1 = false;
                    int k1 = 0;
                    for(int i2 = aarc.length; k1 < i2; k1++)
                        flag1 |= relax(ai, aarc[k1]);

                    if(!flag1)
                    {
                        if(aflag != null)
                            logError(s, aarc, aflag);
                        return true;
                    }
                }

                if(!flag)
                    return false;
                boolean aflag1[] = new boolean[aarc.length];
                for(int l = 0; l < i; l++)
                {
                    int l1 = 0;
                    for(int j1 = aarc.length; l1 < j1; l1++)
                        aflag1[l1] = aflag1[l1] | relax(ai, aarc[l1]);

                }

                if(j == 0)
                    aflag = aflag1;
                int i1 = 0;
                Arc arc;
                do
                {
                    if(i1 >= aarc.length)
                        continue label0;
                    if(aflag1[i1])
                    {
                        arc = aarc[i1];
                        if(arc.span.min >= arc.span.max)
                            break;
                    }
                    i1++;
                } while(true);
                arc.valid = false;
            }

            return true;
        }

        private void solveAndDistributeSpace(int ai[])
        {
            Arrays.fill(getDeltas(), 0);
            solve(ai);
            int i = parentMin.value * getChildCount() + 1;
            if(i < 2)
                return;
            int j = 0;
            float f = calculateTotalWeight();
            int k = -1;
            boolean flag = true;
            while(j < i) 
            {
                int l = (int)(((long)j + (long)i) / 2L);
                invalidateValues();
                shareOutDelta(l, f);
                flag = solve(getArcs(), ai, false);
                if(flag)
                {
                    k = l;
                    j = l + 1;
                } else
                {
                    i = l;
                }
            }
            if(k > 0 && flag ^ true)
            {
                invalidateValues();
                shareOutDelta(k, f);
                solve(ai);
            }
        }

        private Arc[] topologicalSort(List list)
        {
            return topologicalSort((Arc[])list.toArray(new Arc[list.size()]));
        }

        private Arc[] topologicalSort(Arc aarc[])
        {
            return (aarc. new Object() {

                Arc[] sort()
                {
                    int i = 0;
                    for(int j = arcsByVertex.length; i < j; i++)
                        walk(i);

                    if(!_2D_assertionsDisabled && cursor != -1)
                        throw new AssertionError();
                    else
                        return result;
                }

                void walk(int i)
                {
                    visited[i];
                    JVM INSTR tableswitch 0 2: default 32
                //                               0 33
                //                               1 119
                //                               2 32;
                       goto _L1 _L2 _L3 _L1
_L1:
                    return;
_L2:
                    visited[i] = 1;
                    Arc aarc[] = arcsByVertex[i];
                    int j = 0;
                    for(int k = aarc.length; j < k; j++)
                    {
                        Arc arc = aarc[j];
                        walk(arc.span.max);
                        Arc aarc1[] = result;
                        int l = cursor;
                        cursor = l - 1;
                        aarc1[l] = arc;
                    }

                    visited[i] = 2;
                    continue; /* Loop/switch isn't completed */
_L3:
                    if(!_2D_assertionsDisabled)
                        throw new AssertionError();
                    if(true) goto _L1; else goto _L4
_L4:
                }

                static final boolean _2D_assertionsDisabled = android/widget/GridLayout$Axis$1.desiredAssertionStatus() ^ true;
                Arc arcsByVertex[][];
                int cursor;
                Arc result[];
                final Axis this$1;
                final Arc val$arcs[];
                int visited[];


            
            {
                this$1 = final_axis;
                arcs = _5B_Landroid.widget.Arc_3B_.this;
                super();
                result = new Arc[arcs.length];
                cursor = result.length - 1;
                arcsByVertex = groupArcsByFirstVertex(arcs);
                visited = new int[getCount() + 1];
            }
            }
).sort();
        }

        public Arc[] getArcs()
        {
            if(arcs == null)
                arcs = createArcs();
            if(!arcsValid)
            {
                computeArcs();
                arcsValid = true;
            }
            return arcs;
        }

        public int getCount()
        {
            return Math.max(definedCount, getMaxIndex());
        }

        public int[] getDeltas()
        {
            if(deltas == null)
                deltas = new int[getChildCount()];
            return deltas;
        }

        public PackedMap getGroupBounds()
        {
            if(groupBounds == null)
                groupBounds = createGroupBounds();
            if(!groupBoundsValid)
            {
                computeGroupBounds();
                groupBoundsValid = true;
            }
            return groupBounds;
        }

        public int[] getLeadingMargins()
        {
            if(leadingMargins == null)
                leadingMargins = new int[getCount() + 1];
            if(!leadingMarginsValid)
            {
                computeMargins(true);
                leadingMarginsValid = true;
            }
            return leadingMargins;
        }

        public int[] getLocations()
        {
            if(locations == null)
                locations = new int[getCount() + 1];
            if(!locationsValid)
            {
                computeLocations(locations);
                locationsValid = true;
            }
            return locations;
        }

        public int getMeasure(int i)
        {
            int j = android.view.View.MeasureSpec.getMode(i);
            i = android.view.View.MeasureSpec.getSize(i);
            switch(j)
            {
            default:
                if(!_2D_assertionsDisabled)
                    throw new AssertionError();
                else
                    return 0;

            case 0: // '\0'
                return getMeasure(0, 0x186a0);

            case 1073741824: 
                return getMeasure(i, i);

            case -2147483648: 
                return getMeasure(0, i);
            }
        }

        public int[] getTrailingMargins()
        {
            if(trailingMargins == null)
                trailingMargins = new int[getCount() + 1];
            if(!trailingMarginsValid)
            {
                computeMargins(false);
                trailingMarginsValid = true;
            }
            return trailingMargins;
        }

        Arc[][] groupArcsByFirstVertex(Arc aarc[])
        {
            boolean flag = false;
            int j = getCount() + 1;
            Arc aarc1[][] = new Arc[j][];
            int ai[] = new int[j];
            int j1 = aarc.length;
            for(int k = 0; k < j1; k++)
            {
                int k1 = aarc[k].span.min;
                ai[k1] = ai[k1] + 1;
            }

            for(int l = 0; l < ai.length; l++)
                aarc1[l] = new Arc[ai[l]];

            Arrays.fill(ai, 0);
            j1 = aarc.length;
            for(int i1 = ((flag) ? 1 : 0); i1 < j1; i1++)
            {
                Arc arc = aarc[i1];
                int l1 = arc.span.min;
                Arc aarc2[] = aarc1[l1];
                int i = ai[l1];
                ai[l1] = i + 1;
                aarc2[i] = arc;
            }

            return aarc1;
        }

        public void invalidateStructure()
        {
            maxIndex = 0x80000000;
            groupBounds = null;
            forwardLinks = null;
            backwardLinks = null;
            leadingMargins = null;
            trailingMargins = null;
            arcs = null;
            locations = null;
            deltas = null;
            hasWeightsValid = false;
            invalidateValues();
        }

        public void invalidateValues()
        {
            groupBoundsValid = false;
            forwardLinksValid = false;
            backwardLinksValid = false;
            leadingMarginsValid = false;
            trailingMarginsValid = false;
            arcsValid = false;
            locationsValid = false;
        }

        public boolean isOrderPreserved()
        {
            return orderPreserved;
        }

        public void layout(int i)
        {
            setParentConstraints(i, i);
            getLocations();
        }

        public void setCount(int i)
        {
            if(i != 0x80000000 && i < getMaxIndex())
            {
                StringBuilder stringbuilder = new StringBuilder();
                String s;
                if(horizontal)
                    s = "column";
                else
                    s = "row";
                GridLayout._2D_wrap0(stringbuilder.append(s).append("Count must be greater than or equal to the maximum of all grid indices ").append("(and spans) defined in the LayoutParams of each child").toString());
            }
            definedCount = i;
        }

        public void setOrderPreserved(boolean flag)
        {
            orderPreserved = flag;
            invalidateStructure();
        }

        static final boolean _2D_assertionsDisabled = android/widget/GridLayout$Axis.desiredAssertionStatus() ^ true;
        private static final int COMPLETE = 2;
        private static final int NEW = 0;
        private static final int PENDING = 1;
        final boolean $assertionsDisabled;
        public Arc arcs[];
        public boolean arcsValid;
        PackedMap backwardLinks;
        public boolean backwardLinksValid;
        public int definedCount;
        public int deltas[];
        PackedMap forwardLinks;
        public boolean forwardLinksValid;
        PackedMap groupBounds;
        public boolean groupBoundsValid;
        public boolean hasWeights;
        public boolean hasWeightsValid;
        public final boolean horizontal;
        public int leadingMargins[];
        public boolean leadingMarginsValid;
        public int locations[];
        public boolean locationsValid;
        private int maxIndex;
        boolean orderPreserved;
        private MutableInt parentMax;
        private MutableInt parentMin;
        final GridLayout this$0;
        public int trailingMargins[];
        public boolean trailingMarginsValid;


        private Axis(boolean flag)
        {
            this$0 = GridLayout.this;
            super();
            definedCount = 0x80000000;
            maxIndex = 0x80000000;
            groupBoundsValid = false;
            forwardLinksValid = false;
            backwardLinksValid = false;
            leadingMarginsValid = false;
            trailingMarginsValid = false;
            arcsValid = false;
            locationsValid = false;
            hasWeightsValid = false;
            orderPreserved = true;
            parentMin = new MutableInt(0);
            parentMax = new MutableInt(0xfffe7960);
            horizontal = flag;
        }

        Axis(boolean flag, Axis axis)
        {
            this(flag);
        }
    }

    static class Bounds
    {

        protected int getOffset(GridLayout gridlayout, View view, Alignment alignment, int i, boolean flag)
        {
            return before - alignment.getAlignmentValue(view, i, gridlayout.getLayoutMode());
        }

        protected void include(int i, int j)
        {
            before = Math.max(before, i);
            after = Math.max(after, j);
        }

        protected final void include(GridLayout gridlayout, View view, Spec spec1, Axis axis, int i)
        {
            flexibility = flexibility & spec1.getFlexibility();
            boolean flag = axis.horizontal;
            int j = Spec._2D_wrap0(spec1, axis.horizontal).getAlignmentValue(view, i, gridlayout.getLayoutMode());
            include(j, i - j);
        }

        protected void reset()
        {
            before = 0x80000000;
            after = 0x80000000;
            flexibility = 2;
        }

        protected int size(boolean flag)
        {
            if(!flag && GridLayout.canStretch(flexibility))
                return 0x186a0;
            else
                return before + after;
        }

        public String toString()
        {
            return (new StringBuilder()).append("Bounds{before=").append(before).append(", after=").append(after).append('}').toString();
        }

        public int after;
        public int before;
        public int flexibility;

        private Bounds()
        {
            reset();
        }

        Bounds(Bounds bounds)
        {
            this();
        }
    }

    static final class Interval
    {

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (Interval)obj;
            if(max != ((Interval) (obj)).max)
                return false;
            return min == ((Interval) (obj)).min;
        }

        public int hashCode()
        {
            return min * 31 + max;
        }

        Interval inverse()
        {
            return new Interval(max, min);
        }

        int size()
        {
            return max - min;
        }

        public String toString()
        {
            return (new StringBuilder()).append("[").append(min).append(", ").append(max).append("]").toString();
        }

        public final int max;
        public final int min;

        public Interval(int i, int j)
        {
            min = i;
            max = j;
        }
    }

    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        private void init(Context context, AttributeSet attributeset)
        {
            context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.GridLayout_Layout);
            int i = context.getInt(0, 0);
            int j = context.getInt(1, 0x80000000);
            int k = context.getInt(4, DEFAULT_SPAN_SIZE);
            float f = context.getFloat(6, 0.0F);
            columnSpec = GridLayout.spec(j, k, GridLayout.getAlignment(i, true), f);
            j = context.getInt(2, 0x80000000);
            k = context.getInt(3, DEFAULT_SPAN_SIZE);
            f = context.getFloat(5, 0.0F);
            rowSpec = GridLayout.spec(j, k, GridLayout.getAlignment(i, false), f);
            context.recycle();
            return;
            attributeset;
            context.recycle();
            throw attributeset;
        }

        private void reInitSuper(Context context, AttributeSet attributeset)
        {
            context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ViewGroup_MarginLayout);
            int i = context.getDimensionPixelSize(2, 0x80000000);
            leftMargin = context.getDimensionPixelSize(3, i);
            topMargin = context.getDimensionPixelSize(4, i);
            rightMargin = context.getDimensionPixelSize(5, i);
            bottomMargin = context.getDimensionPixelSize(6, i);
            context.recycle();
            return;
            attributeset;
            context.recycle();
            throw attributeset;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (LayoutParams)obj;
            if(!columnSpec.equals(((LayoutParams) (obj)).columnSpec))
                return false;
            return rowSpec.equals(((LayoutParams) (obj)).rowSpec);
        }

        public int hashCode()
        {
            return rowSpec.hashCode() * 31 + columnSpec.hashCode();
        }

        protected void setBaseAttributes(TypedArray typedarray, int i, int j)
        {
            width = typedarray.getLayoutDimension(i, -2);
            height = typedarray.getLayoutDimension(j, -2);
        }

        final void setColumnSpecSpan(Interval interval)
        {
            columnSpec = columnSpec.copyWriteSpan(interval);
        }

        public void setGravity(int i)
        {
            rowSpec = rowSpec.copyWriteAlignment(GridLayout.getAlignment(i, false));
            columnSpec = columnSpec.copyWriteAlignment(GridLayout.getAlignment(i, true));
        }

        final void setRowSpecSpan(Interval interval)
        {
            rowSpec = rowSpec.copyWriteSpan(interval);
        }

        private static final int BOTTOM_MARGIN = 6;
        private static final int COLUMN = 1;
        private static final int COLUMN_SPAN = 4;
        private static final int COLUMN_WEIGHT = 6;
        private static final int DEFAULT_COLUMN = 0x80000000;
        private static final int DEFAULT_HEIGHT = -2;
        private static final int DEFAULT_MARGIN = 0x80000000;
        private static final int DEFAULT_ROW = 0x80000000;
        private static final Interval DEFAULT_SPAN;
        private static final int DEFAULT_SPAN_SIZE;
        private static final int DEFAULT_WIDTH = -2;
        private static final int GRAVITY = 0;
        private static final int LEFT_MARGIN = 3;
        private static final int MARGIN = 2;
        private static final int RIGHT_MARGIN = 5;
        private static final int ROW = 2;
        private static final int ROW_SPAN = 3;
        private static final int ROW_WEIGHT = 5;
        private static final int TOP_MARGIN = 4;
        public Spec columnSpec;
        public Spec rowSpec;

        static 
        {
            DEFAULT_SPAN = new Interval(0x80000000, 0x80000001);
            DEFAULT_SPAN_SIZE = DEFAULT_SPAN.size();
        }

        public LayoutParams()
        {
            this(Spec.UNDEFINED, Spec.UNDEFINED);
        }

        private LayoutParams(int i, int j, int k, int l, int i1, int j1, Spec spec1, 
                Spec spec2)
        {
            super(i, j);
            rowSpec = Spec.UNDEFINED;
            columnSpec = Spec.UNDEFINED;
            setMargins(k, l, i1, j1);
            rowSpec = spec1;
            columnSpec = spec2;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            rowSpec = Spec.UNDEFINED;
            columnSpec = Spec.UNDEFINED;
            reInitSuper(context, attributeset);
            init(context, attributeset);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            rowSpec = Spec.UNDEFINED;
            columnSpec = Spec.UNDEFINED;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            rowSpec = Spec.UNDEFINED;
            columnSpec = Spec.UNDEFINED;
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            rowSpec = Spec.UNDEFINED;
            columnSpec = Spec.UNDEFINED;
            rowSpec = layoutparams.rowSpec;
            columnSpec = layoutparams.columnSpec;
        }

        public LayoutParams(Spec spec1, Spec spec2)
        {
            this(-2, -2, 0x80000000, 0x80000000, 0x80000000, 0x80000000, spec1, spec2);
        }
    }

    static final class MutableInt
    {

        public void reset()
        {
            value = 0x80000000;
        }

        public String toString()
        {
            return Integer.toString(value);
        }

        public int value;

        public MutableInt()
        {
            reset();
        }

        public MutableInt(int i)
        {
            value = i;
        }
    }

    static final class PackedMap
    {

        private static Object[] compact(Object aobj[], int ai[])
        {
            int i = aobj.length;
            Object aobj1[] = (Object[])Array.newInstance(((Object) (aobj)).getClass().getComponentType(), GridLayout.max2(ai, -1) + 1);
            for(int j = 0; j < i; j++)
                aobj1[ai[j]] = aobj[j];

            return aobj1;
        }

        private static int[] createIndex(Object aobj[])
        {
            int i = aobj.length;
            int ai[] = new int[i];
            HashMap hashmap = new HashMap();
            for(int j = 0; j < i; j++)
            {
                Object obj = aobj[j];
                Integer integer = (Integer)hashmap.get(obj);
                Integer integer1 = integer;
                if(integer == null)
                {
                    integer1 = Integer.valueOf(hashmap.size());
                    hashmap.put(obj, integer1);
                }
                ai[j] = integer1.intValue();
            }

            return ai;
        }

        public Object getValue(int i)
        {
            return values[index[i]];
        }

        public final int index[];
        public final Object keys[];
        public final Object values[];

        private PackedMap(Object aobj[], Object aobj1[])
        {
            index = createIndex(aobj);
            keys = compact(aobj, index);
            values = compact(aobj1, index);
        }

        PackedMap(Object aobj[], Object aobj1[], PackedMap packedmap)
        {
            this(aobj, aobj1);
        }
    }

    public static class Spec
    {

        static Alignment _2D_wrap0(Spec spec1, boolean flag)
        {
            return spec1.getAbsoluteAlignment(flag);
        }

        private Alignment getAbsoluteAlignment(boolean flag)
        {
            if(alignment != GridLayout.UNDEFINED_ALIGNMENT)
                return alignment;
            if(weight == 0.0F)
            {
                Alignment alignment1;
                if(flag)
                    alignment1 = GridLayout.START;
                else
                    alignment1 = GridLayout.BASELINE;
                return alignment1;
            } else
            {
                return GridLayout.FILL;
            }
        }

        final Spec copyWriteAlignment(Alignment alignment1)
        {
            return new Spec(startDefined, span, alignment1, weight);
        }

        final Spec copyWriteSpan(Interval interval)
        {
            return new Spec(startDefined, interval, alignment, weight);
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (Spec)obj;
            if(!alignment.equals(((Spec) (obj)).alignment))
                return false;
            return span.equals(((Spec) (obj)).span);
        }

        final int getFlexibility()
        {
            int i;
            if(alignment == GridLayout.UNDEFINED_ALIGNMENT && weight == 0.0F)
                i = 0;
            else
                i = 2;
            return i;
        }

        public int hashCode()
        {
            return span.hashCode() * 31 + alignment.hashCode();
        }

        static final float DEFAULT_WEIGHT = 0F;
        static final Spec UNDEFINED = GridLayout.spec(0x80000000);
        final Alignment alignment;
        final Interval span;
        final boolean startDefined;
        final float weight;


        private Spec(boolean flag, int i, int j, Alignment alignment1, float f)
        {
            this(flag, new Interval(i, i + j), alignment1, f);
        }

        Spec(boolean flag, int i, int j, Alignment alignment1, float f, Spec spec1)
        {
            this(flag, i, j, alignment1, f);
        }

        private Spec(boolean flag, Interval interval, Alignment alignment1, float f)
        {
            startDefined = flag;
            span = interval;
            alignment = alignment1;
            weight = f;
        }
    }


    static void _2D_wrap0(String s)
    {
        handleInvalidParams(s);
    }

    public GridLayout(Context context)
    {
        this(context, null);
    }

    public GridLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public GridLayout(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public GridLayout(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mHorizontalAxis = new Axis(true, null);
        mVerticalAxis = new Axis(false, null);
        mOrientation = 0;
        mUseDefaultMargins = false;
        mAlignmentMode = 1;
        mLastLayoutParamsHashCode = 0;
        mPrinter = LOG_PRINTER;
        mDefaultGap = context.getResources().getDimensionPixelOffset(0x1050069);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.GridLayout, i, j);
        setRowCount(context.getInt(1, 0x80000000));
        setColumnCount(context.getInt(3, 0x80000000));
        setOrientation(context.getInt(0, 0));
        setUseDefaultMargins(context.getBoolean(5, false));
        setAlignmentMode(context.getInt(6, 1));
        setRowOrderPreserved(context.getBoolean(2, true));
        setColumnOrderPreserved(context.getBoolean(4, true));
        context.recycle();
        return;
        attributeset;
        context.recycle();
        throw attributeset;
    }

    static int adjust(int i, int j)
    {
        return android.view.View.MeasureSpec.makeMeasureSpec(android.view.View.MeasureSpec.getSize(i + j), android.view.View.MeasureSpec.getMode(i));
    }

    static Object[] append(Object aobj[], Object aobj1[])
    {
        Object aobj2[] = (Object[])Array.newInstance(((Object) (aobj)).getClass().getComponentType(), aobj.length + aobj1.length);
        System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj2)), 0, aobj.length);
        System.arraycopy(((Object) (aobj1)), 0, ((Object) (aobj2)), aobj.length, aobj1.length);
        return aobj2;
    }

    static boolean canStretch(int i)
    {
        boolean flag = false;
        if((i & 2) != 0)
            flag = true;
        return flag;
    }

    private void checkLayoutParams(LayoutParams layoutparams, boolean flag)
    {
        String s;
        Interval interval;
        int i;
        if(flag)
            s = "column";
        else
            s = "row";
        if(flag)
            layoutparams = layoutparams.columnSpec;
        else
            layoutparams = layoutparams.rowSpec;
        interval = ((Spec) (layoutparams)).span;
        if(interval.min != 0x80000000 && interval.min < 0)
            handleInvalidParams((new StringBuilder()).append(s).append(" indices must be positive").toString());
        if(flag)
            layoutparams = mHorizontalAxis;
        else
            layoutparams = mVerticalAxis;
        i = ((Axis) (layoutparams)).definedCount;
        if(i != 0x80000000)
        {
            if(interval.max > i)
                handleInvalidParams((new StringBuilder()).append(s).append(" indices (start + span) mustn't exceed the ").append(s).append(" count").toString());
            if(interval.size() > i)
                handleInvalidParams((new StringBuilder()).append(s).append(" span mustn't exceed the ").append(s).append(" count").toString());
        }
    }

    private static int clip(Interval interval, boolean flag, int i)
    {
        int j = interval.size();
        if(i == 0)
            return j;
        int k;
        if(flag)
            k = Math.min(interval.min, i);
        else
            k = 0;
        return Math.min(j, i - k);
    }

    private int computeLayoutParamsHashCode()
    {
        int i = 1;
        int j = 0;
        int k = getChildCount();
        while(j < k) 
        {
            View view = getChildAt(j);
            if(view.getVisibility() != 8)
                i = i * 31 + ((LayoutParams)view.getLayoutParams()).hashCode();
            j++;
        }
        return i;
    }

    private void consistencyCheck()
    {
        if(mLastLayoutParamsHashCode != 0) goto _L2; else goto _L1
_L1:
        validateLayoutParams();
        mLastLayoutParamsHashCode = computeLayoutParamsHashCode();
_L4:
        return;
_L2:
        if(mLastLayoutParamsHashCode != computeLayoutParamsHashCode())
        {
            mPrinter.println("The fields of some layout parameters were modified in between layout operations. Check the javadoc for GridLayout.LayoutParams#rowSpec.");
            invalidateStructure();
            consistencyCheck();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static Alignment createSwitchingAlignment(Alignment alignment, Alignment alignment1)
    {
        return new Alignment(alignment, alignment1) {

            public int getAlignmentValue(View view, int i, int j)
            {
                Alignment alignment2;
                if(!view.isLayoutRtl())
                    alignment2 = ltr;
                else
                    alignment2 = rtl;
                return alignment2.getAlignmentValue(view, i, j);
            }

            int getGravityOffset(View view, int i)
            {
                Alignment alignment2;
                if(!view.isLayoutRtl())
                    alignment2 = ltr;
                else
                    alignment2 = rtl;
                return alignment2.getGravityOffset(view, i);
            }

            final Alignment val$ltr;
            final Alignment val$rtl;

            
            {
                ltr = alignment;
                rtl = alignment1;
                super();
            }
        }
;
    }

    private void drawLine(Canvas canvas, int i, int j, int k, int l, Paint paint)
    {
        if(isLayoutRtl())
        {
            int i1 = getWidth();
            canvas.drawLine(i1 - i, j, i1 - k, l, paint);
        } else
        {
            canvas.drawLine(i, j, k, l, paint);
        }
    }

    private static boolean fits(int ai[], int i, int j, int k)
    {
        if(k > ai.length)
            return false;
        for(; j < k; j++)
            if(ai[j] > i)
                return false;

        return true;
    }

    static Alignment getAlignment(int i, boolean flag)
    {
        byte byte0;
        int j;
        if(flag)
            byte0 = 7;
        else
            byte0 = 112;
        if(flag)
            j = 0;
        else
            j = 4;
        switch((i & byte0) >> j)
        {
        default:
            return UNDEFINED_ALIGNMENT;

        case 3: // '\003'
            Alignment alignment;
            if(flag)
                alignment = LEFT;
            else
                alignment = TOP;
            return alignment;

        case 5: // '\005'
            Alignment alignment1;
            if(flag)
                alignment1 = RIGHT;
            else
                alignment1 = BOTTOM;
            return alignment1;

        case 7: // '\007'
            return FILL;

        case 1: // '\001'
            return CENTER;

        case 8388611: 
            return START;

        case 8388613: 
            return END;
        }
    }

    private int getDefaultMargin(View view, LayoutParams layoutparams, boolean flag, boolean flag1)
    {
        if(!mUseDefaultMargins)
            return 0;
        Axis axis;
        boolean flag2;
        if(flag)
            layoutparams = layoutparams.columnSpec;
        else
            layoutparams = layoutparams.rowSpec;
        if(flag)
            axis = mHorizontalAxis;
        else
            axis = mVerticalAxis;
        layoutparams = ((Spec) (layoutparams)).span;
        if(flag && isLayoutRtl())
            flag2 = flag1 ^ true;
        else
            flag2 = flag1;
        if(flag2 ? ((Interval) (layoutparams)).min == 0 : ((Interval) (layoutparams)).max == axis.getCount())
            flag2 = true;
        else
            flag2 = false;
        return getDefaultMargin(view, flag2, flag, flag1);
    }

    private int getDefaultMargin(View view, boolean flag, boolean flag1)
    {
        if(view.getClass() == android/widget/Space)
            return 0;
        else
            return mDefaultGap / 2;
    }

    private int getDefaultMargin(View view, boolean flag, boolean flag1, boolean flag2)
    {
        return getDefaultMargin(view, flag1, flag2);
    }

    private int getMargin(View view, boolean flag, boolean flag1)
    {
        if(mAlignmentMode == 1)
            return getMargin1(view, flag, flag1);
        Object obj;
        int i;
        if(flag)
            obj = mHorizontalAxis;
        else
            obj = mVerticalAxis;
        if(flag1)
            obj = ((Axis) (obj)).getLeadingMargins();
        else
            obj = ((Axis) (obj)).getTrailingMargins();
        view = getLayoutParams(view);
        if(flag)
            view = ((LayoutParams) (view)).columnSpec;
        else
            view = ((LayoutParams) (view)).rowSpec;
        if(flag1)
            i = ((Spec) (view)).span.min;
        else
            i = ((Spec) (view)).span.max;
        return obj[i];
    }

    private int getMeasurement(View view, boolean flag)
    {
        int i;
        if(flag)
            i = view.getMeasuredWidth();
        else
            i = view.getMeasuredHeight();
        return i;
    }

    private int getTotalMargin(View view, boolean flag)
    {
        return getMargin(view, flag, true) + getMargin(view, flag, false);
    }

    private static void handleInvalidParams(String s)
    {
        throw new IllegalArgumentException((new StringBuilder()).append(s).append(". ").toString());
    }

    private void invalidateStructure()
    {
        mLastLayoutParamsHashCode = 0;
        mHorizontalAxis.invalidateStructure();
        mVerticalAxis.invalidateStructure();
        invalidateValues();
    }

    private void invalidateValues()
    {
        if(mHorizontalAxis != null && mVerticalAxis != null)
        {
            mHorizontalAxis.invalidateValues();
            mVerticalAxis.invalidateValues();
        }
    }

    static int max2(int ai[], int i)
    {
        int j = 0;
        for(int k = ai.length; j < k; j++)
            i = Math.max(i, ai[j]);

        return i;
    }

    private void measureChildWithMargins2(View view, int i, int j, int k, int l)
    {
        view.measure(getChildMeasureSpec(i, getTotalMargin(view, true), k), getChildMeasureSpec(j, getTotalMargin(view, false), l));
    }

    private void measureChildrenWithMargins(int i, int j, boolean flag)
    {
        int k = 0;
        int l = getChildCount();
        while(k < l) 
        {
            View view = getChildAt(k);
            if(view.getVisibility() != 8)
            {
                LayoutParams layoutparams = getLayoutParams(view);
                if(flag)
                {
                    measureChildWithMargins2(view, i, j, layoutparams.width, layoutparams.height);
                } else
                {
                    boolean flag1;
                    Object obj;
                    if(mOrientation == 0)
                        flag1 = true;
                    else
                        flag1 = false;
                    if(flag1)
                        obj = layoutparams.columnSpec;
                    else
                        obj = layoutparams.rowSpec;
                    if(Spec._2D_wrap0(((Spec) (obj)), flag1) == FILL)
                    {
                        Interval interval = ((Spec) (obj)).span;
                        int i1;
                        if(flag1)
                            obj = mHorizontalAxis;
                        else
                            obj = mVerticalAxis;
                        obj = ((Axis) (obj)).getLocations();
                        i1 = obj[interval.max] - obj[interval.min] - getTotalMargin(view, flag1);
                        if(flag1)
                            measureChildWithMargins2(view, i, j, i1, layoutparams.height);
                        else
                            measureChildWithMargins2(view, i, j, layoutparams.width, i1);
                    }
                }
            }
            k++;
        }
    }

    private static void procrusteanFill(int ai[], int i, int j, int k)
    {
        int l = ai.length;
        Arrays.fill(ai, Math.min(i, l), Math.min(j, l), k);
    }

    private static void setCellGroup(LayoutParams layoutparams, int i, int j, int k, int l)
    {
        layoutparams.setRowSpecSpan(new Interval(i, i + j));
        layoutparams.setColumnSpecSpan(new Interval(k, k + l));
    }

    public static Spec spec(int i)
    {
        return spec(i, 1);
    }

    public static Spec spec(int i, float f)
    {
        return spec(i, 1, f);
    }

    public static Spec spec(int i, int j)
    {
        return spec(i, j, UNDEFINED_ALIGNMENT);
    }

    public static Spec spec(int i, int j, float f)
    {
        return spec(i, j, UNDEFINED_ALIGNMENT, f);
    }

    public static Spec spec(int i, int j, Alignment alignment)
    {
        return spec(i, j, alignment, 0.0F);
    }

    public static Spec spec(int i, int j, Alignment alignment, float f)
    {
        boolean flag;
        if(i != 0x80000000)
            flag = true;
        else
            flag = false;
        return new Spec(flag, i, j, alignment, f, null);
    }

    public static Spec spec(int i, Alignment alignment)
    {
        return spec(i, 1, alignment);
    }

    public static Spec spec(int i, Alignment alignment, float f)
    {
        return spec(i, 1, alignment, f);
    }

    private void validateLayoutParams()
    {
        boolean flag;
        Object obj;
        int i;
        int j;
        int k;
        int ai[];
        int l;
        int i1;
        if(mOrientation == 0)
            flag = true;
        else
            flag = false;
        if(flag)
            obj = mHorizontalAxis;
        else
            obj = mVerticalAxis;
        if(((Axis) (obj)).definedCount != 0x80000000)
            i = ((Axis) (obj)).definedCount;
        else
            i = 0;
        j = 0;
        k = 0;
        ai = new int[i];
        l = 0;
        i1 = getChildCount();
        while(l < i1) 
        {
            LayoutParams layoutparams;
            int j1;
            int k1;
            int l1;
            int i2;
label0:
            {
label1:
                {
                    layoutparams = (LayoutParams)getChildAt(l).getLayoutParams();
                    Interval interval;
                    boolean flag1;
                    boolean flag2;
                    int j2;
                    int k2;
                    if(flag)
                        obj = layoutparams.rowSpec;
                    else
                        obj = layoutparams.columnSpec;
                    interval = ((Spec) (obj)).span;
                    flag1 = ((Spec) (obj)).startDefined;
                    j1 = interval.size();
                    if(flag1)
                        j = interval.min;
                    if(flag)
                        obj = layoutparams.columnSpec;
                    else
                        obj = layoutparams.rowSpec;
                    interval = ((Spec) (obj)).span;
                    flag2 = ((Spec) (obj)).startDefined;
                    k1 = clip(interval, flag2, i);
                    if(flag2)
                        k = interval.min;
                    l1 = j;
                    i2 = k;
                    if(i == 0)
                        break label0;
                    j2 = j;
                    k2 = k;
                    if(flag1)
                    {
                        l1 = j;
                        i2 = k;
                        if(!(flag2 ^ true))
                            break label1;
                        k2 = k;
                        j2 = j;
                    }
                    do
                    {
                        l1 = j2;
                        i2 = k2;
                        if(fits(ai, j2, k2, k2 + k1))
                            break;
                        if(flag2)
                            j2++;
                        else
                        if(k2 + k1 <= i)
                        {
                            k2++;
                        } else
                        {
                            k2 = 0;
                            j2++;
                        }
                    } while(true);
                }
                procrusteanFill(ai, i2, i2 + k1, l1 + j1);
            }
            if(flag)
                setCellGroup(layoutparams, l1, j1, i2, k1);
            else
                setCellGroup(layoutparams, i2, k1, l1, j1);
            k = i2 + k1;
            l++;
            j = l1;
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(!(layoutparams instanceof LayoutParams))
        {
            return false;
        } else
        {
            layoutparams = (LayoutParams)layoutparams;
            checkLayoutParams(((LayoutParams) (layoutparams)), true);
            checkLayoutParams(((LayoutParams) (layoutparams)), false);
            return true;
        }
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    protected LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams();
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(sPreserveMarginParamsInLayoutParamConversion)
        {
            if(layoutparams instanceof LayoutParams)
                return new LayoutParams((LayoutParams)layoutparams);
            if(layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
                return new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
        }
        return new LayoutParams(layoutparams);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/GridLayout.getName();
    }

    public int getAlignmentMode()
    {
        return mAlignmentMode;
    }

    public int getColumnCount()
    {
        return mHorizontalAxis.getCount();
    }

    final LayoutParams getLayoutParams(View view)
    {
        return (LayoutParams)view.getLayoutParams();
    }

    int getMargin1(View view, boolean flag, boolean flag1)
    {
        LayoutParams layoutparams = getLayoutParams(view);
        int i;
        int j;
        if(flag)
        {
            if(flag1)
                i = layoutparams.leftMargin;
            else
                i = layoutparams.rightMargin;
        } else
        if(flag1)
            i = layoutparams.topMargin;
        else
            i = layoutparams.bottomMargin;
        j = i;
        if(i == 0x80000000)
            j = getDefaultMargin(view, layoutparams, flag, flag1);
        return j;
    }

    final int getMeasurementIncludingMargin(View view, boolean flag)
    {
        if(view.getVisibility() == 8)
            return 0;
        else
            return getMeasurement(view, flag) + getTotalMargin(view, flag);
    }

    public int getOrientation()
    {
        return mOrientation;
    }

    public Printer getPrinter()
    {
        return mPrinter;
    }

    public int getRowCount()
    {
        return mVerticalAxis.getCount();
    }

    public boolean getUseDefaultMargins()
    {
        return mUseDefaultMargins;
    }

    public boolean isColumnOrderPreserved()
    {
        return mHorizontalAxis.isOrderPreserved();
    }

    public boolean isRowOrderPreserved()
    {
        return mVerticalAxis.isOrderPreserved();
    }

    protected void onChildVisibilityChanged(View view, int i, int j)
    {
        super.onChildVisibilityChanged(view, i, j);
        if(i == 8 || j == 8)
            invalidateStructure();
    }

    protected void onDebugDraw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setStyle(android.graphics.Paint.Style.STROKE);
        paint.setColor(Color.argb(50, 255, 255, 255));
        Insets insets = getOpticalInsets();
        int i = getPaddingTop() + insets.top;
        int j = getPaddingLeft() + insets.left;
        int k = getWidth();
        int l = getPaddingRight();
        int i1 = insets.right;
        int j1 = getHeight();
        int k1 = getPaddingBottom();
        int i2 = insets.bottom;
        int ai[] = mHorizontalAxis.locations;
        if(ai != null)
        {
            int j2 = 0;
            for(int l2 = ai.length; j2 < l2; j2++)
            {
                int j3 = j + ai[j2];
                drawLine(canvas, j3, i, j3, j1 - k1 - i2, paint);
            }

        }
        ai = mVerticalAxis.locations;
        if(ai != null)
        {
            int k2 = 0;
            for(int i3 = ai.length; k2 < i3; k2++)
            {
                int l1 = i + ai[k2];
                drawLine(canvas, j, l1, k - l - i1, l1, paint);
            }

        }
        super.onDebugDraw(canvas);
    }

    protected void onDebugDrawMargins(Canvas canvas, Paint paint)
    {
        LayoutParams layoutparams = new LayoutParams();
        for(int i = 0; i < getChildCount(); i++)
        {
            View view = getChildAt(i);
            layoutparams.setMargins(getMargin1(view, true, true), getMargin1(view, false, true), getMargin1(view, true, false), getMargin1(view, false, false));
            layoutparams.onDebugDraw(view, canvas, paint);
        }

    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        consistencyCheck();
        k -= i;
        int i1 = getPaddingLeft();
        int j1 = getPaddingTop();
        int k1 = getPaddingRight();
        i = getPaddingBottom();
        mHorizontalAxis.layout(k - i1 - k1);
        mVerticalAxis.layout(l - j - j1 - i);
        int ai[] = mHorizontalAxis.getLocations();
        int ai1[] = mVerticalAxis.getLocations();
        i = 0;
        l = getChildCount();
        while(i < l) 
        {
            View view = getChildAt(i);
            if(view.getVisibility() != 8)
            {
                Object obj = getLayoutParams(view);
                Object obj1 = ((LayoutParams) (obj)).columnSpec;
                obj = ((LayoutParams) (obj)).rowSpec;
                Object obj2 = ((Spec) (obj1)).span;
                Object obj3 = ((Spec) (obj)).span;
                j = ai[((Interval) (obj2)).min];
                int l1 = ai1[((Interval) (obj3)).min];
                int i2 = ai[((Interval) (obj2)).max];
                int j2 = ai1[((Interval) (obj3)).max];
                int k2 = i2 - j;
                int l2 = j2 - l1;
                int i3 = getMeasurement(view, true);
                int j3 = getMeasurement(view, false);
                obj1 = Spec._2D_wrap0(((Spec) (obj1)), true);
                obj2 = Spec._2D_wrap0(((Spec) (obj)), false);
                obj3 = (Bounds)mHorizontalAxis.getGroupBounds().getValue(i);
                obj = (Bounds)mVerticalAxis.getGroupBounds().getValue(i);
                int k3 = ((Alignment) (obj1)).getGravityOffset(view, k2 - ((Bounds) (obj3)).size(true));
                j2 = ((Alignment) (obj2)).getGravityOffset(view, l2 - ((Bounds) (obj)).size(true));
                int l3 = getMargin(view, true, true);
                i2 = getMargin(view, false, true);
                int i4 = getMargin(view, true, false);
                int j4 = getMargin(view, false, false);
                int k4 = l3 + i4;
                int l4 = i2 + j4;
                int i5 = ((Bounds) (obj3)).getOffset(this, view, ((Alignment) (obj1)), i3 + k4, true);
                j4 = ((Bounds) (obj)).getOffset(this, view, ((Alignment) (obj2)), j3 + l4, false);
                k2 = ((Alignment) (obj1)).getSizeInCell(view, i3, k2 - k4);
                l2 = ((Alignment) (obj2)).getSizeInCell(view, j3, l2 - l4);
                j = j + k3 + i5;
                if(!isLayoutRtl())
                    j = i1 + l3 + j;
                else
                    j = k - k2 - k1 - i4 - j;
                l1 = j1 + l1 + j2 + j4 + i2;
                if(k2 != view.getMeasuredWidth() || l2 != view.getMeasuredHeight())
                    view.measure(android.view.View.MeasureSpec.makeMeasureSpec(k2, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(l2, 0x40000000));
                view.layout(j, l1, j + k2, l1 + l2);
            }
            i++;
        }
    }

    protected void onMeasure(int i, int j)
    {
        consistencyCheck();
        invalidateValues();
        int k = getPaddingLeft() + getPaddingRight();
        int l = getPaddingTop() + getPaddingBottom();
        int i1 = adjust(i, -k);
        int j1 = adjust(j, -l);
        measureChildrenWithMargins(i1, j1, true);
        int k1;
        int l1;
        if(mOrientation == 0)
        {
            k1 = mHorizontalAxis.getMeasure(i1);
            measureChildrenWithMargins(i1, j1, false);
            l1 = mVerticalAxis.getMeasure(j1);
        } else
        {
            l1 = mVerticalAxis.getMeasure(j1);
            measureChildrenWithMargins(i1, j1, false);
            k1 = mHorizontalAxis.getMeasure(i1);
        }
        k1 = Math.max(k1 + k, getSuggestedMinimumWidth());
        l1 = Math.max(l1 + l, getSuggestedMinimumHeight());
        setMeasuredDimension(resolveSizeAndState(k1, i, 0), resolveSizeAndState(l1, j, 0));
    }

    protected void onSetLayoutParams(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.onSetLayoutParams(view, layoutparams);
        if(!checkLayoutParams(layoutparams))
            handleInvalidParams("supplied LayoutParams are of the wrong type");
        invalidateStructure();
    }

    public void onViewAdded(View view)
    {
        super.onViewAdded(view);
        invalidateStructure();
    }

    public void onViewRemoved(View view)
    {
        super.onViewRemoved(view);
        invalidateStructure();
    }

    public void requestLayout()
    {
        super.requestLayout();
        invalidateValues();
    }

    public void setAlignmentMode(int i)
    {
        mAlignmentMode = i;
        requestLayout();
    }

    public void setColumnCount(int i)
    {
        mHorizontalAxis.setCount(i);
        invalidateStructure();
        requestLayout();
    }

    public void setColumnOrderPreserved(boolean flag)
    {
        mHorizontalAxis.setOrderPreserved(flag);
        invalidateStructure();
        requestLayout();
    }

    public void setOrientation(int i)
    {
        if(mOrientation != i)
        {
            mOrientation = i;
            invalidateStructure();
            requestLayout();
        }
    }

    public void setPrinter(Printer printer)
    {
        Printer printer1 = printer;
        if(printer == null)
            printer1 = NO_PRINTER;
        mPrinter = printer1;
    }

    public void setRowCount(int i)
    {
        mVerticalAxis.setCount(i);
        invalidateStructure();
        requestLayout();
    }

    public void setRowOrderPreserved(boolean flag)
    {
        mVerticalAxis.setOrderPreserved(flag);
        invalidateStructure();
        requestLayout();
    }

    public void setUseDefaultMargins(boolean flag)
    {
        mUseDefaultMargins = flag;
        requestLayout();
    }

    private static final int ALIGNMENT_MODE = 6;
    public static final int ALIGN_BOUNDS = 0;
    public static final int ALIGN_MARGINS = 1;
    public static final Alignment BASELINE = new Alignment() {

        public int getAlignmentValue(View view, int i, int j)
        {
            if(view.getVisibility() == 8)
                return 0;
            j = view.getBaseline();
            i = j;
            if(j == -1)
                i = 0x80000000;
            return i;
        }

        public Bounds getBounds()
        {
            return new Bounds() {

                protected int getOffset(GridLayout gridlayout, View view, Alignment alignment, int i, boolean flag)
                {
                    return Math.max(0, super.getOffset(gridlayout, view, alignment, i, flag));
                }

                protected void include(int i, int j)
                {
                    super.include(i, j);
                    size = Math.max(size, i + j);
                }

                protected void reset()
                {
                    super.reset();
                    size = 0x80000000;
                }

                protected int size(boolean flag)
                {
                    return Math.max(super.size(flag), size);
                }

                private int size;
                final _cls6 this$1;

            
            {
                this$1 = _cls6.this;
                super(null);
            }
            }
;
        }

        int getGravityOffset(View view, int i)
        {
            return 0;
        }

    }
;
    public static final Alignment BOTTOM;
    private static final int CAN_STRETCH = 2;
    public static final Alignment CENTER = new Alignment() {

        public int getAlignmentValue(View view, int i, int j)
        {
            return i >> 1;
        }

        int getGravityOffset(View view, int i)
        {
            return i >> 1;
        }

    }
;
    private static final int COLUMN_COUNT = 3;
    private static final int COLUMN_ORDER_PRESERVED = 4;
    private static final int DEFAULT_ALIGNMENT_MODE = 1;
    static final int DEFAULT_CONTAINER_MARGIN = 0;
    private static final int DEFAULT_COUNT = 0x80000000;
    private static final boolean DEFAULT_ORDER_PRESERVED = true;
    private static final int DEFAULT_ORIENTATION = 0;
    private static final boolean DEFAULT_USE_DEFAULT_MARGINS = false;
    public static final Alignment END;
    public static final Alignment FILL = new Alignment() {

        public int getAlignmentValue(View view, int i, int j)
        {
            return 0x80000000;
        }

        int getGravityOffset(View view, int i)
        {
            return 0;
        }

        public int getSizeInCell(View view, int i, int j)
        {
            return j;
        }

    }
;
    public static final int HORIZONTAL = 0;
    private static final int INFLEXIBLE = 0;
    private static final Alignment LEADING;
    public static final Alignment LEFT;
    static final Printer LOG_PRINTER = new LogPrinter(3, android/widget/GridLayout.getName());
    static final int MAX_SIZE = 0x186a0;
    static final Printer NO_PRINTER = new Printer() {

        public void println(String s)
        {
        }

    }
;
    private static final int ORIENTATION = 0;
    public static final Alignment RIGHT;
    private static final int ROW_COUNT = 1;
    private static final int ROW_ORDER_PRESERVED = 2;
    public static final Alignment START;
    public static final Alignment TOP;
    private static final Alignment TRAILING;
    public static final int UNDEFINED = 0x80000000;
    static final Alignment UNDEFINED_ALIGNMENT = new Alignment() {

        public int getAlignmentValue(View view, int i, int j)
        {
            return 0x80000000;
        }

        int getGravityOffset(View view, int i)
        {
            return 0x80000000;
        }

    }
;
    static final int UNINITIALIZED_HASH = 0;
    private static final int USE_DEFAULT_MARGINS = 5;
    public static final int VERTICAL = 1;
    int mAlignmentMode;
    int mDefaultGap;
    final Axis mHorizontalAxis;
    int mLastLayoutParamsHashCode;
    int mOrientation;
    Printer mPrinter;
    boolean mUseDefaultMargins;
    final Axis mVerticalAxis;

    static 
    {
        LEADING = new Alignment() {

            public int getAlignmentValue(View view, int i, int j)
            {
                return 0;
            }

            int getGravityOffset(View view, int i)
            {
                return 0;
            }

        }
;
        TRAILING = new Alignment() {

            public int getAlignmentValue(View view, int i, int j)
            {
                return i;
            }

            int getGravityOffset(View view, int i)
            {
                return i;
            }

        }
;
        TOP = LEADING;
        BOTTOM = TRAILING;
        START = LEADING;
        END = TRAILING;
        LEFT = createSwitchingAlignment(START, END);
        RIGHT = createSwitchingAlignment(END, START);
    }
}
