// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.animation.LayoutTransition;
import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.*;
import android.os.*;
import android.text.TextUtils;
import android.util.TypedValue;
import android.util.Xml;
import android.view.*;
import android.widget.*;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.preference:
//            PreferenceScreen, PreferenceManager, Preference, PreferenceFragment

public abstract class PreferenceActivity extends ListActivity
    implements PreferenceManager.OnPreferenceTreeClickListener, PreferenceFragment.OnPreferenceStartFragmentCallback
{
    public static final class Header
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public CharSequence getBreadCrumbShortTitle(Resources resources)
        {
            if(breadCrumbShortTitleRes != 0)
                return resources.getText(breadCrumbShortTitleRes);
            else
                return breadCrumbShortTitle;
        }

        public CharSequence getBreadCrumbTitle(Resources resources)
        {
            if(breadCrumbTitleRes != 0)
                return resources.getText(breadCrumbTitleRes);
            else
                return breadCrumbTitle;
        }

        public CharSequence getSummary(Resources resources)
        {
            if(summaryRes != 0)
                return resources.getText(summaryRes);
            else
                return summary;
        }

        public CharSequence getTitle(Resources resources)
        {
            if(titleRes != 0)
                return resources.getText(titleRes);
            else
                return title;
        }

        public void readFromParcel(Parcel parcel)
        {
            id = parcel.readLong();
            titleRes = parcel.readInt();
            title = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            summaryRes = parcel.readInt();
            summary = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            breadCrumbTitleRes = parcel.readInt();
            breadCrumbTitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            breadCrumbShortTitleRes = parcel.readInt();
            breadCrumbShortTitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            iconRes = parcel.readInt();
            fragment = parcel.readString();
            fragmentArguments = parcel.readBundle();
            if(parcel.readInt() != 0)
                intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
            extras = parcel.readBundle();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeLong(id);
            parcel.writeInt(titleRes);
            TextUtils.writeToParcel(title, parcel, i);
            parcel.writeInt(summaryRes);
            TextUtils.writeToParcel(summary, parcel, i);
            parcel.writeInt(breadCrumbTitleRes);
            TextUtils.writeToParcel(breadCrumbTitle, parcel, i);
            parcel.writeInt(breadCrumbShortTitleRes);
            TextUtils.writeToParcel(breadCrumbShortTitle, parcel, i);
            parcel.writeInt(iconRes);
            parcel.writeString(fragment);
            parcel.writeBundle(fragmentArguments);
            if(intent != null)
            {
                parcel.writeInt(1);
                intent.writeToParcel(parcel, i);
            } else
            {
                parcel.writeInt(0);
            }
            parcel.writeBundle(extras);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Header createFromParcel(Parcel parcel)
            {
                return new Header(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Header[] newArray(int i)
            {
                return new Header[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public CharSequence breadCrumbShortTitle;
        public int breadCrumbShortTitleRes;
        public CharSequence breadCrumbTitle;
        public int breadCrumbTitleRes;
        public Bundle extras;
        public String fragment;
        public Bundle fragmentArguments;
        public int iconRes;
        public long id;
        public Intent intent;
        public CharSequence summary;
        public int summaryRes;
        public CharSequence title;
        public int titleRes;


        public Header()
        {
            id = -1L;
        }

        Header(Parcel parcel)
        {
            id = -1L;
            readFromParcel(parcel);
        }
    }

    private static class HeaderAdapter extends ArrayAdapter
    {

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            Object obj;
            if(view == null)
            {
                viewgroup = mInflater.inflate(mLayoutResId, viewgroup, false);
                view = new HeaderViewHolder(null);
                view.icon = (ImageView)viewgroup.findViewById(0x1020006);
                view.title = (TextView)viewgroup.findViewById(0x1020016);
                view.summary = (TextView)viewgroup.findViewById(0x1020010);
                viewgroup.setTag(view);
            } else
            {
                viewgroup = view;
                view = (HeaderViewHolder)view.getTag();
            }
            obj = (Header)getItem(i);
            if(mRemoveIconIfEmpty)
            {
                if(((Header) (obj)).iconRes == 0)
                {
                    ((HeaderViewHolder) (view)).icon.setVisibility(8);
                } else
                {
                    ((HeaderViewHolder) (view)).icon.setVisibility(0);
                    ((HeaderViewHolder) (view)).icon.setImageResource(((Header) (obj)).iconRes);
                }
            } else
            {
                ((HeaderViewHolder) (view)).icon.setImageResource(((Header) (obj)).iconRes);
            }
            ((HeaderViewHolder) (view)).title.setText(((Header) (obj)).getTitle(getContext().getResources()));
            obj = ((Header) (obj)).getSummary(getContext().getResources());
            if(!TextUtils.isEmpty(((CharSequence) (obj))))
            {
                ((HeaderViewHolder) (view)).summary.setVisibility(0);
                ((HeaderViewHolder) (view)).summary.setText(((CharSequence) (obj)));
            } else
            {
                ((HeaderViewHolder) (view)).summary.setVisibility(8);
            }
            return viewgroup;
        }

        private LayoutInflater mInflater;
        private int mLayoutResId;
        private boolean mRemoveIconIfEmpty;

        public HeaderAdapter(Context context, List list, int i, boolean flag)
        {
            super(context, 0, list);
            mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
            mLayoutResId = i;
            mRemoveIconIfEmpty = flag;
        }
    }

    private static class HeaderAdapter.HeaderViewHolder
    {

        ImageView icon;
        TextView summary;
        TextView title;

        private HeaderAdapter.HeaderViewHolder()
        {
        }

        HeaderAdapter.HeaderViewHolder(HeaderAdapter.HeaderViewHolder headerviewholder)
        {
            this();
        }
    }


    static ListAdapter _2D_get0(PreferenceActivity preferenceactivity)
    {
        return preferenceactivity.mAdapter;
    }

    static Header _2D_get1(PreferenceActivity preferenceactivity)
    {
        return preferenceactivity.mCurHeader;
    }

    static ArrayList _2D_get2(PreferenceActivity preferenceactivity)
    {
        return preferenceactivity.mHeaders;
    }

    static void _2D_wrap0(PreferenceActivity preferenceactivity)
    {
        preferenceactivity.bindPreferences();
    }

    public PreferenceActivity()
    {
        mPreferenceHeaderItemResId = 0;
        mPreferenceHeaderRemoveEmptyIcon = false;
        mHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 1 2: default 28
            //                           1 29
            //                           2 39;
                   goto _L1 _L2 _L3
_L1:
                return;
_L2:
                PreferenceActivity._2D_wrap0(PreferenceActivity.this);
                continue; /* Loop/switch isn't completed */
_L3:
                Object obj = new ArrayList(PreferenceActivity._2D_get2(PreferenceActivity.this));
                PreferenceActivity._2D_get2(PreferenceActivity.this).clear();
                onBuildHeaders(PreferenceActivity._2D_get2(PreferenceActivity.this));
                if(PreferenceActivity._2D_get0(PreferenceActivity.this) instanceof BaseAdapter)
                    ((BaseAdapter)PreferenceActivity._2D_get0(PreferenceActivity.this)).notifyDataSetChanged();
                message = onGetNewHeader();
                if(message != null && ((Header) (message)).fragment != null)
                {
                    obj = findBestMatchingHeader(message, ((ArrayList) (obj)));
                    if(obj == null || PreferenceActivity._2D_get1(PreferenceActivity.this) != obj)
                        switchToHeader(message);
                } else
                if(PreferenceActivity._2D_get1(PreferenceActivity.this) != null)
                {
                    message = findBestMatchingHeader(PreferenceActivity._2D_get1(PreferenceActivity.this), PreferenceActivity._2D_get2(PreferenceActivity.this));
                    if(message != null)
                        setSelectedHeader(message);
                }
                if(true) goto _L1; else goto _L4
_L4:
            }

            final PreferenceActivity this$0;

            
            {
                this$0 = PreferenceActivity.this;
                super();
            }
        }
;
    }

    private void bindPreferences()
    {
        PreferenceScreen preferencescreen = getPreferenceScreen();
        if(preferencescreen != null)
        {
            preferencescreen.bind(getListView());
            if(mSavedInstanceState != null)
            {
                super.onRestoreInstanceState(mSavedInstanceState);
                mSavedInstanceState = null;
            }
        }
    }

    private void postBindPreferences()
    {
        if(mHandler.hasMessages(1))
        {
            return;
        } else
        {
            mHandler.obtainMessage(1).sendToTarget();
            return;
        }
    }

    private void requirePreferenceManager()
    {
        if(mPreferenceManager == null)
        {
            if(mAdapter == null)
                throw new RuntimeException("This should be called after super.onCreate.");
            else
                throw new RuntimeException("Modern two-pane PreferenceActivity requires use of a PreferenceFragment");
        } else
        {
            return;
        }
    }

    private void switchToHeaderInner(String s, Bundle bundle)
    {
        getFragmentManager().popBackStack(":android:prefs", 1);
        if(!isValidFragment(s))
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid fragment for this activity: ").append(s).toString());
        s = Fragment.instantiate(this, s, bundle);
        bundle = getFragmentManager().beginTransaction();
        int i;
        if(mSinglePane)
            i = 0;
        else
            i = 4099;
        bundle.setTransition(i);
        bundle.replace(0x1020386, s);
        bundle.commitAllowingStateLoss();
        if(mSinglePane && mPrefsContainer.getVisibility() == 8)
        {
            mPrefsContainer.setVisibility(0);
            mHeadersContainer.setVisibility(8);
        }
    }

    public void addPreferencesFromIntent(Intent intent)
    {
        requirePreferenceManager();
        setPreferenceScreen(mPreferenceManager.inflateFromIntent(intent, getPreferenceScreen()));
    }

    public void addPreferencesFromResource(int i)
    {
        requirePreferenceManager();
        setPreferenceScreen(mPreferenceManager.inflateFromResource(this, i, getPreferenceScreen()));
    }

    Header findBestMatchingHeader(Header header, ArrayList arraylist)
    {
        ArrayList arraylist1;
        int i;
        arraylist1 = new ArrayList();
        i = 0;
_L2:
        Header header1;
        int k;
label0:
        {
            if(i < arraylist.size())
            {
                header1 = (Header)arraylist.get(i);
                if(header != header1 && (header.id == -1L || header.id != header1.id))
                    break label0;
                arraylist1.clear();
                arraylist1.add(header1);
            }
            k = arraylist1.size();
            if(k == 1)
                return (Header)arraylist1.get(0);
            break MISSING_BLOCK_LABEL_192;
        }
        if(header.fragment == null)
            break; /* Loop/switch isn't completed */
        if(header.fragment.equals(header1.fragment))
            arraylist1.add(header1);
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        if(header.intent != null)
        {
            if(header.intent.equals(header1.intent))
                arraylist1.add(header1);
        } else
        if(header.title != null && header.title.equals(header1.title))
            arraylist1.add(header1);
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
        if(k > 1)
        {
            for(int j = 0; j < k; j++)
            {
                arraylist = (Header)arraylist1.get(j);
                if(header.fragmentArguments != null && header.fragmentArguments.equals(((Header) (arraylist)).fragmentArguments))
                    return arraylist;
                if(header.extras != null && header.extras.equals(((Header) (arraylist)).extras))
                    return arraylist;
                if(header.title != null && header.title.equals(((Header) (arraylist)).title))
                    return arraylist;
            }

        }
        return null;
    }

    public Preference findPreference(CharSequence charsequence)
    {
        if(mPreferenceManager == null)
            return null;
        else
            return mPreferenceManager.findPreference(charsequence);
    }

    public void finishPreferencePanel(Fragment fragment, int i, Intent intent)
    {
        onBackPressed();
        if(fragment != null && fragment.getTargetFragment() != null)
            fragment.getTargetFragment().onActivityResult(fragment.getTargetRequestCode(), i, intent);
    }

    public List getHeaders()
    {
        return mHeaders;
    }

    protected Button getNextButton()
    {
        return mNextButton;
    }

    public PreferenceManager getPreferenceManager()
    {
        return mPreferenceManager;
    }

    public PreferenceScreen getPreferenceScreen()
    {
        if(mPreferenceManager != null)
            return mPreferenceManager.getPreferenceScreen();
        else
            return null;
    }

    public boolean hasHeaders()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mHeadersContainer != null)
        {
            flag1 = flag;
            if(mHeadersContainer.getVisibility() == 0)
                flag1 = true;
        }
        return flag1;
    }

    protected boolean hasNextButton()
    {
        boolean flag;
        if(mNextButton != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void invalidateHeaders()
    {
        if(!mHandler.hasMessages(2))
            mHandler.sendEmptyMessage(2);
    }

    public boolean isMultiPane()
    {
        return mSinglePane ^ true;
    }

    protected boolean isValidFragment(String s)
    {
        if(getApplicationInfo().targetSdkVersion >= 19)
            throw new RuntimeException((new StringBuilder()).append("Subclasses of PreferenceActivity must override isValidFragment(String) to verify that the Fragment class is valid! ").append(getClass().getName()).append(" has not checked if fragment ").append(s).append(" is valid.").toString());
        else
            return true;
    }

    public void loadHeadersFromResource(int i, List list)
    {
        Object obj;
        Object obj1;
        Object obj2;
        obj = null;
        obj1 = null;
        obj2 = null;
        XmlResourceParser xmlresourceparser = getResources().getXml(i);
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        android.util.AttributeSet attributeset = Xml.asAttributeSet(xmlresourceparser);
_L2:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        i = xmlresourceparser.next();
        if(i != 1 && i != 2) goto _L2; else goto _L1
_L1:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        Object obj3 = xmlresourceparser.getName();
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        if("preference-headers".equals(obj3))
            break MISSING_BLOCK_LABEL_254;
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        Object obj4 = JVM INSTR new #183 <Class RuntimeException>;
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        list = JVM INSTR new #210 <Class StringBuilder>;
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        list.StringBuilder();
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        ((RuntimeException) (obj4)).RuntimeException(list.append("XML document must start with <preference-headers> tag; found").append(((String) (obj3))).append(" at ").append(xmlresourceparser.getPositionDescription()).toString());
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        throw obj4;
        list;
        obj = obj2;
        obj1 = JVM INSTR new #183 <Class RuntimeException>;
        obj = obj2;
        ((RuntimeException) (obj1)).RuntimeException("Error parsing headers", list);
        obj = obj2;
        throw obj1;
        list;
        if(obj != null)
            ((XmlResourceParser) (obj)).close();
        throw list;
        obj3 = null;
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        i = xmlresourceparser.getDepth();
_L30:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        int j = xmlresourceparser.next();
        if(j == 1)
            break; /* Loop/switch isn't completed */
        if(j != 3)
            break MISSING_BLOCK_LABEL_330;
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        if(xmlresourceparser.getDepth() <= i)
            break; /* Loop/switch isn't completed */
        if(j == 3 || j == 4)
            continue; /* Loop/switch isn't completed */
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        if(!"header".equals(xmlresourceparser.getName()))
            break MISSING_BLOCK_LABEL_1319;
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        Header header = JVM INSTR new #18  <Class PreferenceActivity$Header>;
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        header.Header();
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        obj4 = obtainStyledAttributes(attributeset, com.android.internal.R.styleable.PreferenceHeader);
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        header.id = ((TypedArray) (obj4)).getResourceId(1, -1);
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        TypedValue typedvalue = ((TypedArray) (obj4)).peekValue(2);
        if(typedvalue == null) goto _L4; else goto _L3
_L3:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        if(typedvalue.type != 3) goto _L4; else goto _L5
_L5:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        if(typedvalue.resourceId == 0) goto _L7; else goto _L6
_L6:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        header.titleRes = typedvalue.resourceId;
_L4:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        typedvalue = ((TypedArray) (obj4)).peekValue(3);
        if(typedvalue == null) goto _L9; else goto _L8
_L8:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        if(typedvalue.type != 3) goto _L9; else goto _L10
_L10:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        if(typedvalue.resourceId == 0) goto _L12; else goto _L11
_L11:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        header.summaryRes = typedvalue.resourceId;
_L9:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        typedvalue = ((TypedArray) (obj4)).peekValue(5);
        if(typedvalue == null) goto _L14; else goto _L13
_L13:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        if(typedvalue.type != 3) goto _L14; else goto _L15
_L15:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        if(typedvalue.resourceId == 0) goto _L17; else goto _L16
_L16:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        header.breadCrumbTitleRes = typedvalue.resourceId;
_L14:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        typedvalue = ((TypedArray) (obj4)).peekValue(6);
        if(typedvalue == null) goto _L19; else goto _L18
_L18:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        if(typedvalue.type != 3) goto _L19; else goto _L20
_L20:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        if(typedvalue.resourceId == 0) goto _L22; else goto _L21
_L21:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        header.breadCrumbShortTitleRes = typedvalue.resourceId;
_L19:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        header.iconRes = ((TypedArray) (obj4)).getResourceId(0, 0);
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        header.fragment = ((TypedArray) (obj4)).getString(4);
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        ((TypedArray) (obj4)).recycle();
        obj4 = obj3;
        if(obj3 != null)
            break MISSING_BLOCK_LABEL_886;
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        obj4 = JVM INSTR new #328 <Class Bundle>;
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        ((Bundle) (obj4)).Bundle();
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        int k = xmlresourceparser.getDepth();
_L26:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        j = xmlresourceparser.next();
        if(j == 1)
            break MISSING_BLOCK_LABEL_1252;
        if(j != 3) goto _L24; else goto _L23
_L23:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        if(xmlresourceparser.getDepth() <= k)
            break MISSING_BLOCK_LABEL_1252;
_L24:
        if(j == 3 || j == 4) goto _L26; else goto _L25
_L25:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        obj3 = xmlresourceparser.getName();
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        if(!((String) (obj3)).equals("extra")) goto _L28; else goto _L27
_L27:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        getResources().parseBundleExtra("extra", attributeset, ((Bundle) (obj4)));
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        XmlUtils.skipCurrentTag(xmlresourceparser);
          goto _L26
        obj2;
        obj = obj1;
        list = JVM INSTR new #183 <Class RuntimeException>;
        obj = obj1;
        list.RuntimeException("Error parsing headers", ((Throwable) (obj2)));
        obj = obj1;
        throw list;
_L7:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        header.title = typedvalue.string;
          goto _L4
_L12:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        header.summary = typedvalue.string;
          goto _L9
_L17:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        header.breadCrumbTitle = typedvalue.string;
          goto _L14
_L22:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        header.breadCrumbShortTitle = typedvalue.string;
          goto _L19
_L28:
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        if(!((String) (obj3)).equals("intent"))
            break MISSING_BLOCK_LABEL_1233;
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        header.intent = Intent.parseIntent(getResources(), xmlresourceparser, attributeset);
          goto _L26
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        XmlUtils.skipCurrentTag(xmlresourceparser);
          goto _L26
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        obj3 = obj4;
        if(((Bundle) (obj4)).size() <= 0)
            break MISSING_BLOCK_LABEL_1296;
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        header.fragmentArguments = ((Bundle) (obj4));
        obj3 = null;
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        list.add(header);
        continue; /* Loop/switch isn't completed */
        obj2 = xmlresourceparser;
        obj = xmlresourceparser;
        obj1 = xmlresourceparser;
        XmlUtils.skipCurrentTag(xmlresourceparser);
        if(true) goto _L30; else goto _L29
_L29:
        if(xmlresourceparser != null)
            xmlresourceparser.close();
        return;
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if(mPreferenceManager != null)
            mPreferenceManager.dispatchActivityResult(i, j, intent);
    }

    public void onBackPressed()
    {
        if(mCurHeader != null && mSinglePane && getFragmentManager().getBackStackEntryCount() == 0 && getIntent().getStringExtra(":android:show_fragment") == null)
        {
            mCurHeader = null;
            mPrefsContainer.setVisibility(8);
            mHeadersContainer.setVisibility(0);
            if(mActivityTitle != null)
                showBreadCrumbs(mActivityTitle, null);
            getListView().clearChoices();
        } else
        {
            super.onBackPressed();
        }
    }

    public void onBuildHeaders(List list)
    {
    }

    public Intent onBuildStartFragmentIntent(String s, Bundle bundle, int i, int j)
    {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClass(this, getClass());
        intent.putExtra(":android:show_fragment", s);
        intent.putExtra(":android:show_fragment_args", bundle);
        intent.putExtra(":android:show_fragment_title", i);
        intent.putExtra(":android:show_fragment_short_title", j);
        intent.putExtra(":android:no_headers", true);
        return intent;
    }

    public void onContentChanged()
    {
        super.onContentChanged();
        if(mPreferenceManager != null)
            postBindPreferences();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Object obj = obtainStyledAttributes(null, com.android.internal.R.styleable.PreferenceActivity, 0x1110099, 0);
        int i = ((TypedArray) (obj)).getResourceId(0, 0x10900c7);
        mPreferenceHeaderItemResId = ((TypedArray) (obj)).getResourceId(1, 0x10900c1);
        mPreferenceHeaderRemoveEmptyIcon = ((TypedArray) (obj)).getBoolean(2, false);
        ((TypedArray) (obj)).recycle();
        setContentView(i);
        mListFooter = (FrameLayout)findViewById(0x10202e1);
        mPrefsContainer = (ViewGroup)findViewById(0x1020388);
        mHeadersContainer = (ViewGroup)findViewById(0x1020284);
        boolean flag;
        Object obj1;
        int k;
        int l;
        if(!onIsHidingHeaders())
            flag = onIsMultiPane() ^ true;
        else
            flag = true;
        mSinglePane = flag;
        obj = getIntent().getStringExtra(":android:show_fragment");
        obj1 = getIntent().getBundleExtra(":android:show_fragment_args");
        k = getIntent().getIntExtra(":android:show_fragment_title", 0);
        l = getIntent().getIntExtra(":android:show_fragment_short_title", 0);
        mActivityTitle = getTitle();
        if(bundle != null)
        {
            obj1 = bundle.getParcelableArrayList(":android:headers");
            if(obj1 != null)
            {
                mHeaders.addAll(((java.util.Collection) (obj1)));
                int j = bundle.getInt(":android:cur_header", -1);
                if(j >= 0 && j < mHeaders.size())
                    setSelectedHeader((Header)mHeaders.get(j));
                else
                if(!mSinglePane && obj == null)
                    switchToHeader(onGetInitialHeader());
            } else
            {
                showBreadCrumbs(getTitle(), null);
            }
        } else
        {
            if(!onIsHidingHeaders())
                onBuildHeaders(mHeaders);
            if(obj != null)
                switchToHeader(((String) (obj)), ((Bundle) (obj1)));
            else
            if(!mSinglePane && mHeaders.size() > 0)
                switchToHeader(onGetInitialHeader());
        }
        if(mHeaders.size() > 0)
        {
            setListAdapter(new HeaderAdapter(this, mHeaders, mPreferenceHeaderItemResId, mPreferenceHeaderRemoveEmptyIcon));
            if(!mSinglePane)
                getListView().setChoiceMode(1);
        }
        if(mSinglePane && obj != null && k != 0)
        {
            obj1 = getText(k);
            if(l != 0)
                bundle = getText(l);
            else
                bundle = null;
            showBreadCrumbs(((CharSequence) (obj1)), bundle);
        }
        if(mHeaders.size() == 0 && obj == null)
        {
            setContentView(0x10900c9);
            mListFooter = (FrameLayout)findViewById(0x10202e1);
            mPrefsContainer = (ViewGroup)findViewById(0x1020386);
            mPreferenceManager = new PreferenceManager(this, 100);
            mPreferenceManager.setOnPreferenceTreeClickListener(this);
            mHeadersContainer = null;
        } else
        if(mSinglePane)
        {
            if(obj != null || mCurHeader != null)
                mHeadersContainer.setVisibility(8);
            else
                mPrefsContainer.setVisibility(8);
            ((ViewGroup)findViewById(0x1020387)).setLayoutTransition(new LayoutTransition());
        } else
        if(mHeaders.size() > 0 && mCurHeader != null)
            setSelectedHeader(mCurHeader);
        obj1 = getIntent();
        if(((Intent) (obj1)).getBooleanExtra("extra_prefs_show_button_bar", false))
        {
            findViewById(0x10201dc).setVisibility(0);
            bundle = (Button)findViewById(0x10201bd);
            bundle.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    setResult(0);
                    finish();
                }

                final PreferenceActivity this$0;

            
            {
                this$0 = PreferenceActivity.this;
                super();
            }
            }
);
            obj = (Button)findViewById(0x10203fb);
            ((Button) (obj)).setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    setResult(-1);
                    finish();
                }

                final PreferenceActivity this$0;

            
            {
                this$0 = PreferenceActivity.this;
                super();
            }
            }
);
            mNextButton = (Button)findViewById(0x1020323);
            mNextButton.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    setResult(-1);
                    finish();
                }

                final PreferenceActivity this$0;

            
            {
                this$0 = PreferenceActivity.this;
                super();
            }
            }
);
            String s;
            if(((Intent) (obj1)).hasExtra("extra_prefs_set_next_text"))
            {
                s = ((Intent) (obj1)).getStringExtra("extra_prefs_set_next_text");
                if(TextUtils.isEmpty(s))
                    mNextButton.setVisibility(8);
                else
                    mNextButton.setText(s);
            }
            if(((Intent) (obj1)).hasExtra("extra_prefs_set_back_text"))
            {
                s = ((Intent) (obj1)).getStringExtra("extra_prefs_set_back_text");
                if(TextUtils.isEmpty(s))
                    bundle.setVisibility(8);
                else
                    bundle.setText(s);
            }
            if(((Intent) (obj1)).getBooleanExtra("extra_prefs_show_skip", false))
                ((Button) (obj)).setVisibility(0);
        }
    }

    protected void onDestroy()
    {
        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
        super.onDestroy();
        if(mPreferenceManager != null)
            mPreferenceManager.dispatchActivityDestroy();
    }

    public Header onGetInitialHeader()
    {
        for(int i = 0; i < mHeaders.size(); i++)
        {
            Header header = (Header)mHeaders.get(i);
            if(header.fragment != null)
                return header;
        }

        throw new IllegalStateException("Must have at least one header with a fragment");
    }

    public Header onGetNewHeader()
    {
        return null;
    }

    public void onHeaderClick(Header header, int i)
    {
        if(header.fragment == null) goto _L2; else goto _L1
_L1:
        switchToHeader(header);
_L4:
        return;
_L2:
        if(header.intent != null)
            startActivity(header.intent);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean onIsHidingHeaders()
    {
        return getIntent().getBooleanExtra(":android:no_headers", false);
    }

    public boolean onIsMultiPane()
    {
        return getResources().getBoolean(0x11200ec);
    }

    protected void onListItemClick(ListView listview, View view, int i, long l)
    {
        if(!isResumed())
            return;
        super.onListItemClick(listview, view, i, l);
        if(mAdapter != null)
        {
            listview = ((ListView) (mAdapter.getItem(i)));
            if(listview instanceof Header)
                onHeaderClick((Header)listview, i);
        }
    }

    protected void onNewIntent(Intent intent)
    {
        if(mPreferenceManager != null)
            mPreferenceManager.dispatchNewIntent(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        if(menuitem.getItemId() == 0x102002c)
        {
            onBackPressed();
            return true;
        } else
        {
            return super.onOptionsItemSelected(menuitem);
        }
    }

    public boolean onPreferenceStartFragment(PreferenceFragment preferencefragment, Preference preference)
    {
        startPreferencePanel(preference.getFragment(), preference.getExtras(), preference.getTitleRes(), preference.getTitle(), null, 0);
        return true;
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferencescreen, Preference preference)
    {
        return false;
    }

    protected void onRestoreInstanceState(Bundle bundle)
    {
        if(mPreferenceManager != null)
        {
            Bundle bundle1 = bundle.getBundle(":android:preferences");
            if(bundle1 != null)
            {
                PreferenceScreen preferencescreen = getPreferenceScreen();
                if(preferencescreen != null)
                {
                    preferencescreen.restoreHierarchyState(bundle1);
                    mSavedInstanceState = bundle;
                    return;
                }
            }
        }
        super.onRestoreInstanceState(bundle);
        if(!mSinglePane && mCurHeader != null)
            setSelectedHeader(mCurHeader);
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        if(mHeaders.size() > 0)
        {
            bundle.putParcelableArrayList(":android:headers", mHeaders);
            if(mCurHeader != null)
            {
                int i = mHeaders.indexOf(mCurHeader);
                if(i >= 0)
                    bundle.putInt(":android:cur_header", i);
            }
        }
        if(mPreferenceManager != null)
        {
            PreferenceScreen preferencescreen = getPreferenceScreen();
            if(preferencescreen != null)
            {
                Bundle bundle1 = new Bundle();
                preferencescreen.saveHierarchyState(bundle1);
                bundle.putBundle(":android:preferences", bundle1);
            }
        }
    }

    protected void onStop()
    {
        super.onStop();
        if(mPreferenceManager != null)
            mPreferenceManager.dispatchActivityStop();
    }

    public void setListFooter(View view)
    {
        mListFooter.removeAllViews();
        mListFooter.addView(view, new android.widget.FrameLayout.LayoutParams(-1, -2));
    }

    public void setParentTitle(CharSequence charsequence, CharSequence charsequence1, android.view.View.OnClickListener onclicklistener)
    {
        if(mFragmentBreadCrumbs != null)
            mFragmentBreadCrumbs.setParentTitle(charsequence, charsequence1, onclicklistener);
    }

    public void setPreferenceScreen(PreferenceScreen preferencescreen)
    {
        requirePreferenceManager();
        if(mPreferenceManager.setPreferences(preferencescreen) && preferencescreen != null)
        {
            postBindPreferences();
            preferencescreen = getPreferenceScreen().getTitle();
            if(preferencescreen != null)
                setTitle(preferencescreen);
        }
    }

    void setSelectedHeader(Header header)
    {
        mCurHeader = header;
        int i = mHeaders.indexOf(header);
        if(i >= 0)
            getListView().setItemChecked(i, true);
        else
            getListView().clearChoices();
        showBreadCrumbs(header);
    }

    void showBreadCrumbs(Header header)
    {
        if(header != null)
        {
            CharSequence charsequence = header.getBreadCrumbTitle(getResources());
            CharSequence charsequence1 = charsequence;
            if(charsequence == null)
                charsequence1 = header.getTitle(getResources());
            charsequence = charsequence1;
            if(charsequence1 == null)
                charsequence = getTitle();
            showBreadCrumbs(charsequence, header.getBreadCrumbShortTitle(getResources()));
        } else
        {
            showBreadCrumbs(getTitle(), null);
        }
    }

    public void showBreadCrumbs(CharSequence charsequence, CharSequence charsequence1)
    {
        if(mFragmentBreadCrumbs == null)
        {
            View view = findViewById(0x1020016);
            try
            {
                mFragmentBreadCrumbs = (FragmentBreadCrumbs)view;
            }
            // Misplaced declaration of an exception variable
            catch(CharSequence charsequence1)
            {
                setTitle(charsequence);
                return;
            }
            if(mFragmentBreadCrumbs == null)
            {
                if(charsequence != null)
                    setTitle(charsequence);
                return;
            }
            if(mSinglePane)
            {
                mFragmentBreadCrumbs.setVisibility(8);
                View view1 = findViewById(0x10201d1);
                if(view1 != null)
                    view1.setVisibility(8);
                setTitle(charsequence);
            }
            mFragmentBreadCrumbs.setMaxVisible(2);
            mFragmentBreadCrumbs.setActivity(this);
        }
        if(mFragmentBreadCrumbs.getVisibility() != 0)
        {
            setTitle(charsequence);
        } else
        {
            mFragmentBreadCrumbs.setTitle(charsequence, charsequence1);
            mFragmentBreadCrumbs.setParentTitle(null, null, null);
        }
    }

    public void startPreferenceFragment(Fragment fragment, boolean flag)
    {
        FragmentTransaction fragmenttransaction = getFragmentManager().beginTransaction();
        fragmenttransaction.replace(0x1020386, fragment);
        if(flag)
        {
            fragmenttransaction.setTransition(4097);
            fragmenttransaction.addToBackStack(":android:prefs");
        } else
        {
            fragmenttransaction.setTransition(4099);
        }
        fragmenttransaction.commitAllowingStateLoss();
    }

    public void startPreferencePanel(String s, Bundle bundle, int i, CharSequence charsequence, Fragment fragment, int j)
    {
        s = Fragment.instantiate(this, s, bundle);
        if(fragment != null)
            s.setTargetFragment(fragment, j);
        bundle = getFragmentManager().beginTransaction();
        bundle.replace(0x1020386, s);
        if(i == 0) goto _L2; else goto _L1
_L1:
        bundle.setBreadCrumbTitle(i);
_L4:
        bundle.setTransition(4097);
        bundle.addToBackStack(":android:prefs");
        bundle.commitAllowingStateLoss();
        return;
_L2:
        if(charsequence != null)
            bundle.setBreadCrumbTitle(charsequence);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void startWithFragment(String s, Bundle bundle, Fragment fragment, int i)
    {
        startWithFragment(s, bundle, fragment, i, 0, 0);
    }

    public void startWithFragment(String s, Bundle bundle, Fragment fragment, int i, int j, int k)
    {
        s = onBuildStartFragmentIntent(s, bundle, j, k);
        if(fragment == null)
            startActivity(s);
        else
            fragment.startActivityForResult(s, i);
    }

    public void switchToHeader(Header header)
    {
        if(mCurHeader == header)
        {
            getFragmentManager().popBackStack(":android:prefs", 1);
        } else
        {
            if(header.fragment == null)
                throw new IllegalStateException("can't switch to header that has no fragment");
            switchToHeaderInner(header.fragment, header.fragmentArguments);
            setSelectedHeader(header);
        }
    }

    public void switchToHeader(String s, Bundle bundle)
    {
        Object obj = null;
        int i = 0;
        do
        {
label0:
            {
                Header header = obj;
                if(i < mHeaders.size())
                {
                    if(!s.equals(((Header)mHeaders.get(i)).fragment))
                        break label0;
                    header = (Header)mHeaders.get(i);
                }
                setSelectedHeader(header);
                switchToHeaderInner(s, bundle);
                return;
            }
            i++;
        } while(true);
    }

    private static final String BACK_STACK_PREFS = ":android:prefs";
    private static final String CUR_HEADER_TAG = ":android:cur_header";
    public static final String EXTRA_NO_HEADERS = ":android:no_headers";
    private static final String EXTRA_PREFS_SET_BACK_TEXT = "extra_prefs_set_back_text";
    private static final String EXTRA_PREFS_SET_NEXT_TEXT = "extra_prefs_set_next_text";
    private static final String EXTRA_PREFS_SHOW_BUTTON_BAR = "extra_prefs_show_button_bar";
    private static final String EXTRA_PREFS_SHOW_SKIP = "extra_prefs_show_skip";
    public static final String EXTRA_SHOW_FRAGMENT = ":android:show_fragment";
    public static final String EXTRA_SHOW_FRAGMENT_ARGUMENTS = ":android:show_fragment_args";
    public static final String EXTRA_SHOW_FRAGMENT_SHORT_TITLE = ":android:show_fragment_short_title";
    public static final String EXTRA_SHOW_FRAGMENT_TITLE = ":android:show_fragment_title";
    private static final int FIRST_REQUEST_CODE = 100;
    private static final String HEADERS_TAG = ":android:headers";
    public static final long HEADER_ID_UNDEFINED = -1L;
    private static final int MSG_BIND_PREFERENCES = 1;
    private static final int MSG_BUILD_HEADERS = 2;
    private static final String PREFERENCES_TAG = ":android:preferences";
    private static final String TAG = "PreferenceActivity";
    private CharSequence mActivityTitle;
    private Header mCurHeader;
    private FragmentBreadCrumbs mFragmentBreadCrumbs;
    private Handler mHandler;
    private final ArrayList mHeaders = new ArrayList();
    private ViewGroup mHeadersContainer;
    private FrameLayout mListFooter;
    private Button mNextButton;
    private int mPreferenceHeaderItemResId;
    private boolean mPreferenceHeaderRemoveEmptyIcon;
    private PreferenceManager mPreferenceManager;
    private ViewGroup mPrefsContainer;
    private Bundle mSavedInstanceState;
    private boolean mSinglePane;
}
