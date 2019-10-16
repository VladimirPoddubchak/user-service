package com.poddubchak.myproject.userservice.repository;


import com.poddubchak.microservicies.model.user.MyUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by @author Vladimir Poddubchak @date 10.10.2019.
 */
@CrossOrigin
@RepositoryRestResource
public interface UserRepository extends CrudRepository<MyUser,Long> {
}
