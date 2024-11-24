package conn.ra.repository;


import conn.ra.model.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogRepository extends JpaRepository<Categories,Long> {
    List<Categories> findByStatus(boolean status);
}
