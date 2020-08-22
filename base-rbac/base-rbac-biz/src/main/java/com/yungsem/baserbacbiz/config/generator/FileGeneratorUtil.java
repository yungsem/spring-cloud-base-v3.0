package com.yungsem.baserbacbiz.config.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.yungsem.basecommon.pojo.entity.common.BaseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件生成器
 * 配置文档：https://mp.baomidou.com/config/generator-config.html
 *
 * @author yangsen
 * @version 1.0
 * @since 2020-06-01
 */
public class FileGeneratorUtil {
    private static final String author = "yangsen";
    private static final String packageName = "common";
    private static final String tablePrefix = "rbac";
    private static final String[] tables = {"rbac_user"};

    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();
        generator.setGlobalConfig(buildGlobalConfig());
        generator.setDataSource(buildDataSourceConfig());
        generator.setStrategy(buildStrategyConfig());
        generator.setPackageInfo(buildPackageConfig());
        generator.setCfg(buildInjectionConfig());
        generator.setTemplate(buildTemplateConfig());
        generator.execute();
    }

    /**
     * 构建全局配置
     *
     * @return GlobalConfig 对象
     */
    private static GlobalConfig buildGlobalConfig() {
        GlobalConfig config = new GlobalConfig();

        String projectPath = System.getProperty("user.dir") + "/" + "base-rbac/base-rbac-biz";
        config.setOutputDir(projectPath + "/src/main/java");
        config.setFileOverride(true);
        config.setAuthor(author);
        config.setSwagger2(true);
        config.setBaseResultMap(true);
        config.setDateType(DateType.ONLY_DATE);
        config.setBaseColumnList(true);
        config.setIdType(IdType.AUTO);
        config.setEntityName("%sEntity");
        // config.setControllerName("%sController");
        config.setServiceName("%sService");
        config.setServiceImplName("%sServiceImpl");
        config.setMapperName("%sMapper");
        config.setXmlName("%sMapper");
        config.setOpen(false);

        return config;
    }

    /**
     * 数据库配置
     *
     * @return DataSourceConfig
     */
    public static DataSourceConfig buildDataSourceConfig() {
        DataSourceConfig config = new DataSourceConfig();
        config.setDbType(DbType.MYSQL);
        config.setDriverName("com.mysql.jdbc.Driver");
        config.setUrl("jdbc:mysql://yungsem.cn:3306/auth?useUnicode=true&useSSL=false&characterEncoding=utf8");
        config.setUsername("root");
        config.setPassword("root");
        return config;
    }

    /**
     * 数据库表配置
     *
     * @return StrategyConfig
     */
    public static StrategyConfig buildStrategyConfig() {
        StrategyConfig config = new StrategyConfig();
        config.setNaming(NamingStrategy.underline_to_camel);
        config.setColumnNaming(NamingStrategy.underline_to_camel);
        config.setTablePrefix(tablePrefix);
        config.setSuperEntityClass(BaseEntity.class);
        config.setInclude(tables);
        config.setEntityLombokModel(true);
        config.setRestControllerStyle(true);
        return config;
    }

    /**
     * 包名配置
     *
     * @return PackageConfig
     */
    private static PackageConfig buildPackageConfig() {
        PackageConfig config = new PackageConfig();
        config.setParent("com.yungsem.baserbacbiz");
        // config.setController("controller.pc." + packageName);
        config.setService("service." + packageName);
        config.setServiceImpl("service." + packageName + ".impl");
        config.setMapper("mapper." + packageName);
        config.setEntity("mapper." + packageName);
        config.setXml("mapper." + packageName);
        return config;
    }

    /**
     * 模板配置
     *
     * @return TemplateConfig
     */
    private static TemplateConfig buildTemplateConfig() {
        TemplateConfig config = new TemplateConfig();
        // config.setController("/templates/controller.java.vm");
        config.setEntity("/templates/entity.java.vm");
        config.setMapper("/templates/mapper.java.vm");
        config.setXml("/templates/mapper.xml.vm");
        config.setService("/templates/service.java.vm");
        config.setServiceImpl("/templates/serviceImpl.java.vm");
        return config;
    }

    /**
     * 注入配置
     *
     * @return InjectionConfig 对象
     */
    private static InjectionConfig buildInjectionConfig() {
        InjectionConfig config = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                this.setMap(map);
            }
        };

        return config;
    }

}
