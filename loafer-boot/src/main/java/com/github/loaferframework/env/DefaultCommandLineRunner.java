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
package com.github.loaferframework.env;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

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
@Component
public class DefaultCommandLineRunner implements CommandLineRunner, Ordered {

    private final String env;

    private final String systemName;

    private final int port;

    public DefaultCommandLineRunner(@Value("${system.env}") String env,
                                    @Value("${system.name}") String systemName,
                                    @Value("${system.port}") int port) {
        this.env = env;
        this.systemName = systemName;
        this.port = port;
    }

    @Override
    public void run(String... args) {
        log.info("==========================项目启动成功==========================");
        log.info("====================项目名称:{}, 启动端口:{}, 配置:{}=====================", this.systemName, this.port, this.env);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
