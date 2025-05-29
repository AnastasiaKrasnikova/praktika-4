import java.util.HashMap;
import java.util.Map;

public class CachingService {
    private static CachingService instance;
    private Map<Integer, DataEntity> cache;
    
    private CachingService() {
        this.cache = new HashMap<>();
    }
    
    public static synchronized CachingService getInstance() {
        if (instance == null) {
            instance = new CachingService();
        }
        return instance;
    }
    
    public DataEntity getData(int id) {
        // Проверяем кэш
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        
        // Если нет в кэше, запрашиваем из БД
        DataEntity entity = DatabaseRepository.getInstance().getById(id);
        
        // Кэшируем только read-only данные
        if (entity != null && entity.isReadOnly()) {
            cache.put(id, entity);
        }
        
        return entity;
    }
    
    public void clearCache() {
        cache.clear();
    }
}
