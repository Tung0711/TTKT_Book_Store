package conn.ra.repository;

import conn.ra.model.entity.Address;
import conn.ra.model.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a from Address a where a.users.id = :id")
    List<Address> findAllByUserId(Long id);

    void deleteByIdAndUsers(Long addressId, User user);

    @Query("SELECT a from Address a where a.id = :addressId and a.users.id = :userId")
    Address findByIdAndUserId(Long addressId, Long userId);

    List<Address> findAllByUsers(User user);
}
