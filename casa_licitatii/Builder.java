package casa_licitatii;

import java.lang.reflect.InvocationTargetException;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class Builder<T> {
	
	private T instance;
	
	public Builder(Class<T> clazz) {
		try {
			
			instance = clazz.getDeclaredConstructor().newInstance();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		}
	}
	
	
	//metoda "with" are ca parametru o expresie lambda
	public Builder<T> with(Consumer<T> setter){

		//se aplica expresia transmisa
		setter.accept(instance);
		return this;
	}
	
	public static <T> Builder<T> build(Class<T> clazz){
		return new Builder<>(clazz);
	}
	
	public T get() {
		return instance;
	}
	
}
