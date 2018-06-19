package com.example.administrator.view_test.ExceptionHandler;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.administrator.view_test.util.FilePathConstant;
import com.example.administrator.view_test.util.MyFileUtils;
import com.example.administrator.view_test.util.TimeUtils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import static com.example.administrator.view_test.ExceptionHandler.ConfigConstants.CRASH_REPORT_TO_SERVER;
import static com.example.administrator.view_test.ExceptionHandler.ConfigConstants.CRASH_REPORT_LOCAL_FILE;

/**
 * **************************************************************
 * File name:   CrashHandler.java
 * Created on   2014-2-19 下午2:41:25
 * Title:       DCEMobile
 * Description: [ * UncaughtExceptionHandler：线程未捕获异常控制器是用来处理未捕获异常的。 如果程序出现了未捕获异常默认情况下则会出现强行关闭对话框
 * 实现该接口并注册为程序中的默认未捕获异常处理 这样当未捕获异常发生时，就可以做些异常处理操作 例如：收集异常信息，发送错误报告 等。
 * <p>
 * UncaughtException处理类,当程序发生Uncaught异常的时候,由该类来接管程序,并记录发送错误报告.]
 * Company:     东软集团（大连）有限公司
 * Department:  金融事业部
 *
 * @author <a href="mailto: li-f.neu@neusoft.com">苗凯翔</a>
 *         ------------------------------------------------------------
 *         修改历史
 *         序号  日期  修改人   修改原因
 *         1
 *         2
 *         -------------------------------------------------------------
 ***************************************************************/
public class CrashHandler implements UncaughtExceptionHandler {

    /**
     * Debug Log Tag
     */
    public static final String TAG = "CrashHandler";

    /**
     * CrashHandler实例
     */
    private static CrashHandler INSTANCE;

    /**
     * 程序的Context对象
     */
    private Context mContext;

    /**
     * 系统默认的UncaughtException处理类
     */
    private UncaughtExceptionHandler mDefaultHandler;

    /**
     * 使用Properties来保存设备的信息和错误堆栈信息
     */
    private final Properties mDeviceCrashInfo = new Properties();

    /**
     * app版本号
     */
    private static final String VERSION_NAME = "versionName";

    /**
     * app版本更新次数
     */
    private static final String VERSION_CODE = "versionCode";

    private static final String STACK_TRACE = "STACK_TRACE";

    private static final String CAUSE = "CAUSE";

    /**
     * 错误报告文件的扩展名
     */
    private static final String CRASH_REPORTER_EXTENSION = ".txt";

    //日志存放路径
    private static final String LOG_FILE_PATH = "/app_log/";

    private static final String CRASH_ = "crash-";


    private static final String NEW_LINE = "\r\n";

