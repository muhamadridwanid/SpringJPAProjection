package id.muhamadridwan.service;

import id.muhamadridwan.model.Dosen;
import id.muhamadridwan.dto.DosenCustom;

import java.util.List;

/**
 * Created by mridwan on 14/11/2017.
 */
public interface DosenService {

    void saveDosen(List<Dosen> dosens);

    List<Dosen> getDosenAll();

    List<DosenCustom> getDosenCustomAll();

}
