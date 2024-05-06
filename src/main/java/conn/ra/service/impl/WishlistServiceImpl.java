package conn.ra.service.impl;

import conn.ra.model.dto.request.WishlistRequest;
import conn.ra.model.entity.Book;
import conn.ra.model.entity.User;
import conn.ra.model.entity.WishList;
import conn.ra.repository.WishListRepository;
import conn.ra.service.BookService;
import conn.ra.service.UserService;
import conn.ra.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {
    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @Override
    public WishList add(Long userId, WishlistRequest wishlistRequest) {
        User user = userService.findById ( userId );

        Book book = bookService.findById ( wishlistRequest.getBookId () );

        if (book == null) {
            throw new RuntimeException ( "không tồn tại sách" );
        }

        WishList wishList = WishList.builder ()
                .users ( user )
                .book ( book )
                .build ();
        return wishListRepository.save ( wishList );
    }

    @Override
    public List<WishList> getAll(Long userId) {
        return wishListRepository.getAllByUserId ( userId );
    }

    @Override
    public void delete(Long wishlistId, Long userId) {
        wishListRepository.deleteByIdAndUserId ( wishlistId, userId );
    }

    @Override
    public WishList findByUserAndBook(Long userId, Long bookId) {
        return wishListRepository.findByUserAndBook ( userId, bookId );
    }
}