    private static final String TABLE_CHAR = "\t";


    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {

    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {

        if (INSTANCE == null)
            INSTANCE = new CrashHandler();
        return INSTANCE;
    }


    /**
     * 初始化,注册Context对象, 获取系统默认的UncaughtException处理器, 设置该CrashHandler为程序的默认处理器
     * <p>
     * isOpen参数用于是否开启日志收集功能
     *
     * @param ctx
     * @param isOpen
     */
    public void init(Context ctx, boolean isOpen) {

        if (isOpen) {
            mContext = ctx;
            mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, final Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            // Sleep一会后结束程序
            // 来让线程停止一会是为了显示Toast信息给用户，然后Kill程序
            try {
                Thread.sleep(1000);
                //退出程序
                if ((ex.getMessage() != null) && (ex.getMessage().equals("An error occured while executing doInBackground()")
                        || ex.getMessage().equals("error loading page"))) {
                } else {
//                    ((MyApplication) mContext).getAppManager().AppExit(mContext, false);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(10);
                }
//                ((NMafApplication) mContext).getAppManager().AppExit(mContext, false);
//                android.os.Process.killProcess(android.os.Process.myPid());
//                System.exit(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     * 开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false
     */
    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return true;
        }
        // 使用Toast来显示异常信息
        new Thread() {

            @Override
            public void run() {

                // Toast 显示需要出现在一个线程的消息队列中
                Looper.prepare();
                if ((ex.getMessage() != null) && (ex.getMessage().equals("An error occured while executing doInBackground()")
                        || ex.getMessage().equals("error loading page"))) {
                    Toast.makeText(mContext, "预览失败", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, "程序出错了，错误日志已保存", Toast.LENGTH_LONG).show();
//                    SnapSdkMain.getInstance().doLocalExitApp();//退出系统
                }
                Looper.loop();
            }
        }.start();
        collectCrashDeviceInfo(mContext);
        collectCrashExceptionTrace(ex);
        if (TextUtils.equals(CRASH_REPORT_LOCAL_FILE, CRASH_REPORT_LOCAL_FILE)) {
            // 保存错误报告文件
            saveCrashInfoToFile();
        } else if (TextUtils.equals(CRASH_REPORT_TO_SERVER, CRASH_REPORT_LOCAL_FILE)) {
            saveCrashInfoToFile();
            ;//发送到服务器
            //sendToServer();//未实现，需服务端实现接口
        } else {
            ;//do nothing;
        }
//        ((NMafApplication) mContext).getAppManager().AppExit(mContext, false);
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(10);
        return true;
    }

    /**
     * 收集程序崩溃的设备信息
     *
     * @param ctx
     */
    public void collectCrashDeviceInfo(Context ctx) {

        try {
            // Class for retrieving various kinds of information related to the
            // application packages that are currently installed on the device.
            // You can find this class through getPackageManager().
            PackageManager pm = ctx.getPackageManager();
            // getPackageInfo(String packageName, int flags)
            // Retrieve overall information about an application package that is
            // installed on the system.
            // public static final int GET_ACTIVITIES
            // Since: API Level 1 PackageInfo flag: return information about
            // activities in the package in activities.
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                // public String versionName The version name of this package,
                // as specified by the <manifest> tag's versionName attribute.
                mDeviceCrashInfo.put(VERSION_NAME, pi.versionName == null ? "not set" : pi.versionName);
                // public int versionCode The version number of this package,
                // as specified by the <manifest> tag's versionCode attribute.
                mDeviceCrashInfo.put(VERSION_CODE, pi.versionCode);
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            //            LogUtil.saveLog(e, CLASSNAME, "collectCrashDeviceInfo");
        }
        // 使用反射来收集设备信息.在Build类中包含各种设备信息,
        // 例如: 系统版本号,设备生产商 等帮助调试程序的有用信息
        // 返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                // setAccessible(boolean flag)
                // 将此对象的 accessible 标志设置为指示的布尔值。
                // 通过设置Accessible属性为true,才能对私有变量进行访问，不然会得到一个IllegalAccessException的异常
                field.setAccessible(true);
                mDeviceCrashInfo.put(field.getName(), field.get(null));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 收集异常信息
     *
     * @param ex
     */
    public void collectCrashExceptionTrace(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        // 将此 throwable 及其追踪输出到指定的 PrintWriter
        ex.printStackTrace(printWriter);
        // getCause() 返回此 throwable 的 cause；如果 cause 不存在或未知，则返回 null。
        Throwable cause = ex.getCause();

        // toString() 以字符串的形式返回该缓冲区的当前值。
        String result = info.toString();
        StackTraceElement[] stes = ex.getStackTrace();

        while (cause != null) {
            cause.printStackTrace();
            stes = cause.getStackTrace();
            for (StackTraceElement ste : stes) {
                sb.append(TABLE_CHAR + ste.toString() + NEW_LINE);
            }
            cause = cause.getCause();
        }
        printWriter.close();
        if (ex.getCause() != null) {
            result += NEW_LINE + CAUSE + "=" + sb.toString();
        }
        mDeviceCrashInfo.put(STACK_TRACE, result);
    }

    String getCrashInfoStr() {
        StringBuffer sb = new StringBuffer();
        Set<Entry<Object, Object>> set = mDeviceCrashInfo.entrySet();
        Iterator<Entry<Object, Object>> iterator = set.iterator();
        Entry<Object, Object> entry = null;
        while (iterator.hasNext()) {
            entry = iterator.next();
            sb.append(entry.getKey().toString()).append("=").append(entry.getValue()).append(NEW_LINE);
        }
        return sb.toString();
    }

    /**
     * 保存错误信息到文件中
     *
     * @return
     */
    public String saveCrashInfoToFile() {
        String crashFilePath = "";
        try {
            File dir = FilePathConstant.getLogFilePath();
            File crashFile = new File(dir, new StringBuffer().append(CRASH_)
                    .append(TimeUtils.getCurrentTime("yyyy_MM_dd-HH_mm_ss")).append(CRASH_REPORTER_EXTENSION).toString());
            crashFilePath = crashFile.getAbsolutePath();
            System.out.println(crashFilePath);
            // 保存文件
            MyFileUtils.saveTxtFile(crashFile, getCrashInfoStr());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crashFilePath;
    }
}