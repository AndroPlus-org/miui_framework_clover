// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Xml;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.app:
//            Activity

public class AliasActivity extends Activity
{

    public AliasActivity()
    {
    }

    private Intent parseAlias(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        android.util.AttributeSet attributeset = Xml.asAttributeSet(xmlpullparser);
        Intent intent = null;
        int i;
        do
            i = xmlpullparser.next();
        while(i != 1 && i != 2);
        String s = xmlpullparser.getName();
        if(!"alias".equals(s))
            throw new RuntimeException((new StringBuilder()).append("Alias meta-data must start with <alias> tag; found").append(s).append(" at ").append(xmlpullparser.getPositionDescription()).toString());
        int k = xmlpullparser.getDepth();
        do
        {
            int j = xmlpullparser.next();
            if(j == 1 || j == 3 && xmlpullparser.getDepth() <= k)
                break;
            if(j != 3 && j != 4)
                if("intent".equals(xmlpullparser.getName()))
                {
                    Intent intent1 = Intent.parseIntent(getResources(), xmlpullparser, attributeset);
                    if(intent == null)
                        intent = intent1;
                } else
                {
                    XmlUtils.skipCurrentTag(xmlpullparser);
                }
        } while(true);
        return intent;
    }

    protected void onCreate(Bundle bundle)
    {
        Object obj;
        Object obj1;
        Object obj2;
        super.onCreate(bundle);
        bundle = null;
        obj = null;
        obj1 = null;
        obj2 = null;
        Object obj3 = getPackageManager().getActivityInfo(getComponentName(), 128).loadXmlMetaData(getPackageManager(), "android.app.alias");
        if(obj3 != null)
            break MISSING_BLOCK_LABEL_138;
        obj2 = obj3;
        bundle = ((Bundle) (obj3));
        obj = obj3;
        obj1 = obj3;
        Object obj4 = JVM INSTR new #47  <Class RuntimeException>;
        obj2 = obj3;
        bundle = ((Bundle) (obj3));
        obj = obj3;
        obj1 = obj3;
        ((RuntimeException) (obj4)).RuntimeException("Alias requires a meta-data field android.app.alias");
        obj2 = obj3;
        bundle = ((Bundle) (obj3));
        obj = obj3;
        obj1 = obj3;
        throw obj4;
        obj3;
        bundle = ((Bundle) (obj2));
        obj = JVM INSTR new #47  <Class RuntimeException>;
        bundle = ((Bundle) (obj2));
        ((RuntimeException) (obj)).RuntimeException("Error parsing alias", ((Throwable) (obj3)));
        bundle = ((Bundle) (obj2));
        throw obj;
        obj3;
        if(bundle != null)
            bundle.close();
        throw obj3;
        obj2 = obj3;
        bundle = ((Bundle) (obj3));
        obj = obj3;
        obj1 = obj3;
        obj4 = parseAlias(((XmlPullParser) (obj3)));
        if(obj4 != null)
            break MISSING_BLOCK_LABEL_243;
        obj2 = obj3;
        bundle = ((Bundle) (obj3));
        obj = obj3;
        obj1 = obj3;
        obj4 = JVM INSTR new #47  <Class RuntimeException>;
        obj2 = obj3;
        bundle = ((Bundle) (obj3));
        obj = obj3;
        obj1 = obj3;
        ((RuntimeException) (obj4)).RuntimeException("No <intent> tag found in alias description");
        obj2 = obj3;
        bundle = ((Bundle) (obj3));
        obj = obj3;
        obj1 = obj3;
        throw obj4;
        obj2;
        bundle = ((Bundle) (obj));
        obj3 = JVM INSTR new #47  <Class RuntimeException>;
        bundle = ((Bundle) (obj));
        ((RuntimeException) (obj3)).RuntimeException("Error parsing alias", ((Throwable) (obj2)));
        bundle = ((Bundle) (obj));
        throw obj3;
        obj2 = obj3;
        bundle = ((Bundle) (obj3));
        obj = obj3;
        obj1 = obj3;
        startActivity(((Intent) (obj4)));
        obj2 = obj3;
        bundle = ((Bundle) (obj3));
        obj = obj3;
        obj1 = obj3;
        finish();
        if(obj3 != null)
            ((XmlResourceParser) (obj3)).close();
        return;
        IOException ioexception;
        ioexception;
        bundle = ((Bundle) (obj1));
        obj3 = JVM INSTR new #47  <Class RuntimeException>;
        bundle = ((Bundle) (obj1));
        ((RuntimeException) (obj3)).RuntimeException("Error parsing alias", ioexception);
        bundle = ((Bundle) (obj1));
        throw obj3;
    }

    public final String ALIAS_META_DATA = "android.app.alias";
}
