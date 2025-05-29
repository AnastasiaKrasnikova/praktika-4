import java.util.HashMap;
import java.util.Map;

public class DatabaseRepository {
    private static DatabaseRepository instance;
    private Map<Integer, DataEntity> database;
    
    private DatabaseRepository() {
        this.database = new HashMap<>();
        // Инициализация тестовыми данными
        database.put(1, new DataEntity(1, "Read-only data 1", true));
        database.put(2, new DataEntity(2, "Read-only data 2", true));
        database.put(3, new DataEntity(3, "Modifiable data 1", false));
    }
    
    public static synchronized DatabaseRepository getInstance() {
        if (instance == null) {
            instance = new DatabaseRepository();
        }
        return instance;
    }
    
    public DataEntity getById(int id) {
        // Имитация задержки при обращении к БД
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return database.get(id);
    }
    
    public void updateData(DataEntity entity) {
        if (!entity.isReadOnly()) {
            database.put(entity.getId(), entity);
        }
    }
}
