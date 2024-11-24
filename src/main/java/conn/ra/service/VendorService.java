package conn.ra.service;

import conn.ra.model.entity.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VendorService {
    List<Vendor> getAll();

    Vendor findById(Long id);

    Boolean add(Vendor vendor);

    Boolean edit(Vendor vendor);

    void delete(Long id);

    List<Vendor> getByStatus();
}
