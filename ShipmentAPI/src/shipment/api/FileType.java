package shipment.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)   // ElementType.TYPE "applies to classes"
public @interface FileType {
    // The FileType annotation stores the fileExtension
    String fileExtension() default "";
}
