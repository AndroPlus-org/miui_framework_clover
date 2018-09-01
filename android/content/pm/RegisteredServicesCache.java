// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.*;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.*;
import android.util.*;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.FastXmlSerializer;
import com.google.android.collect.Lists;
import com.google.android.collect.Maps;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import libcore.io.IoUtils;
import org.xmlpull.v1.*;

// Referenced classes of package android.content.pm:
//            UserInfo, ResolveInfo, XmlSerializerAndParser, PackageManager, 
//            PackageInfo, ApplicationInfo, ServiceInfo, ComponentInfo, 
//            RegisteredServicesCacheListener

public abstract class RegisteredServicesCache
{
    public static class ServiceInfo
    {

        public String toString()
        {
            return (new StringBuilder()).append("ServiceInfo: ").append(type).append(", ").append(componentName).append(", uid ").append(uid).toString();
        }

        public final ComponentInfo componentInfo;
        public final ComponentName componentName;
        public final Object type;
        public final int uid;

        public ServiceInfo(Object obj, ComponentInfo componentinfo, ComponentName componentname)
        {
            type = obj;
            componentInfo = componentinfo;
            componentName = componentname;
            int i;
            if(componentinfo != null)
                i = componentinfo.applicationInfo.uid;
            else
                i = -1;
            uid = i;
        }
    }

    private static class UserServices
    {

        boolean mPersistentServicesFileDidNotExist;
        final Map persistentServices;
        Map services;

        private UserServices()
        {
            persistentServices = Maps.newHashMap();
            services = null;
            mPersistentServicesFileDidNotExist = true;
        }

        UserServices(UserServices userservices)
        {
            this();
        }
    }


    static void _2D_wrap0(RegisteredServicesCache registeredservicescache, Intent intent, int i)
    {
        registeredservicescache.handlePackageEvent(intent, i);
    }

