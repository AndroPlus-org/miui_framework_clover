// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.text.TextUtils;

// Referenced classes of package miui.maml:
//            ScreenElementRoot, ScreenContext, ResourceManager

public class SystemCommandListener
    implements ScreenElementRoot.OnExternCommandListener
{

    public SystemCommandListener(ScreenElementRoot screenelementroot)
    {
        mRoot = screenelementroot;
    }

    public void onCommand(String s, Double double1, String s1)
    {
        if(!"__clearResource".equals(s)) goto _L2; else goto _L1
_L1:
        if(TextUtils.isEmpty(s1))
            mRoot.getContext().mResourceManager.clear();
        else
            mRoot.getContext().mResourceManager.clear(s1);
_L4:
        return;
_L2:
        if("__requestUpdate".equals(s))
            mRoot.requestUpdate();
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final String CLEAR_RESOURCE = "__clearResource";
    private static final String REQUEST_UPDATE = "__requestUpdate";
    private ScreenElementRoot mRoot;
}
