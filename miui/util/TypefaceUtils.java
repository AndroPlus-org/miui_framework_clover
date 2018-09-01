// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashSet;
import miui.os.Build;
import miui.os.Environment;

public class TypefaceUtils
{
    private static class FontsWhiteListHolder
    {

        static HashSet _2D_get0()
        {
            return mFontsWhiteList;
        }

        private static final HashSet mFontsWhiteList;

        static 
        {
            mFontsWhiteList = new HashSet();
            mFontsWhiteList.add("com.tencent.mm");
            mFontsWhiteList.add("com.tencent.mobileqq");
            mFontsWhiteList.add("com.UCMobile");
            mFontsWhiteList.add("com.qzone");
            mFontsWhiteList.add("com.sina.weibo");
            mFontsWhiteList.add("com.qvod.player");
            mFontsWhiteList.add("com.qihoo360.mobilesafe");
            mFontsWhiteList.add("com.kugou.android");
            mFontsWhiteList.add("com.taobao.taobao");
            mFontsWhiteList.add("com.baidu.BaiduMap");
            mFontsWhiteList.add("com.youku.phone");
            mFontsWhiteList.add("com.sds.android.ttpod");
            mFontsWhiteList.add("com.qihoo.appstore");
            mFontsWhiteList.add("com.pplive.androidphone");
            mFontsWhiteList.add("com.tencent.minihd.qq");
            mFontsWhiteList.add("tv.pps.mobile");
            mFontsWhiteList.add("com.xiaomi.channel");
            mFontsWhiteList.add("com.shuqi.controller");
            mFontsWhiteList.add("com.storm.smart");
            mFontsWhiteList.add("com.tencent.qbx");
            mFontsWhiteList.add("com.moji.mjweather");
            mFontsWhiteList.add("com.wandoujia.phoenix2");
            mFontsWhiteList.add("com.renren.mobile.android");
            mFontsWhiteList.add("com.duokan.reader");
            mFontsWhiteList.add("com.immomo.momo");
            mFontsWhiteList.add("com.tencent.news");
            mFontsWhiteList.add("com.tencent.qqmusic");
            mFontsWhiteList.add("com.qiyi.video");
            mFontsWhiteList.add("com.baidu.video");
            mFontsWhiteList.add("com.tencent.WBlog");
            mFontsWhiteList.add("qsbk.app");
            mFontsWhiteList.add("com.netease.newsreader.activity");
            mFontsWhiteList.add("com.sohu.newsclient");
            mFontsWhiteList.add("com.tencent.mtt");
            mFontsWhiteList.add("com.baidu.tieba");
            mFontsWhiteList.add("com.wochacha");
            mFontsWhiteList.add("com.tencent.qqpimsecure");
            mFontsWhiteList.add("com.xiaomi.shop");
            mFontsWhiteList.add("com.mt.mtxx.mtxx");
            mFontsWhiteList.add("com.qihoo360.mobilesafe.opti.powerctl");
            mFontsWhiteList.add("com.dragon.android.pandaspace");
            mFontsWhiteList.add("cn.etouch.ecalendar");
            mFontsWhiteList.add("com.changba");
            mFontsWhiteList.add("com.xiaomi.xmsf");
            mFontsWhiteList.add("com.tencent.qqlive");
            mFontsWhiteList.add("com.chaozh.iReaderFree");
            mFontsWhiteList.add("com.snda.wifilocating");
            mFontsWhiteList.add("com.ijinshan.kbatterydoctor");
            mFontsWhiteList.add("com.duowan.mobile");
            mFontsWhiteList.add("com.hiapk.marketpho");
            mFontsWhiteList.add("com.qihoo360.launcher");
            mFontsWhiteList.add("com.qihoo360.mobilesafe.opti");
            mFontsWhiteList.add("cn.com.fetion");
            mFontsWhiteList.add("com.nd.android.pandahome2");
            mFontsWhiteList.add("com.youdao.dict");
            mFontsWhiteList.add("com.eg.android.AlipayGphone");
            mFontsWhiteList.add("cn.kuwo.player");
            mFontsWhiteList.add("cn.wps.moffice");
            mFontsWhiteList.add("com.alibaba.mobileim");
            mFontsWhiteList.add("com.letv.android.client");
            mFontsWhiteList.add("com.baidu.searchbox");
            mFontsWhiteList.add("com.funshion.video.mobile");
            mFontsWhiteList.add("com.gau.go.launcherex");
            mFontsWhiteList.add("cn.opda.a.phonoalbumshoushou");
            mFontsWhiteList.add("com.qq.reader");
            mFontsWhiteList.add("com.duomi.android");
            mFontsWhiteList.add("com.qihoo.browser");
            mFontsWhiteList.add("com.meitu.meiyancamera");
            mFontsWhiteList.add("com.nd.android.pandareader");
            mFontsWhiteList.add("com.kingsoft");
            mFontsWhiteList.add("com.cleanmaster.mguard");
            mFontsWhiteList.add("com.sohu.sohuvideo");
            mFontsWhiteList.add("com.jingdong.app.mall");
            mFontsWhiteList.add("bubei.tingshu");
            mFontsWhiteList.add("com.alipay.android.app");
            mFontsWhiteList.add("vStudio.Android.Camera360");
            mFontsWhiteList.add("com.androidesk");
            mFontsWhiteList.add("com.ss.android.article.news");
            mFontsWhiteList.add("org.funship.findsomething.withRK");
            mFontsWhiteList.add("com.mybook66");
            mFontsWhiteList.add("com.tencent.token");
            mFontsWhiteList.add("com.tmall.wireless");
            mFontsWhiteList.add("com.tencent.qqgame.qqlordwvga");
            mFontsWhiteList.add("com.budejie.www");
            mFontsWhiteList.add("com.sankuai.meituan");
            mFontsWhiteList.add("com.google.android.apps.maps");
            mFontsWhiteList.add("com.kascend.video");
            mFontsWhiteList.add("com.tencent.android.pad");
            mFontsWhiteList.add("com.muzhiwan.market");
            mFontsWhiteList.add("com.mymoney");
            mFontsWhiteList.add("com.baidu.browser.apps");
            mFontsWhiteList.add("com.geili.koudai");
            mFontsWhiteList.add("com.baidu.news");
            mFontsWhiteList.add("com.tencent.androidqqmail");
            mFontsWhiteList.add("com.myzaker.ZAKER_Phone");
            mFontsWhiteList.add("com.ifeng.news2");
            mFontsWhiteList.add("com.handsgo.jiakao.android");
            mFontsWhiteList.add("com.hexin.plat.android");
            mFontsWhiteList.add("com.tencent.qqphonebook");
            mFontsWhiteList.add("my.beautyCamera");
            mFontsWhiteList.add("com.autonavi.minimap");
            mFontsWhiteList.add("com.cubic.autohome");
            mFontsWhiteList.add("com.clov4r.android.nil");
            mFontsWhiteList.add("com.yangzhibin.chengrenxiaohua");
            mFontsWhiteList.add("com.dianxinos.powermanager");
            mFontsWhiteList.add("com.ijinshan.duba");
            mFontsWhiteList.add("com.wuba");
            mFontsWhiteList.add("sina.mobile.tianqitong");
            mFontsWhiteList.add("com.mandi.lol");
            mFontsWhiteList.add("com.duowan.lolbox");
            mFontsWhiteList.add("com.android.chrome");
            mFontsWhiteList.add("com.chinamworld.main");
            mFontsWhiteList.add("com.ss.android.essay.joke");
            mFontsWhiteList.add("air.com.tencent.qqpasture");
            mFontsWhiteList.add("com.kingreader.framework");
            mFontsWhiteList.add("cn.ibuka.manga.ui");
            mFontsWhiteList.add("com.ting.mp3.qianqian.android");
            mFontsWhiteList.add("com.jiubang.goscreenlock");
            mFontsWhiteList.add("com.shoujiduoduo.ringtone");
            mFontsWhiteList.add("com.lbe.security");
            mFontsWhiteList.add("com.snda.youni");
            mFontsWhiteList.add("com.jiasoft.swreader");
            mFontsWhiteList.add("com.anyview");
            mFontsWhiteList.add("com.baidu.appsearch");
            mFontsWhiteList.add("com.sohu.inputmethod.sogou");
            mFontsWhiteList.add("com.mxtech.videoplayer.ad");
            mFontsWhiteList.add("com.zdworks.android.zdclock");
            mFontsWhiteList.add("com.antutu.ABenchMark");
            mFontsWhiteList.add("dopool.player");
            mFontsWhiteList.add("com.uc.browser");
            mFontsWhiteList.add("com.ijinshan.mguard");
            mFontsWhiteList.add("bdmobile.android.app");
            mFontsWhiteList.add("com.alensw.PicFolder");
            mFontsWhiteList.add("com.xiaomi.topic");
            mFontsWhiteList.add("com.oupeng.mini.android");
            mFontsWhiteList.add("com.qihoo360.launcher.screenlock");
            mFontsWhiteList.add("com.android.vending");
            mFontsWhiteList.add("com.meilishuo");
            mFontsWhiteList.add("com.qidian.QDReader");
            mFontsWhiteList.add("com.tencent.research.drop");
            mFontsWhiteList.add("com.android.bluetooth");
            mFontsWhiteList.add("com.sinovatech.unicom.ui");
            mFontsWhiteList.add("com.dianping.v1");
            mFontsWhiteList.add("com.yx");
            mFontsWhiteList.add("com.dianxinos.dxhome");
            mFontsWhiteList.add("com.yiche.price");
            mFontsWhiteList.add("com.iBookStar.activity");
            mFontsWhiteList.add("com.android.dazhihui");
            mFontsWhiteList.add("cn.wps.moffice_eng");
            mFontsWhiteList.add("com.taobao.wwseller");
            mFontsWhiteList.add("com.icbc");
            mFontsWhiteList.add("cn.chinabus.main");
            mFontsWhiteList.add("com.ganji.android");
            mFontsWhiteList.add("com.ting.mp3.android");
            mFontsWhiteList.add("com.hy.minifetion");
            mFontsWhiteList.add("com.mogujie");
            mFontsWhiteList.add("com.baozoumanhua.android");
            mFontsWhiteList.add("com.calendar.UI");
            mFontsWhiteList.add("com.wacai365");
            mFontsWhiteList.add("com.cnvcs.junqi");
            mFontsWhiteList.add("cn.cntv");
            mFontsWhiteList.add("com.xunlei.kankan");
            mFontsWhiteList.add("com.xikang.android.slimcoach");
            mFontsWhiteList.add("com.thunder.ktvdaren");
            mFontsWhiteList.add("cn.goapk.market");
            mFontsWhiteList.add("cn.htjyb.reader");
            mFontsWhiteList.add("com.sec.android.app.camera");
            mFontsWhiteList.add("com.blovestorm");
            mFontsWhiteList.add("me.papa");
            mFontsWhiteList.add("com.when.android.calendar365");
            mFontsWhiteList.add("com.android.wallpaper.livepicker");
            mFontsWhiteList.add("com.vancl.activity");
            mFontsWhiteList.add("jp.naver.line.android");
            mFontsWhiteList.add("com.netease.mkey");
            mFontsWhiteList.add("com.youba.barcode");
            mFontsWhiteList.add("com.hupu.games");
            mFontsWhiteList.add("com.kandian.vodapp");
            mFontsWhiteList.add("com.dewmobile.kuaiya");
            mFontsWhiteList.add("com.anguanjia.safe");
            mFontsWhiteList.add("com.tudou.android");
            mFontsWhiteList.add("cmb.pb");
            mFontsWhiteList.add("com.weico.sinaweibo");
            mFontsWhiteList.add("com.ireadercity.b2");
            mFontsWhiteList.add("cn.wps.livespace");
            mFontsWhiteList.add("com.estrongs.android.pop");
            mFontsWhiteList.add("com.facebook.katana");
            mFontsWhiteList.add("com.disney.WMW");
            mFontsWhiteList.add("com.tuan800.tao800");
            mFontsWhiteList.add("com.byread.reader");
            mFontsWhiteList.add("me.imid.fuubo");
            mFontsWhiteList.add("com.lingdong.client.android");
            mFontsWhiteList.add("com.mop.activity");
            mFontsWhiteList.add("com.sina.mfweibo");
            mFontsWhiteList.add("cld.navi.mainframe");
            mFontsWhiteList.add("com.mappn.gfan");
            mFontsWhiteList.add("com.tencent.pengyou");
            mFontsWhiteList.add("com.xunlei.downloadprovider");
            mFontsWhiteList.add("com.tencent.android.qqdownloader");
            mFontsWhiteList.add("com.whatsapp");
            mFontsWhiteList.add("com.mx.browser");
            mFontsWhiteList.add("com.xiaomi.jr");
            mFontsWhiteList.add("com.xiaomi.smarthome");
            mFontsWhiteList.add("com.miui.backup.transfer");
            mFontsWhiteList.add("com.sohu.inputmethod.sogou.xiaomi");
            mFontsWhiteList.add("com.baidu.input_miv6");
            mFontsWhiteList.add("com.baidu.input_mi");
            mFontsWhiteList.add("com.wali.live");
            mFontsWhiteList.add("com.miui.hybrid");
            mFontsWhiteList.add("com.miui.hybrid.loader");
        }

