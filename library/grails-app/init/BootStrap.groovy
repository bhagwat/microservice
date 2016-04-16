import com.ttnd.library.Book

class BootStrap {

    def init = { servletContext ->
        if (Book.count < 1) {
            1..10.each { index ->
                new Book(name: "Book $index", author: "Author $index").save()
            }
        }
    }
    def destroy = {
    }
}
