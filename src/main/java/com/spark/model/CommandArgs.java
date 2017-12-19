package com.spark.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("classpath:config/command-args.properties")
public class CommandArgs {
    @Value("${spark.job.master}")
    private String master;//spark 提交地址 yarn 或者 local 或者 spark://host:7077等
    @Value("${spark.job.class}")
    private String classPath;//指定jar包的类路径，包全路径
    @Value("${spark.job.name}")
    private String name;//提交的job名字
    @Value("${spark.job.conf}")
    private String conf;//???配置文件路径
    @Value("${spark.job.executor-memory}")
    private String executorMemory;//分配executor内存
    @Value("${spark.job.total-executor-cores}")
    private String totalExecutorCores;//总共executor内存
    @Value("${spark.job.supervise}")
    private String supervise;//推测执行
    @Value("${spark.job.num-executors}")
    private String numExecutors;//executor个数
    @Value("${spark.job.deploy-mode}")
    private String deployMode;

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    public String getDeployMode() {
        return this.deployMode;
    }

    public void setDeployMode(String deployMode) {
        this.deployMode = deployMode;
    }

    public String getExecutorMemory() {
        return executorMemory;
    }

    public void setExecutorMemory(String executorMemory) {
        this.executorMemory = executorMemory;
    }

    public String getTotalExecutorCores() {
        return totalExecutorCores;
    }

    public void setTotalExecutorCores(String totalExecutorCores) {
        this.totalExecutorCores = totalExecutorCores;
    }

    public String getSupervise() {
        return supervise;
    }

    public void setSupervise(String supervise) {
        this.supervise = supervise;
    }

    public String getNumExecutors() {
        return numExecutors;
    }

    public void setNumExecutors(String numExecutors) {
        this.numExecutors = numExecutors;
    }
}
