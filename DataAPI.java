public class DataAPI {
    private CachingService cachingService;
    
    public DataAPI() {
        this.cachingService = CachingService.getInstance();
    }
    
    public String getDataContent(int id) {
        DataEntity entity = cachingService.getData(id);
        return entity != null ? entity.getContent() : "Data not found";
    }
    
    public boolean updateDataContent(int id, String newContent) {
        DataEntity entity = DatabaseRepository.getInstance().getById(id);
        if (entity == null) return false;
        
        try {
            entity.setContent(newContent);
            DatabaseRepository.getInstance().updateData(entity);
            return true;
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public void generateReport(int[] ids) {
        System.out.println("Generating report...");
      for (int id : ids) {
            DataEntity entity = cachingService.getData(id);
            System.out.println("ID: " + id + ", Content: " + 
                (entity != null ? entity.getContent() : "N/A"));
        }
    }
    
    public void exportResults(int[] ids, boolean singleFile) {
        if (singleFile) {
            System.out.println("Exporting to single file:");
            for (int id : ids) {
                DataEntity entity = DatabaseRepository.getInstance().getById(id);
                System.out.println("ID: " + id + ", Content: " + 
                    (entity != null ? entity.getContent() : "N/A"));
            }
        } else {
            System.out.println("Exporting to multiple files:");
            for (int id : ids) {
                DataEntity entity = DatabaseRepository.getInstance().getById(id);
                System.out.println("File for ID " + id + ": " + 
                    (entity != null ? entity.getContent() : "N/A"));
            }
        }
    }
}
