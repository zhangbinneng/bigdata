package org.qianfeng.bigdata.hdfs.newhdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;


/**
 * @description 文件系统工厂类，用于构建文件对象
 * @author: 张斌能
 * @create: 2018-11-14 15:27:42
 **/

public class FileSystemFactory {
    /**
     * 根据用户自定义的配置信息获取一个HDFS的对象
     * @param hdfsConf  用户自定义的配置信息对象
     * @return  HDFS对象
     */
    public static FileSystem getDIYHDFS(Configuration hdfsConf){
        try {
           return FileSystem.get(hdfsConf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取默认的非HAHDFS对象
     * @return  默认的非HA的HDFS对象
     */
    public static FileSystem getDefaultNonHAHDFS(){
        Configuration defaultNonHAHDFSConfiguration = new Configuration();
        defaultNonHAHDFSConfiguration.set("fs.default","hdfs://zk1:9000");
        return getDIYHDFS(defaultNonHAHDFSConfiguration);
    }

    /**
     * 获取默认的HA的HDFS对象
     * @return  默认的HA的HDFS对象
     */
    public static FileSystem getDefaultHAHDFS(){
        Configuration defaultHAHDFSConfiguration = new Configuration();
        defaultHAHDFSConfiguration.set("fs.defaultFS","hdfs://mycluster");
        defaultHAHDFSConfiguration.set("dfs.nameservices","mycluster");
        defaultHAHDFSConfiguration.set("dfs.ha.namenodes.mycluster","nn1,nn2");
        defaultHAHDFSConfiguration.set("dfs.namenode.rpc-address.mycluster.nn1","zk1:9000");
        defaultHAHDFSConfiguration.set("dfs.namenode.rpc-address.mycluster.nn2","zk2:9000");
        defaultHAHDFSConfiguration.set("dfs.client.failover.proxy.provider.mycluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        return getDIYHDFS(defaultHAHDFSConfiguration);
    }

    /**
     * 获取一个本地文本系统对象
     * @return  本地系统对象
     */
    public static FileSystem getLocalFileSytem(){
        try {
            return FileSystem.getLocal(new Configuration());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
