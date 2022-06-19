package com.springboot.boilerplate.Logger;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RequestAsynchronousLoggingServiceImpl implements RequestAsynchronousLoggingService {

	private static Logger log = LoggerFactory.getLogger(RequestAsynchronousLoggingServiceImpl.class);
    
	@Autowired
    private ApiLogRepository apiLogRepository;

    @Async("asyncExec")
    public void logRequest(ApiLogDto apiLogDto) {
        try {
        	ModelMapper modelMapper = new ModelMapper();
        	ApiLog apiLog = modelMapper.map(apiLogDto, ApiLog.class);
        	apiLogRepository.save(apiLog);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

}
