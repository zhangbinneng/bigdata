package org.qianfeng.bigdata.mapreduce.util;

import org.apache.hadoop.conf.Configuration;

/**
 * @description 获取远程hadoop集群配置对象，使用给定的jar包的绝对路径
 * @author: 张斌能
 * @create: 2018-11-15 14:24:17
 **/

public class HadoopUtil {
    static {
        System.setProperty("HADOOP_USER_NAME","root");
    }

    public static Configuration getRemoteHadoopConf(){
        Configuration conf = getBaseRemoteHadoopConf();
        conf.set("mapreduce.job.jar","D:\\idea\\bigdata\\target\\bigdata-1.0-SNAPSHOT.jar");
        return conf;
    }

    public static Configuration getRemoteHadoopConf(String jarAbsPath){
        Configuration conf = getBaseRemoteHadoopConf();
        conf.set("mapreduce.job.jar",jarAbsPath);
        return conf;
    }

    private static Configuration getBaseRemoteHadoopConf(){
        System.setProperty("HADOOP_USER_NAME","root");
        Configuration conf = new Configuration();
        //HDFS远程连接信息
        conf.set("fs.defaultFS", "hdfs://mycluster");
        conf.set("dfs.nameservices", "mycluster");
        conf.set("dfs.ha.namenodes.mycluster", "nn1,nn2");
        conf.set("dfs.namenode.rpc-address.mycluster.nn1", "zk1:8020");
        conf.set("dfs.namenode.rpc-address.mycluster.nn2", "zk2:8020");
        conf.set("dfs.client.failover.proxy.provider.mycluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");

        //MR&YARN相关配置
        conf.set("mapreduce.framework.name", "yarn");
        conf.set("yarn.resourcemanager.address", "http://zk2:8032");
        conf.set("yarn.resourcemanager.scheduler.address","http://zk2:8030");
        conf.set("mapreduce.app-submission.cross-platform", "true");//允许跨平台提交jar包
        return conf;
    }
}
