// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.URLSpan;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import libcore.io.IoUtils;

// Referenced classes of package android.content:
//            ClipDescription, Intent, ContentResolver, ContentProvider, 
//            Context

public class ClipData
    implements Parcelable
{
    public static class Item
    {

        private CharSequence coerceToHtmlOrStyledText(Context context, boolean flag)
        {
            if(mUri == null) goto _L2; else goto _L1
_L1:
            String as[] = null;
            String as1[] = context.getContentResolver().getStreamTypes(mUri, "text/*");
            as = as1;
_L9:
            boolean flag1;
            boolean flag3;
            flag1 = false;
            boolean flag2 = false;
            flag3 = false;
            boolean flag4 = false;
            if(as != null)
            {
                int j = 0;
                int k = as.length;
                do
                {
                    flag1 = flag2;
                    flag3 = flag4;
                    if(j >= k)
                        break;
                    String s = as[j];
                    if("text/html".equals(s))
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = flag2;
                        if(s.startsWith("text/"))
                        {
                            flag4 = true;
                            flag1 = flag2;
                        }
                    }
                    j++;
                    flag2 = flag1;
                } while(true);
            }
            if(!flag1 && !flag3) goto _L4; else goto _L3
_L3:
            Object obj;
            Context context1;
            Object obj1;
            InputStreamReader inputstreamreader;
            Object obj2;
            char ac[];
            Context context2;
            Context context3;
            obj1 = null;
            inputstreamreader = null;
            obj2 = null;
            ac = null;
            context2 = ac;
            context1 = ((Context) (obj1));
            context3 = inputstreamreader;
            obj = obj2;
            ContentResolver contentresolver = context.getContentResolver();
            context2 = ac;
            context1 = ((Context) (obj1));
            context3 = inputstreamreader;
            obj = obj2;
            Uri uri = mUri;
            int i;
            if(flag1)
                context = "text/html";
            else
                context = "text/plain";
            context2 = ac;
            context1 = ((Context) (obj1));
            context3 = inputstreamreader;
            obj = obj2;
            context = contentresolver.openTypedAssetFileDescriptor(uri, context, null).createInputStream();
            context2 = context;
            context1 = context;
            context3 = context;
            obj = context;
            inputstreamreader = JVM INSTR new #92  <Class InputStreamReader>;
            context2 = context;
            context1 = context;
            context3 = context;
            obj = context;
            inputstreamreader.InputStreamReader(context, "UTF-8");
            context2 = context;
            context1 = context;
            context3 = context;
            obj = context;
            obj1 = JVM INSTR new #99  <Class StringBuilder>;
            context2 = context;
            context1 = context;
            context3 = context;
            obj = context;
            ((StringBuilder) (obj1)).StringBuilder(128);
            context2 = context;
            context1 = context;
            context3 = context;
            obj = context;
            ac = new char[8192];
_L6:
            context2 = context;
            context1 = context;
            context3 = context;
            obj = context;
            i = inputstreamreader.read(ac);
            if(i <= 0)
                break; /* Loop/switch isn't completed */
            context2 = context;
            context1 = context;
            context3 = context;
            obj = context;
            ((StringBuilder) (obj1)).append(ac, 0, i);
            if(true) goto _L6; else goto _L5
            context;
            obj = context2;
            Log.w("ClipData", "Failure opening stream", context);
            String s1;
            if(context2 != null)
                try
                {
                    context2.close();
                }
                // Misplaced declaration of an exception variable
                catch(Context context) { }
_L4:
            context = mUri.getScheme();
            if("content".equals(context) || "android.resource".equals(context) || "file".equals(context))
                return "";
            break; /* Loop/switch isn't completed */
_L5:
            context2 = context;
            context1 = context;
            context3 = context;
            obj = context;
            s1 = ((StringBuilder) (obj1)).toString();
            if(!flag1)
                break MISSING_BLOCK_LABEL_544;
            if(!flag)
                break MISSING_BLOCK_LABEL_511;
            context2 = context;
            context1 = context;
            context3 = context;
            obj = context;
            obj1 = Html.fromHtml(s1);
            obj = obj1;
            if(obj == null)
                obj = s1;
            if(context != null)
                try
                {
                    context.close();
                }
                // Misplaced declaration of an exception variable
                catch(Context context) { }
            return ((CharSequence) (obj));
            obj;
            if(context != null)
                try
                {
                    context.close();
                }
                // Misplaced declaration of an exception variable
                catch(Context context) { }
            return s1;
            context2 = context;
            context1 = context;
            context3 = context;
            obj = context;
            s1 = s1.toString();
            if(context != null)
                try
                {
                    context.close();
                }
                // Misplaced declaration of an exception variable
                catch(Context context) { }
            return s1;
            if(flag)
            {
                if(context != null)
                    try
                    {
                        context.close();
                    }
                    // Misplaced declaration of an exception variable
                    catch(Context context) { }
                return s1;
            }
            context2 = context;
            context1 = context;
            context3 = context;
            obj = context;
            s1 = Html.escapeHtml(s1);
            if(context != null)
                try
                {
                    context.close();
                }
                // Misplaced declaration of an exception variable
                catch(Context context) { }
            return s1;
            context;
            obj = context1;
            Log.w("ClipData", "Failure loading text", context);
            obj = context1;
            context = Html.escapeHtml(context.toString());
            if(context1 != null)
                try
                {
                    context1.close();
                }
                // Misplaced declaration of an exception variable
                catch(Object obj) { }
            return context;
            context;
            if(context3 != null)
                try
                {
                    context3.close();
                }
                // Misplaced declaration of an exception variable
                catch(Context context) { }
            if(true) goto _L4; else goto _L7
            context;
            if(obj != null)
                try
                {
                    ((FileInputStream) (obj)).close();
                }
                catch(IOException ioexception) { }
            throw context;
_L7:
            if(flag)
                return uriToStyledText(mUri.toString());
            else
                return uriToHtml(mUri.toString());
_L2:
            if(mIntent != null)
            {
                if(flag)
                    return uriToStyledText(mIntent.toUri(1));
                else
                    return uriToHtml(mIntent.toUri(1));
            } else
            {
                return "";
            }
            SecurityException securityexception;
            securityexception;
            if(true) goto _L9; else goto _L8
_L8:
        }

        private String uriToHtml(String s)
        {
            StringBuilder stringbuilder = new StringBuilder(256);
            stringbuilder.append("<a href=\"");
            stringbuilder.append(Html.escapeHtml(s));
            stringbuilder.append("\">");
            stringbuilder.append(Html.escapeHtml(s));
            stringbuilder.append("</a>");
            return stringbuilder.toString();
        }

        private CharSequence uriToStyledText(String s)
        {
            SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
            spannablestringbuilder.append(s);
            spannablestringbuilder.setSpan(new URLSpan(s), 0, spannablestringbuilder.length(), 33);
            return spannablestringbuilder;
        }

        public String coerceToHtmlText(Context context)
        {
            Object obj = null;
            Object obj1 = getHtmlText();
            if(obj1 != null)
                return ((String) (obj1));
            obj1 = getText();
            if(obj1 != null)
                if(obj1 instanceof Spanned)
                    return Html.toHtml((Spanned)obj1);
                else
                    return Html.escapeHtml(((CharSequence) (obj1)));
            obj1 = coerceToHtmlOrStyledText(context, false);
            context = obj;
            if(obj1 != null)
                context = ((CharSequence) (obj1)).toString();
            return context;
        }

        public CharSequence coerceToStyledText(Context context)
        {
            CharSequence charsequence;
            Object obj;
            charsequence = getText();
            if(charsequence instanceof Spanned)
                return charsequence;
            obj = getHtmlText();
            if(obj == null)
                break MISSING_BLOCK_LABEL_35;
            obj = Html.fromHtml(((String) (obj)));
            if(obj != null)
                return ((CharSequence) (obj));
            break MISSING_BLOCK_LABEL_35;
            RuntimeException runtimeexception;
            runtimeexception;
            if(charsequence != null)
                return charsequence;
            else
                return coerceToHtmlOrStyledText(context, true);
        }

        public CharSequence coerceToText(Context context)
        {
            Uri uri;
            CharSequence charsequence = getText();
            if(charsequence != null)
                return charsequence;
            uri = getUri();
            if(uri == null) goto _L2; else goto _L1
_L1:
            Object obj;
            Object obj2;
            InputStreamReader inputstreamreader;
            Object obj3;
            Object obj4;
            IOException ioexception;
            Object obj5;
            Object obj6;
            Object obj7;
            obj2 = context.getContentResolver();
            inputstreamreader = null;
            context = null;
            obj3 = null;
            obj4 = null;
            ioexception = null;
            obj5 = null;
            obj6 = inputstreamreader;
            obj7 = ioexception;
            obj = obj4;
            obj2 = ((ContentResolver) (obj2)).openTypedAssetFileDescriptor(uri, "text/*", null);
            context = ((Context) (obj2));
_L13:
            if(context == null) goto _L4; else goto _L3
_L3:
            obj6 = context;
            obj7 = ioexception;
            obj = obj4;
            obj4 = context.createInputStream();
            obj6 = context;
            obj7 = ioexception;
            obj = obj4;
            obj3 = obj4;
            inputstreamreader = JVM INSTR new #92  <Class InputStreamReader>;
            obj6 = context;
            obj7 = ioexception;
            obj = obj4;
            obj3 = obj4;
            inputstreamreader.InputStreamReader(((java.io.InputStream) (obj4)), "UTF-8");
            obj = JVM INSTR new #99  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder(128);
            obj6 = new char[8192];
_L7:
            int i = inputstreamreader.read(((char []) (obj6)));
            if(i <= 0) goto _L6; else goto _L5
_L5:
            ((StringBuilder) (obj)).append(((char []) (obj6)), 0, i);
              goto _L7
            ioexception;
_L11:
            obj6 = context;
            obj7 = inputstreamreader;
            obj = obj4;
            Log.w("ClipData", "Failure loading text", ioexception);
            obj6 = context;
            obj7 = inputstreamreader;
            obj = obj4;
            obj3 = ioexception.toString();
            IoUtils.closeQuietly(context);
            IoUtils.closeQuietly(((AutoCloseable) (obj4)));
            IoUtils.closeQuietly(inputstreamreader);
            return ((CharSequence) (obj3));
            SecurityException securityexception;
            securityexception;
            obj6 = inputstreamreader;
            obj7 = ioexception;
            obj = obj4;
            Log.w("ClipData", "Failure opening stream", securityexception);
            continue; /* Loop/switch isn't completed */
            context;
            obj4 = obj;
_L9:
            IoUtils.closeQuietly(((AutoCloseable) (obj6)));
            IoUtils.closeQuietly(((AutoCloseable) (obj4)));
            IoUtils.closeQuietly(((AutoCloseable) (obj7)));
            throw context;
_L6:
            obj = ((StringBuilder) (obj)).toString();
            IoUtils.closeQuietly(context);
            IoUtils.closeQuietly(((AutoCloseable) (obj4)));
            IoUtils.closeQuietly(inputstreamreader);
            return ((CharSequence) (obj));
_L4:
            IoUtils.closeQuietly(context);
            IoUtils.closeQuietly(null);
            IoUtils.closeQuietly(null);
            context = uri.getScheme();
            if("content".equals(context) || "android.resource".equals(context) || "file".equals(context))
                return "";
            else
                return uri.toString();
_L2:
            context = getIntent();
            if(context != null)
                return context.toUri(1);
            else
                return "";
            Object obj1;
            obj1;
            obj7 = inputstreamreader;
            obj6 = context;
            context = ((Context) (obj1));
            if(true) goto _L9; else goto _L8
_L8:
            ioexception;
            inputstreamreader = obj5;
            obj4 = obj3;
            if(true) goto _L11; else goto _L10
_L10:
            obj1;
            if(true) goto _L13; else goto _L12
_L12:
        }

        public String getHtmlText()
        {
            return mHtmlText;
        }

        public Intent getIntent()
        {
            return mIntent;
        }

        public CharSequence getText()
        {
            return mText;
        }

        public Uri getUri()
        {
            return mUri;
        }

        public void toShortString(StringBuilder stringbuilder)
        {
            if(mHtmlText != null)
            {
                stringbuilder.append("H:");
                stringbuilder.append(mHtmlText);
            } else
            if(mText != null)
            {
                stringbuilder.append("T:");
                stringbuilder.append(mText);
            } else
            if(mUri != null)
            {
                stringbuilder.append("U:");
                stringbuilder.append(mUri);
            } else
            if(mIntent != null)
            {
                stringbuilder.append("I:");
                mIntent.toShortString(stringbuilder, true, true, true, true);
            } else
            {
                stringbuilder.append("NULL");
            }
        }

        public void toShortSummaryString(StringBuilder stringbuilder)
        {
            if(mHtmlText != null)
                stringbuilder.append("HTML");
            else
            if(mText != null)
                stringbuilder.append("TEXT");
            else
            if(mUri != null)
            {
                stringbuilder.append("U:");
                stringbuilder.append(mUri);
            } else
            if(mIntent != null)
            {
                stringbuilder.append("I:");
                mIntent.toShortString(stringbuilder, true, true, true, true);
            } else
            {
                stringbuilder.append("NULL");
            }
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            stringbuilder.append("ClipData.Item { ");
            toShortString(stringbuilder);
            stringbuilder.append(" }");
            return stringbuilder.toString();
        }

        final String mHtmlText;
        final Intent mIntent;
        final CharSequence mText;
        Uri mUri;

        public Item(Item item)
        {
            mText = item.mText;
            mHtmlText = item.mHtmlText;
            mIntent = item.mIntent;
            mUri = item.mUri;
        }

        public Item(Intent intent)
        {
            mText = null;
            mHtmlText = null;
            mIntent = intent;
            mUri = null;
        }

        public Item(Uri uri)
        {
            mText = null;
            mHtmlText = null;
            mIntent = null;
            mUri = uri;
        }

        public Item(CharSequence charsequence)
        {
            mText = charsequence;
            mHtmlText = null;
            mIntent = null;
            mUri = null;
        }

        public Item(CharSequence charsequence, Intent intent, Uri uri)
        {
            mText = charsequence;
            mHtmlText = null;
            mIntent = intent;
            mUri = uri;
        }

        public Item(CharSequence charsequence, String s)
        {
            mText = charsequence;
            mHtmlText = s;
            mIntent = null;
            mUri = null;
        }

        public Item(CharSequence charsequence, String s, Intent intent, Uri uri)
        {
            if(s != null && charsequence == null)
            {
                throw new IllegalArgumentException("Plain text must be supplied if HTML text is supplied");
            } else
            {
                mText = charsequence;
                mHtmlText = s;
                mIntent = intent;
                mUri = uri;
                return;
            }
        }
    }


    public ClipData(ClipData clipdata)
    {
        mClipDescription = clipdata.mClipDescription;
        mIcon = clipdata.mIcon;
        mItems = new ArrayList(clipdata.mItems);
    }

    public ClipData(ClipDescription clipdescription, Item item)
    {
        mClipDescription = clipdescription;
        if(item == null)
        {
            throw new NullPointerException("item is null");
        } else
        {
            mIcon = null;
            mItems = new ArrayList();
            mItems.add(item);
            return;
        }
    }

    public ClipData(ClipDescription clipdescription, ArrayList arraylist)
    {
        mClipDescription = clipdescription;
        if(arraylist == null)
        {
            throw new NullPointerException("item is null");
        } else
        {
            mIcon = null;
            mItems = arraylist;
            return;
        }
    }

    ClipData(Parcel parcel)
    {
        mClipDescription = new ClipDescription(parcel);
        int i;
        int j;
        if(parcel.readInt() != 0)
            mIcon = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel);
        else
            mIcon = null;
        mItems = new ArrayList();
        i = parcel.readInt();
        j = 0;
        while(j < i) 
        {
            CharSequence charsequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            String s = parcel.readString();
            Intent intent;
            Uri uri;
            if(parcel.readInt() != 0)
                intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
            else
                intent = null;
            if(parcel.readInt() != 0)
                uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
            else
                uri = null;
            mItems.add(new Item(charsequence, s, intent, uri));
            j++;
        }
    }

    public ClipData(CharSequence charsequence, String as[], Item item)
    {
        mClipDescription = new ClipDescription(charsequence, as);
        if(item == null)
        {
            throw new NullPointerException("item is null");
        } else
        {
            mIcon = null;
            mItems = new ArrayList();
            mItems.add(item);
            return;
        }
    }

    private static String[] getMimeTypes(ContentResolver contentresolver, Uri uri)
    {
        Object obj = null;
        if(!"content".equals(uri.getScheme())) goto _L2; else goto _L1
_L1:
        String s;
        s = contentresolver.getType(uri);
        contentresolver = contentresolver.getStreamTypes(uri, "*/*");
        obj = contentresolver;
        if(s == null) goto _L2; else goto _L3
_L3:
        if(contentresolver != null) goto _L5; else goto _L4
_L4:
        obj = new String[1];
        obj[0] = s;
_L2:
        contentresolver = ((ContentResolver) (obj));
        if(obj == null)
            contentresolver = MIMETYPES_TEXT_URILIST;
        return contentresolver;
_L5:
        obj = contentresolver;
        if(!ArrayUtils.contains(contentresolver, s))
        {
            obj = new String[contentresolver.length + 1];
            obj[0] = s;
            System.arraycopy(contentresolver, 0, obj, 1, contentresolver.length);
        }
        if(true) goto _L2; else goto _L6
_L6:
    }

    public static ClipData newHtmlText(CharSequence charsequence, CharSequence charsequence1, String s)
    {
        charsequence1 = new Item(charsequence1, s);
        return new ClipData(charsequence, MIMETYPES_TEXT_HTML, charsequence1);
    }

    public static ClipData newIntent(CharSequence charsequence, Intent intent)
    {
        intent = new Item(intent);
        return new ClipData(charsequence, MIMETYPES_TEXT_INTENT, intent);
    }

    public static ClipData newPlainText(CharSequence charsequence, CharSequence charsequence1)
    {
        charsequence1 = new Item(charsequence1);
        return new ClipData(charsequence, MIMETYPES_TEXT_PLAIN, charsequence1);
    }

    public static ClipData newRawUri(CharSequence charsequence, Uri uri)
    {
        uri = new Item(uri);
        return new ClipData(charsequence, MIMETYPES_TEXT_URILIST, uri);
    }

    public static ClipData newUri(ContentResolver contentresolver, CharSequence charsequence, Uri uri)
    {
        Item item = new Item(uri);
        return new ClipData(charsequence, getMimeTypes(contentresolver, uri), item);
    }

    public void addItem(Item item)
    {
        if(item == null)
        {
            throw new NullPointerException("item is null");
        } else
        {
            mItems.add(item);
            return;
        }
    }

    public void addItem(Item item, ContentResolver contentresolver)
    {
        addItem(contentresolver, item);
    }

    public void addItem(ContentResolver contentresolver, Item item)
    {
        addItem(item);
        if(item.getHtmlText() == null) goto _L2; else goto _L1
_L1:
        mClipDescription.addMimeTypes(MIMETYPES_TEXT_HTML);
_L4:
        if(item.getIntent() != null)
            mClipDescription.addMimeTypes(MIMETYPES_TEXT_INTENT);
        if(item.getUri() != null)
            mClipDescription.addMimeTypes(getMimeTypes(contentresolver, item.getUri()));
        return;
_L2:
        if(item.getText() != null)
            mClipDescription.addMimeTypes(MIMETYPES_TEXT_PLAIN);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void collectUris(List list)
    {
        for(int i = 0; i < mItems.size(); i++)
        {
            Object obj = getItemAt(i);
            if(((Item) (obj)).getUri() != null)
                list.add(((Item) (obj)).getUri());
            obj = ((Item) (obj)).getIntent();
            if(obj == null)
                continue;
            if(((Intent) (obj)).getData() != null)
                list.add(((Intent) (obj)).getData());
            if(((Intent) (obj)).getClipData() != null)
                ((Intent) (obj)).getClipData().collectUris(list);
        }

    }

    public int describeContents()
    {
        return 0;
    }

    public void fixUris(int i)
    {
        int j = mItems.size();
        for(int k = 0; k < j; k++)
        {
            Item item = (Item)mItems.get(k);
            if(item.mIntent != null)
                item.mIntent.fixUris(i);
            if(item.mUri != null)
                item.mUri = ContentProvider.maybeAddUserId(item.mUri, i);
        }

    }

    public void fixUrisLight(int i)
    {
        int j = mItems.size();
        for(int k = 0; k < j; k++)
        {
            Item item = (Item)mItems.get(k);
            if(item.mIntent != null)
            {
                Uri uri = item.mIntent.getData();
                if(uri != null)
                    item.mIntent.setData(ContentProvider.maybeAddUserId(uri, i));
            }
            if(item.mUri != null)
                item.mUri = ContentProvider.maybeAddUserId(item.mUri, i);
        }

    }

    public ClipDescription getDescription()
    {
        return mClipDescription;
    }

    public Bitmap getIcon()
    {
        return mIcon;
    }

    public Item getItemAt(int i)
    {
        return (Item)mItems.get(i);
    }

    public int getItemCount()
    {
        return mItems.size();
    }

    public void prepareToEnterProcess()
    {
        int i = mItems.size();
        for(int j = 0; j < i; j++)
        {
            Item item = (Item)mItems.get(j);
            if(item.mIntent != null)
                item.mIntent.prepareToEnterProcess();
        }

    }

    public void prepareToLeaveProcess(boolean flag)
    {
        prepareToLeaveProcess(flag, 1);
    }

    public void prepareToLeaveProcess(boolean flag, int i)
    {
        int j = mItems.size();
        for(int k = 0; k < j; k++)
        {
            Item item = (Item)mItems.get(k);
            if(item.mIntent != null)
                item.mIntent.prepareToLeaveProcess(flag);
            if(item.mUri == null || !flag)
                continue;
            if(StrictMode.vmFileUriExposureEnabled())
                item.mUri.checkFileUriExposed("ClipData.Item.getUri()");
            if(StrictMode.vmContentUriWithoutPermissionEnabled())
                item.mUri.checkContentUriWithoutPermission("ClipData.Item.getUri()", i);
        }

    }

    public void setItemAt(int i, Item item)
    {
        mItems.set(i, item);
    }

    public void toShortString(StringBuilder stringbuilder)
    {
        int i;
        boolean flag;
        if(mClipDescription != null)
            i = mClipDescription.toShortString(stringbuilder) ^ true;
        else
            i = 1;
        flag = i;
        if(mIcon != null)
        {
            if(i == 0)
                stringbuilder.append(' ');
            flag = false;
            stringbuilder.append("I:");
            stringbuilder.append(mIcon.getWidth());
            stringbuilder.append('x');
            stringbuilder.append(mIcon.getHeight());
        }
        for(i = 0; i < mItems.size(); i++)
        {
            if(!flag)
                stringbuilder.append(' ');
            flag = false;
            stringbuilder.append('{');
            ((Item)mItems.get(i)).toShortString(stringbuilder);
            stringbuilder.append('}');
        }

    }

    public void toShortStringShortItems(StringBuilder stringbuilder, boolean flag)
    {
        if(mItems.size() > 0)
        {
            if(!flag)
                stringbuilder.append(' ');
            ((Item)mItems.get(0)).toShortString(stringbuilder);
            if(mItems.size() > 1)
                stringbuilder.append(" ...");
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("ClipData { ");
        toShortString(stringbuilder);
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        mClipDescription.writeToParcel(parcel, i);
        int j;
        int k;
        if(mIcon != null)
        {
            parcel.writeInt(1);
            mIcon.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        j = mItems.size();
        parcel.writeInt(j);
        k = 0;
        while(k < j) 
        {
            Item item = (Item)mItems.get(k);
            TextUtils.writeToParcel(item.mText, parcel, i);
            parcel.writeString(item.mHtmlText);
            if(item.mIntent != null)
            {
                parcel.writeInt(1);
                item.mIntent.writeToParcel(parcel, i);
            } else
            {
                parcel.writeInt(0);
            }
            if(item.mUri != null)
            {
                parcel.writeInt(1);
                item.mUri.writeToParcel(parcel, i);
            } else
            {
                parcel.writeInt(0);
            }
            k++;
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ClipData createFromParcel(Parcel parcel)
        {
            return new ClipData(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ClipData[] newArray(int i)
        {
            return new ClipData[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final String MIMETYPES_TEXT_HTML[] = {
        "text/html"
    };
    static final String MIMETYPES_TEXT_INTENT[] = {
        "text/vnd.android.intent"
    };
    static final String MIMETYPES_TEXT_PLAIN[] = {
        "text/plain"
    };
    static final String MIMETYPES_TEXT_URILIST[] = {
        "text/uri-list"
    };
    final ClipDescription mClipDescription;
    final Bitmap mIcon;
    final ArrayList mItems;

}
