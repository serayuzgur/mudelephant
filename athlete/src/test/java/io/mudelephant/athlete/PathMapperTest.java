package io.mudelephant.athlete;

import com.google.common.collect.Sets;
import io.mudelephant.athlete.resource.ResourceMapper;

import java.util.Set;

public class PathMapperTest {

    @org.junit.Test
    public void testGetRouteMap() throws Exception {
        Set<Class<?>> classes = Sets.newHashSet();

        classes.add(SampleResource.class);

        ResourceMapper mapper = new ResourceMapper(null, classes);

        assert mapper.getRouteMap().size() == SampleResource.class.getDeclaredMethods().length;
//       for(Map.Entry<String, Method> entries : mapper.getRouteMap().entrySet()){
//           System.out.println(entries.getKey() + " : " + entries.getValue().getName());
//       }


    }
}