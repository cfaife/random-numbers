package mz.co.vm.randomnumber.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * 
 * @author Clerio Alfredo Faife
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier
public @interface DelayMe {
	
	int VALUE = 30000;

}
