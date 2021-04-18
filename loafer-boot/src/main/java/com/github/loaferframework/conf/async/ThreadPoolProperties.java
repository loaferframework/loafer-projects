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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 创建时间: 2021年04月17号
 * 联系方式: hchkang8710@gmail.com
 * </p>
 *
 * @author kanghouchao
 * @since 0.0.1
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "spring.loafer.async")
public class ThreadPoolProperties {

    /**
     * 是否开启线程池配置
     */
    private Boolean enabled;

    /**
     * 核心线程数
     */
    private Integer corePoolSize;

    /**
     * 最大线程数
     */
    private Integer maxPoolSize;

    /**
     * 阻塞队列大小
     */
    private Integer queueCapacity;

    /**
     * 线程名称前缀
     */
    private String threadNamePrefix;

    /**
     * 空闲存活时间
     */
    private Integer keepAliveSeconds;

    /**
     * 是否运行核心线程超时释放
     */
    private Boolean allowCoreThreadTimeOut;

    /**
     * 是否在项目停止时等待线程结束
     */
    private Boolean waitForTasksToCompleteOnShutdown;

}
