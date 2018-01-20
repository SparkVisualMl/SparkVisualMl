package com.algorithm;import com.algorithm.abstracts.*;import com.algorithm.impl.*;/** * 策略模式，方法模板模式，工厂模式（？？？？。 * 工厂也分为创建数据源插件的工厂， * 创建数据预处理插件的工厂， * 创建数据特征工程插件的工厂， * 创建机器学习插件的工厂， * 创建模型保存的工厂） * * * ARM 新概念设计 * ARM是算法关系映射，指从众多算法中任意选出一个算法作为上一个算法和再选出任意另一个算法作为下一个算法。上一个算法输出作为 * 下一个算法输入在xml文件中的准确映射描述。 */public class test {    public static void main(String args[]){        AlgorithmSparkContext algorithmSparkContext = new AlgorithmSparkContext();        //1 获取输入数据，数据源 设置数据源实现插件，这里最好是单例的，避免重复创建对象        SourceAlgorithm sourceAlgorithm = new SourceAlgorithmImpl();        algorithmSparkContext.setSourcePlugin(sourceAlgorithm);        //2 设置预处理实现插件        PreHandleAlgorithm preHandleAlgorithm = new PreHandleAlgorithmImpl();        algorithmSparkContext.setFeatureProPlugin(preHandleAlgorithm);        //3 设置特征工程实现插件        FeatureProAlgorithm featureProAlgorithm = new FeatureProAlgorithmImpl();        algorithmSparkContext.setPreHandlePlugin(featureProAlgorithm);        //4 设置机器学习实现插件        MlibAlgorithm mlibAlgorithm = new MlibAlgorithmImpl();        algorithmSparkContext.setMlibPlugin(mlibAlgorithm);        //5 设置模型评估插件        ModelTestAlgorithm modelTestAlgoritm = new ModelTestAlgorithmImpl();        algorithmSparkContext.setModelTestPlugin(modelTestAlgoritm);        //6 设置模型保存插件        ModelSaveAlgorithm modelAlgorithm = new ModelAlgorithmImpl();        algorithmSparkContext.setModelSavePlugin(modelAlgorithm);        //7 开始运行        algorithmSparkContext.invokeStepsToLearn();    }}