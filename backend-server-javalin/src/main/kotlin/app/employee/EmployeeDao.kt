package app.employee

import java.util.concurrent.atomic.AtomicInteger

class EmployeeDao {

    // "Initialize" with a few employees
    // This demonstrates type inference, map-literals and named parameters
    val employees = hashMapOf(
            0 to Employee(name = "Alice", age = 43, id = 0),
            1 to Employee(name = "Bob", age = 23, id = 1),
            2 to Employee(name = "Carol", age = 51, id = 2),
            3 to Employee(name = "Dave", age = 31, id = 3)
    )

    var lastId: AtomicInteger = AtomicInteger(employees.size - 1)

    fun save(name: String, age: Int): Employee? {
        val id = lastId.incrementAndGet()
        employees[id] = Employee(name = name, age = age, id = id)
        return employees[id]
    }

    fun findById(id: Int): Employee? {
        return employees[id]
    }

    fun findByName(name: String): Employee? {
        return employees.values.find { it.name == name } // == is equivalent to java .equals() (referential equality is checked by ===)
    }

    fun update(id: Int, employee: Employee) {
        employees[id] = Employee(name = employee.name, age = employee.age, id = id)
    }

    fun delete(id: Int) {
        employees.remove(id)
    }

}
