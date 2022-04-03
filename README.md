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

