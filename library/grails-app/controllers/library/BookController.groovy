package library

import com.ttnd.library.Book
import grails.rest.RestfulController

class BookController extends RestfulController<Book> {
    static responseFormats = ['json', 'xml']

    BookController() {
        super(Book)
    }

    def list() {
        log.info "Getting Books"
        respond Book.list(params)
    }
}