package id.muhamadridwan.service;

import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import id.muhamadridwan.model.Dosen;
import id.muhamadridwan.dto.DosenCustom;
import id.muhamadridwan.model.QDosen;
import id.muhamadridwan.repository.DosenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by mridwan on 14/11/2017.
 */
@Component
public class DosenServiceImpl implements DosenService {

    @Autowired
    private DosenRepository repository;

    private final QDosen qDosen = QDosen.dosen;

    @Override
    public void saveDosen(List<Dosen> dosens) {
        repository.save(dosens);
    }

    @Override
    public List<Dosen> getDosenAll() {
        return repository.findAll();
    }

    @Override
    public List<DosenCustom> getDosenCustomAll() {
        FactoryExpression<Dosen> column = Projections.bean(Dosen.class, qDosen.nama, qDosen.alamat);
        Class targetClass = DosenCustom.class;
        Predicate predicate = qDosen.nama.like("%%");
        return repository.findWithProjection(column, targetClass, predicate);
    }
}
