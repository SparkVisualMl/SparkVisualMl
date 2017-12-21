package com.spark.utils;


import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPUtil {

    public static String getLocalIpAddress() throws UnknownHostException{
        String ip = InetAddress.getLocalHost().getHostAddress();
        return ip;
    }
    public static void main(String args[])throws UnknownHostException{
        System.out.printf(IPUtil.getLocalIpAddress());

    }
}
