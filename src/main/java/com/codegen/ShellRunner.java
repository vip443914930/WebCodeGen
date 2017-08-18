package com.codegen;

import com.codegen.exception.GeneratorException;
import com.codegen.config.XmlConfigParser;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 支持命令行运行项目
 * @author chenxiaojun
 */
public class ShellRunner {
    private static final String CONFIG_FILE = "-configFile"; //配置文件

    public static void main(String[] args) throws IOException, GeneratorException {
        if(args == null || args.length ==0){
            System.out.println("读取测试目录配置文件!D:\\workspace\\git\\PracticeArea\\WebCodeGen\\ftl\\generatorConfig.xml");
            args = new String[]{"-configFile","D:\\workspace\\git\\PracticeArea\\WebCodeGen\\ftl\\generatorConfig.xml"};
        }
        System.out.println("开始执行命令!");
        if (args.length == 0) {
            System.out.println("args is empty");
            System.exit(0);
            return;
        }
        //命令参数
        Map<String, String> arguments = parseCommandLine(args);
        System.out.println(arguments);
        if (!arguments.containsKey(CONFIG_FILE)) {
            System.out.println("args do not contain configFile");
            return;
        }
        String configFile = arguments.get(CONFIG_FILE);
        if (!new File(configFile).exists()) {
            System.out.println("configFile is not exist");
            return;
        }
        XmlConfigParser parser = new XmlConfigParser(configFile);
        System.out.println("装载配置文件成功!" + configFile);
        CodeGenProcessor.invoke(parser);//生成代码
    }

    private static Map<String, String> parseCommandLine(String[] args) {
        Map<String, String> arguments = new TreeMap<String, String>();
        for (int i = 0; i < args.length; ++i) {
            if (CONFIG_FILE.equalsIgnoreCase(args[i])) {
                if ((i + 1) < args.length) {
                    arguments.put(CONFIG_FILE, args[i + 1]);
                    ++i;
                } else {
                    System.out.println("args error");
                    System.exit(-1);
                }
            }
        }
        return arguments;
    }
}
