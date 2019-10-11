package com.wxsoft.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;

/**
 * 获取设备的硬件信息
 */
public class HardWareUtils {
    public HardWareUtils(){
    }
//    private static final long serialVersionUID = 1L;


    /**
     * 获取主板序列号
     *
     * @return
     */
    public static String getMotherboardSN() {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";

            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p
                    .getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }

    /**
     * 获取硬盘序列号
     *
     * @param drive
     *            盘符
     * @return
     */
    public static String getHardDiskSN(String drive) {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);

            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n"
                    + "Set objDrive = colDrives.item(\""
                    + drive
                    + "\")\n"
                    + "Wscript.Echo objDrive.SerialNumber"; // see note
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p
                    .getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }

    /**
     * 获取CPU序列号
     *
     * @return
     */
    public static String getCPUSerial() {
        String result = "";
        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_Processor\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.ProcessorId \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p
                    .getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
            file.delete();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        if (result.trim().length() < 1 || result == null) {
            result = "无CPU_ID被读取";
        }
        return result.trim();
    }

    /**
     * 获取MAC地址
     */
    public static String getMac() {
        try {
            byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                String s = Integer.toHexString(mac[i] & 0xFF);
                sb.append(s.length() == 1 ? 0 + s : s);
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 获取硬件信息
     * 获取了硬件设备的 CPU序列号，主板序列号，Mac地址
     * @return
     */
    public static String getHWInfo(){
        String msg  = getCPUSerial()+getMotherboardSN()+getMac();
        return msg ;
    }
    /**
     * 获取加密后的硬件信息
     * 获取了硬件设备的 CPU序列号，主板序列号，Mac地址
     * @return
     */
    public static String getHWInfoEncrypt(){
        String msg  = getHWInfo();
        String encrypt = DESUtil.getEncryptString(msg);
        return encrypt ;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("CPU序列号："+getCPUSerial());//CPU
        System.out.println("主板序列号："+getMotherboardSN());//主板
        System.out.println("c盘序列号："+getHardDiskSN("c"));//c盘
        System.out.println("MAC地址："+getMac());//MAC
        String msg  = getCPUSerial()+getMotherboardSN().replace(".", "")+getHardDiskSN("c")+getMac().replace("-", "");
        msg  = getCPUSerial()+getMotherboardSN()+getMac();
        System.out.println("原始数据:"+msg);

        long begin = new Date().getTime();
        System.out.println(begin);
//        String encrypt = DesUtil.encrypt(msg);
        String encrypt = DESUtil.getEncryptString(msg);
        System.out.println("加密："+encrypt);
//        String decrypt = DesUtil.decrypt(encrypt);
        String decrypt = DESUtil.getDecryptString(encrypt);
        System.out.println("解密:"+decrypt);
        long end = new Date().getTime();
        System.out.println(end );
        System.out.println("用时："+(end-begin)+"毫秒");

    }
}
