package org.johnny.blogscommon.repository.ipaccess;

import org.johnny.blogscommon.entity.ipaccess.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author johnny
 * @create 2020-08-14 下午3:47
 **/
@Repository
public interface CityRepository extends JpaRepository<City, Long> {


}