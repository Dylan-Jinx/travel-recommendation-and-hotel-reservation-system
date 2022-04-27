package se.xmut.trahrs;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.util.Collections;

public class CodeGenerator {

    private static final String PREFIX = "";
    private static final String MODULE_NAME = "";
    //要生成的表名
    private static final String[] TABLES= {"想生成代码的表名"};
    private static final String DATABASE_URL = "jdbc:mysql://42.192.5.34/trahrs?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "a13850526746";
    private static final String BASE_PACKAGE = "se.xmut.trahrs";
    private static final DataSourceConfig.Builder DATA_CONFIG_BUILDER=
            new DataSourceConfig.Builder(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD)
                    .dbQuery(new MySqlQuery())
                    .schema("public")
                    .typeConvert(new MySqlTypeConvert())
                    .keyWordsHandler(new MySqlKeyWordsHandler());

    public static void main(String[] args) {
        final String parentPath = System.getProperty("user.dir")+"/src/main";
        final String projectPath = parentPath+"/java";
        FastAutoGenerator.create(DATA_CONFIG_BUILDER)
                .globalConfig(builder -> {
                    builder.enableSwagger()
                            .fileOverride()
                            .disableOpenDir()
                            .outputDir(projectPath);
                })
        .packageConfig(builder -> {
            builder.parent(BASE_PACKAGE)
                    .pathInfo(Collections.singletonMap(OutputFile.xml,parentPath+"/resources/mapper"));
        })
        .strategyConfig(builder -> {
            builder.addExclude()
                    .mapperBuilder().enableBaseColumnList().enableBaseResultMap()
                    .serviceBuilder().formatServiceFileName("%sService")
                    .controllerBuilder().enableRestStyle()
                    .entityBuilder().columnNaming(NamingStrategy.underline_to_camel).naming(NamingStrategy.underline_to_camel)
                    .enableLombok();
        })
//                .templateEngine(new VelocityTemplateEngine())
                .execute();


    }
}
