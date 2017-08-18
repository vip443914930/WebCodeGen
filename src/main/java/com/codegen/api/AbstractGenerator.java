package com.codegen.api;

import com.codegen.ShellRunner;
import com.codegen.config.ProjectConfig;
import com.codegen.context.Context;
import com.codegen.enums.ErrorCodeEnum;
import com.codegen.exception.GeneratorException;
import com.codegen.meta.GeneratorFile;
import com.codegen.util.JarSourceUtil;
import com.codegen.util.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

/**
 *
 * Created by chenxiaojun on 2017/8/2.
 */
public abstract class AbstractGenerator<ctx extends Context> implements IGenerator<GeneratorFile>{
    private Configuration cfg;
    private ctx context;

    /** 执行 **/
    public void invoke(GeneratorFile file) {
        String destFilePath ="";
        try {
            if(cfg == null) cfg = getFtlConfiguration();
            if(context == null)  context = createContext();
            baseValid(file, context);
            ProjectConfig projectConfig = context.getProjectConfig();
            for (String relativePath : file.getRelativePath()) {
                destFilePath = String.format("%s/%s", projectConfig.getProjectPath(), relativePath);
                switch (file.getGeneratorTypeEnum()){
                    case FTL:
                        generaterFile(file.getSource(), destFilePath);
                        break;
                    case COPY_FILE:
                        copyFile(file.getSource(), destFilePath);
                        break;
                    case COPY_FOLDER:
                        copyFolder(file.getSource(), destFilePath);
                        break;
                    case COPY_JAR_FILE:
                        //new JarSourceUtil().loadRecourseFromJarByFolder(file.getSource());
                        break;
                    case COPY_JAR_FOLDER:
                        new JarSourceUtil(ShellRunner.class, destFilePath).loadRecourseFromJarByFolder(file.getSource());
                        break;
                    case GENERATOR_FOLDER:
                        GeneratorFolder(destFilePath);
                        break;
                    case CUSTOMER_INVOKE:
                        customerInvoke(cfg, file, context);
                        break;
                    default:
                        throw new GeneratorException(ErrorCodeEnum.GENERATOR_FILE_ERROR, "没有找到处理器");
                }
            }
        } catch (Exception e) {
            System.err.println(String.format("生成失败![%s]原因：%s", destFilePath, e.getMessage()));
            e.printStackTrace();
        }
    }

    public abstract void customerInvoke(Configuration cfg, GeneratorFile file, ctx context);

    private void baseValid(GeneratorFile file, ctx context) throws GeneratorException{
        if(file.getGeneratorTypeEnum() == null){
            throw new GeneratorException(ErrorCodeEnum.VALID_ERROR, "生成方式不存在[GeneratorTypeEnum]");
        }
        if(context.getProjectConfig() == null){
            throw new GeneratorException(ErrorCodeEnum.VALID_ERROR, "项目配置不存在[ProjectConfig]");
        }
        if(file.getRelativePath() == null || file.getRelativePath().length == 0){
            throw new GeneratorException(ErrorCodeEnum.VALID_ERROR, "不存在要生成的文件/文件夹");
        }
        onValid(file, context);
    }

    public abstract void onValid(GeneratorFile fileInfo, ctx Context) throws GeneratorException;

    /** 执行之前做一些操作，比如扩展等 **/
    public abstract ctx createContext() throws GeneratorException;

    public abstract Configuration getFtlConfiguration() throws IOException;

    /***
     *  生成文件
     */
    private void generaterFile(String ftl, String filePath) throws IOException, TemplateException, GeneratorException {
        Template temp = cfg.getTemplate(ftl);
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                throw new GeneratorException(ErrorCodeEnum.GENERATOR_FILE_ERROR, "创建目标文件所在目录失败");
            }
        }
        if(file.exists()){
            if(!StringUtils.equalsIgnoreCase("true", context.getProjectConfig().getOverWrite())) {
                throw new GeneratorException(ErrorCodeEnum.GENERATOR_FILE_ERROR, "创建单个文件失败! 文件已存在");
            }
        }else {
            if (file.createNewFile()) {
                System.out.println(String.format("创建单个文件%s成功!", file.getPath()));
            } else {
                throw new GeneratorException(ErrorCodeEnum.GENERATOR_FILE_ERROR, "创建单个文件失败!");
            }
        }
        Writer out = null;
        try {
            out = new FileWriter(file);
            temp.process(context, out);
            out.flush();
            System.out.println(String.format("写入单个文件%s完成!", file.getPath()));
        } catch (Exception e) {
            throw new GeneratorException(ErrorCodeEnum.GENERATOR_FILE_ERROR, "写入失败!");
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    /**
     * 复制单个文件
     * @param oldPath 原文件路径 如：c:/fqf.txt
     * @param newPath 复制后路径 如：f:/fqf.txt
     */
    private void copyFile(String oldPath, String newPath) {
        System.out.println("开始复制单个文件：[" + oldPath + "] TO [" + newPath + "]");
        try {
            int byteSum = 0;
            int byteRead = 0;
            File oldFile = new File(oldPath);
            if (oldFile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ( (byteRead = inStream.read(buffer)) != -1) {
                    byteSum += byteRead; //字节数 文件大小
                    fs.write(buffer, 0, byteRead);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }
        System.out.println("结束复制单个文件：[" + oldPath + "] TO [" + newPath + "]");
    }

    /**
     * 复制整个文件夹内容
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     */
    private void copyFolder(String oldPath, String newPath) {
        newPath += (oldPath.endsWith(File.separator) ? "": File.separator );
        System.out.println("开始复制文件夹：[" + oldPath + "] TO [" + newPath + "]");
        try {
            GeneratorFolder(newPath);
            File oldFile = new File(oldPath);
            String[] file = oldFile.list();
            File temp;
            for (int i = 0; i < file.length; i++) {
                if(oldPath.endsWith(File.separator)){
                    temp=new File(oldPath + file[i]);
                } else{
                    temp=new File(oldPath + File.separator + file[i]);
                }
                if(temp.isFile()){
                    copyFile(temp.getAbsolutePath(), newPath + temp.getName());
                } else if(temp.isDirectory()){
                    // 如果是子文件夹
                    copyFolder(oldPath + "/" + file[i],newPath + file[i]);
                }
            }
        }
        catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();
        }
        System.out.println("结束复制文件夹：[" + oldPath + "] TO [" + newPath + "]");
    }

    /**
     * 生成文件夹
     * @param newPath String 复制后路径 如：f:/fqf/ff
     */
    private void GeneratorFolder(String newPath) {
        try {
            File newFile = new File(newPath);
            if(!newFile.exists() && !newFile.mkdirs()){
                throw new GeneratorException(ErrorCodeEnum.GENERATOR_FILE_ERROR, "创建文件夹失败!" + newPath);
            }
        }
        catch (Exception e) {
            System.out.println("创建文件夹文件夹出错" + e.getMessage());
        }
    }

}
