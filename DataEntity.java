public class DataEntity {
    private int id;
    private String content;
    private boolean readOnly;
    
    public DataEntity(int id, String content, boolean readOnly) {
        this.id = id;
        this.content = content;
        this.readOnly = readOnly;
    }
    
    // Геттеры
    public int getId() { return id; }
    public String getContent() { return content; }
    public boolean isReadOnly() { return readOnly; }
    
    // Сеттеры
    public void setContent(String content) {
        if (!readOnly) {
            this.content = content;
        } else {
            throw new IllegalStateException("Cannot modify read-only data");
        }
    }
}
