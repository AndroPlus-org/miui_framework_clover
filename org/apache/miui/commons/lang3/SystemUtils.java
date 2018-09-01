// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3;

import java.io.File;
import java.io.PrintStream;

// Referenced classes of package org.apache.miui.commons.lang3:
//            JavaVersion

public class SystemUtils
{

    public SystemUtils()
    {
    }

    public static File getJavaHome()
    {
        return new File(System.getProperty("java.home"));
    }

    public static File getJavaIoTmpDir()
    {
        return new File(System.getProperty("java.io.tmpdir"));
    }

    private static boolean getJavaVersionMatches(String s)
    {
        return isJavaVersionMatch(JAVA_SPECIFICATION_VERSION, s);
    }

    private static boolean getOSMatches(String s, String s1)
    {
        return isOSMatch(OS_NAME, OS_VERSION, s, s1);
    }

    private static boolean getOSMatchesName(String s)
    {
        return isOSNameMatch(OS_NAME, s);
    }

    private static String getSystemProperty(String s)
    {
        String s1;
        try
        {
            s1 = System.getProperty(s);
        }
        catch(SecurityException securityexception)
        {
            System.err.println((new StringBuilder()).append("Caught a SecurityException reading the system property '").append(s).append("'; the SystemUtils property value will default to null.").toString());
            return null;
        }
        return s1;
    }

    public static File getUserDir()
    {
        return new File(System.getProperty("user.dir"));
    }

    public static File getUserHome()
    {
        return new File(System.getProperty("user.home"));
    }

    public static boolean isJavaAwtHeadless()
    {
        boolean flag;
        if(JAVA_AWT_HEADLESS != null)
            flag = JAVA_AWT_HEADLESS.equals(Boolean.TRUE.toString());
        else
            flag = false;
        return flag;
    }

    public static boolean isJavaVersionAtLeast(JavaVersion javaversion)
    {
        return JAVA_SPECIFICATION_VERSION_AS_ENUM.atLeast(javaversion);
    }

    static boolean isJavaVersionMatch(String s, String s1)
    {
        if(s == null)
            return false;
        else
            return s.startsWith(s1);
    }

    static boolean isOSMatch(String s, String s1, String s2, String s3)
    {
        boolean flag = false;
        if(s == null || s1 == null)
            return false;
        if(s.startsWith(s2))
            flag = s1.startsWith(s3);
        return flag;
    }

    static boolean isOSNameMatch(String s, String s1)
    {
        if(s == null)
            return false;
        else
            return s.startsWith(s1);
    }

