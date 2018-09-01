// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.*;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

// Referenced classes of package android.widget:
//            ImageView

public class QuickContactBadge extends ImageView
    implements android.view.View.OnClickListener
{
    private class QueryHandler extends AsyncQueryHandler
    {

        protected void onQueryComplete(int i, Object obj, Cursor cursor)
        {
            Object obj1;
            Uri uri;
            Uri uri1;
            Object obj2;
            boolean flag;
            boolean flag1;
            Bundle bundle;
            obj1 = null;
            uri = null;
            uri1 = null;
            obj2 = null;
            flag = false;
            flag1 = false;
            boolean flag2 = false;
            if(obj != null)
                bundle = (Bundle)obj;
            else
                bundle = new Bundle();
            obj = uri;
            i;
            JVM INSTR tableswitch 0 3: default 64
        //                       0 256
        //                       1 177
        //                       2 238
        //                       3 160;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            i = ((flag2) ? 1 : 0);
            uri = obj1;
_L7:
            if(cursor != null)
                cursor.close();
            QuickContactBadge._2D_set0(QuickContactBadge.this, uri);
            QuickContactBadge._2D_wrap0(QuickContactBadge.this);
            if(i != 0 && QuickContactBadge._2D_get0(QuickContactBadge.this) != null)
                android.provider.ContactsContract.QuickContact.showQuickContact(getContext(), QuickContactBadge.this, QuickContactBadge._2D_get0(QuickContactBadge.this), mExcludeMimes, QuickContactBadge._2D_get1(QuickContactBadge.this));
            else
            if(obj2 != null)
            {
                obj = new Intent("com.android.contacts.action.SHOW_OR_CREATE_CONTACT", ((Uri) (obj2)));
                if(bundle != null)
                {
                    bundle.remove("uri_content");
                    ((Intent) (obj)).putExtras(bundle);
                }
                getContext().startActivity(((Intent) (obj)));
            }
            return;
_L5:
            flag = true;
            obj = Uri.fromParts("tel", bundle.getString("uri_content"), null);
_L3:
            obj2 = obj;
            uri = obj1;
            i = ((flag) ? 1 : 0);
            if(cursor == null) goto _L7; else goto _L6
_L6:
            obj2 = obj;
            uri = obj1;
            i = ((flag) ? 1 : 0);
            if(!cursor.moveToFirst()) goto _L7; else goto _L8
_L8:
            uri = android.provider.ContactsContract.Contacts.getLookupUri(cursor.getLong(0), cursor.getString(1));
            obj2 = obj;
            i = ((flag) ? 1 : 0);
              goto _L7
_L4:
            flag1 = true;
            uri1 = Uri.fromParts("mailto", bundle.getString("uri_content"), null);
_L2:
            obj2 = uri1;
            uri = obj1;
            i = ((flag1) ? 1 : 0);
            if(cursor == null) goto _L7; else goto _L9
_L9:
            obj2 = uri1;
            uri = obj1;
            i = ((flag1) ? 1 : 0);
            if(!cursor.moveToFirst()) goto _L7; else goto _L10
_L10:
            uri = android.provider.ContactsContract.Contacts.getLookupUri(cursor.getLong(0), cursor.getString(1));
            obj2 = uri1;
            i = ((flag1) ? 1 : 0);
              goto _L7
            obj;
            if(cursor != null)
                cursor.close();
            throw obj;
        }

        final QuickContactBadge this$0;

        public QueryHandler(ContentResolver contentresolver)
        {
            this$0 = QuickContactBadge.this;
            super(contentresolver);
        }
    }


    static Uri _2D_get0(QuickContactBadge quickcontactbadge)
    {
        return quickcontactbadge.mContactUri;
    }

    static String _2D_get1(QuickContactBadge quickcontactbadge)
    {
        return quickcontactbadge.mPrioritizedMimeType;
    }

    static Uri _2D_set0(QuickContactBadge quickcontactbadge, Uri uri)
    {
        quickcontactbadge.mContactUri = uri;
        return uri;
    }

    static void _2D_wrap0(QuickContactBadge quickcontactbadge)
    {
        quickcontactbadge.onContactUriChanged();
    }

    public QuickContactBadge(Context context)
    {
        this(context, null);
    }

    public QuickContactBadge(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public QuickContactBadge(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public QuickContactBadge(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mExtras = null;
        mExcludeMimes = null;
        context = mContext.obtainStyledAttributes(com.android.internal.R.styleable.Theme);
        mOverlay = context.getDrawable(316);
        context.recycle();
        setOnClickListener(this);
    }

    private boolean isAssigned()
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(mContactUri != null) goto _L2; else goto _L1
_L1:
        if(mContactEmail == null) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(mContactPhone == null)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void onContactUriChanged()
    {
        setEnabled(isAssigned());
    }

    public void assignContactFromEmail(String s, boolean flag)
    {
        assignContactFromEmail(s, flag, null);
    }

    public void assignContactFromEmail(String s, boolean flag, Bundle bundle)
    {
        mContactEmail = s;
        mExtras = bundle;
        if(!flag && mQueryHandler != null)
        {
            mQueryHandler.startQuery(0, null, Uri.withAppendedPath(android.provider.ContactsContract.CommonDataKinds.Email.CONTENT_LOOKUP_URI, Uri.encode(mContactEmail)), EMAIL_LOOKUP_PROJECTION, null, null, null);
        } else
        {
            mContactUri = null;
            onContactUriChanged();
        }
    }

    public void assignContactFromPhone(String s, boolean flag)
    {
        assignContactFromPhone(s, flag, new Bundle());
    }

    public void assignContactFromPhone(String s, boolean flag, Bundle bundle)
    {
        mContactPhone = s;
        mExtras = bundle;
        if(!flag && mQueryHandler != null)
        {
            mQueryHandler.startQuery(1, null, Uri.withAppendedPath(android.provider.ContactsContract.PhoneLookup.CONTENT_FILTER_URI, mContactPhone), PHONE_LOOKUP_PROJECTION, null, null, null);
        } else
        {
            mContactUri = null;
            onContactUriChanged();
        }
    }

    public void assignContactUri(Uri uri)
    {
        mContactUri = uri;
        mContactEmail = null;
        mContactPhone = null;
        onContactUriChanged();
    }

    public void drawableHotspotChanged(float f, float f1)
    {
        super.drawableHotspotChanged(f, f1);
        if(mOverlay != null)
            mOverlay.setHotspot(f, f1);
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        Drawable drawable = mOverlay;
        if(drawable != null && drawable.isStateful() && drawable.setState(getDrawableState()))
            invalidateDrawable(drawable);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/QuickContactBadge.getName();
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(!isInEditMode())
            mQueryHandler = new QueryHandler(mContext.getContentResolver());
    }

    public void onClick(View view)
    {
        if(mExtras == null)
            view = new Bundle();
        else
            view = mExtras;
        if(mContactUri != null)
            android.provider.ContactsContract.QuickContact.showQuickContact(getContext(), this, mContactUri, mExcludeMimes, mPrioritizedMimeType);
        else
        if(mContactEmail != null && mQueryHandler != null)
        {
            view.putString("uri_content", mContactEmail);
            mQueryHandler.startQuery(2, view, Uri.withAppendedPath(android.provider.ContactsContract.CommonDataKinds.Email.CONTENT_LOOKUP_URI, Uri.encode(mContactEmail)), EMAIL_LOOKUP_PROJECTION, null, null, null);
        } else
        if(mContactPhone != null && mQueryHandler != null)
        {
            view.putString("uri_content", mContactPhone);
            mQueryHandler.startQuery(3, view, Uri.withAppendedPath(android.provider.ContactsContract.PhoneLookup.CONTENT_FILTER_URI, mContactPhone), PHONE_LOOKUP_PROJECTION, null, null, null);
        } else
        {
            return;
        }
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(!isEnabled())
            return;
        while(mOverlay == null || mOverlay.getIntrinsicWidth() == 0 || mOverlay.getIntrinsicHeight() == 0) 
            return;
        mOverlay.setBounds(0, 0, getWidth(), getHeight());
        if(mPaddingTop == 0 && mPaddingLeft == 0)
        {
            mOverlay.draw(canvas);
        } else
        {
            int i = canvas.getSaveCount();
            canvas.save();
            canvas.translate(mPaddingLeft, mPaddingTop);
            mOverlay.draw(canvas);
            canvas.restoreToCount(i);
        }
    }

    public void setExcludeMimes(String as[])
    {
        mExcludeMimes = as;
    }

    public void setImageToDefault()
    {
        if(mDefaultAvatar == null)
            mDefaultAvatar = mContext.getDrawable(0x108032f);
        setImageDrawable(mDefaultAvatar);
    }

    public void setMode(int i)
    {
    }

    public void setOverlay(Drawable drawable)
    {
        mOverlay = drawable;
    }

    public void setPrioritizedMimeType(String s)
    {
        mPrioritizedMimeType = s;
    }

    static final int EMAIL_ID_COLUMN_INDEX = 0;
    static final String EMAIL_LOOKUP_PROJECTION[] = {
        "contact_id", "lookup"
    };
    static final int EMAIL_LOOKUP_STRING_COLUMN_INDEX = 1;
    private static final String EXTRA_URI_CONTENT = "uri_content";
    static final int PHONE_ID_COLUMN_INDEX = 0;
    static final String PHONE_LOOKUP_PROJECTION[] = {
        "_id", "lookup"
    };
    static final int PHONE_LOOKUP_STRING_COLUMN_INDEX = 1;
    private static final int TOKEN_EMAIL_LOOKUP = 0;
    private static final int TOKEN_EMAIL_LOOKUP_AND_TRIGGER = 2;
    private static final int TOKEN_PHONE_LOOKUP = 1;
    private static final int TOKEN_PHONE_LOOKUP_AND_TRIGGER = 3;
    private String mContactEmail;
    private String mContactPhone;
    private Uri mContactUri;
    private Drawable mDefaultAvatar;
    protected String mExcludeMimes[];
    private Bundle mExtras;
    private Drawable mOverlay;
    private String mPrioritizedMimeType;
    private QueryHandler mQueryHandler;

}
