# libraryapp
 library app test


# API for Library app

###### Customer Api

- Login http://localhost:8080/api/auth/signin 
- signup http://localhost:8080/api/auth/signup
- update customer http://localhost:8080/api/customer/updatecustomer/{id}
- get all customer http://localhost:8080/api/customer/allcustomer
- delete customer http://localhost:8080/api/customer/deletecustomer/{id}
- get single customer http://localhost:8080/api/customer/getcustomer/{id}
- add customer http://localhost:8080/api/customer/addcustomer

###### Book Api

- update book http://localhost:8080/api/book/update-book/{id}
- get all book http://localhost:8080/api/book/all-books/
- delete book http://localhost:8080/api/book/deletebook/{id}
- get single book http://localhost:8080/api/book/get-book/{id}
- add book http://localhost:8080/api/book/add-book/

###### Category Api
- update category http://localhost:8080/api/category/update-category/{id}
- get all catgories http://localhost:8080/api/category/all-categories
- delete category http://localhost:8080/api/category/delete-category/{id}
- get single category http://localhost:8080/api/category/get-category/{id}
- add category http://localhost:8080/api/category/add-category

i have used Mysql database 
but i  have also added H2 dependency in the pom.xml file and applicaiton.properities by these are commented if want to use H2 memory database can use it by uncommenting it
