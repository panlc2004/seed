package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysAttachment;
import com.czy.seed.mvc.sys.mapper.SysAttachmentMapper;
import com.czy.seed.mvc.sys.service.SysAttachmentService;
import com.czy.seed.mvc.util.ILoginUserTool;
import com.czy.seed.mvc.util.Res;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class SysAttachmentServiceImpl extends BaseServiceImpl<SysAttachment> implements SysAttachmentService {

    private static final Logger logger = LoggerFactory.getLogger(SysAttachmentServiceImpl.class);

    private static final Path FILE_UPLOAD_PATH = Paths.get("F:", "upload");
    private static final String SAVE_PATH = FILE_UPLOAD_PATH.toString();

    @Autowired
    private SysAttachmentMapper sysAttachmentMapper;

    @Autowired
    private ILoginUserTool loginUserTool;

    @PostConstruct
    public void init() {
        File uploadPath = new File(FILE_UPLOAD_PATH.toUri());
        if (!uploadPath.exists()) {
            boolean mkdirs = uploadPath.mkdirs();
            if (!mkdirs) {
                throw new Error("创建文件上传路径失败");
            }
        }
    }

    /**
     * 文件上传
     *
     * @param file
     * @param request
     * @return
     */
    @Override
    public Res upLoadFile(MultipartFile file, HttpServletRequest request) throws IOException {
        if (file.isEmpty()) {
            return Res.error("附件为空", null);
        }

        //文件名
        String fileName = file.getOriginalFilename();
        //文件后缀
        int suffixIndex = fileName.lastIndexOf(".");
        String fileSuffix = fileName.substring(suffixIndex);
//        String fileSuffix = "";
//        String[] split = fileName.split("\\.");
//        if (split.length > 1) {
//            fileSuffix = "." + split[split.length - 1];
//        }

        //文件保存名称处理
        String extraName = UUID.randomUUID().toString();
        //保存时文件名称加上uuid，防止覆盖
        String saveName = fileName.substring(0, suffixIndex) + "_" + extraName;
        Path fileSavePath = Paths.get(FILE_UPLOAD_PATH.toString(), saveName + fileSuffix);

        //保存附件
        File needSaveFile = new File(fileSavePath.toUri());
        try {
            file.transferTo(needSaveFile);
        } catch (Exception e) {
            return Res.error("附件上传失败", null);
        }

        //记录附件信息
        this.insertFile(fileName, extraName, fileSavePath, fileSuffix);

        return Res.ok();
    }

    /**
     * 保存文件上传数据到数据库
     *
     * @param showName
     * @param saveName
     * @param fileSavePath
     * @param fileSuffix
     * @return
     */
    private Res insertFile(String showName, String saveName, Path fileSavePath, String fileSuffix) {
        SysAttachment sysAttachment = new SysAttachment();
        sysAttachment.setSysOrgId(loginUserTool.getLoginUser().getSysOrgId());
        sysAttachment.setSysDeptId(loginUserTool.getLoginUser().getSysDeptId());
        sysAttachment.setShowName(showName);
        sysAttachment.setSaveName(saveName);
        sysAttachment.setSavePath(fileSavePath.toString());
        sysAttachment.setFileSuffix(fileSuffix.isEmpty() ? "" : fileSuffix.substring(1));
        this.insert(sysAttachment);
        return Res.ok();
    }

    /**
     * 文件下载
     *
     * @param response
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Res downLoadFile(HttpServletResponse response, Long id) throws IOException {
        SysAttachment attachment = this.selectByPrimaryKey(id);
        //为了解决中文乱码问题
        String filename = new String(attachment.getShowName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        response.setContentType("application/octet-stream; charset=utf-8");

        // 读到流中
        InputStream inStream = new FileInputStream(attachment.getSavePath());// 文件原来的存放路径
        //创建缓冲区
        OutputStream outStream=response.getOutputStream();
        // 循环取出流中的数据
        byte[] b = new byte[1024];
        int len=0;
        try {
            while ((len = inStream.read(b)) > 0){
                outStream.write(b, 0, len);
            }
            response.getOutputStream().flush();
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Res.ok();
    }
}


