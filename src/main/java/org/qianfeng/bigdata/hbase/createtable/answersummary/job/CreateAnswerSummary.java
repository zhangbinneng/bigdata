package org.qianfeng.bigdata.hbase.createtable.answersummary.job;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import org.qianfeng.bigdata.hbase.createtable.answersummary.mapper.MyMapper;
import org.qianfeng.bigdata.hbase.createtable.answersummary.reducer.MyReducer;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;


/**
 * @description
 * @author: 张斌能
 * @create: 2018-12-06 17:16:33
 **/

public class CreateAnswerSummary {
    public static void main(String[] args) throws Exception {
        // 创建Configuration
        Configuration conf = HadoopUtil.getRemoteHadoopConf();
        conf.set("hbase.zookeeper.quorum", "192.168.216.50,192.168.216.51,192.168.216.52");// zookeeper节点信息
        conf.set("hbase.zookeeper.property.clientPort", "2181");// zookeeper端口
        conf.set(TableOutputFormat.OUTPUT_TABLE,args[1]);//设置输出到表中
        try {
            Connection connection= ConnectionFactory.createConnection(conf);
            Admin admin = connection.getAdmin();// hbase表管理类
            TableName tableName = TableName.valueOf(args[1]);// 获取表名
            // 如果表名已存在 删除表
            if(admin.tableExists(tableName)){
                admin.disableTable(tableName);
                admin.deleteTable(tableName);
            }
            // 创建表的描述类
            HTableDescriptor descriptor = new HTableDescriptor(tableName);
            // 创建列簇的描述类
            HColumnDescriptor family = new HColumnDescriptor(args[2]);
            // 将列簇添加到表中
            descriptor.addFamily(family);
            admin.createTable(descriptor);

            Job job = Job.getInstance(conf,"upload to hbase");
            TableMapReduceUtil.addDependencyJars(job);// 设置依赖
            job.setJarByClass(CreateAnswerSummary.class);

            job.setMapperClass(MyMapper.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(NullWritable.class);


            job.setReducerClass(MyReducer.class);

            FileInputFormat.addInputPath(job,new Path(args[0]));
            job.setOutputFormatClass(TableOutputFormat.class);
            System.exit(job.waitForCompletion(true)?0:1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
