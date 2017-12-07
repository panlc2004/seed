package com.czy.seed.mvc.sys.controller;


import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.sys.entity.SysAttachment;
import com.czy.seed.mvc.sys.service.SysAttachmentService;
import com.czy.seed.mvc.util.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/sys/attachment")
public class SysAttachmentController extends BaseController<SysAttachment> {

    @Autowired
    private SysAttachmentService sysAttachmentService;

    /**
     * 文件上传功能
     *
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/upLoadFile")
    @ResponseBody
    public Res upLoadFile(@RequestParam("file") MultipartFile file,
                          HttpServletRequest request) throws Exception {
        sysAttachmentService.upLoadFile(file, request);
        return Res.ok();
    }

    @RequestMapping("/downLoadFile")
    public HttpServletResponse downLoadFile(HttpServletResponse response, long id) throws IOException {
        sysAttachmentService.downLoadFile(response, id);
        return response;
    }

}

