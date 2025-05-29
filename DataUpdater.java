import java.util.Timer;
import java.util.TimerTask;

public class DataUpdater {
    private Timer timer;
    private DataAPI dataAPI;
    
    public DataUpdater(DataAPI dataAPI, long updateInterval) {
        this.dataAPI = dataAPI;
        this.timer = new Timer();
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateNonCachedData();
            }
        }, 0, updateInterval);
    }
    
    private void updateNonCachedData() {
        System.out.println("Updating non-cached data from DB...");
        // В реальной системе здесь был бы запрос к БД для получения
        // списка ID изменяемых данных и их обновление
    }
    
    public void stop() {
        timer.cancel();
    }
}
