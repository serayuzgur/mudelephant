package io.mudelephant.athlete.resource;


import io.mudelephant.athlete.param.setter.*;
import io.mudelephant.common.utils.StringUtils;

import javax.ws.rs.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ResourceMapper helps to create a resource mapping according to url paths
 */
public class ResourceMapper {

    private final ConcurrentHashMap<String, MethodEntry> routeMap = new ConcurrentHashMap<String, MethodEntry>();

    private final String context;

    /**
     * PathMapper takes a set of {@link Resource} classes and creates a method level mapping for them
     * It provides a fast access mechanism for routing request.
     *
     * @param classes
     */
    public ResourceMapper(String context, Set<Class<?>> classes) {
        this.context = (context == null) ? "" : context;
        for (Class<?> clazz : classes) {
            collectPaths(clazz);
        }
    }

    /**
     * Scans class and all methods to collect path and http method annotations
     *
     * @param clazz
     */
    private void collectPaths(Class<?> clazz) {
        String cPath = getPathFromAnnotation(clazz.getAnnotation(Path.class)).toLowerCase(Locale.ENGLISH);

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            for (String httpMethod : getHttpMethodAnnotations(method)) {
                StringBuilder keyBuilder = new StringBuilder();
                keyBuilder.append(context)
                        .append('/')
                        .append(cPath)
                        .append('/')
                        .append(getPathFromAnnotation(method.getAnnotation(Path.class)).toLowerCase(Locale.ENGLISH))
                        .append('/')
                        .append(httpMethod);
                String key = StringUtils.replaceNSlashWith1Slash(keyBuilder.toString());
                if (routeMap.containsKey(key))
                    throw new RuntimeException("Path conflict : " + key);
                //TODO: find a nice way to log paths registered.

                routeMap.put(key, new MethodEntry(method, decideParameterSetters(method, httpMethod)));
            }

        }
    }

    private ArrayList<String> getHttpMethodAnnotations(Method method) {
        Annotation[] annotations = method.getDeclaredAnnotations();
        ArrayList<String> methods = new ArrayList<String>();
        for (Annotation annotation : annotations) {
            HttpMethod hm = annotation.annotationType().getAnnotation(HttpMethod.class);
            if (hm != null) {
                methods.add(hm.value());
            }
        }
        methods.trimToSize();
        return methods;
    }

    private Setter[] decideParameterSetters(Method method, String httpMethod) {
        Annotation[][] annotations = method.getParameterAnnotations();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Setter[] setters = new Setter[parameterTypes.length];
        int i = 0;
        for (Annotation[] anns : annotations) {
            Class<?> type = parameterTypes[i];
            if (anns.length == 0) {
                //It means it will parse from JsonPayload
                Setter setter = new ByJson(null, type);
                setters[i] = setter;

            } else {
                DefaultValue defaultValue = null;
                for (int j = 0; j < anns.length; j++) {
                    if (DefaultValue.class.isInstance(anns[j]))
                        defaultValue = (DefaultValue) anns[j];
                }
                for (Annotation ann : anns) {
                    Setter setter = null;
                    if (CookieParam.class.isInstance(ann))
                        setter = new ByCookie(defaultValue, type, ((CookieParam) ann).value());
                    else if (HeaderParam.class.isInstance(ann))
                        setter = new ByHeader(defaultValue, type, ((HeaderParam) ann).value());
                    else if (QueryParam.class.isInstance(ann))
                        setter = new ByQuery(defaultValue, type, ((QueryParam) ann).value());
                    else if (FormParam.class.isInstance(ann)) {
                        //If it is a get method it means they will be query parameters
                        if ("GET".equals(httpMethod))
                            setter = new ByQuery(defaultValue, type, ((FormParam) ann).value());
                        else
                            setter = new ByForm(defaultValue, type, ((FormParam) ann).value());
                    } else if (DefaultValue.class.isInstance(anns))
                        continue;

                    setters[i] = setter;
                }
            }
            i++;
        }
        return setters;
    }


    private String getPathFromAnnotation(Path path) {
        return (path == null) ? "" : path.value();
    }

    public Map<String, MethodEntry> getRouteMap() {
        return Collections.unmodifiableMap(routeMap);
    }
}
