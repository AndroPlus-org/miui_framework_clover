// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.accounts.*;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import miui.maml.ScreenContext;
import miui.maml.ScreenElementRoot;
import miui.maml.elements.ListScreenElement;
import miui.maml.util.*;
import miui.maml.util.net.*;
import miui.net.ConnectivityHelper;
import miui.os.FileUtils;
import org.json.*;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// Referenced classes of package miui.maml.data:
//            VariableBinder, Expression, IndexedVariable, Variables

public class WebServiceBinder extends VariableBinder
{
    public static final class AuthToken
    {

        public static AuthToken parse(String s)
        {
            if(TextUtils.isEmpty(s))
                return null;
            s = s.split(",");
            if(s.length != 2)
                return null;
            else
                return new AuthToken(s[0], s[1]);
        }

        public final String authToken;
        public final String security;

        private AuthToken(String s, String s1)
        {
            authToken = s;
            security = s1;
        }
    }

    private static class List
    {

        static String _2D_get0(List list)
        {
            return list.mName;
        }

        private static int[] _2D_getmiui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues != null)
                return _2D_miui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues;
            int ai[] = new int[miui.maml.elements.ListScreenElement.ColumnInfo.Type.values().length];
            try
            {
                ai[miui.maml.elements.ListScreenElement.ColumnInfo.Type.BITMAP.ordinal()] = 6;
            }
            catch(NoSuchFieldError nosuchfielderror5) { }
            try
            {
                ai[miui.maml.elements.ListScreenElement.ColumnInfo.Type.DOUBLE.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror4) { }
            try
            {
                ai[miui.maml.elements.ListScreenElement.ColumnInfo.Type.FLOAT.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            try
            {
                ai[miui.maml.elements.ListScreenElement.ColumnInfo.Type.INTEGER.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[miui.maml.elements.ListScreenElement.ColumnInfo.Type.LONG.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[miui.maml.elements.ListScreenElement.ColumnInfo.Type.STRING.ordinal()] = 5;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues = ai;
            return ai;
        }

        public void fill(JSONArray jsonarray)
        {
            ArrayList arraylist;
            int i;
            Object aobj[];
            int j;
            if(mList == null)
            {
                mList = (ListScreenElement)mRoot.findElement(mName);
                if(mList == null)
                {
                    Log.e("WebServiceBinder", (new StringBuilder()).append("fail to find list: ").append(mName).toString());
                    return;
                }
            }
            mList.removeAllItems();
            if(jsonarray.length() == 0)
                return;
            arraylist = mList.getColumnsInfo();
            i = arraylist.size();
            aobj = new Object[i];
            j = 0;
_L2:
            if(j >= jsonarray.length())
                break MISSING_BLOCK_LABEL_338;
            Object obj;
            obj = jsonarray.get(j);
            if(obj instanceof JSONObject)
                break; /* Loop/switch isn't completed */
_L12:
            j++;
            if(true) goto _L2; else goto _L1
_L1:
            obj = (JSONObject)obj;
            int k = 0;
_L10:
            if(k >= i) goto _L4; else goto _L3
_L3:
            aobj[k] = null;
            miui.maml.elements.ListScreenElement.ColumnInfo columninfo = (miui.maml.elements.ListScreenElement.ColumnInfo)arraylist.get(k);
            _2D_getmiui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues()[columninfo.mType.ordinal()];
            JVM INSTR tableswitch 1 5: default 208
        //                       1 275
        //                       2 275
        //                       3 296
        //                       4 317
        //                       5 214;
               goto _L5 _L6 _L6 _L7 _L8 _L9
_L5:
            break; /* Loop/switch isn't completed */
_L8:
            break MISSING_BLOCK_LABEL_317;
_L11:
            k++;
              goto _L10
_L9:
            aobj[k] = ((JSONObject) (obj)).optString(columninfo.mVarName);
              goto _L11
            obj;
            Log.e("WebServiceBinder", (new StringBuilder()).append("JSON error: ").append(((JSONException) (obj)).toString()).toString());
_L4:
            mList.addItem(aobj);
              goto _L12
_L6:
            aobj[k] = Double.valueOf(((JSONObject) (obj)).optDouble(columninfo.mVarName));
              goto _L11
_L7:
            aobj[k] = Integer.valueOf(((JSONObject) (obj)).optInt(columninfo.mVarName));
              goto _L11
            aobj[k] = Long.valueOf(((JSONObject) (obj)).optLong(columninfo.mVarName));
              goto _L11
              goto _L12
        }

        public void fill(NodeList nodelist)
        {
            ArrayList arraylist;
            int i;
            Object aobj[];
            int j;
            if(nodelist == null)
                return;
            if(mList == null)
            {
                mList = (ListScreenElement)mRoot.findElement(mName);
                if(mList == null)
                {
                    Log.e("WebServiceBinder", (new StringBuilder()).append("fail to find list: ").append(mName).toString());
                    return;
                }
            }
            mList.removeAllItems();
            arraylist = mList.getColumnsInfo();
            i = arraylist.size();
            aobj = new Object[i];
            j = 0;
_L13:
            Element element;
            int k;
            if(j >= nodelist.getLength())
                break; /* Loop/switch isn't completed */
            element = (Element)nodelist.item(j);
            k = 0;
_L11:
            miui.maml.elements.ListScreenElement.ColumnInfo columninfo;
            Object obj;
            if(k >= i)
                break MISSING_BLOCK_LABEL_297;
            aobj[k] = null;
            columninfo = (miui.maml.elements.ListScreenElement.ColumnInfo)arraylist.get(k);
            obj = Utils.getChild(element, columninfo.mVarName);
            if(obj == null) goto _L2; else goto _L1
_L1:
            obj = ((Element) (obj)).getTextContent();
            if(obj == null) goto _L2; else goto _L3
_L3:
            _2D_getmiui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues()[columninfo.mType.ordinal()];
            JVM INSTR tableswitch 1 5: default 224
        //                       1 245
        //                       2 258
        //                       3 271
        //                       4 284
        //                       5 230;
               goto _L4 _L5 _L6 _L7 _L8 _L9
_L8:
            break MISSING_BLOCK_LABEL_284;
_L4:
            break; /* Loop/switch isn't completed */
_L9:
            break; /* Loop/switch isn't completed */
_L2:
            k++;
            if(true) goto _L11; else goto _L10
_L10:
            NumberFormatException numberformatexception;
            aobj[k] = obj;
              goto _L2
_L5:
            try
            {
                aobj[k] = Double.valueOf(((String) (obj)));
            }
            // Misplaced declaration of an exception variable
            catch(NumberFormatException numberformatexception) { }
              goto _L2
_L6:
            aobj[k] = Float.valueOf(((String) (obj)));
              goto _L2
_L7:
            aobj[k] = Integer.valueOf(((String) (obj)));
              goto _L2
            aobj[k] = Long.valueOf(((String) (obj)));
              goto _L2
            mList.addItem(aobj);
            j++;
            if(true) goto _L13; else goto _L12
_L12:
        }

        private static final int _2D_miui_2D_maml_2D_elements_2D_ListScreenElement$ColumnInfo$TypeSwitchesValues[];
        public String mDataPath;
        private ListScreenElement mList;
        private String mName;
        private ScreenElementRoot mRoot;

        public List(Element element, ScreenElementRoot screenelementroot)
        {
            mDataPath = element.getAttribute("path");
            if(TextUtils.isEmpty(mDataPath))
                mDataPath = element.getAttribute("xpath");
            mName = element.getAttribute("name");
            mRoot = screenelementroot;
        }
    }

    private class QueryThread extends Thread
    {

        private static int[] _2D_getmiui_2D_maml_2D_data_2D_WebServiceBinder$RequestMethodSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_data_2D_WebServiceBinder$RequestMethodSwitchesValues != null)
                return _2D_miui_2D_maml_2D_data_2D_WebServiceBinder$RequestMethodSwitchesValues;
            int ai[] = new int[RequestMethod.values().length];
            try
            {
                ai[RequestMethod.GET.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[RequestMethod.INVALID.ordinal()] = 7;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[RequestMethod.POST.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_data_2D_WebServiceBinder$RequestMethodSwitchesValues = ai;
            return ai;
        }

        private static int[] _2D_getmiui_2D_maml_2D_data_2D_WebServiceBinder$ResponseProtocolSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_data_2D_WebServiceBinder$ResponseProtocolSwitchesValues != null)
                return _2D_miui_2D_maml_2D_data_2D_WebServiceBinder$ResponseProtocolSwitchesValues;
            int ai[] = new int[ResponseProtocol.values().length];
            try
            {
                ai[ResponseProtocol.BITMAP.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            try
            {
                ai[ResponseProtocol.JSONarray.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[ResponseProtocol.JSONobj.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[ResponseProtocol.XML.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_data_2D_WebServiceBinder$ResponseProtocolSwitchesValues = ai;
            return ai;
        }

        private Object doRequest(String s, RequestMethod requestmethod, boolean flag, boolean flag1)
        {
            return doRequest(s, requestmethod, flag, flag1, true);
        }

        private Object doRequest(String s, RequestMethod requestmethod, boolean flag, boolean flag1, boolean flag2)
        {
            Object obj;
            AccountManager accountmanager;
            Object obj1;
            Log.i("WebServiceBinder", "doRequest");
            obj = null;
            accountmanager = AccountManager.get(getContext().mContext);
            if(!flag)
                break MISSING_BLOCK_LABEL_330;
            if(mEncryptedUser != null && mServiceToken != null)
                break MISSING_BLOCK_LABEL_291;
            obj = null;
            android.accounts.Account aaccount[] = accountmanager.getAccountsByType("com.xiaomi");
            if(aaccount.length > 0)
                obj = aaccount[0];
            if(obj == null)
            {
                WebServiceBinder._2D_wrap3(WebServiceBinder.this, 4);
                Log.e("WebServiceBinder", "xiaomi account not login");
                return null;
            }
            mEncryptedUser = accountmanager.getUserData(((android.accounts.Account) (obj)), "encrypted_user_id");
            obj1 = accountmanager.getAuthToken(((android.accounts.Account) (obj)), WebServiceBinder._2D_get4(WebServiceBinder.this), null, true, null, null);
            obj = null;
            if(obj1 == null) goto _L2; else goto _L1
_L1:
            obj1 = (Bundle)((AccountManagerFuture) (obj1)).getResult();
            if(obj1 == null) goto _L4; else goto _L3
_L3:
            obj1 = AuthToken.parse(((Bundle) (obj1)).getString("authtoken"));
            obj = obj1;
_L6:
            if(obj == null)
            {
                WebServiceBinder._2D_wrap3(WebServiceBinder.this, 5);
                return null;
            }
            break; /* Loop/switch isn't completed */
_L4:
            try
            {
                Log.d("WebServiceBinder", "getAuthToken: future getResult is null");
            }
            catch(OperationCanceledException operationcanceledexception)
            {
                WebServiceBinder._2D_wrap0(WebServiceBinder.this, operationcanceledexception);
            }
            catch(AuthenticatorException authenticatorexception)
            {
                WebServiceBinder._2D_wrap0(WebServiceBinder.this, authenticatorexception);
            }
            catch(IOException ioexception)
            {
                WebServiceBinder._2D_wrap0(WebServiceBinder.this, ioexception);
            }
            catch(Exception exception)
            {
                WebServiceBinder._2D_wrap0(WebServiceBinder.this, exception);
            }
            continue; /* Loop/switch isn't completed */
_L2:
            Log.d("WebServiceBinder", "getAuthToken: future is null");
            if(true) goto _L6; else goto _L5
_L5:
            mServiceToken = ((AuthToken) (obj)).authToken;
            mSecurity = ((AuthToken) (obj)).security;
            obj = new HashMap();
            ((HashMap) (obj)).put("cUserId", mEncryptedUser);
            ((HashMap) (obj)).put("serviceToken", mServiceToken);
            HashMap hashmap;
            hashmap = new HashMap();
            ioexception = WebServiceBinder._2D_get1(WebServiceBinder.this).getText();
            if(!TextUtils.isEmpty(ioexception))
            {
                String as1[] = ioexception.split(",");
                int i = 0;
                int j = as1.length;
                while(i < j) 
                {
                    String as[] = as1[i].split(":");
                    if(as.length == 2)
                        hashmap.put(as[0], as[1]);
                    i++;
                }
            }
            if(WebServiceBinder._2D_get2(WebServiceBinder.this) != ResponseProtocol.BITMAP)
                break MISSING_BLOCK_LABEL_465;
            obj = SimpleRequest.getAsStream(s, hashmap, null);
            if(obj == null)
                break MISSING_BLOCK_LABEL_614;
            WebServiceBinder._2D_wrap4(WebServiceBinder.this, 200);
            return obj;
            as = null;
            _2D_getmiui_2D_maml_2D_data_2D_WebServiceBinder$RequestMethodSwitchesValues()[requestmethod.ordinal()];
            JVM INSTR tableswitch 1 2: default 500
        //                       1 525
        //                       2 565;
               goto _L7 _L8 _L9
_L7:
            obj = as;
_L10:
            if(obj == null)
                break MISSING_BLOCK_LABEL_614;
            WebServiceBinder._2D_wrap4(WebServiceBinder.this, 200);
            return ((miui.maml.util.net.SimpleRequest.StringContent) (obj)).getBody();
_L8:
            if(!flag1)
                break MISSING_BLOCK_LABEL_551;
            obj = SecureRequest.getAsString(s, hashmap, ((java.util.Map) (obj)), true, mSecurity);
              goto _L10
            obj = SimpleRequest.getAsString(s, hashmap, ((java.util.Map) (obj)), true);
              goto _L10
_L9:
            if(!flag1)
                break MISSING_BLOCK_LABEL_591;
            obj = SecureRequest.postAsString(s, hashmap, ((java.util.Map) (obj)), true, mSecurity);
              goto _L10
            obj = SimpleRequest.postAsString(s, hashmap, ((java.util.Map) (obj)), true);
              goto _L10
            s;
            WebServiceBinder._2D_wrap0(WebServiceBinder.this, s);
_L12:
            return null;
            AuthenticationFailureException authenticationfailureexception;
            authenticationfailureexception;
            WebServiceBinder._2D_wrap0(WebServiceBinder.this, authenticationfailureexception);
            WebServiceBinder._2D_wrap4(WebServiceBinder.this, 400);
            accountmanager.invalidateAuthToken("com.xiaomi", mServiceToken);
            mServiceToken = null;
            if(flag2)
                return doRequest(s, requestmethod, flag, flag1, false);
            continue; /* Loop/switch isn't completed */
            s;
            WebServiceBinder._2D_wrap0(WebServiceBinder.this, s);
            WebServiceBinder._2D_wrap3(WebServiceBinder.this, 7);
            continue; /* Loop/switch isn't completed */
            s;
            WebServiceBinder._2D_wrap0(WebServiceBinder.this, s);
            WebServiceBinder._2D_wrap4(WebServiceBinder.this, 403);
            continue; /* Loop/switch isn't completed */
            s;
            WebServiceBinder._2D_wrap0(WebServiceBinder.this, s);
            WebServiceBinder._2D_wrap3(WebServiceBinder.this, 6);
            continue; /* Loop/switch isn't completed */
            s;
            WebServiceBinder._2D_wrap0(WebServiceBinder.this, s);
            WebServiceBinder._2D_wrap3(WebServiceBinder.this, 8);
            if(true) goto _L12; else goto _L11
_L11:
        }

        public void run()
        {
            Object obj;
            Log.i("WebServiceBinder", "QueryThread start");
            WebServiceBinder._2D_wrap3(WebServiceBinder.this, 0);
            WebServiceBinder._2D_wrap4(WebServiceBinder.this, 0);
            obj = mUriFormatter.getText();
            if(obj != null)
                obj = ((String) (obj)).replaceAll(" ", "");
            else
                obj = null;
            if(TextUtils.isEmpty(((CharSequence) (obj)))) goto _L2; else goto _L1
_L1:
            obj = doRequest(((String) (obj)), mRequestMethod, WebServiceBinder._2D_get0(WebServiceBinder.this), WebServiceBinder._2D_get3(WebServiceBinder.this));
            if(mContentStringVar != null)
                if(obj != null && (obj instanceof String))
                    mContentStringVar.set((String)obj);
                else
                    mContentStringVar.set(null);
            if(obj == null) goto _L4; else goto _L3
_L3:
            _2D_getmiui_2D_maml_2D_data_2D_WebServiceBinder$ResponseProtocolSwitchesValues()[WebServiceBinder._2D_get2(WebServiceBinder.this).ordinal()];
            JVM INSTR tableswitch 1 4: default 168
        //                       1 242
        //                       2 228
        //                       3 228
        //                       4 214;
               goto _L4 _L5 _L6 _L6 _L7
_L4:
            onUpdateComplete();
            WebServiceBinder._2D_set0(WebServiceBinder.this, false);
            Log.i("WebServiceBinder", "QueryThread end");
            return;
_L7:
            WebServiceBinder._2D_wrap2(WebServiceBinder.this, (String)obj);
            continue; /* Loop/switch isn't completed */
_L6:
            WebServiceBinder._2D_wrap1(WebServiceBinder.this, (String)obj);
            continue; /* Loop/switch isn't completed */
_L5:
            processResponseBitmap((miui.maml.util.net.SimpleRequest.StreamContent)obj);
            continue; /* Loop/switch isn't completed */
_L2:
            Log.w("WebServiceBinder", "url is null");
            if(true) goto _L4; else goto _L8
_L8:
        }

        private static final int _2D_miui_2D_maml_2D_data_2D_WebServiceBinder$RequestMethodSwitchesValues[];
        private static final int _2D_miui_2D_maml_2D_data_2D_WebServiceBinder$ResponseProtocolSwitchesValues[];
        private static final int ERROR_IO_EXCEPTION = 8;
        private static final int ERROR_OK = 0;
        private static final int ERROR_SECURE_ACCOUNT_AUTHTOKEN_FAIL = 5;
        private static final int ERROR_SECURE_ACCOUNT_NOT_LOGIN = 4;
        private static final int ERROR_SECURE_CIPHER_EXCEPTION = 6;
        private static final int ERROR_SECURE_INVALID_RESPONSE = 7;
        public static final int ERROR_USE_NETWORK_FORBIDDEN = 3;
        private static final String KEY_ENCRYPTED_USER_ID = "encrypted_user_id";
        final int $SWITCH_TABLE$miui$maml$data$WebServiceBinder$RequestMethod[];
        final int $SWITCH_TABLE$miui$maml$data$WebServiceBinder$ResponseProtocol[];
        final WebServiceBinder this$0;

        public QueryThread()
        {
            this$0 = WebServiceBinder.this;
            super("WebServiceBinder QueryThread");
        }
    }

    static final class RequestMethod extends Enum
    {

        public static RequestMethod valueOf(String s)
        {
            return (RequestMethod)Enum.valueOf(miui/maml/data/WebServiceBinder$RequestMethod, s);
        }

        public static RequestMethod[] values()
        {
            return $VALUES;
        }

        private static final RequestMethod $VALUES[];
        public static final RequestMethod GET;
        public static final RequestMethod INVALID;
        public static final RequestMethod POST;

        static 
        {
            INVALID = new RequestMethod("INVALID", 0);
            POST = new RequestMethod("POST", 1);
            GET = new RequestMethod("GET", 2);
            $VALUES = (new RequestMethod[] {
                INVALID, POST, GET
            });
        }

        private RequestMethod(String s, int i)
        {
            super(s, i);
        }
    }

    static final class ResponseProtocol extends Enum
    {

        public static ResponseProtocol valueOf(String s)
        {
            return (ResponseProtocol)Enum.valueOf(miui/maml/data/WebServiceBinder$ResponseProtocol, s);
        }

        public static ResponseProtocol[] values()
        {
            return $VALUES;
        }

        private static final ResponseProtocol $VALUES[];
        public static final ResponseProtocol BITMAP;
        public static final ResponseProtocol JSONarray;
        public static final ResponseProtocol JSONobj;
        public static final ResponseProtocol XML;

        static 
        {
            XML = new ResponseProtocol("XML", 0);
            JSONobj = new ResponseProtocol("JSONobj", 1);
            JSONarray = new ResponseProtocol("JSONarray", 2);
            BITMAP = new ResponseProtocol("BITMAP", 3);
            $VALUES = (new ResponseProtocol[] {
                XML, JSONobj, JSONarray, BITMAP
            });
        }

        private ResponseProtocol(String s, int i)
        {
            super(s, i);
        }
    }

    public static class Variable extends VariableBinder.Variable
    {

        static boolean _2D_wrap0(Variable variable, String s)
        {
            return variable.hasCache(s);
        }

        private final String getCacheName()
        {
            return (new StringBuilder()).append(mName).append(".cache").toString();
        }

        private boolean hasCache(String s)
        {
            boolean flag;
            if(mCache)
                flag = TextUtils.isEmpty(s) ^ true;
            else
                flag = false;
            return flag;
        }

        public void loadCache(String s)
        {
            Object obj;
            Object obj1;
            Object obj2;
            Object obj3;
            Object obj4;
            if(mType != 7 || hasCache(s) ^ true)
                return;
            obj = null;
            obj1 = null;
            obj2 = null;
            obj3 = null;
            obj4 = obj1;
            Object obj7 = JVM INSTR new #85  <Class FileInputStream>;
            obj4 = obj1;
            File file = JVM INSTR new #87  <Class File>;
            obj4 = obj1;
            file.File(s, getCacheName());
            obj4 = obj1;
            ((FileInputStream) (obj7)).FileInputStream(file);
            s = BitmapFactory.decodeStream(((InputStream) (obj7)));
            IOUtils.closeQuietly(((InputStream) (obj7)));
_L1:
            set(s);
            return;
            obj7;
            s = obj2;
_L5:
            obj4 = s;
            Log.e("WebServiceBinder", ((OutOfMemoryError) (obj7)).toString());
            IOUtils.closeQuietly(s);
            s = obj3;
              goto _L1
            obj7;
            s = obj;
_L4:
            obj4 = s;
            Log.e("WebServiceBinder", ((FileNotFoundException) (obj7)).toString());
            IOUtils.closeQuietly(s);
            s = obj3;
              goto _L1
            s;
_L3:
            IOUtils.closeQuietly(((InputStream) (obj4)));
            throw s;
            s;
            obj4 = obj7;
            if(true) goto _L3; else goto _L2
_L2:
            s;
            Object obj5 = obj7;
            obj7 = s;
            s = ((String) (obj5));
              goto _L4
            s;
            Object obj6 = obj7;
            obj7 = s;
            s = ((String) (obj6));
              goto _L5
        }

        public void saveCache(InputStream inputstream, String s)
        {
            if(!hasCache(s))
                break MISSING_BLOCK_LABEL_119;
            File file = new File(s);
            if(!file.exists())
                FileUtils.mkdirs(file, 493, -1, -1);
            File file1;
            file1 = JVM INSTR new #87  <Class File>;
            file1.File(s, getCacheName());
            file1.delete();
            if(inputstream == null)
                break MISSING_BLOCK_LABEL_119;
            s = JVM INSTR new #142 <Class FileOutputStream>;
            s.FileOutputStream(file1, false);
            byte abyte0[] = new byte[0x10000];
_L1:
            int i = inputstream.read(abyte0, 0, 0x10000);
            if(i <= 0)
                break MISSING_BLOCK_LABEL_120;
            s.write(abyte0, 0, i);
              goto _L1
            inputstream;
            try
            {
                IOUtils.closeQuietly(s);
                throw inputstream;
            }
            // Misplaced declaration of an exception variable
            catch(InputStream inputstream)
            {
                Log.e("WebServiceBinder", inputstream.toString());
                inputstream.printStackTrace();
            }
            // Misplaced declaration of an exception variable
            catch(InputStream inputstream)
            {
                Log.e("WebServiceBinder", inputstream.toString());
                inputstream.printStackTrace();
            }
            // Misplaced declaration of an exception variable
            catch(InputStream inputstream)
            {
                Log.e("WebServiceBinder", inputstream.toString());
                inputstream.printStackTrace();
            }
            return;
            IOUtils.closeQuietly(s);
            break MISSING_BLOCK_LABEL_119;
        }

        public void set(Object obj)
        {
            if(!isArray() || !(obj instanceof JSONArray)) goto _L2; else goto _L1
_L1:
            JSONArray jsonarray;
            Object obj1;
            int i;
            int j;
            jsonarray = (JSONArray)obj;
            obj1 = mVar.getVariables().get(mVar.getIndex());
            i = 0;
            j = 0;
            if(!(obj1 instanceof double[])) goto _L4; else goto _L3
_L3:
            int k;
            i = ((double[])obj1).length;
            k = 1;
_L17:
            j = 0;
_L13:
            if(j >= i) goto _L6; else goto _L5
_L5:
            obj1 = null;
            Object obj2 = obj1;
            double d;
            Object obj3;
            double d1;
            try
            {
                if(j < jsonarray.length())
                    obj2 = jsonarray.get(j);
            }
            catch(JSONException jsonexception)
            {
                jsonexception = ((JSONException) (obj1));
            }
            d = 0.0D;
            obj3 = null;
            d1 = d;
            obj1 = obj3;
            if(obj2 == null) goto _L8; else goto _L7
_L7:
            if(obj2 == JSONObject.NULL || !(obj2 instanceof JSONObject)) goto _L10; else goto _L9
_L9:
            obj2 = (new JSONPath((JSONObject)obj2)).get(mInnerPath);
            d1 = d;
            obj1 = obj3;
            if(!(obj2 instanceof String)) goto _L8; else goto _L11
_L11:
            obj2 = (String)obj2;
            d1 = d;
            obj1 = obj2;
            if(k == 0) goto _L8; else goto _L12
_L12:
            d1 = Utils.parseDouble(((String) (obj2)));
            obj1 = obj2;
_L8:
            if(k != 0)
                mVar.setArr(j, d1);
            else
                mVar.setArr(j, obj1);
            j++;
              goto _L13
_L4:
            k = j;
            if(obj1 instanceof String[])
            {
                i = ((String[])obj1).length;
                k = j;
            }
            continue; /* Loop/switch isn't completed */
            obj1;
            d1 = d;
            obj1 = obj2;
              goto _L8
_L10:
            d1 = d;
            obj1 = obj3;
            if(!(obj2 instanceof String)) goto _L8; else goto _L14
_L14:
            obj2 = (String)obj;
            d1 = d;
            obj1 = obj2;
            if(k == 0) goto _L8; else goto _L15
_L15:
            d1 = Utils.parseDouble(((String) (obj2)));
            obj1 = obj2;
              goto _L8
            obj1;
            d1 = d;
            obj1 = obj2;
              goto _L8
_L2:
            super.set(obj);
_L6:
            return;
            if(true) goto _L17; else goto _L16
_L16:
        }

        private boolean mCache;
        private String mInnerPath;
        public String mPath;

        public Variable(Element element, Variables variables)
        {
            super(element, variables);
            mPath = element.getAttribute("xpath");
            if(TextUtils.isEmpty(mPath))
                mPath = element.getAttribute("path");
            mInnerPath = element.getAttribute("innerPath");
            mCache = Boolean.parseBoolean(element.getAttribute("cache"));
        }
    }


    static boolean _2D_get0(WebServiceBinder webservicebinder)
    {
        return webservicebinder.mAuthentication;
    }

    static TextFormatter _2D_get1(WebServiceBinder webservicebinder)
    {
        return webservicebinder.mParamsFormatter;
    }

    static ResponseProtocol _2D_get2(WebServiceBinder webservicebinder)
    {
        return webservicebinder.mProtocol;
    }

    static boolean _2D_get3(WebServiceBinder webservicebinder)
    {
        return webservicebinder.mSecure;
    }

    static String _2D_get4(WebServiceBinder webservicebinder)
    {
        return webservicebinder.mServiceId;
    }

    static boolean _2D_set0(WebServiceBinder webservicebinder, boolean flag)
    {
        webservicebinder.mQueryInProgress = flag;
        return flag;
    }

    static void _2D_wrap0(WebServiceBinder webservicebinder, Exception exception)
    {
        webservicebinder.handleException(exception);
    }

    static void _2D_wrap1(WebServiceBinder webservicebinder, String s)
    {
        webservicebinder.processResponseJson(s);
    }

    static void _2D_wrap2(WebServiceBinder webservicebinder, String s)
    {
        webservicebinder.processResponseXml(s);
    }

    static void _2D_wrap3(WebServiceBinder webservicebinder, int i)
    {
        webservicebinder.setErrorCode(i);
    }

    static void _2D_wrap4(WebServiceBinder webservicebinder, int i)
    {
        webservicebinder.setStatusCode(i);
    }

    public WebServiceBinder(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        mUpdateInterval = -1;
        mRequestMethod = RequestMethod.POST;
        mProtocol = ResponseProtocol.XML;
        mUseNetwork = 2;
        load(element);
    }

    private boolean canUseNetwork()
    {
        if(mUseNetwork == 2)
            return true;
        if(mUseNetwork == 0)
            return false;
        return mUseNetwork == 1 && ConnectivityHelper.getInstance().isWifiConnected();
    }

    private void handleException(Exception exception)
    {
        Log.e("WebServiceBinder", exception.toString());
        exception.printStackTrace();
        setErrorString(exception.toString());
    }

    private void load(Element element)
    {
        if(element == null)
        {
            Log.e("WebServiceBinder", "WebServiceBinder node is null");
            throw new NullPointerException("node is null");
        }
        if("get".equalsIgnoreCase(element.getAttribute("requestMethod")))
            mRequestMethod = RequestMethod.GET;
        mQueryAtStart = Boolean.parseBoolean(element.getAttribute("queryAtStart"));
        Object obj = getVariables();
        Object obj1 = Expression.build(((Variables) (obj)), element.getAttribute("uriExp"));
        Expression expression = Expression.build(((Variables) (obj)), element.getAttribute("uriFormatExp"));
        mUriFormatter = new TextFormatter(((Variables) (obj)), element.getAttribute("uri"), element.getAttribute("uriFormat"), element.getAttribute("uriParas"), ((Expression) (obj1)), expression);
        mParamsFormatter = new TextFormatter(((Variables) (obj)), element.getAttribute("params"), element.getAttribute("paramsFormat"), element.getAttribute("paramsParas"));
        mUpdateInterval = Utils.getAttrAsInt(element, "updateInterval", -1);
        parseProtocol(element.getAttribute("protocol"));
        mAuthentication = Boolean.parseBoolean(element.getAttribute("authentication"));
        mSecure = Boolean.parseBoolean(element.getAttribute("secure"));
        mServiceId = element.getAttribute("serviceID");
        obj1 = element.getAttribute("useNetwork");
        if(TextUtils.isEmpty(((CharSequence) (obj1))) || "all".equalsIgnoreCase(((String) (obj1))))
            mUseNetwork = 2;
        else
        if("wifi".equalsIgnoreCase(((String) (obj1))))
            mUseNetwork = 1;
        else
        if("none".equalsIgnoreCase(((String) (obj1))))
            mUseNetwork = 0;
        else
            mUseNetworkExp = Expression.build(((Variables) (obj)), ((String) (obj1)));
        loadVariables(element);
        if(!TextUtils.isEmpty(mName))
        {
            mStatusCodeVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("statusCode").toString(), ((Variables) (obj)), true);
            mErrorCodeVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("errorCode").toString(), ((Variables) (obj)), true);
            mErrorStringVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("errorString").toString(), ((Variables) (obj)), false);
            if(Boolean.parseBoolean(element.getAttribute("dbgContentString")))
                mContentStringVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("contentString").toString(), ((Variables) (obj)), false);
        }
        obj = Utils.getChild(element, "List");
        if(obj == null)
            break MISSING_BLOCK_LABEL_491;
        element = JVM INSTR new #9   <Class WebServiceBinder$List>;
        element.List(((Element) (obj)), mRoot);
        mList = element;
_L1:
        return;
        element;
        Log.e("WebServiceBinder", "invalid List");
          goto _L1
    }

    private void parseProtocol(String s)
    {
        if(!"xml".equalsIgnoreCase(s)) goto _L2; else goto _L1
_L1:
        mProtocol = ResponseProtocol.XML;
_L4:
        return;
_L2:
        if("json/obj".equalsIgnoreCase(s))
            mProtocol = ResponseProtocol.JSONobj;
        else
        if("json/array".equalsIgnoreCase(s))
            mProtocol = ResponseProtocol.JSONarray;
        else
        if("bitmap".equalsIgnoreCase(s))
            mProtocol = ResponseProtocol.BITMAP;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void processResponseJson(String s)
    {
        if(mProtocol != ResponseProtocol.JSONobj) goto _L2; else goto _L1
_L1:
        JSONPath jsonpath;
        jsonpath = JVM INSTR new #343 <Class JSONPath>;
        JSONObject jsonobject = JVM INSTR new #345 <Class JSONObject>;
        jsonobject.JSONObject(s);
        jsonpath.JSONPath(jsonobject);
        s = jsonpath;
_L4:
        Variable variable;
        for(Iterator iterator = mVariables.iterator(); iterator.hasNext(); variable.set(s.get(variable.mPath)))
            variable = (Variable)(VariableBinder.Variable)iterator.next();

          goto _L3
        s;
        handleException(s);
_L6:
        return;
_L2:
        JSONArray jsonarray = JVM INSTR new #382 <Class JSONArray>;
        jsonarray.JSONArray(s);
        s = new JSONPath(jsonarray);
          goto _L4
_L3:
        if(mList == null) goto _L6; else goto _L5
_L5:
        s = ((String) (s.get(mList.mDataPath)));
        if(s == null) goto _L6; else goto _L7
_L7:
        if(!(s instanceof JSONArray)) goto _L6; else goto _L8
_L8:
        mList.fill((JSONArray)s);
          goto _L6
    }

    private void processResponseXml(String s)
    {
        XPath xpath;
        Object obj;
        Iterator iterator;
        XPathExpressionException xpathexpressionexception;
        Object obj1;
        Object obj2;
        Object obj3;
        Variable variable;
        Object obj4;
        xpath = XPathFactory.newInstance().newXPath();
        obj = DocumentBuilderFactory.newInstance();
        iterator = null;
        xpathexpressionexception = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        variable = null;
        obj4 = obj3;
        DocumentBuilder documentbuilder = ((DocumentBuilderFactory) (obj)).newDocumentBuilder();
        obj4 = obj3;
        obj = JVM INSTR new #423 <Class ByteArrayInputStream>;
        obj4 = obj3;
        ((ByteArrayInputStream) (obj)).ByteArrayInputStream(s.getBytes("utf-8"));
        s = documentbuilder.parse(((InputStream) (obj)));
        iterator = mVariables.iterator();
_L3:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        variable = (Variable)(VariableBinder.Variable)iterator.next();
        variable.set(xpath.evaluate(variable.mPath, s, XPathConstants.STRING));
          goto _L3
        xpathexpressionexception;
        variable.set(null);
        obj4 = JVM INSTR new #272 <Class StringBuilder>;
        ((StringBuilder) (obj4)).StringBuilder();
        Log.e("WebServiceBinder", ((StringBuilder) (obj4)).append("fail to get variable: ").append(variable.mName).append(" :").append(xpathexpressionexception.toString()).toString());
          goto _L3
        obj4;
        s = ((String) (obj));
        obj = obj4;
_L8:
        obj4 = s;
        handleException(((Exception) (obj)));
        if(s == null)
            break MISSING_BLOCK_LABEL_211;
        s.close();
_L4:
        return;
_L2:
        obj4 = mList;
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_252;
        s = (NodeList)xpath.evaluate(mList.mDataPath, s, XPathConstants.NODESET);
        mList.fill(s);
_L5:
        if(obj != null)
            try
            {
                ((InputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
          goto _L4
        obj4;
        s = JVM INSTR new #272 <Class StringBuilder>;
        s.StringBuilder();
        Log.e("WebServiceBinder", s.append("fail to get list: ").append(List._2D_get0(mList)).append(" :").append(((XPathExpressionException) (obj4)).toString()).toString());
          goto _L5
        obj4;
        s = ((String) (obj));
        obj = obj4;
_L9:
        obj4 = s;
        handleException(((Exception) (obj)));
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
          goto _L4
        obj;
        s = xpathexpressionexception;
_L12:
        obj4 = s;
        handleException(((Exception) (obj)));
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
          goto _L4
        obj;
        s = obj1;
_L11:
        obj4 = s;
        handleException(((Exception) (obj)));
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
          goto _L4
        obj;
        s = obj2;
_L10:
        obj4 = s;
        handleException(((Exception) (obj)));
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
          goto _L4
        s;
          goto _L4
        s;
_L7:
        if(obj4 != null)
            try
            {
                ((InputStream) (obj4)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj4) { }
        throw s;
        s;
        obj4 = obj;
        if(true) goto _L7; else goto _L6
_L6:
        obj;
        s = variable;
          goto _L8
        obj;
        s = iterator;
          goto _L9
        s;
        Object obj5 = obj;
        obj = s;
        s = ((String) (obj5));
          goto _L10
        IOException ioexception;
        ioexception;
        s = ((String) (obj));
        obj = ioexception;
          goto _L11
        s;
        Object obj6 = obj;
        obj = s;
        s = ((String) (obj6));
          goto _L12
    }

    private void setErrorCode(int i)
    {
        if(mErrorCodeVar != null)
            mErrorCodeVar.set(i);
        Log.e("WebServiceBinder", (new StringBuilder()).append("QueryThread error: #").append(i).toString());
    }

    private void setErrorString(String s)
    {
        if(mErrorStringVar != null)
            mErrorStringVar.set(s);
    }

    private void setStatusCode(int i)
    {
        if(mStatusCodeVar != null)
            mStatusCodeVar.set(i);
        Log.d("WebServiceBinder", (new StringBuilder()).append("QueryThread status code: #").append(i).toString());
    }

    private void tryStartQuery()
    {
        long l = System.currentTimeMillis() - mLastQueryTime;
        if(l < 0L)
            mLastQueryTime = 0L;
        if(mLastQueryTime == 0L || mUpdateInterval > 0 && l > (long)(mUpdateInterval * 1000))
            startQuery();
    }

    public void finish()
    {
        super.finish();
    }

    public void init()
    {
        super.init();
        mEncryptedUser = null;
        mServiceToken = null;
        mSecurity = null;
        if(mUseNetworkExp != null)
            mUseNetwork = (int)mUseNetworkExp.evaluate();
        if(mVariables.size() > 0)
            ((Variable)mVariables.get(0)).loadCache(mRoot.getCacheDir());
        if(mQueryAtStart)
        {
            mLastQueryTime = 0L;
            tryStartQuery();
        }
    }

    protected volatile VariableBinder.Variable onLoadVariable(Element element)
    {
        return onLoadVariable(element);
    }

    protected Variable onLoadVariable(Element element)
    {
        return new Variable(element, getContext().mVariables);
    }

    public void pause()
    {
        super.pause();
    }

    public void processResponseBitmap(miui.maml.util.net.SimpleRequest.StreamContent streamcontent)
    {
        if(mVariables.size() < 1)
        {
            Log.w("WebServiceBinder", "no image element var");
            return;
        }
        InputStream inputstream = streamcontent.getStream();
        Variable variable = (Variable)mVariables.get(0);
        streamcontent = mRoot.getCacheDir();
        if(Variable._2D_wrap0(variable, streamcontent))
        {
            variable.saveCache(inputstream, streamcontent);
            variable.loadCache(streamcontent);
        } else
        {
            streamcontent = null;
            if(inputstream != null)
            {
                android.graphics.Bitmap bitmap = BitmapFactory.decodeStream(inputstream);
                streamcontent = bitmap;
                if(bitmap == null)
                {
                    Log.w("WebServiceBinder", "decoded bitmap is null");
                    streamcontent = bitmap;
                }
            } else
            {
                Log.w("WebServiceBinder", "response stream is null");
            }
            variable.set(streamcontent);
        }
        IOUtils.closeQuietly(inputstream);
    }

    public void refresh()
    {
        super.refresh();
        startQuery();
    }

    public void resume()
    {
        super.resume();
        if(mQueryAtStart)
            tryStartQuery();
    }

    public void startQuery()
    {
        if(!mRoot.getCapability(1))
        {
            Log.w("WebServiceBinder", "capability disabled: webservice");
            return;
        }
        if(mQueryInProgress)
            return;
        mLastQueryTime = System.currentTimeMillis();
        if(!canUseNetwork())
        {
            if(mErrorCodeVar != null)
                mErrorCodeVar.set(3D);
            if(mErrorStringVar != null)
                mErrorStringVar.set((new StringBuilder()).append("cancel query because current network is forbidden by useNetwork config: ").append(mUseNetwork).toString());
            Log.w("WebServiceBinder", (new StringBuilder()).append("cancel query because current network is forbidden by useNetwork config: ").append(mUseNetwork).toString());
            return;
        } else
        {
            mQueryInProgress = true;
            mQueryThread = new QueryThread();
            mQueryThread.start();
            return;
        }
    }

    private static final String LOG_TAG = "WebServiceBinder";
    public static final String TAG_NAME = "WebServiceBinder";
    private boolean mAuthentication;
    IndexedVariable mContentStringVar;
    public String mEncryptedUser;
    IndexedVariable mErrorCodeVar;
    IndexedVariable mErrorStringVar;
    private long mLastQueryTime;
    private List mList;
    private TextFormatter mParamsFormatter;
    private ResponseProtocol mProtocol;
    private boolean mQueryInProgress;
    private Thread mQueryThread;
    RequestMethod mRequestMethod;
    private boolean mSecure;
    public String mSecurity;
    private String mServiceId;
    public String mServiceToken;
    IndexedVariable mStatusCodeVar;
    private int mUpdateInterval;
    protected TextFormatter mUriFormatter;
    private int mUseNetwork;
    private Expression mUseNetworkExp;
}
