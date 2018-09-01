// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Bitmap;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import miui.maml.ScreenContext;
import miui.maml.ScreenElementRoot;
import miui.maml.data.*;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ElementGroup, AttrDataBinders, AnimatedScreenElement, ScreenElement, 
//            VariableArrayElement

public class ListScreenElement extends ElementGroup
{
    public static class Column
    {

        private void load(Element element)
        {
            mName = element.getAttribute("name");
            mTarget = element.getAttribute("target");
            mObserver = new VariableArrayElement.VarObserver() {

                public void onDataChange(Object aobj[])
                {
                    mList.addColumn(mName, aobj);
                }

                final Column this$1;

            
            {
                this$1 = Column.this;
                super();
            }
            }
;
        }

        public void finish()
        {
            if(mTargetElement != null)
                mTargetElement.registerVarObserver(mObserver, false);
        }

        public void init()
        {
label0:
            {
                if(mTargetElement == null)
                {
                    ScreenElement screenelement = mRoot.findElement(mTarget);
                    if(!(screenelement instanceof VariableArrayElement))
                        break label0;
                    mTargetElement = (VariableArrayElement)screenelement;
                }
                mTargetElement.registerVarObserver(mObserver, true);
                return;
            }
            Log.e("ListScreenElement", (new StringBuilder()).append("can't find VarArray:").append(mTarget).toString());
        }

        public ListScreenElement mList;
        public String mName;
        public VariableArrayElement.VarObserver mObserver;
        public ScreenElementRoot mRoot;
        public String mTarget;
        public VariableArrayElement mTargetElement;

        public Column(Element element, ScreenElementRoot screenelementroot, ListScreenElement listscreenelement)
        {
            mRoot = screenelementroot;
            mList = listscreenelement;
            if(element != null)
                load(element);
        }
    }

    public static class ColumnInfo
    {

