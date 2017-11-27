package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.conf.exception.BusinessException;
import com.czy.seed.mvc.sys.entity.SysAttachment;
import com.czy.seed.mvc.sys.mapper.SysAttachmentMapper;
import com.czy.seed.mvc.sys.service.SysAttachmentService;
import com.czy.seed.mvc.util.Res;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

//@Service
public class SysAttachmentServiceImpl extends BaseServiceImpl<SysAttachment> implements SysAttachmentService {

//    private static final Logger logger = LoggerFactory.getLogger(SysAttachmentServiceImpl.class);
//
//    private static final Path FILE_UPLOAD_PATH = Paths.get("F:", "upload");
//    private static final String SAVE_PATH = FILE_UPLOAD_PATH.toString();
//
//    @Autowired
//    private SysAttachmentMapper sysAttachmentMapper;
//
//    @PostConstruct
//    public void init() {
//        File uploadPath = new File(FILE_UPLOAD_PATH.toUri());
//        if (!uploadPath.exists()) {
//            boolean mkdirs = uploadPath.mkdirs();
//            if (!mkdirs) {
//                throw new Error("创建文件上传路径失败");
//            }
//        }
//    }
//
//    /**
//     * 文件上传
//     *
//     * @param file
//     * @param request
//     * @return
//     */
//    public Res upLoadFile(MultipartFile file, HttpServletRequest request) throws IOException {
//        String saveName = UUID.randomUUID().toString();
//        String showName = file.getOriginalFilename();
//        String fileSuffix = "";
//        String[] split = showName.split("\\.");
//        if (split.length > 1) {
//            fileSuffix = "." + split[split.length - 1];
//        }
//        Path fileSavePath = Paths.get(FILE_UPLOAD_PATH.toString(), saveName + fileSuffix);
//        File needSaveFile = new File(fileSavePath.toUri());
//        if (!needSaveFile.createNewFile()) {
//            logger.error("创建文件{}失败", fileSavePath.toString());
//            throw new BusinessException("上传失败");
//        }
//        FileOutputStream outputStream = new FileOutputStream(needSaveFile);
//        outputStream.write(file.getBytes());
//        outputStream.flush();
//        outputStream.close();
//        file.getInputStream().close();
//        this.insertFile(showName, saveName, fileSavePath, fileSuffix);
//        return Res.ok();
//    }
//
//    /**
//     * 保存文件上传数据到数据库
//     *
//     * @param showName
//     * @param saveName
//     * @param fileSavePath
//     * @param fileSuffix
//     * @return
//     */
//    private Res insertFile(String showName, String saveName, Path fileSavePath, String fileSuffix) {
//        SysAttachment sysAttachment = new SysAttachment();
//        sysAttachment.setSysOrgId(-1L);
//        sysAttachment.setSysDeptId(-1L);
//        sysAttachment.setShowName(showName);
//        sysAttachment.setSaveName(saveName);
//        sysAttachment.setSavePath(fileSavePath.toString());
//        sysAttachment.setFileSuffix(fileSuffix.isEmpty() ? "" : fileSuffix.substring(1));
//        this.insert(sysAttachment);
//        return Res.ok();
//    }
//
//    /**
//     * 文件下载
//     *
//     * @param response
//     * @param id
//     * @return
//     * @throws Exception
//     */
//    public Res downLoadFile(HttpServletResponse response, Long id) throws IOException {
//        SysAttachment attachment = this.selectByPrimaryKey(id);
//        //为了解决中文乱码问题
//        String filename = new String(attachment.getShowName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
//        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
//        response.setContentType("application/octet-stream; charset=utf-8");
//
//        // 读到流中
//        InputStream inStream = new FileInputStream(attachment.getSavePath());// 文件原来的存放路径
//        //创建缓冲区
//        OutputStream outStream=response.getOutputStream();
//        // 循环取出流中的数据
//        byte[] b = new byte[1024];
//        int len=0;
//        try {
//            while ((len = inStream.read(b)) > 0){
//                outStream.write(b, 0, len);
//            }
//            response.getOutputStream().flush();
//            inStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return Res.ok();
//    }
}


