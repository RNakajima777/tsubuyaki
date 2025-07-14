package jp.kobe_u.cs.daikibo.tsubuyaki.repository;

import org.springframework.stereotype.Repository;
import jp.kobe_u.cs.daikibo.tsubuyaki.entity.Tsubuyaki;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

@Repository  
public interface TsubuyakiRepository extends CrudRepository<Tsubuyaki, Long>{
    public List<Tsubuyaki> findByCommentContaining(String comment);
}
