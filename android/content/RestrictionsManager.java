// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.*;
import android.util.Log;
import android.util.Xml;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.util.*;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.content:
//            RestrictionEntry, Context, IRestrictionsManager, Intent

public class RestrictionsManager
{

    public RestrictionsManager(Context context, IRestrictionsManager irestrictionsmanager)
    {
        mContext = context;
        mService = irestrictionsmanager;
    }

    private static Bundle addRestrictionToBundle(Bundle bundle, RestrictionEntry restrictionentry)
    {
        restrictionentry.getType();
        JVM INSTR tableswitch 0 8: default 56
    //                   0 130
    //                   1 86
    //                   2 100
    //                   3 100
    //                   4 100
    //                   5 115
    //                   6 130
    //                   7 145
    //                   8 168;
           goto _L1 _L2 _L3 _L4 _L4 _L4 _L5 _L2 _L6 _L7
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unsupported restrictionEntry type: ").append(restrictionentry.getType()).toString());
_L3:
        bundle.putBoolean(restrictionentry.getKey(), restrictionentry.getSelectedState());
_L9:
        return bundle;
_L4:
        bundle.putStringArray(restrictionentry.getKey(), restrictionentry.getAllSelectedStrings());
        continue; /* Loop/switch isn't completed */
_L5:
        bundle.putInt(restrictionentry.getKey(), restrictionentry.getIntValue());
        continue; /* Loop/switch isn't completed */
_L2:
        bundle.putString(restrictionentry.getKey(), restrictionentry.getSelectedString());
        continue; /* Loop/switch isn't completed */
_L6:
        Bundle bundle1 = convertRestrictionsToBundle(Arrays.asList(restrictionentry.getRestrictions()));
        bundle.putBundle(restrictionentry.getKey(), bundle1);
        continue; /* Loop/switch isn't completed */
_L7:
        RestrictionEntry arestrictionentry[] = restrictionentry.getRestrictions();
        Bundle abundle[] = new Bundle[arestrictionentry.length];
        int i = 0;
        while(i < arestrictionentry.length) 
        {
            RestrictionEntry arestrictionentry1[] = arestrictionentry[i].getRestrictions();
            if(arestrictionentry1 == null)
            {
                Log.w("RestrictionsManager", "addRestrictionToBundle: Non-bundle entry found in bundle array");
                abundle[i] = new Bundle();
            } else
            {
                abundle[i] = convertRestrictionsToBundle(Arrays.asList(arestrictionentry1));
            }
            i++;
        }
        bundle.putParcelableArray(restrictionentry.getKey(), abundle);
        if(true) goto _L9; else goto _L8
_L8:
    }

    public static Bundle convertRestrictionsToBundle(List list)
    {
        Bundle bundle = new Bundle();
        for(list = list.iterator(); list.hasNext(); addRestrictionToBundle(bundle, (RestrictionEntry)list.next()));
        return bundle;
    }

