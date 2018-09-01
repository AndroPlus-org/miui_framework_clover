// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.*;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import java.util.*;
import miui.maml.ScreenContext;
import miui.maml.ScreenElementRoot;
import miui.maml.elements.ImageScreenElement;
import miui.maml.elements.ListScreenElement;
import miui.maml.util.TextFormatter;
import miui.maml.util.Utils;
import miui.os.SystemProperties;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.data:
//            VariableBinder, Expression, IndexedVariable, Variables, 
//            AsyncQueryHandler

public class ContentProviderBinder extends VariableBinder
{
    public static class Builder
    {

        public void addVariable(String s, String s1, String s2, int i, Variables variables)
        {
            s = new Variable(s, s1, variables);
            s.mColumn = s2;
            s.mRow = i;
            mBinder.addVariable(s);
        }

        public Builder setArgs(String as[])
        {
            mBinder.mArgs = as;
            return this;
        }

        public Builder setColumns(String as[])
        {
            mBinder.mColumns = as;
            return this;
        }

        public Builder setCountName(String s)
        {
            mBinder.mCountName = s;
            mBinder.createCountVar();
            return this;
        }

        public Builder setName(String s)
        {
            mBinder.mName = s;
            return this;
        }

        public Builder setOrder(String s)
        {
            mBinder.mOrder = s;
            return this;
        }

        public Builder setWhere(String s)
        {
            mBinder.mWhereFormatter = new TextFormatter(mBinder.getVariables(), s);
            return this;
        }

        public Builder setWhere(String s, String s1)
        {
            mBinder.mWhereFormatter = new TextFormatter(mBinder.getVariables(), s, s1);
            return this;
        }

        private ContentProviderBinder mBinder;

        protected Builder(ContentProviderBinder contentproviderbinder)
        {
            mBinder = contentproviderbinder;
        }
    }

    private class ChangeObserver extends ContentObserver
    {

        public boolean deliverSelfNotifications()
        {
            return true;
        }

        public void onChange(boolean flag)
        {
            onContentChanged();
        }

        final ContentProviderBinder this$0;

        public ChangeObserver()
        {
            this$0 = ContentProviderBinder.this;
            super(ContentProviderBinder._2D_get0(ContentProviderBinder.this));
        }
    }

    private static class List
    {