        private FontsWhiteListHolder()
        {
        }
    }

    private static class Holder
    {

        static Typeface[] _2D_get0()
        {
            return MIUI_TYPEFACES;
        }

        private static final Typeface MIUI_TYPEFACES[] = {
            Typeface.create("miui", 0), Typeface.create("miui", 1), Typeface.create("miui", 2), Typeface.create("miui", 3)
        };


        private Holder()
        {
        }
    }


    public TypefaceUtils()
    {
    }

    public static Typeface replaceTypeface(Context context, Typeface typeface)
    {
        Typeface typeface1;
label0:
        {
            Object obj = null;
            typeface1 = obj;
            if(!usingMiuiFonts(context))
                break label0;
            if(typeface != null && !Typeface.DEFAULT.equals(typeface) && !Typeface.DEFAULT_BOLD.equals(typeface))
            {
                typeface1 = obj;
                if(!Typeface.SANS_SERIF.equals(typeface))
                    break label0;
            }
            int i;
            if(typeface == null)
                i = 0;
            else
                i = typeface.getStyle();
            typeface1 = Holder._2D_get0()[i];
        }
        if(typeface1 != null)
            typeface = typeface1;
        return typeface;
    }

    public static boolean usingMiuiFonts(Context context)
    {
        if(Build.IS_INTERNATIONAL_BUILD)
            return false;
        if(Environment.isUsingMiui(context))
            return true;
        else
            return FontsWhiteListHolder._2D_get0().contains(context.getPackageName());
    }

    private static final String MIUI_TYPEFACE_FAMILY = "miui";
}
