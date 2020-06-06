package mz.co.vm.randomnumber.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * 
 * @author Clerio Alfredo Faife
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier
@Target(ElementType.METHOD)
public @interface DelayMe {
	
	int time() default 20;

}