        private static int[] _2D_getmiui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues != null)
                return _2D_miui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues;
            int ai[] = new int[miui.maml.elements.ListScreenElement.ColumnInfo.Type.values().length];
            try
            {
                ai[miui.maml.elements.ListScreenElement.ColumnInfo.Type.BITMAP.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror5) { }
            try
            {
                ai[miui.maml.elements.ListScreenElement.ColumnInfo.Type.DOUBLE.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror4) { }
            try
            {
                ai[miui.maml.elements.ListScreenElement.ColumnInfo.Type.FLOAT.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            try
            {
                ai[miui.maml.elements.ListScreenElement.ColumnInfo.Type.INTEGER.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[miui.maml.elements.ListScreenElement.ColumnInfo.Type.LONG.ordinal()] = 5;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[miui.maml.elements.ListScreenElement.ColumnInfo.Type.STRING.ordinal()] = 6;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues = ai;
            return ai;
        }

        public void fill(Cursor cursor)
        {
            ArrayList arraylist;
            int i;
            int ai[];
            Object aobj[];
            int j;
            int k;
            if(cursor == null)
                return;
            if(mList == null)
            {
                mList = (ListScreenElement)mRoot.findElement(mName);
                if(mList == null)
                {
                    Log.e("ContentProviderBinder", (new StringBuilder()).append("fail to find list: ").append(mName).toString());
                    return;
                }
            }
            mList.removeAllItems();
            arraylist = mList.getColumnsInfo();
            i = arraylist.size();
            ai = new int[i];
            aobj = new Object[i];
            j = 0;
            do
            {
                if(j >= ai.length)
                    break;
                try
                {
                    ai[j] = cursor.getColumnIndexOrThrow(((miui.maml.elements.ListScreenElement.ColumnInfo)arraylist.get(j)).mVarName);
                }
                // Misplaced declaration of an exception variable
                catch(Cursor cursor)
                {
                    Log.e("ContentProviderBinder", (new StringBuilder()).append("illegal column:").append(((miui.maml.elements.ListScreenElement.ColumnInfo)arraylist.get(j)).mVarName).append(" ").append(cursor.toString()).toString());
                    return;
                }
                j++;
            } while(true);
            cursor.moveToFirst();
            k = cursor.getCount();
            j = k;
            if(k > mMaxCount)
                j = mMaxCount;
            k = 0;
_L14:
            int l;
            if(k >= j)
                break; /* Loop/switch isn't completed */
            l = 0;
_L12:
            miui.maml.elements.ListScreenElement.ColumnInfo columninfo;
            int i1;
            if(l >= i)
                break MISSING_BLOCK_LABEL_486;
            aobj[l] = null;
            columninfo = (miui.maml.elements.ListScreenElement.ColumnInfo)arraylist.get(l);
            i1 = ai[l];
            if(cursor.isNull(i1)) goto _L2; else goto _L1
_L1:
            _2D_getmiui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues()[columninfo.mType.ordinal()];
            JVM INSTR lookupswitch 2: default 312
        //                       1: 378
        //                       6: 362;
               goto _L3 _L4 _L5
_L5:
            break; /* Loop/switch isn't completed */
_L3:
            _2D_getmiui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues()[columninfo.mType.ordinal()];
            JVM INSTR tableswitch 2 5: default 356
        //                       2 410
        //                       3 429
        //                       4 448
        //                       5 467;
               goto _L6 _L7 _L8 _L9 _L10
_L6:
            break; /* Loop/switch isn't completed */
_L10:
            break MISSING_BLOCK_LABEL_467;
_L2:
            l++;
            if(true) goto _L12; else goto _L11
_L11:
            aobj[l] = cursor.getString(i1);
              goto _L2
_L4:
            byte abyte0[] = cursor.getBlob(i1);
            if(abyte0 != null)
                aobj[l] = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length);
              goto _L2
_L7:
            aobj[l] = Double.valueOf(cursor.getDouble(i1));
              goto _L2
_L8:
            aobj[l] = Float.valueOf(cursor.getFloat(i1));
              goto _L2
_L9:
            aobj[l] = Integer.valueOf(cursor.getInt(i1));
              goto _L2
            aobj[l] = Long.valueOf(cursor.getLong(i1));
              goto _L2
            mList.addItem(aobj);
            cursor.moveToNext();
            k++;
            if(true) goto _L14; else goto _L13
_L13:
        }

        private static final int _2D_miui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues[];
        private ListScreenElement mList;
        private int mMaxCount;
        private String mName;
        private ScreenElementRoot mRoot;

        public List(Element element, ScreenElementRoot screenelementroot)
        {
            mName = element.getAttribute("name");
            mMaxCount = Utils.getAttrAsInt(element, "maxCount", 0x7fffffff);
            mRoot = screenelementroot;
        }
    }

    public static interface QueryCompleteListener
    {

        public abstract void onQueryCompleted(String s);
    }

    private final class QueryHandler extends AsyncQueryHandler
    {

        protected Handler createHandler(Looper looper)
        {
            return new CatchingWorkerHandler(looper);
        }

        protected void onQueryComplete(int i, Object obj, Cursor cursor)
        {
            ContentProviderBinder._2D_wrap1(ContentProviderBinder.this, cursor);
        }

        final ContentProviderBinder this$0;

        public QueryHandler(Context context)
        {
            this$0 = ContentProviderBinder.this;
            super(Looper.getMainLooper(), context.getContentResolver());
        }
    }

    protected class QueryHandler.CatchingWorkerHandler extends AsyncQueryHandler.WorkerHandler
    {

        public void handleMessage(Message message)
        {
            super.handleMessage(message);
_L1:
            return;
            message;
            Log.w("ContentProviderBinder", "Exception on background worker thread", message);
              goto _L1
            message;
            Log.w("ContentProviderBinder", "Exception on background worker thread", message);
              goto _L1
            message;
            Log.w("ContentProviderBinder", "Exception on background worker thread", message);
              goto _L1
        }

        final QueryHandler this$1;

        public QueryHandler.CatchingWorkerHandler(Looper looper)
        {
            this$1 = QueryHandler.this;
            super(QueryHandler.this, looper);
        }
    }

    private static class Variable extends VariableBinder.Variable
    {

        public ImageScreenElement getImageElement(ScreenElementRoot screenelementroot)
        {
            if(mImageVar == null && mNoImageElement ^ true)
            {
                mImageVar = (ImageScreenElement)screenelementroot.findElement(mName);
                boolean flag;
                if(mImageVar == null)
                    flag = true;
                else
                    flag = false;
                mNoImageElement = flag;
            }
            return mImageVar;
        }

        protected int parseType(String s)
        {
            int i = super.parseType(s);
            if("blob.bitmap".equalsIgnoreCase(mTypeStr))
                i = 1001;
            else
                mNoImageElement = true;
            return i;
        }

        public void setNull(ScreenElementRoot screenelementroot)
        {
            if(getImageElement(screenelementroot) != null)
                getImageElement(screenelementroot).setBitmap(null);
            else
                set(null);
        }

        public static final int BLOB_BITMAP = 1001;
        public boolean mBlocked;
        public String mColumn;
        private ImageScreenElement mImageVar;
        private boolean mNoImageElement;
        public int mRow;

        public Variable(String s, String s1, Variables variables)
        {
            super(s, s1, variables);
        }

        public Variable(Element element, Variables variables)
        {
            super(element, variables);
            mColumn = element.getAttribute("column");
            mRow = Utils.getAttrAsInt(element, "row", 0);
        }
    }


    static Handler _2D_get0(ContentProviderBinder contentproviderbinder)
    {
        return contentproviderbinder.mHandler;
    }

    static void _2D_wrap0(ContentProviderBinder contentproviderbinder)
    {
        contentproviderbinder.checkUpdate();
    }

    static void _2D_wrap1(ContentProviderBinder contentproviderbinder, Cursor cursor)
    {
        contentproviderbinder.onQueryComplete(cursor);
    }

    public ContentProviderBinder(ScreenElementRoot screenelementroot)
    {
        this(null, screenelementroot);
    }

    public ContentProviderBinder(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mChangeObserver = new ChangeObserver();
        mUpdateInterval = -1;
        mNeedsRequery = true;
        mHandler = screenelementroot.getContext().getHandler();
        mQueryHandler = new QueryHandler(getContext().mContext);
        if(element != null)
            load(element);
    }

    private void checkUpdate()
    {
        if(mUpdateInterval <= 0)
            return;
        mHandler.removeCallbacks(mUpdater);
        long l = System.currentTimeMillis() - mLastQueryTime;
        long l1 = l;
        if(l >= (long)(mUpdateInterval * 1000))
        {
            startQuery();
            l1 = 0L;
        }
        mHandler.postDelayed(mUpdater, (long)(mUpdateInterval * 1000) - l1);
    }

    private void load(Element element)
    {
        Variables variables = getVariables();
        Object obj = Expression.build(variables, element.getAttribute("uriExp"));
        Object obj1 = Expression.build(variables, element.getAttribute("uriFormatExp"));
        mUriFormatter = new TextFormatter(variables, element.getAttribute("uri"), element.getAttribute("uriFormat"), element.getAttribute("uriParas"), ((Expression) (obj)), ((Expression) (obj1)));
        obj = element.getAttribute("columns");
        if(TextUtils.isEmpty(((CharSequence) (obj))))
            obj = null;
        else
            obj = ((String) (obj)).split(",");
        mColumns = ((String []) (obj));
        obj1 = Expression.build(variables, element.getAttribute("whereExp"));
        obj = Expression.build(variables, element.getAttribute("whereFormatExp"));
        mWhereFormatter = new TextFormatter(variables, element.getAttribute("where"), element.getAttribute("whereFormat"), element.getAttribute("whereParas"), ((Expression) (obj1)), ((Expression) (obj)));
        obj = element.getAttribute("args");
        if(TextUtils.isEmpty(((CharSequence) (obj))))
            obj = null;
        else
            obj = ((String) (obj)).split(",");
        mArgs = ((String []) (obj));
        obj1 = element.getAttribute("order");
        obj = obj1;
        if(TextUtils.isEmpty(((CharSequence) (obj1))))
            obj = null;
        mOrder = ((String) (obj));
        obj1 = element.getAttribute("countName");
        obj = obj1;
        if(TextUtils.isEmpty(((CharSequence) (obj1))))
            obj = null;
        mCountName = ((String) (obj));
        if(mCountName != null)
            mCountVar = new IndexedVariable(mCountName, variables, true);
        mUpdateInterval = Utils.getAttrAsInt(element, "updateInterval", -1);
        if(mUpdateInterval > 0)
            mUpdater = new Runnable() {

                public void run()
                {
                    ContentProviderBinder._2D_wrap0(ContentProviderBinder.this);
                }

                final ContentProviderBinder this$0;

            
            {
                this$0 = ContentProviderBinder.this;
                super();
            }
            }
;
        loadVariables(element);
        obj = Utils.getChild(element, "List");
        if(obj != null)
            try
            {
                List list = JVM INSTR new #14  <Class ContentProviderBinder$List>;
                list.List(((Element) (obj)), mRoot);
                mList = list;
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                Log.e("ContentProviderBinder", "invalid List");
            }
        mAwareChangeWhilePause = Boolean.parseBoolean(element.getAttribute("vigilant"));
    }

    private void onQueryComplete(Cursor cursor)
    {
        if(cursor != null)
        {
            if(!mFinished)
                updateVariables(cursor);
            cursor.close();
        }
        onUpdateComplete();
    }

    private void registerObserver(Uri uri, boolean flag)
    {
        ContentResolver contentresolver;
        contentresolver = getContext().mContext.getContentResolver();
        contentresolver.unregisterContentObserver(mChangeObserver);
        if(!flag)
            break MISSING_BLOCK_LABEL_33;
        contentresolver.registerContentObserver(uri, true, mChangeObserver);
_L1:
        return;
        Object obj;
        obj;
        Log.e("ContentProviderBinder", (new StringBuilder()).append(((SecurityException) (obj)).toString()).append("  uri:").append(uri).toString());
          goto _L1
        obj;
        Log.e("ContentProviderBinder", (new StringBuilder()).append(((IllegalArgumentException) (obj)).toString()).append("  uri:").append(uri).toString());
          goto _L1
    }

    private void updateVariables(Cursor cursor)
    {
        Iterator iterator;
        int i;
        if(cursor == null)
            i = 0;
        else
            i = cursor.getCount();
        if(mCountVar != null)
            mCountVar.set(i);
        if(mList != null)
            mList.fill(cursor);
        if(cursor == null || i == 0)
        {
            for(cursor = mVariables.iterator(); cursor.hasNext(); ((Variable)(VariableBinder.Variable)cursor.next()).setNull(mRoot));
            return;
        }
        iterator = mVariables.iterator();
_L17:
        Object obj;
        Variable variable;
        double d;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        obj = (VariableBinder.Variable)iterator.next();
        variable = (Variable)obj;
        if(variable.mBlocked)
            continue; /* Loop/switch isn't completed */
        d = 0.0D;
        if(!cursor.moveToPosition(variable.mRow))
            continue; /* Loop/switch isn't completed */
        int j = cursor.getColumnIndexOrThrow(variable.mColumn);
        if(cursor.isNull(j)) goto _L2; else goto _L1
_L1:
        ((VariableBinder.Variable) (obj)).mType;
        JVM INSTR lookupswitch 5: default 236
    //                   2: 490
    //                   7: 505
    //                   8: 422
    //                   9: 338
    //                   1001: 505;
           goto _L3 _L4 _L5 _L6 _L7 _L5
_L3:
        ((VariableBinder.Variable) (obj)).mType;
        JVM INSTR tableswitch 3 6: default 272
    //                   3 604
    //                   4 617
    //                   5 591
    //                   6 579;
           goto _L8 _L9 _L10 _L11 _L12
_L8:
        StringBuilder stringbuilder = JVM INSTR new #308 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.w("ContentProviderBinder", stringbuilder.append("invalide type").append(((VariableBinder.Variable) (obj)).mTypeStr).toString());
_L15:
        NumberFormatException numberformatexception;
        ((VariableBinder.Variable) (obj)).set(d);
        continue; /* Loop/switch isn't completed */
_L7:
        ArrayList arraylist1;
        try
        {
            ArrayList arraylist = JVM INSTR new #407 <Class ArrayList>;
            arraylist.ArrayList();
            do
                arraylist.add(cursor.getString(j));
            while(cursor.moveToNext());
            ((VariableBinder.Variable) (obj)).set(((Object) (arraylist.toArray())));
        }
        // Misplaced declaration of an exception variable
        catch(NumberFormatException numberformatexception)
        {
            Log.w("ContentProviderBinder", String.format("failed to get value from cursor", new Object[0]));
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            Log.e("ContentProviderBinder", (new StringBuilder()).append("column does not exist: ").append(variable.mColumn).toString());
        }
        catch(Exception exception)
        {
            Log.e("ContentProviderBinder", exception.toString());
        }
        continue; /* Loop/switch isn't completed */
_L6:
        arraylist1 = JVM INSTR new #407 <Class ArrayList>;
        arraylist1.ArrayList();
        do
            arraylist1.add(Double.valueOf(cursor.getDouble(j)));
        while(cursor.moveToNext());
        ((VariableBinder.Variable) (obj)).set(((Object) (arraylist1.toArray())));
        continue; /* Loop/switch isn't completed */
_L4:
        ((VariableBinder.Variable) (obj)).set(cursor.getString(j));
        continue; /* Loop/switch isn't completed */
_L5:
        android.graphics.Bitmap bitmap = null;
        byte abyte0[] = cursor.getBlob(j);
        if(abyte0 == null) goto _L14; else goto _L13
_L13:
        bitmap = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length);
_L14:
        if(((VariableBinder.Variable) (obj)).mType == 7)
        {
            ((VariableBinder.Variable) (obj)).set(bitmap);
            continue; /* Loop/switch isn't completed */
        }
        obj = variable.getImageElement(mRoot);
        if(obj == null)
            continue; /* Loop/switch isn't completed */
        ((ImageScreenElement) (obj)).setBitmap(bitmap);
        continue; /* Loop/switch isn't completed */
_L12:
        d = cursor.getDouble(j);
          goto _L15
_L11:
        d = cursor.getFloat(j);
          goto _L15
_L9:
        d = cursor.getInt(j);
          goto _L15
_L10:
        d = cursor.getLong(j);
          goto _L15
_L2:
        variable.setNull(mRoot);
        if(true) goto _L17; else goto _L16
_L16:
    }

    public void createCountVar()
    {
        if(mCountName == null)
            mCountVar = null;
        else
            mCountVar = new IndexedVariable(mCountName, getContext().mVariables, true);
    }

    public void finish()
    {
        mLastUri = null;
        registerObserver(null, false);
        mHandler.removeCallbacks(mUpdater);
        setBlockedColumns(null);
        super.finish();
    }

    public final String getUriText()
    {
        return mUriFormatter.getText();
    }

    public void onContentChanged()
    {
        Log.i("ContentProviderBinder", "ChangeObserver: content changed.");
        if(mFinished)
            return;
        if(mPaused && mAwareChangeWhilePause ^ true)
            mNeedsRequery = true;
        else
            startQuery();
    }

    protected Variable onLoadVariable(Element element)
    {
        return new Variable(element, getContext().mVariables);
    }

    protected volatile VariableBinder.Variable onLoadVariable(Element element)
    {
        return onLoadVariable(element);
    }

    public void pause()
    {
        super.pause();
        mHandler.removeCallbacks(mUpdater);
    }

    public void refresh()
    {
        super.refresh();
        startQuery();
    }

    public void resume()
    {
        super.resume();
        if(mNeedsRequery)
            startQuery();
        else
            checkUpdate();
    }

    public final void setBlockedColumns(String as[])
    {
        HashSet hashset = null;
        if(as != null)
        {
            HashSet hashset1 = new HashSet();
            int i = as.length;
            int j = 0;
            do
            {
                hashset = hashset1;
                if(j >= i)
                    break;
                hashset1.add(as[j]);
                j++;
            } while(true);
        }
        as = mVariables.iterator();
        while(as.hasNext()) 
        {
            Variable variable = (Variable)(VariableBinder.Variable)as.next();
            boolean flag;
            if(hashset != null)
                flag = hashset.contains(variable.mColumn);
            else
                flag = false;
            variable.mBlocked = flag;
        }
    }

    public void startQuery()
    {
        String s = getUriText();
        if(s == null)
        {
            Log.e("ContentProviderBinder", "start query: uri null");
            return;
        }
        if(!mSystemBootCompleted && s.startsWith("content://sms/"))
        {
            mSystemBootCompleted = "1".equals(SystemProperties.get("sys.boot_completed"));
            if(!mSystemBootCompleted)
                return;
        }
        mNeedsRequery = false;
        mQueryHandler.cancelOperation(100);
        Uri uri = Uri.parse(s);
        if(uri == null)
            return;
        if(mUpdateInterval == -1 && uri.equals(mLastUri) ^ true)
        {
            registerObserver(uri, true);
            mLastUri = uri;
        }
        s = mWhereFormatter.getText();
        mQueryHandler.startQuery(100, null, uri, mColumns, s, mArgs, mOrder);
        mLastQueryTime = System.currentTimeMillis();
        checkUpdate();
    }

    private static final boolean DBG = false;
    private static final String LOG_TAG = "ContentProviderBinder";
    private static final int QUERY_TOKEN = 100;
    public static final String TAG_NAME = "ContentProviderBinder";
    protected String mArgs[];
    private boolean mAwareChangeWhilePause;
    public ChangeObserver mChangeObserver;
    protected String mColumns[];
    protected String mCountName;
    private IndexedVariable mCountVar;
    private Handler mHandler;
    private long mLastQueryTime;
    private Uri mLastUri;
    private List mList;
    private boolean mNeedsRequery;
    protected String mOrder;
    private QueryHandler mQueryHandler;
    private boolean mSystemBootCompleted;
    private int mUpdateInterval;
    private Runnable mUpdater;
    protected TextFormatter mUriFormatter;
    protected TextFormatter mWhereFormatter;
}
