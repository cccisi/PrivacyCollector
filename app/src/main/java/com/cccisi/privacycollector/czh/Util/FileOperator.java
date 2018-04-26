package com.cccisi.privacycollector.czh.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author cccis FB:Zachary Chen
 * @name PrivacyCollector
 * @class name：com.cccisi.privacycollector.czh.Util
 * @class describe
 * @time 4/20/2018 11:19 PM
 * @change
 * @chang time
 * @class describe
 */
public class FileOperator {

    public static final String FOLDER_NAME = "sdcard" + File.separator + "privacy";//文件路径
    public static final String FILE_NAME_PK = "privacy.rc";
    public static final String SHARE_PATH_PK = "/sdcard/privacy/privacy.rc";

    //创建分享文件夹
    public static void createFolder() {

        File folder = new File(FOLDER_NAME);//    File同时可以表示文件或文件夹

        if (!folder.exists()) {
            //创建文件夹,一旦存在相同的文件或文件夹，是不可能存在的。
            //    在文件夹的目录结构中，只要任一级目录不存在，那么都会不存在。
            //    比如"NewFolder2"+File.separator+"separator2"此路径，NewFolder2没有存在，所以NewFolder2和separator2都不存在

            //    不管路径是否存在，都会慢慢向下一级创建文件夹。所以创建文件夹我们一般用此方法，确定稳定性。

            folder.mkdirs();
            System.out.println("文件夹的绝对路径为：" + folder.getAbsolutePath());
            System.out.println("文件夹的相对路径为：" + folder.getPath());
        }
    }

    public static void createFile() {
        //创建文件的名称
        File file = new File(FILE_NAME_PK);//文件是否存在
        if (!file.exists()) {
            try {//文件不存在，就创建一个新文件
                file.createNewFile();
                System.out.println("文件已经创建了");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件已经存在");
            System.out.println("文件名：" + file.getName());
            System.out.println("文件绝对路径为：" + file.getAbsolutePath());//是存在工程目录下，所以
            System.out.println("文件相对路径为：" + file.getPath());

        }
    }

    //删除公钥记录
    public static void deleteFile() {
        //创建文件的名称
        File file = new File(FILE_NAME_PK);//文件是否存在
        if (file.exists()) {
            file.delete();
            System.out.println("文件已经被删除了");

        }
    }

    public static void writestring(String str) {
        try {
            FileWriter fw = new FileWriter(SHARE_PATH_PK, true);//路径
            fw.flush();
            fw.write(str + "\n");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    File file=new File("/mnt/sdcard/aa.txt");
//    FileReader fr=new FileReader(file);
//    BufferedReader br=new BufferedReader(fr);
//    String temp=null;
//    String s="";
//  while((temp=br.readLine())!=null){
//        s+=temp+"\n";
//    }
//    String [] ss=s.split("\n");
//  for (int i = 0; i < ss.length; i++) {
//        System.out.println(ss[i]);
//    }

}