    private List loadManifestRestrictions(String s, XmlResourceParser xmlresourceparser)
    {
        ArrayList arraylist;
        Context context;
        int i;
        RestrictionEntry restrictionentry;
        try
        {
            context = mContext.createPackageContext(s, 0);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        arraylist = new ArrayList();
        try
        {
            i = xmlresourceparser.next();
        }
        // Misplaced declaration of an exception variable
        catch(XmlResourceParser xmlresourceparser)
        {
            Log.w("RestrictionsManager", (new StringBuilder()).append("Reading restriction metadata for ").append(s).toString(), xmlresourceparser);
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(XmlResourceParser xmlresourceparser)
        {
            Log.w("RestrictionsManager", (new StringBuilder()).append("Reading restriction metadata for ").append(s).toString(), xmlresourceparser);
            return null;
        }
        if(i == 1)
            break; /* Loop/switch isn't completed */
        if(i != 2)
            break MISSING_BLOCK_LABEL_60;
        restrictionentry = loadRestrictionElement(context, xmlresourceparser);
        if(restrictionentry == null)
            break MISSING_BLOCK_LABEL_60;
        arraylist.add(restrictionentry);
        i = xmlresourceparser.next();
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_27;
_L1:
        return arraylist;
    }

    private RestrictionEntry loadRestriction(Context context, TypedArray typedarray, XmlResourceParser xmlresourceparser)
        throws IOException, XmlPullParserException
    {
        String s;
        int i;
        RestrictionEntry restrictionentry;
        s = typedarray.getString(3);
        i = typedarray.getInt(6, -1);
        String s1 = typedarray.getString(2);
        String s2 = typedarray.getString(0);
        int j = typedarray.getResourceId(1, 0);
        int k = typedarray.getResourceId(5, 0);
        if(i == -1)
        {
            Log.w("RestrictionsManager", "restrictionType cannot be omitted");
            return null;
        }
        if(s == null)
        {
            Log.w("RestrictionsManager", "key cannot be omitted");
            return null;
        }
        restrictionentry = new RestrictionEntry(i, s);
        restrictionentry.setTitle(s1);
        restrictionentry.setDescription(s2);
        if(j != 0)
            restrictionentry.setChoiceEntries(context, j);
        if(k != 0)
            restrictionentry.setChoiceValues(context, k);
        i;
        JVM INSTR tableswitch 0 8: default 184
    //                   0 214
    //                   1 271
    //                   2 214
    //                   3 184
    //                   4 241
    //                   5 227
    //                   6 214
    //                   7 285
    //                   8 285;
           goto _L1 _L2 _L3 _L2 _L1 _L4 _L5 _L2 _L6 _L6
_L1:
        Log.w("RestrictionsManager", (new StringBuilder()).append("Unknown restriction type ").append(i).toString());
_L8:
        return restrictionentry;
_L2:
        restrictionentry.setSelectedString(typedarray.getString(4));
        continue; /* Loop/switch isn't completed */
_L5:
        restrictionentry.setIntValue(typedarray.getInt(4, 0));
        continue; /* Loop/switch isn't completed */
_L4:
        i = typedarray.getResourceId(4, 0);
        if(i != 0)
            restrictionentry.setAllSelectedStrings(context.getResources().getStringArray(i));
        continue; /* Loop/switch isn't completed */
_L3:
        restrictionentry.setSelectedState(typedarray.getBoolean(4, false));
        continue; /* Loop/switch isn't completed */
_L6:
        int l = xmlresourceparser.getDepth();
        ArrayList arraylist = new ArrayList();
        do
        {
            if(!XmlUtils.nextElementWithin(xmlresourceparser, l))
                break;
            typedarray = loadRestrictionElement(context, xmlresourceparser);
            if(typedarray == null)
            {
                Log.w("RestrictionsManager", (new StringBuilder()).append("Child entry cannot be loaded for bundle restriction ").append(s).toString());
            } else
            {
                arraylist.add(typedarray);
                if(i == 8 && typedarray.getType() != 7)
                    Log.w("RestrictionsManager", (new StringBuilder()).append("bundle_array ").append(s).append(" can only contain entries of type bundle").toString());
            }
        } while(true);
        restrictionentry.setRestrictions((RestrictionEntry[])arraylist.toArray(new RestrictionEntry[arraylist.size()]));
        if(true) goto _L8; else goto _L7
_L7:
    }

    private RestrictionEntry loadRestrictionElement(Context context, XmlResourceParser xmlresourceparser)
        throws IOException, XmlPullParserException
    {
        if(xmlresourceparser.getName().equals("restriction"))
        {
            android.util.AttributeSet attributeset = Xml.asAttributeSet(xmlresourceparser);
            if(attributeset != null)
                return loadRestriction(context, context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.RestrictionEntry), xmlresourceparser);
        }
        return null;
    }

    public Intent createLocalApprovalIntent()
    {
label0:
        {
            Intent intent;
            try
            {
                if(mService == null)
                    break label0;
                intent = mService.createLocalApprovalIntent();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return intent;
        }
        return null;
    }

    public Bundle getApplicationRestrictions()
    {
label0:
        {
            Bundle bundle;
            try
            {
                if(mService == null)
                    break label0;
                bundle = mService.getApplicationRestrictions(mContext.getPackageName());
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return bundle;
        }
        return null;
    }

    public List getManifestRestrictions(String s)
    {
        Object obj;
        try
        {
            obj = mContext.getPackageManager().getApplicationInfo(s, 128);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("No such package ").append(s).toString());
        }
        if(obj == null || ((ApplicationInfo) (obj)).metaData.containsKey("android.content.APP_RESTRICTIONS") ^ true)
            return null;
        else
            return loadManifestRestrictions(s, ((ApplicationInfo) (obj)).loadXmlMetaData(mContext.getPackageManager(), "android.content.APP_RESTRICTIONS"));
    }

    public boolean hasRestrictionsProvider()
    {
label0:
        {
            boolean flag;
            try
            {
                if(mService == null)
                    break label0;
                flag = mService.hasRestrictionsProvider();
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            return flag;
        }
        return false;
    }

    public void notifyPermissionResponse(String s, PersistableBundle persistablebundle)
    {
        if(s == null)
            throw new NullPointerException("packageName cannot be null");
        if(persistablebundle == null)
            throw new NullPointerException("request cannot be null");
        if(!persistablebundle.containsKey("android.request.id"))
            throw new IllegalArgumentException("REQUEST_KEY_ID must be specified");
        if(!persistablebundle.containsKey("android.response.result"))
            throw new IllegalArgumentException("RESPONSE_KEY_RESULT must be specified");
        try
        {
            if(mService != null)
                mService.notifyPermissionResponse(s, persistablebundle);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void requestPermission(String s, String s1, PersistableBundle persistablebundle)
    {
        if(s == null)
            throw new NullPointerException("requestType cannot be null");
        if(s1 == null)
            throw new NullPointerException("requestId cannot be null");
        if(persistablebundle == null)
            throw new NullPointerException("request cannot be null");
        try
        {
            if(mService != null)
                mService.requestPermission(mContext.getPackageName(), s, s1, persistablebundle);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public static final String ACTION_PERMISSION_RESPONSE_RECEIVED = "android.content.action.PERMISSION_RESPONSE_RECEIVED";
    public static final String ACTION_REQUEST_LOCAL_APPROVAL = "android.content.action.REQUEST_LOCAL_APPROVAL";
    public static final String ACTION_REQUEST_PERMISSION = "android.content.action.REQUEST_PERMISSION";
    public static final String EXTRA_PACKAGE_NAME = "android.content.extra.PACKAGE_NAME";
    public static final String EXTRA_REQUEST_BUNDLE = "android.content.extra.REQUEST_BUNDLE";
    public static final String EXTRA_REQUEST_ID = "android.content.extra.REQUEST_ID";
    public static final String EXTRA_REQUEST_TYPE = "android.content.extra.REQUEST_TYPE";
    public static final String EXTRA_RESPONSE_BUNDLE = "android.content.extra.RESPONSE_BUNDLE";
    public static final String META_DATA_APP_RESTRICTIONS = "android.content.APP_RESTRICTIONS";
    public static final String REQUEST_KEY_APPROVE_LABEL = "android.request.approve_label";
    public static final String REQUEST_KEY_DATA = "android.request.data";
    public static final String REQUEST_KEY_DENY_LABEL = "android.request.deny_label";
    public static final String REQUEST_KEY_ICON = "android.request.icon";
    public static final String REQUEST_KEY_ID = "android.request.id";
    public static final String REQUEST_KEY_MESSAGE = "android.request.mesg";
    public static final String REQUEST_KEY_NEW_REQUEST = "android.request.new_request";
    public static final String REQUEST_KEY_TITLE = "android.request.title";
    public static final String REQUEST_TYPE_APPROVAL = "android.request.type.approval";
    public static final String RESPONSE_KEY_ERROR_CODE = "android.response.errorcode";
    public static final String RESPONSE_KEY_MESSAGE = "android.response.msg";
    public static final String RESPONSE_KEY_RESPONSE_TIMESTAMP = "android.response.timestamp";
    public static final String RESPONSE_KEY_RESULT = "android.response.result";
    public static final int RESULT_APPROVED = 1;
    public static final int RESULT_DENIED = 2;
    public static final int RESULT_ERROR = 5;
    public static final int RESULT_ERROR_BAD_REQUEST = 1;
    public static final int RESULT_ERROR_INTERNAL = 3;
    public static final int RESULT_ERROR_NETWORK = 2;
    public static final int RESULT_NO_RESPONSE = 3;
    public static final int RESULT_UNKNOWN_REQUEST = 4;
    private static final String TAG = "RestrictionsManager";
    private static final String TAG_RESTRICTION = "restriction";
    private final Context mContext;
    private final IRestrictionsManager mService;
}
