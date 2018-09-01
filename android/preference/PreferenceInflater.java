// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.AttributeSet;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.preference:
//            GenericInflater, Preference, PreferenceGroup, PreferenceManager

class PreferenceInflater extends GenericInflater
{

    public PreferenceInflater(Context context, PreferenceManager preferencemanager)
    {
        super(context);
        init(preferencemanager);
    }

    PreferenceInflater(GenericInflater genericinflater, PreferenceManager preferencemanager, Context context)
    {
        super(genericinflater, context);
        init(preferencemanager);
    }

    private void init(PreferenceManager preferencemanager)
    {
        mPreferenceManager = preferencemanager;
        setDefaultPackage("android.preference.");
    }

    public GenericInflater cloneInContext(Context context)
    {
        return new PreferenceInflater(this, mPreferenceManager, context);
    }

    protected boolean onCreateCustomFromTag(XmlPullParser xmlpullparser, Preference preference, AttributeSet attributeset)
        throws XmlPullParserException
    {
        String s = xmlpullparser.getName();
        if(s.equals("intent"))
        {
            try
            {
                xmlpullparser = Intent.parseIntent(getContext().getResources(), xmlpullparser, attributeset);
            }
            // Misplaced declaration of an exception variable
            catch(XmlPullParser xmlpullparser)
            {
                preference = new XmlPullParserException("Error parsing preference");
                preference.initCause(xmlpullparser);
                throw preference;
            }
            if(xmlpullparser != null)
                preference.setIntent(xmlpullparser);
            return true;
        }
        if(s.equals("extra"))
        {
            getContext().getResources().parseBundleExtra("extra", attributeset, preference.getExtras());
            try
            {
                XmlUtils.skipCurrentTag(xmlpullparser);
            }
            // Misplaced declaration of an exception variable
            catch(XmlPullParser xmlpullparser)
            {
                preference = new XmlPullParserException("Error parsing preference");
                preference.initCause(xmlpullparser);
                throw preference;
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected volatile boolean onCreateCustomFromTag(XmlPullParser xmlpullparser, Object obj, AttributeSet attributeset)
        throws XmlPullParserException
    {
        return onCreateCustomFromTag(xmlpullparser, (Preference)obj, attributeset);
    }

    protected volatile GenericInflater.Parent onMergeRoots(GenericInflater.Parent parent, boolean flag, GenericInflater.Parent parent1)
    {
        return onMergeRoots((PreferenceGroup)parent, flag, (PreferenceGroup)parent1);
    }

    protected PreferenceGroup onMergeRoots(PreferenceGroup preferencegroup, boolean flag, PreferenceGroup preferencegroup1)
    {
        if(preferencegroup == null)
        {
            preferencegroup1.onAttachedToHierarchy(mPreferenceManager);
            return preferencegroup1;
        } else
        {
            return preferencegroup;
        }
    }

    private static final String EXTRA_TAG_NAME = "extra";
    private static final String INTENT_TAG_NAME = "intent";
    private static final String TAG = "PreferenceInflater";
    private PreferenceManager mPreferenceManager;
}
