package pojo;


import lombok.Data;

/**
 * Lombok должен был быть использован для удобства описания,
 * но к сожалению с SAX подружить их не смог.
 * Потому добавил требуемые поля вручную.
 */

@Data
public class POJO {

    private int field;

    public POJO(int field) {
        this.field = field;
    }

    public int getField() {
        return field;
    }
}
