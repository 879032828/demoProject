package com.example.administrator.view_test.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.security.MessageDigest;

public class MyFileUtils {
    public static boolean saveTxtFile(String file, String s) {
        File f = new File(file);
        return saveTxtFile(f.getParentFile(), f.getName(), s);
    }

    public static boolean saveTxtFile(File dir, String fileName, String s) {
        boolean rtn = true;
        if (dir != null) {
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
        File file = new File(dir, fileName);
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), Charset.forName("UTF-8")));
            writer.write(s);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
            rtn = false;
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rtn;
    }

    public static String readTxtFile(File file) {
        return readTxtFile(file.getParentFile(), file.getName());
    }

    public static String readTxtFromInputStream(InputStream is) {
        try {
            return readStringFromReader(getInputStreamReader(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取指定文件的Reader对象
     *
     * @param file
     * @return
     */
    public static BufferedReader getTextFileReader(File file) {
        return getTextFileReader(file.getParentFile(), file.getName());
    }

    /**
     * 换取指定文件的Reader对象
     *
     * @param dir
     * @param fileName
     * @return
     */
    public static BufferedReader getTextFileReader(File dir, String fileName) {
        BufferedReader rtn = null;

        File file = new File(dir, fileName);
        if (!file.exists()) {
            return rtn;
        }
        try {
            String encoding = getFileEncoding(file);
            rtn = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file),
                    Charset.forName(encoding)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return rtn;
    }

    public static BufferedReader getInputStreamReader(InputStream is) {
        BufferedReader rtn = null;

        try {
            String encoding = getInputStreamEnCoding(is);
            rtn = new BufferedReader(new InputStreamReader(is,
                    Charset.forName(encoding)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }

    public static String readTxtFile(File dir, String fileName) {
        String rtn = "";
        if (dir == null) {
            return rtn;
        }
        File file = new File(dir, fileName);
        if (!file.exists()) {
            return rtn;
        }
        BufferedReader reader = null;
        try {
            reader = getTextFileReader(dir, fileName);
            rtn = readStringFromReader(reader);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rtn;
    }

    private static String readStringFromReader(BufferedReader reader)
            throws IOException {
        String rtn;
        String line = null;
        StringBuffer sb = new StringBuffer();
        boolean b = false;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\r\n");
            b = true;
        }
        if (b) {
            sb.delete(sb.length() - 2, sb.length());
        }
        rtn = sb.toString();
        return rtn;
    }

    public static void saveTxtFile(File f, String str) {
        saveTxtFile(f.getAbsolutePath(), str);
    }

    public static void copyTextFile(File src, File dst) {
        String s = readTxtFile(src);
        System.out.println(src);

        saveTxtFile(dst, s);
    }

    public static void dirCopy(String src, String des) throws IOException {
        dirCopy(src, des, null);
    }

    public static interface IFileCopyListener {
        public void fileCopy(String src, String des) throws IOException;

        public void dirCopy(String src, String des) throws IOException;
    }

    public static interface IDirTravelListener {
        public void doFile(File file);

        public void doDir(File dir);
    }

    /**
     * 目录递归访问每个文件
     *
     * @param dir
     * @param listener
     */
    public static void dirTravel(File dir, FilenameFilter filenamefilter,
                                 IDirTravelListener listener) {

        File[] fs = dir.listFiles();
        if (fs != null) {
            for (File f : fs) {
                if (f.isFile()) {
                    if (filenamefilter == null || filenamefilter.accept(f.getParentFile(), f.getName())) {
                        if (listener != null) {
                            listener.doFile(f);
                        }
                    }
                } else {
                    if (filenamefilter == null || filenamefilter.accept(f.getParentFile(), f.getName())) {
                        if (listener != null) {
                            listener.doDir(f);
                        }
                        dirTravel(f, filenamefilter, listener);
                    }
                }
            }
        }

    }


    public static void dirCopy(String src, String des,
                               IFileCopyListener filecopyListener) throws IOException {
        File srcFile = new File(src);

        File desFile = new File(des);
        if (!desFile.exists()) {
            desFile.mkdirs();
        }
        File[] fs = srcFile.listFiles();
        if (fs == null) {
            System.out.println(srcFile.getAbsolutePath() + " is not exists!");
            return;
        }
        for (File f : fs) {
            String newSrc = f.getAbsolutePath();
            String newDes = desFile.getAbsolutePath() + "/" + f.getName();
            if (f.isFile()) {
                fileCopy(newSrc, newDes);
                if (filecopyListener != null) {
                    filecopyListener.fileCopy(newSrc, newDes);
                }
            } else if (f.isDirectory()) {
                dirCopy(newSrc, newDes, filecopyListener);
                if (filecopyListener != null) {
                    filecopyListener.dirCopy(newSrc, newDes);
                }
            }
        }
    }

    static final int SIZE = 1024 * 1024 * 15;

    public static void fileCopy(String src, String des) throws IOException {
        fileCopy(new File(src), new File(des));
    }

    public static void fileCopy(File srcFile, File desFile) {
        if (!desFile.getParentFile().exists()) {
            desFile.getParentFile().mkdirs();
        }
        try {
            FileInputStream fis;

            fis = new FileInputStream(srcFile);

            FileOutputStream fos = new FileOutputStream(desFile);
            byte b[] = new byte[SIZE];
            int n;
            while ((n = fis.read(b)) != -1) {
                fos.write(b, 0, n);
            }
            fos.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful. If a
     * deletion fails, the method stops attempting to delete and returns
     * "false".
     */
    public static boolean dirDelete(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = dirDelete(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 删除文件或目录
     *
     * @param files
     * @return
     */
    public static void deleteFileOrDirs(File... files) {
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    dirDelete(file);
                } else {
                    file.delete();
                }
            }
        }
    }


    /**
     * 获取指定文件的md5值
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static String getMd5ByFile(File file) throws FileNotFoundException {
        String value = null;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(
                    FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }


    /**
     * 判断文件的编码格式
     *
     * @param file :file
     * @return 文件编码格式
     * @throws Exception
     */
    public static String getFileEncoding(File file) {
        String encode = null;

        FileInputStream fis;
        try {
            fis = new FileInputStream(file);

            encode = getInputStreamEnCoding(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (encode == null) {// 如果没有找到文件编码，则默认按gbk处理
            encode = "gbk";
        }
        // System.out.println(encode);
        return encode;
    }

    private static String getInputStreamEnCoding(
            InputStream fis)
            throws IOException {
        String encode = "utf-8";
        return encode;
    }


    /**
     * 返回file对应的路径中去掉baseFile对应的路径
     *
     * @param file
     * @param baseFile
     * @return
     */
    public static String getFileRelativePath(File file, File baseFile) {
        String abs = file.getAbsolutePath();
        String fileRelativePath = abs.substring(baseFile.getAbsolutePath()
                .length());
        return fileRelativePath;
    }

    private final static String[] imgExts = {"jpg", "png", "jpeg", "gif"};

//    /**
//     * 判断文件的后缀名为图片
//     *
//     * @param ext 文件名后缀，不包括.
//     * @return 是否为图片后缀
//     */
//    public static boolean isImageType(String ext) {
//        if (NMafStringUtils.isNotEmpty(ext)) {
//            for (String m : imgExts) {
//                if (ext.equalsIgnoreCase(m)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

//    /**
//     * 是否为语音消息
//     *
//     * @param ext 文件名后缀，不包括.
//     * @return
//     */
//    public static boolean isVoiceType(String ext) {
//        String[] myExts = {"amr"};
//        if (NMafStringUtils.isNotEmpty(ext)) {
//            for (String m : myExts) {
//                if (ext.equalsIgnoreCase(m)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    /**
     * 计算所有的文件及文件夹的大小
     *
     * @param fileOrDirs 文件或文件夹
     * @return 总共有多少byte
     */
    public static long calculateCommonFileSize(File... fileOrDirs) {
        long sum = 0;
        if (fileOrDirs != null) {
            for (File f : fileOrDirs) {
                if (f != null)
                    sum += calculateFileSize(f);
            }
        }
        return sum;
    }

    private static long calculateFileSize(File file) {
        long sum = 0;
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] subFiles = file.listFiles();
                for (File sf : subFiles) {
                    sum += calculateFileSize(sf);
                }
            } else {
                sum += file.length();
            }
        }

        return sum;
    }

//    /**
//     * 根据uri获取对应的文件的绝对路径
//     * uri schema 支持 file:// content://
//     *
//     * @param fileUri
//     * @return
//     */
//    public static String getFilePathFromUri(Uri fileUri) {
//        String localSelectPath = "";
//        if (fileUri != null) {
//            Cursor cursor = SnapApplication.getApplication().getContentResolver().query(fileUri, null, null, null, null);
//            if (cursor != null) {
//                cursor.moveToFirst();
//                int columnIndex = cursor.getColumnIndex("_data");
//                localSelectPath = cursor.getString(columnIndex);
//                cursor.close();
//            }
//            if (NMafStringUtils.isEmpty(localSelectPath)) {
//                if (NMafStringUtils.startWith(fileUri.getScheme(), "file")) {
//                    localSelectPath = fileUri.getPath();//获取文件路径
//                }
//            }
//        }
//        return localSelectPath;
//    }


}
