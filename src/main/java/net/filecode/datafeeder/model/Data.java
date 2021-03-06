package net.filecode.datafeeder.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "datas", type = "data")
public class Data {

    @Id
    private String id;

    private String description;
    
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
