package com.robbie.mvc.repository;

import com.robbie.mvc.entity.Dealership;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DealershipRepository extends BaseRepository<Dealership,Long>{
}
