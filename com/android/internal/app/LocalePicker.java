// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.*;
import android.app.backup.BackupManager;
import android.content.Context;
import android.content.res.*;
import android.miui.LocaleComparator;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.text.Collator;
import java.util.*;

// Referenced classes of package com.android.internal.app:
//            LocalePickerInjector

public class LocalePicker extends ListFragment
{
    public static class LocaleInfo
        implements Comparable
    {

        public int compareTo(LocaleInfo localeinfo)
        {
            return sCollator.compare(label, localeinfo.label);
        }

        public volatile int compareTo(Object obj)
        {
            return compareTo((LocaleInfo)obj);
        }

        public String getLabel()
        {
            return label;
        }

        public Locale getLocale()
        {
            return locale;
        }

        public String toString()
        {
            return label;
        }

        static final Collator sCollator = Collator.getInstance();
        String label;
        final Locale locale;


        public LocaleInfo(String s, Locale locale1)
        {
            label = s;
            locale = locale1;
        }
    }

    public static interface LocaleSelectionListener
    {

        public abstract void onLocaleSelected(Locale locale);
    }


    public LocalePicker()
    {
    }

    public static ArrayAdapter constructAdapter(Context context)
    {
        return constructAdapter(context, 0x109007e, 0x10202e5);
    }

    public static ArrayAdapter constructAdapter(Context context, int i, int j)
    {
        boolean flag;
        List list;
        LocaleInfo alocaleinfo[];
        if(android.provider.Settings.Global.getInt(context.getContentResolver(), "development_settings_enabled", 0) != 0)
            flag = true;
        else
            flag = false;
        list = getAllAssetLocales(context, flag);
        alocaleinfo = new LocaleInfo[list.size()];
        list.toArray(alocaleinfo);
        Arrays.sort(alocaleinfo, new LocaleComparator());
        return new ArrayAdapter(context, i, j, alocaleinfo, (LayoutInflater)context.getSystemService("layout_inflater"), i, j) {

            public View getView(int k, View view, ViewGroup viewgroup)
            {
                LocaleInfo localeinfo;
                if(view == null)
                {
                    view = inflater.inflate(layoutId, viewgroup, false);
                    viewgroup = (TextView)view.findViewById(fieldId);
                    view.setTag(viewgroup);
                } else
                {
                    View view1 = view;
                    viewgroup = (TextView)view.getTag();
                    view = view1;
                }
                localeinfo = (LocaleInfo)getItem(k);
                viewgroup.setText(localeinfo.toString());
                viewgroup.setTextLocale(localeinfo.getLocale());
                return view;
            }

            final int val$fieldId;
            final LayoutInflater val$inflater;
            final int val$layoutId;

            
            {
                inflater = layoutinflater;
                layoutId = k;
                fieldId = l;
                super(context, i, j, alocaleinfo);
            }
        }
;
    }

    public static List getAllAssetLocales(Context context, boolean flag)
    {
        Resources resources = context.getResources();
        context = getSystemAssetLocales();
        Object obj = new ArrayList(context.length);
        Collections.addAll(((java.util.Collection) (obj)), context);
        if(!flag)
        {
            context = pseudoLocales;
            int i = 0;
            for(int j = context.length; i < j; i++)
                ((List) (obj)).remove(context[i]);

        }
        Collections.sort(((List) (obj)));
        context = resources.getStringArray(0x1070064);
        String as[] = resources.getStringArray(0x1070065);
        ArrayList arraylist = new ArrayList(((List) (obj)).size());
        obj = ((Iterable) (obj)).iterator();
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break;
            Locale locale = Locale.forLanguageTag(((String)((Iterator) (obj)).next()).replace('_', '-'));
            if(locale != null && !"und".equals(locale.getLanguage()) && !locale.getLanguage().isEmpty() && !locale.getCountry().isEmpty())
                if(arraylist.isEmpty())
                {
                    arraylist.add(new LocaleInfo(toTitleCase(locale.getDisplayLanguage(locale)), locale));
                } else
                {
                    LocaleInfo localeinfo = (LocaleInfo)arraylist.get(arraylist.size() - 1);
                    if(localeinfo.locale.getLanguage().equals(locale.getLanguage()) && localeinfo.locale.getLanguage().equals("zz") ^ true)
                    {
                        localeinfo.label = toTitleCase(getDisplayName(localeinfo.locale, context, as));
                        arraylist.add(new LocaleInfo(toTitleCase(getDisplayName(locale, context, as)), locale));
                    } else
                    {
                        arraylist.add(new LocaleInfo(toTitleCase(LocalePickerInjector.getDisplayLanguage(locale, context, as, toTitleCase(locale.getDisplayLanguage(locale)))), locale));
                    }
                }
        } while(true);
        Collections.sort(arraylist);
        return arraylist;
    }

    private static String getDisplayName(Locale locale, String as[], String as1[])
    {
        String s = locale.toString();
        for(int i = 0; i < as.length; i++)
            if(as[i].equals(s))
                return as1[i];

        return locale.getDisplayName(locale);
    }

    public static LocaleList getLocales()
    {
        LocaleList localelist;
        try
        {
            localelist = ActivityManager.getService().getConfiguration().getLocales();
        }
        catch(RemoteException remoteexception)
        {
            return LocaleList.getDefault();
        }
        return localelist;
    }

    public static String[] getPseudoLocales()
    {
        return pseudoLocales;
    }

    public static String[] getSupportedLocales(Context context)
    {
        return context.getResources().getStringArray(0x1070066);
    }

    public static String[] getSystemAssetLocales()
    {
        return Resources.getSystem().getAssets().getLocales();
    }

    private static String toTitleCase(String s)
    {
        if(s.length() == 0)
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static void updateLocale(Locale locale)
    {
        updateLocales(new LocaleList(new Locale[] {
            locale
        }));
    }

    public static void updateLocales(LocaleList localelist)
    {
        IActivityManager iactivitymanager = ActivityManager.getService();
        Configuration configuration = iactivitymanager.getConfiguration();
        configuration.setLocales(localelist);
        configuration.userSetLocale = true;
        iactivitymanager.updatePersistentConfiguration(configuration);
        BackupManager.dataChanged("com.android.providers.settings");
_L2:
        return;
        localelist;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        setListAdapter(constructAdapter(getActivity()));
    }

    public void onListItemClick(ListView listview, View view, int i, long l)
    {
        if(mListener != null)
        {
            listview = ((LocaleInfo)getListAdapter().getItem(i)).locale;
            mListener.onLocaleSelected(listview);
        }
    }

    public void onResume()
    {
        super.onResume();
        getListView().requestFocus();
    }

    public void setLocaleSelectionListener(LocaleSelectionListener localeselectionlistener)
    {
        mListener = localeselectionlistener;
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "LocalePicker";
    private static final String pseudoLocales[] = {
        "en-XA", "ar-XB"
    };
    LocaleSelectionListener mListener;

}
