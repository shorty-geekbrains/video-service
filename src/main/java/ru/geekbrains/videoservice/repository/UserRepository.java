package ru.geekbrains.videoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.videoservice.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    <S extends User> S save(S entity);

    @Override
    Optional<User> findById(Long aLong);

    @Override
    List<User> findAll();

    @Override
    void deleteById(Long aLong);

    User findByUserName(String userName);

    User findByUserSecondName(String userSecondName);
}
