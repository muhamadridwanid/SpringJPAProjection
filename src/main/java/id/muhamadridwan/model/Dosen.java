package id.muhamadridwan.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by mridwan on 14/11/2017.
 */
@Data
@Entity
public class Dosen {
    @Id
    @GeneratedValue
    private long id;
    private String nama;
    private String alamat;
}
