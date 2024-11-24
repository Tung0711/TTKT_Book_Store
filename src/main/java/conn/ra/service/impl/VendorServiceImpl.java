package conn.ra.service.impl;

import conn.ra.model.entity.Vendor;
import conn.ra.repository.VendorRepository;
import conn.ra.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public List<Vendor> getAll() {
        return vendorRepository.findAll ();
    }

    @Override
    public Vendor findById(Long id) {
        return vendorRepository.findById ( id ).orElse ( null );
    }

    @Override
    public Boolean add(Vendor vendor) {
        try {
            vendor.setStatus ( true );
            vendorRepository.save ( vendor );
            return true;
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return false;
    }

    @Override
    public Boolean edit(Vendor vendor) {
        vendorRepository.save ( vendor );
        return true;
    }

    @Override
    public void delete(Long id) {
        vendorRepository.deleteById ( id );
    }

    @Override
    public List<Vendor> getByStatus() {
        return vendorRepository.findByStatus ( true );
    }
}
