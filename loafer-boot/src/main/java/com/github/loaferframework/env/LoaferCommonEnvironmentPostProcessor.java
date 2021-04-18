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
import net.dreamlu.mica.auto.annotation.AutoEnvPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigDataEnvironmentPostProcessor;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

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
@AutoEnvPostProcessor
public class LoaferCommonEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    /**
     * 序号
     */
    public static final int DEFAULT_ORDER = ConfigDataEnvironmentPostProcessor.ORDER + 1;

    /**
     * Yaml loader
     */
    private final PropertySourceLoader loader = new YamlPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Resource resource = new ClassPathResource("loafer.yaml");
        try {
            List<PropertySource<?>> sources = this.loader.load("LoaferBasicEnvironmentConfiguration", resource);
            sources.forEach(environment.getPropertySources()::addLast);
        } catch (IOException e) {
            log.error("Can't load LoaferBasicEnvironmentConfiguration", e);
        }
    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }

}
