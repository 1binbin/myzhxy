package com.example.myzhxy.utils;

import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传工具类
 *
 * @Author hongxiaobin
 * @Time 2022/10/11-15:50
 */
public class UploadFile {
    //    存储文件上传失败的错误信息
    private static Map<String, Object> error_result = new HashMap<>();
    //    存储头像上传结果信息
    private static Map<String, Object> upload_result = new HashMap<>();

    /**
     * 效验所上传图片的大小及格式等信息...
     *
     * @Param: MultipartFile photo, String path
     * @Return: Map<String, Object>
     */
    private static Map<String, Object> uploadPhoto(MultipartFile photo, String path) {
//        限制头像大小（20M）
        int MAX_SIZE = 20971520;
//        获取头像的原始名称
        String originalFilename = photo.getOriginalFilename();

//        限制上传文件大小
        if (photo.getSize() > MAX_SIZE) {
            error_result.put("success", false);
            error_result.put("msg", "上传的图片大小不能超过20M");
            return error_result;
        }
//        限制上传的文件类型
        String[] suffixs = new String[]{".png", ".PNG", ".jpg", ".JPG", ".jpeg", ".JPEG", ".gif", ".GIF", ".bmp", ".BMP"};
        SuffixFileFilter suffixFileFilter = new SuffixFileFilter(suffixs);
        if (suffixFileFilter.accept(new File(path + originalFilename))) {
            error_result.put("success", false);
            error_result.put("msg", "进制上传此类型的文件，请上传图片类型文件！");
            return error_result;
        }
//        如果保存文件的路径不存在，则创建该目录
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        return null;
    }

    /**
     * (提取公共代码 : 提高代码的可重用性)获取头像的上传结果信息
     *
     * @Param: MultipartFile photo, String dirPaht, String portraitPath
     * @Return: Map<String, Object>
     */
    public static Map<String, Object> getUpload_result(MultipartFile photo, String dirPath, String portraitPath) {
        if (!photo.isEmpty() && photo.getSize() > 0) {
//            获取图片原始名称
            String originalFilename = photo.getOriginalFilename();
//            上传图片
            Map<String, Object> map = UploadFile.uploadPhoto(photo, dirPath);
            if (map != null) {
                return error_result;
            }
//            使用UUID重命名图片名称（uuid_原始图片名称
            String newPhotoName = UUID.randomUUID() + "_" + originalFilename;
//            上传到指定文件夹
            try {
                photo.transferTo(new File(dirPath + newPhotoName));
                upload_result.put("success", true);
                upload_result.put("portrait_path", portraitPath + newPhotoName);
            } catch (IOException e) {
                e.printStackTrace();
                upload_result.put("success", false);
                upload_result.put("msg", "上传文件失败! 服务器端发生异常!");
                return upload_result;
            }


        } else {
            upload_result.put("success", false);
            upload_result.put("msg", "头像上传失败! 未找到指定图片!");
        }
        return upload_result;
    }
}
