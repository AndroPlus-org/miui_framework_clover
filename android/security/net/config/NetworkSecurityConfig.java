// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import android.util.ArrayMap;
import android.util.ArraySet;
import java.security.cert.X509Certificate;
import java.util.*;

// Referenced classes of package android.security.net.config:
//            CertificatesEntryRef, SystemCertificateSource, UserCertificateSource, TrustAnchor, 
//            NetworkSecurityTrustManager, PinSet

public final class NetworkSecurityConfig
{
    public static final class Builder
    {

        private List getEffectiveCertificatesEntryRefs()
        {
            if(mCertificatesEntryRefs != null)
                return mCertificatesEntryRefs;
            if(mParentBuilder != null)
                return mParentBuilder.getEffectiveCertificatesEntryRefs();
            else
                return Collections.emptyList();
        }

        private boolean getEffectiveCleartextTrafficPermitted()
        {
            if(mCleartextTrafficPermittedSet)
                return mCleartextTrafficPermitted;
            if(mParentBuilder != null)
                return mParentBuilder.getEffectiveCleartextTrafficPermitted();
            else
                return true;
        }

        private boolean getEffectiveHstsEnforced()
        {
            if(mHstsEnforcedSet)
                return mHstsEnforced;
            if(mParentBuilder != null)
                return mParentBuilder.getEffectiveHstsEnforced();
            else
                return false;
        }

        private PinSet getEffectivePinSet()
        {
            if(mPinSet != null)
                return mPinSet;
            if(mParentBuilder != null)
                return mParentBuilder.getEffectivePinSet();
            else
                return PinSet.EMPTY_PINSET;
        }

        public Builder addCertificatesEntryRef(CertificatesEntryRef certificatesentryref)
        {
            if(mCertificatesEntryRefs == null)
                mCertificatesEntryRefs = new ArrayList();
            mCertificatesEntryRefs.add(certificatesentryref);
            return this;
        }

        public Builder addCertificatesEntryRefs(Collection collection)
        {
            if(mCertificatesEntryRefs == null)
                mCertificatesEntryRefs = new ArrayList();
            mCertificatesEntryRefs.addAll(collection);
            return this;
        }

        public NetworkSecurityConfig build()
        {
            return new NetworkSecurityConfig(getEffectiveCleartextTrafficPermitted(), getEffectiveHstsEnforced(), getEffectivePinSet(), getEffectiveCertificatesEntryRefs(), null);
        }

        List getCertificatesEntryRefs()
        {
            return mCertificatesEntryRefs;
        }

        public Builder getParent()
        {
            return mParentBuilder;
        }

        public boolean hasCertificatesEntryRefs()
        {
            boolean flag;
            if(mCertificatesEntryRefs != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public Builder setCleartextTrafficPermitted(boolean flag)
        {
            mCleartextTrafficPermitted = flag;
            mCleartextTrafficPermittedSet = true;
            return this;
        }

        public Builder setHstsEnforced(boolean flag)
        {
            mHstsEnforced = flag;
            mHstsEnforcedSet = true;
            return this;
        }

        public Builder setParent(Builder builder)
        {
            for(Builder builder1 = builder; builder1 != null; builder1 = builder1.getParent())
                if(builder1 == this)
                    throw new IllegalArgumentException("Loops are not allowed in Builder parents");

            mParentBuilder = builder;
            return this;
        }

        public Builder setPinSet(PinSet pinset)
        {
            mPinSet = pinset;
            return this;
        }

        private List mCertificatesEntryRefs;
        private boolean mCleartextTrafficPermitted;
        private boolean mCleartextTrafficPermittedSet;
        private boolean mHstsEnforced;
        private boolean mHstsEnforcedSet;
        private Builder mParentBuilder;
        private PinSet mPinSet;

        public Builder()
        {
            mCleartextTrafficPermitted = true;
            mHstsEnforced = false;
            mCleartextTrafficPermittedSet = false;
            mHstsEnforcedSet = false;
        }
    }


    private NetworkSecurityConfig(boolean flag, boolean flag1, PinSet pinset, List list)
    {
        mAnchorsLock = new Object();
        mTrustManagerLock = new Object();
        mCleartextTrafficPermitted = flag;
        mHstsEnforced = flag1;
        mPins = pinset;
        mCertificatesEntryRefs = list;
        Collections.sort(mCertificatesEntryRefs, new Comparator() {

            public int compare(CertificatesEntryRef certificatesentryref, CertificatesEntryRef certificatesentryref1)
            {
                boolean flag2 = false;
                int i = 0;
                if(certificatesentryref.overridesPins())
                {
                    if(!certificatesentryref1.overridesPins())
                        i = -1;
                    return i;
                }
                i = ((flag2) ? 1 : 0);
                if(certificatesentryref1.overridesPins())
                    i = 1;
                return i;
            }

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((CertificatesEntryRef)obj, (CertificatesEntryRef)obj1);
            }

            final NetworkSecurityConfig this$0;

            
            {
                this$0 = NetworkSecurityConfig.this;
                super();
            }
        }
);
    }

    NetworkSecurityConfig(boolean flag, boolean flag1, PinSet pinset, List list, NetworkSecurityConfig networksecurityconfig)
    {
        this(flag, flag1, pinset, list);
    }

