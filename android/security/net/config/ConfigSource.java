// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import java.util.Set;

// Referenced classes of package android.security.net.config:
//            NetworkSecurityConfig

public interface ConfigSource
{

    public abstract NetworkSecurityConfig getDefaultConfig();

    public abstract Set getPerDomainConfigs();
}
