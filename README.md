## Course-Student Application ##


#### SWAGGER URL

    http://localhost:8080/swagger-ui.html


#### Api 

* Get all Courses
    ```
    isOrdered(required = false)
    GET http://localhost:8080/courses?isOrdered=true
    ```

* Get Course By id
    ```
    GET http://localhost:8080/courses/{{id}}
    ```

* Create Course
    ```
    POST http://localhost:8080/courses
        {
          "courseName": "string",
          "creditScore": 0
        }
    ```
* Delete Course By id
    ```
    DELETE http://localhost:8080/courses/{{id}}
    ```

* Update Course
    ```
    PUT http://localhost:8080/courses
      {
         "creditScore": 0,
         "id": 0,
         "name": "string"
      }
    ```

* Add Student To Course

    ```
    POST http://localhost:8080/courses/2/addStudent/{{id}}
    ```

* Delete Student In Course

    ```
    DELETE http://localhost:8080/courses/2/deleteStudent/{{id}}
    ```
