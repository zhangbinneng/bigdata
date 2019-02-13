package org.qianfeng.bigdata.hdfs.oldhdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.net.URI;
public class HAHDFSOperator {
    public static void main(String[] args) throws Exception {
        FileSystem hdfs = getHAHDFS();
        HDFSOperator.makedir(hdfs,"/test1112");
    }

    /**
     * 获取一个配置了HA的Hadoop集群的链接
     * @return
     * @throws Exception
     */
    public static FileSystem getHAHDFS() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://mycluster");
        conf.set("dfs.nameservices","mycluster");
        conf.set("dfs.ha.namenodes.mycluster","nn1,nn2");
        conf.set("dfs.namenode.rpc-address.mycluster.nn1","zk1:8020");
        conf.set("dfs.namenode.rpc-address.mycluster.nn2","zk2:8020");
        conf.set("dfs.client.failover.proxy.provider.mycluster","org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        return FileSystem.get(new URI("hdfs://mycluster"),conf,"root");
    }
}