    public RegisteredServicesCache(Context context, String s, String s1, String s2, XmlSerializerAndParser xmlserializerandparser)
    {
        mContext = context;
        mInterfaceName = s;
        mMetaDataName = s1;
        mAttributesName = s2;
        mSerializerAndParser = xmlserializerandparser;
        migrateIfNecessaryLocked();
        context = new IntentFilter();
        context.addAction("android.intent.action.PACKAGE_ADDED");
        context.addAction("android.intent.action.PACKAGE_CHANGED");
        context.addAction("android.intent.action.PACKAGE_REMOVED");
        context.addDataScheme("package");
        mContext.registerReceiverAsUser(mPackageReceiver, UserHandle.ALL, context, null, null);
        context = new IntentFilter();
        context.addAction("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
        context.addAction("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
        mContext.registerReceiver(mExternalReceiver, context);
        s = new IntentFilter();
        context.addAction("android.intent.action.USER_REMOVED");
        mContext.registerReceiver(mUserRemovedReceiver, s);
    }

    private boolean containsType(ArrayList arraylist, Object obj)
    {
        int i = 0;
        for(int j = arraylist.size(); i < j; i++)
            if(((ServiceInfo)arraylist.get(i)).type.equals(obj))
                return true;

        return false;
    }

    private boolean containsTypeAndUid(ArrayList arraylist, Object obj, int i)
    {
        int j = 0;
        for(int k = arraylist.size(); j < k; j++)
        {
            ServiceInfo serviceinfo = (ServiceInfo)arraylist.get(j);
            if(serviceinfo.type.equals(obj) && serviceinfo.uid == i)
                return true;
        }

        return false;
    }

    private boolean containsUid(int ai[], int i)
    {
        boolean flag;
        if(ai != null)
            flag = ArrayUtils.contains(ai, i);
        else
            flag = true;
        return flag;
    }

    private AtomicFile createFileForUser(int i)
    {
        return new AtomicFile(new File(getUserSystemDirectory(i), (new StringBuilder()).append("registered_services/").append(mInterfaceName).append(".xml").toString()));
    }

    private UserServices findOrCreateUserLocked(int i)
    {
        return findOrCreateUserLocked(i, true);
    }

    private UserServices findOrCreateUserLocked(int i, boolean flag)
    {
        Object obj;
        Object obj1;
        UserServices userservices;
        UserInfo userinfo;
        Object obj2;
        obj = (UserServices)mUserServices.get(i);
        obj1 = obj;
        if(obj != null)
            break MISSING_BLOCK_LABEL_134;
        userservices = new UserServices(null);
        mUserServices.put(i, userservices);
        obj1 = userservices;
        if(!flag)
            break MISSING_BLOCK_LABEL_134;
        obj1 = userservices;
        if(mSerializerAndParser == null)
            break MISSING_BLOCK_LABEL_134;
        userinfo = getUser(i);
        obj1 = userservices;
        if(userinfo == null)
            break MISSING_BLOCK_LABEL_134;
        obj2 = createFileForUser(userinfo.id);
        obj1 = userservices;
        if(!((AtomicFile) (obj2)).getBaseFile().exists())
            break MISSING_BLOCK_LABEL_134;
        obj = null;
        obj1 = null;
        obj2 = ((AtomicFile) (obj2)).openRead();
        obj1 = obj2;
        obj = obj2;
        readPersistentServicesLocked(((InputStream) (obj2)));
        IoUtils.closeQuietly(((AutoCloseable) (obj2)));
        obj1 = userservices;
_L2:
        return ((UserServices) (obj1));
        Exception exception1;
        exception1;
        obj = obj1;
        obj2 = JVM INSTR new #186 <Class StringBuilder>;
        obj = obj1;
        ((StringBuilder) (obj2)).StringBuilder();
        obj = obj1;
        Log.w("PackageManager", ((StringBuilder) (obj2)).append("Error reading persistent services for user ").append(userinfo.id).toString(), exception1);
        IoUtils.closeQuietly(((AutoCloseable) (obj1)));
        obj1 = userservices;
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        IoUtils.closeQuietly(((AutoCloseable) (obj)));
        throw exception;
    }

    private void generateServicesMap(int ai[], int i)
    {
        Object obj;
        Object obj1;
        obj = new ArrayList();
        obj1 = queryIntentServices(i).iterator();
_L2:
        Object obj2;
        if(!((Iterator) (obj1)).hasNext())
            break; /* Loop/switch isn't completed */
        obj2 = (ResolveInfo)((Iterator) (obj1)).next();
        Object obj3 = parseServiceInfo(((ResolveInfo) (obj2)));
        if(obj3 != null)
            break MISSING_BLOCK_LABEL_130;
        try
        {
            obj3 = JVM INSTR new #186 <Class StringBuilder>;
            ((StringBuilder) (obj3)).StringBuilder();
            Log.w("PackageManager", ((StringBuilder) (obj3)).append("Unable to load service info ").append(((ResolveInfo) (obj2)).toString()).toString());
        }
        // Misplaced declaration of an exception variable
        catch(Object obj3)
        {
            Log.w("PackageManager", (new StringBuilder()).append("Unable to load service info ").append(((ResolveInfo) (obj2)).toString()).toString(), ((Throwable) (obj3)));
        }
        continue; /* Loop/switch isn't completed */
        ((ArrayList) (obj)).add(obj3);
        if(true) goto _L2; else goto _L1
_L1:
        obj1 = mServicesLock;
        obj1;
        JVM INSTR monitorenter ;
        obj2 = findOrCreateUserLocked(i);
        boolean flag1;
        ServiceInfo serviceinfo;
        Object obj5;
        Iterator iterator;
        boolean flag;
        boolean flag2;
        if(((UserServices) (obj2)).services == null)
            flag = true;
        else
            flag = false;
        if(!flag)
            break MISSING_BLOCK_LABEL_180;
        obj2.services = Maps.newHashMap();
        new StringBuilder();
        flag1 = false;
        iterator = ((Iterable) (obj)).iterator();
_L4:
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_471;
        serviceinfo = (ServiceInfo)iterator.next();
        obj5 = (Integer)((UserServices) (obj2)).persistentServices.get(serviceinfo.type);
        if(obj5 != null)
            break MISSING_BLOCK_LABEL_346;
        flag2 = true;
        ((UserServices) (obj2)).services.put(serviceinfo.type, serviceinfo);
        ((UserServices) (obj2)).persistentServices.put(serviceinfo.type, Integer.valueOf(serviceinfo.uid));
        boolean flag3;
        if(((UserServices) (obj2)).mPersistentServicesFileDidNotExist)
            flag3 = flag;
        else
            flag3 = false;
        flag1 = flag2;
        if(flag3) goto _L4; else goto _L3
_L3:
        notifyListener(serviceinfo.type, i, false);
        flag1 = flag2;
          goto _L4
        ai;
        throw ai;
        if(((Integer) (obj5)).intValue() != serviceinfo.uid) goto _L6; else goto _L5
_L5:
        ((UserServices) (obj2)).services.put(serviceinfo.type, serviceinfo);
          goto _L4
_L6:
        if(!inSystemImage(serviceinfo.uid) && !(containsTypeAndUid(((ArrayList) (obj)), serviceinfo.type, ((Integer) (obj5)).intValue()) ^ true)) goto _L4; else goto _L7
_L7:
        flag1 = true;
        ((UserServices) (obj2)).services.put(serviceinfo.type, serviceinfo);
        ((UserServices) (obj2)).persistentServices.put(serviceinfo.type, Integer.valueOf(serviceinfo.uid));
        notifyListener(serviceinfo.type, i, false);
          goto _L4
        obj5 = Lists.newArrayList();
        Iterator iterator1 = ((UserServices) (obj2)).persistentServices.keySet().iterator();
        do
        {
            if(!iterator1.hasNext())
                break;
            Object obj4 = iterator1.next();
            if(!containsType(((ArrayList) (obj)), obj4) && containsUid(ai, ((Integer)((UserServices) (obj2)).persistentServices.get(obj4)).intValue()))
                ((ArrayList) (obj5)).add(obj4);
        } while(true);
        obj = ((Iterable) (obj5)).iterator();
_L8:
        if(!((Iterator) (obj)).hasNext())
            break MISSING_BLOCK_LABEL_620;
        ai = ((int []) (((Iterator) (obj)).next()));
        flag1 = true;
        ((UserServices) (obj2)).persistentServices.remove(ai);
        ((UserServices) (obj2)).services.remove(ai);
        notifyListener(ai, i, true);
          goto _L8
        if(!flag1)
            break MISSING_BLOCK_LABEL_637;
        onServicesChangedLocked(i);
        writePersistentServicesLocked(((UserServices) (obj2)), i);
        obj1;
        JVM INSTR monitorexit ;
    }

    private final void handlePackageEvent(Intent intent, int i)
    {
        String s;
        s = intent.getAction();
        boolean flag;
        boolean flag1;
        if(!"android.intent.action.PACKAGE_REMOVED".equals(s))
            flag = "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE".equals(s);
        else
            flag = true;
        flag1 = intent.getBooleanExtra("android.intent.extra.REPLACING", false);
        if(!flag || !flag1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Object obj;
        obj = null;
        if(!"android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE".equals(s) && !"android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE".equals(s))
            break; /* Loop/switch isn't completed */
        intent = intent.getIntArrayExtra("android.intent.extra.changed_uid_list");
_L4:
        generateServicesMap(intent, i);
        if(true) goto _L1; else goto _L3
_L3:
        int j = intent.getIntExtra("android.intent.extra.UID", -1);
        intent = obj;
        if(j > 0)
        {
            intent = new int[1];
            intent[0] = j;
        }
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void migrateIfNecessaryLocked()
    {
        Object obj;
        Object obj1;
        if(mSerializerAndParser == null)
            return;
        obj = new File(new File(getDataDirectory(), "system"), "registered_services");
        obj1 = new AtomicFile(new File(((File) (obj)), (new StringBuilder()).append(mInterfaceName).append(".xml").toString()));
        if(!((AtomicFile) (obj1)).getBaseFile().exists()) goto _L2; else goto _L1
_L1:
        File file = new File(((File) (obj)), (new StringBuilder()).append(mInterfaceName).append(".xml.migrated").toString());
        if(file.exists()) goto _L2; else goto _L3
_L3:
        Object obj2;
        obj = null;
        obj2 = null;
        obj1 = ((AtomicFile) (obj1)).openRead();
        obj2 = obj1;
        obj = obj1;
        mUserServices.clear();
        obj2 = obj1;
        obj = obj1;
        readPersistentServicesLocked(((InputStream) (obj1)));
        IoUtils.closeQuietly(((AutoCloseable) (obj1)));
_L6:
        obj1 = getUsers().iterator();
_L5:
        do
        {
            if(!((Iterator) (obj1)).hasNext())
                break MISSING_BLOCK_LABEL_268;
            obj = (UserInfo)((Iterator) (obj1)).next();
            obj2 = (UserServices)mUserServices.get(((UserInfo) (obj)).id);
        } while(obj2 == null);
        writePersistentServicesLocked(((UserServices) (obj2)), ((UserInfo) (obj)).id);
        if(true) goto _L5; else goto _L4
_L4:
        mUserServices.clear();
_L2:
        return;
        Exception exception;
        exception;
        obj = obj2;
        Log.w("PackageManager", "Error reading persistent services, starting from scratch", exception);
        IoUtils.closeQuietly(((AutoCloseable) (obj2)));
          goto _L6
        Exception exception1;
        exception1;
        IoUtils.closeQuietly(((AutoCloseable) (obj)));
        throw exception1;
        try
        {
            file.createNewFile();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.w("PackageManager", "Migration failed", ((Throwable) (obj)));
        }
          goto _L4
    }

    private void notifyListener(final Object type, final int userId, final boolean removed)
    {
        this;
        JVM INSTR monitorenter ;
        final RegisteredServicesCacheListener listener2;
        Handler handler;
        listener2 = mListener;
        handler = mHandler;
        this;
        JVM INSTR monitorexit ;
        if(listener2 == null)
        {
            return;
        } else
        {
            handler.post(new Runnable() {

                public void run()
                {
                    listener2.onServiceChanged(type, userId, removed);
                }

                final RegisteredServicesCache this$0;
                final RegisteredServicesCacheListener val$listener2;
                final boolean val$removed;
                final Object val$type;
                final int val$userId;

            
            {
                this$0 = RegisteredServicesCache.this;
                listener2 = registeredservicescachelistener;
                type = obj;
                userId = i;
                removed = flag;
                super();
            }
            }
);
            return;
        }
        type;
        throw type;
    }

    private void readPersistentServicesLocked(InputStream inputstream)
        throws XmlPullParserException, IOException
    {
        XmlPullParser xmlpullparser;
        xmlpullparser = Xml.newPullParser();
        xmlpullparser.setInput(inputstream, StandardCharsets.UTF_8.name());
        for(int i = xmlpullparser.getEventType(); i != 2 && i != 1; i = xmlpullparser.next());
        if(!"services".equals(xmlpullparser.getName())) goto _L2; else goto _L1
_L1:
        int j = xmlpullparser.next();
_L6:
        if(j != 2 || xmlpullparser.getDepth() != 2 || !"service".equals(xmlpullparser.getName())) goto _L4; else goto _L3
_L3:
        inputstream = ((InputStream) (mSerializerAndParser.createFromXml(xmlpullparser)));
        if(inputstream != null) goto _L5; else goto _L2
_L2:
        return;
_L5:
        j = Integer.parseInt(xmlpullparser.getAttributeValue(null, "uid"));
        findOrCreateUserLocked(UserHandle.getUserId(j), false).persistentServices.put(inputstream, Integer.valueOf(j));
_L4:
        j = xmlpullparser.next();
        if(j == 1) goto _L2; else goto _L6
    }

    private void writePersistentServicesLocked(UserServices userservices, int i)
    {
        AtomicFile atomicfile;
        java.io.FileOutputStream fileoutputstream;
        if(mSerializerAndParser == null)
            return;
        atomicfile = createFileForUser(i);
        fileoutputstream = null;
        java.io.FileOutputStream fileoutputstream1 = atomicfile.startWrite();
        fileoutputstream = fileoutputstream1;
        FastXmlSerializer fastxmlserializer = JVM INSTR new #503 <Class FastXmlSerializer>;
        fileoutputstream = fileoutputstream1;
        fastxmlserializer.FastXmlSerializer();
        fileoutputstream = fileoutputstream1;
        fastxmlserializer.setOutput(fileoutputstream1, StandardCharsets.UTF_8.name());
        fileoutputstream = fileoutputstream1;
        fastxmlserializer.startDocument(null, Boolean.valueOf(true));
        fileoutputstream = fileoutputstream1;
        fastxmlserializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        fileoutputstream = fileoutputstream1;
        fastxmlserializer.startTag(null, "services");
        fileoutputstream = fileoutputstream1;
        userservices = userservices.persistentServices.entrySet().iterator();
_L2:
        fileoutputstream = fileoutputstream1;
        if(!userservices.hasNext())
            break; /* Loop/switch isn't completed */
        fileoutputstream = fileoutputstream1;
        java.util.Map.Entry entry = (java.util.Map.Entry)userservices.next();
        fileoutputstream = fileoutputstream1;
        fastxmlserializer.startTag(null, "service");
        fileoutputstream = fileoutputstream1;
        fastxmlserializer.attribute(null, "uid", Integer.toString(((Integer)entry.getValue()).intValue()));
        fileoutputstream = fileoutputstream1;
        mSerializerAndParser.writeAsXml(entry.getKey(), fastxmlserializer);
        fileoutputstream = fileoutputstream1;
        fastxmlserializer.endTag(null, "service");
        if(true) goto _L2; else goto _L1
_L4:
        return;
_L1:
        fileoutputstream = fileoutputstream1;
        fastxmlserializer.endTag(null, "services");
        fileoutputstream = fileoutputstream1;
        fastxmlserializer.endDocument();
        fileoutputstream = fileoutputstream1;
        try
        {
            atomicfile.finishWrite(fileoutputstream1);
        }
        // Misplaced declaration of an exception variable
        catch(UserServices userservices)
        {
            Log.w("PackageManager", "Error writing accounts", userservices);
            if(fileoutputstream != null)
                atomicfile.failWrite(fileoutputstream);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[], int i)
    {
        filedescriptor = ((FileDescriptor) (mServicesLock));
        filedescriptor;
        JVM INSTR monitorenter ;
        Object obj = findOrCreateUserLocked(i);
        if(((UserServices) (obj)).services != null)
        {
            as = JVM INSTR new #186 <Class StringBuilder>;
            as.StringBuilder();
            printwriter.println(as.append("RegisteredServicesCache: ").append(((UserServices) (obj)).services.size()).append(" services").toString());
            StringBuilder stringbuilder;
            for(obj = ((UserServices) (obj)).services.values().iterator(); ((Iterator) (obj)).hasNext(); printwriter.println(stringbuilder.append("  ").append(as).toString()))
            {
                as = (ServiceInfo)((Iterator) (obj)).next();
                stringbuilder = JVM INSTR new #186 <Class StringBuilder>;
                stringbuilder.StringBuilder();
            }

            break MISSING_BLOCK_LABEL_146;
        }
        break MISSING_BLOCK_LABEL_139;
        printwriter;
        throw printwriter;
        printwriter.println("RegisteredServicesCache: services not loaded");
        filedescriptor;
        JVM INSTR monitorexit ;
    }

    public Collection getAllServices(int i)
    {
        Object obj = mServicesLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        UserServices userservices = findOrCreateUserLocked(i);
        if(userservices.services == null)
            generateServicesMap(null, i);
        obj1 = JVM INSTR new #144 <Class ArrayList>;
        ((ArrayList) (obj1)).ArrayList(userservices.services.values());
        obj1 = Collections.unmodifiableCollection(((Collection) (obj1)));
        obj;
        JVM INSTR monitorexit ;
        return ((Collection) (obj1));
        Exception exception;
        exception;
        throw exception;
    }

    protected File getDataDirectory()
    {
        return Environment.getDataDirectory();
    }

    public RegisteredServicesCacheListener getListener()
    {
        this;
        JVM INSTR monitorenter ;
        RegisteredServicesCacheListener registeredservicescachelistener = mListener;
        this;
        JVM INSTR monitorexit ;
        return registeredservicescachelistener;
        Exception exception;
        exception;
        throw exception;
    }

    protected Map getPersistentServices(int i)
    {
        return findOrCreateUserLocked(i).persistentServices;
    }

    public ServiceInfo getServiceInfo(Object obj, int i)
    {
        Object obj1 = mServicesLock;
        obj1;
        JVM INSTR monitorenter ;
        UserServices userservices = findOrCreateUserLocked(i);
        if(userservices.services == null)
            generateServicesMap(null, i);
        obj = (ServiceInfo)userservices.services.get(obj);
        obj1;
        JVM INSTR monitorexit ;
        return ((ServiceInfo) (obj));
        obj;
        throw obj;
    }

    protected UserInfo getUser(int i)
    {
        return UserManager.get(mContext).getUserInfo(i);
    }

    protected File getUserSystemDirectory(int i)
    {
        return Environment.getUserSystemDirectory(i);
    }

    protected List getUsers()
    {
        return UserManager.get(mContext).getUsers(true);
    }

    protected boolean inSystemImage(int i)
    {
        String as[] = mContext.getPackageManager().getPackagesForUid(i);
        if(as != null)
        {
            int j = as.length;
            PackageManager.NameNotFoundException namenotfoundexception;
            for(i = 0; i < j; i++)
            {
                String s = as[i];
                int k;
                try
                {
                    k = mContext.getPackageManager().getPackageInfo(s, 0).applicationInfo.flags;
                }
                // Misplaced declaration of an exception variable
                catch(PackageManager.NameNotFoundException namenotfoundexception)
                {
                    return false;
                }
                if((k & 1) != 0)
                    return true;
            }

        }
        return false;
    }

    public void invalidateCache(int i)
    {
        Object obj = mServicesLock;
        obj;
        JVM INSTR monitorenter ;
        findOrCreateUserLocked(i).services = null;
        onServicesChangedLocked(i);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void onServicesChangedLocked(int i)
    {
    }

    protected void onUserRemoved(int i)
    {
        Object obj = mServicesLock;
        obj;
        JVM INSTR monitorenter ;
        mUserServices.remove(i);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public abstract Object parseServiceAttributes(Resources resources, String s, AttributeSet attributeset);

    protected ServiceInfo parseServiceInfo(ResolveInfo resolveinfo)
        throws XmlPullParserException, IOException
    {
        android.content.pm.ServiceInfo serviceinfo;
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        serviceinfo = resolveinfo.serviceInfo;
        obj = new ComponentName(serviceinfo.packageName, serviceinfo.name);
        obj1 = mContext.getPackageManager();
        obj2 = null;
        obj3 = null;
        Object obj4 = serviceinfo.loadXmlMetaData(((PackageManager) (obj1)), mMetaDataName);
        if(obj4 != null)
            break MISSING_BLOCK_LABEL_208;
        obj3 = obj4;
        obj2 = obj4;
        resolveinfo = JVM INSTR new #271 <Class XmlPullParserException>;
        obj3 = obj4;
        obj2 = obj4;
        obj = JVM INSTR new #186 <Class StringBuilder>;
        obj3 = obj4;
        obj2 = obj4;
        ((StringBuilder) (obj)).StringBuilder();
        obj3 = obj4;
        obj2 = obj4;
        resolveinfo.XmlPullParserException(((StringBuilder) (obj)).append("No ").append(mMetaDataName).append(" meta-data").toString());
        obj3 = obj4;
        obj2 = obj4;
        try
        {
            throw resolveinfo;
        }
        // Misplaced declaration of an exception variable
        catch(ResolveInfo resolveinfo)
        {
            obj2 = obj3;
        }
        resolveinfo = JVM INSTR new #271 <Class XmlPullParserException>;
        obj2 = obj3;
        obj4 = JVM INSTR new #186 <Class StringBuilder>;
        obj2 = obj3;
        ((StringBuilder) (obj4)).StringBuilder();
        obj2 = obj3;
        resolveinfo.XmlPullParserException(((StringBuilder) (obj4)).append("Unable to load resources for pacakge ").append(serviceinfo.packageName).toString());
        obj2 = obj3;
        throw resolveinfo;
        resolveinfo;
        if(obj2 != null)
            ((XmlResourceParser) (obj2)).close();
        throw resolveinfo;
        obj3 = obj4;
        obj2 = obj4;
        AttributeSet attributeset = Xml.asAttributeSet(((XmlPullParser) (obj4)));
_L2:
        obj3 = obj4;
        obj2 = obj4;
        int i = ((XmlResourceParser) (obj4)).next();
        if(i != 1 && i != 2) goto _L2; else goto _L1
_L1:
        obj3 = obj4;
        obj2 = obj4;
        String s = ((XmlResourceParser) (obj4)).getName();
        obj3 = obj4;
        obj2 = obj4;
        if(mAttributesName.equals(s))
            break MISSING_BLOCK_LABEL_370;
        obj3 = obj4;
        obj2 = obj4;
        obj = JVM INSTR new #271 <Class XmlPullParserException>;
        obj3 = obj4;
        obj2 = obj4;
        resolveinfo = JVM INSTR new #186 <Class StringBuilder>;
        obj3 = obj4;
        obj2 = obj4;
        resolveinfo.StringBuilder();
        obj3 = obj4;
        obj2 = obj4;
        ((XmlPullParserException) (obj)).XmlPullParserException(resolveinfo.append("Meta-data does not start with ").append(mAttributesName).append(" tag").toString());
        obj3 = obj4;
        obj2 = obj4;
        throw obj;
        obj3 = obj4;
        obj2 = obj4;
        obj1 = parseServiceAttributes(((PackageManager) (obj1)).getResourcesForApplication(serviceinfo.applicationInfo), serviceinfo.packageName, attributeset);
        if(obj1 == null)
        {
            if(obj4 != null)
                ((XmlResourceParser) (obj4)).close();
            return null;
        }
        obj3 = obj4;
        obj2 = obj4;
        resolveinfo = new ServiceInfo(obj1, resolveinfo.serviceInfo, ((ComponentName) (obj)));
        if(obj4 != null)
            ((XmlResourceParser) (obj4)).close();
        return resolveinfo;
    }

    protected List queryIntentServices(int i)
    {
        return mContext.getPackageManager().queryIntentServicesAsUser(new Intent(mInterfaceName), 0xc0080, i);
    }

    public void setListener(RegisteredServicesCacheListener registeredservicescachelistener, Handler handler)
    {
        Handler handler1;
        handler1 = handler;
        if(handler == null)
            handler1 = new Handler(mContext.getMainLooper());
        this;
        JVM INSTR monitorenter ;
        mHandler = handler1;
        mListener = registeredservicescachelistener;
        this;
        JVM INSTR monitorexit ;
        return;
        registeredservicescachelistener;
        throw registeredservicescachelistener;
    }

    public void updateServices(int i)
    {
        Object obj = mServicesLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        Object obj3;
        obj1 = findOrCreateUserLocked(i);
        obj3 = ((UserServices) (obj1)).services;
        if(obj3 != null)
            break MISSING_BLOCK_LABEL_27;
        obj;
        JVM INSTR monitorexit ;
        return;
        obj1 = new ArrayList(((UserServices) (obj1)).services.values());
        obj;
        JVM INSTR monitorexit ;
        Iterator iterator;
        obj = null;
        iterator = ((Iterable) (obj1)).iterator();
_L2:
        ServiceInfo serviceinfo;
        int j;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        serviceinfo = (ServiceInfo)iterator.next();
        j = serviceinfo.componentInfo.applicationInfo.versionCode;
        obj3 = serviceinfo.componentInfo.packageName;
        obj1 = null;
        obj3 = mContext.getPackageManager().getApplicationInfoAsUser(((String) (obj3)), 0, i);
        obj1 = obj3;
_L3:
        if(obj1 == null || ((ApplicationInfo) (obj1)).versionCode != j)
        {
            Object obj2 = obj;
            if(obj == null)
                obj2 = new IntArray();
            ((IntArray) (obj2)).add(serviceinfo.uid);
            obj = obj2;
        }
        if(true) goto _L2; else goto _L1
        Exception exception;
        exception;
        throw exception;
_L1:
        if(obj != null && ((IntArray) (obj)).size() > 0)
            generateServicesMap(((IntArray) (obj)).toArray(), i);
        return;
        PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
          goto _L3
    }

    private static final boolean DEBUG = false;
    protected static final String REGISTERED_SERVICES_DIR = "registered_services";
    private static final String TAG = "PackageManager";
    private final String mAttributesName;
    public final Context mContext;
    private final BroadcastReceiver mExternalReceiver = new BroadcastReceiver() {

        public void onReceive(Context context1, Intent intent)
        {
            RegisteredServicesCache._2D_wrap0(RegisteredServicesCache.this, intent, 0);
        }

        final RegisteredServicesCache this$0;

            
            {
                this$0 = RegisteredServicesCache.this;
                super();
            }
    }
;
    private Handler mHandler;
    private final String mInterfaceName;
    private RegisteredServicesCacheListener mListener;
    private final String mMetaDataName;
    private final BroadcastReceiver mPackageReceiver = new BroadcastReceiver() {

        public void onReceive(Context context1, Intent intent)
        {
            int i = intent.getIntExtra("android.intent.extra.UID", -1);
            if(i != -1)
                RegisteredServicesCache._2D_wrap0(RegisteredServicesCache.this, intent, UserHandle.getUserId(i));
        }

        final RegisteredServicesCache this$0;

            
            {
                this$0 = RegisteredServicesCache.this;
                super();
            }
    }
;
    private final XmlSerializerAndParser mSerializerAndParser;
    protected final Object mServicesLock = new Object();
    private final BroadcastReceiver mUserRemovedReceiver = new BroadcastReceiver() {

        public void onReceive(Context context1, Intent intent)
        {
            int i = intent.getIntExtra("android.intent.extra.user_handle", -1);
            onUserRemoved(i);
        }

        final RegisteredServicesCache this$0;

            
            {
                this$0 = RegisteredServicesCache.this;
                super();
            }
    }
;
    private final SparseArray mUserServices = new SparseArray(2);
}