    public static final String AWT_TOOLKIT = getSystemProperty("awt.toolkit");
    public static final String FILE_ENCODING = getSystemProperty("file.encoding");
    public static final String FILE_SEPARATOR = getSystemProperty("file.separator");
    public static final boolean IS_JAVA_1_1 = getJavaVersionMatches("1.1");
    public static final boolean IS_JAVA_1_2 = getJavaVersionMatches("1.2");
    public static final boolean IS_JAVA_1_3 = getJavaVersionMatches("1.3");
    public static final boolean IS_JAVA_1_4 = getJavaVersionMatches("1.4");
    public static final boolean IS_JAVA_1_5 = getJavaVersionMatches("1.5");
    public static final boolean IS_JAVA_1_6 = getJavaVersionMatches("1.6");
    public static final boolean IS_JAVA_1_7 = getJavaVersionMatches("1.7");
    public static final boolean IS_OS_AIX;
    public static final boolean IS_OS_FREE_BSD;
    public static final boolean IS_OS_HP_UX;
    public static final boolean IS_OS_IRIX;
    public static final boolean IS_OS_LINUX;
    public static final boolean IS_OS_MAC = getOSMatchesName("Mac");
    public static final boolean IS_OS_MAC_OSX;
    public static final boolean IS_OS_NET_BSD;
    public static final boolean IS_OS_OPEN_BSD;
    public static final boolean IS_OS_OS2 = getOSMatchesName("OS/2");
    public static final boolean IS_OS_SOLARIS;
    public static final boolean IS_OS_SUN_OS;
    public static final boolean IS_OS_UNIX;
    public static final boolean IS_OS_WINDOWS = getOSMatchesName("Windows");
    public static final boolean IS_OS_WINDOWS_2000 = getOSMatches("Windows", "5.0");
    public static final boolean IS_OS_WINDOWS_2003 = getOSMatches("Windows", "5.2");
    public static final boolean IS_OS_WINDOWS_2008 = getOSMatches("Windows Server 2008", "6.1");
    public static final boolean IS_OS_WINDOWS_7 = getOSMatches("Windows", "6.1");
    public static final boolean IS_OS_WINDOWS_95 = getOSMatches("Windows 9", "4.0");
    public static final boolean IS_OS_WINDOWS_98 = getOSMatches("Windows 9", "4.1");
    public static final boolean IS_OS_WINDOWS_ME = getOSMatches("Windows", "4.9");
    public static final boolean IS_OS_WINDOWS_NT = getOSMatchesName("Windows NT");
    public static final boolean IS_OS_WINDOWS_VISTA = getOSMatches("Windows", "6.0");
    public static final boolean IS_OS_WINDOWS_XP = getOSMatches("Windows", "5.1");
    public static final String JAVA_AWT_FONTS = getSystemProperty("java.awt.fonts");
    public static final String JAVA_AWT_GRAPHICSENV = getSystemProperty("java.awt.graphicsenv");
    public static final String JAVA_AWT_HEADLESS = getSystemProperty("java.awt.headless");
    public static final String JAVA_AWT_PRINTERJOB = getSystemProperty("java.awt.printerjob");
    public static final String JAVA_CLASS_PATH = getSystemProperty("java.class.path");
    public static final String JAVA_CLASS_VERSION = getSystemProperty("java.class.version");
    public static final String JAVA_COMPILER = getSystemProperty("java.compiler");
    public static final String JAVA_ENDORSED_DIRS = getSystemProperty("java.endorsed.dirs");
    public static final String JAVA_EXT_DIRS = getSystemProperty("java.ext.dirs");
    public static final String JAVA_HOME = getSystemProperty("java.home");
    private static final String JAVA_HOME_KEY = "java.home";
    public static final String JAVA_IO_TMPDIR = getSystemProperty("java.io.tmpdir");
    private static final String JAVA_IO_TMPDIR_KEY = "java.io.tmpdir";
    public static final String JAVA_LIBRARY_PATH = getSystemProperty("java.library.path");
    public static final String JAVA_RUNTIME_NAME = getSystemProperty("java.runtime.name");
    public static final String JAVA_RUNTIME_VERSION = getSystemProperty("java.runtime.version");
    public static final String JAVA_SPECIFICATION_NAME = getSystemProperty("java.specification.name");
    public static final String JAVA_SPECIFICATION_VENDOR = getSystemProperty("java.specification.vendor");
    public static final String JAVA_SPECIFICATION_VERSION = getSystemProperty("java.specification.version");
    private static final JavaVersion JAVA_SPECIFICATION_VERSION_AS_ENUM = JavaVersion.get(JAVA_SPECIFICATION_VERSION);
    public static final String JAVA_UTIL_PREFS_PREFERENCES_FACTORY = getSystemProperty("java.util.prefs.PreferencesFactory");
    public static final String JAVA_VENDOR = getSystemProperty("java.vendor");
    public static final String JAVA_VENDOR_URL = getSystemProperty("java.vendor.url");
    public static final String JAVA_VERSION = getSystemProperty("java.version");
    public static final String JAVA_VM_INFO = getSystemProperty("java.vm.info");
    public static final String JAVA_VM_NAME = getSystemProperty("java.vm.name");
    public static final String JAVA_VM_SPECIFICATION_NAME = getSystemProperty("java.vm.specification.name");
    public static final String JAVA_VM_SPECIFICATION_VENDOR = getSystemProperty("java.vm.specification.vendor");
    public static final String JAVA_VM_SPECIFICATION_VERSION = getSystemProperty("java.vm.specification.version");
    public static final String JAVA_VM_VENDOR = getSystemProperty("java.vm.vendor");
    public static final String JAVA_VM_VERSION = getSystemProperty("java.vm.version");
    public static final String LINE_SEPARATOR = getSystemProperty("line.separator");
    public static final String OS_ARCH = getSystemProperty("os.arch");
    public static final String OS_NAME = getSystemProperty("os.name");
    private static final String OS_NAME_WINDOWS_PREFIX = "Windows";
    public static final String OS_VERSION = getSystemProperty("os.version");
    public static final String PATH_SEPARATOR = getSystemProperty("path.separator");
    public static final String USER_COUNTRY;
    public static final String USER_DIR = getSystemProperty("user.dir");
    private static final String USER_DIR_KEY = "user.dir";
    public static final String USER_HOME = getSystemProperty("user.home");
    private static final String USER_HOME_KEY = "user.home";
    public static final String USER_LANGUAGE = getSystemProperty("user.language");
    public static final String USER_NAME = getSystemProperty("user.name");
    public static final String USER_TIMEZONE = getSystemProperty("user.timezone");

    static 
    {
        boolean flag = true;
        String s;
        boolean flag1;
        if(getSystemProperty("user.country") == null)
            s = getSystemProperty("user.region");
        else
            s = getSystemProperty("user.country");
        USER_COUNTRY = s;
        IS_OS_AIX = getOSMatchesName("AIX");
        IS_OS_HP_UX = getOSMatchesName("HP-UX");
        IS_OS_IRIX = getOSMatchesName("Irix");
        if(!getOSMatchesName("Linux"))
            flag1 = getOSMatchesName("LINUX");
        else
            flag1 = true;
        IS_OS_LINUX = flag1;
        IS_OS_MAC_OSX = getOSMatchesName("Mac OS X");
        IS_OS_FREE_BSD = getOSMatchesName("FreeBSD");
        IS_OS_OPEN_BSD = getOSMatchesName("OpenBSD");
        IS_OS_NET_BSD = getOSMatchesName("NetBSD");
        IS_OS_SOLARIS = getOSMatchesName("Solaris");
        IS_OS_SUN_OS = getOSMatchesName("SunOS");
        flag1 = flag;
        if(!IS_OS_AIX)
        {
            flag1 = flag;
            if(!IS_OS_HP_UX)
            {
                flag1 = flag;
                if(!IS_OS_IRIX)
                {
                    flag1 = flag;
                    if(!IS_OS_LINUX)
                    {
                        flag1 = flag;
                        if(!IS_OS_MAC_OSX)
                        {
                            flag1 = flag;
                            if(!IS_OS_SOLARIS)
                            {
                                flag1 = flag;
                                if(!IS_OS_SUN_OS)
                                {
                                    flag1 = flag;
                                    if(!IS_OS_FREE_BSD)
                                    {
                                        flag1 = flag;
                                        if(!IS_OS_OPEN_BSD)
                                            flag1 = IS_OS_NET_BSD;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        IS_OS_UNIX = flag1;
    }
}
