package pojo;

import lombok.Data;

@Data
public class POJO {
    private int id;
    private int field;

//определены отдельно, потому jdom не умеет работать с lombok
    public void setField(int field) {
        this.field = field;
    }

    public void setId(int id) {
        this.id = id;
    }
}
