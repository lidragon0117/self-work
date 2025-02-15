package com.lilong.massage.general.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilong.massage.general.MassageGeneralService;
import com.lilong.massage.general.entity.MessageConfig;
import com.lilong.massage.general.mapper.MassageConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : lilong
 * @date : 2024-12-29 16:46
 * @description :
 */
@Slf4j
@Service
public class MassageGeneralServiceImpl extends ServiceImpl<MassageConfigMapper, MessageConfig>
        implements MassageGeneralService {

}
