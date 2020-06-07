package mz.co.vm.randomnumber.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * This annotation  causes any method that is annotated with it to be delayed by 20 seconds by default. 
 * The annotation receives an optional parameter to define the delay time, eg.: @DelayMe(time=3000).
 * 
 * 
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
