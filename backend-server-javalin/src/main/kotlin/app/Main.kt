package app

import app.employee.Employee
import app.employee.EmployeeDao
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.Javalin

fun main(args: Array<String>) {

    val employeeDao = EmployeeDao()

    val app = Javalin.create().apply {
        exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("not found") }
    }.enableCorsForAllOrigins().start(7000)

    app.routes {

        get("/employees") { ctx ->
            ctx.json(employeeDao.employees)
        }

        get("/employees/:employee-id") { ctx ->
            ctx.json(employeeDao.findById(ctx.pathParam("employee-id").toInt())!!)
        }

        get("/employees/name/:name") { ctx ->
            ctx.json(employeeDao.findByName(ctx.pathParam("name"))!!)
        }

        post("/employees") { ctx ->
            val employee = ctx.body<Employee>()
            val newEmployee = employeeDao.save(name = employee.name, age = employee.age)!!
            ctx.status(201)
            ctx.json(newEmployee)
        }

        patch("/employees/:employee-id") { ctx ->
            val employee = ctx.body<Employee>()
            employeeDao.update(
                    id = ctx.pathParam("employee-id").toInt(),
                    employee = employee
            )
            ctx.status(204)
        }

        delete("/employees/:employee-id") { ctx ->
            employeeDao.delete(ctx.pathParam("employee-id").toInt())
            ctx.status(204)
        }

    }

}
