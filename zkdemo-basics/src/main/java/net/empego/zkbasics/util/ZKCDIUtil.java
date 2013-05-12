package net.empego.zkbasics.util;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;

import org.zkoss.zkplus.cdi.CDIUtil;


public class ZKCDIUtil {

	/**
	 * Perform injection in obj which is instantiated by the framework but not by CDI.
	 * 
	 * @param <T>
	 * @param obj
	 *          - The instance to inject beans into
	 */
	public static <T> void inject(final Object obj) {
		final BeanManager beanManager = CDIUtil.getBeanManager();

		// CDI uses an AnnotatedType object to read the annotations of a class
		final AnnotatedType<T> type = (AnnotatedType<T>) beanManager.createAnnotatedType(obj.getClass());

		// The extension uses an InjectionTarget to delegate instantiation,
		// dependency injection
		// and lifecycle callbacks to the CDI container
		final InjectionTarget<T> it = beanManager.createInjectionTarget(type);
		// each instance needs its own CDI CreationalContext
		final CreationalContext<T> instanceContext = beanManager.createCreationalContext(null);

		it.inject((T) obj, instanceContext); // call initializer methods and
		// perform field injection
		it.postConstruct((T) obj); // call the @PostConstruct method
	}

}
