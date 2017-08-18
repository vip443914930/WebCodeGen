package com.codegen.api;
import com.codegen.meta.GeneratorFile;

/**
 * 生成文件接口
 * Created by chenxiaojun on 2017/8/2.
 */
public interface IGenerator<generatorFile extends GeneratorFile> {
   public void invoke(generatorFile file);
}
