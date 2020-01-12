package com.xml.parser.utils.validator;

import com.xml.parser.post.analyse.AnalyseParams;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLStreamHandler;

public class AnalyseParamsValidator implements ConstraintValidator<AnalyseParamsConstraint, AnalyseParams> {

   public void initialize(AnalyseParamsConstraint constraint) {
   }

   @Override
   public boolean isValid(AnalyseParams analyseParams, ConstraintValidatorContext constraintValidatorContext) {
      try {
         var url = new URL(analyseParams.getUrl());
      } catch (MalformedURLException e) {
         return false;
      }
      return true;
   }
}
