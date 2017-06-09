package com.czy.seed.mvc.wbm.test.service;

import com.czy.seed.mvc.wbm.test.mapper.MySqlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by panlc on 2017-04-11.
 */
@Service
public class MySqlService {
    @Autowired
    private MySqlMapper mySqlMapper;
}
