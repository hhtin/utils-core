package vn.tinhh.utils.core.functions;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapperUtil {
    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper
                .getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

    }

    /**
     * Hide from public usage.
     */
    private MapperUtil() {
    }

    public static <D, T> D map(final T entity, Class<D> outClass) {
        if (entity == null) {
            return null;
        }
        return modelMapper.map(entity, outClass);
    }

    public static <D, T> List<D> map(final Collection<T> entityList, Class<D> outCLass) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }
    public static <D, T> Page<D> map(final Page<T> page, Class<D> outCLass) {
        if (page == null) {
            return null;
        }
        return page.map(objectEntity -> modelMapper.map(objectEntity, outCLass));
    }

    public static <S, D> D map(final S source, D destination) {
        if (source == null) {
            return null;
        }
        modelMapper.map(source, destination);
        return destination;
    }

    public static Field getField(String field, Class cs) {
        try {
            return cs.getDeclaredField(field);
        } catch (NoSuchFieldException e) {
            Class superClass = cs.getSuperclass();
            while (superClass != null) {
                try {
                    return cs.getSuperclass().getDeclaredField(field);
                } catch (Exception ex) {
                    superClass = cs.getSuperclass();
                }
            }
        }
        return null;
    }
    public static Map<String, Object> map(Object request) {
        Map<String, Object> map = new HashMap<>();
        for (Field field : request.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try{
                if(!ObjectUtils.isEmpty(field.get(request))){
                    map.put(field.getName(), field.get(request));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return map;
    }
}
