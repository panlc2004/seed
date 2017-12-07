package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysAttachment;
import com.czy.seed.mvc.util.Res;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface SysAttachmentService extends BaseService<SysAttachment> {
    Res upLoadFile(MultipartFile file, HttpServletRequest request) throws IOException;

    Res downLoadFile(HttpServletResponse response, Long id) throws IOException;

}
