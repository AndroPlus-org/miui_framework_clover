// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.*;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import java.io.*;
import java.util.WeakHashMap;

// Referenced classes of package android.widget:
//            ResourceCursorAdapter, SearchView, Filter, ImageView, 
//            TextView

class SuggestionsAdapter extends ResourceCursorAdapter
    implements android.view.View.OnClickListener
{
    private static final class ChildViewCache
    {

        public final ImageView mIcon1;
        public final ImageView mIcon2;
        public final ImageView mIconRefine;
        public final TextView mText1;
        public final TextView mText2;

        public ChildViewCache(View view)
        {
            mText1 = (TextView)view.findViewById(0x1020014);
            mText2 = (TextView)view.findViewById(0x1020015);
            mIcon1 = (ImageView)view.findViewById(0x1020007);
            mIcon2 = (ImageView)view.findViewById(0x1020008);
            mIconRefine = (ImageView)view.findViewById(0x102022e);
        }
    }


    public SuggestionsAdapter(Context context, SearchView searchview, SearchableInfo searchableinfo, WeakHashMap weakhashmap)
    {
        super(context, searchview.getSuggestionRowLayout(), null, true);
        mClosed = false;
        mQueryRefinement = 1;
        mText1Col = -1;
        mText2Col = -1;
        mText2UrlCol = -1;
        mIconName1Col = -1;
        mIconName2Col = -1;
        mFlagsCol = -1;
        mSearchManager = (SearchManager)mContext.getSystemService("search");
        mSearchView = searchview;
        mSearchable = searchableinfo;
        mCommitIconResId = searchview.getSuggestionCommitIconResId();
        context = mSearchable.getActivityContext(mContext);
        mProviderContext = mSearchable.getProviderContext(mContext, context);
        mOutsideDrawablesCache = weakhashmap;
        getFilter().setDelayer(new Filter.Delayer() {

            public long getPostingDelay(CharSequence charsequence)
            {
                if(charsequence == null)
                    return 0L;
                long l;
                if(charsequence.length() < mPreviousLength)
                    l = 500L;
                else
                    l = 0L;
                mPreviousLength = charsequence.length();
                return l;
            }

            private int mPreviousLength;
            final SuggestionsAdapter this$0;

            
            {
                this$0 = SuggestionsAdapter.this;
                super();
                mPreviousLength = 0;
            }
        }
);
    }

    private Drawable checkIconCache(String s)
    {
        s = (android.graphics.drawable.Drawable.ConstantState)mOutsideDrawablesCache.get(s);
        if(s == null)
            return null;
        else
            return s.newDrawable();
    }

    private CharSequence formatUrl(Context context, CharSequence charsequence)
    {
        if(mUrlColor == null)
        {
            TypedValue typedvalue = new TypedValue();
            context.getTheme().resolveAttribute(0x11100c6, typedvalue, true);
            mUrlColor = context.getColorStateList(typedvalue.resourceId);
        }
        context = new SpannableString(charsequence);
        context.setSpan(new TextAppearanceSpan(null, 0, 0, mUrlColor, null), 0, charsequence.length(), 33);
        return context;
    }

    private Drawable getActivityIcon(ComponentName componentname)
    {
        PackageManager packagemanager = mContext.getPackageManager();
        Object obj;
        int i;
        try
        {
            obj = packagemanager.getActivityInfo(componentname, 128);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            Log.w("SuggestionsAdapter", componentname.toString());
            return null;
        }
        i = ((ActivityInfo) (obj)).getIconResource();
        if(i == 0)
            return null;
        obj = packagemanager.getDrawable(componentname.getPackageName(), i, ((ActivityInfo) (obj)).applicationInfo);
        if(obj == null)
        {
            Log.w("SuggestionsAdapter", (new StringBuilder()).append("Invalid icon resource ").append(i).append(" for ").append(componentname.flattenToShortString()).toString());
            return null;
        } else
        {
            return ((Drawable) (obj));
        }
    }

    private Drawable getActivityIconWithCache(ComponentName componentname)
    {
        Drawable drawable = null;
        String s = componentname.flattenToShortString();
        if(mOutsideDrawablesCache.containsKey(s))
        {
            componentname = (android.graphics.drawable.Drawable.ConstantState)mOutsideDrawablesCache.get(s);
            if(componentname == null)
                componentname = drawable;
            else
                componentname = componentname.newDrawable(mProviderContext.getResources());
            return componentname;
        }
        drawable = getActivityIcon(componentname);
        if(drawable == null)
            componentname = null;
        else
            componentname = drawable.getConstantState();
        mOutsideDrawablesCache.put(s, componentname);
        return drawable;
    }

    public static String getColumnString(Cursor cursor, String s)
    {
        return getStringOrNull(cursor, cursor.getColumnIndex(s));
    }

    private Drawable getDefaultIcon1(Cursor cursor)
    {
        cursor = getActivityIconWithCache(mSearchable.getSearchActivity());
        if(cursor != null)
            return cursor;
        else
            return mContext.getPackageManager().getDefaultActivityIcon();
    }

    private Drawable getDrawable(Uri uri)
    {
        Object obj;
        if(!"android.resource".equals(uri.getScheme()))
            break MISSING_BLOCK_LABEL_121;
        obj = mProviderContext.getContentResolver().getResourceId(uri);
        try
        {
            obj = ((android.content.ContentResolver.OpenResourceIdResult) (obj)).r.getDrawable(((android.content.ContentResolver.OpenResourceIdResult) (obj)).id, mProviderContext.getTheme());
        }
        catch(android.content.res.Resources.NotFoundException notfoundexception)
        {
            try
            {
                FileNotFoundException filenotfoundexception1 = JVM INSTR new #305 <Class FileNotFoundException>;
                notfoundexception = JVM INSTR new #239 <Class StringBuilder>;
                notfoundexception.StringBuilder();
                filenotfoundexception1.FileNotFoundException(notfoundexception.append("Resource does not exist: ").append(uri).toString());
                throw filenotfoundexception1;
            }
            catch(FileNotFoundException filenotfoundexception)
            {
                Log.w("SuggestionsAdapter", (new StringBuilder()).append("Icon not found: ").append(uri).append(", ").append(filenotfoundexception.getMessage()).toString());
            }
            return null;
        }
        return ((Drawable) (obj));
        Object obj1 = mProviderContext.getContentResolver().openInputStream(uri);
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_169;
        obj1 = JVM INSTR new #305 <Class FileNotFoundException>;
        filenotfoundexception = JVM INSTR new #239 <Class StringBuilder>;
        filenotfoundexception.StringBuilder();
        ((FileNotFoundException) (obj1)).FileNotFoundException(filenotfoundexception.append("Failed to open ").append(uri).toString());
        throw obj1;
        filenotfoundexception = Drawable.createFromStream(((InputStream) (obj1)), null);
        ((InputStream) (obj1)).close();
_L2:
        return filenotfoundexception;
        IOException ioexception1;
        ioexception1;
        obj1 = JVM INSTR new #239 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.e("SuggestionsAdapter", ((StringBuilder) (obj1)).append("Error closing icon stream for ").append(uri).toString(), ioexception1);
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        ((InputStream) (obj1)).close();
_L3:
        throw exception;
        IOException ioexception;
        ioexception;
        StringBuilder stringbuilder = JVM INSTR new #239 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("SuggestionsAdapter", stringbuilder.append("Error closing icon stream for ").append(uri).toString(), ioexception);
          goto _L3
    }

    private Drawable getDrawableFromResourceValue(String s)
    {
        while(s == null || s.length() == 0 || "0".equals(s)) 
            return null;
        int i;
        Object obj;
        Drawable drawable2;
        try
        {
            i = Integer.parseInt(s);
            obj = JVM INSTR new #239 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            obj = ((StringBuilder) (obj)).append("android.resource://").append(mProviderContext.getPackageName()).append("/").append(i).toString();
            drawable2 = checkIconCache(((String) (obj)));
        }
        catch(NumberFormatException numberformatexception)
        {
            Drawable drawable = checkIconCache(s);
            if(drawable != null)
            {
                return drawable;
            } else
            {
                Drawable drawable1 = getDrawable(Uri.parse(s));
                storeInIconCache(s, drawable1);
                return drawable1;
            }
        }
        catch(android.content.res.Resources.NotFoundException notfoundexception)
        {
            Log.w("SuggestionsAdapter", (new StringBuilder()).append("Icon resource not found: ").append(s).toString());
            return null;
        }
        if(drawable2 != null)
            return drawable2;
        drawable2 = mProviderContext.getDrawable(i);
        storeInIconCache(((String) (obj)), drawable2);
        return drawable2;
    }

    private Drawable getIcon1(Cursor cursor)
    {
        if(mIconName1Col == -1)
            return null;
        Drawable drawable = getDrawableFromResourceValue(cursor.getString(mIconName1Col));
        if(drawable != null)
            return drawable;
        else
            return getDefaultIcon1(cursor);
    }

    private Drawable getIcon2(Cursor cursor)
    {
        if(mIconName2Col == -1)
            return null;
        else
            return getDrawableFromResourceValue(cursor.getString(mIconName2Col));
    }

    private static String getStringOrNull(Cursor cursor, int i)
    {
        if(i == -1)
            return null;
        try
        {
            cursor = cursor.getString(i);
        }
        // Misplaced declaration of an exception variable
        catch(Cursor cursor)
        {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", cursor);
            return null;
        }
        return cursor;
    }

    private void setViewDrawable(ImageView imageview, Drawable drawable, int i)
    {
        imageview.setImageDrawable(drawable);
        if(drawable == null)
        {
            imageview.setVisibility(i);
        } else
        {
            imageview.setVisibility(0);
            drawable.setVisible(false, false);
            drawable.setVisible(true, false);
        }
    }

    private void setViewText(TextView textview, CharSequence charsequence)
    {
        textview.setText(charsequence);
        if(TextUtils.isEmpty(charsequence))
            textview.setVisibility(8);
        else
            textview.setVisibility(0);
    }

    private void storeInIconCache(String s, Drawable drawable)
    {
        if(drawable != null)
            mOutsideDrawablesCache.put(s, drawable.getConstantState());
    }

    private void updateSpinnerState(Cursor cursor)
    {
        if(cursor != null)
            cursor = cursor.getExtras();
        else
            cursor = null;
        if(cursor != null && cursor.getBoolean("in_progress"))
            return;
        else
            return;
    }

    public void bindView(View view, Context context, Cursor cursor)
    {
        ChildViewCache childviewcache;
        int i;
        childviewcache = (ChildViewCache)view.getTag();
        i = 0;
        if(mFlagsCol != -1)
            i = cursor.getInt(mFlagsCol);
        if(childviewcache.mText1 != null)
        {
            view = getStringOrNull(cursor, mText1Col);
            setViewText(childviewcache.mText1, view);
        }
        if(childviewcache.mText2 == null) goto _L2; else goto _L1
_L1:
        view = getStringOrNull(cursor, mText2UrlCol);
        if(view != null)
            view = formatUrl(context, view);
        else
            view = getStringOrNull(cursor, mText2Col);
        if(!TextUtils.isEmpty(view)) goto _L4; else goto _L3
_L3:
        if(childviewcache.mText1 != null)
        {
            childviewcache.mText1.setSingleLine(false);
            childviewcache.mText1.setMaxLines(2);
        }
_L6:
        setViewText(childviewcache.mText2, view);
_L2:
        if(childviewcache.mIcon1 != null)
            setViewDrawable(childviewcache.mIcon1, getIcon1(cursor), 4);
        if(childviewcache.mIcon2 != null)
            setViewDrawable(childviewcache.mIcon2, getIcon2(cursor), 8);
        if(mQueryRefinement == 2 || mQueryRefinement == 1 && (i & 1) != 0)
        {
            childviewcache.mIconRefine.setVisibility(0);
            childviewcache.mIconRefine.setTag(childviewcache.mText1.getText());
            childviewcache.mIconRefine.setOnClickListener(this);
        } else
        {
            childviewcache.mIconRefine.setVisibility(8);
        }
        return;
_L4:
        if(childviewcache.mText1 != null)
        {
            childviewcache.mText1.setSingleLine(true);
            childviewcache.mText1.setMaxLines(1);
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void changeCursor(Cursor cursor)
    {
        if(mClosed)
        {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if(cursor != null)
                cursor.close();
            return;
        }
        super.changeCursor(cursor);
        if(cursor == null)
            break MISSING_BLOCK_LABEL_114;
        mText1Col = cursor.getColumnIndex("suggest_text_1");
        mText2Col = cursor.getColumnIndex("suggest_text_2");
        mText2UrlCol = cursor.getColumnIndex("suggest_text_2_url");
        mIconName1Col = cursor.getColumnIndex("suggest_icon_1");
        mIconName2Col = cursor.getColumnIndex("suggest_icon_2");
        mFlagsCol = cursor.getColumnIndex("suggest_flags");
_L1:
        return;
        cursor;
        Log.e("SuggestionsAdapter", "error changing cursor and caching columns", cursor);
          goto _L1
    }

    public void close()
    {
        changeCursor(null);
        mClosed = true;
    }

    public CharSequence convertToString(Cursor cursor)
    {
        if(cursor == null)
            return null;
        String s = getColumnString(cursor, "suggest_intent_query");
        if(s != null)
            return s;
        if(mSearchable.shouldRewriteQueryFromData())
        {
            String s1 = getColumnString(cursor, "suggest_intent_data");
            if(s1 != null)
                return s1;
        }
        if(mSearchable.shouldRewriteQueryFromText())
        {
            cursor = getColumnString(cursor, "suggest_text_1");
            if(cursor != null)
                return cursor;
        }
        return null;
    }

    public View getDropDownView(int i, View view, ViewGroup viewgroup)
    {
        try
        {
            view = super.getDropDownView(i, view, viewgroup);
        }
        catch(RuntimeException runtimeexception)
        {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", runtimeexception);
            if(mDropDownContext == null)
                view = mContext;
            else
                view = mDropDownContext;
            view = newDropDownView(view, mCursor, viewgroup);
            if(view != null)
                ((ChildViewCache)view.getTag()).mText1.setText(runtimeexception.toString());
            return view;
        }
        return view;
    }

    public int getQueryRefinement()
    {
        return mQueryRefinement;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        try
        {
            view = super.getView(i, view, viewgroup);
        }
        // Misplaced declaration of an exception variable
        catch(View view)
        {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", view);
            viewgroup = newView(mContext, mCursor, viewgroup);
            if(viewgroup != null)
                ((ChildViewCache)viewgroup.getTag()).mText1.setText(view.toString());
            return viewgroup;
        }
        return view;
    }

    public boolean hasStableIds()
    {
        return false;
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewgroup)
    {
        context = super.newView(context, cursor, viewgroup);
        context.setTag(new ChildViewCache(context));
        ((ImageView)context.findViewById(0x102022e)).setImageResource(mCommitIconResId);
        return context;
    }

    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
        updateSpinnerState(getCursor());
    }

    public void notifyDataSetInvalidated()
    {
        super.notifyDataSetInvalidated();
        updateSpinnerState(getCursor());
    }

    public void onClick(View view)
    {
        view = ((View) (view.getTag()));
        if(view instanceof CharSequence)
            mSearchView.onQueryRefine((CharSequence)view);
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charsequence)
    {
        if(charsequence == null)
            charsequence = "";
        else
            charsequence = charsequence.toString();
        if(mSearchView.getVisibility() != 0 || mSearchView.getWindowVisibility() != 0)
            return null;
        charsequence = mSearchManager.getSuggestions(mSearchable, charsequence, 50);
        if(charsequence == null)
            break MISSING_BLOCK_LABEL_79;
        charsequence.getCount();
        return charsequence;
        charsequence;
        Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", charsequence);
        return null;
    }

    public void setQueryRefinement(int i)
    {
        mQueryRefinement = i;
    }

    private static final boolean DBG = false;
    private static final long DELETE_KEY_POST_DELAY = 500L;
    static final int INVALID_INDEX = -1;
    private static final String LOG_TAG = "SuggestionsAdapter";
    private static final int QUERY_LIMIT = 50;
    static final int REFINE_ALL = 2;
    static final int REFINE_BY_ENTRY = 1;
    static final int REFINE_NONE = 0;
    private boolean mClosed;
    private final int mCommitIconResId;
    private int mFlagsCol;
    private int mIconName1Col;
    private int mIconName2Col;
    private final WeakHashMap mOutsideDrawablesCache;
    private final Context mProviderContext;
    private int mQueryRefinement;
    private final SearchManager mSearchManager;
    private final SearchView mSearchView;
    private final SearchableInfo mSearchable;
    private int mText1Col;
    private int mText2Col;
    private int mText2UrlCol;
    private ColorStateList mUrlColor;
}
