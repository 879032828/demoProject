package com.example.administrator.view_test.H5;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import android.widget.Toast;

import com.example.administrator.view_test.R;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;


public class h5Activity extends AppCompatActivity {

    private WebView webView;
    private webAppInterface webAppInterface;
    StringBuilder htmlstr22 = new StringBuilder();

    private Gson mGson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ware_detail);
        initView();
        initWebView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webview);
    }

    private void initWebView() {

        htmlstr22.append("<html><head>\\r\\n<meta http-equiv=\\\"Content-Type\\\" content=\\\"text/html; charset=gb2312\\\"><style>body { line-height: 1.5; }blockquote { margin-top: 0px; margin-bottom: 0px; margin-left: 0.5em; }p { margin-top: 0px; margin-bottom: 0px; }div.foxdiv20170414104427766980 { }body { font-size: 10.5pt; font-family: 'Microsoft YaHei UI'; color: rgb(0, 0, 0); line-height: 1.5; }</style></head><body>\\n<div><strike><span></span><span style=\\\"font-family: 微软雅黑; font-size: 15px; line-height: 1.5; background-color: window;\\\">5、</span><span style=\\\"font-family: 微软雅黑; font-size: 15px; line-height: 1.5; background-color: window;\\\">邮件信息预览不全【C】</span></strike></div><div><span style=\\\"font-family: 微软雅黑; font-size: 15px; line-height: 1.5; background-color: window;\\\"><br></span></div><div><font face=\\\"微软雅黑\\\"><span style=\\\"font-size: 15px; line-height: 22px;\\\">其中第5条，我和王萌老师测试重复了，以第3条为准，详见附件截图，我修改了文件名，方便对应。</span></font></div>\\n<div><br></div><hr style=\\\"width: 210px; height: 1px;\\\" color=\\\"#b5c4df\\\" size=\\\"1\\\" align=\\\"left\\\">\\n<div><span><span style=\\\"color: rgb(128, 128, 128); font-family: 宋体; font-size: 13px;\\\">付航</span></span></div>\\n<blockquote style=\\\"margin-top: 0px; margin-bottom: 0px; margin-left: 0.5em;\\\"><div>&nbsp;</div><div style=\\\"border:none;border-top:solid #B5C4DF 1.0pt;padding:3.0pt 0cm 0cm 0cm\\\"><div style=\\\"PADDING-RIGHT: 8px; PADDING-LEFT: 8px; FONT-SIZE: 12px;FONT-FAMILY:tahoma;COLOR:#000000; BACKGROUND: #efefef; PADDING-BOTTOM: 8px; PADDING-TOP: 8px\\\"><div><b>发件人：</b>&nbsp;<a href=\\\"mailto:fuhang@neusoft.com\\\">付航</a></div><div><b>发送时间：</b>&nbsp;2017-04-14&nbsp;10:16</div><div><b>收件人：</b>&nbsp;<a href=\\\"mailto:wei-yq@neusoft.com\\\">wei-yq</a>; <a href=\\\"mailto:wang-m_neu@neusoft.com\\\">wang-m_neu</a>; <a href=\\\"mailto:libaoyu@neusoft.com\\\">libaoyu</a></div><div><b>抄送：</b>&nbsp;<a href=\\\"mailto:wang-wg@neusoft.com\\\">王伟光</a>; <a href=\\\"mailto:jinsong@neusoft.com\\\">jinsong@neusoft.com</a>; <a href=\\\"mailto:denglong@neusoft.com\\\">denglong</a>; <a href=\\\"mailto:lizhenwei@neusoft.com\\\">lizhenwei</a></div><div><b>主题：</b>&nbsp;回复: 回复: snap主干添加Android邮箱功能代码</div></div></div><div><div class=\\\"FoxDiv20170414104427766980\\\">\\n<div style=\\\"font-size: 15px;\\\"><font face=\\\"微软雅黑\\\">关于Android邮箱功能测试，目前发现了这几个问题：</font></div><div style=\\\"font-size: 15px;\\\"><font face=\\\"微软雅黑\\\">1、<span style=\\\"line-height: 1.5; background-color: window;\\\">邮件详情――点击右下角――程序崩溃【A】</span></font></div><font face=\\\"微软雅黑\\\" style=\\\"font-size: 15px;\\\"><span style=\\\"line-height: 22px;\\\">2、</span>邮件详情――转发――-取消――确定――程序崩溃<span style=\\\"line-height: 22px;\\\">【A】</span></font><div style=\\\"font-size: 15px;\\\"><span style=\\\"line-height: 22px;\\\"><font face=\\\"微软雅黑\\\">3、查看邮件详细内容时，显示的信息有误（邮件列表预览时正常，选转发时也能正常看见邮件信息）【B】</font></span><div style=\\\"\\\"><font face=\\\"微软雅黑\\\"><span style=\\\"line-height: 1.5; background-color: window;\\\">4、无法发送邮件，点击发送按钮没有响应</span><span style=\\\"line-height: 1.5; background-color: window;\\\">【B】</span></font></div><div style=\\\"\\\"><font face=\\\"微软雅黑\\\"><span style=\\\"line-height: 1.5; background-color: window;\\\">5、</span><span style=\\\"line-height: 1.5; background-color: window;\\\">邮件信息预览不全【C】</span></font></div><span style=\\\"line-height: 22px;\\\"><font face=\\\"微软雅黑\\\">6、邮箱导航栏样式，和iOS（新版SNAP）风格不同，为Android共性问题，统一对应【C】</font></span></div><div><font face=\\\"微软雅黑\\\" style=\\\"font-size: 15px;\\\"><span style=\\\"line-height: 22px;\\\">7、</span><span style=\\\"line-height: 22px;\\\">进入邮箱微应用时没有刷新邮箱列表，需手动下拉才能刷新【D】</span><span style=\\\"line-height: 22px;\\\"><br></span></font><div style=\\\"font-size: 15px;\\\"><font face=\\\"微软雅黑\\\">8、更多<span style=\\\"background-color: window;\\\">――</span><font style=\\\"line-height: 1.5; background-color: window;\\\">选择推送后，收到邮件后首页系统没有提示（使用企业微信时会消息列表中会显示邮件一项</font><span style=\\\"line-height: 1.5; background-color: window;\\\">）</span><span style=\\\"line-height: 1.5; background-color: window;\\\">【D】</span></font></div><div style=\\\"font-size: 15px;\\\"><font face=\\\"微软雅黑\\\">9、邮件绑定后无法取消，无法切换至另一个邮箱，如为固定设置建议增加个提示【D】</font></div><div style=\\\"font-size: 15px;\\\"><font face=\\\"微软雅黑\\\"><br></font></div><div style=\\\"font-size: 15px;\\\"><font face=\\\"微软雅黑\\\">以上，有问题，请联系。</font></div>\\n<div><br></div><hr style=\\\"width: 210px; height: 1px;\\\" color=\\\"#b5c4df\\\" size=\\\"1\\\" align=\\\"left\\\">\\n<div><span><span style=\\\"color: rgb(128, 128, 128); font-family: 宋体; font-size: 13px;\\\">付航</span></span></div>\\n<blockquote style=\\\"margin-top: 0px; margin-bottom: 0px; margin-left: 0.5em;\\\"><div>&nbsp;</div><div style=\\\"border:none;border-top:solid #B5C4DF 1.0pt;padding:3.0pt 0cm 0cm 0cm\\\"><div style=\\\"PADDING-RIGHT: 8px; PADDING-LEFT: 8px; FONT-SIZE: 12px;FONT-FAMILY:tahoma;COLOR:#000000; BACKGROUND: #efefef; PADDING-BOTTOM: 8px; PADDING-TOP: 8px\\\"><div><b>发件人：</b>&nbsp;<a href=\\\"mailto:wei-yq@neusoft.com\\\">wei-yq@neusoft.com</a></div><div><b>发送时间：</b>&nbsp;2017-04-13&nbsp;16:26</div><div><b>收件人：</b>&nbsp;<a href=\\\"mailto:wang-m_neu@neusoft.com\\\">wang-m_neu@neusoft.com</a>; <a href=\\\"mailto:libaoyu@neusoft.com\\\">libaoyu</a></div><div><b>抄送：</b>&nbsp;<a href=\\\"mailto:fuhang@neusoft.com\\\">fuhang</a>; <a href=\\\"mailto:wang-wg@neusoft.com\\\">wang-wg</a>; <a href=\\\"mailto:denglong@neusoft.com\\\">denglong</a>; <a href=\\\"mailto:lizhenwei@neusoft.com\\\">lizhenwei</a></div><div><b>主题：</b>&nbsp;回复: 回复: snap主干添加Android邮箱功能代码</div></div></div><div><div class=\\\"FoxDiv20170414090230933395\\\">\\n<div><span></span>宝玉老师、王萌老师，<span style=\\\"background-color: rgba(0, 0, 0, 0); font-size: 10.5pt; line-height: 1.5;\\\">邮箱功能</span><span style=\\\"background-color: rgba(0, 0, 0, 0); font-size: 10.5pt; line-height: 1.5;\\\">代码已</span><span style=\\\"font-size: 10.5pt; line-height: 1.5; background-color: window;\\\">合入</span><span style=\\\"font-family: 微软雅黑, sans-serif; font-size: 13px; line-height: 19px; background-color: window;\\\">开发分支的</span><span style=\\\"font-size: 10.5pt; line-height: 1.5; background-color: window;\\\">安卓版本；</span></div><div><span style=\\\"font-size: 10.5pt; line-height: 1.5; background-color: window;\\\"><br></span></div><div><span style=\\\"font-size: 10.5pt; line-height: 1.5; background-color: window;\\\">请知晓，谢谢！</span></div><div><span style=\\\"line-height: 1.5; font-size: 10.5pt; background-color: window;\\\"><br></span></div><div><span style=\\\"line-height: 1.5; font-size: 10.5pt; background-color: window;\\\"><br></span></div><div><br></div>\\n<div><br></div><hr style=\\\"width: 210px; height: 1px;\\\" color=\\\"#b5c4df\\\" size=\\\"1\\\" align=\\\"left\\\">\\n<div><span><div style=\\\"margin: 10px; font-size: 10pt;\\\"><br><div><span style=\\\"font-family: 微软雅黑; font-size: 14px; line-height: 21px;\\\">韦艺乔</span><div style=\\\"font-size: 14px; line-height: 21px; position: static !important;\\\">东软集团股份有限公司 金融事业部<br><div><div style=\\\"color: rgb(0, 0, 128);\\\"><font face=\\\"宋体\\\"><span style=\\\"background-color: window; font-size: 13px;\\\">Mobile：</span>18688830250</font></div><div style=\\\"color: rgb(0, 0, 128);\\\"><font face=\\\"宋体\\\" style=\\\"color: rgb(0, 0, 0); font-size: 13px; line-height: 19px;\\\">Email：</font><a href=\\\"mailto:wei-yq@neusoft.com\\\" class=\\\"\\\" style=\\\"font-family: verdana; font-size: 10pt; line-height: 1.5; background-color: window;\\\">wei-yq@neusoft.com</a><span style=\\\"color: rgb(0, 0, 0); font-family: verdana; font-size: 10pt; line-height: 1.5; background-color: rgba(0, 0, 0, 0);\\\">&nbsp;</span></div><div style=\\\"color: rgb(0, 0, 128);\\\"><div><br></div></div></div></div></div><font face=\\\"verdana\\\"><span style=\\\"color: rgb(0, 0, 0); background-color: rgba(0, 0, 0, 0);\\\"></span></font></div></span></div>\\n<blockquote style=\\\"margin-top: 0px; margin-bottom: 0px; margin-left: 0.5em;\\\"><div>&nbsp;</div><div style=\\\"border:none;border-top:solid #B5C4DF 1.0pt;padding:3.0pt 0cm 0cm 0cm\\\"><div style=\\\"PADDING-RIGHT: 8px; PADDING-LEFT: 8px; FONT-SIZE: 12px;FONT-FAMILY:tahoma;COLOR:#000000; BACKGROUND: #efefef; PADDING-BOTTOM: 8px; PADDING-TOP: 8px\\\"><div><b>发件人：</b>&nbsp;<a href=\\\"mailto:wang-m_neu@neusoft.com\\\">wang-m_neu@neusoft.com</a></div><div><b>发送时间：</b>&nbsp;2017-04-13&nbsp;09:37</div><div><b>收件人：</b>&nbsp;<a href=\\\"mailto:libaoyu@neusoft.com\\\">李宝玉</a>; <a href=\\\"mailto:wei-yq@neusoft.com\\\">wei-yq@neusoft.com</a></div><div><b>抄送：</b>&nbsp;<a href=\\\"mailto:fuhang@neusoft.com\\\">付航</a>; <a href=\\\"mailto:wang-wg@neusoft.com\\\">王伟光</a></div><div><b>主题：</b>&nbsp;回复: 答复: snap主干添加Android邮箱功能代码</div></div></div><div><div class=\\\"FoxDiv20170413162126342718\\\">\\n<!--[if gte mso 9]><xml>\\n<o:shapedefaults v:ext=\\\"edit\\\" spidmax=\\\"1026\\\" ></o:shapedefaults>\\n</xml><![endif]--><!--[if gte mso 9]><xml>\\n<o:shapelayout v:ext=\\\"edit\\\">\\n<o:idmap v:ext=\\\"edit\\\" data=\\\"1\\\" ></o:idmap>\\n</o:shapelayout></xml><![endif]-->\\n<div><span></span>韦艺乔，好！</div><div>&nbsp; &nbsp; &nbsp; &nbsp;现在可以合入版本。最近一直在修改UI，合入前请先更新。</div>\\n<div><br></div><hr style=\\\"width: 210px; height: 1px;\\\" color=\\\"#b5c4df\\\" size=\\\"1\\\" align=\\\"left\\\">\\n<div><span><div style=\\\"margin: 10px;\\\"><p class=\\\"MsoNormal\\\" align=\\\"left\\\" style=\\\"color: rgb(128, 128, 128); margin-top: 0px; margin-bottom: 0px;\\\"><font size=\\\"2\\\"><span style=\\\"line-height: 19px;\\\"><font face=\\\"Verdana\\\">王</font><font face=\\\"微软雅黑\\\">萌</font></span></font></p><p class=\\\"MsoNormal\\\" align=\\\"left\\\" style=\\\"font-family: Verdana; font-size: small; color: rgb(128, 128, 128); margin-top: 0px; margin-bottom: 0px;\\\"><span style=\\\"font-family: 宋体;\\\"><span style=\\\"font-size: 10pt; font-family: 微软雅黑;\\\">先行产品研发事业部</span><span style=\\\"font-family: 微软雅黑;\\\">&nbsp;</span></span></p><p class=\\\"MsoNormal\\\" align=\\\"left\\\" style=\\\"font-family: Verdana; font-size: small; color: rgb(128, 128, 128); margin-top: 0px; margin-bottom: 0px;\\\">Mobile：18809819075<o:p></o:p></p><p class=\\\"MsoNormal\\\" align=\\\"left\\\" style=\\\"font-family: Verdana; font-size: small; color: rgb(128, 128, 128); margin-top: 0px; margin-bottom: 0px;\\\"><br></p></div></span></div>\\n<blockquote style=\\\"margin-top: 0px; margin-bottom: 0px; margin-left: 0.5em;\\\"><div>&nbsp;</div><div style=\\\"border:none;border-top:solid #B5C4DF 1.0pt;padding:3.0pt 0cm 0cm 0cm\\\"><div style=\\\"PADDING-RIGHT: 8px; PADDING-LEFT: 8px; FONT-SIZE: 12px;FONT-FAMILY:tahoma;COLOR:#000000; BACKGROUND: #efefef; PADDING-BOTTOM: 8px; PADDING-TOP: 8px\\\"><div><b>发件人：</b>&nbsp;<a href=\\\"mailto:libaoyu@neusoft.com\\\" style=\\\"color: blue; text-decoration: underline;\\\">libaoyu</a></div><div><b>发送时间：</b>&nbsp;2017-04-13&nbsp;09:24</div><div><b>收件人：</b>&nbsp;<a href=\\\"mailto:wei-yq@neusoft.com\\\" style=\\\"color: blue; text-decoration: underline;\\\">wei-yq@neusoft.com</a>; <a href=\\\"mailto:wang-m_neu@neusoft.com\\\" style=\\\"color: blue; text-decoration: underline;\\\">王萌</a></div><div><b>抄送：</b>&nbsp;<a href=\\\"mailto:fuhang@neusoft.com\\\" style=\\\"color: blue; text-decoration: underline;\\\">'fuhang'</a>; <a href=\\\"mailto:wang-wg@neusoft.com\\\" style=\\\"color: blue; text-decoration: underline;\\\">王伟光</a></div><div><b>主题：</b>&nbsp;答复: snap主干添加Android邮箱功能代码</div></div></div><div><div class=\\\"FoxDiv20170413093512221649\\\">\\n<!--[if gte mso 9]><xml>\\n<o:shapedefaults v:ext=\\\"edit\\\" spidmax=\\\"1026\\\" ></o:shapedefaults>\\n</xml><![endif]--><!--[if gte mso 9]><xml>\\n<o:shapelayout v:ext=\\\"edit\\\">\\n<o:idmap v:ext=\\\"edit\\\" data=\\\"1\\\" ></o:idmap>\\n</o:shapelayout></xml><![endif]--><div class=\\\"WordSection1\\\" style=\\\"page: WordSection1;\\\"><p class=\\\"MsoNormal\\\" style=\\\"margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span style=\\\"font-size:10.5pt;color:#1F497D\\\">王萌看一下能否合入安卓版本</span><span lang=\\\"EN-US\\\" style=\\\"font-size:10.5pt;font-family:&quot;Calibri&quot;,sans-serif;color:#1F497D\\\"><o:p></o:p></span></p><p class=\\\"MsoNormal\\\" style=\\\"margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:10.5pt;font-family:&quot;Calibri&quot;,sans-serif;color:#1F497D\\\">to</span><span style=\\\"font-size:10.5pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:black\\\">韦艺乔</span><span style=\\\"font-size:10.0pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:black\\\">：你们那边合版本，现在只合入开发分支就行了。<span lang=\\\"EN-US\\\"><o:p></o:p></span></span></p><p class=\\\"MsoNormal\\\" style=\\\"margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:10.0pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:black\\\">https://192.168.161.99:8443/svn/2012/SNAP-2017/04-</span><span style=\\\"font-size:10.0pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:black\\\">开发工程<span lang=\\\"EN-US\\\">/02-</span>分支<span lang=\\\"EN-US\\\">/201703/android<o:p></o:p></span></span></p><p class=\\\"MsoNormal\\\" style=\\\"margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:10.5pt;font-family:&quot;Calibri&quot;,sans-serif;color:#1F497D\\\"><o:p>&nbsp;</o:p></span></p><p class=\\\"MsoNormal\\\" style=\\\"margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:10.5pt;font-family:&quot;Calibri&quot;,sans-serif;color:#1F497D\\\"><o:p>&nbsp;</o:p></span></p><div><div style=\\\"border:none;border-top:solid #E1E1E1 1.0pt;padding:3.0pt 0cm 0cm 0cm\\\"><p class=\\\"MsoNormal\\\" style=\\\"margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><b><span style=\\\"font-size:11.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\\\">发件人<span lang=\\\"EN-US\\\">:</span></span></b><span lang=\\\"EN-US\\\" style=\\\"font-size:11.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\\\"> wei-yq@neusoft.com [mailto:wei-yq@neusoft.com] <br></span><b><span style=\\\"font-size:11.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\\\">发送时间<span lang=\\\"EN-US\\\">:</span></span></b><span lang=\\\"EN-US\\\" style=\\\"font-size:11.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\\\"> 2017</span><span style=\\\"font-size:11.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\\\">年<span lang=\\\"EN-US\\\">4</span>月<span lang=\\\"EN-US\\\">13</span>日<span lang=\\\"EN-US\\\"> 9:18<br></span><b>收件人<span lang=\\\"EN-US\\\">:</span></b><span lang=\\\"EN-US\\\"> libaoyu &lt;libaoyu@neusoft.com&gt;<br></span><b>抄送<span lang=\\\"EN-US\\\">:</span></b><span lang=\\\"EN-US\\\"> fuhang &lt;fuhang@neusoft.com&gt;<br></span><b>主题<span lang=\\\"EN-US\\\">:</span></b><span lang=\\\"EN-US\\\"> snap</span>主干添加<span lang=\\\"EN-US\\\">Android</span>邮箱功能代码<span lang=\\\"EN-US\\\"><o:p></o:p></span></span></p></div></div><p class=\\\"MsoNormal\\\" style=\\\"margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span lang=\\\"EN-US\\\"><o:p>&nbsp;</o:p></span></p><div><p class=\\\"MsoNormal\\\" style=\\\"margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span style=\\\"font-size:10.5pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:black\\\">宝玉老师，<span lang=\\\"EN-US\\\">Android</span>邮箱已经完成开发，前端时间做了一些接口调试，最近<span lang=\\\"EN-US\\\">i</span>忙平安的事情，忘记<span lang=\\\"EN-US\\\">Android</span>代码还未添加在<span lang=\\\"EN-US\\\">snap</span>主干上；<span lang=\\\"EN-US\\\"><o:p></o:p></span></span></p></div><div><p class=\\\"MsoNormal\\\" style=\\\"margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:10.5pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:black\\\"><o:p>&nbsp;</o:p></span></p></div><div><p class=\\\"MsoNormal\\\" style=\\\"margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span style=\\\"font-size:10.5pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:black\\\">现在我们是否今天我们是否可以添加<span lang=\\\"EN-US\\\">Android</span>邮箱功能的代码？<span lang=\\\"EN-US\\\"><o:p></o:p></span></span></p></div><div><p class=\\\"MsoNormal\\\" style=\\\"margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:10.5pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:black\\\"><o:p>&nbsp;</o:p></span></p></div><div><p class=\\\"MsoNormal\\\" style=\\\"margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:10.5pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:black\\\"><o:p>&nbsp;</o:p></span></p></div><div><p class=\\\"MsoNormal\\\" style=\\\"margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:10.5pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:black\\\"><o:p>&nbsp;</o:p></span></p></div><div><p class=\\\"MsoNormal\\\" style=\\\"margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:10.5pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:black\\\"><o:p>&nbsp;</o:p></span></p></div><div class=\\\"MsoNormal\\\" style=\\\"margin: 0cm 0cm 0.0001pt; font-size: 12pt; font-family: 宋体;\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:10.5pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:black\\\"><hr size=\\\"1\\\" width=\\\"210\\\" style=\\\"width:157.5pt\\\" noshade=\\\"\\\" align=\\\"left\\\"></span></div><div><div style=\\\"margin-left:7.5pt;margin-top:7.5pt;margin-right:7.5pt;margin-bottom:7.5pt\\\"><p class=\\\"MsoNormal\\\" style=\\\"margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:10.0pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:black\\\"><o:p>&nbsp;</o:p></span></p><div><p class=\\\"MsoNormal\\\" style=\\\"margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span style=\\\"font-size:10.5pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:black\\\">韦艺乔</span><span lang=\\\"EN-US\\\" style=\\\"font-size:10.0pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:black\\\"><o:p></o:p></span></p><div><p class=\\\"MsoNormal\\\" style=\\\"line-height: 15.75pt; margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span style=\\\"font-size:10.5pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:black\\\">东软集团股份有限公司 金融事业部<span lang=\\\"EN-US\\\"><o:p></o:p></span></span></p><div><div><p class=\\\"MsoNormal\\\" style=\\\"line-height: 15.75pt; margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:10.0pt;color:navy;background:white\\\">Mobile</span><span style=\\\"font-size:10.0pt;color:navy;background:white\\\">：</span><span lang=\\\"EN-US\\\" style=\\\"font-size:10.5pt;color:navy\\\">18688830250</span><span lang=\\\"EN-US\\\" style=\\\"font-size:10.5pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:navy\\\"><o:p></o:p></span></p></div><div><p class=\\\"MsoNormal\\\" style=\\\"line-height: 15.75pt; margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:10.0pt;color:black\\\">Email</span><span style=\\\"font-size:10.0pt;color:black\\\">：</span><span lang=\\\"EN-US\\\" style=\\\"font-size:10.5pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:navy\\\"><a href=\\\"mailto:wei-yq@neusoft.com\\\" style=\\\"color: blue; text-decoration: underline;\\\"><span style=\\\"font-size:10.0pt;font-family:&quot;Verdana&quot;,sans-serif;background:white\\\">wei-yq@neusoft.com</span></a></span><span lang=\\\"EN-US\\\" style=\\\"font-size:10.0pt;font-family:&quot;Verdana&quot;,sans-serif;color:black\\\">&nbsp;</span><span lang=\\\"EN-US\\\" style=\\\"font-size:10.5pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:navy\\\"><o:p></o:p></span></p></div><div><div><p class=\\\"MsoNormal\\\" style=\\\"line-height: 15.75pt; margin: 0px 0cm; font-size: 12pt; font-family: 宋体;\\\"><span lang=\\\"EN-US\\\" style=\\\"font-size:10.5pt;font-family:&quot;微软雅黑&quot;,sans-serif;color:navy\\\"><o:p>&nbsp;</o:p></span></p></div></div></div></div></div></div></div></div></div></div></blockquote>\\n</div></div></blockquote>\\n</div></div></blockquote>\\n</div></div></div></blockquote>\\n</body></html>");

        WebSettings webSettings = webView.getSettings();
        // 告诉WebView启用JavaScript执行。默认的是false。
        webSettings.setJavaScriptEnabled(true);
        //  页面加载好以后，再放开图片
        webSettings.setBlockNetworkImage(false);
        // 启动应用缓存
        webSettings.setAppCacheEnabled(true);

        //使用简单的loadData方法会导致乱码，可能是Android API的Bug
        //show.loadData(sb.toString(), "text/html", "utf-8");
        //加载、并显示HTML代码


        String html = Html.escapeHtml(htmlstr22);

        html = htmlstr22.toString().replaceAll("\\\\\\\\r", "\r");
        html = html.replaceAll("\\\\\\\\n", "\n");


        OkHttpUtils
                .get()
                .url("http://sacasnap.neusoft.com/mailclient/mail/receiver/rest/getContent")
                .addParams("mailId", "7663c3f5e87c4744bd87d8a7f9238219")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("response_log", "response = " + response);
                        Boolean bll;
                        try {
                            JSONObject object = new JSONObject(response);
                            htmlstr22 = htmlstr22;
                            String result = object.getString("data");
                            result = result.substring(2, result.length() - 2);
                            result.replaceAll("\\\\/", "/");
                            Log.d("response_log", "result = " + result);
                            if (result.equals(htmlstr22)){
                                bll = true;
                                Log.d("response_log", "equals = " + bll);
                            }else {
                                bll = false;
                                Log.d("response_log", "equals = " + bll);
                            }
                            StringBuilder ssss = new StringBuilder();
                            ssss.append(result);
//                            webView.loadDataWithBaseURL(null, ssss.toString(), "text/html", "UTF-8", null);
                            webView.loadData(ssss.toString(), "text/html", "UTF-8");
                            webView.setWebViewClient(new MyWebViewClient());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                });

    }

    class webAppInterface {

        private Context mContext;

        public webAppInterface(Context context) {
            mContext = context;
        }

        @JavascriptInterface
        public void showDetail() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                }
            });
        }

        @JavascriptInterface
        public void buy(long id) {

        }

        //参数需要一致
        @JavascriptInterface
        public void addToCart(long id) {
        }
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

        }
    }

    public class OnSharesListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

        }
    }

}
