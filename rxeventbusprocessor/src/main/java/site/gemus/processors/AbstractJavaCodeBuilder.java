package site.gemus.processors;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;

/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

public abstract class AbstractJavaCodeBuilder {
     final void build(Set<? extends Element> elements, Filer filer) {
        MethodSpec methodSpec = createMethod(elements);
        TypeSpec typeSpec = createClass(methodSpec);
        Element element = elements.iterator().next();
        JavaFile javaFile = createFile(element, typeSpec);
        CreateAssistCode createAssistCode = new CreateAssistCode();
        createAssistCode.buildAssistCode(element, filer);
        try {
            javaFile.writeTo(filer);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param element 注解对象
     * @param typeSpec 类构造对象
     * @return 顶级java文件构造对象
     */
    protected abstract JavaFile createFile(Element element, TypeSpec typeSpec);

    /**
     * 函数构造对象
     * @param elements 注解元素
     * @return 函数构造对象
     */
    protected abstract MethodSpec createMethod(Set<? extends Element> elements);

    /**
     * 类构造对象
     * @param methodSpec 函数构造对象
     * @return 类构造对象
     */
    protected abstract TypeSpec createClass(MethodSpec methodSpec);
}
