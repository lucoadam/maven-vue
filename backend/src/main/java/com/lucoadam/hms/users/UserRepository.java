package com.lucoadam.hms.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Integer> {

    @Query(" SELECT user FROM User user WHERE user.username LIKE ?1")
    User findByUsername(String username);

    @Query(value = " SELECT user FROM User user  WHERE ( user.name LIKE ?1 or user.username LIKE ?1 ) and  user.username <> 'superuser' ",
            countQuery = " SELECT count(user) FROM User user WHERE ( user.name LIKE ?1 or user.username LIKE ?1 ) and user.username <> 'superuser' " )
    Page<UsersResponseDTO> search(String s, Pageable page);

    @Query( value = " SELECT user FROM User user WHERE user.username <> 'superuser'"
            , countQuery = " SELECT count(user) FROM User user  WHERE user.username <> 'superuser' ")
    Page<UsersResponseDTO> customFindAll(Pageable pageable);

    @Query(" SELECT count(*) FROM User user WHERE user.username = ?1 ")
    Integer usernameCount(String username);

    @Query(" SELECT user FROM User  user WHERE user.id in ?1 ")
    List<User> customFindById(Collection<Integer> users);

    @Query( " SELECT user FROM User user WHERE user.id in  ?1 " )
    UsersResponseDTO customFindUserById(Integer id);
}
