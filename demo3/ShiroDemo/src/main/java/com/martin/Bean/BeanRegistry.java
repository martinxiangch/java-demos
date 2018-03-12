package com.martin.Bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.martin.Sdemo.ShiroDemoApplication;

@Configuration
public class BeanRegistry implements BeanDefinitionRegistryPostProcessor , EnvironmentAware{
    
    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0) throws BeansException {
        // TODO Auto-generated method stub
        ShiroDemoApplication.Log("BeanRegistry: postProcessBeanFactory()");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry arg0)
            throws BeansException {
        // TODO Auto-generated method stub
        ShiroDemoApplication.Log("BeanRegistry: postProcessBeanDefinitionRegistry()");
        registerBean(arg0,"student",Student.class);
        registerBean(arg0,"teacher",Teacher.class);
    }
    
    private void registerBean(BeanDefinitionRegistry registry,String name,Class<?> beanClass){
        AnnotatedBeanDefinition annotatedBeanDefinition  = new AnnotatedGenericBeanDefinition(beanClass);
        String beanName = (name != null?name:this.beanNameGenerator.generateBeanName(annotatedBeanDefinition, registry));
        BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(annotatedBeanDefinition, beanName);
        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, registry);
     }

    @Override
    public void setEnvironment(Environment environment) {
        // TODO Auto-generated method stub
        ShiroDemoApplication.Log("[EnvironmentAware]  "+environment.getProperty("JAVA_HOME"));
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(environment, "customer.person.");
        ShiroDemoApplication.Log("[EnvironmentAware]  name:"+propertyResolver.getProperty("name")+",age:"+propertyResolver.getProperty("age")); 
    }
}
