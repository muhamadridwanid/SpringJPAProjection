package id.muhamadridwan.repository;

import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.BooleanExpression;
import id.muhamadridwan.model.Dosen;
import id.muhamadridwan.model.QDosen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

/**
 * Created by mridwan on 14/11/2017.
 */
public interface DosenRepository extends QueryDslPredicateAndProjectionExecutor<Dosen, Long> {
}