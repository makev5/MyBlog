package com.xiaoke.blog.controller;

import com.qiniu.http.Response;
import com.xiaoke.blog.common.Result;
import com.xiaoke.blog.service.QiniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/qiniu")
public class QiniuController {

    @Autowired
    private QiniuService qiniuService;

    /**
     * 以流的形式上传图片
     *
     * @param file
     * @return 返回访问路径
     * @throws IOException
     */
    @PostMapping("upload")
    public Result uploadFile(@RequestParam(value = "file") MultipartFile file) throws IOException {
        return Result.success(qiniuService.uploadFile(file.getInputStream()));
    }

    @GetMapping("/list")
    public Result getFileList(){
        return Result.success(qiniuService.getFileList());
    }

    /**
     * 删除文件
     *
     * @param key
     * @return
     * @throws IOException
     */
    @GetMapping("delete/{key}")
    public Result deleteFile(@PathVariable String key) throws IOException {
        return Result.success(qiniuService.delete(key));
    }
}