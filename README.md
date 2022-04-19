Create student

POST: http://localhost:8080/student-management-system/api/v1/students

JSON:
{
"firstName": "Förnamn",
	"lastName": "Efternamn",
	"email": "förnamn.efternamn@iths.se",
	"phoneNumber": "valfritt"
}


Update student

PUT: http://localhost:8080/student-management-system/api/v1/students

JSON:
{
	"id": id,
	"firstName": "Förnamn",
	"lastName": "Efternamn",
	"email": "förnamn.efternamn@iths.se",
	"phoneNumber": "valfritt"
}


Get student by ID

GET: http://localhost:8080/student-management-system/api/v1/students/{id}


Get students by last name

GET: http://localhost:8080/student-management-system/api/v1/students/lastname:{lastname}


Get all students

GET: http://localhost:8080/student-management-system/api/v1/students


Remove student

DELETE: http://localhost:8080/student-management-system/api/v1/students/{id}


Update phone number

PATCH: http://localhost:8080/student-management-system/api/v1/students/{id}

JSON:
{
	"phoneNumber": "telefonnummer"
}

Create subject

POST: http://localhost:8080/student-management-system/api/v1/subjects

JSON:
{
"name": "Kursnamn"
}


Get subject by ID

GET: http://localhost:8080/student-management-system/api/v1/subjects/{id}


Get all students

GET: http://localhost:8080/student-management-system/api/v1/subjects


Remove student

DELETE: http://localhost:8080/student-management-system/api/v1/subjects/{id}


Create teacher

POST: http://localhost:8080/student-management-system/api/v1/teachers

JSON:
{
"name": "Namn"
}


Get teacher by ID

GET: http://localhost:8080/student-management-system/api/v1/teachers/{id}


Get all teachers

GET: http://localhost:8080/student-management-system/api/v1/teachers


Remove teacher

DELETE: http://localhost:8080/student-management-system/api/v1/teachers/{id}