package com.wxsoft;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成器演示
 * 扩展版本 : 支持自定义 模版
 * </p>
 */
public class MpGeneratorShiro {

    //代码输出路径
//    private static String outputDir = "E:/IdeaProjects/shop/shop-parent/mybatis-plus-generator/src/main/java";
    private static String outputDir = "E:/IdeaProjects/platform/platform-parent/platform-shiro-springboot/src/main/java";
    //生成 类的  父包名
    private static String parentPackage = "com.wxsoft.shiro.business";
    //数据库地址
    private static String url = "jdbc:mysql://localhost:3306/shiro";
    //数据库用户名
    private static String user = "root";
    //数据库密码
    private static String password = "root";
    //需要生成的表
    private static String[] tables = new String[]{"sys_permissions"};
    //自定义模版路径
    private static String templetPath = "/templates/shiro";

    public static void main(String[] args) {
//        assert (false) : "代码生成属于危险操作，请确定配置后取消断言执行代码生成！";
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Velocity
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("cl");
        gc.setOutputDir(outputDir);
        gc.setFileOverride(false);// 是否覆盖同名文件，默认是false
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        /* 自定义文件命名，注意 %s 会自动填充表实体属性！ */
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sDao");
        // gc.setServiceName("MP%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                return super.processTypeConvert(fieldType);
            }
        });
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(user);
        dsc.setPassword(password);
        dsc.setUrl(url + "?useUnicode=true&characterEncoding=utf8");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
//         strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setTablePrefix(new String[] { "sys_" });// 此处可以修改为您的表前缀
//        strategy.setNaming(NamingStrategy.nochange);// 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
//        strategy.setInclude(new String[] { "sys_menu" }); // 需要生成的表
//        strategy.setInclude(new String[] { "student_info" }); // 需要生成的表
//        strategy.setInclude(new String[]{"stud_work"}); // 需要生成的表
//        strategy.setInclude(new String[]{"stud_learn"}); // 需要生成的表
        strategy.setInclude(tables); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 自定义实体父类
        // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuilderModel(true);
        //生成rest风格controller
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(parentPackage);
        // pc.setModuleName("test");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() +
                        "-mp");
                this.setMap(map);
            }
        };
        //
        // // 自定义 xxList.jsp 生成
        List<FileOutConfig> focList = new ArrayList<>();

        //自定义 cope文件
        focList.add(new FileOutConfig( templetPath + "/vue.html.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                String path = gc.getOutputDir();

                return path + "/copy/" + tableInfo.getEntityName() + ".vue";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        focList.add(new FileOutConfig( templetPath + "/vue.html.add.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                String path = gc.getOutputDir();

                return path + "/copy/" + "AddEditDialog.vue";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        //自定义 Vo java 类
        focList.add(new FileOutConfig(templetPath + "/entityVo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                String path = gc.getOutputDir();
                String pacg = pc.getParent();
//                return "/develop/code/xml/" + tableInfo.getEntityName() +".xml";
                return path + "/" + pacg.replace(".", "/") + "/entity/" + tableInfo.getEntityName() + "Vo.java";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        //增加list.jsp
//        focList.add(new FileOutConfig(templetPath + "/list.jsp.ftl") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                String path = gc.getOutputDir();
//                System.out.println(tableInfo.getName());
//                System.out.println(tableInfo.getEntityName());
//                System.out.println(tableInfo.getEntityPath());
//                // 自定义输入文件名称
//                return path + "/../webapp/jsp/" + tableInfo.getEntityPath() + "/list.jsp";
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);

        // // 调整 xml 生成目录演示
        focList.add(new FileOutConfig(templetPath + "/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String path = gc.getOutputDir();

                return path + "/../resources/mapper/" + tableInfo.getEntityName() + ".xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        //
        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        mpg.setTemplate(tc);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
//        TemplateConfig tc = new TemplateConfig();
//        tc.setController(templetPath + "/controller.java.ftl");
//        tc.setEntity(templetPath + "/entity.java.ftl");
//        tc.setMapper(templetPath + "/mapper.java.ftl");
//        tc.setXml(null);
//        tc.setService(templetPath + "/service.java.ftl");
//        tc.setServiceImpl(templetPath + "/serviceImpl.java.ftl");
////        如上任何一个模块如果设置 空 OR Null 将不生成该模块。
//        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();

        // 打印注入设置【可无】
        // System.err.println(mpg.getCfg().getMap().get("abc"));
    }
}