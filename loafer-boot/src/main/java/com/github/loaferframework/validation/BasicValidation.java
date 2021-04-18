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
package com.github.loaferframework.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.function.Predicate;

/**
 * <p>
 * 创建时间: 2021年04月17号
 * 联系方式: hchkang8710@gmail.com
 * </p>
 *
 * @author kanghouchao
 * @since 0.0.1
 */
public abstract class BasicValidation<A extends Annotation, T> implements ConstraintValidator<A, T> {

    private Predicate<T> p;

    @Override
    public void initialize(A constraintAnnotation) {
        p = this.predicate(constraintAnnotation);
    }

    @Override
    public boolean isValid(T t, ConstraintValidatorContext context) {
        return p.test(t);
    }

    protected abstract Predicate<T> predicate(A annotation);
}