/*
  Copyright 2021 kanghouchao
  
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  
  http://www.apache.org/licenses/LICENSE-2.0
  
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package com.github.loaferframework.conf.async;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Optional;
import java.util.concurrent.Executor;

/**
 * <p>
 * 创建时间: 2021年04月17号
 * 联系方式: hchkang8710@gmail.com
 * </p>
 *
 * @author kanghouchao
 * @since 0.0.1
 */
@Slf4j
@EnableAsync
@EnableScheduling
@AllArgsConstructor
@Configuration
@ConditionalOnProperty(prefix = "spring.loafer.async", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(ThreadPoolProperties.class)
public class ExecutorConfiguration extends AsyncConfigurerSupport {

    private final ThreadPoolProperties threadPoolProperties;

    @Bean
    @Override
    public Executor getAsyncExecutor() {
        int processors = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Optional.ofNullable(threadPoolProperties.getCorePoolSize()).orElse(processors));
        executor.setMaxPoolSize(Optional.ofNullable(threadPoolProperties.getMaxPoolSize()).orElse(2 * processors + 1));
        executor.setKeepAliveSeconds(threadPoolProperties.getKeepAliveSeconds());
        executor.setQueueCapacity(threadPoolProperties.getQueueCapacity());
        executor.setThreadNamePrefix(threadPoolProperties.getThreadNamePrefix());
        executor.setAllowCoreThreadTimeOut(threadPoolProperties.getAllowCoreThreadTimeOut());
        executor.setWaitForTasksToCompleteOnShutdown(threadPoolProperties.getWaitForTasksToCompleteOnShutdown());
        executor.setRejectedExecutionHandler((r, e) -> log.warn("Task {} rejected in thread pool : {}.", r, e));
        return executor;
    }

    @Bean
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (e, method, params) -> {
            if (log.isWarnEnabled()) {
                log.error("error executed {} for {},with {} params", method.getName(), e.getMessage(), params.length);
            }
            e.printStackTrace();
        };
    }
}
