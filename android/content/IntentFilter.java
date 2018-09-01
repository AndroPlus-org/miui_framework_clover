// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.*;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.util.*;
import org.xmlpull.v1.*;

// Referenced classes of package android.content:
//            Intent, ContentResolver

public class IntentFilter
    implements Parcelable
{
    public static final class AuthorityEntry
    {

        static String _2D_get0(AuthorityEntry authorityentry)
        {
            return authorityentry.mHost;
        }

        static int _2D_get1(AuthorityEntry authorityentry)
        {
            return authorityentry.mPort;
        }

        static boolean _2D_get2(AuthorityEntry authorityentry)
        {
            return authorityentry.mWild;
        }

        public boolean equals(Object obj)
        {
            if(obj instanceof AuthorityEntry)
                return match((AuthorityEntry)obj);
            else
                return false;
        }

        public String getHost()
        {
            return mOrigHost;
        }

        public int getPort()
        {
            return mPort;
        }

        public int match(Uri uri)
        {
            String s = uri.getHost();
            if(s == null)
                return -2;
            String s1 = s;
            if(mWild)
            {
                if(s.length() < mHost.length())
                    return -2;
                s1 = s.substring(s.length() - mHost.length());
            }
            if(s1.compareToIgnoreCase(mHost) != 0)
                return -2;
            if(mPort >= 0)
                return mPort == uri.getPort() ? 0x400000 : -2;
            else
                return 0x300000;
        }

        public boolean match(AuthorityEntry authorityentry)
        {
            if(mWild != authorityentry.mWild)
                return false;
            if(!mHost.equals(authorityentry.mHost))
                return false;
            return mPort == authorityentry.mPort;
        }

        void writeToParcel(Parcel parcel)
        {
            parcel.writeString(mOrigHost);
            parcel.writeString(mHost);
            int i;
            if(mWild)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(mPort);
        }

        private final String mHost;
        private final String mOrigHost;
        private final int mPort;
        private final boolean mWild;

        AuthorityEntry(Parcel parcel)
        {
            boolean flag = false;
            super();
            mOrigHost = parcel.readString();
            mHost = parcel.readString();
            if(parcel.readInt() != 0)
                flag = true;
            mWild = flag;
            mPort = parcel.readInt();
        }

        public AuthorityEntry(String s, String s1)
        {
            boolean flag = false;
            super();
            mOrigHost = s;
            boolean flag1 = flag;
            if(s.length() > 0)
            {
                flag1 = flag;
                if(s.charAt(0) == '*')
                    flag1 = true;
            }
            mWild = flag1;
            String s2 = s;
            if(mWild)
                s2 = s.substring(1).intern();
            mHost = s2;
            int i;
            if(s1 != null)
                i = Integer.parseInt(s1);
            else
                i = -1;
            mPort = i;
        }
    }

    public static class MalformedMimeTypeException extends AndroidException
    {

        public MalformedMimeTypeException()
        {
        }

        public MalformedMimeTypeException(String s)
        {
            super(s);
        }
    }


    public IntentFilter()
    {
        mCategories = null;
        mDataSchemes = null;
        mDataSchemeSpecificParts = null;
        mDataAuthorities = null;
        mDataPaths = null;
        mDataTypes = null;
        mHasPartialTypes = false;
        mPriority = 0;
        mActions = new ArrayList();
    }

    public IntentFilter(IntentFilter intentfilter)
    {
        mCategories = null;
        mDataSchemes = null;
        mDataSchemeSpecificParts = null;
        mDataAuthorities = null;
        mDataPaths = null;
        mDataTypes = null;
        mHasPartialTypes = false;
        mPriority = intentfilter.mPriority;
        mOrder = intentfilter.mOrder;
        mActions = new ArrayList(intentfilter.mActions);
        if(intentfilter.mCategories != null)
            mCategories = new ArrayList(intentfilter.mCategories);
        if(intentfilter.mDataTypes != null)
            mDataTypes = new ArrayList(intentfilter.mDataTypes);
        if(intentfilter.mDataSchemes != null)
            mDataSchemes = new ArrayList(intentfilter.mDataSchemes);
        if(intentfilter.mDataSchemeSpecificParts != null)
            mDataSchemeSpecificParts = new ArrayList(intentfilter.mDataSchemeSpecificParts);
        if(intentfilter.mDataAuthorities != null)
            mDataAuthorities = new ArrayList(intentfilter.mDataAuthorities);
        if(intentfilter.mDataPaths != null)
            mDataPaths = new ArrayList(intentfilter.mDataPaths);
        mHasPartialTypes = intentfilter.mHasPartialTypes;
        mVerifyState = intentfilter.mVerifyState;
        mInstantAppVisibility = intentfilter.mInstantAppVisibility;
    }

    public IntentFilter(Parcel parcel)
    {
        boolean flag = true;
        super();
        mCategories = null;
        mDataSchemes = null;
        mDataSchemeSpecificParts = null;
        mDataAuthorities = null;
        mDataPaths = null;
        mDataTypes = null;
        mHasPartialTypes = false;
        mActions = new ArrayList();
        parcel.readStringList(mActions);
        if(parcel.readInt() != 0)
        {
            mCategories = new ArrayList();
            parcel.readStringList(mCategories);
        }
        if(parcel.readInt() != 0)
        {
            mDataSchemes = new ArrayList();
            parcel.readStringList(mDataSchemes);
        }
        if(parcel.readInt() != 0)
        {
            mDataTypes = new ArrayList();
            parcel.readStringList(mDataTypes);
        }
        int i = parcel.readInt();
        if(i > 0)
        {
            mDataSchemeSpecificParts = new ArrayList(i);
            for(int j = 0; j < i; j++)
                mDataSchemeSpecificParts.add(new PatternMatcher(parcel));

        }
        i = parcel.readInt();
        if(i > 0)
        {
            mDataAuthorities = new ArrayList(i);
            for(int k = 0; k < i; k++)
                mDataAuthorities.add(new AuthorityEntry(parcel));

        }
        i = parcel.readInt();
        if(i > 0)
        {
            mDataPaths = new ArrayList(i);
            for(int l = 0; l < i; l++)
                mDataPaths.add(new PatternMatcher(parcel));

        }
        mPriority = parcel.readInt();
        boolean flag1;
        if(parcel.readInt() > 0)
            flag1 = true;
        else
            flag1 = false;
        mHasPartialTypes = flag1;
        if(parcel.readInt() > 0)
            flag1 = flag;
        else
            flag1 = false;
        setAutoVerify(flag1);
        setVisibilityToInstantApp(parcel.readInt());
    }

    public IntentFilter(String s)
    {
        mCategories = null;
        mDataSchemes = null;
        mDataSchemeSpecificParts = null;
        mDataAuthorities = null;
        mDataPaths = null;
        mDataTypes = null;
        mHasPartialTypes = false;
        mPriority = 0;
        mActions = new ArrayList();
        addAction(s);
    }

    public IntentFilter(String s, String s1)
        throws MalformedMimeTypeException
    {
        mCategories = null;
        mDataSchemes = null;
        mDataSchemeSpecificParts = null;
        mDataAuthorities = null;
        mDataPaths = null;
        mDataTypes = null;
        mHasPartialTypes = false;
        mPriority = 0;
        mActions = new ArrayList();
        addAction(s);
        addDataType(s1);
    }

    private static String[] addStringToSet(String as[], String s, int ai[], int i)
    {
        if(findStringInSet(as, s, ai, i) >= 0)
            return as;
        if(as == null)
        {
            as = new String[2];
            as[0] = s;
            ai[i] = 1;
            return as;
        }
        int j = ai[i];
        if(j < as.length)
        {
            as[j] = s;
            ai[i] = j + 1;
            return as;
        } else
        {
            String as1[] = new String[(j * 3) / 2 + 2];
            System.arraycopy(as, 0, as1, 0, j);
            as1[j] = s;
            ai[i] = j + 1;
            return as1;
        }
    }

    public static IntentFilter create(String s, String s1)
    {
        try
        {
            s = new IntentFilter(s, s1);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("Bad MIME type", s);
        }
        return s;
    }

    private final boolean findMimeType(String s)
    {
        ArrayList arraylist = mDataTypes;
        if(s == null)
            return false;
        if(arraylist.contains(s))
            return true;
        int i = s.length();
        if(i == 3 && s.equals("*/*"))
            return arraylist.isEmpty() ^ true;
        if(mHasPartialTypes && arraylist.contains("*"))
            return true;
        int k = s.indexOf('/');
        if(k > 0)
        {
            if(mHasPartialTypes && arraylist.contains(s.substring(0, k)))
                return true;
            if(i == k + 2 && s.charAt(k + 1) == '*')
            {
                int l = arraylist.size();
                for(int j = 0; j < l; j++)
                    if(s.regionMatches(0, (String)arraylist.get(j), 0, k + 1))
                        return true;

            }
        }
        return false;
    }

    private static int findStringInSet(String as[], String s, int ai[], int i)
    {
        if(as == null)
            return -1;
        int j = ai[i];
        for(i = 0; i < j; i++)
            if(as[i].equals(s))
                return i;

        return -1;
    }

    private static String[] removeStringFromSet(String as[], String s, int ai[], int i)
    {
        int j = findStringInSet(as, s, ai, i);
        if(j < 0)
            return as;
        int k = ai[i];
        if(k > as.length / 4)
        {
            int l = k - (j + 1);
            if(l > 0)
                System.arraycopy(as, j + 1, as, j, l);
            as[k - 1] = null;
            ai[i] = k - 1;
            return as;
        }
        s = new String[as.length / 3];
        if(j > 0)
            System.arraycopy(as, 0, s, 0, j);
        if(j + 1 < k)
            System.arraycopy(as, j + 1, s, j, k - (j + 1));
        return s;
    }

    public final Iterator actionsIterator()
    {
        Iterator iterator = null;
        if(mActions != null)
            iterator = mActions.iterator();
        return iterator;
    }

    public final void addAction(String s)
    {
        if(!mActions.contains(s))
            mActions.add(s.intern());
    }

    public final void addCategory(String s)
    {
        if(mCategories == null)
            mCategories = new ArrayList();
        if(!mCategories.contains(s))
            mCategories.add(s.intern());
    }

    public final void addDataAuthority(AuthorityEntry authorityentry)
    {
        if(mDataAuthorities == null)
            mDataAuthorities = new ArrayList();
        mDataAuthorities.add(authorityentry);
    }

    public final void addDataAuthority(String s, String s1)
    {
        String s2 = s1;
        if(s1 != null)
            s2 = s1.intern();
        addDataAuthority(new AuthorityEntry(s.intern(), s2));
    }

    public final void addDataPath(PatternMatcher patternmatcher)
    {
        if(mDataPaths == null)
            mDataPaths = new ArrayList();
        mDataPaths.add(patternmatcher);
    }

    public final void addDataPath(String s, int i)
    {
        addDataPath(new PatternMatcher(s.intern(), i));
    }

    public final void addDataScheme(String s)
    {
        if(mDataSchemes == null)
            mDataSchemes = new ArrayList();
        if(!mDataSchemes.contains(s))
            mDataSchemes.add(s.intern());
    }

    public final void addDataSchemeSpecificPart(PatternMatcher patternmatcher)
    {
        if(mDataSchemeSpecificParts == null)
            mDataSchemeSpecificParts = new ArrayList();
        mDataSchemeSpecificParts.add(patternmatcher);
    }

    public final void addDataSchemeSpecificPart(String s, int i)
    {
        addDataSchemeSpecificPart(new PatternMatcher(s, i));
    }

    public final void addDataType(String s)
        throws MalformedMimeTypeException
    {
        int i;
        int j;
        i = s.indexOf('/');
        j = s.length();
        if(i <= 0 || j < i + 2) goto _L2; else goto _L1
_L1:
        if(mDataTypes == null)
            mDataTypes = new ArrayList();
        if(j != i + 2 || s.charAt(i + 1) != '*') goto _L4; else goto _L3
_L3:
        s = s.substring(0, i);
        if(!mDataTypes.contains(s))
            mDataTypes.add(s.intern());
        mHasPartialTypes = true;
_L5:
        return;
_L4:
        if(!mDataTypes.contains(s))
            mDataTypes.add(s.intern());
        if(true) goto _L5; else goto _L2
_L2:
        throw new MalformedMimeTypeException(s);
    }

    public final Iterator authoritiesIterator()
    {
        Iterator iterator = null;
        if(mDataAuthorities != null)
            iterator = mDataAuthorities.iterator();
        return iterator;
    }

    public final Iterator categoriesIterator()
    {
        Iterator iterator = null;
        if(mCategories != null)
            iterator = mCategories.iterator();
        return iterator;
    }

    public final int countActions()
    {
        return mActions.size();
    }

    public final int countCategories()
    {
        int i;
        if(mCategories != null)
            i = mCategories.size();
        else
            i = 0;
        return i;
    }

    public final int countDataAuthorities()
    {
        int i;
        if(mDataAuthorities != null)
            i = mDataAuthorities.size();
        else
            i = 0;
        return i;
    }

    public final int countDataPaths()
    {
        int i;
        if(mDataPaths != null)
            i = mDataPaths.size();
        else
            i = 0;
        return i;
    }

    public final int countDataSchemeSpecificParts()
    {
        int i;
        if(mDataSchemeSpecificParts != null)
            i = mDataSchemeSpecificParts.size();
        else
            i = 0;
        return i;
    }

    public final int countDataSchemes()
    {
        int i;
        if(mDataSchemes != null)
            i = mDataSchemes.size();
        else
            i = 0;
        return i;
    }

    public final int countDataTypes()
    {
        int i;
        if(mDataTypes != null)
            i = mDataTypes.size();
        else
            i = 0;
        return i;
    }

    public boolean debugCheck()
    {
        return true;
    }

    public final int describeContents()
    {
        return 0;
    }

    public void dump(Printer printer, String s)
    {
        StringBuilder stringbuilder = new StringBuilder(256);
        if(mActions.size() > 0)
        {
            for(Iterator iterator = mActions.iterator(); iterator.hasNext(); printer.println(stringbuilder.toString()))
            {
                stringbuilder.setLength(0);
                stringbuilder.append(s);
                stringbuilder.append("Action: \"");
                stringbuilder.append((String)iterator.next());
                stringbuilder.append("\"");
            }

        }
        if(mCategories != null)
        {
            for(Iterator iterator1 = mCategories.iterator(); iterator1.hasNext(); printer.println(stringbuilder.toString()))
            {
                stringbuilder.setLength(0);
                stringbuilder.append(s);
                stringbuilder.append("Category: \"");
                stringbuilder.append((String)iterator1.next());
                stringbuilder.append("\"");
            }

        }
        if(mDataSchemes != null)
        {
            for(Iterator iterator2 = mDataSchemes.iterator(); iterator2.hasNext(); printer.println(stringbuilder.toString()))
            {
                stringbuilder.setLength(0);
                stringbuilder.append(s);
                stringbuilder.append("Scheme: \"");
                stringbuilder.append((String)iterator2.next());
                stringbuilder.append("\"");
            }

        }
        if(mDataSchemeSpecificParts != null)
        {
            for(Iterator iterator5 = mDataSchemeSpecificParts.iterator(); iterator5.hasNext(); printer.println(stringbuilder.toString()))
            {
                PatternMatcher patternmatcher = (PatternMatcher)iterator5.next();
                stringbuilder.setLength(0);
                stringbuilder.append(s);
                stringbuilder.append("Ssp: \"");
                stringbuilder.append(patternmatcher);
                stringbuilder.append("\"");
            }

        }
        if(mDataAuthorities != null)
        {
            for(Iterator iterator3 = mDataAuthorities.iterator(); iterator3.hasNext(); printer.println(stringbuilder.toString()))
            {
                AuthorityEntry authorityentry = (AuthorityEntry)iterator3.next();
                stringbuilder.setLength(0);
                stringbuilder.append(s);
                stringbuilder.append("Authority: \"");
                stringbuilder.append(AuthorityEntry._2D_get0(authorityentry));
                stringbuilder.append("\": ");
                stringbuilder.append(AuthorityEntry._2D_get1(authorityentry));
                if(AuthorityEntry._2D_get2(authorityentry))
                    stringbuilder.append(" WILD");
            }

        }
        if(mDataPaths != null)
        {
            for(Iterator iterator6 = mDataPaths.iterator(); iterator6.hasNext(); printer.println(stringbuilder.toString()))
            {
                PatternMatcher patternmatcher1 = (PatternMatcher)iterator6.next();
                stringbuilder.setLength(0);
                stringbuilder.append(s);
                stringbuilder.append("Path: \"");
                stringbuilder.append(patternmatcher1);
                stringbuilder.append("\"");
            }

        }
        if(mDataTypes != null)
        {
            for(Iterator iterator4 = mDataTypes.iterator(); iterator4.hasNext(); printer.println(stringbuilder.toString()))
            {
                stringbuilder.setLength(0);
                stringbuilder.append(s);
                stringbuilder.append("Type: \"");
                stringbuilder.append((String)iterator4.next());
                stringbuilder.append("\"");
            }

        }
        if(mPriority != 0 || mHasPartialTypes)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("mPriority=");
            stringbuilder.append(mPriority);
            stringbuilder.append(", mHasPartialTypes=");
            stringbuilder.append(mHasPartialTypes);
            printer.println(stringbuilder.toString());
        }
        if(getAutoVerify())
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("AutoVerify=");
            stringbuilder.append(getAutoVerify());
            printer.println(stringbuilder.toString());
        }
    }

    public final String getAction(int i)
    {
        return (String)mActions.get(i);
    }

    public final boolean getAutoVerify()
    {
        boolean flag = true;
        if((mVerifyState & 1) != 1)
            flag = false;
        return flag;
    }

    public final String getCategory(int i)
    {
        return (String)mCategories.get(i);
    }

    public final AuthorityEntry getDataAuthority(int i)
    {
        return (AuthorityEntry)mDataAuthorities.get(i);
    }

    public final PatternMatcher getDataPath(int i)
    {
        return (PatternMatcher)mDataPaths.get(i);
    }

    public final String getDataScheme(int i)
    {
        return (String)mDataSchemes.get(i);
    }

    public final PatternMatcher getDataSchemeSpecificPart(int i)
    {
        return (PatternMatcher)mDataSchemeSpecificParts.get(i);
    }

    public final String getDataType(int i)
    {
        return (String)mDataTypes.get(i);
    }

    public String[] getHosts()
    {
        ArrayList arraylist = getHostsList();
        return (String[])arraylist.toArray(new String[arraylist.size()]);
    }

    public ArrayList getHostsList()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = authoritiesIterator();
        if(iterator != null)
            for(; iterator.hasNext(); arraylist.add(((AuthorityEntry)iterator.next()).getHost()));
        return arraylist;
    }

    public final int getOrder()
    {
        return mOrder;
    }

    public final int getPriority()
    {
        return mPriority;
    }

    public int getVisibilityToInstantApp()
    {
        return mInstantAppVisibility;
    }

    public final boolean handleAllWebDataURI()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(!hasCategory("android.intent.category.APP_BROWSER"))
            if(handlesWebUris(false) && countDataAuthorities() == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public final boolean handlesWebUris(boolean flag)
    {
        while(!hasAction("android.intent.action.VIEW") || hasCategory("android.intent.category.BROWSABLE") ^ true || mDataSchemes == null || mDataSchemes.size() == 0) 
            return false;
        int i = mDataSchemes.size();
        for(int j = 0; j < i; j++)
        {
            String s = (String)mDataSchemes.get(j);
            boolean flag1;
            if(!"http".equals(s))
                flag1 = "https".equals(s);
            else
                flag1 = true;
            if(flag)
            {
                if(!flag1)
                    return false;
                continue;
            }
            if(flag1)
                return true;
        }

        return flag;
    }

    public final boolean hasAction(String s)
    {
        boolean flag;
        if(s != null)
            flag = mActions.contains(s);
        else
            flag = false;
        return flag;
    }

    public final boolean hasCategory(String s)
    {
        boolean flag;
        if(mCategories != null)
            flag = mCategories.contains(s);
        else
            flag = false;
        return flag;
    }

    public final boolean hasDataAuthority(AuthorityEntry authorityentry)
    {
        if(mDataAuthorities == null)
            return false;
        int i = mDataAuthorities.size();
        for(int j = 0; j < i; j++)
            if(((AuthorityEntry)mDataAuthorities.get(j)).match(authorityentry))
                return true;

        return false;
    }

    public final boolean hasDataAuthority(Uri uri)
    {
        boolean flag = false;
        if(matchDataAuthority(uri) >= 0)
            flag = true;
        return flag;
    }

    public final boolean hasDataPath(PatternMatcher patternmatcher)
    {
        if(mDataPaths == null)
            return false;
        int i = mDataPaths.size();
        for(int j = 0; j < i; j++)
        {
            PatternMatcher patternmatcher1 = (PatternMatcher)mDataPaths.get(j);
            if(patternmatcher1.getType() == patternmatcher.getType() && patternmatcher1.getPath().equals(patternmatcher.getPath()))
                return true;
        }

        return false;
    }

    public final boolean hasDataPath(String s)
    {
        if(mDataPaths == null)
            return false;
        int i = mDataPaths.size();
        for(int j = 0; j < i; j++)
            if(((PatternMatcher)mDataPaths.get(j)).match(s))
                return true;

        return false;
    }

    public final boolean hasDataScheme(String s)
    {
        boolean flag;
        if(mDataSchemes != null)
            flag = mDataSchemes.contains(s);
        else
            flag = false;
        return flag;
    }

    public final boolean hasDataSchemeSpecificPart(PatternMatcher patternmatcher)
    {
        if(mDataSchemeSpecificParts == null)
            return false;
        int i = mDataSchemeSpecificParts.size();
        for(int j = 0; j < i; j++)
        {
            PatternMatcher patternmatcher1 = (PatternMatcher)mDataSchemeSpecificParts.get(j);
            if(patternmatcher1.getType() == patternmatcher.getType() && patternmatcher1.getPath().equals(patternmatcher.getPath()))
                return true;
        }

        return false;
    }

    public final boolean hasDataSchemeSpecificPart(String s)
    {
        if(mDataSchemeSpecificParts == null)
            return false;
        int i = mDataSchemeSpecificParts.size();
        for(int j = 0; j < i; j++)
            if(((PatternMatcher)mDataSchemeSpecificParts.get(j)).match(s))
                return true;

        return false;
    }

    public final boolean hasDataType(String s)
    {
        boolean flag;
        if(mDataTypes != null)
            flag = findMimeType(s);
        else
            flag = false;
        return flag;
    }

    public final boolean hasExactDataType(String s)
    {
        boolean flag;
        if(mDataTypes != null)
            flag = mDataTypes.contains(s);
        else
            flag = false;
        return flag;
    }

    public boolean isExplicitlyVisibleToInstantApp()
    {
        boolean flag = true;
        if(mInstantAppVisibility != 1)
            flag = false;
        return flag;
    }

    public boolean isImplicitlyVisibleToInstantApp()
    {
        boolean flag;
        if(mInstantAppVisibility == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public final boolean isVerified()
    {
        boolean flag = false;
        if((mVerifyState & 0x100) == 256)
        {
            if((mVerifyState & 0x10) == 16)
                flag = true;
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean isVisibleToInstantApp()
    {
        boolean flag = false;
        if(mInstantAppVisibility != 0)
            flag = true;
        return flag;
    }

    public final int match(ContentResolver contentresolver, Intent intent, boolean flag, String s)
    {
        if(flag)
            contentresolver = intent.resolveType(contentresolver);
        else
            contentresolver = intent.getType();
        return match(intent.getAction(), ((String) (contentresolver)), intent.getScheme(), intent.getData(), intent.getCategories(), s);
    }

    public final int match(String s, String s1, String s2, Uri uri, Set set, String s3)
    {
        if(s != null && matchAction(s) ^ true)
            return -3;
        int i = matchData(s1, s2, uri);
        if(i < 0)
            return i;
        if(matchCategories(set) != null)
            return -4;
        else
            return i;
    }

    public final boolean matchAction(String s)
    {
        return hasAction(s);
    }

    public final String matchCategories(Set set)
    {
        Object obj = null;
        if(set == null)
            return null;
        Iterator iterator = set.iterator();
        if(mCategories == null)
        {
            set = obj;
            if(iterator.hasNext())
                set = (String)iterator.next();
            return set;
        }
        while(iterator.hasNext()) 
        {
            set = (String)iterator.next();
            if(!mCategories.contains(set))
                return set;
        }
        return null;
    }

    public final int matchData(String s, String s1, Uri uri)
    {
        ArrayList arraylist;
        int j;
        int l;
        byte byte0 = -2;
        arraylist = mDataTypes;
        ArrayList arraylist1 = mDataSchemes;
        j = 0x100000;
        if(arraylist == null && arraylist1 == null)
        {
            int k = byte0;
            if(s == null)
            {
                k = byte0;
                if(uri == null)
                    k = 0x108000;
            }
            return k;
        }
        if(arraylist1 == null)
            break MISSING_BLOCK_LABEL_206;
        if(s1 == null)
            s1 = "";
        if(arraylist1.contains(s1))
        {
            int i = 0x200000;
            l = i;
            if(mDataSchemeSpecificParts != null)
            {
                l = i;
                if(uri != null)
                    if(hasDataSchemeSpecificPart(uri.getSchemeSpecificPart()))
                        l = 0x580000;
                    else
                        l = -2;
            }
            i = l;
            if(l != 0x580000)
            {
                i = l;
                if(mDataAuthorities != null)
                {
                    i = matchDataAuthority(uri);
                    if(i < 0)
                        break MISSING_BLOCK_LABEL_203;
                    if(mDataPaths != null)
                        if(hasDataPath(uri.getPath()))
                            i = 0x500000;
                        else
                            return -2;
                }
            }
            l = i;
            if(i == -2)
                return -2;
            break MISSING_BLOCK_LABEL_265;
        } else
        {
            return -2;
        }
        return -2;
        l = j;
        if(s1 != null)
        {
            l = j;
            if("".equals(s1) ^ true)
            {
                l = j;
                if("content".equals(s1) ^ true)
                {
                    l = j;
                    if("file".equals(s1) ^ true)
                        return -2;
                }
            }
        }
        if(arraylist == null) goto _L2; else goto _L1
_L1:
        if(!findMimeType(s)) goto _L4; else goto _L3
_L3:
        l = 0x600000;
_L6:
        return 32768 + l;
_L4:
        return -1;
_L2:
        if(s != null)
            return -1;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public final int matchDataAuthority(Uri uri)
    {
        if(mDataAuthorities == null || uri == null)
            return -2;
        int i = mDataAuthorities.size();
        for(int j = 0; j < i; j++)
        {
            int k = ((AuthorityEntry)mDataAuthorities.get(j)).match(uri);
            if(k >= 0)
                return k;
        }

        return -2;
    }

    public final boolean needsVerification()
    {
        boolean flag;
        if(getAutoVerify())
            flag = handlesWebUris(true);
        else
            flag = false;
        return flag;
    }

    public final Iterator pathsIterator()
    {
        Iterator iterator = null;
        if(mDataPaths != null)
            iterator = mDataPaths.iterator();
        return iterator;
    }

    public void readFromXml(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        Object obj = xmlpullparser.getAttributeValue(null, "autoVerify");
        boolean flag;
        int i;
        if(TextUtils.isEmpty(((CharSequence) (obj))))
            flag = false;
        else
            flag = Boolean.getBoolean(((String) (obj)));
        setAutoVerify(flag);
        i = xmlpullparser.getDepth();
        do
        {
            int j = xmlpullparser.next();
            if(j != 1 && (j != 3 || xmlpullparser.getDepth() > i))
            {
                if(j != 3 && j != 4)
                {
                    obj = xmlpullparser.getName();
                    if(((String) (obj)).equals("action"))
                    {
                        obj = xmlpullparser.getAttributeValue(null, "name");
                        if(obj != null)
                            addAction(((String) (obj)));
                    } else
                    if(((String) (obj)).equals("cat"))
                    {
                        obj = xmlpullparser.getAttributeValue(null, "name");
                        if(obj != null)
                            addCategory(((String) (obj)));
                    } else
                    if(((String) (obj)).equals("type"))
                    {
                        obj = xmlpullparser.getAttributeValue(null, "name");
                        if(obj != null)
                            try
                            {
                                addDataType(((String) (obj)));
                            }
                            // Misplaced declaration of an exception variable
                            catch(Object obj) { }
                    } else
                    if(((String) (obj)).equals("scheme"))
                    {
                        obj = xmlpullparser.getAttributeValue(null, "name");
                        if(obj != null)
                            addDataScheme(((String) (obj)));
                    } else
                    if(((String) (obj)).equals("ssp"))
                    {
                        obj = xmlpullparser.getAttributeValue(null, "literal");
                        if(obj != null)
                        {
                            addDataSchemeSpecificPart(((String) (obj)), 0);
                        } else
                        {
                            obj = xmlpullparser.getAttributeValue(null, "prefix");
                            if(obj != null)
                            {
                                addDataSchemeSpecificPart(((String) (obj)), 1);
                            } else
                            {
                                obj = xmlpullparser.getAttributeValue(null, "sglob");
                                if(obj != null)
                                {
                                    addDataSchemeSpecificPart(((String) (obj)), 2);
                                } else
                                {
                                    obj = xmlpullparser.getAttributeValue(null, "aglob");
                                    if(obj != null)
                                        addDataSchemeSpecificPart(((String) (obj)), 3);
                                }
                            }
                        }
                    } else
                    if(((String) (obj)).equals("auth"))
                    {
                        obj = xmlpullparser.getAttributeValue(null, "host");
                        String s = xmlpullparser.getAttributeValue(null, "port");
                        if(obj != null)
                            addDataAuthority(((String) (obj)), s);
                    } else
                    if(((String) (obj)).equals("path"))
                    {
                        obj = xmlpullparser.getAttributeValue(null, "literal");
                        if(obj != null)
                        {
                            addDataPath(((String) (obj)), 0);
                        } else
                        {
                            obj = xmlpullparser.getAttributeValue(null, "prefix");
                            if(obj != null)
                            {
                                addDataPath(((String) (obj)), 1);
                            } else
                            {
                                obj = xmlpullparser.getAttributeValue(null, "sglob");
                                if(obj != null)
                                {
                                    addDataPath(((String) (obj)), 2);
                                } else
                                {
                                    obj = xmlpullparser.getAttributeValue(null, "aglob");
                                    if(obj != null)
                                        addDataPath(((String) (obj)), 3);
                                }
                            }
                        }
                    } else
                    {
                        Log.w("IntentFilter", (new StringBuilder()).append("Unknown tag parsing IntentFilter: ").append(((String) (obj))).toString());
                    }
                    XmlUtils.skipCurrentTag(xmlpullparser);
                }
            } else
            {
                return;
            }
        } while(true);
    }

    public final Iterator schemeSpecificPartsIterator()
    {
        Iterator iterator = null;
        if(mDataSchemeSpecificParts != null)
            iterator = mDataSchemeSpecificParts.iterator();
        return iterator;
    }

    public final Iterator schemesIterator()
    {
        Iterator iterator = null;
        if(mDataSchemes != null)
            iterator = mDataSchemes.iterator();
        return iterator;
    }

    public final void setAutoVerify(boolean flag)
    {
        mVerifyState = mVerifyState & -2;
        if(flag)
            mVerifyState = mVerifyState | 1;
    }

    public final void setOrder(int i)
    {
        mOrder = i;
    }

    public final void setPriority(int i)
    {
        mPriority = i;
    }

    public void setVerified(boolean flag)
    {
        mVerifyState = mVerifyState | 0x100;
        mVerifyState = mVerifyState & 0xffffefff;
        if(flag)
            mVerifyState = mVerifyState | 0x1000;
    }

    public void setVisibilityToInstantApp(int i)
    {
        mInstantAppVisibility = i;
    }

    public final Iterator typesIterator()
    {
        Iterator iterator = null;
        if(mDataTypes != null)
            iterator = mDataTypes.iterator();
        return iterator;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeStringList(mActions);
        if(mCategories != null)
        {
            parcel.writeInt(1);
            parcel.writeStringList(mCategories);
        } else
        {
            parcel.writeInt(0);
        }
        if(mDataSchemes != null)
        {
            parcel.writeInt(1);
            parcel.writeStringList(mDataSchemes);
        } else
        {
            parcel.writeInt(0);
        }
        if(mDataTypes != null)
        {
            parcel.writeInt(1);
            parcel.writeStringList(mDataTypes);
        } else
        {
            parcel.writeInt(0);
        }
        if(mDataSchemeSpecificParts != null)
        {
            int j = mDataSchemeSpecificParts.size();
            parcel.writeInt(j);
            for(int i1 = 0; i1 < j; i1++)
                ((PatternMatcher)mDataSchemeSpecificParts.get(i1)).writeToParcel(parcel, i);

        } else
        {
            parcel.writeInt(0);
        }
        if(mDataAuthorities != null)
        {
            int k = mDataAuthorities.size();
            parcel.writeInt(k);
            for(int j1 = 0; j1 < k; j1++)
                ((AuthorityEntry)mDataAuthorities.get(j1)).writeToParcel(parcel);

        } else
        {
            parcel.writeInt(0);
        }
        if(mDataPaths != null)
        {
            int l = mDataPaths.size();
            parcel.writeInt(l);
            for(int k1 = 0; k1 < l; k1++)
                ((PatternMatcher)mDataPaths.get(k1)).writeToParcel(parcel, i);

        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(mPriority);
        if(mHasPartialTypes)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(getAutoVerify())
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mInstantAppVisibility);
    }

    public void writeToXml(XmlSerializer xmlserializer)
        throws IOException
    {
        int i;
        int j1;
        if(getAutoVerify())
            xmlserializer.attribute(null, "autoVerify", Boolean.toString(true));
        i = countActions();
        for(int j = 0; j < i; j++)
        {
            xmlserializer.startTag(null, "action");
            xmlserializer.attribute(null, "name", (String)mActions.get(j));
            xmlserializer.endTag(null, "action");
        }

        i = countCategories();
        for(int k = 0; k < i; k++)
        {
            xmlserializer.startTag(null, "cat");
            xmlserializer.attribute(null, "name", (String)mCategories.get(k));
            xmlserializer.endTag(null, "cat");
        }

        i = countDataTypes();
        for(int l = 0; l < i; l++)
        {
            xmlserializer.startTag(null, "type");
            String s = (String)mDataTypes.get(l);
            String s1 = s;
            if(s.indexOf('/') < 0)
                s1 = (new StringBuilder()).append(s).append("/*").toString();
            xmlserializer.attribute(null, "name", s1);
            xmlserializer.endTag(null, "type");
        }

        i = countDataSchemes();
        for(int i1 = 0; i1 < i; i1++)
        {
            xmlserializer.startTag(null, "scheme");
            xmlserializer.attribute(null, "name", (String)mDataSchemes.get(i1));
            xmlserializer.endTag(null, "scheme");
        }

        i = countDataSchemeSpecificParts();
        j1 = 0;
_L7:
        Object obj;
        if(j1 >= i)
            break MISSING_BLOCK_LABEL_456;
        xmlserializer.startTag(null, "ssp");
        obj = (PatternMatcher)mDataSchemeSpecificParts.get(j1);
        ((PatternMatcher) (obj)).getType();
        JVM INSTR tableswitch 0 3: default 368
    //                   0 384
    //                   1 402
    //                   2 420
    //                   3 438;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_438;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L8:
        xmlserializer.endTag(null, "ssp");
        j1++;
        if(true) goto _L7; else goto _L6
_L6:
        xmlserializer.attribute(null, "literal", ((PatternMatcher) (obj)).getPath());
          goto _L8
_L3:
        xmlserializer.attribute(null, "prefix", ((PatternMatcher) (obj)).getPath());
          goto _L8
_L4:
        xmlserializer.attribute(null, "sglob", ((PatternMatcher) (obj)).getPath());
          goto _L8
        xmlserializer.attribute(null, "aglob", ((PatternMatcher) (obj)).getPath());
          goto _L8
        i = countDataAuthorities();
        for(j1 = 0; j1 < i; j1++)
        {
            xmlserializer.startTag(null, "auth");
            obj = (AuthorityEntry)mDataAuthorities.get(j1);
            xmlserializer.attribute(null, "host", ((AuthorityEntry) (obj)).getHost());
            if(((AuthorityEntry) (obj)).getPort() >= 0)
                xmlserializer.attribute(null, "port", Integer.toString(((AuthorityEntry) (obj)).getPort()));
            xmlserializer.endTag(null, "auth");
        }

        i = countDataPaths();
        j1 = 0;
_L15:
        if(j1 >= i)
            break MISSING_BLOCK_LABEL_708;
        xmlserializer.startTag(null, "path");
        obj = (PatternMatcher)mDataPaths.get(j1);
        ((PatternMatcher) (obj)).getType();
        JVM INSTR tableswitch 0 3: default 620
    //                   0 636
    //                   1 654
    //                   2 672
    //                   3 690;
           goto _L9 _L10 _L11 _L12 _L13
_L13:
        break MISSING_BLOCK_LABEL_690;
_L9:
        break; /* Loop/switch isn't completed */
_L10:
        break; /* Loop/switch isn't completed */
_L16:
        xmlserializer.endTag(null, "path");
        j1++;
        if(true) goto _L15; else goto _L14
_L14:
        xmlserializer.attribute(null, "literal", ((PatternMatcher) (obj)).getPath());
          goto _L16
_L11:
        xmlserializer.attribute(null, "prefix", ((PatternMatcher) (obj)).getPath());
          goto _L16
_L12:
        xmlserializer.attribute(null, "sglob", ((PatternMatcher) (obj)).getPath());
          goto _L16
        xmlserializer.attribute(null, "aglob", ((PatternMatcher) (obj)).getPath());
          goto _L16
    }

    private static final String ACTION_STR = "action";
    private static final String AGLOB_STR = "aglob";
    private static final String AUTH_STR = "auth";
    private static final String AUTO_VERIFY_STR = "autoVerify";
    private static final String CAT_STR = "cat";
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public IntentFilter createFromParcel(Parcel parcel)
        {
            return new IntentFilter(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public IntentFilter[] newArray(int i)
        {
            return new IntentFilter[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String HOST_STR = "host";
    private static final String LITERAL_STR = "literal";
    public static final int MATCH_ADJUSTMENT_MASK = 65535;
    public static final int MATCH_ADJUSTMENT_NORMAL = 32768;
    public static final int MATCH_CATEGORY_EMPTY = 0x100000;
    public static final int MATCH_CATEGORY_HOST = 0x300000;
    public static final int MATCH_CATEGORY_MASK = 0xfff0000;
    public static final int MATCH_CATEGORY_PATH = 0x500000;
    public static final int MATCH_CATEGORY_PORT = 0x400000;
    public static final int MATCH_CATEGORY_SCHEME = 0x200000;
    public static final int MATCH_CATEGORY_SCHEME_SPECIFIC_PART = 0x580000;
    public static final int MATCH_CATEGORY_TYPE = 0x600000;
    private static final String NAME_STR = "name";
    public static final int NO_MATCH_ACTION = -3;
    public static final int NO_MATCH_CATEGORY = -4;
    public static final int NO_MATCH_DATA = -2;
    public static final int NO_MATCH_TYPE = -1;
    private static final String PATH_STR = "path";
    private static final String PORT_STR = "port";
    private static final String PREFIX_STR = "prefix";
    public static final String SCHEME_HTTP = "http";
    public static final String SCHEME_HTTPS = "https";
    private static final String SCHEME_STR = "scheme";
    private static final String SGLOB_STR = "sglob";
    private static final String SSP_STR = "ssp";
    private static final int STATE_NEED_VERIFY = 16;
    private static final int STATE_NEED_VERIFY_CHECKED = 256;
    private static final int STATE_VERIFIED = 4096;
    private static final int STATE_VERIFY_AUTO = 1;
    public static final int SYSTEM_HIGH_PRIORITY = 1000;
    public static final int SYSTEM_LOW_PRIORITY = -1000;
    private static final String TYPE_STR = "type";
    public static final int VISIBILITY_EXPLICIT = 1;
    public static final int VISIBILITY_IMPLICIT = 2;
    public static final int VISIBILITY_NONE = 0;
    private final ArrayList mActions;
    private ArrayList mCategories;
    private ArrayList mDataAuthorities;
    private ArrayList mDataPaths;
    private ArrayList mDataSchemeSpecificParts;
    private ArrayList mDataSchemes;
    private ArrayList mDataTypes;
    private boolean mHasPartialTypes;
    private int mInstantAppVisibility;
    private int mOrder;
    private int mPriority;
    private int mVerifyState;

}