        private static int[] _2D_getmiui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues != null)
                return _2D_miui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues;
            int ai[] = new int[Type.values().length];
            try
            {
                ai[Type.BITMAP.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror5) { }
            try
            {
                ai[Type.DOUBLE.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror4) { }
            try
            {
                ai[Type.FLOAT.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            try
            {
                ai[Type.INTEGER.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[Type.LONG.ordinal()] = 5;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[Type.STRING.ordinal()] = 6;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues = ai;
            return ai;
        }

        public static ArrayList createColumnsInfo(String s)
        {
            if(TextUtils.isEmpty(s))
                return null;
            ArrayList arraylist = new ArrayList();
            s = s.split(",");
            int i = 0;
            for(int j = s.length; i < j; i++)
                arraylist.add(new ColumnInfo(s[i]));

            return arraylist;
        }

        public boolean validate(Object obj)
        {
            if(obj == null)
                return true;
            switch(_2D_getmiui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues()[mType.ordinal()])
            {
            default:
                return false;

            case 6: // '\006'
                return obj instanceof String;

            case 1: // '\001'
                return obj instanceof Bitmap;

            case 4: // '\004'
                return obj instanceof Integer;

            case 2: // '\002'
                return obj instanceof Double;

            case 5: // '\005'
                return obj instanceof Long;

            case 3: // '\003'
                return obj instanceof Float;
            }
        }

        private static final int _2D_miui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues[];
        public Type mType;
        public String mVarName;

        public ColumnInfo(String s)
        {
            int i = s.indexOf(":");
            if(i == -1)
                throw new IllegalArgumentException((new StringBuilder()).append("List: invalid item data ").append(s).toString());
            mVarName = s.substring(0, i);
            s = s.substring(i + 1);
            if(ListScreenElement._2D_get6().equals(s))
                mType = Type.STRING;
            else
            if(ListScreenElement._2D_get0().equals(s))
                mType = Type.BITMAP;
            else
            if(ListScreenElement._2D_get3().equals(s) || ListScreenElement._2D_get4().equals(s))
                mType = Type.INTEGER;
            else
            if(ListScreenElement._2D_get1().equals(s))
                mType = Type.DOUBLE;
            else
            if(ListScreenElement._2D_get5().equals(s))
                mType = Type.LONG;
            else
            if(ListScreenElement._2D_get2().equals(s))
                mType = Type.FLOAT;
            else
                throw new IllegalArgumentException((new StringBuilder()).append("List: invalid item data type:").append(s).toString());
        }
    }

    public static final class ColumnInfo.Type extends Enum
    {

        public static ColumnInfo.Type valueOf(String s)
        {
            return (ColumnInfo.Type)Enum.valueOf(miui/maml/elements/ListScreenElement$ColumnInfo$Type, s);
        }

        public static ColumnInfo.Type[] values()
        {
            return $VALUES;
        }

        public boolean isNumber()
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = flag;
            if(this == INTEGER) goto _L2; else goto _L1
_L1:
            if(this != DOUBLE) goto _L4; else goto _L3
_L3:
            flag1 = flag;
_L2:
            return flag1;
_L4:
            flag1 = flag;
            if(this != LONG)
            {
                flag1 = flag;
                if(this != FLOAT)
                    flag1 = false;
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        private static final ColumnInfo.Type $VALUES[];
        public static final ColumnInfo.Type BITMAP;
        public static final ColumnInfo.Type DOUBLE;
        public static final ColumnInfo.Type FLOAT;
        public static final ColumnInfo.Type INTEGER;
        public static final ColumnInfo.Type LONG;
        public static final ColumnInfo.Type STRING;

        static 
        {
            STRING = new ColumnInfo.Type("STRING", 0);
            BITMAP = new ColumnInfo.Type("BITMAP", 1);
            INTEGER = new ColumnInfo.Type("INTEGER", 2);
            DOUBLE = new ColumnInfo.Type("DOUBLE", 3);
            LONG = new ColumnInfo.Type("LONG", 4);
            FLOAT = new ColumnInfo.Type("FLOAT", 5);
            $VALUES = (new ColumnInfo.Type[] {
                STRING, BITMAP, INTEGER, DOUBLE, LONG, FLOAT
            });
        }

        private ColumnInfo.Type(String s, int i)
        {
            super(s, i);
        }
    }

    private static class DataIndexMap
    {

        public void setData(int i, Object obj)
        {
            if(mData != null && mData.length > i)
            {
                mData[i] = obj;
                mNeedRebind = true;
            }
        }

        public Object mData[];
        public int mElementIndex;
        public boolean mNeedRebind;

        public DataIndexMap(Object aobj[])
        {
            this(aobj, -1);
        }

        public DataIndexMap(Object aobj[], int i)
        {
            mElementIndex = -1;
            mData = aobj;
            mElementIndex = i;
        }
    }

    public static class ListData
    {

        private void load(Element element)
        {
            Utils.traverseXmlElementChildren(element, "Column", new miui.maml.util.Utils.XmlTraverseListener() {

                public void onChild(Element element)
                {
                    mColumns.add(new Column(element, mRoot, mList));
                }

                final ListData this$1;

            
            {
                this$1 = ListData.this;
                super();
            }
            }
);
        }

        public void finish()
        {
            Iterator iterator = mColumns.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Column column = (Column)iterator.next();
                if(column != null)
                    column.finish();
            } while(true);
        }

        public void init()
        {
            Iterator iterator = mColumns.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Column column = (Column)iterator.next();
                if(column != null)
                    column.init();
            } while(true);
        }

        public ArrayList mColumns;
        public ListScreenElement mList;
        public ScreenElementRoot mRoot;

        public ListData(Element element, ScreenElementRoot screenelementroot, ListScreenElement listscreenelement)
        {
            mColumns = new ArrayList();
            mRoot = screenelementroot;
            mList = listscreenelement;
            if(element != null)
                load(element);
        }
    }

    private static class ListItemElement extends ElementGroup
    {

        public int getDataIndex()
        {
            return mDataIndex;
        }

        public void setDataIndex(int i)
        {
            mDataIndex = i;
            if(mDivider != null)
                if(i <= 0)
                    mDivider.show(false);
                else
                    mDivider.show(true);
        }

        public static final String TAG_NAME = "Item";
        private int mDataIndex;
        private AnimatedScreenElement mDivider;
        protected Element mNode;

        public ListItemElement(Element element, ScreenElementRoot screenelementroot)
        {
            super(element, screenelementroot);
            mDataIndex = -1;
            mNode = element;
            element = findElement("divider");
            if(element instanceof AnimatedScreenElement)
            {
                mDivider = (AnimatedScreenElement)element;
                removeElement(element);
                addElement(mDivider);
            }
            mAlignV = ScreenElement.AlignV.TOP;
        }
    }


    static String _2D_get0()
    {
        return DATA_TYPE_BITMAP;
    }

    static String _2D_get1()
    {
        return DATA_TYPE_DOUBLE;
    }

    static String _2D_get2()
    {
        return DATA_TYPE_FLOAT;
    }

    static String _2D_get3()
    {
        return DATA_TYPE_INTEGER;
    }

    static String _2D_get4()
    {
        return DATA_TYPE_INTEGER1;
    }

    static String _2D_get5()
    {
        return DATA_TYPE_LONG;
    }

    static String _2D_get6()
    {
        return DATA_TYPE_STRING;
    }

    public ListScreenElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mDataList = new ArrayList();
        mIndexOrder = new ArrayList();
        mReuseIndex = new ArrayList();
        mCurrentIndex = -1;
        if(mItem == null)
        {
            Log.e("ListScreenElement", "no item");
            throw new IllegalArgumentException("List: no item");
        }
        setClip(true);
        mMaxHeight = Expression.build(getVariables(), element.getAttribute("maxHeight"));
        mClearOnFinish = Boolean.parseBoolean(element.getAttribute("clearOnFinish"));
        screenelementroot = element.getAttribute("data");
        if(TextUtils.isEmpty(screenelementroot))
        {
            Log.e("ListScreenElement", "no data");
            throw new IllegalArgumentException("List: no data");
        }
        mColumnsInfo = ColumnInfo.createColumnsInfo(screenelementroot);
        if(mColumnsInfo == null)
        {
            Log.e("ListScreenElement", "invalid item data");
            throw new IllegalArgumentException("List: invalid item data");
        }
        mIndexedVariables = new IndexedVariable[mColumnsInfo.size()];
        screenelementroot = Utils.getChild(element, "AttrDataBinders");
        if(screenelementroot == null)
        {
            Log.e("ListScreenElement", "no attr data binder");
            throw new IllegalArgumentException("List: no attr data binder");
        }
        mAttrDataBinders = new AttrDataBinders(screenelementroot, mRoot.getContext().mContextVariables);
        element = Utils.getChild(element, "Data");
        if(element != null)
            mListData = new ListData(element, mRoot, this);
        element = findElement("scrollbar");
        if(element instanceof AnimatedScreenElement)
        {
            mScrollBar = (AnimatedScreenElement)element;
            mScrollBar.mAlignV = ScreenElement.AlignV.TOP;
            removeElement(element);
            addElement(mScrollBar);
        }
        mSelectedIdVar = new IndexedVariable((new StringBuilder()).append(mName).append(".selectedId").toString(), mRoot.getContext().mVariables, true);
    }

    private void bindData(ListItemElement listitemelement, int i, int j)
    {
        if(j < 0 || j >= mItemCount)
        {
            Log.e("ListScreenElement", "invalid item data");
            return;
        }
        Object aobj[] = ((DataIndexMap)mDataList.get(j)).mData;
        listitemelement.setDataIndex(j);
        ((DataIndexMap)mDataList.get(j)).mElementIndex = i;
        ((DataIndexMap)mDataList.get(j)).mNeedRebind = false;
        listitemelement.setY((float)(double)((float)j * mItem.getHeight()));
        j = mColumnsInfo.size();
        ContextVariables contextvariables = getContext().mContextVariables;
        for(i = 0; i < j; i++)
            contextvariables.setVar(((ColumnInfo)mColumnsInfo.get(i)).mVarName, aobj[i]);

        if(mAttrDataBinders != null)
            mAttrDataBinders.bind(listitemelement);
    }

    private void checkVisibility()
    {
        ArrayList arraylist = mInnerGroup.getElements();
        int i = 0;
        while(i < arraylist.size()) 
        {
            ListItemElement listitemelement = (ListItemElement)arraylist.get(i);
            int j = listitemelement.getDataIndex();
            if(j >= 0 && j >= mTopIndex && j <= mBottomIndex)
            {
                if(!listitemelement.isVisible())
                    listitemelement.show(true);
            } else
            if(listitemelement.isVisible())
                listitemelement.show(false);
            i++;
        }
    }

    private void clearEmptyRow()
    {
        int i = mDataList.size() - 1;
_L10:
        if(i < 0) goto _L2; else goto _L1
_L1:
        Object aobj[];
        boolean flag;
        int j;
        int k;
        aobj = ((DataIndexMap)mDataList.get(i)).mData;
        flag = true;
        j = aobj.length;
        k = 0;
_L8:
        boolean flag1 = flag;
        if(k >= j) goto _L4; else goto _L3
_L3:
        if(aobj[k] == null) goto _L6; else goto _L5
_L5:
        flag1 = false;
_L4:
        if(flag1)
            break; /* Loop/switch isn't completed */
_L2:
        return;
_L6:
        k++;
        if(true) goto _L8; else goto _L7
_L7:
        removeItem(i);
        i--;
        if(true) goto _L10; else goto _L9
_L9:
    }

    private ListItemElement getItem(int i)
    {
        int j;
        ListItemElement listitemelement1;
label0:
        {
            if(i < 0 || i >= mItemCount)
                return null;
            j = ((DataIndexMap)mDataList.get(i)).mElementIndex;
            ListItemElement listitemelement = null;
            if(j >= 0)
                listitemelement = (ListItemElement)mInnerGroup.getElements().get(j);
            if(j >= 0)
            {
                listitemelement1 = listitemelement;
                if(listitemelement.getDataIndex() == i)
                    break label0;
            }
            int k = getUseableElementIndex();
            listitemelement = (ListItemElement)mInnerGroup.getElements().get(k);
            j = k;
            listitemelement1 = listitemelement;
            if(listitemelement.getDataIndex() < 0)
            {
                listitemelement.reset();
                listitemelement1 = listitemelement;
                j = k;
            }
        }
        if(listitemelement1.getDataIndex() != i || ((DataIndexMap)mDataList.get(i)).mNeedRebind)
            bindData(listitemelement1, j, i);
        return listitemelement1;
    }

    private int getUseableElementIndex()
    {
        int i;
        if(mReuseIndex.size() > 0)
            i = ((Integer)mReuseIndex.remove(0)).intValue();
        else
        if(mIsUpDirection)
            i = ((Integer)mIndexOrder.remove(0)).intValue();
        else
            i = ((Integer)mIndexOrder.remove(mIndexOrder.size() - 1)).intValue();
        if(mIsUpDirection)
            mIndexOrder.add(Integer.valueOf(i));
        else
            mIndexOrder.add(0, Integer.valueOf(i));
        return i;
    }

    private void moveTo(double d)
    {
        double d1 = d;
        if(d < (double)(getHeight() - (float)mItemCount * mItem.getHeight()))
        {
            d1 = getHeight() - (float)mItemCount * mItem.getHeight();
            mStartAnimTime = 0L;
        }
        d = d1;
        if(d1 > 0.0D)
        {
            d = 0.0D;
            mStartAnimTime = 0L;
        }
        mInnerGroup.setY((float)d);
        mTopIndex = Math.min((int)Math.floor(-d / (double)mItem.getHeight()), mItemCount - (int)(getHeight() / mItem.getHeight()) - 1);
        mBottomIndex = Math.min((int)(getHeight() / mItem.getHeight()) + mTopIndex, mItemCount - 1);
        for(int i = mTopIndex; i <= mBottomIndex; i++)
            getItem(i);

        checkVisibility();
        updateScorllBar();
    }

    private void resetInner()
    {
        if(mScrollBar != null)
            mScrollBar.show(false);
        mMoving = false;
        mIsScroll = false;
        mIsChildScroll = false;
        mStartAnimTime = -1L;
        mSpeed = 0.0D;
    }

    private void setVariables()
    {
        int i = mColumnsInfo.size();
        int j = 0;
        while(j < i) 
        {
            Object obj = (ColumnInfo)mColumnsInfo.get(j);
            if(((ColumnInfo) (obj)).mType != ColumnInfo.Type.BITMAP)
            {
                if(mIndexedVariables[j] == null)
                    mIndexedVariables[j] = new IndexedVariable((new StringBuilder()).append(mName).append(".").append(((ColumnInfo) (obj)).mVarName).toString(), mRoot.getContext().mVariables, ((ColumnInfo) (obj)).mType.isNumber());
                IndexedVariable indexedvariable = mIndexedVariables[j];
                if(mSelectedId < 0)
                    obj = null;
                else
                    obj = ((DataIndexMap)mDataList.get(mSelectedId)).mData[j];
                indexedvariable.set(obj);
            }
            j++;
        }
    }

    private void startAnimation()
    {
        mStartAnimTime = SystemClock.elapsedRealtime();
        mStartAnimY = mInnerGroup.getY();
    }

    private void updateScorllBar()
    {
        if(mScrollBar != null && mIsScroll)
        {
            double d = (float)mItemCount * mItem.getHeight();
            double d1 = getHeight();
            double d2 = d1 / d;
            boolean flag = true;
            double d3 = d2;
            if(d2 >= 1.0D)
            {
                d3 = 0.0D;
                flag = false;
            }
            d = (double)mInnerGroup.getY() / (d1 - d);
            d2 = d;
            if(d > 1.0D)
                d2 = 1.0D;
            mScrollBar.setY((float)((1.0D - d3) * d1 * d2));
            mScrollBar.setH((float)(d1 * d3));
            if(mScrollBar.isVisible() != flag)
                mScrollBar.show(flag);
        }
    }

    public void addColumn(String s, Object aobj[])
    {
        if(s == null || aobj == null)
            return;
        int i = -1;
        int j = mColumnsInfo.size();
        int k = 0;
        int j1;
label0:
        do
        {
label1:
            {
                j1 = i;
                if(k < j)
                {
                    if(!s.equals(((ColumnInfo)mColumnsInfo.get(k)).mVarName))
                        break label1;
                    j1 = k;
                }
                if(j1 < 0)
                    return;
                break label0;
            }
            k++;
        } while(true);
        j = aobj.length;
        i = mDataList.size();
        for(int l = 0; l < i; l++)
        {
            s = null;
            if(l < j)
                s = ((String) (aobj[l]));
            ((DataIndexMap)mDataList.get(l)).setData(j1, s);
            if(((DataIndexMap)mDataList.get(l)).mElementIndex >= 0)
                getItem(l);
        }

        for(int i1 = i; i1 < j; i1++)
        {
            s = ((String) (new Object[mColumnsInfo.size()]));
            s[j1] = aobj[i1];
            addItem(s);
        }

        clearEmptyRow();
        requestUpdate();
    }

    public transient void addItem(Object aobj[])
    {
        if(aobj == null)
            return;
        if(aobj.length != mColumnsInfo.size())
        {
            Log.e("ListScreenElement", "invalid item data count");
            return;
        }
        int i = aobj.length;
        for(int j = 0; j < i; j++)
            if(!((ColumnInfo)mColumnsInfo.get(j)).validate(aobj[j]))
            {
                Log.e("ListScreenElement", (new StringBuilder()).append("invalid item data type: ").append(aobj[j]).toString());
                return;
            }

        mDataList.add(new DataIndexMap((Object[])aobj.clone()));
        mCurrentIndex = mCurrentIndex + 1;
        mItemCount = mItemCount + 1;
        setActualHeight(descale(getHeight()));
        mVisibleItemCount = (int)(Math.max(super.getHeight(), scale(evaluate(mMaxHeight))) / mItem.getHeight());
        mCachedItemCount = mVisibleItemCount * 2;
        int k = mInnerGroup.getElements().size();
        if(k < mCachedItemCount)
        {
            aobj = new ListItemElement(mItem.mNode, mItem.mRoot);
            mInnerGroup.addElement(((ScreenElement) (aobj)));
            ((DataIndexMap)mDataList.get(mCurrentIndex)).mElementIndex = k;
            mSelectedId = mCurrentIndex;
            ((ListItemElement) (aobj)).init();
            mSelectedId = -1;
            bindData(((ListItemElement) (aobj)), k, mCurrentIndex);
            mIndexOrder.add(Integer.valueOf(mCurrentIndex));
        }
        requestUpdate();
    }

    protected void doTick(long l)
    {
        super.doTick(l);
        if(mStartAnimTime >= 0L && mPressed ^ true)
        {
            l -= mStartAnimTime;
            if(mStartAnimTime == 0L || mSpeed + (ACC * (double)l) / 1000D < 0.0D)
            {
                resetInner();
            } else
            {
                mOffsetY = (mSpeed * (double)l) / 1000D + (ACC * 0.5D * (double)l * (double)l) / 1000000D;
                double d = mStartAnimY;
                double d1;
                if(mIsUpDirection)
                    d1 = -mOffsetY;
                else
                    d1 = mOffsetY;
                moveTo(d + d1);
            }
            requestUpdate();
        }
    }

    public ScreenElement findElement(String s)
    {
        if(mSelectedId >= 0 && mSelectedId < mItemCount)
        {
            int i = ((DataIndexMap)mDataList.get(mSelectedId)).mElementIndex;
            if(i >= 0)
            {
                ScreenElement screenelement = ((ListItemElement)mInnerGroup.getElements().get(i)).findElement(s);
                if(screenelement != null)
                    return screenelement;
            }
        }
        return super.findElement(s);
    }

    public void finish()
    {
        super.finish();
        if(mClearOnFinish)
            removeAllItems();
        if(mListData != null)
            mListData.finish();
    }

    public ArrayList getColumnsInfo()
    {
        return mColumnsInfo;
    }

    public float getHeight()
    {
        float f;
        if(mMaxHeight == null)
            f = super.getHeight();
        else
            f = Math.min((float)mItemCount * mItem.getHeight(), scale(evaluate(mMaxHeight)));
        return f;
    }

    public void init()
    {
        super.init();
        resetInner();
        mInnerGroup.setY(0.0D);
        setActualHeight(descale(getHeight()));
        mSelectedId = -1;
        mSelectedIdVar.set(mSelectedId);
        setVariables();
        if(mListData != null)
            mListData.init();
    }

    protected ScreenElement onCreateChild(Element element)
    {
        if(element.getTagName().equalsIgnoreCase("Item") && mInnerGroup == null)
        {
            mItem = new ListItemElement(element, mRoot);
            mInnerGroup = new ElementGroup(null, mRoot);
            return mInnerGroup;
        } else
        {
            return super.onCreateChild(element);
        }
    }

    public boolean onTouch(MotionEvent motionevent)
    {
        float f;
        float f1;
        boolean flag;
        if(!isVisible())
            return false;
        f = motionevent.getX();
        f1 = motionevent.getY();
        flag = false;
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 56
    //                   0 78
    //                   1 439
    //                   2 193
    //                   3 499;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        break; /* Loop/switch isn't completed */
_L5:
        break MISSING_BLOCK_LABEL_499;
_L6:
        boolean flag1;
        double d;
        double d1;
        if(!super.onTouch(motionevent))
        {
            if(flag)
                flag1 = mInterceptTouch;
            else
                flag1 = false;
        } else
        {
            flag1 = true;
        }
        return flag1;
_L2:
        if(touched(f, f1))
        {
            mMoving = true;
            mPressed = true;
            performAction("down");
            onActionDown(f, f1);
            mStartAnimTime = -1L;
            mSpeed = 0.0D;
            mLastTime = SystemClock.elapsedRealtime();
            mSelectedId = (int)Math.floor((f1 - mInnerGroup.getAbsoluteTop()) / mItem.getHeight());
            mSelectedIdVar.set(mSelectedId);
            setVariables();
            mTouchStartX = f;
            mTouchStartY = f1;
            updateScorllBar();
            flag = true;
        }
          goto _L6
_L4:
        if(mMoving)
        {
            mCurrentTime = SystemClock.elapsedRealtime();
            mOffsetY = (double)f1 - mTouchStartY;
            mOffsetX = (double)f - mTouchStartX;
            if(!mIsScroll && mIsChildScroll ^ true)
            {
                d = Math.abs(mOffsetY);
                d1 = Math.abs(mOffsetX);
                if(d > 5D && !mIsChildScroll && d >= d1)
                    mIsScroll = true;
                else
                if(d1 > 5D && !mIsScroll && d < d1)
                    mIsChildScroll = true;
            }
            if(mOffsetY >= 0.0D)
                flag1 = mIsChildScroll;
            else
                flag1 = true;
            mIsUpDirection = flag1;
            if(mIsScroll)
            {
                motionevent.setAction(3);
                performAction("move");
                onActionMove(f, f1);
                mSpeed = (Math.abs(mOffsetY) / (double)(mCurrentTime - mLastTime)) * 1000D;
                moveTo((double)mInnerGroup.getY() + mOffsetY);
                mTouchStartY = f1;
                mLastTime = mCurrentTime;
            }
            flag = true;
        }
          goto _L6
_L3:
        mPressed = false;
        if(mMoving)
        {
            Log.i("ListScreenElement", "unlock touch up");
            performAction("up");
            onActionUp();
            if(mSpeed < 400D)
                resetInner();
            else
                startAnimation();
            flag = true;
        }
          goto _L6
        mPressed = false;
        if(mMoving)
        {
            performAction("cancel");
            resetInner();
            mStartAnimTime = -1L;
            flag = true;
        }
          goto _L6
    }

    public void removeAllItems()
    {
        mInnerGroup.removeAllElements();
        mInnerGroup.setY(0.0D);
        mDataList.clear();
        mIndexOrder.clear();
        mReuseIndex.clear();
        mCurrentIndex = -1;
        mItemCount = 0;
        setActualHeight(descale(getHeight()));
    }

    public void removeItem(int i)
    {
        if(i < 0 || i >= mItemCount)
            return;
        mDataList.remove(i);
        mItemCount = mItemCount - 1;
        setActualHeight(descale(getHeight()));
        int j = mIndexOrder.size();
        int k = 0;
        int l = 0;
        while(l < j) 
        {
            ListItemElement listitemelement = (ListItemElement)mInnerGroup.getElements().get(((Integer)mIndexOrder.get(l)).intValue());
            int i1 = listitemelement.getDataIndex();
            int j1;
            if(i1 == i)
            {
                j1 = l;
                listitemelement.setDataIndex(-1);
                listitemelement.setY(-1.7976931348623157E+308D);
                listitemelement.show(false);
            } else
            {
                j1 = k;
                if(i1 > i)
                {
                    listitemelement.setDataIndex(i1 - 1);
                    listitemelement.setY((float)(i1 - 1) * mItem.getHeight());
                    j1 = k;
                }
            }
            l++;
            k = j1;
        }
        if(j > 0)
        {
            i = ((Integer)mIndexOrder.remove(k)).intValue();
            moveTo(mInnerGroup.getY());
            mReuseIndex.add(Integer.valueOf(i));
        }
        requestUpdate();
    }

    private static double ACC = 0D;
    private static String DATA_TYPE_BITMAP = "bitmap";
    private static String DATA_TYPE_DOUBLE = "double";
    private static String DATA_TYPE_FLOAT = "float";
    private static String DATA_TYPE_INTEGER = "int";
    private static String DATA_TYPE_INTEGER1 = "integer";
    private static String DATA_TYPE_LONG = "long";
    private static String DATA_TYPE_STRING = "string";
    private static final String LOG_TAG = "ListScreenElement";
    public static final String TAG_NAME = "List";
    protected AttrDataBinders mAttrDataBinders;
    private int mBottomIndex;
    private int mCachedItemCount;
    private boolean mClearOnFinish;
    private ArrayList mColumnsInfo;
    private int mCurrentIndex;
    private long mCurrentTime;
    private ArrayList mDataList;
    private ArrayList mIndexOrder;
    private IndexedVariable mIndexedVariables[];
    private ElementGroup mInnerGroup;
    private boolean mIsChildScroll;
    private boolean mIsScroll;
    private boolean mIsUpDirection;
    private ListItemElement mItem;
    private int mItemCount;
    private long mLastTime;
    protected ListData mListData;
    private Expression mMaxHeight;
    private boolean mMoving;
    private double mOffsetX;
    private double mOffsetY;
    private boolean mPressed;
    private ArrayList mReuseIndex;
    private AnimatedScreenElement mScrollBar;
    private int mSelectedId;
    private IndexedVariable mSelectedIdVar;
    private double mSpeed;
    private long mStartAnimTime;
    private float mStartAnimY;
    private int mTopIndex;
    private double mTouchStartX;
    private double mTouchStartY;
    private int mVisibleItemCount;

    static 
    {
        ACC = -800D;
    }
}