    public static final Builder getDefaultBuilder(int i, int j)
    {
        Builder builder = (new Builder()).setHstsEnforced(false).addCertificatesEntryRef(new CertificatesEntryRef(SystemCertificateSource.getInstance(), false));
        boolean flag;
        if(j < 2)
            flag = true;
        else
            flag = false;
        builder.setCleartextTrafficPermitted(flag);
        if(i <= 23)
            builder.addCertificatesEntryRef(new CertificatesEntryRef(UserCertificateSource.getInstance(), false));
        return builder;
    }

    public Set findAllCertificatesByIssuerAndSignature(X509Certificate x509certificate)
    {
        ArraySet arrayset = new ArraySet();
        for(Iterator iterator = mCertificatesEntryRefs.iterator(); iterator.hasNext(); arrayset.addAll(((CertificatesEntryRef)iterator.next()).findAllCertificatesByIssuerAndSignature(x509certificate)));
        return arrayset;
    }

    public TrustAnchor findTrustAnchorByIssuerAndSignature(X509Certificate x509certificate)
    {
        for(Iterator iterator = mCertificatesEntryRefs.iterator(); iterator.hasNext();)
        {
            TrustAnchor trustanchor = ((CertificatesEntryRef)iterator.next()).findByIssuerAndSignature(x509certificate);
            if(trustanchor != null)
                return trustanchor;
        }

        return null;
    }

    public TrustAnchor findTrustAnchorBySubjectAndPublicKey(X509Certificate x509certificate)
    {
        for(Iterator iterator = mCertificatesEntryRefs.iterator(); iterator.hasNext();)
        {
            TrustAnchor trustanchor = ((CertificatesEntryRef)iterator.next()).findBySubjectAndPublicKey(x509certificate);
            if(trustanchor != null)
                return trustanchor;
        }

        return null;
    }

    public PinSet getPins()
    {
        return mPins;
    }

    public Set getTrustAnchors()
    {
        Object obj = mAnchorsLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        if(mAnchors == null)
            break MISSING_BLOCK_LABEL_23;
        obj1 = mAnchors;
        obj;
        JVM INSTR monitorexit ;
        return ((Set) (obj1));
        obj1 = JVM INSTR new #138 <Class ArrayMap>;
        ((ArrayMap) (obj1)).ArrayMap();
        for(Iterator iterator = mCertificatesEntryRefs.iterator(); iterator.hasNext();)
        {
            Iterator iterator1 = ((CertificatesEntryRef)iterator.next()).getTrustAnchors().iterator();
            while(iterator1.hasNext()) 
            {
                TrustAnchor trustanchor = (TrustAnchor)iterator1.next();
                X509Certificate x509certificate = trustanchor.certificate;
                if(!((Map) (obj1)).containsKey(x509certificate))
                    ((Map) (obj1)).put(x509certificate, trustanchor);
            }
        }

        break MISSING_BLOCK_LABEL_128;
        obj1;
        throw obj1;
        ArraySet arrayset = JVM INSTR new #95  <Class ArraySet>;
        arrayset.ArraySet(((Map) (obj1)).size());
        arrayset.addAll(((Map) (obj1)).values());
        mAnchors = arrayset;
        obj1 = mAnchors;
        obj;
        JVM INSTR monitorexit ;
        return ((Set) (obj1));
    }

    public NetworkSecurityTrustManager getTrustManager()
    {
        Object obj = mTrustManagerLock;
        obj;
        JVM INSTR monitorenter ;
        NetworkSecurityTrustManager networksecuritytrustmanager1;
        if(mTrustManager == null)
        {
            NetworkSecurityTrustManager networksecuritytrustmanager = JVM INSTR new #176 <Class NetworkSecurityTrustManager>;
            networksecuritytrustmanager.NetworkSecurityTrustManager(this);
            mTrustManager = networksecuritytrustmanager;
        }
        networksecuritytrustmanager1 = mTrustManager;
        obj;
        JVM INSTR monitorexit ;
        return networksecuritytrustmanager1;
        Exception exception;
        exception;
        throw exception;
    }

    public void handleTrustStorageUpdate()
    {
        Object obj = mAnchorsLock;
        obj;
        JVM INSTR monitorenter ;
        mAnchors = null;
        for(Iterator iterator = mCertificatesEntryRefs.iterator(); iterator.hasNext(); ((CertificatesEntryRef)iterator.next()).handleTrustStorageUpdate());
        break MISSING_BLOCK_LABEL_51;
        Exception exception;
        exception;
        throw exception;
        obj;
        JVM INSTR monitorexit ;
        getTrustManager().handleTrustStorageUpdate();
        return;
    }

    public boolean isCleartextTrafficPermitted()
    {
        return mCleartextTrafficPermitted;
    }

    public boolean isHstsEnforced()
    {
        return mHstsEnforced;
    }

    public static final boolean DEFAULT_CLEARTEXT_TRAFFIC_PERMITTED = true;
    public static final boolean DEFAULT_HSTS_ENFORCED = false;
    private Set mAnchors;
    private final Object mAnchorsLock;
    private final List mCertificatesEntryRefs;
    private final boolean mCleartextTrafficPermitted;
    private final boolean mHstsEnforced;
    private final PinSet mPins;
    private NetworkSecurityTrustManager mTrustManager;
    private final Object mTrustManagerLock;
}
