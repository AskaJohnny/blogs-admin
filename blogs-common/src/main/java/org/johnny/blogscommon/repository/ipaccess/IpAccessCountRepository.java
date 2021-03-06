package org.johnny.blogscommon.repository.ipaccess;

import org.johnny.blogscommon.entity.ipaccess.IpAccessCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ip access repository
 *
 * @author johnny
 * @create 2020-08-14 下午2:24
 **/
@Repository
public interface IpAccessCountRepository extends JpaRepository<IpAccessCount, Long> {

}